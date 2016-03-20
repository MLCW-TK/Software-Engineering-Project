package users;

import java.util.Date;
import java.util.HashMap;

import update.Subscriber;
/**
 * Class of ClientUser
 * @author MLCW, Xiong
 *
 */
public class ClientUser extends User{
	protected String user_type = "Client";
	
	/**
	 * ClientUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 * @param contact
	 */
	public ClientUser(String firstname, String lastname, String username, String password, String email, HashMap<String,String> contact){
		super(firstname, lastname, username, password, email, contact);
		this.user_type = "Client";
	}
	
	/**
	 * ClientUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 */
	public ClientUser(String firstname, String lastname, String username, String password, String email){
		super(firstname, lastname, username, password, email);
		this.user_type = "Client";
	}
	
	/**
	 * ClientUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param contact
	 */
	public ClientUser(String firstname, String lastname, String username, String password, HashMap<String,String> contact){
		super(firstname, lastname, username, password, contact);
		this.user_type = "Client";
	}
	
	/**
	 * ClientUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 */
	public ClientUser(String firstname, String lastname, String username, String password){
		super(firstname, lastname, username, password);
		this.user_type = "Client";
	}
	
	public String getUsertype(){
		return this.user_type;
	}


}
