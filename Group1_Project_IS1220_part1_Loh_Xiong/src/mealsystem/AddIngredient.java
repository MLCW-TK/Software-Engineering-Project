package mealsystem;

import customutilities.*;
import ingredients.Ingredient;


public class AddIngredient implements MealBehavior{
	public void behavior(AbstractMeal meal, Ingredient ingredient, double quantity){
		if (meal.getIngredients().contains(ingredient)){
			throw new RuntimeException("Ingredient already exist!");
		} else {
			meal.getIngredients().add(ingredient);
			meal.extraIngredientsPrice += CustomUtilities.round(ingredient.getTotalprice(),2);
			meal.totalIngredientsPrice += CustomUtilities.round(meal.getextraIngredientsPrice(),2);
		}
	}
}
