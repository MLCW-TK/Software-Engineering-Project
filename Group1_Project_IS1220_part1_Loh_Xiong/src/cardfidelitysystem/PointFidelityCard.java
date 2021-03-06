package cardfidelitysystem;

import orders.Order;
import users.User;

/**
 * Point fidelity card implementation
 *
 */
public class PointFidelityCard extends FidelityCard{
	int points = 0;
	double totalAmountSpent;
	double totalAmountProcessed;
	int discountTickets = 0;
	boolean canGetDiscount = false;
	double discountRate = 0.10;	
	public PointFidelityCard(User owner){
		super(owner);
		owner.setCanReceiveSpecialOffers(false);
		name = "PointFidelityCard";

	}

	/**
	 * getter for discountRate
	 * @return
	 */
	public double getDiscountRate(){
		return this.discountRate;
	}
	
	/**
	 * update the totalAmountSpent
	 */
	public void update(){
		totalAmountSpent = 0;
		for (Order userorder : this.owner.getOrders()){
			totalAmountSpent += userorder.getTotalTransaction();
		}
	}
	
	/**
	 * update the points of this PointFidelityCard
	 */
	public void updatePoints(){
		update();
		double outstandingAmounts = this.totalAmountSpent - this.totalAmountProcessed;
		this.points += outstandingAmounts/10;
		
		if (this.points > 100){
			discountTickets++;
			canGetDiscount = true;
			this.points = this.points - 100;
		}	
	}
	
	
	/**
	 * convert an amount of cash to points 
	 * @param amount
	 */
	public void Add_Cash_as_Points(double amount){
		this.totalAmountProcessed += amount;
		this.points += amount/10;
		
		if (this.points >= 100){
			discountTickets++;
			canGetDiscount = true;
			this.points = this.points - 100;
		}
		
	}
	
	/**
	 * Checks if user eligible for discount
	 * If yes, set eligibility to false
	 * returns true
	 */
	@Override
	public boolean useFeature(){
		if (canGetDiscount){
			discountTickets -= 1;
			if (!(discountTickets > 0)){
				canGetDiscount = false;
			}
			return true;
		}
		return false;
		
	}

	public int getDiscountTickets() {
		return this.discountTickets;
	}
	
	
}
