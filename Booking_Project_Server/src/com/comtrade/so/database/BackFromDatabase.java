package com.comtrade.so.database;

import java.sql.SQLException;

import javax.swing.plaf.ListUI;

import com.comtrade.broker.Broker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.rating.Client_Rating;
import com.comtrade.reservation.Reservation;
import com.comtrade.so.GeneralSystemOperation;

public class BackFromDatabase extends GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>> {

	@Override
	protected void runConcreteSO(GenericMap<String, GenericList<GeneralDomain>> object) throws SQLException, Exception {

		GenericMap<String, GenericList<GeneralDomain>>map=object;
		Broker broker=new Broker();

		GenericList<GeneralDomain>userList=new GenericList<GeneralDomain>();
		userList=broker.returnTable(new User());
		
		GenericList<GeneralDomain>listUsers=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listProperty=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listAllAboutProperty=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listAlbum=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listReservation=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listCard=new GenericList<GeneralDomain>();
		
		Adress adress=new Adress();
		GeoLocation geoLocation=new GeoLocation();
		Room_Info room_Info=new Room_Info();
		Room room=new Room();
		Property property=new Property();
		Discount discount=new Discount();
		PaymentUserCard card=new PaymentUserCard();
		Reservation reservation=new Reservation();
		Client_Rating client_rating=new Client_Rating();
		User user=new User();
		User_Info user_info=new User_Info();
		
		for(int a=0;a<userList.size();a++) {
				listUsers=null;
				listUsers=new GenericList<GeneralDomain>();
				user=(User) userList.get(a);
				user_info=(User_Info) broker.returnInfo(user, user_info);
				listUsers.add(user);
				listUsers.add(user_info);
				if(user.getStatus().equals("user")) {
					card=(PaymentUserCard) broker.returnParrentInfoForOneString(user.getUsername(),card);
					listUsers.add(card);
					map.put(user.getUsername(),listUsers);
				}else if (user.getStatus().equals("deactivated")){
					continue;
				}else {
					map.put(user.getUsername(),listUsers);
				}
				
				listProperty=broker.returnParrentInfoForMoreString(user.getUsername(),property);
				for(int b=0;b<listProperty.size();b++) {
					listAllAboutProperty=null;
					listAllAboutProperty=new GenericList<GeneralDomain>();
					property=(Property) listProperty.get(b);
					
					listAlbum=broker.returnAlbumOfPicture(property);
					listRooms=broker.returnInfoForMore(property, room);
					listDiscount=broker.returnInfoForMore(property,discount);
					
					adress=(Adress) broker.returnInfo(property,adress);
					geoLocation=(GeoLocation) broker.returnInfo(adress,geoLocation);
					room_Info=(Room_Info) broker.returnInfo(room,room_Info);
					
					listAllAboutProperty.add(property);
					listAllAboutProperty.add(adress);
					listAllAboutProperty.add(geoLocation);
					

					for(int c=0;c<listAlbum.size();c++) {
						listAllAboutProperty.add(listAlbum.get(c));
					}
					for(int d=0;d<listRooms.size();d++) {
						room=(Room) listRooms.get(d);
						room_Info=(Room_Info) broker.returnParrentInfo(room.getRoom_code(),room_Info);
						listAllAboutProperty.add(room);
						listAllAboutProperty.add(room_Info);
						listReservation=broker.returnParrentInfoForMore(room.getRoom_code(),reservation);
						for(int e=0;e<listReservation.size();e++) {
							reservation=(Reservation) listReservation.get(e);
							client_rating=(Client_Rating) broker.returnParrentInfo(reservation.getReservation_code(),client_rating);
							listAllAboutProperty.add(listReservation.get(e));
							if(client_rating.getClient_rating() != 0) {
								listAllAboutProperty.add(client_rating);
							}
						}
						
					}
					for(int f=0;f<listDiscount.size();f++) {
						discount=(Discount) listDiscount.get(f);
						listAllAboutProperty.add(discount);
					}
					
					map.put(property.getName(),listAllAboutProperty);
				}
				
					
				
			}
			
			
			
		}
		
	}

