package com.task.entity.vo.shizhivo;

import java.io.Serializable;

import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.Cusimportance;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.TryMake;

public class TryMakeVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proName;//项目名称
	private String month;//月份
	 private Integer inputNum;//入库数量
	 private Integer approvalNum;//申请数量
	 private String partName;//零件名称
	 private String projectStatu;//项目阶段
	 private Float craftLoad;//工艺负荷分值
	 private Float productivityLoad;//产能负荷系数
	 private Float bonusLoad;//奖金负荷系数
	 private Float cusImportance;//客户重要性系数
	 private Float cLoadRate;//工艺负荷分值占比
	 private Float pLoadRate;//产能负荷系数占比
	 private Float bLoadRate;//奖金负荷系数占比
	 private Float cImRate;//客户重要性系数占比
	 private Float totalRate;//总比得分
	 private Float allTotalRate;//该月总占比得分总和
	 private Float bonus;//该月奖金额
	 private String remark;//备注
	 
	 private Integer  qpId;//零件ID 
		private String markId;//件号
		// 树形结构附属属性
		private Integer rootId;// 第一层父类Id
		private Integer fatherId;// 上层父类Id
	 /**
	  * 此方法在server层调用
	  * @param tryMake
	  */
	 public TryMakeVo(TryMake tryMake){
		 ProTryMakeScore ptm =tryMake.getProTryMakeScore();
		 this.proName=ptm.getProName();
		 this.month=ptm.getMonth();
		 this.id=tryMake.getId();
		 this.inputNum=tryMake.getInputNum();
		 this.approvalNum=tryMake.getApprovalNum();
		 this.partName=tryMake.getPartName();
		 this.projectStatu=tryMake.getProjectStatu();
		 this.remark=tryMake.getRemark();
		 this.qpId=tryMake.getQpId();
		 this.rootId=tryMake.getRootId();
		 this.fatherId=tryMake.getFatherId();
		 this.markId=tryMake.getMarkId();
		 if(ptm!=null&&ptm.getProjectOrder()!=null&&(ptm.getProjectOrder()).size()>0
				 &&inputNum!=null&&inputNum>0){
			 this.bonusLoad=tryMake.getBloadScore();
			 this.bLoadRate=tryMake.getBloadRate();
			 this.craftLoad=tryMake.getCloadScore();
			 this.cLoadRate=tryMake.getCloadRate();
			 this.cusImportance=tryMake.getCimScore();
			 this.cImRate=tryMake.getCloadRate();
			 this.productivityLoad=tryMake.getPloadScore();
			 this.pLoadRate=tryMake.getPloadRate();
			 this.totalRate=tryMake.getTotalRate();
			 this.allTotalRate=tryMake.getAllTotalRate();
			 this.bonus=tryMake.getBonus();
		 }
	 }
	public TryMakeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInputNum() {
		return inputNum;
	}
	public void setInputNum(Integer inputNum) {
		this.inputNum = inputNum;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public String getProjectStatu() {
		return projectStatu;
	}
	public void setProjectStatu(String projectStatu) {
		this.projectStatu = projectStatu;
	}
	public Float getCraftLoad() {
		return craftLoad;
	}
	public void setCraftLoad(Float craftLoad) {
		this.craftLoad = craftLoad;
	}
	public Float getProductivityLoad() {
		return productivityLoad;
	}
	public void setProductivityLoad(Float productivityLoad) {
		this.productivityLoad = productivityLoad;
	}
	public Float getBonusLoad() {
		return bonusLoad;
	}
	public void setBonusLoad(Float bonusLoad) {
		this.bonusLoad = bonusLoad;
	}
	public Float getCusImportance() {
		return cusImportance;
	}
	public void setCusImportance(Float cusImportance) {
		this.cusImportance = cusImportance;
	}
	public Float getcLoadRate() {
		return cLoadRate;
	}
	public void setcLoadRate(Float cLoadRate) {
		this.cLoadRate = cLoadRate;
	}
	public Float getpLoadRate() {
		return pLoadRate;
	}
	public void setpLoadRate(Float pLoadRate) {
		this.pLoadRate = pLoadRate;
	}
	public Float getbLoadRate() {
		return bLoadRate;
	}
	public void setbLoadRate(Float bLoadRate) {
		this.bLoadRate = bLoadRate;
	}
	public Float getcImRate() {
		return cImRate;
	}
	public void setcImRate(Float cImRate) {
		this.cImRate = cImRate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getQpId() {
		return qpId;
	}
	public void setQpId(Integer qpId) {
		this.qpId = qpId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	
	public Float getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(Float totalRate) {
		this.totalRate = totalRate;
	}
	public Float getBonus() {
		return bonus;
	}
	public void setBonus(Float bonus) {
		this.bonus = bonus;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Float getAllTotalRate() {
		return allTotalRate;
	}
	public void setAllTotalRate(Float allTotalRate) {
		this.allTotalRate = allTotalRate;
	}
	public Integer getApprovalNum() {
		return approvalNum;
	}
	public void setApprovalNum(Integer approvalNum) {
		this.approvalNum = approvalNum;
	}
	 
}
