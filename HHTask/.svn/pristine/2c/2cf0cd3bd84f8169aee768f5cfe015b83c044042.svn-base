package com.task.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IBorrowService;
import com.task.entity.Borrow;
import com.task.entity.VOBorrow;

@SuppressWarnings("unchecked")
public class BorrowAction {
	private IBorrowService ibs;
	private List list;
	private List<Borrow> borrowList;
	private String errorMessage;
	private static final String NOTGIVEkBACK = "未归还";
	private static final String ERROR = null;
	private Borrow bo;
	private VOBorrow vobo = new VOBorrow();

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Date date1;
	private Date date2;
	private String fileName;
	private String tag;

	public String initQueryBorrow() {
		Object[] object = ibs.queryBorrow(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_initQueryBorrow.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "borrow_index";
	}
	
	/**
	 * 查询所有借还工装 未开门操作
	 * @return
	 */
	public String showCodeBorrow() {
		if (bo != null) ActionContext.getContext().getSession().put("Borrows", bo);
		else bo = (Borrow) ActionContext.getContext().getSession().get("Borrows");
		Object[] object = ibs.showCodeBorrow(bo, Integer.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			list = (List<Borrow>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_sjowCodeBorrow.action?tag="+tag);
			}
			errorMessage = null;
		} else errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "borrow_DaiIndex";
	}

	
	public String queryBorrowByCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vobo.getDept() != null && !vobo.getDept().equals(""))
			map.put("dept", vobo.getDept());
		if (vobo.getCardId() != null && !vobo.getCardId().equals(""))
			map.put("cardId", vobo.getCardId());
		if (vobo.getPerson() != null && !vobo.getPerson().equals(""))
			map.put("person", vobo.getPerson());
		if (vobo.getName() != null && !vobo.getName().equals(""))
			map.put("name", vobo.getName());
		if (vobo.getStandard() != null && !vobo.getStandard().equals(""))
			map.put("standard", vobo.getStandard());
		if (vobo.getNumber() != null && !vobo.getNumber().equals(""))
			map.put("number", vobo.getNumber());
		if (vobo.getPieceNum() != null && !vobo.getPieceNum().equals(""))
			map.put("pieceNum", vobo.getPieceNum());
		if (vobo.getStorehouse() != null && !vobo.getStorehouse().equals(""))
			map.put("storehouse", vobo.getStorehouse());
		if (vobo.getStartTime() != null && !vobo.getStartTime().equals(""))
			map.put("startTime", vobo.getStartTime());
		if (vobo.getEndTime() != null && !vobo.getEndTime().equals(""))
			map.put("endTime", vobo.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("also", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get("also");
			} else
				ActionContext.getContext().getSession().remove("also");
		}
		Object[] object = ibs.queryBorrow(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_queryBorrowByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "borrow_index";
	}

	public String del() {
		if (vobo.getId() != null) {
			ibs.delBorrowById(vobo.getId());
		}
		return "redirectList";
	}

	public String initUpdate() {
		if (vobo.getId() != null) {
			bo = ibs.getBorrowById(vobo.getId());
			if (bo == null) {
				return "redirectList";
			}
			return "success";
		}
		return "redirectList";
	}

	public String update() {
		if (bo != null && bo.getId() != null) {
			Borrow oldBorrow = ibs.getBorrowById(bo.getId());
			BeanUtils.copyProperties(bo, oldBorrow, new String[] { "id",
					"store", "alsos" });
			ibs.update(oldBorrow);
			return "redirectList";
		}
		return "redirectList";
	}

	public String initAdd() {
		return "success";
	}

	public String add() {
		if (bo != null) {
			ibs.add(bo);
		}
		return "redirectList";
	}

	public String export() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vobo.getDept() != null && !vobo.getDept().equals("")) {
			map.put("dept", vobo.getDept());
		}
		if (vobo.getCardId() != null && !vobo.getCardId().equals("")) {
			map.put("cardId", vobo.getCardId());
		}
		if (vobo.getPerson() != null && !vobo.getPerson().equals("")) {
			map.put("person", vobo.getPerson());
		}
		if (vobo.getNumber() != null && !vobo.getNumber().equals("")) {
			map.put("number", vobo.getNumber());
		}
		if (vobo.getStorehouse() != null && !vobo.getStorehouse().equals("")) {
			map.put("storehouse", vobo.getStorehouse());
		}
		if (vobo.getStartTime() != null && !vobo.getStartTime().equals("")) {
			map.put("startTime", vobo.getStartTime());
		}
		if (vobo.getEndTime() != null && !vobo.getEndTime().equals("")) {
			map.put("endTime", vobo.getEndTime());
		}
		ibs.exportExcel(map);
		return null;
	}

	// 导出Excel
	public String borrowexcel() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vobo.getPerson() != null && !vobo.getPerson().equals(""))
			map.put("person", vobo.getPerson());
		if (vobo.getName() != null && !vobo.getName().equals(""))
			map.put("name", vobo.getName());
		if (vobo.getDept() != null && !vobo.getDept().equals(""))
			map.put("dept", vobo.getDept());
		if (vobo.getFormat() != null && !vobo.getFormat().equals(""))
			map.put("format", vobo.getFormat());
		if (vobo.getStartTime() != null && !vobo.getStartTime().equals(""))
			map.put("startTime", vobo.getStartTime());
		if (vobo.getEndTime() != null && !vobo.getEndTime().equals(""))
			map.put("endTime", vobo.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("byStatistics", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"byStatistics");
			} else
				ActionContext.getContext().getSession().remove("byStatistics");
		}
		fileName = ibs.exportExcel(map,vobo.getStartTime(), vobo.getEndTime());
		if (fileName != null && !"no".equals(fileName)) {
			return "generateexcel";
		} else {
			errorMessage = "导出数据出错，请检查后重试!";
			return ERROR;
		}
	}

	public String statistics() {
		Object[] object = ibs.queryStatistics(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_statistics.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "statistics";
	}

	public String statisticsByCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vobo.getPerson() != null && !vobo.getPerson().equals(""))
			map.put("person", vobo.getPerson());
		if (vobo.getName() != null && !vobo.getName().equals(""))
			map.put("name", vobo.getName());
		if (vobo.getDept() != null && !vobo.getDept().equals(""))
			map.put("dept", vobo.getDept());
		if (vobo.getFormat() != null && !vobo.getFormat().equals(""))
			map.put("format", vobo.getFormat());
		if (vobo.getStartTime() != null && !vobo.getStartTime().equals(""))
			map.put("startTime", vobo.getStartTime());
		if (vobo.getEndTime() != null && !vobo.getEndTime().equals(""))
			map.put("endTime", vobo.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("byStatistics", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"byStatistics");
			} else
				ActionContext.getContext().getSession().remove("byStatistics");
		}
		Object[] object = ibs.queryStatistics(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("borrow_statisticsByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "statistics";
	}

	public String queryBorByNum() {
		if (vobo != null && vobo.getCardNum() != null
				&& !vobo.getCardNum().equals("")) {
			list = ibs.queryBrrowByCardNum(vobo.getCardNum(), NOTGIVEkBACK);
			if (list == null || list.size() < 1) {
				errorMessage = "没有找到你要归还的东西,谢谢合作!";
				return "error";
			}
		}
		return "also_giveBack";
	}

	public String queryBorById() {
		if (vobo != null && !vobo.getId().equals("")
				&& !vobo.getOpType().equals("")) {
			bo = ibs.getBorrowById(vobo.getId());
			if (vobo.getOpType().equals("also"))
				return "store_return";
			else
				return "scrap_return";
		}
		return null;
	}

	public IBorrowService getIbs() {
		return ibs;
	}

	public void setIbs(IBorrowService ibs) {
		this.ibs = ibs;
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

	public VOBorrow getVobo() {
		return vobo;
	}

	public void setVobo(VOBorrow vobo) {
		this.vobo = vobo;
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

	public Borrow getBo() {
		return bo;
	}

	public void setBo(Borrow bo) {
		this.bo = bo;
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

	public static String getNotgivekback() {
		return NOTGIVEkBACK;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<Borrow> getBorrowList() {
		return borrowList;
	}
	public void setBorrowList(List<Borrow> borrowList) {
		this.borrowList = borrowList;
	}
	public static String getError() {
		return ERROR;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

}
