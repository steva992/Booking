package com.comtrade.constants;

import java.io.File;

public class AbsolutePath {
	
	public static final String SERVER="smtp.gmail.com";
	public static final String username = "stevdzan099@gmail.com";
    public static final String password = "lozinka991";
    
	public static String absolutePath() {
		StringBuffer stringBuffer=new StringBuffer();
		File file1=new File(".");
		String file=file1.getAbsolutePath().replace('\\','/');
		file=file.replaceAll("Client","Server");
		stringBuffer.append(file.substring(0,file.length()-2));
		return stringBuffer.toString();
		
	}
}
