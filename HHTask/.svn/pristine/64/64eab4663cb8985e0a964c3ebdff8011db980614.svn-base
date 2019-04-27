package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IConsumingService;
import com.task.entity.Consuming;
import com.task.entity.VOConsuming;
import com.task.util.MKUtil;

/***
 * 领用
 * 
 * @author 曾建森
 * 
 */
public class ConsumingAction {
	private IConsumingService ics;
	private List list;
	private String errorMessage;
	private Consuming bo;
	private VOConsuming voc = new VOConsuming();
	private int[] selected;
	

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	
	public int[] getSelected() {
		return selected;
	}
	public void setSelected(int[] selected) {
		this.selected = selected;
	}
	

	/**
	 * 根据storId 和卡号查询最后一次领取记录
	 * 
	 * @param storeId
	 * @param cardNum
	 * @return
	 */
	public String getConsumingByStoreId() {
		Consuming consuming = this.ics.getConsumingByStoreId(this.bo.getStore()
				.getId(), this.bo.getCardNum());
		if (consuming != null) {
			consuming.setStore(null);
			consuming.setOut(null);
			MKUtil.writeJSON(true, "操作成功", consuming);
		} else {
			MKUtil.writeJSON(true, "操作成功", null);
		}

		return null;
	}

	public String initQueryConsuming() {
		Object[] object = ics.queryConsuming(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("consuming_initQueryConsuming.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";//consuming_index.jsp
	}

	public String queryConsumingByCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (voc.getDept() != null && !voc.getDept().equals(""))
			map.put("dept", voc.getDept());
		if (voc.getCardId() != null && !voc.getCardId().equals(""))
			map.put("cardId", voc.getCardId());
		if (voc.getPerson() != null && !voc.getPerson().equals(""))
			map.put("person", voc.getPerson());
		if (voc.getName() != null && !voc.getName().equals(""))
			map.put("matetag", voc.getName());
		if (voc.getStandard() != null && !voc.getStandard().equals(""))
			map.put("format", voc.getStandard());
		if (voc.getNumber() != null && !voc.getNumber().equals(""))
			map.put("number", voc.getNumber());
		if (voc.getStorehouse() != null && !voc.getStorehouse().equals(""))
			map.put("storehouse", voc.getStorehouse());
		if (voc.getStartTime() != null && !voc.getStartTime().equals(""))
			map.put("startTime", voc.getStartTime());
		if (voc.getEndTime() != null && !voc.getEndTime().equals(""))
			map.put("endTime", voc.getEndTime());
		
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("con", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get("con");
			} else
				ActionContext.getContext().getSession().remove("con");
		}
		Object[] object = ics.queryConsuming(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("consuming_queryConsumingByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String del() {
		if (voc.getId() != null) {
			ics.delConsumingById(voc.getId());
		}
		return "redirectList";
	}
	/*
	 * 批量打印
	 * 
	 */
	public String printStorage() {
		
		if (selected != null && selected.length > 0) {
			list = ics.printStorage(selected);
		}
		return "success1";
	}
	public String export() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (voc.getDept() != null && !voc.getDept().equals(""))
			map.put("dept", voc.getDept());
		if (voc.getCardId() != null && !voc.getCardId().equals(""))
			map.put("cardId", voc.getCardId());
		if (voc.getPerson() != null && !voc.getPerson().equals(""))
			map.put("person", voc.getPerson());
		if (voc.getNumber() != null && !voc.getNumber().equals(""))
			map.put("number", voc.getNumber());
		if (voc.getStorehouse() != null && !voc.getStorehouse().equals(""))
			map.put("storehouse", voc.getStorehouse());
		if (voc.getStartTime() != null && !voc.getStartTime().equals(""))
			map.put("startTime", voc.getStartTime());
		if (voc.getEndTime() != null && !voc.getEndTime().equals(""))
			map.put("endTime", voc.getEndTime());
		ics.exportExcel(map);
		return null;
	}

	public IConsumingService getIcs() {
		return ics;
	}

	public void setIcs(IConsumingService ics) {
		this.ics = ics;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Consuming getBo() {
		return bo;
	}

	public void setBo(Consuming bo) {
		this.bo = bo;
	}

	public VOConsuming getVoc() {
		return voc;
	}

	public void setVoc(VOConsuming voc) {
		this.voc = voc;
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
