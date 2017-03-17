package org.imodel;

import java.util.List;
import java.util.Set;

/**
 * 话题对象
 */

public class IComments implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String content;
	private Integer clock;
	// private ITopics topic;
	private Long topicid;
	private List<IReplys> replys;
	private String username;
	private List<String> urlList;
	private String headUrl;

	// private Long topicid;
	// Constructors

	/** default constructor */
	public IComments() {
	}

	/** full constructor 用于传递数据 */
	public IComments(Long id, Long userid, String content, Integer clock,
			Long topicid, List<IReplys> replys, String username,
			List<String> urlList, String headUrl) {
		super();
		this.id = id;
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.topicid = topicid;
		this.replys = replys;
		this.username = username;
		this.urlList = urlList;
		this.headUrl = headUrl;
	}

	/** min constructor 用于插入新评论 */
	public IComments(Long userid, String content, Integer clock, Long topicid,
			String username) {
		super();
		this.userid = userid;
		this.content = content;
		this.clock = clock;
		this.topicid = topicid;
		this.username = username;
	}

	// Property accessors
	public List<IReplys> getReplys() {
		return replys;
	}

	public void setReplys(List<IReplys> replys) {
		this.replys = replys;
	}

	public Long getTopicid() {
		return topicid;
	}

	public void setTopicid(Long topicid) {
		this.topicid = topicid;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	// public Long getTopicId() {
	// return this.topicId;
	// }
	//
	// public void setTopicId(Long topicId) {
	// this.topicId = topicId;
	// }

}