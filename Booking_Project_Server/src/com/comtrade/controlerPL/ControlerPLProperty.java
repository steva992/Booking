package com.comtrade.controlerPL;

import java.sql.SQLException;

import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.property.BackAllForProperty;
import com.comtrade.so.property.ChangePictureURLPropertySO;
import com.comtrade.so.property.EntertPropertySO;
import com.comtrade.so.user.ChangePictureURLUserSO;
import com.comtrade.transfer.TransferClass;

public class ControlerPLProperty {
private static ControlerPLProperty instance;
	
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
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_PROPERTY:
				GenericList<GeneralDomain>list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				transferClass2=enterTheProperty(list);
				break;
			case BACK_ALL_ABOUT_PROPERTY:
				User user=(User) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(BackAllAboutProperty(user));
				break;
			case CHANGE_PICTURE_URL_HOTEL:
				Property_Picutre_Album property_Picutre_Album=(Property_Picutre_Album) transferClass.getClient_Object_Request();
				transferClass2.setServer_Object_Response(ChangePictureURL(property_Picutre_Album));
				break;	
				default:
					break;
		}
		return transferClass2;
		
	}

	private Property_Picutre_Album ChangePictureURL(Property_Picutre_Album property_Picutre_Album) {
		TransferClass transferClass=new TransferClass();
		GeneralSystemOperation<Property_Picutre_Album>genericSO=new ChangePictureURLPropertySO();
		try {
			genericSO.runSO(property_Picutre_Album);
			transferClass.setServer_Object_Response(TransferClass_Message.SUCCESSFUL_CHANGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property_Picutre_Album;
	}

	private GenericList<GeneralDomain> BackAllAboutProperty(User user) {
		TransferClass transferClass=new TransferClass();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		list.add(user);
		try {
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new BackAllForProperty();
			generalSO.runSO(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
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
