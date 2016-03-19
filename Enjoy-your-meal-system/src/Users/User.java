package Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import CardFidelitySystem.BasicFidelityCard;
import CardFidelitySystem.FidelityCard;
import Orders.Order;
import update.Subscriber;

public abstract class User implements Subscriber{
	private String first_name;
	private String last_name;
	protected String user_name;
	private String password;
	protected String email;
	private HashMap<String, String> contact = new HashMap<String,String>();
	boolean receive_updates = false;
	private String receive_address = this.email;
	protected int amount_spent;
	protected ArrayList<Order> userOrders = new ArrayList<Order>();
	protected boolean canReceiveSpecialOffers = true;
	protected FidelityCard membershipCard = new BasicFidelityCard(this);
	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 * @param contact
	 */
	public User(String firstname, String lastname, String username, String password, String email, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.amount_spent = 0;
	}
	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String firstname, String lastname, String username, String password, String email){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = new HashMap<String,String>();
	}
	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 * @param contact
	 */
	public User(String firstname, String lastname, String username, String password, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = contact;;
	}
	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param username
	 * @param password
	 */
	public User(String firstname, String lastname, String username, String password){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = new HashMap<String,String>();
		this.receive_address = email;
	}
	
	// getters and setters
	public void setFidelityCard(FidelityCard fidelitycard){
		this.membershipCard = fidelitycard;
	}
	
	public ArrayList<Order> getOrders(){
		return this.userOrders;
	}
	
	public FidelityCard getFidelityCard(){
		return this.membershipCard;
	}
	
	public void setCanReceiveSpecialOffers(boolean bool){
		this.canReceiveSpecialOffers = bool;
	}
	
	public boolean getCanReceiveSpecialOffers(){
		return this.canReceiveSpecialOffers;
	}
	
	public String getUsername(){
		return this.user_name; 
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public HashMap<String, String> getContactHash(){
		return contact;
	}
	
	public String getContactKeys(){
		return this.contact.keySet().toString();
	}
	
	public String getContactValue(String ContactKey){
		return contact.get(ContactKey);
	}
	
	public void setReceiveUpdates(boolean bool){
		this.receive_updates = bool;
	}
	
	public boolean getReceiveUpdates(){
		return this.receive_updates;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstname() {
		return first_name;
	}

	public void setReceiveAddress(String receive_address){
		this.receive_address = receive_address;
	}
	
	public String getReceiveAddress(){
		return this.receive_address;
	}
	
	public String getLastname() {
		return last_name;
	}
	
	public String toString(){
		String s = new String();
		s += "Name: " + this.first_name + " " + this.last_name;
		s += "\r\n";
		s += "Username: " + this.user_name;
		s += "\r\n";
		s += "Email: " + this.email;
		s += "\r\n";
		if (!this.contact.isEmpty()){
			for (String contacts: this.contact.keySet()){
				s += contacts + ": " + contact.get(contacts);
				s += "\r\n";
			}
		}
		s = s.substring(0, s.length()-2);
		return s;
	}
	// Equals and hash
	/**
	 * Check if the user_name is the same
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof ClientUser){
			ClientUser c1 = (ClientUser) o; 
			return (c1.getUsername().equals(this.user_name));
		}
		return false;
	}
	/**
	 * overridden hashCode method
	 */
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
