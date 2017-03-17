package org.model;

/**
 * Replys entity. @author MyEclipse Persistence Tools
 */

public class Replys implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String content;
	private Integer clock;
//	private Long commentId;
	private Comments comment;
	// Constructors

	/** default constructor */
	public Replys() {
	}

	/** full constructor */
	public Replys(Long userid, String content, Integer clock, Comments comment) {
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.comment = comment;
	}

	// Property accessors
	
	public Comments getComment() {
		return comment;
	}

	public void setComment(Comments comment) {
		this.comment = comment;
	}

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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}


//	public Long getCommentId() {
//		return this.commentId;
//	}
//
//	public void setCommentsId(Long commentId) {
//		this.commentId = commentId;
//	}

}