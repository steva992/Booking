package com.comtrade.constants;

import java.io.File;

public enum PicturesURL {
	
	
	PICTURE_FOLDER("/picture"),
	PROFILE_PICTURE_DEFAULT("/Default"),
	PROFILE_PICTURE_DEFAULT_CRYING("/Default/Crying.jpg"),
	
	PROFILE_PICTURE_HOTELS("/Property"),
	PROFILE_PICTURE_USERS("/Users"),
	PICTURE_FOR_SERVER("/Server"),
	PROFILE_PICTURE_USER_COUNTRYES("/CountryesFlag"),
	
	PICTURE_FOR_LOGIN_LOGO("/Wallpaper/Logo.jpg"),
	PICTURE_FOR_LOGIN_BACKGROUND("/Wallpaper/Login.jpg"),
	PICTURE_FOR_LOGIN_FORGOT_PASSWORD("/Wallpaper/ForgotPassword.jpg"),
	PICTURE_FOR_LOGIN_BUTTON_LOGIN("/Wallpaper/LoginButton.jpg"), 
	PICTURE_FOR_LOGIN_BUTTON_SIGN_UP("/Wallpaper/SignUpButton.png"),
	
	PICTURE_FORGOT_PASSWORD_BACKGROUND("/Wallpaper/Password.jpg"),
	PICTURE_FORGOT_PASSWORD_BUTTON("/Wallpaper/ChangePassword.png"),
	
	PICTURE_SIGN_UP_BACKGROUND("/Wallpaper/SignUp.jpg"),
	PICTURE_SIGN_UP_BUTTON("/Wallpaper/SignUp+.png"),
	PICTURE_SIGN_UP_BUTTON_STEP_TWO("/Wallpaper/Step2.png"),
	PICTURE_BACK_TO("/Wallpaper/BackTo.jpg"),
	
	PICTURE_PROPERTY_BACKGROUND("/Wallpaper/Property.jpg"),
	PICTURE_PROPERTY_CREATED("/Wallpaper/PropertyCreated.jpg"),
	PICTURE_PROPERTY_EMPTY_STAR("/Wallpaper/EmptyStar.jpg"),
	PICTURE_PROPERTY_FULL_STAR("/Wallpaper/FullStar.jpg"),
	
	PICTURE_ADMIN_BACKGROUND("/Wallpaper/HotelIN.jpg"),
	PICTURE_ADMIN_UPDATE("/Wallpaper/UpdateProperty.jpg"),
	PICTURE_ADMIN_UPLOAD("/Wallpaper/Upload.png"),
	PICTURE_ADMIN_DELETE("/Wallpaper/Delete.png"),
	PICTURE_ADMIN_NEW_PROPERTY("/Wallpaper/AddProperty.png"),
	PICTURE_ADMIN_CARD("/Wallpaper/Card+.png"),
	
	PICTURE_ADMIN_TV("/Wallpaper/TV.png"),
	PICTURE_ADMIN_WIFI("/Wallpaper/WiFi.png"),
	PICTURE_ADMIN_MINI_BAR("/Wallpaper/MiniBar.png"),
	PICTURE_ADMIN_BALCON("/Wallpaper/Balcon.png"),
	PICTURE_ADMIN_LIVING_ROOM("/Wallpaper/LivingRoom.png"),
	PICTURE_ADMIN_KITCHEN("/Wallpaper/Kitchen.png"),
	PICTURE_ADMIN_SMOKING("/Wallpaper/Smoking.png"),
	PICTURE_ADMIN_JACUZZI("/Wallpaper/Jacuzzi.png"),
	PICTURE_ADMIN_AIR_CONDITION("/Wallpaper/Air.png"),
	PICTURE_ADMIN_UNDER_FLOOR_HEATING("/Wallpaper/Under.png"),
	PICTURE_ADMIN_WATCH("/Wallpaper/Watch.png"),
	PICTURE_ADMIN_WORLD("/Wallpaper/World.png"),
	
	PICTURE_USER_CARD("/Wallpaper"),
	PICTURE_USER_CARD_VISA("/Wallpaper/VISA.png"),
	PICTURE_USER_CARD_DINA("/Wallpaper/DINA.png"),
	PICTURE_USER_CARD_MASTER("/Wallpaper/MASTER.png"),
	PICTURE_USER_RESERVATION("/Wallpaper/Reservation.png"),
	PICTURE_USER_BACKGROUND("/Wallpaper/UserPanel.jpg");
	
	
	private String value;

	private PicturesURL(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
