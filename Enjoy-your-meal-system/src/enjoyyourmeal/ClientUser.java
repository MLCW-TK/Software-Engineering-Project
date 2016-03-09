package enjoyyourmeal;

import java.util.HashMap;

public class ClientUser{
	private String first_name;
	private String last_name;
	protected String user_name;
	private String password;
	protected String email;
	private HashMap<String, String> contact;
	boolean receive_updates = false;
	private String receive_address;;
	protected String user_type;
	
	public ClientUser(String firstname, String lastname, String username, String password, String email, HashMap<String, String> contact){
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.receive_address = email;
		this.user_type = "Client";
	}
	
	protected String getUsername(){
		return this.user_name; 
	}
	
	protected String getUsertype(){
		return this.user_type;
	}
	
	protected String getPassword(){
		return this.password;
	}
	
	protected String getEmail(){
		return this.email;
	}
	
	protected HashMap<String, String> getContactHash(){
		return contact;
	}
	
	protected String getContact(){
		return this.contact.keySet().toString();
	}
	
	protected String getContactValue(String ContactKey){
		return contact.get(ContactKey);
	}
	
	public void setReceiveUpdates(boolean bool){
		this.receive_updates = bool;
	}
	
	protected void setEmail(String email){
		this.email = email;
	}
	
	protected String getFirstname() {
		return first_name;
	}

	protected void setFirstname(String first_name) {
		this.first_name = first_name;
	}
	
	protected void setReceiveAddress(String receive_address){
		this.receive_address = receive_address;
	}
	
	protected String getReceiveAddress(){
		return this.receive_address;
	}
	
	protected String getLastname() {
		return last_name;
	}

	protected void setLastname(String last_name) {
		this.last_name = last_name;
	}
	
	// Equals and hash
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
		return 41*((user_name).length()+19);
	}


}
