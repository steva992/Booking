package com.comtrade.controlerComboBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.CountryesTxt;
import com.comtrade.constants.PicturesURL;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.transfer.TransferClass;

public class ControlerComboBox {
	private static ControlerComboBox instance;
	
	private ControlerComboBox() {
		
	}
	
	public static ControlerComboBox getInstance() {
		if(instance==null) {
			instance=new ControlerComboBox();
		}
		return instance;
		
	}

	public List<List<String>> fillList() {
		List<List<String>> list = new ArrayList<>();
		File file=new File(AbsolutePath.absolutePath()+CountryesTxt.ALL_COUNTRIES_AND_CITIES.getValue());
		Scanner sc=null;
		try  {
			sc=new Scanner(file);
			while(sc.hasNext()) {
				String line =sc.nextLine();
				String[]oneLine=line.split(";");
				List<String>city=new ArrayList<>();
				for(int i=0;i<oneLine.length;i++) {
					
						city.add(oneLine[i]);
						
				}
				
				list.add(city);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sc.close();
		}
		return list;
	}

	public List<List<String>> fillContryComboBox() {
		List<List<String>> list = new ArrayList<>();
		File file=new File(AbsolutePath.absolutePath()+CountryesTxt.ALL_COUNTRIES_CALL.getValue());
		Scanner sc=null;
		try  {
			sc=new Scanner(file);
			while(sc.hasNext()) {
				String line =sc.nextLine();
				String[]oneLine=line.split(";");
				List<String>call=new ArrayList<>();
				for(int i=0;i<oneLine.length;i++) {
					
						call.add(oneLine[i]);
						
				}
				
				list.add(call);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sc.close();
		}
		return list;
	}
}
