package mealSystem;

import ingredients.Ingredient;

public class MainCourseFactory extends MealFactory{

	@Override
	public Meal createMeal(String name, String description, Ingredient... ingredients) {
		return new MainCourse(name, description, ingredients);
	}

	@Override
	public Meal createMeal(String name, String description, double price, Ingredient... ingredients) {
		// TODO Auto-generated method stub
		return new MainCourse(name, description, price, ingredients);
	}

}
