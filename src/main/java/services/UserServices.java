package services;

import java.util.List;

import model.User;

public interface UserServices {
	
	public User getUser(int id);
	public User getUser(String username);
	public boolean addUser(User u);
	public User registerUser(String username, String password);
	

	// These will be separated as I will distinguish later that only the admin can access these directly.
	public boolean deleteUser(int id);
	public List<User> getAllUsers();
	public boolean updateUser(User u);
}
