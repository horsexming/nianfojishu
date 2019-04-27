package com.task.action.pro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.UserServer;
import com.task.Server.pro.ProServer;
import com.task.entity.Users;
import com.task.entity.kvp.KVPAssess;
import com.task.entity.pro.Pro;
import com.task.util.MKUtil;
import com.tast.entity.zhaobiao.Nianlilv;

public class ProAction extends ActionSupport {
	private Pro pro;
	private List<Pro> proList;
	private String errorMessage;
	private String successMessage;
	private String overName;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List list;
	private List listAll;
	private ProServer proServer;
	private UserServer userServer;

	public void listPro() {
		listAll = proServer.listPro();
		MKUtil.writeJSON(listAll);
	}

	public void listKVP() {
		List list2 = proServer.listKVP(overName);
		MKUtil.writeJSON(list2);
	}

	/***
	 * 安卓初始加载项目接口
	 */
	public void android_list1() {
		listAll = proServer.listPro();
		if (listAll != null && listAll.size() > 0) {
			MKUtil.writeJSON(true, "", listAll);
		} else {
			MKUtil.writeJSON(false, "数据异常!", "");
		}
	}

	/***
	 * 安卓初始加载KVP接口
	 */
	public void android_list2() {
		List list2 = proServer.listKVP(overName);
		if (list2 != null && list2.size() > 0) {
			MKUtil.writeJSON(true, "", list2);
		} else {
			MKUtil.writeJSON(false, "数据异常!", "");
		}
	}

	// 添加项目记录
	public String addPro() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		this.pro.setCreateUserId(user.getId());
		this.pro.setCreateDate(new Date());
		this.pro.setStatus("start");
		try {
			String result = proServer.addPro(this.pro);
			if ("true".equals(result)) {
				// MKUtil.writeJSON(true, "操作成功", null);
				return "addPro_success";
			} else {
				// MKUtil.writeJSON(false, "操作失败", null);
				return "addPro_failure";
			}
		} catch (Exception e) {
			// MKUtil.writeJSON(false, "操作失败", null);
			return "addPro_failure";
		}
	};

	// 删除项目记录
	public String deletePro() {
		try {
			String result = proServer.deletePro(this.pro);
			if ("true".equals(result)) {
				return "deletePro_success";
			} else {
				return "deletePro_failure";
			}
		} catch (Exception e) {
			return "deletePro_failure";
		}
	};

	// 获得更新页面
	public String getProUpdatePage() {
		this.pro = proServer.getProById(this.pro.getId());
		return "getProUpdatePage_success";
	}

	// 更新项目记录
	public String updatePro() {
		try {
			Pro pro = proServer.getProById(this.pro.getId());
			String name = this.pro.getName();
			String code = this.pro.getCode();
			String clientName = this.pro.getClientName();
			Double budget = this.pro.getBudget();
			Date finishDate = this.pro.getFinishDate();
			if (name != null && !"".equals(name)) {
				pro.setName(name);
			}
			if (code != null && !"".equals(code)) {
				pro.setCode(code);
			}
			if (clientName != null && !"".equals(clientName)) {
				pro.setClientName(clientName);
			}
			if (budget != null) {
				pro.setBudget(budget);
			}
			if (finishDate != null) {
				pro.setFinishDate(finishDate);
			}
			String result = proServer.updatePro(pro);
			if ("true".equals(result)) {
				return "updatePro_success";
			} else {
				return "updatePro_failure";
			}
		} catch (Exception e) {
			return "updatePro_failure";
		}
	};

	// 获得项目记录
	public String getProById() {
		this.pro = proServer.getProById(this.pro.getId());
		String userName = userServer.findUserById(this.pro.getCreateUserId())
				.getName();
		this.pro.setCreateUserName(userName);
		return null;
	};

	// 获得项目记录集合
	/*
	 * public String findAllPro(){ Map map=new HashMap(); Object[] object =
	 * proServer.findAllPro(map, Integer.parseInt(cpage), pageSize); if (object
	 * != null && object.length > 0) { proList = (List<Pro>) object[0]; if
	 * (proList != null && proList.size() > 0) { int size=proList.size();
	 * for(int i=0;i<size;i++){ Pro pro=proList.get(i); String
	 * userName=userServer.findUserById(pro.getCreateUserId()).getName();
	 * pro.setCreateUserName(userName); } int count = (Integer) object[1]; int
	 * pageCount = (count + pageSize - 1) / pageSize; this.setTotal(pageCount +
	 * ""); this.setUrl("proAction!findAllPro.action"); errorMessage = null; }
	 * else errorMessage = "没有找到你要查询的内容,请检查后重试!"; } else{ errorMessage =
	 * "没有找到你要查询的内容,请检查后重试!"; } return "findAllPro_success"; }
	 */

	public String findAllPro() {
		if (pro != null) {
			ActionContext.getContext().getSession().put("pro", pro);
		} else {
			pro = (Pro) ActionContext.getContext().getSession().get("pro");
		}
		Object[] object = proServer.listpro(pro, Integer.parseInt(cpage),
				pageSize);

		if (object != null && object.length > 0) {
			list = (List<Pro>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("proAction!findAllPro.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "findAllPro_success";
	}

	public String findAllProForJson() {
		Object[] object = proServer.listpro(pro, 0, 0);
		if (object != null && object.length > 0) {
			list = (List<Pro>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("proAction!findAllPro.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "findAllPro_success";
	}

	public String getProDetailPage() {
		this.pro = proServer.getProById(this.pro.getId());
		return "getProDetailPage_success";// pro_detail.jsp
	}

	public Pro getPro() {
		return pro;
	}

	public void setPro(Pro pro) {
		this.pro = pro;
	}

	public List<Pro> getProList() {
		return proList;
	}

	public void setProList(List<Pro> proList) {
		this.proList = proList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
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

	public ProServer getProServer() {
		return proServer;
	}

	public void setProServer(ProServer proServer) {
		this.proServer = proServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public String getOverName() {
		return overName;
	}

	public void setOverName(String overName) {
		this.overName = overName;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

}
