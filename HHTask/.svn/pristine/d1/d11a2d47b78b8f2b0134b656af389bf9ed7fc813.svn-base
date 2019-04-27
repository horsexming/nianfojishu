package com.task.ServerImpl.shizhi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProcessSopTempServer;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.ProcessSopTemp;
import com.task.entity.shizhi.SkillScore;

public class ProcessSopTempServerImpl implements ProcessSopTempServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Map<Integer, Object> findPocessSopTempsByCondition(
			ProcessSopTemp processSopTemp, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (processSopTemp == null) {
			processSopTemp = new ProcessSopTemp();
		}
		String hql = totalDao.criteriaQueries(processSopTemp, null, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public ProcessSopTemp getById(Integer id) {
		// TODO Auto-generated method stub
		return (ProcessSopTemp) totalDao.getObjectById(ProcessSopTemp.class, id);
	}

	@Override
	public boolean update(ProcessSopTemp processSopTemp,String updateAll) {
		// TODO Auto-generated method stub
		boolean b=false;
		if (processSopTemp != null) {
			ProcessSopTemp pTemp = getById(processSopTemp.getId());
			if (pTemp != null) {
				if(processSopTemp.getCraftskillId()!=null){
					SkillScore craftSkill=(SkillScore) totalDao.getObjectById(SkillScore.class, processSopTemp.getCraftskillId());
					if(craftSkill!=null){
						pTemp.setCraftskillId(processSopTemp.getCraftskillId());
						pTemp.setCraftComplexity(craftSkill.getTotal());
						if(craftSkill.getSkillType()!=null){
							pTemp.setCraftSkillName(craftSkill.getSkillType().getName());
							if(craftSkill.getSkillType().getCraftComplexity()!=null){
								pTemp.setCraftComplexityName(craftSkill.getSkillType().getCraftComplexity().getName());
							}
						}
					}else{
						pTemp.setCraftComplexity(0f);
					}
				}else{
					pTemp.setCraftComplexity(0f);
				}
				if(processSopTemp.getPpdSkillId()!=null){
					SkillScore ppdSkill=(SkillScore) totalDao.getObjectById(SkillScore.class, processSopTemp.getPpdSkillId());
					if(ppdSkill!=null){
						pTemp.setPpdSkillId(processSopTemp.getPpdSkillId());
						pTemp.setProcessDifficulty(ppdSkill.getTotal());
						if(ppdSkill.getSkillType()!=null){
							pTemp.setPpdSkillName(ppdSkill.getSkillType().getName());
							if(ppdSkill.getSkillType().getProProcessDifficulty()!=null){
								pTemp.setProcessDifficultyName(ppdSkill.getSkillType().getProProcessDifficulty().getName());
							}
						}
					}else{
						pTemp.setProcessPRNScore(0f);
					}
				}else{
					pTemp.setProcessPRNScore(0f);
				}
				if(processSopTemp.getRpnScore1()!=null){
					pTemp.setRpnScore1(processSopTemp.getRpnScore1());
				}else{
					pTemp.setRpnScore1(0f);
				}
				if(processSopTemp.getRpnScore2()!=null){
					pTemp.setRpnScore2(processSopTemp.getRpnScore2());
				}else{
					pTemp.setRpnScore2(0f);
				}
				if(processSopTemp.getRpnScore3()!=null){
					pTemp.setRpnScore3(processSopTemp.getRpnScore3());
				}else{
					pTemp.setRpnScore3(0f);
				}
				pTemp.setProcessPRNScore(Float.parseFloat(String
										.format("%.3f", pTemp.getRpnScore1()*pTemp.getRpnScore2()*pTemp.getRpnScore3())));
				
				pTemp.setCraftLoadScore(Float.parseFloat(String.format("%.3f",
						pTemp.getProcessPRNScore()+pTemp.getCraftComplexity()+pTemp.getProcessDifficulty())));
				b= totalDao.update(pTemp);
				if(b&&updateAll!=null&&updateAll.equals("是")){
					List<CraftLoad> cLoadList=totalDao.query("from CraftLoad where processName=?", pTemp.getProName());
					if(cLoadList.size()>0){
						for(CraftLoad cload:cLoadList){
							cload.setCraftLoadScore(pTemp.getCraftLoadScore());//工艺分值
							cload.setCraftComplexity(pTemp.getCraftComplexity());//工艺复杂分值
							cload.setCraftComplexityName(pTemp.getCraftComplexityName());//工艺复杂分值分类名称
							cload.setCraftSkillName(pTemp.getCraftSkillName());//工艺复杂分值技能名称
							cload.setCraftskillId(pTemp.getCraftskillId());//工艺复杂分值的技能系数Id
							
							cload.setProcessDifficulty(pTemp.getProcessDifficulty());//加工难点分值
							cload.setProcessDifficultyName(pTemp.getProcessDifficultyName());//加工难点分值分类名称
							cload.setPpdSkillName(pTemp.getPpdSkillName());//加工难点分值技能名称
							cload.setPpdSkillId(pTemp.getPpdSkillId());//加工难点分值的技能系数Id
							
							cload.setProcessPRNScore(pTemp.getProcessPRNScore());//工序prn评分总值
							cload.setRpnScore1(pTemp.getRpnScore1());//工序prn评分值1
							cload.setRpnScore2(pTemp.getRpnScore2());//工序prn评分值2
							cload.setRpnScore3(pTemp.getRpnScore3());//工序prn评分值3
							b=b&totalDao.update(cload);
						}
					}
				}
			}
		}
		return b;
	}

	@Override
	public boolean delete(ProcessSopTemp processSopTemp) {
		// TODO Auto-generated method stub
		if(processSopTemp!=null&&processSopTemp.getId()!=null){
			processSopTemp=getById(processSopTemp.getId());
			return totalDao.delete(processSopTemp);
		}
		return false;
	}

	@Override
	public boolean updateAll() {
		// TODO Auto-generated method stub
		boolean b=true;
		List<String> proNameList=totalDao.query("select processName from CraftLoad group by processName");
		List<String> proNameList2=totalDao.query("select proName from ProcessSopTemp group by proName");
		if(proNameList.size()>0){
			for(String proName:proNameList){
				if(!proNameList2.contains(proName)){
					ProcessSopTemp processSopTemp=new ProcessSopTemp();
					processSopTemp.setProName(proName);
		            b=b&totalDao.save(processSopTemp);			
				}
			}
		}
		return b;
	}

	@Override
	public Map<Integer, Object> getScoreMap(Integer id) {
		// TODO Auto-generated method stub
        ProcessSopTemp process = getById(id);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<CraftComplexity> ccList=totalDao.query("from CraftComplexity");
		List<ProProcessDifficulty> ppdList=totalDao.query("from ProProcessDifficulty");
		map.put(1, process);
		map.put(2, ccList);
		map.put(3, ppdList);
		return map;
	}


}
