package enjoyyourmeal;

import java.util.Scanner;

public class RunSystem {
	public static void main(String[] args){
		RestaurantSystem re1 = new RestaurantSystem("our restaurant");
		while (re1.getRegistrationPhase()){
			System.out.println("Welcome to " + re1.getRestaurantName() + " !");
			System.out.println("Please type 'register' to register a new user, or 'login' to create a new one");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();		
			switch(input.toUpperCase()){
			case "REGISTER":
				re1.register();
				System.out.println("");
				break;
			default:
				System.out.println("Invalid input. Please try again");
				System.out.println("");
				continue;
			}
		}
		System.out.println("Congrats, we broke out of this shithole");
		
	}
}
