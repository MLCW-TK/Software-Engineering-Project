package enjoyyourmeal;

public class RunSystem {
	public static void main(String[] args){
		/**
		 * Loads our system
		 */
		LoadSystem le1 = new LoadSystem("our restaurant");
		
		/**
		 * Loads all staff members into our system
		 */
		StaffUser chef = new StaffUser("Mathias", "Loh", "mathiasloh", "12345", "kyd1992@gmail.com");
		System.out.println(chef.toString());
		le1.addStaff(chef);
		
		/**
		 * Loads all predefined meals
		 */
		
		
		Thread system1 = new Thread(le1);
		system1.start();
	}
}
