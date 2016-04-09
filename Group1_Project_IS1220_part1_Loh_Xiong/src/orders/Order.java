package orders;

import ingredients.Ingredient;
import mealsystem.*;
import users.User;

import java.text.DecimalFormat;
import java.util.*;


public class Order{
	private DecimalFormat df = new DecimalFormat("#.00"); 
	private ArrayList<AbstractMeal> unprocessedOrders;
	private double total_transaction;
	private ArrayList<AbstractMeal> savedOrders;
	private ArrayList<AbstractMeal> editedOrders;
	
	
	/**
	 * Constructor Order
	 * unprocessedOrders = unpersonalized, selected orders
	 * editedOrders = personalized, selected orders
	 * savedOrders = unprocessedOrders + editedOrders
	 */
	public Order(){
		this.unprocessedOrders = new ArrayList<AbstractMeal>();
		this.savedOrders = new ArrayList<AbstractMeal>();
		this.editedOrders = new ArrayList<AbstractMeal>();
	}
	
	/**
	 * Adds unprocessedOrders to savedOrders
	 * Adds editedOrders to savedOrders
	 */
	public void saveOrder(){
		this.savedOrders.addAll(unprocessedOrders);
		for (AbstractMeal meal : savedOrders){
			total_transaction += meal.getPrice();
		}

		this.savedOrders.addAll(editedOrders);
		for (AbstractMeal meal : savedOrders){
			total_transaction += meal.getPrice();
		}
	}
	
	public ArrayList<AbstractMeal> getSavedOrder(){
		return this.savedOrders;
	}
	
	public void addPersonalizedMeal(AbstractMeal meal){
		this.editedOrders.add(meal);
	}
	
	/**
	 * Selects meal
	 * @param meal
	 * @param count
	 */
	public void selectMeal(AbstractMeal meal, int count){
		for (int i = 0; i < count; i++){
			unprocessedOrders.add(meal);	
		}
	}
	
	public ArrayList<AbstractMeal> getUnprocessedOrders(){
		return unprocessedOrders;
	}
	
	public AbstractMeal personalizeMeal(AbstractMeal meal, Ingredient ingredient, int quantity){
		// If meal already contains ingredient
		if (meal.getIngredients().contains(ingredient)){
			// if we set the quantity = 0, means he wants to remove the ingredient
			if (quantity == 0){
				// Creates a new meal instance
				// remove ingredients
				Meal newinstance = (Meal) meal.createNewInstance();
				newinstance.setBehavior(new RemoveIngredient());
				newinstance.executeBehavior(ingredient, 0);
				newinstance.setPersonalizedBool(true);
				// returns new meal instance
				return newinstance;
			// else, he wants to change ingredient count
			} else {
				// Creates a new meal instance
				// Creates a new ingredient instance (ingredients are independent instances!!)
				Meal newinstance = (Meal) meal.createNewInstance();
				Ingredient new_ingredient = ingredient.createnewinstance();
				new_ingredient.setQuantity(new_ingredient.getOriginalQuantity()+quantity);
				// removes old ingredient in new instance
				// adds new edited ingredient quantity in new instance
				newinstance.getIngredients().remove(ingredient);
				newinstance.getIngredients().add(new_ingredient);
				newinstance.setPersonalizedBool(true);
				return newinstance;
			}
		// else, ingredient is not present. hence we add the ingredient!
		} else {
			// Again, creates a new instance
			Meal newinstance = (Meal) meal.createNewInstance();
			// executes behavior
			newinstance.setBehavior(new AddIngredient());
			newinstance.executeBehavior(ingredient, quantity);
			newinstance.setPersonalizedBool(true);
			// returns new instance
			return newinstance;
		}
	}
	
	public double getTotalTransaction(){return this.total_transaction;}
	
	
	public void useCardfeature(User user){
		String cardtype = user.getFidelityCard().getCardName();
		switch(cardtype){
		case "BasicFidelityCard":
			break;
		case "LotteryFidelityCard":
			if (user.getFidelityCard().useFeature()){
				this.
			}
		}
	}
	/**
	 * Prints out a string containing the summary of orders saved
	 */
	public String Summary(){
		String s = new String();
		for (AbstractMeal obj : this.getSavedOrder()){
			s += obj.getName() + " " + obj.getIngredientsString() + ", $" + obj.getStringPrice();
			s += "\n";
		}
		s += "Total price: " + df.format(total_transaction);
		return s;
	}
	
	

	

}
