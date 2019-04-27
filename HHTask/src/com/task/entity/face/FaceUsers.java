package com.task.entity.face;

import java.io.Serializable;

/**
 * 人脸用户表
 * @author wcy
 *	ta_hr_faceusers
 */
public class FaceUsers implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;//用户ID
	private String code; //用户编码
	private String cardId;//用户卡号
	private String userName;//用户姓名
	private String dept;//部门
	
	private String picturePath; //存放图片路径
	private String groupName; 	//人脸所在组名Inner、visitor、alarm
	private String faceCode;	//人脸用户编码
	private String faceInfo;	//人脸用户信息
	private String face_token;  //人脸
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getFaceCode() {
		return faceCode;
	}
	public void setFaceCode(String faceCode) {
		this.faceCode = faceCode;
	}
	public String getFaceInfo() {
		return faceInfo;
	}
	public void setFaceInfo(String faceInfo) {
		this.faceInfo = faceInfo;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getFace_token() {
		return face_token;
	}
	public void setFace_token(String face_token) {
		this.face_token = face_token;
	}
	
	
	
}
