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
	private int id_User;
	
	
	
	public Property(int id, String name, int rating, String type_Of_Property, int id_User) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
		this.type_Of_Property = type_Of_Property;
		this.id_User = id_User;
	}

	public Property() {
		
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
		return "(Name,Rating,Type_Of_Property,id_User)";
	}
	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?)";
	}
	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,name);
		preparedStatement.setDouble(2,rating);
		preparedStatement.setString(3,type_Of_Property);
		preparedStatement.setInt(4,id_User);
		return preparedStatement;
	}
	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_User";
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
				property.setId_User(resultSet.getInt("id_User"));
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
			preparedStatement.setInt(1,id);
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
				property.setId_User(resultSet.getInt("id_User"));
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
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "Id_Property";
	}
	
	
	
}
