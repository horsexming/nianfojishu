package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IIPEntityService;
import com.task.entity.IPEntity;

public class IPEntityAction {
	private IIPEntityService ipes;
	private List list;
	private Integer id;
	private IPEntity ip;
	private String errorMessage;
	private String ipStr;
	private String deptStr;
	private String nameStr;
	private String stateStr;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String initIPEntity() {
		Object[] object = ipes.queryIPEntityByCondition(null, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ipManager_initIPEntity.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "init";
	}

	@SuppressWarnings("unchecked")
	public String queryIPEntityByCondition() {
		Map map = new HashMap();
		if (ipStr != null && !ipStr.equals("")) {
			map.put("ipStr", ipStr);
		}
		if (deptStr != null && !deptStr.equals("")) {
			map.put("deptStr", deptStr);
		}
		if (nameStr != null && !nameStr.equals("")) {
			map.put("nameStr", nameStr);
		}
		if (stateStr != null && !stateStr.equals("")) {
			map.put("stateStr", stateStr);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("ipCondition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"ipCondition");
			} else
				ActionContext.getContext().getSession().remove("ipCondition");
		}
		Object[] object = ipes.queryIPEntityByCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ipManager_queryIPEntityByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "init";
	}

	public String del() {
		if (id != 0) {
			ipes.delIPEntityById(id);
		}
		return "redirect";
	}

	public String initUpdate() {
		if (id != 0) {
			ip = ipes.getIPEntityById(id);
		}
		return "success";
	}

	public String update() {
		if (id != 0) {
			IPEntity oldIp = ipes.getIPEntityById(id);
			BeanUtils.copyProperties(ip, oldIp, new String[] { "id" });
			ipes.update(oldIp);
		}
		return "redirect";
	}

	public String add() {
		ipes.add(ip);
		return "redirect";
	}

	public String initAdd() {
		return "success";
	}

	public IPEntity getIp() {
		return ip;
	}

	public void setIp(IPEntity ip) {
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public IIPEntityService getIpes() {
		return ipes;
	}

	public void setIpes(IIPEntityService ipes) {
		this.ipes = ipes;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getIpStr() {
		return ipStr;
	}

	public void setIpStr(String ipStr) {
		this.ipStr = ipStr;
	}

	public String getDeptStr() {
		return deptStr;
	}

	public void setDeptStr(String deptStr) {
		this.deptStr = deptStr;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public String getStateStr() {
		return stateStr;
	}

	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
}
