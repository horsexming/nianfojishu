package com.task.ServerImpl;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sun.text.normalizer.CharTrie.FriendAgent;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.IntegralServerDao;
import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.entity.banci.BanCi;
import com.task.util.Util;

public class IntegralServerDaoImpl implements IntegralServerDao {
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		IntegralServerDaoImpl acc = new IntegralServerDaoImpl();
		acc.setTotalDao(totalDao);
		return totalDao;
	}

	@Override
	public String addIntegral(Integral integral) {
		if (integral != null) {
			String hql="from Integral where userId=? and year=?";
			Integral integral1=(Integral) totalDao.getObjectByCondition(hql, integral.getUserId(),Util.getDateTime("yyyy"));
			if(integral1==null){
			List<Integralsource> isList = integral.getIsList();
			if (isList != null && isList.size() > 0) {
				Integer total = isList.get(0).getAddintegral();
				isList.get(0).setAddtime(Util.getDateTime());
				isList.get(0).setIn_code(integral.getIntegrcode());
				// isList.get(0).setIntegral(integral);
				Set<Integralsource> isSet = new HashSet<Integralsource>();
				isSet.add(isList.get(0));
				integral.setIs(isSet);
				if (total != null) {
					integral.setTotalIntegral(total);
					integral.setYear(Util.getDateTime("yyyy"));
					if( totalDao.save(integral)){
						return "true";
					};
				}
			}
			}
			return "该员工今年已添加过，无需添加";
		}
		return "后台没有获取到数据";
	}
	public static String addIntegral1(Integral integral) {//IntegralServerDaoImpl.addIntegral1
		if (integral != null) {
			TotalDao totalDao1 = createTotol();
			String hql="from Integral where userId=? and year=?";
			List<Integral> integralList=(List<Integral>) totalDao1.query(hql, integral.getUserId(),Util.getDateTime("yyyy"));
			if(integralList==null || integralList.size()==0){
				List<Integralsource> isList = integral.getIsList();
				if (isList != null && isList.size() > 0) {
					Integer total = isList.get(0).getAddintegral();
					isList.get(0).setAddtime(Util.getDateTime());
					isList.get(0).setIn_code(integral.getIntegrcode());
					// isList.get(0).setIntegral(integral);
					Set<Integralsource> isSet = new HashSet<Integralsource>();
					isSet.add(isList.get(0));
					integral.setIs(isSet);
					if (total != null) {
						integral.setTotalIntegral(total);
						integral.setYear(Util.getDateTime("yyyy"));
						if( totalDao1.save2(integral)){
							return "true";
						}
					}
				}
			}else{
				return  updateIntegral1( integral);
			}
			
		}
		return "后台没有获取到数据";
	}

	@Override
	public boolean delIntegral(Integral integral) {
		if (integral != null) {
			return totalDao.delete(integral);
		}
		return false;
	}

	@Override
	public List<Integral> fIndAll() {
		String hql = "from Integral where year= '"+Util.getDateTime("yyyy")+"'";
		return (List<Integral>) totalDao.find(hql);
	}

	@Override
	public List<Integral> findAllByPage(int pageNo, int pageSize) {
		String hql = "from Integral where year ='"+Util.getDateTime("yyyy")+"' ORDER BY integrdept";
		return (List<Integral>) totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	@Override
	public Integral findIntegralbyid(Integer id) {
		if (id != null) {
			return (Integral) totalDao.get(Integral.class, id);
		}
		return null;
	}

	@Override
	public int getcont() {
		String hql = "from Integral where year= '"+Util.getDateTime("yyyy")+"'";
		return totalDao.getCount(hql);
	}

	public static String updateIntegral1(Integral integral) {
		if (integral != null) {
			TotalDao totalDao1 = createTotol();
			List<Integral> IntegralList = (List<Integral>) totalDao1.query(
					"from Integral where integrcode=? and year=?", integral.getIntegrcode(),Util.getDateTime("yyyy"));
			if(IntegralList!=null && IntegralList.size()>0){
				Integral	integral2 = IntegralList.get(0);
			Integer totalIntegral = integral2.getTotalIntegral();
			List<Integralsource> isList = integral.getIsList();
			List<XiaoFei> xfList = integral.getXfList();
			if (isList != null && isList.size() > 0) {
				Integer AddIntegral = isList.get(0).getAddintegral();
				isList.get(0).setAddtime(Util.getDateTime());
				isList.get(0).setIn_code(integral2.getIntegrcode());
				// isList.get(0).setIntegral(integral2);
				integral2.getIs().add(isList.get(0));
				if (AddIntegral > 0) {
					totalIntegral += AddIntegral;
				} else {
					return "添加积分至少要比0大！";
				}
			} else if (xfList != null && xfList.size() > 0) {
				Integer Xiaofeijifen = xfList.get(0).getXiaofeijifen();
				xfList.get(0).setXiaofeitime(Util.getDateTime());
				xfList.get(0).setIn_code(integral2.getIntegrcode());
				// isList.get(0).setIntegral(integral2);
				integral2.getXf().add(xfList.get(0));
				List<XiaoFei> xfList2 = totalDao1.query(
						"from XiaoFei where in_code=? order by id desc",
						integral2.getIntegrcode());
				
				if (Xiaofeijifen >= 0) {
					totalIntegral -= Xiaofeijifen;
				} else {
					return "消费积分不能为负";
				}
				int NowMonth1 = Integer.parseInt(Util.getDateTime().substring(5,
						7));
				Integer sumxf = integral2.getSumxf();
				if (sumxf == null) {
					sumxf = Xiaofeijifen;
				} else if (xfList2 != null && xfList2.size() > 1) {
					int xfMonth = Integer.parseInt(xfList2.get(1)
							.getXiaofeitime().substring(5, 7));
					if (NowMonth1 == xfMonth) {
						sumxf += Xiaofeijifen;
					} else if (NowMonth1 - xfMonth == 1) {
						sumxf = Xiaofeijifen;
					}
				} else {
					sumxf = Xiaofeijifen;
				}
				integral2.setSumxf(sumxf);
			}
			integral2.setTotalIntegral(totalIntegral);
			if (totalDao1.update2(integral2)) {
				return "true";
			}
		}
		}
		return "更新失败！";
	}

	/**
	 * 
	 * @param accessType 扣除积分原因
	 * @param xmkf_1  消费的积分数
	 * @param integral 积分对象
	 * @return 扣除结果
	 */
	public static String kouJiFen(String accessType,int xmkf_1,Integral integral){
		if (integral == null || integral.getTotalIntegral() == null || integral.getTotalIntegral() == 0) return "积分为空，扣除失败！";
		XiaoFei xf = new XiaoFei();
		xf.setNeirong(accessType);
		xf.setXiaofeijifen(xmkf_1);
		List<XiaoFei> xflList = new ArrayList<XiaoFei>();
		xflList.add(xf);
		integral.setXfList(xflList);
		return updateIntegral1(integral);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, Object> findIntegralByCondition(Integral integral,
			int pageNo, int pageSize) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(integral, null);
		hql+=" and year = '"+Util.getDateTime("yyyy")+"'";
		int count = totalDao.getCount(hql);
		
		List<Integral> IntegralList = (List<Integral>) totalDao.findAllByPage(
				hql, pageNo, pageSize);
		map.put(1, IntegralList);
		map.put(2, count);
		return map;
	}

	@Override
	public List<XiaoFei> getXiaoFeis(Integral integral) {
		if (integral != null) {
			Integral integral2 = (Integral) totalDao.get(Integral.class,
					integral.getId());
			List<XiaoFei> list = new ArrayList<XiaoFei>(integral2.getXf());
			return list;
		}
		return null;
	}

	@Override
	public List<Integralsource> getiIntegralsources(Integral integral) {
		if (integral != null) {
			Integral integral2 = (Integral) totalDao.get(Integral.class,
					integral.getId());
			List<Integralsource> list = new ArrayList<Integralsource>(integral2
					.getIs());
			return list;
		}
		return null;
	}

	@Override
	public String updateIntegral(Integral integral) {
		if (integral != null) {
			Integral integral2 = (Integral) totalDao.get(Integral.class,
					integral.getId());
			Integer totalIntegral = integral2.getTotalIntegral();
			List<Integralsource> isList = integral.getIsList();
			List<XiaoFei> xfList = integral.getXfList();
			if (isList != null && isList.size() > 0) {
				Integer AddIntegral = isList.get(0).getAddintegral();
				isList.get(0).setAddtime(Util.getDateTime());
				isList.get(0).setIn_code(integral2.getIntegrcode());
				// isList.get(0).setIntegral(integral2);
				integral2.getIs().add(isList.get(0));
				if (AddIntegral > 0) {
					totalIntegral += AddIntegral;
				} else {
					return "添加积分至少要比0大！";
				}
			} else if (xfList != null && xfList.size() > 0) {
				Integer Xiaofeijifen = xfList.get(0).getXiaofeijifen();
				xfList.get(0).setXiaofeitime(Util.getDateTime());
				xfList.get(0).setIn_code(integral2.getIntegrcode());
				// isList.get(0).setIntegral(integral2);
				integral2.getXf().add(xfList.get(0));
				int NowMonth = Integer.parseInt(Util.getDateTime().substring(5,
						7));
				integral2.getXf().add(xfList.get(0));
				List<XiaoFei> xfList2 = totalDao.query(
						"from XiaoFei where in_code=? order by id desc",
						integral2.getIntegrcode());
				Integer sumxf = integral2.getSumxf();
				if (sumxf == null) {
					sumxf = Xiaofeijifen;
				} else if (xfList2 != null && xfList2.size() > 1) {
					int xfMonth = Integer.parseInt(xfList2.get(1)
							.getXiaofeitime().substring(5, 7));
					if (NowMonth == xfMonth) {
						sumxf += Xiaofeijifen;
					} else if (NowMonth - xfMonth == 1) {
						sumxf = Xiaofeijifen;
					}
				} else {
					sumxf = Xiaofeijifen;
				}
				if (Xiaofeijifen >= 0) {
					totalIntegral -= Xiaofeijifen;
				} else {
					return "消费积分不能为空";
				}
				integral2.setSumxf(sumxf);
				integral2.setXfmonth(Util.getDateTime("yyyy-MM"));
			}
			integral2.setTotalIntegral(totalIntegral);
			if (totalDao.update(integral2)) {
				return "true";
			}
		}
		return "更新失败！";
	}

	
//	public String addIntegral(Integer jifen, Integer userId, String laiyuan) {
//		if (jifen != null && userId != null && jifen > 0) {
//			Integral integral = (Integral) totalDao.getObjectByCondition(
//					"from Integral where userId=? and year=?", userId,Util.getDateTime("yyyy"));
//			Integralsource is = new Integralsource();
//			is.setAddintegral(jifen);
//			is.setLaiyuan(laiyuan);
//			List<Integralsource> isList = new ArrayList<Integralsource>();
//			isList.add(is);
//			integral.setIsList(isList);
//			if (integral != null) {
//				return updateIntegral(integral);
//
//			} else {
//				Users user = (Users) totalDao.get(Users.class, userId);
//				integral.setIntegrcode(user.getCode());
//				integral.setIntegrdept(user.getDept());
//				integral.setIntegralName(user.getName());
//				integral.setUserId(userId);
//				integral.setYear(Util.getDateTime("yyyy"));
//				if (addIntegral(integral)) {
//					return "新添加积分项成功";
//				}
//			}
//
//		}
//		return "error";
//	}

	/**
	 * 计算出待在休息室的时间(分为上班时间和下班时间), 并根据不同的时间段，采用不同的消耗积分算法;
	 * 
	 * @throws Exception
	 * 
	 */
	public static Integer xhjf(String start, Integer userId) throws Exception {
		
		String end = Util.getDateTime();
		TotalDao totalDao1 = createTotol();

		Integral integral = (Integral) totalDao1.query(
				"from Integral where userId=? and year=?", userId,Util.getDateTime("yyyy")).get(0);
		XiaoFei xf = new XiaoFei();
		List<XiaoFei> xfList = new ArrayList<XiaoFei>();
		xf.setNeirong("进门");
		Integer xfjf=0;
		long time = Util.getWorkTime1(start, end);
		if (start != null && end != null && start.length() > 0
				&& end.length() > 0 && userId != null) {
			if (time <= 1000 * 60 * 3) {
				xfjf=0;
			} else {

				Users user = (Users) totalDao1.query("from Users where id=?",
						userId).get(0);
				BanCi banci = (BanCi) totalDao1.query("from BanCi where id=?",
						user.getBanci_id()).get(0);
				if (banci != null) {
					String[] sbdate = banci.getSbdate().split(",");// 上班日期(星期几);
					String startdate = Util.dayForWeek(start, null);// 进入休息室的时间为星期几
					String enddate = Util.dayForWeek(end, null);// 离开休息室的时间为星期几
					boolean bool1 = false;
					boolean bool2 = false;
					for (int i = 0; i < sbdate.length; i++) {
						if (startdate.equals(sbdate[i].trim())) {
							bool1 = true;
						}
						if (enddate.equals(sbdate[i].trim())) {
							bool2 = true;
						}
					}
					String now = Util.getDateTime("yyyy-MM-dd");
					String nowweek = Util.getDateTime("EEEE");// 当前时间为星期几;
					Date firsttime = Util.StringToDate(banci.getFirsttime(),
							"HH:mm:ss");// 上班开始时间
					Date wxstarttime = null;// 午休开始时间;
					Date wxendtime = null;// 午休结束时间;
					Date finishtime = null;// 上班结束时间;
					String first = now + " " + banci.getFirsttime();// 白班情况下,当天上班开始时间(年月日，时分秒)
					String finish = now + " " + banci.getFinishtime();// 白班情况下,当天上班结束时间(年月日，时分秒)
					String wxstart = now + " " + banci.getWxstarttime();// 白班情况下,当天午休开始时间(年月日，时分秒)
					String wxend = now + " " + banci.getWxendtime();// 白班情况下,当天午休结束时间(年月日，时分秒)

					if (Util.compareTime(banci.getFirsttime(), "HH:mm:ss",
							banci.getFinishtime(), "HH:mm:ss")) {
						// Date nowtime =
						// Util.StringToDate(Util.getDateTime("HH:mm:ss"),
						// "HH:mm:ss");
						Date wxstartDate = Util.StringToDate(banci
								.getWxstarttime(), "HH:mm:ss");
						Date wxendDate = Util.StringToDate(
								banci.getWxendtime(), "HH:mm:ss");
						Date lingdian = Util.StringToDate("23:59:59",
								"HH:mm:ss");// 零点;
						Date nowtime = Util.StringToDate(Util
								.getDateTime("HH:mm:ss"), "HH:mm:ss");
						long date1 = Util.getWorkTime(wxstartDate, lingdian);
						long date2 = Util.getWorkTime(wxendDate, lingdian);
						long date3 = Util.getWorkTime(firsttime, lingdian);
						long date4 = Util.getWorkTime(nowtime, lingdian);
						if (bool1 && startdate.equals(nowweek) && date4 < date3) {// 如果当前时间在前半夜;
							String tomorrow = Util.getSpecifiedDayAfter(now, 1);
							first = now + " " + banci.getFirsttime();
							finish = tomorrow + " " + banci.getFinishtime();
							if (date1 > date3) {// 当零点到午休开始时间时间段大于零点到上班开始时间段，则认为午休开始时间在第二天,即夜班后半夜;
								wxstart = tomorrow + " " + banci.getFirsttime();
								wxend = tomorrow + " " + banci.getFinishtime();
							} else if (date1 < date3 && date2 > date3) {
								// 当零点到午休时间时间段下于零点至上班开始时间段，且零点到午休结束时间时间段大于零点到上班开始时间段;
								// 则认为，午休开始时间在前一天（夜班前半夜），而午休结束时间后一天（夜班后半夜）;
								wxstart = now + " " + banci.getWxstarttime();
								wxend = tomorrow + " " + banci.getWxendtime();
							} else if (date2 < date3) {
								// 当零点到午休结束时间时间段小于零点到上班开始时间段，则认为午休结束时间在零点之前，即夜班前半夜;
								wxstart = now + " " + banci.getWxstarttime();
								wxend = now + " " + banci.getWxendtime();
							}
						} else if (bool2 && enddate.equals(nowweek)
								&& date4 > date3) {// 如果当前时间在后半夜;
							String befroe = Util.getSpecifiedDayAfter(now, -1);
							first = befroe + " " + banci.getFirsttime();
							finish = now + " " + banci.getFinishtime();
							if (date1 > date3) {// 当零点到午休开始时间时间段大于零点到上班开始时间段，则认为午休开始时间在第二天,即夜班后半夜;
								wxstart = now + " " + banci.getFirsttime();
								wxend = now + " " + banci.getFinishtime();
							} else if (date1 < date3 && date2 > date3) {
								// 当零点到午休时间时间段下于零点至上班开始时间段，且零点到午休结束时间时间段大于零点到上班开始时间段;
								// 则认为，午休开始时间在前一天（夜班前半夜），而午休结束时间后一天（夜班后半夜）;
								wxstart = befroe + " " + banci.getWxstarttime();
								wxend = now + " " + banci.getWxendtime();
							} else if (date2 < date3) {
								// 当零点到午休结束时间时间段小于零点到上班开始时间段，则认为午休结束时间在零点之前，即夜班前半夜;
								wxstart = befroe + " " + banci.getWxstarttime();
								wxend = befroe + " " + banci.getWxendtime();
							}
						}
					}
					firsttime = Util.StringToDate(first, null);
					finishtime = Util.StringToDate(finish, null);
					wxstarttime = Util.StringToDate(wxstart, null);
					wxendtime = Util.StringToDate(wxend, null);
					Date startime = Util.StringToDate(start, null);// 进入TeaRoom的开始时间；
					Date endtime = Util.StringToDate(end, null);// 进入TeaRoom的结束时间
					if (bool1 && bool2) {
						if (!Util.betweenTime(firsttime, wxstarttime, startime)
								&& Util.betweenTime(firsttime, wxstarttime,
										endtime)) {
							// 当进入休息室的开始时间不再前一段上班时间内，结束时间在前一段上班时间内;
							long time1 = Util.getWorkTime1(start, first);
							long time2 = Util.getWorkTime1(first, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 1;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 10;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(firsttime, wxstarttime,
								startime)
								&& Util.betweenTime(firsttime, wxstarttime,
										endtime)) {
							// 当进入休息室的开始时间和结束时间都在上班时间;
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 10;
						} else if (Util.betweenTime(firsttime, wxstarttime,
								startime)
								&& Util.betweenTime(wxstarttime, wxendtime,
										endtime)) {
							// 当进入休息室的开始时间在前一段上班时间内结束时间在休息时间段内;
							long time1 = Util.getWorkTime1(start, wxstart);

							long time2 = Util.getWorkTime1(wxstart, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(wxstarttime, wxendtime,
								startime)
								&& Util.betweenTime(wxstarttime, wxendtime,
										endtime)) {
							// 当进入休息室的开始时间和结束时间都在休息时间段内;
							xfjf=(int)((time/1000/60)-3+1)*1;
						} else if (Util.betweenTime(wxstarttime, wxendtime,
								startime)
								&& Util.betweenTime(wxendtime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间在休息时间段内结束时间在下一段上班时间内;
							long time1 = Util.getWorkTime1(start, wxend);
							long time2 = Util.getWorkTime1(wxend, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 1;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 10;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(wxendtime, finishtime,
								startime)
								&& Util.betweenTime(wxendtime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间和结束都在下一段上班时间内;
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 10;
						} else if (Util.betweenTime(wxendtime, finishtime,
								startime)
								&& !Util.betweenTime(firsttime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间在下一段上班时间内，结束时间在下班时间；
							long time1 = Util.getWorkTime1(start, finish);
							long time2 = Util.getWorkTime1(finish, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(firsttime, finishtime,
								startime)
								&& Util.betweenTime(finishtime, finishtime,
										endtime)) {
							long time1 = Util.getWorkTime1(start, wxstart);
							long time2 = Util.getWorkTime1(wxstart, wxend);
							long time3 = Util.getWorkTime1(wxend, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							long xfjf3 = ((time3 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2 + xfjf3);
						} else {
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 1;
						}
					} else {
						xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 1;
						
					}
				}

			}
		}

		xf.setXiaofeijifen(xfjf);
		xfList.add(xf);
		integral.setXfList(xfList);
		updateIntegral1(integral);
		return xfjf;
	}

	@Override
	public void xhjf2(String start, Integer userId) throws Exception {
		String end = Util.getDateTime();
		TotalDao totalDao1 = createTotol();

		Integral integral = (Integral) totalDao1.query(
				"from Integral where userId=?", userId).get(0);
		XiaoFei xf = new XiaoFei();
		List<XiaoFei> xfList = new ArrayList<XiaoFei>();
		xf.setNeirong("进门");
		Integer xfjf=0;
		long time = Util.getWorkTime1(start, end);
		if (start != null && end != null && start.length() > 0
				&& end.length() > 0 && userId != null) {
			if (time <= 1000 * 60 * 3) {
				xfjf=0;
			} else {

				Users user = (Users) totalDao1.query("from Users where id=?",
						userId).get(0);
				BanCi banci = (BanCi) totalDao1.query("from BanCi where id=?",
						user.getBanci_id()).get(0);
				if (banci != null) {
					String[] sbdate = banci.getSbdate().split(",");// 上班日期(星期几);
					String startdate = Util.dayForWeek(start, null);// 进入休息室的时间为星期几
					String enddate = Util.dayForWeek(end, null);// 离开休息室的时间为星期几
					boolean bool1 = false;
					boolean bool2 = false;
					for (int i = 0; i < sbdate.length; i++) {
						if (startdate.equals(sbdate[i].trim())) {
							bool1 = true;
						}
						if (enddate.equals(sbdate[i].trim())) {
							bool2 = true;
						}
					}
					String now = Util.getDateTime("yyyy-MM-dd");
					String nowweek = Util.getDateTime("EEEE");// 当前时间为星期几;
					Date firsttime = Util.StringToDate(banci.getFirsttime(),
							"HH:mm:ss");// 上班开始时间
					Date wxstarttime = null;// 午休开始时间;
					Date wxendtime = null;// 午休结束时间;
					Date finishtime = null;// 上班结束时间;
					String first = now + " " + banci.getFirsttime();// 白班情况下,当天上班开始时间(年月日，时分秒)
					String finish = now + " " + banci.getFinishtime();// 白班情况下,当天上班结束时间(年月日，时分秒)
					String wxstart = now + " " + banci.getWxstarttime();// 白班情况下,当天午休开始时间(年月日，时分秒)
					String wxend = now + " " + banci.getWxendtime();// 白班情况下,当天午休结束时间(年月日，时分秒)

					if (Util.compareTime(banci.getFirsttime(), "HH:mm:ss",
							banci.getFinishtime(), "HH:mm:ss")) {
						// Date nowtime =
						// Util.StringToDate(Util.getDateTime("HH:mm:ss"),
						// "HH:mm:ss");
						Date wxstartDate = Util.StringToDate(banci
								.getWxstarttime(), "HH:mm:ss");
						Date wxendDate = Util.StringToDate(
								banci.getWxendtime(), "HH:mm:ss");
						Date lingdian = Util.StringToDate("23:59:59",
								"HH:mm:ss");// 零点;
						Date nowtime = Util.StringToDate(Util
								.getDateTime("HH:mm:ss"), "HH:mm:ss");
						long date1 = Util.getWorkTime(wxstartDate, lingdian);
						long date2 = Util.getWorkTime(wxendDate, lingdian);
						long date3 = Util.getWorkTime(firsttime, lingdian);
						long date4 = Util.getWorkTime(nowtime, lingdian);
						if (bool1 && startdate.equals(nowweek) && date4 < date3) {// 如果当前时间在前半夜;
							String tomorrow = Util.getSpecifiedDayAfter(now, 1);
							first = now + " " + banci.getFirsttime();
							finish = tomorrow + " " + banci.getFinishtime();
							if (date1 > date3) {// 当零点到午休开始时间时间段大于零点到上班开始时间段，则认为午休开始时间在第二天,即夜班后半夜;
								wxstart = tomorrow + " " + banci.getFirsttime();
								wxend = tomorrow + " " + banci.getFinishtime();
							} else if (date1 < date3 && date2 > date3) {
								// 当零点到午休时间时间段下于零点至上班开始时间段，且零点到午休结束时间时间段大于零点到上班开始时间段;
								// 则认为，午休开始时间在前一天（夜班前半夜），而午休结束时间后一天（夜班后半夜）;
								wxstart = now + " " + banci.getWxstarttime();
								wxend = tomorrow + " " + banci.getWxendtime();
							} else if (date2 < date3) {
								// 当零点到午休结束时间时间段小于零点到上班开始时间段，则认为午休结束时间在零点之前，即夜班前半夜;
								wxstart = now + " " + banci.getWxstarttime();
								wxend = now + " " + banci.getWxendtime();
							}
						} else if (bool2 && enddate.equals(nowweek)
								&& date4 > date3) {// 如果当前时间在后半夜;
							String befroe = Util.getSpecifiedDayAfter(now, -1);
							first = befroe + " " + banci.getFirsttime();
							finish = now + " " + banci.getFinishtime();
							if (date1 > date3) {// 当零点到午休开始时间时间段大于零点到上班开始时间段，则认为午休开始时间在第二天,即夜班后半夜;
								wxstart = now + " " + banci.getFirsttime();
								wxend = now + " " + banci.getFinishtime();
							} else if (date1 < date3 && date2 > date3) {
								// 当零点到午休时间时间段下于零点至上班开始时间段，且零点到午休结束时间时间段大于零点到上班开始时间段;
								// 则认为，午休开始时间在前一天（夜班前半夜），而午休结束时间后一天（夜班后半夜）;
								wxstart = befroe + " " + banci.getWxstarttime();
								wxend = now + " " + banci.getWxendtime();
							} else if (date2 < date3) {
								// 当零点到午休结束时间时间段小于零点到上班开始时间段，则认为午休结束时间在零点之前，即夜班前半夜;
								wxstart = befroe + " " + banci.getWxstarttime();
								wxend = befroe + " " + banci.getWxendtime();
							}
						}
					}
					firsttime = Util.StringToDate(first, null);
					finishtime = Util.StringToDate(finish, null);
					wxstarttime = Util.StringToDate(wxstart, null);
					wxendtime = Util.StringToDate(wxend, null);
					Date startime = Util.StringToDate(start, null);// 进入TeaRoom的开始时间；
					Date endtime = Util.StringToDate(end, null);// 进入TeaRoom的结束时间
					if (bool1 && bool2) {
						if (!Util.betweenTime(firsttime, wxstarttime, startime)
								&& Util.betweenTime(firsttime, wxstarttime,
										endtime)) {
							// 当进入休息室的开始时间不再前一段上班时间内，结束时间在前一段上班时间内;
							long time1 = Util.getWorkTime1(start, first);
							long time2 = Util.getWorkTime1(first, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 1;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 10;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(firsttime, wxstarttime,
								startime)
								&& Util.betweenTime(firsttime, wxstarttime,
										endtime)) {
							// 当进入休息室的开始时间和结束时间都在上班时间;
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 10;
						} else if (Util.betweenTime(firsttime, wxstarttime,
								startime)
								&& Util.betweenTime(wxstarttime, wxendtime,
										endtime)) {
							// 当进入休息室的开始时间在前一段上班时间内结束时间在休息时间段内;
							long time1 = Util.getWorkTime1(start, wxstart);

							long time2 = Util.getWorkTime1(wxstart, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(wxstarttime, wxendtime,
								startime)
								&& Util.betweenTime(wxstarttime, wxendtime,
										endtime)) {
							// 当进入休息室的开始时间和结束时间都在休息时间段内;
							xfjf=(int)((time/1000/60)-3+1)*1;
						} else if (Util.betweenTime(wxstarttime, wxendtime,
								startime)
								&& Util.betweenTime(wxendtime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间在休息时间段内结束时间在下一段上班时间内;
							long time1 = Util.getWorkTime1(start, wxend);
							long time2 = Util.getWorkTime1(wxend, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 1;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 10;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(wxendtime, finishtime,
								startime)
								&& Util.betweenTime(wxendtime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间和结束都在下一段上班时间内;
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 10;
						} else if (Util.betweenTime(wxendtime, finishtime,
								startime)
								&& !Util.betweenTime(firsttime, finishtime,
										endtime)) {
							// 当进入休息室的开始时间在下一段上班时间内，结束时间在下班时间；
							long time1 = Util.getWorkTime1(start, finish);
							long time2 = Util.getWorkTime1(finish, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2);
						} else if (Util.betweenTime(firsttime, finishtime,
								startime)
								&& Util.betweenTime(finishtime, finishtime,
										endtime)) {
							long time1 = Util.getWorkTime1(start, wxstart);
							long time2 = Util.getWorkTime1(wxstart, wxend);
							long time3 = Util.getWorkTime1(wxend, end);
							long xfjf1 = ((time1 / 1000 / 60) - 3 + 1) * 10;
							long xfjf2 = ((time2 / 1000 / 60) - 3 + 1) * 1;
							long xfjf3 = ((time3 / 1000 / 60) - 3 + 1) * 1;
							xfjf=(int) (xfjf1 + xfjf2 + xfjf3);
						} else {
							xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 1;
						}
					} else {
						xfjf=(int) ((time / 1000 / 60) - 3 + 1) * 1;
						
					}
				}

			}
		}

		xf.setXiaofeijifen(xfjf);
		xfList.add(xf);
		integral.setXfList(xfList);
		updateIntegral(integral);;
	}

	@Override
	public boolean laheiIntegral(Integer id) {
		if(id!=null && id>0){
			Integral integral =	(Integral) totalDao.get(Integral.class,id);
			integral.setStatus("拉黑");
			integral.setLaheitime(Util.getDateTime());
			return totalDao.update(integral);
		}
		return false;
	}

}
