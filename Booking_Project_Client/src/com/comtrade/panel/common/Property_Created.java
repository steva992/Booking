package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.w3c.dom.UserDataHandler;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Property;
import com.comtrade.controlerKI.ControlerComboBox;
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
import com.toedter.calendar.JDayChooser;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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
	private JLabel label_2,label_11,label_3,label_4,label_5;
	private int rating=0;

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
		btfBackToLog.setBackground(Color.WHITE);
		btfBackToLog.setForeground(Color.RED);
		btfBackToLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel signUp=new SignUp();
				Application.setPanelOnLayeredPane(signUp);
			}
		});
		btfBackToLog.setFont(new Font("Castellar", Font.BOLD, 14));
		btfBackToLog.setBounds(21, 21, 287, 42);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_BACK_TO.getValue(),btfBackToLog);
		add(btfBackToLog);
		
		JLabel lblNameOfProperty = new JLabel("Name Of Property *");
		lblNameOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		lblNameOfProperty.setBounds(21, 83, 212, 28);
		add(lblNameOfProperty);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		tfNameOfProperty.setColumns(10);
		tfNameOfProperty.setBounds(21, 122, 287, 28);
		add(tfNameOfProperty);
		
		JLabel lblTypeOfProperty = new JLabel("Type Of Property *");
		lblTypeOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		lblTypeOfProperty.setBounds(21, 153, 212, 28);
		add(lblTypeOfProperty);
		
		cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setFont(new Font("Castellar", Font.BOLD, 12));
		cbTypeOfProperty.setBounds(21, 183, 287, 34);
		add(cbTypeOfProperty);
		
		JLabel lblCountry = new JLabel("Country *");
		lblCountry.setFont(new Font("Castellar", Font.BOLD, 12));
		lblCountry.setBounds(21, 228, 212, 28);
		add(lblCountry);
		
		JLabel lblCity = new JLabel(" City *");
		lblCity.setFont(new Font("Castellar", Font.BOLD, 12));
		lblCity.setBounds(21, 294, 212, 28);
		add(lblCity);
		
		JLabel lblAdressstreet = new JLabel("Adress_Street *");
		lblAdressstreet.setFont(new Font("Castellar", Font.BOLD, 12));
		lblAdressstreet.setBounds(21, 362, 212, 13);
		add(lblAdressstreet);
		
		tfAdress_Street = new JTextField();
		tfAdress_Street.setFont(new Font("Castellar", Font.BOLD, 12));
		tfAdress_Street.setColumns(10);
		tfAdress_Street.setBounds(21, 381, 287, 28);
		add(tfAdress_Street);
		
		JLabel lblAdressnumber = new JLabel("Adress_Number *");
		lblAdressnumber.setFont(new Font("Castellar", Font.BOLD, 12));
		lblAdressnumber.setBounds(21, 420, 212, 20);
		add(lblAdressnumber);
		
		tfAdress_Number = new JTextField();
		tfAdress_Number.setFont(new Font("Castellar", Font.BOLD, 12));
		tfAdress_Number.setColumns(10);
		tfAdress_Number.setBounds(21, 450, 287, 28);
		add(tfAdress_Number);
		
		JButton btnSignUpYou = new JButton("Sign Up You \r\n+ Property");
		btnSignUpYou.setBackground(Color.WHITE);
		btnSignUpYou.setFont(new Font("Castellar", Font.BOLD, 10));
		btnSignUpYou.setForeground(Color.RED);
		btnSignUpYou.addActionListener(new ActionListener() {
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
						property.setRating(rating);
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
		btnSignUpYou.setBounds(21, 647, 287, 42);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_CREATED.getValue(),btnSignUpYou);
		add(btnSignUpYou);
		
		JLabel label_6 = new JLabel("Example");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Castellar", Font.BOLD, 14));
		label_6.setBounds(346, 125, 167, 28);
		add(label_6);
		
		JLabel label_9 = new JLabel("Example Example");
		label_9.setForeground(Color.BLACK);
		label_9.setFont(new Font("Castellar", Font.BOLD, 14));
		label_9.setBounds(346, 375, 177, 28);
		add(label_9);
		
		JLabel label_10 = new JLabel("15");
		label_10.setForeground(Color.BLACK);
		label_10.setFont(new Font("Castellar", Font.BOLD, 14));
		label_10.setBounds(346, 447, 167, 28);
		add(label_10);
		
		 cbCountry = new JComboBox();
		 cbCountry.setFont(new Font("Castellar", Font.BOLD, 12));
		 cbCountry.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		cbCity.removeAllItems();
		 		addItemToCity();
		 	}
		 });
		cbCountry.setBounds(21, 253, 287, 30);
		add(cbCountry);
		
		 cbCity = new JComboBox();
		 cbCity.setFont(new Font("Castellar", Font.BOLD, 12));
		cbCity.setBounds(21, 321, 287, 30);
		add(cbCity);
		
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setFont(new Font("Castellar", Font.BOLD, 12));
		lblLatitude.setBounds(21, 489, 208, 28);
		add(lblLatitude);
		
		tfLatitude = new JTextField();
		tfLatitude.setFont(new Font("Castellar", Font.BOLD, 12));
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(21, 521, 287, 34);
		add(tfLatitude);
		
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setFont(new Font("Castellar", Font.BOLD, 12));
		lblLongitude.setBounds(21, 566, 212, 28);
		add(lblLongitude);
		
		label_11 = new JLabel("1");
		label_11.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
			}
		});
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent arg0) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=1;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_11.setBounds(443, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_11);
		add(label_11);
		
		label_4 = new JLabel("New label");
		label_4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_4);
			}
		});
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=4;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_4.setBounds(656, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_4);
		add(label_4);
		
		label_3 = new JLabel("New label");
		label_3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				
			}
		});
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=3;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_3.setBounds(582, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_3);
		add(label_3);
		
		label_5 = new JLabel("New label");
		label_5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_4);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_5);
			}
		});
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=5;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_5.setBounds(733, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_5);
		add(label_5);
		
		JLabel lblNewLabel = new JLabel("RATING");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 23));
		lblNewLabel.setBounds(443, 582, 346, 44);
		add(lblNewLabel);
		
		label_2 = new JLabel("New label");
		label_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_11);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_FULL_STAR.getValue(), label_2);
				
			}
		});
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setAllToEmpty();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=2;
				JOptionPane.showMessageDialog(null,TransferClass_Message.RATING.getValue()+" "+rating);
			}
		});
		label_2.setBounds(509, 637, 56, 52);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_2);
		add(label_2);
		
		tfLongitude = new JTextField();
		tfLongitude.setFont(new Font("Castellar", Font.BOLD, 12));
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(21, 597, 287, 28);
		add(tfLongitude);
		
		JLabel label = new JLabel("20.4464");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Castellar", Font.BOLD, 14));
		label.setBounds(346, 527, 167, 28);
		add(label);
		
		JLabel label_1 = new JLabel("40.5567");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Castellar", Font.BOLD, 14));
		label_1.setBounds(346, 597, 167, 28);
		add(label_1);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setForeground(Color.BLACK);
		lblBackground.setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_BACKGROUND.getValue(), lblBackground);
		add(lblBackground);
		addItemAtPropertyComboBox();
		addItemToCountry();
		addItemToCity();
	}
	
	protected void setAllToEmpty() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_11);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_PROPERTY_EMPTY_STAR.getValue(), label_5);
		
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
