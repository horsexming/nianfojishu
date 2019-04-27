package com.task.ServerImpl.checktype;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.checktype.CheckNoteServer;
import com.task.ServerImpl.IntegralServerDaoImpl;
import com.task.entity.DeptNumber;
import com.task.entity.Integral;
import com.task.entity.Users;
import com.task.entity.checktype.CheckNote;
import com.task.util.Util;

public class CheckNoteServerImpl implements CheckNoteServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public Map<Integer, Object> findAll(CheckNote checkNote, int pageNo,
			int pageSize) {
		if (checkNote == null) {
			checkNote= new CheckNote();
		}
		String hql = null;
		hql = totalDao.criteriaQueries(checkNote,null);
		hql+=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public boolean dock(CheckNote checkNote,Integral i) {
		if(i!=null){
			int s = Integer.parseInt(checkNote.getCheckType().getMaxScore());
			String r = "6s"+checkNote.getCheckType().getType()+checkNote.getCheckType().getName();
			IntegralServerDaoImpl.kouJiFen(r,-s, i);
			return true;
		}
		return false;
	}
	@Override
	public boolean Userbind(Integer id, int[] arrayId) {
		if (id != null) {
			Users user = (Users) totalDao.get(Users.class, id);
			Set<DeptNumber> dSet = new HashSet<DeptNumber>();
			if (user != null && arrayId != null && arrayId.length > 0) {
				for (int i = 0; i < arrayId.length; i++) {
					DeptNumber d = (DeptNumber) totalDao.get(DeptNumber.class,
							arrayId[i]);
					dSet.add(d);
				}
			} 
			user.setDeptNumber(dSet);
			return totalDao.update(user);
		}
		return false;
	}
	
	public List<Users> findUser(String  id){
		Integer userId =Integer.parseInt(id);
		String hql="from Users where dept in( select d.dept from DeptNumber d  join d.user u where u.id="
					+ userId + ")"+"and onwork IN ('在职','实习','试用') and internal = '是'";
		List<Users> list = totalDao.find(hql);
		return list;
	}
	@Override
	public List<Integer> findDept(Integer userId) {
		String hql="select d.id from DeptNumber d  join d.user u where u.id="+ userId + ")";
		List<Integer> list = totalDao.find(hql);
		return list;
	}
	@Override
	public boolean update(CheckNote checkNote) {
		return totalDao.update(checkNote);
	}
	@Override
	public CheckNote findById(Integer id) {
		return (CheckNote)totalDao.get(CheckNote.class,id);
	}
	@Override
	public Integral findIntegral(String id) {
		String hql1 = "From  Integral where integrcode =?";
		Integral i =(Integral)totalDao.getObjectByCondition(hql1,id);
		return i;
	}

	@Override
	public Map<Integer, Object> findUserByfuze(CheckNote checkNote, int pageNo,
			int pageSize ,String id,String leader) {
		if (checkNote == null) {
			checkNote= new CheckNote();
		}
		String sql =null;
		if("leader".equals(leader)){
			 sql = "loginCode ="+id;
		}else{
			 sql = "firstPersonCode ="+id;
		}
		String hql = totalDao.criteriaQueries(checkNote,sql,null);
		hql+=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	//
	@Override
	public Map<Integer, Object> findSysAll(CheckNote checkNote, int pageNo,
			int pageSize) {
		if (checkNote == null) {
			checkNote= new CheckNote();
		}
		String sql = "checkType.type='系统异常'";
		String hql = totalDao.criteriaQueries(checkNote,sql,null);
		hql+=" order by id desc";
		System.out.println(hql);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public Map<Integer, Object> findbyfuze(CheckNote checkNote, int pageNo,
			int pageSize) {
		if (checkNote == null) {
			checkNote= new CheckNote();
		}
		String sql ="firstPersonCode ="+Util.getLoginUser().getCode()+"and checkType.type='系统异常'";
		String hql = totalDao.criteriaQueries(checkNote,sql,null);
		hql+=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	@Override
	public Map<Integer, Object> findbytijiao(CheckNote checkNote, int pageNo,
			int pageSize) {
		if (checkNote == null) {
			checkNote= new CheckNote();
		}
		String sql ="loginCode ="+Util.getLoginUser().getCode()+"and checkType.type='系统异常'";
		String hql = totalDao.criteriaQueries(checkNote,sql,null);
		hql+=" order by id desc";
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	public boolean startHandle(Integer id,String status){
		CheckNote checkNote = (CheckNote)totalDao.get(CheckNote.class,id);
		if(checkNote!=null){
			if("kaishi".equals(status)){
				checkNote.setStatus("处理中");
			}else if("wancheng".equals(status)){
				checkNote.setStatus("处理完成");
			}else if("queding".equals(status)){
				checkNote.setStatus("确定");
			}
			boolean b = totalDao.update(checkNote);
			if(b){
				//发送信息
				return b;
			}
		}
		return false;
	}
	
}