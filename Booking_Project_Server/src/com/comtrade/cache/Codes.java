package com.comtrade.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.CountryesTxt;
import com.comtrade.controlerBL.ControlerBLCode;

public class Codes {
	
	private volatile List<Integer>listCodes;
	private static volatile Codes instance;
	
	private Codes() {
		listCodes=fillFromTXT();
	}
	
	private List<Integer> fillFromTXT() {
		
		List<Integer>list=new ArrayList<>();
		File file=new File(AbsolutePath.absolutePath()+CountryesTxt.ALL_CODES.getValue());
		Scanner sc=null;
		try  {
			sc=new Scanner(file);
			while(sc.hasNext()) {
				String line =sc.nextLine();
				list.add(Integer.parseInt(line));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sc.close();
		}
		return list;
		
	}

	public static Codes getInstance() {
		
		if(instance==null) {
			synchronized (Codes.class) {
				if(instance==null) {
					instance=new Codes();
				}
			}
		}
		return instance;
		
	}

	public synchronized List<Integer> getListCodes() {
		
		return listCodes;
		
	}
	
	public synchronized int addCode(int code) {
		
		if(!listCodes.contains(code)) {
			listCodes.add(code);
			addToTXT(code);
			return 0;
		}else {
			return 1;
		}
		
	}

	private void addToTXT(int code) 
	{
		File file=new File(AbsolutePath.absolutePath()+CountryesTxt.ALL_CODES.getValue());
		FileWriter fileW=null;
		PrintWriter printW = null;
		try {
			fileW=new FileWriter(file,true);
			printW=new PrintWriter(fileW,true);
			printW.println(code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
