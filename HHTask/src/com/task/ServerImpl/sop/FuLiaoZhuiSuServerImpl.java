package com.task.ServerImpl.sop;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.FuLiaoZhuiSuServer;
import com.task.entity.sop.FuLiaoZhuiSu;
import com.task.entity.sop.Procard;
import com.task.util.Util;

public class FuLiaoZhuiSuServerImpl implements FuLiaoZhuiSuServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String addflzs(FuLiaoZhuiSu flzs) {
		if(flzs!=null){
			flzs.setAddtime(Util.getDateTime());
			String groupA = flzs.getGroupA();
			String groupB = flzs.getGroupB();
			if(groupA!=null && groupA.length()>0){
				groupA = groupA.replaceAll("\r\n", ";");
			}
			if(groupB!=null && groupB.length()>0){
				groupB = groupB.replaceAll("\r\n", ";");
			}
			flzs.setGroupA(groupA);
			flzs.setGroupB(groupB);
			return totalDao.save(flzs)+"";
		}
		return null;
	}

	@Override
	public String delflzs(FuLiaoZhuiSu flzs) {
		if(flzs!=null){
			return totalDao.delete(flzs)+"";
		}
		return null;
	}

	@Override
	public Object[] findAllflzs(FuLiaoZhuiSu flzs, Integer pageNo,
			Integer pageSize, String pageStatus) {
		if(flzs == null){
			flzs = new FuLiaoZhuiSu();
		}
		String hql =	totalDao.criteriaQueries(flzs, null);
		List<FuLiaoZhuiSu> flzsList =	totalDao.findAllByPage(hql, pageNo, pageSize, null);
		int  count = totalDao.getCount(hql, null);
		return new Object[]{flzsList,count};
	}

	@Override
	public List<String> getywmarkIds(String orderNum) {
		if(orderNum!=null && orderNum.length()>0){
			return	totalDao.query(" select ywMarkId from ProductManager where orderManager.id in (select id from OrderManager where orderNum = ?)", orderNum);
		}
		return null;
	}

	@Override
	public Procard findProcardBy(String orderNum, String ywmarkId) {
		if(orderNum!=null && orderNum.length()>0
				&& ywmarkId!=null && ywmarkId.length()>0){
			return 	(Procard) totalDao.getObjectByCondition(" from Procard where orderNumber = ? and ywMarkId = ? and procardStyle = '总成'", orderNum,ywmarkId);
		}
		return null;
	}

	@Override
	public String getshebeiNo(String gongwei) {
		if(gongwei!=null && gongwei.length()>0){
			return 	(String) totalDao.getObjectByCondition(" select no from Machine where workPosition = ? ", gongwei);
		}
		return null;
	}

	@Override
	public FuLiaoZhuiSu findflzsById(Integer id) {
		if(id!=null){
			FuLiaoZhuiSu flzs = (FuLiaoZhuiSu) totalDao.get(FuLiaoZhuiSu.class, id);
			String groupA = flzs.getGroupA();
			String groupB = flzs.getGroupB();
			if(groupA!=null && groupA.length()>0){
				groupA = groupA.replaceAll(";", "\r\n");
			}
			if(groupB!=null && groupB.length()>0){
				groupB = groupB.replaceAll(";", "\r\n");
			}
			flzs.setGroupA(groupA);
			flzs.setGroupB(groupB);
			return flzs;
		}
		return null;
	}

	@Override
	public String updateflzs(FuLiaoZhuiSu flzs) {
		return totalDao.update(flzs)+"";
	}

	@Override
	public String getproName(String markId) {
		if(markId!=null && markId.length()>0){
			return 	(String) totalDao.getObjectByCondition(" select proName from ProcardTemplate where markId = ?  and proName is not null and proName <> '' ", markId);
		}
		return null;
	}

}
