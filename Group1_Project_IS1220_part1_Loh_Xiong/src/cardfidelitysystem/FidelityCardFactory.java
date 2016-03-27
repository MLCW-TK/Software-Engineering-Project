package cardfidelitysystem;

import java.util.ArrayList;
import java.util.HashMap;

import users.ClientUser;

public class FidelityCardFactory {
	HashMap<String, FidelityCard> cardtypes = new HashMap<String, FidelityCard>();

	
	public FidelityCard createFidelityCard(String cardName, ClientUser user){
		if (cardName == "BasicFidelityCard"){
			return new BasicFidelityCard(user);
		}
		if (cardName == "LotteryFidelityCard"){
			return new LotteryFidelityCard(user);
		}
		if (cardName == "PointFidelityCard"){
			return new PointFidelityCard(user);
		}
		throw new RuntimeException("No cardtype found!");
	}
}
