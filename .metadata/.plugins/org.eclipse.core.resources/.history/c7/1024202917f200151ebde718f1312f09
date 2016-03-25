package commandinterface;

import users.*;

public class CommandList {

	public Object register_user;

	public ClientUser register_user(String firstname, String lastname, String username, String password){
		ClientUser newUser = new ClientUser(firstname, lastname, username, password);
//		CommandConsole.re1.addUser(newUser);
		return newUser;
	}
	
	public ClientUser login(String username, String password){
		for (ClientUser user : CommandConsole.re1.getUserList()){
			if (user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			}
		}
		
		throw new RuntimeException("No user found!");
	}
}
