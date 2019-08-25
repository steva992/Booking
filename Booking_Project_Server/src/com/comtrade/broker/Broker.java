
package com.comtrade.broker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.comtrade.connection.ConnectionDataBase;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.TypeForDatabase;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.rating.Client_Rating;
import com.comtrade.reservation.Reservation;
import com.mysql.cj.jdbc.PreparedStatementWrapper;

public class Broker implements IBroker {

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
		ResultSet resultSet=refactorinReturnParrentInfo(id, generalDomain_Info);
		generalDomain_Info=generalDomain_Info.setResultSetForOne(resultSet);
		return generalDomain_Info;
		
	}
	
	@Override
	public GenericList<GeneralDomain> returnParrentInfoForMore(int id, GeneralDomain generalDomain_Info) throws SQLException {
		ResultSet resultSet=refactorinReturnParrentInfo(id,generalDomain_Info);
		GenericList<GeneralDomain> list=generalDomain_Info.setResultSetForMore(resultSet);
		return list;
		
	}
	
	
	private ResultSet refactorinReturnParrentInfo(int id,GeneralDomain generalDomain_info) throws SQLException {
		String sql="select * from "+generalDomain_info.returnNameOfTable()+" where "+generalDomain_info.printIDOfParrentTable()+"="+id;
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		return preparedStatement.executeQuery();
	}


	public GenericList<GeneralDomain> returnAlbumOfPicture(Property property) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String sql="select * from property_picture_album where property_code="+property.getProperty_code();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next()) {
			Property_Picutre_Album property_Picutre_Album=new Property_Picutre_Album();
			property_Picutre_Album.setId(resultSet.getInt("id_Album_Of_Picture"));
			property_Picutre_Album.setPicutre_URL(resultSet.getString("PictureURL"));
			property_Picutre_Album.setProperty_code(resultSet.getInt("property_code"));
			property_Picutre_Album.setNumber(resultSet.getInt("Picture_Number"));
			list.add(property_Picutre_Album);
		}
		return list;
	}




	@Override
	public GenericList<GeneralDomain> returnTable(GeneralDomain generalDomain) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		String sql="select * from "+generalDomain.returnNameOfTable();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		ResultSet resultSet=preparedStatement.executeQuery();
		list=generalDomain.setResultSetForMore(resultSet);
		return list;
	}





	public GenericList<GeneralDomain> returnParrentInfoForMoreString(String username, GeneralDomain generalDomain) throws SQLException {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		ResultSet resultSet=RefactoringReturnParrentInfoForOneString(username, generalDomain);
		list=(GenericList<GeneralDomain>) generalDomain.setResultSetForMore(resultSet);
		return list;
	}
	
	public GeneralDomain returnParrentInfoForOneString(String username, GeneralDomain generalDomain) throws SQLException {
		ResultSet resultSet=RefactoringReturnParrentInfoForOneString(username, generalDomain);
		generalDomain=(GeneralDomain) generalDomain.setResultSetForOne(resultSet);
		return generalDomain;
	}

	public ResultSet RefactoringReturnParrentInfoForOneString(String username, GeneralDomain generalDomain) throws SQLException {
		String sql="select * from " +generalDomain.returnNameOfTable()+" where "+generalDomain.printIDOfParrentTable()+"='"+username+"'";
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		return preparedStatement.executeQuery();
		
	}





	@Override
	public void execute(GenericList<TypeForDatabase> list) throws SQLException {
TypeForDatabase tfd=new TypeForDatabase();
		
		GenericList<TypeForDatabase>listUsers=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listProperties=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listAdress=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listPictureAlbum=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listUsersInfo=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listRoom=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listPayment=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listDiscount=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listRoomInfo=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listReservation=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listGeoLocation=new GenericList<TypeForDatabase>();
		GenericList<TypeForDatabase>listRating=new GenericList<TypeForDatabase>();
		
		
		String sql=null;
		for(int i=0;i<list.size();i++) {
				tfd=list.get(i);
				GeneralDomain generalDomain=tfd.getGeneralDomain();
				if(generalDomain instanceof User) {
					listUsers.add(tfd);
				}else if(generalDomain instanceof Property) {
					listProperties.add(tfd);
				}else if(generalDomain instanceof Adress) {
					listAdress.add(tfd);
				}else if(generalDomain instanceof Property_Picutre_Album) {
					listPictureAlbum.add(tfd);
				}else if(generalDomain instanceof User_Info) {
					listUsersInfo.add(tfd);
				}else if(generalDomain instanceof Room) {
					listRoom.add(tfd);
				}else if(generalDomain instanceof PaymentUserCard) {
					listPayment.add(tfd);
				}else if(generalDomain instanceof Discount) {
					listDiscount.add(tfd);
				}else if(generalDomain instanceof Room_Info) {
					listRoomInfo.add(tfd);
				}else if(generalDomain instanceof Reservation) {
					listReservation.add(tfd);
				}else if(generalDomain instanceof GeoLocation) {
					listGeoLocation.add(tfd);
				}else if(generalDomain instanceof Client_Rating) {
					listRating.add(tfd);
				}
		}
		
		if(listUsers.size() > 0) {
			executelist(listUsers);
		}
		if(listProperties.size() > 0) {
			executelist(listProperties);
		}
		if(listAdress.size() > 0) {
			executelist(listAdress);
		}
		if(listPictureAlbum.size() > 0) {
			executelist(listPictureAlbum);
		}
		if(listUsersInfo.size() > 0) {
			executelist(listUsersInfo);
		}
		if(listRoom.size() > 0) {
			executelist(listRoom);
		}
		if(listPayment.size() >0) {
			executelist(listPayment);
		}
		if(listDiscount.size() > 0) {
			executelist(listDiscount);
		}
		if(listRoomInfo.size() > 0) {
			executelist(listRoomInfo);
		}
		if(listReservation.size() > 0) {
			executelist(listReservation);
		}
		if(listGeoLocation.size() > 0) {
			executelist(listGeoLocation);
		}
		if(listRating.size() > 0) {
			executelist(listRating);
		}
		
	}









	private void executelist(GenericList<TypeForDatabase> list) throws SQLException {
		GenericList<GeneralDomain>listInsert=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listUpdate=new GenericList<GeneralDomain>();
		
		TypeForDatabase tfd=new TypeForDatabase();
		for(int i=0;i<list.size();i++) {
			tfd=list.get(i);
			GeneralDomain generalDomain=tfd.getGeneralDomain();
			if(tfd.getType()==1) {
				listInsert.add(generalDomain);
			}else if(tfd.getType()==2) {
				listUpdate.add(generalDomain);
		}
		
		
	}
		if(listInsert.size()>0) {
			executeInsert(listInsert,listInsert.get(0));
		}
		if(listUpdate.size() > 0) {
			executeUpdate(listUpdate,listUpdate.get(0));
		}

}




	private void executeUpdate(GenericList<GeneralDomain> listUpdate, GeneralDomain generalDomain) throws SQLException {
		String sql=null;
		if(generalDomain instanceof Room || generalDomain instanceof Discount) {
			sql="update "+generalDomain.returnNameOfTable()+" set "+generalDomain.setColumnForUpdate()+" where "+generalDomain.printUniqueValueOfTable()+"=?";
		}else {
			sql="update "+generalDomain.returnNameOfTable()+" set "+generalDomain.setColumnForUpdate()+" where "+generalDomain.printIDOfParrentTable()+"=?";
		}
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		for(int i=0;i<listUpdate.size();i++) {
			GeneralDomain geneDomain=listUpdate.get(i);
			preparedStatement=geneDomain.setPS(preparedStatement);
			preparedStatement.addBatch();
		}
		
		preparedStatement.executeBatch();
	}





	private void executeInsert(GenericList<GeneralDomain> listInsert, GeneralDomain generalDomain) throws SQLException {
		String sql="insert into "+generalDomain.returnNameOfTable()+" "+generalDomain.returnNameOfColumns()+" "+generalDomain.returnQuestionnaires();
		PreparedStatement preparedStatement=ConnectionDataBase.getInstance().getConnection().prepareStatement(sql);
		for(int i=0;i<listInsert.size();i++) {
			GeneralDomain geneDomain=listInsert.get(i);
			preparedStatement=geneDomain.setPS(preparedStatement);
			preparedStatement.addBatch();
		}
		
		preparedStatement.executeBatch();
	}











	







}
