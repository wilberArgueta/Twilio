package com.dominosoft.twilio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;


import com.dominosoft.twilio.model.Message;
import com.dominosoft.twilio.model.Response;

public class Consumer {

	private URL urlConnection;
	private HttpsURLConnection connection;
	private Message message;
	private final String API_WA_URL = "https://api.twilio.com/2010-04-01/Accounts/%s/Messages.json";
	private final String POST = "POST";
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

	public Response send() throws IOException {
		try {
			System.out.println("Set Data: " + this.message.getDataEncode());
			byte paramBody[] = this.message.getDataEncode().getBytes(StandardCharsets.UTF_8);
			this.urlConnection = getUrl();

			this.connection = (HttpsURLConnection) this.urlConnection.openConnection();
			this.connection.setRequestMethod(POST);
			this.connection.setRequestProperty("Authorization", authSet());
			this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			this.connection.setRequestProperty("Accept", "*");
			this.connection.setFixedLengthStreamingMode(paramBody.length);
			this.connection.setDoOutput(true);
			this.connection.setDoInput(true);

			// Enviar la informacion en form-urlencoded
			DataOutputStream writer = new DataOutputStream(this.connection.getOutputStream());
			writer.write(paramBody);
			writer.flush();
			writer.close();

			// Leer la respuesta del servidor
			BufferedReader in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			return new Response(true, content.toString());

		} catch (Exception e) {
			e.printStackTrace();
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.connection.getErrorStream()));
			StringBuilder content = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
			return new Response(false, content.toString());

		}
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	private URL getUrl() throws MalformedURLException {

		return new URL(String.format(API_WA_URL, Twilio.accountSID));
	}

	private String authSet() {
		String auth = Twilio.accountSID + ":" + Twilio.authToken;
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
		System.out.println("Set AUTH=" + "Basic " + new String(encodedAuth));
		return "Basic " + new String(encodedAuth);
	}

}
