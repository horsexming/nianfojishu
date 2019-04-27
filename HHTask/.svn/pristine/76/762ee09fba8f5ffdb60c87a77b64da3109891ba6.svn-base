package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.entity.*;

/**
 * 职位胜任要求Action层
 * 
 * @author 刘培
 * 
 */
public class DutyClaimAction extends ActionSupport {

	private DutyClaimServer dutyClaimServer;// 职位胜任要求Server层
	private DutyClaim dutyClaim;// 职位胜任要求对象
	private List<DutyClaim> dutyClaimList;// 职位胜任要求集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private List list;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加职位胜任要求
	public String addDutyClaim() {
		DutyClaim oldDutyClaim = dutyClaimServer.findDutyClaimByFloor(dutyClaim
				.getDuty(), 1);// 查询第一层是否存在该职位
		if (oldDutyClaim == null) {
			dutyClaim.setSkillClaim(dutyClaim.getSkillClaim().replaceAll(" ",
					"").replaceAll(",", "").replaceAll("，", ""));
			dutyClaim.setQuaClaim(dutyClaim.getQuaClaim().replaceAll(" ", "")
					.replaceAll(",", "").replaceAll("，", ""));
			boolean bool = dutyClaimServer.addDutyClaim(dutyClaim);
			if (bool) {
				successMessage = "添加职位 " + dutyClaim.getDuty() + " 的胜任要求成功!";
				list = dutyClaimServer.findAllDuty();
				return "findAllDuty";
			}
		} else {
			errorMessage = "该职位已经添加过标准胜任要求,请勿重复添加!";
		}
		return ERROR;
	}

	// 添加人员
	public String addPeople() {
		DutyClaim fatherDutyClaim = dutyClaimServer.findDutyClaimById(id);
		if (fatherDutyClaim != null) {
			dutyClaim.setDutyClaim(fatherDutyClaim);// 设置父类
			dutyClaim.setFloor(fatherDutyClaim.getFloor() + 1);// 设置层数=父类层数+1
			// 查询该成员是否已经添加过
			DutyClaim oldDutyClaim = dutyClaimServer
					.findDutyClaimByUser(dutyClaim);
			if (oldDutyClaim == null) {

				boolean bool = dutyClaimServer.addDutyClaim(dutyClaim);// 添加人员
				if (bool) {
					return "addPeople";
				}
			} else
				errorMessage = oldDutyClaim.getUserName() + "在职位"
						+ fatherDutyClaim.getDuty() + "的"
						+ oldDutyClaim.getClaimStatus() + "中已经添加过,请勿重复添加";
		} else
			errorMessage = "不存在您要查询的职位信息，无法添加人员!";
		return ERROR;
	}

	// 查询职位胜任要求(条件+分页)
	@SuppressWarnings("unchecked")
	public String findAllDutyClaim() {
		if (dutyClaim != null) {
			ActionContext.getContext().getSession().put("dutyClaim", dutyClaim);
		} else {
			dutyClaim = (DutyClaim) ActionContext.getContext().getSession()
					.get("dutyClaim");
		}
		Object[] object = dutyClaimServer.findAllDutyClaim(dutyClaim, Integer
				.parseInt(cpage), pageSize);
		if (object != null) {
			dutyClaimList = (List<DutyClaim>) object[0];
			if (dutyClaimList != null && dutyClaimList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("DutyClaimAction!findAllDutyClaim.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
			return "findAllDutyClaim";
		}
		return ERROR;
	}

	// 查询职位胜任要求明细
	@SuppressWarnings("unchecked")
	public String findDutyClaimDetails() {
		dutyClaim = dutyClaimServer.findDutyClaimById(id);
		if (dutyClaim != null) {
			// 查询现有人员
			dutyClaimList = dutyClaimServer.findDutyClaimByFather(id, "现有人员");
			list = dutyClaimServer.findDutyClaimByFather(id, "备选人员");
			return "findAllDutyClaim";
		}
		errorMessage = "不存在您要查询的职位信息";
		return ERROR;
	}

	// 查询职位胜任要求做修改
	public String findDutyForUpdate() {
		dutyClaim = dutyClaimServer.findDutyClaimById(id);
		if (dutyClaim != null) {
			list = dutyClaimServer.findAllDuty();
			return "findAllDuty";
		}
		errorMessage = "不存在您要查询的职位信息";
		return ERROR;
	}

	// 修改职位胜任要求
	public String updateDutyClaim() {
		dutyClaim.setSkillClaim(dutyClaim.getSkillClaim().replaceAll(" ", "")
				.replaceAll(",", "").replaceAll("，", ""));
		dutyClaim.setQuaClaim(dutyClaim.getQuaClaim().replaceAll(" ", "")
				.replaceAll(",", "").replaceAll("，", ""));
		boolean bool = dutyClaimServer.updateDutyClaim(dutyClaim, id);
		if (bool) {
			return "findAllDuty";
		}
		errorMessage = "不存在您要查询的职位信息";
		return ERROR;
	}

	// 删除职位胜任要求明细
	public String delDutyClaim() {
		dutyClaim = dutyClaimServer.findDutyClaimById(id);
		if (dutyClaim != null) {
			dutyClaimServer.delDutyClaim(dutyClaim);
			if (dutyClaim.getFloor() != 1) {
				id = dutyClaim.getDutyClaim().getId();
				return "addPeople";
			}
			return "delDutyClaim";
		}
		errorMessage = "不存在您要查询的职位信息";
		return ERROR;
	}

	// 查找人员表所有的职位
	public String findAllDuty() {
		list = dutyClaimServer.findAllDuty();
		return "findAllDuty";
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public DutyClaimServer getDutyClaimServer() {
		return dutyClaimServer;
	}

	public void setDutyClaimServer(DutyClaimServer dutyClaimServer) {
		this.dutyClaimServer = dutyClaimServer;
	}

	public DutyClaim getDutyClaim() {
		return dutyClaim;
	}

	public void setDutyClaim(DutyClaim dutyClaim) {
		this.dutyClaim = dutyClaim;
	}

	public List<DutyClaim> getDutyClaimList() {
		return dutyClaimList;
	}

	public void setDutyClaimList(List<DutyClaim> dutyClaimList) {
		this.dutyClaimList = dutyClaimList;
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

}
