package com.patikadev.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.DbConnection;
import com.patikadev.helper.DbPostgreConnection;
import com.patikadev.helper.Helper;

public class User {

	private int id;
	private String name;
	private String userName;
	private String password;
	private String type;

	private static DbConnection postgreConnection = new DbPostgreConnection();

	public User() {
		super();
	}

	public User(int id, String name, String userName, String password, String type) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.type = type;
	}

	public static boolean addUser(String name, String userName, String password, String statu) throws SQLException {

		String query = "INSERT INTO users(name , username , password , type) VALUES(? , ? , ? , ?::person);";

		boolean count = getFetch(userName);
		boolean key = false;
		PreparedStatement preparedStatement = null;
		try {

			if (count) {
				Helper.showMsg("Kullanıcı Adı daha önce kullanılmış");
			} else {
				preparedStatement = postgreConnection.connection().prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, userName);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, statu);
				preparedStatement.executeUpdate();
				key = true;
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!(preparedStatement == null)) {
				preparedStatement.close();

			}

		}

		if (key) {

			Helper.showMsg("done");

			return true;
		} else {
			if (!count) {
				Helper.showMsg("Database Hatası...");
			}
			return false;
		}

	}

	public static ArrayList<User> getUserList() throws SQLException {

		ArrayList<User> userList = new ArrayList<>();

		boolean control = false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			statement = postgreConnection.connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users");
			User user;

			while (resultSet.next()) {

				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("type"));
				userList.add(user);

			}
			control = true;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			statement.close();
			resultSet.close();
		}

		if (control)

			return userList;
		else {
			Helper.showMsg("Database Bağlantı Soru	nu ...");
			return null;
		}

	}

	public static ArrayList<User> searchUserList(String query) {

		ArrayList<User> userList = new ArrayList<>();

		boolean control = false;

		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = postgreConnection.connection().createStatement();
			resultSet = statement.executeQuery(query);
			User user;

			while (resultSet.next()) {

				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("type"));
				userList.add(user);

			}
			control = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (control)

			return userList;
		else {
			Helper.showMsg("Database Bağlantı Soru	nu ...");
			return null;
		}

	}

	public boolean deleteUser(int id) {

		String query = "DELETE FROM users WHERE id = ?";
		PreparedStatement preparedStatement = null;
		boolean key = false;
		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (key) {
			Course.getCourseList().stream().forEach(course -> {
				if (course.getUser_id() == id) {
					course.deleteCourse(course.getId());
				}
			});

			Helper.showMsg("done");
			return true;
		} else {
			Helper.showMsg("eror");
			return false;
		}
	}

	public boolean updateUser(int id, String name, String userName, String password, String statu) {

		String query = "UPDATE users SET name = ? , username = ? , password = ? , type = ?::person WHERE id = ?";

		boolean count = getFetch(userName, id);
		boolean key = false;
		if (!count) {
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = postgreConnection.connection().prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, userName);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, statu);
				preparedStatement.setInt(5, id);
				preparedStatement.executeUpdate();
				key = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (key) {
				Helper.showMsg("done");
				return true;
			} else {
				Helper.showMsg("eror");
				return false;
			}

		}
		return true;

	}

	public static User getFetch(int id) {

		boolean key = false;
		User user = null;
		Connection connection = postgreConnection.connection();
		try {
			user = new User();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id =" + id + ";");
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));

			}
			key = true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			return user;
		} else {
			Helper.showMsg("error");
			return user;
		}

	}

	public static boolean getFetch(String userName) {

		boolean count = false;

		Connection connection = postgreConnection.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE userName = '" + userName + "'");
			while (resultSet.next()) {
				count = true;
				break;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public static boolean getFetch(String userName, int id) {

		boolean count = false;

		Connection connection = postgreConnection.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"SELECT * FROM users WHERE userName = '" + userName + "' AND NOT( id = " + id + "); ");
			while (resultSet.next()) {
				count = true;
				Helper.showMsg("Kullanıcı Mevcut");
				break;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	public static String searchQuery(String name, String userName, String statu) {
		String query = "SELECT * FROM users WHERE name ILIKE '%{{name}}%' AND  username ILIKE '%{{username}}%'";
		query = query.replace("{{name}}", name);
		query = query.replace("{{username}}", userName);

		if (!statu.isEmpty()) {
			query = query + "AND type ='{{type}}' ;";
			query = query.replace("{{type}}", statu);
		}
		System.out.println(query);
		return query;

	}

	public static User userLogin(String userName, String password) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		boolean key = false;
		try {
			preparedStatement = postgreConnection.connection()
					.prepareStatement("Select * FROM users WHERE username = ? AND password = ?");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("username"),
						resultSet.getString("password"), resultSet.getString("type"));
				return user;
			}

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!key)
			Helper.showMsg("error");
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static DbConnection getPostgreConnection() {
		return postgreConnection;
	}

	public static void setPostgreConnection(DbConnection postgreConnection) {
		User.postgreConnection = postgreConnection;
	}

}
