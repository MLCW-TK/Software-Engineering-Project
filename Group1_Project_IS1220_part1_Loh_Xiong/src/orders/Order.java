package orders;

import ingredients.Ingredient;
import mealsystem.*;
import users.User;

import java.text.DecimalFormat;
import java.util.*;

import cardfidelitysystem.FidelityCard;
import cardfidelitysystem.PointFidelityCard;
import commandinterface.ClientConsole;

/**
 * Order class
 * @author Xiong
 *
 */
public class Order{
	private DecimalFormat df = new DecimalFormat("#.00"); 
	private ArrayList<AbstractMeal> unprocessedOrders;
	private double total_transaction;
	private ArrayList<AbstractMeal> savedOrders;
	private ArrayList<AbstractMeal> editedOrders;
	private static Stack<AbstractMeal> meal_as_it_is = new Stack<AbstractMeal>();
	private static Stack<AbstractMeal> meal_as_modified = new Stack<AbstractMeal>();
	private static Stack<AbstractMeal> meal_only_on_sale = new Stack<AbstractMeal>();
	private static Stack<AbstractMeal> orders_as_it_is = new Stack<AbstractMeal>();
	private static Stack<AbstractMeal> orders_modified = new Stack<AbstractMeal>();
//	private static HashMap<AbstractMeal, Integer> orders_most_modified = new HashMap<AbstractMeal, Integer>();
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
		Order.getOrders_as_it_is().addAll(unprocessedOrders);
//		 Saves order according to number of times modified
		Order.getOrders_modified().addAll(editedOrders);
//		addToModifiedMap(this.editedOrders);
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
	

	/**
	 * calculate the asItIsCount, asModifiedCount and justOnSaleCount
	 * of all meals in the RestaurantSystem's meal_list
	 */
	public static void generateStatistics(){
		for (AbstractMeal a : ClientConsole.re1.getMeal_list()){
			a.setAsItIsCount(0);
			for (AbstractMeal b : Order.getOrders_as_it_is()){
				if (a.getName().equalsIgnoreCase(b.getName())){
					a.setAsItIsCount(a.getAsItIsCount()+1);
				}
			}
			a.setAsModifiedCount(0);
			for (AbstractMeal b : Order.getOrders_modified()){
				if (a.getName().equalsIgnoreCase(b.getName())){
					a.setAsModifiedCount(a.getAsModifiedCount()+1);
				}
			}
			a.setJustOnSaleCount(0);
			for (AbstractMeal b : Order.getOrders_when_special_offer()){
				if (a.getName().equalsIgnoreCase(b.getName())){
					a.setJustOnSaleCount(a.getJustOnSaleCount()+1);
				}
			}
		}
	}

	/**
	 * return a String of the meals that are mostly modified when ordered
	 * @return
	 */
	public static String showMealAsMostModified(){
		Order.setMeal_as_modified(new Stack<AbstractMeal>());
		String s = new String();
		Order.generateStatistics();
		for (AbstractMeal m : ClientConsole.re1.getMeal_list()){
			if (m.getAsModifiedCount() >= m.getAsItIsCount()&& m.getAsModifiedCount() >= m.getJustOnSaleCount()){
				s += m.getName()+": "+m.getAsModifiedCount() +"\n";
				Order.getMeal_as_modified().add(m);
			}
		}
		return s;
	}
	

	/**
	 * return a String of the meals that are mostly ordered as they are
	 * @return
	 */
	public static String showMealsAsItIs(){
		Order.setMeal_as_it_is(new Stack<AbstractMeal>());
		String s = new String();
		Order.generateStatistics();
		for (AbstractMeal m : ClientConsole.re1.getMeal_list()){
			if (m.getAsItIsCount() >= m.getAsModifiedCount()&& m.getAsItIsCount() >= m.getJustOnSaleCount()){
				s += m.getName()+": "+m.getAsItIsCount() +"\n";
				Order.getMeal_as_it_is().add(m);
			}
		}
		return s;
	}

	/**
	 * return a string of the meal that are mostly ordered when they are on sale
	 * @return
	 */
	public static String showMealJustOnSale(){
		Order.setMeal_only_on_sale(new Stack<AbstractMeal>());
		String s = new String();
		Order.generateStatistics();
		for (AbstractMeal m : ClientConsole.re1.getMeal_list()){
			if (m.getJustOnSaleCount() >= m.getAsModifiedCount()&& m.getJustOnSaleCount() >= m.getAsItIsCount()){
				s += m.getName()+": "+m.getJustOnSaleCount() +"\n";
				Order.getMeal_only_on_sale().add(m);
			}
		}
		return s;
	}

	/**
	 * reset all static data
	 */
	public static void resetAllStaticData(){
		setOrders_as_it_is(new Stack<AbstractMeal>());
		setOrders_modified(new Stack<AbstractMeal>());
//		setOrders_most_modified(new HashMap<AbstractMeal, Integer>());
		setOrders_when_special_offer(new Stack<AbstractMeal>());
	}
//	
//	public void addToModifiedMap(ArrayList<AbstractMeal> mealArray){
//		for (AbstractMeal item : mealArray){
//			if (!Order.getOrders_most_modified().containsKey(item)){
//				Order.getOrders_most_modified().put(item, 1);
//			}
//			else {
//				Order.getOrders_most_modified().put(item, Order.getOrders_most_modified().get(item)+1);
//			}
//		}
//	}
	
	/**
	 * clear the orders
	 */
	public void clearOrders(){
		this.setUnprocessedOrders(new ArrayList<AbstractMeal>());
		this.setEditedOrders(new ArrayList<AbstractMeal>());

	}

	/**
	 * SavedOrder setter
	 * @param o
	 */
	public void setSavedOrder(ArrayList<AbstractMeal> o){this.savedOrders = o;}

	/**
	 * savedOrder getter
	 * @return
	 */
	public ArrayList<AbstractMeal> getSavedOrder(){return this.savedOrders;}

	/**
	 * add a personalized meal,
	 * transfer this meal from unprocessedOrders to editedOrders
	 * @param meal
	 */
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


	/**
	 * unprocessedOrders setter
	 * @param o
	 */
	public void setUnprocessedOrders(ArrayList<AbstractMeal> o){this.unprocessedOrders = o;}

	/**
	 * unprocessedOrders getter
	 * @return
	 */
	public ArrayList<AbstractMeal> getUnprocessedOrders(){return unprocessedOrders;}

	/**
	 * editedorders setter
	 * @param o
	 */
	public void setEditedOrders(ArrayList<AbstractMeal> o){this.editedOrders = o;}

	/**
	 * editedOrders getter
	 * @return
	 */
	public ArrayList<AbstractMeal> getEditedOrders(){return editedOrders;}

	/**
	 * personalizeMeal
	 * by modifying the quantity of certain ingredient
	 * @param meal
	 * @param ingredient
	 * @param quantity
	 * @return
	 */
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

	/**
	 * total_transaction getter
	 * @return
	 */
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

	/**
	 * orderedBy getter
	 * return the user who made the order
	 * @return
	 */
	public User getOrderedby() {return orderedby;}

	/**
	 * orderedBy setter
	 * @param user
	 */
	public void setOrderedby(User user) {this.orderedby = user;}

	/**
	 * orders_as_it_is getter
	 * @return
	 */
	public static Stack<AbstractMeal> getOrders_as_it_is() {return orders_as_it_is;}

	/**
	 * orders_as_it_is setter
	 * @param orders_as_it_is
	 */
	public static void setOrders_as_it_is(Stack<AbstractMeal> orders_as_it_is) {Order.orders_as_it_is = orders_as_it_is;}

//	/**
//	 * 
//	 * @return
//	 */
//	public static HashMap<AbstractMeal, Integer> getOrders_most_modified() {
//		return orders_most_modified;
//	}

//	/**
//	 * orderd_most_modified setter
//	 * @param orders_most_modified
//	 */
//	public static void setOrders_most_modified(HashMap<AbstractMeal, Integer> orders_most_modified) {Order.orders_most_modified = orders_most_modified;}

	/**
	 * orders_when_special_offer getter
	 * @return
	 */
	public static Stack<AbstractMeal> getOrders_when_special_offer() {return orders_when_special_offer;}

	/**
	 * orders_when_special_offer setter
	 * @param orders_when_special_offer
	 */
	public static void setOrders_when_special_offer(Stack<AbstractMeal> orders_when_special_offer) {Order.orders_when_special_offer = orders_when_special_offer;}

	/**
	 * orders_modified getter 
	 * @return
	 */
	public static Stack<AbstractMeal> getOrders_modified() {return orders_modified;}

	/**
	 * orders_modified setter
	 * @param orders_modified
	 */
	public static void setOrders_modified(Stack<AbstractMeal> orders_modified) {Order.orders_modified = orders_modified;}

	public static Stack<AbstractMeal> getMeal_as_it_is() {
		return meal_as_it_is;
	}

	public static void setMeal_as_it_is(Stack<AbstractMeal> meal_as_it_is) {
		Order.meal_as_it_is = meal_as_it_is;
	}

	public static Stack<AbstractMeal> getMeal_as_modified() {
		return meal_as_modified;
	}

	public static void setMeal_as_modified(Stack<AbstractMeal> meal_as_modified) {
		Order.meal_as_modified = meal_as_modified;
	}

	public static Stack<AbstractMeal> getMeal_only_on_sale() {
		return meal_only_on_sale;
	}

	public static void setMeal_only_on_sale(Stack<AbstractMeal> meal_only_on_sale) {
		Order.meal_only_on_sale = meal_only_on_sale;
	}





}
