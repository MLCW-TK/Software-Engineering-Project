package cardfidelitysystem;

import java.util.Random;

import users.User;


/**
 * Lottery fidelity card
 *	
 */
public class LotteryFidelityCard extends FidelityCard{
	Random rand = new Random();
	private boolean canUseFeature = true;
	int percentage;

	/**
	 * constructor which specifies the percentage
	 * @param owner: the user
	 * @param percentage: % chance of striking "lottery"
	 */
	public LotteryFidelityCard(User owner, int percentage){
		super(owner, percentage);
		name = "LotteryFidelityCard";
	}

	/**
	 * constructor without specifying the percentage
	 * @param owner
	 */
	public LotteryFidelityCard(User owner){
		super(owner);
		this.percentage = 5;
		name = "LotteryFidelityCard";
	}

	/**
	 * setter for percentage
	 * @param percentage
	 */
	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	/**
	 * This use feature rolls between 1 and 100.
	 * If your number that you get is < than the percentage set
	 * You get a free meal
	 * Once the feature is used, it cannot be used again unless the system restarts.
	 */
	@Override
	public boolean useFeature() {
		if (this.isCanUseFeature()){
			int roll = rand.nextInt(100) + 1;
			if (roll < percentage){
				this.setCanUseFeature(false);
				return true;
			} else {
				return false;
			}
		}else{return false;}
	}

	/**
	 * check if the card's feature can be used today
	 * @return
	 */
	public boolean isCanUseFeature() {
		return canUseFeature;
	}

	/**
	 * setter for canUseFeature
	 * @param canUseFeature
	 */
	public void setCanUseFeature(boolean canUseFeature) {
		this.canUseFeature = canUseFeature;
	}

}
