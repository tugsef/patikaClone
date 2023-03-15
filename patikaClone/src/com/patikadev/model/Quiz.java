package com.patikadev.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import com.patikadev.helper.DbConnection;
import com.patikadev.helper.DbPostgreConnection;
import com.patikadev.helper.Helper;

public class Quiz {

	private int id;
	private int contentId;
	private String question;
	private String answer;
	private String firstwrongAnswer;
	private String secondwrongAnswer;
	private String thirdwrongAnswer;
	private String reply;

	private static DbConnection postgreConnection = new DbPostgreConnection();
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static int content_id;

	public Quiz() {
		super();
	}

	public Quiz(int id, int contentId, String question, String answer, String firstwrongAnswer,
			String secondwrongAnswer, String thirdwrongAnswer, String reply) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.question = question;
		this.answer = answer;
		this.firstwrongAnswer = firstwrongAnswer;
		this.secondwrongAnswer = secondwrongAnswer;
		this.thirdwrongAnswer = thirdwrongAnswer;
		this.reply = reply;
	}

	public static ArrayList<Quiz> getQuizList() {
		ArrayList<Quiz> quizs = new ArrayList<>();
		Quiz quiz = null;
		boolean key = false;
		try {
			statement = postgreConnection.connection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz");
			while (resultSet.next()) {
				quiz = new Quiz();
				int id = resultSet.getInt("id");
				int content_id = resultSet.getInt("content_id");
				String question = resultSet.getString("question");
				String answer = resultSet.getString("answer");
				String firstwrong = resultSet.getString("firstwrong");
				String secondwrong = resultSet.getString("secondwrong");
				String thirdwrong = resultSet.getString("thirdwrong");
				String reply = resultSet.getString("reply");

				quizs.add(new Quiz(id, content_id, question, answer, firstwrong, secondwrong, thirdwrong, reply));

			}

			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!key)
			Helper.showMsg("error");
		return quizs;

	}

	public static boolean deleteQuiz(int id) {

		String query = "DELETE FROM quiz WHERE id = ?";

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

	public static boolean updateQuiz(int id, String question, String answer, String firstwrong, String secondwrong,
			String thirdwrong) {

		boolean key = false;

		String query = "UPDATE quiz SET question = ? ,answer = ? ,firstwrong = ? ,secondwrong = ? ,thirdwrong = ?   WHERE id = ?";

		key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setString(1, question);
			preparedStatement.setString(2, answer);
			preparedStatement.setString(3, firstwrong);
			preparedStatement.setString(4, secondwrong);
			preparedStatement.setString(5, thirdwrong);
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

	public static LinkedList<Quiz> getFetchIteretor(int contentId) {
		LinkedList<Quiz> quizs = new LinkedList<>();
		Quiz quiz = null;
		boolean key = false;
		try {
			statement = postgreConnection.connection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz WHERE content_id = " + contentId + ";");

			while (resultSet.next()) {
				quiz = new Quiz(resultSet.getInt("id"), resultSet.getInt("content_id"), resultSet.getString("question"),
						resultSet.getString("answer"), resultSet.getString("firstwrong"),
						resultSet.getString("secondwrong"), resultSet.getString("thirdwrong"),
						resultSet.getString("reply"));
				quizs.add(quiz);

			}
			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!key)
			Helper.showMsg("error");

		return quizs;

	}

	public static Quiz getFetch(int id) {
		Quiz quiz = null;
		boolean key = false;
		try {
			statement = postgreConnection.connection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM quiz WHERE id = " + id + ";");

			while (resultSet.next()) {
				quiz = new Quiz(resultSet.getInt("id"), resultSet.getInt("content_id"), resultSet.getString("question"),
						resultSet.getString("answer"), resultSet.getString("firstwrong"),
						resultSet.getString("secondwrong"), resultSet.getString("thirdwrong"),
						resultSet.getString("reply"));
				break;
			}
			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!key)
			Helper.showMsg("error");

		return quiz;

	}

	public static boolean addQuiz(int content_id, String question, String answer, String firstwrong, String secondwrong,
			String thirdwrong) {

		String query = "INSERT INTO quiz(content_id , question ,  answer, firstwrong,secondwrong"
				+ ", thirdwrong )  VALUES(?,?,?,?,?,?)";
		boolean key = false;

		try {
			preparedStatement = postgreConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, content_id);
			preparedStatement.setString(2, question);
			preparedStatement.setString(3, answer);
			preparedStatement.setString(4, firstwrong);
			preparedStatement.setString(5, secondwrong);
			preparedStatement.setString(6, thirdwrong);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFirstwrongAnswer() {
		return firstwrongAnswer;
	}

	public void setFirstwrongAnswer(String firstwrongAnswer) {
		this.firstwrongAnswer = firstwrongAnswer;
	}

	public String getSecondwrongAnswer() {
		return secondwrongAnswer;
	}

	public void setSecondwrongAnswer(String secondwrongAnswer) {
		this.secondwrongAnswer = secondwrongAnswer;
	}

	public String getThirdwrongAnswer() {
		return thirdwrongAnswer;
	}

	public void setThirdwrongAnswer(String thirdwrongAnswer) {
		this.thirdwrongAnswer = thirdwrongAnswer;
	}

}
