package mealsystem;

import ingredients.Ingredient;

/**
 * Meal class
 * this is a subclass of AbstractMeal
 * @author Xiong
 *
 */
public class Meal extends AbstractMeal {

	/**
	 * Meal constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
	public Meal(String name, String description, double price, Ingredient[] ingredients) {
		super(name, description, price, ingredients);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Meal constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param behavior
	 * @param ingredients
	 */
	public Meal(String name, String description, double price, MealBehavior behavior, Ingredient[] ingredients){
		super(name, description, price, behavior, ingredients);
	}
	
	/**
	 * Meal constructor
	 * @param name
	 * @param description
	 * @param ingredients
	 */
	public Meal(String name, String description, Ingredient[] ingredients) {
		super(name, description, ingredients);
	}
	
	/**
	 * Meal constructor
	 * @param name
	 * @param price
	 */
	public Meal(String name, double price){
		super(name, price);
	}
	
	/**
	 * check if equal
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Meal){
			Meal c1 = (Meal) o; 
			return (c1.getName().equals(this.getName()));
		}
		return false;
	}
	
	/**
	 * overridden hashCode method
	 */	
	@Override
	public int hashCode(){
    	int code = 0;
        for (int i=0; i < this.getName().length(); i++){
        	char c = this.getName().charAt(i);
        	int h = 41+((int)c+i)*(19+i);
        	code += h;
        }
		return code;
	}
}
