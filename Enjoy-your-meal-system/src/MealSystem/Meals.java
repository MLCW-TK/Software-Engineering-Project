	package MealSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;

public class Meals{
	public String name;
	public double totalIngredientsPrice;
	public double extraIngredientsPrice;
	public double price;
	public double temp_price;
	public double specialPrice;
	public String description = "";
	public boolean specialOfferToggle = false;
	public HashSet<Ingredient> ingredients = new HashSet<Ingredient>();
	
	
	public Meals(String name, String description, Ingredient ...ingredients){
		this.name = name;
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.description = description;
		this.extraIngredientsPrice = 0;
		this.totalIngredientsPrice = updatePrices();
		this.price = this.totalIngredientsPrice;
		this.temp_price = price;
	}
	
	public Meals(String name, String description, double price, Ingredient ...ingredients){
		this.name = name;
		for (Ingredient obj : ingredients){
			this.ingredients.add(obj);
		}
		this.description = description;
		this.temp_price = price;
		this.price = price;
		this.extraIngredientsPrice = 0;
		this.totalIngredientsPrice = updatePrices();
	}
	
	public double getTotalIngredientPrice(){
		return round(this.totalIngredientsPrice,2);
	}
	
	public double updatePrices(){
		double price = 0;
		for (Ingredient obj : this.ingredients){
			price += obj.getTotalprice();
		}
		return round(price,2);
	}
	
	public double updateSpecialPrices(double price){
		if (this.specialPrice + price < 0){
			return round(0,2);
		} else {
			double newPrice = this.specialPrice;
			return round((newPrice += price),2);
		}
	}
	
	public String getName() {
		return name;
	}


	public double getPrice() {
		if (isSpecialOffer()){
			return round(specialPrice,2);
		} else {
			if (this.extraIngredientsPrice+this.price < this.price){
				return round(this.price,2);
			} else {
				return round(extraIngredientsPrice+this.price,2);
			}
		}
	}

	public void setPrice(int price){
		this.price = price;
	}

	public double getSpecialPrice() {
		if (isSpecialOffer()){
			return round(specialPrice,2);
		} else {
			throw new RuntimeException("No special offers yet!");
		}
	}


	public void setSpecialPrice(double specialPrice) {
		if (isSpecialOffer()){
			this.specialPrice = specialPrice;
		} else {
			throw new RuntimeException("Special offer not set yet!");
		}
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isSpecialOffer() {
		return specialOfferToggle;
	}


	public void setSpecialOfferToggle(boolean specialOffer) {
		this.specialOfferToggle = specialOffer;
	}


	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public String getIngredientsString(){
		ArrayList<String> s = new ArrayList<String>();
		for (Ingredient obj : ingredients){
			s.add(obj.getName());
		}
		Collections.sort(s);
		return s.toString();
	}
	
	public void addIngredient(Ingredient ingredient){
		if (ingredients.contains(ingredient)){
			throw new RuntimeException("Ingredient already exist!");
		} else {
			ingredients.add(ingredient);
			this.extraIngredientsPrice += round(ingredient.getTotalprice(),2);
			this.totalIngredientsPrice += round(this.extraIngredientsPrice,2);
		}
	}
	
	
	public void removeIngredient(Ingredient ingredient){
		if (ingredients.contains(ingredient)){
			ingredients.remove(ingredient);
			this.extraIngredientsPrice -= round(ingredient.getTotalprice(),2);
			this.totalIngredientsPrice -= round(this.extraIngredientsPrice,2);
		} else {
			throw new RuntimeException("Ingredient does not exist!");
		}
	}
	
	public void changeIngredientQuantity(Ingredient ingredient, double quantity){
		if (ingredients.contains(ingredient)){
			if (ingredient.getQuantity() + quantity < 0){
				throw new RuntimeException("Quantity cannot be less than 0!");
			} else {
				double quantity_changed = quantity - ingredient.getQuantity();
				ingredient.setQuantity(quantity);
				this.extraIngredientsPrice += round((quantity_changed)*ingredient.priceperquantity,2);
				this.totalIngredientsPrice += round(this.extraIngredientsPrice,2);
			}
			
		} else {
			throw new RuntimeException("Ingredient not found!");
		}
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	// Equals and hash
	/**
	 * Check if the name is the same
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Meals){
			Meals c1 = (Meals) o; 
			return (c1.getName().equals(this.name));
		}
		return false;
	}
	/**
	 * overridden hashCode method
	 */
	
	@Override
	public int hashCode(){
    	int code = 0;
        for (int i=0; i < this.name.length(); i++){
        	char c = this.name.charAt(i);
        	int h = 41+((int)c+i)*(19+i);
        	code += h;
        }
		return code;
	}
	
}
