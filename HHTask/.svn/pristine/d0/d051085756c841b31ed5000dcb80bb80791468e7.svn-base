package com.task.ServerImpl.bp;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.bp.TempletMonitorService;
import com.task.entity.bp.Templet;
import com.task.entity.bp.TempletMonitor;

public class TempletMonitorServiceImpl implements TempletMonitorService {
	private TotalDao totalDao;
	
	//更新
	public boolean update(Templet templet){
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("update")){
			return false;
		}
		TempletMonitor tm = new TempletMonitor();
		BeanUtils.copyProperties(templet, tm, new String[]{"id","parentId"});
		tm.settId(templet.getId());
		tm.setParentId(templet.getParentId2());
		templet.setMonitorType("update");
		totalDao.update(templet);
		tm.setReason("update");
		totalDao.save(tm);
		return true;
	}

	//同意
	public boolean agree(Templet templet) {
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("update")){//如果修改同意，保留
			templet.setMonitorType("");
			return totalDao.update(templet);
		}
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("add")){//添加同意，保留
			templet.setMonitorType("");
			return totalDao.update(templet);
		}
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("delete")){//删除同意，删除此记录
			String hql = "from Templet where id=?";
			Templet t = (Templet) totalDao.getObjectByCondition(hql, templet.getId());
			t.setParent(null);
			return totalDao.delete(t);
		}
		return false;
	}

	//不同意
	public boolean disagree(Templet templet){
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("update")){//更新如果不同意，再替换回来
			TempletMonitor templetMonitor = this.get(templet);
			templet.setAdvPosition(templetMonitor.getAdvPosition());
			templet.setCategory(templetMonitor.getCategory());
			templet.setDirections(templetMonitor.getDirections());
			templet.setModels(templetMonitor.getModels());
			templet.setName(templetMonitor.getName());
			templet.setPartsNumber(templetMonitor.getPartsNumber());
			templet.setResPerson(templetMonitor.getResPerson());
			templet.setSpecification(templetMonitor.getSpecification());
			templet.setUnit(templetMonitor.getUnit());
			templet.setMonitorType("");
			return totalDao.update(templet);
		}
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("add")){//添加的子节点如果不同意，直接删除
			String hql = "from Templet where id=?";
			Templet t = (Templet) totalDao.getObjectByCondition(hql, templet.getId());
			t.setParent(null);
			return totalDao.delete(t);
		}
		if(templet.getMonitorType()!= null && templet.getMonitorType().equals("delete")){//删除如果不同意，就取消"删除"的状态
			templet.setMonitorType("");
			totalDao.update(templet);
			return true;
		}
		return false;
	}

	private TempletMonitor get(Templet templet){
		String hql = "select max(id) from TempletMonitor where tId=?";
		TempletMonitor templetMonitor = (TempletMonitor)totalDao.getObjectByCondition(hql, templet.getId());
		return templetMonitor;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}


}
