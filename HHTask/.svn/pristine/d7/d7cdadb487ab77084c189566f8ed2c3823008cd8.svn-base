package com.task.ServerImpl.onemark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.onemark.OneLightServer;
import com.task.entity.Users;
import com.task.entity.android.processpush.OneMachine;
import com.task.entity.onemark.OneLight;
import com.task.entity.parking.ParkSpaceUseInfor;
import com.task.entity.rtx.RtxMsg;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class OneLightServerImpl implements OneLightServer {

	private TotalDao totalDao;

	@Override
	public String addOneLight(OneLight light) {
		// TODO Auto-generated method stub
		if (light != null) {
			int i = totalDao.getCount("from OneLight where lightNum=?", light
					.getLightNum());
			if (i > 0) {
				return "灯号已存在，添加失败！";
			}
			int i2 = totalDao.getCount(
					"from OneLight where lightIP=? and lightZhiLing = ?", light
							.getLightIP(), light.getLightZhiLing());
			if (i2 > 0) {
				return "相同IP无法添加重复开关指令，添加失败！";
			}

			light.setLightStatus("关闭");
			light.setAddTime(Util.getDateTime());
			if (totalDao.save(light))
				return "添加成功！";
			else
				return "添加失败！";
		}
		return "对象为空，添加成功！";
	}

	@Override
	public OneLight byIdOneLight(Integer id) {
		// TODO Auto-generated method stub
		return (OneLight) totalDao.getObjectById(OneLight.class, id);
	}

	@Override
	public String deleteOneLight(Integer id) {
		// TODO Auto-generated method stub
		OneLight obje = byIdOneLight(id);
		if (obje != null) {
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findOneLight(OneLight light, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		if (light == null) {
			light = new OneLight();
		}
		String hql = totalDao.criteriaQueries(light, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, list);
		map.put(1, count);
		return map;
	}

	@Override
	public String updateOneLight(OneLight light) {
		// TODO Auto-generated method stub
		OneLight light2 = byIdOneLight(light.getId());
		if (light2 != null) {
			if (!"".equals(light.getLightNum().trim())
					&& !light.getLightNum().equals(light2.getLightNum())) {
				int i = totalDao.getCount("from OneLight where lightNum=?",
						light.getLightNum());
				if (i > 0) {
					return "灯号已存在，添加失败！";
				}
			}
			if ((!"".equals(light.getLightIP().trim()) && !light.getLightIP()
					.equals(light2.getLightIP()))
					|| (light.getLightZhiLing() > 0 && light.getLightZhiLing() != light2
							.getLightZhiLing())) {
				int i = totalDao.getCount(
						"from OneLight where lightIP=? and lightZhiLing = ?",
						light.getLightIP(), light.getLightZhiLing());
				if (i > 0) {
					return "相同IP，无法添加重复开关指令，添加失败！";
				}
			}
			BeanUtils.copyProperties(light, light2, new String[] { "id",
					"addTime", "machiness", "lightStatus", "accessEquipment",
					"parkSpaceUseInforId" });
			light2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(light2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public String updateOCLight(OneLight light, Integer integer) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		if ("关闭".equals(light.getLightStatus())) {
			light.setLightStatus("打开");
		} else if ("打开".equals(light.getLightStatus())) {
			light.setLightStatus("关闭");
		} else {
			return "灯状态错误，操作失败！";
		}
		String message = Util.SendZ(light.getLightIP(), Integer.parseInt(light
				.getLightPort()), integer);
		if ("true".equals(message)) {
			if (totalDao.update(light)) {
				if ("打开".equals(light.getLightStatus())) {
					ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
					infor.setAddTime(Util.getDateTime());
					infor.setParkId(light.getId());
					infor.setUseName(users.getName());
					infor.setUseCode(users.getCode());
					infor.setJlType("灯");
					infor.setUseStatus("使用中");
					if (totalDao.save2(infor)) {
						light.setParkSpaceUseInforId(infor.getId());
						totalDao.update2(light);
					}
				} else if ("关闭".equals(light.getLightStatus())) {
					ParkSpaceUseInfor infor = (ParkSpaceUseInfor) totalDao
							.getObjectById(ParkSpaceUseInfor.class, light
									.getParkSpaceUseInforId());
					if (infor != null) {
						String nowTime = Util.getDateTime();
						infor.setUpdateTime(nowTime);
						infor.setCloseName(users.getName());
						infor.setCloseCode(users.getCode());
						infor.setUseStatus("已结束");
						long l = Util.getWorkTime1(infor.getAddTime(), nowTime);
						infor.setUseTime(Util.formatDuring(l));
						totalDao.update2(infor);
					}
				}
			} else {
				return "灯状态保存失败，请检查网络！";
			}
		}
		return message;
	}

	// 手机端开关灯，需要查数据库
	public static String staticOCLight(OneLight light, Integer integer,
			String code) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		List<Users> userss = totalDao.query("from Users where code = ?", code);
		String message = "用户不存在！";
		if (userss != null && userss.size() > 0) {
			Users users = userss.get(0);
			if ("关闭".equals(light.getLightStatus())) {
				light.setLightStatus("打开");
			} else if ("打开".equals(light.getLightStatus())) {
				light.setLightStatus("关闭");
			} else {
				return "灯状态错误，操作失败！";
			}
			message = Util.SendZ(light.getLightIP(), Integer.parseInt(light
					.getLightPort()), integer);
			if ("true".equals(message)) {
				if (totalDao.update2(light)) {
					if ("打开".equals(light.getLightStatus())) {
						ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
						infor.setAddTime(Util.getDateTime());
						infor.setParkId(light.getId());
						infor.setUseName(users.getName());
						infor.setUseCode(users.getCode());
						infor.setJlType("灯");
						infor.setUseStatus("使用中");
						if (totalDao.save2(infor)) {
							light.setParkSpaceUseInforId(infor.getId());
							totalDao.update2(light);
						}
					} else if ("关闭".equals(light.getLightStatus())) {
						ParkSpaceUseInfor infor = (ParkSpaceUseInfor) totalDao
								.getObjectById(ParkSpaceUseInfor.class, light
										.getParkSpaceUseInforId());
						if (infor != null) {
							String nowTime = Util.getDateTime();
							infor.setUpdateTime(nowTime);
							infor.setCloseName(users.getName());
							infor.setCloseCode(users.getCode());
							infor.setUseStatus("已结束");
							long l = Util.getWorkTime1(infor.getAddTime(),
									nowTime);
							infor.setUseTime(Util.formatDuring(l));
							totalDao.update2(infor);
						}
					}
				} else {
					return "打开失败，请检查网络！";
				}
			}
		}
		return message;
	}

	/**
	 * 刷卡关灯接口不用重新查数据库
	 */
	public static void staticOCLight(List<OneLight> light, String code,
			String name) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		int[] ints = new int[light.size()];
		for (int i = 0; i < light.size(); i++) {
			OneLight light2 = light.get(i);
			if (light2 != null)
				ints[i] = Util.swiInstruction(light2.getLightZhiLing(), 0);// 因为是打开状态默认发0。"打开".equals(light2.getLightStatus())// ?// 0:1
			else
				ints[i] = 0;
		}
		String message = Util.SendZ(light.get(0).getLightIP(), Integer
				.parseInt(light.get(0).getLightPort()), ints);
		if ("true".equals(message)) {
			for (OneLight ol : light) {
				ol.setLightStatus("关闭");
				List<ParkSpaceUseInfor> perkInfors = totalDao.query(
						"from ParkSpaceUseInfor where id = ?", ol
								.getParkSpaceUseInforId());
				if (perkInfors != null && perkInfors.size() > 0) {
					ParkSpaceUseInfor infor = perkInfors.get(0);
					if (infor != null) {
						String nowTime = Util.getDateTime();
						infor.setUpdateTime(nowTime);
						infor.setCloseName(name);
						infor.setCloseCode(code);
						infor.setUseStatus("已结束");
						long l = Util.getWorkTime1(infor.getAddTime(),
								nowTime);
						infor.setUseTime(Util.formatDuring(l));
						if (totalDao.update2(infor))
							totalDao.update2(ol);
					}
				}
			}
		}
	}
	
	/**
	 * 刷卡 开灯接口不用重新查数据库
	 */
	public static void staticOCLight_1(List<OneLight> light, String code,
			String name) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		int[] ints = new int[light.size()];
		for (int i = 0; i < light.size(); i++) {
			OneLight light2 = light.get(i);
			if (light2 != null)
				ints[i] = Util.swiInstruction(light2.getLightZhiLing(), 1);// 因为是打开状态默认发0。"打开".equals(light2.getLightStatus())// ?// 0:1
			else
				ints[i] = 0;
		}
		String message = Util.SendZ(light.get(0).getLightIP(), Integer
				.parseInt(light.get(0).getLightPort()), ints);
		if ("true".equals(message)) {
			for (OneLight ol : light) {
				ol.setLightStatus("打开");
				ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
				infor.setAddTime(Util.getDateTime());
				infor.setParkId(ol.getId());
				infor.setUseName(name);
				infor.setUseCode(code);
				infor.setJlType("灯");
				infor.setUseStatus("使用中");
				if (totalDao.save2(infor)) {
					ol.setParkSpaceUseInforId(infor.getId());
					totalDao.update2(ol);
				}
			}
		}
	}
	
	/**
	 * 车辆通过关闭管理员办公室灯
	 */
	public static void staticOCLight_1(List<OneLight> light, String code,
			String name,String outInDoor) {
		// TODO Auto-generated method stub
		TotalDao totalDao = createTotol();
		boolean b = false;
		if ("进门".equals(outInDoor))
			b = true;
		for (int i = 0; i < light.size(); i++) {
			OneLight light2 = light.get(i);
			if (light2 != null){
				String message = "";
				if (b) {//开灯
					if (light2.getLightOpen()!=null&&light2.getLightOpenMin()!=null) {
						message = Util.SendAndBack(light.get(0).getLightIP(), Integer
								.parseInt(light.get(0).getLightPort()), Util.swiInstruction(light2.getLightOpen()), light2.getLightOpenMin());
					}else {
						message = Util.SendAndBack(light.get(0).getLightIP(), Integer
								.parseInt(light.get(0).getLightPort()), Util.swiInstruction(0), 0);
						//给管理员发消息
						RtxUtil.sendNotify(code, "您的专属灯还未设置延时开灯指令，车控灯无法进行，请先设置开关指令。", "系统消息", "0", "0");
					}
				}else {//关门
					if (light2.getLightClose()!=null&&light2.getLightCloseMin()!=null) {
						message = Util.SendAndBack(light.get(0).getLightIP(), Integer
								.parseInt(light.get(0).getLightPort()), Util.swiInstruction(light2.getLightClose()), light2.getLightCloseMin());
					}else {
						message = Util.SendAndBack(light.get(0).getLightIP(), Integer
								.parseInt(light.get(0).getLightPort()), Util.swiInstruction(0), 0);
						//给管理员发消息
						RtxUtil.sendNotify(code, "您的专属灯还未设置延时关灯指令，车控灯无法进行，请先设置开关指令。", "系统消息", "0", "0");
					}
				}
				if ("true".equals(message)) {
					if (b) {//添加开灯记录
						light2.setLightStatus("打开");
						if (totalDao.update2(light2)) {
							ParkSpaceUseInfor infor = new ParkSpaceUseInfor();
							infor.setAddTime(Util.getDateTime());
							infor.setParkId(light2.getId());
							infor.setUseName(name);
							infor.setUseCode(code);
							infor.setJlType("灯");
							infor.setUseStatus("使用中");
							if (totalDao.save2(infor)) {
								light2.setParkSpaceUseInforId(infor.getId());
								totalDao.update2(light2);
							}
						}
					}else {//修改使用记录
						light2.setLightStatus("关闭");
						if (totalDao.update2(light2)) {
							List<ParkSpaceUseInfor> perkInfors = totalDao.query(
									"from ParkSpaceUseInfor where id = ?", light2
											.getParkSpaceUseInforId());
							if (perkInfors != null && perkInfors.size() > 0) {
								ParkSpaceUseInfor infor = perkInfors.get(0);
								if (infor != null) {
									String nowTime = Util.getDateTime();
									infor.setUpdateTime(nowTime);
									infor.setCloseName(name);
									infor.setCloseCode(code);
									infor.setUseStatus("已结束");
									long l = Util.getWorkTime1(infor.getAddTime(),
											nowTime);
									infor.setUseTime(Util.formatDuring(l));
									totalDao.update2(infor);
								}
							}
						}
					}
				}
			}
		}
	}

	/** 生成门禁记录表信息的接口 **/
	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		OneLightServerImpl olt = new OneLightServerImpl();
		olt.setTotalDao(totalDao);
		return totalDao;
	}

	@Override
	public List findGongweiLight(String num) {
		// TODO Auto-generated method stub
		if (num != null) {
			OneMachine machine = (OneMachine) totalDao.getObjectByCondition(
					"from OneMachine where ipAddress=?", num);
			if (machine != null) {
				List listLight = totalDao
						.query("from OneLight where machiness.id = ?", machine
								.getId());
				return listLight;
			}
		}
		return null;
	}

	@Override
	public List findUserIdLight(Integer id) {
		// TODO Auto-generated method stub
		if (id != null && id > 0)
			return totalDao.query("from OneLight where accessEquipment.id in (select fk_acEq_id from DoorBangDing where fk_user_id = ? and status = '内') or accessEquipment.id in (select id from AccessEquipment where adminCardId = (select cardId from Users where id = ?) and equipmentDaoType = '办公室')", id, id);
		return null;
	}
}
