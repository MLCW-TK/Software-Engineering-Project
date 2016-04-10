package mealsystem;


import ingredients.Ingredient;

public class MealFactory extends AbstractMealFactory{

	@Override
	public AbstractMeal createMeal(String name, String description, Ingredient... ingredients) {
		return new Meal(name, description, ingredients);
	}

	@Override
	public AbstractMeal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Meal(name, description, price, ingredients);
	}

	@Override
	public AbstractMeal createMeal(String name, double price){
		return new Meal(name, price);
	}
}
