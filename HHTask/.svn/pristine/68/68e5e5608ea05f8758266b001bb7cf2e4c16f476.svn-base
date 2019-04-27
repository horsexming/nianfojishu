package com.task.action;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.JiMiLeiXingServerDao;
import com.task.Server.UserServer;
import com.task.entity.JiMiLeiXing;
import com.task.entity.Users;
import com.task.util.MKUtil;


public class JiMiLeiXingAction extends ActionSupport{

	private JiMiLeiXing jimileixing;
	private JiMiLeiXingServerDao jimileixingserver;
	private List<JiMiLeiXing> JiMiList;
	private Users u;
	private String errorMessage;
	private String successMessage;
	private UserServer userServer;
	private Integer id;
	private List<Users> userList;
	private List<Users> bangUserList;//已绑定的用户
	private int count = 0;// 已绑定用户数量
	private Users user;
	private Integer [] uid;
	private String pageStatus;
	
	private int pageSize = 15;
	private String cpage="1";
	private String total;
	private String url;
	private String statue = "";
	
	
	//添加机密类型;
	public String addJiMi(){
		JiMiList=jimileixingserver.showJiMiList();
		boolean bool=true;
		for (int i = 0; i <JiMiList.size(); i++) {
			if(JiMiList.get(i).getType().equals(jimileixing.getType())){
				bool=false;
				errorMessage="已有该机密等级无需添加！";
			}
		}
		if(bool){
			if(jimileixingserver.addJiMi(jimileixing)){
				errorMessage="添加成功！";
				return "addJiMi";
			}
		}
		return ERROR;
	}
	//删除机密类型；
	public String delJiMi(){
		if(jimileixingserver.delJiMi(jimileixing)){
			errorMessage="删除成功！";
		}
		errorMessage="删除失败！";
		return "addJiMi";
	}
	//修改机密类型；
	public String updateJiMi(){
		if(jimileixingserver.updateJiMi(jimileixing)){
			successMessage="修改成功!";
		}
		errorMessage="修改失败！";
		return "showJiMiListByid";
	}
	//查询机密类型（所有）;
	public String showJiMiList(){
		int count=jimileixingserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		JiMiList=jimileixingserver.findAllByPage(Integer.parseInt(cpage), pageSize);
		jimileixing=null;
		if(JiMiList!=null){
			this.setUrl("jimileixing_showJiMiList.action");
			return "showJiMiList";
		}
		errorMessage="暂时还没有机密等级!";
		return ERROR;
	}
	// 1.通过Id查询模块功能 2、查询已绑定用户 3、查询所有未绑定用户
	@SuppressWarnings("unchecked")
	public String findMfById() {
		JiMiList=jimileixingserver.showJiMiList();
		if(id!=null){
			jimileixing = jimileixingserver.showJiMiListByid(id);
		}
		if (jimileixing != null) {
			// 未绑定人员处理
			Object[] object = userServer.findAllUser("jimileixing", id,
					Integer.parseInt(cpage), pageSize);// 条件查询所有用户
			if (object != null && object.length > 0) {
				userList = (List<Users>) object[0];
				if (userList != null && userList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("jimileixing_findMfById.action?id="
							+ id);
				} else {
					errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
				}
			} else {
				errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
			}
			// 已绑定人员处理
			bangUserList = userServer.findAllBangUser("jimileixing", id);
			if (bangUserList != null && bangUserList.size() > 0) {
				count = bangUserList.size();
			}
			return "findMfByIdSuccess";
		} else {
			errorMessage = "请选择机密等级!";
			return "findMfByIdSuccess";
		}
		
	}
	// 用户条件查询
	@SuppressWarnings("unchecked")
	public String findUsersByCondition() {
		jimileixing = jimileixingserver.showJiMiListByid(id);
		JiMiList=jimileixingserver.showJiMiList();
		if (jimileixing != null) {
			if (user != null) {
				ActionContext.getContext().getSession().put("adminUser", user);
			} else {
				user = (Users) ActionContext.getContext().getSession().get(
						"adminUser");
			}
			Object[] object = userServer.findUsersByCondition(user, Integer
					.parseInt(cpage), pageSize, "houtai",null);// 条件查询所有用户
			if (object != null && object.length > 0) {
				userList = (List<Users>) object[0];
				if (userList != null && userList.size() > 0) {
					int sum = (Integer) object[1];
					int pageCount = (sum + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("jimileixing_findUsersByCondition.action?id="
									+ id);
					errorMessage = null;
				} else {
					errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
				}
				// 已绑定人员处理
				bangUserList = userServer.findAllBangUser("jimileixing", id);
				if (bangUserList != null && bangUserList.size() > 0) {
					count = bangUserList.size();// 绑定人员数量
					// 去除已绑定人员
					for (int i = 0; i < userList.size(); i++) {
						Users listUser = userList.get(i);
						for (int j = 0; j < bangUserList.size(); j++) {
							Users binUser = bangUserList.get(j);
							if (listUser.getId().equals(binUser.getId())) {
								userList.remove(listUser);
								i--;
							}
						}
					}
				}

			} else {
				errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
			}
			return "findMfByIdSuccess";

		} else {
			errorMessage = "该功能不存在，请重试!";
			return ERROR;
		}
	}
	
	//根据id查询机密类型;
	public String showJiMiListByid(){
		jimileixing=jimileixingserver.showJiMiListByid(jimileixing.getId());
		return  "showJiMiListByid";
	}
	//绑定人员机密等级；
	public String bangdingjimi(){
		jimileixing = jimileixingserver.showJiMiListByid(id);
		if(jimileixing!=null){
			boolean bool=jimileixingserver.BDJiMi(jimileixing, uid);
			if(bool){
				successMessage = "绑定用户成功!";
				ActionContext.getContext().getSession().put("successMessage",
						successMessage);
				return "updateMfSuccess";
			}else{
				errorMessage = "绑定用户失败";
			}
		}else{
			errorMessage = "不存在该功能!请检查后重试!";
		}
		return ERROR;
	}

	public String findAllJiMi(){
		JiMiList=jimileixingserver.showJiMiList();
			if (JiMiList == null) {
				errorMessage = "没有查询到机密等级！";
			}
		return "findAllJiMi";
	}
	public  void finAllDeptNumberForSetlect(){
		successMessage=jimileixingserver.finAllDeptNumberForSetlect();
		if(successMessage!=null&&successMessage.length()>0){
			try {
				MKUtil.writeJSON(successMessage);
			} catch (Exception e) {
				MKUtil.writeJSON(e);
			}
		}
		
	}
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public JiMiLeiXing getJimileixing() {
		return jimileixing;
	}

	public void setJimileixing(JiMiLeiXing jimileixing) {
		this.jimileixing = jimileixing;
	}

	public JiMiLeiXingServerDao getJimileixingserver() {
		return jimileixingserver;
	}

	public void setJimileixingserver(JiMiLeiXingServerDao jimileixingserver) {
		this.jimileixingserver = jimileixingserver;
	}
	public List<JiMiLeiXing> getJiMiList() {
		return JiMiList;
	}
	public void setJiMiList(List<JiMiLeiXing> jiMiList) {
		JiMiList = jiMiList;
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
	public Users getU() {
		return u;
	}
	public void setU(Users u) {
		this.u = u;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Integer[] getUid() {
		return uid;
	}
	public void setUid(Integer[] uid) {
		this.uid = uid;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	
	
	
}
