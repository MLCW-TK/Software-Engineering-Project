package mealsystem;


import ingredients.Ingredient;

/**
 * MealFactory class
 * this is a subclass of AbstractmealFactory
 * @author Xiong
 *
 */
public class MealFactory extends AbstractMealFactory{

	/**
	 * create an AbstractMeal instance
	 */
	@Override
	public AbstractMeal createMeal(String name, String description, Ingredient... ingredients) {
		return new Meal(name, description, ingredients);
	}

	/**
	 * create an AbstractMeal instance
	 */
	@Override
	public AbstractMeal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Meal(name, description, price, ingredients);
	}

	/**
	 * create an AbstractMeal instance
	 */
	@Override
	public AbstractMeal createMeal(String name, double price){
		return new Meal(name, price);
	}
}
