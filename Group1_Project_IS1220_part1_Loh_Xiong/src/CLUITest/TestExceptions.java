package CLUITest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import commandinterface.ClientConsole;

public class TestExceptions {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		//readPaths for files to read

		String readPath = "eval/eval_2.txt";

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

		System.out.println("Test Exceptions complete!");
	}
}


