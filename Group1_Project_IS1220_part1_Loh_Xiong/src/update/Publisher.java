package update;

public interface Publisher {
	public void subscribe(Subscriber sub);
	public void unsubscribe(Subscriber unsub);
	public void notifySubscriber();
	public void notifySubscriber(String message, String mealName);
}
