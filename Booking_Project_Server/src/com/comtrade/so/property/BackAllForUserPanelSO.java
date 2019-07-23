package com.comtrade.so.property;

import java.sql.SQLException;
import java.util.Map;

import com.comtrade.broker.Broker;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;

public class BackAllForUserPanelSO extends GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>> {

	@Override
	protected void runConcreteSO(GenericMap<String, GenericList<GeneralDomain>> object) throws SQLException, Exception {
		Broker broker=new Broker();
		GenericMap<String,GenericList<GeneralDomain>>map=object;
		GenericList<GeneralDomain>propertyList=new GenericList<GeneralDomain>();
		propertyList=broker.returnTable(new Property());
		
		GenericList<GeneralDomain>listAllAboutProperty=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listAlbum=new GenericList<GeneralDomain>();
		GenericList<GeneralDomain>listUser=object.get("user");
		
		Adress adress=new Adress();
		GeoLocation geoLocation=new GeoLocation();
		Room_Info room_Info=new Room_Info();
		Room room=new Room();
		Property property=new Property();
		Discount discount=new Discount();
		PaymentUserCard card=new PaymentUserCard();
		User user=(User) listUser.get(0);
		
		for(int i=0;i<propertyList.size();i++) {
			listAllAboutProperty=new GenericList<GeneralDomain>();
			property=(Property) propertyList.get(i);
			
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
			
			card=(PaymentUserCard) broker.returnParrentInfo(user.getId(),card);
			listAllAboutProperty.add(card);
			
			map.put(property.getName(),listAllAboutProperty);
		}
		
		map.remove("user");
	}

}
