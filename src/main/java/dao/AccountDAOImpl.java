package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.User;
import util.JDBCConnection;

public class AccountDAOImpl implements AccountDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	public Account getAccount(String type, int userId) {
		try {

			String sql = "SELECT * FROM accounts WHERE type = ? AND user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, type);
			ps.setString(2, Integer.toString(userId));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setType(rs.getString("TYPE"));
				a.setBalance(rs.getDouble("BALANCE"));

				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccount(int accountId) {
		try {

			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(accountId));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setType(rs.getString("TYPE"));
				a.setBalance(rs.getDouble("BALANCE"));

				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Account> getAllUserAccounts(int userId) {
		List<Account> accs = new ArrayList<Account>();

		try {

			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setType(rs.getString("TYPE"));
				a.setBalance(rs.getDouble("BALANCE"));

				accs.add(a);
			}
			return accs;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public double getBalance(int accountId) {
		try {

			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(accountId));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setType(rs.getString("TYPE"));
				a.setBalance(rs.getDouble("BALANCE"));

				return a.getBalance();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	public String getType(int accountId) {
		try {

			String sql = "SELECT * FROM accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(accountId));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setType(rs.getString("TYPE"));
				a.setBalance(rs.getDouble("BALANCE"));

				return a.getType();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean setType(int accountId, String type) {
		try {

			String sql = "UPDATE accounts SET type = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, type);
			ps.setString(2, Integer.toString(accountId));

			ps.executeQuery();

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addAccount(Account a) {
		try {

			String sql = "CALL add_account(?,?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, Integer.toString(a.getUserId()));
			cs.setString(2, a.getType());

			cs.execute();
			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	public boolean deleteAccount(int id) {
		try {

			String sql = "DELETE accounts WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean setBalance(int id, double balance) {
		try {

			String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Double.toString(balance));
			ps.setString(2, Integer.toString(id));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
