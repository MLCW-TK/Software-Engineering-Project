package users;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import cardfidelitysystem.BasicFidelityCard;
import cardfidelitysystem.FidelityCard;
import orders.Order;
import update.Subscriber;

public abstract class User implements Subscriber{
	private String first_name;
	private String last_name;
	protected String user_name;
	private String password;
	protected String email;
	private HashMap<String, String> contact = new HashMap<String,String>();
	boolean receive_updates = false;
	boolean receive_birthdayOffer = false;
	private String receive_address = this.email;
	protected int amount_spent;
	protected Order currentOrder = new Order();
	protected ArrayList<Order> userOrders = new ArrayList<Order>();
	protected boolean canReceiveSpecialOffers = true;
	protected FidelityCard membershipCard = new BasicFidelityCard(this);
	@SuppressWarnings("deprecation")
	private Date birthday = new Date(1000,1,1);
	// Region - constructors

	public User(String firstname, String lastname, String username, String password, String email, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.amount_spent = 0;
	}
	
	public User(String firstname, String lastname, String username, String password, String email, HashMap<String,String> contact, Date birthday){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = contact;
		this.amount_spent = 0;
		this.setBirthday(birthday);
	}
	
	public User(String firstname, String lastname, String username, String password, String email){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = new HashMap<String,String>();
	}
	
	public User(String firstname, String lastname, String username, String password, String email, Date birthday){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = email;
		this.contact = new HashMap<String,String>();
		this.setBirthday(birthday);
	}
	
	public User(String firstname, String lastname, String username, String password, HashMap<String,String> contact){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = contact;
	}
	
	public User(String firstname, String lastname, String username, String password, HashMap<String,String> contact, Date birthday){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = contact;
		this.setBirthday(birthday);
	}

	public User(String firstname, String lastname, String username, String password){
		this.first_name = firstname;
		this.last_name = lastname;
		this.user_name = username;
		this.password = password;
		this.email = "";
		this.contact = new HashMap<String,String>();
		this.receive_address = email;
	}
	// EndRegion
	
	
	// getters and setters
	
	public void setFidelityCardByString(String name){
		
	}
	public Order getCurrentOrder(){
		return this.currentOrder;
	}
	
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
	
	/**
	 * Ask the user if he wants to receive updates, and register with instructions.
	 */
	public void registerUpdates(){
		System.out.println("Would you like to receive updates? ('yes', or press 'enter' to skip)");
		Scanner sc2 = new Scanner(System.in);
		String response = sc2.nextLine();
		if (response.equalsIgnoreCase("YES")){
			this.setReceiveUpdates(true);
			this.setReceiveAddress(email);
			/** 
			 * Checks if either email or contact details were given
			 * If none were given, prompt the user to give either email address or contact address
			 * If other contacts were given, asks the user to specify the receive method.
			 */
			if (email.equals("")){
				if (this.contact.isEmpty()){
					while (this.getEmail().equals("") && this.getContactHash().isEmpty()){
						System.out.println("No contact details found");
						System.out.println("Where would you like us to send you updates?");
						System.out.println("Type '1' to enter an email address, or '2' to enter other contact details manually");
						String ans = sc2.nextLine();
						switch(ans){
						case "1":
							System.out.println("Please enter your email address: ");
							email = sc2.nextLine();
							if (email.equals("")){
								continue;
							} else {
								this.setEmail(email);
								this.setReceiveAddress(email);
								System.out.println("We will send you updates by email from now on");
								break;
							}
						case "2":
							System.out.println("Please enter a contact field description");
							String value1 = sc2.nextLine();
							System.out.println("Please enter a contact field value");
							String value2 = sc2.nextLine();
							if (value1.equals("") || value2.equals("")){
								continue;
							}
							this.contact.put(value1, value2);
							this.setReceiveAddress(this.getContactHash().get(value1));
							System.out.println("We will send updates to " + value1 + " from now on");
							break;
							
						default:
							System.out.println("Invalid input. Please try again");
							break;
						}
					}
				}
					else{
						while (this.getReceiveAddress().equals("")){
							System.out.println("No email address set for updates notification");
							System.out.println("Type '1' if you would like to add an email address to receive updates, or type '2' if you like to choose from other contact details");
							String NotifyResponse = sc2.nextLine();
							switch(NotifyResponse){
							case "1":
								System.out.println("Please enter an email address");
								email = sc2.nextLine();
								if (email.equals("")){
									continue;
								} else {
									this.setEmail(email);
									this.setReceiveAddress(email);
									System.out.println("Email updated. You will receive email updates from now on");
									break;
								}
							case "2":
								System.out.println("Please choose from the list below");
								System.out.println(this.getContactKeys());
								String ContactKey = sc2.nextLine();
						
								while (true){
									if (this.getContactHash().containsKey(ContactKey)){
										this.setReceiveAddress(this.getContactValue(ContactKey));
										System.out.println("We will send updates to " + ContactKey + " from now on");
										break;
									}
									else{
										System.out.println("Wrong input selected. Please try again");
										ContactKey = sc2.nextLine();
									}
								}
								break;
							default:
								System.out.println("Invalid response. Please try again");
								break;
							}
						}
						}
				}else {
					if (!this.getContactHash().isEmpty()){
						System.out.println("Would you like to change how you would like to receive updates? (yes/no, default = email)");
						String response2 = sc2.nextLine();
							if (response2.equalsIgnoreCase("YES")){
								System.out.println("Please enter one of your contact types shown below");
								System.out.println(this.getContactKeys());
								String ContactKey = sc2.nextLine();
							while (true){
								if (this.getContactHash().containsKey(ContactKey)){
									this.setReceiveAddress(this.getContactValue(ContactKey));
									break;
								}
								else{
									System.out.println("Wrong input selected. Please try again");
									ContactKey = sc2.nextLine();
								}
							}	
						}
					} else {
						System.out.println("We will send you email updates if any!");
					}
				}
		}
	}

	//Region - birthday
	public void setBirthday(Date date){this.birthday = date;}
	
	public Date getBirthday(){return this.birthday;}
	
	
	public void setReceiveBirthdayOffer(Boolean bool){
		if(this.getReceiveUpdates()){
			this.receive_birthdayOffer = bool;
		}else{
			if(bool){
				System.out.println("You have not agreed to receive our updates.");
				registerUpdates();
				if(this.getReceiveUpdates()){this.setReceiveBirthdayOffer(bool);}
				else{
					this.setReceiveBirthdayOffer(false);
					if(!this.getReceiveUpdates()){
						System.out.println("We have not registered you for birthday offer because you have not agreed to receive our updates");
					}
				}
			}
		}
	}
	
	public boolean getReceiveBirthdayOffer(){return this.receive_birthdayOffer;}
	
	/**
	 * Ask the user if he wants to receive birthday special offer, and register with instructions.
	 */
	public void registerBirthday(){
		System.out.println("Would you like to receive our special offers on your birthday?(Type 'yes' to accept.)");
		Scanner sc = new Scanner(System.in);
		String response = sc.nextLine();
		if (!response.equalsIgnoreCase("YES")){
			this.receive_birthdayOffer=false;
			System.out.println("You have chosen not to receive birthday offers.");}
		else{
			this.recordBirthday();
			this.setReceiveBirthdayOffer(true);
			System.out.println("You will receive a special offer on your birthday!");
		}	
	}
	
	/**
	 * record the birthday of user in the correct format.
	 */
	public void recordBirthday(){
		do{
			System.out.println("We need to know your birthday, please type it down in dd/mm/yyyy format.(e.g. 21/07/1993)");
			Scanner sc = new Scanner(System.in);
			String birthdayString = sc.nextLine();
			if(birthdayString.length()==10){
				try{
					int day = Integer.parseInt(birthdayString.substring(0, 2));
					int month = Integer.parseInt(birthdayString.substring(3, 5));
					int year = Integer.parseInt(birthdayString.substring(6, 10));
					if(day>0 && day<32 && month>0 && month<13 && year>1000 && year < 10000){
						Date temp = new Date(year-1900, month-1, day);
						this.birthday = temp;
					}
					else{System.out.println("Birthday formate is unrecognized, please follow the dd/mm/yyyy format.(e.g. 21/07/1993)");}
				}catch(Exception e){
					System.out.println("Birthday formate is unrecognized, please follow the dd/mm/yyyy format.(e.g. 21/07/1993)");}
			}
			else{
				System.out.println("Birthday formate is unrecognized, please follow the dd/mm/yyyy format.(e.g. 21/07/1993)");
			}
		}while(this.birthday.equals(new Date(1000,1,1)));
		
	}
	
	public boolean birthdayIsToday(){
		int bDay = this.getBirthday().getDate();
		int bMonth = this.getBirthday().getMonth();
		Date today = new Date();
		return today.getDate()==bDay && today.getMonth()==bMonth;
	}
	//EndRegion
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
