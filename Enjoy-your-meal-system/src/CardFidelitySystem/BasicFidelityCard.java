package CardFidelitySystem;

import Users.User;

public class BasicFidelityCard extends FidelityCard {
	
	public BasicFidelityCard(User owner){
		super(owner);
	}
	
	public boolean UseFeature() {
		this.owner.setCanReceiveSpecialOffers(true);
		return true;
	}
}
