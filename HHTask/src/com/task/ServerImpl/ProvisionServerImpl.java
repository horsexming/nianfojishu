package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.ProvisionServer;
import com.task.entity.Provision;

/***
 * 合同条款Server实现类
 * 
 * @author 刘培
 * 
 */
public class ProvisionServerImpl implements ProvisionServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加条款
	public boolean addProvision(Provision provision) {
		if (provision != null) {
			String hql = "from Provision where " +
			" provisionStatus = 'public' order by num";
			int count = totalDao.getCount(hql);
			if(provision.getNum()== null||provision.getNum()>count){
				provision.setNum(count+1);
			}else if(provision.getNum()<1){
				provision.setNum(1);
				List list  = totalDao.query(hql);
				for(int i=0;i<list.size();i++){
					Provision pro = (Provision) list.get(i);
					pro.setNum(pro.getNum()+1);
					totalDao.update(pro);
				};
			}else{
				String sql = "from Provision where num > ? and" +
				" provisionStatus = 'public' order by num";
				List list  = totalDao.query(sql, provision.getNum()-1);
				for(int i=0;i<list.size();i++){
					Provision pro = (Provision) list.get(i);
					pro.setNum(pro.getNum()+1);
					totalDao.update(pro);
				};
			}
			
			return totalDao.save(provision);
		}
		return false;
	}

	// 删除条款
	public boolean delProvision(Provision provision) {
		if (provision != null) {
			String hql = "from Provision where num > ? and" +
			" provisionStatus = 'public' order by num";
			List list  = totalDao.query(hql, provision.getNum());
			for(int i=0;i<list.size();i++){
				Provision pro = (Provision) list.get(i);
				pro.setNum(pro.getNum()-1);
				totalDao.update(pro);
			};
			return totalDao.delete(provision);
		}
		return false;
	}

	// 修改条款
	public boolean updateProvision(Provision provision) {
		if (provision != null) {
			return totalDao.update(provision);
		}
		return false;
	}
	public boolean updateProvision(Provision provision,int p){
		if(provision != null){
			String hql = "from Provision where " +
			" provisionStatus = 'public' order by num";
			int count = totalDao.getCount(hql);
			if(provision.getNum()== null||provision.getNum()>count){
				provision.setNum(count);
				String sql = "from Provision where num > ? and" +
				" provisionStatus = 'public' order by num";
				List list  = totalDao.query(sql,p);
				for(int i=0;i<list.size();i++){
					Provision pro = (Provision) list.get(i);
					pro.setNum(pro.getNum()-1);
					totalDao.update(pro);
				};
			}else if(provision.getNum()<1){
				provision.setNum(1);
				String sql = "from Provision where num < ? and" +
				" provisionStatus = 'public' order by num";
				List list  = totalDao.query(sql,p);
				for(int i=0;i<list.size();i++){
					Provision pro = (Provision) list.get(i);
					pro.setNum(pro.getNum()+1);
					totalDao.update(pro);
				};
			}else{
				String sql = "from Provision where num > ?and num<? and" +
				" provisionStatus = 'public' order by num";
				if(p< provision.getNum()){
					List list  = totalDao.query(sql,p, provision.getNum()+1);
					for(int i=0;i<list.size();i++){
						Provision pro = (Provision) list.get(i);
						pro.setNum(pro.getNum()-1);
						totalDao.update(pro);
					};
				}else if(p> provision.getNum()){
					List list  = totalDao.query(sql, provision.getNum()-1,p);
					for(int i=0;i<list.size();i++){
						Provision pro = (Provision) list.get(i);
						pro.setNum(pro.getNum()+1);
						totalDao.update(pro);
					};
				}
			}
			return totalDao.update(provision);
		}
		return false;
	}

	// 查询所有条款(分页+条件查询)
	@SuppressWarnings("unchecked")
	public Object[] findAllProvision(Provision provision, int pageNo,
			int pageSize) {
		if (provision == null) {
			provision = new Provision();
		}
		String hql = totalDao.criteriaQueries(provision, null, null)+" order by num";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	// 查询所有测试单
	@SuppressWarnings("unchecked")
	public Object[] findAllProvisionMsd(Provision provision, int pageNo,
			int pageSize) {
		if (provision == null) {
			provision = new Provision();
		}
		String hql = totalDao.criteriaQueries(provision, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}


	// 通过Id查询条款
	public Provision findProvisionById(Integer id) {
		if (id != null && id > 0) {
			return (Provision) totalDao.getObjectById(Provision.class, id);
		}
		return null;
	}
	// 通过num查询条款
	public Provision findUpProvisionByNum(Integer num){
		if (num != null && num > 0) {
			String hql = "from Provision where num = ? and" +
					" provisionStatus = 'public'";
			List list  = totalDao.query(hql, num);
			if(list.size()>0){
				Provision provision = (Provision) list.get(0);
				return provision;
			}
		}
		return null;
	}

	
}
