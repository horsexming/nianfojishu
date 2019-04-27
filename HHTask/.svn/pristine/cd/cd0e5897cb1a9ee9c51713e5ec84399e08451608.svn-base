package com.task.action.renshi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.renshi.DimissionLogServer;
import com.task.Server.renshi.Dimission_ZhengYiServer;
import com.task.entity.WageStandard;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.renshi.Dimission_ZhengYi;

public class Dimission_ZhengYiAction {
	private Dimission_ZhengYiServer dimissionZhengYiServer;// 争议单服务；
	private DimissionLogServer dimissionLogServer;
	private Dimission_ZhengYi dimissionZhengYi;// 争议单对象；
	private DimissionLog dimissionLog;// 离职申请单对象
	private WageStandard standard;// 工资模板
	private Dimission_Handover dimissionHandover;// 离职交接单对象
	private List<Dimission_ZhengYi> dimissionZhengYis;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String test() {
		return "error";
	}

	//查询工资模板
	public String gzMuban(){
		standard = dimissionLogServer.getWageStandardByid(id);
		if (standard!=null) {
			return "dimission_gongzimuban";
		}else {
			return "error";
		}
	}
	
	// 显示查询内容
	@SuppressWarnings("unchecked")
	public String showList() {
		if (dimissionZhengYi != null) {
			ActionContext.getContext().getSession().put("dimissionZhengYi1",
					dimissionZhengYi);
		} else {// 用来保持分页时带着查询条件
			dimissionZhengYi = (Dimission_ZhengYi) ActionContext.getContext()
					.getSession().get("dimissionZhengYi1");
		}
		Map<Integer, Object> map = dimissionZhengYiServer
				.findDimission_ZhengYisByCondition(dimissionZhengYi, Integer
						.parseInt(cpage), pageSize);
		dimissionZhengYis = (List<Dimission_ZhengYi>) map.get(1);// 显示争议单列表
		if (dimissionZhengYis != null && dimissionZhengYis.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("dimission_ZhengYiAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "dimission_show_1";
	}

	// 跳转到添加页面
	public String toadd() {
		DimissionLog dimissionLog1 = dimissionLogServer
				.getDimissionLogById(dimissionLog.getId());
		if (dimissionLog1 != null) {
			dimissionLog = dimissionLog1;
			Dimission_Handover dimissionHandover1 = dimissionLogServer
					.getDimission_HandoverByid(dimissionLog.getId());
			if (dimissionHandover1 != null) {
				dimissionHandover = dimissionHandover1;
			}
			return "dimission_zhengyi_add";
		} else {
			errorMessage = "没有该申请单,请检查后重试!";
			return "error";
		}

	}

	// 添加方法
	public String add() {
		errorMessage = dimissionZhengYiServer.addDimission_ZhengYi(
				dimissionLog, dimissionZhengYi);
		if ("true".equals(errorMessage)) {
			successMessage = "addsuccess";
			return "dim_add_succ_1";
		} else {
			return toadd();
		}
	}

	// 跳转到预览打印页面
	public String toselect() {
		Dimission_ZhengYi dimissionLZhengYi2 = dimissionZhengYiServer
				.getDimission_ZhengYiById(dimissionZhengYi.getId());
		if (dimissionLZhengYi2 != null) {
			dimissionZhengYi = dimissionLZhengYi2;
			return "dimission_zhengyi_select";
		} else {
			errorMessage = "seleteNot";
		}
		return "dim_add_succ_1";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		Dimission_ZhengYi dimissionLZhengYi2 = dimissionZhengYiServer
				.getDimission_ZhengYiById(dimissionZhengYi.getId());
		if (dimissionLZhengYi2 != null) {
			dimissionZhengYi = dimissionLZhengYi2;
			return "dimission_zhengyi_update";
		} else {
			errorMessage = "修改失败，不存在该离职单";
		}
		return "dim_add_succ_1";
	}

	// 修改方法
	public String update() {
		boolean b = dimissionZhengYiServer
				.updateDimission_ZhengYi(dimissionZhengYi);
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
		boolean b = dimissionZhengYiServer
				.deleteDimission_ZhengYi(dimissionZhengYi.getId());
		if (b) {
			successMessage = "deletesuccess";
		} else {
			errorMessage = "删除失败";
		}
		dimissionLog = null;
		return "dim_add_succ_1";
	}

	// get set 方法

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

	public DimissionLog getDimissionLog() {
		return dimissionLog;
	}

	public void setDimissionLog(DimissionLog dimissionLog) {
		this.dimissionLog = dimissionLog;
	}

	public Dimission_Handover getDimissionHandover() {
		return dimissionHandover;
	}

	public void setDimissionHandover(Dimission_Handover dimissionHandover) {
		this.dimissionHandover = dimissionHandover;
	}

	public Dimission_ZhengYiServer getDimissionZhengYiServer() {
		return dimissionZhengYiServer;
	}

	public void setDimissionZhengYiServer(
			Dimission_ZhengYiServer dimissionZhengYiServer) {
		this.dimissionZhengYiServer = dimissionZhengYiServer;
	}

	public Dimission_ZhengYi getDimissionZhengYi() {
		return dimissionZhengYi;
	}

	public void setDimissionZhengYi(Dimission_ZhengYi dimissionZhengYi) {
		this.dimissionZhengYi = dimissionZhengYi;
	}

	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}

	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
	}

	public List<Dimission_ZhengYi> getDimissionZhengYis() {
		return dimissionZhengYis;
	}

	public void setDimissionZhengYis(List<Dimission_ZhengYi> dimissionZhengYis) {
		this.dimissionZhengYis = dimissionZhengYis;
	}

	public WageStandard getStandard() {
		return standard;
	}

	public void setStandard(WageStandard standard) {
		this.standard = standard;
	}

}
