package com.dominosoft.twilio.util;

public class PhoneNumber {

	public static String formatNumber(String number) {
		final String _key = "whatsapp:";
		final String _sig_plus = "+";
		String firtsDigit = number.substring(0, 1);
		if (firtsDigit.equalsIgnoreCase(_sig_plus))
			return _key + number;
		else
			return _key + _sig_plus + number;
	}

}
