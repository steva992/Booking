package com.comtrade.threads;

import com.comtrade.cache.Cache;
import com.comtrade.domain.TypeForDatabase;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.database.EnterToDatabase;

public class DatabaseThread extends Thread {
	
	public void run() {
		try {
			
			while(true) {
				
				Thread.sleep(30000);
				GenericList<TypeForDatabase>list=Cache.getInstance().getListForDatabase();
				if(list.size() != 0) {
					GeneralSystemOperation<GenericList<TypeForDatabase>>generalSO=new EnterToDatabase();
					generalSO.runSO(list);
				}
				
				Cache.getInstance().setDatabaseListToNull();
			}
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
