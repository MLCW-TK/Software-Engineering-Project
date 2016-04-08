package mealsystem;

import customutilities.CustomUtilities;
import ingredients.Ingredient;

public class RemoveIngredient implements MealBehavior {

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
