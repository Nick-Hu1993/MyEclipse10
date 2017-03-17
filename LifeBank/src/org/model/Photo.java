package org.model;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Integer type;
	private Long foreignId;
	private String url;

	// Constructors

	/** default constructor */
	public Photo() {
	}

	/** minimal constructor */
	public Photo(Long userid, Integer type, String url) {
		this.userid = userid;
		this.type = type;
		this.url = url;
	}

	/** full constructor */
	public Photo(Long userid, Integer type, Long foreignId, String url) {
		this.userid = userid;
		this.type = type;
		this.foreignId = foreignId;
		this.url = url;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(Long foreignId) {
		this.foreignId = foreignId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}