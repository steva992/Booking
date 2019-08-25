package com.comtrade.domain.user;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class PaymentUserCard implements Serializable,GeneralDomain{
	private int id;
	private String type;
	private long number;
	private Date expiration_date;
	private String User_Username;
	
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
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
		return "payment_user_card";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Type,Number,expiration_date,User_Username)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return "values (?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,type);
		preparedStatement.setLong(2,number);
		preparedStatement.setDate(3,expiration_date);
		preparedStatement.setString(4,User_Username);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "User_Username";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		PaymentUserCard payment=new PaymentUserCard();
		if(resultSet.next()) {
			payment.setId(resultSet.getInt("id_Card"));
			payment.setType(resultSet.getString("Type"));
			payment.setNumber(resultSet.getInt("Number"));
			payment.setUser_Username(resultSet.getString("User_Username"));
			payment.setExpiration_date(resultSet.getDate("expiration_date"));
		}
		return payment;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		while(resultSet.next()) {
			PaymentUserCard payment=new PaymentUserCard();
			payment.setId(resultSet.getInt("id_Card"));
			payment.setType(resultSet.getString("Type"));
			payment.setNumber(resultSet.getInt("Number"));
			payment.setUser_Username(resultSet.getString("User_Username"));
			payment.setExpiration_date(resultSet.getDate("expiration_date"));
			list.add(payment);
		}
		return list;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,id);
		return null;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("id_Card=? ,");
		stringBuffer.append("Type=? ,");
		stringBuffer.append("Number=? ,");
		stringBuffer.append("User_Username=? ,");
		stringBuffer.append("expiration_dateL=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "id_Card";
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
		PaymentUserCard other = (PaymentUserCard) obj;
		if (User_Username == null) {
			if (other.User_Username != null)
				return false;
		} else if (!User_Username.equals(other.User_Username))
			return false;
		return true;
	}
	
	

}
