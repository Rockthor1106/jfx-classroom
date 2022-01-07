package model;

import java.util.ArrayList;

public class UserAccount {
	
	private String username;
	private String password;
	private String photo; //this is the photo's address
	private String gender;
	private ArrayList<String> career;
	private String birthday;
	private String favoriteBrowser;
	
	public UserAccount(String username, String password, String photo, String gender, ArrayList<String> career, String birthday,String favoriteBrower) {
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.gender = gender;
		this.career = career;
		this.birthday = birthday;
		this.favoriteBrowser = favoriteBrower;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPhoto() {
		return photo;
	}

	public ArrayList<String> getCareer() {
		return career;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getFavoriteBrowser() {
		return favoriteBrowser;
	}

	public String getGender() {
		return gender;
	}

}