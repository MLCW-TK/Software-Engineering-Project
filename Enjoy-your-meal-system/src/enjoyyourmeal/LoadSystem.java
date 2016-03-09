package enjoyyourmeal;

import java.util.Scanner;

public class LoadSystem extends Thread {
	String restaurant_name;
	ClientUser LoginUser;
	public LoadSystem(String name){
		restaurant_name = name;
	}
	
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		RestaurantSystem re1 = new RestaurantSystem(restaurant_name);
		StaffUser Chef = new StaffUser("Mathias", "Loh", "mathiasloh", "12345", "kyd1992", null);
		re1.addUser(Chef);
		while (!re1.exit){
			while (re1.getRegistrationPhase()){
				System.out.println("Welcome to " + re1.getRestaurantName() + " !");
				System.out.println("Please type 'register' to register a new user, or 'login' to create a new one");
				String input = sc.nextLine();		
				switch(input.toUpperCase()){
				case "REGISTER":
					re1.register();
					System.out.println("");
					break;
				case "LOGIN":
					System.out.println("Please enter your username:");
					String username = sc.nextLine();
					System.out.println("Please enter your password:");
					String password = sc.nextLine();
					LoginUser = re1.login(username, password);
					if (LoginUser == null){
						System.out.println("User not found. Please try again");
						System.out.println("");
						break;
					} else {
						re1.setRegistrationPhase(false);
						break;
					}
					
				default:
					System.out.println("Invalid input. Please try again");
					System.out.println("");
					continue;
				}
			}
			while (re1.getUserPhase()){
				// If is a staff
				if(LoginUser instanceof StaffUser){	
					//
					// Implementation of birthday here
					//
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!");
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. Insert a meal");
					System.out.println("3. Logout");
					System.out.println("4. Shutdown system");
					String input = sc.nextLine();
					switch(input){
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					default: 
						break;
					}
					
				} else{
					System.out.println("fucked up again zzz");
				}
			}
		}
		
	}
}
