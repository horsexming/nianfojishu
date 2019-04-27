package com.task.ServerImpl;

import java.util.List;
import java.util.Map;


import com.task.Dao.TotalDao;
import com.task.Server.UserSignInServer;
import com.task.entity.AskForLeave;
import com.task.entity.UserSignIn;
import com.task.entity.Users;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class UserSignInServerImpl implements UserSignInServer {
	private TotalDao totalDao;

	// 在静态方法调用totalDao
//	private static TotalDao createTotol() {
//		// 获得totalDao
//		TotalDao totalDao = TotalDaoImpl.findTotalDao();
//		UserSignInServerImpl acc = new UserSignInServerImpl();
//		acc.setTotalDao(totalDao);
//		return totalDao;
//	}

//	@Override
//	public Map<Integer, Object> findAttendanceTow(AttendanceTow attendanceTow,
//			int pageNo, int pageSize) {
//		// TODO Auto-generated method stub
//		if (attendanceTow == null) {
//			attendanceTow = new AttendanceTow();
//		}
//		String sql = "";
//		String hql = totalDao.criteriaQueries(attendanceTow, sql);
//		hql += " order by id desc";
//		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
//		int count = totalDao.getCount(hql);
//		Map<Integer, Object> map = new HashMap<Integer, Object>();
//		map.put(1, listInt);
//		map.put(2, count);
//		return map;
//	}


	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String add(UserSignIn userSignIn) {
		// TODO Auto-generated method stub
		Users users = (Users) totalDao.getObjectByCondition("from Users where id = ? and code = ?", userSignIn.getUserId(),userSignIn.getCode());
		if (users!=null) {
			boolean b = true;
			if ("0".equals(userSignIn.getStatus())) {
				userSignIn.setStatus("正常");
			}else if("1".equals(userSignIn.getStatus())){
				userSignIn.setStatus("出差");
				b=false;
			}else {
				return "位置异常，签到失败！";
			}
			userSignIn.setDept(users.getDept());
			userSignIn.setName(users.getName());
			userSignIn.setCardId(users.getCardId());
			String nowTime_1 = Util.getDateTime();// 获取当前时间
			userSignIn.setAddTime(nowTime_1);
			if (totalDao.save(userSignIn)) {
				if((userSignIn.getJingdu()!=null&&userSignIn.getJingdu()>0)||b){
					//如果为出差，经纬度不为空，进行打卡操作。
					boolean min = true;
					if(b){
					}else {//查询公出请假记录
						// 查询请假表语句:当天有请假记录的，全天都能进出
						String askForLeaveStr = "from AskForLeave where leaveUserCardId = ? and leavePerson = ? and leavePersonCode = ? and ((? < leaveStartDate and leaveStartDate < ?) or (? < leaveEndDate and leaveEndDate < ?) or (leaveStartDate < ? and ? < leaveEndDate)) and approvalStatus in ('审批中','同意')";
						String sk_date = Util.getDateTime("yyyy-MM-dd");// 获取当前日期
						String sk_date_one = Util.getSpecifiedDayAfter(sk_date, 1);// 获取当前日期的后一天
						List<AskForLeave> askForLeavelist = totalDao.query(askForLeaveStr, users.getCardId(), users.getName(),users.getCode(), sk_date, sk_date_one,sk_date, sk_date_one,nowTime_1,nowTime_1);
						double dd = 0;
						if (askForLeavelist != null && askForLeavelist.size() > 0) {
							// 有请假申请流程
							for (AskForLeave ask : askForLeavelist) {
								if(ask.getLat()!=null){
									dd = LantitudeLongitudeDist(userSignIn.getJingdu(), userSignIn.getWeidu(), ask.getLng().doubleValue(), ask.getLat().doubleValue());
									if(dd<1000){
										min = false;
										break;
									}
								}
							}
						}else {
							return "没有有效公出申请记录，出差签到无效！";
						}
						if(min){
							return "不在有效打卡范围内，出差签到无效！距离："+dd+"米";
						}
					}
					try {
						String jieguo = "true";
						jieguo = AttendanceTowServerImpl.addAttendanceTow(users.getCardId(),users.getCode(), users.getName(), users.getDept(), users.getId(),userSignIn.getStatus(),userSignIn.getAddress(),"手机",null);
						if(!"true".equals(jieguo)){
							return jieguo;
						}
						//DownloadServerImpl.AddDownLoads(users.getCardId(),userSignIn.getStatus(),userSignIn.getAddress(),"手机");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					return "出差打卡请选择经纬度";
				}
			}
			return "true";
			
		}
		return "添加失败！";
	}

	@Override
	public Map<Integer, Object> findUserSignIn(UserSignIn userSignIn,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final  double EARTH_RADIUS = 6378137;//赤道半径(单位m)
	
	 /**
     * 转化为弧度(rad)
     * */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
	
	/**
     * 基于余弦定理求两经纬度距离
     * @param lon1 第一点的经度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的经度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     * */
    public static double LantitudeLongitudeDist(double lon1, double lat1,double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);

        if (radLat1 < 0)
            radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
        if (radLat1 > 0)
            radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
        if (radLon1 < 0)
            radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
        if (radLat2 < 0)
            radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
        if (radLat2 > 0)
            radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
        if (radLon2 < 0)
            radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
        double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = EARTH_RADIUS * Math.cos(radLat1);

        double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = EARTH_RADIUS * Math.cos(radLat2);

        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)+ (z1 - z2) * (z1 - z2));
        //余弦定理求夹角
        double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS * EARTH_RADIUS - d * d) / (2 * EARTH_RADIUS * EARTH_RADIUS));
        double dist = theta * EARTH_RADIUS;
        return dist;
    }

}
