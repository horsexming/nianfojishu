package com.task.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.ejb.criteria.expression.function.AggregationFunction.COUNT;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ContractServer;
import com.task.Server.InsuranceGoldServer;
import com.task.Server.UserServer;
import com.task.Server.WageStandardServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Contract;
import com.task.entity.InsuranceGold;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.WageStandard;
import com.task.util.MKUtil;
import com.task.util.Util;

@SuppressWarnings("serial")
public class WageStandardAction extends ActionSupport {

	private WageStandardServer wageStandardServer;// 工资模板Server层
	private InsuranceGoldServer InsuranceGoldServer;// 五险一金Server层
	private UserServer userServer;// 用户Server层
	private ContractServer contractServer;// 合同Server层

	private WageStandard wageStandard;// 试用工资模板
	private WageStandard onWorkWs;// 转正工资模板
	private Wage wage;// 工资
	private InsuranceGold insuranceGold;// 保险缴纳比例
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private List<WageStandard> WsList = new ArrayList<WageStandard>();// 工资模版集合
	private List<WageStandard> auditWsList;// 审核工资模版集合
	private List<WageStandard> updateWsList;// 审核工资模版集合
	private List<Contract> provisionList;// 条款集合
	private List list;
	private int id;
	private String code;// 工号
	private String cardId;// 卡号
	private Contract contract;// 合同
	private Integer[] wsId;// 工资标准id数组
	private Float[] gjj;// 工资标准id数组
	private String pageStatus;
	private String contractNumber;// 条码编号

	private Integer userId;//用户ID
	private File addFile;
	private String addmachineContentType;// 文件类型
	private String addmachineFileName;// 文件名称
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加工资模板
	public String addWageStandard() {
		Users user = userServer.findUserByCode(wageStandard.getCode());
		if (user != null) {
			WageStandard newWs = new WageStandard();
			newWs.setCode(user.getCode());
			Object[] object = wageStandardServer.findWSByCondition(newWs, 0, 0);// 查询该员工工资是否存在
			if ((Integer) object[1] == 0) {
				InsuranceGold insuranceGold = InsuranceGoldServer
						.findInsuranceGoldBylc(wageStandard.getLocalOrField(),
								wageStandard.getCityOrCountryside(),
								wageStandard.getPersonClass());
				if (insuranceGold != null) {
					// 薪资协议用工资信息
					boolean bool = wageStandardServer.addWageStandard(
							wageStandard, null, insuranceGold);// 添加试用工资标准
					bool = wageStandardServer.addWageStandard(onWorkWs, null,
							insuranceGold);// 添加转正工资标准

					// 默认工资标准信息
					if (!"试用".equals(user.getOnWork())) {
						wageStandard = onWorkWs;
					}
					wageStandard.setStandardStatus(null);// 设置状态为null(审核通过后改为'默认')
					wageStandard.setProcessStatus("审核");// 流程状态
					wageStandard.setIsOnWork("添加薪资模版信息");// 备注
					bool = wageStandardServer.addWageStandard(wageStandard,
							null, insuranceGold);// 添加工资标准信息

					if (bool) {
						successMessage = "为员工: <font color='red'>"
								+ wageStandard.getUserName()
								+ " </font> 添加工资模板成功!请等待审核!";
						user.getPassword().setUserStatus("完成");// 更改入职流程状态
						bool = userServer.updateUser(user);
						if (bool == false) {
							successMessage += "(修改员工入职流程状态出错!请联系管理员!)";
						}
						try {
							AlertMessagesServerImpl.addAlertMessages("工资模版审核",
									wageStandard.getDept() + "部门的员工"
											+ wageStandard.getUserName()
											+ "新添加薪资信息,请您审核!", "1");
						} catch (Exception e) {
						}
						wageStandard = null;
						return "wageXieYi";
					}
					errorMessage = "为员工:" + wageStandard.getUserName()
							+ "添加工资模板失败,请检查后重试!";
				} else {
					errorMessage = "不存在该员工的保险缴纳比例数据,请检查后重试!";
				}
			} else {
				errorMessage = "该员工的工资标准已经存在!";
			}
		} else {
			errorMessage = "不存在该用户信息!无法录入工资标准信息!";
		}
		return ERROR;
	}

	// 通过工号和卡号查询薪资协议单
	@SuppressWarnings("unchecked")
	public String findWageXieYiByCode() {
		List list = wageStandardServer.findWageXieYiByCode(code, "协议");// 所有工资信息
		if (list != null && list.size() > 0) {
			contract = wageStandardServer.findContractByCode(code);// 合同信息
			for (int i = 0; i < list.size(); i++) {
				WageStandard findWageStandard = (WageStandard) list.get(i);
				if ("试用".equals(findWageStandard.getIsOnWork())) {
					wageStandard = findWageStandard;
				} else if ("正式".equals(findWageStandard.getIsOnWork())) {
					onWorkWs = findWageStandard;
				}
			}
			return "wageXieYi";
		} else {
			errorMessage = "不存在该薪资信息!请检查后重试!";
		}
		return ERROR;
	}

	// 通过工号和卡号查询所有历史工资
	@SuppressWarnings("unchecked")
	public String findWageHistoryByCode() {
		WsList = wageStandardServer.findWageXieYiByCode(code, "历史");// 所有工资信息
		return "wageHistroy";
	}

	// 打印薪资调整通知单
	@SuppressWarnings("unchecked")
	public String findWagePrintByCode() {
		WsList = wageStandardServer.findWageXieYiByCode(code, "历史");// 所有工资信息
		if (WsList != null && WsList.size() > 1) {
			wageStandard = WsList.get(WsList.size() - 2);// 调整前工资
			onWorkWs = WsList.get(WsList.size() - 1);// 调整后工资
			// 查询合同信息，生成合同编号
			contract = wageStandardServer.findContractByCode(code);// 合同信息
			if (contract != null && contract.getContractNumber() != null
					&& contract.getContractNumber().length() > 0) {
				contractNumber = contract.getContractNumber();
			} else {
				contractNumber = onWorkWs.getCode() + onWorkWs.getCardId();
			}
			// 查询薪资调整协议
			contract = contractServer.findContractByCode(code, "完成");
			if (contract != null) {
				provisionList = contractServer.findProvision(contract);
			}

			return "printUdpdateWS";
		} else {
			errorMessage = "该员工尚未调整工资，无需打印!";
		}
		return ERROR;
	}

	// 修改模板
	public String updateWs() {
		WageStandard oldWageStandard = wageStandardServer.findWsById(id);
		if (oldWageStandard != null) {
			boolean bool = wageStandardServer.updateWs(wageStandard,
					oldWageStandard);
			if (bool) {
				ActionContext.getContext().put("pageStatus", "update");
				successMessage = "为员工: <font color='red'>"
						+ wageStandard.getUserName() + " </font> 修改工资模板成功!";
				id = wageStandard.getId();
				pageStatus = "details";
				return "updateWs";
			}
			errorMessage = "为员工: <font color='red'>"
					+ wageStandard.getUserName() + " </font> 修改工资模板失败,请检查后重试!";
		} else {
			errorMessage = "不存在该工资模板,请检查后重试!";
		}
		return ERROR;
	}

	// 查询所有工资模板(分页)
	@SuppressWarnings("unchecked")
	public String findAllWs() {
		WsList = wageStandardServer
				.findAllWs(Integer.parseInt(cpage), pageSize);// 查询所有默认工资模版

		auditWsList = wageStandardServer.findAuditWs(null);// 审核和不同意的模版

		updateWsList = wageStandardServer.findNeedUpdateWs("gztz");// 查询需要调整模版的信息
		if (WsList != null && WsList.size() > 0) {
			int count = wageStandardServer.getCountByAll();
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if (pageStatus != null && pageStatus.length() > 0) {
				this.setUrl("WageStandardAction!findAllWs.action?pageStatus="
						+ pageStatus);
			} else
				this.setUrl("WageStandardAction!findAllWs.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllWsSuccess";
	}

	// 查询所有默认工资模板（公积金调整）
	@SuppressWarnings("unchecked")
	public String findAllWsforGjj() {
		WsList = wageStandardServer.findAllWageStandard();
		pageSize = WsList.size();
		return "wage_gjjWageStandard";
	}

	/***
	 * 通过工号得到历史的工资模版
	 * 
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void findOldWageSByCode() {
		WsList = wageStandardServer.findOldWageSByCode(code);
		MKUtil.writeJSON(WsList);
	}

	/***
	 * 调整公积金
	 * 
	 * @return
	 */
	public String updateWageSGjj() {
		boolean bool = wageStandardServer.updateWageSGjj(WsList);
		if (bool) {
			errorMessage = "调整完成!";
		} else {
			errorMessage = "调整失败!";
		}
		url = "WageStandardAction!findAllWsforGjj.action";
		return ERROR;
	}

	/***
	 * 查询待调整人员的工资模版
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findChageWage() {
		updateWsList = wageStandardServer.findNeedUpdateWs("jstz");// 查询需要调整模版的信息
		auditWsList = wageStandardServer.findNeedAddWs("");// 查询需要添加模版的信息
		pageStatus = "find";
		return "wage_chageJstzWs";
	}

	/***
	 * 修改基数工资
	 * 
	 * @return
	 */
	public String updateJsWs() {
		boolean bool = wageStandardServer.updateJsWs(wageStandard, pageStatus);
		if (bool) {
			errorMessage = "修改成功!";
			url = "WageStandardAction!findChageWage.action";
		} else {
			errorMessage = "修改失败!";
		}
		return ERROR;
	}

	// 条件查询(分页)
	@SuppressWarnings("unchecked")
	public String findWSByCondition() {
		if (wageStandard != null) {
			ActionContext.getContext().getSession().put("wageStandard",
					wageStandard);
		} else {
			wageStandard = (WageStandard) ActionContext.getContext()
					.getSession().get("wageStandard");
		}
		auditWsList = wageStandardServer.findAuditWs(null);// 审核和不同意的模版

		updateWsList = wageStandardServer.findNeedUpdateWs("gztz");// 查询需要调整模版的信息
		Object[] object = wageStandardServer.findWSByCondition(wageStandard,
				Integer.parseInt(cpage), pageSize);
		if (object != null) {
			WsList = (List<WageStandard>) object[0];
			if (WsList != null && WsList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("WageStandardAction!findWSByCondition.action?pageStatus="+pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			ActionContext.getContext().put("pageStatus", "find");
			return "findAllWsSuccess";
		}
		return ERROR;
	}

	// 删除工资模板
	public String delWs() {
		wageStandard = wageStandardServer.findWsById(id);
		if (wageStandard != null) {
			WageStandard oldWageStandard = wageStandardServer
					.findWSByUser(wageStandard.getCode());
			if (oldWageStandard == null) {
				errorMessage = wageStandard.getUserName() + "的工资模版为首次添加,无法删除!";
				return ERROR;
			}
			boolean bool = wageStandardServer.delWs(wageStandard);
			if (bool) {
				successMessage = "删除工资模板失败,请检查后重试!";
				return "delWsSuccess";
			} else {
				errorMessage = "删除工资模板失败,请检查后重试!";
			}
		}
		errorMessage = "不存在该工资模板,请检查后重试!";
		return ERROR;
	}

	// 根据id查询工资模板
	public String findWsById() {
		wageStandard = wageStandardServer.findWsById(id);
		if (wageStandard != null) {
			// if ("同意".equals(wageStandard.getProcessStatus())) {
			// auditWsList = wageStandardServer.findAuditWs(wageStandard
			// .getCode());// 审核和不同意的模版
			// if (auditWsList != null && auditWsList.size() > 0) {
			// onWorkWs = auditWsList.get(0);
			// errorMessage = onWorkWs.getUserName() + "存在状态为: "
			// + onWorkWs.getProcessStatus() + " 的待处理模版,请前往处理!";
			// return ERROR;
			// }
			// auditWsList = null;// 销毁该集合
			// }
			// pageStatus = "update";
			// ActionContext.getContext().put("pageStatus", "update");
			if (pageStatus != null && "jsgzupdate".equals(pageStatus)) {
				// 查询保险缴纳比例
				list = InsuranceGoldServer.findTimeIns();
				if (wageStandard.getInsuranceGoldId() != null) {
					insuranceGold = InsuranceGoldServer
							.findInsuranceGoldById(wageStandard
									.getInsuranceGoldId());
				}
				// 查询薪资调整协议
				contract = contractServer.findContractByCode(wageStandard
						.getCode(), "1");
				return "wage_chageJstzWs";
			}
			return "findAllWsSuccess";//wage_findWageStandard.jsp
		}
		errorMessage = "不存在该工资模板,请检查后重试!";
		return ERROR;
	}

	// 查询在审核中的工资标准信息
	@SuppressWarnings("unchecked")
	public String findWsFroAudit() {
		Object[] object = wageStandardServer.findWsFroAudit("默认", "审核", Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			WsList = (List<WageStandard>) object[0];
			if (WsList != null && WsList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("WageStandardAction!findWsFroAudit.action");
			} else {
				errorMessage = "暂时没有待审核的工资信息!";
			}
		} else {
			errorMessage = "暂时没有待审核的工资信息!";
		}
		return "findWsFroAudit";
	}

	// 审核工资模版
	public String auditWageStand() {
		boolean bool = wageStandardServer.auditWageStand(wsId, pageStatus);
		if (bool) {
			successMessage = "审核完成!";
			return "auditWageStand";
		}
		errorMessage = "审核失败!请检查后重试!";
		return ERROR;
	}

	// 通过工号查询用户信息(跳转到薪资协议查询页面)
	public String findUidForContract() {
		Users user = userServer.findUserByCode(code);
		if (user != null) {
			id = user.getId();
			return "findUidForContract";
		} else {
			errorMessage = "不存在该员工的信息,请检查后重试!";
		}
		return ERROR;
	}

	// 通过工号查询工资模版
	public String findWSByCode() {
		if (pageStatus != null && pageStatus.length() > 0
				&& "print".equals(pageStatus)) {
			Users loginuser = Util.getLoginUser();
			code = loginuser.getCode();
		}
		wageStandard = wageStandardServer.findWSByUser(code);
		if (wageStandard != null) {
			if (pageStatus != null && pageStatus.length() > 0
					&& "leave".equals(pageStatus)) {
				wage = wageStandardServer.findLeaveWage(code);
				return "findWSByCode";//hr_addLeaveWage
			} else if (pageStatus != null && pageStatus.length() > 0
					&& "print".equals(pageStatus)) {
				return "wage_findWageStandard_print";
			} else {
				wage = wageStandardServer.findLastWage(code);
				return "findWSForWage";
			}
		} else {
			errorMessage = "工号" + code + "的员工不存在工资模版!请检查后重试!";
		}
		return ERROR;
	}
	public String pladdWageStandard(){
		errorMessage = wageStandardServer.pladdWageStandard(addFile);
		return "error";
	}
	
	public WageStandardServer getWageStandardServer() {
		return wageStandardServer;
	}

	public void setWageStandardServer(WageStandardServer wageStandardServer) {
		this.wageStandardServer = wageStandardServer;
	}

	public InsuranceGoldServer getInsuranceGoldServer() {
		return InsuranceGoldServer;
	}

	public void setInsuranceGoldServer(InsuranceGoldServer insuranceGoldServer) {
		InsuranceGoldServer = insuranceGoldServer;
	}

	public WageStandard getWageStandard() {
		return wageStandard;
	}

	public void setWageStandard(WageStandard wageStandard) {
		this.wageStandard = wageStandard;
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

	public List<WageStandard> getWsList() {
		return WsList;
	}

	public void setWsList(List<WageStandard> wsList) {
		WsList = wsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public WageStandard getOnWorkWs() {
		return onWorkWs;
	}

	public void setOnWorkWs(WageStandard onWorkWs) {
		this.onWorkWs = onWorkWs;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCardId() {
		return cardId;
	}

	public Integer[] getWsId() {
		return wsId;
	}

	public void setWsId(Integer[] wsId) {
		this.wsId = wsId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public List<WageStandard> getAuditWsList() {
		return auditWsList;
	}

	public void setAuditWsList(List<WageStandard> auditWsList) {
		this.auditWsList = auditWsList;
	}

	public List<WageStandard> getUpdateWsList() {
		return updateWsList;
	}

	public void setUpdateWsList(List<WageStandard> updateWsList) {
		this.updateWsList = updateWsList;
	}

	public ContractServer getContractServer() {
		return contractServer;
	}

	public void setContractServer(ContractServer contractServer) {
		this.contractServer = contractServer;
	}

	public List<Contract> getProvisionList() {
		return provisionList;
	}

	public void setProvisionList(List<Contract> provisionList) {
		this.provisionList = provisionList;
	}

	public Wage getWage() {
		return wage;
	}

	public void setWage(Wage wage) {
		this.wage = wage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public InsuranceGold getInsuranceGold() {
		return insuranceGold;
	}

	public void setInsuranceGold(InsuranceGold insuranceGold) {
		this.insuranceGold = insuranceGold;
	}

	public Float[] getGjj() {
		return gjj;
	}

	public void setGjj(Float[] gjj) {
		this.gjj = gjj;
	}

	public File getAddFile() {
		return addFile;
	}

	public void setAddFile(File addFile) {
		this.addFile = addFile;
	}

	public String getAddmachineContentType() {
		return addmachineContentType;
	}

	public void setAddmachineContentType(String addmachineContentType) {
		this.addmachineContentType = addmachineContentType;
	}

	public String getAddmachineFileName() {
		return addmachineFileName;
	}

	public void setAddmachineFileName(String addmachineFileName) {
		this.addmachineFileName = addmachineFileName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
