package model;

import java.util.ArrayList;

public class UserAccount {

	public void createAccount(String username,String password) {
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
		usernames.add(username);
		passwords.add(password);
	}
	
}
