package mealsystem;


import ingredients.Ingredient;

/**not used yet
 * MainCourseFactory
 * this is a subclass of MealFactory
 * @author Xiong
 *
 */
public class MainCourseFactory extends MealFactory{

	/**
	 * create a MainCourse instance
	 */
	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new MainCourse(name, description, ingredients);
	}

	/**
	 * create a MainCourse instance
	 */
	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		// TODO Auto-generated method stub
		return new MainCourse(name, description, price, ingredients);
	}

	/**
	 * create a MainCourse instance
	 */
	@Override
	public Meal createMeal(String name, double price){
		return new Appertizer(name, price);
	}
}
