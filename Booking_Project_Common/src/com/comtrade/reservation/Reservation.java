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
	private int idUser;
	private int IdRoom;
	private int numberAdults;
	private int numberChildren;
	private Date checkIn;
	private Date checkOut;
	private int numberNights;
	private double amount;
	
	

	

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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

	public int getIdRoom() {
		return IdRoom;
	}

	public void setIdRoom(int idRoom) {
		IdRoom = idRoom;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "reservation";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Id_User,Number_Of_Adults,Number_Of_Children,Id_Room,Check_In,Check_Out,number_nights,amount_reservation)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?,?,?,?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,idUser);
		preparedStatement.setInt(2,numberAdults);
		preparedStatement.setInt(3,numberChildren);
		preparedStatement.setInt(4,IdRoom);
		preparedStatement.setDate(5,checkIn);
		preparedStatement.setDate(6,checkOut);
		preparedStatement.setInt(7,numberNights);
		preparedStatement.setDouble(8,amount);
		
		return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "idRoom";
	}

	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) throws SQLException {
		Reservation reservation=new Reservation();
		if(resultSet.next()) {
			reservation.setIdReservation(resultSet.getInt("Id_Reservation"));
			reservation.setIdUser(resultSet.getInt("Id_User"));
			reservation.setNumberAdults(resultSet.getInt("Number_Of_Adults"));
			reservation.setNumberChildren(resultSet.getInt("Number_Of_Children"));
			reservation.setIdRoom(resultSet.getInt("Id_Room"));
			reservation.setCheckIn(resultSet.getDate("Check_In"));
			reservation.setCheckOut(resultSet.getDate("Check_Out"));
			reservation.setNumberNights(resultSet.getInt("number_nights"));
			reservation.setAmount(resultSet.getDouble("amount_reservation"));
		}
		return reservation;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		while(resultSet.next()) {
			Reservation reservation=new Reservation();
			reservation.setIdReservation(resultSet.getInt("Id_Reservation"));
			reservation.setIdUser(resultSet.getInt("Id_User"));
			reservation.setNumberAdults(resultSet.getInt("Number_Of_Adults"));
			reservation.setNumberChildren(resultSet.getInt("Number_Of_Children"));
			reservation.setIdRoom(resultSet.getInt("Id_Room"));
			reservation.setCheckIn(resultSet.getDate("Check_In"));
			reservation.setCheckOut(resultSet.getDate("Check_Out"));
			reservation.setNumberNights(resultSet.getInt("number_nights"));
			reservation.setAmount(resultSet.getDouble("amount_reservation"));
			list.add(reservation);
		}
		return null;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1,idReservation);
		return null;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Id_User=? ,");
		stringBuffer.append("Number_Of_Adults=? ,");
		stringBuffer.append("Number_Of_Children=? ,");
		stringBuffer.append("Id_Room=? ,");
		stringBuffer.append("Check_In=? ,");
		stringBuffer.append("Check_Out=? ,");
		stringBuffer.append("number_nights=? ,");
		stringBuffer.append("amount_reservation=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "Id_Reservation";
	}
	
}
