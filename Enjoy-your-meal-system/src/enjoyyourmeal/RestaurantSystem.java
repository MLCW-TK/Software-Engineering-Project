package enjoyyourmeal;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RestaurantSystem {
	String name;
	boolean registration_phase = true;
	boolean user_phase = true;
	Set<Users> user_list;
	
	public RestaurantSystem(String restaurant_name){
		this.name = restaurant_name;
		this.user_list = new HashSet<Users>();
	}
	
	public String getRestaurantName(){
		return this.name;
	}
	
	public void addUser(Users newUser){
		user_list.add(newUser);
	}
	
	public boolean getRegistrationPhase(){
		return this.registration_phase;
	}
	
	public void setRegistrationPhase(boolean bool){
		registration_phase = bool;
	}
	
	public void printUsers(){
		for (Users users:user_list){
			System.out.println(users);
		}
	}
	
	public void register(){
		Scanner sc = new Scanner(System.in);
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
		if (email.toUpperCase() == "NO"){email = null;}
		System.out.println("Please enter a postal address: (type 'no' to skip)");
		String address = sc.nextLine();
		if (address.toUpperCase()=="NO"){address=null;}
		ClientUser newUser = new ClientUser(first_name, last_name, username, password, email, address);
		System.out.println("Would you like to receive updates? (yes/no)");
		String response = sc.nextLine();
		if (response.toUpperCase()=="YES"){
			newUser.setReceiveUpdates(true);
		}
		System.out.println("Would you like to set which");
		
		if (this.user_list.isEmpty()){
			this.user_list.add(newUser);
			System.out.println("Registration successful! Please login in the main menu");
		}
		
		else if (user_list.contains(newUser)){
			System.out.println("User already exist, please retry again, or log in with the main menu");
		}
		else{
			user_list.add(newUser);
			System.out.println("Registration successful! Please login from the main menu");
		}
	}	
}
