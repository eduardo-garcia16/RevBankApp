package services;

import java.util.List;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

public class UserServicesImpl implements UserServices{
	
	public UserDAO ud = new UserDAOImpl();

	public User getUser(int id) {
		return ud.getUser(id);
	}

	public User getUser(String username) {
		return ud.getUser(username);
	}

	public boolean addUser(User u) {
		return ud.addUser(u);
	}

	public User registerUser(String username, String password) {
		if (ud.addUser(new User(username, password)))
			return ud.getUser(username);
		else
			return null;
	}

	public boolean deleteUser(int id) {
		return ud.deleteUser(id);
	}

	public List<User> getAllUsers() {
		return ud.getAllUsers();
	}

	public boolean updateUser(User u) {
		return ud.updateUser(u);
	}

}
