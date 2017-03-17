package org.model;

/**
 * SuperUser entity. @author MyEclipse Persistence Tools
 */

public class SuperUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String username;
	private String password;
	private Integer available;
	private Integer priority;
	private Integer clock;

	// Constructors

	/** default constructor */
	public SuperUser() {
	}

	/** minimal constructor */
	public SuperUser(String username, String password, Integer clock) {
		this.username = username;
		this.password = password;
		this.clock = clock;
	}

	/** full constructor */
	public SuperUser(String username, String password, Integer available,
			Integer priority, Integer clock) {
		this.username = username;
		this.password = password;
		this.available = available;
		this.priority = priority;
		this.clock = clock;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

}