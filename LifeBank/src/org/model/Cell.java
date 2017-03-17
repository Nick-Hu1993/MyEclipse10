package org.model;

/**
 * Cell entity. @author MyEclipse Persistence Tools
 */

public class Cell implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer num;
	private String serial;
	private Integer clock;

	// Constructors

	/** default constructor */
	public Cell() {
	}

	/** full constructor */
	public Cell(String name, Integer num, String serial, Integer clock) {
		this.name = name;
		this.num = num;
		this.serial = serial;
		this.clock = clock;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

}