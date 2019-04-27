package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.SkillScoreServer;
import com.task.entity.shizhi.SkillScore;

public class SkillScoreServerImpl implements SkillScoreServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(SkillScore skillScore) {
		// TODO Auto-generated method stub
		if (skillScore != null) {
			if (skillScore.getTotalscore() != null
					& skillScore.getDifficultScore() != null) {
				float total = skillScore.getTotalscore()
						* skillScore.getDifficultScore();
				skillScore.setTotal(Float.parseFloat(String.format("%.2f",
						total)));
			} else {
				skillScore.setTotal(0f);
			}
			return totalDao.save(skillScore);
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		SkillScore sscore = getById(id);
		if (sscore != null) {
			return totalDao.delete(sscore);
		}
		return false;
	}

	@Override
	public SkillScore getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(SkillScore.class, id);
			if (o != null) {
				return (SkillScore) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(SkillScore skillScore) {
		// TODO Auto-generated method stub
		if (skillScore != null) {
			if (skillScore.getTotalscore() != null
					& skillScore.getDifficultScore() != null) {
				float total = skillScore.getTotalscore()
						* skillScore.getDifficultScore();
				skillScore.setTotal(Float.parseFloat(String.format("%.2f",
						total)));
			}
			return totalDao.update(skillScore);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findSkillScoresByCondition(
			SkillScore skiilScore, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (skiilScore == null) {
			skiilScore = new SkillScore();
		}
		String hql = totalDao.criteriaQueries(skiilScore, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<SkillScore> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from SkillScore");
		if (all.size() > 0) {
			List<SkillScore> skillScores = new ArrayList<SkillScore>();
			for (Object o : all) {
				skillScores.add((SkillScore) o);
			}
			return skillScores;
		}

		return null;
	}

}
