package com.comtrade.render;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class Render extends DefaultListCellRenderer implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {

		ComboBoxClass comboBoxClass=(ComboBoxClass) value;
		setText(comboBoxClass.getCall());
		setIcon(comboBoxClass.getImage());
			setBackground(index % 2 == 0 ? new Color(0, 100, 255, 15) : (Color) UIManager.get("List.background"));
		return this;
		
	}

}
