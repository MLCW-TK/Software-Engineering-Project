package enjoyyourmeal;

public class RunSystem {
	public static void main(String[] args){
		LoadSystem le1 = new LoadSystem("our restaurant");
		Thread system1 = new Thread(le1);
		system1.start();
	}
}
