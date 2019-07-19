package com.comtrade.domain.property;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class GeoLocation implements Serializable, GeneralDomain {
	private int id;
	private double Latitude;
	private double Longitude;
	private int id_adress;
	
	

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public int getId_adress() {
		return id_adress;
	}

	public void setId_adress(int id_adress) {
		this.id_adress = id_adress;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "geo_location";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Latitude,Longitude,id_adress)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values (?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setDouble(1,Latitude);
		preparedStatement.setDouble(2,Longitude);
		preparedStatement.setInt(3,id_adress);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_adress";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		GeoLocation geoLocation=new GeoLocation();
		try {
			if(resultSet.next()) {
				geoLocation.setId(resultSet.getInt("id_geoLocation"));
				geoLocation.setLatitude(resultSet.getDouble("Latitude"));
				geoLocation.setLongitude(resultSet.getDouble("Longitude"));
				geoLocation.setId_adress(resultSet.getInt("id_adress"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geoLocation;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		
		return null;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Latitude=? ,");
		stringBuffer.append("Longitude=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
