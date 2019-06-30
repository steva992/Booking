package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.connection.ConnectionDataBase;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;

public class Broker implements IBroker {

	@Override
	public void enter(GeneralDomain generalDomain) throws SQLException{
		String sql="insert into "+generalDomain.returnNameOfTable()+" "+generalDomain.returnNameOfColumns()+" "+generalDomain.returnQuestionnaires();
		try {
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			preparedStatement=generalDomain.setPS(preparedStatement);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public GeneralDomain setID(GeneralDomain generalDomain) throws SQLException {
		String sql="SELECT * "
				+ "from "+ generalDomain.returnNameOfTable()+"  order by Id_User desc limit 1";
		PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		return generalDomain.setResultSetForOne(resultSet);
	}


	public User login(User user) {
		User user2=new User();
		try {
			String sql="select * from user where Username='"+user.getUsername()+"' and password='"+ user.getPassword()+"'";
			PreparedStatement preparedStatement =ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			user.setResultSetForOne(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user2;
		
	}

}
