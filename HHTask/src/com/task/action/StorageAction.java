package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.IStorageService;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.OaAppDetail;
import com.task.entity.Storage;
import com.task.entity.StorageForm;
import com.task.entity.Store;
import com.task.entity.VOStorage;
import com.task.entity.VOStore;

/***
 * 综合库 入库
 * 
 * @author zjs
 * 
 */
@SuppressWarnings("unchecked")
public class StorageAction extends ActionSupport{
	private IStorageService iss;
	private List list;
	private String errorMessage;
	private Storage sto;
	private Store st;
	private OaAppDetail oa;
	private List<OaAppDetail> oaDList;//oa采购
	private VOStorage vsto = new VOStorage();
	private VOStore vos = new VOStore();
	private StorageForm sf = new StorageForm();
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String initQueryStorage() {
		Object[] object = iss.queryStorage(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_initQueryStorage.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String queryStorageByCondition() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vsto.getMatetag() != null && !vsto.getMatetag().equals(""))
			map.put("matetag", vsto.getMatetag());
		if (vsto.getFormat() != null && !vsto.getFormat().equals(""))
			map.put("format", vsto.getFormat());
		if (vsto.getStorage() != null && !vsto.getStorage().equals(""))
			map.put("storages", vsto.getStorage());
		if (vsto.getCategory() != null && !vsto.getCategory().equals(""))
			map.put("category", vsto.getCategory());
		if (vsto.getPlace() != null && !vsto.getPlace().equals(""))
			map.put("place", vsto.getPlace());
		if (vsto.getNumber() != null && !vsto.getNumber().equals(""))
			map.put("number", vsto.getNumber());
		if (vsto.getStartTime() != null && !vsto.getStartTime().equals(""))
			map.put("startTime", vsto.getStartTime());
		if (vsto.getEndTime() != null && !vsto.getEndTime().equals(""))
			map.put("endTime", vsto.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("storages", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"storages");
			} else
				ActionContext.getContext().getSession().remove("storages");
		}
		Object[] object = iss.queryStorage(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_queryStorageByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String del() {
		if (vsto.getId() != null) {
			iss.delStorageById(vsto.getId());
		}
		return "redirectList";
	}

	public String initUpdate() {
		if (vsto.getId() != null) {
			sto = iss.getStorageById(vsto.getId());
			if (sto == null) {
				return "redirectList";
			}
			return "success";
		}
		return "redirectList";
	}

	public String update() {
		String msg = "";
		if (sto != null && sto.getId() != null) {
			try {
				msg = iss.update(sto);
				if (!"".equals(msg)) {
					ResponseUtil.write(ServletActionContext.getResponse(), msg,
							"storage_initQueryStorage.action", null);
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "修改失败";
				ResponseUtil.write(ServletActionContext.getResponse(), msg,
						"storage_initQueryStorage.action", null);
				return null;
			}
		}
		return "redirectList";
	}

	public String export() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vsto.getStorage() != null && !vsto.getStorage().equals(""))
			map.put("storages", vsto.getStorage());
		if (vsto.getCategory() != null && !vsto.getCategory().equals(""))
			map.put("category", vsto.getCategory());
		if (vsto.getPlace() != null && !vsto.getPlace().equals(""))
			map.put("place", vsto.getPlace());
		if (vsto.getStartTime() != null && !vsto.getStartTime().equals(""))
			map.put("startTime", vsto.getStartTime());
		if (vsto.getEndTime() != null && !vsto.getEndTime().equals(""))
			map.put("endTime", vsto.getEndTime());
		iss.exportExcel(map);
		return null;
	}

	//扫描条码入库
	public String scanningApplyFor() {
		Object[] object = iss.queryPurchase(vsto.getApplyForNum(), Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_scanningApplyFor.action?vsto.applyForNum="
						+ vsto.getApplyForNum());
				errorMessage = null;
			} else
				errorMessage = "";
		} else
			errorMessage = "";
		return "success";
	}

	// 入库
	public String getOaAppDetail() {
		if (vsto != null && vsto.getOaDetailId() != null) {
			oa = iss.getOaAppDetail(vsto.getOaDetailId());
			// 扫描条码入库
			if (vsto.getJump().equals("scanning")) {
				return "scanningApplyFor";
				// 批量入库
			} else if (vsto.getJump().equals("select")) {
				return "storage_warehousingfill";
			} else if (vsto.getJump().equals("gz")) {
				return "storage_warehousingfill";
			}
		}
		return null;
	}
	//批量入库，填写入库详细
	public String storageProducts(){
		//判断编号是否存在
		String msg = null;
		Map<String, Object> map = null;
		//判断
		/*Storage storage = iss.getnumberId(sf.getNumber());
		if (storage!=null) {
			errorMessage = "编号为 ："+sf.getNumber()+"物品已添加入库，请勿重复添加,谢谢！";
			return ERROR;
		}*/
		oa = null;
		oa = iss.getOaAppDetail1(vsto.getOaDetailId());
		if (sf.getNum()>oa.getDetailCount()) {
			errorMessage = "编号为 ："+sf.getNumber()+"物品入库数量以超过上限，请勿重复添加,谢谢！";
			return ERROR;
		}
		/*oa = iss.getOaAppDetail1(vsto.getOaDetailId());
		if (sf.getNum()>oa.getDetailCount()) {
			errorMessage = "编号为 ："+sf.getNumber()+"物品入库数量以超过上限，请勿重复添加,谢谢！";
			return ERROR;
		}*/
			if (vsto.getJump().equals("scanning")) {
				if (vsto.getJump() != null && !vsto.getJump().equals("")) {
				map = new HashMap<String, Object>();
				map.put("vsto.applyForNum", vsto.getApplyForNum());
			}
		}
		try {
			msg = iss.storageProducts(sf, vsto);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "入库失败!";
		}
		String jump = vsto.getJump();
		if (jump != null && !jump.equals("")) {
			if (jump.equals("gz")) {
				ResponseUtil.write(ServletActionContext.getResponse(), msg,
						"storage_showYiCunGui.action", map);
			}else if (jump.equals("scanning")) {
				ResponseUtil.write(ServletActionContext.getResponse(), msg,
						"storage_scanningApplyFor.action", map);
			} else if (jump.equals("manual")) {
				ResponseUtil.write(ServletActionContext.getResponse(), msg,
						"storage_initSelectStorage.action", null);
			} else {
				ResponseUtil.write(ServletActionContext.getResponse(), msg,
						"storage_initSelectStorage.action", null);
			}
		}
		return null;
	}

	public String initSelectStorage() {
		Object[] object = iss.queryDetailByCondition(null, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_initSelectStorage.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "storage_selectStorage";
	}

	/**
	 * 查询已存柜的物品=>入库
	 * @return
	 */
	public String showYiCunGui() {
		if (oa != null) ActionContext.getContext().getSession().put("oadetailRugui", oa);
		else oa = (OaAppDetail) ActionContext.getContext().getSession().get("oadetailRugui");
		Object[] object = iss.findOADetail_rugui(oa, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<OaAppDetail>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_showYiCunGui.action");
			}
			errorMessage = null;
		} else errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "storage_selectRuGuiStorage";
	}
	
	public String selectStorage() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vsto.getMatetag() != null && !vsto.getMatetag().equals(""))
			map.put("name", vsto.getMatetag());
		if (vsto.getApplyForNum() != null && !vsto.getApplyForNum().equals(""))
			map.put("number", vsto.getApplyForNum());
		if (vsto.getStatus() != null && !vsto.getStatus().equals("状态"))
			map.put("status", vsto.getStatus());
		if (vsto.getFormat() != null && !vsto.getFormat().equals(""))
			map.put("format", vsto.getFormat());
		if (vsto.getDept() != null && !vsto.getDept().equals(""))
			map.put("dept", vsto.getDept());
		if (vsto.getMonth() != null && !vsto.getMonth().equals(""))
			map.put("month", vsto.getMonth());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("select", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"select");
			} else
				ActionContext.getContext().getSession().remove("select");
		}
		Object[] object = iss.queryDetailByCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_selectStorage.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "storage_selectStorage";
	}

	// 选择入库
	public String statisticalProcurement() {
		if (vsto != null && vsto.getSelected() != null
				&& vsto.getSelected().length > 0) {
			Object[] obj = iss.getCountProcurement(vsto.getSelected());
			if (obj != null && obj.length > 0) {
				oa = (OaAppDetail) obj[0];
				vsto.setNumber((String) obj[1]);
				return "warehousingfill";
			}
		}
		return "initSelectStorage";
	}

	public String initStorageHistory() {
		Object[] object = iss.queryStorageHistory(null,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_initStorageHistory.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "storageHistory";
	}

	public String historyIndex() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (vsto.getStartTime() != null && !vsto.getStartTime().equals(""))
			map.put("startTime", vsto.getStartTime());
		if (vsto.getEndTime() != null && !vsto.getEndTime().equals(""))
			map.put("endTime", vsto.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("historyStorage", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"historyStorage");
			} else
				ActionContext.getContext().getSession()
						.remove("historyStorage");
		}
		Object[] object = iss.queryStorageHistory(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("storage_historyIndex.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "success";
	}

	public String printStorage() {
		if (vsto != null && vsto.getSelected() != null
				&& vsto.getSelected().length > 0) {
			list = iss.printStorage(vsto.getSelected());
		}
		return "success";
	}

	/***
	 * 手动入库
	 * 
	 * @return
	 */
	public String addStorage() {
		iss.addStorage(sf,vsto);
		return "redirectList";
	}

	public IStorageService getIss() {
		return iss;
	}

	public void setIss(IStorageService iss) {
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

	public Storage getSto() {
		return sto;
	}

	public void setSto(Storage sto) {
		this.sto = sto;
	}

	public VOStorage getVsto() {
		return vsto;
	}

	public void setVsto(VOStorage vsto) {
		this.vsto = vsto;
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

	public OaAppDetail getOa() {
		return oa;
	}

	public void setOa(OaAppDetail oa) {
		this.oa = oa;
	}

	public Store getSt() {
		return st;
	}

	public void setSt(Store st) {
		this.st = st;
	}

	public VOStore getVos() {
		return vos;
	}

	public void setVos(VOStore vos) {
		this.vos = vos;
	}

	public StorageForm getSf() {
		return sf;
	}

	public void setSf(StorageForm sf) {
		this.sf = sf;
	}

	public List<OaAppDetail> getOaDList() {
		return oaDList;
	}

	public void setOaDList(List<OaAppDetail> oaDList) {
		this.oaDList = oaDList;
	}

}
