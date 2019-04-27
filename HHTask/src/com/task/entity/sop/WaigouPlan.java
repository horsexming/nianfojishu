package com.task.entity.sop;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.sop.muju.MouldDetail;

/****
 * 外购采购订单明细表
 * 
 * @author 刘培
 * @表名 ta_sop_w_WaigouPlan
 * 
 */
public class WaigouPlan  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	public void setWlWeizhiDt(String wlWeizhiDt) {
		this.wlWeizhiDt = wlWeizhiDt;
	}

	private String markId;// 件号
	private String ywmarkId;//业务件号
	private String kgliao;// //供料属性（自购、指定、客供）
	private String banben;// 版本
	private String proName;// 零件名称
	private Integer banci;// 版次
	private String specification;// 规格
	private String tuhao;//图号
	private String unit;// 单位 
	private String type;// 计划类型(外购/外委/模具/辅料)
	//------
	private Float number;// 数量
	private Float syNumber;// 剩余未送货数量
	//---------
	private Float hgNumber;// 合格数量
	private Double hsPrice;// 含税单价
	private Double price;// 不含税单价
	private Double taxprice; // 税率
	private Double money;// 总额
	private Integer priceId;// 价格id
	private Double yfMoney;//已付金额(含税)
	private String hasjk;//是否已借款
	/******** 供应商相关 *******/
	private Integer userId;// 用户id
	private String userCode;// 用户工号(编号)
	private Integer gysId;// 供应商id
	private String gysName;// 供应商名称
	private String gysjc;//简称
	private String addTime;// 添加时间
	private Float kuCunCount;// 库存数量
	private String jiaofuTime;// 交付时间
	private String querenTime;// 确认时间
	private String shArrivalTime;// 应到货时间
	private String acArrivalTime;// 实际到货时间
	private String jianyanTime;// 检验时间
	private String rukuTime;// 入库时间

	private String status;// 状态(待确认、协商确认、生产中、送货中、待检验、入库、取消)
	private String oldStatus;//（设变关联后记录之前的status,处理完成后恢复）
	private String wlWeizhiDt;// 物料位置动态(供应商地址；运输中(xxx-yyy)；进门；检验区(xxx)；库位(xxx);领取人)
	private String wlKw;// 物料库位(库位)
	private Float singleDuration;// 单班时长(工作时长)
	private Float allJiepai;// 单件总节拍
	private Float deliveryDuration;// 延误时长

	private String applyDate;// 领取时间
	private Float applyCount;// 已领数量
	private Integer rukuCount;// 入库次数
	private Integer comeCount;// 来货次数
	private Float qsNum;//签收数量
	private Float keruku;// 可入库数量
	private Float hasruku;// 已入库数量
	private String wgType;//物料类别;
	private String dhTime;//到货时间
	private Float rukuNum;//入库数量（页面）
	private Float sbjdApplyCount;//设变减单申请数量
	private Float sbjdCount;//设变减单数量
	private String demanddept;//需求部门
	/**
	 * 外委相关
	 */
	private String processNOs;//工序号
	private String processNames;//工序名称
	private String wwType;//外委类型（工序外委/包工包料、外购件/原材料）
	private String wwSource;//外委来源（BOM外委,手动外委）
	private String payDate;//交付日期
	private String remark;//备注
	private Integer wwDetailId;//外委申请明细Id(ta_sop_w_ProcessInforWWApplyDetail)
	private String hadChange;//与同供应商上次的订单相比是否有过设变
	private Float blNum;//补料数量(用于外委补料)
	
	private String rootMarkId;//总成件号（页面传值使用不存入数据库）
	private String rootSlfCard;//总成批次（页面传值使用不存入数据库）
	private String neiorderNum;//内部订单号（页面传值使用不存入数据库）
	private String pcDetail;//批次明细
	private String planNumber;//采购单号（页面显示）
	private Float gbNum;//关闭数量
	private Float wxckCount;//外协出库数量(页面显示)
	private String procardbanben;//生产零件(页面显示)
	private String cybanben;//是否版本有差异(页面显示)
	private String bgMarkIds;//变更零件

	/**
	 * 模具相关
	 */
	private String mujuNumber;//开模单号
	List<MouldDetail> mdList;
	
	
	private WaigouOrder waigouOrder;// 外购订单(多对一)
	
	private String classNames;//前端显示
	
	private Float qrNumber; //签收数量 导出显示使用
	
	private Integer mopId;//物料需求Id;
	private String  more;//备注
	private Boolean isshowBl;//页面显示
	//审批信息
	private Integer epId;	//
	private String epStatus;//
	private String proNumber;//项目编号
	
	private String cordTime;//关闭或删除时间
	
	private String sbNumber;//设变单号(页面传值)
	private Integer sbId;//设变Id(页面传值)
	private String sbmsg;//设变信息(页面传值)
	
	private List<WaigouPlanclApply> clApplyList;//减单处理申请 //页面传值
	// private Set<WaigouDeliveryDetail> wddSet; // 送货明细

	// private ProcardWGCenter pwc;//采购计划中间表
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public String getWlKw() {
		return wlKw;
	}

	public void setWlKw(String wlKw) {
		this.wlKw = wlKw;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getNumber() {
		return number;
	}

	public void setNumber(Float number) {
		this.number = number;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getShArrivalTime() {
		return shArrivalTime;
	}

	public void setShArrivalTime(String shArrivalTime) {
		this.shArrivalTime = shArrivalTime;
	}

	public String getAcArrivalTime() {
		return acArrivalTime;
	}

	public void setAcArrivalTime(String acArrivalTime) {
		this.acArrivalTime = acArrivalTime;
	}

	public String getJianyanTime() {
		return jianyanTime;
	}

	public void setJianyanTime(String jianyanTime) {
		this.jianyanTime = jianyanTime;
	}

	public String getRukuTime() {
		return rukuTime;
	}

	public void setRukuTime(String rukuTime) {
		this.rukuTime = rukuTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getSingleDuration() {
		return singleDuration;
	}

	public void setSingleDuration(Float singleDuration) {
		this.singleDuration = singleDuration;
	}

	public Float getAllJiepai() {
		return allJiepai;
	}

	public void setAllJiepai(Float allJiepai) {
		this.allJiepai = allJiepai;
	}

	public Float getDeliveryDuration() {
		return deliveryDuration;
	}

	public void setDeliveryDuration(Float deliveryDuration) {
		this.deliveryDuration = deliveryDuration;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Float getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}

	public Integer getRukuCount() {
		return rukuCount;
	}

	public void setRukuCount(Integer rukuCount) {
		this.rukuCount = rukuCount;
	}

	public Integer getComeCount() {
		return comeCount;
	}

	public void setComeCount(Integer comeCount) {
		this.comeCount = comeCount;
	}

	public Float getKeruku() {
		return keruku;
	}

	public void setKeruku(Float keruku) {
		this.keruku = keruku;
	}

	public Float getHasruku() {
		return hasruku;
	}

	public void setHasruku(Float hasruku) {
		this.hasruku = hasruku;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	@JSONField(serialize = false)
	public WaigouOrder getWaigouOrder() {
		return waigouOrder;
	}

	public void setWaigouOrder(WaigouOrder waigouOrder) {
		this.waigouOrder = waigouOrder;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Float getKuCunCount() {
		return kuCunCount;
	}

	public void setKuCunCount(Float kuCunCount) {
		this.kuCunCount = kuCunCount;
	}

	public String getJiaofuTime() {
		return jiaofuTime;
	}

	public void setJiaofuTime(String jiaofuTime) {
		this.jiaofuTime = jiaofuTime;
	}

	public Double getHsPrice() {
		return hsPrice;
	}

	public void setHsPrice(Double hsPrice) {
		this.hsPrice = hsPrice;
	}

	public Double getTaxprice() {
		return taxprice;
	}

	public void setTaxprice(Double taxprice) {
		this.taxprice = taxprice;
	}

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getGysId() {
		return gysId;
	}

	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}

	public String getGysName() {
		return gysName;
	}

	public void setGysName(String gysName) {
		this.gysName = gysName;
	}

	public String getQuerenTime() {
		return querenTime;
	}

	public void setQuerenTime(String querenTime) {
		this.querenTime = querenTime;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getSyNumber() {
		return syNumber;
	}

	public void setSyNumber(Float syNumber) {
		this.syNumber = syNumber;
	}

	public String getBanben() {
		return banben;
	}

	public void setBanben(String banben) {
		this.banben = banben;
	}

	public Float getHgNumber() {
		return hgNumber;
	}

	public void setHgNumber(Float hgNumber) {
		this.hgNumber = hgNumber;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getWlWeizhiDt() {
		return wlWeizhiDt;
	}

//	public void setWlWeizhiDt(String wlWeizhiDt) {
//		this.wlWeizhiDt = wlWeizhiDt;
//	}


	public String getProcessNOs() {
		return processNOs;
	}

	public void setProcessNOs(String processNOs) {
		this.processNOs = processNOs;
	}

	public String getProcessNames() {
		return processNames;
	}

	public void setProcessNames(String processNames) {
		this.processNames = processNames;
	}

	public String getWwType() {
		return wwType;
	}

	public void setWwType(String wwType) {
		this.wwType = wwType;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getWwDetailId() {
		return wwDetailId;
	}

	public void setWwDetailId(Integer wwDetailId) {
		this.wwDetailId = wwDetailId;
	}

	public String getClassNames() {
		return classNames;
	}

	public void setClassNames(String classNames) {
		this.classNames = classNames;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public String getYwmarkId() {
		return ywmarkId;
	}

	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}

	public String getWwSource() {
		return wwSource;
	}

	public void setWwSource(String wwSource) {
		this.wwSource = wwSource;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public Float getQrNumber() {
		return qrNumber;
	}

	public void setQrNumber(Float qrNumber) {
		this.qrNumber = qrNumber;
	}

	public Double getYfMoney() {
		return yfMoney;
	}

	public void setYfMoney(Double yfMoney) {
		this.yfMoney = yfMoney;
	}

	public String getHasjk() {
		return hasjk;
	}

	public void setHasjk(String hasjk) {
		this.hasjk = hasjk;
	}

	public String getRootMarkId() {
		return rootMarkId;
	}

	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}

	public String getRootSlfCard() {
		return rootSlfCard;
	}

	public void setRootSlfCard(String rootSlfCard) {
		this.rootSlfCard = rootSlfCard;
	}

	public String getNeiorderNum() {
		return neiorderNum;
	}

	public void setNeiorderNum(String neiorderNum) {
		this.neiorderNum = neiorderNum;
	}

	public Float getQsNum() {
		return qsNum;
	}

	public void setQsNum(Float qsNum) {
		this.qsNum = qsNum;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public String getDhTime() {
		return dhTime;
	}

	public void setDhTime(String dhTime) {
		this.dhTime = dhTime;
	}

	public Float getRukuNum() {
		return rukuNum;
	}

	public void setRukuNum(Float rukuNum) {
		this.rukuNum = rukuNum;
	}

	public Integer getMopId() {
		return mopId;
	}

	public void setMopId(Integer mopId) {
		this.mopId = mopId;
	}

	public Float getSbjdCount() {
		return sbjdCount;
	}

	public void setSbjdCount(Float sbjdCount) {
		this.sbjdCount = sbjdCount;
	}

	public Float getSbjdApplyCount() {
		return sbjdApplyCount;
	}

	public void setSbjdApplyCount(Float sbjdApplyCount) {
		this.sbjdApplyCount = sbjdApplyCount;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public String getGysjc() {
		return gysjc;
	}

	public void setGysjc(String gysjc) {
		this.gysjc = gysjc;
	}

	public String getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
	}

	public String getMujuNumber() {
		return mujuNumber;
	}

	public void setMujuNumber(String mujuNumber) {
		this.mujuNumber = mujuNumber;
	}

	public List<MouldDetail> getMdList() {
		return mdList;
	}

	public void setMdList(List<MouldDetail> mdList) {
		this.mdList = mdList;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public Float getGbNum() {
		return gbNum;
	}

	public void setGbNum(Float gbNum) {
		this.gbNum = gbNum;
	}

	public Float getWxckCount() {
		return wxckCount;
	}

	public void setWxckCount(Float wxckCount) {
		this.wxckCount = wxckCount;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getEpStatus() {
		return epStatus;
	}

	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}

	public String getHadChange() {
		return hadChange;
	}

	public void setHadChange(String hadChange) {
		this.hadChange = hadChange;
	}

	public String getCordTime() {
		return cordTime;
	}

	public void setCordTime(String cordTime) {
		this.cordTime = cordTime;
	}

	public Boolean getIsshowBl() {
		return isshowBl;
	}

	public void setIsshowBl(Boolean isshowBl) {
		this.isshowBl = isshowBl;
	}

	public List<WaigouPlanclApply> getClApplyList() {
		return clApplyList;
	}

	public void setClApplyList(List<WaigouPlanclApply> clApplyList) {
		this.clApplyList = clApplyList;
	}

	public String getDemanddept() {
		return demanddept;
	}

	public void setDemanddept(String demanddept) {
		this.demanddept = demanddept;
	}

	public Float getBlNum() {
		return blNum;
	}

	public void setBlNum(Float blNum) {
		this.blNum = blNum;
	}

	public String getProcardbanben() {
		return procardbanben;
	}

	public void setProcardbanben(String procardbanben) {
		this.procardbanben = procardbanben;
	}

	public String getCybanben() {
		return cybanben;
	}

	public void setCybanben(String cybanben) {
		this.cybanben = cybanben;
	}

	public String getSbNumber() {
		return sbNumber;
	}

	public void setSbNumber(String sbNumber) {
		this.sbNumber = sbNumber;
	}

	
	public Integer getSbId() {
		return sbId;
	}

	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}

	public String getSbmsg() {
		return sbmsg;
	}

	public void setSbmsg(String sbmsg) {
		this.sbmsg = sbmsg;
	}

	public String getBgMarkIds() {
		return bgMarkIds;
	}

	public void setBgMarkIds(String bgMarkIds) {
		this.bgMarkIds = bgMarkIds;
	}
	

	
	
	
	
}
