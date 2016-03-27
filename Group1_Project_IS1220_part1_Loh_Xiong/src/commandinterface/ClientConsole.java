package commandinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import cardfidelitysystem.FidelityCardFactory;

import coresystem.RestaurantSystem;

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
	static protected FidelityCardFactory fcf = new FidelityCardFactory();


	static Meal editedMeal = null;
	
	
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
			System.out.println("Congratulations! Account successfully created");
			System.out.println("You may now use 'addContactInfo <contactInfo>' to add new contact information");
			System.out.println("You may now use 'associateCard <username, cardtype> to associate your fidelity card");
			System.out.println("You may also login now with 'login <username, password>'");
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
				currentUser = cl.login(data[0], data[1]);
			} catch (RuntimeException e){
				throw new RuntimeException("Invalid username/password. Please try again");
			}
			
			loginPhase = false;
			loggedinPhase = true;
	}
	
	public static void addContactInfo(String input){
		String data = input.substring("addContactInfo".length()+2, input.length()-1);
		try {
			cl.addContactInfo(data);
		} catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static void associateCard(String input){
		String command = input.substring("associateCard".length()+2, input.length()-1);
		String data[] = command.split(",");
		
		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. associateCard <username,cardType>");
		}
		
		for (int i = 0; i < 2; i++){
			data[i]=data[i].replaceAll("\\s", "");
		}
		
		System.out.println("For verification purposes, please enter your password");
		String password = sc.nextLine();
		try {
			re1.validateUser(data[0], password);
		} catch (RuntimeException e){
			System.out.println("User does not exist. Please try the command again");
			return;
		}
		
		try {
			cl.associateCard(data[0], data[1]);
		} catch (RuntimeException e){
			return;
		}
	}
	
//	public static void associateAgreement <username, agreement>
	public static void associateAgreement(String input){
		String command = input.substring("associateAgreement".length()+2, input.length()-1);
		String data[] = command.split(",");
		
		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. associateAgreement <username, agreement>");
		}
		
		for (int i = 0; i < 2; i++){
			data[i]=data[i].replaceAll("\\s+", "");
		}
		
		System.out.println("For verification purposes, please enter your password");
		String password = sc.nextLine();
		try {
			re1.validateUser(data[0], password);
		} catch (RuntimeException e){
			System.out.println("User does not exist. Please try the command again");
			return;
		}
		
		try {
			cl.associateAgreement(data[0], data[1]);
		} catch (RuntimeException e){
			return;
		}
	}
		
	public static void loginInputTreatment(String input){
		int strlength = input.length();
		String lastLetter = input.substring(strlength-1, strlength);
		
		// register client
		if ((input.length()>=17)&&((input.substring(0,16)).equals("registerClient <"))&&(lastLetter.equals(">"))){
			registerUser(input);
			return;}
		
		// login user
		if ((input.length()>=8)&&((input.substring(0,7).equals("login <"))&&(lastLetter.equals(">")))){
			loginUser(input);
			return;}
		
		// insert chef
		if ((input.length()>=13)&&((input.substring(0,12).equals("insertChef <"))&&(lastLetter.equals(">")))){
			insertChef(input);
			return;}
		
		// add contact info
		if ((input.length()>="addContactInfo <".length()+1)&&((input.substring(0,"addContactInfo <".length())).equals("addContactInfo <"))&&(lastLetter.equals(">"))){
			addContactInfo(input);
			return;}
		
		// associate card
		if ((input.length()>="associateCard <".length()+1)&&((input.substring(0,"associateCard <".length())).equals("associateCard <"))&&(lastLetter.equals(">"))){
			associateCard(input);
			return;}
		
		if ((input.length()>="associateAgreement <".length()+1)&&((input.substring(0,"associateAgreement <".length())).equals("associateAgreement <"))&&(lastLetter.equals(">"))){
			associateAgreement(input);
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
	
	public static void saveMeal(String input){
		System.out.println("Would you like to save your meal? (yes/no)");
		String input1 = sc.nextLine();
		if (input1.equalsIgnoreCase("YES")){
			try {
				currentMeal = cl.saveMeal();
			} catch (RuntimeException e){
				throw new RuntimeException(e.getMessage());
			}			
		} else{
			cl.currentMeal();
		}
	}
	
	public static void listIngredients(String input){
		String data = input.substring("listIngredients".length()+2,input.length()-1);
		if (data.contains(",")){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. listingredients <mealName>");				
		}
		
		try {
			cl.listIngredients(data);
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static void selectMeal(String input){
		String command = input.substring("selectMeal".length()+2, input.length()-1);
		String[] data = command.split(",");

		
		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. selectMeal <name, quantity>");				
		}
		
		for (int i = 0; i < 2; i++){
			data[i]=data[i].replaceAll("\\s+", "");
		}
		
		int quantity = Integer.parseInt(data[1]);
		
		try {
			cl.selectMeal(data[0], quantity);
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	public static void logout(String input){
		try {
			cl.logout();
		} catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	
	public static void operationsInputTreatment(String input){
		int strlength = input.length();
		String lastLetter = input.substring(strlength-1, strlength);

		// create meal
		if ((input.length()>="createMeal <".length()+1)&&((input.substring(0,"createMeal <".length())).equals("createMeal <"))&&(lastLetter.equals(">"))){
			createMeal(input);
			return;}
		// add ingredient
		if ((input.length()>="addIngredient <".length()+1)&&((input.substring(0,"addIngredient <".length())).equals("addIngredient <"))&&(lastLetter.equals(">"))){
			addIngredient(input);
			return;}
		// checks current meal
		if ((input.length()=="currentMeal <".length()+1)&&((input.substring(0,"currentMeal <".length())).equals("currentMeal <"))&&(lastLetter.equals(">"))){
			currentMeal(input);
			return;}
		// saves meal
		if ((input.length()=="saveMeal <".length()+1)&&((input.substring(0,"saveMeal <".length())).equals("saveMeal <"))&&(lastLetter.equals(">"))){
			saveMeal(input);
			return;}
		// list ingredients
		if ((input.length()>="listIngredients <".length()+1)&&((input.substring(0,"listIngredients <".length())).equals("listIngredients <"))&&(lastLetter.equals(">"))){
			listIngredients(input);
			return;}
		// selectMeal
		if ((input.length()>="selectMeal <".length()+1)&&((input.substring(0,"selectMeal <".length())).equals("selectMeal <"))&&(lastLetter.equals(">"))){
			selectMeal(input);
			return;}
		// logout
		if ((input.length()=="logout <".length()+1)&&((input.substring(0,"logout <".length())).equals("logout <"))&&(lastLetter.equals(">"))){
			logout(input);
			return;}
		
		

	}
	
	// Main program
	public static void main(String[] args) throws Exception {
		String input = new String();
		while (globalPhase){
			boolean loginMessagePrinted = false;
			boolean loggedinMessagePrinted = false;
			while (loginPhase) {
				if (!loginMessagePrinted){
					System.out.println("Welcome to our restaurant!");
					System.out.println("To register, please enter: registerClient <firstname, lastname, username, password>");
					System.out.println("To login, please enter: login <username, password>");
					// Note that a secret method insertChef is included in the system
					// Only "Special Users" would be made known of this feature
					System.out.println("(please type 'exit' to exit)");	
					loginMessagePrinted = true;
				}
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
				
				// -help commands
				if (input.equalsIgnoreCase("-help")){
					String loginOperations = new String();
					loginOperations+="Here are the possible login operations:\n"
							+"registerClient <firstname, lastname, username, password>			:	to register.\n"
							+"login <username, password>										:	to login with an existing account.\n"
							+"addContactInfo <contactInfo>										:	add a contact information to your account.\n"
							+"associateCard <userName, cardType>								:	associate a fidelity card to your account. Type '-cardType' for the possible card types.\n"
							+"associateAgreement <username, agreement>							:	configure your agreement options. Type '-agreement' for the possible agreement settings.\n";
					System.out.print(loginOperations);
				
				}
				if (input.equalsIgnoreCase("-cardType")){
					String cardTypes = new String();
					cardTypes+="Currently available card types are: \n"
							+"BasicFidelityCard\n"
							+"PointFidelityCard\n"
							+"LotteryFidelityCard\n";
					System.out.print(cardTypes);
				}
				if (input.equalsIgnoreCase("-agreement")){
					String agreement = new String();
					agreement+="You may chose to set on the following agreements: \n"
							+"update	:	you will receive update from us.\n"
							+"birthday	:	you will receive birthday special offers.\n";
					System.out.print(agreement);
				}
				
				try{
					loginInputTreatment(input);	
				} catch (RuntimeException e){
					System.out.println(e.getMessage());
					System.out.println("");
				}
				
			}
			while (loggedinPhase){
				if (!loggedinMessagePrinted){
					System.out.println("Welcome, " + currentUser.getFirstname() + " " + 
							currentUser.getLastname() + "!" + " " + "(" + currentUser.getUsertype() + ")");
					System.out.println("You may type -help to see the available commands.");
					
					// Logout is now a function
//					System.out.println("(You may type 'logout' to log out at any time)");
					loggedinMessagePrinted = true;
				}
				input = sc.nextLine();
				
				//Logout is now a function
//				if (input.equalsIgnoreCase("LOGOUT")){
//					System.out.println("Thank you for your time today!");
//					System.out.println("You have been logged out");
//					System.out.println("");
//					loginPhase = true;
//					loggedinPhase = false;
//					continue;
//				}
				if (input.equalsIgnoreCase("-help")){
                                    if (currentUser.getUsertype().equals("Staff")){
                                        String staffOperations = new String();
                                        staffOperations += "Here are the operations only available to staff users:\n"
                                            +"createMeal <name, price>			:	to create a meal with name and price set.\n"
                                            +"addIngredient <name, quantity>	:	add an ingredient to the current meal, with quantity set in grams.\n"
                                            +"currentMeal <>					:	print a summary of the current meal.\n"
                                            +"saveMeal <>						:	save the curren meal.\n"
                                            +"listIngredients <name>			:	list the ingredients of a chosen meal\n";
                                        System.out.print(staffOperations);
                                    }
					
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