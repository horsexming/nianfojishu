package com.task.ServerImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.MachineDayDjServer;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;
import com.task.entity.MachineMonthDj;
import com.task.util.Util;

public class MachineDayDjServerImpl implements MachineDayDjServer{

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public boolean add(MachineDayDj mdd) {
		
		
		if(mdd!=null){
			
		}
		return false;
	}

	@Override
	public boolean del(MachineDayDj mdd) {
			if(mdd!=null){
			return	totalDao.delete(mdd);
			}
		return false;
	}

	@Override
	public Map<Integer, Object> findMDDByCondition(MachineDayDj mdd,
			int pageNo, int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(mdd, null);
		int count=totalDao.getCount(hql);
		List<MachineDayDj> djnrList=(List<MachineDayDj>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, djnrList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean update(MachineDayDj mdd) {
			if(mdd!=null){
			return	totalDao.update(mdd);
			}
		return false;
	}

	@Override
	public List<MachineDayDj> findmddbymonth(Integer id, String month) {
		if(id!=null){
			if(month==null||month==""){
				month=Util.getDateTime("yyyy-MM");
			}
			String hql="from MachineDayDj where machine_id="+id+" and machineMonth="+month;
			return 	(List<MachineDayDj>)totalDao.find(hql);
		}
		return null;
	}

	@Override
	public List<MachineDayDj> findmonthstatus(String machineDjnr, String month,Integer id) {
		if(id!=null&&id>0&&machineDjnr!=null&&machineDjnr.length()>0){
			if(month==null||month==""){
				month=Util.getDateTime("yyyy-MM");
			}
			String day=Util.getDateTime("yyyy-MM-dd");
			String hql="select dj_status,machineDay  from MachineDayDj where  machine_id="+id+" and machine_djnr='"+machineDjnr+"' and machineMonth='"+month+"'";
			List<MachineDayDj>	mddList=totalDao.find(hql);
			return mddList;
		}
		return null;
	}

	@Override
	public String jisuantest() {
		List<MachineMonthDj>  listmmdj =totalDao.query(" from MachineMonthDj");
		for (MachineMonthDj mmdj : listmmdj) {
			String hql_mddj = " from MachineDayYZSJ where machine_id = ? and machineMonth =?";
			List<MachineDayYZSJ>  listmddj =	totalDao.query(hql_mddj, mmdj.getMachine_id(),mmdj.getMachineMonth());
			Double machineZYZSJ2 = 0d;
			for (MachineDayYZSJ mddj : listmddj) {
				if(mddj.getMachineYZSJ2()!=null){
					machineZYZSJ2+=mddj.getMachineYZSJ2();
				}else if(mddj.getMachineYZSJ()!=null){
					machineZYZSJ2+=mddj.getMachineYZSJ();
				}
			}
			mmdj.setMachineZYZSJ2(machineZYZSJ2);
			mmdj.setMachineZTZSJ((8*21.5)-machineZYZSJ2);
			mmdj.setZjiadonglv(((machineZYZSJ2/(8*21.5))*100)+"%");
			totalDao.update(mmdj);
		}
		return null;
	}

	
}
