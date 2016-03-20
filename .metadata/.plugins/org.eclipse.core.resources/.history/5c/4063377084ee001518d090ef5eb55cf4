package cardFidelitySystem;

import users.User;

public class BasicFidelityCard extends FidelityCard {
	
	public BasicFidelityCard(User owner){
		super(owner);
	}

	@Override
	public boolean useFeature() {
		this.owner.setCanReceiveSpecialOffers(true);
		return true;
	}
}
