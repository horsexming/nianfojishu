package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IAlsoService;
import com.task.entity.Also;
import com.task.entity.VOAlso;

@SuppressWarnings("unchecked")
public class AlsoAction {
	private IAlsoService ias;
	private List list;
	private String errorMessage;
	private Also al;
	private VOAlso voal = new VOAlso();
	private boolean flag = false;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String tag;
	private int pageSize = 15;

	/**
	 * 查询所有归还后的工装 未开门操作
	 * @return
	 */
	public String showCodeAlso() {
		if (al != null) ActionContext.getContext().getSession().put("Alsos", al);
		else al = (Also) ActionContext.getContext().getSession().get("Alsos");
		Object[] object = ias.showCodeAlso(al, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			list = (List<Also>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_sjowCodeBorrow.action?tag="+tag);
			}
			errorMessage = null;
		} else errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "also_DaiIndex";
	}
	
	public String initQueryAlso() {
		Object[] object = ias
				.queryAlso(null, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("also_initQueryAlso.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "also_index";
	}

	public String queryAlsoByCondition() {
		Map map = new HashMap();
		if (voal.getDept() != null && !voal.getDept().equals("")) {
			map.put("dept", voal.getDept());
		}
		if (voal.getCardId() != null && !voal.getCardId().equals("")) {
			map.put("cardId", voal.getCardId());
		}
		if (voal.getPerson() != null && !voal.getPerson().equals("")) {
			map.put("person", voal.getPerson());
		}
		if (voal.getName() != null && !voal.getName().equals("")) {
			map.put("name", voal.getName());
		}
		if (voal.getStandard() != null && !voal.getStandard().equals("")) {
			map.put("standard", voal.getStandard());
		}
		if (voal.getNumber() != null && !voal.getNumber().equals("")) {
			map.put("number", voal.getNumber());
		}
		if (voal.getPieceNum() != null && !voal.getPieceNum().equals("")) {
			map.put("pieceNum", voal.getPieceNum());
		}
		if (voal.getStorehouse() != null && !voal.getStorehouse().equals("")) {
			map.put("storehouse", voal.getStorehouse());
		}
		if (voal.getStartTime() != null && !voal.getStartTime().equals("")) {
			map.put("startTime", voal.getStartTime());
		}
		if (voal.getEndTime() != null && !voal.getEndTime().equals("")) {
			map.put("endTime", voal.getEndTime());
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("also", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get("also");
			} else
				ActionContext.getContext().getSession().remove("also");
		}
		Object[] object = ias.queryAlso(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("also_queryAlsoByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "also_index";
	}

	public String del() {
		if (voal.getId() != null) {
			al = ias.getAlsoById(voal.getId());
			ias.del(al);
		}
		return "redirectList";
	}

	public String initUpdate() {
		if (voal.getId() != null) {
			al = ias.getAlsoById(voal.getId());
			if (al == null) {
				return "redirectList";
			}
			return "success";
		}
		return "redirectList";
	}

	public String update() {
		if (al != null && al.getId() != null) {
			Also oldAlso = ias.getAlsoById(al.getId());
			BeanUtils.copyProperties(al, oldAlso,
					new String[] { "id", "store" });
			ias.update(oldAlso);
			return "redirectList";
		}
		return "redirectList";
	}

	public String initAdd() {
		return "success";
	}

	public String add() {
		if (al != null) {
			ias.add(al);
		}
		return "redirectList";
	}

	public String export() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (voal.getDept() != null && !voal.getDept().equals("")) {
			map.put("dept", voal.getDept());
		}
		if (voal.getCardId() != null && !voal.getCardId().equals("")) {
			map.put("cardId", voal.getCardId());
		}
		if (voal.getPerson() != null && !voal.getPerson().equals("")) {
			map.put("person", voal.getPerson());
		}
		if (voal.getNumber() != null && !voal.getNumber().equals("")) {
			map.put("number", voal.getNumber());
		}
		if (voal.getStorehouse() != null && !voal.getStorehouse().equals("")) {
			map.put("storehouse", voal.getStorehouse());
		}
		if (voal.getStartTime() != null && !voal.getStartTime().equals("")) {
			map.put("startTime", voal.getStartTime());
		}
		if (voal.getEndTime() != null && !voal.getEndTime().equals("")) {
			map.put("endTime", voal.getEndTime());
		}
		ias.exportExcel(map);
		return null;
	}

	/**
	 * 归还
	 * 
	 * @Title: alsoStore
	 * @return String
	 * @throws
	 */
	public String alsoStore() {
		if (al != null) {
			try {
				String msg = ias.alsoGoods(al);
				if (!msg.equals("")) {
					flag = true;
					voal.setMsg(msg);
				}
			} catch (Exception e) {
				flag = true;
				voal.setMsg("归还失败!");
			}
		}
		return "giveBackMsg";
	}

	public IAlsoService getIas() {
		return ias;
	}

	public void setIas(IAlsoService ias) {
		this.ias = ias;
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

	public Also getAl() {
		return al;
	}

	public void setAl(Also al) {
		this.al = al;
	}

	public VOAlso getVoal() {
		return voal;
	}

	public void setVoal(VOAlso voal) {
		this.voal = voal;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
