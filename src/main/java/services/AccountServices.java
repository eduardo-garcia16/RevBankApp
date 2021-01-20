package services;

import java.util.List;

import model.Account;

public interface AccountServices {
	
	public Account getAccount(String type, int userId);
	public Account getAccount(int accountId);
	public List<Account> getAllUserAccounts(int userId);
	public double getBalance(int accountId);
	public String getType(int accountId);
	public boolean setType(int accountId, String type);
	public boolean addAccount(Account a);
	public boolean deleteAccount(int id);
	public boolean deposit(int accountId, double total);
	public boolean withdraw(int accountId, double total);

}
