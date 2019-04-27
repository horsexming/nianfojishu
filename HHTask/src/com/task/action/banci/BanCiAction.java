package com.task.action.banci;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.banci.BanCiServer;
import com.task.ServerImpl.banci.BanCiServerImpl;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.banci.BanCiTime;
import com.task.entity.banci.SchedulingTable;
import com.task.util.MKUtil;
import com.task.util.Util;

/***
 * 班次Action层
 * 
 * @author 聂威
 * 
 */
@SuppressWarnings("unchecked")
public class BanCiAction extends ActionSupport {
	private BanCiServer banCiServer;
	private BanCi banCi;
	private Users user;
	private List<BanCi> banciList;
	private List<BanCiTime> banciTimeList;
	private List<Users> usersList;// 未绑定
	private List<Users> bangusersList;// 已绑定
	private String cpage = "1";
	private String banci1;
	private String banci2;
	private String tager;//类型 1：交换 2：将1的人转移到2
	private int pageSize = 15;
	private String errorMessage;
	private String ERROR;
	private String total;
	private String tag;//删除状态
	private String url;
	private Integer[] usersId;
	private int count = 0;// 已绑定用户数量
	private String startTime;
	private String endTime;
	private SchedulingTable schedulingTable;
	private String usersIds;
	private Integer id;
	

	/**
	 * 查询所有的班次
	 * 
	 * @return
	 */
	public Object findbanCi() {
		if (banCi != null) {
			ActionContext.getContext().getSession().put("banCi", banCi);
		} else {
			banCi = (BanCi) ActionContext.getContext().getSession()
					.get("banCi");
		}
		Object[] object = this.banCiServer.findbanCi(banCi, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			banciList = (List<BanCi>) object[0];
			if (banciList != null && banciList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("banCiAction_findbanCi.action?tag="+tag);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}

		return "findbanCi";

	}
	
	public String toaddbanci(){
		return "addbanCi";
	}

	// 添加班次
	public String addbanCi() {
		errorMessage = banCiServer.addbanCi(banCi,banciTimeList);
		if ("true".equals(errorMessage)) {
			errorMessage = "添加成功!";
		}
		url = "banCiAction_toaddbanci.action?errorMessage="+errorMessage;
		return "error";
	}

	// 根据id查询班次=>修改
	public String salBanCiByid() {
		if (banCi!=null) {
			banCi = this.banCiServer.salBanCiByid(banCi.getId());
			if(banCi!=null){
				return "salBanCiByid";
			}
			errorMessage = "班次为空，请检查后重试";
		}
		return "error";
	}
	
	// 调换班次页面
	public String tochangeBanCi() {
		return "changeBanCi";
	}
	
	// 调换班次页面
	public String changeBanCi() {
		if(banci1!=null&&!"".equals(banci1)&&banci2!=null&&!"".equals(banci2)){
			if(!banci1.equals(banci2)){
				errorMessage = banCiServer.changeBanCi(banci1, banci2, tager);
			}else {
				errorMessage = "班次相等，无法对调！";
			}
		}
		url="banCiAction_tochangeBanCi.action?errorMessage="+errorMessage;
		return "error";
	}

	// 更新班次
	public String updateBanCi() {
		errorMessage = this.banCiServer.updateBanCi(banCi,banciTimeList);
		if ("true".equals(errorMessage)) {
			errorMessage = "修改成功!";
			url = "banCiAction_salBanCiByid.action?banCi.id="+banCi.getId()+"&errorMessage="+errorMessage;
		}
		return "error";
	}

	// 删除班次
	public String delBanCi() {
		boolean b = this.banCiServer.delBanCi(banCi);
		if (b) {
			return "delBanCi";
		} else {
			return ERROR;
		}
	}

	// 给员工绑定班次
	public String bangding() {
		if (banCi != null) {
			errorMessage = banCiServer.bangDingBanci(usersId, banCi.getId());
			if ("绑定成功！".equals(errorMessage))
				url = "banCiAction_findUserById.action?banCi.id="
						+ banCi.getId()+"&tag="+tag;
			return "error";
		}
		errorMessage = "班次信息为空";
		return "error";
	}

	// 给员工解除绑定班次
	public String jiebangding() {
		if (banCi != null) {
			errorMessage = banCiServer.jieBangBanci(usersId, banCi.getId());
			if ("解绑成功！".equals(errorMessage))
				url = "banCiAction_findStationCondition.action?banCi.id="
						+ banCi.getId()+"&tag="+tag;
			return "error";
		}
		errorMessage = "班次信息为空";
		return "error";
	}

	// 条件查询已绑定用户
	public String findStationCondition() {
		banCi = banCiServer.salBanCiByid(banCi.getId());
		if (banCi != null) {
			if (user != null) {
				ActionContext.getContext().getSession().put("users", user);
			} else {
				user = (Users) ActionContext.getContext().getSession().get(
						"users");
			}
			Object[] object = banCiServer.findAllTagw(banCi.getId(), user,
					Integer.parseInt(cpage), pageSize,tag);// 条件查询所有用户
			if (object != null && object.length > 0) {
				bangusersList = (List<Users>) object[0];
				if (bangusersList != null && bangusersList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					if(user!=null&&user.getDept()!=null){
						String[] dept1 = user.getDept().split(";");
						if(dept1.length>1){
							pageCount = 1;
						}
					}
					this.setTotal(pageCount + "");
					this
							.setUrl("banCiAction_findStationCondition.action?banCi.id="
									+ banCi.getId());
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的员工不存在或者已绑班次!";
				}
				// 已绑定人员处理
				// bangusersList = banCiServer.findAllBangBc(banCi.getId());
				// if (bangusersList != null && bangusersList.size() > 0) {
				// count = bangusersList.size();// 绑定人员数量
				// // 去除已绑定人员
				// for (int i = 0; i < usersList.size(); i++) {
				// Users listus = usersList.get(i);
				// for (int j = 0; j < bangusersList.size(); j++) {
				// Users listusbang = bangusersList.get(j);
				// if (listus.getId().equals(listusbang.getId())) {
				// usersList.remove(listus);
				// i--;
				// }
				// }
				// }
				// }
			} else {
				errorMessage = "抱歉!您查询的员工不存在或者已绑班次!";
			}
			return "jiebangding_show";
		} else {
			errorMessage = "该功能不存在，请重试!";
			return "error";
		}
	}

	// 条件查询未绑定用户
	public String findUserById() {
		banCi = banCiServer.salBanCiByid(banCi.getId());
		if (banCi != null) {
			if (user != null) {
				ActionContext.getContext().getSession().put("userse", user);
			} else {
				user = (Users) ActionContext.getContext().getSession().get(
						"userse");
			}
			// 条件查询 未绑定人员处理
			Object[] object = banCiServer.findAllTagw(user, Integer
					.parseInt(cpage), pageSize,tag);// 条件查询所有用户
			if (object != null && object.length > 0) {
				usersList = (List<Users>) object[0];
				if (usersList != null && usersList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					if(user!=null&&user.getDept()!=null){
						String[] dept1 = user.getDept().split(";");
						if(dept1.length>1){
							pageCount = 1;
						}
					}
					this.setTotal(pageCount + "");
					this.setUrl("banCiAction_findUserById.action?banCi.id="
							+ banCi.getId());
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的用户不存在或者已绑班次!";
				}
			} else {
				errorMessage = "抱歉!您查询的用户不存在或者已经已绑班次!";
			}
			// // 已绑定人员处理
			// banggongweiList = machineServer.findAllBangGw(id);
			// if (banggongweiList != null && banggongweiList.size() > 0) {
			// count = banggongweiList.size();
			// }
			return "bancibangding_show";
		} else {
			errorMessage = "不存在该功能!";
		}
		return "error";
	}

	// 临时生成用户唯一图片标识
	public void shengchengimagenum() {
		banCiServer.shengchengimagenum();
	}
	
	//进入【排班页面
	public String schedulTablePage() {
		//查找所有班次
		Object[] object = this.banCiServer.findbanCi(banCi, 0, 0);
		if (object != null && object.length > 0) {
			banciList = (List<BanCi>) object[0];
		}
		
		return "SchedulRoot";
	}
	
	//查找所有班次
	public void findAllBanCi() {
		Object[] object = this.banCiServer.findbanCi(banCi, 0, 0);
		if (object != null && object.length > 0) {
			banciList = (List<BanCi>) object[0];
			MKUtil.writeJSON(banciList);
		}
	}
	
	//更新班次的排班
	public void updateSchedulTable() {
		try {
			errorMessage = banCiServer.settingScheduling(schedulingTable, tag);
		} catch (Exception e) {
			errorMessage = e.toString();
		}
		MKUtil.writeJSON(errorMessage);
	}
	
	//根据用户排班
	public void updateSchedulByUsersBanci() {
		errorMessage = banCiServer.settingSchedulingByUsersBanci(schedulingTable, usersIds);
		MKUtil.writeJSON(errorMessage);
	}
	
	//根据用户Id和开始及结束时间查找排班信息
	public void findSchedulIngByDate() {
		List<SchedulingTable> stList=null;
		if(tag!=null && tag.equals("self")) {
				stList = banCiServer.fandSchedulIngBySelfDate(user.getId(), startTime, endTime);
		}else {
			stList = banCiServer.fandSchedulIngByDate(banCi.getId(), startTime, endTime);
		}
		for (SchedulingTable schedulingTable : stList) {
			schedulingTable.setUsers(null);
			schedulingTable.setBanCi(null);
		}
		MKUtil.writeJSON(stList);
	}
	
	public void settingScheduling() {
		errorMessage = banCiServer.settingScheduling(schedulingTable, tag);
		MKUtil.writeJSON(errorMessage);
	}
	
	public void settingSchedulingByUsersBanci() {
		Map<String, Object> findIfyouReWork = BanCiServerImpl.updateORsearchIfyouReWork(id,null,startTime,null); 
		MKUtil.writeJSON(findIfyouReWork);
		
	}

	public BanCiServer getBanCiServer() {
		return banCiServer;
	}

	public void setBanCiServer(BanCiServer banCiServer) {
		this.banCiServer = banCiServer;
	}

	public BanCi getBanCi() {
		return banCi;
	}

	public void setBanCi(BanCi banCi) {
		this.banCi = banCi;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
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

	public List<BanCi> getBanciList() {
		return banciList;
	}

	public void setBanciList(List<BanCi> banciList) {
		this.banciList = banciList;
	}

	public Integer[] getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer[] usersId) {
		this.usersId = usersId;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public List<Users> getBangusersList() {
		return bangusersList;
	}

	public void setBangusersList(List<Users> bangusersList) {
		this.bangusersList = bangusersList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getBanci1() {
		return banci1;
	}

	public void setBanci1(String banci1) {
		this.banci1 = banci1;
	}

	public String getBanci2() {
		return banci2;
	}

	public void setBanci2(String banci2) {
		this.banci2 = banci2;
	}

	public String getTager() {
		return tager;
	}

	public void setTager(String tager) {
		this.tager = tager;
	}

	public List<BanCiTime> getBanciTimeList() {
		return banciTimeList;
	}

	public void setBanciTimeList(List<BanCiTime> banciTimeList) {
		this.banciTimeList = banciTimeList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public SchedulingTable getSchedulingTable() {
		return schedulingTable;
	}

	public void setSchedulingTable(SchedulingTable schedulingTable) {
		this.schedulingTable = schedulingTable;
	}

	public String getUsersIds() {
		return usersIds;
	}

	public void setUsersIds(String usersIds) {
		this.usersIds = usersIds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
