package mealSystem;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import ingredients.Ingredient;
import ingredients.IngredientFactory;

public class MealsTestTest {

	@Test
	public void test() {
		MealFactory appertizers = new AppertizerFactory();
		MealFactory maincourses = new MainCourseFactory();
		MealFactory desserts = new DessertFactory();
		
		IngredientFactory ingredient = new IngredientFactory();
		Ingredient vegetable = ingredient.createIngredient("vegetable", 1, 1.15);
		
		Appertizer salad = (Appertizer) appertizers.createMeal("salad", "good for your health", 0, vegetable);
		MainCourse steak = (MainCourse) maincourses.createMeal("salad", "also good for you", vegetable);
		Dessert icecream = (Dessert) desserts.createMeal("icecream", "good for you", 10, vegetable);
		HashSet meal_list = new HashSet<Meal>();
		
		assertTrue(!salad.equals(steak));
		meal_list.add(salad);
		meal_list.add(steak);
		meal_list.add(icecream);
		assertTrue(meal_list.size() == 3);
		
	}

}
