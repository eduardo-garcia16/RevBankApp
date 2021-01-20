package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.JDBCConnection;

public class UserDAOImpl implements UserDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public User getUser(int id) {
		try {

			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setAdmin(rs.getInt("ADMIN"));

				return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser(String username) {
		try {

			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setAdmin(rs.getInt("ADMIN"));

				return u;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		try {

			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("ID"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setAdmin(rs.getInt("ADMIN"));

				users.add(u);

			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addUser(User u) {
		try {

			String sql = "CALL add_user(?,?)";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, u.getUsername());
			cs.setString(2, u.getPassword());

			cs.execute();
			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	public boolean deleteUser(int id) {
		try {

			String sql = "DELETE users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}


	}

	public boolean updateUser(User u) {
		
		try {
			
			String sql = "UPDATE users SET username = ?, password = ?, admin = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, Integer.toString(u.isAdmin()));
			
			ps.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
