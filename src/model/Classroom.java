package model;

import java.util.ArrayList;


public class Classroom {
	
	private ArrayList<UserAccount> accounts;

	public Classroom() {
		accounts = new ArrayList<>();
	}
	
	public void addUserAccounts(String username, String password) {
		accounts.add(new UserAccount(username,password));
	}
	
	public ArrayList<UserAccount> getUserAccounts(){
		return accounts; 
	}
	
	public boolean userExists(String strUsername,String strPassword) {
		boolean exists = false;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(strUsername) && accounts.get(i).getPassword().equals(strPassword)) {
				exists = true;
			}
		}
		return exists;
	}
	
}
