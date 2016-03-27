package mealsystem;

import ingredients.Ingredient;

public class Dessert extends AbstractMeal{

	public Dessert(String name, String description, double price, Ingredient[] ingredients) {
		super(name, description, price, ingredients);
		// TODO Auto-generated constructor stub
	}

	public Dessert(String name, String description, Ingredient[] ingredients) {
		super(name, description, ingredients);
	}

	public Dessert(String name, double price){
		super(name, price);
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof Dessert){
			Dessert c1 = (Dessert) o; 
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
