package CoreSystem;

import Users.ClientUser;
import update.Subscriber;

public class TestMain {
	public static void main(String[] args){
		RestaurantSystem sys = new RestaurantSystem("Test");
		ClientUser a = new ClientUser("a","b","c","d");
		sys.addUser(a);
		sys.printUsers();
		System.out.println(sys.user_list.isEmpty());
		System.out.print(sys.getMeal_list());
		System.out.println(sys.getMeal_list().equals(null));
	}
}
