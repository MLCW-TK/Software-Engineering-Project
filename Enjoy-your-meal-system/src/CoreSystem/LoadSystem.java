package CoreSystem;

import java.util.HashSet;
import java.util.Scanner;

import MealSystem.Ingredient;
import MealSystem.Meals;
import Users.ClientUser;
import Users.StaffUser;

public class LoadSystem extends Thread {
	RestaurantSystem re1;
	ClientUser LoginUser;
	HashSet<Meals> meals;
	HashSet<Ingredient> ingredients;
	private Scanner sc;
	
	/**
	 * LoadSystem constructor
	 * @param name
	 */
	public LoadSystem(String name){
		re1 = new RestaurantSystem(name);
		meals = new HashSet<Meals>();
		ingredients = new HashSet<Ingredient>();
	}
	
	/**
	 * Add a staff user to the RestaurantSystem re1
	 * @param staff
	 */
	public void addStaff(StaffUser staff){
		this.re1.addUser(staff);
	}
	
	/**
	 * Add an ingredient to the HashSet<Ingredient> ingredients
	 * @param ingredient
	 */
	public void addIngredient(Ingredient ingredient){
		this.ingredients.add(ingredient);
	}
	
	/**
	 * Add a meal to the HashSet<Meals> meals
	 * @param meals
	 */
	public void addMeals(Meals meals){
		this.meals.add(meals);
	}
	
	@Override
	public void run() {
		sc = new Scanner(System.in);
		while (!re1.exit){
			// when the system is in RegistrationPhase, run the registration thread as follows
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
					try {
						LoginUser = re1.login(username, password);
					} catch (RuntimeException e){
						System.out.println(e.getMessage());
						System.out.println("Please try again");
						System.out.println("");
						break;
					} 
					re1.setRegistrationPhase(false);
					break;
					
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
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!" + " (" + LoginUser.getUsertype() + ")");
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. View your orders");
					System.out.println("3. Checkout");
					System.out.println("4. Inserting a meal");
					System.out.println("5. Adding a special price offer");
					System.out.println("6. Removing a special price offer");
					System.out.println("7. Logout");
					System.out.println("8. Shutdown system");
					String input = sc.nextLine();
					switch(input){
					// TO BE IMPLEMENTED
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						break;
					case "5":
						break;
					case "6":
						break;
					case "7":
						re1.setUserPhase(false);
						re1.setRegistrationPhase(true);
						System.out.println("You have logged out. Thank you for using Enjoy-Your-Meal-System");
						System.out.println("");
						break;
					case "8":
						break;
					default: 
						break;
					}
					
				} else{
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!" + " (" + LoginUser.getUsertype() + ")");
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. View your orders");
					System.out.println("3. Checkout");
					System.out.println("4. Logout");
					String input = sc.nextLine();
					switch(input){
					// TO BE IMPLEMENTED
					case "1":
						break;
					case "2":
						break;
					case "3":
						break;
					case "4":
						re1.setUserPhase(false);
						re1.setRegistrationPhase(true);
						System.out.println("You have logged out. Thank you for using Enjoy-Your-Meal-System");
						System.out.println("");
						break;
					default:
						break;
					}
				}
			}
		}
		
	}
}
