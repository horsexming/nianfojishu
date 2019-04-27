package com.task.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ClientManagementServer;
import com.task.Server.IInternalOrderDetailService;
import com.task.Server.IInternalOrderService;
import com.task.Server.IOrderManagerService;
import com.task.Server.IProductManagerService;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.ClientManagement;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.sop.ProcardTemplateBanBenApply;
import com.task.util.MKUtil;

public class InternalOrderAction extends ActionSupport {
	private IInternalOrderService ios;
	private IInternalOrderDetailService iods;
	private IOrderManagerService oms;
	private IProductManagerService ips;
	private ClientManagementServer cms;
	private List list;
	private List<InternalOrder> noStatusList;// 未转换的信息
	private List<ClientManagement> clients;
	private List<ProductManager> detailLis;
	private List<InternalOrder> innerLis;
	private List<ProcardTemplateBanBenApply> procardTemplateBanBenApplyList;
	private List<InternalOrderDetail> iodList;
	private OrderManager om;
	private InternalOrder io;
	private String tidai;
	private InternalOrderDetail iod;
	private boolean ifAgree;
	private boolean bol;
	private int[] num;
	private int[] selected;
	private Float[] selecteds;
	private String[] pieceNum;
	private String[] remerk;
	private String title;
	private String message;
	private String orderNum;
	private String deliveryStatus;
	private String beginTime;
	private String endTime;
	private Integer customeId;
	private int id;
	private String errorMessage;
	private String successMessage;
	private String waitErrorMessage;
	private String pageStatus;
	private String tag;
	private String flag;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String initGenerateInnerOrder() {
		Object[] object = oms.queryNotConversionOrder(om, beginTime, endTime,
				customeId, Integer.parseInt(cpage), pageSize, tag);
		// Object[] object = oms.queryNotConversionOrder(map, Integer
		// .parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			clients = cms.queryAllClient();
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("internalOrder_initGenerateInnerOrder.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "internalOrder_generator";
	}

	@SuppressWarnings("unchecked")
	public String batchConversionOrder() {
		if (selected != null && selected.length > 0) {
			Object[] object = ios.integrationOrderDetail(selected);
			if (object != null && object.length > 0) {
				detailLis = (List<ProductManager>) object[0];
				message = (String) object[1];
				for (int i = 0; i < selected.length; i++) {
					if (i == 0) {
						beginTime = selected[i] + ",";
					} else
						beginTime += selected[i] + ",";
				}
				beginTime = beginTime.substring(0, beginTime.lastIndexOf(","));
			}
			return "internalOrder_initData";
		}
		return null;
	}

	public String batchConversion() {
		if (selecteds != null) {
			try {
				ios.batchConversionOrder(pieceNum, selecteds, remerk, orderNum,
						title, tag);
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = e.getMessage();
				return "error";
			}
		}
		return "internalOrder_batchCon";
	}

	/****
	 * 要货计划直接转换为生产计划
	 * 
	 * @param orderId
	 * @param iodList
	 * @param tag
	 */
	public String yaohuoPlanForIod() {
		try {
			ios.yaohuoPlanForIod(om.getId(), iodList, tag);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
			return "error";
		}
		errorMessage = "添加成功";
		url = "internalOrder_findIodByProductId.action?id=" + iod.getId();
		return ERROR;
	}

	/****
	 * 根据订单明细产品id查找生产计划
	 * 
	 */
	public String findIodByProductId() {
		iodList = ios.findIodByProductId(id);
		return "product_paicheng_calendar";
	}

	/****
	 * 根据订单明细产品id查找生产计划Json
	 * 
	 */
	public void findIodByProductIdForJson() {
		List list1 = ios.findIodByProductId(id);
		for (int i = 0; i < list1.size(); i++) {
			InternalOrderDetail iod = (InternalOrderDetail) list1.get(i);
			iod.setInternalOrder(null);
		}
		MKUtil.writeJSON(list1);
	}

	/****
	 * 查询所有生产计划
	 * 
	 */
	public String findAllIod() {
		iodList = ios.findAllIod(null, null);
		return "product_paicheng_calendar_all";
	}

	/****
	 * 查询所有生产计划 Json
	 * 
	 */
	public void findAllIodForJson() {
		List list1 = ios.findAllIod(null, null);
		for (int i = 0; i < list1.size(); i++) {
			InternalOrderDetail iod = (InternalOrderDetail) list1.get(i);
			iod.setInternalOrder(null);
		}
		MKUtil.writeJSON(list1);
	}

	public String validateNum() {
		if (!ConvertNumber.isNumeric(message) || message.equals("")) {
			ResponseUtil.write("{\"msg\":\"" + "请输入数字！谢谢" + "\"}");
		} else {
			String msg = ios.validateNum(orderNum, title, Integer
					.parseInt(message), beginTime);
			if (msg != null) {
				ResponseUtil.write(msg);
			}
		}
		return null;
	}

	public String initInternalOrder() {
		ios.validateComplete();
		Object[] object = ios.queryAll(Integer.parseInt(cpage), pageSize,
				pageStatus);
		if (object != null && object.length > 0) {
			innerLis = (List) object[0];
			list = (List) object[2];
			clients = cms.queryAllClient();
			bol = (Boolean) object[3];
			if (innerLis == null || innerLis.size() == 0) {
				waitErrorMessage = "没有需要审核的内容!";
			} else {
				waitErrorMessage = "";
			}
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("internalOrder_initInternalOrder.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有审核通过的内容,请稍后再试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "internalOrder_init";
	}

	@SuppressWarnings("unchecked")
	public String queryInternalOrderByCondition() {
		String resultStr = "internalOrder_init";
		Map map = new HashMap();
		if (orderNum != null && !orderNum.equals("")) {
			map.put("orderNum", orderNum);
		}
		if (customeId != null && customeId != 0) {
			map.put("custome", customeId);
		}
		if (beginTime != null && !beginTime.equals("")) {
			map.put("beginTime", beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			map.put("endTime", endTime);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("interCondition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"interCondition");
			} else
				ActionContext.getContext().getSession()
						.remove("interCondition");
		}
		Object[] object = ios.queryInternalOrderByCondition(map, Integer
				.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			innerLis = (List) object[0];
			list = (List) object[2];
			clients = cms.queryAllClient();
			bol = (Boolean) object[3];
			if (innerLis == null && innerLis.size() == 0) {
				waitErrorMessage = "没有需要审核的内容!";
			} else {
				waitErrorMessage = "";
			}
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("internalOrder_queryInternalOrderByCondition.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else
				errorMessage = "没有审核通过的内容,请稍后再试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return resultStr;
	}

	public String queryInternalOrderDetail() {
		if (id != 0) {
			io = ios.getInternalOrderById(id);
			if (io != null) {
				list = iods.queryInternalOrderDetailById(id);
				title = io.getGenertorDate() + "("
						+ io.getCustome().getClientcompanyname() + ")";
				orderNum = io.getNum();
				if (pageStatus != null && pageStatus.equals("all")) {
					bol = true;
				}
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			if (pageStatus != null && pageStatus.equals("turn")
					|| pageStatus.equals("noCard")) {
				return "procard_trunProduct";
			} else
				return "internalOrder_product";
		}
		return null;
	}

	/***
	 * 查询内部计划对应的产品信息 (输出json)
	 */
	public void showOrderProduct() {
		list = iods.queryInternalOrderDetailById(id);
		List<InternalOrderDetail> newList = new ArrayList<InternalOrderDetail>();
		for (int i = 0; i < list.size(); i++) {
			InternalOrderDetail iod = (InternalOrderDetail) list.get(i);
			iod.setInternalOrder(null);
			newList.add(iod);
		}
		MKUtil.writeJSON(newList);
	}

	public String initLogisticsCheck() {
		Object[] object = ios.queryExaminePass(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			clients = cms.queryAllClient();
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("internalOrder_initLogisticsCheck.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "internalOrder_logistics_check";
	}

	public String queryLogisticsCheckByCondition() {
		Map map = new HashMap();
		if (orderNum != null && !orderNum.equals("")) {
			map.put("orderNum", orderNum);
		}
		if (customeId != null && customeId != 0) {
			map.put("custome", customeId);
		}
		if (beginTime != null && !beginTime.equals("")) {
			map.put("beginTime", beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			map.put("endTime", endTime);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("interCheckCondition",
					map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"interCheckCondition");
			} else
				ActionContext.getContext().getSession().remove(
						"interCheckCondition");
		}
		Object[] object = ios.queryExaminePass(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			clients = cms.queryAllClient();
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("internalOrder_queryLogisticsCheckByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "internalOrder_logistics_check";
	}

	public String applyPurchase() {
		if (selected != null && selected.length > 0) {
			String str = ios.countPurchaseAmount(selected);
			if (str != null && str.length() > 0) {
				ResponseUtil.write(ServletActionContext.getResponse(),
						"部分计划产品申请采购!" + str,
						"internalOrder_initLogisticsCheck.action", null);
			} else {
				ResponseUtil.write(ServletActionContext.getResponse(),
						"申请采购成功!", "internalOrder_initLogisticsCheck.action",
						null);
			}
		}
		return null;
	}

	public String auditProcess() {
		if (id != 0) {
			boolean bol = ios.auditOrder(id, ifAgree, pageStatus);
			if (bol) {
				ResponseUtil.write(ServletActionContext.getResponse(),
						"审核操作成功！",
						"internalOrder_initInternalOrder.action?pageStatus="
								+ pageStatus, null);
			} else {
				ResponseUtil.write(ServletActionContext.getResponse(),
						"审核操作失败！",
						"internalOrder_initInternalOrder.action?pageStatus="
								+ pageStatus, null);
			}
		} else {
			ResponseUtil.write(ServletActionContext.getResponse(), "审核操作失败！",
					"internalOrder_initInternalOrder.action?pageStatus="
							+ pageStatus, null);
		}
		return null;
	}

	public String queryOrderManagerByInnerOrder() {
		if (id != 0) {
			Object[] object = ios.queryOrderManagerByinnerOrderId(id);
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("orderManager_initOrder.action");
					errorMessage = null;
				} else
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "orderManager_initOrder";
	}

	public String del() {
		if (id != 0) {
			InternalOrder interOrder = ios.getInternalOrderById(id);
			if (interOrder != null) {
				String msg = ios.delById(id);
				if (msg.equals("true")) {
					ResponseUtil.write(ServletActionContext.getResponse(),
							"删除成功!",
							"internalOrder_initInternalOrder.action?pageStatus="
									+ pageStatus, null);
				} else {
					ResponseUtil.write(ServletActionContext.getResponse(), msg,
							"internalOrder_initInternalOrder.action?pageStatus="
									+ pageStatus, null);
				}
				return null;
			}
		}
		ResponseUtil.write(ServletActionContext.getResponse(), "删除失败!",
				"internalOrder_initInternalOrder.action?pageStatus="
						+ pageStatus, null);
		return null;
	}

	public String initPrint() {
		if (id != 0) {
			InternalOrder interOrder = ios.getInternalOrderById(id);
			if (interOrder != null) {
				list = iods.queryInternalOrderDetailById(id);
				title = interOrder.getGenertorDate() + "("
						+ interOrder.getCustome().getClientcompanyname() + ")";
				orderNum = interOrder.getNum();
				return "internalOrder_initPrint";
			}
		}
		return null;
	}

	public String initProduct() {
		if (id != 0 && customeId != null) {
			iod = iods.getInternalOrderDetailById(id);
			if (iod != null) {
				return "internalOrder_initProduct";
			}
		}
		return null;
	}

	public String updateProduct() {
		if (iod != null) {
			InternalOrderDetail oldInter = iods.getInternalOrderDetailById(iod
					.getId());
			BeanUtils.copyProperties(iod, oldInter, new String[] { "id",
					"name", "pieceNumber", "internalOrder" });
			iods.update(oldInter);
			Map map = new HashMap();
			map.put("id", id);
			ResponseUtil.write(ServletActionContext.getResponse(), "修改成功!",
					"internalOrder_queryInternalOrderDetail.action", map);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), "修改失败!",
				"internalOrder_initInternalOrder.action", null);
		return null;
	}

	/***
	 * 查询所有已同意的内部生产计划(用于生成流水卡片)
	 * 
	 * @param internalOrder
	 * @param pageNo
	 * @param pageSize
	 * 
	 * 
	 * @author 刘培
	 * @return Object[]
	 */
	@SuppressWarnings("unchecked")
	public String findAllAgreeOrder() {
		if (io != null) {
			ActionContext.getContext().getSession().put("io", io);
		} else {
			io = (InternalOrder) ActionContext.getContext().getSession().get(
					"io");
		}
		if (tidai != null) {
			ActionContext.getContext().getSession().put("tidai", tidai);
		} else {
			tidai = (String) ActionContext.getContext().getSession().get(
					"tidai");
			if (io != null) {
				io.setPieceNumber(tidai);
			}
		}

		clients = cms.queryAllClient();
		noStatusList = ios.findNoStatus(io, tag);// 未转完的信息
		Object[] object = ios.findAllAgreeOrder(io, Integer.parseInt(cpage),
				pageSize, tag);
		if (object != null && object.length > 0) {
			innerLis = (List<InternalOrder>) object[0];
			if (innerLis != null && innerLis.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null && pageStatus.length() > 0)
					this
							.setUrl("internalOrder!findAllAgreeOrder.action?pageStatus="
									+ pageStatus);
				else
					this.setUrl("internalOrder!findAllAgreeOrder.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要   查询的内容,请检查后重试!";
			}
		}
		return "proCard_internalOrder";
	}
	public String findPtoTbanbenApply(){
//		procardTemplateBanBenApplyList = ios.findPtoTbanbenApply(id);
		return "sbMassage";
	}
	public IInternalOrderService getIos() {
		return ios;
	}

	public void setIos(IInternalOrderService ios) {
		this.ios = ios;
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

	public IOrderManagerService getOms() {
		return oms;
	}

	public void setOms(IOrderManagerService oms) {
		this.oms = oms;
	}

	public ClientManagementServer getCms() {
		return cms;
	}

	public void setCms(ClientManagementServer cms) {
		this.cms = cms;
	}

	public List getList() {
		return list;
	}

	public List<ClientManagement> getClients() {
		return clients;
	}

	public void setClients(List<ClientManagement> clients) {
		this.clients = clients;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public OrderManager getOm() {
		return om;
	}

	public void setOm(OrderManager om) {
		this.om = om;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IProductManagerService getIps() {
		return ips;
	}

	public void setIps(IProductManagerService ips) {
		this.ips = ips;
	}

	public List<ProductManager> getDetailLis() {
		return detailLis;
	}

	public void setDetailLis(List<ProductManager> detailLis) {
		this.detailLis = detailLis;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InternalOrder getIo() {
		return io;
	}

	public void setIo(InternalOrder io) {
		this.io = io;
	}

	public List<InternalOrder> getInnerLis() {
		return innerLis;
	}

	public void setInnerLis(List<InternalOrder> innerLis) {
		this.innerLis = innerLis;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCustomeId() {
		return customeId;
	}

	public void setCustomeId(Integer customeId) {
		this.customeId = customeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public String[] getRemerk() {
		return remerk;
	}

	public void setRemerk(String[] remerk) {
		this.remerk = remerk;
	}

	public int[] getNum() {
		return num;
	}

	public void setNum(int[] num) {
		this.num = num;
	}

	public String[] getPieceNum() {
		return pieceNum;
	}

	public void setPieceNum(String[] pieceNum) {
		this.pieceNum = pieceNum;
	}

	public IInternalOrderDetailService getIods() {
		return iods;
	}

	public void setIods(IInternalOrderDetailService iods) {
		this.iods = iods;
	}

	public InternalOrderDetail getIod() {
		return iod;
	}

	public void setIod(InternalOrderDetail iod) {
		this.iod = iod;
	}

	public void setList(List list) {
		this.list = list;
	}

	public boolean isIfAgree() {
		return ifAgree;
	}

	public void setIfAgree(boolean ifAgree) {
		this.ifAgree = ifAgree;
	}

	public String getWaitErrorMessage() {
		return waitErrorMessage;
	}

	public void setWaitErrorMessage(String waitErrorMessage) {
		this.waitErrorMessage = waitErrorMessage;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public void setCustomeId(int customeId) {
		this.customeId = customeId;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List<InternalOrder> getNoStatusList() {
		return noStatusList;
	}

	public void setNoStatusList(List<InternalOrder> noStatusList) {
		this.noStatusList = noStatusList;
	}

	public String getTidai() {
		return tidai;
	}

	public void setTidai(String tidai) {
		this.tidai = tidai;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<InternalOrderDetail> getIodList() {
		return iodList;
	}

	public void setIodList(List<InternalOrderDetail> iodList) {
		this.iodList = iodList;
	}

	public List<ProcardTemplateBanBenApply> getProcardTemplateBanBenApplyList() {
		return procardTemplateBanBenApplyList;
	}

	public void setProcardTemplateBanBenApplyList(
			List<ProcardTemplateBanBenApply> procardTemplateBanBenApplyList) {
		this.procardTemplateBanBenApplyList = procardTemplateBanBenApplyList;
	}

	/**
	 * @return the selecteds
	 */
	public Float[] getSelecteds() {
		return selecteds;
	}

	/**
	 * @param selecteds the selecteds to set
	 */
	public void setSelecteds(Float[] selecteds) {
		this.selecteds = selecteds;
	}
	
}
