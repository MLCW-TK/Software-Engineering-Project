package coresystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import mealsystem.AbstractMeal;
import update.Publisher;
import update.Subscriber;
import users.ClientUser;
import users.StaffUser;
import users.User;

public class RestaurantSystem implements Publisher{
    String message = "";
	String name;
	boolean registration_phase = true;
	boolean user_phase = true;
	boolean exit = false;
	Set<ClientUser> user_list;
	private ArrayList<Subscriber> subscriber_list = new ArrayList<Subscriber>();
	public HashSet<AbstractMeal> meal_list = new HashSet<AbstractMeal>();
	public String birthdaySpecialOffer = "30% discount!";
	public double birthdayOffer = 0.3;
	private String staffKey = "EYMSresto";
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
	public Set<ClientUser> getUserList(){
		return user_list;
	}
	
	public ClientUser getUserFromUserName(String USERNAME){
		for (ClientUser obj : this.user_list){
			if (obj.getUsername().equals(USERNAME)){
				return obj;
			}
		}
		return null;
	}
	
	public ClientUser validateUser(String USERNAME, String PASSWORD){
		ClientUser user = null;
		for (ClientUser obj : this.user_list){
			if (obj.getUsername().equals(USERNAME) && obj.getPassword().equals(PASSWORD)){
				if (user instanceof StaffUser){
					user = (StaffUser) obj;
				} else {
					user = (ClientUser) obj;
				}
			}
		}
		if (user.equals(null)){
			throw new RuntimeException("User not found!");
		} else {
			return user;
		}
	}
	
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
		
		// Double password checker
		String password;
		String passwordConfirm;
		int count = 1;
		do {
			if (count!=1){
				System.out.println("Password incorrect, please try again");
			}
			System.out.println("Please enter your password: ");
			password = sc2.nextLine();
			/**
			 *  Enter password twice to confirm it.
			 *  If the two passwords do not correspond, the system asks the user to repeat the process.
			 */
			System.out.println("Please confirm your password by re-entering it: ");
			passwordConfirm = sc2.nextLine();
			count += 1;
		} while (!password.equals(passwordConfirm));
		// -----------------------------------
		
		// Email prompt
		System.out.println("Please enter add an email address: (press 'enter' to skip)");
		String email = sc2.nextLine();
		// -----------------------------------

		
		// Additional details prompt
		/**
		 * Prompts user for additional detail
		 * It will loop to prompt the user for details until he does not say 'yes'
		 */
		boolean additional_details_state = true;
		boolean contact_description_state = false;
		boolean contact_value_state = false;
		String field_tester = new String();
		String contact_field_description = new String();
		String contact_field_value = new String();
		
		while (additional_details_state){
			int state_counter = 1;
			if (!(state_counter > 1)){
			System.out.println("Would you like to include additional contact details?");
			System.out.println("Type 'yes' to continue, or press any key to skip");
			String prompt = sc2.nextLine();
			if (!prompt.equalsIgnoreCase("YES") && !prompt.equalsIgnoreCase("Y")){
				additional_details_state = false;
				continue;
				}
			}
			// State of entering description
			contact_description_state = true;
			while (contact_description_state){	
				int counter = 1;
				if (counter > 1){
					System.out.println("Contact field error. Please try again.");
				}
				// Checks contact field description validity. Breaks to start of detail phase if
				// “none” was returned. Loop to ask contact field description is empty
				do{
				System.out.println("Please enter a valid contact field description");
				System.out.println("NOTE: Empty values are not allowed. You may type 'none' to exit");
				contact_field_description = sc2.nextLine();
				
				// Checks for entries with only whitespace
				field_tester = contact_field_description;
				field_tester.replaceAll("\\+","");
				
				counter += 1;
				} while (field_tester.length()==0 && !contact_field_description.equalsIgnoreCase("NONE"));
				
				contact_description_state = false;
				continue;
			}
			
			
			if (contact_field_description.equalsIgnoreCase("NONE")){
				continue;
			}
			
			// Checks contact field value validity. Works similarly to description state
			contact_value_state = true;
			while (contact_value_state){
				int counter = 1;
				if (counter > 1){
					System.out.println("Contact value error. Please try again.");
				}
				// Checks contact field description validity. Breaks to start of detail phase if
				// none was returned. Loop to ask contact field description is empty
				do{
				System.out.println("Please enter a valid contact field value");
				System.out.println("NOTE: Empty values are not allowed. You may type 'none' to exit");
				contact_field_value = sc2.nextLine();	
				
				// Checks for entries with only whitespace
				field_tester = contact_field_value;
				field_tester.replaceAll("\\s","");
				
				counter += 1;
				} while (field_tester.length() == 0 && !contact_field_value.equalsIgnoreCase("NONE"));
				
				contact_value_state = false;
				continue;
			}

			if (contact_field_value.equalsIgnoreCase("NONE")){
				continue;
			}

			
			// Asks if user wishes to use add additional details
			contact_details.put(contact_field_description, contact_field_value);
			System.out.println("Additional details created successfully. Would you like to include additional details?");
			System.out.println("Type 'yes' to enter new details, or press any key to continue");
			String prompt2 = sc2.nextLine();
			if(!prompt2.equalsIgnoreCase("YES")){
				additional_details_state = false;
				continue;
				} else {
					state_counter += 1;
					continue;
				}
			}
		

			/**
			 * Create a ClientUser object with parameters taken in the previous steps.
			 */
			ClientUser newUser = new ClientUser(first_name, last_name, username, password, email, contact_details);
			newUser.registerUpdates();
			newUser.registerBirthday();
			
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
			} 
			else {
				break;
			}
		}
	}	
	
	public void register(String firstname, String lastname, String username, String password, String email, String dob, String staffKey){
		if (staffKey.equals(this.staffKey)){
			User newStaff = new StaffUser(firstname, lastname, username, password, email);
			this.addUser((ClientUser) newStaff);
		}
		if (!staffKey.equals(this.staffKey)){
			User newClient = new ClientUser(firstname, lastname, username, password, email);
			this.addUser((ClientUser) newClient);
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
		throw new RuntimeException("No user found!");
	}
	
	Set<ClientUser> getUser_list(){
		return this.user_list;
	}
	
	ArrayList<Subscriber> getSubscriber_list(){
		return this.subscriber_list;
	}
	
	public HashSet<AbstractMeal> getMeal_list(){
		return this.meal_list;
	}
//Region - update
	@Override
	public void subscribe(Subscriber sub) {
		subscriber_list.add(sub);
	}

	@Override
	public void unsubscribe(Subscriber unsub) {
		int ind = (subscriber_list).indexOf(unsub);
		subscriber_list.remove(ind);	
	}
	@Override
	public void notifySubscriber() {
		String offers = "";
		String screenMessage = "";
		if(!this.getMeal_list().isEmpty()){
			offers = this.allMealOffer();
			for(Subscriber sub :  this.getSubscriber_list()){
				if(!((User)sub).birthdayIsToday()){	
					String userMessage = "From: "+this.getRestaurantName()+"\n"
											+"To: "+((User)sub).getReceiveAddress() +"\n"
											+"Message: "+normaldayOffer(sub)+"\n";
					screenMessage = screenMessage + userMessage;
				}
				else{
					String userMessage = "From: "+this.getRestaurantName()+"\n"
							+"To: "+((User)sub).getReceiveAddress() +"\n"
							+"Message: "+birthdayOffer(sub)+"\n";
					screenMessage = screenMessage + userMessage;
				}
			}

			System.out.println(screenMessage);
		}
		else{System.out.println("Meal_list is empty!");}
		
		
	}


	@Override
	public void notifySubscriber(String message, String mealName) {
		String offers = "";
		String screenMessage = "";
		if(!this.getMeal_list().isEmpty()){
            for(AbstractMeal meal : this.getMeal_list()){
                if(meal.getName().equalsIgnoreCase(mealName)&&meal.isSpecialOffer()){
                        offers = offers+"Meal: "+meal.getName()+"\n"
                            +"Special price: "+meal.getSpecialPrice()+"$\n";
                        break;
                }
            }
		}else{System.out.println("Meal_list is empty!");}
		
		if (!this.getSubscriber_list().isEmpty()){
            for(Subscriber sub :  this.getSubscriber_list()){
					String userMessage = "From: "+this.getRestaurantName()+"\n"
							+"To: "+((User)sub).getReceiveAddress() +"\n"
							+message
							+"\n"+offers+"\n";
					screenMessage = userMessage;
            }
			System.out.println(screenMessage);
		}else{System.out.println("No subscriber!");}
	}
	

	
	// modified from notifySubscribers()
	public void notifyBirthday(){
		String offers = "";
		String screenMessage = "";
		if(!this.getMeal_list().isEmpty()){
			offers = this.allMealOffer();
			for(Subscriber sub :  this.getSubscriber_list()){
				if(((User)sub).birthdayIsToday()){	
					String userMessage = "From: "+this.getRestaurantName()+"\n"
							+"To: "+((User)sub).getReceiveAddress() +"\n"
							+"Message: "+birthdayOffer(sub)+"\n";
					screenMessage = screenMessage + userMessage;
				}
			}

			System.out.println(screenMessage);
		}
		else{System.out.println("Meal_list is empty!");}
		
	}

	public void popupMessage(Subscriber sub){
		if(sub.getReceiveUpdates()){
			if(((User) sub).getReceiveBirthdayOffer()){
				System.out.println(this.birthdayOffer(sub));}
			else{System.out.println(this.normaldayOffer(sub));}
		}
	}
	
	public String birthdayOffer(Subscriber sub){
		String message = new String();
		message = message
				+ "Happy Birthday! Dear "+((User)sub).getFirstname() +" " + ((User) sub).getLastname()
				+ ",\n"+ "Would you like to spend your birthday with "+this.getRestaurantName()+"?\n"
				+ "We offer you "+ this.getbirthdaySpecialOffer();
		return message;
	}
	
	public String allMealOffer(){
		String offers = new String();
		for(AbstractMeal meal: this.getMeal_list()){
			if(meal.isSpecialOffer()){
				offers = offers + meal.toString();
			}
		}
		return offers;
	}
	public String normaldayOffer(Subscriber sub){
		String message = new String();
		message = message 
				+ "Goodday! Dear " + ((User)sub).getFirstname() +" " + ((User) sub).getLastname() 
				+",\n"+ "Here are our new offers:\n\n"
				+ allMealOffer();
		return message;
	}
	
	public void setbirthdaySpecialOffer(String offer){this.birthdaySpecialOffer = offer;}
	public String getbirthdaySpecialOffer(){return this.birthdaySpecialOffer;}
	public void refresh(){
//		System.out.println(this.getSubscriber_list().toString());
		System.out.println("System refreshing subscriber list...");
		// refresh the subscriber_list according to the state of clients
		if(!this.getUser_list().isEmpty()){
			for(Subscriber client: this.getUser_list()){
				if(client.getReceiveUpdates() && !this.getSubscriber_list().contains(client)){
					try{
						Subscriber sub = (Subscriber)client;
						subscribe(sub);
					}catch(Exception e){
						System.out.println("error1");
					}
				}else{continue;}	
				
				if(!client.getReceiveUpdates() && this.getSubscriber_list().contains(client)){
					try{
						
						Subscriber sub = (Subscriber)client;
						unsubscribe(sub);
					}catch(Exception e){
						System.out.println("error2");
					}
				}else{continue;}
			}
		}
		System.out.println("Refreshing finished");
//		System.out.println(this.getSubscriber_list().toString());

	}
//EndRegion

	public void setMessage(String message) {
            this.message = message;
	}

	public String getMessage() {
            return this.message;
	}

	
	// print a list of meals
	public String printMeal_list() {
		String mealString = "";
		for (AbstractMeal meal : this.getMeal_list()){
			mealString +="Name: "+meal.getName()+"\n"+"$"+meal.getPrice()+"\n"+meal.getDescription()+"\n\n";
		};
		return mealString;
	}
	
}
