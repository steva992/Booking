package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Property implements GeneralDomain,Serializable{
	private int id;
	private String name;
	private double rating;
	private String Album_Of_Picture;
	private String type_Of_Property;
	private int id_User;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getAlbum_Of_Picture() {
		return Album_Of_Picture;
	}
	public void setAlbum_Of_Picture(String album_Of_Picture) {
		Album_Of_Picture = album_Of_Picture;
	}
	public String getType_Of_Property() {
		return type_Of_Property;
	}
	public void setType_Of_Property(String type_Of_Property) {
		this.type_Of_Property = type_Of_Property;
	}
	public int getId_User() {
		return id_User;
	}
	public void setId_User(int id_User) {
		this.id_User = id_User;
	}
	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "property";
	}
	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Name,Rating,Album_Of_Picture,Type_Of_Property,id_User)";
	}
	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?)";
	}
	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,name);
		preparedStatement.setDouble(2,rating);
		preparedStatement.setString(3,Album_Of_Picture);
		preparedStatement.setString(4,type_Of_Property);
		preparedStatement.setInt(5,id_User);
		return preparedStatement;
	}
	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "Id_Property";
	}
	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Property property=new Property();
		try {
			if(resultSet.next()) {
				property.setId(resultSet.getInt("Id_Property"));
				property.setName(resultSet.getString("Name"));
				property.setRating(resultSet.getDouble("Rating"));
				property.setAlbum_Of_Picture(resultSet.getString("Album_Of_Picture"));
				property.setType_Of_Property(resultSet.getString("Type_Of_Property"));
				property.setId_User(resultSet.getInt("id_User"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}
	
	
}
