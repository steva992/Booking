 package com.comtrade.controlerPL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.Control;

import com.comtrade.constants.URLS;
import com.comtrade.cache.Cache;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.property.ChangePictureURLSO;
import com.comtrade.so.property.EnterPropertySO;
import com.comtrade.so.property.GetMapSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLProperty{
	private static volatile ControlerBLProperty instance;
	
	private ControlerBLProperty() {
		
	}
	
	public static ControlerBLProperty getInstance() {
		if(instance==null) {
			synchronized (ControlerBLProperty.class) {
				if(instance==null) {
					instance=new ControlerBLProperty();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) throws Exception{
		TransferClass transferClass2=new TransferClass();
		User user=new User();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		switch(transferClass.getType_Of_operation()) {
			case REGISTRATION_PROPERTY:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				enterTheProperty(list,clientThread);
				break;
			case CHANGE_PICTURE_URL_HOTEL:
				list=(GenericList<GeneralDomain>) transferClass.getClient_Object_Request();
				ChangePictureURL(list,clientThread);
				break;		
			case BACK_ALL_FOR_USER_PANEL:
				user=(User) transferClass.getClient_Object_Request();
				AllForUserPanel(user,clientThread);
				break;	
				default:
					break;
		}
		
	}
	

	private void AllForUserPanel(User user, ClientThreadRequest clientThread) throws Exception {
		TransferClass transferClass=new TransferClass();
		try {
			
			GenericMap<String, GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
			GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>>generalSO=new GetMapSO();
			generalSO.runSO(map);
			
			transferClass.setServer_Object_Response(map);
			transferClass.setType_Of_operation(Type_Of_Operation.GET_MAP);
			transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
			clientThread.send(transferClass);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	private void ChangePictureURL(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) {
		try {
			
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new ChangePictureURLSO();
			generalSO.runSO(list);
			Controler_Thread.getInstance().sendToClientForUPDATEPICTURE(Type_Of_Data.PROPERTY, clientThread, TransferClass_Message.SUCCESSFUL_CHANGE,list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void enterTheProperty(GenericList<GeneralDomain> list, ClientThreadRequest clientThread) throws Exception {
		TransferClass transferClass =new TransferClass();
		try {
			GenericList<GeneralDomain>list1=list;
			GeneralSystemOperation<GenericList<GeneralDomain>>generalSO=new EnterPropertySO();
			
			generalSO.runSO(list1);
		
			if(list1.get(list1.size()-1) instanceof PaymentUserCard) {
					transferClass.setMessage(TransferClass_Message.EXCIST_PROPERTY.getValue());
				    transferClass.setType_Of_operation(Type_Of_Operation.JUST_MESSAGE);
				    transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
					clientThread.send(transferClass);
			}else {
					transferClass.setServer_Object_Response(list);
					transferClass.setMessage(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
					transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
					transferClass.setType_Of_operation(Type_Of_Operation.ADD);
				
				List<ClientThreadRequest>list2=Controler_Thread.getInstance().getListAllThread();
				for(int i=0;i<list1.size();i++) {
					list2.get(i).send(transferClass);
			    }
			
			}	
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	

}
