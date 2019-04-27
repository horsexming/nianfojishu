package com.task.ServerImpl.gzbj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import sun.misc.OSEnvironment;

import com.task.Dao.TotalDao;
import com.task.Server.gzbj.ProcessGzstoreServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.Measuring;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.Util;

public class ProcessGzstoreServerImpl implements ProcessGzstoreServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 
	 * 查询所有工序(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.gzbj.ProcessGzstoreServer#findGxAll(com.task.entity.gzbj
	 * .ProcessGzstore, int, int)
	 */
	@Override
	public Object[] findGxAll(ProcessGzstore processGzstore, int pageNo,
			int pageSize) {
		if (processGzstore == null) {
			processGzstore = new ProcessGzstore();
		}
		String sql = "select * from ta_processgzstore  where 1=1";

		if (!"".equals(processGzstore.getProcessName())
				&& processGzstore.getProcessName() != null) {
			sql += " and processName like '%" + processGzstore.getProcessName()
					+ "%'";
		}
		String sql1 = sql + " order by id desc";
		List list1 = totalDao.findBySql(sql1, pageNo, pageSize);
		int count = totalDao.findBySql(sql).size();// 总行数
		// System.out .println("总行数：" + count + "当前行数" + pageNo + "总页数：" +
		// pageSize);
		Object[] o = { list1, count };
		return o;
	}

	/**
	 *  
	 * 查询所有工序ForSelect
	 * 
	 */
	public List getProcessGzstoreListAllForSelect(ProcessGzstore processGzstore) {
		String hql = "from ProcessGzstore  where 1=1 order by processName";
		List list = this.totalDao.find(hql);
		return list;
	}

	/**
	 * 
	 * 查询工序根据ID
	 * 
	 */
	public ProcessGzstore getProcessGzstoreById(Integer id) {

		return (ProcessGzstore) totalDao.get(ProcessGzstore.class, id);
	}

	@Override
	public boolean bdOsScope(Integer id, List<OsScope> osList) {

		if (id != null && id > 0) {
			ProcessGzstore oldpg = (ProcessGzstore) totalDao.get(
					ProcessGzstore.class, id);
			Set<OsScope> os = oldpg.getOs();
			if (osList != null && osList.size() > 0) {
				for (int i = 0; i < osList.size(); i++) {
					os.add(osList.get(i));
					totalDao.save(osList.get(i));
				}
				oldpg.setOs(os);
			}
			oldpg.setStatus("未更新");
			return totalDao.update(oldpg);
		}
		return false;

	}

	@Override
	public List<OsScope> getOsListbyId(Integer id) {
		List<OsScope> osList = new ArrayList<OsScope>();
		ProcessGzstore oldpg = (ProcessGzstore) totalDao.get(
				ProcessGzstore.class, id);
		Set<OsScope> os = oldpg.getOs();
		for (OsScope osScope : os) {
			osList.add(osScope);
		}
		return osList;
	}

	@Override
	public boolean delOsScope(Integer processId, Integer osId) {
		if (processId != null && osId != null) {
			ProcessGzstore pg = (ProcessGzstore) totalDao.get(
					ProcessGzstore.class, processId);
			OsScope os = (OsScope) totalDao.get(OsScope.class, osId);
			Set<OsScope> osSet = pg.getOs();
			OsTemplate ost = (OsTemplate) totalDao
					.getObjectByCondition(
							"from OsTemplate where id=(select  osTemplate from OsScope where id=?)",
							osId);
			if (ost != null) {
				Set<OsScope> osSet1 = ost.getScopes();
				osSet1.remove(os);
				ost.setScopes(osSet1);
				totalDao.update(ost);
			}
			osSet.remove(os);
			pg.setOs(osSet);
			if (totalDao.update(pg)) {
				return totalDao.delete(os);
			}
		}
		return false;
	}

	@Override
	public boolean update(ProcessGzstore pg) {
		if (pg != null) {
			return totalDao.update(pg);
		}
		return false;
	}

	@Override
	public String shuaixn(String processName) {
		String msg = "";
		String hql_pg = "from ProcessGzstore where processName=?";
		ProcessGzstore pg = (ProcessGzstore) totalDao.getObjectByCondition(
				hql_pg, processName);
		Set<OsScope> osSet = pg.getOs();
		List<OsScope> osList = new ArrayList<OsScope>();
		for (OsScope osScope : osSet) {
			osList.add(osScope);
		}
		if (osList != null && osList.size() > 0) {
			String hql = " from ProcessTemplate where processName='"
					+ processName
					+ "' and"
					+ " processNO is not null and processNO>0 and procardTemplate.id is not null ";
			List<ProcessTemplate> ptList = (List<ProcessTemplate>) totalDao
					.find(hql);
			if (ptList != null && ptList.size() > 0) {
				List<String> tag = new ArrayList<String>();
				for (ProcessTemplate pt : ptList) {
					List<Integer> otIdList = new ArrayList<Integer>();
					ProcardTemplate procardt = pt.getProcardTemplate();
					if(procardt == null){
						continue;
					}
					String partNumber = pt.getProcardTemplate().getMarkId();

					if (tag.contains(partNumber + pt.getProcessNO())) {
						continue;
					} else {
						tag.add(partNumber + pt.getProcessNO());
					}
					if (partNumber != null && !"".equals(partNumber)) {
						String hql_ost = "from OsTemplate where  gongxuNum='"
								+ pt.getProcessNO() + "' and  partNumber='"
								+ partNumber + "'";
						List<OsTemplate> ostList = totalDao.find(hql_ost);
						if (ostList.size() > 0) {
							for (OsTemplate ot : ostList) {
								if (!otIdList.contains(ot.getId())) {
									otIdList.add(ot.getId());
									Set<OsScope> osset1 = ot.getScopes();
									for (OsScope sc : osSet) {
										List<OsScope> osList1 = new ArrayList<OsScope>();
										for (OsScope os1 : osset1) {
											if (os1.getContent().equals(
													sc.getContent())
													&& os1.getJcff().equals(
															sc.getJcff())
													&& os1.getZltz().equals(
															sc.getZltz())
													&& os1.getType().equals(
															sc.getType())) {

												osList1.add(os1);
												// }else{
												// OsScope osc = new OsScope();
												// osc.setContent(sc.getContent());
												// osc.setJcff(sc.getJcff());
												// osc.setZltz(sc.getZltz());
												// osc.setType(sc.getType());
												// osset1.add(osc);
											}

										}
										if (osList1 == null
												|| osList1.size() == 0) {
											OsScope osc = new OsScope();
											osc.setContent(sc.getContent());
											osc.setJcff(sc.getJcff());
											osc.setZltz(sc.getZltz());
											osc.setType(sc.getType());
											osc.setOsTemplate(ot);
											osset1.add(osc);
										}

									}
									ot.setScopes(osset1);
									if (ot.getXjtype() == null) {
										ot.setGongxuName(pt.getProcessName());
										ot.setZhonglei("巡检");
										ot.setXjtype("按时间");
										ot.setXjcheckpc(2);
									}
									if (!totalDao.update(ot)) {
										msg += "" + ot.getId();
									}
								}
							}
						} else {
							OsTemplate ost = new OsTemplate();
							ost.setPartNumber(partNumber);
							ost.setGongxuNum(pt.getProcessNO() + "");
							ost.setGongxuName(pt.getProcessName());
							ost
									.setCmodel(pt.getProcardTemplate()
											.getCarStyle());
							ost.setName(pt.getProcardTemplate().getProName());
							ost.setCtype1(pt.getProcardTemplate()
									.getProcardStyle());
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
							if (!totalDao.save(ost)) {
								msg += "" + pt.getId();
							}
							;

						}
					}
				}
			} else {
				msg = "没有找到对应的工序模板";
			}

		} else {
			msg = "没有找到对应的检查项模板";
		}
		if ("".equals(msg)) {
			msg = "true";
		}

		return msg;
	}

	@Override
	public ProcessGzstore getproceGzstorebyname(String processName) {
		String hql = "from ProcessGzstore where processName=?";
		return (ProcessGzstore) totalDao.getObjectByCondition(hql, processName);
	}
	
	@Override
	public String applySpecial(Integer id) {
		// TODO Auto-generated method stub
		ProcessGzstore processGz= (ProcessGzstore) totalDao.getObjectById(
				ProcessGzstore.class, id);
		Integer epId;
		try {
			epId = CircuitRunServerImpl.createProcess("工序库特殊工序申请",
					ProcessGzstore.class, id, "isSpecial", "id", "特殊工序申请审批", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "审批流程有误,请联系管理员!";
		}
		if (epId != null) {
			processGz.setEpId(epId);
			processGz.setIsSpecial("审批中");
			return totalDao.update(processGz) + "";
		} else {
			return "审批流程有误,请联系管理员!";
		}
	}

	@Override
	public List<ProcessAndMeasuring> findAllPamList(Integer id) {
		if(id!=null){
			List<ProcessAndMeasuring> pamList =	totalDao.query(" from ProcessAndMeasuring where processId = ?", id);
			for (ProcessAndMeasuring pam : pamList) {
				Measuring mea =	(Measuring) totalDao.get(Measuring.class, pam.getMeasuringId());
				pam.setMeasuring(mea);
			}
			return  pamList;
		}
		return null;
	}

	@Override
	public List<Measuring> findALLmMeasList(Integer id, Measuring measuring) {
			if(measuring==null){
				measuring = new Measuring();
			}
			String hql =	totalDao.criteriaQueries(measuring, null);
			hql+= " and id not in (select measuringId from ProcessAndMeasuring where processId =? )";
			return  totalDao.query(hql,id);
	}

	@Override
	public String savePAM(Integer id, Integer[] ids) {
		if(id!=null && ids!=null && ids.length>0){
			ProcessGzstore pgz =	(ProcessGzstore) totalDao.get(ProcessGzstore.class, id);
			for (int i = 0; i < ids.length; i++) {
				ProcessAndMeasuring pam =(ProcessAndMeasuring) totalDao.getObjectByCondition(" from ProcessAndMeasuring where processId=? and measuringId =? ", 
						id,ids[i]);
				if(pam == null){
					Measuring mea =	(Measuring) totalDao.get(Measuring.class, ids[i]);
					 pam = new ProcessAndMeasuring(id, ids[i], pgz.getProcessName(),
							mea.getNumber(), mea.getMatetag(), mea.getMeasuring_no());
					totalDao.save(pam);
				}
			}
			return "true";
		}
		return "error";
	}

	@Override
	public String delPAM(Integer id) {
		if(id!=null){
			ProcessAndMeasuring pam =	(ProcessAndMeasuring) totalDao.get(ProcessAndMeasuring.class, id);
			List<ProcessAndMeasuring> pamList = totalDao.query(" from ProcessAndMeasuring where processId =? and measuringId <>? ", pam.getProcessId(),pam.getMeasuringId());
			return 	totalDao.delete(pam)+"";
		}
		return null;
	}
	
}
