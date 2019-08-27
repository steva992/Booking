package com.comtrade.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.URLS;

	public class TimeThread extends Thread{
		private JLabel label;
	
	
	
		public TimeThread(JLabel label) {
			super();
			this.label = label;
		}



		public void run() {
			
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			
				while(true) {
					
					label.setText(simpleDateFormat.format(new Date()));
					try {
						
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
}
