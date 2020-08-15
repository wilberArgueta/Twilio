package com.dominosoft.twilio;

import java.io.IOException;

import com.dominosoft.twilio.model.Message;
import com.dominosoft.twilio.model.Response;

public class TestConsumer {

	public static void main(String[] args) throws IOException {
		final String ACCOUNT_SID = "ACc27240856adfc30b22aba8f00d195fdc";
		final String AUTH_TOKEN = "7b6347f2e5dbb9a8555fb6e504a3ca90";

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Response response = Consumer.build(new Message("14155238886", "50379132465", "Prueba")).send();
		System.out.println(response);

	}
}
