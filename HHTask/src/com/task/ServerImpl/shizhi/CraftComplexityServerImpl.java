package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.CraftComplexityServer;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.SkillType;
import com.task.entity.vo.shizhivo.CraftComplexityVo;
import com.task.entity.vo.shizhivo.SkillTypeVo;

public class CraftComplexityServerImpl implements CraftComplexityServer{
    private TotalDao totalDao;
    
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(CraftComplexity cc) {
		// TODO Auto-generated method stub
		return totalDao.save(cc);
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		CraftComplexity cc=getById(id);
		if(cc!=null){
			cc.setSkillType(null);//清除外键关系
			return totalDao.delete(cc);
		}
		return false;
	}

	@Override
	public List<CraftComplexity> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from CraftComplexity");
		if (all.size() > 0) {
			List<CraftComplexity> craftComplexitys = new ArrayList<CraftComplexity>();
			for (Object o : all) {
				craftComplexitys.add((CraftComplexity) o);
			}
			return craftComplexitys;
		}

		return null;
	}

	@Override
	public Map<Integer, Object> findSkillTypesByCondition(CraftComplexity cc,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (cc == null) {
			cc = new CraftComplexity();
		}
		String hql = totalDao.criteriaQueries(cc, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		//将实体类转换为Vo类用来防止懒加载问题
		List<CraftComplexityVo> ccVos=new ArrayList<CraftComplexityVo>();
		if(objs.size()>0){
			for(Object o:objs){
			ccVos.add(new CraftComplexityVo((CraftComplexity)o));
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, ccVos);
		map.put(2, count);
		return map;
	}

	@Override
	public CraftComplexity getById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(CraftComplexity.class, id);
		if(o!=null){
			return (CraftComplexity)o;
		}
		return null;
	}

	@Override
	public Map<Integer, Object> getSkillTypeVosMap(Integer id, Integer cpage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		CraftComplexity cc = getById(id);
		if (cc != null) {
			Set<SkillType> sTypeSet = cc.getSkillType();
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
			int n=0;
			for (int i = start; i < skillTypes.size() & n < end; i++) {
				unHadList.add(skillTypes.get(i));
				n++;
			}
			int totalpage = (skillTypes.size() + pageSize - 1) / pageSize;
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map.put(1, hadList);
			map.put(2, unHadList);
			map.put(3, cc);
			map.put(4, totalpage);
			return map;
		}
		return null;
	}

	@Override
	public CraftComplexityVo getVoById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(CraftComplexity.class, id);
		if(o!=null){
			return new CraftComplexityVo((CraftComplexity)o);
		}
		return null;
	}

	@Override
	public boolean linkSkillType(Integer ccId, int[] skillTypeId) {
		// TODO Auto-generated method stub
		CraftComplexity cc = getById(ccId);
		if (cc != null) {
			Set<SkillType> sTypeSet = new HashSet<SkillType>();
			if (skillTypeId != null) {
				for (Integer stId : skillTypeId) {
					Object o = totalDao.getObjectById(SkillType.class, stId);
					if (o != null) {
						sTypeSet.add((SkillType) o);
					}
				}
				cc.setSkillType(sTypeSet);
			} else {
				cc.setSkillType(sTypeSet);

			}
			return totalDao.update(cc);
		}
		return false;
	}

	@Override
	public boolean update(CraftComplexity cc) {
		// TODO Auto-generated method stub
		if(cc!=null){
			CraftComplexity c=getById(cc.getId());
			if(c!=null){
				return totalDao.update(c);
			}
		}
		return false;
	}



}
