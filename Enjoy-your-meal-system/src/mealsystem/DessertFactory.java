package mealsystem;

import ingredients.Ingredient;

public class DessertFactory extends MealFactory {

	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new Dessert(name, description, ingredients);
	}

	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Dessert(name, description, price, ingredients);
	}

}
