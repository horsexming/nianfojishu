package com.task.entity.sop;
/**
 * 外购外委与上此外发相比是否有新的差异(ta_WaigouWaiweisbCompare)
 * @author txb
 *
 */
public class WaigouWaiweisbCompare implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private String tableName;//相关表（WaigouPlan,WaigouwaiweiPlan,ProcessinforWwApplyDetail）
	private Integer relateId;// 相关表id
	private String changeMarkId;//修改件号
	private String dataType;//数据类型（本身,工序，图纸，子件）
	private String markId;//变更件号
	private String shuxin;//改变属性
	private String opType;//操作类型（修改,新增,删除）
	private String oldData;//原值
	private String newData;//新值
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getRelateId() {
		return relateId;
	}
	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}
	
	public String getChangeMarkId() {
		return changeMarkId;
	}
	public void setChangeMarkId(String changeMarkId) {
		this.changeMarkId = changeMarkId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getShuxin() {
		return shuxin;
	}
	public void setShuxin(String shuxin) {
		this.shuxin = shuxin;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public String getOldData() {
		return oldData;
	}
	public void setOldData(String oldData) {
		this.oldData = oldData;
	}
	public String getNewData() {
		return newData;
	}
	public void setNewData(String newData) {
		this.newData = newData;
	}
	
	
	
	
}
