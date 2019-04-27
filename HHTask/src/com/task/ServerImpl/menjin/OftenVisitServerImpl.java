package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.OftenVisitServer;
import com.task.entity.menjin.OftenVisit;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class OftenVisitServerImpl implements OftenVisitServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public OftenVisit OftenVisitByid(Integer integer) {
		// TODO Auto-generated method stub
		return (OftenVisit) totalDao.getObjectById(OftenVisit.class, integer);
	}

	@Override
		// TODO Auto-generated method stub
	public String addOftenVisit(OftenVisit OftenVisit) {
		if (OftenVisit != null) {
			OftenVisit.setAddTime(Util.getDateTime());
			OftenVisit.setEffectiveDate(Util.getNextMonth3(Util
					.getDateTime("yyyy-MM")));
			if (totalDao.save(OftenVisit)) {
				return "已申请!";
			}
		}
		return "申请失败";
	}

	@Override
	public boolean deleteOftenVisit(OftenVisit OftenVisit) {
		// TODO Auto-generated method stub
		OftenVisit OftenVisit1 = OftenVisitByid(OftenVisit.getId());
		if (OftenVisit1 != null) {
			return totalDao.delete(OftenVisit1);
		} else {
			return false;
		}
	}

	@Override
	public String updateOftenVisit(OftenVisit oftenVisit) {
		// TODO Auto-generated method stub
		OftenVisit oftenVisit1 = OftenVisitByid(oftenVisit.getId());
		if (oftenVisit1 != null) {
			BeanUtils.copyProperties(oftenVisit, oftenVisit1, new String[] {
					"id", "addTime", "carFiles", "oftenmarkId"});
			oftenVisit1.setUpdateTime(Util.getDateTime());
			oftenVisit1.setEffectiveDate(Util.getNextMonth3(Util
					.getDateTime("yyyy-MM")));
			if (totalDao.update(oftenVisit1)) {
				return "修改成功";
			}
		} else {
			return "不存在该对象,修改失败";
		}
		return "修改失败";
	}

	@Override
	public Map<Integer, Object> findOftenVisitByCondition(
			OftenVisit oftenVisit, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (oftenVisit == null) {
			oftenVisit = new OftenVisit();
		}
		String hql = totalDao.criteriaQueries(oftenVisit, null);
		hql += " order by id desc";
		List listi = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listi);
		map.put(2, count);
		return map;
	}

}
