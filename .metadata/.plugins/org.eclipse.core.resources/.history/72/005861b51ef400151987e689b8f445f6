package orders;

import customutilities.CustomUtilities;
import ingredients.Ingredient;
import mealsystem.*;
import users.User;

import java.text.DecimalFormat;
import java.util.*;

import cardfidelitysystem.BasicFidelityCard;
import cardfidelitysystem.LotteryFidelityCard;
import cardfidelitysystem.PointFidelityCard;

public class Order{
	DecimalFormat df = new DecimalFormat("#.00"); 
	Stack<String> SummarySequence;
	ArrayList<ArrayList<Object>> orderSequence;
	HashMap<AbstractMeal, Integer> meal_count;
	ArrayList<ArrayList<Object>> specialOfferOrderSequence;
	ArrayList<AbstractMeal> unprocessedOrders = new ArrayList<AbstractMeal>();
	double total_transaction;
	ArrayList<AbstractMeal> savedOrders = new ArrayList<AbstractMeal>();
	ArrayList<AbstractMeal> editedOrders = new ArrayList<AbstractMeal>();
	/**
	 * Constructor Order
	 */
	public Order(){
		this.meal_count = new HashMap<AbstractMeal, Integer>();
		this.orderSequence = new ArrayList<ArrayList<Object>>();
		this.specialOfferOrderSequence = new ArrayList<ArrayList<Object>>();
		this.SummarySequence = new Stack<String>();
	}
	
	public void saveOrder(){
		this.savedOrders.addAll(unprocessedOrders);
		for (AbstractMeal meal : savedOrders){
			total_transaction += meal.getPrice();
		}
<<<<<<< HEAD
		this.savedOrders.addAll(editedOrders);
		for (AbstractMeal meal : savedOrders){
			total_transaction += meal.getPrice();
		}
=======
>>>>>>> 37e232836025391d7f32ab1ee5186fa11547a131
	}
	
	public ArrayList<AbstractMeal> getSavedOrder(){
		return this.savedOrders;
	}
	
	public void addPersonalizedMeal(AbstractMeal meal){
		this.editedOrders.add(meal);
	}
	
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
<<<<<<< HEAD
				Meal newinstance = (Meal) meal.createnewinstance();
				newinstance.setBehavior(new RemoveIngredient());
				newinstance.executeBehavior(ingredient, 0);
				newinstance.setPersonalizedBool(true);
				return newinstance;
=======
				meal.setBehavior(new RemoveIngredient());
				meal.executeBehavior(ingredient, 0);
>>>>>>> 37e232836025391d7f32ab1ee5186fa11547a131
			} else {
				Meal newinstance = (Meal) meal.createnewinstance();
				Ingredient new_ingredient = ingredient.createnewinstance();
				new_ingredient.setQuantity(new_ingredient.original_quantity+quantity);
				newinstance.getIngredients().remove(ingredient);
				newinstance.getIngredients().add(new_ingredient);
				newinstance.setPersonalizedBool(true);
				return newinstance;
//				meal.setBehavior(new ChangeIngredient());
//				meal.executeBehavior(ingredient, quantity);
			}
		} else {
<<<<<<< HEAD
			Meal newinstance = (Meal) meal.createnewinstance();
			newinstance.setBehavior(new AddIngredient());
			newinstance.executeBehavior(ingredient, quantity);
			newinstance.setPersonalizedBool(true);
			return newinstance;
		}
=======
			meal.setBehavior(new AddIngredient());
			meal.executeBehavior(ingredient, quantity);
		}
		
		Meal newinstance = (Meal) meal.createnewinstance();
		newinstance.setPersonalizedBool(true);
		meal.setBehavior(new NormalBehavior());
		meal.executeBehavior(null, 0);
		return newinstance;
>>>>>>> 37e232836025391d7f32ab1ee5186fa11547a131
	}
	
	
	
	/**
	 * Add orders to the orderSequence
	 * @param meal
	 * @param user
	 * @param count
	 */
	public void Add_order(AbstractMeal meal, User user, int count){
		// Creates a list of all orders
		for (int i = 0; i<count; i++){
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(user);
			temp.add(meal);
			orderSequence.add(temp);

			// push the order as a string to the SummarySequence stack.

			if (user.getCanReceiveSpecialOffers()){
				ArrayList<Object> temp2 = new ArrayList<Object>();
				temp2.add(user);
				temp2.add(meal);
				specialOfferOrderSequence.add(temp2);
			}
			
			double amount_paid = 0;
			if (user.getFidelityCard() instanceof BasicFidelityCard){
				amount_paid = CustomUtilities.round(meal.getSpecialPrice(),2);
				total_transaction += amount_paid;
			} 
			
			if (user.getFidelityCard() instanceof PointFidelityCard){
				PointFidelityCard reference = (PointFidelityCard) user.getFidelityCard();
				if (reference.useFeature()){
					amount_paid = CustomUtilities.round(meal.getPrice()*reference.getDiscountRate(),2);
					reference.Add_Cash_as_Points(amount_paid);
					total_transaction += amount_paid;
				} else {
					amount_paid = CustomUtilities.round(meal.getPrice(),2);
					reference.Add_Cash_as_Points(amount_paid);
					total_transaction += amount_paid;
				}
			}
			
			if (user.getFidelityCard() instanceof LotteryFidelityCard){
				LotteryFidelityCard reference = (LotteryFidelityCard) user.getFidelityCard();
				if (reference.useFeature()){
					amount_paid = 0;
					total_transaction += amount_paid;
				} else{
					amount_paid = meal.getPrice();
					total_transaction += amount_paid;
				}
			}
			

			String s = new String();
			s += user.getUsername() + ": " + meal.getName() + " " + meal.getIngredientsString() + ", price:" + amount_paid + "\n";
			SummarySequence.push(s);
		}

		// modify the meal_count accordingly

		
		// adds counter view

		if (!meal_count.containsKey(meal)){
			meal_count.put(meal, count);
		} else {
			int current_count = meal_count.get(meal);
			meal_count.put(meal, current_count + count);
		}		

	}
	
	public double getTotalTransaction(){return this.total_transaction;}
	
	
	public void Add_order_specialoffer(AbstractMeal meal, User user, int count){
		for (int i = 0; i<count; i++){
			
		}
	}
	
	public String Summary(){
		String s = new String();
		int i = 0;
		for (AbstractMeal obj : this.getSavedOrder()){
			s += obj.getName() + " " + obj.getIngredientsString() + ", $" + obj.getStringPrice();
			s += "\n";
		}
		s += "Total price: " + df.format(total_transaction);
		return s;
	}

}
