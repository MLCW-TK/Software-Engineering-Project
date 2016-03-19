package Orders;

import MealSystem.Meals;
import Users.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Order{
	Stack<String> SummarySequence;
	ArrayList<ArrayList<Object>> orderSequence;
	HashMap<Meals, Integer> meal_count;
	ArrayList<ArrayList<Object>> specialOfferOrderSequence;
	double total_transaction;
	
	/**
	 * Constructor Order
	 */
	public Order(){
		this.orderSequence = new ArrayList<ArrayList<Object>>();
		this.meal_count = new HashMap<Meals, Integer>();
		this.specialOfferOrderSequence = new ArrayList<ArrayList<Object>>();
		this.SummarySequence = new Stack();
	}
	
	/**
	 * Add orders to the orderSequence
	 * @param meal
	 * @param user
	 * @param count
	 */
	public void Add_order(Meals meal, User user, int count){
		for (int i = 0; i<count; i++){
			ArrayList<Object> temp = new ArrayList<Object>();
			temp.add(user);
			temp.add(meal);
			orderSequence.add(temp);
			// push the order as a string to the SummarySequence stack.
			String s = new String();
			s += user.getUsername() + ": " + meal.getName() + " " + meal.getIngredientsString() + ", price:" + meal.getPrice() + "\n";
			SummarySequence.push(s);

		}
		// modify the meal_count accordingly
		if (!meal_count.containsKey(meal)){
			meal_count.put(meal, count);
		} else {
			int current_count = meal_count.get(meal);
			meal_count.put(meal, current_count + count);
		}
		// calculate the total transaction
		total_transaction += ((double) count)*meal.getPrice();
		
	}

	
	public void Add_order_specialoffer(Meals meal, User user, int count){
		for (int i = 0; i<count; i++){
			
		}
	}
	
	public String Summary(){
		String s = new String();
		while (!SummarySequence.isEmpty()){
			s += SummarySequence.pop();
		}
		s += "Total transaction cost: " + round(total_transaction,2);
		return s;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
