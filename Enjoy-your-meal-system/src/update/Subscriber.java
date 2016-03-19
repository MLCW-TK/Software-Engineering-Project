package update;

public interface Subscriber {
	public String receive_address ="";
	public void setReceiveAddress(String receive_address);
	public String getReceiveAddress();
	public void setReceiveUpdates(boolean bool);
	public boolean getReceiveUpdates();
}
