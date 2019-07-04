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
	public void enter(GeneralDomain generalDomain) throws Exception{
		String sql="insert into "+generalDomain.returnNameOfTable()+" "+generalDomain.returnNameOfColumns()+" "+generalDomain.returnQuestionnaires();
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			preparedStatement=generalDomain.setPS(preparedStatement);
			preparedStatement.execute();
	}
	

	


	public User login(User user) throws SQLException {
		User user2=new User();
			String sql="select * from user where Username='"+user.getUsername()+"' and password='"+ user.getPassword()+"'";
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			user2=(User) user2.setResultSetForOne(resultSet);
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





	public User checkUser(String username) throws SQLException {
		User user1=new User();
		String sql="select * from user where user.Username= '"+username+"'";
		return checkUserRefactoring(user1,sql);
	}
	
	public User checkUser(String username,String password) throws SQLException {
		User user1=new User();
		String sql="select * from user where user.Username= '"+username+"' and user.Password= '"+password+"'";
		return checkUserRefactoring(user1, sql);
	}
	
	private User checkUserRefactoring(User user1, String sql) throws SQLException {
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		user1=(User) user1.setResultSetForOne(resultSet);
		return user1;
	}


	public void changePassword(User userChange) throws SQLException {
		String sql="UPDATE user SET user.Password='"+userChange.getPassword()+"' where user.Username='"+userChange.getUsername()+"'";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement.execute();
	}





	@Override
	public GeneralDomain returnInfo(GeneralDomain generalDomain, GeneralDomain generalDoman_Info) throws SQLException {
		String sql="select * from "+generalDoman_Info.returnNameOfTable()+" where "+generalDomain.printIDOfTable()+"=?";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement=generalDomain.setPSforID(preparedStatement);
		ResultSet resultSet=preparedStatement.executeQuery();
		generalDoman_Info=generalDoman_Info.setResultSetForOne(resultSet);
		return generalDoman_Info;
	}










	













	





	

}
