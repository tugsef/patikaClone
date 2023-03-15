package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.Helper;

public class RegisteredCourse {
	private int id;
	private int userId;
	private int courseId;
	private int patikaId;
	private String courseName;
	private String patikaName;
	private int lessonId;

	public RegisteredCourse() {
		super();
	}

	public static boolean deleteRegisterCourse(int id) {

		String query = "DELETE FROM registers WHERE id = ?";
		PreparedStatement preparedStatement = null;
		boolean key = false;
		try {
			preparedStatement = User.getPostgreConnection().connection().prepareStatement(query);
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

	public RegisteredCourse(int id, int userId, int courseId, int patikaId, String courseName, String patikaName,
			int lessonId) {
		super();
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
		this.patikaId = patikaId;
		this.courseName = courseName;
		this.patikaName = patikaName;
		this.lessonId = lessonId;
	}

	public static ArrayList<RegisteredCourse> getRegisteredCoursesList() {
		ArrayList<RegisteredCourse> registeredCourses = new ArrayList<>();
		RegisteredCourse registeredCourse = null;
		boolean key = false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = User.getPostgreConnection().connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM registers");
			while (resultSet.next()) {
				registeredCourse = new RegisteredCourse();
				int id = resultSet.getInt("id");
				int user_id = resultSet.getInt("user_id");
				int course_id = resultSet.getInt("course_id");
				int patika_id = resultSet.getInt("patika_id");
				String course_name = resultSet.getString("course_name");
				String patika_name = resultSet.getString("patika_name");
				int lesson_id = resultSet.getInt("lesson_id");

				registeredCourses.add(
						new RegisteredCourse(id, user_id, course_id, patika_id, course_name, patika_name, lesson_id));

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
			return registeredCourses;
		} else {
			Helper.showMsg("error");
			return null;
		}

	}

	public static boolean addRegisterLesson(int userId, int course_id, int patika_id, String course_name,
			String patika_name, int lesson_id) {

		String query = "INSERT INTO registers(user_id , course_id , patika_id , course_name ,"
				+ "patika_name,lesson_id)  VALUES(?,?,?,?,?,?);";
		boolean key = false;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = User.getPostgreConnection().connection().prepareStatement(query);
			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, course_id);
			preparedStatement.setInt(3, patika_id);
			preparedStatement.setString(4, course_name);
			preparedStatement.setString(5, patika_name);
			preparedStatement.setInt(6, lesson_id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getPatikaId() {
		return patikaId;
	}

	public void setPatikaId(int patikaId) {
		this.patikaId = patikaId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPatikaName() {
		return patikaName;
	}

	public void setPatikaName(String patikaName) {
		this.patikaName = patikaName;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

}
