package com.task.ServerImpl.yw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.yw.IBusinessService;
import com.task.Server.yw.IExamineFlowService;
import com.task.entity.Business;

/**
 * @Title: BusinessServiceImpl.java
 * @Package com.task.ServerImpl.ywr
 * @Description: TODO 业务表实现类
 * @author 曾建森
 * @date 2012-10-29 上午09:51:59
 * @version V1.0rrttttrrr
 */
public class BusinessServiceImpl implements IBusinessService {
	private IExamineFlowService efs;
	private TotalDao totalDao;
	/**
	 * @Title: add
	 * @Description: 添加业务记录
	 * @param @param bu 
	 * @return void
	 * @throws
	 */
	public void add(Business bu) {
		totalDao.save(bu);
	}
	/**
	 * @Title: delById
	 * @Description: 根据ID删除指定记录
	 * @param id 
	 * @return void
	 * @throws
	 */
	public void delById(int id) {
		Business bu = (Business) totalDao.getObjectById(Business.class, id);
		totalDao.delete(bu);
	}
	/**
	 * @Title: update
	 * @Description: 修改记录
	 * @param bu 
	 * @return void
	 * @throws
	 */
	public boolean update(Business bu) {
		return totalDao.update(bu);
	}
	/**
	 * @Title: getBusinessById
	 * @Description: TODO 根据ID查询业务
	 * @param @param id
	 * @return Business
	 * @throws
	 */
	public Business getBusinessById(int id){
		return (Business) totalDao.getObjectById(Business.class, id);
		
	}
	/**
	 * @Title: queryAllBusiness
	 * @Description: 查询所有业务
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return List
	 * @throws
	 */
	public Object[] queryAllBusiness(int pageNo,int pageSize,String flow) {
		String hql = "from Business b where b.status = 'NO'";
		if(flow != null){
			if(flow.equals("A")){
				hql+=" and b.flow = '物流'";
			}
			if(flow.equals("B")){
				hql+=" and b.flow = '副总'";
			}
			if(flow.equals("C")){
				hql+=" and b.flow='经理'";
			}
		}else{
			hql+=" and b.flow = '物流'";
		}
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		return new Object[]{list,count};
	}
	/**
	 * @Title: queryByCondition
	 * @Description: 根据条件查询业务
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryByCondition(Map map, int pageNo, int pageSize,String flow) {
		List list = new ArrayList();
			String hql = "from Business b where 1 = 1 and b.status = 'NO'";
			if(map != null){
			if (map.get("type") != null) {
				hql += " and b.type = '" + map.get("type") + "'";
			}
			if(map.get("collectionUnit") != null){
				hql += " and b.collectionUnit = '" + map.get("collectionUnit") + "'";
			}
			if(map.get("transactor") != null){
				hql += " and b.transactor = '" + map.get("transactor") + "'";
			}
			if (map.get("content") != null) {
				hql += " and b.content like '%" + map.get("content") + "%'";
			}
			if(map.get("beginTime") != null && map.get("endTime") != null){
				hql += " and (b.time between '" + map.get("beginTime") + "' and '" + map.get("endTime") + "')";
			}
			}
			if(flow != null){
				if(flow.equals("A")){
					hql+=" and b.flow = '物流'";
				}
				if(flow.equals("B")){
					hql+=" and b.flow = '副总'";
				}
				if(flow.equals("C")){
					hql+=" and b.flow='经理'";
				}
			}else{
				hql +=" and b.flow='物流'";
			}
			list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			if(list != null && list.size() > 0){
				return new Object[]{list,count};
			}
		return null;
	}
	/**
	 * @Title: auditBusiness
	 * @Description: 更改业务审核完的结果
	 * @param id
	 * @param flow
	 * @return boolean
	 * @throws
	 */
	public boolean auditBusiness(int id, String flow,int ifAgree,String dept) {
		Business bu = getBusinessById(id);
		if(flow != null){
			if(flow.equals("A")){
				if(ifAgree == 1){
					String val = efs.getNextExamine("物流", dept);
					bu.setStatus("NO");
					bu.setFlow(val);
				}
			}
			if(flow.equals("B")){
				if(ifAgree == 1){
					String val = efs.getNextExamine("副总", dept);
					bu.setStatus("NO");
					bu.setFlow(val);
				}else{
					String val = efs.getFirstExamine(dept);
					bu.setStatus("NO");
					bu.setFlow(val);
				}
			}
			if(flow.equals("C")){
				if(ifAgree ==1){
					String val = efs.getNextExamine("经理", dept);
					bu.setStatus("YES");
					bu.setFlow(val);
				}
				if(ifAgree ==2){
					String val = efs.getFirstExamine(dept);
					bu.setStatus("NO");
					bu.setFlow(val);
				}
			}
		}
		return update(bu);
	}
	/**
	 * @Title: queryByPass
	 * @Description: 查询所有通过的业务
	 * @param @param pageNo
	 * @param @param pageSize
	 * @return Object[]
	 * @throws
	 */
	public Object[] queryByPass(int pageNo, int pageSize) {
		String hql = "from Business b where b.status = 'YES' and b.flow = '通过'";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);	
		int count =	totalDao.getCount(hql);
		if(list != null && list.size() > 0){
			return new Object[]{list,count};
		}
		return null;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	public IExamineFlowService getEfs() {
		return efs;
	}
	public void setEfs(IExamineFlowService efs) {
		this.efs = efs;
	}
	
}
