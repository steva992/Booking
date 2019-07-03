package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.comtrade.connection.ConnectionDataBase;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

public class Broker implements IBroker {

	@Override
	public void enter(GeneralDomain generalDomain) throws SQLException{
		String sql="insert into "+generalDomain.returnNameOfTable()+" "+generalDomain.returnNameOfColumns()+" "+generalDomain.returnQuestionnaires();
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			preparedStatement=generalDomain.setPS(preparedStatement);
			preparedStatement.execute();
		
	}
	

	


	public User login(User user) {
		User user2=new User();
		try {
			String sql="select * from user where Username='"+user.getUsername()+"' and password='"+ user.getPassword()+"'";
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			user2=(User) user2.setResultSetForOne(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user2;
		
	}





	@Override
	public GeneralDomain setID(GeneralDomain generalDomain) throws SQLException {
		String sql="select * from "+generalDomain.returnNameOfTable()+" order by "+generalDomain.printIDOfTable()+" desc limit 1 ";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		generalDomain=generalDomain.setResultSetForOne(resultSet);
		return generalDomain;
	}





	public User checkUser(User user) throws SQLException {
		User user1=new User();
		String sql="select * from user where user.Username= '"+user.getUsername()+"'";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		user1=(User) user.setResultSetForOne(resultSet);
		return user1;
	}





	





	

}
