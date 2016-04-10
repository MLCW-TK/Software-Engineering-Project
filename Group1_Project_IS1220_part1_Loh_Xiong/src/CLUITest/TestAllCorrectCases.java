package CLUITest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import commandinterface.ClientConsole;
import commandinterface.CommandConsole;

/**
 * This test serves to demonstrate the typical functions of our programme.
 * It is designed with no wrong input.
 * To see the effect on notifyBirthday <>
 * you may change the birthday to the day when you execute this test
 * It is in the eval_1.txt
 * @author Xiong
 *
 */
public class TestAllCorrectCases {
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		//readPaths for files to read
		
		String readPath = "eval/eval_1.txt";
	    
	    //Initiate the file to read
	    
		File file = new File(readPath);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String content = "";
		
		
		while(content != null){
			
			//Read a whole line from the readFile
			content = reader.readLine();
			
			//Stop the loop
			if (content.equalsIgnoreCase("quit")){
				System.out.print("The test has finished. Bye!");
				return;
			}
			
			//set an unborrowable book
			/*
			if (content.equalsIgnoreCase("set")){
				Client.libraries.get(0).getBox().getStockedItems().get(4).setBorrowable(false);
				continue;
			}
			*/
			System.out.println(content);
			ClientConsole.operationsInputTreatment(content);
			System.out.println("\nType 'enter' to continue.");
			scanner.nextLine();

		}
		
		//Close readFile
		reader.close();
		
		System.out.println("Test All Correct Cases complete!");
	}
}
