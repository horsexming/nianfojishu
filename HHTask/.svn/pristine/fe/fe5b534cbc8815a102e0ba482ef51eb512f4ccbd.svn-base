package com.task.ServerImpl.sop.fhyp;

import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.fhyp.FanghuYongpinServer;
import com.task.entity.sop.fhyp.FanghuYongpin;
import com.task.entity.sop.fhyp.FanghuYongpinLingqu;
import com.task.entity.sop.fhyp.FanghuYongpinQuanxian;

public class FanghuYongpinImpl implements FanghuYongpinServer{
	private TotalDao totalDao;
	
	/**
	 * 添加防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpin addFanghuYongpin(FanghuYongpin fanghuYongpin){
		boolean result=totalDao.save(fanghuYongpin);
		if(result){
			return fanghuYongpin;
		}else{
			return null;
		}
	}
	/**
	 * 删除防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpin deleteFanghuYongpin(FanghuYongpin fanghuYongpin){
		boolean result=totalDao.delete(fanghuYongpin);
		if(result){
			return fanghuYongpin;
		}else{
			return null;
		}
	}
	/**
	 * 更新防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpin updateFanghuYongpin(FanghuYongpin fanghuYongpin){
		FanghuYongpin fanghuYongpinTemp=this.getFanghuYongpinById(fanghuYongpin.getId());
		if(fanghuYongpinTemp!=null){
			/**防护用品名字*/
			String fanghuYongpinName=fanghuYongpin.getFanghuYongpinName();
			/**防护用品规格*/
			String fanghuYongpinGuige=fanghuYongpin.getFanghuYongpinGuige();
			if(fanghuYongpinName!=null && !"".equals(fanghuYongpinName)){
				fanghuYongpinTemp.setFanghuYongpinName(fanghuYongpinName);
			}
			if(fanghuYongpinGuige!=null && !"".equals(fanghuYongpinGuige)){
				fanghuYongpinTemp.setFanghuYongpinGuige(fanghuYongpinGuige);
			}
			boolean result=this.totalDao.update(fanghuYongpinTemp);
			if(result){
				return fanghuYongpinTemp;
			}
		}
		return null;
	}
	
	/**
	 * 获得防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpin getFanghuYongpinById(Integer id){
		if(id!=null){
			return (FanghuYongpin)totalDao.getObjectById(FanghuYongpin.class, id);
		}
		return null;
	}
	/**
	 * 获得防护用品集合ForSelect
	 * @param fanghuYongpin
	 * @return
	 */
	public List getFanghuYongpinListForSelect(){
		String hql="from FanghuYongpin f where 1=1 and f.parentId is null";
		List list = totalDao.find(hql);
		return list;
	}
	/**
	 * 获得防护用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuYongpinList(FanghuYongpin fanghuYongpin,int pageNo,int pageSize){
		String hql="from FanghuYongpin f where 1=1 and f.parentId is null";
		if(fanghuYongpin!=null){
			
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	/**
	 * 获得防护用品规格集合根据父ID
	 * @param fanghuYongpin
	 * @return
	 */
	public List getFanghuYongpinGuigeListByParentId(Integer parentId){
		String hql="from FanghuYongpin f where 1=1 and f.parentId=?";
		return this.totalDao.find(hql, new Object[]{parentId});
	}
	/**
	 * 添加防护用品权限
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinQuanxian addFanghuYongpinQuanxian(FanghuYongpinQuanxian fanghuYongpinQuanxian){
		boolean result=totalDao.save(fanghuYongpinQuanxian);
		if(result){
			return fanghuYongpinQuanxian;
		}else{
			return null;
		}
	}
	/**
	 * 删除防护用品权限
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinQuanxian deleteFanghuYongpinQuanxian(FanghuYongpinQuanxian fanghuYongpinQuanxian){
		boolean result=totalDao.delete(fanghuYongpinQuanxian);
		if(result){
			return fanghuYongpinQuanxian;
		}else{
			return null;
		}
	}
	/**
	 * 更新防护用品权限
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinQuanxian updateFanghuYongpinQuanxian(FanghuYongpinQuanxian fanghuYongpinQuanxian){
		FanghuYongpinQuanxian fanghuYongpinQuanxianTemp=this.getFanghuYongpinQuanxianById(fanghuYongpinQuanxian.getId());
		if(fanghuYongpinQuanxianTemp!=null){
			/**领取周期*/
			String lingquZhouqi=fanghuYongpinQuanxian.getLingquZhouqi();
			/**是否可以领取 yes no*/
			String lingquEnable=fanghuYongpinQuanxian.getLingquEnable();
			if(lingquZhouqi!=null && !"".equals(lingquZhouqi)){
				fanghuYongpinQuanxianTemp.setLingquZhouqi(lingquZhouqi);
			}
			if(lingquEnable!=null && !"".equals(lingquEnable)){
				fanghuYongpinQuanxianTemp.setLingquEnable(lingquEnable);
			}
			boolean result=this.totalDao.update(fanghuYongpinQuanxianTemp);
			if(result){
				return fanghuYongpinQuanxianTemp;
			}
		}
		return null;
	}
	
	/**
	 * 获得防护用品权限
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinQuanxian getFanghuYongpinQuanxianById(Integer id){
		if(id!=null){
			return (FanghuYongpinQuanxian)totalDao.getObjectById(FanghuYongpinQuanxian.class, id);
		}
		return null;
	}
	/**
	 * 获得防护用品集合集合
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuYongpinQuanxianList(FanghuYongpinQuanxian fanghuYongpinQuanxian,int pageNo,int pageSize){
		String hql="from FanghuYongpinQuanxian f where 1=1 and f.lingquUserId in (select max(ff.id) from FanghuYongpinQuanxian ff group by ff.lingquUserId)";
		if(fanghuYongpinQuanxian!=null){
			Integer lingquUserId=fanghuYongpinQuanxian.getLingquUserId();
			if(lingquUserId!=null){
				hql+=" and f.lingquUserId='"+lingquUserId+"'";
			}
		}
		hql+=" order by f.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	/**
	 * 添加防护用品领取记录
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinLingqu addFanghuYongpinLingqu(FanghuYongpinLingqu fanghuYongpinLingqu){
		boolean result=totalDao.save(fanghuYongpinLingqu);
		if(result){
			return fanghuYongpinLingqu;
		}else{
			return null;
		}
	}
	/**
	 * 删除防护用品领取记录
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinLingqu deleteFanghuYongpinLingqu(FanghuYongpinLingqu fanghuYongpinLingqu){
		boolean result=totalDao.delete(fanghuYongpinLingqu);
		if(result){
			return fanghuYongpinLingqu;
		}else{
			return null;
		}
	}
	/**
	 * 更新防护用品领取记录
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinLingqu updateFanghuYongpinLingqu(FanghuYongpinLingqu fanghuYongpinLingqu){
		FanghuYongpinLingqu fanghuYongpinLingquTemp=this.getFanghuYongpinLingquById(fanghuYongpinLingqu.getId());
		if(fanghuYongpinLingquTemp!=null){
			boolean result=this.totalDao.update(fanghuYongpinLingquTemp);
			if(result){
				return fanghuYongpinLingquTemp;
			}
		}
		return null;
	}
	
	/**
	 * 获得防护用品领取记录
	 * @param fanghuYongpin
	 * @return
	 */
	public FanghuYongpinLingqu getFanghuYongpinLingquById(Integer id){
		if(id!=null){
			return (FanghuYongpinLingqu)totalDao.getObjectById(FanghuYongpinLingqu.class, id);
		}
		return null;
	}
	/**
	 * 获得防护用品领取记录集合
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuYongpinLingquList(FanghuYongpinLingqu fanghuYongpinLingqu,int pageNo,int pageSize){
		String hql="select distinct(*)  from FanghuYongpinLingqu f where 1=1 ";
		if(fanghuYongpinLingqu!=null){
			
		}
		hql+=" order by f.id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	/**
	 * 获得防护用品领取用户集合
	 * @param fanghuYongpin
	 * @return
	 */
	public Object[] getFanghuYongpinLingquUserList(FanghuYongpinLingqu fanghuYongpinLingqu,int pageNo,int pageSize){
		String hql="from FanghuYongpinQuanxian f where 1=1 and f.lingquUserId in (select max(ff.id) FanghuYongpinQuanxian ff group by ff.lingquUserId)";
		//String hql="from dis FanghuYongpinLingqu f where 1=1";
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
