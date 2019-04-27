package com.task.ServerImpl.sys;

import java.util.ArrayList;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sys.UserDeptServer;
import com.task.entity.DeptNumber;
import com.task.entity.DeptNumberVo;

public class UserDeptServerImpl implements UserDeptServer{

	private TotalDao totalDao;
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	

	@Override
	public List<DeptNumberVo> getDeptVos(Integer id) {
		// TODO Auto-generated method stub
		List<Integer> deptIds=totalDao.query("select deptId from UserDept where userId = "+id);
		List<DeptNumber> all=totalDao.query("from DeptNumber");
		if(all.size()>0){
			List<DeptNumberVo> vos=new ArrayList<DeptNumberVo>();
			for(DeptNumber d:all){
				DeptNumberVo dVo=new DeptNumberVo();
				dVo.SetDeptContext(d);
				if(d.getId()!=null&&deptIds.contains(d.getId())){
					dVo.setIsHad(true);
				}else{
					dVo.setIsHad(false);
				}
				vos.add(dVo);
			}
			return vos;
		}
		
		return null;
	}




}
