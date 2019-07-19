package com.comtrade.domain.user;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class User_Info implements Serializable,GeneralDomain{
	private int id_User_Info;
	private int id_User;
	private String name;
	private String surname;
	private String gender;
	private String email;
	private String pictureURL;
	private String mobileNumber;
	
	
	public User_Info() {
		
	}
	
	
	





	public int getId_User_Info() {
		return id_User_Info;
	}
	public void setId_User_Info(int id_User_Additional_Features) {
		this.id_User_Info = id_User_Additional_Features;
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
	public User_Info(int id_User_Info, int id_User, String name, String surname,
			String gender, String email, String pictureURL, String mobileNumber) {
		super();
		this.id_User_Info = id_User_Info;
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
		return "(Name,Surname,Gender,Email,Picture_URL,Mobile_Number,id_User)";
	}
	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?,?)";
	}
	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,name);
		preparedStatement.setString(2,surname);
		preparedStatement.setString(3,gender);
		preparedStatement.setString(4,email);
		preparedStatement.setString(5,pictureURL);
		preparedStatement.setString(6,mobileNumber);
		preparedStatement.setInt(7,id_User);
		return preparedStatement;
	}




	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		User_Info user=new User_Info();
		try {
			if(resultSet.next()) {
				user.setId_User_Info(resultSet.getInt("id_User_Info"));
				user.setId_User(resultSet.getInt("id_User"));
				user.setName(resultSet.getString("Name"));
				user.setSurname(resultSet.getString("Surname"));
				user.setGender(resultSet.getString("Gender"));
				user.setEmail(resultSet.getString("Email"));
				user.setPictureURL(resultSet.getString("Picture_URL"));
				user.setMobileNumber(resultSet.getString("Mobile_Number"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setInt(1,id_User);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}


	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_User";
	}


	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		return null;
	}



	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Name=? ,");
		stringBuffer.append("Surname=? ,");
		stringBuffer.append("Gender=? ,");
		stringBuffer.append("Email=? ,");
		stringBuffer.append("Picture_URL=? ,");
		stringBuffer.append("Mobile_Number=? ");
		return stringBuffer.toString();
	}








	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return null;
	}








	


	


	


	



	
	
}
