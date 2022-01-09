package model;

import java.util.ArrayList;


public class Classroom {
	
	private ArrayList<UserAccount> accounts;

	public Classroom() {
		accounts = new ArrayList<>();
	}
	
	public void addUserAccounts(String username, String password, String photo, String gender, ArrayList<String> career, String birthday, String favoriteBrower) {
		accounts.add(new UserAccount(username, password, photo, gender, career, birthday, favoriteBrower));
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
	
	public UserAccount searchUser(String username) {
		UserAccount foundUserAccount = null;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getUsername().equals(username)) {
				foundUserAccount = accounts.get(i);
			}
		}
		return foundUserAccount;
	}
	
}
