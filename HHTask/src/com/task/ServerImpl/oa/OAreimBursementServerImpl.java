package com.task.ServerImpl.oa;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.oa.OAreimBursementServer;
import com.task.entity.Goods;
import com.task.entity.OaAppDetail;
import com.task.entity.Oabusiness;
import com.task.entity.Oareimbursement;
import com.task.entity.Price;
import com.task.entity.Storage;
import com.task.entity.Users;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.util.Util;

public class OAreimBursementServerImpl implements OAreimBursementServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 查询可以报账的入库记录
	 * 
	 * @param storage
	 *            条件查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	@Override
	public Object[] findStorageBaoZhang(Storage storage, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = "from Storage where storageInvoice!=null and storageInvoice!='' and storageTaxPrice>0 and storageInvoice not in(select distinct(oainvoicenumber) from Oabusiness where oastatus<>'打回')";
		if (null != storage) {// 条件查询
			// 按发票号查询
			if (null != storage.getStorageInvoice()
					&& !"".equals(storage.getStorageInvoice())) {
				hql += " and storageInvoice='" + storage.getStorageInvoice()
						+ "'";
			}
			// 按物品名称查询
			if (null != storage.getMatetag()
					&& !"".equals(storage.getMatetag())
					&& storage.getStorageInvoice().length() > 0) {
				hql += " and matetag='" + storage.getStorageInvoice() + "'";
			}
			// 按物品编号查询
			if (null != storage.getNumber() && !"".equals(storage.getNumber())) {
				hql += " and number='" + storage.getNumber() + "'";
			}
			// 按规格查询
			if (null != storage.getFormat() && !"".equals(storage.getFormat())) {
				hql += " and format='" + storage.getFormat() + "'";
			}
			// 按类别查询
			if (null != storage.getParClass()
					&& !"".equals(storage.getParClass())) {
				hql += " and parClass='" + storage.getParClass() + "'";
			}
			// 按库别
			if (null != storage.getStorehouse()
					&& !"".equals(storage.getStorehouse())) {
				hql += " and storehouse='" + storage.getStorehouse() + "'";
			}
			// 按部门查询
			if (null != storage.getDept() && !"".equals(storage.getDept())) {
				hql += " and dept='" + storage.getDept() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and date between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by date desc,number desc";
		Object[] storeageAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		// List list = totalDao.query(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		storeageAarr[0] = count;
		storeageAarr[1] = list;
		return storeageAarr;
	}

	/**
	 * 查找预报账历史记录
	 * 
	 * @param storage
	 *            条件查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	@Override
	public Object[] findPreBaoZhang(Storage storage, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = "from Storage where bzStatus='预报账' ";
		if (null != storage) {// 条件查询
			// 按发票号查询
			if (null != storage.getStorageInvoice()
					&& !"".equals(storage.getStorageInvoice())) {
				hql += " and storageInvoice='" + storage.getStorageInvoice()
						+ "'";
			}
			// 按物品名称查询
			if (null != storage.getMatetag()
					&& !"".equals(storage.getMatetag())
					&& storage.getStorageInvoice().length() > 0) {
				hql += " and matetag='" + storage.getStorageInvoice() + "'";
			}
			// 按物品编号查询
			if (null != storage.getNumber() && !"".equals(storage.getNumber())) {
				hql += " and number='" + storage.getNumber() + "'";
			}
			// 按规格查询
			if (null != storage.getFormat() && !"".equals(storage.getFormat())) {
				hql += " and format='" + storage.getFormat() + "'";
			}
			// 按类别查询
			if (null != storage.getParClass()
					&& !"".equals(storage.getParClass())) {
				hql += " and parClass='" + storage.getParClass() + "'";
			}
			// 按库别
			if (null != storage.getStorehouse()
					&& !"".equals(storage.getStorehouse())) {
				hql += " and storehouse='" + storage.getStorehouse() + "'";
			}
			// 按部门查询
			if (null != storage.getDept() && !"".equals(storage.getDept())) {
				hql += " and dept='" + storage.getDept() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and date between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by date desc,number desc";
		Object[] storeageAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		// List list = totalDao.query(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		storeageAarr[0] = count;
		storeageAarr[1] = list;
		return storeageAarr;
	}

	/**
	 * 预报账审批
	 * 
	 * @param storageSelect
	 *            ，选择的预报账入库记录数组
	 * @param tag
	 *            审批状态（ok：同意；ng：打回）
	 * @return
	 */
	@Override
	public boolean updateStorageBZStatus(Integer[] storageSelect, String tag) {
		boolean boo = false;
		if (storageSelect.length > 0) {
			for (Integer id : storageSelect) {
				Storage storage = (Storage) totalDao.getObjectById(
						Storage.class, id);
				if ("ok".equals(tag)) {
					storage.setBzStatus("可开票");
					storage.setBudgetNumber("BZ"
							+ totalDao.getDateTime("yyyyMMddHHmmSS"));
				} else {
					storage.setBzStatus("打回");
				}
				boo = totalDao.update(storage);
			}
		}
		return boo;
	}

	/**
	 * 返回选择的Storage 列表
	 * 
	 * @param storageSelect
	 *            Storage的id数组
	 * @return
	 */
	@Override
	public Object[] findSelectedStorage(Integer[] storageSelect) {
		Object[] obj = new Object[2];
		List list = new ArrayList();
		Oareimbursement obs = new Oareimbursement();
		if (null != storageSelect) {
			String hqlSumMonet = "select sum(storageTaxMoney) from Storage where id in(:test) ";
			String hql = "from Storage where id in(:test) ";
			Query query_obs = totalDao.createQuery(hqlSumMonet);
			Query query_storage = totalDao.createQuery(hql);
			query_storage.setParameterList("test", storageSelect);
			query_obs.setParameterList("test", storageSelect);
			// 选中报账明细的总金额
			List listsum = query_obs.list();
			if (listsum.size() > 0 && null != listsum && null != listsum.get(0)) {
				Float sumMoney = Float.parseFloat(listsum.get(0).toString());// 总金额
				sumMoney = Float.parseFloat(String.format("%.2f", sumMoney));
				obs.setOathetotalamount(sumMoney);
			}
			// 处理付款凭证编号
			String pingzhegnNum = Util.getDateTime("yyyyMMddHHmmss");
			String hqlreimB = "select max(oaPaymentvouchernumber) from Oareimbursement where oaPaymentvouchernumber like'%"
					+ pingzhegnNum + "%'";
			List listpingzhegnNum = totalDao.query(hqlreimB);// 最大的付款凭证
			if (listpingzhegnNum.size() > 0 && null != listpingzhegnNum
					&& null != listpingzhegnNum.get(0)) {
				String maxNum = (String) listpingzhegnNum.get(0);
				if (maxNum.contains("-")) {
					int siz = Integer.parseInt(maxNum.substring(maxNum
							.indexOf("-")));
					siz++;
					pingzhegnNum += "-" + siz;
				} else {
					pingzhegnNum += "-" + 1;
				}

			}
			obs.setOaPaymentvouchernumber(pingzhegnNum);
			list = query_storage.list();
			obj[0] = obs;
			obj[1] = list;

		}
		return obj;
	}

	/**
	 * 条件查询可报账的入库明细，下拉前台传过来的字段
	 * 
	 * @param tag
	 *            前台下拉字段属性
	 * @param powerTag
	 *            备用字段
	 * @return
	 */
	@Override
	public String findOASelect(String tag, String powerTag) {
		String message = "";
		String hql = "select distinct("
				+ tag
				+ ") from Storage where storageInvoice!=null and storageInvoice!='' and storageTaxPrice>0 and storageInvoice not in(select distinct(oainvoicenumber) from Oabusiness)";
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	/**
	 * 保存报账信息
	 * 
	 * @param oaReimBursement
	 *            报账对象
	 * @param storageSelect
	 *            Storage ID数组
	 * @return
	 */
	@Override
	public List saveBaozhang(Oareimbursement oaReimBursement,
			Integer[] storageSelect) {
		List list = new ArrayList();
		boolean boo = false;
		Users user = Util.getLoginUser();
		oaReimBursement.setOastatus("审核中");
		oaReimBursement.setOadangannumber(Util.getDateTime("yyyy-MM-dd"));
		oaReimBursement.setOadatetime(Util.getDateTime());
		oaReimBursement.setOausername(user.getName());
		boo = totalDao.save(oaReimBursement);
		for (int i = 0; i < storageSelect.length; i++) {
			Storage stor = (Storage) totalDao.getObjectById(Storage.class,
					storageSelect[i]);
			Set<OaAppDetail> detailSet = stor.getOaDetails();// 获取申报明细
			String unitNum = "";
			for (Iterator<OaAppDetail> iter = detailSet.iterator(); iter
					.hasNext();) {
				OaAppDetail detail = iter.next();
				// 更新申报表状态
				unitNum = detail.getDetailOrdNumber();
				detail.setDetailStatus("报账");
				totalDao.update(detail);
			}
			// 添加到报账明细表
			Oabusiness business = new Oabusiness();
			business.setOaproductName(stor.getMatetag());// 物品名称
			business.setOaproductnumber(stor.getNumber());// 物品编号
			business.setOaspecification(stor.getFormat());// 物品规格
			business.setOaunit(stor.getUnit());// 物品单位
			business.setOaquantity(stor.getNum());// 数量
			business.setOatotalMon(stor.getStorageTaxMoney());
			business.setOaunitprice(stor.getStorageTaxPrice());
			business.setOastatus("审核中");// 状态
			business.setOauniNum(unitNum);// 统一编号
			business.setOadate(Util.getDateTime("yyyy-MM-dd"));// 报账日期
			business.setOafactory(stor.getStorageCompany());// 厂家
			business.setOainvoicenumber(stor.getStorageInvoice());// 发票号
			business.setOacontractNumber(oaReimBursement.getOadangannumber());// 档案号
			business.setOafkpznumber(oaReimBursement
					.getOaPaymentvouchernumber());// 付款凭证号
			business.setOahetongnumber(oaReimBursement.getOaContractnumber());// 合同号
			business.setOarbs(oaReimBursement);
			business.setOausername(user.getName());// 用户名
			boo = totalDao.save(business);
			list.add(business);
		}
		if (boo) {
			return list;
		} else {
			return null;
		}
	}

	/***
	 * 查找报账记录 或可打印记录 总经理付款审核记录，财务付款审核记录，以及查看历史
	 * 
	 * @param oaReimBursement
	 *            报账查询对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            查询动作表示
	 * @param powerTag
	 *            权限标识 tag="print" && powerTag="shSD" 打印可采购 tag="exam" &&
	 *            powerTag="manager" 总经理审核 tag="history" && powerTag="manager"
	 *            总经理审核历史记录 tag="exam" && powerTag="shFD" 财务付款审核 tag="history"
	 *            && powerTag="shFD" 财务付款审核历史记录
	 * @return
	 */
	@Override
	public Object[] OAReimBursementList(Oareimbursement oaReimBursement,
			String startDate, String endDate, Integer cpage, Integer pageSize,
			String tag, String powerTag) {
		Users user = Util.getLoginUser();
		List listPrint = new ArrayList();// 可打印list
		List list;// 历史记录及付款审核
		String hqlPrint = " from Oareimbursement where oastatus=?";
		String hql = "  from Oareimbursement where";
		if ("shSD".equals(powerTag)) {
			listPrint = totalDao.query(hqlPrint, "可打印");
			hql += " oastatus <>'可打印'";
		} else if ("manager".equals(powerTag)) {
			if ("exam".equals(tag)) {// 总经理审核
				hql += " oastatus='审核中'";
			} else { // 审核历史记录
				hql += " oastatus<>'审核中'";
			}
		} else if ("shFD".equals(powerTag)) {
			if ("exam".equals(tag)) {// 财务付款审核
				hql += " oastatus='可付款'";
			} else { // 付款审核历史记录
				hql += " oastatus<>'可付款'";
			}
		}
		// 条件查询
		if (null != oaReimBursement) {
			// 付款凭证
			if (null != oaReimBursement.getOaPaymentvouchernumber()
					&& !"".equals(oaReimBursement.getOaPaymentvouchernumber())) {
				hql += " and oaPaymentvouchernumber='"
						+ oaReimBursement.getOaPaymentvouchernumber() + "'";
			}
			// 付款依据
			if (null != oaReimBursement.getOaPaymentaccordancewith()
					&& !"".equals(oaReimBursement.getOaPaymentaccordancewith())) {
				hql += " and oaPaymentaccordancewith='"
						+ oaReimBursement.getOaPaymentaccordancewith() + "'";
			}
			// 合同号
			if (null != oaReimBursement.getOaContractnumber()
					&& !"".equals(oaReimBursement.getOaContractnumber())) {
				hql += " and oaContractnumber='"
						+ oaReimBursement.getOaContractnumber() + "'";
			}
			// 付款性质
			if (null != oaReimBursement.getOaPaymentnature()
					&& !"".equals(oaReimBursement.getOaPaymentnature())) {
				hql += " and oaPaymentnature='"
						+ oaReimBursement.getOaPaymentnature() + "'";
			}
			// 付款方式
			if (null != oaReimBursement.getOaPayment()
					&& !"".equals(oaReimBursement.getOaPayment())) {
				hql += " and oaPayment='" + oaReimBursement.getOaPayment()
						+ "'";
			}
			// 付款条件
			if (null != oaReimBursement.getOaTermsPayment()
					&& !"".equals(oaReimBursement.getOaTermsPayment())) {
				hql += " and oaTermsPayment='"
						+ oaReimBursement.getOaTermsPayment() + "'";
			}
			// 付款情况
			if (null != oaReimBursement.getOaPaymentsqk()
					&& !"".equals(oaReimBursement.getOaPaymentsqk())) {
				hql += " and oaPaymentsqk='"
						+ oaReimBursement.getOaPaymentsqk() + "'";
			}
			// 收款单位名称
			if (null != oaReimBursement.getOaBeneficiary()
					&& !"".equals(oaReimBursement.getOaBeneficiary())) {
				hql += " and oaBeneficiary='"
						+ oaReimBursement.getOaBeneficiary() + "'";
			}
			// 关联客户名称
			if (null != oaReimBursement.getOaAssociatedusername()
					&& !"".equals(oaReimBursement.getOaAssociatedusername())) {
				hql += " and oaAssociatedusername like '%"
						+ oaReimBursement.getOaAssociatedusername() + "%'";
			}
			// 评审单号
			if (null != oaReimBursement.getOaReviewnumber()
					&& !"".equals(oaReimBursement.getOaReviewnumber())) {
				hql += " and oaReviewnumber='"
						+ oaReimBursement.getOaReviewnumber() + "'";
			}
			// 状态
			if (null != oaReimBursement.getOastatus()
					&& !"".equals(oaReimBursement.getOastatus())) {
				hql += " and oastatus='" + oaReimBursement.getOastatus() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and oadate between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by oadatetime desc";
		Object[] obj = new Object[3];
		int count = totalDao.getCount(hql);
		list = totalDao.findAllByPage(hql, cpage, pageSize);
		obj[0] = count;
		obj[1] = list;
		obj[2] = listPrint;
		return obj;
	}

	/***
	 * 查询待付款的凭证(超过90天)
	 * 
	 * @return
	 */
	@Override
	public List findMaturityOabur() {
		String hql = "from Oareimbursement where getDate()-oadate>90 and oastatus='审核中' order by oadate";
		return totalDao.query(hql);
	}

	/**
	 * 打印或查看报账单信息
	 * 
	 * @param id
	 *            报账表ID 返回报账明细list Business
	 * @return
	 */
	@Override
	public Object[] findBusinesByOBSId(Integer id) {
		String hql = " from Oabusiness where oarbs.id=?";
		Oareimbursement obs = (Oareimbursement) totalDao.getObjectById(
				Oareimbursement.class, id);
		List list = totalDao.query(hql, id);
		Object[] obj = new Object[2];
		obj[0] = list;
		obj[1] = obs;
		return obj;
	}

	/**
	 * 打印或查看报账单信息
	 * 
	 * @param business
	 *            找张历史对象
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            (expor:导出，list：查询)
	 * @return
	 */
	@Override
	public Object[] findBusList(Oabusiness business, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = " from Oabusiness where 1=1";
		if (null != business) {
			// 物品编号不为空
			if (null != business.getOaproductnumber()
					&& !"".equals(business.getOaproductnumber())) {
				hql += " and oaproductnumber='" + business.getOaproductnumber()
						+ "'";
			}
			// 物品名称不为空
			if (null != business.getOaproductName()
					&& !"".equals(business.getOaproductName())) {
				hql += " and oaproductName='" + business.getOaproductName()
						+ "'";
			}
			// 状态不为空
			if (null != business.getOastatus()
					&& !"".equals(business.getOastatus())) {
				hql += " and oastatus='" + business.getOastatus() + "'";
			}
			// 负责人不为空
			if (null != business.getOausername()
					&& !"".equals(business.getOausername())) {
				hql += " and oausername='" + business.getOausername() + "'";
			}
			// 厂家不为空
			if (null != business.getOafactory()
					&& !"".equals(business.getOafactory())) {
				hql += " and oafactory='" + business.getOafactory() + "'";
			}
			// 合同号不为空
			if (null != business.getOahetongnumber()
					&& !"".equals(business.getOahetongnumber())) {
				hql += " and oahetongnumber='" + business.getOahetongnumber()
						+ "'";
			}
			// 发票号不为空
			if (null != business.getOainvoicenumber()
					&& !"".equals(business.getOainvoicenumber())) {
				hql += " and oainvoicenumber='" + business.getOainvoicenumber()
						+ "'";
			}

		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and oadate between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by oadate desc";
		if ("list".equals(tag)) {// 数据查询
			List list = totalDao.findAllByPage(hql, cpage, pageSize);
			int count = totalDao.getCount(hql);
			Object[] obj = new Object[2];
			obj[0] = count;
			obj[1] = list;
			return obj;
		} else {// 数据导出
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename="
								+ new String("报账记录".getBytes("GB2312"),
										"8859_1") + ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("报账历史记录", 0);
				ws.setColumnView(4, 20);
				ws.setColumnView(3, 10);
				ws.setColumnView(2, 20);
				ws.setColumnView(1, 12);
				WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wcf = new WritableCellFormat(wf);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wcf.setAlignment(Alignment.CENTRE);
				jxl.write.Label label0 = new Label(0, 0, "报账历史记录", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 14, 0);
				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "物品编号", wc));
				ws.addCell(new jxl.write.Label(2, 1, "物品名称", wc));
				ws.addCell(new jxl.write.Label(3, 1, "规格", wc));
				ws.addCell(new jxl.write.Label(4, 1, "单位", wc));
				ws.addCell(new jxl.write.Label(5, 1, "数量", wc));
				ws.addCell(new jxl.write.Label(6, 1, "单价", wc));
				ws.addCell(new jxl.write.Label(7, 1, "总金额", wc));
				ws.addCell(new jxl.write.Label(8, 1, "状态", wc));
				ws.addCell(new jxl.write.Label(9, 1, "负责人", wc));
				ws.addCell(new jxl.write.Label(10, 1, "厂家", wc));
				ws.addCell(new jxl.write.Label(11, 1, "发票号", wc));
				ws.addCell(new jxl.write.Label(12, 1, "报账日期", wc));
				ws.addCell(new jxl.write.Label(13, 1, "合同编号", wc));
				for (int i = 0; i < list.size(); i++) {
					Oabusiness o = (Oabusiness) list.get(i);

					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, o.getOaproductnumber(), wc));
					ws.addCell(new Label(2, i + 2, o.getOaproductName(), wc));
					ws.addCell(new Label(3, i + 2, o.getOaspecification(), wc));
					ws.addCell(new Label(4, i + 2, o.getOaunit(), wc));
					ws.addCell(new Label(5, i + 2, String.format("%.2f", o
							.getOaquantity()), wc));
					// 单价
					ws.addCell(new Label(6, i + 2, String.format("%.2f", o
							.getOaunitprice()), wc));
					ws.addCell(new Label(7, i + 2, String.format("%.2f", o
							.getOatotalMon()), wc));
					ws.addCell(new Label(8, i + 2, o.getOastatus(), wc));
					ws.addCell(new Label(9, i + 2, o.getOausername(), wc));
					ws.addCell(new Label(10, i + 2, o.getOafactory(), wc));
					// 支出
					ws
							.addCell(new Label(11, i + 2, o
									.getOainvoicenumber(), wc));
					ws.addCell(new Label(12, i + 2, o.getOadate(), wc));
					// 结余
					ws.addCell(new Label(13, i + 2, o.getOahetongnumber(), wc));
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

	}

	/**
	 * 打印更新报账状态 由可打印改为可付款
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateOBSById(Integer id) {
		Oareimbursement obs = (Oareimbursement) totalDao.getObjectById(
				Oareimbursement.class, id);
		obs.setOastatus("可付款");
		Set<Oabusiness> list = obs.getBusiness();
		for (Iterator<Oabusiness> iter = list.iterator(); iter.hasNext();) {
			Oabusiness busi = iter.next();
			// 更新申报表状态
			busi.setOastatus("可付款");
			totalDao.update(busi);
			// 处理申报明细
			String hql = "from OaAppDetail where detailSeqNum=?";
			List listD = totalDao.query(hql, busi.getOaproductnumber());
			if (listD.size() > 0 && null != listD) {
				for (int i = 0; i < listD.size(); i++) {
					OaAppDetail d = (OaAppDetail) listD.get(i);
					d.setDetailStatus("可付款");
					totalDao.update(d);
				}
			}
		}
		return totalDao.update(obs);
	}

	/**
	 * 报账下拉处理
	 * 
	 * @param tag
	 *            下拉属性
	 * @param powerTag
	 *            权限
	 * @return
	 */
	@Override
	public String selectItem(String tag, String powerTag) {
		String message = "";
		String hql = "select distinct(" + tag
				+ ") from Oareimbursement where 1=1";
		if ("shSD".equals(powerTag)) {
			hql += "and oastatus <>'可打印'";
		}
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	/**
	 * 查看报账明细表
	 * 
	 * @param business
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 *            //操作 exam 审核
	 * @param powerTag
	 *            //操起权限 manganger ，shFD ,shMD
	 * @return
	 */
	@Override
	public Object[] findOABusinessList(Oabusiness business, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag,
			String powerTag) {
		String hql = " from Oabusiness where 1=1";
		List list;
		if (null != business) {
			// 报账ID不为空
			if (null != business.getOarbs().getId()) {// 审核处理或查看处理
				hql += " and oarbs.id=" + business.getOarbs().getId();
			}
			// 物品编号不为空
			if (null != business.getOaproductnumber()
					&& !"".equals(business.getOaproductnumber())) {
				hql += " and oaproductnumber='" + business.getOaproductnumber()
						+ "'";
			}
			// 物品名称不为空
			if (null != business.getOaproductName()
					&& !"".equals(business.getOaproductName())) {
				hql += " and oaproductName='" + business.getOaproductName()
						+ "'";
			}
			// 状态不为空
			if (null != business.getOastatus()
					&& !"".equals(business.getOastatus())) {
				hql += " and oastatus='" + business.getOastatus() + "'";
			}
			// 负责人不为空
			if (null != business.getOausername()
					&& !"".equals(business.getOausername())) {
				hql += " and oausername='" + business.getOausername() + "'";
			}
			// 厂家不为空
			if (null != business.getOafactory()
					&& !"".equals(business.getOafactory())) {
				hql += " and oafactory='" + business.getOafactory() + "'";
			}
			// 合同号不为空
			if (null != business.getOahetongnumber()
					&& !"".equals(business.getOahetongnumber())) {
				hql += " and oahetongnumber='" + business.getOahetongnumber()
						+ "'";
			}

		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and oadate between '" + startDate + "' and '" + endDate
					+ "'";
		}
		hql += " order by oadate desc";
		Object[] obj = new Object[2];
		int count = totalDao.getCount(hql);
		list = totalDao.findAllByPage(hql, cpage, pageSize);
		obj[0] = count;
		obj[1] = list;
		return obj;
	}

	/**
	 * 查找Business 下拉
	 * 
	 * @param tag
	 * @param powerTag
	 * @return
	 */
	@Override
	public String selectBusinessItem(String tag, String powerTag) {
		String message = "";
		String hql = "select distinct(" + tag + ") from Oabusiness where 1=1";
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	/**
	 * 筛选
	 * 
	 * @param id
	 *            报账ID
	 * @return
	 */
	@Override
	public boolean updateSelectShaixuang(Integer id) {
		Users user = Util.getLoginUser();
		boolean boo = false;
		Oareimbursement obs = (Oareimbursement) totalDao.getObjectById(
				Oareimbursement.class, id);
		Set<Oabusiness> list = obs.getBusiness();
		int count_kfk = 0;// 可付款数量
		int count_dh = 0;// 打回数量
		float dhamoney = 0f;// 打回金额
		for (Iterator<Oabusiness> iter = list.iterator(); iter.hasNext();) {
			Oabusiness busi = iter.next();
			// 比较历史价格
			if ("审核中".equals(busi.getOastatus())) {
				String hqlStorageMiPrice = "select avg(storageTaxPrice) from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
						+ busi.getOaproductnumber() + "' and date>'2012-01-01'";
				List listAvgPrice = totalDao.query(hqlStorageMiPrice, busi
						.getOaproductName(), busi.getOaspecification(), busi
						.getOaunit());
				Double avgPrice = 0d;
				if (listAvgPrice.size() > 0 && null != listAvgPrice
						&& null != listAvgPrice.get(0)) {
					avgPrice = (Double) listAvgPrice.get(0);
				}
				if (busi.getOaunitprice() > avgPrice) {// 超过历史平均价格
					// 更新申报表状态
					busi.setOastatus("打回");
					busi.setOaremarks(user.getName());
					boo = totalDao.update(busi);
					count_dh++;
					dhamoney += busi.getOatotalMon();
					// 处理申报明细
					String hql = "from OaAppDetail where detailSeqNum=?";
					List listD = totalDao.query(hql, busi.getOaproductnumber());
					if (listD.size() > 0 && null != listD) {
						for (int i = 0; i < listD.size(); i++) {
							OaAppDetail d = (OaAppDetail) listD.get(i);
							d.setDetailStatus("打回");
							totalDao.update(d);
						}
					}
				} else {
					count_kfk++;
				}
			}
		}
		// 判断报账状态是否需要处理
		if (dhamoney > 0) {// 有打回记录
			obs.setOathetotalamount(obs.getOathetotalamount() - dhamoney);
			// 判断有没有状态为审核中的记录
			String hql = " from Oabusiness where oastatus='审核中' and oarbs.id="
					+ obs.getId();
			String hql2 = " from Oabusiness where oastatus='可打印' and oarbs.id="
					+ obs.getId();
			List listSHZ = totalDao.query(hql);// 审核中记录
			List listKFK = totalDao.query(hql2);
			if (listSHZ.size() > 0 && null != listSHZ) {// 存在状态为审核中的记录
				// 状态不变
			} else {// 不存在状态为审核中的记录，报账状态需要更新
				if (listKFK.size() > 0 && null != listKFK) {// 可付款
					obs.setOastatus("可打印");
				} else {
					obs.setOastatus("打回");
				}

			}
			boo = totalDao.update(obs);
		} else {// 无打回记录

		}

		return boo;
	}

	/**
	 * 审核比价
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List findSameProductPrice(Integer id, String tag) {
		if ("store".equals(tag)) {
			Storage storage = (Storage) totalDao.getObjectById(Storage.class,
					id);
			String hql = "select matetag,format,unit,storageTaxPrice from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
					+ storage.getNumber()
					+ "' and date>'2011-07-01' group by matetag,format,unit,storageTaxPrice";
			return totalDao.query(hql, storage.getMatetag(), storage
					.getFormat(), storage.getUnit());
		} else if ("oaApp".equals(tag)) {
			//Goods goods = (Goods) totalDao.getObjectById(Goods.class, id);
			String hql_goods = "select goodsFullName,goodsFormat,goodsUnit,goodsBuyPrice from Goods where goodsFullName=?" +
					" and goodsFormat = ? and goodsUnit=? and goodsBuyPrice>0 and goodsCode != ?" +
					" and goodsChangeTime>'2011-07-01' group by goodsFullName,goodsFormat,goodsUnit,goodsBuyPrice";
			OaAppDetail oaAppDetail = (OaAppDetail) totalDao.getObjectById(
					OaAppDetail.class, id);
			/*String hql = "select matetag,format,unit,storageTaxPrice from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
					+ oaAppDetail.getDetailSeqNum()
					+ "' and date>'2011-07-01' group by matetag,format,unit,storageTaxPrice";*/
			return totalDao.query(hql_goods, oaAppDetail.getDetailAppName(),
					oaAppDetail.getDetailFormat(), oaAppDetail.getDetailUnit(),oaAppDetail.getWlcode());

		} else {
			Oabusiness business = (Oabusiness) totalDao.getObjectById(
					Oabusiness.class, id);
			String hql = "select matetag,format,unit,storageTaxPrice from Storage where matetag=? and format=? and unit=? and storageTaxPrice>0 and number!='"
					+ business.getOaproductnumber()
					+ "' and date>'2011-07-01' group by matetag,format,unit,storageTaxPrice";
			return totalDao.query(hql, business.getOaproductName(), business
					.getOaspecification(), business.getOaunit());
		}
	}

	/**
	 * 审核操作
	 * 
	 * @param id
	 *            business id
	 * @param tag
	 *            审核意见
	 * @param powerTag
	 *            权限
	 * @return 报账状态更改 返回list 报账状态为更改 返回报账id
	 */
	@Override
	public Object[] examBusiness(Integer id, String tag, String powerTag) {
		Users user = Util.getLoginUser();
		Oabusiness business = (Oabusiness) totalDao.getObjectById(
				Oabusiness.class, id);
		Oareimbursement obs = business.getOarbs();// 获得报账对象
		float ngMoney_manager = 0f;
		float ngMoney_shFD = 0f;
		Object[] obj = new Object[2];
		if ("kfk".equals(tag)) {// 可付款
			business.setOastatus("可打印");
			totalDao.update(business);
			// 判断有没有状态为审核中的记录
			String hql = " from Oabusiness where oastatus='审核中' and oarbs.id="
					+ obs.getId();
			List list = totalDao.query(hql);
			if (list.size() > 0) {// 报账状态不变
				obj[0] = "exam";
				obj[1] = obs.getId();
				return obj;
			} else {// 报账状态改变
				obs.setOastatus("可打印");
				totalDao.update(obs);
				String hqlExam = " from Oareimbursement where oastatus='审核中'";
				obj[0] = "list";
				obj[1] = totalDao.query(hqlExam);
				return obj;

			}
		} else if ("kfkNG".equals(tag)) {// 付款打回
			// ngMoney_manager+=business.getOatotalMon();
			business.setOastatus("打回");
			business.setOaremarks(user.getName());
			totalDao.update(business);
			obs.setOathetotalamount(obs.getOathetotalamount()
					- business.getOatotalMon());
			String hqlKFKng = " from Oabusiness where oastatus='审核中' and oarbs.id="
					+ obs.getId();
			String hqlKFKdh = " from Oabusiness where oastatus='可打印' and oarbs.id="
					+ obs.getId();
			List listKFK = totalDao.query(hqlKFKng);// 有无状态为审核中的记录
			List listkdy = totalDao.query(hqlKFKdh);// 有无可打印的记录
			if (listKFK.size() > 0 && null != listKFK) {// 有审核中的记录，状态不动
				totalDao.update(obs);
				obj[0] = "exam";
				obj[1] = obs.getId();
				return obj;
			} else {// 无审核总记录，最后一条,状态更改，判断改为可打印还是打回
				if (listkdy.size() > 0 && null != listkdy) {// 有可打印，状态改为可打印
					obs.setOastatus("可打印");
				} else {
					obs.setOastatus("打回");
				}
				totalDao.update(obs);
				String hqlExam = " from Oareimbursement where oastatus='审核中'";
				obj[0] = "list";
				obj[1] = totalDao.query(hqlExam);
				return obj;
			}

		} else if ("yfk".equals(tag)) {// 已付款
			business.setOastatus("已付款");
			totalDao.update(business);
			// 判断有没有状态为可付款的记录
			String hql = " from Oabusiness where oastatus='可付款' and oarbs.id="
					+ obs.getId();
			List list = totalDao.query(hql);
			if (list.size() > 0) {// 报账状态不变
				obj[0] = "exam";
				obj[1] = obs.getId();
				return obj;
			} else {// 报账状态改变
				obs.setOastatus("已付款");
				totalDao.update(obs);
				String hqlExam = " from Oareimbursement where oastatus='可付款'";
				obj[0] = "list";
				obj[1] = totalDao.query(hqlExam);
				return obj;

			}
		} else if ("yfkNG".equals(tag)) {
			business.setOastatus("打回");
			business.setOaremarks(user.getName());
			totalDao.update(business);
			obs.setOathetotalamount(obs.getOathetotalamount()
					- business.getOatotalMon());
			String hqlKFKng = " from Oabusiness where oastatus='可付款' and oarbs.id="
					+ obs.getId();
			String hqlKFKdh = " from Oabusiness where oastatus='已付款' and oarbs.id="
					+ obs.getId();
			List listKFK = totalDao.query(hqlKFKng);// 有无状态为可付款的记录
			List listkdy = totalDao.query(hqlKFKdh);// 有无已付款的记录
			if (listKFK.size() > 0 && null != listKFK) {// 有可付款的记录，状态不动
				totalDao.update(obs);
				obj[0] = "exam";
				obj[1] = obs.getId();
				return obj;
			} else {// 无可付款记录，最后一条,状态更改，判断改为可打印还是打回
				if (listkdy.size() > 0 && null != listkdy) {// 有可打印，状态改为可打印
					obs.setOastatus("已付款");
				} else {
					obs.setOastatus("打回");
				}
				totalDao.update(obs);
				String hqlExam = " from Oareimbursement where oastatus='可付款'";
				obj[0] = "list";
				obj[1] = totalDao.query(hqlExam);
				return obj;
			}
		} else if ("resubmit".equals(tag)) {// 重新提交
			if ("打回".equals(business.getOastatus())) {
				business.setOastatus("审核中");
				totalDao.update(business);
				obs.setOathetotalamount(obs.getOathetotalamount()
						+ business.getOatotalMon());
				obs.setOastatus("审核中");
				totalDao.update(obs);
				obj[0] = "exam";
				obj[1] = obs.getId();
				return obj;
			}

		}

		return null;

	}

	/**
	 * 批量审核操作
	 * 
	 * @param id
	 *            business id
	 * @param tag
	 *            审核意见
	 * @param powerTag
	 *            权限
	 * @return 报账状态更改 返回list 报账状态为更改 返回报账id
	 */
	@Override
	public Object[] examlotBusiness(Integer[] ids, String tag, String powerTag) {
		Object[] obj = new Object[2];
		if (null != ids) {
			Users user = Util.getLoginUser();
			Oabusiness business = (Oabusiness) totalDao.getObjectById(
					Oabusiness.class, ids[0]);
			Oareimbursement obs = business.getOarbs();// 获得报账对象
			String status = "";
			if ("kfk".equals(tag)) {
				status = "可打印";
			} else if ("kfkNG".equals(tag)) {
				status = "打回";
			} else if ("yfk".equals(tag)) {
				status = "已付款";
			} else if ("yfkNG".equals(tag)) {
				status = "打回";
			}
			for (int id : ids) {
				Oabusiness busi = (Oabusiness) totalDao.getObjectById(
						Oabusiness.class, id);
				busi.setOastatus(status);
				// 处理预算对接（OTT201408053,<br/>OTT201408039）
				if ("kfk".equals(tag)) {
					String[] num = busi.getOaproductnumber().split(",<br/>");
					if (num.length > 0) {
						for (String pnum : num) {
							String hqloadetail = "from OaAppDetail where detailSeqNum=?";
							OaAppDetail oadetail = (OaAppDetail) totalDao
									.getObjectByCondition(hqloadetail, pnum);
							// 预算处理
							if (null != oadetail.getDeptMonthBudgetID()) {
								DeptMonthBudget deptBudget = (DeptMonthBudget) totalDao
										.getObjectById(DeptMonthBudget.class,
												oadetail.getDeptMonthBudgetID());
								if (busi.getOatotalMon()
										+ deptBudget.getRealMoney() > deptBudget
										.getAccountMoney()) {
									obj[0] = "beyond";
									obj[1] = obs.getId();
									return obj;// 超出预算
								} else {
									deptBudget.setRealMoney(deptBudget
											.getRealMoney()
											+ deptBudget.getRealMoney());
									totalDao.update(deptBudget);
								}
							}

						}
					}
				}
				totalDao.update(busi);
			}

			if ("kfk".equals(tag)) {// 可付款

				// 判断有没有状态为审核中的记录
				String hql = " from Oabusiness where oastatus='审核中' and oarbs.id="
						+ obs.getId();
				List list = totalDao.query(hql);
				if (list.size() > 0) {// 报账状态不变
					obj[0] = "exam";
					obj[1] = obs.getId();
					return obj;
				} else {// 报账状态改变
					obs.setOastatus("可打印");
					totalDao.update(obs);
					String hqlExam = " from Oareimbursement where oastatus='审核中'";
					obj[0] = "list";
					obj[1] = totalDao.query(hqlExam);
					return obj;

				}
			} else if ("kfkNG".equals(tag)) {// 付款打回
				obj[0] = "exam";
				// ngMoney_manager+=business.getOatotalMon();
				String hqlKFKng = " from Oabusiness where oastatus='审核中' and oarbs.id="
						+ obs.getId();
				String hqlKFKdh = " from Oabusiness where oastatus='可打印' and oarbs.id="
						+ obs.getId();
				List listKFK = totalDao.query(hqlKFKng);// 有无状态为审核中的记录
				List listkdy = totalDao.query(hqlKFKdh);// 有无可打印的记录
				if (listKFK.size() > 0 && null != listKFK) {// 有审核中的记录，状态不动
					totalDao.update(obs);

					obj[1] = obs.getId();
					return obj;
				} else {// 无审核总记录，最后一条,状态更改，判断改为可打印还是打回
					if (listkdy.size() > 0 && null != listkdy) {// 有可打印，状态改为可打印
						obs.setOastatus("可打印");
					} else {
						obs.setOastatus("打回");
					}
					totalDao.update(obs);
					String hqlExam = " from Oareimbursement where oastatus='审核中'";
					obj[0] = "list";
					obj[1] = totalDao.query(hqlExam);
					return obj;
				}

			} else if ("yfk".equals(tag)) {// 已付款
				// 判断有没有状态为可付款的记录

				String hql = " from Oabusiness where oastatus='可付款' and oarbs.id="
						+ obs.getId();
				List list = totalDao.query(hql);
				if (list.size() > 0) {// 报账状态不变
					obj[0] = "exam";
					obj[1] = obs.getId();
					return obj;
				} else {// 报账状态改变
					obs.setOastatus("已付款");
					totalDao.update(obs);
					String hqlExam = " from Oareimbursement where oastatus='可付款'";
					obj[0] = "list";
					obj[1] = totalDao.query(hqlExam);
					return obj;

				}
			} else if ("yfkNG".equals(tag)) {
				String hqlKFKng = " from Oabusiness where oastatus='可付款' and oarbs.id="
						+ obs.getId();
				String hqlKFKdh = " from Oabusiness where oastatus='已付款' and oarbs.id="
						+ obs.getId();
				List listKFK = totalDao.query(hqlKFKng);// 有无状态为可付款的记录
				List listkdy = totalDao.query(hqlKFKdh);// 有无已付款的记录
				if (listKFK.size() > 0 && null != listKFK) {// 有可付款的记录，状态不动
					totalDao.update(obs);

					obj[1] = obs.getId();
					return obj;
				} else {// 无可付款记录，最后一条,状态更改，判断改为可打印还是打回
					if (listkdy.size() > 0 && null != listkdy) {// 有可打印，状态改为可打印
						obs.setOastatus("已付款");
					} else {
						obs.setOastatus("打回");
					}
					totalDao.update(obs);
					String hqlExam = " from Oareimbursement where oastatus='可付款'";
					obj[0] = "list";
					obj[1] = totalDao.query(hqlExam);
					return obj;
				}
			}

		}
		return obj;
	}
}
