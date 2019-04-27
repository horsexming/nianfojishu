package com.task.ServerImpl.fin.budget;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import com.task.Dao.TotalDao;
import com.task.Server.fin.budget.SaleBudgetServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.bargain.Bargain;
import com.task.entity.fin.budget.SaleBudget;
import com.task.entity.fin.budget.SaleBudgetDetail;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.fin.budget.SubMonthMoney;
import com.task.entity.singlecar.SingleCar;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

/**
 * 市场销售收入serverImpl
 * 
 * @author jhh
 * 
 */
public class SaleBudgetServerImpl implements SaleBudgetServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Object[] SaveplanMonth() {
		Users user = Util.getLoginUser();
		SimpleDateFormat sdfD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
		String mm = sdf.format(new Date());
		String yy = sdfy.format(new Date());
		int yyint = Integer.parseInt(yy);
		int mint = Integer.parseInt(mm);
		if (mint == 12) {
			mint = 1;
			yyint++;
		} else {
			mint++;
		}
		String monS = String.valueOf(mint);
		if (monS.length() < 2) {
			monS = "0" + monS;
		}
		String plm = yyint + "-" + monS;
		String hql = " from SaleBudget where planMonth='" + plm + "'";
		List list = totalDao.find(hql);
		String tag = "no";// 是否存在计划
		Object[] prearr = new Object[3];
		if (list.size() > 0 && null != list.get(0)) {// 存在记录
			SaleBudget sbt = (SaleBudget) list.get(0);
			tag = "yes";
			prearr[0] = sbt;
		} else {// 不存在需要申报的记录
			SaleBudget sbt = new SaleBudget();
			sbt.setPlanMonth(plm);
			sbt.setInputTime(totalDao.getDateTime("yyyy-MM-dd HH:mm:ss"));
			sbt.setStatus("申报");
			sbt.setUserName(user.getName());
			sbt.setBarcode("sbt" + totalDao.getDateTime("yyyyMMdd") + "01");
			totalDao.save(sbt);
			prearr[0] = sbt;
		}

		prearr[1] = plm;
		prearr[2] = tag;
		return prearr;
	}

	/** 查找销售收入预算信息 ***/
	public Object[] findSaleBudget(SaleBudget saleBudget, Integer cpage,
			Integer pageSize, String tag) {
		String hql = "from SaleBudget where 1=1";
		if (null != saleBudget) {
			if (null != saleBudget.getPlanMonth()
					&& !"".equals(saleBudget.getPlanMonth())) {
				hql += " and planMonth='" + saleBudget.getPlanMonth() + "'";
			}
			if (null != saleBudget.getStatus()
					&& !"".equals(saleBudget.getStatus())) {
				hql += " and status='" + saleBudget.getStatus() + "'";
			}
			if (null != saleBudget.getBarcode()
					&& !"".equals(saleBudget.getBarcode())) {
				hql += " and barcode='" + saleBudget.getBarcode() + "'";
			}
		}
		hql += " order by planMonth desc";
		Object[] budgetAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		budgetAarr[0] = count;
		budgetAarr[1] = list;
		return budgetAarr;
	}

	@Override
	public String findbudgetStyle(String tag) {
		String message = "";
		if (null != tag && !"".equals(tag)) {
			String hql = "";
			if ("planMonth".equals(tag)) {
				hql = "select distinct(planMonth) from SaleBudget";
			} else if ("status".equals(tag)) {
				hql = "select distinct(status) from SaleBudget";
			} else if ("detailMarkId".equals(tag)) {
				hql = "select distinct(markID) from SaleBudgetDetail";
			}
			List<String> list = totalDao.query(hql);
			for (String d : list) {
				message += d.toString() + "|";
			}
		}
		return message;
	}

	@Override
	public Object[] findSaleBudgetProduct(Price price, Integer cpage,
			Integer pageSize, Integer id) {
		String hql = "from Price where produceType='销售' and productCategory='总成' ";
		String time = Util.getDateTime("yyyy-MM-dd");
//		hql += " and  '"
//				+ time
//				+ "'>= pricePeriodStart and ('"
//				+ time
//				+ "' <= pricePeriodEnd or pricePeriodEnd = '' or pricePeriodEnd is null) ";
		if (null != price) {
			if (null != price.getPartNumber()
					&& !"".equals(price.getPartNumber())) {
				hql += " and partNumber like '%" + price.getPartNumber() + "%'";
			}
			if (null != price.getName() && !"".equals(price.getName())) {
				hql += " and name='" + price.getName() + "'";
			}
			if (null != price.getType() && !"".equals(price.getType())) {
				hql += " and type='" + price.getType() + "'";
			}
		}
		SaleBudget sbt = (SaleBudget) totalDao.getObjectById(SaleBudget.class,
				id);
		hql += " and partNumber not in(select markID from SaleBudgetDetail where saleBudget.id='"
				+ sbt.getId() + "')";
		hql += " order by type desc";
		Object[] prieceAarr = new Object[2];
		Integer count = totalDao.getCount(hql);
		List list = totalDao.findAllByPage(hql, cpage, pageSize);
		prieceAarr[0] = count;
		prieceAarr[1] = list;
		return prieceAarr;
	}

	/**
	 * 根据预算ID查找明细
	 * 
	 * @param id
	 * @return
	 */
	public List findBudgetDetailBysbt(SaleBudgetDetail sbdetail, Integer id) {
		String hql = " from SaleBudgetDetail where saleBudget.id='" + id + "'";
		if (null != sbdetail) {
			if (null != sbdetail.getMarkID()
					&& !"".equals(sbdetail.getMarkID())) {
				hql += "and markID like'%" + sbdetail.getMarkID() + "%'";
			}
			if (null != sbdetail.getCustomer()
					&& !"".equals(sbdetail.getCustomer())) {
				hql += "and customer like'%" + sbdetail.getCustomer() + "%'";
			}
			if (null != sbdetail.getGoodsName()
					&& !"".equals(sbdetail.getGoodsName())) {
				hql += "and goodsName like'%" + sbdetail.getGoodsName() + "%'";
			}
		}
		return totalDao.find(hql);
	}

	@Override
	public SaleBudget getSaleBudgetById(Integer id) {
		// TODO Auto-generated method stub
		return (SaleBudget) totalDao.getObjectById(SaleBudget.class, id);
	}

	public Object[] querySelectedProduct(Integer[] priceSelect) {
		List list = new ArrayList();
		List error = new ArrayList();
		String errorStr = null;
		for (int i = 0; i < priceSelect.length; i++) {
			Price price = (Price) totalDao.getObjectById(Price.class,
					priceSelect[i]);
			if (price != null) {
				list.add(price);
			} else {
				error.add("产品：" + price.getName() + ",件号："
						+ price.getPartNumber() + ",出现问题！;" + "\\n");
			}
		}
		if (error != null && error.size() > 0) {
			errorStr = error.toString();
			errorStr = errorStr.substring(errorStr.indexOf("[") + 1).substring(
					0, errorStr.indexOf("]") - 1);
		}
		return new Object[] { list, errorStr };
	}

	@Override
	public boolean saveDetail(Integer[] priceSelect, Float[] count,
			String[] isInclud, Integer id) {
		boolean bool = false;
		if (null != priceSelect) {
			SaleBudget sbt = getSaleBudgetById(id);
			float budgetM = 0f;
			if (null != sbt.getSaleMoney()) {
				budgetM = sbt.getSaleMoney();
			}
			// 遍历处理预算明细
			for (int i = 0; i < priceSelect.length; i++) {
				Price p = (Price) totalDao.getObjectById(Price.class,
						priceSelect[i]);
				String isIncludeTag = isInclud[i];// 是否计入预算
				float cou = count[i];
				float f = Float.parseFloat(p.getBhsPrice().toString());// 单价
				// 重复累加处理
				String hql = "from SaleBudgetDetail where markID='"
						+ p.getPartNumber() + "' and goodsName='" + p.getName()
						+ "' and saleBudget.id=" + id;
				List<SaleBudgetDetail> listD = totalDao.find(hql);
				if (listD.size() > 0) {
					SaleBudgetDetail sbd2 = listD.get(0);
					if (null != sbd2.getForecastCount()) {
						sbd2.setForecastCount(sbd2.getForecastCount() + cou);
					}
					if (null != sbd2.getSaleReven()) {
						sbd2.setSaleReven(sbd2.getSaleReven() + cou * f);
					}
					totalDao.update(sbd2);
				} else {
					SaleBudgetDetail sbd3 = new SaleBudgetDetail();
					sbd3.setCustomer(p.getType());
					sbd3.setMarkID(p.getPartNumber());
					sbd3.setGoodsName(p.getName());
					sbd3.setForecastCount(cou);
					sbd3.setSaleReven(cou * f);
					sbd3.setOnePrice(f);
					sbd3.setSaleBudget(sbt);
					sbd3.setIsIncludBudget(isIncludeTag);
					totalDao.save(sbd3);
				}
				if ("no".equals(isIncludeTag)) {

				} else {
					budgetM += cou * f;
				}

			}
			sbt.setSaleMoney(budgetM);
			bool = totalDao.update(sbt);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteDetailById(SaleBudgetDetail sbd) {
		// TODO Auto-generated method stub
		SaleBudget sbt = sbd.getSaleBudget();
		sbd.setSaleBudget(null);
		totalDao.delete(sbd);
		updateZE(sbt.getId());
		return true;
	}

	// 根据ID更新销售总额
	private boolean updateZE(Integer id) {
		SaleBudget sbt = getSaleBudgetById(id);
		String hql = "select sum(saleReven) from SaleBudgetDetail sd where sd.saleBudget.id=? and isIncludBudget!='no'";
		List list = totalDao.query(hql, id);
		if (list != null && list.size() > 0) {
			Float ze = (Float) list.get(0);
			sbt.setSaleMoney(ze);
			return totalDao.update(sbt);
		}
		return false;
	}

	@Override
	public SaleBudgetDetail getDetailById(Integer id, String tag) {
		// TODO Auto-generated method stub
		return (SaleBudgetDetail) totalDao.getObjectById(
				SaleBudgetDetail.class, id);
	}

	@Override
	public boolean updateDetailById(SaleBudgetDetail sbd) {
		// TODO Auto-generated method stub
		SaleBudgetDetail oldsbd = (SaleBudgetDetail) totalDao.getObjectById(
				SaleBudgetDetail.class, sbd.getId());
		oldsbd.setForecastCount(sbd.getForecastCount());
		oldsbd.setSaleReven(oldsbd.getOnePrice() * sbd.getForecastCount());
		boolean bool = updateZE(oldsbd.getSaleBudget().getId());
		bool = totalDao.update(oldsbd);
		return bool;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean subSaleBudget(SaleBudget saleBudget) {
		if (saleBudget != null) {
			// 查询一级科目总比例
			String hql2 = "select sum(subjectRate) from SubBudgetRate where belongLayer=1";
			List sumList = totalDao.query(hql2);
			if (sumList != null && sumList.size() > 0) {
				// 查询一级科目
				String hql = "from SubBudgetRate where belongLayer=1";
				List<SubBudgetRate> subList = totalDao.query(hql);

				// 生成跟目录
				Float sumSubjectRate = (Float) sumList.get(0);
				String hql3 = "from SubMonthMoney where budgetMonth=? and belongLayer=0";
				SubMonthMoney subMonthMoney = (SubMonthMoney) totalDao
						.getObjectByCondition(hql3, saleBudget.getPlanMonth());
				if (subMonthMoney == null) {
					subMonthMoney = new SubMonthMoney();
				}
				subMonthMoney.setName(saleBudget.getPlanMonth() + "月度科目预算");
				subMonthMoney.setBudgetMonth(saleBudget.getPlanMonth());// 月份
				subMonthMoney.setSubjectRate(sumSubjectRate);// 总比例
				subMonthMoney.setMonthRealMoney(0F);
				subMonthMoney.setMonthBudgetMoney(saleBudget.getSaleMoney()
						* sumSubjectRate / 100);
				subMonthMoney.setBelongLayer(0);
				if (subMonthMoney.getId() == null) {
					totalDao.save(subMonthMoney);
					subMonthMoney.setRootId(subMonthMoney.getId());
				} else {
					totalDao.update(subMonthMoney);
				}

				try {
					// 生成下层
					addsubMonthMoney(subList, saleBudget, subMonthMoney);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		}
		return false;
	}

	/***
	 * 递归生成月度科目预算总金额
	 * 
	 * @param subList
	 *            科目信息
	 * @param saleBudget
	 *            销售总额信息
	 * @param smMoney
	 *            科目预算总金额
	 */
	private void addsubMonthMoney(List<SubBudgetRate> subList,
			SaleBudget saleBudget, SubMonthMoney smMoney) {
		if (subList != null) {
			for (SubBudgetRate subBudgetRate : subList) {
				String hql = "from SubMonthMoney where budgetMonth=? and sbRateId=?";
				SubMonthMoney subMonthMoney = (SubMonthMoney) totalDao
						.getObjectByCondition(hql, saleBudget.getPlanMonth(),
								subBudgetRate.getId());
				if (subMonthMoney == null) {
					subMonthMoney = new SubMonthMoney();
				}

				BeanUtils.copyProperties(subBudgetRate, subMonthMoney,
						new String[] { "id" });

				subMonthMoney.setBudgetMonth(saleBudget.getPlanMonth());
				subMonthMoney.setMonthBudgetMoney(saleBudget.getSaleMoney()
						* subMonthMoney.getSubjectRate() / 100);
				subMonthMoney.setSbRateId(subBudgetRate.getId());
				if (smMoney != null) {
					subMonthMoney.setRootId(smMoney.getRootId());
					subMonthMoney.setFatherId(smMoney.getId());
					subMonthMoney.setSubMonthMoney(smMoney);
					subMonthMoney.setFatherName(smMoney.getName());
					subMonthMoney.setMonthRealMoney(0F);
				}
				if (subMonthMoney.getId() == null)
					totalDao.save(subMonthMoney);
				else
					totalDao.update(subMonthMoney);

				Set<SubBudgetRate> subSet = subBudgetRate.getSubBudgetRateSet();
				if (subSet != null && subSet.size() > 0) {
					List<SubBudgetRate> sonList = new ArrayList<SubBudgetRate>();
					sonList.addAll(subSet);
					addsubMonthMoney(sonList, saleBudget, subMonthMoney);
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateSaleBudgetById(Integer id, String tag) {
		// TODO Auto-generated method stub
		SaleBudget sbt = getSaleBudgetById(id);
		if ("yes".equals(tag)) {
			sbt.setStatus("同意");
			if (sbt != null) {
				if (sbt != null) {
					subSaleBudget(sbt);
				}
			}
		} else if ("no".equals(tag)) {
			sbt.setStatus("打回");
		} else if ("subb".equals(tag)) {
			// 提交月度预算，生成审核流程
			sbt.setStatus("未审核");
			Integer epId;
			String processName = "月度销售收入审核";
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						SaleBudget.class, sbt.getId(), "status", "id", sbt
								.getPlanMonth()
								+ "的月度销售收入,请您审核!", true, null);
				if (epId != null && epId > 0) {
					sbt.setEpId(epId);
					return totalDao.update(sbt);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return totalDao.update(sbt);
	}

	/*
	 * 月度预算销售审核列表 (non-Javadoc)
	 * 
	 * @see com.task.Server.fin.budget.SaleBudgetServer#findExamList(int, int)
	 */
	@Override
	public Object[] findExamList(int parseInt, int pageSize) {
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				SaleBudget.class, false);
		if (map != null) {
			String hql = "from SaleBudget where id in (:entityId)";
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
	 * 审核(通过、驳回)(non-Javadoc)
	 * 
	 * @see
	 * com.task.Server.fin.budget.SaleBudgetServer#updateExamBonus(java.lang
	 * .Integer[], java.lang.String)
	 */
	@Override
	public boolean updateExamBonus(Integer[] detailSelect, String tag) {
		boolean bool = false;
		if (null != detailSelect && detailSelect.length > 0) {
			Users user = Util.getLoginUser();
			for (int i = 0; i < detailSelect.length; i++) {// 遍历处理状态
				SaleBudget detail = (SaleBudget) totalDao.getObjectById(
						SaleBudget.class, detailSelect[i]);
				CircuitRun circuitRun = circuitRunServer
						.findCircuitRunById(detail.getEpId());
				if ("ok".equals(tag)) {// 同意
					// 处理审批流程
					String audit = circuitRunServer.updateExeNodeByCirId(detail
							.getEpId(), true, "", true, null, false);
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

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}
}
