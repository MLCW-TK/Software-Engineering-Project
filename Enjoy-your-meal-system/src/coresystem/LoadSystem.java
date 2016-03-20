package coreSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import ingredients.Ingredient;
import mealSystem.Meal;
import orders.Order;
import users.ClientUser;
import users.StaffUser;


public class LoadSystem extends Thread {
	RestaurantSystem re1;
	ArrayList<Order> orders;
	ClientUser LoginUser;
	HashSet<Meal> meals;
	HashSet<Ingredient> ingredients;
	private Scanner sc;
	
	/**
	 * LoadSystem constructor
	 * @param name
	 */
	public LoadSystem(String name){
		re1 = new RestaurantSystem(name);
		meals = re1.meal_list;
//		meals = new HashSet<Meals>();
		ingredients = new HashSet<Ingredient>();
		orders = new ArrayList<Order>();
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
	public void addMeals(Meal meals){
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
					re1.setUserPhase(true);
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
					re1.popupMessage(LoginUser);
					System.out.println("What would you like to do today? (Enter numbers)");
					System.out.println("1. Order a meal");
					System.out.println("2. View your orders");
					System.out.println("3. Checkout");
					System.out.println("4. Inserting a meal");
					System.out.println("5. Adding a special price offer");
					System.out.println("6. Removing a special price offer");
					System.out.println("7. Send notifications to subscribed clients.");
					System.out.println("8. Change the birthday special offer.");
					System.out.println("9. Logout");
					System.out.println("10. Shutdown system");
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
						re1.refresh();
						re1.notifySubscriber();
					case "8":
						System.out.println("Please write a new statement of the birthday special offer");
						Scanner cs = new Scanner(System.in);
						String offer = cs.nextLine();
						re1.setbirthdaySpecialOffer(offer);
					case "9":
						re1.setUserPhase(false);
						re1.setRegistrationPhase(true);
						System.out.println("You have logged out. Thank you for using Enjoy-Your-Meal-System");
						System.out.println("");
					case "10":
						break;
					default: 
						break;
					}
					
				} else{
					System.out.println("Welcome, " + LoginUser.getFirstname() + " " + LoginUser.getLastname() + "!" + " (" + LoginUser.getUsertype() + ")");
					re1.popupMessage(LoginUser);
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
