package com.task.ServerImpl.sop;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.sop.ProcessCollectSever;
import com.task.entity.Screen;
import com.task.entity.TaSopGongwei;
import com.task.entity.sop.ProcessCollect;
import com.task.util.Util;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.VerticalAlignment;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessCollectServerImpl implements ProcessCollectSever {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String exportExcel(ProcessCollect pc) {

		return null;
	}

	@Override
	public List<ProcessCollect> findAllPcList(int pageNo, int pageSize) {
		String sql = " SELECT c.ywMarkId,c.proName ,c.orderNumber,c.markid,c.selfcard,d.gongwei,"
				+" c.count,c.productStyle,"
				+ "d.submitnumber,d.usercodes,d.usernames,d.shebeino,"
				+ "d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ "d.processname ,d.processstatus,d.procesdianshu,c.id,c.banBenNumber,c.needFinalDate from  ta_sop_w_procard "
				+ "c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid,a.gongwei,b.shebeino,a.submitnumber,b.procesdianshu "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id)"
				+ " d ON c.id = d.fk_procardId order by d.sumitapplydate desc ";

		List<Map> list = totalDao.findBySql(sql, pageNo, pageSize);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = list.get(i);
				String usercodes = (String) map.get("usercodes");
				String usernames = (String) map.get("usernames");
				String markId = (String) map.get("markId");
				String selfCard = (String) map.get("selfCard");
				Integer processNO = (Integer) map.get("processno");
				String processName = (String) map.get("processname");
				String ywmarkId = (String) map.get("ywMarkId");
				String orderNumber = (String) map.get("orderNumber");
				String proName = (String)map.get("proName");
				double tjNumber = 0d;
				if(map.get("submitnumber")!=null){
					tjNumber = Double.valueOf(map.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map.get("count")!=null){
					finalCount = Double.valueOf(map.get("count").toString());
				}
				String gongwei = (String) map.get("gongwei");
				String shebeiNo = (String) map.get("shebeino");
				String processStatus = (String) map.get("processstatus");
				String firstApplyDate = (String) map.get("firstapplydate");
				String sumitApplyDate = (String) map.get("sumitapplydate");
				String productStyle = (String) map.get("productStyle");
				String banBenNumber = (String) map.get("banBenNumber");
				String needFinalDate = (String) map.get("needFinalDate");
				double procesdianshu = 0d;
				if(map.get("procesdianshu")!=null){
					procesdianshu = Double.valueOf(map.get("procesdianshu").toString());
				}
				ProcessCollect pc = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc.setFinalCount(finalCount);
				pc.setProcesdianshu(procesdianshu);
				Float orderNum = (Float) totalDao.getObjectByCondition(" select  filnalCount  from Procard where orderNumber=? and procardStyle='总成'", pc.getOrderNumber());
				pc.setOrderNum(orderNum.doubleValue());
				pc.setNeedFinalDate(needFinalDate);
				Integer procardId = (Integer) map.get("id");
				if(procardId!=null){
					String 	mrpjihuoDate =   (String) totalDao.getObjectByCondition(" select addtime from ProcardBl where procardId =? ", procardId);
					pc.setMrpjihuoDate(mrpjihuoDate);
				}
				pclist.add(pc);
			}
		}
		return pclist;
	}

	@Override
	public Map<Integer, Object> findPcCondition(ProcessCollect pc, int pageNo,
			int pageSize,String startDate,String endDate,String workShop,String productStyle_str) {
		if (pc == null) {
			pc = new ProcessCollect();
		}
		String add1="";
		String add2="";
		if(startDate!=null&&startDate.length()>0){
			add1 = " and a.firstApplyDate>='"+startDate+"'";
		}
		if(endDate!=null&&endDate.length()>0){
			add2 = " and a.sumitApplyDate<='"+endDate+"'";
		}
		String productStyle_sql = "";
		if(productStyle_str!=null&&!"".equals(productStyle_str)){
			productStyle_sql = " and c.productStyle ='"+productStyle_str+"'";
		}
		//拼接工位
		String hql_gongwei="";
		if(workShop!=null&&!"".equals(workShop)){
			List<TaSopGongwei> list =new ArrayList<TaSopGongwei>()  ;
			Screen s = (Screen) totalDao.getObjectByCondition("from Screen where name =?", workShop);
			for (TaSopGongwei taSopGongwei : s.getGongweis()) {
				list.add(taSopGongwei);
			}
			if(list!=null&&list.size()>0){
			String gongwei ="";
			for(TaSopGongwei taSopGongwei :list){
				if("".equals(gongwei)){
					gongwei +="'"+taSopGongwei.getGongweihao()+"'";
				}else{
					gongwei +=","+"'"+taSopGongwei.getGongweihao()+"'";
				}
			}
			hql_gongwei=" and d.gongwei in("+gongwei+")";
			}
		}
		//拼接工序名
		String hql_process = "";
		if(pc.getProcessName()!=null&&!"".equals(pc.getProcessName())){
			String[] processNames = pc.getProcessName().split(",");
			if(processNames!=null&&processNames.length>0){
			String process ="";
			for(int i=0;i<processNames.length;i++){
				process+=","+"'"+processNames[i]+"'";
			}
			if(process.length()>1){
				process = process.substring(1);
			}
			hql_process=" and d.processname in("+process+")";
			}
		}
		String sql = " SELECT c.ywMarkId,c.proName,c.orderNumber, c.markid,c.selfcard,d.gongwei,"
				+" c.count,c.productStyle,"
				+ "d.submitnumber,d.usercodes,d.usernames,d.shebeino,"
				+ "d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ "d.processname ,d.processstatus,d.procesdianshu,c.id,c.banBenNumber,c.needFinalDate from  ta_sop_w_procard "
				+ "c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeino,a.submitnumber,b.procesdianshu "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id where 2=2 "+add1+add2+")"
				+ " d ON c.id = d.fk_procardId  where 1=1  "+hql_gongwei+productStyle_sql+hql_process;

		String hql1 = totalDao.criteriaQueries(pc, null,"processName","procesdianshu");
		String[] array = hql1.split(" where 1=1");
		if (array != null && array.length == 2) {
			sql += array[1] ;
		}
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		int count = 0;
		List<Map> list2 = totalDao.findBySql(sql+ " order by d.sumitapplydate   desc");
		if (list2 != null && list2.size() > 0) {
			count = list2.size();
		}
		List<Map> list = totalDao.findBySql(sql+" order by d.sumitapplydate   desc", pageNo, pageSize);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) { 
				Map map1 = list.get(i);
				String usercodes = (String) map1.get("usercodes");
				String usernames = (String) map1.get("usernames");
				String markId = (String) map1.get("markId");
				String selfCard = (String) map1.get("selfCard");
				Integer processNO = (Integer) map1.get("processno");
				String processName = (String) map1.get("processname");
				String ywmarkId = (String) map1.get("ywMarkId");
				String orderNumber = (String) map1.get("orderNumber");
				String proName = (String)map1.get("proName");
				double tjNumber = 0d;
				if (map1.get("submitnumber") != null) {
					tjNumber = Double.valueOf(map1.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map1.get("count")!=null){
					finalCount = Double.valueOf(map1.get("count").toString());
				}
				String gongwei = (String) map1.get("gongwei");
				String shebeiNo = (String) map1.get("shebeino");
				String processStatus = (String) map1.get("processstatus");
				String firstApplyDate = (String) map1.get("firstapplydate");
				String sumitApplyDate = (String) map1.get("sumitapplydate");
				String productStyle =(String) map1.get("productStyle");
				String banBenNumber = (String) map1.get("banBenNumber");
				String needFinalDate = (String) map1.get("needFinalDate");
				
				double procesdianshu = 0d;
				if(map1.get("procesdianshu")!=null){
					procesdianshu = Double.valueOf(map1.get("procesdianshu").toString());
				}
				Integer procardId = (Integer) map1.get("id");
				ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc1.setFinalCount(finalCount);
				pc1.setProcesdianshu(procesdianshu);
				pc1.setNeedFinalDate(needFinalDate);
				if(procardId!=null){
					String 	mrpjihuoDate =   (String) totalDao.getObjectByCondition(" select addtime from ProcardBl where procardId =? ", procardId);
					pc1.setMrpjihuoDate(mrpjihuoDate);
				}
				Float orderNum = (Float) totalDao.getObjectByCondition(" select  filnalCount  from Procard where orderNumber=? and procardStyle='总成'", pc1.getOrderNumber());
				pc1.setOrderNum(orderNum.doubleValue());
				pclist.add(pc1);
			}
		}

		map.put(1, pclist);
		map.put(2, count);
		return map;
	}
	
	@Override
	public Map<Integer, Object> findPcCondition(ProcessCollect pc, int pageNo,
			int pageSize,String startDate,String endDate,Screen screen,String productStyle_str) {
		if (pc == null) {
			pc = new ProcessCollect();
		}
		//拼接工位
		String hql_gongwei="";
		if(screen!=null){
			List<TaSopGongwei> list =new ArrayList<TaSopGongwei>()  ;
			String screenHql = "";
			if(screen.getId()!=null) {
				screenHql = " from Screen where id="+screen.getId() ;
			}else {
				screenHql = totalDao.criteriaQueries(screen,null);
			}
			Screen s = (Screen) totalDao.getObjectByCondition(screenHql);
			for (TaSopGongwei taSopGongwei : s.getGongweis()) {
				list.add(taSopGongwei);
			}
			if(list!=null&&list.size()>0){
			String gongwei ="";
			for(TaSopGongwei taSopGongwei :list){
				if("".equals(gongwei)){
					gongwei +="'"+taSopGongwei.getGongweihao()+"'";
				}else{
					gongwei +=","+"'"+taSopGongwei.getGongweihao()+"'";
				}
			}
			hql_gongwei=" and d.gongwei in("+gongwei+")";
			}
		}
		String sql = " SELECT c.ywMarkId,c.proName,c.orderNumber, c.markid,c.selfcard,d.gongwei,"
				+" c.count,c.productStyle,"
				+ "d.submitnumber,d.usercodes,d.usernames,d.shebeino,"
				+ "d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ "d.processname ,d.processstatus,d.procesdianshu,c.id,c.banBenNumber,c.needFinalDate from  ta_sop_w_procard "
				+ "c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeino,a.submitnumber,b.procesdianshu "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id where 2=2 and a.firstApplyDate like '"+Util.getDateTime("yyyy-MM-dd")+"%')"
				+ " d ON c.id = d.fk_procardId  where 1=1  "+hql_gongwei;

		String hql1 = totalDao.criteriaQueries(pc, null,"processName","procesdianshu");
		String[] array = hql1.split(" where 1=1");
		if (array != null && array.length == 2) {
			sql += array[1] ;
		}
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		int count = 0;
		List<Map> list2 = totalDao.findBySql(sql+ " order by d.sumitapplydate   desc");
		if (list2 != null && list2.size() > 0) {
			count = list2.size();
		}
		List<Map> list = totalDao.findBySql(sql+" order by d.sumitapplydate   desc", pageNo, pageSize);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map1 = list.get(i);
				String usercodes = (String) map1.get("usercodes");
				String usernames = (String) map1.get("usernames");
				String markId = (String) map1.get("markId");
				String selfCard = (String) map1.get("selfCard");
				Integer processNO = (Integer) map1.get("processno");
				String processName = (String) map1.get("processname");
				String ywmarkId = (String) map1.get("ywMarkId");
				String orderNumber = (String) map1.get("orderNumber");
				String proName = (String)map1.get("proName");
				String banBenNumber = (String)map1.get("banBenNumber");
				double tjNumber = 0d;
				if (map1.get("submitnumber") != null) {
					tjNumber = Double.valueOf(map1.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map1.get("count")!=null){
					finalCount = Double.valueOf(map1.get("count").toString());
				}
				String gongwei = (String) map1.get("gongwei");
				String shebeiNo = (String) map1.get("shebeino");
				String processStatus = (String) map1.get("processstatus");
				String firstApplyDate = (String) map1.get("firstapplydate");
				String sumitApplyDate = (String) map1.get("sumitapplydate");
				String productStyle =(String) map1.get("productStyle");
				String needFinalDate =(String) map1.get("needFinalDate");
				double procesdianshu = 0d;
				if(map1.get("procesdianshu")!=null){
					procesdianshu = Double.valueOf(map1.get("procesdianshu").toString());
				}
				Integer procardId = (Integer) map1.get("id");
				ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc1.setFinalCount(finalCount);
				pc1.setProcesdianshu(procesdianshu);
				pc1.setNeedFinalDate(needFinalDate);
				if(procardId!=null){
					String 	mrpjihuoDate =   (String) totalDao.getObjectByCondition(" select addtime from ProcardBl where procardId =? ", procardId);
					pc1.setMrpjihuoDate(mrpjihuoDate);
				}
				Float orderNum = (Float) totalDao.getObjectByCondition(" select  filnalCount  from Procard where orderNumber=? and procardStyle='总成'", pc1.getOrderNumber());
				pc1.setOrderNum(orderNum.doubleValue());
				pclist.add(pc1);
			}
		}

		map.put(1, pclist);
		map.put(2, count);
		return map;
	}

	// 获得总数量；
	@Override
	public int getcont() {
		String sql = "select count(*) count from  ta_sop_w_procard "
				+ " c JOIN (SELECT a.usernames,b.processNO,b.processName,"
				+ "a.usercodes,b.processStatus,a.firstApplyDate,a.sumitApplyDate,"
				+ "b.fk_procardId ,a.shebeiNo from ta_sop_w_ProcessInforReceiveLog "
				+ "a JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id) "
				+ "d ON c.id = d.fk_procardId";
		
		List<Map> list = totalDao.findBySql(sql);
		if (list != null && list.size() > 0) {
			Map map = list.get(0);
			//Integer count =  (Integer) map.get("count");sqlServer
			BigInteger count =		(BigInteger) map.get("count"); // mysql
			
			return count.intValue();
		}
		return 0;
	}

	public void explorExcel(ProcessCollect pc, String startDate,
			String endDate, String status,String workShop,String productStyle_str) {
		if (pc == null) {
			pc = new ProcessCollect();
		}
		String productStyle_sql = "";
		if(productStyle_str!=null&&!"".equals(productStyle_str)){
			productStyle_sql = " and c.productStyle ='"+productStyle_str+"'";
		}
		//拼接工位
		String hql_gongwei="";
		if(workShop!=null&&!"".equals(workShop)){
			List<TaSopGongwei> list =new ArrayList<TaSopGongwei>()  ;
			Screen s = (Screen) totalDao.getObjectByCondition("from Screen where name =?", workShop);
			for (TaSopGongwei taSopGongwei : s.getGongweis()) {
				list.add(taSopGongwei);
			}
			if(list!=null&&list.size()>0){
			String gongwei ="";
			for(TaSopGongwei taSopGongwei :list){
				if("".equals(gongwei)){
					gongwei +="'"+taSopGongwei.getGongweihao()+"'";
				}else{
					gongwei +=","+"'"+taSopGongwei.getGongweihao()+"'";
				}
			}
			hql_gongwei=" and d.gongwei in("+gongwei+")";
			}
		}
		//拼接工序名
		String hql_process = "";
		String process0 = "";
		if(pc.getProcessName()!=null&&!"".equals(pc.getProcessName())){
			process0 =  pc.getProcessName();
			String[] processNames = pc.getProcessName().split(",");
			if(processNames!=null&&processNames.length>0){
			String process ="";
			for(int i=0;i<processNames.length;i++){
				process+=","+"'"+processNames[i]+"'";
			}
			if(process.length()>1){
				process = process.substring(1);
			}
			hql_process=" and d.processname in("+process+")";
			}
			pc.setProcessName(null);
		}
		
		String sql = " SELECT c.ywMarkId,c.orderNumber,c.proName ,c.markid,c.selfcard,d.gongwei,"
				+" c.count,c.productStyle,"
				+ "d.submitnumber,d.usercodes,d.usernames,d.shebeino,"
				+ "d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ "d.processname ,d.processstatus,d.procesdianshu,c.id,c.needFinalDate from  ta_sop_w_procard "
				+ "c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeino,a.submitnumber,b.procesdianshu "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id)"
				+ " d ON c.id = d.fk_procardId  where 1=1  "+hql_gongwei+productStyle_sql+hql_process;

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		String hql1 = totalDao.criteriaQueries(pc, "sumitapplydate", between,
				null);
		pc.setProcessName(process0);
		String[] array = hql1.split(" where 1=1");
		if (array != null && array.length == 2) {
			sql += array[1] + " order by d.sumitApplyDate ,d.processno desc";
		}
		List<Map> list = totalDao.findBySql(sql);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map1 = list.get(i);
				String usercodes = (String) map1.get("usercodes");
				String usernames = (String) map1.get("usernames");
				String markId = (String) map1.get("markId");
				String selfCard = (String) map1.get("selfCard");
				Integer processNO = (Integer) map1.get("processno");
				String processName = (String) map1.get("processname");
				String ywmarkId = (String) map1.get("ywMarkId");
				String orderNumber = (String) map1.get("orderNumber");
				String proName = (String) map1.get("proName");
				double tjNumber = 0d;
				if (map1.get("submitnumber") != null) {
					tjNumber =Double.parseDouble(map1.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map1.get("count")!=null){
					finalCount = Double.valueOf(map1.get("count").toString());
				}
				String gongwei = (String) map1.get("gongwei");
				String shebeiNo = (String) map1.get("shebeino");
				String processStatus = (String) map1.get("processstatus");
				String firstApplyDate = (String) map1.get("firstapplydate");
				String sumitApplyDate = (String) map1.get("sumitapplydate");
				String productStyle =(String)map1.get("productStyle");
				String banBenNumber = (String)map1.get("banBenNumber");
				String needFinalDate = (String)map1.get("needFinalDate");
				double procesdianshu = 0d;
				if(map1.get("procesdianshu")!=null){
					procesdianshu = Double.valueOf(map1.get("procesdianshu").toString());
				}
				Integer procardId = (Integer) map1.get("id");
				ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc1.setFinalCount(finalCount);
				pc1.setProcesdianshu(procesdianshu);
				if(procardId!=null){
					String 	mrpjihuoDate =   (String) totalDao.getObjectByCondition(" select addtime from ProcardBl where procardId =? ", procardId);
					pc1.setMrpjihuoDate(mrpjihuoDate);
				}
				Float orderNum = (Float) totalDao.getObjectByCondition(" select  filnalCount  from Procard where orderNumber=? and procardStyle='总成'", pc1.getOrderNumber());
				pc1.setOrderNum(orderNum.doubleValue());
				pc1.setNeedFinalDate(needFinalDate);
				pclist.add(pc1);
			}
		}

		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("工序汇总信息".getBytes("GB2312"), "8859_1")
					+ ".xlsx");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("工序汇总信息", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "工序汇总信息", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 16, 0);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "工号", wc));
			ws.addCell(new jxl.write.Label(2, 1, "操作工", wc));
			ws.addCell(new jxl.write.Label(3, 1, "产品编码", wc));
			ws.addCell(new jxl.write.Label(4, 1, "销售订单号", wc));
			ws.addCell(new jxl.write.Label(5, 1, "图号", wc));
			ws.addCell(new jxl.write.Label(6, 1, "产品名称", wc));
			ws.addCell(new jxl.write.Label(7, 1, "批次号", wc));
			ws.addCell(new jxl.write.Label(8, 1, "工序代码", wc));
			ws.addCell(new jxl.write.Label(9, 1, "工序名称", wc));
			ws.addCell(new jxl.write.Label(10, 1, "工序点数", wc));
			ws.addCell(new jxl.write.Label(11, 1, "工序次数", wc));
			ws.addCell(new jxl.write.Label(12, 1, "订单数量", wc));
			ws.addCell(new jxl.write.Label(13, 1, "需求数量", wc));
			ws.addCell(new jxl.write.Label(14, 1, "合格数量", wc));
			ws.addCell(new jxl.write.Label(15, 1, "工位", wc));
			ws.addCell(new jxl.write.Label(16, 1, "设备编号", wc));
			ws.addCell(new jxl.write.Label(17, 1, "是否并行", wc));
			ws.addCell(new jxl.write.Label(18, 1, "日期", wc));
			ws.addCell(new jxl.write.Label(19, 1, "投产日期", wc));
			ws.addCell(new jxl.write.Label(20, 1, "实际完工日期", wc));
			ws.addCell(new jxl.write.Label(21, 1, "生产类型", wc));
			ws.addCell(new jxl.write.Label(22, 1, "计划日期", wc));
			for (int i = 0,len=pclist.size(); i <len ; i++) {
				ProcessCollect pc1 = (ProcessCollect) pclist.get(i);
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, pc1.getUsercodes(), wc));
				ws.addCell(new Label(2, i + 2, pc1.getUsernames(), wc));
				ws.addCell(new Label(3, i + 2, pc1.getYwmarkId(), wc));
				ws.addCell(new Label(4, i + 2, pc1.getOrderNumber(), wc));
				ws.addCell(new Label(5, i + 2, pc1.getMarkId(), wc));
				ws.addCell(new Label(6, i + 2, pc1.getProName(), wc));
				ws.addCell(new Label(7, i + 2, pc1.getSelfCard(), wc));
				ws.addCell(new jxl.write.Number(8, i + 2, pc1.getProcessNO(),
						wc));
				ws.addCell(new Label(9, i + 2, pc1.getProcessName(), wc));
				ws
				.addCell(new jxl.write.Number(10, i + 2, pc1
						.getProcesdianshu(), wc));
				ws
				.addCell(new jxl.write.Number(11, i + 2, pc1
						.getProcesdianshu()* pc1.getTjNumber(), wc));
				ws
				.addCell(new jxl.write.Number(12, i + 2, pc1
						.getOrderNum(), wc));
				ws
				.addCell(new jxl.write.Number(13, i + 2, pc1
						.getFinalCount(), wc));
				ws
						.addCell(new jxl.write.Number(14, i + 2, pc1
								.getTjNumber(), wc));
				ws.addCell(new Label(15, i + 2, pc1.getGongwei(), wc));
				ws.addCell(new Label(16, i + 2, pc1.getShebeiNo(), wc));
				ws.addCell(new Label(17, i + 2, pc1.getProcessStatus(), wc));
				ws.addCell(new Label(18, i + 2, pc1.getFirstApplyDate(), wc));
				ws.addCell(new Label(19, i + 2, pc1.getMrpjihuoDate(), wc));
				ws.addCell(new Label(20, i + 2, pc1.getSumitApplyDate(), wc));
				ws.addCell(new Label(21, i + 2, pc1.getProductStyle(), wc));
				ws.addCell(new Label(21, i + 2, pc1.getNeedFinalDate(), wc));
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
	public void explorExcel1(ProcessCollect pc, String startDate,
			String endDate, String status,String workShop) {
		if (pc == null) {
			pc = new ProcessCollect();
		}
		String sql = " SELECT c.ywMarkId,c.orderNumber,c.proName,c.markid,c.selfcard,d.gongwei,"
				+" c.count,productStyle,"
				+ "d.submitNumber,d.usercodes,d.usernames,d.shebeiNo,"
				+ " d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ " d.processname ,d.processstatus,c.needFinalDate from  ta_sop_w_procard "
				+ " c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeiNo,a.submitNumber "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id)"
				+ " d ON c.id = d.fk_procardId  where 1=1  ";

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		String hql1 = totalDao.criteriaQueries(pc, "firstApplyDate", between,
				null);
		String[] array = hql1.split(" where 1=1");
		if (array != null && array.length == 2) {
			sql += array[1] + " order by d.sumitApplyDate ,d.processNO desc";
		}
		List<Map> list = totalDao.findBySql(sql);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map1 = list.get(i);
				String usercodes = (String) map1.get("usercodes");
				String usernames = (String) map1.get("usernames");
				String markId = (String) map1.get("markId");
				String selfCard = (String) map1.get("selfCard");
				Integer processNO = (Integer) map1.get("processno");
				String processName = (String) map1.get("processname");
				String ywmarkId = (String) map1.get("ywMarkId");
				String orderNumber = (String) map1.get("orderNumber");
				String proName = (String) map1.get("proName");
				double tjNumber = 0d;
				if (map1.get("submitnumber") != null) {
					tjNumber =Double.parseDouble(map1.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map1.get("count")!=null){
					finalCount = Double.valueOf(map1.get("count").toString());
				}
				String gongwei = (String) map1.get("gongwei");
				String shebeiNo = (String) map1.get("shebeino");
				String processStatus = (String) map1.get("processstatus");
				String firstApplyDate = (String) map1.get("firstapplydate");
				String sumitApplyDate = (String) map1.get("sumitapplydate");
				String productStyle = (String) map1.get("productStyle");
				String banBenNumber = (String) map1.get("banBenNumber");
				String needFinalDate = (String) map1.get("needFinalDate");
				ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc1.setFinalCount(finalCount);
				pc1.setNeedFinalDate(needFinalDate);
				pclist.add(pc1);
			}

		}
		Map<String, Double[]> map = new HashMap<String, Double[]>();
		if (pclist != null && pclist.size() > 0) {
			for (ProcessCollect pct : pclist) {
				String str = pct.getUsercodes() + "_"+pct.getUsernames()+"_"+pct.getOrderNumber()
				+"_"+pct.getYwmarkId()+"_"+ pct.getMarkId() + "_"+pct.getProName()+"_"
						+ pct.getProcessNO()+"_"+pct.getProcessName();
				if (map.get(str) == null) {
					Double[] numbers = {pct.getFinalCount(),pct.getTjNumber()}; 
					map.put(str, numbers);
				} else {
					Double[] numbers = map.get(str);
					numbers[0]=numbers[0]+pct.getFinalCount();
					numbers[1]=numbers[1]+pct.getTjNumber();
					map.put(str, numbers);
				}
			}
		}
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("工序汇总信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("工序汇总信息", 0);
			ws.setColumnView(5, 20);
			ws.setColumnView(4, 25);
			ws.setColumnView(3, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setAlignment(Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "工序汇总信息", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 4, 0);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "工号", wc));
			ws.addCell(new jxl.write.Label(2, 1, "姓名", wc));
			ws.addCell(new jxl.write.Label(3, 1, "内部订单号", wc));
			ws.addCell(new jxl.write.Label(4, 1, "业务件号", wc));
			ws.addCell(new jxl.write.Label(5, 1, "件号", wc));
			ws.addCell(new jxl.write.Label(6, 1, "产品名称", wc));
			ws.addCell(new jxl.write.Label(7, 1, "工序代码", wc));
			ws.addCell(new jxl.write.Label(8, 1, "工序名", wc));
			ws.addCell(new jxl.write.Label(9, 1, "需求数量", wc));
			ws.addCell(new jxl.write.Label(10, 1, "完成数量", wc));
			int count = -1;
			for (Map.Entry<String, Double[]> entry : map.entrySet()) {
				count++;
				String str = entry.getKey();
				String[] arrays = str.split("_");
				Double[] numbers  = entry.getValue();
				ws.addCell(new jxl.write.Number(0, count + 2, count + 1, wc));
				if (arrays != null && arrays.length == 8) {
					ws.addCell(new Label(1, count + 2, arrays[0], wc));
					ws.addCell(new Label(2, count + 2, arrays[1], wc));
					ws.addCell(new Label(3, count + 2, arrays[2], wc));
					ws.addCell(new Label(4, count + 2, arrays[3], wc));
					ws.addCell(new Label(5, count + 2, arrays[4], wc));
					ws.addCell(new Label(6, count + 2, arrays[5], wc));
					ws.addCell(new Label(7, count + 2, arrays[6], wc));
					ws.addCell(new Label(8, count + 2, arrays[7], wc));
				}
				ws.addCell(new jxl.write.Number(9, count + 2, numbers[0], wc));
				ws.addCell(new jxl.write.Number(10, count + 2, numbers[1], wc));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}



	//工序汇总 普通导出
    @Override
    public void explorExcelbyPoi(ProcessCollect pc, String startDate,
                                 String endDate, String status, String workShop, String productStyle_str) {
        if (pc == null) {
            pc = new ProcessCollect();
        }
        String productStyle_sql = "";
        if(productStyle_str!=null&&!"".equals(productStyle_str)){
            productStyle_sql = " and c.productStyle ='"+productStyle_str+"'";
        }
        //拼接工位
        String hql_gongwei="";
        if(workShop!=null&&!"".equals(workShop)){
            List<TaSopGongwei> list =new ArrayList<TaSopGongwei>()  ;
            Screen s = (Screen) totalDao.getObjectByCondition("from Screen where name =?", workShop);
            for (TaSopGongwei taSopGongwei : s.getGongweis()) {
                list.add(taSopGongwei);
            }
            if(list!=null&&list.size()>0){
                String gongwei ="";
                for(TaSopGongwei taSopGongwei :list){
                    if("".equals(gongwei)){
                        gongwei +="'"+taSopGongwei.getGongweihao()+"'";
                    }else{
                        gongwei +=","+"'"+taSopGongwei.getGongweihao()+"'";
                    }
                }
                hql_gongwei=" and d.gongwei in("+gongwei+")";
            }
        }
        //拼接工序名
        String hql_process = "";
        String process0 = "";
        if(pc.getProcessName()!=null&&!"".equals(pc.getProcessName())){
            process0 =  pc.getProcessName();
            String[] processNames = pc.getProcessName().split(",");
            if(processNames!=null&&processNames.length>0){
                String process ="";
                for(int i=0;i<processNames.length;i++){
                    process+=","+"'"+processNames[i]+"'";
                }
                if(process.length()>1){
                    process = process.substring(1);
                }
                hql_process=" and d.processname in("+process+")";
            }
            pc.setProcessName(null);
        }

        String sql = " SELECT c.ywMarkId,c.orderNumber,c.proName ,c.markid,c.selfcard,d.gongwei,"
                +" c.count,c.productStyle,"
                + "d.submitnumber,d.usercodes,d.usernames,d.shebeino,"
                + "d.firstapplydate,d.sumitapplydate ,d.processno,"
                + "d.processname ,d.processstatus,d.procesdianshu,c.needFinalDate from  ta_sop_w_procard "
                + "c JOIN (SELECT a.usernames,b.processno,b.processname,"
                + "a.usercodes,b.processstatus,a.firstapplydate,"
                + "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeino,a.submitnumber,b.procesdianshu "
                + "from ta_sop_w_ProcessInforReceiveLog a "
                + "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id)"
                + " d ON c.id = d.fk_procardId  where 1=1  "+hql_gongwei+productStyle_sql+hql_process;

        String[] between = new String[2];
        if (null != startDate && null != endDate && !"".equals(endDate)
                && !"".equals(startDate)) {
            between[0] = startDate;
            between[1] = endDate;
        }
        String hql1 = totalDao.criteriaQueries(pc, "sumitapplydate", between,
                null);
        pc.setProcessName(process0);
        String[] array = hql1.split(" where 1=1");
        if (array != null && array.length == 2) {
            sql += array[1] + " order by d.sumitApplyDate ,d.processno desc";
        }
        List<Map> list = totalDao.findBySql(sql);
        List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map map1 = list.get(i);
                String usercodes = (String) map1.get("usercodes");
                String usernames = (String) map1.get("usernames");
                String markId = (String) map1.get("markId");
                String selfCard = (String) map1.get("selfCard");
                Integer processNO = (Integer) map1.get("processno");
                String processName = (String) map1.get("processname");
                String ywmarkId = (String) map1.get("ywMarkId");
                String orderNumber = (String) map1.get("orderNumber");
                String proName = (String) map1.get("proName");
                double tjNumber = 0d;
                if (map1.get("submitnumber") != null) {
                    tjNumber =Double.parseDouble(map1.get("submitnumber").toString());
                }
                double finalCount = 0d;
                if(map1.get("count")!=null){
                    finalCount = Double.valueOf(map1.get("count").toString());
                }
                String gongwei = (String) map1.get("gongwei");
                String shebeiNo = (String) map1.get("shebeino");
                String processStatus = (String) map1.get("processstatus");
                String firstApplyDate = (String) map1.get("firstapplydate");
                String sumitApplyDate = (String) map1.get("sumitapplydate");
                String productStyle =(String)map1.get("productStyle");
                String banBenNumber = (String) map1.get("banBenNumber");
                String needFinalDate = (String) map1.get("needFinalDate");
                double procesdianshu = 0d;
                if(map1.get("procesdianshu")!=null){
                    procesdianshu = Double.valueOf(map1.get("procesdianshu").toString());
                }
                ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
                        markId, selfCard, processNO, processName, tjNumber,
                        gongwei, shebeiNo, processStatus, firstApplyDate,
                        sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
                pc1.setFinalCount(finalCount);
                pc1.setProcesdianshu(procesdianshu);
                pc1.setNeedFinalDate(needFinalDate);
                pclist.add(pc1);
            }
        }

        try {
            //set response
            HttpServletResponse response = (HttpServletResponse) ActionContext
                    .getContext().get(ServletActionContext.HTTP_RESPONSE);
            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String("工序汇总信息".getBytes("GB2312"), "8859_1")
                    + ".xlsx");
            response.setContentType("application/msexcel");
            //set response end

            //start format excel
            //创建工作簿
            //用SXSSFWorkbook 大数据Write
            SXSSFWorkbook workBook= new SXSSFWorkbook(50000);
//			XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表
//			XSSFSheet sheet = workBook.createSheet("工序汇总信息");
            Sheet sheet = workBook.createSheet("工序汇总信息");
            //创建行
//			XSSFRow row = sheet.createRow(2);
            Row row = sheet.createRow(2);

            //set Titile
            //创建合并单元格对象
            CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 1, 10);
            //创建样式
//			XSSFCellStyle style = workBook.createCellStyle();
            CellStyle style =workBook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
//			style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
            //创建字体
//			XSSFFont font = workBook.createFont();
            Font font = workBook.createFont();
            font.setFontHeightInPoints((short) 16);
            //font.setFontHeight((short)320); 效果和上面一样。用这个方法设置大小，值要设置为字体大小*20倍，具体看API文档
            font.setBold(true);
            style.setFont(font);
            sheet.addMergedRegion(rangeAddress);
            row = sheet.createRow(0);
//			XSSFCell cell = row.createCell(1);
            Cell cell =row.createCell(1);
            cell.setCellValue("工序汇总信息");
            cell.setCellStyle(style);
            //set Titile End
            row = sheet.createRow(1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("序号");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("工号");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("操作工");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("产品编码");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("销售订单号");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("图号");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("产品名称");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("批次号");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("工序代码");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("工序名称");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("工序点数");
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("工序次数");
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("需求数量");
            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("合格数量");
            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("工位");
            cell = row.createCell(15, CellType.STRING);
            cell.setCellValue("设备编号");
            cell = row.createCell(16, CellType.STRING);
            cell.setCellValue("是否并行");
            cell = row.createCell(17, CellType.STRING);
            cell.setCellValue("日期");
            cell = row.createCell(18, CellType.STRING);
            cell.setCellValue("实际完工日期");
            cell = row.createCell(19, CellType.STRING);
            cell.setCellValue("生产类型");

            for (int i = 0; i < pclist.size(); i++) {
                ProcessCollect pc1 = (ProcessCollect) pclist.get(i);
                row = sheet.createRow(i+2);
                    cell = row.createCell(0, CellType.STRING);
                    cell.setCellValue(i + 1);
                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(pc1.getUsercodes());
                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(pc1.getUsernames());
                    cell = row.createCell(3, CellType.STRING);
                    cell.setCellValue(pc1.getYwmarkId());
                    cell = row.createCell(4, CellType.STRING);
                    cell.setCellValue(pc1.getOrderNumber());
                    cell = row.createCell(5, CellType.STRING);
                    cell.setCellValue(pc1.getMarkId());
                    cell = row.createCell(6, CellType.STRING);
                    cell.setCellValue(pc1.getProName());
                    cell = row.createCell(7, CellType.STRING);
                    cell.setCellValue(pc1.getSelfCard());
                    cell = row.createCell(8, CellType.STRING);
                    cell.setCellValue(pc1.getProcessNO());
                    cell = row.createCell(9, CellType.STRING);
                    cell.setCellValue(pc1.getProcessName());
                    cell = row.createCell(10, CellType.STRING);
                    cell.setCellValue(pc1
                            .getProcesdianshu());
                    cell = row.createCell(11, CellType.STRING);
                    cell.setCellValue(pc1
                            .getProcesdianshu()* pc1.getTjNumber());
                    cell = row.createCell(12, CellType.STRING);
                    cell.setCellValue(pc1
                            .getFinalCount());
                    cell = row.createCell(13, CellType.STRING);
                    cell.setCellValue(pc1
                            .getTjNumber());
                    cell = row.createCell(14, CellType.STRING);
                    cell.setCellValue(pc1.getGongwei());
                    cell = row.createCell(15, CellType.STRING);
                    cell.setCellValue(pc1.getShebeiNo());
                    cell = row.createCell(16, CellType.STRING);
                    cell.setCellValue(pc1.getProcessStatus());
                    cell = row.createCell(17, CellType.STRING);
                    cell.setCellValue(pc1.getFirstApplyDate());
                    cell = row.createCell(18, CellType.STRING);
                    cell.setCellValue(pc1.getSumitApplyDate());
                    cell = row.createCell(19, CellType.STRING);
                    cell.setCellValue(pc1.getProductStyle());
            }
            workBook.write(os);
            workBook.close();//记得关闭工作簿
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


/*
    * @author fy
　　* @date 2018/6/13 11:23
　　* @Description: 工序汇总 汇总导出
　　* @param [pc, startDate, endDate, status, workShop]
　　* @return java.lang.String
　　* @throws
　　*/
	@Override
	public String explorExcelbyPoi(ProcessCollect pc, String startDate,
								   String endDate, String status,String workShop) {

		if (pc == null) {
			pc = new ProcessCollect();
		}
		String sql = " SELECT c.ywMarkId,c.orderNumber,c.proName,c.markid,c.selfcard,d.gongwei,"
				+" c.count,productStyle,"
				+ "d.submitNumber,d.usercodes,d.usernames,d.shebeiNo,"
				+ " d.firstapplydate,d.sumitapplydate ,d.processno,"
				+ " d.processname ,d.processstatus from  ta_sop_w_procard "
				+ " c JOIN (SELECT a.usernames,b.processno,b.processname,"
				+ "a.usercodes,b.processstatus,a.firstapplydate,"
				+ "a.sumitapplydate,b.fk_procardid, a.gongwei,b.shebeiNo,a.submitNumber "
				+ "from ta_sop_w_ProcessInforReceiveLog a "
				+ "JOIN ta_sop_w_ProcessInfor b ON a.fk_processInforId = b.id)"
				+ " d ON c.id = d.fk_procardId  where 1=1  ";

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		String hql1 = totalDao.criteriaQueries(pc, "firstApplyDate", between,
				null);
		String[] array = hql1.split(" where 1=1");
		if (array != null && array.length == 2) {
			sql += array[1] + " order by d.sumitApplyDate ,d.processNO desc";
		}
		List<Map> list = totalDao.findBySql(sql);
		List<ProcessCollect> pclist = new ArrayList<ProcessCollect>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map1 = list.get(i);
				String usercodes = (String) map1.get("usercodes");
				String usernames = (String) map1.get("usernames");
				String markId = (String) map1.get("markId");
				String selfCard = (String) map1.get("selfCard");
				Integer processNO = (Integer) map1.get("processno");
				String processName = (String) map1.get("processname");
				String ywmarkId = (String) map1.get("ywMarkId");
				String orderNumber = (String) map1.get("orderNumber");
				String proName = (String) map1.get("proName");
				double tjNumber = 0d;
				if (map1.get("submitnumber") != null) {
					tjNumber =Double.parseDouble(map1.get("submitnumber").toString());
				}
				double finalCount = 0d;
				if(map1.get("count")!=null){
					finalCount = Double.valueOf(map1.get("count").toString());
				}
				String gongwei = (String) map1.get("gongwei");
				String shebeiNo = (String) map1.get("shebeino");
				String processStatus = (String) map1.get("processstatus");
				String firstApplyDate = (String) map1.get("firstapplydate");
				String sumitApplyDate = (String) map1.get("sumitapplydate");
				String productStyle = (String) map1.get("productStyle");
				String banBenNumber = (String) map1.get("banBenNumber");
				ProcessCollect pc1 = new ProcessCollect(usercodes, usernames,
						markId, selfCard, processNO, processName, tjNumber,
						gongwei, shebeiNo, processStatus, firstApplyDate,
						sumitApplyDate,ywmarkId,orderNumber,proName,productStyle,banBenNumber);
				pc1.setFinalCount(finalCount);
				pclist.add(pc1);
			}

		}
		Map<String, Double[]> map = new HashMap<String, Double[]>();
		if (pclist != null && pclist.size() > 0) {
			for (ProcessCollect pct : pclist) {
				String str = pct.getUsercodes() + "_"+pct.getUsernames()+"_"+pct.getOrderNumber()
						+"_"+pct.getYwmarkId()+"_"+ pct.getMarkId() + "_"+pct.getProName()+"_"
						+ pct.getProcessNO()+"_"+pct.getProcessName();
				if (map.get(str) == null) {
					Double[] numbers = {pct.getFinalCount(),pct.getTjNumber()};
					map.put(str, numbers);
				} else {
					Double[] numbers = map.get(str);
					numbers[0]=numbers[0]+pct.getFinalCount();
					numbers[1]=numbers[1]+pct.getTjNumber();
					map.put(str, numbers);
				}
			}
		}


		try {
			//set response
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("工序汇总信息".getBytes("GB2312"), "8859_1")
					+ ".xlsx");
			response.setContentType("application/msexcel");
			//set response end


			//start format excel
			//创建工作簿
            //用SXSSFWorkbook 大数据Write

			SXSSFWorkbook workBook= new SXSSFWorkbook(50000);
//			XSSFWorkbook workBook = new XSSFWorkbook();
			//创建工作表
//			XSSFSheet sheet = workBook.createSheet("工序汇总信息");
			 Sheet sheet = workBook.createSheet("工序汇总信息");
			//创建行
//			XSSFRow row = sheet.createRow(2);
			Row row = sheet.createRow(2);





			//set Titile
			//创建合并单元格对象
			CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 1, 10);
			//创建样式
//			XSSFCellStyle style = workBook.createCellStyle();
			CellStyle style =workBook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
//			style.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
			//创建字体
//			XSSFFont font = workBook.createFont();
			Font font = workBook.createFont();
			font.setFontHeightInPoints((short) 16);
			//font.setFontHeight((short)320); 效果和上面一样。用这个方法设置大小，值要设置为字体大小*20倍，具体看API文档
			font.setBold(true);
			style.setFont(font);
			sheet.addMergedRegion(rangeAddress);

			row = sheet.createRow(0);
//			XSSFCell cell = row.createCell(1);
			Cell cell =row.createCell(1);
			cell.setCellValue("工序汇总信息");
			cell.setCellStyle(style);
			//set Titile End



			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("序号");
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("工号");
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("姓名");
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("内部订单号");
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("业务件号");
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("件号");
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue("产品名称");
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue("工序代码");
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue("工序名");
			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue("需求数量");
			cell = row.createCell(10, CellType.STRING);
			cell.setCellValue("完成数量");
			int count = -1;
			for (Map.Entry<String, Double[]> entry : map.entrySet()) {
				count++;
				String str = entry.getKey();
				String[] arrays = str.split("_");
				Double[] numbers = entry.getValue();
				row = sheet.createRow(count+2);
//				ws.addCell(new jxl.write.Number(0, count + 2, count + 1, wc));
				if (arrays != null && arrays.length == 8) {
					cell = row.createCell(0, CellType.STRING);
					cell.setCellValue(count + 1);
					cell = row.createCell(1, CellType.STRING);
					cell.setCellValue(arrays[0]);
					cell = row.createCell(2, CellType.STRING);
					cell.setCellValue(arrays[1]);
					cell = row.createCell(3, CellType.STRING);
					cell.setCellValue(arrays[2]);
					cell = row.createCell(4, CellType.STRING);
					cell.setCellValue(arrays[3]);
					cell = row.createCell(5, CellType.STRING);
					cell.setCellValue(arrays[4]);
					cell = row.createCell(6, CellType.STRING);
					cell.setCellValue(arrays[5]);
					cell = row.createCell(7, CellType.STRING);
					cell.setCellValue(arrays[6]);
					cell = row.createCell(8, CellType.STRING);
					cell.setCellValue(arrays[7]);
				}
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue(numbers[0]);
				cell = row.createCell(10, CellType.STRING);
				cell.setCellValue(numbers[1]);
			}
			workBook.write(os);
			workBook.close();//记得关闭工作簿
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 查询所有车间
	 */
	public List<String[]> findScreen(){
		List<String[]> seList = totalDao.query("select id,name from Screen");
		return seList;
	}

	@Override
	public List<String> findAllProcessGzstore() {
		List<String>  processList = totalDao.query(" select processName from ProcessGzstore where processName not like '外委%' ");
		return processList;
	}
}
