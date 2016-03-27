package mealsystem;

import ingredients.Ingredient;

public class Meal extends AbstractMeal {

	public Meal(String name, String description, double price, Ingredient[] ingredients) {
		super(name, description, price, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Meal(String name, String description, double price, MealBehavior behavior, Ingredient[] ingredients){
		super(name, description, price, behavior, ingredients);
	}
	
	public Meal(String name, String description, Ingredient[] ingredients) {
		super(name, description, ingredients);
	}
	
	public Meal(String name, double price){
		super(name, price);
	}
	@Override
	public boolean equals(Object o){
		if (o instanceof Meal){
			Meal c1 = (Meal) o; 
			return (c1.getName().equals(this.name));
		}
		return false;
	}
	
	/**
	 * overridden hashCode method
	 */	
	@Override
	public int hashCode(){
    	int code = 0;
        for (int i=0; i < this.name.length(); i++){
        	char c = this.name.charAt(i);
        	int h = 41+((int)c+i)*(19+i);
        	code += h;
        }
		return code;
	}
}
