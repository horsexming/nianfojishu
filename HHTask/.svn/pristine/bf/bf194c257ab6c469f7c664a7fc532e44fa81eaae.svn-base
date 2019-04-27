package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.SkillTypeServer;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;

public class SkillTypeServerImpl implements SkillTypeServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(SkillType skillType) {
		// TODO Auto-generated method stub
		return totalDao.save(skillType);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		SkillType skillType = getById(id);
		if (skillType != null) {
			skillType.setCraftComplexity(null);
		    skillType.setProProcessDifficulty(null);
		    skillType.setSkillScore(null);//清空外鍵關係
			return totalDao.delete(skillType);
		}
		return false;
	}

	@Override
	public List<SkillType> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from SkillType");
		if (all.size() > 0) {
			List<SkillType> skillTypes = new ArrayList<SkillType>();
			for (Object o : all) {
				skillTypes.add((SkillType) o);
			}
			return skillTypes;
		}

		return null;
	}

	@Override
	public Map<Integer, Object> findSkillTypesByCondition(SkillType skillType,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (skillType == null) {
			skillType = new SkillType();
		}
		String hql = totalDao.criteriaQueries(skillType, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public SkillType getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(SkillType.class, id);
			if (o != null) {
				return (SkillType) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(SkillType skillType) {
		// TODO Auto-generated method stub
		if (skillType != null) {
			SkillType sType = getById(skillType.getId());
			if (sType != null) {
				sType.setMaxscore(skillType.getMaxscore());
				sType.setMinscore(skillType.getMinscore());
				return totalDao.update(sType);
			}
		}
		return false;
	}

	@Override
	public Map<Integer, Object> getSScoresMap(Integer id, Integer cpage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		SkillType skillType = getById(id);
		if (skillType != null) {
			Set<SkillScore> sScoreSet = skillType.getSkillScore();
			List<SkillScore> hadList = new ArrayList<SkillScore>();// 存放已绑定的技能系数
			List<SkillScore> unHadList = new ArrayList<SkillScore>();// 存放未绑定的技能系数
			List<SkillScore> skillScores = new ArrayList<SkillScore>();// 存放所有技能系数
			for (SkillScore ss : sScoreSet) {
				hadList.add(ss);
			}
			List<Object> all = totalDao.query("from SkillScore");// 获取所有的技能系数
			if (all.size() > 0) {
				for (Object o : all) {
					skillScores.add((SkillScore) o);
				}
			}
			skillScores.removeAll(hadList);// 所有的技能系数减去已绑定的技能系数就是未绑定的技能系数
			// 对未绑定的技能系数进行分页
			int start = (cpage - 1) * pageSize;
			int end = cpage * pageSize - 1;
			int n=0;
			for (int i = start; i < skillScores.size()& n < end; i++) {
				if(skillScores.get(i).getSkillType()==null){//如果该技能系数被其他的技能分类绑定了就剔除
				unHadList.add(skillScores.get(i));
				n++;
				}
			}
			int totalpage = (unHadList.size() + pageSize - 1) / pageSize;
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, hadList);
			map.put(2, unHadList);
			map.put(3, skillType);
			map.put(4, totalpage);
			return map;
		}
		return null;
	}

	@Override
	public boolean linkSkillScore(Integer skillTypeId, int[] skillScoreId) {
		// TODO Auto-generated method stub
		SkillType skillType = getById(skillTypeId);
		if (skillType != null) {
			Set<SkillScore> sScoreSet = new HashSet<SkillScore>();
			if (skillScoreId != null) {
				for (Integer ssId : skillScoreId) {
					Object o = totalDao.getObjectById(SkillScore.class, ssId);
					if (o != null) {
						sScoreSet.add((SkillScore) o);
					}
				}
			}
				skillType.setSkillScore(sScoreSet);
			return totalDao.update(skillType);
		}
		return false;
	}

}
