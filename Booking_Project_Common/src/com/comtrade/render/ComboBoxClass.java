package com.comtrade.render;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ComboBoxClass {
	private Icon image;
	private String call;
	
	public ComboBoxClass(String call,Icon object) {
		this.image = object;
		this.call = call;
	}

	public Icon getImage() {
		return image;
	}

	public void setImage(Icon image) {
		this.image = image;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}
	
	
	
	
	
}
