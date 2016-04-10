package mealsystem;


import customutilities.CustomUtilities;
import ingredients.Ingredient;

/**
 * Remove Ingredient class
 * this is a realization of the MealBehavior interface
 * @author Xiong
 *
 */
public class RemoveIngredient implements MealBehavior {

	/**
	 * remove a chosen ingredient from the meal.
	 */
	@Override
	public void behavior(AbstractMeal meal, Ingredient ingredient, double quantity) {
		if (meal.getIngredients().contains(ingredient)){
			meal.getIngredients().remove(ingredient);
			meal.extraIngredientsPrice -= CustomUtilities.round(ingredient.getTotalprice(),2);
			meal.totalIngredientsPrice -= CustomUtilities.round(meal.extraIngredientsPrice,2);
		} else {
			throw new RuntimeException("Ingredient does not exist!");
		}
	}

}
