package enjoyyourmeal.Meals;

public class Ingredient {
	public String name;
	public Integer quantity;
	public Ingredient(String name, Integer quantity){
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Integer getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	
}
