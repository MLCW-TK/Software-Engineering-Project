package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ingredients.Ingredient;
import ingredients.IngredientFactory;
import mealsystem.AddIngredient;
import mealsystem.Appertizer;
import mealsystem.AppertizerFactory;
import mealsystem.ChangeIngredient;
import mealsystem.Dessert;
import mealsystem.DessertFactory;
import mealsystem.MainCourse;
import mealsystem.MainCourseFactory;
import mealsystem.Meal;
import mealsystem.MealFactory;
import mealsystem.AbstractMealFactory;
import mealsystem.NormalBehavior;
import orders.Order;
import users.*;


public class JOrderTest {
	User mathias = new ClientUser("Mathias", "Loh", "mathiasloh", "12345");
	Order order = new Order(mathias);
	/**
	 * Loads all predefined ingredients
	 * Constructors: Ingredient(String name, double priceperquantity), Ingredient(String name, double quantity, double priceperquantity)
	 */
	IngredientFactory ingredient = new IngredientFactory();
	Ingredient vegetable = ingredient.createIngredient("vegetable", 1, 1);
	Ingredient meat = ingredient.createIngredient("Meat", 2);

	/**
	 * Loads all predefined meals
	 * Constructors: Meal(String name, String description, Ingredients args(ingredients))
	 */

	AbstractMealFactory meal = new MealFactory();
	MealFactory appertizers = new AppertizerFactory();
	MealFactory maincourses = new MainCourseFactory();
	MealFactory desserts = new DessertFactory();
	
	Appertizer salad = (Appertizer) appertizers.createMeal("salad", "good for your health", 1, vegetable);
	MainCourse steak = (MainCourse) maincourses.createMeal("salad", "also good for you", vegetable);
	Dessert icecream = (Dessert) desserts.createMeal("icecream", "good for you", 10, vegetable);

	
	@Test
	public void testSaveOrderGiveCorrectTotalTransactionWithoutPersonalization(){
		order.selectMeal(icecream, 1);
		order.selectMeal(salad, 2);
		order.selectMeal(steak, 1);
		order.saveOrder();
		assertTrue(order.getTotalTransaction()==icecream.getDefaultprice()+salad.getDefaultprice()*2+steak.getDefaultprice());
		
	}
	
	@Test
	public void testSaveOrderGiveCorrectTotalTransactionWithPersonalization(){
		order.selectMeal(icecream, 1);
		order.selectMeal(salad, 2);
		order.personalizeMeal(steak, meat, 100);
		order.addPersonalizedMeal(steak);
		order.saveOrder();
		assertTrue(order.getTotalTransaction()==icecream.getDefaultprice()+salad.getDefaultprice()*2+steak.getDefaultprice()+100*meat.getPriceperquantity());
	}
	
	@Test
	public void testNewOrderOfModifiedMealShouldGiveDefaultPrice(){
		Order order1 = new Order(mathias);
		Meal pers = (Meal) order1.personalizeMeal(steak, meat, 100);
		order1.addPersonalizedMeal(pers);
		order1.saveOrder();
		double trans1 = order1.getTotalTransaction();
		Order order2 = new Order(mathias);
		order2.selectMeal(steak, 1);
		order2.saveOrder();
		double trans2 = order2.getTotalTransaction();
		assertTrue((trans1==trans2));
		Order.resetAllStaticData();
	}
	
	@Test
	public void testOrderAsItIs(){
		Order.resetAllStaticData();
		Order order1 = new Order(mathias);
		Meal pers = (Meal) order1.personalizeMeal(steak, meat, 100);
		order1.addPersonalizedMeal(pers);
		order1.saveOrder();
		Order order2 = new Order(mathias);
		order2.selectMeal(steak, 3);
		order2.saveOrder();
		assertTrue(Order.getOrders_as_it_is().size() == 4);
		System.out.println("Order as it is...");
		System.out.println(Order.showMealsAsItIs());

	}
	
	@Test
	public void testOrderMostModified(){
		Meal meal = (Meal) order.personalizeMeal(steak, meat, 100);
		order.addPersonalizedMeal(meal);
		order.addPersonalizedMeal(meal);
		order.addPersonalizedMeal(meal);
		order.saveOrder();
		Meal meal1 = (Meal) order.personalizeMeal(icecream, meat, 100);
		order.addPersonalizedMeal(meal1);
		order.addPersonalizedMeal(meal1);
		order.addPersonalizedMeal(meal1);
		order.addPersonalizedMeal(meal1);
		order.addPersonalizedMeal(meal1);
		order.saveOrder();
		assertTrue(Order.getOrders_most_modified().get(meal)==3);
		assertTrue(Order.getOrders_most_modified().get(meal1)==5);
		System.out.println("Order as most modified...");
		System.out.println(Order.showMealAsMostModified());
	}

	@Test
	public void testOrderJustOnSale(){
		icecream.setSpecialOfferToggle(true);
		icecream.setSpecialPrice(1000);
		order.selectMeal(icecream, 2);
		order.saveOrder();
		assertTrue(Order.getOrders_when_special_offer().size()==2);
		icecream.setSpecialOfferToggle(false);
		order.selectMeal(icecream, 3);
		assertTrue(Order.getOrders_when_special_offer().size()==2);
		System.out.println("Order as it is on sale...");
		System.out.println(Order.showMealJustOnSale());
	}
}
