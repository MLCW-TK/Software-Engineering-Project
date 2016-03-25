package commandinterface;

import java.util.Scanner;

import users.StaffUser;

public class test1 {
	// Waiter Bob Red
	static StaffUser waiter = new StaffUser("Bob", "Red", "bobred", "123456");
	static CommandConsole cc = new CommandConsole();
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		System.out.println("Satisfying Prerequisites....");
		System.out.println("----------");
		ClientConsole.re1.addUser(waiter);
		System.out.println("Waiter 'Bob Red' added to system");
		System.out.println("Please press 'ENTER' to proceed");
		sc.nextLine();
		
		System.out.println("Test part1 commencing...");
		System.out.println("----------");
		// Logs in bobred;
		System.out.println("*** Tests login ***");
		cc.login("bobred", "123456");
		System.out.println("");
		
		System.out.println("*** Tests createMeal ***");
		cc.createMeal("Raclette", 10.50);
		System.out.println("");
		
		System.out.println("*** Tests addIngredient ***");
		cc.addIngredient("Cheese", 90);
		cc.addIngredient("Ham", 40);
		cc.addIngredient("Potatoes", 50);
		System.out.println("");
		
		System.out.println("*** Tests currentMeal ***");
		cc.currentMeal();
		System.out.println("");
		
		System.out.println("*** Tests saveMeal ***");
		cc.saveMeal();
		System.out.println("");
	}

	
}
