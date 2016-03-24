package ingredients;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class IngredientTest {
	Ingredient TestIngredient = new Ingredient("test1", 1, 1.56);
	
	@Test
	public void testConstructorsAndEquivalence(){
		Ingredient TestIngredient1 = new Ingredient("test1", 1.56);
		assertTrue(TestIngredient1 instanceof Ingredient);
		assertTrue(TestIngredient1.getQuantity() == 1);
		assertTrue(TestIngredient1.getTotalprice() == TestIngredient1.getPriceperquantity()*TestIngredient1.getQuantity());
		assertTrue(TestIngredient1.getName() == "test1");
		Ingredient TestIngredient2 = new Ingredient("test1", 1.56);
		assertTrue(TestIngredient1.equals(TestIngredient2));
		assertTrue(TestIngredient2.equals(TestIngredient1));
		assertTrue(TestIngredient1.equals(TestIngredient));
		assertTrue(TestIngredient.equals(TestIngredient1));
		assertTrue(TestIngredient2.equals(TestIngredient));
		assertTrue(TestIngredient.equals(TestIngredient2));
		
		Set<Ingredient> testUniqueness = new HashSet<Ingredient>();
		testUniqueness.add(TestIngredient);
		testUniqueness.add(TestIngredient1);
		testUniqueness.add(TestIngredient2);
		assertTrue(testUniqueness.size() == 1);		
	}
	
	@Test(expected = RuntimeException.class)
	public void ConstructorQuantityCannotBeNegative() {
		Ingredient TestIngredient1 = new Ingredient("test", -0.0001, 1.56);
		fail("quantity cannot be negative");
	}
	
	@Test(expected = RuntimeException.class)
	public void CannotRemoveZeroQuantity(){
		TestIngredient.removeQuantity(2);
		fail("quantity cannot be negative");
	}

}
