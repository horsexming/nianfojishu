package com.task.entity;

import java.util.Date;
import java.util.List;

import com.task.entity.bbs.BoardReview;

/**
 * Board的显示类
 * @author 马凯
 *
 */
public class Vboard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String content;
	private Integer scrnId;
	private String scrnName;
	private Integer createUserId;
	private Integer parentId;
	private Date createDate;
	
	private List<Vboard> boardList;
	private List<BoardReview> boardReviewList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BoardReview> getBoardReviewList() {
		return boardReviewList;
	}

	public void setBoardReviewList(List<BoardReview> boardReviewList) {
		this.boardReviewList = boardReviewList;
	}

	public Integer getScrnId() {
		return scrnId;
	}

	public void setScrnId(Integer scrnId) {
		this.scrnId = scrnId;
	}

	public String getScrnName() {
		return scrnName;
	}

	public void setScrnName(String scrnName) {
		this.scrnName = scrnName;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Vboard> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Vboard> boardList) {
		this.boardList = boardList;
	}
	
}
