package com.task.ServerImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.JiMiLeiXingServerDao;
import com.task.entity.DeptNumber;
import com.task.entity.JiMiLeiXing;
import com.task.entity.ModuleFunction;
import com.task.entity.Users;
import com.task.entity.sop.BuHeGePin;

public class JiMiLeiXingServerDaoImpl implements JiMiLeiXingServerDao {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
	@Override
	public boolean addJiMi(JiMiLeiXing jimi) {
		
			if(jimi!=null){
				return totalDao.save(jimi);
			}
		return false;
	}

	@Override
	public boolean delJiMi(JiMiLeiXing jimi) {
		if(jimi!=null){
			return totalDao.delete(jimi);
		}
		return false;
	}

	@Override
	public List<JiMiLeiXing> showJiMiList() {
		String hql="from JiMiLeiXing";
		return	(List<JiMiLeiXing>)totalDao.find(hql);
	}

	@Override
	public JiMiLeiXing  showJiMiListByid(Integer id) {
		String hql="from JiMiLeiXing where id="+id;
		return (JiMiLeiXing)totalDao.find(hql).get(0);
	}

	@Override
	public boolean updateJiMi(JiMiLeiXing jimi) {
		if(jimi!=null){
			JiMiLeiXing jimi1=(JiMiLeiXing) totalDao.get(jimi.getClass(), jimi.getId());
			jimi1.setType(jimi.getType());
			return totalDao.update(jimi1);
			//return totalDao.update(jimi);
		}
		return false;
	}

	@Override
	public boolean BDJiMi(JiMiLeiXing jimi,Integer[] uid) {
		Set<Users> usersset=new HashSet<Users>();
		if(jimi!=null){
			Set<Users> usersSet = new HashSet<Users>();// 用来存储最终要的绑定用户
			Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
			Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前减少的绑定用户
			JiMiLeiXing jimi2 = (JiMiLeiXing) totalDao
					.getObjectById(JiMiLeiXing.class, jimi.getId());
			Set<Users> haduserSet = jimi2.getUsers();
			if (uid != null && uid.length > 0) {
				for (int i = 0; i < uid.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							uid[i]);// 查询用户
					if (user != null) {
						usersSet.add(user);
					} else {
						return false;
					}
				}
			}
			Users user = (Users) ActionContext.getContext().getSession().get(
			"adminusers");
		boolean bool = true;
		if(user!=null){

			List<String> deptNames = totalDao
					.query("select deptName from UserDept where userId ="
							+ user.getId());
			if (jimi2 != null) {
				Set<Users> sameSet = new HashSet<Users>();// 用来存储与当前登陆用户可操作部门的用户对象
				if (haduserSet.size() > 0) {
					for (Users u : haduserSet) {
						if (u.getDept() != null
								&& deptNames.contains(u.getDept())) {
							sameSet.add(u);
						}
					}
					haduserSet.removeAll(sameSet);// 剩下的就是与当前登陆用户不可部门的用户对象
				}
				for (Users u1 : sameSet) {
					if (!usersSet.contains(u1)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
						lessSet.add(u1);
					}
				}
				for (Users u2 : usersSet) {
					if (!sameSet.contains(u2)) {// 原来的的不包含说明页面新增加了这个对象的绑定
						moreSet.add(u2);
					}
				}

			}
			usersSet.addAll(haduserSet);
		}else{
			for (Users u4 : haduserSet) {
				if (!usersSet.contains(u4)) {// 页面传过来的不包含说明页面新减少了这个对象的绑定
					lessSet.add(u4);
				}
			}
			for (Users u5 : usersSet) {
				if (!haduserSet.contains(u5)) {// 原来的的不包含说明页面新增加了这个对象的绑定
					moreSet.add(u5);
				}
			}
		}
		jimi2.setUsers(usersSet);
		bool = totalDao.update(jimi2);
		return bool;
		}

	return false;
	}

	
	// 查询所有机密等级为页面Select使用
	@SuppressWarnings("unchecked")
	public String finAllJiMiForSetlect() {
		String hql = "from JiMiLeiXing where type is not null and type <> ''";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				JiMiLeiXing jimi = (JiMiLeiXing) list.get(i);
				message += jimi.getType() + "|";
			}
			return message;
		}
		return null;
	}

	@Override
	public List<JiMiLeiXing> findAllByPage(int pageNo, int pageSize) {
		String hql="from JiMiLeiXing";
		
		return (List<JiMiLeiXing>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	public int getcont() {
		String hql="from JiMiLeiXing";
		
		 return totalDao.getCount(hql);
	}

	@Override
	public String finAllDeptNumberForSetlect() {
		String hql="from JiMiLeiXing where type is not null and type <> ''";
		List list = totalDao.query(hql);
		if (list != null) {
			String message = "";
			for (int i = 0; i < list.size(); i++) {
				JiMiLeiXing jimileixing = (JiMiLeiXing) list.get(i);
				message += jimileixing.getType() + "|";
			}
			return message;
		}
		return null;
		
	}
}
