package ingredients;

import customutilities.CustomUtilities;

/**
 * Ingredient class
 * @author Xiong
 *
 */
public class Ingredient {
	private String name;
	private double quantity;
	private double priceperquantity = 0;
	private double totalprice = 0;
	private double original_quantity;

	/**
	 * Ingredient constructor
	 * @param name
	 * @param quantity
	 */
	public Ingredient(String name, double quantity){
		this.setName(name);
		this.setQuantity(quantity);
		this.setOriginal_Quantity(quantity);
	}
	


	/**
	 * Ingredient constructor, with priceperquantity set
	 * @param name
	 * @param quantity
	 * @param priceperquantity
	 */
	public Ingredient(String name, double quantity, double priceperquantity){
		this.setName(name);
		this.setQuantity(quantity);
		this.setPriceperquantity(priceperquantity);
		this.totalprice = CustomUtilities.round(this.priceperquantity * this.quantity,2);
		this.setOriginal_Quantity(this.getQuantity());

		
		if (this.quantity < 0){
			throw new RuntimeException("Quantity cannot be less than 0");
		}
	}
	
	/**
	 * create a new instance of identical ingredient
	 * @return
	 */
	public Ingredient createnewinstance(){
		return new Ingredient(this.name, this.quantity);
	}
	
	/**
	 * Original_quantity setter
	 * @param quantity
	 */
	public void setOriginal_Quantity(double quantity) {
		this.original_quantity = quantity;
		
	}
	 
	/**
	 * OriginalQuantity getter
	 * @return
	 */
	public double getOriginalQuantity(){
		return original_quantity;
	}
	
	/**
	 * add a certain quantity to this ingredient
	 * and update the total price accordingly
	 * @param quantity
	 */
	public void addQuantity(double quantity){
		this.quantity = quantity;
		this.totalprice = CustomUtilities.round(priceperquantity * this.quantity,2);
	}
	
	/**
	 * remove certain quantity from this ingredient,
	 * and update the total price accordingly
	 * @param quantity
	 */
	public void removeQuantity(double quantity){
		if (this.quantity-quantity < 0){
			throw new RuntimeException("Quantity cannot be less than 0");
		} else {
			this.quantity = quantity;
			this.totalprice = CustomUtilities.round(priceperquantity * this.quantity,2);
		}
	}

	/**
	 * Pricerperquanttiy getter
	 * @return
	 */
	public double getPriceperquantity() {
		return priceperquantity;
	}

	
	/**
	 * Priceperquantity setter
	 * @param priceperquantity
	 */
	public void setPriceperquantity(double priceperquantity) {
		this.priceperquantity = priceperquantity;
	}

	/**
	 * return total price with two decimal places
	 * @return
	 */
	public double getTotalprice() {
		this.totalprice = CustomUtilities.round(this.quantity*this.priceperquantity,2);
		return totalprice;
	}

	/**
	 * totalPrice setter
	 * @param totalprice
	 */
	public void setTotalprice(double totalprice) {
		this.totalprice = CustomUtilities.round(totalprice,2);
	}

	/**
	 * name setter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * quantity setter
	 * update total price accordingly
	 * @param quantity
	 */
	public void setQuantity(double quantity) {
		if (quantity >= 0){
			this.quantity = quantity;
			this.totalprice = CustomUtilities.round(this.quantity * this.priceperquantity,2);
		} else {
			throw new RuntimeException("Quantity cannot be lesser than 0!");
		}
	}

	
	/**
	 * name getter
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * quantity getter
	 * @return
	 */
	public double getQuantity(){
		return this.quantity;
	}
	
	
	
	// Equals and hash
	/**
	 * Check if the name is the same
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Ingredient){
			Ingredient c1 = (Ingredient) o; 
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
		return (code + (int) this.quantity);
	}
	
	
}
