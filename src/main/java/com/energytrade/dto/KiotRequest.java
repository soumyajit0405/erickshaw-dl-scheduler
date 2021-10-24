package com.energytrade.dto;

public class KiotRequest {

	private String intent ;
	
	private PayloadDto payload;

	public String getIntent() {
		return intent;
	}

	public void setIntent(String intent) {
		this.intent = intent;
	}

	public PayloadDto getPayload() {
		return payload;
	}

	public void setPayload(PayloadDto payload) {
		this.payload = payload;
	}
	
}
