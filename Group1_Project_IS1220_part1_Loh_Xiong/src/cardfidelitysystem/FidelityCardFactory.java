package cardfidelitysystem;

import java.util.ArrayList;
import java.util.HashMap;

import users.ClientUser;
/**
 * The factory class of Fidelity cards
 * @author Xiong
 *
 */
public class FidelityCardFactory {
	HashMap<String, FidelityCard> cardtypes = new HashMap<String, FidelityCard>();

	
	/**
	 * make a instance of fidelity card of chosen type.
	 * @param cardName
	 * @param user
	 * @return
	 */
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
