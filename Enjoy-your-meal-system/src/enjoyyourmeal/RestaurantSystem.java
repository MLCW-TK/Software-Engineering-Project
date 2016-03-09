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
	
	public RestaurantSystem(String restaurant_name){
		this.name = restaurant_name;
		this.user_list = new HashSet<ClientUser>();
	}
	
	public String getRestaurantName(){
		return this.name;
	}
	
	public void addUser(ClientUser newUser){
		user_list.add(newUser);
	}
	
	public boolean getRegistrationPhase(){
		return this.registration_phase;
	}
	
	public void setRegistrationPhase(boolean bool){
		registration_phase = bool;
	}
	
	public boolean getUserPhase(){
		return this.user_phase;
	}
	
	public void setUserPhase(boolean bool){
		user_phase = bool;
	}
	
	public void setExit(boolean bool){
		exit = bool;
	}
	
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
	
	public void register(){
		Scanner sc2 = new Scanner(System.in);
		HashMap<String, String> contact_details = new HashMap<String, String>();
		
		System.out.println("Please enter your first name: ");
		String first_name = sc2.nextLine();
		System.out.println("Please enter your last name: ");
		String last_name = sc2.nextLine();
		System.out.println("Please enter your desired username: ");
		String username = sc2.nextLine();
		System.out.println("Please enter your password: ");
		String password = sc2.nextLine();
		System.out.println("Please enter add an email address: (press 'enter' to skip)");
		String email = sc2.nextLine();
		
		System.out.println("Would you like to include additional contact details? (yes/no)");
		String prompt = sc2.nextLine();
		if (prompt.equalsIgnoreCase("YES")){
			while (true){
			System.out.println("Please enter a contact field desc2ription (type 'none' to return)");
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
		
		ClientUser newUser = new ClientUser(first_name, last_name, username, password, email, contact_details);
		System.out.println("Would you like to receive updates? ('yes', or press 'enter' to skip)");
		String response = sc2.nextLine();
		if (response.equalsIgnoreCase("YES")){
			newUser.setReceiveUpdates(true);
			if (email == ""){
				if (contact_details.isEmpty()){
					System.out.println("No contact details found. Please enter your email address");
					email = sc2.nextLine();
					newUser.setEmail(email);
					newUser.setReceiveAddress(email);
				}
				else{					
					System.out.println("No email address set. Please choose your specified contact details");
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
				System.out.println("Would you like to change how you would like to receive updates? (yes/no, default = email)");
				String response2 = sc2.nextLine();
				if (response2.equalsIgnoreCase("YES")){
					System.out.println("Please type enter one of your contact types shown below");
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
			}
		}
		if (this.user_list.isEmpty()){
			this.user_list.add(newUser);
			System.out.println("Registration successful! Please login in the main menu");
		}
		else{
			if (user_list.contains(newUser)){
				System.out.println("User already exist, please retry again, or log in with the main menu");
			}
			else{
				user_list.add(newUser);
				System.out.println("Registration successful! Please login in the main menu");
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
