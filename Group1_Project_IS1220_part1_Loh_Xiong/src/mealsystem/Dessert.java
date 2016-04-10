package mealsystem;

import ingredients.Ingredient;

/**
 * Dessert class
 * this is a subclass of the Meal class
 * @author Xiong
 *
 */
public class Dessert extends Meal{

	/**
	 * Dessert constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
	public Dessert(String name, String description, double price, Ingredient[] ingredients) {
		super(name, description, price, ingredients);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Dessert constructor
	 * @param name
	 * @param description
	 * @param ingredients
	 */
	public Dessert(String name, String description, Ingredient[] ingredients) {
		super(name, description, ingredients);
	}

	/**
	 * Dessert constructor
	 * @param name
	 * @param price
	 */
	public Dessert(String name, double price){
		super(name, price);
	}
	
	/**
	 * check if equal
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Dessert){
			Dessert c1 = (Dessert) o; 
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
