package mealsystem;

import java.util.HashSet;

import ingredients.Ingredient;

public class NormalBehavior implements MealBehavior {

	@Override
	public void behavior(Meal meal, Ingredient ingredient, double quantity) {
		meal.setPrice(meal.default_price);
		meal.ingredients = new HashSet<Ingredient>();
		for (Ingredient obj : meal.default_ingredients){
			obj.setQuantity(obj.getOriginalQuantity());
		}
		meal.ingredients.addAll(meal.default_ingredients);
		meal.extraIngredientsPrice = 0;
		meal.totalIngredientsPrice = meal.updatePrices();
	}

}
