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
	private int adress_code;
	
	

	
	

	
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

	public int getAdress_code() {
		return adress_code;
	}

	public void setAdress_code(int adress_code) {
		this.adress_code = adress_code;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "geo_location";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Latitude,Longitude,adress_code)";
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
		preparedStatement.setInt(3,adress_code);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "adress_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		GeoLocation geoLocation=new GeoLocation();
		try {
			if(resultSet.next()) {
				geoLocation.setId(resultSet.getInt("id_geoLocation"));
				geoLocation.setLatitude(resultSet.getDouble("Latitude"));
				geoLocation.setLongitude(resultSet.getDouble("Longitude"));
				geoLocation.setAdress_code(resultSet.getInt("adress_code"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return geoLocation;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,adress_code);
		return preparedStatement;
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
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adress_code;
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
		GeoLocation other = (GeoLocation) obj;
		if (adress_code != other.adress_code)
			return false;
		return true;
	}

	

}
