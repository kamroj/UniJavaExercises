//Autor : Kamil Rojek

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public final class F1Statistics 
{	
	//columns in excel
	private static final int place = 0;
	private static final int bolidNumber = 1;
	private static final int driverName = 2;
	private static final int team = 3;
	private static final int time = 4;
	private static final int points = 5;
	
	public static void printWinners(XSSFWorkbook workBook) 
	{
		final int firstPlaceRow = 0;
		HashSet<String> winnerList = new HashSet<String>();
		
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) 
		{			
			var sheet = workBook.getSheetAt(i);					
			
			XSSFRow row = sheet.getRow(firstPlaceRow);
			XSSFCell cell = row.getCell(driverName);			
			
			winnerList.add(cell.getStringCellValue());			
		}
		
		System.out.println("***List of winners with at least one win***");
		
		for (String name : winnerList) 
		{
			System.out.println(name);
		}
		
		winnerList.clear();
	}
	
	public static void summaryHamiltonPoints(XSSFWorkbook workBook)
	{
		final String hamilton= "Lewis Hamilton HAM";
		int summaryPoints = 0;
		
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) 
		{			
			var sheet = workBook.getSheetAt(i);
			var rowCount = sheet.getLastRowNum();			
			
			while(rowCount >= 0)
			{
				String currentName = sheet.getRow(rowCount).getCell(driverName).getStringCellValue();
				int currentValue = Integer.parseInt(sheet.getRow(rowCount).getCell(points).getStringCellValue());				
				
				summaryPoints += hamilton.equalsIgnoreCase(currentName) ?  currentValue : 0;
				
				if (hamilton.equalsIgnoreCase(currentName)) 
				{
					break;
				}
				else
				{
					--rowCount;					
				}				
			}						
		}
		
		System.out.println("\nHamilton summary points: " + summaryPoints);
		
	}
	
	public static void summaryFerrariPoints(XSSFWorkbook workBook)
	{
		final String teamName= "Ferrari";
		int summaryPoints = 0;
		
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) 
		{			
			var sheet = workBook.getSheetAt(i);
			var rowCount = sheet.getLastRowNum();			
			
			while(rowCount >= 0)
			{
				String currentTeamName = sheet.getRow(rowCount).getCell(team).getStringCellValue();
				int currentValue = Integer.parseInt(sheet.getRow(rowCount).getCell(points).getStringCellValue());				
				
				summaryPoints += teamName.equalsIgnoreCase(currentTeamName) ?  currentValue : 0;				
				
				--rowCount;			
			
			}						
		}
		
		System.out.println("\nFerrari summary points: " + summaryPoints + "\n");
		
	}
	
	public static void printFinalScores(XSSFWorkbook workBook)
	{
		int place = 1;		
		int previousPoints = 0;
		
		Boolean firstIteration = true;
		
		var finalList = calculateFinalScores(workBook);		
		var finalListSorted = sortMap(finalList);
		
		System.out.println("***** FINAL SCOREBOARD *****");
		
		for (String key : finalListSorted.keySet()) 
		{
			int currentPoints = finalListSorted.get(key);
			
			if (currentPoints != previousPoints && firstIteration == false) 
			{
				previousPoints = finalListSorted.get(key);
				++place;
			}			
			
			System.out.println(place + ". " + key + " " + finalListSorted.get(key));
			
			firstIteration = false;
		}
		
		finalList.clear();
		finalListSorted.clear();
	}
	
	public static HashMap<String, Integer> calculateFinalScores(XSSFWorkbook workBook)
	{
		HashMap<String, Integer> finalList = new HashMap();
		
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) 
		{
			var sheet = workBook.getSheetAt(i);
			
			for (int j = 0; j < sheet.getLastRowNum(); j++) 
			{
				String currentName = sheet.getRow(j).getCell(driverName).getStringCellValue();
				int currentValue = Integer.parseInt(sheet.getRow(j).getCell(points).getStringCellValue());	
				
				if (finalList.containsKey(currentName)) 
				{
					finalList.computeIfPresent(currentName, (key, value) -> value += currentValue);
				}
				else
				{
					finalList.put(currentName, currentValue);
				}		
			}			
		}
		
		return finalList;		
	}
	
	public static XSSFWorkbook writeExcelWorkBookWithDriverScores(XSSFWorkbook workBook)
	{
		XSSFWorkbook newWorkBook = new XSSFWorkbook();
		var newSheet = newWorkBook.createSheet("MySheet");
		
		int startingCell = 0;		
		int rowForNames = 1;
		Boolean firstCountry = true;
		
		LinkedHashMap<String, LinkedHashMap<String, Integer>> scoresInCountries = prepareMap(workBook);		
		var driversNamesList = generateAllDriversNamesList(scoresInCountries);		
		prepareWorkSheet(newSheet, driversNamesList);		
		
		for (String driverName : driversNamesList) 
		{			
			XSSFRow row = newSheet.getRow(rowForNames);
			XSSFCell cell = row.createCell(startingCell);
			
			cell.setCellValue(driverName);
			
			++rowForNames;
		}		
		
		for (String country : scoresInCountries.keySet()) 
		{
			int headerRow = 0;
			++startingCell;	
			
			XSSFRow row = newSheet.getRow(headerRow);
			XSSFCell cell = row.createCell(startingCell);
			
			cell.setCellValue(country);			
			
			for (String driverName : scoresInCountries.get(country).keySet()) 
			{				
				int rowIndex = findDriverRowIndex(newSheet, driverName);				
				double driverPoints = 0;
				
				row = newSheet.getRow(rowIndex);
				cell = row.createCell(startingCell);
				
				if (firstCountry == true)
				{
					driverPoints = scoresInCountries.get(country).get(driverName);					
				}
				else
				{
					XSSFCell previousCell = row.getCell(startingCell - 1);					
					
					if (previousCell == null)
						continue;
					
					driverPoints = previousCell.getNumericCellValue();
					
					driverPoints += scoresInCountries.get(country).get(driverName);					
				}
				
				cell.setCellValue(driverPoints);
				
			}
			firstCountry = false;
			 
		}
		
		prettyWorkBook(newSheet, newWorkBook);
		
		return newWorkBook;		 
	}

	private static void prettyWorkBook(XSSFSheet newSheet, XSSFWorkbook newWorkBook) 
	{
		newSheet.setTabColor(new XSSFColor(IndexedColors.RED, null));
		int numberOfColumns = newSheet.getRow(0).getLastCellNum();		
		
		for (int i = 0; i < numberOfColumns; i++) 
		{
			if (i == 0)
			{
				newSheet.setColumnWidth(i, 6000);				
			}			
			else
			{
				newSheet.setColumnWidth(i, 3000);				
			}			
		}		
	}	
	
	private static int findDriverRowIndex(XSSFSheet newSheet, String driverName) 
	{
		int index = 0;
		
		for (int i = 1; i <= newSheet.getLastRowNum(); i++) 
		{
			var row = newSheet.getRow(i);
			
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(driverName))
			{
				index = i;
			}
		}
		return index;
	}

	private static void prepareWorkSheet(XSSFSheet newSheet, List<String> driversNamesList) 
	{		
		for (int i = 0; i < driversNamesList.size() + 1; i++) 
		{
			XSSFRow row = newSheet.createRow(i);
		}		
	}

	private static LinkedHashMap<String, LinkedHashMap<String, Integer>> prepareMap(XSSFWorkbook workBook) 
	{
		LinkedHashMap <String, LinkedHashMap<String, Integer>> scoresInCountries 
					= new LinkedHashMap<String, LinkedHashMap<String,Integer>>();
		
		for (int i = 0; i < workBook.getNumberOfSheets(); i++) 
		{
			var sheet = workBook.getSheetAt(i);
			
			String currentCountry = workBook.getSheetName(i);
			scoresInCountries.put(currentCountry, new LinkedHashMap<String, Integer>());
			
			for (int j = 0; j < sheet.getLastRowNum(); j++) 
			{
				String currentName = sheet.getRow(j).getCell(driverName).getStringCellValue();
				int currentValue = Integer.parseInt(sheet.getRow(j).getCell(points).getStringCellValue());	
				
				scoresInCountries.get(currentCountry).put(currentName, currentValue);
			}
		}
		return scoresInCountries;
	}

	private static List<String> generateAllDriversNamesList(LinkedHashMap<String, LinkedHashMap<String, Integer>> scoresInCountries) 
	{
		//nie znam się na F1 zakładam że w kolejnych rundach mogą brać udział inni zawodnicy
		
		HashSet<String> allDriversNameList = new HashSet<String>();
		
		for (String city : scoresInCountries.keySet()) 
		{
			for (String driverName : scoresInCountries.get(city).keySet()) 
			{
				allDriversNameList.add(driverName);
			}
		}
		
		List convertToList = new ArrayList(allDriversNameList);
		Collections.sort(convertToList);
		
		return convertToList;
	}
	
	private static HashMap<String, Integer> sortMap (HashMap<String, Integer> mapToSort)
	{
		return mapToSort.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect
				(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
	}
	
}
