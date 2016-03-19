package CoreSystem;

import MealSystem.*;
import Users.*;

public class RunSystem {
	public static void main(String[] args){
		/**
		 * Loads our system
		 */
		LoadSystem le1 = new LoadSystem("our restaurant");
		
		/**
		 * Loads all staff members into our system
		 */
		User chef = new StaffUser("Mathias", "Loh", "mathiasloh", "12345", "kyd1992@gmail.com");
		System.out.println(chef.toString());
		le1.addStaff((StaffUser)chef);
//		chef.setReceiveUpdates(false);
//		System.out.println(chef.getReceiveAddress());
//		
//		User chef2 = new StaffUser("Tiankai", "Xiong", "xtk923", "12345", "xiongtk@gmail.com");
//		le1.addStaff((StaffUser) chef2);
//		chef2.setReceiveUpdates(true);
		/**
		 * Loads all predefined ingredients
		 * Constructors: Ingredient(String name, double priceperquantity), Ingredient(String name, double quantity, double priceperquantity)
		 */
		Ingredient testIngredient1 = new Ingredient("Ingredient1", 6, 1.10);
		Ingredient testIngredient2 = new Ingredient("Ingredient2", 2.5, 1.69);
		Ingredient testIngredient3 = new Ingredient("Ingredient3", 2.3);
	
		
		/**
		 * Loads all predefined meals
		 * Constructors: Meal(String name, String description, Ingredients args(ingredients))
		 */
		Meals testMeal1 = new Meals("Meal1", "Good Food1", testIngredient1, testIngredient2);
		Meals testMeal2 = new Meals("Meal2", "Good Food2", testIngredient2, testIngredient3);
		Meals testMeal3 = new Meals("Meal3", "Good Food3", testIngredient1, testIngredient2, testIngredient3);
		
		/** 
		 * Add meals to the meal list
		 */
		le1.addMeals(testMeal3);
		le1.addMeals(testMeal2);
		le1.addMeals(testMeal1);
		
		testMeal1.setSpecialOfferToggle(true);
		
		/**
		 * Start system
		 */
		Thread system1 = new Thread(le1);
		system1.start();
	}
}
