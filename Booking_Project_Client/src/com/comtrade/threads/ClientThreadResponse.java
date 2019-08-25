package com.comtrade.threads;

import java.io.IOException;

import com.comtrade.communication.Communication;
import com.comtrade.controlerClient.ControlerCode;
import com.comtrade.controlerClient.ControlerDiscount;
import com.comtrade.controlerClient.ControlerMessage;
import com.comtrade.controlerClient.ControlerProperty;
import com.comtrade.controlerClient.ControlerRating;
import com.comtrade.controlerClient.ControlerReservation;
import com.comtrade.controlerClient.ControlerRoom;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.transfer.TransferClass;

public class ClientThreadResponse extends Thread{
	
	@Override
		public void run() {
			
			while(true) {
				TransferClass transferClass=new TransferClass();
				
				try {
					
					System.out.println("stiglo je na klienta");
					transferClass=Communication.getInstance().read();
					processServerRequest(transferClass);
				}catch(Exception e) {
					System.out.println("nema konekcije");
					System.exit(0);
					break;
				}
			}
		}

			public void processServerRequest(TransferClass transferClass) {
				switch(transferClass.getType_Of_Data()) {
				case USER:
				
					ControlerUser.getInstance().CheckTheOperation(transferClass);
					
					break;
					
				case PROPERTY:
					
					ControlerProperty.getInstance().CheckTheOperation(transferClass);
					
					break;
				case ROOM:
					
					ControlerRoom.getInstance().CheckTheOperation(transferClass);
					
					break;
				case DISCOUNT:
					
					ControlerDiscount.getInstance().CheckTheOperation(transferClass);
					
					break;
				case RESERVATION:
					
					ControlerReservation.getInstance().CheckTheOperation(transferClass);
					
					break;	
				case RATING:
					
					ControlerRating.getInstance().CheckTheOperation(transferClass);
					
					break;	
				case CODE:
					
					ControlerCode.getInstance().CheckTheOperation(transferClass);
					
					break;	
				case MESSAGE:
					
					ControlerMessage.getInstance().CheckTheOperation(transferClass);
					
					break;	
					
				default:
					break;
				}
				
			}
		
		}


