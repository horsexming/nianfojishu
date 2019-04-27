package com.task.ServerImpl.android;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.android.ApkServer;
import com.task.entity.system.CompanyInfo;

public class ApkServerImpl implements ApkServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 查询公司坐标-android
	public List findJWD() {
		String hql = "from CompanyInfo";
		List list = totalDao.find(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public String findJWD_1() {
		// TODO Auto-generated method stub
		String hql = "from CompanyInfo";
		String jingwei = "";
		List<CompanyInfo> list = totalDao.query(hql);
		if (list != null && list.size() > 0) {
			CompanyInfo companyInfo = list.get(0);
			if (companyInfo.getJingdu() != null
					&& companyInfo.getWeidu() != null) {
				jingwei = companyInfo.getJingdu() + "&"
						+ companyInfo.getWeidu();
			} else {
				jingwei = "0&0";
			}
			return jingwei;
		}
		return "0&0";
	}

}
