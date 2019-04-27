package com.task.action.menjin;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.ToolCabineServer;
import com.task.entity.Users;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.DrinksType;
import com.task.entity.menjin.ReceiveCabinet;
import com.task.entity.menjin.ToolCabine;
import com.task.util.MKUtil;

/**
 * 工具柜Action层 2016-06-08
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class ToolCabineAction {
	private ToolCabineServer toolCabineServer;// Server层
	private ToolCabine toolCabine;// 对象
	private DepositCabinet depositCabinet;// 对象
	private ReceiveCabinet receiveCabinet;// 对象
	private DrinksType drinksType;// //饮品表
	private List<DrinksType> drinksTypeList;//
	private List<ToolCabine> toolCabineList;// 集合
	private List<DepositCabinet> depositCabinetList;// 集合
	private List<ReceiveCabinet> receiveCabinetList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private String pageStatus;// 页面状态// 绑定功能
	private Users user;
	private Integer[] usersId;// 绑定人员
	private List<Users> userList;// 未绑定用户
	private List<Users> bangUserList;// 已绑定用户

	private String tag;// 标识(wp:用户物品柜/ck:仓库柜)
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private String an;//快递柜类型
	private int pageSize = 15;
	private int count = 0;// 已绑定用户数量
	private String oldpassword,newpassword,password;

	public String Test() {
		return "error";
	}

	public String toadd() {
		if ("wp".equals(tag))//用户柜
			return "toolCabine_add_1";
		else if ("ck".equals(tag))//衣柜
			return "toolCabine_add_2";
		else if ("kdg".equals(tag))//快递柜
			return "toolCabine_add_3";
		return "toolCabine_add";
	}

	// 分页显示
	// 显示查询内容
	public String showList() {
		if (toolCabine != null) {
			ActionContext.getContext().getSession().put("ToolCabine",
					toolCabine);
		} else {// 用来保持分页时带着查询条件
			toolCabine = (ToolCabine) ActionContext.getContext().getSession()
					.get("ToolCabine");
		}
		Map<Integer, Object> map = toolCabineServer.findToolCabine(toolCabine,
				Integer.parseInt(cpage), pageSize, tag);
		toolCabineList = (List<ToolCabine>) map.get(1);// 显示面试单列表
		if (toolCabineList != null && toolCabineList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ToolCabineAction_showList.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("wp".equals(tag))
			return "toolCabine_show_1";
		else if ("ck".equals(tag))
			return "toolCabine_show_2";
		else if ("kdg".equals(tag))
			return "toolCabine_show_4";
		return "toolCabine_show";
	}

	// 添加方法
	public String add() {
		if (toolCabine != null) {
			errorMessage = toolCabineServer.addToolCabine(toolCabine);
			if ("添加成功！".equals(errorMessage))
				url = "ToolCabineAction_showList.action?tag=" + tag;
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (toolCabine.getId() != null && toolCabine.getId() > 0
				&& toolCabine != null) {
			toolCabine = toolCabineServer.byIdToolCabine(toolCabine.getId());
			if (toolCabine != null) {
				if ("wp".equals(tag))
					return "toolCabine_update_1";
				else if ("ck".equals(tag))
					return "toolCabine_update_2";
				return "toolCabine_update";
			}
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdatemima() {
		if (toolCabine.getId() != null && toolCabine.getId() > 0
				&& toolCabine != null) {
			toolCabine = toolCabineServer.byIdToolCabine(toolCabine.getId());
			return "toolCabine_update_3";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法密码
	public String updatemima() {
		errorMessage = toolCabineServer.updatemima(id,oldpassword,newpassword);
		if ("修改成功！".equals(errorMessage))
			url = "ToolCabineAction_showpeople.action?";
		return "error";
	}
	// 修改方法
	public String update() {
		errorMessage = toolCabineServer.updateToolCabine(toolCabine);
		if ("修改成功！".equals(errorMessage))
			url = "ToolCabineAction_showList.action?tag=" + tag;
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id > 0 && id != null) {
			errorMessage = toolCabineServer.deleteToolCabine(id);
			if ("删除成功！".equals(errorMessage))
				url = "ToolCabineAction_showList.action?tag=" + tag
						+ "&pageStatus=" + pageStatus;
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	// 显示待存柜的物品
	public String showList_Depos() {
		if (depositCabinet != null) {
			ActionContext.getContext().getSession().put("DepositCabinet",
					depositCabinet);
		} else {// 用来保持分页时带着查询条件
			depositCabinet = (DepositCabinet) ActionContext.getContext()
					.getSession().get("DepositCabinet");
		}
		Map<Integer, Object> map = toolCabineServer.findDepositCabinet(
				depositCabinet, Integer.parseInt(cpage), pageSize, tag);
		depositCabinetList = (List<DepositCabinet>) map.get(1);// 显示面试单列表
		if (depositCabinetList != null && depositCabinetList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ToolCabineAction_showList_Depos.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "depositCabinet_show";
	}

	// 显示待取柜的物品
	public String showList_rece() {
		if (receiveCabinet != null) {
			ActionContext.getContext().getSession().put("ReceiveCabinet",
					receiveCabinet);
		} else {// 用来保持分页时带着查询条件
			receiveCabinet = (ReceiveCabinet) ActionContext.getContext()
					.getSession().get("ReceiveCabinet");
		}
		Map<Integer, Object> map = toolCabineServer.findReceiveCabinet(
				receiveCabinet, Integer.parseInt(cpage), pageSize);
		receiveCabinetList = (List<ReceiveCabinet>) map.get(1);// 显示面试单列表
		if (receiveCabinetList != null && receiveCabinetList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ToolCabineAction_showList_rece.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "receiveCabinet_show";
	}

	// 领取物品
	public String shenqingQu() {
		depositCabinet = toolCabineServer.byIdDepositCabinet(depositCabinet
				.getId());
		if (depositCabinet != null && depositCabinet.getArtQuantity() != null
				&& depositCabinet.getArtQuantity() > 0) {
			errorMessage = toolCabineServer.addReceiveCabinet(depositCabinet,
					receiveCabinet);
		}
		return "error";
	}

	// 存物品
	public String shenqingCun() {
		depositCabinet = toolCabineServer.byIdDepositCabinet(depositCabinet
				.getId());
		if (depositCabinet != null) {
			errorMessage = toolCabineServer.addErWeiMa(depositCabinet);
		}
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdateDr() {
		if (drinksType != null && drinksType.getId() != null
				&& drinksType.getId() > 0) {
			drinksType = toolCabineServer.byIdDrinksType(drinksType.getId());
			if (drinksType != null)
				return "drinksType_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String updateDr() {
		if (drinksType != null) {
			errorMessage = toolCabineServer.updateDrinksType(drinksType);
			if ("修改成功！".equals(errorMessage))
				return "drinksType_update";
			else
				return "error";
		}
		errorMessage = "对象为空";
		return "error";
	}

	// 显示所有饮品类型
	public String showList_Drinks() {
		if (drinksType != null) {
			ActionContext.getContext().getSession().put("DrinksType",
					drinksType);
		} else {// 用来保持分页时带着查询条件
			drinksType = (DrinksType) ActionContext.getContext().getSession()
					.get("DrinksType");
		}
		Map<Integer, Object> map = toolCabineServer.findDrinksType(drinksType,
				Integer.parseInt(cpage), pageSize);
		drinksTypeList = (List<DrinksType>) map.get(1);// 显示面试单列表
		if (drinksTypeList != null && drinksTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ToolCabineAction_showList_Drinks.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "drinksType_show";
	}

	// 条件查询柜子没绑定的人
	public String findToolCabCondition() {
		toolCabine = toolCabineServer.byIdToolCabine(toolCabine.getId());
		if (toolCabine != null) {
			if (user != null) {
				ActionContext.getContext().getSession().put("usersToolCab",
						user);
			} else {
				user = (Users) ActionContext.getContext().getSession().get(
						"usersToolCab");
			}
			Object[] object = toolCabineServer.findAllUsers(user, Integer
					.parseInt(cpage), pageSize, toolCabine.getId());// 条件查询所有未绑定的灯
			if (object != null && object.length > 0) {
				userList = (List<Users>) object[0];
				if (userList != null && userList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("ToolCabineAction_findToolCabCondition.action?toolCabine.id="
									+ toolCabine.getId());
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的灯不存在或者已经与该设备绑定!";
				}
				// 已绑定人处理
				bangUserList = toolCabineServer.findAllBangUsers(toolCabine
						.getId());
				if (bangUserList != null && bangUserList.size() > 0) {
					count = bangUserList.size();// 绑定灯数量
				} else {
					count = 0;
				}
			} else {
				errorMessage = "抱歉!您查询的灯不存在或者已经与该一体机绑定!";
			}
			return "guizibangding_show";
		} else {
			errorMessage = "该功能不存在，请重试!";
			return "error";
		}
	}

	// 跳转到绑定灯泡页面(查看该设备已绑定的灯和所有未绑定的灯)
	public String findUsersById() {
		toolCabine = toolCabineServer.byIdToolCabine(toolCabine.getId());
		if (toolCabine != null) {
			// 未绑定灯处理
			Object[] object = toolCabineServer.findAllUsers(user, Integer
					.parseInt(cpage), pageSize, toolCabine.getId());// 条件查询所有未绑定
			if (object != null && object.length > 0) {
				userList = (List<Users>) object[0];
				if (userList != null && userList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("ToolCabineAction_findUsersById.action?toolCabine.id="
									+ toolCabine.getId());
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的灯号不存在或者已经与该设备绑定!";
				}
			} else {
				errorMessage = "抱歉!您查询的灯号不存在或者已经与该设备绑定!";
			}
			// 已绑定灯处理
			bangUserList = toolCabineServer
					.findAllBangUsers(toolCabine.getId());
			if (bangUserList != null && bangUserList.size() > 0) {
				count = bangUserList.size();
			} else {
				count = 0;
			}
			return "guizibangding_show";
		} else {
			errorMessage = "不存在该功能!";
		}
		return "error";
	}

	// 给柜子绑定人
	public String bindingUsers() {
		if (toolCabine.getId() == null || toolCabine.getId() <= 0) {
			errorMessage = "不存在该门禁设备!请检查后重试!";
			return "error";
		}
		toolCabine = toolCabineServer.byIdToolCabine(toolCabine.getId());
		if (toolCabine != null) {
			if("ck".equals(tag)&&usersId!=null&&usersId.length>1){
				errorMessage = "衣柜只能绑定一个用户";
			}else {
				errorMessage = toolCabineServer.binDingUsers(
						toolCabine.getId(), usersId);
				if ("绑定成功！".equals(errorMessage)) {
					url = "ToolCabineAction_findUsersById.action?tag="+tag+"&toolCabine.id="
					+ toolCabine.getId();
					return "error";
				}
				errorMessage = "绑定用户失败";
			}
		} else {
			errorMessage = "不存在该柜子!请检查后重试!";
		}
		return "error";
	}
	
	// 根据登录人查询柜子新消息
	public String showpeople() {
		toolCabineList = toolCabineServer.findpeople();
		return "toolCabine_show_3";
	}

	// 获得所有仓库柜子
	public void findCkTool() {
		MKUtil.writeJSON(toolCabineServer.findCkTool());
	}
	// 获得所有仓库柜子
	public void findkdgTool() {
		MKUtil.writeJSON(toolCabineServer.findkdgTool(an));
	}

	
	public String getAn() {
		return an;
	}

	public void setAn(String an) {
		this.an = an;
	}

	public ToolCabineServer getToolCabineServer() {
		return toolCabineServer;
	}

	public void setToolCabineServer(ToolCabineServer toolCabineServer) {
		this.toolCabineServer = toolCabineServer;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public List<Users> getBangUserList() {
		return bangUserList;
	}

	public void setBangUserList(List<Users> bangUserList) {
		this.bangUserList = bangUserList;
	}

	public ToolCabine getToolCabine() {
		return toolCabine;
	}

	public void setToolCabine(ToolCabine toolCabine) {
		this.toolCabine = toolCabine;
	}

	public List<ToolCabine> getToolCabineList() {
		return toolCabineList;
	}

	public void setToolCabineList(List<ToolCabine> toolCabineList) {
		this.toolCabineList = toolCabineList;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public DepositCabinet getDepositCabinet() {
		return depositCabinet;
	}

	public void setDepositCabinet(DepositCabinet depositCabinet) {
		this.depositCabinet = depositCabinet;
	}

	public List<DepositCabinet> getDepositCabinetList() {
		return depositCabinetList;
	}

	public void setDepositCabinetList(List<DepositCabinet> depositCabinetList) {
		this.depositCabinetList = depositCabinetList;
	}

	public ReceiveCabinet getReceiveCabinet() {
		return receiveCabinet;
	}

	public void setReceiveCabinet(ReceiveCabinet receiveCabinet) {
		this.receiveCabinet = receiveCabinet;
	}

	public List<ReceiveCabinet> getReceiveCabinetList() {
		return receiveCabinetList;
	}

	public void setReceiveCabinetList(List<ReceiveCabinet> receiveCabinetList) {
		this.receiveCabinetList = receiveCabinetList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public DrinksType getDrinksType() {
		return drinksType;
	}

	public void setDrinksType(DrinksType drinksType) {
		this.drinksType = drinksType;
	}

	public List<DrinksType> getDrinksTypeList() {
		return drinksTypeList;
	}

	public void setDrinksTypeList(List<DrinksType> drinksTypeList) {
		this.drinksTypeList = drinksTypeList;
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

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// 构造方法

}
