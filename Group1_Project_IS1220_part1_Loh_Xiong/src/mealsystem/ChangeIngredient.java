package mealsystem;

import customutilities.CustomUtilities;
import ingredients.Ingredient;

public class ChangeIngredient implements MealBehavior {

	@Override
	public void behavior(AbstractMeal meal, Ingredient ingredient, double quantity) {
		if (meal.ingredients.contains(ingredient)){
			for (Ingredient obj : meal.ingredients){
				if (obj.equals(ingredient)){
					if (obj.getQuantity() + quantity <= 0){
						throw new RuntimeException("Quantity cannot be less than 0!");
					}
					else {
						obj.setQuantity(obj.getQuantity()+ quantity);
						meal.extraIngredientsPrice += CustomUtilities.round((quantity)*obj.priceperquantity,2);
						meal.totalIngredientsPrice += meal.updatePrices();
						}
				}
			}
		}
		else {
			throw new RuntimeException("Ingredient not found!");
		}	
	}

}