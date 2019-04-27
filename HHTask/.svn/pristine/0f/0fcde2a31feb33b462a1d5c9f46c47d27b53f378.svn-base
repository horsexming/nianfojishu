package com.task.ServerImpl.menjin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.WarehouseApplicationServer;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.menjin.MarkStatusType;
import com.task.entity.menjin.WarehouseApplication;
import com.task.entity.menjin.WarehouseCertificate;
import com.task.util.Util;
import com.task.util.UtilTong;

@SuppressWarnings("unchecked")
public class WarehouseApplicationServerImpl implements WarehouseApplicationServer {
	private TotalDao totalDao;
	private static Integer duankou = 8885;
	private static Integer iw = 6;
	@Override
	public String addWarehouseApplication(WarehouseApplication warehouseA,List<WarehouseCertificate> wac) {
		// TODO Auto-generated method stub
		boolean b = false;
		if (warehouseA != null && wac!=null && wac.size()>0) {
			Users us = Util.getLoginUser();
			String ss = Util.getDateTime();
			warehouseA.setUsersId(us.getId());
			warehouseA.setCode(us.getCode());
			warehouseA.setDept(us.getDept());
			warehouseA.setCardId(us.getCardId());
			warehouseA.setName(us.getName());
			warehouseA.setAddTime(ss);
			warehouseA.setAppStatus("未使用");
			warehouseA.setPici("2016010004");
			b = totalDao.save(warehouseA);
			for (WarehouseCertificate w : wac) {
				if(w.getWarehouseId()==null) continue;
				WarehouseNumber w1 = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, w.getWarehouseId());
				if(w1!=null){
					w.setWareAppId(warehouseA.getId());
					w.setWarehouseId(w1.getId());
					w.setWarehouseName(w1.getNumber());
					w.setDepositTake(warehouseA.getCuiqutype());
					w.setUseStatus("待开门");
					w.setType("零件");
					w.setAddTime(ss);
					w.setActualNumber(0f);
					b = b&totalDao.save(w);
//					if(b) Util.Oshansuo(w1.getFourlightIp(), duankou, w1.getNumid(), true);
					//开始闪灯
//					if(b) Util.openColorXin(w1.getFourlightIp(), duankou, true, true, w1.getNumid(), w1.getMarkTypt()==null ? 0:w1.getMarkTypt().getMarkColor());
					if(b) UtilTong.openColorLight(w1.getIp(), duankou, true, false, w1.getOneNumber(), w1.getNumid(), w1.getMarkTypt()==null ? 0:w1.getMarkTypt().getMarkColor());
				}
			}
			if (b) return "添加成功！";
			else return "添加失败！";
		}
		return "对象为空，添加失败！";
	}

	@Override
	public WarehouseApplication byIdWarehouseApplication(Integer id) {
		// TODO Auto-generated method stub
		return (WarehouseApplication) totalDao.getObjectById(WarehouseApplication.class, id);
	}

	@Override
	public String deleteWarehouseApplication(Integer id) {
		// TODO Auto-generated method stub
		WarehouseApplication obje = byIdWarehouseApplication(id);
		if (obje != null) {
			if (totalDao.delete(obje))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findWarehouseApplication(WarehouseApplication warehouseA,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (warehouseA == null) {
			warehouseA = new WarehouseApplication();
		}
		String hql = totalDao.criteriaQueries(warehouseA, null);
		hql += " and appStatus <> '已使用' order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateWarehouseApplication(WarehouseApplication warehouseA) {
		// TODO Auto-generated method stub
		WarehouseApplication WarehouseApplication2 = byIdWarehouseApplication(warehouseA.getId());
		if (WarehouseApplication2 != null) {
			BeanUtils.copyProperties(warehouseA, WarehouseApplication2, new String[] {
					"id", "addTime", "addPName"});
			if (totalDao.update(WarehouseApplication2))
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
	public List<WarehouseCertificate> findWareCert(Integer id) {
		// TODO Auto-generated method stub
		if (id!=null&&id>0) return totalDao.query("from WarehouseCertificate where wareAppId = ? and useStatus <> '已失效'", id);
		return null;
	}

	@Override
	public String openandClose(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		boolean b = false;
		String messages = "操作失败！";
		if ("待开门".equals(wac.getUseStatus())) {
			wac.setUseStatus("待关门");b=true;
		} else if ("待关门".equals(wac.getUseStatus())) {
			//if ("存".equals(wac.getDepositTake())) 
				wac.setUseStatus("待确认");
			//else wac.setUseStatus("已失效");
		} else {
			return "状态有误，操作失败！";
		}
		WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, wac.getWarehouseId());
		if(wN!=null&&wN.getNumid()!=null){
			if(b){
				Util.backTowMa(wN.getIp(), duankou);
				//结束闪烁
				//灭灯
				UtilTong.closeLight(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid());
				//亮灯的颜色
				UtilTong.openColorLight(wN.getIp(), duankou, true, true, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			}
			messages = UtilTong.OCKuWei(wN.getIp(), duankou, b, wN.getOneNumber(), wN.getNumid());//开关门操作
			if("true".equals(messages)){//关门=》需要发送组合灯指令
				//改变凭证表状态
				WarehouseApplication wal = (WarehouseApplication) totalDao.getObjectById(WarehouseApplication.class, wac.getWareAppId());
				if(b)wac.setOpenTime(Util.getDateTime());
				else wac.setCloseTime(Util.getDateTime());
				if (totalDao.update(wac)) {//
					if("已失效".equals(wac.getUseStatus())){
						if (wal!=null) {
							int ii = totalDao.getCount("from WarehouseCertificate where wareAppId = ? and useStatus <> '已失效'", wac.getWareAppId());
							if (ii==0) wal.setAppStatus("已使用");
							else wal.setAppStatus("使用中");
							totalDao.update(wal);
						}
					}
				}
				
//				if(b){}
//				else{
//					//改变库位中物品的状态
//					if("存".equals(wac.getDepositTake())){
//						MarkStatusType mst = (MarkStatusType) totalDao.getObjectByCondition("from MarkStatusType where markTypt = '待检'");
//						if(mst!=null){
//							wN.setMarkTyptName("待检");
//							wN.setMarkTypt(mst);
//						}else {
//							wN.setMarkTyptName("空");
//							wN.setMarkTypt(null);
//						}
//						wN.setHave("有");
//						wN.setNowArticleName(wal.getSelfCard());//件号
//						wN.setTotalNumber(wN.getTotalNumber()+wac.getNumber());//当前总数量
//						totalDao.update(wN);
//						try {
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,1, "A2-01-1");
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,3, "A2-01-2");
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,5, "A2-01-3");
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,2, "A2-02-1");
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,4, "A2-02-2");
//							Thread.sleep(100);
//							Util.pingKuWei(wN.getIp(), duankou,6, "A2-02-3");
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//					int[] color = new int[iw];
//					List<WarehouseNumber> listwn = totalDao.query("from WarehouseNumber where ip = ? order by numid", wN.getIp());
//					if (listwn!=null&&listwn.size()>0) {
//						if (listwn.size()==iw) {
//							for (int i = 0; i < listwn.size(); i++) {
//								WarehouseNumber was = listwn.get(i);
//								if (was.getMarkTypt()==null) {
//									color[i] = 0;
//									continue;
//								}else {
//									color[i] = Util.lightColor(i+1, was.getMarkTypt().getMarkColor());
//								}
//							}
//						}else {
//							for (int j = 0; j < iw; j++) {
//								boolean bw = false;
//								int col = 0;
//								for (WarehouseNumber wah : listwn) {
//									if (wah.getNumid()!=null&&wah.getNumid()==j+1) {
//										bw = true;
//										if (wah.getMarkTypt()!=null) col = wah.getMarkTypt().getMarkColor();
//										else color[j] = 0;
//										break;
//									}
//								}
//								if (bw) color[j] = Util.lightColor(j+1, col);
//								else color[j] = 0;
//							}
//						}
//						Util.lightColorKuWei(wN.getFourlightIp(), duankou, color);
//					}
//				}
				//添加库存关系
			}
			return messages;
		}
		return messages;
	}

	@Override
	public WarehouseCertificate byIdWarehouseC(Integer id) {
		// TODO Auto-generated method stub
		return (WarehouseCertificate) totalDao.getObjectById(WarehouseCertificate.class, id);
	}

	@Override
	public String queren(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		wac.setQuerenTime(Util.getDateTime());
		wac.setUseStatus("已失效");
		boolean b = false;
		b = totalDao.update(wac);
		WarehouseApplication wal = (WarehouseApplication) totalDao.getObjectById(WarehouseApplication.class, wac.getWareAppId());
		if(wal!=null){
			int ii = totalDao.getCount("from WarehouseCertificate where wareAppId = ? and useStatus <> '已失效'", wac.getWareAppId());
			if (ii==0) {
				wal.setAppStatus("已使用");
				if(totalDao.update(wal)) return "trues";
			}
			else wal.setAppStatus("使用中");
			totalDao.update(wal);
		}
		//将物品保存至外购件待检表中
		if(b) return "true";
		return "确认失败！";
	}

	@Override
	public Map<Integer, Object> findWarehouseAppAndroid(
			WarehouseApplication w, int pageNo, int pageSize, Integer userId) {
		// TODO Auto-generated method stub
		if (w == null) {
			w = new WarehouseApplication();
		}
		String sql = "";
		Users users = (Users) totalDao.getObjectById(Users.class, userId);
		if (users!=null) {
			sql += " usersId = '"+userId+"'";
		}else {
			sql += " usersId = '0'";
		}
		sql += "";
		String hql = totalDao.criteriaQueries(w, sql);
		hql += " and appStatus <> '已使用' order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public WarehouseApplication byIdWarehouseApplication(Integer id,
			Integer userId) {
		// TODO Auto-generated method stub
		return (WarehouseApplication) totalDao.getObjectByCondition("from WarehouseApplication where id = ? and usersId = ?", id,userId);
	}

	@Override
	public WarehouseCertificate byIdWarehouseC(Integer id, Integer userId) {
		// TODO Auto-generated method stub
//		String sql = "from WarehouseCertificate w join WarehouseApplication a where a.id = w.wareAppId and w.id = ? and a.usersId = ? and a.appStatus <> '已使用'";
		String sql = "from WarehouseCertificate where id = ? and wareAppId in (select id from WarehouseApplication where usersId = ? and appStatus <> '已使用')";
		return (WarehouseCertificate) totalDao.getObjectByCondition(sql, id,userId);
	}
	
	@Override
	public String oaCloseA(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		boolean b = false;
		String messages = "操作失败！";
		if ("待开门".equals(wac.getUseStatus())) {
			wac.setUseStatus("待关门");b=true;
		} else if ("待关门".equals(wac.getUseStatus())) {
			if ("存".equals(wac.getDepositTake())) wac.setUseStatus("待确认");
			else wac.setUseStatus("已失效");
		} else {
			return "状态有误，操作失败！";
		}
		WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, wac.getWarehouseId());
		if(wN!=null&&wN.getNumid()!=null){
			if(b){
				Util.backTowMa(wN.getIp(), duankou);
				//结束闪烁
//				Util.openColorXin(wN.getFourlightIp(), duankou, true, false, wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
				//灭灯
				UtilTong.closeLight(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid());
				//亮灯的颜色
				UtilTong.openColorLight(wN.getIp(), duankou, true, true, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			}
			messages = UtilTong.OCKuWei(wN.getIp(), duankou, b, wN.getOneNumber(), wN.getNumid());//开关门操作
			if("true".equals(messages)){//关门=》需要发送组合灯指令
				//改变凭证表状态
				WarehouseApplication wal = (WarehouseApplication) totalDao.getObjectById(WarehouseApplication.class, wac.getWareAppId());
				if(b)wac.setOpenTime(Util.getDateTime());
				else wac.setCloseTime(Util.getDateTime());
				if (totalDao.update2(wac)) {//
					if("已失效".equals(wac.getUseStatus())){
						if (wal!=null) {
							int ii = totalDao.getCount("from WarehouseCertificate where wareAppId = ? and useStatus <> '已失效'", wac.getWareAppId());
							if (ii==0) wal.setAppStatus("已使用");
							else wal.setAppStatus("使用中");
							totalDao.update2(wal);
						}
					}
				}
//				if(b){}
//				else{}
				//添加库存关系
			}
			return messages;
		}
		return messages;
	}

	@Override
	public String querenAnd(WarehouseCertificate wac, Integer ir) {
		// TODO Auto-generated method stub
		if(!"待确认".equals(wac.getUseStatus())) return "状态异常，确认失败！";
		wac.setQuerenTime(Util.getDateTime());
		Float f1 = Float.parseFloat(ir+"");
		Float f2 = wac.getNumber()-wac.getActualNumber();
		boolean b = false;
		WarehouseApplication wal = (WarehouseApplication) totalDao.getObjectById(WarehouseApplication.class, wac.getWareAppId());
		if(wal!=null){
			//这里要区分类型
			WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, wac.getWarehouseId());
			if (f2.equals(f1)) {
				if ("存".equals(wac.getDepositTake())) {
					//改变库位中物品的状态
					MarkStatusType mst = (MarkStatusType) totalDao.getObjectByCondition("from MarkStatusType where markTypt = '待检'");
					if(mst!=null){
						wN.setMarkTyptName("待检");
						wN.setMarkTypt(mst);
					}else {
						wN.setMarkTyptName("空");
						wN.setMarkTypt(null);
					}
					wN.setHave("有");
					wN.setNowArticleName(wal.getSelfCard());//件号
					wN.setTotalNumber(wN.getTotalNumber()+f1);//当前总数量
					totalDao.update2(wN);
					try {
						UtilTong.pingKuWei(wN.getIp(), duankou, wN.getOneNumber(),wN.getNumid(), wN.getNumber());
						Thread.sleep(1500);
//						Thread.sleep(100);
//						Util.pingKuWei(wN.getIp(), duankou,2, "A2-01-2");
//						Thread.sleep(100);
//						Util.pingKuWei(wN.getIp(), duankou,3, "A2-01-3");
//						Thread.sleep(100);
//						Util.pingKuWei(wN.getIp(), duankou,4, "A2-02-1");
//						Thread.sleep(100);
//						Util.pingKuWei(wN.getIp(), duankou,5, "A2-02-2");
//						Thread.sleep(100);
//						Util.pingKuWei(wN.getIp(), duankou,6, "A2-02-3");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//更新亮灯的颜色
					//灭灯
					UtilTong.closeLight(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid());
					//亮灯的颜色
					UtilTong.openColorLight(wN.getIp(), duankou, true, true, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
					//Util.openColorXin(wN.getFourlightIp(), duankou, false, false, wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
//					int[] color = new int[iw];
//					List<WarehouseNumber> listwn = totalDao.query("from WarehouseNumber where ip = ? order by numid", wN.getIp());
//					if (listwn!=null&&listwn.size()>0) {
//						if (listwn.size()==iw) {
//							for (int i = 0; i < listwn.size(); i++) {
//								WarehouseNumber was = listwn.get(i);
//								if (was.getMarkTypt()==null) {
//									color[i] = 0;
//									continue;
//								}else {
//									color[i] = Util.lightColor(i+1, was.getMarkTypt().getMarkColor());
//								}
//							}
//						}else {
//							for (int j = 0; j < iw; j++) {
//								boolean bw = false;
//								int col = 0;
//								for (WarehouseNumber wah : listwn) {
//									if (wah.getNumid()!=null&&wah.getNumid()==j+1) {
//										bw = true;col = wah.getMarkTypt().getMarkColor();break;
//									}
//								}
//								if (bw) color[j] = Util.lightColor(j+1, col);
//								else color[j] = 0;
//							}
//						}
//						//Util.lightColorKuWei(wN.getFourlightIp(), duankou, color);
//					}
					wac.setUseStatus("已失效");
					//建立关系
//					WareBangGoogs bwg = new WareBangGoogs();
//					bwg.setFk_waigouDeliveryDetail_id(wal.getClassNameId());//采购明细单ID
//					bwg.setFk_ware_id(wac.getWarehouseId());
//					bwg.setNumber(f1);//可操作数量
//					bwg.setStatus(wN.getMarkTyptName());
//					totalDao.save2(bwg);
//					WaigouDeliveryDetail wgde = (WaigouDeliveryDetail) totalDao.getObjectById(WaigouDeliveryDetail.class, bwg.getFk_waigouDeliveryDetail_id());
//					if (wgde!=null) {
//					}
					StringBuffer buffer = new StringBuffer();
//						buffer.append(Util.neirong(wgde.getMarkId(), 30));//件号
//						buffer.append(Util.neirong(wgde.getSelfCard(),16));//批次
//						buffer.append(Util.neirong(f1+"",8));//数量
//						buffer.append(Util.neirong(wN.getNumber(),16));//库位编号
//						buffer.append(Util.neirong(wN.getMarkTyptName(),8));//库位状态
//						buffer.append(Util.neirong(wgde.getGysName(),30));//供应商
					buffer.append(Util.neirong("P913341-01P913341-01", 30));//件号
					buffer.append(Util.neirong("20160900001",16));//批次
					buffer.append(Util.neirong(f1+"",8));//数量
					buffer.append(Util.neirong(wN.getNumber(),16));//库位编号
					buffer.append(Util.neirong(wN.getMarkTyptName(),8));//库位状态
					buffer.append(Util.neirong("苏州恒昌世茂电子科技有限公司",30));//供应商
					//UtilTong.querenpingKuWei1(wN.getIp(), duankou, wN.getOneNumber(), wN.getNumid(), buffer.toString()+buffer.toString());
				}else if("检验".equals(wac.getDepositTake())){
					
				}else if("存".equals(wac.getDepositTake())){
					
				}else {
					return "类型为空，确认失败！";
				}
				//将物品保存至外购件待检表中
			}else if(f2>f1){
				if("检验".equals(wac.getDepositTake())){
					wac.setUseStatus("不合格");
				}else {
					wac.setUseStatus("待处理");//处理缺少多少件或者还将存入多少件
				}
			}else {
				return "输入数量异常，确认失败！";
			}
			b = totalDao.update2(wac);
			int ii = totalDao.getCount("from WarehouseCertificate where wareAppId = ? and useStatus <> '已失效'", wac.getWareAppId());
			if (ii==0) {
				wal.setAppStatus("已使用");
				if(totalDao.update2(wal)) return "trues";
			}
			else wal.setAppStatus("使用中");
			totalDao.update2(wal);
		}
		if(b) return "true";
		return "确认失败！";
	}
	
	@Override
	public void shansuo(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, wac.getWarehouseId());
		if(wN!=null&&wN.getNumid()!=null)
			//闪烁
			UtilTong.openColorLight(wN.getIp(), duankou, true, false, wN.getOneNumber(), wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			//Util.openColorXin(wN.getFourlightIp(), duankou, true, true, wN.getNumid(), wN.getMarkTypt()==null ? 0:wN.getMarkTypt().getMarkColor());
			//Util.Oshansuo(wN.getFourlightIp(), duankou, wN.getNumid() ,true);
	}

	@Override
	public String shansuoAnd(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendTow(WarehouseCertificate wac) {
		// TODO Auto-generated method stub
		if(wac.getUseStatus()!=null&&!"待开门".equals(wac.getUseStatus())) return "状态异常，发送失败！";
		WarehouseNumber wN = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, wac.getWarehouseId());
		if(wN!=null&&wN.getNumid()!=null){
			String message = Util.sendTowMa(wN.getIp(), duankou, wac.getId());
			if ("true".equals(message)) {
				return message;
			}
			return "连接断开，发送失败！";
		}
		return "发送失败！";
	}

//	@Override
//	public void huanyuanProcard(){
//		List<String> list = totalDao.query("select more from Logging where addTime > '2018-11-30 12:00:00' and objectName = 'Procard'");
//		if (list!=null&&list.size()>0){
//			for (String s : list) {
//				try {
//					JSONObject jo = new JSONObject(s);
//					String id = jo.getString("id");
//					Procard procard = (Procard) totalDao.getObjectById(Procard.class, Integer.parseInt(id));
//					if(procard==null) continue;
//					String status = getStrings(jo,"status");
//					System.out.println("status:"+status);
//					if (!"".equals(status)) procard.setStatus(status);
//					String tjNumber = getStrings(jo,"tjNumber");
//					System.out.println("tjNumber:"+tjNumber);
//					if (!"".equals(tjNumber)) procard.setTjNumber(Float.parseFloat(tjNumber));
//					String minNumber = getStrings(jo,"minNumber");
//					System.out.println("minNumber:"+minNumber);
//					if (!"".equals(minNumber)) procard.setMinNumber(Float.parseFloat(minNumber));
//					totalDao.update(procard);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	public String getStrings(JSONObject object,String name) throws JSONException{
//		if(object.isNull(name)){
//			return "";
//		}else{
//			return object.getString(name);
//		}
//	}
	
}
