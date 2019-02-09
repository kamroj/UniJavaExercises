//Autor : Kamil Rojek

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App 
{
	public static void main(String[] args) {
		try 
			{
				FileInputStream file = new FileInputStream("f1-results.xlsx");
				XSSFWorkbook wb = new XSSFWorkbook(file);
			
				F1Statistics.printWinners(wb);
				F1Statistics.summaryHamiltonPoints(wb);
				F1Statistics.summaryFerrariPoints(wb);
				F1Statistics.printFinalScores(wb);			
				
				writeExcelFile(wb);
			} 
		catch (IOException e) 
			{
				e.printStackTrace();
			}		
	}

	private static void writeExcelFile(XSSFWorkbook wb) throws FileNotFoundException, IOException 
	{
		var newWorkBook = F1Statistics.writeExcelWorkBookWithDriverScores(wb);
		FileOutputStream output = new FileOutputStream("newWorkbook.xlsx");
		newWorkBook.write(output);
		output.close();
	}

}
