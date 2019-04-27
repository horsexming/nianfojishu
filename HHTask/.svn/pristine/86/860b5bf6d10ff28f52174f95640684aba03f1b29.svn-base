package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.QexamineServer;
import com.task.entity.Qexamine;
import com.task.entity.Stylebook;
import com.task.util.Util;

public class QexamineServerImpl implements QexamineServer{
	private TotalDao totalDao;
	
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	@Override
	//添加质量审核表
	public void saveQexamine(Qexamine qexamine) {
		// TODO Auto-generated method stub
		qexamine.setAuditdate(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		qexamine.setWritename(Util.getLoginUser().getName());
		qexamine.setCheckstatus("未审批");
		qexamine.setStatus("初始化");
		totalDao.save(qexamine);
	}

	@Override
	//添加样本
	public void saveStyle(Stylebook stylebook){
		stylebook.setNumb(Util.getDateTime("yyyyMMddHHmmss"));
		
		//对质量审查表的修改
		if(stylebook.getNature().equals("样本")){
			int a = stylebook.getQid(); 
			String hql ="from Stylebook where qid = ? and nature = ?";
			int b = totalDao.getCount(hql,a,stylebook.getNature())+1;
			Qexamine qexamine = this.qexamineFind(stylebook.getQid());		
			qexamine.setTotalstyle(" "+b);
			totalDao.save(qexamine);
		}
		totalDao.save(stylebook);
	}
	//查询质量审查表列表数量
	@Override
	public Integer qexamineCount() {		
		String hql="from Qexamine";
		return this.totalDao.getCount(hql);
	}
	//分页查询全部质量审查表列表
	@Override
	public List qexaminelist(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Qexamine order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}
	public List qexamineFindList(){
		String hql = "from Qexamine order by id desc";
		List list = totalDao.find(hql);
		return list;
	}
	//单个查询质量审查表列表
	@Override
	public Qexamine qexamineFind(int id) {
		//String hql = "from Qexamine where id= ? ";
		//Qexamine qexamine = (Qexamine)totalDao.getObjectByCondition(hql,id);
		Qexamine qexamine = (Qexamine)totalDao.getObjectById(Qexamine.class, id);
		return qexamine;
	}
	//查询单表中全部样本
	@Override
	public List stylebookList(int qid) {
		String hql = "from Stylebook where qid = ? order by id desc";
		List list = totalDao.query(hql,qid);
		return list;
	}
	//修改计量审查表
	@Override
	public void updateQexamine(Qexamine qexamine) {
		Qexamine q = this.qexamineFind(qexamine.getId());
		q.setCustomername(qexamine.getCustomername());
		q.setProductname(qexamine.getProductname());
		q.setProductdraw(qexamine.getProductdraw());
		q.setReferencestandard(qexamine.getReferencestandard());
		q.setBatchsampling(qexamine.getBatchsampling());
		q.setRemarks(qexamine.getRemarks());
		totalDao.update(q);		
	}

	@Override
	public void deleteQexamine(Qexamine qexamine) {		
		String hql = "delete Stylebook where qid = ?";
		totalDao.createQueryUpdate(hql, null, qexamine.getId());
		totalDao.delete(qexamine);	
	}
	//单个样本查询
	@Override
	public Stylebook stylebookFind(int id) {
		// TODO Auto-generated method stub
		return (Stylebook)totalDao.getObjectById(Stylebook.class, id);
	}
	//删除单个样本
	@Override
	public void deleteStylebook(Stylebook stylebook) {
		// TODO Auto-generated method stub
		totalDao.delete(stylebook);
		if(stylebook.getNature().equals("样本")){
			int a = stylebook.getQid(); 
			String hql ="from Stylebook where qid = ? and nature = ?";
			int b = totalDao.getCount(hql,a,stylebook.getNature());
			Qexamine qexamine = this.qexamineFind(stylebook.getQid());	
			qexamine.setTotalstyle(""+b);
			totalDao.save(qexamine);
		}
	}
	//修改单个样本
	@Override
	public void updateStyleboook(Stylebook stylebook) {
		// TODO Auto-generated method stub
		totalDao.update(stylebook);
	}
	
}
