package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IOrderManagerService;
import com.task.Server.InsuranceGoldServer;
import com.task.Server.WageStandardServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Becoming;
import com.task.entity.ClientManagement;
import com.task.entity.Evaluators;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.InsuranceGold;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.InternalZsAboutBh;
import com.task.entity.Machine;
import com.task.entity.OrderManager;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.ProductZsAboutBh;
import com.task.entity.Sell;
import com.task.entity.TaHkHuikuan;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.entity.sop.ProcessInforWWApply;
import com.task.entity.sop.RunningWaterCard;
import com.task.entity.sop.YcProduct;
import com.task.entity.sop.YcWaiGouProcrd;
import com.task.entity.sop.YcWeekFePei;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.AuditNode;
import com.task.entity.system.CircuitCustomize;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.ZhUser;

public class OrderManagerServiceImpl implements IOrderManagerService {
	// private Float cgNum = 0f;
	private TotalDao totalDao;
	private WageStandardServer wss; // 工资模板
	private InsuranceGoldServer igs; // 五险一金Server层
	private static final Double SECONDS = 619200.0;

	/**
	 * @Title: add
	 * @Description: 添加
	 * @param om
	 * @return void
	 * @throws
	 */
	public String add(OrderManager om, List<ProductManager> pmList) {
		if (om.getDocumentaryPeopleId() != null) {
			Users user = (Users) totalDao.getObjectById(Users.class, om
					.getDocumentaryPeopleId());
			if (user != null) {
				om.setDocumentaryPeople(user.getName());
			}
		}
		String productStyle = "批产";
		String processName = "订单申请审核";
		if (om.getProducttype() != null && "试制".equals(om.getProducttype())) {
			processName = "试制订单申请审核";
			productStyle = "试制";
		}
		if ("售后".equals(om.getOrderType())) {
			processName = "售后订单申请审核";
		}
		String mes_products = "";
		if (pmList != null && pmList.size() > 0) {

			Set<ProductManager> pmSet = new HashSet<ProductManager>();
			if (om.getId() > 0 && om.getProducts() != null) {
				pmSet = om.getProducts();
			}
			Double totalAmount = 0d;
			for (int i = 0; i < pmList.size(); i++) {
				// String markId = pmList.get(i).getPieceNumber();
				// String fmarkId = pmList.get(i).getFmarkid();
				//				
				// if(markId!=null && fmarkId!=null){
				// if(markId== fmarkId || markId.equals(fmarkId)){
				// //总成
				//						
				// }else{
				// //配件
				//						
				// }
				// }
				if (pmList.get(i).getNum() <= 0) {
					continue;
				}
				ProductManager pm = new ProductManager();
				pm.setFmarkid(pmList.get(i).getFmarkid());// 关联配件
				pm.setType(pmList.get(i).getType());// 型别
				pm.setName(pmList.get(i).getName());// 名称
				pm.setNum(pmList.get(i).getNum());// 数量;
				pm.setUnitPrice(pmList.get(i).getUnitPrice());// 总价;
				pm.setUnit(pmList.get(i).getUnit());// 单价
				pm.setBhsPrice(pmList.get(i).getBhsPrice());// 不含税价
				pm.setTaxprice(pmList.get(i).getTaxprice());// 税率
				pm.setDanwei(pmList.get(i).getDanwei());// 单位
				pm.setRemark(pmList.get(i).getRemark());// 备注
				pm.setCxApplyCount(0f);// 冲销申请数量（未通过审批的预冲销数量）
				pm.setCxCount(0f);// 冲销数量（对预测或备货订单,指冲销给正式订单数量，对正式订单通过冲销方式生产的数量）
				pm.setCxHasTurn(0f);// 冲销已转数量（标记正式订单中冲销的已经转换的数量，正式订单中已转数量应为：cxHasTurn+hasTurn）
				pm.setCxRukuCount(0f);// （关联备货订单入库）冲销入库数量(正式订单记录冲销入库的数量,表示已生产完成不管是在备货库中还是在成品库中)
				pm.setCxzkuCount(0f);// 冲销转库数量（从备货转入成品冲销的入库数量,转库之后增加正式订单的allocationsNum）
				pm.setCxzkuApplyCount(0f);// 冲销转库申请数量
				pm.setStatus("计划完善");
				if (pmList.get(i).getPaymentDate() != null
						&& !"".equals(pmList.get(i).getPaymentDate())) {
					pm.setPaymentDate(pmList.get(i).getPaymentDate());// 交付日期
				}
				ProcardTemplate pt = null;
				List list=totalDao
						.query(
								"from ProcardTemplate where (markId=? or ywMarkId=?) and productStyle = ? and procardStyle='总成' "
										+ " and (banbenStatus is null or banbenStatus = '默认')"
										+ " and (dataStatus is null or dataStatus<>'删除')  ",
								pmList.get(i).getPieceNumber(), pmList.get(i)
										.getPieceNumber(), productStyle);
				
				if (list != null&&list.size()>0) {
					if(list.size()>1){
						return productStyle+"零件"+pmList.get(i).getPieceNumber()+"的bom出现重复,请删除一项后再下单!";
					}
					pt=(ProcardTemplate) list.get(0);
					pm.setPieceNumber(pt.getMarkId());// 件号;
					pm.setYwMarkId(pt.getYwMarkId());
				} else {
					pm.setPieceNumber(pmList.get(i).getPieceNumber());// 件号;
					pm.setYwMarkId(pmList.get(i).getPieceNumber());
				}
				if (pm.getFmarkid() == null
						|| pm.getPieceNumber().equals(pm.getFmarkid())
						|| pm.getFmarkid().equals(pm.getYwMarkId())) {// 总成
					pm.setIsPeiJian("false");
				} else {
					pm.setIsPeiJian("true");
				}
				mes_products += "\n" + (i + 1) + "、" + pm.getPieceNumber()
						+ "(" + pm.getYwMarkId() + "),数量:" + pm.getNum()
						+ pm.getDanwei() + ",交付:" + pm.getPaymentDate();
				pm.setPriceId(pmList.get(i).getPriceId());// 价格表Id
				if (om.getOrderType() != null && om.getOrderType().equals("正式")) {
					String ids = pmList.get(i).getPmids();
					if (ids != null && om.getOrderType().length() > 0) {// 冲销
						Float num = pm.getNum();
						// 先保存为获取id
						totalDao.save(pm);
						String[] pmIds = ids.split(",");
						if (pmIds != null && pmIds.length > 0) {
							for (String pmidStr : pmIds) {
								Integer pmid = Integer.parseInt(pmidStr);
								ProductManager relatePm = (ProductManager) totalDao
										.getObjectById(ProductManager.class,
												pmid);
								Float relateCount = 0f;
								// 获取该备货订单剩下的可冲销数量
								relateCount = relatePm.getNum()
										- relatePm.getCxCount()
										- relatePm.getCxApplyCount();
								if (relateCount.floatValue() > 0) {
									ProductZsAboutBh pcx = new ProductZsAboutBh();
									pcx.setZsProductId(pm.getId());// 正式产品Id
									pcx.setBhProductId(relatePm.getId());// 备货产品Id
									pcx.setMarkId(relatePm.getPieceNumber());// 零件
									pcx.setYwMarkId(relatePm.getYwMarkId());// 业务件号
									pcx.setStatus("未审批");
									if (relateCount >= num) {// 订单关联已足够冲销
										pcx.setAboutCount(num);// 相关数量
										if (relatePm.getCxApplyCount() == null) {
											relatePm.setCxApplyCount(0f);
										}
										relatePm.setCxApplyCount(relatePm
												.getCxApplyCount()
												+ num);
										if (pm.getCxApplyCount() == null) {
											pm.setCxApplyCount(0f);
										}
										pm.setCxApplyCount(pm.getCxApplyCount()
												+ num);
										num = 0f;
										totalDao.update(relatePm);
										totalDao.save(pcx);

									} else {
										pcx.setAboutCount(relateCount);// 相关数量
										if (relatePm.getCxApplyCount() == null) {
											relatePm.setCxApplyCount(0f);
										}
										relatePm.setCxApplyCount(relatePm
												.getCxApplyCount()
												+ relateCount);
										if (pm.getCxApplyCount() == null) {
											pm.setCxApplyCount(0f);
										}
										pm.setCxApplyCount(pm.getCxApplyCount()
												+ relateCount);
										num = num - relateCount;
										totalDao.update(relatePm);
										totalDao.save(pcx);

									}
									if (pm.getNum() == (pm.getCxApplyCount() + pm
											.getCxCount())) {
										pm.setStatus("全部冲销");
									}
									if (num == 0) {
										break;
									}
								}

							}
						}
					}
				}

				pmSet.add(pm);
				if (null == pmList.get(i).getUnitPrice()) {
					continue;// 配件
				}
				totalAmount += pmList.get(i).getUnitPrice();

				if (om.getOrderId() != null && om.getOrderId() > 0) {
					String hql = " from YcProduct where orderNo = (select orderNum from  OrderManager where id = ?) and markId = ?";
					YcProduct ycproduct = (YcProduct) totalDao
							.getObjectByCondition(hql, om.getId(), pm
									.getPieceNumber());
					if (ycproduct != null) {
						ycproduct.setCxNum(pm.getNum() - ycproduct.getNum());
						totalDao.update(ycproduct);
					}
				}

			}
			if (om.getTotalAmount() != null && om.getTotalAmount() > 0) {
				totalAmount += om.getTotalAmount();
			}
			om.setTotalAmount(totalAmount);
			om.setProducts(pmSet);
		}
		if (om.getOrderNum() == null || "".equals(om.getOrderNum())) {
			String hql1 = "select valueCode from CodeTranslation where type = 'sys' and keyCode=? and valueName='订单'";
			String valueCode = (String) totalDao.getObjectByCondition(hql1, om
					.getOrderType());
			String orderNum = valueCode + Util.getDateTime("yyyyMMdd");
			String hql_orderNum = "select max(orderNum) from  OrderManager where orderNum like '"
					+ orderNum + "%'";
			String orderNum1 = (String) totalDao
					.getObjectByCondition(hql_orderNum);
			if (orderNum1 == null || orderNum1.length() == 0) {
				orderNum1 = orderNum + "0001";
			} else {
				String strnum = orderNum1.substring(valueCode.length() + 8);
				Integer num = Integer.parseInt(strnum) + 1;
				orderNum1 = orderNum + String.format("%04d", num);
			}
			om.setOrderNum(orderNum1);
		}
		om.setOrderStatus("计划完善");// 添加后跳转到完善要货计划

		boolean bool = false;
		if (om.getId() > 0 && om.getProducts() != null) {
			bool = totalDao.update(om);
		} else {
			om.setEp_statuts("未申请");
			bool = totalDao.save(om);
		}
		if (bool && "预测".equals(om.getOrderType())) {// 如果是预测订单则根据bom生成预测外购件物料需求信息
			Users user = Util.getLoginUser();
			Set<ProductManager> productSet = om.getProducts();
			for (ProductManager product : productSet) {
				YcProduct ycProduct = new YcProduct();
				ycProduct.setNum(product.getNum());
				ycProduct.setOrderNo(om.getOrderNum());
				ycProduct.setOutOrderNo(om.getOutOrderNumber());
				ycProduct.setMarkId(product.getPieceNumber());
				ycProduct.setYwmarkId(product.getYwMarkId());
				ycProduct.setProductId(product.getId());
				ycProduct.setAddTime(Util.getDateTime());
				ycProduct.setProName(product.getName());
				ycProduct.setAddUserName(user.getName());
				ycProduct.setStatus("未分配");
				totalDao.save(ycProduct);
			}
		}
		// Integer epId = null;
		// try {
		// epId = CircuitRunServerImpl.createProcess(processName,
		// OrderManager.class, om.getId(), "ep_statuts", "id",
		// "orderManager_queryDetail.action?id=" + om.getId()
		// + "&status=shenpi&flag=dj", "新增 "
		// + om.getOrderType() + " 订单,订单编号:"
		// + om.getOutOrderNumber() + "/" + om.getOrderNum()
		// + ",请您审核!(要货计划请查看订单明细)" + mes_products, true);
		// if (epId != null && epId > 0) {
		// om.setEpId(epId);
		// CircuitRun circuitRun = (CircuitRun) totalDao.get(
		// CircuitRun.class, epId);
		// if ("同意".equals(circuitRun.getAllStatus())
		// && "审批完成".equals(circuitRun.getAuditStatus())) {
		// om.setEp_statuts("同意");
		// CircuitRunServerImpl.orderChongxiao(om.getId());
		// } else {
		// om.setEp_statuts("未审批");
		// }
		// bool = totalDao.update(om);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		return "true";
	}

	private Float getcgNum(ProcardTemplate sonProcardT, Float num) {
		Float cgNum = 0f;
		if ("外购".equals(sonProcardT.getProcardStyle())) {
			cgNum = sonProcardT.getQuanzi2();
		} else {
			Float corrCount = sonProcardT.getCorrCount();
			if (corrCount == null) {
				corrCount = 1f;
			}
			cgNum = corrCount * num;
		}
		if (sonProcardT.getFatherId() != null
				|| !"总成".equals(sonProcardT.getProcardStyle())) {
			ProcardTemplate fatherProcardT = (ProcardTemplate) totalDao.get(
					ProcardTemplate.class, sonProcardT.getFatherId());
			cgNum = getcgNum(fatherProcardT, cgNum);
		}
		return cgNum;
	}

	/**
	 * @Title: delOrderManagerById
	 * @Description: 根据ID删除
	 * @param id
	 * @return void
	 * @throws
	 */
	public void delOrderManagerById(int id) {
		OrderManager om = (OrderManager) totalDao.getObjectById(
				OrderManager.class, id);
		totalDao.delete(om);
	}

	/**
	 * @Title: del
	 * @Description: 删除对象
	 * @param om
	 * @return void
	 * @throws
	 */
	public void del(OrderManager om) {
		CircuitRunServerImpl.deleteCircuitRun(om.getEpId());
		om = (OrderManager) totalDao.getObjectById(OrderManager.class, om
				.getId());
		om.getCustome().getOrders().remove(om);
		Set<ProductManager> ptoductSet = om.getProducts();
		if (ptoductSet != null && ptoductSet.size() > 0) {
			for (ProductManager pm : ptoductSet) {
				List<ProductZsAboutBh> aboutbhList = totalDao
						.query("from ProductZsAboutBh where zsProductId=?", pm
								.getId());
				if (aboutbhList != null && aboutbhList.size() > 0) {
					for (ProductZsAboutBh aboutbh : aboutbhList) {
						ProductManager relatePm = (ProductManager) totalDao
								.getObjectById(ProductManager.class, aboutbh
										.getBhProductId());
						if (relatePm != null) {
							relatePm.setCxApplyCount(relatePm.getCxApplyCount()
									- aboutbh.getAboutCount());
							totalDao.update(relatePm);
						}
					}
				}
			}
		}
		totalDao.delete(om);

	}

	/**
	 * @Title: update
	 * @Description: 修改对象
	 * @param om
	 * @return void
	 * @throws
	 */
	public String update(OrderManager om, String status) {
		OrderManager orm = (OrderManager) totalDao.getObjectById(
				OrderManager.class, om.getId());
		if(om.getCustome()==null){
			return "客户信息丢失，请刷新后重试!";
		}
		if ("打回".equals(orm.getEp_statuts())) {
			if (CircuitRunServerImpl.updateCircuitRun(orm.getEpId())) {
				orm.setEp_statuts("未审批");
			}
		}
		if (orm.getInnerOrders() != null && orm.getInnerOrders().size() > 0) {
			if (orm.getPaymentDate() != null && om.getPaymentDate() != null
					&& !orm.getPaymentDate().equals(orm.getPaymentDate())) {
				return "该订单已产生内部计划，不允许修改交付日期!";
			}
		}
		if (orm.getPaymentDate2() == null) {
			orm.setPaymentDate2("");
		}
		if (orm.getPaymentDate2() == null) {
			orm.setPaymentDate2("");
		}
		if (!orm.getPaymentDate2().equals(om.getPaymentDate2())) {

		}
		if (!Util.isEquals(om.getOrderNum(), orm.getOrderNum())) {// 修改订单号
              	List<Procard> procardList = totalDao.query(
 					"from Procard where orderNumber=?", orm.getOrderNum());
			if (procardList != null && procardList.size() > 0) {
				for (Procard procard : procardList) {
					procard.setOrderNumber(om.getOrderNum());
					totalDao.update(procard);
				}
			}
			List<ManualOrderPlanDetail> manualOrderPlanDetailList = totalDao
					.query("from ManualOrderPlanDetail where orderNumber=?",
							orm.getOrderNum());
			if (manualOrderPlanDetailList != null
					&& manualOrderPlanDetailList.size() > 0) {
				for (ManualOrderPlanDetail manual : manualOrderPlanDetailList) {
					manual.setOrderNumber(om.getOrderNum());
					totalDao.update(manual);
				}
			}
			List<ProcessInforWWApply> ProcessInforWWApplyList = totalDao.query(
					"from ProcessInforWWApply where orderNumber=?", orm
							.getOrderNum());
			if (ProcessInforWWApplyList != null
					&& ProcessInforWWApplyList.size() > 0) {// 外委申请单
				for (ProcessInforWWApply processwwApply : ProcessInforWWApplyList) {
					processwwApply.setOrderNumber(om.getOrderNum());
					totalDao.update(processwwApply);
				}
			}
			List<GoodsStore> goodsStoreList = totalDao.query(
					"from GoodsStore where neiorderId=?", orm.getOrderNum());
			if (goodsStoreList != null && goodsStoreList.size() > 0) {// 外委申请单
				for (GoodsStore goodsStore : goodsStoreList) {
					goodsStore.setNeiorderId(om.getOrderNum());
					totalDao.update(goodsStore);
				}
			}
			List<Goods> goodsList = totalDao.query(
					"from Goods where neiorderId=?", orm.getOrderNum());
			if (goodsList != null && goodsList.size() > 0) {// 外委申请单
				for (Goods goods : goodsList) {
					goods.setNeiorderId(om.getOrderNum());
					totalDao.update(goods);
				}
			}
			List<Sell> sellList = totalDao.query("from Sell where orderNum=?",
					orm.getOrderNum());
			if (sellList != null && goodsList.size() > 0) {// 外委申请单
				for (Sell sell : sellList) {
					sell.setOrderNum(om.getOrderNum());
					totalDao.update(sell);
				}
			}

		}
		if (!"dahui".equals(status) && !"xieshang".equals(status)
				&& !"dingdan".equals(status)) {
			BeanUtils.copyProperties(om, orm, new String[] { "id",
					"totalAmount", "deliveryStatus", "backSection",
					"orderStatus", "contractDocuments", "products",
					"innerOrders", "completionrate", "timeRate", "hkrate",
					"kprate", "orderzhaobiaos", "innerOrderszhaobiao",
					"addTime", "epId", "ep_statuts", "producttype", "orderFil",
					"documentaryPeopleId", "clientName" });
		} else {
			if (om.getPaymentDate2() != null && om.getPaymentDate2() != "") {
				orm.setPaymentDate2(om.getPaymentDate2());// 修改协商交付日期
				if (orm.getYpaymentDate() == null
						|| orm.getYpaymentDate().equals("")) {
					orm.setYpaymentDate(orm.getPaymentDate());
				} else {
					orm.setYpaymentDate(orm.getYpaymentDate() + ","
							+ orm.getPaymentDate());
				}
				orm.setPaymentDate(om.getPaymentDate2());
				// 重新计算及时率
				Set<ProductManager> productSet = orm.getProducts();
				if (productSet != null && productSet.size() > 0) {
					for (ProductManager product : productSet) {
						Float sellCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(sellCount) from Sell where sellMarkId=? "
												+ "and sellLot in(select selfCard from Procard where markId=? and planOrderId "
												+ "in(select io.id from InternalOrder io join io.outerOrders oo where oo.id=?))",
										product.getPieceNumber(), product
												.getPieceNumber(), orm.getId());
						Float timeNum = (Float) totalDao
								.getObjectByCondition(
										"select sum(sellCount) from Sell where sellTime<='"
												+ om.getPaymentDate2()
												+ " 23:59:59' and sellMarkId=? "
												+ "and sellLot in(select selfCard from Procard where markId=? and planOrderId "
												+ "in(select io.id from InternalOrder io join io.outerOrders oo where oo.id=?))",
										product.getPieceNumber(), product
												.getPieceNumber(), orm.getId());
						if (sellCount != null) {
							product.setSellCount(sellCount);
						} else {
							product.setSellCount(0f);
						}
						if (timeNum != null) {
							product.setTimeNum(timeNum);
						} else {
							product.setTimeNum(0f);
						}
						totalDao.update(product);
					}
				}
				String hql5 = "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
				List list = totalDao.query(hql5, orm.getId());
				if (list != null && list.size() > 0 && list.get(0) != null) {
					String sellSql = "select sum(sellCount) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
					String timeRateSql = "select sum(timeNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
					String inpuSql = "select sum(allocationsNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
					Float inputNum = (Float) totalDao.getObjectByCondition(
							inpuSql, orm.getId());
					Float pronum = (Float) list.get(0);
					Float sellCount = (Float) totalDao.getObjectByCondition(
							sellSql, orm.getId());
					Float timeNum = (Float) totalDao.getObjectByCondition(
							timeRateSql, orm.getId());
					if (sellCount == null) {
						sellCount = 0f;
					}
					if (timeNum == null) {
						timeNum = 0f;
					}

					if (pronum == 0 || pronum == null) {
						orm.setCompletionrate(0F);
					} else {
						orm.setCompletionrate(sellCount / pronum * 100);
					}
					if (timeNum == 0 || timeNum == null) {
						orm.setTimeRate(0F);
					} else {
						orm.setTimeRate(timeNum / pronum * 100);
					}
					if (inputNum == 0 || inputNum == null) {
						orm.setInputRate(0F);
					} else {
						orm.setInputRate(inputNum / pronum * 100);
					}

				} else {
					orm.setCompletionrate(0F);
					orm.setTimeRate(0F);
					orm.setInputRate(0F);
				}

			}
			if (om.getContractDocuments() != null
					&& om.getContractDocuments() != "") {
				orm.setContractDocuments(om.getContractDocuments());
			}
			if (om.getOrderNum() != null && !"".equals(om.getOrderNum())) {
				if (!om.getOrderNum().equals(orm.getOrderNum())) {
					InternalOrder internalOrder = (InternalOrder) totalDao
							.getObjectByCondition(
									" from InternalOrder where orderIds =? ",
									orm.getId() + "");
					if (internalOrder != null) {
						internalOrder.setOrderNums(om.getOrderNum());
						totalDao.update(internalOrder);
					}
					// 修改Procard上的订单号
					List<Procard> procardList = totalDao
							.query(" from Procard where orderId =? ", orm
									.getId()
									+ "");
					if (procardList != null && procardList.size() > 0) {
						for (Procard procard : procardList) {
							procard.setOrderNumber(om.getOrderNum());
							totalDao.update(procard);
						}
					}
				}
				orm.setOrderNum(om.getOrderNum());
			}
			if (om.getOutOrderNumber() != null
					&& !"".equals(om.getOutOrderNumber())) {
				orm.setOutOrderNumber(om.getOutOrderNumber());
			}

			return "" + totalDao.update(orm);
		}
		List<ProductManager> pmList2 = new ArrayList<ProductManager>();
		Set<ProductManager> pmSet = orm.getProducts();
		List<ProductManager> pmList = om.getPmList();
		if (pmList != null && pmList.size() > 0) {
			for (ProductManager pm : pmList) {
				if (pm.getNum() <= 0)
					continue;
				for (ProductManager pm1 : pmSet) {
					if (pm.getPieceNumber().equals(pm1.getPieceNumber())) {
						pmList2.add(pm);
						pm1.setUnitPrice((pm.getUnitPrice() == null ? pm
								.getUnit()
								* pm.getNum() : pm.getUnitPrice())
								+ (pm1.getUnitPrice() == null ? pm1.getUnit()
										* pm1.getNum() : pm1.getUnitPrice()));
						pm1.setNum(pm.getNum() + pm1.getNum());
						totalDao.update(pm1);
					}
				}
			}
		}
		if (pmList2 != null && pmList2.size() > 0) {
			for (int j = 0; j < pmList2.size(); j++) {
				pmList.remove(pmList2.get(j));
			}

		}
		if (pmList != null && pmList.size() > 0) {
			for (ProductManager pm : pmList) {
				pm.setStatus("计划完善");
				if (pm.getNum() <= 0)
					continue;
				// 增加业务件号处理
				String productStyle = "批产";
				if (om.getProducttype() != null
						&& "试制".equals(om.getProducttype())) {
					productStyle = "试制";
				}
				ProcardTemplate pt = (ProcardTemplate) totalDao
						.getObjectByCondition(
								"from ProcardTemplate where (markId=? or ywMarkId=?) and productStyle = ? and procardStyle='总成' "
										+ " and (banbenStatus is null or banbenStatus = '默认')"
										+ " and (dataStatus is null or dataStatus<>'删除')  ",
								pm.getPieceNumber(), pm.getPieceNumber(),
								productStyle);
				if (pt != null) {
					pm.setPieceNumber(pt.getMarkId());// 件号;
					pm.setYwMarkId(pt.getYwMarkId());
				} else {
//					String ywMarkId = pm.getYwMarkId() == null ? "" : pm
//							.getYwMarkId();
//					Float tqcount = (Float) totalDao
//							.getObjectByCondition(
//									"select count(*) from ProcardTemplatePrivilege where markId=? or markId =?",
//									pm.getPieceNumber(), ywMarkId);
//					if (tqcount != null && tqcount > 0) {
//						pt = (ProcardTemplate) totalDao
//								.getObjectByCondition(
//										"from ProcardTemplate where (markId=? or ywMarkId=?) and productStyle = '试制' and procardStyle='总成' "
//												+ " and (banbenStatus is null or banbenStatus = '默认')"
//												+ " and (dataStatus is null or dataStatus<>'删除')  ",
//										pm.getPieceNumber(), pm
//												.getPieceNumber());
//						if (pt != null) {
//							pm.setPieceNumber(pt.getMarkId());// 件号;
//							pm.setYwMarkId(pt.getYwMarkId());
//						} else {
//							pm.setPieceNumber(pm.getPieceNumber());// 件号;
//							pm.setYwMarkId(pm.getPieceNumber());
//						}
//					} else {
						pm.setPieceNumber(pm.getPieceNumber());// 件号;
						pm.setYwMarkId(pm.getPieceNumber());
//					}
				}
				pm.setUnitPrice(pm.getUnitPrice() == null ? pm.getUnit()
						* pm.getNum() : pm.getUnitPrice());
				if (pm.getFmarkid() == null
						|| pm.getPieceNumber().equals(pm.getFmarkid())
						|| pm.getFmarkid().equals(pm.getYwMarkId())) {// 总成
					pm.setIsPeiJian("false");
				} else {
					pm.setIsPeiJian("true");
				}
				// 结束
				pmSet.add(pm);
			}
		}

		orm.setProducts(pmSet);
		Double b = 0d;
		for (ProductManager p1 : pmSet) {
			b += p1.getUnitPrice() == null ? p1.getUnit() * p1.getNum() : p1
					.getUnitPrice();
		}
		orm.setTotalAmount(b);
		return "" + totalDao.update(orm);
	}

	/**
	 * @Title: getOrderById
	 * @Description: 根据ID获取
	 * @param id
	 * @throws Exception
	 * @return OrderManager
	 */
	public OrderManager getOrderById(int id) {
		OrderManager om = (OrderManager) totalDao.getObjectById(
				OrderManager.class, id);
		if ("正式".equals(om.getOrderType())) {
			// 正式订单需要查询冲销数据
			List<InternalOrder> ioList = totalDao
					.query(
							"from InternalOrder where id in"
									+ "(select internalOrder.id from InternalOrderDetail where id in(select idetailId from InternalZsAboutBh where pzsAboutbhId in("
									+ "select id from ProductZsAboutBh where zsProductId in( select id from ProductManager where orderManager.id=?))))",
							id);
			if (ioList != null && ioList.size() > 0) {
				if (om.getInnerOrders() == null) {
					om.setInnerOrders(new HashSet<InternalOrder>());
				}
				for (InternalOrder io : ioList) {
					om.getInnerOrders().add(io);
				}
			}
		}
		return om;
	}

	/**
	 * @Title: getOrderById
	 * @Description: 根据OrderID获取订单成本动态
	 * @param id
	 * @throws Exception
	 * @return OrderManager
	 */
	@Override
	public Object[] getOrderByIdForCb(int id) {
		String hql = "select timeCB from OrderCBDT where oId=?";
		List list_t = totalDao.query(hql, id);
		String hql_n = "select addCB from OrderCBDT where oId=?";
		List list_n = totalDao.query(hql_n, id);
		String hql_a = "select sumCB from OrderCBDT where oId=?";
		List list_a = totalDao.query(hql_a, id);
		return new Object[] { list_t, list_n, list_a };
	}

	/***
	 * 根据订单查询对应生产批次
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public List findProcardByOrder(Integer orderId) {
		if (orderId != null) {
			String hql = "from Procard where planOrderId in (select io.id from InternalOrder io join io.outerOrders od where od.id=?) "
					+ "and markId in (select pieceNumber from ProductManager where orderManager.id=?) and sellCount>0";
			return totalDao.query(hql, orderId, orderId);
		}
		return null;
	}

	/**
	 * @Title: queryAllOrderManager
	 * @Description: 查询所有订单
	 * @param pageNo
	 * @param pageSize
	 * @throws Exception
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryAllOrderManager(int pageNo, int pageSize, Users users) {
		if (users != null) {
			String userName = users.getName();
			String hql = "from OrderManager o where 1 = 1 and o.orderStatus = 'NO'";
			if (userName != null) {
				hql += " and o.billingPeople = '" + userName + "'";
			}
			hql += " order by o.id desc";
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			return new Object[] { list, count };
		}
		return null;
	}

	/**
	 * @Title: queryOrderManagerByCondition
	 * @Description: 根据条件查询所有订单
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Object[] queryOrderManagerByCondition(Map map, int pageNo,
			int pageSize, String status, String tag) {
		List list = new ArrayList();
		String hql = "from OrderManager o join o.custome c where 1=1 ";
		if (map != null) {
			if (map.get("orderNum") != null) {
				hql += " and (o.orderNum like '%" + map.get("orderNum")
						+ "%' or o.outOrderNumber like '%"
						+ map.get("orderNum") + "%') ";
			}
			if (map.get("deliveryStatus") != null) {
				hql += " and o.deliveryStatus = '" + map.get("deliveryStatus")
						+ "'";
			}
			if (map.get("documentaryPeople") != null) {
				hql += " and o.documentaryPeople = '"
						+ map.get("documentaryPeople") + "'";
			}
			if (map.get("custome") != null) {
				hql += " and o.custome.id = '" + map.get("custome") + "'";
			}
			if (map.get("billingPeople") != null) {
				hql += " and o.billingPeople = '" + map.get("billingPeople")
						+ "'";
			}
			if (map.get("paymentDate") != null) {
				hql += " and o.paymentDate like '%" + map.get("paymentDate")
						+ "%'";
			}
			if (map.get("beginTime") != null && map.get("endTime") != null) {
				hql += " and (o.paymentDate between '" + map.get("beginTime")
						+ "' and '" + map.get("endTime") + "')";
			}
			if (map.get("orderType") != null) {
				hql += " and o.orderType = '" + map.get("orderType") + "'";
			}

			if (status != null && status.equals("inner")) {
				hql += " and o.conversionStatus = 'NO'";
			}
			if (map.get("markId") != null) {
				hql += " and o.id in (select orderManager.id from ProductManager where ywMarkId like '%"
						+ map.get("markId")
						+ "%' or pieceNumber like '%"
						+ map.get("markId") + "%')";
			}
		}
		if (status != null && status.equals("sz")) {
			hql += " and o.producttype = '试制' ";
		} else {
			hql += " and  COALESCE(o.producttype,'批产') = '批产'  ";
			//hql += " and (o.producttype is null or o.producttype='批产')";
		}
		if ("yc".equals(tag)) {
			hql += " and orderType = '预测' ";
		} else if ("sh".equals(tag)) {
			hql += " and orderType = '售后'";
		}
		hql += " order by o.id desc";
		String beforHql = "select o.id,o.orderNum,o.totalAmount,c.clientcompanyname,o.paymentDate,o.documentaryPeople,o.billingPeople,o.deliveryStatus,o.completionrate,o.hkrate,"
				+ "(select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= o.id),"
				+ "(select sum(sellCount) from ProductManager where (status is null or status!='取消') and orderManager.id= o.id),o.outOrderNumber,o.hkrate,o.kprate,o.timeRate,c.id,o.addTime,o.inputRate,o.ep_statuts,o.epId,o.dept,o.remark ";
		list = totalDao.findAllByPage(beforHql + hql, pageNo, pageSize);
		if (pageNo > 1 && list.size() <= 0) {
			pageNo = pageNo - 1;
			list = totalDao.findAllByPage(beforHql + hql, pageNo, pageSize);
		}
		List list_jindu = new ArrayList();
		List list_product = new ArrayList();
		if (list != null) {
			// 统计订单的工序完成进度
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				// 采购进度
				String hql_proccard_tj = "select avg(tjNumber/filnalCount)*100 from Procard where orderid=? and procardStyle='外购'";
				Float tjNum = (Float) totalDao.getObjectByCondition(
						hql_proccard_tj, obj[0]);

				Float jindu = 0F;
				// Object[] sums;
				try {
					if (tjNum > 0) {
						// 总工序数
						String hql_process_all = "select avg(totalCount/submmitCount) from ProcessInfor where  procard.id in "
								+ "(select id from Procard where planOrderId in "
								+ "(select io.id from InternalOrder io join io.outerOrders oo where oo.id=? ))";
						jindu = (Float) totalDao.getObjectByCondition(
								hql_process_all, obj[0]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//
				// if (sums[0] == null) {
				// sums[0] = 0;
				// }
				// if (sums[1] == null) {
				// sums[1] = 0;
				// }
				//
				// Float allprocessNum = Float.valueOf(sums[0].toString());
				// // 提交工序数
				// Float finalprocessNum = Float.valueOf(sums[1].toString());
				// Float jindu = finalprocessNum / allprocessNum * 100;
				// if (allprocessNum == 0) {
				// jindu = 0F;
				// }
				Object[] objnew = new Object[obj.length + 4];
				for (int j = 0, len = obj.length; j < len; j++) {
					objnew[j] = obj[j];
				}
				objnew[obj.length] = jindu * 100;// 生产进度23

				// 订单产品列表24 25
				String hql_pro = "from ProductManager where orderManager.id=?";
				List pro_list = totalDao.query(hql_pro, obj[0]);
				if (pro_list != null) {
					objnew[obj.length + 1] = pro_list.size() + 1;
					objnew[obj.length + 2] = pro_list;
				}
				objnew[obj.length + 3] = tjNum;
				list_jindu.add(objnew);
			}
		}
		int count = totalDao.getCount(hql);
		return new Object[] { list_jindu, count, list_product };
	}

	/**
	 * 更新订单完成率为空的订单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateCompletionrate() {
		String hql1 = "from OrderManager where inputRate is null or inputRate >100";
		List<OrderManager> list1 = totalDao.query(hql1);
		if (list1 != null && list1.size() > 0) {
			try {
				for (OrderManager o : list1) {
					Integer orderId = o.getId();
					String hql5 = "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
					List list = totalDao.query(hql5, orderId);
					if (list != null && list.size() > 0 && list.get(0) != null) {
						String inpuSql = "select sum(allocationsNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
						Float pronum = (Float) list.get(0);
						Float inputNum = (Float) totalDao.getObjectByCondition(
								inpuSql, orderId);
						if (inputNum == null) {
							inputNum = 0f;
						}

						if (pronum == 0 || pronum == null) {
							o.setInputRate(0F);
						} else {
							o.setInputRate(inputNum / pronum * 100);
						}
						if (o.getInputRate() > 0) {
							totalDao.update(o);
						}
					} else {
						o.setInputRate(0F);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String hql4 = "from OrderManager where completionrate is null or completionrate>100 ";
		// String hql4 = "from OrderManager where  paymentDate like '2015-12%'";
		List list4 = totalDao.query(hql4);
		if (list4 != null && list4.size() > 0) {
			try {
				for (int i = 0; i < list4.size(); i++) {
					OrderManager or = (OrderManager) list4.get(i);
					// Set<ProductManager> productSet = or.getProducts();
					// if(productSet!=null&&productSet.size()>0){
					// for(ProductManager product:productSet){
					// Float sellCount=(Float)
					// totalDao.getObjectByCondition("select sum(s.sellCount) from Sell s where s.sellCount>0 and s.sellLot is not null and s.sellLot!='' and s.sellMarkId=? "
					// +
					// "and s.sellLot+s.sellMarkId in(select selfCard+markId from Procard where planOrderId "
					// +
					// "in(select io.id from InternalOrder io join io.outerOrders oo where oo.id=?))",
					// product.getPieceNumber(),or.getId());
					// if(sellCount!=null){
					// if(sellCount>product.getNum()){
					// product.setSellCount(product.getNum());
					// }else{
					// product.setSellCount((int)(float)sellCount);
					// }
					// }
					// Float timeCount=(Float)
					// totalDao.getObjectByCondition("select sum(s.sellCount) from Sell s where s.sellCount>0 and s.sellLot is not null and s.sellLot!='' and s.sellMarkId=? "
					// +
					// "and s.sellLot+s.sellMarkId in(select selfCard+markId from Procard where planOrderId "
					// +
					// "in(select io.id from InternalOrder io join io.outerOrders oo where oo.id=? and (s.sellDate<=oo.paymentDate2 or s.sellDate <=oo.paymentDate)))",
					// product.getPieceNumber(),or.getId());
					// if(timeCount!=null){
					// if(timeCount>product.getNum()){
					// product.setTimeNum(product.getNum());
					// }else{
					// product.setTimeNum((int)(float)timeCount);
					// }
					// }
					// totalDao.update(product);
					// }
					// }
					// List<InternalOrder> ioList=
					// totalDao.query("from InternalOrder where id in(select io.id from InternalOrder io join io.outerOrders oo where oo.id=?)",
					// or.getId());
					// if(ioList.size()>0){
					// for(InternalOrder io:ioList){
					// Set<InternalOrderDetail>
					// iodSet=io.getInterOrderDetails();
					// if(iodSet!=null&&iodSet.size()>0){
					// for(InternalOrderDetail iod:iodSet){
					// Float sellCount=(Float)
					// totalDao.getObjectByCondition("select sum(s.sellCount) from Sell s where s.sellCount>0 and s.sellLot is not null and s.sellLot!='' and s.sellMarkId=?"
					// +
					// " and s.sellLot+s.sellMarkId in(select selfCard+markId from Procard where planOrderId=?)",iod.getPieceNumber(),
					// iod.getInternalOrder().getId());
					// if(sellCount==null){
					// sellCount=0f;
					// }
					// if(sellCount>iod.getNum()){
					// iod.setSellCount(iod.getNum());
					// }else{
					// iod.setSellCount((int)(float)sellCount);
					// }
					// totalDao.update(iod);
					// }
					// }
					// }
					// }
					// 计算订单交付率
					Integer orderId = or.getId();
					String hql5 = "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
					List list = totalDao.query(hql5, orderId);
					if (list != null && list.size() > 0 && list.get(0) != null) {
						String hql6 = "select sum(sellCount) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
						String hql7 = "select sum(timeNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
						Float pronum = (Float) list.get(0);
						Float proallocationsNum = (Float) totalDao
								.getObjectByCondition(hql6, orderId);
						Float timeNum = (Float) totalDao.getObjectByCondition(
								hql7, orderId);
						if (proallocationsNum == null) {
							proallocationsNum = 0f;
						}
						if (timeNum == null) {
							timeNum = 0f;
						}
						if (pronum == null || pronum <= 0) {
							or.setCompletionrate(0F);
						} else {
							or.setCompletionrate(proallocationsNum / pronum
									* 100);
							or.setTimeRate(timeNum / pronum * 100);
						}
						if (pronum == 0 || pronum == null) {
							or.setCompletionrate(0F);
							or.setTimeRate(0F);
						} else {
							or.setCompletionrate(proallocationsNum / pronum
									* 100);
							or.setTimeRate(timeNum / pronum * 100);
						}

					} else {
						or.setCompletionrate(0F);
					}
					if (or.getCompletionrate() > 0) {
						totalDao.update(or);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/****
	 * 获得当前月和上个月的订单信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findOrderFormMonth() {
		String hql = "select o.id,o.orderNum,o.totalAmount,c.clientcompanyname,o.paymentDate,o.documentaryPeople,o.billingPeople,o.backSection,"
				+ "(select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= o.id),"
				+ "(select sum(allocationsNum) from ProductManager where (status is null or status!='取消') and orderManager.id= o.id) ";
		String brforMonth = Util.getLastMonth("yyyy-MM");
		String nowMonth = Util.getDateTime("yyyy-MM");
		hql += "from OrderManager o join o.custome c where (o.paymentDate like '%"
				+ brforMonth + "%' or o.paymentDate like '%" + nowMonth + "%')";
		return totalDao.query(hql);
	}

	/****
	 * 获得当前月和上个月的订单信息的id集合
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findOrderIdsFormMonth() {
		String brforMonth = Util.getLastMonth("yyyy-MM");
		String nowMonth = Util.getDateTime("yyyy-MM");
		String hql = "select o.id from OrderManager o join o.custome c where (o.paymentDate like '%"
				+ brforMonth + "%' or o.paymentDate like '%" + nowMonth + "%')";
		return totalDao.query(hql);
	}

	/***
	 * 根据月份查询该月的订单完成率
	 * 
	 * @param month
	 *            月份
	 * @return
	 */
	@Override
	public Float getCompletionrateByMonth(String month) {
		String hql = "select sum(o.completionrate)/count(*) from OrderManager o  where o.paymentDate like '%"
				+ month + "%'";
		Object obj = totalDao.getObjectByCondition(hql);
		if (obj == null)
			return 0F;
		else
			return Float.parseFloat(obj.toString());
	}

	/****
	 * 订单一条龙纠错
	 */
	@Override
	public void updateOrder(OrderManager om) {
		// 处理订单进度
		String ohql = "from OrderManager where paymentDate<'2014-08-01'";
		// 所有订单
		List<OrderManager> olist = totalDao.query(ohql);
		for (OrderManager orderManager : olist) {
			Integer orderManagerId = orderManager.getId();// 订单id
			// 内部计划
			Set<InternalOrder> ioSet = orderManager.getInnerOrders();
			for (InternalOrder internalOrder : ioSet) {
				Integer internalOrderId = internalOrder.getId();// 内部计划id
				// 产品
				Set<InternalOrderDetail> iodSet = internalOrder
						.getInterOrderDetails();
				for (InternalOrderDetail internalOrderDetail : iodSet) {
					String markId = internalOrderDetail.getPieceNumber();// 件号
					// 流水单
					String phql = "from Procard where markId=? and planOrderId=?";
					List<Procard> pList = totalDao.query(phql, markId,
							internalOrderId);
					for (Procard procard : pList) {
						// 激活并完成所有进度
						String uHql = "update Procard set jihuoStatua='激活',klNumber=filnalCount,tjNumber=klNumber,minNumber=klNumber,status='完成' where rootId = ?";
						totalDao.createQueryUpdate(uHql, null, procard.getId());
						totalDao.createQueryUpdate(uHql, null, procard.getId());
						procard.setStatus("入库");
						totalDao.update(procard);
						// 处理流水卡为初始
						String rHql = "from RunningWaterCard where procardId=?";
						RunningWaterCard runningWaterCard = (RunningWaterCard) totalDao
								.getObjectByCondition(rHql, procard.getId());
						if (runningWaterCard != null) {
							runningWaterCard.setCardStatus("初始");
							runningWaterCard.setReceiveStatus("初始");
							runningWaterCard.setFollowCardId(null);
							runningWaterCard.setProcardId(null);
							totalDao.update(runningWaterCard);
						}
					}
					internalOrderDetail
							.setQuantityCompletion(internalOrderDetail.getNum());
					totalDao.update(internalOrderDetail);
				}

			}
			// 计划以及产品完成
			orderManager.setDeliveryStatus("是");
			// 更新所有产品为完成
			String pHql = "update ProductManager set allocationsNum=num where orderManager.id=?";
			totalDao.createQueryUpdate(pHql, null, orderManagerId);
			totalDao.update(orderManager);
		}
	}

	/**
	 * @Title: queryOrderByClientById
	 * @Description: 根据客户 ID查询订单
	 * @param id
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryOrderByClientById(int id, int pageNo, int pageSize) {
		List list = new ArrayList();
		if (id != 0) {
			String hql = "from OrderManager o where o.custome.id = " + id;
			list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			return new Object[] { list, count };
		}
		return null;
	}

	/**
	 * @Title: updateOr
	 * @Description: 修改对象
	 * @return boolean
	 * @throws
	 */
	public boolean updateOr(OrderManager om) {
		Boolean bol = totalDao.update(om);
		return bol;
	}

	public Object[] queryNotConversionOrder(OrderManager om, String beginTime,
			String endTime, Integer customeIdm, int pageNo, int pageSize,
			String tag) {
		String hql = "from OrderManager  where id in"
				+ "(select orderManager.id from ProductManager"
				+ " where (orderType ='备货' and (hasTurn is  null or num>hasTurn) and num>0 ) "
				+ " or (orderType in('正式','售后') and num>0 and ((hasTurn is  null and num>cxCount) or (hasTurn is not null and num>(cxCount+hasTurn))))"
				+ " group by orderManager) "
				+ "and conversionStatus = 'NO' and ep_statuts='同意'";
		if (om != null) {
			if (om.getOrderNum() != null && !"".equals(om.getOrderNum())) {
				hql += " and  orderNum like '%" + om.getOrderNum() + "%'";
			}
			if (om.getDeliveryStatus() != null
					&& !"".equals(om.getDeliveryStatus())) {
				hql += " and  deliveryStatus like '%" + om.getDeliveryStatus()
						+ "%'";
			}
			if (om.getDocumentaryPeople() != null
					&& !"".equals(om.getDocumentaryPeople())) {
				hql += " and  documentaryPeople like '%"
						+ om.getDocumentaryPeople() + "%'";
			}
			if (om.getBillingPeople() != null
					&& !"".equals(om.getBillingPeople())) {
				hql += " and  billingPeople like '%" + om.getBillingPeople()
						+ "%'";
			}
			if (!"".equals(beginTime) && !"".equals(endTime)) {
				hql += " and paymentDate between '" + beginTime + "' and '"
						+ endTime + "'";
			}
			if (customeIdm != null && customeIdm != 0) {
				hql += " and custome.id =" + customeIdm;
			}
		}
		if (tag != null && "sz".equals(tag)) {
			hql += " and  producttype = '试制'";
		} else {
			hql += " and (producttype is null or producttype='批产')";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	/***
	 * 通过订单id查询对应内部计划信息
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	@Override
	public List findInternalOrder(Integer orderId) {
		if (orderId != null && orderId > 0) {
			String hql = "from InternalOrder where id in (select i.id from InternalOrder i join i.outerOrders  where i.id =?)";
			return totalDao.query(hql);
		}
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String checkOrderNumber(Integer id, String orderNum,
			String outOrderNum) {
		// TODO Auto-generated method stub
		String msg = "true";
		String sql = "from OrderManager o join o.products p where o.orderNum=?"
				+ " and  p.status <> '取消'";
		if (id != null) {
			sql += " and o.id!=" + id;
		}
		List<OrderManager> list = totalDao.query(sql, orderNum);
		if (list.size() > 0) {
			msg = "内部订单号已存在!";
		}
		// if (outOrderNum != null && outOrderNum.length() > 0) {
		// String sql2 = "from OrderManager where outOrderNumber=?";
		// if (id != null) {
		// sql2 += " and id!=" + id;
		// }
		// List<OrderManager> list2 = totalDao.query(sql2, outOrderNum);
		// if (list2.size() > 0) {
		// if (msg.equals("true")) {
		// msg = "外部订单号已存在!";
		// } else {
		// msg += ",外部订单号已存在!";
		// }
		// }
		// } else {
		// // msg = "外部订单号不能为空!";
		// }
		return msg;
	}

	@Override
	public List<TaHkHuikuan> gettaHkHuikuanList(String orderNum) {
		// TODO Auto-generated method stub
		return (List<TaHkHuikuan>) totalDao
				.query(
						"from TaHkHuikuan where hkStatus in ('未核对','可开票','回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId=? )",
						orderNum);
	}

	@Override
	public String getOutNumerByNumber(String orderNum) {
		// TODO Auto-generated method stub
		return (String) totalDao
				.getObjectByCondition(
						"select outOrderNumber from OrderManager where orderNum=? and id not in (select orderManager.id from ProductManager where status ='取消')",
						orderNum);
	}

	/***
	 * 计算订单成本
	 * 
	 * @param orderId
	 *            订单id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void orderChengben(Integer orderId) {
		if (orderId != null) {
			OrderManager order = (OrderManager) totalDao.getObjectById(
					OrderManager.class, orderId);
			if (order != null) {
				String hql_procard = "from Procard where planOrderId in (select i.id from InternalOrder i join i.outerOrders o where o.id=? ) "
						+ "and markId in (select pieceNumber from ProductManager where  orderManager.id=?)  "
						+ "and procardStyle='总成' and allMoney is null";
				// and allMoney is null
				List list_procard = totalDao.query(hql_procard, order.getId(),
						order.getId());
				if (list_procard != null && list_procard.size() > 0) {
					Float rengongfei_all = 0F;// 人工成本
					Float jbfei_all = 0f;// 加班费
					Float shebeiZjFei_all = 0F;// 设备折旧
					Float nyxhFei_all = 0F;// 设备能耗
					Float clFei_all = 0F;// 材料费
					Float WgFei_all = 0F;// 外购价格
					Float flFei_all = 0F;// 辅料费
					Float glFei_all = 0F;// 管理费
					Float clvFei_all = 0F;// 差旅费
					Float canFei_all = 0F;// 餐费
					Float allMoney = 0F;// 总费用

					Map<String, String> nameMaps = new HashMap<String, String>();// 统计所有的领取人员

					for (int i = 0; i < list_procard.size(); i++) {
						Procard procard = (Procard) list_procard.get(i);
						// 累计餐旅费
						if (procard.getClvFei() != null) {
							clvFei_all += procard.getClvFei();
						}
						if (procard.getJbfei() != null) {
							jbfei_all += procard.getJbfei();
						}

						String ywMarkId = (String) totalDao
								.getObjectByCondition(
										"select ywMarkId from ProcardTemplate where procardStyle='总成' and markId=?"
												+ " and (banbenStatus is null or banbenStatus = '默认')"
												+ " and (dataStatus is null or dataStatus<>'删除')  ",
										procard.getMarkId());
						String addSql = null;
						if (ywMarkId != null && ywMarkId.length() > 0) {
							addSql = " (pieceNumber=? or pieceNumber='"
									+ ywMarkId + "')";
						} else {
							addSql = " pieceNumber=?";
						}
						// select allcount from com.task.entity.Product_Internal
						// where productId =(select id from
						// com.task.entity.ProductManager where (pieceNumber=?
						// or pieceNumber='W01073021Z') and orderManager.id=?)
						// and ioDetailId in (select id from
						// com.task.entity.InternalOrderDetail where
						// pieceNumber=?, internalOrder.id =(select planOrderId
						// from com.task.entity.sop.Procard where markId=? and
						// selfCard=? and productStyle='批产') )]
						Integer zyCount = (Integer) totalDao
								.getObjectByCondition(
										"select allcount from Product_Internal where productId =(select id from ProductManager where "
												+ addSql
												+ " and orderManager.id=?) "
												+ "and ioDetailId in (select id from InternalOrderDetail where pieceNumber=? and internalOrder.id =(select planOrderId from Procard where markId=? and selfCard=? and productStyle='批产') )",
										procard.getMarkId(), order.getId(),
										procard.getMarkId(), procard
												.getMarkId(), procard
												.getSelfCard());
						Integer allCount = (Integer) totalDao
								.getObjectByCondition(
										"select num from InternalOrderDetail where pieceNumber=? and internalOrder.id =(select planOrderId from Procard where markId=? and selfCard=? and productStyle='批产')",
										procard.getMarkId(), procard
												.getMarkId(), procard
												.getSelfCard());
						Float rate = 1f;
						if (zyCount != null && allCount > 0) {
							if (allCount != null && allCount > 0) {
								rate = zyCount / allCount * 1.0F;
							}
						}
						// 查询该总成下所有的产品
						String hql_sonProcard = "from Procard where rootId=? and status in ('完成','待入库','入库')";
						List<Procard> list_sonProcard = (List<Procard>) totalDao
								.query(hql_sonProcard, procard.getId());
						Float procard_allMoney = 0F;// 总成总费用
						Float wgFei = 0F;// 外购费
						for (Procard procard2 : list_sonProcard) {
							/**
							 * 查看此订单下对应此procard的数量计算出所占的比例
							 */

							/**
							 * 遍历一个组件所需要的工序用于计算 人工成本、设备折旧、设备能耗
							 */
							Float rengongfei = 0F;// 人工成本
							Float shebeiZjFei = 0F;// 设备折旧
							Float nyxhFei = 0F;// 设备能耗
							Set<ProcessInfor> processset = procard2
									.getProcessInforSet(); // 此零件需要的工序
							for (ProcessInfor processInfor : processset) {
								if (!"自制"
										.equals(processInfor.getProductStyle())
										|| processInfor.getNowAllJiepai() == null) {
									continue;
								}
								// 遍历所有的工序领取记录,统计所有的领取人员
								String hql_prolog = "from ProcessInforReceiveLog where fk_processInforId=? and fk_pirLId is null and status='提交'";
								List<ProcessInforReceiveLog> list = totalDao
										.query(hql_prolog, processInfor.getId());
								Map<String, String> nameMap = new HashMap<String, String>();
								for (ProcessInforReceiveLog pirl : list) {
									String userCodes = pirl.getUsercodes();
									String[] codes = userCodes.split(",");
									for (String code : codes) {
										String userCode = nameMap.get(code);
										if (userCode == null) {
											nameMap.put(code, code);
										}
										String userCode_all = nameMaps
												.get(code);
										if (userCode_all == null) {
											nameMaps.put(code, code);
										}
									}
								}
								Double workingHoursWages = 0.0; // 工序工时工资
								for (String jobNum : nameMap.keySet()) { // 统计工序中基本工时工资
									WageStandard wageStandard = wss
											.findWSByUser(jobNum); // 根据工号查询工资模板
									if (wageStandard == null) {
										continue;
									}
									InsuranceGold insuranceGold = igs
											.findInsuranceGoldBylc(
													wageStandard
															.getLocalOrField(),
													wageStandard
															.getCityOrCountryside(),
													wageStandard
															.getPersonClass()); // 福利系数（计算公司缴纳的保险成本）
									// 单工序总成本（当月个人人力成本）
									workingHoursWages += (wageStandard
											.getGangweigongzi()
											+ wageStandard.getSsBase()
											* (insuranceGold
													.getQYoldageInsurance()
													+ insuranceGold
															.getQYmedicalInsurance()
													+ insuranceGold
															.getQYunemploymentInsurance()
													+ insuranceGold
															.getQYinjuryInsurance() + insuranceGold
													.getQYmaternityInsurance())
											/ 100 + wageStandard.getGjjBase()
											* insuranceGold.getQYhousingFund()
											/ 100);
								}

								Double basicWorkingHoursWages = workingHoursWages
										/ nameMap.size() / SECONDS; // 工序中基本工时工资(秒工资=单工序总成本/21.5天)
								Double processWages = basicWorkingHoursWages
										* processInfor.getNowAllJiepai();// 单个工序的人工成本
								processInfor.setRengongfei(processWages
										.floatValue());// 人工费

								rengongfei += processWages.floatValue();// 累计人工成本
								rengongfei_all += (processWages.floatValue() * rate);
								procard_allMoney += processWages.floatValue();

								if (processInfor.getShebeiNo() != null
										&& processInfor.getShebeiNo().length() > 0) {
									/************ 计算设备折旧费 ***/
									// 查询设备价格
									String hql_Machine = "from Machine where no=?";
									Machine machine = (Machine) totalDao
											.getObjectByCondition(hql_Machine,
													processInfor.getShebeiNo());
									if (machine != null) {
										// 先计算设备净值
										SimpleDateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd 00:00:00");
										String buytime = machine.getBuytime();
										if (buytime != null
												&& buytime.length() > 0
												&& buytime != "") {
											String[] str = buytime.split("-");
											String buy = str[0];
											Integer buy1 = Integer
													.parseInt(buy)
													+ machine
															.getDepreciationYear();
											String buy2 = buy1.toString();
											String buytime1 = buy2 + "-"
													+ str[1] + "-" + str[2];
											machine.setEndtime(buytime1);
											Float Buyamount = (Float) machine
													.getBuyamount();// 购买金额
											Timestamp timestamp = Timestamp
													.valueOf(df
															.format(new java.util.Date()));// 取出系统当前时间
											System.out.println(timestamp
													.toString());
											Long date = Util.StringToDate(
													timestamp.toString(),
													"yyyy-MM-dd").getTime();// 当前日期
											Long buydate = Util.StringToDate(
													buytime, "yyyy-MM-dd")
													.getTime();// 购买日期
											Integer depreciationYear = machine
													.getDepreciationYear();// 折旧年限
											Long nYear = 1000 * 60 * 60 * 24
													* 365L;// 转化为年
											Long newYear = date - buydate;// 已用折旧时间
											float year1 = newYear / nYear;// 转化为年
											float oldYear = depreciationYear
													- year1;// 剩余折旧时间(转化为年)
											float equipmentworth = (oldYear / depreciationYear)
													* Buyamount;
											DecimalFormat myFormat = new DecimalFormat(
													"0.00");// 设置float类型的小数只能为两位
											String strFloat = myFormat
													.format(equipmentworth);
											machine.setEquipmentworth(strFloat);
											// 计算本次折旧费用
											Float shebeiZhejiu_1 = processInfor
													.getOpshebeijiepai()
													* processInfor
															.getSubmmitCount()
													* equipmentworth
													/ (3600 * 8 * 12 * 22.5f * machine
															.getDepreciationYear());
											if (shebeiZhejiu_1 < 0) {
												shebeiZhejiu_1 = 0f;
											}
											processInfor
													.setShebeiZjFei(shebeiZhejiu_1);

											shebeiZjFei += shebeiZhejiu_1;// 累计设备折旧费
											shebeiZjFei_all += (shebeiZhejiu_1 * rate);
											procard_allMoney += shebeiZhejiu_1;
										}
									}
									/************ 计算设备折旧费结束 ***/

									/************ 计算设备能耗 ***/
									Float allnonehao = 0F;
									if (processInfor.getNowAllNenghao() != null) {
										allnonehao += processInfor
												.getNowAllNenghao();
									}
									if (processInfor.getDaiNeghao() != null) {
										allnonehao += processInfor
												.getDaiNeghao();
									}
									Float sbnh = allnonehao * 1;// 电价按照1元计费
									processInfor.setNyxhFei(sbnh);
									nyxhFei += sbnh;// 累计设备能耗费用
									nyxhFei_all += (sbnh * rate);
									procard_allMoney += sbnh;
								}
							}

							procard2.setRengongfei(rengongfei);
							procard2.setShebeiZjFei(shebeiZjFei);
							procard2.setNyxhFei(nyxhFei);
							// 初始值设置
							procard2.setClFei(0F);
							procard2.setWgFei(0F);
							procard2.setFlFei(0F);

							procard2.setGlFei(0F);
							procard2.setClvFei(0F);
							procard2.setCanFei(0F);

							// if ("自制".equals(procard2.getProcardStyle())
							// || "是".equals(procard2.getDanjiaojian())
							// || ("组合".equals(procard2.getProcardStyle()) &&
							// procard2
							// .isZhHasYcl())) {
							// /************** 计算材料费 ******************/
							// // String hql_Zhgongxu =
							// //
							// "select jiage from Zhgongxu where paihao=? and guige=? and danwei=?";
							// String hql_Zhgongxu =
							// "select jiage from Zhgongxu where paihao=? and guige=? ";
							// Float jiage = (Float) totalDao
							// .getObjectByCondition(hql_Zhgongxu,
							// procard2.getTrademark(),
							// procard2.getSpecification());
							// if (jiage != null) {
							// Float clFei = procard2.getFilnalCount()
							// * procard2.getQuanzi2()
							// / procard2.getQuanzi1() * jiage;
							// procard2.setClFei(clFei);
							// clFei_all += (clFei * rate);
							// procard_allMoney += clFei;
							// }
							// } else
							if ("外购".equals(procard2.getProcardStyle())) {
								String nowDate = Util.getDateTime("YYYY-MM-dd");
								// String hql_price =
								// "select bhsPrice from Price where partNumber =? and pricePeriodStart<=? and pricePeriodEnd>=?";
								// Double bhsPrice = (Double) totalDao
								// .getObjectByCondition(hql_price,
								// procard2.getMarkId(), nowDate,
								// nowDate);
								String hql_price = "select bhsPrice from Price where partNumber =? order by  pricePeriodEnd desc";
								Double bhsPrice = (Double) totalDao
										.getObjectByCondition(hql_price,
												procard2.getMarkId());
								if (bhsPrice != null) {
									Float wgFei_1 = bhsPrice.floatValue()
											* procard2.getFilnalCount();
									procard2.setWgFei(wgFei_1);
									wgFei += wgFei_1;
									WgFei_all += (wgFei_1 * rate);
									procard_allMoney += wgFei_1;
								}
							}
							totalDao.update(procard2);
						}
						// if ("完成".equals(procard.getStatus())
						// || "待入库".equals(procard.getStatus())
						// || "入库".equals(procard.getStatus())) {
						// // procard.setWgFei(0F);
						procard.setAllMoney(procard_allMoney);
						// } else
						// procard.setAllMoney(null);

						totalDao.update(procard);
						allMoney += (procard_allMoney * rate);

					}
					// 统计订单成本
					// Float rengongfei_all = 0F;// 人工成本
					// Float shebeiZjFei_all = 0F;// 设备折旧
					// Float nyxhFei_all = 0F;// 设备能耗
					// Float clFei_all = 0F;// 材料费
					// Float WgFei_all = 0F;// 外购价格
					// Float flFei_all = 0F;// 辅料费
					// Float glFei_all = 0F;// 管理费
					// Float clvFei_all = 0F;// 差旅费
					// Float canFei_all = 0F;// 餐费
					// Float allMoney = 0F;// 总费用
					/******* 用总的人工和工人总工资对比 *********/
					Float workingHoursWages_all = 0F;
					for (String jobNum : nameMaps.keySet()) { // 统计工序中基本工时工资
						WageStandard wageStandard = wss.findWSByUser(jobNum); // 根据工号查询工资模板
						if (wageStandard == null) {
							continue;
						}
						InsuranceGold insuranceGold = igs
								.findInsuranceGoldBylc(wageStandard
										.getLocalOrField(), wageStandard
										.getCityOrCountryside(), wageStandard
										.getPersonClass()); // 福利系数（计算公司缴纳的保险成本）
						// 单工序总成本（当月个人人力成本）
						workingHoursWages_all += (wageStandard
								.getGangweigongzi()
								+ wageStandard.getSsBase()
								* (insuranceGold.getQYoldageInsurance()
										+ insuranceGold.getQYmedicalInsurance()
										+ insuranceGold
												.getQYunemploymentInsurance()
										+ insuranceGold.getQYinjuryInsurance() + insuranceGold
										.getQYmaternityInsurance()) / 100 + wageStandard
								.getGjjBase()
								* insuranceGold.getQYhousingFund() / 100);
					}
					if (rengongfei_all > workingHoursWages_all)
						rengongfei_all = workingHoursWages_all;

					order.setRengongfei(rengongfei_all + jbfei_all);
					order.setShebeiZjFei(shebeiZjFei_all);
					order.setNyxhFei(nyxhFei_all);
					order.setClFei(clFei_all);
					order.setWgFei(WgFei_all);
					order.setFlFei(flFei_all);
					order.setGlFei(glFei_all);
					order.setClvFei(clvFei_all);
					order.setCanFei(canFei_all);
					order.setAllMoney(allMoney);
					totalDao.update(order);
				}
			}
		}
	}

	/****
	 * 查询该订单下的对应产品信息
	 * 
	 * @param orderId
	 * @return
	 */
	@Override
	public List findProcardByOrderId(Integer orderId, String flag) {
		String hql_procard = "from Procard where planOrderId in (select i.id from InternalOrder i join i.outerOrders o where o.id=? ) "
				+ "and markId in (select pieceNumber from ProductManager where orderManager.id=?) and procardStyle='总成' ";
		List<Procard> list = totalDao.query(hql_procard, orderId, orderId);
		String cb = "";
		if (flag.equals("rg")) {
			cb = "rengongfei";
		} else if (flag.equals("sb")) {
			cb = "shebeiZjFei";
		} else if (flag.equals("ny")) {
			cb = "nyxhFei";
		} else if (flag.equals("cl")) {
			cb = "clFei";
		} else if (flag.equals("wg")) {
			cb = "wgFei";
		} else if (flag.equals("fl")) {
			cb = "flFei";
		} else if (flag.equals("gl")) {
			cb = "glFei";
		} else if (flag.equals("clv")) {
			cb = "clvFei";
		} else if (flag.equals("cf")) {
			cb = "canFei";
		}

		for (Procard procard : list) {
			String hql_sumrg = "select sum(" + cb
					+ ") from Procard where rootId=?";
			Float sumRgMonery = (Float) totalDao.getObjectByCondition(
					hql_sumrg, procard.getId());
			if (sumRgMonery != null) {
				sumRgMonery = Float.parseFloat(String.format("%.2f",
						sumRgMonery));
			} else {
				sumRgMonery = 0F;
			}
			if (flag.equals("rg")) {
				procard.setRengongfei(sumRgMonery);
			} else if (flag.equals("sb")) {
				procard.setShebeiZjFei(sumRgMonery);
			} else if (flag.equals("ny")) {
				procard.setNyxhFei(sumRgMonery);
			} else if (flag.equals("cl")) {
				procard.setClFei(sumRgMonery);
			} else if (flag.equals("wg")) {
				procard.setWgFei(sumRgMonery);
			} else if (flag.equals("fl")) {
				procard.setFlFei(sumRgMonery);
			} else if (flag.equals("gl")) {
				procard.setGlFei(sumRgMonery);
			} else if (flag.equals("clv")) {
				procard.setClvFei(sumRgMonery);
			} else if (flag.equals("cf")) {
				procard.setCanFei(sumRgMonery);
			}
		}
		return list;
	}

	@Override
	public List<OrderManager> findProcardByRootId(int id) {
		return totalDao.query("from Procard where rootId=? ", id);
	}

	public WageStandardServer getWss() {
		return wss;
	}

	public void setWss(WageStandardServer wss) {
		this.wss = wss;
	}

	public InsuranceGoldServer getIgs() {
		return igs;
	}

	public void setIgs(InsuranceGoldServer igs) {
		this.igs = igs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String removeProduct(int id) {
		// TODO Auto-generated method stub
		ProductManager product = (ProductManager) totalDao.getObjectById(
				ProductManager.class, id);
		if (product != null) {
			product.setRemoveDate(Util.getDateTime());
			product.setStatus("取消");
			totalDao.update(product);
			// 更新内部计划上的状态为取消
			String hql_io = "from InternalOrder where id in (select internalOrder.id from InternalOrderDetail where productManagerId=?) ";
			InternalOrder ilo = (InternalOrder) totalDao.getObjectByCondition(
					hql_io, product.getId());
			if (ilo != null) {
				String hql_iod = "from ProductManager where id in (select productManagerId from InternalOrderDetail where internalOrder.id=?)"
						+ " and status<>'取消' and id<>?";
				Integer count = totalDao.getCount(hql_iod, ilo.getId(), product
						.getId());
				if (count == 0) {
					ilo.setStatus("取消");
					ilo.setRemoveDate(Util.getDateTime());
					totalDao.update(ilo);
				}
			}
			// 重新计算订单的各个率
			OrderManager orm = product.getOrderManager();
			String hql5 = "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
			List list = totalDao.query(hql5, orm.getId());
			if (list != null && list.size() > 0 && list.get(0) != null) {
				String sellSql = "select sum(sellCount) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
				String timeRateSql = "select sum(timeNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
				String inpuSql = "select sum(allocationsNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
				Float inputNum = (Float) totalDao.getObjectByCondition(inpuSql,
						orm.getId());
				Float pronum = (Float) list.get(0);
				Float sellCount = (Float) totalDao.getObjectByCondition(
						sellSql, orm.getId());
				Float timeNum = (Float) totalDao.getObjectByCondition(
						timeRateSql, orm.getId());
				if (sellCount == null) {
					sellCount = 0f;
				}
				if (timeNum == null) {
					timeNum = 0f;
				}

				if (pronum == 0 || pronum == null) {
					orm.setCompletionrate(0F);
				} else {
					orm.setCompletionrate(sellCount / pronum * 100);
				}
				if (timeNum == 0 || timeNum == null) {
					orm.setTimeRate(0F);
				} else {
					orm.setTimeRate(timeNum / pronum * 100);
				}
				if (inputNum == 0 || inputNum == null) {
					orm.setInputRate(0F);
				} else {
					orm.setInputRate(inputNum / pronum * 100);
				}

			} else {
				orm.setCompletionrate(0F);
				orm.setTimeRate(0F);
				orm.setInputRate(0F);
			}
			totalDao.update(orm);
			List<Procard> procardList = totalDao
					.query(
							"from Procard where planOrderDetailId in (select id from InternalOrderDetail where productManagerId=?) and status <> '取消'",
							product.getId());
			if (procardList != null && procardList.size() > 0) {
				for (Procard pd : procardList) {
					if(!pd.getStatus().equals("设变锁定")){
						pd.setOldStatus(pd.getStatus());
					}
					pd.setStatus("取消");
					totalDao.update(pd);
				}
			}
			return "true";
		}
		return "没有找到目标";
	}

	@Override
	public String shuaxin(int productId, int priceId) {
		String msg = "";
		if (productId > 0 && priceId > 0) {
			ProductManager pm = (ProductManager) totalDao.get(
					ProductManager.class, productId);
			Price price = (Price) totalDao.get(Price.class, priceId);
			if (price != null && pm != null) {
				String time = Util.getDateTime("yyyy-MM-dd");
				String hql_price = "from Price where 1=1 and ( partNumber=?  or partNumber = ?)  and '"
						+ time
						+ "'>= pricePeriodStart and ('"
						+ time
						+ "'<= pricePeriodEnd or pricePeriodEnd = '' or pricePeriodEnd is null)";
				if (pm.getBanben() != null && pm.getBanben().length() > 0) {
					hql_price += " and banbenhao='" + pm.getBanben() + "'";
				} else {
					hql_price += " and (banbenhao = '' or banbenhao is null )";
				}
				Price newprice = (Price) totalDao.getObjectByCondition(
						hql_price, pm.getPieceNumber(), pm.getYwMarkId());
				if (newprice != null) {
					if ((newprice.getId() + "").equals(price.getId())) {
						return "订单与最新单价一样无需刷新!~";
					}
					pm.setTaxprice(newprice.getTaxprice());
					pm.setBhsPrice(newprice.getBhsPrice());
					pm.setUnit(newprice.getHsPrice());
					pm.setUnitPrice(newprice.getHsPrice() * pm.getNum());
					pm.setPriceId(newprice.getId());
					pm.setDanwei(newprice.getDanwei());
					if (totalDao.update(pm)) {
						msg = "true";
					}
				} else {
					msg = "未查到件号:" + pm.getPieceNumber() + " 有效期内的单价!~";
				}
			}
		}
		return msg;
	}

	@Override
	public List findDjfProduct() {
		String hql_pieceNumber = "select pieceNumber from ProductManager where num-sellCount>500 GROUP BY pieceNumber";
		String hql_num = "select sum(num-sellCount) from ProductManager where num-sellCount>500 GROUP BY pieceNumber";
		List list_pieceNumber = totalDao.query(hql_pieceNumber);
		List list_num = totalDao.query(hql_num);
		List list = new ArrayList();
		list.add(list_pieceNumber);
		list.add(list_num);
		return list;
	}

	@Override
	public String pladdorder(File addorder) {

		String msg = "true";
		boolean flag = true;
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int i = 0;
		try {
			FileCopyUtils.copy(addorder, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				List<Integer> strList = new ArrayList<Integer>();
				for (i = 2; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[0].getContents() != null) {
						// 序号 内部订单号 外部订单号 物料名称 业务件号 件号 版本 单位 计划生产数量 入库数量

						String b = cells[1].getContents();// 内部订单号
						String c = cells[2].getContents();// 外部订单号
						String d = cells[3].getContents();// 物料名称
						String e = cells[4].getContents();// 业务件号
						String f = cells[5].getContents();// 件号
						String g = cells[6].getContents();// 版本
						String k = cells[7].getContents();// 单位
						String h = cells[8].getContents();// 计划生产数量
						String j = cells[9].getContents();// 入库数量
						String l = cells[10].getContents();// 出库数量
						String m = cells[11].getContents();// 客户编号
						String hql_cl = "from ClientManagement where number=?";
						ClientManagement clim = (ClientManagement) totalDao
								.getObjectByCondition(hql_cl, m);
						if (clim == null) {
							continue;
						}

						String hql = "from OrderManager where orderNum=? and outOrderNumber =?";

						OrderManager order1 = (OrderManager) totalDao
								.getObjectByCondition(hql, b, c);
						int orderId = 0;
						if (order1 == null) {
							OrderManager order = new OrderManager();
							order.setOrderNum(b);
							order.setOutOrderNumber(c);
							order.setOrderType("正式");
							order.setAddTime(Util.getDateTime());
							order.setOrderStatus("NO");
							order.setDeliveryStatus("否");
							Users loginUser = Util.getLoginUser();
							order.setDocumentaryPeople(loginUser.getName());
							order.setBillingPeople(loginUser.getName());
							order.setPaymentDate("2016-11-5");
							order.setEp_statuts("同意");
							order.setDept(loginUser.getDept());
							order.setConversionStatus("NO");
							order.setTimeRate(0F);
							order.setCustome(clim);
							order.setDocumentaryPeopleId(loginUser.getId());
							ProductManager pm = new ProductManager();
							pm.setName(d);
							pm.setYwMarkId(e);
							pm.setPieceNumber(f);
							pm.setBanben(g);
							pm.setDanwei(k);
							Float sellcount = Float.valueOf(l);
							Float num = Float.valueOf(h);
							Float rknum = Float.valueOf(j);
							pm.setNum(num);
							pm.setSellCount(sellcount);
							pm.setAllocationsNum(rknum);
							ProcardTemplate pt = (ProcardTemplate) totalDao
									.getObjectByCondition(
											"from ProcardTemplate where ywMarkId=? and productStyle = ? and procardStyle='总成' "
													+ " and (banbenStatus is null or banbenStatus = '默认')"
													+ " and (dataStatus is null or dataStatus<>'删除')  ",
											e, "批产");
							if (pt != null) {
								pm.setPieceNumber(pt.getMarkId());// 件号;
								pm.setYwMarkId(pt.getYwMarkId());
							} else {
								continue;
							}
							Set<ProductManager> pmset = new HashSet<ProductManager>();
							pmset.add(pm);
							order.setProducts(pmset);
							Float inputRate = (float) rknum / num;
							order.setInputRate(inputRate * 100);
							order
									.setCompletionrate((float) (sellcount / num) * 100);
							totalDao.save(order);
							orderId = order.getId();
						} else {
							ProductManager pm = new ProductManager();
							pm.setName(d);
							pm.setYwMarkId(e);
							pm.setPieceNumber(f);
							pm.setBanben(g);
							pm.setDanwei(k);
							Float num = Float.valueOf(h);
							Float rknum = Float.valueOf(j);
							Float sellcount = Float.valueOf(l);
							pm.setNum(num);
							pm.setAllocationsNum(rknum);
							pm.setSellCount(sellcount);
							Set<ProductManager> pmset = order1.getProducts();
							pmset.add(pm);

							String sql_num = " SELECT sum(f_num) num ,sum(f_allocationsNum) allocationsNum ,sum(sellCount) sellcount FROM TA_Product WHERE fk_orderManager_id = "
									+ order1.getId();

							List<Map> list = totalDao.findBySql(sql_num);
							if (list != null && list.size() > 0) {
								Map map = list.get(0);
								Float sumnum = (Float) map.get("num");
								Float allocationsNum = (Float) map
										.get("allocationsNum");
								Float sumsellCount = (Float) map
										.get("sellcount");
								sumnum += num;
								allocationsNum += rknum;
								sumsellCount += sellcount;
								order1
										.setCompletionrate((float) (sumsellCount / sumnum) * 100);
								order1
										.setInputRate((float) (allocationsNum / sumnum) * 100);
							}
							totalDao.update(order1);
							orderId = order1.getId();
						}
						// 更新库存; 自动入库，自动出库;
						String hql_goodsStore = "from GoodsStore where goodsStoreMarkId=? and goodsStoreGoodsName=? and order_Id=? and banBenNumber =?";
						GoodsStore goodsStore = (GoodsStore) totalDao
								.getObjectByCondition(hql_goodsStore, f, d,
										orderId, g);
						float storeCount = Float.parseFloat(j);
						String hql_goods = "from Goods  where order_Id=? and goodsMarkId =? and goodsFullName = ? and banBenNumber = ?";
						Goods goods = (Goods) totalDao.getObjectByCondition(
								hql_goodsStore, f, d, orderId, g);

						String hql_sell = " from Sell where orderNum = ? and outOrderNumer = ? and sellMarkId =? ";
						Sell sell = (Sell) totalDao.getObjectByCondition(
								hql_sell, b, c, f);
						if (goodsStore == null) {
							goodsStore = new GoodsStore();
							goodsStore.setNeiorderId(b);
							goodsStore.setWaiorderId(c);
							goodsStore.setGoodsStoreMarkId(f);
							goodsStore.setGoodsStoreGoodsName(d);
							goodsStore.setBanBenNumber(g);
							goodsStore.setGoodsStoreUnit(k);
							goodsStore.setGoodsStoreCount(storeCount);
							goodsStore.setGoodsStoreTotal(storeCount);
							goodsStore.setOrder_Id(orderId);
							goodsStore.setStatus("入库");
							goodsStore.setStyle("手动入库");
							goodsStore.setGoodsStoreWarehouse("成品库");
							if (goods == null) {
								goods = new Goods();
								goods.setNeiorderId(b);
								goods.setWaiorderId(c);
								goods.setGoodsMarkId(f);
								goods.setGoodsFullName(d);
								goods.setBanBenNumber(g);
								goods.setGoodsUnit(k);
								goods.setGoodsCurQuantity(storeCount);
								goods.setGoodsStyle("手动入库");
								goods.setGoodsClass("成品库");
								totalDao.save(goods);
							}
							totalDao.save(goodsStore);
						} else {
							goodsStore.setGoodsStoreCount(storeCount);
							goodsStore.setGoodsStoreTotal(goodsStore
									.getGoodsStoreTotal()
									+ storeCount);
							goods.setGoodsCurQuantity(goods
									.getGoodsCurQuantity()
									+ storeCount);
							totalDao.update(goodsStore);
							totalDao.update(goods);
						}
						Float sellCount = Float.parseFloat(l);
						if (sell == null) {
							sell = new Sell();
							sell.setOrderNum(b);
							sell.setOutOrderNumer(c);
							sell.setSellMarkId(f);
							sell.setSellGoods(d);
							sell.setSellUnit(k);
							sell.setSellCount(sellCount);
							sell.setPrintStatus("YES");
							sell.setStyle("正常出库");
							sell.setGoodsId(goods.getGoodsId());
							sell.setSellWarehouse("成品库");
							totalDao.save(sell);
						} else {
							sell.setSellCount(sell.getSellCount() + sellCount);
							sell.setPrintStatus("YES");
							totalDao.update(sell);
						}
						goods.setGoodsCurQuantity(goods.getGoodsCurQuantity()
								- sellCount);
						totalDao.update(goods);
					} else {
						strList.add(i);
					}
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				if (strList != null && strList.size() > 0) {
					Integer drcount = exclecolums - 2 - strList.size();
					msg = "已成功导入" + drcount + "个，失败" + strList.size()
							+ "个，失败的行数分别为：";
					for (int j = 0; j < strList.size(); j++) {
						if (j == 0) {
							msg += strList.get(j);
						} else {
							msg += "," + strList.get(j);
						}
					}
					msg += "。";
				}
			} else {
				msg = "没有获取到行数";
			}

		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		return msg;

	}

	@Override
	public String getorderNum(String type) {
		if (type == null || type.length() == 0) {
			type = "正式";
		}
		String orderNum1 = "";
		String hql1 = "select valueCode from CodeTranslation where type = 'sys' and valueName='订单' and keyCode=?";
		String valueCode = (String) totalDao.getObjectByCondition(hql1, type);
		String orderNum = valueCode + Util.getDateTime("yyyyMMdd");
		String hql_orderNum = "select max(orderNum) from  OrderManager where orderNum like '"
				+ orderNum + "%'";
		orderNum1 = (String) totalDao.getObjectByCondition(hql_orderNum);
		if (orderNum1 == null || orderNum1.length() == 0) {
			orderNum1 = orderNum + "0001";
		} else {
			String strnum = orderNum1.substring(valueCode.length() + 8);
			Integer num = Integer.parseInt(strnum) + 1;
			orderNum1 = orderNum + String.format("%04d", num);
		}
		return orderNum1;
	}

	@Override
	public Float getbfCount(String markId) {
		// TODO Auto-generated method stub
		Float count = (Float) totalDao
				.getObjectByCondition(
						"select count(*) from ProductManager where  num> (cxCount+cxApplyCount) "
								+ "and (pieceNumber=? or ywMarkId=?) and orderManager.orderType in('备货') and orderManager.ep_statuts='同意'",
						markId, markId);
		return count;
	}

	@Override
	public List<ProductManager> getBhPmList(String markId,String ywMarkId) {
		// TODO Auto-generated method stub
		List<ProductManager> pmList = totalDao
				.query(
						" from ProductManager where status not in('删除','取消','暂停') and num> (cxCount+cxApplyCount)"
								+ "and (pieceNumber=? or ywMarkId=? or pieceNumber=? or ywMarkId=?) and orderManager.orderType in('备货') and orderManager.ep_statuts='同意'",
						markId, markId,ywMarkId,ywMarkId);
		if (pmList != null && pmList.size() > 0) {
			for (ProductManager pm : pmList) {
				pm.setOrderNumber(pm.getOrderManager().getOrderNum());
			}
		}
		return pmList;
	}

	@Override
	public Object[] findAllYcProduct(YcProduct ycProduct, int pageNo,
			int pageSize, String status) {
		if (ycProduct == null) {
			ycProduct = new YcProduct();
		}
		String hql = totalDao.criteriaQueries(ycProduct, null);
		if ("wfp".equals(status)) {
			hql += " and status = '未分配'";
		} else if ("ywc".equals(status)) {
			hql += " and status = '完成'";
		} else if ("cgz".equals(status)) {
			hql += " and status = '采购中'";
		} else if ("dsq".equals(status)) {
			hql += " and status = '待申请'";
		}
		List<YcProduct> ycproductList = totalDao.findAllByPage(hql, pageNo,
				pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { ycproductList, count };
	}

	@Override
	public String addycWeek(Integer id, List<YcWeekFePei> ycweekList) {
		if (id != null && id > 0) {
			YcProduct ycproduct = (YcProduct) totalDao.get(YcProduct.class, id);
			Float yfpNum = 0f;
			Set<YcWeekFePei> setycWeekFeiPei = new HashSet<YcWeekFePei>();
			if (ycweekList != null && ycweekList.size() > 0) {
				for (int i = 0; i < ycweekList.size(); i++) {
					YcWeekFePei ycweek = ycweekList.get(i);
					yfpNum += (ycweek.getFpNum() == null ? 0 : ycweek
							.getFpNum());
					ycweek.setYcProduct(ycproduct);
					setycWeekFeiPei.add(ycweek);
				}
				if ("未分配".equals(ycproduct.getStatus())) {
					ycproduct.setStatus("待申请");
				}
				ycproduct.setSetycWeekFeiPei(setycWeekFeiPei);
				ycproduct.setYfpNum(yfpNum);
				return totalDao.update(ycproduct) + "";
			} else {
				return "请至少分配一周的数量";
			}
		}
		return null;
	}

	@Override
	public String addYcWaiGouProcrd(Integer id) {
		StringBuffer msg = new StringBuffer();

		StringBuffer errormsg = new StringBuffer();
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录";
		}
		if (id != null && id > 0) {
			YcProduct ycproduct = (YcProduct) totalDao.get(YcProduct.class, id);
			OrderManager order = (OrderManager) totalDao.getObjectByCondition(
					" from OrderManager where orderNum = ?", ycproduct
							.getOrderNo());
			List<YcWeekFePei> ycWeekList = totalDao
					.query(
							" from YcWeekFePei where ycProduct.id =?  order by whateWeek ",
							id);
			List<ProcardTemplate> wgProcardTList = totalDao
					.query(
							" from ProcardTemplate where  rootId in (select id from ProcardTemplate where markId = ? and productStyle = '批产' and (banbenStatus !='历史' or banbenStatus is null) and  (dataStatus is null or dataStatus <>'删除') ) "
									+ " and procardStyle = '外购' and (banbenStatus !='历史' or banbenStatus is null)"
									+ " and (dataStatus is null or dataStatus <>'删除') and (needProcess is null or needProcess <> 'yes')",
							ycproduct.getMarkId());
			List<ManualOrderPlanDetail> modpList = new ArrayList<ManualOrderPlanDetail>();
			String hql1 = "select valueCode from CodeTranslation where type = 'sys' and keyCode='无限物料' and valueName='MRP'";
			String valueCode = (String) totalDao.getObjectByCondition(hql1);
			int count = 0;
			for (ProcardTemplate wgProcardT : wgProcardTList) {
				// 客供料，无需采购
				if ("CS".equals(wgProcardT.getKgliao())
						|| "否".equals(wgProcardT.getLingliaostatus())) {
					msg.append("<br>件号:" + wgProcardT.getMarkId() + "的供料属性为："
							+ wgProcardT.getKgliao() + "，领料状态为"
							+ wgProcardT.getLingliaostatus() + "，无需采购。</br>");
					continue;
				}
				if ("DKBA3360.1".equals(wgProcardT.getMarkId())) {
					System.out.println(wgProcardT.getMarkId() + " ======");
				}
				String hql_ycl = " from YuanclAndWaigj where markId = ?";
				if (wgProcardT.getBanBenNumber() != null
						&& wgProcardT.getBanBenNumber().length() > 0) {
					hql_ycl += " and banbenhao = '"
							+ wgProcardT.getBanBenNumber() + "'";
				} else {
					hql_ycl += " and (banbenhao = '' or banbenhao is null)";
				}
				if (wgProcardT.getSpecification() != null
						&& wgProcardT.getSpecification().length() > 0) {
					hql_ycl += " and specification = '"
							+ wgProcardT.getSpecification() + "'";
				} else {
					hql_ycl += " and (specification = '' or specification is null)";
				}
				if (wgProcardT.getKgliao() != null
						&& wgProcardT.getKgliao().length() > 0) {
					hql_ycl += " and kgliao = '" + wgProcardT.getKgliao() + "'";
				} else {
					hql_ycl += " and (kgliao = '' or kgliao is null)";
				}
				YuanclAndWaigj wgj = (YuanclAndWaigj) totalDao
						.getObjectByCondition(hql_ycl, wgProcardT.getMarkId());
				if (wgj != null) {
					if (wgj.getLtuse() != null && wgj.getLtuse() > 0) {
						Integer zcNum = 0;
						Integer begin = 0;
						YcWaiGouProcrd oldycwgProcard = null;
						try {
							count++;
							String hql_ycwgProcard = " from YcWaiGouProcrd where ycProductId = ? and procardTId =?  order by addTime desc";
							oldycwgProcard = (YcWaiGouProcrd) totalDao
									.getObjectByCondition(hql_ycwgProcard,
											ycproduct.getId(), wgProcardT
													.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}

						Set<YcWeekFePei> setycWeekfepei = new HashSet<YcWeekFePei>();
						if (oldycwgProcard != null) {
							begin = (Integer) totalDao
									.getObjectByCondition(
											"select A.whateWeek from YcWeekFePei A join A.setycwgProcard B where B.id=?  order by A.whateWeek desc",
											oldycwgProcard.getId());
							if (begin != null) {
								begin++;
							}
						}
						String fpweek = "";
						if (wgj.getLtuse() > ycWeekList.size()) {
							errormsg.append("<br>件号:" + wgj.getMarkId()
									+ "的采购周数为" + wgj.getLtuse() + "大于分配的周数"
									+ ycWeekList.size()
									+ ",请调整改件号的分配周数或者再增加分配周数。</br>");
							continue;
						}
						for (int i = begin; i < wgj.getLtuse() + begin; i++) {
							YcWeekFePei ycweek = ycWeekList.get(i);
							if (ycweek != null) {
								zcNum += ycweek.getFpNum() == null ? 0 : ycweek
										.getFpNum();
								setycWeekfepei.add(ycweek);
								fpweek += ";第" + ycweek.getWhateWeek()
										+ "周分配量:" + ycweek.getFpNum();
							}
						}

						if (fpweek != null && fpweek.length() > 0) {
							fpweek = fpweek.substring(1);
						}
						if (zcNum > 0) {
							Float yongli = getcgNum(wgProcardT, null);
							Float xqNum = yongli * zcNum;
							// 计算损耗率，提高采购量
							Float sunhao = wgProcardT.getSunhao();
							if (wgProcardT.getSunhaoType() == null
									|| 0 == wgProcardT.getSunhaoType()) {
								if (sunhao == null) {
									sunhao = 0f;
								}
								if (wgProcardT.getSunhao() != null
										&& wgProcardT.getSunhao() > 0) {
									xqNum = xqNum * ((100 + sunhao) / 100);
									if ("个".equals(wgProcardT.getUnit())
											|| "pcs".equals(wgProcardT
													.getUnit())
											|| "PCS".equals(wgProcardT
													.getUnit())) {
										Double newnum = Math.ceil(xqNum);
										xqNum = newnum.floatValue();
									}
								}
							} else if (1 == wgProcardT.getSunhaoType()) {
								if (sunhao == null) {
									sunhao = 0f;
								}
								if (wgProcardT.getSunhao() != null
										&& wgProcardT.getSunhao() > 0) {
									xqNum = xqNum + wgProcardT.getSunhao();
									if ("个".equals(wgProcardT.getUnit())
											|| "pcs".equals(wgProcardT
													.getUnit())
											|| "PCS".equals(wgProcardT
													.getUnit())) {
										Double newnum = Math.ceil(xqNum);
										xqNum = newnum.floatValue();
									}
								}
							}

							/***************** MRP计算(库存量&&&&&占用量) *****************/
							String goodsClassSql = null;
							// if (wgProcardT.getProductStyle() != null
							// && wgProcardT.getProductStyle()
							// .equals("试制")) {
							// // 试制的外购件去试制库取
							// goodsClassSql = " and goodsClass ='试制库'";
							// } else {
							String kgsql = "";
							if (wgProcardT.getKgliao() != null
									&& wgProcardT.getKgliao().length() > 0) {
								kgsql += " and kgliao ='"
										+ wgProcardT.getKgliao() + "'";
							}
							// goodsClassSql =
							// " and ((goodsClass in ('外购件库','中间库') "
							// + kgsql + " ) or goodsClass = '备货库')";
							goodsClassSql = " and goodsClass in ('外购件库') "
									+ kgsql;
							// }
							String banben_hql = "";
							if (wgProcardT.getBanBenNumber() != null
									&& wgProcardT.getBanBenNumber().length() > 0) {
								banben_hql = " and banBenNumber='"
										+ wgProcardT.getBanBenNumber() + "'";
							}
							String specification_sql = "";
//							if (wgProcardT.getSpecification() != null
//									&& wgProcardT.getSpecification().length() > 0) {
//								specification_sql = " and specification = '"
//										+ wgProcardT.getSpecification() + "'";
//							} else {
//								specification_sql = " and (specification = '' and specification is null)";
//							}
							// 库存量(件号+版本+供料属性+库别)
							String hqlGoods = "";
							hqlGoods = "select sum(goodsCurQuantity) from Goods where goodsMarkId=? "
									+ goodsClassSql
									+ " and goodsCurQuantity>0 " + banben_hql;
							Float kcCount = (Float) totalDao
									.getObjectByCondition(hqlGoods, wgProcardT
											.getMarkId());
							if (kcCount == null || kcCount < 0) {
								kcCount = 0f;
							}

							/****************** 占用量=生产占用量+导入占用量 ******************************/
							// 系统占用量(已计算过采购量(1、有库存 2、采购中)，未领料,预测占用量)
							String zyCountSql = "select sum(hascount) from Procard where markId=? and productStyle=? and kgliao=? "
									+ banben_hql
									+ " and jihuoStatua='激活' and (status='已发卡' or (oldStatus='已发卡' and status='设变锁定')) and procardStyle='外购'"
									+ " and (sbStatus<>'删除' or sbStatus is null ) ";
							// Float zyCount = (Float) totalDao
							// .getObjectByCondition(zyCountSql, procard
							// .getMarkId(),
							// procard.getProductStyle(), procard
							// .getKgliao());
							Float zyCount = (Float) totalDao
									.getObjectByCondition(zyCountSql,
											wgProcardT.getMarkId(), wgProcardT
													.getProductStyle(),
											wgProcardT.getKgliao());
							if (zyCount == null || zyCount < 0) {
								zyCount = 0f;
							}


							// 导入占用量(系统切换时导入占用量)
							String hqlGoods_zy = "select sum(goodsCurQuantity) from Goods where goodsMarkId=?"
									+ banben_hql
									+ " and goodsClass in ('占用库') and kgliao=? and goodsCurQuantity>0 and (fcStatus is null or fcStatus='可用')";
							Float kcCount_zy = (Float) totalDao
									.getObjectByCondition(hqlGoods_zy,
											wgProcardT.getMarkId(), wgProcardT
													.getKgliao());
							if (kcCount_zy == null || kcCount_zy < 0) {
								kcCount_zy = 0f;
							}
							zyCount += kcCount_zy;
							if (zyCount < 0) {
								zyCount = 0F;
							}

							/****************** 结束 占用量=生产占用量+导入占用量 结束 ******************************/
							/****************** 在途量=采购在途量+导入在途量 ******************************/
							// 系统在途量(已生成采购计划，未到货)
							String hql_zc = "select sum(cgNumber-dhNumber) from  Procard where markId=? and productStyle=? "
									+ banben_hql
									+ " and kgliao=? and jihuoStatua='激活' and (status='已发卡' or (oldStatus='已发卡' and status='设变锁定')) and procardStyle='外购'"
									+ " and cgNumber >0 and dhNumber is not null";
							// Float ztCount = (Float)
							// totalDao.getObjectByCondition(
							// hql_zc, wgProcardT.getMarkId(), wgProcardT
							// .getProductStyle(), wgProcardT.getKgliao());
							// 系统在途量(已生成物料需求信息，未到货)
							String hql_zc0 = "select sum(cgnumber-rukuNum) from ManualOrderPlanDetail where markId = ?  "
									+ banben_hql
									+ " and kgliao=? and cgNumber >0  and (status<>'取消' or status is null) "
									+ specification_sql;
							Float ztCount = (Float) totalDao
									.getObjectByCondition(hql_zc0, wgProcardT
											.getMarkId(), wgProcardT
											.getKgliao());
							if (ztCount == null) {
								ztCount = 0f;
							}

							// 导入在途量(系统切换时导入在途量)
							String hqlGoods_zt = "select sum(goodsCurQuantity) from Goods where goodsMarkId=?"
									+ banben_hql
									+ " and kgliao=? and goodsClass in ('在途库') and goodsCurQuantity>0 and (fcStatus is null or fcStatus='可用')";
							Float kcCount_zt = (Float) totalDao
									.getObjectByCondition(hqlGoods_zt,
											wgProcardT.getMarkId(), wgProcardT
													.getKgliao());
							if (kcCount_zt == null || kcCount_zt < 0) {
								kcCount_zt = 0f;
							}
							ztCount += kcCount_zt;
							if (ztCount < 0) {
								ztCount = 0F;
							}
							/****************** 结束 在途量=采购在途量+导入在途量 结束 ******************************/
							// (库存量+在途量(已生成采购，未到货))-占用量=剩余可用库存量
							Float daizhiCount = (kcCount + ztCount) - zyCount;
							if (daizhiCount < 0) {
								daizhiCount = 0F;
							}
							// 无限物料模式 库存根据需求数量自动增加
							if (xqNum > daizhiCount && "是".equals(valueCode)) {
								String hqlgoods = "from Goods where goodsMarkId=? "
										+ goodsClassSql
										+ " and goodsCurQuantity>0 "
										+ banben_hql
										+ " and (fcStatus is null or fcStatus='可用')";
								Goods goods = (Goods) totalDao
										.getObjectByCondition(hqlgoods,
												wgProcardT.getMarkId());
								if (goods != null) {
									goods.setGoodsCurQuantity(goods
											.getGoodsCurQuantity()
											+ xqNum);
									totalDao.update(goods);
								} else {
									goods = new Goods();
									goods
											.setGoodsMarkId(wgProcardT
													.getMarkId());
									if (wgProcardT.getBanBenNumber() != null
											&& wgProcardT.getBanBenNumber()
													.length() > 0) {
										goods.setBanBenNumber(wgProcardT
												.getBanBenNumber());
									}
									goods.setGoodsFullName(wgProcardT
											.getProName());
									goods.setGoodsClass("外购件库");
									if (wgProcardT.getKgliao() != null
											&& wgProcardT.getKgliao().length() > 0) {
										goods.setKgliao(wgProcardT.getKgliao());
									}
									goods.setGoodsCurQuantity(xqNum);
									totalDao.save(goods);
								}
								daizhiCount += xqNum;
							}

							// 剩余可用库存量多余
							Float sjxqNum = 0f;
							Float fpNum = 0f;
							if (daizhiCount > 0) {
								/****
								 * 有库存
								 */
								// 待采购量(需求数量-呆滞数量)
								sjxqNum = xqNum - daizhiCount;
								// 库存量小于待采购量
								if (sjxqNum > 0) {
									fpNum = daizhiCount;
								} else {
									sjxqNum = 0f;
									fpNum = xqNum;
								}
							} else {
								sjxqNum = xqNum;
							}
							String more = "需求量(" + xqNum + ")= (总成分配量(" + zcNum
									+ ")*用量(" + yongli + "))*((100+损耗率("
									+ sunhao + ")/100))<br/>" + "实际需求量("
									+ sjxqNum + "),在途量(" + ztCount + "),库存量("
									+ kcCount + "),占用量(" + zyCount + ")";
							YcWaiGouProcrd ycwgProcard = new YcWaiGouProcrd();
							ycwgProcard.setMarkId(wgProcardT.getMarkId());
							ycwgProcard.setProName(wgProcardT.getProName());
							ycwgProcard.setSpecification(wgProcardT
									.getSpecification());
							ycwgProcard.setBanben(wgProcardT.getBanBenNumber());
							ycwgProcard.setUnit(wgProcardT.getUnit());
							ycwgProcard.setTuhao(wgProcardT.getTuhao());
							ycwgProcard.setWgType(wgProcardT.getWgType());
							ycwgProcard.setXqNum(xqNum);
							ycwgProcard.setZcNum(zcNum);
							ycwgProcard.setAddTime(Util.getDateTime());
							ycwgProcard.setAddUsersCode(user.getCode());
							ycwgProcard.setAddUsersName(user.getName());
							ycwgProcard.setYcProductId(ycproduct.getId());
							ycwgProcard.setProcardTId(wgProcardT.getId());
							ycwgProcard.setSetycWeekfepei(setycWeekfepei);
							ycwgProcard.setLtdengji(wgj.getLtdengji());
							ycwgProcard.setLtuse(wgj.getLtuse());
							ycwgProcard.setFpweek(fpweek);
							ycwgProcard.setMore(more);
							ycwgProcard.setSjxqNum(sjxqNum);
							if (!"TK".equals(wgProcardT.getKgliao())
									&& !"TK AVL".equals(wgProcardT.getKgliao())
									&& !"TK Price".equals(wgProcardT
											.getKgliao())
									&& !"CS".equals(wgProcardT.getKgliao())) {
								ycwgProcard.setKgliao("TK");
							}
							totalDao.save(ycwgProcard);
							// 添加物流需求明显
							if (sjxqNum > 0) {
								ManualOrderPlanDetail mopd = new ManualOrderPlanDetail(
										ycwgProcard.getMarkId(), ycwgProcard
												.getProName(), ycwgProcard
												.getSpecification(),
										ycwgProcard.getBanben(), ycwgProcard
												.getUnit(), ycwgProcard
												.getKgliao(), ycwgProcard
												.getTuhao(), ycwgProcard
												.getWgType(), xqNum, null, Util
												.getDateTime(), user.getName(),
										user.getCode(), "预测订单", null, "未申请",
										null);
								mopd.setBanci(wgProcardT.getBanci());
								mopd.setProductId(ycproduct.getProductId());
								mopd.setOrderId(order.getId());
								mopd.setRukuNum(0f);
								mopd.setCategory("外购");
								mopd.setOrderNumber(ycproduct.getOrderNo());
								mopd.setOrderOutNumber(ycproduct
										.getOutOrderNo());
								mopd.setStatus("未采购");
								totalDao.save(mopd);
								modpList.add(mopd);
								ycwgProcard.setEpstatus("未申请");
								ycwgProcard.setMopdId(mopd.getId());
							} else {
								ycwgProcard.setEpstatus("同意");
							}
							totalDao.update(ycwgProcard);
						}
					} else {
						errormsg.append("<br>件号:" + wgProcardT.getMarkId()
								+ "版本:" + wgProcardT.getBanBenNumber() + "规格:"
								+ wgProcardT.getSpecification() + "供料属性:"
								+ wgProcardT.getKgliao()
								+ "的外购件。尚未绑定外购件LT等级，请前往外购件库绑定。</br>");
						continue;
					}
				} else {
					errormsg.append("<br>件号:" + wgProcardT.getMarkId() + "版本:"
							+ wgProcardT.getBanBenNumber() + " 规格:"
							+ wgProcardT.getSpecification() + " 供料属性:"
							+ wgProcardT.getKgliao() + "  "
							+ "的外购件，未在外购件库中找到。</br>");
					continue;
				}

			}
			if (modpList != null && modpList.size() > 0) {
				String hql_dept = "select B.dept from CircuitCustomize A join A.deptset B where A.name = '物料需求申请' and B.dept = '"
						+ user.getDept() + "' ";
				String deptName = (String) totalDao
						.getObjectByCondition(hql_dept);
				String hql_AuditNode = " from AuditNode where circuitCustomize.id in (select id from CircuitCustomize where name = '物料需求申请' )";
				if (deptName != null && deptName.length() > 0) {
					hql_AuditNode = "from AuditNode where circuitCustomize.id=in(select id from CircuitCustomize where name = '物料需求申请' ) and id in ( select a.id from AuditNode a join a.deptSet b where b.dept ="
							+ deptName + ") order by auditLevel";
				}
				AuditNode auditNode = (AuditNode) totalDao
						.getObjectByCondition(hql_AuditNode);
				if (auditNode != null) {
					AlertMessagesServerImpl.addAlertMessages("物料需求信息审批", user
							.getName()
							+ "预测物料需求信息" + modpList.size() + "条，请您审批.",
							new Integer[] { auditNode.getAuditUserId() },
							"ManualOrderPlanAction_findmanualPlanList.action?status=wsq&mod1.addUsersCode="
									+ user.getCode() + "mod1.productId="
									+ ycproduct.getProductId() + "&tag=daoru",
							true);
				}
			}
			Float sum_zcNum = 0f;
			List<Map> list_map = totalDao
					.findBySql("select min(A.sum_zcNum) sum_zcNum  from (select sum(zcNum) sum_zcNum from ta_yc_YcWaiGouProcrd where ycProductId = "
							+ ycproduct.getId() + " group by procardTId ) A ");
			if (list_map != null && list_map.size() > 0) {
				Map<String, Object> map = list_map.get(0);
				if (map.get("sum_zcNum") != null) {
					sum_zcNum = (Float) map.get("sum_zcNum");
				}
			}
			ycproduct.setYsqNum(sum_zcNum);
			ycproduct.setStatus("采购中");
			totalDao.update(ycproduct);
			if (errormsg != null && errormsg.length() > 0) {
				throw new RuntimeException(errormsg.toString());
			}
			if (msg != null && msg.length() > 0) {
				return msg.toString();
			} else {
				return "true";
			}
		}
		return null;
	}

	@Override
	public Object[] findycwgProcardList(YcWaiGouProcrd ycwgprocard, int pageNo,
			int pageSize, String status) {
		if (ycwgprocard == null) {
			ycwgprocard = new YcWaiGouProcrd();
		}
		String hql = totalDao.criteriaQueries(ycwgprocard, null);
		List<YcWaiGouProcrd> ycwgprocardList = totalDao.findAllByPage(hql,
				pageNo, pageSize);
		for (YcWaiGouProcrd ycwgprocard0 : ycwgprocardList) {

		}
		int count = totalDao.getCount(hql);
		return new Object[] { ycwgprocardList, count };
	}

	@Override
	public String issqcg(Integer id) {
		if (id != null && id > 0) {
			YcProduct ycproduct = (YcProduct) totalDao.get(YcProduct.class, id);
			if (ycproduct.getYsqNum() != null
					&& ycproduct.getYsqNum().floatValue() >= ycproduct.getNum()
							.floatValue()) {
				return "申请采购的数量已超过下单总成需求数量!";
			}
			List<YcWeekFePei> ycWeekList = totalDao
					.query(
							" from YcWeekFePei where ycProduct.id =?  order by whateWeek ",
							id);
			List<ProcardTemplate> wgProcardTList = totalDao
					.query(
							" from ProcardTemplate where rootMarkId = ? and procardStyle = '外购'",
							ycproduct.getMarkId());
			if (wgProcardTList == null || wgProcardTList.size() == 0) {
				return "件号：" + ycproduct.getMarkId() + "未在BOM中找到下属外购件.";
			}
			boolean bool = true;
			for (ProcardTemplate wgProcardT : wgProcardTList) {
				String hql_ycl = " from YuanclAndWaigj where markId = ?";
				if (wgProcardT.getBanBenNumber() != null
						&& wgProcardT.getBanBenNumber().length() > 0) {
					hql_ycl += " and banbenhao = '"
							+ wgProcardT.getBanBenNumber() + "'";
				} else {
					hql_ycl += " and (banbenhao = '' or banbenhao is null)";
				}
				if (wgProcardT.getSpecification() != null
						&& wgProcardT.getSpecification().length() > 0) {
					hql_ycl += " and specification = '"
							+ wgProcardT.getSpecification() + "'";
				} else {
					hql_ycl += " and (specification = '' or specification is null)";
				}
				if (wgProcardT.getKgliao() != null
						&& wgProcardT.getKgliao().length() > 0) {
					hql_ycl += " and kgliao = '" + wgProcardT.getKgliao() + "'";
				} else {
					hql_ycl += " and (kgliao = '' or kgliao is null)";
				}
				YuanclAndWaigj wgj = (YuanclAndWaigj) totalDao
						.getObjectByCondition(hql_ycl, wgProcardT.getMarkId());
				if (wgj != null) {
					if (wgj.getLtuse() != null && wgj.getLtuse() > 0) {
						Integer zcNum = 0;
						Integer begin = 0;
						String hql_ycwgProcard = " from YcWaiGouProcrd where ycProductId = ? and procardTId =?  order by addTime desc";
						YcWaiGouProcrd oldycwgProcard = (YcWaiGouProcrd) totalDao
								.getObjectByCondition(hql_ycwgProcard,
										ycproduct.getId(), wgProcardT.getId());
						Set<YcWeekFePei> setycWeekfepei = new HashSet<YcWeekFePei>();
						if (oldycwgProcard != null) {
							begin = (Integer) totalDao
									.getObjectByCondition(
											"select A.whateWeek from YcWeekFePei A join A.setycwgProcard B where B.id=?  order by A.whateWeek desc",
											oldycwgProcard.getId());
							if (begin != null) {
								begin++;
							}
						}
						for (int i = begin; i < ycWeekList.size() - begin; i++) {
							YcWeekFePei ycweek = ycWeekList.get(i);
							if (ycweek != null) {
								zcNum += ycweek.getFpNum() == null ? 0 : ycweek
										.getFpNum();
							}
						}
						if (zcNum > 0) {
							bool = false;
							break;
						}
					}
				}
			}
			if (bool) {
				return "该预测订单目前的分配量可采购量为0，请分配后再申请采购。";
			}
			YcWaiGouProcrd ycwgprocard = (YcWaiGouProcrd) totalDao
					.getObjectByCondition(
							" from YcWaiGouProcrd where ycProductId = ? and ltdengji =(select min(ltdengji) from YcWaiGouProcrd   where ycProductId = ? )  order by  addTime desc",
							id, id);
			if (ycwgprocard != null) {
				// String addtime = ycwgprocard.getAddTime();
				// String lastTime = Util.getSpecifiedDayAfter(addtime,
				// ycwgprocard.getLtuse() * 7);
				// Date date2 = Util.StringToDate(lastTime, "yyyy-MM-dd");
				// bool = new Date().after(date2);
			} else {
				bool = true;
			}
			if (bool) {
				return "true";
			}
			// else {
			// return "采购周期未到!";
			// }
		} else {
			return "刷新重试！";
		}
		return "true";

	}

	@Override
	public Object[] findycWeek(Integer id) {
		if (id != null && id > 0) {
			YcProduct ycproduct = (YcProduct) totalDao.get(YcProduct.class, id);
			List<YcWeekFePei> ycweekList = totalDao.query(
					" from YcWeekFePei where ycProduct.id = ? ", id);
			return new Object[] { ycproduct, ycweekList };
		}
		return null;
	}

	/****
	 * 根据id获取订单产品信息
	 */
	@Override
	public ProductManager findProductManager(Integer id) {
		if (id != null) {
			return (ProductManager) totalDao.getObjectById(
					ProductManager.class, id);
		}
		return null;
	}

	/****
	 * 提交订单进入审核
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String updateOrderForsubmit(Integer id) {
		String errorMes = "";
		if (id != null) {
			OrderManager om = (OrderManager) totalDao.getObjectById(
					OrderManager.class, id);
			if (om != null) {
				if ("未审批".equals(om.getEp_statuts())
						|| "审批中".equals(om.getEp_statuts())
						|| "同意".equals(om.getEp_statuts())) {
					return "订单已申请审批，请刷新！";
				}
				// 判断下层的要货计划是否全部添加完成
				List<ProductManager> pmList = totalDao.query(
						"from ProductManager where orderManager.id=?", id);
				String mes_products = "";
				for (int i = 0; i < pmList.size(); i++) {
					ProductManager pm = pmList.get(i);
					if ("已转完".equals(pm.getStatus())
							|| "未转完".equals(pm.getStatus())) {
						continue;
					}
					if (!"计划转换".equals(pm.getStatus())
							&& !"全部冲销".equals(pm.getStatus())) {
						errorMes += pm.getPieceNumber() + "尚未添加要货计划。";
					}
					mes_products += "\n" + (i + 1) + "、" + pm.getPieceNumber()
							+ "(" + pm.getYwMarkId() + "),数量:" + pm.getNum()
							+ pm.getDanwei() + ",交付:" + pm.getPaymentDate();

					// 判断bom是否已经编制完成
					// Float noPzCount = (Float) totalDao
					// .getObjectByCondition(
					// "select count(*) from ProcardTemplate where (bzStatus is null or bzStatus!='已批准') and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除') "
					// +
					// " and rootId in(select id from ProcardTemplate where (markId=? or ywMarkId=?)  and procardStyle='总成' and (banbenStatus is null or banbenStatus ='默认') and (dataStatus is null or dataStatus !='删除'))",
					// pm.getPieceNumber(), pm.getPieceNumber());
					// if (noPzCount != null && noPzCount > 0) {
					// errorMes += pm.getPieceNumber() + "还有" + noPzCount
					// + "项未编制,请抓紧联系工程师处理!";
					// }
				}

				if (errorMes == "") {
					Integer epId = null;
					try {
						String processName = "订单申请审核";
						if (om.getProducttype() != null
								&& "试制".equals(om.getProducttype())) {
							processName = "试制订单申请审核";
						} else if ("售后".equals(om.getOrderType())) {
							processName = "售后订单申请审核";
						}
						epId = CircuitRunServerImpl
								.createProcess(processName, OrderManager.class,
										om.getId(), "ep_statuts", "id",
										"orderManager_queryDetail.action?id="
												+ om.getId()
												+ "&status=shenpi&flag=dj",
										"新增 " + om.getOrderType() + " 订单,订单编号:"
												+ om.getOutOrderNumber() + "/"
												+ om.getOrderNum()
												+ ",请您审核!(要货计划请查看订单明细)"
												+ mes_products, true);
						if (epId != null && epId > 0) {
							om.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.get(
									CircuitRun.class, epId);
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun
											.getAuditStatus())) {
								om.setEp_statuts("同意");
								// CircuitRunServerImpl.orderChongxiao(om.getId());
							} else {
								om.setEp_statuts("未审批");
							}
							boolean bool = totalDao.update(om);
							if (bool) {
								errorMes = "提交成功!";
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						errorMes += e.getMessage();
					}
				}
			}
		}
		return errorMes;
	}

	@Override
	public String exportExcelYc(YcProduct ycproduct) {
		if (ycproduct == null) {
			ycproduct = new YcProduct();
		}
		String hql = totalDao.criteriaQueries(ycproduct, null);
		List<YcProduct> ycproductList = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("预测订单产品信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("预测订单产品信息", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 38);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "预测订单号(内)"));
			ws.addCell(new Label(2, 0, "预测订单号(外)"));
			ws.addCell(new Label(3, 0, "件号"));
			ws.addCell(new Label(4, 0, "业务件号"));
			ws.addCell(new Label(5, 0, "产品名称"));
			ws.addCell(new Label(6, 0, "预测订单量"));
			ws.addCell(new Label(7, 0, "正式订单量"));
			ws.addCell(new Label(8, 0, "差异量"));
			ws.addCell(new Label(9, 0, "正式订单号（内）"));
			ws.addCell(new Label(10, 0, "正式订单号（外）"));
			for (int i = 0; i < ycproductList.size(); i++) {
				YcProduct ycProduct2 = (YcProduct) ycproductList.get(i);
				OrderManager order = (OrderManager) totalDao
						.getObjectByCondition(
								" from OrderManager where orderId in ( select id   from OrderManager where orderNum = ?) ",
								ycProduct2.getOrderNo());
				String zsNum = "未关联正式";
				String zsOrderNo = "未关联正式";
				String zsoutOrderNo = "未关联正式";
				if (order != null) {
					zsOrderNo = order.getOrderNum();
					zsoutOrderNo = order.getOutOrderNumber();
					ProductManager product = (ProductManager) totalDao
							.getObjectByCondition(
									" from ProductManager where orderManager.id = ? and pieceNumber = ? ",
									order.getId(), ycProduct2.getMarkId());
					if (product != null) {
						zsNum = product.getNum() + "";
					}
				}
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, ycProduct2.getOrderNo()));
				ws.addCell(new Label(2, i + 1, ycProduct2.getOutOrderNo()));
				ws.addCell(new Label(3, i + 1, ycProduct2.getMarkId()));
				ws.addCell(new Label(4, i + 1, ycProduct2.getYwmarkId()));
				ws.addCell(new Label(5, i + 1, ycProduct2.getProName()));
				ws.addCell(new Label(6, i + 1, ycProduct2.getNum() + ""));
				ws.addCell(new Label(7, i + 1, zsNum));
				ws.addCell(new Label(8, i + 1,
						(ycProduct2.getCxNum() != null ? ycProduct2.getCxNum()
								+ "" : "未关联正式")));
				ws.addCell(new Label(9, i + 1, zsOrderNo));
				ws.addCell(new Label(10, i + 1, zsoutOrderNo));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String exportExcelYcWg(YcWaiGouProcrd ycwg) {
		if (ycwg == null) {
			ycwg = new YcWaiGouProcrd();
		}
		String hql = totalDao.criteriaQueries(ycwg, null);
		List<YcWaiGouProcrd> ycWgList = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("申请外购件采购明细信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("申请外购件采购明细", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 38);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "预测订单号(内)"));
			ws.addCell(new Label(2, 0, "预测订单号(外)"));
			ws.addCell(new Label(3, 0, "总成件号"));
			ws.addCell(new Label(4, 0, "业务件号"));
			ws.addCell(new Label(5, 0, "外购件号"));
			ws.addCell(new Label(6, 0, "外购名称"));
			ws.addCell(new Label(7, 0, "版本号"));
			ws.addCell(new Label(8, 0, "规格"));
			ws.addCell(new Label(9, 0, "单位"));
			ws.addCell(new Label(10, 0, "物料类别"));
			ws.addCell(new Label(11, 0, "添加人"));
			ws.addCell(new Label(12, 0, "添加时间"));
			ws.addCell(new Label(13, 0, "需求数量"));
			ws.addCell(new Label(14, 0, "申购量"));
			ws.addCell(new Label(15, 0, "计算方式"));
			ws.addCell(new Label(16, 0, "周分配明细"));
			for (int i = 0; i < ycWgList.size(); i++) {
				YcWaiGouProcrd ycWg = (YcWaiGouProcrd) ycWgList.get(i);
				YcProduct ycProduct2 = (YcProduct) totalDao
						.getObjectByCondition(" from YcProduct where id = ?",
								ycWg.getYcProductId());
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, ycProduct2.getOrderNo()));
				ws.addCell(new Label(2, i + 1, ycProduct2.getOutOrderNo()));
				ws.addCell(new Label(3, i + 1, ycProduct2.getMarkId()));
				ws.addCell(new Label(4, i + 1, ycProduct2.getYwmarkId()));
				ws.addCell(new Label(5, i + 1, ycWg.getMarkId()));
				ws.addCell(new Label(6, i + 1, ycWg.getProName()));
				ws.addCell(new Label(7, i + 1, ycWg.getBanben()));
				ws.addCell(new Label(8, i + 1, ycWg.getSpecification()));
				ws.addCell(new Label(9, i + 1, ycWg.getUnit()));
				ws.addCell(new Label(10, i + 1, ycWg.getWgType()));
				ws.addCell(new Label(11, i + 1, ycWg.getAddUsersName()));
				ws.addCell(new Label(12, i + 1, ycWg.getAddTime()));
				ws.addCell(new Label(13, i + 1, ycWg.getXqNum() + ""));
				ws.addCell(new Label(14, i + 1, ycWg.getSjxqNum() + ""));
				ws.addCell(new Label(15, i + 1, ycWg.getMore()));
				ws.addCell(new Label(16, i + 1, ycWg.getFpweek()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String relateBh(int id, String ids) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录!";
		}
		ProductManager pm = (ProductManager) totalDao.getObjectById(
				ProductManager.class, id);
		if (pm != null) {
			if (pm.getOrderManager().getOrderType() != null
					&& pm.getOrderManager().getOrderType().equals("正式")) {
				if (ids != null) {// 冲销
					Float num = pm.getNum();
					// 先保存为获取id
					String[] pmIds = ids.split(",");
					if (pmIds != null && pmIds.length > 0) {
						for (String pmidStr : pmIds) {
							Integer pmid = Integer.parseInt(pmidStr);
							ProductManager relatePm = (ProductManager) totalDao
									.getObjectById(ProductManager.class, pmid);
							if (relatePm == null) {
								throw new RuntimeException("没有找到对应的备货订单!");
							}
							Float relateCount = 0f;
							// 获取该备货订单剩下的可冲销数量
							relateCount = relatePm.getNum()
									- relatePm.getCxCount()
									- relatePm.getCxApplyCount();
							if (relateCount > 0) {
								ProductZsAboutBh pcx = new ProductZsAboutBh();
								pcx.setZsProductId(pm.getId());// 正式产品Id
								pcx.setBhProductId(relatePm.getId());// 备货产品Id
								pcx.setMarkId(relatePm.getPieceNumber());// 零件
								pcx.setYwMarkId(relatePm.getYwMarkId());// 业务件号
								pcx.setStatus("未审批");
								Float thisCxCount = 0f;
								if (relateCount >= num) {// 订单关联已足够冲销
									pcx.setAboutCount(num);// 相关数量
									thisCxCount = num;
									relatePm.setCxApplyCount(relatePm
											.getCxApplyCount()
											+ num);
									pm.setCxApplyCount(pm.getCxApplyCount()
											+ num);
									num = 0f;
									totalDao.update(pm);
									totalDao.update(relatePm);
									totalDao.save(pcx);

								} else {
									thisCxCount = relateCount;
									pcx.setAboutCount(relateCount);// 相关数量
									relatePm.setCxApplyCount(relatePm
											.getCxApplyCount()
											+ relateCount);
									pm.setCxApplyCount(pm.getCxApplyCount()
											+ relateCount);
									num = num - relateCount;
									totalDao.update(relatePm);
									totalDao.save(pcx);

								}
								if (pm.getOrderManager().getEp_statuts() != null
										&& pm.getOrderManager().getEp_statuts()
												.equals("同意")) {
									pcx.setStatus("同意");
									totalDao.update(pcx);
									// 备货订单关联的内部计划明细
									List<InternalOrderDetail> idetailList = totalDao
											.query(
													"from InternalOrderDetail where id in(select ioDetailId from Product_Internal where productId=?) and (cxCount is null or num>cxCount)",
													relatePm.getId());
									if (pm.getHasTurn() == null) {
										pm.setHasTurn(0f);
									}
									if (relatePm.getHasTurn().floatValue() > relatePm
											.getCxCount().floatValue()) {// 结余已转数量为本次的冲销已转数量
										if ((relatePm.getHasTurn().floatValue() - relatePm
												.getCxCount().floatValue()) > thisCxCount
												.floatValue()) {// 超过该备货订单此次用以冲销数量
											pm.setHasTurn(pm.getHasTurn()
													+ thisCxCount);
										} else {
											pm.setHasTurn(pm.getHasTurn()
													+ (pm.getHasTurn() - pm
															.getCxCount()));
										}
									}
									relatePm.setCxCount(relatePm.getCxCount()
											+ thisCxCount);
									relatePm.setCxApplyCount(relatePm
											.getCxApplyCount()
											- thisCxCount);
									pm
											.setCxCount(pm.getCxCount()
													+ thisCxCount);
									pm.setCxApplyCount(pm.getCxApplyCount()
											- thisCxCount);
									totalDao.update(relatePm);
									if (idetailList != null
											&& idetailList.size() > 0) {// 计算allocationsNum
										for (InternalOrderDetail idetail : idetailList) {
											InternalZsAboutBh izsaboutbh = new InternalZsAboutBh();
											izsaboutbh.setPzsAboutbhId(pcx
													.getId());// 订单冲销对应表Id
											izsaboutbh.setIdetailId(idetail
													.getId());// 内部计划Id
											izsaboutbh.setMarkId(pcx
													.getMarkId());// 件号
											izsaboutbh.setYwMakrId(pcx
													.getYwMarkId());// 业务件号
											if (idetail.getCxCount() == null) {
												idetail.setCxCount(0f);
											}
											Float thisdCxCount = 0f;// 此内部计划冲销数量
											if ((idetail.getNum().floatValue() - idetail
													.getCxCount().floatValue()) > thisCxCount
													.floatValue()) {// 足够冲销
												thisdCxCount = thisCxCount;
												thisCxCount = 0f;
											} else {
												thisdCxCount = idetail.getNum()
														- idetail.getCxCount();
												thisCxCount = thisCxCount
														- (idetail.getNum() - idetail
																.getCxCount());
											}
											if (pm.getCxRukuCount() == null) {
												pm.setCxRukuCount(0f);
											}
											// 冲销转库之后在修改正式的入库数量
											// if
											// (idetail.getQuantityCompletion()
											// > idetail
											// .getCxCount()) {//
											// 结余入库数量为本次的冲销入库数量
											// if ((idetail
											// .getQuantityCompletion() -
											// idetail
											// .getCxCount()) > thisdCxCount) {
											// pm.setCxRukuCount(pm
											// .getCxRukuCount()
											// + thisdCxCount);
											// pm
											// .setAllocationsNum(pm
											// .getAllocationsNum()
											// + thisdCxCount);
											// } else {
											// pm
											// .setCxRukuCount(pm
											// .getCxRukuCount()
											// + (idetail
											// .getQuantityCompletion() -
											// idetail
											// .getCxCount()));
											// pm
											// .setAllocationsNum(pm
											// .getAllocationsNum()
											// + (idetail
											// .getQuantityCompletion() -
											// idetail
											// .getCxCount()));
											// }
											// // 更新订单入库率
											// OrderManager or = pm
											// .getOrderManager();
											// Integer orderId = or.getId();
											// String hql5 =
											// "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
											// List list = totalDao.query(
											// hql5, orderId);
											// if (list != null
											// && list.size() > 0
											// && list.get(0) != null) {
											// String hql6 =
											// "select sum(sellCount) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
											// String hql7 =
											// "select sum(timeNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
											// Float pronum = (Float) list
											// .get(0);
											// Float proallocationsNum = (Float)
											// totalDao
											// .getObjectByCondition(
											// hql6,
											// orderId);
											// Float timeNum = (Float) totalDao
											// .getObjectByCondition(
											// hql7,
											// orderId);
											// if (proallocationsNum == null) {
											// proallocationsNum = 0f;
											// }
											// if (timeNum == null) {
											// timeNum = 0f;
											// }
											// if (pronum == null
											// || pronum <= 0) {
											// or
											// .setCompletionrate(0F);
											// } else {
											// or
											// .setCompletionrate(proallocationsNum
											// / pronum
											// * 100);
											// or.setTimeRate(timeNum
											// / pronum * 100);
											// }
											// if (pronum == 0
											// || pronum == null) {
											// or
											// .setCompletionrate(0F);
											// or.setTimeRate(0F);
											// } else {
											// or
											// .setCompletionrate(proallocationsNum
											// / pronum
											// * 100);
											// or.setTimeRate(timeNum
											// / pronum * 100);
											// }
											//
											// } else {
											// or.setCompletionrate(0F);
											// }
											// if (or.getCompletionrate() > 0) {
											// totalDao.update(or);
											// }
											// }
											idetail.setCxCount(idetail
													.getCxCount()
													+ thisdCxCount);
											izsaboutbh.setCount(thisdCxCount);// 数量
											totalDao.update(idetail);
											totalDao.save(izsaboutbh);
											if (thisCxCount == 0) {
												break;
											}
										}
									}

								}
								if (pm.getNum().floatValue() == (pm
										.getCxApplyCount().floatValue() + pm
										.getCxCount().floatValue())) {
									pm.setStatus("全部冲销");
								}
								totalDao.update(pm);
								if (num == 0) {
									break;
								}
							}
						}

					}
				} else {
					return "请选择要关联的订单!";
				}
			}
		} else {
			return "没有找到对应的正式订单!";
		}
		return "true";
	}

	@Override
	public ProductManager updatejq(Integer id, String paymentDate) {
		if (id != null) {
			ProductManager product = (ProductManager) totalDao.get(
					ProductManager.class, id);
			product.setPaymentDate(paymentDate);
			if (totalDao.update(product)) {
				return product;
			}
		}
		return null;
	}

	@Override
	public void exprot(Map map, String flag, String status) {
		String hql = " from OrderManager where 1=1 ";
		if (map != null) {
			if (map.get("orderNum") != null) {
				hql += " and( orderNum like '%" + map.get("orderNum")
						+ "%' or outOrderNumber like '%" + map.get("orderNum")
						+ "%')";
			}
			if (map.get("customeId") != null) {
				hql += " and custome.id =  " + map.get("customeId");
			}
			if (map.get("documentaryPeople") != null) {
				hql += " and documentaryPeople like '%"
						+ map.get("documentaryPeople") + "%'";
			}
			if (map.get("paymentDate") != null) {
				hql += " and paymentDate like  '%" + map.get("paymentDate") + "%'";
			}
			if (map.get("beginTime") != null) {
				hql += " and addTime >= '" + map.get("beginTime") + "'";
			}
			if (map.get("endTime") != null) {
				hql += " and addTime <= '" + map.get("endTime") + "'";
			}
			if (map.get("markId") != null) {
				hql += " and id in (select orderManager.id from ProductManager "
						+ " where pieceNumber like '%"
						+ map.get("markId")
						+ "%') or ywMarkId like '%" + map.get("markId") + "%'";
			}
			if (map.get("orderType") != null) {
				hql += " and orderType = '" + map.get("orderType") + "'";
			}
		}
		if ("sz".equals(status)) {
			hql += " and producttype = '试制'";
		} else {
			hql += " and (producttype = '批产' or producttype is null or producttype = '')";
		}

		List<OrderManager> orderList = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("订单信息".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("申请外购件采购明细", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 38);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "订单号(内部)"));
			ws.addCell(new Label(2, 0, "订单号(外部)"));
			ws.addCell(new Label(3, 0, "订单类型"));
			ws.addCell(new Label(4, 0, "生产类型"));
			ws.addCell(new Label(5, 0, "客户"));
			ws.addCell(new Label(6, 0, "件号"));
			ws.addCell(new Label(7, 0, "业务件号"));
			ws.addCell(new Label(8, 0, "订单数量"));
			ws.addCell(new Label(9, 0, "转换数量"));
			ws.addCell(new Label(10, 0, "入库数量"));
			ws.addCell(new Label(11, 0, "出库数量"));
			ws.addCell(new Label(12, 0, "申请开票数量"));
			ws.addCell(new Label(13, 0, "下单人"));
			ws.addCell(new Label(14, 0, "跟单人"));
			ws.addCell(new Label(15, 0, "单价(含税)"));
			ws.addCell(new Label(16, 0, "单价(不含税)"));
			ws.addCell(new Label(17, 0, "税率"));
			ws.addCell(new Label(18, 0, "总价(含税)"));
			ws.addCell(new Label(19, 0, "状态"));
			ws.addCell(new Label(20, 0, "下单日期"));
			ws.addCell(new Label(21, 0, "交付日期"));
			int count = 0;
			for (int i = 0; i < orderList.size(); i++) {
				OrderManager order = (OrderManager) orderList.get(i);
				List<ProductManager> productList = totalDao.query(
						" from ProductManager where orderManager.id =? ", order
								.getId());
				if (productList != null && productList.size() > 0) {
					for (ProductManager product : productList) {
						ws.addCell(new Label(0, count + 1, count + 1 + ""));
						ws
								.addCell(new Label(1, count + 1, order
										.getOrderNum()));
						ws.addCell(new Label(2, count + 1, order
								.getOutOrderNumber()));
						ws
								.addCell(new Label(3, count + 1, order
										.getOrderType()));
						ws.addCell(new Label(4, count + 1, order
								.getProducttype()));
						ws.addCell(new Label(5, count + 1, order
								.getClientName()));
						ws.addCell(new Label(6, count + 1, product
								.getPieceNumber()));
						ws.addCell(new Label(7, count + 1, product
								.getYwMarkId()));
						ws.addCell(new jxl.write.Number(8, count + 1, product
								.getNum()));
						ws.addCell(new jxl.write.Number(9, count + 1, product
								.getHasTurn() == null ? 0 : product
								.getHasTurn()));
						ws.addCell(new jxl.write.Number(10, count + 1, product
								.getAllocationsNum() == null ? 0 : product
								.getAllocationsNum()));
						ws.addCell(new jxl.write.Number(11, count + 1, product
								.getSellCount() == null ? 0 : product
								.getSellCount()));
						ws.addCell(new jxl.write.Number(12, count + 1, product
								.getApplyNumber() == null ? 0 : product
								.getApplyNumber()));
						ws.addCell(new Label(13, count + 1, order
								.getBillingPeople()));
						ws.addCell(new Label(14, count + 1, order
								.getDocumentaryPeople()));
						ws.addCell(new jxl.write.Number(15, count + 1, product
								.getUnit() == null ? 0 : product.getUnit()));
						ws.addCell(new jxl.write.Number(16, count + 1, product
								.getBhsPrice() == null ? 0 : product
								.getBhsPrice()));
						ws.addCell(new jxl.write.Number(17, count + 1, product
								.getTaxprice() == null ? 0 : product
								.getTaxprice()));
						ws.addCell(new jxl.write.Number(18, count + 1, product
								.getUnitPrice() == null ? 0 : product
								.getUnitPrice()));
						ws
								.addCell(new Label(19, count + 1, product
										.getStatus()));
						ws
								.addCell(new Label(20, count + 1, order
										.getAddTime()));
						ws.addCell(new Label(21, count + 1, order
								.getPaymentDate()));
						count++;
					}
				}
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String JiuzhengMarkId(Integer id) {
		ProductManager product = (ProductManager) totalDao.get(
				ProductManager.class, id);
		OrderManager order = product.getOrderManager();
		if (order != null) {
			if (order.getProducttype() == null) {
				order.setProducttype("批产");
			}
			ProcardTemplate procardT = (ProcardTemplate) totalDao
					.getObjectByCondition(
							" from ProcardTemplate where 1=1 and( ywmarkId = ? or ywmarkId = ? ) and productStyle =? and procardStyle = '总成'",
							product.getPieceNumber(), product.getYwMarkId(),order.getProducttype());
			if (procardT != null) {
				product.setPieceNumber(procardT.getMarkId());
				product.setYwMarkId(procardT.getYwMarkId());
				totalDao.update(product);
				InternalOrderDetail intrerdetail = (InternalOrderDetail) totalDao
						.getObjectByCondition(
								" from InternalOrderDetail where productManagerId = ? ",
								id);
				if (intrerdetail != null) {
					intrerdetail.setPieceNumber(procardT.getMarkId());
					intrerdetail.setYwMarkId(procardT.getYwMarkId());
					totalDao.update(intrerdetail);
				}
				return "纠正成功!";
			}
		}

		return "纠正失败";
	}

	@Override
	public String fansheng(Integer id) {
		// TODO Auto-generated method stub
		OrderManager manager = (OrderManager) totalDao.getObjectById(
				OrderManager.class, id);
		if (manager != null) {
			if (!manager.getEp_statuts().equals("未申请")) {
				manager.setEp_statuts("未申请");
				CircuitRunServerImpl.deleteCircuitRun(manager.getEpId());
				manager.setEpId(null);
				// 更新内部计划为未申请
				Set<InternalOrder> innerOrders = manager.getInnerOrders();
				for (InternalOrder internalOrder : innerOrders) {
					internalOrder.setStatus("未申请");
					totalDao.update(internalOrder);
				}
				totalDao.update(manager);
			}
		}
		return "反审成功！";
	}

	@Override
	public String removeSqProduct(Integer id,Float qxNum) {
		Users user =	Util.getLoginUser() ;
		if(user ==null){
			return "请先登录!~";
		}
		if(id!=null){
			ProductManager product =(ProductManager) totalDao.get(ProductManager.class, id);
			if(qxNum == null || qxNum ==0){
				product.setQxNum(product.getNum());
			}else if(qxNum>product.getNum()){
				return "取消数量不能大于订单数量!~";
			}else{
				product.setQxNum(qxNum);
			}
			 String processName = "订单取消申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							ProductManager.class, product.getId(), "qx_epstatus", "id",
							"orderManager_queryDetail.action?id="+product.getOrderManager().getId()
									,user.getDept() + "部门 "
									+ user.getName() + "订单取消申请，请您审批", true);
					if (epId != null && epId > 0) {
						product.setQx_epId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							product.setQx_epstatus("完成");
						} else {
							product.setQx_epstatus("未审批");
						}
						return totalDao.update(product)+"";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return "error";
	}

	@Override
	public String addEvaluators(Evaluators evaluators) {
		if(evaluators!=null){
			if(evaluators.getUserId()!=null){
				Users user =(Users) totalDao.get(Users.class, evaluators.getUserId());
				evaluators.setUserName(user.getName());
				evaluators.setUsersCode(user.getCode());
				evaluators.setDept(user.getDept());
			}
			return 	totalDao.save(evaluators)+"";
		}
		return null;
	}

	@Override
	public String delEvaluators(Evaluators evaluators) {
		if(evaluators!=null){
			return	totalDao.delete(evaluators)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllEvaluators(Evaluators evaluators, Integer pageSize,
			Integer pageNo, String status) {
		if(evaluators == null){
			evaluators = new Evaluators();
		}
		String hql = totalDao.criteriaQueries(evaluators, null);
		List<Evaluators> evalList =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int  count = totalDao.getCount(hql);
		return new Object[]{evalList,count};
	}

	@Override
	public String updateEvaluators(Evaluators evaluators) {
		if(evaluators!=null){
			Evaluators old =(Evaluators) totalDao.get(Evaluators.class, evaluators.getId());
			old.setGroups(evaluators.getGroups());
			old.setName(evaluators.getName());
			if(evaluators.getUserId()!=null){
				Users user =(Users) totalDao.get(Users.class, evaluators.getUserId());
				old.setUserName(user.getName());
				old.setUsersCode(user.getCode());
				old.setDept(user.getDept());
				old.setUserId(user.getId());
			}
			return	totalDao.update(old)+"";
		}
		return null;
	}

	@Override
	public Evaluators findEvaluatorsById(Integer id) {
		if(id!=null){
			return (Evaluators) totalDao.get(Evaluators.class, id);
		}
		return null;
	}

	@Override
	public Object[] findAllZTProcard(Procard procard, Integer pageNo,
			Integer pageSize, String status) {
		if(procard == null){
			procard = new Procard();
		}
		String hql =totalDao.criteriaQueries(procard, null);
		hql+=" and status = '暂停' and procardStyle = '总成'";
		List<Procard> procardList =	totalDao.findAllByPage(hql, pageNo, pageSize,null);
		int count =totalDao.getCount(hql);
		return new Object[]{procardList,count};
	}

	@Override
	public String saveProcardPingSheng(Integer id) {
		ProductManager  product =(ProductManager) totalDao.get(ProductManager.class, id);
		List<Procard> procardList =	totalDao.query(" from Procard where ywmarkId =? and orderNumber =?", product.getYwMarkId(),product.getOrderManager().getOrderNum());
		Float num = product.getQxNum();
		for (Procard procard : procardList) {
			Float qxNum = 0f;
			if("总成".equals(procard.getProcardStyle())){
				qxNum = num;
			}else{
				qxNum = getqxNum(procard, num, procard.getBelongLayer());
			}
		}
		return null;
	}

	private Float getqxNum(Procard sonProcardT, Float num, Integer belongLayer) {
		Float qxNum = 0f;
		if ("外购".equals(sonProcardT.getProcardStyle())) {
			qxNum = (sonProcardT.getQuanzi2() / sonProcardT.getQuanzi1()) * num;
		} else {
			Float corrCount = sonProcardT.getCorrCount();
			if (corrCount == null) {
				corrCount = 1f;
			}
			qxNum = corrCount * num;
		}
		if (sonProcardT.getBelongLayer() <= belongLayer) {
			Procard fatherProcardT = (Procard) totalDao.get(Procard.class,
					sonProcardT.getFatherId());
			qxNum = getqxNum(fatherProcardT, qxNum, belongLayer);
		}
		return qxNum;
	}

}