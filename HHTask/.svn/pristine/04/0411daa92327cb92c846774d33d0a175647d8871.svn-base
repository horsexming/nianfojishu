package com.task.ServerImpl.shizhi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.shizhi.ProjectOrderServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.Users;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ProcessinforPeople;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.entity.vo.shizhivo.ProjectOrderVo;
import com.task.util.DateUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class ProjectOrderServerImpl implements ProjectOrderServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(ProjectOrder projectOrder) {
		// TODO Auto-generated method stub
		if (projectOrder != null) {
			ProTryMakeScore ptm = getProTryMakeScore(projectOrder
					.getProTryMakeScore().getId());
			projectOrder.setCusName(ptm.getCusName());
			projectOrder.setGroupName(ptm.getGroupName());
			projectOrder.setProName(ptm.getProName());
			projectOrder.setMonth(ptm.getMonth());
			projectOrder.setProTryMakeScore(ptm);
			if (projectOrder.getProductEngineer() != null
					&& projectOrder.getProductEngineer().equals("未选择")) {
				projectOrder.setProductEngineer(null);
			}
			if (projectOrder.getTechnicalEngineer() != null
					&& projectOrder.getTechnicalEngineer().equals("未选择")) {
				projectOrder.setTechnicalEngineer(null);
			}
			int maxNo = 0;
			String qianzui1 = "szd_"
					+ DateUtil.formatDate(new Date(), "yyyyMM");
			List<ProjectOrder> plist = findAll();
			if (plist != null) {// 设计订单号
				for (ProjectOrder p : plist) {
					if (p.getOrderNO() != null
							&& p.getOrderNO().startsWith(qianzui1)) {
						String numStr = p.getOrderNO().replaceFirst(qianzui1,
								"");
						try {
							int num = Integer.parseInt(numStr);
							if (maxNo < num) {
								maxNo = num;
							}
						} catch (Exception e) {
							return false;
						}
					}
				}
			}
			maxNo++;
			Users user = Util.getLoginUser();
			projectOrder.setUserName(user.getName());
			projectOrder.setUserCode(user.getCode());
			projectOrder.setUserId(user.getId());
			projectOrder.setOrderNO(qianzui1 + maxNo);
			projectOrder.setStatus("未申请");
			return totalDao.save(projectOrder);
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		// boolean b=true;
		ProjectOrder projectOrder = getById(id);
		// updateInputNumByDeletPo(projectOrder);
		// String month=projectOrder.getMonth();

		if (projectOrder != null) {
			boolean b = true;
			if (projectOrder.getEpId() != null) {
				CircuitRunServerImpl.deleteCircuitRun(projectOrder.getEpId());
				// CircuitRun cr=(CircuitRun)
				// totalDao.getObjectById(CircuitRun.class,
				// projectOrder.getEpId());
				// if(cr!=null){
				// b=b&totalDao.delete(cr);
				// }
			}
			if (b) {
				return totalDao.delete(projectOrder);
			}
		}
		// if(month!=null){
		// b=b&setTryMakeRateByMonth(month);
		// }
		return false;
	}

	@Override
	public List<ProjectOrder> findAll() {
		// TODO Auto-generated method stub
		List<Object> all = totalDao.query("from ProjectOrder");
		if (all.size() > 0) {
			List<ProjectOrder> ProjectOrders = new ArrayList<ProjectOrder>();
			for (Object o : all) {
				ProjectOrders.add((ProjectOrder) o);
			}
			return ProjectOrders;
		}

		return null;
	}

	@Override
	public Map<Integer, Object> findProjectOrdersByCondition(
			ProjectOrder projectOrder, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (projectOrder == null) {
			projectOrder = new ProjectOrder();
		}
		String hql = totalDao.criteriaQueries(projectOrder,
				"1=1 order by orderNO desc", null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public ProjectOrder getById(Integer id) {
		// TODO Auto-generated method stub
		if (id != null) {
			Object o = totalDao.getObjectById(ProjectOrder.class, id);
			if (o != null) {
				return (ProjectOrder) o;
			}
		}
		return null;
	}

	@Override
	public boolean update(ProjectOrder projectOrder) {
		// TODO Auto-generated method stub
		if (projectOrder != null && projectOrder.getId() != null) {
			ProjectOrder po = getById(projectOrder.getId());
			if (po != null) {
				po.setProductEngineer(projectOrder.getProductEngineer());
				po.setTechnicalEngineer(projectOrder.getTechnicalEngineer());
				po.setDeliveryInfo(projectOrder.getDeliveryInfo());
				po.setDeal(projectOrder.getDeal());
				po.setProjectBy(projectOrder.getProjectBy());
				po.setProjectTo(projectOrder.getProjectTo());
				po.setRemark(projectOrder.getRemark());
				return totalDao.update(po);
			}
		}
		return false;
	}

	@Override
	public List<ProTryMakeScore> findProTryMakeScoreAll() {
		// TODO Auto-generated method stub
		List all = totalDao.query("from ProTryMakeScore");
		if (all.size() > 0) {
			return (List<ProTryMakeScore>) all;
		}
		return null;
	}

	@Override
	public ProTryMakeScore getProTryMakeScore(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(ProTryMakeScore.class, id);
		if (o != null) {
			return (ProTryMakeScore) o;
		}
		return null;
	}

	@Override
	public String addapproval(ProjectOrder projectOrder) throws Exception {
		// TODO Auto-generated method stub
		ProjectOrder po = getById(projectOrder.getId());
		String msg = null;
		if (po != null) {
			Set<ProjectOrderPart> partSet = po.getProjectOrderPart();
			if (partSet != null) {
				for (ProjectOrderPart prat : partSet) {
					// if(prat.getType()==null||!prat.getType().equals("首件")){
					// List<ProjectOrderPart> list =
					// totalDao.query("from ProjectOrderPart where markId=? and type is not null and type='首件' and projectOrder.id in(select id from ProjectOrder where status='同意')",
					// prat.getMarkId());
					// if(list==null||list.size()==0 ){
					// return "零件:"+prat.getMarkId()+",还未申请过首件,请先申请首件!";
					// }
					// }
				}
			} else {
				return "请先申请要试制的零件,申请失败!";
			}
			if (po.getStatus() != null && po.getStatus().equals("打回")) {// 再次申请
				CircuitRunServerImpl.updateCircuitRun(po.getEpId());
			} else {// 头次申请
				Integer epId = CircuitRunServerImpl.createProcess("项目试制评审流程",
						ProjectOrder.class, projectOrder.getId(), "status",
						"id",
						"projectOrderAction_prodetail.action?projectOrder.id="
								+ projectOrder.getId(), "项目试制需求单申请审批", true);
				if (epId != null) {
					po.setEpId(epId);
				} else {
					return "审批流程有误，申请失败!";
				}
			}
			po.setStatus("未审批");
			return totalDao.update(po) + "";
		}

		return "没有找到申请目标,申请失败!";
	}

	@Override
	public ProjectOrderVo getProjectOrderVoById(Integer id) {
		// TODO Auto-generated method stub
		ProjectOrder po = getById(id);
		if (po != null) {
			List<String> qianmingList = new ArrayList<String>();
			if (po.getEpId() != null) {
				List<ExecutionNode> list = totalDao
						.query(
								"from ExecutionNode where circuitRun.id=? order by auditLevel",
								po.getEpId());
				if (list.size() > 0) {
					for (ExecutionNode en : list) {
						List<String> qmlist = totalDao
								.query(
										"select signature_address from Signature where default_address='默认' and code=(select code from Users where id=?)",
										en.getAuditUserId());
						if (qmlist.size() > 0) {
							qianmingList.add(qmlist.get(0));
						}
					}
				}
			}
			ProjectOrderVo pVo = new ProjectOrderVo(po);
			pVo.setQianmingUrl(qianmingList);
			return pVo;
		}
		return null;
	}

	@Override
	public String addProCard(ProjectOrder projectOrder,
			List<ProjectOrderPart> partList) {
		// TODO Auto-generated method stub
		String message = "生成流水卡片错误!原因如下:\\n";
		if (projectOrder != null && projectOrder.getId() != null) {
			// 查询订单信息
			ProjectOrder oldProjectOrder = (ProjectOrder) totalDao
					.getObjectById(ProjectOrder.class, projectOrder.getId());
			Boolean bool = false;
			if (partList != null) {
				if (partList == null) {
					message += "没有对应的需求单零件信息";
				}
				for (ProjectOrderPart part : partList) {
					Float numberDetail = 0f;// 需要转换的数量
					if (part.getWantturn() != null) {
						numberDetail = part.getWantturn();
					}

					if (numberDetail <= 0) {
						continue;
					}
					ProjectOrderPart oldPart = (ProjectOrderPart) totalDao
							.getObjectById(ProjectOrderPart.class, part.getId());
					if (oldPart == null) {
						continue;
					}
					String hql2 = "from ProcardTemplate where markId=? and belongLayer=1";
					ProcardTemplate procardTemplate = (ProcardTemplate) totalDao
							.getObjectByCondition(hql2, oldPart.getMarkId());// 通过件号查询对应流水卡片模板
					if (procardTemplate != null) {
						Float num = numberDetail;// 转换数量
						// 判断转换数量是否大于总计划量
						if (oldPart.getHasturn() == null) {
							oldPart.setHasturn(0f);
						}
						if (oldPart.getHasturn() + num > oldPart.getPartNum()) {
							message += "件号为" + oldPart.getMarkId()
									+ "的总转换数量不能大于计划总数量,请修改转换数量!";
							continue;
						}
						Float maxCount = procardTemplate.getMaxCount();// 流水卡片模板最大数量
						if (maxCount == null || maxCount <= 0) {
							message += procardTemplate.getMarkId()
									+ "的最大批次量不能为0!";
							break;
						}
						int chuNum = (int) (num / maxCount);// 相除数量(倍数)
						Float moNum = num % maxCount;// 余数
						List<Procard> procardList = new ArrayList<Procard>();// 生成的所有件号
						if (chuNum >= 1) {
							for (int i = 0; i < chuNum; i++) {
								Procard procard = null;
								try {
									procard = genProCard(procardTemplate,
											oldProjectOrder, null, 0, maxCount);
									procardList.add(procard);
								} catch (Exception e) {
									String hql_nullRootId = "from Procard where rootId is null";
									List<Procard> list_nullRootId = totalDao
											.query(hql_nullRootId);
									for (Procard procard2 : list_nullRootId) {
										totalDao.delete(procard2);
									}
									message += "请检查"
											+ procardTemplate.getMarkId()
											+ "的工艺模版信息是否有误!如:权值";
									e.printStackTrace();
									continue;
								}
							}
						}
						if (moNum != 0) {
							Procard procard = null;
							procard = genProCard(procardTemplate,
									oldProjectOrder, null, 0, moNum);
							procardList.add(procard);
						}
						// // 更新内部计划状态为"已生成"
						// Float turnCount = part.getTurnCount() + num;
						// if (turnCount == part.getNum()) {
						// bool = true;
						// }
						// if (bool) {
						// internalOrder.setZhStatus("已转完");
						// } else {
						// internalOrder.setZhStatus("未转完");
						// }
						oldPart.setHasturn(oldPart.getHasturn() + num);// 已转换数量

						/***
						 * 无卡模式，// 生成外购件采购计划
						 */
						message = "本计划的所有生产工艺卡片已经全部生成成功!";
					} else {
						message += "不存在件号为" + oldPart.getMarkId()
								+ "的流水卡片模版,请添加!";
					}
				}
			} else {
				message = "不存在您要查询的订单信息!";
			}
		} else {
			message = "无效的订单信息!";
		}
		return message;
	}

	/***
	 * 递归 生成流水卡片
	 * 
	 * @param procardTemplate
	 *            流水卡片模板
	 * @param internalOrder
	 *            内部计划单
	 * @param fatherProcard
	 *            上层流水卡片
	 * @return
	 */
	public Procard genProCard(ProcardTemplate procardTemplate,
			ProjectOrder projectOrder, Procard fatherProcard, Integer rootId,
			double number) {
		try {
			String message = "";
			if (procardTemplate != null) {
				// 生成流水卡片
				message += procardTemplate.getMarkId();
				Procard procard = new Procard();
				/**
				 * 将流水卡片模板转换为流水卡片
				 */
				BeanUtils.copyProperties(procardTemplate, procard,
						new String[] { "rootId", "fatherId", "id" });

				double needNumber = 0;
				if ("自制".equals(procard.getProcardStyle())) {
					if (procard.getLingliaostatus() == null
							|| !procard.getLingliaostatus().equals("否")) {
						needNumber = number * procard.getQuanzi2()
								/ procard.getQuanzi1();
					}
				} else if ("外购".equals(procard.getProcardStyle())) {
					needNumber = number * procard.getQuanzi2()
							/ procard.getQuanzi1();
				}

				Double d = Math.ceil(needNumber);
				procard.setNeedCount(Float.parseFloat(d.toString()));// 计算外购/自制的实际需求数量(自动进1取整)
				if ("外购".equals(procard.getProcardStyle())) {
					number = (double) procard.getNeedCount();
				}
				procard.setProductStyle("试制");
				procard.setPlanOrderId(projectOrder.getId());// 试制单id
				procard.setPlanOrderNum(projectOrder.getOrderNO());// 试制单单号
				procard.setProcardTime(Util.getDateTime());// 制卡时间
				procard.setSelfCard(findMaxSelfCard(procard.getMarkId()));// 批次号
				procard.setZhikaren(Util.getLoginUser().getName());// 制卡人(当前登录用户)
				procard.setBarcode(Util.getDateTime("yyyyMMddHHmmss"));// 条码
				procard.setStatus("初始");
				procard.setFilnalCount((float) number);// 生产数量

				// 设置调用关系
				if (fatherProcard != null) {
					procard.setFatherId(fatherProcard.getId());// 父类id
					procard.setRootId(rootId);// 更新rootId
					procard.setProcard(fatherProcard);// 设置父类
				}

				totalDao.save(procard);// 添加

				if (fatherProcard == null) {
					rootId = procard.getId();
					procard.setRootId(rootId);// 更新rootId
				}

				// 遍历该流水卡片对应工序并生成工序
				Set<ProcessTemplate> setProCess = procardTemplate
						.getProcessTemplate();
				for (ProcessTemplate processTem : setProCess) {
					ProcessInfor process = new ProcessInfor();
					BeanUtils.copyProperties(processTem, process, new String[] {
							"id", "procardTemplate" });
					// process.setProcessNO(processTem.getProcessNO());// 工序号
					// process.setProcessName(processTem.getProcessName());//
					// 工序名称
					// process.setProcessStatus(processTem.getProcessStatus());//
					// 状态(并行/单独)
					// process.setParallelId(processTem.getParallelId());//
					// 并行开始id

					// 人工节拍和设备节拍处理
					if (process.getOpcaozuojiepai() == null) {
						process.setOpcaozuojiepai(0F);
					}
					if (process.getOpshebeijiepai() == null) {
						process.setOpshebeijiepai(0F);
					}
					process.setTotalCount(procard.getFilnalCount());// 可领取量
					process.setStatus("初始");
					process.setProcard(procard);
					totalDao.save(process);
				}

				// 遍历查询子类流水卡片
				Set<ProcardTemplate> setProCard = procardTemplate
						.getProcardTSet();
				for (ProcardTemplate procardTem2 : setProCard) {
					if ("外购".equals(procardTem2.getProcardStyle())) {
						genProCard(procardTem2, projectOrder, procard, rootId,
								procard.getFilnalCount());
					} else {
						Float num = (float) number * procardTem2.getCorrCount();// 上层流水卡片数量*权值=总数量
						Float maxCount = procardTem2.getMaxCount();// 流水卡片模板最大数量
						if (maxCount > 0) {
							int chuNum = (int) (num / maxCount);// 相除数量(倍数)
							Float moNum = (float) (double) (num % maxCount);// 余数

							if (chuNum >= 1) {
								for (int i = 0; i < chuNum; i++) {
									genProCard(procardTem2, projectOrder,
											procard, rootId, procardTem2
													.getMaxCount());
								}
							}
							if (moNum != 0) {
								genProCard(procardTem2, projectOrder, procard,
										rootId, moNum);
							}
						} else {
							return null;
						}
					}
				}
				return procard;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// 查询该件号最大批次号
	public String findMaxSelfCard(String markId) {
		String mouth = new SimpleDateFormat("yyyyMMdd").format(new Date());
		int yy = Integer.parseInt(mouth.substring(0, 4));
		int mm = Integer.parseInt(mouth.substring(4, 6));
		int dd = Integer.parseInt(mouth.substring(6, 8));
		if (dd >= 26) {
			if (mm == 12) {
				mm = 1;
				yy++;
			} else {
				mm++;
			}
		}
		if (mm < 10) {
			mouth = yy + "0" + mm;
		} else {
			mouth = yy + "" + mm;
		}
		String hql = "select max(selfCard) from Procard where markId=? and selfCard like '%"
				+ mouth + "%'";
		Object object = (Object) totalDao.getObjectByCondition(hql, markId);
		if (object != null) {
			Long selfCard = Long.parseLong(object.toString()) + 1;// 当前最大流水卡片
			return selfCard.toString();
		} else {
			return mouth + "00001";
		}
	}

}
