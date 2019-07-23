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
	private int id_user;
	
	

	public Date getExpiration_date() {
		return expiration_date;
	}

	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}

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

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "payment_user_card";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Type,Number,expiration_date,id_user)";
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
		preparedStatement.setInt(4,id_user);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_user";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		PaymentUserCard payment=new PaymentUserCard();
		if(resultSet.next()) {
			payment.setId(resultSet.getInt("id_Card"));
			payment.setType(resultSet.getString("Type"));
			payment.setNumber(resultSet.getInt("Number"));
			payment.setId_user(resultSet.getInt("id_user"));
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
			payment.setId_user(resultSet.getInt("id_user"));
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
		stringBuffer.append("id_user=? ,");
		stringBuffer.append("expiration_dateL=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "id_Card";
	}

}
