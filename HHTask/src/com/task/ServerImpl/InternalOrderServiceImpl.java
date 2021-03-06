package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IInternalOrderService;
import com.task.Server.IOrderManagerService;
import com.task.Server.IPieceNumService;
import com.task.Server.bp.DetailService;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.InternalZsAboutBh;
import com.task.entity.OrderManager;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.Product_Internal;
import com.task.entity.Users;
import com.task.entity.bp.Detail;
import com.task.entity.bp.Templet;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcardTemplateBanBen;
import com.task.entity.sop.ProcardTemplateBanBenApply;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class InternalOrderServiceImpl implements IInternalOrderService {

	private TotalDao totalDao;
	private IOrderManagerService oms;
	private IPieceNumService pns;
	private DetailService ds;
	private static final String AGREE = "同意";
	private static final String goBack = "打回";
	private static final String EXAMINE = "待审核";
	private static final String COMPLETE = "完成";
	private static final String PART = "采购部分";
	private static final String NOTPURCHASE = "未采购";
	private static final int ZERO = 0;
	private static final Float ZEROl = 0f;
	private static final int ONE = 1;
	private static final String YES = "是";

	@Override
	public void add(InternalOrder io) {
		totalDao.save(io);
	}

	@Override
	public void del(InternalOrder io) {
		totalDao.delete(io);
	}

	@Override
	public void delInternalObjectById(int id) {
		InternalOrder io = (InternalOrder) totalDao.getObjectById(
				InternalOrder.class, id);
		totalDao.delete(io);
	}

	@Override
	public InternalOrder getInternalOrderById(int id) {
		return (InternalOrder) totalDao.getObjectById(InternalOrder.class, id);
	}

	@Override
	public boolean update(InternalOrder io) {
		Boolean bol = totalDao.update(io);
		return bol;
	}

	@Override
	public void conversion(int id) {
		OrderManager orderManager = (OrderManager) totalDao.getObjectById(
				OrderManager.class, id);
		orderManager.setConversionStatus("YES");
		totalDao.update(orderManager);
	}

	public Object[] queryAll(int pageNo, int pageSize, String pageStatus) {
		if (pageStatus != null && pageStatus.length() > 0) {
			boolean bol = false;
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			String hql = " from InternalOrder i where 1=1 and i.status in ('待审核','打回') ";
			String hqlA = "from InternalOrder i where 1=1 and i.status = '同意'";
			String userName = user.getName();
			if (pageStatus.equals("unpass")) {
				hql = " from InternalOrder i where 1=1 and i.status not in ('同意','打回','取消') ";
			} else if (pageStatus.equals("jinliAudit")) {
				hql = " from InternalOrder i where 1=1 and i.status ='经理审核'";
				bol = true;
			} else if (pageStatus.equals("all") || pageStatus.equals("sc")) {
				hql = " from InternalOrder i where 1=1 and i.status not in ('同意') ";
			} else if (pageStatus.equals("waiAudit")) {
				hql += " and i.documentaryPeople = '" + userName + "'";
				hqlA += " and i.documentaryPeople = '" + userName + "'";
				bol = true;
			}
			hql += " order by i.id desc";
			hqlA += " order by i.id desc";
			List waitLis = totalDao.query(hql, null);
			List agreeLis = totalDao.findAllByPage(hqlA, pageNo, pageSize);
			int count = totalDao.getCount(hqlA);
			return new Object[] { waitLis, count, agreeLis, bol };
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object[] queryInternalOrderByCondition(Map map, int pageNo,
			int pageSize, String pageStatus) {
		List waitLis = new ArrayList();
		List agreeLis = new ArrayList();
		boolean bol = false;
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		String hql = "from InternalOrder i where 1=1 and i.status in ('待审核','打回')";
		String hqlA = "from InternalOrder i where 1=1 and i.status = '同意'";
		if (map != null) {
			if (map.get("orderNum") != null) {
				hqlA += " and i.num like '%" + map.get("orderNum") + "%'";
			}
			if (map.get("custome") != null) {
				hqlA += " and i.custome.id = '" + map.get("custome") + "'";
			}
			if (map.get("beginTime") != null && map.get("endTime") != null) {
				hqlA += " and (i.newDate between '" + map.get("beginTime")
						+ "' and '" + map.get("endTime") + "')";
			}
		}
		String userName = user.getName();
		if (pageStatus.equals("jinliAudit")) {
			hql = " from InternalOrder i where 1=1 and i.status ='经理审核'";
			bol = true;
		} else if (pageStatus.equals("all")) {
		} else if (pageStatus.equals("waiAudit")) {
			hql += " and i.documentaryPeople = '" + userName + "'";
			hqlA += " and i.documentaryPeople = '" + userName + "'";
			bol = true;
		}
		hql += " order by i.id desc";
		hqlA += " order by i.id desc";
		waitLis = totalDao.query(hql);
		agreeLis = totalDao.findAllByPage(hqlA, pageNo, pageSize);
		int count = totalDao.getCount(hqlA);
		return new Object[] { waitLis, count, agreeLis, bol };
	}

	public Object[] integrationOrderDetail(int[] selected) {
		if (selected != null && selected.length > 0) {
			String title = "生产计划(";
			Map<String, ProductManager> map = new HashMap<String, ProductManager>();
			for (int i = 0; i < selected.length; i++) {
				int orderId = selected[i];
				OrderManager om = oms.getOrderById(orderId);
				if (i == 0)
					title = title + om.getCustome().getCompanyAbbreviation()
							+ ")";
				Iterator<ProductManager> in = om.getProducts().iterator();
				while (in.hasNext()) {
					ProductManager pm = in.next();
					if (map.containsKey(pm.getPieceNumber())) {
						ProductManager old = map.get(pm.getPieceNumber());
						old.setNum(old.getNum() + pm.getNum());
						map.put(old.getPieceNumber(), old);
					} else {
						ProductManager newPM = new ProductManager();
						BeanUtils.copyProperties(pm, newPM, new String[] {
								"id", "unitPrice", "unit", "orderManager" });
						map.put(pm.getPieceNumber(), newPM);
					}
				}
			}
			List list = new ArrayList(map.values());
			return new Object[] { list, title };
		}
		return null;
	}

	public void batchConversionOrder(String[] pieceNum, Float[] num,
			String[] remark, String orderIdStr, String monthStr, String tag) {
		String[] orderStr = orderIdStr.split(",");
		InternalOrder newIO = new InternalOrder();
		newIO.setStatus(AGREE);
		String newDate = ConvertNumber.conversionDateStr();
		String newNum = "";
		newIO.setGenertorDate(monthStr);
		newIO.setNewDate(newDate);
		newIO.setWhetherPurchase(NOTPURCHASE);
		newIO.setIsVali(ZERO);
		newIO.setZhStatus("未转完");
		newIO.setFlow("");
		String orderIds = "";// 订单ids
		String orderNums = "";// 订单编号s

		for (int i = 0; i < orderStr.length; i++) {
			OrderManager om = oms.getOrderById(Integer.parseInt(orderStr[i]));
			if (i == 0) {
				newIO.setDocumentaryPeopleId(om.getDocumentaryPeopleId());
				newIO.setDocumentaryPeople(om.getDocumentaryPeople());
				newIO.setFlow(newIO.getFlow() + om.getOrderNum());// 订单编号
				newNum = om.getCustome().getNumber() + "-"
						+ Util.getDateTime("yyyyMMdd");// 內部计划编号（客户编号-yyyyMMdd001）
				newIO.setCustome(om.getCustome());
			}
			newIO.setOrderType(om.getOrderType());
			om.getInnerOrders().add(newIO);
			newIO.getOuterOrders().add(om);
			if (i > 0) {
				orderIds = orderIds + ";" + om.getId();
				orderNums = orderNums + ";" + om.getOrderNum();
			} else {
				orderIds += om.getId();
				orderNums += om.getOrderNum();
			}
		}
		newIO.setOrderIds(orderIds);
		newIO.setOrderNums(orderNums);
		// 根据newNum查询最大编号
		String hql = "select max(num) from InternalOrder where num like '%"
				+ newNum + "%'";
		String maxNum = (String) totalDao.getObjectByCondition(hql);
		if (maxNum != null && maxNum.length() > 0) {
			int maxNewNum = Integer.parseInt(maxNum.substring(
					maxNum.length() - 3, maxNum.length())) + 1000 + 1;
			newNum += (maxNewNum + "").substring(1, 4);
		} else {
			newNum += "001";
		}
		newIO.setNum(newNum);
		String productStyle = "";
		for (int i = 0; i < pieceNum.length; i++) {
			Float remainder = num[i];
			if (remainder == 0) {
				continue;
			}
			Product_Internal pi = new Product_Internal();
			String hql_ywMarkId = "from ProcardTemplate where (markId=? or ywMarkId=?) and (banbenStatus is null or banbenStatus !='历史')"
					+ " and (dataStatus is null or dataStatus!='删除') and productStyle =?";
			productStyle = "批产";
			if ("sz".equals(tag)) {
				productStyle = "试制";
			}

			ProcardTemplate pt = (ProcardTemplate) totalDao
					.getObjectByCondition(hql_ywMarkId, pieceNum[i],
							pieceNum[i], productStyle);
			if (pt == null) {
				throw new RuntimeException(pieceNum[i] + "的BOM模版不存在!");
			} else {
				// String hql_bzMarkId =
				// "from ProcardTemplate where productStyle =? and rootId=?";
				// Float noPzCount = (Float) totalDao
				// .getObjectByCondition(
				// "select count(*) from ProcardTemplate where (bzStatus is null or bzStatus!='已批准') and (banbenStatus is null or banbenStatus ='默认') and rootId=?",
				// pt.getRootId());
				// if (noPzCount != null && noPzCount > 0) {
				// throw new RuntimeException(pieceNum[i] + "的BOM模版有"
				// + noPzCount + "个物料未审批通过,请等待!");
				// }
			}
			pieceNum[i] = pt.getMarkId();
			pi.setMarkId(pt.getMarkId());
			pi.setStatus("申请中");
			Price price = pns.getPriceByPieceNum(pt.getMarkId());
			if (price == null) {
				price = pns.getPriceByPieceNum(pt.getYwMarkId());
			}
			InternalOrderDetail iod = new InternalOrderDetail(pt.getProName(),
					pt.getMarkId(), pt.getYwMarkId(), num[i], 0F, remark[i],
					ZEROl, "");
			newIO.getInterOrderDetails().add(iod);
			iod.setInternalOrder(newIO);
			totalDao.save(iod);
			pi.setIoDetailId(iod.getId());
			totalDao.save(pi);
			// for (int j = 0; j < orderStr.length; j++) {
			// OrderManager om = oms.getOrderById(Integer
			// .parseInt(orderStr[j]));
			// for (Iterator<ProductManager> it = om.getProducts().iterator();
			// it
			// .hasNext();) {
			// // if (remainder == 0) {
			// // continue;
			// // }
			OrderManager om = oms.getOrderById(Integer.parseInt(orderStr[0]));
			Float zhCount = remainder;
			ProductManager pm = (ProductManager) totalDao
					.getObjectByCondition(
							"from ProductManager where orderManager.id=? and pieceNumber=?",
							Integer.parseInt(orderStr[0]), pieceNum[i]);
			pm.setPieceNumber(pt.getMarkId());// 件号;
			pm.setYwMarkId(pt.getYwMarkId());
			if (pm.getPieceNumber().equals(pieceNum[i])) {
				List<ProductManager> relatePmList = null;
				if (om.getOrderType().equals("备货")
						|| om.getOrderType().equals("预测")) {
					relatePmList = totalDao
							.query(
									"from ProductManager where orderManager.orderType='正式' and orderManager.ep_statuts='同意' and (cxHasTurn is null or cxCount>cxHasTurn) and id in(select zsProductId from ProductZsAboutBh where bhProductId=?)",
									pm.getId());
				}
				if (pm.getHasTurn() == null) {
					pm.setHasTurn(0f);
				}
				if (zhCount.floatValue() <= (pm.getNum().floatValue() - pm
						.getHasTurn().floatValue())) {
					pm.setHasTurn(pm.getHasTurn() + zhCount);
				} else {
					throw new RuntimeException("产品" + pm.getPieceNumber()
							+ "超过剩余可转换量,转换失败!");
				}
				if (relatePmList != null && relatePmList.size() > 0) {// 反馈到冲销订单
					for (ProductManager relatePm : relatePmList) {
						InternalZsAboutBh izab = new InternalZsAboutBh();
						Integer pzsAboutbhId = (Integer) totalDao
								.getObjectByCondition(
										"select id from ProductZsAboutBh where zsProductId=? and bhProductId=?",
										relatePm.getId(), pm.getId());
						izab.setPzsAboutbhId(pzsAboutbhId);
						izab.setIdetailId(iod.getId());
						izab.setMarkId(relatePm.getPieceNumber());
						izab.setYwMakrId(relatePm.getYwMarkId());
						if (relatePm.getCxHasTurn() == null) {
							relatePm.setCxHasTurn(0f);
						}
						if (zhCount.floatValue() > (relatePm.getCxCount()
								.floatValue() - relatePm.getCxHasTurn()
								.floatValue())) {
							izab.setCount(relatePm.getCxCount()
									- relatePm.getCxHasTurn());
							zhCount = zhCount
									- (relatePm.getCxCount() - relatePm
											.getCxHasTurn());
							relatePm.setCxHasTurn(relatePm.getCxCount());
						} else {
							izab.setCount(zhCount);
							relatePm.setCxHasTurn(relatePm.getCxHasTurn()
									+ zhCount);
							zhCount = 0f;
						}
						if (iod.getCxCount() == null) {
							iod.setCxCount(izab.getCount());
						} else {
							iod.setCxCount(iod.getCxCount() + izab.getCount());
						}
						totalDao.update(iod);
						totalDao.update(relatePm);
						totalDao.save(izab);
						if (zhCount == 0) {
							break;
						}
					}
				}
				iod.setPaymentDate(iod.getPaymentDate() + pm.getPaymentDate());// 交付日期

				totalDao.update(iod);
				totalDao.update(pm);
				pi.setHasruku(0f);
				pi.setAllcount(remainder);
				pi.setProductId(pm.getId());
			}

		}

		newIO.setProducttype(productStyle);
		totalDao.save(newIO);
		// 增加审批流程
		String uIds = null;
		if (newIO.getDocumentaryPeopleId() != null) {
			uIds = newIO.getDocumentaryPeopleId() + "";
		} else {
			Integer uid = (Integer) totalDao.getObjectByCondition(
					"select id from Users where name=?", newIO
							.getDocumentaryPeople());
			uIds = uid + "";
		}
		String processName = "内部计划审批";
		if ("sz".equals(tag)) {
			processName = "试制内部计划审批";
		}
		Integer epId;
		try {
			epId = CircuitRunServerImpl.createProcessbf(processName,
					InternalOrder.class, newIO.getId(), "status", "id",
					"internalOrder_queryInternalOrderDetail.action?pageStatus=unpass&id="
							+ newIO.getId(), processName + "，请您审批", true, uIds);
			if (epId != null) {
				newIO.setEpId(epId);
				totalDao.update(newIO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("审批流程有误!");
		}

	}

	/****
	 * 要货计划直接转换为生产计划
	 * 
	 * @param orderId
	 * @param iodList
	 * @param tag
	 */
	@Override
	public void yaohuoPlanForIod(Integer orderId,
			List<InternalOrderDetail> iodList, String tag) {

		String hql_io = "from InternalOrder where orderIds=?";
		InternalOrder newIO = (InternalOrder) totalDao.getObjectByCondition(
				hql_io, orderId + "");
		if (newIO == null) {
			newIO = new InternalOrder();
		}

		String newDate = ConvertNumber.conversionDateStr();
		String newNum = "";
		newIO.setGenertorDate(Util.getDateTime("yyyy-MM"));
		newIO.setNewDate(newDate);
		newIO.setWhetherPurchase(NOTPURCHASE);
		newIO.setIsVali(ZERO);
		newIO.setZhStatus("未转完");
		newIO.setFlow("");
		String orderIds = "";// 订单ids
		String orderNums = "";// 订单编号s

		OrderManager pageOm = oms.getOrderById(orderId);
		if (pageOm != null) {
			newIO.setStatus(pageOm.getEp_statuts());
			newIO.setDocumentaryPeopleId(pageOm.getDocumentaryPeopleId());
			newIO.setDocumentaryPeople(pageOm.getDocumentaryPeople());
			newIO.setFlow(newIO.getFlow() + pageOm.getOrderNum());// 订单编号
			newNum = pageOm.getCustome().getNumber() + "-"
					+ Util.getDateTime("yyyyMMdd");// 內部计划编号（客户编号-yyyyMMdd001）
			newIO.setCustome(pageOm.getCustome());
			newIO.setOrderType(pageOm.getOrderType());
			pageOm.getInnerOrders().add(newIO);
			newIO.getOuterOrders().add(pageOm);
			orderIds += pageOm.getId();
			orderNums += pageOm.getOrderNum();
		}
		newIO.setOrderIds(orderIds);
		newIO.setOrderNums(orderNums);
		// 根据newNum查询最大编号
		String hql = "select max(num) from InternalOrder where num like '"
				+ newNum + "%'";
		String maxNum = (String) totalDao.getObjectByCondition(hql);
		if (maxNum != null && maxNum.length() > 0) {
			int maxNewNum = Integer.parseInt(maxNum.substring(
					maxNum.length() - 3, maxNum.length())) + 1000 + 1;
			newNum += (maxNewNum + "").substring(1, 4);
		} else {
			newNum += "001";
		}
		newIO.setNum(newNum);
		String productStyle = pageOm.getProducttype();

		int sumnum = 0;
		Integer pmId = 0;
		for (int i = 0; i < iodList.size(); i++) {
			InternalOrderDetail pageIod = iodList.get(i);
			if (pageIod.getNum() != null && pageIod.getNum().floatValue() > 0) {
				sumnum += pageIod.getNum();
				pmId = pageIod.getProductManagerId();
			}
		}
		String hql_ywMarkId = "from ProcardTemplate where (markId=? or ywMarkId=?) "
				+ "and productStyle =?  and (banbenStatus is null or banbenStatus ='默认') "
				+ "and (dataStatus is null or dataStatus !='删除') and procardStyle='总成' order by ywMarkId desc";
		ProductManager productManager = (ProductManager) totalDao
				.getObjectById(ProductManager.class, pmId);
		if (productManager == null)
			return;// 订单明细为空，完善计划失败！
		String pieceNum = productManager.getPieceNumber();
		String ywMarkid = productManager.getYwMarkId();
		newIO.setIsPeiJian(productManager.getIsPeiJian());
		ProcardTemplate pt = null;
		List ptList = totalDao.query(hql_ywMarkId, pieceNum, pieceNum,
				productStyle);
		if (ptList != null && ptList.size() > 0) {
			if (ptList.size() > 1) {
				throw new RuntimeException(productStyle + "零件" + pieceNum
						+ "的bom出现重复,请删除一项后再转计划!");
			}else{
				pt=(ProcardTemplate) ptList.get(0);
			}
		}
		boolean b = false;
		if (pt == null) {
			// String sql1 = "from ProcardTemplatePrivilege where markId = ?";
			// if(productManager.getYwMarkId()!=null&&!"".equals(productManager.getYwMarkId())){
			// sql1+=" or markId = '"+productManager.getYwMarkId()+"' ";
			// }
			// Integer tqcount = totalDao.getCount(sql1
			// ,productManager.getPieceNumber());
			// if(tqcount!=null&&tqcount>0){
			// if (productStyle.equals("批产")) {
			// pt = (ProcardTemplate) totalDao.getObjectByCondition(
			// hql_ywMarkId, pieceNum, pieceNum, "试制");
			// } else {
			// pt = (ProcardTemplate) totalDao.getObjectByCondition(
			// hql_ywMarkId, pieceNum, pieceNum, "批产");
			// }
			// }
			if (pt == null) {
				if ("true".equals(productManager.getIsPeiJian())) {
					b = true;
				} else {
					throw new RuntimeException(pieceNum + "的BOM模版不存在!");
				}
			}
		} else {
			// Float noPzCount = (Float) totalDao
			// .getObjectByCondition(
			// "select count(*) from ProcardTemplate where (bzStatus is null or bzStatus!='已批准') and (banbenStatus is null or banbenStatus ='默认') and rootId=?",
			// pt.getRootId());
			// if (noPzCount != null && noPzCount > 0) {
			// throw new RuntimeException(pieceNum + "的BOM模版有" + noPzCount
			// + "个物料未审批通过,请等待!");
			// }
		}
		OrderManager om = oms.getOrderById(orderId);
		productManager.setPieceNumber(pieceNum);// 件号;
		if (b) {
			productManager.setYwMarkId(pieceNum);
		} else {
			ywMarkid = pt.getYwMarkId();// 配件的业务件号为空。pt.getYwMarkId()会空指针，不要赋值
		}
		if (productManager.getHasTurn() == null) {
			productManager.setHasTurn(0f);
		}
		Float chHasTurn = 0f;
		Float cxCount = 0f;
		if (productManager.getOrderManager().getOrderType().equals("正式")) {
			chHasTurn = productManager.getCxHasTurn() == null ? 0f
					: productManager.getCxHasTurn();
			if (productManager.getCxApplyCount() != null
					&& productManager.getCxCount() != null
					&& productManager.getCxApplyCount().floatValue() > 0)
				cxCount = productManager.getCxApplyCount()
						+ productManager.getCxCount();
		}
		if (sumnum > (productManager.getNum().floatValue() - cxCount)
				|| (productManager.getHasTurn() + chHasTurn) == productManager
						.getNum().floatValue()) {
			throw new RuntimeException("产品" + productManager.getPieceNumber()
					+ "计划数量不能超过订单总量(正式订单会扣去冲销数量显示)"
					+ (productManager.getNum() - cxCount) + ",转换失败!");
		} else if ((sumnum + cxCount.floatValue()) < productManager.getNum()
				.floatValue()) {
			throw new RuntimeException("产品" + productManager.getPieceNumber()
					+ "的订单总量(" + productManager.getNum() + ")需要全部分配完毕,转换失败!");
		}
		productManager.setHasTurn(productManager.getNum());

		String touchanDate = "";
		for (int i = 0; i < iodList.size(); i++) {
			InternalOrderDetail pageIod = iodList.get(i);
			if (pageIod.getNum() != null && pageIod.getNum().floatValue() > 0) {
			} else {
				if (touchanDate == "") {
					touchanDate = pageIod.getPaymentDate();
				}
				continue;
			}
			Product_Internal pi = new Product_Internal();
			pi.setMarkId(pieceNum);
			pi.setStatus("同意");
			Price price = pns.getPriceByPieceNum(pieceNum);
			if (price == null) {
				price = pns.getPriceByPieceNum(productManager.getYwMarkId());
			}

			InternalOrderDetail iod = new InternalOrderDetail(productManager
					.getName(), pieceNum, ywMarkid, pageIod.getNum(), 0F, "",
					ZEROl, "");
			iod.setTouchanDate(touchanDate);// 投产日期
			iod.setPaymentDate(pageIod.getPaymentDate());// 交付日期
			touchanDate = pageIod.getPaymentDate();
			iod.setOrderId(pageOm.getId());
			iod.setOrderNum(pageOm.getOrderNum());
			iod.setProductManagerId(pageIod.getProductManagerId());
			iod.setIsPeiJian(productManager.getIsPeiJian());
			newIO.getInterOrderDetails().add(iod);
			iod.setInternalOrder(newIO);
			iod.setUnit(productManager.getDanwei());
			totalDao.save(iod);
			pi.setIoDetailId(iod.getId());
			totalDao.save(pi);

			Float zhCount = pageIod.getNum();
			if (productManager.getPieceNumber().equals(pieceNum)) {
				List<ProductManager> relatePmList = null;
				if (om.getOrderType().equals("备货")
						|| om.getOrderType().equals("预测")) {
					relatePmList = totalDao
							.query(
									"from ProductManager where orderManager.orderType='正式' and orderManager.ep_statuts='同意' and (cxHasTurn is null or cxCount>cxHasTurn) and id in(select zsProductId from ProductZsAboutBh where bhProductId=?)",
									productManager.getId());
				}
				if (relatePmList != null && relatePmList.size() > 0) {// 反馈到冲销订单
					for (ProductManager relatePm : relatePmList) {
						InternalZsAboutBh izab = new InternalZsAboutBh();
						Integer pzsAboutbhId = (Integer) totalDao
								.getObjectByCondition(
										"select id from ProductZsAboutBh where zsProductId=? and bhProductId=?",
										relatePm.getId(), productManager
												.getId());
						izab.setPzsAboutbhId(pzsAboutbhId);
						izab.setIdetailId(iod.getId());
						izab.setMarkId(relatePm.getPieceNumber());
						izab.setYwMakrId(relatePm.getYwMarkId());
						if (relatePm.getCxHasTurn() == null) {
							relatePm.setCxHasTurn(0f);
						}
						if (zhCount.floatValue() > (relatePm.getCxCount()
								.floatValue() - relatePm.getCxHasTurn()
								.floatValue())) {
							izab.setCount(relatePm.getCxCount()
									- relatePm.getCxHasTurn());
							zhCount = zhCount
									- (relatePm.getCxCount() - relatePm
											.getCxHasTurn());
							relatePm.setCxHasTurn(relatePm.getCxCount());
						} else {
							izab.setCount(zhCount);
							relatePm.setCxHasTurn(relatePm.getCxHasTurn()
									+ zhCount);
							zhCount = 0f;
						}
						if (iod.getCxCount() == null) {
							iod.setCxCount(izab.getCount());
						} else {
							iod.setCxCount(iod.getCxCount() + izab.getCount());
						}
						totalDao.update(iod);
						totalDao.update(relatePm);
						totalDao.save(izab);
						if (zhCount == 0) {
							break;
						}
					}
				}
				pi.setHasruku(0f);
				pi.setAllcount(pageIod.getNum());
				pi.setProductId(productManager.getId());
				totalDao.update(pi);
			}
		}
		newIO.setProducttype(productStyle);
		if (newIO.getId() > 0) {
			totalDao.update(newIO);
		} else {
			totalDao.save(newIO);
		}
		productManager.setStatus("计划转换");
		totalDao.update(productManager);
		// 消息推送
		if ("同意".equals(pageOm.getEp_statuts())) {
			AlertMessagesServerImpl.addAlertMessages("内部计划转换(无卡)",
					"您有新的内部计划需要转换:" + productManager.getPieceNumber() + "("
							+ productManager.getYwMarkId() + ")"
							+ productManager.getNum()
							+ productManager.getDanwei(), "2");

			// AlertMessagesServerImpl.addAlertMessages("检验提醒", "员工:"
			// + breaksubmit.getTjUsersName() + "生产时发现外购件:"
			// + markIds[i] + "不合格，不合格数量:"
			// + breaksubmit1.getTjbreakcount() + " 请及时检验确认!",
			// userIds, "", true, "false");
		}

	}

	/***
	 * 根据订单明细产品id查找生产计划
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public List findIodByProductId(Integer productId) {
		if (productId != null) {
			return totalDao.query(
					"from InternalOrderDetail where productManagerId=?",
					productId);
		}
		return null;
	}

	/***
	 * 查询所有生产计划
	 * 
	 * @param iod
	 * @param pageStatus
	 * @return
	 */
	@Override
	public List findAllIod(InternalOrderDetail iod, String pageStatus) {
		return totalDao.query("from InternalOrderDetail order by touchanDate");
	}

	public Object[] queryOrderManagerByinnerOrderId(int innelOrderId) {
		InternalOrder io = getInternalOrderById(innelOrderId);
		List list = new ArrayList();
		for (Iterator<OrderManager> it = io.getOuterOrders().iterator(); it
				.hasNext();) {
			OrderManager om = it.next();
			list.add(om);
		}
		if (list != null) {
			// 统计订单的工序完成进度
			for (int i = 0; i < list.size(); i++) {
				OrderManager order = (OrderManager) list.get(i);
				// 总工序数
				String hql_process_all = "select sum(totalCount),sum(submmitCount) from ProcessInfor where  procard.id in "
						+ "(select id from Procard where planOrderId in "
						+ "(select io.id from InternalOrder io join io.outerOrders oo where oo.id=? ))";
				Object[] sums = (Object[]) totalDao.getObjectByCondition(
						hql_process_all, order.getId());

				if (sums[0] == null) {
					sums[0] = 0;
				}
				if (sums[1] == null) {
					sums[1] = 0;
				}
				Float allprocessNum = Float.valueOf(sums[0].toString());
				// 提交工序数
				Float finalprocessNum = Float.valueOf(sums[1].toString());
				Float jindu = finalprocessNum / allprocessNum * 100;
				if (allprocessNum == 0) {
					jindu = 0F;
				}
				order.setCgjindu(jindu);

				String hql_pro = "from ProductManager where orderManager.id=?";
				List pro_list = totalDao.query(hql_pro, order.getId());
				if (pro_list != null) {
					order.setPmList(pro_list);
				}

			}
		}
		return new Object[] { list, list.size() };
	}

	public boolean auditOrder(int id, boolean ifAgree, String pageStatus) {
		InternalOrder io = getInternalOrderById(id);
		List<Product_Internal> piList = totalDao
				.query(
						"from Product_Internal where ioDetailId in (select id from InternalOrderDetail where internalOrder.id=?)",
						io.getId());
		if (ifAgree) {
			if (pageStatus != null && pageStatus.equals("waiAudit")) {
				io.setStatus("经理审核");
				if (piList != null && piList.size() > 0) {
					for (Product_Internal pi : piList) {
						pi.setStatus("经理审核");
						totalDao.update(pi);
					}
				}
			} else {
				io.setStatus(InternalOrderServiceImpl.AGREE);
				if (piList != null && piList.size() > 0) {
					for (Product_Internal pi : piList) {
						pi.setStatus(InternalOrderServiceImpl.AGREE);
						totalDao.update(pi);
					}
				}
				// OrderManager order= (OrderManager)
				// totalDao.getObjectByCondition("from OrderManager where id in (select o.id from OrderManager o join o.innerOrders oi where oi.id=?)",
				// io.getId());
				// if(order!=null&&(order.getOrderType()==null||order.getOrderType().equals("正式"))){
				// //正式订单查看对应的件号是否有备货或者预测订单
				// Set<ProductManager> productSet = order.getProducts();
				// if(productSet!=null&&productSet.size()>0){
				// for(ProductManager product:productSet){
				// List<InternalOrderDetail> internalOrderDetails=
				// totalDao.query("from InternalOrderDetail where pieceNumber=? and (zyCount is null or zyCount<turnCount) and turnCount is not null and turnCount>0 and (internalOrder.orderType is null or internalOrder.orderType!='正式')",
				// product.getPieceNumber());
				// if(internalOrderDetails!=null&&internalOrderDetails.size()>0){
				// int needNum=product.getNum();
				// for(InternalOrderDetail
				// internalOrderDetail:internalOrderDetails){
				// if(internalOrderDetail.getZyCount()==null){
				// internalOrderDetail.setZyCount(0f);
				// }
				// if((internalOrderDetail.getTurnCount()-internalOrderDetail.getZyCount())>=product.getNum()){
				// internalOrderDetail.setZyCount(internalOrderDetail.getZyCount()+product.getNum());
				// break;
				// }else{
				// internalOrderDetail.setZyCount(internalOrderDetail.getTurnCount());
				// needNum-=internalOrderDetail.getTurnCount()-internalOrderDetail.getZyCount();
				// }
				// }
				// }
				// }
				// }
				// }
			}

		} else {
			io.setStatus(InternalOrderServiceImpl.goBack);
			if (piList != null && piList.size() > 0) {
				for (Product_Internal pi : piList) {
					pi.setStatus("打回");
					totalDao.update(pi);
					ProductManager pm = (ProductManager) totalDao
							.getObjectById(ProductManager.class, pi
									.getProductId());
					pm.setHasTurn(pm.getHasTurn() - pi.getAllcount());
					totalDao.update(pm);
				}
			}
		}

		Boolean bol = update(io);
		return bol;
	}

	public String validateNum(String ids, String pieceNum, int num,
			String ioIdStr) {
		Map msgMap = new HashMap();
		String msg = null;
		Float currentNum = null;
		Map<String, Float> map = new HashMap<String, Float>();
		if (ioIdStr == null) {
			String[] idStr = ids.split(",");
			for (int i = 0; i < idStr.length; i++) {
				OrderManager om = oms.getOrderById(Integer.parseInt(idStr[i]));
				map = countUndistributedNum(om, map);
			}
		} else {
			int ioId = Integer.parseInt(ioIdStr);
			InternalOrder io = getInternalOrderById(ioId);
			for (Iterator<OrderManager> it = io.getOuterOrders().iterator(); it
					.hasNext();) {
				OrderManager om = it.next();
				map = countUndistributedNum(om, map);
			}
			currentNum = getInnerDetailDistributeNum(Integer.parseInt(ids));
		}
		if (map.containsKey(pieceNum)) {
			Float oldNum = map.get(pieceNum);
			if (num > oldNum) {
				msgMap.put("msg", "该零件号：" + pieceNum + "的产品还能分配：" + oldNum);
				if (currentNum != null) {
					msgMap.put("num", currentNum);
				}
				msg = JSONObject.toJSON(msgMap).toString();
				return msg;
			} else if (oldNum == num) {
			}
		}
		return msg;
	}

	public Map countUndistributedNum(OrderManager om, Map<String, Float> map) {
		for (Iterator<ProductManager> pmIt = om.getProducts().iterator(); pmIt
				.hasNext();) {
			ProductManager pm = pmIt.next();
			if (map.containsKey(pm.getPieceNumber())) {
				Float pmNum = map.get(pm.getPieceNumber());
				pmNum += pm.getNum() - pm.getAllocationsNum();
				map.put(pm.getPieceNumber(), pmNum);
			} else {
				map.put(pm.getPieceNumber(), pm.getNum()
						- pm.getAllocationsNum());
			}
		}
		return map;
	}

	public Object[] queryExaminePass(Map map, int pageNo, int pageSize) {
		String hql = "from InternalOrder i where 1=1 and i.status = '同意' and (i.whetherPurchase = '未采购' or i.whetherPurchase = '采购部分' )";
		if (map != null) {
			if (map.get("orderNum") != null) {
				hql += " and i.num like '%" + map.get("orderNum") + "%'";
			}
			if (map.get("custome") != null) {
				hql += " and i.custome.id = '" + map.get("custome") + "'";
			}
			if (map.get("beginTime") != null && map.get("endTime") != null) {
				hql += " and (i.newDate between '" + map.get("beginTime")
						+ "' and '" + map.get("endTime") + "')";
			}
		}
		hql += " order by i.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public String countPurchaseAmount(int[] select) {
		boolean ifNot;
		int count;
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < select.length; i++) {
			count = 0;
			ifNot = false;
			InternalOrder io = getInternalOrderById(select[i]);
			for (Iterator<InternalOrderDetail> it = io.getInterOrderDetails()
					.iterator(); it.hasNext();) {
				InternalOrderDetail iod = it.next();
				Templet tem = queryTempletByPieceNumber(iod.getPieceNumber());
				if (tem != null) {
					if (!whetherExistPurchase(io.getNum(), iod.getPieceNumber())) {
						Detail deta = new Detail();
						deta.setDetailNumber(io.getNum());
						deta.setMonth(io.getGenertorDate());
						deta.setQuantity(iod.getNum());
						deta.setExplanation(iod.getRemark());
						boolean whether = ds.add(tem, deta);
						if (whether) {
							count++;
							ifNot = true;
						} else {
							count--;
							map.put("msg", "\r\n申请采购订单出错!; ");
						}
					}
				} else {
					map.put(iod.getPieceNumber(), "\r\n工艺产品信息不存在,产品件号："
							+ iod.getPieceNumber() + "; ");
				}
			}
			if (count == io.getInterOrderDetails().size()) {
				io.setWhetherPurchase(COMPLETE);
			} else {
				if (ifNot == true)
					io.setWhetherPurchase(PART);
				else
					io.setWhetherPurchase(NOTPURCHASE);
			}
			update(io);
		}
		if (map.size() > 0) {
			for (Iterator<Entry<String, String>> it = map.entrySet().iterator(); it
					.hasNext();) {
				Entry en = it.next();
				str.append(en.getValue());
			}
			return str.toString();
		}
		return null;
	}

	public Templet queryTempletByPieceNumber(String pieceNumber) {
		String hql = "from Templet t where t.partsNumber = ?";
		List list = totalDao.query(hql, pieceNumber);
		if (list != null && list.size() > 0) {
			return (Templet) list.get(0);
		}
		return null;
	}

	public boolean whetherExistPurchase(String orderNum, String pieceNum) {
		String hql = "from Detail d where d.detailNumber = ? and d.templet.partsNumber = ?";
		List list = totalDao.query(hql, orderNum, pieceNum);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public IOrderManagerService getOms() {
		return oms;
	}

	public void setOms(IOrderManagerService oms) {
		this.oms = oms;
	}

	public IPieceNumService getPns() {
		return pns;
	}

	public void setPns(IPieceNumService pns) {
		this.pns = pns;
	}

	public DetailService getDs() {
		return ds;
	}

	public void setDs(DetailService ds) {
		this.ds = ds;
	}

	@Override
	public String delById(int id) {
		if (id != 0) {
			List<Procard> plist = totalDao.query(
					"from Procard where planOrderId=?", id);
			if (plist != null && plist.size() > 0) {
				return "此内部计划已转生产不允许删除!";
			}
			InternalOrder interOrder = getInternalOrderById(id);
			if (interOrder != null) {
				for (Iterator<InternalOrderDetail> it = interOrder
						.getInterOrderDetails().iterator(); it.hasNext();) {
					InternalOrderDetail iod = it.next();
					Float count = iod.getNum(); // 该产品分配数量
					for (Iterator<OrderManager> omIt = interOrder
							.getOuterOrders().iterator(); omIt.hasNext();) {
						OrderManager om = omIt.next();
						if (count == 0) {
							break;
						}
						for (Iterator<ProductManager> pmIt = om.getProducts()
								.iterator(); pmIt.hasNext();) {
							ProductManager pm = pmIt.next();
							if (iod.getPieceNumber()
									.equals(pm.getPieceNumber())) {
								if (pm.getHasTurn() == null) {
									pm.setHasTurn(0f);
								}
								pm.setHasTurn(pm.getHasTurn() - count);
								if (pm.getHasTurn().floatValue() < 0) {
									pm.setHasTurn(0f);
								}
								// int sumNum = count + pm.getAllocationsNum();
								// if (sumNum <= pm.getNum()) {
								// // pm.setAllocationsNum(sumNum);
								// count = pm.getNum() - sumNum;
								// } else {
								// count = sumNum - pm.getNum();
								// // pm.setAllocationsNum(pm.getNum());
								// }
							}
						}
					}
				}
				for (Iterator<OrderManager> it = interOrder.getOuterOrders()
						.iterator(); it.hasNext();) {
					OrderManager om = it.next();
					om.getInnerOrders().remove(interOrder);
				}
				interOrder.setOuterOrders(null);
				interOrder.setCustome(null);
				return this.totalDao.delete(interOrder) + "";
			}
		}
		return "没有找到目标,删除失败!";
	}

	public void validateComplete() {
		// TODO Auto-generated method stub
		boolean flag = false;
		int realityNum; // 实际
		int undistributedNum; // 未
		int distributeNum; // 已
		int completeNum; // 完成
		Map<String, int[]> outMap = statisticsOrderManager();
		Map<String, int[]> innerMap = statisticsInternalOrder();
		for (Iterator<Entry<String, int[]>> it = outMap.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, int[]> en = it.next();
			if (!innerMap.containsKey(en.getKey())) {
				flag = false;
				return; // 内部订单如果找不到该产品,则该产品未生成内部计划
			}
			realityNum = en.getValue()[ZERO];
			undistributedNum = en.getValue()[ONE];
			if (undistributedNum != ZERO) {
				flag = false;
				return; // 如果还为分配完实际数量,则结束
			}
			distributeNum = innerMap.get(en.getKey())[ZERO];
			completeNum = innerMap.get(en.getKey())[ONE];
			if (distributeNum != completeNum) {
				flag = false;
				return; // 如果分配数量不等于入库数量,则结束
			}
			if (distributeNum != realityNum) {
				flag = false;
				return; // 如果分配数量不等于实际数量,则结束
			}
			flag = true;
		}
		if (flag) {
			String hql = "from InternalOrder i where 1=1 and  i.status = '同意' and i.isVali = 0";
			Iterator<InternalOrder> it = totalDao.query(hql).iterator();
			while (it.hasNext()) {
				InternalOrder io = it.next();
				io.setIsVali(ONE);
				for (Iterator<OrderManager> omIt = io.getOuterOrders()
						.iterator(); omIt.hasNext();) {
					OrderManager om = omIt.next();
					om.setDeliveryStatus(YES);
					totalDao.update(om);
				}
				totalDao.update(io);
			}
		}

	}

	public Map statisticsOrderManager() {
		Map<String, Float[]> map = new HashMap<String, Float[]>();
		String hql = "from InternalOrder i where 1=1 and i.status = '同意' and i.isVali = 0";
		Iterator<InternalOrder> it = totalDao.query(hql).iterator();
		InternalOrder io = null;
		if (it != null && it.hasNext()) {
			io = it.next();
			for (Iterator<OrderManager> odIt = io.getOuterOrders().iterator(); odIt
					.hasNext();) {
				OrderManager om = odIt.next();
				for (Iterator<ProductManager> pmIt = om.getProducts()
						.iterator(); pmIt.hasNext();) {
					ProductManager pm = pmIt.next();
					if (map.containsKey(pm.getPieceNumber())) {
						Float[] nums = map.get(pm.getPieceNumber());
						nums[ZERO] += pm.getNum();
						nums[ONE] += pm.getAllocationsNum();
						map.put(pm.getPieceNumber(), nums);
					} else {
						map.put(pm.getPieceNumber(), new Float[] { pm.getNum(),
								pm.getAllocationsNum() });
					}
				}
			}
		}
		return map;
	}

	public Map statisticsInternalOrder() {
		Map<String, Float[]> map = new HashMap<String, Float[]>();
		String hql = "from InternalOrder i where 1=1 and  i.status = '同意' and i.isVali = 0";
		Iterator<InternalOrder> it = totalDao.query(hql).iterator();
		while (it.hasNext()) {
			InternalOrder io = it.next();
			for (Iterator<InternalOrderDetail> iodIt = io
					.getInterOrderDetails().iterator(); iodIt.hasNext();) {
				InternalOrderDetail iod = iodIt.next();
				if (map.containsKey(iod.getPieceNumber())) {
					Float[] nums = map.get(iod.getPieceNumber());
					nums[ZERO] += iod.getNum();
					nums[ONE] += iod.getQuantityCompletion();
					map.put(iod.getPieceNumber(), nums);
				} else {
					map.put(iod.getPieceNumber(), new Float[] { iod.getNum(),
							iod.getQuantityCompletion() });
				}
			}
		}
		return map;
	}

	public Float getInnerDetailDistributeNum(int ioId) {
		Float num = 0f;
		String hql = "from InternalOrderDetail i where i.id = ?";
		List<InternalOrderDetail> list = totalDao.query(hql, ioId);
		if (list != null && list.size() > 0) {
			InternalOrderDetail iod = list.get(0);
			num = iod.getNum();
		}
		return num;
	}

	/***
	 * 查询所有已同意的内部生产计划(用于生成流水卡片)
	 * 
	 * @param internalOrder
	 * @param pageNo
	 * @param pageSize
	 * @author 刘培
	 * @return Object[]
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findAllAgreeOrder(InternalOrder internalOrder, int pageNo,
			int pageSize, String status) {
		if (internalOrder == null) {
			internalOrder = new InternalOrder();
		}
		// internalOrder.setStatus("同意");// 只查询同意的内部计划
		internalOrder.setOuterOrders(null);// 清空
		internalOrder.setInterOrderDetails(null);// 清空

		String str = "";
		if (internalOrder.getPieceNumber() != null
				&& !"".equals(internalOrder.getPieceNumber())) {
			str = " and id  in ( select internalOrder.id from InternalOrderDetail where pieceNumber like '%"
					+ internalOrder.getPieceNumber()
					+ "%' or ywMarkId like '%"
					+ internalOrder.getPieceNumber() + "%')";
		}
		String str1 = "";
		if (internalOrder.getCustome() != null
				&& internalOrder.getCustome().getId() != null
				&& internalOrder.getCustome().getId() > 0) {
			str1 = " and custome.id =" + internalOrder.getCustome().getId();
		}
		internalOrder.setCustome(null);
		internalOrder.setPieceNumber(null);
		 String str2 = " and (producttype is null or producttype='批产')  ";//
		 //MySQl
		 if ("sz".equals(status)) {
		 str2 = " and producttype = '试制' ";
		 }
		String hql = totalDao.criteriaQueries(internalOrder, null)
				+str2+" and zhStatus='已转完' and status in ('同意','完成')";
		// 不查状态是取消的订单
		// String str0 =
		// " and id not in ( select internalOrder.id  from  InternalOrderDetail where productManagerId in (select id from  "
		// +
		// " ProductManager where status = '取消' )) ";
		hql += str + str1;
		List list = totalDao.findAllByPage(hql + " order by id desc", pageNo,
				pageSize);
		List newList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				InternalOrder internalOrderold = (InternalOrder) list.get(i);
				internalOrderold.setBzStatus("完成");
				Set<InternalOrderDetail> interOrderDetails = internalOrderold
						.getInterOrderDetails();
				String markId = "";
				// String shebiDate ="";
				if (interOrderDetails == null || interOrderDetails.size() <= 0) {
					markId = "<hr/>";
				}

				for (InternalOrderDetail internalOrderDetail : interOrderDetails) {
					if (internalOrderDetail.getYwMarkId() == null) {
						internalOrderDetail.setYwMarkId("");
					}
					if (internalOrderDetail.getPaymentDate() == null) {
						internalOrderDetail.setPaymentDate("");
					}
					// Float noPzCount = (Float) totalDao
					// .getObjectByCondition(
					// "select count(*) from ProcardTemplate where (bzStatus is null or bzStatus!='已批准') and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除') "
					// +
					// " and rootId in(select id from ProcardTemplate where markId=? and procardStyle='总成' and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除'))",
					// internalOrderDetail.getPieceNumber());
					// if (noPzCount != null && noPzCount > 0) {
					// internalOrderold.setBzStatus("<font color='red'>"
					// + noPzCount.intValue() + "项物料未审批</font>");
					// }
					// String shebiDate_sql =
					// "select pt.procardTemplateBanBenApply.applyTime from ProcardTemplateBanBen pt,Procard p where pt.banci = "
					// +
					// "(p.banci-1) and pt.procardTemplateBanBenApply.processStatus <> '取消' and pt.markId = p.markId and p.rootId = ?  order by pt.procardTemplateBanBenApply.applyTime DESC";
					//					
					// String shebiDate_new
					// =(String)totalDao.getObjectByQuery(shebiDate_sql,
					// internalOrderDetail.getProcardId());
					// if(!"".equals(shebiDate)){
					// if(shebiDate.compareTo(shebiDate_new)<0){
					// shebiDate = shebiDate_new;
					// }
					// }
					markId += "[<font color='red'>"
							+ internalOrderDetail.getPieceNumber()
							+ "("
							+ internalOrderDetail.getYwMarkId()
							+ ")</font>]:<b>"
							+ (internalOrderDetail.getNum() - internalOrderDetail
									.getTurnCount()) + "/"
							+ internalOrderDetail.getNum() + "</b>;<br/>交期:<b>"
							+ internalOrderDetail.getPaymentDate()
							+ "</b><hr/>";

				}
				if (markId != null && !"".equals(markId)) {
					internalOrderold.setPieceNumber(markId.substring(0, markId
							.lastIndexOf("<hr/>")));
					newList.add(internalOrderold);
				}
				// if(shebiDate != null && !"".equals(shebiDate)){
				// internalOrderold.setShebeiDate(shebiDate);
				// }

			}
		}
		int count = totalDao.getCount(hql);
		Object[] o = { newList, count };
		return o;

	}

	// 未转换的信息
	@Override
	public List<InternalOrder> findNoStatus(InternalOrder internalOrder,
			String status) {
		String str = "";
		String markId = internalOrder.getPieceNumber();
		if (markId != null && !"".equals(markId)) {
			str = " and id  in ( select internalOrder.id from InternalOrderDetail where pieceNumber like '%"
					+ markId + "%' or ywMarkId like '%" + markId + "%')";
		}
		 String str1 = " and  (producttype is null or producttype='批产') ";//
		 //MYSQL
		 if ("sz".equals(status)) {
		 str1 = " and producttype = '试制' ";
		 }
		// String str0 =
		// " and id not in ( select internalOrder.id  from  InternalOrderDetail where productManagerId in (select id from  "
		// +
		// " ProductManager where status = '取消' )) ";
		String hql = totalDao.criteriaQueries(internalOrder, null,
				"pieceNumber", "custome")
				+ " and zhStatus='未转完' and status='同意'" + str +str1+ " order by id";
		// 不查状态是取消的订单
		List list = totalDao.query(hql);
		List newList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			InternalOrder internalOrderold = (InternalOrder) list.get(i);
			Set<InternalOrderDetail> interOrderDetails = internalOrderold
					.getInterOrderDetails();
			internalOrderold.setBzStatus("完成");
			String markIds = "";
			String shebiDate = "";
			for (InternalOrderDetail internalOrderDetail : interOrderDetails) {
				if (internalOrderDetail.getYwMarkId() == null) {
					internalOrderDetail.setYwMarkId("");
				}
				if (internalOrderDetail.getPaymentDate() == null) {
					internalOrderDetail.setPaymentDate("");
				}
				String sql1 = "from ProcardTemplatePrivilege where markId = ?";
				if (internalOrderDetail.getYwMarkId() != null
						&& !"".equals(internalOrderDetail.getYwMarkId())) {
					sql1 += " or markId = '"
							+ internalOrderDetail.getYwMarkId() + "' ";
				}
				Integer tqcount = totalDao.getCount(sql1, internalOrderDetail
						.getPieceNumber());
				String productStylesql = " and 1=1";
				if (tqcount == null || tqcount == 0) {
					if (internalOrderold.getProducttype().equals("批产")) {
						productStylesql = " and productStyle='批产'";
					} else if (internalOrderold.getProducttype().equals("试制")) {
						productStylesql = " and productStyle='试制'";
					} else {
						internalOrderold
								.setBzStatus("<font color='red'>无法判别生产类型</font>");
						markIds += "[<font color='red'>"
								+ internalOrderDetail.getYwMarkId()
								+ "</font>]:<b>"
								+ (internalOrderDetail.getNum() - internalOrderDetail
										.getTurnCount()) + "/"
								+ internalOrderDetail.getNum()
								+ "</b>;<br/>交付日期:<b>"
								+ internalOrderDetail.getPaymentDate()
								+ "</b><hr/>";
						continue;
					}
				}
				String shebiDate_sql = "select pt.procardTemplateBanBenApply.applyTime from ProcardTemplateBanBen pt,ProcardTemplate p where pt.newbanci = "
						+ "p.banci and pt.procardTemplateBanBenApply.processStatus <> '取消' and (p.bzStatus is null or p.bzStatus !='已批准') "
						+ "and pt.markId = p.markId and p.rootId in(select id from ProcardTemplate where (markId=? or ywMarkId=?))  order by pt.procardTemplateBanBenApply.applyTime DESC";
				String shebiDate_new = (String) totalDao.getObjectByQuery(
						shebiDate_sql, internalOrderDetail.getPieceNumber(),
						internalOrderDetail.getPieceNumber());
				if (shebiDate != null && !"".equals(shebiDate)
						&& shebiDate_new != null && !"".equals(shebiDate_new)) {
					if (shebiDate.compareTo(shebiDate_new) < 0) {
						shebiDate = shebiDate_new;
					}
				} else {
					shebiDate = shebiDate_new;
				}
				Float noPzCount = (Float) totalDao
						.getObjectByCondition(
								"select count(*) from ProcardTemplate where (bzStatus is null or bzStatus!='已批准') and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除') "
										+ " and rootId in(select id from ProcardTemplate where (markId=? or ywMarkId=?) "
										+ productStylesql
										+ " and procardStyle='总成' and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除'))",
								internalOrderDetail.getPieceNumber(),
								internalOrderDetail.getPieceNumber());
				if (noPzCount != null && noPzCount.floatValue() > 0) {
					internalOrderold.setBzStatus("<font color='red'>"
							+ noPzCount.intValue() + "项物料未审批</font>");
					markIds += "[<font color='red'>"
							+ internalOrderDetail.getYwMarkId()
							+ "</font>]:<b>"
							+ (internalOrderDetail.getNum() - internalOrderDetail
									.getTurnCount()) + "/"
							+ internalOrderDetail.getNum()
							+ "</b>;<br/>交付日期:<b>"
							+ internalOrderDetail.getPaymentDate()
							+ "</b><hr/>";
				} else {
					markIds += "[<font color='red'>"
							+ internalOrderDetail.getYwMarkId()
							+ "</font>]:<b>"
							+ (internalOrderDetail.getNum() - internalOrderDetail
									.getTurnCount()) + "/"
							+ internalOrderDetail.getNum()
							+ "</b>;<br/>交付日期:<b>"
							+ internalOrderDetail.getPaymentDate()
							+ "</b><hr/>";
					Float hasBom = (Float) totalDao
							.getObjectByCondition(
									"select count(*) from ProcardTemplate where procardStyle='总成' and (markId=? or ywMarkId=?) and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除') and procardStyle='总成' "
											+ productStylesql,
									internalOrderDetail.getPieceNumber(),
									internalOrderDetail.getPieceNumber());
					if (hasBom == null || hasBom.floatValue() == 0) {
						internalOrderold
								.setBzStatus("<font color='red'>无对应工程BOM</font>");
					}
				}
			}
			if (markIds.lastIndexOf("<hr/>") > 0) {
				internalOrderold.setPieceNumber(markIds.substring(0, markIds
						.lastIndexOf("<hr/>")));
			}
			internalOrderold.setShebeiDate(shebiDate);
			newList.add(internalOrderold);
		}
		return newList;
	}

	/*
	 * 
	 */
	public List<ProcardTemplateBanBenApply> findPtoTbanbenApply(Integer id) {
		// if(id!=null){
		// InternalOrder io = (InternalOrder)totalDao.get(InternalOrder.class,
		// id);
		// if(io!=null&&io.getInterOrderDetails()!=null&&io.getInterOrderDetails().size()>0){
		// //获取明细
		// Set<InternalOrderDetail> interOrderDetails =
		// io.getInterOrderDetails();
		// List<String> ywMarkIdList = new ArrayList<String>();
		// //获取ywmarkId
		// for(InternalOrderDetail iod :interOrderDetails){
		// if(iod.getYwMarkId()!=null&&!"".equals(iod.getYwMarkId())){
		// ywMarkIdList.add(iod.getYwMarkId());
		// }
		// }
		// //获取ywmarkId所对应的所有Bom id;
		// List<Integer> ptListId =new ArrayList<Integer>();
		// if(ywMarkIdList!=null&&ywMarkIdList.size()>0){
		// for(String ywMarkId : ywMarkIdList){
		// String hql =
		// "select rootid from ProcardTemplate where ywMarkId = ? and rootid = id";
		// Integer rootId = (Integer)totalDao.getObjectByCondition(hql,
		// ywMarkId);
		// if(rootId!=null){
		// ptListId.add(rootId);
		// }
		// }
		// }else{
		// return null;
		// }
		// //根据rootId 获取 所有零件的markid
		// if(ptListId!=null&&ptListId.size()>0){
		// String ids ="";
		// for(Integer root_id:ptListId){//拼接rootId
		// if("".equals(ids)){
		// ids += Integer.toString(root_id);
		// }else{
		// ids +=","+ Integer.toString(root_id);
		// }
		// }
		// if(!"".equals(ids)){
		// String hql_ptbe =
		// "select procardTemplateBanBenApply.id from ProcardTemplateBanBen where markId in (select markId from ProcardTemplate where rootId in ("+ids+") and  (bzStatus is null or bzStatus !='已批准') "
		// +
		// "and (banbenStatus is null banbenStatus !='历史') and (dataStatus is null dataStatus !='删除') )";
		// List<Integer> ptbeaId = totalDao.query(hql_ptbe);
		// String ptbeaids = "";
		// for(Integer ptbea_id:ptbeaId){//拼接rootId
		// if("".equals(ids)){
		// ptbeaids += Integer.toString(ptbea_id);
		// }else{
		// ptbeaids +=","+ Integer.toString(ptbea_id);
		// }
		// }
		// if(!"".equals(ids)){
		// String hql_ptbea =
		// "from ProcardTemplateBanBenApply where id in("+ids+")";
		// List<ProcardTemplateBanBenApply> ptbeaList =
		// totalDao.query(hql_ptbea);
		// return ptbeaList;
		// }else{
		// return null;
		// }
		// }else{
		// return null;
		// }
		// }else{
		// return null;
		// }
		// }else{
		// return null;
		// }
		// }else{
		return null;
		// }

	}

}
