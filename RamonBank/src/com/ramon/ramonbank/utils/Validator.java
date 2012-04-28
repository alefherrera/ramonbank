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
}
