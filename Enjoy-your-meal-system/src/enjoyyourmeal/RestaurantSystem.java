package enjoyyourmeal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RestaurantSystem {
	String name;
	boolean registration_phase = true;
	boolean user_phase = true;
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
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> contact_details = new HashMap<String, String>();
		
		System.out.println("Please enter your first name: ");
		String first_name = sc.nextLine();
		System.out.println("Please enter your last name: ");
		String last_name = sc.nextLine();
		System.out.println("Please enter your desired username: ");
		String username = sc.nextLine();
		System.out.println("Please enter your password: ");
		String password = sc.nextLine();
		System.out.println("Please enter add an email address: (type 'no' to skip)");
		String email = sc.nextLine();
		if (email.equalsIgnoreCase("NO")){email = "";}
		System.out.println("Please enter a postal address: (type 'no' to skip)");
		String address = sc.nextLine();
		if (address.equalsIgnoreCase("NO")){address="";}
		if (!address.equalsIgnoreCase("")){
			contact_details.put("Postal address", address);
		}
		
		System.out.println("Would you like to include additional contact details? (yes/no)");
		String prompt = sc.nextLine();
		if (prompt.equalsIgnoreCase("YES")){
			while (true){
			System.out.println("Please enter a contact field description (type 'none' to return)");
			String value1 = sc.nextLine();
			if(value1.equalsIgnoreCase("NONE")){
				break;
			}
			System.out.println("Please enter a contact field value");
			String value2 = sc.nextLine();
			contact_details.put(value1, value2);
			System.out.println("Would you like to include additional contact details? (yes/no)");
			}
		}
		
		ClientUser newUser = new ClientUser(first_name, last_name, username, password, email, contact_details);
		System.out.println(newUser.hashCode());
		System.out.println("Would you like to receive updates? (yes/no)");
		String response = sc.nextLine();
		if (response.equalsIgnoreCase("YES")){
			newUser.setReceiveUpdates(true);
			if (email == ""){
				if (contact_details.isEmpty()){
					System.out.println("No contact details found. Please enter your email address");
					email = sc.nextLine();
					newUser.setEmail(email);
					newUser.setReceiveAddress(email);
				}
				else{					
					System.out.println("No email address set. Please choose your specified contact details");
					System.out.println(newUser.getContact());
					String ContactKey = sc.nextLine();
					
					while (true){
						if (newUser.getContactHash().containsKey(ContactKey)){
							newUser.setReceiveAddress(newUser.getContactValue(ContactKey));
							break;
						}
						else{
							System.out.println("Wrong input selected. Please try again");
							ContactKey = sc.nextLine();
						}
					}
				}
			} else {
				System.out.println("Would you like to change how you would like to receive updates? (yes/no, default = email");
				String response2 = sc.nextLine();
				if (response2.equalsIgnoreCase("YES")){
					System.out.println(newUser.getContact());
					String ContactKey = sc.nextLine();
					while (true){
						if (newUser.getContactHash().containsKey(ContactKey)){
							newUser.setReceiveAddress(newUser.getContactValue(ContactKey));
							break;
						}
						else{
							System.out.println("Wrong input selected. Please try again");
							ContactKey = sc.nextLine();
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
				System.out.println("User/Email already exist, please retry again, or log in with the main menu");
			}
			else{
				user_list.add(newUser);
				System.out.println("Fucked up here");
			}
			
		}
	}	
	
	public void login(String username, String password){
		
	}
}
