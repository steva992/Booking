
package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.comtrade.connection.ConnectionDataBase;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.mysql.cj.jdbc.PreparedStatementWrapper;

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
	public GeneralDomain returnInfo(GeneralDomain generalDomain, GeneralDomain generalDomain_Info) throws SQLException {
		ResultSet resultSet=refactorinReturnInfo(generalDomain_Info,generalDomain);
		generalDomain_Info=(GeneralDomain) generalDomain_Info.setResultSetForOne(resultSet);
		return generalDomain_Info;
	}
	
	@Override
	public GenericList<GeneralDomain> returnInfoForMore(GeneralDomain generalDomain, GeneralDomain generalDomain_Info) throws SQLException {
		ResultSet resultSet=refactorinReturnInfo(generalDomain_Info,generalDomain);
		GenericList<GeneralDomain>listGeneralDomain=(GenericList<GeneralDomain>) generalDomain_Info.setResultSetForMore(resultSet);
		return listGeneralDomain;
	}
	
	
	
	private ResultSet refactorinReturnInfo(GeneralDomain generalDomain_Info,GeneralDomain generalDomain) throws SQLException {
		String sql="select * from "+generalDomain_Info.returnNameOfTable()+" where "+generalDomain_Info.printIDOfParrentTable()+"=?";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement=generalDomain.setPSforID(preparedStatement);
		return preparedStatement.executeQuery();
	}





	@Override
	public GeneralDomain returnParrentInfo(int id, GeneralDomain generalDomain_Info) throws SQLException {
		String sql="select * from "+generalDomain_Info.returnNameOfTable()+" where "+generalDomain_Info.printIDOfParrentTable()+"="+id;
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		generalDomain_Info=generalDomain_Info.setResultSetForOne(resultSet);
		return generalDomain_Info;
		
	}
	
	
	
	
	





	public void changePictureURLUser(User_Info user_Info) throws SQLException {
		String sql="UPDATE user_info SET Picture_URL='"+user_Info.getPictureURL()+"' WHERE id_User="+user_Info.getId_User();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement.execute();
	}


	public void changePictureURL(Property_Picutre_Album property_Picture_Album) throws SQLException {
		String sql="UPDATE property_picture_album SET PictureURL='"+property_Picture_Album.getPicutre_URL()+"' WHERE id_property="+property_Picture_Album.getId_property()+" and Picture_Number="+property_Picture_Album.getNumber();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement.execute();
		
	}


	public GenericList<GeneralDomain> returnAlbumOfPicture(Property property) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String sql="select * from property_picture_album where id_property="+property.getId();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			Property_Picutre_Album property_Picutre_Album=new Property_Picutre_Album();
			property_Picutre_Album.setId(resultSet.getInt("id_Album_Of_Picture"));
			property_Picutre_Album.setPicutre_URL(resultSet.getString("PictureURL"));
			property_Picutre_Album.setId_property(resultSet.getInt("id_property"));
			property_Picutre_Album.setNumber(resultSet.getInt("Picture_Number"));
			list.add(property_Picutre_Album);
		}
		return list;
	}





	@Override
	public void update(GeneralDomain generalDomain) throws SQLException {
		String sql="update "+generalDomain.returnNameOfTable()+" set "+generalDomain.setColumnForUpdate()+" where "+generalDomain.printIDOfParrentTable()+"=?";
		refactoringUpdate(generalDomain,sql);
		
	}


	@Override
	public void updateWithMore(GeneralDomain generalDomain,int id) throws SQLException {
		String sql="update "+generalDomain.returnNameOfTable()+" set "+generalDomain.setColumnForUpdate()+" where "+generalDomain.printIDOfTable()+"="+id;
		refactoringUpdate(generalDomain, sql);
		
	}


	private void refactoringUpdate(GeneralDomain generalDomain, String sql) throws SQLException {
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement=generalDomain.setPS(preparedStatement);
		preparedStatement.execute();
		
	}





	@Override
	public void delete(GeneralDomain generalDomain) throws SQLException{
		String sql="delete from "+generalDomain.returnNameOfTable()+" where "+generalDomain.printIDOfTable()+"=?";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		preparedStatement=generalDomain.setPSforID(preparedStatement);
		preparedStatement.execute();
	}





	





	





	






	












	













	





	

}
