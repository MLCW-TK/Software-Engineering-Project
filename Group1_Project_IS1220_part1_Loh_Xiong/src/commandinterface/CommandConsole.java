package commandinterface;

import java.util.Scanner;

import cardfidelitysystem.FidelityCard;
import ingredients.Ingredient;
import ingredients.IngredientFactory;
import mealsystem.AbstractMeal;
import mealsystem.Meal;
import mealsystem.MealFactory;
import users.*;

public class CommandConsole {
	private static Scanner sc = new Scanner(System.in);
	// Login commands
	public ClientUser registerClient(String firstname, String lastname, String username, String password){
		ClientUser newUser = new ClientUser(firstname, lastname, username, password);
		ClientConsole.re1.addUser(newUser);
		System.out.println("Client '" + username +  "' successfully created!");
		return newUser;
	}
	
	public ClientUser login(String username, String password){
		for (ClientUser user : ClientConsole.re1.getUserList()){
			if (user.getUsername().equals(username) && user.getPassword().equals(password)){
				System.out.println("'" + user.getUsername() + "' has logged in");
				ClientConsole.currentUser = user;
				return user;
			}
		}
		
		throw new RuntimeException("No user found!");
	}
	
	public FidelityCard associateCard(String username, String cardType){
		ClientUser user = ClientConsole.re1.getUserFromUserName(username);
		try {
			ClientConsole.fcf.createFidelityCard(cardType, user);
		} catch (RuntimeException e){
			System.out.println(cardType + " does not exist");
			System.out.println(username + " is now holding " + user.getFidelityCard().getCardName());
			throw new RuntimeException();
		}
		FidelityCard fc = ClientConsole.fcf.createFidelityCard(cardType, user);
		user.setFidelityCard(fc);
		System.out.println(username + " is now holding " + user.getFidelityCard().getCardName());
		return fc;
	}
//	public static void associateAgreement <username, agreement>
	public void associateAgreement(String username, String agreement) {
		ClientUser user = ClientConsole.re1.getUserFromUserName(username);
		if (agreement.equalsIgnoreCase("update")){
			try {
				user.registerUpdates();
			} catch (RuntimeException e){
				throw new RuntimeException();
			}
		}
		if (agreement.equalsIgnoreCase("birthday")){
			try {
				user.registerBirthday();
			} catch (RuntimeException e){
				throw new RuntimeException();
			}
		}
			
	}
	
	public StaffUser insertChef(String firstname, String lastname, String username, String password){
		StaffUser newUser = new StaffUser(firstname, lastname, username, password);
		ClientConsole.re1.addUser(newUser);
		System.out.println("Staff '" + username + "' sucessfully created!");
		return newUser;
	}
	
	// ClientUser commands
	public void logout(){
		ClientConsole.loginPhase = true;
		ClientConsole.loggedinPhase = false;
		String username = ClientConsole.currentUser.getUsername();
		ClientConsole.currentUser = null;
		System.out.println("'" + username + "' has logged out");
		return;
	}
	
	public void addContactInfo(String input){
		System.out.println("In order to use this function, you would be required to log in temporarily");
		System.out.println("Please enter your username");
		String username = sc.nextLine();
		System.out.println("Please enter your password");
		String password = sc.nextLine();
		try {
			ClientConsole.re1.validateUser(username, password);
		} catch (RuntimeException e){
			System.out.println("Invalid User/Password");
			addContactInfo(input);
		}
		
		ClientUser loginuser = (ClientUser) ClientConsole.re1.validateUser(username, password);
		
		Boolean addOption = true;
		do{
		System.out.println("Please select an option below (only numbers allowed): ");
		System.out.println("1. Add an email address");
		System.out.println("2. Add a custom contact detail");
		System.out.println("3. Exit");
		String input1 = sc.nextLine();
			switch(input1){
		case "1":
			loginuser.setEmail(input);
			System.out.println("Email address successfuly added!");
			addOption = false;
			break;
		case "2":
			System.out.println("Please type a contact field category");
			String contactInfo = sc.nextLine();
			loginuser.getContactHash().put(contactInfo, input);
			addOption = false;
			break;
		case "3":
			addOption = false;
			break;
		default:
			System.out.println("Wrong input selected. Please try again");
			continue;
			}
		} while (addOption);
		
		
	}
	
	// StaffUser commands
	public Meal createMeal(String name, double price){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		} else {
			MealFactory mealfac = new MealFactory();
			Meal tempMeal = (Meal) mealfac.createMeal(name, price);
			ClientConsole.meals.add(tempMeal);
			System.out.println("Meal '" + tempMeal.getName() + "' is added to the system");
			ClientConsole.currentMeal = tempMeal;
			return tempMeal;
		}	
	}
	
	public Ingredient addIngredient(String name, double quantity){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal.equals(null))){
			throw new RuntimeException("currentMeal is empty!");
		}
		IngredientFactory ingrfac = new IngredientFactory();
		Ingredient ingr = ingrfac.createIngredient(name, quantity);
		ClientConsole.currentMeal.insertInitialIngredients(ingr);
		ClientConsole.ingredients.add(ingr.getName());
		System.out.println(ingr.getOriginalQuantity() + "g '" + ingr.getName() + "' added to '" + ClientConsole.currentMeal.getName() + "'");
		return ingr;
	}
	
	public Meal currentMeal(){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal.equals(null))){
			throw new RuntimeException("currentMeal is empty!");
		}
		
		System.out.println("Summary of current meal:");
		System.out.println("Meal: " + ClientConsole.currentMeal.getName() + ", Price: $" + ClientConsole.currentMeal.getStringPrice());
		System.out.println("Ingredients: ");
		for (Ingredient obj : ClientConsole.currentMeal.getIngredients()){
			System.out.println(obj.getOriginalQuantity() + "g " + obj.getName());
		}
		
		return ClientConsole.currentMeal;
	}
	
	public Meal saveMeal(){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal.equals(null))){
			throw new RuntimeException("currentMeal is empty!");
		}
		
//		System.out.println("Would you like to confirm your order? (yes/no)");
//		String input = sc.nextLine();
//		if (input.equalsIgnoreCase("YES")){
		ClientConsole.currentMeal.setFinalDefaultIngredients();
		ClientConsole.meals.add(ClientConsole.currentMeal);
		ClientConsole.currentMeal = null;
		System.out.println("Meal saved!");
		return ClientConsole.currentMeal;
//		} else {
//			return ClientConsole.currentMeal;
//		}
	}
	// ClientUser commands
	public void listIngredients(String meal){
		boolean bool = false;
		for (AbstractMeal aMeal : ClientConsole.meals){
			if (aMeal.getName().equalsIgnoreCase(meal)){
				aMeal.printIngredients();
				bool = true;
			}
		}
		if (!bool){
			System.out.println("This meal does not exist yet. You may want to check your spelling or create this meal.");
			System.out.println("To create a meal: createmeal <name, price>");
		}		
	}

	//selectMeal<mealName, quantity>
	public void selectMeal(String mealName, int quantity){
            
		Meal selected_meal = null;
		for (AbstractMeal obj : ClientConsole.meals){
			if (obj.getName().equals(mealName)){
				selected_meal = (Meal) obj;
			} 
		}
		if (selected_meal.equals(null)){
			throw new RuntimeException(mealName + " not found!");
		}
		
		ClientConsole.currentUser.getCurrentOrder().selectMeal(selected_meal, quantity);	
		for (int i = 0; i < quantity; i++){
			System.out.println(mealName + " selected");	
		}
	}
	
	public Meal personalizeMeal(String mealName, String IngredientName, int quantity){
		Meal selected_meal = null;
		Ingredient selected_ingredient = null;
		for (AbstractMeal obj : ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders()){
			if (obj.getName().equals(mealName) && !obj.getPersonalizedBool()){
				selected_meal = (Meal) obj;
			} 
		}
		if (selected_meal.equals(null)){
			throw new RuntimeException(mealName + " not found! Please select a meal");
		}

		for (Ingredient obj : selected_meal.getIngredients()){
			if (obj.getName().equals(IngredientName)){
				selected_ingredient = obj.createnewinstance();
			}
		}
		
		if (selected_ingredient.equals(null)){
			selected_ingredient = new Ingredient(IngredientName, quantity);
		}
		
		Meal personalizedmeal = (Meal) ClientConsole.currentUser.getCurrentOrder().personalizeMeal(selected_meal, selected_ingredient, quantity);
		ClientConsole.currentUser.getCurrentOrder().addPersonalizedMeal(personalizedmeal);
//		int index = ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().indexOf(selected_meal);
		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().remove(selected_meal);
//		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().add(index, personalizedmeal);
//			
		System.out.println(mealName + " personalized");
		return personalizedmeal;
	}
	
	public void saveOrder(){
		ClientConsole.currentUser.getCurrentOrder().saveOrder();
		ClientConsole.currentUser.getOrders().add(ClientConsole.currentUser.getCurrentOrder());
		System.out.println(ClientConsole.currentUser.getCurrentOrder().Summary());
		
	}
}