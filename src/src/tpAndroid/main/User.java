package tpAndroid.main;

import java.util.Date;

public class User {

	String token;
	int id;
	String userName;
	String firstName;
	Date  last_login_date;
	
	public User(String token,int id,String userName,String firstName,Date  last_login_date) {
		this.firstName=firstName;
		this.id=id;
		this.last_login_date=last_login_date;
		this.token=token;
		this.userName=userName;
	}

	public String getToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}
	
}
