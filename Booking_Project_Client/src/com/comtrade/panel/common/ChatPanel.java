package com.comtrade.panel.common;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.URL;
import com.comtrade.constants.Server_Information;
import com.comtrade.controlerKI.ControlerKI;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.message.Message;
import com.comtrade.domain.user.User;
import com.comtrade.genericClasses.GenericList;
import com.comtrade.threads.ReceivingMessageThread;
import com.comtrade.threads.SendingMessageThread;
import com.comtrade.transfer.TransferClass;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ChatPanel extends JPanel {
	private JTextField textField;
	private JTextArea textArea;
	private User user;
	private Message message;
	private JTable table;
	private DefaultTableModel dtm=new DefaultTableModel();
	private GenericList<GeneralDomain>genericList;
	private TransferClass transferClass;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public ChatPanel(User user) throws ClassNotFoundException, IOException {
		transferClass=ControlerKI.getInstance().backALLUserAndProperties();
		genericList=(GenericList<GeneralDomain>) transferClass.getServer_Object_Response();
		this.user=user;
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 430, 209);
		add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 280, 50);
		add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					sendMessage();
				}
				
			}
		});
		
		JButton btnNewButton = new JButton("SendMessage");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendMessage();
			}
		});
		btnNewButton.setBounds(300, 25, 140, 29);
		add(btnNewButton);
		
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBounds(458, 11, 422, 278);
		add(scrollPane_1);
		
		table = new JTable(dtm);
		scrollPane_1.setViewportView(table);
		Object[]columns= {"USERNAME","USER PICTURE","PROPERTY NAME"};
		dtm.addColumn(columns[0]);
		dtm.addColumn(columns[1]);
		dtm.addColumn(columns[2]);
		startChat();
		Object[]row= {1,2,new ImageIcon(AbsolutePath.absolutePath()+URL.PROFILE_PICTURE_DEFAULT.getValue()+"/Property"+".jpg")};
		dtm.addRow(row);
	}

	protected void sendMessage() {
		String messageForSend=textField.getText();
		textArea.append("\n Me : "+messageForSend+"\n");
		message.setPoruka(messageForSend);
		clearTF();
	}

	private void clearTF() {
		textField.setText("");
		
	}

	private void startChat() {
		if(user.getUsername() !=null && !user.getUsername().isEmpty()) {
			message=new Message();
		}	
	}
}
