//Kamil Rojek

package zadanie1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

public class Skynet {
	
	Scanner scanner = new Scanner(System.in);
	
	HashMap<String, String> userDataMap = new HashMap<String, String>();
		
	public static void main(String args[]) 
	{
		Skynet skynet = new Skynet();
		skynet.run();
	}
	
	public void run()
	{
		readCredentials();
		printSkyNetLogo();
		runLoginPanel();
	}
	
	private void runLoginPanel() 
	{
		String readLogin;
		String readPassword;
		
		boolean credentialsOK;
		
		do 
		{
			readLogin = getTextFromInput("Login");
			readPassword = getTextFromInput("Password");
			credentialsOK = verifyCredentials(readLogin, readPassword);			
		} 
		while (!credentialsOK);
		
		System.out.println("\nWelcome to SKYNET...");
	}
	
	private String getTextFromInput(String label) 
	{		
		System.out.print(label + ": ");		
		return scanner.next();
	}
	
	private boolean verifyCredentials(String login, String password) 
	{
		if (!userDataMap.containsKey(login)) 
		{
			System.out.println("Wrong user name!");
			return false;
		}
		else
		{
			String userPassword = userDataMap.get(login);
			
			if (!userPassword.contentEquals(password))
			{
				System.out.println("Incorrect password!");
				return false;
			}
		}
		return true;
	}
	 
	private void readCredentials()
	{		
		String credentialsPath = "mwo-java2-lab3/res/zadanie1/skynet.conf";
		
		try 
		{
			//bufferedReaderHelper(credentialsPath);
			prepareUserDataBase(credentialsPath);
			System.out.println("INFO System reconfigured...\n\n");
		} 
		catch (IOException e) 
		{
			System.out.println("INFO No valid conf data");
		}		
	}
	
	private void printSkyNetLogo()
	{		
		String logoPath = "mwo-java2-lab3/res/zadanie1/logo.txt";		
		
		try 
		{
			bufferedReaderHelper(logoPath);
		} 
		catch (IOException e) 
		{
			System.out.println("Unabled to read logo!");
		}		
	}
	
	//obselote
	private void bufferedReaderHelper(String filePath) throws IOException
	{
		String line = null;
		File filename = new File(filePath);
		
		BufferedReader reader = Files.newBufferedReader(filename.toPath());		
		
		while((line = reader.readLine()) != null)
		{
			System.out.println(line);
		}
	}
	
	private void prepareUserDataBase(String filePath) throws IOException
	{	
		File filename = new File(filePath);
		
		try (BufferedReader reader = Files.newBufferedReader(filename.toPath()))
		{
			String login = null;
			String password = null;		
			
			int nullLinesCounter = 0;
			
			Boolean validateLogin = false;
			Boolean validatePassword = false;
			
			while(nullLinesCounter != 4)
			{
				String line = reader.readLine();
				if (line == null)
				{
					nullLinesCounter += 1;
					return;
				}
				
				if (line.contains("login")) 
				{
					String[] temp = line.split(" ");
					
					if (temp.length == 2)
					{						
						login = temp[temp.length-1];
						validateLogin = true;
						validatePassword = false;
					}
					else
					{
						System.out.println("Incorrect login!");
						return;
					}
				}
				else if (line.contains("password")) 
				{
					String[] temp = line.split(" ");
					
					if (temp.length == 2)
					{						
						password = temp[temp.length-1];
						validatePassword = true;
					}
					else
					{
						System.out.println("Incorrect password!");
						return;
					}
				}				
				
				if (validateLogin == true && validatePassword == true)
				{
					userDataMap.put(login, password);
					validateLogin = false;
					validatePassword = false;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Find some problems creating user data base!");
		}
	}
}
