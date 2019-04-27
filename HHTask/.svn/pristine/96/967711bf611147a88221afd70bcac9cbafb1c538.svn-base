package com.task.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.UnitManagerService;
import com.task.entity.UnitManager;
import com.task.util.MKUtil;

public class UnitManagerAction {

	private UnitManagerService unitManagerService;
	private UnitManager manager;
	private String errorMessage;
	private String successMessage;
	private List<Map> maps;
	private Object[] objects;
	private String pageStatus;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer unit_id;

	public Integer getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Integer unitId) {
		unit_id = unitId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public Object[] getObjects() {
		return objects;
	}

	public void setObjects(Object[] objects) {
		this.objects = objects;
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

	public UnitManagerService getUnitManagerService() {
		return unitManagerService;
	}

	public void setUnitManagerService(UnitManagerService unitManagerService) {
		this.unitManagerService = unitManagerService;
	}

	public UnitManager getManager() {
		return manager;
	}

	public void setManager(UnitManager manager) {
		this.manager = manager;
	}

	// 查询所有单位信息
	public String findUnitManager() {
		if (manager != null) {
			ActionContext.getContext().getSession().put("manager", manager);
		} else {
			manager = (UnitManager) ActionContext.getContext().getSession()
					.get("manager");
		}
		Object[] object = unitManagerService.findCustomerAll(manager, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("UnitManagerAction_findUnitManager.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findUnitManager";
	}

	// 查询所有材质信息
	public String findCaizhiManager() {
		if (manager != null) {
			ActionContext.getContext().getSession().put("manager", manager);
		} else {
			manager = (UnitManager) ActionContext.getContext().getSession()
					.get("manager");
		}
		Object[] object = unitManagerService.findCaizhiAll(manager, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("UnitManagerAction_findCaizhiManager.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findCaizhiManager";
	}

	// 查询所有单位
	public String findUninAll() {
		List list = unitManagerService.findUninAll(pageStatus);
		// List<UnitManager> newList = new ArrayList<UnitManager>();
		// for (int i = 0; i < list.size(); i++) {
		// UnitManager unitname = (UnitManager) list.get(i);
		// newList.add(unitname);
		// }
		MKUtil.writeJSON(list);
		return null;
	}

	public String toadd() {
		return "addUnitManager";
	}

	// 添加单位
	public String addUnitManager() {
		this.unitManagerService.addUnitManager(manager);
		this.successMessage = "添加成功!";
		return "addUnitManager";
	}

	// 删除单位信息
	public String delUnitManager() {
		this.unitManagerService.delUnitManager(manager);
		if(pageStatus!=null&&pageStatus.equals("material")){
			return findCaizhiManager();
		}
		return "delUnitManager";
	}

	// 根据编号查询单位信息
	public String findUnitManagerById() {
		this.manager = unitManagerService.findUnitManagerById(unit_id);
		return "findUnitManagerById";
	}

	// 修改单位信息
	public String updateUnitManager() {
		this.unitManagerService.updateUnitManager(manager);
		this.successMessage = "修改成功!";
		return "updateUnitManager";
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

}
