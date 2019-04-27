package com.task.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.*;

/**
 * 合同Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class ContractAction extends ActionSupport {

	private ContractServer contractServer;// 合同Server层
	private UserServer userServer;// 用户Server层
	private ProvisionServer provisionServer;// Server层（改）
	private String[] proContent;// 对象（改）
	private Contract contract;// 合同
	private List<Contract> contractList;// 合同集合
	private List<Provision> provisionList;// 条款集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private Integer[] provisionId;// 条款Id
	private Users user;// 用户
	private String contractNumber;// 合同编号
	private String code;// 工号
	private String isUse;// 合同状态
	private String pageStatus;// 状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加合同以及重新签订合同
	public String addContract() {
		Users user = userServer.findUserById(id);
		if (user != null) {
			errorMessage = contractServer.findNumber(contract
					.getContractNumber());// 查询合同编号是否存在
			if (errorMessage != null && "true".equals(errorMessage)) {
				errorMessage = null;
			} else
				return ERROR;
			Contract oldContract = contractServer.findContractById(
					user.getId(), "contract");// 查询员工是否已经签订过合同
			if (oldContract != null) {
				oldContract.setIsUse("历史");
				contractServer.updateContract(oldContract);// 更新以前的合同信息为历史状态
			}
			contract.setIsUse("默认");
			boolean bool = contractServer.addContract(contract, proContent);// 添加合同
			if (bool) {
				successMessage = "签订合同成功!";
				pageStatus = "print";
				if (user.getPassword() != null
						&& user.getPassword().getUserStatus().equals("签订合同")) {
					user.getPassword().setUserStatus("薪资处理");
					bool = userServer.updateUser(user);
					if (bool == false) {
						successMessage += "(修改员工入职流程状态失败!)";
					}
				}
				id = contract.getUserId();
				return "addContractSuccess";
			} else {
				errorMessage = "签订合同失败!请检查后重试!";
			}
		} else {
			errorMessage = "不存在该员工的信息!请核查!";
		}
		return ERROR;
	}

	// 预览合同
	public String viewContract() {
		user = userServer.findUserById(id);
		if (user != null) {
			errorMessage = contractServer.findNumber(contract
					.getContractNumber());// 查询合同编号是否存在
			if (errorMessage != null && "true".equals(errorMessage)) {
				errorMessage = null;
			} else
				return ERROR;
			contract.setIsUse("默认");
			provisionList = new ArrayList<Provision>();
			for (int i = 0; i < proContent.length; i++) {
				Provision provision = new Provision();
				provision.setContent(proContent[i]);// 内容
				provision.setProvisionStatus("private");// 状态
				provision.setContract(contract);// 合同
				provisionList.add(provision);
			}
			pageStatus = "view";
			return "findContractByUid";
		} else {
			errorMessage = "不存在该员工的信息!请核查!";
		}
		return ERROR;
	}

	// 预览薪资调整协议
	public String viewAgreement() {
		user = userServer.findUserById(id);
		if (user != null) {
			Contract oldContract = contractServer.findContractById(
					user.getId(), "contract");// 劳务合同
			if (oldContract != null) {
				contractNumber = oldContract.getContractNumber();
			} else {
				contractNumber = user.getCode() + user.getCardId();
			}
			provisionList = new ArrayList<Provision>();
			for (int i = 0; i < proContent.length; i++) {
				Provision provision = new Provision();
				provision.setContent(proContent[i]);// 内容
				provision.setProvisionStatus("private");// 状态
				provision.setContract(contract);// 合同
				provisionList.add(provision);
			}
			pageStatus = "view";
			return "findUserWageAgre";
		} else {
			errorMessage = "不存在该员工的信息!请核查!";
		}
		return ERROR;
	}

	// 添加薪资调整协议
	public String addWageAgreement() {
		user = userServer.findUserById(contract.getUserId());
		if (user != null) {
			Contract contract = contractServer.findContractByCode(user
					.getCode());
			if (contract != null) {
				errorMessage = user.getName()
						+ "存在尚未处理完成的薪资协议,无法继续添加薪资协议!可到薪资调整历史查看处理状态!";
				return ERROR;
			}
		}

		boolean bool = contractServer.addContract(contract, proContent);// 添加薪资调整协议
		if (bool) {
			AlertMessagesServerImpl.addAlertMessages("社保基数调整", contract
					.getEmployedSquare()
					+ "的薪资调整协议添加成功，请您为其处理社保基数! ", "1");
			return "addWageAgreement";
		}
		errorMessage = "添加协议失败!请检查后重试!";
		return ERROR;
	}

	// 查询员工所有薪资协议
	@SuppressWarnings("unchecked")
	public String findUserWageAgre() {
		if (contract != null) {
			ActionContext.getContext().getSession().put("contractSelect",
					contract);
		} else {
			contract = (Contract) ActionContext.getContext().getSession().get(
					"contractSelect");
		}
		user = userServer.findUserById(contract.getUserId());
		Object[] object = contractServer.findUserWageAgre(contract, Integer
				.parseInt(cpage), pageSize);// 条件查询所有用户
		if (object != null && object.length > 0) {
			contractList = (List<Contract>) object[0];
			if (contractList != null && contractList.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ContractAction!findUserWageAgre.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!该员工尚未调整薪资!";
			}
		} else {
			errorMessage = "抱歉!该员工尚未调整薪资!";
		}
		contract = null;// 清空对象,用于页面流程判断
		return "findUserWageAgre";
	}

	// 根据id查询该协议下的所有条款(打印薪资调整协议)
	@SuppressWarnings("unchecked")
	public String findProvisions() {
		contract = contractServer.findContractById(id);// 薪资协议合同
		user = userServer.findUserById(contract.getUserId());
		Contract oldContract = contractServer.findContractById(user.getId(),
				"contract");// 劳务合同
		if (oldContract != null) {
			contractNumber = oldContract.getContractNumber();
		} else {
			contractNumber = user.getCode() + user.getCardId();
		}
		if (contract != null) {
			provisionList = contractServer.findProvision(contract);
			return "findUserWageAgre";
		}
		return ERROR;
	}

	// 根据userId查询合同以及合同所对应的条款
	@SuppressWarnings("unchecked")
	public String findContractByUid() {
		user = userServer.findUserById(id);
		contract = contractServer.findContractById(id, "contract");
		if (contract != null && user != null) {
			provisionList = contractServer.findProvision(contract);
			pageStatus = "print";
			return "findContractByUid";
		} else {
			errorMessage = "不存在该合同信息!请核查!";
		}
		return ERROR;

	}

	// 重新签订合同
	public String updateContractDate() {
		if (contract.getStartDate() != null) {// 生效时间
			if (contract.getEndDate() != null) {// 结束时间
				Contract oldContract = contractServer.findContractById(id,
						"contract");
				if (oldContract != null) {
					oldContract.setStartDate(contract.getStartDate());
					oldContract.setEndDate(contract.getEndDate());
					boolean bool = contractServer.updateContract(oldContract);
					if (bool) {
						return "addContractSuccess";
					} else {
						errorMessage = "重新签订合同出错!请核查后重试!";
					}
				} else {
					errorMessage = "不存在该合同信息!请核查!";
				}
			} else {
				errorMessage = "请填写合同到期时间!";
			}
		} else {
			errorMessage = "请填写合同生效时间!";
		}
		return ERROR;
	}

	// 删除合同
	public String delContract() {
		contract = contractServer.findContractById(id);// 薪资协议合同
		if (contract != null) {
			contractServer.delContract(contract);
			return "addWageAgreement";
		} else {
			errorMessage = "不存在该信息";
			return ERROR;
		}
	}

	// 根据工号和状态查询合同信息
	@SuppressWarnings("unchecked")
	public String findContractByCode() {
		contract = contractServer.findContractByCode(code, isUse);
		if (contract != null) {
			user = userServer.findUserById(contract.getUserId());
			Contract oldContract = contractServer.findContractById(
					user.getId(), "contract");// 劳务合同
			// 如果没有合同,生成合同编号
			if (oldContract != null) {
				contractNumber = oldContract.getContractNumber();
			} else {
				contractNumber = user.getCode() + user.getCardId();
			}
			provisionList = contractServer.findProvision(contract);
			return "findUserWageAgre";
		} else {
			errorMessage = "不存在该合同信息!请核查!";
		}
		return ERROR;
	}

	// 构造方法

	public Contract getContract() {
		return contract;
	}

	public ContractServer getContractServer() {
		return contractServer;
	}

	public void setContractServer(ContractServer contractServer) {
		this.contractServer = contractServer;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
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

	public Integer[] getProvisionId() {
		return provisionId;
	}

	public void setProvisionId(Integer[] provisionId) {
		this.provisionId = provisionId;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public ProvisionServer getProvisionServer() {
		return provisionServer;
	}

	public void setProvisionServer(ProvisionServer provisionServer) {
		this.provisionServer = provisionServer;
	}

	public String[] getProContent() {
		return proContent;
	}

	public void setProContent(String[] proContent) {
		this.proContent = proContent;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public List<Provision> getProvisionList() {
		return provisionList;
	}

	public void setProvisionList(List<Provision> provisionList) {
		this.provisionList = provisionList;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

}
