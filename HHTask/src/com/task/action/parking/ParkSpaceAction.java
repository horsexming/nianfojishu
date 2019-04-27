package com.task.action.parking;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.AccessEquipmentServer;
import com.task.Server.parking.ParkSpaceServer;
import com.task.entity.parking.ParkSpace;
import com.task.entity.parking.ParkSpaceUseInfor;
import com.task.entity.parking.SimCarkTel;
import com.task.util.MKUtil;

/**
 * 门禁系统 20150920_licong
 * 
 * @author Li_Cong 记录表
 */
@SuppressWarnings("unchecked")
public class ParkSpaceAction {
	private ParkSpace parkSpace;
	private SimCarkTel simCarkTel;
	private ParkSpaceUseInfor parkSpaceUseInfor;
	private ParkSpaceServer parkSpaceServer;
	private AccessEquipmentServer accessEquipmentServer;
	private List<ParkSpace> parkSpaceList;
	private List<ParkSpaceUseInfor> parkSpaceUseInforList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String infor = "";

	private String tag = "";

	private String dengluid = "";// 提交人id
	private String tijiaoName = "";// 提交人姓名
	private String tijiaoCode = "";// 提交人工号
	private String pairedDevices = "";// 接收到的已配对的蓝牙设备列表
	private String simId = "";// sim卡id
	private String simTel = "";// 手机号码
	private int id;

	public String toadd() {
		return "parkSpace_add";
	}

	public String add() {
		if (parkSpace != null) {
			if ("guan".equals(tag)) {
				errorMessage = parkSpaceServer.addParkSpace(parkSpace);
				if ("true".equals(errorMessage)) {
					errorMessage = "添加成功!";
					url = "ParkSpaceAction_showList.action?tag=" + tag;
					return "error";
				} else
					return "error";
			} else {
				errorMessage = "页面状态为空，添加失败！";
				return "error";
			}
		} else {
			errorMessage = "对象为空，添加失败";
			return "error";
		}
	}

	public String showList() {
		if (parkSpace != null) {
			ActionContext.getContext().getSession().put("ParkSpace", parkSpace);
		} else {
			parkSpace = (ParkSpace) ActionContext.getContext().getSession()
					.get("ParkSpace");
		}
		Map<Integer, Object> map = parkSpaceServer.findParkSpaceByCondition(
				parkSpace, Integer.parseInt(cpage), pageSize, tag);
		parkSpaceList = (List<ParkSpace>) map.get(1);// 显示面试单列表
		if (parkSpaceList != null && parkSpaceList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ParkSpaceAction_showList.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		if ("show".equals(tag)) {
			if (parkSpaceList == null || parkSpaceList.size() == 0) {
				errorMessage = "当前没有可使用的车位！";
				return "error";
			} else
				return "parkSpace_show";
		} else if ("guan".equals(tag))
			return "parkSpace_guan";
		else if ("showAll".equals(tag))
			return "parkSpace_show";
		else
			return "error";
	}

	//根据手机号码和已配对的蓝牙地址查询可用车位
	public String showList_Android() {
		List list = parkSpaceServer.ByblueaddressList();
		return MKUtil.writeJSON(true, "", list);
		//需求更改
//		if (simTel!=null&&!"".equals(simTel)&&simTel.length()>0) {
//			if (pairedDevices != null) {
//				List list = parkSpaceServer.ByblueaddressList(pairedDevices,simTel);
//				if (list != null && list.size() > 0) {
//					return MKUtil.writeJSON(true, "", list);
//				} else {
//					errorMessage = "notelornoBlue";
//					return MKUtil.writeJSON(false, errorMessage, null);
//				}
//			} else {
//				errorMessage = "没有已配对的蓝牙设备！请先连接设备";
//				return MKUtil.writeJSON(false, errorMessage, null);
//			}
//		} else {
//			if (simId != null && !"".equals(simId) && simId.length() > 0) {
//				SimCarkTel carkTel = parkSpaceServer.ByidSimCarkTel(simId);
//				if (carkTel != null) {
//					simTel = carkTel.getSimTel();
//					if ((simTel!=null&&!"".equals(simTel)&&simTel.length()>0)&&"正常".equals(carkTel.getStatus())) {
//						if (pairedDevices != null) {
//							List list = parkSpaceServer.ByblueaddressList(
//									pairedDevices, simTel);
//							if (list != null && list.size() > 0) {
//								return MKUtil.writeJSON(true, "", list);
//							} else {
//								errorMessage = "notelornoBlue";
//								return MKUtil.writeJSON(false, errorMessage, null);
//							}
//						} else {
//							errorMessage = "没有已配对的蓝牙设备！请先连接设备";
//							return MKUtil.writeJSON(false, errorMessage, null);
//						}
//					}else {
//						return MKUtil.writeJSON(false, "TEL", null);
//					}
//				}else {
//					//添加sim卡数据
//					parkSpaceServer.addSimCark(simId);
//					return MKUtil.writeJSON(false, "SIM", null);
//				}
//			} else {
//				return MKUtil.writeJSON(false, "nosim", null);
//			}
//		}
	}

	// 查询车位使用记录
	public String showList_Infor() {
		if (parkSpaceUseInfor != null) {
			ActionContext.getContext().getSession().put("ParkSpaceUseInfor",
					parkSpaceUseInfor);
		} else {
			parkSpaceUseInfor = (ParkSpaceUseInfor) ActionContext.getContext()
					.getSession().get("ParkSpaceUseInfor");
		}
		Map<Integer, Object> map = parkSpaceServer
				.findParkSpaceUseInforByCondition(parkSpaceUseInfor, Integer
						.parseInt(cpage), pageSize);
		parkSpaceUseInforList = (List<ParkSpaceUseInfor>) map.get(1);// 显示面试单列表
		if (parkSpaceUseInforList != null && parkSpaceUseInforList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ParkSpaceAction_showList_Infor.action?parkSpace.id="
					+ parkSpaceUseInfor.getParkId());
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "parkSpaceInfor_show";
	}

	public String toupdate() {
		if (parkSpace != null) {
			parkSpace = parkSpaceServer.ByidParkSpace(parkSpace.getId());
			if (parkSpace != null)
				return "parkSpace_update";
		}
		errorMessage = "对象为空，修改失败";
		return "error";
	}

	public String update() {
		if (parkSpace != null && parkSpace.getId() != null) {
			errorMessage = parkSpaceServer.updateAccess1(parkSpace);
			if ("true".equals(errorMessage)) {
				errorMessage = "修改成功";
				url = "ParkSpaceAction_showList.action?tag=" + tag;
				return "error";
			} else
				return "error";
		}
		errorMessage = "对象为空，修改失败";
		return "error";
	}

	public String delete() {
		if (parkSpace != null) {
			parkSpace = parkSpaceServer.ByidParkSpace(parkSpace.getId());
			if (parkSpace != null) {
				errorMessage = parkSpaceServer.deleteAccess(parkSpace);
				if ("true".equals(errorMessage)) {
					errorMessage = "删除成功";
					url = "ParkSpaceAction_showList.action?tag=" + tag;
					return "error";
				} else
					return "error";
			}
		}
		errorMessage = "对象为空，删除失败";
		return "error";
	}

	// Android开关接口
	public String closepark() {
		ParkSpace parkSpace1 = null;
		if (parkSpace.getBlueAddress() != null
				&& parkSpace.getBlueName() != null) {
			parkSpace1 = parkSpaceServer.ByblueaddressParkSpace(parkSpace
					.getBlueAddress(), parkSpace.getBlueName());
		}
		if (parkSpace1 != null) {
			errorMessage = parkSpaceServer.updateAccessPhone(parkSpace1, id,
					dengluid, tijiaoName, tijiaoCode);
			if ("true".equals(errorMessage)) {
				errorMessage = "您选择的停车位" + parkSpace1.getParkNum() + "已经"
						+ parkSpace1.getParkStatus() + "成功!";
				return MKUtil.writeJSON(true, errorMessage, null);
			}
			return MKUtil.writeJSON(false, errorMessage, null);
		}
		return MKUtil.writeJSON(false, "蓝牙地址与车位地址不符", null);
	}

	// Android来访车位开关接口
	public String visitpark() {
		ParkSpace parkSpace1 = null;
		if (parkSpace.getBlueAddress() != null
				&& parkSpace.getBlueName() != null) {
//			parkSpace1 = parkSpaceServer.ByblueaddressParkSpace(parkSpace
//					.getBlueAddress(), parkSpace.getBlueName());
		}
		parkSpace1 = parkSpaceServer.ByblueaddressParkSpace(parkSpace
				.getParkNum());
		if (parkSpace1 != null) {
			errorMessage = parkSpaceServer.updateAccessTelPhone(parkSpace1, id);
			if ("true".equals(errorMessage)) {
				errorMessage = "您选择的停车位" + parkSpace1.getParkNum() + "已经"
						+ parkSpace1.getParkStatus() + "成功!";
				return MKUtil.writeJSON(true, errorMessage, null);
			}
			if("操作失败!".equals(errorMessage))
				errorMessage = "车位网络连接失败！操作失败!";
			return MKUtil.writeJSON(false, errorMessage, null);
		}
		return MKUtil.writeJSON(false, "蓝牙地址与车位地址不符", null);
	}

	
	public String openPark() {
		parkSpace = parkSpaceServer.ByidParkSpace(parkSpace.getId());
		if (parkSpace != null) {
			// int id = 0;
			// if ("0x0A".equals(infor)) {
			// id = 0x0A;
			// } else if ("0x1A".equals(infor)) {
			// id = 0x1A;
			// } else if ("0x2A".equals(infor)) {
			// id = 0x2A;
			// } else if ("0x3A".equals(infor)) {
			// id = 0x3A;
			// } else if ("0x4A".equals(infor)) {
			// id = 0x4A;
			// } else if ("0x5A".equals(infor)) {
			// id = 0x5A;
			// }
			errorMessage = parkSpaceServer.updateAccess(parkSpace, id);
			if ("true".equals(errorMessage)) {
				url = "ParkSpaceAction_showList.action?tag=" + tag;
				errorMessage = "您选择的停车位" + parkSpace.getParkNum() + "已经"
						+ parkSpace.getParkStatus() + "成功!";
				return "error";
			}
		}
		return "error";
	}

	// android端根据蓝牙地址和名称得到车位信息
	public void findAddress() {
		if (parkSpace != null && parkSpace.getBlueAddress() != null
				&& parkSpace.getBlueName() != null) {
			ParkSpace space = parkSpaceServer.ByblueaddressParkSpace(parkSpace
					.getBlueAddress(), parkSpace.getBlueName());
			if (space != null) // new Object[] { circuitRun, list }
				MKUtil.writeJSON(true, "", space);
			else
				MKUtil.writeJSON(false, "数据异常!", null);
		}
	}

	//生成验证码
	public String Security_addyan(){
		errorMessage = parkSpaceServer.addSecurity(simCarkTel);
		if ("true".equals(errorMessage)) {
			return MKUtil.writeJSON(true, "验证码已发送至您的手机号，请及时使用。", null);
		}else {
			return MKUtil.writeJSON(false, errorMessage, null);
		}
	}
	
	//验证手机号码
	public String Security_yanTel(){
		errorMessage = parkSpaceServer.addSimCarkTel(simCarkTel);
		if ("true".equals(errorMessage)) {
			return MKUtil.writeJSON(true, "验证通过", null);
		}else {
			return MKUtil.writeJSON(false, errorMessage, null);
		}
	}
	
	public ParkSpace getParkSpace() {
		return parkSpace;
	}

	public void setParkSpace(ParkSpace parkSpace) {
		this.parkSpace = parkSpace;
	}

	public ParkSpaceServer getParkSpaceServer() {
		return parkSpaceServer;
	}

	public void setParkSpaceServer(ParkSpaceServer parkSpaceServer) {
		this.parkSpaceServer = parkSpaceServer;
	}

	public List<ParkSpace> getParkSpaceList() {
		return parkSpaceList;
	}

	public void setParkSpaceList(List<ParkSpace> parkSpaceList) {
		this.parkSpaceList = parkSpaceList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public AccessEquipmentServer getAccessEquipmentServer() {
		return accessEquipmentServer;
	}

	public void setAccessEquipmentServer(
			AccessEquipmentServer accessEquipmentServer) {
		this.accessEquipmentServer = accessEquipmentServer;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public ParkSpaceUseInfor getParkSpaceUseInfor() {
		return parkSpaceUseInfor;
	}

	public void setParkSpaceUseInfor(ParkSpaceUseInfor parkSpaceUseInfor) {
		this.parkSpaceUseInfor = parkSpaceUseInfor;
	}

	public List<ParkSpaceUseInfor> getParkSpaceUseInforList() {
		return parkSpaceUseInforList;
	}

	public void setParkSpaceUseInforList(
			List<ParkSpaceUseInfor> parkSpaceUseInforList) {
		this.parkSpaceUseInforList = parkSpaceUseInforList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDengluid() {
		return dengluid;
	}

	public void setDengluid(String dengluid) {
		this.dengluid = dengluid;
	}

	public String getTijiaoName() {
		return tijiaoName;
	}

	public void setTijiaoName(String tijiaoName) {
		this.tijiaoName = tijiaoName;
	}

	public String getTijiaoCode() {
		return tijiaoCode;
	}

	public void setTijiaoCode(String tijiaoCode) {
		this.tijiaoCode = tijiaoCode;
	}

	public String getPairedDevices() {
		return pairedDevices;
	}

	public void setPairedDevices(String pairedDevices) {
		this.pairedDevices = pairedDevices;
	}

	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}

	public String getSimTel() {
		return simTel;
	}

	public void setSimTel(String simTel) {
		this.simTel = simTel;
	}

	public SimCarkTel getSimCarkTel() {
		return simCarkTel;
	}

	public void setSimCarkTel(SimCarkTel simCarkTel) {
		this.simCarkTel = simCarkTel;
	}

}
