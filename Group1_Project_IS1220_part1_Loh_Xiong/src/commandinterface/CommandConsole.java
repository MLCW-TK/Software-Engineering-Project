package commandinterface;

import ingredients.Ingredient;
import ingredients.IngredientFactory;
import mealsystem.AbstractMeal;
import mealsystem.Meal;
import mealsystem.MealFactory;
import users.*;

public class CommandConsole {
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
	
	public StaffUser insertChef(String firstname, String lastname, String username, String password){
		StaffUser newUser = new StaffUser(firstname, lastname, username, password);
		ClientConsole.re1.addUser(newUser);
		return newUser;
	}
	
	// ClientUser commands
	
	
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
	
}
