package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Property implements GeneralDomain,Serializable{
	
	private int id;
	private String name;
	private int rating;
	private String type_Of_Property;
	private String User_Username;
	private int property_code;
	
	

	public Property() {
		
	}
	
	
	
	public int getProperty_code() {
		return property_code;
	}



	public void setProperty_code(int property_code) {
		this.property_code = property_code;
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



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public String getType_Of_Property() {
		return type_Of_Property;
	}



	public void setType_Of_Property(String type_Of_Property) {
		this.type_Of_Property = type_Of_Property;
	}



	public String getUser_Username() {
		return User_Username;
	}



	public void setUser_Username(String user_Username) {
		User_Username = user_Username;
	}



	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "property";
	}
	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Name,Rating,Type_Of_Property,User_Username,property_code)";
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
		preparedStatement.setString(3,type_Of_Property);
		preparedStatement.setString(4,User_Username);
		preparedStatement.setInt(5,property_code);
		return preparedStatement;
	}
	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "User_Username";
	}
	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Property property=new Property();
		try {
			if(resultSet.next()) {
				property.setId(resultSet.getInt("Id_Property"));
				property.setName(resultSet.getString("Name"));
				property.setRating(resultSet.getInt("Rating"));
				property.setType_Of_Property(resultSet.getString("Type_Of_Property"));
				property.setUser_Username(resultSet.getString("User_Username"));
				property.setProperty_code(resultSet.getInt("property_code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}
	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setInt(1,property_code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}
	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		GenericList<GeneralDomain>listProperty=new GenericList<GeneralDomain>();
		try {
			while(resultSet.next()) {
				Property property=new Property();
				property.setId(resultSet.getInt("Id_Property"));
				property.setName(resultSet.getString("Name"));
				property.setRating(resultSet.getInt("Rating"));
				property.setType_Of_Property(resultSet.getString("Type_Of_Property"));
				property.setUser_Username(resultSet.getString("User_Username"));
				property.setProperty_code(resultSet.getInt("property_code"));
				listProperty.add(property);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProperty;
	}
	@Override
	public String setColumnForUpdate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "property_code";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + property_code;
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
		Property other = (Property) obj;
		if (property_code != other.property_code)
			return false;
		return true;
	}
	
	
	
}
