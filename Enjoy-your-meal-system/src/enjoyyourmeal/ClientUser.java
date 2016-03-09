package enjoyyourmeal;

import java.util.HashMap;

public class ClientUser{
	private String first_name;
	private String last_name;
	private String user_name;
	private String password;
	private String email;
	private HashMap<String, String> contact;
	boolean receive_updates = false;
	private String receive_address = email;
	
	public ClientUser(String firstname, String lastname, String username, String password, String email, HashMap<String, String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
	}
	
	public String getUsername(){
		return this.user_name; 
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public HashMap<String, String> getContactHash(){
		return contact;
	}
	
	public String getContact(){
		String s = new String();
		for (String values: this.contact.keySet()){
			s += values + " ";
		}
		return s;
	}
	
	public String getContactValue(String ContactKey){
		return contact.get(ContactKey);
	}
	
	public void setReceiveUpdates(boolean bool){
		this.receive_updates = bool;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setReceiveAddress(String receive_address){
		this.receive_address = receive_address;
	}
	
	// Equals and hash
	@Override
	public boolean equals(Object o){
		if (o instanceof ClientUser){
			ClientUser c1 = (ClientUser) o; 
			return (c1.getUsername().equals(this.user_name) || c1.getEmail().equals(this.email));
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return 41*((user_name+email).length()+19);
	}
}
