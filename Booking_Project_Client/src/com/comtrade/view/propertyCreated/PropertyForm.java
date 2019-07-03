package com.comtrade.view.propertyCreated;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.Regular_Expression;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Property;
import com.comtrade.domain.Property_Info;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.transfer.TransferClass;
import com.comtrade.view.LoginForm;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class PropertyForm extends JFrame{

	private JPanel contentPane;
	private JTextField tfNameOfProperty;
	private JTextField tfState;
	private JTextField tfCity;
	private JTextField tfAdress_Street;
	private User user;
	private User_Info user_Additional_Features;
	private JTextField tfAdress_Number;
	private JComboBox cbTypeOfProperty;

	
	public PropertyForm(GeneralDomain user, GeneralDomain user_Additional_Features) {
		this.user=(User) user;
		this.user_Additional_Features=(User_Info) user_Additional_Features;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Type Of Property");
		label.setBounds(10, 92, 147, 28);
		contentPane.add(label);
		
	    cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setBounds(10, 122, 212, 20);
		contentPane.add(cbTypeOfProperty);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setColumns(10);
		tfNameOfProperty.setBounds(10, 61, 212, 20);
		contentPane.add(tfNameOfProperty);
		
		JLabel lblNameOfProperty = new JLabel("Name Of Property");
		lblNameOfProperty.setBounds(10, 22, 147, 28);
		contentPane.add(lblNameOfProperty);
		
		JLabel label_2 = new JLabel("State");
		label_2.setBounds(10, 153, 147, 28);
		contentPane.add(label_2);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(10, 192, 212, 20);
		contentPane.add(tfState);
		
		JLabel label_3 = new JLabel("City");
		label_3.setBounds(10, 221, 147, 28);
		contentPane.add(label_3);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(10, 260, 212, 20);
		contentPane.add(tfCity);
		
		JLabel lblAdressstreet = new JLabel("Adress_Street");
		lblAdressstreet.setBounds(10, 286, 147, 28);
		contentPane.add(lblAdressstreet);
		
		tfAdress_Street = new JTextField();
		tfAdress_Street.setColumns(10);
		tfAdress_Street.setBounds(10, 328, 212, 20);
		contentPane.add(tfAdress_Street);
		
		JLabel label_5 = new JLabel("Picture");
		label_5.setBounds(292, 103, 212, 178);
		contentPane.add(label_5);
		
		JButton button = new JButton("<<");
		button.setBounds(292, 308, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton(">>");
		button_1.setBounds(415, 308, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Sign Up You and Your Property");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfNameOfProperty.getText();
				String type_Of_Property=cbTypeOfProperty.getSelectedItem().toString();
				String State=tfState.getText();
				String City=tfCity.getText();
				String Adress_Number=tfAdress_Number.getText();
				String Adress_Street=tfAdress_Street.getText();
				boolean testField=fieldValidityProperty(name,State,City,Adress_Number,Adress_Street);
				boolean AllFieldCompleted=FieldComplete(name,State,City,Adress_Number,Adress_Street);
				if(testField) {
					if(AllFieldCompleted) {
						Property property=new Property();
						property.setName(name);
						property.setAlbum_Of_Picture("Grand Kazino");
						property.setRating(0.0);
						property.setType_Of_Property(type_Of_Property);
						Property_Info property_info=new Property_Info();
						property_info.setAdress_street(Adress_Street);
						property_info.setCity(City);
						property_info.setState(State);
						property_info.setCoordinates("20X30");
						property_info.setAdress_number(Integer.parseInt(Adress_Number));
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(user_Additional_Features);
						list.add(property);
						list.add(property_info);
						try {
							String poruka=ControlerKI.getInstance().enterProperty(list).getMessage();
							LoginForm loginForm=new LoginForm();
							loginForm.setVisible(true);
							dispose();
							if(poruka!=null) {
								JOptionPane.showMessageDialog(null,poruka);
							}
			
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,"!!! All fields must be filled !!!");
					}
				}else {
					JOptionPane.showMessageDialog(null,"!!! Incorrectly entered data !!!");
				}
			}
		});
		button_2.setBounds(248, 478, 181, 42);
		contentPane.add(button_2);
		
		JButton btnBackToLog = new JButton("Back To Log In Form");
		btnBackToLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginForm logiinForm=new LoginForm();
				logiinForm.setVisible(true);
				dispose();
			}
		});
		btnBackToLog.setBounds(10, 478, 181, 42);
		contentPane.add(btnBackToLog);
		
		JLabel lblAdressnumber = new JLabel("Adress_Number");
		lblAdressnumber.setBounds(10, 359, 147, 28);
		contentPane.add(lblAdressnumber);
		
		tfAdress_Number = new JTextField();
		tfAdress_Number.setColumns(10);
		tfAdress_Number.setBounds(10, 401, 212, 20);
		contentPane.add(tfAdress_Number);
		addItemAtComboBox();
	}


	private void addItemAtComboBox() {
		cbTypeOfProperty.addItem("Hotel");
		cbTypeOfProperty.addItem("Villa");
		cbTypeOfProperty.addItem("Motel");
		cbTypeOfProperty.addItem("Hostel");
	}


	protected boolean FieldComplete(String name, String state, String city, String adress_Number, String adress_Street) {
		if(name.length()<1 || state.length()<1 || city.length()<1 || adress_Street.length()<1 ||  adress_Number.length()<1) {
			return false;
		}
		return true;
	}


	protected boolean fieldValidityProperty(String name, String state, String city, String adress_Number,
			String adress_Street) {
		backBorderToGray();
		boolean name1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),name);
		boolean state1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),state);
		boolean city1=Pattern.matches(Regular_Expression.ONLY_TEXT_VALUES.getValue(),city);
		boolean adress_Street1=Pattern.matches(Regular_Expression.ADRESS.getValue(),adress_Street);
		boolean adress_Number1=Pattern.matches(Regular_Expression.ONLY_NUMBER_VALUES.getValue(),adress_Number);
		
		if(name1 &&  state1 && city1 && adress_Number1 && adress_Street1) {
			return true;
		}
		
		if(name1==false) {
			tfNameOfProperty.setBorder(BorderFactory.createLineBorder(Color.RED,4));
		}
		
		if(state1==false) {
			tfState.setBorder(BorderFactory.createLineBorder(Color.RED,4));
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
		tfState.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		tfCity.setBorder(BorderFactory.createLineBorder(Color.GRAY,1));
		
	}


	


	
}
