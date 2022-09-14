package ch.hcuge.simed.gbserver.model.api;

import java.io.Serializable;

public class FakeToken implements Serializable {
	private String access_token = "bidon";	
	private String token_type = "Bearer";
	private int expires_in = 3000;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

}