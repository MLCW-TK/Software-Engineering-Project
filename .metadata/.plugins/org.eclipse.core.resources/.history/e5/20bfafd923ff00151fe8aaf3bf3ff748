package test;

import static org.junit.Assert.*;

import org.junit.Test;

import cardfidelitysystem.BasicFidelityCard;
import cardfidelitysystem.LotteryFidelityCard;
import cardfidelitysystem.PointFidelityCard;
import users.ClientUser;
import users.User;

public class JFidCardTest {
	
	// Test Basic Fidelity Card
	@Test 
	public void testBasicUseFeature() {
		User user = new ClientUser("firstname", "lastname", "username", "password");
		BasicFidelityCard f = new BasicFidelityCard(user);
		f.useFeature();
		assertTrue(user.getCanReceiveSpecialOffers());
	}

	// Test Lottery Fidelity Card
	@Test 
	public void testLotteryUseFeature() {
		User user = new ClientUser("firstname", "lastname", "username", "password");
		LotteryFidelityCard f = new LotteryFidelityCard(user);
		f.setPercentage(50);
		boolean result = false;
		int i =0;
		while (!result&&i<1000){
			result = f.useFeature();
			i++;
		}
		assertTrue(result);
	}
	
	// Test Point Fidelity Card
	@Test
	public void testPointAdd_Cash_as_Points(){
		User user = new ClientUser("firstname", "lastname", "username", "password");
		PointFidelityCard f = new PointFidelityCard(user);
		f.Add_Cash_as_Points(1000);
		assertTrue(f.getDiscountTickets()>=1);
	}
	
	@Test
	public void testPointUseFeature(){
		User user = new ClientUser("firstname", "lastname", "username", "password");
		PointFidelityCard f = new PointFidelityCard(user);
		f.Add_Cash_as_Points(1000);
		assertTrue(f.useFeature());
		assertTrue(f.getDiscountTickets()<1);
	}
}
