package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Property;
import com.comtrade.controlerComboBox.ControlerComboBox;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.panel.admin.Admin_Panel;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;

import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Property_Created extends JPanel {
	private JTextField tfNameOfProperty;
	private JTextField tfAdress_Street;
	private User user;
	private User_Info user_info;
	private JTextField tfAdress_Number;
	private JComboBox cbTypeOfProperty;
	private JTextField tfLatitude;
	private JTextField tfLongitude;
	private JComboBox cbCity,cbCountry;
	private List<List<String>> list = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public Property_Created(User user,User_Info user_info) {
		list=ControlerComboBox.getInstance().fillList();
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
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
		
		JLabel lblCity = new JLabel(" City *");
		lblCity.setBounds(21, 282, 147, 28);
		add(lblCity);
		
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
				String Country=cbCountry.getSelectedItem().toString();
				String City=cbCity.getSelectedItem().toString();
				String Adress_Number=tfAdress_Number.getText();
				String Adress_Street=tfAdress_Street.getText();
				String Latitude=tfLatitude.getText();
				String Longitude=tfLongitude.getText();
				boolean testField=fieldValidityProperty(name,Adress_Number,Adress_Street,Latitude,Longitude);
				boolean AllFieldCompleted=FieldComplete(name,Adress_Number,Adress_Street,Latitude,Longitude);
				if(testField) {
					if(AllFieldCompleted) {
						Property property=new Property();
						Property_Picutre_Album property_picture_album=new Property_Picutre_Album();
						property.setName(name);
						property.setRating(0.0);
						property.setType_Of_Property(type_Of_Property);
						Adress adress=new Adress();
						adress.setStreet(Adress_Street);
						adress.setCity(City);
						adress.setHouseNumber(Integer.parseInt(Adress_Number));
						adress.setCountry(Country);
						GeoLocation geoLocation=new GeoLocation();
						geoLocation.setLatitude(Double.parseDouble(Latitude));
						geoLocation.setLongitude(Double.parseDouble(Longitude));
						property_picture_album.setPicutre_URL(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg");
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(user_info);
						list.add(property);
						list.add(adress);
						list.add(geoLocation);
						list.add(property_picture_album);
						try {
							TransferClass transferClass=ControlerKI.getInstance().enterProperty(list);
							String poruka=transferClass.getMessage();
							if(poruka.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
								JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
								
								File userFile=new File(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername());
								if(!userFile.exists()) {
									userFile.mkdir();
								}
								File propertyFile=new File(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getType_Of_Property()+"/"+name);
								if(!propertyFile.exists()) {
									propertyFile.mkdir();
									
								}
								if(user.getId() !=0) {
									JPanel admin=null;
									try {
										admin = new Admin_Panel(user);
									} catch (URISyntaxException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									Application.setPanelOnLayeredPane(admin);
								}else {
									JPanel login=new Login();
									Application.setPanelOnLayeredPane(login);
								}
								
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
		button_1.setBounds(21, 668, 212, 42);
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
		
		 cbCountry = new JComboBox();
		 cbCountry.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		cbCity.removeAllItems();
		 		addItemToCity();
		 	}
		 });
		cbCountry.setBounds(21, 253, 212, 30);
		add(cbCountry);
		
		 cbCity = new JComboBox();
		cbCity.setBounds(21, 321, 212, 30);
		add(cbCity);
		
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setBounds(25, 518, 164, 28);
		add(lblLatitude);
		
		tfLatitude = new JTextField();
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(21, 545, 212, 28);
		add(tfLatitude);
		
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setBounds(21, 584, 164, 28);
		add(lblLongitude);
		
		tfLongitude = new JTextField();
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(21, 615, 212, 28);
		add(tfLongitude);
		
		JLabel label = new JLabel("20.4464");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(254, 545, 141, 28);
		add(label);
		
		JLabel label_1 = new JLabel("40.5567");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setBounds(254, 615, 141, 28);
		add(label_1);
		addItemAtPropertyComboBox();
		addItemToCountry();
		addItemToCity();
	}
	
	private void addItemToCity() {
		String country=cbCountry.getSelectedItem().toString();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).get(0).equals(country)) {
				for(int j=1;j<list.get(i).size();j++) {
					cbCity.addItem(list.get(i).get(j));
				}
			}
		}
		
	}

	private void addItemToCountry() {
		for(int i=0;i<list.size();i++) {
			cbCountry.addItem(list.get(i).get(0));
		}
		
	}

	private void addItemAtPropertyComboBox() {
		cbTypeOfProperty.addItem(Type_Of_Property.VILLA.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOSTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.HOTEL.getValue());
		cbTypeOfProperty.addItem(Type_Of_Property.MOTEL.getValue());
	}


	protected boolean FieldComplete(String name, String adress_Number, String adress_Street,String latitude,String longitude) {
		if(name.length()<1 || adress_Street.length()<1 ||  adress_Number.length()<1 || latitude.length() < 1 || longitude.length() < 1) {
			return false;
		}
		return true;
	}


	protected boolean fieldValidityProperty(String name, String adress_Number,
			String adress_Street, String latitude, String longitude) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean adress_Street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),adress_Street);
		boolean adress_Number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),adress_Number);
		boolean latitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),latitude);
		boolean longitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),longitude);
		
		if(name1 && adress_Number1 && adress_Street1) {
			return true;
		}
		
		if(name1==false) {
			tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(adress_Number1==false) {
			tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(adress_Street1==false) {
			tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(latitude1==false) {
			tfLatitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(longitude1==false) {
			tfLongitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		return false;
	}


	private void backBorderToGray() {
		tfAdress_Number.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfAdress_Street.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLatitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLongitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}
}
