package com.patikadev.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.patikadev.helper.Helper;

public class Lesson {
	
	private int id;
	private int  course_id;
	private String course_name;
	
	private String feature;
	private String explanation;
	private String link;
	
	
	
	public Lesson() {
		super();
	}

	public Lesson(int id, int course_id, String course_name, String feature, String explanation, String link) {
		super();
		this.id = id;
		this.course_id = course_id;
		this.course_name = course_name;
		this.feature = feature;
		this.explanation = explanation;
		this.link = link;
	}
	public static Lesson getFetch(int id) {

		boolean key = false;
		Lesson lesson = null;
		Statement statement=null;
		ResultSet resultSet=null;
		try {
			lesson = new Lesson();
			statement = User.getPostgreConnection().connection().createStatement();
			resultSet = statement.executeQuery("SELECT * FROM lesson WHERE id =" + id + ";");
			while (resultSet.next()) {
				lesson.setId(resultSet.getInt("id"));
				lesson.setCourse_id(resultSet.getInt("course_id"));
				lesson.setCourse_name(resultSet.getString("course_name"));
				lesson.setFeature(resultSet.getString("feature"));
				lesson.setExplanation(resultSet.getString("explanation"));
				lesson.setLink(resultSet.getString("link"));
				

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
			return lesson;
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

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	


	
	
	
	
	
}
