package com.dominosoft.twilio;

import java.io.IOException;

import com.dominosoft.twilio.model.Message;

public class TestConsumer {

	public static void main(String[] args) throws IOException {
		final String ACCOUNT_SID = "ACc27240856adfc30b22aba8f00d195fdc";
		final String AUTH_TOKEN = "48dc380cd7dba536f5f728334ce92f2b";

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Consumer.build(new Message("14155238886", "50379132465", "Prueba")).send();

	}
}
