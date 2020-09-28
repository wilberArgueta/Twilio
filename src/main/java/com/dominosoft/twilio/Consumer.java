package com.dominosoft.twilio;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.dominosoft.requests.connections.Connection;
import com.dominosoft.twilio.model.Message;
import com.dominosoft.twilio.model.Response;

public class Consumer {

	private Message message;
	private final String API_WA_URL = "https://api.twilio.com/2010-04-01/Accounts/%s/Messages.json";
	private static Consumer consumer = null;

	public static Consumer build(Message message) {
		if (consumer != null) {
			consumer.setMessage(message);
			return consumer;
		} else {
			consumer = new Consumer();
			consumer.setMessage(message);
			return consumer;

		}
	}

	public static Consumer create(Message message) {

		return Consumer.build(message);
	}

	public static Consumer create(String to, String from, String message) {
		Message _message = new Message(from, to, message);
		return Consumer.build(_message);
	}

	public Response send() {

		try {
			byte paramBody[] = this.message.getDataEncode().getBytes(StandardCharsets.UTF_8);
			System.out.println("data lenth =" + paramBody.length);
			com.dominosoft.requests.model.Response<String> response = Connection
					.build(String.format(API_WA_URL, Twilio.accountSID)).https().post()
					.addHeader("Authorization", authSet())
					.addHeader("Content-Type", "application/x-www-form-urlencoded").addHeader("Accept", "*").make()
					.send(paramBody).responseString();

			if (response.isSuccess())
				return new Response(true, response.getExtra());
			else
				return new Response(false, response.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(false, e.getMessage());
		}

	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	private String authSet() {
		String auth = Twilio.accountSID + ":" + Twilio.authToken;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		System.out.println("Set AUTH=" + "Basic " + new String(encodedAuth));
		return "Basic " + new String(encodedAuth);
	}

}
