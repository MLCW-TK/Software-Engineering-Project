package CardFidelitySystem;

import Users.User;

public class BasicFidelityCard implements FidelityCard {
	User owner;
	
	public BasicFidelityCard(User owner){
		this.owner = owner;
		owner.setFidelityCard(this);
	}
	
	public boolean UseFeature() {
		this.owner.setCanReceiveSpecialOffers(true);
		return true;
	}
}
