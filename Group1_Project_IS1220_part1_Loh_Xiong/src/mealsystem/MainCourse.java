package mealsystem;

import ingredients.Ingredient;

/**not used yetn
 * MainCourse class
 * this is a subclass of Meal
 * @author Xiong
 *
 */
public class MainCourse extends Meal {

	/**
	 * MainCourse constructor
	 * @param name
	 * @param description
	 * @param price
	 * @param ingredients
	 */
	public MainCourse(String name, String description, double price, Ingredient[] ingredients) {
		super(name, description, price, ingredients);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * MainCourse constructor
	 * @param name
	 * @param description
	 * @param ingredients
	 */
	public MainCourse(String name, String description, Ingredient[] ingredients) {
		super(name, description, ingredients);
	}
	
	/**
	 * MainCourse constructor
	 * @param name
	 * @param price
	 */
	public MainCourse(String name, double price){
		super(name, price);
	}
	
	/**
	 * check if equal
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof MainCourse){
			MainCourse c1 = (MainCourse) o; 
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
