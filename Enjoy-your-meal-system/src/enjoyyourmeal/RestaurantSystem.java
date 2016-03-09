package enjoyyourmeal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RestaurantSystem{
	String name;
	boolean registration_phase = true;
	boolean user_phase = true;
	boolean exit = false;
	private Set<ClientUser> user_list;
	/**
	 * This is the constructor of RestaurantSystem.
	 * @param restaurant_name
	 */
	public RestaurantSystem(String restaurant_name){
		this.name = restaurant_name;
		this.user_list = new HashSet<ClientUser>();
	}
	/**
	 * Public method to add a ClientUser object to user_list
	 * @param newUser
	 */
	public void addUser(ClientUser newUser){
		user_list.add(newUser);
	}
	/**
	 * getRestaurantName
	 * @return
	 */
	public String getRestaurantName(){
		return this.name;
	}
	/**
	 * getRegistrationPhase
	 * @return
	 */
	public boolean getRegistrationPhase(){
		return this.registration_phase;
	}
	/**
	 * setRegistrationPhase
	 * @param bool
	 */
	public void setRegistrationPhase(boolean bool){
		registration_phase = bool;
	}
	/**
	 * getUserPhase
	 * @return
	 */
	public boolean getUserPhase(){
		return this.user_phase;
	}
	/**
	 * setUserPhase
	 * @param bool
	 */
	public void setUserPhase(boolean bool){
		user_phase = bool;
	}
	/**
	 * setExit
	 * @param bool
	 */
	public void setExit(boolean bool){
		exit = bool;
	}
	/**
	 * Public method to print users. It prints out the user names and email addresses of all users from user_list.
	 * If user_list is empty, it prints "None to print".
	 */
	public void printUsers(){
		if (!user_list.isEmpty()){
			for (ClientUser users:user_list){
				System.out.println(users.getUsername());
				System.out.println(users.getEmail());
			}
		} else {
			System.out.println("None to print");
		}
	}
	/**
	 * Public method to register as an user.
	 */
	public void register(){
		Scanner sc2 = new Scanner(System.in);
		/**
		 * contact_details is a HashMap<String, String>
		 */
		boolean register = true;
		while (register){
		HashMap<String, String> contact_details = new HashMap<String, String>();
		/**
		 * Sequence of instructions to register an user.
		 */
		System.out.println("Please enter your first name: ");
		String first_name = sc2.nextLine();
		System.out.println("Please enter your last name: ");
		String last_name = sc2.nextLine();
		System.out.println("Please enter your desired username: ");
		String username = sc2.nextLine();
		System.out.println("Please enter your password: ");
		String password = sc2.nextLine();
		/**
		 *  Enter password twice to confirm it.
		 *  If the two passwords do not correspond, the system asks the user to repeat the process.
		 */
		System.out.println("Please confirm your password by re-entering it: ");
		String passwordConfirmed = sc2.nextLine();
		while (!password.equals(passwordConfirmed)){
			System.out.println("Please make sure your password is entered correctly.");
			System.out.println("Please enter your password: ");
			password = sc2.nextLine();
			System.out.println("Please confirm your password by re-entering it: ");
			passwordConfirmed = sc2.nextLine();
		}
		System.out.println("Please enter add an email address: (press 'enter' to skip)");
		String email = sc2.nextLine();
		
		System.out.println("Would you like to include additional contact details? (yes/no)");
		String prompt = sc2.nextLine();
		// Here we accept "yes" and "y"
		if (prompt.equalsIgnoreCase("YES") || prompt.equalsIgnoreCase("Y")){
			/**
			 * Keeping asking the user for additional contact details until the user does not reply "yes".
			 */
			while (true){
			System.out.println("Please enter a contact field description (type 'none' to return)");
			String value1 = sc2.nextLine();
			if(value1.equalsIgnoreCase("NONE")){
				break;
			}
			System.out.println("Please enter a contact field value");
			String value2 = sc2.nextLine();
			contact_details.put(value1, value2);
			System.out.println("Would you like to include additional contact details? (yes/no)");
			prompt = sc2.nextLine();
			if(!prompt.equalsIgnoreCase("YES")){break;}
			}
		}
		/**
		 * Create a ClientUser object with parameters taken in the previous steps.
		 */
		ClientUser newUser = new ClientUser(first_name, last_name, username, password, email, contact_details);
		System.out.println("Would you like to receive updates? ('yes', or press 'enter' to skip)");
		String response = sc2.nextLine();
		if (response.equalsIgnoreCase("YES")){
			newUser.setReceiveUpdates(true);
			/** 
			 * Checks if either email or contact details were given
			 * If none were given, prompt the user to give either email address or contact address
			 * If other contacts were given, asks the user to specify the receive method.
			 */
			if (email.equals("")){
				if (contact_details.isEmpty()){
//					while (newUser.getEmail().equals("")){
//						// Give the ClientUser an option to only provide other contact detail at this step.
//						System.out.println("No contact details found. Please enter an email address: ");
//						email = sc2.nextLine();
//						newUser.setEmail(email);
//						newUser.setReceiveAddress(email);
//					}
//					if (email == ""){
//						// Ask for alternative contact information and set it as the ReceiveAddress.
//						System.out.println("We need at least one of your contact to update you our special offers. Please enter the contact field description: (email address, address, phone number...)");
//						String value1 = sc2.nextLine();
//						System.out.println("Please enter the contact field value");
//						String value2 = sc2.nextLine();
//						contact_details.put(value1, value2);
//						newUser.setReceiveAddress(value2);
//					}
					while (newUser.getEmail().equals("") && newUser.getContactHash().isEmpty()){
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
								newUser.setEmail(email);
								newUser.setReceiveAddress(email);
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
							contact_details.put(value1, value2);
							newUser.setReceiveAddress(newUser.getContactHash().get(value1));
							System.out.println("We will send updates to " + value1 + " from now on");
							break;
							
						default:
							System.out.println("Invalid input. Please try again");
							break;
						}
					}
				}
					else{
						boolean UnresolvedUpdateChoice = true;
						while (newUser.getReceiveAddress().equals("")){
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
									newUser.setEmail(email);
									newUser.setReceiveAddress(email);
									System.out.println("Email updated. You will receive email updates from now on");
									break;
								}
							case "2":
								System.out.println("Please choose from the list below");
								System.out.println(newUser.getContact());
								String ContactKey = sc2.nextLine();
						
								while (true){
									if (newUser.getContactHash().containsKey(ContactKey)){
										newUser.setReceiveAddress(newUser.getContactValue(ContactKey));
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
					if (!newUser.getContactHash().isEmpty()){
						System.out.println("Would you like to change how you would like to receive updates? (yes/no, default = email)");
						String response2 = sc2.nextLine();
							if (response2.equalsIgnoreCase("YES")){
								System.out.println("Please enter one of your contact types shown below");
								System.out.println(newUser.getContact());
								String ContactKey = sc2.nextLine();
							while (true){
								if (newUser.getContactHash().containsKey(ContactKey)){
									newUser.setReceiveAddress(newUser.getContactValue(ContactKey));
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
		
		System.out.println("Would you like to save this account? (yes/no)");
		String save = sc2.nextLine();
		if (save.equalsIgnoreCase("YES")){
			/**
			 * Adds created user to the list of users in the system
			 * If user already exist, tells the user that registration has failed.
			 * Else, lets the user know that registration has been successful.
			 */
			if (this.user_list.isEmpty()){
				this.user_list.add(newUser);
				System.out.println("Registration successful! Please login in the main menu");
				break;
			}
			else{
				if (user_list.contains(newUser)){
					System.out.println("Registration failed");
					System.out.println("User already exist, please retry again. or log in with the main menu");
					break;
				}
				else{
					user_list.add(newUser);
					System.out.println("Registration successful! Please login in the main menu");
					break;
				}	
			}
		} else {
			break;
		}
	}
	}	
	
	public ClientUser login(String username, String password){
		ClientUser result;
		for (ClientUser users : user_list){
			if(users.getUsername().equals(username) && users.getPassword().equals(password)){
				if (users instanceof StaffUser){
					result = (StaffUser) users;
					return result;
				}
				result = users;
				return result;
			}
		}
		return null;
	}

}
