package com.task.ServerImpl.bbs;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.bbs.AffixServer;
import com.task.entity.bbs.Affix;




public class AffixServerImpl implements AffixServer{
	private TotalDao totalDao;
	
	//添加附件记录
	public Affix addAffix(Affix affix) {
		boolean result= totalDao.save(affix);
		if(result){
			return affix;
		}else {
			return null;
		}
	}
	
	//删除附件记录
	public String deleteAffix(Affix affix) {
		boolean result= totalDao.delete(affix);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新附件记录
	public String updateAffix(Affix affix) {
		boolean result= totalDao.update(affix);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得附件记录
	public Affix getAffixById(Integer id) {
		if(id != null){
			return (Affix)totalDao.getObjectById(Affix.class, id);
		}
		return null;
	}
	
	//获得附件记录集合
	public Object[] findAllAffix(Map map, int pageNo, int pageSize) {
		String hql = "from Affix a where 1 = 1";
		if(map!=null){
		}
		hql+=" order by a.createDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
}
