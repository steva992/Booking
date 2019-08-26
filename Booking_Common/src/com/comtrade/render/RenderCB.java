package com.comtrade.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class RenderCB extends DefaultListCellRenderer implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index,
		boolean isSelected, boolean cellHasFocus) {

		ComboBoxClass comboBoxClass=(ComboBoxClass) value;
		
		setText(comboBoxClass.getCall());
		setIcon(comboBoxClass.getImage());
		
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		if(isSelected) {
			setBackground(Color.blue);
			setForeground(Color.WHITE);
		}
			
		return this;
		
	}

}
