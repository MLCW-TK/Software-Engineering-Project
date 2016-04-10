package ingredients;


public class IngredientFactory {
	public Ingredient createIngredient(String name, double quantity) {
		return new Ingredient(name, quantity);
	}
	
	public Ingredient createIngredient(String name, double quantity, double priceperquantity){
		return new Ingredient(name, quantity, priceperquantity);
	}


}
