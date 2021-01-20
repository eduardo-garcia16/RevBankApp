package services;

import java.util.List;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import model.Account;

public class AccountServicesImpl implements AccountServices {
	
	public AccountDAO ad = new AccountDAOImpl();

	public Account getAccount(String type, int userId) {
		return ad.getAccount(type, userId);
	}

	public Account getAccount(int accountId) {
		return ad.getAccount(accountId);
	}

	public List<Account> getAllUserAccounts(int userId) {
		return ad.getAllUserAccounts(userId);
	}

	public double getBalance(int accountId) {
		return ad.getBalance(accountId);
	}

	public String getType(int accountId) {
		return ad.getType(accountId);
	}

	public boolean setType(int accountId, String type) {
		return ad.setType(accountId, type);
	}

	public boolean addAccount(Account a) {
		if(ad.addAccount(a))
			return true;
		else
			return false;
		
	}

	public boolean deleteAccount(int id) {
		if(ad.getBalance(id) == 0) {
			ad.deleteAccount(id);
			return true;
		}
		else {
			System.out.println("Could not delete: Account balance not zero.");
			return false;
		}
	}

	public boolean deposit(int accountId, double total) {
		if(ad.setBalance(accountId, (ad.getBalance(accountId) + total)))
			return true;
		else
			return false;
	}

	public boolean withdraw(int accountId, double total) {
		if((ad.getBalance(accountId) - total > 0)) {
			ad.setBalance(accountId, (ad.getBalance(accountId)-total));
			return true;
		}
		else {
			System.out.println("Cannot withdraw more than in balance.");
			return false;
		}
	}

}
