package commandinterface;

import java.util.Scanner;

import cardfidelitysystem.FidelityCard;
import ingredients.Ingredient;
import ingredients.IngredientFactory;
import mealsystem.AbstractMeal;
import mealsystem.Meal;
import mealsystem.MealFactory;
import orders.Order;
import users.*;

/**
 * all recognized and processed String from ClientConsole are sent here to execute
 * @author Xiong
 *
 */
public class CommandConsole {
	private static Scanner sc = new Scanner(System.in);
	// Login commands
	/**
	 * register a ClientUser
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @return
	 */
	public ClientUser registerClient(String firstname, String lastname, String username, String password){
		ClientUser newUser = new ClientUser(firstname, lastname, username, password);
		ClientConsole.re1.addUser(newUser);
		System.out.println("Client '" + username +  "' successfully created!");
		return newUser;
	}

	/**
	 * login
	 * @param username
	 * @param password
	 * @return
	 */
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
	
	/**
	 * Create a fidelity card of chosen type and associate it to the user.
	 * @param username
	 * @param password
	 * @param cardType
	 * @return
	 */
	public void associateCard(String username, String password, String cardType){
		try{
		ClientUser user = ClientConsole.re1.validateUser(username, password);
		FidelityCard fc = ClientConsole.fcf.createFidelityCard(cardType, user);
		user.setFidelityCard(fc);
		System.out.println(username + " is now holding " + user.getFidelityCard().getCardName());
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	
	
	//	public static void associateAgreement <username, password, agreement>
	/**
	 * associate an agreement, without modifier
	 * @param username
	 * @param password
	 * @param agreement
	 */
	public void associateAgreement(String username, String password, String agreement) {
		ClientUser user = ClientConsole.re1.validateUser(username, password);
		if (agreement.equalsIgnoreCase("update")){
			try {
				user.registerUpdates();
			} catch (RuntimeException e){
				throw new RuntimeException();
			}
			if (user.getCanReceiveSpecialOffers()){System.out.println("Success!");}
		}
		if (agreement.equalsIgnoreCase("birthday")){
			System.out.println("Please add specification of your birthday");
		}

	}

	/**
	 * associate an agreement, with modifier
	 * @param username
	 * @param password
	 * @param agreement
	 * @param modifier
	 */
	public void associateAgreement(String username, String password, String agreement, String modifier){
		ClientUser user = ClientConsole.re1.validateUser(username, password);
		if (agreement.equalsIgnoreCase("update")){
			try {
				user.registerUpdates(modifier);
			}catch(RuntimeException e){
				throw new RuntimeException();
			}
		}
		if (agreement.equalsIgnoreCase("birthday")){
			try {
				user.registerBirthday(modifier);
			}catch(RuntimeException e){throw new RuntimeException();}
		}
	}

	/**
	 * secret command to create a StaffUser
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @return
	 */
	public StaffUser insertChef(String firstname, String lastname, String username, String password){
		StaffUser newUser = new StaffUser(firstname, lastname, username, password);
		ClientConsole.re1.addUser(newUser);
		System.out.println("Staff '" + username + "' sucessfully created!");
		return newUser;
	}

	// ClientUser commands
	/**
	 * logout
	 */
	public void logout(){
		ClientConsole.loginPhase = true;
		ClientConsole.loggedinPhase = false;
		String username = ClientConsole.currentUser.getUsername();
		ClientConsole.currentUser = null;
		System.out.println("'" + username + "' has logged out");
		return;
	}

	/**
	 * add a chosen type of contact information to the user
	 * @param username
	 * @param password
	 * @param type
	 * @param detail
	 */
	public void addContactInfo(String username, String password, String type, String detail){
		try {
			ClientUser loginuser = (ClientUser) ClientConsole.re1.validateUser(username, password);
			if (type.equalsIgnoreCase("email")){
				loginuser.setEmail(detail);
			}
			else{
				loginuser.addContact(type, detail);
			}
		} catch (RuntimeException e){
			System.out.println("Invalid User/Password");
		}
	}

	// StaffUser commands
	/**
	 * create a meal with set price
	 * @param name
	 * @param price
	 * @return
	 */
	public Meal createMeal(String name, double price){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		} else {
			MealFactory mealfac = new MealFactory();
			Meal tempMeal = (Meal) mealfac.createMeal(name, price);
			ClientConsole.re1.getMeal_list().add(tempMeal);
			System.out.println("Meal '" + tempMeal.getName() + "' is added to the system");
			ClientConsole.currentMeal = tempMeal;
			return tempMeal;
		}
	}

	
	/**
	 * add certain quantity of an ingredient to the current meal
	 * @param name
	 * @param quantity
	 * @return
	 */
	public Ingredient addIngredient(String name, double quantity){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			System.out.println("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal == null)){
			throw new RuntimeException("currentMeal is empty!");
		}
		IngredientFactory ingrfac = new IngredientFactory();
		Ingredient ingr = ingrfac.createIngredient(name, quantity);
		ClientConsole.currentMeal.insertInitialIngredients(ingr);
		ClientConsole.ingredients.add(ingr.getName());
		System.out.println(ingr.getOriginalQuantity() + "g '" + ingr.getName() + "' added to '" + ClientConsole.currentMeal.getName() + "'");
		return ingr;
	}

	/**
	 * show the current meal
	 * @return
	 */
	public Meal currentMeal(){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal == null)){
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

	/**
	 * save the current meal
	 * @return
	 */
	public Meal saveMeal(){
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		if ((ClientConsole.currentMeal ==null)){
			throw new RuntimeException("currentMeal is empty!");
		}

		//		System.out.println("Would you like to confirm your order? (yes/no)");
		//		String input = sc.nextLine();
		//		if (input.equalsIgnoreCase("YES")){
		ClientConsole.currentMeal.setFinalDefaultIngredients();
		ClientConsole.re1.getMeal_list().add(ClientConsole.currentMeal.createNewInstance());
		ClientConsole.currentMeal = null;
		System.out.println("Meal saved!");
		return ClientConsole.currentMeal;
		//		} else {
		//			return ClientConsole.currentMeal;
		//		}
	}

	/**
	 * put the meal to special offer, set the price
	 * @param mealName
	 * @param price
	 */
	public void putInSpecialOffer(String mealName, double price) {
		boolean b = false;
		if (!(ClientConsole.currentUser instanceof StaffUser)){
			throw new RuntimeException("Insufficient previledges to access this command");
		}
		for (AbstractMeal meal : ClientConsole.re1.getMeal_list()){
			if(meal.getName().equals(mealName)){
				try{
					meal.setSpecialOfferToggle(true);
					meal.setSpecialPrice(price);
					b = true;
				}catch(Exception e){System.out.println(e.getMessage());}
			}
		}
		if (!b){
			System.out.println(mealName+" does not exist!\n");
		}


	}

	// clientUser commands
	/**
	 * list the ingredients of a meal
	 * @param meal
	 */
	public void listIngredients(String meal){
		boolean bool = false;
		for (AbstractMeal ameal : ClientConsole.re1.getMeal_list()){
			if (ameal.getName().equalsIgnoreCase(meal)){
				ameal.printIngredients();
				bool = true;
			}
		}
		if (!bool){
			System.out.println("this meal does not exist yet. you may want to check your spelling or create this meal.");
			System.out.println("to create a meal: createmeal <name, price>");
		}
	}

	//selectMeal<mealname, quantity>
	/**
	 * select certain quantity of a meal
	 * @param mealname
	 * @param quantity
	 */
	public void selectMeal(String mealname, int quantity){

		Meal selected_meal = null;
		for (AbstractMeal obj : ClientConsole.re1.getMeal_list()){
			if (obj.getName().equals(mealname)){
				selected_meal = (Meal) obj;
			}
		}
		if (selected_meal == null){
			throw new RuntimeException(mealname + " not found!");
		}

		ClientConsole.currentUser.getCurrentOrder().selectMeal(selected_meal, quantity);
		for (int i = 0; i < quantity; i++){
			System.out.println(mealname + " selected");
		}
	}

	/**
	 * add a certain quantity of an ingredient to the meal
	 * quantity = 0 means to remove the ingredient
	 * @param mealName
	 * @param IngredientName
	 * @param quantity
	 * @return
	 */
	public Meal personalizeMeal(String mealName, String IngredientName, int quantity){
		Meal selected_meal = null;
		Ingredient selected_ingredient = null;
		for (AbstractMeal obj : ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders()){
			if (obj.getName().equals(mealName) && !obj.getPersonalizedBool()){
				selected_meal = (Meal) obj;
			}
		}
		if (selected_meal== null){
			throw new RuntimeException(mealName + " not found! Please select a meal");
		}

		for (Ingredient obj : selected_meal.getIngredients()){
			if (obj.getName().equals(IngredientName)){
				selected_ingredient = obj.createnewinstance();
			}
		}

		if (selected_ingredient == null){
			selected_ingredient = new Ingredient(IngredientName, quantity);
		}

		Meal personalizedmeal = (Meal) ClientConsole.currentUser.getCurrentOrder().personalizeMeal(selected_meal, selected_ingredient, quantity);
		ClientConsole.currentUser.getCurrentOrder().addPersonalizedMeal(personalizedmeal);

		//		int index = ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().indexOf(selected_meal);
		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().remove(selected_meal);
		//		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().add(index, personalizedmeal);
		//

		//		int index = ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().indexOf(selected_meal);
		//		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().remove(selected_meal);
		//		ClientConsole.currentUser.getCurrentOrder().getUnprocessedOrders().add(index, personalizedmeal);

		System.out.println(mealName + " personalized");
		return personalizedmeal;
	}

	/**
	 * save the order
	 */
	public void saveOrder(){
		ClientConsole.currentUser.getCurrentOrder().saveOrder();
		ClientConsole.currentUser.getOrders().add(ClientConsole.currentUser.getCurrentOrder());
		System.out.println(ClientConsole.currentUser.getCurrentOrder().Summary());
		ClientConsole.currentUser.setCurrentOrder(new Order(ClientConsole.currentUser));
		
	}
	
	/**
	 * notify all subscribed users who are on their birthday of the special birthday offer
	 */
	public void notifyBirthday(){
		ClientConsole.re1.refresh();
		ClientConsole.re1.notifyBirthday();
	}

	// Here I believe that this function will change the specialPrice if it is identical to the already set specialPrice.
	/**
	 * notify all subscribed users of a special offer of a meal, and sent attach a message
	 * @param message
	 * @param mealName
	 * @param specialPrice
	 */
	public void notifyAd(String message, String mealName, double specialPrice) {
		putInSpecialOffer(mealName, specialPrice);
		ClientConsole.re1.refresh();
		ClientConsole.re1.notifySubscriber(message, mealName);
	}

	/**
	 * list all available meal of the restaurant
	 */
	public void listMeals() {
		System.out.println(ClientConsole.re1.printMeal_list());
	}
	
	/**
	 * show the meals according to a certain ordering criteria
	 * @param orderingCriteria
	 */
	public void showMeal(String orderingCriteria){
		if (orderingCriteria.equalsIgnoreCase("JustOnSale")){
			System.out.println(Order.showMealJustOnSale());
			return;
		}
		if (orderingCriteria.equalsIgnoreCase("AsItIs")){
			System.out.println(Order.showMealsAsItIs());
			return;
		}
		if (orderingCriteria.equalsIgnoreCase("AsMostModified")){
			System.out.println(Order.showMealAsMostModified());
			return;
		}
		else{System.out.println("Criteria not found.");}
		
	}
	
}
