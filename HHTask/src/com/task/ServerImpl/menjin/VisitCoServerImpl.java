package com.task.ServerImpl.menjin;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.VisitCoServer;
import com.task.entity.menjin.VisitCo;

@SuppressWarnings("unchecked")
public class VisitCoServerImpl implements VisitCoServer {
	private TotalDao totalDao;

	@Override
	public String addVisitCo(VisitCo visitco) {
		if (totalDao.save(visitco)) {
			return "添加成功！";
		}
		return "对象为空，添加失败！";
	}

	@Override
	public VisitCo byIdVisitCo(Integer id) {
		return (VisitCo) totalDao.getObjectById(VisitCo.class, id);
	}

	@Override
	public String deleteVisitCo(Integer id) {
		VisitCo obje = byIdVisitCo(id);
		if (obje != null) {
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object[] findVisitco() {
		String hql = "from VisitCo";
		List<VisitCo> list = totalDao.query(hql, null);
		Object[] o = { list };
		return o;
	}

	@Override
	public String updateVisitCo(VisitCo visitco) {
		return null;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
