package com.comtrade.controlerClient;

import com.comtrade.transfer.TransferClass;

public class ControlerCode {
	private static volatile ControlerCode instance;
	private volatile int code;
	private volatile int number;
	
	
	
	
	public synchronized int getCode() {
		while(number==0) {
			getNumber();
		}
		return code;
	}

	

	public synchronized int getNumber() {
		return number;
	}



	public synchronized void setNumber(int number) {
		this.number = number;
	}



	public static ControlerCode getInstance() {
		
		if(instance==null) {
			
			synchronized (ControlerCode.class) {
				
				if(instance==null) {
					instance=new ControlerCode();
				}
			}
				instance=new ControlerCode();
		}
		
		return instance;
		
	}


	public void CheckTheOperation(TransferClass transferClass) {
		switch (transferClass.getType_Of_operation()) {
			case ADD:
				
				int code=(int) transferClass.getServer_Object_Response();
				this.number=1;
			
			break;
			default:
				break;
		}
		
	}
	
	
}
