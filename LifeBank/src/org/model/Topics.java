package org.model;

import java.util.Set;

/**
 * Topics entity. @author MyEclipse Persistence Tools
 */

public class Topics implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String title;
	private String content;
	private Integer clock;
	private String type;
	private Set<Comments> comments;
	// Constructors


	/** default constructor */
	public Topics() {
	}

	/** full constructor */
	public Topics(Long userid, String title, String content, Integer clock,
			String type,Set<Comments> comments) {
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.clock = clock;
		this.type = type;
		this.comments = comments;
	}
	/** min constructor */
	public Topics(Long userid, String title, String content, Integer clock,
			String type){
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.clock = clock;
		this.type = type;
	}

	// Property accessors

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


}