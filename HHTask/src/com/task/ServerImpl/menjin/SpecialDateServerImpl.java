package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.SpecialDateServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.menjin.SpecialDate;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class SpecialDateServerImpl implements SpecialDateServer {
	private TotalDao totalDao;

	@Override
	public String addSpecialDate(SpecialDate specialDate) {
		// TODO Auto-generated method stub
		if (specialDate != null) {
			int count = totalDao.getCount("from SpecialDate where banciId = ? and date = ?", specialDate.getBanciId(),specialDate.getDate());
			if (count > 0)
				return specialDate.getBanciName()+"班次在"+specialDate.getDate()+"已有特殊日期信息，不能重复添加。";
			//判断特殊类型
			BanCi banCi = (BanCi) totalDao.getObjectById(BanCi.class, specialDate.getBanciId());
			if (banCi!=null) {
				if ("上班".equals(specialDate.getSpecialType())) {
					if (AttendanceTowServerImpl.isbanci(banCi, specialDate.getDate()))
						return specialDate.getDate()+"已在班次"+specialDate.getBanciName()+"内，无需添加上班信息！";
				}else if("放假".equals(specialDate.getSpecialType())){
					if (!AttendanceTowServerImpl.isbanci(banCi, specialDate.getDate()))
						return specialDate.getDate()+"不在班次"+specialDate.getBanciName()+"内，无需添加放假信息！";
				}
				Users users = Util.getLoginUser();
				specialDate.setAddTime(Util.getDateTime());
				if (users!=null) 
					specialDate.setAddPName(users.getName());
				if (totalDao.save(specialDate))
					return "添加成功！";
				else
					return "添加失败！";
			}else
				return "班次为空，添加失败！";
		}
		return "对象为空，添加失败！";
	}

	@Override
	public SpecialDate byIdSpecialDate(Integer id) {
		// TODO Auto-generated method stub
		return (SpecialDate) totalDao.getObjectById(SpecialDate.class, id);
	}

	@Override
	public String deleteSpecialDate(Integer id) {
		// TODO Auto-generated method stub
		SpecialDate obje = byIdSpecialDate(id);
		if (obje != null) {
			String str = Util.getDateTime("yyyy-MM-dd");
			if (str.compareTo(obje.getDate())>0)
				return "当前日期大于特殊日期，删除失败！";
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findSpecialDate(SpecialDate specialDate,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (specialDate == null) {
			specialDate = new SpecialDate();
		}
		String hql = totalDao.criteriaQueries(specialDate, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateSpecialDate(SpecialDate SpecialDate) {
		// TODO Auto-generated method stub
		SpecialDate SpecialDate2 = byIdSpecialDate(SpecialDate.getId());
		if (SpecialDate2 != null) {
			BeanUtils.copyProperties(SpecialDate, SpecialDate2, new String[] {
					"id", "addTime", "addPName"});
			if (totalDao.update(SpecialDate2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	@Override
	public List findBanCi() {
		// TODO Auto-generated method stub
		String hql = "from BanCi where name is not null and name <> ''";
		List list = totalDao.query(hql);
		return list;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
