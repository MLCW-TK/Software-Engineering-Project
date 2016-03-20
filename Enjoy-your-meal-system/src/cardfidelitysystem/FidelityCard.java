package cardfidelitysystem;

import users.User;

/**
 * 
 * An abstract fidelity class.
 *
 */
public abstract class FidelityCard {
	public User owner;
	public int percentage;
	
	
	public FidelityCard(User owner){
		this.owner = owner;
		owner.setFidelityCard(this);
	}
	
	public FidelityCard(User owner, int percentage){
		this.owner = owner;
		this.percentage = percentage;
		owner.setCanReceiveSpecialOffers(false);
		owner.setFidelityCard(this);
	}
	


	public abstract boolean useFeature();
}
