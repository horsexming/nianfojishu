package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.TryMakeServer;
import com.task.entity.Price;
import com.task.entity.project.ProjectTime;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProcessSopTemp;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.TryMake;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.vo.shizhivo.TryMakeVo;

public class TryMakeServerImpl implements TryMakeServer{
    private TotalDao totalDao;
    
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(TryMake tryMake) {
		// TODO Auto-generated method stub
		tryMake.setProjectStatu("立项");
		boolean b= totalDao.save(tryMake);
		//生成产能负荷系数，工艺负荷系数，奖金负荷系数
		Integer rootId=tryMake.getRootId();
		if(rootId!=null){
			List list=totalDao.query("from CraftLoad where rootId=?", rootId);
			if(list.size()==0){//这棵树之前没有产生过这三种系数
				ProcardTemplate pt=(ProcardTemplate) totalDao.getObjectById(ProcardTemplate.class,rootId);
				if(pt!=null){
					b=b&makeLoadScore(pt);
				}
			}
		}
		return b;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		TryMake tm=getById(id);
		if(tm!=null){
			return totalDao.delete(tm);
		}
		return false;
	}

	@Override
	public List<TryMake> findAll() {
		// TODO Auto-generated method stub
		List all=totalDao.query("from TryMake");
		if(all.size()>0){
			return (List<TryMake>)all;
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findTryMakesByCondition(TryMake tryMake,
			int pageNo, int pageSize,ProTryMakeScore proTryMakeScore) {
		// TODO Auto-generated method stub
		if (tryMake == null) {
			tryMake = new TryMake();
		}
		String sql=null;
		if(proTryMakeScore!=null&&(proTryMakeScore.getMonth()!=null||proTryMakeScore.getProName()!=null)){
			sql="proTryMakeScore.id in (select id from ProTryMakeScore where 1=1";
		String afterWhere="";
		if(proTryMakeScore.getMonth()!=null&&!proTryMakeScore.getMonth().equals("")){
			afterWhere=afterWhere+" and month like '%"+proTryMakeScore.getMonth()+"%'";
		}
		
		if(proTryMakeScore.getProName()!=null&&!proTryMakeScore.getProName().equals("")){
			afterWhere=afterWhere+" and proName like '%"+proTryMakeScore.getProName()+"%'";
		}
		sql=sql+afterWhere+")";
		}
		String hql = totalDao.criteriaQueries(tryMake, sql, null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		List<TryMakeVo> tmVoList=new ArrayList<TryMakeVo>();
		if(objs.size()>0){
			for(Object o:objs){
			tmVoList.add(new TryMakeVo((TryMake)o));
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, tmVoList);
		map.put(2, count);
		return map;
	}

	@Override
	public TryMake getById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(TryMake.class, id);
		if(o!=null){
			return (TryMake)o;
		}
		return null;
	}

	@Override
	public boolean update(TryMake tryMake) {
		// TODO Auto-generated method stub
		TryMake tm=getById(tryMake.getId());
		if(tm!=null){
			tm.setProjectStatu(tryMake.getProjectStatu());
			tm.setRemark(tryMake.getRemark());
			return totalDao.update(tm);
		}
		return false;
	}

	@Override
	public ProcardTemplate getProcardTemplateById(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProcardTemplate.class, id);
		if(o!=null){
			return (ProcardTemplate)o;
		}
		return null;
	}

	@Override
	public ProTryMakeScore getProTryMakeScore(Integer id) {
		// TODO Auto-generated method stub
		Object o=totalDao.getObjectById(ProTryMakeScore.class, id);
		if(o!=null){
			return (ProTryMakeScore)o;
		}
		return null;
	}

	@Override
	public Set<TryMake> getTMSetByPTMSId(Integer PtmId) {
		// TODO Auto-generated method stub
		ProTryMakeScore ptm=getProTryMakeScore(PtmId);
		Set<TryMake> tmSet=ptm.getTryMake();
		if(tmSet.size()>0){
			return tmSet;
		}
		return null;
	}

	@Override
	public ProTryMakeScore getProTryMakeScoreByTmId(Integer id) {
		// TODO Auto-generated method stub
		TryMake tm=getById(id);
		if(tm!=null){
			ProTryMakeScore p=tm.getProTryMakeScore();
			p.getProName();
			return p;
		}
		return null;
	}

	@Override
	public List<ProTryMakeScore> findProTryMakeScoreAll() {
		// TODO Auto-generated method stub
		List all=totalDao.query("from ProTryMakeScore");
		if(all.size()>0){
			return (List<ProTryMakeScore>)all;
		}
		return null;
	}

	@Override
	public List<ProcardTemplate> findTotalProcardTemplateAll() {
		// TODO Auto-generated method stub
		List<ProcardTemplate> all = totalDao.query("from ProcardTemplate where productStyle ='试制' and procardStyle='总成' order by markId");
			return all;
	}

	@Override
	public boolean ischangeStatus(Integer id) {
		// TODO Auto-generated method stub
		TryMake tm=getById(id);
		if(tm.getInputNum()!=null&&tm.getInputNum()>0&&
				tm.getProTryMakeScore()!=null&&tm.getProTryMakeScore().getPoSize()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<ProcardTemplate> findPartsByProId(Integer id) {
		// TODO Auto-generated method stub
		//return totalDao.query("from ProcardTemplate where markId in (select markId from QuotedPrice where proId in (select id from ProjectManage where projectNum=(select proNum from ProTryMakeScore where id=?))) and procardStyle='总成'",id);
		return totalDao.query("from ProcardTemplate where markId in (select markId from QuotedPrice where proId in (select id from ProjectManage where projectNum=(select proNum from ProTryMakeScore where id=?))) order by markId",id);
//	  return totalDao.query("from ProcardTemplate where rootId=id");
	}
	public boolean makeLoadScore(ProcardTemplate pt){
		boolean b=true;
		if(pt!=null){
			//添加奖金负荷系数
			if(pt.getProcardStyle()!=null&&pt.getProcardStyle().equals("总成")){
				String sql1 = "from ProjectTime where level=1 and money is not null and quoId in (select id from QuotedPrice where id=rootId and markId in(select markId from ProcardTemplate where rootId=?)) order by id desc";
				ProjectTime projectTime=(ProjectTime) totalDao.getObjectByQuery(sql1, pt.getRootId());//项目时间表的中的数据用来查询单价零件总成本
				String sql3 ="from Price where produceType='销售' and hsPrice is not null and partNumber in (select markId from ProcardTemplate where rootId=?) order by id desc";
				Price price=(Price) totalDao.getObjectByQuery(sql1,pt.getRootId());//价格表中的数据用来查询单件零件销售价格
				BonusLoad bload=new BonusLoad();
				bload.setQpId(pt.getId());
				bload.setFatherId(pt.getFatherId());
				bload.setMarkId(pt.getMarkId());
				bload.setRootId(pt.getRootId());
				if(projectTime!=null){
					bload.setCost(projectTime.getMoney());
				}
				if(price!=null){
					bload.setSalePrice(price.getHsPrice());
				}
				if(bload.getCost()!=null
						&&bload.getCost()!=0
						&&bload.getSalePrice()!=null
						&&bload.getSalePrice()!=0){
					float bonusLoad=(float) (bload.getCost()/bload.getSalePrice());
					bload.setBonusLoad(Float.parseFloat(String.format("%.3f", bonusLoad)));
				}else{
					bload.setBonusLoad(0f);
				}
				b=b&totalDao.save(bload);
			}
			Set<ProcessTemplate> processTSet=pt.getProcessTemplate();
			if(processTSet!=null){
				for(ProcessTemplate processt:processTSet){
					//添加产能负荷系数
					ProductivityLoad pload = new ProductivityLoad();
						pload.setQpId(pt.getId());
						pload.setFatherId(pt.getFatherId());
						pload.setMarkId(pt.getMarkId());
						pload.setProcardName(pt.getProName());
						pload.setRootId(pt.getRootId());
						pload.setProcessId(processt.getId());
						pload.setProcessNO(processt.getProcessNO());
						pload.setProcessName(processt.getProcessName());
						if(processt.getAllJiepai()!=null){
							pload.setSingleTime(processt.getAllJiepai());
						}else if(processt.getOpcaozuojiepai()!=null&&processt.getOpshebeijiepai()!=null&&processt.getGzzhunbeijiepai()!=null&&processt.getGzzhunbeicishu()!=null){
							pload.setSingleTime(processt.getOpcaozuojiepai()+processt.getOpshebeijiepai()+(processt.getGzzhunbeijiepai()*processt.getGzzhunbeicishu()));
						}
						if(pload.getSingleTime()!=null&&pload.getSingleTime()!=0){
							float upNumber=8*3600/pload.getSingleTime();
							pload.setUpNumber(Float.parseFloat(String.format("%.3f",upNumber)));
						}else{
							pload.setUpNumber(null);
						}
						b = b & totalDao.save(pload);
					//添加工艺负荷系数
						CraftLoad cload = new CraftLoad();// 创建工艺负荷对象临时存储
						ProcessSopTemp processSopTemp=(ProcessSopTemp) totalDao.getObjectByCondition("from ProcessSopTemp where proName=?", processt.getProcessName());
						if(processSopTemp!=null){
							cload.setCraftLoadScore(processSopTemp.getCraftLoadScore());//工艺分值
							cload.setCraftComplexity(processSopTemp.getCraftComplexity());//工艺复杂分值
							cload.setCraftComplexityName(processSopTemp.getCraftComplexityName());//工艺复杂分值分类名称
							cload.setCraftSkillName(processSopTemp.getCraftSkillName());//工艺复杂分值技能名称
							cload.setCraftskillId(processSopTemp.getCraftskillId());//工艺复杂分值的技能系数Id
							
							cload.setProcessDifficulty(processSopTemp.getProcessDifficulty());//加工难点分值
							cload.setProcessDifficultyName(processSopTemp.getProcessDifficultyName());//加工难点分值分类名称
							cload.setPpdSkillName(processSopTemp.getPpdSkillName());//加工难点分值技能名称
							cload.setPpdSkillId(processSopTemp.getPpdSkillId());//加工难点分值的技能系数Id
							
							cload.setProcessPRNScore(processSopTemp.getProcessPRNScore());//工序prn评分总值
							cload.setRpnScore1(processSopTemp.getRpnScore1());//工序prn评分值1
							cload.setRpnScore2(processSopTemp.getRpnScore2());//工序prn评分值2
							cload.setRpnScore3(processSopTemp.getRpnScore3());//工序prn评分值3
						}else{
							processSopTemp= new ProcessSopTemp();
							processSopTemp.setProName(processt.getProcessName());
							totalDao.save(processSopTemp);
						}
						cload.setFatherId(pt.getFatherId());// 设置工艺负荷关于工序的属性
						cload.setQpId(pt.getId());
						cload.setRootId(pt.getRootId());
						cload.setMarkId(pt.getMarkId());
						cload.setProcardName(pt.getProName());
						cload.setProcessId(processt.getId());
						cload.setProcessNO(processt.getProcessNO());
						cload.setProcessName(processt.getProcessName());
						cload.setCraftLoadScore(0f);
						b = b & totalDao.save(cload);
					
						
				}
			}
			Set<ProcardTemplate> ptSet=pt.getProcardTSet();
			if(ptSet!=null){
				for(ProcardTemplate ptSon:ptSet){
					b=b&makeLoadScore(ptSon);
				}
			}else{
				return b;
			}
		}
		return b;
	}

	@Override
	public void addLoad() {
		// TODO Auto-generated method stub
		//弥补之前产能，工艺和奖金三种负荷系数
		List<Integer> rootIds= totalDao.query("select rootId from ProcardTemplate where id=rootId and productStyle='试制' and markId in (select markId from TryMake where inputNum>0) group by rootId");
		if(rootIds.size()>0){
			for(Integer rootId:rootIds){
				List list=totalDao.query("from CraftLoad where rootId=? ", rootId);
				if(list.size()==0){//这棵树之前没有产生过这三种系数
					ProcardTemplate pt=(ProcardTemplate) totalDao.getObjectById(ProcardTemplate.class,rootId);
					if(pt!=null){
						boolean b=makeLoadScore(pt);
						System.out.println(b);
					}
				}
			}
		}
	}

}
