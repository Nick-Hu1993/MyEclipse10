package org.model;

import java.util.Set;

/**
 * 话题对象
 */

public class Comments implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String content;
	private Integer clock;
	private Topics topic;
	private Set<Replys> replys;
	// private Long topicid;
	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** full constructor */
	public Comments(Long userid, String content, Integer clock, Topics topic,
			Set<Replys> replys) {
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.topic = topic;
		this.replys = replys;
	}
	/** min constructor */
	public Comments(Long userid, String content, Integer clock, Topics topic) {
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.topic = topic;
	}

	// Property accessors
	public Set<Replys> getReplys() {
		return replys;
	}

	public void setReplys(Set<Replys> replys) {
		this.replys = replys;
	}

	public Topics getTopic() {
		return topic;
	}

	public void setTopic(Topics topic) {
		this.topic = topic;
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


	// public Long getTopicId() {
	// return this.topicId;
	// }
	//
	// public void setTopicId(Long topicId) {
	// this.topicId = topicId;
	// }

}