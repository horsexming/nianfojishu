package com.task.ServerImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.task.Dao.TotalDao;
import com.task.Server.CredentialsServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Credentials;
import com.task.entity.Users;
import com.task.util.Util;

/**
 * 
 * @author 于勇鸿斌 2017-07-25
 * 
 */
@SuppressWarnings("unchecked")
public class CredentialsServerImpl implements CredentialsServer {
	private TotalDao totalDao;
	
	//分页查询所有内容,按工号查询
	@Override
	public Map<Integer, Object> findcredentials(
			Credentials credentials, int pageNo, int pageSize,String ps) {
		if(credentials == null){
			credentials = new Credentials();
		}
		String sql = "";
		if ("person".equals(ps)) {
			Users users = Util.getLoginUser();
			sql = "code='"+users.getCode()+"'";
		}else if("all".equals(ps)){
		}else {
			sql = "code='无！'";
		}
		String hql = totalDao.criteriaQueries(credentials, sql)+" order by id desc"; 
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}
	
	//查看某一条数据
	public Credentials findcredentialsById(Integer id) {
		// TODO Auto-generated method stub
 		Object o = totalDao.getObjectById(Credentials.class, id);
		if (o != null) {
			Credentials credentials = (Credentials) o;
			return credentials;
		}
		return null;
	}
	//删除
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		Object o = totalDao.getObjectById(Credentials.class, id);
		if (o != null) {
			Credentials credentials = (Credentials) o;
			return totalDao.delete(credentials);
		}
		return false;
	}
	//添加
	@Override
	public String addCredentials(Credentials credentials,
			String ps) {
		// TODO Auto-generated method stub
		if("person".equals(ps)){
			Users u = Util.getLoginUser();
			credentials.setCode(u.getCode());
			credentials.setName(u.getName());
			credentials.setSex(u.getSex());
		}else if("admin".equals(ps)){
		}else {
			return "添加状态异常，添加失败！";
		}
		if(credentials.getCardtype().isEmpty()){
			return "";
		}else {
			if("驾驶证".equals(credentials.getCardtype())){
				int i = totalDao.getCount("from Credentials where code = ? and name = ? and cardtype = '驾驶证'", credentials.getCode(),credentials.getName());
				if(i>0)
					return credentials.getName()+"驾驶证已添加过！";
			}else if("行驶证".equals(credentials.getCardtype())){
				int i = totalDao.getCount("from Credentials where platenumber = ? and cardtype = '行驶证'", credentials.getPlatenumber());
				if(i>0)
					return credentials.getPlatenumber()+"驾驶证已添加过！车主："+credentials.getName();
			}
			credentials.setAddTime(Util.getDateTime());
			if(totalDao.save(credentials))
				return "添加成功！";
			return "添加失败！";
		}
	}
	/**
	 * 修改
	 * 
	 * @return
	 */
	@Override
	public String updateCredentials(Credentials credentials) {
		// TODO Auto-generated method stub
		if (credentials != null) {
			if (totalDao.update(credentials)){
				return "true";
			}else{
				return "修改失败!";
			}
		} else
			return "不存在该条数据，修改失败";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}


	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String shenqing(Credentials c) {
		// TODO Auto-generated method stub
		Credentials credentials2 = findcredentialsById(c.getId());
		if(credentials2!=null){
			credentials2.setStatus("未审批");
			//调用审批流程
			String processName = "公车申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Credentials.class, credentials2.getId(),
						"status", "id",
						"CredentialsAction!findcredentialsById.action?id="
								+ credentials2.getId(), 
								credentials2.getName()
								+ " 想将个人名下车牌号为"+credentials2.getPlatenumber()+"的私车转为公车使用（此车将出现在公车列表。如果有人在公出时使用此车，在车主同意并且使用完毕后，公司将会有燃油补贴费给车主）。请您审批！", true);
				if (epId != null && epId > 0) {
					credentials2.setEpId(epId);
					if(totalDao.update(credentials2)){
						return "申请成功！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
