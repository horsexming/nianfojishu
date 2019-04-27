package com.task.ServerImpl.sop.qd;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.sop.qd.LogoStickerServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.Users;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.Measuring;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.BreakSubmit;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.FailureSSOnDay;
import com.task.entity.sop.FailureSSOnMonth;
import com.task.entity.sop.FailureSSOnWeek;
import com.task.entity.sop.FailureStatistics;
import com.task.entity.sop.FailureStatisticsDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplateBanBen;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforReceiveLog;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.qd.CheckAlert;
import com.task.entity.sop.qd.LogoStickers;
import com.task.entity.system.CircuitRun;
import com.task.util.RtxUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

/**
 * 质量标识贴操作
 * 
 * @author jhh
 * 
 */
public class LogoStickerServerImpl implements LogoStickerServer {
	private TotalDao totalDao;
	private GoodsStoreServer goodsStoreServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}

	/**
	 * 查询流水卡片信息
	 */
	public Object[] findSticck(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag) {
		String hql = "";
		if ("manger".equals(tag)) {
			hql = "from LogoStickers where 1=1";
		} else {
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String deptNumber = user.getPassword().getDeptNumber();
			hql = "from LogoStickers where workingGroup='" + deptNumber + "'";
			if (null == sticker) {
				sticker = new LogoStickers();
			}
			sticker.setWorkingGroup(deptNumber);
		}
		if (null != startDate && !"".equals(startDate) && null != endDate
				&& !"".equals(endDate)) {
			hql += " and billDate between '" + startDate + "' and '" + endDate
					+ "'";
		}
		if (null != sticker) {
			if (null != sticker.getNumber() && !"".equals(sticker.getNumber())) {
				hql += " and number='" + sticker.getNumber() + "'";
			}
			if (null != sticker.getLotId() && !"".equals(sticker.getLotId())) {
				hql += " and lotId='" + sticker.getLotId() + "'";
			}
			if (null != sticker.getMarkId() && !"".equals(sticker.getMarkId())) {
				hql += " and markId='" + sticker.getMarkId() + "'";
			}
			if (null != sticker.getPartsName()
					&& !"".equals(sticker.getPartsName())) {
				hql += " and partsName='" + sticker.getPartsName() + "'";
			}
			if (null != sticker.getProcessNO()
					&& !"".equals(sticker.getProcessNO())) {
				hql += " and processNO='" + sticker.getProcessNO() + "'";
			}
			if (null != sticker.getOperator()
					&& !"".equals(sticker.getOperator())) {
				hql += " and operator='" + sticker.getOperator() + "'";
			}
			if (null != sticker.getStickStyle()
					&& !"".equals(sticker.getStickStyle())) {
				hql += " and stickStyle='" + sticker.getStickStyle() + "'";
			}
			if (null != sticker.getIsPrint()
					&& !"".equals(sticker.getIsPrint())) {
				hql += " and isPrint='" + sticker.getIsPrint() + "'";
			}
		}
		ZhUser zhUser = Util.getCurrzhUser();
		if (zhUser != null) {
			hql += " and isGys='" + zhUser.getId()
					+ "'  order by billDate desc";
		} else {
			hql += " and (isGys is null or isGys='no')  order by billDate desc";
		}
		Object[] procardAarr = new Object[2];
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		Integer count = totalDao.getCount(hql);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	public boolean saveStick(LogoStickers sticker) {
		// TODO Auto-generated method stub
		if (null != sticker) {
			// 编号处理
			HttpServletRequest request = ServletActionContext.getRequest();
			Users user = (Users) ActionContext.getContext().getSession().get(
					"Users");
			String hql = "select max(number) from LogoStickers where stickStyle='"
					+ sticker.getStickStyle() + "'";
			List list = totalDao.find(hql);
			String newNumber = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat sdfTime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			if (null != list && list.size() > 0 && null != list.get(0)) {
				String maxNumber = (String) list.get(0);
				String y = sdf.format(new Date());
				String headStr = maxNumber.substring(0, 12);
				String headStr2 = maxNumber.substring(0, 6);
				if (maxNumber.contains(y)) {
					newNumber = headStr + strAddOne(maxNumber);
				} else {
					newNumber = headStr2 + y + "001";
				}
			} else {
				if ("报废品".equals(sticker.getStickStyle())) {
					newNumber = "QD-RP-" + sdf.format(new Date()) + "001";
				} else if ("待处理品".equals(sticker.getStickStyle())) {
					newNumber = "QD-WP-" + sdf.format(new Date()) + "001";
				} else if ("首检样品".equals(sticker.getStickStyle())) {
					newNumber = "QD-FP-" + sdf.format(new Date()) + "001";
				}
			}
			sticker.setExaminerCode(user.getCode());
			sticker.setExaminerName(user.getName());
			sticker.setWorkingGroup(user.getPassword().getDeptNumber());// 班组编码
			sticker.setNumber(newNumber);
			sticker.setMarkId(sticker.getMarkId().replace(" ", ""));
			sticker.setBillDate(sdfTime.format(new Date()));
			sticker.setIsPrint("NO");
			return totalDao.save(sticker);
		}
		return false;
	}

	/**
	 * 
	 * @param liuShuiHao
	 * @return
	 */
	public static String strAddOne(String bianhao) {
		Integer intHao = Integer.parseInt(bianhao.substring(12));
		intHao++;
		String strHao = intHao.toString();
		while (strHao.length() < 3)
			strHao = "0" + strHao;
		return strHao;
	}

	@Override
	public boolean deleteStickById(Integer id) {
		// TODO Auto-generated method stub
		LogoStickers sticket = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, id);
		return totalDao.delete(sticket);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.sop.qd.LogoStickerServer#getStickById(java.lang.Integer)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.sop.qd.LogoStickerServer#getStickById(java.lang.Integer)
	 */
	@Override
	public LogoStickers getStickById(Integer id) {
		// TODO Auto-generated method stub
		LogoStickers ls = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, id);
		if (ls != null) {
			if ((ls.getYwMarkId() == null || ls.getYwMarkId().length() == 0)) {
				String ywMarkId = (String) totalDao
						.getObjectByCondition(
								"select ywMarkId from Procard where id in (select rootId from Procard where markId=? and selfCard=?)",
								ls.getMarkId(), ls.getLotId());
				if (ywMarkId != null && ywMarkId.length() > 0) {
					ls.setYwMarkId(ywMarkId);
				}
			}
			Procard procard = (Procard) totalDao.getObjectByCondition(
					" from Procard where markId=? and selfCard = ?", ls
							.getMarkId(), ls.getLotId());
			if (procard.getSbNumber() != null
					&& procard.getSbNumber().length() > 0) {
				ls.setSbNumber(procard.getSbNumber());
				ls.setSbId(procard.getSbId());
				if (procard.getSbId() != null && procard.getSbId() > 0) {
					ProcardTemplateBanBen ptbb = (ProcardTemplateBanBen) totalDao
							.getObjectByCondition(
									"from ProcardTemplateBanBen where procardTemplateBanBenApply.id=? and markId=?",
									procard.getSbId(), procard.getMarkId());
					if (ptbb != null) {
						ls.setSbmsg(ptbb.getRemark());
					}
				}
			}
		}

		return ls;
	}

	@Override
	public LogoStickers getStickBynumber(String number) {
		LogoStickers ls = (LogoStickers) totalDao.getObjectByCondition(
				"from LogoStickers where number=?", number);
		Procard procard = (Procard) totalDao.getObjectByCondition(
				" from Procard where markId=? and selfCard = ?",
				ls.getMarkId(), ls.getLotId());
		if (procard.getSbNumber() != null && procard.getSbNumber().length() > 0) {
			ls.setSbNumber(procard.getSbNumber());
			ls.setSbId(procard.getSbId());
			if (procard.getSbId() != null && procard.getSbId() > 0) {
				ProcardTemplateBanBen ptbb = (ProcardTemplateBanBen) totalDao
						.getObjectByCondition(
								"from ProcardTemplateBanBen where procardTemplateBanBenApply.id=? and markId=?",
								procard.getSbId(), procard.getMarkId());
				if (ptbb != null) {
					ls.setSbmsg(ptbb.getRemark());
				}
			}
		}

		return ls;
	}

	@Override
	public boolean updateStick(LogoStickers sticker) {
		// TODO Auto-generated method stub
		sticker.setMarkId(sticker.getMarkId().replace(" ", ""));
		LogoStickers lo = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, sticker.getId());
		lo.setDemandExamContent(sticker.getDemandExamContent());
		lo.setRealExamContent(sticker.getRealExamContent());
		// BeanUtils.copyProperties(sticker, lo, new String[] { "billDate",
		// "examinerCode", "examinerName", "workingGroup", "isGys" });
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lo.setLastUpdateTime(sdf.format(new Date()));
		return totalDao.update(lo);
	}

	@Override
	public String[] findUserInfor(String style, String content) {
		// TODO Auto-generated method stub
		// 值不能为空
		if (null != content && !"".equals(content)) {
			String[] obj = new String[2];
			String message = "";
			Users user = new Users();
			String returnCon = "";
			String hql = "";
			if ("co".equals(style)) {
				hql = "from Users where code='" + content + "'";
				List list = totalDao.query(hql);
				if (null != list && list.size() > 0) {
					user = (Users) list.get(0);
					returnCon = user.getName();
				} else {
					message = "输入的员工号系统中不存在或输入有误！！";
				}
			} else if ("na".equals(style)) {
				hql = "from Users where name='" + content + "'";
				List list = totalDao.query(hql);
				if (null != list && list.size() > 0) {
					user = (Users) list.get(0);
					returnCon = user.getCode();
				} else {
					message = "输入的员工姓名系统中不存在或输入有误！！";
				}

			}
			obj[0] = message;
			obj[1] = returnCon;
			return obj;
		}
		return null;
	}

	public boolean printInfor(Integer id) {
		LogoStickers lo = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, id);
		lo.setIsPrint("YES");
		if ("报废品".equals(lo.getStickStyle())) {
			// lo.setStatus("");
		}
		return totalDao.update(lo);
	}

	@Override
	public Object[] findSum(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize) {
		// TODO Auto-generated method stub
		String hql = "select count(*) as num,workingGroup,stickStyle,markId from LogoStickers group by workingGroup,stickStyle,markId";
		if (null != sticker) {
			hql = "select count(*) as num,workingGroup,stickStyle,markId from LogoStickers where 1=1";
			if (null != sticker.getWorkingGroup()
					&& !"".equals(sticker.getWorkingGroup())) {
				hql += " and workingGroup='" + sticker.getWorkingGroup() + "'";
			}
			if (null != sticker.getMarkId() && !"".equals(sticker.getMarkId())) {
				hql += " and markId='" + sticker.getMarkId() + "'";
			}
			if (null != sticker.getLotId() && !"".equals(sticker.getLotId())) {
				hql += " and lotId='" + sticker.getLotId() + "'";
			}
			if (null != sticker.getPartsName()
					&& !"".equals(sticker.getPartsName())) {
				hql += " and partsName='" + sticker.getPartsName() + "'";
			}
			if (null != sticker.getProcessNO()
					&& !"".equals(sticker.getProcessNO())) {
				hql += " and processNO='" + sticker.getProcessNO() + "'";
			}
			if (null != sticker.getOperator()
					&& !"".equals(sticker.getOperator())) {
				hql += " and operator='" + sticker.getOperator() + "'";
			}
			if (null != sticker.getStickStyle()
					&& !"".equals(sticker.getStickStyle())) {
				hql += " and stickStyle='" + sticker.getStickStyle() + "'";
			}
			if (null != startDate && !"".equals(startDate) && null != endDate
					&& !"".equals(endDate)) {
				hql += " and billDate between '" + startDate + "' and '"
						+ endDate + "'";
			}
			hql += " group by workingGroup,stickStyle,markId";
		}
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.find(hql).size();// list.size();
		Object[] procardSUM = new Object[2];
		procardSUM[0] = count;
		procardSUM[1] = list;
		return procardSUM;
	}

	public String selectItem(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "select distinct(" + tag + ") from LogoStickers";
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	public void export(LogoStickers sticker, String startDate, String endDate) {
		String hql = "select count(*) as num,workingGroup,stickStyle,markId from LogoStickers group by workingGroup,stickStyle,markId";
		if (null != sticker) {
			hql = "select count(*) as num,workingGroup,stickStyle,markId from LogoStickers where 1=1";
			if (null != sticker.getWorkingGroup()
					&& !"".equals(sticker.getWorkingGroup())) {
				hql += " and workingGroup='" + sticker.getWorkingGroup() + "'";
			}
			if (null != sticker.getMarkId() && !"".equals(sticker.getMarkId())) {
				hql += " and markId='" + sticker.getMarkId() + "'";
			}
			if (null != sticker.getLotId() && !"".equals(sticker.getLotId())) {
				hql += " and lotId='" + sticker.getLotId() + "'";
			}
			if (null != sticker.getPartsName()
					&& !"".equals(sticker.getPartsName())) {
				hql += " and partsName='" + sticker.getPartsName() + "'";
			}
			if (null != sticker.getProcessNO()
					&& !"".equals(sticker.getProcessNO())) {
				hql += " and processNO='" + sticker.getProcessNO() + "'";
			}
			if (null != sticker.getOperator()
					&& !"".equals(sticker.getOperator())) {
				hql += " and operator='" + sticker.getOperator() + "'";
			}
			if (null != sticker.getStickStyle()
					&& !"".equals(sticker.getStickStyle())) {
				hql += " and stickStyle='" + sticker.getStickStyle() + "'";
			}
			if (null != startDate && !"".equals(startDate) && null != endDate
					&& !"".equals(endDate)) {
				hql += " and billDate between '" + startDate + "' and '"
						+ endDate + "'";
			}
			hql += " group by workingGroup,stickStyle,markId";
		}
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("bst".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("标识管理数据汇总", 0);
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
			jxl.write.Label label0 = new Label(0, 0, "标识管理数据汇总", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 5, 0);

			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "班组编号", wc));
			ws.addCell(new jxl.write.Label(2, 1, "标识类型", wc));
			ws.addCell(new jxl.write.Label(3, 1, "件号", wc));
			ws.addCell(new jxl.write.Label(4, 1, "汇总数量", wc));
			for (int i = 0; i < list.size(); i++) {
				Object[] o = (Object[]) list.get(i);
				String bz = (String) o[1];
				String bstyle = (String) o[2];
				String markId = (String) o[3];

				long count = (Long) o[0];
				float cou = (float) count;
				float f = Float.parseFloat(String.valueOf(cou));
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, bz, wc));
				ws.addCell(new Label(2, i + 2, bstyle, wc));
				ws.addCell(new Label(3, i + 2, markId, wc));
				ws.addCell(new jxl.write.Number(4, i + 2, f, wc));
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
	public List findListPrint(String tag) {
		// TODO Auto-generated method stub
		if ("exam".equals(tag)) {// 检验处理
			String hql = "from LogoStickers where status='报废' and stickStyle ='补料单' order by billDate desc";
			return totalDao.find(hql);
		} else {// 班长打印
			Users user = (Users) Util.getLoginUser();
			String deptNumber = user.getPassword().getDeptNumber();
			String hql = "from LogoStickers where workingGroup='"
					+ deptNumber
					+ "' and status='已发卡' and stickStyle ='补料单' and isPrint='NO' order by billDate desc";
			return totalDao.find(hql);
		}

	}

	@Override
	public Object[] findNOOKList(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag) {
		String hql = "";
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if ("exam".equals(tag)) {// 检查查看检验历史记录
			hql = "from LogoStickers where  stickStyle ='补料单' and status<>'报废' order by billDate desc";
			if (null != sticker) {
				hql = totalDao
						.criteriaQueries(sticker, "billDate", between, "");
				if (hql.contains("where")) {
					hql += "  and stickStyle ='补料单' and status<>'报废'  order by billDate desc";
				} else {
					hql += " where  stickStyle ='补料单' and status<>'报废'  order by billDate desc";
				}
			}
		} else {// 班组打印补料单
			Users user = (Users) Util.getLoginUser();
			String deptNumber = user.getPassword().getDeptNumber();
			hql = "from LogoStickers where  workingGroup='"
					+ deptNumber
					+ "' and stickStyle ='补料单' and isPrint<>'NO' order by billDate desc";
			if (null != sticker) {
				hql = totalDao
						.criteriaQueries(sticker, "billDate", between, "");
				if (hql.contains("where")) {
					hql += "  and workingGroup='"
							+ deptNumber
							+ "' and stickStyle ='补料单' and isPrint<>'NO'  order by billDate desc";
				} else {
					hql += " where  workingGroup='"
							+ deptNumber
							+ "' and stickStyle ='补料单' and isPrint<>'NO'  order by billDate desc";
				}
			}
		}
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	public List getsjMarkId() {
		// TODO Auto-generated method stub
		return totalDao
				.query("select distinct markId from LogoStickers where stickStyle='首检样品' and isPrint='NO'");
	}

	@Override
	public List getsjSelfCard(String markId) {
		// TODO Auto-generated method stub
		return totalDao
				.query(
						"select distinct lotId from LogoStickers where stickStyle='首检样品' and isPrint='NO' and markId=?",
						markId);
	}

	@Override
	public List getsjProcessNo(String markId, String lotId) {
		// TODO Auto-generated method stub
		return totalDao
				.query(
						"select distinct processNO from LogoStickers where stickStyle='首检样品' and isPrint='NO' and markId=? and lotId=?",
						markId, lotId);
	}

	@Override
	public LogoStickers getStickerForCheck(String markId, String lotId,
			String processNO) {
		// TODO Auto-generated method stub
		LogoStickers stickers = (LogoStickers) totalDao
				.getObjectByCondition(
						" from LogoStickers where stickStyle='首检样品' and isPrint='NO' and markId=? and lotId=? and processNO=?",
						markId, lotId, processNO);
		if (stickers != null) {
			Integer processNO2 = 0;
			if (processNO != null) {
				processNO2 = Integer.parseInt(processNO);
			}
			ProcessInfor process = (ProcessInfor) totalDao
					.getObjectByCondition(
							"from ProcessInfor where procard.id in(select id from Procard where markId=? and selfCard=?) and processNO=?",
							markId, lotId, processNO2);
			if (process != null) {
				stickers.setGongwei(process.getGongwei());
				stickers.setMachineNo(process.getShebeiNo());
			}
			return stickers;
		}
		return null;
	}

	@Override
	public List getcheckList(Integer id) {
		LogoStickers stickers = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, id);
		if (stickers != null) {
			return totalDao
					.query(
							"from OsScope where id in( select s.id from OsTemplate ot join ot.scopes s where zhonglei='巡检' and ot.partNumber=? and ot.gongxuNum=?) order by id",
							stickers.getMarkId(), stickers.getProcessNO());
		}
		return null;
	}

	@Override
	public Object[] updateOsTemplate(Integer id) {
		LogoStickers stickers = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, id);
		if (stickers != null) {
			OsTemplate otp = (OsTemplate) totalDao
					.getObjectByCondition(
							"from OsTemplate  where partNumber=? and gongxuNum=? and zhonglei = '巡检' ",
							stickers.getMarkId(), stickers.getProcessNO() + "");
			List list = null;
			if (otp != null) {
				list = totalDao.query(" from OsScope where osTemplate.id =?",
						otp.getId());
				if (list == null || list.size() <= 0) {
					totalDao.delete(otp);
					otp = null;
					list=null;
					totalDao.clear();
				}
			}

			if (otp == null) {
				String hql_pg = "from ProcessGzstore where processName=?";
				ProcessGzstore pg = (ProcessGzstore) totalDao
						.getObjectByCondition(hql_pg, stickers.getProcessName());
				Set<OsScope> osSet = new HashSet<OsScope>();
				if (pg != null) {
					osSet = pg.getOs();
					if (osSet != null) {
						OsTemplate ost = new OsTemplate();
						ost.setPartNumber(stickers.getMarkId());
						ost.setGongxuNum(stickers.getProcessNO() + "");
						ost.setGongxuName(stickers.getProcessName());
						ost.setName(stickers.getPartsName());
						ost.setCtype1("自制");
						ost.setZhonglei("巡检");
						ost.setXjtype("按时间");
						ost.setXjcheckpc(2);
						ost.setCreateDate(Util.getDateTime());
						Set<OsScope> osSet1 = new HashSet<OsScope>();
						for (OsScope osScope : osSet) {
							OsScope osc = new OsScope();
							osc.setContent(osScope.getContent());
							osc.setJcff(osScope.getJcff());
							osc.setZltz(osScope.getZltz());
							osc.setType(osScope.getType());
							osc.setOsTemplate(ost);
							osSet1.add(osc);
						}
						ost.setScopes(osSet1);
						totalDao.save(ost);
						otp = ost;
					}
				}
			}

			List<ProcessAndMeasuring> pamList = totalDao
					.query(
							" from ProcessAndMeasuring where processName=? "
									+ " and measuringId in (select id from Measuring where DATEDIFF(nextcalibrationTime,CURRENT_DATE)>0)",
							stickers.getProcessName());
			if (otp != null) {
				list = totalDao.query(" from OsScope where osTemplate.id =?",
						otp.getId());
			}
			Object[] obj = { otp, list, pamList };
			return obj;
		}
		return null;
	}

	@Override
	public Object[] findSjList(LogoStickers sticker, String startDate,
			String endDate, int cpage, int pageSize, String tag) {
		if (sticker == null) {
			sticker = new LogoStickers();
		}
		Users user = Util.getLoginUser();
		String hql = totalDao.criteriaQueries(sticker, null, "count");
		hql += " and stickStyle='首检样品' and isPrint='NO' "
				+ " and gongwei in ( select m.workPosition from Machine m  join m.userset u where u.id="
				+ user.getId() + ")  order by gongwei";
		Object[] procardAarr = new Object[2];
		List<LogoStickers> list = totalDao.findAllByPage(hql, cpage, pageSize);
		if (list != null && list.size() > 0) {
			for (LogoStickers log : list) {
				if (log.getGongwei() == null || log.getGongwei().length() == 0) {
					Integer processNO2 = 0;
					if (log.getProcessNO() != null) {
						try {
							processNO2 = Integer.parseInt(log.getProcessNO());
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					ProcessInfor process = (ProcessInfor) totalDao
							.getObjectByCondition(
									"from ProcessInfor where procard.id in(select id from Procard where markId=? and selfCard=?) and processNO=?",
									log.getMarkId(), log.getLotId(), processNO2);
					if (process != null) {
						log.setGongwei(process.getGongwei());
						log.setMachineNo(process.getShebeiNo());
					}
				}
			}
		}
		Integer count = totalDao.getCount(hql);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	public String updateStick2(LogoStickers sticker,
			List<OsRecordScope> osRecordScopeList, String status) {
		// TODO Auto-generated method stub
		LogoStickers old = (LogoStickers) totalDao.getObjectById(
				LogoStickers.class, sticker.getId());
		// 查询对应的检验提醒
		List<CheckAlert> caList = totalDao
				.query(
						"from CheckAlert where markId=? and selfCard=? and processNo=? and status !='完成'",
						old.getMarkId(), old.getLotId(), Integer.parseInt(old
								.getProcessNO()));
		if (caList != null && caList.size() > 0) {
			for (CheckAlert ca : caList) {
				ca.setStatus("完成");
				ca.setEndTime(Util.getDateTime());
				totalDao.update(ca);
			}
		}
		Procard procard = (Procard) totalDao.getObjectByCondition(
				" from Procard  where markId = ? and selfCard = ?", old
						.getMarkId(), old.getLotId());
		String ywmarkId = "";
		String neiorderNum = "";
		String selfcard = "";
		if (procard != null) {
			ywmarkId = procard.getYwMarkId();
			neiorderNum = procard.getOrderNumber();
			selfcard = procard.getSelfCard();
		}
		StringBuffer jcSb = new StringBuffer();
		StringBuffer jlSb = new StringBuffer();
		if (old == null) {
			return "没有找到目标！";
		}
		if (old.getIsPrint() != null
				&& old.getIsPrint().equalsIgnoreCase("YES")) {
			return "该首检已被检验，请勿重复检验";
		}
		old.setExaminerName(sticker.getExaminerName());
		old.setExaminerDate(Util.getDateTime());
		old.setOperator(sticker.getOperator());
		old.setCode(sticker.getCode());
		Users user = Util.getLoginUser();
		if (user == null) {
			return "请先登录!";
		}
		List<OsRecord> orList = new ArrayList<OsRecord>();
		Set<OsRecord> osrSet = new HashSet<OsRecord>();
		List<Integer> otIdList = new ArrayList<Integer>();
		OsTemplate ost = null;
		if ("ty".equals(status)) {
			ost = new OsTemplate();
			ost.setPartNumber(old.getMarkId());
			ost.setGongxuNum(old.getProcessNO());
			ost.setGongxuName(old.getProcessName());
			ost.setName(old.getPartsName());
			ost.setZhonglei("巡检");
			ost.setIspublic("否");
			Set<OsScope> scopes = new HashSet<OsScope>();
			for (OsRecordScope ors : osRecordScopeList) {
				OsScope os = (OsScope) totalDao.getObjectById(OsScope.class,
						ors.getScopeId());
				if (ors.getScopeId() != null) {
					OsScope oldos = (OsScope) totalDao.getObjectById(
							OsScope.class, ors.getScopeId());
					BeanUtils.copyProperties(oldos, os, new String[] { "id",
							"osTemplate" });
				} else if (ors.getScope() != null) {
					os = ors.getScope();
					os.setType("手动填写");
				}
				scopes.add(os);
			}
			ost.setScopes(scopes);
			totalDao.save(ost);
		}
		if (osRecordScopeList != null && osRecordScopeList.size() > 0) {
			int i = 0;
			for (OsRecordScope ors : osRecordScopeList) {
				i++;
				OsScope os = (OsScope) totalDao.getObjectById(OsScope.class,
						ors.getScopeId());
				if (os == null) {
					return "没有找到对应的检查项!";
				}
				jcSb.append(i + "、" + os.getJcff() + os.getZltz() + "。");
				jlSb.append(i + "、" + ors.getContent() + "。");
				ors.setScope(os);
				totalDao.save(ors);
				OsTemplate ot = null;
				if (ost != null && "ty".equals(status)) {
					ot = ost;
				} else {
					ot = (OsTemplate) totalDao
							.getObjectByCondition(
									"from OsTemplate where id in (select ot.id from OsTemplate ot join ot.scopes s where s.id=?)",
									ors.getScopeId());
				}
				ors.setTitle(os.getTitle());
				if (otIdList.contains(ot.getId())) {
					for (OsRecord or : orList) {
						Set<OsRecordScope> orsSet = or.getRecordScope();
						orsSet.add(ors);
						or.setRecordScope(orsSet);
					}
				} else {
					otIdList.add(ot.getId());
					OsRecord or = new OsRecord();
					or.setJcpc(old.getLotId());// 检查批次
					or.setQuantity(1f);// 本批数量
					or.setVerification(sticker.getIsHege());// 是否合格
					or.setNowDate(Util.getDateTime());// 当前时间
					or.setUsername(user.getName());// 检验人
					or.setTemplateId(ot.getId());// 检验模版id，用户查询件号
					or.setTemplate(ot);
					if ("不合格".equals(sticker.getIsHege())
							&& sticker.getBuhegeId() != null) {
						BuHeGePin bhgp = (BuHeGePin) totalDao.getObjectById(
								BuHeGePin.class, sticker.getBuhegeId());
						if (bhgp != null) {
							String[] cpAndClass = sticker.getBhgcode().split(
									",");
							String code = cpAndClass[0] + bhgp.getCode()
									+ cpAndClass[1];
							code = code.replaceAll(" ", "");
							or.setCode(code);
							or.setType(bhgp.getType());
						}
					}
					or.setSeeDate(Util.getDateTime("yyyy-MM-dd"));
					or.setYwmakrId(ywmarkId);
					or.setNeiordeNum(neiorderNum);
					or.setBanbenNumber(procard.getBanBenNumber());
					or.setGongwei(old.getGongwei());
					or.setSelfCard(selfcard);
					Set<OsRecordScope> orsSet = new HashSet<OsRecordScope>();
					orsSet.add(ors);
					or.setRecordScope(orsSet);
					orList.add(or);
				}
			}
		}
		old.setDemandExamContent(jcSb.toString());// 要求检查内容
		old.setRealExamContent(jlSb.toString());// 实际检查内容记录
		for (OsRecord or2 : orList) {
			or2.setZhonglei("首检");
			or2.setMeasuring_no(old.getMeasuring_no());
			or2.setMeasuringMatetag(old.getMeasuringMatetag());
			totalDao.save(or2);
			osrSet.add(or2);
		}
		old.setIsPrint("YES");
		// 如果检验不合格 添加一条不合格记录信息;
		if ("不合格".equals(sticker.getIsHege())) {
			BreakSubmit breakSubmit = new BreakSubmit();
			breakSubmit.setProcardId(procard.getId());// 自制件Id;
			breakSubmit.setProName(old.getPartsName());// 零件名称
			breakSubmit.setType("零件损坏");
			breakSubmit.setBreakgroup("本工序不合格");
			breakSubmit.setProcessNo(Integer.parseInt(old.getProcessNO()));// 工序号
			breakSubmit.setProcessName(old.getProcessName());// 工序名
			breakSubmit.setMarkId(old.getMarkId());
			breakSubmit.setTjbreakcount(1f);
			breakSubmit.setQrbreakcount(1f);
			breakSubmit.setTjUsersId(user.getId());// 提交人Id
			breakSubmit.setTjUsersName(user.getName());// 提交人姓名
			breakSubmit.setQrUsersId(user.getId());// 确认人Id
			breakSubmit.setQrUsersName(user.getName());// 确认人姓名
			breakSubmit.setTjTime(Util.getDateTime());// 提交时间
			breakSubmit.setQrTime(Util.getDateTime());// 确认时间
			breakSubmit.setSelfcard(procard.getSelfCard());
			breakSubmit.setYwmarkId(procard.getYwMarkId());
			breakSubmit.setTjtype("首检检验");
			ProcessInfor process = (ProcessInfor) totalDao
					.getObjectByCondition(
							" from ProcessInfor where procard.id = ? and processNO = ? ",
							procard.getId(), breakSubmit.getProcessNo());
			if (process != null) {
				breakSubmit.setProcessId(process.getId());// 工序Id
				process.setBreakCount(process.getBreakCount() + 1);
				String prologId = "";
				List<ProcessInforReceiveLog> proLogList = totalDao
						.query(
								" from ProcessInforReceiveLog where fk_processInforId = ?  and status = '提交' and receiveNumber=1 ",
								process.getId());
				for (ProcessInforReceiveLog processInforReceiveLog : proLogList) {
					prologId += "," + processInforReceiveLog.getId();
				}
				if (prologId != null && prologId.length() >= 1) {
					prologId = prologId.substring(1);
				}
				breakSubmit.setPrologId(prologId);
				totalDao.update(process);
				/**
				 * 更新后面工序的总数量
				 */
				// String updateSql =
				// "update ta_sop_w_processinfor set totalCount=?,applyCount= "
				// + "CASE applyCount WHEN 0 THEN applyCount ELSE applyCount-"
				// + process.getBreakCount()
				// + " END where fk_procardId=? and processNO>?";
				// totalDao.createQueryUpdate(null, updateSql, process
				// .getTotalCount()
				// - process.getBreakCount(), process
				// .getProcard().getId(), process
				// .getProcessNO());
				String afterhql = " from ProcessInfor where procard.id = ? and processNO >?";
				List<ProcessInfor> processList = totalDao.query(afterhql,
						procard.getId(), process.getProcessNO());
				if (processList != null && processList.size() > 0) {
					for (ProcessInfor processInfor : processList) {
						processInfor.setTotalCount(processInfor.getTotalCount()
								- process.getBreakCount());
						processInfor.setApplyCount(process.getBreakCount());
						totalDao.update(processInfor);
					}
				}

			}
			totalDao.save(breakSubmit);
			String processName = "现场不良品处理申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						BreakSubmit.class, breakSubmit.getId(), "epstatus",
						"id", "ProcardAction!findbreaksubmitById.action?id="
								+ breakSubmit.getId(), user.getDept() + "部门 "
								+ user.getName() + "现场不良品处理申请，请您审批", true);
				if (epId != null && epId > 0) {
					breakSubmit.setEpId(epId);
					CircuitRun circuitRun = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun.getAllStatus())
							&& "审批完成".equals(circuitRun.getAuditStatus())) {
						breakSubmit.setEpstatus("同意");
						breakSubmit.setClResult("返修");
					} else {
						breakSubmit.setEpstatus("未审批");
					}
					totalDao.update(breakSubmit);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 入不合格品库
			GoodsStore goodsStore = new GoodsStore();
			goodsStore.setGoodsStoreMarkId(breakSubmit.getMarkId());// 件号
			goodsStore.setGoodsStoreWarehouse("不合格品库");// 库别
			goodsStore.setBanBenNumber(procard.getBanBenNumber());// 版本号
			goodsStore.setKgliao(procard.getKgliao());// 供料属性
			goodsStore.setGoodsStoreLot(procard.getSelfCard());// 批次
			goodsStore.setGoodsStoreGoodsName(procard.getProName());// 名称
			goodsStore.setGoodsStoreFormat(procard.getSpecification());// 规格
			goodsStore.setWgType(procard.getWgType());// 物料类别
			goodsStore.setGoodsStoreUnit(procard.getUnit());// 单位
			goodsStore.setGoodsStoreCount(breakSubmit.getQrbreakcount());// 入库数量
			goodsStore.setGoodsStoreTime(Util.getDateTime());// 入库时间
			goodsStore.setGoodsStorePerson(user.getName());// 负责人
			goodsStore.setGoodsStoreProMarkId(procard.getRootMarkId());// 总成件号
			goodsStore.setTuhao(procard.getTuhao());// 图号
			goodsStore.setInputSource("在库不良品提交");//
			goodsStore.setStatus("入库");
			goodsStore.setStyle("不合格品入库");// 入库类型
			goodsStore.setPrintStatus("YES");// 打印状态
			goodsStore.setProcessNo(Integer.parseInt(old.getProcessNO()));// 工序号
			goodsStore.setProcessName(old.getProcessName());// 工序号
			goodsStoreServer.saveSgrk(goodsStore);
			// 统计不合格报表
			FailureStatistics failureSt = new FailureStatistics();
			failureSt.setDateTime(Util.getDateTime("yyyy年MM月dd日"));
			failureSt.setMarkId(procard.getMarkId().replaceAll(" ", ""));
			int week = 0;
			try {
				week = Util.getNowWeek(Util.StringToDate(failureSt
						.getDateTime(), "yyyy年MM月dd日"));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String weeks = Util.getDateTime("yyyy") + "年" + week + "周";
			failureSt.setWeekds(weeks);
			failureSt.setAddTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
			failureSt.setSubmitCount(1f);
			failureSt.setGongwei(old.getGongwei());
			failureSt.setSelfcard(procard.getSelfCard());
			failureSt.setProcessNo(breakSubmit.getProcessNo());
			failureSt.setProcessName(old.getProcessName());
			failureSt.setProName(breakSubmit.getProName());
			Set<FailureStatisticsDetail> fsdSet = new HashSet<FailureStatisticsDetail>();
			if (sticker.getBuhegeId() != null && sticker.getBuhegeId() > 0) {
				BuHeGePin bhgp = (BuHeGePin) totalDao.getObjectById(
						BuHeGePin.class, sticker.getBuhegeId());
				if (bhgp != null) {
					String[] cpAndClass = sticker.getBhgcode().split(",");
					String code = cpAndClass[0] + bhgp.getCode()
							+ cpAndClass[1];
					code = code.replaceAll(" ", "");
					// 不良品类型明细
					FailureStatisticsDetail fsd = new FailureStatisticsDetail();
					fsd.setBuhegeId(bhgp.getId());
					fsd.setCode(code);
					fsd.setType(bhgp.getType());
					fsd.setBadNumber(1F);
					fsd.setFailureStatistics(failureSt);
					fsdSet.add(fsd);
				}

			}
			String days = Util.getDateTime("yyyy年MM月dd日");
			String months = Util.getDateTime("yyyy年MM月");
			failureSt.setFailureCount(1f);
			failureSt.setAdddays(days);
			failureSt.setAddmonths(months);
			failureSt.setFsdSet(fsdSet);
			failureSt.setType("自制");
			failureSt.setNowberakcount(1f);// 记录当前时间节点该批次件号不合格品总数
			totalDao.save(failureSt);
			Set<FailureStatisticsDetail> fsdSet1 = failureSt.getFsdSet();
			// 统计日报表
			FailureSSOnDay fsday = (FailureSSOnDay) totalDao
					.getObjectByCondition(
							"  from FailureSSOnDay where gongwei = ? and days = ? ",
							failureSt.getGongwei(), days);
			if (fsday != null) {
				Set<FailureStatisticsDetail> fsddaySet = fsday.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
							.getObjectByCondition(
									" from FailureStatisticsDetail where failureSSOnDay.id =? and buhegeId = ?",
									fsday.getId(), f.getBuhegeId());
					if (fsd != null) {
						fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
						totalDao.update(fsd);
					} else {
						if (fsddaySet == null) {
							fsddaySet = new HashSet<FailureStatisticsDetail>();
						}
						fsd = new FailureStatisticsDetail();
						fsd.setBadNumber(f.getBadNumber());
						fsd.setBuhegeId(f.getBuhegeId());
						fsd.setCode(f.getCode());
						fsd.setType(f.getType());
						fsd.setFailureSSOnDay(fsday);
						fsddaySet.add(fsd);
					}
				}
				fsday.setOneDayFc(fsday.getOneDaySc()
						+ failureSt.getFailureCount());// 每日不合格总数 某工位
				fsday.setOneDaySc(fsday.getOneDaySc()
						+ failureSt.getSubmitCount());// 每日检验总数 某工位
				fsday.setFsdSet(fsddaySet);
				totalDao.update(fsday);
			} else {
				fsday = new FailureSSOnDay();
				fsday.setOneDayFc(failureSt.getFailureCount());// 每日不合格总数 某工位
				fsday.setOneDaySc(failureSt.getSubmitCount());// 每日检验总数 某工位
				fsday.setAddTime(Util.getDateTime());
				fsday.setGongwei(failureSt.getGongwei());
				fsday.setDays(days);
				fsday.setMonths(months);
				fsday.setWeekds(weeks);
				Set<FailureStatisticsDetail> fsddaySet = fsday.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnDay(fsday);
					if (fsddaySet == null) {
						fsddaySet = new HashSet<FailureStatisticsDetail>();
					}
					fsddaySet.add(fsd);
				}
				fsday.setFsdSet(fsddaySet);
				totalDao.save(fsday);
			}
			// 统计周报表
			FailureSSOnWeek fsweek = (FailureSSOnWeek) totalDao
					.getObjectByCondition(
							"  from FailureSSOnWeek where gongwei = ? and weekds = ? ",
							failureSt.getGongwei(), weeks);
			if (fsweek != null) {
				Set<FailureStatisticsDetail> fsdweekSet = fsweek.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
							.getObjectByCondition(
									" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?",
									fsweek.getId(), f.getBuhegeId());
					if (fsd != null) {
						fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
						totalDao.update(fsd);
					} else {
						if (fsdweekSet == null) {
							fsdweekSet = new HashSet<FailureStatisticsDetail>();
						}
						fsd = new FailureStatisticsDetail();
						fsd.setBadNumber(f.getBadNumber());
						fsd.setBuhegeId(f.getBuhegeId());
						fsd.setCode(f.getCode());
						fsd.setType(f.getType());
						fsd.setFailureSSOnWeek(fsweek);
						fsdweekSet.add(fsd);
					}
				}
				fsweek.setOneWeekFc(fsweek.getOneWeekFc()
						+ failureSt.getFailureCount());// 每周不合格总数 某工位
				fsweek.setOneWeekSc(fsweek.getOneWeekSc()
						+ failureSt.getSubmitCount());// 每周检验总数 某工位
				fsweek.setFsdSet(fsdweekSet);
				totalDao.update(fsweek);
			} else {
				fsweek = new FailureSSOnWeek();
				fsweek.setOneWeekFc(failureSt.getFailureCount());// 每周不合格总数 某工位
				fsweek.setOneWeekSc(failureSt.getSubmitCount());// 每周检验总数 某工位
				fsweek.setAddTime(Util.getDateTime());
				fsweek.setGongwei(failureSt.getGongwei());
				fsweek.setWeekds(weeks);
				Set<FailureStatisticsDetail> fsdweekSet = fsweek.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnWeek(fsweek);
					if (fsdweekSet == null) {
						fsdweekSet = new HashSet<FailureStatisticsDetail>();
					}
					fsdweekSet.add(fsd);
				}
				fsweek.setFsdSet(fsdweekSet);
				totalDao.save(fsweek);
			}
			// 统计周报表
			FailureSSOnMonth fsdmonth = (FailureSSOnMonth) totalDao
					.getObjectByCondition(
							"  from FailureSSOnMonth where gongwei = ? and months = ? ",
							failureSt.getGongwei(), months);
			if (fsdmonth != null) {
				Set<FailureStatisticsDetail> fsdmonthSet = fsweek.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = (FailureStatisticsDetail) totalDao
							.getObjectByCondition(
									" from FailureStatisticsDetail where failureSSOnWeek.id =? and buhegeId = ?",
									fsweek.getId(), f.getBuhegeId());
					if (fsd != null) {
						fsd.setBadNumber(fsd.getBadNumber() + f.getBadNumber());
						totalDao.update(fsd);
					} else {
						if (fsdmonthSet == null) {
							fsdmonthSet = new HashSet<FailureStatisticsDetail>();
						}
						fsd = new FailureStatisticsDetail();
						fsd.setBadNumber(f.getBadNumber());
						fsd.setBuhegeId(f.getBuhegeId());
						fsd.setCode(f.getCode());
						fsd.setType(f.getType());
						fsd.setFailureSSOnMonth(fsdmonth);
						fsdmonthSet.add(fsd);
					}
				}
				fsdmonth.setOneMonthSc(fsdmonth.getOneMonthSc()
						+ failureSt.getFailureCount());// 每月不合格总数 某工位
				fsdmonth.setOneMonthFc(fsdmonth.getOneMonthFc()
						+ failureSt.getSubmitCount());// 每月检验总数 某工位
				fsdmonth.setFsdSet(fsdmonthSet);
				totalDao.update(fsdmonth);
			} else {
				fsdmonth = new FailureSSOnMonth();
				fsdmonth.setOneMonthSc(failureSt.getFailureCount());// 每月不合格总数
				// 某工位
				fsdmonth.setOneMonthFc(failureSt.getSubmitCount());// 每月检验总数 某工位
				fsdmonth.setAddTime(Util.getDateTime());
				fsdmonth.setGongwei(failureSt.getGongwei());
				fsdmonth.setWeekds(weeks);
				Set<FailureStatisticsDetail> fsdmonthSet = fsdmonth.getFsdSet();
				for (FailureStatisticsDetail f : fsdSet1) {
					FailureStatisticsDetail fsd = new FailureStatisticsDetail();
					fsd.setBadNumber(f.getBadNumber());
					fsd.setBuhegeId(f.getBuhegeId());
					fsd.setCode(f.getCode());
					fsd.setType(f.getType());
					fsd.setFailureSSOnMonth(fsdmonth);
					if (fsdmonthSet == null) {
						fsdmonthSet = new HashSet<FailureStatisticsDetail>();
					}
					fsdmonthSet.add(fsd);
				}
				fsdmonth.setFsdSet(fsdmonthSet);
				totalDao.save(fsdmonth);
			}

		} else {// 合格之后添加巡检提示
			if (procard != null) {
				CheckAlert ca = new CheckAlert();
				ca.setMarkId(procard.getMarkId());// 件号
				ca.setSelfCard(procard.getSelfCard());
				ca.setProName(procard.getProName());// 零件名称
				try {
					ca.setProcessNo(Integer.parseInt(old.getProcessNO()));// 工序号
				} catch (Exception e) {
					// TODO: handle exception
				}
				ca.setProcessName(old.getProcessName());// 工序名称
				ca.setGongwei(old.getGongwei());
				ca.setShebeino(old.getMachineNo());
				String time = Util.getDateTime();
				ca.setAddTime(time);// 添加时间
				ca.setUserId(user.getId());// 检验员Id
				ca.setUserCode(user.getCode());// 检验员工号
				ca.setUserName(user.getName());// 检验员名字
				String time2 = null;
				try {
					// time2 = Util.aftertime(time, 2 * 3600 * 1000l);
					time2 = Util.getCalendarModified(Util.getDateTime(), 10, 2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ca.setCheckTime(time2);// 检验时间
				ca.setStatus("待通知");// (待通知，已通知，完成)
				totalDao.save(ca);
			}
		}

		return totalDao.update(old) + "";
	}

	/****
	 * 首检打印消息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String printMes(Integer id) {
		if (id != null) {
			LogoStickers sticker = (LogoStickers) totalDao.getObjectById(
					LogoStickers.class, id);
			if (sticker != null) {
				String mess = sticker.getNumber() + ";" + sticker.getMarkId()
						+ ";" + sticker.getYwMarkId() + ";"
						+ sticker.getPartsName() + ";" + sticker.getLotId()
						+ ";" + sticker.getProcessNO() + ";"
						+ sticker.getProcessName() + ";" + sticker.getCode()
						+ ";" + sticker.getOperator() + ";"
						+ sticker.getExaminerName() + ";"
						+ sticker.getExaminerDate();
				return mess;
			}
		}
		return "";
	}

	@Override
	public String updateAlertCheck() {
		// TODO Auto-generated method stub
		Date date = new Date();
		// long t1 = date.getTime()-10*60*1000l;
		long t2 = date.getTime() + 10 * 60 * 1000l;
		// Date date1 = new Date(t1);
		Date date2 = new Date(t2);
		// String time1 = Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss");
		String time2 = Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss");
		List<CheckAlert> caList = totalDao
				.query("from CheckAlert where status !='完成'  and checkTime<='"
						+ time2 + "'");
		if (caList != null && caList.size() > 0) {
			for (CheckAlert ca : caList) {
				// AlertMessagesServerImpl.addAlertMessages("巡检消息提醒",
				// ca.getMarkId()+"第"+ca.getSelfCard()+"批次的第"+ca.getProcessNo()+"工序("+ca.getProcessName()+")需巡检,请在"+ca.getCheckTime()+"前前往"+ca.getGongwei()+"工位"+ca.getShebeino()+"设备处巡检,",
				// new Integer[]{ca.getUserId()}, null, true, "yes");
				RtxUtil.sendNotify(ca.getUserCode(), ca.getMarkId() + "第"
						+ ca.getSelfCard() + "批次的第" + ca.getProcessNo() + "工序("
						+ ca.getProcessName() + ")需巡检,请在" + ca.getCheckTime()
						+ "前前往" + ca.getGongwei() + "工位" + ca.getShebeino()
						+ "设备处巡检。", "巡检提醒", "0", "0");
				ca.setStatus("已通知");
				totalDao.update(ca);
			}
		}
		return null;
	}

	@Override
	public String xuanzeMea(Integer[] ids, Integer id, Integer id1) {
		if (id != null) {
			String measuringMatetag = "";
			String measuring_no = "";
			LogoStickers ls = (LogoStickers) totalDao.get(LogoStickers.class,
					id);
			ProcessInfor process = (ProcessInfor) totalDao
			.getObjectByCondition(
					" from ProcessInfor where processName =? and fk_procardId =? "
							+ " and (dataStatus is null or dataStatus <> '删除') ",
					ls.getProcessName(),ls.getProcardId());
			if (ids != null && ids.length > 0) {
				for (int i = 0, len = ids.length; i < len; i++) {
					ProcessAndMeasuring pam = (ProcessAndMeasuring) totalDao
							.get(ProcessAndMeasuring.class, ids[i]);
					if (i == 0) {
						measuringMatetag += pam.getMatetag();
						measuring_no += pam.getMeasuring_no();
					} else {
						measuringMatetag += ";" + pam.getMatetag();
						measuring_no += ";" + pam.getMeasuring_no();
					}
				}
			} else if (id1 != null) {
				Measuring mea = (Measuring) totalDao.get(Measuring.class, id1);
				boolean bool = Util.compareTime(mea.getNextcalibrationTime(),
						"yyyy-MM-dd", Util.getDateTime("yyyy-MM-dd"),
						"yyyy-MM-dd");
				if (bool) {
					measuringMatetag = mea.getMatetag();
					measuring_no = mea.getMeasuring_no();
					ProcessAndMeasuring pam = (ProcessAndMeasuring) totalDao
							.getObjectByCondition(
									" from ProcessAndMeasuring where processName =? and measuringId =?  ",
									ls.getProcessName(), mea.getId());
					if (pam == null) {
						pam = new ProcessAndMeasuring(process.getId(), mea
								.getId(), ls.getProcessName(), mea.getNumber(),
								mea.getMatetag(), mea.getMeasuring_no());
						totalDao.save(pam);
					}
				} else {
					return "量、检具:" + mea.getMeasuring_no() + "校验期:("
							+ mea.getNextcalibrationTime()
							+ ")已到期，请先校验，或选择另外的量、检具";
				}

			}
			ls.setMeasuring_no(measuring_no);
			ls.setMeasuringMatetag(measuringMatetag);
			totalDao.update(ls);
			if (process != null) {
				process.setMeasuring_no(measuring_no);
				process.setMeasuringMatetag(measuringMatetag);
				// 同步更新BOM上ProcessTem上的
				if (process.getProcessTemplateId() != null) {
					ProcessTemplate processT = (ProcessTemplate) totalDao
							.getObjectByCondition(
									" from ProcessTemplate where id =? ",
									process.getProcessTemplateId());
					if (processT != null) {
						processT.setMeasuring_no(measuring_no);
						processT.setMeasuringMatetag(measuringMatetag);
					}
				}
				return totalDao.update(process) + "";
			}
		}
		return "error";
	}
}
