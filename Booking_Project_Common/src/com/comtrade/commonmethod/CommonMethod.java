package com.comtrade.commonmethod;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.tools.JavaFileObject;

import com.comtrade.constants.URLS;
import com.comtrade.domain.GeneralDomain;
import com.comtrade.domain.discount.Discount;
import com.comtrade.domain.property.Adress;
import com.comtrade.domain.property.GeoLocation;
import com.comtrade.domain.property.Property;
import com.comtrade.doman.room.Room;
import com.comtrade.doman.room.Room_Info;
import com.comtrade.genericClasses.GenericList;

public class CommonMethod {
	
	
	private static Image refactoringSetPictureMethod(String URL) {
		ImageIcon image= new ImageIcon(URL);
		image.getImage().flush();
		return image.getImage();
	}
	
	public static void setNewPicutreOnLabel(String pictureURL, JLabel lblPicture) {
		Image image1=refactoringSetPictureMethod(pictureURL);
		Image newImg=image1.getScaledInstance(lblPicture.getWidth(),lblPicture.getHeight(),Image.SCALE_SMOOTH);
		lblPicture.setIcon(new ImageIcon(newImg));
	}

	

	public static void setNewPicutreOnButton(String pictureURL, JButton btnNewButton_3) {
		Image image1=refactoringSetPictureMethod(pictureURL);
		Image newImg=image1.getScaledInstance(btnNewButton_3.getWidth()/4,(int) (btnNewButton_3.getHeight()/1.25f),Image.SCALE_SMOOTH);
		btnNewButton_3.setIcon(new ImageIcon(newImg));
	}

	public static void setNewPicutreOnRadioButton(String string, JRadioButton rdbtnChat) {
		Image image1=refactoringSetPictureMethod(string);
		Image newImg=image1.getScaledInstance(rdbtnChat.getWidth(),(int) (rdbtnChat.getHeight()/1.25f),Image.SCALE_SMOOTH);
		rdbtnChat.setIcon(new ImageIcon(newImg));
	}
	
	
	
}
