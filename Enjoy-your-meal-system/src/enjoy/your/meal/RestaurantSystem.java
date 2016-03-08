package enjoy.your.meal;

import java.util.Scanner;

public class RestaurantSystem {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to our restaurant!");
		System.out.println("What would you like to do today?");
		System.out.println("Type 'register' to register a new user. Type 'login' to login a current user");
		String input = sc.nextLine();
		System.out.println("You have chosen to "+input);
		System.out.println("Please wait");
	}
}
