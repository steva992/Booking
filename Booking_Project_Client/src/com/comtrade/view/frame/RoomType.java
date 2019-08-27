package com.comtrade.view.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.URLS;
import com.comtrade.domain.room.decorator.ApartmentDecorator;
import com.comtrade.domain.room.decorator.LuxuryRoomDecorator;
import com.comtrade.domain.room.decorator.OrdinaryRoomDecorator;
import com.comtrade.domain.room.decorator.Room_Info_Decorator;
import com.comtrade.domain.user.User;
import com.comtrade.panel.admin.Admin_Panel;

import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class RoomType extends JFrame {

	private JPanel contentPane;
	private User user;
	private Room_Info_Decorator room;
	private String string;
	private JCheckBox chckbxWifi,cbTV,chckbxMinbar,chckbxBalkon,chckbxLivingRomm,chckbxKitchen,chckbxSmiking,chckbxJacuzzi,chckbxAircondition,chckbxUnderFlorHeating;

	
	public RoomType(User user2, Room_Info_Decorator room) {
		this.room=room;
		setBounds(100, 100, 426, 684);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbTV = new JCheckBox("TV");
		cbTV.setEnabled(false);
		cbTV.setBackground(Color.WHITE);
		cbTV.setFont(new Font("Castellar", Font.BOLD, 14));
		cbTV.setForeground(Color.BLACK);
		cbTV.setBounds(6, 7, 235, 31);
		contentPane.add(cbTV);
		
		
		chckbxWifi = new JCheckBox("WI_FI");
		chckbxWifi.setEnabled(false);
		chckbxWifi.setBackground(Color.WHITE);
		chckbxWifi.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxWifi.setForeground(Color.BLACK);
		chckbxWifi.setBounds(6, 65, 235, 31);
		contentPane.add(chckbxWifi);
		
		chckbxMinbar = new JCheckBox("MIN_BAR");
		chckbxMinbar.setEnabled(false);
		chckbxMinbar.setBackground(Color.WHITE);
		chckbxMinbar.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxMinbar.setForeground(Color.BLACK);
		chckbxMinbar.setBounds(6, 130, 235, 31);
		contentPane.add(chckbxMinbar);
		
		chckbxBalkon = new JCheckBox("Balkon");
		chckbxBalkon.setEnabled(false);
		chckbxBalkon.setBackground(Color.WHITE);
		chckbxBalkon.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxBalkon.setForeground(Color.BLACK);
		chckbxBalkon.setBounds(6, 191, 235, 31);
		contentPane.add(chckbxBalkon);
		
		chckbxLivingRomm = new JCheckBox("Living Room");
		chckbxLivingRomm.setEnabled(false);
		chckbxLivingRomm.setBackground(Color.WHITE);
		chckbxLivingRomm.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxLivingRomm.setForeground(Color.BLACK);
		chckbxLivingRomm.setBounds(6, 253, 235, 31);
		contentPane.add(chckbxLivingRomm);
		
		chckbxKitchen = new JCheckBox("Kitchen");
		chckbxKitchen.setEnabled(false);
		chckbxKitchen.setBackground(Color.WHITE);
		chckbxKitchen.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxKitchen.setForeground(Color.BLACK);
		chckbxKitchen.setBounds(6, 310, 235, 31);
		contentPane.add(chckbxKitchen);
		
		chckbxSmiking = new JCheckBox("Smoking");
		chckbxSmiking.setEnabled(false);
		chckbxSmiking.setBackground(Color.WHITE);
		chckbxSmiking.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxSmiking.setForeground(Color.BLACK);
		chckbxSmiking.setBounds(6, 370, 235, 31);
		contentPane.add(chckbxSmiking);
		
		chckbxJacuzzi = new JCheckBox("Jacuzzi");
		chckbxJacuzzi.setEnabled(false);
		chckbxJacuzzi.setBackground(Color.WHITE);
		chckbxJacuzzi.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxJacuzzi.setForeground(Color.BLACK);
		chckbxJacuzzi.setBounds(6, 430, 235, 31);
		contentPane.add(chckbxJacuzzi);
		
		chckbxAircondition = new JCheckBox("Air_Condition");
		chckbxAircondition.setEnabled(false);
		chckbxAircondition.setBackground(Color.WHITE);
		chckbxAircondition.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxAircondition.setForeground(Color.BLACK);
		chckbxAircondition.setBounds(6, 486, 235, 31);
		contentPane.add(chckbxAircondition);
		
		chckbxUnderFlorHeating = new JCheckBox("Under Flor Heating");
		chckbxUnderFlorHeating.setEnabled(false);
		chckbxUnderFlorHeating.setBackground(Color.WHITE);
		chckbxUnderFlorHeating.setFont(new Font("Castellar", Font.BOLD, 14));
		chckbxUnderFlorHeating.setForeground(Color.BLACK);
		chckbxUnderFlorHeating.setBounds(6, 542, 235, 31);
		contentPane.add(chckbxUnderFlorHeating);
		
		JLabel lblTv = new JLabel("New label");
		lblTv.setBounds(275, 7, 108, 45);
		contentPane.add(lblTv);
		
		JLabel lblWIFI = new JLabel("New label");
		lblWIFI.setBounds(275, 63, 108, 48);
		contentPane.add(lblWIFI);
		
		JLabel lblMinBar = new JLabel("New label");
		lblMinBar.setBounds(275, 122, 108, 48);
		contentPane.add(lblMinBar);
		
		JLabel lblBalkon = new JLabel("New label");
		lblBalkon.setBounds(275, 181, 108, 53);
		contentPane.add(lblBalkon);
		
		JLabel lblLivingRoom = new JLabel("New label");
		lblLivingRoom.setBounds(275, 245, 108, 48);
		contentPane.add(lblLivingRoom);
		
		JLabel lblKitchen = new JLabel("New label");
		lblKitchen.setBounds(275, 304, 108, 45);
		contentPane.add(lblKitchen);
		
		JLabel lblSmoking = new JLabel("New label");
		lblSmoking.setBounds(275, 360, 108, 53);
		contentPane.add(lblSmoking);
		
		JLabel lblJacuzzi = new JLabel("New label");
		lblJacuzzi.setBounds(275, 424, 108, 45);
		contentPane.add(lblJacuzzi);
		
		JLabel lblAir = new JLabel("New label");
		lblAir.setBounds(275, 480, 108, 45);
		contentPane.add(lblAir);
		
		JLabel lblUnder = new JLabel("New label");
		lblUnder.setBounds(275, 536, 108, 45);
		contentPane.add(lblUnder);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_TV.getValue(), lblTv);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_WIFI.getValue(), lblWIFI);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_MINI_BAR.getValue(), lblMinBar);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_BALCON.getValue(), lblBalkon);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_LIVING_ROOM.getValue(), lblLivingRoom);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_KITCHEN.getValue(),lblKitchen);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_SMOKING.getValue(), lblSmoking);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_JACUZZI.getValue(), lblJacuzzi);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_UNDER_FLOOR_HEATING.getValue(), lblUnder);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URLS.PICTURE_ADMIN_AIR_CONDITION.getValue(), lblAir);
		
		JButton btnNewButton = new JButton("<< My Panel");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
				
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Castellar", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(6, 591, 214, 43);
		CommonMethod.setNewPicutreOnButton(AbsolutePath.absolutePath()+URLS.PICTURE_BACK_TO.getValue(),btnNewButton);
		contentPane.add(btnNewButton);
		selectCB();
	}


	private void selectCB() {
		if(room instanceof OrdinaryRoomDecorator) {
			selectOrdinary();
		}else if(room instanceof LuxuryRoomDecorator) {
			selectOrdinary();
			selectLuxury();
		}else if(room instanceof ApartmentDecorator) {
			selectOrdinary();
			selectApartment();
		}
		
	}


	private void selectApartment() {
		chckbxBalkon.setSelected(true);
		chckbxLivingRomm.setSelected(true);
		chckbxKitchen.setSelected(true);
		chckbxSmiking.setSelected(true);
	}


	private void selectLuxury() {
		chckbxJacuzzi.setSelected(true);
		chckbxBalkon.setSelected(true);
		chckbxSmiking.setSelected(true);
		chckbxUnderFlorHeating.setSelected(true);
		chckbxSmiking.setSelected(true);
		chckbxAircondition.setSelected(true);
	}


	private void selectOrdinary() {
		cbTV.setSelected(true);
		chckbxWifi.setSelected(true);
		chckbxMinbar.setSelected(true);
	}

	
}
