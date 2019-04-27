package com.task.ServerImpl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.bargain.BargainServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.DeptNumber;
import com.task.entity.OaAppDetail;
import com.task.entity.Password;
import com.task.entity.Users;
import com.task.entity.approval.Signature;
import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.BarContractDetails;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainGoods;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.bargain.Company;
import com.task.entity.bargain.CompanyVO;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.QuotedPrice;
import com.task.entity.sop.OutSourcingApp;
import com.task.entity.system.CircuitRun;
import com.task.entity.system.ExecutionNode;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class BargainServerImpl implements BargainServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	/*
	 * 添加询比议价(non-Javadoc)
	 * 
	 * @see com.task.Server.bargain.BargainServer#addBargain(java.util.List,
	 * java.util.List, java.util.List)
	 */
	@Override
	public boolean addBargain(Bargain bargain1, List<BargainGoods> listgoods,
			List<Company> listcompany, String selected_status) {
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String createdate = Util.getDateTime("yyyy-MM-dd");
		String createdate1 = Util.getDateTime("yyyy-MM");
		String[] a = createdate1.split("-");
		createdate1 = a[0] + a[1];
		String hql = "select max(bargain_number) from Bargain";
		String max_number = (String) this.totalDao.getObjectByCondition(hql);
		Bargain bargain = new Bargain();
		if (max_number != null && !"".equals(max_number)) {
			long number2 = Long.parseLong(max_number) + 1;
			String number3 = Long.toString(number2);
			bargain.setBargain_number(number3);
		} else {
			String number2 = createdate1 + "001";
			bargain.setBargain_number(number2);
		}
		bargain.setApplicants_dept(loginUser.getDept());
		bargain.setBargain_date(createdate);
		// 保存议价缘由和议价单号
		bargain.setBargain_source(bargain1.getBargain_source());
		bargain.setBargain_num(bargain1.getBargain_num());

		if (listgoods != null) {
			Set<BargainGoods> newSet = new HashSet<BargainGoods>();
			for (int i = 0; i < listgoods.size(); i++) {
				BargainGoods goods = listgoods.get(i);
				goods.setBargain(bargain);
				newSet.add(goods);
				bool = this.totalDao.save(goods);// 保存议价物品表
			}
			bargain.setBargainGoods(newSet);
		}
		if (listcompany != null) {
			Set<BargainingDetails> newSet = new HashSet<BargainingDetails>();
			for (int i = 0; i < listcompany.size(); i++) {
				Company company = listcompany.get(i);
				if (i == Integer.parseInt(selected_status) - 1) {
					company.setSelected_status("是");
				} else {
					company.setSelected_status("否");
				}
				List<BargainingDetails> detailsList = company.getBargList();
				Set<BargainingDetails> newSet1 = new HashSet<BargainingDetails>();
				for (int j = 0; j < detailsList.size(); j++) {
					BargainingDetails details = detailsList.get(j);
					details.setCompany(company);
					details.setBargain(bargain);
					newSet1.add(details);
					// company.setBargSet((Set<BargainingDetails>) details);
					// newSet1.add(details);
				}
				company.setBargSet(newSet1);
				for (BargainingDetails details2 : newSet1) {
					this.totalDao.save(details2);// 保存议价明细
				}
				bargain.setBargainingDetails(newSet);
				// company.setBargSet(new HashSet<BargainingDetails>(
				// company.getBargList()));
				bool = this.totalDao.save(company);// 保存公司
			}
		}
		bargain.setStatus("未审核");
		bargain.setUser_name(loginUser.getName());// 申请人
		bargain.setApplicants_dept(loginUser.getDept());
		bool = this.totalDao.save(bargain);// 保存议价主表

		if (bool) {
			Integer epId;
			String dept = loginUser.getDept();
			String beforeName = "";
			if ("OA".equals(bargain.getBargain_source())) {
				beforeName = "OA";
			} else if ("SB".equals(bargain.getBargain_source())
					|| "设备".equals(bargain.getBargain_source())) {
				beforeName = "设备";
			} else if ("KVP".equals(bargain.getBargain_source())) {
				beforeName = "KVP";
			} else if ("紧急采购".equals(bargain.getBargain_source())) {
				beforeName = "紧急采购";
			} else if ("行政事务采购".equals(bargain.getBargain_source())) {
				beforeName = "行政事务采购";
			} else if ("原材料采购".equals(bargain.getBargain_source())) {
				beforeName = "原材料采购";
			} else if ("零部件及工序外委采购".equals(bargain.getBargain_source())) {
				beforeName = "工序外委采购";
			} else if ("外购工装".equals(bargain.getBargain_source())) {
				beforeName = "外购工装";
			}
			String processName = beforeName + "询比议价审核";
			try {
				// bargainAction_salBargain.action?bargain.id=${pageList.id}&test=1
				epId = CircuitRunServerImpl.createProcess(processName,
						Bargain.class, bargain.getId(), "status", "id",
						"bargainAction_salBargain.action?test=1&bargain.id="
								+ bargain.getId(), loginUser.getDept() + "部门的"
								+ beforeName + "询比议价单请您审核!", true);
				// epId = CircuitRunServerImpl.createProcess(processName,
				// Bargain.class,bargain.getId(), "status",
				// "id",loginUser.getDept() + "部门的"+beforeName+"询比议价单请您审核!",
				// true,null);
				// // epId =
				// CircuitRunServerImpl.createProcess(196,Bargain.class,
				// // bargain.getId(), "status", "id", loginUser.getDept()
				// // + "询比议价考核请您审核!", true,null);
				if (epId != null && epId > 0) {
					bargain.setEpId(epId);
					totalDao.update(bargain);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	/*
	 * 查看询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findBargain(com.task.entity.bargain
	 * .Bargain, int, int)
	 */
	@Override
	public Object[] findBargain(Bargain bargain, int parseInt, int pageSize,
			String test,String markId) {
		if (bargain == null) {
			bargain = new Bargain();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(bargain, null);
		if (test != null && !"".equals(test)) {
			hql += "and user_name='" + loginUser.getName() + "'";
		}
		if(markId!=null&&markId.length()>0){
			hql +=" and id in (select bargain.id from BargainGoods where goods_format like'%"+markId+"%')";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] findBargain_name(Bargain bargain,String name) {
		if (bargain == null) {
			bargain = new Bargain();
		}
		String hql = totalDao.criteriaQueries(bargain, null);
//		if (test != null && !"".equals(test)) {
//			hql += "and user_name='" + loginUser.getName() + "'";
//		}
//		if(markId!=null&&markId.length()>0){
//			hql +=" and id in (select bargain.id from BargainGoods where goods_format like'%"+markId+"%')";
//		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, 1, 100);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	/*
	 * 
	 * 询比议价审核管理(non-Javadoc)
	 * 
	 * @see com.task.Server.bargain.BargainServer#findExamList(int, int)
	 */
	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Bargain.class, false);
		if (map != null) {
			String hql = "from Bargain where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 询比议价审批(通过、驳回)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#updateExamBonus(java.lang.Integer
	 * [], java.lang.String)
	 */
	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				Bargain detail = (Bargain) totalDao.getObjectById(
						Bargain.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
					}
					// 更改明细状态
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, true);
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 修改及查看询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findBargainById(com.task.entity
	 * .bargain.Bargain)
	 */
	@Override
	public Map<String, Object> findBargainById(Bargain bargain) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap();
		Bargain bargain1 = (Bargain) this.totalDao.getObjectById(Bargain.class,
				bargain.getId());

		String hql = "from Company where id in( select company.id from BargainingDetails where"
				+ "  bargain_id in(select id from Bargain where id=?))";
		List<Company> companies = this.totalDao.query(hql, bargain.getId());
		List<CompanyVO> companyVOs = new ArrayList<CompanyVO>();
		if (companies.size() > 0) {
			for (int i = 0; i < companies.size(); i++) {
				CompanyVO compayVo = new CompanyVO();
				Company company = companies.get(i);
				BeanUtils.copyProperties(company, compayVo, new String[] {
						"bargList", "bargSet" });
				List<BargainingDetails> bdList = new ArrayList<BargainingDetails>();
				Set<BargainingDetails> bdSet = company.getBargSet();
				if (bdSet.size() > 0) {
					for (BargainingDetails bd : bdSet) {
						bdList.add(bd);
					}
				}
				compayVo.setBargainingDetails(bdList);
				companyVOs.add(compayVo);
			}
		}

		String hql1 = "from BargainGoods  where bargain.id=?";
		List list = this.totalDao.query(hql1, bargain.getId());

		List<BargainGoods> bargainGoods = new ArrayList<BargainGoods>();
		for (int i = 0; i < list.size(); i++) {
			BargainGoods goods = (BargainGoods) list.get(i);
			bargainGoods.add(goods);
		}
		/*
		 * String hql1 = "from BargainingDetails where bargain.id=?"; List list2
		 * = this.totalDao.query(hql1, bargain.getId());
		 * 
		 * List<BargainingDetails> details = new ArrayList<BargainingDetails>();
		 * // List<Company> companies = new ArrayList<Company>(); List<Company>
		 * companies2 = new ArrayList<Company>(); for (int i = 0; i <
		 * list2.size(); i++) { BargainingDetails details2 = (BargainingDetails)
		 * list2.get(i); details.add(details2); try { List<BargainingDetails>
		 * list3 =
		 * this.totalDao.query("from BargainingDetails where id="+details2
		 * .getCompany().getId()); Company company =(Company)
		 * this.totalDao.getObjectById(Company.class,
		 * list3.get(0).getCompany().getId()); companies2.add(company);
		 * 
		 * } catch (Exception e) { }
		 * 
		 * }
		 */
		// map.put("details", details);
		// map.put("companies", companies2);
		map.put("bargain", bargain1);
		map.put("bargainGoods", bargainGoods);
		map.put("companyVOs", companyVOs);
		return map;
	}

	/*
	 * 查看询比议价对应审批节点人(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findBargain_ExecutionNode(java.
	 * lang.Integer)
	 */
	@Override
	public List<Map> findBargain_ExecutionNode(Integer payId) {
		Bargain bargain = (Bargain) this.totalDao.getObjectById(Bargain.class,
				payId);
		String hql = "select auditUserName from ta_sys_ExecutionNode where auditStatus='同意' and  fk_exeProId="
				+ bargain.getEpId();
		List<Map> maps = this.totalDao.findBySql(hql);
		return maps;
	}

	/*
	 * 删除询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#delBargain(com.task.entity.bargain
	 * .Bargain)
	 */
	@Override
	public void delBargain(Bargain bargain) {
		// TODO Auto-generated method stub
		String hql2 = "from Company where id in( select company.id from BargainingDetails where"
				+ "  bargain_id in(select id from Bargain where id=?))";
		List<Company> comList = totalDao.query(hql2, bargain.getId());
		if (comList.size() > 0) {
			for (int i = 0; i < comList.size(); i++) {
				Company company = comList.get(i);
				company.setBargSet(null);
				this.totalDao.delete(company);
			}
		}
		String hql = "from BargainGoods where bargain.id=?";
		Bargain bargain2 = (Bargain) this.totalDao.getObjectById(Bargain.class,
				bargain.getId());
		List list = this.totalDao.query(hql, bargain.getId());
		CircuitRunServerImpl.deleteCircuitRun(bargain2.getEpId());

		this.totalDao.delete(bargain2);
	}

	/*
	 * 修改询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#updateBargain(com.task.entity.bargain
	 * .Bargain, java.util.List, java.util.List)
	 */
	@Override
	public void updateBargain(Bargain bargain, List<BargainGoods> listgoods,
			List<Company> listcompany, String selected_status) {
		// TODO Auto-generated method stub
		Bargain bargain2 = (Bargain) this.totalDao.getObjectById(Bargain.class,
				bargain.getId());
		if (bargain2 != null && "打回".equals(bargain2.getStatus())) {
			CircuitRunServerImpl.updateCircuitRun(bargain2.getEpId());
			bargain2.setStatus("未审核");
		}
		if (bargain2 != null && bargain2.getEpId() == null) {
			Users loginUser = Util.getLoginUser();
			Integer epId;
			String dept = loginUser.getDept();
			String beforeName = "";
			if ("OA".equals(bargain2.getBargain_source())) {
				beforeName = "OA";
			} else if ("SB".equals(bargain2.getBargain_source())
					|| "设备".equals(bargain2.getBargain_source())) {
				beforeName = "设备";
			} else if ("KVP".equals(bargain2.getBargain_source())) {
				beforeName = "KVP";
			} else if ("紧急采购".equals(bargain2.getBargain_source())) {
				beforeName = "紧急采购";
			} else if ("行政事务采购".equals(bargain2.getBargain_source())) {
				beforeName = "行政事务采购";
			} else if ("原材料采购".equals(bargain2.getBargain_source())) {
				beforeName = "原材料采购";
			} else if ("零部件及工序外委采购".equals(bargain2.getBargain_source())) {
				beforeName = "工序外委采购";
			} else if ("外购工装".equals(bargain2.getBargain_source())) {
				beforeName = "外购工装";
			}
			String processName = dept + "" + beforeName + "询比议价审核";
			try {
				// bargainAction_salBargain.action?bargain.id=${pageList.id}&test=1
				epId = CircuitRunServerImpl.createProcess(processName,
						Bargain.class, bargain2.getId(), "status", "id",
						"bargainAction_salBargain.action?test=1&bargain.id="
								+ bargain2.getId(), loginUser.getDept() + "部门的"
								+ beforeName + "询比议价单请您审核!", true);
				// epId = CircuitRunServerImpl.createProcess(processName,
				// Bargain.class,bargain.getId(), "status",
				// "id",loginUser.getDept() + "部门的"+beforeName+"询比议价单请您审核!",
				// true,null);
				// epId = CircuitRunServerImpl.createProcess(196,Bargain.class,
				// bargain.getId(), "status", "id", loginUser.getDept()
				// + "询比议价考核请您审核!", true,null);
				if (epId != null && epId > 0) {
					bargain2.setEpId(epId);
					totalDao.update(bargain2);
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		// 对物品进行操作
		bargain2.setBargainGoods(new HashSet<BargainGoods>(listgoods));
		totalDao.update(bargain2);
		String hql_delGoods = "delete BargainGoods where bargain.id is null";
		totalDao.createQueryUpdate(hql_delGoods, null);

		String hql2 = "select id from Company where id in( select company.id from BargainingDetails where"
				+ "  bargain_id in(select id from Bargain where id=?))";
		List<Integer> oldcomidList = totalDao.query(hql2, bargain.getId());
		List<Integer> comidList = new ArrayList<Integer>();
		for (Company c : listcompany) {
			if (c != null && c.getId() != null) {
				comidList.add(c.getId());
			}
		}
		for (Integer cId : oldcomidList) {
			if (!comidList.contains(cId)) {// 删除公司
				totalDao.delete(totalDao.getObjectById(Company.class, cId));
			}
		}
		for (int i = 0; i < listcompany.size(); i++) {
			Company company = listcompany.get(i);
			if (selected_status != null && !"".equals(selected_status)) {
				if (i == Integer.parseInt(selected_status) - 1) {
					company.setSelected_status("是");
				} else {
					company.setSelected_status("否");
				}
			}
			if (company != null) {
				if (company.getId() != null) {
					Set<BargainingDetails> BarSet = new HashSet<BargainingDetails>();
					// 更新公司信息
					this.totalDao.update(company);
					// 对议价明细进行操作
					List<BargainingDetails> detailList = listcompany.get(i)
							.getBargList();
					if (detailList.size() > 0) {
						List<Integer> allId = new ArrayList<Integer>();
						for (int j = 0; j < detailList.size(); j++) {
							BargainingDetails bargainingDetails = detailList
									.get(j);
							if (bargainingDetails.getId() != null) {
								allId.add(bargainingDetails.getId());
							}
						}
						List<Integer> oldAllId = totalDao
								.query("select id from BargainingDetails where company.id="
										+ listcompany.get(i).getId());
						List<Integer> todeleteId = new ArrayList<Integer>();
						List<Integer> toupdate = new ArrayList<Integer>();
						for (Integer id : oldAllId) {
							if (!allId.contains(id)) {
								todeleteId.add(id);
							} else {
								toupdate.add(id);
							}
						}
						for (int j = 0; j < detailList.size(); j++) {
							BargainingDetails bargainingDetails = detailList
									.get(j);
							if (bargainingDetails.getId() != null
									&& todeleteId.contains(bargainingDetails
											.getId())) {
								// 删除
								this.totalDao.delete(bargainingDetails);
							} else {
								// 添加及更新
								bargainingDetails.setBargain(bargain2);
								BarSet.add(bargainingDetails);
							}
						}

					}
					company.setBargSet(BarSet);
					this.totalDao.update(company);
				} else {
					List<BargainingDetails> detailsList = company.getBargList();
					Set<BargainingDetails> newSet1 = new HashSet<BargainingDetails>();
					for (int j = 0; j < detailsList.size(); j++) {
						BargainingDetails details = detailsList.get(j);
						details.setBargain(bargain2);
						details.setCompany(company);
						newSet1.add(details);
					}
					company.setBargSet(newSet1);
					boolean b1 = false;
					boolean b2 = false;
					try {
						b1 = this.totalDao.save(company);// 保存公司
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						System.out.println(b1);
					}

				}
				String hql_delDetail = "delete BargainingDetails where company.id is null";
				totalDao.createQueryUpdate(hql_delDetail, null);
			}
		}

	}

	/*
	 * 查询各节点的审批意见(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findExecutionOpinion(java.lang.
	 * Integer)
	 */
	@Override
	public List findExecutionOpinion(Integer payId) {
		Bargain bargain = (Bargain) this.totalDao.getObjectById(Bargain.class,
				payId);
		String hql = "from ExecutionNode where circuitRun.id=?";
		List list = this.totalDao.query(hql, bargain.getEpId());
		return list;
	}

	/*
	 * 查询OA单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource1(java.lang.String
	 * )
	 */
	@Override
	public List findbargainSource1(String source) {
		// TODO Auto-generated method stub
		String hql = "from OaAppDetail where detailChildClass='工装' and  detailStatus in('同意','采购中')";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 查询OA单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource1(java.lang.String
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findbargainSource4(String source) {
		if (source != null && source.length() > 0) {
			source = "'" + source.replaceAll(",%20", "','") + "'";
		}
		String hql = "from OaAppDetail where detailChildClass='工装' and  detailStatus in('同意','采购中') and detailSeqNum in ("
				+ source + ")";
		List<OaAppDetail> list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 查询设备维修单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource2(java.lang.String
	 * )
	 */
	@Override
	public List findbargainSource2(String source) {
		String hql = "from Maintenance";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 查询KVP单号 (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource3(java.lang.String
	 * )
	 */
	@Override
	public List findbargainSource3(String source) {
		// TODO Auto-generated method stub
		String hql = "select executeNumber from ExecuteKVP where status='同意'";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 添加OA合同(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#saveBarContract(com.task.entity
	 * .bargain.BarContract, com.task.entity.bargain.BarContractDetails)
	 */
	@Override
	public boolean saveBarContract(BarContract barContract,
			List<BarContractDetails> barContractDetailList,Integer id) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String createdate1 = Util.getDateTime("yyyyMM");
		Set<BarContractDetails> barSet = new HashSet<BarContractDetails>();
		// 明细
		for (int i = 0; i < barContractDetailList.size(); i++) {
			BarContractDetails barContractDetails = barContractDetailList
					.get(i);
			barContractDetails.setBarContract(barContract);
			barSet.add(barContractDetails);
		}
		barContract.setSetbarContractDetails(barSet);
		// 主表
		String number4 = barContract.getContract_source();
		if ("紧急采购".equals(barContract.getContract_source())) {
			number4 = "JJ";
		}
		if ("零部件及工序外委采购".equals(barContract.getContract_source())) {
			number4 = "GXWW";
		}
		if ("原材料采购".equals(barContract.getContract_source())) {
			number4 = "YCL";
		}
		if ("工装采购".equals(barContract.getContract_source())) {
			number4 = "GZ";
		}
		if ("设备".equals(barContract.getContract_source())) {
			number4 = "Machine";
		}
		if ("包装物".equals(barContract.getContract_source())) {
			number4 = "BZW";
		}
		String max_number = (String) totalDao
				.getObjectByCondition("select max(contract_num) from BarContract where contract_num like'"
						+ number4 + createdate1 + "-%'");
		int maxnumber = 1001;
		if (max_number != null) {
			String[] strs = max_number.split("-");
			if (strs != null && strs.length == 2) {
				try {
					Integer number = Integer.parseInt(strs[1]);
					maxnumber = number;
					maxnumber++;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		barContract.setContract_num(number4 + createdate1 + "-" + maxnumber);

		barContract.setStatus("同意");
		barContract.setUsername(loginUser.getName());
		barContract.setDept(loginUser.getDept());
		bool = this.totalDao.save(barContract);
		if ("零部件及工序外委采购".equals(barContract.getContract_source())) {
			String hql = " from ZhUser where (name = ? or cmp =?)";
			ZhUser zhuser =	(ZhUser) totalDao.getObjectByCondition(hql, barContract.getSupplier(),barContract.getSupplier());
			if(zhuser == null){
				zhuser = new ZhUser();
				String usercode = "";
				String hql_usercode = "select max(usercode) from ZhUser where usercode like '%gys_%'";
				String maxcode =	(String) totalDao.getObjectByCondition(hql_usercode);
				if(maxcode!=null && maxcode.length()>0){
					Integer num = Integer.parseInt(maxcode.substring(4));
						num+=1;
					usercode = "gys_"+num;
				}else{
					usercode = "gys_001";
				}
				zhuser.setBlackliststauts("正常使用");
				zhuser.setUsercode(usercode);
				zhuser.setName(barContract.getSupplier());//供应商公司名称;
				zhuser.setCmp(barContract.getSupplier());//供应商公司全称;
				zhuser.setCompanydz(barContract.getAddress());//供应商公司地址；
				zhuser.setCfax(barContract.getTel());//电话;
				zhuser.setUserdept("供应商");
				zhuser.setFkfs(barContract.getPaymentway1()+";"+barContract.getPaymentway2()+";"+barContract.getPaymentway3());//付款方式
				totalDao.save(zhuser);
				Users User = new Users();
				User.setMore(zhuser.getCmp());
				User.setCode(usercode);
				User.setSex("男");
				User.setDuty("供应商");
				User.setOnWork("在职");
				User.setInternal("否");
				String hql_dn = "from DeptNumber where deptNumber=?";
				DeptNumber deptNumber = (DeptNumber) totalDao
						.getObjectByCondition(hql_dn, "gys");// 查询部门编号
				if (deptNumber != null) {
					User.setDept(deptNumber.getDept());
					User.setDeptId(deptNumber.getId());
					Password password = new Password();
					password
							.setPassword("e10adc3949ba59abbe56e057f20f883e");// 默认密码(123456)
					password.setDeptNumber("gys");
					password.setPresentAddress(zhuser.getCompanydz());
					password.setUserStatus("完成");
					password.setUser(User);
					User.setPassword(password);
					 totalDao.save(User);
					 zhuser.setUserid(User.getId());
					totalDao.update(zhuser);
					barContract.setGysId(zhuser.getId());
					totalDao.update(barContract);
				}
			}
			
		}
		// if (bool) {
		// Integer epId = null;
		// String processName = "采购合同审批";
		// try {
		// epId = CircuitRunServerImpl.createProcess(processName,
		// BarContract.class, barContract.getId(), "status", "id",
		// "bargainAction_salBarContract.action?status=print&barContract.id="
		// + barContract.getId(), loginUser.getDept()
		// + "部门的采购合同审批请您审核!", true);
		// epId = CircuitRunServerImpl.createProcess(processName,
		// BarContract.class, barContract.getId(), "status", "id",
		// loginUser.getDept() + "部门的采购合同审批请您审核!", true, null);
		// barContract.setEpId(epId);
		// this.totalDao.update(barContract);
		// } catch (Exception e) {
		// e.printStackTrace();
		//
		// }
		Performsingle p=null;
		try {
			p = (Performsingle) totalDao.get(Performsingle.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(p!=null){
			p.setHt_status("已生成");
			totalDao.update(p);
		}
		return bool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findBarContract(com.task.entity
	 * .bargain.BarContract, int, int)
	 */
	@Override
	public Object[] findBarContract(BarContract barContract, int parseInt,
			int pageSize, String test) {
		if (barContract == null) {
			barContract = new BarContract();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(barContract, null);
		if (barContract != null) {
			if (barContract.getContract_num() != null
					&& !"".equals(barContract.getContract_num())) {// 查询编号
				hql += "and contract_num like '%"
						+ barContract.getContract_num() + "%'";
			}
			if (barContract.getContract_source() != null
					&& !"".equals(barContract.getContract_source())) {
				hql += "and contract_source like '%"
						+ barContract.getContract_source() + "%'";
			}
		}
		if (test != null && "self".equals(test)) {
			hql += "and username='" + loginUser.getName() + "'";
		}else if("dept".equals(test)){
			hql += "and dept='" + loginUser.getDept() + "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 查看合同(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#salBarContract(com.task.entity.
	 * bargain.BarContract)
	 */
	@Override
	public BarContract salBarContract(BarContract barContract) {
		// TODO Auto-generated method stub
		BarContract barContract1 = (BarContract) this.totalDao.getObjectById(
				BarContract.class, barContract.getId());
		return barContract1;
	}

	/*
	 * 查询OA合同明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#salBarContractDetail(com.task.entity
	 * .bargain.BarContract)
	 */
	@Override
	public List salBarContractDetail(BarContract barContract) {
		String hql = "from BarContractDetails where barContract.id=?";
		List list = this.totalDao.query(hql, barContract.getId());
		return list;
	}

	/*
	 * 修改合同(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#updateBarContract(com.task.entity
	 * .bargain.BarContract)
	 */
	@Override
	public boolean updateBarContract(BarContract barContract,
			List<BarContractDetails> barContractDetailsList) {
		// TODO Auto-generated method stub
		BarContract barContract2 = (BarContract) this.totalDao.getObjectById(
				BarContract.class, barContract.getId());
		barContract2.setContract_name(barContract.getContract_name());
		barContract2.setSupplier(barContract.getSupplier());
		barContract2.setSignedPlace(barContract.getSignedPlace());
		barContract2.setStartDate(barContract.getStartDate());
		barContract2.setEndDate(barContract.getEndDate());
		barContract2.setContract_according(barContract.getContract_according());
		barContract2.setHeji(barContract.getHeji());
		barContract2.setSignedDate(barContract.getSignedDate());
		barContract2.setPaymentDate(barContract.getPaymentDate());
		barContract2.setDeliveryPlace(barContract.getDeliveryPlace());

		barContract2.setIstax(barContract.getIstax());
		barContract2.setTranspor_tway(barContract.getTranspor_tway());
		barContract2.setPack_ask(barContract.getPack_ask());

		barContract2.setProjectname(barContract.getProjectname());
		barContract2.setDeliverydate(barContract.getDeliverydate());
		barContract2.setPaymentway1(barContract.getPaymentway1());
		barContract2.setPaymentway2(barContract.getPaymentway2());
		barContract2.setPaymentway3(barContract.getPaymentway3());

		// 乙方信息
		barContract2.setUnit_name(barContract.getUnit_name());
		barContract2.setAddress(barContract.getAddress());
		barContract2.setTel(barContract.getTel());
		barContract2.setAccountBank(barContract.getAccountBank());
		barContract2.setAccountnum(barContract.getAccountnum());
		barContract2.setZip(barContract.getZip());
		barContract2.setSetbarContractDetails(new HashSet<BarContractDetails>(
				barContractDetailsList));
		boolean bool = this.totalDao.update(barContract2);
		if (barContract2 != null && "打回".equals(barContract2.getStatus())) {
			CircuitRunServerImpl.updateCircuitRun(barContract2.getEpId());
			barContract2.setStatus("未审核");
		}
		return bool;
	}

	/*
	 * 删除合同(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#delBarContract(com.task.entity.
	 * bargain.BarContract)
	 */
	@Override
	public boolean delBarContract(BarContract barContract) {
		BarContract barContract2 = (BarContract) this.totalDao.getObjectById(
				BarContract.class, barContract.getId());
		boolean bool = this.totalDao.delete(barContract2);
		return bool;
	}

	/*
	 * 查询询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource5(java.lang.String
	 * )
	 */
	@Override
	public List findbargainSource5(String source) {
		// TODO Auto-generated method stub
		String hql = "from Bargain where status='同意'";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 根据议价单号查询询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource6(java.lang.String
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object[] findbargainSource6(String contractNum1) {
		// TODO Auto-generated method stub
		Object[] o = null;
		String hql = "from Bargain where bargain_number=?";
		Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(hql,
				contractNum1);
		String hql1 = "from BargainingDetails where bargain.id=?";
		List<BargainingDetails> listDetails = this.totalDao.query(hql1, bargain
				.getId());
		// 找到议价物品
		String hql3 = "from BargainGoods where bargain.id=?";
		List<BargainGoods> listGoods = this.totalDao.query(hql3, bargain
				.getId());
		Float money = 0F;
		for (int i = 0; i < listDetails.size(); i++) {
			// 找到议价明细
			BargainingDetails bargainingDetails = listDetails.get(i);
			// 从议价找到公司
			Company company = bargainingDetails.getCompany();
			if ("是".equals(company.getSelected_status())) {
				String hql2 = "select min(unitprice) from BargainingDetails where company.id=? and unitprice is not null ";
				money = (Float) totalDao.getObjectByCondition(hql2, company
						.getId());
			}
		}
		if (money != null && money != 0F) {
			o = new Object[] { listGoods, money };
		} else {
			o = new Object[] { listGoods, null };
		}
		return o;
	}

	/*
	 * 根据设备单号查询设备(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource7(java.lang.String
	 * )
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Object[] findbargainSource7(String contractNum1) {
		// Object[] o = null;
		// String hql = "from Maintenance where barcode=?";
		// List list = this.totalDao.query(hql, contractNum1);
		// o = new Object[]{list};
		// return o;
		contractNum1 = URLDecoder.decode(contractNum1);// 解除转码效果。

		Object[] o = null;
		String hql = "from Performsingle where purchase_number=?";
		Performsingle performsingle = (Performsingle) this.totalDao
				.getObjectByCondition(hql, contractNum1);
		if (performsingle != null) {
			String hql1 = "from PerformsingleDetail where performsingle.id=?";
			List list = this.totalDao.query(hql1, performsingle.getId());
			o = new Object[] { list };
		}
		return o;
	}

	/*
	 * 根据kvp编号查询kvp(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource8(java.lang.String
	 * )
	 */
	@Override
	public Object[] findbargainSource8(String contractNum1) {
		Object[] o = null;
		String hql = "from ExecuteKVP where executeNumber=?";
		List list = this.totalDao.query(hql, contractNum1);
		o = new Object[] { list };
		return o;
	}

	/*
	 * 采购合同审批列表(non-Javadoc)
	 * 
	 * @see com.task.Server.bargain.BargainServer#findContractExamList(int, int)
	 */
	@Override
	public Object[] findContractExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				BarContract.class, false);
		if (map != null) {
			String hql = "from BarContract where id in (:entityId)";
			List list = totalDao.find(hql, map, parseInt, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/*
	 * 采购合同审批(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#updateContractExamList(java.lang
	 * .Integer[], java.lang.String)
	 */
	@Override
	public boolean updateContractExamList(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				BarContract detail = (BarContract) totalDao.getObjectById(
						BarContract.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", false, null, false);
					if ("同意".equals(circuitRun.getAllStatus())) {
						// 审批同意后做后续操作
					}
					// 更改明细状态
				} else {// 打回
					circuitRunServer.updateExeNodeByCirId(detail.getEpId(),
							false, "", false, null, true);
					totalDao.update(detail);
				}
				bool = true;
			}
		}
		return bool;
	}

	/*
	 * 根据合同编号查询电子签名(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findContractExecutionNode(java.
	 * lang.Integer)
	 */
	@Override
	public Map<Integer, Object> findContractExecutionNode(Integer contractId) {
		// BarContract barContract = (BarContract)
		// this.totalDao.getObjectById(BarContract.class, contractId);
		Performsingle performsingle = (Performsingle) this.totalDao
				.getObjectById(Performsingle.class, contractId);
		String hql = "from ExecutionNode where auditStatus='同意' and  circuitRun.id=? order by auditLevel desc";
		List<ExecutionNode> list1 = this.totalDao.query(hql, performsingle
				.getEpId());
		List<Signature> list = new ArrayList<Signature>();
		for (int i = 0; i < list1.size(); i++) {
			ExecutionNode executionNode = (ExecutionNode) list1.get(i);
			String username = executionNode.getAuditUserName();
			String hql1 = "from Signature where name='" + username
					+ "' and default_address='默认' ";
			Signature signature = (Signature) this.totalDao
					.getObjectByCondition(hql1);
			list.add(signature);
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, list1);
		return map;
	}

	/*
	 * 根据议价单号查询外委评审(non-Javadoc)
	 * 
	 * @see com.task.Server.bargain.BargainServer#findbargainNumber()
	 */
	@Override
	public List findbargainNumber() {
		String hql = "";
		// if(tag.equals("number")){
		hql = "select distinct(markID) from OutSourcingApp where executeStatus='评审完成'";
		// }else if(tag.equals("producetype")){
		// hql =
		// "select distinct(processNO) from OutSourcingApp where executeStatus='评审完成'";
		// }else if(tag.equals("gx_type")){
		// hql =
		// "select distinct(customer) from OutSourcingApp where executeStatus='评审完成'";
		// }
		List list = this.totalDao.query(hql, null);
		return list;
	}

	/*
	 * 根据件号查询型别(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainNumber1(java.lang.String
	 * )
	 */
	@Override
	public List findbargainNumber1(String gxNumber) {
		// TODO Auto-generated method stub
		String hql = "select distinct(customer) from OutSourcingApp where markID=? and  executeStatus='评审完成'";
		List list = this.totalDao.query(hql, gxNumber);
		return list;
	}

	/*
	 * 根据件号查询生产类型(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainNumber2(java.lang.String
	 * )
	 */
	@Override
	public List findbargainNumber2(String gx_number) {
		// TODO Auto-generated method stub
		String hql = "select distinct(processNO) from OutSourcingApp where markID=? and  executeStatus='评审完成'";
		List list = this.totalDao.query(hql, gx_number);
		return list;
	}

	/*
	 * 根据件号查询询价单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainNumber3(java.lang.String
	 * )
	 */
	@Override
	public List findbargainNumber3(String gxNumber) {
		// TODO Auto-generated method stub
		String hql = "from QuotedPrice where id in(select rootId from OutSourcingApp where markID='"
				+ gxNumber + "'  and  executeStatus='评审完成')";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 根据询价单号查询项目编号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainNumber4(java.lang.String
	 * )
	 */
	@Override
	public List findbargainNumber4(String quotedNumber) {
		String hql = "from ProjectManage where id in(select proId from QuotedPrice where quotedNumber='"
				+ quotedNumber + "')";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 根据件号下拉名称(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainNumber5(java.lang.String
	 * )
	 */
	@Override
	public List findbargainNumber5(String gx_number) {
		String hql = "from OutSourcingApp where markID=? and  executeStatus='评审完成'";
		List list = this.totalDao.query(hql, gx_number);
		return list;
	}

	/*
	 * 查询合同明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#salContractDetails(java.lang.Integer
	 * )
	 */
	@Override
	public List<BarContractDetails> salContractDetails(Integer id) {
		String hql = "from BarContractDetails where barContract.id=?";
		List<BarContractDetails> barContractDetails = this.totalDao.query(hql,
				id);
		return barContractDetails;
	}

	/*
	 * 通过项目编号查询项目(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#salProjectManage(java.lang.String)
	 */
	@Override
	public ProjectManage salProjectManage(String detailItemId) {
		// TODO Auto-generated method stub
		String hql = "from ProjectManage where  projectNum=?";
		ProjectManage projectManage = (ProjectManage) this.totalDao
				.getObjectByCondition(hql, "PMD-2014-33");
		return projectManage;
	}

	/*
	 * 零部件及工序外委采购(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource9(java.lang.String
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findbargainSource9(String source) {
		List list1 = totalDao
				.query(
						"select purchase_number from Performsingle where purchase_category =?",
						source);
		String components = "'"
				+ list1.toString().replaceAll("\\[", "").replaceAll("\\]", "")
						.replaceAll(",", "','").replaceAll(" ", "") + "'";
		String hql = "select osaNO from OutSourcingApp where executeStatus='评审完成' and osaNO not in ("
				+ components + ")";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 查询执行单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findPerformsingle(java.lang.String)
	 */
	@Override
	public Performsingle findPerformsingle(String contractNum1) {
		String hql = "from Performsingle where purchase_number=? ";
		Performsingle performsingle = (Performsingle) this.totalDao
				.getObjectByCondition(hql, contractNum1);
		return performsingle;
	}

	/*
	 * 查询原材料明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findbargainSource10(java.lang.String
	 * )
	 */
	@Override
	public List findbargainSource10(String contractNum1) {
		String hql = "from Performsingle  where purchase_number=?";
		Performsingle performsingle = (Performsingle) this.totalDao
				.getObjectByCondition(hql, contractNum1);
		if (performsingle != null) {
			String hql1 = "from PerformsingleDetail where performsingle.id=?";
			List list = (List) this.totalDao.query(hql1, performsingle.getId());
			return list;
		} else {
			return null;
		}
	}

	/*
	 * 查询设备(non-Javadoc)
	 * 
	 * @see com.task.Server.bargain.BargainServer#findbargainSource11()
	 */
	@Override
	public List findbargainSource11() {
		String hql = "from Machine";
		List list = this.totalDao.query(hql);
		return list;
	}

	/*
	 * 查询询比议价中未OA的单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.bargain.BargainServer#findBargainSource(java.lang.String)
	 */
	@Override
	public List findBargainSource(String source) {
		// TODO Auto-generated method stub
		String hql = "from Bargain where bargain_source='" + source
				+ "' and status='同意'";
		List list = this.totalDao.query(hql);
		return list;
	}

	@Override
	public PerformsingleDetail findPerformsingle1(String contractNum1) {
		PerformsingleDetail perdetail = null;
		String hql = "from Performsingle where purchase_number=? ";
		Performsingle performsingle = (Performsingle) this.totalDao
				.getObjectByCondition(hql, contractNum1);
		if (performsingle != null) {
			String hql1 = "from PerformsingleDetail where performsingle.id=? ";
			perdetail = (PerformsingleDetail) this.totalDao
					.getObjectByCondition(hql1, performsingle.getId());
		}
		return perdetail;
	}

	@Override
	public List findbargainSource6() {
		// TODO Auto-generated method stub
		return totalDao
				.query("from QuotedPrice where procardStyle='外购' and gongzhuangFei is not null and gongzhuangFei>0 and id not in(select pk_id from Bargain where bargain_source='外购工装')");
	}

	@Override
	public Bargain getPageBargain(Bargain bargain, String osaIds) {
		// TODO Auto-generated method stub
		if (bargain.getBargain_source() != null) {
			if (bargain.getBargain_source().equals("外购工装")) {
				QuotedPrice qp = (QuotedPrice) totalDao
						.getObjectByQuery(
								"from QuotedPrice where id=? and procardStyle='外购' and gongzhuangFei is not null and gongzhuangFei>0",
								Integer.parseInt(bargain.getBargain_num()));
				if (qp != null) {
					bargain.setBargain_num(qp.getMarkId());
					BargainGoods goods = new BargainGoods();
					goods.setGoods_name(qp.getProName());
					goods.setGoods_unit(qp.getUnit());
					BargainingDetails details = new BargainingDetails();
					details.setUnitprice(qp.getGongzhuangFei());
					Set<BargainGoods> bgSet = new HashSet<BargainGoods>();
					bgSet.add(goods);
					Set<BargainingDetails> detailsSet = new HashSet<BargainingDetails>();
					detailsSet.add(details);
					bargain.setBargainGoods(bgSet);
					bargain.setBargainingDetails(detailsSet);
				}
			} else if (bargain.getBargain_source().equals("零部件及工序外委采购")) {
				if (osaIds == null || osaIds.equals("")) {
					return null;
				} else {
					osaIds = osaIds.replaceAll(" ", "");
					String[] osaIdStrs = osaIds.split(",");
					if (osaIdStrs != null && osaIdStrs.length > 0) {
						Set<BargainGoods> bargainGoodSet = new HashSet<BargainGoods>();
						for (int i = 0; i < osaIdStrs.length; i++) {
							OutSourcingApp osa = (OutSourcingApp) totalDao
									.getObjectById(OutSourcingApp.class,
											Integer.parseInt(osaIdStrs[i]));
							if (osa != null) {
								BargainGoods goods = new BargainGoods();
								goods.setGoods_name(osa.getProcessNO());
								// if(osa.getProcessNO()!=null&&!osa.getProcessNO().equals("")&&!osa.getProcessNO().equals("外购")){
								// goods.setGoods_name(osa.getProcessNO());
								// }else{
								// goods.setGoods_name(osa.getPartName());
								// }
								goods.setGoods_format(osa.getMarkID());
								goods.setDataId(osa.getId());
								bargainGoodSet.add(goods);
							}
						}
						bargain.setBargainGoods(bargainGoodSet);
						return bargain;
					}
					return null;
				}

			} else if (bargain.getBargain_source().equals("原材料采购")) {

			} else if (bargain.getBargain_source().equals("紧急采购")) {

			} else if (bargain.getBargain_source().equals("行政事务采购")) {

			} else if (bargain.getBargain_source().equals("XBYJ")) {

			} else if (bargain.getBargain_source().equals("设备")) {

			} else if (bargain.getBargain_source().equals("KVP")) {

			} else if (bargain.getBargain_source().equals("SB")) {

			} else if (bargain.getBargain_source().equals("OA")) {

			}
		}
		return bargain;
	}

	/***
	 * 查询登录用户添加的合同信息
	 * 
	 * @return
	 */
	@Override
	public List getLoginHeTong() {
		Users loginUser = Util.getLoginUser();
		if (loginUser != null) {
//			String hql = "from BarContract where username=?";
//			return totalDao.query(hql, loginUser.getName());
			String hql = "from BarContract order by contract_num";
			return totalDao.query(hql);
		}
		return null;
	}

	@Override
	public List findOsaList() {
		// TODO Auto-generated method stub
		return totalDao
				.query("from OutSourcingApp where executeStatus='评审完成'"
						+ " and ((processNO = '外购' " +
//								"and markID not in (select goods_format from BargainGoods where bargain.status='同意' ) and markID not in (select goods_format from PerformsingleDetail where gx_status !='打回')" +
								")"
						+ " or (processNO !='外购' and processNO is not null and processNO not in ( select goods_name from BargainGoods where goods_format=markID and bargain.status='同意' ) and processNO not in (select goods_name from PerformsingleDetail where goods_format=markID and gx_status !='打回'))" 
								+") order by id desc");
	}

	@Override
	public List findBargainwgww() {
		// TODO Auto-generated method stub
		return totalDao
				.query("from BargainGoods b where b.bargain.status='同意' and  b.bargain.bargain_source='零部件及工序外委采购' and ( (ISNUMERIC(b.goods_name)>0 and b.goods_format is not null and b.goods_format !='' and b.goods_name not in (select p.goods_name from PerformsingleDetail p where p.goods_format=b.goods_format and p.gx_status !='打回'))"
						+ " or(ISNUMERIC(b.goods_name)=0 and b.goods_format is not null and b.goods_format !='' and b.goods_format not in(select p.goods_format from PerformsingleDetail p where p.gx_status !='打回'))) order by b.goods_format");
	}

	@Override
	public BarContract getbarContract(Integer id) {
		Performsingle pe=(Performsingle) totalDao.get(Performsingle.class, id);
		String hql="from BarContract where   contract_num1=?";
		String contract_num1=pe.getPurchase_number();
		return (BarContract) totalDao.getObjectByCondition(hql ,contract_num1 );
	}

}
