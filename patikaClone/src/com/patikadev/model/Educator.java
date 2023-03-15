package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.DbConnection;
import com.patikadev.helper.DbPostgreConnection;
import com.patikadev.helper.Helper;

public class Educator extends User {

	private static DbConnection postgreConnection = new DbPostgreConnection();
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;

	public Educator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Educator(int id, String name, String userName, String password, String type) {
		super(id, name, userName, password, type);
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<Lesson> getLessonList() {
		ArrayList<Lesson> lessons = new ArrayList<>();
		Lesson lesson = null;
		boolean key = false;
		try {
			statement = postgreConnection.connection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM lesson");
			while (resultSet.next()) {
				lesson = new Lesson();
				int id = resultSet.getInt("id");
				int course_id = resultSet.getInt("course_id");
				String course_name = resultSet.getString("course_name");
				String feature = resultSet.getString("feature");
				String explanation = resultSet.getString("explanation");
				String link = resultSet.getString("link");
				lessons.add(new Lesson(id, course_id, course_name, feature, explanation, link));

			}

			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!key)
			Helper.showMsg("error");
		return lessons;

	}

	public static boolean deleteLesson(int id) {

		String query = "DELETE FROM lesson WHERE id = ?";

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

		return key;
	}

	public static boolean updateLesson(int id, int course_id, String course_name, String feature, String explanation,
			String link) {

		boolean key = false;

		String query = "UPDATE lesson SET course_id = ? , course_name = ?, feature = ? , explanation = ?  ,link = ? WHERE id = ?";

		key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, course_id);
			preparedStatement.setString(2, course_name);
			preparedStatement.setString(3, feature);
			preparedStatement.setString(4, explanation);
			preparedStatement.setString(5, link);
			preparedStatement.setInt(6, id);
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

		return key;

	}

	public static boolean addCourse(int course_id, String course_name, String feature, String explanation,
			String link) {

		String query = "INSERT INTO lesson(course_id , course_name , feature , explanation , link )  VALUES(?,?,?,?,?)";
		boolean key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, course_id);
			preparedStatement.setString(2, course_name);
			preparedStatement.setString(3, feature);
			preparedStatement.setString(4, explanation);
			preparedStatement.setString(5, link);
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

		return key;

	}

}
