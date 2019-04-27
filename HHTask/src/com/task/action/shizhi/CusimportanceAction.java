package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.CusimportanceServer;
import com.task.entity.shizhi.Cusimportance;

/**
 * 客户重要系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class CusimportanceAction {
	private CusimportanceServer cusimportanceServer;// 技能系数服务层
	private Cusimportance cusimportance;// 技能系数对象
	private List<Cusimportance> cimpList;// 技能系数列表

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<String> customerNameList;//客户名称列表

	public String showList() {
		if (cusimportance != null) {
			ActionContext.getContext().getSession().put("cusimportance",
					cusimportance);
		} else {// 用来保持分页时带着查询条件
			cusimportance = (Cusimportance) ActionContext.getContext()
					.getSession().get("cusimportance");
		}
		Map<Integer, Object> map = cusimportanceServer
				.findCusimportancesByCondition(cusimportance, Integer
						.parseInt(cpage), pageSize);
		cimpList = (List<Cusimportance>) map.get(1);// 显示页的技能系数列表
		if (cimpList != null & cimpList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("cusimportanceAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "cimp_show";
	}

	public String toupdate() {
		cusimportance = cusimportanceServer
				.getById(cusimportance.getId());
		if (cusimportance != null) {
			return "cimp_update";

		} else {
			errorMessage = "修改失败,不存在该技能系数！";
		}
		return showList();
	}

	public String update() {
			boolean b = cusimportanceServer.update(cusimportance);
			if (b) {
				successMessage = "修改成功！";
				cusimportance = null;
				return showList();
			}
		errorMessage = "修改失败！";
		return "Cusimportance_update";

	}

	public String delete() {
		boolean b = cusimportanceServer.deleteById(cusimportance.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		cusimportance = null;
		return showList();
	}
//	/**
//	 * 对应clientManagement表重新检索数据
//	 * @return
//	 */
//	 public String updateAll(){
//		 boolean b=cusimportanceServer.updateAll();
//		 if(b){
//			 successMessage="更新完成!";
//		 }else{
//			 errorMessage = "更新失败,请检查后重试!";
//		 }
//		 forreturn=1;
//		 return showList();
//	 }

	// get set方法

	public String getSuccessMessage() {
		return successMessage;
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

	public CusimportanceServer getCusimportanceServer() {
		return cusimportanceServer;
	}

	public void setCusimportanceServer(CusimportanceServer cusimportanceServer) {
		this.cusimportanceServer = cusimportanceServer;
	}

	public Cusimportance getCusimportance() {
		return cusimportance;
	}

	public void setCusimportance(Cusimportance cusimportance) {
		this.cusimportance = cusimportance;
	}

	public List<Cusimportance> getCimpList() {
		return cimpList;
	}

	public void setCimpList(List<Cusimportance> cimpList) {
		this.cimpList = cimpList;
	}
	public List<String> getCustomerNameList() {
		return customerNameList;
	}
	public void setCustomerNameList(List<String> customerNameList) {
		this.customerNameList = customerNameList;
	}
	
	

}
