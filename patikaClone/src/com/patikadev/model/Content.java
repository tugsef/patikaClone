package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.Helper;

public class Content {
	private int id;
	private int lessonId;
	private String name;

	public Content() {
		super();
	}

	public Content(int id, int lessonId, String name) {
		super();
		this.id = id;
		this.lessonId = lessonId;
		this.name = name;

	}

	public static ArrayList<Content> getContentList() {
		ArrayList<Content> contents = new ArrayList<>();
		Content content = null;
		boolean key = false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = User.getPostgreConnection().connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM content");
			while (resultSet.next()) {
				content = new Content();
				int id = resultSet.getInt("id");
				int lesson_id = resultSet.getInt("lesson_id");
				String course_name = resultSet.getString("name");

				contents.add(new Content(id, lesson_id, course_name));

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
		if (!key)
			Helper.showMsg("error");
		return contents;

	}

	public static boolean deleteContent(int id) {

		String query = "DELETE FROM content WHERE id = ?";
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

	public static boolean updateContent(int id, String name) {

		boolean key = false;

		String query = "UPDATE content SET name = ?  WHERE id = ?";

		key = false;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = User.getPostgreConnection().connection().prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
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

	public static boolean addContent(int lesson_id, String name) {

		String query = "INSERT INTO content(lesson_id , name )  VALUES(?,?)";
		boolean key = false;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = User.getPostgreConnection().connection().prepareStatement(query);
			preparedStatement.setInt(1, lesson_id);
			preparedStatement.setString(2, name);
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

	public static Content getFetch(int lesson_id) {

		boolean key = false;
		Lesson lesson = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Content content = null;
		try {
			content = new Content();
			statement = User.getPostgreConnection().connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM content WHERE id =" + lesson_id + ";");
			while (resultSet.next()) {
				content.setId(resultSet.getInt("id"));
				content.setLessonId(resultSet.getInt("lesson_id"));
				content.setName(resultSet.getString("name"));

			}
			key = true;

		} catch (Exception e) {
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
			return content;
		} else {
			Helper.showMsg("error");
			return null;
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
