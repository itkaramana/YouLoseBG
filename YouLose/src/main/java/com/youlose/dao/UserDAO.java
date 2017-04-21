package dao;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.ToDoubleBiFunction;

import model.User;

public class UserDAO {
	private static UserDAO instance;
	private Set<User> users = new HashSet<User>();

	public synchronized static UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	public synchronized boolean save(User user) {

		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO users (email,username,password)" + "VALUES(?,?,?)";
			statement = DBManager.getInstance().getConnection().prepareStatement(sql);

			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Saving user is successful!!");
				return true;
			}

		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public boolean loginValid(String email, String password) {
		PreparedStatement ps = null;
		String sql = "SELECT email, password " + "FROM users WHERE email = ? AND password = ?;";
		try {

			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (!(rs.next())) {
				System.out.println("Wrong data. ");
				return false;
			}

		} catch (SQLException e) {
			System.out.println("User cannot be logged in.");
		}
		return true;
	}

	public Set<User> getAllUsers() {
		if (users != null) {
			Statement st = null;
			try {
				st = DBManager.getInstance().getConnection().createStatement();

				ResultSet resultSet = st
						.executeQuery("SELECT user_id, password, email, username, profile_picture FROM users;");
				while (resultSet.next()) {
					User user = new User();
					user.setEmail(resultSet.getString("email"));
					user.setPassword(resultSet.getString("password"));
					user.setUserID(resultSet.getInt("user_id"));
					user.setName(resultSet.getString("username"));
					user.setProfilePicture(resultSet.getString("profile_picture"));
					users.add(user);
				}

			} catch (SQLException e) {

				System.out.println("Error in getAllUsers");
				e.printStackTrace();

			}
			return users;
		} else {
			return users;
		}
	}

	// shte go vzima po ime
	public User getUser(String name) {
		String sql = "SELECT user_id, username, email, password, profile_picture FROM users WHERE username = ?;";
		User user = null;
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUserID(rs.getInt("user_id"));
				user.setProfilePicture(rs.getString("profile_picture"));
				System.out.println("done!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public boolean subscribeUser(int subscriberID, int subscribedID) {
		String sql = "INSERT into user_subscribers (users_user_id, users_subscriber_id) values (?,?);";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, subscribedID);
			ps.setInt(2, subscriberID);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public ArrayList<Integer> getSubscribers(int subscribedID) {
		String sql = "SELECT users_subscriber_id from user_subscribers where users_user_id = ?;";
		ArrayList<Integer> subscribers = new ArrayList<>();
		PreparedStatement ps = null;

		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, subscribedID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscribers.add(rs.getInt("users_subscriber_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subscribers;
	}

	public ArrayList<Integer> getSubscribing(int subscriberID) {
		String sql = "SELECT users_user_id from user_subscribers where user_subscriber_id = ?;";
		ArrayList<Integer> subscribing = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, subscriberID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscribing.add(rs.getInt("users_user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subscribing;
	}

	public boolean unSubscribeUser(int subscriberID, int subscribedID) {

		String sql = "DELETE from user_subscribers WHERE users_subscriber_id = ? AND users_user_id = ?;";
		PreparedStatement ps = null;

		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setInt(1, subscriberID);
			ps.setInt(2, subscribedID);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return false;
	}

	public void editPassword(int userID, String newPassword) {
		String sql = "UPDATE users SET password = ? WHERE user_id=?";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProfilePicture(String username, String profilepic) throws SQLException {
		String sql = "UPDATE users SET profile_picture = ? where username = ?;";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);
			ps.setString(1, profilepic);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editProfilePicture(int userID, String newPhoto) {
		String sql = "UPDATE users SET profile_picture = ? WHERE user_id =?";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, newPhoto);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error in editpic");
			e.printStackTrace();
		}
	}

	public void editName(int userID, String newName) {
		String sql = "UPDATE users SET username = ? WHERE user_id = ?";
		PreparedStatement ps = null;
		try {
			ps = DBManager.getInstance().getConnection().prepareStatement(sql);

			ps.setString(1, newName);
			ps.setInt(2, userID);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("err with updating name");
			e.printStackTrace();
		}
	}
}
