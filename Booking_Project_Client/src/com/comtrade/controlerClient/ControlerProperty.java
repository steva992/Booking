package com.comtrade.controlerClient;

import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.panel.superAdmin.Super_Admin_Panel;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.transfer.TransferClass;

public class ControlerProperty {
	private static volatile ControlerProperty instance;
	private volatile User user;
	private volatile User_Info user_info;
	private volatile Property property;
	private volatile Adress adress;
	private volatile GeoLocation geoLocation;
	private volatile Property_Picutre_Album propertyAlbum;
	private volatile String message;
	private GenericMap<String, GenericList<GeneralDomain>>map;
	private User_Panel userPanel;
	private Super_Admin_Panel superAdmin;
	private volatile int number=0;

	
	private ControlerProperty() {
		map=new GenericMap<String, GenericList<GeneralDomain>>();
	}

	public synchronized int getNumber() {
		return number;
	}

	public synchronized void setNumber(int number) {
		this.number = number;
	}

	public synchronized void setUserPanel(User_Panel userPanel) {
		this.userPanel = userPanel;
	}
	
	public synchronized void setSuperAdmin(Super_Admin_Panel superAdmin) {
		this.superAdmin = superAdmin;
	}


	public synchronized GenericMap<String, GenericList<GeneralDomain>> getMap() {
		while(number==0) {
			getNumber();
		}
		
		return map;
	}

	public synchronized String getMessage() {
		while(number==0) {
			getNumber();
		}
		
		return message;
	}

	public synchronized User getUser() {
		while(number==0) {
			getNumber();
		}
		return user;
	}

	public synchronized User_Info getUser_info() {
		while(number==0) {
			getNumber();
		}
		
		return user_info;
	}

	public synchronized Property getProperty() {
		while(number==0) {
			getNumber();
		}
		
		return property;
	}

	public synchronized Adress getAdress() {
		while(number==0) {
			getNumber();
		}
		
		return adress;
	}

	public synchronized GeoLocation getGeoLocation() {
		while(number==0) {
			getNumber();
		}
		
		return geoLocation;
	}

	public synchronized Property_Picutre_Album getPropertyAlbum() {
		while(number==0) {
			getNumber();
		}
		
		return propertyAlbum;
	}

	

	public static ControlerProperty getInstance() {
		if(instance==null) {
			synchronized (ControlerProperty.class) {
				if(instance==null) {
				instance=new ControlerProperty();
				}
			}
		}
		return instance;
	
}

	public void CheckTheOperation(TransferClass transferClass) {
		TransferClass transferClass2=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		GenericMap<String, GenericList<GeneralDomain>>mapServer=new GenericMap<String, GenericList<GeneralDomain>>();
		switch (transferClass.getType_Of_operation()) {
		
		case ADD:
			message=transferClass.getMessage();
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			Property property=(Property) list.get(0);
			map.put(property.getName(),list);
			if(userPanel != null) {
				userPanel.backAllForProperty(map);
			}
			if(superAdmin != null) {
				superAdmin.addProperty(list);
			}
			this.number=1;
			break;
		case UPDATE:
			message=transferClass.getMessage();
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			adress=(Adress) list.get(1);
			geoLocation=(GeoLocation) list.get(2);
			this.number=1;
			break;	
		case DELETE:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			propertyAlbum=(Property_Picutre_Album) list.get(1);
			message=transferClass.getMessage();
			this.number=1;
			break;		
		case UPDATE_PICTURE:
			list=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			property=(Property) list.get(0);
			propertyAlbum=(Property_Picutre_Album) list.get(1);
			message=transferClass.getMessage();
			if(userPanel != null) {
				userPanel.updateImageOnProperty(property,propertyAlbum);
			}
			this.number=1;
			break;
		case GET_MAP:
			mapServer=(GenericMap<String, GenericList<GeneralDomain>>) transferClass.getServer_Object_Response();
			map=mapServer;
			this.number=1;
			break;	
		case JUST_MESSAGE:
			message=transferClass.getMessage();
			this.number=1;
			break;	
			
		default:
			break;
		}
	}	


	
}	
