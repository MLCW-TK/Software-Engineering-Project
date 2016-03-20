package cardfidelitysystem;

import users.User;

public class BasicFidelityCard extends FidelityCard {
	/**
	 * Creates a new basic fidelity card
	 * @param owner
	 */
	public BasicFidelityCard(User owner){
		super(owner);
	}

	/**
	 * Allows the user to receive special offers whenever one is avaliable
	 */
	@Override
	public boolean useFeature() {
		this.owner.setCanReceiveSpecialOffers(true);
		return true;
	}
}
