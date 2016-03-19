package Orders;

import MealSystem.Meals;
import Users.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import CardFidelitySystem.*;

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
		this.meal_count = new HashMap<Meals, Integer>();
		this.orderSequence = new ArrayList<ArrayList<Object>>();
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
				amount_paid = round(meal.getPrice(),2);
				total_transaction += amount_paid;
			} 
			
			if (user.getFidelityCard() instanceof PointFidelityCard){
				PointFidelityCard reference = (PointFidelityCard) user.getFidelityCard();
				if (reference.UseFeature()){
					amount_paid = round(meal.getPrice()*reference.getDiscountRate(),2);
					reference.Add_Cash_as_Points(amount_paid);
					total_transaction += amount_paid;
				} else {
					amount_paid = round(meal.getPrice(),2);
					reference.Add_Cash_as_Points(amount_paid);
					total_transaction += amount_paid;
				}
			}
			
			if (user.getFidelityCard() instanceof LotteryFidelityCard){
				LotteryFidelityCard reference = (LotteryFidelityCard) user.getFidelityCard();
				if (reference.UseFeature()){
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
