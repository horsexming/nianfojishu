package com.task.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.EquipmentChangesService;
import com.task.entity.EquipmentChanges;
import com.task.entity.Machine;
import com.task.entity.Users;
import com.task.util.Util;

public class EquipmentChangesAction extends ActionSupport {
	private EquipmentChangesService equipmentChangesService;
	private EquipmentChanges equipmentChanges;
	private String successMessage;// 成功消息
	private String errorMessage;// 成功消息
	private List<EquipmentChanges> equipmentChangesList;
	private List<EquipmentChanges> equipmentChangesList1;
	private List<EquipmentChanges> equipmentChangesList2;
	private List<EquipmentChanges> equipmentChangesList3;
	private List<EquipmentChanges> equipmentChangesList4;
	private List<EquipmentChanges> reList;
	private String pageStatus;
	private int id;
	private Machine machine;
	private List<Machine> machineList;
	private List<EquipmentChanges> equipmenList;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Date date1;
	private Date date2;
	private String fileName;

	// 添加
	public String add() {
		boolean bool = equipmentChangesService.add(equipmentChanges);
		if (bool) {
			successMessage = "添加成功";
		}
		return "addequipmentChanges";
	}

	// 查询所有
	@SuppressWarnings("unchecked")
	public String findAll() {
		if (equipmentChanges != null) {
			ActionContext.getContext().getSession().put("equipmentChanges",
					equipmentChanges);
		} else {
			equipmentChanges = (EquipmentChanges) ActionContext.getContext()
					.getSession().get("equipmentChanges");
		}
		Object[] object = null;

		if (pageStatus == null) {
			equipmentChangesList1 = equipmentChangesService.findAll("审批");// 审核
			equipmentChangesList2 = equipmentChangesService.findAll("打回");// 打回
			equipmentChangesList3 = equipmentChangesService.findAll("批准");// 同意
			equipmentChangesList4 = equipmentChangesService.findAll("同意");// 同意
			equipmentChanges.setStatus("完成");
			object = equipmentChangesService.findAll(equipmentChanges, Integer
					.parseInt(cpage), pageSize, pageStatus);
		} else {
			object = equipmentChangesService.findAll(equipmentChanges, Integer
					.parseInt(cpage), pageSize, pageStatus);
		}

		if (object != null && object.length > 0) {
			equipmentChangesList = (List<EquipmentChanges>) object[0];
			if (equipmentChangesList != null && equipmentChangesList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("EquipmentChangesAction!findAll.action?pageStatus="
						+ pageStatus);
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAll";

	}

	// 移动
	public String initupmachiner() {
		machine = equipmentChangesService.findAssettById(id);
		return "upper";
	}

	// 条件查询
	@SuppressWarnings("unchecked")
	public String findAlll() {
		if (machine != null) {
			ActionContext.getContext().getSession().put("machine", machine);
		} else {
			machine = (Machine) ActionContext.getContext().getSession().get(
					"machine");
		}
		Object[] object = equipmentChangesService.findMachineByCondition(
				machine, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			machineList = (List<Machine>) object[0];
			if (machineList != null && machineList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("EquipmentChangesAction!findAlll.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "addSuccess";
	}

	// 条件查询(EquipmentChanges)
	@SuppressWarnings("unchecked")
	public String findAAll() {
		if (equipmentChanges != null) {
			ActionContext.getContext().getSession().put("equipmentChanges",
					equipmentChanges);
		} else {
			equipmentChanges = (EquipmentChanges) ActionContext.getContext()
					.getSession().get("equipmentChanges");
		}
		Object[] object = equipmentChangesService.findMachineBCondition(
				equipmentChanges, Integer.parseInt(cpage), pageSize, date1,
				date2);

		if (object != null && object.length > 0) {
			equipmenList = (List<EquipmentChanges>) object[0];
			if (equipmenList != null && equipmenList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (date1 != null && date2 != null) {
					this.setUrl("EquipmentChangesAction!findAAll.action?date1="
							+ Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss")
							+ "&date2="
							+ Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss"));
				} else {
					this.setUrl("EquipmentChangesAction!findAAll.action");
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "addSuccesss";
	}

	// 审批管理
	public String updateSubmit() {
		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		equipmentChanges = equipmentChangesService.findAssetById(id);
		if (equipmentChanges != null) {
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("fuzong".equals(pageStatus)) {
					equipmentChanges.setStatus("批准");
					equipmentChanges.setProcessingtime(writeDate);
				} else if ("zongjingli".equals(pageStatus)) {
					equipmentChanges.setStatus("同意");
					equipmentChanges.setZhongtime(writeDate);
				} else if ("tongyi".equals(pageStatus)) {
					equipmentChanges.setStatus("完成");
					Users loginUser = Util.getLoginUser();
					equipmentChanges.setChangesname(loginUser.getName());
					equipmentChanges.setCompletiontime(writeDate);
				} else if ("back".equals(pageStatus.substring(0, 4))) {
					equipmentChanges.setStatus("打回");
					pageStatus = pageStatus.substring(4);
				}
				equipmentChangesService.update(equipmentChanges);
				return "updateSubmit";
			} else {
				errorMessage = "参数异常!请检查后重试!";
			}
		} else {
			errorMessage = "不存在您要查询的数据!";
		}
		return ERROR;
	}

	// 修改
	public String initupmaintenance() {
		equipmentChanges = equipmentChangesService.findAssetById(id);

		return "updaterr";
	}

	public String updatemaintenance() {
		equipmentChanges
				.setChangesdate(Util.getDateTime("yyyy-MM-dd hh:mm:ss"));
		equipmentChanges.setStatus("审批");
		equipmentChangesService.update(equipmentChanges);

		return "updatermaintenancer";
	}

	// 明细
	public String findByclientManagement() {
		equipmentChanges = equipmentChangesService.findAssetById(id);
		return "findByclientManagement";

	}

	// 删除
	public String delSubmit() {
		equipmentChanges = equipmentChangesService.findAssetById(id);
		equipmentChangesService.delete(equipmentChanges);
		return "delsubmit";
	}

	// 导出Excel月度统计
	public String macrepairexcel() {
		fileName = equipmentChangesService.exportExcel(date1, date2);
		if (fileName != null && !"no".equals(fileName)) {
			return "generateexcel";
		} else {
			errorMessage = "导出数据出错，请检查后重试!";
			return ERROR;
		}

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public EquipmentChangesService getEquipmentChangesService() {
		return equipmentChangesService;
	}

	public void setEquipmentChangesService(
			EquipmentChangesService equipmentChangesService) {
		this.equipmentChangesService = equipmentChangesService;
	}

	public EquipmentChanges getEquipmentChanges() {
		return equipmentChanges;
	}

	public void setEquipmentChanges(EquipmentChanges equipmentChanges) {
		this.equipmentChanges = equipmentChanges;
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

	public List<EquipmentChanges> getEquipmentChangesList() {
		return equipmentChangesList;
	}

	public void setEquipmentChangesList(
			List<EquipmentChanges> equipmentChangesList) {
		equipmentChangesList = equipmentChangesList;
	}

	public List<EquipmentChanges> getEquipmentChangesList1() {
		return equipmentChangesList1;
	}

	public void setEquipmentChangesList1(
			List<EquipmentChanges> equipmentChangesList1) {
		equipmentChangesList1 = equipmentChangesList1;
	}

	public List<EquipmentChanges> getEquipmentChangesList2() {
		return equipmentChangesList2;
	}

	public void setEquipmentChangesList2(
			List<EquipmentChanges> equipmentChangesList2) {
		equipmentChangesList2 = equipmentChangesList2;
	}

	public List<EquipmentChanges> getEquipmentChangesList3() {
		return equipmentChangesList3;
	}

	public void setEquipmentChangesList3(
			List<EquipmentChanges> equipmentChangesList3) {
		equipmentChangesList3 = equipmentChangesList3;
	}

	public List<EquipmentChanges> getEquipmentChangesList4() {
		return equipmentChangesList4;
	}

	public void setEquipmentChangesList4(
			List<EquipmentChanges> equipmentChangesList4) {
		equipmentChangesList4 = equipmentChangesList4;
	}

	public List<EquipmentChanges> getReList() {
		return reList;
	}

	public void setReList(List<EquipmentChanges> reList) {
		this.reList = reList;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	public List<EquipmentChanges> getEquipmenList() {
		return equipmenList;
	}

	public void setEquipmenList(List<EquipmentChanges> equipmenList) {
		this.equipmenList = equipmenList;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

}
