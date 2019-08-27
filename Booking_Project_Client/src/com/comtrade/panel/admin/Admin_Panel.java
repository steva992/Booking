package com.comtrade.panel.admin;

import javax.swing.JPanel;


import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.CountryesTxt;
import com.comtrade.constants.Discount_Contstants;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URL;
import com.comtrade.controlerClient.ControlerCode;
import com.comtrade.controlerClient.ControlerComboBox;
import com.comtrade.controlerClient.ControlerDiscount;
import com.comtrade.controlerClient.ControlerMessage;
import com.comtrade.controlerClient.ControlerProperty;
import com.comtrade.controlerClient.ControlerRating;
import com.comtrade.controlerClient.ControlerReservation;
import com.comtrade.controlerClient.ControlerRoom;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.Reservation_Constant;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_OF_Operation_TXT;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.cyrcleList.CyrclularList;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.property.Property_Picutre_Album;
import com.comtrade.domain.room.decorator.ApartmentDecorator;
import com.comtrade.domain.room.decorator.LuxuryRoomDecorator;
import com.comtrade.domain.room.decorator.OrdinaryRoomDecorator;
import com.comtrade.domain.room.decorator.Room_Info_Decorator;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.Generic;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.message.Message;
import com.comtrade.panel.common.Login;
import com.comtrade.panel.common.Property_Created;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.rating.Client_Rating;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.reservation.Reservation;
import com.comtrade.threads.TimeThread;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;
import com.comtrade.view.frame.RoomType;
import com.comtrade.view.frame.UserInfo;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle.Control;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Component;
import java.awt.Container;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import sun.audio.*;
import java.io.*;

public class Admin_Panel extends JPanel {

	private static User user;
	private User_Info user_info;
	private User userReservation;
	private User_Info userInfoReservation;
	private Date CurrentDate=Date.valueOf(LocalDate.now());
	private static String url;
	private int Number_of_bed;
	private int counter=0;
	private int row;
	private int discount_code;
	private int Code;
	private String Type;
	private double Price_per_night;
	private GeoLocation geoLocation;
	private Adress adress;
	private Property property;
	private JComboBox cbTypeOfRoom;
	private JLayeredPane layeredPane;
	private JTextArea textArea;
	private JButton btnRating;
	
	
	private JLabel lblmainHotelPicture,lblAftermain,lblBeforeMain,lblPicture;
	
	private String idDiscount,date_from,date_to,amount,WhichProperty;
	
	private JPanel PanelRoom,PanelDiscount,PanelReservation,PanelRating,ChatPanel;
	
	private JRadioButton rbRoom,rbDiscount,rbReservation,rdbtnRating,rdbtnChat;
	
	private JTextField tfName,tfSurname,tfEmail,tfMobileNumber,tfCountry,tfCity,tfStreet,tfNumber,tfLongitude,tfLatitude,tfNumberOfBed,tfPricePerNight,tfAmount,tfProfit,tfmessage;

	private JTable tableRooms,tableDiscount,tableReservation;
	
	private JDateChooser calendarFrom,CalendarTo;
	
	private JList<Object> listProperties,list,listChat; 
	
	private long startTime,endTime;
	
	
	private GenericMap<String,GenericList<GeneralDomain>>map;
	private Map<String,StringBuffer>chatMap=new HashMap<>();
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	private GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain>listReservation;
	private GenericList<GeneralDomain>listRating=new GenericList<GeneralDomain>();

	private DefaultListModel dm=new DefaultListModel();
	private DefaultListModel dm1=new DefaultListModel();
	private DefaultListModel dm2=new DefaultListModel();
	private DefaultComboBoxModel dmChat=new DefaultComboBoxModel<ComboBoxClass>();
	
	private DefaultTableModel dtmRoom=new DefaultTableModel();
	private DefaultTableModel dtmDiscount=new DefaultTableModel();
	private DefaultTableModel dtmReservation=new DefaultTableModel();
	
	private TransferClass transferClass=new TransferClass();
	private CyrclularList cyrcleList=new CyrclularList();
	
	private ButtonGroup bg=new ButtonGroup();
	
	
	


	public static String getUrl() {
		return url;
	}



	public static void setUrl(String url) {
		Admin_Panel.url = url;
	}



	public Admin_Panel(User user) throws ClassNotFoundException, IOException, URISyntaxException {
		this.user=user;
		startTime=System.currentTimeMillis();
		user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.LOGIN_USER.getValue(),user.getUsername());
		ControlerUI.getInstance().sendToServer(Type_Of_Operation.BACK_ALL_FOR_USER_PANEL,Type_Of_Data.PROPERTY, user);
		try {
			Thread.sleep(30);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		map=ControlerProperty.getInstance().getMap();
		ControlerProperty.getInstance().setNumber(0);
		this.user_info=(User_Info) map.get(user.getUsername()).get(1);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		setLayout(null);

		
		lblPicture = new JLabel("New label");
		lblPicture.setBounds(10, 11, 139, 110);
		add(lblPicture);
		url=AbsolutePath.absolutePath()+user_info.getPictureURL();
		CommonMethod.setNewPicutreOnLabel(url, lblPicture);
		

		lblBeforeMain = new JLabel("New label");
		lblBeforeMain.setFont(new Font("Castellar", Font.BOLD, 10));
		lblBeforeMain.setBounds(752, 38, 80, 76);
		add(lblBeforeMain);
		
		lblAftermain = new JLabel("New label");
		lblAftermain.setBounds(1110, 119, 80, 76);
		add(lblAftermain);
		
		lblmainHotelPicture = new JLabel("Main");
		lblmainHotelPicture.setBounds(842, 38, 258, 152);
		add(lblmainHotelPicture);
		
		
		JButton btnNewButton = new JButton("UPLOAD ");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User_Panel.addNewPicture(user_info,user, lblPicture,btnNewButton);
				try {
					GenericList<GeneralDomain>list=new  GenericList<GeneralDomain>();
					list.add(user);
					list.add(user_info);
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHANGE_PICTURE_URL_USER, Type_Of_Data.USER, list);
					String message=ControlerUser.getInstance().getMessage();
					ControlerUser.getInstance().setNumber(0);
					JOptionPane.showMessageDialog(null, message);
					user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.CHANGE_PICTURE_URL_USER.getValue(),user_info.getPictureURL());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 132, 139, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPLOAD.getValue(), btnNewButton);
		add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("<<<");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Castellar", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
				cyrcleList.next();
			}
		});
		btnNewButton_1.setBounds(800, 206, 80, 23);
		add(btnNewButton_1);
		
		JButton button = new JButton(">>>");
		button.setBackground(Color.WHITE);
		button.setForeground(Color.RED);
		button.setFont(new Font("Castellar", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblmainHotelPicture);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previous().getPicutre_URL(), lblBeforeMain);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.nextX2().getPicutre_URL(), lblAftermain);
				cyrcleList.previous();
			}
		});
		button.setBounds(1061, 206, 78, 23);
		add(button);
		JButton btnNewButton_2 = new JButton("UPLOAD ");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.RED);
		btnNewButton_2.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewPicture(cyrcleList.current(),property,lblmainHotelPicture,btnNewButton_2);
				try {
					GenericList< GeneralDomain>list=new GenericList<GeneralDomain>();
					list.add(property);
					list.add(cyrcleList.current());
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHANGE_PICTURE_URL_HOTEL, Type_Of_Data.PROPERTY, list);
					user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.CHANGE_PICTURE_URL_HOTEL.getValue(),cyrcleList.current().getPicutre_URL());
					ControlerProperty.getInstance().setNumber(0);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(890, 201, 161, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPLOAD.getValue(), btnNewButton_2);
		add(btnNewButton_2);
		
		
		
		
		JButton btnUpdateMyInfo = new JButton("UPDATE");
		btnUpdateMyInfo.setBackground(Color.WHITE);
		btnUpdateMyInfo.setForeground(Color.RED);
		btnUpdateMyInfo.setFont(new Font("Castellar", Font.BOLD, 10));
		btnUpdateMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
				String name=tfName.getText();
				String surname=tfSurname.getText();
				String email=tfEmail.getText();
				String gender=user_info.getGender();
				String pictureUrl=user_info.getPictureURL();
				String mobileNumber=tfMobileNumber.getText();
				boolean allField=allFieldComplete(name,surname,email,mobileNumber);
				boolean testField=testAllField(name,surname,email,mobileNumber);
				if(allField) {
					if(testField) {
						try {
							User_Info user_info=new User_Info();
							user_info.setUser_Username(user.getUsername());
							user_info.setName(name);
							user_info.setSurname(surname);
							user_info.setGender(gender);
							user_info.setEmail(email);
							user_info.setPictureURL(pictureUrl);
							user_info.setMobileNumber(mobileNumber);
							list.add(user);
							list.add(user_info);
							ControlerUI.getInstance().sendToServer(Type_Of_Operation.UPDATE_USER, Type_Of_Data.USER, list);
							String message=ControlerUser.getInstance().getMessage();
							ControlerUser.getInstance().setNumber(0);
							if(message != null) {
								JOptionPane.showMessageDialog(null,message);
							}
							user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.UPDATE_USER.getValue(),user.getUsername());
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
				}
				
				
			}
		});
		btnUpdateMyInfo.setBounds(10, 376, 139, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPDATE.getValue(), btnUpdateMyInfo);
		add(btnUpdateMyInfo);
		
		
		
		JButton btnNewButton_3 = new JButton("NEW PROPERTY");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setFont(new Font("Castellar", Font.BOLD, 9));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel propertyJPanel=new Property_Created(user, user_info,"admin");
				Application.setPanelOnLayeredPane(propertyJPanel);
				backAllForThisProperty();
			}
		});
		btnNewButton_3.setBounds(168, 673, 186, 29);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnNewButton_3);
		add(btnNewButton_3);
		
		JButton btnNewButton_6 = new JButton("UPDATE");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setBackground(Color.WHITE);
		btnNewButton_6.setForeground(Color.RED);
		btnNewButton_6.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String country=tfCountry.getText();
				String city=tfCity.getText();
				String street=tfStreet.getText();
				String number=tfNumber.getText();
				String latitude=tfLatitude.getText();
				String longitude=tfLongitude.getText();
				boolean allField=allFieldCompleteProperty(country,city,street,number,latitude,longitude);
				boolean testField=testAllFieldProperty(country,city,street,number,latitude,longitude);
				if(allField) {
					if(testField) {
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						Adress adress1=new Adress();
						adress1.setProperty_code(property.getProperty_code());
						adress1.setAdress_code(adress.getAdress_code());
						adress1.setCountry(country);
						adress1.setCity(city);
						adress1.setStreet(street);
						adress1.setHouseNumber(Integer.parseInt(number));
						GeoLocation geoLocation=new GeoLocation();
						geoLocation.setAdress_code(adress.getAdress_code());
						geoLocation.setLatitude(Double.parseDouble(latitude));
						geoLocation.setLongitude(Double.parseDouble(longitude));
						list.add(property);
						list.add(adress1);
						list.add(geoLocation);
						try {
							ControlerUI.getInstance().sendToServer(Type_Of_Operation.UPDATE_PROPERTY, Type_Of_Data.PROPERTY, list);
							 String message=ControlerProperty.getInstance().getMessage();
							 ControlerProperty.getInstance().setNumber(0);
							 if(message != null) {
								 JOptionPane.showMessageDialog(null,message);
									((Adress) map.get(WhichProperty).get(1)).setCity(city);
									((Adress) map.get(WhichProperty).get(1)).setStreet(street);
									((Adress) map.get(WhichProperty).get(1)).setCountry(country);
									((Adress) map.get(WhichProperty).get(1)).setHouseNumber(Integer.parseInt(number));
									((GeoLocation) map.get(WhichProperty).get(2)).setLatitude(Double.parseDouble(latitude));
									((GeoLocation) map.get(WhichProperty).get(2)).setLongitude(Double.parseDouble(longitude));
									fillCountryComboBox();
									refreshGlobalVariables();
									refreshPropertyCountryPicture();
									user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.UPDATE_PROPERTY.getValue(),property.getName());
							 }
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.ALL_FIELDS_FILL.getValue());
				}
			}
		});
		btnNewButton_6.setBounds(10, 672, 139, 30);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPDATE.getValue(), btnNewButton_6);
		add(btnNewButton_6);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Castellar", Font.BOLD, 14));
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 210, 161, 23);
		add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setEnabled(false);
		tfName.setBounds(168, 210, 186, 20);
		add(tfName);
		tfName.setColumns(10);
		addMouseListener(tfName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Castellar", Font.BOLD, 14));
		lblSurname.setForeground(Color.BLACK);
		lblSurname.setBounds(10, 251, 161, 23);
		add(lblSurname);
		
		tfSurname = new JTextField();
		tfSurname.setEnabled(false);
		tfSurname.setColumns(10);
		tfSurname.setBounds(168, 252, 186, 20);
		add(tfSurname);
		addMouseListener(tfSurname);
		
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Castellar", Font.BOLD, 14));
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setBounds(10, 285, 161, 23);
		add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(168, 285, 186, 20);
		add(tfEmail);
		addMouseListener(tfEmail);
		
		
		tfMobileNumber = new JTextField();
		tfMobileNumber.setEnabled(false);
		tfMobileNumber.setColumns(10);
		tfMobileNumber.setBounds(168, 329, 186, 20);
		add(tfMobileNumber);
		addMouseListener(tfMobileNumber);
		
		JLabel lblLogOut = new JLabel("");
		lblLogOut.setBackground(Color.RED);
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JPanel login=new Login();
				Application.setPanelOnLayeredPane(login);
				endTime=System.currentTimeMillis();
				long duration=endTime-startTime;
				try {
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.USER_LOGOUT.getValue(),String.valueOf(duration));
					try {
						ControlerUI.getInstance().sendToServer(Type_Of_Operation.REMOVE_ONLINE_USER,Type_Of_Data.USER,user);
						ControlerUser.getInstance().setNumber(0);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		lblLogOut.setBounds(292, 64, 186, 40);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_LOG_OUT.getValue(), lblLogOut);
		add(lblLogOut);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setFont(new Font("Castellar", Font.BOLD, 14));
		lblMobileNumber.setForeground(Color.BLACK);
		lblMobileNumber.setBounds(10, 328, 161, 23);
		add(lblMobileNumber);
		
		JLabel lblCountry_1 = new JLabel("Country");
		lblCountry_1.setFont(new Font("Castellar", Font.BOLD, 14));
		lblCountry_1.setForeground(Color.BLACK);
		lblCountry_1.setBounds(10, 454, 151, 23);
		add(lblCountry_1);
		
		tfCountry = new JTextField();
		tfCountry.setEnabled(false);
		tfCountry.setText((String) null);
		tfCountry.setColumns(10);
		tfCountry.setBounds(168, 454, 186, 20);
		add(tfCountry);
		addMouseListener(tfCountry);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Castellar", Font.BOLD, 14));
		lblCity.setForeground(Color.BLACK);
		lblCity.setBounds(10, 495, 151, 23);
		add(lblCity);
		
		tfCity = new JTextField();
		tfCity.setEnabled(false);
		tfCity.setText((String) null);
		tfCity.setColumns(10);
		tfCity.setBounds(168, 496, 186, 20);
		add(tfCity);
		addMouseListener(tfCity);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!url.equals(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg")) {
					deletePictureForServer(AbsolutePath.absolutePath()+url);
					user_info.setPictureURL(URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
					CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg",lblPicture);
					try {
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(user_info);
						ControlerUI.getInstance().sendToServer(Type_Of_Operation.CHANGE_PICTURE_URL_USER,Type_Of_Data.USER, list);
						ControlerUser.getInstance().setNumber(0);
						user.enterDataOnTXTFle(user,Type_OF_Operation_TXT.DELETE_PICTURE.getValue(),url);
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Admin_Panel.setUrl(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Castellar", Font.BOLD, 10));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(168, 132, 186, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_DELETE.getValue(), btnDelete);
		add(btnDelete);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setFont(new Font("Castellar", Font.BOLD, 14));
		lblStreet.setForeground(Color.BLACK);
		lblStreet.setBounds(10, 529, 151, 23);
		add(lblStreet);
		
		tfStreet = new JTextField();
		tfStreet.setEnabled(false);
		tfStreet.setText((String) null);
		tfStreet.setColumns(10);
		tfStreet.setBounds(168, 529, 186, 20);
		add(tfStreet);
		addMouseListener(tfStreet);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setFont(new Font("Castellar", Font.BOLD, 14));
		lblNumber.setForeground(Color.BLACK);
		lblNumber.setBounds(10, 572, 151, 23);
		add(lblNumber);
		
		rdbtnRating = new JRadioButton("RATING");
		rdbtnRating.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,PanelRating);
				
			}
		});
		
		rdbtnChat = new JRadioButton("");
		rdbtnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,ChatPanel);
				
			}
		});
		rdbtnChat.setForeground(Color.RED);
		rdbtnChat.setFont(new Font("Castellar", Font.BOLD, 12));
		rdbtnChat.setBackground(Color.YELLOW);
		rdbtnChat.setBounds(1129, 269, 61, 50);
		CommonMethod.setNewPicutreOnRadioButton(AbsolutePath.absolutePath()+URL.MESSAGE.getValue(),rdbtnChat);
		add(rdbtnChat);
		bg.add(rdbtnChat);
		rdbtnChat.setVisible(false);
		rdbtnRating.setForeground(Color.RED);
		rdbtnRating.setFont(new Font("Castellar", Font.BOLD, 12));
		rdbtnRating.setBackground(Color.WHITE);
		rdbtnRating.setBounds(776, 296, 139, 23);
		add(rdbtnRating);
		bg.add(rdbtnRating);
		
		btnRating = new JButton("New button");
		btnRating.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRating.setForeground(Color.WHITE);
		btnRating.setBounds(539, 225, 191, 50);
		add(btnRating);
		
		tfNumber = new JTextField();
		tfNumber.setEnabled(false);
		tfNumber.setText((String) null);
		tfNumber.setColumns(10);
		tfNumber.setBounds(168, 573, 186, 20);
		add(tfNumber);
		addMouseListener(tfNumber);
		
		JLabel Latitude = new JLabel("Latitude");
		Latitude.setFont(new Font("Castellar", Font.BOLD, 14));
		Latitude.setForeground(Color.BLACK);
		Latitude.setBounds(10, 605, 151, 23);
		add(Latitude);
		
		JButton btnDeactivatedAccount = new JButton("DEACTIVATE");
		btnDeactivatedAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					user.setStatus("deactivated");
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.DEACTIVATE_USER, Type_Of_Data.USER,user);
					String message=ControlerUser.getInstance().getMessage();
					ControlerUser.getInstance().setNumber(0);
					JPanel login=new Login();
					Application.setPanelOnLayeredPane(login);
					JOptionPane.showMessageDialog(null, message);
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.DEACTIVATE_USER.getValue(),user.getUsername());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeactivatedAccount.setForeground(Color.RED);
		btnDeactivatedAccount.setFont(new Font("Castellar", Font.BOLD, 9));
		btnDeactivatedAccount.setBackground(Color.WHITE);
		btnDeactivatedAccount.setBounds(168, 376, 186, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.DEACTIVATE_USER.getValue(), btnDeactivatedAccount);
		add(btnDeactivatedAccount);
		
		JLabel Longitude = new JLabel("Longitude");
		Longitude.setFont(new Font("Castellar", Font.BOLD, 14));
		Longitude.setForeground(Color.BLACK);
		Longitude.setBounds(10, 639, 151, 23);
		add(Longitude);
		
		tfLongitude = new JTextField();
		tfLongitude.setText((String) null);
		tfLongitude.setEnabled(false);
		tfLongitude.setColumns(10);
		tfLongitude.setBounds(168, 640, 186, 20);
		add(tfLongitude);
		addMouseListener(tfLongitude);
		
		tfLatitude = new JTextField();
		tfLatitude.setText((String) null);
		tfLatitude.setEnabled(false);
		tfLatitude.setColumns(10);
		tfLatitude.setBounds(168, 609, 186, 20);
		add(tfLatitude);
		addMouseListener(tfLatitude);
		
		rbDiscount = new JRadioButton("Discount");
		rbDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,PanelDiscount);
				
			}
		});
		rbDiscount.setBackground(Color.WHITE);
		rbDiscount.setFont(new Font("Castellar", Font.BOLD, 12));
		rbDiscount.setForeground(Color.RED);
		rbDiscount.setBounds(524, 296, 109, 23);
		add(rbDiscount);
		bg.add(rbDiscount);
		
		rbRoom = new JRadioButton("Room");
		rbRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				User_Panel.setPanelOnLayeredPane(layeredPane,PanelRoom);
				 
			}
		});
		rbRoom.setSelected(true);
		rbRoom.setBackground(Color.WHITE);
		
		rbRoom.setFont(new Font("Castellar", Font.BOLD, 12));
		rbRoom.setForeground(Color.RED);
		rbRoom.setBounds(413, 296, 109, 23);
		bg.add(rbRoom);
		add(rbRoom);
		
		rbReservation = new JRadioButton("Reservation");
		rbReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,PanelReservation);
				
			}
		});
		
		rbReservation.setForeground(Color.RED);
		rbReservation.setFont(new Font("Castellar", Font.BOLD, 12));
		rbReservation.setBackground(Color.WHITE);
		rbReservation.setBounds(635, 296, 139, 23);
		add(rbReservation);
		bg.add(rbReservation);
		
		JLabel timeLabel = new JLabel("New label");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(new Color(255, 255, 255));
		timeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		timeLabel.setBounds(292, 25, 186, 40);
		add(timeLabel);
		TimeThread time=new TimeThread(timeLabel);
		
		JLabel lblTime = new JLabel("New label");
		lblTime.setBounds(176, 18, 106, 59);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_WATCH.getValue(), lblTime);
		add(lblTime);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(539, 38, 191, 191);
		add(scrollPane_2);
		
		listProperties = new JList<Object>();
		scrollPane_2.setViewportView(listProperties);
		listProperties.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backAllForThisProperty();
			}
		});
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(UIManager.getColor("Button.highlight"));
		layeredPane.setBounds(413, 326, 777, 372);
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		PanelRoom = new JPanel();
		layeredPane.add(PanelRoom, "name_415392098794085");
		PanelRoom.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane(tableRooms);
		scrollPane.setBounds(290, 66, 473, 283);
		PanelRoom.add(scrollPane);
		
		tableRooms = new JTable(dtmRoom);
		tableRooms.setFont(new Font("Castellar", Font.BOLD, 11));
		tableRooms.setForeground(Color.WHITE);
		tableRooms.setBackground(Color.BLUE);
		tableRooms.setBounds(0, 0, 0, 0);
		scrollPane.setViewportView(tableRooms);
		
		JButton btnNewButton_8 = new JButton("DELETE");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Type=tableRooms.getModel().getValueAt(row,0).toString();
				Price_per_night=Double.parseDouble(tableRooms.getModel().getValueAt(row,1).toString());
				Number_of_bed=Integer.parseInt(tableRooms.getModel().getValueAt(row,2).toString());
				Code=Integer.parseInt(tableRooms.getModel().getValueAt(row,3).toString());
				tfPricePerNight.setText(String.valueOf(Price_per_night));
				tfNumberOfBed.setText(String.valueOf(Number_of_bed));
				boolean reservedRomm=reservedRoom(Code);
				Room_Info room_info=new Room_Info();
				if(reservedRomm) {
					for(int i=1;i<listRoom.size();i=i+2) {
						room_info=(Room_Info) listRoom.get(i);
						if(room_info.getRoom_code()==Code) {
							break;
						}
					}
					Room room=new Room();
					room.setRoom_code(Code);
					room.setProperty_code(property.getProperty_code());
					room.setNumber_of_bed(Number_of_bed);
					room.setPrice_per_night(Price_per_night);
					room.setType(Type);
					GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
					list.add(property);
					list.add(room);
					list.add(room_info);
					try {
						ControlerUI.getInstance().sendToServer(Type_Of_Operation.DELETE_ROOM, Type_Of_Data.ROOM, list);
						String message=ControlerRoom.getInstance().getMessage();
						ControlerRoom.getInstance().setNumber(0);
						if(message != null) {
							JOptionPane.showMessageDialog(null,message);
						}
						user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.DELETE_ROOM.getValue(),user.getUsername());
						for(int i=0;i<listRoom.size();i=i+2) {
							Room room1=(Room) listRoom.get(i);
							Room_Info room_Info1=(Room_Info) listRoom.get(i+1);
							if(room.getRoom_code()==room1.getRoom_code()) {
								listRoom.delete(room1);
								listRoom.delete(room_Info1);
								map.get(property.getName()).delete(room1);
								map.get(property.getName()).delete(room_Info1);
							}
						}
						EnterRoomInTable(listRoom);
						clearTFRooms();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.DONT_DELETE_ROOM.getValue());
				}
			}
		});
		btnNewButton_8.setBackground(Color.WHITE);
		btnNewButton_8.setForeground(Color.RED);
		btnNewButton_8.setFont(new Font("Castellar", Font.BOLD, 12));
		btnNewButton_8.setBounds(80, 309, 126, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_DELETE.getValue(), btnNewButton_8);
		PanelRoom.add(btnNewButton_8);
		
		cbTypeOfRoom = new JComboBox();
		cbTypeOfRoom.setBounds(414, 22, 172, 33);
		PanelRoom.add(cbTypeOfRoom);
		cbTypeOfRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(counter>0) {
					EnterRoomInTable(listRoom);
				}
				counter++;
			}
		});
		cbTypeOfRoom.setFont(new Font("Castellar", Font.BOLD, 10));
		
		JButton btnNewButton_5 = new JButton("Update");
		btnNewButton_5.setBounds(146, 252, 119, 46);
		PanelRoom.add(btnNewButton_5);
		btnNewButton_5.setBackground(Color.WHITE);
		btnNewButton_5.setForeground(Color.RED);
		btnNewButton_5.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton_5.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				Room room=new Room();
				room.setRoom_code(Code);
				String type=cbTypeOfRoom.getSelectedItem().toString();
				int number_of_bed=Integer.parseInt(tfNumberOfBed.getText());
				double price_per_night=Double.parseDouble(tfPricePerNight.getText());
				room.setNumber_of_bed(number_of_bed);
				room.setPrice_per_night(price_per_night);
				room.setType(type);
				room.setProperty_code(property.getProperty_code());
				GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
				Room_Info room_Info=new Room_Info();
				
				if(type.equals(Room_Constants.ORDINARY_ROOM.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new OrdinaryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.LUXURY_ROOM.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new LuxuryRoomDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}else if(type.equals(Room_Constants.APARTMENT.getValue())) {
					
					Room_Info_Decorator room_info_decorator=new ApartmentDecorator(room_Info);
					room_Info=room_info_decorator.addnewPropertiesForRoom();
					updateRooms(listRooms,room,room_Info);
					
				}
				
			}
		});
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_UPDATE.getValue(), btnNewButton_5);
		
		tfPricePerNight = new JTextField();
		tfPricePerNight.setBounds(80, 109, 126, 33);
		PanelRoom.add(tfPricePerNight);
		tfPricePerNight.setFont(new Font("Castellar", Font.BOLD, 10));
		tfPricePerNight.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Price Per Night");
		lblNewLabel.setBounds(80, 72, 126, 26);
		PanelRoom.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Castellar", Font.BOLD, 10));
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblNumberOfBeed = new JLabel("Number of Bed");
		lblNumberOfBeed.setBounds(80, 162, 126, 21);
		PanelRoom.add(lblNumberOfBeed);
		lblNumberOfBeed.setFont(new Font("Castellar", Font.BOLD, 10));
		lblNumberOfBeed.setForeground(Color.WHITE);
		
		tfNumberOfBed = new JTextField();
		tfNumberOfBed.setBounds(80, 186, 126, 33);
		PanelRoom.add(tfNumberOfBed);
		tfNumberOfBed.setFont(new Font("Castellar", Font.BOLD, 10));
		tfNumberOfBed.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Add");
		btnNewButton_4.setBounds(17, 252, 119, 46);
		PanelRoom.add(btnNewButton_4);
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setForeground(Color.RED);
		btnNewButton_4.setFont(new Font("Castellar", Font.BOLD, 10));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String type=cbTypeOfRoom.getSelectedItem().toString();
				if(tfPricePerNight.getText().length() > 0 && tfNumberOfBed.getText().length() > 0) {
					if(tfPricePerNight.getText().matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue()) && tfNumberOfBed.getText().matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue())) {
						double pricePerNight=Double.parseDouble(tfPricePerNight.getText());
						int numberOfBed=Integer.parseInt(tfNumberOfBed.getText());
								Room room=new Room();
								room.setProperty_code(property.getProperty_code());
								room.setNumber_of_bed(numberOfBed);
								room.setPrice_per_night(pricePerNight);
								try {
									room.setRoom_code(setCode());
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Room_Info room_Info=new Room_Info();
								GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
								if(type.equals(Room_Constants.ORDINARY_ROOM.getValue())) {
									
									room.setType(Room_Constants.ORDINARY_ROOM.getValue());
									Room_Info_Decorator room_info_decorator=new OrdinaryRoomDecorator(room_Info);
									room_Info=room_info_decorator.addnewPropertiesForRoom();
									EnterRoomAndRoomInfo(listRooms,room,room_Info);
									
								}else if(type.equals(Room_Constants.LUXURY_ROOM.getValue())) {
									
									room.setType(Room_Constants.LUXURY_ROOM.getValue());
									Room_Info_Decorator room_info_decorator=new LuxuryRoomDecorator(room_Info);
									room_Info=room_info_decorator.addnewPropertiesForRoom();
									EnterRoomAndRoomInfo(listRooms,room,room_Info);
									
								}else if(type.equals(Room_Constants.APARTMENT.getValue())) {
									
									room.setType(Room_Constants.APARTMENT.getValue());
									Room_Info_Decorator room_info_decorator=new ApartmentDecorator(room_Info);
									room_Info=room_info_decorator.addnewPropertiesForRoom();
									EnterRoomAndRoomInfo(listRooms,room,room_Info);
									
								}
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
						}
							
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
					
				
			}
		});
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnNewButton_4);
		
		JButton btnNewButton_9 = new JButton("??");
		btnNewButton_9.setBounds(608, 21, 60, 33);
		PanelRoom.add(btnNewButton_9);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room_Info room_info =new Room_Info();
				Room_Info_Decorator room = null;
				if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.ORDINARY_ROOM.getValue())) {
					
					room=new OrdinaryRoomDecorator(room_info);
					
				}else if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.APARTMENT.getValue())) {

					room=new ApartmentDecorator(room_info);
					
				}else if(cbTypeOfRoom.getSelectedItem().toString().equals(Room_Constants.LUXURY_ROOM.getValue())) {
					
					room=new LuxuryRoomDecorator(room_info);
					
				}
				RoomType room_typType=new RoomType(user,room);
				room_typType.setVisible(true);
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_9.setForeground(Color.WHITE);
		btnNewButton_9.setBackground(Color.RED);
		
		JLabel lblPanelRoomBackGround = new JLabel("");
		lblPanelRoomBackGround.setBounds(0, 0, 820, 372);
		//lblPanelRoomBackGround.setIcon(new ImageIcon((AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_BACKGROUND.getValue())));
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_WORLD.getValue(), lblPanelRoomBackGround);
		PanelRoom.add(lblPanelRoomBackGround);
		tableRooms.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				row=tableRooms.getSelectedRow();
				Type=tableRooms.getModel().getValueAt(row,0).toString();
				Price_per_night=Double.parseDouble(tableRooms.getModel().getValueAt(row,1).toString());
				Number_of_bed=Integer.parseInt(tableRooms.getModel().getValueAt(row,2).toString());
				Code=Integer.parseInt(tableRooms.getModel().getValueAt(row,3).toString());
				tfPricePerNight.setText(String.valueOf(Price_per_night));
				tfNumberOfBed.setText(String.valueOf(Number_of_bed));
			}
		});
		
		PanelDiscount = new JPanel();
		layeredPane.add(PanelDiscount, "name_415392116793595");
		layeredPane.setBorder(BorderFactory.createEtchedBorder());
		PanelDiscount.setBackground(new Color(0,0,0,0));
		PanelRoom.setBackground(new Color(0,0,0,0));
		PanelDiscount.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("% of Discount");
		lblNewLabel_2.setBounds(55, 89, 173, 19);
		PanelDiscount.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Castellar", Font.BOLD, 15));
		lblNewLabel_2.setForeground(Color.WHITE);
		
		
		tfAmount = new JTextField();
		tfAmount.setBounds(55, 119, 173, 30);
		PanelDiscount.add(tfAmount);
		tfAmount.setFont(new Font("Castellar", Font.BOLD, 10));
		tfAmount.setColumns(10);
		
		calendarFrom = new JDateChooser();
		calendarFrom.setBounds(55, 190, 173, 36);
		PanelDiscount.add(calendarFrom);
		
		calendarFrom.setDateFormatString("yyyy,MM dd");
		calendarFrom.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		
		CalendarTo = new JDateChooser();
		CalendarTo.setBounds(55, 268, 173, 36);
		PanelDiscount.add(CalendarTo);
		
		CalendarTo.setDateFormatString("yyyy,MM dd");
		CalendarTo.setMinSelectableDate(Date.valueOf(LocalDate.now()));
		
		JButton btnEnterDiscount = new JButton("Add");
		btnEnterDiscount.setBounds(311, 22, 130, 45);
		PanelDiscount.add(btnEnterDiscount);
		btnEnterDiscount.setBackground(Color.WHITE);
		btnEnterDiscount.setForeground(Color.RED);
		btnEnterDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
				if(calendarFrom.getDate() != null && CalendarTo.getDate() != null) {
					if(tfAmount.getText().matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue())) {
						Date from_date=Date.valueOf(sdf.format(calendarFrom.getDate()));
						Date to_date=Date.valueOf(sdf.format(CalendarTo.getDate()));
						Double amount=Double.parseDouble(tfAmount.getText());
						int code=property.getProperty_code();
						if(to_date.compareTo(from_date) > 0) {
							Discount discount=new Discount();
							try {
								discount.setDiscount_code(setCode());
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							discount.setProperty_code(property.getProperty_code());
							discount.setFrom_Date(from_date);
							discount.setTo_Date(to_date);
							discount.setAmount_of_dosicount(amount);
							try {
								GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
								list.add(property);
								list.add(discount);
								ControlerUI.getInstance().sendToServer(Type_Of_Operation.REGISTRATION_DISCOUNT, Type_Of_Data.DISCOUNT, list);
								Discount discount2=(Discount) ControlerDiscount.getInstance().getDiscount1();
								String message=ControlerDiscount.getInstance().getMessage();
								ControlerDiscount.getInstance().setNumber(0);
								if(message != null) {
									JOptionPane.showMessageDialog(null,message);
								}
								clearTFDiscount();
								map.get(property.getName()).add(discount2);
								listDiscount.add(discount2);
								EnterDiscountOnTable(listDiscount);
								user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_DISCOUNT.getValue(),String.valueOf(discount.getFrom_Date())+String.valueOf(discount.getTo_Date()));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_DATE.getValue());
						}
					}else {
						JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_ENTER_DATA.getValue());
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.SET_DATEFROM_DATETO.getValue());
				}
				
			}
		});
		btnEnterDiscount.setFont(new Font("Castellar", Font.BOLD, 10));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnEnterDiscount);
		
		JButton btnDeleteDiscount = new JButton("Delete ");
		btnDeleteDiscount.setBounds(633, 27, 130, 40);
		PanelDiscount.add(btnDeleteDiscount);
		btnDeleteDiscount.setBackground(Color.WHITE);
		btnDeleteDiscount.setForeground(Color.RED);
		btnDeleteDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Discount discount=new Discount();
				discount.setDiscount_code(discount_code);
				discount.setAmount_of_dosicount(Double.parseDouble(amount));
				discount.setFrom_Date(Date.valueOf(date_from));
				discount.setTo_Date(Date.valueOf(date_to));
				try {
					GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
					list.add(property);
					list.add(discount);
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.DELETE_DISCOUNT, Type_Of_Data.DISCOUNT,list);
					String message=ControlerDiscount.getInstance().getMessage();
					ControlerDiscount.getInstance().setNumber(0);
					if(message != null) {
						JOptionPane.showMessageDialog(null,message);
					}
					for(int i=0;i<listDiscount.size();i++) {
						if(listDiscount.get(i) instanceof Discount) {
							Discount discount1=(Discount) listDiscount.get(i);
							if(discount1.getDiscount_code()==discount_code) {
								listDiscount.delete(i);
								map.get(property.getName()).delete(discount1);
							}
							EnterDiscountOnTable(listDiscount);
							clearTFDiscount();
						}
					}
					EnterRoomInTable(listRoom);
					user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.DELETE_DISCOUNT.getValue(),String.valueOf(discount.getFrom_Date())+" -- "+String.valueOf(discount.getTo_Date()));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeleteDiscount.setFont(new Font("Castellar", Font.BOLD, 10));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_DELETE.getValue(), btnDeleteDiscount);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableDiscount);
		scrollPane_1.setBounds(311, 78, 452, 277);
		PanelDiscount.add(scrollPane_1);
		
		tableDiscount = new JTable(dtmDiscount);
		tableDiscount.setFont(new Font("Castellar", Font.BOLD, 11));
		tableDiscount.setBackground(Color.BLUE);
		tableDiscount.setForeground(Color.WHITE);
		scrollPane_1.setViewportView(tableDiscount);
		
		JLabel lblNewLabel_3 = new JLabel("DATE_FROM");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Castellar", Font.BOLD, 12));
		lblNewLabel_3.setBounds(55, 160, 173, 19);
		PanelDiscount.add(lblNewLabel_3);
		
		JLabel lblDateto = new JLabel("DATE_TO");
		lblDateto.setForeground(Color.WHITE);
		lblDateto.setFont(new Font("Castellar", Font.BOLD, 12));
		lblDateto.setBounds(55, 237, 173, 19);
		PanelDiscount.add(lblDateto);
		
		JLabel lblPanelBackGround = new JLabel("");
		lblPanelBackGround.setBounds(0, 0, 820, 375);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_WORLD.getValue(), lblPanelBackGround);
		PanelDiscount.add(lblPanelBackGround);
		
		PanelReservation = new JPanel();
		PanelReservation.setBackground(new Color(0,0,0,0));
		layeredPane.add(PanelReservation, "name_423989168900109");
		PanelReservation.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane(tableReservation);
		scrollPane_3.setBounds(10, 29, 753, 254);
		PanelReservation.add(scrollPane_3);
		
		tableReservation = new JTable(dtmReservation);
		tableReservation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableReservation.setBackground(Color.BLUE);
		tableReservation.setForeground(Color.WHITE);
		scrollPane_3.setViewportView(tableReservation);
		Object[] column= {
				Reservation_Constant.ACTIVE.getValue(),
				Reservation_Constant.USER.getValue(),	
				Reservation_Constant.CHECK_IN.getValue(),
				Reservation_Constant.CHECK_OUT.getValue(),
				Reservation_Constant.NUMBER_ADULTS.getValue(),
				Reservation_Constant.NUMBER_CHILDREN.getValue(),
				Reservation_Constant.NUMBER_NIGHTS.getValue(),
				Reservation_Constant.AMOUNT.getValue(),
				Reservation_Constant.ROOM_CODE.getValue()
			      		 };
        dtmReservation.addColumn(column[0]); 
        dtmReservation.addColumn(column[1]);  
        dtmReservation.addColumn(column[2]);  
        dtmReservation.addColumn(column[3]);  
        dtmReservation.addColumn(column[4]);
        dtmReservation.addColumn(column[5]);
        dtmReservation.addColumn(column[6]);
        dtmReservation.addColumn(column[7]);
        dtmReservation.addColumn(column[8]);
		tableReservation.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				boolean isThisID=false;
				int row=tableReservation.getSelectedRow();
				String username=tableReservation.getModel().getValueAt(row,1).toString();
				User_Info user_info=new User_Info();
				for(int i=1;i<listReservation.size();i=i+3) {
					User user=(User) listReservation.get(i);
					user_info=(User_Info) listReservation.get(i+1);
					if(username.equals(user.getUsername())) {
						isThisID=true;
						break;
					}
				}
				if(isThisID) {
					UserInfo frame=new UserInfo(user_info);
					frame.setVisible(true);
				}
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("INCOME OF THIS PROPERTY :");
		lblNewLabel_4.setFont(new Font("Sitka Text", Font.BOLD, 11));
		lblNewLabel_4.setBackground(Color.RED);
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(196, 312, 195, 45);
		PanelReservation.add(lblNewLabel_4);
		
		tfProfit = new JTextField();
		tfProfit.setForeground(Color.RED);
		tfProfit.setFont(new Font("Tahoma", Font.BOLD, 14));
		tfProfit.setHorizontalAlignment(SwingConstants.CENTER);
		tfProfit.setBounds(405, 316, 148, 32);
		PanelReservation.add(tfProfit);
		tfProfit.setColumns(10);
		
		PanelRating = new JPanel();
		layeredPane.add(PanelRating, "name_32596903632730");
		PanelRating.setBackground(new Color(0,0,0,0));
		PanelRating.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(117, 11, 479, 332);
		PanelRating.add(scrollPane_4);
		
		list = new JList();
		list.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane_4.setViewportView(list);
		
		ChatPanel = new JPanel();
		layeredPane.add(ChatPanel, "name_126561918059752");
		ChatPanel.setBackground(new Color(0,0,0,0));
		ChatPanel.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 31, 191, 310);
		ChatPanel.add(scrollPane_5);
		
		listChat = new JList();
		listChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText(null);
				String value=((ComboBoxClass)listChat.getSelectedValue()).getCall();
				textArea.append(chatMap.get(value).toString());
			}
		});
		scrollPane_5.setViewportView(listChat);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(246, 101, 517, 238);
		ChatPanel.add(scrollPane_6);
		
		textArea = new JTextArea();
		scrollPane_6.setViewportView(textArea);
		
		tfmessage = new JTextField();
		tfmessage.setBounds(246, 56, 257, 34);
		ChatPanel.add(tfmessage);
		tfmessage.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("SEND");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String message=tfmessage.getText();
				GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
				String value=((ComboBoxClass)listChat.getSelectedValue()).getCall();
				Message message1=new Message();
				User user1=(User) map.get(value).get(0);
				message1.setUser(user1);
				message1.setMessage(message);
				list.add(message1);
				list.add(user);
				try {
					ControlerUI.getInstance().sendToServer(Type_Of_Operation.SENDMESSAGE,Type_Of_Data.MESSAGE,list);
					ControlerMessage.getInstance().setNumber(0);
					chatMap.get(user1.getUsername()).append("\n");
					chatMap.get(user1.getUsername()).append(user.getUsername()+" : "+message+"\n");
					textArea.append("\n");
					textArea.append(user.getUsername()+" : "+message+"\n");
					tfmessage.setText("");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_7.setBounds(553, 45, 89, 45);
		ChatPanel.add(btnNewButton_7);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1250, 720);
		add(lblBackground);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_BACKGROUND.getValue(), lblBackground);
		tableDiscount.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowDiscount=tableDiscount.getSelectedRow();
				date_from=tableDiscount.getModel().getValueAt(rowDiscount, 0).toString();
				date_to=tableDiscount.getModel().getValueAt(rowDiscount, 1).toString();
				amount=tableDiscount.getModel().getValueAt(rowDiscount, 2).toString();
				discount_code=Integer.parseInt(tableDiscount.getModel().getValueAt(rowDiscount, 3).toString());
				tfAmount.setText(amount);
				calendarFrom.setDate(Date.valueOf(date_from));
				CalendarTo.setDate(Date.valueOf(date_to));
				
			}
		});
		
		createColumnForDTM(); 
		fillcombobox(); 
		fillCountryComboBox();
		backAllForThisProperty(); 
		time.start(); 
		sortAllTables(); 
		setAdminPanelForControlers();
		 
	}





	



	protected void appendTextOnArea(String value) {
		StringBuffer sb=new StringBuffer();
		sb=chatMap.get(value);
		textArea.append(sb.toString());
	}



	public static int setCode() throws ClassNotFoundException, IOException {
		int number=Integer.parseInt(String.valueOf(Math.round(Math.random()*100000)));
		ControlerUI.getInstance().sendToServer(Type_Of_Operation.ADD,Type_Of_Data.CODE,number);
		int objectCode=ControlerCode.getInstance().getCode();
		ControlerCode.getInstance().setNumber(0);
		while(objectCode == 1) {
			number=Integer.parseInt(String.valueOf(Math.round(Math.random()*100000)));
			ControlerUI.getInstance().sendToServer(Type_Of_Operation.ADD,Type_Of_Data.CODE,number);
			objectCode=ControlerCode.getInstance().getCode();
			ControlerCode.getInstance().setNumber(0);
		}
		return number;
	}



	private void setAdminPanelForControlers() {
		ControlerReservation.getInstance().setAdminPanel(this);
		ControlerRating.getInstance().setAdminPanel(this);
		ControlerMessage.getInstance().setAdminPanel(this);
		ControlerUser.getInstance().setAdminPanel(this);
		
	}



	private void fillRatingList() {
		dm1.clear();
		Client_Rating client=new Client_Rating();
		User user=new User();
		User_Info user_Info=new User_Info();
		Reservation reservation=new Reservation();
		DefaultComboBoxModel dm1=new DefaultComboBoxModel<ComboBoxClass>();
		for(int i=0;i<listRating.size();i++) {
			client=(Client_Rating) listRating.get(i);
			for(int j=1;j<listReservation.size();j=j+3) {
				user=(User) listReservation.get(j);
				if(client.getUser_Username().equals(user.getUsername())) {
					user_Info=(User_Info) listReservation.get(j+1);
				}
			}
				ImageIcon imageIcon=new ImageIcon(AbsolutePath.absolutePath()+user_Info.getPictureURL());
				Image image=imageIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
				dm1.addElement(new ComboBoxClass("       ( "+user_Info.getName()+" ) RATING OF THIS CLIENT IS : "+String.valueOf(client.getClient_rating()),new ImageIcon(image)));
			}
			dm1.addElement(new ComboBoxClass(null,null));
			list.setCellRenderer(new RenderCB());
			list.setModel(dm1);
			list.setSelectedIndex(0);
		}
		



	private void sortAllTables() {
		sort(dtmReservation,tableReservation);
		sort(dtmDiscount,tableDiscount);
		sort(dtmRoom,tableRooms);
		
	}



	public void sort(DefaultTableModel dtm,JTable jtable) {
		dtm=(DefaultTableModel) jtable.getModel();
		TableRowSorter<DefaultTableModel>sorter =new TableRowSorter<DefaultTableModel>(dtm);
		jtable.setRowSorter(sorter);
	}
	



	protected boolean reservedRoom(int roomCode) {
		for(int i=0;i<listReservation.size();i=i+3) {
			Reservation reservation =(Reservation) listReservation.get(i);
			if(reservation.getRoom_code()==roomCode) {
				return false;
			}
		}
		return true;
	}



	protected void deletePictureForServer(String picutre_URL) {
		File file=new File(picutre_URL);
		if(file.exists()) {
			file.delete();
			JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_DELETE.getValue());
		}
		
	}



	protected void clearTFDiscount() {
		tfAmount.setText("");
		calendarFrom.setDate(null);
		CalendarTo.setDate(null);
		
	}



	protected void updateRooms(GenericList<GeneralDomain> listRooms1, Room room, Room_Info room_Info) {
		room_Info.setRoom_code(room.getRoom_code());
		listRooms1.add(property);
		listRooms1.add(room);
		listRooms1.add(room_Info);
		try {
			ControlerUI.getInstance().sendToServer(Type_Of_Operation.UPDATE_ROOM, Type_Of_Data.ROOM, listRooms1);
			String message=ControlerRoom.getInstance().getMessage();
			ControlerRoom.getInstance().setNumber(0);
			if(message != null) {
				JOptionPane.showMessageDialog(null,message);
			}
			clearTFRooms();
			for(int i=0;i<listRoom.size();i++) {
				if(listRoom.get(i) instanceof Room) {
					Room room1=(Room) listRoom.get(i);
					Room_Info room_info=(Room_Info) listRoom.get(i+1);
					if(room1.getRoom_code()==room.getRoom_code()) {
						listRoom.set(i,room);
						listRoom.set(i+1,room_Info);
					}
				}
			}
			EnterRoomInTable(listRoom);
			user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.UPDATE_ROOM.getValue(),room.getType());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void clearTFRooms() {
		tfNumberOfBed.setText("");
		tfPricePerNight.setText("");
		
	}



	protected boolean testAllFieldProperty(String country, String city, String street, String number, String latitude,
			String longitude) {
		if( country.length()<1 || city.length()<1 || street.length()<1 || number.length()<1 || latitude.length()<1 || longitude.length()<1) {
			return false;
		}
		return true;
	}



	protected boolean allFieldCompleteProperty(String country, String city, String street, String number, String latitude, String longitude) {
		backBorderToGrayProperty();
		boolean country1=Pattern.matches(Regular_Expression.ADRESS.getValue(),country);
		boolean city1=Pattern.matches(Regular_Expression.ADRESS.getValue(),city);
		boolean street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),street);
		boolean number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),number);
		boolean latitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),latitude);
		boolean longitude1=Pattern.matches(Regular_Expression.LATITUDE_LONGITUDE.getValue(),longitude);
		if(country1 && city1 && street1 && number1 && latitude1 && longitude1) {
			return true;
		}
		
		if(country1==false) {
			tfCountry.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(city1==false) {
			tfCity.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(street1==false) {
			tfStreet.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(number1==false) {
			tfNumber.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(latitude1==false) {
			tfLatitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(longitude1==false) {
			tfLongitude.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		return false;
	}



	private void backBorderToGrayProperty() {
		tfCountry.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfCity.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfStreet.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLatitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfLongitude.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
	}



	protected boolean testAllField(String name, String surname, String email, String mobileNumber) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean surname1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),surname);
		boolean email1=Pattern.matches(Regular_Expression.EMAIL.getValue(),email);
		boolean mobileNumber1=Pattern.matches(Regular_Expression.PHONE_NUMBER.getValue(),mobileNumber);
		if(name1 && surname1 && email1 && mobileNumber1) {
			return true;
		}
		
		if(name1==false) {
			tfName.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(surname1==false) {
			tfSurname.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		
		if(email1==false) {
			tfEmail.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(mobileNumber1==false) {
			tfMobileNumber.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		return false;
	}



	private void backBorderToGray() {
		tfEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfName.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfSurname.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfMobileNumber.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}



	protected boolean allFieldComplete(String name, String surname, String email, String mobileNumber) {
		if( name.length()<1 || surname.length()<1 || email.length()<1 || mobileNumber.length()<1) {
			return false;
		}
		return true;
	}



	private void fillCountryComboBox() {		
		dm.clear();
		GenericList<GeneralDomain>list1=new GenericList<GeneralDomain>();
		Property property=new Property();
		Adress adress1=new Adress();
		String name;
		DefaultComboBoxModel dm=new DefaultComboBoxModel<ComboBoxClass>();
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if(entry.getValue().get(0) instanceof Property && ((Property) entry.getValue().get(0)).getUser_Username().equals(user.getUsername())) {
				list1=null;
				list1=new GenericList<GeneralDomain>();;
				list1=entry.getValue();
				property=(Property) list1.get(0);
				adress1=(Adress) list1.get(1);
				String country=adress1.getCountry();
				name=property.getName().toString();
				String url=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+country+".jpg";
						dm.addElement(new ComboBoxClass(property.getName()+" ("+property.getType_Of_Property()+")",new ImageIcon(url)));
			 	}
			}
			dm.addElement(new ComboBoxClass(null,null));
			listProperties.setCellRenderer(new RenderCB());
			listProperties.setModel(dm);
			listProperties.setSelectedIndex(0);
		}




	private void addMouseListener(JTextField tfName2) {
		tfName2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				tfName2.setEnabled(false);
			}
		});
		tfName2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				tfName2.setEnabled(true);
			}
			
		});
		
	}



	protected void backAllForThisProperty() {
		 refreshGlobalVariables();
		 refreshHotelPicture();
		 refreshPropertyCountryPicture();
		 refreshUserInfoField();
		 refreshHotelInfoField();
		 refreshRoomAndDiscount();
		 fillRatingList();
		 EnterRoomInTable(listRoom);
		 EnterDiscountOnTable(listDiscount);
		 EnterReservationOnTable(listReservation);
	}






	private void refreshRoomAndDiscount() {
		double sum=0;
		int counter = 0;
		int counterRating=0;
		listRoom=null;
		listRoom=new GenericList<GeneralDomain>();
		listDiscount=null;
		listDiscount=new GenericList<GeneralDomain>();
		listReservation=null;
		listReservation=new GenericList<GeneralDomain>();
		listRating=null;
		listRating=new GenericList<GeneralDomain>();
		int number=map.get(WhichProperty).size();
		for(int i=0;i<number;i++) {
			if(map.get(WhichProperty).get(i) instanceof Room || map.get(WhichProperty).get(i) instanceof Room_Info) {
				listRoom.add(map.get(WhichProperty).get(i));
			}else if(map.get(WhichProperty).get(i) instanceof Discount){
				listDiscount.add(map.get(WhichProperty).get(i));
			}else if(map.get(WhichProperty).get(i) instanceof Reservation ) {
				Reservation reservation=(Reservation) map.get(WhichProperty).get(i);
				listReservation.add(reservation);
				listReservation.add(map.get(reservation.getUser_Username()).get(0));
				listReservation.add(map.get(reservation.getUser_Username()).get(1));
			}else if(map.get(WhichProperty).get(i) instanceof Client_Rating && counterRating==0) {
				Client_Rating client=(Client_Rating) map.get(WhichProperty).get(i);
				listRating.add(client);
				counter++;
				sum+=client.getClient_rating();
				
			}
		}
		counterRating++;
		if(sum/counter > 0) {
			btnRating.setText("CLIENT RATING IS : "+String.format("%.2f",sum/counter));
		}else {
			btnRating.setText("RATING IS 0");
		}
		if(sum/counter < 3.50) {
			btnRating.setBackground(Color.RED);
			btnRating.setForeground(Color.WHITE);
		}else if(sum/counter >= 3.50 && sum/counter < 4.50) {
			btnRating.setBackground(Color.YELLOW);
			btnRating.setForeground(Color.BLACK);
		}else {
			btnRating.setBackground(Color.GREEN);
		}
		
	}



	private void refreshGlobalVariables() {
		 cyrcleList.remove();
		 String value=((ComboBoxClass)listProperties.getSelectedValue()).getCall();
		 WhichProperty=value.substring(0,value.indexOf(' '));
		 GenericList<GeneralDomain>list=map.get(WhichProperty);
		 
		 this.property=(Property) list.get(0);
		 this.adress=(Adress) list.get(1);
		 this.geoLocation=(GeoLocation) list.get(2);
		 
		 this.listAlbum.add((Property_Picutre_Album) list.get(3));
		 this.listAlbum.add((Property_Picutre_Album) list.get(4));
		 this.listAlbum.add((Property_Picutre_Album) list.get(5));
		 this.listAlbum.add((Property_Picutre_Album) list.get(6));
		 this.listAlbum.add((Property_Picutre_Album) list.get(7));
		 for(int i=0;i<5;i++) {
		 	cyrcleList.append(listAlbum.get(i));
		 }
		 listAlbum=null;
		 listAlbum=new GenericList<Property_Picutre_Album>();
		 setUrl(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
		
	}



	private void refreshHotelInfoField() {
		tfCountry.setText(adress.getCountry());
		tfCity.setText(adress.getCity());
		tfStreet.setText(adress.getStreet());
		tfNumber.setText(String.valueOf(adress.getHouseNumber()));
		tfLatitude.setText(String.valueOf(geoLocation.getLatitude()));
		tfLongitude.setText(String.valueOf(geoLocation.getLongitude()));
		
	}



	private void refreshUserInfoField() {
		tfName.setText(user_info.getName());
		tfSurname.setText(user_info.getSurname());
		tfEmail.setText(user_info.getEmail());
		tfMobileNumber.setText(user_info.getMobileNumber());
	}



	private void refreshPropertyCountryPicture() {
		
	}



	private void refreshHotelPicture() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.current().getPicutre_URL(),lblmainHotelPicture);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.next().getPicutre_URL(), lblAftermain);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+cyrcleList.previousX2().getPicutre_URL(), lblBeforeMain);
		cyrcleList.next();
	}



	private void EnterDiscountOnTable(GenericList<GeneralDomain> listDiscount2) {
		Discount discount=new Discount();
		dtmDiscount.setRowCount(0);
		for(int i=0;i<listDiscount2.size();i++) {
			discount=(Discount) listDiscount2.get(i);
			Object[]row= {discount.getFrom_Date(),discount.getTo_Date(),discount.getAmount_of_dosicount(),discount.getDiscount_code()};
			dtmDiscount.addRow(row);
		}
	}



	private void EnterRoomInTable(GenericList<GeneralDomain> listRoom2) {
		Room room=new Room();
		dtmRoom.setRowCount(0);
		for(int i=0;i<listRoom2.size();i=i+2) {
			room=(Room) listRoom2.get(i);
			if(room.getType().equals(cbTypeOfRoom.getSelectedItem().toString())) {
				Object[]row= {room.getType(),room.getPrice_per_night(),room.getNumber_of_bed(),room.getRoom_code()};
				dtmRoom.addRow(row);
			}
		}
		
	}
	
	private void EnterReservationOnTable(GenericList<GeneralDomain> listReservation2) {
		double amount=0;
		int number=1;
		Reservation reservation=new Reservation();
		User user=new User();
		dtmReservation.setRowCount(0);
		for(int i=0;i<listReservation2.size();i=i+3) {
					reservation=(Reservation) listReservation2.get(i);
					user=(User) listReservation2.get(i+1);
					Room room=new Room();
					for(int j=0;j<listRoom.size();j=j+2) {
						room=(Room) listRoom.get(j);
						if(reservation.getRoom_code()==room.getRoom_code()) {
							break;
						}
					}
					if(reservation.getCheckOut().compareTo(CurrentDate) > 0) {
						Object[]row= {"ACTIVE",user.getUsername(),reservation.getCheckIn(),reservation.getCheckOut(),reservation.getNumberAdults(),reservation.getNumberChildren(),reservation.getNumberNights(),reservation.getAmount(),room.getRoom_code()};
						dtmReservation.addRow(row);
					}else {
						Object[]row= {"INACTIVE",user.getUsername(),reservation.getCheckIn(),reservation.getCheckOut(),reservation.getNumberAdults(),reservation.getNumberChildren(),reservation.getNumberNights(),reservation.getAmount(),room.getRoom_code()};
						dtmReservation.addRow(row);
					}
					amount+=reservation.getAmount();
			}
		tfProfit.setText(String.format("%.3f",amount)+" $");
	}



	protected void EnterRoomAndRoomInfo(GenericList<GeneralDomain> list, Room room, Room_Info room_Info) {
		room_Info.setRoom_code(room.getRoom_code());
		list.add(property);
		list.add(room);
		list.add(room_Info);
		try {
			ControlerUI.getInstance().sendToServer(Type_Of_Operation.REGISTRATION_ROOM,Type_Of_Data.ROOM, list);
			String message=ControlerRoom.getInstance().getMessage();
			
			ControlerRoom.getInstance().setNumber(0);
			Room enteredRoom=new Room();
			clearTFRooms();
			user.enterDataOnTXTFle(user, Type_OF_Operation_TXT.REGISTRATION_ROOM.getValue(),room.getType());
			listRoom.add(room);
			listRoom.add(room_Info);
			map.get(property.getName()).add(room);
			map.get(property.getName()).add(room_Info);
			if(message != null) {
				JOptionPane.showMessageDialog(null,message);
			}
			EnterRoomInTable(listRoom);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private void fillcombobox() {
		cbTypeOfRoom.addItem(Room_Constants.ORDINARY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.LUXURY_ROOM.getValue());
		cbTypeOfRoom.addItem(Room_Constants.APARTMENT.getValue());
	}



	private void createColumnForDTM() {
		Object[]columnsRooms= {
				Room_Constants.ROOM_TYPE.getValue(),
				Room_Constants.PRICE_PER_NIGHT.getValue(),
				Room_Constants.NUMBER_OF_BEED.getValue(),
				Room_Constants.ROOM_CODE.getValue()
		  };
		
		dtmRoom.addColumn(columnsRooms[0]);
		dtmRoom.addColumn(columnsRooms[1]);
		dtmRoom.addColumn(columnsRooms[2]);
		dtmRoom.addColumn(columnsRooms[3]);
		
		Object[]columnsDiscount= {
				Discount_Contstants.FROM_DATE.getValue(),
				Discount_Contstants.TO_DATE.getValue(),
				Discount_Contstants.AMOUNT_OF_DISCOUNT.getValue(),
				Discount_Contstants.DISCOUNT_CODE.getValue()
		};
		
		dtmDiscount.addColumn(columnsDiscount[0]);
		dtmDiscount.addColumn(columnsDiscount[1]);
		dtmDiscount.addColumn(columnsDiscount[2]);
		dtmDiscount.addColumn(columnsDiscount[3]);
		
	}



	public static void addNewPicture(Property_Picutre_Album property_picutre_album,Property property,JLabel label, JButton btnNewButton_2) {
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("png","jpg"));
		fileChooser.showOpenDialog(btnNewButton_2);
		String newPictureURL=fileChooser.getSelectedFile().getAbsolutePath();
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equalsIgnoreCase("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,property,property_picutre_album,user);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			property_picutre_album.setPicutre_URL(URL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+URL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getName()+"("+property.getType_Of_Property()+")/"+property_picutre_album.getNumber()+".jpg");
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,Property property, Property_Picutre_Album property_picutre_album, User user2){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			String out1=AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USERS.getValue()+"/"+user2.getUsername()+URL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getName()+"("+property.getType_Of_Property()+")/"+property_picutre_album.getNumber()+".jpg";
			FileOutputStream out=new FileOutputStream(out1);
			BufferedInputStream bin=new BufferedInputStream(in);
			BufferedOutputStream bou=new BufferedOutputStream(out);
			int b=0;
			while(b !=-1) {
				b=bin.read();
				bou.write(b);
			}
			bin.close();
			bou.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



	public void addRating(String name, Client_Rating rating) {
		map.get(name).add(rating);
		fillRatingList();
		backAllForThisProperty();

	}



	public void addReservation(String name, Reservation reservation, User user2, User_Info user_info2) {
		map.get(name).add(reservation);
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		list.add(user2);
		list.add(user_info2);
		map.put(user2.getUsername(),list);
		listReservation.add(reservation);
		listReservation.add(user2);
		listReservation.add(user_info2);
		EnterReservationOnTable(listReservation);
	}



	public void deleteReservation(String name, Reservation reservation, User user2, User_Info user_info2) {
		map.get(name).delete(reservation);
		backAllForThisProperty();
	}



	public void appendMessageOnTextArea(String message, User user2) {
		if(!chatMap.containsKey(user2.getUsername())) {
			chatMap.put(user2.getUsername(),new StringBuffer());
			chatMap.get(user2.getUsername()).append("\n");
			chatMap.get(user2.getUsername()).append(user2.getUsername()+" : "+message+"\n");
			fillJList(user2);
		}else {
			chatMap.get(user2.getUsername()).append("\n");
			chatMap.get(user2.getUsername()).append(user2.getUsername()+" : "+message+"\n");
		}
		
		String value=((ComboBoxClass)listChat.getSelectedValue()).getCall();
		if(value.equals(user2.getUsername())) {
			textArea.append("\n");
			textArea.append(user2.getUsername()+" : "+message+"\n");
		}
	}



	private void fillJList(User user2) {
			rdbtnChat.setVisible(true);
			String user;
			dmChat.addElement(new ComboBoxClass(user2.getUsername(),new ImageIcon(AbsolutePath.absolutePath()+URL.ONLINE_USER.getValue())));
			listChat.setCellRenderer(new RenderCB());
			listChat.setModel(dmChat);
			if(dmChat.getSize() != 0) {
				listChat.setSelectedIndex(0);
			}
			
		
	}





	public void removeFromList(User user2) {
			dm2.clear();
			dmChat.removeAllElements();
			chatMap.remove(user2.getUsername());
			for(Map.Entry<String,StringBuffer> entry : chatMap.entrySet() ) {
				dmChat.addElement(new ComboBoxClass(entry.getKey(),new ImageIcon(AbsolutePath.absolutePath()+URL.ONLINE_USER.getValue())));
			}
			
			listChat.setCellRenderer(new RenderCB());
			if(dmChat.getSize() != 0) {
				listChat.setSelectedIndex(0);
			}else {
				rdbtnChat.setVisible(false);
				rbRoom.setSelected(true);
				layeredPane.removeAll();
				layeredPane.add(PanelRoom);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
			textArea.setText("");
	}



	public void addUser(GenericList<GeneralDomain> list2) {
		User user=(User) list2.get(0);
		map.put(user.getUsername(),list2);
		
	}
}
