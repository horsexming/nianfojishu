package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.DJNRServer;
import com.task.entity.DJNR;
import com.task.entity.Machine;

public class DJNRServerImpl implements DJNRServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(DJNR djnr) {
		if(djnr!=null){
		return	totalDao.save(djnr);
		}
		return false;
	}

	@Override
	public boolean del(DJNR djnr) {
		if(djnr!=null){
			djnr=(DJNR) totalDao.get(DJNR.class, djnr.getId());
			Set<Machine> machine=djnr.getMachine();
			for (Machine machine2 : machine) {
				machine2.getDjnr().remove(djnr);
				Set<DJNR> setdjnr=machine2.getDjnr();
				boolean bool = true;
				for (DJNR djnr2 : setdjnr) {
					bool = false;
				}
				if(bool){
					machine2.setIsdj("否");
				}
				totalDao.update(machine2);
			}
			
			return totalDao.delete(djnr);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findDJNRByCondition(DJNR djnr, int pageNo,
			int pageSize) {
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		String hql=	totalDao.criteriaQueries(djnr, null);
		int count=totalDao.getCount(hql);
		List<DJNR> djnrList=(List<DJNR>)totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, djnrList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean update(DJNR djnr) {
		if(djnr!=null){
			return	totalDao.update(djnr);
		}
		return false;
	}

	@Override
	public List<DJNR> findAllDJNR(int pageNo, int pageSize) {
		String hql="from DJNR";
		return (List<DJNR>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public DJNR finddjnrbyid(DJNR djnr) {
		djnr=(DJNR) totalDao.get(DJNR.class,djnr.getId());
		return djnr;
	}
	//根據 設備ID和得到所多應得點檢內容
	@SuppressWarnings("null")
	@Override
	public List getdjnrbyId1(Integer id) {
		if(id!=null&&id>0){
			Machine machine=(Machine) totalDao.get(Machine.class, id);
			Set<DJNR> djnrSet=machine.getDjnr();
			List djnrList= new ArrayList();
			for (DJNR djnr : djnrSet) {
				Object[] obj={djnr.getId(),djnr.getNr()};
				djnrList.add(obj);
			}
			return djnrList;
		}
		return null;
	}
	public List getdjnrbyId(Integer id) {
		if(id!=null&&id>0){
			Machine machine=(Machine) totalDao.get(Machine.class, id);
			Set<DJNR> djnrSet=machine.getDjnr();
			List djnrList= new ArrayList();
			for (DJNR djnr : djnrSet) {
				djnrList.add(djnr);
			}
			return djnrList;
		}
		return null;
	}

	
	@Override
	public boolean bdmachine(DJNR djnr) {
		 
			if(djnr!=null){
				DJNR olddjnr=(DJNR) totalDao.get(Machine.class, djnr.getId());
				List<Machine> machineList=olddjnr.getMachineList();
				Set<Machine> machine=new HashSet<Machine>();
				if(machineList!=null&&machineList.size()>0){
					for (int i = 0; i < machineList.size(); i++) {
						machine.add(machineList.get(i));
					}
					olddjnr.setMachine(machine);
				}
				return totalDao.update(olddjnr);
			}
			return false;
	}

	@Override
	public List<Machine> findbdmachine(Integer id) {
		if(id!=null && id>0){
			DJNR djnr=(DJNR) totalDao.get(DJNR.class, id);
			if(djnr!=null){
				Set<Machine> machine=	djnr.getMachine();
				List<Machine> machineList=new ArrayList<Machine>();
				for (Machine machine2 : machine) {
					machineList.add(machine2);
				}
				return machineList;
			}
		}
		return null;
	}

	@Override
	public List<Machine> findmachine(Integer id) {
		if(id!=null&&id>0){
			DJNR djnr=(DJNR) totalDao.get(DJNR.class, id);
			List<Integer> list=null;
			if(djnr!=null){
				Set<Machine> machine=	djnr.getMachine();
				list =new ArrayList<Integer>();
				for (Machine machine2 : machine) {
					list.add(machine2.getId());
				}
				
			}
			String hql="from Machine where id not in("+list+")";
			return totalDao.find(hql);
			
		}
		return null;
	}
}
