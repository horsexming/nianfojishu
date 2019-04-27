package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.BonusLoadServer;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.shizhi.BonusLoad;

/**
 * 技能系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class BonusLoadAction {
	private BonusLoadServer bonusLoadServer;// 奖金负荷系数服务层
	private BonusLoad bonusLoad;// 奖金负荷系数对象
	private List<BonusLoad> bLoadList;// 奖金负荷系数列表
	private List<QuotedProcessInfor> qpInfoList;// 工序列表

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	


	public String showList() {
		if (bonusLoad != null) {
			ActionContext.getContext().getSession().put("bonusLoad", bonusLoad);
		} else {// 用来保持分页时带着查询条件
			bonusLoad = (BonusLoad) ActionContext.getContext().getSession()
					.get("bonusLoad");
		}
		
		Map<Integer, Object> map = bonusLoadServer.findBonusLoadsByCondition(
				bonusLoad, Integer.parseInt(cpage), pageSize);
		bLoadList = (List<BonusLoad>) map.get(1);// 显示页的奖金负荷系数列表
		if (bLoadList != null & bLoadList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("bonusLoadAction_showList.action?forreturn=1");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "bonusLoad_show";
	}

	

	public String delete() {
		boolean b = bonusLoadServer.deleteById(bonusLoad.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		bonusLoad = null;
		return showList();
	}
	/**
	 * 重新校验成本和含税价
	 * @return
	 */
	 public String updateAll(){
		 boolean b=bonusLoadServer.updateAll();
		 if(b){
			 successMessage="更新完成!";
		 }else{
			 errorMessage = "更新失败,请检查后重试!";
		 }
		 return showList();
	 }

	// get set方法

	public String getSuccessMessage() {
		return successMessage;
	}

	public BonusLoadServer getBonusLoadServer() {
		return bonusLoadServer;
	}

	public void setBonusLoadServer(BonusLoadServer bonusLoadServer) {
		this.bonusLoadServer = bonusLoadServer;
	}

	public BonusLoad getBonusLoad() {
		return bonusLoad;
	}

	public void setBonusLoad(BonusLoad bonusLoad) {
		this.bonusLoad = bonusLoad;
	}

	public List<BonusLoad> getbLoadList() {
		return bLoadList;
	}

	public void setbLoadList(List<BonusLoad> bLoadList) {
		this.bLoadList = bLoadList;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public List<QuotedProcessInfor> getQpInfoList() {
		return qpInfoList;
	}

	public void setQpInfoList(List<QuotedProcessInfor> qpInfoList) {
		this.qpInfoList = qpInfoList;
	}

}
