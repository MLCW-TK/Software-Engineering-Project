package users;

import java.util.HashMap;

/**
 * StaffUser class as a subclass of ClientUser class
 * @author MLCW, Xiong
 *
 */
public class StaffUser extends ClientUser {
	
	/**
	 * StaffUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 * @param contact
	 */
	public StaffUser(String firstname, String lastname, String username, String password, String email,
			HashMap<String, String> contact) {
		super(firstname, lastname, username, password, email, contact);
		this.user_type = "Staff";
	}
	
	/**
	 * StaffUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 */
	public StaffUser(String firstname, String lastname, String username, String password, String email) {
		super(firstname, lastname, username, password, email);
		this.user_type = "Staff";
	}
	
	/**
	 * StaffUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param contact
	 */
	public StaffUser(String firstname, String lastname, String username, String password,
			HashMap<String, String> contact) {
		super(firstname, lastname, username, password,contact);
		this.user_type = "Staff";
	}
	
	/**
	 * StaffUser constructor
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 */
	public StaffUser(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
		this.user_type = "Staff";
	}
	
	@Override
	public boolean equals(Object o){
		if (o instanceof ClientUser){
			ClientUser c1 = (ClientUser) o; 
			return (c1.getUsername().equals(this.user_name));
		}
		return false;
	}
	
	@Override
	public int hashCode(){
    	int code = 0;
        for (int i=0; i < user_name.length(); i++){
        	char c = user_name.charAt(i);
        	int h = 41+((int)c+i)*(19+i);
        	code += h;
        }
		return code;
	}
}

