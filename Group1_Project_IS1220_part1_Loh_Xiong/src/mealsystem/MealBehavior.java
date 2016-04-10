package mealsystem;

import ingredients.Ingredient;

/**
 * interface of MealBehavior
 * @author Xiong
 *
 */
public interface MealBehavior {
	void behavior(AbstractMeal meal, Ingredient ingredient, double Quantity);
}
