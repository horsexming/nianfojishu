package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Dao.TotalDao;
import com.task.Server.menjin.ToolCabineServer;
import com.task.action.UsersAction;
import com.task.action.xinxi.TwoDimensionCode;
import com.task.entity.Users;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.DrinksType;
import com.task.entity.menjin.ReceiveCabinet;
import com.task.entity.menjin.ToolCabine;
import com.task.util.RtxUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class ToolCabineServerImpl implements ToolCabineServer {
	private TotalDao totalDao;

	@Override
	public String addToolCabine(ToolCabine toolCabine) {
		// TODO Auto-generated method stub
		if (toolCabine != null) {
			int i2 = totalDao.getCount(
					"from ToolCabine where cabAceIp=? and cabOpenOrder = ?", toolCabine
							.getCabAceIp(), toolCabine.getCabOpenOrder());
			if (i2 > 0) return "相同IP无法添加重复的柜子编号，添加失败！";
			toolCabine.setCabStatus("未满");
			toolCabine.setNowNumber(0);
			toolCabine.setAddTime(Util.getDateTime());
			if("衣柜".equals(toolCabine.getCaType())) toolCabine.setPassWord("1111");
			if (totalDao.save(toolCabine)) return "添加成功！";
			else return "添加失败！";
		}
		return "对象为空，添加成功！";
	}

	@Override
	public ToolCabine byIdToolCabine(Integer id) {
		// TODO Auto-generated method stub
		return (ToolCabine) totalDao.getObjectById(ToolCabine.class, id);
	}

	@Override
	public String deleteToolCabine(Integer id) {
		// TODO Auto-generated method stub
		ToolCabine obje = byIdToolCabine(id);
		if (obje != null) {
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findToolCabine(ToolCabine toolCabine,
			int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (toolCabine == null) {
			toolCabine = new ToolCabine();
		}
		String sql = "";
		if ("wp".equals(tag))
			sql += "caType = '用户'";
		else if ("ck".equals(tag))
			sql += "caType = '衣柜'";
		else if ("kdg".equals(tag))
			sql += "caType = '快递柜'";
		else
			sql += "(caType = '' or caType is null)";
		String hql = totalDao.criteriaQueries(toolCabine, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		for (Object object : list) {
			ToolCabine t = (ToolCabine) object;
			String s = "";
//			Iterator iterator = t.getUsers().iterator();
			for (Users u : t.getUsers()) {
				if("离职".equals(u.getOnWork())) continue;
				s += u.getName();
//				iterator.next();
//				if(iterator.hasNext()){
					s+=" ";
//				}
			}
			t.setUsersList(s);
		}
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateToolCabine(ToolCabine toolCabine) {
		// TODO Auto-generated method stub
		ToolCabine toolCabine2 = byIdToolCabine(toolCabine.getId());
		if (toolCabine2 != null) {
			BeanUtils.copyProperties(toolCabine, toolCabine2, new String[] {
					"id", "addTime", "nowNumber", "nowArticleName",
					"nowArticleFormat", "cabStatus", "users","caType","passWord"});
			toolCabine2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(toolCabine2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}
	//修改密码
	@Override
	public String updatemima(Integer id,String oldpassword,String newpassword) {
		// TODO Auto-generated method stub
		ToolCabine toolCabine2 = byIdToolCabine(id);
		if(toolCabine2.getPassWord().equals(oldpassword)){
			toolCabine2.setPassWord(newpassword);
			if (toolCabine2 != null) {
				if (totalDao.update(toolCabine2))
					return "修改成功！";
				else
					return "修改失败!";
			}
		}
		return "与原密码不一致，修改失败!";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public Map<Integer, Object> findDepositCabinet(
			DepositCabinet depositCabinet, int pageNo, int pageSize, String tag) {
		// TODO Auto-generated method stub
		if (depositCabinet == null) {
			depositCabinet = new DepositCabinet();
		}
		String sql = "";
		if ("cun".equals(tag)) {
			sql += " depositStatus = '待入柜' and artQuantity > actualDepositQuantity ";
		}else if("qu".equals(tag)){
			sql += " depositStatus <> '待入柜' and actualDepositQuantity > 0 and artQuantity > alreadyReceivedQuantity";
		}else if("all".equals(tag)){
		}
		String hql = totalDao.criteriaQueries(depositCabinet, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public Map<Integer, Object> findReceiveCabinet(
			ReceiveCabinet receiveCabinet, int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (receiveCabinet == null) {
			receiveCabinet = new ReceiveCabinet();
		}
		String hql = totalDao.criteriaQueries(receiveCabinet, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public DepositCabinet byIdDepositCabinet(Integer id) {
		// TODO Auto-generated method stub
		return (DepositCabinet) totalDao
				.getObjectById(DepositCabinet.class, id);
	}

	@Override
	public ReceiveCabinet byIdReceiveCabinet(Integer id) {
		// TODO Auto-generated method stub
		return (ReceiveCabinet) totalDao
				.getObjectById(ReceiveCabinet.class, id);
	}

	@Override
	public String addReceiveCabinet(DepositCabinet cabinet,
			ReceiveCabinet receiveCabinet) {
		// TODO Auto-generated method stub
		if (receiveCabinet != null
				&& receiveCabinet.getReceiveQuantity() != null
				&& receiveCabinet.getReceiveQuantity() > 0
				&& cabinet.getActualDepositQuantity() - cabinet.getAlreadyReceivedQuantity() >= receiveCabinet.getReceiveQuantity()) {
			Users users = Util.getLoginUser();
			receiveCabinet.setReceiveWuName(cabinet.getDepArticleName());
			receiveCabinet.setReceiveFormat(cabinet.getDepArticleFormat());
			receiveCabinet.setPosId(cabinet.getId());
			receiveCabinet.setRealReceiveQuantity(0);// 实际已取数量
			receiveCabinet.setReceiveCardId(users.getCardId());
			receiveCabinet.setReceiveDept(users.getDept());
			receiveCabinet.setReceiveName(users.getName());
			receiveCabinet.setReceiveUserId(users.getId());
			receiveCabinet.setAddTime(Util.getDateTime());
			receiveCabinet.setFailTime(Util.getSpecifiedDayAfter(Util.getDateTime("yyyy-MM-dd"), 1));
			receiveCabinet.setReceiveStatus("待领取");
			receiveCabinet.setVerificationCodeStatus("未使用");
			String yans = Util.yanNumber(6);
			receiveCabinet.setReceiveVerificationCode(yans);
			if (totalDao.save(receiveCabinet)) {
				//查询最优取物柜编号：先根据物品名称和规格查询toolCabine表，得到所有相同物品的柜子信息。循环到大于，应取数量为止。将柜子设备的编号和应取数量，发送至RTX
				StringBuffer buffer = new StringBuffer();
				List<ToolCabine> list = totalDao.query("from ToolCabine where nowArticleName = ? and nowArticleFormat = ? and nowNumber > 0",cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
				if (list!=null&&list.size()>0) {
					int num = 0;
					int i1 = 0;
					int yingqu = receiveCabinet.getReceiveQuantity();
					String ip = "";
					for (ToolCabine toolCabine : list) {
						if (toolCabine==null||toolCabine.getCabAceIp()==null||"".equals(toolCabine.getCabAceIp())||toolCabine.getNowNumber()<=0)
							continue;
						if ("".equals(ip)){
							num += toolCabine.getNowNumber();
							i1 += toolCabine.getNowNumber();
							ip = toolCabine.getCabAceIp();
							AccessEquipment accessEquipment = (AccessEquipment) totalDao.getObjectByCondition("from AccessEquipment where equipmentIP = ?", ip);
							if (accessEquipment!=null) {
								buffer.append("取物柜子编号为:"+accessEquipment.getEquipmentNum());
							}
							if (num >= yingqu) {
								buffer.append("，应取数量为:"+yingqu);
								break;
							}
						}else if (!"".equals(ip)&&!ip.equals(toolCabine.getCabAceIp())) {//柜子IP变化了。证明不在编号相同的柜子中。
							buffer.append("，应取数量为:"+i1);
							yingqu -= num;
							ip = toolCabine.getCabAceIp();
							num = toolCabine.getNowNumber();
							AccessEquipment accessEquipment = (AccessEquipment) totalDao.getObjectByCondition("from AccessEquipment where equipmentIP = ?", ip);
							if (accessEquipment!=null) {
								buffer.append("，取物柜子编号为:"+accessEquipment.getEquipmentNum());
							}
							if (num >= yingqu) {
								buffer.append("，应取数量为:"+yingqu);
								break;
							}
							i1 = toolCabine.getNowNumber();
						}else {
							num += toolCabine.getNowNumber();
							if (num >= yingqu) {
								buffer.append("，应取数量为:"+yingqu);
								break;
							}
							i1 += toolCabine.getNowNumber();
						}
					}
				}else {
					buffer.append("当前柜子中没有您要取的物品！");
					return "当前柜子中没有您要取的物品！";
				}
				// 给领取人发送RTX消息
				RtxUtil.sendNotify(users.getCode(), "您的"
						+ receiveCabinet.getReceiveFormat()
						+ receiveCabinet.getReceiveWuName() +" 建议"+buffer.toString()+"(如无法领取，请按柜子编号依次领取)。"+ "验证码为： "
						+ yans + " !", "系统消息", "0", "0");
				return "申请成功，验证码为：" + yans + "已下发至您的RTX。请前往刷卡领取";
			}
		}
		return "申请数量有误！";
	}

	@Override
	public String addErWeiMa(DepositCabinet cabinet) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		cabinet.setPrintUserId(users.getId());
		cabinet.setPrintName(users.getName());
		cabinet.setPrintCardId(users.getCardId());
		cabinet.setPrintDate(Util.getDateTime());
		if (totalDao.update(cabinet)) {
			String realFilePath = UsersAction.mainUrl+"/upload/file/daQRcode";
			TwoDimensionCode code = new TwoDimensionCode();
			String ids = Util.cardId(cabinet.getId()+"");
			if ("true".equals(code.danganQRcode(ids))) {
				//存物位置提示（计算）
				String selectGuiWM = "from ToolCabine where cabStatus = '未满' and nowArticleName = ? and nowArticleFormat = ?";//相同柜子未满
				String selectTSGui = "from ToolCabine where articleName = ? and articleFormat = ?";//是否存在特殊规定柜子
				String selectTSGuiWM = "from ToolCabine where nowNumber = 0 and cabStatus = '未满' and articleName = ? and articleFormat = ?";//找出空非特殊物品柜子未满
				String selectGuiKong = "from ToolCabine where nowNumber = 0 and cabStatus = '未满' and (articleName = '' or articleName is null) and (articleFormat = '' or articleFormat is null) and (nowArticleName = '' or nowArticleName is null) and (nowArticleFormat = '' or nowArticleFormat is null)";//找出空非特殊物品的柜子
				String selectAccessE = "from AccessEquipment where equipmentIP = ?";//根据IP查找门禁设备
				String tishi = "";
				List<ToolCabine> cabinelist = totalDao.query(selectGuiWM, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
				if (cabinelist != null && cabinelist.size() > 0) {// 有相同物品且未满柜子的情况下
					//查找设备
					AccessEquipment accessEquipment = (AccessEquipment) totalDao.getObjectByCondition(selectAccessE, cabinelist.get(0).getCabAceIp());
					if (accessEquipment!=null)
						tishi = "建议前往编号"+accessEquipment.getEquipmentNum()+"柜存物";
				}else {// 没有相同物品的情况下
					// 2.判断是否为特殊规定的物品 
					int special = totalDao.getCount(selectTSGui, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
					List<ToolCabine> toolCabines = null;
					if (special > 0)// 为特殊规定物品
						toolCabines = totalDao.query(selectTSGuiWM, cabinet.getDepArticleName(), cabinet.getDepArticleFormat());
					else// 不为特殊规定物品
						toolCabines = totalDao.query(selectGuiKong);
					if (toolCabines!=null&&toolCabines.size()>0) {
						//查找设备
						AccessEquipment accessEquipment = (AccessEquipment) totalDao.getObjectByCondition(selectAccessE, toolCabines.get(0).getCabAceIp());
						if (accessEquipment!=null)
							tishi = "建议前往编号"+accessEquipment.getEquipmentNum()+"柜存物";
					}else
						return "当前"+cabinet.getDepArticleFormat()+"的"+cabinet.getDepArticleName()+"物品已没有可供存放的储物柜，请先领取。";
				}
				String sr = "物品： "
					+ cabinet.getDepArticleName()+" 规格："+cabinet.getDepArticleFormat()+" 应存数量："+cabinet.getArtQuantity()
					+ " 存柜二维码为："+realFilePath+"/"+ids+".png"+" "+tishi+"(如无法存入，请按柜子编号依次存放)。";
				System.out.println(sr);
				RtxUtil.sendNotify(users.getCode(), sr, "系统消息",
						"0", "0");
				return "申请成功，存柜二维码已发送至您的RTX";
			}
			return "二维码生成失败！请重新申请。";
		}
		return "申请存柜失败！";
	}

	@Override
	public Map<Integer, Object> findDrinksType(DrinksType drinksType,
			int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (drinksType == null) {
			drinksType = new DrinksType();
		}
		String hql = totalDao.criteriaQueries(drinksType, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateDrinksType(DrinksType drinksType) {
		// TODO Auto-generated method stub
		DrinksType drinksType2 = byIdDrinksType(drinksType.getId());
		if (drinksType2 != null) {
			drinksType2.setDrinkJiFen(drinksType.getDrinkJiFen());
			drinksType2.setUpdateTime(Util.getDateTime());
			if (totalDao.update(drinksType2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	@Override
	public DrinksType byIdDrinksType(Integer id) {
		// TODO Auto-generated method stub
		return (DrinksType) totalDao.getObjectById(DrinksType.class, id);
	}

	@Override
	public String binDingUsers(Integer toCab, Integer[] usersId) {
		// TODO Auto-generated method stub
		if (toCab != null) {
			Set<Users> usersSet = new HashSet<Users>();// 用来存储最终要的绑定用户
			Set<Users> moreSet = new HashSet<Users>();// 用来存储相对之前增加的绑定用户
			Set<Users> lessSet = new HashSet<Users>();// 用来存储相对之前减少的绑定用户
			ToolCabine toolCabine1 = (ToolCabine) totalDao
					.getObjectById(ToolCabine.class, toCab);
			Set<Users> haduserSet = toolCabine1.getUsers();
			if (usersId != null && usersId.length > 0) {
				for (int i = 0; i < usersId.length; i++) {
					Users user = (Users) totalDao.getObjectById(Users.class,
							usersId[i]);// 查询用户
					if (user != null) {
						usersSet.add(user);
					} else {
						return "绑定对象为空";
					}
				}
			}
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
			toolCabine1.setUsers(usersSet);
			if (totalDao.update(toolCabine1))
				return "绑定成功！";
			else
				return "绑定失败！";
		}
		return "柜号为空绑定失败！";
	}

	@Override
	public List<Users> findAllBangUsers(Integer id) {
		// TODO Auto-generated method stub
		return totalDao.query("from Users where id in (select u.id from Users u join u.toolCabines t where t.id = ?) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休')", id);
	}

	@Override
	public Object[] findAllUsers(Users users, int parseInt, int pageSize, Integer ids) {
		// TODO Auto-generated method stub
		if (users == null) {
			users = new Users();
		}
		String hql = "";
		hql = totalDao.criteriaQueries(users, null);
		hql += " and id not in (select u.id from Users u join u.toolCabines t where t.id = ? ) and onWork not in ('离职','退休','病休') and dept not in ('內退','病休') order by dept";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize, ids);
		int count = totalDao.getCount(hql,ids);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List findCkTool() {
		// TODO Auto-generated method stub
		return totalDao.query("from ToolCabine where caType = '仓库' order by id desc");
	}
	@Override
	public List<ToolCabine> findpeople() {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		return totalDao.query("from ToolCabine where id in (select t.id from ToolCabine t join t.users u where u.id = ?)and caType = '衣柜'", users.getId());
	}

	@Override
	public List findkdgTool(String an) {
		// TODO Auto-generated method stub
		return totalDao.query("from ToolCabine where caType = '快递柜' and nowArticleFormat = ? and cabStatus = '未满'",an);
	}

}
