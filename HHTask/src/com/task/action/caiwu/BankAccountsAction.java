package com.task.action.caiwu;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.BankAccountsServer;
import com.task.entity.caiwu.BankAccounts;
import com.task.entity.caiwu.CwPingZheng;
import com.task.entity.caiwu.CwZWAndSbr;
import com.task.entity.caiwu.CwZhangWu;

public class BankAccountsAction extends ActionSupport {
	private BankAccountsServer bankAccountsServer;// Server层
	private BankAccounts bankAccounts;// 对象
	private CwPingZheng cwPingZheng;
	private CwZhangWu cwZhangWu;
	private List<BankAccounts> bankAccountsList;// 集合
	private List<CwPingZheng> cwPingZhengList;// 集合
	private List<Object> list;// 集合
	private List<CwZWAndSbr> cwZWAndSbrList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	/***
	 * 添加银行账户信息
	 * 
	 * @return
	 */
	public String addBankAccounts() {
		return "";
	}

	/****
	 * 查询银行账户信息
	 * 
	 * @return
	 */
	public String findWorkLogByCondition() {
		if (bankAccounts != null) {
			ActionContext.getContext().getSession().put("bankAccounts",
					bankAccounts);
		} else {
			bankAccounts = (BankAccounts) ActionContext.getContext()
					.getSession().get("bankAccounts");
		}
		Object[] object = bankAccountsServer.findWorkLogByCondition(
				bankAccounts, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			bankAccountsList = (List<BankAccounts>) object[0];
			if (bankAccountsList != null && bankAccountsList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("BankAccountsAction!findWorkLogByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "bank_show";
	}

	/**
	 * 查询凭证
	 * 
	 * @return
	 */
	public String findPZByCondition() {
		if (cwPingZheng != null) {
			ActionContext.getContext().getSession().put("cwPingZheng",
					cwPingZheng);
		} else {
			cwPingZheng = (CwPingZheng) ActionContext.getContext().getSession()
					.get("cwPingZheng");
		}
		Object[] object = bankAccountsServer.findPZByCondition(cwPingZheng,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			cwPingZhengList = (List<CwPingZheng>) object[0];
			if (cwPingZhengList != null && cwPingZhengList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus == null) {
					pageStatus = "";
				}
				this
						.setUrl("BankAccountsAction!findPZByCondition.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "cw_pz_show";
	}

	/***
	 * 查询凭证，录入
	 * 
	 * @return
	 */
	public String findPzToAdd() {
		cwPingZheng = bankAccountsServer.findPZ(id);
		return "cw_pz_luru";
	}

	/***
	 * 添加财务报表
	 * 
	 * @return
	 */
	public String addCwbb() {
		try {
			bankAccountsServer.addCwbb(cwZhangWu, cwZWAndSbrList);
			errorMessage = "录入报表成功!";
			url = "BankAccountsAction!findCwBb.action";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "录入报表失败!错误原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 查询财务报表
	 * 
	 * @return
	 */
	public String findCwBb() {
		list = bankAccountsServer.findCwZw();
		return "cw_cwbb_show";
	}

	public BankAccountsServer getBankAccountsServer() {
		return bankAccountsServer;
	}

	public void setBankAccountsServer(BankAccountsServer bankAccountsServer) {
		this.bankAccountsServer = bankAccountsServer;
	}

	public BankAccounts getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(BankAccounts bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public List<BankAccounts> getBankAccountsList() {
		return bankAccountsList;
	}

	public void setBankAccountsList(List<BankAccounts> bankAccountsList) {
		this.bankAccountsList = bankAccountsList;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
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

	public CwPingZheng getCwPingZheng() {
		return cwPingZheng;
	}

	public void setCwPingZheng(CwPingZheng cwPingZheng) {
		this.cwPingZheng = cwPingZheng;
	}

	public List<CwPingZheng> getCwPingZhengList() {
		return cwPingZhengList;
	}

	public void setCwPingZhengList(List<CwPingZheng> cwPingZhengList) {
		this.cwPingZhengList = cwPingZhengList;
	}

	public List<CwZWAndSbr> getCwZWAndSbrList() {
		return cwZWAndSbrList;
	}

	public void setCwZWAndSbrList(List<CwZWAndSbr> cwZWAndSbrList) {
		this.cwZWAndSbrList = cwZWAndSbrList;
	}

	public CwZhangWu getCwZhangWu() {
		return cwZhangWu;
	}

	public void setCwZhangWu(CwZhangWu cwZhangWu) {
		this.cwZhangWu = cwZhangWu;
	}

}
