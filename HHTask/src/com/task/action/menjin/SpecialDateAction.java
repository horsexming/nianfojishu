package com.task.action.menjin;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.SpecialDateServer;
import com.task.Server.menjin.ToolCabineServer;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.DrinksType;
import com.task.entity.menjin.ReceiveCabinet;
import com.task.entity.menjin.SpecialDate;
import com.task.entity.menjin.ToolCabine;
import com.task.entity.onemark.OneLight;

/**
 * 工具柜Action层 2016-06-08
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class SpecialDateAction {
	private SpecialDateServer specialDateServer;// Server层
	private SpecialDate specialDate;// 对象
	private List<SpecialDate> specialDateList;//
	private List<BanCi> banciList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private String pageStatus;// 页面状态// 绑定功能

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String Test() {
		return "error";
	}

	public String toadd() {
		banciList = specialDateServer.findBanCi();
		return "specialDate_add";
	}

	// 分页显示
	// 显示查询内容
	public String showList() {
		if (specialDate != null) {
			ActionContext.getContext().getSession().put("SpecialDate",
					specialDate);
		} else {// 用来保持分页时带着查询条件
			specialDate = (SpecialDate) ActionContext.getContext().getSession()
					.get("SpecialDate");
		}
		Map<Integer, Object> map = specialDateServer.findSpecialDate(specialDate,
				Integer.parseInt(cpage), pageSize);
		specialDateList = (List<SpecialDate>) map.get(1);// 显示面试单列表
		if (specialDateList != null && specialDateList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("SpecialDateAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "specialDate_show";
	}

	// 添加方法
	public String add() {
		if (specialDate != null) {
			errorMessage = specialDateServer.addSpecialDate(specialDate);
			if ("添加成功！".equals(errorMessage))
				url = "SpecialDateAction_showList.action";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (specialDate.getId() != null && specialDate.getId() > 0
				&& specialDate != null) {
			specialDate = specialDateServer.byIdSpecialDate(specialDate.getId());
			if (specialDate != null)
				return "specialDate_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String update() {
		errorMessage = specialDateServer.updateSpecialDate(specialDate);
		if ("修改成功！".equals(errorMessage))
			url = "SpecialDateAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = specialDateServer.deleteSpecialDate(id);
			if ("删除成功！".equals(errorMessage))
				url = "SpecialDateAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	// 构造方法
	public SpecialDateServer getSpecialDateServer() {
		return specialDateServer;
	}

	public void setSpecialDateServer(SpecialDateServer specialDateServer) {
		this.specialDateServer = specialDateServer;
	}

	public SpecialDate getSpecialDate() {
		return specialDate;
	}

	public void setSpecialDate(SpecialDate specialDate) {
		this.specialDate = specialDate;
	}

	public List<SpecialDate> getSpecialDateList() {
		return specialDateList;
	}

	public void setSpecialDateList(List<SpecialDate> specialDateList) {
		this.specialDateList = specialDateList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<BanCi> getBanciList() {
		return banciList;
	}

	public void setBanciList(List<BanCi> banciList) {
		this.banciList = banciList;
	}


}
