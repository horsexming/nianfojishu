package com.task.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ClientManagementServer;
import com.task.Server.IOrderManagerService;
import com.task.Server.IProductManagerService;
import com.task.Server.WareHouseAuthService;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.ServerImpl.yw.FileUtil;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.ClientManagement;
import com.task.entity.Evaluators;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.TaHkHuikuan;
import com.task.entity.TaHkPartBackMoney;
import com.task.entity.Users;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.Procard;
import com.task.entity.sop.YcProduct;
import com.task.entity.sop.YcWaiGouProcrd;
import com.task.entity.sop.YcWeekFePei;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * @ClassName: OrderManagerAction
 * @Description: 订单管理Action
 * @author Damon
 * @date 2012-11-20 上午08:19:25
 */

@SuppressWarnings( { "unchecked", "serial" })
public class OrderManagerAction extends ActionSupport implements
		ServletResponseAware {

	private IOrderManagerService oms;
	private ClientManagementServer cms;
	private IProductManagerService ipm;
	private OrderManager om;
	private ProductManager pm;
	private List<File> orderFile;
	private List<String> orderFileFileName;
	private List<OrderManager> list;
	private List<OrderManager> list1;
	private List<ClientManagement> clients;
	private List<TaHkHuikuan> taHkHuikuanList;// 发票
	private List<TaHkPartBackMoney> taHkPartBackMoneylist;// 回款明细
	private List<ProductManager> pmList;
	private List<Procard> procardList;
	private Procard procard;
	private String errorMessage;
	private int id;
	private String outOrderNumber;// 外部订单编号
	private String orderNum; // 内部订单编号
	private String deliveryStatus; // 交付状态
	private String documentaryPeople; // 跟单人
	private String billingPeople; // 开单人
	private Integer customeId; // 客户
	private String markId;
	private String status;
	private String flag;//
	private String tag;
	private String tags;// 是否可以反审核(yes//)
	private String sz;
	private String bei;// 是否有消备库(have/"")
	private String isallcx;// 是否有消备库(allcx/halfcx/"")
	private String beginTime;
	private String endTime;
	private String paymentDate;
	private boolean ifAgree;
	private HttpServletResponse response;
	private Float qxNum;

	private YcProduct ycProduct;
	private List<YcProduct> ycProductList;
	private YcWeekFePei ycWeek;
	private List<YcWeekFePei> ycWeekList;
	private Integer[] fenfeiNum;
	private YcWaiGouProcrd ycwgProcard;
	private List<YcWaiGouProcrd> ycwgProcardList;
	private Evaluators evaluators;
	private List<Evaluators> evalList;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;

	private File addorder;
	private Users users;
	private WareHouseAuthService wareHouseAuthService;
	private List<String> strList;
	private List<String> strList1;
	private Integer index;
	private Object[] obj;
	private String ids;

	public String fanshen() {
		errorMessage = oms.fansheng(id);
		url = "orderManager_queryDetail.action?id=" + id + "&tags=" + tags;
		return "error";
	}

	/****
	 * 展示订单的计划转换情况、生产情况、入库情况等明细
	 * 
	 * @return
	 */
	public String showOrderDetils() {
		// 查询订单
		om = oms.getOrderById(id);
		if (om != null) {
			// // 查询订单对应产品
			// Object[] obj = ipm.queryDetailByOrderById(om.getId(), null,
			// null);
			// list = (List) obj[0];
			// // 查询订单对应内部计划
			// oms.findInternalOrder(om.getId());
			return "orderManager_showDetails";
		} else {
			errorMessage = "不存在您要查询的订单信息!请核实或刷新后再次查看!";
			return ERROR;
		}
	}

	/****
	 * 展示订单生产产品明细
	 * 
	 * @return
	 */
	public void showProcardDetils() {
		// 查询订单
		om = oms.getOrderById(id);
		if (om != null) {
			// 查询订单对应产品
			list = oms.findProcardByOrder(om.getId());
			MKUtil.writeJSON(true, "", list);

		} else {
			MKUtil.writeJSON(false, "不存在您选择的订单", null);
		}
	}

	/****
	 * 展示订单的产品，发票，开票明细和回款明细
	 * 
	 * @return
	 */
	public String showOrderHKDetils() {
		// 查询订单
		om = oms.getOrderById(id);
		if (om != null) {
			// // 查询订单对应产品
			// Object[] obj = ipm.queryDetailByOrderById(om.getId(), null,
			// null);
			// list = (List) obj[0];
			// // 查询订单对应内部计划
			// oms.findInternalOrder(om.getId());
			taHkHuikuanList = oms.gettaHkHuikuanList(om.getOrderNum());
			return "orderManager_showOrderHKDetils";
		} else {
			errorMessage = "不存在您要查询的订单信息!请核实或刷新后再次查看!";
			return ERROR;
		}
	}

	/**
	 * @throws Exception
	 * @throws NumberFormatException
	 * @Title: queryOrderManager
	 * @Description: 查询订单管理
	 * @return String
	 * @throws
	 */
	public String initOrder() {
		Users users = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		String resultStr = "orderManager_check";
		Object[] object = oms.queryAllOrderManager(Integer.parseInt(cpage),
				pageSize, users);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			clients = cms.queryAllClient();
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
		return resultStr;
	}

	/**
	 * @Title: queryOrderManagerByCondition
	 * @Description: 根据条件查询
	 * @return String
	 * @throws
	 */
	public String queryOrderManagerByCondition() {
		String resultStr = null;
		Map map = new HashMap();
		Users user = Util.getLoginUser();
		strList = wareHouseAuthService.getDdShow(user.getCode());
		strList1 = wareHouseAuthService.getDdUpdate(user.getCode());
		// strList = wareHouseAuthService.getDdShow(user.getCode());
		// strList1 = wareHouseAuthService.getDdUpdate(user.getCode());
		// if (strList != null && strList.size() == 0) {
		// errorMessage = "抱歉,您没有查看订单的权限";
		// return "error";
		// }
		users = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if (status != null && !status.equals("")) {
			map.put("resultStr", status);
		}
		if (orderNum != null && !orderNum.equals("")) {
			map.put("orderNum", orderNum);
		}
		if (deliveryStatus != null && !deliveryStatus.equals("")) {
			map.put("deliveryStatus", deliveryStatus);
		}
		if (documentaryPeople != null && !documentaryPeople.equals("")) {
			map.put("documentaryPeople", documentaryPeople);
		}
		if (billingPeople != null && !billingPeople.equals("")) {
			map.put("billingPeople", billingPeople);
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
		if (paymentDate != null && !paymentDate.equals("")) {
			map.put("paymentDate", paymentDate);
		}
		if (users != null) {
			map.put("users", users);
		}
		if (markId != null && !markId.equals("")) {
			map.put("markId", markId);
		}
		if (om != null && om.getOrderType() != null
				&& om.getOrderType().length() > 0) {
			map.put("orderType", om.getOrderType());
		}
		if ("KH".equals(tag)) {
			ClientManagement cm = cms.findByUserID(users.getId());
			if (cm != null) {
				map.put("custome", cm.getId());
			} else {
				map.put("custome", 0);
			}

		}
		if (map.size() > 1) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if ((errorMessage == null || !errorMessage.equals("all"))) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
				if (!"KH".equals(tag)) {
					if (map != null) {
						map.remove("custome");
					}

				}

			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		// 更新订单完成率
		oms.updateCompletionrate();
		Object[] object = oms.queryOrderManagerByCondition(map, Integer
				.parseInt(cpage), pageSize, status, tag);

		// 显示当月的订单完成率
		String nowMonth = Util.getDateTime("yyyy-MM");
		if (paymentDate != null) {
			nowMonth = paymentDate;
		}
		Float finLv = oms.getCompletionrateByMonth(nowMonth);

		successMessage = nowMonth + "月份的完成率为:" + finLv + "%";
		if (object != null && object.length > 0) {
			// 查询所有客户，条件查询用
			list = (List) object[0];
			pmList = (List) object[2];
			clients = cms.queryAllClient();
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (tag == null) {
					tag = "";
				}
				if (status == null) {
					status = "";
				}
				if (flag != null && flag.length() > 0) {
					this
							.setUrl("orderManager_queryOrderManagerByCondition.action?flag="
									+ flag
									+ "&tag="
									+ tag
									+ "&status="
									+ status);
				} else {
					this
							.setUrl("orderManager_queryOrderManagerByCondition.action?tag="
									+ tag + "&status=" + status);
				}
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		if (map != null) {
			if (map.containsKey("resultStr")) {
				resultStr = (String) map.get("resultStr");
				if (resultStr.equals("check"))
					resultStr = "orderManager_check";
				else if (resultStr.equals("sz")) {
					resultStr = "orderManager_initOrder";
				} else if (resultStr.equals("yc")) {
					resultStr = "orderManager_initOrder";
				} else if (resultStr.equals("sh")) {
					resultStr = "orderManager_initOrder";
				} else {
					resultStr = "orderManager_initOrder_inner";
				}
			} else {
				resultStr = "orderManager_initOrder";
			}
		} else {
			resultStr = "orderManager_initOrder";// System/SOP/orderManager_index.jsp
		}
		return resultStr;
	}

	/**
	 * 订单明细是否存在备货订单
	 * 
	 * @param list
	 * @return
	 */
	public String isyou(List list) {
		String tag = "";
		for (Object product : list) {
			ProductManager manager = (ProductManager) product;
			if (manager != null && manager.getBhnumber() != null
					&& !"".equals(manager.getBhnumber())
					&& !"待关联".equals(manager.getBhnumber())) {
				tag = "have";// 备货订单号不为空
			}
		}
		return tag;
	}

	/**
	 * 订单明细是否全部冲销完。
	 * 
	 * @param list
	 * @return
	 */
	public String isAllCx(List list) {
		String tag = "";
		for (Object product : list) {
			ProductManager manager = (ProductManager) product;
			if (manager != null && manager.getBhnumber() != null
					&& !"".equals(manager.getBhnumber())
					&& !"待关联".equals(manager.getBhnumber())) {
				if (manager.getCxCount().floatValue() == manager.getNum()
						.floatValue()) {
					tag = "allcx";
				} else {
					tag = "halfcx";
				}
			}
		}
		return tag;
	}

	/**
	 * @Title: queryDetail
	 * @Description: 根据订单查询明细
	 * @return String
	 * @throws
	 */
	public String queryDetail() {
		if (id != 0) {
			Object[] object = ipm.queryDetailByOrderById(id, Integer
					.parseInt(cpage), pageSize);
			list1 = cms.queryAllClient();
			om = oms.getOrderById(id);
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					bei = isyou(list);
					isallcx = isAllCx(list);
					int count = (Integer) object[1];
					String reamks = (String) object[2];
					om.setRemark(reamks);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("orderManager_queryDetail.action?id=" + id);
					errorMessage = null;
				} else
					errorMessage = "该订单尚未添加产品,请您添加产品!";
			} else {
				errorMessage = "该订单尚未添加产品,请您添加产品!";
			}
			Users user = Util.getLoginUser();
			strList = wareHouseAuthService.getDdPrice(user.getCode());
			strList1 = wareHouseAuthService.getDdUpdate(user.getCode());
			if (status != null && !status.equals(""))
				return "orderManager_internal_detail";// orderManager_examine_product.jsp
			else {
				return "orderManager_detail";// productManager_index.jsp
			}
		}
		return null;
	}

	/**
	 * @Title: initupdate
	 * @Description: 初始化修改页面的客户信息
	 * @return String
	 * @throws
	 */
	public String initUpdate() {
		list = cms.queryAllClient();
		om = oms.getOrderById(id);
		pmList = om.getPmList();

		return "orderManager_update";
	}

	/**
	 * @Title: update
	 * @Description: 修改订单信息
	 * @return String
	 * @throws
	 */
	public String update() {
		if (om != null) {
			String msg = null;
			if (!"xieshang".equals(status)) {
				msg = oms.checkOrderNumber(om.getId(), om.getOrderNum(), om
						.getOutOrderNumber());
				if (!msg.equals("true")) {
					successMessage = msg + "修改失败";
					return "orderManager_update";
				}
			}
			String fileLocation = "upload/order";
			String copyLocation = "D:/WorkSpace/HHTask/WebRoot/upload/order";
			String[] fileNames = null;
			if (orderFile != null) {
				fileNames = FileUtil.copyFiles(orderFile, orderFileFileName,
						fileLocation, copyLocation);

				if (fileNames.length == 2) {
					int a = fileNames[0].lastIndexOf("\\");
					String a1 = fileNames[0].substring(a + 1, fileNames[0]
							.length());
					int b = fileNames[1].lastIndexOf("\\");
					String b1 = fileNames[1].substring(b + 1, fileNames[1]
							.length());
					// om.setOrderFil(fileNames[0]);
					// om.setContractDocuments(fileNames[1]);
					om.setOrderFil(a1);
					om.setContractDocuments(b1);
				} else {
					int a = fileNames[0].lastIndexOf("\\");
					String a1 = fileNames[0].substring(a + 1, fileNames[0]
							.length());
					om.setOrderFil(a1);
				}
			}
			ClientManagement cm = cms.getClientManagementById(id);
			Set<ProductManager> pmSet = om.getProducts();
			om.setCustome(cm);

			om.setPmList(pmList);
			msg = oms.update(om, status);
			if (msg.equals("true")) {
				errorMessage = "修改成功!";

			} else {
				errorMessage = "修改失败!";
			}
		}
		return "orderManager_update";
	}

	/**
	 * 重新申请
	 */
	public void updatesatuts() {
		if (om != null) {
			status = "dahui";
			String msg = oms.update(om, status);
			if ("true".equals(msg)) {
				ResponseUtil.write(response, "重新申请成功!",
						"orderManager_queryOrderManagerByCondition.action?flag="
								+ flag + "&cpage=" + cpage, null);
				return;
			}
		}
		ResponseUtil.write(response, "重新申请成功!",
				"orderManager_queryOrderManagerByCondition.action?flag=" + flag
						+ "&cpage=" + cpage, null);
	}

	/**
	 * @Title: initadd
	 * @Description: 初始化添加页面
	 * @return String
	 * @throws
	 */
	public String initAdd() {
		Users user = Util.getLoginUser();
		strList = wareHouseAuthService.getDdUpdate(user.getCode());
		strList1 = wareHouseAuthService.getDdPhone(user.getCode());
		// if(strList==null || strList.size() ==0){
		// return "orderManager_noAdd";
		// }
		clients = cms.queryAllClient();
		users = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		for (ClientManagement cm : clients) {
			if (cm.getClientcompanyname().equals(users.getMore())) {
				customeId = cm.getId();
			}
		}
		om = new OrderManager();
		if ("yc".equals(status)) {
			om.setOrderNum(oms.getorderNum("预测"));
		} else if ("sz".equals(status)) {
			om.setOrderNum(oms.getorderNum("试制"));
		} else if ("sh".equals(status)) {
			om.setOrderNum(oms.getorderNum("售后"));
		}
		billingPeople = users.getName();
		return "orderManager_initAdd";// orderManager_add.jsp
	}

	/**
	 * @Title: add
	 * @Description: 添加订单
	 * @return String
	 * @throws
	 */
	public String add() {
		flag = "dj";
		String msg = oms.checkOrderNumber(null, om.getOrderNum(), om
				.getOutOrderNumber());
		if (!msg.equals("true")) {
			successMessage = msg + "添加失败";
			clients = cms.queryAllClient();
			users = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			for (ClientManagement cm : clients) {
				if (cm.getClientcompanyname().equals(users.getMore())) {
					customeId = cm.getId();
				}
			}
			billingPeople = users.getName();
			return "orderManager_initAdd";
		}
		String fileLocation = "upload/order";
		String copyLocation = "D:/WorkSpace/HHTask/WebRoot/upload/order";
		// String copyLocation =
		// "D:/Workspaces/MyEclipse 8.6/test/HHTask/WebRoot/upload/order";
		String[] fileNames = null;
		if (orderFile != null && orderFile.size() > 0) {
			fileNames = FileUtil.copyFiles(orderFile, orderFileFileName,
					fileLocation, copyLocation);
		}

		if (fileNames != null && fileNames.length == 2) {
			int a = fileNames[0].lastIndexOf("\\");
			String a1 = fileNames[0].substring(a + 1, fileNames[0].length());
			int b = fileNames[1].lastIndexOf("\\");
			String b1 = fileNames[1].substring(b + 1, fileNames[1].length());
			// om.setOrderFil(fileNames[0]);
			// om.setContractDocuments(fileNames[1]);
			om.setOrderFil(a1);
			om.setContractDocuments(b1);
		} else if (fileNames != null) {
			int a = fileNames[0].lastIndexOf("\\");
			String a1 = fileNames[0].substring(a + 1, fileNames[0].length());
			om.setOrderFil(a1);
		}
		ClientManagement cl = cms.getClientManagementById(id);
		// cl.getOrders().add(om);
		om.setCustome(cl);
		om.setClientName(cl.getClientcompanyname());
		om.setClientDz(cl.getClientdz());
		om.setDeliveryStatus("否");
		om.setAddTime(Util.getDateTime());
		Iterator<ProductManager> iterator = pmList.iterator();
		while (iterator.hasNext()) {
			ProductManager pm = iterator.next();
			if (null == pm) {
				iterator.remove();
			}
		}
		errorMessage = oms.add(om, pmList);
		if (!errorMessage.equals("true")) {
			errorMessage = "添加订单失败:" + errorMessage;
		} else {
			errorMessage = "添加订单成功";
			url = "orderManager_queryDetail.action?id=" + om.getId()
					+ "&flag=dj&cpage=1&tag=&sz=";
		}
		tag = status;
		return ERROR;
	}

	/**
	 * @Title: del
	 * @Description: 删除订单
	 * @return String
	 * @throws
	 */
	public String del() {
		if (id != 0) {
			om = oms.getOrderById(id);
			oms.del(om);
			ResponseUtil.write(response, "删除成功!",
					"orderManager_queryOrderManagerByCondition.action?flag="
							+ flag + "&cpage=" + cpage + "&tag=" + tag
							+ "&status=" + status, null);
		} else {
			ResponseUtil.write(response, "删除失败!",
					"orderManager_queryOrderManagerByCondition.action?cpage="
							+ cpage, null);
		}
		return null;
	}

	/**
	 * @Title: initUpdateDetail
	 * @Description: 初始化修改明细
	 * @return String
	 * @throws
	 */
	public String initUpdateDetail() {
		if (id != 0) {
			pm = ipm.getProductManagerById(id);
		}
		if (status != null && !status.equals("")) {
			return "orderManager_conver_initUpdateDetail";
		}
		return "orderManager_initUpdateDetail";// productManager_update.jsp
	}

	/**
	 * @Title: updateDetail
	 * @Description: 修改订单明细
	 * @return String
	 * @throws
	 */
	public String updateDetail() {
		if (id != 0 && orderNum != null) {
			String msg = ipm.checkCanChangeNum(id, orderNum);
			if (!msg.equals("true")) {
				errorMessage = msg;
				return "error";
			}
			pm = ipm.getProductManagerById(id);
			if (orderNum != null && !orderNum.equals("")) {
				Float orderNuma = Float.valueOf(orderNum);
				if (pm.getUnit() != null) {
					double newPrice = ConvertNumber.multiply(orderNuma, pm
							.getUnit());// 总价
					Double sub = ConvertNumber.doubleSubtract(pm
							.getOrderManager().getTotalAmount(), pm
							.getUnitPrice());// 订单总价-产品原来总价
					Double price = ConvertNumber.doubleSum(sub, newPrice);// 现在订单总价
					pm.getOrderManager().setTotalAmount(price);
					pm.setUnitPrice(newPrice);
				}
				pm.setNum(orderNuma);
				pm.setPaymentDate(paymentDate);
				// pm.setAllocationsNum(orderNuma);
			}
			if (deliveryStatus != null && !deliveryStatus.equals("")) {
				pm.setRemark(deliveryStatus);
			}
			ipm.update(pm);
			if (status != null && !status.equals("")) {
				Map map = new HashMap();
				int a = Integer.parseInt(status);
				map.put("id", a);
				ResponseUtil.write(response, "修改成功!",
						"internalOrder_conversionOrderData.action", map);
			} else {
				errorMessage = "修改成功!";
			}
		} else {
			return "orderManager_initUpdateDetail";
		}
		return "orderManager_initUpdateDetail";
	}

	/**
	 * @Title: delDetail
	 * @Description: 删除产品
	 * @return String
	 * @throws
	 */
	public String delDetail() {
		if (id != 0) {
			pm = ipm.getProductManagerById(id);
			ipm.del(pm);
			id = pm.getOrderManager().getId();
			ResponseUtil.write(response, "删除成功!",
					"orderManager_queryDetail.action?id=" + id, null);
		} else
			ResponseUtil.write(response, "删除失败!",
					"orderManager_queryDetail.action?id=" + id, null);
		return null;
	}

	/***
	 * 订单纠错
	 * 
	 * @return
	 */
	public String updateOrder() {
		try {
			oms.updateOrder(om);
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "处理完成!";
		return ERROR;
	}

	/****
	 * 获得当前月和上个月的订单信息
	 * 
	 * @return
	 */
	public String findOrderFormMonth() {
		list = oms.findOrderFormMonth();
		return "orderManager_monthView";
	}

	/****
	 * 获得当前月和上个月的订单信息的id集合
	 * 
	 * @return
	 */
	public String findOrderIdsFormMonth() {
		list = oms.findOrderIdsFormMonth();
		return "orderManager_id_monthView";
	}

	public void getOutNumerByNumber() {
		outOrderNumber = oms.getOutNumerByNumber(orderNum);
		MKUtil.writeJSON(outOrderNumber);
	}

	/***
	 * 订单成本分析中转页面
	 * 
	 * @return
	 */
	public String toTemplate() {
		om = oms.getOrderById(id);
		return "order_template";
	}

	/***
	 * 订单成本分析
	 * 
	 * @return
	 */
	public void toOrderChengben() {
		try {
			oms.orderChengben(id);
			MKUtil.writeJSON(true);
		} catch (Exception e) {
			MKUtil.writeJSON(false);
			e.printStackTrace();
		}
	}

	/***
	 * 订单成本分析显示
	 * 
	 * @return
	 */
	public String orderChengben() {
		om = oms.getOrderById(id);
		obj = oms.getOrderByIdForCb(id);
		return "order_chengben";
	}

	/***
	 * 订单成本分析
	 * 
	 * @return
	 */
	public String orderChengbenFenxi() {
		list = oms.findProcardByOrderId(id, flag);
		return "order_chengbenFenxi";
	}

	/**
	 * 总成件下零件的成本分析
	 * 
	 * @return
	 */
	public void orderPartsChengBen() {
		try {
			list = oms.findProcardByRootId(id);
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 根据id查询订单信息
	 * 
	 * @return
	 */
	public String findomsbyid() {
		om = oms.getOrderById(om.getId());
		if (om != null) {
			return "findomsbyid";
		}
		return ERROR;
	}

	public String removeProduct() {
		String msg = oms.removeProduct(pm.getId());
		if (msg.equals("true")) {
			errorMessage = "取消成功";
		} else {
			errorMessage = msg;
		}
		url = "orderManager_queryDetail.action?id=" + id + "&status=" + status
				+ "&flag=" + flag;
		return "error";
	}

	public void shuaxin() {
		errorMessage = oms.shuaxin(pm.getId(), id);
		if ("true".equals(errorMessage)) {
			errorMessage = "刷新单价成功!";
		}
		try {
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void findDjfProduct() {
		List list = oms.findDjfProduct();
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 在途订单导入
	public String pladdorder() {
		errorMessage = oms.pladdorder(addorder);
		if ("true".equals(errorMessage)) {
			errorMessage = "导入成功!";
		}
		return "error";
	}

	// 更改订单类型时更改显示的内部订单号（只是显示让别人看到的 放心点）
	public void changshowNum() {
		try {
			String str = oms.getorderNum(status);
			if (str != null && str.length() > 0) {
				MKUtil.writeJSON(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 对应件号未冲销完的订单数量
	 */
	public void getbfCount() {
		Float count = oms.getbfCount(markId);
		MKUtil.writeJSON(count);
	}

	/**
	 * 跳往关联备货订单
	 */
	public String toRelateBh() {
		String ywMarkId = markId;
		if (pm != null && pm.getYwMarkId() != null) {
			ywMarkId = pm.getYwMarkId();
		}
		pmList = oms.getBhPmList(markId, ywMarkId);
		return "order_relateBh";
	}

	public void relateBh() {
		try {
			String msg = oms.relateBh(id, ids);
			if (msg.equals("true")) {
				MKUtil.writeJSON(true, null, null);
			} else {
				MKUtil.writeJSON(false, msg, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			MKUtil.writeJSON(false, e.getMessage(), null);
		}
	}

	/**
	 * 
	 * 查询所有预测订单产品
	 */
	public String findAllYcProduct() {
		if (ycProduct != null) {
			ActionContext.getContext().getSession().put("ycProduct", ycProduct);
		} else {
			ycProduct = (YcProduct) ActionContext.getContext().getSession()
					.get("ycProduct");
		}

		Object[] obj = oms.findAllYcProduct(ycProduct, Integer.parseInt(cpage),
				pageSize, status);
		ycProductList = (List<YcProduct>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("orderManager_findAllYcProduct.action?status=" + status);
		return "ycproduct_showList";
	}

	/**
	 * 
	 * 添加预测产品每周预分配量
	 */
	public String addycWeek() {
		errorMessage = oms.addycWeek(id, ycWeekList);
		return "ycweek_add";
	}

	/**
	 * 根据预测产品Id查询预分配周;
	 * 
	 */
	public String findycWeek() {
		Object[] obj = oms.findycWeek(id);
		ycProduct = (YcProduct) obj[0];
		ycWeekList = (List<YcWeekFePei>) obj[1];
		return "ycweek_add";
	}

	/**
	 * 添加外购预分配记录
	 * 
	 * @return
	 */
	public void addYcWaiGouProcrd() {
		try {
			errorMessage = oms.addYcWaiGouProcrd(id);
			MKUtil.writeJSON(true, errorMessage, null, null);
		} catch (RuntimeException e) {
			MKUtil.writeJSON(false, e.getMessage(), null, null);
			e.printStackTrace();
		}

	}

	// 根据预测产品Id查询预采购外购件明细
	public String findycwgProcardList() {
		if (ycwgProcard != null) {
			ActionContext.getContext().getSession().put("ycwgProcard",
					ycwgProcard);
		} else {
			ycwgProcard = (YcWaiGouProcrd) ActionContext.getContext()
					.getSession().get("ycwgProcard");
		}

		Object[] obj = oms.findycwgProcardList(ycwgProcard, Integer
				.parseInt(cpage), pageSize, status);
		ycwgProcardList = (List<YcWaiGouProcrd>) obj[0];
		int count = (Integer) obj[1];
		;
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("orderManager_findycwgProcardList.action?status=" + status);
		return "ycwgprocard_showList";
	}

	public void issqcg() {
		try {
			errorMessage = oms.issqcg(id);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/****
	 * 根据订单产品交付日期，生成待排产列表
	 */
	public String getBetweenDate() {
		pm = oms.findProductManager(id);
		if (pm != null) {
			List<Date> list = Util.getBetweenDates(new Date(), Util
					.StringToDate(pm.getPaymentDate() + " 23:00:00",
							"yyyy-MM-dd HH:ss:mm"));
			strList = new ArrayList<String>();
			for (Date date : list) {
				strList.add(Util.DateToString(date, "yyyy-MM-dd"));
			}
			return "product_paicheng_add";
		}
		errorMessage = "未查到你要排程的产品信息!请检查后重试!";
		return ERROR;
	}

	/****
	 * 提交订单进入审核
	 */
	public String updateOrderForsubmit() {
		errorMessage = oms.updateOrderForsubmit(id);
		if ("提交成功!".equals(errorMessage) || "订单已申请审批，请刷新！".equals(errorMessage))
			status = "shenpi";
		url = "orderManager_queryDetail.action?id=" + id + "&status=" + status
				+ "&flag=" + flag + "&tag=" + tag + "&sz=" + sz + "&tags="
				+ tags;
		return ERROR;
	}

	/**
	 * 导出预测产品
	 * 
	 * @return
	 */
	public void exportExcelYc() {
		oms.exportExcelYc(ycProduct);
	}

	/**
	 * 导出预测产品申请采购的外购明细
	 */
	public void exportExcelYcWg() {
		oms.exportExcelYcWg(ycwgProcard);
	}

	/**
	 * 修改产品交期
	 * 
	 * @return
	 */
	public void updatejq() {
		try {
			ProductManager product = oms.updatejq(id, endTime);
			MKUtil.writeJSON(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找左右的客户
	 */
	public void getCustomer() {
		List<ClientManagement> list = cms.queryAllClient();
		List<String> cmList = new ArrayList<String>();
		for (ClientManagement clientManagement : list) {
			cmList.add(clientManagement.getClientcompanyname());
		}
		MKUtil.writeJSON(cmList);
	}

	/**
	 * 导出订单信息
	 * 
	 * @return
	 */
	public void export() {
		Map map = new HashMap();
		users = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		if (status != null && !status.equals("")) {
			map.put("resultStr", status);
		}
		if (orderNum != null && !orderNum.equals("")) {
			map.put("orderNum", orderNum);
		}
		if (deliveryStatus != null && !deliveryStatus.equals("")) {
			map.put("deliveryStatus", deliveryStatus);
		}
		if (documentaryPeople != null && !documentaryPeople.equals("")) {
			map.put("documentaryPeople", documentaryPeople);
		}
		if (billingPeople != null && !billingPeople.equals("")) {
			map.put("billingPeople", billingPeople);
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
		if (paymentDate != null && !paymentDate.equals("")) {
			map.put("paymentDate", paymentDate);
		}
		if (users != null) {
			map.put("users", users);
		}
		if (markId != null && !markId.equals("")) {
			map.put("markId", markId);
		}
		if (om != null && om.getOrderType() != null
				&& om.getOrderType().length() > 0) {
			map.put("orderType", om.getOrderType());
		}
		oms.exprot(map, flag, status);
	}

	public String JiuzhengMarkId() {
		errorMessage = oms.JiuzhengMarkId(id);
		return "error";
	}

	public String removeSqProduct() {
		errorMessage = oms.removeSqProduct(id, qxNum);
		if ("true".equals(errorMessage)) {
			errorMessage = "订单取消申请成功，请等待审批!~";
		}
		url = "orderManager_queryDetail.action?id=" + om.getId() + "&status="
				+ status + "&flag=" + flag;
		return "error";
	}

	// 查询所有暂停的生产单
	public String findAllZTProcard() {
		if (procard != null) {
			ActionContext.getContext().getSession().put("procard", procard);
		} else {
			procard = (Procard) ActionContext.getContext().getSession().get(
					"procard");
		}
		Object[] obj = oms.findAllZTProcard(procard, Integer.parseInt(cpage),
				pageSize, tag);
		procardList = (List<Procard>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		url = "orderManager_findAllZTProcard.action?tag=" + tag;
		return "procard_ZTList";
	}

	//
	public String addEvaluators() {
		errorMessage = oms.addEvaluators(evaluators);
		return "findAllEvaluators";
	}

	//
	public String findAllEvaluators() {
		if (evaluators != null) {
			ActionContext.getContext().getSession().put("evaluators",
					evaluators);
		} else {
			evaluators = (Evaluators) ActionContext.getContext().getSession()
					.get("evaluators");
		}
		Object[] obj = oms.findAllEvaluators(evaluators, pageSize, Integer
				.parseInt(cpage), tag);
		evalList = (List<Evaluators>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		url = "orderManager_findAllEvaluators.action?tag=" + tag;
		return "evalList_show";
	}

	public String delEvaluators() {
		errorMessage = oms.delEvaluators(evaluators);
		return "findAllEvaluators";
	}

	public String findEvaluatorsById() {
		evaluators = oms.findEvaluatorsById(id);
		return "evaluators_show";
	}

	public String updateEvaluators() {
		errorMessage = oms.updateEvaluators(evaluators);
		if ("true".equals(errorMessage)) {
			errorMessage = "修改成功";
		}
		return "findAllEvaluators";
	}

	// 前往自制件评审

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<OrderManager> getList() {
		return list;
	}

	public void setList(List<OrderManager> list) {
		this.list = list;
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

	public List<File> getOrderFile() {
		return orderFile;
	}

	public void setOrderFile(List<File> orderFile) {
		this.orderFile = orderFile;
	}

	public List<String> getOrderFileFileName() {
		return orderFileFileName;
	}

	public void setOrderFileFileName(List<String> orderFileFileName) {
		this.orderFileFileName = orderFileFileName;
	}

	public OrderManager getOm() {
		return om;
	}

	public void setOm(OrderManager om) {
		this.om = om;
	}

	public IOrderManagerService getOms() {
		return oms;
	}

	public void setOms(IOrderManagerService oms) {
		this.oms = oms;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
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

	public String getDocumentaryPeople() {
		return documentaryPeople;
	}

	public void setDocumentaryPeople(String documentaryPeople) {
		this.documentaryPeople = documentaryPeople;
	}

	public String getBillingPeople() {
		return billingPeople;
	}

	public void setBillingPeople(String billingPeople) {
		this.billingPeople = billingPeople;
	}

	public Integer getCustomeId() {
		return customeId;
	}

	public void setCustomeId(Integer customeId) {
		this.customeId = customeId;
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

	public ClientManagementServer getCms() {
		return cms;
	}

	public void setCms(ClientManagementServer cms) {
		this.cms = cms;
	}

	public List<ClientManagement> getClients() {
		return clients;
	}

	public void setClients(List<ClientManagement> clients) {
		this.clients = clients;
	}

	public IProductManagerService getIpm() {
		return ipm;
	}

	public void setIpm(IProductManagerService ipm) {
		this.ipm = ipm;
	}

	public ProductManager getPm() {
		return pm;
	}

	public void setPm(ProductManager pm) {
		this.pm = pm;
	}

	public boolean isIfAgree() {
		return ifAgree;
	}

	public void setIfAgree(boolean ifAgree) {
		this.ifAgree = ifAgree;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public List<OrderManager> getList1() {
		return list1;
	}

	public void setList1(List<OrderManager> list1) {
		this.list1 = list1;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getOutOrderNumber() {
		return outOrderNumber;
	}

	public void setOutOrderNumber(String outOrderNumber) {
		this.outOrderNumber = outOrderNumber;
	}

	public List<TaHkHuikuan> getTaHkHuikuanList() {
		return taHkHuikuanList;
	}

	public void setTaHkHuikuanList(List<TaHkHuikuan> taHkHuikuanList) {
		this.taHkHuikuanList = taHkHuikuanList;
	}

	public List<TaHkPartBackMoney> getTaHkPartBackMoneylist() {
		return taHkPartBackMoneylist;
	}

	public void setTaHkPartBackMoneylist(
			List<TaHkPartBackMoney> taHkPartBackMoneylist) {
		this.taHkPartBackMoneylist = taHkPartBackMoneylist;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public List<ProductManager> getPmList() {
		return pmList;
	}

	public void setPmList(List<ProductManager> pmList) {
		this.pmList = pmList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public File getAddorder() {
		return addorder;
	}

	public void setAddorder(File addorder) {
		this.addorder = addorder;
	}

	public WareHouseAuthService getWareHouseAuthService() {
		return wareHouseAuthService;
	}

	public void setWareHouseAuthService(
			WareHouseAuthService wareHouseAuthService) {
		this.wareHouseAuthService = wareHouseAuthService;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<String> getStrList1() {
		return strList1;
	}

	public void setStrList1(List<String> strList1) {
		this.strList1 = strList1;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Object[] getObj() {
		return obj;
	}

	public void setObj(Object[] obj) {
		this.obj = obj;
	}

	public YcProduct getYcProduct() {
		return ycProduct;
	}

	public void setYcProduct(YcProduct ycProduct) {
		this.ycProduct = ycProduct;
	}

	public List<YcProduct> getYcProductList() {
		return ycProductList;
	}

	public void setYcProductList(List<YcProduct> ycProductList) {
		this.ycProductList = ycProductList;
	}

	public YcWeekFePei getYcWeek() {
		return ycWeek;
	}

	public void setYcWeek(YcWeekFePei ycWeek) {
		this.ycWeek = ycWeek;
	}

	public List<YcWeekFePei> getYcWeekList() {
		return ycWeekList;
	}

	public void setYcWeekList(List<YcWeekFePei> ycWeekList) {
		this.ycWeekList = ycWeekList;
	}

	public Integer[] getFenfeiNum() {
		return fenfeiNum;
	}

	public void setFenfeiNum(Integer[] fenfeiNum) {
		this.fenfeiNum = fenfeiNum;
	}

	public YcWaiGouProcrd getYcwgProcard() {
		return ycwgProcard;
	}

	public void setYcwgProcard(YcWaiGouProcrd ycwgProcard) {
		this.ycwgProcard = ycwgProcard;
	}

	public List<YcWaiGouProcrd> getYcwgProcardList() {
		return ycwgProcardList;
	}

	public void setYcwgProcardList(List<YcWaiGouProcrd> ycwgProcardList) {
		this.ycwgProcardList = ycwgProcardList;
	}

	public String getIds() {
		return ids;
	}

	/**
	 * @return the bei
	 */
	public String getBei() {
		return bei;
	}

	/**
	 * @param bei
	 *            the bei to set
	 */
	public void setBei(String bei) {
		this.bei = bei;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSz() {
		return sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

	public String getIsallcx() {
		return isallcx;
	}

	public void setIsallcx(String isallcx) {
		this.isallcx = isallcx;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	public Float getQxNum() {
		return qxNum;
	}

	public void setQxNum(Float qxNum) {
		this.qxNum = qxNum;
	}

	public Evaluators getEvaluators() {
		return evaluators;
	}

	public void setEvaluators(Evaluators evaluators) {
		this.evaluators = evaluators;
	}

	public List<Evaluators> getEvalList() {
		return evalList;
	}

	public void setEvalList(List<Evaluators> evalList) {
		this.evalList = evalList;
	}

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	// public void setUsers(Users users) {
	// this.users = users;
	// }

}
