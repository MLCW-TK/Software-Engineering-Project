package mealsystem;

import ingredients.Ingredient;

public interface MealBehavior {
	void behavior(Meal meal, Ingredient ingredient, double Quantity);
}
