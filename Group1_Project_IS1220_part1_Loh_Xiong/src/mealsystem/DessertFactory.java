package mealsystem;

import ingredients.Ingredient;

public class DessertFactory extends AbstractMealFactory {

	@Override
	public AbstractMeal createMeal(String name, String description, Ingredient... ingredients) {
		return new Dessert(name, description, ingredients);
	}

	@Override
	public AbstractMeal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Dessert(name, description, price, ingredients);
	}

	@Override
	public AbstractMeal createMeal(String name, double price){
		return new Dessert(name, price);
	}
}