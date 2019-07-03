package com.comtrade.domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Additional_Features extends User implements Serializable{
	private int id_User_Additional_Features;
	private int id_User;
	private String name;
	private String surname;
	private String gender;
	private String email;
	private String pictureURL;
	private String mobileNumber;
	
	public User_Additional_Features() {
		
	}
	
	
	public int getId_User_Additional_Features() {
		return id_User_Additional_Features;
	}
	public void setId_User_Additional_Features(int id_User_Additional_Features) {
		this.id_User_Additional_Features = id_User_Additional_Features;
	}
	public int getId_User() {
		return id_User;
	}
	public void setId_User(int id_User) {
		this.id_User = id_User;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public User_Additional_Features(int id_User_Additional_Features, int id_User, String name, String surname,
			String gender, String email, String pictureURL, String mobileNumber) {
		super();
		this.id_User_Additional_Features = id_User_Additional_Features;
		this.id_User = id_User;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.email = email;
		this.pictureURL = pictureURL;
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "user_info";
	}
	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(id_User,Name,Surname,Gender,Email,Picture_URL,Mobile_Number)";
	}
	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?,?)";
	}
	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,id_User);
		preparedStatement.setString(2,name);
		preparedStatement.setString(3,surname);
		preparedStatement.setString(4,gender);
		preparedStatement.setString(5,email);
		preparedStatement.setString(6,pictureURL);
		preparedStatement.setString(7,mobileNumber);
		return preparedStatement;
	}


	



	
	
}
