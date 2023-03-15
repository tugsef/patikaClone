package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.DbConnection;
import com.patikadev.helper.DbPostgreConnection;
import com.patikadev.helper.Helper;

public class Course {

	private int id;
	private int user_id;
	private int patika_id;
	private String name;
	private String language;

	private User user;
	private Patika patika;

	private static DbConnection postgreConnection = new DbPostgreConnection();
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;

	public Course() {
		super();
	}

	public Course(int id, int user_id, int patika_id, String name, String language) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.patika_id = patika_id;
		this.name = name;
		this.language = language;
		this.user = User.getFetch(user_id);
		this.patika = Patika.getFetch(patika_id);
	}

	public static ArrayList<Course> getCourseList() {
		ArrayList<Course> courses = new ArrayList<>();
		Course course = null;
		boolean key = false;
		try {
			statement = postgreConnection.connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM course");
			while (resultSet.next()) {
				course = new Course();
				int id = resultSet.getInt("id");
				int userId = resultSet.getInt("user_id");
				int patikaId = resultSet.getInt("patika_id");
				String name = resultSet.getString("name");
				String language = resultSet.getString("language");

				courses.add(new Course(id, userId, patikaId, name, language));

			}
			key = true;
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

		if (key) {
			return courses;
		} else {
			Helper.showMsg("error");
			return courses;
		}

	}

	public static boolean updateCourse(int id, int userId, int patikaId, String name, String language) {

		boolean key = false;

		String query = "UPDATE course SET user_id = ? , patika_id = ?, name = ? , language = ?  WHERE id = ?";

		key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, patikaId);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, language);
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

			Helper.showMsg("Kayıt Mevcut");
			return false;
		}

	}

	public static boolean deleteCourse(int id) {

		String query = "DELETE FROM course WHERE id = ?";

		boolean key = false;
		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			Helper.showMsg("done");
			return true;
		} else {
			Helper.showMsg("eror");
			return false;
		}
	}

	public static boolean addCourse(int userId, int patikaId, String name, String language) {

		String query = "INSERT INTO course(user_id , patika_id , name , language)  VALUES(?,?,?,?)";
		boolean key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, patikaId);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, language);
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
			Helper.showMsg("error");
			return false;
		}

	}

	public static int getFetch(int id) {
		int courseId = -1;
		for (Course course : Course.getCourseList()) {
			if (id == course.getPatika_id()) {
				courseId = course.getId();
				return courseId;
			}
		}
		return courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPatika_id() {
		return patika_id;
	}

	public void setPatika_id(int patika_id) {
		this.patika_id = patika_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Patika getPatika() {
		return patika;
	}

	public void setPatika(Patika patika) {
		this.patika = patika;
	}

}
