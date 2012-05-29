package com.ramon.ramonbank.utils;

public class Validator {
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static boolean validMail(String str){
		//TODO: Una validacion posta
		return true;
	}
	
	public static boolean valDNI(String str){
		str = str.replace(".", "");
		str = str.replace(",", "");
		if (!isNumeric(str)){
			return false;
		}
		Double dni = Double.parseDouble(str);
		if (dni <= 0 || dni >= 900000000){
			return false;
		}
		return true;
	}
	
}