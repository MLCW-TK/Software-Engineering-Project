package commandinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import coresystem.RestaurantSystem;
import ingredients.Ingredient;
import mealsystem.AbstractMeal;
import mealsystem.Meal;
import orders.Order;
import users.ClientUser;

public class ClientConsole{
	static RestaurantSystem re1 = new RestaurantSystem("our restaurant!");
	static ArrayList<Order> orders = new ArrayList<Order>();
	static HashSet<AbstractMeal> meals = new HashSet<AbstractMeal>();;
	static HashSet<String> ingredients = new HashSet<String>();
	static Scanner sc = new Scanner(System.in);
	private static CommandConsole cl = new CommandConsole();
	static ClientUser currentUser;
	static Meal currentMeal;
	
	static boolean globalPhase = true;
	static boolean loginPhase = true;
	static boolean loggedinPhase = false;
	
	// Login Commands
	public static void registerUser(String input) throws RuntimeException{
			String command = input.substring(16,input.length()-1);
			String[] data = command.split(",");
			
			if (data.length < 4){
				System.out.println("Please enter valid commands");
				throw new RuntimeException("Eg. registerClient <firstname,lastname,username,password>");				
			}
			
			for (int i = 0; i < 4; i++){
				if (data[i].substring(0, 1).equals(" ")){
					data[i] = data[i].substring(1, data[i].length());
				}
			}
			
			cl.registerClient(data[0], data[1], data[2], data[3]);
			System.out.println("Please login from the main menu");
			System.out.println("");
	}

	public static void insertChef(String input) throws RuntimeException{
		String command = input.substring(12,input.length()-1);
		String[] data = command.split(",");
		
		if (data.length < 4){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. insertChef <firstname,lastname,username,password>");				
		}
		
		for (int i = 0; i < 4; i++){
			if (data[i].substring(0, 1).equals(" ")){
				data[i] = data[i].substring(1, data[i].length());
			}
		}
		
		cl.insertChef(data[0], data[1], data[2], data[3]);
		System.out.println("Please login from the main menu");
		System.out.println("");
}
	
	public static void loginUser(String input){
			String command = input.substring(7,input.length()-1);
			String[] data = command.split(",");
			
			if (data.length < 2){
				System.out.println("Please enter valid commands");
				throw new RuntimeException("Eg. login <username,password>");
			}
			
			for (int i = 0; i < 2; i++){
				if (data[i].substring(0, 1).equals(" ")){
					data[i] = data[i].substring(1, data[i].length());
				}
			}
			
			try{
				cl.login(data[0], data[1]);
			} catch (RuntimeException e){
				throw new RuntimeException("Invalid username/password. Please try again");
			}
			
			loginPhase = false;
			loggedinPhase = true;
			currentUser = cl.login(data[0], data[1]);
	}
	
	public static void loginInputTreatment(String input){
		int strlength = input.length();
		String lastLetter = input.substring(strlength-1, strlength);
		
		
		if ((input.length()>=17)&&((input.substring(0,16)).equals("registerClient <"))&&(lastLetter.equals(">"))){
			registerUser(input);
			return;}
		
		if ((input.length()>=8)&&((input.substring(0,7).equals("login <"))&&(lastLetter.equals(">")))){
			loginUser(input);
			return;}
		
		if ((input.length()>=13)&&((input.substring(0,12).equals("insertChef <"))&&(lastLetter.equals(">")))){
			insertChef(input);
			return;}
		
		System.out.println("Wrong inputs detected. Please try again.");
		System.out.println("");
	}

	// ClientUser commands
	public static void addIngredient(String input){
		String command = input.substring(15, input.length()-1);
		String[] data = command.split(",");

		
		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. addIngredient <name, quantity>");				
		}
		
		for (int i = 0; i < 2; i++){
			if (data[i].substring(0, 1).equals(" ")){
				data[i] = data[i].substring(1, data[i].length());
			}
		}
		double quantity = Double.parseDouble(data[1]);
		
		try {
			cl.addIngredient(data[0], quantity);
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
		
	}

	public static void createMeal(String input){
		String command = input.substring(12,input.length()-1);
		String[] data = command.split(",");

		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. createMeal <name, price>");				
		}
		
		for (int i = 0; i < 2; i++){
			if (data[i].substring(0, 1).equals(" ")){
				data[i] = data[i].substring(1, data[i].length());
			}
		}
		double price = Double.parseDouble(data[1]);
		
		try {
			cl.createMeal(data[0], price);
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static void currentMeal(String input){
		try {
			cl.currentMeal();
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static void operationsInputTreatment(String input){
		int strlength = input.length();
		String lastLetter = input.substring(strlength-1, strlength);

		
		if ((input.length()>=13)&&((input.substring(0,12)).equals("createMeal <"))&&(lastLetter.equals(">"))){
			registerUser(input);
			return;}
		
		if ((input.length()>=16)&&((input.substring(0,15)).equals("addIngredient <"))&&(lastLetter.equals(">"))){
			registerUser(input);
			return;}

		if ((input.length()==14)&&((input.substring(0,13)).equals("currentMeal <"))&&(lastLetter.equals(">"))){
			currentMeal(input);
			return;}
		
	}
	
	// Main program
	public static void main(String[] args) throws Exception {
		String input = new String();
		while (globalPhase){
			while (loginPhase) {
				System.out.println("Welcome to our restaurant!");
				System.out.println("To register, please enter: registerClient <firstname, lastname, username, password>");
				System.out.println("To login, please enter: login <username, password>");
				// Note that a secret method insertChef is included in the system
				// Only "Special Users" would be made known of this feature
				System.out.println("(please type 'exit' to exit)");
				input = sc.nextLine();
							
				//Stop the loop
				if (input.equalsIgnoreCase("EXIT")){
					System.out.print("Thanks for dining with us!");
					globalPhase = false;
					return;}
				
				if (input.equals("")){
					System.out.println("Wrong inputs detected. Please try again.");
					System.out.println("");
					continue;
				}
				
				try{
					loginInputTreatment(input);	
				} catch (RuntimeException e){
					System.out.println(e.getMessage());
					System.out.println("");
				}
				
			} while (loggedinPhase){
				System.out.println("Welcome, " + currentUser.getFirstname() + " " + 
					currentUser.getLastname() + "!" + " " + "(" + currentUser.getUsertype() + ")");
				System.out.println("(You may type 'logout' to log out at any time)");
				input = sc.nextLine();
				if (input.equalsIgnoreCase("LOGOUT")){
					System.out.println("Thank you for your time today!");
					System.out.println("You have been logged out");
					System.out.println("");
					loginPhase = true;
					loggedinPhase = false;
					continue;
				}
				try{
					operationsInputTreatment(input);
				} catch (RuntimeException e){
					System.out.println(e.getMessage());
					System.out.println("");
				}
				
			}
		}
	}
}
