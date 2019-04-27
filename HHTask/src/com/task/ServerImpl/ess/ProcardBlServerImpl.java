package com.task.ServerImpl.ess;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.caiwu.core.CorePayableServer;
import com.task.Server.ess.ProcardBlServer;
import com.task.Server.sop.ProcardServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.WareHouseAuthServiceImpl;
import com.task.ServerImpl.menjin.DoorBangDingServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Category;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.Goodstq;
import com.task.entity.Password;
import com.task.entity.Price;
import com.task.entity.PrintedOut;
import com.task.entity.PrintedOutOrder;
import com.task.entity.ProcardBl;
import com.task.entity.Sell;
import com.task.entity.Users;
import com.task.entity.WareHouse;
import com.task.entity.WareHouseAuth;
import com.task.entity.sop.ManualOrderPlan;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardProductRelation;
import com.task.entity.sop.ProcardWxTuiLiao;
import com.task.entity.sop.ProcessAndWgProcardTem;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.CircuitRun;
import com.task.util.DateUtil;
import com.task.util.MD5;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class ProcardBlServerImpl implements ProcardBlServer {
	private StringBuffer strbu = new StringBuffer();
	TotalDao totalDao;
	private ProcardServer procardServer;
	private CorePayableServer corePayableServer;

	private CircuitRunServer circuitRunServer;

	/**
	 * @return the circuitRunServer
	 */
	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	/**
	 * @param circuitRunServer
	 *            the circuitRunServer to set
	 */
	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		ProcardBlServerImpl acc = new ProcardBlServerImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	public CorePayableServer getCorePayableServer() {
		return corePayableServer;
	}

	public void setCorePayableServer(CorePayableServer corePayableServer) {
		this.corePayableServer = corePayableServer;
	}

	public ProcardServer getProcardServer() {
		return procardServer;
	}

	public void setProcardServer(ProcardServer procardServer) {
		this.procardServer = procardServer;
	}

	@Override
	public Object[] findProcardBlbyCondition(Procard procard, int cpage,
			int pageSize) {
		// TODO Auto-generated method stub
		String hql = "";
		String time = Util.getDateTime("yyyy-MM-dd");
		if (procard == null) {
			hql += "from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消','设变锁定') and  id in ( select procardId from ProcardBl where ylingliaoTime like '%"
					+ time + "%') ";
		} else {
			if (procard.getJihuoDate() != null
					&& procard.getJihuoDate().length() > 0
					&& !procard.getJihuoDate().contains("delete")
					&& !procard.getJihuoDate().contains("select")
					&& !procard.getJihuoDate().contains("update")) {
				hql += "from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消','设变锁定')  and id in ( select procardId from ProcardBl where ylingliaoTime like '%"
						+ procard.getJihuoDate() + "%') ";
			} else {
				hql += "from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消','设变锁定')  and id in ( select procardId from ProcardBl where ylingliaoTime like '%"
						+ procard.getJihuoDate() + "%') ";
			}
			if (procard.getMarkId() != null && procard.getMarkId().length() > 0
					&& !procard.getMarkId().contains("delete")
					&& !procard.getMarkId().contains("select")
					&& !procard.getMarkId().contains("update")) {
				hql += " and markId like '" + procard.getMarkId() + "'";
			}
			if (procard.getSelfCard() != null
					&& procard.getSelfCard().length() > 0
					&& !procard.getSelfCard().contains("delete")
					&& !procard.getSelfCard().contains("select")
					&& !procard.getSelfCard().contains("update")) {
				hql += " and selfCard like '" + procard.getSelfCard() + "'";
			}
		}
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };

		return null;
	}

	@Override
	public Object[] findRootProcardBlbyCondition(Procard procard, int cpage,
			int pageSize, String pagestatus) {
		// TODO Auto-generated method stub
		String hql = "";
		String lqStatus = "'未领','未领完'";
		String notstatussql = " and status not in('取消')";
		if ("findAll".equalsIgnoreCase(pagestatus)) {
			lqStatus = "'未领','未领完','已领完'";
			hql = "from Procard where (sbStatus is null or sbStatus !='删除') "
					+ notstatussql
					+ "  and id in ( select procardRootId from ProcardBl where status in("
					+ lqStatus + ")) ";
		} else if ("tuiliao".equalsIgnoreCase(pagestatus)) {
			// lqStatus = "'未领完','已领完'";
			notstatussql = "";
			hql = "from Procard where (sbStatus is null or sbStatus !='删除') "
					+ notstatussql;
		}
		if (procard == null) {
		} else {
			if (procard.getJihuoDate() != null
					&& procard.getJihuoDate().length() > 0
					&& !procard.getJihuoDate().contains("delete")
					&& !procard.getJihuoDate().contains("select")
					&& !procard.getJihuoDate().contains("update")) {
				hql = "from Procard where (sbStatus is null or sbStatus !='删除') "
						+ notstatussql
						+ "   and id in ( select procardRootId from ProcardBl where status in("
						+ lqStatus
						+ ") and ylingliaoTime like '%"
						+ procard.getJihuoDate() + "%') ";
			} else if ("tuiliao".equalsIgnoreCase(pagestatus)) {
				hql = "from Procard where (sbStatus is null or sbStatus !='删除') and procardStyle='总成'"
						+ notstatussql;
			} else {
				// Calendar cal = Calendar.getInstance();
				// cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
				// String date = Util.DateToString(cal.getTime(), "yyyy-MM-dd")
				// + " 24:00:00";
				// hql =
				// "from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消','设变锁定')  and id in ( select procardRootId from ProcardBl where status in("
				// + lqStatus + ") and ylingliaoTime <='"+date+"') ";
				hql = "from Procard where (sbStatus is null or sbStatus !='删除') "
						+ notstatussql
						+ "  and id in ( select procardRootId from ProcardBl where status in("
						+ lqStatus + ") ) ";
			}
			if (procard.getRootMarkId() != null
					&& procard.getRootMarkId().length() > 0
					&& !procard.getRootMarkId().contains("delete")
					&& !procard.getRootMarkId().contains("select")
					&& !procard.getRootMarkId().contains("update")) {
				hql += " and markId like '%" + procard.getRootMarkId() + "%'";
			}
			if (procard.getRootSelfCard() != null
					&& procard.getRootSelfCard().length() > 0
					&& !procard.getRootSelfCard().contains("delete")
					&& !procard.getRootSelfCard().contains("select")
					&& !procard.getRootSelfCard().contains("update")) {
				hql += " and selfCard like '%" + procard.getRootSelfCard()
						+ "%'";
			}
			if (procard.getYwMarkId() != null
					&& procard.getYwMarkId().length() > 0
					&& !procard.getYwMarkId().contains("delete")
					&& !procard.getYwMarkId().contains("select")
					&& !procard.getYwMarkId().contains("update")) {
				hql += " and ywMarkId like '%" + procard.getYwMarkId() + "%'";
			}
			if (procard.getOrderNumber() != null
					&& procard.getOrderNumber().length() > 0
					&& !procard.getOrderNumber().contains("delete")
					&& !procard.getOrderNumber().contains("select")
					&& !procard.getOrderNumber().contains("update")) {
				hql += " and orderNumber like '%" + procard.getOrderNumber()
						+ "%'";
			}
		}
		List list = totalDao.findAllByPage(hql + " order by id", cpage,
				pageSize);
		int count = totalDao.getCount(hql + " order by id desc");
		Object[] o = { list, count };

		return o;
	}

	@Override
	public Object[] findProcardllDetailbyRootId(Integer id, Integer blRootid) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 3);
		String date = Util.DateToString(cal.getTime(), "yyyy-MM-dd")
				+ " 24:00:00";
		List<Procard> list1 = totalDao
				.query(
						"from Procard where rootId=? and status not in ('取消') and (sbStatus is null or sbStatus !='删除') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes'))  and id in (select procardId from ProcardBl where procardRootId=? and rootId=? and ylCount<pcCount ) order by id desc",
						id, id, blRootid);
		List<Procard> list2 = totalDao
				.query(
						"from Procard where rootId=? and status not in ('取消','设变锁定') and (sbStatus is null or sbStatus !='删除') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes')) and id in (select procardId from ProcardBl where procardRootId=? and rootId=? and ylCount<pcCount and ylingliaoTime >'"
								+ date + "')", id, id, blRootid);
		List<Procard> list3 = totalDao
				.query(
						"from Procard where rootId=? and status not in ('取消','设变锁定') and (sbStatus is null or sbStatus !='删除') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes')) and id in (select procardId from ProcardBl where procardRootId=? and rootId=? and status in('已领完') )",
						id, id, blRootid);
		List<ProcardBl> bllist1 = new ArrayList<ProcardBl>();
		List<ProcardBl> bllist2 = new ArrayList<ProcardBl>();
		List<ProcardBl> bllist3 = new ArrayList<ProcardBl>();
		Procard totalProcard = byProcardId(id);
		if (list1 != null && list1.size() > 0) {
			for (Procard p : list1) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=? and rootId=?", p
								.getId(), blRootid);
				if (pbl.getYlCount() == null) {
					pbl.setYlCount(0f);
				}
				p.setHascount(pbl.getPcCount() - pbl.getYlCount());
				p.setFilnalCount(pbl.getPcCount());
				p.setKlNumber(pbl.getPcCount());
				// String
				// sql="select rootSelfCard from Procard where (hascount is null or hascount=klNumber )  and status in('初始','已发卡') and (wwblCount is null or (wwblCount<filnalCount)) and belongLayer=? and markId=? and (sbStatus is null or sbStatus !='删除')"
				// +
				// " and rootId in(select id from Procard where rootId =id and (status in('初始','已发卡','已发料','领工序')) and markId=? and selfCard<?)";
				// String beforeSelfCard = (String)
				// totalDao.getObjectByCondition(sql, p.getBelongLayer(),
				// p.getMarkId(),
				// totalProcard.getMarkId(),totalProcard.getSelfCard());
				// if(beforeSelfCard!=null&&beforeSelfCard.length()>0){
				// pbl.setBeforeSelfcard(beforeSelfCard);
				// }
				Set<Procard> pset = p.getProcardSet();
				System.out.println(p.getId());
				List<Procard> proList = new ArrayList<Procard>();
				if (pset != null) {
					for (Procard procard : pset) {
						if ("外购".equals(procard.getProcardStyle())
								&& "激活".equals(procard.getJihuoStatua())
								&& (procard.getNeedProcess() == null || !procard
										.getNeedProcess().equals("yes"))
								&& !"删除".equals(procard.getSbStatus())
								&& procard.getHascount() != null
								&& procard.getHascount() > 0) {

							ProcardBl pblwai = (ProcardBl) totalDao
									.getObjectByCondition(
											"from ProcardBl where procardId=? and rootId=?",
											procard.getId(), blRootid);
							if (pblwai != null) {
								procard.setHascount(pblwai.getPcCount()
										- pblwai.getYlCount());
								procard.setFilnalCount(pblwai.getPcCount());
								procard.setKlNumber(pblwai.getPcCount());
							}
							proList.add(procard);
						}
					}
				}
				// proList.addAll(p.getProcardSet());
				if (proList.size() > 0) {
					p.setProcardList(proList);
					// p.setFilnalCount(filnalCount)
					pbl.setProcard(p);
					bllist1.add(pbl);
				} else if (("yes").equals(p.getNeedProcess())) {
					pbl.setProcard(p);
					bllist1.add(pbl);
				}
			}
		}
		if (list2 != null && list2.size() > 0) {
			for (Procard p : list2) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=? and rootId=?", p
								.getId(), blRootid);
				pbl.setProcard(p);
				bllist2.add(pbl);
			}
		}
		if (list3 != null && list3.size() > 0) {
			for (Procard p : list3) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=? and rootId=?", p
								.getId(), blRootid);
				pbl.setProcard(p);
				bllist3.add(pbl);
			}
		}

		return new Object[] { bllist1, bllist2, bllist3, totalProcard };
	}

	@Override
	public Object[] findProcardBlDetailbyRootId(Integer id) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 3);
		String date = Util.DateToString(cal.getTime(), "yyyy-MM-dd")
				+ " 24:00:00";
		// List<Procard>list1 =
		// totalDao.query("from Procard where rootId=? and id in (select procardId from ProcardBl where procardRootId=? and status in('未领','未领完') and ylingliaoTime <='"+date+"')",
		// id,id);
		List<Procard> list1 = totalDao
				.query(
						"from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes'))   and rootId=? and id in (select procardId from ProcardBl where procardRootId=? and status in('未领','未领完'))",
						id, id);
		List<Procard> list2 = totalDao
				.query(
						"from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes'))   and rootId=? and id in (select procardId from ProcardBl where procardRootId=? and status in('未领','未领完') and ylingliaoTime >'"
								+ date + "')", id, id);
		List<Procard> list3 = totalDao
				.query(
						"from Procard where (sbStatus is null or sbStatus !='删除') and status not in('取消') and (procardStyle in ('总成','自制') or (procardStyle in ('外购') and needProcess ='yes'))   and rootId=? and id in (select procardId from ProcardBl where procardRootId=? and status in('已领完') )",
						id, id);
		List<ProcardBl> bllist1 = new ArrayList<ProcardBl>();
		List<ProcardBl> bllist2 = new ArrayList<ProcardBl>();
		List<ProcardBl> bllist3 = new ArrayList<ProcardBl>();
		if (list1 != null && list1.size() > 0) {
			for (Procard p : list1) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=?", p.getId());
				pbl.setProcard(p);
				Float wwblcount = p.getWwblCount() == null ? 0f : p
						.getWwblCount();
				Float scCount = p.getFilnalCount() - wwblcount;
				if (scCount == 0) {
					p.setThisAlertCount(0f);
				} else {
					if (p.getHascount() == null) {
						p.setThisAlertCount(scCount);
					} else {
						p.setThisAlertCount(scCount
								- (p.getFilnalCount() - p.getHascount()));
					}
				}
				bllist1.add(pbl);
			}
		}
		if (list2 != null && list2.size() > 0) {
			for (Procard p : list2) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=?", p.getId());
				pbl.setProcard(p);
				bllist2.add(pbl);
			}
		}
		if (list3 != null && list3.size() > 0) {
			for (Procard p : list3) {
				ProcardBl pbl = (ProcardBl) totalDao.getObjectByCondition(
						"from ProcardBl where procardId=?", p.getId());
				pbl.setProcard(p);
				bllist3.add(pbl);
			}
		}

		return new Object[] { bllist1, bllist2, bllist3 };
	}

	@Override
	public Map<Integer, Object> findProcardForbllingliao(int[] checkboxs,
			float[] peiqiCount, Goods pagegoods, String cardId) {
		if (checkboxs == null) {
			return null;
		}
		int[] checkboxs2 = new int[checkboxs.length];
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		WaigouOrder order = null;
		Users user = Util.getLoginUser();
		// boolean totalEnough = true;gongxuName
		Map<String, Integer> flagMap = new HashMap<String, Integer>();
		Integer maxFlag = 0;
		String gysSql = " and 1=1";
		if (user == null) {
			throw new RuntimeException("请先登录");
		}
		Users lingliaoren = (Users) totalDao
				.getObjectByCondition(
						"from Users where cardId=? and onWork not in('离职','离职中','内退','退休')",
						cardId);
		if (lingliaoren == null) {
			throw new RuntimeException("卡号有误!");
		}
		// 获取人员技能
		List<String> canProcessList = totalDao
				.query(
						"select p.processName from ProcessGzstore p join p.users u  where u.id=?",
						lingliaoren.getId());
		if (canProcessList == null || canProcessList.size() == 0) {
			throw new RuntimeException("刷卡人没有绑定任何工序!");
		}
		String hql1 = "select valueCode from CodeTranslation where type = 'sys' and keyCode='关闭余料库' and valueName='关闭余料库'";
		String valueCode = (String) totalDao.getObjectByCondition(hql1);
		Boolean useyl = true;
		if (valueCode != null && valueCode.equals("是")) {
			useyl = false;
		}
		String cqSql = "";
		List<String> gxList = new ArrayList<String>();// 关联工序列表
		boolean needNullgx = true;// 是否需要空关联工序件号

		// 集合存放要领的外购件，String 外购件件号,
		// List<Procard>
		// 每一个要领的外购件生产数据其中下标为0的procard存放的是同件号统计数据（finalCount需求总量,bili外购件的单张重量,xiaohao最小生产一件上层零件所需数量）
		// 下标大于0procard为同件号同工序号的统计数据其下的procardList为支撑此统计安好同工序号的具体零件数据
		Map<String, List<Procard>> wgjmap = new HashMap();
		Procard rootProcard = null;
		String oderNumber = null;
		if (pagegoods != null) {
			if (pagegoods.getGoodHouseName() != null
					&& pagegoods.getGoodHouseName().length() > 0) {
				String goodHouseName = pagegoods.getGoodHouseName().replaceAll(
						"，", ",");
				try {
					String[] cqStrs = goodHouseName.split(",");
					StringBuffer cqsb = new StringBuffer();
					for (String cq : cqStrs) {
						if (cq.length() > 0) {
							if (cqsb.length() == 0) {
								cqsb.append("'" + cq + "'");
							} else {
								cqsb.append(",'" + cq + "'");
							}
						}
					}
					if (cqsb.length() > 0) {
						cqSql = " and goodHouseName in (" + cqsb.toString()
								+ ")";
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("仓区格式有误!");
				}

			}
			if (pagegoods.getProcessName() != null
					&& pagegoods.getProcessName().length() > 0) {
				String processNames = pagegoods.getProcessName().replaceAll(
						"，", ",");
				try {
					String[] processnameStrs = processNames.split(",");
					StringBuffer cqsb = new StringBuffer();
					boolean haswu = false;
					for (String processname : processnameStrs) {
						if (processname.length() > 0) {
							if ("无".equals(processname)) {
								haswu = true;
							} else {
								gxList.add(processname);
							}
						}
					}
					if (!haswu) {
						needNullgx = false;
					}
					if (cqsb.length() > 0) {
						cqSql = " and goodHouseName in (" + cqsb.toString()
								+ ")";
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("工序格式有误!");
				}

			}
		}
		for (int i = 0; i < checkboxs.length; i++) {
			int id = checkboxs[i];
			float pqCount = peiqiCount[i];
			ProcardBl procardbl = (ProcardBl) totalDao.getObjectById(
					ProcardBl.class, id);
			if (procardbl == null) {
				throw new RuntimeException("没有找到第" + i + "个目标零件");
			}
			Procard procard = (Procard) totalDao.getObjectById(Procard.class,
					procardbl.getProcardId());
			if (procard == null) {
				throw new RuntimeException("没有找到第" + i + "个目标零件");
			} else {
				checkboxs2[i] = procard.getId();
				if (rootProcard == null) {
					rootProcard = (Procard) totalDao.getObjectById(
							Procard.class, procard.getRootId());
					List<String> outNumberList = totalDao
							.query(
									"select DISTINCT orderManager.orderNum from "
											+ "ProductManager where id in(select zsProductId from ProductZsAboutBh"
											+ " where bhProductId in(select productId from Product_Internal where ioDetailId=? ))",
									rootProcard.getPlanOrderDetailId());
					StringBuffer sb = new StringBuffer();
					sb.append("'" + rootProcard.getOrderNumber() + "'");
					if(rootProcard.getOrderId()!=null){
						try {
							String outOrderNumber = (String) totalDao.getObjectByCondition("select outOrderNumber from OrderManager where id=?", Integer.parseInt(rootProcard.getOrderId()));
							if(outOrderNumber!=null&&outOrderNumber.length()>0){
								sb.append(",'" + outOrderNumber + "'");
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (outNumberList != null && outNumberList.size() > 0) {
						for (String on : outNumberList) {
							sb.append(",'" + on + "'");
						}
					}
					oderNumber = sb.toString();
					// // 获取外部订单号
					// String outOrderNumber = (String) totalDao
					// .getObjectByCondition(
					// "select outOrderNumber from OrderManager where id in(select orderManager.id from ProductManager"
					// +
					// " where id in(select productId from Product_Internal where ioDetailId=?) )",
					// rootProcard.getPlanOrderDetailId());
					// if (outOrderNumber == null) {
					// outOrderNumber = "";
					// }
					map.put(1, rootProcard);
				}
			}
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())
					&& (procard.getNeedProcess() == null || procard
							.getNeedProcess().equalsIgnoreCase("no"))) {
				throw new RuntimeException("对不起,第" + i + "个目标零件为不可领料外购件");
			}
			/****
			 * 半成品领料
			 */
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())
					&& (procard.getLingliaostatus() == null || !procard
							.getLingliaostatus().equalsIgnoreCase("否"))) {
				Float count = pqCount;
				// 计算剩余未领比较数量是否足够
				if (procard.getKlNumber() == null) {// 可领数量为空不予领料
					continue;
				}

				String gongxuNum = null;
				String gongxuName = null;
				List<String> hasName = new ArrayList<String>();
				List<ProcessAndWgProcardTem> proAndWgList = totalDao
						.query(
								" from ProcessAndWgProcardTem where procardMarkId = ? and wgprocardMardkId = ? ",
								procard.getProcard().getMarkId(), procard
										.getMarkId());
				boolean selected = false;// 此外购件是否有绑定选中的工序
				if (proAndWgList != null && proAndWgList.size() > 0) {
					for (ProcessAndWgProcardTem proAndWg : proAndWgList) {
						if (!selected) {
							if (proAndWg.getProcessName() != null) {
								if (!canProcessList.contains(proAndWg
										.getProcessName())) {
									continue;
								}
							}
							if (gxList != null && gxList.size() > 0) {
								if (proAndWg.getProcessName() == null) {
									if (needNullgx) {
										selected = true;
									}
								} else if (gxList.contains(proAndWg
										.getProcessName())) {// 有包含则全选
									selected = true;
								}
							} else {// 没有设置选中工序表示全选
								selected = true;
							}
						}
						if (hasName.contains(proAndWg.getProcessName())) {
							continue;
						} else {
							hasName.add(proAndWg.getProcessName());
							if (gongxuNum == null) {
								gongxuNum = "" + proAndWg.getProcessNo();
								gongxuName = "" + proAndWg.getProcessName();
							} else {
								gongxuNum += ";" + proAndWg.getProcessNo();
								gongxuName += ";" + proAndWg.getProcessName();
							}
						}
					}
				} else {
					if (gxList != null && gxList.size() > 0) {
						if (needNullgx) {
							selected = true;
						}
					} else {
						selected = true;
					}
				}
				if (!selected) {
					continue;
				}
				procard.setGongxuName(gongxuName);
				procard.setGongxuNum(gongxuNum);
				// 外委出去数量
				Float wwCount = (Float) totalDao
						.getObjectByCondition(
								"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwCount == null) {
					wwCount = 0f;
				}
				// 外委出去剩余未被领取数量
				Float wwhascount = (Float) totalDao
						.getObjectByCondition(
								"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwhascount == null) {
					wwhascount = 0f;
				}
				if (procard.getWwblCount() == null) {
					procard.setWwblCount(0f);
				}
				procard.setWwCount(wwCount + procard.getWwblCount());
				if (procard.getWwblreceiveCount() == null) {
					procard.setWwblreceiveCount(0f);
				}
				procard.setWwhascount(wwhascount
						+ (procard.getWwblCount() - procard
								.getWwblreceiveCount()));
				if (procard.getHascount() == null) {// 包工包料
					continue;
				}

				// 生产未领数量
				Float scHascount = procard.getHascount()
						- procard.getWwhascount();
				// Float maxklNumber = Util.Floatdelete(procard.getKlNumber(),
				// procard.getHascount());
				if (scHascount < count) {
					count = scHascount;
				}
				// if (maxklNumber < count) {
				// count = maxklNumber;
				// }
				String key = procard.getMarkId() + procard.getKgliao();
				if (procard.getBanBenNumber() != null
						&& procard.getBanBenNumber().length() > 0) {
					key += procard.getBanBenNumber();
				}
				List<Procard> pList = wgjmap.get(key);
				if (pList != null && pList.size() > 0) {
					Procard tp2 = null;// 同件号同同工序统计数据
					boolean hadtp2 = false;
					Procard tp = pList.get(0);// 第一条数据为同件号统计数据
					tp.setFilnalCount(tp.getFilnalCount() + count);
					tp.setWwhascount(tp.getWwhascount()
							+ procard.getWwhascount());
					tp.setHascount(tp.getHascount() + procard.getHascount());
					tp.setKlNumber(tp.getKlNumber() + procard.getKlNumber());
					Float tjNumber = procard.getTjNumber() == null ? 0
							: procard.getTjNumber();
					tp.setTjNumber(tp.getTjNumber() + tjNumber);
					tp.setGyscodeAndNum(tp.getGyscodeAndNum() + procard.getId()
							+ "," + pqCount + ";");// 保存procardId,peiqicount;
					for (int j = 1; j < pList.size(); j++) {// 数据从第二个开始遍历
						Procard tp3 = pList.get(j);
						if (Util.isEquals(tp3.getGongxuName(), gongxuName)) {// 以工序名称做比对依据
							tp2 = tp3;
							hadtp2 = true;
						}
					}
					if (tp2 == null) {
						tp2 = new Procard();
						tp2.setMarkId(procard.getMarkId());
						tp2.setBanBenNumber(procard.getBanBenNumber());
						tp2.setFilnalCount(count);
						tp2.setWwhascount(procard.getWwhascount());
						tp2.setHascount(procard.getHascount());
						tp2.setKlNumber(procard.getKlNumber());
						tp2.setSpecification(procard.getSpecification());
						tp.setTjNumber(tjNumber);
						tp2.setGongxuNum(gongxuNum);
						tp2.setGongxuName(gongxuName);
					} else {
						tp2.setFilnalCount(tp2.getFilnalCount() + count);
						tp2.setWwhascount(tp2.getWwhascount()
								+ procard.getWwhascount());
						tp2.setHascount(tp2.getHascount()
								+ procard.getHascount());
						tp2.setKlNumber(tp2.getKlNumber()
								+ procard.getKlNumber());
						tp.setTjNumber(tp.getTjNumber() + tjNumber);
					}
					if (tp.getBili() != null) {
						Float single = null;
						String banbenSql = null;
						if (procard.getBanBenNumber() == null
								|| procard.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ procard.getBanBenNumber() + "'";
						}
						String goodsUnit = tp.getYuanUnit();
						if (goodsUnit != null
								&& procard.getUnit()
										.equalsIgnoreCase(goodsUnit)) {// 单位一致
							single = procard.getQuanzi2()
									/ procard.getQuanzi1();
						} else {
							single = tp.getBili() * procard.getQuanzi2()
									/ procard.getQuanzi1();
						}
						if (tp.getXiaohaoCount() > single) {
							tp.setXiaohaoCount(single);
						}
						if (tp2.getXiaohaoCount() == null
								|| tp2.getXiaohaoCount() > single) {
							tp2.setXiaohaoCount(single);
						}
					}
					List<Procard> sontpList = new ArrayList<Procard>();
					sontpList.add(procard);
					tp2.setProcardList(sontpList);
					if (!hadtp2) {
						pList.add(tp2);
					}
					wgjmap.put(key, pList);

				} else {
					pList = new ArrayList<Procard>();
					Procard tp = new Procard();
					tp.setSpecification(procard.getSpecification());
					tp.setUnit(procard.getUnit());
					tp.setProName(procard.getProName());
					tp.setMarkId(procard.getMarkId());
					tp.setBanBenNumber(procard.getBanBenNumber());
					tp.setFilnalCount(count);
					tp.setWwhascount(procard.getWwhascount());
					tp.setHascount(procard.getHascount());
					tp.setKlNumber(procard.getKlNumber());
					tp.setGongxuNum(gongxuNum);
					tp.setGongxuName(gongxuName);
					tp.setKgliao(procard.getKgliao());
					Float tjNumber = procard.getTjNumber() == null ? 0
							: procard.getTjNumber();
					tp.setTjNumber(tjNumber);
					tp.setGyscodeAndNum(procard.getId() + "," + pqCount + ","
							+ procard.getQuanzi1() + "," + procard.getQuanzi2()
							+ ";");// 保存procardId,peiqicount;
					// 获取比重
					Float bizhong = null;
					if (useyl) {
						bizhong = (Float) totalDao
								.getObjectByCondition(
										"select bili from YuanclAndWaigj where markId =?  and (banbenStatus is null or banbenStatus !='历史')",
										procard.getMarkId());
					}
					if (bizhong != null) {
						tp.setBili(bizhong);
						Float single = null;
						String banbenSql = null;
						if (procard.getBanBenNumber() == null
								|| procard.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ procard.getBanBenNumber() + "'";
						}
						Goods gTemp = (Goods) totalDao
								.getObjectByCondition(
										"from Goods where goodsCurQuantity>0 and  goodsZhishu>0 and (goodsUnit =? or goodsStoreZHUnit=?) and goodsMarkId=? "
												+ banbenSql, procard
												.getMarkId(),
										procard.getUnit(), procard.getUnit());
						if (gTemp != null
								&& procard.getUnit().equalsIgnoreCase(
										gTemp.getGoodsUnit())) {// 单位一致
							single = procard.getQuanzi2()
									/ procard.getQuanzi1();
							// 设置请领单位
							if (gTemp.getGoodsStoreZHUnit() != null
									&& gTemp.getGoodsStoreZHUnit().length() > 0) {
								tp.setYtuhao(gTemp.getGoodsStoreZHUnit());
							} else {
								tp.setYtuhao("张");
							}
							tp.setYuanUnit(gTemp.getGoodsUnit());
						} else {
							single = bizhong * procard.getQuanzi2()
									/ procard.getQuanzi1();
							tp.setYtuhao("张");
						}

						tp.setXiaohaoCount(single);
					}
					Procard tp2 = new Procard();
					BeanUtils.copyProperties(tp, tp2);
					List<Procard> sontpList = new ArrayList<Procard>();
					sontpList.add(procard);
					tp2.setProcardList(sontpList);
					pList.add(tp);
					pList.add(tp2);
					wgjmap.put(key, pList);
				}
			} else {
				/****
				 * 自制件领料 查出下层需领的外购件
				 */
				String hql2 = "from Procard where fatherId=? and procardStyle='外购' and hascount>0 and (sbStatus is null or sbStatus !='删除') and (lingliaostatus is null or lingliaostatus!='否') and (needProcess !='yes' or needProcess is null) and status not in ('取消','设变锁定')";
				List<Procard> list = totalDao.query(hql2, procard.getId());
				if (list != null && list.size() > 0) {
					for (Procard wgj : list) {
						// System.out.println(wgj.getMarkId());
						if (wgj.getHascount() == null) {// 包工包料
							continue;
						}
						/***** 开始----查询关联件号对应的工序名称，用于显示物料的对应工序 ****/
						String gongxuNum = null;
						String gongxuName = null;
						List<String> hasName = new ArrayList<String>();
						List<ProcessAndWgProcardTem> proAndWgList = totalDao
								.query(
										" from ProcessAndWgProcardTem where procardMarkId = ? and wgprocardMardkId = ? ",
										procard.getMarkId(), wgj.getMarkId());
						boolean selected = false;// 此外购件是否有绑定选中的工序
						if (proAndWgList != null && proAndWgList.size() > 0) {
							for (ProcessAndWgProcardTem proAndWg : proAndWgList) {
								if (!selected) {
									if (proAndWg.getProcessName() != null) {
										if (!canProcessList.contains(proAndWg
												.getProcessName())) {
											continue;
										}
									}
									if (gxList != null && gxList.size() > 0) {
										if (proAndWg.getProcessName() == null) {
											if (needNullgx) {
												selected = true;
											}
										} else if (gxList.contains(proAndWg
												.getProcessName())) {// 有包含则全选
											selected = true;
										}
									} else {// 没有设置选中工序表示全选
										selected = true;
									}
								}
								if (hasName.contains(proAndWg.getProcessName())) {
									continue;
								} else {
									hasName.add(proAndWg.getProcessName());
									if (gongxuNum == null) {
										gongxuNum = ""
												+ proAndWg.getProcessNo();
										gongxuName = ""
												+ proAndWg.getProcessName();
									} else {
										gongxuNum += ";"
												+ proAndWg.getProcessNo();
										gongxuName += ";"
												+ proAndWg.getProcessName();
									}
								}
							}
						} else {
							if (gxList != null && gxList.size() > 0) {
								if (needNullgx) {
									selected = true;
								}
							} else {
								selected = true;
							}
						}
						if (!selected) {
							continue;
						}
						wgj.setGongxuNum(gongxuNum);
						wgj.setGongxuName(gongxuName);
						/***** 结束----查询关联件号对应的工序名称，用于显示物料的对应工序 *****/

						/***** 开始----查询排产备料表信息，控制领料数量 *****/
						ProcardBl procardbl_wgj = (ProcardBl) totalDao
								.getObjectByCondition(
										"from ProcardBl where procardId=? and rootId=?",
										wgj.getId(), procardbl.getRootId());
						// 按照输入的领料数量领取,（无法明确输入的套数到底还有多少零件没有领取，因为每次都是重新计算所得）
						Float count = pqCount * wgj.getQuanzi2()
								/ wgj.getQuanzi1();

						if (procardbl_wgj != null) {
							// 按照排产配套领取(排产的配套数量领完就不能继续领取。明确齐料性)
							Float count2 = procardbl_wgj.getPcCount()
									- procardbl_wgj.getYlCount();
							if (count > count2) {
								count = count2;
							}
						}
						/***** 结束----查询排产备料表信息，控制领料数量 *****/

						// 外委出去数量
						Float wwCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										wgj.getId());
						if (wwCount == null) {
							wwCount = 0f;
						}
						// 外委出去剩余未被领取数量
						Float wwhascount = (Float) totalDao
								.getObjectByCondition(
										"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										wgj.getId());
						if (wwhascount == null) {
							wwhascount = 0f;
						}
						if (wgj.getWwblCount() == null) {
							wgj.setWwblCount(0f);
						}
						wgj.setWwCount(wwCount + wgj.getWwblCount());
						if (wgj.getWwblreceiveCount() == null) {
							wgj.setWwblreceiveCount(0f);
						}
						wgj.setWwhascount(wwhascount
								+ (wgj.getWwblCount() - wgj
										.getWwblreceiveCount()));
						// 该不该加呢？
						// if(procard.getFilnalCount() -
						// procard.getWwblCount()==0){
						// continue;
						// }

						// 生产未领数量
						Float scHascount = wgj.getHascount()
								- wgj.getWwhascount();
						// Float maxklNumber =
						// Util.Floatdelete(wgj.getTjNumber(),
						// Util.Floatdelete(wgj.getKlNumber(),
						// wgj.getHascount()));
						Float zyqjCount = 0f;
						if (scHascount < 0) {
							scHascount = 0f;
						}
						if (scHascount < count) {
							count = scHascount;
						}
						// if (maxklNumber < count) {
						// zyqjCount =count- maxklNumber ;
						// if(zyqjCount*wgj.getQuanzi1()/wgj.getQuanzi2()<=0.05){
						// zyqjCount=0f;
						// }
						// count = maxklNumber;
						// }
						/*** 开始--相同件号、供料属性、版本的外购件封装到一个Map中 ****/
						String key = wgj.getMarkId() + wgj.getKgliao();
						if (wgj.getBanBenNumber() != null
								&& wgj.getBanBenNumber().length() > 0) {
							key += wgj.getBanBenNumber();
						}
						List<Procard> pList = wgjmap.get(key);
						// 已存在相同零件
						if (pList != null && pList.size() > 0) {
							Procard tp2 = null;// 同件号同同工序统计数据
							boolean hadtp2 = false;
							Procard tp = pList.get(0);// 第一条数据为同件号统计数据
							tp.setFilnalCount(tp.getFilnalCount() + count);
							tp.setWwhascount(tp.getWwhascount()
									+ wgj.getWwhascount());
							tp
									.setHascount(tp.getHascount()
											+ wgj.getHascount());
							tp
									.setKlNumber(tp.getKlNumber()
											+ wgj.getKlNumber());
							tp.setZyqjCount(tp.getZyqjCount() + zyqjCount);
							tp.setGyscodeAndNum(tp.getGyscodeAndNum()
									+ procard.getId() + "," + pqCount + ","
									+ wgj.getQuanzi1() + "," + wgj.getQuanzi2()
									+ ";");// 保存procardId,peiqicount;
							for (int j = 1; j < pList.size(); j++) {// 数据从第二个开始遍历
								Procard tp3 = pList.get(j);
								if (Util.isEquals(tp3.getGongxuName(),
										gongxuName)) {// 以工序名称做比对依据
									tp2 = tp3;
									hadtp2 = true;
								}
							}
							if (tp2 == null) {
								tp2 = new Procard();
								tp2.setMarkId(wgj.getMarkId());
								tp2.setBanBenNumber(wgj.getBanBenNumber());
								tp2
										.setSpecification(procard
												.getSpecification());
								tp2.setFilnalCount(count);
								tp2.setWwhascount(wgj.getWwhascount());
								tp2.setHascount(wgj.getHascount());
								tp2.setKlNumber(wgj.getKlNumber());
								tp2.setGongxuNum(gongxuNum);
								tp2.setGongxuName(gongxuName);
								tp2.setZyqjCount(zyqjCount);
							} else {
								tp2
										.setFilnalCount(tp2.getFilnalCount()
												+ count);
								tp2.setWwhascount(tp2.getWwhascount()
										+ wgj.getWwhascount());
								tp2.setHascount(tp2.getHascount()
										+ wgj.getHascount());
								tp2.setKlNumber(tp2.getKlNumber()
										+ wgj.getKlNumber());
								tp2
										.setZyqjCount(tp2.getZyqjCount()
												+ zyqjCount);
							}
							if (tp.getBili() != null) {
								Float single = null;
								String banbenSql = null;
								if (wgj.getBanBenNumber() == null
										|| wgj.getBanBenNumber().length() == 0) {
									banbenSql = " and (banBenNumber is null or banBenNumber ='')";
								} else {
									banbenSql = " and banBenNumber ='"
											+ wgj.getBanBenNumber() + "'";
								}
								String goodsUnit = tp.getYuanUnit();
								if (goodsUnit != null
										&& wgj.getUnit().equalsIgnoreCase(
												goodsUnit)) {// 单位一致
									single = wgj.getQuanzi2()
											/ wgj.getQuanzi1();
								} else {
									single = tp.getBili() * wgj.getQuanzi2()
											/ wgj.getQuanzi1();
								}
								if (tp.getXiaohaoCount() > single) {
									tp.setXiaohaoCount(single);
								}
								if (tp2.getXiaohaoCount() == null
										|| tp2.getXiaohaoCount() > single) {
									tp2.setXiaohaoCount(single);
								}
							}
							List<Procard> sontpList = new ArrayList<Procard>();
							sontpList.add(wgj);
							tp2.setProcardList(sontpList);
							if (!hadtp2) {
								pList.add(tp2);
							}
							wgjmap.put(key, pList);

						} else {
							pList = new ArrayList<Procard>();
							Procard tp = new Procard();
							tp.setSpecification(procard.getSpecification());
							tp.setProName(wgj.getProName());
							tp.setMarkId(wgj.getMarkId());
							tp.setBanBenNumber(wgj.getBanBenNumber());
							tp.setFilnalCount(count);
							tp.setWwhascount(wgj.getWwhascount());
							tp.setHascount(wgj.getHascount());
							tp.setKlNumber(wgj.getKlNumber());
							tp.setGongxuNum(gongxuNum);
							tp.setGongxuName(gongxuName);
							tp.setKgliao(wgj.getKgliao());
							tp.setUnit(wgj.getUnit());
							tp.setZyqjCount(zyqjCount);
							tp.setGyscodeAndNum(procard.getId() + "," + pqCount
									+ "," + wgj.getQuanzi1() + ","
									+ wgj.getQuanzi2() + ";");// 保存procardId,peiqicount;
							// 获取比重
							Float bizhong = null;
							if (useyl) {
								bizhong = (Float) totalDao
										.getObjectByCondition(
												"select bili from YuanclAndWaigj where markId =?  and (banbenStatus is null or banbenStatus !='历史')",
												wgj.getMarkId());
							}
							if (bizhong != null) {
								tp.setBili(bizhong);
								Float single = null;
								String banbenSql = null;
								if (wgj.getBanBenNumber() == null
										|| wgj.getBanBenNumber().length() == 0) {
									banbenSql = " and (banBenNumber is null or banBenNumber ='')";
								} else {
									banbenSql = " and banBenNumber ='"
											+ wgj.getBanBenNumber() + "'";
								}
								Goods gTemp = (Goods) totalDao
										.getObjectByCondition(
												"from Goods where goodsCurQuantity>0 and  goodsZhishu>0 and goodsMarkId=? and (goodsUnit =? or goodsStoreZHUnit=?) "
														+ banbenSql, wgj
														.getMarkId(), wgj
														.getUnit(), wgj
														.getUnit());
								if (gTemp != null
										&& wgj.getUnit().equalsIgnoreCase(
												gTemp.getGoodsUnit())) {// 单位一致
									single = wgj.getQuanzi2()
											/ wgj.getQuanzi1();
									// 设置请领单位
									if (gTemp.getGoodsStoreZHUnit() != null
											&& gTemp.getGoodsStoreZHUnit()
													.length() > 0) {
										tp.setYtuhao(gTemp
												.getGoodsStoreZHUnit());
									} else {
										tp.setYtuhao("张");
									}
									tp.setYuanUnit(gTemp.getGoodsUnit());
								} else {
									single = bizhong * wgj.getQuanzi2()
											/ wgj.getQuanzi1();
									tp.setYtuhao("张");
								}

								tp.setXiaohaoCount(single);
							}
							Procard tp2 = new Procard();
							BeanUtils.copyProperties(tp, tp2);
							List<Procard> sontpList = new ArrayList<Procard>();
							sontpList.add(wgj);
							tp2.setProcardList(sontpList);
							pList.add(tp);
							pList.add(tp2);
							wgjmap.put(key, pList);
						}
					}
				}
			}
		}
		if (wgjmap.size() > 0) {
			List<Goods> returngoodsList = new ArrayList<Goods>();
			for (String wgj : wgjmap.keySet()) {
				List<Procard> plist = wgjmap.get(wgj);
				Procard p = plist.get(0);
				StringBuffer gxslsb = new StringBuffer();

				for (int i = 1; i < plist.size(); i++) {
					Procard gxp = plist.get(i);
					if (gxslsb.length() == 0) {
						gxslsb.append(gxp.getGongxuName() + ":"
								+ gxp.getFilnalCount());
					} else {
						gxslsb.append("</br>" + gxp.getGongxuName() + ":"
								+ gxp.getFilnalCount());
					}
				}
				List<Goods> goodsTempList = new ArrayList<Goods>();
				// 外购件需领数量
				float count = p.getFilnalCount();// 第一遍循环累计在这个属性中
				float tqlcount = p.getKlNumber() - p.getHascount();// ============
				// 单零件重量（一般为管材板料使用）
				Float bizhong = p.getBili();
				String goodsClass = "外购件库";
				String goodsClassSql = null;
				// if ((bizhong == null || bizhong == 0)
				// && p.getProductStyle() != null
				// && p.getProductStyle().equalsIgnoreCase("试制")) {
				// goodsClass = "试制库";// 试制的外购件去试制库取
				// goodsClassSql = " and goodsClass ='"
				// + goodsClass + "'";
				// } else {
				String kgsql = "";
				if (p.getKgliao() != null && p.getKgliao().length() > 0) {
					kgsql += " and kgliao ='" + p.getKgliao() + "'";
				}
				goodsClassSql = " and goodsClass in ('" + goodsClass + "') "
						+ kgsql;
				// }
				// 运算单位用量
				Float dwyl = (Float) totalDao
						.getObjectByCondition(
								"select sum(quanzi2/quanzi1) from Procard where rootId=? and markId=? and kgliao=? and (sbStatus is null or sbStatus!='删除')"
										+ " and (lingliaostatus is null or lingliaostatus !='否')",
								rootProcard.getId(), p.getMarkId(), p
										.getKgliao());
				Util.FomartFloat(dwyl, 4);

				if (p.getZyqjCount() != null && p.getZyqjCount() > 0) {
					String hqlGoods2 = " from Goods where  goodsMarkId='"
							+ p.getMarkId()
							+ "'"
							+ goodsClassSql
							+ " and (fcStatus is null or  fcStatus!='封存') and (goodsUnit =? or goodsStoreZHUnit=?) order by goodsLotId asc";
					List<Goods> listG2 = totalDao.query(hqlGoods2, p.getUnit(),
							p.getUnit());
					if (listG2.size() > 0) {
						Goods goot = listG2.get(0);
						Goods goo = new Goods();
						// goo
						// .setOrder_Id(procard
						// .getId());
						goo.setGongxuName(gxslsb.toString());
						goo.setGoodsFormat(p.getSpecification());
						goo.setHqlCount(tqlcount);// 缺少数量==========
						goo.setTqlCount(0f);
						goo.setZyqjCount(p.getZyqjCount());
						goo.setIsEnough(false);
						goo.setGoodsFullName(p.getProName());
						goo.setGoodsMarkId(p.getMarkId());
						goo.setQlUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						goo.setDwyl(dwyl);
						returngoodsList.add(goo);
						// totalEnough = false;
					} else {
						// totalEnough = false;
						Goods goo = new Goods();
						goo.setGongxuName(gxslsb.toString());
						goo.setGoodsFormat(p.getSpecification());
						goo.setOrder_Id(p.getId());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(0f);
						goo.setZyqjCount(p.getZyqjCount());
						goo.setIsEnough(false);
						goo.setGoodsFullName(p.getProName());
						goo.setGoodsMarkId(p.getMarkId());
						goo.setGoodsId(0);
						goo.setQlUnit(p.getUnit());
						goo.setGoodsCurQuantity(0f);
						goo.setGoodsUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						goo.setDwyl(dwyl);
						returngoodsList.add(goo);
					}
				}
				if (count == 0) {
					continue;
				}
				// 余料查询
				if (bizhong != null && bizhong > 0) {// 有比重才会有余料
					Float minsingle = p.getXiaohaoCount();// 最小单间需要重量
					List<Goods> yuliaoList = totalDao
							.query(
									" from Goods where goodsClass='余料库' and goodsCurQuantity>"
											+ minsingle
											+ cqSql
											+ " and goodsMarkId=?  and llGysId is null and kgliao=? and (yllock is null or yllock='' or yllock='no' or (yllock = 'yes' and ylMarkId=?)) and (fcStatus is null or  fcStatus!='封存') order by goodsCurQuantity ",
									p.getMarkId(), p.getKgliao(), p.getMarkId());

					for (int i = 1; i < plist.size(); i++) {
						Procard tgxprocard = plist.get(i);
						List<Procard> wgprocardList = tgxprocard
								.getProcardList();
						for (Procard wgprocard : wgprocardList) {
							Float single = null;
							if (wgprocard.getUnit().equalsIgnoreCase(
									p.getYuanUnit())) {
								single = wgprocard.getQuanzi2()
										/ wgprocard.getQuanzi1();
							} else {
								single = bizhong * wgprocard.getQuanzi2()
										/ wgprocard.getQuanzi1();
							}
							if (yuliaoList != null && yuliaoList.size() > 0) {
								for (Goods goods : yuliaoList) {
									// goods.setOrder_Id(procard
									// .getId());
									goods.setDwyl(dwyl);
									if (goods.getFlushCount() == null) {
										goods.setFlushCount(0f);
									}

									Float kyCount = Util.Floatdelete(goods
											.getGoodsCurQuantity(), goods
											.getFlushCount());
									if (kyCount < single) {
										continue;
									}
									String key = p.getMarkId() + p.getKgliao();
									if (p.getBanBenNumber() != null
											&& p.getBanBenNumber().length() > 0) {
										key += p.getBanBenNumber();
									}
									if (flagMap.get(key) != null) {
										goods.setFlag(flagMap.get(key));
									} else {
										flagMap.put(key, maxFlag);
										goods.setFlag(maxFlag);
										maxFlag++;
									}
									goods.setShowType("余料");
									goods.setGoodsFormat(p.getSpecification());
									goods.setQlCount(p.getWwhascount());
									goods.setHqlCount(tqlcount);
									goods.setTqlCount(0f);
									goods.setIsEnough(true);
									goods.setIsChangeSf(false);
									goods.setQlUnit(p.getYtuhao());
									goods.setGoodsZhishu(1f);// 余料以单张保存
									goods.setGongxuName(gxslsb.toString());
									// goods.setGoodsCurQuantity();//余料重量即领取重量
									Float singleCount = (float) Math
											.floor(kyCount / single);
									if (singleCount > 0) {
										if (p.getUnit().equalsIgnoreCase(
												p.getYuanUnit())) {// 单位一致
											if ((singleCount * single) >= count) {// 余料足够
												goods
														.setFlushCount(Util
																.Floatadd(
																		goods
																				.getFlushCount(),
																		count));// 余料需要重量
												if (!goodsTempList
														.contains(goods)
														&& !returngoodsList
																.contains(goods)) {
													goodsTempList.add(goods);
												}
												count = 0;
												break;
											} else {
												goods
														.setFlushCount(Util
																.Floatadd(
																		goods
																				.getFlushCount(),
																		singleCount
																				* single));// 余料需要重量
												// returngoodsList.add(goods);
												count = Util.Floatdelete(count,
														singleCount * single);
												;
												if (!goodsTempList
														.contains(goods)
														&& !returngoodsList
																.contains(goods)) {
													goodsTempList.add(goods);
												}
											}
										} else {
											if (singleCount * single >= count
													* bizhong) {// 余料足够
												goods.setFlushCount(goods
														.getFlushCount()
														+ count * bizhong);// 余料需要重量
												count = 0;
												if (!goodsTempList
														.contains(goods)
														&& !returngoodsList
																.contains(goods)) {
													goodsTempList.add(goods);
												}
												break;
											} else {
												goods.setFlushCount(goods
														.getFlushCount()
														+ singleCount * single);// 余料需要重量
												// returngoodsList.add(goods);
												count = count - singleCount;
												if (!goodsTempList
														.contains(goods)
														&& !returngoodsList
																.contains(goods)) {
													goodsTempList.add(goods);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				if (count == 0) {
					returngoodsList.addAll(goodsTempList);
					continue;
				}
				// 请领数量
				// float lsCount = count;// 张数
				float surlsCount = count;// 实际所需
				String banbenSql = null;
				if (p.getBanBenNumber() == null
						|| p.getBanBenNumber().length() == 0) {
					banbenSql = " and (banBenNumber is null or banBenNumber ='')";
				} else {
					banbenSql = " and banBenNumber ='" + p.getBanBenNumber()
							+ "'";
				}
				String goodsSql1 = " from Goods where  suodingdanhao in("
						+ oderNumber
						+ ") and  goodsMarkId='"
						+ p.getMarkId()
						+ "'"
						+ goodsClassSql
						+ " and  goodsCurQuantity>0 and (goodsZhishu is null or goodsZhishu=0 or goodsZhishu>=1) and (goodsUnit =? or goodsStoreZHUnit=?)  and (fcStatus is null or  fcStatus!='封存') "
						+ banbenSql + "order by goodsLotId asc";
				List<Goods> goodslist1 = totalDao.query(goodsSql1, p.getUnit(),
						p.getUnit());

				String goodsSql2 = " from Goods where (suodingdanhao is null or suodingdanhao='') and  goodsMarkId='"
						+ p.getMarkId()
						+ "'"
						+ goodsClassSql
						+ cqSql
						+ " and  goodsCurQuantity>0 and (goodsZhishu is null or goodsZhishu=0 or goodsZhishu>=1) and (goodsUnit =? or goodsStoreZHUnit=?)  and (fcStatus is null or fcStatus!='封存') "
						+ banbenSql + "order by goodsLotId asc";
				List<Goods> goodslist2 = totalDao.query(goodsSql2, p.getUnit(),
						p.getUnit());
				List<Goods> listG = new ArrayList<Goods>();
				listG.addAll(goodslist1);
				listG.addAll(goodslist2);
				// Double d2 = Math.ceil(count);
				// count =
				// Float.parseFloat(d2.toString());
				if (listG.size() > 0) {
					int n = 0;
					for (Goods goo : listG) {
						n++;
						// goo
						// .setOrder_Id(procard
						// .getId());
						String key = p.getMarkId() + p.getKgliao();
						if (p.getBanBenNumber() != null
								&& p.getBanBenNumber().length() > 0) {
							key += p.getBanBenNumber();
						}
						if (flagMap.get(key) != null) {
							goo.setFlag(flagMap.get(key));
						} else {
							flagMap.put(key, maxFlag);
							goo.setFlag(maxFlag);
							maxFlag++;
						}
						goo.setDwyl(dwyl);
						goo.setShowType("外购件");
						goo.setQlCount(p.getWwhascount());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(0f);
						goo.setGoodsBeginQuantity(0f);
						goo.setIsEnough(true);
						goo.setIsChangeSf(false);
						goo.setGongxuName(gxslsb.toString());
						goo.setInputSource(p.getGyscodeAndNum());// procardId和peiqicount
						if (bizhong != null && bizhong > 0) {// 有比重需要换算单位
							if (p.getUnit()
									.equalsIgnoreCase(goo.getGoodsUnit())
									&& null != goo.getGoodsZhishu()
									&& goo.getGoodsZhishu() > 0) {// 单位一致有支数,用lsCount计数
								// if (n == 1) {
								// lsCount = (float) Math
								// .ceil(lsCount / bizhong);
								// }
								goo.setIsChangeSf(true);
								if (goo.getGoodsCurQuantity() > count) {// 总量足够
									goo.setGoodsBeginQuantity(count);// 请领
									Float lsCount = (float) Math.ceil(count
											/ bizhong);// 换算张数
									Float count2 = lsCount * bizhong;
									if (goo.getGoodsCurQuantity() > count2) {// 可以出整数张
										goo.setGoodsCurQuantity(count2);
										if (goo.getGoodsZhishu() > lsCount) {// 张数也足以支付
											goo.setGoodsZhishu(lsCount);
										}
									}
									count = 0f;
									goodsTempList.add(goo);
									break;
								} else if (goo.getGoodsCurQuantity().equals(
										count)) {
									goo.setGoodsBeginQuantity(count);// 请领
									count = 0f;
									goodsTempList.add(goo);
									break;
								} else {
									goo.setGoodsBeginQuantity(goo
											.getGoodsCurQuantity());// 请领
									count -= goo.getGoodsCurQuantity();
									goodsTempList.add(goo);
								}
							} else if (p.getUnit().equalsIgnoreCase(
									goo.getGoodsUnit())
									&& (null == goo.getGoodsZhishu() || goo
											.getGoodsZhishu() == 0)) {// 单位一致无支数
								// 此情况不需要比重,数据有误
							} else if (null != goo.getGoodsZhishu()
									&& goo.getGoodsZhishu() > 0) {// 单位不一致,
								// 有支数用count计数
								if (bizhong == null || bizhong == 0) {// 用当前库存重新计算比重
									bizhong = goo.getGoodsCurQuantity()
											/ goo.getGoodsZhishu();
								}
								if (n == 1) {
									surlsCount = count * bizhong;
								}
								if (goo.getGoodsZhishu() >= count) {
									goo.setGoodsZhishu(count);
									// 计算重量
									goo.setGoodsCurQuantity(count * bizhong);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									// returngoodsList.add(goo);
									goodsTempList.add(goo);
									// returngoodsList.addAll(goodsTempList);
									count = 0;
									break;
								} else if (goo.getGoodsZhishu() == count) {
									goo.setGoodsZhishu(count);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									// 支数刚好情况重量，不重新计算重量
									// goo.setGoodsCurQuantity(lsCount
									// * bizhong);
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									// returngoodsList.add(goo);
									goodsTempList.add(goo);
									// returngoodsList.addAll(goodsTempList);
									count = 0;
									break;
								} else {
									goo.setGoodsCurQuantity(goo
											.getGoodsZhishu()
											* bizhong);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									returngoodsList.add(goo);
									count = count - goo.getGoodsZhishu();
									goodsTempList.add(goo);
								}

							} else {// 单位不一致无支数,无法计算

							}
						} else {// 无比重
							if (goo.getGoodsCurQuantity() >= count) {
								goo.setGoodsCurQuantity(count);
								goo.setGoodsBeginQuantity(count);
								// goo.setGoodsZhishu(count);
								count = 0;
								goo.setQlUnit(p.getUnit());
								// returngoodsList.add(goo);
								goodsTempList.add(goo);
								// returngoodsList.addAll(goodsTempList);
								break;
							} else {
								goo.setCkCount(goo.getGoodsCurQuantity());
								count -= goo.getGoodsCurQuantity();
								goo.setGoodsBeginQuantity(goo
										.getGoodsCurQuantity());
								// goo.setGoodsZhishu(goo
								// .getGoodsCurQuantity());
								goo.setQlUnit(p.getUnit());
								// returngoodsList.add(goo);
								goodsTempList.add(goo);
							}
						}

					}
					if (count > 0) {// 库存不够
						Goods gd = new Goods();
						// gd.setOrder_Id(procard.getId());
						gd.setDwyl(dwyl);
						gd.setGoodsFormat(p.getSpecification());
						gd.setGongxuName(gxslsb.toString());
						gd.setGoodsFullName(p.getProName());
						gd.setGoodsMarkId(p.getMarkId());
						gd.setGoodsUnit(p.getUnit());
						gd.setGoodsCurQuantity(0f);
						gd.setGoodsBeginQuantity(count);
						gd.setHqlCount(tqlcount);
						gd.setTqlCount(count);
						gd.setIsEnough(false);
						gd.setKgliao(p.getKgliao());
						// returngoodsList.add(gd);
						// for (Goods gsTemp : goodsTempList) {
						// gsTemp.setIsEnough(false);
						// }
						goodsTempList.add(gd);
						// totalEnough = false;
					}
					returngoodsList.addAll(goodsTempList);
				} else {
					String hqlGoods2 = " from Goods where (suodingdanhao is null or suodingdanhao=?) and  goodsMarkId='"
							+ p.getMarkId()
							+ "'"
							+ goodsClassSql
							+ cqSql
							+ " and goodsCurQuantity=0 and (goodsUnit =? or goodsStoreZHUnit=?) and (fcStatus is null or  fcStatus!='封存') "
							+ banbenSql + " order by goodsLotId asc";
					List<Goods> listG2 = totalDao.query(hqlGoods2, rootProcard
							.getOrderNumber(), p.getUnit(), p.getUnit());
					if (listG2.size() > 0) {
						Goods goo = listG2.get(0);
						// goo
						// .setOrder_Id(procard
						// .getId());
						goo.setDwyl(dwyl);
						goo.setGongxuName(gxslsb.toString());
						goo.setGoodsFullName(p.getProName());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(count);
						goo.setGoodsBeginQuantity(count);
						goo.setIsEnough(false);
						goo.setQlUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						goo.setBanBenNumber(p.getBanBenNumber());
						returngoodsList.add(goo);
						// totalEnough = false;
					} else {
						// totalEnough = false;
						Goods goo = new Goods();
						goo.setDwyl(dwyl);
						goo.setGoodsFormat(p.getSpecification());
						goo.setGongxuName(gxslsb.toString());
						goo.setOrder_Id(p.getId());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(count);
						goo.setGoodsBeginQuantity(count);
						goo.setIsEnough(false);
						goo.setGoodsFullName(p.getProName());
						goo.setGoodsMarkId(p.getMarkId());
						goo.setGoodsId(0);
						goo.setQlUnit(p.getUnit());
						goo.setGoodsCurQuantity(0f);
						goo.setGoodsUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						goo.setBanBenNumber(p.getBanBenNumber());
						returngoodsList.add(goo);
					}
				}
			}
			map.put(2, returngoodsList);
		}
		map.put(3, checkboxs2);
		return map;
	}

	// ===========================领料计算结束
	@Override
	public Map<Integer, Object> findProcardForbeiliao(int[] checkboxs,
			float[] peiqiCount) {
		// TODO Auto-generated method stub
		int[] checkboxs2 = new int[checkboxs.length];
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		WaigouOrder order = null;
		Users user = Util.getLoginUser();
		boolean totalEnough = true;
		Map<String, Integer> flagMap = new HashMap<String, Integer>();
		Integer maxFlag = 0;
		String gysSql = " and 1=1";
		if (user == null) {
			throw new RuntimeException("请先登录");
		}
		String hql1 = "select valueCode from CodeTranslation where type = 'sys' and keyCode='关闭余料库' and valueName='关闭余料库'";
		String valueCode = (String) totalDao.getObjectByCondition(hql1);
		Boolean useyl = true;
		if (valueCode != null && valueCode.equals("是")) {
			useyl = false;
		}
		// 集合存放要领的外购件，String 外购件件号,
		// List<Procard>
		// 每一个要领的外购件生产数据其中下标为0的procard存放的是同件号统计数据（finalCount需求总量,bili外购件的单张重量,xiaohao最小生产一件上层零件所需数量）
		// 下标大于0procard为同件号同工序号的统计数据其下的procardList为支撑此统计安好同工序号的具体零件数据
		Map<String, List<Procard>> wgjmap = new HashMap();
		Procard rootProcard = null;
		String oderNumber = null;
		for (int i = 0; i < checkboxs.length; i++) {
			int id = checkboxs[i];
			float pqCount = peiqiCount[i];
			ProcardBl procardbl = (ProcardBl) totalDao.getObjectById(
					ProcardBl.class, id);
			if (procardbl == null) {
				throw new RuntimeException("没有找到第" + i + "个目标零件");
			}
			Procard procard = (Procard) totalDao.getObjectById(Procard.class,
					procardbl.getProcardId());
			if (procard == null) {
				throw new RuntimeException("没有找到第" + i + "个目标零件");
			} else {
				checkboxs2[i] = procard.getId();
				if (rootProcard == null) {
					rootProcard = (Procard) totalDao.getObjectById(
							Procard.class, procard.getRootId());
					List<String> outNumberList = totalDao
							.query(
									"select DISTINCT orderManager.orderNum from "
											+ "ProductManager where id in(select zsProductId from ProductZsAboutBh"
											+ " where bhProductId in(select productId from Product_Internal where ioDetailId=? ))",
									rootProcard.getPlanOrderDetailId());
					StringBuffer sb = new StringBuffer();
					sb.append("'" + rootProcard.getOrderNumber() + "'");
					if(rootProcard.getOrderId()!=null){
						try {
							String outOrderNumber = (String) totalDao.getObjectByCondition("select outOrderNumber from OrderManager where id=?",Integer.parseInt(rootProcard.getOrderId()) );
							if(outOrderNumber!=null&&outOrderNumber.length()>0){
								sb.append(",'" + outOrderNumber + "'");
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (outNumberList != null && outNumberList.size() > 0) {
						for (String on : outNumberList) {
							sb.append(",'" + on + "'");
						}
					}
					oderNumber = sb.toString();
					map.put(1, rootProcard);
				}
			}
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())
					&& (procard.getNeedProcess() == null || procard
							.getNeedProcess().equalsIgnoreCase("no"))) {
				throw new RuntimeException("对不起,第" + i + "个目标零件为不可领料外购件");
			}
			// 查出下层外购件需领
			String hql2 = "from Procard where fatherId=? and procardStyle='外购' and hascount>0 and (sbStatus is null or sbStatus !='删除') and (lingliaostatus is null or lingliaostatus!='否') and (needProcess !='yes' or needProcess is null)";
			String needprocesswgj = "from Procard where id=? and procardStyle='外购' and (sbStatus is null or sbStatus !='删除') and needProcess ='yes' and (lingliaostatus is null or lingliaostatus!='否')";
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())
					&& (procard.getLingliaostatus() == null || !procard
							.getLingliaostatus().equalsIgnoreCase("否"))) {
				Float count = pqCount;
				// 计算剩余未领比较数量是否足够
				if (procard.getKlNumber() == null) {// 可领数量为空不予领料
					continue;
				}
				String gongxuNum = null;
				String gongxuName = null;
				List<String> hasName = new ArrayList<String>();
				List<ProcessAndWgProcardTem> proAndWgList = totalDao
						.query(
								" from ProcessAndWgProcardTem where procardMarkId = ? and wgprocardMardkId = ? ",
								procard.getMarkId(), procard.getMarkId());
				if (proAndWgList != null && proAndWgList.size() > 0) {
					for (ProcessAndWgProcardTem proAndWg : proAndWgList) {
						if (hasName.contains(proAndWg.getProcessName())) {
							continue;
						} else {
							hasName.add(proAndWg.getProcessName());
							if (gongxuNum == null) {
								gongxuNum = "" + proAndWg.getProcessNo();
								gongxuName = "" + proAndWg.getProcessName();
							} else {
								gongxuNum += ";" + proAndWg.getProcessNo();
								gongxuName += ";" + proAndWg.getProcessName();
							}
						}
					}
				}
				procard.setGongxuName(gongxuName);
				procard.setGongxuNum(gongxuNum);
				// 外委出去数量
				Float wwCount = (Float) totalDao
						.getObjectByCondition(
								"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwCount == null) {
					wwCount = 0f;
				}
				// 外委出去剩余未被领取数量
				Float wwhascount = (Float) totalDao
						.getObjectByCondition(
								"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwhascount == null) {
					wwhascount = 0f;
				}
				if (procard.getWwblCount() == null) {
					procard.setWwblCount(0f);
				}
				procard.setWwCount(wwCount + procard.getWwblCount());
				if (procard.getWwblreceiveCount() == null) {
					procard.setWwblreceiveCount(0f);
				}
				procard.setWwhascount(wwhascount
						+ (procard.getWwblCount() - procard
								.getWwblreceiveCount()));
				if (procard.getHascount() == null) {// 包工包料
					continue;
				}
				// 生产未领数量
				Float scHascount = procard.getHascount()
						- procard.getWwhascount();
				// Float maxklNumber = Util.Floatdelete(procard.getKlNumber(),
				// procard.getHascount());
				if (scHascount < count) {
					count = scHascount;
				}
				// if (maxklNumber < count) {
				// count = maxklNumber;
				// }
				String key = procard.getMarkId() + procard.getKgliao();
				if (procard.getBanBenNumber() != null
						&& procard.getBanBenNumber().length() > 0) {
					key += procard.getBanBenNumber();
				}
				List<Procard> pList = wgjmap.get(key);
				if (pList != null && pList.size() > 0) {
					Procard tp2 = null;// 同件号同同工序统计数据
					boolean hadtp2 = false;
					Procard tp = pList.get(0);// 第一条数据为同件号统计数据
					tp.setFilnalCount(tp.getFilnalCount() + count);
					tp.setWwhascount(tp.getWwhascount()
							+ procard.getWwhascount());
					tp.setHascount(tp.getHascount() + procard.getHascount());
					tp.setKlNumber(tp.getKlNumber() + procard.getKlNumber());
					Float tjNumber = procard.getTjNumber() == null ? 0
							: procard.getTjNumber();
					tp.setTjNumber(tp.getTjNumber() + tjNumber);
					for (int j = 1; j < pList.size(); j++) {// 数据从第二个开始遍历
						Procard tp3 = pList.get(j);
						if (Util.isEquals(tp3.getGongxuName(), gongxuName)) {// 以工序名称做比对依据
							tp2 = tp3;
							hadtp2 = true;
						}
					}
					if (tp2 == null) {
						tp2 = new Procard();
						tp2.setMarkId(procard.getMarkId());
						tp2.setBanBenNumber(procard.getBanBenNumber());
						tp2.setFilnalCount(count);
						tp2.setWwhascount(procard.getWwhascount());
						tp2.setHascount(procard.getHascount());
						tp2.setKlNumber(procard.getKlNumber());
						tp.setTjNumber(tjNumber);
						tp2.setGongxuNum(gongxuNum);
						tp2.setGongxuName(gongxuName);
					} else {
						tp2.setFilnalCount(tp2.getFilnalCount() + count);
						tp2.setWwhascount(tp2.getWwhascount()
								+ procard.getWwhascount());
						tp2.setHascount(tp2.getHascount()
								+ procard.getHascount());
						tp2.setKlNumber(tp2.getKlNumber()
								+ procard.getKlNumber());
						tp.setTjNumber(tp.getTjNumber() + tjNumber);
					}
					if (tp.getBili() != null) {
						Float single = null;
						String banbenSql = null;
						if (procard.getBanBenNumber() == null
								|| procard.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ procard.getBanBenNumber() + "'";
						}
						String goodsUnit = tp.getYuanUnit();
						if (goodsUnit != null
								&& procard.getUnit()
										.equalsIgnoreCase(goodsUnit)) {// 单位一致
							single = procard.getQuanzi2()
									/ procard.getQuanzi1();
						} else {
							single = tp.getBili() * procard.getQuanzi2()
									/ procard.getQuanzi1();
						}
						if (tp.getXiaohaoCount() > single) {
							tp.setXiaohaoCount(single);
						}
						if (tp2.getXiaohaoCount() == null
								|| tp2.getXiaohaoCount() > single) {
							tp2.setXiaohaoCount(single);
						}
					}
					List<Procard> sontpList = new ArrayList<Procard>();
					sontpList.add(procard);
					tp2.setProcardList(sontpList);
					if (!hadtp2) {
						pList.add(tp2);
					}
					wgjmap.put(key, pList);

				} else {
					pList = new ArrayList<Procard>();
					Procard tp = new Procard();
					tp.setProName(procard.getProName());
					tp.setMarkId(procard.getMarkId());
					tp.setBanBenNumber(procard.getBanBenNumber());
					tp.setFilnalCount(count);
					tp.setWwhascount(procard.getWwhascount());
					tp.setHascount(procard.getHascount());
					tp.setKlNumber(procard.getKlNumber());
					tp.setGongxuNum(gongxuNum);
					tp.setGongxuName(gongxuName);
					tp.setKgliao(procard.getKgliao());
					Float tjNumber = procard.getTjNumber() == null ? 0
							: procard.getTjNumber();
					tp.setTjNumber(tjNumber);
					// 获取比重
					Float bizhong = null;
					if (useyl) {
						bizhong = (Float) totalDao
								.getObjectByCondition(
										"select bili from YuanclAndWaigj where markId =?  and (banbenStatus is null or banbenStatus !='历史')",
										procard.getMarkId());
					}
					if (bizhong != null) {
						tp.setBili(bizhong);
						Float single = null;
						String banbenSql = null;
						if (procard.getBanBenNumber() == null
								|| procard.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ procard.getBanBenNumber() + "'";
						}
						Goods gTemp = (Goods) totalDao
								.getObjectByCondition(
										"from Goods where goodsCurQuantity>0 and  goodsZhishu>0 and (goodsUnit =? or goodsStoreZHUnit=?) and goodsMarkId=? "
												+ banbenSql, procard
												.getMarkId(),
										procard.getUnit(), procard.getUnit());
						if (gTemp != null
								&& procard.getUnit().equalsIgnoreCase(
										gTemp.getGoodsUnit())) {// 单位一致
							single = procard.getQuanzi2()
									/ procard.getQuanzi1();
							// 设置请领单位
							if (gTemp.getGoodsStoreZHUnit() != null
									&& gTemp.getGoodsStoreZHUnit().length() > 0) {
								tp.setYtuhao(gTemp.getGoodsStoreZHUnit());
							} else {
								tp.setYtuhao("张");
							}
							tp.setYuanUnit(gTemp.getGoodsUnit());
						} else {
							single = bizhong * procard.getQuanzi2()
									/ procard.getQuanzi1();
							tp.setYtuhao("张");
						}

						tp.setXiaohaoCount(single);
					}
					Procard tp2 = new Procard();
					BeanUtils.copyProperties(tp, tp2);
					List<Procard> sontpList = new ArrayList<Procard>();
					sontpList.add(procard);
					tp2.setProcardList(sontpList);
					pList.add(tp);
					pList.add(tp2);
					wgjmap.put(key, pList);
				}
			} else {
				List<Procard> list = totalDao.query(hql2, procard.getId());
				if (list != null && list.size() > 0) {
					for (Procard wgj : list) {
						if (wgj.getHascount() == null) {// 包工包料
							continue;
						}
						String gongxuNum = null;
						String gongxuName = null;
						List<String> hasName = new ArrayList<String>();
						List<ProcessAndWgProcardTem> proAndWgList = totalDao
								.query(
										" from ProcessAndWgProcardTem where procardMarkId = ? and wgprocardMardkId = ? ",
										procard.getMarkId(), wgj.getMarkId());
						if (proAndWgList != null && proAndWgList.size() > 0) {
							for (ProcessAndWgProcardTem proAndWg : proAndWgList) {
								if (hasName.contains(proAndWg.getProcessName())) {
									continue;
								} else {
									hasName.add(proAndWg.getProcessName());
									if (gongxuNum == null) {
										gongxuNum = ""
												+ proAndWg.getProcessNo();
										gongxuName = ""
												+ proAndWg.getProcessName();
									} else {
										gongxuNum += ";"
												+ proAndWg.getProcessNo();
										gongxuName += ";"
												+ proAndWg.getProcessName();
									}
								}
							}
						}
						wgj.setGongxuNum(gongxuNum);
						wgj.setGongxuName(gongxuName);

						Float count = pqCount * wgj.getQuanzi2()
								/ wgj.getQuanzi1();
						// 外委出去数量
						Float wwCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										wgj.getId());
						if (wwCount == null) {
							wwCount = 0f;
						}
						// 外委出去剩余未被领取数量
						Float wwhascount = (Float) totalDao
								.getObjectByCondition(
										"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										wgj.getId());
						if (wwhascount == null) {
							wwhascount = 0f;
						}
						if (wgj.getWwblCount() == null) {
							wgj.setWwblCount(0f);
						}
						wgj.setWwCount(wwCount + wgj.getWwblCount());
						if (wgj.getWwblreceiveCount() == null) {
							wgj.setWwblreceiveCount(0f);
						}
						wgj.setWwhascount(wwhascount
								+ (wgj.getWwblCount() - wgj
										.getWwblreceiveCount()));
						// 生产未领数量
						Float scHascount = wgj.getHascount()
								- wgj.getWwhascount();
						Float zyqjCount = 0f;
						// Float maxklNumber =
						// Util.Floatdelete(wgj.getTjNumber(),
						// Util.Floatdelete(wgj.getKlNumber(),
						// wgj.getHascount()));//计算占用缺件
						// if (scHascount < count) {
						// count = scHascount;
						// }
						// if (maxklNumber < count) {
						// zyqjCount =count- maxklNumber ;
						// if(zyqjCount*wgj.getQuanzi1()/wgj.getQuanzi2()<=0.05){
						// zyqjCount=0f;
						// }
						// count = maxklNumber;
						// }
						String key = wgj.getMarkId() + wgj.getKgliao();
						if (wgj.getBanBenNumber() != null
								&& wgj.getBanBenNumber().length() > 0) {
							key += wgj.getBanBenNumber();
						}
						List<Procard> pList = wgjmap.get(key);
						if (pList != null && pList.size() > 0) {
							Procard tp2 = null;// 同件号同同工序统计数据
							boolean hadtp2 = false;
							Procard tp = pList.get(0);// 第一条数据为同件号统计数据
							tp.setFilnalCount(tp.getFilnalCount() + count);
							tp.setWwhascount(tp.getWwhascount()
									+ wgj.getWwhascount());
							tp
									.setHascount(tp.getHascount()
											+ wgj.getHascount());
							tp
									.setKlNumber(tp.getKlNumber()
											+ wgj.getKlNumber());
							tp.setZyqjCount(tp.getZyqjCount() + zyqjCount);
							for (int j = 1; j < pList.size(); j++) {// 数据从第二个开始遍历
								Procard tp3 = pList.get(j);
								if (Util.isEquals(tp3.getGongxuName(),
										gongxuName)) {// 以工序名称做比对依据
									tp2 = tp3;
									hadtp2 = true;
								}
							}
							if (tp2 == null) {
								tp2 = new Procard();
								tp2.setMarkId(wgj.getMarkId());
								tp2.setBanBenNumber(wgj.getBanBenNumber());
								tp2.setFilnalCount(count);
								tp2.setWwhascount(wgj.getWwhascount());
								tp2.setHascount(wgj.getHascount());
								tp2.setKlNumber(wgj.getKlNumber());
								tp2.setGongxuNum(gongxuNum);
								tp2.setGongxuName(gongxuName);
								tp2.setZyqjCount(zyqjCount);
							} else {
								tp2
										.setFilnalCount(tp2.getFilnalCount()
												+ count);
								tp2.setWwhascount(tp2.getWwhascount()
										+ wgj.getWwhascount());
								tp2.setHascount(tp2.getHascount()
										+ wgj.getHascount());
								tp2.setKlNumber(tp2.getKlNumber()
										+ wgj.getKlNumber());
								tp2
										.setZyqjCount(tp2.getZyqjCount()
												+ zyqjCount);
							}
							if (tp.getBili() != null) {
								Float single = null;
								String banbenSql = null;
								if (wgj.getBanBenNumber() == null
										|| wgj.getBanBenNumber().length() == 0) {
									banbenSql = " and (banBenNumber is null or banBenNumber ='')";
								} else {
									banbenSql = " and banBenNumber ='"
											+ wgj.getBanBenNumber() + "'";
								}
								String goodsUnit = tp.getYuanUnit();
								if (goodsUnit != null
										&& wgj.getUnit().equalsIgnoreCase(
												goodsUnit)) {// 单位一致
									single = wgj.getQuanzi2()
											/ wgj.getQuanzi1();
								} else {
									single = tp.getBili() * wgj.getQuanzi2()
											/ wgj.getQuanzi1();
								}
								if (tp.getXiaohaoCount() > single) {
									tp.setXiaohaoCount(single);
								}
								if (tp2.getXiaohaoCount() == null
										|| tp2.getXiaohaoCount() > single) {
									tp2.setXiaohaoCount(single);
								}
							}
							List<Procard> sontpList = new ArrayList<Procard>();
							sontpList.add(wgj);
							tp2.setProcardList(sontpList);
							if (!hadtp2) {
								pList.add(tp2);
							}
							wgjmap.put(key, pList);

						} else {
							pList = new ArrayList<Procard>();
							Procard tp = new Procard();
							tp.setProName(wgj.getProName());
							tp.setMarkId(wgj.getMarkId());
							tp.setBanBenNumber(wgj.getBanBenNumber());
							tp.setFilnalCount(count);
							tp.setWwhascount(wgj.getWwhascount());
							tp.setHascount(wgj.getHascount());
							tp.setKlNumber(wgj.getKlNumber());
							tp.setGongxuNum(gongxuNum);
							tp.setGongxuName(gongxuName);
							tp.setKgliao(wgj.getKgliao());
							tp.setUnit(wgj.getUnit());
							tp.setZyqjCount(zyqjCount);
							// 获取比重
							Float bizhong = null;
							if (useyl) {
								bizhong = (Float) totalDao
										.getObjectByCondition(
												"select bili from YuanclAndWaigj where markId =?  and (banbenStatus is null or banbenStatus !='历史')",
												wgj.getMarkId());
							}
							if (bizhong != null) {
								tp.setBili(bizhong);
								Float single = null;
								String banbenSql = null;
								if (wgj.getBanBenNumber() == null
										|| wgj.getBanBenNumber().length() == 0) {
									banbenSql = " and (banBenNumber is null or banBenNumber ='')";
								} else {
									banbenSql = " and banBenNumber ='"
											+ wgj.getBanBenNumber() + "'";
								}
								Goods gTemp = (Goods) totalDao
										.getObjectByCondition(
												"from Goods where goodsCurQuantity>0 and  goodsZhishu>0 and goodsMarkId=? and (goodsUnit =? or goodsStoreZHUnit=?) "
														+ banbenSql, wgj
														.getMarkId(), wgj
														.getUnit(), wgj
														.getUnit());
								if (gTemp != null
										&& wgj.getUnit().equalsIgnoreCase(
												gTemp.getGoodsUnit())) {// 单位一致
									single = wgj.getQuanzi2()
											/ wgj.getQuanzi1();
									// 设置请领单位
									if (gTemp.getGoodsStoreZHUnit() != null
											&& gTemp.getGoodsStoreZHUnit()
													.length() > 0) {
										tp.setYtuhao(gTemp
												.getGoodsStoreZHUnit());
									} else {
										tp.setYtuhao("张");
									}
									tp.setYuanUnit(gTemp.getGoodsUnit());
								} else {
									single = bizhong * wgj.getQuanzi2()
											/ wgj.getQuanzi1();
									tp.setYtuhao("张");
								}

								tp.setXiaohaoCount(single);
							}
							Procard tp2 = new Procard();
							BeanUtils.copyProperties(tp, tp2);
							List<Procard> sontpList = new ArrayList<Procard>();
							sontpList.add(wgj);
							tp2.setProcardList(sontpList);
							pList.add(tp);
							pList.add(tp2);
							wgjmap.put(key, pList);
						}
					}
				}
			}
		}
		if (wgjmap.size() > 0) {
			List<Goods> returngoodsList = new ArrayList<Goods>();
			for (String wgj : wgjmap.keySet()) {
				List<Procard> plist = wgjmap.get(wgj);
				Procard p = plist.get(0);
				StringBuffer gxslsb = new StringBuffer();
				for (int i = 1; i < plist.size(); i++) {
					Procard gxp = plist.get(i);
					if (gxslsb.length() == 0) {
						gxslsb.append(gxp.getGongxuName() + ":"
								+ gxp.getFilnalCount());
					} else {
						gxslsb.append("</br>" + gxp.getGongxuName() + ":"
								+ gxp.getFilnalCount());
					}
				}
				List<Goods> goodsTempList = new ArrayList<Goods>();
				// 外购件需领数量
				float count = p.getFilnalCount();// 第一遍循环累计在这个属性中
				float tqlcount = p.getKlNumber() - p.getHascount();
				// 单零件重量（一般为管材板料使用）
				Float bizhong = p.getBili();
				String goodsClass = "外购件库','中间库";
				String goodsClassSql = null;
				// if ((bizhong == null || bizhong == 0)
				// && p.getProductStyle() != null
				// && p.getProductStyle().equals("试制")) {
				// goodsClass = "试制库";// 试制的外购件去试制库取
				// goodsClassSql = " and goodsClass ='"
				// + goodsClass + "'";
				// } else {
				String kgsql = " and 1=1";
				if (p.getKgliao() != null && p.getKgliao().length() > 0) {
					kgsql += " and kgliao ='" + p.getKgliao() + "'";
				}
				goodsClassSql = " and ((goodsClass in ('" + goodsClass + "') "
						+ kgsql + " ) or goodsClass = '备货库')";
				// }
				// 运算单位用量
				Float dwyl = (Float) totalDao
						.getObjectByCondition(
								"select sum(quanzi2/quanzi1) from Procard where rootId=? and markId=? and kgliao=? and (sbStatus is null or sbStatus!='删除')"
										+ " and (lingliaostatus is null or lingliaostatus !='否')",
								rootProcard.getId(), p.getMarkId(), p
										.getKgliao());
				Util.FomartFloat(dwyl, 4);
				if (p.getZyqjCount() != null && p.getZyqjCount() > 0) {
					String hqlGoods2 = " from Goods where goodsMarkId='"
							+ p.getMarkId()
							+ "'"
							+ goodsClassSql
							+ " and (fcStatus is null or  fcStatus!='封存') and (goodsUnit =? or goodsStoreZHUnit=?) order by goodsLotId asc";
					List<Goods> listG2 = totalDao.query(hqlGoods2, p.getUnit(),
							p.getUnit());
					if (listG2.size() > 0) {
						Goods goo = listG2.get(0);
						// goo
						// .setOrder_Id(procard
						// .getId());
						goo.setDwyl(dwyl);
						goo.setGongxuName(gxslsb.toString());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(0f);
						goo.setZyqjCount(p.getZyqjCount());
						goo.setIsEnough(false);
						goo.setQlUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						returngoodsList.add(goo);
						totalEnough = false;
					} else {
						totalEnough = false;
						Goods goo = new Goods();
						goo.setDwyl(dwyl);
						goo.setGongxuName(gxslsb.toString());
						goo.setOrder_Id(p.getId());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(0f);
						goo.setZyqjCount(p.getZyqjCount());
						goo.setIsEnough(false);
						goo.setGoodsFullName(p.getProName());
						goo.setGoodsMarkId(p.getMarkId());
						goo.setGoodsId(0);
						goo.setQlUnit(p.getUnit());
						goo.setGoodsCurQuantity(0f);
						goo.setGoodsUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						returngoodsList.add(goo);
					}
				}
				if (count == 0) {
					continue;
				}
				// 余料查询
				if (bizhong != null && bizhong > 0) {// 有比重才会有余料
					Float minsingle = p.getXiaohaoCount();// 最小单间需要重量
					List<Goods> yuliaoList = totalDao
							.query(
									" from Goods where goodsClass='余料库' and goodsCurQuantity>"
											+ minsingle
											+ " and goodsMarkId=? and llGysId is null and kgliao=? and (yllock is null or yllock='' or yllock='no' or (yllock = 'yes' and ylMarkId=?)) and (fcStatus is null or  fcStatus!='封存') order by goodsCurQuantity ",
									p.getMarkId(), p.getKgliao(), p.getMarkId());
					for (int i = 1; i < plist.size(); i++) {
						Procard tgxprocard = plist.get(i);
						List<Procard> wgprocardList = tgxprocard
								.getProcardList();
						for (Procard wgprocard : wgprocardList) {
							Float single = null;
							if (wgprocard.getUnit().equalsIgnoreCase(
									p.getYuanUnit())) {
								single = wgprocard.getQuanzi2()
										/ wgprocard.getQuanzi1();
							} else {
								single = bizhong * wgprocard.getQuanzi2()
										/ wgprocard.getQuanzi1();
							}
							if (yuliaoList != null && yuliaoList.size() > 0) {
								for (Goods goods : yuliaoList) {
									// goods.setOrder_Id(procard
									// .getId());
									goods.setDwyl(dwyl);
									if (goods.getFlushCount() == null) {
										goods.setFlushCount(0f);
									}
									Float kyCount = goods.getGoodsCurQuantity()
											- goods.getFlushCount();
									if (kyCount < single) {
										continue;
									}
									if (flagMap.get(p.getMarkId()) != null) {
										goods.setFlag(flagMap
												.get(p.getMarkId()));
									} else {
										flagMap.put(p.getMarkId(), maxFlag);
										goods.setFlag(maxFlag);
										maxFlag++;
									}
									goods.setShowType("余料");
									goods.setQlCount(p.getWwhascount());
									goods.setHqlCount(tqlcount);
									goods.setTqlCount(0f);
									goods.setIsEnough(true);
									goods.setIsChangeSf(false);
									goods.setQlUnit(p.getYtuhao());
									goods.setGoodsZhishu(1f);// 余料以单张保存
									goods.setGongxuName(gxslsb.toString());
									// goods.setGoodsCurQuantity();//余料重量即领取重量
									Float singleCount = (float) Math
											.floor(kyCount / single);
									if (singleCount > 0) {
										if (p.getUnit().equalsIgnoreCase(
												p.getYuanUnit())) {// 单位一致
											if ((singleCount * single) >= count) {// 余料足够
												goods.setFlushCount(goods
														.getFlushCount()
														+ count);// 余料需要重量
												if (!goodsTempList
														.contains(goods)) {
													goodsTempList.add(goods);
												}
												// returngoodsList.addAll(goodsTempList);
												count = 0;
												break;
											} else {
												goods.setFlushCount(goods
														.getFlushCount()
														+ singleCount * single);// 余料需要重量
												// returngoodsList.add(goods);
												count = count - singleCount
														* single;
												if (!goodsTempList
														.contains(goods)) {
													goodsTempList.add(goods);
												}
											}
										} else {
											if (singleCount * single >= count
													* bizhong) {// 余料足够
												goods.setFlushCount(goods
														.getFlushCount()
														+ count * bizhong);// 余料需要重量
												count = 0;
												if (!goodsTempList
														.contains(goods)) {
													goodsTempList.add(goods);
												}
												// returngoodsList.addAll(goodsTempList);
												break;
											} else {
												goods.setFlushCount(goods
														.getFlushCount()
														+ singleCount * single);// 余料需要重量
												// returngoodsList.add(goods);
												count = count - singleCount;
												if (!goodsTempList
														.contains(goods)) {
													goodsTempList.add(goods);
												}
											}
										}
									}
								}
								// returngoodsList.addAll(goodsTempList);
							}
						}
					}
				}
				if (count == 0) {
					continue;
				}
				// 请领数量
				float lsCount = count;// 张数
				float surlsCount = count;// 实际所需
				String banbenSql = null;
				if (p.getBanBenNumber() == null
						|| p.getBanBenNumber().length() == 0) {
					banbenSql = " and (banBenNumber is null or banBenNumber ='')";
				} else {
					banbenSql = " and banBenNumber ='" + p.getBanBenNumber()
							+ "'";
				}
				String goodsSql1 = " from Goods where suodingdanhao in("
						+ oderNumber
						+ ") and goodsMarkId='"
						+ p.getMarkId()
						+ "'"
						+ goodsClassSql
						+ " and  goodsCurQuantity>0 and (goodsZhishu is null or goodsZhishu=0 or goodsZhishu>=1) and (goodsUnit =? or goodsStoreZHUnit=?)  and (fcStatus is null or  fcStatus!='封存') "
						+ banbenSql + "order by goodsLotId asc";
				List<Goods> goodsList1 = totalDao.query(goodsSql1, p.getUnit(),
						p.getUnit());
				String goodsSql2 = " from Goods where (suodingdanhao is null or suodingdanhao='') and goodsMarkId='"
						+ p.getMarkId()
						+ "'"
						+ goodsClassSql
						+ " and  goodsCurQuantity>0 and (goodsZhishu is null or goodsZhishu=0 or goodsZhishu>=1) and (goodsUnit =? or goodsStoreZHUnit=?)  and (fcStatus is null or  fcStatus!='封存') "
						+ banbenSql + "order by goodsLotId asc";
				List<Goods> goodsList2 = totalDao.query(goodsSql2, p.getUnit(),
						p.getUnit());
				List<Goods> listG = new ArrayList<Goods>();
				listG.addAll(goodsList1);
				listG.addAll(goodsList2);
				// Double d2 = Math.ceil(count);
				// count =
				// Float.parseFloat(d2.toString());
				if (listG.size() > 0) {
					int n = 0;
					for (Goods goo : listG) {
						n++;
						// goo
						// .setOrder_Id(procard
						// .getId());
						if (flagMap.get(p.getMarkId()) != null) {
							goo.setFlag(flagMap.get(p.getMarkId()));
						} else {
							flagMap.put(p.getMarkId(), maxFlag);
							goo.setFlag(maxFlag);
							maxFlag++;
						}
						goo.setDwyl(dwyl);
						goo.setShowType("外购件");
						goo.setQlCount(p.getWwhascount());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(0f);
						goo.setGoodsBeginQuantity(0f);
						goo.setIsEnough(true);
						goo.setIsChangeSf(false);
						goo.setGongxuName(gxslsb.toString());
						if (bizhong != null && bizhong > 0) {// 有比重需要换算单位
							if (p.getUnit()
									.equalsIgnoreCase(goo.getGoodsUnit())
									&& null != goo.getGoodsZhishu()
									&& goo.getGoodsZhishu() > 0) {// 单位一致有支数,用lsCount计数
								if (n == 1) {
									lsCount = (float) Math.ceil(lsCount
											/ bizhong);
								}
								if (bizhong != null && bizhong > 0) {
									if (goo.getGoodsZhishu() > lsCount) {
										goo.setGoodsZhishu(lsCount);
										// 计算重量
										if (lsCount * bizhong <= goo
												.getGoodsCurQuantity()) {
											goo.setGoodsBeginQuantity(lsCount
													* bizhong);
										}
										// 计算实际需要的重量
										if (goo.getGoodsCurQuantity() >= surlsCount) {
											goo.setGoodsCurQuantity(surlsCount);
											surlsCount = 0f;
										} else {
											goo.setGoodsCurQuantity(goo
													.getGoodsCurQuantity());
											surlsCount -= goo
													.getGoodsCurQuantity();
										}
										if (goo.getGoodsStoreZHUnit() == null
												|| goo.getGoodsStoreZHUnit()
														.length() == 0) {
											goo.setQlUnit("张");
										} else {
											goo.setQlUnit(goo
													.getGoodsStoreZHUnit());
										}
										goo.setIsChangeSf(true);
										lsCount = 0;
										count = 0;
										// returngoodsList.add(goo);
										goodsTempList.add(goo);
										// returngoodsList.addAll(goodsTempList);
										break;
									} else if (goo.getGoodsZhishu() == lsCount) {
										goo.setGoodsZhishu(lsCount);
										// 计算实际需要的重量
										if (goo.getGoodsCurQuantity() >= surlsCount) {
											goo.setGoodsCurQuantity(surlsCount);
											surlsCount = 0f;
										} else {
											goo.setGoodsCurQuantity(goo
													.getGoodsCurQuantity());
											surlsCount -= goo
													.getGoodsCurQuantity();
										}
										// 支数刚好情况重量，不重新计算重量
										// goo.setGoodsCurQuantity(lsCount
										// * bizhong);
										if (goo.getGoodsStoreZHUnit() == null
												|| goo.getGoodsStoreZHUnit()
														.length() == 0) {
											goo.setQlUnit("张");
										} else {
											goo.setQlUnit(goo
													.getGoodsStoreZHUnit());
										}
										goo.setIsChangeSf(true);
										lsCount = 0;
										count = 0;
										// returngoodsList.add(goo);
										goodsTempList.add(goo);
										// returngoodsList.addAll(goodsTempList);
										break;
									} else {
										lsCount = lsCount
												- goo.getGoodsZhishu();
										if (goo.getGoodsZhishu() * bizhong <= goo
												.getGoodsCurQuantity()) {
											goo.setGoodsCurQuantity(goo
													.getGoodsZhishu()
													* bizhong);
										}
										// 计算实际需要的重量
										if (goo.getGoodsCurQuantity() >= surlsCount) {
											goo.setGoodsCurQuantity(surlsCount);
											surlsCount = 0f;
										} else {
											goo.setGoodsCurQuantity(goo
													.getGoodsCurQuantity());
											surlsCount -= goo
													.getGoodsCurQuantity();
										}
										if (goo.getGoodsStoreZHUnit() == null
												|| goo.getGoodsStoreZHUnit()
														.length() == 0) {
											goo.setQlUnit("张");
										} else {
											goo.setQlUnit(goo
													.getGoodsStoreZHUnit());
										}
										goo.setIsChangeSf(true);
										// returngoodsList.add(goo);
										goodsTempList.add(goo);
									}
								}
							} else if (p.getUnit().equalsIgnoreCase(
									goo.getGoodsUnit())
									&& (null == goo.getGoodsZhishu() || goo
											.getGoodsZhishu() == 0)) {// 单位一致无支数
								// 此情况不需要比重,数据有误
							} else if (null != goo.getGoodsZhishu()
									&& goo.getGoodsZhishu() > 0) {// 单位不一致,
								// 有支数用count计数
								if (bizhong == null || bizhong == 0) {// 用当前库存重新计算比重
									bizhong = goo.getGoodsCurQuantity()
											/ goo.getGoodsZhishu();
								}
								if (n == 1) {
									surlsCount = count * bizhong;
								}
								if (goo.getGoodsZhishu() >= count) {
									goo.setGoodsZhishu(count);
									// 计算重量
									goo.setGoodsCurQuantity(count * bizhong);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									// returngoodsList.add(goo);
									goodsTempList.add(goo);
									// returngoodsList.addAll(goodsTempList);
									count = 0;
									break;
								} else if (goo.getGoodsZhishu() == count) {
									goo.setGoodsZhishu(count);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									// 支数刚好情况重量，不重新计算重量
									// goo.setGoodsCurQuantity(lsCount
									// * bizhong);
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									// returngoodsList.add(goo);
									goodsTempList.add(goo);
									// returngoodsList.addAll(goodsTempList);
									count = 0;
									break;
								} else {
									goo.setGoodsCurQuantity(goo
											.getGoodsZhishu()
											* bizhong);
									// 计算实际需要的重量
									if (goo.getGoodsCurQuantity() >= surlsCount) {
										goo.setGoodsCurQuantity(surlsCount);
										surlsCount = 0f;
									} else {
										goo.setGoodsCurQuantity(goo
												.getGoodsCurQuantity());
										surlsCount -= goo.getGoodsCurQuantity();
									}
									goo.setQlUnit(goo.getGoodsStoreZHUnit());
									returngoodsList.add(goo);
									count = count - goo.getGoodsZhishu();
									goodsTempList.add(goo);
								}

							} else {// 单位不一致无支数,无法计算

							}
						} else {// 无比重
							if (goo.getGoodsCurQuantity() >= count) {
								goo.setGoodsCurQuantity(count);
								goo.setGoodsBeginQuantity(count);
								// goo.setGoodsZhishu(count);
								count = 0;
								goo.setQlUnit(p.getUnit());
								// returngoodsList.add(goo);
								goodsTempList.add(goo);
								// returngoodsList.addAll(goodsTempList);
								break;
							} else {
								goo.setCkCount(goo.getGoodsCurQuantity());
								count -= goo.getGoodsCurQuantity();
								goo.setGoodsBeginQuantity(goo
										.getGoodsCurQuantity());
								// goo.setGoodsZhishu(goo
								// .getGoodsCurQuantity());
								goo.setQlUnit(p.getUnit());
								// returngoodsList.add(goo);
								goodsTempList.add(goo);
							}
						}

					}
					if (count > 0) {// 库存不够
						Goods gd = new Goods();
						// gd.setOrder_Id(procard.getId());
						gd.setDwyl(dwyl);
						gd.setGongxuName(gxslsb.toString());
						gd.setGoodsFullName(p.getProName());
						gd.setGoodsMarkId(p.getMarkId());
						gd.setGoodsUnit(p.getUnit());
						gd.setGoodsCurQuantity(0f);
						gd.setHqlCount(tqlcount);
						gd.setTqlCount(count);
						gd.setIsEnough(false);
						gd.setKgliao(p.getKgliao());
						// returngoodsList.add(gd);
						for (Goods gsTemp : goodsTempList) {
							gsTemp.setIsEnough(false);
						}
						goodsTempList.add(gd);
						totalEnough = false;
					}
					returngoodsList.addAll(goodsTempList);
				} else {
					String hqlGoods2 = " from Goods where goodsMarkId='"
							+ p.getMarkId()
							+ "'"
							+ goodsClassSql
							+ " and goodsCurQuantity=0 and (goodsUnit =? or goodsStoreZHUnit=?) and (fcStatus is null or  fcStatus!='封存') order by goodsLotId asc";
					List<Goods> listG2 = totalDao.query(hqlGoods2, p.getUnit(),
							p.getUnit());
					if (listG2.size() > 0) {
						Goods goo = listG2.get(0);
						// goo
						// .setOrder_Id(procard
						// .getId());
						goo.setDwyl(dwyl);
						goo.setGongxuName(gxslsb.toString());
						goo.setGoodsFullName(p.getProName());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(count);
						goo.setIsEnough(false);
						goo.setQlUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						returngoodsList.add(goo);
						totalEnough = false;
					} else {
						totalEnough = false;
						Goods goo = new Goods();
						goo.setDwyl(dwyl);
						goo.setGongxuName(gxslsb.toString());
						goo.setOrder_Id(p.getId());
						goo.setHqlCount(tqlcount);
						goo.setTqlCount(count);
						goo.setIsEnough(false);
						goo.setGoodsFullName(p.getProName());
						goo.setGoodsMarkId(p.getMarkId());
						goo.setGoodsId(0);
						goo.setQlUnit(p.getUnit());
						goo.setGoodsCurQuantity(0f);
						goo.setGoodsUnit(p.getUnit());
						goo.setKgliao(p.getKgliao());
						returngoodsList.add(goo);
					}
				}
			}
			map.put(2, returngoodsList);
		}
		map.put(3, checkboxs2);
		return map;
	}

	@Override
	public String outblDetail(int[] checkboxs, float[] peiqiCount,
			List<Goods> goodsList, int[] selected, String cardId,
			String password, Integer procardBlRootId) {
		// TODO Auto-generated method stub where kgliao=? and
		// goodscurquantity>0procardbl
		String time = Util.getDateTime();
		Users user = Util.getLoginUser();
		Users lingliaoren = (Users) totalDao
				.getObjectByCondition(
						"from Users where cardId=? and onWork not in('离职','离职中','内退','退休')",
						cardId);
		if (lingliaoren == null) {
			return "卡号有误!";
		}
		MD5 md5 = new MD5();
		String mdsPassword = md5.getMD5(password.getBytes());// 密码MD5转换
		Password oldpassword = lingliaoren.getPassword();
		if (!oldpassword.getPassword().equals(mdsPassword)) {
			return "刷卡人密码有误!";
		}
		Boolean useyl = !SystemShezhi("关闭余料库", "关闭余料库");
		Map<Integer, Float> map = new HashMap<Integer, Float>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < checkboxs.length; i++) {
			map.put(checkboxs[i], peiqiCount[i]);
			if (sb.length() == 0) {
				sb.append(checkboxs[i]);
			} else {
				sb.append("," + checkboxs[i]);
			}
		}
		List<Integer> ids = totalDao
				.query("select id from Procard where id in(" + sb.toString()
						+ ")  order by belongLayer desc");
		List<Goods> oldList = new ArrayList<Goods>();
		if (goodsList != null && goodsList.size() > 0) {
			for (Goods goods : goodsList) {
				if (goods != null && goods.getGoodsId() != null) {
					if (selected != null && selected.length > 0) {
						boolean b = false;
						for (int goodsId : selected) {
							if (goods.getGoodsId() != null
									&& goods.getGoodsId().equals(goodsId)) {
								if (goodsList != null && goodsList.size() > 0) {
									for (Goods qlGoods : goodsList) {
										if (qlGoods.getGoodsId() != null
												&& qlGoods.getGoodsId().equals(
														goodsId)) {
											Goods old = (Goods) totalDao
													.getObjectById(Goods.class,
															goods.getGoodsId());
											if (old != null) {
												oldList.add(old);
											}
										}
									}
								}
								b = true;
								break;
							}
						}
						if (!b) {// 此外购件没有被选中
							continue;
						}
					} else {
						throw new RuntimeException("请至少选中一个");
					}
				}
			}
		}
		// 安全库存
		Map<String, String> aqck = new HashMap<String, String>();
		// 余料待入库数据
		List<Goods> ylGoodsList = new ArrayList();
		Integer rootId = null;
		String rootSelfCard = null;
		String rootMarkId = null;
		String ywMarkId = null;
		String orderNumber = null;
		for (Integer pid : ids) {
			Procard procard = byProcardId(pid);
			if (rootId == null) {
				ywMarkId = procard.getYwMarkId();
				rootId = procard.getRootId();
				rootSelfCard = procard.getRootSelfCard();
				rootMarkId = procard.getRootMarkId();
				orderNumber = procard.getOrderNumber();
			}
			List<Procard> wgprocardSonList = new ArrayList<Procard>();
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())) {// 外购件半成品
				wgprocardSonList.add(procard);
				Float xlCount = map.get(pid);
				// 外委出去数量
				Float wwCount = (Float) totalDao
						.getObjectByCondition(
								"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwCount == null) {
					wwCount = 0f;
				}
				// 外委出去剩余未被领取数量
				Float wwhascount = (Float) totalDao
						.getObjectByCondition(
								"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwhascount == null) {
					wwhascount = 0f;
				}
				if (procard.getWwblCount() == null) {
					procard.setWwblCount(0f);
				}
				procard.setWwCount(wwCount + procard.getWwblCount());
				if (procard.getWwblreceiveCount() == null) {
					procard.setWwblreceiveCount(0f);
				}
				procard.setWwhascount(wwhascount
						+ (procard.getWwblCount() - procard
								.getWwblreceiveCount()));
				if (procard.getHascount() == null) {
					procard.setHascount(procard.getKlNumber());
				}
				// 生产未领数量
				Float thisHasCount = procard.getHascount()
						- procard.getWwhascount() - procard.getWwblCount();
				if (xlCount > thisHasCount) {
					xlCount = thisHasCount;
				}
				for (Goods old : oldList) {
					if (old.getGoodsMarkId().equalsIgnoreCase(
							procard.getMarkId())) {
						for (Goods goods : goodsList) {
							Float outzhishu = null;
							Float outCount = null;
							if (goods != null
									&& goods.getGoodsId() != null
									&& goods.getGoodsId().equals(
											old.getGoodsId())
									&& goods.getGoodsCurQuantity() > 0) {
								Float thisoutCount = null;
								String banben = aqck
										.get(goods.getGoodsMarkId());
								if (banben == null) {
									String banben2 = goods.getBanBenNumber() == null ? ""
											: goods.getBanBenNumber();
									aqck.put(goods.getGoodsMarkId(), banben2);
								}
								if (xlCount.floatValue() > goods
										.getGoodsCurQuantity().floatValue()) {// 这条数据不足以支持
									thisoutCount = goods.getGoodsCurQuantity();
									procard.setHascount(procard.getHascount()
											- goods.getGoodsCurQuantity());
									outCount = goods.getGoodsCurQuantity();
									xlCount -= goods.getGoodsCurQuantity();
									old.setGoodsCurQuantity(old
											.getGoodsCurQuantity()
											- goods.getGoodsCurQuantity());
									goods.setGoodsCurQuantity(0f);
								} else {
									thisoutCount = xlCount;
									procard.setHascount(procard.getHascount()
											- xlCount);
									outCount = xlCount;
									old.setGoodsCurQuantity(old
											.getGoodsCurQuantity()
											- xlCount);
									goods.setGoodsCurQuantity(goods
											.getGoodsCurQuantity()
											- xlCount);
									xlCount = 0f;
								}
								if (old.getGoodsCurQuantity().floatValue() < 0) {
									runtimeEx(old);
								}
								if (old.getGoodsZhishu() != null
										&& old.getGoodsZhishu() < 0) {
									runtimeExZhuan(old);
								}
								totalDao.update(old);
								String lingliaoDetail = procard
										.getLingliaoDetail();
								if (lingliaoDetail == null
										|| lingliaoDetail.length() == 0) {
									lingliaoDetail = old.getGoodsLotId() + ":"
											+ thisoutCount;
								} else {
									lingliaoDetail += "," + old.getGoodsLotId()
											+ ":" + thisoutCount;
								}
								procard.setLingliaoDetail(lingliaoDetail);
								if (procard.getHascount() == 0
										&& (procard.getKlNumber()
												.equals(procard
														.getFilnalCount()))) {
									ProcardBl bl = (ProcardBl) totalDao
											.getObjectByCondition(
													"from ProcardBl where procardId=? and status!='已领完' and rootId=?",
													procard.getId(),
													procardBlRootId);
									if (bl != null) {
										bl.setStatus("已领完");
										bl.setYlCount(bl.getPcCount());
										bl.setRlingliaoTime(Util.getDateTime());
										totalDao.update(bl);
									}
								}
								// 出库记录
								addSellWai(user, lingliaoren, procard, old,
										outzhishu, outCount, time);
								/******** 物料需求表回传已领数量wxf *************/

							}
						}
						if (xlCount == 0) {
							break;
						}
					}
					if (xlCount == 0) {
						break;
					}
				}
			} else {
				/** 自制件领下层 ***/
				Set<Procard> sonSet = procard.getProcardSet();
				for (Procard son : sonSet) {
					// if(son.getMarkId().equalsIgnoreCase("14080250CS")){
					// System.out.println(son.getMarkId());
					// }
					if (son.getProcardStyle().equalsIgnoreCase("外购")
							&& (son.getSbStatus() == null || !son.getSbStatus()
									.equalsIgnoreCase("删除"))
							&& (son.getLingliaostatus() == null || !son
									.getLingliaostatus().equals("否"))) {
						float addzzCount = 0f;
						wgprocardSonList.add(son);
						if (son.getKlNumber() == null) {
							// 激活之后klNumber会被赋值如果为空数据有误
							continue;
						}
						// 上层数量对应下层需领数量
						Float xlCount = map.get(pid) * son.getQuanzi2()
								/ son.getQuanzi1();

						/********* 控制排產的最大數量 ************/
						ProcardBl procardbl_wgj = (ProcardBl) totalDao
								.getObjectByCondition(
										"from ProcardBl where procardId=? and rootId=?",
										son.getId(), procardBlRootId);
						if (procardbl_wgj != null) {
							// 按照排产配套领取(排产的配套数量领完就不能继续领取。明确齐料性)
							Float count_bl = procardbl_wgj.getPcCount()
									- procardbl_wgj.getYlCount();
							if (xlCount > count_bl) {
								xlCount = count_bl;
							}
						}
						/********* 控制排產的最大數量結束 ************/

						// 外委出去数量
						Float wwCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										son.getId());
						if (wwCount == null) {
							wwCount = 0f;
						}
						// 外委出去剩余未被领取数量
						Float wwhascount = (Float) totalDao
								.getObjectByCondition(
										"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										son.getId());
						if (wwhascount == null) {
							wwhascount = 0f;
						}
						if (son.getWwblCount() == null) {
							son.setWwblCount(0f);
						}
						son.setWwCount(wwCount + son.getWwblCount());
						if (son.getWwblreceiveCount() == null) {
							son.setWwblreceiveCount(0f);
						}
						son.setWwhascount(wwhascount
								+ (son.getWwblCount() - son
										.getWwblreceiveCount()));
						if (son.getHascount() == null) {
							son.setHascount(son.getKlNumber());
						}
						// 生产未领数量
						Float thisHasCount = son.getHascount()
								- son.getWwhascount() - son.getWwblCount();
						if (xlCount > thisHasCount) {
							xlCount = thisHasCount;
						}
						Float oldHascount = son.getHascount();
						// 占用缺件计算
						// Float maxKlNumber =
						// Util.Floatdelete(son.getTjNumber(),Util.Floatdelete(son.getKlNumber(),son.getHascount()));
						// if (xlCount > maxKlNumber) {
						// xlCount = maxKlNumber;
						// }
						Float bizhong = null;
						if (useyl) {
							bizhong = (Float) totalDao
									.getObjectByCondition(
											"select bili from YuanclAndWaigj where markId =? and (banbenStatus is null or banbenStatus!='历史') ",
											son.getMarkId());
						}
						String banbenSql = null;
						if (son.getBanBenNumber() == null
								|| son.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ son.getBanBenNumber() + "'";
						}
						Goods googsTemp = (Goods) totalDao
								.getObjectByCondition(
										"from Goods where goodsMarkId=? and goodsCurQuantity>0 and  goodsZhishu>0 and kgliao=?"
												+ banbenSql, son.getMarkId(),
										son.getKgliao());
						for (Goods old : oldList) {
							if (old.getGoodsMarkId().equalsIgnoreCase(
									son.getMarkId())) {
								for (Goods goods : goodsList) {
									// if (son.getMarkId().equals("1.01.20199"))
									// {
									// System.out.println("1.01.20199");
									// }
									goods.setGoodsClass(old.getGoodsClass());
									Float outzhishu = null;
									Float outCount = null;
									if (xlCount > 0
											&& goods != null
											&& goods.getGoodsId() != null
											&& goods.getGoodsId().equals(
													old.getGoodsId())
											&& (goods.getGoodsCurQuantity() > 0 || (goods
													.getFlushCount2() != null && goods
													.getFlushCount2() > 0))) {
										Float thisoutCount = null;
										if ("余料库".equals(old.getGoodsClass())) {
											xlCount = LQYuLiao(user,
													lingliaoren, ylGoodsList,
													son, xlCount, bizhong,
													googsTemp, old, goods);
										} else {// 外购件库
											if (bizhong != null
													&& bizhong > 0
													&& goods.getFlushCount3() == null) {
												updateDan(goods);// 修正单张重量 精度为5
											}
											if (bizhong != null
													&& bizhong > 0
													&& goods.getFlushCount2() != null
													&& goods.getFlushCount2() > 0) {
												Float lout = null;
												if (goods.getFlushCount2() >= xlCount) {
													lout = xlCount;
													goods
															.setFlushCount2(Util
																	.Floatdelete(
																			goods
																					.getFlushCount2(),
																			xlCount));
													son
															.setHascount(Util
																	.Floatdelete(
																			son
																					.getHascount(),
																			xlCount));
													xlCount = 0f;
												} else {
													son
															.setHascount(Util
																	.Floatdelete(
																			son
																					.getHascount(),
																			goods
																					.getFlushCount2()));
													lout = goods
															.getFlushCount2();
													goods.setFlushCount(0f);
													xlCount = Util
															.Floatdelete(
																	xlCount,
																	goods
																			.getFlushCount2());
												}
												if (lout > 0) {
													son
															.setHascount(Util
																	.Floatdelete(
																			son
																					.getHascount(),
																			lout));
													String lingliaoDetail = son
															.getLingliaoDetail();
													if (lingliaoDetail == null
															|| lingliaoDetail
																	.length() == 0) {
														lingliaoDetail = old
																.getGoodsLotId()
																+ ":" + lout;
													} else {
														lingliaoDetail += ","
																+ old
																		.getGoodsLotId()
																+ ":" + lout;
													}
													if (son.getHascount()
															/ (son.getQuanzi2() / son
																	.getQuanzi1()) < 0.09) {
														son.setHascount(0f);
													}
													if (son.getHascount() == 0) {
														son.setStatus("完成");
													}
													if (son.getKlNumber() == null) {
														son
																.setKlNumber(son
																		.getFilnalCount());
													}
													float sonminNumber = (son
															.getKlNumber() - son
															.getHascount())
															* son.getQuanzi1()
															/ son.getQuanzi2();
													if (sonminNumber % 1 > 0.9) {
														sonminNumber = (float) Math
																.ceil(sonminNumber);
													} else {
														sonminNumber = (float) Math
																.floor(sonminNumber);
													}
													son
															.setMinNumber(sonminNumber);
													son
															.setTjNumber(son
																	.getKlNumber()
																	- son
																			.getHascount());
													son
															.setLingliaoDetail(lingliaoDetail);
													// 出库记录
													addSell(user, lingliaoren,
															son, old, lout,
															time);
													if (xlCount == 0
															|| son
																	.getHascount() == 0) {
														totalDao.update(son);
														break;
													}
												}
											}
											if (xlCount > old
													.getGoodsCurQuantity()) {// 这条数据不足以支持
												son
														.setHascount(Util
																.Floatdelete(
																		son
																				.getHascount(),
																		old
																				.getGoodsCurQuantity()));
												thisoutCount = old
														.getGoodsCurQuantity();
												addzzCount += old
														.getGoodsCurQuantity();
												outCount = old
														.getGoodsCurQuantity();
												xlCount = Util
														.Floatdelete(
																xlCount,
																old
																		.getGoodsCurQuantity());
												old.setGoodsCurQuantity(0f);
												goods.setGoodsCurQuantity(0f);
												outzhishu = goods
														.getGoodsZhishu();
												old.setGoodsZhishu(0f);
											} else {
												son.setHascount(Util
														.Floatdelete(son
																.getHascount(),
																xlCount));
												if (bizhong != null
														&& bizhong > 0) {
													outCount = xlCount;
													Float lsCount = (float) Math
															.ceil(xlCount
																	/ goods
																			.getFlushCount3());// 换算张数
													Float count2 = Util
															.Floatmul(
																	lsCount,
																	goods
																			.getFlushCount3());
													if (old
															.getGoodsCurQuantity() > count2) {
														if (old
																.getGoodsZhishu() > lsCount) {// 张数足够
															outzhishu = lsCount;
															old
																	.setGoodsCurQuantity(Util
																			.Floatdelete(
																					old
																							.getGoodsCurQuantity(),
																					count2));
															old
																	.setGoodsZhishu(Util
																			.Floatdelete(
																					old
																							.getGoodsZhishu(),
																					lsCount));
														} else {
															outzhishu = old
																	.getGoodsZhishu();
															old
																	.setGoodsCurQuantity(0f);
															old
																	.setGoodsZhishu(0f);
														}
														goods
																.setGoodsCurQuantity(Util
																		.Floatdelete(
																				goods
																						.getGoodsCurQuantity(),
																				count2));
														if (count2 > xlCount) {
															Float ycount = Util
																	.Floatdelete(
																			count2,
																			xlCount);
															if (goods
																	.getFlushCount2() == null) {
																goods
																		.setFlushCount2(ycount);
															} else {
																goods
																		.setFlushCount2(Util
																				.Floatadd(
																						goods
																								.getFlushCount2(),
																						ycount));
															}
														}
													} else {
														outzhishu = old
																.getGoodsZhishu();
														old
																.setGoodsCurQuantity(0f);
														old.setGoodsZhishu(0f);
														if (old
																.getGoodsCurQuantity() > xlCount) {
															Float ycount = Util
																	.Floatdelete(
																			old
																					.getGoodsCurQuantity(),
																			xlCount);
															if (goods
																	.getFlushCount2() == null) {
																goods
																		.setFlushCount2(ycount);
															} else {
																goods
																		.setFlushCount2(Util
																				.Floatadd(
																						goods
																								.getFlushCount2(),
																						ycount));
															}
														}

													}

												} else {
													thisoutCount = xlCount;
													addzzCount += xlCount;
													outCount = xlCount;
													old
															.setGoodsCurQuantity(old
																	.getGoodsCurQuantity()
																	- xlCount);
													goods
															.setGoodsCurQuantity(goods
																	.getGoodsCurQuantity()
																	- xlCount);
													xlCount = 0f;
												}

											}
											if (old.getGoodsCurQuantity() < 0) {
												runtimeEx(old);
											}
											if (old.getGoodsZhishu() != null
													&& old.getGoodsZhishu() < 0) {
												runtimeExZhuan(old);
											}
											totalDao.update(old);
											if (outCount > 0) {
												String lingliaoDetail = son
														.getLingliaoDetail();
												if (lingliaoDetail == null
														|| lingliaoDetail
																.length() == 0) {
													lingliaoDetail = old
															.getGoodsLotId()
															+ ":" + outCount;
												} else {
													lingliaoDetail += ","
															+ old
																	.getGoodsLotId()
															+ ":" + outCount;
												}
												if ((son.getUnit().equalsIgnoreCase("kg")
														||son.getUnit().equalsIgnoreCase("g"))&&son.getHascount()
														/ (son.getQuanzi2() / son
																.getQuanzi1()) < 0.09) {
													son.setHascount(0f);
												}
												if (son.getHascount() == 0) {
													son.setStatus("完成");
												}
												if (son.getKlNumber() == null) {
													son.setKlNumber(son
															.getFilnalCount());
												}
												float sonminNumber = (son
														.getKlNumber() - son
														.getHascount())
														* son.getQuanzi1()
														/ son.getQuanzi2();
												if (sonminNumber % 1 > 0.9) {
													sonminNumber = (float) Math
															.ceil(sonminNumber);
												} else {
													sonminNumber = (float) Math
															.floor(sonminNumber);
												}
												son.setMinNumber(sonminNumber);
												son.setTjNumber(son
														.getKlNumber()
														- son.getHascount());
												son
														.setLingliaoDetail(lingliaoDetail);
												// 自制件下层出库记录
												addSellWai1(user, lingliaoren,
														son, old, outzhishu,
														outCount, time);
											}
										}
									}
								}
								if (xlCount == 0) {
									totalDao.update(son);
									break;
								}
							}
							if (xlCount == 0) {
								totalDao.update(son);
								break;
							}
						}
						if (procardbl_wgj != null) {
							Float blylcount = procardbl_wgj.getYlCount()
									+ (oldHascount - son.getHascount());
							if (blylcount > procardbl_wgj.getPcCount()) {
								blylcount = procardbl_wgj.getPcCount();
							}
							procardbl_wgj.setYlCount(blylcount);
							procardbl_wgj.setRlingliaoTime(Util.getDateTime());
							totalDao.update(procardbl_wgj);
						}
						totalDao.update(son);
						if (addzzCount > 0) {
							// 添加外购件在制品
							zaizhiInput(son, user, addzzCount, "在制品入库");
						}
					}
					totalDao.update(son);
				}
			}
			// 最后一道工序数量判断
			float lasttotalCount = procard.getFilnalCount();// 最后一道工序的可领数量
			float beforettalCount = procard.getFilnalCount();// 除最后一道工序的可领数量
			// if(procard.getLingliaoType()!=null&&
			// procard.getLingliaoType().equals("part")){
			// Float xcMinNumber = (Float)
			// totalDao.getObjectByCondition("select max(minNumber) from Procard where fatherId=?"
			// +
			// " and (procardStyle='自制' or (procardStyle='外购' and needProcess='yes' ) and (sbStatus is null or sbStatus !='删除'))",
			// procard.getId());
			// if(xcMinNumber!=null){
			// beforettalCount=xcMinNumber;
			// }
			// }else{
			// Float xcMinNumber = (Float)
			// totalDao.getObjectByCondition("select min(minNumber) from Procard where fatherId=?"
			// +
			// " and (procardStyle='自制' or (procardStyle='外购' and needProcess='yes' ) and (sbStatus is null or sbStatus !='删除'))",
			// procard.getId());
			// if(xcMinNumber!=null){
			// beforettalCount=xcMinNumber;
			// }
			// }
			// 已配套套数量
			if (procard.getKlNumber() == null) {
				procard.setKlNumber(procard.getFilnalCount());
			}
			if (procard.getHascount() == null) {
				procard.setHascount(procard.getKlNumber());
			}
			Float peitaoCount = procard.getKlNumber() - procard.getHascount();
			Float scpeitao = null;// 生产配套数量
			Float xcMinhasCount = null;
			xcMinhasCount = procard.getFilnalCount();
			boolean hasll = false;
			List<Procard> peitaoSonList = null;
			Float minNumber = 0f;
			if (procard.getProcardStyle().equals("外购")) {
				peitaoSonList = wgprocardSonList;
				minNumber = procard.getFilnalCount();
			} else {
				peitaoSonList = totalDao
						.query(
								"from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') and procardStyle='外购'"
										+ " and (needProcess is null or needProcess !='yes') and (lingliaostatus is null or lingliaostatus!='否')",
								procard.getId());
				if(procard.getProcardStyle().equals("总成")){
					minNumber = (Float) totalDao
					.getObjectByCondition(
							"select min(minNumber) from Procard where procard.id=? and (oldProcardId is null or oldProcardId=0) and (sbStatus is null or sbStatus !='删除') "
							+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
							procard.getId());
				}else{
					minNumber = (Float) totalDao
					.getObjectByCondition(
							"select min(minNumber) from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') "
							+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
							procard.getId());
				}
				if (minNumber == null) {
					Float sccount = (Float) totalDao
							.getObjectByCondition(
									"select count(*) from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') "
											+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
									procard.getId());
					if (sccount == null || sccount == 0) {
						minNumber = procard.getFilnalCount();
					} else {
						minNumber = 0f;
					}
				}
				if (lasttotalCount > minNumber) {
					lasttotalCount = minNumber;
				}
			}
			Map<Integer, Float> processxz = new HashMap<Integer, Float>();// 工序数量限制
			for (Procard son : peitaoSonList) {
				List<Integer> processNoList = totalDao
						.query(
								"select distinct(processNO) from ProcessInfor where procard.id=? and (dataStatus is null or dataStatus!='删除') and  processName in"
										+ "( select processName from ProcessAndWgProcardTem where procardMarkId=? and wgprocardMardkId=?)",
								procard.getId(), procard.getMarkId(), son
										.getMarkId());
				Float gxCount = 0f;
				if (son.getHascount() == null) {// 被包工包料外委出去了
					scpeitao = 0f;
					lasttotalCount = 0f;
					xcMinhasCount = 0f;
				} else {
					// 生产已领数量
					Float scylCount = son.getKlNumber() - son.getHascount();
					// - wwylCount;
					if (scylCount > 0) {
						hasll = true;
					}
					if (son.getNeedProcess() == null
							|| !son.getNeedProcess().equals("yes")) {
						gxCount = scylCount * son.getQuanzi1()
								/ son.getQuanzi2();
						if(son.getHascount()==0){
							gxCount = (float) Math.floor(gxCount) + 1f;
						}
						if (scpeitao == null) {
							scpeitao = scylCount * son.getQuanzi1()
									/ son.getQuanzi2();
						} else if (scpeitao > gxCount) {
							scpeitao = gxCount;
						}
						if ((scpeitao - Math.floor(scpeitao)) > 0.95) {
							scpeitao = (float) Math.floor(scpeitao) + 1f;
						}
					} else {
						gxCount = scylCount;
						if (scpeitao == null) {
							scpeitao = scylCount;
						} else if (scpeitao > scylCount) {
							scpeitao = scylCount;
						}
						if ((scpeitao - Math.floor(scpeitao)) > 0.95) {
							scpeitao = (float) Math.floor(scpeitao) + 1f;
						}
					}
					float totalHasCount2 = (son.getKlNumber() - son
							.getHascount())
							* son.getQuanzi1() / son.getQuanzi2();
					if (son.getHascount() == 0) {
						totalHasCount2 = procard.getFilnalCount();
					}
					if (son.getKlNumber() >= son.getFilnalCount()
							&& son.getHascount() == 0) {// 小数位数问题
						totalHasCount2 = procard.getFilnalCount();
					}
					if (totalHasCount2 < 0) {
						totalHasCount2 = 0f;
					}
					if (totalHasCount2 < lasttotalCount) {
						lasttotalCount = totalHasCount2;
					}
					if (procard.getLingliaoType() == null
							|| !procard.getLingliaoType().equals("part")) {// 配齐方式
						if (totalHasCount2 < beforettalCount) {
							beforettalCount = totalHasCount2;
						}
					} else {
						if (totalHasCount2 > beforettalCount) {
							beforettalCount = totalHasCount2;
						}
					}
					if ((son.getHascount() * son.getQuanzi1() / son
							.getQuanzi2()) < xcMinhasCount) {
						xcMinhasCount = son.getHascount() * son.getQuanzi1()
								/ son.getQuanzi2();

					}
					if ((xcMinhasCount - Math.floor(xcMinhasCount)) > 0.95) {
						xcMinhasCount = (float) Math.floor(xcMinhasCount) + 1f;
					}
				}
				if (processNoList != null && processNoList.size() > 0) {
					for (Integer pno : processNoList) {
						Float gxxz = processxz.get(pno);
						if (gxxz == null || gxxz > gxCount) {
							gxxz = gxCount;
						}
						processxz.put(pno, gxxz);
					}
				}

			}
			if (hasll) {
				if (procard.getStatus().equals("已发卡")) {
					procard.setStatus("已发料");
				}
			}
			if (scpeitao == null) {
				scpeitao = 0f;
			}
			if (scpeitao % 1 < 0.95) {
				scpeitao = (float) Math.floor(scpeitao);
			} else {
				scpeitao = (float) Math.ceil(scpeitao);
			}
			Float hascount = procard.getKlNumber() - scpeitao;
			if (hascount < 0) {
				hascount = 0f;
			}
			procard.setHascount(hascount);
			if (procard.getHascount() == 0
					&& (procard.getKlNumber().equals(procard.getFilnalCount()))) {
				ProcardBl bl = (ProcardBl) totalDao
						.getObjectByCondition(
								"from ProcardBl where procardId=? and status!='已领完' and rootId=?",
								procard.getId(), procardBlRootId);
				if (bl != null) {
					bl.setStatus("已领完");
					bl.setYlCount(bl.getPcCount());
					bl.setRlingliaoTime(Util.getDateTime());
					totalDao.update(bl);
				}
			}
			String hql_update = "from ProcessInfor where procard.id=? and (dataStatus is null or dataStatus !='删除') order by processNO";
			List<ProcessInfor> sonProcessinfor = totalDao.query(hql_update,
					procard.getId());
			Integer maxProcessNo = -1;
			String upProcesstype = "";
			String upNeedSave = null;
			boolean b = true;// 半成品转库之后工序限制可领数量，半成品转库或者领取之后解开限制数量
			for (int i = 0; i < sonProcessinfor.size(); i++) {
				ProcessInfor processInfor = sonProcessinfor.get(i);
				Float selectWwCount = processInfor.getSelectWwCount() == null ? 0
						: processInfor.getSelectWwCount();// 已选外委数量
				Float applyWwCount = processInfor.getApplyWwCount() == null ? 0
						: processInfor.getApplyWwCount();// 外委申请中数量
				Float agreeWwCount = processInfor.getAgreeWwCount() == null ? 0
						: processInfor.getAgreeWwCount();// 外委同意数量
				Float wwbackCount = processInfor.getWwbackCount() == null ? 0
						: processInfor.getWwbackCount();// 外委回来数量
				// 总数量-外委没有回来数量==最大激活数量
				Float thismaxCount = procard.getFilnalCount()
						- (selectWwCount + applyWwCount + agreeWwCount - wwbackCount);
				if (beforettalCount > thismaxCount) {
					beforettalCount = thismaxCount;
				}
				if ((beforettalCount - Math.floor(beforettalCount)) > 0.95) {
					beforettalCount = (float) Math.floor(beforettalCount) + 1f;
				} else {
					beforettalCount = (float) Math.floor(beforettalCount);
				}
				if (b) {
					if ((upNeedSave != null && upNeedSave.equals("是"))
							&& (processInfor.getNeedSave() == null || !processInfor
									.getNeedSave().equals("是"))) {
						b = false;
					}
					if (upProcesstype.equals("外委")
							&& processInfor.getProductStyle() != null
							&& processInfor.getProductStyle().equals("自制")) {
						b = false;
					}
					if (upProcesstype.equals("外委")
							&& processInfor.getProductStyle() != null
							&& processInfor.getProductStyle().equals("外委")
							&& processInfor.getProcessStatus() != null
							&& processInfor.getProcessStatus().equals("no")) {
						b = false;
					}
				}
				upProcesstype = processInfor.getProductStyle();
				upNeedSave = processInfor.getNeedSave();
				if (i == (sonProcessinfor.size() - 1)) {
					// && ((pro.getKlNumber() - pro.getHascount()) >=
					// lasttotalCount)) {// 部分领料最后一道工序的可领数量为最小minNumber-
					maxProcessNo = processInfor.getProcessNO();// 最大工序号
					if (!b) {
						if (procard.getFilnalCount().equals(
								processInfor.getTotalCount())) {// 第一次领
							processInfor.setTotalCount(0f);
						}
					} else {
						processInfor.setTotalCount((float) Math
								.floor(lasttotalCount));
						if (processInfor.getTotalCount() < processInfor
								.getApplyCount()) {
							processInfor.setTotalCount(processInfor
									.getApplyCount());
						}
					}
				} else {
					if (!b) {
						if (procard.getFilnalCount().equals(
								processInfor.getTotalCount())) {// 第一次领
							processInfor.setTotalCount(0f);
						}
					} else {
						if (beforettalCount < processInfor.getApplyCount()) {
							processInfor.setTotalCount(processInfor
									.getApplyCount());
						} else {
							processInfor.setTotalCount(beforettalCount);
						}

					}
				}
				Float gxzx = processxz.get(processInfor.getProcessNO());
				if (gxzx != null) {
					if ((gxzx - Math.floor(gxzx)) > 0.95) {
						gxzx = (float) Math.floor(gxzx) + 1f;
					} else {
						gxzx = (float) Math.floor(gxzx);
					}
				}
				if (gxzx != null && processInfor.getTotalCount() > gxzx) {
					if (gxzx < processInfor.getApplyCount()) {
						processInfor
								.setTotalCount(processInfor.getApplyCount());
					} else {
						processInfor.setTotalCount(gxzx);
					}
				}
				totalDao.update(processInfor);
			}
			totalDao.update(procard);
			// 计算现已生成在制品数量
			Float xyCount = (Float) totalDao
					.getObjectByCondition(
							"select sum(zyCount) from ProcardProductRelation where flagType='本批在制品' and procardId=?",
							procard.getId());
			if (xyCount == null) {
				xyCount = 0f;
			}
			Float addzaizhi = (float) Math.floor(procard.getKlNumber()
					- xcMinhasCount - xyCount);
			if (addzaizhi > 0) {
				zaizhiInput(procard, user, addzaizhi, "在制品入库");
			}

		}
		if (ylGoodsList != null && ylGoodsList.size() > 0) {// 余料出库生成余料数据
			// 获取余料批次
			String before = "yl_" + Util.getDateTime("yyyyMMdd");
			String maxnumber = (String) totalDao
					.getObjectByCondition("select max(goodsLotId) from Goods where goodsClass='余料' and goodsLotId like '"
							+ before + "%'");
			Integer maxint = 0;
			DecimalFormat df = new DecimalFormat("00000");
			if (maxnumber != null && !"".equals(maxnumber)) {
				// String number1 = paymentVoucher2.getNumber();
				String now_number = maxnumber.split(before)[1];
				maxint = Integer.parseInt(now_number);
			}
			int i = 0;
			for (Goods yl : ylGoodsList) {
				if (yl.getFlushCount2() > 0) {
					yl.setGoodsCurQuantity(0f);// 余料整体出库，然后待入库
					yl.setGoodsZhishu(0f);
					totalDao.update(yl);
					i++;
					String jishu = df.format(maxint + i);
					String goodsLotId = before + jishu;
					// 余料出入库操作
					addYuliao(user, rootId, rootMarkId, yl, goodsLotId);
				}
			}
		}
		if (goodsList != null && goodsList.size() > 0) {// 非余料出库生成余料数据
			// 获取余料批次
			String before = "yl_" + Util.getDateTime("yyyyMMdd");
			String maxnumber = (String) totalDao
					.getObjectByCondition("select max(goodsLotId) from Goods where goodsClass='余料' and goodsLotId like '"
							+ before + "%'");
			Integer maxint = 0;
			DecimalFormat df = new DecimalFormat("00000");
			if (maxnumber != null && !"".equals(maxnumber)) {
				// String number1 = paymentVoucher2.getNumber();
				String now_number = maxnumber.split(before)[1];
				maxint = Integer.parseInt(now_number);
			}
			int i = 0;
			for (Goods yl : goodsList) {
				if (!"余料".equals(yl.getGoodsClass())
						&& yl.getFlushCount2() != null
						&& yl.getFlushCount2() > 0) {
					if (yl.getGoodsId() != null) {
						Goods oldyl = (Goods) totalDao.getObjectById(
								Goods.class, yl.getGoodsId());
						if (oldyl != null) {
							i++;
							// 余料出库记录
							addSellYl(user, lingliaoren, rootSelfCard,
									rootMarkId, ywMarkId, orderNumber, yl,
									oldyl);
							String jishu = df.format(maxint + i);
							String goodsLotId = before + jishu;
							// 添加余料库存
							Goods ylGoods = addYuliaoGoods(yl, oldyl,
									goodsLotId);
							// 余料产品中间表
							addYlzhong(rootId, ylGoods);
							// 余料入库记录
							addGoodsYl(user, rootMarkId, ywMarkId, orderNumber,
									yl, goodsLotId, ylGoods);
						}
					}
				}
			}
		}
		// 安全库存

		/**************************** 判断剩余库存量是否小与最小库存量 *********************************************************************************************/
		Set<String> keySet = aqck.keySet();
		if (keySet != null && keySet.size() > 0) {
			for (String key : keySet) {
				String value = aqck.get(key);
				addneedcg(key, value);
			}
		}
		return "true";
	}

	/***
	 * 单件领料模式--新
	 * 
	 * @param checkboxs
	 * @param peiqiCount
	 * @param goodsList
	 * @param selected
	 * @param cardId
	 * @param password
	 * @param procardBlRootId
	 * @return
	 */
	@Override
	public String updateblDetailNew(int[] checkboxs, float[] peiqiCount,
			List<Goods> goodsList, int[] selected, String cardId,
			String password, Integer procardBlRootId) {
		// TODO Auto-generated method stub where kgliao=? and
		// goodscurquantity>0procardbl
		String time = Util.getDateTime();
		Users user = Util.getLoginUser();
		Users lingliaoren = (Users) totalDao
				.getObjectByCondition(
						"from Users where cardId=? and onWork not in('离职','离职中','内退','退休')",
						cardId);
		if (lingliaoren == null) {
			return "卡号有误!";
		}
		MD5 md5 = new MD5();
		String mdsPassword = md5.getMD5(password.getBytes());// 密码MD5转换
		Password oldpassword = lingliaoren.getPassword();
		if (!oldpassword.getPassword().equals(mdsPassword)) {
			return "刷卡人密码有误!";
		}
		Boolean useyl = !SystemShezhi("关闭余料库", "关闭余料库");
		Map<Integer, Float> map = new HashMap<Integer, Float>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < checkboxs.length; i++) {
			map.put(checkboxs[i], peiqiCount[i]);
			if (sb.length() == 0) {
				sb.append(checkboxs[i]);
			} else {
				sb.append("," + checkboxs[i]);
			}
		}
		List<Integer> ids = totalDao
				.query("select id from Procard where id in(" + sb.toString()
						+ ") order by belongLayer desc");

		List<Goods> oldList = new ArrayList<Goods>();
		String sonMarkId = "";
		Float pageGoodscount = 0F;// 本次可领最大数量
		if (goodsList != null && goodsList.size() > 0) {
			Goods goods = goodsList.get(0);
			if (goods != null) {
				pageGoodscount = goods.getGoodsCurQuantity();
				sonMarkId = "'" + goods.getGoodsMarkId() + "'";
			}
			if (selected != null && selected.length > 0) {
				int goodsId = selected[0];
				if (goods.getGoodsId().equals(goodsId)) {
					Goods old = (Goods) totalDao.getObjectById(Goods.class,
							goods.getGoodsId());
					if (old != null) {
						oldList.add(old);
					}
				}
			} else {
				throw new RuntimeException("请至少选中一个");
			}
		}
		// 安全库存
		Map<String, String> aqck = new HashMap<String, String>();
		// 余料待入库数据
		List<Goods> ylGoodsList = new ArrayList();
		Integer rootId = null;
		String rootSelfCard = null;
		String rootMarkId = null;
		String ywMarkId = null;
		String orderNumber = null;

		for (Integer pid : ids) {
			if (pageGoodscount <= 0) {
				break;
			}
			Procard procard = byProcardId(pid);
			if (rootId == null) {
				ywMarkId = procard.getYwMarkId();
				rootId = procard.getRootId();
				rootSelfCard = procard.getRootSelfCard();
				rootMarkId = procard.getRootMarkId();
				orderNumber = procard.getOrderNumber();
			}
			List<Procard> wgprocardSonList = new ArrayList<Procard>();
			if ("外购".equalsIgnoreCase(procard.getProcardStyle())) {// 外购件半成品
				wgprocardSonList.add(procard);
				Float xlCount = map.get(pid);
				/********* 控制排產的最大數量 ************/
				ProcardBl procardbl_wgj = (ProcardBl) totalDao
						.getObjectByCondition(
								"from ProcardBl where procardId=? and rootId=?",
								procard.getId(), procardBlRootId);
				if (procardbl_wgj != null) {
					// 按照排产配套领取(排产的配套数量领完就不能继续领取。明确齐料性)
					Float count_bl = procardbl_wgj.getPcCount()
							- procardbl_wgj.getYlCount();
					if (xlCount > count_bl) {
						xlCount = count_bl;
					}
				}
				/********* 控制排產的最大數量結束 ************/
				// 外委出去数量
				Float wwCount = (Float) totalDao
						.getObjectByCondition(
								"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwCount == null) {
					wwCount = 0f;
				}
				// 外委出去剩余未被领取数量
				Float wwhascount = (Float) totalDao
						.getObjectByCondition(
								"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
								procard.getId());
				if (wwhascount == null) {
					wwhascount = 0f;
				}
				if (procard.getWwblCount() == null) {
					procard.setWwblCount(0f);
				}
				procard.setWwCount(wwCount + procard.getWwblCount());
				if (procard.getWwblreceiveCount() == null) {
					procard.setWwblreceiveCount(0f);
				}
				procard.setWwhascount(wwhascount
						+ (procard.getWwblCount() - procard
								.getWwblreceiveCount()));
				if (procard.getHascount() == null) {
					procard.setHascount(procard.getKlNumber());
				}
				// 生产未领数量
				Float thisHasCount = procard.getHascount()
						- procard.getWwhascount() - procard.getWwblCount();
				if (xlCount > thisHasCount) {
					xlCount = thisHasCount;
				}
				if (xlCount > pageGoodscount) {
					xlCount = pageGoodscount;
					pageGoodscount = 0F;
				} else {
					pageGoodscount -= xlCount;
				}
				for (Goods old : oldList) {
					if (old.getGoodsMarkId().equalsIgnoreCase(
							procard.getMarkId())) {
						for (Goods goods : goodsList) {
							Float outzhishu = null;
							Float outCount = null;
							if (goods != null
									&& goods.getGoodsId() != null
									&& goods.getGoodsId().equals(
											old.getGoodsId())
									&& goods.getGoodsCurQuantity() > 0) {
								Float thisoutCount = null;
								String banben = aqck
										.get(goods.getGoodsMarkId());
								if (banben == null) {
									String banben2 = goods.getBanBenNumber() == null ? ""
											: goods.getBanBenNumber();
									aqck.put(goods.getGoodsMarkId(), banben2);
								}
								if (xlCount.floatValue() > goods
										.getGoodsCurQuantity().floatValue()) {// 这条数据不足以支持
									thisoutCount = goods.getGoodsCurQuantity();
									Float hascount = procard.getHascount()
											- goods.getGoodsCurQuantity();
									if (hascount < 0) {
										hascount = 0f;
									}
									procard.setHascount(hascount);
									outCount = goods.getGoodsCurQuantity();
									xlCount -= goods.getGoodsCurQuantity();
									old.setGoodsCurQuantity(old
											.getGoodsCurQuantity()
											- goods.getGoodsCurQuantity());
									goods.setGoodsCurQuantity(0f);
								} else {
									thisoutCount = xlCount;
									Float hascount = procard.getHascount()
											- xlCount;
									if (hascount < 0) {
										hascount = 0f;
									}
									procard.setHascount(hascount);
									outCount = xlCount;
									old.setGoodsCurQuantity(old
											.getGoodsCurQuantity()
											- xlCount);
									goods.setGoodsCurQuantity(goods
											.getGoodsCurQuantity()
											- xlCount);
									xlCount = 0f;
								}
								if (old.getGoodsCurQuantity().floatValue() < 0) {
									runtimeEx(old);
								}
								if (old.getGoodsZhishu() != null
										&& old.getGoodsZhishu() < 0) {
									runtimeExZhuan(old);
								}
								totalDao.update(old);
								String lingliaoDetail = procard
										.getLingliaoDetail();
								if (lingliaoDetail == null
										|| lingliaoDetail.length() == 0) {
									lingliaoDetail = old.getGoodsLotId() + ":"
											+ thisoutCount;
								} else {
									lingliaoDetail += "," + old.getGoodsLotId()
											+ ":" + thisoutCount;
								}
								procard.setLingliaoDetail(lingliaoDetail);
								if (procard.getHascount() == 0
										&& (procard.getKlNumber()
												.equals(procard
														.getFilnalCount()))) {
									ProcardBl bl = (ProcardBl) totalDao
											.getObjectByCondition(
													"from ProcardBl where procardId=? and status!='已领完' and rootId=?",
													procard.getId(),
													procardBlRootId);
									if (bl != null) {
										bl.setStatus("已领完");
										bl.setYlCount(bl.getPcCount());
										bl.setRlingliaoTime(Util.getDateTime());
										totalDao.update(bl);
									}
								}
								// 出库记录
								addSellWai(user, lingliaoren, procard, old,
										outzhishu, outCount, time);
								/******** 物料需求表回传已领数量wxf *************/

							}
						}
						if (xlCount == 0) {
							break;
						}
					}
					if (xlCount == 0) {
						break;
					}
				}
			} else {
				/** 自制件领下层 ***/
				// Set<Procard> sonSet = procard.getProcardSet();
				String hql_sonlist = "from Procard where fatherId=? and procardStyle='外购' and markId in ("
						+ sonMarkId + ")";
				List<Procard> sonList = totalDao.query(hql_sonlist, procard
						.getId());
				if (sonList == null || sonList.size() == 0) {
					return "未查到" + procard.getMarkId() + "的下层外购件,无法领料。";
				}
				for (Procard son : sonList) {
					// if(son.getMarkId().equalsIgnoreCase("14080250CS")){
					// System.out.println(son.getMarkId());
					// }
					if (son.getProcardStyle().equalsIgnoreCase("外购")
							&& (son.getSbStatus() == null || !son.getSbStatus()
									.equalsIgnoreCase("删除"))
							&& (son.getLingliaostatus() == null || !son
									.getLingliaostatus().equals("否"))) {
						float addzzCount = 0f;
						wgprocardSonList.add(son);
						if (son.getKlNumber() == null) {
							// 激活之后klNumber会被赋值如果为空数据有误
							continue;
						}
						// 上层数量对应下层需领数量
						Float xlCount = map.get(pid) * son.getQuanzi2()
								/ son.getQuanzi1();

						/********* 控制排產的最大數量 ************/
						ProcardBl procardbl_wgj = (ProcardBl) totalDao
								.getObjectByCondition(
										"from ProcardBl where procardId=? and rootId=?",
										son.getId(), procardBlRootId);
						if (procardbl_wgj != null) {
							// 按照排产配套领取(排产的配套数量领完就不能继续领取。明确齐料性)
							Float count_bl = procardbl_wgj.getPcCount()
									- procardbl_wgj.getYlCount();
							if (son.getWwblCount() != null) {
								count_bl = count_bl - son.getWwblCount()
										* procardbl_wgj.getPcCount()
										/ son.getFilnalCount();
							}
							if (xlCount > count_bl) {
								xlCount = count_bl;
							}
						}
						/********* 控制排產的最大數量結束 ************/

						// 外委出去数量
						Float wwCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										son.getId());
						if (wwCount == null) {
							wwCount = 0f;
						}
						// 外委出去剩余未被领取数量
						Float wwhascount = (Float) totalDao
								.getObjectByCondition(
										"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus  not in('删除','取消')) and processInforWWApply.status !='打回')",
										son.getId());
						if (wwhascount == null) {
							wwhascount = 0f;
						}
						if (son.getWwblCount() == null) {
							son.setWwblCount(0f);
						}
						son.setWwCount(wwCount + son.getWwblCount());
						if (son.getWwblreceiveCount() == null) {
							son.setWwblreceiveCount(0f);
						}
						son.setWwhascount(wwhascount
								+ (son.getWwblCount() - son
										.getWwblreceiveCount()));
						if (son.getHascount() == null) {
							son.setHascount(son.getKlNumber());
						}
						// 生产未领数量
						Float thisHasCount = son.getHascount()
								- son.getWwhascount();
						if (xlCount > thisHasCount) {
							xlCount = thisHasCount;
						}
						// 结合实发数量控制最大领取量
						if (xlCount > pageGoodscount) {
							xlCount = pageGoodscount;
							pageGoodscount = 0F;
						} else {
							pageGoodscount -= xlCount;
						}
						Float oldHascount = son.getHascount();
						// 占用缺件计算
						// Float maxKlNumber =
						// Util.Floatdelete(son.getTjNumber(),Util.Floatdelete(son.getKlNumber(),son.getHascount()));
						// if (xlCount > maxKlNumber) {
						// xlCount = maxKlNumber;
						// }
						Float bizhong = null;
						if (useyl) {
							bizhong = (Float) totalDao
									.getObjectByCondition(
											"select bili from YuanclAndWaigj where markId =? and (banbenStatus is null or banbenStatus!='历史') ",
											son.getMarkId());
						}
						String banbenSql = null;
						if (son.getBanBenNumber() == null
								|| son.getBanBenNumber().length() == 0) {
							banbenSql = " and (banBenNumber is null or banBenNumber ='')";
						} else {
							banbenSql = " and banBenNumber ='"
									+ son.getBanBenNumber() + "'";
						}
						Goods googsTemp = (Goods) totalDao
								.getObjectByCondition(
										"from Goods where goodsMarkId=? and goodsCurQuantity>0 and  goodsZhishu>0 and kgliao=?"
												+ banbenSql, son.getMarkId(),
										son.getKgliao());
						for (Goods old : oldList) {
							if (old.getGoodsMarkId().equalsIgnoreCase(
									son.getMarkId())) {
								for (Goods goods : goodsList) {
									// if (son.getMarkId().equals("1.01.20199"))
									// {
									// System.out.println("1.01.20199");
									// }
									goods.setGoodsClass(old.getGoodsClass());
									Float outzhishu = null;
									Float outCount = null;
									if (xlCount > 0
											&& goods != null
											&& goods.getGoodsId() != null
											&& goods.getGoodsId().equals(
													old.getGoodsId())
											&& (goods.getGoodsCurQuantity() > 0 || (goods
													.getFlushCount2() != null && goods
													.getFlushCount2() > 0))) {
										Float thisoutCount = null;
										if ("余料库".equals(old.getGoodsClass())) {
											xlCount = LQYuLiao(user,
													lingliaoren, ylGoodsList,
													son, xlCount, bizhong,
													googsTemp, old, goods);
										} else {// 外购件库
											if (bizhong != null
													&& bizhong > 0
													&& goods.getFlushCount3() == null) {
												updateDan(goods);// 修正单张重量 精度为5
											}
											if (bizhong != null
													&& bizhong > 0
													&& goods.getFlushCount2() != null
													&& goods.getFlushCount2() > 0) {
												Float lout = null;
												if (goods.getFlushCount2() >= xlCount) {
													lout = xlCount;
													goods
															.setFlushCount2(Util
																	.Floatdelete(
																			goods
																					.getFlushCount2(),
																			xlCount));
													Float hascount = Util
															.Floatdelete(
																	son
																			.getHascount(),
																	xlCount);
													if (hascount < 0) {
														hascount = 0f;
													}
													son.setHascount(hascount);
													xlCount = 0f;
												} else {
													Float hascount = Util
															.Floatdelete(
																	son
																			.getHascount(),
																	goods
																			.getFlushCount2());
													if (hascount < 0) {
														hascount = 0f;
													}
													son.setHascount(hascount);
													lout = goods
															.getFlushCount2();
													goods.setFlushCount(0f);
													xlCount = Util
															.Floatdelete(
																	xlCount,
																	goods
																			.getFlushCount2());
												}
												if (lout > 0) {
													Float hascount = Util
															.Floatdelete(
																	son
																			.getHascount(),
																	lout);
													if (hascount < 0) {
														hascount = 0f;
													}
													son.setHascount(hascount);
													String lingliaoDetail = son
															.getLingliaoDetail();
													if (lingliaoDetail == null
															|| lingliaoDetail
																	.length() == 0) {
														lingliaoDetail = old
																.getGoodsLotId()
																+ ":" + lout;
													} else {
														lingliaoDetail += ","
																+ old
																		.getGoodsLotId()
																+ ":" + lout;
													}
													if ((son.getUnit().equalsIgnoreCase("kg")
															||son.getUnit().equalsIgnoreCase("g"))&&son.getHascount()
															/ (son.getQuanzi2() / son
																	.getQuanzi1()) < 0.09) {
														son.setHascount(0f);
													}
													if (son.getHascount() == 0) {
														son.setStatus("完成");
													}
													if (son.getKlNumber() == null) {
														son
																.setKlNumber(son
																		.getFilnalCount());
													}
													float sonminNumber = (son
															.getKlNumber() - son
															.getHascount())
															* son.getQuanzi1()
															/ son.getQuanzi2();
													if (sonminNumber % 1 > 0.9) {
														sonminNumber = (float) Math
																.ceil(sonminNumber);
													} else {
														sonminNumber = (float) Math
																.floor(sonminNumber);
													}
													son
															.setMinNumber(sonminNumber);
													son
															.setTjNumber(son
																	.getKlNumber()
																	- son
																			.getHascount());
													son
															.setLingliaoDetail(lingliaoDetail);
													// 出库记录
													addSell(user, lingliaoren,
															son, old, lout,
															time);
													if (xlCount == 0
															|| son
																	.getHascount() == 0) {
														totalDao.update(son);
														break;
													}
												}
											}
											if (xlCount > old
													.getGoodsCurQuantity()) {// 这条数据不足以支持
												Float hascount = Util
														.Floatdelete(
																son
																		.getHascount(),
																old
																		.getGoodsCurQuantity());
												if (hascount < 0) {
													hascount = 0f;
												}
												if (hascount < 1
														&& (hascount
																* son
																		.getQuanzi1() / son
																.getQuanzi2()) < 0.005) {
													hascount = 0f;
												}
												son.setHascount(hascount);
												thisoutCount = old
														.getGoodsCurQuantity();
												addzzCount += old
														.getGoodsCurQuantity();
												outCount = old
														.getGoodsCurQuantity();
												xlCount = Util
														.Floatdelete(
																xlCount,
																old
																		.getGoodsCurQuantity());
												old.setGoodsCurQuantity(0f);
												goods.setGoodsCurQuantity(0f);
												outzhishu = goods
														.getGoodsZhishu();
												old.setGoodsZhishu(0f);
											} else {
												Float hascount = Util
														.Floatdelete(son
																.getHascount(),
																xlCount);
												if (hascount < 0) {
													hascount = 0f;
												}
												if (hascount < 1
														&& (hascount
																* son
																		.getQuanzi1() / son
																.getQuanzi2()) < 0.005) {
													hascount = 0f;
												}
												son.setHascount(hascount);
												if (bizhong != null
														&& bizhong > 0) {
													outCount = xlCount;
													Float lsCount = (float) Math
															.ceil(xlCount
																	/ goods
																			.getFlushCount3());// 换算张数
													Float count2 = Util
															.Floatmul(
																	lsCount,
																	goods
																			.getFlushCount3());
													if (old
															.getGoodsCurQuantity() > count2) {
														if (old
																.getGoodsZhishu() > lsCount) {// 张数足够
															outzhishu = lsCount;
															old
																	.setGoodsCurQuantity(Util
																			.Floatdelete(
																					old
																							.getGoodsCurQuantity(),
																					count2));
															old
																	.setGoodsZhishu(Util
																			.Floatdelete(
																					old
																							.getGoodsZhishu(),
																					lsCount));
														} else {
															outzhishu = old
																	.getGoodsZhishu();
															old
																	.setGoodsCurQuantity(0f);
															old
																	.setGoodsZhishu(0f);
														}
														goods
																.setGoodsCurQuantity(Util
																		.Floatdelete(
																				goods
																						.getGoodsCurQuantity(),
																				count2));
														if (count2 > xlCount) {
															Float ycount = Util
																	.Floatdelete(
																			count2,
																			xlCount);
															if (goods
																	.getFlushCount2() == null) {
																goods
																		.setFlushCount2(ycount);
															} else {
																goods
																		.setFlushCount2(Util
																				.Floatadd(
																						goods
																								.getFlushCount2(),
																						ycount));
															}
														}
													} else {
														outzhishu = old
																.getGoodsZhishu();
														old
																.setGoodsCurQuantity(0f);
														old.setGoodsZhishu(0f);
														if (old
																.getGoodsCurQuantity() > xlCount) {
															Float ycount = Util
																	.Floatdelete(
																			old
																					.getGoodsCurQuantity(),
																			xlCount);
															if (goods
																	.getFlushCount2() == null) {
																goods
																		.setFlushCount2(ycount);
															} else {
																goods
																		.setFlushCount2(Util
																				.Floatadd(
																						goods
																								.getFlushCount2(),
																						ycount));
															}
														}

													}

												} else {
													thisoutCount = xlCount;
													addzzCount += xlCount;
													outCount = xlCount;
													old
															.setGoodsCurQuantity(old
																	.getGoodsCurQuantity()
																	- xlCount);
													goods
															.setGoodsCurQuantity(goods
																	.getGoodsCurQuantity()
																	- xlCount);
													xlCount = 0f;
												}

											}
											if (old.getGoodsCurQuantity() < 0) {
												runtimeEx(old);
											}
											if (old.getGoodsZhishu() != null
													&& old.getGoodsZhishu() < 0) {
												runtimeExZhuan(old);
											}
											totalDao.update(old);
											if (outCount > 0) {
												String lingliaoDetail = son
														.getLingliaoDetail();
												if (lingliaoDetail == null
														|| lingliaoDetail
																.length() == 0) {
													lingliaoDetail = old
															.getGoodsLotId()
															+ ":" + outCount;
												} else {
													lingliaoDetail += ","
															+ old
																	.getGoodsLotId()
															+ ":" + outCount;
												}
												if (son.getHascount()
														/ (son.getQuanzi2() / son
																.getQuanzi1()) < 0.09) {
													son.setHascount(0f);
												}
												if (son.getHascount() == 0) {
													son.setStatus("完成");
												}
												if (son.getKlNumber() == null) {
													son.setKlNumber(son
															.getFilnalCount());
												}
												float sonminNumber = (son
														.getKlNumber() - son
														.getHascount())
														* son.getQuanzi1()
														/ son.getQuanzi2();
												if (sonminNumber % 1 > 0.9) {
													sonminNumber = (float) Math
															.ceil(sonminNumber);
												} else {
													sonminNumber = (float) Math
															.floor(sonminNumber);
												}
												son.setMinNumber(sonminNumber);
												son.setTjNumber(son
														.getKlNumber()
														- son.getHascount());
												son
														.setLingliaoDetail(lingliaoDetail);
												// 自制件下层出库记录
												addSellWai1(user, lingliaoren,
														son, old, outzhishu,
														outCount, time);
											}
										}
									}
								}
								if (xlCount == 0) {
									totalDao.update(son);
									break;
								}
							}
							if (xlCount == 0) {
								totalDao.update(son);
								break;
							}
						}
						if (procardbl_wgj != null) {
							Float blylcount = procardbl_wgj.getYlCount()
									+ (oldHascount - son.getHascount());
							if (blylcount > procardbl_wgj.getPcCount()) {
								blylcount = procardbl_wgj.getPcCount();
							}
							procardbl_wgj.setYlCount(blylcount);
							procardbl_wgj.setRlingliaoTime(Util.getDateTime());
							totalDao.update(procardbl_wgj);
						}
						totalDao.update(son);
						if (addzzCount > 0) {
							// 添加外购件在制品
							zaizhiInput(son, user, addzzCount, "在制品入库");
						}
					}
					totalDao.update(son);
				}
			}
			// 最后一道工序数量判断
			float lasttotalCount = procard.getFilnalCount();// 最后一道工序的可领数量
			float beforettalCount = procard.getFilnalCount();// 除最后一道工序的可领数量
			// if(procard.getLingliaoType()!=null&&
			// procard.getLingliaoType().equals("part")){
			// Float xcMinNumber = (Float)
			// totalDao.getObjectByCondition("select max(minNumber) from Procard where fatherId=?"
			// +
			// " and (procardStyle='自制' or (procardStyle='外购' and needProcess='yes' ) and (sbStatus is null or sbStatus !='删除'))",
			// procard.getId());
			// if(xcMinNumber!=null){
			// beforettalCount=xcMinNumber;
			// }
			// }else{
			// Float xcMinNumber = (Float)
			// totalDao.getObjectByCondition("select min(minNumber) from Procard where fatherId=?"
			// +
			// " and (procardStyle='自制' or (procardStyle='外购' and needProcess='yes' ) and (sbStatus is null or sbStatus !='删除'))",
			// procard.getId());
			// if(xcMinNumber!=null){
			// beforettalCount=xcMinNumber;
			// }
			// }
			// 已配套套数量
			if (procard.getKlNumber() == null) {
				procard.setKlNumber(procard.getFilnalCount());
			}
			if (procard.getHascount() == null) {
				procard.setHascount(procard.getKlNumber());
			}
			if(procard.getId().equals(713512)){
				System.out.println(procard.getId());
			}
			Float peitaoCount = procard.getKlNumber() - procard.getHascount();
			Float scpeitao = null;// 生产配套数量
			Float xcMinhasCount = null;
			xcMinhasCount = procard.getFilnalCount();
			boolean hasll = false;
			List<Procard> peitaoSonList = null;
			Float minNumber = 0f;
			if (procard.getProcardStyle().equals("外购")) {
				peitaoSonList = wgprocardSonList;
				minNumber = procard.getFilnalCount();
			} else {
				peitaoSonList = totalDao
						.query(
								"from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') and procardStyle='外购'"
										+ " and (needProcess is null or needProcess !='yes') and (lingliaostatus is null or lingliaostatus!='否')",
								procard.getId());
				if(procard.getProcardStyle().equals("总成")){
					minNumber = (Float) totalDao
					.getObjectByCondition(
							"select min(minNumber) from Procard where procard.id=? and (oldProcardId is null or oldProcardId=0) and (sbStatus is null or sbStatus !='删除') "
							+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
							procard.getId());
				}else{
					minNumber = (Float) totalDao
					.getObjectByCondition(
							"select min(minNumber) from Procard where procard.id=?  and (sbStatus is null or sbStatus !='删除') "
							+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
							procard.getId());
				}
				if (minNumber == null) {
					Float sccount = (Float) totalDao
							.getObjectByCondition(
									"select count(*) from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') "
											+ "and (procardStyle ='自制' or (procardStyle ='外购' and needProcess ='yes'))",
									procard.getId());
					if (sccount == null || sccount == 0) {
						minNumber = procard.getFilnalCount();
					} else {
						minNumber = 0f;
					}
				}
				if (lasttotalCount > minNumber) {
					lasttotalCount = minNumber;
				}
			}
			Map<Integer, Float> processxz = new HashMap<Integer, Float>();// 工序数量限制
			for (Procard son : peitaoSonList) {
				List<Integer> processNoList = totalDao
						.query(
								"select distinct(processNO) from ProcessInfor where procard.id=? and (dataStatus is null or dataStatus!='删除') and  processName in"
										+ "( select processName from ProcessAndWgProcardTem where procardMarkId=? and wgprocardMardkId=?)",
								procard.getId(), procard.getMarkId(), son
										.getMarkId());
				Float gxCount = 0f;
				if (son.getHascount() == null) {// 被包工包料外委出去了
					scpeitao = 0f;
					lasttotalCount = 0f;
					xcMinhasCount = 0f;
				} else {
					// 生产已领数量
					Float scylCount = son.getKlNumber() - son.getHascount();
					// - wwylCount;
					if (scylCount > 0) {
						hasll = true;
					}
					if (son.getNeedProcess() == null
							|| !son.getNeedProcess().equals("yes")) {
						gxCount = scylCount * son.getQuanzi1()
								/ son.getQuanzi2();
						if(son.getHascount()==0){
							gxCount = (float) Math.floor(gxCount) + 1f;
						}
						if (scpeitao == null) {
							scpeitao = gxCount;
						} else if (scpeitao > gxCount) {
							scpeitao = gxCount;
						}
						if ((scpeitao - Math.floor(scpeitao)) > 0.95) {
							scpeitao = (float) Math.floor(scpeitao) + 1f;
						}
					} else {
						gxCount = scylCount;
						if (scpeitao == null) {
							scpeitao = scylCount;
						} else if (scpeitao > scylCount) {
							scpeitao = scylCount;
						}
						if ((scpeitao - Math.floor(scpeitao)) > 0.95) {
							scpeitao = (float) Math.floor(scpeitao) + 1f;
						}
					}
					float totalHasCount2 = (son.getKlNumber() - son
							.getHascount())
							* son.getQuanzi1() / son.getQuanzi2();
					if (son.getHascount() == 0) {
						totalHasCount2 = procard.getFilnalCount();
					}
					if (son.getKlNumber() >= son.getFilnalCount()
							&& son.getHascount() == 0) {// 小数位数问题
						totalHasCount2 = procard.getFilnalCount();
					}
					if (totalHasCount2 < 0) {
						totalHasCount2 = 0f;
					}
					if (totalHasCount2 < lasttotalCount) {
						lasttotalCount = totalHasCount2;
					}
					if (procard.getLingliaoType() == null
							|| !procard.getLingliaoType().equals("part")) {// 配齐方式
						if (totalHasCount2 < beforettalCount) {
							beforettalCount = totalHasCount2;
						}
					} else {
						if (totalHasCount2 > beforettalCount) {
							beforettalCount = totalHasCount2;
						}
					}
					if ((son.getHascount() * son.getQuanzi1() / son
							.getQuanzi2()) < xcMinhasCount) {
						xcMinhasCount = son.getHascount() * son.getQuanzi1()
								/ son.getQuanzi2();

					}
					if ((xcMinhasCount - Math.floor(xcMinhasCount)) > 0.95) {
						xcMinhasCount = (float) Math.floor(xcMinhasCount) + 1f;
					}
				}
				if (processNoList != null && processNoList.size() > 0) {
					for (Integer pno : processNoList) {
						Float gxxz = processxz.get(pno);
						if (gxxz == null || gxxz > gxCount) {
							gxxz = gxCount;
						}
						if(gxxz<0){
							System.out.println(son.getMarkId()+ " ======================"+son.getId());
						}
						processxz.put(pno, gxxz);
					}
				}

			}
			if (hasll) {
				if (procard.getStatus().equals("已发卡")) {
					procard.setStatus("已发料");
				}
			}
			if (scpeitao == null) {
				scpeitao = 0f;
			}
			if (scpeitao % 1 < 0.95) {
				scpeitao = (float) Math.floor(scpeitao);
			} else {
				scpeitao = (float) Math.ceil(scpeitao);
			}
			Float hascount = procard.getKlNumber() - scpeitao;
			if (hascount < 0) {
				hascount = 0f;
			}
			procard.setHascount(hascount);
			if (procard.getHascount() == 0
					&& (procard.getKlNumber().equals(procard.getFilnalCount()))) {
				ProcardBl bl = (ProcardBl) totalDao
						.getObjectByCondition(
								"from ProcardBl where procardId=? and status!='已领完' and rootId=?",
								procard.getId(), procardBlRootId);
				if (bl != null) {
					bl.setStatus("已领完");
					bl.setYlCount(bl.getPcCount());
					bl.setRlingliaoTime(Util.getDateTime());
					totalDao.update(bl);
				}
			}
			String hql_update = "from ProcessInfor where procard.id=? and (dataStatus is null or dataStatus !='删除') order by processNO";
			List<ProcessInfor> sonProcessinfor = totalDao.query(hql_update,
					procard.getId());
			Integer maxProcessNo = -1;
			String upProcesstype = "";
			Integer upprocessno = -1;
			String upNeedSave = null;
			Float zzplqCount=0f;
			boolean b = true;// 半成品转库之后工序限制可领数量，半成品转库或者领取之后解开限制数量
			if ((beforettalCount - Math.floor(beforettalCount)) > 0.95) {
				beforettalCount = (float) Math.floor(beforettalCount) + 1f;
			} else {
				beforettalCount = (float) Math.floor(beforettalCount);
			}
			if ((lasttotalCount - Math.floor(lasttotalCount)) > 0.95) {
				lasttotalCount = (float) Math.floor(lasttotalCount) + 1f;
			} else {
				lasttotalCount = (float) Math.floor(lasttotalCount);
			}
			Float beforettalCount2=beforettalCount;
			for (int i = 0,len=sonProcessinfor.size(); i < len; i++) {
				ProcessInfor processInfor = sonProcessinfor.get(i);
				Float selectWwCount = processInfor.getSelectWwCount() == null ? 0
						: processInfor.getSelectWwCount();// 已选外委数量
				Float applyWwCount = processInfor.getApplyWwCount() == null ? 0
						: processInfor.getApplyWwCount();// 外委申请中数量
				Float agreeWwCount = processInfor.getAgreeWwCount() == null ? 0
						: processInfor.getAgreeWwCount();// 外委同意数量
				Float wwbackCount = processInfor.getWwbackCount() == null ? 0
						: processInfor.getWwbackCount();// 外委回来数量
				// 总数量-外委没有回来数量==最大激活数量
				Float thismaxCount = procard.getFilnalCount()
						- (selectWwCount + applyWwCount + agreeWwCount - wwbackCount);
				if (beforettalCount > thismaxCount) {
					beforettalCount2 = thismaxCount;
				}
				if (b) {
					if ((upNeedSave != null && upNeedSave.equals("是"))
							&& (processInfor.getNeedSave() == null || !processInfor
									.getNeedSave().equals("是"))) {//获取转存出库数量
						zzplqCount = (Float) totalDao.getObjectByCondition("select sum(ckCount) from ProcardProductRelation where procardId=? " +
								"and goodsId in(select goodsId from Goods where goodsClass='半成品库' and goodsMarkId=? and processNo=? ) ",
								procard.getId(),procard.getMarkId(),upprocessno);
						b = false;
					}
					
					if (upProcesstype.equals("外委")
							&& processInfor.getProductStyle() != null
							&& processInfor.getProductStyle().equals("自制")) {//获取外协出库数量
						zzplqCount = (Float) totalDao.getObjectByCondition("select sum(ckCount) from ProcardProductRelation where procardId=? " +
								"and goodsId in(select goodsId from Goods where goodsClass='外协库' and goodsMarkId=? and processNo=? ) ",
								procard.getId(),procard.getMarkId(),upprocessno);
						b = false;
					}
					if (upProcesstype.equals("外委")
							&& processInfor.getProductStyle() != null
							&& processInfor.getProductStyle().equals("外委")
							&& processInfor.getProcessStatus() != null
							&& processInfor.getProcessStatus().equals("no")) {//获取外协出库数量
						zzplqCount = (Float) totalDao.getObjectByCondition("select sum(ckCount) from ProcardProductRelation where procardId=? " +
								"and goodsId in(select goodsId from Goods where goodsClass='外协库' and goodsMarkId=? and processNo=? ) ",
								procard.getId(),procard.getMarkId(),upprocessno);
						b = false;
					}
				}
				upprocessno = processInfor.getProcessNO();
				upProcesstype = processInfor.getProductStyle();
				upNeedSave = processInfor.getNeedSave();
				if (i == (sonProcessinfor.size() - 1)) {
					// && ((pro.getKlNumber() - pro.getHascount()) >=
					// lasttotalCount)) {// 部分领料最后一道工序的可领数量为最小minNumber-
					maxProcessNo = processInfor.getProcessNO();// 最大工序号
					if (!b) {
						if (procard.getFilnalCount().equals(
								processInfor.getTotalCount())) {// 第一次领
							processInfor.setTotalCount(zzplqCount);
						}
					} else {
						processInfor.setTotalCount((float) Math
								.floor(lasttotalCount));
					}
				} else {
					if (!b) {
						if (procard.getFilnalCount().equals(
								processInfor.getTotalCount())) {// 第一次领
							processInfor.setTotalCount(zzplqCount);
						}
					} else {
						if (beforettalCount2 < processInfor.getApplyCount()) {
							processInfor.setTotalCount(processInfor
									.getApplyCount());
						} else {
							processInfor.setTotalCount(beforettalCount2);
						}
					}
				}
				Float gxzx = processxz.get(processInfor.getProcessNO());
				if (gxzx != null) {
					if ((gxzx - Math.floor(gxzx)) > 0.95) {
						gxzx = (float) Math.floor(gxzx) + 1f;
					} else {
						gxzx = (float) Math.floor(gxzx);
					}
				}
				if (gxzx != null && processInfor.getTotalCount() > gxzx) {
					processInfor.setTotalCount(gxzx);
				}
				totalDao.update(processInfor);
			}
			totalDao.update(procard);
			// 计算现已生成在制品数量
			Float xyCount = (Float) totalDao
					.getObjectByCondition(
							"select sum(zyCount) from ProcardProductRelation where flagType='本批在制品' and procardId=?",
							procard.getId());
			if (xyCount == null) {
				xyCount = 0f;
			}
			Float addzaizhi = (float) Math.floor(procard.getKlNumber()
					- xcMinhasCount - xyCount);
			if (addzaizhi > 0) {
				zaizhiInput(procard, user, addzaizhi, "在制品入库");
			}

		}
		if (ylGoodsList != null && ylGoodsList.size() > 0) {// 余料出库生成余料数据
			// 获取余料批次
			String before = "yl_" + Util.getDateTime("yyyyMMdd");
			String maxnumber = (String) totalDao
					.getObjectByCondition("select max(goodsLotId) from Goods where goodsClass='余料' and goodsLotId like '"
							+ before + "%'");
			Integer maxint = 0;
			DecimalFormat df = new DecimalFormat("00000");
			if (maxnumber != null && !"".equals(maxnumber)) {
				// String number1 = paymentVoucher2.getNumber();
				String now_number = maxnumber.split(before)[1];
				maxint = Integer.parseInt(now_number);
			}
			int i = 0;
			for (Goods yl : ylGoodsList) {
				if (yl.getFlushCount2() > 0) {
					yl.setGoodsCurQuantity(0f);// 余料整体出库，然后待入库
					yl.setGoodsZhishu(0f);
					totalDao.update(yl);
					i++;
					String jishu = df.format(maxint + i);
					String goodsLotId = before + jishu;
					// 余料出入库操作
					addYuliao(user, rootId, rootMarkId, yl, goodsLotId);
				}
			}
		}
		if (goodsList != null && goodsList.size() > 0) {// 非余料出库生成余料数据
			// 获取余料批次
			String before = "yl_" + Util.getDateTime("yyyyMMdd");
			String maxnumber = (String) totalDao
					.getObjectByCondition("select max(goodsLotId) from Goods where goodsClass='余料' and goodsLotId like '"
							+ before + "%'");
			Integer maxint = 0;
			DecimalFormat df = new DecimalFormat("00000");
			if (maxnumber != null && !"".equals(maxnumber)) {
				// String number1 = paymentVoucher2.getNumber();
				String now_number = maxnumber.split(before)[1];
				maxint = Integer.parseInt(now_number);
			}
			int i = 0;
			for (Goods yl : goodsList) {
				if (!"余料".equals(yl.getGoodsClass())
						&& yl.getFlushCount2() != null
						&& yl.getFlushCount2() > 0) {
					if (yl.getGoodsId() != null) {
						Goods oldyl = (Goods) totalDao.getObjectById(
								Goods.class, yl.getGoodsId());
						if (oldyl != null) {
							i++;
							// 余料出库记录
							addSellYl(user, lingliaoren, rootSelfCard,
									rootMarkId, ywMarkId, orderNumber, yl,
									oldyl);
							String jishu = df.format(maxint + i);
							String goodsLotId = before + jishu;
							// 添加余料库存
							Goods ylGoods = addYuliaoGoods(yl, oldyl,
									goodsLotId);
							// 余料产品中间表
							addYlzhong(rootId, ylGoods);
							// 余料入库记录
							addGoodsYl(user, rootMarkId, ywMarkId, orderNumber,
									yl, goodsLotId, ylGoods);
						}
					}
				}
			}
		}
		// 安全库存

		/**************************** 判断剩余库存量是否小与最小库存量 *********************************************************************************************/
		Set<String> keySet = aqck.keySet();
		if (keySet != null && keySet.size() > 0) {
			for (String key : keySet) {
				String value = aqck.get(key);
				addneedcg(key, value);
			}
		}
		return "true";
	}

	/**
	 * 修正单张重量 精度为5
	 * 
	 * @param goods
	 */
	private void updateDan(Goods goods) {
		try {
			// 记录修正单张重量
			goods.setFlushCount3(Util.Floatdiv(goods.getGoodsCurQuantity(),
					goods.getGoodsZhishu(), 5));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch
			// block
			e.printStackTrace();
		}
	}

	/**
	 * 领取余料
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param ylGoodsList
	 * @param son
	 * @param xlCount
	 * @param bizhong
	 * @param googsTemp
	 * @param old
	 * @param goods
	 * @return
	 */
	private Float LQYuLiao(Users user, Users lingliaoren,
			List<Goods> ylGoodsList, Procard son, Float xlCount, Float bizhong,
			Goods googsTemp, Goods old, Goods goods) {
		// 领取余料
		if (goods.getFlushCount() > 0 && goods.getGoodsCurQuantity() != null) {
			if (old.getGoodsCurQuantity() < (goods.getFlushCount() - 0.0005)) {
				throw new RuntimeException("对不起余料抵发数量不足,领料失败!");
			}
			if (!ylGoodsList.contains(old)) {
				// 标记余料待入库数量
				old.setFlushCount2(old.getGoodsCurQuantity()
						- goods.getFlushCount());
				xlCount -= goods.getFlushCount();
				// 余料待入库
				ylGoodsList.add(old);
				// 余料出库
				addSellYuliao(user, lingliaoren, son, old, goods);
			}
			Float thisAddZaizhi = 0f;
			if (son.getHascount() == null) {
				son.setHascount(son.getKlNumber());
			}
			if (son.getUnit().equals(old.getGoodsUnit())) {// 单位一致
				if (son.getHascount() >= goods.getFlushCount()) {// 余料不足支付
					son.setHascount(son.getHascount() - goods.getFlushCount());
					if ((son.getHascount() * son.getQuanzi1() / son
							.getQuanzi2()) < 0.01) {
						son.setHascount(0f);
					}
					thisAddZaizhi = goods.getFlushCount();
					goods.setFlushCount(0f);
				} else {// 余料足以支付
					goods.setFlushCount(goods.getFlushCount()
							- son.getHascount());
					thisAddZaizhi = son.getHascount();
					son.setHascount(0f);
				}
			} else {
				// 单零件重量（一般为原材料使用）
				if (bizhong == null) {
					if (googsTemp != null) {
						bizhong = googsTemp.getGoodsCurQuantity()
								/ googsTemp.getGoodsZhishu();
					}
				}
				int deleteCount = Math.round(goods.getFlushCount() / bizhong);
				if (son.getHascount() >= deleteCount) {
					son.setHascount(son.getHascount() - deleteCount);
					goods.setFlushCount(0f);
					thisAddZaizhi = deleteCount + 0f;
				} else {
					thisAddZaizhi = son.getHascount();
					deleteCount -= son.getHascount();
					son.setHascount(0f);
					goods.setFlushCount(goods.getFlushCount() - deleteCount
							* bizhong);
				}

			}
			if (son.getHascount() / (son.getQuanzi2() / son.getQuanzi1()) < 0.05) {
				son.setHascount(0f);
			}
			if (son.getHascount() == 0) {
				son.setStatus("完成");
			}

			if (son.getKlNumber() == null) {
				son.setKlNumber(son.getFilnalCount());
			}
			float sonminNumber = (son.getKlNumber() - son.getHascount())
					* son.getQuanzi1() / son.getQuanzi2();
			if (sonminNumber % 1 > 0.9) {
				sonminNumber = (float) Math.ceil(sonminNumber);
			} else {
				sonminNumber = (float) Math.floor(sonminNumber);
			}
			son.setMinNumber(sonminNumber);
			son.setTjNumber(son.getKlNumber() - son.getHascount());
			totalDao.update(son);
			String lingliaoDetail = son.getLingliaoDetail();
			if (lingliaoDetail == null || lingliaoDetail.length() == 0) {
				lingliaoDetail = old.getGoodsLotId() + "(余):"
						+ Util.FomartFloat(goods.getGoodsCurQuantity(), 4);
			} else {
				lingliaoDetail += "," + old.getGoodsLotId() + "(余):"
						+ Util.FomartFloat(goods.getGoodsCurQuantity(), 4);
			}
			son.setLingliaoDetail(lingliaoDetail);
			// 添加外购件在制品数量
			String hqlzaizhi = "from Goods where goodsMarkId='"
					+ old.getGoodsMarkId()
					+ "' and goodsLotId='"
					+ son.getSelfCard()
					+ "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus!='封存') ";
			List listzizhi = totalDao.find(hqlzaizhi);
			Integer rgoodsId = null;
			if (listzizhi != null && listzizhi.size() > 0) {
				Goods g1 = updatewaigouZai(thisAddZaizhi, listzizhi);
				rgoodsId = g1.getGoodsId();
			} else {
				Goods gg = addWaigouZaiGoods(son, old, thisAddZaizhi);
				rgoodsId = gg.getGoodsId();
			}
			// 添加零件与在制品关系表
			addPprGs(user, son, old, thisAddZaizhi, rgoodsId);
		}
		return xlCount;
	}

	/**
	 * 抛出运行时异常 转换数量 num=GoodsZhishu+GoodsStoreZHUnit
	 * 
	 * @param old
	 */
	private void runtimeExZhuan(Goods old) {
		throw new RuntimeException(old.getGoodsMarkId() + "的第"
				+ old.getGoodsLotId() + "批次的库存数量不足,此时转换数量为:"
				+ old.getGoodsZhishu() + old.getGoodsStoreZHUnit());
	}

	/**
	 * 抛出运行时异常 数量 num=GoodsCurQuantity+GoodsUnit
	 * 
	 * @param old
	 */
	private void runtimeEx(Goods old) {
		throw new RuntimeException(old.getGoodsMarkId() + "的第"
				+ old.getGoodsLotId() + "批次的库存数量不足,此时数量为:"
				+ old.getGoodsCurQuantity() + old.getGoodsUnit());
	}

	private Procard byProcardId(Integer pid) {
		Procard procard = (Procard) totalDao.getObjectById(Procard.class, pid);
		return procard;
	}

	/**
	 * 余料出库记录
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param rootSelfCard
	 * @param rootMarkId
	 * @param ywMarkId
	 * @param orderNumber
	 * @param yl
	 * @param oldyl
	 */
	private void addSellYl(Users user, Users lingliaoren, String rootSelfCard,
			String rootMarkId, String ywMarkId, String orderNumber, Goods yl,
			Goods oldyl) {
		Sell sell = new Sell();
		// sell.setSellArtsCard();
		sell.setSuodingdanhao(oldyl.getSuodingdanhao());
		sell.setSellSupplier(oldyl.getGoodsSupplier());
		sell.setBanBenNumber(oldyl.getBanBenNumber());
		sell.setSellFormat(oldyl.getGoodsFormat());
		sell.setSellLot(oldyl.getGoodsLotId());
		sell.setSellMarkId(oldyl.getGoodsMarkId());
		sell.setSellAdminName(user.getName());
		sell.setSellGoods(oldyl.getGoodsFullName());
		sell.setGoodsPrice(oldyl.getGoodsPrice());// 出库价格
		sell.setSellDate(Util.getDateTime("yyyy-MM-dd"));
		sell.setSellTime(Util.getDateTime());
		sell.setSellWarehouse(oldyl.getGoodsClass());
		sell.setGoodHouseName(oldyl.getGoodHouseName());// 区名
		sell.setKuwei(oldyl.getGoodsPosition());// 库位;
		sell.setWgType(oldyl.getWgType());
		sell.setKgliao(oldyl.getKgliao());
		sell.setSellCharger(lingliaoren.getName());
		sell.setSellchardept(lingliaoren.getDept());
		sell.setSellcharId(lingliaoren.getId());
		sell.setYwmarkId(ywMarkId);// 业务件号
		sell.setSellProMarkId(rootMarkId);// 总成件号
		sell.setRootSelfCard(rootSelfCard);// 总成批次
		sell.setOrderNum(orderNumber);// 内部订单号
		sell.setStyle("生产刷卡做余料(多)");
		sell.setPrintStatus("NO");
		sell.setSellZhishu(0f);
		sell.setSellCount(Util.FomartFloat(yl.getFlushCount2(), 4));
		sell.setSellUnit(oldyl.getGoodsUnit());
		sell.setGoodsStoreZHUnit(oldyl.getGoodsStoreZHUnit());
		sell.setGoodsId(oldyl.getGoodsId());
		totalDao.save(sell);
	}

	/**
	 * 添加余料出库
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param son
	 * @param old
	 * @param goods
	 */
	private void addSellYuliao(Users user, Users lingliaoren, Procard son,
			Goods old, Goods goods) {
		Sell sellYl = new Sell();
		sellYl.setSuodingdanhao(old.getSuodingdanhao());
		sellYl.setSellArtsCard(son.getSelfCard());
		sellYl.setSellSupplier(old.getGoodsSupplier());
		sellYl.setSellFormat(old.getGoodsFormat());
		sellYl.setSellLot(old.getGoodsLotId());
		sellYl.setGoodsId(old.getGoodsId());
		sellYl.setSellMarkId(old.getGoodsMarkId());
		sellYl.setGoodsPrice(old.getGoodsPrice());// 出库价格
		sellYl.setSellAdminName(user.getName());
		sellYl.setSellGoods(old.getGoodsFullName());
		sellYl.setSellDate(Util.getDateTime("yyyy-MM-dd"));
		sellYl.setSellTime(Util.getDateTime());
		sellYl.setSellWarehouse(old.getGoodsClass());
		sellYl.setSellUnit(old.getGoodsUnit());
		sellYl.setSellCount(Util.FomartFloat(goods.getGoodsCurQuantity(), 4));
		sellYl.setKgliao(old.getKgliao());
		sellYl.setSellCharger(user.getName());
		sellYl.setSellchardept(lingliaoren.getDept());
		sellYl.setSellcharId(lingliaoren.getId());
		sellYl.setBanBenNumber(old.getBanBenNumber());
		sellYl.setStyle("生产刷卡(多)");
		sellYl.setYwmarkId(son.getYwMarkId());// 业务件号
		sellYl.setSellProMarkId(son.getRootMarkId());// 总成件号
		sellYl.setRootSelfCard(son.getRootSelfCard());// 总成批次
		sellYl.setOrderNum(son.getOrderNumber());// 内部订单号
		sellYl.setGoodsId(old.getGoodsId());
		sellYl.setProcardId(son.getId());// procardId
		totalDao.save(sellYl);
	}

	/**
	 * 生产刷卡(多)出库历史记录
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param son
	 * @param old
	 * @param lout
	 */
	private void addSell(Users user, Users lingliaoren, Procard son, Goods old,
			Float lout, String time) {
		Sell sell = new Sell();
		sell.setSuodingdanhao(old.getSuodingdanhao());
		sell.setSellArtsCard(son.getSelfCard());
		sell.setSellSupplier(old.getGoodsSupplier());
		sell.setSellFormat(old.getGoodsFormat());
		sell.setSellLot(old.getGoodsLotId());
		sell.setSellMarkId(old.getGoodsMarkId());
		sell.setSellAdminName(user.getName());
		sell.setSellGoods(old.getGoodsFullName());
		sell.setSellDate(Util.getDateTime("yyyy-MM-dd"));
		sell.setSellTime(time);
		sell.setGoodsPrice(old.getGoodsPrice());// 出库价格
		sell.setSellWarehouse(old.getGoodsClass());
		sell.setGoodHouseName(old.getGoodHouseName());// 区名
		sell.setKuwei(old.getGoodsPosition());// 库位;
		sell.setWgType(old.getWgType());
		sell.setKgliao(old.getKgliao());
		sell.setSellCharger(lingliaoren.getName());
		sell.setSellchardept(lingliaoren.getDept());
		sell.setSellcharId(lingliaoren.getId());
		sell.setYwmarkId(son.getYwMarkId());// 业务件号
		sell.setSellProMarkId(son.getRootMarkId());// 总成件号
		sell.setRootSelfCard(son.getRootSelfCard());// 总成批次
		sell.setOrderNum(son.getOrderNumber());// 内部订单号
		sell.setStyle("生产刷卡(多)");
		sell.setPrintStatus("NO");
		sell.setBanBenNumber(old.getBanBenNumber());
		sell.setSellZhishu(0f);
		sell.setSellCount(lout);
		sell.setSellUnit(old.getGoodsUnit());
		sell.setGoodsStoreZHUnit(old.getGoodsStoreZHUnit());
		sell.setGoodsId(old.getGoodsId());
		sell.setProcardId(son.getId());
		// String printNumber = updatMaxSellPrintNumber(sell, time);
		// sell.setPrintNumber(printNumber);
		if (totalDao.save(sell)) {
			if (sell.getGoodsPrice() != null && sell.getGoodsPrice() > 0) {
				corePayableServer.goodsSell(sell);
			} else {
				DoorBangDingServerImpl.caeLogInfor(new StringBuffer("库存id:"
						+ old.getGoodsId() + "GoodsPrice为空:"
						+ old.getGoodsPrice() + ",生成出库凭证失败!"), sell.getSellId()
						+ "", "addSell", "", sell.getStyle(), "外购件领料", null,
						null);
			}
		}
	}

	/**
	 * 添加外购件出库记录
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param procard
	 * @param old
	 * @param outzhishu
	 * @param outCount
	 */
	private void addSellWai(Users user, Users lingliaoren, Procard procard,
			Goods old, Float outzhishu, Float outCount, String time) {
		Sell sell = new Sell();
		sell.setSellArtsCard(procard.getSelfCard());
		sell.setSuodingdanhao(old.getSuodingdanhao());
		sell.setSellSupplier(old.getGoodsSupplier());
		sell.setSellFormat(old.getGoodsFormat());
		sell.setSellLot(old.getGoodsLotId());
		sell.setSellMarkId(old.getGoodsMarkId());
		sell.setSellAdminName(user.getName());
		sell.setSellGoods(old.getGoodsFullName());
		sell.setBanBenNumber(old.getBanBenNumber());
		sell.setGoodsPrice(old.getGoodsPrice());// 出库价格
		sell.setSellPrice(old.getGoodsBuyPrice());// 批次 含税单价
		sell.setSellbhsPrice(old.getGoodsBuyBhsPrice());// 批次单价 不含税
		sell.setTaxprice(old.getTaxprice());// 税率
		sell.setSellDate(Util.getDateTime("yyyy-MM-dd"));
		sell.setSellTime(time);
		sell.setSellWarehouse(old.getGoodsClass());
		sell.setGoodHouseName(old.getGoodHouseName());// 区名
		sell.setKuwei(old.getGoodsPosition());// 库位;
		sell.setWgType(old.getWgType());
		sell.setKgliao(old.getKgliao());
		sell.setSellCharger(lingliaoren.getName());
		sell.setSellchardept(lingliaoren.getDept());
		sell.setSellcharId(lingliaoren.getId());
		sell.setYwmarkId(procard.getYwMarkId());// 业务件号
		sell.setSellProMarkId(procard.getRootMarkId());// 总成件号
		sell.setRootSelfCard(procard.getRootSelfCard());// 总成批次
		sell.setOrderNum(procard.getOrderNumber());// 内部订单号
		sell.setStyle("生产刷卡(多)");
		sell.setPrintStatus("NO");
		sell.setSellZhishu(outzhishu);
		sell.setSellCount(outCount);
		sell.setSellUnit(old.getGoodsUnit());
		sell.setGoodsStoreZHUnit(old.getGoodsStoreZHUnit());
		sell.setGoodsId(old.getGoodsId());
		sell.setProcardId(procard.getId());
		// 生成打印单号wxf
		// String printNumber = updatMaxSellPrintNumber(sell, time);
		// sell.setPrintNumber(printNumber);
		if (totalDao.save(sell)) {
			if (sell.getGoodsPrice() != null && sell.getGoodsPrice() > 0) {
				corePayableServer.goodsSell(sell);
			} else {
				DoorBangDingServerImpl.caeLogInfor(new StringBuffer("库存id:"
						+ old.getGoodsId() + "GoodsPrice为空:"
						+ old.getGoodsPrice() + ",生成出库凭证失败!"), sell.getSellId()
						+ "", "addSellWai", "", sell.getStyle(), "外购件领料", null,
						null);
			}
		}
	}

	/**
	 * 添加自制件下层外购出库记录
	 * 
	 * @param user
	 * @param lingliaoren
	 * @param son
	 * @param old
	 * @param outzhishu
	 * @param outCount
	 */
	private void addSellWai1(Users user, Users lingliaoren, Procard son,
			Goods old, Float outzhishu, Float outCount, String time) {
		Sell sell = new Sell();
		sell.setSuodingdanhao(old.getSuodingdanhao());
		sell.setBanBenNumber(old.getBanBenNumber());
		sell.setSellArtsCard(son.getSelfCard());
		sell.setSellSupplier(old.getGoodsSupplier());
		sell.setSellFormat(old.getGoodsFormat());
		sell.setSellLot(old.getGoodsLotId());
		sell.setSellMarkId(old.getGoodsMarkId());
		sell.setSellAdminName(user.getName());
		sell.setSellGoods(old.getGoodsFullName());
		sell.setGoodsPrice(old.getGoodsPrice());// 出库价格
		sell.setSellPrice(old.getGoodsBuyPrice());// 批次 含税单价
		sell.setSellbhsPrice(old.getGoodsBuyBhsPrice());// 批次单价 不含税
		sell.setTaxprice(old.getTaxprice());// 税率
		sell.setSellDate(Util.getDateTime("yyyy-MM-dd"));
		sell.setSellTime(time);
		sell.setSellWarehouse(old.getGoodsClass());
		sell.setGoodHouseName(old.getGoodHouseName());// 区名
		sell.setKuwei(old.getGoodsPosition());// 库位;
		sell.setWgType(old.getWgType());
		sell.setKgliao(old.getKgliao());
		sell.setSellCharger(lingliaoren.getName());
		sell.setSellchardept(lingliaoren.getDept());
		sell.setSellcharId(lingliaoren.getId());
		sell.setYwmarkId(son.getYwMarkId());// 业务件号
		sell.setSellProMarkId(son.getRootMarkId());// 总成件号
		sell.setRootSelfCard(son.getRootSelfCard());// 总成批次
		sell.setOrderNum(son.getOrderNumber());// 内部订单号
		sell.setStyle("生产刷卡(多)");
		sell.setPrintStatus("NO");
		sell.setSellZhishu(outzhishu);
		sell.setSellCount(outCount);
		sell.setSellUnit(old.getGoodsUnit());
		sell.setGoodsStoreZHUnit(old.getGoodsStoreZHUnit());
		sell.setGoodsId(old.getGoodsId());
		sell.setProcardId(son.getId());
		// String printNumber = updatMaxSellPrintNumber(sell, time);
		// sell.setPrintNumber(printNumber);
		if (totalDao.save(sell)) {
			if (sell.getGoodsPrice() != null && sell.getGoodsPrice() > 0) {
				corePayableServer.goodsSell(sell);
			} else {
				DoorBangDingServerImpl.caeLogInfor(new StringBuffer("库存id:"
						+ old.getGoodsId() + "GoodsPrice为空:"
						+ old.getGoodsPrice() + ",生成出库凭证失败!"), sell.getSellId()
						+ "", "addSellWai1", "", sell.getStyle(), "外购件领料",
						null, null);
			}
		}
	}

	/**
	 * 添加余料库存
	 * 
	 * @param yl
	 * @param oldyl
	 * @param goodsLotId
	 * @return
	 */
	private Goods addYuliaoGoods(Goods yl, Goods oldyl, String goodsLotId) {
		Goods ylGoods = new Goods();
		ylGoods.setGoodsClass("余料库");
		ylGoods.setGoodsBeginQuantity(Util.FomartFloat(yl.getFlushCount2(), 4));
		ylGoods.setGoodsCurQuantity(Util.FomartFloat(yl.getFlushCount2(), 4));
		ylGoods.setGoodsZhishu(1f);
		ylGoods.setGoodHouseName(oldyl.getGoodHouseName());
		// ylGoods.setYlMarkId(son.getMarkId());
		// ylGoods.setYlSelfCard(son.getSelfCard());
		ylGoods.setGoodsMarkId(oldyl.getGoodsMarkId());
		ylGoods.setGoodsFormat(oldyl.getGoodsFormat());
		ylGoods.setGoodsUnit(oldyl.getGoodsUnit());
		ylGoods.setGoodsChangeTime(Util.getDateTime("yyyy-MM-dd"));
		ylGoods.setYllock(oldyl.getYllock());
		ylGoods.setKgliao(oldyl.getKgliao());
		// ylGoods.set
		ylGoods.setWgType(oldyl.getWgType());
		ylGoods.setGoodsLotId(goodsLotId);
		totalDao.save(ylGoods);
		return ylGoods;
	}

	/**
	 * 余料产品中间表
	 * 
	 * @param rootId
	 * @param ylGoods
	 */
	private void addYlzhong(Integer rootId, Goods ylGoods) {
		ProcardProductRelation ppr = new ProcardProductRelation();
		ppr.setAddTime(Util.getDateTime());
		ppr.setProcardId(rootId);
		ppr.setGoodsId(ylGoods.getGoodsId());
		ppr.setZyCount(Util.FomartFloat(ylGoods.getGoodsCurQuantity(), 4));
		ppr.setFlagType("余料(BOM整体领取)");
		totalDao.save(ppr);
	}

	/**
	 * 余料入库历史记录
	 * 
	 * @param user
	 * @param rootMarkId
	 * @param ywMarkId
	 * @param orderNumber
	 * @param yl
	 * @param goodsLotId
	 * @param ylGoods
	 */
	private void addGoodsYl(Users user, String rootMarkId, String ywMarkId,
			String orderNumber, Goods yl, String goodsLotId, Goods ylGoods) {
		GoodsStore ylgs = new GoodsStore();
		ylgs.setYwmarkId(ywMarkId);
		ylgs.setNeiorderId(orderNumber);
		ylgs.setGoodsStoreMarkId(ylGoods.getGoodsMarkId());// 件号
		ylgs.setGoodsStoreFormat(ylGoods.getGoodsFormat());// 规格
		ylgs.setGoodsStoreGoodsName(yl.getGoodsFullName());// 名称
		ylgs.setGoodsStoreLot(goodsLotId);// 批次
		ylgs.setGoodsStoreCount(Util.FomartFloat(yl.getFlushCount2(), 4));// 数量
		ylgs.setPrintStatus("YES");
		ylgs.setWgType(yl.getWgType());
		ylgs.setGoodsStoreProMarkId(rootMarkId);// 总成件号
		ylgs.setGoodsStoreWarehouse("余料库");// 库别
		ylgs.setKgliao(ylGoods.getKgliao());
		ylgs.setGoodsStoreCharger(user.getName());// 经办人
		ylgs.setStyle("余料");// 入库类型
		ylgs.setGoodsStorePerson(user.getName());
		ylgs.setGoodsStoreDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		ylgs.setGoodsStoreUnit(yl.getGoodsUnit());// 单位
		totalDao.save(ylgs);
	}

	/**
	 * 余料出入库操作
	 * 
	 * @param user
	 * @param rootId
	 * @param rootMarkId
	 * @param yl
	 * @param goodsLotId
	 */
	private void addYuliao(Users user, Integer rootId, String rootMarkId,
			Goods yl, String goodsLotId) {
		Goods ylGoods = addYuliaoGoods(yl, yl, goodsLotId);
		addYlzhong(rootId, ylGoods);
		// 余料入库记录
		GoodsStore ylgs = new GoodsStore();
		ylgs.setGoodsStoreMarkId(ylGoods.getGoodsMarkId());// 件号
		ylgs.setGoodsStoreFormat(ylGoods.getGoodsFormat());// 规格
		ylgs.setGoodsStoreGoodsName(yl.getGoodsFullName());// 名称
		ylgs.setGoodsStoreLot(goodsLotId);// 批次
		ylgs.setGoodsStoreCount(Util.FomartFloat(yl.getFlushCount2(), 4));// 数量
		ylgs.setPrintStatus("YES");
		ylgs.setWgType(yl.getWgType());
		ylgs.setGoodsStoreProMarkId(rootMarkId);// 总成件号
		ylgs.setGoodsStoreWarehouse("余料库");// 库别
		ylgs.setKgliao(ylGoods.getKgliao());
		ylgs.setGoodsStoreCharger(user.getName());// 经办人
		ylgs.setStyle("余料");// 入库类型
		ylgs.setGoodsStorePerson(user.getName());
		ylgs.setGoodsStoreDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		ylgs.setGoodsStoreUnit(yl.getGoodsUnit());// 单位
		totalDao.save(ylgs);
	}

	/**
	 * 更新库存数量
	 * 
	 * @param thisAddZaizhi
	 * @param listzizhi
	 * @return
	 */
	private Goods updatewaigouZai(Float thisAddZaizhi, List listzizhi) {
		Goods g1 = (Goods) listzizhi.get(0);
		g1.setGoodsCurQuantity(Util.FomartFloat(g1.getGoodsCurQuantity()
				+ thisAddZaizhi, 4));
		if (g1.getGoodsCurQuantity() < 0) {
			alertMess(g1);
			g1.setGoodsCurQuantity(0f);
		}
		totalDao.update(g1);
		return g1;
	}

	/**
	 * 添加外购件在制品库存记录
	 * 
	 * @param son
	 * @param old
	 * @param thisAddZaizhi
	 * @return
	 */
	private Goods addWaigouZaiGoods(Procard son, Goods old, Float thisAddZaizhi) {
		Goods gg = new Goods();
		gg.setGoodsMarkId(old.getGoodsMarkId());
		gg.setBanBenNumber(son.getBanBenNumber());
		gg.setGoodsFormat(son.getSpecification());
		gg.setTuhao(son.getTuhao());
		gg.setGoodsLotId(son.getSelfCard());
		gg.setGoodsFullName(old.getGoodsFullName());
		gg.setKgliao(son.getKgliao());
		gg.setGoodsClass("在制品");
		gg.setGoodsCurQuantity(Util.FomartFloat(thisAddZaizhi, 4));
		gg.setGoodsBeginQuantity(Util.FomartFloat(thisAddZaizhi, 4));
		gg.setGoodsChangeTime(Util.getDateTime());
		if (gg.getGoodsCurQuantity() < 0) {
			alertMess(gg);
			gg.setGoodsCurQuantity(0f);
		}
		gg.setGoodsUnit(son.getUnit());
		totalDao.save(gg);
		return gg;
	}

	/**
	 * 发送异常信息
	 * 
	 * @param gg
	 */
	private void alertMess(Goods gg) {
		AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
				+ gg.getGoodsMarkId() + "批次:" + gg.getGoodsLotId()
				+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为" + Util.getDateTime(), "2");
	}

	/**
	 * 添加零件与在制品关系表
	 * 
	 * @param user
	 * @param son
	 * @param old
	 * @param thisAddZaizhi
	 * @param rgoodsId
	 */
	private void addPprGs(Users user, Procard son, Goods old,
			Float thisAddZaizhi, Integer rgoodsId) {
		ProcardProductRelation pprelation = new ProcardProductRelation();
		pprelation.setAddTime(Util.getDateTime());
		pprelation.setProcardId(son.getId());
		pprelation.setGoodsId(rgoodsId);
		pprelation.setZyCount(Util.FomartFloat(thisAddZaizhi, 4));
		pprelation.setFlagType("本批在制品");
		totalDao.save(pprelation);
		// 添加外购件在制品入库记录
		GoodsStore gs = new GoodsStore();
		gs.setGoodsStoreMarkId(old.getGoodsMarkId());// 件号
		gs.setGoodsStoreFormat(son.getSpecification());
		gs.setTuhao(son.getTuhao());
		gs.setGoodsStoreGoodsName(old.getGoodsFullName());// 名称
		gs.setGoodsStoreLot(son.getSelfCard());// 批次
		gs.setGoodsStoreCount(thisAddZaizhi);// 数量
		gs.setPrintStatus("YES");
		gs.setGoodsStoreProMarkId(son.getRootMarkId());// 总成件号
		gs.setGoodsStoreWarehouse("在制品");// 库别
		gs.setGoodsStoreCharger(user.getName());// 经办人
		gs.setStyle("正常（成品）");// 入库类型
		gs.setGoodsStorePerson(user.getName());
		gs.setGoodsStoreDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		gs.setGoodsStoreTime(Util.getDateTime());
		gs.setGoodsStoreUnit(son.getUnit());// 单位
		totalDao.save(gs);
	}

	/**
	 * 对应生产批次在制品入库
	 * 
	 * @param procard
	 * @param count
	 * @param type
	 * @return 在制品库存,在制品与零件中间表,在制品入库数据
	 */
	public Object[] zaizhiInput(Procard procard, Users user, Float count,
			String type) {
		Object[] obj = new Object[3];
		String hqlzaizhi = "from Goods where goodsMarkId='"
				+ procard.getMarkId()
				+ "' and goodsLotId='"
				+ procard.getSelfCard()
				+ "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus!='封存') ";
		List listzizhi = totalDao.find(hqlzaizhi);
		Integer rgoodsId = null;
		Goods gg = null;
		if (listzizhi != null && listzizhi.size() > 0) {
			gg = (Goods) listzizhi.get(0);
			gg.setGoodsCurQuantity(gg.getGoodsCurQuantity() + count);
			if (gg.getGoodsCurQuantity() < 0) {
				alertMess(gg);
				gg.setGoodsCurQuantity(0f);
			}
			totalDao.update(gg);
			rgoodsId = gg.getGoodsId();
		} else {
			gg = new Goods();
			gg.setGoodsMarkId(procard.getMarkId());
			gg.setBanBenNumber(procard.getBanBenNumber());
			gg.setGoodsFormat(procard.getSpecification());
			gg.setTuhao(procard.getTuhao());
			gg.setGoodsLotId(procard.getSelfCard());
			gg.setGoodsFullName(procard.getProName());
			gg.setGoodsClass("在制品");
			gg.setGoodsCurQuantity(count);
			gg.setGoodsBeginQuantity(count);
			gg.setGoodsStyle(type);
			if (gg.getGoodsCurQuantity() < 0) {
				alertMess(gg);
				gg.setGoodsCurQuantity(0f);
			}
			gg.setGoodsUnit(procard.getUnit());
			totalDao.save(gg);
			rgoodsId = gg.getGoodsId();

		}
		// 添加零件与在制品关系表
		ProcardProductRelation pprelation = new ProcardProductRelation();
		pprelation.setAddTime(Util.getDateTime());
		pprelation.setProcardId(procard.getId());
		pprelation.setGoodsId(rgoodsId);
		pprelation.setZyCount(count);
		pprelation.setFlagType("本批在制品");
		totalDao.save(pprelation);
		GoodsStore gs = new GoodsStore();// 添加外购件在制品入库记录
		gs.setGoodsStoreMarkId(procard.getMarkId());// 件号
		gs.setGoodsStoreFormat(procard.getSpecification());
		gs.setTuhao(procard.getTuhao());
		gs.setBanBenNumber(procard.getBanBenNumber());
		gs.setGoodsStoreGoodsName(procard.getProName());// 名称
		gs.setGoodsStoreLot(procard.getSelfCard());// 批次
		// gs.setGoodsStoreCount((float) Math.floor(g
		// .getGoodsCurQuantity()));// 数量
		gs.setGoodsStoreCount(count);// 数量
		gs.setPrintStatus("YES");
		gs.setGoodsStoreProMarkId(procard.getMarkId());// 总成件号
		gs.setGoodsStoreWarehouse("在制品");// 库别
		Users jingban = Util.getLoginUser();
		gs.setGoodsStoreCharger(jingban.getName());// 经办人
		gs.setStyle(type);// 入库类型
		if (user != null) {// 负责人
			gs.setGoodsStorePerson(user.getName());
		} else {
			gs.setGoodsStorePerson(procard.getLingliaoren());
		}
		gs.setGoodsStoreDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		gs.setGoodsStoreUnit(procard.getUnit());// 单位
		totalDao.save(gs);
		obj[0] = gg;
		obj[1] = pprelation;
		obj[2] = gs;
		return obj;
	}

	/**
	 * 添加安全库存
	 * 
	 * @param markId
	 * @param banbenhao
	 * @return
	 */
	public String addneedcg(String markId, String banbenhao) {
		if (markId != null && markId.length() > 0) {
			String hql_wgj = " from YuanclAndWaigj where markId = ? and (banbenStatus is null or banbenStatus!='历史') and minkc is not null ";
			String hql_minkc = " select sum(goodsCurQuantity) from Goods where markId = ?";
			if (banbenhao != null && banbenhao.length() > 0) {
				hql_wgj += " and banbenhao = '" + banbenhao + "'";
				hql_minkc += " and banBenNumber = '" + banbenhao + "'";
			} else {
				hql_wgj += " and (banbenhao = '' or  banbenhao is null)";
				hql_minkc += " and (banBenNumber = '' or banBenNumber is null )";
			}
			YuanclAndWaigj wgj = (YuanclAndWaigj) totalDao
					.getObjectByCondition(hql_wgj, markId);
			if (wgj != null && wgj.getMinkc() != null && wgj.getMinkc() > 0) {
				hql_minkc += " HAVING  sum(goods_CurQuantity)<= ?";
				Float kcCount = (Float) totalDao.getObjectByCondition(
						hql_minkc, markId, wgj.getMinkc());
				if (kcCount != null && kcCount > 0) {
					// 添加手动下单信息;
					if (wgj.getCgcount() == null || wgj.getCgcount() <= 0) {
						sendMessYuan(wgj);
					} else {
						addManualOrderPlanDetail(wgj);
					}
				}
			}
		}
		return "true";
	}

	/**
	 * 当采购数量不准确时。给外购件库管理人员发送消息提醒
	 * 
	 * @param wgj
	 */
	private void sendMessYuan(YuanclAndWaigj wgj) {
		String name = "外购件库";
		if ("试制".equals(wgj.getProductStyle()))
			name = "试制外购件库";
		AlertMessagesServerImpl.addAlertMessages(name, "外购件号为 "
				+ wgj.getMarkId() + "的物料。库存数量已经低于安全库存，请前往《" + name
				+ "》设置准确的(大于0的阿拉伯数字)采购量，如果已有设置，请将此消息设置为已读，谢谢合作！", "安全库存采购量待完善");
	}

	/**
	 * 添加该件号的物料需求 并申请审批
	 * 
	 * @param wgj
	 */
	private void addManualOrderPlanDetail(YuanclAndWaigj wgj) {
		ManualOrderPlanDetail mod = new ManualOrderPlanDetail();
		mod.setMarkId(wgj.getMarkId());// 件号
		mod.setProName(wgj.getName());// 名称
		mod.setSpecification(wgj.getSpecification());// 规格
		mod.setBanben(wgj.getBanbenhao());// 版本号
		mod.setKgliao(wgj.getKgliao());// 供料属性
		mod.setTuhao(wgj.getTuhao());// 图号
		mod.setWgType(wgj.getWgType());// 物料类别
		mod.setUnit(wgj.getUnit());// 单位
		mod.setCgnumber(wgj.getCgcount());// 采购数量
		mod.setType("安全库存");// 添加方式
		mod.setRukuNum(0f);
		mod.setAddTime(Util.getDateTime());// 添加时间
		mod.setEstimatePrice(selectPrice(wgj).floatValue());// 返回预估价格
		mod.setRemarks("件号" + wgj.getMarkId() + "零件名称" + wgj.getName()
				+ "总库存量低于安全库存" + wgj.getMinkc() + "系统自动下单;");
		totalDao.save(mod);
		String processName = "物料需求申请";
		Integer epId = null;
		try {
			epId = CircuitRunServerImpl.createProcess(processName,
					ManualOrderPlanDetail.class, mod.getId(), "epStatus", "id",
					"", "安全库存 采购申请，请您审批", true);
			if (epId != null && epId > 0) {
				mod.setEpId(epId);
				CircuitRun circuitRun = (CircuitRun) totalDao.get(
						CircuitRun.class, epId);
				if ("同意".equals(circuitRun.getAllStatus())
						&& "审批完成".equals(circuitRun.getAuditStatus())) {
					mod.setEpStatus("同意");
					// addmaualPlan1(mod);
				} else {
					mod.setEpStatus("未审批");
				}
				totalDao.update(mod);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new RuntimeException(e.toString());
		}
	}

	/**
	 * 预估价格
	 * 
	 * @param wgj
	 */
	private Double selectPrice(YuanclAndWaigj wgj) {
		String hql = "from Price where partNumber=? and name=? ";
		String time = Util.getDateTime("yyyy-MM-dd");
		hql += " and specification=? and '"
				+ time
				+ "'>= pricePeriodStart and('"
				+ time
				+ "'<= pricePeriodEnd or pricePeriodEnd = '' or pricePeriodEnd is null)";
		Price price = (Price) totalDao.getObjectByCondition(hql, wgj
				.getMarkId(), wgj.getName(), wgj.getSpecification());
		if (price != null && price.getHsPrice() != null) {
			return price.getHsPrice();
		}
		return 0d;
	}

	@Override
	public Object[] findGoodstoProductlingliao(Integer id) {
		// TODO Auto-generated method stub
		Goods goods = (Goods) totalDao.getObjectById(Goods.class, id);
		if (goods != null) {
			Goodstq goodstq = (Goodstq) totalDao.getObjectByCondition(
					"from Goodstq where markId=?", goods.getGoodsMarkId());
			if (goodstq == null) {
				return new Object[] { false, "对不起此零件没有这样出库的权限!" };
			}
			if (goods.getFcStatus() != null && goods.getFcStatus().equals("封存")) {
				return new Object[] { false, "对不起此库存已被封存没有这样出库的权限!" };
			}
			String addSql = null;
			if (goods.getBanBenNumber() == null
					|| goods.getBanBenNumber().length() == 0) {
				addSql = " and (banBenNumber is null or banBenNumber ='')";
			} else {
				addSql = " and banBenNumber = '" + goods.getBanBenNumber()
						+ "'";
			}

			List<Procard> ProcardList = totalDao
					.query(
							"from Procard where markId=? and (hascount is not null or hascount>0) and status in ('已发卡','领工序','已发料') and (sbStatus is null or sbStatus !='删除')",
							goods.getGoodsMarkId());

		}
		return new Object[] { false, "没有找到对应的库存!" };
	}

	@SuppressWarnings("unused")
	// 生产领料时回传到物料需求上的已领数量
	private void ReturnYlNum(Integer procardId, Float sellCount) {
		ManualOrderPlanDetail mod = (ManualOrderPlanDetail) totalDao
				.getObjectByCondition(
						" from ManualOrderPlanDetail where procardId =?",
						procardId);
		Float ylNumber = mod.getYlNumber() == null ? 0 : mod.getYlNumber();
		ylNumber += sellCount;
		mod.setYlNumber(ylNumber);
		ManualOrderPlan mop = mod.getManualPlan();
		Float ylNumber0 = mop.getYlNumber() == null ? 0 : mop.getYlNumber();
		ylNumber0 += sellCount;
		mop.setYlNumber(ylNumber0);
		totalDao.update(mod);
		totalDao.update(mop);
	}

	/***
	 * 查看缺料 件号查询的库存（待检库、外购件库）
	 * 
	 * @param goods
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param role
	 * @param pagestatus
	 * @param goodsAge
	 * @return
	 */
	@Override
	public Object[] findLackGoods(Goods goods, String startDate,
			String endDate, Integer cpage, Integer pageSize, String role,
			String pagestatus, Integer goodsAge) {

		String strif = " and goodsCurQuantity>0";
		String changqu = "";
		if (goods == null) {
			goods = new Goods();
		}
		String markId = goods.getGoodsMarkId();
		goods.setGoodsMarkId(null);
		Users user = (Users) Util.getLoginUser();
		String hqlwarehouse = "from WareHouseAuth where usercode='"
				+ user.getCode() + "'";
		List warehoustlist = totalDao.find(hqlwarehouse);
		boolean isall = false;
		if (warehoustlist != null && warehoustlist.size() > 0) {
			WareHouseAuth whh = (WareHouseAuth) warehoustlist.get(0);
			if (("管理员".equals(whh.getGroup()) || "查看".equals(whh.getGroup()))) {
				isall = true;
				if ("all".equals(pagestatus)) {
					strif = "";
				}
			}
		}
		String hql = "from Goods ";
		String hql_cq = "  goodHouseName in (";
		if (goods != null && goods.getGoodHouseName() != null
				&& goods.getGoodHouseName().length() > 0) {
			String str = "";
			String[] cangqus = goods.getGoodHouseName().split(",");
			if (cangqus != null && cangqus.length > 0) {
				for (int i = 0; i < cangqus.length; i++) {
					str += ",'" + cangqus[i] + "'";
				}
				if (str.length() >= 1) {
					str = str.substring(1);
				}
				hql_cq += str;
			}
			changqu = goods.getGoodHouseName();
			goods.setGoodHouseName(null);
			hql_cq += ")";
		} else {
			hql_cq = "";
		}

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		String str = "";
		String wgType = "";
		if (null != goods) {
			if (goods.getWgType() != null && !"".equals(goods.getWgType())) {
				wgType = goods.getWgType();
				Category category = (Category) totalDao.getObjectByCondition(
						" from Category where name =? ", wgType);
				if (category != null) {
					getWgtype(category);
				}
				str = "  wgType in (" + strbu.toString().substring(1) + ")";
				goods.setWgType(null);
			}
			String bw = "";
			if (goods.getGoodsClass() != null
					&& !"".equals(goods.getGoodsClass())) {
				bw = goods.getGoodsClass();
				goods.setGoodsClass(null);
			}
			hql = totalDao.criteriaQueries(goods, "goodsChangeTime", between,
					str + hql_cq);
			if (!"".equals(bw)) {
				hql += " and goodsClass = '" + bw + "'";
				goods.setGoodsClass(bw);
			}
			hql += " and goodsMarkId = '" + markId + "'";
			goods.setGoodsMarkId(markId);
			// 查外购件库 \待检库

			// hql +=" and goodsCurQuantity>0 "; //查询所有是把库存等于0的也查出来； 查询是只查库存>0的
			if (null != goodsAge && 0 != goodsAge) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				calendar.add(Calendar.DAY_OF_YEAR, -goodsAge);
				String oldDate = format.format(calendar.getTime());
				hql += " and goodsChangeTime< '" + oldDate
						+ "' and goodsCurQuantity>0 ";
			}
			goods.setWgType(wgType);
			if (markId != null && markId.length() > 0
					&& !markId.contains("insert") && !markId.contains("update")
					&& !markId.contains("select") && !markId.contains("delete")) {
				hql += " and goodsMarkId='" + markId + "'";
			}
			if (pagestatus != null && "zz".equals(pagestatus)) {
				hql += " and goodsClass ='在制品' order by goodsChangeTime desc";
			} else if ("dtc".equals(pagestatus)) {
				hql += " and dtcFlag is not null and dtcFlag !='' " + strif
						+ " order by goodsChangeTime desc";
			} else {
				// hql += " and goodsClass in(" + getWarehouseList(null) + ") "
				// + strif + " order by goodsChangeTime desc";
				hql += " and goodsClass in(" + "'外购件库','待检库'" + ") " + strif
						+ " order by goodsChangeTime desc";
			}

		}
		goods.setGoodsMarkId(markId);
		Object[] goodsAarr = new Object[4];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		double sumcount = 0;
		for (Object obj : list) {
			Goods g = (Goods) obj;
			// 查询生产批次
			Procard procard = (Procard) totalDao
					.getObjectByCondition(
							" from Procard where id in(select procardId from ProcardProductRelation where goodsId=?)",
							g.getGoodsId());
			if (procard != null) {
				g.setWxselfCard(procard.getSelfCard());
				g.setYwmarkId(procard.getYwMarkId());
				g.setNeiorderId(procard.getOrderNumber());
			}

			sumcount += g.getGoodsCurQuantity();
		}
		goodsAarr[0] = count;
		goodsAarr[1] = list;
		goodsAarr[2] = sumcount;
		goodsAarr[3] = isall;
		strbu = new StringBuffer();
		goods.setGoodHouseName(changqu);
		return goodsAarr;
	}

	private void getWgtype(Category category) {
		List<Category> list = totalDao.query(
				" from  Category where fatherId = ?", category.getId());
		if (list != null && list.size() > 0) {
			for (Category category2 : list) {
				getWgtype(category2);
			}
		} else {
			strbu.append(",'" + category.getName() + "'");
		}
	}

	public String getWarehouseList(String type) {
		String typeSql = null;
		if (type == null || type.equals("")) {
			typeSql = " and (type is null or type='仓库')";
		} else {
			typeSql = " and type='" + type + "'";
		}
		Users user = (Users) Util.getLoginUser();
		String hql = "from WareHouseAuth where usercode = ?" + typeSql;
		WareHouseAuth a = (WareHouseAuth) totalDao.getObjectByCondition(hql,
				new Object[] { user.getCode() });
		if (a == null) {
			return "''";
		}
		String s = a.getAuth();
		List<String> strList = new ArrayList<String>();
		String[] strArr = s.split(",");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].endsWith("_" + WareHouseAuthServiceImpl.VIEW)) {
				strList.add(strArr[i].substring(0, strArr[i].indexOf('_')));
			}
		}
		List<WareHouse> list = totalDao.find("from WareHouse");
		for (WareHouse wareHouse : list) {
			Collections.replaceAll(strList, wareHouse.getCode(), wareHouse
					.getName());
		}
		String warehouse = "";
		for (String str : strList) {
			warehouse += ",'" + str + "'";
		}
		if (warehouse.length() > 0) {
			return warehouse.substring(1);
		}
		return "'test'";

	}

	public StringBuffer getStrbu() {
		return strbu;
	}

	public void setStrbu(StringBuffer strbu) {
		this.strbu = strbu;
	}

	@Override
	public Object[] findProcardTlDetailbyRootId(Integer id) {
		// TODO Auto-generated method stub
		String ss = " and status not in ('设变锁定') and hascount < filnalCount and (lingliaostatus = '是' or lingliaostatus is null) "
				+ "and procardStyle = '外购' and (tuiLiaoStatus is null or tuiLiaoStatus not in ('未审批','审批中','同意'))";
		List<Procard> list1 = totalDao
				.query(
						"from Procard where (rootId = ? or oldRootId = ?) and lingliaoDetail is not null and lingliaoDetail <> '' and id in (select procardId from ProcardBl where procardId in "
								+ "(select id from Procard where (rootId = ? or oldRootId = ?) "
								+ ss + ")) " + ss + " order by sbStatus desc",
						id, id, id, id);
		// List<Object[]> list11 = totalDao
		// .query(
		// "from Procard p,ProcardBl b where p.rootId=? and (p.sbStatus is null or p.sbStatus !='删除') and p.status not in ('设变锁定') "
		// +" and p.id = b.procardId "
		// +
		// " and p.hascount < p.filnalCount and (p.lingliaostatus = '是' or p.lingliaostatus is null) "
		// +
		// "and p.procardStyle = '外购' and (p.tuiLiaoStatus is null or p.tuiLiaoStatus not in ('未审批','审批中','同意'))",
		// id);
		// for (Object[] object : list11){
		// list1.add((Procard) object[0]);
		// }
		List<Procard> list2 = totalDao
				.query(
						"from Procard where status not in ('设变锁定') and (rootId = ? or oldRootId = ?) "
								+ "and (lingliaostatus = '是' or lingliaostatus is null) and procardStyle = '外购' and epId is not null and tuiLiaoStatus <> '已退料'",
						id,id);// 当申请还未同意时。hascount==filnalCount
		List<Procard> list3 = totalDao
				.query(
						"from Procard where status not in ('设变锁定') and (rootId = ? or oldRootId = ?) "
								+ "and (lingliaostatus = '是' or lingliaostatus is null) and procardStyle = '外购' and epId is not null and tuiLiaoStatus = '已退料'",
						id,id);
		return new Object[] { list1, list2, list3 };
	}

	@Override
	public String addApplicationTuiliao(Procard procard1) {
		// TODO Auto-generated method stub
		if (procard1 != null) {
			if (procard1.getStuiLiaoNumber() <= 0) {
				return "退料数量不能小于0";
			}
			Procard procard = (Procard) totalDao.getObjectById(Procard.class,
					procard1.getId());
			if (procard != null) {
				procard1.setRootId(procard.getRootId());
				Users users = Util.getLoginUser();
				if (users != null) {
					procard.setAppliUserName(users.getName());
					procard.setAppliUserId(users.getId());
				}
				procard.setStuiLiaoNumber(procard1.getStuiLiaoNumber());
				procard.setAppliTime(Util.getDateTime());
				String processName = "生产退料申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Procard.class, procard.getId(), "tuiLiaoStatus",
							"id", null, users.getDept() + "部门  "
									+ users.getName() + " 生产退料申请，请您审批！", true,
							users.getDept());
					if (epId != null && epId > 0) {
						procard.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							procard.setTuiLiaoStatus("同意");
							// procard.setHascount(procard.getHascount()+procard.getStuiLiaoNumber());
							// procard.setYtuiLiaoNumber(procard.getYtuiLiaoNumber()==null?procard.getStuiLiaoNumber():procard.getStuiLiaoNumber()+procard.getYtuiLiaoNumber());
							// addsellTui(procard);
						} else {
							procard.setTuiLiaoStatus("未审批");
						}
						if (totalDao.update(procard)) {
							return "申请成功！";
						}
					} else {
						return "生产退料申请流程不存在，申请失败！";
					}
				} catch (Exception e) {
					return e.toString();
				}
			}
		}
		return "信息为空，申请失败！";
	}

	/**
	 * 退料数量归还，继续领取
	 * 
	 * @param procard
	 */
	public static void jianProcardBl(Procard procard) {
		TotalDao totalDao = createTotol();
		List<ProcardBl> bl = totalDao.query(
				"from ProcardBl where procardId = ? order by id desc", procard
						.getId());
		if (bl != null && bl.size() > 0) {
			Float ff = procard.getStuiLiaoNumber();// 申请退料的数量
			for (ProcardBl procardBl : bl) {
				// 遍历减少ylcount
				ff = updateProcardBl(totalDao, ff, procardBl);
				// 处理上层件号的YlCount
				jianFatherBl(procard, totalDao, procardBl);
			}
		}
	}

	/**
	 * 减少procardBl表外购件的ylcount
	 * 
	 * @param totalDao
	 * @param ff
	 *            剩余申请未退料的数量
	 * @param procardBl
	 * @return
	 */
	private static Float updateProcardBl(TotalDao totalDao, Float ff,
			ProcardBl procardBl) {
		if (procardBl.getYlCount() - ff >= 0)
			procardBl.setYlCount(procardBl.getYlCount() - ff);
		else {
			ff -= procardBl.getYlCount();
			procardBl.setYlCount(0f);
		}
		procardBl.setStatus("未领");
		totalDao.update(procardBl);
		return ff;
	}

	/**
	 * 根据procardId 和 rootId 得到自制件的最小ylCount
	 * 
	 * @param procard
	 * @param totalDao
	 * @param procardBl
	 */
	private static void jianFatherBl(Procard procard, TotalDao totalDao,
			ProcardBl procardBl) {
		List zizhiProcardYl = null;
		try {
			zizhiProcardYl = (List) totalDao
					.query(
							"select min(pb.ylCount*pr.quanzi1/pr.quanzi2) from ProcardBl pb, Procard pr where pb.procardId = pr.id and pr.fatherId = ? and pr.rootId = ?",
							procard.getFatherId(), procardBl.getRootId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}
		if (zizhiProcardYl != null && zizhiProcardYl.size() > 0) {
			Float ylCount = (Float) zizhiProcardYl.get(0);
			ProcardBl bl2 = (ProcardBl) totalDao
					.getObjectByCondition(
							"from ProcardBl where procardId = ? and rootId = ? order by id desc",
							procard.getFatherId(), procardBl.getRootId());
			if (bl2 != null) {
				bl2.setStatus("未领");
				bl2.setYlCount(ylCount == null ? 0 : ylCount);
				totalDao.update(bl2);
			}
		}
	}

	@Override
	public String waigouTuiliaoQueren(Integer[] id) {
		if (id != null && id.length > 0) {
			for (Integer ii : id) {
				Procard procard = (Procard) totalDao.getObjectByCondition(
						"from Procard where id = ? and tuiLiaoStatus = '同意'",
						ii);
				if (procard != null) {
					try {
						addsellTui(procard);
						procard.setHascount(procard.getHascount()
								+ procard.getStuiLiaoNumber());
						procard.setYtuiLiaoNumber(procard.getYtuiLiaoNumber() == null ? procard
										.getStuiLiaoNumber()
										: procard.getStuiLiaoNumber()
												+ procard.getYtuiLiaoNumber());
							//return "退料失败，生成出库记录异常";
						procard.setTuiLiaoStatus("已退料");
						totalDao.update(procard);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return e+"件号："+procard.getMarkId()+"退料失败!";
					}
				}
			}
			return "退料成功！";
		}
		return "请选择要确认的退料信息";
	}

	/**
	 * 生产退料后续方法
	 * 
	 * @param procard
	 */
	public static void addsellTui(Procard procard) {
		if(procard.getRootId()!=null&&procard.getFatherId()!=null){
			jianProcardBl(procard);
		}
		TotalDao totalDao = createTotol();
		
		List<Sell> sells =(List<Sell>) totalDao.query("from Sell where sellId in (select sellId from ProcardWxTuiLiao where " +
				" procardId =? and sqtlNum>0 and agreeNum = 0 and epstatus = '未审批') ",procard.getId() );
//		这里不用了，根据退料明细上的出库记录Id来查询
//		String tuiliaoSell1 = "from Sell where sellMarkId = ? and sellArtsCard = ? and sellWarehouse in ('外购件库','研发库','售后库') and orderNum = ? and sellCount-ifnull(tksellCount,0) = ? and style = '生产刷卡(多)' order by sellId desc";
//		String tuiliaoSell2 = "from Sell where sellMarkId = ? and sellArtsCard = ? and sellWarehouse in ('外购件库','研发库','售后库') and orderNum = ? and style = '生产刷卡(多)' order by sellId desc";
//		List<Sell> sells = new ArrayList<Sell>();
//		try {
//			sells = totalDao.query(tuiliaoSell1, procard.getMarkId(), procard
//					.getSelfCard(), procard.getOrderNumber(), procard
//					.getStuiLiaoNumber());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (sells != null && sells.size() == 0) {
//			sells = totalDao.query(tuiliaoSell2, procard.getMarkId(), procard
//					.getSelfCard(), procard.getOrderNumber());
//		}
		Float ff = procard.getStuiLiaoNumber();
		if (sells != null && sells.size() > 0) {
			Float stf = ff;// 剩余退库数量(初始等于本次申请退库数量)
			Float ytf = 0f;// 已退库数量
			for (Sell sell2 : sells) {
				if (sell2.getTksellCount() == null)
					sell2.setTksellCount(0f);
				Float sellCount = sell2.getSellCount() - sell2.getTksellCount();// 剩余可退库数量
				if (sellCount > 0) {
					Float ben = stf;// 本次可退库数量
					stf = ff - sellCount - ytf;
					if (stf >= 0) {// 本次退库数量大于此出库记录可退库数量
						ben = sellCount;
					} else {
						// ben =
					}
					// 将退料的记录累加上去
					setLingLiaoDe(procard, sell2, ben);
					ytf += ben;
					/** 开始入库;goodsStoreLot **/
					GoodsStore goodsStore = addGoodsStore(sell2, "生产退料入库", ben);
					/*** 添加入库记录、库存记录 ****/
					Goods goodss = GoodsStoreServerImpl
							.save_2(goodsStore, null);
					if (goodss != null) {
						sell2.setTksellCount(sell2.getTksellCount() + ben);
						totalDao.update(sell2);
						// return "true";
					}
					//退料成功，更新退料明细状态.
					ProcardWxTuiLiao txml =	(ProcardWxTuiLiao) totalDao.getObjectByCondition("from ProcardWxTuiLiao where sellId=? and groups = '外购' and procardId=? ", sell2.getSellId(),procard.getId());
					if(txml!=null){
						txml.setAgreeNum(ben);
						txml.setEpstatus("同意");
						totalDao.update(txml);
					}
					// 当 stf小于等于0时结束循环
					if (stf <= 0 || "0.0".equals(stf)) {
						break;
					}
				}
			}
			if (ytf < ff) {// 已退库数量小于申请退库数量，表示出库记录中的数量小于已出库数量(怀疑手动删除过出库记录)
				AlertMessagesServerImpl
						.addAlertMessages("系统维护异常组", "件号："
								+ procard.getMarkId() + " 批次："
								+ procard.getSelfCard() + " id:"
								+ procard.getId() + "退料申请时 出库历史记录中的数量:" + ytf
								+ " 小于已出库数量:" + ff
								+ " (怀疑有人手动删除过该件号的出库历史记录)，请检查。", "1");

			}
		} else {
			throw new RuntimeException(procard.getSelfCard() + " 批次的 "
					+ procard.getMarkId() + " 件号没有外购件库、研发库、售后库的出库记录，请检查！");
		}
	}

	/**
	 * 赋值领料明细 将退料的记录累加上去(-num)
	 * 
	 * @param procard
	 * @param sell2
	 * @param ben
	 */
	private static void setLingLiaoDe(Procard procard, Sell sell2, Float ben) {
		String lingliaoDetail = procard.getLingliaoDetail();
		if (lingliaoDetail == null || lingliaoDetail.length() == 0) {
			lingliaoDetail = "<font color='red' title='"
					+ procard.getAppliUserName() + "申请退料(" + procard.getEpId()
					+ ")'>" + sell2.getSellLot() + ":-" + ben + "</font>";
		} else {
			lingliaoDetail += ",&nbsp;<font color='red' title='"
					+ procard.getAppliUserName() + "申请退料(" + procard.getEpId()
					+ ")'>" + sell2.getSellLot() + ":-" + ben + "</font>";
		}
		procard.setLingliaoDetail(lingliaoDetail);
	}

	/**
	 * 退料入库生成入库历史记录
	 * 
	 * @param sell2
	 * @param ben
	 * @return
	 */
	public static GoodsStore addGoodsStore(Sell sell2, String tlType, Float ben) {
		GoodsStore goodsStore = new GoodsStore();
		goodsStore.setGoodsStoreTime(Util.getDateTime());// 入库时间;
		goodsStore.setGoodsStoreDate(Util.getDateTime("yyyy-MM-dd"));// 入库日期
		goodsStore.setGoodsStorePlanner(Util.getLoginUser().getName());// 操作人
		goodsStore.setStatus("入库");
		goodsStore.setStyle(tlType);// 退料类型
		goodsStore.setGoodsStoreFormat(sell2.getSellFormat());// 规格
		goodsStore.setGoodsStoreMarkId(sell2.getSellMarkId());// 件号
		goodsStore.setProNumber(sell2.getProNumber());// 项目编号
		goodsStore.setGoodsStoreGoodsName(sell2.getSellGoods());// 名称
		goodsStore.setGoodsStoreUnit(sell2.getSellUnit());// 单位
		goodsStore.setKgliao(sell2.getKgliao());// 供料属性
		goodsStore.setBanBenNumber(sell2.getBanBenNumber());// 版本号;
		goodsStore.setGoodsStoreSupplier(sell2.getSellSupplier());// 供应商名称
		goodsStore.setGysId(sell2.getCustomerId() + "");// 供应商Id
		if(sell2.getSellPrice()!=null){
			goodsStore.setHsPrice(sell2.getSellPrice().doubleValue());// 含税价格
		}
		if(sell2.getGoodsPrice()!=null){
			goodsStore.setGoodsStorePrice(sell2.getGoodsPrice().doubleValue());// 不含税价格
		}
		goodsStore.setTaxprice(sell2.getTaxprice() == null ? 0 : sell2
				.getTaxprice().doubleValue());// 税率
		goodsStore.setGoodsStoreWarehouse(sell2.getSellWarehouse());// 所属仓库;
		goodsStore.setWgType(sell2.getWgType());// 物料类别
		goodsStore.setGoodHouseName(sell2.getGoodHouseName());// 所属仓区;
		goodsStore.setGoodsStorePosition(sell2.getKuwei());// 所属库位
		goodsStore.setGoodsStoreSendId(sell2.getSellSendnum());// 送货单号
		goodsStore.setNeiorderId(sell2.getWgOrdernumber());// 采购订单号
		goodsStore.setSellId(sell2.getSellId());// 出库记录id
		goodsStore.setNeiorderId(sell2.getOrderNum());// 内部订单号
		// goodsStore.setWwddId(sell2.getId());// 送货单明细Id;
		// goodsStore.setTuhao(detail.getTuhao());// 图号
		if (sell2.getSellPrice() == null) {
			// return "件号："+sell2.getSellMarkId()+" 批次："+ sell2.getSellLot()
			// +"出库价格为空，退库失败！请联系管理员。";
		} else {
			if(sell2.getSellPrice()!=null){
				goodsStore.setMoney(sell2.getSellPrice().doubleValue() * ben);// 总额
			}
		}
		goodsStore.setGoodsStoreCount(ben);// 入库数量
		// String maxexamineLot = tuiLiaoNumber();
		goodsStore.setGoodsStoreLot(sell2.getSellLot() + "T");// 退料批次=原批次+"T"
		return goodsStore;
	}

	/**
	 * 得到退料批次 (废弃)
	 * 
	 * @return
	 */
	@Deprecated
	public static String tuiLiaoNumber() {
		TotalDao totalDao = createTotol();
		String maxexamineLot = "";
		String mouth = "T" + Util.getDateTime("yyyyMM");
		String hql_examineLot = "select max(goodsStoreLot) from GoodsStore where goodsStoreLot like '%"
				+ mouth + "'";
		Object object = (Object) totalDao.getObjectByCondition(hql_examineLot);
		if (object != null) {
			String oo = object.toString();
			Long selfCard = Long.parseLong(oo.substring(1, oo.length())) + 1;// 当前最大流水卡片
			maxexamineLot = "T" + selfCard.toString();
		} else {
			maxexamineLot = mouth + "00001";
		}
		return maxexamineLot;
	}

	/****
	 * 通过ProcardId 查找备料信息
	 * 
	 * @param procardId
	 * @return
	 */
	@Override
	public List findProcardBlByPId(Integer procardId) {
		if (procardId != null) {
			String hql = "from ProcardBl where procardId=? order by ylingliaoTime";
			return totalDao.query(hql, procardId);
		}
		return null;
	}

	/****
	 * 创建/调整 备料排产计划
	 * 
	 * @param procard
	 * @return
	 */
	@Override
	public String creatBlPlan(Procard procard) {
		if (procard != null
				&& (procard.getId() != null || procard.getThProcardId() != null)) {
			ProcardBl dbProcardbl = null;
			if (procard.getThProcardId() != null) {
				dbProcardbl = (ProcardBl) totalDao.getObjectById(
						ProcardBl.class, procard.getThProcardId());
				if (dbProcardbl != null) {
					procard.setId(dbProcardbl.getProcardId());
					procard.setFilnalCount(dbProcardbl.getPcCount());
				}
			}
			Procard dbProcard = (Procard) totalDao.getObjectById(Procard.class,
					procard.getId());
			if (dbProcard != null) {
				if ("待定".equals(dbProcard.getWlstatus())) {
					return "件号:" + dbProcard.getMarkId() + ",订单号:"
							+ dbProcard.getOrderNumber() + "尚未激活MRP,请先前往激活!谢谢。";
				}

				if (dbProcard.getHasPlan() == null) {
					dbProcard.setHasPlan(0F);
				}

				// 查询该批次是否存在设变锁定
				String hql_sbsd = "from Procard where rootId=? and status='设变锁定' and oldStatus='初始'";
				Integer sbcount = totalDao.getCount(hql_sbsd, dbProcard
						.getRootId());
				if (sbcount > 0) {
					throw new RuntimeException("本批次上有" + sbcount
							+ "个零件为'设变锁定'状态,请等待设变完成后再进行排产!");
				}

				boolean iscreate = false;
				dbProcard.setPlanCount(procard.getFilnalCount());
				if (dbProcardbl == null) {
					iscreate = true;
					Float hasplancount = dbProcard.getFilnalCount()
							- dbProcard.getHasPlan() - procard.getFilnalCount();
					if (hasplancount < 0) {
						return dbProcard.getMarkId() + "的总数量"
								+ dbProcard.getFilnalCount() + "已经全部排产，请勿重复排产!";
					}
				}

				/*************** 将整个bom放到map中，用于后续的激活用.避免频繁调用数据库查询，优化速度 ****************************/
				// Map<Integer, Procard> procardAllMap = new HashMap<Integer,
				// Procard>();
				Map<Integer, List<Procard>> procardMap = new HashMap<Integer, List<Procard>>();
				Map<Integer, List<ProcessInfor>> processInforMap = new HashMap<Integer, List<ProcessInfor>>();

				String hql_zizhiP = "from Procard where rootId=?";
				List<Procard> list_zihzi = totalDao.query(hql_zizhiP, dbProcard
						.getRootId());
				String wgAllMarkId = "''";
				for (Procard procard2 : list_zihzi) {
					// if (iscreate) {
					// procardAllMap.put(procard2.getId(), procard2);
					// }
					List<Procard> sonProcardList = null;
					if (dbProcard.getId().equals(procard2.getId())) {
						sonProcardList = procardMap.get(procard2.getId());
					} else {
						sonProcardList = procardMap.get(procard2.getFatherId());
					}
					if (sonProcardList == null) {
						sonProcardList = new ArrayList<Procard>();
					}
					sonProcardList.add(procard2);

					// 子项列表
					if (dbProcard.getId().equals(procard2.getId())) {
						procardMap.put(procard2.getId(), sonProcardList);
					} else {
						procardMap.put(procard2.getFatherId(), sonProcardList);
					}
					// 工序列表
					List<ProcessInfor> sonProcessInforList = new ArrayList<ProcessInfor>();
					sonProcessInforList.addAll(procard2.getProcessInforSet());
					processInforMap.put(procard2.getId(), sonProcessInforList);

					// 拼接所有的外购件库件号，后面查询用
					if ("外购".equals(procard2.getProcardStyle())) {
						wgAllMarkId += ",'" + procard2.getMarkId() + "'";
					}
				}

				// 收集所有工序
				// String hql_ProcessInfor =
				// "from ProcessInfor where procard.id in (select id from from Procard where rootId=?) order by processNO desc";
				// List<ProcessInfor> list_ProcessInfor =
				// totalDao.query(hql_ProcessInfor,
				// procard.getRootId());
				// for (ProcessInfor processInfor : list_ProcessInfor) {
				// List<ProcessInfor> sonProcessInforList = processInforMap
				// .get(processInfor.getProcard().getId());
				// if (sonProcessInforList == null) {
				// sonProcessInforList = new ArrayList<ProcessInfor>();
				// }
				// sonProcessInforList.add(processInfor);
				// processInforMap.put(processInfor.getProcard().getId(),
				// sonProcessInforList);
				// }

				Map<String, YuanclAndWaigj> yuanclAndWaigjMap = new HashMap<String, YuanclAndWaigj>();
				String hql_yawP = "from YuanclAndWaigj where markId in ("
						+ wgAllMarkId
						+ ") and (banbenStatus ='默认' or banbenStatus is null)";
				List<YuanclAndWaigj> list_yaw = totalDao.query(hql_yawP);
				for (YuanclAndWaigj yuanclAndWaigj : list_yaw) {
					yuanclAndWaigjMap.put(yuanclAndWaigj.getMarkId()
							+ yuanclAndWaigj.getKgliao(), yuanclAndWaigj);
				}

				// 开始激活生产任务
				procardServer.sendRunCard4(dbProcard, procardMap,
						processInforMap, yuanclAndWaigjMap, procard
								.getJihuoDate(), iscreate, dbProcard.getId()
								+ "", dbProcardbl);
				return "排产完成!";
			}
		}
		return "排产失败";
	}

	@Override
	public Object[] findAllTuiLiao(Procard procard, int parseInt, int pageSize,
			String tag, String starttime, String endtime) {
		// TODO Auto-generated method stub
		if (procard == null)
			procard = new Procard();
		String hql = totalDao.criteriaQueries(procard, null, "cangqu");
		// if ("code".equals(tag)) hql +=
		// " and cardNum = '"+Util.getLoginUser().getCardId()+"'";
		String cangqu_sql = "";
		if (procard.getCangqu() != null && procard.getCangqu().length() > 0) {
			cangqu_sql = " and goodHouseName like '%" + procard.getCangqu()
					+ "%'";
		}
		if (starttime != null && starttime.length() > 0) {
			hql += " and date(appliTime) >='" + starttime + " 00:00:00";
		}
		if (endtime != null && endtime.length() > 0) {
			hql += " and date(appliTime) <='" + endtime + " 23:59:59";
		}
		String status = "";
		if ("wei".equals(tag)) {// 如果wei 显示本人未审批的申请记录
			Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
					Procard.class, false);
			if (map != null) {
				List<Integer> idList = (List<Integer>) map.get("entityId");
				StringBuffer sb = new StringBuffer();
				if (idList != null && idList.size() > 0) {
					for (Integer orderid : idList) {
						if (sb.length() == 0) {
							sb.append(orderid);
						} else {
							sb.append("," + orderid);
						}
					}
					if (sb.length() == 0) {
						return new Object[] { null, 0 };
					} else {
						status = " and id in(" + sb.toString() + ")";
					}
				} else {
					return new Object[] { null, 0 };
				}
			} else {
				return new Object[] { null, 0 };
			}
		}
		hql += " and epId is not null and (tuiLiaoStatus <> '已退料' or tuiLiaoStatus is null )";
		// List list = totalDao.findAllByPage(hql+status, parseInt, pageSize);
		List list = totalDao.query(hql + status);
		if (list != null && list.size() > 0) {
			for (int i = (list.size() - 1); i >= 0; i--) {
				Procard p = (Procard) list.get(i);
				Sell s = (Sell) totalDao.getObjectByCondition(
						" from Sell where sellMarkId = ? and sellArtsCard =? and style='生产刷卡(多)'"
								+ "  and orderNum = ?  " + cangqu_sql, p
								.getMarkId(), p.getSelfCard(), p
								.getOrderNumber());
				if (s != null) {
					p.setCangqu(s.getGoodHouseName());
				} else if (cangqu_sql.length() > 0) {
					list.remove(i);
				}
			}
		}
		int count = 0;
		if (list != null) {
			count = list.size();
		}
		Object[] o = { list, count };
		return o;
	}

	@Override
	public String auditTuiliao(Integer[] ids, String tag) {
		String mess = "";
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				Procard procard = byProcardId(id);
				if (procard != null && procard.getEpId() != null) {
					if ("ok".equals(tag)) {// 同意
						mess = circuitRunServer.updateExeNodeByCirId(procard
								.getEpId(), true, "", true, null, true);
					} else if ("no".equals(tag)) {// 打回
						mess = circuitRunServer.updateExeNodeByCirId(procard
								.getEpId(), false, "", true, null, true);
					} else {
						return "数据异常!";
					}
					CircuitRun circuitRun = (CircuitRun) totalDao
							.getObjectById(CircuitRun.class, procard.getEpId());
					if (circuitRun.getAllStatus().equals("同意")) {
						procard.setTuiLiaoStatus("同意");
					} else {
						procard.setTuiLiaoStatus("打回");
					}
					totalDao.update(procard);
				}
			}
		} else {
			return "数据异常!";
		}
		return mess;
	}

	@Override
	public String addApplicationTuiliao(Integer[] procardId,
			Float[] stuiLiaoNumber) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		boolean b = false;
		for (int i = 0; i < stuiLiaoNumber.length; i++) {
			if (stuiLiaoNumber[i] <= 0 || procardId[i] == null)
				continue;
			Procard procard = (Procard) totalDao.getObjectById(Procard.class,
					procardId[i]);
			if (procard != null) {
				if (users != null) {
					procard.setAppliUserName(users.getName());
					procard.setAppliUserId(users.getId());
				}
				procard.setStuiLiaoNumber(stuiLiaoNumber[i]);
				procard.setAppliTime(Util.getDateTime());
				String processName = "生产退料申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							Procard.class, procard.getId(), "tuiLiaoStatus",
							"id", null, users.getDept() + "部门  "
									+ users.getName() + " 生产退料申请，请您审批！", true,
							users.getDept());
					if (epId != null && epId > 0) {
						procard.setEpId(epId);
						CircuitRun circuitRun = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun.getAllStatus())
								&& "审批完成".equals(circuitRun.getAuditStatus())) {
							procard.setTuiLiaoStatus("同意");
						} else {
							procard.setTuiLiaoStatus("未审批");
						}
						b = totalDao.update(procard);
					} else {
						return "生产退料申请流程不存在，申请失败！";
					}
				} catch (Exception e) {
					return e.toString();
				}
			}
		}
		if (b)
			return "申请成功！";
		else {
			return "申请失败！";
		}
	}

	public String auditWxTuiliao(Integer[] ids, String tag) {
		String mess = "";
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				ProcardWxTuiLiao wxtl = (ProcardWxTuiLiao) totalDao
						.getObjectById(ProcardWxTuiLiao.class, id);
				if (wxtl != null) {
					if ("ok".equals(tag)) {// 同意
						mess = circuitRunServer.updateExeNodeByCirId(wxtl
								.getEpId(), true, "", true, null, true);
					} else if ("no".equals(tag)) {// 打回
						mess = circuitRunServer.updateExeNodeByCirId(wxtl
								.getEpId(), false, "", true, null, true);
					} else {
						return "数据异常!";
					}
					CircuitRun circuitRun = (CircuitRun) totalDao
							.getObjectById(CircuitRun.class, wxtl.getEpId());
					if ("同意".equals(circuitRun.getAllStatus())) {
						wxtl.setEpstatus("同意");
					} else {
						wxtl.setEpstatus("打回");
					}
					totalDao.update(wxtl);
				}
			}
		} else {
			return "数据异常!";
		}
		return mess;
	}

	@Override
	public Object[] findAllWxTuiliao(ProcardWxTuiLiao wxtl, int parseInt,
			int pageSize, String tag) {
		if (wxtl == null) {
			wxtl = new ProcardWxTuiLiao();
		}
		String hql = totalDao.criteriaQueries(wxtl, null);
		hql += " order by addTime desc";
		String status = "";
		if ("wei".equals(tag)) {
			// 如果wei 显示本人未审批的申请记录
			Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
					ProcardWxTuiLiao.class, false);
			if (map != null) {
				List<Integer> idList = (List<Integer>) map.get("entityId");
				StringBuffer sb = new StringBuffer();
				if (idList != null && idList.size() > 0) {
					for (Integer orderid : idList) {
						if (sb.length() == 0) {
							sb.append(orderid);
						} else {
							sb.append("," + orderid);
						}
					}
					if (sb.length() == 0) {
						return new Object[] { null, 0 };
					} else {
						status = " and id in(" + sb.toString() + ")";
					}
				} else {
					return new Object[] { null, 0 };
				}
			} else {
				return new Object[] { null, 0 };
			}

		}
		List<ProcardWxTuiLiao> wxtlList = totalDao.findAllByPage(hql + status,
				parseInt, pageSize);

		int count = totalDao.getCount(hql + status);
		return new Object[] { wxtlList, count };
	}

	/**
	 * 查询是否有系统设置项目
	 * 
	 * @param valueName
	 *            系统设置项简称
	 * @return ValueCode==是 返回true
	 */
	public static boolean SystemShezhi(String valueName) {
		TotalDao totalDao = createTotol();
		String hql = "select valueCode from CodeTranslation where type = 'sys' and valueName = ? ";
		String valueCode = (String) totalDao.getObjectByCondition(hql,
				valueName);
		if (valueCode != null && valueCode.equals("是"))
			return true;
		else
			return false;
	}

	/**
	 * 查询是否有系统设置项目
	 * 
	 * @param valueName
	 *            系统设置项 简称
	 * @param keyCode
	 *            设置项
	 * @return ValueCode==是 返回true
	 */
	public static boolean SystemShezhi(String valueName, String keyCode) {
		TotalDao totalDao = createTotol();
		String hql = "select valueCode from CodeTranslation where type = 'sys' and valueName = ? and keyCode = ? ";
		String valueCode = (String) totalDao.getObjectByCondition(hql,
				valueName, keyCode);
		if (valueCode != null && valueCode.equals("是"))
			return true;
		else
			return false;
	}

	// public Float dgForProcardCount(Map<Integer, Procard> procardAllMap,
	// Procard procard) {
	// if (procardAllMap != null && procardAllMap.size() > 0) {
	// procardAllMap.get(procard.getFatherId());
	// }
	// return 0F;
	// }

	@Override
	public void tuihuiWaiweiLingliao() {
		TotalDao totalDao = createTotol();
		List<Sell> waiweiSell = totalDao
				.query("from Sell where wgOrdernumber is not null and (tksellCount < sellCount or tksellCount is null) "
						+ "and wgOrdernumber in (select planNumber from WaigouOrder where type = '外委' and status in ('手动删除','删除'))");
		if (waiweiSell != null && waiweiSell.size() > 0) {
			for (Sell sell2 : waiweiSell) {
				/** 开始入库;goodsStoreLot **/
				GoodsStore goodsStore = addGoodsStore(sell2, "外委退料入库", sell2
						.getSellCount());
				/*** 添加入库记录、库存记录 ****/
				Goods goodss = GoodsStoreServerImpl.save_2(goodsStore, null);
				if (goodss != null) {
					if (sell2.getTksellCount() == null)
						sell2.setTksellCount(0f);
					sell2.setTksellCount(sell2.getSellCount());
					totalDao.update(sell2);
				}
				// ProcessInforWWProcard wwProcard =
				// totalDao.getObjectByCondition("", agr);
			}
		}

	}

	@Override
	public Object[] findAllDaiTuiliao(Procard procard, int parseInt,
			int pageSize, String pagestatus, String starttime, String endtime) {
		// TODO Auto-generated method stub
		if (procard == null)
			procard = new Procard();
		String hql = totalDao.criteriaQueries(procard, null, "cangqu")
				+ " and tuiLiaoStatus = '同意' ";
		String cangqu_sql = "";
		if (procard.getCangqu() != null && procard.getCangqu().length() > 0) {
			cangqu_sql = " and goodHouseName like '%" + procard.getCangqu()
					+ "%'";
		}

		if (starttime != null && starttime.length() > 0) {
			hql += " and date(appliTime) >='" + starttime + " 00:00:00";
		}
		if (endtime != null && endtime.length() > 0) {
			hql += " and date(appliTime) <='" + endtime + " 23:59:59";
		}
		hql += " order by appliTime desc";

		// // if ("code".equals(pagestatus)) hql +=
		// " and cardNum = '"+Util.getLoginUser().getCardId()+"'";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		if (list != null && list.size() > 0) {
			for (int i = (list.size() - 1); i >= 0; i--) {
				Procard p = (Procard) list.get(i);
				Sell s = (Sell) totalDao.getObjectByCondition(
						" from Sell where sellMarkId = ? and sellArtsCard =? and style='生产刷卡(多)'"
								+ "  and orderNum = ?  " + cangqu_sql, p
								.getMarkId(), p.getSelfCard(), p
								.getOrderNumber());
				if (s != null) {
					p.setCangqu(s.getGoodHouseName());
				} else if (cangqu_sql.length() > 0) {
					list.remove(i);
				}
			}
		}
		int count = totalDao.getCount(hql);
		if (cangqu_sql.length() > 0 && list != null) {
			count = list.size();
		}
		Object[] o = { list, count };
		return o;
	}

	private String updatMaxSellPrintNumber(Sell sell, String time) {
		Integer year = Integer.parseInt(Util.getDateTime("yyyy"));
		String month = "";
		if (year > 2018) {
			month = Util.getDateTime("yyyyMM");
		}
		String printNumber = (String) totalDao
				.getObjectByCondition(
						"select printNumber from Sell where orderNum =? and goodHouseName =? and printNumber like 'SOUT"
								+ month
								+ "%' "
								+ " and sellTime =?  and sellCharger =? ", sell
								.getOrderNum(), sell.getGoodHouseName(), time,
						sell.getSellCharger());
		String plannumber = "000001";
		PrintedOutOrder printorder = null;
		if (printNumber != null && printNumber.length() > 0) {
			plannumber = printNumber;
			printorder = (PrintedOutOrder) totalDao.getObjectByCondition(
					" from PrintedOutOrder where planNum =? ", plannumber);
		}
		if (printorder != null) {
			Set<PrintedOut> printedOutSet = printorder.getPrintedOutSet();
			if (printedOutSet == null) {
				printedOutSet = new HashSet<PrintedOut>();
			}
			PrintedOut printedOut = new PrintedOut();
			printedOut.setClassName("Sell");
			printedOut.setEntiyId(sell.getSellId());
			printedOut.setYwmarkId(sell.getYwmarkId());
			printedOut.setMarkId(sell.getSellMarkId());
			printedOut.setProNmae(sell.getSellGoods());
			printedOut.setFormat(sell.getSellFormat());
			printedOut.setUnit(sell.getSellUnit());
			printedOut.setNum(sell.getSellCount());
			printedOut.setNeiOrderNum(sell.getOrderNum());
			printedOut.setWaiOrderNum(sell.getOutOrderNumer());
			printedOut.setKubie(sell.getSellWarehouse());
			printedOut.setCangqu(sell.getGoodHouseName());
			printedOut.setSellTime(sell.getSellDate());
			printedOut.setSelfCard(sell.getSellLot());
			printedOut.setTuhao(sell.getTuhao());
			printedOut.setPlanNum(plannumber);
			printedOut.setAddtime(Util.getDateTime());
			printedOutSet.add(printedOut);
			printorder.setPrintedOutSet(printedOutSet);
			totalDao.update(printorder);
		} else {
			printorder = new PrintedOutOrder();
			printorder.setAddTime(Util.getDateTime());
			printorder.setKehuNmae(sell.getSellCompanyName());
			printorder.setClassName("Sell");
			printorder.setRiqi(sell.getSellDate());
			printorder.setAddUsers(Util.getLoginUser().getName());
			printorder.setShPlanNum(sell.getSellSendnum());
			printorder.setType("生产领料单");
			Set<PrintedOut> printedOutSet = new HashSet<PrintedOut>();
			PrintedOut printedOut = new PrintedOut();
			printedOut.setClassName("Sell");
			printedOut.setEntiyId(sell.getSellId());
			printedOut.setYwmarkId(sell.getYwmarkId());
			printedOut.setMarkId(sell.getSellMarkId());
			printedOut.setProNmae(sell.getSellGoods());
			printedOut.setFormat(sell.getSellFormat());
			printedOut.setUnit(sell.getSellUnit());
			printedOut.setNum(sell.getSellCount());
			printedOut.setNeiOrderNum(sell.getOrderNum());
			printedOut.setWaiOrderNum(sell.getOutOrderNumer());
			printedOut.setKubie(sell.getSellWarehouse());
			printedOut.setCangqu(sell.getGoodHouseName());
			printedOut.setSellTime(sell.getSellDate());
			printedOut.setSelfCard(sell.getSellLot());
			printedOut.setTuhao(sell.getTuhao());
			printedOut.setAddtime(Util.getDateTime());
			printedOutSet.add(printedOut);
			printorder.setPrintedOutSet(printedOutSet);
			printorder.setRiqi(sell.getSellDate());
			String maxprintNumber = (String) totalDao
					.getObjectByCondition(" select max(printNumber) from Sell where printNumber like 'SOUT"
							+ month
							+ "%' and sellWarehouse in ('外购件库','外协库','委外库')");
			if (maxprintNumber == null || maxprintNumber.length() == 0) {
				plannumber = "SOUT" + plannumber;
			} else {
				String number = (1000001 + Integer.parseInt(maxprintNumber
						.substring(4)))
						+ "";
				plannumber = "SOUT" + number.substring(1);
			}
			printorder.setPlanNum(plannumber);
			printedOut.setPlanNum(plannumber);
			printedOutSet.add(printedOut);
			printorder.setPrintedOutSet(printedOutSet);
			totalDao.save(printorder);
		}
		if ("YES".equals(sell.getPrintStatus())) {
			sell.setPrintStatus("YES");
			totalDao.update(sell);
		}
		return plannumber;
	}

	@Override
	public String addApplicationTuiliao(List<Procard> procardList) {
		Users users = Util.getLoginUser();
		if(users==null){
			return "请先登录!~";
		}
		if(procardList!=null && procardList.size()>0){
			boolean b = false;
			for (Procard procard : procardList) {
				if(procard!=null && procard.getId()!=null){
					List<Sell> sellList = procard.getSellList();
					Float  sumtlNum = 0f;
					procard = (Procard) totalDao.get(Procard.class,procard.getId());
					for (Sell sell : sellList) {
						if(sell!=null && sell.getSellId()!=null){
							Float tlNum =	sell.getTksellCount();
							sumtlNum+=tlNum;
							sell = (Sell) totalDao.get(Sell.class, sell.getSellId());
							ProcardWxTuiLiao tlmx = new ProcardWxTuiLiao();
							tlmx.setProcardId(procard.getId());
							tlmx.setMarkId(procard.getMarkId());
							tlmx.setNum(procard.getFilnalCount());
							tlmx.setYlNum(procard.getFilnalCount()-procard.getHascount());
							tlmx.setProName(procard.getProName());
							tlmx.setSelfCard(procard.getSelfCard());
							tlmx.setYwMarkid(procard.getYwMarkId());
							tlmx.setRootMarkId(procard.getRootMarkId());
							tlmx.setOrderNum(procard.getOrderNumber());
							tlmx.setSellId(sell.getSellId());
							tlmx.setGroups("外购");
							tlmx.setSellLot(sell.getSellLot());
							tlmx.setGys(sell.getSellSupplier());
							tlmx.setAddTime(Util.getDateTime());
							tlmx.setAddUsersName(users.getName());
							tlmx.setAddUserCode(users.getCode());
							tlmx.setSqtlNum(tlNum);
							tlmx.setAgreeNum(0f);
							tlmx.setEpstatus("未审批");
							totalDao.save(tlmx);
						}
					}
					procard.setStuiLiaoNumber(sumtlNum);
					procard.setAppliTime(Util.getDateTime());
					String processName = "生产退料申请";
					Integer epId = null;
					try {
						epId = CircuitRunServerImpl.createProcess(processName,
								Procard.class, procard.getId(), "tuiLiaoStatus",
								"id", null, users.getDept() + "部门  "
										+ users.getName() + " 生产退料申请，请您审批！", true,
								users.getDept());
						if (epId != null && epId > 0) {
							procard.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.get(
									CircuitRun.class, epId);
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun.getAuditStatus())) {
								procard.setTuiLiaoStatus("同意");
							} else {
								procard.setTuiLiaoStatus("未审批");
							}
							b = totalDao.update(procard);
						} else {
							return "生产退料申请流程不存在，申请失败！";
						}
					} catch (Exception e) {
						return e.toString();
					}
				}
			}
			if (b){
				return "申请成功！";
			}
			else {
				return "申请失败！";
			}
		}else{
			return "请刷新后重试";
		}
	}
}