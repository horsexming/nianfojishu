package com.task.ServerImpl;

import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Dao.TotalDao;
import com.task.Server.CareertrackServer;
import com.task.entity.AssScore;
import com.task.entity.Careertrack;
import com.task.entity.Password;
import com.task.entity.Promotion;
import com.task.entity.RewardPunish;
import com.task.entity.Transfer;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.sop.BuHeGePin;
import com.task.util.Util;

public class CareertrackServerImpl implements CareertrackServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public boolean add(Careertrack ck) {
		if (ck != null) {
			return totalDao.save(ck);

		}
		return false;
	}

	@Override
	public boolean del(Careertrack ck) {
		if (ck != null) {
			return totalDao.delete(ck);
		}
		return false;
	}

	@Override
	public Careertrack findCareertrackByuId(Integer id) {
		if (id != null && id > 0) {
			String hql = "from Careertrack where userId=?";
			return (Careertrack) totalDao.getObjectByCondition(hql, id);
		}
		return null;
	}

	@Override
	public boolean update(Careertrack ck) {
		if (ck != null) {
			Careertrack oldck = findCareertrackByuId(ck.getUserId());
			BeanUtils.copyProperties(ck, oldck, new String[] { "id",
					"userName", "uId" });
			return totalDao.update(oldck);
		}
		return false;
	}

	@Override
	public boolean addMore() {
		String hql = "from Users where internal='是' ";
		List<Users> userList = totalDao.find(hql);
		if (userList != null && userList.size() > 0) {
			for (Users users : userList) {
				InterviewLog ig = null;
				if (users.getUid() != null && !"".equals(users.getUid())) {
					String hql_ms = "from InterviewLog where cardID=? ";
					ig = (InterviewLog) totalDao.getObjectByCondition(hql_ms,
							users.getUid());
				}
				String mianshiTime = "";
				if (ig != null) {
					mianshiTime = ig.getInterviewAddTime();
				}

				String ruzhiTime = "";
				if (users.getJoined() != null) {
					ruzhiTime = Util.DateToString(users.getJoined(),
							"yyyy-MM-dd HH:mm:ss");
				}
				String hql_jx = "select max(rateDate) from AssScore where userId = ?";
				String jixiaoTime = (String) totalDao.getObjectByCondition(
						hql_jx, users.getId());
				;
				String hql_jc = "select max(date) from RewardPunish where userId =?";
				Date date = (Date) totalDao.getObjectByCondition(hql_jc, users
						.getId());
				String jiangchengTime = "";
				if (date != null) {
					jiangchengTime = Util.DateToString(date,
							"yyyy-MM-dd HH:mm:ss");
				}
				Date lzdate = users.getLeaveDate();
				String lizhiTime = "";
				if (lzdate != null) {
					lizhiTime = Util
							.DateToString(lzdate, "yyyy-MM-dd HH:mm:ss");
				}
				Date bothday = users.getBothday();
				String birthday = "";
				if (bothday != null) {
					birthday = Util.DateToString(users.getBothday(),
							"yyyy-MM-dd");
				}
				String status = "";
				if (users.getJoined() != null) {
					status = users.getOnWork();
				}
				String PhoneNumber = "";
				if (users.getPassword() != null) {
					PhoneNumber = users.getPassword().getPhoneNumber();
				}
				String zhuangzhengdate = null;
				if (users.getZhuanzhengtime() != null
						&& !"".equals(users.getZhuanzhengtime())) {
					zhuangzhengdate = users.getZhuanzhengtime();
				}
				Careertrack ck = new Careertrack(users.getName(),
						users.getId(), mianshiTime, ruzhiTime, jixiaoTime,
						jiangchengTime, lizhiTime, users.getDept(), users
								.getEducation(), users.getUid(), users
								.getNation(), users.getBirthplace(), birthday,
						PhoneNumber, users.getDuty(), zhuangzhengdate, status,
						users.getCode());
				if (!totalDao.save(ck)) {
					return false;
				}
				;
			}
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> findUsersById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		// Users users = (Users) totalDao.get(Users.class, id);
		Careertrack ck = (Careertrack) totalDao.get(Careertrack.class, id);

		InterviewLog ig = null;
		if (ck.getCardId() != null && !"".equals(ck.getCardId())) {
			String hql_ms = "from InterviewLog where cardID=?";
			ig = (InterviewLog) totalDao.getObjectByCondition(hql_ms, ck
					.getCardId());
		}
		if (ig != null && ig.getId() != null && ig.getId() > 0) {
			map.put("minashiId", ig.getId());
		}
		if (ck.getUserId() != null && ck.getUserId() > 0) {
			Users users = (Users) totalDao.get(Users.class, ck.getUserId());
			// 试用期到期以及过期了仍未处理，提醒！ tryDays-getDate()
			String nowDate=Util.getDateTime();
			String hql_sydq = "from Users where id =?  and tryDays-'"+nowDate+"'<30  ";
			Users sydqusers = (Users) totalDao.getObjectByCondition(hql_sydq,
					ck.getUserId());
			if (sydqusers != null) {
				long sc = Util.getWorkTime1(Util.getDateTime(), Util
						.DateToString(sydqusers.getTryDays(),
								"yyyy-MM-dd HH:mm:ss"));
				Integer tianshu = (int) Math.ceil(sc / (1000 * 60 * 60 * 24d));
				if (tianshu != null) {
					map.put("tianshu", tianshu);
				}
			}
			// 个人转正考核
			String hql_zzkh = "from AssScore where userId = ? and templateId  in ( select id from Template where type = '转正'"
					+ " )  order by rateDate desc";
			AssScore zzkh = (AssScore) totalDao.getObjectByCondition(hql_zzkh,
					ck.getUserId());
			if (zzkh != null) {
				map.put("zzkh", zzkh);
			}

			// 个人绩效
			String hql_jx = " from AssScore where userId = ? and templateId not in ( select id from Template where type = '转正'"
					+ " )  order by rateDate desc";
			List<AssScore> jxList = totalDao.findAllByPage(hql_jx, 1, 3, ck
					.getUserId());
			if (jxList != null && jxList.size() > 0) {
				map.put("jxList", jxList);
			}
			// 个人奖惩;
			String hql_jc = "from RewardPunish where userId =? order by date desc";
			List<RewardPunish> jcList = totalDao.findAllByPage(hql_jc, 1, 3, ck
					.getUserId());
			if (jcList != null && jcList.size() > 0) {
				map.put("jcList", jcList);
			}
			// 个人调动
			String hql_dg = "from Transfer where userId =? and dgTime=(select max(dgTime) from Transfer)";
			Transfer transfer = (Transfer) totalDao.getObjectByCondition(
					hql_dg, ck.getUserId());
			if (transfer != null) {
				map.put("transfer", transfer);
			}
			// 个人晋升
			String hql_js = "from Promotion where userId =? and jsTime = (select max(jsTime) from Promotion)";
			Promotion promotion = (Promotion) totalDao.getObjectByCondition(
					hql_js, ck.getUserId());
			if (promotion != null) {
				map.put("promotion", promotion);
			}
			// 离职交接单
			String hql_lzjj = " from Dimission_Handover where codeId = ?";
			Dimission_Handover lzjj = (Dimission_Handover) totalDao
					.getObjectByCondition(hql_lzjj, ck.getUserId());
			if (lzjj != null) {
				map.put("lzjj", lzjj);
			}
			// 离职申请单
			String hql_lzsq = "from DimissionLog where codeId = ?";
			DimissionLog lzsq = (DimissionLog) totalDao.getObjectByCondition(
					hql_lzsq, ck.getUserId());
			if (lzsq != null) {
				map.put("lzsq", lzsq);
			}
			// 劳动协议终止
			String hql_zzht = "select id from Dimission_XieYi where codeId = ?";
			Integer zzhtId = (Integer) totalDao.getObjectByCondition(hql_zzht,
					ck.getUserId());
			if (zzhtId != null && zzhtId > 0) {
				map.put("zzhtId", zzhtId);
			}
			// 离职通知
			String hql_lztz = "select id from DimissionNotice where name = ? and code = ?";
			Integer lztzId = (Integer) totalDao.getObjectByCondition(hql_lztz,
					users.getName(), users.getCode());
			if (lztzId != null && lztzId > 0) {
				map.put("lztzId", lztzId);
			}
			// 离职工资
			String hql_lzgz = "select id from Dimission_ZhengYi where codeId = ?";
			Integer lzgzId = (Integer) totalDao.getObjectByCondition(hql_lzgz,
					ck.getUserId());
			if (lzgzId != null && lzgzId > 0) {
				map.put("lzgzId", lzgzId);
			}
		}
		return map;

	}

	@Override
	public Careertrack showCareertrackByid(Integer id) {
		if (id != null) {
			return (Careertrack) totalDao.get(Careertrack.class, id);
		}
		return null;
	}

	@Override
	public List<Careertrack> FindAllCareertrack(int pageNo, int pageSize) {
		String hql = "from Careertrack where status  in ('面试','待入职','试用','实习','在职') order by id desc";

		return (List<Careertrack>) totalDao
				.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Map<Integer, Object> findCareertrackByCondition(Careertrack ck,
			int pageNo, int pageSize, String statue) {
		if (ck == null) {
			ck = new Careertrack();
		}
		if ("dept".equals(statue)) {
			Users user = Util.getLoginUser();
			ck.setDept(user.getDept());
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(ck, null);
		int count = totalDao.getCount(hql);
		List<Careertrack> ckList = (List<Careertrack>) totalDao.findAllByPage(
				hql, pageNo, pageSize);
		map.put(1, ckList);
		map.put(2, count);
		return map;
	}

	@Override
	public int getcont() {
		String hql = "from Careertrack where status  in ('面试','待入职','试用','实习','在职') order by id desc";

		return totalDao.getCount(hql);
	}

	@Override
	public Users findUsersByckId(Integer id) {
		Careertrack ck = showCareertrackByid(id);
		if (ck != null && ck.getUserId() != null && ck.getUserId() > 0) {
			return (Users) totalDao.get(Users.class, ck.getUserId());
		}
		return null;
	}

	@Override
	public void xiufuUserId() {
		// String hql =
		// " from  Careertrack where cardId is not null and cardId <> ''";
		String hql = " from  Careertrack where userId is not null ";
		List<Careertrack> ckList = totalDao.query(hql);
		for (Careertrack ck : ckList) {
			String hql_user = " from Users where id=?";
			Users user = (Users) totalDao.getObjectByCondition(hql_user, ck
					.getUserId());
			if (user != null) {
				ck.setStatus(user.getOnWork());
				totalDao.update(ck);
			}
		}
	}

	@Override
	public void shuaixin() {
		String hql = " FROM Users where id not IN (select userId FROM Careertrack where userId is not null) AND dept <> '供应商'";
		List<Users> userList = totalDao.query(hql);
		for (Users user : userList) {
			Careertrack ck = new Careertrack(user.getName(), user.getId(), "",
					Util.DateToString(user.getJoined(), "yyyy-MM-dd HH:mm:ss"),
					user.getZhuanzhengtime(), "", "", "", "", Util
							.DateToString(user.getLeaveDate(),
									"yyyy-MM-dd HH:mm:ss"), user.getDept(),
					user.getEducation(), user.getUid(), user.getNation(), user
							.getBirthplace(), Util.DateToString(user
							.getBothday(), "yyyy-MM-dd"), user.getPassword()
							.getPhoneNumber(), user.getJobtitle(), user
							.getCode());
			ck.setStatus(user.getOnWork());
			totalDao.save(ck);

		}

	}

}
