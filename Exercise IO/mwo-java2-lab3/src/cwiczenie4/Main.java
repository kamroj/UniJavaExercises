//Kamil Rojek

package cwiczenie4;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

import javax.naming.directory.BasicAttributes;

public class Main {

	public static void main(String args[]) throws Exception 
	{
		//printFilesSimple("C:\\");
		printFilesDetails("C:\\");
		//printFiles("C:\\", ".txt");
		//printTree("C:\\");
	}
 
	public static void printFilesSimple(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			System.out.println(file.getName());
		}
	}

	public static void printFilesDetails(String path) throws Exception 
	{
		StringBuilder sbFirstSpaces;
		StringBuilder sbSecondSpaces;
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();		
		
		int longestFileName = getLongestFileName(listOfFiles);
		int longestDigit = getLongestDigit(listOfFiles);	
		
		for (File file : listOfFiles) 
		{
			Path dirPath = Paths.get(file.getPath());
			BasicFileAttributes attrs = Files.readAttributes(dirPath, BasicFileAttributes.class);
			
			int missingSpacesBetweenNameAndSize = longestFileName - file.getName().length();
			int missingSpacesBetweenSizeAndDateNoDIR = longestDigit - String.valueOf(attrs.size()).length();
			int missingSpacesBetweenSizeAndDateDIR = longestDigit - 3;
			
			sbFirstSpaces = spaceCreator(missingSpacesBetweenNameAndSize);			
			sbSecondSpaces = attrs.isDirectory() ? 
					spaceCreator(missingSpacesBetweenSizeAndDateDIR)  : 
					spaceCreator(missingSpacesBetweenSizeAndDateNoDIR);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");			
			
			if (attrs.isDirectory())
			{
				System.out.println(file.getName() + sbFirstSpaces.toString() + "DIR" + sbSecondSpaces + df.format(attrs.creationTime().toMillis()));
			}
			else
			{
				System.out.println(file.getName() + sbFirstSpaces.toString() + attrs.size() + sbSecondSpaces + df.format(attrs.creationTime().toMillis()));
			}
		}
		
		

	}

	public static void printFiles(String path, String extensionFilter) 
	{
		FilenameFilter filter = new FilenameFilter() 
		{
			@Override
			public boolean accept(File path, String fileName)
			{				
				return fileName.endsWith(extensionFilter);
			}
		};
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(filter);		
		
		for (File file : listOfFiles)
		{
			System.out.println(file.getName());
		}
	}
	
	public static void printTree(String path) throws IOException 
	{		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();	
		
		if (listOfFiles == null) 
		{
			return;
		}
		
		for (File file : listOfFiles) 
		{
			Path dirPath = Paths.get(file.getPath());
			BasicFileAttributes attrs = Files.readAttributes(dirPath, BasicFileAttributes.class);				
			
			if (attrs.isDirectory())
			{
				printTree(file.getPath());
			}
			else
			{
				System.out.println(file.getPath() + "\\");
			}
		}
	}
	
	
	//helpers
	private static int getLongestFileName(File[] listOfFiles) 
	{
		int longestFileName = 0;
		
		for (File file : listOfFiles)
		{
			if (longestFileName < file.getName().length())
			{
				longestFileName = file.getName().length();
			}
		}
		return longestFileName;
	}

	private static int getLongestDigit(File[] listOfFiles) throws IOException 
	{
		int longestDigit = 0;
		
		for (File file : listOfFiles)
		{
			Path dirPath = Paths.get(file.getPath());
			BasicFileAttributes attrs = Files.readAttributes(dirPath, BasicFileAttributes.class);
			
			if (longestDigit < String.valueOf(attrs.size()).length())
			{
				longestDigit = String.valueOf(attrs.size()).length();
			}
		}
		return longestDigit;
	}
	
	
	
	private static StringBuilder spaceCreator(int missingSpaces)
	{
		StringBuilder sb = new StringBuilder("  ");
		
		for (int i = 0; i <= missingSpaces; ++i)
		{
			sb.append(" ");
		}
		
		return sb;
	}
	

}
