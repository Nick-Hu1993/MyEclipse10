package org.imodel;

import org.model.Comments;

/**
 * Replys entity. @author MyEclipse Persistence Tools
 */

public class IReplys implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String content;
	private Integer clock;
	// private Long commentId;
	private Long commentid;
	private String username;

	// Constructors

	/** default constructor */
	public IReplys() {
	}

	/** full constructor 用于显示回复 */
	public IReplys(Long id, Long userid, String content, Integer clock,
			Long commentid, String username) {
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.commentid = commentid;
		this.username = username;
	}
	/** min  constructor 用于插入新的回复  */
	public IReplys(Long userid, String content, Integer clock, Long commentid,
			String username) {
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.commentid = commentid;
		this.username = username;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// public Long getCommentId() {
	// return this.commentId;
	// }
	//
	// public void setCommentsId(Long commentId) {
	// this.commentId = commentId;
	// }

}