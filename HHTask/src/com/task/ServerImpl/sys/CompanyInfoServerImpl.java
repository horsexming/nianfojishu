package com.task.ServerImpl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.sys.CompanyInfoServer;
import com.task.entity.system.CompanyInfo;
import com.task.entity.system.LicenseMsg;

@SuppressWarnings("unchecked")
public class CompanyInfoServerImpl implements CompanyInfoServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub

		return totalDao.save(companyInfo);
	}

	@Override
	public boolean delete(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		return totalDao.delete(companyInfo);
	}

	@Override
	public Map<Integer, Object> findCompanysByCondition(
			CompanyInfo companyInfo, int pageNo, int pageSize) {
		if (companyInfo == null) {
			companyInfo = new CompanyInfo();
		}
		String hql = totalDao.criteriaQueries(companyInfo, null, "jingdu","weidu");
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public CompanyInfo getById(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(CompanyInfo.class, id);
		if (o != null) {
			CompanyInfo companyInfo = (CompanyInfo) o;
			return companyInfo;
		}
		return null;
	}

	@Override
	public boolean update(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		if (companyInfo != null) {
			return totalDao.update(companyInfo);
			// Object o = totalDao.getObjectById(CompanyInfo.class, companyInfo
			// .getId());
			// if (o != null) {
			// CompanyInfo c = (CompanyInfo) o;
			// c.setAddress(companyInfo.getAddress());
			// if (companyInfo.getBottomlogo() != null) {
			// c.setBottomlogo(companyInfo.getBottomlogo());
			// }
			// if (companyInfo.getTasklogo() != null) {
			// c.setTasklogo(companyInfo.getTasklogo());
			// }
			// c.setEmail(companyInfo.getEmail());
			// c.setFax(companyInfo.getFax());
			// c.setName(companyInfo.getName());
			// c.setTel(companyInfo.getTel());
			// c.setUrl(companyInfo.getUrl());
			// c.setUrlname(companyInfo.getUrlname());
			// c.setZip(companyInfo.getZip());
			// return totalDao.update(c);
			// }
		}
		// return totalDao.update(companyInfo);
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(CompanyInfo.class, id);
		if (o != null) {
			CompanyInfo companyInfo = (CompanyInfo) o;
			return totalDao.delete(companyInfo);
		}
		return false;
	}

	@Override
	public int getOnlineCount(String companyUrl) {
		// TODO Auto-generated method stub
		int count =0;
		List <Integer> countList=totalDao.query("select onLineConut from LicenseMsg where companyUrl=?", companyUrl);
		 if(countList.size()>0){
			 count=countList.get(0);
		 }
		return count;
	}

	@Override
	public LicenseMsg getLicensemsg(String companyUrl) {
		// TODO Auto-generated method stub
		List <LicenseMsg> list=totalDao.query("from LicenseMsg where companyUrl=?", companyUrl);
		 if(list!=null&&list.get(0)!=null&&list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	@Override
	public int getOnLEDCount(String companyUrl) {
		// TODO Auto-generated method stub
		int count =0;
		List <Integer> countList=totalDao.query("select onLEDConut from LicenseMsg where companyUrl=?", companyUrl);
		 if(countList!=null&&countList.get(0)!=null&&countList.size()>0){
			 count=countList.get(0);
		 }
		return count;
	}

	@Override
	public int getOneMackCount(String companyUrl) {
		// TODO Auto-generated method stub
		int count =0;
		List <Integer> countList=totalDao.query("select oneMackConut from LicenseMsg where companyUrl=?", companyUrl);
		 if(countList!=null&&countList.get(0)!=null&&countList.size()>0){
			 count=countList.get(0);
		 }
		return count;
	}

	@Override
	public int getoneScreenConut(String companyUrl) {
		// TODO Auto-generated method stub
		int count =0;
		List <Integer> countList=totalDao.query("select oneScreenConut from LicenseMsg where companyUrl=?", companyUrl);
		 if(countList!=null&&countList.get(0)!=null&&countList.size()>0){
			 count=countList.get(0);
		 }
		return count;
	}
}
