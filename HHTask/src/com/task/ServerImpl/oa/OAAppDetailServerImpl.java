package com.task.ServerImpl.oa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.runtime.Name;
import com.task.Dao.TotalDao;
import com.task.Server.SmsService;
import com.task.Server.oa.OAAppDetailServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.OaAppDetail;
import com.task.entity.OaAppDetailTemplate;
import com.task.entity.OaApplyForm;
import com.task.entity.OaMessageAlerm;
import com.task.entity.OaPrepareApply;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.menjin.MarkStatusType;
import com.task.entity.menjin.WareBangGoogs;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.pro.Pro;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.CompanyInfo;
import com.task.util.RtxUtil;
import com.task.util.Util;
import com.task.util.UtilTong;

@SuppressWarnings("unchecked")
public class OAAppDetailServerImpl implements OAAppDetailServer {
	private CircuitRunServer circuitRunServer;
	private SmsService smsService;
	private static Integer duankou = 8885;
	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public List getNameBymingcheng(String tag, String powerTag) {
		List procardTemplates = totalDao
				.query("select distinct(detailAppName) from OaAppDetail where detailChildClass ='"
						+ tag
						+ "' and (detailAppName like '%"
						+ powerTag
						+ "%' or detailFormat like '%" + powerTag + "%')");
		return procardTemplates;
	}

	/*
	 * public String findchildClass(String tag, String powerTag) { // TODO
	 * Auto-generated method stub String message = "  |"; String hql =
	 * "select distinct(detailAppName) from OaAppDetail where detailChildClass='"
	 * + tag + "'"; if ("project".equals(powerTag)) { // String hqlPro =
	 * "from Pro where code=?"; // List listPro = totalDao.query(hqlPro, tag);
	 * // if (listPro.size() > 0 && null != listPro) { // Pro pro = (Pro)
	 * listPro.get(0); // hql =
	 * "select distinct(name) from ProTooling where proId=" // + pro.getId(); //
	 * } hql =
	 * "select gongzhuang from QuotedProcessInfor where isOld ='no' and quotedPrice.id in (select id from QuotedPrice where proId="
	 * + tag + ")";
	 * 
	 * } List<String> list = totalDao.query(hql); for (String d : list) { if
	 * (null != d && d.length() > 0) { message += d.toString() + "|"; }
	 * 
	 * } return message; }
	 */
	/**
	 * 查找申报明细记录
	 */
	public Object[] findOADetail(OaAppDetail oadetail, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		Users user = Util.getLoginUser();
		String hql = "from OaAppDetail where 1=1 ";
		if ("self".equals(tag)) {
			hql += " and detailAppDept='" + user.getDept() + "'";
		} else if ("buy".equals(tag)) {
			// hql+=" and detailAppDept='"+user.getDept()+"'";
		}
		if (null != oadetail) {
			if (null != oadetail.getDetailAppName()
					&& !"".equals(oadetail.getDetailAppName())) {
				hql += " and detailAppName like'%"
						+ oadetail.getDetailAppName() + "%'";
			}
			if (null != oadetail.getDetailFormat()
					&& !"".equals(oadetail.getDetailFormat())) {
				hql += " and detailFormat like'%" + oadetail.getDetailFormat()
						+ "%'";
			}
			if (null != oadetail.getDetailSeqNum()
					&& !"".equals(oadetail.getDetailSeqNum())) {
				hql += " and detailSeqNum like'%" + oadetail.getDetailSeqNum()
						+ "%'";
			}
			if (null != oadetail.getDetailChildClass()
					&& !"".equals(oadetail.getDetailChildClass())) {
				hql += " and detailChildClass='"
						+ oadetail.getDetailChildClass() + "'";
			}
			if (null != oadetail.getDetailPlanMon()
					&& !"".equals(oadetail.getDetailPlanMon())) {
				hql += " and detailPlanMon='" + oadetail.getDetailPlanMon()
						+ "'";
			}
			if (null != oadetail.getDetailAppDept()
					&& !"".equals(oadetail.getDetailAppDept())) {
				hql += " and detailAppDept='" + oadetail.getDetailAppDept()
						+ "'";
			}
			if (null != oadetail.getDetailStatus()
					&& !"".equals(oadetail.getDetailStatus())) {
				hql += " and detailStatus='" + oadetail.getDetailStatus() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and detailAppDate between '" + startDate + "' and '"
					+ endDate + "'";
		}
		Integer count = totalDao.getCount(hql);
		hql += " order by detailPlanMon desc,detailAppDate desc";
		Object[] detailAarr = new Object[2];

		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		detailAarr[0] = count;
		detailAarr[1] = list;
		return detailAarr;
	}

	// ajax 根据属性查找下拉
	@Override
	public String findOASelect(String tag, String powerTag) {
		String message = "";
		Users user = Util.getLoginUser();
		if (null != tag && !"".equals(tag)) {
			String hql = "";
			if ("self".equals(powerTag)) {
				hql = "select distinct(" + tag
						+ ") from OaAppDetail where detailAppDept='"
						+ user.getDept() + "'";
			}/*
			 * else if("buy".equals(powerTag)){ hql = "select distinct(" + tag +
			 * ") from OaPrepareApply "; }
			 */else {
				hql = "select distinct(" + tag + ") from OaAppDetail ";
			}
			hql+=" order by "+tag +" desc";
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				if (null != d && d.length() > 0) {
					message += d.toString() + "|";
				}
			}
		}
		return message;
	}

	// 预申请 判断有无预算记录
	@Override
	public boolean preSaveDetail() {
		Users user = Util.getLoginUser();
		String hql = " from DeptMonthBudget where status='同意' and userDept='"
				+ user.getDept()
				+ "' and accountMoney>realMoney order by budgetMonth desc";
		Integer count = totalDao.getCount(hql);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 添加申购名称和规格
	 * 
	 * @param oadetail
	 * @return
	 */
	@Override
	public boolean saveKemu(OaAppDetail oadetail) {
		if (null != oadetail) {
			return totalDao.save(oadetail);
		}
		return false;
	}

	// ajax 下拉预算月份
	@Override
	public String findSelectMon(String tag) {
		Users user = Util.getLoginUser();
		String message = "";
		String hql = "select distinct(budgetMonth) from DeptMonthBudget where userDept='"
				+ user.getDept()
				+ "' and status='同意' and accountMoney>realMoney";// and
		// status='同意'
		// 状态暂时可以先不限制
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			message += d.toString() + "|";
		}
		return message;
	}

	// 根据预算月份查找预算科目
	@Override
	public List findchildSubjects(String tag, String planMonth) {
		String hql = "";
		if ("planMonth".equals(tag)) {
			Users user = Util.getLoginUser();
			hql = "from DeptMonthBudget where budgetMonth='" + planMonth
					+ "' and userDept='" + user.getDept() + "' and status='同意'";// and
			// status='同意'
			// 状态暂时可以先不限制

		} else if ("projectNO".equals(tag)) {
			hql = " from Pro";
		}
		List<DeptMonthBudget> list = totalDao.query(hql);
		if (list != null && list.size() > 0) {
			for (DeptMonthBudget db : list) {
				String sql = "select sum(detailCount*detailBudgetMoney) from OaAppDetail where detailStatus in('预申请','同意','入库','审批','审批中','采购中') and deptMonthBudgetID="
						+ db.getId();
				Float oaMoney = (Float) totalDao.getObjectByCondition(sql);
				if (oaMoney == null) {
					oaMoney = 0f;
				}
				if (db.getRealMoney() != null) {
					db.setRealMoney2(db.getRealMoney() + oaMoney);
				}
			}
		}
		return list;
	}

	// ajax 下拉查找物品名称
	@Override
	public String findchildClass(String tag, String powerTag) {
		// TODO Auto-generated method stub
		String message = "  |";
		String hql = "select distinct(detailAppName) from OaAppDetail where detailChildClass='"
				+ tag + "'";
		if ("project".equals(powerTag)) {
			// String hqlPro = "from Pro where code=?";
			// List listPro = totalDao.query(hqlPro, tag);
			// if (listPro.size() > 0 && null != listPro) {
			// Pro pro = (Pro) listPro.get(0);
			// hql = "select distinct(name) from ProTooling where proId="
			// + pro.getId();
			// }
			hql = "select gongzhuang from QuotedProcessInfor where isOld ='no' and quotedPrice.id in (select id from QuotedPrice where proId="
					+ tag + ")";

		}
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			if (null != d && d.length() > 0) {
				message += d.toString() + "|";
			}

		}
		return message;
	}

	// ajax 下拉查找物品规格（根据类别和物品名称级联查询）
	@Override
	public String findFormat(String tag, String powerTag) {
		// TODO Auto-generated method stub
		String message = "";
		String hql = "select distinct(detailFormat) from OaAppDetail where detailChildClass='"
				+ tag + "' and detailAppName='" + powerTag + "'";
		List<String> list = totalDao.query(hql);
		for (String d : list) {
			if (null != d && d.length() > 0) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	/**
	 * 根据项目查找工装规格
	 * 
	 * @param tag
	 *            项目编号
	 * @param powerTag
	 *            工装名称
	 * @return
	 */
	@Override
	public String findFormatByProject(String tag, String powerTag) {
		String message = "";
		String hqlPro = "from Pro where code=?";
		List listPro = totalDao.query(hqlPro, tag);
		if (listPro.size() > 0 && null != listPro) {
			Pro pro = (Pro) listPro.get(0);
			String hql = "select distinct(specification) from ProTooling where proId="
					+ pro.getId() + "and name=?";
			List<String> list = totalDao.query(hql, powerTag);
			for (String d : list) {
				if (null != d && d.length() > 0) {
					message += d.toString() + "|";
				}
			}
		}
		
		return message;
	}

	// 预算金额控制
	@Override
	public String compareBudgetCount(Integer id, Float money, Integer id1) {
		DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
				DeptMonthBudget.class, id);
		double realM = 0;
		if (null != dmb.getRealMoney()) {
			realM = dmb.getRealMoney();// 实际花费金额
		}
		double mo_budget = dmb.getAccountMoney() - realM;// 剩余可决算金额
		String hql = "select sum(detailCount*detailBudgetMoney) from OaAppDetail where detailStatus in('预申请','同意','入库','审批','审批中','采购中') and deptMonthBudgetID="
				+ id;
		List<Float> list = totalDao.query(hql);
		if (null != list && list.size() > 0 && null != list.get(0)) {
			money += (Float) list.get(0);// 总的实际花费的金额
		}
		String message = "NO";
		if (mo_budget >= money) {
			message = "OK";
		}
		return message;
	}

	// 添加申报记录 单条申请
	@Override
	public String saveOADetail(OaAppDetail oadetail) {
		
		if (null != oadetail) {
			Users user = Util.getLoginUser();
			String curdate = Util.getDateTime("yyyyMMdd");
			int mm = Integer.parseInt(curdate.substring(4, 6)) + 1;
			int yy = Integer.parseInt(curdate.substring(0, 4));
			if (mm == 13) {
				mm = 1;
				yy += 1;
			}
			String monm = String.valueOf(mm);// 月份
			if (mm < 10) {
				monm = 0 + "" + mm;
			}
			String planMon = yy + "-" + monm;// 计划月份
			String ordNumber = user.getPassword().getDeptNumber() + yy + monm;// 标识
			
			// 物品编码=====================================
			String seqNum = "";
			String bianhao = retuGodsId(oadetail.getDetailChildClass())
					+ planMon.replaceAll("-", "");// 部门编码+月份
			String hqlMaxId = "select max(detailSeqNum) from OaAppDetail where detailSeqNum like '"
					+ bianhao + "%'";
			List listD = totalDao.query(hqlMaxId);
			if (listD.size() > 0 && null != listD.get(0)) {
				String maxId = String.valueOf(Integer.parseInt(((String) listD
						.get(0)).substring(retuGodsId(oadetail.getDetailChildClass()).length()+6)) + 1);
				if (maxId.length() == 1) {
					maxId = "00" + maxId;
				} else if (maxId.length() == 2) {
					maxId = "0" + maxId;
				}
				seqNum = bianhao + maxId;
			} else {
				seqNum = bianhao + "001";
			}
			
			//String search_hql = "from OaAppDetail where detailAppName=? and wlcode=? ";
			//List<OaAppDetail> query = totalDao.query(search_hql, oadetail.getDetailAppName(), oadetail.getWlcode());
			boolean saveBool = true;
			boolean updateBool = true;
			//OaAppDetail oaAppDetail = null;
			/*if(null!=query && query.size()>0){  //已经存在这个编号，进入修改操作
				oaAppDetail = query.get(0);
				oaAppDetail.setDetailBudgetMoney(oadetail.getDetailBudgetMoney());
				oaAppDetail.setDetailCount(oaAppDetail.getDetailCount()+oadetail.getDetailCount());
				//采购数量
				oaAppDetail.setDetailReceveCount(oaAppDetail.getDetailCount()-oaAppDetail.getRgdetailCount());
				//oaAppDetail.setRgdetailCount(oaAppDetail.get)//-oaAppDetail.getRgdetailCount()
				oaAppDetail.setDetailArrDate(oadetail.getDetailArrDate());
				oaAppDetail.setDetailIsBusy(oadetail.getDetailIsBusy());
				oaAppDetail.setDetailPlanAcco(oadetail.getDetailPlanAcco());
				oaAppDetail.setDetailClass(oadetail.getDetailClass()); //类型 项目--普通
				oaAppDetail.setDetailItemId(oadetail.getDetailItemId());//项目编号
				
				oaAppDetail.setDetailPlanMon(planMon);// 计划月份
				oaAppDetail.setDetailOrdNumber(ordNumber);// 编号
				oaAppDetail.setDetailAppDate(Util.getDateTime());// 申请时间
				
				oaAppDetail.setDetailSeqNum(seqNum);// 物品编号
				oaAppDetail.setDetailAppDept(user.getDept());// 部门
				oaAppDetail.setDetailStatus("预申请");
				oaAppDetail.setStatus("未入完");
				totalDao.update(oaAppDetail);
				
			}else{//添加
*/				oadetail.setDetailPlanMon(planMon);// 计划月份
				oadetail.setDetailOrdNumber(ordNumber);// 编号
				oadetail.setDetailAppDate(Util.getDateTime());// 申请时间
				
				oadetail.setDetailReceveCount(oadetail.getDetailCount()); //采购总量
				oadetail.setDetailSeqNum(seqNum);// 物品编号
				oadetail.setDetailAppDept(user.getDept());// 部门
				oadetail.setDetailStatus("预申请");
				oadetail.setStatus("未入完");
				oadetail.setRgdetailCount(0f);//已入数据
				String hql = "from OaPrepareApply where appPlanMon=? and appDept=?";//OaPrepareApply采购申请
				List list = totalDao.query(hql, planMon, user.getDept());
				OaPrepareApply opa = null;
				if (list.size() > 0 && list != null) {
					opa = (OaPrepareApply) list.get(0);// 获取对象
				} else {
					opa = new OaPrepareApply();// 创建对象
					opa.setAppApplier(user.getName());
					opa.setAppDept(user.getDept());
					opa.setAppTimer(Util.getDateTime());
					opa.setAppPlanMon(planMon);// 计划月份
					opa.setAppOrdnumber(ordNumber);// 编号
					opa.setAppBarcode(user.getPassword().getDeptNumber()
							+ Util.getDateTime("yyyyMMdd"));
					opa.setAppDate(Util.getDateTime("yyyy-MM-dd"));
					// appNumber
					String hqlNum = "select max(appNumber) from OaPrepareApply";
					List listNum = totalDao.query(hqlNum);
					if (listNum.size() > 0 && null != listNum.get(0)) {// 在原来的基础上加1
						String Maxbianhao = (String) listNum.get(0);
						opa.setAppNumber(bianhao(Maxbianhao));
					} else {// 初始化
						opa.setAppNumber("PD-P0-0000001");
					}
					
					totalDao.save(opa);
				}
				oadetail.setPrepareApply(opa);
				saveBool = totalDao.save(oadetail);
			//}
			
			if (saveBool && updateBool) {// 判断审批流程
//				String processName = user.getDept() + oadetail.getDetailClass();// 普通申购，项目申购
				String processName = oadetail.getDetailClass();// 普通申购，项目申购
				if ("A".equals(oadetail.getAppayTag())) {
					processName += "个人采购审批流程";
				} else if ("B".equals(oadetail.getAppayTag())) {
					processName += "经理采购审批流程";
				} else if ("C".equals(oadetail.getAppayTag())) {
					processName += "副总经理采购审批流程";
				} else {
					processName += "个人采购审批流程";
				}

				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							OaAppDetail.class, oadetail.getId(),
							"detailStatus", "id", null);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e.getMessage());
				}
				if (epId != null && epId > 0) {
					/*if(null!=query && query.size()>0){
						oaAppDetail.setEpId(epId);
						totalDao.update(oaAppDetail);
					}else{*/
						oadetail.setEpId(epId);
						totalDao.update(oadetail);
					//}
				}

				// 判断发送提醒消息
				Integer userIds[] = CircuitRunServerImpl.findAuditUserId(epId,1);
				for (int i = 0; i < userIds.length; i++) {
					Integer userId = userIds[i];
					Users acessUser = (Users) totalDao.getObjectById(
							Users.class, userId);
					// 判断发送提醒消息
					String sendDate = Util.getDateTime("yyyy-MM-dd");
					String hqlMessage = "from OaMessageAlerm where oaUserName='"
							+ user.getName()
							+ "' and sendDate='"
							+ sendDate
							+ "' and accessPhone=?";
					String phone = acessUser.getPassword().getPhoneNumber();
					if (totalDao.query(hqlMessage, phone).size() <= 0) {
						String msg = user.getDept() + "部门" + planMon
								+ "采购计划请您审批！";
						// 发送短信提醒
						OaMessageAlerm mess = new OaMessageAlerm();
						mess.setOaUserName(user.getName());
						mess.setContent(msg);
						mess.setAccessPhone(phone);
						mess.setRealName(user.getName());
						mess.setSendDate(sendDate);
						totalDao.save(mess);
						AlertMessagesServerImpl.addAlertMessages("采购审批", msg,
								"采购审批", acessUser.getCode(), phone);
					}
				}
			}
			if(saveBool && updateBool){
				return "true";
			}
		}
		return "添加失败";
	}
	
	//根据编号添加申报记录
	@Override
	public boolean saveOADetail2(OaAppDetail oadetail) {
		if (null != oadetail) {
			Users user = Util.getLoginUser();
			String curdate = Util.getDateTime("yyyyMMdd");
			int mm = Integer.parseInt(curdate.substring(4, 6)) + 1;
			int yy = Integer.parseInt(curdate.substring(0, 4));
			if (mm == 13) {
				mm = 1;
				yy += 1;
			}
			String monm = String.valueOf(mm);// 月份
			if (mm < 10) {
				monm = 0 + "" + mm;
			}
			String planMon = yy + "-" + monm;// 计划月份
			String ordNumber = user.getPassword().getDeptNumber() + yy + monm;// 标识
			String hql = "from OaPrepareApply where appPlanMon=? and appDept=?";
			List list = totalDao.query(hql, planMon, user.getDept());

			oadetail.setDetailPlanMon(planMon);// 计划月份
			oadetail.setDetailOrdNumber(ordNumber);// 编号
			oadetail.setDetailAppDate(Util.getDateTime());// 申请时间
			// 物品编码=====================================
			String seqNum = "";
			String bianhao = retuGodsId(oadetail.getDetailChildClass())
					+ planMon.replaceAll("-", "");// 部门编码+月份
			String hqlMaxId = "select max(detailSeqNum) from OaAppDetail where detailSeqNum like '"
					+ bianhao + "%'";
			List listD = totalDao.query(hqlMaxId);
			if (listD.size() > 0 && null != listD.get(0)) {
				String maxId = String.valueOf(Integer.parseInt(((String) listD
						.get(0)).substring(10)) + 1);
				if (maxId.length() == 1) {
					maxId = "00" + maxId;
				} else if (maxId.length() == 2) {
					maxId = "0" + maxId;
				}
				seqNum = bianhao + maxId;
			} else {
				seqNum = bianhao + "001";
			}
			oadetail.setDetailSeqNum(seqNum);// 物品编号
			oadetail.setDetailAppDept(user.getDept());// 部门
			oadetail.setDetailStatus("预申请");
			oadetail.setStatus("未入完");
			OaPrepareApply opa = null;
			if (list.size() > 0 && list != null) {
				opa = (OaPrepareApply) list.get(0);// 获取对象
			} else {
				opa = new OaPrepareApply();// 创建对象
				opa.setAppApplier(user.getName());
				opa.setAppDept(user.getDept());
				opa.setAppTimer(Util.getDateTime());
				opa.setAppPlanMon(planMon);// 计划月份
				opa.setAppOrdnumber(ordNumber);// 编号
				opa.setAppBarcode(user.getPassword().getDeptNumber()
						+ Util.getDateTime("yyyyMMdd"));
				opa.setAppDate(Util.getDateTime("yyyy-MM-dd"));
				// appNumber
				/*String hqlNum = "select max(appNumber) from OaPrepareApply";
				List listNum = totalDao.query(hqlNum);*/
				if (list.size() > 0 && null != list.get(0)) {// 在原来的基础上加1
					String Maxbianhao = (String) list.get(0);
					opa.setAppNumber(bianhao(Maxbianhao));
				} else {// 初始化
					opa.setAppNumber("PD-P0-0000001");
				}
				totalDao.save(opa);
			}
			oadetail.setPrepareApply(opa);
			DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
					DeptMonthBudget.class, oadetail.getDeptMonthBudgetID());
			float realM = oadetail.getDetailBudgetMoney()
					* oadetail.getDetailCount();
			if (null != dmb.getRealMoney()) {
				realM += dmb.getRealMoney();// 计算实际报销金额
			}
			// dmb.setRealMoney(realM);
			// totalDao.update(dmb);
			boolean bool = totalDao.save(oadetail);
			if (bool) {// 判断审批流程
//				String processName = user.getDept() + oadetail.getDetailClass();// 普通申购，项目申购
				String processName = oadetail.getDetailClass();// 普通申购，项目申购
				if ("A".equals(oadetail.getAppayTag())) {
					processName += "个人采购审批流程";
				} else if ("B".equals(oadetail.getAppayTag())) {
					processName += "经理采购审批流程";
				} else if ("C".equals(oadetail.getAppayTag())) {
					processName += "副总经理采购审批流程";
				} else {
					processName += "个人采购审批流程";
				}

				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							OaAppDetail.class, oadetail.getId(),
							"detailStatus", "id", null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (epId != null && epId > 0) {
					oadetail.setEpId(epId);
					totalDao.update(oadetail);
				}

				// 判断发送提醒消息
				Integer userIds[] = CircuitRunServerImpl.findAuditUserId(epId,
						1);
				for (int i = 0; i < userIds.length; i++) {
					Integer userId = userIds[i];
					Users acessUser = (Users) totalDao.getObjectById(
							Users.class, userId);
					// 判断发送提醒消息
					String sendDate = Util.getDateTime("yyyy-MM-dd");
					String hqlMessage = "from OaMessageAlerm where oaUserName='"
							+ user.getName()
							+ "' and sendDate='"
							+ sendDate
							+ "' and accessPhone=?";
					String phone = acessUser.getPassword().getPhoneNumber();
					if (totalDao.query(hqlMessage, phone).size() <= 0) {
						String msg = user.getDept() + "部门" + planMon
								+ "月采购计划请您审批！";
						// 发送短信提醒
						OaMessageAlerm mess = new OaMessageAlerm();
						mess.setOaUserName(user.getName());
						mess.setContent(msg);
						mess.setAccessPhone(phone);
						mess.setRealName(user.getName());
						mess.setSendDate(sendDate);
						totalDao.save(mess);
						AlertMessagesServerImpl.addAlertMessages("采购审批", msg,
								"采购审批", acessUser.getCode(), phone);
					}
				}
			}
			return bool;
		}
		return false;
	}

	// 返回物品编号
	public String retuGodsId(String str) {
		String godsId = "";
		if ("金属五交材料".equals(str)) {
			godsId = "SMT";
		} else if ("备件".equals(str)) {
			godsId = "SPT";
		} else if ("杂品".equals(str)) {
			godsId = "OTT";
		} else if ("办公用品".equals(str)) {
			godsId = "OFT";
		} else if ("包装物".equals(str)) {
			godsId = "LPT";
		} else if ("工具".equals(str)) {
			godsId = "TOT";
		} else if ("工装".equals(str)) {
			godsId = "FKT";
		} else if ("金属五交".equals(str)) {
			godsId = "SMT";
		} else if ("五金".equals(str)) {
			godsId = "SMT";
		}
		return godsId;
	}

	// 返回申报编号
	String bianhao(String num) {
		String t1 = num.substring(6);
		Integer t2 = Integer.parseInt(t1);
		t2 += 1;
		String t3 = String.valueOf(t2);
		int length = t1.length() - t3.length();
		String t4 = "0000000";
		String t5 = t4.substring(0, length);
		return "PD-P0-" + t5 + t3;
	}

	// 根据ID删除申报明细
	@Override
	public boolean deleteOADetailById(OaAppDetail oadetail) {
		boolean boo = false;
		/*DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
				DeptMonthBudget.class, oadetail.getDeptMonthBudgetID());
		float realM = oadetail.getDetailBudgetMoney()
				* oadetail.getDetailCount();*/
		// dmb.setRealMoney(dmb.getRealMoney() - realM);
		// boo = totalDao.update(dmb);
		boo = totalDao.delete(oadetail);
		return boo;
	}

	// 根据ID获取OaDetail 对象
	@Override
	public OaAppDetail getOADetaailById(Integer id) {
		// TODO Auto-generated method stub
		if (null != id) {
			return (OaAppDetail) totalDao.getObjectById(OaAppDetail.class, id);
		}
		return null;
	}

	// 更新申报明细
	@Override
	public boolean updateOADetail(OaAppDetail oadetail, String tag) {
		boolean boo = false;
		if ("update".equals(tag)) {// 更新操作
			// 获得原始对象
			OaAppDetail oad_old = (OaAppDetail) totalDao.getObjectById(
					OaAppDetail.class, oadetail.getId());
			if (oad_old.getDeptMonthBudgetID() != null) {
				int budgetId_old = oad_old.getDeptMonthBudgetID();// 部门预算ID
				int budgetID_new = oadetail.getDeptMonthBudgetID();// 新部门预算ID
				// 老预算删除，新预算加上
				DeptMonthBudget dmb_old = (DeptMonthBudget) totalDao
						.getObjectById(DeptMonthBudget.class, budgetId_old);
				float realM_old = oad_old.getDetailBudgetMoney()
						* oad_old.getDetailCount();
				// dmb_old.setRealMoney(dmb_old.getRealMoney() - realM_old);
				// boo = totalDao.update(dmb_old);
				// 新预算处理
				DeptMonthBudget dmb_new = (DeptMonthBudget) totalDao
						.getObjectById(DeptMonthBudget.class, budgetId_old);

				float realM_new = oadetail.getDetailBudgetMoney()
						* oadetail.getDetailCount();
				// if (null != dmb_new.getRealMoney()) {
				// dmb_old.setRealMoney(dmb_new.getRealMoney() + realM_new);
				// }
				// boo = totalDao.update(dmb_new);
			}
			// 处理明细
			oad_old.setDetailAppName(oadetail.getDetailAppName());
			oad_old.setDetailChildClass(oadetail.getDetailChildClass());
			oad_old.setDetailFormat(oadetail.getDetailFormat());
			oad_old.setDetailBudgetMoney(oadetail.getDetailBudgetMoney());
			oad_old.setDetailCount(oadetail.getDetailCount());
			oad_old.setDetailUnit(oadetail.getDetailUnit());
			oad_old.setDetailPlanAcco(oadetail.getDetailPlanAcco());
			oad_old.setBudgetMonth(oadetail.getBudgetMonth());// 预算月份
			oad_old.setDeptMonthBudgetID(oadetail.getDeptMonthBudgetID());
			oad_old.setDetailItemId(oadetail.getDetailItemId());
			oad_old.setDetailIsBusy(oadetail.getDetailIsBusy());
			oad_old.setDetailArrDate(oadetail.getDetailArrDate());
			if ("打回".equals(oad_old.getDetailStatus())) {
				oad_old.setDetailStatus("预申请");
			}
			// oad_old.setDetailStatus("预申请");
			boo = totalDao.update(oad_old);
			if(boo){
				//CircuitRunServerImpl.create
			}
			return boo;
		}
		return false;
	}

	
	
	// 批量上传 添加申报记录
	@Override
	public String saveLotUpload(File uploadDetail, OaAppDetail oadetail) {
		// TODO Auto-generated method stub
		String addMessage = "";
		String runMessage = "";
		if (null != oadetail) {
			int number = 0;
			String filName = oadetail.getBudgetMonth()
					+ Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			// 上传到服务器
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/oa/detail")
					+ "/" + filName;
			// 备份到项目
			
			String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/oa/detail"
					+ "/" + filName;
			File file = new File(fileRealPath);
			File beifenFile = new File(beiFenfileRealPath);
			try {
				FileCopyUtils.copy(uploadDetail, file);
				try {
					FileCopyUtils.copy(uploadDetail, beifenFile);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 开始读取excel表格
				InputStream is = new FileInputStream(fileRealPath);// 创建文件流
				jxl.Workbook rwb = Workbook.getWorkbook(is);// 创建workBook
				Sheet rs = rwb.getSheet(0);// 获得第一张Sheet表
				int excelcolumns = rs.getRows();// 获得总行数
				// 获得部门申报对象
				DeptMonthBudget dms = (DeptMonthBudget) totalDao.getObjectById(
						DeptMonthBudget.class, oadetail.getDeptMonthBudgetID());
				// 预算月份，申报类型，项目编号
				String budgetMonth = oadetail.getBudgetMonth();
				String sbStyle = oadetail.getDetailClass();// 申报类型
				String projectId = null;// 项目编号
				float sumMoney = 0f;// 申报预算总金额
				double budgetMoney = dms.getAccountMoney();// 预算金属

				if ("项目申购".equals(sbStyle)&&oadetail.getDetailItemId()!=null) {
					try {
						projectId = (String)totalDao.getObjectByCondition("select projectNum from ProjectManage where  id=?", Integer.parseInt(oadetail.getDetailItemId()));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				// 物品编号 部门编号+年月
				Users user = Util.getLoginUser();
				String planMon = Util.getNextMonth("yyyyMM");// 计划月份
				String ordNumber = user.getPassword().getDeptNumber() + planMon;// 标识
				String hql = "from OaPrepareApply where appPlanMon=? and appDept=?";
				List list = totalDao.query(hql, planMon, user.getDept());
				OaPrepareApply opa = null; // 预申请表
				if (list.size() > 0 && list != null) {
					opa = (OaPrepareApply) list.get(0);// 获取对象
				} else {
					opa = new OaPrepareApply();// 创建对象
					opa.setAppApplier(user.getName());
					opa.setAppDept(user.getDept());
					opa.setAppTimer(Util.getDateTime());
					opa.setAppPlanMon(planMon);// 计划月份
					opa.setAppOrdnumber(ordNumber);// 编号
					opa.setAppBarcode(user.getPassword().getDeptNumber()
							+ Util.getDateTime("yyyyMM"));
					// appNumber
					String hqlNum = "select max(appNumber) from OaPrepareApply";
					List listNum = totalDao.query(hqlNum);
					if (listNum.size() > 0 && null != listNum.get(0)) {// 在原来的基础上加1
						String Maxbianhao = (String) listNum.get(0);
						opa.setAppNumber(bianhao(Maxbianhao));
					} else {// 初始化
						opa.setAppNumber("PD-P0-0000001");
					}
					totalDao.save(opa);
				}

				if (excelcolumns > 3) {
					for (int i = 3; i < excelcolumns; i++) {
						
					}
					// 遍历结束，
					if (number == 0 && sumMoney > 0) {
						// 预算控制
						String hqlYUsuan = "select sum(detailCount*detailBudgetMoney) from OaAppDetail where detailStatus<>'付款' and  deptMonthBudgetID="
								+ dms.getId();
						List listYUSUAN = totalDao.query(hqlYUsuan);
						if (null != listYUSUAN && listYUSUAN.size() > 0
								&& null != listYUSUAN.get(0)) {
							sumMoney += (Float) listYUSUAN.get(0);
						}
						if (sumMoney > budgetMoney) {// 无法执行
							addMessage = "申报金额超出可用预算金额"
									+ (budgetMoney - sumMoney) + "元";
						} else {// 再次处理保存到数据库
							// 重新遍历保存到数据库
							float sumD = 0f;// 申报金额
							for (int i = 3; i < excelcolumns; i++) {
								Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
								String name = cells[0].getContents();// 获得物品名称
								if (name != null && name.length() > 0) {
									String childClass = cells[1].getContents();// 获得物品类别
									String format = cells[2].getContents();// 获得物品规格
									String count = cells[4].getContents();// 获得申报数量
									String yiju = cells[5].getContents();// 获得计划依据
									String arrivalDate = cells[6].getContents();// 获得到货期限
									String price = cells[7].getContents();// 获得预算单价
									String unit = cells[8].getContents();// 获得单位
									String isjiaji = cells[9].getContents();// 获得是否加急
									Float countF = Float.parseFloat(count);// 数量
									Float priceF = Float.parseFloat(price);// 单价
									sumD += countF * priceF;
									// 保存明细表

									OaAppDetail detail = new OaAppDetail();
									detail.setBudgetMonth(budgetMonth);// 预算月份
									detail.setDetailClass(sbStyle);// 申购类型
									detail.setDetailAppDept(user.getDept());// 部门
									detail.setDetailAppName(name);// 物品名称
									detail.setDetailChildClass(childClass);// 物品类型
									detail.setDetailFormat(format);// 规格
									detail.setDetailCount(countF);// 数量
									detail.setDetailAppDate(Util.getDateTime());// 申请时间
									detail.setDetailBudgetMoney(priceF);// 预算单价
									detail.setDetailPlanAcco(yiju);// 计划依据
									detail.setDetailIsBusy(isjiaji);// 是否加急
									detail.setDetailUnit(unit);// 单位
									detail.setDetailItemId(projectId);// 项目编号
									detail.setDetailOrdNumber(ordNumber);// 编号
									detail.setDetailPlanMon(planMon);// 计划月份
									detail.setDetailArrDate(arrivalDate);
									detail.setDetailStatus("预申请");
									detail.setStatus("未入完");
									detail.setDeptMonthBudgetID(oadetail
											.getDeptMonthBudgetID());
									detail.setPrepareApply(opa);
									// 生成物品编号
									String seqNum = "";
									String bianhao = retuGodsId(childClass)
											+ planMon.replaceAll("-", "");// 部门编码+月份
									String hqlMaxId = "select max(detailSeqNum) from OaAppDetail where detailSeqNum like '"
											+ bianhao + "%'";
									List listD = totalDao.query(hqlMaxId);
									if (listD.size() > 0 && null != listD.get(0)) {
										String maxId = String.valueOf(Integer.parseInt(((String) listD.get(0)).substring(10)) + 1);
										if (maxId.length() == 1) {
											maxId = "00" + maxId;
										} else if (maxId.length() == 2) {
											maxId = "0" + maxId;
										}
										seqNum = bianhao + maxId;
									} else {
										seqNum = bianhao + "001";
									}
									detail.setDetailSeqNum(seqNum);// 物品编号

									totalDao.save(detail);
									// 判断审批流程
									try {
//										String processName = user.getDept()
//												+ oadetail.getDetailClass();
										String processName = oadetail.getDetailClass();
										if ("A".equals(oadetail.getAppayTag())) {
											processName += "个人采购审批流程";
										} else if ("B".equals(oadetail
												.getAppayTag())) {
											processName += "经理采购审批流程";
										} else if ("C".equals(oadetail
												.getAppayTag())) {
											processName += "副总经理采购审批流程";
										}
										// 添加审批流程
										Integer epId = CircuitRunServerImpl
												.createProcess(processName,
														OaAppDetail.class,
														detail.getId(),
														"detailStatus", "id",
														null);// false
										// 是否发手机短信消息，邮件
										if (epId != null && epId > 0) {
											detail.setEpId(epId);
											totalDao.update(detail);
											// 判断发送提醒消息
											Integer userIds[] = CircuitRunServerImpl
													.findAuditUserId(epId, 1);
											for (int j = 0; j < userIds.length; j++) {
												Integer userId = userIds[j];
												Users acessUser = (Users) totalDao
														.getObjectById(
																Users.class,
																userId);
												// 判断发送提醒消息
												String sendDate = Util
														.getDateTime("yyyy-MM-dd");
												String hqlMessage = "from OaMessageAlerm where oaUserName='"
														+ user.getName()
														+ "' and sendDate='"
														+ sendDate
														+ "' and accessPhone=?";
												String phone = acessUser
														.getPassword()
														.getPhoneNumber();
												if (totalDao.getCount(
														hqlMessage, phone) <= 0) {
													String msg = user.getDept()
															+ "部门" + planMon
															+ "月采购计划请您审批！";
													// 发送短信提醒
													OaMessageAlerm mess = new OaMessageAlerm();
													mess.setOaUserName(user.getName());
													mess.setContent(msg);
													mess.setAccessPhone(phone);
													mess.setRealName(user.getName());
													mess.setSendDate(sendDate);
													totalDao.save(mess);
													AlertMessagesServerImpl
															.addAlertMessages(
																	"采购审批",
																	msg,
																	"采购审批",
																	acessUser.getCode(),
																	phone);
												}
											}
										}

									} catch (Exception e) {
										e.printStackTrace();
									}
								} else {
									break;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				number++;
				runMessage += "数据有" + number + "处问题!<br>";
			}

			return addMessage + runMessage;

		}
		return null;
	}

	// 管理预申请信息表
	@Override
	public Object[] findPreApp(OaPrepareApply opApply, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		Object[] pre = new Object[3];
		Users user = Util.getLoginUser();
		List listP = new ArrayList();// 待打印申报信息
		String hql = "";
		if ("self".equals(tag)) {// 个人查看本部门申报记录
			hql = " from OaPrepareApply op where op.appDept='" + user.getDept()
					+ "'";
		} else if ("buy".equals(tag)) {// 采购查看已打印过多历史记录
			hql = " from OaPrepareApply op where op.appOrdnumber in(select distinct(d.detailOrdNumber) from OaAppDetail d where d.detailStatus in('采购中','入库','付款','报账中','打回','可付款','已付款'))";
			String hqlPrint = " from OaPrepareApply where appOrdnumber in(select distinct(detailOrdNumber) from OaAppDetail where detailStatus='同意')";
			listP = totalDao.query(hqlPrint);
		} else if ("manager".equals(tag)) {// 管理员查看所有
			hql = " from OaPrepareApply op where op.appOrdnumber in(select distinct(d.detailOrdNumber) from OaAppDetail d where d.detailStatus<>'同意')";
			String hqlPrint = " from OaPrepareApply op where op.appOrdnumber in(select distinct(d.detailOrdNumber) from OaAppDetail d where d.detailStatus='同意')";
			listP = totalDao.query(hqlPrint);
		}

		/*
		 * if(null!=opApply){ if(null!=opApply.getAppDept() &&
		 * !"".equals(opApply.getAppDept())){ hql+=" and " } }
		 */
		if (null != opApply) {
			// 部门不为空
			if (null != opApply.getAppDept()
					&& !"".equals(opApply.getAppDept())) {
				hql += " and appDept='" + opApply.getAppDept() + "'";
			}
			// 计划月份不为空
			if (null != opApply.getAppPlanMon()
					&& !"".equals(opApply.getAppPlanMon())) {
				hql += " and appPlanMon='" + opApply.getAppPlanMon() + "'";
			}
			// 编号不为空
			if (null != opApply.getAppOrdnumber()
					&& !"".equals(opApply.getAppOrdnumber())) {
				hql += " and appOrdnumber like'%" + opApply.getAppOrdnumber()
						+ "%'";
			}
			// 条码不为空
			if (null != opApply.getAppBarcode()
					&& !"".equals(opApply.getAppBarcode())) {
				hql += " and appBarcode='" + opApply.getAppBarcode() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and op.appDate between '" + startDate + "' and '"
					+ endDate + "'";
		}
		hql += " order by appPlanMon desc";
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		pre[0] = count;
		pre[1] = list;
		pre[2] = listP;
		return pre;
	}

	// 查看单条预申请内的申报明细
	@Override
	public Object[] findPreAppDetail(OaAppDetail oadetail, Integer id,
			String startDate, String endDate, Integer cpage, Integer pageSize,
			String tag, String powerTag) {
		Users user = Util.getLoginUser();
		OaPrepareApply opa = (OaPrepareApply) totalDao.getObjectById(
				OaPrepareApply.class, id);
		String hql = "from OaAppDetail where detailOrdNumber='"
				+ opa.getAppOrdnumber() + "'";
		if ("print".equals(tag)) {
			hql += " and detailStatus='同意'";
		} else {
			if ("buy".equals(powerTag)) {
				hql += " and detailStatus in('同意','采购中','入库','付款','报账中')";
			}
		}
		if (null != oadetail) {
			if (null != oadetail.getDetailAppName()
					&& !"".equals(oadetail.getDetailAppName())) {
				hql += " and detailAppName like'%"
						+ oadetail.getDetailAppName() + "%'";
			}
			if (null != oadetail.getDetailFormat()
					&& !"".equals(oadetail.getDetailFormat())) {
				hql += " and detailFormat like'%" + oadetail.getDetailFormat()
						+ "%'";
			}
			if (null != oadetail.getDetailSeqNum()
					&& !"".equals(oadetail.getDetailSeqNum())) {
				hql += " and detailSeqNum like'%" + oadetail.getDetailSeqNum()
						+ "%'";
			}
			if (null != oadetail.getDetailClass()
					&& !"".equals(oadetail.getDetailClass())) {
				hql += " and detailClass like'%" + oadetail.getDetailClass()
						+ "%'";
			}
			if (null != oadetail.getDetailPlanMon()
					&& !"".equals(oadetail.getDetailPlanMon())) {
				hql += " and detailPlanMon='" + oadetail.getDetailPlanMon()
						+ "'";
			}
			if (null != oadetail.getDetailAppDept()
					&& !"".equals(oadetail.getDetailAppDept())) {
				hql += " and detailAppDept='" + oadetail.getDetailAppDept()
						+ "'";
			}
			if (null != oadetail.getStatus()
					&& !"".equals(oadetail.getStatus())) {
				hql += " and detailStatus='" + oadetail.getStatus() + "'";
			}
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and detailAppDate between '" + startDate + "' and '"
					+ endDate + "'";
		}
		hql += " order by detailPlanMon desc,detailAppDate desc";
		Object[] detailAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		detailAarr[0] = count;
		detailAarr[1] = list;
		return detailAarr;
	}

	// 打印采购执行单 采购
	@Override
	public List findPrintList(Integer id, String tag) {
		// TODO Auto-generated method stub
		OaPrepareApply opa = (OaPrepareApply) totalDao.getObjectById(
				OaPrepareApply.class, id);
		String hql = "from OaAppDetail where detailOrdNumber='"
				+ opa.getAppOrdnumber() + "'";
		if ("print".equals(tag)) {// 直接打印
			hql += " and detailStatus='同意' order by id desc";
		} else if ("select".equals(tag)) {// 选择补打
			hql += " and detailStatus in('同意','采购中','入库','付款','报账中') order by id desc";
		}
		return totalDao.query(hql);
	}

	// 根据ID获取预申请对象
	@Override
	public OaPrepareApply getPreById(Integer id) {
		return (OaPrepareApply) totalDao
				.getObjectById(OaPrepareApply.class, id);
	}

	// 打印采购执行单操作
	@Override
	public String updatePrintStatus(Integer id, String tag) {
		OaPrepareApply opa = (OaPrepareApply) totalDao.getObjectById(
				OaPrepareApply.class, id);
		String hql = "from OaAppDetail where detailOrdNumber=? and detailStatus=?";
		String hqlAplyForm = "from OaApplyForm where aplyFormOrdnumber=?";
		OaApplyForm oaf;
		List listoaf = totalDao.query(hqlAplyForm, opa.getAppOrdnumber());
		if (listoaf.size() > 0 && null != listoaf) {
			oaf = (OaApplyForm) listoaf.get(0);
		} else {
			oaf = new OaApplyForm();
			oaf.setAplyFormOrdnumber(opa.getAppOrdnumber());
			oaf.setAppFormApplier(opa.getAppApplier());
			oaf.setAppFormDept(opa.getAppDept());
			oaf.setAppFormDate(Util.getDateTime("yyyy-MM-dd"));
			oaf.setAppFormTimer(Util.getDateTime());
			oaf.setAppPlanMon(opa.getAppPlanMon());
			oaf.setAppBarcode(opa.getAppBarcode());
			oaf.setAppFormNumber(opa.getAppNumber());
			totalDao.save(oaf);
		}
		List<OaAppDetail> listDetail = totalDao.query(hql, opa
				.getAppOrdnumber(), "同意");
		if (listDetail.size() > 0 && null != listDetail) {
			for (OaAppDetail od : listDetail) {
				//od.setDetailStatus("采购中");
				od.setDetailStatus("待入库");
				od.setApplyForm(oaf);
				totalDao.update(od);
			}

		}
		return "";
	}

	// 补打采购执行单。选择补选明细 显示到打印页面
	@Override
	public List findSelectedDetail(Integer[] detailSelect, String tag) {
		/*
		 * List list = new ArrayList(); for (int i = 0; i < detailSelect.length;
		 * i++) { OaAppDetail detail = (OaAppDetail) totalDao.getObjectById(
		 * OaAppDetail.class, detailSelect[i]); if (detail != null) {
		 * list.add(detail); } } return list;
		 */
		if (null != detailSelect) {
			String hql = "from OaAppDetail where id in(:test) ";
			Query query = totalDao.createQuery(hql);
			query.setParameterList("test", detailSelect);
			return query.list();
		}
		return null;
	}

	// 查看审批历史记录
	@Override
	public Object[] findExamHistoryList(OaAppDetail oadetail, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		// 返回条件 明细ID

		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				OaAppDetail.class, true);
		String hql = "from OaAppDetail where id in (:entityId)";
		List list = totalDao.find(hql, map, cpage, pageSize);
		Object[] examHistory = new Object[2];
		Long countLong = totalDao.count("select count(*) " + hql, map);
		int count = Integer.parseInt(countLong.toString());
		examHistory[0] = count;
		examHistory[1] = list;
		return examHistory;
	}

	// 查看需要审批的明细
	@Override
	public Object[] findExamList(Integer cpage, Integer pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				OaAppDetail.class, false);
		if (map != null) {
			String hql = "from OaAppDetail where id in (:entityId)";
			List list = totalDao.find(hql, map, cpage, pageSize);
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
				OaAppDetail detail = (OaAppDetail) totalDao.getObjectById(
						OaAppDetail.class, detailSelect[i]);
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
											+ "部门申购计划请您审批！";
									// 发送短信提醒
									OaMessageAlerm mess = new OaMessageAlerm();
									mess.setOaUserName(user.getName());
									mess.setContent(msg);
									mess.setAccessPhone(phone);
									mess.setRealName(user.getName());
									mess.setSendDate(sendDate);
									totalDao.save(mess);
									AlertMessagesServerImpl.addAlertMessages(
											"采购审批", msg, "采购审批", acessUser
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
					detail.setDetailStatus("打回");
					detail.setDetailNextSign(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	@Override
	public List findNoApproval(Users users, boolean bool) {
		List list = null;
		if (users != null) {
			String hql2 = "from Users where code=?";
			Users loginUser = (Users) totalDao.getObjectByCondition(hql2, users
					.getCode());
			// 保存用户到session中
			ActionContext.getContext().getSession().put(TotalDao.users,
					loginUser);
			list = CircuitRunServerImpl.findAudit(bool);
		}
		return list;
	}

	@Override
	public boolean updateExamOADetail1(Integer[] detailSelect, String tag,
			String opinion, Users users) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			String hql = "from Users where code=?";
			Users loginUser = (Users) totalDao.getObjectByCondition(hql, users
					.getCode());
			// Users users = (Users)
			// totalDao.getObjectByCondition(hql,users.getCode());
			ActionContext.getContext().getSession().put(TotalDao.users,
					loginUser);
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				CircuitRun detail = (CircuitRun) totalDao.getObjectById(
						CircuitRun.class, detailSelect[i]);
				if ("ok".equals(tag)) {// 同意
					CircuitRun circuitRun = circuitRunServer
							.findCircuitRunById(detail.getId());
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getId(), true, opinion, true, null, true);
					if ("审批成功".equals(audit)) {

					}
					// 更改明细状态
				} else {// 打回
					// 处理审批流程
					// 处理审批流程
					circuitRunServer.updateExeNodeByCirId(detail.getId(),
							false, "", false, null, false);
					detail.setAuditStatus("打回");
					// detail.setDetailNextSign(user.getName());
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 获取验证码(non-Javadoc)
	 * 
	 * @see com.task.Server.oa.OAAppDetailServer#send(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean send(String number, String code, String cus_id) {
		boolean b = false;
		HttpServletResponse response = ServletActionContext.getResponse();
		// Users user = (Users) Util.getLoginUser();
		Cookie numberCookie = new Cookie(code + "_yzm", number);
		numberCookie.setMaxAge(60 * 10);// 存入cookie十分钟
		response.addCookie(numberCookie);
		PaymentVoucher paymentVoucher = circuitRunServer
				.findPaymentVoucher(Integer.parseInt(cus_id));
		String ms = "";
		String hql = "from Users where code=?";
		Users user = (Users) this.totalDao.getObjectByCondition(hql, code);
		String phone = user.getPassword().getPhoneNumber();
		if (paymentVoucher != null) {
			// String essage = smsService
			// .send(
			// phone,
			// "序列号:"
			// + paymentVoucher.getNumber()
			// + "  验证码为："
			// + number
			// + "(借款凭证验证依据，切勿告知他人),请在文本框中输入已完成验证，有问题请致电021-59567057【PEBS】");
			// b = true;
			ms = "序列号:"
					+ paymentVoucher.getNumber()
					+ "  验证码为："
					+ number
					+ "(借款凭证验证依据，切勿告知他人),请在文本框中输入已完成验证，有问题请致电021-59567057【PEBS】";
		} else {
			ms = "您的验证码为："
					+ number
					+ "(系统审批验证依据，切勿告知他人),请在文本框中输入已完成验证，有问题请致电021-59567057【PEBS】";
			;
		}
		b = RtxUtil.sendNotify(code, ms, "系统消息", "0", "0");

		return b;
	}

	public SmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(SmsService smsService) {
		this.smsService = smsService;
	}

	@Override
	public Object[] findOADetail_1(OaAppDetail oadetail, Integer cpage,
			Integer pageSize, String powerTag, String tag) {
		// TODO Auto-generated method stub
		if (oadetail == null) {
			oadetail = new OaAppDetail();
		}
		Users users = Util.getLoginUser();
		String sql = "";
		String hql = totalDao.criteriaQueries(oadetail, sql);
		if ("dept".equals(powerTag)) hql += " and detailAppDept='" + users.getDept() + "'";
		if ("gz".equals(tag)) hql += " and detailChildClass='工装'";
		hql += " and detailStatus in ('采购中','到货','报账') and (status = '未入完' or status is null) order by id desc";
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List<WarehouseNumber> findAllWNList_1() {
		return 	totalDao.query("from WarehouseNumber order by id desc");//oaAppDetail_list_daohuo.jsp
	}

	@Override
	public Object[] upfindOACaiGouRuGuiBacode(String bacode, String mxId,
			String tag) {
		// TODO Auto-generated method stub
		boolean b = false;
		if (bacode != null) {
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectByCondition("from WarehouseNumber where barCode = ?", bacode);
			if (wN==null) return null;
			List<OaAppDetail> listwdd = new ArrayList<OaAppDetail>();
			String hql = "from WareBangGoogs where fk_oadetail_id = ? and status = '待存入' ";
			if ("无".equals(wN.getHave())&&!"开".equals(wN.getKwStatus())) {
				String[] mxStr = mxId.split(",");
				for (String s : mxStr) {
					OaAppDetail od = (OaAppDetail) totalDao.getObjectById(OaAppDetail.class, Integer.parseInt(s));
					if (od==null) throw new RuntimeException("选择采购明细不存在,请检查后重试!");
					else {//查询此物品有没有待存入状态
						int ie = totalDao.getCount(hql, od.getId());
						if(ie==0){
							//添加中间表 建立关系
							WareBangGoogs bwg = new WareBangGoogs();
							bwg.setFk_oadetail_id(od.getId());//采购明细单ID
							bwg.setFk_ware_id(wN.getId());
							bwg.setNumber(od.getDetailCount());//可操作数量
							bwg.setTowCode(UUID.randomUUID().toString());
							bwg.setStatus("待存入");
							if(totalDao.save(bwg)){//添加成功后 更新库位状态。
								listwdd.add(od);
								b = true;
							}else {
								Object[] o = { "关系保存失败，请重试！" }; return o;
							}
						}else throw new RuntimeException("您尚有未关闭的库位，请先关闭后再进行存物操作！");
					}
				}
			}else {
				if ("有".equals(wN.getHave())&&!"开".equals(wN.getKwStatus())) {
					if ("未满".equals(wN.getStatus())&&("工装".equals(wN.getMarkTyptName()))) {
						String[] mxStr = mxId.split(",");
						for (String s : mxStr) {
							OaAppDetail od = (OaAppDetail) totalDao.getObjectById(OaAppDetail.class, Integer.parseInt(s));
							if (od==null) throw new RuntimeException("选择采购明细不存在,请检查后重试!");
							else {//查询此物品有没有待存入状态
								int ie = totalDao.getCount(hql, od.getId());
								if(ie==0){
									//添加中间表 建立关系
									WareBangGoogs bwg = new WareBangGoogs();
									bwg.setFk_oadetail_id(od.getId());//采购明细单ID
									bwg.setFk_ware_id(wN.getId());
									bwg.setNumber(od.getDetailCount());//可操作数量
									bwg.setTowCode(UUID.randomUUID().toString());
									bwg.setStatus("待存入");
									if(totalDao.save(bwg)){//添加成功后 更新库位状态。
										listwdd.add(od);
										b = true;
									}else {
										Object[] o = { "关系保存失败，请重试！" }; return o;
									}
								}else throw new RuntimeException("您尚有未关闭的库位，请先关闭后再进行存物操作！");
							}
						}
					}else {
						Object[] o = { "库位已满或此库位不为工装库，开门失败！" }; return o;
					}
				}else {
					Object[] o = { "库位已打开，请先关闭！" }; return o;
				}
			}
			if (b) {
				//返回主页面
				Util.backTowMa(wN.getIp(), duankou);
				sleeps(1000);
				//开门
				String messages = UtilTong.OCKuWei(wN.getIp(), duankou, true, wN.getOneNumber(), wN.getNumid());//开关门操作
				if ("true".equals(messages)) {
					wN.setKwStatus("开");
					wN.setCzUserId(Util.getLoginUser().getId());
					wN.setSczTime(Util.getDateTime());
					totalDao.update(wN);//更新状态
					Object[] o = { messages, listwdd, wN}; return o;
				}else {
					//Object[] o = { "开门失败，请检查网络！" }; 
					throw new RuntimeException("开门失败，请检查网络！");
				}
			}else {
				Object[] o = { "信息保存有误，开门失败！" }; return o;
			}
		}
		return null;
	}
	@Override
	public WarehouseNumber byIdWN(Integer id) {
		// TODO Auto-generated method stub
		return (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, id);
	}

	@Override
	public String updateGZToRG(List<OaAppDetail> list, WarehouseNumber wn) {
		// TODO Auto-generated method stub
		String messages = UtilTong.OCKuWei(wn.getIp(), duankou, false, wn.getOneNumber(), wn.getNumid());//关门操作
		if ("true".equals(messages)) {
			wn.setSczTime(Util.getDateTime());
			wn.setCzUserId(null);
			wn.setKwStatus("关");
			for (OaAppDetail wdd : list) {
				OaAppDetail detail = getOADetaailById(wdd.getId());
				if (detail==null) continue;
				else {
					WareBangGoogs bwg = (WareBangGoogs) totalDao.getObjectByCondition("from WareBangGoogs where fk_oadetail_id = ? and fk_ware_id = ? and status = '待存入' ", detail.getId(),wn.getId());
					if (bwg!=null) {
						boolean b = false;
						boolean b1 = false;
						if(detail.getRgdetailCount()==null){//第一次存
							if(wdd.getRgdetailCount()<detail.getDetailCount()){//小于
								bwg.setNumber(wdd.getRgdetailCount());
								if (wdd.getRgdetailCount()<=0) {
									detail.setRgdetailCount(0f);
									totalDao.delete(bwg);
									b1=false;
									continue;
								}else {
									detail.setRgdetailCount(wdd.getRgdetailCount());
									wn.setHave("有");
									b=true;
									bwg.setStatus("已入柜");
									totalDao.update(bwg);
								}
							}else {//大于等于
								wn.setHave("有");
								b=true;
								bwg.setStatus("已入柜");
								totalDao.update(bwg);
								detail.setDetailStatus("待入库");
								detail.setWareWei(wn.getNumber());//存入后库位位置
								detail.setRgdetailCount(wdd.getRgdetailCount());//入柜数量
							}
						}else {//非首次存入
							if(wdd.getRgdetailCount()+detail.getRgdetailCount()<detail.getDetailCount()){//小于
								bwg.setNumber(wdd.getRgdetailCount());
								if (wdd.getRgdetailCount()<=0) {
									detail.setRgdetailCount(0f);
									totalDao.delete(bwg);
									b1=false;
									continue;
								}else {
									if (detail.getRgdetailCount()>0) detail.setWareWei(detail.getWareWei()+","+wn.getNumber());//存入后库位位置
									else  detail.setWareWei(wn.getNumber());//存入后库位位置
									detail.setRgdetailCount(wdd.getRgdetailCount()+detail.getRgdetailCount());
									wn.setHave("有");
									b=true;
									bwg.setStatus("已入柜");
									totalDao.update(bwg);
								}
							}else {//大于等于
								wn.setHave("有");
								b=true;
								bwg.setStatus("已入柜");
								totalDao.update(bwg);
								detail.setDetailStatus("待入库");
								if (detail.getRgdetailCount()>0) detail.setWareWei(detail.getWareWei()+","+wn.getNumber());//存入后库位位置
								else detail.setWareWei(wn.getNumber());//存入后库位位置
								detail.setRgdetailCount(wdd.getRgdetailCount()+detail.getRgdetailCount());
							}
						}
						if(b&&"空".equals(wn.getMarkTyptName())){
							//改变库位中物品的状态
							if (b1) wn.setStatus("已满");
							updateWN(wn, "工装");
						}else {
							wn.setHave("无");
							wn.setStatus("未满");
						}
						totalDao.update(detail);
					}
				}
			}
			totalDao.update(wn);//更新库位信息
			//更新亮灯的颜色
			//messages = Util.openColorXin(wn.getFourlightIp(), duankou, false, false, wn.getNumid(), wn.getMarkTypt()==null ? 0:wn.getMarkTypt().getMarkColor());
			//灭灯
			UtilTong.closeLight(wn.getIp(), duankou, wn.getOneNumber(), wn.getNumid());
			//亮灯的颜色
			messages = UtilTong.openColorLight(wn.getIp(), duankou, true, true, wn.getOneNumber(), wn.getNumid(), wn.getMarkTypt()==null ? 0:wn.getMarkTypt().getMarkColor());
			
			sleeps(500);
			//发送详细屏幕信息
			AttendanceTowServerImpl.sendPin_1(wn);
		}
		return messages;
	}
	
	/**
	 * 更改状态
	 */
	public WarehouseNumber updateWN(WarehouseNumber wn,String s){
		//改变库位中物品的状态
		MarkStatusType mst = (MarkStatusType) totalDao.getObjectByCondition("from MarkStatusType where markTypt = ?",s);
		if(mst!=null){
			wn.setMarkTyptName(mst.getMarkTypt());
			wn.setMarkTypt(mst);
		}else {
			wn.setMarkTyptName(s);
			wn.setMarkTypt(null);
		}
		return wn;
	}
	
	//休眠n毫秒
	public void sleeps(int i){
		try {Thread.sleep(i); } catch (InterruptedException e) { e.printStackTrace(); }
	}

	@Override
	public String Addoadetail(File oadetailFile) {

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
			FileCopyUtils.copy(oadetailFile, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}

			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				List<Integer> strList = new ArrayList<Integer>();
				StringBuffer strb = new StringBuffer();
				Integer errorCount = 0;// 错误数量
				Integer cfCount = 0;// 重复数量
				Integer successCount = 0;// 成功数量
				for (i = 1; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					String a = cells[1].getContents();// 物料编码
					String b = cells[2].getContents();// 物品类别
					String c = cells[3].getContents();// 物品名称
					String d = cells[4].getContents();// 物品规格
					String e = cells[5].getContents();// 计量单位
					if(a==null || a.length()==0){
						cfCount++;
						errorCount++;
						strb.append((i+1)+"行,没有填写物料编码。<br/>");
						continue;
					}
					if(c==null || c.length()==0){
						cfCount++;
						errorCount++;
						strb.append((i+1)+"行,没有填写物品名称。<br/>");
						continue;
					}
					//判断重复
					String hql_cf = " from OaAppDetail where wlcode = ? and detailFormat = ? and detailAppName = ?";
					OaAppDetail oa = 	(OaAppDetail) totalDao.getObjectByCondition(hql_cf, a,d,c);
					if(oa!=null){
						cfCount++;
						errorCount++;
						strb.append((i+1)+"行，物料编码："+a+"物品名称:"+c+"，重复添加。<br/>");
						continue;
					}
					OaAppDetail oa1 = new OaAppDetail();
					
					oa1.setWlcode(a);
					oa1.setDetailChildClass(b);
					oa1.setDetailAppName(c);
					oa1.setDetailFormat(d);
					oa1.setDetailUnit(e);
					if(totalDao.save(oa1)){
						
						
						
						successCount++;
					}
					
				}

				is.close();// 关闭流
				wk.close();// 关闭工作薄
				msg = "已成功导入" + successCount + "个<br>失败" + errorCount + "个<br>重复"
						+ cfCount + "个<br>失败的行数分别为：<br>" + strb.toString();
			} else {
				msg = "没有获取到行数";
			}

		} catch (Exception e) {
			msg = "导入出错,请截屏发给管理员!<br>" + e.getMessage() + (i + 1) + "行";
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String scfuliaoOaApply() {
		// TODO Auto-generated method stub
		return null;
	}

	//设置批量导入模板的内容
	@Override
	public String settingOaUploadTemplate() {
		WritableWorkbook book= null;
		try {
			String path = ServletActionContext.getServletContext().getRealPath(File.separator);
			//CompanyInfo companyInfo = (CompanyInfo) ServletActionContext.getRequest().getSession().getAttribute("companyInfo");
			HttpServletRequest request = ServletActionContext.getRequest();
			CompanyInfo companyInfo = Util.getLoginCompanyInfo();
			
			Workbook wb=Workbook.getWorkbook(new File(path+"upload/sheet/OA/OAUploadTemplate.xls"));
			
			book =Workbook.createWorkbook(new File(path+"upload/sheet/OA/OAUploadTemplate.xls"),wb);
			//WritableSheet sheet=book.createSheet("page2",1);
			WritableSheet sheet = book.getSheet(0); 
			
			String imageFilepath = path.substring(0, path.length()-1)+companyInfo.getLogoOKjpg();
			WritableImage wrimage=new WritableImage(107,10,382,382,new File(imageFilepath ));
			sheet.addImage(wrimage); 
			
			
			WritableFont wfont = 
				new WritableFont(WritableFont.createFont("楷书"), 24,WritableFont.BOLD);
			WritableCellFormat wc = new WritableCellFormat(wfont);
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc.setBackground(Colour.OCEAN_BLUE); // 设置单元格的背景颜色OCEAN_BLUE、、LIGHT_BLUE
			Label label = new Label(2,0,companyInfo.getName(), wc);
			sheet.addCell(label);
			
			book.write(); 
			book.close(); 
			return "true";
		} catch (Exception e) {
			try {
				book.write(); 
				book.close();
			} catch (WriteException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			e.printStackTrace();
			return "false";
		}
		
	}

	//批量上传采购申请
	@Override
	public String saveBatchOADetail(OaAppDetail oadetail, File oadetailFile) {
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file") + "\\" + fileName;
		File file = new File(fileRealPath);
		try {
			FileCopyUtils.copy(oadetailFile, file);
			Object[] obj = readXLS(fileRealPath);
			List<OaAppDetail> list =(List<OaAppDetail>) obj[0];
			//String errorMessage = "";
			int successCount = 0;
			int errorCount = 0;
			if(null!=list && list.size()!=0 ){
				for (OaAppDetail oaAppDetail : list) {
					/*System.out.println(oaAppDetail);
					//检查数据重复   是否是项目
					String existsHql = "from OaAppDetail where wlcode = ?";
					List<OaAppDetail> existsList =  totalDao.query(existsHql, oaAppDetail.getWlcode());
					if(null!=existsList && existsList.size()>0){
						//这条物料编码的物品已经存在，不执行保存
						errorMessage+=oaAppDetail.getWlcode()+"、";
					}else{*/
						//oaAppDetail.setDetailChildClass(oadetail.getDetailChildClass());
						if(null!=oadetail.getDetailItemId() ){
							oaAppDetail.setDetailItemId(oadetail.getDetailItemId());
						}
						if(null==oadetail.getDetailItemId() || "".equals(oadetail.getDetailItemId())){
							oaAppDetail.setDetailClass("普通申购");
						}else{
							oaAppDetail.setDetailItemId(oadetail.getDetailItemId());
							oaAppDetail.setDetailClass("项目申购");
						}
						
						String detail = saveOADetail(oaAppDetail);
						if("true".equals(detail)){
							successCount++;
						}
					//}
				}
			}else{
				if("".equals(obj[1].toString())){
					return "没有获取到行数据";
				}
				return obj[1].toString();//"没有获取到行数据<br>"+
			}
			/*if(errorMessage.length()>0){
				errorMessage = errorMessage.substring(0, errorMessage.length()-1);
				return "添加"+successCount+"条数据成功！<br>"+obj[1].toString();//，其中有物料编码为"+errorMessage+"的物品重复已被过滤<br>
			}*/
			return "添加"+successCount+"条数据成功！<br>"+obj[1].toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "导入出错,请截屏发给管理员!<br>" + e.getMessage();
		}
	}
	
	private Object[] readXLS(String fileRealPath)throws Exception{
		Object[] returns = new Object[2];
		List<OaAppDetail> list = new ArrayList<OaAppDetail>();
		jxl.Workbook wk = null;
		StringBuffer appendBuffer = null;
		int i = 0;
		String errorMessage = "";
		try {
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums >3) {
				OaAppDetail detail = null;
				for (i = 3; i < exclecolums; i++) {
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					detail = new OaAppDetail();
					appendBuffer = new StringBuffer();
					String appName = cells[1].getContents().trim(); // 获得物品名称
					if(null==appName || appName.isEmpty())
						appendBuffer.append("物品名称不能为空、");
					else
						detail.setDetailAppName(appName);
					String code = cells[0].getContents().trim();  //获得物料编码
					if(null==code || code.isEmpty()){
						appendBuffer.append("物料编码不能为空、");
					}else{
						//判断物料编码是否存在
						List<OaAppDetailTemplate> queryCode = totalDao.query("from OaAppDetailTemplate where wlcode = ?", code);
						if(null==queryCode || queryCode.size()==0)
							appendBuffer.append("物料编码 "+code+" 还没有被申请、");
						else if(!"同意".equals(queryCode.get(0).getEpStatus()))
							appendBuffer.append("物料编码还没有审批或被打回、");
						else
							detail.setWlcode(code);
					}
					String childClass = cells[2].getContents().trim();  //获得物品类别 
					Integer categoryId = (Integer) totalDao.getObjectByCondition("select id from Category where name =?",childClass);
					if(null==childClass || childClass.isEmpty())
						appendBuffer.append("物品类别不能为空、");
					else if(null==categoryId)
						appendBuffer.append("物品类别在系统中不存在、");
					else
						detail.setDetailChildClass(childClass);
					detail.setDetailFormat(cells[3].getContents().trim());// 获得物品规格
					String quantityStr = cells[4].getContents().trim();  //获得数量
					if(quantityStr==null || quantityStr.isEmpty())
						appendBuffer.append("数量不能为空、");
					else{
						try {
							float quantity = Float.parseFloat(quantityStr);
							if(quantity<=0)
								appendBuffer.append("数量不合法、");
							else
								detail.setDetailCount(quantity);
						} catch (Exception e) {
							appendBuffer.append("数量格式类型不正确、");
						}
					}
					String budgetPrice = cells[5].getContents().trim();   //获得预算单价
					if(null==budgetPrice || budgetPrice.isEmpty()){
						appendBuffer.append("预算单价不能为空、");
					}else{
						try {
							float price = Float.parseFloat(budgetPrice);
							if(price<=0)
								appendBuffer.append("预算单价不合法、");
							else
								detail.setDetailBudgetMoney(price);
						} catch (Exception e) {
							appendBuffer.append("预算单价格式不正确、");
						}
					}
					detail.setDetailUnit(cells[6].getContents().trim());  //获得物品单位
					detail.setDetailArrDate(cells[7].getContents().trim());  //获得到货期限
					String isBusy = cells[8].getContents().trim();   //获得是否加急
					if(null==isBusy || isBusy.isEmpty() || isBusy.equals("否")){
						detail.setDetailIsBusy("不加急");
					}else{
						detail.setDetailIsBusy("加急");
					}
					String yiju = cells[9].getContents().trim();// 获得计划依据
					if(null==yiju || yiju.isEmpty()){
						appendBuffer.append("计划依据不能为空、");
					}else{
						detail.setDetailPlanAcco(yiju);
					}
					
					
					if((null==appName || appName.isEmpty()) &&(null==code || code.isEmpty())
							&& (quantityStr==null || quantityStr.isEmpty())){
						break;
					}
					//detail.setDetailPlanMon(cells[4].getContents().trim()); //获得计划月份
					if(appendBuffer.length()>0){
						String message = appendBuffer.toString();
						errorMessage+="第"+i+"行有误：错误信息为："+message.substring(0, message.length()-1)+"<br>";
					}else{
						//判断物料编码、类别、名称、规格、单位是否 和编码表对应
						String temp_hql = "from OaAppDetailTemplate where " +
								" wlcode = ? and detailAppName=? ";
						OaAppDetailTemplate template = (OaAppDetailTemplate) totalDao.getObjectByCondition(temp_hql,
								detail.getWlcode(), detail.getDetailAppName());
						if(template==null){
							appendBuffer.append("物品编码、名称不对应、");
						}
						if(appendBuffer.length()>0){
							String message = appendBuffer.toString();
							errorMessage+="第"+i+"行有误：错误信息为："+message.substring(0, message.length()-1)+"<br>";
						}else{
							list.add(detail);
						}
						
					}
					
					
				}
				
				//msg = "已成功导入" + successCount + "个<br>失败" + errorCount + "个<br>重复" + cfCount ;//+ "个<br>失败的行数分别为：<br>" + strb.toString();
			} else {
				throw new Exception("没有获取到行数据");
			}
			is.close();// 关闭流
			wk.close();// 关闭工作薄
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导入出错,请截屏发给管理员!<br>" + e.getMessage() + (i + 1) + "行");
		}
		returns[0] = list;
		returns[1] = errorMessage;
		return returns;
	}

	@Override
	public GoodsStore getRecentlyGoodsStore(Integer id) {
		List<GoodsStore> list = totalDao.query("from GoodsStore where goodsStoreMarkId =(select wlcode from OaAppDetail where id=?) order by goodsStoreDate desc", id);
		if(list==null || list.size()==0){
			return null;
		}else{
			return list.get(0);
		}
		//return (GoodsStore) totalDao.query("from GoodsStore where goodsStoreMarkId =(select wlcode from OaAppDetail where id=?) order by goodsStoreDate desc", id).get(0);
	}

	
	
}
