package CardFidelitySystem;

import Orders.Order;
import Users.User;

public class PointFidelityCard extends FidelityCard{
	int points = 0;
	double totalAmountSpent;
	double totalAmountProcessed;
	int discountTickets = 0;
	boolean canGetDiscount = false;
	
	public PointFidelityCard(User owner){
		super(owner);
		owner.setCanReceiveSpecialOffers(false);

	}

	public void update(){
		totalAmountSpent = 0;
		for (Order userorder : this.owner.getOrders()){
			totalAmountSpent += userorder.getTotalTransaction();
		}
	}
	
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
	
	public boolean UseFeature(){
		if (canGetDiscount){
			return true;
		}
		return false;
		
	}
	
	
}
