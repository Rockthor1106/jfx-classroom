package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	
	private List<UserAccount> accounts;

	public Classroom() {
		accounts = new ArrayList<>();
	}
	
	public void addUserAccounts(String username, String password) {
		accounts.add(new UserAccount(username,password));
	}
	
	public List<UserAccount> getUserAccounts(){
		return accounts; 
	}
	
	public boolean userExists(String strUsername,String strPassword) {
		boolean exists = false;
		if(accounts.contains(strUsername) && accounts.contains(strPassword)) {
			exists = true;
		}
		return exists;
	}
	
}
