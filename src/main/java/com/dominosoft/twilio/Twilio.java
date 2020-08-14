package com.dominosoft.twilio;

public class Twilio {

	
	public static String accountSID;
	public static String authToken;
	
	
	public static void init(String accountSID, String authToken) {	
		Twilio.accountSID = accountSID;
		Twilio.authToken = authToken;
	}
}
