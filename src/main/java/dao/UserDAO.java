package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	
	public User getUser(int id);
	public User getUser(String username);
	public List<User> getAllUsers();
	public boolean addUser(User u);
	public boolean deleteUser(int id);
	public boolean updateUser(User u);
}
