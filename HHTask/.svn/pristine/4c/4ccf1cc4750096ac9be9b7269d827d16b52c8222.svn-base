package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.InsuranceGoldServer;
import com.task.entity.InsuranceGold;
import com.task.util.Util;

public class InsuranceGoldServerImpl implements InsuranceGoldServer {

	private TotalDao totalDao;// Dao层

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 查找百分比通过户籍
	@SuppressWarnings("unchecked")
	public InsuranceGold findInsuranceGoldBylc(String localOrField,
			String cityOrCountryside, String personClass) {
		if (localOrField != null && cityOrCountryside != null) {
			String dateTime = totalDao.getDateTime("yyyy-MM-dd");
			String hql = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
			List list = totalDao.query(hql, localOrField, cityOrCountryside,
					personClass, dateTime, dateTime);
			if (list != null && list.size() > 0) {
				return (InsuranceGold) list.get(0);
			}
		}
		return null;
	}

	// 添加百分比
	public boolean addInsuranceGold(InsuranceGold insuranceGold) {
		if (insuranceGold != null) {
			return totalDao.save(insuranceGold);
		}
		return false;
	}

	// 有效时间比较
	public boolean CompareValidityDate(InsuranceGold insuranceGold) {
		if (insuranceGold != null) {
			String hql = "from InsuranceGold where validityEndDate>=? and validityStartDate<=? and localOrField=? and cityOrCountryside=? and personClass=?";
			List startList = totalDao.query(hql, insuranceGold
					.getValidityStartDate(), insuranceGold
					.getValidityStartDate(), insuranceGold.getLocalOrField(),
					insuranceGold.getCityOrCountryside(), insuranceGold
							.getPersonClass());
			List endList = totalDao.query(hql, insuranceGold
					.getValidityEndDate(), insuranceGold.getValidityEndDate(),
					insuranceGold.getLocalOrField(), insuranceGold
							.getCityOrCountryside(), insuranceGold
							.getPersonClass());
			if (startList != null && startList.size() > 0) {
				return false;
			} else if (endList != null && endList.size() > 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	// 更新工资模版信息(养老保险、医疗保险、失业保险、公积金)
	public boolean updateWSByIG() {
		String dateTime = totalDao.getDateTime("yyyy-MM-dd");
		String hql = "from InsuranceGold where  validityStartDate<=? and validityEndDate>=?";
		List list = totalDao.query(hql, dateTime, dateTime);
		if (list != null && list.size() > 0) {
			boolean bool = true;
			for (int i = 0; i < list.size(); i++) {
				InsuranceGold insuranceGold = (InsuranceGold) list.get(i);
				String sql = "update ta_fin_WageStandard set "
						+ "ta_fin_tongchoujin = case when ta_fin_tongchoujin = 0 then 0 else ?*ta_fin_ssbase/100 end,"
						+ "ta_fin_yiliaobaoxian = case when ta_fin_yiliaobaoxian = 0 then 0 else ?*ta_fin_ssbase/100 end,"
						+ "ta_fin_shiyebaoxian = case when ta_fin_shiyebaoxian = 0 then 0 else ?*ta_fin_ssbase/100 end,"
						+ "ta_fin_gongjijin = case when ta_fin_gongjijin = 0 then 0 else ?*ta_fin_gjjBase/100 end"
						+ " where ta_fin_localOrField=? and ta_fin_cityOrCountryside=? and ta_fin_personClass=?";
				int number = totalDao.createQueryUpdate(null, sql,
						insuranceGold.getOldageInsurance(), insuranceGold
								.getMedicalInsurance(), insuranceGold
								.getUnemploymentInsurance(), insuranceGold
								.getHousingFund(), insuranceGold
								.getLocalOrField(), insuranceGold
								.getCityOrCountryside(), insuranceGold
								.getPersonClass());
				if (number < 0) {
					bool = false;
				}
			}
			return bool;
		}
		return false;
	}

	// 查询所有保险缴纳比例(分页)
	public Object[] findAllInsuranceGold(String cpage, int pageSize) {
		String hql = "from InsuranceGold";
		List list = totalDao.findAllByPage(hql, Integer.parseInt(cpage),
				pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 查询当前时间有效期内的比例数据
	 * 
	 * @return
	 */
	@Override
	public List findTimeIns() {
		String hql = "from InsuranceGold where validityEndDate>=? and validityStartDate<=?";
		return totalDao.query(hql, Util.getDateTime("yyyy-MM-dd"), Util
				.getDateTime("yyyy-MM-dd"));
	}

	// 删除保险缴纳比例
	public boolean delInsuranceGold(InsuranceGold insuranceGold) {
		if (insuranceGold != null) {
			return totalDao.delete(insuranceGold);
		}
		return false;
	}

	// 根据Id查询保险缴纳比例
	public InsuranceGold findInsuranceGoldById(int id) {
		if ((Object) id != null && id > 0) {
			return (InsuranceGold) totalDao.getObjectById(InsuranceGold.class,
					id);
		}
		return null;
	}

}
