package mealsystem;

import ingredients.Ingredient;

public interface MealBehavior {
	void behavior(AbstractMeal meal, Ingredient ingredient, double Quantity);
}
