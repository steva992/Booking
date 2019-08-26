package com.comtrade.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.comtrade.commonmethod.CommonMethod;
import com.comtrade.constants.AbsolutePath;
import com.comtrade.constants.CountryesTxt;
import com.comtrade.constants.Reservation_Constant;
import com.comtrade.constants.Room_Constants;
import com.comtrade.constants.TransferClass_Message;
import com.comtrade.constants.URL;
import com.comtrade.domain.property.Property;
import com.comtrade.doman.room.Room;
import com.comtrade.reservation.Reservation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rating extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lbl1,lbl2,lbl3,lbl4,lbl5;
	private static int rating;
	private Reservation reservation;
	private JLabel lblPicture;
	private Property nameProperty;
	private Room room;
	private JTextArea textArea;
	
	

	
	public static int getRating() {
		return rating;
	}


	public static void setRating(int rating) {
		Rating.rating = rating;
	}


	public Rating(Reservation reservation, Property property, Room room) {
		setModal(true);
		this.reservation=reservation;
		this.nameProperty=property;
		this.room=room;
		setBounds(100, 100, 528, 407);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 423, 315);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("How do you rate expirience in our hotel?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 11, 300, 44);
		contentPanel.add(lblNewLabel);;
		
		lblPicture = new JLabel("New label");
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPicture.setBounds(10, 0, 414, 304);
		contentPanel.add(lblPicture);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_RATING.getValue(), lblPicture);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		textArea.setEditable(false);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setBounds(186, 199, 215, 105);
		contentPanel.add(textArea);
		
		lbl5 = new JLabel("New label");
		lbl5.setBounds(435, 11, 67, 52);
		getContentPane().add(lbl5);
		
		lbl4 = new JLabel("New label");
		lbl4.setBounds(433, 74, 67, 52);
		getContentPane().add(lbl4);
		
		lbl3 = new JLabel("New label");
		lbl3.setBounds(435, 153, 67, 52);
		getContentPane().add(lbl3);
		
		lbl2 = new JLabel("New label");
		lbl2.setBounds(433, 225, 67, 52);
		getContentPane().add(lbl2);
		{
			lbl1 = new JLabel("New label");
			lbl1.setBounds(435, 305, 67, 52);
			getContentPane().add(lbl1);
			lbl1.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
					CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl1);
				}
			});
			lbl1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					rating=1;
					dispose();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					setPictureOnLabel();
				}
			});
		}
		lbl2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl1);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl2);
			}
		});
		lbl2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=2;
				dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setPictureOnLabel();
			}
		});
		lbl3.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl1);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl3);
			}
		});
		lbl3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=3;
				dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setPictureOnLabel();
			}
		});
		lbl4.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl1);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl4);
			}
		});
		lbl4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=4;
				dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setPictureOnLabel();
			}
		});
		lbl5.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl1);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl2);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl3);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl4);
				CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_FULL_STAR.getValue(), lbl5);
			}
		});
		lbl5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating=5;
				dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setPictureOnLabel();
			}
		});
		setPictureOnLabel();
		settextonTextArea();
	}


	private void settextonTextArea() {
		textArea.append("For reservation :\n");
		textArea.append("( "+Reservation_Constant.RESERVATION_CODE.getValue()+" :"+reservation.getReservation_code()+" ) ");
		textArea.append("\n");
		textArea.append("( "+Reservation_Constant.PROPERTY.getValue()+" :"+nameProperty.getName()+" ) ");
		textArea.append("\n");
		textArea.append("( "+Room_Constants.ROOM_TYPE.getValue()+" :"+room.getType()+" ) ");
		textArea.append("\n");
		textArea.append("( "+Reservation_Constant.CHECK_IN.getValue()+" :"+reservation.getCheckIn()+" ) ");
		textArea.append("\n");
		textArea.append("( "+Reservation_Constant.CHECK_OUT.getValue()+" :"+reservation.getCheckOut()+" ) ");
	}


	private void setPictureOnLabel() {
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl1);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl2);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl3);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl4);
		CommonMethod.setNewPicutreOnLabel(AbsolutePath.absolutePath()+URL.PICTURE_PROPERTY_EMPTY_STAR.getValue(),lbl5);
	}
}
