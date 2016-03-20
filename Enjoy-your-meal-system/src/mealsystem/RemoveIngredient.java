package mealSystem;

import customUtilities.CustomUtilities;
import ingredients.Ingredient;

public class RemoveIngredient implements MealBehavior {

	@Override
	public void behavior(Meal meal, Ingredient ingredient, double quantity) {
		if (meal.ingredients.contains(ingredient)){
			meal.ingredients.remove(ingredient);
			meal.extraIngredientsPrice -= CustomUtilities.round(ingredient.getTotalprice(),2);
			meal.totalIngredientsPrice -= CustomUtilities.round(meal.extraIngredientsPrice,2);
		} else {
			throw new RuntimeException("Ingredient does not exist!");
		}
	}

}
