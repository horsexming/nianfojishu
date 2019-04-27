package com.task.action.fin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.YusuantianbaobiaoServer;
import com.task.Server.fin.budget.SubMonthMoneyServer;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.fin.budget.SubMonthMoney;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * 部门月度科目预算金额action
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class SubMonthMoneyAction extends ActionSupport {
	private YusuantianbaobiaoServer yusuantianbaobiaoServer;
	private SubMonthMoneyServer submmServer;
	private SubMonthMoney subMonthMoney;
	private DeptMonthBudget deptMonthBudget;
	private List list;
	private List<SubMonthMoney> smmList;
	private String tag;
	private String message;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer idyu;
	private Integer id;// id
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String pageStatus;
	private String errorMessage;
	private String successMessage;
	private String budgetMonth;
	private Integer[] submmIds;// 填报费用id
	private Integer[] detailSelect;// 选择补打数组,审批数组

	/***
	 * 查询所有月度科目预算总金额(根层)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSmmByCondition() {
		if (subMonthMoney != null) {
			ActionContext.getContext().getSession().put("subMonthMoney",
					subMonthMoney);
		} else {
			subMonthMoney = (SubMonthMoney) ActionContext.getContext()
					.getSession().get("subMonthMoney");
		}
		Object[] object = submmServer.saveSmmByCondition(subMonthMoney, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			smmList = (List<SubMonthMoney>) object[0];
			if (smmList != null && smmList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null && pageStatus.length() > 0) {
					this
							.setUrl("SubMonthMoneyAction!findSmmByCondition.action?pageStatus="
									+ pageStatus);
				} else {
					this
							.setUrl("SubMonthMoneyAction!findSmmByCondition.action");
				}
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "subMonthMoney_List";
	}

	// 到处Excl
	public String exportExcel() {
		if (subMonthMoney != null) {
			submmServer.exportExcel(subMonthMoney.getBudgetMonth());
		}
		return null;
	}

	// 导处Excl
	public String exportExcel1() {
		if (deptMonthBudget != null) {
			submmServer.exportExcel1(deptMonthBudget.getBudgetMonth());
		}
		return null;
	}

	/***
	 * 通过rootId查询属性结构
	 * 
	 * @return
	 */
	public String findSummByRootId() {
		// 根据rootId查询科目预算金额结构
		List<SubMonthMoney> smmList = submmServer.findSmmByRootId(id,
				pageStatus);
		List<SubMonthMoney> newList = new ArrayList<SubMonthMoney>();
		for (SubMonthMoney subMonthMoney : smmList) {
			subMonthMoney.setDmBudgetSet(null);
			subMonthMoney.setSmMoneySet(null);
			subMonthMoney.setSubMonthMoney(null);
			newList.add(subMonthMoney);
		}
		// 根据id查询科目预算金额
		SubMonthMoney smMoney = submmServer.findSmmById(id);
		smMoney.setDmBudgetSet(null);
		smMoney.setSmMoneySet(null);

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("smmList", newList);
		maps.put("smMoney", smMoney);
		MKUtil.writeJSON(maps);
		return null;
	}

	/***
	 * 添加部门填报明细
	 * 
	 * @param deptMonthBudget
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addDeptMonthBudget() {
		try {
			// 当前月申报下月计划内预算
			String budgetMonth = deptMonthBudget.getBudgetMonth();
			String lastMonth = Util.getNextMonth("yyyy-MM");

			if (!budgetMonth.equals(lastMonth)) {

			}
			List list = submmServer.findDeptMonthBu(deptMonthBudget
					.getBudgetMonth(), id, deptMonthBudget.getJhStatus());

			if (list != null && list.size() > 0) {
				MKUtil.writeJSON(false, "本月该科目的计划内预算已经填报,请勿重复填报!", null);
			} else {
				boolean bool = submmServer.addDeptMonthBudget(deptMonthBudget,
						id);
				MKUtil.writeJSON(bool, "", null);
			}
			if (deptMonthBudget.getJhStatus().equals("wai")) {
				yusuantianbaobiaoServer.updatezcYusuantianbaobiao(
						deptMonthBudget.getAccountMoney(), idyu, budgetMonth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 查询部门填报明细
	 * 
	 * @param deptMonthBudget
	 * @return
	 */
	public String findDeptMonthBudget() {
		try {
			List<DeptMonthBudget> list = submmServer.findDeptMonthBu(
					budgetMonth, id, null);
			if (list != null && list.size() > 0) {
				List<DeptMonthBudget> newList = new ArrayList<DeptMonthBudget>();
				for (DeptMonthBudget deptMonthBudget : list) {
					deptMonthBudget.setSubMonthMoney(null);
					newList.add(deptMonthBudget);
				}
				MKUtil.writeJSON(false, "", newList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 查询待审核的部门填报信息
	 * 
	 * @return
	 */
	public String findAuditDmB() {
		if (deptMonthBudget != null) {
			ActionContext.getContext().getSession().put("deptMonthBudget",
					deptMonthBudget);
		} else {
			deptMonthBudget = (DeptMonthBudget) ActionContext.getContext()
					.getSession().get("deptMonthBudget");
		}
		String status = "";
		if (deptMonthBudget != null) {
			status = deptMonthBudget.getStatus();
		}
		Object[] object = submmServer.findAuditDmB(deptMonthBudget, Integer
				.parseInt(cpage), pageSize, id, pageStatus);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null && pageStatus.length() > 0) {
					if (deptMonthBudget != null
							&& deptMonthBudget.getStatus() != null
							&& deptMonthBudget.getStatus().length() > 0) {
						this
								.setUrl("SubMonthMoneyAction!findAuditDmB.action?pageStatus="
										+ pageStatus
										+ "&deptMonthBudget.status="
										+ status
										+ "&id=" + id);
					} else {
						this
								.setUrl("SubMonthMoneyAction!findAuditDmB.action?pageStatus="
										+ pageStatus + "&id=" + id);
					}
				} else {
					this
							.setUrl("SubMonthMoneyAction!findAuditDmB.action?deptMonthBudget.status="
									+ status + "&id=" + id);
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if (pageStatus != null && pageStatus.length() > 0)
			return "DeptMonthBudget_find";
		else
			return "DeptMonthBudget_audit";
	}

	/***
	 * 审批操作
	 * 
	 * @param submmIds
	 * @param pageStatus
	 * @return
	 */
	public String updateAudit() {
		boolean bool = submmServer.updateAudit(submmIds, pageStatus);
		if (bool) {
			return "auditSuccess";
		}
		errorMessage = "审批出错,请核查!";
		return ERROR;
	}

	/***
	 * 删除部门填报明细信息
	 * 
	 * @param dmBudget
	 * @return
	 */
	public String delDeptMonthBudget() {
		DeptMonthBudget dmBudget = submmServer.findDeptMonthBudgetById(id);
		if (dmBudget != null) {
			id = dmBudget.getRootId();
			submmServer.delDeptMonthBudget(dmBudget);
			errorMessage = "科目 " + dmBudget.getName() + "的信息已删除!";
			url = "SubMonthMoneyAction!findAuditDmB.action?id=" + id
					+ "&pageStatus=" + pageStatus;
		} else {
			errorMessage = "删除失败!";
		}
		return ERROR;
	}

	/***
	 * 根据id查询
	 * 
	 * @return
	 */
	public String findDmBudget() {
		deptMonthBudget = submmServer.findDeptMonthBudgetById(id);
		if (deptMonthBudget != null) {
			if (pageStatus != null && "print".equals(pageStatus)) {
				return "DeptMonthBudget_print";
			}
			return "DeptMonthBudget_update";
		}
		return ERROR;
	}

	/***
	 * 月度预算审核列表(计划内)
	 * 
	 * @return
	 */
	public String findExamList() {
		if (deptMonthBudget != null) {
			ActionContext.getContext().getSession().put("deptMonthBudget",
					deptMonthBudget);
		} else {
			deptMonthBudget = (DeptMonthBudget) ActionContext.getContext()
					.getSession().get("deptMonthBudget");
		}
		Object[] obj = submmServer.findExamList(Integer.parseInt(cpage),
				pageSize,deptMonthBudget);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("SubMonthMoneyAction!findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamList";
	}

	/***
	 * 月度预算审核列表(计划外)
	 * 
	 * @return
	 */
	public String findExamList1() {
		Object[] obj = submmServer.findExamList1(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("SubMonthMoneyAction!findExamList1.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamList1";
	}

	/***
	 * 审批(通过、驳回)计划内、计划外
	 * 
	 * @return
	 */
	public String updateExamDmBudget() {
		try {
			if (submmServer.updateExamBonus(detailSelect, tag)) {
				if ("0".equals(pageStatus)) {
					// 计划内
					return "updateExamBudget";
				} else {
					// 计划外
					return "updateExamBudget1";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	/***
	 * 修改预算填报信息
	 * 
	 * @return
	 */
	public String updateDmBudget() {
		DeptMonthBudget oldDmBudget = submmServer.findDeptMonthBudgetById(id);
		if (oldDmBudget != null) {
			oldDmBudget.setAuditResult("");
			boolean bool = submmServer.updateDeptMonthBudget(deptMonthBudget,
					oldDmBudget);
			if (bool) {
				deptMonthBudget = oldDmBudget;
				successMessage = "修改成功!";
				return "DeptMonthBudget_update";
			}
			errorMessage = "修改失败!";
		} else {
			errorMessage = "数据异常,不存在您要修改的信息!请检查!";
		}
		return ERROR;
	}

	public SubMonthMoneyServer getSubmmServer() {
		return submmServer;
	}

	public void setSubmmServer(SubMonthMoneyServer submmServer) {
		this.submmServer = submmServer;
	}

	public SubMonthMoney getSubMonthMoney() {
		return subMonthMoney;
	}

	public void setSubMonthMoney(SubMonthMoney subMonthMoney) {
		this.subMonthMoney = subMonthMoney;
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

	public List<SubMonthMoney> getSmmList() {
		return smmList;
	}

	public void setSmmList(List<SubMonthMoney> smmList) {
		this.smmList = smmList;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public DeptMonthBudget getDeptMonthBudget() {
		return deptMonthBudget;
	}

	public void setDeptMonthBudget(DeptMonthBudget deptMonthBudget) {
		this.deptMonthBudget = deptMonthBudget;
	}

	public String getBudgetMonth() {
		return budgetMonth;
	}

	public void setBudgetMonth(String budgetMonth) {
		this.budgetMonth = budgetMonth;
	}

	public Integer[] getSubmmIds() {
		return submmIds;
	}

	public void setSubmmIds(Integer[] submmIds) {
		this.submmIds = submmIds;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public void setYusuantianbaobiaoServer(
			YusuantianbaobiaoServer yusuantianbaobiaoServer) {
		this.yusuantianbaobiaoServer = yusuantianbaobiaoServer;
	}

	public YusuantianbaobiaoServer getYusuantianbaobiaoServer() {
		return yusuantianbaobiaoServer;
	}

	public void setIdyu(Integer idyu) {
		this.idyu = idyu;
	}

	public Integer getIdyu() {
		return idyu;
	}

}
