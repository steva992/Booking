package com.comtrade.domain.discount;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Discount implements Serializable,GeneralDomain{
	private int id;
	private Date From_Date;
	private Date To_Date;
	private double amount_of_discount;
	private int property_code;
	private int discount_code;
	
	public Discount() {
		
	}
	
	
	
	public double getAmount_of_discount() {
		return amount_of_discount;
	}



	public void setAmount_of_discount(double amount_of_discount) {
		this.amount_of_discount = amount_of_discount;
	}



	public int getDiscount_code() {
		return discount_code;
	}



	public void setDiscount_code(int discount_code) {
		this.discount_code = discount_code;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFrom_Date() {
		return From_Date;
	}
	public void setFrom_Date(Date from_Date) {
		From_Date = from_Date;
	}
	public Date getTo_Date() {
		return To_Date;
	}
	public void setTo_Date(Date to_Date) {
		To_Date = to_Date;
	}
	public double getAmount_of_dosicount() {
		return amount_of_discount;
	}
	public void setAmount_of_dosicount(double amount_of_dosicount) {
		this.amount_of_discount = amount_of_dosicount;
	}
	public int getProperty_code() {
		return property_code;
	}
	public void setProperty_code(int id_property) {
		this.property_code = id_property;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "discount";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(From_Date,To_Date,Amount_Of_Discount,property_code,discount_code)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setDate(1,From_Date);
		preparedStatement.setDate(2,To_Date);
		preparedStatement.setDouble(3,amount_of_discount);
		preparedStatement.setInt(4,property_code);
		preparedStatement.setInt(5,discount_code);
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "property_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		Discount discount=new Discount();
		if(resultSet.next()) {
			discount.setId(resultSet.getInt("Id_Discount"));
			discount.setFrom_Date(resultSet.getDate("From_Date"));
			discount.setTo_Date(resultSet.getDate("To_Date"));
			discount.setAmount_of_dosicount(resultSet.getDouble("Amount_Of_Discount"));
			discount.setProperty_code(property_code);
			discount.setDiscount_code(resultSet.getInt("discount_code"));
		}
		return discount;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
		while(resultSet.next()) {
			Discount discount=new Discount();
			discount.setId(resultSet.getInt("Id_Discount"));
			discount.setFrom_Date(resultSet.getDate("From_Date"));
			discount.setTo_Date(resultSet.getDate("To_Date"));
			discount.setAmount_of_dosicount(resultSet.getDouble("Amount_Of_Discount"));
			discount.setProperty_code(resultSet.getInt("property_code"));
			discount.setDiscount_code(resultSet.getInt("discount_code"));
			listDiscount.add(discount);
		}
		return listDiscount;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,id);
		return preparedStatement;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("From_Date=? ,");
		stringBuffer.append("To_Date=? ,");
		stringBuffer.append("Amount_Of_Discount=? ");
		stringBuffer.append("property_code=? ");
		return stringBuffer.toString();
	}

	

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "discount_code";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + discount_code;
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
		Discount other = (Discount) obj;
		if (discount_code != other.discount_code)
			return false;
		return true;
	}
	
	
	
}
