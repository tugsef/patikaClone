package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.patikadev.helper.Helper;

public class Comments {

	private int id;
	private int lessonId;
	private String userName;
	private String commet;

	public Comments() {
		super();
	}

	public Comments(int id, int lessonId, String lessonUserName, String commet) {
		super();
		this.id = id;
		this.lessonId = lessonId;
		this.userName = lessonUserName;
		this.commet = commet;
	}

	public static boolean addComment(int lesson_id, String username, String comment) {

		String query = "INSERT INTO comments(lesson_id , username , comment )  VALUES(?,?,?)";
		boolean key = false;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = User.getPostgreConnection().connection().prepareStatement(query);
			preparedStatement.setInt(1, lesson_id);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, comment);
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

	public static ArrayList<Comments> getCommetList() {
		ArrayList<Comments> comments = new ArrayList<>();
		Comments com = null;
		boolean key = false;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = User.getPostgreConnection().connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM comments");
			while (resultSet.next()) {
				com = new Comments();
				int id = resultSet.getInt("id");
				int lesson_id = resultSet.getInt("lesson_id");
				String username = resultSet.getString("username");
				String commentTitle = resultSet.getString("comment");

				comments.add(new Comments(id, lesson_id, username, commentTitle));

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
		return comments;

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

	public String getLessonUserName() {
		return userName;
	}

	public void setLessonUserName(String lessonUserName) {
		this.userName = lessonUserName;
	}

	public String getCommet() {
		return commet;
	}

	public void setCommet(String commet) {
		this.commet = commet;
	}

}
