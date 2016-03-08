package enjoyyourmeal;

import java.util.Random;

public class ClientUser implements Users{
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private String email;
	private String address;
	boolean receive_updates = false;
	private String receive_address = email;
	
	public ClientUser(String firstname, String lastname, String username, String password, String email, String address){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	
	public String getUsername(){
		return this.user_name; 
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setReceiveUpdates(boolean bool){
		this.receive_updates = bool;
	}
	
	
	// Equals and hash
	@Override
	public boolean equals(Object o){
		if (o instanceof Users){
			ClientUser c1 = (ClientUser) o; 
			return (c1.getUsername() == this.user_name || c1.getEmail() == this.email);
		}
		return false;
	}
	
	public int hashCode(){
		return 41;
	}
}
