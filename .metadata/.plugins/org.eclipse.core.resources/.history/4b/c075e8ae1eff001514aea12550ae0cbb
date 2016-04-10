package orders;

import ingredients.Ingredient;
import mealsystem.*;
import users.User;

import java.text.DecimalFormat;
import java.util.*;

import cardfidelitysystem.FidelityCard;
import cardfidelitysystem.PointFidelityCard;


public class Order{
	private DecimalFormat df = new DecimalFormat("#.00"); 
	private ArrayList<AbstractMeal> unprocessedOrders;
	private double total_transaction;
	private ArrayList<AbstractMeal> savedOrders;
	private ArrayList<AbstractMeal> editedOrders;
	private static Stack<AbstractMeal> orders_as_it_is = new Stack<AbstractMeal>();
	private static HashMap<AbstractMeal, Integer> orders_most_modified = new HashMap<AbstractMeal, Integer>();
	private static Stack<AbstractMeal> orders_when_special_offer = new Stack<AbstractMeal>();
	private User orderedby;



	/**
	 * Constructor Order
	 * unprocessedOrders = unpersonalized, selected orders
	 * editedOrders = personalized, selected orders
	 * savedOrders = unprocessedOrders + editedOrders
	 * user = user who ordered
	 */
	public Order(User user) {
		this.setOrderedby(user);
		this.unprocessedOrders = new ArrayList<AbstractMeal>();
		this.savedOrders = new ArrayList<AbstractMeal>();
		this.editedOrders = new ArrayList<AbstractMeal>();
	}

	/**
	 * Adds unprocessedOrders to savedOrders
	 * Adds editedOrders to savedOrders
	 */
	public void saveOrder(){
		this.total_transaction = 0;
		this.savedOrders.addAll(unprocessedOrders);
		this.savedOrders.addAll(editedOrders);
//		 Saves orders as it is, in sequence
		Order.getOrders_as_it_is().addAll(savedOrders);
//		 Saves order according to number of times modified
		addToModifiedMap(this.editedOrders);
		FidelityCard fc = this.getOrderedby().getFidelityCard();
		for (AbstractMeal meal : savedOrders){
			if (fc.getCardName().equals("BasicFidelityCard")){
				if(this.getOrderedby().getCanReceiveSpecialOffers() && meal.isSpecialOffer()){
					total_transaction += meal.getSpecialPrice();
					getOrders_when_special_offer().add(meal);
				}else{total_transaction += meal.getPrice();}
			}
			if (fc.getCardName().equals("LotteryFidelityCard")){
				if (fc.useFeature()){
					total_transaction += 0;
				}else{
					total_transaction += meal.getPrice();
				}

			}
		}
		if (fc.getCardName().equals("PointFidelityCard")){
			if (fc.useFeature()){
				for (AbstractMeal meal2 : this.getSavedOrder()){
					total_transaction += (meal2.getPrice()*(1-((PointFidelityCard)fc).getDiscountRate()));
				}
			}else{
				for (AbstractMeal meal2 : this.getSavedOrder()){
					total_transaction += meal2.getPrice();
				}
			}
			((PointFidelityCard)fc).Add_Cash_as_Points(total_transaction);
		}

		this.clearOrders();
	}
	
	public static String showMealAsMostModified(){
		Map<String, Integer> stringlist = new HashMap<String, Integer>();
		String s = new String();
		for (AbstractMeal meal : getOrders_most_modified().keySet()){
			stringlist.put(meal.getName(), getOrders_most_modified().get(meal));
		}
		
		Map<String, Integer> sortedStringList = new TreeMap<String, Integer>(stringlist);
		int i = 1;
		for (Map.Entry<String, Integer> entry : sortedStringList.entrySet()) {
			s += i + ": " + entry.getKey() + ", Modified: " + entry.getValue() + " time(s) \n"; 
			i+=1;
		}
		
		return s;
		
	}
	
	public static String showMealsAsItIs(){
		String s = new String();
		int i = 1;
		for (AbstractMeal obj : Order.getOrders_as_it_is()){
			s += i + ": " + obj.getName() + " " + obj.getIngredientsString();
			s += "\n";
			i+=1;
		}
		
		return s;
		
	}
	
	public static String showMealJustOnSale(){
		String s = new String();
		int i = 1;
		for (AbstractMeal obj : getOrders_when_special_offer()){
			s += i + ": " + obj.getName() + " " + obj.getIngredientsString();
			s += "\n";
			i+=1;
		}
		
		return s;
	}
	
	public static void resetAllStaticData(){
		setOrders_as_it_is(new Stack<AbstractMeal>());
		setOrders_most_modified(new HashMap<AbstractMeal, Integer>());
		setOrders_when_special_offer(new Stack<AbstractMeal>());
	}
	
	public void addToModifiedMap(ArrayList<AbstractMeal> mealArray){
		for (AbstractMeal item : mealArray){
			if (!Order.getOrders_most_modified().containsKey(item)){
				Order.getOrders_most_modified().put(item, 1);
			}
			else {
				Order.getOrders_most_modified().put(item, Order.getOrders_most_modified().get(item)+1);
			}
		}
	}
	
	public void clearOrders(){
		this.setUnprocessedOrders(new ArrayList<AbstractMeal>());
		this.setEditedOrders(new ArrayList<AbstractMeal>());

	}

	public void setSavedOrder(ArrayList<AbstractMeal> o){this.savedOrders = o;}

	public ArrayList<AbstractMeal> getSavedOrder(){
		return this.savedOrders;
	}

	public void addPersonalizedMeal(AbstractMeal meal){
		this.editedOrders.add(meal);
		this.unprocessedOrders.remove(meal);
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


	public void setUnprocessedOrders(ArrayList<AbstractMeal> o){this.unprocessedOrders = o;}

	public ArrayList<AbstractMeal> getUnprocessedOrders(){
		return unprocessedOrders;
	}

	public void setEditedOrders(ArrayList<AbstractMeal> o){this.editedOrders = o;}

	public ArrayList<AbstractMeal> getEditedOrders(){
		return editedOrders;
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

	public User getOrderedby() {
		return orderedby;
	}

	public void setOrderedby(User user) {
		this.orderedby = user;
	}

	public static Stack<AbstractMeal> getOrders_as_it_is() {
		return orders_as_it_is;
	}

	public static void setOrders_as_it_is(Stack<AbstractMeal> orders_as_it_is) {
		Order.orders_as_it_is = orders_as_it_is;
	}

	public static HashMap<AbstractMeal, Integer> getOrders_most_modified() {
		return orders_most_modified;
	}

	public static void setOrders_most_modified(HashMap<AbstractMeal, Integer> orders_most_modified) {
		Order.orders_most_modified = orders_most_modified;
	}

	public static Stack<AbstractMeal> getOrders_when_special_offer() {
		return orders_when_special_offer;
	}

	public static void setOrders_when_special_offer(Stack<AbstractMeal> orders_when_special_offer) {
		Order.orders_when_special_offer = orders_when_special_offer;
	}





}
