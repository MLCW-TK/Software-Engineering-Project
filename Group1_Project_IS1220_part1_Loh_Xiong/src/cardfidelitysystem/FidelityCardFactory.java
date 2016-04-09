package cardfidelitysystem;

import java.util.ArrayList;
import java.util.HashMap;

import users.ClientUser;

public class FidelityCardFactory {
	HashMap<String, FidelityCard> cardtypes = new HashMap<String, FidelityCard>();

	
	public FidelityCard createFidelityCard(String cardName, ClientUser user){
		if (cardName.equals("BasicFidelityCard")){
			return new BasicFidelityCard(user);
		}
		if (cardName.equals("LotteryFidelityCard")){
			return new LotteryFidelityCard(user);
		}
		if (cardName.equals("PointFidelityCard")){
			return new PointFidelityCard(user);
		}
		throw new RuntimeException("No cardtype found!");
	}
}
