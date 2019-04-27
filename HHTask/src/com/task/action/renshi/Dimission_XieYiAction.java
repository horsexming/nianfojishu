package com.task.action.renshi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.DimissionLogServer;
import com.task.Server.renshi.Dimission_XieYiServer;
import com.task.entity.Provision;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.DimissionNotice;
import com.task.entity.renshi.Dimission_XieYi;

@SuppressWarnings("unchecked")
public class Dimission_XieYiAction {
	private Dimission_XieYiServer dimissionXieYiServer;// 争议单服务；
	private DimissionLogServer dimissionLogServer;
	private Dimission_XieYi dimissionXieYi;// 协议单对象；
	private DimissionLog dimissionLog;// 离职申请单对象
	private Users users;// 离职申请单对象
	private DimissionNotice dimissionNotice;// 离职通知单对象
	private List<Dimission_XieYi> dimissionXieYis;
	private List<DimissionNotice> dimissionNotices;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private String number;// 编号
	private List<Provision> provisionlist;

	private String[] proContent;// 对象（改）

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag = "";//协议标识/编号和通知/编号，(xieyi/tongzhi)

	public String test() {
		return "error";
	}

	// 显示查询内容
	public String showList() {
		if (dimissionXieYi != null) {
			ActionContext.getContext().getSession().put("dimissionXieYi1",
					dimissionXieYi);
		} else {// 用来保持分页时带着查询条件
			dimissionXieYi = (Dimission_XieYi) ActionContext.getContext()
					.getSession().get("dimissionXieYi1");
		}
		Map<Integer, Object> map = dimissionXieYiServer
				.findDimission_XieYisByCondition(dimissionXieYi, Integer
						.parseInt(cpage), pageSize);
		dimissionXieYis = (List<Dimission_XieYi>) map.get(1);// 显示争议单列表
		if (dimissionXieYis != null && dimissionXieYis.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimission_XieYiAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dimission_Xieyi_show";
	}

	// 跳转到添加页面
	public String toadd() {
		DimissionLog dimissionLog1 = dimissionLogServer
				.getDimissionLogById(dimissionLog.getId());
		number = dimissionXieYiServer.findIncrementNumber(tag);
		if (dimissionLog1 != null) {
			dimissionLog = dimissionLog1;
			if ("notice".equals(tag)) {
				return "dimissionNotice_add";
			} else {
				provisionlist = dimissionLogServer.findProvision("lzxy");
				return "dimission_Xieyi_add";
			}
		} else {
			errorMessage = "没有该申请单,请检查后重试!";
			return "error";
		}
	}
	// 跳转到添加页面
	public String toUsersAdd() {
		users = dimissionXieYiServer.getUsersById(id);
		number = dimissionXieYiServer.findIncrementNumber(tag);
		if (users != null) {
			dimissionLog = null;
			provisionlist = dimissionLogServer.findProvision("lzxy");
			return "dimission_Xieyi_add";
		} else {
			errorMessage = "没有该申请单,请检查后重试!";
			return "error";
		}
	}

	// 添加方法
	public String add() {
		number = dimissionXieYiServer.findIncrementNumber(tag);
		errorMessage = dimissionXieYiServer.addDimission_XieYi(dimissionLog,users,
				dimissionXieYi, proContent, number);
		if ("true".equals(errorMessage)) {
			successMessage = "addsuccess";
			return "dim_add_succ_1";
		} else {
			return "error";
		}
	}

	// 跳转到预览打印页面
	public String toselect() {
		Dimission_XieYi dimissionXinYi1 = dimissionXieYiServer
				.getDimission_XieYiById(dimissionXieYi.getId());
		if (dimissionXinYi1 != null) {
			dimissionXieYi = dimissionXinYi1;
			provisionlist = dimissionXieYiServer.findProvision(dimissionXieYi);
			return "dimission_Xieyi_select";
		} else {
			successMessage = "seleteNot";
		}
		return "dim_add_succ_1";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		Dimission_XieYi Dimission_XieYi2 = dimissionXieYiServer
				.getDimission_XieYiById(dimissionXieYi.getId());
		if (Dimission_XieYi2 != null) {
			dimissionXieYi = Dimission_XieYi2;
			return "dimission_zhengyi_update";
		} else {
			errorMessage = "修改失败，不存在该离职单";
		}
		return "dim_add_succ_1";
	}

	// 修改方法
	public String update() {
		boolean b = dimissionXieYiServer.updateDimission_XieYi(dimissionXieYi);
		if (b) {
			successMessage = "updatesuccess";
			dimissionLog = null;
			return "dim_add_succ_1";
		} else {
			errorMessage = "修改失败";
			return toupdate();
		}
	}

	// 删除方法
	public String delete() {
		boolean b = dimissionXieYiServer.deleteDimission_XieYi(dimissionXieYi
				.getId());
		if (b) {
			successMessage = "deletesuccess";
		} else {
			successMessage = "deleteNot";
		}
		dimissionLog = null;
		return "dim_add_succ_1";
	}

	// 跳转到修改页面的方法
	public String toupdateNotice() {
		if (dimissionNotice.getId() > 0 && dimissionNotice != null) {
			DimissionNotice dimissionNotice1 = dimissionXieYiServer
					.getDimissionNoticeById(dimissionNotice.getId());
			if (dimissionNotice1 != null) {
				dimissionNotice = dimissionNotice1;
				return "dimissionNotice_update";
			} else {
				errorMessage = "修改失败，不存在该离职单";
				return "error";
			}
		}
		errorMessage = "不存在该离职通知单";
		return "error";
	}

	// 修改方法
	public String updateNotice() {
		boolean b = dimissionXieYiServer.updateDimissionNotice(dimissionNotice);
		if (b) {
			errorMessage = "修改成功！";
			url = "dimission_XieYiAction_showListNotice.action";
			return "error";
		} else {
			errorMessage = "修改失败";
			return "error";
		}
	}

	// 删除方法
	public String deleteNotice() {
		if (dimissionNotice.getId() > 0 && dimissionNotice != null) {
			boolean b = dimissionXieYiServer
					.deleteDimissionNotice(dimissionNotice.getId());
			if (b) {
				errorMessage = "删除成功";
				url = "dimission_XieYiAction_showListNotice.action";
				return "error";
			} else {
				errorMessage = "删除失败";
			}
			return "error";
		}
		errorMessage = "不存在该离职通知单";
		return "error";
	}

	// 跳转到预览打印页面
	public String toselectNotice() {
		dimissionNotice = dimissionXieYiServer
				.getDimissionNoticeById(dimissionNotice.getId());
		if (dimissionNotice != null) {
			return "dimissionNotice_select";
		} else {
			errorMessage = "不存在该通知单";
			url = "";
			return "error";
		}
	}

	// 显示方法
	public String showListNotice() {
		if (dimissionNotice != null) {
			ActionContext.getContext().getSession().put("dimissionNotice",
					dimissionNotice);
		} else {// 用来保持分页时带着查询条件
			dimissionNotice = (DimissionNotice) ActionContext.getContext()
					.getSession().get("dimissionNotice");
		}
		Map<Integer, Object> map = dimissionXieYiServer
				.findDimissionNoticesByCondition(dimissionNotice, Integer
						.parseInt(cpage), pageSize);
		dimissionNotices = (List<DimissionNotice>) map.get(1);// 显示争议单列表
		if (dimissionNotices != null && dimissionNotices.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimission_XieYiAction_showListNotice.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dimissionNotice_show";
	}

	// 添加方法
	public String addNotice() {
		errorMessage = dimissionXieYiServer.addDimissionNotice(dimissionLog,
				dimissionNotice);
		if ("true".equals(errorMessage)) {
			url = "dimission_XieYiAction_showListNotice.action";
			errorMessage = "添加成功";
			return "error";
		} else {
			errorMessage = "添加失败";
			return "error";
		}
	}

	// get set 方法
	public Dimission_XieYiServer getDimissionXieYiServer() {
		return dimissionXieYiServer;
	}

	public void setDimissionXieYiServer(
			Dimission_XieYiServer dimissionXieYiServer) {
		this.dimissionXieYiServer = dimissionXieYiServer;
	}

	public Dimission_XieYi getDimissionXieYi() {
		return dimissionXieYi;
	}

	public void setDimissionXieYi(Dimission_XieYi dimissionXieYi) {
		this.dimissionXieYi = dimissionXieYi;
	}

	public DimissionLog getDimissionLog() {
		return dimissionLog;
	}

	public void setDimissionLog(DimissionLog dimissionLog) {
		this.dimissionLog = dimissionLog;
	}

	public List<Dimission_XieYi> getDimissionXieYis() {
		return dimissionXieYis;
	}

	public void setDimissionXieYis(List<Dimission_XieYi> dimissionXieYis) {
		this.dimissionXieYis = dimissionXieYis;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}

	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
	}

	public List<Provision> getProvisionlist() {
		return provisionlist;
	}

	public void setProvisionlist(List<Provision> provisionlist) {
		this.provisionlist = provisionlist;
	}

	public String[] getProContent() {
		return proContent;
	}

	public void setProContent(String[] proContent) {
		this.proContent = proContent;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public DimissionNotice getDimissionNotice() {
		return dimissionNotice;
	}

	public void setDimissionNotice(DimissionNotice dimissionNotice) {
		this.dimissionNotice = dimissionNotice;
	}

	public List<DimissionNotice> getDimissionNotices() {
		return dimissionNotices;
	}

	public void setDimissionNotices(List<DimissionNotice> dimissionNotices) {
		this.dimissionNotices = dimissionNotices;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
