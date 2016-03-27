package coresystem;

import ingredients.Ingredient;
import ingredients.IngredientFactory;
import mealsystem.Appertizer;
import mealsystem.AppertizerFactory;
import mealsystem.Dessert;
import mealsystem.DessertFactory;
import mealsystem.MainCourse;
import mealsystem.MainCourseFactory;
import mealsystem.AbstractMealFactory;
import users.*;

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
		IngredientFactory ingredient = new IngredientFactory();
		Ingredient vegetable = ingredient.createIngredient("vegetable", 1, 1.15);
	
		/**
		 * Loads all predefined meals
		 * Constructors: Meal(String name, String description, Ingredients args(ingredients))
		 */
		AbstractMealFactory appertizers = new AppertizerFactory();
		AbstractMealFactory maincourses = new MainCourseFactory();
		AbstractMealFactory desserts = new DessertFactory();
		
		Appertizer salad = (Appertizer) appertizers.createMeal("salad", "good for your health", 0, vegetable);
		MainCourse steak = (MainCourse) maincourses.createMeal("salad", "also good for you", vegetable);
		Dessert icecream = (Dessert) desserts.createMeal("icecream", "good for you", 10, vegetable);
		
		/** 
		 * Add meals to the meal list
		 */
		le1.addMeals(salad);
		le1.addMeals(steak);
		le1.addMeals(icecream);
		
		salad.setSpecialOfferToggle(true);
		
		/**
		 * Start system
		 */
		Thread system1 = new Thread(le1);
		system1.start();
	}
}