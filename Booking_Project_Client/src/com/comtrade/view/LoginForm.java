package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.User;
import com.comtrade.domain.User_Additional_Features;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.proxy.IProxy;
import com.comtrade.proxy.ProxyLogin;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsernameLogin;
	private JPasswordField pfPasswordLogin;
	private JTextField tfUsernameSignup;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfEmail;
	private JTextField tfPhoneNumber;
	private JPasswordField pfConfirmPasswordSignUp;
	private JPasswordField pfPasswordSignUp;
	private JPanel panelForAll,panelAdmin;
	private JLayeredPane layeredPane;
	private JCheckBox cbProperty;
	private JButton btnStepTwo,btnSignUpPanel;
	private JTextField tfNameOfProperty;
	private JTextField tfState;
	private JTextField tfCity;
	private JTextField tfAdress;
	private JRadioButton rbMan,rbWoman;
	private ButtonGroup buttonGroup=new ButtonGroup();
	private JCheckBox chckbxNewCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(70, 119, 238, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 203, 238, 33);
		contentPane.add(lblPassword);
		
		tfUsernameLogin = new JTextField();
		tfUsernameLogin.setBounds(70, 156, 238, 36);
		contentPane.add(tfUsernameLogin);
		tfUsernameLogin.setColumns(10);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=tfUsernameLogin.getText();
				String password=String.valueOf(pfPasswordLogin.getPassword());
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				IProxy iProxy=new ProxyLogin();
				try {
					iProxy.login(user);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogIn.setForeground(Color.RED);
		btnLogIn.setBounds(108, 338, 157, 41);
		contentPane.add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sing Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				layeredPane.removeAll();
				layeredPane.add(panelForAll);
				layeredPane.revalidate();
			}
		});
		btnSignUp.setBounds(108, 403, 157, 41);
		contentPane.add(btnSignUp);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(390, 11, 476, 604);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_15413900909851");
		
		panelForAll = new JPanel();
		panelForAll.setBackground(Color.CYAN);
		layeredPane.add(panelForAll, "name_11973441013692");
		panelForAll.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username *");
		lblNewLabel_1.setBounds(10, 11, 164, 28);
		panelForAll.add(lblNewLabel_1);
		
		JLabel lblPassword_1 = new JLabel("Password *");
		lblPassword_1.setBounds(10, 86, 164, 28);
		panelForAll.add(lblPassword_1);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password *");
		lblConfirmPassword.setBounds(10, 158, 164, 28);
		panelForAll.add(lblConfirmPassword);
		
		JLabel lblName = new JLabel("Name *");
		lblName.setBounds(10, 231, 164, 28);
		panelForAll.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname *");
		lblSurname.setBounds(10, 319, 164, 28);
		panelForAll.add(lblSurname);
		
		JLabel lblGender = new JLabel("Gender *");
		lblGender.setBounds(10, 403, 164, 28);
		panelForAll.add(lblGender);
		
		JLabel lblEmail = new JLabel("Email *");
		lblEmail.setBounds(14, 468, 164, 28);
		panelForAll.add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number *");
		lblPhoneNumber.setBounds(10, 534, 164, 28);
		panelForAll.add(lblPhoneNumber);
		
		JLabel lblNewLabel_2 = new JLabel("Picture");
		lblNewLabel_2.setBounds(255, 122, 164, 135);
		panelForAll.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("<");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_2.setBounds(268, 302, 67, 23);
		panelForAll.add(btnNewButton_2);
		
		JButton button = new JButton(">");
		button.setFont(new Font("Tahoma", Font.BOLD, 13));
		button.setBounds(352, 302, 67, 23);
		panelForAll.add(button);
		
		tfUsernameSignup = new JTextField();
		tfUsernameSignup.setBounds(10, 42, 164, 28);
		panelForAll.add(tfUsernameSignup);
		tfUsernameSignup.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(10, 270, 164, 28);
		panelForAll.add(tfName);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		tfSurname.setBounds(10, 364, 164, 28);
		panelForAll.add(tfSurname);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(14, 495, 164, 28);
		panelForAll.add(tfEmail);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setBounds(10, 565, 164, 28);
		panelForAll.add(tfPhoneNumber);
		
		pfConfirmPasswordSignUp = new JPasswordField();
		pfConfirmPasswordSignUp.setBounds(10, 197, 164, 23);
		panelForAll.add(pfConfirmPasswordSignUp);
		
		pfPasswordSignUp = new JPasswordField();
		pfPasswordSignUp.setBounds(10, 116, 164, 31);
		panelForAll.add(pfPasswordSignUp);
		
		rbMan = new JRadioButton("Man");
		rbMan.setBounds(10, 438, 53, 23);
		panelForAll.add(rbMan);
		buttonGroup.add(rbMan);
		
		rbWoman = new JRadioButton("Woman");
		rbWoman.setBounds(85, 438, 93, 23);
		panelForAll.add(rbWoman);
		buttonGroup.add(rbWoman);
		
		cbProperty = new JCheckBox("You Have A Property");
		cbProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbProperty.isSelected()) {
					btnStepTwo.setVisible(true);
					btnSignUpPanel.setVisible(false);
					
				}else {
					btnStepTwo.setVisible(false);
					btnSignUpPanel.setVisible(true);
				}
				
			}
		});
		cbProperty.setBounds(281, 11, 152, 41);
		panelForAll.add(cbProperty);
		
		btnSignUpPanel = new JButton("Sign Up");
		btnSignUpPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=tfUsernameSignup.getText();
				String password=String.copyValueOf(pfPasswordSignUp.getPassword());
				String confirmPassword=String.copyValueOf(pfConfirmPasswordSignUp.getPassword());
				String name=tfName.getText();
				String surname=tfSurname.getText();
				String email=tfEmail.getText();
				String mobileNumber=tfPhoneNumber.getText();
				String gender = null;
				if(rbMan.isSelected()) {
					gender="Man";
				}else if(rbWoman.isSelected()) {
					gender="Woman";
				}
				//Treba naci kako da dovlacis sve slike sa servera
				String picture="Stevan";
				boolean AllFieldCompleted=FieldComplete(username,password,confirmPassword,name,surname,email,mobileNumber,gender);
				if(AllFieldCompleted) {
					if(password.equals(confirmPassword)) {
						User user=new User();
						user.setUsername(username);
						user.setPassword(password);
						user.setStatus("user");
						User_Additional_Features userAdditional=new User_Additional_Features();
						userAdditional.setName(name);
						userAdditional.setEmail(email);
						userAdditional.setGender(gender);
						userAdditional.setMobileNumber(mobileNumber);
						userAdditional.setPictureURL(picture);
						userAdditional.setSurname(surname);
						GenericList<GeneralDomain>list=new GenericList<GeneralDomain>();
						list.add(user);
						list.add(userAdditional);
						ControlerKI.getInstance().enterTheUserAndAdditionalUser(list);
					}else {
						JOptionPane.showMessageDialog(null,"Incorrect password entry");
					}
				}else {
					JOptionPane.showMessageDialog(null,"!!! All fields must be filled !!!");
				}
				
			}
		});
		btnSignUpPanel.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSignUpPanel.setBounds(255, 505, 164, 45);
		panelForAll.add(btnSignUpPanel);
		
		btnStepTwo = new JButton("Go to Step 2 >>");
		btnStepTwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panelAdmin);
				layeredPane.revalidate();
			}
		});
		btnStepTwo.setForeground(Color.BLACK);
		btnStepTwo.setVisible(false);
		btnStepTwo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnStepTwo.setBounds(255, 455, 164, 45);
		panelForAll.add(btnStepTwo);
		panelAdmin = new JPanel();
		panelAdmin.setBackground(Color.YELLOW);
		layeredPane.add(panelAdmin, "name_11973447082047");
		panelAdmin.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Name");
		lblNewLabel_3.setBounds(10, 11, 147, 28);
		panelAdmin.add(lblNewLabel_3);
		
		JLabel lblTypeOfProperty = new JLabel("Type Of Property");
		lblTypeOfProperty.setBounds(10, 81, 147, 28);
		panelAdmin.add(lblTypeOfProperty);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(10, 142, 147, 28);
		panelAdmin.add(lblState);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(10, 210, 147, 28);
		panelAdmin.add(lblCity);
		
		JLabel lblAdre = new JLabel("Adress");
		lblAdre.setBounds(10, 275, 147, 28);
		panelAdmin.add(lblAdre);
		
		tfNameOfProperty = new JTextField();
		tfNameOfProperty.setBounds(10, 50, 212, 20);
		panelAdmin.add(tfNameOfProperty);
		tfNameOfProperty.setColumns(10);
		
		tfState = new JTextField();
		tfState.setColumns(10);
		tfState.setBounds(10, 181, 212, 20);
		panelAdmin.add(tfState);
		
		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(10, 249, 212, 20);
		panelAdmin.add(tfCity);
		
		tfAdress = new JTextField();
		tfAdress.setColumns(10);
		tfAdress.setBounds(10, 317, 212, 20);
		panelAdmin.add(tfAdress);
		
		JComboBox cbTypeOfProperty = new JComboBox();
		cbTypeOfProperty.setBounds(10, 111, 212, 20);
		panelAdmin.add(cbTypeOfProperty);
		
		JLabel lblNewLabel_4 = new JLabel("Picture");
		lblNewLabel_4.setBounds(10, 365, 212, 178);
		panelAdmin.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("<<");
		btnNewButton.setBounds(10, 570, 89, 23);
		panelAdmin.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.setBounds(133, 570, 89, 23);
		panelAdmin.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Sign Up You and Your Property");
		btnNewButton_3.setBounds(274, 453, 181, 76);
		panelAdmin.add(btnNewButton_3);
		
		pfPasswordLogin = new JPasswordField();
		pfPasswordLogin.setBounds(70, 239, 238, 36);
		contentPane.add(pfPasswordLogin);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hide / Unhide");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxNewCheckBox.isSelected()) {
					pfPasswordLogin.setEchoChar((char) 0);
				}else {
					pfPasswordLogin.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(136, 295, 102, 23);
		contentPane.add(chckbxNewCheckBox);
	}

	protected boolean FieldComplete(String username, String password, String confirmPassword, String name,
			String surname, String email, String phoneNumber, String gender) {
		if(username.length()<1 || password.length()<1 || confirmPassword.length()<1 || name.length()<1 || surname.length()<1 || email.length()<1 || phoneNumber.length()<1 || gender==null) {
			return false;
		}
		return true;
	}
}
