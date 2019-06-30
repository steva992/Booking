package com.comtrade.transfer;

import java.awt.Window.Type;
import java.io.Serializable;

import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;

public class TransferClass implements Serializable {
	
	private int type_Of_operation;
	private String type_Of_Data;
	private Object client_Object_Request;
	private Object server_Object_Response;
	private String message;
	
	public TransferClass() {
		
	}

	public TransferClass(int type_Of_operation, String type_Of_Data, Object client_Object_Request,
			Object server_Object_Response, String message) {
		super();
		this.type_Of_operation = type_Of_operation;
		this.type_Of_Data = type_Of_Data;
		this.client_Object_Request = client_Object_Request;
		this.server_Object_Response = server_Object_Response;
		this.message = message;
	}

	public int getType_Of_operation() {
		return type_Of_operation;
	}

	public void setType_Of_operation(int type_Of_operation) {
		this.type_Of_operation = type_Of_operation;
	}

	public String getType_Of_Data() {
		return type_Of_Data;
	}

	public void setType_Of_Data(String type_Of_Data) {
		this.type_Of_Data = type_Of_Data;
	}

	public Object getClient_Object_Request() {
		return client_Object_Request;
	}

	public void setClient_Object_Request(Object client_Object_Request) {
		this.client_Object_Request = client_Object_Request;
	}

	public Object getServer_Object_Response() {
		return server_Object_Response;
	}

	public void setServer_Object_Response(Object server_Object_Response) {
		this.server_Object_Response = server_Object_Response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	
	
}
