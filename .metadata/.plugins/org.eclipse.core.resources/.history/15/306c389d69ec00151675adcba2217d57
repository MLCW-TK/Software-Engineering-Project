package enjoyyourmeal;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class ClientUserTest {
		HashMap<String, String> TestHash = new HashMap<String, String>();
	@Test
	public void testHashCode() {
		// first initialization.
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		// second initialization. 
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		ClientUser z = new ClientUser("firstname3", "lastname3", "username", "password3", "kyd1992@gmail.com", TestHash);
		// Tests: w, x, y, z all different objects
		assertNotSame(w, x);
		assertNotSame(w, y);
		assertNotSame(w, z);
		assertNotSame(x, y);
		assertNotSame(x, z);
		assertNotSame(y, z);
		// Tests: But are equals based on definition (usernames are the same)
	    assertTrue(w.equals(x) && x.equals(w));
	    assertTrue(y.hashCode() == z.hashCode());
	    assertTrue(x.equals(y) && y.equals(x));
	    assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testClientUser() {
		
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		if (w.equals(null) || !(w instanceof ClientUser)){
			fail("ClientUser is not implemented");
		}
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		if (y.equals(null) || !(y instanceof ClientUser)){
			fail("ClientUser is not implemented");
		}
		
		ClientUser x = new ClientUser("firstname2", "lastname2", "username", "password2", TestHash);
		if (x.equals(null) || !(x instanceof ClientUser)){
			fail("ClientUser is not implemented");
		}
		
		ClientUser z = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com");
		if (z.equals(null) || !(z instanceof ClientUser)){
			fail("ClientUser is not implemented");
		}
		
	}

	@Test
	public void testGetUsername() {
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		if (!w.getUsername().equals("username")){
			fail("getUsername is not working");
		}
	}

	@Test
	public void testGetUsertype() {
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		if (!w.getUsertype().equals("Client")){
			fail("getUsertype is not working");
		}
	}

	@Test
	public void testGetPassword() {
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		if (!w.getPassword().equals("password")){
			fail("getPassword is not working");
		}
	}

	@Test
	public void testGetEmail() {
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password", "kyd1992@gmail.com", TestHash);
		if (!w.getEmail().equals("kyd1992@gmail.com")){
			fail("getEmail is not working");
		}
	}

	@Test
	public void testGetContactHash() {
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		assertTrue(y.getContactHash().equals(TestHash));
	}

	@Test
	public void testGetContactKeys() {
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		assertTrue(y.getContactKeys().equals(TestHash.keySet().toString()));
	}

	@Test
	public void testGetContactValue() {
		TestHash.put("value1", "value2");
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		assertTrue(y.getContactValue("value1").equals(TestHash.get("value1")));
	}

	@Test
	public void testSetReceiveUpdates() {
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		y.setReceiveUpdates(true);
		assertTrue(y.getReceiveUpdates() == true);
		y.setReceiveUpdates(false);
		assertTrue(y.getReceiveUpdates() == false);
	}

	@Test
	public void getReceiveUpdates(){
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		y.setReceiveUpdates(true);
		assertTrue(y.getReceiveUpdates() == true);
		y.setReceiveUpdates(false);
		assertTrue(y.getReceiveUpdates() == false);
	}
	
	@Test
	public void testSetEmail() {
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		x.setEmail("kyd1992@gmail.com");
		assertTrue(x.getEmail().equals("kyd1992@gmail.com"));
	}

	@Test
	public void testGetFirstname() {
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		assertTrue(x.getFirstname().equals("firstname1"));
	}

	@Test
	public void testSetFirstname() {
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		x.setFirstname("Mathias");
		assertTrue(x.getFirstname().equals("Mathias"));
	}

	@Test
	public void testSetReceiveAddress() {
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		y.setReceiveAddress("here");
		assertTrue(y.getReceiveAddress().equals("here"));
	}

	@Test
	public void testGetReceiveAddress() {
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		y.setReceiveAddress("here");
		assertTrue(y.getReceiveAddress().equals("here"));
	}

	@Test
	public void testGetLastname() {
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		x.setLastname("Mathias");
		assertTrue(x.getLastname().equals("Mathias"));
	}

	@Test
	public void testSetLastname() {
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		x.setLastname("Mathias");
		assertTrue(x.getLastname().equals("Mathias"));
	}

	@Test
	public void testEqualsObject() {
		// first initialization.
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		ClientUser x = new ClientUser("firstname1", "lastname1", "username", "password1");
		// second initialization. 
		ClientUser y = new ClientUser("firstname2", "lastname2", "username", "password2", "kyd19924@gmail.com", TestHash);
		ClientUser z = new ClientUser("firstname3", "lastname3", "username", "password3", "kyd1992@gmail.com", TestHash);
		// Tests: w, x, y, z all different objects
		assertNotSame(w, x);
		assertNotSame(w, y);
		assertNotSame(w, z);
		assertNotSame(x, y);
		assertNotSame(x, z);
		assertNotSame(y, z);
		// Tests: But are equals based on definition (usernames are the same)
	    assertTrue(w.equals(x) && x.equals(w));
	    assertTrue(y.hashCode() == z.hashCode());
	    assertTrue(x.equals(y) && y.equals(x));
	    assertTrue(x.hashCode() == y.hashCode());
	}

	@Test
	public void testToString() {
		ClientUser w = new ClientUser("firstname", "lastname", "username", "password");
		assertTrue(w.toString() instanceof String);
	}

}
