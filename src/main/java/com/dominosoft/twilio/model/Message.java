package com.dominosoft.twilio.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.dominosoft.twilio.util.PhoneNumber;

public class Message {

	private String fromNumber;
	private String toNumber;
	private String message;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String fromNumber, String toNumber, String message) {
		this.fromNumber = PhoneNumber.formatNumber(fromNumber);
		this.toNumber = PhoneNumber.formatNumber(toNumber);
		this.message = message;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public String getFromNumberEncode() throws UnsupportedEncodingException {
		return URLEncoder.encode(fromNumber, "UTF-8");
	}

	public void setFromNumber(String fromNumber) {

		this.fromNumber = PhoneNumber.formatNumber(fromNumber);
	}

	public String getToNumber() {
		return toNumber;
	}

	public String getToNumberEncode() throws UnsupportedEncodingException {
		return URLEncoder.encode(toNumber, "UTF-8");
	}

	public void setToNumber(String toNumber) {
		this.toNumber = PhoneNumber.formatNumber(toNumber);
	}

	public String getMessage() {
		return message;
	}

	public String getMessageEncoded() throws UnsupportedEncodingException {
		return URLEncoder.encode(message, "UTF-8");
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDataEncode() throws UnsupportedEncodingException {

		final String toParam = URLEncoder.encode("To", "UTF-8");
		final String formParam = URLEncoder.encode("From", "UTF-8");
		final String bodyParam = URLEncoder.encode("Body", "UTF-8");

		String encode = "%s=%s&%s=%s&%s=%s";
		encode = String.format(encode, toParam, getToNumberEncode(), formParam, getFromNumberEncode(), bodyParam,
				getMessageEncoded());
		return encode;
	}

	@Override
	public String toString() {
		return String.format("Message {\nfromNumber=%s,\n toNumber=%s,\n message=%s\n}", getFromNumber(), getToNumber(),
				getMessage());
	}

}
