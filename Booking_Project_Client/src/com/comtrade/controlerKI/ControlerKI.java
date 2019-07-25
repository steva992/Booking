package com.comtrade.controlerKI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.communication.Communication;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.PaymentUserCard;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.reservation.Reservation;
import com.comtrade.transfer.TransferClass;

public class ControlerKI {
	private static ControlerKI instance;
	private TransferClass transferClass=new TransferClass();
	
	private ControlerKI() {
		
	}
	
	public static ControlerKI getInstance() {
		if(instance==null) {
			instance=new ControlerKI();
		}
		return instance;
		
	}
	
	private TransferClass backDataToJFrame(TransferClass transferClass) throws ClassNotFoundException, IOException {
		Communication.getInstance().send(transferClass);
		TransferClass transferClass2=Communication.getInstance().read();
		return transferClass2;
	}

	public TransferClass enterTheUserAndAdditionalUser(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
	}

	public TransferClass login(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.LOGIN_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}


	public TransferClass enterProperty(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_PROPERTY);
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
	}


	public TransferClass checkIfUserExcist(GeneralDomain generalDomain) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.CHECK_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(generalDomain);
		return backDataToJFrame(transferClass);
	}

	public TransferClass changePassword(GenericList<User> listUser) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PASSWORD_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(listUser);
		return backDataToJFrame(transferClass);
	}

	public TransferClass BackUserInfo_ForUser(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.RETURN_USER_INFO);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}

	public TransferClass changePictureURLUser(User_Info user_info) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PICTURE_URL_USER);
		transferClass.setClient_Object_Request(user_info);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass AllAboutProperty(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setType_Of_operation(Type_Of_Operation.BACK_ALL_ABOUT_PROPERTY);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}

	public TransferClass changPictureURLHotel(Property_Picutre_Album current) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setType_Of_operation(Type_Of_Operation.CHANGE_PICTURE_URL_HOTEL);
		transferClass.setClient_Object_Request(current);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass enterRoomAndRoomInfo(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_ROOM);
		transferClass.setType_Of_Data(Type_Of_Data.ROOM);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass updateUser(User_Info user_info) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.UPDATE_USER);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(user_info);
		return backDataToJFrame(transferClass);
	}

	public TransferClass updateProperty(GenericList<GeneralDomain> list) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.UPDATE_PROPERTY);
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setClient_Object_Request(list);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass updateRoom(GenericList<GeneralDomain> listRoom) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.UPDATE_ROOM);
		transferClass.setType_Of_Data(Type_Of_Data.ROOM);
		transferClass.setClient_Object_Request(listRoom);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass enterDiscount(Discount discount) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.REGISTRATION_DISCOUNT);
		transferClass.setType_Of_Data(Type_Of_Data.DISCOUNT);
		transferClass.setClient_Object_Request(discount);
		return backDataToJFrame(transferClass);
	}

	public TransferClass deleteDiscount(Discount discount) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.DELETE_DISCOUNT);
		transferClass.setType_Of_Data(Type_Of_Data.DISCOUNT);
		transferClass.setClient_Object_Request(discount);
		return backDataToJFrame(transferClass);
		
	}

	public TransferClass backALLUserAndProperties() throws ClassNotFoundException, IOException {
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setType_Of_operation(Type_Of_Operation.BACK_ALL_ADMINS);
		return backDataToJFrame(transferClass);
	}

	public TransferClass backAllForUserPanel(User user) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_Data(Type_Of_Data.PROPERTY);
		transferClass.setType_Of_operation(Type_Of_Operation.BACK_ALL_FOR_USER_PANEL);
		transferClass.setClient_Object_Request(user);
		return backDataToJFrame(transferClass);
	}

	public TransferClass enterPaymentCard(PaymentUserCard payment) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.ADD_PAYMENT_CARD);
		transferClass.setType_Of_Data(Type_Of_Data.USER);
		transferClass.setClient_Object_Request(payment);
		return backDataToJFrame(transferClass);
	}

	public TransferClass enterReservation(Reservation reservation) throws ClassNotFoundException, IOException {
		transferClass.setType_Of_operation(Type_Of_Operation.ADD_NEW_REGISTRATION);
		transferClass.setType_Of_Data(Type_Of_Data.RESERVATON);
		transferClass.setClient_Object_Request(reservation);
		return backDataToJFrame(transferClass);
		
	}
	

	
}
