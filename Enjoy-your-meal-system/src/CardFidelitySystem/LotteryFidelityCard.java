package CardFidelitySystem;

import java.util.Random;

import Users.User;

public class LotteryFidelityCard implements FidelityCard{
	User owner;
	Random rand = new Random();
	int percentage;
	
	public LotteryFidelityCard(User owner, int percentage){
		this.owner = owner;
		this.percentage = percentage;
		owner.setCanReceiveSpecialOffers(false);
		owner.setFidelityCard(this);
	}
	
	public void setPercentage(int percentage){
		this.percentage = percentage;
	}

	public boolean UseFeature() {
		int roll = rand.nextInt(100) + 1;
		if (roll < percentage){
			return true;
		} else {
			return false;
		}
	}

}
