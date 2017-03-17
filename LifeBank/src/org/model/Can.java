package org.model;

/**
 * Can entity. @author MyEclipse Persistence Tools
 */

public class Can implements java.io.Serializable {

	// Fields

	private Long id;
	private String serial;
	private String address;

	// Constructors

	/** default constructor */
	public Can() {
	}

	/** minimal constructor */
	public Can(String serial) {
		this.serial = serial;
	}

	/** full constructor */
	public Can(String serial, String address) {
		this.serial = serial;
		this.address = address;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}