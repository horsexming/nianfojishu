package com.task.ServerImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.IStorageService;
import com.task.entity.OaAppDetail;
import com.task.entity.OutLib;
import com.task.entity.Storage;
import com.task.entity.StorageForm;
import com.task.entity.StorageHistory;
import com.task.entity.Store;
import com.task.entity.Users;
import com.task.entity.VOStorage;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.WareBangGoogs;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedPriceCost;
import com.task.util.Util;

@SuppressWarnings( { "unchecked", "unused" })
public class StorageServiceImpl implements IStorageService {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public void add(Storage storage) {
		// TODO Auto-generated method stub
		totalDao.save(storage);
	}

	@Override
	public void del(Storage storage) {
		// TODO Auto-generated method stub
		totalDao.delete(storage);
	}

	@Override
	public Storage getStorageById(int id) {
		// TODO Auto-generated method stub
		return (Storage) totalDao.getObjectById(Storage.class, id);
	}

	@Override
	public Object[] queryStorage(Map map, int pageNo, int pageSize) {
		String hql = "from Storage s where 1=1 ";
		if (map != null) {
			if (map.get("number") != null && !"".equals(map.get("number")))
				hql += " and s.number like '%" + map.get("number") + "%'";
			if (map.get("format") != null && !"".equals(map.get("format")))
				hql += " and s.format like '%" + map.get("format") + "%'";
			if (map.get("matetag") != null && !"".equals(map.get("matetag")))
				hql += " and s.matetag like '%" + map.get("matetag") + "%'";
			if (map.get("storages") != null && !"".equals(map.get("storages")))
				hql += " and s.storehouse like '%" + map.get("storages") + "%'";
			if (map.get("category") != null && !"".equals(map.get("category")))
				hql += " and s.parClass like '%" + map.get("category") + "'";
			if (map.get("position") != null && !"".equals(map.get("position")))
				hql += " and s.position like '%" + map.get("position") + "%'";
			if (map.get("price") != null && !"".equals(map.get("price")))
				hql += " and s.price like '%" + map.get("price") + "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (s.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		List list = totalDao.findAllByPage(hql + " order by s.id desc", pageNo,
				pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	/**
	 * 更新入库明细
	 */
	@Override
	public String update(Storage sto) {
		String msg = "";
		float num = sto.getNum();
		Storage oldSto = getStorageById(sto.getId());
		Store st = oldSto.getStore();
		BigDecimal oldNum = new BigDecimal(oldSto.getNum());
		BigDecimal newNum = new BigDecimal(sto.getNum());
		int i = oldNum.compareTo(newNum);
		if (i != 0) {
			/** 判断是否大于可入库量 */
			validateNum(sto, oldSto, oldSto.getNum());
			/** 状态 */
			isDetailState(oldSto, num);
		}
		if (sto.getIsTranslation().equals("否")) {
			if (i != 0) {
				st.setCurAmount((st.getCurAmount() - oldSto.getNum())
						+ sto.getNum());
				st.setTotal((st.getTotal() - oldSto.getNum()) + sto.getNum());
			}
		} else {
			// num = sto.getCategoryNum() * sto.getConversionNum();
			// sto.setNum(num);
		}
		// 判断状态

		if ("可开票".equals(oldSto.getBzStatus())) {
			if (null != oldSto.getBudgetPrice()
					&& null != sto.getStorageInvoice()
					&& !"".equals(sto.getStorageInvoice())) {
				if (oldSto.getBudgetPrice() >= sto.getStorageTaxPrice()) {
					sto.setBzStatus("已开票");
				} else {
					return "入库价格超过了送货价";
				}
			}
		} else if ("已开票".equals(oldSto.getBzStatus())) {
			if (null != sto.getBudgetPrice() && null != sto.getStorageInvoice()
					&& !"".equals(sto.getStorageInvoice())) {

				if (sto.getBudgetPrice() < sto.getStorageTaxPrice()) {
					return "入库价格超过了送货价";
				}
			}

		} else if ("已开票".equals(oldSto.getBzStatus())) {
			if (null != sto.getBudgetPrice()
					&& null == sto.getStorageTaxPrice()) {
				sto.setBzStatus("预报账");
			}
		} else {
			if (null != oldSto.getBudgetPrice()
					&& null != sto.getStorageInvoice()
					&& !"".equals(sto.getStorageInvoice())) {

				if (oldSto.getBudgetPrice() < sto.getStorageTaxPrice()) {
					return "入库价格超过了送货价";
				}
			}
		}
		/**
		 * 历史价格比较 送货价比较 发票价比较
		 */
		// 判断欲入库状态
		String hqlStorageMiPrice = "select avg(storageTaxPrice) from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
				+ sto.getNumber() + "' and date>'2013-01-01'";
		List listAvgPrice = totalDao.query(hqlStorageMiPrice, sto.getMatetag(),
				sto.getFormat(), sto.getUnit());
		Double avgPrice = 0d;// 历史平均价格
		if (listAvgPrice.size() > 0 && null != listAvgPrice
				&& null != listAvgPrice.get(0)) {
			avgPrice = (Double) listAvgPrice.get(0);
		}
		if (null == sto.getStorageInvoice()
				|| "".equals(sto.getStorageInvoice())) {
			// 送货单比价
			if (null != sto.getBudgetPrice()) {// 可以比价
				if (sto.getBudgetPrice() > 0 && avgPrice > 0
						&& sto.getBudgetPrice() > (avgPrice + 0.1)) {
					return "送货价格超出历史平均价格，不能入库";
				} else {
					sto.setBzStatus("预报账");
				}
			} else {
				sto.setBzStatus("入库");
			}

		} else {// 比价
			if (sto.getStorageTaxPrice() > 0 && avgPrice > 0
					&& sto.getStorageTaxPrice() > (avgPrice + 0.1)) {
				return "入库价格超出历史平均价格，不能入库";
			} else {
				sto.setBzStatus("已开票");
			}

		}

		float countIn = 0f;
		if (sto.getIsTranslation().equals("否")) {
			countIn = sto.getNum();
		} else {
			countIn = sto.getCategoryNum();// 库存数量
		}
		// 10%处理
		// 入库单价与预算单价比较，超过预算单价10%不能办理入库
		String number[] = oldSto.getNumber().split(",");
		String hql = " from OaAppDetail where detailOrdNumber in(:test)";
		Query query = totalDao.createQuery(hql);
		query.setParameterList("test", number);
		List li = query.list();

		float budgetPrice = 0f;
		float countBud = 0f;
		if (li.size() > 0) {
			for (int ii = 0; ii < li.size(); ii++) {
				OaAppDetail detail = (OaAppDetail) li.get(ii);
				countBud = detail.getDetailCount();
			}
			for (int ii = 0; ii < li.size(); ii++) {
				OaAppDetail detail = (OaAppDetail) li.get(ii);
				budgetPrice += detail.getDetailBudgetMoney()
						* detail.getDetailCount() / countBud;
			}
		}

		if (null != sto.getBudgetPrice() && budgetPrice > 0
				&& sto.getBudgetPrice() > 1.1 * budgetPrice) {
			return "送货单单价超过预算价10%，禁止入库！";
		}
		if (null != sto.getStorageTaxPrice() && budgetPrice > 0
				&& sto.getStorageTaxPrice() > 1.1 * budgetPrice) {
			return "入库单价超过预算价10%，禁止入库！";
		}

		// 是否含税
		if (sto.getStorageIsTax().equals("是")) {
			if (sto.getStorageTaxPrice() != null
					&& !sto.getStorageTaxPrice().equals(""))
				st.setPrice(sto.getStorageTaxPrice()); // 获取含税单价

		} else {
			if (sto.getPrice() != null && !sto.getPrice().equals("")) {
				st.setPrice(sto.getPrice());
			}
		}
		boolean flag = false;

		/** 设置是否含税 */
		if (sto.getStorageIsTax().equals("是")) {
			if (sto.getStorageTaxRate() != null
					&& sto.getStorageTaxPrice() != null) {
				sto.setPrice((float) Math.round(sto.getStorageTaxPrice()
						/ (1 + sto.getStorageTaxRate()) * 100) / 100); // ****
				sto.setStorageTaxMoney(sto.getStorageTaxPrice() * num);
				sto.setMoney(sto.getPrice() * num);
			}
			BeanUtils.copyProperties(sto, oldSto, new String[] { "id", "store",
					"mix", "jingbanren", "admin", "adminId" });
			flag = true;
		} else {
			if (sto.getPrice() != null && !sto.getPrice().equals("")) {
				sto.setPrice(sto.getPrice());
				sto.setStorageTaxMoney(sto.getPrice() * num);
				sto.setStorageTaxPrice(sto.getPrice());// 含税价=不含税价
				sto.setMoney(sto.getPrice() * num);
			}
			BeanUtils.copyProperties(sto, oldSto, new String[] { "id", "store",
					"mix", "jingbanren", "admin", "adminId", "storageTaxPrice",
					"storageTaxRate", "taxRate", "budgetNumber" });
			flag = true;
		}

		// 是否换算处理
		if (sto.getIsTranslation() != null
				&& "否".equals(sto.getIsTranslation())) {
			oldSto.setCategoryNum(sto.getNum());// 转换后库存数量=发票数量
			oldSto.setCategory(sto.getUnit());// 库存单位
		}
		oldSto.setExchangPrice(sto.getStorageTaxPrice());// 转换后价格 = 含税价

		if (flag) {
			boolean bool = totalDao.update(oldSto);
			if (bool) {
				// 处理领用/可借用状态
				// findStorageclassify(oldSto.get);

				// 添加打印
				StorageHistory historey = new StorageHistory();
				historey.setId(sto.getId());
				historey.setDate(sto.getDate());
				historey.setParClass(sto.getParClass());
				historey.setMatetag(sto.getMatetag());
				historey.setFormat(sto.getFormat());
				historey.setNumber(sto.getNumber());
				historey.setUnit(sto.getUnit());
				historey.setNum(sto.getNum());
				historey.setStorehouse(sto.getStorehouse());
				historey.setPosition(sto.getPosition());
				historey.setJinbanren(sto.getJinbanren());
				historey.setDept(sto.getDept());
				historey.setMore(sto.getMore());
				totalDao.save(historey);

			}
		} else {
			throw new RuntimeException();
		}
		return msg;
	}

	public void isDetailState(Storage sto, Float newNum) {
		int detailCountNum = 0;
		int odId = 0;
		Set<OaAppDetail> odSet = sto.getOaDetails();
		/**
		 * 明细数
		 */
		for (Iterator<OaAppDetail> it = odSet.iterator(); it.hasNext();) {
			OaAppDetail od = it.next();
			if (detailCountNum == 0) {
				odId = od.getId();
			}
			detailCountNum += od.getDetailCount();
		}
		int storageSum = 0;
		if (odId != 0) {
			List<Storage> l = totalDao
					.query(
							"from Storage s left join fetch s.oaDetails d where d.id = ?",
							odId);
			for (Storage s : l) {
				storageSum += s.getNum();
			}
			storageSum = (int) ((storageSum - sto.getNum()) + newNum);
			String state = "";
			if (storageSum < detailCountNum) {
				state = "未入完";
			} else if (storageSum == detailCountNum) {
				state = "已入完";
			}
			for (Iterator<OaAppDetail> it = odSet.iterator(); it.hasNext();) {
				OaAppDetail oa = it.next();
				oa.setStatus(state);
			}
		}
	}

	public void validateNum(Storage sto, Storage oldSto, Float oldNum) {
		/** 判断是否有换算 */
		if (sto.getIsTranslation().equals("否")) {
			/** 统计数量 */
			float num = 0.0f;
			int oaId = 0;
			int countOaNum = 0;
			int oldCountNum = 0;
			Iterator<OaAppDetail> it = oldSto.getOaDetails().iterator();
			while (it.hasNext()) {
				OaAppDetail oa = it.next();
				if (countOaNum == 0) {
					oaId = oa.getId();
				}
				countOaNum += oa.getDetailCount();
			}
			if (oaId > 0) {
				num = sto.getNum();
				// 修改数量，大于申报数量
				if (num > countOaNum) {
					throw new RuntimeException();
				}
				List<Storage> stoList = totalDao
						.query(
								"from Storage s left join fetch s.oaDetails d where d.id = ?",
								oaId);
				if (stoList != null && stoList.size() > 0) {
					for (Storage s : stoList) {
						oldCountNum += s.getNum();
					}
				}
				oldCountNum -= oldNum;
				oldCountNum = countOaNum - oldCountNum;
				if (num > oldCountNum) {
					throw new RuntimeException();
				}
			}
		}
	}

	@Override
	public void exportExcel(Map map) {
		String hql = "from Storage s where 1=1 ";
		if (map != null) {
			if (map.get("storages") != null)
				hql += " and s.storehouse like '%" + map.get("storages") + "%'";
			if (map.get("category") != null)
				hql += " and s.parClass = '" + map.get("category") + "'";
			if (map.get("place") != null)
				hql += " and s.place like '%" + map.get("place") + "%'";
			if (map.get("startTime") != null && map.get("endTime") != null)
				hql += " and (s.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
		}
		hql += " order by s.id desc";
		List list = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("入库".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("入库数据", 0);
			ws.setColumnView(0, 18);
			ws.setColumnView(1, 30);
			ws.setColumnView(2, 24);
			ws.setColumnView(7, 18);
			ws.setColumnView(7, 20);
			ws.setColumnView(13, 30);
			ws.setColumnView(14, 60);
			ws.addCell(new Label(0, 0, "日期"));
			ws.addCell(new Label(1, 0, "品名"));
			ws.addCell(new Label(2, 0, "规格"));
			ws.addCell(new Label(3, 0, "车型"));
			ws.addCell(new Label(4, 0, "单位"));
			ws.addCell(new Label(5, 0, "库房"));
			ws.addCell(new Label(6, 0, "类别"));
			ws.addCell(new Label(7, 0, "库位"));
			ws.addCell(new Label(8, 0, "编号"));
			ws.addCell(new Label(9, 0, "数量"));
			ws.addCell(new Label(10, 0, "金额"));
			ws.addCell(new Label(11, 0, "入库人"));
			ws.addCell(new Label(12, 0, "经办人"));
			ws.addCell(new Label(13, 0, "供应商"));
			ws.addCell(new Label(14, 0, "备注"));
			for (int i = 0; i < list.size(); i++) {
				Storage sto = (Storage) list.get(i);
				ws.addCell(new Label(0, i + 1, sto.getDate().toString()));
				ws.addCell(new Label(1, i + 1, sto.getMatetag()));
				ws.addCell(new Label(2, i + 1, sto.getFormat()));
				ws.addCell(new Label(3, i + 1, sto.getCarModel()));
				ws.addCell(new Label(4, i + 1, sto.getUnit()));
				ws.addCell(new Label(5, i + 1, sto.getStorehouse()));
				ws.addCell(new Label(6, i + 1, sto.getParClass()));
				ws.addCell(new Label(7, i + 1, sto.getPosition()));
				ws.addCell(new Label(8, i + 1, sto.getNumber()));
				ws.addCell(new jxl.write.Number(9, i + 1,
						sto.getNum() == null ? 0 : sto.getNum()));
				ws.addCell(new jxl.write.Number(10, i + 1,
						sto.getMoney() == null ? 0 : sto.getMoney()));
				ws.addCell(new Label(11, i + 1, sto.getAdmin()));
				ws.addCell(new Label(12, i + 1, sto.getJinbanren()));
				ws.addCell(new Label(13, i + 1, sto.getStorageCompany()));
				ws.addCell(new Label(14, i + 1, sto.getMore()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询采购订单
	 */
	@Override
	public Object[] queryPurchase(String applyForNum, int pageNo, int pageSize) {
		// String
		// hql="from OaAppDetail d where detailStatus in('采购中','到货') and detailOrdNumber in(select aplyFormOrdnumber from OaApplyForm where appBarcode='"+applyForNum+"')"
		// +
		// "or detailStatus='入库' and detailOrdNumber in(select aplyFormOrdnumber from OaApplyForm where appBarcode='"+applyForNum+"')"
		// +
		// "and d.detailCount>(select sum(Storage_num) from Storage where Storage_number=d.detailSeqNum)";
		// String
		// hql="from OaAppDetail d where (detailStatus in('采购中','到货') and detailOrdNumber in(select aplyFormOrdnumber from OaApplyForm where appBarcode='"+applyForNum+"'))"
		// +
		// "or (detailStatus='入库' and detailOrdNumber in(select aplyFormOrdnumber from OaApplyForm where appBarcode='"+applyForNum+"')"
		// +
		// "and d.detailCount>(select sum(num) from Storage where number=d.detailSeqNum))";
		// String hql =
		// "from OaAppDetail d where detailStatus in('采购中','到货') and detailOrdNumber in "
		// +
		// "(select aplyFormOrdnumber from OaApplyForm where appBarcode='"+applyForNum+"')";

		// d.detail_count>(select sum(s.Storage_num)
		// from storage s where d.detail_seqNum=s.Storage_number) and
		// d.detail_ordNumber='shPD20121205'
		String hql = "from OaAppDetail d where d.detailCount>(select CASE WHEN sum(num) IS NULL THEN 0 ELSE sum(num) END from Storage s where s.number=d.detailSeqNum) "
				+ " and detailOrdNumber in(select aplyFormOrdnumber from OaApplyForm where appBarcode='"
				+ applyForNum + "') and d.detailStatus in('采购中','到货')";
		// String hql =
		// "from OaAppDetail d where  (status = '未入完' and detailOrdNumber in (select aplyFormOrdnumber from OaApplyForm "
		// + "where appBarcode='"
		// + applyForNum
		// +
		// "'))  or (detailOrdNumber in (select aplyFormOrdnumber from OaApplyForm where appBarcode='"
		// + applyForNum + "') and status is null) ";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public OaAppDetail getOaAppDetail(int id) {
		OaAppDetail detail = (OaAppDetail) totalDao.getObjectById(
				OaAppDetail.class, id);
		String hql = "select sum(num) from Storage where number='"
				+ detail.getDetailSeqNum() + "'";
		List listsum = totalDao.query(hql);
		if (listsum.size() > 0 && listsum != null) {
			float sum = (Float) listsum.get(0);
			if (sum > 0) {
				detail.setDetailCount(detail.getDetailCount() - sum);
			}
		}
		return detail;
	}

	@Override
	public List printStorage(int[] selected) {
		List<StorageHistory> l = new ArrayList<StorageHistory>();
		for (int id : selected) {
			StorageHistory sh = getStorageHistoryById(id);
			l.add(sh);
		}
		return l;
	}

	@Override
	public void clearPrint() {
		List<Integer> list = (List<Integer>) ActionContext.getContext()
				.getSession().get("storagePrint");
		if (list != null) {
			ActionContext.getContext().getSession().remove("storagePrint");
		}
	}

	/**
	 * 入库操作
	 */
	public String storageProducts(StorageForm sf, VOStorage vost) {
		String msg = "";
		OaAppDetail oad = null;
		int countOaNum = 0; // 明细总数
		boolean bol = false;// true:扫描入库，FALSE：手动入库
		if (vost.getJump() == null || vost.getJump().equals("")) {
			msg = "入库失败!";
			return msg;
		} else {
			if (vost.getJump().equals("scanning")) {// 扫描入库
				bol = true;
			} else if (vost.getJump().equals("manual")) {// 手动入库
				bol = false;
			}else {
				bol = false;
			}
		}
		List<OaAppDetail> oaDetailLis = new ArrayList<OaAppDetail>();
		float budgetPrice = 0f;// 单价上限
		// 是否单位转换
		float countIn = 0f;
		if (sf.getIsTranslation().equals("否")) {
			countIn = sf.getNum();
		} else {
			countIn = sf.getCategoryNum();// 库存数量
		}
		if (bol) {
			// 扫描入库
			oad = (OaAppDetail) totalDao.getObjectById(OaAppDetail.class, vost
					.getOaDetailId());
		} else {
			// 批量入库

			if (vost.getNumber() != null) {
				String[] oaId = vost.getNumber().split(",");
				for (int i = 0; i < oaId.length; i++) {
					// OaAppDetail oa =
					// getOaAppDetail(Integer.parseInt(oaId[i]));
					OaAppDetail oa = (OaAppDetail) totalDao.getObjectById(
							OaAppDetail.class, Integer.parseInt(oaId[i]));
					countOaNum += oa.getDetailCount();
					// budgetPrice+=oa.getDetailBudgetMoney()*oa.getDetailCount()/countIn;
					oaDetailLis.add(oa);
				}
			}
			if (vost.getNumber() != null) {
				String[] oaId = vost.getNumber().split(",");
				for (int i = 0; i < oaId.length; i++) {
					// OaAppDetail oa =
					// getOaAppDetail(Integer.parseInt(oaId[i]));
					OaAppDetail oa = (OaAppDetail) totalDao.getObjectById(
							OaAppDetail.class, Integer.parseInt(oaId[i]));
					budgetPrice += oa.getDetailBudgetMoney()
							* oa.getDetailCount() / countOaNum;
				}
			}
		}
		String mix = "";
		/** 获取单价 */
		float price = 0f; // 单价
		if (sf.getStorageIsTax().equals("是")) {
			if (sf.getStorageTaxPrice() != null
					&& !sf.getStorageTaxPrice().equals(""))
				price = sf.getStorageTaxPrice(); // 获取含税单价

		} else {
			if (sf.getUnitPrice() != null && !sf.getUnitPrice().equals("")) {
				price = sf.getUnitPrice();
			}
		}

		/** 获取数量 */
		float num = 0.0f;
		/*
		 * if (bol == false) { oad = oaDetailLis.get(0); }
		 */
		// 判断欲入库状态
		String hqlStorageMiPrice = "select avg(storageTaxPrice) from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
				+ sf.getNumber() + "' and date>'2012-01-01'";
		List listAvgPrice = totalDao.query(hqlStorageMiPrice, sf.getMatetag(),
				sf.getFormat(), sf.getUnit());
		Double avgPrice = 0d;// 历史平均价格
		if (listAvgPrice.size() > 0 && null != listAvgPrice
				&& null != listAvgPrice.get(0)) {
			avgPrice = (Double) listAvgPrice.get(0);
		}
		List<QuotedPriceCost> qpCostList=new ArrayList<QuotedPriceCost>();
		if (null == sf.getStorageInvoice() || "".equals(sf.getStorageInvoice())) {
			// 送货单比价
			if (null != sf.getBudgetPrice()) {// 可以比价
				if (sf.getBudgetPrice() > 0 && avgPrice > 0
						&& sf.getBudgetPrice() > (avgPrice + 0.1)) {
					return "送货价格超出历史平均价格，不能入库";
				} else {
					sf.setBzStatus("预报账");
				}
			} else {
				sf.setBzStatus("入库");
			}

		} else {// 比价
			if (price > 0 && avgPrice > 0 && price > (avgPrice + 0.1)) {
				return "入库价格超出历史平均价格，不能入库";
			} else {
				sf.setBzStatus("已开票");
				//有发票,计算工装成本
				if(sf.getStorehouse().equals("工装库")){
					if(oad!=null){
					 String detailItemId = 	oad.getDetailItemId();//订单编号
					 if(detailItemId!=null&&detailItemId.length()>0){
						List<QuotedPrice> qpList= totalDao.query("from QuotedPrice where id in(select quotedPrice.rootId from QuotedProcessInfor where gongzhuangNumber=?) " +
						 		"and proId in(select id from ProjectManage where projectNum=?)", oad.getDetailFormat(),detailItemId);
						if(qpList==null||qpList.size()>0){
							Float money=price*sf.getNum();
							Float singleQPPrice =money/qpList.size();
							Users user = Util.getLoginUser();
							for(QuotedPrice qp:qpList){
								if(qp.getRealAllfy()==null){
									qp.setRealAllfy((double)singleQPPrice);
								}else{
									qp.setRealAllfy(qp.getRealAllfy()+singleQPPrice);
								}
								QuotedPriceCost quotedPriceCost = new QuotedPriceCost();
								quotedPriceCost.setOadetailId(oad.getId());
								quotedPriceCost.setProStatus("首件阶段");
								quotedPriceCost.setTzMoney(0d);
								quotedPriceCost.setUserName(user.getName());
								quotedPriceCost.setUserCode(user.getCode());
								quotedPriceCost.setDept(user.getDept());
								quotedPriceCost.setSource("工装申报");
								quotedPriceCost.setAddTime(Util.getDateTime());
								quotedPriceCost.setCostType("工装费");
								quotedPriceCost.setMoney((double)singleQPPrice);
								quotedPriceCost.setMarkId(qp.getMarkId());
								quotedPriceCost.setQpId(qp.getId());
								if(qp.getYingkui()==null){
									qp.setYingkui(0-qp.getAllFeiyong());
								}else{
									qp.setYingkui(qp.getYingkui()-quotedPriceCost.getMoney());
								}
								totalDao.update(qp);
								quotedPriceCost.setApplyStatus("同意");
								totalDao.save(quotedPriceCost);
								qpCostList.add(quotedPriceCost);
							}
						}
					 }
					}
					if(oaDetailLis.size()>0){
						for (OaAppDetail o : oaDetailLis) {
							if (oad!=null&&oad.getDetailItemId()!=null) {
								String detailItemId = oad.getDetailItemId();// 订单编号
								if (detailItemId != null
										&& detailItemId.length() > 0) {
									List<QuotedPrice> qpList = totalDao
									.query(
											"from QuotedPrice where id in(select quotedPrice.rootId from QuotedProcessInfor where gongzhuangNumber=?) "
											+ "and proId in(select id from ProjectManage where projectNum=?)",
											oad.getDetailFormat(),
											detailItemId);
									if (qpList == null || qpList.size() > 0) {
										Float money = price * sf.getNum();
										Float singleQPPrice = money / qpList.size();
										Users user = Util.getLoginUser();
										for (QuotedPrice qp : qpList) {
											if (qp.getRealAllfy() == null) {
												qp.setRealAllfy((double)singleQPPrice);
											} else {
												qp.setRealAllfy(qp.getRealAllfy()
														+ singleQPPrice);
											}
											QuotedPriceCost quotedPriceCost = new QuotedPriceCost();
											quotedPriceCost.setOadetailId(o.getId());
											quotedPriceCost.setProStatus("首件阶段");
											quotedPriceCost.setTzMoney(0d);
											quotedPriceCost.setUserName(user
													.getName());
											quotedPriceCost.setUserCode(user
													.getCode());
											quotedPriceCost.setDept(user.getDept());
											quotedPriceCost.setSource("工装申报");
											quotedPriceCost.setAddTime(Util
													.getDateTime());
											quotedPriceCost.setCostType("工装费");
											quotedPriceCost.setMoney((double)singleQPPrice);
											quotedPriceCost.setMarkId(qp
													.getMarkId());
											quotedPriceCost.setQpId(qp.getId());
											if (qp.getYingkui() == null) {
												qp.setYingkui(0 - qp
														.getAllFeiyong());
											} else {
												qp.setYingkui(qp.getYingkui()
														- quotedPriceCost
														.getMoney());
											}
											totalDao.update(qp);
											quotedPriceCost.setApplyStatus("同意");
											totalDao.save(quotedPriceCost);
											qpCostList.add(quotedPriceCost);
										}
									}
								}
							}
						}
					}
				}
			}

		}

		// 入库单价与预算单价比较，超过预算单价10%不能办理入库
		boolean boRuku = true;// 不超过10%，不能入库
		if (bol) {// 扫描入库
			budgetPrice = oad.getDetailBudgetMoney();// 预算单价
		}
		if (price > 1.1 * budgetPrice) {
			boRuku = false;
		} else {
			boRuku = true;
		}
		if (sf.getBudgetPrice() != null) {
			if (sf.getBudgetPrice() > 1.1 * budgetPrice) {
				return "送货单单价超过预算价10%，禁止入库！";
			}
		}
		if (!boRuku) {
			return "入库单价超过预算价10%，禁止入库！";
		}
		// 入库与预算对接

		// 入库数量与申报数量对比
		Object[] obj = isNumberOfVerification(sf, bol, oad, countOaNum);
		if (obj[0] != null) {
			return (String) obj[0];
		} else {
			num = (Float) obj[1];
		}
		/** 配置入库信息 */
		Storage sto = assignmentOfStorageInfo(sf, oad, bol, oaDetailLis, num);
		if(qpCostList.size()>0){
			for(QuotedPriceCost qpcost:qpCostList){
				qpcost.setStorageId(sto.getId());
				totalDao.update(qpcost);
			}
		}
		/** 配置库存信息 */
		Store store = assignmentOfStoreInfo(sf, price, num, oad, sto);
		/** 设置oaDetail的状态 */
		oaDetailStatus(oad, countOaNum, oaDetailLis);
		if (bol) {
			updateOaDetail(oad, sto);
			totalDao.update(oad);
		} else {
			for (OaAppDetail oa : oaDetailLis) {
				if(store!=null&&oa!=null&&oa.getRgdetailCount()!=null){
					List<WareBangGoogs> wbg1 = totalDao.query("from WareBangGoogs where fk_oadetail_id = ? and status = '已入柜'", oa.getId());
					if (wbg1!=null&&wbg1.size()>0) {
						for (WareBangGoogs wg : wbg1) {
							wg.setFk_store_id(store.getId());
							wg.setStatus("入库");
							totalDao.update(wg);
						}
					}
				}
				updateOaDetail(oa, sto);
				totalDao.update(oa);
			}
		}
		StorageHistory sh = new StorageHistory();
		BeanUtils.copyProperties(sto, sh);
		addStorageHistory(sh);
		return "入库成功!";
	}

	// 更新申报明细
	private void oaDetailStatus(OaAppDetail oad, int countOaNum,
			List<OaAppDetail> oaDetailLis) {
		int storageSum = 0;
		List<Storage> l = null;
		if (oad != null) {
			l = totalDao
					.query(
							"from Storage s left join fetch s.oaDetails d where d.id = ?",
							oad.getId());
		} else {
			l = totalDao
					.query(
							"from Storage s left join fetch s.oaDetails d where d.id = ?",
							oad);
		}

		for (Storage s : l) {
			storageSum += s.getNum();
		}
		if (oaDetailLis == null || oaDetailLis.size() == 0) {
			if (storageSum < oad.getDetailCount()) {
				oad.setStatus("未入完");
			} else if (oad.getDetailCount() == storageSum) {
				oad.setStatus("已入完");
			}
		} else {
			if (storageSum < countOaNum) {
				for (OaAppDetail oa : oaDetailLis)
					oa.setStatus("未入完");
			} else if (storageSum == countOaNum) {
				for (OaAppDetail oa : oaDetailLis)
					oa.setStatus("已入完");
			}
		}
	}

	// 保存库存信息
	public Store assignmentOfStoreInfo(StorageForm sf, float price, float num,
			OaAppDetail oa, Storage sto) {
		List<Store> l = null;
		if (oa != null) {
			l = totalDao.query("from Store s where s.number = ? ", oa
					.getDetailSeqNum());
		} else {
			l = totalDao.query("from Store s where s.number = ? ", oa);
		}

		// List<Store> l = totalDao.query("from Store where mix = ?", mix);
		/** 如果没有记录 */
		if (l.size() == 0) {
			Store newSt = new Store();
			BeanUtils.copyProperties(sf, newSt);
			newSt.setPrice(price); // 设置单价
			// newSt.setMix(mix); //设置合成码
			newSt.setStartDate(sf.getDate()); // 设置日期
			if (sf.getBeginning_num() != null) {
				newSt.setCurAmount(num + sf.getBeginning_num()); // 设置当前数量
				newSt.setTotal(num + sf.getBeginning_num()); // 设置总数
			} else {
				newSt.setCurAmount(num + 0F); // 设置当前数量
				newSt.setTotal(num + 0F); // 设置总数
			}
			newSt.getStorages().add(sto);
			sto.setStore(newSt);
			newSt.getStorages().add(sto);
			if(totalDao.save(newSt))return newSt;
		} else {
			/** 对库存记录进行累加 */
			Store oldSt = l.get(0);
			oldSt.setCurAmount(oldSt.getCurAmount() + num);// 当前数量累加
			oldSt.setTotal(oldSt.getTotal() + num); // 总数量累加
			/** 设置位置 */
			if (sf.getPlace() != null) {
				String placeStr = sf.getPlace().replace("，", ",");
				if (oldSt.getPlace() != null && !oldSt.getPlace().equals("")) {
					String[] placeAry = oldSt.getPlace().split(",");
					boolean bo = true;
					for (String s : placeAry) {
						if (s.equals(placeStr) || s.equalsIgnoreCase(placeStr))
							bo = false;
					}
					if (bo)
						oldSt.setPlace(oldSt.getPlace() + "," + placeStr);
				} else {
					oldSt.setPlace(placeStr);
				}
			}
			oldSt.getStorages().add(sto);
			sto.setStore(oldSt);
			if(totalDao.update(oldSt))return oldSt;
		}
		return null;
	}

	// 入库明细
	public Storage assignmentOfStorageInfo(StorageForm sf, OaAppDetail oad,
			boolean bol, List<OaAppDetail> oaDetailLis, float num) {
		Users u = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		Storage sto = new Storage();
		BeanUtils.copyProperties(sf, sto);// 将双方对象中拥有相同属性的值直接赋值
		sto.setBudgetPrice(sf.getBudgetPrice());// 设置送货单价格
		sto.setAdmin(u.getName()); // 设置 人员
		sto.setAdminId(u.getId());
		sto.setDept(sf.getAppDept());// 设置部门
		sto.setPosition(sf.getPlace());// 设置仓库
		/** 设置是否含税 */
		if (sf.getStorageIsTax().equals("是")) {
			if (sf.getStorageTaxRate() != null
					&& sf.getStorageTaxPrice() != null) {
				sto.setPrice((float) Math.round(sf.getStorageTaxPrice()
						/ (1 + sf.getStorageTaxRate()) * 100) / 100); // 计算不含税单价
			}
		} else {
			if (sf.getUnitPrice() != null && !sf.getUnitPrice().equals("")) {
				sto.setPrice(sf.getUnitPrice());// 不含税价
				sto.setStorageTaxPrice(sf.getUnitPrice());// 含税价=不含税价
			}
		}
		if (sf.getStorageTaxPrice() != null) {
			sto.setStorageTaxMoney(sf.getStorageTaxPrice() * sf.getNum());// 含税总额
		} else {
			sto.setStorageTaxMoney(0F * sf.getNum());// 含税总额
		}
		if (sto.getPrice() != null) {
			sto.setMoney(sto.getPrice() * sf.getNum());// 不含税总额
		} else {
			sto.setMoney(0F * sf.getNum());// 不含税总额
		}
		// 是否换算处理
		if (sf.getIsTranslation() != null && "否".equals(sf.getIsTranslation())) {
			sto.setCategoryNum(sf.getNum());// 转换后库存数量=发票数量
			sto.setCategory(sf.getUnit());// 库存单位
		}
		if (sto.getStorageTaxPrice() != null) {
			sto.setExchangPrice(sto.getStorageTaxPrice() * sto.getNum()
					/ sto.getCategoryNum());// 转换后价格 = 含税价*发票数量/库存数量
		} else {
			sto.setExchangPrice(0F * sto.getNum() / sto.getCategoryNum());// 转换后价格
			// =
			// 含税价*发票数量/库存数量
		}

		sf.setUnit(sto.getCategory());// 统一库存单位

		if (bol) {
			sto.getOaDetails().add(oad);
			oad.getStorages().add(sto);
		} else {
			String seqNum = "";
			for (OaAppDetail oa : oaDetailLis) {
				String hql = "select sum(num) from Storage where number='"
						+ oa.getDetailSeqNum() + "'";
				List listsum = totalDao.query(hql);
				if (listsum.size() > 0 && listsum != null) {
					float sum = (Float) listsum.get(0) + sf.getNum();
					if (sum > 0) {
						// detail.setDetailCount(detail.getDetailCount() - sum);
						if (oa.getDetailCount() - sum <= 0) {
							oa.setDetailStatus("入库");// 更新状态为入库
						}
					}
				}

				oa.getStorages().add(sto);
				sto.getOaDetails().add(oa);
				seqNum += oa.getDetailSeqNum() + ",<br/>";// 组合编号
			}
			sto.setNumber(seqNum.substring(0, seqNum.lastIndexOf(",")));
			sf.setNumber(sto.getNumber());
		}
		sto.setAddDateTime(Util.getDateTime());// 添加时间

		if (totalDao.save(sto))
			addser(sto);
		return sto;
	}

	public void addser(Storage sto) {
		DepositCabinet cabinet = new DepositCabinet();
		cabinet.setDepArticleName(sto.getMatetag());
		cabinet.setDepArticleFormat(sto.getFormat());
		cabinet.setArtOACoding(sto.getNumber());
		cabinet.setArtUnit(sto.getCategory());
		cabinet.setArtQuantity(sto.getNum().intValue());
		cabinet.setActualDepositQuantity(0);
		cabinet.setAlreadyReceivedQuantity(0);
		cabinet.setApplyDept(sto.getDept());
		cabinet.setDepositStatus("待入柜");
		cabinet.setPrintStatus("1");
		cabinet.setAddTime(Util.getDateTime());
		totalDao.save(cabinet);
	}

	public Object[] isNumberOfVerification(StorageForm sf, boolean bol,
			OaAppDetail oad, Integer countOaNum) {
		String msg = "入库失败!入库数量不能大于申请采购数量!";
		float num = 0.0f;
		if (sf.getIsTranslation().equals("否")) {
			num = sf.getNum(); // 获取前台入库数量
			if (oad != null) {
				if (bol) {
					if (num > oad.getDetailCount()) {
						return new Object[] { msg, num };
					}
				} else {
					if (num > countOaNum) {
						return new Object[] { msg, num };
					}
				}
				List<Storage> stoLis = totalDao
						.query(
								"from Storage s left join fetch s.oaDetails d where d.id = ?",
								oad.getId());
				// List<Storage> stoLis =
				// totalDao.query("from Storage s where s.matetag = ? and s.format = ?",
				// oad.getDetailAppName(),oad.getDetailFormat());
				if (stoLis != null && stoLis.size() > 0) {
					int countNum = 0;
					for (Storage s : stoLis) {
						countNum += s.getNum();
					}
					countNum += num;
					if (countNum > oad.getDetailCount()) {
						return new Object[] { msg, num };
					}
				}
			}
		} else {
			num = sf.getCategoryNum();
			// num = sf.getCategoryNum() * sf.getConversionNum();
		}
		return new Object[] { null, num };
	}

	// 更改深白明细
	public void updateOaDetail(OaAppDetail oad, Storage sto) {
		/** 修改OaDetail */
		oad.setDetailPrice(sto.getStorageTaxPrice());
		if(oad.getDetailCount().equals(oad.getRgdetailCount())) oad.setStatus("已入完");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		oad.setDetailarrivaltime(sdf.format(new Date()));
		Float realMoney = null;
		if ("是".equals(sto.getStorageIsTax())) {
			realMoney = oad.getDetailRealMoney();
		}
		if (realMoney == null || realMoney.equals("") || realMoney == 0) {
			oad.setDetailRealMoney(sto.getStorageTaxMoney());
		} else {
			if (sto.getStorageTaxMoney() != null) {
				oad.setDetailRealMoney(realMoney + sto.getStorageTaxMoney());
			} else {
				oad.setDetailRealMoney(realMoney);
			}
		}
	}

	@Override
	public Object[] queryDetailByCondition(Map map, int pageNo, int pageSize) {
		// String hql =
		// "from OaAppDetail d where detailStatus in('采购中','到货','入库') and detailOrdNumber in "
		// + "(select aplyFormOrdnumber from OaApplyForm o where 1=1";

		String hql = "from OaAppDetail d where d.detailCount>(select CASE WHEN sum(num) IS NULL THEN 0 ELSE sum(num) END "
				+ "from Storage s where s.number=d.detailSeqNum) "
				+ " and d.detailStatus in ('采购中','到货','报账') and (d.status='未入完' or d.status is null)";

		if (map != null) {
			if (map.get("number") != null) {
				hql += " and d.detailSeqNum='" + map.get("number") + "')";
			}
			if (map.get("name") != null)
				hql += " and d.detailAppName like '%" + map.get("name") + "%'";
			if (map.get("format") != null)
				hql += " and d.detailFormat like '%" + map.get("format") + "%'";
			if (map.get("dept") != null)
				hql += " and d.detailAppDept like '%" + map.get("dept") + "%'";
			if (map.get("month") != null)
				hql += " and d.detailPlanMon = '" + map.get("month") + "'";
			if (map.get("status") != null)
				hql += " or d.status = '" + map.get("status") + "'";
		} else {
			// hql += ")";//if map 为空 多一个)然后倒序失效
		}
		hql += " order by d.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	@Override
	public Object[] getCountProcurement(int[] selected) {
		OaAppDetail packgeDetai = new OaAppDetail();
		StringBuffer sb = new StringBuffer("");
		int countNum = 0;
		for (int i = 0; i < selected.length; i++) {
			OaAppDetail det = getOaAppDetail(selected[i]);
			if (i == 0) {
				BeanUtils.copyProperties(det, packgeDetai, new String[] { "id",
						"detailCount", "storages" });
			}
			if (det.getDetailAppName().equals(packgeDetai.getDetailAppName())
					&& det.getDetailFormat().equals(
							packgeDetai.getDetailFormat())) {
				countNum += det.getDetailCount();
				sb.append(selected[i]).append(",");

			}
		}
		packgeDetai.setDetailCount(Float.valueOf(countNum));
		String str = sb.substring(0, sb.lastIndexOf(","));
		return new Object[] { packgeDetai, str };
	}

	public void delStorageById(int id) {
		Storage sto = (Storage) totalDao.getObjectById(Storage.class, id);
		Store store = sto.getStore();

		store.getStorages().remove(sto);
		store.setCurAmount(store.getCurAmount() - sto.getCategoryNum()); // 设置当前数量
		store.setTotal(store.getTotal() - sto.getCategoryNum()); // 设置总数
		totalDao.update(store);

		Iterator<OaAppDetail> it = sto.getOaDetails().iterator();
		while (it.hasNext()) {
			OaAppDetail oa = it.next();
			// oa.setStatus(null);
			Set<Storage> st = oa.getStorages();
			st.remove(sto);
			totalDao.update(oa);
		}
		sto.setStore(null);
		sto.setOaDetails(null);
		del(sto);
	}

	public void addStorageHistory(StorageHistory ps) {
		totalDao.save(ps);
	}

	@Override
	public Object[] queryStorageHistory(Map map, int pageNo, int pageSize) {
		String hql = "from StorageHistory s where 1=1 ";
		if (map != null) {
			if (map.get("startTime") != null && map.get("endTime") != null) {
				hql += " and (s.date between '" + map.get("startTime")
						+ "' and '" + map.get("endTime") + "')";
			}
		}
		hql += " order by s.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		return new Object[] { list, count };
	}

	public StorageHistory getStorageHistoryById(int id) {
		return (StorageHistory) totalDao
				.getObjectById(StorageHistory.class, id);
	}

	/***
	 * 通过申报编号以及状态查询其他入库信息
	 * 
	 * @param number
	 * @return
	 */
	@Override
	public int findStorageclassify(String number, String classify) {
		String hql = "from Storage where ";
		return 0;
	}

	/***
	 * 手动入库
	 * 
	 * @param sf
	 * @param vost
	 * @return
	 */
	@Override
	public String addStorage(StorageForm sf, VOStorage vost) {
		// 是否换算
		if (sf.getIsTranslation().equals("否")) {
			sf.setCategoryNum(sf.getNum());// 库存数量和发票数量一致
			sf.setCategory(sf.getUnit());// 库存单位和发票单位一致
		}
		/** 获取单价 */
		float price = 0f; // 单价
		if (sf.getStorageIsTax().equals("是")) {
			if (sf.getStorageTaxPrice() != null
					&& !sf.getStorageTaxPrice().equals(""))
				price = sf.getStorageTaxPrice(); // 获取含税单价
		} else {
			if (sf.getUnitPrice() != null && !sf.getUnitPrice().equals("")) {
				price = sf.getUnitPrice();
			}
		}

		/** 配置入库信息 */
		Users u = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);
		Storage sto = new Storage();
		BeanUtils.copyProperties(sf, sto);
		sto.setBudgetPrice(sf.getBudgetPrice());// 设置送货单价格
		sto.setAdmin(u.getName()); // 设置 人员
		sto.setAdminId(u.getId());
		sto.setDept(sf.getAppDept());// 设置部门
		sto.setPosition(sf.getPlace());// 设置仓库
		/** 设置是否含税 */
		if (sf.getStorageIsTax().equals("是")) {
			if (sf.getStorageTaxRate() != null
					&& sf.getStorageTaxPrice() != null) {
				sto.setPrice((float) Math.round(sf.getStorageTaxPrice()
						/ (1 + sf.getStorageTaxRate()) * 100) / 100); // 计算不含税单价
			}
		} else {
			if (sf.getUnitPrice() != null && !sf.getUnitPrice().equals("")) {
				sto.setPrice(sf.getUnitPrice());// 不含税价
				sto.setStorageTaxPrice(sf.getUnitPrice());// 含税价=不含税价
			}
		}
		if (sf.getStorageTaxPrice() == null) {
			sto.setStorageTaxMoney(0 * sf.getNum());// 含税总额
		} else {
			sto.setStorageTaxMoney(sf.getStorageTaxPrice() * sf.getNum());// 含税总额
		}
		if (sto.getPrice() == null) {
			sto.setMoney(0 * sf.getNum());// 不含税总额
		} else {
			sto.setMoney(sto.getPrice() * sf.getNum());// 不含税总额
		}

		// 是否换算处理
		if (sf.getIsTranslation() != null && "否".equals(sf.getIsTranslation())) {
			sto.setCategoryNum(sf.getNum());// 转换后库存数量=发票数量
			sto.setCategory(sf.getUnit());// 库存单位
		}
		if (sto.getStorageTaxPrice() == null) {
			sto.setExchangPrice(0 * sto.getNum() / sto.getCategoryNum());// 转换后价格
			// =
			// 含税价*发票数量/库存数量
		} else {
			sto.setExchangPrice(sto.getStorageTaxPrice() * sto.getNum()
					/ sto.getCategoryNum());// 转换后价格 = 含税价*发票数量/库存数量
		}

		sf.setUnit(sto.getCategory());// 统一库存单位
		sto.setAddDateTime(Util.getDateTime(null));// 添加时间
		totalDao.save(sto);

		/** 配置库存信息 */
		List<Store> l = totalDao.query(
				"from Store  where matetag = ? and format=?", sto.getMatetag(),
				sto.getFormat());
		float num = 0F;// 库存数量
		if (sf.getBeginning_num() != null) {
			num = sf.getCategoryNum() + sf.getBeginning_num();
		} else {
			num = sf.getCategoryNum() + 0F;
		}

		/** 如果没有记录 */
		if (l.size() == 0) {
			Store newSt = new Store();
			BeanUtils.copyProperties(sf, newSt);
			newSt.setPrice(price); // 设置单价
			newSt.setStartDate(sf.getDate()); // 设置日期
			newSt.setCurAmount(num); // 设置当前数量
			newSt.setTotal(num); // 设置总数
			newSt.getStorages().add(sto);
			sto.setStore(newSt);
			newSt.getStorages().add(sto);
			totalDao.save(newSt);
		} else {
			/** 对库存记录进行累加 */
			Store oldSt = l.get(0);
			oldSt.setCurAmount(oldSt.getCurAmount() + num);// 当前数量累加
			oldSt.setTotal(oldSt.getTotal() + num); // 总数量累加
			/** 设置位置 */
			if (sf.getPlace() != null) {
				String placeStr = sf.getPlace().replace("，", ",");
				if (oldSt.getPlace() != null && !oldSt.getPlace().equals("")) {
					String[] placeAry = oldSt.getPlace().split(",");
					boolean bo = true;
					for (String s : placeAry) {
						if (s.equals(placeStr) || s.equalsIgnoreCase(placeStr))
							bo = false;
					}
					if (bo)
						oldSt.setPlace(oldSt.getPlace() + "," + placeStr);
				} else {
					oldSt.setPlace(placeStr);
				}
			}
			oldSt.getStorages().add(sto);
			sto.setStore(oldSt);
			totalDao.update(oldSt);
		}
		/** 打印任务 */
		StorageHistory sh = new StorageHistory();
		BeanUtils.copyProperties(sto, sh);
		addStorageHistory(sh);
		return "入库成功!";
	}

	@SuppressWarnings("deprecation")
	@Override
	public Storage getnumberId(String str) {
		// TODO Auto-generated method stub
		return (Storage) totalDao.getObjectByQuery(
				"from Storage where Storage_number = ?", str);
	}

	/**
	 * 对比采购和入库中的数量。20150809——李聪
	 */
	@Override
	public OaAppDetail getOaAppDetail1(int id) {
		// TODO Auto-generated method stub
		OaAppDetail detail1 = (OaAppDetail) totalDao.getObjectById(
				OaAppDetail.class, id);
		String hql = "select sum(num) from Storage where number='"
				+ detail1.getDetailSeqNum() + "'";
		List listsum = totalDao.query(hql);
		if (listsum.size() > 0 && listsum != null) {
			float sum = (Float) listsum.get(0);
			if (sum > 0) {
				detail1.setDetailCount(detail1.getDetailCount() - sum);
			}
		}
		return detail1;
	}

	@Override
	public void add(Store store) {
		if (store != null) {
			totalDao.save(store);
		}
	}

	@Override
	public Object[] findOADetail_rugui(OaAppDetail oadetail, Integer cpage, Integer pageSize) {
		// TODO Auto-generated method stub
		if (oadetail == null) oadetail = new OaAppDetail();
		String hql = totalDao.criteriaQueries(oadetail, null);
		hql += " and detailStatus = '待入库' and detailChildClass = '工装' and (status = '未入完' or status is null) order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
}
