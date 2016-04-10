package mealsystem;


import customutilities.CustomUtilities;
import ingredients.Ingredient;

public class ChangeIngredient implements MealBehavior {
	/**
	 * change the quantity of an existing ingredient by "quantity" amount
	 */
	@Override
	public void behavior(AbstractMeal meal, Ingredient ingredient, double quantity) {
		if (meal.getIngredients().contains(ingredient)){
			for (Ingredient obj : meal.getIngredients()){
				if (obj.equals(ingredient)){
					if (obj.getQuantity() + quantity <= 0){
						throw new RuntimeException("Quantity cannot be less than 0!");
					}
					else {
						obj.setQuantity(obj.getQuantity()+ quantity);
						meal.extraIngredientsPrice += CustomUtilities.round((quantity)*obj.getPriceperquantity(),2);
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
