package com.task.entity.vo.shizhivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.task.entity.shizhi.BonusShiZhi;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.TryMake;

public class ProTryMakeScoreVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proName;// 项目名称
	private String month;// 月份
	private String proNum;// 项目编号
	private String cusName;// 客户名称
	private List<TryMakeVo> tryMakeVo;// 试制Vo
	private Float bonus;// 月奖金额
	private String groupName;// 小组名称
	private String orderNo;// 试制订单号
	private Integer poSize;// 试制单个数
	private String approvalStatus;// 审批状态
	private Integer approvalId;// 审批状态Id
	private Float completionrate;//完成率

	public ProTryMakeScoreVo() {
		super();
	}

	public ProTryMakeScoreVo(ProTryMakeScore proTryMakeScore) {

		this.id = proTryMakeScore.getId();
		this.month = proTryMakeScore.getMonth();
		this.bonus = 0f;
		this.proNum = proTryMakeScore.getProNum();
		this.proName = proTryMakeScore.getProName();
		this.cusName = proTryMakeScore.getCusName();
		this.groupName = proTryMakeScore.getGroupName();
		this.approvalStatus=proTryMakeScore.getApprovalStatus();
		this.approvalId=proTryMakeScore.getApprovalId();
		this.completionrate=proTryMakeScore.getCompletionrate();
		if (proTryMakeScore.getPoSize() != null
				&& proTryMakeScore.getPoSize() > 0) {
			StringBuffer sb = new StringBuffer();
			int n = 0;
			for (ProjectOrder p : proTryMakeScore.getProjectOrder()) {
				if (p.getStatus() != null && p.getStatus().equals("同意")) {
					if (n == 0) {
						sb.append(p.getOrderNO());
					} else {
						sb.append("," + p.getOrderNO());
					}
					n++;
				}
			}
			this.orderNo = sb.toString();
		}
		Set<TryMake> tryMakeSet = proTryMakeScore.getTryMake();
		if (tryMakeSet.size() > 0) {
			List<TryMakeVo> tmVoList = new ArrayList<TryMakeVo>();
			for (TryMake tm : tryMakeSet) {
				tmVoList.add(new TryMakeVo(tm));
			}
			this.tryMakeVo = tmVoList;
		}
	}
    public void setVoVaule(ProTryMakeScore proTryMakeScore){
    	this.id = proTryMakeScore.getId();
		this.month = proTryMakeScore.getMonth();
		this.bonus = 0f;
		this.proNum = proTryMakeScore.getProNum();
		this.proName = proTryMakeScore.getProName();
		this.cusName = proTryMakeScore.getCusName();
		this.groupName = proTryMakeScore.getGroupName();
		this.approvalStatus=proTryMakeScore.getApprovalStatus();
		this.approvalId=proTryMakeScore.getId();
		this.completionrate=proTryMakeScore.getCompletionrate();
		if (proTryMakeScore.getPoSize() != null
				&& proTryMakeScore.getPoSize() > 0) {
			StringBuffer sb = new StringBuffer();
			int n = 0;
			for (ProjectOrder p : proTryMakeScore.getProjectOrder()) {
				if (p.getStatus() != null && p.getStatus().equals("同意")) {
					if (n == 0) {
						sb.append(p.getOrderNO());
					} else {
						sb.append("," + p.getOrderNO());
					}
					n++;
				}
			}
			this.orderNo = sb.toString();
		}
		Set<TryMake> tryMakeSet = proTryMakeScore.getTryMake();
		if (tryMakeSet.size() > 0) {
			List<TryMakeVo> tmVoList = new ArrayList<TryMakeVo>();
			for (TryMake tm : tryMakeSet) {
				if(tm.getInputNum()!=null&&tm.getInputNum()>0){
					tmVoList.add(new TryMakeVo(tm));
				}
			}
			this.tryMakeVo = tmVoList;
		}
    }
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public List<TryMakeVo> getTryMakeVo() {
		return tryMakeVo;
	}

	public void setTryMakeVo(List<TryMakeVo> tryMakeVo) {
		this.tryMakeVo = tryMakeVo;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Float getBonus() {
		return bonus;
	}

	public void setBonus(Float bonus) {
		this.bonus = bonus;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPoSize() {
		return poSize;
	}

	public void setPoSize(Integer poSize) {
		this.poSize = poSize;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Integer approvalId) {
		this.approvalId = approvalId;
	}

	public Float getCompletionrate() {
		return completionrate;
	}

	public void setCompletionrate(Float completionrate) {
		this.completionrate = completionrate;
	}

}
