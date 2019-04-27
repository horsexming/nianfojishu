package com.task.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.InsuranceGoldServer;
import com.task.entity.InsuranceGold;

/**
 * 保险缴纳比例Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class InsuranceGoldAction extends ActionSupport {

	private InsuranceGoldServer insuranceGoldServer;// Server层

	private InsuranceGold insuranceGold;// 对象
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private List<InsuranceGold> insuranceGoldList;// 集合
	private int id;// id

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 更新工资模版信息
	public String updateWSByIG() {
		boolean bool = insuranceGoldServer.updateWSByIG();
		if (bool) {
			successMessage = "更新工资模板内个人保险缴纳信息成功!";
			return "updateWSByIGScuuess";
		} else {
			errorMessage = "更新工资模板内个人保险缴纳信息失败!";
		}
		return ERROR;
	}

	// 添加百分比
	public String addInsuranceGold() {
		try {
			if (DateFormat.getDateInstance().parse(
					insuranceGold.getValidityEndDate()).getTime() >= DateFormat
					.getDateInstance().parse(
							insuranceGold.getValidityStartDate()).getTime()) {
				if ((Object) id != null && id > 0) {
					InsuranceGold oldInsuranceGold = insuranceGoldServer
							.findInsuranceGoldById(id);
					insuranceGoldServer.delInsuranceGold(oldInsuranceGold);
				}
				boolean bool = insuranceGoldServer
						.CompareValidityDate(insuranceGold);
				if (bool) {
					insuranceGold.setId(id);
					bool = insuranceGoldServer.addInsuranceGold(insuranceGold);
					if (bool) {
						if ((Object) id != null && id > 0) {
							successMessage = "修改保险缴纳比例成功!";
						} else {
							successMessage = "添加保险缴纳比例成功!";
						}
						return "updateWSByIGScuuess";
					} else {
						errorMessage = "添加保险缴纳比例失败!请检查后重试，或联系管理员!";
					}
				} else {
					errorMessage = "所选择时间段内的保险缴纳比例已经存在,请重新选择时间!";
				}
			} else {
				errorMessage = "开始时间大于结束时间,请重新选择!";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ERROR;
	}

	// 查询所有保险缴纳比例(分页)
	@SuppressWarnings("unchecked")
	public String findAllInsuranceGold() {
		Object[] o = insuranceGoldServer.findAllInsuranceGold(cpage, pageSize);
		insuranceGoldList = (List<InsuranceGold>) o[0];
		if (insuranceGoldList != null && insuranceGoldList.size() > 0) {
			int count = (Integer) o[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("InsuranceGoldAction!findAllInsuranceGold.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "updateWSByIGScuuess";
	}
	
	

	// 删除保险缴纳比例
	public String delInsuranceGold() {
		findInsuranceGoldById();
		boolean bool = insuranceGoldServer.delInsuranceGold(insuranceGold);
		if (bool) {
			findAllInsuranceGold();
			successMessage = "删除保险缴纳比例成功!";
			return "updateWSByIGScuuess";
		} else {
			errorMessage = "删除保险缴纳比例失败!请检查后重试!";
		}
		return ERROR;
	}

	// 查询保险缴纳比例通过Id
	public String findInsuranceGoldById() {
		insuranceGold = insuranceGoldServer.findInsuranceGoldById(id);
		if (insuranceGold != null) {
			return "updateWSByIGScuuess";
		}
		errorMessage = "不存在该保险缴纳比例信息,请检查后重试!";
		return ERROR;
	}

	public InsuranceGoldServer getInsuranceGoldServer() {
		return insuranceGoldServer;
	}

	public void setInsuranceGoldServer(InsuranceGoldServer insuranceGoldServer) {
		this.insuranceGoldServer = insuranceGoldServer;
	}

	public InsuranceGold getInsuranceGold() {
		return insuranceGold;
	}

	public void setInsuranceGold(InsuranceGold insuranceGold) {
		this.insuranceGold = insuranceGold;
	}

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

	public List<InsuranceGold> getInsuranceGoldList() {
		return insuranceGoldList;
	}

	public void setInsuranceGoldList(List<InsuranceGold> insuranceGoldList) {
		this.insuranceGoldList = insuranceGoldList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
