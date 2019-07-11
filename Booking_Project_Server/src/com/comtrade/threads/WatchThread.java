package com.comtrade.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

import com.comtrade.constants.Threads_Constant;

public class WatchThread extends Thread {
	private JLabel label;
	
	
	
	public WatchThread(JLabel label) {
		super();
		this.label = label;
	}



	public void run() {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
			while(true) {
				label.setText(simpleDateFormat.format(new Date()));
				try {
					Thread.sleep(Threads_Constant.SLEEP.getValue());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
