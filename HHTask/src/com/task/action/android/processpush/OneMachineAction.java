package com.task.action.android.processpush;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.android.processpush.OneMachineServer;
import com.task.Server.sop.ProcardServer;
import com.task.action.UsersAction;
import com.task.entity.TaSopGongwei;
import com.task.entity.android.processpush.OneMachine;
import com.task.entity.onemark.OneLight;
import com.task.entity.sop.Procard;
import com.task.util.HttpRequest;
import com.task.util.HttpResponse;

/***
 *一体机管理
 * 
 * @author Li_Cong
 * 
 */

@SuppressWarnings("unchecked")
public class OneMachineAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OneMachine machine;
	private OneMachineServer machineServer;
	private ProcardServer procardServer;// 流水卡片对象

	private List<Procard> procardList;// 集合
	private String errorMessage;
	private String successMessage;
	private TaSopGongwei taSopGongwei;
	private OneLight light;

	private List<OneMachine> machineList;// 集合
	private List<TaSopGongwei> gongweiList;// 未绑定工位集合
	private List<TaSopGongwei> banggongweiList;// 已绑定工位集合
	private List<OneLight> lightList;// 未绑定灯集合（om_id为空）
	private List<OneLight> banglightList;// 已绑定灯集合（om_id=OneMachine.id）
	private Integer id;
	private String cpage = "1";
	private String total;
	private String gongwei;
	private String url;
	private String pageStatus;
	private Procard procard;
	private int pageSize = 15;
	private int count = 0;// 已绑定工位数量
	private Integer[] usersId;// 选中要绑定的id数组
	private Integer[] lightId;// 选中要绑定的id数组
	private String tc;// 无弹窗

	// 分页显示查询内容
	public String showList() {
		if (machine != null) {
			ActionContext.getContext().getSession().put("OneMachine", machine);
		} else {// 用来保持分页时带着查询条件
			machine = (OneMachine) ActionContext.getContext().getSession().get(
					"OneMachine");
		}
		Map<Integer, Object> map = machineServer.findOneMachine(machine,
				Integer.parseInt(cpage), pageSize);
		machineList = (List<OneMachine>) map.get(0);// 显示面试单列表
		if (machineList != null && machineList.size() > 0) {
			int count = (Integer) map.get(1);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("OneMachineAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "machine_show";
	}

	public String toadd() {
		return "machine_add";
	}

	/**
	 * 验证一体机点数
	 * 
	 * @param selfUrl
	 * @return
	 */
	public String validateCount(String selfUrl) {
		int count = machineServer.oneMachineCount();
		HttpRequest httpRequest = new HttpRequest();
		Map<String, String> map = new HashMap<String, String>();
		map.put("companyUrl", selfUrl);
		String result = null;
		try {
			HttpResponse httpResponse = httpRequest.sendHttpPost(
					UsersAction.mainUrl
							+ "/companyInfoAction_getOneMackCount.action", map);
			result = httpResponse.getDataString();
		} catch (IOException e1) {
			e1.printStackTrace();
			// return "服务器连接失败,请稍候重试!";
			return "一体机添加数目前已达到上限，您目前不能添加！";
		}
		if (result == null) {
			return "通过";
		} else {
			Integer onlineCount = Integer.parseInt(result);
			if (onlineCount <= count) {
				return "对不起！一体机添加数目前已达到上限" + onlineCount + "，您目前不能添加！";
			} else {
				return "通过";
			}
		}
	}

	// 添加方法
	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String selfUrl = UsersAction.getSelfUrl(request);
		boolean b = UsersAction.validateLic(selfUrl);
		if (b) {
			errorMessage = validateCount(selfUrl);
			if (!"通过".equals(errorMessage)) {
				return "error";
			}
		}
		if (machine != null) {
			errorMessage = machineServer.addOneMachine(machine);
			if ("添加成功！".equals(errorMessage))
				url = "OneMachineAction_showList.action";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (machine.getId() != null && machine.getId() > 0 && machine != null) {
			machine = machineServer.byIdOneMachine(machine.getId());
			if (machine != null)
				return "machine_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String update() {
		errorMessage = machineServer.updateOneMachine(machine);
		if ("修改成功！".equals(errorMessage))
			url = "OneMachineAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id > 0 && id != null) {
			errorMessage = machineServer.deleteOneMachine(id);
			if ("删除成功！".equals(errorMessage))
				url = "OneMachineAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	// 条件查询工位
	public String findStationCondition() {
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			if (taSopGongwei != null) {
				ActionContext.getContext().getSession().put("taSopGongwei",
						taSopGongwei);
			} else {
				taSopGongwei = (TaSopGongwei) ActionContext.getContext()
						.getSession().get("taSopGongwei");
			}
			Object[] object = machineServer.findMaIdByCondition(taSopGongwei,
					Integer.parseInt(cpage), pageSize, id);// 条件查询所有用户
			if (object != null && object.length > 0) {
				gongweiList = (List<TaSopGongwei>) object[0];
				if (gongweiList != null && gongweiList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("OneMachineAction_findStationCondition.action?id="
									+ id);
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的工位不存在或者已经与该一体机绑定!";
				}
				// 已绑定人员处理
				banggongweiList = machineServer.findAllBangGw(id);
				if (banggongweiList != null && banggongweiList.size() > 0) {
					count = banggongweiList.size();// 绑定人员数量
					// 去除已绑定人员
					for (int i = 0; i < gongweiList.size(); i++) {
						TaSopGongwei listTagw = gongweiList.get(i);
						for (int j = 0; j < banggongweiList.size(); j++) {
							TaSopGongwei binbangTagw = banggongweiList.get(j);
							if (listTagw.getId().equals(binbangTagw.getId())) {
								gongweiList.remove(listTagw);
								i--;
							}
						}
					}
				}
			} else {
				errorMessage = "抱歉!您查询的工位不存在或者已经与该一体机绑定!";
			}
			return "gwbangding_show";
		} else {
			errorMessage = "该功能不存在，请重试!";
			return "error";
		}
	}

	// 跳转到绑定工位页面(查看已绑定工位)
	public String findMachineById() {
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			// 未绑定人员处理
			Object[] object = machineServer.findAllTagw(id, Integer
					.parseInt(cpage), pageSize);// 条件查询所有用户
			if (object != null && object.length > 0) {
				gongweiList = (List<TaSopGongwei>) object[0];
				if (gongweiList != null && gongweiList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("OneMachineAction_findMachineById.action?id="
							+ id);
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的用户不存在或者已经与该设备绑定!";
				}
			} else {
				errorMessage = "抱歉!您查询的用户不存在或者已经与该设备绑定!";
			}
			// 已绑定人员处理
			banggongweiList = machineServer.findAllBangGw(id);
			if (banggongweiList != null && banggongweiList.size() > 0) {
				count = banggongweiList.size();
			}
			return "gwbangding_show";
		} else {
			errorMessage = "不存在该功能!";
		}
		return "error";
	}

	// 绑定工位
	public String bindingStation() {
		if (id == null || id <= 0) {
			errorMessage = "不存在该一体机!请检查后重试!";
			return "error";
		}
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			errorMessage = machineServer.binDingMachine(machine, usersId);
			if ("绑定成功！".equals(errorMessage)) {
				url = "OneMachineAction_findMachineById.action?id=" + id;
				return "error";
			}
			errorMessage = "绑定用户失败";
		} else {
			errorMessage = "不存在该一体机!请检查后重试!";
		}
		return "error";
	}

	/******************************* 灯 *******************************/
	// 条件查询工位
	public String findOneLightCondition() {
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			if (light != null) {
				ActionContext.getContext().getSession().put("light", light);
			} else {
				light = (OneLight) ActionContext.getContext().getSession().get(
						"light");
			}
			Object[] object = machineServer.findAllOneLight(light, Integer
					.parseInt(cpage), pageSize);// 条件查询所有工位
			if (object != null && object.length > 0) {
				lightList = (List<OneLight>) object[0];
				if (lightList != null && lightList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("OneMachineAction_findOneLightCondition.action?id="
									+ id);
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的工位不存在或者已经与该一体机绑定!";
				}
				// 已绑定人员处理
				banglightList = machineServer.findAllBangLight(id);
				if (banglightList != null && banglightList.size() > 0) {
					count = banglightList.size();// 绑定人员数量
				} else {
					count = 0;
				}
			} else {
				errorMessage = "抱歉!您查询的灯不存在或者已经与该一体机绑定!";
			}
			return "oneLightbangding";
		} else {
			errorMessage = "该功能不存在，请重试!";
			return "error";
		}
	}

	// 跳转到绑定工位页面(查看该一体机已绑定的灯和所有未绑定的灯)
	public String findOneLightById() {
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			// 未绑定灯处理
			Object[] object = machineServer.findAllOneLight(light, Integer
					.parseInt(cpage), pageSize);// 条件查询所有未绑定
			if (object != null && object.length > 0) {
				lightList = (List<OneLight>) object[0];
				if (lightList != null && lightList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("OneMachineAction_findOneLightById.action?id="
							+ id);
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的灯号不存在或者已经与该设备绑定!";
				}
			} else {
				errorMessage = "抱歉!您查询的灯号不存在或者已经与该设备绑定!";
			}
			// 已绑定灯处理
			banglightList = machineServer.findAllBangLight(id);
			if (banglightList != null && banglightList.size() > 0) {
				count = banglightList.size();
			} else {
				count = 0;
			}
			return "oneLightbangding";
		} else {
			errorMessage = "不存在该功能!";
		}
		return "error";
	}

	// 绑定工位
	public String bindingOneLight() {
		if (id == null || id <= 0) {
			errorMessage = "不存在该一体机!请检查后重试!";
			return "error";
		}
		machine = machineServer.byIdOneMachine(id);
		if (machine != null) {
			errorMessage = machineServer.binDingOneLight(machine.getId(),
					lightId);
			if ("绑定成功！".equals(errorMessage)) {
				url = "OneMachineAction_findOneLightById.action?id=" + id;
				return "error";
			}
			errorMessage = "绑定用户失败";
		} else {
			errorMessage = "不存在该一体机!请检查后重试!";
		}
		return "error";
	}

	// 显示登录人工位领工序信息
	public String findProcardByGxNum() {
		// 如果是无卡(员工卡)领料
		if (!"".equals(gongwei)) {
			// 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号
			procardList = machineServer.findProcardListByGwAndCard(gongwei);
			return "Procard_noCardList_2";
		} else {
		}
		return "error";
	}

	// 显示登录人可领工序信息
	public String findProcardByCardGxNum() {
		pageSize = 50;
		if (procard != null) {
			ActionContext.getContext().getSession().put("Procards_", procard);
		} else {
			procard = (Procard) ActionContext.getContext().getSession().get(
					"Procards_");
		}
		// 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号
		Object[] o = procardServer.findProcardListByUserCard(procard, Integer
				.parseInt(cpage), pageSize, "00", "loginLingGx", null);
		if (o != null && o.length > 0) {
			procardList = (List<Procard>) o[0];
			int count = (Integer) o[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			boolean isnext = true;
			while (isnext) {
				if (procardList == null || procardList.size() < 50) {
					int nextp = Integer.parseInt(cpage) + 1;
					if (pageCount < nextp) {
						isnext = false;
					} else {
						cpage = nextp + "";
						o = procardServer.findProcardListByUserCard(procard,
								Integer.parseInt(cpage), pageSize, "00",
								"loginLingGx", "hascount");
						if (o != null && o.length > 0) {
							procardList.addAll((List<Procard>) o[0]);
						}
					}
				} else {
					isnext = false;
				}
			}

			this.setUrl("OneMachineAction_findProcardByCardGxNum.action?id="
					+ id + "&pageStatus=" + pageStatus + "&tc=" + tc);
			if (procardList != null && procardList.size() > 0) {
				errorMessage = null;
			} else {
				// errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		// 如果是无卡(员工卡)领料
		// 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号
		// procardList = procardServer.findProcardListByUserCard("00",
		// "loginLingGx",null);
		return "Procard_noCardList_2";// Procard_noCardList.jsp
	}

	public OneMachine getMachine() {
		return machine;
	}

	public void setMachine(OneMachine machine) {
		this.machine = machine;
	}

	public OneMachineServer getMachineServer() {
		return machineServer;
	}

	public void setMachineServer(OneMachineServer machineServer) {
		this.machineServer = machineServer;
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

	public TaSopGongwei getTaSopGongwei() {
		return taSopGongwei;
	}

	public void setTaSopGongwei(TaSopGongwei taSopGongwei) {
		this.taSopGongwei = taSopGongwei;
	}

	public List<OneMachine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<OneMachine> machineList) {
		this.machineList = machineList;
	}

	public List<TaSopGongwei> getGongweiList() {
		return gongweiList;
	}

	public void setGongweiList(List<TaSopGongwei> gongweiList) {
		this.gongweiList = gongweiList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<TaSopGongwei> getBanggongweiList() {
		return banggongweiList;
	}

	public void setBanggongweiList(List<TaSopGongwei> banggongweiList) {
		this.banggongweiList = banggongweiList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer[] getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer[] usersId) {
		this.usersId = usersId;
	}

	public String getGongwei() {
		return gongwei;
	}

	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
	}

	public ProcardServer getProcardServer() {
		return procardServer;
	}

	public void setProcardServer(ProcardServer procardServer) {
		this.procardServer = procardServer;
	}

	public List<OneLight> getLightList() {
		return lightList;
	}

	public void setLightList(List<OneLight> lightList) {
		this.lightList = lightList;
	}

	public List<OneLight> getBanglightList() {
		return banglightList;
	}

	public void setBanglightList(List<OneLight> banglightList) {
		this.banglightList = banglightList;
	}

	public OneLight getLight() {
		return light;
	}

	public void setLight(OneLight light) {
		this.light = light;
	}

	public Integer[] getLightId() {
		return lightId;
	}

	public void setLightId(Integer[] lightId) {
		this.lightId = lightId;
	}

	/**
	 * @return the procard
	 */
	public Procard getProcard() {
		return procard;
	}

	/**
	 * @param procard
	 *            the procard to set
	 */
	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	/**
	 * @return the pageStatus
	 */
	public String getPageStatus() {
		return pageStatus;
	}

	/**
	 * @param pageStatus
	 *            the pageStatus to set
	 */
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	/**
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}

	/**
	 * @param tc
	 *            the tc to set
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}

}
