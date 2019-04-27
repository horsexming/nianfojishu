package com.task.ServerImpl.sop;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.StrutsStatics;
import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.sop.OSWorkServer;
import com.task.Server.sop.ProcardServer;
import com.task.entity.Users;
import com.task.entity.sop.OutSourcingWorkList;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.entity.sop.qd.LogoStickers;
import com.task.util.Util;

/**
 * 外委工作单操作
 * 
 * @author 贾辉辉
 * 
 */
public class OSWorkServerImpl implements OSWorkServer {

	private TotalDao totalDao;
	private ProcardServer procardServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List listwaigouWaiweiPlanId(String waigouWaiweiPlanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List listaddOswGongxu(Integer[] processIds) {
		// TODO Auto-generated method stub
		// String hql =
		// "from ProcessInfor where id in ("+processIds.toString()+") ";
		// List newl = totalDao.query(hql);

		List list = new ArrayList<WaigouWaiweiPlan>(processIds.length);
		for (int i = 0; i < processIds.length; i++) {
			String hql = "from ProcessInfor where id =?";
			List newl = totalDao.query(hql, processIds[i]);
			if (newl.size() > 0) {
				list.add(newl.get(0));
			}
		}
		return list;
	}

	/***
	 * 查询所有外委工序
	 */
	public List listwaigouWaiweiPlanId(Integer id, int[] waigouWaiweiPlanIds) {
		List list = new ArrayList<WaigouWaiweiPlan>(waigouWaiweiPlanIds.length);
		for (int i = 0; i < waigouWaiweiPlanIds.length; i++) {
			String hql = "from WaigouWaiweiPlan where oswId=?";
			List newl = totalDao.query(hql, id);
			OutSourcingWorkList o=(OutSourcingWorkList) newl.get(0);
			if(o.getProcessInforSet() != null&&o.getProcessInforSet().size()>0){
				o.setProcessInforList(new ArrayList(o.getProcessInforSet()));
			}
				list.add(o);
		}
		return list;
	}

	/** 查询所有外委工作单 **/
	@Override
	public Object[] findOSWorkList(OutSourcingWorkList OSWork,
			String startDate, String endDate, Integer cpage, Integer PageSize,
			String tag) {
		// TODO Auto-generated method stub
		String hql = "from OutSourcingWorkList ";
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != OSWork) {
			hql = totalDao
					.criteriaQueries(OSWork, "outSourceTime", between, "");
		}
		if ("jiesuan".equals(tag)) {
			if (hql.contains("where")) {
				hql += " and status='接收' order by outSourceTime desc ";
			} else {
				hql += " where status='接收' order by outSourceTime desc ";
			}
		} else {
			hql += " order by outSourceTime desc";
		}
		Object[] osWorkarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List<OutSourcingWorkList> list = (List<OutSourcingWorkList>)totalDao.findAllByPage(hql, cpage, PageSize);
		if(list.size()>0){
			for(OutSourcingWorkList o:list){
				String ywMarkId=null;
				if(o.getProcessInforSet() != null&&o.getProcessInforSet().size()>0){
					o.setProcessInforList(new ArrayList(o.getProcessInforSet()));
					if(ywMarkId==null){
						ProcessInfor process= o.getProcessInforList().get(0);
						ywMarkId =(String) totalDao.getObjectByCondition("select ywMarkId from Procard where id=?", process.getProcard().getRootId());
						o.setYwMarkId(ywMarkId);
					}
				}
			}
		}
		osWorkarr[0] = count;
		osWorkarr[1] = list;
		return osWorkarr;
	}

	/** 根据属性 查找下拉数据列表 **/
	@Override
	public String findSelectList(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "select distinct(" + tag
					+ ") from OutSourcingWorkList";
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	/** 根据条码查找外委工作单对象 **/
	@Override
	public OutSourcingWorkList getOSWorkByByBarcode(String barcode, String tag) {
		// TODO Auto-generated method stub
		String hql = "from OutSourcingWorkList where number=?";
		List list = totalDao.query(hql, barcode);
		OutSourcingWorkList osw = new OutSourcingWorkList();
		if (list.size() > 0 && list != null) {
			osw = (OutSourcingWorkList) list.get(0);
			if(osw.getProcessInforSet() != null&&osw.getProcessInforSet().size()>0){
				osw.setProcessInforList(new ArrayList(osw.getProcessInforSet()));
			}
		}
		return osw;
	}
	/** 根据ID获取外委工作单对象 **/
	@Override
	public OutSourcingWorkList getOSWorkByID(Integer id, String tag) {
		// TODO Auto-generated method stub
		OutSourcingWorkList osw = (OutSourcingWorkList) totalDao.getObjectById(
				OutSourcingWorkList.class, id);
		String ywMarkId = (String)totalDao.getObjectByCondition("select ywMarkId from Procard where id=(select rootId from Procard where markId=? and selfCard=?)", osw.getMarkID(),osw.getLotId());
		osw.setYwMarkId(ywMarkId);
		return osw;
	}

	@Override
	public String updateReceiveOSW(OutSourcingWorkList OSWork, String tag) {
		String message = "";
		OutSourcingWorkList os = (OutSourcingWorkList) totalDao.getObjectById(
				OutSourcingWorkList.class, OSWork.getId());
		if (os != null) {
			Users user = Util.getLoginUser();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String op=null;
			if ("外委".equals(tag)) {// 出厂扫描
				os.setStatus("出厂");
				os.setLeaveOutTime(sdf.format(new Date()));
				message = "该外委工作单出厂成功，请继续扫描后续外委单！！";
				op="工序外委，外委单条码为:"+OSWork.getNumber();
			} else if ("出厂".equals(tag)) {// 入厂扫描
				os.setStatus("入厂");
				os.setGetIntoTime(sdf.format(new Date()));
				message = "该外委工作单入厂成功，请继续扫描后续外委单";
				op="工序外委出厂扫描，外委单条码为:"+OSWork.getNumber();
			} else if ("入厂".equals(tag)) {// 接收扫描
				op="工序外委入厂接，外委单条码为:"+OSWork.getNumber();
				float bc = 0f;// 不合格数量
				if (null != OSWork.getBreakCount()
						&& OSWork.getBreakCount() > 0) {
					bc = OSWork.getBreakCount();
					if (os.getBreakCount() != null) {
						bc += os.getBreakCount();// 累计不合格数量
					}
				}
				if (os.getReceiveCount() == null) {
					os.setReceiveCount(0F);
				}
				float receAll = bc + OSWork.getReceiveCount()
						+ os.getReceiveCount();// 总接收数=总报废+总接收
				if (receAll > os.getOutSourceCount()) {
					message = "接收总数量不能大于外委数量";
				} else {
					os.setReturnTime(sdf.format(new Date()));
					os.setReceiveCount(os.getReceiveCount()
							+ OSWork.getReceiveCount());// 接收数量
					os.setBreakCount(bc);// 不合格数量
					os.setReceivePerson(user.getName());
					// 生成补料单
					if (OSWork.getBreakCount() != null
							&& OSWork.getBreakCount() > 0) {
						addLogisticker(OSWork);
					}
					if (receAll == os.getOutSourceCount()) {
						os.setStatus("接收");
					}
					// 处理生产工序
					Set<ProcessInfor> piset = os.getProcessInforSet();
					for (ProcessInfor processInfor : piset) {
						if (receAll == os.getOutSourceCount()) {
							// ----------------------------------
							String hql = "from WaigouWaiweiPlan where oswId=?";
							List newl = totalDao.query(hql, os.getId());
							if (newl != null && newl.size() > 0) {
								for (int i = 0; i < newl.size(); i++) {
									WaigouWaiweiPlan wwp = (WaigouWaiweiPlan) newl
											.get(i);
									wwp.setAcArrivalTime(Util.getDateTime());
									totalDao.update(wwp);
								}
							}
							// -------------------------------------
						}
						// 工序提交数量=工序已提交数量+外委接收数量
						float submit = processInfor.getSubmmitCount() + OSWork.getReceiveCount();
						if(processInfor.getApplyCount()<submit){
							throw new RuntimeException("对不起您接收的数量超过了该工序的已领数量");
						}
						if(processInfor.getProcard().getFilnalCount()<submit){
							throw new RuntimeException("对不起您接收的数量超过了该工序的总数量");
						}
						if (processInfor.getProcard().getFilnalCount() == submit) {
							processInfor.setStatus("完成");
						} else
							processInfor.setStatus("已领");
						processInfor.setSubmmitCount(submit);
						processInfor.setSubmitDate(Util.getDateTime());
						totalDao.update(processInfor);

						// 分析是否是最后一道工序，以及是否需要激活上层数据
						procardServer.updateJihuo(processInfor.getId(),
								processInfor.getProcard().getId(),"");

					}
				}
				message = "该外委工作单接收成功，请确认接收数量扫描后续外委工作单";
			}
			totalDao.update(os);
		}
		return message;
	}

	/***
	 * 添加外委工序
	 */

	public void addOswGongxu(OutSourcingWorkList oSWork,
			int[] waigouWaiweiPlanIds) {
		if (oSWork != null) {
			// oswId
			String number = "";
			String date = Util.getDateTime("yyyyMM");
			String hql = "select max(number) from OutSourcingWorkList";
			Object object = totalDao.getObjectByCondition(hql);
			if (object != null) {
				Long selfCard = Long.parseLong(object.toString()) + 1;// 当前最大流水卡片
				number = selfCard.toString();
			} else {
				number = date + "00001";
			}
			oSWork.setNumber(number);// 条码
			oSWork.setOutSourceTime(Util.getDateTime());// 外委时间.
			Users loginUser = Util.getLoginUser();
			oSWork.setCode(loginUser.getCode());
			oSWork.setDept(loginUser.getDept());
			oSWork.setUsername(loginUser.getName());
			boolean bool = totalDao.save(oSWork);
			Float num = 0F;
			for (int i = 0; i < waigouWaiweiPlanIds.length; i++) {
				WaigouWaiweiPlan wwp = (WaigouWaiweiPlan) totalDao
						.getObjectById(WaigouWaiweiPlan.class,
								waigouWaiweiPlanIds[i]);
				wwp.setStatus("已领");
				wwp.setApplyDate(Util.getDateTime());
				wwp.setOswId(oSWork.getId());
				totalDao.update(oSWork);
				if (i == 0) {
					oSWork.setMarkID(wwp.getMarkId());
					oSWork.setPartName(wwp.getProName());
					oSWork.setLotId(wwp.getSelfCard());
					oSWork.setUnit("件");
				}
				num = num + wwp.getNumber();

			}
			oSWork.setOutSourceCount(num);
			totalDao.update(oSWork);
			// oSWork.setOutSourceCount(num);
			// totalDao.update(oSWork);
		}

	}

	/***
	 * 添加外委工序
	 */
	public Object[] addOswGongxu(OutSourcingWorkList oSWork,
			Integer[] processIds, String WaigouWaiweiPlanId) {
		if (oSWork != null) {
			if (processIds != null && processIds.length > 0) {
				Set<ProcessInfor> proSet = new HashSet<ProcessInfor>();
				for (Integer processId : processIds) {
					ProcessInfor process = (ProcessInfor) totalDao
							.getObjectById(ProcessInfor.class, processId);
					if (process != null) {
						// // 判断上一道工序是否提交
						// ProcessInfor topPro = (ProcessInfor) totalDao
						// .getObjectByCondition(
						// "from ProcessInfor where procard.id=? and processNO<? order by processNO desc",
						// process.getProcard().getId(), process
						// .getProcessNO());
						// 已领数量
						Float outCount = process.getApplyCount()
								+ oSWork.getOutSourceCount();
						process.setApplyCount(outCount);
						process.setStatus("已领");
						process.setFirstApplyDate(Util.getDateTime());
						process.setUsernames(oSWork.getOutScourceComp());//外委供应商作为领取人员
						Set<OutSourcingWorkList> oswSet=process.getOsWork();
						if(oswSet==null){
							oswSet =new HashSet<OutSourcingWorkList>();
						}
						oswSet.add(oSWork);
						process.setOsWork(oswSet);
						proSet.add(process);
					} else {
						return new Object[] { false, "工序信息有误，请重试或联系管理员!" };
					}

				}
				String number = "";
				String date = Util.getDateTime("yyyyMM");
				String hql = "select max(number) from OutSourcingWorkList where number like '"
						+ date + "%'";
				Object object = totalDao.getObjectByCondition(hql);
				if (object != null) {
					Long selfCard = Long.parseLong(object.toString()) + 1;// 当前最大流水卡片
					number = selfCard.toString();
				} else {
					number = date + "00001";
				}
				oSWork.setNumber(number);// 条码
				oSWork.setOutSourceTime(Util.getDateTime());// 外委时间.
				Users loginUser = Util.getLoginUser();
				oSWork.setCode(loginUser.getCode());
				oSWork.setDept(loginUser.getDept());
				oSWork.setUsername(loginUser.getName());
				oSWork.setProcessInforSet(proSet);

				boolean bool = totalDao.save(oSWork);
				// -------------------------------------------------------------------------
				String[] strarray = WaigouWaiweiPlanId.split(",");
				HashMap<String, Float> processNumber= new HashMap<String, Float>();
				for (int i = 0; i < strarray.length; i++) {
					Integer ids = Integer.parseInt(strarray[i].toString());
					String hql15 = " from  WaigouWaiweiPlan  where id=?";
					WaigouWaiweiPlan old = (WaigouWaiweiPlan) totalDao
							.getObjectByCondition(hql15, ids);
					if (old != null) {
						Float hasDelte=processNumber.get(old.getProcessNo());
						if(hasDelte==null){
							hasDelte=0f;
						}
						if(oSWork.getOutSourceCount()>hasDelte){
							Float hasNumber=oSWork.getOutSourceCount()-hasDelte;
							if (old.getNumber() < hasNumber) {
								hasDelte += old.getNumber();
								old.setOswId(oSWork.getId());
								old.setApplyDate(old.getApplyDate() + ","
										+ Util.getDateTime());
								old.setNumber(0f);
								old.setStatus("已领");
							} else {
								hasDelte += hasNumber;
								if(old.getNumber() .equals(hasNumber)){
									old.setStatus("已领");
								}
								old.setOswId(oSWork.getId());
								Float numsFloat = old.getNumber()
								- hasNumber;
								old.setApplyDate(old.getApplyDate() + ","
										+ Util.getDateTime());
								old.setNumber(numsFloat);
							}
							processNumber.put(old.getProcessNo(), hasDelte);
							totalDao.update(old);
						}
						}
					
				}
				Set<String> keys = processNumber.keySet();
				if(keys!=null&&keys.size()>0){
					for(String key:keys){
						Float delteCount=processNumber.get(key);
						if(delteCount<oSWork.getOutSourceCount()){
							throw new RuntimeException(key+"工序超额"+(oSWork.getOutSourceCount()-delteCount)+"件");
						}
					}
				}
				// ------------------------------------------------------------------------------

				return new Object[] { bool, "添加外委工作单成功!" };
			}
			return new Object[] { false, "请至少勾选一个工序信息!谢谢!" };
		}
		return new Object[] { false, "数据异常" };
	}

	/*
	 * public Object[] addOswGongxu(OutSourcingWorkList oSWork, Integer[]
	 * processIds,String WaigouWaiweiPlanId) { if (oSWork != null) { if
	 * (processIds != null && processIds.length > 0) { Set<ProcessInfor> proSet
	 * = new HashSet<ProcessInfor>(); for (Integer processId : processIds) {
	 * ProcessInfor process = (ProcessInfor) totalDao
	 * .getObjectById(ProcessInfor.class, processId); if (process != null) { //
	 * 判断上一道工序是否提交 ProcessInfor topPro = (ProcessInfor) totalDao
	 * .getObjectByCondition(
	 * "from ProcessInfor where procard.id=? and processNO<? order by processNO desc"
	 * , process.getProcard().getId(), process .getProcessNO()); // 已领数量 Float
	 * outCount = process.getApplyCount() + oSWork.getOutSourceCount(); if
	 * (topPro != null) { if ("外委".equals(process.getProductStyle()) &&
	 * !"外委".equals(topPro.getProductStyle())) { if (topPro.getSubmmitCount() <=
	 * 0) { return new Object[] { false, "上一道工序 " + topPro.getProcessNO() +
	 * " 尚未完成!无法外委!谢谢" }; } if (outCount > topPro.getSubmmitCount()) { return
	 * new Object[] { false, " 外委数量不能超过" + topPro.getSubmmitCount() + "!无法外委!谢谢"
	 * }; } } } process.setApplyCount(outCount); process.setStatus("已领");
	 * process.setFirstApplyDate(Util.getDateTime()); proSet.add(process); }
	 * else { return new Object[] { false, "工序信息有误，请重试或联系管理员!" }; } }
	 * 
	 * String number = ""; String date = Util.getDateTime("yyyyMM"); String hql
	 * = "select max(number) from OutSourcingWorkList"; Object object =
	 * totalDao.getObjectByCondition(hql); if (object != null) { Long selfCard =
	 * Long.parseLong(object.toString()) + 1;// 当前最大流水卡片 number =
	 * selfCard.toString(); } else { number = date + "00001"; }
	 * oSWork.setNumber(number);// 条码
	 * oSWork.setOutSourceTime(Util.getDateTime());// 外委时间. Users loginUser =
	 * Util.getLoginUser(); oSWork.setCode(loginUser.getCode());
	 * oSWork.setDept(loginUser.getDept());
	 * oSWork.setUsername(loginUser.getName());
	 * oSWork.setProcessInforSet(proSet);
	 * 
	 * boolean bool = totalDao.save(oSWork);
	 * //----------------------------------
	 * --------------------------------------- String[]
	 * strarray=WaigouWaiweiPlanId.split(","); for (int i = 0; i <
	 * strarray.length; i++) { Integer
	 * ids=Integer.parseInt(strarray[i].toString()); String hql15 =
	 * " from  WaigouWaiweiPlan  where id=?"; WaigouWaiweiPlan old =
	 * (WaigouWaiweiPlan) totalDao .getObjectByCondition(hql15,ids);
	 * //old.setNums(number); if (old!=null) { old.setOswId(oSWork.getId());
	 * totalDao.update(old); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * //------------------------------------------------------------------------
	 * ------
	 * 
	 * return new Object[] { bool, "添加外委工作单成功!" }; } return new Object[] {
	 * false, "请至少勾选一个工序信息!谢谢!" }; } return new Object[] { false, "数据异常" }; }
	 */
	/***
	 * 添加外委工作单
	 * 
	 * @param OSWork
	 * @return
	 */
	@Override
	public Object[] saveOsWork(OutSourcingWorkList oSWork, Integer[] processIds) {
		if (oSWork != null) {
			if (processIds != null && processIds.length > 0) {
				Set<ProcessInfor> proSet = new HashSet<ProcessInfor>();
				for (Integer processId : processIds) {
					ProcessInfor process = (ProcessInfor) totalDao
							.getObjectById(ProcessInfor.class, processId);
					if (process != null) {
						// 判断上一道工序是否提交
						ProcessInfor topPro = (ProcessInfor) totalDao
								.getObjectByCondition(
										"from ProcessInfor where procard.id=? and processNO<? order by processNO desc",
										process.getProcard().getId(), process
												.getProcessNO());
						// 已领数量
						Float outCount = process.getApplyCount()
								+ oSWork.getOutSourceCount();
						if (topPro != null) {
							if ("外委".equals(process.getProductStyle())
									&& !"外委".equals(topPro.getProductStyle())) {
								if (topPro.getSubmmitCount() <= 0) {
									return new Object[] {
											false,
											"上一道工序 " + topPro.getProcessNO()
													+ " 尚未完成!无法外委!谢谢" };
								}
								if (outCount > topPro.getSubmmitCount()) {
									return new Object[] {
											false,
											" 外委数量不能超过"
													+ topPro.getSubmmitCount()
													+ "!无法外委!谢谢" };
								}
							}
						}
						process.setApplyCount(outCount);
						process.setStatus("已领");
						process.setFirstApplyDate(Util.getDateTime());
						proSet.add(process);
					} else {
						return new Object[] { false, "工序信息有误，请重试或联系管理员!" };
					}
				}

				String number = "";
				String date = Util.getDateTime("yyyyMM");
				String hql = "select max(number) from OutSourcingWorkList where number like '"
						+ date + "%'";
				Object object = totalDao.getObjectByCondition(hql);
				if (object != null) {
					Long selfCard = Long.parseLong(object.toString()) + 1;// 当前最大流水卡片
					number = selfCard.toString();
				} else {
					number = date + "00001";
				}
				oSWork.setNumber(number);// 条码
				oSWork.setOutSourceTime(Util.getDateTime());// 外委时间.
				Users loginUser = Util.getLoginUser();
				oSWork.setCode(loginUser.getCode());
				oSWork.setDept(loginUser.getDept());
				oSWork.setUsername(loginUser.getName());
				oSWork.setProcessInforSet(proSet);
				boolean bool = totalDao.save(oSWork);
				return new Object[] { bool, "添加外委工作单成功!" };
			}
			return new Object[] { false, "请至少勾选一个工序信息!谢谢!" };
		}
		return new Object[] { false, "数据异常" };
	}

	@Override
	public List findJiesuanList() {
		String hql = " from OutSourcingWorkList where status=?";
		return totalDao.query(hql, "接收");
	}

	@Override
	public Object[] JIesuan(Integer[] osjisuan) {
		// TODO Auto-generated method stub
		Object[] osWorkarr = new Object[1];
		String hql = " from OutSourcingWorkList where id in(:test)";
		Query query = totalDao.createQuery(hql);
		query.setParameterList("test", osjisuan);
		List<OutSourcingWorkList> list = query.list();
		if(list.size()>0){
			for(OutSourcingWorkList o:list){
				if(o.getProcessInforSet() != null&&o.getProcessInforSet().size()>0){
					o.setProcessInforList(new ArrayList(o.getProcessInforSet()));
				}
			}
		}
		osWorkarr[0] = list;
		return osWorkarr;
	}

	@Override
	public boolean updateJIesuan(Integer[] osjisuan) {
		for (Integer id : osjisuan) {
			OutSourcingWorkList os = (OutSourcingWorkList) totalDao
					.getObjectById(OutSourcingWorkList.class, id);
			os.setStatus("结算");
			os.setJiesuanTime(Util.getDateTime());
			totalDao.update(os);
		}
		return true;
	}

	@Override
	public boolean deleteOSW(Integer id) {
		// TODO Auto-generated method stub
		OutSourcingWorkList os = (OutSourcingWorkList) totalDao.getObjectById(
				OutSourcingWorkList.class, id);
		return totalDao.delete(os);
	}

	@Override
	public void explorExcel(OutSourcingWorkList OSWork, String startDate,
			String endDate, String tag) {
		String hql = "from OutSourcingWorkList ";
		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != OSWork) {
			hql = totalDao
					.criteriaQueries(OSWork, "outSourceTime", between, "");
		}
		if ("jiesuan".equals(tag)) {
			if (hql.contains("where")) {
				hql += " and status='接收' order by outSourceTime desc ";
			} else {
				hql += " where status='接收' order by outSourceTime desc ";
			}
		} else {
			hql += " order by outSourceTime desc";
		}
		List list = totalDao.find(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("外委数据信息".getBytes("GB2312"), "8859_1")
					+ ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("外委数据信息", 0);
			ws.setColumnView(4, 20);
			ws.setColumnView(3, 10);
			ws.setColumnView(2, 20);
			ws.setColumnView(1, 12);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.BLACK);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			wcf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcf.setAlignment(jxl.format.Alignment.CENTRE);
			jxl.write.Label label0 = new Label(0, 0, "财务报销数据汇总", wcf);
			ws.addCell(label0);
			ws.mergeCells(0, 0, 21, 0);
			wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD,
					false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(jxl.format.Alignment.CENTRE);
			ws.addCell(new jxl.write.Label(0, 1, "序号", wc));
			ws.addCell(new jxl.write.Label(1, 1, "批次", wc));
			ws.addCell(new jxl.write.Label(2, 1, "件号", wc));
			ws.addCell(new jxl.write.Label(3, 1, "品名", wc));
			ws.addCell(new jxl.write.Label(4, 1, "条码", wc));
			ws.addCell(new jxl.write.Label(5, 1, "外委班组", wc));
			ws.addCell(new jxl.write.Label(6, 1, "外委数量", wc));
			ws.addCell(new jxl.write.Label(7, 1, "接收数量", wc));
			ws.addCell(new jxl.write.Label(8, 1, "损坏数量", wc));
			ws.addCell(new jxl.write.Label(9, 1, "单位", wc));
			ws.addCell(new jxl.write.Label(10, 1, "状态", wc));
			ws.addCell(new jxl.write.Label(11, 1, "外委商", wc));
			ws.addCell(new jxl.write.Label(12, 1, "合同号", wc));
			ws.addCell(new jxl.write.Label(13, 1, "外委人", wc));
			ws.addCell(new jxl.write.Label(14, 1, "接收人", wc));
			ws.addCell(new jxl.write.Label(15, 1, "外委时间", wc));
			ws.addCell(new jxl.write.Label(16, 1, "出厂时间", wc));
			ws.addCell(new jxl.write.Label(17, 1, "进厂时间", wc));
			ws.addCell(new jxl.write.Label(18, 1, "接收时间", wc));
			ws.addCell(new jxl.write.Label(19, 1, "结算时间", wc));
			ws.addCell(new jxl.write.Label(20, 1, "外委工序", wc));
			ws.addCell(new jxl.write.Label(21, 1, "外委说明", wc));
			for (int i = 0; i < list.size(); i++) {
				OutSourcingWorkList osw = (OutSourcingWorkList) list.get(i);
				String gongxu = "";
				Set<ProcessInfor> pset = osw.getProcessInforSet();
				for (ProcessInfor p : pset) {
					gongxu = gongxu + p.getProcessNO() + ";";
				}
				float receC = 0f;
				float breaC = 0f;
				if (null != osw.getReceiveCount()) {
					receC = osw.getReceiveCount();
				}
				if (null != osw.getBreakCount()) {
					breaC = osw.getBreakCount();
				}
				ws.addCell(new jxl.write.Number(0, i + 2, i + 1, wc));
				ws.addCell(new Label(1, i + 2, osw.getLotId(), wc));
				ws.addCell(new Label(2, i + 2, osw.getMarkID(), wc));
				ws.addCell(new Label(3, i + 2, osw.getPartName(), wc));
				ws.addCell(new Label(4, i + 2, osw.getNumber(), wc));
				ws.addCell(new Label(5, i + 2, osw.getDept(), wc));
				ws.addCell(new jxl.write.Number(6, i + 2, osw
						.getOutSourceCount(), wc));
				ws.addCell(new jxl.write.Number(7, i + 2, receC, wc));
				ws.addCell(new jxl.write.Number(8, i + 2, breaC, wc));
				ws.addCell(new Label(9, i + 2, osw.getUnit(), wc));
				ws.addCell(new Label(10, i + 2, osw.getStatus(), wc));
				ws.addCell(new Label(11, i + 2, osw.getOutScourceComp(), wc));
				ws.addCell(new Label(12, i + 2, osw.getContractNO(), wc));
				ws.addCell(new Label(13, i + 2, osw.getUsername(), wc));
				ws.addCell(new Label(14, i + 2, osw.getReceivePerson(), wc));
				ws.addCell(new Label(15, i + 2, osw.getOutSourceTime(), wc));
				ws.addCell(new Label(16, i + 2, osw.getLeaveOutTime(), wc));
				ws.addCell(new Label(17, i + 2, osw.getGetIntoTime(), wc));
				ws.addCell(new Label(18, i + 2, osw.getReturnTime(), wc));
				ws.addCell(new Label(19, i + 2, osw.getJiesuanTime(), wc));
				ws.addCell(new Label(20, i + 2, gongxu, wc));
				ws.addCell(new Label(21, i + 2, osw.getExplain(), wc));

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
	public void addLogisticker(OutSourcingWorkList OSWork) {
		OutSourcingWorkList osw = (OutSourcingWorkList) totalDao.getObjectById(
				OutSourcingWorkList.class, OSWork.getId());
		if (null != osw) {

			Set<ProcessInfor> p = osw.getProcessInforSet();
			for (ProcessInfor processInfor : p) {
				Procard procard = processInfor.getProcard();
				/**
				 * 生成补料单
				 */
				LogoStickers logoStickers = new LogoStickers();
				// 生成编号
				String date = Util.getDateTime("yyyyMM");
				String number = "";
				String hql = "select max(number) from LogoStickers where stickStyle='补料单' and number like 'QD-RP-"
						+ date + "%'";
				Object object = totalDao.getObjectByCondition(hql);
				if (object != null) {
					String maxNumber = object.toString();
					Long selfCard = Long.parseLong(maxNumber.substring(6,
							maxNumber.length())) + 1;// 当前最大流水卡片
					number = "QD-RP-" + selfCard.toString();
				} else {
					number = "QD-RP-" + date + "001";
				}
				logoStickers.setNumber(number);// 编号
				logoStickers.setStickStyle("补料单");
				logoStickers.setMarkId(procard.getMarkId());// 件号
				logoStickers.setLotId(procard.getSelfCard());// 批次号
				logoStickers.setProcessNO(processInfor.getProcessNO()
						.toString());
				Users loginUser = Util.getLoginUser();// 获得登录用户
				logoStickers.setOperator(loginUser.getName());
				logoStickers.setCode(loginUser.getCode());
				logoStickers.setCount(OSWork.getBreakCount());// 报废数量
				logoStickers.setPartsName(procard.getProName());// 名称
				logoStickers.setBillDate(Util.getDateTime());
				logoStickers.setOldProcardId(procard.getId());// 老流水单id
				logoStickers.setWorkingGroup(loginUser.getPassword()
						.getDeptNumber());// 部门编码
				logoStickers.setIsPrint("NO");
				logoStickers.setStatus("报废");
				totalDao.save(logoStickers);
				break;

			}
		}

	}

	public ProcardServer getProcardServer() {
		return procardServer;
	}

	public void setProcardServer(ProcardServer procardServer) {
		this.procardServer = procardServer;
	}

	@Override
	public List getProcessListByOswId(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from ProcessInfor where id in (select p.id from ProcessInfor p join  p.osWork o where o.id=?)", id);
	}

}
