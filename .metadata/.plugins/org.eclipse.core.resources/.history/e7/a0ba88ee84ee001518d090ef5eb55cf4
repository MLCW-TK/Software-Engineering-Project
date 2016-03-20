package mealSystem;

import ingredients.Ingredient;

public abstract class MealFactory {
	/*
	 * Requirement stipulated by our system is that information must be complete before the item
	 * can be created. If certain information is missing (i.e. author name), illegal item_type
	 * exception would be thrown.
	 */
	public abstract Meal createMeal(String name, String description, Ingredient ...ingredients);
	public abstract Meal createMeal(String name, String description, double price, Ingredient ...ingredients);
	

}