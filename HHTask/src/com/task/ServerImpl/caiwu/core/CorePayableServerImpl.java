package com.task.ServerImpl.caiwu.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.caiwu.core.CorePayableServer;
import com.task.Server.caiwu.receivePayment.ReceiptServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.ShortMessageServiceImpl;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.Sell;
import com.task.entity.Users;
import com.task.entity.caiwu.CwUseDetail;
import com.task.entity.caiwu.CwVouchers;
import com.task.entity.caiwu.CwVouchersDetail;
import com.task.entity.caiwu.core.CorePayable;
import com.task.entity.caiwu.core.CorePayableMonth;
import com.task.entity.caiwu.core.MonthPayableBill;
import com.task.entity.caiwu.core.SupplierCorePayable;
import com.task.entity.caiwu.receivePayment.Receipt;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.payment.FundApply;
import com.task.entity.payment.FundApplyDetailed;
import com.task.entity.paymonth.PayMonth;
import com.task.entity.paymonth.PayMonthDetail;
import com.task.entity.sop.ReturnsDetails;
import com.task.entity.sop.WaigouDeliveryDetail;
import com.task.entity.sop.WaigouOrder;
import com.task.util.NumberToCN;
import com.task.util.Upload;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ChargebackNotice;
import com.tast.entity.zhaobiao.PrepaymentsApplication;
import com.tast.entity.zhaobiao.ZhUser;

/***
 * 主营应付实现类
 * 
 * @author liupei
 * 
 */
@SuppressWarnings("unchecked")
public class CorePayableServerImpl implements CorePayableServer {

	private TotalDao totalDao;
	private ReceiptServer receiptServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public ReceiptServer getReceiptServer() {
		return receiptServer;
	}

	public void setReceiptServer(ReceiptServer receiptServer) {
		this.receiptServer = receiptServer;
	}

	/****
	 * 添加应付 （采购订单）
	 * 
	 * @param detail
	 * @param goods
	 * @return
	 */
	@Override
	public boolean addCorePayable(WaigouDeliveryDetail detail, Goods goods) {
		if (detail != null && "入库".equals(detail.getStatus()) || "退货".equals(detail.getStatus())) {
			/******************* 生成供应商月度对账单 ***********************/
			String jzMonth = Util.getDateTime("yyyy-MM");
			String hql_month = "from CorePayableMonth where jzMonth=? and supplierName=?";
			CorePayableMonth corePayableMonth = (CorePayableMonth) totalDao
					.getObjectByCondition(hql_month, jzMonth, detail
							.getGysName());
			if (corePayableMonth == null) {
				corePayableMonth = saveCpam(detail, jzMonth);
			} else {
				corePayableMonth.setYingfukuanJine(corePayableMonth
						.getYingfukuanJine()
						+ detail.getHsPrice() * detail.getHgNumber());
				corePayableMonth.setRealfukuanJine(0d);
				corePayableMonth.setWeifukuanJine(corePayableMonth
						.getWeifukuanJine()
						+ detail.getHsPrice() * detail.getHgNumber());
				totalDao.update(corePayableMonth);
			}

			/******************* 入库---对账明细 ***********************/
			CorePayable corePayable = saveCpa(detail, goods, corePayableMonth);

			try {
				/************* 生成暂估凭证凭证 *****************/
				Double zgBhsMoney = corePayable.getZgbhsPrice()
						* corePayable.getNumber();

				CwVouchers cwVouchers = new CwVouchers();
				// 生成编号
				String num = cwVcNumber();
				cwVouchers.setNumber(num);
				cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
				cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
				cwVouchers.setCreatetime(Util.getDateTime());
				cwVouchers.setUserName(Util.getLoginUser().getName());
				cwVouchers.setCreateCode(Util.getLoginUser().getCode());
				cwVouchers.setCreateName(Util.getLoginUser().getName());
				cwVouchers.setJieMoney(zgBhsMoney);
				cwVouchers.setDaiMoney(zgBhsMoney);

				Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

				// *******************凭证分录 --贷方(银行存款/库存现金)********************
				CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
				cwVouchersDetail_dai.setvClass("转");
				cwVouchersDetail_dai
						.setRemark("暂估-" + corePayable.getZhaiyao());

				// 明细科目需根据科目矩阵图查询
				SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='应付账款'");

				// 查找一级科目
				SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate
								.getRootId());
				cwVouchersDetail_dai.setSub(oneLevelsub.getName());
				cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

				// 明细科目
				cwVouchersDetail_dai.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate.getId()));
				cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
				cwVouchersDetail_dai.setJieMoney(0d);
				cwVouchersDetail_dai.setDaiMoney(zgBhsMoney);
				cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
				cwVouchersDetail_dai.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_dai.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_dai.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_dai);

				// ***************** 凭证分录 --借方(各种科目)*********************
				SubBudgetRate subBudgetRate_jie = null;
				CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

				subBudgetRate_jie = qichuZhuCL();

				CwUseDetail cwUseDetail = saveCwUserDetail(corePayable);
				cwUseDetail.setUsemoney(zgBhsMoney);
				cwUseDetail.setPayType("入库暂估");
				cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
				cwUseDetailSet.add(cwUseDetail);

				cwVouchersDetail_jie
						.setRemark("暂估-" + corePayable.getZhaiyao());
				cwVouchersDetail_jie.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate_jie
								.getRootId());
				cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
				cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
				// 明细科目
				cwVouchersDetail_jie.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate_jie.getId()));
				cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

				cwVouchersDetail_jie.setJieMoney(zgBhsMoney);
				cwVouchersDetail_jie.setDaiMoney(0d);
				cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_jie.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
				cwVouchersDetail_jie.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_jie);

				cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
				totalDao.save(cwVouchers);

				/************* 更新科目余额 *****************/
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
						zgBhsMoney.doubleValue(), 0D);
				// 更新贷方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
						zgBhsMoney.doubleValue());
			} catch (Exception e) {
				//e.printStackTrace();
			}

		}
		return false;
	}

	/**
	 * 财务凭证编号
	 * 
	 * @return
	 */
	private String cwVcNumber() {
		String num = "P" + Util.getDateTime("yyyyMM");
		String hql_finMaxnum = "select max(number) from CwVouchers where number like '%"
				+ num + "%'";
		String maxfkNumber = (String) totalDao
				.getObjectByCondition(hql_finMaxnum);
		if (maxfkNumber != null && maxfkNumber.length() > 0) {
			String subnum = "";
			Integer maxnum = Integer.parseInt(maxfkNumber.substring(7,
					maxfkNumber.length())) + 1;
			if (maxnum < 10) {
				subnum += "0000" + maxnum;// 00009
			} else if (maxnum < 100) {
				subnum += "000" + maxnum;// 00099
			} else if (maxnum < 1000) {
				subnum += "00" + maxnum;// 00999
			} else if (maxnum < 10000) {
				subnum += "0" + maxnum;// 09999
			} else {
				subnum += "" + maxnum;
			}
			num += subnum;
		} else {
			num += "00001";
		}
		return num;
	}

	/**
	 * 添加主营业务应付
	 * 
	 * @param detail
	 * @param goods
	 * @param corePayableMonth
	 * @return
	 */
	private CorePayable saveCpa(WaigouDeliveryDetail detail, Goods goods,
			CorePayableMonth corePayableMonth) {
		CorePayable corePayable = new CorePayable();
		corePayable.setFk_CPMId(corePayableMonth.getId());

		corePayable.setSubjectItem(detail.getType());
		corePayable.setCoreType("主营");

		corePayable.setZhaiyao(detail.getMarkId());
		corePayable.setNumber(detail.getYrukuNum());
		corePayable.setUnit(detail.getUnit());

		corePayable.setHsPrice(detail.getHsPrice());
		corePayable.setBhsPrice(detail.getPrice());
		corePayable.setZgbhsPrice(detail.getPrice());// 暂估价格 用于计算成本差异
		corePayable.setPriceId(detail.getPriceId());
		if (detail.getTaxprice() == null) {
			corePayable.setTaxprice(16f);
		} else {
			corePayable.setTaxprice(detail.getTaxprice().floatValue());
		}
		// corePayable.setHetongbianhao(detail.get);价格合同编号

		corePayable.setYingfukuanJine(detail.getHsPrice()* detail.getHgNumber());// 应付款金额

		corePayable.setYipizhunJine(0d);
		corePayable.setRealfukuanJine(0d);
		corePayable.setSaveTime(Util.getDateTime());
		corePayable.setSaveUser(Util.getLoginUser().getName());
		corePayable.setJzMonth(Util.getDateTime("yyyy-MM"));// 需要计算账期
		corePayable.setDemanddept(detail.getDemanddept());//需求部门
		
		WaigouOrder wgorder =	(WaigouOrder) totalDao.getObjectByCondition(" from WaigouOrder where planNumber =? ", detail.getCgOrderNum());
		Integer fukuanZq  = 90;
		String strfukuanZq = "";
		if(wgorder!=null){
			String str = wgorder.getPayType().replaceAll("[^x00-xff]*", "");
			try {
				fukuanZq = Integer.parseInt(str);
				strfukuanZq = fukuanZq+"";
			} catch (Exception e) {
				e.printStackTrace();
				strfukuanZq =str;
			}
		}
		corePayable.setFukuanZq(strfukuanZq);// 需从供应商获取
		
		
		// try {
		// corePayable.setFukuanDate(Util.DateToString(Util
		// .getCalendarDate(new Date(), 90), null));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		corePayable.setFuzeren(detail.getCaigouUserName());
		corePayable.setStatus("对账");
		corePayable.setOrderNumber(detail.getCgOrderNum());
		corePayable.setOrderId(detail.getWaigouPlanId());
		corePayable.setDeliveryNumber(detail.getWaigouDelivery()
				.getPlanNumber());
		corePayable.setDeliveryId(detail.getId());
		corePayable.setSupplierName(detail.getGysName());
		corePayable.setSupplierId(detail.getGysId());
		corePayable.setMarkId(detail.getMarkId());
		corePayable.setShDte(detail.getQuerenTime());
		corePayable.setRukuNumber(detail.getPrintNumber());
		corePayable.setProName(detail.getProName());
		corePayable.setSpecification(detail.getSpecification());
		corePayable.setShNumber(detail.getShNumber());
		corePayable.setKgliao(detail.getKgliao());
		corePayable.setYwmarkId(detail.getYwmarkId());
		corePayable.setCgOrderType(detail.getWwType());
		if(goods!=null){
			corePayable.setLot(goods.getGoodsLotId());
			corePayable.setGoodsId(goods.getGoodsId());
			corePayable.setCangqu(goods.getGoodHouseName());
		}
		totalDao.save(corePayable);
		return corePayable;
	}

	/**
	 * 生成供应商月度对账单
	 * 
	 * @param detail
	 * @param jzMonth
	 * @return
	 */
	private CorePayableMonth saveCpam(WaigouDeliveryDetail detail,
			String jzMonth) {
		CorePayableMonth corePayableMonth;
		String num = yueDuNumber();
		corePayableMonth = new CorePayableMonth();
		corePayableMonth.setSupplierName(detail.getGysName());
		corePayableMonth.setSupplierId(detail.getGysId());
		corePayableMonth.setRecNumber(num);
		corePayableMonth.setYufukuanJine(0F);
		corePayableMonth.setYingfukuanJine(detail.getHsPrice()
				* detail.getHgNumber());
		corePayableMonth.setRealfukuanJine(0d);
		corePayableMonth.setWeifukuanJine(detail.getHsPrice()
				* detail.getHgNumber());
		corePayableMonth.setJzMonth(jzMonth);
		corePayableMonth.setFukuanZq(90);
		corePayableMonth.setFukuanDate("");
		corePayableMonth.setLateDate("");
		corePayableMonth.setStatus("对账");
		corePayableMonth.setSaveTime(Util.getDateTime());
		corePayableMonth.setSaveUser(Util.getLoginUser().getName());
		corePayableMonth.setAuditStatus("同意");
		totalDao.save(corePayableMonth);
		return corePayableMonth;
	}

	/****
	 * 添加应付 （入库导入）
	 * 
	 * @param goodsStore
	 * @return
	 */
	@Override
	public boolean addCorePayable(GoodsStore goodsStore) {
		if (goodsStore != null && "入库".equals(goodsStore.getStatus())) {
			/******************* 生成供应商月度对账单 ***********************/
			 String jzMonth = Util.getDateTime("yyyy-MM");
//			String jzMonth = "2018-03";
			ZhUser zhUser = null;
			if (goodsStore.getGoodsStoreSupplier() != null
					&& goodsStore.getGoodsStoreSupplier().length() > 0) {
				String hql_zhuser = "from ZhUser where usercode=? or cmp=?";
				zhUser = (ZhUser) totalDao.getObjectByCondition(hql_zhuser,
						goodsStore.getGoodsStoreSupplier(), goodsStore
								.getGoodsStoreSupplier());
			}
			if (zhUser == null) {
				return false;
			}

			String hql_month = "from CorePayableMonth where jzMonth=? and supplierName=?";
			CorePayableMonth corePayableMonth = (CorePayableMonth) totalDao
					.getObjectByCondition(hql_month, jzMonth, zhUser.getCmp());
			Double yingfu = 0d;
			try {
				if (goodsStore.getHsPrice() == null) {
					goodsStore.setHsPrice(0D);
				}
				if (goodsStore.getGoodsStoreCount() == null) {
					goodsStore.setGoodsStoreCount(0F);
				}
				yingfu = goodsStore.getHsPrice()
						* goodsStore.getGoodsStoreCount();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (corePayableMonth == null) {
				String num = yueDuNumber();
				corePayableMonth = new CorePayableMonth();
				corePayableMonth.setSupplierName(goodsStore
						.getGoodsStoreSupplier());
				Integer supplierId = (Integer) totalDao.getObjectByCondition(
						"select id from ZhUser where cmp=?", goodsStore
								.getGoodsStoreSupplier());
				if (supplierId != null) {
					corePayableMonth.setSupplierId(supplierId);
				}
				corePayableMonth.setRecNumber(num);
				corePayableMonth.setYufukuanJine(0F);
				corePayableMonth.setYingfukuanJine(yingfu);
				corePayableMonth.setRealfukuanJine(0d);
				corePayableMonth.setWeifukuanJine(yingfu);
				corePayableMonth.setJzMonth(jzMonth);
				corePayableMonth.setFukuanZq(90);
				corePayableMonth.setFukuanDate("");
				corePayableMonth.setLateDate("");
				corePayableMonth.setStatus("可开票");
				corePayableMonth.setSaveTime(Util.getDateTime());
				corePayableMonth.setSaveUser(Util.getLoginUser().getName());
				corePayableMonth.setAuditStatus("同意");
				totalDao.save(corePayableMonth);
			} else {
				corePayableMonth.setYingfukuanJine(corePayableMonth
						.getYingfukuanJine()
						+ yingfu);
				corePayableMonth.setRealfukuanJine(0d);
				corePayableMonth.setWeifukuanJine(corePayableMonth
						.getWeifukuanJine()
						+ yingfu);
				totalDao.update(corePayableMonth);
			}

			/******************* 入库---对账明细 ***********************/
			String hql_dai = "";
			String subjectItem = "";
			if ("外购件库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "研发库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "售后库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_dai = "from SubBudgetRate where name='应付账款'";
				subjectItem = "原材料";
			} else if ("成品库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_dai = "from SubBudgetRate where name='制造费用'";
				subjectItem = "成品";
			}

			CorePayable corePayable = new CorePayable();
			corePayable.setFk_CPMId(corePayableMonth.getId());

			corePayable.setSubjectItem(subjectItem);
			corePayable.setCoreType("主营");

			corePayable.setZhaiyao(goodsStore.getGoodsStoreMarkId());
			corePayable.setNumber(goodsStore.getGoodsStoreCount());
			corePayable.setUnit(goodsStore.getGoodsStoreUnit());

			corePayable.setHsPrice((double)goodsStore.getHsPrice().floatValue());
			corePayable.setBhsPrice((double)goodsStore.getGoodsStorePrice().floatValue());
			corePayable.setZgbhsPrice(goodsStore.getHsPrice());// 暂估价格 用于计算成本差异
			if (goodsStore.getTaxprice() == null) {
				corePayable.setTaxprice(0f);
			} else {
				corePayable.setTaxprice(goodsStore.getTaxprice().floatValue());
			}

			corePayable.setYingfukuanJine(yingfu);// 应付款金额

			corePayable.setYipizhunJine(0d);
			corePayable.setRealfukuanJine(0d);
			corePayable.setSaveTime(Util.getDateTime());
			corePayable.setSaveUser(Util.getLoginUser().getName());
			corePayable.setJzMonth(Util.getDateTime("yyyy-MM"));// 需要计算账期

			corePayable.setFukuanZq("");// 需从供应商获取
			// try {
			// corePayable.setFukuanDate(Util.DateToString(Util
			// .getCalendarDate(new Date(), 90), null));
			// } catch (ParseException e) {
			// e.printStackTrace();
			// }
			corePayable.setFuzeren(Util.getLoginUser().getName());
			corePayable.setStatus("可开票");
			corePayable.setSupplierName(goodsStore.getGoodsStoreSupplier());
			corePayable.setSupplierId(corePayableMonth.getSupplierId());
			corePayable.setMarkId(goodsStore.getGoodsStoreMarkId());
			corePayable.setLot(goodsStore.getGoodsStoreLot());

			if (goodsStore.getGoodsStoreMarkId() != null
					&& goodsStore.getGoodsStoreLot() != null) {
				WaigouDeliveryDetail detail = (WaigouDeliveryDetail) totalDao
						.getObjectByCondition(
								"from WaigouDeliveryDetail where "
										+ "markId = ? and examineLot = ?",
								goodsStore.getGoodsStoreMarkId(), goodsStore
										.getGoodsStoreLot());
				if (detail != null) {
					corePayable.setOrderNumber(detail.getCgOrderNum());
					corePayable.setOrderId(detail.getWaigouPlanId());
					corePayable.setDeliveryNumber(detail.getWaigouDelivery()
							.getPlanNumber());
					corePayable.setDeliveryId(detail.getId());
				}
			}
			totalDao.save(corePayable);

			pinzhengShengcheng(goodsStore, hql_dai, corePayable);

		}
		return false;
	}

	@Override
	public boolean zhuyinPingzheng(CorePayable corePayable) {
		/******************* 入库---对账明细 ***********************/
		GoodsStore goodsStore = (GoodsStore) totalDao
				.getObjectByCondition(
						"from GoodsStore where "
								+ "goodsStoreMarkId = ? and goodsStoreLot = ? and goodsStoreWarehouse in ('外购件库','研发库')",
						corePayable.getMarkId(), corePayable.getLot());

		String hql_dai = "";
		String subjectItem = "";
		if (goodsStore != null) {
			if ("外购件库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "研发库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "售后库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_dai = "from SubBudgetRate where name='应付账款'";
				subjectItem = "原材料";
			} else if ("成品库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_dai = "from SubBudgetRate where name='制造费用'";
				subjectItem = "成品";
			}
			pinzhengShengcheng(goodsStore, hql_dai, corePayable);
		}
		return true;
	}

	/**
	 * 导入生成凭证
	 * 
	 * @param goodsStore
	 * @param hql_dai
	 * @param corePayable
	 */
	private void pinzhengShengcheng(GoodsStore goodsStore, String hql_dai,
			CorePayable corePayable) {
		String subjectItem;
		try {
			/************* 生成凭证 *****************/
			Double zghsMoney = corePayable.getHsPrice()
					* corePayable.getNumber();
			Double bhsMoney = corePayable.getBhsPrice()
					* corePayable.getNumber();
			Double shuie = bhsMoney * corePayable.getTaxprice() / 100;

			CwVouchers cwVouchers = new CwVouchers();
			String num = cwVcNumber();
			cwVouchers.setNumber(num);
			cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers.setCreatetime(Util.getDateTime());
			cwVouchers.setUserName(Util.getLoginUser().getName());
			cwVouchers.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers.setCreateName(Util.getLoginUser().getName());
			cwVouchers.setJieMoney(zghsMoney);
			cwVouchers.setDaiMoney(zghsMoney);

			Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

			// *******************凭证分录 --贷方(银行存款/库存现金)********************
			CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
			cwVouchersDetail_dai.setvClass("转");
			cwVouchersDetail_dai.setRemark(corePayable.getZhaiyao());

			// 明细科目需根据科目矩阵图查询
			SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
					.getObjectByCondition(hql_dai);

			// 查找一级科目
			SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(
					SubBudgetRate.class, subBudgetRate.getRootId());
			cwVouchersDetail_dai.setSub(oneLevelsub.getName());
			cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

			// 明细科目
			cwVouchersDetail_dai.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate.getId()));
			cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
			cwVouchersDetail_dai.setJieMoney(0d);
			cwVouchersDetail_dai.setDaiMoney(zghsMoney);
			cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
			cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_dai.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_dai);

			// ***************** 凭证分录 --借方(各种科目)*********************
			SubBudgetRate subBudgetRate_jie = null;
			CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
			Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

			String hql_jie = "";
			if ("外购件库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "研发库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "售后库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_jie = "from SubBudgetRate where name='期初原材料'";
				subjectItem = "原材料";
			} else if ("成品库".equals(goodsStore.getGoodsStoreWarehouse())) {
				hql_jie = "from SubBudgetRate where name='期初库存商品'";
				subjectItem = "成品";
			}
			subBudgetRate_jie = (SubBudgetRate) totalDao
					.getObjectByCondition(hql_jie);

			CwUseDetail cwUseDetail = saveCwUserDetail(corePayable);
			cwUseDetail.setUsemoney(bhsMoney);
			cwUseDetail.setPayType("入库");
			cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
			cwUseDetailSet.add(cwUseDetail);

			cwVouchersDetail_jie.setRemark("入库-" + corePayable.getZhaiyao());
			cwVouchersDetail_jie.setvClass("转");
			// 查找一级科目
			SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_jie
							.getRootId());
			cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
			cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
			// 明细科目
			cwVouchersDetail_jie.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_jie.getId()));
			cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

			cwVouchersDetail_jie.setJieMoney(bhsMoney);
			cwVouchersDetail_jie.setDaiMoney(0d);
			cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
			cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
			cwVouchersDetail_jie.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_jie);
			cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);

			if ("外购件库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "研发库".equals(goodsStore.getGoodsStoreWarehouse())
					|| "售后库".equals(goodsStore.getGoodsStoreWarehouse())) {
				/*************** 进项税金 **************************/
				SubBudgetRate subBudgetRate_jie_shui = null;
				CwVouchersDetail cwVouchersDetail_jie_shui = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet_shui = new HashSet<CwUseDetail>();

				subBudgetRate_jie_shui = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='进项税额'");

				// ***************** 分录--辅助明细********************
				CwUseDetail cwUseDetail_shui = new CwUseDetail();
				cwUseDetail_shui.setPayee(corePayable.getSupplierName());
				cwUseDetail_shui.setUseFor(corePayable.getZhaiyao() + "-"
						+ corePayable.getLot());
				cwUseDetail_shui.setFk_goodsStoreId(goodsStore
						.getGoodsStoreId());
				cwUseDetail_shui.setUsemoney(shuie);
				cwUseDetail_shui.setAboutNum(corePayable.getDeliveryNumber());
				cwUseDetail_shui.setFk_monthlyBillsId(corePayable.getId());
				cwUseDetail_shui.setPayNum("");
				cwUseDetail_shui.setPayType("进项税额");
				cwUseDetail_shui.setCwVouchersDetail(cwVouchersDetail_jie_shui);
				cwUseDetailSet_shui.add(cwUseDetail_shui);

				cwVouchersDetail_jie_shui.setRemark("入库-"
						+ corePayable.getZhaiyao());
				cwVouchersDetail_jie_shui.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie_shui = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class,
								subBudgetRate_jie_shui.getRootId());
				cwVouchersDetail_jie_shui
						.setSub(oneLevelsub_jie_shui.getName());
				cwVouchersDetail_jie_shui
						.setSubId(oneLevelsub_jie_shui.getId());
				// 明细科目
				cwVouchersDetail_jie_shui.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate_jie_shui.getId()));
				cwVouchersDetail_jie_shui.setDetailSubId(subBudgetRate_jie_shui
						.getId());

				cwVouchersDetail_jie_shui.setJieMoney(shuie);
				cwVouchersDetail_jie_shui.setDaiMoney(0d);
				cwVouchersDetail_jie_shui.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie_shui.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_jie_shui.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_jie_shui.setCwUseDetails(cwUseDetailSet_shui);
				cwVouchersDetail_jie_shui.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_jie_shui);
				cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
				/************* 更新科目余额 *****************/
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie_shui
						.getId(), zghsMoney.doubleValue(), 0D);
			}
			totalDao.save(cwVouchers);

			/************* 更新科目余额 *****************/
			// 更新借方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
					zghsMoney.doubleValue(), 0D);
			// 更新贷方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
					zghsMoney.doubleValue());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * 出库
	 * 
	 * @param sell
	 * @return
	 */
	@Override
	public boolean goodsSell(Sell sell) {
		String a = sell.getSellWarehouse();
		if ("外购件库".equals(a) || "研发库".equals(a) || "售后库".equals(a)
				|| "成品库".equals(a)) {
			try {
				/************* 生成凭证 *****************/
				Double zgMoney = (double)sell.getGoodsPrice() * sell.getSellCount();
				Double zgbhsMoney = 0d;
				Double shuie = 0d;

				if ("成品库".equals(sell.getSellWarehouse())) {
					try {
						zgMoney = (double)sell.getSellPrice() * sell.getSellCount();
						zgbhsMoney = (double)sell.getSellbhsPrice()
								* sell.getSellCount();
						shuie = zgbhsMoney * sell.getTaxprice();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				CwVouchers cwVouchers = new CwVouchers();
				String num1 = cwVcNumber();
				cwVouchers.setNumber(num1);
				cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
				cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
				cwVouchers.setCreatetime(Util.getDateTime());
				cwVouchers.setUserName(Util.getLoginUser().getName());
				cwVouchers.setCreateCode(Util.getLoginUser().getCode());
				cwVouchers.setCreateName(Util.getLoginUser().getName());
				cwVouchers.setJieMoney(zgMoney);
				cwVouchers.setDaiMoney(zgMoney);

				Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

				// *******************凭证分录
				// --贷方(银行存款/库存现金)********************
				CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
				cwVouchersDetail_dai.setvClass("转");
				cwVouchersDetail_dai.setRemark("出库-" + sell.getSellMarkId());
				String hql_dai = "";
				String subjectItem = "";
				if ("外购件库".equals(sell.getSellWarehouse())
						|| "研发库".equals(sell.getSellWarehouse())
						|| "售后库".equals(sell.getSellWarehouse())) {
					hql_dai = "from SubBudgetRate where name='期初原材料'";
					subjectItem = "原材料";
				} else if ("成品库".equals(sell.getSellWarehouse())) {
					hql_dai = "from SubBudgetRate where name='期初库存商品'";
					subjectItem = "成品";
				}
				// 明细科目需根据科目矩阵图查询
				SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
						.getObjectByCondition(hql_dai);

				// 查找一级科目
				SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate
								.getRootId());
				cwVouchersDetail_dai.setSub(oneLevelsub.getName());
				cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

				// 明细科目
				cwVouchersDetail_dai.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate.getId()));
				cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
				cwVouchersDetail_dai.setJieMoney(0d);
				cwVouchersDetail_dai.setDaiMoney(zgMoney);
				cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
				cwVouchersDetail_dai.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_dai.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_dai.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_dai);

				// ***************** 凭证分录
				// --借方(各种科目)*********************
				SubBudgetRate subBudgetRate_jie = null;
				CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

				String hql_jie = "";
				if ("外购件库".equals(sell.getSellWarehouse())
						|| "研发库".equals(sell.getSellWarehouse())
						|| "售后库".equals(sell.getSellWarehouse())) {
					hql_jie = "from SubBudgetRate where name='制造费用'";
					subBudgetRate_jie = (SubBudgetRate) totalDao
							.getObjectByCondition(hql_jie);
					subjectItem = "原材料";
					// 分录--辅助明细********************
					// CwUseDetail cwUseDetail = new CwUseDetail();
					// cwUseDetail.setPayee(sell.getSellCompanyName());
					// cwUseDetail.setUseFor(sell.getSellMarkId() + "-"
					// + sell.getSellLot());
					// cwUseDetail.setUsemoney(zgMoney);
					// cwUseDetail.setAboutNum(sell.getSellNumber());
					// // cwUseDetail.setFk_monthlyBillsId(corePayable.getId());
					// cwUseDetail.setPayNum("");
					// cwUseDetail.setFk_sellId(sell.getSellId());
					// cwUseDetail.setPayType("出库");
					// cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
					// cwUseDetailSet.add(cwUseDetail);
				} else if ("成品库".equals(sell.getSellWarehouse())) {
					hql_dai = "from SubBudgetRate where name='应收账款-销售收入'";
					subjectItem = "成品";
					subBudgetRate_jie = (SubBudgetRate) totalDao
							.getObjectByCondition(hql_dai);
					// *****************
					// 分录--辅助明细********************
					CwUseDetail cwUseDetail = new CwUseDetail();
					cwUseDetail.setPayee(sell.getSellCompanyName());
					cwUseDetail.setUseFor(sell.getSellMarkId() + "-"
							+ sell.getSellLot());
					cwUseDetail.setUsemoney(zgMoney);
					cwUseDetail.setAboutNum(sell.getSellNumber());
					// cwUseDetail.setFk_monthlyBillsId(corePayable.getId());
					cwUseDetail.setPayNum("");
					cwUseDetail.setFk_sellId(sell.getSellId());
					cwUseDetail.setPayType("出库");
					cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
					cwUseDetailSet.add(cwUseDetail);
				}

				cwVouchersDetail_jie.setRemark("出库-" + sell.getSellMarkId());
				cwVouchersDetail_jie.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate_jie
								.getRootId());
				cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
				cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
				// 明细科目
				cwVouchersDetail_jie.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate_jie.getId()));
				cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

				cwVouchersDetail_jie.setJieMoney(zgMoney);
				cwVouchersDetail_jie.setDaiMoney(0d);
				cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_jie.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
				cwVouchersDetail_jie.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_jie);

				cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
				// totalDao.save(cwVouchers);

				if ("成品库".equals(sell.getSellWarehouse())) {
					/*************** 销项税金 **************************/
					SubBudgetRate subBudgetRate_dai_shui = null;
					CwVouchersDetail cwVouchersDetail_jie_shui = new CwVouchersDetail();
					Set<CwUseDetail> cwUseDetailSet_shui = new HashSet<CwUseDetail>();

					subBudgetRate_dai_shui = (SubBudgetRate) totalDao
							.getObjectByCondition("from SubBudgetRate where name='销项税额'");

					cwVouchersDetail_jie_shui.setRemark("出库-"
							+ sell.getSellMarkId());
					cwVouchersDetail_jie_shui.setvClass("转");
					// 查找一级科目
					SubBudgetRate oneLevelsub_jie_shui = (SubBudgetRate) totalDao
							.getObjectById(SubBudgetRate.class,
									subBudgetRate_dai_shui.getRootId());
					cwVouchersDetail_jie_shui.setSub(oneLevelsub_jie_shui
							.getName());
					cwVouchersDetail_jie_shui.setSubId(oneLevelsub_jie_shui
							.getId());
					// 明细科目
					cwVouchersDetail_jie_shui
							.setDetailSub(receiptServer
									.findBudgetRateNames(subBudgetRate_dai_shui
											.getId()));
					cwVouchersDetail_jie_shui
							.setDetailSubId(subBudgetRate_dai_shui.getId());

					cwVouchersDetail_jie_shui.setJieMoney(shuie);
					cwVouchersDetail_jie_shui.setDaiMoney(0d);
					cwVouchersDetail_jie_shui.setCreateTime(Util.getDateTime());
					cwVouchersDetail_jie_shui.setCreateName(Util.getLoginUser()
							.getName());
					cwVouchersDetail_jie_shui.setCreateCode(Util.getLoginUser()
							.getCode());
					cwVouchersDetail_jie_shui
							.setCwUseDetails(cwUseDetailSet_shui);
					cwVouchersDetail_jie_shui.setCwVouchers(cwVouchers);
					cwVouchersDetailSet.add(cwVouchersDetail_jie_shui);

					cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
					/************* 更新科目余额 *****************/
					// 更新借方科目余额
					receiptServer.updatesubBudgetRate(subBudgetRate_dai_shui
							.getId(), zgbhsMoney.doubleValue(), 0D);
				}
				totalDao.save(cwVouchers);
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
						cwVouchersDetail_jie.getJieMoney().doubleValue(), 0D);
				// 更新贷方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
						cwVouchersDetail_dai.getDaiMoney().doubleValue());
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return true;
	}

	/***
	 * 补充生成已记账的应付的月度账单以及凭证
	 * 
	 * @return
	 */
	@Override
	public String addOldCorePayable() {
		String hql = "from CorePayable where fk_CPMId is null";
		List list = totalDao.query(hql);
		for (int i = 0; i < list.size(); i++) {
			CorePayable corePayable = (CorePayable) list.get(i);
			/******************* 生成供应商月度对账单 ***********************/
			String jzMonth = corePayable.getSaveTime().substring(0, 4)
					+ corePayable.getSaveTime().substring(5, 7);
			String hql_month = "from CorePayableMonth where jzMonth=? and supplierName=? ";
			CorePayableMonth corePayableMonth = (CorePayableMonth) totalDao
					.getObjectByCondition(hql_month, jzMonth, corePayable
							.getSupplierName());
			if (corePayableMonth == null) {
				// 生成编号
				String num = "R" + jzMonth;
				String hql_finMaxnum = "select max(recNumber) from CorePayableMonth where recNumber like '%"
						+ num + "%'";
				String maxfkNumber = (String) totalDao
						.getObjectByCondition(hql_finMaxnum);
				if (maxfkNumber != null && maxfkNumber.length() > 0) {
					String subnum = "";
					Integer maxnum = Integer.parseInt(maxfkNumber.substring(7,
							maxfkNumber.length())) + 1;
					if (maxnum < 10) {
						subnum += "0000" + maxnum;// 00009
					} else if (maxnum < 100) {
						subnum += "000" + maxnum;// 00099
					} else if (maxnum < 1000) {
						subnum += "00" + maxnum;// 00999
					} else if (maxnum < 10000) {
						subnum += "0" + maxnum;// 09999
					} else {
						subnum += "" + maxnum;
					}
					num += subnum;
				} else {
					num += "00001";
				}
				corePayableMonth = new CorePayableMonth();
				corePayableMonth.setSupplierName(corePayable.getSupplierName());
				corePayableMonth.setSupplierId(corePayable.getSupplierId());
				corePayableMonth.setRecNumber(num);
				corePayableMonth.setYufukuanJine(0F);
				corePayableMonth.setYingfukuanJine(corePayable.getHsPrice()
						* corePayable.getNumber());
				corePayableMonth.setRealfukuanJine(0d);
				corePayableMonth.setWeifukuanJine(corePayable.getHsPrice()
						* corePayable.getNumber());
				corePayableMonth.setJzMonth(jzMonth);
				corePayableMonth.setFukuanZq(90);
				corePayableMonth.setFukuanDate("");
				corePayableMonth.setLateDate("");
				corePayableMonth.setStatus("对账");
				corePayableMonth.setSaveTime(Util.getDateTime());
				corePayableMonth.setSaveUser(Util.getLoginUser().getName());
				corePayableMonth.setAuditStatus("同意");
				totalDao.save(corePayableMonth);
			} else {
				corePayableMonth.setYingfukuanJine(corePayableMonth
						.getYingfukuanJine()
						+ corePayable.getHsPrice() * corePayable.getNumber());
				corePayableMonth.setRealfukuanJine(0d);
				corePayableMonth.setWeifukuanJine(corePayableMonth
						.getWeifukuanJine()
						+ corePayable.getHsPrice() * corePayable.getNumber());
				totalDao.update(corePayableMonth);
			}

			corePayable.setFk_CPMId(corePayableMonth.getId());
			totalDao.update(corePayable);
			try {
				/************* 生成暂估凭证凭证 *****************/
				Double zgBhsMoney = corePayable.getZgbhsPrice()
						* corePayable.getNumber();

				CwVouchers cwVouchers = new CwVouchers();
				String num = cwVcNumber();
				cwVouchers.setNumber(num);
				cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
				cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
				cwVouchers.setCreatetime(Util.getDateTime());
				cwVouchers.setUserName(Util.getLoginUser().getName());
				cwVouchers.setCreateCode(Util.getLoginUser().getCode());
				cwVouchers.setCreateName(Util.getLoginUser().getName());
				cwVouchers.setJieMoney(zgBhsMoney);
				cwVouchers.setDaiMoney(zgBhsMoney);

				Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

				// *******************凭证分录 --贷方(银行存款/库存现金)********************
				CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
				cwVouchersDetail_dai.setvClass("转");
				cwVouchersDetail_dai
						.setRemark("暂估-" + corePayable.getZhaiyao());

				// 明细科目需根据科目矩阵图查询
				SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='应付账款'");

				// 查找一级科目
				SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate
								.getRootId());
				cwVouchersDetail_dai.setSub(oneLevelsub.getName());
				cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

				// 明细科目
				cwVouchersDetail_dai.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate.getId()));
				cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
				cwVouchersDetail_dai.setJieMoney(0d);
				cwVouchersDetail_dai.setDaiMoney(zgBhsMoney);
				cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
				cwVouchersDetail_dai.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_dai.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_dai.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_dai);

				// ***************** 凭证分录 --借方(各种科目)*********************
				SubBudgetRate subBudgetRate_jie = null;
				CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

				subBudgetRate_jie = (SubBudgetRate) totalDao
						.getObjectByCondition("from SubBudgetRate where name='主材料' and fatherName='原材料'");

				CwUseDetail cwUseDetail = saveCwUserDetail(corePayable);
				cwUseDetail.setUsemoney(zgBhsMoney);
				cwUseDetail.setPayType("入库暂估");
				cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
				cwUseDetailSet.add(cwUseDetail);

				cwVouchersDetail_jie
						.setRemark("暂估-" + corePayable.getZhaiyao());
				cwVouchersDetail_jie.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
						.getObjectById(SubBudgetRate.class, subBudgetRate_jie
								.getRootId());
				cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
				cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
				// 明细科目
				cwVouchersDetail_jie.setDetailSub(receiptServer
						.findBudgetRateNames(subBudgetRate_jie.getId()));
				cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

				cwVouchersDetail_jie.setJieMoney(zgBhsMoney);
				cwVouchersDetail_jie.setDaiMoney(0d);
				cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie.setCreateName(Util.getLoginUser()
						.getName());
				cwVouchersDetail_jie.setCreateCode(Util.getLoginUser()
						.getCode());
				cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
				cwVouchersDetail_jie.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_jie);

				cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
				totalDao.save(cwVouchers);

				/************* 更新科目余额 *****************/
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
						zgBhsMoney.doubleValue(), 0D);
				// 更新贷方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
						zgBhsMoney.doubleValue());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		/***** 票到冲销暂估 *******************/
		String hql_fp = "from CorePayable where status not in ('对账','对账复核','可开票','发票复核')";
		List list_fp = totalDao.query(hql_fp);
		for (int i = 0; i < list_fp.size(); i++) {
			CorePayable corePayable = (CorePayable) list_fp.get(i);
			CwVouchers cwVouchers = new CwVouchers();
			String num = cwVcNumber();
			cwVouchers.setNumber(num);
			cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers.setCreatetime(Util.getDateTime());
			cwVouchers.setUserName(Util.getLoginUser().getName());
			cwVouchers.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers.setCreateName(Util.getLoginUser().getName());
			Double jieMoney = 0d;
			Double daiMoney = 0d;

			Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

			// 冲销暂估
			/************* 生成冲销暂估凭证 *****************/
			Double zgBhsMoney = corePayable.getZgbhsPrice()
					* corePayable.getNumber();// 暂估总额
			Double fpBhsMoney = corePayable.getBhsPrice()
					* corePayable.getNumber();// 实际总额

			/******************* 冲销 凭证分录 ***********************/

			// ***************** 凭证分录 --借方*********************
			SubBudgetRate subBudgetRate_jie = null;
			CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
			Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

			subBudgetRate_jie = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='主材料' and fatherName='原材料'");

			// ***************** 分录--辅助明细********************
			CwUseDetail cwUseDetail = saveCwUserDetail(corePayable);
			cwUseDetail.setUsemoney(-zgBhsMoney);
			cwUseDetail.setPayType("冲暂估");
			cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
			cwUseDetailSet.add(cwUseDetail);

			cwVouchersDetail_jie.setRemark("冲暂估-" + corePayable.getZhaiyao());
			cwVouchersDetail_jie.setvClass("转");
			// 查找一级科目
			SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_jie
							.getRootId());
			cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
			cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
			// 明细科目
			cwVouchersDetail_jie.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_jie.getId()));
			cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

			cwVouchersDetail_jie.setJieMoney(zgBhsMoney);
			cwVouchersDetail_jie.setDaiMoney(0d);
			cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
			cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
			cwVouchersDetail_jie.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_jie);

			cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
			totalDao.save(cwVouchers);
			// 更新借方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(),
					-zgBhsMoney.doubleValue(), 0D);
			jieMoney += -zgBhsMoney;
			daiMoney += 0F;

			// --*****************凭证分录-贷方********************
			CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
			cwVouchersDetail_dai.setvClass("转");
			cwVouchersDetail_dai.setRemark("冲暂估-" + corePayable.getZhaiyao());

			// 明细科目需根据科目矩阵图查询
			SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='应付账款'");

			// 查找一级科目
			SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(
					SubBudgetRate.class, subBudgetRate.getRootId());
			cwVouchersDetail_dai.setSub(oneLevelsub.getName());
			cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

			// 明细科目
			cwVouchersDetail_dai.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate.getId()));
			cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
			cwVouchersDetail_dai.setJieMoney(0d);
			cwVouchersDetail_dai.setDaiMoney(-zgBhsMoney);
			cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
			cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_dai.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_dai);
			// 更新贷方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D,
					-zgBhsMoney.doubleValue());
			jieMoney += 0F;
			daiMoney += -zgBhsMoney;

			/******************* 发票 凭证分录 ***********************/

			// ***************** 凭证分录 --借方*********************
			SubBudgetRate subBudgetRate_jie_fp = null;
			CwVouchersDetail cwVouchersDetail_jie_fp = new CwVouchersDetail();
			Set<CwUseDetail> cwUseDetailSet_fp = new HashSet<CwUseDetail>();

			subBudgetRate_jie_fp = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='主材料' and fatherName='原材料'");

			// ***************** 分录--辅助明细********************
			CwUseDetail cwUseDetail_fp = new CwUseDetail();
			cwUseDetail_fp.setPayee(corePayable.getSupplierName());
			cwUseDetail_fp.setUseFor(corePayable.getZhaiyao() + "-"
					+ corePayable.getLot());
			cwUseDetail_fp.setUsemoney(fpBhsMoney);
			cwUseDetail_fp.setAboutNum(corePayable.getFapiaoNum());
			cwUseDetail_fp.setFk_monthlyBillsId(corePayable.getId());
			cwUseDetail_fp.setPayNum("");
			cwUseDetail_fp.setPayType("冲暂估");
			cwUseDetail_fp.setCwVouchersDetail(cwVouchersDetail_jie_fp);
			cwUseDetailSet_fp.add(cwUseDetail_fp);

			cwVouchersDetail_jie_fp.setRemark("发票-" + corePayable.getZhaiyao());
			cwVouchersDetail_jie_fp.setvClass("转");
			// 查找一级科目
			SubBudgetRate oneLevelsub_jie_fp = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_jie_fp
							.getRootId());
			cwVouchersDetail_jie_fp.setSub(oneLevelsub_jie_fp.getName());
			cwVouchersDetail_jie_fp.setSubId(oneLevelsub_jie_fp.getId());
			// 明细科目
			cwVouchersDetail_jie_fp.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_jie_fp.getId()));
			cwVouchersDetail_jie_fp
					.setDetailSubId(subBudgetRate_jie_fp.getId());

			cwVouchersDetail_jie_fp.setJieMoney(fpBhsMoney);
			cwVouchersDetail_jie_fp.setDaiMoney(0d);
			cwVouchersDetail_jie_fp.setCreateTime(Util.getDateTime());
			cwVouchersDetail_jie_fp
					.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_jie_fp
					.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_jie_fp.setCwUseDetails(cwUseDetailSet_fp);
			cwVouchersDetail_jie_fp.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_jie_fp);

			cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
			totalDao.save(cwVouchers);
			// 更新借方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_jie_fp.getId(),
					fpBhsMoney.doubleValue(), 0D);
			jieMoney += fpBhsMoney;
			daiMoney += 0F;

			/**************** 应交税费 凭证 ********************/
			Double yjsf = fpBhsMoney * 0.17d;
			CwVouchersDetail cwVouchersDetail_dai_sf = new CwVouchersDetail();
			cwVouchersDetail_dai_sf.setvClass("转");
			cwVouchersDetail_dai_sf.setRemark("税费-" + corePayable.getZhaiyao());

			// 明细科目需根据科目矩阵图查询
			SubBudgetRate subBudgetRate_sf = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='进项税额'");

			// 查找一级科目
			SubBudgetRate oneLevelsub_sf = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_sf
							.getRootId());
			cwVouchersDetail_dai_sf.setSub(oneLevelsub_sf.getName());
			cwVouchersDetail_dai_sf.setSubId(oneLevelsub_sf.getId());

			// 明细科目
			cwVouchersDetail_dai_sf.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_sf.getId()));
			cwVouchersDetail_dai_sf.setDetailSubId(subBudgetRate_sf.getId());
			cwVouchersDetail_dai_sf.setJieMoney(yjsf);
			cwVouchersDetail_dai_sf.setDaiMoney(0d);
			cwVouchersDetail_dai_sf.setCreateTime(Util.getDateTime());
			cwVouchersDetail_dai_sf
					.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_dai_sf
					.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_dai_sf.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_dai_sf);
			// 更新贷方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_sf.getId(), 0D,
					fpBhsMoney.doubleValue());
			jieMoney += yjsf;
			daiMoney += 0F;

			// --*****************凭证分录-贷方********************
			Double dfmoney = fpBhsMoney + yjsf;// 应付账款+税费
			CwVouchersDetail cwVouchersDetail_dai_fp = new CwVouchersDetail();
			cwVouchersDetail_dai_fp.setvClass("转");
			cwVouchersDetail_dai_fp.setRemark("发票-" + corePayable.getZhaiyao());

			// 明细科目需根据科目矩阵图查询
			SubBudgetRate subBudgetRate_fp = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='应付账款'");

			// 查找一级科目
			SubBudgetRate oneLevelsub_fp = (SubBudgetRate) totalDao
					.getObjectById(SubBudgetRate.class, subBudgetRate_fp
							.getRootId());
			cwVouchersDetail_dai_fp.setSub(oneLevelsub_fp.getName());
			cwVouchersDetail_dai_fp.setSubId(oneLevelsub_fp.getId());

			// 明细科目
			cwVouchersDetail_dai_fp.setDetailSub(receiptServer
					.findBudgetRateNames(subBudgetRate_fp.getId()));
			cwVouchersDetail_dai_fp.setDetailSubId(subBudgetRate_fp.getId());
			cwVouchersDetail_dai_fp.setJieMoney(0d);
			cwVouchersDetail_dai_fp.setDaiMoney(dfmoney);
			cwVouchersDetail_dai_fp.setCreateTime(Util.getDateTime());
			cwVouchersDetail_dai_fp
					.setCreateName(Util.getLoginUser().getName());
			cwVouchersDetail_dai_fp
					.setCreateCode(Util.getLoginUser().getCode());
			cwVouchersDetail_dai_fp.setCwVouchers(cwVouchers);
			cwVouchersDetailSet.add(cwVouchersDetail_dai_fp);
			// 更新贷方科目余额
			receiptServer.updatesubBudgetRate(subBudgetRate_fp.getId(), 0D,
					dfmoney.doubleValue());
			jieMoney += 0F;
			daiMoney += dfmoney;

			/**************** 成本差异 ********************/

		}
		return null;
	}

	/**
	 * 辅助明细
	 * 
	 * @param corePayable
	 * @return
	 */
	private CwUseDetail saveCwUserDetail(CorePayable corePayable) {
		CwUseDetail cwUseDetail = new CwUseDetail();
		cwUseDetail.setPayee(corePayable.getSupplierName());
		cwUseDetail.setUseFor(corePayable.getZhaiyao() + "-"
				+ corePayable.getLot());
		cwUseDetail.setAboutNum(corePayable.getDeliveryNumber());
		cwUseDetail.setFk_monthlyBillsId(corePayable.getId());
		cwUseDetail.setPayNum("");
		return cwUseDetail;
	}

	/****
	 * 应付明细补充导入
	 * 
	 * @param attachment
	 * @return
	 */
	@Override
	public String addBcCorePayable(File attachment) {
		String msg = "true";
		boolean flag = true;
		String fileName = "core_" + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf2.format(new Date());
		String date = sdf1.format(new Date());
		int i = 0;
		try {
			FileCopyUtils.copy(attachment, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 1) {
				List<Integer> strList = new ArrayList<Integer>();
				StringBuffer sberror = new StringBuffer();
				int successcount = 0;
				int errorcount = 0;
				int error_index = 0;
				for (i = 1; i < exclecolums; i++) {
					// 类型 供应商名称 摘要 单位 含税单价 数量 应付金额 已付金额 未付金额 订单号 送货单号 创建时间 付款周期
					// 应付款日期

					try {
						Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
						String a = cells[1].getContents();// 类型

						String b = cells[2].getContents();// 供应商名称
						String c = cells[3].getContents();// 摘要
						String d = cells[4].getContents();// 单位
						String e = cells[5].getContents();// 含税单价
						Double hsprice = 0d;
						try {
							hsprice = Double.parseDouble(e);
						} catch (Exception e4) {
							e4.printStackTrace();
						}
						String e2 = cells[6].getContents();// 数量
						Float number = 0F;
						try {
							number = Float.parseFloat(e2);
						} catch (Exception e4) {
							e4.printStackTrace();
						}
						String f = cells[7].getContents();// 应付金额
						Double yingfu = 0d;
						try {
							yingfu = Double.parseDouble(f);
						} catch (Exception e4) {
							e4.printStackTrace();
						}
						String g = cells[8].getContents();// 已付金额
						Double yifu = 0d;
						try {
							yifu = Double.parseDouble(g);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						String h = cells[9].getContents();// 未付金额
						Double weifu = 0d;
						try {
							weifu = Double.parseDouble(h);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						String i1 = cells[10].getContents();// 订单号
						String j = cells[11].getContents();// 送货单号
						String k = cells[12].getContents();// 创建时间
						String l = cells[13].getContents();// 付款周期
						String m = null;
						try {
							m = cells[14].getContents();// 应付款日期
						} catch (Exception e3) {
							e3.printStackTrace();
						}

						Integer supplierId = (Integer) totalDao
								.getObjectByCondition(
										"select id from ZhUser where cmp=?", b);
						if (supplierId == null) {
							throw new RuntimeException("第" + i + "行" + b
									+ "不存在");

						}

						String jzMonth = Util.getDateTime("yyyy-MM");
						String hql_month = "from CorePayableMonth where jzMonth=? and supplierName=?";
						CorePayableMonth corePayableMonth = (CorePayableMonth) totalDao
								.getObjectByCondition(hql_month, jzMonth, b);
						if (corePayableMonth == null) {
							// 生成编号
							String num = yueDuNumber();
							corePayableMonth = new CorePayableMonth();
							corePayableMonth.setSupplierName(b);
							corePayableMonth.setSupplierId(supplierId);
							corePayableMonth.setRecNumber(num);
							corePayableMonth.setYufukuanJine(0F);
							corePayableMonth.setYingfukuanJine(yingfu);
							corePayableMonth.setRealfukuanJine(yifu);
							corePayableMonth.setWeifukuanJine(weifu);
							corePayableMonth.setJzMonth(jzMonth);
							corePayableMonth.setFukuanZq(90);
							corePayableMonth.setFukuanDate("");
							corePayableMonth.setLateDate("");
							corePayableMonth.setStatus("对账");
							corePayableMonth.setSaveTime(Util.getDateTime());
							corePayableMonth.setSaveUser(Util.getLoginUser()
									.getName());
							corePayableMonth.setAuditStatus("同意");
							totalDao.save(corePayableMonth);
						} else {
							corePayableMonth.setYingfukuanJine(corePayableMonth
									.getYingfukuanJine()
									+ yingfu);
							corePayableMonth.setRealfukuanJine(corePayableMonth
									.getRealfukuanJine()
									+ yifu);
							corePayableMonth.setWeifukuanJine(corePayableMonth
									.getWeifukuanJine()
									+ weifu);
							totalDao.update(corePayableMonth);
						}

						CorePayable corePayable = new CorePayable();
						corePayable.setFk_CPMId(corePayableMonth.getId());
						corePayable.setSubjectItem(a);
						corePayable.setCoreType("主营");
						corePayable.setSupplierName(b);
						corePayable.setSupplierId(supplierId);
						corePayable.setZhaiyao(c);
						corePayable.setUnit(d);
						corePayable.setHsPrice(hsprice);
						corePayable.setNumber(number);
						corePayable.setYingfukuanJine(yingfu);
						corePayable.setRealfukuanJine(yifu);
						corePayable.setOrderNumber(i1);
						corePayable.setDeliveryNumber(j);
						corePayable.setSaveTime(k);
						corePayable.setFukuanZq("");
						corePayable.setJzMonth(jzMonth);
						corePayable.setStatus("可开票");
						totalDao.save(corePayable);
						successcount++;
					} catch (Exception e) {
						e.printStackTrace();
						sberror.append("第" + (i + 1) + "行,数据格式错误!异常:"
								+ e.getMessage());
						errorcount++;
						if (error_index == 0) {
							error_index = i + 1;
						}
						continue;
					}
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				String errs = "";
				if (errorcount > 0) {
					errs = "从第" + error_index + "行开始出现错误，请核对错误后，从第"
							+ error_index + "行开始重新导入(即删除excel中1-"
							+ (error_index - 1) + "行的数据)!\\n";
				}
				msg = "总条数:" + exclecolums + "\\n已成功导入" + successcount + "个,失败"
						+ errorcount + "个\\n" + errs + "失败的內容如下:\\n" + sberror;
			} else {
				msg = "没有获取到行数";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "导入失败,出现异常" + e;

		}
		return msg;
	}

	/**
	 * 得到最大供应商月度对账单编号
	 * 
	 * @return
	 */
	private String yueDuNumber() {
		String num = "R" + Util.getDateTime("yyyyMM");
		String hql_finMaxnum = "select max(recNumber) from CorePayableMonth where recNumber like '%"
				+ num + "%'";
		String maxfkNumber = (String) totalDao
				.getObjectByCondition(hql_finMaxnum);
		if (maxfkNumber != null && maxfkNumber.length() > 0) {
			String subnum = "";
			Integer maxnum = Integer.parseInt(maxfkNumber.substring(7,
					maxfkNumber.length())) + 1;
			if (maxnum < 10) {
				subnum += "0000" + maxnum;// 00009
			} else if (maxnum < 100) {
				subnum += "000" + maxnum;// 00099
			} else if (maxnum < 1000) {
				subnum += "00" + maxnum;// 00999
			} else if (maxnum < 10000) {
				subnum += "0" + maxnum;// 09999
			} else {
				subnum += "" + maxnum;
			}
			num += subnum;
		} else {
			num += "00001";
		}
		return num;
	}

	/****
	 * 主营应付月度对账单
	 * 
	 * @return
	 */
	@Override
	public Object[] findCorePayableMonthList(CorePayableMonth corePayableMonth,
			int pageNo, int pageSize, String pageStatus) {
		if (corePayableMonth == null) {
			corePayableMonth = new CorePayableMonth();
		}
		String hql = totalDao.criteriaQueries(corePayableMonth, null);
		if ("gys".equals(pageStatus)) {
			// 查询供应商信息
			String hql_zhuser = "select id from ZhUser where userid=?";
			Integer zhuserId = (Integer) totalDao.getObjectByCondition(
					hql_zhuser, Util.getLoginUser().getId());
			if (zhuserId != null) {
				hql += " and supplierId=" + zhuserId;
				// if ("gysAll".equals(pageStatus)) {
				// } else if ("gysdz".equals(pageStatus)) {
				// hql += " and status='对账' and supplierId=" + zhuserId;
				// } else if ("gysdkp".equals(pageStatus)) {
				// hql += " and status='可开票' and supplierId=" + zhuserId;
				// }
			} else {
				return null;
			}
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/****
	 * 主营应付列表
	 * 
	 * @return
	 */
	@Override
	public Object[] findCorePaybleList(CorePayable corePayable, int pageNo,
			int pageSize, String pageStatus,String firstTime,String endTime) {
		if (corePayable == null) {
			corePayable = new CorePayable();
		}
		String hql = totalDao.criteriaQueries(corePayable, "saveTime",new String[]{firstTime,endTime},"");
		if (corePayable.getSupplierId() != null) {
			hql += " and supplierId=" + corePayable.getSupplierId();
		}
		if ("all".equals(pageStatus)) {

		} else if ("dzfh".equals(pageStatus)) {
			hql += " and status='对账复核'";
		} else if ("fpfh".equals(pageStatus)) {
			hql += " and status='发票复核'";
		} else if ("dkp".equals(pageStatus)) {
			hql += " and status='可开票'";
			pageNo = 0;
			pageSize = 0;
		} else if ("dfk".equals(pageStatus)) {
			hql += " and status in ('待付款','对账')";
		} else if ("fksp".equals(pageStatus)) {
			hql += " and status='付款申请'";
		} else if ("yfkdetail".equals(pageStatus)) {// 已付款
			hql += " and realfukuanJine>0";
		} else if ("wfkdetail".equals(pageStatus)) {// 未付款
			hql += " and yingfukuanJine-realfukuanJine>0";
		} else if ("fkzdetail".equals(pageStatus)) {// 付款中
			hql += " and yipizhunJine-realfukuanJine>0";
		} else if ("yikaipiao".equals(pageStatus)) {// 已开票
			hql += " and fapiaoNum is not null";
		} else if ("weiyikaipiao".equals(pageStatus)) {// 未开票
			hql += " and fapiaoNum is null";
		} else if ("gysAll".equals(pageStatus) || "gysdz".equals(pageStatus)
				|| "gysdkp".equals(pageStatus)) {
			// 供应商待开票
			Users loginUser = Util.getLoginUser();
			if (loginUser != null) {
				// 查询供应商信息
				String hql_zhuser = "select id from ZhUser where userid=?";
				Integer zhuserId = (Integer) totalDao.getObjectByCondition(
						hql_zhuser, loginUser.getId());
				if (zhuserId != null) {
					if ("gysAll".equals(pageStatus)) {
						hql += " and supplierId=" + zhuserId;
					} else if ("gysdz".equals(pageStatus)) {
						hql += "  and supplierId=" + zhuserId;
					} else if ("gysdkp".equals(pageStatus)) {
						hql += " and status='可开票' and supplierId=" + zhuserId;
					}
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
		if (corePayable.getId() != null) {
			hql += " and id=" + corePayable.getId();
		}
		if (corePayable.getFk_CPMId() != null) {
			hql += " and fk_CPMId=" + corePayable.getFk_CPMId();
		}
		List list = totalDao.findAllByPage(hql+" order by id desc ", pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/****
	 * 月度应收应付账单--付款
	 * 
	 * @return
	 */
	@Override
	public Object[] findMonthPayableBillList(MonthPayableBill monthPayableBill,
			int pageNo, int pageSize, String pageStatus) {
		if (monthPayableBill == null) {
			monthPayableBill = new MonthPayableBill();
		}
		String hql = totalDao.criteriaQueries(monthPayableBill, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);

		String hql_wsSum = "select sum(yingshoukuanJine),sum(realshoukuanJine),sum(weishoukuanJine),sum(yingfukuanJine),sum(realfukuanJine),sum(weifukuanJine) from MonthPayableBill ";
		Object[] sums = (Object[]) totalDao.getObjectByCondition(hql_wsSum);
		monthPayableBill = new MonthPayableBill();
		monthPayableBill.setYingshoukuanJine(Float
				.parseFloat(sums[0] == null ? "0" : sums[0].toString()));
		monthPayableBill.setRealshoukuanJine(Float
				.parseFloat(sums[1] == null ? "0" : sums[1].toString()));
		monthPayableBill.setWeishoukuanJine(Float
				.parseFloat(sums[2] == null ? "0" : sums[2].toString()));
		monthPayableBill.setYingfukuanJine(Float
				.parseFloat(sums[3] == null ? "0" : sums[3].toString()));
		monthPayableBill.setRealfukuanJine(Float
				.parseFloat(sums[4] == null ? "0" : sums[4].toString()));
		monthPayableBill.setWeifukuanJine(Float
				.parseFloat(sums[5] == null ? "0" : sums[5].toString()));
		int count = totalDao.getCount(hql);
		Object[] o = { list, count, monthPayableBill };
		return o;
	}

	/****
	 * 批量核对账单
	 * 
	 * @param ids
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	@Override
	public boolean gyshdzd(Integer[] ids, String pageStatus) {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				CorePayable corePayable = (CorePayable) totalDao.getObjectById(
						CorePayable.class, ids[i]);
				if (corePayable != null) {
					if ("yes".equals(pageStatus)) {
						corePayable.setStatus("可开票");
					} else if ("no".equals(pageStatus)) {
						corePayable.setStatus("对账复核");
					}
					bool = totalDao.update(corePayable);
				}
			}
		}
		return bool;
	}

	/****
	 * 上传发票
	 * 
	 * @param corePayable
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	@Override
	public boolean uploadFapiao(CorePayable corePayable, File attachment,
			String attachmentFileName) {
		if (corePayable != null) {
			CorePayable oldCorePayable = (CorePayable) totalDao.getObjectById(
					CorePayable.class, corePayable.getId());
			if (oldCorePayable != null) {
				oldCorePayable.setFapiaoNum(corePayable.getFapiaoNum());
				// 上传发票附件
				Upload upload = new Upload();
				String filenewname = upload.UploadFile(attachment,
						attachmentFileName, null, "/upload/file/fapiao", null);
				oldCorePayable.setFujian(filenewname);
				oldCorePayable.setStatus("发票复核");
				return totalDao.update(oldCorePayable);
			}
		}
		return false;
	}

	/****
	 * 批量上传发票
	 * 
	 * @param ids
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	@Override
	public boolean uploadFapiao(Integer[] ids, String fapiaoNum,
			File attachment, String attachmentFileName) {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			// 上传发票附件
			Upload upload = new Upload();
			String filenewname = upload.UploadFile(attachment,
					attachmentFileName, null, "/upload/file/fapiao", null);

			CwVouchers cwVouchers = new CwVouchers();
			String num = cwVcNumber();
			cwVouchers.setNumber(num);
			cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers.setCreatetime(Util.getDateTime());
			cwVouchers.setUserName(Util.getLoginUser().getName());
			cwVouchers.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers.setCreateName(Util.getLoginUser().getName());
			Double jieMoney = 0d;
			Double daiMoney = 0d;

			Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();

			for (int i = 0; i < ids.length; i++) {
				CorePayable corePayable = (CorePayable) totalDao.getObjectById(
						CorePayable.class, ids[i]);
				if (corePayable != null) {
					corePayable.setFapiaoNum(fapiaoNum);
					corePayable.setFujian(filenewname);
					corePayable.setStatus("发票复核");
					bool = totalDao.update(corePayable);

					// --*****************凭证分录-贷方********************
					Double dfmoney = 0d;
					try {
						// 冲销暂估
						/************* 生成冲销暂估凭证 *****************/
						Double zgBhsMoney = corePayable.getZgbhsPrice()
								* corePayable.getNumber();// 暂估总额
						Double fpBhsMoney = corePayable.getBhsPrice()
								* corePayable.getNumber();// 实际总额

						/******************* 冲销 凭证分录 ***********************/

						// ***************** 凭证分录 --借方*********************
						SubBudgetRate subBudgetRate_jie = null;
						CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
						Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

						subBudgetRate_jie = qichuZhuCL();
						// ***************** 分录--辅助明细********************
						CwUseDetail cwUseDetail = new CwUseDetail();
						cwUseDetail.setPayee(corePayable.getSupplierName());
						cwUseDetail.setUseFor(corePayable.getZhaiyao() + "-"
								+ corePayable.getLot());
						cwUseDetail.setUsemoney(-zgBhsMoney);
						cwUseDetail
								.setAboutNum(corePayable.getDeliveryNumber());
						cwUseDetail.setFk_monthlyBillsId(corePayable.getId());
						cwUseDetail.setPayNum("");
						cwUseDetail.setPayType("冲暂估");
						cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
						cwUseDetailSet.add(cwUseDetail);

						cwVouchersDetail_jie.setRemark("冲暂估-"
								+ corePayable.getZhaiyao());
						cwVouchersDetail_jie.setvClass("转");
						// 查找一级科目
						SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao
								.getObjectById(SubBudgetRate.class,
										subBudgetRate_jie.getRootId());
						cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
						cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
						// 明细科目
						cwVouchersDetail_jie
								.setDetailSub(receiptServer
										.findBudgetRateNames(subBudgetRate_jie
												.getId()));
						cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie
								.getId());

						cwVouchersDetail_jie.setJieMoney(zgBhsMoney);
						cwVouchersDetail_jie.setDaiMoney(0d);
						cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
						cwVouchersDetail_jie.setCreateName(Util.getLoginUser()
								.getName());
						cwVouchersDetail_jie.setCreateCode(Util.getLoginUser()
								.getCode());
						cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
						cwVouchersDetail_jie.setCwVouchers(cwVouchers);
						cwVouchersDetailSet.add(cwVouchersDetail_jie);

						cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
						totalDao.save(cwVouchers);
						// 更新借方科目余额
						receiptServer.updatesubBudgetRate(subBudgetRate_jie
								.getId(), -zgBhsMoney.doubleValue(), 0D);
						jieMoney += -zgBhsMoney;
						daiMoney += 0F;

						// --*****************凭证分录-贷方********************
						CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
						cwVouchersDetail_dai.setvClass("转");
						cwVouchersDetail_dai.setRemark("冲暂估-"
								+ corePayable.getZhaiyao());

						// 明细科目需根据科目矩阵图查询
						SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
								.getObjectByCondition("from SubBudgetRate where name='应付账款'");

						// 查找一级科目
						SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao
								.getObjectById(SubBudgetRate.class,
										subBudgetRate.getRootId());
						cwVouchersDetail_dai.setSub(oneLevelsub.getName());
						cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

						// 明细科目
						cwVouchersDetail_dai.setDetailSub(receiptServer
								.findBudgetRateNames(subBudgetRate.getId()));
						cwVouchersDetail_dai.setDetailSubId(subBudgetRate
								.getId());
						cwVouchersDetail_dai.setJieMoney(0d);
						cwVouchersDetail_dai.setDaiMoney(-zgBhsMoney);
						cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
						cwVouchersDetail_dai.setCreateName(Util.getLoginUser()
								.getName());
						cwVouchersDetail_dai.setCreateCode(Util.getLoginUser()
								.getCode());
						cwVouchersDetail_dai.setCwVouchers(cwVouchers);
						cwVouchersDetailSet.add(cwVouchersDetail_dai);
						// 更新贷方科目余额
						receiptServer.updatesubBudgetRate(
								subBudgetRate.getId(), 0D, -zgBhsMoney
										.doubleValue());
						jieMoney += 0F;
						daiMoney += -zgBhsMoney;

						/******************* 发票 凭证分录 ***********************/

						// ***************** 凭证分录 --借方*********************
						SubBudgetRate subBudgetRate_jie_fp = null;
						CwVouchersDetail cwVouchersDetail_jie_fp = new CwVouchersDetail();
						Set<CwUseDetail> cwUseDetailSet_fp = new HashSet<CwUseDetail>();

						subBudgetRate_jie_fp = qichuZhuCL();
						// ***************** 分录--辅助明细********************
						CwUseDetail cwUseDetail_fp = new CwUseDetail();
						cwUseDetail_fp.setPayee(corePayable.getSupplierName());
						cwUseDetail_fp.setUseFor(corePayable.getZhaiyao() + "-"
								+ corePayable.getLot());
						cwUseDetail_fp.setUsemoney(fpBhsMoney);
						cwUseDetail_fp.setAboutNum(corePayable.getFapiaoNum());
						cwUseDetail_fp
								.setFk_monthlyBillsId(corePayable.getId());
						cwUseDetail_fp.setPayNum("");
						cwUseDetail_fp.setPayType("冲暂估");
						cwUseDetail_fp
								.setCwVouchersDetail(cwVouchersDetail_jie_fp);
						cwUseDetailSet_fp.add(cwUseDetail_fp);

						cwVouchersDetail_jie_fp.setRemark("发票-"
								+ corePayable.getZhaiyao());
						cwVouchersDetail_jie_fp.setvClass("转");
						// 查找一级科目
						SubBudgetRate oneLevelsub_jie_fp = (SubBudgetRate) totalDao
								.getObjectById(SubBudgetRate.class,
										subBudgetRate_jie_fp.getRootId());
						cwVouchersDetail_jie_fp.setSub(oneLevelsub_jie_fp
								.getName());
						cwVouchersDetail_jie_fp.setSubId(oneLevelsub_jie_fp
								.getId());
						// 明细科目
						cwVouchersDetail_jie_fp.setDetailSub(receiptServer
								.findBudgetRateNames(subBudgetRate_jie_fp
										.getId()));
						cwVouchersDetail_jie_fp
								.setDetailSubId(subBudgetRate_jie_fp.getId());

						cwVouchersDetail_jie_fp.setJieMoney(fpBhsMoney);
						cwVouchersDetail_jie_fp.setDaiMoney(0d);
						cwVouchersDetail_jie_fp.setCreateTime(Util
								.getDateTime());
						cwVouchersDetail_jie_fp.setCreateName(Util
								.getLoginUser().getName());
						cwVouchersDetail_jie_fp.setCreateCode(Util
								.getLoginUser().getCode());
						cwVouchersDetail_jie_fp
								.setCwUseDetails(cwUseDetailSet_fp);
						cwVouchersDetail_jie_fp.setCwVouchers(cwVouchers);
						cwVouchersDetailSet.add(cwVouchersDetail_jie_fp);

						cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
						totalDao.save(cwVouchers);
						// 更新借方科目余额
						receiptServer.updatesubBudgetRate(subBudgetRate_jie_fp
								.getId(), fpBhsMoney.doubleValue(), 0D);
						jieMoney += fpBhsMoney;
						daiMoney += 0F;

						/**************** 应交税费 凭证 ********************/
						Double yjsf = fpBhsMoney * 0.17d;
						CwVouchersDetail cwVouchersDetail_dai_sf = new CwVouchersDetail();
						cwVouchersDetail_dai_sf.setvClass("转");
						cwVouchersDetail_dai_sf.setRemark("税费-"
								+ corePayable.getZhaiyao());

						// 明细科目需根据科目矩阵图查询
						SubBudgetRate subBudgetRate_sf = (SubBudgetRate) totalDao
								.getObjectByCondition("from SubBudgetRate where name='进项税额'");

						// 查找一级科目
						SubBudgetRate oneLevelsub_sf = (SubBudgetRate) totalDao
								.getObjectById(SubBudgetRate.class,
										subBudgetRate_sf.getRootId());
						cwVouchersDetail_dai_sf
								.setSub(oneLevelsub_sf.getName());
						cwVouchersDetail_dai_sf
								.setSubId(oneLevelsub_sf.getId());

						// 明细科目
						cwVouchersDetail_dai_sf.setDetailSub(receiptServer
								.findBudgetRateNames(subBudgetRate_sf.getId()));
						cwVouchersDetail_dai_sf.setDetailSubId(subBudgetRate_sf
								.getId());
						cwVouchersDetail_dai_sf.setJieMoney(yjsf);
						cwVouchersDetail_dai_sf.setDaiMoney(0d);
						cwVouchersDetail_dai_sf.setCreateTime(Util
								.getDateTime());
						cwVouchersDetail_dai_sf.setCreateName(Util
								.getLoginUser().getName());
						cwVouchersDetail_dai_sf.setCreateCode(Util
								.getLoginUser().getCode());
						cwVouchersDetail_dai_sf.setCwVouchers(cwVouchers);
						cwVouchersDetailSet.add(cwVouchersDetail_dai_sf);
						// 更新贷方科目余额
						receiptServer.updatesubBudgetRate(subBudgetRate_sf
								.getId(), 0D, fpBhsMoney.doubleValue());
						jieMoney += yjsf;
						daiMoney += 0F;

						dfmoney = fpBhsMoney + yjsf;
						CwVouchersDetail cwVouchersDetail_dai_fp = new CwVouchersDetail();
						cwVouchersDetail_dai_fp.setvClass("转");
						cwVouchersDetail_dai_fp.setRemark("发票-"
								+ corePayable.getZhaiyao());

						// 明细科目需根据科目矩阵图查询
						SubBudgetRate subBudgetRate_fp = (SubBudgetRate) totalDao
								.getObjectByCondition("from SubBudgetRate where name='应付账款'");

						// 查找一级科目
						SubBudgetRate oneLevelsub_fp = (SubBudgetRate) totalDao
								.getObjectById(SubBudgetRate.class,
										subBudgetRate_fp.getRootId());
						cwVouchersDetail_dai_fp
								.setSub(oneLevelsub_fp.getName());
						cwVouchersDetail_dai_fp
								.setSubId(oneLevelsub_fp.getId());

						// 明细科目
						cwVouchersDetail_dai_fp.setDetailSub(receiptServer
								.findBudgetRateNames(subBudgetRate_fp.getId()));
						cwVouchersDetail_dai_fp.setDetailSubId(subBudgetRate_fp
								.getId());
						cwVouchersDetail_dai_fp.setJieMoney(0d);
						cwVouchersDetail_dai_fp.setDaiMoney(dfmoney);
						cwVouchersDetail_dai_fp.setCreateTime(Util
								.getDateTime());
						cwVouchersDetail_dai_fp.setCreateName(Util
								.getLoginUser().getName());
						cwVouchersDetail_dai_fp.setCreateCode(Util
								.getLoginUser().getCode());
						cwVouchersDetail_dai_fp.setCwVouchers(cwVouchers);
						cwVouchersDetailSet.add(cwVouchersDetail_dai_fp);
						// 更新贷方科目余额
						receiptServer.updatesubBudgetRate(subBudgetRate_fp
								.getId(), 0D, dfmoney.doubleValue());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					jieMoney += 0F;
					daiMoney += dfmoney;

					/**************** 成本差异 ********************/

				}
			}
			cwVouchers.setJieMoney(jieMoney);
			cwVouchers.setDaiMoney(daiMoney);
			totalDao.save(cwVouchers);
		}
		return bool;
	}

	/**
	 * 得到原材料科目
	 * 
	 * @return
	 */
	private SubBudgetRate qichuZhuCL() {
		SubBudgetRate subBudgetRate_jie_fp;
		subBudgetRate_jie_fp = (SubBudgetRate) totalDao
				.getObjectByCondition("from SubBudgetRate where name='期初原材料' and fatherName='原材料'");
		if (subBudgetRate_jie_fp == null) {
			subBudgetRate_jie_fp = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='主材料' and fatherName='原材料'");
		}
		return subBudgetRate_jie_fp;
	}

	/****
	 * 发票复核
	 * 
	 * @param ids
	 * @param pageStatus
	 * @return
	 */
	@Override
	public boolean fpfh(Integer[] ids, String pageStatus) {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			// 需增加系统配置项，是否需要 供应商验证功能
			String isNeed = "否";
			// 生成验证码，反馈给供应商。用于其输入后激活应付款项，进入待付队列
			Random random = new Random();
			String result = "";
			for (int j = 0; j < 6; j++) {
				result += random.nextInt(10);
			}
			Integer supplierId = 0;
			String fapiaoNum = "";
			for (int i = 0; i < ids.length; i++) {
				CorePayable corePayable = (CorePayable) totalDao.getObjectById(
						CorePayable.class, ids[i]);
				if (corePayable != null) {
					if ("否".equals(isNeed)) {
						corePayable.setStatus("待付款");
						try {
							corePayable.setFukuanDate(Util.DateToString(Util
									.getCalendarDate(new Date(), 90), null));
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						corePayable.setStatus("供应商验证");
						corePayable.setRandomyzm(result);
					}
					bool = totalDao.update(corePayable);
					if (bool) {
						String hql_checkMonth = "from CorePayable where fk_CPMId=? and status<>'待付款'";
						Integer count = totalDao.getCount(hql_checkMonth,
								corePayable.getFk_CPMId());
						if (count == 0) {
							CorePayableMonth corePayableMonth = (CorePayableMonth) totalDao
									.getObjectById(CorePayableMonth.class,
											corePayable.getFk_CPMId());
							if (corePayableMonth != null) {
								// 添加付款单
								Receipt receipt = new Receipt();
								// 生成编号
								String num = "APB" + Util.getDateTime("yyyyMM");
								String hql_finMaxnum = "select max(pkNumber) from Receipt where pkNumber like '%"
										+ num + "%'";
								String maxfkNumber = (String) totalDao
										.getObjectByCondition(hql_finMaxnum);
								if (maxfkNumber != null
										&& maxfkNumber.length() > 0) {
									String subnum = "";
									Integer maxnum = Integer
											.parseInt(maxfkNumber.substring(9,
													maxfkNumber.length())) + 1;
									if (maxnum < 10) {
										subnum += "0000" + maxnum;// 00009
									} else if (maxnum < 100) {
										subnum += "000" + maxnum;// 00099
									} else if (maxnum < 1000) {
										subnum += "00" + maxnum;// 00999
									} else if (maxnum < 10000) {
										subnum += "0" + maxnum;// 09999
									} else {
										subnum += "" + maxnum;
									}
									num += subnum;
								} else {
									num += "00001";
								}
								receipt.setPkNumber(num);
								receipt.setPayee(corePayableMonth
										.getSupplierName());
								receipt.setPayeeId(corePayableMonth
										.getSupplierId());
								receipt.setSummary(corePayableMonth
										.getSupplierName()
										+ corePayableMonth.getJzMonth()
										+ "应付账款");
								receipt.setPayType("采购订单");
								receipt.setAboutNum(corePayableMonth
										.getRecNumber());
								receipt.setFk_monthlyBillsId(corePayableMonth
										.getId());
								receipt.setAllMoney(corePayableMonth
										.getYingfukuanJine().floatValue());
								receipt.setAccountPaid(0F);
								receipt.setUnPay(corePayableMonth
										.getYingfukuanJine().floatValue());
								receipt.setPayIng(0F);
								receipt.setPayOn(0F);
								receipt.setPayLast(corePayableMonth
										.getYingfukuanJine().floatValue());
								receipt.setFukuanDate(Util
										.getDateTime("yyyy-MM-dd"));
								receipt.setPaymentCycle(corePayableMonth
										.getFukuanZq());
								receipt.setFukuanMonth(Util
										.getDateTime("yyyy-MM"));
								receipt.setStatus("待付款");

								receipt.setAddTime(Util.getDateTime());
								receipt.setAddUserCode(Util.getLoginUser()
										.getCode());
								receipt.setAddUserName(Util.getLoginUser()
										.getName());
								totalDao.save(receipt);

								// 添加月度应付账单
								String hql_MonthPayableBill = "from MonthPayableBill where jzMonth=?";
								MonthPayableBill monthPayableBill = (MonthPayableBill) totalDao
										.getObjectByCondition(
												hql_MonthPayableBill, receipt
														.getFukuanMonth());
								if (monthPayableBill != null) {
									monthPayableBill
											.setYingfukuanJine(monthPayableBill
													.getYingfukuanJine()
													+ receipt.getAllMoney());
									monthPayableBill
											.setWeifukuanJine(monthPayableBill
													.getWeifukuanJine()
													+ receipt.getAllMoney());
									totalDao.update(monthPayableBill);
								} else {
									monthPayableBill = new MonthPayableBill();
									monthPayableBill.setJzMonth(receipt
											.getFukuanMonth());
									monthPayableBill.setYingfukuanJine(receipt
											.getAllMoney());
									monthPayableBill.setRealfukuanJine(0F);
									monthPayableBill.setWeifukuanJine(receipt
											.getAllMoney());
									monthPayableBill.setYingshoukuanJine(0F);
									monthPayableBill.setRealshoukuanJine(0F);
									monthPayableBill.setWeishoukuanJine(0F);
									monthPayableBill.setStatus("");
									monthPayableBill.setSaveTime(Util
											.getDateTime());
									monthPayableBill.setSaveUser(Util
											.getLoginUser().getName());
									totalDao.save(monthPayableBill);
								}

								// 添加应付汇总
								// 查询该收款单位在付款汇总表是否存在
								String hql_SupplierCorePayable = "from SupplierCorePayable where supplierName=? and coreType='非主营' and payableType='付款'";
								SupplierCorePayable scp = (SupplierCorePayable) totalDao
										.getObjectByCondition(
												hql_SupplierCorePayable,
												receipt.getPayee());
								if (scp != null) {
									scp.setYingfukuanJine(scp
											.getYingfukuanJine()
											+ receipt.getAllMoney());
									scp.setWeifukuanJine(scp.getWeifukuanJine()
											+ receipt.getAllMoney());
									totalDao.update(scp);
								} else {
									scp = new SupplierCorePayable();
									scp.setSupplierName(receipt.getPayee());
									scp.setCoreType("主营");
									scp.setPayableType("付款");
									scp
											.setYingfukuanJine(receipt
													.getAllMoney());
									scp.setRealfukuanJine(0F);
									scp.setWeifukuanJine(receipt.getAllMoney());
									scp.setFukuanzhongJine(0F);
									scp.setFkCount(0);
									scp.setFukuanzhongJine(0F);
									scp.setAddTime(Util.getDateTime());
									totalDao.save(scp);
								}
							}
						}
					}
				}
			}
			if ("否".equals(isNeed)) {
				// 发送验证码
				ZhUser zhuser = (ZhUser) totalDao.getObjectById(ZhUser.class,
						supplierId);
				if (zhuser != null) {
					ShortMessageServiceImpl sms = new ShortMessageServiceImpl();
					sms.send(zhuser.getCtel(), "您上传的发票：" + fapiaoNum
							+ ids.length);
					// 您的发票号：123456789
					// 共计4条对账信息已经复核完成。请您登录系统后用该验证码【304814】输入确认，即可进入付款序列。
					// 验证码请妥善保管。谢谢!
				}
			}
		}
		return bool;
	}

	/****
	 * 供应商付款汇总表
	 * 
	 * @return
	 */
	@Override
	public Object[] findSupplierCorePayableList(
			SupplierCorePayable supplierCorePayable, int pageNo, int pageSize,
			String pageStatus) {
		if (supplierCorePayable == null) {
			supplierCorePayable = new SupplierCorePayable();
		}
		String hql = totalDao.criteriaQueries(supplierCorePayable, null);
		if (supplierCorePayable.getCoreType() != null
				&& supplierCorePayable.getCoreType().length() > 0) {
			hql += " and coreType='" + supplierCorePayable.getCoreType() + "'";
		}
		if ("shoukuan".equals(pageStatus)) {
			hql += " and payableType='收款'";
		} else {
			hql += " and payableType='付款'";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/****
	 * 个人财务中心
	 * 
	 * @return
	 */
	@Override
	public Object[] findSupplierCorePayablePerson(
			SupplierCorePayable supplierCorePayable, int pageNo, int pageSize,
			String pageStatus) {
		Users user = Util.getLoginUser();
		if (user != null) {
			// 费用
			String hql_fk = "from SupplierCorePayable where supplierName=? and payableType='付款'";
			SupplierCorePayable f = (SupplierCorePayable) totalDao
					.getObjectByCondition(hql_fk, user.getName());
			String hql_sk = "from SupplierCorePayable where supplierName=? and payableType='收款'";
			SupplierCorePayable s = (SupplierCorePayable) totalDao
					.getObjectByCondition(hql_sk, user.getName());
			// 借领还
			String hql_jie = "select sum(num) from Borrow  where cardNum=?";
			Float jienum = (Float) totalDao.getObjectByCondition(hql_jie, user
					.getCardId());

			String hql_ling = "select sum(num) from Consuming  where cardNum=?";
			Float lingnum = (Float) totalDao.getObjectByCondition(hql_ling,
					user.getCardId());

			String hql_huan = "select sum(num) from Also where cardNum=?";
			Float huannum = (Float) totalDao.getObjectByCondition(hql_huan,
					user.getCardId());
			// 工资
			String hql_wage = "select sum(shifagongzi) from Wage where code=?";
			Float wagenum = (Float) totalDao.getObjectByCondition(hql_wage,
					user.getCode());
			Float[] jlh = { jienum, lingnum, huannum, wagenum };

			Object[] o = { f, s, jlh };
			return o;
		}
		return null;
	}

	/****
	 * 申请付款
	 * 
	 * @param ids
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	@Override
	public boolean shenqingfukuan(Integer[] ids, String pageStatus) {
		boolean bool = false;
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				CorePayable corePayable = (CorePayable) totalDao.getObjectById(
						CorePayable.class, ids[i]);
				if (corePayable != null) {
					corePayable.setStatus("付款申请");
					bool = totalDao.update(corePayable);
				}
			}
			AlertMessagesServerImpl.addAlertMessages("付款调额及审批", "您有待审核付款申请",
					"1");
		}
		return bool;
	}

	/****
	 * 申请付款审批及调额
	 * 
	 * @param ids
	 * @param attachment
	 * @param attachmentFileName
	 * @return
	 */
	@Override
	public boolean fukuanAudit(List<CorePayable> list) {
		boolean bool = false;
		if (list != null && list.size() > 0) {
			FundApply fundApply = new FundApply();
			fundApply.setZhifuoryufu("支付");
			fundApply.setAbout("主营应付");
			String createdate1 = "jkbx_" + Util.getDateTime("yyyy");
			String hql = "select max(number) from FundApply";
			String max_numbere = (String) this.totalDao
					.getObjectByCondition(hql);
			if (max_numbere != null && !"".equals(max_numbere)) {
				// String number1 = paymentVoucher2.getNumber();
				String now_number = max_numbere.split("_")[1];
				long number2 = Long.parseLong(now_number) + 1;
				String number3 = Long.toString(number2);
				fundApply.setNumber("jkbx_" + number3);
			} else {
				String number2 = createdate1 + "001";
				fundApply.setNumber(number2);
			}
			fundApply.setUserId(Util.getLoginUser().getId());
			fundApply.setApprovalApplier(Util.getLoginUser().getName());
			fundApply.setAddTime(Util.getDateTime());
			fundApply.setStatus("申请中");
			fundApply.setEpStattus("同意");
			fundApply.setStatus("财务确认");
			fundApply.setZhifuoryufu("支付");
			fundApply.setPayStyle("银行转账");
			fundApply.setIsTax("增值税发票");
			fundApply.setVoucherway("银行转账");
			Set<FundApplyDetailed> fadSet = new HashSet<FundApplyDetailed>();
			String relationclient = "";
			String fapiaohao = "";
			Double allMoney = 0d;
			for (CorePayable corePayable : list) {
				CorePayable oldcorePayable = (CorePayable) totalDao
						.getObjectById(CorePayable.class, corePayable.getId());
				if (oldcorePayable != null) {
					// relationclient += oldcorePayable.getSupplierName() + ";";
					relationclient = oldcorePayable.getSupplierName();
					fapiaohao += oldcorePayable.getFapiaoNum() + ";";
					allMoney += corePayable.getYipizhunJine();
					FundApplyDetailed fundApplyDetailed = new FundApplyDetailed();
					fundApplyDetailed.setZjStyle(oldcorePayable
							.getSubjectItem());
					fundApplyDetailed.setBudgetDept(oldcorePayable
							.getSubjectItem());
					fundApplyDetailed.setPay_use(oldcorePayable.getZhaiyao());
					fundApplyDetailed.setVoucherMoney(corePayable
							.getYipizhunJine().doubleValue());
					fundApplyDetailed.setInvoiceNo(oldcorePayable
							.getFapiaoNum());
					fundApplyDetailed.setFundApply(fundApply);
					fadSet.add(fundApplyDetailed);
					oldcorePayable.setYipizhunJine(oldcorePayable
							.getYipizhunJine()
							+ corePayable.getYipizhunJine());
					if (oldcorePayable.getYipizhunJine() >= oldcorePayable
							.getYingfukuanJine()) {
						oldcorePayable.setStatus("付款中");
					} else {
						oldcorePayable.setStatus("待付款");
					}
					totalDao.update(oldcorePayable);
				}
			}
			if (relationclient != "") {
				fundApply.setTotalMoney(allMoney.doubleValue());
				fundApply.setInvoiceNum(fapiaohao);
				fundApply.setRelationclient(relationclient);
				fundApply.setFadSet(fadSet);
				bool = totalDao.save(fundApply);
			}
		}
		return bool;
	}

	/**
	 * 调整入库科目余额表
	 * 
	 * @param zgBhsMoney
	 *            增加或减少的数量
	 */
	@Override
	public void chuliPir(Float zgBhsMoney) {
		SubBudgetRate subBudgetRate_jie = qichuZhuCL();
		// 明细科目需根据科目矩阵图查询
		SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
				.getObjectByCondition("from SubBudgetRate where name='应付账款'");
		/************* 更新科目余额 *****************/
		// 更新借方科目余额
		receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(), zgBhsMoney
				.doubleValue(), 0D);
		// 更新贷方科目余额
		receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D, zgBhsMoney
				.doubleValue());
	}
	/**
	 * 月度付款明细
	 */
	public List<PayMonthDetail> findPayMonthDetail(PayMonth payMonth,String firstTime,String endTime,String cangqu,Integer max){
		if(payMonth==null){
			payMonth = new PayMonth();
		}
		List<PayMonthDetail> pmList = new ArrayList<PayMonthDetail>();
		String hql =null;
		if(!"".equals(cangqu)&&cangqu!=null){//处理仓区信息
				String str = "";
				String[] cangqus = cangqu.split(",");
				if (cangqus != null && cangqus.length > 0) {
					for (int i = 0; i < cangqus.length; i++) {
						str += ",'" + cangqus[i] + "'";
					}
					if (str.length() >= 1) {
						str = str.substring(1);
					}
				}
			 hql = "select sum(hsPrice*number) from CorePayable where 1=1 and supplierName like '%"+payMonth.getSupplierName()+"%' and cangqu in (" +str+")"+
			" and saveTime >= '"+firstTime+" 00:00:00' and saveTime <= '"+endTime+" 23:59:59'";
		}else{
			hql = "select sum(hsPrice*number) from CorePayable where 1=1 and supplierName like '%"+payMonth.getSupplierName()+"%'"+
			" and saveTime >= '"+firstTime+" 00:00:00' and saveTime <= '"+endTime+" 23:59:59'";
		}
	
		//账单查询(所有) 数据来源
		Float jine = (Float)totalDao.getObjectByCondition(hql, null);
		String name = "付"+payMonth.getSupplierName()+"("+firstTime+"-"+endTime+")货款("+cangqu+")";
		PayMonthDetail pmd = new PayMonthDetail();
		pmd.setIllustrate(name);
		pmd.setJine(jine+"");
		pmList.add(pmd);
		//扣款单(页面第一次访问该方法的时候查询)
		if(max==1){
			String hql_kk = "from ChargebackNotice where zhUser_name like '%"+payMonth.getSupplierName()+"%'"+" and addTime >= '"+firstTime+"' and addTime <= '"+endTime+"'";
			List<ChargebackNotice> cnList = (List<ChargebackNotice>)totalDao.find(hql_kk);
			for(ChargebackNotice cn : cnList){
				PayMonthDetail pmd_kk = new PayMonthDetail();
				pmd_kk.setIllustrate(cn.getKkCause()+"("+cn.getKkMouth()+"扣款)");
				pmd_kk.setJine(-cn.getKkMoney()+"");
				pmList.add(pmd_kk);
			}
		}
		return  pmList;
	}
	@Override
	public void exprotCorePayable(CorePayable corePayable,String firstTime,String endTime) {
		if(corePayable==null){
			corePayable = new CorePayable();
		}
		String hql = totalDao.criteriaQueries(corePayable, "saveTime",new String[]{firstTime,endTime},"");
		List<CorePayable> corePayableList = 	totalDao.query(hql+" and fukuanZq  in ('0','5','30','60','75','90'," +
				"'120','135', '150','180','360')");
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			String filename = "对账单明细";
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(filename.getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet(filename, 0);
			ws.setColumnView(16, 30);
			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "采购订单号"));
			ws.addCell(new Label(2, 0, "件号"));
			ws.addCell(new Label(3, 0, "零件名称"));
			ws.addCell(new Label(4, 0, "规格"));
			ws.addCell(new Label(5, 0, "单位"));
			ws.addCell(new Label(6, 0, "含税单价"));
			ws.addCell(new Label(7, 0, "送货单号"));
			ws.addCell(new Label(8, 0, "入库时间"));
			ws.addCell(new Label(9, 0, "入库单号"));
			ws.addCell(new Label(10, 0, "合格数量"));
			ws.addCell(new Label(11, 0, "仓区"));
			ws.addCell(new Label(12, 0, "总额(含税)"));
			ws.addCell(new Label(13, 0, "供应商"));
			ws.addCell(new Label(14, 0, "税率"));
			ws.addCell(new Label(15, 0, "采购员"));
			ws.addCell(new Label(16, 0, "需求部门"));
			ws.addCell(new Label(17, 0, "不含税单价"));
			ws.addCell(new Label(18, 0, "总额(不含税)"));
			ws.addCell(new Label(19, 0, "送货数量"));
			ws.addCell(new Label(20, 0, "送货时间"));
			ws.addCell(new Label(21, 0, "供料属性"));
			ws.addCell(new Label(22, 0, "采购类型"));
			ws.addCell(new Label(23, 0, "业务件号"));
			ws.addCell(new Label(24, 0, "月结天数"));
			DecimalFormat df0 = new DecimalFormat("#.####");
			DecimalFormat df = new DecimalFormat("#.##");
			if(corePayableList!=null && corePayableList.size()>=65535){
				ws.mergeCells(1, 0, 24, 0);
				ws.addCell(new Label(0,  1, "导出数据量过大，请分批次导出。"));
			}
			for (int i = 0; i < corePayableList.size(); i++) {
				CorePayable c= corePayableList.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, c.getOrderNumber()));
				ws.addCell(new Label(2, i + 1, c.getZhaiyao()));
				ws.addCell(new Label(3, i + 1, c.getProName()));
				ws.addCell(new Label(4, i + 1, c.getSpecification()));
				ws.addCell(new Label(5, i + 1, c.getUnit()));
				ws.addCell(new jxl.write.Number(6, i + 1, Double.parseDouble(df0.format(c.getHsPrice()))));
				ws.addCell(new Label(7, i + 1, c.getDeliveryNumber()));
				ws.addCell(new Label(8, i + 1, c.getSaveTime().substring(0, 10)));
				ws.addCell(new Label(9, i + 1, c.getRukuNumber()));
				ws.addCell(new jxl.write.Number(10, i + 1, c.getNumber()));
				ws.addCell(new Label(11, i + 1, c.getCangqu()));
				Double hsSumPrice = 0d;
				if(c.getHsPrice()!=null){
					hsSumPrice = c.getHsPrice().doubleValue()*c.getNumber();
					hsSumPrice = Double.parseDouble(df.format(hsSumPrice));
				}
				Double bhsSumPrice = 0d;
				if(c.getHsPrice()!=null){
					bhsSumPrice = c.getBhsPrice().doubleValue()*c.getNumber();
					bhsSumPrice = Double.parseDouble(df.format(bhsSumPrice));
				}
				ws.addCell(new jxl.write.Number(12, i + 1, hsSumPrice));
				ws.addCell(new Label(13, i + 1, c.getSupplierName()));
				ws.addCell(new jxl.write.Number(14, i + 1, Double.parseDouble(df.format(c.getTaxprice()/100))));
				ws.addCell(new Label(15, i + 1, c.getFuzeren()));
				ws.addCell(new Label(16, i + 1, c.getDemanddept()));
				ws.addCell(new jxl.write.Number(17, i + 1, Double.parseDouble(df0.format(c.getBhsPrice()))));
				ws.addCell(new jxl.write.Number(18, i + 1, bhsSumPrice));
				ws.addCell(new jxl.write.Number(19, i + 1, c.getShNumber()==null?0:c.getShNumber()));
				ws.addCell(new Label(20, i + 1, c.getShDte()));
				ws.addCell(new Label(21, i + 1, c.getKgliao()));
				ws.addCell(new Label(22, i + 1, c.getSubjectItem()));
				ws.addCell(new Label(23, i + 1, c.getYwmarkId()));
				ws.addCell(new Label(24, i + 1, c.getFukuanZq()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	public String addPayMonth(PayMonth payMonth,List<PayMonthDetail> pmdList,String firstTime,String endTime){
		if(payMonth!=null&&pmdList!=null){
			if(firstTime!=null&&endTime!=null){
				String hql_zhusers = "From ZhUser where cmp like '%"+payMonth.getSupplierName()+"%'";
				List<ZhUser> zuList = totalDao.find(hql_zhusers);
				if(zuList.size()>1){
					return "该供应商简称存在多家供应商，请输入供应商全称!";
				}
				String s ="";
				String before = Util.getDateTime("yyyyMM");
				String cNumber = (String) totalDao.getObjectByCondition("select max(recNumber) from PayMonth where recNumber like '"
							+ before + "%'");
				int maxNo = 0;
				if (cNumber!=null) {
				String str= cNumber.substring(6);
						maxNo = Integer
								.parseInt(str);
						maxNo++;
						if (maxNo >=0 && maxNo < 10)
						{
							s = "000" + maxNo; // 1~9之间
						}
						else if (maxNo<100) {
							s = "00" + maxNo; // 10~99之间
						} else if (maxNo< 1000) {
							s = "0" + maxNo; // 100~999之间
						} else if (maxNo < 10000) {
							s = String.valueOf(maxNo); // 1000~9999之间
						}
				}else{
					s ="0001";
				}
				payMonth.setRecNumber(before+s);
				payMonth.setSupplierName(zuList.get(0).getName());
				payMonth.setFkfs(zuList.get(0).getFkfs());
				payMonth.setSupplierId(zuList.get(0).getId());
				payMonth.setJzMonth(firstTime+"-"+endTime);
				Users users = Util.getLoginUser();
				payMonth.setSaveUser(users.getName());
				payMonth.setSaveTime(Util.getDateTime("yyyy-MM-dd"));
				double all_m = 0d;
				for(PayMonthDetail pmd : pmdList){
					all_m +=Double.parseDouble(pmd.getJine());
				}
				payMonth.setJine(all_m);
				payMonth.setJineOfChiness(NumberToCN.NumberCN(all_m));
				if(totalDao.save(payMonth)){
					for(PayMonthDetail pmd : pmdList){
						pmd.setAddTime(Util.getDateTime("yyyy-MM-dd"));
						pmd.setAddUser(users.getName());
						pmd.setPayMonthId(payMonth.getId());
						totalDao.save(pmd);
					}
					return "添加成功！";
				}
			}
			return "添加失败";
		}else{
			return "申请月结信息有误，请重新填写！";
		}
	}
	public PayMonth findPMbyId(Integer id){
		if(id!=null){
			return (PayMonth)totalDao.get(PayMonth.class, id);
		}else{
			return null;
		}
	}
	public List<PayMonthDetail> findPMDByid(PayMonth pm){
		if(pm!=null){
			List<PayMonthDetail> pmdList = totalDao.find("from PayMonthDetail where payMonthId ="+pm.getId());
			return pmdList;
		}else{
			return null;
		}
	}
	public Object[] findPayMonth(PayMonth payMonth, int parseInt,int pageSize, String tag) {
		if (payMonth == null) {
			payMonth = new PayMonth();
		}
		String sql = "";
		String hql = totalDao.criteriaQueries(payMonth, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object[] o = { list, count };
		return o;
	}
	public String delcp(PayMonth payMonth){
		if(payMonth.getId()!=null){
			String hql ="from PayMonthDetail where payMonthId =?";
			List<PayMonthDetail> pdList = (List<PayMonthDetail>)totalDao.query(hql, payMonth.getId());
			if(pdList.size()>0&&pdList!=null){
				for(PayMonthDetail pd : pdList){
					totalDao.delete(pd);
				}
				if(totalDao.delete(totalDao.get(PayMonth.class, payMonth.getId()))){
					return "成功";
				}else{
					return "删除失败";
				}
			}else{
				return "删除明细出现问题，请联系管理员";
			}
		}else{
			return "数据为空";
		}
	}
}