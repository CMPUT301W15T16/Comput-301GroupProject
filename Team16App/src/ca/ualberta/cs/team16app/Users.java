package ca.ualberta.cs.team16app;

public class Users {
	protected String username;
	protected String password;
	
	public Users(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsernname() {
		return username;
	}

	public void setUsernname(String usernname) {
		this.username = usernname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
