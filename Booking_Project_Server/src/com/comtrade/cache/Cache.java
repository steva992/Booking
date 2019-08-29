package com.comtrade.cache;

import java.util.ArrayList;
import java.util.List;

import com.comtrade.controlerBL.ControlerBLDiscount;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.TypeForDatabase;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.database.BackFromDatabase;
import com.comtrade.threads.ClientThreadRequest;

public class Cache {
	
	private volatile GenericList<TypeForDatabase>listForDatabase;
	private  GenericMap<String, GenericList<GeneralDomain>>map;
	private volatile GenericList<GeneralDomain>list;
	private static volatile Cache instance;
	
	
	public synchronized GenericList<TypeForDatabase> getListForDatabase() {
		return listForDatabase;
	}

	private Cache() throws Exception {
		list=new GenericList<GeneralDomain>();
		listForDatabase=new GenericList<TypeForDatabase>();
	}
	
	public  static Cache getInstance() throws Exception {
		if(instance==null) {
			synchronized (Cache.class) {
				if(instance==null) {
					instance=new Cache();
				}
			}
		}
		return instance;
		
	}
	
	
	public void setDatabaseListToNull() {
		listForDatabase=null;
		listForDatabase=new GenericList<TypeForDatabase>();
	}

	public synchronized GenericMap<String, GenericList<GeneralDomain>> getMap() {
		return map;
	}


	public synchronized void setMap(GenericMap<String, GenericList<GeneralDomain>> map) {
		this.map = map;
	}

	public synchronized void addListInMap(String type,GenericList<GeneralDomain> generalDomain) {
		synchronized (Cache.class) {
			map.put(type, generalDomain);
		}
			
			for(int i=0;i<generalDomain.size();i++) {
				TypeForDatabase tfd=new TypeForDatabase();
				tfd.setType(1);
				tfd.setGeneralDomain(generalDomain.get(i));
				synchronized (Cache.class) {
					listForDatabase.add(tfd);
				}
			}
	}
	
	public  void addInMap(String type,GeneralDomain generalDomain) {
		TypeForDatabase tfd=new TypeForDatabase();
		tfd.setType(1);
		tfd.setGeneralDomain(generalDomain);
		synchronized (Cache.class) {
			listForDatabase.add(tfd);
			map.get(type).add(generalDomain);
		}
	}
	
	public synchronized void deleteFromMap(String type,GeneralDomain generalDomain) {
		for(int i=0;i<map.get(type).size();i++) {
			if(map.get(type).get(i).equals(generalDomain)) {
				map.get(type).delete(generalDomain);
			}
		}
	}
	
	
	public synchronized void updateInMap(String type,GeneralDomain generalDomain) {
		for(int i=0;i<map.get(type).size();i++) {
			if(map.get(type).get(i).equals(generalDomain)) {
				map.get(type).set(i,generalDomain);
			}
		}
		TypeForDatabase tfd=new TypeForDatabase();
		tfd.setType(2);
		tfd.setGeneralDomain(generalDomain);
		listForDatabase.add(tfd);
	}

	public synchronized boolean checkUserInMap(User user) {
		boolean excist=false;
		if(user != null) {
			if(map.get(user.getUsername()) != null) {
				excist=true;
			}
		}
		
		return excist;
	}

	public  void updateUserPassword(User user) {
		synchronized (Cache.class) {
			((User) map.get(user.getUsername()).get(0)).setPassword(user.getPassword());
		}
		TypeForDatabase tfd=new TypeForDatabase();
		tfd.setType(2);
		tfd.setGeneralDomain(user);
		synchronized (Cache.class) {
			listForDatabase.add(tfd);
		}
	}

	public synchronized User checkLoginUserInMap(User user)  throws Exception{
		if(((User) map.get(user.getUsername()).get(0)).getUsername() != null && ((User) map.get(user.getUsername()).get(0)).getPassword() != null) {
			String username=((User) map.get(user.getUsername()).get(0)).getUsername();
			String password=((User) map.get(user.getUsername()).get(0)).getPassword();
			if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				user=(User) map.get(user.getUsername()).get(0);
				user.setStatus(user.getStatus());
			}else {
				user=null;
			}
		}
		
		return user;
	}

	public  boolean checkUserPassword(User user) {
		String password;
		synchronized (Cache.class) {
			password=((User) map.get(user.getUsername()).get(0)).getPassword();	
		}
		String password2=user.getPassword();
		
		if(password.equals(password2)) {
			return true;
		}
		
		return false;
	}




	public boolean checkPropertyInMap(Property property) {
		boolean excist=false;
		if(property != null) {
			if(map.get(property.getName()) != null) {
				excist=true;
			}
		}
		return excist;
	}




	
	
	
	
	
	
	
}
