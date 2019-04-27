package com.task.entity.bbs;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.task.entity.Users;

public class BoardReview implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Integer createUserId;
	private Date createDate;
	private Integer boardId;
	private Integer parentId;

	private Users createUser;
	private List<BoardReview> subBoardReviewList = null;

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

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Users getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Users createUser) {
		this.createUser = createUser;
	}

	public Integer getBoardId() {
		return boardId;
	}

	public void setBoardId(Integer boardId) {
		this.boardId = boardId;
	}

	public List<BoardReview> getSubBoardReviewList() {
		return subBoardReviewList;
	}

	public void setSubBoardReviewList(List<BoardReview> subBoardReviewList) {
		this.subBoardReviewList = subBoardReviewList;
	}

}
