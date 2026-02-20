package lld.lldproblems.ridesharingapp.User;

public class Passenger extends User {
	
	public Passenger(String userID, String name, String email, String phoneNumber) {
		super(userID, name, email, phoneNumber);
	}
	
	public void notify(String message) {
		System.out.println(this.name + " Notified: " + message);
	}
}