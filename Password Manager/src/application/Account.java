package application;

public class Account {
	private String username, password, service;
	
	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void editPassword(String password) {
		this.password = password;
	}
}
