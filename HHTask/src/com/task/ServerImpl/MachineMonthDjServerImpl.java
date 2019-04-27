package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.MachineMonthDjServer;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.MachineDayDj;
import com.task.entity.MachineDayYZSJ;
import com.task.entity.MachineMonthDj;
import com.task.entity.sop.ProcessInfor;
import com.task.util.Util;

public class MachineMonthDjServerImpl implements MachineMonthDjServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

		
	@Override
	public boolean add(MachineMonthDj mdj) {
		if(mdj!=null){
			return	totalDao.save(mdj);
		}
		return false;
	}
	@Override
	public boolean del(MachineMonthDj mdj) {
			if(mdj!=null){
				return totalDao.delete(mdj);
			}
		return false;
	}

	@Override
	//根据设备id 和月份查询出对应的设备每月点检汇总;
	public MachineMonthDj findallbyid(Integer id,String month) {
		if(id!=null&&id>0){
			String hql="from MachineMonthDj where machine_id=? ";
			if(month!=null && month.length()>0){
				hql +=" and machineMonth = '"+month+"'";
			}
			
			return 	(MachineMonthDj) totalDao.getObjectByCondition(hql, id);
		}
		return null;
	}
	@Override
	public boolean update(MachineMonthDj mdj) {
			if(mdj!=null){
			return	totalDao.update(mdj);
			}
		return false;
	}

	@Override
	public List<ProcessInfor> getprocess(Integer id, String month) {
		List<String> nameList=new ArrayList<String>();
		if(id!=null&&id>0){
			if (month ==null || "".equals(month)) {
				month = Util.getDateTime("yyyy-MM");
			}
			String firsttime=month+"-01"+" 00:00:00";
			String subtime=month+"-"+Util.getMonthofday(month)+" 23:59:59";
			String hql="select submitDate,operatorDept,usernames from" +
					" ProcessInfor where shebeiNo=(select no from Machine where id="+id+")" +
					" and gongwei=(select workPosition from Machine where id="+id+")" +
					" and operatorDept is not null and firstApplyDate>='"+firsttime
					+"' and submitDate<='"+subtime+"'";
			return	totalDao.find(hql);
			
			
		}
		return null;
	}

	@Override
	public List<MachineDayDj> getdjnrofmonth(Integer id, String month) {
		List<MachineDayDj> mddList=null;
		if(id!=null&&id>0){
			if (month ==null || "".equals(month)) {
				month = Util.getDateTime("yyyy-MM");
			}
			String hql="from MachineDayYZSJ where machine_id="+id+" and machineMonth='"+month+"'";
			List<MachineDayYZSJ> list=totalDao.find(hql);
			mddList=new ArrayList<MachineDayDj>();
			if(list!=null&&list.size()>0){
				for (MachineDayYZSJ mdy : list) {
					Set<MachineDayDj> mddSet=mdy.getMdd();
					for (MachineDayDj mdd : mddSet) {
						mddList.add(mdd);
					}
				}
			}
		}
		return mddList;
	}

	@Override
	public List<MachineDayDj> getmddofmonth(Integer id, String month) {
		if(id!=null&&id>0){
			if (month ==null || "".equals(month)) {
				month = Util.getDateTime("yyyy-MM");
			}
			String hql="from MachineDayDj where machine_id="+id+" machineMonth='"+month+"'";
			List<MachineDayDj> mddList=totalDao.find(hql);
			if(mddList!=null&&mddList.size()>0){
				return mddList;
			}
		}
		return null;
	}

	@Override
	public Machine getmachine(Integer id) {
		if(id!=null && id>0){
			return  (Machine) totalDao.get(Machine.class,id); 
		}
		return null;
	}
	
	

	
}
