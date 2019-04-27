package com.task.ServerImpl.rtx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.rtx.RtxMsgServer;
import com.task.entity.DeptNumber;
import com.task.entity.Users;
import com.task.entity.rtx.RtxConnect;
import com.task.entity.rtx.RtxDept;
import com.task.entity.rtx.RtxMsg;
import com.task.entity.rtx.RtxUser;
import com.task.util.RtxUtil;

public class RtxMsgServerImpl implements RtxMsgServer {
	private TotalDao totalDao;
	private Integer msgcount;

	public Integer getMsgcount() {
		return msgcount;
	}

	public void setMsgcount(Integer msgcount) {
		this.msgcount = msgcount;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean saveSendRtxMsg(Users sender, List<String> receiver,
			String msgContext, String title) {
		// TODO Auto-generated method stub
		if (receiver != null && receiver.size() > 0) {
			boolean b = RtxUtil.sendNotify(receiver, msgContext, "平台消息", "0",
					"0");
			return b;
		}
		return false;
	}

	@Override
	public List<Users> getDeptsUsers(String deptIds) {
		// TODO Auto-generated method stub
		if (deptIds != null) {
			return totalDao
					.query("from Users where onWork not in ('离职','内退','离职中','退休') and deptId in ("
							+ deptIds + ") order by deptId");
		} else {
			return null;
		}

	}

	@Override
	public List<DeptNumber> getDeptVos() {
		// TODO Auto-generated method stub
		List<DeptNumber> all = totalDao
				.query("from DeptNumber where dept not in('内退','病休')");
		return all;
	}

	@Override
	public Map<Integer, Object> findrtxMsgsByCondition(RtxMsg rtxMsg,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (rtxMsg == null) {
			rtxMsg = new RtxMsg();
		}
		String hql = totalDao.criteriaQueries(rtxMsg, " 1=1 order by id desc",
				null);
		int count = totalDao.getCount(hql);
		List objs = totalDao.findAllByPage(hql, pageNo, pageSize);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, objs);
		map.put(2, count);
		return map;
	}

	@Override
	public List findAllRtxDeptForJson() {
		List list = new ArrayList();
		try {
			if (RtxUtil.userName == null) {
				RtxUtil.userName = "";
			}
			if (RtxUtil.userPwd == null) {
				RtxUtil.userPwd = "";
			}
			RtxUtil.userName = RtxUtil.userName.replaceAll(" ", "");
			RtxUtil.userPwd = RtxUtil.userPwd.replaceAll(" ", "");
			Properties prop = new Properties();
			prop.put("charSet", "gb2312"); // 这里是解决中文乱码
			prop.put("user", "");
			prop.put("password", "");
			RtxUtil.dbConn = RtxUtil.getDbConnection();
			RtxUtil.st = RtxUtil.dbConn.createStatement();
			RtxUtil.res = RtxUtil.st
					.executeQuery("select DeptID,PDeptID,DeptName from RTX_Dept");
			while (RtxUtil.res.next()) {
				RtxDept rtxDept = new RtxDept();
				rtxDept.setId(RtxUtil.res.getInt(1));
				rtxDept.setFatherId(RtxUtil.res.getInt(2));
				rtxDept.setDeptName(RtxUtil.res.getString(3));
				list.add(rtxDept);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RtxUtil.close();
		}
		return list;
	}

	@Override
	public List getRtxUsersByDeptId(String deptIds) {
		// TODO Auto-generated method stub
		// String hql =
		// "from RtxUser where id in(select u.id from RtxUser u join u.rtxDept d where d.id in("
		// + deptIds + "))";
		// List rxtUser = totalDao.query(hql);

		List rxtUser = new ArrayList();
		try {
			Class.forName(RtxUtil.driverName);
			if (RtxUtil.userName == null) {
				RtxUtil.userName = "";
			}
			if (RtxUtil.userPwd == null) {
				RtxUtil.userPwd = "";
			}
			RtxUtil.userName = RtxUtil.userName.replaceAll(" ", "");
			RtxUtil.userPwd = RtxUtil.userPwd.replaceAll(" ", "");
			Properties prop = new Properties();
			prop.put("charSet", "gb2312"); // 这里是解决中文乱码
			prop.put("user", "");
			prop.put("password", "");
			RtxUtil.dbConn = RtxUtil.getDbConnection();
			RtxUtil.st = RtxUtil.dbConn.createStatement();
			RtxUtil.res = RtxUtil.st
					.executeQuery("select UserName,Name from SYS_User where id in(select UserID from RTX_DeptUser where DeptID in("
							+ deptIds + "))");
			while (RtxUtil.res.next()) {
				RtxUser rtxUser = new RtxUser();
				rtxUser.setUserName(RtxUtil.res.getString(1));// 工号
				rtxUser.setName(RtxUtil.res.getString(2));// 名称
				rxtUser.add(rtxUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			RtxUtil.close();
		}

		return rxtUser;
	}

	@Override
	public String addRtxConnect(RtxConnect rtxConnect) {
		int count = totalDao.getCount(" from RtxConnect ");
		if (count >= 1) {
			String msg = "";
			msgcount = (Integer) ActionContext.getContext().getSession().get(
					"msgcount");
			if (msgcount == null) {
				msgcount = 0;
			}
			if (msgcount > 3) {
				msg = "提醒两次了还不够吗？非得让我提醒第" + (msgcount + 1)
						+ "次，不要这么调皮好不好，你添加不上去的啊!";
				ActionContext.getContext().getSession().put("msgcount",
						msgcount++);
			} else {
				msg = "有一个RTX连接配置就够了，不要太贪心啊!";
				msgcount += 1;
				ActionContext.getContext().getSession().put("msgcount",
						msgcount);
			}
			return msg;
		}
		if (rtxConnect != null) {
			return totalDao.save(rtxConnect) + "";
		} else {
			return "请刷新后重试!";
		}
	}

	@Override
	public String delRtxConnect(RtxConnect rtxConnect) {
		if (rtxConnect != null) {
			return totalDao.delete(rtxConnect) + "";
		}
		return null;
	}

	@Override
	public List<RtxConnect> showRtxConnect() {
		return totalDao.query(" from RtxConnect");
	}

	@Override
	public String updateRtxConnect(RtxConnect rtxConnect) {
		if (rtxConnect != null) {
			return totalDao.update(rtxConnect) + "";
		}
		return null;
	}

	@Override
	public RtxConnect getRtxConnectById(Integer id) {
		if (id != null) {
			return (RtxConnect) totalDao.get(RtxConnect.class, id);
		}
		return null;
	}

	@Override
	public String sendAgin(Integer id) {
		// TODO Auto-generated method stub
		String msg = RtxUtil.sendAgin(id);
		return msg;
	}

}
