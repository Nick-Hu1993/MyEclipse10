package org.imodel;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Topics entity. @author MyEclipse Persistence Tools
 */

public class ITopics implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String title;
	private String content;
	private Integer clock;
	private String type;
	private String username;
	private Integer commentNum;
	private List<String> urlList;
	private String headUrl;

	// Constructors

	/** default constructor */
	public ITopics() {
	}

	/** min constructor 用于插入新话题 */
	public ITopics(Long userid, String title, String content, Integer clock,
			String type, String username) {
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.clock = clock;
		this.type = type;
		this.username = username;
	}

	/** full constructor 用于传递话题信息 */
	public ITopics(Long id, Long userid, String title, String content,
			Integer clock, String type, String username, Integer commentNum,
			List<String> urlList, String headUrl) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.clock = clock;
		this.type = type;
		this.username = username;
		this.commentNum = commentNum;
		this.urlList = urlList;
		this.headUrl = headUrl;
	}

	// Property accessors
	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

}