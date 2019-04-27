package com.task.action.fin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.fin.budget.SubjectBudgetServer;
import com.task.entity.caiwu.baobiao.BalanceSheet;
import com.task.entity.caiwu.baobiao.CashFlow;
import com.task.entity.caiwu.baobiao.Management;
import com.task.entity.caiwu.baobiao.ProfitSheet;
import com.task.entity.caiwu.baobiao.XsfyjCwfyMx;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.fin.budget.SubBudgetRateMonth;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * 预算科目action
 * 
 * @author jhh
 * 
 */
@SuppressWarnings("serial")
public class SubjectBudgetAction extends ActionSupport {
	private SubjectBudgetServer subjectBudgetServer;
	private SubBudgetRate subBudgetRate;

	@SuppressWarnings("unchecked")
	private List list;
	private List<SubBudgetRate> sbRateList;
	private List<SubBudgetRateMonth> ListsubMonths;
	private String tag;
	private String errorMessage;
	private String jisungs;
	private BalanceSheet balance;// 资产负债表对象
	private ProfitSheet profit;// 利润表
	private CashFlow cashflow;// 现金流动表
	private Management ma;// 管理费用明细表
	private XsfyjCwfyMx xcm;// 销售费用及财务费用明细表
	private File addfile;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String tage;
	private int pageSize = 15;
	private Integer id;// id
	private Integer[] id2;

	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private String pageStauts;//
	private String months;

	/* 查看科目比例明细* */
	public String findSubBudgetRate() {
		return null;
	}

	public String toFindKemu(){
		return "subject_manage";
	}
	
	/***
	 * 查询所有科目信息(输出Json)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAllSBRate() {
		try {
			if("all".equals(tage)||"z".equals(tage))
				sbRateList = subjectBudgetServer.findAllSubBudgetByUser(tage);
			else 
				sbRateList = subjectBudgetServer.findAllSubBudgetByUser();
			List newList = new ArrayList();
			for (SubBudgetRate subBudgetRate : sbRateList) {
				subBudgetRate.setSubBudgetRateSet(null);
				subBudgetRate.setSubBudgetRate(null);
				subBudgetRate.setDeptNumberSet(null);
				newList.add(subBudgetRate);
			}
			MKUtil.writeJSON(newList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 添加科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	public String addSubBudgetRate() {
		boolean bool = subjectBudgetServer.addSubBudgetRate(subBudgetRate);
		subBudgetRate.setSubBudgetRateSet(null);
		subBudgetRate.setSubBudgetRate(null);
		subBudgetRate.setDeptNumberSet(null);
		MKUtil.writeJSON(bool, subBudgetRate.getName(), subBudgetRate);
		return null;
	}

	/**
	 * 修改科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	public String updateSubBudgetRate() {
		Object[] object = subjectBudgetServer.updateSubBudgetRate(
				subBudgetRate, id);
		subBudgetRate.setSubBudgetRateSet(null);
		subBudgetRate.setSubBudgetRate(null);
		subBudgetRate.setDeptNumberSet(null);
		MKUtil
				.writeJSON((Boolean) object[0], (String) object[1],
						subBudgetRate);
		return null;
	}

	/**
	 * 删除科目
	 * 
	 * @param subBudgetRate
	 * @return
	 */
	public String delSubBudgetRate() {
		SubBudgetRate oldsubBudgetRate = subjectBudgetServer
				.findSubBudgetRateById(id);
		boolean bool = false;
		if (oldsubBudgetRate != null) {
			bool = subjectBudgetServer.delSubBudgetRate(oldsubBudgetRate);
			if (bool && oldsubBudgetRate.getBelongLayer() > 1) {
				subBudgetRate = subjectBudgetServer
						.findSubBudgetRateById(oldsubBudgetRate.getFatherId());
				subBudgetRate.setSubBudgetRateSet(null);
				subBudgetRate.setSubBudgetRate(null);
				subBudgetRate.setDeptNumberSet(null);
			} else {
				subBudgetRate = null;
			}
		}
		MKUtil.writeJSON(bool, oldsubBudgetRate.getName(), subBudgetRate);
		return null;
	}

	/***
	 * 查询所有部门匹配科目信息(输出Json)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSBRateFroDept() {
		// 更新对应部门绑定的科目为选中
		subjectBudgetServer.updateSBRateFroDept(id);
		// 查询所有科目
		sbRateList = subjectBudgetServer.findAllSubBudget();
		List newList = new ArrayList();
		for (SubBudgetRate subBudgetRate : sbRateList) {
			subBudgetRate.setSubBudgetRateSet(null);
			subBudgetRate.setSubBudgetRate(null);
			subBudgetRate.setDeptNumberSet(null);
			newList.add(subBudgetRate);
		}
		MKUtil.writeJSON(newList);
		return null;
	}

	/***
	 * 查询所有部门匹配科目信息(输出Json)
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findSBRateFroUser() {
		// 更新对应部门绑定的科目为选中
		subjectBudgetServer.updateSBRateFroUsers(id);
		// 查询所有科目
		sbRateList = subjectBudgetServer.findAllSubBudget();
		List newList = new ArrayList();
		for (SubBudgetRate subBudgetRate : sbRateList) {
			subBudgetRate.setSubBudgetRateSet(null);
			subBudgetRate.setSubBudgetRate(null);
			subBudgetRate.setDeptNumberSet(null);
			newList.add(subBudgetRate);
		}
		MKUtil.writeJSON(newList);
		return null;
	}

	/***
	 * 科目与部门绑定
	 * 
	 * @return
	 */
	public String updateSubDept() {
		Boolean bool = subjectBudgetServer.updateSubDept(id, subBudgetRate
				.getId());
		MKUtil.writeJSON(bool);
		return null;
	}

	/**
	 * 按月份 查询资产负债表信息;
	 * 
	 * @return
	 */
	public String findbalanceByMonths() {
		balance = subjectBudgetServer.findbalanceByMonths(months);
		if(balance!=null){
			months = balance.getMonths();
		}
		return "balance_show";
	}

	/**
	 * 按月份 查询利润表信息;
	 * 
	 * @return
	 */
	public String findprofitByMonths() {
		profit = subjectBudgetServer.findprofitByMonths(months);
		if(profit!=null){
			months = profit.getMonths();
		}
		return "Profit_show";
	}

	/**
	 * 按月份 查询资金流动表信息;
	 * 
	 * @return
	 */
	public String findcashflowByMonths() {
		cashflow = subjectBudgetServer.findcashflowByMonths(months);
		if(cashflow!=null){
			months = cashflow.getMonths();
		}
		return "CashFlow_show";
	}

	/**
	 * 按月份 查询管理费用明细表信息;
	 * 
	 * @return
	 */
	public String findMaByMonths() {
		ma = subjectBudgetServer.findMaByMonths(months);
		return "Management";
	}

	/**
	 * 按月份 查询销售费用及财务费用明细表信息;
	 * 
	 * @return
	 */
	public String findXcmByMonths() {
		if (months == null || months.length() == 0) {
			months = Util.getDateTime("yyy-MM");
		}
		xcm = subjectBudgetServer.findXcmByMonths(months);
		return "xcm_show";
	}

	/**
	 * 跳往绑定用户与科目页面
	 * 
	 * @return
	 */
	public String toUserAndSubDpetjsp() {
		return "userandsubdept";
	}

	/**
	 * 查询所有科目并标记用户绑定的科目
	 */
	public void findAllSubDpetjsp() {
		list = subjectBudgetServer.findAllSubDpetjsp(id);
		MKUtil.writeJSON(list);
	}

	public void bdAndqxUser() {
		Boolean bool = subjectBudgetServer.bdAndqxUser(id, subBudgetRate
				.getId());
		MKUtil.writeJSON(bool);
	}

	// 导入月度科目余额表
	public String daoru() {
		errorMessage = subjectBudgetServer.daoru(addfile, months);
		return "error";
	}

	// 列出计算公式和计算科目明细;
	public String getjisunGS() {
		Object[] obj = subjectBudgetServer.getjisunGS(tag, months);
		jisungs = (String) obj[0];
		ListsubMonths = (List<SubBudgetRateMonth>) obj[1];
		return "jisuangs";
	}

	// 科目余额月份记录显示;
	public String findSubMonths() {
		try {
			ListsubMonths = subjectBudgetServer.findSubMonths(months);
			if (ListsubMonths != null && ListsubMonths.size() > 0) {
				months = ListsubMonths.get(0).getBookKDate();
			}
			MKUtil.writeJSON(ListsubMonths);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void getjisunGS0() {
		try {
			Object[] obj = subjectBudgetServer.getjisunGS(tag, months);
			List doubleList = (List) obj[2];
			MKUtil.writeJSON(doubleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 月末结转，本期末改为下月期初;发生额改为0；
	public String jiezhuan() {
		subjectBudgetServer.jiezhuang();
		errorMessage = "结转完成";
		return ERROR;
	}

	// 得到下级科目编号
	public void getNextSubNumber() {
		try {
			String subNumber = subjectBudgetServer.getNextSubNumber(id);
			MKUtil.writeJSON(subNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 科目余额与用户绑定或解绑
	public void SubBangUsers() {
		try {
			Boolean bool = subjectBudgetServer.SubBangUsers(id, id2);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SubjectBudgetServer getSubjectBudgetServer() {
		return subjectBudgetServer;
	}

	public void setSubjectBudgetServer(SubjectBudgetServer subjectBudgetServer) {
		this.subjectBudgetServer = subjectBudgetServer;
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
		return errorMessage;
	}

	public void setMessage(String message) {
		this.errorMessage = message;
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

	public SubBudgetRate getSubBudgetRate() {
		return subBudgetRate;
	}

	public void setSubBudgetRate(SubBudgetRate subBudgetRate) {
		this.subBudgetRate = subBudgetRate;
	}

	public List<SubBudgetRate> getSbRateList() {
		return sbRateList;
	}

	public void setSbRateList(List<SubBudgetRate> sbRateList) {
		this.sbRateList = sbRateList;
	}

	public BalanceSheet getBalance() {
		return balance;
	}

	public void setBalance(BalanceSheet balance) {
		this.balance = balance;
	}

	public ProfitSheet getProfit() {
		return profit;
	}

	public void setProfit(ProfitSheet profit) {
		this.profit = profit;
	}

	public CashFlow getCashflow() {
		return cashflow;
	}

	public void setCashflow(CashFlow cashflow) {
		this.cashflow = cashflow;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public File getAddfile() {
		return addfile;
	}

	public void setAddfile(File addfile) {
		this.addfile = addfile;
	}

	public String getJisungs() {
		return jisungs;
	}

	public void setJisungs(String jisungs) {
		this.jisungs = jisungs;
	}

	public String getPageStauts() {
		return pageStauts;
	}

	public void setPageStauts(String pageStauts) {
		this.pageStauts = pageStauts;
	}

	public List<SubBudgetRateMonth> getListsubMonths() {
		return ListsubMonths;
	}

	public void setListsubMonths(List<SubBudgetRateMonth> listsubMonths) {
		ListsubMonths = listsubMonths;
	}

	public Integer[] getId2() {
		return id2;
	}

	public void setId2(Integer[] id2) {
		this.id2 = id2;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Management getMa() {
		return ma;
	}

	public void setMa(Management ma) {
		this.ma = ma;
	}

	public XsfyjCwfyMx getXcm() {
		return xcm;
	}

	public void setXcm(XsfyjCwfyMx xcm) {
		this.xcm = xcm;
	}

	public String getTage() {
		return tage;
	}

	public void setTage(String tage) {
		this.tage = tage;
	}

}
