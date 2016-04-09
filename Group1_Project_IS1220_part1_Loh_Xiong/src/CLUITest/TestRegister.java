package CLUITest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import commandinterface.ClientConsole;
import commandinterface.CommandConsole;

public class TestRegister {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		//readPaths for files to read
		
		String readPath = "eval/test1input.txt";
	    
	    //Initiate the file to read
	    
		File file = new File(readPath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String content = "";
		
		
		while(content != null){
			
			//Read a whole line from the readFile
			content = reader.readLine();
			
			//Stop the loop
			if (content.equalsIgnoreCase("EXIT")){
				System.out.print("Bye!");
				return;
			}
			
			//set an unborrowable book
			/*
			if (content.equalsIgnoreCase("set")){
				Client.libraries.get(0).getBox().getStockedItems().get(4).setBorrowable(false);
				continue;
			}
			*/
			ClientConsole.operationsInputTreatment(content);
			scanner.nextLine();

		}
		
		//Close readFile
		reader.close();
		
		System.out.println("Test1 complete!");
	}
}
