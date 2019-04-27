package com.task.ServerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.TdetailService;
import com.task.entity.Project;
import com.task.entity.Tdetail;

public class TdetailServiceImpl implements TdetailService {
	
	private TotalDao totalDao;

	@Override
	public void addAll(List<Tdetail> details) {
		Tdetail t = sortDetail(details);
		try {
			totalDao.save(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 利用序号整理
	 * @param details
	 * @return
	 */
	public Tdetail sortDetail(List<Tdetail> details){
		Tdetail detail = details.get(0);
		detail.setSeq(0);
		Map<String, Tdetail> maps = new HashMap<String, Tdetail>();
		for (int i = 1; i < details.size(); i++) {
			details.get(i).setSeq(Integer.parseInt(details.get(i).getXunhao().substring(details.get(i).getXunhao().lastIndexOf(".") +1).trim()));
			Project p = new Project();
			details.get(i).setProject(detail.getProject());
			maps.put(details.get(i).getXunhao(), details.get(i));
		}

		for (String xuhao : maps.keySet()) {
			if(xuhao.indexOf(".") < 0){
				detail.getDetails().add(maps.get(xuhao));
				maps.get(xuhao).setDetail(detail);
			} else {
				Tdetail t = maps.get(xuhao.substring(0, xuhao.lastIndexOf(".")));
				maps.get(xuhao).setDetail(t);
				t.getDetails().add(maps.get(xuhao));
			}
		}
		return detail;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<Tdetail> get(Project p) {
		String hql = "from Tdetail t where t.project.id = ? and t.type = '自制件'";
		List<Tdetail> list = totalDao.query(hql,new Object[]{p.getId()});
		return list;
	}

	@Override
	public List getZizhiSelect(Project project) {
		String sql = "select id, name, xunhao, partNumber  from ta_tdetail t where t.f_project_id = ? and t.type = ?";
		List query = totalDao.createQuerySelect(null, sql, new Object[]{project.getId(), "自制件"});
		return query;
	}

	@Override
	public List getAll(Project project) {
		String sql = "select id, name, partNumber from ta_tdetail t where t.f_project_id = ?";
		List query = totalDao.createQuerySelect(null, sql, new Object[]{project.getId()});
		return query;
	}

	@Override
	public List<Tdetail> getZongCheng(Project project) {
		String hql = "from Tdetail t where t.detail is null and t.project.id = ?";
		return totalDao.find(hql, new Object[]{project.getId()});
	}


}
