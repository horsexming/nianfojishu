package com.task.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.UserFacialInforServer;
import com.task.entity.UserFacialFeatures;
import com.task.entity.UserFacialInfor;
import com.task.util.MKUtil;

/**
 * 面部特征用户对接信息
 * @author licong
 *
 */
@SuppressWarnings("unchecked")
public class UserFacialInforAction{
	private Integer id;// id
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
	private String userFeatures;//用户面部特征
	private String total;
	private String url;
	private int pageSize = 15;
	private Map<String, Object> mapAll = null;//待下发信息
	private String tag;//标识
	
	public void text(){
		facialInforServer.addUser();
	}
	
	public String addHsUser(){
		facialInforServer.addHSUser();
		return "error";
	}
	
	
	//添加面部特征接口
	public void A(){
		errorMessage = facialInforServer.addUserFacialFeatures(facialFeatures);
		if("true".equals(errorMessage)){
			MKUtil.writeJSONS(true, "添加成功!", null);
		}else {
			MKUtil.writeJSONS(false, errorMessage, null);
		}
	}
	//面部检测成功接口
	public void R(){
		facialFeatures = facialInforServer.addAttendanceInfor(userFeatures,equipmentId);
		if(facialFeatures!=null){
			facialFeatures.setUserFeatures(null);
			MKUtil.writeJSONS(true, "识别成功："+facialFeatures.getUserName(), facialFeatures);
		}else {
			MKUtil.writeJSONS(false, "没有最佳匹配结果", null);
		}
	}
	//获得该设备所有待下发信息接口
	public void S(){
		mapAll = facialInforServer.pindList(equipmentId,type);
		if(mapAll!=null){
			MKUtil.writeJSON(mapAll);
		}else {
			MKUtil.writeJSONS(false, "设备标识为空，获取失败!", null);
		}
	}
	
	// 分页查询内容
	public String showListInfor() {
		if (facialInfor != null)
			ActionContext.getContext().getSession().put("UserFacialInfor", facialInfor);
		else
			facialInfor = (UserFacialInfor) ActionContext.getContext().getSession().get("UserFacialInfor");
		Object[] object = facialInforServer.findUserFacialInfor(facialInfor, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			facialInforList = (List<UserFacialInfor>) object[0];
			if (facialInforList != null && facialInforList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("US!showListInfor.action");
				errorMessage = null;
			}else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "facialInfor_show";
	}
	
	//删除对接信息
	public String deleteInfor(){
		if (id != null && id > 0) {
			errorMessage = facialInforServer.deleteUserFacialInfor(id);
			if ("删除成功！".equals(errorMessage))
				url = "US!showListInfor.action?cpage="+cpage;
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}
	
	// 分页查询内容
	public String showListFacial() {//
		if (facialFeatures != null)
			ActionContext.getContext().getSession().put("UserFacialFeatures", facialFeatures);
		else
			facialFeatures = (UserFacialFeatures) ActionContext.getContext().getSession().get("UserFacialFeatures");
		Object[] object = facialInforServer.findUserFacialFeatures(facialFeatures, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			facialFeaturesList = (List<UserFacialFeatures>) object[0];
			if (facialFeaturesList != null && facialFeaturesList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("US!showListFacial.action");
				errorMessage = null;
			}else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "facialFeatures_show";
	}

	//删除面部特征
	public String deleteFacial(){
		if (id != null && id > 0) {
			errorMessage = facialInforServer.deleteUserFacialFeatures(id);
			if ("删除成功！".equals(errorMessage))
				url = "US!showListFacial.action?cpage="+cpage;
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
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

	public String getUserFeatures() {
		return userFeatures;
	}

	public void setUserFeatures(String userFeatures) {
		this.userFeatures = userFeatures;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
