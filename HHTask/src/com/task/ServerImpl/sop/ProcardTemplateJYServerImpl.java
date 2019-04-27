package com.task.ServerImpl.sop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.InsuranceGoldServer;
import com.task.Server.WageStandardServer;
import com.task.Server.sop.ProcardTemplateJYServer;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.DTOProcess;
import com.task.entity.DataGrid;
import com.task.entity.InsuranceGold;
import com.task.entity.Machine;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.VOProductTree;
import com.task.entity.WageStandard;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.GongyiGuichengAffix;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.gzbj.Measuring;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.jy.ProcardTemplateJY;
import com.task.entity.sop.jy.ProcessTemplateJY;
import com.task.util.Util;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.ProcessMarkIdZijian;

public class ProcardTemplateJYServerImpl implements ProcardTemplateJYServer {

	private TotalDao totalDao;
	private WageStandardServer wss; // 工资模板
	private InsuranceGoldServer igs; // 五险一金Server层
	private List<Integer> deptIds=null;
	private static final Double SECONDS = 619200.0;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 根据名称查询量具
	 */
	public List listliangju(String markId) {
		if (markId != null) {
			List procardTemplates = totalDao
					.query("from Measuring where matetag like '%" + markId
							+ "%'");
			return procardTemplates;
		}
		return null;
	}

	/***
	 * 根据工装编号查询工装
	 */
	public List listGzstoreBycarModel(String code) {
		if (code != null) {
			List procardTemplates = totalDao
					.query("from Gzstore where matetag like '%" + code + "%'");
			return procardTemplates;
		}
		return null;
	}



	/***
	 * 根据id查询流水卡片模板
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	@Override
	public ProcardTemplateJY findProcardTemById(int id) {
		if ((Object) id != null && id > 0) {
			return (ProcardTemplateJY) totalDao.getObjectById(
					ProcardTemplateJY.class, id);
		}
		return null;
	}


	/***
	 * 查询所有总成流水卡片模板(分页)
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	@Override
	public Object[] findAllProcardTemplateJY(
			ProcardTemplateJY procardTemplateJY, int pageNo, int pageSize,
			String sql) {
		if (procardTemplateJY == null) {
			procardTemplateJY = new ProcardTemplateJY();
			procardTemplateJY.setBelongLayer(1);// 只查询第一层的总成模板
		}
		String hql = totalDao.criteriaQueries(procardTemplateJY, sql);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 根据首层父类id查询流水卡片模板(组装树形结构)
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	@Override
	public List findProcardTemByRootId(int rootId) {
		if ((Object) rootId != null && rootId > 0) {
			String hql = "from ProcardTemplateJY where rootId=?";
			return totalDao.query(hql, rootId);
		}
		return null;
	}


	/***
	 * 通过流水卡片id(外键)查询对应工序信息
	 * 
	 * @param fkId
	 * @return
	 */
	@Override
	public List findProcessByFkId(Integer fkId) {
		if (fkId != null && fkId > 0) {
			String hql = "from ProcessTemplateJY pc where pc.procardTemplateJY.id=? order by pc.processNO";
			return totalDao.query(hql, fkId);
		}
		return null;
	}

	/***
	 * 查询流水卡片模板(页面显示流水卡片模板详细使用)
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Object[] findCardTemForShow(int id) {
		if ((Object) id != null && id > 0) {
			ProcardTemplateJY pc = (ProcardTemplateJY) totalDao.getObjectById(
					ProcardTemplateJY.class, id);
			if (pc != null) {
				// 下层流水卡片模板
				Set<ProcardTemplateJY> pcSet = pc.getProcardTJYSet();
				List<ProcardTemplateJY> pclist = new ArrayList<ProcardTemplateJY>();
				pclist.addAll(pcSet);
				// 对应工序信息
				Set<ProcessTemplateJY> pceSet = pc.getProcessTemplateJY();
				List<ProcessTemplateJY> pcelist = new ArrayList<ProcessTemplateJY>();
				pcelist.addAll(pceSet);

				return new Object[] { pc, pclist, pcelist };
			}
		}
		return null;
	}



	/***
	 * 删除工序信息
	 */
	public void delProcessT(ProcessTemplateJY processT) {
		totalDao.delete(processT);
	}

	@SuppressWarnings("unchecked")
	public String packageData(Integer id, Map map) {
		int count = 1;
		DataGrid dg = new DataGrid();
		int length = 1;
		// 查询所有零组件信息
		String hql = "from ProcardTemplateJY where rootId=? and procardStyle<>'外购'";
		List<ProcardTemplateJY> proTemList = totalDao.query(hql, id);
		length += proTemList.size();
		for (ProcardTemplateJY proTem : proTemList) {

			// 组装零件信息
			VOProductTree part = new VOProductTree(count++, proTem.getMarkId(),
					proTem.getProName(), 0D, null, proTem.getId());
			// 获得零件对应工序信息
			Set<ProcessTemplateJY> processTemSet = proTem
					.getProcessTemplateJY();
			if (processTemSet != null && processTemSet.size() > 0) {
				length += processTemSet.size();
				for (ProcessTemplateJY processTem : processTemSet) {
					// 数据有效性效验
					Double OPLabourBeat = 0.0; // 人工节拍
					Double OPEquipmentBeat = 0.0; // 设备节拍
					Double PRLabourBeat = 0.0; // 人工节拍
					Double PRPrepareIndex = 0.0; // 准备次数
					if (processTem.getOpcaozuojiepai() != null)
						OPLabourBeat = processTem.getOpcaozuojiepai()
								.doubleValue();
					if (processTem.getOpshebeijiepai() != null)
						OPEquipmentBeat = processTem.getOpshebeijiepai()
								.doubleValue();
					if (processTem.getGzzhunbeijiepai() != null)
						PRLabourBeat = processTem.getGzzhunbeijiepai()
								.doubleValue();
					if (processTem.getGzzhunbeicishu() != null)
						PRPrepareIndex = processTem.getGzzhunbeicishu()
								.doubleValue();

					VOProductTree process = null;
					if (map != null && map.size() > 0) {
						DTOProcess dto = (DTOProcess) map.get(processTem
								.getId());
						process = new VOProductTree(count++, processTem
								.getProcessName(), processTem.getProcessNO()
								.toString(), processTem.getShebeiNo(), dto
								.getOPLabourBeat(), dto.getOPEquipmentBeat(),
								dto.getPRLabourBeat(), dto.getPRPrepareTIme(),
								dto.getHandlers(),
								processTem.getProcessMomey(), part.getId(),
								processTem.getId(), "PR", dto.getSumMoney(),
								dto.getUnitPrice(), dto.getJobNum());
					} else {
						process = new VOProductTree(count++, processTem
								.getProcessName(),
								processTem.getProcessNO().toString(),
								processTem.getShebeiNo(), OPLabourBeat,
								OPEquipmentBeat, PRLabourBeat, PRPrepareIndex,
								processTem.getOperatorName(), processTem
										.getProcessMomey(), part.getId(),
								processTem.getId(), "PR", null, null,
								processTem.getOperatorCode());
					}
//					String handlers=null;
//					if(processTem.getShebeistatus()!=null&&processTem.getShebeistatus().equals("yes")){
//						
//					}
					process.setHandlers("<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=ji&id="+processTem.getId()+"' target='_blank'>机</a> /"
							+ "<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=gong&id="+processTem.getId()+"' target='_blank'>工</a> /"
							+ "<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=liang&id="+processTem.getId()+"' target='_blank'>量</a> /"
							+ "<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=jian&id="+processTem.getId()+"' target='_blank'>检</a> /"
							+ "<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=wen&id="+processTem.getId()+"' target='_blank'>文</a> /"
							+ "<a href='procardTemplateJYAction_toupdatejgljwr.action?pageStatus=ren&id="+processTem.getId()+"' target='_blank'>人</a> ");
					part.getChildren().add(process);
				}
			}
			dg.getRows().add(part);
		}
		dg.setTotal(new Long(length));
		String jsonStr = JSON.toJSON(dg).toString();
		return jsonStr;
	}
	@SuppressWarnings("unchecked")
	public String packageData2(Integer id, Map map) {
		int count = 1;
		DataGrid dg = new DataGrid();
		int length = 1;
		// 查询所有零组件信息
		String hql = "from ProcardTemplateJY where rootId=? and procardStyle<>'外购'";
		List<ProcardTemplateJY> proTemList = totalDao.query(hql, id);
		length += proTemList.size();
		for (ProcardTemplateJY proTem : proTemList) {
			
			// 组装零件信息
			VOProductTree part = new VOProductTree(count++, proTem.getMarkId(),
					proTem.getProName(), 0D, null, proTem.getId());
			// 获得零件对应工序信息
			Set<ProcessTemplateJY> processTemSet = proTem
			.getProcessTemplateJY();
			if (processTemSet != null && processTemSet.size() > 0) {
				length += processTemSet.size();
				for (ProcessTemplateJY processTem : processTemSet) {
					// 数据有效性效验
					 Float allJiepai=processTem.getAllJiepai();//总节拍
					 String productStyle=processTem.getProductStyle();//生产类型
					 String processStatus=processTem.getProcessStatus();//是否并行
					 Float capacity=processTem.getCapacity();//产能
					 Float capacitySurplus=processTem.getCapacitySurplus();//产能盈余
					 Float capacityRatio=processTem.getCapacityRatio();//产能比
					 Float alldeliveryDuration=processTem.getAlldeliveryDuration();//延误时长
					 Integer deliveryPeriod=processTem.getDeliveryPeriod();//配送周期
					 Float deliveryAmount=processTem.getDeliveryAmount();//送货量
					 Float proSingleDuration=processTem.getProSingleDuration();//总成单班生产时长
					 VOProductTree process = null;
						process = new VOProductTree(count++,processTem.getProcessName(), allJiepai,productStyle,processStatus,
						 capacity,capacitySurplus,capacityRatio,alldeliveryDuration,deliveryPeriod,
						 deliveryAmount,proSingleDuration);
					part.getChildren().add(process);
				}
			}
			dg.getRows().add(part);
		}
		dg.setTotal(new Long(length));
		String jsonStr = JSON.toJSON(dg).toString();
		return jsonStr;
	}

	/***
	 * 查找总成信息
	 * 
	 * @param procardTem
	 * @param mentioningAwardPrice
	 * @return
	 */
	@Override
	public String packageProduct(ProcardTemplateJY pp,
			Double mentioningAwardPrice) {
		DataGrid dg = new DataGrid();
		VOProductTree data = new VOProductTree(pp.getId(), pp.getMarkId(), pp
				.getProName(), pp.getDailyoutput().doubleValue(), pp
				.getCarStyle(), pp.getCarStyle(), mentioningAwardPrice, pp
				.getId());
		dg.getRows().add(data);
		dg.setTotal(new Long(1));
		String resultStr = JSON.toJSONString(dg);
		return resultStr;
	}

	/**
	 * @Title: trial
	 * @Description: 试算数据
	 * @param id
	 *            成品ID
	 * @return String 前台数据
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String trial(Integer id, Float dayOutput) {
		Map map = trialMentioningAwardPrice(id, dayOutput);
		String url = null;
		if (map != null && map.size() > 0)
			url = packageData(id, map);
		return url;
	}

	/**
	 * @Title: trialMentioningAwardPrice
	 * @Description: 试算
	 * @param id
	 * @return Map
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public Map trialMentioningAwardPrice(Integer id, Float dayOutput) {
		Map session = ActionContext.getContext().getSession();
		ProcardTemplateJY procardt = findProcardTemById(id); // 查询成品,根据ID
		// 处理前天录入的日产量
		if (dayOutput != null) {
			procardt.setDailyoutput(dayOutput);
		}
		List<ProcardTemplateJY> procardSet = findProcardTemByRootId(id); // 根据成品ID查询组件
		/**
		 * 遍历所有组件
		 */
		Double allProcessWages = 0.0; // 此组件工序工资
		Double allOPSynthesizeStrength = 0.0; // 操作过程综合强度
		Double allPRSynthesizeStrength = 0.0; // 准备过程综合强度

		Double allOPSynthesizeCoefficient = 0.0; // 操作过程综合系数(add)
		Double allPRSynthesizeCoefficient = 0.0; // 操作过程综合系数(add)

		/**
		 * 操作过程
		 */
		Double OPSkillIndex = 0.0; // 操作技能指数
		Double OPNotReplaceCoefficient = 0.0; // 不可替换系数
		Double OPLoadIndex = 0.0; // 负荷指数
		Double OPLabourBeat = 0.0; // 人工节拍
		/**
		 * 准备过程
		 */
		Double PRSkillIndex = 0.0;// 技能指数
		Double PRNotReplaceCoefficient = 0.0; // 不可替换系数
		Double PRLoadIndex = 0.0; // 负荷指数
		Double PRLabourBeat = 0.0; // 人工节拍
		Map map = new HashMap(); // 存储数据
		try {
			/**
			 * 遍历算 所有工序工资 求出技能指数 、可替换人数、负荷指数、人工节拍各总和
			 */
			for (ProcardTemplateJY procardTemplateJY : procardSet) {
				Set<ProcessTemplateJY> processset = procardTemplateJY
						.getProcessTemplateJY(); // 此组件需要的工序
				/**
				 * 遍历一个组件所需要的工序
				 */
				for (ProcessTemplateJY processT : processset) {
					DTOProcess dto = (DTOProcess) session.get(processT.getId()); // 从Session中取出存储的前台填写数据
					String jobNumStr = null;
					if (dto != null) {
						jobNumStr = dto.getJobNum(); // 获取完成此工序的人员工号
						if (jobNumStr == null || jobNumStr.length() <= 0) {
							// 保存页面添加人员信息
							processT.setOperatorDept("");
							processT.setOperatorCode("");
							processT.setOperatorCardId("");
							processT.setOperatorName("");
							// 保存参数信息
							processT.setOpcaozuojiepai(Float.valueOf(dto
									.getOPLabourBeat().toString()));
							processT.setOpshebeijiepai(Float.valueOf(dto
									.getOPEquipmentBeat().toString()));
							processT.setGzzhunbeijiepai(Float.valueOf(dto
									.getPRLabourBeat().toString()));
							processT.setGzzhunbeicishu(Float.valueOf(dto
									.getPRPrepareTIme().toString()));
							// 清空记录
							processT.setProcessMomey(0D);

						}
					} else {
						jobNumStr = processT.getOperatorCode(); // 获取完成此工序的人员工号
					}
					if (jobNumStr == null || jobNumStr.equals("")) {
						continue;
					}
					String[] allJobNum = jobNumStr.split(";");
					Double workingHoursWages = 0.0; // 工序工时工资
					String dept = "";
					String cardId = "";
					String name = "";
					for (String jobNum : allJobNum) { // 统计工序中基本工时工资
						WageStandard wageStandard = wss.findWSByUser(jobNum); // 根据工号查询工资模板
						if (wageStandard == null) {
							continue;
						}
						if (dto != null) {
							dept += ";" + wageStandard.getDept();
							cardId += ";" + wageStandard.getCardId();
							name += ";" + wageStandard.getUserName();
							// 保存页面添加人员信息
							processT.setOperatorDept(dept);
							processT.setOperatorCode(jobNumStr);
							processT.setOperatorCardId(cardId);
							processT.setOperatorName(name);
							// 保存参数信息
							processT.setOpcaozuojiepai(Float.valueOf(dto
									.getOPLabourBeat().toString()));
							processT.setOpshebeijiepai(Float.valueOf(dto
									.getOPEquipmentBeat().toString()));
							processT.setGzzhunbeijiepai(Float.valueOf(dto
									.getPRLabourBeat().toString()));
							processT.setGzzhunbeicishu(Float.valueOf(dto
									.getPRPrepareTIme().toString()));
						}

						InsuranceGold insuranceGold = igs
								.findInsuranceGoldBylc(wageStandard
										.getLocalOrField(), wageStandard
										.getCityOrCountryside(), wageStandard
										.getPersonClass()); // 福利系数
						workingHoursWages += workingHoursWages
								+ (wageStandard.getGangweigongzi()
										+ wageStandard.getSsBase()
										* (insuranceGold.getQYoldageInsurance()
												+ insuranceGold
														.getQYmedicalInsurance()
												+ insuranceGold
														.getQYunemploymentInsurance()
												+ insuranceGold
														.getQYinjuryInsurance() + insuranceGold
												.getQYmaternityInsurance())
										/ 100 + wageStandard.getGjjBase()
										* insuranceGold.getQYhousingFund()
										/ 100);
					}

					Double basicWorkingHoursWages = workingHoursWages / SECONDS; // 工序中基本工时工资
					Double processWages = 0.0;
					if (dto != null) {
						processWages = basicWorkingHoursWages
								* ((dto.getOPLabourBeat() + dto
										.getOPEquipmentBeat())
										* procardt.getDailyoutput() + (dto
										.getPRLabourBeat() * dto
										.getPRPrepareTIme())); // 基本工时工资(单个工序工资)
					} else {
						processWages = basicWorkingHoursWages
								* ((processT.getOpcaozuojiepai() + processT
										.getOpshebeijiepai())
										* procardt.getDailyoutput() + (processT
										.getGzzhunbeijiepai() * processT
										.getGzzhunbeicishu())); // 基本工时工资(单个工序工资)
					}
					allProcessWages = allProcessWages + processWages; // 所有工序工资

					/**
					 * 操作过程统计
					 */
					if (processT.getOptechnologyRate() != null)
						OPSkillIndex += processT.getOptechnologyRate(); // 统计技能指数
					if (processT.getOpnoReplaceRate() != null)
						OPNotReplaceCoefficient += processT
								.getOpnoReplaceRate(); // 统计不可替换系数
					if (processT.getOpfuheRate() != null)
						OPLoadIndex += processT.getOpfuheRate(); // 统计负荷指数
					if (dto != null) {
						if (dto.getOPLabourBeat() != null)
							OPLabourBeat += dto.getOPLabourBeat(); // 统计人工节拍
						// 前台替换
					} else {
						if (processT.getOpcaozuojiepai() != null)
							OPLabourBeat += processT.getOpcaozuojiepai(); // 统计人工节拍
					}
					/**
					 * 准备过程统计
					 */
					if (processT.getGztechnologyRate() != null)
						PRSkillIndex += processT.getGztechnologyRate(); // 统计技能指数
					if (processT.getGznoReplaceRate() != null)
						PRNotReplaceCoefficient += processT
								.getGznoReplaceRate(); // 统计不可替换系数
					if (processT.getGzfuheRate() != null)
						PRLoadIndex += processT.getGzfuheRate(); // 统计负荷指数
					if (dto != null) {
						if (dto.getOPLabourBeat() != null)
							PRLabourBeat += dto.getPRLabourBeat(); // 统计人工节拍
					} else {
						if (processT.getGzzhunbeijiepai() != null)
							PRLabourBeat += processT.getGzzhunbeijiepai(); // 统计人工节拍
					}
				}
			}
			/**
			 * 遍历算出综合强度
			 */
			for (ProcardTemplateJY procardTemplateJY : procardSet) {
				Set<ProcessTemplateJY> processset = procardTemplateJY
						.getProcessTemplateJY(); // 此组件需要的工序
				/**
				 * 遍历一个组件所需要的工序
				 */
				try {
					for (ProcessTemplateJY processT : processset) {
						if (processT.getOpjiaofu() == null)
							continue;
						DTOProcess dto = (DTOProcess) session.get(processT
								.getId());
						Double dtoOPla = 0.0;// 操作人工节拍
						Double dtoPRla = 0.0;// 准备过程人工节拍
						if (dto != null) {
							if (dto.getOPLabourBeat() != null)
								dtoOPla = dto.getOPLabourBeat();
							if (dto.getPRLabourBeat() != null)
								dtoPRla = dto.getPRLabourBeat();
						} else {
							if (processT.getOpcaozuojiepai() != null)
								dtoOPla = processT.getOpcaozuojiepai()
										.doubleValue();
							if (processT.getGzzhunbeijiepai() != null)
								dtoPRla = processT.getGzzhunbeijiepai()
										.doubleValue();
						}
						Double OPcannot = 0.0;// 操作不可替换系数
						Double PRcannot = 0.0;// 准备过程不可替换系数
						if (processT.getOpnoReplaceRate() != null)
							OPcannot = processT.getOpnoReplaceRate()
									.doubleValue();
						if (processT.getGznoReplaceRate() != null)
							PRcannot = processT.getGznoReplaceRate()
									.doubleValue();
						// 操作过程 综合系数
						Double OPSynthesizeCoefficient = ConvertNumber.isNum(
								processT.getOptechnologyRate().doubleValue(),
								OPSkillIndex)
								+ ConvertNumber.isNum(OPcannot,
										OPNotReplaceCoefficient)
								+ ConvertNumber.isNum(processT.getOpfuheRate()
										.doubleValue(), OPLoadIndex)
								+ ConvertNumber.isNum(dtoOPla, OPLabourBeat);
						// 统计操作综合系数
						allOPSynthesizeCoefficient += OPSynthesizeCoefficient;
						// 准备过程 综合系数
						Double PRSynthesizeCoefficient = ConvertNumber.isNum(
								processT.getGztechnologyRate().doubleValue(),
								PRSkillIndex)
								+ ConvertNumber.isNum(PRcannot,
										PRNotReplaceCoefficient)
								+ ConvertNumber.isNum(processT.getGzfuheRate()
										.doubleValue(), PRLoadIndex)
								+ ConvertNumber.isNum(dtoPRla, PRLabourBeat);
						// 统计准备综合系数
						allPRSynthesizeCoefficient += PRSynthesizeCoefficient;
						// 操作过程 综合强度 = 综合系数 * 交付数量
						Double OPsynthesizeStrength = OPSynthesizeCoefficient
								* processT.getOpjiaofu();
						allOPSynthesizeStrength += OPsynthesizeStrength; // 统计单个零件操作过程所有工序强度
						// 准备过程 综合强度 = 综合系数 * 准备次数
						Double PRSynthesizeStrength = 0.0;
						if (dto != null) {
							PRSynthesizeStrength = PRSynthesizeCoefficient
									* dto.getPRPrepareTIme();
						} else {
							PRSynthesizeStrength = PRSynthesizeCoefficient
									* processT.getGzzhunbeicishu();
						}
						allPRSynthesizeStrength += PRSynthesizeStrength; // 统计单个零件准备过程所有工序强度
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Double mentioningAwardPrice = (procardt.getDailyoutput()
					* procardt.getLaborcost() * procardt.getFenpeiRate() - allProcessWages)
					/ procardt.getDailyoutput(); // ((日产量 * 报价费用 * 可调系数) -
			/*********** 单件价格 ************/
			procardt.setOnePrice(mentioningAwardPrice);

			// 所有工序周期工资) / 日产量=提奖价
			Double distributeBonus = mentioningAwardPrice
					* procardt.getDailyoutput(); // 可分配奖金(元/月) = 累计入库量件/月 *入库量

			// 单件计价奖金
			session.put("mentioningAwardPrice", mentioningAwardPrice);

			/**
			 * 操作过程
			 */
			Double OPdistributeProportion = allOPSynthesizeStrength
					/ (allOPSynthesizeStrength + allPRSynthesizeStrength); // 分配比例
			// =
			// sum(综合强度)
			// /
			// (sum(操作综合强度)
			// +
			// sum(准备综合强度))
			Double OPdistributeTotal = distributeBonus * OPdistributeProportion; // 分配总额
			// =
			// 可分配奖金(元/月)
			// *
			// 分配比例
			/**
			 * 准备过程
			 */
			Double PRdistributeProportion = allPRSynthesizeStrength
					/ (allOPSynthesizeStrength + allPRSynthesizeStrength); // 分配比例
			// =
			// sum(综合强度)
			// /
			// (sum(操作综合强度)
			// +
			// sum(准备综合强度))
			Double PRdistributeTotal = distributeBonus * PRdistributeProportion; // 分配总额
			// =
			// 可分配奖金(元/月)
			// *
			// 分配比例

			/**
			 * 遍历算出综合强度
			 */
			for (ProcardTemplateJY procardTemplateJY : procardSet) {
				Set<ProcessTemplateJY> processset = procardTemplateJY
						.getProcessTemplateJY(); // 此组件需要的工序
				/**
				 * 遍历一个组件所需要的工序
				 */
				for (ProcessTemplateJY processT : processset) {
					DTOProcess newDto = null;
					// 工序提交量==总成提交量
					processT.setOpjiaofu(procardt.getDailyoutput());

					if (processT.getOpjiaofu() == null
							|| processT.getOpjiaofu().equals("")) {
						Double newOPLabourBeat = 0.0;
						Double newOPEquipmentBeat = 0.0;
						Double newPRLabourBeat = 0.0;
						Double newPRPrepareTIme = 0.0;
						if (processT.getOpcaozuojiepai() != null)
							newOPLabourBeat = processT.getOpcaozuojiepai()
									.doubleValue();
						if (processT.getOpshebeijiepai() != null)
							newOPEquipmentBeat = processT.getOpshebeijiepai()
									.doubleValue();
						if (processT.getGzzhunbeijiepai() != null)
							newPRLabourBeat = processT.getGzzhunbeijiepai()
									.doubleValue();
						if (processT.getGzzhunbeicishu() != null)
							newPRPrepareTIme = processT.getGzzhunbeicishu()
									.doubleValue();
						newDto = new DTOProcess(processT.getId(), null,
								newOPLabourBeat, newOPEquipmentBeat,
								newPRLabourBeat, newPRPrepareTIme, null, 0.0,
								0.0);
						map.put(processT.getId(), newDto);
						continue;
					}
					DTOProcess dto = (DTOProcess) session.get(processT.getId());// 从前台获取保存的工序对象数据
					Double dtoOPla = 0.0;
					Double dtoPRla = 0.0;
					if (dto != null) {
						if (dto.getOPLabourBeat() != null)
							dtoOPla = dto.getOPLabourBeat();
						if (dto.getPRLabourBeat() != null)
							dtoPRla = dto.getPRLabourBeat();
					} else {
						if (processT.getOpcaozuojiepai() != null)
							dtoOPla = processT.getOpcaozuojiepai()
									.doubleValue();
						if (processT.getGzzhunbeijiepai() != null)
							dtoPRla = processT.getGzzhunbeijiepai()
									.doubleValue();
					}
					Double OPcannot = 0.0; // 不可替换系数
					Double PRcannot = 0.0;
					if (processT.getOpnoReplaceRate() != null)
						OPcannot = processT.getOpnoReplaceRate().doubleValue();
					if (processT.getGznoReplaceRate() != null)
						PRcannot = processT.getGznoReplaceRate().doubleValue();
					// 操作过程 综合系数
					Double OPSynthesizeCoefficient = ConvertNumber.isNum(
							processT.getOptechnologyRate().doubleValue(),
							OPSkillIndex)
							+ ConvertNumber.isNum(OPcannot,
									OPNotReplaceCoefficient)
							+ ConvertNumber.isNum(processT.getOpfuheRate()
									.doubleValue(), OPLoadIndex)
							+ ConvertNumber.isNum(dtoOPla, OPLabourBeat);
					// 准备过程 综合系数
					Double PRSynthesizeCoefficient = ConvertNumber.isNum(
							processT.getGztechnologyRate().doubleValue(),
							PRSkillIndex)
							+ ConvertNumber.isNum(PRcannot,
									PRNotReplaceCoefficient)
							+ ConvertNumber.isNum(processT.getGzfuheRate()
									.doubleValue(), PRLoadIndex)
							+ ConvertNumber.isNum(dtoPRla, PRLabourBeat);
					// Double OPMoney = OPdistributeTotal *
					// OPSynthesizeCoefficient
					// / OPdistributeProportion; //操作过程 工序该分配金额
					// Double PRMoney = PRdistributeTotal *
					// PRSynthesizeCoefficient
					// / PRdistributeProportion; //准备过程 工序该分配金额
					Double OPMoney = OPdistributeTotal
							* OPSynthesizeCoefficient
							/ allOPSynthesizeCoefficient; // 操作过程 工序该分配金额 =
					// 操作分配总额 *
					// 综合系数 / sum(综合系数)
					Double PRMoney = PRdistributeTotal
							* PRSynthesizeCoefficient
							/ allPRSynthesizeCoefficient; // 准备过程 工序该分配金额 =
					// 准备分配总额 *
					// 综合指数 / sum(综合指数)
					Double sumMoney = OPMoney + PRMoney; // 单工序分配总额
					Double unitPrice = sumMoney / processT.getOpjiaofu(); // 每个工序分配金额

					processT.setProcessMomey(unitPrice);

					// =
					// 分配总额
					// /
					// 交付数量
					if (dto != null) {
						newDto = new DTOProcess(processT.getId(), dto
								.getJobNum(), dto.getOPLabourBeat(), dto
								.getOPEquipmentBeat(), dto.getPRLabourBeat(),
								dto.getPRPrepareTIme(), dto.getHandlers(),
								sumMoney, unitPrice);
					} else {
						newDto = new DTOProcess(processT.getId(), processT
								.getOperatorCode(), processT
								.getOpcaozuojiepai().doubleValue(), processT
								.getOpshebeijiepai().doubleValue(), processT
								.getGzzhunbeijiepai().doubleValue(), processT
								.getGzzhunbeicishu().doubleValue(), processT
								.getOperatorName(), sumMoney, unitPrice);
					}
					map.put(processT.getId(), newDto);
					if (dto != null)
						session.remove(processT.getId());
				}
			}
			totalDao.update(procardt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public WageStandardServer getWss() {
		return wss;
	}

	public void setWss(WageStandardServer wss) {
		this.wss = wss;
	}

	public InsuranceGoldServer getIgs() {
		return igs;
	}

	public void setIgs(InsuranceGoldServer igs) {
		this.igs = igs;
	}

	@Override
	public List<ProcardTemplateJY> getAllNames(String markId) {
		// TODO Auto-generated method stub
		if (markId != null) {
			List<ProcardTemplateJY> plist1 = totalDao
					.query("from ProcardTemplateJY where markId like '%"
							+ markId + "%'");
			if (plist1.size() > 0) {

				List<ProcardTemplateJY> plist2 = new ArrayList<ProcardTemplateJY>();
				List<String> markIdList = new ArrayList<String>();
				int count = 0;
				for (ProcardTemplateJY p1 : plist1) {
					if (p1.getMarkId() != null
							&& !markIdList.contains(p1.getMarkId())) {
						markIdList.add(p1.getMarkId());
						plist2.add(p1);
						count++;
						if (count == 10) {// 只取前十条
							break;
						}
					} else {
						int count2 = 0;
						for (ProcardTemplateJY p2 : plist2) {
							if (p1.getMarkId() != null
									&& p2.getMarkId().equals(p1.getMarkId())
									&& p2.getProcardStyle() != null
									&& p1.getProcardStyle() != null
									&& p2.getProcardStyle().endsWith(
											p1.getProcardStyle())) {
								count2++;
							}
						}
						if (count2 == 0) {// 没有同件号的同时也同生产类型的情况
							plist2.add(p1);
							count++;
							if (count == 10) {// 只取前十条
								break;
							}
						}
					}
				}
				return plist2;
			}
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public String jingyiJisuan(Integer id, ProcardTemplateJY procardTem) {
		if (id != null) {
			Float singleDuration = 0F;
			Float needCapacity = 0F;
			// 总成
			ProcardTemplateJY rootProcard = (ProcardTemplateJY) totalDao
					.getObjectById(ProcardTemplateJY.class, id);
			if (procardTem != null && procardTem.getSingleDuration() != null) {
				singleDuration = procardTem.getSingleDuration();// 上班时长
				needCapacity = procardTem.getNeedCapacity();// 客户需求
				rootProcard.setSingleDuration(singleDuration);
				rootProcard.setNeedCapacity(needCapacity);
			} else {
				singleDuration = rootProcard.getSingleDuration();// 上班时长
				needCapacity = rootProcard.getNeedCapacity();// 客户需求
			}

			if (rootProcard != null) {
				// 得到最大的层次
				String maxhql = "select max(belongLayer) from ProcardTemplateJY where rootId=?";
				Integer belongLayer = (Integer) totalDao.getObjectByCondition(
						maxhql, rootProcard.getId());
				// 根据层次计算
				for (int i = belongLayer; i > 0; i--) {
					// 开始计算产品的单班产量
					String hql_pro = "from ProcardTemplateJY where rootId=? and belongLayer=?";
					List<ProcardTemplateJY> proList = totalDao.query(hql_pro,
							rootProcard.getId(), i);
					/** 处理外购件的产能、时长、供应商信息 **/
					Float maxTime = 0F;
					for (ProcardTemplateJY procardTemplateJY : proList) {
						procardTemplateJY.setSingleDuration(singleDuration);
						procardTemplateJY.setNeedCapacity(needCapacity);
						if ("外购".equals(procardTemplateJY.getProcardStyle())) {
							// 查询供应量最大的供应商产品信息
							String hql_gys = "from GysMarkIdjiepai where markId=? and procardStyle='总成' order by capacity desc";
							GysMarkIdjiepai gysJiepai = (GysMarkIdjiepai) totalDao
									.getObjectByCondition(hql_gys,
											procardTemplateJY.getMarkId());
							if (gysJiepai != null) {
								if (gysJiepai.getCapacity() == null
										|| gysJiepai.getDeliveryDuration() == null) {
									return "外购件:"
											+ procardTemplateJY.getMarkId()
											+ "尚未填报上产节拍!";
								}
								procardTemplateJY.setSingleDuration(gysJiepai
										.getSingleDuration());
								procardTemplateJY.setCapacity(gysJiepai
										.getCapacity()
										/ procardTemplateJY.getQuanzi1()
										* procardTemplateJY.getQuanzi2());
								procardTemplateJY.setCapacitySurplus(gysJiepai
										.getCapacitySurplus());
								procardTemplateJY.setCapacityRatio(gysJiepai
										.getCapacityRatio());
								procardTemplateJY.setDeliveryDuration(gysJiepai
										.getDeliveryDuration());
								procardTemplateJY.setDeliveryRatio(gysJiepai
										.getDeliveryRatio());
								procardTemplateJY.setDeliveryPeriod(gysJiepai
										.getDeliveryPeriod());
								procardTemplateJY.setDeliveryAmount(gysJiepai
										.getDeliveryAmount());
								procardTemplateJY
										.setProSingleDuration(gysJiepai
												.getDeliveryDuration());
								procardTemplateJY.setAllJiepai(gysJiepai
										.getAllJiepai());
								procardTemplateJY
										.setCapacitySurplus(procardTemplateJY
												.getCapacity()
												- needCapacity);
								procardTemplateJY.setZhuserId(gysJiepai
										.getZhuserId());
								procardTemplateJY.setGys(gysJiepai.getGys());

								totalDao.update(procardTemplateJY);
								// 得到最大外购件延误时间
								maxTime = maxTime > procardTemplateJY
										.getDeliveryDuration() ? maxTime
										: procardTemplateJY
												.getDeliveryDuration();
							}
						}
					}
					/** 处理外购件的产能、时长、供应商信息结束 **/

					/** 处理自制、外委、组合、总成 **/
					for (ProcardTemplateJY procardTemplateJY : proList) {
						if ("外购".equals(procardTemplateJY.getProcardStyle())) {
							continue;
						}
						procardTemplateJY.setSingleDuration(singleDuration);
						procardTemplateJY.setNeedCapacity(needCapacity);

						// 获得下层最大总节拍
						String hql_minJiepai = "select max(allJiepai) from ProcardTemplateJY where fatherId=? and procardStyle <> '外购'";
						Object obj = totalDao.getObjectByCondition(
								hql_minJiepai, procardTemplateJY.getId());
						Float sumjiepai = 0F;
						if (obj != null) {
							sumjiepai = Float.parseFloat(obj.toString());
						}

						// 获得下层总延误时长
						String hql_deliveryDuration = "select sum(deliveryDuration) from ProcardTemplateJY where fatherId=?  and procardStyle <> '外购'";
						Object obj_deliveryDuration = totalDao
								.getObjectByCondition(hql_deliveryDuration,
										procardTemplateJY.getId());
						Float sumdeliveryDuration = 0F;
						if (obj_deliveryDuration != null) {
							sumdeliveryDuration = Float
									.parseFloat(obj_deliveryDuration.toString());
						}

						List<ProcessTemplateJY> ptList = new ArrayList<ProcessTemplateJY>();
						ptList.addAll(procardTemplateJY.getProcessTemplateJY());
						Float allJiepai = sumjiepai;
						Float maxJiepai = 0F;
						Float allWaiWeiDate = sumdeliveryDuration;// 生产总时长
						Float maxWaiWeiDate = 0F;// 外委最大时长
						for (int j = 0; j < ptList.size(); j++) {
							ProcessTemplateJY pt = ptList.get(j);
							if ("外委".equals(pt.getProductStyle())) {
								// 查询外委供应商填报信息
								String hql_ww = "from ProcessMarkIdZijian where gysMarkIdjiepai.markId=? and processNO=? and processName=? order by capacity desc";
								ProcessMarkIdZijian pkid = (ProcessMarkIdZijian) totalDao
										.getObjectByCondition(hql_ww,
												procardTemplateJY.getMarkId(),
												pt.getProcessNO(), pt
														.getProcessName());
								if (pkid != null) {
									pt.setOpcaozuojiepai(pkid
											.getOpcaozuojiepai());
									pt.setOpshebeijiepai(pkid
											.getOpshebeijiepai());
									pt.setGzzhunbeijiepai(pkid
											.getGzzhunbeijiepai());
									pt.setGzzhunbeicishu(pkid
											.getGzzhunbeicishu());
									pt.setGys(pkid.getGys());
									pt.setDeliveryDuration(pkid
											.getDeliveryDuration());
									pt.setZhuserId(pkid.getZhuserId());
								} else {
									return procardTemplateJY.getMarkId()
											+ " 的外委工序: " + pt.getProcessName()
											+ " 尚未填报生产节拍!";
								}
							}
							Float caozuojiepai = pt.getOpcaozuojiepai() == null ? 0F
									: pt.getOpcaozuojiepai();
							Float shebeijiepai = pt.getOpshebeijiepai() == null ? 0F
									: pt.getOpshebeijiepai();
							Float zhunbeijiepai = pt.getGzzhunbeijiepai() == null ? 0F
									: pt.getGzzhunbeijiepai();
							Float zhunbeicishu = pt.getGzzhunbeicishu() == null ? 0F
									: pt.getGzzhunbeicishu();
							Float nowAllJiepai = caozuojiepai + shebeijiepai
									+ zhunbeijiepai * zhunbeicishu;// 总节拍
							Float rgAllJiepai = caozuojiepai + zhunbeijiepai
									* zhunbeicishu;// 人工节拍
							pt.setAllJiepai(Float.parseFloat(String.format(
									"%.2f", nowAllJiepai)));// 更新总节拍
							// 计算工序的产能、配送比
							// 产能
							if (nowAllJiepai > 0) {
								pt.setCapacity(Float.parseFloat(String.format(
										"%.0f", singleDuration * 3600
												/ (nowAllJiepai + sumjiepai))));
							} else {
								pt.setCapacity(0F);
							}
							// 产能盈余
							pt.setCapacitySurplus(pt.getCapacity()
									- needCapacity);
							allJiepai += nowAllJiepai;// 节拍
//							if (j > 0) {
//								ProcessTemplate topPt = ptList.get(j - 1);// 获得上一道工序
								if ("自制".equals(pt.getProductStyle())) {
									if ("yes".equals(pt.getProcessStatus())) {
										// 连续的并行工序
//										if ("yes".equals(topPt
//												.getProcessStatus())) {
//											allJiepai -= nowAllJiepai;
//											allJiepai += rgAllJiepai;
//											maxJiepai = topPt
//													.getOpshebeijiepai();
//											// 选择设备节拍更大的工序
//											if (maxJiepai < pt
//													.getOpshebeijiepai()) {
//												allJiepai -= maxJiepai;
//												maxJiepai = pt
//														.getOpshebeijiepai();
//												allJiepai += pt
//														.getOpshebeijiepai();
//											}
											allJiepai-=nowAllJiepai;
											Float maxbin=nowAllJiepai;//并行工序最大总节拍
											Float sumbin=nowAllJiepai;//并行工序总结拍累加和
											int n=1;//并行工序连续数量
											while(j+n<ptList.size()){
												ProcessTemplateJY pt2 = ptList.get(j+n);
												if(!"yes".equals(pt2.getProcessStatus())||!"自制".equals(pt2.getProductStyle())){
													break;
												}
												Float caozuojiepai2 = pt2.getOpcaozuojiepai() == null ? 0F
														: pt2.getOpcaozuojiepai();
												Float shebeijiepai2 = pt2.getOpshebeijiepai() == null ? 0F
														: pt2.getOpshebeijiepai();
												Float zhunbeijiepai2 = pt2.getGzzhunbeijiepai() == null ? 0F
														: pt2.getGzzhunbeijiepai();
												Float zhunbeicishu2 = pt2.getGzzhunbeicishu() == null ? 0F
														: pt2.getGzzhunbeicishu();
												Float nowAllJiepai2 = caozuojiepai2 + shebeijiepai2
														+ zhunbeijiepai2 * zhunbeicishu2;// 总节拍
												Float rgAllJiepai2 = caozuojiepai2 + zhunbeijiepai2
														* zhunbeicishu2;// 人工节拍
												pt2.setAllJiepai(Float.parseFloat(String.format(
														"%.2f", nowAllJiepai2)));// 更新总节拍
												// 计算工序的产能、配送比
												// 产能
												if (nowAllJiepai2 > 0) {
													pt2.setCapacity(Float.parseFloat(String.format(
															"%.0f", singleDuration * 3600
																	/ (nowAllJiepai2 + sumjiepai))));
												} else {
													pt2.setCapacity(0F);
												}
												// 产能盈余
												pt2.setCapacitySurplus(pt2.getCapacity()
														- needCapacity);
												if(nowAllJiepai2>maxbin){
													maxbin=nowAllJiepai2;
												}
												sumbin+=nowAllJiepai2;
												totalDao.update(pt2);
												n++;
											}
											Float binjiepai=(singleDuration*3600*maxbin)/(singleDuration*3600-sumbin+maxbin);//几道并行工序的总时长
											allJiepai+=binjiepai;
											j=j+n-1;//工序下标跳n次因为本身会加1所以这里减去一
											
//										} else {
//											maxJiepai = pt.getAllJiepai();
//										}
										// else 上一道工序为不并行 （已累加 ）
									} else {
										maxJiepai = pt.getAllJiepai();
									}
									// else 不并行工序 （已累加 ）
								} else {

									// 不累加外委工序节拍
									allJiepai -= nowAllJiepai;
									allWaiWeiDate += pt.getDeliveryDuration() == null ? 0
											: pt.getDeliveryDuration();// 送货时长
									if ("yes".equals(pt.getProcessStatus())) {
										// 连续的并行工序
//										if ("外委"
//												.equals(topPt.getProductStyle())
//												&& "yes".equals(topPt
//														.getProcessStatus())) {
//											// 选择节拍更大的工序
//											if (maxJiepai < pt.getAllJiepai()) {
//												// allJiepai = allJiepai
//												// - maxJiepai;
//												// maxJiepai =
//												// pt.getAllJiepai();
//												allWaiWeiDate = allWaiWeiDate
//														- maxWaiWeiDate;
//												maxWaiWeiDate = pt
//														.getDeliveryDuration();
//											} else {
//												// allJiepai = allJiepai
//												// - pt.getAllJiepai();
//												allWaiWeiDate = allWaiWeiDate
//														- pt
//																.getDeliveryPeriod();
//
//											}
//										} else {
//											maxJiepai = pt
//													.getDeliveryDuration();
//											maxWaiWeiDate = pt
//													.getDeliveryDuration();
//										}
										allJiepai-=nowAllJiepai;
										Float maxbin=nowAllJiepai;//并行工序最大总节拍
										Float sumbin=nowAllJiepai;//并行工序总结拍累加和
										int n=1;//并行工序连续数量
										while(j+n<ptList.size()){
											ProcessTemplateJY pt2 = ptList.get(j+n);
											if(!"yes".equals(pt2.getProcessStatus())||!"外委".equals(pt2.getProductStyle())){
												break;
											}
												// 查询外委供应商填报信息
												String hql_ww = "from ProcessMarkIdZijian where gysMarkIdjiepai.markId=? and processNO=? and processName=? order by capacity desc";
												ProcessMarkIdZijian pkid = (ProcessMarkIdZijian) totalDao
														.getObjectByCondition(hql_ww,
																procardTemplateJY.getMarkId(), pt2
																		.getProcessNO(), pt2
																		.getProcessName());
												if (pkid != null) {
													pt2.setOpcaozuojiepai(pkid
															.getOpcaozuojiepai());
													pt2.setOpshebeijiepai(pkid
															.getOpshebeijiepai());
													pt2.setGzzhunbeijiepai(pkid
															.getGzzhunbeijiepai());
													pt2.setGzzhunbeicishu(pkid
															.getGzzhunbeicishu());
													pt2.setGys(pkid.getGys());
													pt2.setDeliveryDuration(pkid
															.getDeliveryDuration());
													pt2.setZhuserId(pkid.getZhuserId());
												} else {
													return procardTemplateJY.getMarkId()
															+ " 的外委工序: " + pt2.getProcessName()
															+ " 尚未填报生产节拍!";
												}
											Float caozuojiepai2 = pt2.getOpcaozuojiepai() == null ? 0F
													: pt.getOpcaozuojiepai();
											Float shebeijiepai2 = pt2.getOpshebeijiepai() == null ? 0F
													: pt.getOpshebeijiepai();
											Float zhunbeijiepai2 = pt2.getGzzhunbeijiepai() == null ? 0F
													: pt.getGzzhunbeijiepai();
											Float zhunbeicishu2 = pt2.getGzzhunbeicishu() == null ? 0F
													: pt.getGzzhunbeicishu();
											Float nowAllJiepai2 = caozuojiepai2 + shebeijiepai2
													+ zhunbeijiepai2 * zhunbeicishu2;// 总节拍
											Float rgAllJiepai2 = caozuojiepai2 + zhunbeijiepai2
													* zhunbeicishu2;// 人工节拍
											pt2.setAllJiepai(Float.parseFloat(String.format(
													"%.2f", nowAllJiepai2)));// 更新总节拍
											// 计算工序的产能、配送比
											// 产能
											if (nowAllJiepai2 > 0) {
												pt2.setCapacity(Float.parseFloat(String.format(
														"%.0f", singleDuration * 3600
																/ (nowAllJiepai2 + sumjiepai))));
											} else {
												pt2.setCapacity(0F);
											}
											// 产能盈余
											pt2.setCapacitySurplus(pt2.getCapacity()
													- needCapacity);
											if(nowAllJiepai2>maxbin){
												maxbin=nowAllJiepai2;
											}
											sumbin+=nowAllJiepai2;
											totalDao.update(pt2);
											n++;
										}
										Float binjiepai=(singleDuration*3600*maxbin)/(singleDuration*3600-sumbin+maxbin);//几道并行工序的总时长
										allJiepai+=binjiepai;
										j=j+n-1;//工序下标跳n次因为本身会加1所以这里减去一
										// else 上一道工序为不并行 （已累加 ）
									} else {
										maxJiepai = pt.getDeliveryDuration();
										maxWaiWeiDate = pt
												.getDeliveryDuration();
									}
									// else 不并行工序 （已累加 ）

								}
//							} else {
//								maxJiepai = pt.getDeliveryDuration();
//								maxWaiWeiDate = pt.getDeliveryDuration();
//							}
							totalDao.update(pt);
						}
						// 总节拍累加权值
						procardTemplateJY.setAllJiepai(allJiepai
								* (procardTemplateJY.getCorrCount() == null ? 1
										: procardTemplateJY.getCorrCount()));

						// allWaiWeiDate += allJiepai / 3600;//累加外委工序生产时长
						procardTemplateJY.setDeliveryDuration(allWaiWeiDate);
						if (allJiepai > 0) {
							float capa = singleDuration * 3600 / allJiepai;
							int a = (int) Math.ceil(capa);
							procardTemplateJY.setCapacity((float) a);
						} else {
							procardTemplateJY.setCapacity(0F);
						}
						totalDao.update(procardTemplateJY);

						// 如果是总成，计算单批最大数量
						if ("总成".equals(procardTemplateJY.getProcardStyle())) {
							/** min算法 **/
							// 自制件最小产能
							Float minZz = procardTemplateJY.getCapacity();

							// 计算外委最小产能
							String hql_minWw = " select min(capacity) from ProcessTemplateJY where productStyle='外委' and capacity is not null and procardTemplateJY.id in (select id from ProcardTemplateJY where rootId=?)";
							Float minWw = (Float) totalDao.query(hql_minWw,
									procardTemplateJY.getId()).get(0);
							if (minWw == null) {
								minWw = minZz;
							}

							// 计算外购最小产能
							String hql_minWg = " select min(capacity) from ProcardTemplateJY where rootId=? and procardStyle='外购'";
							Float minWg = (Float) totalDao.query(hql_minWg,
									procardTemplateJY.getId()).get(0);
							if (minWg == null) {
								minWg = minZz;
							}

							// 通过比对得到最小批次数量
							float pici = minZz > minWw ? minWw : minZz;
							pici = pici > minWg ? minWg : pici;

							int minPici = (int) Math.ceil(pici);

							procardTemplateJY.setMaxCount((float) minPici);

							/*** 计算最大批次数量 ***/
							// 如果自制产能最低
							if (minPici == minZz) {
								/*** 根据延误时长得到单批次最大数量 **/
								Float maxHour = procardTemplateJY
										.getDeliveryDuration();
								// 比较外委时间和外购时间，取最大时间
								if (maxTime > maxHour) {
									maxHour = maxTime;
								}
								if (maxTime > 0) {
									float maxNumber = maxHour * 3600
											/ procardTemplateJY.getAllJiepai();
									// 通过比对得到最小批次数量
									pici = maxNumber > minWw ? minWw
											: maxNumber;
									pici = pici > minWg ? minWg : pici;
									minPici = (int) Math.ceil(pici);
									procardTemplateJY
											.setMaxCount((float) minPici);
								}
							}

							// 更新下层所有件号的最大数量
							String hql_upMax = "update ProcardTemplateJY set maxCount=corrCount*"
									+ minPici
									+ " where rootId=? and procardStyle<>'总成'";
							totalDao.createQueryUpdate(hql_upMax, null,
									procardTemplateJY.getId());
							String hql_upMaxw = "update ProcardTemplateJY set maxCount="
									+ minPici
									+ "/quanzi1*quanzi2 where rootId=? and procardStyle='外购'";
							totalDao.createQueryUpdate(hql_upMaxw, null,
									procardTemplateJY.getId());

							// 时长算法
							// float capa =
							// procardTemplateJY.getDeliveryDuration()
							// * 3600 / allJiepai;
							// int a = (int) Math.ceil(capa);
							// procardTemplateJY.setMaxCount((float) a);
							// // 更新下层所有件号的最大数量
							// String hql_upMax =
							// "update ProcardTemplateJY set maxCount=corrCount*"
							// + a
							// + " where rootId=? and procardStyle<>'总成'";
							// totalDao.createQueryUpdate(hql_upMax, null,
							// procardTemplateJY.getId());
							// String hql_upMaxw =
							// "update ProcardTemplateJY set maxCount="
							// + a
							// +
							// "/quanzi1*quanzi2 where rootId=? and procardStyle='外购'";
							// totalDao.createQueryUpdate(hql_upMaxw, null,
							// procardTemplateJY.getId());
						}
					}
					// /** 计算所有外购件的周期批次 **/
					// // 外购周期(因为外购件是同步生产，所以得到最大的送货周期作为外购的周期批次)
					// String hql_wai_zhou =
					// "select max(deliveryPeriod) from ProcardTemplateJY where procardStyle='外购'";
					// Integer wai_zhou = (Integer) totalDao
					// .getObjectByCondition(hql_wai_zhou);

				}

				// /*** 计算批次周期 ****/
				// // 总成
				// rootProcard = (ProcardTemplateJY) totalDao.getObjectById(
				// ProcardTemplateJY.class, id);
				// Float zongCapacity = rootProcard.getCapacity();

			}
		}
		return "";
	}

	/***
	 *计算每的其他数据(产能比、送货量、生产时间)
	 * 
	 * @param id
	 * @param procardTem
	 * @return
	 */
	@Override
	public String jingyiJisuan2(Integer id, ProcardTemplateJY procardTem) {
		if (id != null) {
			Float singleDuration = 0F;
			Float needCapacity = 0F;
			// 总成
			ProcardTemplateJY rootProcard = (ProcardTemplateJY) totalDao
					.getObjectById(ProcardTemplateJY.class, id);
			if (procardTem != null && procardTem.getSingleDuration() != null) {
				singleDuration = procardTem.getSingleDuration();// 上班时长
				needCapacity = procardTem.getNeedCapacity();// 客户需求
				rootProcard.setSingleDuration(singleDuration);
				rootProcard.setNeedCapacity(needCapacity);
			} else {
				singleDuration = rootProcard.getSingleDuration();// 上班时长
				needCapacity = rootProcard.getNeedCapacity();// 客户需求
			}
			if (rootProcard != null) {
				// 得到最大的层次
				String maxhql = "select max(belongLayer) from ProcardTemplateJY where rootId=?";
				Integer belongLayer = (Integer) totalDao.getObjectByCondition(
						maxhql, rootProcard.getId());
				// 根据层次计算
				for (int i = belongLayer; i > 0; i--) {
					/** 计算本层的其他数据(产能比、送货量、生产时间) **/
					// 得到最小产能
					String hql_mincanneng = "select min(capacity) from ProcessTemplateJY where procardTemplateJY.id in "
							+ "(select id from ProcardTemplateJY where rootId=? and belongLayer=?) and productStyle='自制'";
					Float capacity_min = (Float) totalDao.query(hql_mincanneng,
							rootProcard.getId(), i).get(0);
					if (capacity_min != null && capacity_min > 0) { // 更新产能比、送货量、生产时间
						String sql_cannengbi = "update ProcessTemplateJY set capacityRatio=Convert(decimal(18,2),capacity/"
								+ capacity_min
								+ "),deliveryAmount=deliveryPeriod*capacity,proSingleDuration="
								+ singleDuration
								+ "*capacity/"
								+ capacity_min
								+ " where "
								+ "procardTemplateJY.id in(select id from ProcardTemplateJY where rootId=? and belongLayer=?)";
						totalDao.createQueryUpdate(sql_cannengbi, null,
								rootProcard.getId(), i); // 更新外委生产时间
						String sql_chengchan = "update ProcessTemplateJY set proSingleDuration="
								+ singleDuration
								+ "*capacityRatio where procardTemplateJY.id in"
								+ "(select id from ProcardTemplateJY where rootId=? and belongLayer=?)";
						totalDao.createQueryUpdate(sql_chengchan, null,
								rootProcard.getId(), i);
					}

				}
			}
		}
		return "";
	}


	public boolean updateBelongLayer(ProcardTemplateJY pt, Integer belongLayer) {
		boolean b = true;
		belongLayer++;
		pt.setBelongLayer(belongLayer);
		b = b & totalDao.update(pt);
		Set<ProcardTemplateJY> ptSet = pt.getProcardTJYSet();
		if (b & ptSet != null && ptSet.size() > 0) {
			for (ProcardTemplateJY ptSon : ptSet) {
				b = b & updateBelongLayer(ptSon, belongLayer);
			}
			return b;
		} else {
			return b;
		}
	}


	@Override
	public List<String> getAllMarkId() {
		// TODO Auto-generated method stub
		return totalDao.query("select markId from ProcardTemplateJY");
	}

	@Override
	public int updateFk() {
		// TODO Auto-generated method stub

		return totalDao
				.createQueryUpdate(
						null,
						"update ta_sop_w_ProcardTemplateJY set fk_ProcardTId=fatherId where fk_ProcardTId is null and procardStyle!='总成'",
						null);
	}


	@Override
	public ProcessTemplateJY getProcessJYById(Integer id) {
		// TODO Auto-generated method stub
		ProcessTemplateJY processJY=(ProcessTemplateJY)totalDao.getObjectById(ProcessTemplateJY.class, id);
		if(processJY!=null&&processJY.getProcardTemplateJY()!=null){
			processJY.setProgressStatus(processJY.getProcardTemplateJY().getProgressStatus());
			processJY.setMarkId(processJY.getProcardTemplateJY().getMarkId());
		}
		return processJY;
	}

	@Override
	public boolean updateProcessJY(ProcessTemplateJY processTemplateJY,
			String pageStatus, String updateContext) {
		// TODO Auto-generated method stub
		ProcessTemplateJY p=getProcessJYById(processTemplateJY.getId());
		if(pageStatus.equals("ji")){
			p.setContextJi(updateContext);
			p.setOpshebeijiepai(processTemplateJY.getOpshebeijiepai());
		}else if(pageStatus.equals("gong")){
			p.setContextGong(updateContext);
			p.setGzzhunbeijiepai(processTemplateJY.getGzzhunbeijiepai());
			p.setGzzhunbeicishu(processTemplateJY.getGzzhunbeicishu());
		}else if(pageStatus.equals("liang")){
			p.setContextLiang(updateContext);
			p.setGzzhunbeijiepai(processTemplateJY.getGzzhunbeijiepai());
			p.setGzzhunbeicishu(processTemplateJY.getGzzhunbeicishu());
		}else if(pageStatus.equals("jian")){
			p.setContextJian(updateContext);
		}else if(pageStatus.equals("wen")){
			p.setContextWen(updateContext);
			p.setOpcaozuojiepai(processTemplateJY.getOpcaozuojiepai());
			p.setOpshebeijiepai(processTemplateJY.getOpshebeijiepai());
			p.setGzzhunbeijiepai(processTemplateJY.getGzzhunbeijiepai());
			p.setGzzhunbeicishu(processTemplateJY.getGzzhunbeicishu());
		}else if(pageStatus.equals("ren")){
			p.setContextRen(updateContext);
			p.setOpcaozuojiepai(processTemplateJY.getOpcaozuojiepai());
			p.setGzzhunbeijiepai(processTemplateJY.getGzzhunbeijiepai());
		}
		boolean b= totalDao.update(p);
		
		return b;
	}

	@Override
	public boolean setBomProgressStatusByprocessId(Integer id, String status) {
		// TODO Auto-generated method stub
		totalDao
		.createQueryUpdate(
				null,
				"update ta_sop_w_ProcardTemplateJY set progressStatus='"+status+"' where rootId=(select rootId from ta_sop_w_ProcardTemplateJY where id=(select fk_processTId from ta_sop_w_ProcessTemplateJY where id=?))",
				id);
		return true;
	}

	@Override
	public boolean setBomProgressStatusByRootId(Integer id, String status) {
		// TODO Auto-generated method stub
		totalDao
		.createQueryUpdate(
				null,
				"update ta_sop_w_ProcardTemplateJY set progressStatus='"+status+"' where rootId=?",
				id);
		return true;
	}

	@Override
	public boolean checkisOK(Integer id) {
		// TODO Auto-generated method stub
		List list=totalDao.query("select count(*) from ProcessTemplateJY where capacitySurplus is null or capacitySurplus<0 and procardTemplateJY.id in (select id from ProcardTemplateJY where rootId=?)",id);
	    if(list.size()>0){
	    	Double count =Double.parseDouble(list.get(0).toString());
	    	if(count >0){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }
	    return false;
	}

	@Override
	public List findSBGZLJList(String no, String name,String pageStatus) {
		// TODO Auto-generated method stub
		if(pageStatus!=null){
			List returnlist=new ArrayList();
			List list=new ArrayList(); 
			String hql1=null;
			String hql2=null;
			if(pageStatus.equals("ji")){
				hql1="from Machine where no like ? or name like ?";
				hql2="from Machine where no like ? and name like ?";
			}else if(pageStatus.equals("gong")){
				hql1="from Gzstore where number like ? or matetag like ?";
				hql2="from Gzstore where number like ? and matetag like ?";
			}else if(pageStatus.equals("liang")){
				hql1="from Measuring where measuring_no like ? or matetag like ?";
				hql2="from Measuring where measuring_no like ? and matetag like ?";
			}else{
				return null;
			}
			if(no!=null&&name!=null&&no.equals(name)){
				list=totalDao.query(hql1, "%"+no+"%","%"+name+"%");
			}else{
				list=totalDao.query(hql2, "%"+no+"%","%"+name+"%");
			}
			if(list.size()>15){
				for(int i=0;i<15;i++){
					returnlist.add(list.get(i));
				}
				return returnlist;
			}
			return  list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateProcessSBGZLJ(String no, String name, Integer id,String pageStatus) {
		// TODO Auto-generated method stub
		if(pageStatus!=null){
			if(pageStatus.equals("ji")){
				List<Machine> list=new ArrayList<Machine>(); 
				if(no!=null && name!=null && no.equals(name)){
					list=(List<Machine>)totalDao.query("from Machine where no = ? or name = ?", no,name);
				}else{
					list=(List<Machine>)totalDao.query("from Machine where no = ? and name = ?", no,name);
					
				}
				ProcessTemplateJY process=getProcessJYById(id);
				if(list.size()>0&&process!=null){
					Machine m=list.get(0);
					process.setShebeiId(m.getId());
					process.setShebeiNo(m.getNo());
					process.setShebeiName(m.getName());
					process.setShebeistatus("是");
					return totalDao.update(process);
				}
			}else if(pageStatus.equals("gong")){
				List<Gzstore> list=new ArrayList<Gzstore>(); 
				if(no!=null && name!=null && no.equals(name)){
					list=(List<Gzstore>)totalDao.query("from Gzstore where number = ? or matetag = ?", no,name);
				}else{
					list=(List<Gzstore>)totalDao.query("from Gzstore where number = ? and matetag = ?", no,name);
					
				}
				ProcessTemplateJY process=getProcessJYById(id);
				if(list.size()>0&&process!=null){
					Gzstore g=list.get(0);
					process.setGzstoreId(g.getId());
					process.setNumber(g.getNumber());
					process.setMatetag(g.getMatetag());
					process.setGongzhuangstatus("是");
					return totalDao.update(process);
				}
			}else if(pageStatus.equals("liang")){
				List<Measuring> list=new ArrayList<Measuring>(); 
				if(no!=null && name!=null && no.equals(name)){
					list=(List<Measuring>)totalDao.query("from Measuring where measuring_no = ? or matetag = ?", no,name);
				}else{
					list=(List<Measuring>)totalDao.query("from Measuring where measuring_no = ? and matetag = ?", no,name);
				}
				ProcessTemplateJY process=getProcessJYById(id);
				if(list.size()>0&&process!=null){
					Measuring m=list.get(0);
					process.setMeasuringId(m.getId());
					process.setMeasuringNumber(m.getNumber());
					process.setMeasuringMatetag(m.getMatetag());
					process.setMeasuring_no(m.getMeasuring_no());
					process.setLiangjustatus("是");
					return totalDao.update(process);
				}
			}
		}
		
		return false;
	}

	@Override
	public boolean updateFileName(ProcessTemplateJY processTemplateJY) {
		// TODO Auto-generated method stub
		ProcessTemplateJY p=(ProcessTemplateJY) totalDao.getObjectById(ProcessTemplateJY.class, processTemplateJY.getId());
		if(p!=null){
			p.setFileName(processTemplateJY.getFileName());
			p.setGuifanstatus("是");
			return totalDao.update(p);
		}
		return false;
	}

	@Override
	public List getDept() {
		// TODO Auto-generated method stub
		List list=totalDao.query("from DeptNumber");
		return list;
	}

	@Override
	public List getUsersByDeptId(Integer id,Integer processId) {
		// TODO Auto-generated method stub
		if(id!=null&&id!=0&&id!=-1){
			deptIds=new ArrayList<Integer>();
		getUnderDeptIdById(id);
		deptIds.add(id);
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<deptIds.size();i++){
			if(i==(deptIds.size()-1)){
				sb.append(deptIds.get(i)+")");
			}else{
				sb.append(deptIds.get(i)+",");
			}
		}
		List<String> deptnames=totalDao.query("select dept from DeptNumber where id in("+sb.toString());
		StringBuffer sb2=new StringBuffer();
		for(int i=0;i<deptnames.size();i++){
			if(i==(deptnames.size()-1)){
				sb2.append("'"+deptnames.get(i)+"')");
			}else{
				sb2.append("'"+deptnames.get(i)+"',");
			}
		}
		List list=totalDao.query("from Users where onWork not in ('离职','离职中','内退','退休','病休') and dept in ("+sb2.toString());
		return list;
		}else if(id!=null&&id==-1){
			ProcessTemplateJY process=getProcessJYById(processId);
			String dept=process.getOperatorDept();
			if(dept!=null){
				List<Integer> deptIds=totalDao.query("select id from DeptNumber where dept=?",dept);
				if(deptIds.size()>0){
					return getUsersByDeptId(deptIds.get(0),null);
				}
			}
		}
		return null;
	}
	/**
	 * 通过部门id递归获取该部门下所有的下级部门id
	 * @param deptId
	 */
 public void getUnderDeptIdById(Integer deptId){
	 List list=totalDao.query("select id from DeptNumber where fatherId="+deptId);
	 if(list.size()!=0){
		List<Integer> ids=list;
		 deptIds.addAll(ids);
		 for(Integer id:ids){
			 deptId=id;
			 getUnderDeptIdById(deptId);
		 }
	 }
	 
	 
 }

	@Override
	public boolean updateOperator(Integer id, Integer processId) {
		// TODO Auto-generated method stub
		Users user=(Users) totalDao.getObjectById(Users.class, id);
		ProcessTemplateJY process=getProcessJYById(processId);
		if(process!=null&&user!=null){
			process.setOperatorDept(user.getDept());
			process.setOperatorCode(user.getCode());
			process.setOperatorCardId(user.getCardId());
			process.setOperatorName(user.getName());
			process.setOperatorUserId(user.getId());
			return totalDao.update(process);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> saveBacktoProcardTemplate(Integer id) {
		// TODO Auto-generated method stub
		boolean b=false;
		String msg="打回模板失败";
		List<ProcardTemplate> ptList=totalDao.query("from ProcardTemplate where rootId=id and markId= (select markId from ProcardTemplateJY where id=?)", id);
		if(ptList.size()>0){
			List<ProcardTemplateJY> ptJYList=totalDao.query("from ProcardTemplateJY where rootId=id and rootId=?", id);
			if(ptJYList.size()>0){
				ProcardTemplate pt=ptList.get(0);//模板总成对象
				ProcardTemplateJY ptJY=ptJYList.get(0);//精益总成对象
				String hql="from ProcardTemplateJY where procardTemplateJY.id=?";
				List list=totalDao.query(hql, ptJY.getId());
				if(ptJY.getProgressStatus()!=null&&ptJY.getProgressStatus().equals("完成")){
					msg="该精益bom已打入模板，不需要再次打入";
				}else if(ptJY.getProgressStatus()!=null&&ptJY.getProgressStatus().equals("执行中")){
					b=true;
					Set<ProcessTemplate> processSet=pt.getProcessTemplate();//模板总成对象的工序
					Set<ProcessTemplateJY> processJySet=ptJY.getProcessTemplateJY();//精益总成对象的工序
					List<ProcessData> pdList=null;//工艺规范总成的工序
					if(processSet!=null&&processSet.size()>0&&processJySet!=null&&processJySet.size()>0){
						for(ProcessTemplateJY processJY:processJySet){
							for(ProcessTemplate process:processSet){//双层遍历工序
								if(process.getProcessNO()!=null&&processJY.getProcessNO()!=null
										&&process.getProcessNO().equals(processJY.getProcessNO())){//找到同序号工序
									//改节拍
									process.setOpcaozuojiepai(processJY.getOpcaozuojiepai());
									process.setOpshebeijiepai(processJY.getOpshebeijiepai());
									process.setGzzhunbeijiepai(processJY.getGzzhunbeijiepai());
									process.setGzzhunbeicishu(processJY.getGzzhunbeicishu());
									//改设备
									if(processJY.getShebeistatus()!=null&&processJY.getShebeistatus().equals("是")){
										process.setShebeiId(processJY.getShebeiId());
										process.setShebeiName(processJY.getShebeiName());
										process.setShebeiNo(processJY.getShebeiNo());
									}
									//改量具
									if(processJY.getLiangjustatus()!=null&&processJY.getLiangjustatus().equals("是")){
										process.setMeasuringId(processJY.getMeasuringId());
										process.setMeasuring_no(process.getMeasuring_no());
										process.setMeasuringMatetag(processJY.getMeasuringMatetag());
										process.setMeasuringNumber(process.getMeasuringNumber());
									}
									//改工装
									if(processJY.getGongzhuangstatus()!=null&&processJY.getGongzhuangstatus().equals("是")){
										process.setGzstoreId(processJY.getGzstoreId());
										process.setNumber(processJY.getNumber());
										process.setMatetag(processJY.getMatetag());
									}
									b=b&totalDao.update(process);
								}
							}
						}
					}
					b=b&saveBacktoProcardTemplate2(pt,ptJY);
				}else{
					msg="精益模板目前还没有进入执行中状态不能打回模板";
				}
			}
		}else{
			b=false;
			msg="流水卡模板中没有该BOM,提交失败！";
		}
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		map.put(1, b);
		map.put(2, msg);
		return map;
	}
	/**
	 * 根据精益工序修改模板工序
	 * @param pt
	 * @param ptJY
	 * @return
	 */
	public boolean saveBacktoProcardTemplate2(ProcardTemplate pt,ProcardTemplateJY ptJY){
		boolean b=true;
		if(ptJY!=null&&pt!=null){
			if(ptJY.getProcardStyle()!=null&&!ptJY.getProcardStyle().equals("总成")){
				Set<ProcessTemplate> processSet=pt.getProcessTemplate();//模板对象的工序
				Set<ProcessTemplateJY> processJySet=ptJY.getProcessTemplateJY();//精益对象的工序
				List<ProcessData> pdList=null;//工艺规范总成的工序
				if(processSet!=null&&processSet.size()>0&&processJySet!=null&&processJySet.size()>0){
					for(ProcessTemplateJY processJY:processJySet){
						for(ProcessTemplate process:processSet){//双层遍历工序
							if(process.getProcessNO()!=null&&processJY.getProcessNO()!=null
									&&process.getProcessNO().equals(processJY.getProcessNO())){//找到同序号工序
								//改节拍
								process.setOpcaozuojiepai(processJY.getOpcaozuojiepai());
								process.setOpshebeijiepai(processJY.getOpshebeijiepai());
								process.setGzzhunbeijiepai(processJY.getGzzhunbeijiepai());
								process.setGzzhunbeicishu(processJY.getGzzhunbeicishu());
								//改设备
								if(processJY.getShebeistatus()!=null&&processJY.getShebeistatus().equals("是")){
									process.setShebeiId(processJY.getShebeiId());
									process.setShebeiName(processJY.getShebeiName());
									process.setShebeiNo(processJY.getShebeiNo());
								}
								//改量具
								if(processJY.getLiangjustatus()!=null&&processJY.getLiangjustatus().equals("是")){
									process.setMeasuringId(processJY.getMeasuringId());
									process.setMeasuring_no(process.getMeasuring_no());
									process.setMeasuringMatetag(processJY.getMeasuringMatetag());
									process.setMeasuringNumber(process.getMeasuringNumber());
								}
								//改工装
								if(processJY.getGongzhuangstatus()!=null&&processJY.getGongzhuangstatus().equals("是")){
									process.setGzstoreId(processJY.getGzstoreId());
									process.setNumber(processJY.getNumber());
									process.setMatetag(processJY.getMatetag());
								}
								b=b&totalDao.update(process);
							}
						}
					}
				}
			}
			Set<ProcardTemplate> ptSet=pt.getProcardTSet();
			Set<ProcardTemplateJY> ptJYSet=ptJY.getProcardTJYSet();
			if(ptSet!=null&&ptJYSet!=null){
				for(ProcardTemplateJY ptJY2:ptJYSet){
					for(ProcardTemplate pt2:ptSet){
						if(ptJY2.getMarkId()!=null&&pt2.getMarkId()!=null&&ptJY2.getMarkId().equals(pt2.getMarkId())){
							b=b&saveBacktoProcardTemplate2(pt2,ptJY2);
						}
					}
				}
			}
		}
		return b;
	}

	@Override
	public Map<Integer,Integer> getGygcIdAndProcessDataId(String markId,Integer processNo) {
		// TODO Auto-generated method stub
		Map<Integer,Integer> map=new HashMap<Integer, Integer>();
		Integer ggId=null;
		Integer pdId=null;
		List<Integer> ggidList=totalDao.query("select id from GongyiGuicheng where jianId=?", markId);
		if(ggidList.size()>0){
			ggId=ggidList.get(0);
			List<Integer> pdList=totalDao.query("select id from ProcessData where gongyiGuichengId=? and gongxuNo=?" , ggId,processNo);
		    if(pdList.size()>0){
		    	pdId=pdList.get(0);
		    }
		}
		map.put(1, ggId);
		map.put(2, pdId);
		return map;
	}


}
