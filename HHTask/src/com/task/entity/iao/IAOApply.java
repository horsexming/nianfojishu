package com.task.entity.iao;
/**
 * 贾辉辉
 * 出入申请表（ta_iao_apply）
 * ***/
import java.io.Serializable;
import java.util.Set;

public class IAOApply implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;	
	private String code;        //申请人工号
	private String username;    //出门人
	private String outCard; //出门人卡号
	private String applyName;  //申请提交人
	private String iaoStyle; //出门类型（员工请假/事假/发货/外委/设备维修/来访/送货）
	private String result;  //原因描述
	private String carryGoods; //携带物品名称
	private String applyOutTime; //申请出门时间
	private String getOutTime;   //实际出门时间
	private String applyInTime; //预计返回时间
	private String getInTime;   //实际返回时间
	private String submitTime; //提交时间
	private String status;    //状态（申请，审批，同意,未返回，已返回）
	private String barcode; //条码或出入人员工卡号
	private String iaoStatus; //出入状态（初始，出门，进门）
	private String prePath ;   //预申批流程
	private String relPath ;   //审批流程
	private String examPoint; //审批节点
	private String plateNum; //车牌号
	private String ascTime; //排序时间
	private String printStatus; //打印状态	
	private String followPerson;//随从人员姓名
	private Set<CarryGoods> iaoCarryGoods;
	private String iaoPersonTyle;//本人/他人（内部）/他人（外部）
	private String dept;// 部门
	private String iaoPingzheng;//出门凭证（card/barcode）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOutCard() {
		return outCard;
	}
	public void setOutCard(String outCard) {
		this.outCard = outCard;
	}
	
	public String getApplyName() {
		return applyName;
	}
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	
	public String getIaoStyle() {
		return iaoStyle;
	}
	public void setIaoStyle(String iaoStyle) {
		this.iaoStyle = iaoStyle;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCarryGoods() {
		return carryGoods;
	}
	public void setCarryGoods(String carryGoods) {
		this.carryGoods = carryGoods;
	}
	public String getApplyOutTime() {
		return applyOutTime;
	}
	public void setApplyOutTime(String applyOutTime) {
		this.applyOutTime = applyOutTime;
	}
	public String getGetOutTime() {
		return getOutTime;
	}
	public void setGetOutTime(String getOutTime) {
		this.getOutTime = getOutTime;
	}
	public String getApplyInTime() {
		return applyInTime;
	}
	public void setApplyInTime(String applyInTime) {
		this.applyInTime = applyInTime;
	}
	public String getGetInTime() {
		return getInTime;
	}
	public void setGetInTime(String getInTime) {
		this.getInTime = getInTime;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIaoStatus() {
		return iaoStatus;
	}
	public void setIaoStatus(String iaoStatus) {
		this.iaoStatus = iaoStatus;
	}
	public String getPrePath() {
		return prePath;
	}
	public void setPrePath(String prePath) {
		this.prePath = prePath;
	}
	public String getRelPath() {
		return relPath;
	}
	public void setRelPath(String relPath) {
		this.relPath = relPath;
	}
	public String getExamPoint() {
		return examPoint;
	}
	public void setExamPoint(String examPoint) {
		this.examPoint = examPoint;
	}
	public String getPlateNum() {
		return plateNum;
	}
	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}
	public String getAscTime() {
		return ascTime;
	}
	public void setAscTime(String ascTime) {
		this.ascTime = ascTime;
	}
	public String getPrintStatus() {
		return printStatus;
	}
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	public Set<CarryGoods> getIaoCarryGoods() {
		return iaoCarryGoods;
	}
	public void setIaoCarryGoods(Set<CarryGoods> iaoCarryGoods) {
		this.iaoCarryGoods = iaoCarryGoods;
	}
	public String getFollowPerson() {
		return followPerson;
	}
	public void setFollowPerson(String followPerson) {
		this.followPerson = followPerson;
	}
	public String getIaoPersonTyle() {
		return iaoPersonTyle;
	}
	public void setIaoPersonTyle(String iaoPersonTyle) {
		this.iaoPersonTyle = iaoPersonTyle;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getIaoPingzheng() {
		return iaoPingzheng;
	}
	public void setIaoPingzheng(String iaoPingzheng) {
		this.iaoPingzheng = iaoPingzheng;
	}
}

