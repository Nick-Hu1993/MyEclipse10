package org.model;

/**
 * UserCell entity. @author MyEclipse Persistence Tools
 */

public class UserCell implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long cellid;

	// Constructors

	/** default constructor */
	public UserCell() {
	}

	/** full constructor */
	public UserCell(Long userid, Long cellid) {
		this.userid = userid;
		this.cellid = cellid;
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

	public Long getCellid() {
		return this.cellid;
	}

	public void setCellid(Long cellid) {
		this.cellid = cellid;
	}

}