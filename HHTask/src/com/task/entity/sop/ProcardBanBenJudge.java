package com.task.entity.sop;

import java.util.Set;

/**
 * 生产件评论 ta_ProcardBanBenJudge
 * @author txb
 *
 */
public class ProcardBanBenJudge implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Integer pabbJudgeId;//设变关联生产Id(ProcardAboutBanBenApply)
	private Integer processbbJudgeId;//设变关联生产工序Id(ProcessAboutBanBenAppl
	private Integer ptbbJudgeId;//设变内审人员Id(ta_ProcardTemplateBanBenJudges)
	private Integer userId;//评审员Id
	private String userName;//评审员
	private String remark;//评论(顺延,返修,重产)
	private String bcremark;//评论补充
	private String addTime;//添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPabbJudgeId() {
		return pabbJudgeId;
	}
	public void setPabbJudgeId(Integer pabbJudgeId) {
		this.pabbJudgeId = pabbJudgeId;
	}
	public Integer getPtbbJudgeId() {
		return ptbbJudgeId;
	}
	public void setPtbbJudgeId(Integer ptbbJudgeId) {
		this.ptbbJudgeId = ptbbJudgeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getProcessbbJudgeId() {
		return processbbJudgeId;
	}
	public void setProcessbbJudgeId(Integer processbbJudgeId) {
		this.processbbJudgeId = processbbJudgeId;
	}
	public String getBcremark() {
		return bcremark;
	}
	public void setBcremark(String bcremark) {
		this.bcremark = bcremark;
	}
	
	
	

}
