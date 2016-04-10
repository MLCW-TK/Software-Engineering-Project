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
	public String name;
	
	/**
	 * constructor
	 * @param owner
	 */
	public FidelityCard(User owner){
		this.owner = owner;
		owner.setFidelityCard(this);
	}
	
	/**
	 * return the cardname 
	 * @return
	 */
	public String getCardName(){
		return this.name;
	}
	
	
	/**
	 * constructor for lottery fidelity card only
	 * with the percentage of getting a free meal set
	 * @param owner
	 * @param percentage
	 */
	public FidelityCard(User owner, int percentage){
		this.owner = owner;
		this.percentage = percentage;
		owner.setCanReceiveSpecialOffers(false);
		owner.setFidelityCard(this);
	}
	


	/**
	 * use the feature of the fidelity card
	 * @return
	 */
	public abstract boolean useFeature();
}
