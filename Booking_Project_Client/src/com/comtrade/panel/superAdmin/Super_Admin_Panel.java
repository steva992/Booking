package com.comtrade.panel.superAdmin;

import javax.swing.JPanel;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.Type_Of_Data;
import com.comtrade.constants.Type_Of_Operation;
import com.comtrade.constants.URL;
import com.comtrade.controlerClient.ControlerProperty;
import com.comtrade.controlerClient.ControlerReservation;
import com.comtrade.controlerClient.ControlerUI;
import com.comtrade.controlerClient.ControlerUser;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.Property;
import com.comtrade.domain.user.User;
import com.comtrade.domain.user.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.panel.common.Login;
import com.comtrade.panel.user.User_Panel;
import com.comtrade.render.ComboBoxClass;
import com.comtrade.render.RenderCB;
import com.comtrade.reservation.Reservation;
import com.comtrade.view.frame.Application;
import com.comtrade.view.frame.UserInfo;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.Rotation;

import javax.swing.JTextField;
import javax.swing.JLayeredPane;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Super_Admin_Panel extends JPanel {

	private User user;
	private User_Info user_info;
	private Adress adress;
	private JLayeredPane layeredPane;
	private JRadioButton rdbtnRatioOfAmount;
	
	private ButtonGroup bg=new ButtonGroup();
	
	private JTextField tfAll,tfThisClient,tfNumber,tfStreet,tfCity,tfName,tfSurname,tfMobileNumber,tfEmail;
	
	private JPanel panelPie,panel_1,panelBar2,panelBar3,panelBubble,panelCountries;
	
	private ChartPanel chartPanel,charPanel,chartPannelBar2,chartPanelBar3,chartPanelBubble,chartCountries;
	
	private JFreeChart jchar,jchar1,jchart2,jchart3,jchar4,jchar5;
	
	private JList<Object> listAdmins,listProperties; 
	
	private DefaultListModel dmUsers=new DefaultListModel();
	
	private DefaultListModel dmProperties=new DefaultListModel();
	
	private GenericList<GeneralDomain>listPropery=new GenericList<GeneralDomain>();
	
	private Map<String,Double>mapCountries=new HashMap<>();
	
	private GenericMap<String, GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
	
	
	public Super_Admin_Panel(User user) throws ClassNotFoundException, IOException {
		setForeground(Color.LIGHT_GRAY);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),Panel_Dimension.WIDTH.getValue(),Panel_Dimension.HEIGHT.getValue());
		this.user=user;
		ControlerUI.getInstance().sendToServer(Type_Of_Operation.BACK_ALL_FOR_USER_PANEL,Type_Of_Data.PROPERTY,user);
		map=ControlerProperty.getInstance().getMap();
		ControlerProperty.getInstance().setNumber(0);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(284, 11, 101, 25);
		add(lblNewLabel);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(284, 95, 101, 25);
		add(lblSurname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(284, 174, 101, 25);
		add(lblEmail);
		
		JRadioButton rbBar = new JRadioButton("Property Earning Ratio");
		rbBar.setBackground(new Color(255, 255, 255));
		rbBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panelPie);
				
			}
		});
		rbBar.setBounds(500, 11, 220, 23);
		add(rbBar);
		bg.add(rbBar);
		
		JRadioButton rbPie = new JRadioButton("Property Earning Ratio (IN % )");
		rbPie.setBackground(new Color(255, 255, 255));
		rbPie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panel_1);
				
			}
		});
		rbPie.setBounds(499, 35, 220, 23);
		add(rbPie);
		bg.add(rbPie);
		
		JRadioButton rbBar2 = new JRadioButton("Average Reservation");
		rbBar2.setBackground(new Color(255, 255, 255));
		rbBar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panelBar2);
				
				
			}
		});
		rbBar2.setBounds(722, 12, 214, 23);
		add(rbBar2);
		bg.add(rbBar2);
		
		JRadioButton rdbtnRationOfNumber = new JRadioButton("Ratio Adults and Young ");
		rdbtnRationOfNumber.setBackground(new Color(255, 255, 255));
		rdbtnRationOfNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panelBar3);
				
			}
		});
		rdbtnRationOfNumber.setBounds(721, 35, 220, 23);
		add(rdbtnRationOfNumber);
		bg.add(rdbtnRationOfNumber);
		
		rdbtnRatioOfAmount = new JRadioButton("Ratio Amount and  Reservation");
		rdbtnRatioOfAmount.setBackground(new Color(255, 255, 255));
		rdbtnRatioOfAmount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panelBubble);
				
			}
		});
		rdbtnRatioOfAmount.setBounds(938, 12, 257, 23);
		add(rdbtnRatioOfAmount);
		bg.add(rdbtnRatioOfAmount);
		
		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setBounds(284, 263, 101, 25);
		add(lblMobileNumber);
		
		JRadioButton rdbtnRatioAmountPer = new JRadioButton("Ratio Amount Per Country (ALL PROP.)");
		rdbtnRatioAmountPer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				User_Panel.setPanelOnLayeredPane(layeredPane,panelCountries);
				
			}
		});
		rdbtnRatioAmountPer.setBackground(Color.WHITE);
		rdbtnRatioAmountPer.setBounds(938, 35, 257, 23);
		add(rdbtnRatioAmountPer);
		bg.add(rdbtnRatioAmountPer);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(500, 76, 700, 400);
		add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.setBackground(new Color(0,0,0,0));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,0,0,0));
		layeredPane.add(panel_1, "name_341674515347536");
		
		panelPie = new JPanel();
		panelPie.setBackground(new Color(0,0,0,0));
		layeredPane.add(panelPie, "name_341688127517403");
		
		panelBar2 = new JPanel();
		panelBar2.setBackground(new Color(0,0,0,0));
		layeredPane.add(panelBar2, "name_27360762346995");
		
		panelBar3 = new JPanel();
		panelBar3.setBackground(new Color(0,0,0,0));
		layeredPane.add(panelBar3, "name_108989811268971");
		
		panelBubble = new JPanel();
		panelBubble.setBackground(new Color(0,0,0,0));
		layeredPane.add(panelBubble, "name_111431472200765");
		
		panelCountries = new JPanel();
		layeredPane.add(panelCountries, "name_118835939041885");
		
		
		JLabel lblStreet = new JLabel("City");
		lblStreet.setBounds(270, 391, 101, 25);
		add(lblStreet);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(269, 427, 156, 42);
		add(tfCity);
		
		JLabel lblSalaryOfSelected = new JLabel("SALARY OF SELECTED CLIENT");
		lblSalaryOfSelected.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSalaryOfSelected.setBounds(1007, 492, 188, 42);
		add(lblSalaryOfSelected);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(270, 583, 101, 25);
		add(lblNumber);
		
		tfNumber = new JTextField();
		tfNumber.setColumns(10);
		tfNumber.setBounds(270, 619, 156, 42);
		add(tfNumber);
		
		tfAll = new JTextField();
		tfAll.setForeground(new Color(255, 0, 0));
		tfAll.setFont(new Font("Tahoma", Font.BOLD, 15));
		tfAll.setHorizontalAlignment(SwingConstants.CENTER);
		tfAll.setBounds(865, 650, 330, 41);
		add(tfAll);
		tfAll.setColumns(10);
		
		tfThisClient = new JTextField();
		tfThisClient.setForeground(Color.RED);
		tfThisClient.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfThisClient.setHorizontalAlignment(SwingConstants.CENTER);
		tfThisClient.setColumns(10);
		tfThisClient.setBounds(1002, 549, 188, 39);
		add(tfThisClient);
		
		JLabel lblNewLabel_1 = new JLabel("SALARY OF ALL CLIENTS");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(865, 595, 330, 42);
		add(lblNewLabel_1);
		
		
		JLabel lblStreet_1 = new JLabel("Street");
		lblStreet_1.setBounds(270, 480, 101, 25);
		add(lblStreet_1);
		
		JLabel lblLogOut = new JLabel("New label");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JPanel login=new Login();
				Application.setPanelOnLayeredPane(login);
			}
		});
		lblLogOut.setBounds(577, 651, 233, 42);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_ADMIN_LOG_OUT.getValue(), lblLogOut);
		add(lblLogOut);
		
		JLabel lblAdminsProperties = new JLabel("ADMINS PROPERTIES");
		lblAdminsProperties.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdminsProperties.setFont(new Font("Castellar", Font.BOLD, 13));
		lblAdminsProperties.setBounds(10, 342, 188, 25);
		add(lblAdminsProperties);
		
		JLabel lblAdmins = new JLabel("ADMINS");
		lblAdmins.setFont(new Font("Castellar", Font.BOLD, 13));
		lblAdmins.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdmins.setBounds(10, 11, 101, 25);
		add(lblAdmins);
		
		tfStreet = new JTextField();
		tfStreet.setColumns(10);
		tfStreet.setBounds(270, 516, 156, 42);
		add(tfStreet);
		
		tfMobileNumber = new JTextField();
		tfMobileNumber.setColumns(10);
		tfMobileNumber.setBounds(284, 299, 156, 33);
		add(tfMobileNumber);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(284, 131, 156, 33);
		add(tfSurname);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(283, 47, 156, 33);
		add(tfName);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(283, 210, 156, 33);
		add(tfEmail);
		tfEmail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 226, 289);
		add(scrollPane);
		
		listAdmins = new JList();
		listAdmins.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String user=((ComboBoxClass)listAdmins.getSelectedValue()).getCall();
				user_info=(User_Info) map.get(user).get(1);
				fillTextField(user_info);
				fillListProperties(user);
			}
		});
		scrollPane.setViewportView(listAdmins);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 378, 226, 316);
		add(scrollPane_1);
		
		listProperties = new JList();
		listProperties.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String property=((ComboBoxClass)listProperties.getSelectedValue()).getCall();
				property=property.substring(0,property.indexOf('(')-1);
				adress=(Adress) map.get(property).get(1);
				fillTFProp(adress);
			}
		});
		scrollPane_1.setViewportView(listProperties);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(0, 0, 1250, 750);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.SUPER_ADMIN_WALLPAPER.getValue(), lblNewLabel_2);
		add(lblNewLabel_2);
		
		enterProfitOfAllProperties();
		fillJListAdmins();
		setAllControlers();
		
		
	}

	protected void fillTFProp(Adress adress2) {
		tfCity.setText(adress2.getCity());
		tfStreet.setText(adress2.getStreet());
		tfNumber.setText(String.valueOf(adress2.getHouseNumber()));
		
	}

	protected void fillListProperties(String username) {
		dmProperties.clear();
		mapCountries=null;
		mapCountries=new HashMap<String, Double>();
		listPropery=null;
		listPropery=new GenericList<GeneralDomain>();
		User_Info user_Info=(User_Info) map.get(username).get(1);
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if((entry.getValue().get(0) instanceof Property)){
				Property property=(Property) entry.getValue().get(0);
				Adress adress=(Adress) entry.getValue().get(1);
				if(!mapCountries.containsKey(adress.getCountry())) {
					mapCountries.put(adress.getCountry(),0.0);
				}
				if(property.getUser_Username().equals(username)) {
					listPropery.add(property);
					ImageIcon image=resizeImage(adress);
					dmProperties.addElement(new ComboBoxClass(property.getName()+" ("+property.getType_Of_Property()+") "+property.getRating()+" "+"\u2B50",image));
				}
			}
		}
		
		listProperties.setCellRenderer(new RenderCB());
		listProperties.setModel(dmProperties);
		listProperties.setSelectedIndex(0);
		
		createBarChart(listPropery);
		createPieChart(listPropery);
		createBarChartAverage(listPropery);
		createBarChartPeopleRatio(listPropery);
		createBarChartBubble(listPropery);
		createBarChartCountries(mapCountries);
		
		setChartOnPanel();
	}


	private void setAllControlers() {
		ControlerProperty.getInstance().setSuperAdmin(this);
		ControlerReservation.getInstance().setSuperAdmin(this);
		
	}

	private void enterProfitOfAllProperties() {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		double sum=0;
		Reservation reservation=new Reservation();
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if(entry.getValue().get(0) instanceof Property) {
				list=entry.getValue();
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						sum+=reservation.getAmount();
					}
				}
			}
		}
		tfAll.setText(String.valueOf(sum)+" $");
		
	}

	private void fillJListAdmins() {
		dmUsers.clear();
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if(entry.getValue().get(0) instanceof User) {
				user=(User) entry.getValue().get(0);
				user_info=(User_Info) entry.getValue().get(1);
				ImageIcon image=resizeImage(user_info);
				if(user.getStatus().equals("admin")) {
					dmUsers.addElement(new ComboBoxClass(user.getUsername(),image));
				}
			}
		}
		
		listAdmins.setCellRenderer(new RenderCB());
		listAdmins.setModel(dmUsers);
		listAdmins.setSelectedIndex(0);
	}

	private void fillTextField(User_Info user_info) {
		tfName.setText(user_info.getName());
		tfSurname.setText(user_info.getSurname());
		tfEmail.setText(user_info.getEmail());
		tfMobileNumber.setText(String.valueOf(user_info.getMobileNumber()));
		
	}

	private ImageIcon resizeImage(GeneralDomain generalDomain) {
		ImageIcon image=null;
		Image image1=null;
		Image newImg=null;
		if(generalDomain instanceof User_Info) {
			image= new ImageIcon(AbsolutePath.absolutePath()+((User_Info) generalDomain).getPictureURL());
			image.getImage().flush();
			image1=image.getImage();
			newImg=image1.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		}else if(generalDomain instanceof Adress){
			image= new ImageIcon(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_USER_COUNTRYES.getValue()+"/"+((Adress) generalDomain).getCountry()+".jpg");
			image.getImage().flush();
			image1=image.getImage();
			newImg=image1.getScaledInstance(50,50,Image.SCALE_SMOOTH);
		}
		return new ImageIcon(newImg);
	}


	private void setChartOnPanel() {  
		
		setChartOnCertainPanel(panel_1,chartPanel);
		setChartOnCertainPanel(panelPie,charPanel);
		setChartOnCertainPanel(panelBar2,chartPannelBar2);
		setChartOnCertainPanel(panelBar3,chartPanelBar3);
		setChartOnCertainPanel(panelBubble,chartPanelBubble);
		setChartOnCertainPanel(panelCountries,chartCountries);  
		
	}

	private void setChartOnCertainPanel(JPanel panel, ChartPanel charPanel) {
		  panel.removeAll();
		  panel.add(charPanel);
		  panel.updateUI();
		
	}

	private void createPieChart(GenericList<GeneralDomain> listPropery2) {
		DefaultPieDataset dp=new DefaultPieDataset();
		Property property=new Property();
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		double sum=0;
		double totalSum=0;
		Reservation reservation=new Reservation();
		for(int i=0;i<listPropery2.size();i++) {
			property=(Property) listPropery2.get(i);
			list=map.get(property.getName());
			for(int j=0;j<list.size();j++) {
				if(list.get(j) instanceof Reservation) {
					reservation=(Reservation) list.get(j);
					sum+=reservation.getAmount();
				}
			}
			dp.setValue(property.getName()+" ("+property.getType_Of_Property()+")",sum);
			sum=0;
		}
		
		
		
		
		jchar1=ChartFactory.createPieChart3D("PROPERTY SALARY ( IN % ) ",dp, true, true, false);
		
		
		PiePlot3D plot1=(PiePlot3D) jchar1.getPlot();
		plot1.setStartAngle(0);
		plot1.setForegroundAlpha(0.5f);
		
		chartPanel = new ChartPanel(jchar1);
		chartPanel.setSize(new Dimension(200,200));
		chartPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		chartPanel.setPreferredSize(new java.awt.Dimension(700,400) ); 
		
		
	}
	
	private void createBarChartCountries(Map<String, Double> mapCountries) {
		GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
		Reservation reservation=new Reservation();
		Adress adress=new Adress();
		double sum=0;
		for(Map.Entry<String,GenericList<GeneralDomain>> entry : map.entrySet()) {
			if(entry.getValue().get(0) instanceof Property) {
				list=entry.getValue();
				adress=(Adress) list.get(1);
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						if(mapCountries.get(adress.getCountry()) != null) {
							sum=mapCountries.get(adress.getCountry());
							mapCountries.remove(adress.getCountry());
							mapCountries.put(adress.getCountry(),reservation.getAmount()+sum);
						}
						
					}
				}
			}
		}
		DefaultCategoryDataset dcd2=new DefaultCategoryDataset(); 
		String key;
		double sum1=0;
		for(Map.Entry<String,Double> entry : mapCountries.entrySet()) {
			key=entry.getKey();
			sum1=entry.getValue();
			dcd2.setValue(sum1,key,key);
		}
		
		jchar5=ChartFactory.createBarChart("PROFIT FROM ALL COUNTRIES","COUNTRY NAME","PROFIT FROM COUNTRY", dcd2, PlotOrientation.HORIZONTAL, true, false, true);
		
		 CategoryPlot plot=jchar5.getCategoryPlot();
		 plot.setRangeGridlinePaint(Color.BLACK);
		  
		 chartCountries=new ChartPanel(jchar5);
		 chartCountries.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		 chartCountries.setPreferredSize(new java.awt.Dimension(700,400) );  
	}

	
	  
	 

	private void createBarChart(GenericList<GeneralDomain> listPropery2) {
		 DefaultCategoryDataset dcd=new DefaultCategoryDataset(); 
		 Property property=new Property();
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			double sum=0;
			double totalSum=0;
			Reservation reservation=new Reservation();
			for(int i=0;i<listPropery2.size();i++) {
				property=(Property) listPropery2.get(i);
				list=map.get(property.getName());
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						sum+=reservation.getAmount();
					}
				}
				dcd.setValue(sum,property.getName()+" ("+property.getType_Of_Property()+")",property.getName());
				totalSum+=sum;
				sum=0;
			}
		  
		  jchar=ChartFactory.createBarChart("PROPERTY SALARY","PROPERTY NAME","PROPERTY SALARY", dcd, PlotOrientation.HORIZONTAL, true, false, true);
		  
		  CategoryPlot plot=jchar.getCategoryPlot();
		  plot.setRangeGridlinePaint(Color.BLACK);
		  
		  charPanel=new ChartPanel(jchar);
		  charPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  charPanel.setPreferredSize(new java.awt.Dimension(700,400) );
		  tfThisClient.setText(totalSum+" $");
	}
	
	private void createBarChartAverage(GenericList<GeneralDomain> listPropery2) {
		DefaultCategoryDataset dcd2=new DefaultCategoryDataset(); 
		 Property property=new Property();
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			double sum=0;
			int count=0;
			Reservation reservation=new Reservation();
			for(int i=0;i<listPropery2.size();i++) {
				property=(Property) listPropery2.get(i);
				list=map.get(property.getName());
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						sum+=reservation.getNumberNights();
						count++;
					}
				}
				double averag=sum/count;
				if(averag > 0) {
					dcd2.setValue(sum/count,property.getName()+" ("+property.getType_Of_Property()+")",property.getName());
				}else {
					dcd2.setValue(0.0,property.getName()+" ("+property.getType_Of_Property()+")",property.getName());
				}
				sum=0;
				count=0;
			}
		  
		  jchart2=ChartFactory.createBarChart("AVERAGE NUMBER OF NIGHT IN PROPERTIES","PROPERTY NAME","AVERAGE NUMBER OF NIGHTS", dcd2, PlotOrientation.VERTICAL, true, false, true);
		  
		  CategoryPlot plot=jchart2.getCategoryPlot();
		  plot.setRangeGridlinePaint(Color.BLACK);
		  
		  chartPannelBar2=new ChartPanel(jchart2);
		  chartPannelBar2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  chartPannelBar2.setPreferredSize(new java.awt.Dimension(700,400) );
		
	}
	
	private void createBarChartPeopleRatio(GenericList<GeneralDomain> listPropery2) {
		DefaultCategoryDataset dcd3=new DefaultCategoryDataset(); 
		 Property property=new Property();
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			double sumAdults=0;
			double sumChildren=0;
			int count=0;
			Reservation reservation=new Reservation();
			for(int i=0;i<listPropery2.size();i++) {
				property=(Property) listPropery2.get(i);
				list=map.get(property.getName());
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						sumAdults+=reservation.getNumberAdults();
						sumChildren+=reservation.getNumberChildren();
						count++;
					}
				}
				double averagAdults=sumAdults/count;
				double averagChildren=sumChildren/count;
				if(averagAdults > 0 && averagChildren > 0) {
					dcd3.setValue(averagAdults,"ADULTS",property.getName()+" ("+property.getType_Of_Property()+")");
					dcd3.setValue(averagChildren,"CHILDREN",property.getName()+" ("+property.getType_Of_Property()+")");
				}else if(averagAdults <=0){
					dcd3.setValue(0.0,"ADULTS",property.getName());
					dcd3.setValue(averagChildren,"CHILDREN",property.getName()+" ("+property.getType_Of_Property()+")");
				}else if(averagChildren <= 0){
					dcd3.setValue(averagAdults,"ADULTS",property.getName()+" ("+property.getType_Of_Property()+")");
					dcd3.setValue(0.0,"CHILDREN",property.getName()+" ("+property.getType_Of_Property()+")");
				}
				sumAdults=0;
				sumChildren=0;
				count=0;
			}
		  
		  jchart3=ChartFactory.createBarChart("RATIO OF NUMBER OF ADULTS AND YOUNG PEOPLE","AVERAGE NUMBER OF PEOPLE","PROPERTY NAME", dcd3, PlotOrientation.VERTICAL, true, false, true);
		  
		  CategoryPlot plot=jchart3.getCategoryPlot();
		  plot.setRangeGridlinePaint(Color.BLACK);
		  
		  chartPanelBar3=new ChartPanel(jchart3);
		  chartPanelBar3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		  chartPanelBar3.setPreferredSize(new java.awt.Dimension(700,400) );
		
	}
	
	private void createBarChartBubble(GenericList<GeneralDomain> listPropery2) {
		 DefaultXYZDataset defaultxyzdataset = new DefaultXYZDataset(); 
		 
		 Property property=new Property();
			GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
			double sum=0;
			int count=0;
			Reservation reservation=new Reservation();
			for(int i=0;i<listPropery2.size();i++) {
				property=(Property) listPropery2.get(i);
				list=map.get(property.getName());
				for(int j=0;j<list.size();j++) {
					if(list.get(j) instanceof Reservation) {
						reservation=(Reservation) list.get(j);
						sum+=reservation.getAmount();
						count++;
					}
				}
				if(sum !=0 && count != 0) {
					  double ad[ ] = {sum};                 
				      double ad1[ ] = {count};    
				      double ad2[ ] = {0.1};
				      double ad3[][] = {ad, ad1,ad2};
				      defaultxyzdataset.addSeries(property.getName()+" ("+property.getType_Of_Property()+")", ad3 );
				}
				sum=0;
				count=0;
			}             
	            
	      
	      
	      jchar4 = ChartFactory.createBubbleChart( "RATIO OF AMOUNT AND NUMBER OF RESERVATION", "AMOUNT OF RESERVATION", "NUMBER OF RESERVATION", defaultxyzdataset, PlotOrientation.HORIZONTAL, true, true, false);
	      
	      XYPlot xyplot = ( XYPlot )jchar4.getPlot( );                 
	      xyplot.setForegroundAlpha( 0.65F );                 
	      XYItemRenderer xyitemrenderer = xyplot.getRenderer( );
	      xyitemrenderer.setSeriesPaint( 0 , Color.blue );                 
	      NumberAxis numberaxis = ( NumberAxis )xyplot.getDomainAxis( );                 
	      //numberaxis.setLowerMargin( 0.2 );                 
	      //numberaxis.setUpperMargin( 0.5 );                 
	      NumberAxis numberaxis1 = ( NumberAxis )xyplot.getRangeAxis( );                 
	      //numberaxis1.setLowerMargin( 0.8 );                 
	      //numberaxis1.setUpperMargin( 0.9 );
	      
	      chartPanelBubble=new ChartPanel(jchar4);
	      chartPanelBubble.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	      chartPanelBubble.setPreferredSize(new java.awt.Dimension(700,400) );
	      
	}

	public void addProperty(GenericList<GeneralDomain> list) {
		GenericList<GeneralDomain>listUser=new GenericList<GeneralDomain>();
		Property property=(Property) list.get(0);
		User user=new User();
		User_Info user_info=new User_Info();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof User) {
				user=(User) list.get(i);
				user_info=(User_Info) list.get(i+1);
				list.delete(user);
				list.delete(user_info);
			}
		}
		listUser.add(user);
		listUser.add(user_info);
		map.put(user.getUsername(),listUser);
		map.put(property.getName(),list);
		fillJListAdmins();
		
	}

	public void addReservation(String name, Reservation reservation) {
		map.get(name).add(reservation);
		enterProfitOfAllProperties();
		fillJListAdmins();
	}

	public void deleteReservation(String name, Reservation reservation) {
		map.get(name).delete(reservation);
		enterProfitOfAllProperties();
		fillJListAdmins();
		
	}
}
