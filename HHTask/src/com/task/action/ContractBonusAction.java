package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.*;
import com.task.Server.zgkh.AssessPersonnelServer;
import com.task.entity.*;
import com.task.entity.zgkh.AssessPersonnel;

/***
 * 承包奖金总额Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class ContractBonusAction extends ActionSupport {

	private AssessPersonnelServer assessPersonnelServer;// Server层
	private List<AssessPersonnel> assessPersonnelList;// 集合
	private ContractBonusServer contractBonusServer;// 承包奖金总额Server层
	private ContractBonus contractBonus;// 对象
	private ContractBonusReceive cbr;// 领取部留金额
	private List<ContractBonus> contractBonusList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private int[] contractBonusId;// id
	private String pageStauts;// 状态
	private List list;
	private List wSList;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	// 查询功能所绑定的人员
	public String findUserByFunction() {
		if (pageStauts != null && pageStauts.length() > 0) {
			if ("chengbao".equals(pageStauts)) {
				list = contractBonusServer.findUserBuFunction("承包奖金分配");
			} else if ("bumen".equals(pageStauts)) {
				list = contractBonusServer.findUserBuFunction("部门奖金分配");
			}
			return "findUserBuFunction";//hr_cb_ContractBonus.jsp
		}
		errorMessage = "参数错误!请检查后重试!";
		return ERROR;
	}

	// 添加承包奖金总额信息
	public String addContractBonus() {
		if (contractBonus != null) {
			ContractBonus olcContractBonus = contractBonusServer
					.findCBByMouthAndDept(id, contractBonus.getBonusMouth(),
							null);// 查询该用户是否已经存在总金额
			if (olcContractBonus == null) {
				if (pageStauts != null && pageStauts.length() > 0) {
					if ("chengbao".equals(pageStauts)) {
						contractBonus.setBonusStatus("审核");
					} else if ("bumen".equals(pageStauts)) {
						contractBonus.setBonusStatus("同意");
					}
					boolean bool = contractBonusServer.addContractBonus(
							contractBonus, id);// 添加
					if (bool) {
						successMessage = "为 " + contractBonus.getUserName()
								+ " 添加 " + contractBonus.getBonusMouth()
								+ " 奖金总额: " + contractBonus.getTotalMoney()
								+ "元 成功。请等待审核!";
						if ("chengbao".equals(pageStauts)) {
//							AlertMessagesServerImpl.addAlertMessages(
//									"承包奖金总额（总经理审核） ", contractBonus
//											.getDeptName()
//											+ "部门"
//											+ contractBonus.getBonusMouth()
//											+ "的承包奖金总额:"
//											+ contractBonus.getTotalMoney()
//											+ "元，请您的审核!", "1");

							list = contractBonusServer
									.findUserBuFunction("承包奖金分配");
						} else if ("bumen".equals(pageStauts)) {
							list = contractBonusServer
									.findUserBuFunction("部门奖金分配");
						}
						contractBonus = null;
						return "addContractBonus";
					}
				}
			} else {
				errorMessage = olcContractBonus.getUserName() + "的"
						+ contractBonus.getBonusMouth() + "的承包奖金总额已经存在,请勿重复添加!";
			}
		} else {
			errorMessage = "数据异常!请检查后重试";
		}
		return ERROR;
	}
	
	// 批量审批
	public String updateExamContractBonus() {
		try {
			if (contractBonusServer.updateExamBonus(detailSelect, tag)) {
				return "updateExamContractBonus";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 查询所有+条件查询+分页
	public String findAllContractBonus() {
		if (contractBonus != null) {
			ActionContext.getContext().getSession().put("contractBonus",
					contractBonus);
		} else {
			if (pageStauts != null && pageStauts.equals("allBuLiu")) {
				contractBonus = new ContractBonus();
			} else
				contractBonus = (ContractBonus) ActionContext.getContext()
						.getSession().get("contractBonus");
		}

		Object[] object = contractBonusServer.findAllContractBonus(
				contractBonus, Integer.parseInt(cpage), pageSize, pageStauts);// 条件查询所有用户
		if (object != null && object.length > 0) {
			contractBonusList = (List<ContractBonus>) object[0];
			if (contractBonusList != null && contractBonusList.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("ContractBonusAction!findAllContractBonus.action?pageStauts="
								+ pageStauts);
			} else {
				errorMessage = "抱歉!您查询的用户不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的员工信息!";
		}
		if (pageStauts != null && pageStauts.length() > 0) {
			if ("audit".equals(pageStauts)) {
				return "auditContractBonus";
			}
		}
		return "addContractBonus";
	}
	
	// 审批查询
	public String findAllContractBonus1() {
		Object[] obj = contractBonusServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("ContractBonusAction!findAllContractBonus1.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "auditContractBonus";
	}


	// 审核承包奖金总额
	public String auditContractBonus() {
		successMessage = contractBonusServer.auditContractBonus(errorMessage,
				contractBonusId);
		pageStauts = "audit";
		return "auditSuccess";
	}

	// 根据id查询承包奖金总额信息
	public String findContractBonusById() {
		contractBonus = contractBonusServer.findContractBonusById(id);
		if (contractBonus != null) {
			if (pageStauts != null && "receive".equals(pageStauts)) {
				return "receiveContractBonus";
			}
			return "addContractBonus";
		} else {
			errorMessage = "不存在该个信息!请检查后重试!";
		}
		return ERROR;
	}

	// 修改承包奖金分配
	public String updateContractBonus() {
		ContractBonus oldContractBonus = contractBonusServer
				.findContractBonusById(contractBonus.getId());
		if (oldContractBonus != null) {
			if (pageStauts != null && pageStauts.length() > 0) {
				if ("chengbao".equals(pageStauts)) {
					contractBonus.setBonusStatus("审核");
				} else if ("bumen".equals(pageStauts)) {
					contractBonus.setBonusStatus("同意");
				}
				oldContractBonus.setTotalMoney(contractBonus.getTotalMoney());
				if (contractBonusServer.updateContractBonus(oldContractBonus)) {
					successMessage = "修改 " + oldContractBonus.getUserName()
							+ " 的 " + contractBonus.getBonusMouth()
							+ " 的承包奖金总额成功!";

				}
				return "addContractBonus";
			}
		} else {
			errorMessage = "不存在该个信息!请检查后重试!";
		}
		return ERROR;
	}

	// 根据id查询承包奖金总额信息
	public String delContractBonus() {
		contractBonus = contractBonusServer.findContractBonusById(id);
		if (contractBonus != null) {
			if (contractBonusServer.delContractBonus(contractBonus)) {
				successMessage = "删除" + contractBonus.getDeptName() + "部门"
						+ contractBonus.getBonusMouth() + "的承包奖金总额成功!";
				contractBonus = null;
				return "auditSuccess";
			}
			return "addContractBonus";
		} else {
			errorMessage = "不存在该个信息!请检查后重试!";
		}
		return ERROR;
	}

	// 查询登录用户的部门奖金总额以及其所属成员
	public String findTolAndTeam() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		contractBonus = contractBonusServer.findCBByMouthAndDept(user.getId(),
				"", null);// 查询该用户的部门奖金总额
		if (contractBonus != null) {
			if ((Object) id != null && id > 0) {
				UsersGroup up = assessPersonnelServer.findUsersGroupById(id);
				if (up != null) {
					wSList = contractBonusServer.findTeammembersByUserId(user
							.getId(), up.getId());
				}
			} else {
				// 判断成员组是否大于1个
				list = assessPersonnelServer.findUsersGroupByUid();
				if (list != null && list.size() == 1) {
					wSList = contractBonusServer.findTeammembersByUserId(user
							.getId());
				}
			}
			return "bonusDistribution";
		} else {
			errorMessage = "不存在您的部门奖金总额!请检查后重试!";
		}
		return ERROR;
	}

	// 领取部留金额
	public String contractBonusReceive() {
		ContractBonusReceive oldCbr = contractBonusServer.findCbr(id);
		if (oldCbr == null) {
			boolean bool = contractBonusServer.addReceiveMessage(cbr);
			if (bool) {
				contractBonus = contractBonusServer.findContractBonusById(id);
				contractBonus.setIfReceive("yes");
				contractBonusServer.updateContractBonus(contractBonus);
				successMessage = cbr.getReceivePeople() + "领取"
						+ cbr.getReceiveDept() + "部门" + cbr.getReceiveMonth()
						+ "的部留金额:<font color='red'>" + cbr.getReceiveMoney()
						+ "</font>元 成功!";
				pageStauts = "success";
				return "receiveContractBonus";
			}
			errorMessage = "领取失败,请重新领取!";
		} else {
			errorMessage = "该金额已经领取过，请勿重复领取!";
		}
		return ERROR;
	}

	// 查询领取记录
	public String findCdr() {
		cbr = contractBonusServer.findCbr(id);
		return "receiveContractBonus";
	}

	// 构造方法
	public ContractBonusServer getContractBonusServer() {
		return contractBonusServer;
	}

	public void setContractBonusServer(ContractBonusServer contractBonusServer) {
		this.contractBonusServer = contractBonusServer;
	}

	public ContractBonus getContractBonus() {
		return contractBonus;
	}

	public void setContractBonus(ContractBonus contractBonus) {
		this.contractBonus = contractBonus;
	}

	public List<ContractBonus> getContractBonusList() {
		return contractBonusList;
	}

	public void setContractBonusList(List<ContractBonus> contractBonusList) {
		this.contractBonusList = contractBonusList;
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

	public String getPageStauts() {
		return pageStauts;
	}

	public void setPageStauts(String pageStauts) {
		this.pageStauts = pageStauts;
	}

	public int[] getContractBonusId() {
		return contractBonusId;
	}

	public void setContractBonusId(int[] contractBonusId) {
		this.contractBonusId = contractBonusId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ContractBonusReceive getCbr() {
		return cbr;
	}

	public void setCbr(ContractBonusReceive cbr) {
		this.cbr = cbr;
	}

	public AssessPersonnelServer getAssessPersonnelServer() {
		return assessPersonnelServer;
	}

	public void setAssessPersonnelServer(
			AssessPersonnelServer assessPersonnelServer) {
		this.assessPersonnelServer = assessPersonnelServer;
	}

	public List<AssessPersonnel> getAssessPersonnelList() {
		return assessPersonnelList;
	}

	public void setAssessPersonnelList(List<AssessPersonnel> assessPersonnelList) {
		this.assessPersonnelList = assessPersonnelList;
	}

	public List getwSList() {
		return wSList;
	}

	public void setwSList(List wSList) {
		this.wSList = wSList;
	}

}
