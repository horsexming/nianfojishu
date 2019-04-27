package com.task.entity.sop;

import com.task.util.FieldMeta;

/**
 * 外委申请明细删除申请单明细
 * @author txb
 *
 */
public class ProcessInfordeleteApplyDetail  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	@FieldMeta(name="件号")
	private String markId;//件号
	@FieldMeta(name="零件名称")
	private String proName;//零件名称
	@FieldMeta(name="版本号")
	private String banbenNumber;//版本号
	@FieldMeta(name="版次")
	private Integer banci;//版次
	@FieldMeta(name="单位")
	private String unit;//单位
	@FieldMeta(name="批次")
	private String selfCard;//批次
	@FieldMeta(name="原工序号")
	private String processNOs;//工序号
	@FieldMeta(name="原工序名称")
	private String processNames;//名称
	@FieldMeta(name="申请数量")
	private Float applyCount;//申请数量
	private String relatDown;//工序外委是否关联下层（标记在被外委的当层）(是，否)
	private String dataStatus;//数据状态
	
	private Integer processinforwwDetailId;
	private String processStatus;//流转状态(预选未审批,合同待确认,外委待下单,订单外委采购，生产中,删除)
	
	private ProcessInfordeleteApply pida;//主表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getBanbenNumber() {
		return banbenNumber;
	}

	public void setBanbenNumber(String banbenNumber) {
		this.banbenNumber = banbenNumber;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSelfCard() {
		return selfCard;
	}

	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}

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

	public Float getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}

	public String getRelatDown() {
		return relatDown;
	}

	public void setRelatDown(String relatDown) {
		this.relatDown = relatDown;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getProcessinforwwDetailId() {
		return processinforwwDetailId;
	}

	public void setProcessinforwwDetailId(Integer processinforwwDetailId) {
		this.processinforwwDetailId = processinforwwDetailId;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public ProcessInfordeleteApply getPida() {
		return pida;
	}

	public void setPida(ProcessInfordeleteApply pida) {
		this.pida = pida;
	}
	
	
	
}
