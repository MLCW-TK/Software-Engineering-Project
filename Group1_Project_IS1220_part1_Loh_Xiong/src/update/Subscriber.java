package update;

import java.util.Date;

public interface Subscriber {
	public String receive_address ="";
	
	public void setReceiveAddress(String receive_address);
	public String getReceiveAddress();
	public void setReceiveUpdates(boolean bool);
	public boolean getReceiveUpdates();
	public void setBirthday(Date date);
	public Date getBirthday();
	public void update();
}
