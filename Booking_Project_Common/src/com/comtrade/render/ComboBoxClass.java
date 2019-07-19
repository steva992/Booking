package com.comtrade.render;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ComboBoxClass extends DefaultListCellRenderer{
	private ImageIcon image;
	private String call;
	
	public ComboBoxClass(ImageIcon object, String call) {
		super();
		this.image = object;
		this.call = call;
	}
	public ComboBoxClass(Object setNewPicutreOnComboBox) {
		// TODO Auto-generated constructor stub
	}
	public ImageIcon getImage() {
		return image;
	}
	public void setImage(ImageIcon image) {
		this.image = image;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	
	
}
