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

public class Patika {

	private static DbConnection dbPostgreConnection = new DbPostgreConnection();
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static PreparedStatement preparedStatement = null;

	private int id;
	private String name;

	public Patika() {
		super();
	}

	public Patika(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public static ArrayList<Patika> getPatikaList() {

		ArrayList<Patika> patikaList = new ArrayList<>();

		connection = dbPostgreConnection.connection();
		boolean key = false;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM patika;");

			Patika patika = null;
			while (resultSet.next()) {
				patika = new Patika();
				patika.setId(resultSet.getInt("id"));
				patika.setName(resultSet.getString("name"));
				patikaList.add(patika);

			}

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (!key) {
			Helper.showMsg("Database Bağlantı Hatası");
		}

		return patikaList;
	}

	public boolean addPatika(String name) {
		boolean key = false;
		connection = dbPostgreConnection.connection();
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO patika(name) VALUES(?)");
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();

			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			Helper.showMsg("done");
			return true;
		} else {
			Helper.showMsg("Database Hatası");
			return false;
		}

	}

	public boolean deletePatika(int id) {

		connection = dbPostgreConnection.connection();
		String query = "DELETE FROM patika WHERE id = ?";

		boolean key = false;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			key = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {
			Course.getCourseList().stream().forEach(course -> {
				if (course.getPatika_id() == id) {
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

	public static Patika getFetch(int id) {
		connection = dbPostgreConnection.connection();
		Patika patika = new Patika();
		try {

			boolean key = false;
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM patika WHERE  id = " + id + ";");
			while (resultSet.next()) {
				patika.setId(resultSet.getInt("id"));
				patika.setName(resultSet.getString("name"));
				key = true;
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(patika.toString());
		return patika;

	}

	public boolean updatePatika(int id, String name) {
		connection = dbPostgreConnection.connection();
		boolean key = false;

		String query = "UPDATE patika SET name= ?  WHERE id = ?";

		key = false;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (key) {

			Helper.showMsg("done");
			return true;
		} else {

			Helper.showMsg("Kayıt Mevcut");
			return false;
		}

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

}
