package com.comtrade.controlerBL;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.user.User;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.codes.EnterNewCodeSO;
import com.comtrade.threads.ClientThreadRequest;
import com.comtrade.transfer.TransferClass;

public class ControlerBLCode {
	private static volatile ControlerBLCode instance;
	

	private ControlerBLCode() {
		
	}
	
	public static ControlerBLCode getInstance() {
		if(instance==null) {
			synchronized (ControlerBLCode.class) {
				if(instance==null) {
					instance=new ControlerBLCode();
				}
			}
		}
		return instance;
		
	}

	public void CheckTheOperation(TransferClass transferClass, ClientThreadRequest clientThread) {
		TransferClass transferClass2=new TransferClass();
		switch(transferClass.getType_Of_operation()) {
			case ADD:
				
				int code=(int) transferClass.getClient_Object_Request();
				enterToListCodes(code,clientThread);
				break;
				
				default:
					break;
		}
	}

	private void enterToListCodes(int code, ClientThreadRequest clientThread) {
		
		TransferClass transferClass2=new TransferClass();
		User user=new User();
		user.setId(code);
		GeneralSystemOperation<User>generalSO=new EnterNewCodeSO();
		
		try {
			generalSO.runSO(user);
			
			transferClass2.setServer_Object_Response(user.getId());
			transferClass2.setType_Of_operation(Type_Of_Operation.ADD);
			transferClass2.setType_Of_Data(Type_Of_Data.CODE);
			clientThread.send(transferClass2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
