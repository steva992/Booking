package com.comtrade.rating;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Client_Rating implements Serializable,GeneralDomain{
	
	private int id_client_rating;
	private int property_code;
	private int reservation_code;
	private String User_Username;
	private int client_rating;
	
	

	

	public int getId_client_rating() {
		return id_client_rating;
	}

	public void setId_client_rating(int id_client_rating) {
		this.id_client_rating = id_client_rating;
	}

	public int getProperty_code() {
		return property_code;
	}

	public void setProperty_code(int property_code) {
		this.property_code = property_code;
	}

	public int getReservation_code() {
		return reservation_code;
	}

	public void setReservation_code(int reservation_code) {
		this.reservation_code = reservation_code;
	}

	public String getUser_Username() {
		return User_Username;
	}

	public void setUser_Username(String user_Username) {
		User_Username = user_Username;
	}

	public int getClient_rating() {
		return client_rating;
	}

	public void setClient_rating(int client_rating) {
		this.client_rating = client_rating;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "client_rating";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(property_code,reservation_code,User_Username,client_rating)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,property_code);
		preparedStatement.setInt(2,reservation_code);
		preparedStatement.setString(3,User_Username);
		preparedStatement.setInt(4,client_rating);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "reservation_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		Client_Rating client=new Client_Rating();
		if(resultSet.next()) {
			client.setId_client_rating(resultSet.getInt("id_client_rating"));
			client.setProperty_code(resultSet.getInt("property_code"));
			client.setReservation_code(resultSet.getInt("reservation_code"));
			client.setUser_Username(resultSet.getString("User_Username"));
			client.setClient_rating(resultSet.getInt("client_rating"));
		}
		return client;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		while(resultSet.next()) {
			Client_Rating client=new Client_Rating();
			client.setId_client_rating(resultSet.getInt("id_client_rating"));
			client.setProperty_code(resultSet.getInt("property_code"));
			client.setReservation_code(resultSet.getInt("reservation_code"));
			client.setUser_Username(resultSet.getString("User_Username"));
			client.setClient_rating(resultSet.getInt("client_rating"));
			list.add(client);
		}
		return list;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,property_code);
		return preparedStatement;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("property_code=? ,");
		stringBuffer.append("reservation_code=? ,");
		stringBuffer.append("User_Username=? ,");
		stringBuffer.append("client_rating=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "id_client_rating";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((User_Username == null) ? 0 : User_Username.hashCode());
		result = prime * result + property_code;
		result = prime * result + reservation_code;
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
		Client_Rating other = (Client_Rating) obj;
		if (User_Username == null) {
			if (other.User_Username != null)
				return false;
		} else if (!User_Username.equals(other.User_Username))
			return false;
		if (property_code != other.property_code)
			return false;
		if (reservation_code != other.reservation_code)
			return false;
		return true;
	}

	
}
