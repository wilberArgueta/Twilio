package com.dominosoft.twilio;

import com.dominosoft.twilio.model.Message;

public class TestPhoneNumber {

	public static void main(String[] args) {
		Message message = new Message("79132465", "+74455281", "La amoo");

		System.out.println(message.getDataEncode());
	}
}
