package com.comtrade.domain.user;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class User_Info implements Serializable,GeneralDomain{
	private int id_User_Info;
	private String User_Username;
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









	public void setId_User_Info(int id_User_Info) {
		this.id_User_Info = id_User_Info;
	}









	public String getUser_Username() {
		return User_Username;
	}









	public void setUser_Username(String user_Username) {
		User_Username = user_Username;
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









	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "user_info";
	}
	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Name,Surname,Gender,Email,Picture_URL,Mobile_Number,User_Username)";
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
		preparedStatement.setString(7,User_Username);
		return preparedStatement;
	}




	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		User_Info user=new User_Info();
		try {
			if(resultSet.next()) {
				user.setId_User_Info(resultSet.getInt("id_User_Info"));
				user.setUser_Username(resultSet.getString("User_Username"));
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
			preparedStatement.setString(1,User_Username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}


	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "User_Username";
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
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return null;
	}









	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((User_Username == null) ? 0 : User_Username.hashCode());
		return result;
	}









	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User_Info other = (User_Info) obj;
		if (User_Username == null) {
			if (other.User_Username != null)
				return false;
		} else if (!User_Username.equals(other.User_Username))
			return false;
		return true;
	}








	


	


	


	



	
	
}
