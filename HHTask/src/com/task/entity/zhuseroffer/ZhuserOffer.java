package com.task.entity.zhuseroffer;

import java.io.File;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.util.FieldMeta;
import com.tast.entity.zhaobiao.ZhUser;

/**
 * 供应商报价表（表 ta_ZhuserOffer）
 * 
 * @author tx
 * 
 */
public class ZhuserOffer implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ZhUser zhUser;
	private YuanclAndWaigj yuanclAndWaigj;
	private String status;//（未报价、已报价、确认、删除）
	//用于前台显示
	private String markId;//件号
	private String name;//名称
	private String specification;//规格
	private String banbenhao;//版本
	private String wgType;//物料类别
	private Integer zhUserId;//供应商Id
	private String cmp;//供应商名称
	private String usercode;//编号(工号)
	private String cperson;//联系人
	private String ctel;//手机
	private String kgliao;//供料属性
	private String kbstatus;//
	private String banci;//版次
	private String processNO;//工序号
	private String processName;//工序名称
	private Integer processId;//
	//单价
	private Double hsPrice;// 含税价格
	private Double bhsPrice;// 不含税价格
	private Double taxprice; //税率
	private String joinDate;//添加时间
	private String cycle;// 周期
	private String endDate;//截止时间
	private Set<Sample> sampleSet;  
	private Integer EpId;
	private Integer sumProcessId;
	//图纸 
	@FieldMeta(name="图纸 ")
	private String baojia;
	private File baojiaF;
	private String  baojiaFContentType;
	private String baojiaFFileName;
 	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize = false)
	public ZhUser getZhUser() {
		return zhUser;
	}
	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JSONField(serialize = false)
	public YuanclAndWaigj getYuanclAndWaigj() {
		return yuanclAndWaigj;
	}
	public void setYuanclAndWaigj(YuanclAndWaigj yuanclAndWaigj) {
		this.yuanclAndWaigj = yuanclAndWaigj;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getBanbenhao() {
		return banbenhao;
	}
	public void setBanbenhao(String banbenhao) {
		this.banbenhao = banbenhao;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public Integer getZhUserId() {
		return zhUserId;
	}
	public void setZhUserId(Integer zhUserId) {
		this.zhUserId = zhUserId;
	}
	public String getCmp() {
		return cmp;
	}
	public void setCmp(String cmp) {
		this.cmp = cmp;
	}
	public String getUsercode() {
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getCperson() {
		return cperson;
	}
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	public String getCtel() {
		return ctel;
	}
	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	public Double getHsPrice() {
		return hsPrice;
	}
	public void setHsPrice(Double hsPrice) {
		this.hsPrice = hsPrice;
	}
	public Double getBhsPrice() {
		return bhsPrice;
	}
	public void setBhsPrice(Double bhsPrice) {
		this.bhsPrice = bhsPrice;
	}
	public Double getTaxprice() {
		return taxprice;
	}
	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}
	@JSONField(serialize = false)
	public Set<Sample> getSampleSet() {
		return sampleSet;
	}
	public void setSampleSet(Set<Sample> sampleSet) {
		this.sampleSet = sampleSet;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getKbstatus() {
		return kbstatus;
	}
	public void setKbstatus(String kbstatus){
		this.kbstatus = kbstatus;
	}
	public String getBanci() {
		return banci;
	}
	public void setBanci(String banci) {
		this.banci = banci;
	}
	public String getProcessNO() {
		return processNO;
	}
	public void setProcessNO(String processNO) {
		this.processNO = processNO;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public Integer getEpId() {
		return EpId;
	}
	public void setEpId(Integer epId) {
		EpId = epId;
	}
	public Integer getSumProcessId() {
		return sumProcessId;
	}
	public void setSumProcessId(Integer sumProcessId) {
		this.sumProcessId = sumProcessId;
	}
	public String getBaojia() {
		return baojia;
	}
	public void setBaojia(String baojia) {
		this.baojia = baojia;
	}
	public File getBaojiaF() {
		return baojiaF;
	}
	public void setBaojiaF(File baojiaF) {
		this.baojiaF = baojiaF;
	}
	public String getBaojiaFContentType() {
		return baojiaFContentType;
	}
	public void setBaojiaFContentType(String baojiaFContentType) {
		this.baojiaFContentType = baojiaFContentType;
	}
	public String getBaojiaFFileName() {
		return baojiaFFileName;
	}
	public void setBaojiaFFileName(String baojiaFFileName) {
		this.baojiaFFileName = baojiaFFileName;
	}
	
	
}
