package com.comtrade.controlerPL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.property.BackAllForAdminPanel;
import com.comtrade.so.property.BackAllForUserPanelSO;
import com.comtrade.so.property.ChangePictureURLPropertySO;
import com.comtrade.so.property.EntertPropertySO;
import com.comtrade.so.property.UpdatePropertySO;
import com.comtrade.so.user.ChangePictureURLUserSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLProperty{
	private static volatile ControlerPLProperty instance;
	
	private ControlerPLProperty() {
		
	}
	
	public static ControlerPLProperty getInstance() {
		if(instance==null) {
			synchronized (ControlerPLProperty.class) {
				if(instance==null) {
					instance=new ControlerPLProperty();
				}
			}
		}
		return instance;
		
	}

	public TransferClass CheckTheOperation(TransferClass transferClass) throws Exception{
		TransferClass transferClass2=new TransferClass();
		User user=new User();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_PROPERTY:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterTheProperty(list);
				break;
			case BACK_ALL_ABOUT_PROPERTY:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(BackAllAboutProperty(user));
				break;
			case CHANGE_PICTURE_URL_HOTEL:
				Property_Picutre_Album property_Picutre_Album=(Property_Picutre_Album) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(ChangePictureURL(property_Picutre_Album));
				break;	
			case UPDATE_PROPERTY:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=updateProperty(list);
				break;	
			case BACK_ALL_FOR_USER_PANEL:
				user=(User) transferClass.getClient_Object_Request();
				transferClass2=AllForUserPanel(user);
				break;	
				default:
					break;
		}
		return transferClass2;
		
	}

	private TransferClass AllForUserPanel(User user) {
		TransferClass transferClass=new TransferClass();
		GenericMap<String, GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		list.add(user);
		map.put("user",list);
		GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>>generalSO=new BackAllForUserPanelSO();
		try {
			generalSO.runSO(map);
			transferClass.setServer_Object_Response(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private TransferClass updateProperty(GenericList<GeneralDomain> list) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new UpdatePropertySO();
		try {
			generalSO.runSO(list);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transferClass;
	}

	private Property_Picutre_Album ChangePictureURL(Property_Picutre_Album property_Picutre_Album) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<Property_Picutre_Album>genericSO=new ChangePictureURLPropertySO();
		try {
			genericSO.runSO(property_Picutre_Album);
			transferClass.setMessage(TransferClass_Message.SUCCESSFUL_CHANGE.getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property_Picutre_Album;
	}

	private GenericMap<String,GenericList<GeneralDomain>> BackAllAboutProperty(User user) {
		TransferClass transferClass=new TransferClass();
		GenericMap<String,GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		list.add(user);
		map.put("user",list);
		try {
			GeneralSystemOperation<GenericMap<String,GenericList<GeneralDomain>>>generalSO=new BackAllForAdminPanel();
			generalSO.runSO(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	private TransferClass enterTheProperty(GenericList<GeneralDomain> list) throws Exception {
		TransferClass transferClass=new TransferClass();
		try {
		GenericList<GeneralDomain>list1=list;
		GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EntertPropertySO();
		generalSO.runSO(list);
		transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
		} catch (Exception e) {
			transferClass.setMessage(TransferClass_Message.EXCIST_PROPERTY.getValue());
			e.printStackTrace();
		}
		return transferClass;
	}
	
	

}
