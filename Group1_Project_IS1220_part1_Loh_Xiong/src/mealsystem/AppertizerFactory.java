package mealsystem;


import ingredients.Ingredient;

/**not used yet
 * AppertizerFactory
 * this is a subclass of MealFactory
 * @author Xiong
 *
 */
public class AppertizerFactory extends MealFactory{

	/**
	 * create an Appertizer
	 */
	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new Appertizer(name, description, ingredients);
	}

	/**
	 * create an Appertizer
	 */
	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Appertizer(name, description, price, ingredients);
	}

	/**
	 * create an Appertizer
	 */
	@Override
	public Meal createMeal(String name, double price){
		return new Appertizer(name, price);
	}
}
