package com.task.action;

import java.util.List;
import java.util.Map;

import com.task.Server.UserFacialInforServer;
import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.util.MKUtil;

/**
 * 用户对接信息接口
 * @author licong
 *
 */
public class UserFacialFeaturesAction{
	private int id;// id
	private UserFacialInfor facialInfor;//用户对接信息
	private UserFacialFeatures facialFeatures;//用户面部特征信息
	private List<UserFacialInfor> facialInforList;
	private List<UserFacialFeatures> facialFeaturesList;
	private UserFacialInforServer facialInforServer;
	private String errorMessage;// 错误信息
	private String successMessage;// 成功信息

	private String test;// 标识

	// 分页
	private String cpage = "1";
	private String type;//接受信息，s代表查询，d代表删除
	private String userNo;//工号
	private String openDoorType;//打卡类型（0：面部识别，1：密码）
	private String equipmentId;//设备唯一标识
	private String total;
	private String url;
	private int pageSize = 15;
	private Map<String, Object> mapAll = null;//待下发信息
	private String tag;//标识
	
	public void K(){
		MKUtil.writeJSONS(true, "8c160649-4333-44db-bfa9-6fdf8992eccf", null);
	}
	
//	//添加面部特征接口
//	public void A(){
//		errorMessage = facialInforServer.addUserFacialFeatures(facialFeatures);
//		if("true".equals(errorMessage)){
//			MKUtil.writeJSONS(true, "添加成功!", null);
//		}else {
//			MKUtil.writeJSONS(false, errorMessage, null);
//		}
//	}
//	//面部检测成功接口
//	public void R(){
//		errorMessage = facialInforServer.addAttendanceInfor(userNo,openDoorType,equipmentId);
//		if("true".equals(errorMessage)){
//			MKUtil.writeJSONS(true, "签到成功!", null);
//		}else {
//			MKUtil.writeJSONS(false, errorMessage, null);
//		}
//	}
	//获得该设备所有待下发信息接口
	public void S(){
		if(equipmentId==null||"".equals(equipmentId)){
			MKUtil.writeJSONS(false, "获取失败", null);
		}else {
			MKUtil.writeJSON(facialInforServer.pindList(equipmentId, type));
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public UserFacialInfor getFacialInfor() {
		return facialInfor;
	}
	public void setFacialInfor(UserFacialInfor facialInfor) {
		this.facialInfor = facialInfor;
	}
	public UserFacialFeatures getFacialFeatures() {
		return facialFeatures;
	}
	public void setFacialFeatures(UserFacialFeatures facialFeatures) {
		this.facialFeatures = facialFeatures;
	}
	public List<UserFacialInfor> getFacialInforList() {
		return facialInforList;
	}
	public void setFacialInforList(List<UserFacialInfor> facialInforList) {
		this.facialInforList = facialInforList;
	}
	public List<UserFacialFeatures> getFacialFeaturesList() {
		return facialFeaturesList;
	}
	public void setFacialFeaturesList(List<UserFacialFeatures> facialFeaturesList) {
		this.facialFeaturesList = facialFeaturesList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UserFacialInforServer getFacialInforServer() {
		return facialInforServer;
	}
	public void setFacialInforServer(UserFacialInforServer facialInforServer) {
		this.facialInforServer = facialInforServer;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getOpenDoorType() {
		return openDoorType;
	}
	public void setOpenDoorType(String openDoorType) {
		this.openDoorType = openDoorType;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Map<String, Object> getMapAll() {
		return mapAll;
	}
	public void setMapAll(Map<String, Object> mapAll) {
		this.mapAll = mapAll;
	}

}
