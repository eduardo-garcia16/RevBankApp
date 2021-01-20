package model;

public class Account {

	private int id;
	private int userId;
	private String type;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(int id, int userId, String type, double balance) {
		this.id = id;
		this.userId = userId;
		this.type = type;
		this.balance = balance;
	}
	
	public Account(int userId, String type, double balance) {
		this.userId = userId;
		this.type = type;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", type=" + type + ", balance=" + balance + "]";
	}
	
}
