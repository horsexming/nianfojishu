package com.task.entity.sop.muju;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.project.ProjectWenJian;
import com.task.util.FieldMeta;

/**
 * 
 * @author 王晓飞
 *	模具申请单	(ta_sop_muju_MouldApplyOrder)
 */
public class MouldApplyOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@FieldMeta(name="申请单号")
	private String planNumber;//申请单号
	@FieldMeta(name="模具号")
	private String mojuNo;//模具号
	@FieldMeta(name="客户")
	private String kehu;//客户
	@FieldMeta(name="业务件号")
	private String ywMarkId;//业务件号
	@FieldMeta(name="申请类型")
	private String applytype;//申请类型(开新模，改旧模)
	private String maketype;//制作方式(自制,外发)
	private String status ;//
	private String epstatus;//审批状态
	private Integer epId;
	@FieldMeta(name="申请时间")
	private String addtime;//申请时间
	private String adddate;//申请日期
	@FieldMeta(name="申请人")
	private String addUserName;//申请人
	private String shUserName;//审核人
	private String agreeUserName;//批准人
	@FieldMeta(name="订单数量")
	private Float orderNum;//订单数量
	@FieldMeta(name="产品交期")
	private String projiaoqiTime;//产品交期
	@FieldMeta(name="需求日期")
	private String xqTime;//需求日期;
	private String ishaveyangban;//有无样本(有，无)
	private String protuzi;//产品图(有，无)
	private String zaikaitu;//展开图（有，无）；
	private String tu3d;//3d图(有，无)
	private Integer num;//套数
	private Set<ProjectWenJian> projectWenJianSet;
	private String mujuyanshou;
	
	private String more;///备注
	private Set<MouldPingGu> mpgSet;//一对多 模具评估
	private Set<MouldDetail> mdSet;//一对多 开模明细
	private List<MouldDetail> mdList;
	private List<MouldPingGu> mpgList;
	private Integer userId;//页面传值 
	private Integer gysId;//供应商Id
	private Integer priceId;//价格Id
	private Float hsprice;//含税价
	private Float bhsprice;//不含税价
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	public String getKehu() {
		return kehu;
	}
	public void setKehu(String kehu) {
		this.kehu = kehu;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public String getApplytype() {
		return applytype;
	}
	public void setApplytype(String applytype) {
		this.applytype = applytype;
	}
	public String getMaketype() {
		return maketype;
	}
	public void setMaketype(String maketype) {
		this.maketype = maketype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getShUserName() {
		return shUserName;
	}
	public void setShUserName(String shUserName) {
		this.shUserName = shUserName;
	}
	public String getAgreeUserName() {
		return agreeUserName;
	}
	public void setAgreeUserName(String agreeUserName) {
		this.agreeUserName = agreeUserName;
	}
	public Float getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Float orderNum) {
		this.orderNum = orderNum;
	}
	public String getProjiaoqiTime() {
		return projiaoqiTime;
	}
	public void setProjiaoqiTime(String projiaoqiTime) {
		this.projiaoqiTime = projiaoqiTime;
	}
	public String getXqTime() {
		return xqTime;
	}
	public void setXqTime(String xqTime) {
		this.xqTime = xqTime;
	}
	public Set<MouldPingGu> getMpgSet() {
		return mpgSet;
	}
	public void setMpgSet(Set<MouldPingGu> mpgSet) {
		this.mpgSet = mpgSet;
	}
	@JSONField(serialize = false)
	public Set<MouldDetail> getMdSet() {
		return mdSet;
	}
	public void setMdSet(Set<MouldDetail> mdSet) {
		this.mdSet = mdSet;
	}
	public List<MouldDetail> getMdList() {
		return mdList;
	}
	public void setMdList(List<MouldDetail> mdList) {
		this.mdList = mdList;
	}
	@JSONField(serialize = false)
	public List<MouldPingGu> getMpgList() {
		return mpgList;
	}
	public void setMpgList(List<MouldPingGu> mpgList) {
		this.mpgList = mpgList;
	}
	public String getEpstatus() {
		return epstatus;
	}
	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getAdddate() {
		return adddate;
	}
	public void setAdddate(String adddate) {
		this.adddate = adddate;
	}
	public String getIshaveyangban() {
		return ishaveyangban;
	}
	public void setIshaveyangban(String ishaveyangban) {
		this.ishaveyangban = ishaveyangban;
	}
	public String getProtuzi() {
		return protuzi;
	}
	public void setProtuzi(String protuzi) {
		this.protuzi = protuzi;
	}
	public String getZaikaitu() {
		return zaikaitu;
	}
	public void setZaikaitu(String zaikaitu) {
		this.zaikaitu = zaikaitu;
	}
	public String getTu3d() {
		return tu3d;
	}
	public void setTu3d(String tu3d) {
		this.tu3d = tu3d;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@JSONField(serialize = false)
	public Set<ProjectWenJian> getProjectWenJianSet() {
		return projectWenJianSet;
	}
	public void setProjectWenJianSet(Set<ProjectWenJian> projectWenJianSet) {
		this.projectWenJianSet = projectWenJianSet;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getGysId() {
		return gysId;
	}
	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Float getHsprice() {
		return hsprice;
	}
	public void setHsprice(Float hsprice) {
		this.hsprice = hsprice;
	}
	public Float getBhsprice() {
		return bhsprice;
	}
	public void setBhsprice(Float bhsprice) {
		this.bhsprice = bhsprice;
	}
	public String getMujuyanshou() {
		return mujuyanshou;
	}
	public void setMujuyanshou(String mujuyanshou) {
		this.mujuyanshou = mujuyanshou;
	}
	public String getMojuNo() {
		return mojuNo;
	}
	public void setMojuNo(String mojuNo) {
		this.mojuNo = mojuNo;
	}
	
 
	  
	
	
}
