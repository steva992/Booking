package com.comtrade.domain.user;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class User implements GeneralDomain,Serializable {
	private int id;
	private String username;
	private String password;
	private String status;
	
	
	public User() {
		
	}

	public User(int id, String username, String password, String status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Password,Username,Status)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,password);
		preparedStatement.setString(2,username);
		preparedStatement.setString(3,status);
			return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "id_User";
	}



	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		User user=new User();
		try {
			if(resultSet.next()) {
				user.setId(resultSet.getInt("id_User"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword((resultSet.getString("Password")));
				user.setStatus((resultSet.getString("Status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setInt(1,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		GenericList<GeneralDomain>listUsers=new GenericList<GeneralDomain>();
		try {
			
			while(resultSet.next()) {
				User user=new User();
				user.setId(resultSet.getInt("id_User"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword((resultSet.getString("Password")));
				user.setStatus((resultSet.getString("Status")));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}

	@Override
	public String setColumnForUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printIDOfTable() {
		// TODO Auto-generated method stub
		return "Id_User";
	}

	

	

}