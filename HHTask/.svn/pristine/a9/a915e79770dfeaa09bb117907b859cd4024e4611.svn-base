package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhaobiao.ClauseServer;
import com.tast.entity.zhaobiao.ClauseFather;
import com.tast.entity.zhaobiao.ClauseSon;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;

public class ClauseAction extends ActionSupport  {
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	//
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	//
	private List list;
	private ClauseServer clauseServer;
	private ClauseFather clauseFather;
	private ClauseSon clauseSon;
	
	public String listclauseFatherSon() {
		clauseFather=clauseServer.getByIdClauseFather(clauseFather);
		if (clauseSon != null) {
			ActionContext.getContext().getSession().put("clauseSon", clauseSon);
		} else {
			clauseSon = (ClauseSon) ActionContext.getContext()
					.getSession().get("clauseSon");
		}
		Object[] object = clauseServer.listclauseFatherSon(clauseFather.getId(),clauseSon, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<ClauseSon>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("ClauseAction!listclauseFatherSon.action?clauseFather.id="+clauseFather.getId());
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listclauseFatherSon";
	}
	public String toaddClauseSon() {
		clauseFather=clauseServer.getByIdClauseFather(clauseFather);
		return "toaddClauseSon";
	}
	public String addClauseSon() {
		clauseServer.addClauseSon(clauseSon);
		errorMessage="添加成功！！！";
		return "addClauseSon";
	}
	public String deleteclauseSon() {
		if (clauseSon!=null) {
			clauseServer.deleteclauseSon(clauseSon);
			errorMessage="删除成功！！！";
		}
		url="ClauseAction!listclauseFatherSon.action?clauseFather.id="+clauseFather.getId();
		return ERROR;
	}
	public String toUpdateClauseSon() {
		clauseSon=clauseServer.ByIdclauseSon(clauseSon.getId());
		return "toUpdateClauseSon";
	}
	public String updateClauseSon() {
		clauseServer.updateClauseSon(clauseSon);
		errorMessage="修改成功！！！";
		return "updateClauseSon";
	}
	//---------------------------------------
	public String listClause() {
		if (clauseFather != null) {
			ActionContext.getContext().getSession().put("clauseFather", clauseFather);
		} else {
			clauseFather = (ClauseFather) ActionContext.getContext()
					.getSession().get("clauseFather");
		}
		Object[] object = clauseServer.listtianxiejiepai(clauseFather, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<ClauseFather>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("ClauseAction!listClause.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listClause";
	}
	public String  addClause() {
		clauseServer.addClause(clauseFather);
		errorMessage="添加成功！！！";
		return "addClause";
	}
	public String deleteclauseFather() {
		if (clauseFather!=null) {
			clauseServer.deleteclauseFather(clauseFather);
			errorMessage="删除成功！！！";
		}
		url="ClauseAction!listClause.action";
		return ERROR;
	}
	public String toUpdateClause() {
			//clauseFather=(ClauseFather) clauseServer.getByIdObject(clauseFather,clauseFather.getId());
			clauseFather=clauseServer.getByIdClauseFather(clauseFather);
		return "toUpdateClause";
	}
	public String updateClause() {
		clauseServer.updateClause(clauseFather);
		errorMessage="修改成功!!!";
		return "toUpdateClause";
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ClauseServer getClauseServer() {
		return clauseServer;
	}

	public void setClauseServer(ClauseServer clauseServer) {
		this.clauseServer = clauseServer;
	}

	public ClauseFather getClauseFather() {
		return clauseFather;
	}

	public void setClauseFather(ClauseFather clauseFather) {
		this.clauseFather = clauseFather;
	}

	public ClauseSon getClauseSon() {
		return clauseSon;
	}

	public void setClauseSon(ClauseSon clauseSon) {
		this.clauseSon = clauseSon;
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
	
	
}
