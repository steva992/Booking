package com.comtrade.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.cache.Cache;
import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.Panel_Dimension;
import com.comtrade.constants.URLS;
import com.comtrade.controlerBL.ControlerBLServer;
import com.comtrade.controlerBL.Controler_Thread;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.Generic;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.genericClasses.GenericMap;
import com.comtrade.so.GeneralSystemOperation;
import com.comtrade.so.database.BackFromDatabase;
import com.comtrade.threads.ServerThread;
import com.comtrade.threads.WatchThread;

import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServerForm extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel lblBackGround;
	private JLabel lblWatch,lblDate;
	private JLabel lblNewLabel;
	private int number=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerForm frame = new ServerForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ServerForm() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Panel_Dimension.X.getValue(),Panel_Dimension.Y.getValue(),800,798);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWatch = new JLabel("Time");
		lblWatch.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWatch.setForeground(Color.WHITE);
		lblWatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblWatch.setBounds(387, 129, 228, 44);
		contentPane.add(lblWatch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 484, 753, 266);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setFont(new Font("Cambria Math", Font.BOLD, 14));
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(number==0) {
					ServerThread serverThread=new ServerThread(textArea);
					serverThread.start();
					WatchThread watchThread=new WatchThread(lblWatch,lblDate);
					watchThread.start();
					number++;
				}
			}
		});
		lblNewLabel_1.setBounds(292, 333, 184, 131);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_FOR_SERVER.getValue()+"/Server.png", lblNewLabel_1);
		contentPane.add(lblNewLabel_1);
		
		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblDate.setForeground(Color.WHITE);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(209, 125, 193, 53);
		contentPane.add(lblDate);
		
		lblNewLabel = new JLabel("CURRENT DATE  AND TIME:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(115, 54, 548, 60);
		contentPane.add(lblNewLabel);
		
		lblBackGround = new JLabel("New label");
		//lblBackGround.setIcon(new ImageIcon(ServerForm.class.getResource("/default1/Woman.jpg")));
		lblBackGround.setBounds(0,0,784,759);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_FOR_SERVER.getValue()+"/Server.jpg",lblBackGround);
		contentPane.add(lblBackGround);
		ControlerBLServer.getInstance().setServerForm(this);
		backDataToMap();
	}

	private void backDataToMap() throws Exception {
		GenericMap<String, GenericList<GeneralDomain>>map=new GenericMap<String, GenericList<GeneralDomain>>();
		GeneralSystemOperation<GenericMap<String, GenericList<GeneralDomain>>>generalSO=new BackFromDatabase();
		generalSO.runSO(map);
		Cache.getInstance().setMap(map);
	}

	public void setTextOnTextArea(String string) {
		textArea.append(string);
	}
}
