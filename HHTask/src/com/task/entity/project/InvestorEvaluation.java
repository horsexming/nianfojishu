package com.task.entity.project;
/**
 * 投资者评价表 ta_pro_InvestorEvaluation
 * @author txb
 *
 */
public class InvestorEvaluation  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String type;//类别（点赞，吐槽）
	private String remark;//备注
	private Integer investorId;//投资者Id
	private Integer iofQpId;//对应零件的投资Id
	private Integer iofQpId2;//评价人Id
	private Integer qpId;//零件id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public Integer getIofQpId() {
		return iofQpId;
	}
	public void setIofQpId(Integer iofQpId) {
		this.iofQpId = iofQpId;
	}
	public Integer getIofQpId2() {
		return iofQpId2;
	}
	public void setIofQpId2(Integer iofQpId2) {
		this.iofQpId2 = iofQpId2;
	}
	
	
	

}
