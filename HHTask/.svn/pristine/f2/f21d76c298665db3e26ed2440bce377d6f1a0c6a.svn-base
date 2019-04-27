package com.task.ServerImpl.zhaobiao;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.zhaobiao.CaoZuoServer;
import com.task.Server.zhaobiao.MarkIdServer;
import com.task.entity.Machine;
import com.task.entity.PassReal;
import com.task.entity.Users;
import com.task.util.MD5;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Baofei;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.QueXian;
import com.tast.entity.zhaobiao.Zh_CaozuoDengji;
import com.tast.entity.zhaobiao.Zh_CaozuoEmp;
import com.tast.entity.zhaobiao.Zh_caozuo;
import com.tast.entity.zhaobiao.Zh_shebei;
import com.tast.entity.zhaobiao.Zhmoban;

public class CaoZuoServerImpl implements CaoZuoServer{
	private TotalDao totalDao;

	
	public List listBaofeiname(String leixing) {
		String hql = "select yuanyin  from Baofei  where leixing='"+leixing+"' ";
		return totalDao.query(hql);
		// List newList = new ArrayList();
		// for (DeptMonthBudget deptMonthBudget : list) {
		// deptMonthBudget.setSubMonthMoney(null);
		// newList.add(deptMonthBudget);
		// }
		// return newList;
	}
	public void deleteBaofei(Baofei baofei) {
		totalDao.delete(baofei);
	}
	public void updatebaofei(Baofei baofei) {
		totalDao.update(baofei);
	}
	public void addbaofei(Baofei baofei) {
		totalDao.save(baofei);
	}
	public void updateUsers(Users use) {
		String uid = "";
		if (use.getUid() != null && use.getUid().length() > 6) {
			MD5 md5 = new MD5();
			uid = use.getUid().substring(use.getUid().length() - 6,
					use.getUid().length());
			String mdsPassword = md5.getMD5(uid.getBytes());// 身份证转换为MD5加密
			use.getPassword().setPassword(mdsPassword);
		} else {
			use.getPassword().setPassword("e10adc3949ba59abbe56e057f20f883e");// 密码
		}
		boolean bool = totalDao.update(use);
		if (bool) {
			PassReal passReal = (PassReal) totalDao.getObjectByCondition(
					"from PassReal where uid=?", use.getId());
			if (passReal != null) {
				passReal.setRealPass(uid == "" ? "123456" : uid);// 如果身份证号为空，则初始密码为"123456"
				totalDao.update(passReal);
			} else {
				passReal = new PassReal();
				passReal.setRealPass(uid == "" ? "123456" : uid);// 如果身份证号为空，则初始密码为"123456"
				passReal.setUid(use.getId());
				totalDao.save(passReal);
			}
		}
	}

	public Users ByIdUsers(Integer id) {
		return (Users) totalDao.getObjectById(Users.class, id);
	}
	public Object[] listBaofei(Baofei baofei, Integer pageNo, Integer pageSize) {
		if (baofei == null) {
			baofei = new Baofei();
		}
		String hql = totalDao.criteriaQueries(baofei, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listUsers(Users userss, Integer pageNo, Integer pageSize) {
		if (userss == null) {
			userss = new Users();
		}
		String hql = totalDao.criteriaQueries(userss, null, null)
				+ " and onWork in ('在职','试用','实习','离职中')";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listCaoZuoHuiZong(Zh_caozuo zhCaozuo, Integer pageNo,
			Integer pageSize) {
		if (zhCaozuo == null) {
			zhCaozuo = new Zh_caozuo();
		}
		String hql = totalDao.criteriaQueries(zhCaozuo, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listCaoZuo(Zh_shebei zhShebei, Integer pageNo,
			Integer pageSize) {
		if (zhShebei == null) {
			zhShebei = new Zh_shebei();
		}
		String hql = totalDao.criteriaQueries(zhShebei, null, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List listmachinegongwei() {
		String hql = "select distinct workPosition  from Machine ";
		List list = totalDao.find(hql);
		return list;
	}

	public List listshebeibianhao(String pageStatus) {
		String hql = "  from Machine where  workPosition=?";
		List list1 = totalDao.query(hql, pageStatus);
		// for (int i = 0; i <= list1.size(); i++) {
		// Machine newMachine=new Machine();
		// newMachine=(Machine) list1.get(i);
		// String hql2 =
		// "from ProcessTemplate where   gongwei=? and  shebeiNo=?  ";
		// List list2 =
		// totalDao.query(hql2,newMachine.getNo(),newMachine.getWorkPosition());
		// if (list2.size()==0) {
		// list1.remove(i);
		// i--;
		// }
		// }
		return list1;
	}

	public List listshebeibianhao(String name, String no) {
		// workPosition ASM-01 no 5009044
		// 工位编号 设备编号
		String hql = " from Machine where   no=? and  workPosition=? ";
		List list = totalDao.query(hql, no, name);

		// String hql = " from Machine where   no=? and  workPosition=? ";
		// Zh_shebei zhShebei= (Zh_shebei)
		// totalDao.getObjectByCondition(hql,no,name);
		//		
		// String hql2 = " from EquipmentChanges where   ";
		// List list = totalDao.query(hql,no,name);
		return list;
	}

	public List listshebeigongxu(String name, String no) {
		String hql = " from ProcessTemplate where   gongwei=? and  shebeiNo=? ";
		List list = totalDao.query(hql, name, no);
		return list;
	}

	public void addshebei(Zh_shebei zhShebei) {
		totalDao.save(zhShebei);
	}

	public Zh_shebei ByIdZhshebei(Integer id) {
		return (Zh_shebei) totalDao.getObjectById(Zh_shebei.class, id);
	}

	public List listDengji(Integer id) {
		String hql = " from Zh_CaozuoDengji where  shebeiId=?";
		List list = totalDao.query(hql, id);
		return list;
	}

	public List listzhCaozuoEmp(Integer id) {
		String hql = " from Zh_CaozuoEmp where  shebeiId=?";
		List list = totalDao.query(hql, id);
		return list;
	}

	public void addzhCaozuoDengji(Zh_CaozuoDengji zhCaozuoDengji) {
		totalDao.save(zhCaozuoDengji);
	}

	public void updateshebei(Zh_shebei zhShebei) {
		totalDao.update(zhShebei);
	}

	public List listEmp(String deptname) {
		String hql = " from Users where  dept=?";
		List list = totalDao.query(hql, deptname);
		return list;
	}

	public List listDengjiByshebeiId(Integer id) {
		String hql = " from Zh_CaozuoDengji where  dept='工艺'";
		List list = totalDao.query(hql);
		return list;
	}

	public void addzhCaozuoEmp(Zh_CaozuoEmp zhCaozuoEmp) {
		totalDao.save(zhCaozuoEmp);
	}

	public void deletezhShebei(Zh_shebei zhShebei) {
		totalDao.delete(zhShebei);
	}

	public void deletezhCaozuoDengji(Zh_CaozuoDengji zhCaozuoDengji) {
		totalDao.delete(zhCaozuoDengji);
	}

	public void deletezhCaozuoEmp(Zh_CaozuoEmp zhCaozuoEmp) {
		totalDao.delete(zhCaozuoEmp);
	}
		
		public Baofei ByIdBaofei(Integer id) {
			return (Baofei) totalDao.getObjectById(Baofei.class, id);
		}
		

	public Zh_CaozuoDengji byIdEmp(String id) {
		Integer eid = Integer.parseInt(id);
		return (Zh_CaozuoDengji) totalDao.getObjectById(Zh_CaozuoDengji.class,
				eid);
	}

	public void addzhCao(Zh_caozuo newzhCaozuo) {
		totalDao.save(newzhCaozuo);
	}

	public void adddengji(String[] dengji, Integer integer) {
		for (int i = 0; i < dengji.length; i++) {
			Zh_CaozuoDengji newzCaozuoDengji = new Zh_CaozuoDengji();
			newzCaozuoDengji.setShebeiId(integer);
			newzCaozuoDengji.setCaozuodengji(dengji[i]);
			totalDao.save(newzCaozuoDengji);
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}


}
