package com.shopnow.main;

public class Validator {

	public static boolean isDay(String str) {
		if (str.length() > 0) {
			int n = Integer.parseInt(str);
			return n < 32 && n > 0;
		}
		return false;
	}

	public static boolean isMonth(String str) {
		if (str.length() > 0) {
			int n = Integer.parseInt(str);
			return n < 13 && n > 0;
		}
		return false;
	}

	public static boolean isYear(String str) {
		if (str.length() > 0) {
			int n = Integer.parseInt(str);
			return n < 2100 && n > 1900;
		}
		return false;
	}

	public static boolean isMobile(String str) {
		return str.length() > 10;
	}

	public static boolean isEmail(String str) {
		return str.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}

	public static boolean isName(String str) {
		return str.length() >= 4;
	}

	public static boolean isDBName(String str) {
		return str.matches("[a-zA-Z0-9]{4,20}") && !str.equals("dbname")
				&& !str.contains("cover");
	}
}
