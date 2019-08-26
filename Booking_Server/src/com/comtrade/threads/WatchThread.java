package com.comtrade.threads;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JLabel;


public class WatchThread extends Thread {
	private JLabel label,labelDate;
	
	
	
	public WatchThread(JLabel label, JLabel lblDate) {
		
		super();
		this.label = label;
		this.labelDate=lblDate;
		
	}



	public void run() {
		
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
		
			while(true) {
				
				label.setText(simpleDateFormat.format(new Date()));
				labelDate.setText(String.valueOf(LocalDate.now()));
				
				try {
					
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
