package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IStoreService;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.FanghuOutLib;
import com.task.entity.Store;
import com.task.entity.VOStore;
import com.task.entity.VOUser;
import com.task.util.MKUtil;

public class StoreAction {

	private IStoreService iss;
	private List list;
	private VOStore vos = new VOStore();
	private String errorMessage;
	private Store store;
	private Integer id;
	private String number;
	private String cardNum;
	private String storehouse;
	private String matetag;
	private String place;
	private String format;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String pageStauts;
	private FanghuOutLib fanghuOutLib;
	private int fhid;

	public String initQueryStore() {
		Object[] object = iss.queryStore(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("store_initQueryStore.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";//store_index.jsp
	}

	public String queryStoreByCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (number != null && !number.equals(""))
			map.put("number", number);
		if (matetag != null && !matetag.equals(""))
			map.put("name", matetag);
		if (format != null && !format.equals(""))
			map.put("format", format);
		if (storehouse != null && !storehouse.equals("")
				&& !storehouse.equals("选择性质"))
			map.put("storehouse", storehouse);
		if (place != null && !place.equals(""))
			map.put("place", place);
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("store", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession()
						.get("store");
			} else
				ActionContext.getContext().getSession().remove("store");
		}
		Object[] object = iss
				.queryStore(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("store_queryStoreByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String del() {
		if (id != null)
			iss.delStoreById(id);
		return "redirectList";
	}

	public String initUpdate() {
		if (id != null && !id.equals("")) {
			store = iss.getStoreById(id);
			if (store == null)
				return "redirectList";
			return "success";
		}
		return "redirectList";
	}

	public String update() {
		if (store != null && store.getId() != null) {
			Store oldStore = iss.getStoreById(store.getId());
			BeanUtils.copyProperties(store, oldStore, new String[] { "id",
					"mix", "period", "startDate", "curAmount", "maxBorrowNum",
					"more1", "price", "carePeriod", "curworkAmount",
					"lastCareDate", "serverCardId", "duizhang", "minStore",
					"appDept", "totMoney", "storages", "scraps", "maintains",
					"borrows", "alsos", "renews", "outlibs" });
			try {
				iss.update(oldStore);
				int count = iss.updateStorage(oldStore);
				errorMessage = "修改成功!";
				// ActionContext.getContext().put("errorMessage", "修改成功!");
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "修改失败!";
			}
			return "error";
		}
		return "redirectList";
	}

	public String export() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (number != null && !number.equals(""))
			map.put("number", number);
		if (matetag != null && !matetag.equals(""))
			map.put("name", matetag);
		if (format != null && !format.equals(""))
			map.put("format", format);
		if (storehouse != null && !storehouse.equals("")
				&& !storehouse.equals("选择性质"))
			map.put("storehouse", storehouse);
		if (place != null && !place.equals(""))
			map.put("place", place);
		iss.exportExcel(map);
		return null;
	}

	public String exportStatistics() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vos.getMatetag() != null && !vos.getMatetag().equals(""))
			map.put("name", vos.getMatetag());
		if (vos.getStartTime() != null && !vos.getStartTime().equals(""))
			map.put("startTime", vos.getStartTime());
		if (vos.getEndTime() != null && !vos.getEndTime().equals(""))
			map.put("endTime", vos.getEndTime());
		iss.exportStatisticsExcel(map);
		return null;
	}

	/** depre */
	public String initBorrow() {
		if (id != null && !id.equals("")) {
			store = iss.getStoreById(id);
			if (store == null)
				return "redirectList";
			return "success";
		}
		return "redirectList";
	}

	
	
	public String initBorrowOrConsuming() {
		String url = "";
		if (vos.getFormUrl() == null && vos.getFormat().equals("")) {
			return null;
		}
		if (vos.getFormUrl().equals("loan"))
			url = "store_initBorrow";
		else if (vos.getFormUrl().equals("consuming"))
			url = "initConsuming";
		else
			return null;
		if (id != null && !id.equals("")) {
			store = iss.getStoreById(id);
			if (store == null) {
				return "redirectList";
			}
			return url;
		}
		return "redirectList";
	}

	public String repairs() {
		if (id != null && !id.equals("")) {
			store = iss.getStoreById(id);
			if (store == null) {
				return "redirectList";
			}
			return "repairs";
		}
		return "redirectList";
	}

	//借出方法
	public String lend() {
		if (vos != null) {
			String msg = iss.lend(vos, fhid);
			if (!msg.equals("")) {
				Map<String, Object> map = new HashMap<String, Object>();
				String url = "";
				if (vos.getFormUrl().equals("borrow")) {
					url = "loan";
					map.put("vos.formUrl", url);
					ResponseUtil.write(ServletActionContext.getResponse(), msg,
							"store_initQueryLoan.action", map);
					return null;
				} else if (vos.getFormUrl().equals("consuming")) {
					url = "consuming";
					map.put("vos.formUrl", url);
					if (!msg.equals("领用成功!")) {
						ResponseUtil.write(ServletActionContext.getResponse(),
								msg, "store_initQueryLoan.action", map);
						return null;
					}
					return "consumings";
				}
			}
		}
		return null;
	}

	public String getUser() {
		if (cardNum != null) {
			VOUser u = iss.getUserByCardId(cardNum);
			fanghuOutLib = iss.findFanghuOutLib(matetag, format, u.getId());
			Map<String, Object> maps = new HashMap<String, Object>();
			if (pageStauts != null && pageStauts.length() > 0) {
				if ("zw".equals(pageStauts) && fanghuOutLib != null) {
					MKUtil.writeJSON(false, "防护用品请到防护用品领用", null);
					return null;
				} else if ("fh".equals(pageStauts) && fanghuOutLib != null) {
					maps.put("user", u);
					maps.put("fanghuOutLib", fanghuOutLib);
					MKUtil.writeJSON(true, "", maps);
					return null;
				}
			}
			maps.put("user", u);
			MKUtil.writeJSON(true, null, maps);
		}
		return null;
	}

	public String initQueryLoan() {	
		String jumpUrl = "";
		String classification = "";
		if (vos.getFormUrl() == null && vos.getFormat().equals(""))
			return null;
		if (vos.getFormUrl().equals("store_LoanIndex")) {
			jumpUrl = "store_LoanIndex";
			classification = "可借用";
		} else if (vos.getFormUrl().equals("consuming")) {
			jumpUrl = "consuming";
			classification = "领用";
		} else
			return null;
		Object[] object = iss.queryLoanByCondition(null, Integer
				.parseInt(cpage), pageSize, classification);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("store_initQueryLoan.action?vos.formUrl="
								+ jumpUrl);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return jumpUrl;
	}
	
	
	

	public String queryLoanByCondition() {
		String jumpUrl = "";
		String classification = "";
		if (vos.getFormUrl() == null && vos.getFormat().equals(""))
			return null;
		if (vos.getFormUrl().equals("store_LoanIndex")) {
			jumpUrl = "store_LoanIndex";
			classification = "可借用";
		} else if (vos.getFormUrl().equals("consuming")) {
			jumpUrl = "consuming";
			classification = "领用";
		} else
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		if (vos.getNumber() != null && !vos.getNumber().equals(""))
			map.put("number", vos.getNumber());
		if (vos.getMatetag() != null && !vos.getMatetag().equals(""))
			map.put("matetag", vos.getMatetag());
		if (vos.getFormat() != null && !vos.getFormat().equals(""))
			map.put("format", vos.getFormat());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("loan", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get("loan");
			} else
				ActionContext.getContext().getSession().remove("loan");
		}
		Object[] object = iss.queryLoanByCondition(map,
				Integer.parseInt(cpage), pageSize, classification);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("store_queryLoanByCondition.action?vos.formUrl="
						+ jumpUrl);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return jumpUrl;
	}

	/***
	 * 月度数据汇总
	 * 
	 * @return
	 */
	public String initMonthlyStatistics() {
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] object = iss.monthlyStatistics(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("store_initMonthlyStatistics.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "statistics";
	}

	@SuppressWarnings("unchecked")
	public String queryMonthlyStatistics() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vos.getMatetag() != null && !vos.getMatetag().equals(""))
			map.put("name", vos.getMatetag());
		if (vos.getParClass() != null && !vos.getParClass().equals(""))
			map.put("parClass", vos.getParClass());
		if (vos.getStartTime() != null && !vos.getStartTime().equals(""))
			map.put("startTime", vos.getStartTime());
		if (vos.getEndTime() != null && !vos.getEndTime().equals(""))
			map.put("endTime", vos.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("Monthly", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {

				map = (Map) ActionContext.getContext().getSession().get(
						"Monthly");
			} else
				ActionContext.getContext().getSession().remove("store");
		}
		Object[] object = iss.monthlyStatistics(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("store_queryMonthlyStatistics.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "statistics";
	}

	/**
	 * 修改领用周期page
	 * 
	 * @return
	 */
	public String getStoreUpdatePage() {
		if (id != null && !id.equals("")) {
			store = iss.getStoreById(id);
		}
		return "getStoreUpdatePage_success";
	}

	/**
	 * 修改领用周期
	 * 
	 * @return
	 */
	public String updateStore() {

		return "updateStore_success";
	}

	public IStoreService getIss() {
		return iss;
	}

	public void setIss(IStoreService iss) {
		this.iss = iss;
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public String getMatetag() {
		return matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public VOStore getVos() {
		return vos;
	}

	public void setVos(VOStore vos) {
		this.vos = vos;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getPageStauts() {
		return pageStauts;
	}

	public void setPageStauts(String pageStauts) {
		this.pageStauts = pageStauts;
	}

	public FanghuOutLib getFanghuOutLib() {
		return fanghuOutLib;
	}

	public void setFanghuOutLib(FanghuOutLib fanghuOutLib) {
		this.fanghuOutLib = fanghuOutLib;
	}

	public int getFhid() {
		return fhid;
	}

	public void setFhid(int fhid) {
		this.fhid = fhid;
	}

}
