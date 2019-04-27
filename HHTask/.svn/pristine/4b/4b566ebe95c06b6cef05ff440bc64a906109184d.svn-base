package com.task.ServerImpl.caiwu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.caiwu.CarAllowServer;
import com.task.Server.caiwu.receivePayment.ReceiptServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Credentials;
import com.task.entity.Users;
import com.task.entity.caiwu.CaiwuRecharge;
import com.task.entity.caiwu.CarAllowOneApp;
import com.task.entity.caiwu.CarAllowSumApp;
import com.task.entity.caiwu.receivePayment.Receipt;
import com.task.util.Util;

public class CarAllowServerImpl implements CarAllowServer {
	private TotalDao totalDao;
	private ReceiptServer receiptServer;

	public ReceiptServer getReceiptServer() {
		return receiptServer;
	}

	public void setReceiptServer(ReceiptServer receiptServer) {
		this.receiptServer = receiptServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean deleteOneapp(Integer id) {

		CarAllowOneApp oneapp = (CarAllowOneApp) totalDao.getObjectById(
				CarAllowOneApp.class, id);
		return totalDao.delete(oneapp);
	}

	@Override
	public Object[] findOneAppList(CarAllowOneApp oneAllow, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = "from CarAllowOneApp order by chongzhTime desc";

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != oneAllow) {
			hql = totalDao
					.criteriaQueries(oneAllow, "chongzhTime", between, "")
					+ "  order by chongzhTime desc";
		}

		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	// 获取车牌信息
	public Credentials getPlatNumber(String code) {
		String hql = "from Credentials where cardtype='行驶证' and  code=?";
		Credentials c = (Credentials) totalDao.getObjectByQuery(hql, code);
		return c;

	}

	@Override
	public Object[] findRechargeList(CaiwuRecharge recharge, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag) {
		String hql = "from CaiwuRecharge order by operateTime desc";

		String[] between = new String[2];
		if (null != startDate && null != endDate && !"".equals(endDate)
				&& !"".equals(startDate)) {
			between[0] = startDate;
			between[1] = endDate;
		}
		if (null != recharge) {
			hql = totalDao
					.criteriaQueries(recharge, "operateTime", between, "")
					+ "  order by operateTime desc";
		}

		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	public boolean deletesumAllow(Integer id) {
		CarAllowSumApp app = (CarAllowSumApp) totalDao.getObjectById(
				CarAllowSumApp.class, id);
		String hql = "from CaiwuRecharge where sumID=" + id;
		List<CaiwuRecharge> l = totalDao.query(hql);
		if (null != l && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				CaiwuRecharge c = l.get(i);
				totalDao.delete(c);
			}
		}
		return totalDao.delete(app);
	}

	@Override
	public Object[] findsumAppList(CarAllowSumApp carAllowSumApp,
			Integer cpage, Integer pageSize, String tag) {
		String hql = "from CarAllowSumApp";

		String[] between = new String[2];

		if (null != carAllowSumApp) {
			hql = totalDao.criteriaQueries(carAllowSumApp, null)
					+ "  order by lastChongzhiMonth desc";
		}
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	public Object[] findoneappHistory(Integer id, Integer cpage,
			Integer pageSize) {
		// CarAllowSumApp a=getOneCarAllow(id);
		String hql = "from CarAllowOneApp where sumapp.id=" + id;
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	@Override
	/**
	 * 删除单条申请记录
	 */
	public boolean deleteOneHistory(Integer id) {
		CarAllowOneApp a = (CarAllowOneApp) totalDao.getObjectById(
				CarAllowOneApp.class, id);
		CarAllowSumApp s = a.getSumapp();
		float sum = 0;
		if (null != a.getRoadcost()) {
			s.setRoadcost(s.getRoadcost() - a.getRoadcost());
			sum += a.getRoadcost();
		}
		if (null != a.getParkcost()) {
			s.setParkcost(s.getParkcost() - a.getParkcost());
			sum += a.getParkcost();
		}
		if (null != a.getRepaircost()) {
			s.setRepaircost(s.getRepaircost() - a.getRepaircost());
			sum += a.getRepaircost();
		}
		if (null != a.getInsurancecost()) {
			s.setInsurancecost(s.getInsurancecost() - a.getInsurancecost());
			sum += a.getInsurancecost();
		}
		s.setSumchognzhi(s.getSumcost() - sum);
		s.setSumremainbaoxiao(s.getSumremainbaoxiao() - sum);
		totalDao.update(s);
		return totalDao.delete(a);

	}

	@Override
	public Object[] findChongzhiHistory(Integer id, Integer cpage,
			Integer pageSize, String tag) {
		// CarAllowSumApp a=getOneCarAllow(id);
		String hql = "from CaiwuRecharge where sumID=" + id;
		if ("cz".equals(tag)) {// 充值
			hql += " and isChongzhORbaoxiao='充值'";
		} else if ("bx".equals(tag)) {// 报销
			hql += " and isChongzhORbaoxiao='申领'";
		}
		Object[] procardAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		procardAarr[0] = count;
		procardAarr[1] = list;
		return procardAarr;
	}

	// 删除充值记录
	@Override
	public boolean deleteOneCZHistory(Integer id) {
		CaiwuRecharge c = (CaiwuRecharge) totalDao.getObjectById(
				CaiwuRecharge.class, id);
		CarAllowSumApp s = getOneCarAllow(c.getSumID());
		s.setSumchognzhi(s.getSumchognzhi() - c.getChongzhiJine());
		s.setSumremainbaoxiao(s.getSumremainbaoxiao() + c.getChongzhiJine());
		totalDao.update(s);
		return totalDao.delete(c);
	}

	// 设置限额
	@Override
	public boolean shezhiXiane(Integer id, CarAllowSumApp carAllowOne) {
		CarAllowSumApp a = getOneCarAllow(id);
		a.setChongzhiMax(carAllowOne.getChongzhiMax());
		a.setChognzhiedu(carAllowOne.getChognzhiedu());
		return totalDao.update(a);
	}

	@Override
	public CarAllowSumApp getOneCarAllow(Integer id) {
		// TODO Auto-generated method stub
		CarAllowSumApp sumapp = (CarAllowSumApp) totalDao.getObjectById(
				CarAllowSumApp.class, id);
		if (null != sumapp) {
			return sumapp;
		}
		return null;
	}

	@Override
	public List<CarAllowSumApp> findsumList() {
		String hql = "from CarAllowSumApp";
		List l = totalDao.query(hql);
		return l;
	}

	@Override
	public CarAllowSumApp getOneCarAllowByCode(String code) {
		// TODO Auto-generated method stub
		String hql = "from CarAllowSumApp where code=?";
		// List<CarAllowSumApp> list=totalDao.query(hql);
		CarAllowSumApp c = (CarAllowSumApp) totalDao.getObjectByCondition(hql,
				code);

		/*
		 * if(null!=list && list.size()>0){ if(null!=list.get(0)){
		 * CarAllowSumApp sumapp=(CarAllowSumApp)list.get(0); return sumapp; } }
		 */
		return c;
	}

	@Override
	public boolean saveCarAllow(CarAllowSumApp carAllowSumApp) {
		boolean boo = false;
		if (null != carAllowSumApp) {
			boo = totalDao.save(carAllowSumApp);
		}
		return boo;
	}

	@Override
	public boolean saveOneAllowAPP(Integer id, CarAllowOneApp oneAllow) {
		boolean boo = false;
		if (null != oneAllow) {
			CarAllowSumApp sumapp = getOneCarAllow(id);
			oneAllow.setSumapp(sumapp);// 设置外键关联
			// 设置数据汇总
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowTime = sdf.format(new Date());
			/*
			 * if(null!=sumapp.getOilcost()){
			 * sumapp.setOilcost(sumapp.getOilcost()+oneAllow.getOilcost());
			 * }else{ sumapp.setOilcost(oneAllow.getOilcost()); }
			 */
			if (null != sumapp.getRepaircost()) {
				sumapp.setRoadcost(sumapp.getRepaircost()
						+ oneAllow.getRoadcost());
			} else {
				sumapp.setRoadcost(oneAllow.getRoadcost());
			}
			if (null != sumapp.getParkcost()) {
				sumapp.setParkcost(sumapp.getParkcost()
						+ oneAllow.getParkcost());
			} else {
				sumapp.setParkcost(oneAllow.getParkcost());
			}
			if (null != sumapp.getRepaircost()) {
				sumapp.setRepaircost(sumapp.getRepaircost()
						+ oneAllow.getRepaircost());
			} else {
				sumapp.setRepaircost(oneAllow.getRepaircost());
			}
			if (null != sumapp.getInsurancecost()) {
				sumapp.setInsurancecost(sumapp.getInsurancecost()
						+ oneAllow.getInsurancecost());
			} else {
				sumapp.setInsurancecost(oneAllow.getInsurancecost());
			}

			float sum = oneAllow.getRoadcost() + oneAllow.getParkcost()
					+ oneAllow.getRepaircost() + oneAllow.getInsurancecost();
			if (null != sumapp.getSumcost()) {
				sumapp.setSumcost(sumapp.getSumcost() + sum);
			} else {
				sumapp.setSumcost(sum);
			}
			if (null != sumapp.getSumremainbaoxiao()) {
				sumapp.setSumremainbaoxiao(sumapp.getSumremainbaoxiao() + sum);
			} else {
				sumapp.setSumremainbaoxiao(sum);// 初始化
			}
			sumapp.setLastChongzhiMonth(nowTime);
			totalDao.update(sumapp);
			oneAllow.setChongzhTime(nowTime);
			oneAllow.setStatus("未审批");// 未审批/同意
			boo = totalDao.save(oneAllow);
			/****************** 调用审批流程 *****************/
			String processName = "车辆补贴申请流程";
			Integer epId = null;
			// DanganAction_toselect.action?dangAn.id=" + dangan.getId()
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						CarAllowOneApp.class, oneAllow.getId(), "status", "id",
						"DanganAction_toSelect.action?dangAn.id="
								+ oneAllow.getId(), oneAllow.getName()
								+ "的车补申请额 " + sum + " ，请您审批！", true);
				if (epId != null && epId > 0) {
					oneAllow.setEpId(epId);
					totalDao.update(oneAllow);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return boo;
	}

	@Override
	public boolean saveRecharge(CaiwuRecharge recharge) {
		boolean boo = false;
		if (null != recharge) {
			boo = totalDao.save(recharge);
		}
		return boo;
	}

	@Override
	public List chongzhi(Integer listId[], Float chongzhi[]) {
		List<CaiwuRecharge> list = new ArrayList();
		if (null != chongzhi) {
			for (int i = 0; i < chongzhi.length; i++) {
				float f = chongzhi[i];
				if (f > 0) {
					CarAllowSumApp a = getOneCarAllow(listId[i]);
					float sumc = 0;
					if (null != a.getSumchognzhi()) {
						sumc = a.getSumchognzhi();
					}
					;
					a.setSumchognzhi(sumc + f);// 充值累计
					float remainS = 0;

					if (null != a.getSumcost()) {
						remainS = a.getSumcost();
					}
					// 更新可申领金额
					// a.setSumremainbaoxiao(a.getSumcost()-sumc-f);
					a.setSumbaoxiao(a.getSumbaoxiao() - f);

					totalDao.update(a);
					CaiwuRecharge c = new CaiwuRecharge();
					c.setSumID(listId[i]);
					c.setCode(a.getCode());
					c.setName(a.getName());
					c.setPlatenumber(a.getPlatenumber());
					c.setChongzhiJine(f);
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					c.setOperateTime(sdf.format(new Date()));
					c.setIsChongzhORbaoxiao("充值");
					c.setBaoxiaoStatus("NO");
					totalDao.save(c);
					list.add(c);

					// NonCorePayable n = new NonCorePayable();// 非主营业务应付对象
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-");
					SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
					Users user = Util.getLoginUser();
					String resu = null;
					float jine = 0;
					if (c.getChongzhiJine() > 0) {
						jine = c.getChongzhiJine();

						// // 对接非主营业务应付
						// n.setSubjectItem("车辆补贴");
						// n.setZhaiyao("月度车辆使用补贴申领可申领余额" + a.getSumbaoxiao());
						// n.setShoukuandanwei(a.getName());
						// n.setHetongbianhao(a.getPlatenumber());
						// n.setZhangqiStartDate(sdf2.format(new Date()) +
						// "01");
						// n.setZhangqiEndDate(sdf2.format(new Date()) + "28");
						// n.setFukuaiDate(sdf2.format(new Date()) + "28");
						// n.setLateDate(sdf2.format(new Date()) + "28");
						// n.setSaveTime(sdf.format(new Date()));
						// n.setYingfukuanJIne(jine);
						// n.setRealfukuanJIne(0f);
						// n.setSaveUser(user.getName());
						// n.setSaveDept(user.getDept());
						// n.setSaveUserId(user.getId());
						// n.setStatus("未审批");
						// n.setFujian(c.getFujian());

						Receipt receipt = new Receipt();
						receipt.setPayee(a.getName());
						receipt.setPayeeId(null);
						receipt.setSummary("月度车辆使用补贴申领可申领余额"
								+ a.getSumbaoxiao());
						receipt.setPayType("费用报销");
						receipt.setAboutNum(a.getPlatenumber());
						receipt.setFk_monthlyBillsId(null);
						receipt.setAllMoney(jine);
						receipt.setPaymentCycle(0);
						receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
						receiptServer.addReceipt(receipt);

						// 审批流程
						// totalDao.save(n);
						// String processName = "非主营业务应付申请";
						// Integer epId = null;
						// try {
						// epId = CircuitRunServerImpl.createProcess(
						// processName, NonCorePayable.class, n
						// .getId(), "status", "id",
						// "NoncorePayableAction!toselect.action?corePayable.id="
						// + n.getId(), user.getDept() + "部门 "
						// + user.getName()
						// + " 非主营业务应付申请，请您审批！", true);
						// if (epId != null && epId > 0) {
						// n.setEpId(epId);
						// totalDao.update(n);
						// }
						// } catch (Exception e) {
						// e.printStackTrace();
						// }
					}
				}
			}
		}
		return list;
	}

	@Override
	// 补贴申领申请,
	public String savenutieApp(Integer id, CaiwuRecharge caiwuRecharge) {
		CarAllowSumApp a = getOneCarAllow(id);
		Users user = Util.getLoginUser();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");

//		NonCorePayable n = new NonCorePayable();// 非主营业务应付对象
		String resu = null;
		float jine = 0;
		if (caiwuRecharge.getChongzhiJine() > 0) {
			jine = caiwuRecharge.getChongzhiJine();

//			// 对接非主营业务应付
//			n.setSubjectItem("车辆补贴");
//			n.setZhaiyao("月度车辆使用补贴申领可申领余额" + a.getSumbaoxiao());
//			n.setShoukuandanwei(a.getName());
//			n.setHetongbianhao(a.getPlatenumber());
//			n.setZhangqiStartDate(sdf2.format(new Date()) + "01");
//			n.setZhangqiEndDate(sdf2.format(new Date()) + "28");
//			n.setFukuaiDate(sdf2.format(new Date()) + "28");
//			n.setLateDate(sdf2.format(new Date()) + "28");
//			n.setSaveTime(sdf.format(new Date()));
//			n.setYingfukuanJIne(jine);
//			n.setRealfukuanJIne(0f);
//			n.setSaveUser(user.getName());
//			n.setSaveDept(user.getDept());
//			n.setSaveUserId(user.getId());
//			n.setStatus("未审批");
//			n.setFujian(caiwuRecharge.getFujian());
//
//			// 审批流程
//			totalDao.save(n);
//			String processName = "非主营业务应付申请";
//			Integer epId = null;
//			try {
//				epId = CircuitRunServerImpl.createProcess(processName,
//						NonCorePayable.class, n.getId(), "status", "id",
//						"NoncorePayableAction!toselect.action?corePayable.id="
//								+ n.getId(), user.getDept() + "部门 "
//								+ user.getName() + " 非主营业务应付申请，请您审批！", true);
//				if (epId != null && epId > 0) {
//					n.setEpId(epId);
//					totalDao.update(n);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			Receipt receipt = new Receipt();
			receipt.setPayee(a.getName());
			receipt.setPayeeId(null);
			receipt.setSummary("月度车辆使用补贴申领可申领余额"
					+ a.getSumbaoxiao());
			receipt.setPayType("费用报销");
			receipt.setAboutNum(a.getPlatenumber());
			receipt.setFk_monthlyBillsId(null);
			receipt.setAllMoney(jine);
			receipt.setPaymentCycle(0);
			receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
			receiptServer.addReceipt(receipt);

			// 保存申领记录
			caiwuRecharge.setYingfuId(receipt.getId());
			caiwuRecharge.setSumID(id);
			caiwuRecharge.setIsChongzhORbaoxiao("申领");
			caiwuRecharge.setOperateTime(sdf.format(new Date()));

			totalDao.save(caiwuRecharge);
			// 更新可申领金额
			a.setSumbaoxiao(a.getSumbaoxiao() - jine);
			a.setSumremainbaoxiao(a.getSumremainbaoxiao()-jine);
			totalDao.update(a);
			resu = "申领成功";
		}

		return resu;

	}

	@Override
	public boolean updateCarAllow(CarAllowSumApp carAllowSumApp) {
		boolean boo = false;
		if (null != carAllowSumApp) {
			boo = totalDao.update(carAllowSumApp);
		}
		return boo;
	}

	@Override
	// 定时执行补贴更新操作，每月1日自动更新
	public boolean updateSum() {
		boolean b = false;
		String hql = "from CarAllowSumApp";
		List<CarAllowSumApp> l = totalDao.query(hql);
		if (null != l && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				CarAllowSumApp a = l.get(i);
				float edu = a.getChognzhiedu();// 当月额度
				float shenling = a.getSumbaoxiao();// 可领额度
				float yue = a.getSumremainbaoxiao();// 申请余额(待用)/可充值金额
				float addShenlingjine = 0;

				if (yue < edu) {// 申请票额不足
					addShenlingjine = yue;
					shenling += yue;
					yue = 0;
				} else {
					addShenlingjine = edu;
					shenling += edu;
					yue -= edu;
				}
				a.setSumremainbaoxiao(yue);
				a.setSumbaoxiao(shenling);
				String code = a.getCode();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String month = sdf.format(new Date());
				int sumId = a.getId();
				String hql2 = "from CaiwuRecharge where sumID=? and operateTime=?";
				List listC = totalDao.query(hql2, sumId, month);
				if (null == listC || listC.size() < 1) {
					b = totalDao.update(a);
				}

			}
		}
		return b;
	}
}
