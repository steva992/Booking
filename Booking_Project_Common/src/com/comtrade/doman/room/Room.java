package com.comtrade.doman.room;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Room implements Serializable,GeneralDomain{
	private int id;
	private int id_property;
	private String type;
	private double price_per_night;
	private int number_of_bed;
	
	public Room() {
		
	}
	
	

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_property() {
		return id_property;
	}

	public void setId_property(int id_property) {
		this.id_property = id_property;
	}

	public double getPrice_per_night() {
		return price_per_night;
	}

	public void setPrice_per_night(double price_per_night) {
		this.price_per_night = price_per_night;
	}

	public int getNumber_of_bed() {
		return number_of_bed;
	}

	public void setNumber_of_bed(int number_of_bed) {
		this.number_of_bed = number_of_bed;
	}

	
	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "room";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Price_per_night,Number_Of_Bed,Type,Id_Property)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setDouble(1,price_per_night);
		preparedStatement.setInt(2,number_of_bed);
		preparedStatement.setString(3,type);
		preparedStatement.setInt(4,id_property);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "Id_Property";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Room room=new Room();
		try {
			if(resultSet.next()) {
				room.setId(resultSet.getInt("Id_Room"));
				room.setId_property(resultSet.getInt("Id_Property"));
				room.setPrice_per_night(resultSet.getDouble("Price_per_night"));
				room.setNumber_of_bed(resultSet.getInt("Number_Of_Bed"));
				room.setType(resultSet.getString("Type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setInt(1,id_property);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
		try {
			while(resultSet.next()) {
				Room room=new Room();
				room.setId(resultSet.getInt("Id_Room"));
				room.setId_property(resultSet.getInt("Id_Property"));
				room.setPrice_per_night(resultSet.getDouble("Price_per_night"));
				room.setNumber_of_bed(resultSet.getInt("Number_Of_Bed"));
				room.setType(resultSet.getString("Type"));
				listRoom.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  listRoom;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Price_per_night=? ,");
		stringBuffer.append("Number_Of_Bed=? ,");
		stringBuffer.append("Type=? ,");
		stringBuffer.append("Id_Property=? ");
		return stringBuffer.toString();
		
	}





	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "Id_Room";
	}

	
	
	
	
}
