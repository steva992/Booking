package com.comtrade.view.panel;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Property;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Info;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Begin;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Property_Created extends JPanel {
	private JTextField tfNameOfProperty;
	private JTextField tfCountry;
	private JTextField tfCity;
	private JTextField tfAdress_Street;
	private User user;
	private User_Info user_info;
	private JTextField tfAdress_Number;
	private JComboBox cbTypeOfProperty;

	/**
	 * Create the panel.
	 */
	public Property_Created(User user,User_Info user_info) {
		setBounds(100,100,800,600);
		this.user=user;
		this.user_info=user_info;
		setLayout(null);
		
		JButton btfBackToLog = new JButton("<< <  Step 1");
		btfBackToLog.setFont(new Font("Tahoma", Font.BOLD, 12));
		btfBackToLog.setBounds(21, 21, 131, 42);
		add(btfBackToLog);
		
		JLabel lblNameOfProperty = new JLabel("Name Of Property *");
		lblNameOfProperty.setBounds(21, 83, 147, 28);
		add(lblNameOfProperty);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setColumns(10);
		tfNameOfProperty.setBounds(21, 122, 212, 34);
		add(tfNameOfProperty);
		
		JLabel lblTypeOfProperty = new JLabel("Type Of Property *");
		lblTypeOfProperty.setBounds(21, 153, 147, 28);
		add(lblTypeOfProperty);
		
		cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setBounds(21, 183, 212, 34);
		add(cbTypeOfProperty);
		
		JLabel lblCountry = new JLabel("Country *");
		lblCountry.setBounds(21, 228, 147, 28);
		add(lblCountry);
		
		tfCountry = new JTextField();
		tfCountry.setColumns(10);
		tfCountry.setBounds(21, 253, 212, 34);
		add(tfCountry);
		
		JLabel lblCity = new JLabel(" City *");
		lblCity.setBounds(21, 282, 147, 28);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(21, 307, 212, 34);
		add(tfCity);
		
		JLabel lblAdressstreet = new JLabel("Adress_Street *");
		lblAdressstreet.setBounds(21, 347, 147, 28);
		add(lblAdressstreet);
		
		tfAdress_Street = new JTextField();
		tfAdress_Street.setColumns(10);
		tfAdress_Street.setBounds(21, 375, 212, 34);
		add(tfAdress_Street);
		
		JLabel lblAdressnumber = new JLabel("Adress_Number *");
		lblAdressnumber.setBounds(21, 420, 147, 28);
		add(lblAdressnumber);
		
		tfAdress_Number = new JTextField();
		tfAdress_Number.setColumns(10);
		tfAdress_Number.setBounds(21, 462, 212, 34);
		add(tfAdress_Number);
		
		JButton button_1 = new JButton("Sign Up You \nand Your Property");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfNameOfProperty.getText();
				String type_Of_Property=cbTypeOfProperty.getSelectedItem().toString();
				String Country=tfCountry.getText();
				String City=tfCity.getText();
				String Adress_Number=tfAdress_Number.getText();
				String Adress_Street=tfAdress_Street.getText();
				boolean testField=fieldValidityProperty(name,Country,City,Adress_Number,Adress_Street);
				boolean AllFieldCompleted=FieldComplete(name,Country,City,Adress_Number,Adress_Street);
				if(testField) {
					if(AllFieldCompleted) {
						Property property=new Property();
						Property_Info property_info=new Property_Info();
						Property_Picutre_Album property_picture_album=new Property_Picutre_Album();
						property.setName(name);
						property.setRating(0.0);
						property.setType_Of_Property(type_Of_Property);
						property_info.setAdress_street(Adress_Street);
						property_info.setCity(City);
						property_info.setCountry(Country);
						property_info.setCoordinates("20X30");
						property_info.setAdress_number(Integer.parseInt(Adress_Number));
						property_picture_album.setPicutre_URL(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+"Hotel.jpg");
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(user_info);
						list.add(property);
						list.add(property_info);
						list.add(property_picture_album);
						try {
							TransferClass transferClass=ControlerKI.getInstance().enterProperty(list);
							String poruka=transferClass.getMessage();
							if(poruka.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
								JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
								File userFile=new File(PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername());
								if(!userFile.exists()) {
									userFile.mkdir();
								}
								File propertyFile=new File(PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+name);
								if(!propertyFile.exists()) {
									propertyFile.mkdir();
								}
								JPanel login=new Login();
								Begin.setPanelOnLayeredPane(login);
							}else if(poruka.equals(TransferClass_Message.EXCIST_PROPERTY.getValue())){
								JOptionPane.showMessageDialog(null,TransferClass_Message.EXCIST_PROPERTY.getValue());
							}
			
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
				}
			}
		});
		button_1.setBounds(21, 521, 212, 42);
		add(button_1);
		
		JLabel label_6 = new JLabel("Example");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(254, 125, 141, 28);
		add(label_6);
		
		JLabel label_7 = new JLabel("Example");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_7.setBounds(254, 239, 141, 28);
		add(label_7);
		
		JLabel label_8 = new JLabel("Example");
		label_8.setForeground(Color.RED);
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_8.setBounds(254, 307, 141, 28);
		add(label_8);
		
		JLabel label_9 = new JLabel("Example Example");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_9.setBounds(254, 375, 141, 28);
		add(label_9);
		
		JLabel label_10 = new JLabel("15");
		label_10.setForeground(Color.RED);
		label_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_10.setBounds(254, 462, 141, 28);
		add(label_10);
		addItemAtComboBox();
	}
	
	private void addItemAtComboBox() {
		cbTypeOfProperty.addItem(Type_Of_Property.VILLA.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOSTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.MOTEL.getValue());
	}


	protected boolean FieldComplete(String name, String state, String city, String adress_Number, String adress_Street) {
		if(name.length()<1 || state.length()<1 || city.length()<1 || adress_Street.length()<1 ||  adress_Number.length()<1) {
			return false;
		}
		return true;
	}


	protected boolean fieldValidityProperty(String name, String country, String city, String adress_Number,
			String adress_Street) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean country1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),country);
		boolean city1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),city);
		boolean adress_Street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),adress_Street);
		boolean adress_Number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),adress_Number);
		
		if(name1 &&  country1 && city1 && adress_Number1 && adress_Street1) {
			return true;
		}
		
		if(name1==false) {
			tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(country1==false) {
			tfCountry.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(city1==false) {
			tfCity.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(adress_Number1==false) {
			tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(adress_Street1==false) {
			tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		return false;
	}


	private void backBorderToGray() {
		tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfCountry.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfCity.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}
}
