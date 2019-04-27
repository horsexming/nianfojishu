package com.task.action.fin;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.fin.budget.SaleBudgetServer;
import com.task.entity.Price;
import com.task.entity.fin.budget.SaleBudget;
import com.task.entity.fin.budget.SaleBudgetDetail;
import com.task.util.MKUtil;

/**
 * 销售收入action
 * 
 * @author jhh
 * 
 */
public class SaleBudgetAction extends ActionSupport {
	private SaleBudgetServer saleBudgetServer;
	private SaleBudget saleBudget;// 销售收总表
	private SaleBudgetDetail sbdetail;// 收入明细
	private Price price;// 产品单价信息
	private Integer[] priceSelect;// 选择件号id
	private Float[] count;// 单个产品预测产量
	private String[] isInclud;// 是否计入预算总额
	private Float[] prices;// 含税单价
	private List<SaleBudgetDetail> listDetail;
	private List list;
	private String tag;
	private String powerTag;// 权限标签
	private String message;
	private HttpServletResponse response;
	// 分页
	private String cpage = "1";

	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private Integer sbId;// 明细ID
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String planMonth;// 计划月份
	private String errorMessage;
	private Integer[] detailSelect;// 选择补打数组,审批数组

	// 准备添加销售收入明细,获得计划月份
	public String prepareSave() {
		Object prea[] = saleBudgetServer.SaveplanMonth();
		String planMon = (String) prea[1];
		String isExa = (String) prea[2];
		SaleBudget sbt = (SaleBudget) prea[0];
		this.id = sbt.getId();
		if ("yes".equals(isExa)) {// 存在记录跳转到历史记录页面
			return "exaprepareSave";
		} else {// 不存在跳转到新添页面
			return "newprepareSave";
		}
	}

	// 单个月份的销售收入明细
	public String findOneBudget() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != sbdetail) {
			request.getSession().setAttribute("sbdetail", sbdetail);
		} else {
			sbdetail = (SaleBudgetDetail) request.getSession().getAttribute(
					"sbdetail");
		}
		if ("find".equals(tag) || "update".equals(tag)) {
			sbdetail = null;
		}
		saleBudget = saleBudgetServer.getSaleBudgetById(id);
		list = saleBudgetServer.findBudgetDetailBysbt(sbdetail, id);

		return "findDetailBysbt";
	}

	// 选择产品信息（条件查询）
	public String findAllPruduct() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != id) {
			request.getSession().setAttribute("id", id);
		} else {
			id = (Integer) request.getSession().getAttribute("id");
		}
		this.pageSize = 20;
		this.setUrl("saleBudgetAction!findAllPruduct.action");

		if (null != price) {
			request.getSession().setAttribute("price", price);
		} else {
			price = (Price) request.getSession().getAttribute("price");
		}
		Object[] obj = saleBudgetServer.findSaleBudgetProduct(price, Integer
				.parseInt(cpage), pageSize, this.id);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findAllProductOK";
	}

	// 选择预算产品
	public String findBudgetPruduct() {
		if (priceSelect != null && priceSelect.length > 0) {
			Object[] object = saleBudgetServer
					.querySelectedProduct(priceSelect);
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (object[1] != null) {
					errorMessage = (String) object[1];
				}
				return "priceNum_selected";
			}
		}
		return null;
	}

	// 保存所选产品(计算总额)
	public String saveBudgetProduct() {
		if (priceSelect != null && count != null && id != null) {
			if (saleBudgetServer.saveDetail(priceSelect, count, isInclud, id)) {
				this.message = "添加成功！";
				return "saveSelectedPOK";
			} else {
				this.message = "添加失败！";
				return "saveSelectedPERROR";
			}

		}

		return null;
	}

	// 根据ID查找明细
	public String getDetailById() {
		sbdetail = saleBudgetServer.getDetailById(sbId, tag);
		return "findDetailOK";
	}

	// 更新单个明细
	public String updateDetail() {
		MKUtil.writeJSON(saleBudgetServer.updateDetailById(sbdetail), "", null);
		return null;
	}

	// 根据ID更新saleBudget
	public String updateBudgetById() {
		if ("yes".equals(tag) || "no".equals(tag)) {
			powerTag = "manager";
		} else {
			powerTag = "";
		}
		if (saleBudgetServer.updateSaleBudgetById(id, tag)) {
			return "ExamBudgetOK";
		}
		return ERROR;
	}

	// 删除单条明细
	public String deleteDetailById() {
		sbdetail = saleBudgetServer.getDetailById(sbId, tag);
		id = sbdetail.getSaleBudget().getId();
		if (saleBudgetServer.deleteDetailById(sbdetail)) {
			return "deleteDetailOK";
		}
		return null;
	}

	/***
	 * 科目销售总额计算
	 * 
	 * @return
	 */
	public String saveBudget() {
		saleBudget = saleBudgetServer.getSaleBudgetById(id);
		if (saleBudgetServer.subSaleBudget(saleBudget)) {
			return "subBudgetOK";
		}
		return null;
	}

	// 查询管理所有销售收入明细
	public String findAllBudget() {
		this.id = id;
		this.pageSize = 15;
		this.setUrl("saleBudgetAction!findAllBudget.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != saleBudget) {
			request.getSession().setAttribute("saleBudget", saleBudget);
		} else {
			saleBudget = (SaleBudget) request.getSession().getAttribute(
					"saleBudget");
		}
		Object[] obj = saleBudgetServer.findSaleBudget(saleBudget, Integer
				.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBudgetOK";
	}

	// 下拉选项
	public String selectStyle() {
		String message = saleBudgetServer.findbudgetStyle(tag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/***
	 * 更新月度销售汇总
	 * 
	 * @return
	 */
	public String subSaleBudget() {
		SaleBudget saleBudget = saleBudgetServer.getSaleBudgetById(id);
		boolean bool = saleBudgetServer.subSaleBudget(saleBudget);
		if (bool) {
			errorMessage = "处理月度销售汇总成功!";
		}
		return ERROR;
	}

	// 月度销售收入审核列表
	public String findExamList() {
		Object[] obj = saleBudgetServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("saleBudgetAction!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamList";
	}

	// 审批(通过、驳回)
	public String updateExamBargain() {
		try {
			if (saleBudgetServer.updateExamBonus(detailSelect, tag)) {
				return "updateExamBudget";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	public SaleBudgetServer getSaleBudgetServer() {
		return saleBudgetServer;
	}

	public void setSaleBudgetServer(SaleBudgetServer saleBudgetServer) {
		this.saleBudgetServer = saleBudgetServer;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<SaleBudgetDetail> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<SaleBudgetDetail> listDetail) {
		this.listDetail = listDetail;
	}

	public String getPlanMonth() {
		return planMonth;
	}

	public void setPlanMonth(String planMonth) {
		this.planMonth = planMonth;
	}

	public SaleBudget getSaleBudget() {
		return saleBudget;
	}

	public void setSaleBudget(SaleBudget saleBudget) {
		this.saleBudget = saleBudget;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Integer[] getPriceSelect() {
		return priceSelect;
	}

	public void setPriceSelect(Integer[] priceSelect) {
		this.priceSelect = priceSelect;
	}

	public SaleBudgetDetail getSbdetail() {
		return sbdetail;
	}

	public void setSbdetail(SaleBudgetDetail sbdetail) {
		this.sbdetail = sbdetail;
	}

	public Float[] getCount() {
		return count;
	}

	public void setCount(Float[] count) {
		this.count = count;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Float[] getPrices() {
		return prices;
	}

	public void setPrices(Float[] prices) {
		this.prices = prices;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Integer getSbId() {
		return sbId;
	}

	public void setSbId(Integer sbId) {
		this.sbId = sbId;
	}

	public String getPowerTag() {
		return powerTag;
	}

	public void setPowerTag(String powerTag) {
		this.powerTag = powerTag;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public String[] getIsInclud() {
		return isInclud;
	}

	public void setIsInclud(String[] isInclud) {
		this.isInclud = isInclud;
	}

}
