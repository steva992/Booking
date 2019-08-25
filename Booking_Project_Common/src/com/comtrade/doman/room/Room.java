package com.comtrade.doman.room;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Room implements Serializable,GeneralDomain{
	private int id;
	private int property_code;
	private String type;
	private double price_per_night;
	private int number_of_bed;
	private int room_code;
	
	public Room() {
		
	}
	
	

	
	
	
	




	public int getId() {
		return id;
	}











	public void setId(int id) {
		this.id = id;
	}











	public int getProperty_code() {
		return property_code;
	}











	public void setProperty_code(int property_code) {
		this.property_code = property_code;
	}











	public String getType() {
		return type;
	}











	public void setType(String type) {
		this.type = type;
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











	public int getRoom_code() {
		return room_code;
	}











	public void setRoom_code(int room_code) {
		this.room_code = room_code;
	}











	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "room";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Price_per_night,Number_Of_Bed,Type,property_code,room_code)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setDouble(1,price_per_night);
		preparedStatement.setInt(2,number_of_bed);
		preparedStatement.setString(3,type);
		preparedStatement.setLong(4,property_code);
		preparedStatement.setInt(5,room_code);
		
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "property_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Room room=new Room();
		try {
			if(resultSet.next()) {
				room.setId(resultSet.getInt("Id_Room"));
				room.setProperty_code(resultSet.getInt("property_code"));
				room.setPrice_per_night(resultSet.getDouble("Price_per_night"));
				room.setNumber_of_bed(resultSet.getInt("Number_Of_Bed"));
				room.setType(resultSet.getString("Type"));
				room.setRoom_code(resultSet.getInt("room_code"));
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
			preparedStatement.setInt(1,room_code);
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
				room.setProperty_code(resultSet.getInt("property_code"));
				room.setPrice_per_night(resultSet.getDouble("Price_per_night"));
				room.setNumber_of_bed(resultSet.getInt("Number_Of_Bed"));
				room.setType(resultSet.getString("Type"));
				room.setRoom_code(resultSet.getInt("room_code"));
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
		stringBuffer.append("property_code=? ");
		return stringBuffer.toString();
		
	}





	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "room_code";
	}











	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + room_code;
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
		Room other = (Room) obj;
		if (room_code != other.room_code)
			return false;
		return true;
	}

	
	
	
	
}
