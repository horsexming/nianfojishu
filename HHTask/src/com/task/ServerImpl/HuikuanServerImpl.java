package com.task.ServerImpl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.HuikuanServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Becoming;
import com.task.entity.Careertrack;
import com.task.entity.InsuranceGold;
import com.task.entity.OaMessageAlerm;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.Sell;
import com.task.entity.TaHkBackMoney;
import com.task.entity.TaHkHkInvoice;
import com.task.entity.TaHkHuikuan;
import com.task.entity.TaHkPartBackMoney;
import com.task.entity.TaHkSellSta;
import com.task.entity.TaHkShoppingCard;
import com.task.entity.Users;
import com.task.entity.WageStandard;
import com.task.entity.caigou.MonthlySummary;
import com.task.entity.caiwu.core.SupplierCorePayable;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedPriceCost;
import com.task.entity.sop.Procard;
import com.task.entity.system.CircuitRun;
import com.task.entity.unpasskp.ProductUnPassKp;
import com.task.util.Util;

public class HuikuanServerImpl implements HuikuanServer {

	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public Object[] findshowNoteDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateHk(TaHkHuikuan hk) {
		// TODO Auto-generated method stub
		if (totalDao.update(hk)) {
			return true;
		}
		return false;
	}

	public Object getObj(Integer id) {
		return totalDao.getObjectById(TaHkHuikuan.class, id);
	}

	public TaHkSellSta gethkSta(Integer id) {
		if (id > 0) {
			return (TaHkSellSta) totalDao.getObjectById(TaHkSellSta.class, id);
		}
		return null;
	}

	/**
	 * 修改明细信
	 */
	public boolean updateHKSta(TaHkSellSta taHkSellSta) {
		TaHkSellSta sta = (TaHkSellSta) totalDao.getObjectById(
				TaHkSellSta.class, taHkSellSta.getId());
		sta.setHkSellCumpanyName(taHkSellSta.getHkSellCumpanyName());
		sta.setHkSellMarkId(taHkSellSta.getHkSellMarkId());
		sta.setHkSellOutOrderId(taHkSellSta.getHkSellOutOrderId());
		sta.setHkSellCount(taHkSellSta.getHkSellCount());
		sta.setHkSellSendId(taHkSellSta.getHkSellSendId());
		sta.setHkSellMore(taHkSellSta.getHkSellMore());
		TaHkHuikuan taHK = sta.getTaHkHuikuan();
		if (taHK.getHkStatus().contains("打回")) {
			taHK.setHkStatus("开票申请");
			taHK.setHkPreNotPath("LD");
			taHK.setHkRelNotPath("FD");
			totalDao.update(taHK);
		}
		return totalDao.update(sta);
	}

	public Object[] queryNoteSell(Sell sell, String startDate, String endDate,
			int cpage, int pageSize) {
		String hql = "from Sell where sellWarehouse='成品库'";
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != sell) {
			sell.setSellWarehouse("成品库");
			hql = totalDao.criteriaQueries(sell, "sellDate", between, "");
		}
		hql += " and sellSendnum is null and sellDate>'2012-01-01' order by sellDate desc";
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
		/*
		 * String bteween[]=new String[2]; if(null!=startDate && null!=endDate
		 * && !"".equals(startDate) && !"".equals(endDate)){
		 * bteween[0]="'"+startDate+"'"; bteween[1]="'"+endDate+"'"; } String
		 * hql=
		 * "from Sell where sellWarehouse='成品库' and sellSendnum is null and sellDate>'2012-01-01' order by sellDate desc"
		 * ; if(null!=sell){ sell.setSellWarehouse("成品库");
		 * hql=totalDao.criteriaQueries
		 * (sell,"sellDate",bteween," sellSendnum is null and sellDate>'2012-01-01'"
		 * )+" order by sellDate desc"; } List list=totalDao.findAllByPage(hql,
		 * pageNo, pageSize); return list;
		 */
	}

	@Override
	public Integer findCou(Sell sell, String startDate, String endDate) {
		// TODO Auto-generated method stub

		String bteween[] = new String[2];
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = "'" + startDate + "'";
			bteween[1] = "'" + endDate + "'";
		}
		String hql = "from Sell where sellWarehouse='成品库' and sellSendnum is null";
		if (null != sell) {
			// sell.setSellWarehouse("成品库");
			hql = totalDao.criteriaQueries(sell, "sellDate", bteween, "")
					+ " and sellWarehouse='成品库' and sellSendnum is null";
		}

		return totalDao.getCount(hql);
	}

	// 获得所有选择出库记录处理
	public List selectSellResult(Integer[] sellId) {
		// List <Sell> listSell = new ArrayList<Sell>();
		// List<Object[]> list=new ArrayList();
		// Integer[] t={12,33,44,60218,60219,60215};
		// System.out.println("=========="+sellId.length+"==========="+sellId[0]);
		if (null != sellId) {

			// Map<String,Object> map = new HashMap<String,Object>();
			// map.put("ids", sellId);

			/*
			 * String sellidString=""; for (int i = 0,len=sellId.length; i <len
			 * ; i++) { if(i==len-1){ sellidString+=sellId[i].toString(); }else
			 * sellidString+=sellId[i].toString()+",";
			 * 
			 * }
			 */
			String hql = "select sum(sellCount),sellMarkId,sellCompanyName from Sell where sellId in(:test) group by sellMarkId,sellCompanyName";

			Query query = totalDao.createQuery(hql);
			query.setParameterList("test", sellId);
			return query.list();

			// list=(List<Object[]>) totalDao.findByHql(hql, map, 1, 20);
			// System.out.println("==================list大小:"+li.size());
		}

		return null;
	}

	// 保存送货单号
	public boolean saveSendNum(String sendNum, List<TaHkSellSta> hkset) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) ActionContext.getContext().getSession().get(
				"Users");
		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null != hkset && hkset.size() > 0) {
			for (TaHkSellSta sellSta : hkset) {
				sellSta.setHkSellSendId(sendNum);
				sellSta.setHksellUsername(user.getName());
				sellSta.setHksellTime(sdfTime.format(new Date()));
				// sellSta.setId(null);
				totalDao.save(sellSta);
			}
			Integer[] sellId = (Integer[]) ActionContext.getContext()
					.getSession().get("sellId");
			for (int i = 0; i < sellId.length; i++) {
				Sell sell = (Sell) totalDao
						.getObjectById(Sell.class, sellId[i]);
				sell.setSellSendnum(sendNum);
				totalDao.update(sell);
			}
			return true;
		}
		return false;
	}

	// 删除送货单记录
	public boolean deleteSellSta(Integer id) {
		TaHkSellSta hkSellSta = (TaHkSellSta) totalDao.getObjectById(
				TaHkSellSta.class, id);
		String hql1 = "from Sell where sellMarkId=? and sellSendnum=?";
		List list = this.totalDao.query(hql1, hkSellSta.getHkSellMarkId(),
				hkSellSta.getHkSellSendId());
		for (int i = 0; i < list.size(); i++) {
			Sell sell = (Sell) list.get(i);
			sell.setSellSendnum(null);
			this.totalDao.update(sell);
		}
		return totalDao.delete(hkSellSta);
	}

	// 查询送货单号记录
	public List<TaHkSellSta> querySta(TaHkSellSta sta, String startDate,
			String endDate, int pageNo, int pageSize) {
		String bteween[] = new String[2];
		String hql = totalDao.criteriaQueries(sta, "", bteween,
				" hkSellNum is NULL");
		if (null == sta) {
			hql = "from TaHkSellSta where hkSellNum is NULL";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	public Integer findStaCou(TaHkSellSta sta) {
		String bteween[] = new String[2];
		String hql = totalDao.criteriaQueries(sta, "", bteween,
				" hkSellNum is NULL");
		if (null == sta) {
			hql = "from TaHkSellSta where hkSellNum is NULL";
		}

		return totalDao.getCount(hql);
	}

	// 根据送货单号获得送货明细列表
	public List<TaHkSellSta> selectStaResult(Integer[] sellId) {
		if (null != sellId) {
			String hql = "from TaHkSellSta where id in(:test) and hkSellNum is null";
			Query query = totalDao.createQuery(hql);
			query.setParameterList("test", sellId);
			return query.list();
		}
		return null;
	}

	public String findNoteId() {// 系统生成开票通知单编号
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); String
		 * curMon=sdf.format(new Date());
		 */
		String curMon = totalDao.getDateTime("yyyyMM");
		String hql = "select max(hkNum) from TaHkHuikuan where hkNum like '%"
				+ curMon + "%'";
		String huNum = "";
		if (null != totalDao.query(hql) && null != totalDao.query(hql).get(0)
				&& totalDao.query(hql).size() > 0) {
			String maxNum = (String) totalDao.query(hql).get(0);
			int newNum = Integer.parseInt(maxNum.substring(4)) + 1;
			huNum = "shLD" + String.valueOf(newNum);
		} else {
			huNum = "shLD" + curMon + "001";
		}
		return huNum;
	}

	@Override
	public boolean saveHk(TaHkHuikuan hk, List<TaHkSellSta> hkset,
			File[] attachment, String[] attachmentFileName, String tag) {

		if (null != hk) {
			// 上传附件
			// System.out.println("===============tag:"+tag);
			if ("manual".equals(tag)) {
				// System.out.println("=================num:"+findNoteId());
				hk.setHkNum(findNoteId());
			}
			String attachmentName = "";
			if (attachment != null && attachment.length > 0) {
				for (int i = 0; i < attachment.length; i++) {
					String fileName = hk.getHkNum().replaceAll("/", "").trim()
							+ new SimpleDateFormat("yyyyMMddHHmmss")
									.format(new Date())
							+ (attachmentFileName[i]
									.substring(attachmentFileName[i]
											.lastIndexOf(".")));
					if (i > 0) {
						attachmentName += "&" + fileName;
					} else {
						attachmentName += fileName;
					}
					attachmentName.trim();
					// 上传到服务器
					String fileRealPath = ServletActionContext
							.getServletContext().getRealPath("/upload/huikuan")
							+ "/" + fileName;
					File file = new File(fileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], file);
					} catch (Exception e) {
						return false;
					}

					// 备份到项目
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/huikuan"
							+ "/" + fileName;
					File beiFenFile = new File(beiFenfileRealPath);
					try {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			// 为属性赋值
			hk.setHkApplyDate(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			hk.setHkApplier(user.getName());
			// 开票钱审核流程
			hk.setHkPreNotPath("LD");
			hk.setHkRelNotPath("FD");
			// 开票后审核流程
			hk.setHkInvoPrePath("FD");
			hk.setHkInvoRelPath("MD");
			// 汇款追踪审核流程
			hk.setHkBackAuditA("MD");
			hk.setHkBackAuditB("FD");
			hk.setHkStatus("开票申请");
			hk.setHkNoticeFile(attachmentName);
			hk.setHkTrackRate(0f);
			hk.setHkZhuikuanren(hk.getHkClientName());// 设置追款人=客户负责人
			// 添加关联的明细表
			Set taHkSellStas = new HashSet();
			boolean bool = totalDao.save(hk);
			if (bool) {
				try {
					String processName = "开票审批";
					Integer epId = CircuitRunServerImpl.createProcess(
							processName, TaHkHuikuan.class, hk.getId(),
							"hkStatus", "id", hk.getHkClientComp() + " 的 "
									+ hk.getHkClientName() + "开票申请 ,请您审核!",
							true, "可开票");
					// Integer epId = CircuitRunServerImpl.createProcess(192,
					// TaHkHuikuan.class, hk.getId(), "hkStatus", "id", hk
					// .getHkClientComp()
					// + " 的 "
					// + hk.getHkClientName()
					// + "开票申请 ,请您审核!", true, "可开票");
					if (epId != null && epId > 0) {
						hk.setEpId(epId);
						totalDao.update(hk);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int i = 0; i < hkset.size(); i++) {
				TaHkSellSta hkSell = hkset.get(i);
				hkSell.setTaHkHuikuan(hk);
				hkSell.setHkSellNum(hk.getHkNum());
				taHkSellStas.add(hkSell);

				// 修改出库状态
				if (!"manual".equals(tag)) {
					totalDao.update(hkSell);
					if ("shopp".equals(tag)) {
						// 处理任务栏内内容
						// for(TaHkSellSta staId:hkset){
						String hqlShopp = "from TaHkShoppingCard where haSellSta.id="
								+ hkSell.getId();
						TaHkShoppingCard shop = (TaHkShoppingCard) (totalDao
								.query(hqlShopp).get(0));
						// System.out.println("==========="+shop.getId());
						totalDao.delete(shop);
						// }
					}
					String hqlSell = "from Sell where sellSendnum='"
							+ hkSell.getHkSellSendId()
							+ "' and sellWarehouse='成品库'";
					List<Sell> li = totalDao.query(hqlSell);
					for (Sell sell : li) {
						sell.setSellHkId(hk.getHkNum());
						totalDao.update(sell);
					}
				} else {
					totalDao.save(hkSell);
				}
			}

			return bool;
		}
		return false;
	}

	public Integer findNoteExamCou(TaHkHuikuan hk, TaHkSellSta tahkSellSta,
			String startDate, String endDate, String ifTag) {
		String bteween[] = new String[2];
		String btDate = "hkAppPayDate";
		String oth = "";
		Set<TaHkSellSta> taset = new HashSet<TaHkSellSta>();
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = startDate;
			bteween[1] = endDate;
		}
		if (null == hk) {
			hk = new TaHkHuikuan();
		}
		if (null == tahkSellSta) {
			hk.setTaHkSellStas(null);
		} else {
			taset.add(tahkSellSta);
			hk.setTaHkSellStas(taset);
			// tahkSellSta.setTaHkHuikuan(null);
		}
		hk.setTaHkHkInvoices(null);
		if ("noteLD".equals(ifTag)) {// 物流审批
			hk.setHkPreNotPath("LD");
			hk.setHkRelNotPath("FD");
			hk.setHkStatus("开票申请");
			oth = " hkPreNotPath='LD' and hkRelNotPath='FD'";
		} else if ("noteFD".equals(ifTag)) {// 财务审批
			hk.setHkPreNotPath("OKLD");
			hk.setHkRelNotPath("FD");
			hk.setHkStatus("开票申请");
			oth = " hkPreNotPath='OKLD' and hkRelNotPath='FD'";
		} else if ("FDInvoice".equals(ifTag)) { // 财务开票
			hk.setHkStatus("可开票");
		} else if ("backMoney".equals(ifTag)) {// 财务回款登记
			// hk.setHkStatus("回款");
			oth = " hkStatus='回款中' ";
		} else if ("zkquery".equals(ifTag)) {// 追款查看
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			hk.setHkZhuikuanren(user.getName());
			// oth=" hkZhuikuanren='"+user.getName()+"'";
		} else if ("retuMD".equals(ifTag)) {// 回款市场审批
			// hk.setHkBackAuditA("MD");
			// hk.setHkBackAuditB("OKFD");
			oth = " hkStatus='回款中' ";
			// hk.setHkStatus("回款");
			// oth=" and hkBackAuditA='MD' and hkBackAuditB='OKFD'";
		} else if ("invoHistory".equals(ifTag)) {// 开票记录
			// hk.setHkTrackRate(0.0f);
			oth = "hkStatus in('回款中','回款完成') and hkBillTime is not null";
		} else if ("manager".equals(ifTag)) {// 总经理查看结果
			oth = "hkStatus <>'回款中' and hkStatus <>'回款完成' order by hkStatus asc ";
		} else {

		}
		String hql = totalDao.criteriaQueries(hk, btDate, bteween, "");
		String dic = "hkSellNum";
		String hqlChild = totalDao.queryIfhql(tahkSellSta, dic);
		String allHql = hql;
		if (null != tahkSellSta) {
			if (hql.contains("where")) {
				allHql += " and  id in (" + hqlChild + ")";
			} else {
				allHql += " where  id in (" + hqlChild + ")";
			}
		}
		if ("manager".equals(ifTag) && !"".equals(oth)) {
			if (allHql.contains("where")) {
				allHql += " and " + oth;
			} else {
				allHql += " where " + oth;
			}
		} else {
			if (!"".equals(oth)) {
				if (allHql.contains("where")) {
					allHql += " and " + oth;
				} else {
					allHql += " where " + oth;
				}
			}
			// allHql+=oth;
		}

		return totalDao.getCount(allHql);
	}

	@Override
	public List addStaResult(TaHkHuikuan taHk) {
		String hql = "from TaHkSellSta where taHkHuikuan.id=" + taHk.getId();
		return totalDao.query(hql);
	}

	// 查询报警条数
	public List updateselectAlert() {
		// String
		// hql="from TaHkHuikuan where hkTrackerTotMoney <> hkBillMoney and hkStatus in('回款') order by hkBillTime desc hkTrackRate asc";
		String hql = "from TaHkHuikuan where hkStatus in('回款中') order by hkBillTime desc hkTrackRate asc";
		return updatefindCountdown(totalDao.query(hql));
	}

	// 查询完成记录
	public List selectComp() {
		String hql = "from TaHkHuikuan where hkTrackerTotMoney = hkBillMoney or hkStatus in('回款完成') order by hkBillTime desc hkTrackRate asc";
		return totalDao.query(hql);
	}

	public List findNoteExam(TaHkHuikuan hk, TaHkSellSta tahkSellSta,
			String startDate, String endDate, int pageNo, int pageSize,
			String ifTag) {// 审批和查询
		String bteween[] = new String[2];
		String btDate = "hkAppPayDate";
		String oth = "";
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = startDate;
			bteween[1] = endDate;
		}
		if (null == hk) {
			hk = new TaHkHuikuan();
		}
		if (null == tahkSellSta) {
			hk.setTaHkSellStas(null);
		} else {
			hk.setTaHkSellStas(null);
			tahkSellSta.setTaHkHuikuan(null);
		}
		hk.setTaHkHkInvoices(null);
		if ("noteLD".equals(ifTag)) {// 物流审批
			hk.setHkPreNotPath("LD");
			hk.setHkRelNotPath("FD");
			hk.setHkStatus("开票申请");
			oth = " hkPreNotPath='LD' and hkRelNotPath='FD'";
		} else if ("noteFD".equals(ifTag)) {// 财务审批
			hk.setHkPreNotPath("OKLD");
			hk.setHkRelNotPath("FD");
			hk.setHkStatus("开票申请");
			oth = " hkPreNotPath='OKLD' and hkRelNotPath='FD'";
		} else if ("FDInvoice".equals(ifTag)) { // 财务开票
			hk.setHkStatus("可开票");
		}/*
		 * else if("invoFD".equals(ifTag)){//开票财务审批 hk.setHkInvoPrePath("FD");
		 * hk.setHkStatus("回款审批"); oth=" and hkInvoPrePath='FD'"; }else
		 * if("invoMD".equals(ifTag)){//开票市场审批 hk.setHkInvoPrePath("OKFD");
		 * hk.setHkInvoRelPath("MD"); hk.setHkStatus("回款审批");
		 * oth=" and hkInvoPrePath='OKFD' and hkInvoRelPath='MD'"; }else
		 * if("backMoney".equals(ifTag)){//财务回款登记 hk.setHkStatus("追款审批");
		 * //oth=" and hkBillMoney<>hkTrackerTotMoney "; }else
		 * if("retuFD".equals(ifTag)){//回款财务审批 hk.setHkBackAuditB("FD");
		 * hk.setHkStatus("追款审批"); oth=" and hkBackAuditB='FD'"; }
		 */
		else if ("backMoney".equals(ifTag)) {// 财务回款登记
			// hk.setHkStatus("回款");
			oth = " hkStatus='回款中' ";
			// oth=" and hkBillMoney<>hkTrackerTotMoney ";
		} else if ("retuMD".equals(ifTag)) {// 回款市场审批
			// hk.setHkBackAuditA("MD");
			// hk.setHkBackAuditB("OKFD");
			oth = " hkStatus='回款中' ";
			// hk.setHkStatus("回款");
			// oth=" and hkBackAuditA='MD' and hkBackAuditB='OKFD'";
		} else if ("invoHistory".equals(ifTag)) {// 开票记录
			// hk.setHkTrackRate(0f);
			hk.setHkStatus(null);
			oth = "hkStatus in('回款中','回款完成','开票申请') and hkBillTime is not null";
		} else if ("zkquery".equals(ifTag)) {// 追款查看
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			hk.setHkZhuikuanren(user.getName());
			oth = "hkStatus <>'开票申请' order by hkStatus asc";
		} else if ("manager".equals(ifTag)) {// 总经理查看结果
			oth = "hkStatus <>'回款中' order by hkStatus asc ";
		} else if ("huikuan".equals(ifTag)) {// 总经理查看结果
			oth = " hkTrackerTotMoney>0";
		} else {

		}
		String hql = totalDao.criteriaQueries(hk, btDate, bteween, "");
		// String hql=totalDao.criteriaQueries(hk,null,null);
		String dic = "hkSellNum";
		String str = "select  taHkHuikuan ";
		String hqlChild = totalDao.criteriaQueries(tahkSellSta, null);
		hqlChild = str + hqlChild;
		String allHql = hql;
		if (null != tahkSellSta) {
			if (hql.contains("where")) {
				allHql += " and  id in (" + hqlChild + ")";
			} else {
				allHql += " where  id in (" + hqlChild + ")";
			}
		}
		if ("manager".equals(ifTag) && !"".equals(oth)) {
			if (allHql.contains("where")) {
				allHql += " and " + oth;
			} else {
				allHql += " where " + oth;
			}
		} else {
			if (!"".equals(oth)) {
				if (allHql.contains("where")) {
					allHql += " and " + oth;
				} else {
					allHql += " where " + oth;
				}
			}
			// allHql+=oth;
		}

		// System.out.println("输出语句：==="+allHql);
		// +"  in  (:isc)"+oth;
		allHql += "  order by id desc";
		List list = totalDao.findAllByPage(allHql, pageNo, pageSize);
		return list;
	}

	// 审批意见
	public String updateIders(Integer id, String tag, String idea) {
		TaHkHuikuan tahk = (TaHkHuikuan) totalDao.getObjectById(
				TaHkHuikuan.class, id);
		System.out.println("======================================="
				+ tahk.getHkClientComp());
		String retutag = null;
		if ("NO".equals(idea)) {
			tahk.setHkStatus(tahk.getHkStatus() + "打回");
		}
		if ("noteLD".equals(tag)) {
			tahk.setHkPreNotPath(idea + tahk.getHkPreNotPath());
			retutag = "notExam";
		} else if ("noteFD".equals(tag)) {
			tahk.setHkRelNotPath(idea + tahk.getHkRelNotPath());
			if ("OK".equals(idea)) {
				tahk.setHkStatus("可开票");
				tahk.setHkNotOverTime(totalDao.getDateTime("yyyy-MM-dd"));
			}
			retutag = "notExam";
		}/*
		 * else if("invoFD".equals(tag)){
		 * tahk.setHkInvoPrePath(idea+tahk.getHkInvoPrePath());
		 * //System.out.println("=============="+tahk.getHkInvoPrePath());
		 * retutag="invoiceExam"; }else if("invoMD".equals(tag)){
		 * tahk.setHkInvoRelPath(idea+tahk.getHkInvoRelPath());
		 * if("OK".equals(idea)){ tahk.setHkStatus("追款审批");
		 * tahk.setHkInvoOverTime(totalDao.getDateTime("yyyy-MM-dd")); }
		 * retutag="invoiceExam"; }else if("retuFD".equals(tag)){
		 * tahk.setHkBackAuditB(idea+tahk.getHkBackAuditB());
		 * retutag="invoiceExam"; }
		 */else if ("retuMD".equals(tag)) {
			tahk.setHkBackAuditA(idea + tahk.getHkBackAuditA());
			if ("OK".equals(idea)) {
				tahk.setHkStatus("回款完成");

			}
			retutag = "invoiceExam";
		}
		totalDao.update(tahk);
		return retutag;
	}

	// 查看明细,发票明细，回款明细
	public List findhkSta(TaHkHuikuan tahk, String str) {
		// TaHkHuikuan tahk=(TaHkHuikuan)totalDao.getId(TaHkHuikuan.class, id);
		String hql = "from " + str + " where taHkHuikuan.id=" + tahk.getId();
		if ("TaHkSellSta".equals(str)) {
			hql = "from TaHkSellSta where taHkHuikuan.id=" + tahk.getId()
					+ " order by hkSellOutOrderId desc";
		}
		// System.out.println("输出sql语句："+hql);
		return totalDao.query(hql);
	}

	// 添加发票信息
	public String saveInvoice(TaHkHuikuan hk, List<TaHkSellSta> hkset,
			List<TaHkHkInvoice> invoiceArr, File[] attachment,
			String[] attachmentFileName, String[] attachmentStatus) {
		// select * from ta_hk_huikuan

		// update ta_hk_huikuan set hk_status='可开票', hk_invoPrePath='FD' WHERE
		// id=2
		// 上传附件
		// System.out.println("状态组长度"+attachmentStatus.length+"文件组长度===="+attachment.length
		// );
		String monthApply = null;// 申请开票月份
		String monthHk = null;// 回款期限月份
		String message = "";
		List<String> outOderNumerList = new ArrayList<String>();
		if (null != attachmentStatus) {
			File[] staFile = new File[attachmentStatus.length];
			String[] attfileame = new String[attachmentStatus.length];
			int count = 0;
			for (int i = 0; i < attachmentStatus.length; i++) {
				if ("yes".equals(attachmentStatus[i])) {
					staFile[i] = attachment[count];
					attfileame[i] = attachmentFileName[count];
					count++;
				}
			}
			attachment = staFile;
			attachmentFileName = attfileame;
		}
		String attachmentName = "";
		String Iftag = "yes";
		if (attachment != null && attachment.length > 0) {
			String fileName = "";
			int j = 0;
			for (int i = 0; i < attachment.length; i++) {// 文件的个数和发票的个数可能不同已上传的发票没有页面对应的文件所以i不一定=j只能找到第i个不为空的发票才能对应起来
				TaHkHkInvoice invoice = null;
				for (; j < invoiceArr.size(); j++) {
					invoice = invoiceArr.get(j);
					if (invoice != null && invoice.getHkInvoInvoNum() != null
							&& invoice.getHkInvoInvoNum().length() > 0) {
						j++;
						break;
					}
				}
				if (invoice == null) {
					break;
				}
				TaHkSellSta sellSta = hkset.get(j - 1);
				if (null != attachment[i]) {
					fileName = invoice.getHkInvoInvoNum().replaceAll("/", "")
							.trim()
							+ (attachmentFileName[i]
									.substring(attachmentFileName[i]
											.lastIndexOf(".")));
					hkset.get(j - 1).setHkSellFile(fileName);
				} else {// 查询相同发票号的上传附件
					if (!"".equals(fileName)) {
						hkset.get(j - 1).setHkSellFile(fileName);
					} else {
						// System.out.println(invoice.getHkInvoInvoNum()+"请上传发票附件！！！");
						// Iftag = "no";
					}
				}

				/*
				 * if (i > 0) { attachmentName += "|" + fileName; } else {
				 * attachmentName += fileName; }
				 */
				attachmentName.trim();
				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/huikuan/invoice")
						+ "/" + fileName;
				File file = new File(fileRealPath);
				try {
					if (null != attachment[i]) {
						FileCopyUtils.copy(attachment[i], file);
					}
				} catch (Exception e) {
					return message;
				}

				// 备份到项目
				String beiFenfileRealPath = "E:/WorkSpace/HHTask/WebRoot/upload/huikuan/invoice"
						+ "/" + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					if (null != attachment[i]) {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 判断list长度跟数组长度的大小
			/*
			 * if(attachment.length<){
			 * 
			 * }
			 */
		}
		// 添加保存发票信息
		// 修改开票明细
		// System.out.println("================================"+Iftag);
		String unit = "元";
		if (null != hkset && null != invoiceArr && "yes".equals(Iftag)) {
			Float countMoney = 0f;
			float count = 0f;
			Integer count1 = 0;
			for (int i = 0; i < hkset.size(); i++) {
				TaHkHkInvoice invoiceHead = null;
				if (i < invoiceArr.size()) {
					invoiceHead = invoiceArr.get(i);
				}
				if (invoiceHead == null) {
					count1++;
					continue;
				}
				if (invoiceHead.getHkInvoInvoNum() == null
						|| invoiceHead.getHkInvoInvoNum().length() == 0) {
					continue;

				}
				TaHkSellSta sellSta = hkset.get(i);
				TaHkSellSta hkSellSta = (TaHkSellSta) this.totalDao
						.getObjectById(TaHkSellSta.class, sellSta.getId());// 旧的数据
				if (hkSellSta.getHkSellFile() == null
						|| "".equals(hkSellSta.getHkSellFile())) {// 如果数据库中已上传过文件,则保存(及修改),反之保存新的数据
					if (sellSta.getHkSellPrice() != null
							&& sellSta.getHkSellPrice() > 0
							&& invoiceHead.getHkInvoInvoNum() != null
							&& !"".equals(invoiceHead.getHkInvoInvoNum())) {

						float money = sellSta.getHkSellPrice()
								* sellSta.getHkSellCount();
						if ("NO".equals(sellSta.getHkSellIsTax())) {
							money = (float) (money * 1.17);
						}
						countMoney += money;

						// count+=invoice.getHkInvoCount();
						count += sellSta.getHkSellCount();
						// sellSta.setTaHkHuikuan(hk);
						hkSellSta.setTaHkHuikuan(hk);
						hkSellSta.setHkSellFile(sellSta.getHkSellFile());
						hkSellSta.setHkSellPrice(sellSta.getHkSellPrice());
						hkSellSta.setHkSellIsTax(sellSta.getHkSellIsTax());
						hkSellSta.setHkSellCumpanyName(sellSta
								.getHkSellCumpanyName());
						hkSellSta.setHkSellMarkId(sellSta.getHkSellMarkId());
						hkSellSta.setHkSellCount(sellSta.getHkSellCount());
						hkSellSta.setHkSellSendId(sellSta.getHkSellSendId());
						hkSellSta.setHkSellOutOrderId(sellSta
								.getHkSellOutOrderId());
						if (!outOderNumerList.contains(sellSta
								.getHkSellOutOrderId())) {
							outOderNumerList.add(sellSta.getHkSellOutOrderId());
						}
						hkSellSta.setHkSellMoneyUnit(sellSta
								.getHkSellMoneyUnit());

						// invoice.setTaHkHuikuan(hk);
						// sellSta.setHkSellCount(invoice.getHkInvoCount());
						// invoice.setHkInvoTaxPrice(sellSta.getHkSellPrice());

						// sellSta.setHkSellMoney(money);
						hkSellSta.setHkSellMoney(money);
						// sellSta.setHkSellMarkId(invoice.getHkInvoMarkId());

						// sellSta.setHkSellNum(hk.getHkNum());
						hkSellSta.setHkSellNum(hk.getHkNum());
						// invoice.setHkInvoTaxMoney(money);
						// invoice.setHkInvoNum(hk.getHkNum());
						// sellSta.setHkSellMoneyUnit(invoice.getHkInvoMoneyUnit());
						// invoice.setHkInvoMoneyUnit(sellSta.getHkSellMoneyUnit());
						// System.out.println("==================单位"+invoice.getHkInvoMoneyUnit());
						unit = sellSta.getHkSellMoneyUnit();
						// invoice.setHkInvoBillTime(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));
						Users user = (Users) ActionContext.getContext()
								.getSession().get("Users");

						// totalDao.save(invoice);
						// 判断发票号存不存在
						String hqlExInvi = "from TaHkHkInvoice where hkInvoInvoNum=?";
						if (totalDao.query(hqlExInvi,
								invoiceHead.getHkInvoInvoNum()).size() <= 0) {
							TaHkHkInvoice invoice = new TaHkHkInvoice();
							invoice.setHkInvoMoneyUnit(unit);
							// invoice.setHkInvoMoney(sellSta.getHkSellMoney());
							invoice.setHkInvoTaxMoney(sellSta.getHkSellMoney());
							invoice.setHkInvoInvoNum(invoiceHead
									.getHkInvoInvoNum());
							invoice.setHkInvoCount(sellSta.getHkSellCount());
							// 发票上传设置
							if (null != sellSta.getHkSellFile()
									&& null == invoice.getHkInvoFile()) {
								invoice.setHkInvoFile(sellSta.getHkSellFile());
							}
							invoice.setHkInvoDrawer(user.getName());
							invoice.setHkInvoBillTime(totalDao
									.getDateTime("yyyy-MM-dd"));
							invoice.setTaHkHuikuan(hk);
							totalDao.save(invoice);
							// sellSta.setTaHkHkInvoice(invoice);
							hkSellSta.setTaHkHkInvoice(invoice);
						} else {
							TaHkHkInvoice invoice = (TaHkHkInvoice) totalDao
									.query(hqlExInvi,
											invoiceHead.getHkInvoInvoNum())
									.get(0);
							if (sellSta.getHkSellMoney() == null) {
								if (invoice.getHkInvoTaxMoney() != null) {
									invoice.setHkInvoTaxMoney(invoice
											.getHkInvoTaxMoney() + 0F);
								} else {
									invoice.setHkInvoTaxMoney(0F + 0F);
								}
							} else {
								if (invoice.getHkInvoTaxMoney() != null) {
									invoice.setHkInvoTaxMoney(invoice
											.getHkInvoTaxMoney()
											+ sellSta.getHkSellMoney());
								} else {
									invoice.setHkInvoTaxMoney(0F + sellSta
											.getHkSellMoney());
								}
							}
							invoice.setHkInvoCount(sellSta.getHkSellCount()
									+ invoice.getHkInvoCount());
							if (null == invoice.getHkInvoFile()
									&& null != sellSta.getHkSellFile()) {
								invoice.setHkInvoFile(sellSta.getHkSellFile());
							}
							totalDao.update(invoice);
							// sellSta.setTaHkHkInvoice(invoice);
							hkSellSta.setTaHkHkInvoice(invoice);
						}
						count1++;
						totalDao.update(hkSellSta);
					} else {
						message += "第 " + (i + 1) + "条数据的单价或发票信息不存在,请前往存档!\n";
						continue;
					}
				} else {
					count1++;
				}

				// invoice.setHkInvoDrawer(user.getName());
			}
			TaHkHuikuan taHkHuikuan = (TaHkHuikuan) totalDao.getObjectById(
					TaHkHuikuan.class, hk.getId());
			if (taHkHuikuan.getHkBillMoney() != null
					&& taHkHuikuan.getHkBillMoney() > 0) {// 原来已经有记录已开过票
				taHkHuikuan.setHkBillMoney(taHkHuikuan.getHkBillMoney()
						+ countMoney);
			} else {
				if (null != hk.getZhekou()) {
					// hk.setHkBillMoney(countMoney - hk.getZhekou());
					taHkHuikuan.setHkBillMoney(countMoney - hk.getZhekou());
				} else {
					// hk.setHkBillMoney(countMoney);
					taHkHuikuan.setHkBillMoney(countMoney);
				}
			}

			// hk.setHkApplyCount(count);
			// hk.setHkMoneyUnit(unit);
			taHkHuikuan.setHkApplyCount(count);
			taHkHuikuan.setHkMoneyUnit(unit);
			taHkHuikuan.setHkPayCycle(hk.getHkPayCycle());
			taHkHuikuan.setHkBillTime(hk.getHkBillTime());
			if (taHkHuikuan.getHkPayCycle() != null
					&& taHkHuikuan.getHkBillTime() != null) {// 计算回款期限
				Long time1 = Util.StringToDate(taHkHuikuan.getHkBillTime(),
						"yyyy-MM-dd").getTime();
				Long addTime = taHkHuikuan.getHkPayCycle() * 24 * 3600 * 1000l;
				taHkHuikuan.setLastTime(Util.DateToString(new Date(time1
						+ addTime), "yyyy-MM-dd"));
				monthHk = taHkHuikuan.getLastTime().substring(0, 7);
			}
			taHkHuikuan.setZhekou(hk.getZhekou());
			if (taHkHuikuan.getCheckTime() != null) {
				try {
					monthApply = taHkHuikuan.getCheckTime().substring(0, 7);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			String billDate = totalDao.getDateTime("yyyy-MM-dd");
			if (null != hk.getHkBillTime()) {
				billDate = hk.getHkBillTime();
			}
			billDate = billDate.replace("-", "");
			String nowDate = totalDao.getDateTime("yyyyMMdd");
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			try {
				java.util.Date now = df.parse(nowDate);
				java.util.Date date = df.parse(billDate);
				long l = now.getTime() - date.getTime();
				long day = l / (24 * 60 * 60 * 1000);
				int tday = (int) day;
				// 差额比较
				int cou = hk.getHkPayCycle() - tday;
				if (cou > 7) {
					// hk.setHkBillTime(totalDao.getDateTime("yyyy-MM-dd"));
					taHkHuikuan.setHkBillTime(totalDao
							.getDateTime("yyyy-MM-dd"));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			// 状态修改
			if (count1 == hkset.size() && count1 > 0) {
				// hk.setHkStatus("回款中");
				taHkHuikuan.setKpTime(Util.getDateTime());
				taHkHuikuan.setHkStatus("回款中");
				message = "";
				totalDao.update(taHkHuikuan);

				/****** 添加收款汇总 ***********/
				SupplierCorePayable scp = (SupplierCorePayable) totalDao
						.getObjectByCondition(
								"from SupplierCorePayable where supplierName=? and coreType='主营' and payableType = '收款'",
								taHkHuikuan.getHkClientComp());
				Float addYfkmoney = taHkHuikuan.getHkBillMoney();
				if (scp != null) {
					scp
							.setYingfukuanJine(scp.getYingfukuanJine()
									+ addYfkmoney);
					scp.setWeifukuanJine(scp.getYingfukuanJine()
							- scp.getRealfukuanJine());
					totalDao.update(scp);
				} else {
					scp = new SupplierCorePayable();
					scp.setCoreType("主营");
					scp.setPayableType("收款");
					scp.setSupplierName(taHkHuikuan.getHkClientComp());
					// scp.setSupplierId(corePayable.getSupplierId());
					scp.setYingfukuanJine(addYfkmoney);
					scp.setRealfukuanJine(0F);
					scp.setWeifukuanJine(addYfkmoney);
					scp.setAddTime(Util.getDateTime());
					totalDao.save(scp);
				}
				taHkHuikuan.setScpId(scp.getId());
				totalDao.update(taHkHuikuan);

				// 设置订单的开票率
				if (outOderNumerList.size() > 0) {
					for (int i = 0; i < outOderNumerList.size(); i++) {
						Float kpCount = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from TaHkHuikuan where hkStatus in('回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId =?)",
										outOderNumerList.get(i));
						Float fpTotalCount = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from TaHkHuikuan where hkStatus in('可开票','回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId =?)",
										outOderNumerList.get(i));
						Float kpRate = 0f;
						if (kpCount == null) {
							kpCount = 0f;
						}
						if (fpTotalCount != null && fpTotalCount != 0) {
							kpRate = (kpCount / fpTotalCount) * 100f;
						}
						OrderManager order = (OrderManager) totalDao
								.getObjectByCondition(
										"from OrderManager where orderNum=? and (type is null or type='正常')",
										outOderNumerList.get(i));
						if (order != null) {
							order.setKprate(kpRate);
							totalDao.update(order);
						}
					}
				}
				// 计算该月的开票及时率
				if (monthApply != null) {
					MonthlySummary monthlySummary1 = (MonthlySummary) totalDao
							.getObjectByCondition(
									"from MonthlySummary where name='开票及时率' and month=?",
									monthApply + "月");
					boolean had1 = true;
					if (monthlySummary1 == null) {
						monthlySummary1 = new MonthlySummary();
						monthlySummary1.setAddTime(Util.getDateTime());
						monthlySummary1.setMonth(monthApply + "月");
						monthlySummary1.setName("开票及时率");
						monthlySummary1.setNumber("kpjsr");
						had1 = false;
					}
					// 该月已开票数量
					Float hadCount = (Float) totalDao
							.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款中','回款完成') and checkTime like '"
									+ monthApply + "%'");
					// 该月开票数量
					Float allKpCount = (Float) totalDao
							.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('开票申请','审批中','开票','回款中','回款完成') and checkTime like '"
									+ monthApply + "%'");
					if (hadCount == null) {
						hadCount = 0f;
					}
					if (allKpCount == null || allKpCount == 0) {
						monthlySummary1.setCompletionRate(0f);
					} else {
						monthlySummary1
								.setCompletionRate(hadCount / allKpCount);
					}
					if (had1) {
						totalDao.update(monthlySummary1);
					} else {
						totalDao.save(monthlySummary1);
					}
				}
				// 计算该月的回款完成及时率
				if (monthHk != null) {
					MonthlySummary monthlySummary2 = (MonthlySummary) totalDao
							.getObjectByCondition(
									"from MonthlySummary where name='回款完成及时率' and month=?",
									monthHk + "月");
					boolean had2 = true;
					if (monthlySummary2 == null) {
						monthlySummary2 = new MonthlySummary();
						monthlySummary2.setAddTime(Util.getDateTime());
						monthlySummary2.setMonth(monthHk + "月");
						monthlySummary2.setName("回款完成及时率");
						monthlySummary2.setNumber("hkwcr");
						had2 = false;
					}
					// 该月已及时完成的回款期限为这个月的发票
					Float hadhkCount = (Float) totalDao
							.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款完成') and lastTime like '"
									+ monthHk + "%' and overTime<lastTime");
					// 该月已完成的
					Float allhkCount = (Float) totalDao
							.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款中','回款完成') and lastTime like '"
									+ monthHk + "%'");
					if (hadhkCount == null) {
						hadhkCount = 0f;
					}
					if (allhkCount == null || allhkCount == 0) {
						monthlySummary2.setCompletionRate(0f);
					} else {
						monthlySummary2.setCompletionRate(hadhkCount
								/ allhkCount);
					}
					if (had2) {
						totalDao.update(monthlySummary2);
					} else {
						totalDao.save(monthlySummary2);
					}
				}

			} else {
				// hk.setHkStatus("可开票");
				taHkHuikuan.setHkStatus("可开票");
				totalDao.update(taHkHuikuan);
				return message;
			}

		}

		return message;
	}

	// 重新开票
	public boolean updateInvoice(TaHkHuikuan hk, List<TaHkSellSta> hkset,
			List<TaHkHkInvoice> invoiceArr, File[] attachment,
			String[] attachmentFileName, String[] attachmentStatus) {
		if (null != attachmentStatus) {
			File[] staFile = new File[attachmentStatus.length];
			String[] attfileame = new String[attachmentStatus.length];
			int count = 0;
			for (int i = 0; i < attachmentStatus.length; i++) {
				if ("yes".equals(attachmentStatus[i])) {
					staFile[i] = attachment[count];
					attfileame[i] = attachmentFileName[count];
					count++;
				}
			}
			attachment = staFile;
			attachmentFileName = attfileame;
		}
		String attachmentName = "";
		String Iftag = "yes";
		if (attachment != null && attachment.length > 0) {
			String fileName = "";
			for (int i = 0; i < attachment.length; i++) {
				// System.out.println("================"+attachment[i]);
				TaHkSellSta sellSta = hkset.get(i);
				TaHkHkInvoice invoice = invoiceArr.get(i);
				if (null != attachment[i]) {
					System.out.println("输出发票号" + invoice.getHkInvoInvoNum());
					fileName = invoice.getHkInvoInvoNum().replaceAll("/", "")
							.trim()
							// + new
							// SimpleDateFormat("yyyyMMddHHmmss").format(new
							// Date())
							+ (attachmentFileName[i]
									.substring(attachmentFileName[i]
											.lastIndexOf(".")));
					if (!"".equals(fileName)) {
						hkset.get(i).setHkSellFile(fileName);
					}

				}
				// System.out.println("=============fapaioxixn"+hkset.get(i).getHkSellFile());
				/*
				 * else{//查询相同发票号的上传附件 //String
				 * hqlinvo="from TaHkSellSta where hkSellFile like '"
				 * +invoice.getHkInvoInvoNum()+"%' and hkSellFile is not null";
				 * //System.out.println("=========================tnnd");
				 * if(!"".equals(fileName)){
				 * hkset.get(i).setHkSellFile(fileName); }else{
				 * //System.out.println
				 * (invoice.getHkInvoInvoNum()+"请上传发票附件！！！"); //Iftag="no";
				 * //return false; } }
				 */

				/*
				 * if (i > 0) { attachmentName += "|" + fileName; } else {
				 * attachmentName += fileName; }
				 */
				attachmentName.trim();
				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/huikuan/invoice")
						+ "/" + fileName;
				File file = new File(fileRealPath);
				try {
					if (null != attachment[i]) {
						FileCopyUtils.copy(attachment[i], file);
					}
				} catch (Exception e) {
					return false;
				}

				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/huikuan/invoice"
						+ "/" + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					if (null != attachment[i]) {
						FileCopyUtils.copy(attachment[i], beiFenFile);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 判断list长度跟数组长度的大小
			/*
			 * if(attachment.length<){
			 * 
			 * }
			 */
		}
		// 添加保存发票信息
		// 修改开票明细
		// System.out.println("================================"+Iftag);
		String unit = "元";
		if (null != hkset && null != invoiceArr && "yes".equals(Iftag)) {
			Float countMoney = 0f;
			float count = 0f;
			for (int i = 0; i < hkset.size(); i++) {
				TaHkSellSta sellSta = hkset.get(i);
				TaHkHkInvoice invoiceHead = invoiceArr.get(i);
				float money = sellSta.getHkSellPrice()
						* sellSta.getHkSellCount();
				countMoney += money;
				// count+=invoice.getHkInvoCount();
				count += sellSta.getHkSellCount();
				sellSta.setTaHkHuikuan(hk);
				// invoice.setTaHkHuikuan(hk);
				// sellSta.setHkSellCount(invoice.getHkInvoCount());
				// invoice.setHkInvoTaxPrice(sellSta.getHkSellPrice());
				sellSta.setHkSellMoney(money);
				// sellSta.setHkSellMarkId(invoice.getHkInvoMarkId());
				sellSta.setHkSellNum(hk.getHkNum());
				// invoice.setHkInvoTaxMoney(money);
				// invoice.setHkInvoNum(hk.getHkNum());
				// sellSta.setHkSellMoneyUnit(invoice.getHkInvoMoneyUnit());
				// invoice.setHkInvoMoneyUnit(sellSta.getHkSellMoneyUnit());
				// System.out.println("==================单位"+invoice.getHkInvoMoneyUnit());
				unit = sellSta.getHkSellMoneyUnit();
				// invoice.setHkInvoBillTime(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));
				Users user = (Users) ActionContext.getContext().getSession()
						.get("Users");

				// totalDao.save(invoice);
				// 判断发票号存不存在
				System.out.println("===================id="
						+ invoiceHead.getId());
				String hqlExInvi = "from TaHkHkInvoice where id="
						+ invoiceHead.getId();
				if (totalDao.query(hqlExInvi).size() <= 0) {
					TaHkHkInvoice invoice = new TaHkHkInvoice();
					invoice.setHkInvoMoneyUnit(unit);
					// invoice.setHkInvoMoney(sellSta.getHkSellMoney());
					invoice.setHkInvoTaxMoney(sellSta.getHkSellMoney());
					invoice.setHkInvoInvoNum(invoiceHead.getHkInvoInvoNum());
					invoice.setHkInvoCount(sellSta.getHkSellCount());
					// 发票上传设置
					if (null != sellSta.getHkSellFile()
							&& null == invoice.getHkInvoFile()) {
						invoice.setHkInvoFile(sellSta.getHkSellFile());
					}
					invoice.setHkInvoDrawer(user.getName());
					invoice.setHkInvoBillTime(totalDao
							.getDateTime("yyyy-MM-dd"));
					invoice.setTaHkHuikuan(hk);
					totalDao.save(invoice);
					totalDao.delete((TaHkHkInvoice) (totalDao.getObjectById(
							TaHkHkInvoice.class, sellSta.getTaHkHkInvoice()
									.getId())));
					sellSta.setTaHkHkInvoice(invoice);
				} else {
					TaHkHkInvoice invoice = (TaHkHkInvoice) totalDao.query(
							hqlExInvi).get(0);
					invoice.setHkInvoTaxMoney(invoice.getHkInvoTaxMoney()
							+ sellSta.getHkSellMoney());
					invoice.setHkInvoCount(sellSta.getHkSellCount()
							+ invoice.getHkInvoCount());
					if (null == invoice.getHkInvoFile()
							&& null != sellSta.getHkSellFile()) {
						invoice.setHkInvoFile(sellSta.getHkSellFile());
					}
					totalDao.update(invoice);
					sellSta.setTaHkHkInvoice(invoice);

				}
				totalDao.update(sellSta);
				// invoice.setHkInvoDrawer(user.getName());
			}
			hk.setHkBillMoney(countMoney);
			hk.setHkApplyCount(count);
			hk.setHkMoneyUnit(unit);
			if (null == hk.getHkBillTime()) {
				hk.setHkBillTime(totalDao.getDateTime("yyyy-MM-dd"));
			}

			// 状态修改
			hk.setHkStatus("回款中");
			totalDao.update(hk);
			return true;
		}
		return false;
	}

	// 查询多个文件的地址
	public List findMoreFile(TaHkHuikuan hk) {
		String fileAdd = hk.getHkNoticeFile();
		List list = new ArrayList();
		if (null != fileAdd && !"".equals(fileAdd)) {
			String[] strarr = fileAdd.split("&");
			if (strarr.length > 0) {
				for (int i = 0; i < strarr.length; i++) {
					list.add(strarr[i]);
				}
			}
		}
		return list;
	}

	// 删除回款记录
	public boolean deleteHK(Integer id) {
		TaHkHuikuan hk = (TaHkHuikuan) totalDao.getObjectById(
				TaHkHuikuan.class, id);
		String hql = "from TaHkSellSta where taHkHuikuan.id=?";
		TaHkSellSta hkSellSta = (TaHkSellSta) this.totalDao
				.getObjectByCondition(hql, hk.getId());
		String hql1 = "from Sell where sellMarkId=? and sellSendnum=?";
		List list = this.totalDao.query(hql1, hkSellSta.getHkSellMarkId(),
				hkSellSta.getHkSellSendId());
		for (int i = 0; i < list.size(); i++) {
			Sell sell = (Sell) list.get(i);
			sell.setSellSendnum(null);
			this.totalDao.update(sell);
		}

		if (null != hk) {
			for (Object obj : hk.getTaHkSellStas()) {
				TaHkSellSta se = (TaHkSellSta) obj;
				totalDao.delete(se);
			}
			for (Object obj : hk.getTaHkHkInvoices()) {
				TaHkHkInvoice invo = (TaHkHkInvoice) obj;
				totalDao.delete(invo);
			}
			totalDao.delete(hk);
			return true;
		}
		return false;
	}

	// 添加回款记录
	public boolean saveBackMon(TaHkHuikuan taHk, TaHkBackMoney backMoney,
			File[] attachment, String[] attachmentFileName) {
		String attachmentName = "";
		String monthHk = null;// 发票的回款期限月份
		Users user =	Util.getLoginUser();
		if(user == null){
			return false;
		}
		if (attachment != null && attachment.length > 0) {
			for (int i = 0; i < attachment.length; i++) {
				String fileName = taHk.getHkNum().replaceAll("/", "").trim()
						+ new SimpleDateFormat("yyyyMMddHHmmss")
								.format(new Date())
						+ (attachmentFileName[i]
								.substring(attachmentFileName[i]
										.lastIndexOf(".")));
				if (i > 0) {
					attachmentName += "&" + fileName;
				} else {
					attachmentName += fileName;
				}
				attachmentName.trim();
				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/huikuan")
						+ "/" + fileName;
				File file = new File(fileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], file);
				} catch (Exception e) {
					return false;
				}

				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/huikuan"
						+ "/" + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], beiFenFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		backMoney.setHkbmFile(attachmentName);
		backMoney.setTaHkHuikuan(taHk);
		backMoney.setHkbmNum(taHk.getHkNum());
		List<TaHkPartBackMoney> partMoneyList = backMoney
				.getTaHkPartBackMoneys();
		totalDao.save(backMoney);
		String processName = "回款入账申请";
		Integer epId = null;
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					TaHkBackMoney.class, backMoney.getId(), "ep_status", "id",
					""
							,user.getDept() + "部门 "
							+ user.getName() + "回款入账申请，请您审批", true);
			if (epId != null && epId > 0) {
				backMoney.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.get(
						CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun.getAuditStatus())) {
					backMoney.setEpstatus("同意");
					//更新账户余额;
//					SubBudgetRate sub =	(SubBudgetRate) totalDao.getObjectByCondition(" from SubBudgetRate where id = ? ",backMoney.getSubId() );
//					sub.setBorrowMoney(sub.getBorrowMoney()==null?backMoney.getHkbmMoney():backMoney.getHkbmMoney()+sub.getBorrowMoney());//本期借方发生余额
//					sub.setBorrowJieyuMoney(sub.getBorrowJieyuMoney()==null?backMoney.getHkbmMoney():backMoney.getHkbmMoney()+sub.getBorrowJieyuMoney());//期末借方余额
//					sub.setBorrowYearBegingMoney(sub.getBorrowYearBegingMoney()==null?backMoney.getHkbmMoney():backMoney.getHkbmMoney()+sub.getBorrowYearBegingMoney());//本年借方余额
//					totalDao.update(sub);
				} else {
					backMoney.setEpstatus("未审批");
				}
					totalDao.update(backMoney);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		float rate = 0f;
		if (null == taHk.getHkTrackerTotMoney()) {
			taHk.setHkTrackerTotMoney(backMoney.getHkbmMoney());
			// System.out.println("3333333===="+backMoney.getHkbmMoney()+"===:"+taHk.getHkBillMoney());

		} else {
			taHk.setHkTrackerTotMoney(taHk.getHkTrackerTotMoney()
					+ backMoney.getHkbmMoney());
		}
		rate = taHk.getHkTrackerTotMoney() / taHk.getHkBillMoney();
		rate = (float) (Math.round(rate * 10000) / 100);
		taHk.setHkTrackRate(rate);
		// System.out.println("========================="+taHk.getHkTrackerTotMoney()+"====="+taHk.getHkBillMoney());
		if (100 == rate) {
			taHk.setHkStatus("回款完成");
			taHk.setOverTime(Util.getDateTime("yyyy-MM-dd"));
		}/*
		 * else{ taHk.setHkStatus("回款"); }
		 */
		if (taHk.getLastTime() != null) {
			try {
				monthHk = taHk.getLastTime().substring(0, 7);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		totalDao.update(taHk);
		/**********更新收款汇总数据*************************/
		if (taHk.getScpId() != null) {
			// 更新付款汇总表
			SupplierCorePayable scp = (SupplierCorePayable) totalDao
					.getObjectById(SupplierCorePayable.class,
							taHk.getScpId());
			if (scp != null) {
				scp.setWeifukuanJine(scp.getWeifukuanJine()
						- backMoney.getHkbmMoney());
				scp.setRealfukuanJine(scp.getRealfukuanJine()
						+ backMoney.getHkbmMoney());
				if (scp.getFkCount() == null) {
					scp.setFkCount(0);
				}
				scp.setFkCount(scp.getFkCount() + 1);
				totalDao.update(scp);
			}
		}
		
		Float totalCount = 0f;
		if (partMoneyList != null && partMoneyList.size() > 0) {
			for (TaHkPartBackMoney partMoney : partMoneyList) {// 保存对应零件的回款金额
				partMoney.setBackMoneyId(backMoney.getId());
				partMoney.setHkbmNum(backMoney.getHkbmNum());
				partMoney.setHkTime(backMoney.getHkbmDate());
				partMoney.setHkbmClientCom(backMoney.getHkbmClientCom());
				// 通过单价和回款金额算出回款数量
				partMoney.setHkbmCount(partMoney.getHkbmMoney()
						/ partMoney.getHkSellPrice());
				Float hkbmCount = partMoney.getHkbmCount();
				Float hmMoney = partMoney.getHkbmMoney();
				totalCount += hkbmCount;
				if (partMoney.getHkSellOrderId() != null) {// 有订单号
					// 根据件号和订单号查到对应的订单产品
					ProductManager product = (ProductManager) totalDao
							.getObjectByCondition(
									"from ProductManager where pieceNumber=? and orderManager.outOrderNumber=? and ( orderManager.type is null or orderManager.type='正常')",
									partMoney.getHkmarkId(), partMoney
											.getHkSellOrderId());
					if (product != null) {
						if (product.getHkMoney() == null) {
							product.setHkMoney(partMoney.getHkbmMoney());
						} else {
							product.setHkMoney(product.getHkMoney()
									+ partMoney.getHkbmMoney());
						}
						if (product.getHkNumber() == null) {
							product.setHkNumber(hkbmCount);
						} else {
							product.setHkNumber(product.getHkNumber()
									+ hkbmCount);
						}
						totalDao.update(product);
						// 订单号查询到对应的订单
						OrderManager order = product.getOrderManager();
						if (order != null) {
							String hql1 = "select sum(num) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
							Float pronum = (Float) totalDao
									.getObjectByCondition(hql1, order.getId());
							String hql2 = "select sum(cutNum) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
							Float cutNum = (Float) totalDao
									.getObjectByCondition(hql2, order.getId());
							if (cutNum != null) {
								pronum = pronum - cutNum;
							}
							// 修改订单回款率
							if (pronum != null && pronum > 0) {
								String hql3 = "select sum(hkNumber) from ProductManager where (status is null or status!='取消') and orderManager.id= ?";
								Float hkNum = (Float) totalDao
										.getObjectByCondition(hql3, order
												.getId());
								if (hkNum == null) {
									hkNum = 0f;
								}
								order.setHkrate(hkNum / pronum * 100);
							} else {
								order.setHkrate(0f);
							}
							totalDao.update(order);
						}
					}
					// }else{
					// //无订单号
					// if(hkbmCount>0&&hmMoney>0){
					// List<ProductManager> productList=(List<ProductManager>)
					// totalDao.query("from ProductManager where pieceNumber=? and kpNumber is not null and (hkNumber is null or kpNumber>hkNumber) order by num desc",
					// partMoney.getHkmarkId());
					// if(productList.size()>0){
					// for(ProductManager product :productList){
					// if(product.getHkNumber()==null){
					// product.setHkNumber(0f);
					// }
					// if(product.getHkMoney()==null){
					// product.setHkMoney(0f);
					// }
					// if((product.getKpNumber()-product.getHkNumber())>=hkbmCount){//该产品的可开票数量大于等于此次回款的数量
					// product.setHkNumber(product.getHkNumber()+hkbmCount);
					// product.setHkMoney(product.getHkMoney()+hmMoney);
					// totalDao.update(product);
					// //订单号查询到对应的订单
					// OrderManager order=product.getOrderManager();
					// if(order!=null){
					// String hql1 =
					// "select sum(num) from ProductManager where orderManager.id= ?";
					// Float pronum = (Float)
					// totalDao.getObjectByCondition(hql1, order.getId());
					// String hql2 =
					// "select sum(cutNum) from ProductManager where orderManager.id= ?";
					// Float cutNum = (Float)
					// totalDao.getObjectByCondition(hql2, order.getId());
					// if(cutNum!=null){
					// pronum=pronum-cutNum;
					// }
					// //修改订单回款率
					// if(pronum!=null&&pronum>0){
					// String hql3 =
					// "select sum(hkNumber) from ProductManager where orderManager.id= ?";
					// Float hkNum =(Float) totalDao.getObjectByCondition(hql3,
					// order.getId());
					// if(hkNum==null){
					// hkNum=0f;
					// }
					// order.setHkrate((hkNum/pronum * 100));
					// }else{
					// order.setHkrate(0f);
					// }
					// totalDao.update(order);
					// }
					// break;
					// }else{//该产品的可开票数量大于此次回款的数量
					// product.setHkNumber(Float.parseFloat(product.getKpNumber()+""));
					// Float
					// deleteCount=product.getKpNumber()-product.getHkNumber();
					// hkbmCount=hkbmCount-deleteCount;
					// Float deleteMoney=deleteCount*partMoney.getHkSellPrice();
					// product.setHkMoney(product.getHkMoney()+deleteMoney);
					// //订单号查询到对应的订单
					// OrderManager order=product.getOrderManager();
					// if(order!=null){
					// String hql1 =
					// "select sum(num) from ProductManager where orderManager.id= ?";
					// Float pronum = (Float)
					// totalDao.getObjectByCondition(hql1, order.getId());
					// String hql2 =
					// "select sum(cutNum) from ProductManager where orderManager.id= ?";
					// Float cutNum = (Float)
					// totalDao.getObjectByCondition(hql2, order.getId());
					// if(cutNum!=null){
					// pronum=pronum-cutNum;
					// }
					// //修改订单回款率
					// if(pronum!=null&&pronum>0){
					// String hql3 =
					// "select sum(hkNumber) from ProductManager where orderManager.id= ?";
					// Float hkNum =(Float) totalDao.getObjectByCondition(hql3,
					// order.getId());
					// if(hkNum==null){
					// hkNum=0f;
					// }
					// order.setHkrate(hkNum/pronum * 100);
					// }else{
					// order.setHkrate(0f);
					// }
					// totalDao.update(order);
					// }
					// }
					// }
					// }
					// }
				}

				totalDao.save(partMoney);
			}
		}
		backMoney.setHkbmCount(totalCount);
		totalDao.update(backMoney);
		// 计算该月的回款完成及时率
		MonthlySummary monthlySummary2 = (MonthlySummary) totalDao
				.getObjectByCondition(
						"from MonthlySummary where name='回款完成及时率' and month=?",
						monthHk + "月");
		boolean had2 = true;
		if (monthlySummary2 == null) {
			monthlySummary2 = new MonthlySummary();
			monthlySummary2.setAddTime(Util.getDateTime());
			monthlySummary2.setMonth(monthHk + "月");
			monthlySummary2.setName("回款完成及时率");
			monthlySummary2.setNumber("hkwcr");
			had2 = false;
		}
		// 该月已及时完成的回款期限为这个月的发票
		Float hadhkCount = (Float) totalDao
				.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款完成') and lastTime like '"
						+ monthHk + "%' and overTime<lastTime");
		// 该月已完成的
		Float allhkCount = (Float) totalDao
				.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款中','回款完成') and lastTime like '"
						+ monthHk + "%'");
		if (hadhkCount == null) {
			hadhkCount = 0f;
		}
		if (allhkCount == null || allhkCount == 0) {
			monthlySummary2.setCompletionRate(0f);
		} else {
			monthlySummary2.setCompletionRate(hadhkCount / allhkCount);
		}
		if (had2) {
			totalDao.update(monthlySummary2);
		} else {
			totalDao.save(monthlySummary2);
		}
		return true;
	}

	// 计算回款倒计时
	public List updatefindCountdown(List<TaHkHuikuan> list) {

		List<TaHkHuikuan> li = new ArrayList<TaHkHuikuan>();
		if (list.size() > 0 && null != list) {
			for (TaHkHuikuan hk : list) {
				String billDate = hk.getHkBillTime();
				billDate = billDate.replace("-", "");
				String nowDate = totalDao.getDateTime("yyyyMMdd");
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				try {
					java.util.Date now = df.parse(nowDate);
					java.util.Date date = df.parse(billDate);
					long l = now.getTime() - date.getTime();
					long day = l / (24 * 60 * 60 * 1000);
					int tday = (int) day;
					// 回款倒计时
					int cou = hk.getHkPayCycle() - tday;
					// 添加倒计时字段
					hk.setHkCountDown(cou);
					totalDao.update(hk);
					li.add(hk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		String hql = "from TaHkHuikuan where hkStatus in('回款中') order by hkCountDown asc hkBillTime desc hkTrackRate asc";
		li = totalDao.query(hql);

		return li;
	}

	// 查询价格
	public String selectPrice(String markId, String isTax) {
		String message = "";
		if (null != markId && markId.length() > 0) {
			String markId2 = null;
			try {
				// 解码(进行解码)
				markId2 = java.net.URLDecoder.decode(markId, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String hql = "select hsPrice from Price where partNumber=? and produceType='销售'";
			if ("NO".equals(isTax)) {
				hql = "select bhsPrice from Price where partNumber=? and produceType='销售'";
			}
			List<Double> list = totalDao.query(hql, markId2);
			for (Double d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	// 添加到任务栏
	public String saveShoppingCard(Integer id) {
		TaHkSellSta hkSellSta = (TaHkSellSta) totalDao.getObjectById(
				TaHkSellSta.class, id);
		// 判断任务栏中有没有此明细
		String hql = "from TaHkShoppingCard where haSellSta.id=?";
		if (totalDao.query(hql, id).size() > 0) {
			return "对不起，任务栏中已经存在该送货明细，请查实！！";
		} else {
			TaHkShoppingCard shoppingCard = new TaHkShoppingCard();
			shoppingCard.setHaSellSta(hkSellSta);
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			shoppingCard.setShoppingUserCode(user.getCode());
			shoppingCard.setShoppingUserName(user.getName());
			shoppingCard.setShoppingTime(totalDao
					.getDateTime("yyyy-MM-dd HH:mm:SS"));
			totalDao.save(shoppingCard);
			// hkSellSta.setTaShoppingCard(shoppingCard);
			// totalDao.update(hkSellSta);
			return "任务栏添加成功！！！";
		}
	}

	// 管理任务栏
	public List queryShopping(TaHkSellSta tahkSellSta, String startDate,
			String endDate) {
		String hql = "";
		TaHkShoppingCard shoppingCard = new TaHkShoppingCard();
		String bteween[] = new String[2];
		String btDate = "shoppingTime";
		String oth = "";
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = "'" + startDate + "'";
			bteween[1] = "'" + endDate + "'";
		}
		if (null != tahkSellSta) {
			String hqlchld = totalDao.getChildHql("id", tahkSellSta);
			hql = "from TaHkShoppingCard where haSellSta.id in(" + hqlchld
					+ ")";
		} else {
			hql = totalDao.criteriaQueries(shoppingCard, btDate, bteween, "");
		}
		return totalDao.query(hql);
	}

	// 删除任务栏中的送货单任务
	public String deleteShopping(Integer id) {
		TaHkShoppingCard shoppingCard = (TaHkShoppingCard) totalDao
				.getObjectById(TaHkShoppingCard.class, id);
		if (totalDao.delete(shoppingCard)) {
			return "deleteShoppingOK";
		} else {
			return "ERROR";
		}

	}

	// 总经理强制关闭回款或打回重开发票
	public boolean updateCompulsory(TaHkHuikuan hk, String tag) {
		if ("hkOVER".equals(tag)) {
			hk.setHkStatus("关闭");
			return totalDao.update(hk);
		} else if ("hkRepeatInvo".equals(tag)) {
			String hql = "from TaHkSellSta where taHkHuikuan.id=" + hk.getId();
			if (totalDao.query(hql).size() > 0) {
				for (int i = 0; i < totalDao.query(hql).size(); i++) {
					TaHkSellSta hkSta = (TaHkSellSta) totalDao.query(hql)
							.get(i);
					hkSta.setTaHkHkInvoice(null);
					totalDao.update(hkSta);
					// 删除发票

				}
			}
			String hqlInvo = "from TaHkHkInvoice where taHkHuikuan.id="
					+ hk.getId();
			if (totalDao.query(hqlInvo).size() > 0) {
				for (int i = 0; i < totalDao.query(hqlInvo).size(); i++) {
					TaHkHkInvoice invoice = (TaHkHkInvoice) totalDao.query(
							hqlInvo).get(i);
					invoice.setTaHkHuikuan(null);
					totalDao.delete(invoice);
				}
			}
			hk.setHkStatus("可开票");
			hk.setHkInvoPrePath("FD");
			hk.setHkInvoRelPath("MD");
			totalDao.update(hk);
		}
		return false;
	}

	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				TaHkHuikuan.class, false);
		if (map != null) {
			String hql = "from TaHkHuikuan where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	// 批量审批操作
	@Override
	public boolean updateExamOADetail(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				TaHkHuikuan detail = (TaHkHuikuan) totalDao.getObjectById(
						TaHkHuikuan.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					CircuitRun circuitRun = circuitRunServer
							.findCircuitRunById(detail.getEpId());
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);

					if ("审批成功".equals(audit)) {// 判断发送提醒消息
						Integer userIds[] = circuitRunServer.findAuditUserIds(
								detail.getEpId(),
								circuitRun.getAuditLevel() + 1);
						if (userIds != null && userIds.length > 0) {
							for (int j = 0; j < userIds.length; j++) {
								Integer userId = userIds[j];
								Users acessUser = (Users) totalDao
										.getObjectById(Users.class, userId);
								// 判断发送提醒消息
								String sendDate = Util
										.getDateTime("yyyy-MM-dd");
								String hqlMessage = "from OaMessageAlerm where oaUserName='"
										+ user.getName()
										+ "' and sendDate='"
										+ sendDate + "' and accessPhone=?";
								String phone = acessUser.getPassword()
										.getPhoneNumber();
								if (totalDao.query(hqlMessage, phone).size() <= 0) {
									String msg = user.getDept()
											+ "部门开票请您审批！--上海红湖排气系统有限公司";
									// 发送短信提醒
									OaMessageAlerm mess = new OaMessageAlerm();
									mess.setOaUserName(user.getName());
									mess.setContent(msg);
									mess.setAccessPhone(phone);
									mess.setRealName(user.getName());
									mess.setSendDate(sendDate);
									totalDao.save(mess);
									AlertMessagesServerImpl.addAlertMessages(
											"开票审批", msg, "开票审批", acessUser
													.getCode(), phone);
								}
							}
						}
					}
					// 更改明细状态
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, false);
					detail.setHkStatus("打回");
					detail.setHkApplier(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 导出(non-Javadoc)
	 * 
	 * @see com.task.Server.HuikuanServer#exportExcel(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void exportExcel(String startDate, String endDate) {

		// where budgetMonth like '%" + budgetMonth + "%'
		String hql = "from TaHkSellSta where  taHkHuikuan.id in ( select id from TaHkHuikuan where hkStatus='可开票'";
		if (null != startDate && startDate.length() > 0 && null != endDate
				&& endDate.length() > 0) {
			hql += "  and  hkApplyDate between '" + startDate + "' and '"
					+ endDate + "'";
		}
		hql += ")";
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("财务开票汇总".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("财务开票汇总", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 25,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "财务开票报表", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 8, 0);
			wf = new WritableFont(WritableFont.ARIAL, 18, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);

			// // 定义单元格样式 定义格式 字体 下划线 颜色 斜体 粗体
			WritableCellFormat wcf2 = new WritableCellFormat(new WritableFont(
					WritableFont.ARIAL, 10)); // 单元格定义
			wcf2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);// 添加细边框样式
			// 创建一个样式
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "编号", wcf2));
			ws.addCell(new jxl.write.Label(1, 1, "送货单号", wcf2));
			ws.addCell(new jxl.write.Label(2, 1, "品名", wcf2));
			ws.addCell(new jxl.write.Label(3, 1, "客户", wcf2));
			ws.addCell(new jxl.write.Label(4, 1, "订单号", wcf2));
			ws.addCell(new jxl.write.Label(5, 1, "单价", wcf2));
			ws.addCell(new jxl.write.Label(6, 1, "数量", wcf2));
			ws.addCell(new jxl.write.Label(7, 1, "总额", wcf2));

			// 格式
			ws.setRowView(1, 1000);// 第二行的高度
			ws.setColumnView(0, 10);// 第一列的宽度
			ws.setColumnView(1, 20);// 第二列的宽度
			ws.setColumnView(2, 10);// 第三列的宽度
			ws.setColumnView(3, 15);// 第四列的宽度
			ws.setColumnView(4, 15);// 第五列的宽度
			ws.setColumnView(5, 15);// 第六列的宽度
			ws.setColumnView(6, 15);// 第七列的宽度
			ws.setColumnView(7, 15);// 第八列的宽度
			ws.setColumnView(8, 15);// 第九列的宽度
			ws.setColumnView(9, 15);// 第十列的宽度
			ws.setColumnView(10, 15);// 第十一列的宽度

			// 把水平对齐方式指定为居中
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
					WritableFont.BOLD);
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.CENTRE);

			DecimalFormat fnum = new DecimalFormat("##0.00"); // 保留两位小数
			for (int i = 0; i < list.size(); i++) {
				ws.setRowView(i + 2, 500);// 循环调整每行的高度（从第二行开始）
				TaHkSellSta go = (TaHkSellSta) list.get(i);

				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wcf2));
				ws.addCell(new Label(1, i + 2, go.getHkSellSendId(), wcf2));
				ws.addCell(new Label(2, i + 2, go.getHkSellGoods(), wcf2));
				ws
						.addCell(new Label(3, i + 2, go.getHkSellCumpanyName(),
								wcf2));
				ws.addCell(new Label(4, i + 2, go.getHkSellOrderId(), wcf2));
				if (go.getHkSellPrice() == null) {
					go.setHkSellPrice(0F);
				}
				ws.addCell(new Label(5, i + 2,
						fnum.format(go.getHkSellPrice()), wcf2));
				if (go.getHkSellCount() == null) {
					go.setHkSellCount(0F);
				}
				ws.addCell(new Label(6, i + 2,
						fnum.format(go.getHkSellCount()), wcf2));
				if (go.getHkSellMoney() == null) {
					go.setHkSellMoney(0F);
				}
				ws.addCell(new Label(7, i + 2,
						fnum.format(go.getHkSellMoney()), wcf2));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 查询件号是否存在(non-Javadoc)
	 * 
	 * @see com.task.Server.HuikuanServer#findPriceByPartNumber(java.util.List)
	 */
	@Override
	public String findPriceByPartNumber(List<TaHkSellSta> hkset) {
		// TODO Auto-generated method stub
		// boolean b = false;
		String message = "";
		if (hkset != null) {
			for (int i = 0; i < hkset.size(); i++) {
				// hkSellMarkId
				TaHkSellSta hkSellSta = hkset.get(i);
				String hql = "from Price where partNumber=?";
				List list = this.totalDao.query(hql, hkSellSta
						.getHkSellMarkId());
				if (list == null || list.size() == 0) {
					message += hkSellSta.getHkSellMarkId() + ",";
					continue;
				}
			}
			if (message.length() > 0) {
				message = message.substring(1, message.length() - 1);
				message = "件号为:" + message + "的单价信息不存在,请前往存档!";
			}
		}
		return message;
	}

	@Override
	public TaHkHuikuan findtahk(TaHkHuikuan taHk) {
		// TODO Auto-generated method stub
		TaHkHuikuan hkHuikuan = (TaHkHuikuan) this.totalDao.getObjectById(
				TaHkHuikuan.class, taHk.getId());
		return hkHuikuan;
	}

	@Override
	public List<TaHkHuikuan> findUncheckList(TaHkHuikuan hk,
			TaHkSellSta tahkSellSta, String startDate, String endDate,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String bteween[] = new String[2];
		String btDate = "hkAppPayDate";
		String oth = "";
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = startDate;
			bteween[1] = endDate;
		}
		if (null == hk) {
			hk = new TaHkHuikuan();
		}
		hk.setHkStatus("未核对");
		if (null == tahkSellSta) {
			hk.setTaHkSellStas(null);
		} else {
			hk.setTaHkSellStas(null);
			tahkSellSta.setTaHkHuikuan(null);
		}
		hk.setTaHkHkInvoices(null);
		String hql = totalDao.criteriaQueries(hk, btDate, bteween, "");
		// String hql=totalDao.criteriaQueries(hk,null,null);
		String dic = "hkSellNum";
		String hqlChild = totalDao.queryIfhql(tahkSellSta, dic);
		String allHql = hql;
		if (null != tahkSellSta) {
			if (hql.contains("where")) {
				allHql += " and  id in (" + hqlChild + ")";
			} else {
				allHql += " where  id in (" + hqlChild + ")";
			}
		}
		// System.out.println("输出语句：==="+allHql);
		// +"  in  (:isc)"+oth;
		allHql += "  order by id desc";
		List list = totalDao.findAllByPage(allHql, pageNo, pageSize);
		return list;
	}

	@Override
	public int findUncheckcount(TaHkHuikuan hk, TaHkSellSta tahkSellSta,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		String bteween[] = new String[2];
		String btDate = "hkAppPayDate";
		String oth = "";
		Set<TaHkSellSta> taset = new HashSet<TaHkSellSta>();
		if (null != startDate && null != endDate && !"".equals(startDate)
				&& !"".equals(endDate)) {
			bteween[0] = startDate;
			bteween[1] = endDate;
		}
		if (null == hk) {
			hk = new TaHkHuikuan();
		}
		if (null == tahkSellSta) {
			hk.setTaHkSellStas(null);
		} else {
			taset.add(tahkSellSta);
			hk.setTaHkSellStas(taset);
			// tahkSellSta.setTaHkHuikuan(null);
		}
		hk.setHkStatus("未核对");
		hk.setTaHkHkInvoices(null);
		String hql = totalDao.criteriaQueries(hk, btDate, bteween, "");
		String dic = "hkSellNum";
		String hqlChild = totalDao.queryIfhql(tahkSellSta, dic);
		String allHql = hql;
		if (null != tahkSellSta) {
			if (hql.contains("where")) {
				allHql += " and  id in (" + hqlChild + ")";
			} else {
				allHql += " where  id in (" + hqlChild + ")";
			}
		}

		return totalDao.getCount(allHql);
	}

	@Override
	public String checkBillCount(TaHkHuikuan taHk, List<TaHkSellSta> hkset,
			File[] attachment, String[] attachmentFileName) {
		// TODO Auto-generated method stub
		//
		String attachmentName = "";
		List<String> sendNumberList = new ArrayList<String>();// 送货单号
		if (attachment != null && attachment.length > 0) {
			for (int i = 0; i < attachment.length; i++) {
				String fileName = taHk.getHkNum().replaceAll("/", "").trim()
						+ new SimpleDateFormat("yyyyMMddHHmmss")
								.format(new Date())
						+ (attachmentFileName[i]
								.substring(attachmentFileName[i]
										.lastIndexOf(".")));
				if (i > 0) {
					attachmentName += "&" + fileName;
				} else {
					attachmentName += fileName;
				}
				attachmentName.trim();
				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/huikuan")
						+ "/" + fileName;
				File file = new File(fileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], file);
				} catch (Exception e) {
					return "文件保存出错";
				}

				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/huikuan"
						+ "/" + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], beiFenFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		String monthApply = null;// 申请开票月份
		TaHkHuikuan old = findtahk(taHk);
		old.setHkNoticeFile(attachmentName);
		old.setHkClientComp(taHk.getHkClientComp());
		old.setHkClientName(taHk.getHkClientName());
		old.setHkAppPayDate(taHk.getHkAppPayDate());
		List<String> outOderNumerList = new ArrayList<String>();
		if (hkset != null && hkset.size() > 0 && old != null) {
			Set<TaHkSellSta> staSet = old.getTaHkSellStas();
			Set<TaHkSellSta> saveSet = new HashSet<TaHkSellSta>();
			if (staSet != null) {
				for (TaHkSellSta sta1 : staSet) {
					if (sta1.getHkSellSendId() != null
							&& !sendNumberList.contains(sta1.getHkSellSendId())) {
						sendNumberList.add(sta1.getHkSellSendId());
					}
					for (TaHkSellSta sta2 : hkset) {
						if (sta2.getId() != null
								&& sta2.getId().equals(sta1.getId())) {
							if (sta2.getHkSellCount() == null) {
								return "请填写件号为：" + sta1.getHkSellMarkId()
										+ ",申请开票数为：" + sta1.getApplyCount()
										+ "的开票明细的实际开票数量";
							} else if (sta2.getHkSellCount() < 0) {
								return "件号为：" + sta1.getHkSellMarkId()
										+ ",申请开票数为：" + sta1.getApplyCount()
										+ "的开票明细的实际开票数量不能小于0";
							} else if (sta2.getApplyCount() != null
									&& sta2.getApplyCount() < sta2
											.getHkSellCount()) {
								return "件号为：" + sta1.getHkSellMarkId()
										+ ",申请开票数为：" + sta1.getApplyCount()
										+ "的开票明细的实际开票数量不能大于申请开票数";
							} else if (sta2.getApplyCount() != null
									&& sta2.getApplyCount() >= sta2
											.getHkSellCount()) {
								if (sta2.getApplyCount() > sta2
										.getHkSellCount()) {
									ProductUnPassKp product = new ProductUnPassKp();
									product.setCusName(sta1
											.getHkSellCumpanyName());
									product.setMarkId(sta1.getHkSellMarkId());
									product.setSelfCard(sta1.getHkSelfCard());
									product.setHkSellSendId(sta1
											.getHkSellSendId());
									product.setProName(sta1.getHkSellGoods());
									product.setOdrerNumber(sta1
											.getHkSellOrderId());
									product.setTotalCount(sta2.getApplyCount()
											- sta2.getHkSellCount());
									product.setStatus("初始");
									product.setAddTime(Util.getDateTime());
									product.setHkSellStaId(sta2.getId());
									if (product.getTotalCount() != null
											&& product.getTotalCount() > 0) {
										totalDao.save(product);
									}
								}
								sta1.setHkSellCount(sta2.getHkSellCount());
								sta1.setHkSellCumpanyName(sta2
										.getHkSellCumpanyName());
								if (sta2.getHkSellCount() != 0) {
									saveSet.add(sta1);
									if (sta1.getHkSellOrderId() != null
											&& !sta1.getHkSellOrderId().equals(
													"未确定")) {
										if (!outOderNumerList.contains(sta1
												.getHkSellOrderId())) {
											outOderNumerList.add(sta1
													.getHkSellOrderId());
										}
										// 有外部订单号,根据订单号和件号去填补
										String ywMarkId = (String) totalDao
												.getObjectByCondition(
														"select ywMarkId from ProcardTemplate where procardStyle='总成' and markId=?",
														sta1.getHkSellMarkId());
										String addSql = null;
										if (ywMarkId != null
												&& ywMarkId.length() > 0) {
											addSql = "and (pieceNumber=? or pieceNumber='"
													+ ywMarkId + "')";
										} else {
											addSql = "and pieceNumber=?";
										}
										ProductManager productManager = (ProductManager) totalDao
												.getObjectByCondition(
														"from ProductManager where orderManager.orderNum=? "
																+ addSql
																+ "  and ( orderManager.type is null or orderManager.type='正常')",
														sta1.getHkSellOrderId(),
														sta1.getHkSellMarkId());
										if (productManager != null) {
											if (productManager.getKpNumber() == null) {
												productManager.setKpNumber(sta2
																.getHkSellCount());
											} else {
												productManager.setKpNumber((productManager
																.getKpNumber() + sta2
																.getHkSellCount()));
											}
											totalDao.update(productManager);
										}
									}
								} else {
									sta1.setTaHkHuikuan(null);
									sta1.setOldHkId(old.getId());
									totalDao.update(sta1);
								}
								break;
							}
						}
					}
				}
				old.setTaHkSellStas(saveSet);
				old.setCheckTime(Util.getDateTime());
				monthApply = old.getCheckTime().substring(0, 7);
				if (saveSet == null || saveSet.size() == 0) {
					old.setHkStatus("废弃");
				} else {
					old.setHkStatus("开票申请");
					try {
						String processName = "开票审批";
						Integer epId = CircuitRunServerImpl
								.createProcess(processName, TaHkHuikuan.class,
										old.getId(), "hkStatus", "id", old
												.getHkClientComp()
												+ " 的 "
												+ old.getHkClientName()
												+ "开票申请 ,请您审核!", true, "可开票");
						// Integer epId =
						// CircuitRunServerImpl.createProcess(192,
						// TaHkHuikuan.class, hk.getId(), "hkStatus", "id", hk
						// .getHkClientComp()
						// + " 的 "
						// + hk.getHkClientName()
						// + "开票申请 ,请您审核!", true, "可开票");
						if (epId != null && epId > 0) {
							old.setEpId(epId);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				totalDao.update(old);
				// 设置订单的开票率
				if (outOderNumerList.size() > 0) {
					for (int i = 0; i < outOderNumerList.size(); i++) {
						Float kpCount = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from TaHkHuikuan where hkStatus in('回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId =?)",
										outOderNumerList.get(i));
						Float fpTotalCount = (Float) totalDao
								.getObjectByCondition(
										"select count(*) from TaHkHuikuan where hkStatus in('可开票','回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId =?)",
										outOderNumerList.get(i));
						Float kpRate = 0f;
						if (kpCount == null) {
							kpCount = 0f;
						}
						if (fpTotalCount != null && fpTotalCount != 0) {
							kpRate = (kpCount / fpTotalCount) * 100f;
						}
						OrderManager order = (OrderManager) totalDao
								.getObjectByCondition(
										"from OrderManager where orderNum=? and (type is null or type='正常')",
										outOderNumerList.get(i));
						order.setKprate(kpRate);
						totalDao.update(order);
					}
				}
				// 计算该月的开票及时率
				MonthlySummary monthlySummary1 = (MonthlySummary) totalDao
						.getObjectByCondition(
								"from MonthlySummary where name='开票及时率' and month=?",
								monthApply + "月");
				boolean had1 = true;
				if (monthlySummary1 == null) {
					monthlySummary1 = new MonthlySummary();
					monthlySummary1.setAddTime(Util.getDateTime());
					monthlySummary1.setMonth(monthApply + "月");
					monthlySummary1.setName("开票及时率");
					monthlySummary1.setNumber("kpjsr");
					had1 = false;
				}
				// 该月已开票数量
				Float hadCount = (Float) totalDao
						.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('回款中','回款完成') and checkTime like '"
								+ monthApply + "%'");
				// 该月开票数量
				Float allKpCount = (Float) totalDao
						.getObjectByCondition("select count(*) from TaHkHuikuan where hkStatus in ('开票申请','审批中','开票','回款中','回款完成') and checkTime like '"
								+ monthApply + "%'");
				if (hadCount == null) {
					hadCount = 0f;
				}
				if (allKpCount == null || allKpCount == 0) {
					monthlySummary1.setCompletionRate(0f);
				} else {
					monthlySummary1.setCompletionRate(hadCount / allKpCount);
				}
				if (had1) {
					totalDao.update(monthlySummary1);
				} else {
					totalDao.save(monthlySummary1);
				}
				// 根据送货单号查到对应的出库记录
				if (sendNumberList.size() > 0) {
					for (String sendNUmber : sendNumberList) {
						Float money = (Float) totalDao
								.getObjectByCondition(
										"select sellSendCost from Sell where sellSendnum=? and sellSendCost!=null",
										sendNUmber);
						if (money != null && money > 0) {
							List<Object[]> markIdAndCountList = totalDao
									.query(
											"select sellMarkId,sum(sellCount) from Sell where sellSendnum=? group by sellMarkId",
											sendNUmber);
							if (markIdAndCountList != null
									&& markIdAndCountList.size() > 0) {
								// List<Object[]> markIdAndMoneyList=new
								// ArrayList<Object[]>();
								Float totalMoney = 0f;
								for (Object[] markIdAndCount : markIdAndCountList) {
									if (markIdAndCount[0] != null
											&& markIdAndCount[1] != null) {
										Float selfMoney = (Float) totalDao
												.getObjectByCondition(
														"select sum(money) from ProjectTime where quoId in (select id from QuotedPrice where markId=? and procardStyle='总成') and classNumber ='bzys' ",
														markIdAndCount[0]
																.toString());
										if (selfMoney != null && selfMoney > 0) {
											selfMoney = selfMoney
													* Float
															.parseFloat(markIdAndCount[1]
																	.toString());
											totalMoney = totalMoney + selfMoney;
											// Object[] markIdAndMoney =new
											// Object[]{markIdAndCount[0].toString(),selfMoney};
											// markIdAndMoneyList.add(markIdAndMoney);
										}
									}
								}
								List<Sell> sellLsit = totalDao.query(
										" from Sell where sellSendnum=?",
										sendNUmber);
								if (sellLsit.size() > 0) {
									for (Sell sell : sellLsit) {
										QuotedPrice qp = (QuotedPrice) totalDao
												.getObjectByCondition(
														"from QuotedPrice where markId=? and procardStyle='总成'",
														sell.getSellMarkId());
										if (qp != null) {
											QuotedPriceCost quotedPriceCost = new QuotedPriceCost();
											quotedPriceCost
													.setSendNumber(sendNUmber);
											quotedPriceCost
													.setProStatus("批产阶段");
											quotedPriceCost.setTzMoney(0d);
											if (sell.getSellAdminId() != null) {
												Users user = (Users) totalDao
														.getObjectById(
																Users.class,
																sell
																		.getSellAdminId());
												quotedPriceCost
														.setUserName(user
																.getName());
												quotedPriceCost
														.setUserCode(user
																.getCode());
												quotedPriceCost.setDept(user
														.getDept());
											} else {
												quotedPriceCost
														.setUserName(sell
																.getSellAdminName());
											}
											quotedPriceCost
													.setApplyStatus("同意");
											quotedPriceCost.setSource("运费申报");
											quotedPriceCost
													.setCostType("物流运输费");
											// quotedPriceCost.setProStatus(qp.getStatus());
											quotedPriceCost.setMarkId(qp
													.getMarkId());
											quotedPriceCost.setSelfCard(sell
													.getSellLot());
											quotedPriceCost.setQpId(qp.getId());
											// 重新计算零件总费用
											Float selfMoney = (Float) totalDao
													.getObjectByCondition(
															"select sum(money) from ProjectTime where quoId in (select id from QuotedPrice where markId=? and procardStyle='总成') and classNumber ='bzys' ",
															qp.getMarkId());
											if (selfMoney != null
													&& selfMoney > 0) {
												Double realAllfy = (double) money
														* selfMoney
														* sell.getSellCount()
														/ totalMoney;
												quotedPriceCost
														.setMoney(realAllfy);
												if (qp.getRealAllfy() != null) {
													qp
															.setRealAllfy(realAllfy
																	+ qp
																			.getRealAllfy());
												} else {
													qp.setRealAllfy(realAllfy);
												}
												if (qp.getYingkui() == null) {
													qp.setYingkui(0 - qp
															.getRealAllfy());
												} else {
													qp
															.setYingkui(qp
																	.getYingkui()
																	- quotedPriceCost
																			.getMoney());
												}
												totalDao.update(qp);
												quotedPriceCost
														.setApplyStatus("同意");
												quotedPriceCost.setAddTime(Util
														.getDateTime());
												totalDao.save(quotedPriceCost);
												Procard procard = (Procard) totalDao
														.getObjectByCondition(
																"from Procard where markId=? and selfCard=?",
																sell
																		.getSellMarkId(),
																sell
																		.getSellLot());
												if (procard != null) {
													if (procard.getBzysfei() != null) {
														procard
																.setBzysfei(Float
																		.parseFloat((procard
																				.getBzysfei() + quotedPriceCost
																				.getMoney())
																				+ ""));
													} else {
														procard
																.setBzysfei(Float
																		.parseFloat(quotedPriceCost
																				.getMoney()
																				+ ""));
													}
													totalDao.update(procard);
												}
											}
										}
									}
								}
							}
						}
					}
				}

				return "true";
			}
		}
		return null;
	}

	@Override
	public List<TaHkSellSta> showKaiPiaoDetail(Integer id, String orderNum) {
		// TODO Auto-generated method stub
		List<TaHkSellSta> taHkSellStas = (List<TaHkSellSta>) totalDao.query(
				"from TaHkSellSta where hkSellOrderId=? and taHkHuikuan.id=?",
				orderNum, id);
		if (taHkSellStas.size() > 0) {
			for (TaHkSellSta t : taHkSellStas) {
				Object[] objs = (Object[]) totalDao
						.getObjectByCondition(
								"select sum(hkbmCount),sum(hkbmMoney) from TaHkPartBackMoney where hkmarkId =? and hkSellOrderId=? and backMoneyId in (select id from TaHkBackMoney where taHkHuikuan.id=(select taHkHuikuan.id from TaHkSellSta where id =?))",
								t.getHkSellMarkId(), t.getHkSellOrderId(), t
										.getId());
				if (objs != null && objs.length > 0) {
					if (objs[0] != null) {
						Float count = Float.parseFloat(objs[0].toString());
						t.setHkcount(count);

					} else {
						t.setHkcount(0f);
					}
					if (objs[1] != null) {
						Float money = Float.parseFloat(objs[1].toString());
						t.setHkmoney(money);
					} else {
						t.setHkmoney(0f);
					}
				}
			}
			return taHkSellStas;
		}
		return null;
	}

	@Override
	public List<TaHkBackMoney> showBackMoney(Integer id, String orderNum) {
		// TODO Auto-generated method stub
		return (List<TaHkBackMoney>) totalDao
				.query(
						"from TaHkBackMoney where taHkHuikuan.id=? and id in (select backMoneyId from TaHkPartBackMoney where hkSellOrderId=?)",
						id, orderNum);
	}

	@Override
	public List<TaHkPartBackMoney> showBackMoneyDatail(Integer id,
			String orderNum, String markId) {
		// TODO Auto-generated method stub
		if (markId != null && !markId.equals("")) {// 有markId时id为发票id
			return (List<TaHkPartBackMoney>) totalDao
					.query(
							"from TaHkPartBackMoney where backMoneyId in (select id from TaHkBackMoney where taHkHuikuan.id=?) and hkSellOrderId=? and hkmarkId=?",
							id, orderNum, markId);

		} else {// 没有markId时id为回款单id
			return (List<TaHkPartBackMoney>) totalDao
					.query(
							"from TaHkPartBackMoney where backMoneyId=? and hkSellOrderId=?",
							id, orderNum);
		}
	}

	@Override
	public List<TaHkHuikuan> showKaiPiaoDan(String outOrderNumber, String markId) {
		// TODO Auto-generated method stub
		return (List<TaHkHuikuan>) totalDao
				.query(
						"from TaHkHuikuan where hkStatus in ('未核对','可开票','回款中','回款完成') and id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId=? and hkSellMarkId=?)",
						outOrderNumber, markId);
	}

	@Override
	public void calculateLastTime() {
		// TODO Auto-generated method stub
		// 计算回款期限
		List<TaHkHuikuan> list = totalDao
				.query("from TaHkHuikuan where hkStatus in('回款中','回款完成') and lastTime is null");
		if (list.size() > 0) {
			for (TaHkHuikuan taHkHuikuan : list) {
				if (taHkHuikuan.getHkPayCycle() != null
						&& taHkHuikuan.getHkBillTime() != null) {// 计算回款期限
					Date date1 = Util.StringToDate(taHkHuikuan.getHkBillTime(),
							"yyyy-MM-dd");
					Long time1 = 0l;
					if (date1 != null) {
						time1 = date1.getTime();
					}
					Long addTime = taHkHuikuan.getHkPayCycle() * 24 * 3600 * 1000l;
					taHkHuikuan.setLastTime(Util.DateToString(new Date(time1
							+ addTime), "yyyy-MM-dd"));
					totalDao.update(taHkHuikuan);
				}
			}
		}
		// 计算回款完成的发票的完成时间
		List<TaHkHuikuan> list2 = totalDao
				.query("from TaHkHuikuan where hkStatus ='回款完成' and overTime is null");
		if (list2.size() > 0) {
			for (TaHkHuikuan taHkHuikuan : list2) {
				String maxDate = (String) totalDao
						.getObjectByCondition(
								"select max(hkbmDate) from TaHkBackMoney where taHkHuikuan.id=?",
								taHkHuikuan.getId());
				taHkHuikuan.setOverTime(maxDate);
				totalDao.update(taHkHuikuan);
			}
		}
	}

	@Override
	public List<TaHkHuikuan> findFaPiao(Integer id, String tag) {
		// TODO Auto-generated method stub
		if (id != null && tag != null) {
			if (tag.equals("p")) {
				return totalDao
						.query(
								"from TaHkHuikuan where id in (select taHkHuikuan.id from TaHkSellSta where hkSellOrderId=(select orderManager.orderNum from ProductManager where id=?))",
								id);
			} else if (tag.equals("f")) {
				TaHkHuikuan huikuan = (TaHkHuikuan) totalDao.getObjectById(
						TaHkHuikuan.class, id);
				if (huikuan != null) {
					List<TaHkHuikuan> list = new ArrayList<TaHkHuikuan>();
					list.add(huikuan);
					return list;
				}
			}
		}
		return null;
	}

	@Override
	public List<SubBudgetRate> findBankSub(Users user) {
		String hql_sub = "";
		List<SubBudgetRate> subList = null ;
		if(user == null){
			hql_sub = " from SubBudgetRate where subNumber  like '1001%' or  subNumber LIKE '1002%'";
			subList =  totalDao.query(hql_sub);
		}else{
			hql_sub = " from SubBudgetRate where id in ( select S.id  from SubBudgetRate S join S.userSet U  where U.id =?)  ";
			 subList =  totalDao.query(hql_sub, user.getId());
		}
		return subList;
	}

}
