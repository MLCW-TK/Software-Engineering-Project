package ingredients;

/**
 * IngredientFactory class
 * @author Xiong
 *
 */
public class IngredientFactory {
	public Ingredient createIngredient(String name, double quantity) {
		return new Ingredient(name, quantity);
	}
	/**
	 * create a new ingredient
	 * this can allow potentially many kinds of ingredients to be created with same method
	 * @param name
	 * @param quantity
	 * @param priceperquantity
	 * @return
	 */
	public Ingredient createIngredient(String name, double quantity, double priceperquantity){
		return new Ingredient(name, quantity, priceperquantity);
	}


}
