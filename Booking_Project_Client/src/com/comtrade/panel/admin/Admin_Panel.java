package com.comtrade.panel.admin;

import javax.swing.JPanel;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Discount_Contstants;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.PicturesURL;
import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.Threads_Constant;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.controlerKI.ControlerComboBox;
import com.comtrade.controlerKI.ControlerKI;
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
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.panel.common.ChatPanel;
import com.comtrade.panel.common.Login;
import com.comtrade.panel.common.Property_Created;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.threadsClient.TimeThread;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.frame.Application;
import com.comtrade.view.frame.RoomType;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

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
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

public class Admin_Panel extends JPanel {

	private User user;
	private int row;
	private static String url;
	private int idRooms,Number_of_bed;
	private String Type;
	private double Price_per_night;
	private User_Info user_info;
	private GeoLocation geoLocation;
	private Adress adress;
	private Property property;
	private GenericList<Property_Picutre_Album>listAlbum=new GenericList<Property_Picutre_Album>();
	private TransferClass transferClass=new TransferClass();
	private CyrclularList cyrcleList=new CyrclularList();
	private JLabel lblmainHotelPicture,lblAftermain,lblBeforeMain,lblPicture;
	private JComboBox cbTypeOfRoom;
	private JTextField tfPricePerNight;
	private JTextField tfNumberOfBed;
	private String WhichProperty;
	private GenericMap<String,GenericList<GeneralDomain>>map;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private JTextField tfMobileNumber;
	private JTextField tfCountry;
	private JTextField tfCity;
	private JTextField tfStreet;
	private JTextField tfNumber;
	private JTextField tfLongitude;
	private JTextField tfLatitude;
	private int counter=0;
	private JTextField tfAmount;
	private JTable tableRooms;
	private JTable tableDiscount;
	private DefaultTableModel dtmRoom=new DefaultTableModel();
	private DefaultTableModel dtmDiscount=new DefaultTableModel();
	private JDateChooser calendarFrom,CalendarTo;
	private GenericList<GeneralDomain>listRoom=new GenericList<GeneralDomain>();
	private GenericList<GeneralDomain>listDiscount=new GenericList<GeneralDomain>();
	private String idDiscount,date_from,date_to,amount;
	private DefaultListModel dm=new DefaultListModel();
	private JList<Object> listProperties;
	private ButtonGroup bg=new ButtonGroup();
	private JLayeredPane layeredPane;
	private JPanel PanelRoom,PanelDiscount,PanelReservation;
	private JRadioButton rbRoom,rbDiscount,rbReservation;
	
	
	
	
	public static String getUrl() {
		return url;
	}



	public static void setUrl(String url) {
		Admin_Panel.url = url;
	}



	public Admin_Panel(User user) throws ClassNotFoundException, IOException, URISyntaxException {
		
		this.user=user;
		transferClass=ControlerKI.getInstance().BackUserInfo_ForUser(user);
		this.user_info=(User_Info) transferClass.getServer_Object_Response();
		transferClass=ControlerKI.getInstance().AllAboutProperty(user);
		map=(GenericMap<String, GenericList<GeneralDomain>>) transferClass.getServer_Object_Response();
		
		
		setBounds(1200,720,1200,720);
		this.user=user;
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
				User_Panel.addNewPicture(user_info, user, lblPicture,btnNewButton);
				try {
					ControlerKI.getInstance().changePictureURLUser(user_info);
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_UPLOAD.getValue(), btnNewButton);
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
					ControlerKI.getInstance().changPictureURLHotel(cyrcleList.current());
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_UPLOAD.getValue(), btnNewButton_2);
		add(btnNewButton_2);
		
		
		
		
		JButton btnUpdateMyInfo = new JButton("UPDATE");
		btnUpdateMyInfo.setBackground(Color.WHITE);
		btnUpdateMyInfo.setForeground(Color.RED);
		btnUpdateMyInfo.setFont(new Font("Castellar", Font.BOLD, 10));
		btnUpdateMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
							user_info.setId_User(user.getId());
							user_info.setName(name);
							user_info.setSurname(surname);
							user_info.setGender(gender);
							user_info.setEmail(email);
							user_info.setPictureURL(pictureUrl);
							user_info.setMobileNumber(mobileNumber);
							TransferClass transferClass=ControlerKI.getInstance().updateUser(user_info);
							String message=transferClass.getMessage();
							JOptionPane.showMessageDialog(null,message);
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_UPDATE.getValue(), btnUpdateMyInfo);
		add(btnUpdateMyInfo);
		
		
		
		JButton btnNewButton_3 = new JButton("NEW PROPERTY");
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setFont(new Font("Castellar", Font.BOLD, 9));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel propertyJPanel=new Property_Created(user, user_info);
				Application.setPanelOnLayeredPane(propertyJPanel);
				backAllForThisProperty();
			}
		});
		btnNewButton_3.setBounds(168, 673, 186, 29);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnNewButton_3);
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
						adress1.setId_property(property.getId());
						adress1.setCountry(country);
						adress1.setCity(city);
						adress1.setStreet(street);
						adress1.setHouseNumber(Integer.parseInt(number));
						GeoLocation geoLocation=new GeoLocation();
						geoLocation.setId_adress(adress.getId_adress());
						geoLocation.setLatitude(Double.parseDouble(latitude));
						geoLocation.setLongitude(Double.parseDouble(longitude));
						list.add(adress1);
						list.add(geoLocation);
						try {
							TransferClass trnasfClass=ControlerKI.getInstance().updateProperty(list);
							String message=trnasfClass.getMessage();
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_UPDATE.getValue(), btnNewButton_6);
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
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!url.equals(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg")) {
					deletePictureForServer(AbsolutePath.absolutePath()+url);
					user_info.setPictureURL(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
					CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg",lblPicture);
					try {
						ControlerKI.getInstance().changePictureURLUser(user_info);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Admin_Panel.setUrl(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+user_info.getGender()+".jpg");
			}
		});
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Castellar", Font.BOLD, 10));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(168, 132, 186, 40);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_DELETE.getValue(), btnDelete);
		add(btnDelete);
		
		rbDiscount = new JRadioButton("Discount");
		rbDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(PanelDiscount);
				layeredPane.repaint();
				layeredPane.revalidate();
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
				layeredPane.removeAll();
				layeredPane.add(PanelRoom);
				layeredPane.repaint();
				layeredPane.revalidate();
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
				layeredPane.removeAll();
				layeredPane.add(PanelReservation);
				layeredPane.repaint();
				layeredPane.revalidate();
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
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_WATCH.getValue(), lblTime);
		add(lblTime);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(539, 38, 191, 215);
		add(scrollPane_2);
		
		listProperties = new JList<Object>();
		scrollPane_2.setViewportView(listProperties);
		listProperties.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				backAllForThisProperty();
			}
		});
		
		JButton btnDeletePicture = new JButton("DELETE ");
		btnDeletePicture.setBackground(Color.WHITE);
		btnDeletePicture.setForeground(Color.RED);
		btnDeletePicture.setFont(new Font("Castellar", Font.BOLD, 10));
		btnDeletePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!cyrcleList.current().getPicutre_URL().equals(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg")) {
						deletePictureForServer(AbsolutePath.absolutePath()+cyrcleList.current().getPicutre_URL());
						cyrcleList.current().setPicutre_URL(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg");
						String url=cyrcleList.current().getPicutre_URL();
						CommonMethod.setNewPicutreOnLabel(url, lblmainHotelPicture);
						cyrcleList.current().setPicutre_URL(PicturesURL.PROFILE_PICTURE_DEFAULT.getValue()+"/"+property.getClass().getSimpleName()+".jpg");
						try {
							ControlerKI.getInstance().changPictureURLHotel(cyrcleList.current());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
			}
		});
		btnDeletePicture.setBounds(890, 245, 161, 30);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_DELETE.getValue(), btnDeletePicture);
		add(btnDeletePicture);
		
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
				room.setId(idRooms);
				String type=cbTypeOfRoom.getSelectedItem().toString();
				int number_of_bed=Integer.parseInt(tfNumberOfBed.getText());
				double price_per_night=Double.parseDouble(tfPricePerNight.getText());
				room.setNumber_of_bed(number_of_bed);
				room.setPrice_per_night(price_per_night);
				room.setType(type);
				room.setId_property(property.getId());
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
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_UPDATE.getValue(), btnNewButton_5);
		
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
				double pricePerNight=Double.parseDouble(tfPricePerNight.getText());
				int numberOfBed=Integer.parseInt(tfNumberOfBed.getText());
				Room room=new Room();
				room.setId_property(property.getId());
				room.setNumber_of_bed(numberOfBed);
				room.setPrice_per_night(pricePerNight);
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
			}
		});
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnNewButton_4);
		
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
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_WORLD.getValue(), lblPanelRoomBackGround);
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
				idRooms=Integer.parseInt(tableRooms.getModel().getValueAt(row,0).toString());
				Type=tableRooms.getModel().getValueAt(row,1).toString();
				Price_per_night=Double.parseDouble(tableRooms.getModel().getValueAt(row,2).toString());
				Number_of_bed=Integer.parseInt(tableRooms.getModel().getValueAt(row,3).toString());
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
				Date from_date=Date.valueOf(sdf.format(calendarFrom.getDate()));
				Date to_date=Date.valueOf(sdf.format(CalendarTo.getDate()));
				Double amount=Double.parseDouble(tfAmount.getText());
				int idProperty=property.getId();
				if(to_date.compareTo(from_date) > 0) {
					Discount discount=new Discount();
					discount.setId_property(idProperty);
					discount.setFrom_Date(from_date);
					discount.setTo_Date(to_date);
					discount.setAmount_of_dosicount(amount);
					try {
						TransferClass transferClass=ControlerKI.getInstance().enterDiscount(discount);
						String message=transferClass.getMessage();
						JOptionPane.showMessageDialog(null,message);
						clearTFDiscount();
						Discount discount2=(Discount) transferClass.getServer_Object_Response();
						listDiscount.add(discount2);
						EnterDiscountOnTable(listDiscount);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,TransferClass_Message.INCORECT_DATE.getValue());
				}
				
			}
		});
		btnEnterDiscount.setFont(new Font("Castellar", Font.BOLD, 10));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_NEW_PROPERTY.getValue(),btnEnterDiscount);
		
		JButton btnDeleteDiscount = new JButton("Delete ");
		btnDeleteDiscount.setBounds(633, 27, 130, 40);
		PanelDiscount.add(btnDeleteDiscount);
		btnDeleteDiscount.setBackground(Color.WHITE);
		btnDeleteDiscount.setForeground(Color.RED);
		btnDeleteDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Discount discount=new Discount();
				discount.setId(Integer.parseInt(idDiscount));
				discount.setAmount_of_dosicount(Double.parseDouble(amount));
				discount.setFrom_Date(Date.valueOf(date_from));
				discount.setTo_Date(Date.valueOf(date_to));
				try {
					TransferClass transferClass=ControlerKI.getInstance().deleteDiscount(discount);
					String message=transferClass.getMessage();
					JOptionPane.showMessageDialog(null,message);
					for(int i=0;i<listDiscount.size();i++) {
						if(listDiscount.get(i) instanceof Discount) {
							Discount discount1=(Discount) listDiscount.get(i);
							if(discount1.getId()==discount.getId()) {
								listDiscount.delete(i);
							}
							EnterDiscountOnTable(listDiscount);
							clearTFDiscount();
						}
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
		});
		btnDeleteDiscount.setFont(new Font("Castellar", Font.BOLD, 10));
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_DELETE.getValue(), btnDeleteDiscount);
		
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
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_WORLD.getValue(), lblPanelBackGround);
		PanelDiscount.add(lblPanelBackGround);
		
		PanelReservation = new JPanel();
		PanelReservation.setBackground(new Color(0,0,0,0));
		layeredPane.add(PanelReservation, "name_423989168900109");
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1250, 720);
		add(lblBackground);
		lblBackground.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+PicturesURL.PICTURE_ADMIN_BACKGROUND.getValue(), lblBackground);
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
				idDiscount=tableDiscount.getModel().getValueAt(rowDiscount,0).toString();
				date_from=tableDiscount.getModel().getValueAt(rowDiscount, 1).toString();
				date_to=tableDiscount.getModel().getValueAt(rowDiscount, 2).toString();
				amount=tableDiscount.getModel().getValueAt(rowDiscount, 3).toString();
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
		room_Info.setId_room(room.getId());
		listRooms1.add(room);
		listRooms1.add(room_Info);
		try {
			TransferClass transferClass=ControlerKI.getInstance().updateRoom(listRooms1);
			String poruka=transferClass.getMessage();
			JOptionPane.showMessageDialog(null,poruka);
			clearTFRooms();
			for(int i=0;i<listRoom.size();i++) {
				if(listRoom.get(i) instanceof Room) {
					Room room1=(Room) listRoom.get(i);
					Room_Info room_info=(Room_Info) listRoom.get(i+1);
					if(room1.getId()==room.getId()) {
						listRoom.set(i,room);
						listRoom.set(i+1,room_Info);
					}
				}
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
			list1=entry.getValue();
			property=(Property) list1.get(0);
			adress1=(Adress) list1.get(1);
			String country=adress1.getCountry();
			name=property.getName().toString();
			String url=AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+country+".jpg";
					dm.addElement(new ComboBoxClass(property.getName()+" ("+property.getType_Of_Property()+")",new ImageIcon(url)));
			}
			dm.addElement(new ComboBoxClass(null,null));
			listProperties.setCellRenderer(new RenderCB());
			listProperties.setModel(dm);
			listProperties.setSelectedIndex(1);
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
		 EnterRoomInTable(listRoom);
		 EnterDiscountOnTable(listDiscount);
	}




	private void refreshRoomAndDiscount() {
		listRoom=null;
		listRoom=new GenericList<GeneralDomain>();
		listDiscount=null;
		listDiscount=new GenericList<GeneralDomain>();
		int number=map.get(WhichProperty).size();
		for(int i=8;i<number;i++) {
			if(map.get(WhichProperty).get(i) instanceof Room || map.get(WhichProperty).get(i) instanceof Room_Info) {
				listRoom.add(map.get(WhichProperty).get(i));
			}else {
				listDiscount.add(map.get(WhichProperty).get(i));
			}
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
		 setUrl(PicturesURL.PROFILE_PICTURE_USERS.getValue()+"/"+user.getUsername()+"/"+"ProfilePicture.jpg");
		
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
			Object[]row= {discount.getId(),discount.getFrom_Date(),discount.getTo_Date(),discount.getAmount_of_dosicount()};
			dtmDiscount.addRow(row);
		}
	}



	private void EnterRoomInTable(GenericList<GeneralDomain> listRoom2) {
		Room room=new Room();
		dtmRoom.setRowCount(0);
		for(int i=0;i<listRoom2.size();i=i+2) {
			room=(Room) listRoom2.get(i);
			if(room.getType().equals(cbTypeOfRoom.getSelectedItem().toString())) {
				Object[]row= {room.getId(),room.getType(),room.getPrice_per_night(),room.getNumber_of_bed()};
				dtmRoom.addRow(row);
			}
		}
		
	}



	protected void EnterRoomAndRoomInfo(GenericList<GeneralDomain> list, Room room, Room_Info room_Info) {
		list.add(room);
		list.add(room_Info);
		try {
			TransferClass transferClass=ControlerKI.getInstance().enterRoomAndRoomInfo(list);
			GenericList<GeneralDomain>listRooms=new GenericList<GeneralDomain>();
			listRooms=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
			String message=transferClass.getMessage();
			Room enteredRoom=new Room();
			enteredRoom=(Room) listRooms.get(2);
			JOptionPane.showMessageDialog(null,message);
			if(message.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
				map.get(WhichProperty).add(enteredRoom);
				map.get(WhichProperty).add(room_Info);
			}
			clearTFRooms();
			backAllForThisProperty();
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
				Room_Constants.ID_ROOM.getValue(),
				Room_Constants.ROOM_TYPE.getValue(),
				Room_Constants.PRICE_PER_NIGHT.getValue(),
				Room_Constants.NUMBER_OF_BEED.getValue()
		  };
		
		dtmRoom.addColumn(columnsRooms[0]);
		dtmRoom.addColumn(columnsRooms[1]);
		dtmRoom.addColumn(columnsRooms[2]);
		dtmRoom.addColumn(columnsRooms[3]);
		
		Object[]columnsDiscount= {
				Discount_Contstants.ID_DISCOUNT.getValue(),
				Discount_Contstants.FROM_DATE.getValue(),
				Discount_Contstants.TO_DATE.getValue(),
				Discount_Contstants.AMOUNT_OF_DISCOUNT.getValue()
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
		if(newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("jpg") ||
		 newPictureURL.substring(newPictureURL.length()-3,newPictureURL.length()).equals("png")	) {
			createPictureForServer(newPictureURL,property,property_picutre_album);
			CommonMethod.setNewPicutreOnLabel(newPictureURL, label);
			property_picutre_album.setPicutre_URL(PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getType_Of_Property()+"/"+property.getName()+"/"+property_picutre_album.getNumber()+".jpg");
		}
	}
	
	
	public static void createPictureForServer(String newPictureURL,Property property, Property_Picutre_Album property_picutre_album){
		try {
			FileInputStream in=new FileInputStream(newPictureURL);
			FileOutputStream out=new FileOutputStream(AbsolutePath.absolutePath()+PicturesURL.PROFILE_PICTURE_HOTELS.getValue()+"/"+property.getType_Of_Property()+"/"+property.getName()+"/"+property_picutre_album.getNumber()+".jpg");
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
}
