package mealsystem;


import ingredients.Ingredient;

public class AppertizerFactory extends MealFactory{

	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new Appertizer(name, description, ingredients);
	}

	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Appertizer(name, description, price, ingredients);
	}

	@Override
	public Meal createMeal(String name, double price){
		return new Appertizer(name, price);
	}
}
