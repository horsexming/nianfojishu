package com.task.ServerImpl.sop;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.sop.BeHeGePinServer;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.DefectOfType;
import com.task.util.Util;

public class BeHeGePinServerImpl implements BeHeGePinServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 查詢所有（分頁）
	@SuppressWarnings("unchecked")
	public List<BuHeGePin> FindAllBuHeGePin(int pageNo, int pageSize) {
		String hql = "from BuHeGePin order by id desc";

		return (List<BuHeGePin>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 添加 不合格品
	public String AddBuGePin(BuHeGePin bhgp) {
		if (bhgp != null) {
			// 判断是否已经存在缺陷代码
			String hql = "from BuHeGePin where code =? or type=?";
			int count = totalDao.getCount(hql, bhgp.getCode(), bhgp.getType());
			if (count > 0) {
				return "已经存在您要添加的缺陷代码!";
			}
			return totalDao.save(bhgp) + "";
		}
		return "数据异常!";
	}

	// 根據產品名字查詢不合格品
	public List<BuHeGePin> FindBuHeGePin(String name, int pageNo, int pageSize) {
		String hql = "from BuHeGePin where name=? order by writeDate desc";
		return (List<BuHeGePin>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 根據ID查詢不合格品
	@Override
	public BuHeGePin FindBuHeGePinByid(int id) {
		if ((Object) id != null && id > 0) {
			return (BuHeGePin) totalDao.getObjectById(BuHeGePin.class, id);
		}
		return null;
	}

	// 根据ID刪除不合格品
	@Override
	// public boolean DelBuHeGePin(int id) {
	// String hql="delete BuHeGePin where id=?";
	// int row= totalDao.createQueryUpdate(hql, null, id);
	// return row ==1;
	// }
	public boolean DelBuHeGePin(BuHeGePin buhegepin) {
		return totalDao.delete(buhegepin);
	}

	// 获得总数量；
	@Override
	public int getcont() {
		String hql = "from BuHeGePin";

		return totalDao.getCount(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findBuHeGePinByCondition(BuHeGePin buhegepin,
			int pageNo, int pageSize) {
		if (buhegepin == null) {
			buhegepin = new BuHeGePin();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(buhegepin, null);
		hql += " order by id desc";
		int count = totalDao.getCount(hql);
		List<BuHeGePin> BuHeGePinList = (List<BuHeGePin>) totalDao
				.findAllByPage(hql, pageNo, pageSize);
		map.put(1, BuHeGePinList);
		map.put(2, count);
		return map;
	}

	// 修改不合格品的類型；
	public boolean UpdataBuHeGePin(Object[] obj) {
		String hql = "update BuHeGePin set type=? where id=?";
		int row = totalDao.createQueryUpdate(hql, null, obj);
		return row == 1;
	}

	// 更新不合格
	@Override
	public boolean updatebuhegepin1(BuHeGePin buhegepin) {

		return totalDao.update(buhegepin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findBuHeGePinType() {
		String hql = "from BuHeGePin order by id ";
		return (List<String>) totalDao.find(hql);
	}

	@Override
	public String adddDefectOfType(DefectOfType defType) {
		Users user = Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(defType!=null){
			defType.setAddtime(Util.getDateTime());
			defType.setAddUsersName(user.getName());
			List<BuHeGePin> bhgList = defType.getBhgList();
			Set<BuHeGePin> bhgpSet = new HashSet<BuHeGePin>();
			if(bhgList!=null && bhgList.size()>0){
				for (BuHeGePin bhg : bhgList) {
					bhg.setWriteDate(Util.getDateTime());
					bhg.setWritePerson(user.getName());
					bhg.setDefType(defType);
					bhgpSet.add(bhg);
				}
			}
			defType.setBhgpSet(bhgpSet);
			return	totalDao.save(defType)+"";
		}
		return null;
	}

	@Override
	public Boolean delDefectOfType(DefectOfType defType) {
		if(defType!=null){
			return totalDao.delete(defType);
		}
		return null;
	}

	@Override
	public List<DefectOfType> findAllDefTypeList() {
		return totalDao.query(" from DefectOfType");
	}

	@Override
	public Object[] findDefType(DefectOfType defType, String status,
			Integer pageSize, Integer pageNo) {
		if(defType==null){
			defType = new DefectOfType();
		}
		String hql =	totalDao.criteriaQueries(defType,null);
		List<DefectOfType> defTypeList =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int  count =totalDao.getCount(hql);
		return new Object[]{defTypeList,count};
	}
	
	@Override
	public List<BuHeGePin> findbhgpListByDefId(Integer id,String status,BuHeGePin bhg) {
		if(id!=null){
			if(bhg == null){
				bhg = new BuHeGePin();
			}
			String hql = totalDao.criteriaQueries(bhg, null);
			if("ybd".equals(status)){
				return 	totalDao.query( hql+" and  defType.id = ? order by code ", id);		
			}else if("wbd".equals(status)){
				return totalDao.query( hql+" and  defType.id is null");		
			}
		}
		return null;
	}
	public List<BuHeGePin> findbhgpListByDefName(String name){
		if(name!=null&&!"".equals(name)){
			List<BuHeGePin> bhgpList = totalDao.query("from BuHeGePin where defType.defName =?", name);
			return bhgpList;
		}else{
			return null;
		}
	}
	@Override
	public String updateDefectOfType(DefectOfType defType) {
		Users user = Util.getLoginUser();
		if(user == null){
			return "请先登录!~";
		}
		if(defType!=null){
			DefectOfType olddef =	(DefectOfType) totalDao.get(DefectOfType.class, defType.getId());
			Set<BuHeGePin> bhgpSet =	olddef.getBhgpSet();
			List<BuHeGePin> bhgList = defType.getBhgList(); 
			if(bhgList!=null && bhgList.size()>0){
				if(bhgpSet == null){
					bhgpSet = new HashSet<BuHeGePin>();
				}
				for (BuHeGePin bhg : bhgList) {
					if(bhg.getId()>0){
						BuHeGePin oldbhg =	(BuHeGePin) totalDao.get(bhg.getClass(), bhg.getId());
						oldbhg.setCode(bhg.getCode());
						oldbhg.setType(bhg.getType());
						totalDao.update(oldbhg);
					}else{
						bhg.setWriteDate(Util.getDateTime());
						bhg.setWritePerson(user.getName());
						bhgpSet.add(bhg);
					}
					
				}
			}
			olddef.setDefCode(defType.getDefCode());
			olddef.setDefName(defType.getDefName());
			olddef.setBhgpSet(bhgpSet);
			return totalDao.update(olddef)+"";
		}
		return null;
	}

	@Override
	public DefectOfType getdefTypeById(Integer id) {
		if(id!=null){
			return 	(DefectOfType) totalDao.get(DefectOfType.class, id);
		}
		return null;
	}

	@Override
	public String bangdingbhg(Integer id, Integer[] ids) {
		if(id!=null){
			DefectOfType deftype =	getdefTypeById(id);
			Set<BuHeGePin> bhgSet =	deftype.getBhgpSet();
			if(ids!=null && ids.length>0){
				if(bhgSet == null){
					bhgSet = new HashSet<BuHeGePin>();
				}
				for (int i = 0; i < ids.length; i++) {
					BuHeGePin bhg =	(BuHeGePin) totalDao.get(BuHeGePin.class, ids[i]);
					bhgSet.add(bhg);
				}
				deftype.setBhgpSet(bhgSet);
				return	totalDao.update(deftype)+"";
			}else{
				return "请至少选择一项!~";
			}
		}
		return null;
	}

}
