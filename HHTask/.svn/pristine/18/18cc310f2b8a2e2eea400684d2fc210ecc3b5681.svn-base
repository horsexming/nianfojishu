package com.task.entity.weixin;
import java.io.Serializable;

/**
 * Article entity. @author MyEclipse Persistence Tools
 */

public class Article implements Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private SendMass sendMass;
	private String mediaid;
	private String author;
	private String title;
	private String url;
	private String content;
	private String digest;

	// Constructors

	/** default constructor */
	public Article() {
	}

	/** minimal constructor */
	public Article(SendMass sendMass, String title, String content) {
		this.sendMass = sendMass;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Article(SendMass sendMass, String mediaid, String author,
			String title, String url, String content, String digest) {
		this.sendMass = sendMass;
		this.mediaid = mediaid;
		this.author = author;
		this.title = title;
		this.url = url;
		this.content = content;
		this.digest = digest;
	}

	// Property accessors

	

	public SendMass getSendMass() {
		return this.sendMass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSendMass(SendMass sendMass) {
		this.sendMass = sendMass;
	}

	public String getMediaid() {
		return this.mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDigest() {
		return this.digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

}