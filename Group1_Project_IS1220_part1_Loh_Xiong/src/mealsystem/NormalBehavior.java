package mealsystem;


import java.util.HashSet;

import ingredients.Ingredient;

/**
 * NormalBehavior class
 * this is a realization of the MealBehavior interface
 * @author Xiong
 *
 */
public class NormalBehavior implements MealBehavior {

	/**
	 * initialize the meal
	 */
	@Override
	public void behavior(AbstractMeal meal, Ingredient ingredient, double quantity) {
		meal.setPrice(meal.getDefaultprice());
		meal.setIngredients(new HashSet<Ingredient>());
		for (Ingredient obj : meal.getDefault_ingredients()){
			obj.setQuantity(obj.getOriginalQuantity());
		}
		meal.getIngredients().addAll(meal.getDefault_ingredients());
		meal.extraIngredientsPrice = 0;
		meal.totalIngredientsPrice = meal.updatePrices();
	}

}
