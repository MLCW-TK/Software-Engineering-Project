package CardFidelitySystem;

import java.util.Random;

import Users.User;

public class LotteryFidelityCard extends FidelityCard{
	Random rand = new Random();
	int percentage;
	

	public LotteryFidelityCard(User owner, int percentage){
		super(owner, percentage);
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
