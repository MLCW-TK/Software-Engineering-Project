package orders;

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
import mealsystem.MealFactory;
import mealsystem.AbstractMealFactory;
import mealsystem.NormalBehavior;
import users.*;


public class OrderTest {

	@Test
	public void test() throws CloneNotSupportedException {
		Order Ordersystem = new Order();
		User Mathias = new ClientUser("Mathias", "Loh", "mathiasloh", "12345");
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
		
		// Tests for personalizeMeal
		// Tests for selectMeal
		// Tests for saveOrder
	}

}
