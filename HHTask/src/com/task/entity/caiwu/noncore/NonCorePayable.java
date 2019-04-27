package com.task.entity.caiwu.noncore;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * 非主营业务应付(ta_fin_caiwu_NonCorePayable)
 * @author 贾辉辉
 *
 */
public class NonCorePayable implements Serializable{
	private Integer id;
	@FieldMeta(name = "类型")
	private String subjectItem;//科目，类型
	private String zhaiyao;//摘要，应付款名称
	private String hetongbianhao;//合同编号
	private String fujian;//附件地址
	@FieldMeta(name = "收款单位")
	private String shoukuandanwei;//收款单位
	@FieldMeta(name = "账期开始日期")
	private String zhangqiStartDate;//账期开始日期
	@FieldMeta(name = "账期结束日期")
	private String zhangqiEndDate;//账期结束日期
	@FieldMeta(name = "付款日期")
	private String fukuaiDate;//付款日期
	@FieldMeta(name = "最晚付款日期")
	private String lateDate;//最晚付款日期
	@FieldMeta(name = "应付付款金额")
	private Float yingfukuanJIne;//应付付款金额
	private Float realfukuanJIne;//实付款金额
	private String more;//备注
	@FieldMeta(name = "负责人")
	private String fuzeren;//负责人
	@FieldMeta(name = "添加时间")
	private String saveTime;//添加时间
	private String saveUser;//添加人
	private String saveDept;//添加部门
	private Integer saveUserId;//添加人id
	private Integer epId;//流程id
	@FieldMeta(name = "审批")
	private String status;//审批状态(未审批/同意)
	
	private Integer scpId;//付款汇总表 SupplierCorePayable Id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSaveUserId() {
		return saveUserId;
	}
	public void setSaveUserId(Integer saveUserId) {
		this.saveUserId = saveUserId;
	}
	public String getSaveDept() {
		return saveDept;
	}
	public void setSaveDept(String saveDept) {
		this.saveDept = saveDept;
	}
	public String getLateDate() {
		return lateDate;
	}
	public void setLateDate(String lateDate) {
		this.lateDate = lateDate;
	}
	public String getSubjectItem() {
		return subjectItem;
	}
	public void setSubjectItem(String subjectItem) {
		this.subjectItem = subjectItem;
	}
	public String getZhaiyao() {
		return zhaiyao;
	}
	public void setZhaiyao(String zhaiyao) {
		this.zhaiyao = zhaiyao;
	}
	public String getHetongbianhao() {
		return hetongbianhao;
	}
	public void setHetongbianhao(String hetongbianhao) {
		this.hetongbianhao = hetongbianhao;
	}
	public String getFujian() {
		return fujian;
	}
	public void setFujian(String fujian) {
		this.fujian = fujian;
	}
	public String getShoukuandanwei() {
		return shoukuandanwei;
	}
	public void setShoukuandanwei(String shoukuandanwei) {
		this.shoukuandanwei = shoukuandanwei;
	}
	public String getZhangqiStartDate() {
		return zhangqiStartDate;
	}
	public void setZhangqiStartDate(String zhangqiStartDate) {
		this.zhangqiStartDate = zhangqiStartDate;
	}
	public String getZhangqiEndDate() {
		return zhangqiEndDate;
	}
	public void setZhangqiEndDate(String zhangqiEndDate) {
		this.zhangqiEndDate = zhangqiEndDate;
	}
	public String getFukuaiDate() {
		return fukuaiDate;
	}
	public void setFukuaiDate(String fukuaiDate) {
		this.fukuaiDate = fukuaiDate;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}
	public String getFuzeren() {
		return fuzeren;
	}
	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}
	public String getSaveTime() {
		return saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	public String getSaveUser() {
		return saveUser;
	}
	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getYingfukuanJIne() {
		return yingfukuanJIne;
	}
	public void setYingfukuanJIne(Float yingfukuanJIne) {
		this.yingfukuanJIne = yingfukuanJIne;
	}
	public Float getRealfukuanJIne() {
		return realfukuanJIne;
	}
	public void setRealfukuanJIne(Float realfukuanJIne) {
		this.realfukuanJIne = realfukuanJIne;
	}
	public Integer getScpId() {
		return scpId;
	}
	public void setScpId(Integer scpId) {
		this.scpId = scpId;
	}
	
	
}