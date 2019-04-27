package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.CraftLoadServer;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;

public class CraftLoadServerImpl implements CraftLoadServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public CraftLoad getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(CraftLoad.class, id);
			if (o != null) {
				return (CraftLoad) o;
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findCraftLoadsByCondition(CraftLoad CraftLoad,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (CraftLoad == null) {
			CraftLoad = new CraftLoad();
		}
		String hql = totalDao.criteriaQueries(CraftLoad, null, null);
		hql = hql + " order by id desc";
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List<CraftLoad> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from CraftLoad");
		if (all.size() > 0) {
			List<CraftLoad> CraftLoads = new ArrayList<CraftLoad>();
			for (Object o : all) {
				CraftLoads.add((CraftLoad) o);
			}
			return CraftLoads;
		}

		return null;
	}



	@Override
	public List<CraftComplexity> findCraftComplexityAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from CraftComplexity");
		if (all.size() > 0) {
			List<CraftComplexity> ccs = new ArrayList<CraftComplexity>();
			for (Object o : all) {
				ccs.add((CraftComplexity) o);
			}
			return ccs;
		}

		return null;
	}

	@Override
	public List<ProProcessDifficulty> findProProcessDifficultyAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from ProProcessDifficulty");
		if (all.size() > 0) {
			List<ProProcessDifficulty> ppds = new ArrayList<ProProcessDifficulty>();
			for (Object o : all) {
				ppds.add((ProProcessDifficulty) o);
			}
			return ppds;
		}

		return null;
	}


//	@Override
//	public boolean addUnintoData() {
//		// TODO Auto-generated method stub
//		String sql = "from ProcessTemplate where id not in (select processId from CraftLoad)";
//		List qlist = totalDao.query(sql);
//		List<ProcessShiZhi> plist = findProcessShiZhiAll();
//		boolean b = true;
//		if (qlist.size() > 0) {// 添加未和ProcessTemplate表同步的值
//			List<ProcessTemplate> qpInfors =qlist;
//			for (ProcessTemplate qpInfo:qpInfors) {
//				CraftLoad cload = new CraftLoad();// 创建工艺负荷对象临时存储
//				ProcardTemplate qp = qpInfo.getProcardTemplate();
//				cload.setFatherId(qp.getFatherId());// 设置工艺负荷关于工序的属性
//				cload.setQpId(qp.getId());
//				cload.setRootId(qp.getRootId());
//				cload.setMarkId(qp.getMarkId());
//				cload.setProcessId(qpInfo.getId());
//				cload.setProcessNO(qpInfo.getProcessNO());
//				cload.setProcessName(qpInfo.getProcessName());
//				cload.setCraftLoadScore(0f);
//				if (plist.size() > 0) {
//					for (ProcessShiZhi p : plist) {
//						if (p.getProcessId().equals(cload.getProcessId())  
//								&& p.getProcessPRNScore() != null) {
//							cload.setProcessPRNScore(p.getProcessPRNScore()
//									.getRiskScore());
//							cload.setCraftLoadScore(Float.parseFloat(String
//									.format("%.3f", p.getProcessPRNScore()
//											.getRiskScore())));
//						}
//					}
//				}
//				b = b & totalDao.save(cload);
//			}
//		}
//		return b;
//	}




	@Override
	public Map<Integer, Object> getScoreMap(Integer id) {
		// TODO Auto-generated method stub
		CraftLoad cload = getById(id);
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<CraftComplexity> ccList=totalDao.query("from CraftComplexity");
		List<ProProcessDifficulty> ppdList=totalDao.query("from ProProcessDifficulty");
		map.put(1, cload);
		map.put(2, ccList);
		map.put(3, ppdList);
		return map;
	}


	@Override
	public boolean update(CraftLoad craftLoad) {
		// TODO Auto-generated method stub
		if (craftLoad != null) {
			CraftLoad cload = getById(craftLoad.getId());
			if (cload != null) {
				if(craftLoad.getCraftskillId()!=null){
					SkillScore craftSkill=(SkillScore) totalDao.getObjectById(SkillScore.class, craftLoad.getCraftskillId());
					if(craftSkill!=null){
						cload.setCraftskillId(craftLoad.getCraftskillId());
						cload.setCraftComplexity(craftSkill.getTotal());
						if(craftSkill.getSkillType()!=null){
							cload.setCraftSkillName(craftSkill.getSkillType().getName());
							if(craftSkill.getSkillType().getCraftComplexity()!=null){
								cload.setCraftComplexityName(craftSkill.getSkillType().getCraftComplexity().getName());
							}
						}
					}else{
						cload.setCraftComplexity(0f);
					}
				}else{
					cload.setCraftComplexity(0f);
				}
				if(craftLoad.getPpdSkillId()!=null){
					SkillScore ppdSkill=(SkillScore) totalDao.getObjectById(SkillScore.class, craftLoad.getPpdSkillId());
					if(ppdSkill!=null){
						cload.setPpdSkillId(craftLoad.getPpdSkillId());
						cload.setProcessDifficulty(ppdSkill.getTotal());
						if(ppdSkill.getSkillType()!=null){
							cload.setPpdSkillName(ppdSkill.getSkillType().getName());
							if(ppdSkill.getSkillType().getProProcessDifficulty()!=null){
								cload.setProcessDifficultyName(ppdSkill.getSkillType().getProProcessDifficulty().getName());
							}
						}
					}else{
						cload.setProcessPRNScore(0f);
					}
				}else{
					cload.setProcessPRNScore(0f);
				}
				if(craftLoad.getRpnScore1()!=null){
					cload.setRpnScore1(craftLoad.getRpnScore1());
				}else{
					cload.setRpnScore1(0f);
				}
				if(craftLoad.getRpnScore2()!=null){
					cload.setRpnScore2(craftLoad.getRpnScore2());
				}else{
					cload.setRpnScore2(0f);
				}
				if(craftLoad.getRpnScore3()!=null){
					cload.setRpnScore3(craftLoad.getRpnScore3());
				}else{
					cload.setRpnScore3(0f);
				}
				cload.setProcessPRNScore(Float.parseFloat(String
										.format("%.3f", cload.getRpnScore1()*cload.getRpnScore2()*cload.getRpnScore3())));
				
				cload.setCraftLoadScore(Float.parseFloat(String.format("%.3f",
						cload.getProcessPRNScore()+cload.getCraftComplexity()+cload.getProcessDifficulty())));
				return totalDao.update(cload);
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		CraftLoad cl=getById(id);
		if(cl!=null){
			return totalDao.delete(cl);
		}
		return false;
	}

	@Override
	public List<SkillType> getSkills(String flag, Integer id) {
		// TODO Auto-generated method stub
		if(flag.equals("cc")){
			return totalDao.query("from SkillType where craftComplexity.id=?", id);
		}else if(flag.equals("ppd")){
			return totalDao.query("from SkillType where proProcessDifficulty.id=?", id);
		}
		return null;
	}

	@Override
	public List<SkillScore> getSkillScores(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from SkillScore where skillType.id=?", id);
	}
}
