package com.comtrade.domain.user;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.URL;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.genericClasses.GenericList;

public class User implements GeneralDomain,Serializable {
	private int id;
	private String username;
	private String password;
	private String status;
	
	
	public User() {
		
	}

	public User(int id, String username, String password, String status) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String returnNameOfTable() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public String returnNameOfColumns() {
		// TODO Auto-generated method stub
		return "(Password,Status,Username)";
	}

	@Override
	public String returnQuestionnaires() {
		// TODO Auto-generated method stub
		return " values(?,?,?)";
	}

	@Override
	public PreparedStatement setPS(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1,password);
		preparedStatement.setString(2,status);
		preparedStatement.setString(3,username);
			return preparedStatement;
	}

	@Override
	public String printIDOfParrentTable() {
		// TODO Auto-generated method stub
		return "Username";
	}



	@Override
	public GeneralDomain setResultSetForOne(ResultSet resultSet) {
		User user=new User();
		try {
			if(resultSet.next()) {
				user.setId(resultSet.getInt("id_User"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword((resultSet.getString("Password")));
				user.setStatus((resultSet.getString("Status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public PreparedStatement setPSforID(PreparedStatement preparedStatement) {
		try {
			preparedStatement.setString(1,username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;
	}

	@Override
	public GenericList<GeneralDomain> setResultSetForMore(ResultSet resultSet) {
		GenericList<GeneralDomain>listUsers=new GenericList<GeneralDomain>();
		try {
			
			while(resultSet.next()) {
				User user=new User();
				user.setId(resultSet.getInt("id_User"));
				user.setUsername(resultSet.getString("Username"));
				user.setPassword((resultSet.getString("Password")));
				user.setStatus((resultSet.getString("Status")));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listUsers;
	}

	@Override
	public String setColumnForUpdate() {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("Password=? ,");
		stringBuffer.append("Status=? ");
		return stringBuffer.toString();
	}

	@Override
	public String printUniqueValueOfTable() {
		// TODO Auto-generated method stub
		return "User_username";
	}

	
	public void enterDataOnTXTFle(User user,String Type_Of_Operation,String object) throws IOException {
		String object1=null;
		if(Type_Of_Operation.equals(Type_OF_Operation_TXT.USER_LOGOUT.getValue())) {
			object1="Duration on site";
			object=calculateTimeOnSite(Long.parseLong(object));
		}else {
			object1="Object";
		}
		String url=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.username+"/Log.txt";
		Path path;
		File file;
		FileWriter fileW=null;
		PrintWriter printW = null;
		if(Paths.get(url) == null){
			file = new File(url);
			fileW=new FileWriter(file);
			printW=new PrintWriter(fileW,true);
		}else {
			file=Paths.get(url).toFile();
			fileW=new FileWriter(file,true);
			printW=new PrintWriter(fileW,true);
		}
		
		try {
			printW.println("-------------------------------------------");
			printW.println("Username : "+user.username);
			printW.println("Status : "+user.status);
			printW.println("Operation : "+Type_Of_Operation);
			printW.println(object1+" : "+object);
			printW.println("Date : "+LocalDate.now());
			printW.println("Time : "+LocalTime.now());
			printW.println("--------------------------------------------");
			printW.println("\n");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private String calculateTimeOnSite(long time) {
		StringBuilder sb=new StringBuilder();
		long rest=0;
		long days=time/86400000;
		rest=time%86400000;
		long hours=rest/3600000;
		rest=time%3600000;
		long minutes=rest/60000;
		rest=time%60000;
		long second=rest/1000;
		rest=time%1000;
		long milisecond=rest;
		sb.append("(Days-"+days+" ");
		sb.append("Hours-"+hours+" ");
		sb.append("Minutes-"+minutes+" ");
		sb.append("Seconds-"+second+" ");
		sb.append("MiliSecond-"+rest+" )");
		return sb.toString(); 
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void updatePictureURL(GenericList<GeneralDomain> list) {
		// TODO Auto-generated method stub
		
	}

	

	
	

}
