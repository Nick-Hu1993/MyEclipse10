package org.domain;

public class Product {
	private String username;
	private String password;

	public Product(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getP() {
		return password;
	}

	public void setP(String password) {
		this.password = password;
	}

}
