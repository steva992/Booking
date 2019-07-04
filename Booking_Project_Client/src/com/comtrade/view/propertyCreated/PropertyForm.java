package com.comtrade.view.propertyCreated;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.constants.Regular_Expression;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.Type_Of_Property;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.Property;
import com.comtrade.domain.Property_Info;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Info;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.importantCommonMethod.ImportantCommonMethod;
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
import java.awt.Font;

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
		setBounds(100, 100, 424, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Type Of Property");
		label.setBounds(20, 143, 147, 28);
		contentPane.add(label);
		
	    cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setBounds(20, 173, 212, 34);
		contentPane.add(cbTypeOfProperty);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setColumns(10);
		tfNameOfProperty.setBounds(20, 112, 212, 34);
		contentPane.add(tfNameOfProperty);
		
		JLabel lblNameOfProperty = new JLabel("Name Of Property");
		lblNameOfProperty.setBounds(20, 73, 147, 28);
		contentPane.add(lblNameOfProperty);
		
		JLabel label_2 = new JLabel("State");
		label_2.setBounds(20, 204, 147, 28);
		contentPane.add(label_2);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(20, 229, 212, 34);
		contentPane.add(tfState);
		
		JLabel label_3 = new JLabel("City");
		label_3.setBounds(20, 272, 147, 28);
		contentPane.add(label_3);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(20, 297, 212, 34);
		contentPane.add(tfCity);
		
		JLabel lblAdressstreet = new JLabel("Adress_Street");
		lblAdressstreet.setBounds(20, 337, 147, 28);
		contentPane.add(lblAdressstreet);
		
		tfAdress_Street = new JTextField();
		tfAdress_Street.setColumns(10);
		tfAdress_Street.setBounds(20, 365, 212, 34);
		contentPane.add(tfAdress_Street);
		
		JButton button_2 = new JButton("Sign Up You \nand Your Property");
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
							TransferClass transferClass=ControlerKI.getInstance().enterProperty(list);
							String poruka=transferClass.getMessage();
							if(poruka.equals(TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue())) {
								JOptionPane.showMessageDialog(null,TransferClass_Message.SUCCESSFUL_REGISTRATION.getValue());
								dispose();
								ImportantCommonMethod.startLoginForm();
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
		button_2.setBounds(20, 511, 212, 42);
		contentPane.add(button_2);
		
		JButton btnBackToLog = new JButton("<< <  Step 1");
		btnBackToLog.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBackToLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBackToLog.setBounds(20, 11, 131, 42);
		contentPane.add(btnBackToLog);
		
		JLabel lblAdressnumber = new JLabel("Adress_Number");
		lblAdressnumber.setBounds(20, 410, 147, 28);
		contentPane.add(lblAdressnumber);
		
		tfAdress_Number = new JTextField();
		tfAdress_Number.setColumns(10);
		tfAdress_Number.setBounds(20, 452, 212, 34);
		contentPane.add(tfAdress_Number);
		
		JLabel lblNewLabel = new JLabel("Example");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(253, 115, 141, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblExample = new JLabel("Example");
		lblExample.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExample.setForeground(Color.RED);
		lblExample.setBounds(253, 229, 141, 28);
		contentPane.add(lblExample);
		
		JLabel lblExample_1 = new JLabel("Example");
		lblExample_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExample_1.setForeground(Color.RED);
		lblExample_1.setBounds(253, 297, 141, 28);
		contentPane.add(lblExample_1);
		
		JLabel lblExampleExample = new JLabel("Example Example");
		lblExampleExample.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblExampleExample.setForeground(Color.RED);
		lblExampleExample.setBounds(253, 365, 141, 28);
		contentPane.add(lblExampleExample);
		
		JLabel label_6 = new JLabel("15");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setForeground(Color.RED);
		label_6.setBounds(253, 452, 141, 28);
		contentPane.add(label_6);
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
