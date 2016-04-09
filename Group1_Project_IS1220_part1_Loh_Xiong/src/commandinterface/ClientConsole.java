package commandinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

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
		String command = input.substring("addContactInfo".length()+2, input.length()-1);
		String data[] = command.split(",");
		if (data.length<4){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. addContactInfo <username, password, email, myemail@email.com");
		}

		for (int i = 0; i < 3; i++){
			data[i]=data[i].replaceAll("\\s", "");
		}

		try {
			cl.addContactInfo(data[0], data[1], data[2], data[3]);
		} catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void associateCard(String input){
		String command = input.substring("associateCard".length()+2, input.length()-1);
		String data[] = command.split(",");

		if (data.length < 3){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. associateCard <username, password, cardType>");
		}

		for (int i = 0; i < 3; i++){
			data[i]=data[i].replaceAll("\\s", "");
		}

		try {
			cl.associateCard(data[0], data[1], data[2]);
		} catch (RuntimeException e){
			return;
		}
	}

	//	public static void associateAgreement <username, password, agreement>
	//	public static void associateAgreement <username, password, agreement, modifier>
	public static void associateAgreement(String input){
		String command = input.substring("associateAgreement".length()+2, input.length()-1);
		String data[] = command.split(",");

		if (data.length < 3){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. associateAgreement <username, password,  agreement>");
		}

		if (data.length == 3){
			for (int i = 0; i < 3; i++){
				data[i]=data[i].replaceAll("\\s+", "");
			}
			try {
				cl.associateAgreement(data[0], data[1], data[2]);
			} catch (RuntimeException e){
				return;
			}
		}
		if (data.length == 4){
			for (int i = 0; i < 4; i++){
				data[i]=data[i].replaceAll("\\s+", "");
			}
			try {
				cl.associateAgreement(data[0], data[1], data[2], data[3]);
			} catch (RuntimeException e){
				return;
			}
		}



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
		try {
			currentMeal = cl.saveMeal();
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());                
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

	public static void notifyBirthday(String input){
		String command = input.substring("notifyBirthday".length()+2, input.length()-1);

		if (!command.equals("")){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. notifyBirthday <>");				
		}
		try{
			cl.notifyBirthday();
		}catch(RuntimeErrorException e){
			throw new RuntimeException(e.getMessage());
		}


	}
	// putInSpecialOffer <mealName, price>
	public static void putInSpecialOffer(String input){
		String command = input.substring("putInSpecialOffer ".length()+2, input.length()-1);
		String[] data = command.split(",");


		if (data.length < 2){
			System.out.println("Please enter valid commands");
			throw new RuntimeException("Eg. putInSpecialOffer  <mealName, price>");				
		}

		for (int i = 0; i < 2; i++){
			data[i]=data[i].replaceAll("\\s+", "");
		}

		double price = Double.parseDouble(data[1]);

		try {
			cl.putInSpecialOffer (data[0], price);
		} catch (RuntimeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	// notifyAd <message, mealName, specialPrice>
	public static void notifyAd(String input){
		String command = input.substring("notifyAd ".length()+2, input.length()-1);
		String[] data = command.split(",");

		if (!(data.length==0)){
			if (data.length < 3){
				System.out.println("Please enter valid commands");
				throw new RuntimeException("Eg. notifyAd  <message, mealName, specialPrice>");				
			}

			for (int i = 0; i < 3; i++){
				data[i]=data[i].replaceAll("\\s+", "");
			}

			double specialPrice = Double.parseDouble(data[2]);

			try {
				cl.notifyAd (data[0], data[1], specialPrice);
			} catch (RuntimeException e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	public static void logout(String input){
		try {
			cl.logout();
			System.out.println("You have logged out.");
		} catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}



	public static void operationsInputTreatment(String input){
		int strlength = input.length();
		if (!(strlength == 0)){
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
			// notifyBirthday
			if ((input.length()>="notifyBirthday <".length()+1)&&((input.substring(0,"notifyBirthday <".length())).equals("notifyBirthday <"))&&(lastLetter.equals(">"))){
				notifyBirthday(input);
				return;}

			// putInSpecialOffer <mealName, price>
			if ((input.length()>="putInSpecialOffer <".length()+1)&&((input.substring(0,"putInSpecialOffer <".length())).equals("putInSpecialOffer <"))&&(lastLetter.equals(">"))){
				selectMeal(input);
				return;}
			// notifyAd <message, mealName, specialPrice>
			if ((input.length()>="notifyAd <".length()+1)&&((input.substring(0,"notifyAd <".length())).equals("notifyAd <"))&&(lastLetter.equals(">"))){
				selectMeal(input);
				return;}

			// logout
			if ((input.length()=="logout <".length()+1)&&((input.substring(0,"logout <".length())).equals("logout <"))&&(lastLetter.equals(">"))){
				logout(input);
				return;}
			
			
			//Stop the loop
			if (input.equalsIgnoreCase("EXIT")){
				System.out.print("Thanks for dining with us!");
				globalPhase = false;
				return;}

			// -help commands
			if (input.equalsIgnoreCase("-help")&&loginPhase){
				String loginOperations = new String();
				loginOperations+="Here are the possible login operations:\n"
                                                +"====================================================\n"
						+"registerClient <firstname, lastname, username, password>\n"
                                                +"---: to register.\n"
						+"login <username, password>\n"
                                                +"---: to login with an existing account.\n"
						+"addContactInfo <username, password, contact type, contact detail>\n"
                                                +"---: add a contact information to your account. e.g. addContactInfo <username, password, email, myemail@email.com\n"
						+"associateCard <userName,password,  cardType>\n"
                                                +"---: associate a fidelity card to your account. Type '-cardType' for the possible card types.\n"
						+"associateAgreement <username, agreement>\n"
                                                +"---: configure your agreement options. Type '-agreement' for the possible agreement settings.\n"
						+"-cardType\n"
                                                +"---: show available card types.\n"
						+"-agreement\n"
                                                +"---: show possibile agreements.\n"
						+"====================================================\n";
				System.out.print(loginOperations);

			}
			if (input.equalsIgnoreCase("-HELP")&&loggedinPhase){
				if (currentUser.getUsertype().equals("Staff")){
					String staffOperations = new String();
					staffOperations += "Here are the operations only available to staff users:\n"
                                                        +"===================================================\n"
							+"createMeal <name, price>\n"
                                                        +"---: to create a meal with name and price set.\n"
							+"addIngredient <name, quantity>\n"
                                                        +"---: add an ingredient to the current meal, with quantity set in grams.\n"
							+"currentMeal <>\n"
                                                        +"---: print a summary of the current meal.\n"
							+"saveMeal <>\n"
                                                        +"---: save the curren meal.\n"
							+"listIngredients <name>\n"
                                                        +"---: list the ingredients of a chosen meal\n"
							+"notifyBirthday <>\n"
                                                        +"---: to notify clients whoes birthday is today about the birthday offers.\n"
							+"notifyAd <message, mealName, specialPrice>\n"
                                                        +"---: send ads about a special price of chosen meal.\n"
                                                        +"===================================================\n";

					System.out.print(staffOperations);
				}
				String clientOperations = new String();
				clientOperations += "Here are the operations available to both client and staff users:\n\n"
                                                +"===================================================\n"
						+"addContactInfo <contact>\n"
                                                +"---: follow a procedure to add your contact.\n"
						+"listingredients <meal>\n"
                                                +"---: list the ingredients of a chosen meal.\n"
						+"selectmeal <mealname, quantity>\n"
                                                +"---: select a certain quantity of the meal.\n"
						+"personalizemeal <mealname, ingredientname, quantity>\n"
                                                +"---: add certain amoung the chosen ingredient to the meal. quantity = 0 means remove this ingredient.\n"
						+"saveOrder <>\n"
                                                +"---: save your current order.\n"
                                                +"===================================================\n";
				System.out.print(clientOperations);



			}

			if (input.equalsIgnoreCase("-cardType")){
				String cardTypes = new String();
				cardTypes+="Currently available card types are: \n"
                                                +"===================================================\n"
						+"BasicFidelityCard\n"
						+"PointFidelityCard\n"
						+"LotteryFidelityCard\n"
                                                +"===================================================\n";

				System.out.print(cardTypes);
			}
			if (input.equalsIgnoreCase("-agreement")){
				String agreement = new String();
				agreement+="You may chose to set on the following agreements: \n"
                                                +"===================================================\n"
						+"update\n"
                                                +"---: you will receive update from us.\n"
						+"birthday\n"
                                                +"---: you will receive birthday special offers.\n"
                                                +"===================================================\n";
				System.out.print(agreement);
			}
		

			// -help
			else{
				if (!input.equalsIgnoreCase("-help")){
					System.out.println("Wrong inputs detected. Please type -help to see available commands.");
					System.out.println("");
				}
			}



		}
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
					System.out.println("Type -help for help");
					// Note that a secret method insertChef is included in the system
					// Only "Special Users" would be made known of this feature
					System.out.println("(please type 'exit' to exit)");	
					loginMessagePrinted = true;
				}
				input = sc.nextLine();

				try{
					operationsInputTreatment(input);	
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
