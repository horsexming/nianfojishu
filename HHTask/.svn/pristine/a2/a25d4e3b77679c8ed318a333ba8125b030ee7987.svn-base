package com.task.ServerImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;

import sun.awt.image.PixelConverter.Bgrx;
import sun.net.www.content.image.gif;

import com.task.Dao.TotalDao;
import com.task.Server.IntegralGiftServer;
import com.task.entity.Attendance;
import com.task.entity.Gift;
import com.task.entity.IgiftSet;
import com.task.entity.IndianaGift;
import com.task.entity.Integral;
import com.task.entity.IntegralGift;
import com.task.entity.Integralsource;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.util.Util;

public class IntegralGiftServerImpl implements IntegralGiftServer {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	} 

	@Override
	public boolean addgift(Gift gift) {
		if (gift != null) {
			String hql = "from Gift where name = ? and xy_Integral=?";
			Gift oldgift = (Gift) totalDao.getObjectByCondition(hql, gift
					.getName(), gift.getXy_Integral());
			if (oldgift == null) {
				gift.setAddTime(Util.getDateTime());
				gift.setAddusers(Util.getLoginUser().getName());
				return totalDao.save(gift);
			} else {
				oldgift.setNum(gift.getNum() + oldgift.getNum());
				return totalDao.update(oldgift);
			}

		}
		return false;
	}

	// 领取礼品
	public String addIntegralGift(IntegralGift integralgift,String dhnum) {
		if (integralgift != null && integralgift.getGiftId() != null) {
			integralgift.setLqTime(Util.getDateTime());
			Gift gift = (Gift) totalDao.get(Gift.class, integralgift
					.getGiftId());
			if (gift == null) {
				return "抱歉，该物品已被删除!";
			}
			integralgift.setPicture(gift.getPicture());
			Integer num = gift.getNum();
			Integer lqnum = integralgift.getLqnum();
			if (num != null && lqnum != null && num >= lqnum) {// 更新礼品数量
				Users user = Util.getLoginUser();
				IntegralGift igift = null;
				if(dhnum!=null && !"".equals(dhnum)){
					String hql = "from IntegralGift where dhnum =? and userId=?";
					integralgift.setUserCode(user.getCode());
					integralgift.setUserId(user.getId());
					integralgift.setDept(user.getDept());
					integralgift.setUserName(user.getName());
					igift =(IntegralGift) totalDao.getObjectByCondition(hql, dhnum,user.getId());
					if(igift!=null && igift.getDhstatus()!=null && "已兑换".equals(igift.getDhstatus())){
						return "抱歉，此兑换码，已兑换过礼品!";
					}
				}
				boolean bool = totalDao.save(integralgift);
				if(igift!=null){
					igift.setDhstatus("已兑换");
					totalDao.update(igift);
				}
				if (bool) {
					gift.setNum(num - lqnum);
					totalDao.update(gift);
					if (integralgift.getXaIntegral() != null) {// 更新个人积分情况;
						String hql = "from Integral where userId =? and year =?";
						Integral integral = (Integral) totalDao
								.getObjectByCondition(hql, integralgift
										.getUserId(), Util.getDateTime("yyyy"));
						if (integral != null && integral.getTotalIntegral() < integralgift.getXaIntegral()) {
							return "抱歉，积分不足，或尚未添加积分。";
						}
						List<XiaoFei> xfList = new ArrayList<XiaoFei>();
						XiaoFei xiaofei = new XiaoFei();
						xiaofei.setNeirong(integralgift.getType());
						Float xaintegral = integralgift.getXaIntegral();
						xiaofei.setXiaofeijifen((int) Math.ceil(xaintegral));
						xfList.add(xiaofei);
						integral.setXfList(xfList);
						return IntegralServerDaoImpl.updateIntegral1(integral);
					}else{
						return "消费积分不能为空!";
					}

				}
			} else {
				return "兑换礼品数量大于礼品剩余数量，兑换失败!";
			}
		}
		return "未获取到相关数据!";
	}

	@Override
	public boolean delIntegralGift(IntegralGift integralgift) {
		if (integralgift != null) {
			return totalDao.delete(integralgift);
		}
		return false;
	} 

	@Override
	public boolean delgift(Gift gift) {
		if (gift != null) {
			return totalDao.delete(gift);
		}
		return false;
	}

	@Override
	public Map<Integer, Object> findIGByCondition(IntegralGift integralgift,
			int pageNo, int pageSize, String flag) {
		if (integralgift == null) {
			integralgift = new IntegralGift();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(integralgift, null);
		int count = totalDao.getCount(hql);
		String str = "";
		if ("duihuan".equals(flag)) {
			str = " and type='兑换' ";
		} else if ("choujiang".equals(flag)) {
			str = " and type='抽奖' ";
		}
		List<IntegralGift> IGList = (List<IntegralGift>) totalDao
				.findAllByPage(hql + str + " order by id desc", pageNo,
						pageSize);
		map.put(1, IGList);
		map.put(2, count);
		return map;
	}

	@Override
	public Map<Integer, Object> findgiftByCondition(Gift gift, int pageNo,
			int pageSize) {
		if (gift == null) {
			gift = new Gift();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(gift, null);
		int count = totalDao.getCount(hql);
		List<Gift> giftList = (List<Gift>) totalDao.findAllByPage(hql
				+ "order by id desc", pageNo, pageSize);
		map.put(1, giftList);
		map.put(2, count);
		return map;
	}

	@Override
	public boolean update(Gift gift) {
		if (gift != null && gift.getId() != null) {
			totalDao.update(gift);
		}
		return false;
	}

	@Override
	public boolean updateIntegralGift(IntegralGift integralgift) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Gift getGiftById(Integer id) {
		if (id != null && id > 0) {
			return (Gift) totalDao.get(Gift.class, id);
		}
		return null;
	}

	@Override
	public IntegralGift getIntegralGiftById(Integer id) {
		if (id != null && id > 0) {
			return (IntegralGift) totalDao.get(IntegralGift.class, id);
		}
		return null;
	}

	// 判断某人是否有足够的积分兑换礼物
	@Override
	public boolean Isenough(Integer userId, Float xaIntegral) {
		if (userId != null && xaIntegral != null && userId > 0
				&& xaIntegral >= 0) {
			String hql = " from Integral where userId =? and year =?";
			Integral integral = (Integral) totalDao.getObjectByCondition(hql,
					userId, Util.getDateTime("yyyy"));
			if (integral != null && integral.getTotalIntegral() != null
					&& integral.getTotalIntegral() > xaIntegral) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Luckdraw(Integer id) {
		if (id != null && id > 0) {
			Gift gift = (Gift) totalDao.get(Gift.class, id);
			gift.setIsLuckdraw("yes");
			return totalDao.update(gift);
		}
		return false;
	}

	@Override
	public List<Gift> findAllGift() {
		String hql = " from Gift where  num > 0 order by xy_Integral desc";
		return totalDao.find(hql);
	}

	@Override
	public Integral findIntegralByUsersId(Integer userId) {
		if (userId == null) {
			Users user = Util.getLoginUser();
			userId = user.getId();
		}
		String hql = "from Integral where userId=? and year=?";
		return (Integral) totalDao.getObjectByCondition(hql, userId, Util
				.getDateTime("yyyy"));
	}

	@Override
	public Map<String, Object> GiftIndex() {
		Users user = Util.getLoginUser();
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from Integral where userId=? and year=?";
		Integral integral = (Integral) totalDao.getObjectByCondition(hql, user
				.getId(), Util.getDateTime("yyyy"));
		if (integral != null) {
			map.put("integral", integral);
		}
		// 查出最近20条消费记录;
		String hql_xf = " from XiaoFei where in_code=? order by xiaofeitime desc";
		List<XiaoFei> xfList = totalDao.findAllByPage(hql_xf, 1, 20, user
				.getCode());
		if (xfList != null && xfList.size() > 0) {
			map.put("xfList", xfList);
		}
		// 查出当前登录人最近三条兑换记录；
		String hql_dhuser = " from IntegralGift where userId=? and type = '兑换' order by lqTime desc ";
		List<IntegralGift> dhuserList = totalDao.findAllByPage(hql_dhuser, 1,
				3, user.getId());
		if (dhuserList != null && dhuserList.size() > 0) {
			map.put("dhuserList", dhuserList);
		}
		// 查出所有人最近十五条兑换记录
		String hql_dhAll = " from IntegralGift where type = '兑换' order by lqTime desc";
		List<IntegralGift> dhAllList = totalDao.findAllByPage(hql_dhAll, 1, 15);
		if (dhAllList != null && dhAllList.size() > 0) {
			map.put("dhAllList", dhAllList);
		}
		// 查出当前登录人最近三条中奖记录
		String hql_zjuserList = " from IntegralGift where userId=? and type = '抽奖' order by lqTime desc ";
		List<IntegralGift> zjuserList = totalDao.findAllByPage(hql_zjuserList,
				1, 3, user.getId());
		if (zjuserList != null && zjuserList.size() > 0) {
			map.put("zjuserList", zjuserList);
		}
		// 查出所有人最近十五条中奖记录
		String hql_zjAll = " from IntegralGift where type = '抽奖' order by lqTime desc";
		List<IntegralGift> zjAllList = totalDao.findAllByPage(hql_zjAll, 1, 15);
		
		if (zjAllList != null && zjAllList.size() > 0) {
			map.put("zjAllList", zjAllList);
		}
		return map;

	}

	@Override
	public String findcjgift() {
		String hql = "select name from Gift where isLuckdraw = 'yes' and num >0";
		List<String> strlist = totalDao.find(hql);
		String str = "";
		if (strlist != null && strlist.size() > 0) {
			for (int i = 0; i < strlist.size(); i++) {
				if (i == 0) {
					str += strlist.get(i);
				} else {
					str += "_" + strlist.get(i);
				}
			}
		}
		return str;
	}

	@Override
	public String choujiang(Integer userId, String giftName) {
		if(iscjzg()){
		Users user = null;
		if (userId == null) {
			user = Util.getLoginUser();
			userId = user.getId();
		} else {
			user = (Users) totalDao.get(Users.class, userId);
		}
		if (user != null && giftName != null && !"".equals(giftName)) {
			String hql = "from Gift where name =? and num>0";
			Gift gift = (Gift) totalDao.getObjectByCondition(hql, giftName);
			String hql1 = "from IgiftSet order by id desc";
			IgiftSet igSet = (IgiftSet) totalDao.getObjectByCondition(hql1);
			if(igSet!=null){
				String hql2 = "from IntegralGift where userId= "+userId +" and dhtime >= '"+igSet.getBeginTime() +"' and dhtime<= '" +igSet.getEndTime()+"'" ;
				IntegralGift igift1 = (IntegralGift) totalDao.getObjectByCondition(hql2);
				if(igift1!=null){
					return "抱歉，每人每次只有一次抽奖机会";
				}
			}
			if (gift != null) {
				IntegralGift igift = new IntegralGift();
				igift.setUserId(userId);// 中奖人userID
				igift.setUserCode(user.getCode());// z中奖人工号
				igift.setUserName(user.getName());// 中奖人姓名
				igift.setDept(user.getDept());// 中奖人部门
				igift.setLqnum(1);// 中奖数量
				igift.setGiftNmae(giftName);// 中奖物品名称
				igift.setType("抽奖");
				igift.setDjIntegral(50f);
				igift.setXaIntegral(50f);
				igift.setDhnum(Util.getrandomNum(8));// 8位随机兑换码
				igift.setIsdh("yes");
				igift.setDhtime(Util.getDateTime());
				igift.setDhstatus("未兑换");
				igift.setGiftId(gift.getId());
				if(giftName.indexOf("谢谢参与")<0){
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(user.getPassword().getPhoneNumber(), "您的兑换码为:"+igift.getDhnum()+"，可兑换"+giftName+"请于指定日期内兑换，过期无效!");
				}
				return addIntegralGift(igift,null) + "&" + igift.getDhnum();
			} else {
				return "ycw";
			}
		} else {
			return "抱歉，后台未获取数据";
		}
		}else{
			return "抱歉，您没有抽奖资格";
		}
	}

	@Override
	public String getpicture(String giftName) {
		String hql = " from Gift where name =? ";
		Gift gift = (Gift) totalDao.getObjectByCondition(hql, giftName);
		if (gift != null && gift.getPicture() != null
				&& !"".equals(gift.getPicture())) {
			return gift.getPicture();
		}
		return null;
	}

	// 根据userId 和兑换码 兑换奖品
	public Gift dhchoujiang(Integer userId, String dhnum) {
		String hql = "from Gift where id  in( select giftId from IntegralGift where userId=? and dhnum=? and getDate()-dhtime<15) ";
		return (Gift) totalDao.getObjectByCondition(hql, userId, dhnum);
	}

	@Override
	public String findZJIgift() {
		String hql = " from IntegralGift where type = ? order by id desc";
		List<IntegralGift> igift = totalDao.findAllByPage(hql, 1, 3, "抽奖");
		String msg = "";
		if (igift != null && igift.size() > 0) {
			for (int i = 0; i < igift.size(); i++) {
				IntegralGift ig = igift.get(i);
				if (ig.getGiftNmae().indexOf("谢谢参与") < 0) {
					msg += "恭喜"
							+ ig.getUserName()
							+ "于"
							+ ig.getDhtime().substring(0, 10)
							+ " 抽中"
							+ ig.getGiftNmae()
							+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
			}
		}
	
		return msg;
	}

	@Override
	public String addigSet(IgiftSet igSet) {
		if (igSet != null) {
			String year = Util.getDateTime("yyyy");
			String hql = "select max(qihao) from IgiftSet where qihao like '"+year+"%'";
			String qihao =	(String) totalDao.getObjectByCondition(hql);
			if(qihao == null || "".equals(qihao)){
				qihao = year+"00001";
			}else{
				int num =	Integer.parseInt(qihao);
				qihao = (num+1)+"";
			}
			igSet.setQihao(qihao);
			String time = Util.getSpecifiedDayAfter(Util.getDateTime(), 3);
			String begingtime = igSet.getBeginTime();
//			time += begingtime.substring(10);
//			long sc = Util.getWorkTime1(time, begingtime);
//			double s = Math.ceil(sc / 1000 / 60 / 60 / 24);
//			if (s < 0) {
//				return "抽奖开始时间应当在三天之后";
//			}
//			String endtime = igSet.getEndTime();
//			long sc1 = Util.getWorkTime1(begingtime, endtime);
//			double s1 = Math.ceil(sc1 / 1000 / 60);
//			double s2 = Math.ceil(sc1 / 1000 / 60 / 60);
//			if (s1 < 1) {
//				return "抽奖结束时间相距抽奖开始时间至少需要一分钟";
//			} else if (s2 > 3) {
//				return "抽奖结束时间相距抽奖开始时间不能超过三小时";
//			}
			return totalDao.save(igSet) + "";
		}

		return "为获取到相关数据";
	}

	@Override
	public boolean findigSet() {
		String hql = "from IgiftSet order by id desc";
		IgiftSet igSet = (IgiftSet) totalDao.getObjectByCondition(hql);
		if(igSet!=null){
			String begingtime = igSet.getBeginTime();
			String endtime = igSet.getEndTime();
			Date beging = Util.StringToDate(begingtime, "yyyy-MM-dd HH:mm:ss");
			Date end = Util.StringToDate(endtime, "yyyy-MM-dd HH:mm:ss");
			return Util.betweenTime(beging, end, new Date());
		}
		return false;
	}

	@Override
	public String Giveintegral(Integral integral) {
		if (integral != null) {
			String msg = IntegralServerDaoImpl.addIntegral1(integral);
			if ("true".equals(msg)) {
				List<Integralsource> islist = integral.getIsList();
				if (islist != null && islist.size() > 0) {
					Integer xiaofeijifen = islist.get(0).getAddintegral();
					String hql = "from Integral where userId =? and year =?";
					Integral integral1 = (Integral) totalDao
							.getObjectByCondition(hql, Util.getLoginUser()
									.getId(), Util.getDateTime("yyyy"));
					if (integral != null && integral.getTotalIntegral() > 0) {
						return "抱歉，积分不足，或尚未添加积分。";
					}
					List<XiaoFei> xfList = new ArrayList<XiaoFei>();
					XiaoFei xiaofei = new XiaoFei();
					xiaofei.setNeirong("赠送红心给" + integral.getIntegralName());
					xiaofei.setXiaofeijifen(xiaofeijifen);
					xfList.add(xiaofei);
					integral1.setXfList(xfList);
					return IntegralServerDaoImpl.updateIntegral1(integral1);
				}
			}
		}
		return "error";
	}

	@Override
	public String addindian(IndianaGift indianagift) {
		if(iscjzg()){
			Users user = Util.getLoginUser();
		if (indianagift != null && indianagift.getIgiftSet()!=null&&
				indianagift.getIgiftSet().getId()!=null) {
			IgiftSet igiftSet = (IgiftSet) totalDao.get(IgiftSet.class, indianagift.getIgiftSet().getId());
			
			indianagift.setCode(user.getCode());// 工号
			indianagift.setDept(user.getDept()); // 部门
			indianagift.setName(user.getName());// 姓名
			indianagift.setUserId(user.getId());// userId
		String hql3 = "from IndianaGift where userId=? and igiftSet.id = ?";
		IndianaGift indianaGift1 = (IndianaGift) totalDao.getObjectByCondition(
				hql3, indianagift.getUserId(), igiftSet.getId());
		if (indianaGift1 != null) {
			return "您已经报名参加过本次活动，无需再次报名。";
		}

		if (igiftSet != null
				&& igiftSet.getBeginTime() != null
				&& igiftSet.getEndTime() != null
				&& Util.betweenTime(Util.StringToDate(igiftSet.getBeginTime(),
						"yyyy-MM-dd HH:mm:ss"), Util.StringToDate(igiftSet
						.getEndTime(), "yyyy-MM-dd HH:mm:ss"), new Date())) {
			indianagift.setIgiftSet(igiftSet);
			indianagift.setJoinnum(Long.parseLong(Util
					.getDateTime("yyyyMMddHHmmss")));
			indianagift.setJointime(Util.getDateTime());// 报名时间
			String hql = "select max(num) from IndianaGift where igiftSet.id=?";
			String num = (String) totalDao.getObjectByCondition(hql,igiftSet.getId());

			if (num == null && !"".equals(num)) {
				num = "10000001";
				indianagift.setNum(num);
			} else {
				Integer a = Integer.parseInt(num);
				indianagift.setNum((a + 1) + "");
			}
			String hql_integral = "from Integral where userId =? and year =?";
			Integral integral = (Integral) totalDao.getObjectByCondition(
					hql_integral, indianagift.getUserId(), Util
							.getDateTime("yyyy"));
			if (integral == null || integral.getTotalIntegral() <= 0) {
				return "抱歉，积分不足，或尚未添加积分。";
			}
			if (totalDao.save(indianagift)) {

				List<XiaoFei> xfList = new ArrayList<XiaoFei>();
				XiaoFei xiaofei = new XiaoFei();
				xiaofei.setNeirong("夺宝");
				xiaofei.setXiaofeijifen(0);
				xfList.add(xiaofei);
				integral.setXfList(xfList);
				
				SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
				smsServiceImpl.send(user.getPassword().getPhoneNumber(), "您已成功报名期号为:"+igiftSet.getQihao()+"的活动，您的编号为:"+indianagift.getNum()+"时间数字为:"+indianagift.getJoinnum()+"。");
				
				return IntegralServerDaoImpl.updateIntegral1(integral);
			}
		} else {
			return "抱歉，不是活动时间。";
		}
		}
		}else{
			return "抱歉，您没有参与的活动的资格";
		}
		return "error";
	}

	@Override
	// 计算中奖编号;计算公式为:(50个时间之和+时时彩5位开奖数字（传过来的str）)%奖品总需人次+10000001 = 幸运号码
	public String jszjnum(String str,String qihao) {
		String zjnum = "";
		if (str == null || "".equals(str)) {
			str = "00000";
		}
		if(qihao!=null && !"".equals(qihao)){
		String hql1 = "from IgiftSet where qihao=? ";
		IgiftSet igiftSet = (IgiftSet) totalDao.getObjectByCondition(hql1,qihao);
		if(igiftSet == null){
			return "未找到期号:"+qihao+"的活动";
		}
		if(igiftSet!=null && igiftSet.getStatus()!=null
				&& !"已开奖".equals(igiftSet.getStatus())){
		String hql = "SELECT TOP 50  SUM (a.joinnum) sumjoinnum from (SELECT  top 50 * FROM ta_IndianaGift where igiftSet_id = "
				+ igiftSet.getId() + "  ORDER BY id DESC) a";
		List<Map> list = totalDao.findBySql(hql);
		BigDecimal sumjoinnum = null;
		if (list != null && list.size() > 0) {
			Map map = list.get(0);
			sumjoinnum = (BigDecimal) map.get("sumjoinnum");
		}
		long sum =	Long.parseLong((sumjoinnum+""))	 ;
		String hql_count = "select count(*) from IndianaGift where igiftSet.id = ?";
		float count = (Float) totalDao.getObjectByCondition(hql_count, igiftSet
				.getId());
		if (count < 0) {
			return "没有获取到参加活动的人数";
		}
		int count1 = (int) count;
		long a = Integer.parseInt(str) + sum;
		zjnum = a % count1 + 10000001 + "";
		igiftSet.setRandomnum(str);
		igiftSet.setStatus("已开奖");
		totalDao.update(igiftSet);
		return "true_"+zjnum;
		}else{
			return "本次活动已开过奖!";
		}
		}
		return "没有获取到活动期号";
	}

	@Override
	public String zjuser(String zjnum,String qihao) {
 		if (zjnum != null && !"".equals(zjnum)) {
			String hql = "from IgiftSet where qihao=?";
			IgiftSet igiftSet = (IgiftSet) totalDao.getObjectByCondition(hql,qihao);
			String hql1 = "from IndianaGift where num = ? and igiftSet.id=?";
			IndianaGift indianaGift = (IndianaGift) totalDao
					.getObjectByCondition(hql1, zjnum, igiftSet.getId());
			Gift gift = (Gift) totalDao.get(Gift.class, igiftSet.getGiftid());
			Users user = (Users) totalDao.get(Users.class, indianaGift.getUserId());
			SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
			smsServiceImpl.send(user.getPassword().getPhoneNumber(), "恭喜您为期号:"+igiftSet.getQihao()+"活动的，中奖人。稍后会把兑换码发给您。");
			choujiang(indianaGift.getUserId(), gift.getName());
			if (indianaGift != null) {
				
				return "中奖人为" + indianaGift.getDept() + "部门的"
						+ indianaGift.getName() + "工号为" + indianaGift.getCode()
						+ "。";
				
			}
		}
		return "中奖号码不能为空";
	}

	@Override
	public List<Gift> findgiftList() {
		String hql = " from Gift where num>0";
		return totalDao.find(hql);
	}

	@Override
	public List<IgiftSet> AlligSetList(int pageNo, int pageSize) {
		String time = Util.getDateTime();
		String hql = " from IgiftSet order by id desc";
		String hql1 = "from IgiftSet where  status is null and  beginTime<='"+time+"' and endTime >= '"+time+"'";
		List<IgiftSet> igSetList =	totalDao.find(hql1);
		if(igSetList!=null && igSetList.size()>0){
			for(int i=0;i<igSetList.size();i++){
				IgiftSet	igiftSet = igSetList.get(i);
				igiftSet.setStatus("可报名");
				totalDao.update(igiftSet);
			}
		}
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}
	
	public int getigSetcount(){
		String hql = " from IgiftSet order by id desc";
		return totalDao.getCount(hql);
	}

	@Override
	public Map<Integer, Object> findidgift(Integer igiftsetId, int pageNo,
			int pageSize) {
 		if(igiftsetId!=null && igiftsetId>0){
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			String hql = "from IndianaGift where igiftSet.id="+igiftsetId;
			List<IndianaGift>	 idgiftList =	totalDao.findAllByPage(hql, pageNo, pageSize);
			String hql_sum = "SELECT sum(joinnum) FROM IndianaGift WHERE igiftSet.id=?";
			Long sum =	(Long) totalDao.getObjectByCondition(hql_sum, igiftsetId);
			int count = 	totalDao.getCount(hql);
			String hql_count = "select count(*) from IndianaGift where igiftSet.id = ?";
	
			float 	peoplenum =	(Float) totalDao.getObjectByCondition(hql_count, igiftsetId);
			map.put(1, idgiftList);
			map.put(2, count);
			map.put(3, sum);
			map.put(4, (int)peoplenum);
			return map;
		}
		return null;
	}

	@Override//判断某人是否有抽奖资格
	public boolean iscjzg() {
		try {
			Users user = Util.getLoginUser();
			String onWork =	user.getOnWork();
			if(!"在职".equals(onWork) && !"是".equals(user.getInternal()) && user.getBanci_id()!=null){
				return false;
			}else{
				String time = Util.getDateTime();
				String befortime =	Util.getSpecifiedDayAfter(time,-90 );
				String hql_qj = "SELECT  SUM(leaveDays) FROM AskForLeave WHERE " +
						"leavePersonCode = '"+user.getCode()+"' and leaveTypeOf IN" +
								" ('病假','事假','换休','年休') and  leaveStartDate > '"
						+befortime+"' and leaveEndDate  < '"+time+"'";
				String hql = " from Integral where userId =? and year = ?";
				Integral integral =	(Integral) totalDao.getObjectByCondition(hql, user.getId(),Util.getDateTime("yyyy"));
				
				if(integral!=null && integral.getLaheitime()!=null && !"".equals(integral.getLaheitime())){
						 String lahetime = integral.getLaheitime();
						 long sc = Util.getWorkTime1(lahetime, time); 
						 double s = Math.ceil(sc / 1000 / 60 / 60 / 24);
						 if(s<=30 && s>0){
							 return false;
						 }else{
							 integral.setStatus("正常");
							 totalDao.update(integral);
						 }
				}
				Integer sumleaveDays =	(Integer) totalDao.getObjectByCondition(hql_qj);
				String hql_nx = " select standardAnnualLeave from AnnualLeave where jobNum=?";
				Integer nx =(Integer)totalDao.getObjectByCondition(hql_nx, user.getCode());
				if(sumleaveDays!=null && nx!=null && sumleaveDays>nx){
					return false;
				}
				String hql_kq = "from Attendance where userId = "+user.getId()+" and dateTime > '"+befortime+"' and attendanceStatus in ('迟到','早退','缺勤')";
				Attendance attendance =(Attendance) totalDao.getObjectByCondition(hql_kq);
				if(attendance!=null){
					return false;
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
