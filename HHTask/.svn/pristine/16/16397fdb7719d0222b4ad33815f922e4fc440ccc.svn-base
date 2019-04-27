package com.task.entity.dangan;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 存档案表
 * @author Li_Cong 2016-01-19
 * 表名 ta_da_DangAn
 * 申请档案=>生成进出验证码=>记录进出时间
 */
public class DangAn implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String daName;//档案名称
	private String daNum;//档案编号
	@FieldMeta(name = "申请人名称")
	private String sqName;//申请人名称
	@FieldMeta(name = "申请人部门")
	private String sqDept;//申请人部门
	private String sqCardId;//申请人卡号
	@FieldMeta(name = "申请人工号")
	private String sqCode;//申请人工号
	private Integer squserId;//申请人ID
	@FieldMeta(name = "申请人手机号")
	private String sqTel;//申请人手机号
	private String cdAceName;//存档室名称
	@FieldMeta(name = "存档室编号")
	private String cdAceNum;//存档室编号
	private Integer cdAceId;//存档室ID
	private Integer epId;// 审批流程ID
	@FieldMeta(name = "审核状态")
	private String status;//审核状态
	@FieldMeta(name = "验证码状态")
	private String useStatus;// 验证码使用状态(未使用/使用中/已失效)
	@FieldMeta(name = "验证码")
	private String yanzheng;// 验证码
	private String addTime;//添加时间
	private String updateTime;//修改时间
	private String shenqingdate;//存档日期
	private String shixiaodate;//失效日期
	private String shTime;//审核时间
	@FieldMeta(name = "进门时间")
	private String inTime;//进门时间
	@FieldMeta(name = "出门时间")
	private String outTime;//出门时间
	@FieldMeta(name = "使用时长")
	private String useDate;//使用时长
	private String useDateNum;//使用时长毫秒
	private String daFiles;//档案文件
	private String daAddress;//档案存档位置
	@FieldMeta(name = "取档缘由")
	private String quDangReason;//取档原因
	private Set<ArchiveUnarchiverAplt> aplts;//存取档申请表
	private String qutype;//类型
	private Float money;//开票金额
	private Integer num;//发票数量
	
//	get set 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	public String getQutype() {
		return qutype;
	}
	public void setQutype(String qutype) {
		this.qutype = qutype;
	}
	public String getQuDangReason() {
		return quDangReason;
	}
	public void setQuDangReason(String quDangReason) {
		this.quDangReason = quDangReason;
	}
	public String getDaName() {
		return daName;
	}
	public void setDaName(String daName) {
		this.daName = daName;
	}
	public String getDaNum() {
		return daNum;
	}
	public void setDaNum(String daNum) {
		this.daNum = daNum;
	}
	public String getSqName() {
		return sqName;
	}
	public void setSqName(String sqName) {
		this.sqName = sqName;
	}
	public String getSqDept() {
		return sqDept;
	}
	public void setSqDept(String sqDept) {
		this.sqDept = sqDept;
	}
	public String getSqCardId() {
		return sqCardId;
	}
	public void setSqCardId(String sqCardId) {
		this.sqCardId = sqCardId;
	}
	public String getSqCode() {
		return sqCode;
	}
	public void setSqCode(String sqCode) {
		this.sqCode = sqCode;
	}
	public String getCdAceName() {
		return cdAceName;
	}
	public void setCdAceName(String cdAceName) {
		this.cdAceName = cdAceName;
	}
	public String getCdAceNum() {
		return cdAceNum;
	}
	public void setCdAceNum(String cdAceNum) {
		this.cdAceNum = cdAceNum;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getYanzheng() {
		return yanzheng;
	}
	public void setYanzheng(String yanzheng) {
		this.yanzheng = yanzheng;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getShTime() {
		return shTime;
	}
	public void setShTime(String shTime) {
		this.shTime = shTime;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getUseDateNum() {
		return useDateNum;
	}
	public void setUseDateNum(String useDateNum) {
		this.useDateNum = useDateNum;
	}
	public Integer getSquserId() {
		return squserId;
	}
	public void setSquserId(Integer squserId) {
		this.squserId = squserId;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getSqTel() {
		return sqTel;
	}
	public void setSqTel(String sqTel) {
		this.sqTel = sqTel;
	}
	public Integer getCdAceId() {
		return cdAceId;
	}
	public void setCdAceId(Integer cdAceId) {
		this.cdAceId = cdAceId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDaFiles() {
		return daFiles;
	}
	public void setDaFiles(String daFiles) {
		this.daFiles = daFiles;
	}
	public String getDaAddress() {
		return daAddress;
	}
	public void setDaAddress(String daAddress) {
		this.daAddress = daAddress;
	}
	public Set<ArchiveUnarchiverAplt> getAplts() {
		return aplts;
	}
	public void setAplts(Set<ArchiveUnarchiverAplt> aplts) {
		this.aplts = aplts;
	}
	public String getShenqingdate() {
		return shenqingdate;
	}
	public void setShenqingdate(String shenqingdate) {
		this.shenqingdate = shenqingdate;
	}
	public String getShixiaodate() {
		return shixiaodate;
	}
	public void setShixiaodate(String shixiaodate) {
		this.shixiaodate = shixiaodate;
	}
	
}
