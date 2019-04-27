package com.task.ServerImpl.parking;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.sun.org.apache.regexp.internal.recompile;
import com.task.Dao.TotalDao;
import com.task.Server.parking.ParkSpaceServer;
import com.task.ServerImpl.SmsServiceImpl;
import com.task.entity.Users;
import com.task.entity.parking.ParkSpace;
import com.task.entity.parking.ParkSpaceUseInfor;
import com.task.entity.parking.SimCarkTel;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class ParkSpaceServerImpl implements ParkSpaceServer {
	private TotalDao totalDao;

	@Override
	public ParkSpace ByidParkSpace(Integer integer) {
		// TODO Auto-generated method stub
		return (ParkSpace) totalDao.getObjectById(ParkSpace.class, integer);
	}

	@Override
	public String addParkSpace(ParkSpace parkSpace) {
		// TODO Auto-generated method stub
		ParkSpace parkSpace2 = (ParkSpace) totalDao.getObjectByCondition(
				"from ParkSpace where parkNum=? and parkingLot=?", parkSpace
						.getParkNum(), parkSpace.getParkingLot());
		if (parkSpace2 != null) {
			return "车位编号重复，添加失败！";
		}
		parkSpace.setAddTime(Util.getDateTime());
		parkSpace.setParkStatus("关闭");
		if (totalDao.save(parkSpace))
			return "true";
		else
			return "添加失败";
	}

	@Override
	public String deleteAccess(ParkSpace parkSpace) {
		// TODO Auto-generated method stub
		if (parkSpace != null) {
			if (!"开启".equals(parkSpace.getParkStatus())) {
				if (totalDao.delete(parkSpace))
					return "true";
				else
					return "删除失败！";
			} else
				return "车位为开启状态，删除失败！";
		} else
			return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findParkSpaceByCondition(ParkSpace parkSpace,
			int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (parkSpace == null) {
			parkSpace = new ParkSpace();
		}
		String sql = "";
		if ("show".equals(tag)) {
			Users users = Util.getLoginUser();
			sql = " vipName = '" + users.getName() + "' and vipCode = '"
					+ users.getCode() + "'";
		}
		String hql = totalDao.criteriaQueries(parkSpace, sql);
		hql += " order by parkNum";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateAccess(ParkSpace parkSpace, Integer id) {
		// TODO Auto-generated method stub
		// 获取开关人信息 判断是否可以打开
		Users users = Util.getLoginUser();
		if ("关闭".equals(parkSpace.getParkStatus())) {
			if ("员工".equals(parkSpace.getParkType())) {
				parkSpace.setUsePeople(users.getName());
				parkSpace.setUsePeopleCode(users.getCode());
				parkSpace.setParkStatus("打开");
			} else if ("VIP".equals(parkSpace.getParkType())) {
				if (users.getName().equals(parkSpace.getVipName())
						|| "是".equals(users.getParkAdmin())) {
					parkSpace.setUsePeople(users.getName());
					parkSpace.setUsePeopleCode(users.getCode());
					parkSpace.setParkStatus("打开");
				} else {
					return "没有此车位打开权限";
				}
			} else if ("来访".equals(parkSpace.getParkType())) {
				parkSpace.setUsePeople(users.getName());
				parkSpace.setUsePeopleCode(users.getCode());
				parkSpace.setParkStatus("打开");
			} else {
			}
		} else if ("打开".equals(parkSpace.getParkStatus())) {
			if (users.getName().equals(parkSpace.getUsePeople())
					|| "是".equals(users.getParkAdmin())) {
				parkSpace.setParkStatus("关闭");
			} else {
				return "当前" + parkSpace.getUsePeople() + "正在使用该车位，您没有关闭权限";
			}
		} else {
			return "车位状态错误，操作失败！";
		}
		String message = openCW(parkSpace.getParkIP(), parkSpace.getParkPort(),
				id);
		if ("true".equals(message)) {
			if (totalDao.update(parkSpace)) {
				if ("打开".equals(parkSpace.getParkStatus())) {
					ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
					infor.setAddTime(Util.getDateTime());
					infor.setParkId(parkSpace.getId());
					infor.setUseName(users.getName());
					infor.setUseCode(users.getCode());
					infor.setUseStatus("使用中");
					if (totalDao.save2(infor)) {
						parkSpace.setInforId(infor.getId());
						totalDao.update2(parkSpace);
					}
				} else if ("关闭".equals(parkSpace.getParkStatus())) {
					ParkSpaceUseInfor infor = (ParkSpaceUseInfor) totalDao
							.getObjectById(ParkSpaceUseInfor.class, parkSpace
									.getInforId());
					if (infor != null) {
						String nowTime = Util.getDateTime();
						infor.setUpdateTime(nowTime);
						infor.setCloseName(users.getName());
						infor.setCloseCode(users.getCode());
						infor.setUseStatus("已结束");
						long l = Util.getWorkTime1(infor.getAddTime(), nowTime);
						infor.setUseTime(Util.formatDuring(l));
						if (totalDao.update2(infor)) {
							parkSpace.setUsePeople(null);
							parkSpace.setUsePeopleCode(null);
							totalDao.update2(parkSpace);
						}
					}
				}
			} else {
				return "车位状态保存失败，请检查网络！";
			}
		}
		return message;
	}

	@Override
	public String updateAccessPhone(ParkSpace parkSpace, Integer id,
			String userid, String userName, String userCode) {
		// TODO Auto-generated method stub
		// 获取开关人信息 判断是否可以打开
		Users users = (Users) totalDao.getObjectById(Users.class, Integer
				.parseInt(userid));
		if (users != null && userName.equals(users.getName())
				&& userCode.equals(users.getCode())) {
			// 判断蓝牙地址是否为空
			if ("关闭".equals(parkSpace.getParkStatus())) {
				if ("员工".equals(parkSpace.getParkType())) {
					parkSpace.setUsePeople(users.getName());
					parkSpace.setUsePeopleCode(users.getCode());
					parkSpace.setParkStatus("打开");
				} else if ("VIP".equals(parkSpace.getParkType())) {
					if (users.getName().equals(parkSpace.getVipName())
							|| "是".equals(users.getParkAdmin())) {
						parkSpace.setUsePeople(users.getName());
						parkSpace.setUsePeopleCode(users.getCode());
						parkSpace.setParkStatus("打开");
					} else
						return "没有此车位打开权限";
				} else if ("来访".equals(parkSpace.getParkType())) {
					parkSpace.setUsePeople(users.getName());
					parkSpace.setUsePeopleCode(users.getCode());
					parkSpace.setParkStatus("打开");
				} else {
				}
			} else if ("打开".equals(parkSpace.getParkStatus())) {
				if (users.getName().equals(parkSpace.getUsePeople())
						|| "是".equals(users.getParkAdmin()))
					parkSpace.setParkStatus("关闭");
				else
					return "当前" + parkSpace.getUsePeople() + "正在使用该车位，您没有关闭权限";
			} else
				return "车位状态错误，操作失败！";
			/****************************** 打开 **************************************/
			String message = openCW(parkSpace.getParkIP(), parkSpace
					.getParkPort(), id);
			if ("true".equals(message)) {
				if (totalDao.update(parkSpace)) {
					if ("打开".equals(parkSpace.getParkStatus())) {
						ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
						infor.setAddTime(Util.getDateTime());
						infor.setParkId(parkSpace.getId());
						infor.setUseName(users.getName());
						infor.setUseCode(users.getCode());
						infor.setUseStatus("使用中");
						if (totalDao.save2(infor)) {
							parkSpace.setInforId(infor.getId());
							totalDao.update2(parkSpace);
						}
					} else if ("关闭".equals(parkSpace.getParkStatus())) {
						ParkSpaceUseInfor infor = (ParkSpaceUseInfor) totalDao
								.getObjectById(ParkSpaceUseInfor.class,
										parkSpace.getInforId());
						if (infor != null) {
							String nowTime = Util.getDateTime();
							infor.setUpdateTime(nowTime);
							infor.setCloseName(users.getName());
							infor.setCloseCode(users.getCode());
							infor.setUseStatus("已结束");
							long l = Util.getWorkTime1(infor.getAddTime(),
									nowTime);
							infor.setUseTime(Util.formatDuring(l));
							if (totalDao.update2(infor)) {
								parkSpace.setUsePeople(null);
								parkSpace.setUsePeopleCode(null);
								totalDao.update2(parkSpace);
							}
						}
					}
				} else
					return "车位状态保存失败，请检查网络！";
			}
			return message;
		}
		return "用户信息不符";
	}


	@Override
	public String updateAccessTelPhone(ParkSpace parkSpace, Integer id) {
		// TODO Auto-generated method stub
		// 判断蓝牙地址是否为空
		if ("关闭".equals(parkSpace.getParkStatus())) {
			if ("员工".equals(parkSpace.getParkType())) {
			} else if ("VIP".equals(parkSpace.getParkType())) {
			} else if ("来访".equals(parkSpace.getParkType())) {
				parkSpace.setUsePeople(parkSpace.getVisitName());
				parkSpace.setUsePeopleCode(parkSpace.getUserTelNum());
				parkSpace.setParkStatus("打开");
			} else {
			}
		} else if ("打开".equals(parkSpace.getParkStatus())) {
			if (parkSpace.getVisitName().equals(parkSpace.getUsePeople()))
				parkSpace.setParkStatus("关闭");
			else
				return "当前" + parkSpace.getUsePeople() + "正在使用该车位，您没有关闭权限";
		} else
			return "车位状态错误，操作失败！";
		/****************************** 打开 **************************************/
		String message = openCW(parkSpace.getParkIP(), parkSpace
				.getParkPort(), id);
		if ("true".equals(message)) {
			if (totalDao.update(parkSpace)) {
				if ("打开".equals(parkSpace.getParkStatus())) {
					ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
					infor.setAddTime(Util.getDateTime());
					infor.setParkId(parkSpace.getId());
					infor.setUseName(parkSpace.getVisitName());
					infor.setUseCode(parkSpace.getUserTelNum());
					infor.setUseStatus("使用中");
					if (totalDao.save2(infor)) {
						parkSpace.setInforId(infor.getId());
						totalDao.update2(parkSpace);
					}
				} else if ("关闭".equals(parkSpace.getParkStatus())) {
					ParkSpaceUseInfor infor = (ParkSpaceUseInfor) totalDao
							.getObjectById(ParkSpaceUseInfor.class,
									parkSpace.getInforId());
					if (infor != null) {
						String nowTime = Util.getDateTime();
						infor.setUpdateTime(nowTime);
						infor.setUseName(parkSpace.getVisitName());
						infor.setUseCode(parkSpace.getUserTelNum());
						infor.setUseStatus("已结束");
						long l = Util.getWorkTime1(infor.getAddTime(),
								nowTime);
						infor.setUseTime(Util.formatDuring(l));
						if (totalDao.update2(infor)) {
							parkSpace.setUsePeople(null);
							parkSpace.setUsePeopleCode(null);
							totalDao.update2(parkSpace);
						}
					}
				}
			} else
				return "车位状态保存失败，请检查网络！";
		}
		return message;
	}
	
	/**
	 * 打开或关闭车位方法
	 * 
	 * @param ip
	 * @param port
	 * @param openOrClose
	 * @return
	 */
	public String openCW(String ip, Integer port, int openOrClose) {
		// TODO Auto-generated method stub
		Socket s;
		try {
			s = new Socket(ip, port);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s
					.getOutputStream()));
			bw.write(openOrClose);
			// bw.newLine();
			bw.flush();
			bw.close();
			s.close();
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String updateAccess1(ParkSpace parkSpace) {
		// TODO Auto-generated method stub
		ParkSpace parkSpace2 = ByidParkSpace(parkSpace.getId());
		if (!parkSpace.getParkNum().equals(parkSpace2.getParkNum())) {
			ParkSpace parkSpace3 = (ParkSpace) totalDao.getObjectByCondition(
					"from ParkSpace where parkNum=? and parkingLot=?",
					parkSpace.getParkNum(), parkSpace.getParkingLot());
			if (parkSpace3 != null) {
				return "车位编号重复，添加失败！";
			}
		}
		if (parkSpace2 != null) {
			BeanUtils.copyProperties(parkSpace, parkSpace2,
					new String[] { "id", "addTime", "parkStatus", "usePeople",
							"usePeopleCode" });
			if (totalDao.update2(parkSpace2)) {
				return "true";
			}
		}
		return "对象为空，修改失败！";
	}

	@Override
	public Map<Integer, Object> findParkSpaceUseInforByCondition(
			ParkSpaceUseInfor parkSpaceUseInfor, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (parkSpaceUseInfor == null) {
			parkSpaceUseInfor = new ParkSpaceUseInfor();
		}
		String sql = "";
		if (parkSpaceUseInfor.getParkId() != null
				&& parkSpaceUseInfor.getParkId() > 0) {
			sql = "parkId=" + parkSpaceUseInfor.getParkId();
		}
		String hql = totalDao.criteriaQueries(parkSpaceUseInfor, sql, "parkId");
		hql += " order by id desc ";
		List listInt = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		map.put(2, count);
		return map;
	}

	@Override
	public ParkSpace ByblueaddressParkSpace(String address, String name) {
		// TODO Auto-generated method stub
		ParkSpace parkSpace = null;
		if (address != null && !"".equals(address)) {
			parkSpace = (ParkSpace) totalDao.getObjectByCondition(
					"from ParkSpace where blueAddress = ? and blueName = ?",
					address, name);
		}
		return parkSpace;
	}
	@Override
	public ParkSpace ByblueaddressParkSpace(String carnum) {
		// TODO Auto-generated method stub
		ParkSpace parkSpace = null;
		if (carnum != null && !"".equals(carnum)) {
			parkSpace = (ParkSpace) totalDao.getObjectByCondition(
					"from ParkSpace where parkNum = ?",
					carnum);
		}
		return parkSpace;
	}

	@Override
	public List ByblueaddressList() {
		// TODO Auto-generated method stub
		return totalDao.query("from ParkSpace");
	}
	
	@Override
	public List ByblueaddressList(String address, String tel) {
		// TODO Auto-generated method stub
		address = "'"
				+ address.replaceAll("\\[", "").replaceAll("\\]", "")
						.replaceAll(",", "','").replaceAll(" ", "") + "'";
		List list = totalDao.query("from ParkSpace where blueAddress in ("
				+ address + ") and userTelNum = ?",tel);
		return list;
	}

	/******************************* 电话号码管理方法2015-12-30 *********************************/
	@Override
	public SimCarkTel ByidSimCarkTel(Integer integer) {
		// TODO Auto-generated method stub
		if (integer != null && integer > 0) {
			SimCarkTel simCarkTel = (SimCarkTel) totalDao.getObjectById(
					SimCarkTel.class, integer);
			return simCarkTel;
		}
		return null;
	}

	@Override
	public String addSimCarkTel(SimCarkTel simCarkTel) {
		// TODO Auto-generated method stub
		if (simCarkTel != null) {
			SimCarkTel carkTel = (SimCarkTel) totalDao
					.getObjectByCondition(
							"from SimCarkTel where simId = ? and securityTel = ? and securityCode = ? and failTime > ?",
							simCarkTel.getSimId(), simCarkTel.getSecurityTel(), simCarkTel
									.getSecurityCode(), Util.getDateTime());
			if (carkTel != null) {
				carkTel.setSimTel(simCarkTel.getSecurityTel());
				carkTel.setStatus("正常");
				if (totalDao.update2(carkTel)) {
					return "true";
				}
			}else
				return "验证失败，请重新获取。";
		}

		return null;
	}

	@Override
	public Map<Integer, Object> findParkSimCarkTelndition(
			SimCarkTel simCarkTel, int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateSimCarkTel(SimCarkTel simCarkTel, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimCarkTel ByidSimCarkTel(String simId) {
		// TODO Auto-generated method stub
		if (simId != null && !"".equals(simId)) {
			SimCarkTel carkTel = (SimCarkTel) totalDao.getObjectByCondition(
					"from SimCarkTel where simId = ? order by id desc", simId);
			return carkTel;
		}
		return null;
	}

	@Override
	public String addSimCark(String simCark) {
		// TODO Auto-generated method stub
		if (simCark != null && !"".equals(simCark)) {
			SimCarkTel carkTel = (SimCarkTel) totalDao.getObjectByCondition(
					"from SimCarkTel where simId = ?", simCark);
			if (carkTel == null) {
				carkTel = new SimCarkTel();
				carkTel.setAddTime(Util.getDateTime());
				carkTel.setSimId(simCark);
				carkTel.setStatus("初始");
				if (totalDao.save2(carkTel)) {
					return "true";
				}
			}else {
				return "sim码已存在无法添加";
			}
		}
		return null;
	}

	// 获取验证码绑定手机号码操作
	@Override
	public String addSecurity(SimCarkTel simCarkTel) {
		// TODO Auto-generated method stub
		if (simCarkTel != null) {
			//先判断手机号码有没有绑定过有的话提示手机号码已绑定，
			
			SimCarkTel carkTel = (SimCarkTel) totalDao.getObjectByCondition(
					"from SimCarkTel where simId = ?", simCarkTel.getSimId());
			if (carkTel != null) {
				carkTel.setUpdateTime(Util.getDateTime());
				carkTel.setFailTime(Util.getSpecifiedminuteAfter(Util
						.getDateTime(), 30));
				carkTel.setSecurityTel(simCarkTel.getSecurityTel());
				int i = 1;
				if (carkTel.getSecurityshu() == null) {
				} else {
					i = carkTel.getSecurityshu() + 1;
				}
				carkTel.setSecurityshu(i);
				carkTel.setRandomNum(Util.yanNumber(2));
				String num6 = Util.yanNumber(6);
				carkTel.setSecurityCode(num6);
				if (totalDao.update2(carkTel)) {
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(carkTel.getSecurityTel(),
							"您正在进行哒哒停车绑定手机号码操作。验证码为：" + num6 + " 验证码有效期30分钟 ");
					return "true";
				}
			} else {
				SimCarkTel carkTel2 = new SimCarkTel();
				carkTel2.setAddTime(Util.getDateTime());
				carkTel2.setFailTime(Util.getSpecifiedminuteAfter(Util
						.getDateTime(), 30));
				carkTel2.setSimId(simCarkTel.getSimId());
				carkTel2.setSecurityTel(simCarkTel.getSecurityTel());
				int i = 1;
				if (simCarkTel.getSecurityshu() == null) {
				} else {
					i = simCarkTel.getSecurityshu() + 1;
				}
				carkTel2.setSecurityshu(i);
				carkTel2.setRandomNum(Util.yanNumber(2));
				String num6 = Util.yanNumber(6);
				carkTel2.setSecurityCode(num6);
				if (totalDao.update2(carkTel2)) {
					// 生成验证码的同时将短信发送至手机号码
					SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
					smsServiceImpl.send(carkTel2.getSecurityTel(),
							"您正在进行哒哒停车绑定手机号码操作。验证码为：" + num6 + " 验证码有效期30分钟 ");
					return "true";
				}
			}
		}
		return "验证码获取失败，请重新获取。";
	}
}
