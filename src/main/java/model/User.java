package model;


public class User {
	
	private int id;
	private String username;
	private String password;
	private int admin;

	public User() {
		super();
	}
	
	public User(int id, String username, String password, int admin) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	
	public User(String username, String password, int admin) {
		super();
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.admin = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int isAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", is_admin=" + admin + "]";
	}
	
	
	
	
}
