package com.comtrade.so.property;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;

public class BackAllForProperty extends GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>> {

	@Override
	protected void runConcreteSO(GenericMap<String, GenericList<GeneralDomain>> object) throws SQLException, Exception {
		Broker broker=new Broker();
		GenericMap<String,GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
		map=object;
		GenericList<GeneralDomain> userList=new GenericList<GeneralDomain>();
		userList=map.get("user");
		User user=(User) userList.get(0);
		Property property=new Property();
		Room room=new Room();
		Discount discount=new Discount();
		
		GenericList<GeneralDomain>listProperty=broker.returnInfoForMore(user,property);
		GenericList<GeneralDomain>listAllAboutProperty=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
		
		Adress adress=new Adress();
		GeoLocation geoLocation=new GeoLocation();
		GenericList<GeneralDomain>listAlbum=new GenericList<GeneralDomain>();
		Room_Info room_Info=new Room_Info();
		map.remove("user");
		
		for(int i=0;i<listProperty.size();i++) {
			listAllAboutProperty=new GenericList<GeneralDomain>();
			property=(Property) listProperty.get(i);
			
			listAlbum=broker.returnAlbumOfPicture(property);
			listRooms=broker.returnInfoForMore(property, room);
			listDiscount=broker.returnInfoForMore(property,discount);
			
			adress=(Adress) broker.returnInfo(property,adress);
			geoLocation=(GeoLocation) broker.returnInfo(adress,geoLocation);
			room_Info=(Room_Info) broker.returnInfo(room,room_Info);
					
			listAllAboutProperty.add(property);
			listAllAboutProperty.add(adress);
			listAllAboutProperty.add(geoLocation);
			
			
			for(int j=0;j<listAlbum.size();j++) {
				listAllAboutProperty.add(listAlbum.get(j));
			}
			for(int k=0;k<listRooms.size();k++) {
				room=(Room) listRooms.get(k);
				room_Info=(Room_Info) broker.returnParrentInfo(room.getId(),room_Info);
				listAllAboutProperty.add(listRooms.get(k));
				listAllAboutProperty.add(room_Info);
			}
			for(int l=0;l<listDiscount.size();l++) {
				discount=(Discount) listDiscount.get(l);
				listAllAboutProperty.add(discount);
			}
			
			map.put(property.getName(),listAllAboutProperty);
		}
		
	}

}
