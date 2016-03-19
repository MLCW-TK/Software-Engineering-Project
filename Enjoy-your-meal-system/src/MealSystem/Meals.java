package MealSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

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
	
	/**
	 * 
	 * @param name
	 * @param description
	 * @param ingredients
	 */
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
	
	/**
	 * constructor Meals, specifying the price
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
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
	
	public Meals createNewInstance(){
		Ingredient[] new_ingredients = new Ingredient[this.ingredients.size()];
		Meals new_meal = new Meals(this.name, this.description, this.price, this.ingredients.toArray(new_ingredients));
		return new_meal;
	}
	
	/**
	 * getTotalIngredientPrice to 2 decimal places, i.e. to cents
	 * @return
	 */
	public double getTotalIngredientPrice(){
		return round(this.totalIngredientsPrice,2);
	}
	
	/**
	 * updatePrices by summing up prices of each ingredient.
	 * @return
	 */
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

	/**
	 * getPrice of the meal
	 * if on special offer, return special price
	 * else, return normal price with consideration of extra ingredients. 
	 * @return
	 */
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
	
	/**
	 * set the price of the meal
	 * @param price
	 */
	public void setPrice(int price){
		this.price = price;
	}
	/** 
	 * returns special price if the meal is on special offer
	 * @return
	 */
	public double getSpecialPrice() {
		if (isSpecialOffer()){
			return round(specialPrice,2);
		} else {
			return round(price, 2);
		}
	}

	/**
	 * set special price only when the offer is on
	 * @param specialPrice
	 */
	public void setSpecialPrice(double specialPrice) {
		if (isSpecialOffer() && this.price>=specialPrice){
			this.specialPrice = specialPrice;
		} else {
			this.specialPrice = this.price;
		}
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * check if this meal is on special offer
	 * @return
	 */
	public boolean isSpecialOffer() {
		return specialOfferToggle;
	}

	/**
	 * set the special offer state to be true or false
	 * @param specialOffer
	 */
	public void setSpecialOfferToggle(boolean specialOffer) {
		this.specialOfferToggle = specialOffer;
	}

	/**
	 * get the HashSet of ingredients
	 * @return
	 */
	public HashSet<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/**
	 * return a string of the names of ingredients
	 * @return
	 */
	public String getIngredientsString(){
		ArrayList<String> s = new ArrayList<String>();
		for (Ingredient obj : ingredients){
			s.add(obj.getName());
		}
		Collections.sort(s);
		return s.toString();
	}
	
	/**
	 * add an extra ingredient
	 * @param ingredient
	 */
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
	
	public String toString(){
		String s = new String();
		s = this.getName()+": "+this.getDescription()+"\n" + "Original price: " + this.getPrice()+"\n"
				+ "Special price: " +this.getSpecialPrice()+"\n\n";
		return s;
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
