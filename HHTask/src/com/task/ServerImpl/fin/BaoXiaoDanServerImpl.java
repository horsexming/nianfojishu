package com.task.ServerImpl.fin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import sun.management.counter.Units;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.fin.BaoXiaoDanServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.fin.BaoxiaoDan;
import com.task.entity.fin.BaoxiaoDetail;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.payee.Payee;
import com.task.entity.payment.FundApply;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedPriceCost;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

/**
 * 报销单serverImpl
 * 
 * @author jhh
 * 
 */
public class BaoXiaoDanServerImpl implements BaoXiaoDanServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean saveBaoXiaoDan(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, Float money1, String money2) {
		// TODO Auto-generated method stub
		boolean boo = false;
		List<BaoxiaoDetail> listDetailNew = new ArrayList<BaoxiaoDetail>();
		String status = "print";// 可以直接打印
		if (null != baoxiaodan) {
			if ("归还借款".equals(baoxiaodan.getPayStyle())) {
				String date1 = Util.getDateTime("yyyy-MM-dd");
				String str[] = money2.split(",");
				Float pay_money = Float.parseFloat(str[0]);// 拿到借款金额
				Integer pay_id = Integer.parseInt(str[1]);// 拿到id
				PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
						.getObjectById(PaymentVoucher.class, pay_id);
				Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
				if (prepaidMoney1 == null) {
					prepaidMoney1 = 0F;
				}
				Float pay_money1 = pay_money - money1;
				if (pay_money1 > 0) {
					paymentVoucher.setPaymentStatus("未付清");
					paymentVoucher.setVoucherdate(date1);
					paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					baoxiaodan.setPaymentVouchers(paymentVoucher);
				} else if (pay_money1 < 0) {
					return false;
				} else {
					paymentVoucher.setPaymentStatus("已付款");
					paymentVoucher.setVoucherdate(date1);
					paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					baoxiaodan.setPaymentVouchers(paymentVoucher);
				}
				this.totalDao.update(paymentVoucher);
			}

			Users user = (Users) Util.getLoginUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat yy = new SimpleDateFormat("yyyyMM");
			float totalMoney = 0f;
			for (BaoxiaoDetail detail : listDetail) {
				DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
						DeptMonthBudget.class, Integer.parseInt(detail
								.getBaoxiaoStyle()));
				detail.setBaoxiaoStyle(dmb.getName());
				detail.setDeptMonthBudgetID(dmb.getId());
				detail.setPlanMonth(dmb.getBudgetMonth());
				detail.setSaveTime(sdf.format(new Date()));
				detail.setCurrency(baoxiaodan.getCurrency());
				if (user.getDept().equals(detail.getBudgetDept())) {// 部门一致
					// 判断是多部门还是本部门(本部门直接通过，跨部门，其他部门需要确认)
					detail.setStatus("print");
				} else {
					detail.setStatus("confirm");// 待确认
					status = "confirm";
				}
				listDetailNew.add(detail);
				totalMoney += detail.getMoney();
				float realM = detail.getMoney();
				if (null != dmb.getRealMoney()) {
					realM += dmb.getRealMoney();// 计算实际报销金额
				}
				// 判断有无超预算
				if (realM <= dmb.getAccountMoney()) {
					dmb.setRealMoney((double) realM);
					boo = totalDao.update(dmb);
				} else {
					boo = false;
					break;
				}

			}
			if (boo) {
				baoxiaodan.setTotalMoney((double) totalMoney);
				baoxiaodan.setSaveTime(sdf.format(new Date()));
				baoxiaodan.setCode(user.getCode());
				baoxiaodan.setDept(user.getDept());
				String bianhao = user.getPassword().getDeptNumber() + "-BX-"
						+ yy.format(new Date());
				String hql = "select max(baoxiaoBarcode) from BaoxiaoDan where baoxiaoBarcode like'%"
						+ bianhao + "%' ";
				if (null != totalDao.find(hql) && totalDao.find(hql).size() > 0
						&& null != totalDao.find(hql).get(0)) {
					String maxBarcode = (String) totalDao.find(hql).get(0);
					String num = maxBarcode.substring(bianhao.length());
					Integer intHao = Integer.parseInt(num);
					intHao++;
					String strHao = intHao.toString();
					while (strHao.length() < 3) {
						strHao = "0" + strHao;
					}
					bianhao += strHao;
				} else {
					bianhao += "001";
				}
				baoxiaodan.setStatus(status);// 状态赋值
				baoxiaodan.setBaoxiaoBarcode(bianhao);
				baoxiaodan.setBaoxiaoDetail(new HashSet(listDetailNew));// 在一方维护添加
				boo = totalDao.save(baoxiaodan);

			}

		}
		return boo;
	}

	@Override
	public boolean deleteBaoXiaoDan(Integer id) {
		BaoxiaoDan baoxiaodan = (BaoxiaoDan) totalDao.getObjectById(
				BaoxiaoDan.class, id);
		// 清空借款的关系(退还已付的借款金额)
		if (baoxiaodan.getPaymentVouchers() != null
				&& baoxiaodan.getPaymentVouchers().getId() != null) {
			PaymentVoucher pv = (PaymentVoucher) totalDao.getObjectById(
					PaymentVoucher.class, baoxiaodan.getPaymentVouchers()
							.getId());
			// 计算应退还金额
			Float thMoney = pv.getPrepaidMoney()
					- baoxiaodan.getTotalMoney().floatValue();
			if (thMoney < 0) {
				thMoney = 0F;
			}
			pv.setPrepaidMoney(thMoney);
			totalDao.update(pv);
			baoxiaodan.setPaymentVouchers(null);
		}
		// 清空预算的关系(退还已用的借款金额)
		return totalDao.delete(baoxiaodan);
	}

	@Override
	public Object[] findBaoXiaoDan(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		String username = user.getName();
		String hql = "from BaoxiaoDan where baoxiaoren='" + username
				+ "' order by saveTime desc";
		if ("manger".equals(tag)) {
			hql = "from BaoxiaoDan order by saveTime desc";
		} else {
			if (null == baoxiaodan) {
				baoxiaodan = new BaoxiaoDan();
			}
			baoxiaodan.setBaoxiaoren(username);
		}
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != baoxiaodan) {
			String other = "";
			if (baoxiaodan.getPaymentVouchers() != null
					&& baoxiaodan.getPaymentVouchers().getId() != null) {
				other = " and paymentVouchers.id="
						+ baoxiaodan.getPaymentVouchers().getId();
				baoxiaodan.setPaymentVouchers(null);
			}
			hql = totalDao.criteriaQueries(baoxiaodan, "baoxiaoDate", between,
					"")
					+ other + "  order by saveTime desc";
		}

		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	Object getOldStr(Integer id, String tag) {
		BaoxiaoDetail b = (BaoxiaoDetail) totalDao.getObjectById(
				BaoxiaoDetail.class, id);
		if ("oldBudgetID".equals(tag)) {
			return b.getDeptMonthBudgetID();
		} else if ("oldMoney".equals(tag)) {
			return b.getMoney();
		} else if ("oldSttaus".equals(tag)) {
			return b.getStatus();
		}
		return null;
	}

	/** 更改报销费用 */
	@Override
	public boolean updateBaoXiaoDan(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, String money2, Float money1,
			Float oldmoney) {
		boolean bool = false;
		BaoxiaoDan bxd = (BaoxiaoDan) totalDao.getObjectById(BaoxiaoDan.class,
				baoxiaodan.getId());// 获取数据库的实体类
		Users user = (Users) Util.getLoginUser();
		BeanUtils.copyProperties(baoxiaodan, bxd, new String[] {
				"paymentVouchers", "dept", "code", "baoxiaoren", "saveTime",
				"baoxiaoBarcode", "deptManager", "Deputymanager",
				"Genalmangager", "planMonth", "isPrint" });
		String status = baoxiaodan.getStatus();// 可以直接打印
		float totalMoney = 0f;// 总金额
		List<BaoxiaoDetail> listDetailNew = new ArrayList<BaoxiaoDetail>();
		for (BaoxiaoDetail detail : listDetail) {
			// 判断是否新增记录
			DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
					DeptMonthBudget.class, Integer.parseInt(detail
							.getBaoxiaoStyle()));
			detail.setBaoxiaoStyle(dmb.getName());
			detail.setDeptMonthBudgetID(dmb.getId());
			detail.setSaveTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			detail.setCurrency(bxd.getCurrency());
			if (detail.getId() != null && detail.getId() > 0) {// 不是新增
				BaoxiaoDetail oldDetail = (BaoxiaoDetail) totalDao
						.getObjectById(BaoxiaoDetail.class, detail.getId());

				// int id=detail.getId();
				// BaoxiaoDetail d=getbxDetailById(id);
				int oldBudgetID = oldDetail.getDeptMonthBudgetID();// (Integer)getOldStr(id,"oldBudgetID");
				float oldMoney = oldDetail.getMoney();// (Float)getOldStr(id,"oldMoney");//老金额
				String oldSttaus = oldDetail.getStatus();// (String)getOldStr(id,"oldSttaus");//老金额
				oldDetail.setMoney(detail.getMoney());

				oldDetail.setDeptMonthBudgetID(detail.getDeptMonthBudgetID());
				oldDetail.setBaoxiaoStyle(detail.getBaoxiaoStyle());
				oldDetail.setBaoxiaoContent(detail.getBaoxiaoContent());

				oldDetail.setBaoxiaoDan(bxd);
				// 判断状态（根据报销类型和金额判断是否需要从新确认）
				if (oldBudgetID == dmb.getId()) {
					if (oldMoney >= detail.getMoney()) {// 原来金额>新报金额
						oldDetail.setStatus(oldSttaus);
						double realM = detail.getMoney();
						if (null != dmb.getRealMoney()) {
							realM = (double) (realM + dmb.getRealMoney() - oldMoney);// 计算实际报销金额
						}
						dmb.setRealMoney((double) realM);
					} else {// 金额有变动
						oldDetail.setStatus("confirm");// 待确认
						status = "confirm";
						// 计算部门预算明细
						double realM = detail.getMoney();
						if (null != dmb.getRealMoney()) {
							realM = (double) realM + dmb.getRealMoney()
									- oldMoney;// 计算实际报销金额
						}
						dmb.setRealMoney(realM);
						// totalDao.update(dmb);
					}

				} else {// 前后科目不一致（老金额恢复，新金额减去）
					oldDetail.setStatus("confirm");// 待确认
					status = "confirm";
					// 计算部门预算明细（新金额处理）
					double realM = detail.getMoney();
					if (null != dmb.getRealMoney()) {
						realM += dmb.getRealMoney();// 计算实际报销金额
					}
					dmb.setRealMoney(realM);
					// 判断有无超预算
					if (realM <= dmb.getAccountMoney()) {
						bool = totalDao.update(dmb);
					} else {
						bool = false;
						break;
					}
					// 老金额处理
					DeptMonthBudget dmbOLD = (DeptMonthBudget) totalDao
							.getObjectById(DeptMonthBudget.class, oldBudgetID);
					dmbOLD.setRealMoney(dmbOLD.getRealMoney() - oldMoney);
					totalDao.update(dmbOLD);
				}
				listDetailNew.add(oldDetail);
				totalMoney += oldDetail.getMoney();
			} else {// 新增记录
				if (user.getDept().equals(detail.getBudgetDept())) {// 部门一致
					// 判断是多部门还是本部门(本部门直接通过，跨部门，其他部门需要确认)
					detail.setStatus("print");
				} else {
					detail.setStatus("confirm");// 待确认
					status = "confirm";
				}
				// 计算部门预算明细
				double realM = detail.getMoney();
				if (null != dmb.getRealMoney()) {
					realM += dmb.getRealMoney();// 计算实际报销金额
				}
				dmb.setRealMoney(realM);
				// 判断有无超预算
				if (realM <= dmb.getAccountMoney()) {
					totalDao.update(dmb);
				} else {
					break;
				}
				// totalDao.save(detail);
				listDetailNew.add(detail);
				totalMoney += detail.getMoney();
			}
		}
		baoxiaodan.setTotalMoney((double) totalMoney);
		bxd.setBaoxiaoDetail(new HashSet(listDetailNew));
		bxd.setPlanMonth(baoxiaodan.getPlanMonth());
		bxd.setTotalMoney((double) totalMoney);
		bxd.setStatus(status);// 状态赋值
		bool = totalDao.update(bxd);
		if (bool) {
			// 查到老的数据
			PaymentVoucher paymentVoucher2 = new PaymentVoucher();
			if (null != bxd.getPaymentVouchers()) {
				paymentVoucher2 = (PaymentVoucher) this.totalDao.getObjectById(
						PaymentVoucher.class, bxd.getPaymentVouchers().getId());
			}
			// PaymentVoucher paymentVoucher2 = (PaymentVoucher)
			// this.totalDao.getObjectById(PaymentVoucher.class,
			// bxd.getPaymentVouchers().getId());
			if ("归还借款".equals(baoxiaodan.getPayStyle())) {
				String date1 = Util.getDateTime("yyyy-MM-dd");
				String str[] = money2.split(",");
				Float pay_money = Float.parseFloat(str[0]);// 拿到借款金额
				Integer pay_id = Integer.parseInt(str[1]);// 拿到id
				PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
						.getObjectById(PaymentVoucher.class, pay_id);
				// 用老的借款id与新的借款id对比
				if (!bxd.getPaymentVouchers().getId().toString().equals(
						pay_id.toString())) {
					// 修改时不为同一条借款
					paymentVoucher2.setPrepaidMoney(paymentVoucher2
							.getPrepaidMoney()
							- paymentVoucher2.getPrepaidMoney());// 更新老的付款金额
					bxd.setPaymentVouchers(paymentVoucher2);
					this.totalDao.update(paymentVoucher2);// 更新新的付款金额

					Set<BaoxiaoDan> pvSet = paymentVoucher.getBaoxiaoDanSet();
					if (pvSet == null) {
						pvSet = new HashSet<BaoxiaoDan>();
					}
					Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
					Float VoucherMoney = paymentVoucher.getVoucherMoney();// 总金额
					Float newMoney = VoucherMoney - prepaidMoney1;// 剩余金额
					if (prepaidMoney1 == null) {
						prepaidMoney1 = 0F;
					}
					Float pay_money1 = pay_money - money1;
					if (pay_money1 > 0) {
						paymentVoucher.setPaymentStatus("未付清");
						paymentVoucher.setVoucherdate(date1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					} else {
						paymentVoucher.setPaymentStatus("已付款");
						paymentVoucher.setVoucherdate(date1);
						// paymentVoucher.setPrepaidMoney(prepaidMoney1+money1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					}
					bxd.setPaymentVouchers(paymentVoucher);
					pvSet.add(bxd);
					paymentVoucher.setBaoxiaoDanSet(pvSet);
					this.totalDao.update(paymentVoucher);
				} else {
					// 修改借款时为同一条借款
					Set<BaoxiaoDan> pvSet = paymentVoucher.getBaoxiaoDanSet();
					if (pvSet == null) {
						pvSet = new HashSet<BaoxiaoDan>();
					}
					Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
					Float VoucherMoney = paymentVoucher.getVoucherMoney();// 总金额
					Float newMoney = VoucherMoney - prepaidMoney1;// 剩余金额
					if (prepaidMoney1 == null) {
						prepaidMoney1 = 0F;
					}
					Float pay_money1 = pay_money - money1;
					if (pay_money1 > 0) {
						paymentVoucher.setPaymentStatus("未付清");
						paymentVoucher.setVoucherdate(date1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					} else {
						paymentVoucher.setPaymentStatus("已付款");
						paymentVoucher.setVoucherdate(date1);
						// paymentVoucher.setPrepaidMoney(prepaidMoney1+money1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					}
					bxd.setPaymentVouchers(paymentVoucher);
					pvSet.add(bxd);
					paymentVoucher.setBaoxiaoDanSet(pvSet);
					try {
						this.totalDao.update(paymentVoucher);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} else {
				// 还原老的金额
				if (null != bxd.getPaymentVouchers()) {
					PaymentVoucher paymentVoucher1 = (PaymentVoucher) this.totalDao
							.getObjectById(PaymentVoucher.class, bxd
									.getPaymentVouchers().getId());
					paymentVoucher1.setPrepaidMoney(paymentVoucher1
							.getPrepaidMoney()
							- paymentVoucher2.getPrepaidMoney());
					this.totalDao.update(paymentVoucher1);
				}

			}
		}
		return bool;
	}

	@Override
	public BaoxiaoDan getBaoXiaoDanById(Integer id) {
		BaoxiaoDan baoxiaoDan = (BaoxiaoDan) totalDao.getObjectById(
				BaoxiaoDan.class, id);
		// baoxiaoDan.getPaymentVouchers().getAccreditationnum();
		return baoxiaoDan;
	}

	@Override
	public void exportExcel(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, String tag) {
		if ("sum".equals(tag)) {// 汇总数据导出
			String hql = "select sum(totalMoney) as totalMoney,dept,currency from BaoxiaoDan where 1=1";
			String groupBy = "group by dept,currency";
			if (null != baoxiaodan) {
				if (!"".equals(baoxiaodan.getDept())
						&& null != baoxiaodan.getDept()
						&& null != baoxiaodan.getBaoxiaoClass()
						&& !"".equals(baoxiaodan.getBaoxiaoClass())) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept()
							+ "' and baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if (("".equals(baoxiaodan.getDept()) || null == baoxiaodan
						.getDept())
						&& (null != baoxiaodan.getBaoxiaoClass() && !""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if ((!"".equals(baoxiaodan.getDept()) && null != baoxiaodan
						.getDept())
						&& (null == baoxiaodan.getBaoxiaoClass() || ""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				}
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					hql += " and baoxiaoDate between '" + startDate + "' and '"
							+ endDate + "'";
				}
			}
			hql += groupBy;
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response
						.setHeader("Content-disposition",
								"attachment; filename="
										+ new String("baoxiaoHuizong"
												.getBytes("GB2312"), "8859_1")
										+ ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("财务报销数据汇总", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "财务报销数据汇总", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 7, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(2, 1, "类别", wc));
				ws.addCell(new jxl.write.Label(3, 1, "合计金额", wc));
				ws.addCell(new jxl.write.Label(4, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(5, 1, "汇总开始时间", wc));
				ws.addCell(new jxl.write.Label(6, 1, "汇总截止时间", wc));
				for (int i = 0; i < list.size(); i++) {
					Object[] o = (Object[]) list.get(i);
					String dept = (String) o[1];
					String bizhong = (String) o[2];
					double count = (Double) o[0];
					float cou = (float) count;
					float f = Float.parseFloat(String.valueOf(cou));
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, dept, wc));
					if (null != baoxiaodan.getBaoxiaoClass()
							&& !"".equals(baoxiaodan.getBaoxiaoClass())) {// 判断报销科目是否为空
						ws.addCell(new Label(2, i + 2, baoxiaodan
								.getBaoxiaoClass(), wc));
					} else {
						ws.addCell(new Label(2, i + 2, "全部", wc));
					}
					ws.addCell(new jxl.write.Number(3, i + 2, f, wc));

					ws.addCell(new Label(4, i + 2, bizhong, wc));
					ws.addCell(new Label(5, i + 2, startDate, wc));
					ws.addCell(new Label(6, i + 2, endDate, wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} else if ("bxd".equals(tag)) {// 报销单数据导出
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String username = user.getName();
			String hql = "from BaoxiaoDan where 1=1";
			if (null != startDate && null != endDate && !"".equals(endDate)
					&& !"".equals(startDate)) {
				hql += " and baoxiaoDate between '" + startDate + "' and '"
						+ endDate + "' ";
			}
			if (null != baoxiaodan) {
				if (null != baoxiaodan.getShoukuanRen()
						&& !"".equals(baoxiaodan.getShoukuanRen())) {
					hql += " and shoukuanRen='" + baoxiaodan.getShoukuanRen()
							+ "'";
				}
				if (null != baoxiaodan.getPayStyle()
						&& !"".equals(baoxiaodan.getPayStyle())) {
					hql += " and payStyle='" + baoxiaodan.getPayStyle() + "'";
				}
				if (null != baoxiaodan.getBaoxiaoren()
						&& !"".equals(baoxiaodan.getBaoxiaoren())) {
					hql += " and baoxiaoren='" + baoxiaodan.getBaoxiaoren()
							+ "'";
				}
				if (null != baoxiaodan.getBaoxiaoBarcode()
						&& !"".equals(baoxiaodan.getBaoxiaoBarcode())) {
					hql += " and baoxiaoBarcode='"
							+ baoxiaodan.getBaoxiaoBarcode() + "'";
				}
				if (null != baoxiaodan.getDept()
						&& !"".equals(baoxiaodan.getDept())) {
					hql += " dept='" + baoxiaodan.getDept() + "'";
				}
			}
			hql += " order by saveTime desc";
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename="
								+ new String("baoxiaodan".getBytes("GB2312"),
										"8859_1") + ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("报销单信息", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "报销单信息", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 9, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "收款单位(个人)", wc));
				ws.addCell(new jxl.write.Label(2, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(3, 1, "报销人", wc));
				ws.addCell(new jxl.write.Label(4, 1, "付款方式", wc));
				ws.addCell(new jxl.write.Label(5, 1, "报销日期", wc));
				ws.addCell(new jxl.write.Label(6, 1, "报销金额", wc));
				ws.addCell(new jxl.write.Label(7, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(8, 1, "报销编号", wc));
				for (int i = 0; i < list.size(); i++) {
					BaoxiaoDan bxd = (BaoxiaoDan) list.get(i);
					/*
					 * Object[] o=(Object[])list.get(i); String
					 * dept=(String)o[1]; double count=(Double)o[0]; float
					 * cou=(float)count; float f =
					 * Float.parseFloat(String.valueOf(cou));
					 */
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, bxd.getShoukuanRen(), wc));
					ws.addCell(new Label(2, i + 2, bxd.getDept(), wc));
					ws.addCell(new Label(3, i + 2, bxd.getBaoxiaoren(), wc));
					ws.addCell(new Label(4, i + 2, bxd.getPayStyle(), wc));
					ws.addCell(new Label(5, i + 2, bxd.getBaoxiaoDate(), wc));
					ws.addCell(new jxl.write.Number(6, i + 2, bxd
							.getTotalMoney(), wc));
					ws.addCell(new Label(7, i + 2, bxd.getCurrency(), wc));
					ws
							.addCell(new Label(8, i + 2, bxd
									.getBaoxiaoBarcode(), wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

		} else if ("detail".equals(tag)) {// 明细数据导出

		}

	}

	@Override
	public void exportExcel1(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, String tag) {
		if ("sum".equals(tag)) {// 汇总数据导出
			String hql = "select sum(totalMoney) as totalMoney,dept,currency from BaoxiaoDan where 1=1";
			String groupBy = "group by dept,currency";
			if (null != baoxiaodan) {
				if (!"".equals(baoxiaodan.getDept())
						&& null != baoxiaodan.getDept()
						&& null != baoxiaodan.getBaoxiaoClass()
						&& !"".equals(baoxiaodan.getBaoxiaoClass())) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept()
							+ "' and baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if (("".equals(baoxiaodan.getDept()) || null == baoxiaodan
						.getDept())
						&& (null != baoxiaodan.getBaoxiaoClass() && !""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if ((!"".equals(baoxiaodan.getDept()) && null != baoxiaodan
						.getDept())
						&& (null == baoxiaodan.getBaoxiaoClass() || ""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				}
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					hql += " and  producestatus!='' and baoxiaoDate between '"
							+ startDate + "' and '" + endDate + "'";
				}
			}
			hql += groupBy;
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response
						.setHeader("Content-disposition",
								"attachment; filename="
										+ new String("baoxiaoHuizong"
												.getBytes("GB2312"), "8859_1")
										+ ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("财务报销数据汇总", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "财务报销数据汇总", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 7, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(2, 1, "类别", wc));
				ws.addCell(new jxl.write.Label(3, 1, "合计金额", wc));
				ws.addCell(new jxl.write.Label(4, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(5, 1, "汇总开始时间", wc));
				ws.addCell(new jxl.write.Label(6, 1, "汇总截止时间", wc));
				for (int i = 0; i < list.size(); i++) {
					Object[] o = (Object[]) list.get(i);
					String dept = (String) o[1];
					String bizhong = (String) o[2];
					double count = (Double) o[0];
					float cou = (float) count;
					float f = Float.parseFloat(String.valueOf(cou));
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, dept, wc));
					if (null != baoxiaodan.getBaoxiaoClass()
							&& !"".equals(baoxiaodan.getBaoxiaoClass())) {// 判断报销科目是否为空
						ws.addCell(new Label(2, i + 2, baoxiaodan
								.getBaoxiaoClass(), wc));
					} else {
						ws.addCell(new Label(2, i + 2, "全部", wc));
					}
					ws.addCell(new jxl.write.Number(3, i + 2, f, wc));

					ws.addCell(new Label(4, i + 2, bizhong, wc));
					ws.addCell(new Label(5, i + 2, startDate, wc));
					ws.addCell(new Label(6, i + 2, endDate, wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} else if ("bxd".equals(tag)) {// 报销单数据导出
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String username = user.getName();
			String hql = "from BaoxiaoDan where 1=1 and producestatus!='' ";
			if (null != startDate && null != endDate && !"".equals(endDate)
					&& !"".equals(startDate)) {
				hql += " and baoxiaoDate between '" + startDate + "' and '"
						+ endDate + "' ";
			}
			if (null != baoxiaodan) {
				if (null != baoxiaodan.getShoukuanRen()
						&& !"".equals(baoxiaodan.getShoukuanRen())) {
					hql += " and shoukuanRen='" + baoxiaodan.getShoukuanRen()
							+ "'";
				}
				if (null != baoxiaodan.getPayStyle()
						&& !"".equals(baoxiaodan.getPayStyle())) {
					hql += " and payStyle='" + baoxiaodan.getPayStyle() + "'";
				}
				if (null != baoxiaodan.getBaoxiaoren()
						&& !"".equals(baoxiaodan.getBaoxiaoren())) {
					hql += " and baoxiaoren='" + baoxiaodan.getBaoxiaoren()
							+ "'";
				}
				if (null != baoxiaodan.getBaoxiaoBarcode()
						&& !"".equals(baoxiaodan.getBaoxiaoBarcode())) {
					hql += " and baoxiaoBarcode='"
							+ baoxiaodan.getBaoxiaoBarcode() + "'";
				}
				if (null != baoxiaodan.getDept()
						&& !"".equals(baoxiaodan.getDept())) {
					hql += " dept='" + baoxiaodan.getDept() + "'";
				}
			}
			hql += " order by saveTime desc";
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename="
								+ new String("baoxiaodan".getBytes("GB2312"),
										"8859_1") + ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("报销单信息", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "报销单信息", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 9, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "收款单位(个人)", wc));
				ws.addCell(new jxl.write.Label(2, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(3, 1, "报销人", wc));
				ws.addCell(new jxl.write.Label(4, 1, "付款方式", wc));
				ws.addCell(new jxl.write.Label(5, 1, "报销日期", wc));
				ws.addCell(new jxl.write.Label(6, 1, "报销金额", wc));
				ws.addCell(new jxl.write.Label(7, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(8, 1, "报销编号", wc));
				for (int i = 0; i < list.size(); i++) {
					BaoxiaoDan bxd = (BaoxiaoDan) list.get(i);
					/*
					 * Object[] o=(Object[])list.get(i); String
					 * dept=(String)o[1]; double count=(Double)o[0]; float
					 * cou=(float)count; float f =
					 * Float.parseFloat(String.valueOf(cou));
					 */
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, bxd.getShoukuanRen(), wc));
					ws.addCell(new Label(2, i + 2, bxd.getDept(), wc));
					ws.addCell(new Label(3, i + 2, bxd.getBaoxiaoren(), wc));
					ws.addCell(new Label(4, i + 2, bxd.getPayStyle(), wc));
					ws.addCell(new Label(5, i + 2, bxd.getBaoxiaoDate(), wc));
					ws.addCell(new jxl.write.Number(6, i + 2, bxd
							.getTotalMoney(), wc));
					ws.addCell(new Label(7, i + 2, bxd.getCurrency(), wc));
					ws
							.addCell(new Label(8, i + 2, bxd
									.getBaoxiaoBarcode(), wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

		} else if ("detail".equals(tag)) {// 明细数据导出

		}

	}

	@Override
	public String findBaoXiaoStyle(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "";
			if ("dept".equals(tag)) {
				hql = "select distinct(dept) from BaoxiaoDan";
			} else if ("course".equals(tag)) {
				hql = "select distinct(baoxiaoClass) from BaoxiaoDan";
			} else if ("baoxiaoStyle".equals(tag)) {
				hql = "select distinct(baoxiaoStyle) from BaoxiaoDetail";
			} else if ("markId".equals(tag)) {
				hql = "select distinct(markId) from BaoxiaoDetail";
			} else if ("shoukuanRen".equals(tag)) {
				hql = "select distinct(shoukuanRen) from BaoxiaoDan";
			}
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	/***
	 * 查找部门确认下la
	 */
	public String findDeptConfirm(String tag) {
		String message = "";
		Users user = Util.getLoginUser();
		if (null != tag && !"".equals(tag)) {
			String hql = "";
			if ("baoxiaoStyle".equals(tag)) {
				hql = "select distinct(baoxiaoStyle) from BaoxiaoDetail where budgetDept='"
						+ user.getDept() + "' and status in('back','ok')";
			} else if ("planMonth".equals(tag)) {
				hql = "select distinct(planMonth) from BaoxiaoDan where id in(select d.baoxiaoDan.id from BaoxiaoDetail d where d.budgetDept='"
						+ user.getDept() + "' and d.status in('back','ok'))";
			} else if ("shoukuanRen".equals(tag)) {
				hql = "select distinct(shoukuanRen) from BaoxiaoDan where id in(select d.baoxiaoDan.id from BaoxiaoDetail d where d.budgetDept='"
						+ user.getDept() + "' and d.status in('back','ok'))";
			}
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	public String findchildClass(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			try {
				tag = URLDecoder.decode(tag, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}

			String hql = "select distinct(childCourse) from BaoxiaoCourse where fatherCourse='"
					+ tag + "'";
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	// 查找部门下拉
	public String findchildDept(String tag, String planMonth) {
		String message = "";
		if (null != planMonth && !"".equals(planMonth)) {
			try {
				planMonth = URLDecoder.decode(planMonth, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != tag && !"".equals(tag)) {
			try {
				tag = URLDecoder.decode(tag, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if ("self".equals(tag)) {
				Users user = (Users) Util.getLoginUser();
				message = user.getDept() + "|";
			} else {// 根据月份查找
				planMonth = Util.getDateTime("yyyy");
				String hql = "select distinct(userDept) from DeptMonthBudget where status='同意' and budgetMonth like '%"
						+ planMonth + "%'";// and
				// status='同意'
				// 状态暂时可以先不限制
				List<String> list = totalDao.query(hql);
				for (String d : list) {
					message += d.toString() + "|";
				}
			}
		}
		return message;
	}

	public List findchildSubjects(String tag, String planMonth) {
		// String hql = "";
		// List list =null;
		// if (planMonth != null && !"".equals(planMonth)) {
		// hql = "from DeptMonthBudget where budgetMonth='" + planMonth
		// + "' and userDept='" + tag
		// + "' and status='同意' order by budgetMonth desc";// and
		// // status='同意'
		// // 状态暂时可以先不限制
		// list = totalDao.query(hql);
		// } else {
		// planMonth = Util.getDateTime("yyyy");
		// hql = "from DeptMonthBudget where  userDept='" + tag
		// +
		// "' and status='同意' and budgetMonth like '%"+planMonth+"%' order by budgetMonth desc";//
		// and
		// // status='同意'
		// // 状态暂时可以先不限制
		// list = totalDao.query(hql);
		// }
		String hql = "from SubBudgetRate";
		if ("payType".equals(tag)) {
			hql += " where rootId in (select id from SubBudgetRate where name in ('库存现金','银行存款')) order by rootId,id";
		}
		List list = totalDao.query(hql);
		return list;
	}

	public List findchildSubjects2(String tag) {
		Users user = Util.getLoginUser();
		if (user != null && tag != null) {
			List<Object[]> list = new ArrayList<Object[]>();
			// List<QuotedPriceCost> qpCostList =
			// totalDao.query("from QuotedPriceCost where ");
			if (tag.equals("项目")) {
				List<QuotedPriceCost> qpCostList = totalDao
						.query("from QuotedPriceCost where source='预算申报' and applyStatus='同意' and (bxMoney is null or money>bxMoney)");

				if (qpCostList != null && qpCostList.size() > 0) {
					for (QuotedPriceCost qpCost : qpCostList) {
						String proName = (String) totalDao
								.getObjectByCondition(
										"select projectName from ProjectManage where id=(select proId from QuotedPrice where id=?)",
										qpCost.getQpId());
					}
				}
				return qpCostList;
			} else if (tag.equals("KVP")) {
				List<ExecuteKVP> kvpList = totalDao
						.query("from ExecuteKVP where status='同意' and  res_usercode like '%"
								+ user.getCode()
								+ "%' and (bxMoney is null or costsavings>bxMoney)");
				if (kvpList != null && kvpList.size() > 0) {
					for (ExecuteKVP kvp : kvpList) {
						kvp.getKvpAssess().getPart_name();
						kvp.setImproved_beforeproblems(null);
						kvp.setImproved_endproblems(null);
						kvp.getKvpAssess().setImproved_beforeproblems(null);
						kvp.getKvpAssess().setImproved_endproblems(null);
					}
				}
				return kvpList;
			}else if(tag.equals("质量")){
				return totalDao.query("from CustomerOpinion");
			}

		}
		return null;
	}

	public List findchildSubjects3(String tag) {
		Users user = Util.getLoginUser();
		if (user != null && tag != null) {
			List<FundApply> list = totalDao
					.query("from FundApply where userId="
							+ user.getId()
							+ " and zhifuoryufu='预付' and status in('待还款','未付清')");
			return list;
		}
		return null;
	}

	@Override
	public Object[] findSumBaoXiaoDan(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, Integer cpage, Integer pageSize) {
		String hql = "select sum(totalMoney) as totalMoney,dept,currency from BaoxiaoDan where 1=1";
		String groupBy = "group by dept,currency";
		if (null != baoxiaodan) {
			if (!"".equals(baoxiaodan.getDept())
					&& null != baoxiaodan.getDept()
					&& null != baoxiaodan.getBaoxiaoClass()
					&& !"".equals(baoxiaodan.getBaoxiaoClass())) {
				hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
						+ baoxiaodan.getDept()
						+ "' and baoxiaoClass='"
						+ baoxiaodan.getBaoxiaoClass() + "'";
				groupBy = "group by dept,currency,baoxiaoClass";
			} else if (("".equals(baoxiaodan.getDept()) || null == baoxiaodan
					.getDept())
					&& (null != baoxiaodan.getBaoxiaoClass() && !""
							.equals(baoxiaodan.getBaoxiaoClass()))) {
				hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where baoxiaoClass='"
						+ baoxiaodan.getBaoxiaoClass() + "'";
				groupBy = "group by dept,currency,baoxiaoClass";
			} else if ((!"".equals(baoxiaodan.getDept()) && null != baoxiaodan
					.getDept())
					&& (null == baoxiaodan.getBaoxiaoClass() || ""
							.equals(baoxiaodan.getBaoxiaoClass()))) {
				hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
						+ baoxiaodan.getDept() + "'";
				groupBy = "group by dept,currency,baoxiaoClass";
			}
			if (null != startDate && !"".equals(startDate) && null != endDate
					&& !"".equals(endDate)) {
				hql += " and baoxiaoDate between '" + startDate + "' and '"
						+ endDate + "'";
			}

		}
		hql += groupBy;

		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.find(hql).size();// list.size();
		Object[] procardSUM = new Object[2];
		procardSUM[0] = count;
		procardSUM[1] = list;
		return procardSUM;
	}

	public Object[] findBaoXiaoDanDetail(BaoxiaoDan baoxiaodan,
			BaoxiaoDetail detail, String startDate, String endDate,
			Integer cpage, Integer pageSize, String tag) {
		String hql = "from BaoxiaoDetail where 1=1";
		if (null != detail) {
			if (null != detail.getBaoxiaoStyle()
					&& !"".equals(detail.getBaoxiaoStyle())) {
				hql += " and baoxiaoStyle='" + detail.getBaoxiaoStyle() + "'";
			}
			if (null != detail.getMarkId() && !"".equals(detail.getMarkId())) {
				hql += " and markId='" + detail.getMarkId() + "'";
			}
		}
		if (null != baoxiaodan) {
			if (null != baoxiaodan.getShoukuanRen()
					&& !"".equals(baoxiaodan.getShoukuanRen())) {
				hql += " and baoxiaoDan.shoukuanRen='"
						+ baoxiaodan.getShoukuanRen() + "'";
			}
			if (null != baoxiaodan.getPayStyle()
					&& !"".equals(baoxiaodan.getPayStyle())) {
				hql += " and baoxiaoDan.payStyle='" + baoxiaodan.getPayStyle()
						+ "'";
			}
			if (null != baoxiaodan.getBaoxiaoren()
					&& !"".equals(baoxiaodan.getBaoxiaoren())) {
				hql += " and baoxiaoDan.baoxiaoren='"
						+ baoxiaodan.getBaoxiaoren() + "'";
			}
			if (null != baoxiaodan.getDept()
					&& !"".equals(baoxiaodan.getDept())) {
				hql += " and baoxiaoDan.dept='" + baoxiaodan.getDept() + "'";
			}
			if (null != baoxiaodan.getBaoxiaoBarcode()
					&& !"".equals(baoxiaodan.getBaoxiaoBarcode())) {
				hql += " and baoxiaoDan.baoxiaoBarcode='"
						+ baoxiaodan.getBaoxiaoBarcode() + "'";
			}

		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and baoxiaoDan.baoxiaoDate between '" + startDate
					+ "' and '" + endDate + "'";
		}
		hql += " order by id desc";
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	public void exportDetailExcel(BaoxiaoDan baoxiaodan, BaoxiaoDetail detail,
			String startDate, String endDate, String tag) {
		String hql = "from BaoxiaoDetail where 1=1";
		if (null != detail) {
			if (null != detail.getBaoxiaoStyle()
					&& !"".equals(detail.getBaoxiaoStyle())) {
				hql += " and baoxiaoStyle='" + detail.getBaoxiaoStyle() + "'";
			}
			if (null != detail.getMarkId() && !"".equals(detail.getMarkId())) {
				hql += " and markId='" + detail.getMarkId() + "'";
			}
		}
		if (null != baoxiaodan) {
			if (null != baoxiaodan.getShoukuanRen()
					&& !"".equals(baoxiaodan.getShoukuanRen())) {
				hql += " and baoxiaoDan.shoukuanRen='"
						+ baoxiaodan.getShoukuanRen() + "'";
			}
			if (null != baoxiaodan.getPayStyle()
					&& !"".equals(baoxiaodan.getPayStyle())) {
				hql += " and baoxiaoDan.payStyle='" + baoxiaodan.getPayStyle()
						+ "'";
			}
			if (null != baoxiaodan.getBaoxiaoren()
					&& !"".equals(baoxiaodan.getBaoxiaoren())) {
				hql += " and baoxiaoDan.baoxiaoren='"
						+ baoxiaodan.getBaoxiaoren() + "'";
			}
			if (null != baoxiaodan.getDept()
					&& !"".equals(baoxiaodan.getDept())) {
				hql += " and baoxiaoDan.dept='" + baoxiaodan.getDept() + "'";
			}
			if (null != baoxiaodan.getBaoxiaoBarcode()
					&& !"".equals(baoxiaodan.getBaoxiaoBarcode())) {
				hql += " and baoxiaoDan.baoxiaoBarcode='"
						+ baoxiaodan.getBaoxiaoBarcode() + "'";
			}

		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and baoxiaoDan.baoxiaoDate between '" + startDate
					+ "' and '" + endDate + "'";
		}
		hql += " order by id desc";
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("baoxiaodetail".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("报销明细信息", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "报销明细信息", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 12, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "报销类别", wc));
			ws.addCell(new jxl.write.Label(2, 1, "内容摘要", wc));
			ws.addCell(new jxl.write.Label(3, 1, "所属总成", wc));
			ws.addCell(new jxl.write.Label(4, 1, "金额", wc));
			ws.addCell(new jxl.write.Label(5, 1, "币种", wc));
			ws.addCell(new jxl.write.Label(6, 1, "收款单位(个人)", wc));
			ws.addCell(new jxl.write.Label(7, 1, "报销人", wc));
			ws.addCell(new jxl.write.Label(8, 1, "部门", wc));
			ws.addCell(new jxl.write.Label(9, 1, "付款方式", wc));
			ws.addCell(new jxl.write.Label(10, 1, "报销日期 ", wc));
			ws.addCell(new jxl.write.Label(11, 1, "报销编号", wc));
			for (int i = 0; i < list.size(); i++) {
				BaoxiaoDetail baoxiaoDetail = (BaoxiaoDetail) list.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, baoxiaoDetail.getBaoxiaoStyle(),
						wc));
				ws.addCell(new Label(2, i + 2, baoxiaoDetail
						.getBaoxiaoContent(), wc));
				ws.addCell(new Label(3, i + 2, baoxiaoDetail.getMarkId(), wc));
				ws.addCell(new jxl.write.Number(4, i + 2, baoxiaoDetail
						.getMoney(), wc));
				ws.addCell(new Label(5, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getCurrency(), wc));
				ws.addCell(new Label(6, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getShoukuanRen(), wc));
				ws.addCell(new Label(7, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getBaoxiaoren(), wc));
				ws.addCell(new Label(8, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getDept(), wc));
				ws.addCell(new Label(9, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getPayStyle(), wc));
				ws.addCell(new Label(10, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getBaoxiaoDate(), wc));
				ws.addCell(new Label(11, i + 2, baoxiaoDetail.getBaoxiaoDan()
						.getBaoxiaoBarcode(), wc));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	/** 生成报销月份 */
	public String getPlanMonth() {
		Users user = Util.getLoginUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String plm = sdf.format(new Date());
		// 可查询本部门的预算填报表
		/*
		 * String hql = " from DeptMonthBudget where budgetMonth='" + plm +
		 * "' and status='同意' and userDept='"+user.getDept()+"'";
		 */
		String hql = " from DeptMonthBudget where status='同意' and userDept='"
				+ user.getDept() + "'";
		List list = totalDao.find(hql);
		if (list.size() > 0 && list != null) {
			return plm;
		}
		return null;
	}

	// 比较预算与决算金额大小
	@Override
	public String compareBudgetCount(Integer id, Float money) {
		DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
				DeptMonthBudget.class, id);
		double realM = 0;
		if (null != dmb.getRealMoney()) {
			realM = dmb.getRealMoney();
		}
		double mo_budget = dmb.getAccountMoney() - realM;// 剩余可决算金额
		/*
		 * String
		 * hql="select sum(money) from BaoxiaoDetail where deptMonthBudgetID="
		 * +id; List list=totalDao.find(hql); float mo_bx=money;//历史报销过的金额
		 * if(list.size()>0 && list!=null && null!=list.get(0)){
		 * mo_bx+=(Float)list.get(0); }
		 */
		String message = "NO";
		if (mo_budget >= money) {
			message = "OK";
		}
		return message;
	}

	@Override
	public DeptMonthBudget getdeptMonthBudgetById(Integer id) {
		// TODO Auto-generated method stub
		return (DeptMonthBudget) totalDao.getObjectById(DeptMonthBudget.class,
				id);
	}

	@Override
	public BaoxiaoDetail getbxDetailById(Integer id) {
		// TODO Auto-generated method stub
		return (BaoxiaoDetail) totalDao.getObjectById(BaoxiaoDetail.class, id);
	}

	/***
	 * 部门确认
	 */
	@Override
	public Object[] findOtherDeptBXD(BaoxiaoDan baoxiaodan,
			BaoxiaoDetail detail, String startDate, String endDate,
			Integer cpage, Integer pageSize, String tag) {
		// TODO Auto-generated method stub
		Users user = (Users) Util.getLoginUser();
		String hqlConfirm = "from BaoxiaoDetail where status=? and budgetDept=?";
		List listConfirm = totalDao
				.query(hqlConfirm, "confirm", user.getDept());
		String hql = " from BaoxiaoDetail where status in('back','ok') and budgetDept='"
				+ user.getDept() + "'";
		if (null != detail) {
			if (null != detail.getBaoxiaoStyle()
					&& !"".equals(detail.getBaoxiaoStyle())) {
				hql += " and baoxiaoStyle='" + detail.getBaoxiaoStyle() + "'";
			}
			if (null != detail.getBaoxiaoContent()
					&& !"".equals(detail.getBaoxiaoContent())) {
				hql += " and baoxiaoContent='" + detail.getBaoxiaoContent()
						+ "'";
			}
		}
		if (null != baoxiaodan) {
			if (null != baoxiaodan.getDept()
					&& !"".equals(baoxiaodan.getDept())) {
				hql += " and baoxiaoDan.dept='" + baoxiaodan.getDept() + "'";
			}
			if (null != baoxiaodan.getPlanMonth()
					&& !"".equals(baoxiaodan.getPlanMonth())) {
				hql += " and baoxiaoDan.planMonth='"
						+ baoxiaodan.getPlanMonth() + "'";
			}
			if (null != baoxiaodan.getShoukuanRen()
					&& !"".equals(baoxiaodan.getShoukuanRen())) {
				hql += " and baoxiaoDan.shoukuanRen='"
						+ baoxiaodan.getShoukuanRen() + "'";
			}
			if (null != baoxiaodan.getPayStyle()
					&& !"".equals(baoxiaodan.getPayStyle())) {
				hql += " and baoxiaoDan.payStyle='" + baoxiaodan.getPayStyle()
						+ "'";
			}
		}
		hql += " order by saveTime desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.find(hql).size();// list.size();
		Object[] confirmD = new Object[3];
		confirmD[0] = count;
		confirmD[1] = list;
		confirmD[2] = listConfirm;
		return confirmD;
	}

	/**
	 * 确认审核（确认print，打回 back）
	 */
	@Override
	public boolean updateDetailById(Integer id, String tag) {
		// TODO Auto-generated method stub
		boolean b = false;
		BaoxiaoDetail detail = (BaoxiaoDetail) totalDao.getObjectById(
				BaoxiaoDetail.class, id);
		if ("ok".equals(tag)) {
			detail.setStatus("ok");
		} else {
			detail.setStatus("back");
			DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
					DeptMonthBudget.class, detail.getDeptMonthBudgetID());
			dmb.setRealMoney(dmb.getRealMoney() - detail.getMoney());
			b = totalDao.update(dmb);
		}
		Users user = Util.getLoginUser();
		detail.setConfirmCode(user.getCode());
		detail.setConfirmTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		b = totalDao.update(detail);
		BaoxiaoDan bxd = detail.getBaoxiaoDan();
		String hql = " from BaoxiaoDetail b where b.baoxiaoDan.id="
				+ bxd.getId() + " and status='confirm'";
		List list = totalDao.query(hql);
		// 可以打印
		if (list.size() > 0 && null != list) {
		} else {
			bxd.setStatus("print");
			b = totalDao.update(bxd);
		}
		return b;
	}

	/*
	 * 财务确认(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.BaoXiaoDanServer#updateXiaoDan(java.lang.Integer)
	 */
	@Override
	public void updateXiaoDan(Integer id) {
		// TODO Auto-generated method stub
		BaoxiaoDan baoxiaoDan = (BaoxiaoDan) this.totalDao.getObjectById(
				BaoxiaoDan.class, id);
		baoxiaoDan.setDealstatus("已还款");
		this.totalDao.update(baoxiaoDan);
	}

	/*
	 * 查找入库表件号(non-Javadoc)
	 * 
	 * @see com.task.Server.fin.BaoXiaoDanServer#findjianhao()
	 */
	@Override
	public String findjianhao() {
		String message = "";
		String hql = "select distinct(goodsStoreMarkId) from GoodsStore where status='入库' and goodsStoreMarkId is not null "
				+ "and (baoxiao_status ='未报完' or baoxiao_status is null)";// and
		// status='同意'
		// 状态暂时可以先不限制
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	/*
	 * 根据件号查询批次
	 * 
	 * @Override(non-Javadoc)
	 * 
	 * @see com.task.Server.fin.BaoXiaoDanServer#findpici(java.lang.String)
	 */
	public List findpici(String tag) {
		String hql = "from GoodsStore where goodsStoreMarkId=? and status='入库' and goodsStoreLot is not null"
				+ " and (baoxiao_status ='未报完' or baoxiao_status is null)";
		List list = totalDao.query(hql, tag);
		return list;
	}

	/*
	 * 根据件号和批次查询数量(non-Javadoc)
	 * 
	 * @see com.task.Server.fin.BaoXiaoDanServer#findshuliang(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public GoodsStore findshuliang(String tag, String goodsStoreLots) {
		String hql = "from GoodsStore where goodsStoreMarkId=? and goodsStoreLot=? and status='入库' and"
				+ " (baoxiao_status ='未报完' or baoxiao_status is null)";
		GoodsStore goodsStore = (GoodsStore) this.totalDao.getObjectByQuery(
				hql, tag, goodsStoreLots);
		// String count = goodsStore.getGoodsStoreCount().toString();
		return goodsStore;
	}

	/*
	 * 添加生产性报销单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.BaoXiaoDanServer#saveBaoXiaoDan1(com.task.entity.
	 * fin.BaoxiaoDan, java.util.List, java.lang.Float, java.lang.String)
	 */
	@Override
	public boolean saveBaoXiaoDan1(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, Float money1, String money2,
			String tag, List<Integer> ids) {
		boolean boo = false;
		List<BaoxiaoDetail> listDetailNew = new ArrayList<BaoxiaoDetail>();
		String status = "print";// 可以直接打印
		if (null != baoxiaodan) {
			Users user = (Users) Util.getLoginUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat yy = new SimpleDateFormat("yyyyMM");
			if (ids != null && ids.size() > 0) {
				Set<WaigouWaiweiPlan> wwplanSet = new HashSet<WaigouWaiweiPlan>();
				for (Integer waiweiPlanId : ids) {
					WaigouWaiweiPlan waiweiPlan = (WaigouWaiweiPlan) this.totalDao
							.getObjectById(WaigouWaiweiPlan.class, waiweiPlanId);
					Set<BaoxiaoDan> baoxiaoDanSet = waiweiPlan.getBaoxiaodans();
					if (baoxiaoDanSet == null) {
						baoxiaoDanSet = new HashSet<BaoxiaoDan>();
					}
					baoxiaoDanSet.add(baoxiaodan);
					waiweiPlan.setBaoxiaodans(baoxiaoDanSet);
					wwplanSet.add(waiweiPlan);
				}
				baoxiaodan.setWaigouWaiweiPlans(wwplanSet);
			}
			for (int i = 0; i < listDetail.size(); i++) {
				BaoxiaoDetail baoxiaoDetail = listDetail.get(i);
				baoxiaoDetail.setBaoxiaoStyle("");
				baoxiaoDetail.setSaveTime(sdf.toString());
				listDetailNew.add(baoxiaoDetail);
				// 标识是否是其他费用报销
				if (tag == null || "".equals(tag)) {
					GoodsStore goodsStore = (GoodsStore) this.totalDao
							.getObjectById(GoodsStore.class, baoxiaoDetail
									.getGoodsStoreId());
					goodsStore.setContrast_num(baoxiaoDetail
							.getGoodsStoreCount());
					if (goodsStore.getGoodsStoreCount() > baoxiaoDetail
							.getGoodsStoreCount()) {
						goodsStore.setBaoxiao_status("未报完");
					} else {
						goodsStore.setBaoxiao_status("已报销");
					}
					this.totalDao.update(goodsStore);
				}
			}
			float totalMoney = 0f;
			if (money1 != null) {
				totalMoney = money1;
			}
			baoxiaodan.setTotalMoney((double) totalMoney);
			baoxiaodan.setSaveTime(sdf.format(new Date()));
			baoxiaodan.setCode(user.getCode());
			baoxiaodan.setDept(user.getDept());
			String bianhao = user.getPassword().getDeptNumber() + "-BX-"
					+ yy.format(new Date());
			String hql = "select max(baoxiaoBarcode) from BaoxiaoDan where baoxiaoBarcode like'%"
					+ bianhao + "%' ";
			if (null != totalDao.find(hql) && totalDao.find(hql).size() > 0
					&& null != totalDao.find(hql).get(0)) {
				String maxBarcode = (String) totalDao.find(hql).get(0);
				String num = maxBarcode.substring(bianhao.length());
				Integer intHao = Integer.parseInt(num);
				intHao++;
				String strHao = intHao.toString();
				while (strHao.length() < 3) {
					strHao = "0" + strHao;
				}
				bianhao += strHao;
			} else {
				bianhao += "001";
			}
			baoxiaodan.setStatus(status);// 状态赋值
			baoxiaodan.setBaoxiaoBarcode(bianhao);
			if (tag == null || "".equals(tag)) {
				baoxiaodan.setProducestatus("生产");
			} else {
				if ("wg".equals(tag)) {
					baoxiaodan.setProducestatus("外购");
				} else if ("ww".equals(tag)) {
					baoxiaodan.setProducestatus("外委");
				} else {
					baoxiaodan.setProducestatus("其他");
				}
			}

			baoxiaodan.setBaoxiaoDetail(new HashSet(listDetailNew));// 在一方维护添加
			boo = totalDao.save(baoxiaodan);

			if ("归还借款".equals(baoxiaodan.getPayStyle())) {
				String date1 = Util.getDateTime("yyyy-MM-dd");
				String str[] = money2.split(",");
				Float pay_money = Float.parseFloat(str[0]);// 拿到借款金额
				Integer pay_id = Integer.parseInt(str[1]);// 拿到id
				PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
						.getObjectById(PaymentVoucher.class, pay_id);
				Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
				if (prepaidMoney1 == null) {
					prepaidMoney1 = 0F;
				}
				Float pay_money1 = pay_money - money1;
				if (pay_money1 > 0) {
					paymentVoucher.setPaymentStatus("未付清");
					paymentVoucher.setVoucherdate(date1);
					paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					baoxiaodan.setPaymentVouchers(paymentVoucher);
				} else {
					paymentVoucher.setPaymentStatus("已付款");
					paymentVoucher.setVoucherdate(date1);
					paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					baoxiaodan.setPaymentVouchers(paymentVoucher);
				}
				this.totalDao.update(paymentVoucher);
			}
		}
		return boo;
	}

	/*
	 * 查询管理报销单(生产)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.BaoXiaoDanServer#findBaoXiaoDan1(com.task.entity.
	 * fin.BaoxiaoDan, java.lang.String, java.lang.String, int, int,
	 * java.lang.String)
	 */
	@Override
	public Object[] findBaoXiaoDan1(BaoxiaoDan baoxiao, String startDate,
			String endDate, int parseInt, int pageSize, String tag) {

		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		String username = user.getName();
		String hql = "from BaoxiaoDan where baoxiaoren='" + username
				+ "' order by saveTime desc";
		if ("manger".equals(tag)) {
			hql = "from BaoxiaoDan where producestatus in('生产','其他','外委','外购') order by saveTime desc";
		} else {
			if (null == baoxiao) {
				baoxiao = new BaoxiaoDan();
			}
			baoxiao.setBaoxiaoren(username);
		}
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != baoxiao) {
			hql = totalDao.criteriaQueries(baoxiao, "baoxiaoDate", between, "")
					+ " and producestatus in('生产','其他') order by saveTime desc";
		}
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	/*
	 * 更新报销单(生产)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.BaoXiaoDanServer#updateBaoXiaoDan1(com.task.entity
	 * .fin.BaoxiaoDan, java.util.List, java.lang.String, java.lang.Float,
	 * java.lang.Float)
	 */
	@Override
	public boolean updateBaoXiaoDan1(BaoxiaoDan baoxiaodan,
			List<BaoxiaoDetail> listDetail, String money2, Float money1,
			Float oldmoney) {
		boolean bool = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BaoxiaoDan bxd = (BaoxiaoDan) totalDao.getObjectById(BaoxiaoDan.class,
				baoxiaodan.getId());// 获取数据库的实体类
		Users user = (Users) Util.getLoginUser();
		BeanUtils.copyProperties(baoxiaodan, bxd, new String[] {
				"paymentVouchers", "dept", "code", "baoxiaoren", "saveTime",
				"baoxiaoBarcode", "deptManager", "Deputymanager",
				"Genalmangager", "planMonth", "isPrint" });
		String status = baoxiaodan.getStatus();// 可以直接打印
		float totalMoney = 0f;// 总金额
		Set<BaoxiaoDetail> listDetailNew = new HashSet<BaoxiaoDetail>();
		for (int i = 0; i < listDetail.size(); i++) {
			BaoxiaoDetail baoxiaoDetail = listDetail.get(i);
			BaoxiaoDetail baoxiaoDetail2 = null;
			if (baoxiaoDetail.getId() != null) {
				baoxiaoDetail2 = (BaoxiaoDetail) this.totalDao.getObjectById(
						BaoxiaoDetail.class, baoxiaoDetail.getId());
			}
			if (baoxiaoDetail2 == null) {
				baoxiaoDetail.setBaoxiaoStyle("");
				baoxiaoDetail.setSaveTime(sdf.toString());
				baoxiaoDetail.setBaoxiaoDan(bxd);
				// baoxiaoDetail.setId(null);
				// try {
				// totalDao.save(baoxiaoDetail);
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				if (!"其他".equals(baoxiaodan.getProducestatus())
						&& baoxiaoDetail.getGoodsStoreId() != null) {
					GoodsStore goodsStore = (GoodsStore) this.totalDao
							.getObjectById(GoodsStore.class, baoxiaoDetail
									.getGoodsStoreId());
					goodsStore.setContrast_num(baoxiaoDetail
							.getGoodsStoreCount());
					if (goodsStore.getGoodsStoreCount() > baoxiaoDetail
							.getGoodsStoreCount()) {
						goodsStore.setBaoxiao_status("未报完");
					} else {
						goodsStore.setBaoxiao_status("已报销");
					}
					this.totalDao.update(goodsStore);
				}
				listDetailNew.add(baoxiaoDetail);
			} else {
				if (!"其他".equals(baoxiaodan.getProducestatus())
						&& baoxiaoDetail.getGoodsStoreId() != null) {
					GoodsStore goodsStore = (GoodsStore) this.totalDao
							.getObjectById(GoodsStore.class, baoxiaoDetail2
									.getGoodsStoreId());
					baoxiaoDetail2.setSaveTime(sdf.toString());
					baoxiaoDetail2.setGoodsStoreMarkId(baoxiaoDetail
							.getGoodsStoreMarkId());
					baoxiaoDetail2.setGoodsStoreLot(baoxiaoDetail
							.getGoodsStoreLot());
					baoxiaoDetail2.setGoodsStoreCount(baoxiaoDetail
							.getGoodsStoreCount());
					listDetailNew.add(baoxiaoDetail2);
					// 更新库存的已报数量
					goodsStore.setContrast_num(baoxiaoDetail
							.getGoodsStoreCount());
					if (goodsStore.getGoodsStoreCount() > baoxiaoDetail
							.getGoodsStoreCount()) {
						goodsStore.setBaoxiao_status("未报完");
					} else {
						goodsStore.setBaoxiao_status("已报销");
					}
					this.totalDao.update(goodsStore);
				}
			}
		}
		bxd.setTotalMoney((double) money1);
		bxd.setBaoxiaoDetail(listDetailNew);
		bool = totalDao.update(bxd);
		if (bool) {
			// 查到老的数据
			PaymentVoucher paymentVoucher2 = new PaymentVoucher();
			if (null != bxd.getPaymentVouchers()) {
				paymentVoucher2 = (PaymentVoucher) this.totalDao.getObjectById(
						PaymentVoucher.class, bxd.getPaymentVouchers().getId());
			}
			// PaymentVoucher paymentVoucher2 = (PaymentVoucher)
			// this.totalDao.getObjectById(PaymentVoucher.class,
			// bxd.getPaymentVouchers().getId());
			if ("归还借款".equals(baoxiaodan.getPayStyle())) {
				String date1 = Util.getDateTime("yyyy-MM-dd");
				String str[] = money2.split(",");
				Float pay_money = Float.parseFloat(str[0]);// 拿到借款金额
				Integer pay_id = Integer.parseInt(str[1]);// 拿到id
				PaymentVoucher paymentVoucher = (PaymentVoucher) this.totalDao
						.getObjectById(PaymentVoucher.class, pay_id);
				// 用老的借款id与新的借款id对比
				if (!bxd.getPaymentVouchers().getId().toString().equals(
						pay_id.toString())) {
					// 修改时不为同一条借款
					paymentVoucher2.setPrepaidMoney(paymentVoucher2
							.getPrepaidMoney()
							- paymentVoucher2.getPrepaidMoney());// 更新老的付款金额
					bxd.setPaymentVouchers(paymentVoucher2);
					this.totalDao.update(paymentVoucher2);// 更新新的付款金额

					Set<BaoxiaoDan> pvSet = paymentVoucher.getBaoxiaoDanSet();
					if (pvSet == null) {
						pvSet = new HashSet<BaoxiaoDan>();
					}
					Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
					Float VoucherMoney = paymentVoucher.getVoucherMoney();// 总金额
					Float newMoney = VoucherMoney - prepaidMoney1;// 剩余金额
					if (prepaidMoney1 == null) {
						prepaidMoney1 = 0F;
					}
					Float pay_money1 = pay_money - money1;
					if (pay_money1 > 0) {
						paymentVoucher.setPaymentStatus("未付清");
						paymentVoucher.setVoucherdate(date1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					} else {
						paymentVoucher.setPaymentStatus("已付款");
						paymentVoucher.setVoucherdate(date1);
						// paymentVoucher.setPrepaidMoney(prepaidMoney1+money1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1 + money1);
					}
					bxd.setPaymentVouchers(paymentVoucher);
					pvSet.add(bxd);
					paymentVoucher.setBaoxiaoDanSet(pvSet);
					this.totalDao.update(paymentVoucher);
				} else {
					// 修改借款时为同一条借款
					Set<BaoxiaoDan> pvSet = paymentVoucher.getBaoxiaoDanSet();
					if (pvSet == null) {
						pvSet = new HashSet<BaoxiaoDan>();
					}
					Float prepaidMoney1 = paymentVoucher.getPrepaidMoney();// 查出存在的已付金额
					Float VoucherMoney = paymentVoucher.getVoucherMoney();// 总金额
					Float newMoney = VoucherMoney - prepaidMoney1;// 剩余金额
					if (prepaidMoney1 == null) {
						prepaidMoney1 = 0F;
					}
					Float pay_money1 = pay_money - money1;
					if (pay_money1 > 0) {
						paymentVoucher.setPaymentStatus("未付清");
						paymentVoucher.setVoucherdate(date1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1
								- paymentVoucher2.getPrepaidMoney() + money1);
					} else {
						paymentVoucher.setPaymentStatus("已付款");
						paymentVoucher.setVoucherdate(date1);
						// paymentVoucher.setPrepaidMoney(prepaidMoney1+money1);
						paymentVoucher.setPrepaidMoney(prepaidMoney1
								- paymentVoucher2.getPrepaidMoney() + money1);
					}
					bxd.setPaymentVouchers(paymentVoucher);
					pvSet.add(bxd);
					paymentVoucher.setBaoxiaoDanSet(pvSet);
					try {
						this.totalDao.update(paymentVoucher);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			} else {
				// 还原老的金额
				if (null != bxd.getPaymentVouchers()) {
					PaymentVoucher paymentVoucher1 = (PaymentVoucher) this.totalDao
							.getObjectById(PaymentVoucher.class, bxd
									.getPaymentVouchers().getId());
					paymentVoucher1.setPrepaidMoney(paymentVoucher1
							.getPrepaidMoney()
							- paymentVoucher2.getPrepaidMoney());
					this.totalDao.update(paymentVoucher1);
				}

			}
		}
		return bool;
	}

	@Override
	public Object[] findBaoXiaoDan2(BaoxiaoDan baoxiaodan2, String startDate,
			String endDate, int parseInt, int pageSize, String tag) {
		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		String username = user.getName();
		String hql = "from BaoxiaoDan where baoxiaoren='" + username
				+ "' order by saveTime desc";
		if ("manger".equals(tag)) {
			hql = "from BaoxiaoDan order by saveTime desc";
		} else {
			if (null == baoxiaodan2) {
				baoxiaodan2 = new BaoxiaoDan();
			}
			baoxiaodan2.setBaoxiaoren(username);
		}
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != baoxiaodan2) {
			hql = totalDao.criteriaQueries(baoxiaodan2, "baoxiaoDate", between,
					"")
					+ "  order by saveTime desc";
		}
		hql += " and dealstatus='未处理'";
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	public void exportExcel2(BaoxiaoDan baoxiaodan, String startDate,
			String endDate, String tag) {
		if ("sum".equals(tag)) {// 汇总数据导出
			String hql = "select sum(totalMoney) as totalMoney,dept,currency from BaoxiaoDan where 1=1";
			String groupBy = "group by dept,currency";
			if (null != baoxiaodan) {
				if (!"".equals(baoxiaodan.getDept())
						&& null != baoxiaodan.getDept()
						&& null != baoxiaodan.getBaoxiaoClass()
						&& !"".equals(baoxiaodan.getBaoxiaoClass())) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept()
							+ "' and baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if (("".equals(baoxiaodan.getDept()) || null == baoxiaodan
						.getDept())
						&& (null != baoxiaodan.getBaoxiaoClass() && !""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where baoxiaoClass='"
							+ baoxiaodan.getBaoxiaoClass() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				} else if ((!"".equals(baoxiaodan.getDept()) && null != baoxiaodan
						.getDept())
						&& (null == baoxiaodan.getBaoxiaoClass() || ""
								.equals(baoxiaodan.getBaoxiaoClass()))) {
					hql = "select sum(totalMoney) as totalMoney,dept,currency,baoxiaoClass from BaoxiaoDan where dept='"
							+ baoxiaodan.getDept() + "'";
					groupBy = "group by dept,currency,baoxiaoClass";
				}
				if (null != startDate && !"".equals(startDate)
						&& null != endDate && !"".equals(endDate)) {
					hql += " and  producestatus!='' and baoxiaoDate between '"
							+ startDate + "' and '" + endDate + "'";
				}
			}
			hql += groupBy;
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response
						.setHeader("Content-disposition",
								"attachment; filename="
										+ new String("baoxiaoHuizong"
												.getBytes("GB2312"), "8859_1")
										+ ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("财务报销数据汇总", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "财务报销数据汇总", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 7, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(2, 1, "类别", wc));
				ws.addCell(new jxl.write.Label(3, 1, "合计金额", wc));
				ws.addCell(new jxl.write.Label(4, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(5, 1, "汇总开始时间", wc));
				ws.addCell(new jxl.write.Label(6, 1, "汇总截止时间", wc));
				for (int i = 0; i < list.size(); i++) {
					Object[] o = (Object[]) list.get(i);
					String dept = (String) o[1];
					String bizhong = (String) o[2];
					double count = (Double) o[0];
					float cou = (float) count;
					float f = Float.parseFloat(String.valueOf(cou));
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, dept, wc));
					if (null != baoxiaodan.getBaoxiaoClass()
							&& !"".equals(baoxiaodan.getBaoxiaoClass())) {// 判断报销科目是否为空
						ws.addCell(new Label(2, i + 2, baoxiaodan
								.getBaoxiaoClass(), wc));
					} else {
						ws.addCell(new Label(2, i + 2, "全部", wc));
					}
					ws.addCell(new jxl.write.Number(3, i + 2, f, wc));

					ws.addCell(new Label(4, i + 2, bizhong, wc));
					ws.addCell(new Label(5, i + 2, startDate, wc));
					ws.addCell(new Label(6, i + 2, endDate, wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} else if ("bxd".equals(tag)) {// 报销单数据导出
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String username = user.getName();
			String hql = "from BaoxiaoDan where 1=1 and producestatus!='' ";
			if (null != startDate && null != endDate && !"".equals(endDate)
					&& !"".equals(startDate)) {
				hql += " and baoxiaoDate between '" + startDate + "' and '"
						+ endDate + "' ";
			}
			if (null != baoxiaodan) {
				if (null != baoxiaodan.getShoukuanRen()
						&& !"".equals(baoxiaodan.getShoukuanRen())) {
					hql += " and shoukuanRen='" + baoxiaodan.getShoukuanRen()
							+ "'";
				}
				if (null != baoxiaodan.getPayStyle()
						&& !"".equals(baoxiaodan.getPayStyle())) {
					hql += " and payStyle='" + baoxiaodan.getPayStyle() + "'";
				}
				if (null != baoxiaodan.getBaoxiaoren()
						&& !"".equals(baoxiaodan.getBaoxiaoren())) {
					hql += " and baoxiaoren='" + baoxiaodan.getBaoxiaoren()
							+ "'";
				}
				if (null != baoxiaodan.getBaoxiaoBarcode()
						&& !"".equals(baoxiaodan.getBaoxiaoBarcode())) {
					hql += " and baoxiaoBarcode='"
							+ baoxiaodan.getBaoxiaoBarcode() + "'";
				}
				if (null != baoxiaodan.getDept()
						&& !"".equals(baoxiaodan.getDept())) {
					hql += " dept='" + baoxiaodan.getDept() + "'";
				}
			}
			hql += " order by saveTime desc";
			List list = totalDao.find(hql);
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				OutputStream os = response.getOutputStream();
				response.reset();
				response.setHeader("Content-disposition",
						"attachment; filename="
								+ new String("baoxiaodan".getBytes("GB2312"),
										"8859_1") + ".xls");
				response.setContentType("application/msexcel");
				WritableWorkbook wwb = Workbook.createWorkbook(os);
				WritableSheet ws = wwb.createSheet("报销单信息", 0);
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
				jxl.write.Label label0 = new Label(0, 0, "报销单信息", wcf);
				ws.addCell(label0);
				ws.mergeCells(0, 0, 9, 0);

				wf = new WritableFont(WritableFont.ARIAL, 12,
						WritableFont.NO_BOLD, false,
						UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				WritableCellFormat wc = new WritableCellFormat(wf);
				wc.setAlignment(Alignment.CENTRE);
				ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
				ws.addCell(new jxl.write.Label(1, 1, "收款单位(个人)", wc));
				ws.addCell(new jxl.write.Label(2, 1, "部门", wc));
				ws.addCell(new jxl.write.Label(3, 1, "报销人", wc));
				ws.addCell(new jxl.write.Label(4, 1, "付款方式", wc));
				ws.addCell(new jxl.write.Label(5, 1, "报销日期", wc));
				ws.addCell(new jxl.write.Label(6, 1, "报销金额", wc));
				ws.addCell(new jxl.write.Label(7, 1, "币种", wc));
				ws.addCell(new jxl.write.Label(8, 1, "报销编号", wc));
				for (int i = 0; i < list.size(); i++) {
					BaoxiaoDan bxd = (BaoxiaoDan) list.get(i);
					/*
					 * Object[] o=(Object[])list.get(i); String
					 * dept=(String)o[1]; double count=(Double)o[0]; float
					 * cou=(float)count; float f =
					 * Float.parseFloat(String.valueOf(cou));
					 */
					ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
					ws.addCell(new Label(1, i + 2, bxd.getShoukuanRen(), wc));
					ws.addCell(new Label(2, i + 2, bxd.getDept(), wc));
					ws.addCell(new Label(3, i + 2, bxd.getBaoxiaoren(), wc));
					ws.addCell(new Label(4, i + 2, bxd.getPayStyle(), wc));
					ws.addCell(new Label(5, i + 2, bxd.getBaoxiaoDate(), wc));
					ws.addCell(new jxl.write.Number(6, i + 2, bxd
							.getTotalMoney(), wc));
					ws.addCell(new Label(7, i + 2, bxd.getCurrency(), wc));
					ws
							.addCell(new Label(8, i + 2, bxd
									.getBaoxiaoBarcode(), wc));
				}
				wwb.write();
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

		} else if ("detail".equals(tag)) {// 明细数据导出

		}
	}

	@Override
	public void updateXiaoDan2(Integer id) {
		// TODO Auto-generated method stub
		BaoxiaoDan baoxiaoDan = (BaoxiaoDan) this.totalDao.getObjectById(
				BaoxiaoDan.class, id);
		baoxiaoDan.setDealstatus("已处理");
		this.totalDao.update(baoxiaoDan);
	}

	@Override
	public List findjianhao2(String tag) {
		// TODO Auto-generated method stub
		String hql = "select distinct(goodsStoreMarkId) from GoodsStore where status='入库' and goodsStoreMarkId is not null "
				+ "and (baoxiao_status ='未报完' or baoxiao_status is null) order by goodsStoreMarkId";// and
		return totalDao.query(hql);
	}

	@Override
	public List findWaiweiGys(String tag) {
		// TODO Auto-generated method stub
		// return
		// totalDao.query("select gysId,gysName from WaigouPlan where type ='外委' and (hasjk is null or hasjk !='是') group by gysId,gysName");
		return totalDao
				.query("select gysId,gysName from WaigouPlan where type ='外委' and (yfMoney is null or yfMoney<money) group by gysId,gysName");
	}

	@Override
	public List findpiWaiwei(String id) {
		// TODO Auto-generated method stub
		// return
		// totalDao.query("select id,number,markId,waigouOrder.planNumber from WaigouPlan  where type ='外委' and (hasjk is null or hasjk !='是') and gysId=?",
		// Integer.parseInt(id));
		return totalDao
				.query(
						"select id,number,markId,waigouOrder.planNumber,money,yfMoney from WaigouPlan  where type ='外委' and (yfMoney is null or yfMoney<money)  and gysId=? order by markId",
						Integer.parseInt(id));
	}

	@Override
	public List findpiWaigou(String id) {
		// TODO Auto-generated method stub
		// return
		// totalDao.query("select id,number,markId,waigouOrder.planNumber from WaigouPlan  where type ='外购' and (hasjk is null or hasjk !='是') and gysId=?",
		// Integer.parseInt(id));
		return totalDao
				.query(
						"select id,number,markId,waigouOrder.planNumber,money,yfMoney from WaigouPlan  where type ='外购' and (yfMoney is null or yfMoney<money) and gysId=? order by markId",
						Integer.parseInt(id));
	}

	@Override
	public List findWaigouGys(String tag) {
		// TODO Auto-generated method stub
		// return
		// totalDao.query("select gysId,gysName from WaigouPlan where type ='外购' and (hasjk is null or hasjk !='是') group by gysId,gysName");
		return totalDao
				.query("select gysId,gysName from WaigouPlan where type ='外购' and (yfMoney is null or yfMoney<money) group by gysId,gysName");
	}

	@Override
	public List findfzyBusiness(String tag) {
		// TODO Auto-generated method stub
		String ss = " ";
		Users s = Util.getLoginUser();
		if (s != null)
			ss = s.getDept();
		return totalDao
				.query(
						"select distinct(type) from PayableType where id in (select p.id from PayableType p join p.usersList u where u.id =?)",
						Util.getLoginUser().getId());
	}

	@Override
	public List findfzykm(String tag) {
		// TODO Auto-generated method stub
		return totalDao
				.query(
						"select id,shoukuandanwei,zhangqiStartDate,yingfukuanJIne,realfukuanJIne,zhaiyao from NonCorePayable where subjectItem=? and status = '同意' and yingfukuanJIne-realfukuanJIne > 0",
						tag);
	}

	@Override
	public Map<Integer, Object> findAll(Payee payee, int pageNo, int pageSize) {
		if (payee == null) {
			payee = new Payee();
		}
		String hql = totalDao.criteriaQueries(payee, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	public Payee findByName(String name) {
		String hql = "from Payee where name = '" + name + "'";
		Payee payee = (Payee) totalDao.getObjectByQuery(hql);
		return payee;
	}

	public Users findUsers(Payee payee) {
		return (Users) totalDao.getObjectByCondition("from Users where code ='"
				+ payee.getCode() + "' and name = '" + payee.getName() + "'",
				null);
	}

	@Override
	public String save(Payee payee) {
		String Code = (String) totalDao.getObjectByCondition(
				"select code from Users where name= ?", payee.getName());
		if ("".equals(Code) || Code == null) {
			payee.setCode("非内部人员");
		} else {
			payee.setCode(Code);
		}
		String hql1 = "select num from Payee order by id desc LIMIT 1";
		String num = (String) totalDao.getObjectByCondition(hql1, null);
		if ("".equals(num) || num == null) {
			num = "PH000001";
		} else {
			String num1 = num.substring(num.length() - 6);
			Integer num2 = Integer.parseInt(num1);
			num2++;
			num = "";
			if (num2 >= 0 && num2 < 10) {
				num = "00000" + num2; // 1~9之间
			} else if (num2 < 100) {
				num = "0000" + num2; // 10~99之间
			} else if (num2 < 1000) {
				num = "000" + num2; // 100~999之间
			} else if (num2 < 10000) {
				num = "00" + num2; // 1000~9999之间
			} else if (num2 < 10000) {
				num = "0" + num2;// 10000~99999之间
			} else if (num2 < 10000) {
				num = String.valueOf(num2);// 100000~999999之间
			}
			num = "PH" + num;
		}
		payee.setNum(num);
		if (totalDao.save(payee)) {
			String processName = "收款账户申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Payee.class, payee.getId(), "epStattus", "id",
						"BaoXiaoDanAction!findoneById.action?id="
								+ payee.getId(), "收款账户申请，请您审批", true);
				if (epId != null && epId > 0) {
					payee.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						payee.setEpStattus("同意");
					} else {
						payee.setEpStattus("未审批");
					}
				}
				return "true";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "false";
	}

	public String importFile(File importFile) {
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
			FileCopyUtils.copy(importFile, file);
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
					if (cells[2].getContents() != null
							&& cells[2].getContents() != "") {
						String a = cells[1].getContents();//开户行
						String b = cells[2].getContents();//账号
						String c = cells[3].getContents();//名称
						String d = cells[4].getContents();//联系方式
						Payee payee = new Payee();
						if(a!=null){payee.setBank(a);}
						if(b!=null){payee.setaNumber(b);}
						if(findByName(c)!=null){
							strList.add(i+1);
							continue;
						}
						String hql1="select num from Payee order by id desc LIMIT 1";
						String num = (String)totalDao.getObjectByCondition(hql1, null);
						if("".equals(num)||num==null){
							num="PH000001";
						}else{
							String num1=num.substring(num.length()-6);
							Integer num2 = Integer.parseInt(num1);
							num2++;
							num="";
							if (num2 >=0 && num2 < 10)
							{
								num = "00000" + num2; // 1~9之间
							}
							else if (num2<100) {
								num = "0000" + num2; // 10~99之间
							} else if (num2< 1000) {
								num = "000" + num2; // 100~999之间
							} else if (num2 < 10000) {
								num = "00" + num2;  // 1000~9999之间
							}
							else if (num2 < 10000) {
								num = "0" + num2;// 10000~99999之间
							}
							else if (num2 < 10000) {
								num = String.valueOf(num2);// 100000~999999之间
							}
								num="PH"+num;
							}
						payee.setNum(num);
						payee.setName(c);
						String Code = (String)totalDao.getObjectByCondition("select code from Users where name= ?",c);
						if("".equals(Code)||Code==null){
							payee.setCode("非内部人员");
						}else{
							payee.setCode(Code);
						}
						if(d!=null){payee.setCmobile(d);}
						payee.setEpStattus("同意");
						totalDao.save(payee);
					}

				}
				is.close();// 关闭流
				wk.close();// 关闭工作薄
				if (strList != null && strList.size() > 0) {
					Integer drcount = exclecolums - 2 - strList.size();
					msg = "已成功导入" + drcount + "个，失败" + strList.size()
							+ "个，失败的行数为：";
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
			e.printStackTrace();

		}
		return msg;
	}

	@Override
	public Payee findOneById(Integer id) {
		return (Payee) totalDao.get(Payee.class, id);
	}

	@Override
	public List findkmTree() {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		if(user==null){
			return null;
		}
		return totalDao.query("from SubBudgetRate where id in(select sid from SubBudgetRateUser where uid=?)",user.getId());
	}
}
