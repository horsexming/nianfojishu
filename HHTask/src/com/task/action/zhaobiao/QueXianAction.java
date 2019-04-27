package com.task.action.zhaobiao;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.zhaobiao.QueXianServer;
import com.task.entity.Users;
import com.task.util.Util;
import com.tast.entity.zhaobiao.QueXian;

public class QueXianAction {
	private QueXianServer queXianServer;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	//
	private QueXian queXian;
	private Users users;
	
	private List list;
	
	
	public String listqueXianZong() {
		if (queXian != null) {
			ActionContext.getContext().getSession().put("queXian", queXian);
		} else {
			queXian = (QueXian) ActionContext.getContext().getSession().get(
					"queXian");
		}
		Object[] object = queXianServer.listqueXianZong(queXian, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<QueXian>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("queXianAction!listqueXianZong.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listqueXianZong";
	}	
	public String toUpdatequeXianguanliyuan() {
		queXian=queXianServer.ByIdquexian(queXian.getId());
		return "toUpdatequeXianguanliyuan";
	}
	public String UpdatequeXianguanliyuan() {
		queXianServer.UpdatequeXian(queXian);
		return "UpdatequeXianguanliyuan";
	}
	
	public String deletequeXianguanliyuan() {
		queXianServer.deletequeXian(queXian);
		return "deletequeXianguanliyuan";
	}
	public String listQueXian() {
	
		if (queXian != null) {
			ActionContext.getContext().getSession().put("queXian", queXian);
		} else {
			queXian = (QueXian) ActionContext.getContext().getSession().get(
					"queXian");
		}
		Object[] object = queXianServer.listQueXian(queXian, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<QueXian>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("queXianAction!listQueXian.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listQueXian";
	}
	public String addqueXian(){
		Users user = Util.getLoginUser();
		queXian.setTianbaoren(user.getName());
		queXian.setTianbaoshijian(Util.getDateTime());
		queXian.setTianbaodept(user.getDept());
		queXianServer.addqueXian(queXian);
		return "addqueXian";
	}
	public String deletequeXian() {
		queXianServer.deletequeXian(queXian);
		return "deletequeXian";
	}
	public String toUpdatequeXian() {
		queXian=queXianServer.ByIdquexian(queXian.getId());
		return "toUpdatequeXian";
	}
	public String UpdatequeXian() {
		queXianServer.UpdatequeXian(queXian);
		return "UpdatequeXian";
	}
	
	///--------------------------
	
	public QueXianServer getQueXianServer() {
		return queXianServer;
	}
	public void setQueXianServer(QueXianServer queXianServer) {
		this.queXianServer = queXianServer;
	}
	public QueXian getQueXian() {
		return queXian;
	}
	public void setQueXian(QueXian queXian) {
		this.queXian = queXian;
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


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}
}