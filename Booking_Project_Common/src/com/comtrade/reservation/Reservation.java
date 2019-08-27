package com.comtrade.reservation;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class Reservation implements Serializable,GeneralDomain{
	
	private int idReservation;
	private String User_Username;
	private int room_code;
	private int numberAdults;
	private int numberChildren;
	private Date checkIn;
	private Date checkOut;
	private int numberNights;
	private double amount;
	private int reservation_code;
	

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getUser_Username() {
		return User_Username;
	}

	public void setUser_Username(String user_Username) {
		User_Username = user_Username;
	}

	public int getRoom_code() {
		return room_code;
	}

	public void setRoom_code(int room_code) {
		this.room_code = room_code;
	}

	public int getNumberAdults() {
		return numberAdults;
	}

	public void setNumberAdults(int numberAdults) {
		this.numberAdults = numberAdults;
	}

	public int getNumberChildren() {
		return numberChildren;
	}

	public void setNumberChildren(int numberChildren) {
		this.numberChildren = numberChildren;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int getNumberNights() {
		return numberNights;
	}

	public void setNumberNights(int numberNights) {
		this.numberNights = numberNights;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getReservation_code() {
		return reservation_code;
	}

	public void setReservation_code(int reservation_code) {
		this.reservation_code = reservation_code;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "reservation";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(User_Username,Number_Of_Adults,Number_Of_Children,room_code,Check_In,Check_Out,number_nights,amount_reservation,reservation_code)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,User_Username);
		preparedStatement.setInt(2,numberAdults);
		preparedStatement.setInt(3,numberChildren);
		preparedStatement.setInt(4,room_code);
		preparedStatement.setDate(5,checkIn);
		preparedStatement.setDate(6,checkOut);
		preparedStatement.setInt(7,numberNights);
		preparedStatement.setDouble(8,amount);
		preparedStatement.setLong(9,reservation_code);
		
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "room_code";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		Reservation reservation=new Reservation();
		if(resultSet.next()) {
			reservation.setIdReservation(resultSet.getInt("Id_Reservation"));
			reservation.setUser_Username(resultSet.getString("User_Username"));
			reservation.setNumberAdults(resultSet.getInt("Number_Of_Adults"));
			reservation.setNumberChildren(resultSet.getInt("Number_Of_Children"));
			reservation.setRoom_code(resultSet.getInt("room_code"));
			reservation.setCheckIn(resultSet.getDate("Check_In"));
			reservation.setCheckOut(resultSet.getDate("Check_Out"));
			reservation.setNumberNights(resultSet.getInt("number_nights"));
			reservation.setAmount(resultSet.getDouble("amount_reservation"));
			reservation.setReservation_code(resultSet.getInt("reservation_code"));
		}
		return reservation;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		while(resultSet.next()) {
			Reservation reservation=new Reservation();
			reservation.setIdReservation(resultSet.getInt("Id_Reservation"));
			reservation.setUser_Username(resultSet.getString("User_Username"));
			reservation.setNumberAdults(resultSet.getInt("Number_Of_Adults"));
			reservation.setNumberChildren(resultSet.getInt("Number_Of_Children"));
			reservation.setRoom_code(resultSet.getInt("room_code"));
			reservation.setCheckIn(resultSet.getDate("Check_In"));
			reservation.setCheckOut(resultSet.getDate("Check_Out"));
			reservation.setNumberNights(resultSet.getInt("number_nights"));
			reservation.setAmount(resultSet.getDouble("amount_reservation"));
			reservation.setReservation_code(resultSet.getInt("reservation_code"));
			list.add(reservation);
		}
		return list;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,room_code);
		return preparedStatement;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("User_Username=? ,");
		stringBuffer.append("Number_Of_Adults=? ,");
		stringBuffer.append("Number_Of_Children=? ,");
		stringBuffer.append("room_code=? ,");
		stringBuffer.append("Check_In=? ,");
		stringBuffer.append("Check_Out=? ,");
		stringBuffer.append("number_nights=? ,");
		stringBuffer.append("amount_reservation=? ,");
		stringBuffer.append("reservation_code=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "reservation_code";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkIn == null) ? 0 : checkIn.hashCode());
		result = prime * result + ((checkOut == null) ? 0 : checkOut.hashCode());
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
		Reservation other = (Reservation) obj;
		if (checkIn == null) {
			if (other.checkIn != null)
				return false;
		} else if (!checkIn.equals(other.checkIn))
			return false;
		if (checkOut == null) {
			if (other.checkOut != null)
				return false;
		} else if (!checkOut.equals(other.checkOut))
			return false;
		if (room_code != other.room_code)
			return false;
		return true;
	}


	
	
	
}
