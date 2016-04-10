package mealsystem;


import ingredients.Ingredient;

/**not used yet
 * DessertFactory
 * This is a subclass of MealFacotry
 * @author Xiong
 *
 */
public class DessertFactory extends MealFactory {

	/**
	 * create a Dessert
	 */
	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new Dessert(name, description, ingredients);
	}
	
	/**
	 * create a Dessert
	 */
	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		return new Dessert(name, description, price, ingredients);
	}
	
	/**
	 * create a Dessert
	 */
	@Override
	public Meal createMeal(String name, double price){
		return new Dessert(name, price);
	}
}
