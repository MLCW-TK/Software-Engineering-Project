package MealSystem;

import static org.junit.Assert.*;

import org.junit.Test;

public class MealsTest {
	// TestIngredient = TestIngredient1
	Ingredient TestIngredient = new Ingredient("test", 1.56);
	Ingredient TestIngredient1 = new Ingredient("test2", 1.56);
	// TestIngredient2 != TestIngredient, != TestIngredient1
	Ingredient TestIngredient2 = new Ingredient("test2", 2);
	Meals testMeal = new Meals("Chicken rice", "Singapore delicacy", 3.56, TestIngredient, TestIngredient2);
	@Test
	public void testConstuctors() {
		assertTrue(testMeal.getIngredientsString().equals("[test, test2]"));
		assertTrue(testMeal.getPrice() == 3.56);
		Ingredient TestIngredient3 = new Ingredient("test3", 3);
		testMeal.addIngredient(TestIngredient3);
		assertTrue(testMeal.getPrice()==6.56);
		assertTrue(testMeal.getTotalIngredientPrice()==6.56);
		assertTrue(testMeal.getIngredientsString().equals("[test, test2, test3]"));
		assertTrue(testMeal.isSpecialOffer()==false);
		testMeal.setSpecialOfferToggle(true);
		assertTrue(testMeal.isSpecialOffer()==true);
		testMeal.setSpecialPrice(5.69);
		assertTrue(testMeal.getPrice()==5.69);
		Ingredient TestIngredient4 = new Ingredient("test4", 3);
		testMeal.addIngredient(TestIngredient4);
//		assertTrue(testMeal.getPrice()==8.69);
//		testMeal.removeIngredient(TestIngredient4);
//		assertTrue(testMeal.getPrice()==5.69);
//		testMeal.changeIngredientQuantity(TestIngredient3, 0.5);
//		assertTrue(testMeal.getPrice()==4.19);
//		testMeal.setSpecialOfferToggle(false);
//		assertTrue(testMeal.getPrice()==6.56-1.5);		
	}
	
	@Test(expected = RuntimeException.class)
	public void testForNoSpecialOfferYetException(){
		testMeal.getSpecialPrice();
	}
	
	@Test(expected = RuntimeException.class)
	public void testForNoSpecialOfferSetYetException(){
		testMeal.setSpecialPrice(5);
	}
	
	@Test(expected = RuntimeException.class)
	public void testAlreadyIncludedIngredient(){
		testMeal.addIngredient(TestIngredient1);
		testMeal.addIngredient(TestIngredient);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCannotRemoveIngredient(){
		Ingredient TestIngredient3 = new Ingredient("test3", 3);
		testMeal.removeIngredient(TestIngredient3);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCannotChangeIngredient(){
		Ingredient TestIngredient3 = new Ingredient("test3", 3);
		testMeal.changeIngredientQuantity(TestIngredient3,5);
	}
	

	@Test
	public void testRound(){
		double x = 6.96969696;
		if (TestIngredient.round(x,2)!=6.97){
			fail("Not working");
		}
	}
}
