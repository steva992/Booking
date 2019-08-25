package com.comtrade.doman.room;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Room_Info implements GeneralDomain, Serializable, IRoom_Info {
	private int id_room_info;
	private int room_code;
	private boolean tv;
	private boolean wi_fi;
	private boolean mini_bar;
	private boolean jacuzzi;
	private boolean air_condition;
	private boolean kitchen;
	private boolean balkon;
	private boolean smoking;
	private boolean livingRoom;
	private boolean underFloorHeating;
	
	
	

	

	public boolean isUnderFloorHeating() {
		return underFloorHeating;
	}

	public void setUnderFloorHeating(boolean underFloorHeating) {
		this.underFloorHeating = underFloorHeating;
	}

	public int getId_room_info() {
		return id_room_info;
	}

	public void setId_room_info(int id_room_info) {
		this.id_room_info = id_room_info;
	}

	public int getRoom_code() {
		return room_code;
	}

	public void setRoom_code(int id_room) {
		this.room_code = id_room;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isWi_fi() {
		return wi_fi;
	}

	public void setWi_fi(boolean wi_fi) {
		this.wi_fi = wi_fi;
	}

	public boolean isMini_bar() {
		return mini_bar;
	}

	public void setMini_bar(boolean mini_bar) {
		this.mini_bar = mini_bar;
	}

	public boolean isJacuzzi() {
		return jacuzzi;
	}

	public void setJacuzzi(boolean jacuzzi) {
		this.jacuzzi = jacuzzi;
	}

	public boolean isAir_condition() {
		return air_condition;
	}

	public void setAir_condition(boolean air_condition) {
		this.air_condition = air_condition;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isBalkon() {
		return balkon;
	}

	public void setBalkon(boolean balkon) {
		this.balkon = balkon;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public boolean isLivingRoom() {
		return livingRoom;
	}

	public void setLivingRoom(boolean livingRoom) {
		this.livingRoom = livingRoom;
	}

	@Override
	public Room_Info addnewPropertiesForRoom() {
		Room_Info room_info=new Room_Info();
		return room_info;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "room_info";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Balkon,Jacuzzi,Mini_Bar,Air_conditioning,Wi_Fi,Kitchen,Livingroom,TV,smoking,underFloorHeating,room_code)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?,?,?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setBoolean(1,balkon);
		preparedStatement.setBoolean(2,jacuzzi);
		preparedStatement.setBoolean(3, mini_bar);
		preparedStatement.setBoolean(4,air_condition);
		preparedStatement.setBoolean(5,wi_fi);
		preparedStatement.setBoolean(6,kitchen);
		preparedStatement.setBoolean(7,livingRoom);
		preparedStatement.setBoolean(8,tv);
		preparedStatement.setBoolean(9,smoking);
		preparedStatement.setBoolean(10,underFloorHeating);
		preparedStatement.setInt(11,room_code);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "room_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		Room_Info room_info=new Room_Info();
		try {
			if(resultSet.next()) {
				room_info.setId_room_info(resultSet.getInt("Id_Room_Info"));
				room_info.setRoom_code(resultSet.getInt("room_code"));
				room_info.setBalkon(resultSet.getBoolean("Balkon"));
				room_info.setJacuzzi(resultSet.getBoolean("Jacuzzi"));
				room_info.setMini_bar(resultSet.getBoolean("Mini_Bar"));
				room_info.setAir_condition(resultSet.getBoolean("Air_conditioning"));
				room_info.setWi_fi(resultSet.getBoolean("Wi_Fi"));
				room_info.setKitchen(resultSet.getBoolean("Kitchen"));
				room_info.setLivingRoom(resultSet.getBoolean("Livingroom"));
				room_info.setTv(resultSet.getBoolean("TV"));
				room_info.setSmoking(resultSet.getBoolean("smoking"));
				room_info.setUnderFloorHeating(resultSet.getBoolean("underFloorHeating"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return room_info;
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
		GenericList<GeneralDomain>listRoom_info=new GenericList<GeneralDomain>();
		try {
			while(resultSet.next()) {
				Room_Info room_info=new Room_Info();
				room_info.setId_room_info(resultSet.getInt("Id_Room_Info"));
				room_info.setRoom_code(resultSet.getInt("room_code"));
				room_info.setBalkon(resultSet.getBoolean("Balkon"));
				room_info.setJacuzzi(resultSet.getBoolean("Jacuzzi"));
				room_info.setMini_bar(resultSet.getBoolean("Mini_Bar"));
				room_info.setAir_condition(resultSet.getBoolean("Air_condition"));
				room_info.setWi_fi(resultSet.getBoolean("Wi_Fi"));
				room_info.setKitchen(resultSet.getBoolean("Kitchen"));
				room_info.setLivingRoom(resultSet.getBoolean("Livingroom"));
				room_info.setTv(resultSet.getBoolean("TV"));
				room_info.setSmoking(resultSet.getBoolean("smoking"));
				room_info.setUnderFloorHeating(resultSet.getBoolean("underFloorHeating"));
				listRoom_info.add(room_info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRoom_info;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Balkon=? ,");
		stringBuffer.append("Jacuzzi=? ,");
		stringBuffer.append("Mini_Bar=? ,");
		stringBuffer.append("Air_conditioning=? ,");
		stringBuffer.append("Wi_Fi=? ,");
		stringBuffer.append("Kitchen=? ,");
		stringBuffer.append("Livingroom=? ,");
		stringBuffer.append("TV=? ,");
		stringBuffer.append("smoking=? ,");
		stringBuffer.append("underFloorHeating=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "room_info";
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
		Room_Info other = (Room_Info) obj;
		if (room_code != other.room_code)
			return false;
		return true;
	}

	

}
