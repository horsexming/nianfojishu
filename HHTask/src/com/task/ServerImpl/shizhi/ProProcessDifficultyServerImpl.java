package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProProcessDifficultyServer;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.SkillType;
import com.task.entity.vo.shizhivo.ProProcessDifficultyVo;
import com.task.entity.vo.shizhivo.SkillTypeVo;

public class ProProcessDifficultyServerImpl implements ProProcessDifficultyServer{
    private TotalDao totalDao;
    
    
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(ProProcessDifficulty pPD) {
		// TODO Auto-generated method stub
		return totalDao.save(pPD);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		ProProcessDifficulty ppd=getById(id);
		if(ppd!=null){
			ppd.setSkillType(null);//清空外键关系
			return totalDao.delete(ppd);
		}
		return false;
	}

	@Override
	public List<ProProcessDifficulty> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from ProProcessDifficulty");
		if (all.size() > 0) {
			List<ProProcessDifficulty> proProcessDifficultys = new ArrayList<ProProcessDifficulty>();
			for (Object o : all) {
				proProcessDifficultys.add((ProProcessDifficulty) o);
			}
			return proProcessDifficultys;
		}

		return null;
	}

	@Override
	public Map<Integer, Object> findSkillTypesByCondition(
			ProProcessDifficulty pPD, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (pPD == null) {
			pPD = new ProProcessDifficulty();
		}
		String hql = totalDao.criteriaQueries(pPD, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		//将实体类转换为Vo类用来防止懒加载问题
		List<ProProcessDifficultyVo> ppdVos=new ArrayList<ProProcessDifficultyVo>();
		if(objs.size()>0){
			for(Object o:objs){
			 ppdVos.add(new ProProcessDifficultyVo((ProProcessDifficulty)o));
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, ppdVos);
		map.put(2, count);
		return map;
	}

	@Override
	public ProProcessDifficulty getById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProProcessDifficulty.class, id);
		if(o!=null){
			return (ProProcessDifficulty)o;
		}
		return null;
	}

	

	@Override
	public boolean update(ProProcessDifficulty pPD) {
		// TODO Auto-generated method stub
		if(pPD!=null){
			ProProcessDifficulty p=getById(pPD.getId());
			if(p!=null){
				p.setName(pPD.getName());
				return totalDao.update(p);
			}
		}
		return false;
	}

	@Override
	public Map<Integer, Object> getSkillTypeVosMap(Integer id, Integer cpage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		ProProcessDifficulty ppd = getById(id);
		if (ppd != null) {
			Set<SkillType> sTypeSet = ppd.getSkillType();
			List<SkillTypeVo> hadList = new ArrayList<SkillTypeVo>();// 存放已绑定的技能系数
			List<SkillTypeVo> unHadList = new ArrayList<SkillTypeVo>();// 存放未绑定的技能系数
			List<SkillTypeVo> skillTypes = new ArrayList<SkillTypeVo>();// 存放所有技能系数
			for (SkillType ss : sTypeSet) {
				hadList.add(new SkillTypeVo(ss));
			}
			List<Object> all = totalDao.query("from SkillType");// 获取所有的技能系数
			if (all.size() > 0) {
				for (Object o : all) {
					skillTypes.add(new SkillTypeVo((SkillType) o));
				}
			}
			skillTypes.removeAll(hadList);// 所有的技能系数减去已绑定的技能系数就是未绑定的技能系数
			// 对未绑定的技能系数进行分页
			int start = (cpage - 1) * pageSize;
			int end = cpage * pageSize - 1;
			for (int i = start; i < skillTypes.size() & i <= end; i++) {
				unHadList.add(skillTypes.get(i));
			}
			int totalpage = (skillTypes.size() + pageSize - 1) / pageSize;
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, hadList);
			map.put(2, unHadList);
			map.put(3, ppd);
			map.put(4, totalpage);
			return map;
		}
		return null;
	}

	@Override
	public boolean linkSkillType(Integer pPDId, int[] skillTypeId) {
		// TODO Auto-generated method stub
		ProProcessDifficulty ppd = getById(pPDId);
		if (ppd != null) {
			Set<SkillType> sTypeSet = new HashSet<SkillType>();
			if (skillTypeId != null) {
				for (Integer stId : skillTypeId) {
					Object o = totalDao.getObjectById(SkillType.class, stId);
					if (o != null) {
						sTypeSet.add((SkillType) o);
					}
				}
			}
				ppd.setSkillType(sTypeSet);

			return totalDao.update(ppd);
		}
		return false;
	}

	@Override
	public ProProcessDifficultyVo getVoById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProProcessDifficulty.class, id);
		if(o!=null){
			return new ProProcessDifficultyVo((ProProcessDifficulty)o);
		}
		return null;
	}




}
