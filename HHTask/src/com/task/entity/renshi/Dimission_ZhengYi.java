package com.task.entity.renshi;

import java.io.Serializable;

/**
 * 离职工资单对象
 * 表名ta_hr_lz_dimission_ZhengYi
 * @author Li_Cong
 *
 */
public class Dimission_ZhengYi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//姓名
	private String ruzhiTime;//入职时间
	private String liTime;//离职日期
	private String code;//员工工号
	private String cardId;//员工卡号
	private String dept;//员工部门
	private String dimi_Reason;//离职原因
	private String log_number;//申请单号
	private String hand_number;//交接单号
	
	private String jz_Time;//工资结算截止日期
	private String jxr_Time;//当月计薪日
	private String jx_Money;//生产奖金或绩效分配数
	private String gw_Money;//应发岗位工资
	private String jiangj_Money;//应发奖金绩效
	private String add_up_number;//合计离职工资
	private String bc_Money;//离职补偿金额
	private String daxie;//大写
	private String addup_yf;//合计应发
	private String butie;//补贴
	private String butie_shuoming;//补贴说明
	private String sbjf_jzTime;//社保缴费截止日期
	private String buzu_min;//是否需要补足最低工资标准
	private String canfei;//餐费
	
	private String pension;//养老金
	private String yiliao;//医疗金
	private String shiye;//失业金
	private String fund;//公积金
	private String rent;//房租
	private String other;//其他
	private String real_money;//实际金额合计
	private String yuefen;//工资月份
	private String remark;//备注
	
	
	private Integer epId;//审批流程
	private String zhengyi_Status;//争议审批状态
	private String addTime;
	private String updateTime;
	private String addUserName;//添加人名称
	private Integer addUserId;//添加人ID
	private Integer codeId;//用户id验证非空
	
	private DimissionLog dimissionLogs;//对应的离职申请单
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCanfei() {
		return canfei;
	}
	public void setCanfei(String canfei) {
		this.canfei = canfei;
	}
	public String getYuefen() {
		return yuefen;
	}
	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public DimissionLog getDimissionLogs() {
		return dimissionLogs;
	}
	public void setDimissionLogs(DimissionLog dimissionLogs) {
		this.dimissionLogs = dimissionLogs;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getZhengyi_Status() {
		return zhengyi_Status;
	}
	public void setZhengyi_Status(String zhengyiStatus) {
		zhengyi_Status = zhengyiStatus;
	}
	public String getLog_number() {
		return log_number;
	}
	public void setLog_number(String logNumber) {
		log_number = logNumber;
	}
	public String getHand_number() {
		return hand_number;
	}
	public void setHand_number(String handNumber) {
		hand_number = handNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRuzhiTime() {
		return ruzhiTime;
	}
	public void setRuzhiTime(String ruzhiTime) {
		this.ruzhiTime = ruzhiTime;
	}
	public String getLiTime() {
		return liTime;
	}
	public void setLiTime(String liTime) {
		this.liTime = liTime;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDimi_Reason() {
		return dimi_Reason;
	}
	public void setDimi_Reason(String dimiReason) {
		dimi_Reason = dimiReason;
	}
	public String getJz_Time() {
		return jz_Time;
	}
	public void setJz_Time(String jzTime) {
		jz_Time = jzTime;
	}
	public String getJxr_Time() {
		return jxr_Time;
	}
	public void setJxr_Time(String jxrTime) {
		jxr_Time = jxrTime;
	}
	public String getJx_Money() {
		return jx_Money;
	}
	public void setJx_Money(String jxMoney) {
		jx_Money = jxMoney;
	}
	public String getGw_Money() {
		return gw_Money;
	}
	public void setGw_Money(String gwMoney) {
		gw_Money = gwMoney;
	}
	public String getJiangj_Money() {
		return jiangj_Money;
	}
	public void setJiangj_Money(String jiangjMoney) {
		jiangj_Money = jiangjMoney;
	}
	public String getAdd_up_number() {
		return add_up_number;
	}
	public void setAdd_up_number(String addUpNumber) {
		add_up_number = addUpNumber;
	}
	public String getBc_Money() {
		return bc_Money;
	}
	public void setBc_Money(String bcMoney) {
		bc_Money = bcMoney;
	}
	public String getDaxie() {
		return daxie;
	}
	public void setDaxie(String daxie) {
		this.daxie = daxie;
	}
	public String getAddup_yf() {
		return addup_yf;
	}
	public void setAddup_yf(String addupYf) {
		addup_yf = addupYf;
	}
	public String getButie() {
		return butie;
	}
	public void setButie(String butie) {
		this.butie = butie;
	}
	public String getButie_shuoming() {
		return butie_shuoming;
	}
	public void setButie_shuoming(String butieShuoming) {
		butie_shuoming = butieShuoming;
	}
	public String getSbjf_jzTime() {
		return sbjf_jzTime;
	}
	public void setSbjf_jzTime(String sbjfJzTime) {
		sbjf_jzTime = sbjfJzTime;
	}
	public String getBuzu_min() {
		return buzu_min;
	}
	public void setBuzu_min(String buzuMin) {
		buzu_min = buzuMin;
	}
	public String getYiliao() {
		return yiliao;
	}
	public void setYiliao(String yiliao) {
		this.yiliao = yiliao;
	}
	public String getShiye() {
		return shiye;
	}
	public void setShiye(String shiye) {
		this.shiye = shiye;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getReal_money() {
		return real_money;
	}
	public void setReal_money(String realMoney) {
		real_money = realMoney;
	}
	public String getPension() {
		return pension;
	}
	public void setPension(String pension) {
		this.pension = pension;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	
}
