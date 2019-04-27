package com.task.ServerImpl.perform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.perform.PerformsingleServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Machine;
import com.task.entity.Maintenance;
import com.task.entity.OaAppDetail;
import com.task.entity.Users;
import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainGoods;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.bargain.Company;
import com.task.entity.kvp.ExecuteKVP;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.sop.OutSourcingApp;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhaobiaoXi;
import com.tast.entity.zhaobiao.Zhmoban;

public class PerformsingleServerImpl implements PerformsingleServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/*
	 * 查询OA单号(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource1(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List findbargainSource1(String bargainNum) {
		String hql = "from OaAppDetail where detailChildClass='工装' and  detailStatus in('同意','采购中') and detailSeqNum in ("
				+ Util.szhuans(bargainNum) + ")";
		List<OaAppDetail> list = this.totalDao.query(hql);
		for (OaAppDetail oaAppDetail : list) {
			String hql_Bargain = "from Bargain where bargain_num=?";
			Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(
					hql_Bargain, oaAppDetail.getDetailSeqNum());
			if (bargain != null) {
				String hql1 = "from BargainingDetails where bargain.id=?";
				List<BargainingDetails> listDetails = this.totalDao.query(hql1,
						bargain.getId());

				List listP = new ArrayList();
				Float money = 0F;
				for (int i = 0; i < listDetails.size(); i++) {
					// 找到议价明细
					BargainingDetails bargainingDetails = listDetails.get(i);
					// 从议价找到公司
					Company company = bargainingDetails.getCompany();
					if ("是".equals(company.getSelected_status())) {
						String hql2 = "from BargainingDetails where company.id=? and unitprice is not null order by unitprice ";
						BargainingDetails bargainingDetails2 = (BargainingDetails) totalDao
								.getObjectByCondition(hql2, company.getId());
						money = bargainingDetails2.getUnitprice();
						oaAppDetail.setDetailBudgetMoney(money);
						break;
					} else {
						break;
					}
				}
			}
		}

		return list;
	}

	/*
	 * 根据设备单号查询设备(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource2(java.lang
	 * .String)
	 */
	@Override
	public Object[] findbargainSource2(String bargainNum) {
		// Object[] o = null;
		// String hql = "from Maintenance where barcode=?";
		// List list = this.totalDao.query(hql, bargainNum);
		// o = new Object[]{list};
		// return o;
		Object[] o = null;
		String hql = "from Bargain where bargain_num = ?";
		Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(hql,
				bargainNum);
		if (bargain != null) {
			String hql1 = "from BargainGoods where bargain.id=?";
			List list = this.totalDao.query(hql1, bargain.getId());
			o = new Object[] { list };
		}
		return o;
	}

	/*
	 * 根据kvp编号查询kvp(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource3(java.lang
	 * .String)
	 */
	@Override
	public Object[] findbargainSource3(String bargainNum) {
		Object[] o = null;
		String hql = "from ExecuteKVP where executeNumber in ("
				+ Util.szhuans(bargainNum) + ")";
		List list = this.totalDao.query(hql);
		o = new Object[] { list };
		return o;
	}

	/*
	 * 根据议价单号查询询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource4(java.lang
	 * .String)
	 */
	@Override
	public Object[] findbargainSource4(String bargainNum) {
		Object[] o = null;
		String hql = "from Bargain where bargain_number=?";
		Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(hql,
				bargainNum);
		String hql1 = "from BargainingDetails where bargain.id=?";
		List<BargainingDetails> listDetails = this.totalDao.query(hql1, bargain
				.getId());
		// 找到议价物品
		String hql3 = "from BargainGoods where bargain.id=?";
		List<BargainGoods> listGoods = this.totalDao.query(hql3, bargain
				.getId());
		List listP = new ArrayList();
		Float money = 0F;
		for (int i = 0; i < listDetails.size(); i++) {
			// 找到议价明细
			BargainingDetails bargainingDetails = listDetails.get(i);
			// 从议价找到公司
			Company company = bargainingDetails.getCompany();
			if ("是".equals(company.getSelected_status())) {
				String hql2 = "from BargainingDetails where company.id=? and unitprice is not null order by unitprice";
				BargainingDetails bargainingDetails2 = (BargainingDetails) totalDao
						.getObjectByCondition(hql2, company.getId());
				money = bargainingDetails2.getUnitprice();
				break;
			} else {
				break;
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
	 * 添加采购执行单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#addPerformsingle(com.task
	 * .entity.perform.Performsingle, java.util.List)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addPerformsingle(Performsingle performsingle,
			List<PerformsingleDetail> performsingleDetails,
			List<OutSourcingApp> list) {
		boolean bool = false;
		Users loginUser = Util.getLoginUser();// 登陆用户信息
		String createdate1 = Util.getDateTime("yyyy-MM");
		String[] a = createdate1.split("-");
		createdate1 = a[0] + a[1];

		Set<PerformsingleDetail> BarSet = new HashSet<PerformsingleDetail>();
		// 明细
		for (int i = 0; i < performsingleDetails.size(); i++) {
			PerformsingleDetail performsingleDetail = performsingleDetails
					.get(i);

			if (performsingle.getPurchase_category() != null) {
				if ("OA".equals(performsingle.getPurchase_category())
						|| "SB".equals(performsingle.getPurchase_category())) {
					// 得到项目
					QuotedPrice quotedPrice = getQuotedPrice(performsingle);
					if (quotedPrice != null) {
						performsingleDetail.setQuotedPrice_id(quotedPrice
								.getId());
					}
					// 得到询比议价
					Bargain bargain = findbargain(performsingle);
					if (bargain != null) {
						performsingleDetail.setBargain_id(bargain.getId());
					}
				}
				if ("KVP".equals(performsingle.getPurchase_category())) {
					// 获取kvp
					ExecuteKVP executeKVP = getExecuteKVP(performsingle);
					System.out.println("======>"
							+ executeKVP.getKvpAssess().getId());
					if (executeKVP != null) {
						performsingleDetail.setKvp_id(executeKVP.getKvpAssess()
								.getId());
					}
				}
				if ("原材料采购".equals(performsingle.getPurchase_category())
						|| "包装物".equals(performsingle.getPurchase_category())) {
					// 获取招标
					ZhaobiaoXi zhaobiaoXi = getZhaobiaoXi(performsingle);
					if (zhaobiaoXi != null) {
						performsingleDetail.setZhaobiao_id(zhaobiaoXi.getT10());
					}
				}
				if ("设备".equals(performsingle.getPurchase_category())) {
					// 获取设备
					Machine machine = getMachine(performsingle);
					if (machine != null) {
						performsingleDetail.setMachine_id(machine.getId());
					}
				}
				if ("设备维修".equals(performsingle.getPurchase_category())) {
					// 获取设备
					Machine machine = getMachine(performsingle);
					if (machine != null) {
						performsingleDetail.setMachine_id(machine.getId());
					}
				}
				if("零部件及工序外委采购".equals(performsingle.getPurchase_category())){
					String before = "wgww"+Util.getDateTime("yyyyMM")+"_";
					Integer maxNo=0;
					String maxnumber=(String) totalDao.getObjectByCondition("select max(purchase_number) from Performsingle where purchase_number like '"+before+"%'");
				    if(maxnumber!=null){
				    	maxNo = Integer.parseInt(maxnumber.split("_")[1])+1;
				    }
				    performsingle.setPurchase_number(before+maxNo);
				}
			}
			BarSet.add(performsingleDetail);
			performsingle.setSetperformsingleDetails(BarSet);
		}

		for (OutSourcingApp i : list) {
			OutSourcingApp outSourcingApp2 = (OutSourcingApp) totalDao
					.getObjectById(OutSourcingApp.class, i.getId());
			outSourcingApp2.setPer_status("已申请");
		}
		performsingle.setPurchase_dept(loginUser.getDept());
		performsingle.setPurchase_date(Util.getDateTime("yyyy-MM-dd"));
		performsingle.setStatus("未审核");
		performsingle.setPurchaser(loginUser.getName());
		bool = this.totalDao.save(performsingle);
		if (bool) {
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess("采购执行单审核",
						Performsingle.class, performsingle.getId(), "status",
						"id",
						"performsingleAction_salPerformsingle.action?performsingle.id="
								+ performsingle.getId(), loginUser.getName()
								+ "提交的采购执行单,请您审核!", true);
				performsingle.setEpId(epId);
				this.totalDao.update(performsingle);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bool;
	}

	// 获取设备信息
	private Machine getMachine(Performsingle performsingle) {

		if (performsingle != null) {
			String hql = "from Machine where no=?";
			Machine machine = (Machine) this.totalDao.getObjectByCondition(hql,
					performsingle.getPurchase_number());
			return machine;
		} else {
			return null;
		}
	}

	// 获取招标
	private ZhaobiaoXi getZhaobiaoXi(Performsingle performsingle) {
		String hql = "from ZhaobiaoXi where zhmoban.id=(select id from Zhmoban where name=?)";
		ZhaobiaoXi zhaobiaoXi = (ZhaobiaoXi) this.totalDao
				.getObjectByCondition(hql, performsingle.getPurchase_number());
		return zhaobiaoXi;
	}

	// 获取kvp
	private ExecuteKVP getExecuteKVP(Performsingle performsingle) {
		// TODO Auto-generated method stub
		String hql = "from ExecuteKVP where executeNumber=?";
		ExecuteKVP executeKVP = (ExecuteKVP) this.totalDao
				.getObjectByCondition(hql, performsingle.getPurchase_number());
		return executeKVP;
	}
	
	/*
	 * 查询采购申请单(non-Javadoc)(已生成合同的)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findPerformsingle(com.task
	 * .entity.perform.Performsingle, int, int)
	 */
	@Override
	public Object[] findPerformsingle(Performsingle performsingle,
			int parseInt, int pageSize, String tag) {
		Object[] o = null;
		if (performsingle == null) {
			performsingle = new Performsingle();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(performsingle, null);

		if (performsingle.getPurchase_name() != null
				&& !"".equals(performsingle.getPurchase_name())) {
			hql += " and purchase_name like '%"
					+ performsingle.getPurchase_name() + "%' ";
		}
		if (performsingle.getPurchase_number() != null
				&& !"".equals(performsingle.getPurchase_number())) {
			hql += " and purchase_number like '%"
					+ performsingle.getPurchase_number() + "%' ";
		}
		if (performsingle.getPurchase_dept() != null
				&& !"".equals(performsingle.getPurchase_dept())) {
			hql += " and purchase_dept='" + performsingle.getPurchase_dept()
					+ "'";
		}
		if (tag != null && !"".equals(tag)) {
			hql += " and purchaser='" + loginUser.getName() + "'";
		}
		hql += " and ht_status = '已生成'  order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		//String hql1 = "from BarContract";
		//List<BarContract> list2 = this.totalDao.query(hql1, null);
		//List list3 = new ArrayList();
//		for (int i = 0; i < list.size(); i++) {
//			for (BarContract b : list2) {
//				Performsingle p = (Performsingle) list.get(i);
//				if (p.getPurchase_category() != null
//						&& b.getContract_source() != null
//						&& p.getPurchase_number() != null
//						&& b.getContract_num1() != null
//						&& p.getPurchase_category().equals(
//								b.getContract_source())
//						&& p.getPurchase_number().equals(b.getContract_num1())) {
//					list3.add(p);
//				}
//			}
//		}
	//	list.removeAll(list3);
		// int count = totalDao.getCount(hql);
		int count = list.size();
		o = new Object[] { list, count };
		return o;
	}
	
	
	/*
	 * 
	 * 删除采购执行单(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#delPerformsingle(com.task
	 * .entity.perform.Performsingle)
	 */
	@Override
	public boolean delPerformsingle(Performsingle performsingle,
			List<OutSourcingApp> list) {
		// TODO Auto-generated method stub
		boolean b = false;
		Performsingle performsingle2 = (Performsingle) this.totalDao
				.getObjectById(Performsingle.class, performsingle.getId());
		if (performsingle2 != null && performsingle2.getEpId() != null) {
			CircuitRun circuitRun = (CircuitRun) this.totalDao.getObjectById(
					CircuitRun.class, performsingle2.getEpId());
			if (circuitRun != null) {

				b = this.totalDao.delete(circuitRun);
			}
			if (list != null) {
				for (OutSourcingApp i : list) {
					OutSourcingApp outSourcingApp2 = (OutSourcingApp) totalDao
							.getObjectById(OutSourcingApp.class, i.getId());
					outSourcingApp2.setPer_status(null);
				}
			}
			b = this.totalDao.delete(performsingle2);
		}
		return b;
	}

	/*
	 * 根据单号查询OA(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#salPerformsingle(com.task
	 * .entity.perform.Performsingle)
	 */
	@Override
	public Object[] salPerformsingle(Performsingle performsingle) {
		Object[] o = null;
		Performsingle performsingle2 = (Performsingle) this.totalDao
				.getObjectById(Performsingle.class, performsingle.getId());
		String hql = "from PerformsingleDetail where performsingle.id=?";
		List list = this.totalDao.query(hql, performsingle2.getId());
		o = new Object[] { list, performsingle2 };
		return o;
	}

	/*
	 * 采购执行单审批(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findContractExamList(int,
	 * int)
	 */
	@Override
	public Object[] findContractExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Performsingle.class, false);
		if (map != null) {
			String hql = "from Performsingle where id in (:entityId)";
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
	 * 采购执行单审批(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#updateContractExamList(java
	 * .lang.Integer[], java.lang.String)
	 */
	@Override
	public boolean updateContractExamList(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				Performsingle detail = (Performsingle) totalDao.getObjectById(
						Performsingle.class, detailSelect[i]);
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
	 * 获取项目对象(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#getQuotedPrice(com.task.entity
	 * .perform.Performsingle)
	 */
	@Override
	public QuotedPrice getQuotedPrice(Performsingle performsingle) {
		List<QuotedProcessInfor> qpInforList = null;
		String hql = "from OaAppDetail where detailSeqNum=? ";
		OaAppDetail oaAppDetail = (OaAppDetail) this.totalDao
				.getObjectByCondition(hql, performsingle.getPurchase_number());
		if (oaAppDetail != null) {
			String hql1 = "from QuotedProcessInfor where oldgongzhuangNumber=? and  quotedPrice.id in (select id from QuotedPrice where "
					+ "proId in(select id from ProjectManage where projectNum=?)) order by id desc";
			// in(select id from ProjectManage where projectNum=?)
			if (oaAppDetail.getDetailItemId() != null
					&& !"".equals(oaAppDetail.getDetailItemId())) {
				// ProjectManage projectManage = (ProjectManage)
				// this.totalDao.getObjectById(ProjectManage.class, id)
				qpInforList = totalDao.query(hql1, oaAppDetail
						.getDetailFormat(), oaAppDetail.getDetailItemId());
			}
			if (qpInforList != null && qpInforList.size() > 0) {
				return qpInforList.get(0).getQuotedPrice();
			} else {
				return null;
			}
		}
		return null;
	}

	/*
	 * 获取询比议价(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargain(com.task.entity
	 * .perform.Performsingle)
	 */
	@Override
	public Bargain findbargain(Performsingle performsingle) {
		// Performsingle performsingle2 = (Performsingle)
		// this.totalDao.getObjectById(Performsingle.class,
		// performsingle.getId());
		String hql = "from Bargain where bargain_num=?";
		Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(hql,
				performsingle.getPurchase_number());
		return bargain;
	}

	/*
	 * 获取设备的项目(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#getQuotedPrice1(com.task.
	 * entity.perform.Performsingle)
	 */
	@Override
	public QuotedPrice getQuotedPrice1(Performsingle performsingle) {
		if (performsingle != null) {
			// 拿到采购执行的单号
			String number = performsingle.getPurchase_number();
			// 通过单号查询设备编号
			String hql1 = "from Maintenance where barcode=?";
			Maintenance maintenance = (Maintenance) this.totalDao
					.getObjectByCondition(hql1, number);
			// 通多单号查询工序的的链接
			String hql = "from QuotedProcessInfor where shebeiNo=? order by id desc";
			QuotedProcessInfor processInfor = (QuotedProcessInfor) this.totalDao
					.getObjectByCondition(hql, maintenance.getNo());
			if (processInfor != null) {
				return processInfor.getQuotedPrice();
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/*
	 * 根据外委申请编号查询外购外委评审(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource5(java.lang
	 * .String)
	 */
	@Override
	public Object[] findbargainSource5(String bargainNum) {
		Object[] o = null;
		// String hql =
		// "from QuotedPrice where id in(select rootId from OutSourcingApp where markID='"+gxNumber+"'  and  executeStatus='评审完成')";
		// List list = this.totalDao.query(hql);

		// String hql
		// ="from QuotedPrice where id in(select rootId from OutSourcingApp where osaNO=? and executeStatus='评审完成')";
		// List list = this.totalDao.query(hql, bargainNum);
		String hql = "from OutSourcingApp where osaNO in ("
				+ Util.szhuans(bargainNum) + ") and executeStatus='评审完成'";
		List list = this.totalDao.query(hql);
		o = new Object[] { list };
		return o;
	}

	/*
	 * 查询原材料的明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource6(java.lang
	 * .String)
	 */
	@Override
	public List findbargainSource6(String bargainNum) {
		// TODO Auto-generated method stub
		String hql = "from Zhmoban where name=?";
		Zhmoban zhmoban = (Zhmoban) this.totalDao.getObjectByCondition(hql,
				bargainNum);
		if (zhmoban != null) {
			String hql1 = "from ZhaobiaoXi where zhmoban.id=?";
			List list = this.totalDao.query(hql1, zhmoban.getId());
			return list;
		}
		return null;
	}

	/*
	 * 查看设备明细(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findbargainSource7(java.lang
	 * .String)
	 */
	@Override
	public List findbargainSource7(String bargainNum) {
		String hql = "from Machine where no=?";
		List list = this.totalDao.query(hql, bargainNum);
		return list;
	}

	/*
	 * 查询明细价格(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.perform.PerformsingleServer#findBargainprice(java.lang
	 * .String)
	 */
	@Override
	public BargainingDetails findBargainprice(String bargainNum) {
		BargainingDetails details = null;
		String hql = "from Bargain where bargain_num = ?";
		Bargain bargain = (Bargain) this.totalDao.getObjectByCondition(hql,
				bargainNum);
		if (bargain != null) {
			String hql1 = "from BargainingDetails where company.id=( select id from Company where id in (select company.id from BargainingDetails where bargain.id=? ) and selected_status='是') and unitprice is not null order by numbers desc";
			List list = this.totalDao.query(hql1, bargain.getId());
			if (list.size() > 0 && list != null) {
				details = (BargainingDetails) list.get(0);
			} else {
				return null;
			}
		}
		return details;
	}

	@Override
	public List findDGoodsListByIds(String ids) {
		// TODO Auto-generated method stub
		if(ids==null||ids.equals("")){
			return null;
		}else{
			List list=new ArrayList();
			ids=ids.replaceAll(" ", "");
			String [] idStrs=ids.split(",");
			if(idStrs!=null&&idStrs.length>0){
				Set<BargainGoods> bargainGoodSet = new HashSet<BargainGoods>();
				for(int i=0;i<idStrs.length;i++){
					BargainGoods bGoods=(BargainGoods) totalDao.getObjectById(BargainGoods.class, Integer.parseInt(idStrs[i]));
				    Float price = (Float) totalDao.getObjectByCondition("select unitprice from BargainingDetails where unitprice is not null and unitprice>0" +
				    		" and company.id in(select id from Company where selected_status='是')"+
				    		" and  bargain.id in(select bargain.id from BargainGoods where id=?) order by unitprice", bGoods.getId());
					bGoods.setPrice(price);
				    list.add(bGoods);
				}
				return list;
			}
			return null;
		}
	}

	@Override
	public List<Performsingle> findPerformsingle1(String tag) {
		String hql = "from Performsingle where ht_status is null ";
		Users user=	Util.getLoginUser();
		if("person".equals(tag)){
			hql +=" and purchaser='"+user.getName()+"'";
		}
		return totalDao.find(hql);
	}
}
