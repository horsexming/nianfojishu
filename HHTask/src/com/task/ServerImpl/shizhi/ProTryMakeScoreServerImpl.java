package com.task.ServerImpl.shizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProTryMakeScoreServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Bonus;
import com.task.entity.Users;
import com.task.entity.project.ProjectManage;
import com.task.entity.shizhi.BonusLoad;
import com.task.entity.shizhi.BonusShiZhi;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.Cusimportance;
import com.task.entity.shizhi.GroupShiZhi;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProductivityLoad;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.TryMake;
import com.task.entity.shizhi.TryMakeApproval;
import com.task.entity.sop.Procard;
import com.task.entity.system.ExecutionNode;
import com.task.entity.vo.shizhivo.ProTryMakeScoreVo;
import com.task.util.Util;

public class ProTryMakeScoreServerImpl implements ProTryMakeScoreServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List<ProTryMakeScore> findAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from ProTryMakeScore");
		if (list.size() > 0) {
			List<ProTryMakeScore> ptmlist = list;
			return ptmlist;
		}
		return null;
	}

	@Override
	public Map<Integer, Object> findProTryMakrScoresByCondition(
			ProTryMakeScore proTryMakeScore, int pageNo, int pageSize,
			int status, String markId) {
		if (proTryMakeScore == null) {
			proTryMakeScore = new ProTryMakeScore();
		}
		String sql = null;
		if (status > 0) {
			sql = "poSize > 0";
		} else if(status==0) {
			sql = "poSize = 0";
		}else {
			sql = " 1=1 ";
		}
		if (markId != null && !markId.equals("")) {
			sql = sql
					+ " and id in (select proTryMakeScore.id from TryMake where markId like '%"
					+ markId + "%')";
		}
		String hql = null;
		if (status > 0) {
			// 查询已生成试制订单的项目试制
			hql = totalDao.criteriaQueries(proTryMakeScore, sql, null);
		} else {
			// 查询未生成试制订单的项目试制
			hql = totalDao.criteriaQueries(proTryMakeScore, sql, null);
		}
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql+" order by id desc", pageNo, pageSize);
		List<ProTryMakeScoreVo> ptmVoList = new ArrayList<ProTryMakeScoreVo>();
		if (objs.size() > 0) {
			for (Object o : objs) {
				ptmVoList.add(new ProTryMakeScoreVo((ProTryMakeScore) o));
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, ptmVoList);
		map.put(2, count);
		return map;
		// TODO Auto-generated method stub

	}

	@Override
	public ProTryMakeScore getById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(ProTryMakeScore.class, id);
		if (o != null) {
			return (ProTryMakeScore) o;
		}
		return null;
	}

	@Override
	public List<TryMake> getTryMakeMap(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectOrder> findProjectOrderAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from ProjectOrder");
		if (list.size() > 0) {
			List<ProjectOrder> polist = list;
			return polist;
		}
		return null;
	}

	@Override
	public List<BonusLoad> findBonusLoadAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from BonusLoad");
		if (list.size() > 0) {
			List<BonusLoad> bllist = list;
			return bllist;
		}
		return null;
	}

	@Override
	public List<CraftLoad> findCraftLoadAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from CraftLoad");
		if (list.size() > 0) {
			List<CraftLoad> cllist = list;
			return cllist;
		}
		return null;
	}

	@Override
	public List<Cusimportance> findCusimportanceAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from Cusimportance");
		if (list.size() > 0) {
			List<Cusimportance> cimlist = list;
			return cimlist;
		}
		return null;
	}

	@Override
	public List<ProductivityLoad> findProductivityLoadAll() {
		// TODO Auto-generated method stub
		List list = totalDao.query("from ProductivityLoad");
		if (list.size() > 0) {
			List<ProductivityLoad> pllist = list;
			return pllist;
		}
		return null;
	}

	@Override
	public TryMake getTryMakeById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(TryMake.class, id);
		if (o != null) {
			return (TryMake) o;
		}
		return null;
	}

	@Override
	public boolean updateTryMake(TryMake tryMake) {
		// TODO Auto-generated method stub
		TryMake t = getTryMakeById(tryMake.getId());
		if (t != null) {
			t.setInputNum(tryMake.getInputNum());
			t.setProjectStatu(tryMake.getProjectStatu());
			return totalDao.update(t);
		}
		return false;
	}

	@Override
	public boolean updatePro(ProTryMakeScore proTryMakeScore) {
		// TODO Auto-generated method stub
		ProTryMakeScore ptm = getById(proTryMakeScore.getId());
		if (ptm != null) {
			ptm.setProNum(proTryMakeScore.getProName());
			return totalDao.update(ptm);
		}
		return false;
	}

	@Override
	public boolean add(ProTryMakeScore proTryMakeScore) {
		// TODO Auto-generated method stub
		boolean b= totalDao.save(proTryMakeScore);
		//添加客户重要系数
		List<Cusimportance> list=totalDao.query("from Cusimportance where cuName =?" ,proTryMakeScore.getCusName());
	    if(list.size()>0){
	    	return b;
	    }else{
	    	Cusimportance cus=new Cusimportance();
	    	cus.setCuName(proTryMakeScore.getCusName());
	    	return b&totalDao.save(cus);
	    }
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		ProTryMakeScore ptm = getById(id);
		if (ptm != null) {
			ptm.setProjectOrder(null);
			return totalDao.delete(ptm);
		}
		return false;
	}

	@Override
	public List<String> getAllCusName() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from Cusimportance");
		if (all.size() > 0) {
			List<String> cusNames = new ArrayList<String>();
			for (Object o : all) {
				Cusimportance c = ((Cusimportance) o);
				String cusName = c.getCuName();
				cusNames.add(cusName);
			}
			return cusNames;
		}
		return null;
	}

	@Override
	public List<String> getAllGroupName() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from GroupShiZhi");
		if (all.size() > 0) {
			List<String> gNames = new ArrayList<String>();
			for (Object o : all) {
				GroupShiZhi c = ((GroupShiZhi) o);
				String gName = c.getName();
				gNames.add(gName);
			}
			return gNames;
		}
		return null;
	}

	@Override
	public List<ProTryMakeScoreVo> getAllBouns(String proName) {
		// TODO Auto-generated method stub
		List objs = totalDao.query("from ProTryMakeScore where proName ='"
				+ proName + "'");
		List<ProTryMakeScoreVo> ptmVoList = new ArrayList<ProTryMakeScoreVo>();
		if (objs.size() > 0) {
			for (Object o : objs) {
				ptmVoList.add(new ProTryMakeScoreVo((ProTryMakeScore) o));
			}
		}
		return ptmVoList;
	}

//	@Override
//	public void updateRate() {
//		// TODO Auto-generated method stub
//		List list = totalDao
//				.query("from ProjectOrder where status = '同意'");
//		if (list.size() > 0) {
//			List<String> monthList = new ArrayList<String>();
//			for (int i = 0; i < list.size(); i++) {
//				ProjectOrder projectOrder = (ProjectOrder) list.get(i);
//					updateInputNum(projectOrder);
//					String month = projectOrder.getMonth();
//					if(!monthList.contains(month)){
//						monthList.add(month);
//					}
//					totalDao.update(projectOrder);
//			}
//			if (monthList.size() > 0) {
//				for (String m : monthList) {
//					SetTryMakeRateByMonth(m);
//				}
//			}
//		}
//	}

	@Override
	public boolean SetTryMakeRateByMonth(String month) {
		// TODO Auto-generated method stub
		boolean b = true;
		if(month==null){
			return b;
		}
		String sql = "from ProTryMakeScore where id in (select proTryMakeScore.id from TryMake where inputNum>0) and month = '"
				+ month + "'";
		List list = totalDao.query(sql);// 获取所选的月的所有有入库零件的项目试制评审对象
		if (list.size() > 0) {
			List<ProTryMakeScore> ptmsList = list;
			List<TryMake> tmList = new ArrayList<TryMake>();// 用来存放所有该月需要参与计算占比的项目试制零件
			for (ProTryMakeScore ptms : ptmsList) {
				Set<TryMake> tmSet = ptms.getTryMake();
				if (tmSet.size() > 0) {
					for (TryMake t : tmSet) {
						if (t.getInputNum() != null && t.getInputNum() > 0) {
							tmList.add(t);// 如果有入库数量则表示该项目试制零件参与计算占比
						}
					}
				}

			}
			// 第一次循环计算出各负荷值并记录各负荷的总值
			float bloadtotal = 0f;
			float cloadtotal = 0f;
			float ploadtotal = 0f;
			float cimtotal = 0f;
			List<BonusLoad> bloadList = totalDao.query("from BonusLoad where qpId in (select qpId from TryMake where inputNum>0 and  proTryMakeScore.id in (select id from ProTryMakeScore where month=?)) ",month);
			List<CraftLoad> cloadList = totalDao.query("from CraftLoad where rootId in (select qpId from TryMake where inputNum>0 and  proTryMakeScore.id in (select id from ProTryMakeScore where month=?)) ",month);
			List<ProductivityLoad> ploadList = totalDao.query("from ProductivityLoad where rootId in (select qpId from TryMake where inputNum>0 and  proTryMakeScore.id in (select id from ProTryMakeScore where month=?)) ",month);
			List<Cusimportance> cimList = totalDao.query("from Cusimportance where cuName in (select cusName from ProTryMakeScore where month=? and id in (select proTryMakeScore.id from TryMake where inputNum>0))",month);
			if (tmList.size() > 0) {
				for (TryMake tm : tmList) {
					// 设置奖金负荷系数
					if (bloadList != null) {
						// tmVo.setbLoadRate(0f);
						float bLoadScore = 0f;
						for (BonusLoad bload : bloadList) {
							if (tm.getQpId() != null
									&& bload.getRootId() != null
									&& tm.getQpId().equals(bload.getRootId())
									&& bload.getBonusLoad() != null) {
								bLoadScore += bload.getBonusLoad();
							}
						}
						bloadtotal += bLoadScore;
						tm.setBloadScore(Float.parseFloat(String.format("%.4f",
								bLoadScore)));
					}
					// 设置产能负荷系数
					if (ploadList != null) {
						// tm.setbLoadRate(0f);
						float pLoadScore = 0f;
						int count =0;
						for (ProductivityLoad pload : ploadList) {
							if (tm.getQpId() != null
									&& pload.getRootId() != null
									&& tm.getQpId().equals(pload.getRootId())
									&& pload.getProLoad() != null) {
								pLoadScore += pload.getProLoad();
								count++;
							}
						}
						if(count!=0){
							pLoadScore=pLoadScore/count;
						}
						ploadtotal += pLoadScore;
						tm.setPloadScore(Float.parseFloat(String.format("%.4f",
								pLoadScore)));
					}
					// 设置工艺负荷系数
					if (cloadList != null) {
						// tm.setbLoadRate(0f);
						float cLoadScore = 0f;
						int count=0;
						for (CraftLoad cload : cloadList) {
							if (tm.getQpId() != null
									&& cload.getRootId() != null
									&& tm.getQpId().equals(cload.getRootId())
									&& cload.getCraftLoadScore() != null) {
								cLoadScore += cload.getCraftLoadScore();
								count++;
							}
						}
						if(count!=0){
							cLoadScore=cLoadScore/count;
						}
						cloadtotal += cLoadScore;
						tm.setCloadScore(Float.parseFloat(String.format("%.4f",
								cLoadScore)));
					}
					// 设置客户重要系数
					if (cimList != null) {
						// tm.setbLoadRate(0f);
						float cimScore = 0f;
						for (Cusimportance cim : cimList) {
							ProTryMakeScore ptm = tm.getProTryMakeScore();
							if (ptm != null && ptm.getCusName() != null
									&& cim.getCuName() != null
									&& ptm.getCusName().equals(cim.getCuName())
									&& cim.getImprotance() != null) {
								cimScore += cim.getImprotance();
							}
						}
						cimtotal += cimScore;
						tm.setCimScore(Float.parseFloat(String.format("%.4f",
								cimScore)));
					}
				}
			}
			// 第二次循环算出各负荷占比和各负荷占比相加的值并记录各负荷占比相加值得总和
			float totalScore = 0;// 总比分总分
			if (tmList.size() > 0) {
				for (TryMake tm : tmList) {
					if (bloadtotal != 0) {
						tm.setBloadRate(Float.parseFloat(String.format("%.4f",
								tm.getBloadScore() / bloadtotal)));
					} else {
						tm.setBloadRate(0f);
					}
					if (cloadtotal != 0) {
						tm.setCloadRate(Float.parseFloat(String.format("%.4f",
								tm.getCloadScore() / cloadtotal)));
					} else {
						tm.setCloadRate(0f);
					}
					if (ploadtotal != 0) {
						tm.setPloadRate(Float.parseFloat(String.format("%.4f",
								tm.getPloadScore() / ploadtotal)));
					} else {
						tm.setPloadRate(0f);
					}
					if (cimtotal != 0) {
						tm.setCimRate(Float.parseFloat(String.format("%.4f", tm
								.getCimScore()
								/ cimtotal)));
					} else {
						tm.setCimRate(0f);
					}
					float totalRate = tm.getBloadRate() + tm.getCimRate()
							+ tm.getCloadRate() + tm.getPloadRate();
					totalScore += totalRate;
					tm.setTotalRate(Float.parseFloat(String.format("%.4f",
							totalRate)));
				}
			}
			// 第三次循环计算各负荷占比相加值得占比
			Float money = 0f;
			List Bonus = totalDao.query("from BonusShiZhi where month = '"
					+ month + "'");
			if (Bonus.size() > 0) {
				BonusShiZhi bShiZhi = (BonusShiZhi) Bonus.get(0);
				if (bShiZhi.getBonus() != null) {
					money = bShiZhi.getBonus();
				}
			}
			if (tmList.size() > 0) {
				for (TryMake tm : tmList) {
					tm.setAllTotalRate(totalScore);
					if (totalScore != 0f) {
						tm.setTotalBonus(Float.parseFloat(String.format("%.4f",
								money * tm.getTotalRate() / totalScore)));
						tm.setBonus(tm.getTotalBonus()*tm.getInputNum()/tm.getApprovalNum());
					}else{
						tm.setBonus(0f);
						tm.setTotalBonus(0f);
					}
					b = b & totalDao.update(tm);
				}
			}
		}
		return b;
	}

//	@Override
//	public void updateInputNum(ProjectOrder projectOrder) {
//		// TODO Auto-generated method stub
//		ProTryMakeScore p = projectOrder.getProTryMakeScore();
//		if (p != null) {
//			Integer poSize = p.getProjectOrder().size();
//			p.setPoSize(poSize);
//			Set<ProjectOrderPart> popSet = projectOrder.getProjectOrderPart();
//			Set<TryMake> tmSet = p.getTryMake();
//			if (popSet.size() > 0 && tmSet.size() > 0) {
//				for (ProjectOrderPart pop : popSet) {
//					for (TryMake tm : tmSet) {
//						if (pop.getTmId() != null
//								&& tm.getId().equals(pop.getTmId())) {
//							Integer inputNum = 0;
//							inputNum += pop.getPartNum();
//							tm.setInputNum(inputNum);
//						}
//					}
//				}
//			}
//			p.setTryMake(tmSet);
//			totalDao.update(p);
//		}
//
//	}

	@Override
	public List<ProjectManage> getAllProjectManage() {
		// TODO Auto-generated method stub
		return totalDao.query("from ProjectManage where aduitStatus='同意'");
	}

	@Override
	public ProjectManage getProjectManageById(Integer id) {
		// TODO Auto-generated method stub
		ProjectManage pm=(ProjectManage)totalDao.getObjectById(ProjectManage.class, id);
		return pm;
	}

	@Override
	public Map<Integer, Object> updateLoadAndgetApprovalOrPrint(String month) {
		// TODO Auto-generated method stub
		Map<Integer,Object> map=new HashMap<Integer, Object>();
		List<Integer> epIdList=totalDao.query("select epId from TryMakeApproval where status ='当前' and month=?", month);
		if(epIdList.size()>0){
			map.put(0, epIdList.get(0));
		}
		String sql1="from ProTryMakeScore where month=? and approvalId in (select id from TryMakeApproval where approvalStatus='同意' and status ='当前' and month=?)";
		List<ProTryMakeScore> list1=totalDao.query(sql1, month,month);
		if(list1.size()>0){//表示该月的试制已被审批同意
			map.put(1, "print");
			List<ProTryMakeScoreVo> voList=new ArrayList<ProTryMakeScoreVo>();
			for(ProTryMakeScore ptm:list1){
				ptm.setApprovalStatus("同意");
				ProTryMakeScoreVo vo=new ProTryMakeScoreVo();
				vo.setVoVaule(ptm);
				voList.add(vo);
			}
			map.put(2, voList);
			TryMakeApproval tryMakeApproval=(TryMakeApproval) totalDao.getObjectById(TryMakeApproval.class, list1.get(0).getApprovalId());
			List<String> qianmingList=new ArrayList<String>();
			if(tryMakeApproval.getEpId()!=null){
				List<ExecutionNode> list=totalDao.query("from ExecutionNode where circuitRun.id=? order by auditLevel", tryMakeApproval.getEpId());
			    if(list.size()>0){
			    	for(ExecutionNode en:list){
			    		List<String> qmlist=totalDao.query("select signature_address from Signature where code=(select code from Users where id=?)", en.getAuditUserId());
			    	     if(qmlist.size()>0){
			    	    	 qianmingList.add(qmlist.get(0));
			    	     }
			    	}
			    }
			}
			map.put(3, qianmingList);
			return map;
		}
		String sql3="from ProTryMakeScore where month=? and  approvalId in (select id from TryMakeApproval where approvalStatus ='未审批' and status ='当前')";
		String sql4="from ProTryMakeScore where month=? and  approvalId in (select id from TryMakeApproval where approvalStatus ='审批中' and status ='当前')";
		List<ProTryMakeScore> list3=totalDao.query(sql3, month);
		List<ProTryMakeScore> list4=totalDao.query(sql4, month);
		if(list3.size()>0||list4.size()>0){//表示该月的试制未审批或者正在审批中
			map.put(1, "none");
			List<ProTryMakeScoreVo> voList=new ArrayList<ProTryMakeScoreVo>();
			for(ProTryMakeScore ptm:list3){
				ptm.setApprovalStatus("未审批");
				ProTryMakeScoreVo vo=new ProTryMakeScoreVo();
				vo.setVoVaule(ptm);
				voList.add(vo);
			}
			for(ProTryMakeScore ptm:list4){
				ptm.setApprovalStatus("审批中");
				ProTryMakeScoreVo vo=new ProTryMakeScoreVo();
				vo.setVoVaule(ptm);
				voList.add(vo);
			}
			map.put(2, voList);
			return map;
		}
		//该月的试制奖金未审批或者已被打回，从新计算该月的奖金额
		SetTryMakeRateByMonth(month);
		String sql2="from ProTryMakeScore where month=? and (approvalId is null or approvalId in(select id from TryMakeApproval where approvalStatus='打回' and status ='当前')) and id in (select proTryMakeScore.id from TryMake where inputNum>0)";
		List<ProTryMakeScore> list2=totalDao.query(sql2, month);
		if(list2.size()>0){//表示该月的试制未申请或者已被打回
			map.put(1, "approval");
			List<ProTryMakeScoreVo> voList=new ArrayList<ProTryMakeScoreVo>();
			for(ProTryMakeScore ptm:list2){
				if(ptm.getApprovalId()!=null){
					ptm.setApprovalStatus("打回");
				}else{
					ptm.setApprovalStatus("未申请");
				}
				ProTryMakeScoreVo vo=new ProTryMakeScoreVo();
				vo.setVoVaule(ptm);
				voList.add(vo);
			}
			map.put(2, voList);
			return map;
		}
		
		return null;
	}

	@Override
	public boolean addApprovalOneMonth(String month) throws Exception {
		// TODO Auto-generated method stub
		List<TryMakeApproval> list=	totalDao.query("from TryMakeApproval where month=? and approvalStatus in ('未审批','审批中','同意') and status ='当前'",month);
		 if(list.size()>0){
			 return false;
		 }
		 //处理打回
		 String sql2="from ProTryMakeScore where month=? and approvalId in(select id from TryMakeApproval where approvalStatus='打回' and status ='当前') and id in (select proTryMakeScore.id from TryMake where inputNum>0)";
		 List<ProTryMakeScore> list2= totalDao.query(sql2, month);
		 if(list2.size()>0){//将原来的申请的状态改成历史
			 TryMakeApproval approval=	(TryMakeApproval) totalDao.getObjectByQuery("from TryMakeApproval where month=? and approvalStatus='打回'", month);
			 if(approval!=null&&approval.getEpId()!=null){
				 approval.setStatus("历史");
				 totalDao.delete(approval);
			 }
			 for(ProTryMakeScore ptm:list2){
				 ptm.setApprovalId(null);
				 totalDao.update(ptm);
			 }
		 }
		 
		 TryMakeApproval tryMakeApproval=new TryMakeApproval();
		 tryMakeApproval.setMonth(month);
		 tryMakeApproval.setStatus("当前");
		 tryMakeApproval.setApprovalStatus("未审批");
		 tryMakeApproval.setAddTime(Util.getDateTime());
		 totalDao.save(tryMakeApproval);
		 Integer epId =null;
		 try{
			 epId= CircuitRunServerImpl.createProcess("试制奖金审批流程",
					 TryMakeApproval.class, tryMakeApproval.getId(), "approvalStatus", "id", "proTryMakeScoreAction_toApprovalOrPrint.action?flag=view&month="+month,"申请"+month+"月份试制奖金",
						true);
		 }catch (Exception e) {
			// TODO: handle exception
		}
		 if(epId==null){
			 throw new Exception("申请失败，不存在该审批流程");
		 }else{
			 tryMakeApproval.setEpId(epId);
			 totalDao.update(tryMakeApproval);
			 String sql3="from ProTryMakeScore where month=? and id in (select proTryMakeScore.id from TryMake where inputNum>0)";
			 List<ProTryMakeScore> list3= totalDao.query(sql3, month);
			 if(list3.size()>0){
				 for(ProTryMakeScore ptm:list3){
					 ptm.setApprovalId(tryMakeApproval.getId());
					 totalDao.update(ptm);
				 }
			 }
			 return true;
		 }
		 
	}

	@Override
	public void addCusimportance() {
		// TODO Auto-generated method stub
		//弥补客户重要系数
		List<String> cusNameList=totalDao.query("select cusName from ProTryMakeScore where cusName not in (select cuName from Cusimportance) group by cusName");
		if(cusNameList.size()>0){
			for(String cusName:cusNameList){
				Cusimportance cus=new Cusimportance();
		    	cus.setCuName(cusName);
		    	boolean b=totalDao.save(cus);
		    	System.out.println(b);
			}
		}
	}

	@Override
	public List<Procard> getprocardByTrymakeId(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from Procard  where markId=(select markId from TryMake where id=?) and planOrderId in (select id from ProjectOrder where proTryMakeScore.id=(select proTryMakeScore.id from TryMake where id=?)) ", id,id);
	}

	@Override
	public List getUserBonus(String month) {
		// TODO Auto-generated method stub
		List<TryMake> tryMakeList=totalDao.query("from TryMake where inputNum>0 and proTryMakeScore.id in (select id from ProTryMakeScore where month=?)", month);
		if(tryMakeList!=null&&tryMakeList.size()>0){
			List<Object[]> codeAndPriceList= new ArrayList<Object[]>();//工号和奖金
			for(TryMake tryMake:tryMakeList){
				Float totalJiePai=0f;//该试制件号下面的总节拍
				Float totalJiePaitest=0f;//该试制件号下面的总节拍
				List<Object[]> codeAndJiepailist=new ArrayList<Object[]>();//获取该试制件号下面的所有的人员工号和对应的节拍
				List<Integer> procardRootIdList=totalDao.query("select rootId from Procard  where status in ('完成','待入库','入库') and markId=(select markId from TryMake where id=?) and planOrderId in (select id from ProjectOrder where proTryMakeScore.id=(select proTryMakeScore.id from TryMake where id=?)) ", tryMake.getId(),tryMake.getId());
			    if(procardRootIdList!=null&&procardRootIdList.size()>0){
			      for(Integer rootId:procardRootIdList){	
			    	  //虽然一个试制零件下面只会是一种bom但是因为试制的原因可能生产的多批的节拍不一样所以都要用来计算不能按一套来定
			    	  Float allJiePai=(Float) totalDao.getObjectByCondition("select sum(allJiepai) from ProcessInforReceiveLog where userId in (select id from Users where internal='是') and fk_processInforId in(select id from ProcessInfor where procard.id in(select id from Procard where rootId=?))", rootId);
			    	  if(allJiePai!=null){
			    		  totalJiePai+= allJiePai;
			    	  }
			    	  List<Object[]> codeAndJiepailist2=totalDao.query("select usercodes, sum(allJiepai) from ProcessInforReceiveLog where userId in (select id from Users where internal='是') and fk_processInforId in(select id from ProcessInfor where procard.id in(select id from Procard where rootId=?)) group by usercodes", rootId);
			          if(codeAndJiepailist2!=null&&codeAndJiepailist2.size()>0){
			        	  codeAndJiepailist.addAll(codeAndJiepailist2);
			        	  for(Object[] c: codeAndJiepailist2){
			        		  totalJiePaitest+=Float.parseFloat(c[1].toString());
			        	  }
			          }
			      }
			      Float singePrice=0f;//该试制下秒节拍的金额
			      if(totalJiePai!=0){
			    	  singePrice= tryMake.getBonus()/totalJiePai;
			      }
			      if(codeAndJiepailist!=null){
			    	  List<Object[]> codeAndPriceList2= new ArrayList<Object[]>();
			    	  List<String> codes=new ArrayList<String>();
			    	  for(Object[] codeAndJiepail: codeAndJiepailist){
			    		  if(codeAndJiepail[1]!=null){
			    			 Float money=Float.parseFloat(codeAndJiepail[1].toString())*singePrice;
			    		     if(codeAndJiepail[0]!=null){
			    		    		 if(codes.contains(codeAndJiepail[0].toString())){
			    		    			 for(Object[] codeAndPrice:codeAndPriceList2){
			    		    				 if(codeAndPrice[0].equals(codeAndJiepail[0])){
			    		    					 codeAndPrice[1]=Float.parseFloat(codeAndPrice[1].toString())+money;
			    		    					 break;
			    		    				 }
			    		    			 }
			    		    		 }else{
			    		    			 codeAndPriceList2.add(new Object[]{codeAndJiepail[0],money});
			    		    			 codes.add(codeAndJiepail[0].toString());
			    		    		 }
			    		     }
			    		  }
			    	  }
			    	  codeAndPriceList.addAll(codeAndPriceList2);
			      }
			    }
				
			
			
			}
			if(codeAndPriceList.size()>0){
				Float moneytest=0f;
				List<Object[]> idAndUserAndMoneyList=new ArrayList<Object[]>();
				List<String> idList=new ArrayList<String>();
				for(Object[] codeAndPrice2: codeAndPriceList){
					String[] codes=codeAndPrice2[0].toString().split(",");
						if(codes!=null&&codes.length>0){
							for(String code:codes){
								Users user=(Users) totalDao.getObjectByCondition("from Users where code=?", code);
							    if(user!=null){
							    	if(idList.contains(user.getId().toString())){
							    		for(Object[] idAndUserAndMoney:idAndUserAndMoneyList){
							    			if(idAndUserAndMoney[0].equals(user.getId())){
							    				idAndUserAndMoney[2]=Float.parseFloat(idAndUserAndMoney[2].toString())+(Float.parseFloat(codeAndPrice2[1].toString())/codes.length);
							    				moneytest+=Float.parseFloat(codeAndPrice2[1].toString())/codes.length;
							    				break;
							    			}
							    		}
							    	}else{
							    		idAndUserAndMoneyList.add(new Object[]{user.getId(),user,Float.parseFloat(codeAndPrice2[1].toString())/codes.length});
							    		moneytest+=Float.parseFloat(codeAndPrice2[1].toString())/codes.length;
							    		idList.add(user.getId().toString());
							    	}
							    }
							}
						}
				}
				return idAndUserAndMoneyList;
			}
		}
		return null;
	}

	@Override
	public boolean checkTryMakeApproval(String month) {
		// TODO Auto-generated method stub
		String approvalStatus=(String) totalDao.getObjectByCondition("select approvalStatus from TryMakeApproval where status='当前' and month=?", month);
		if(approvalStatus!=null&&approvalStatus.equals("同意")){
			return true;
		}
		return false;
	}

	@Override
	public boolean addUserBonus(String month) {
		// TODO Auto-generated method stub
		List<Object[]> list = getUserBonus(month);
		String sql="from Bonus where bonusdata=? and shizhiMoney is null";
		List<Bonus> bonusList=totalDao.query(sql, month);
		boolean b=true;
		if(bonusList.size()>0&&list!=null&&list.size()>0){
			for(Bonus bonus:bonusList){
				for(Object[] objs:list){
					Users user=(Users) objs[1];
					if(user.getCode().equals(bonus.getBonusmembernumber())){
						bonus.setShizhiMoney(Float.parseFloat(objs[2].toString()));
						b=b&totalDao.update(bonus);
						break;
					}
				}
			}
			return b;
		}
		return false;
	}

	@Override
	public boolean hasDistributionBonus(String month) {
		// TODO Auto-generated method stub
		//List<Bonus> list=totalDao.query("from Bonus where shizhiMoney>0 and bonusdata=?", agr)
		return false;
	}
	

}
