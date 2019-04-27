package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IOutLibService;
import com.task.Server.UserServer;
import com.task.entity.FanghuOutLib;
import com.task.entity.OutLib;
import com.task.entity.Users;
import com.task.entity.VOOutLib;
import com.task.entity.sop.fhyp.FanghuYongpin;

public class OutLibAction {
	private IOutLibService ols;
	private List list;
	private String errorMessage;
	private OutLib ou;
	private VOOutLib voou = new VOOutLib();
	
	private Users user;
	private List<Users> userList;
	private FanghuOutLib fanghuOutLib;
	private List<FanghuOutLib> fanghuOutLibList;
	private UserServer userServer;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String cardID;//员工卡号
	private int id;//主键
	//读取发放人员信息
	public String findFangUserList(){
		
		return null;
	}
	//添加零用明细
	public String saveFanghu(){
		
		return null;
	}
	//管理发放明细
	public String findFafangList(){
		
		return null;
	}
	//刷卡零用
	public String scanCardLingyong(){
		//根据卡号判断要领取的物品
		Object[] object = ols.scanCardLingyong(cardID);		
		list=(List)object[0];
		id=(Integer)object[1];	
		return "scanCardLingyong";
	}
	//添加零用
	public String saveOutLib(){
		Object[] object = ols.scanCardLingyong(cardID);		
		list=(List)object[0];
		id=(Integer)object[1];		
		return "";
	}
	
	public String initQueryOutLib() {
		Object[] object = ols.queryOutLib(null, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("outlib_initQueryOutLib.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	@SuppressWarnings("unchecked")
	public String queryOutLibByCondition() {
		Map map = new HashMap();
		if (voou.getPerson() != null && !voou.getPerson().equals(""))
			map.put("person", voou.getPerson());
		if (voou.getName() != null && !voou.getName().equals(""))
			map.put("name", voou.getName());
		if (voou.getStandard() != null && !voou.getStandard().equals(""))
			map.put("standard", voou.getStandard());
		if (voou.getStorehouse() != null && !voou.getStorehouse().equals(""))
			map.put("storehouse", voou.getStorehouse());
		if (voou.getCategory() != null && !voou.getCategory().equals(""))
			map.put("category", voou.getCategory());
		if (voou.getPlace() != null && !voou.getPlace().equals(""))
			map.put("place", voou.getPlace());
		if (voou.getNumber() != null && !voou.getNumber().equals(""))
			map.put("number", voou.getNumber());
		if (voou.getStartTime() != null && !voou.getStartTime().equals(""))
			map.put("startTime", voou.getStartTime());
		if (voou.getEndTime() != null && !voou.getEndTime().equals(""))
			map.put("endTime", voou.getEndTime());
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("outlib", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"outlib");
			} else
				ActionContext.getContext().getSession().remove("outlib");
		}
		Object[] object = ols.queryOutLib(map, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("outlib_queryOutLibByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String del() {
		if (voou.getId() != null) {
			ols.delOutLibById(voou.getId());
		}
		return "redirectList";
	}

	public String initUpdate() {
		if (voou.getId() != null) {
			ou = ols.getOutLibById(voou.getId());
			if (ou == null) {
				return "redirectList";
			}
			return "success";
		}
		return "redirectList";
	}

	public String update() {
		if (ou != null && ou.getId() != null) {
			OutLib oldOutLib = ols.getOutLibById(ou.getId());
			BeanUtils.copyProperties(ou, oldOutLib, new String[] { "id",
					"store" });
			ols.update(oldOutLib);
			return "redirectList";
		}
		return "redirectList";
	}

	public String initAdd() {
		return "success";
	}

	public String add() {
		if (ou != null) {
			ols.add(ou);
		}
		return "redirectList";
	}

	public String export() {
		Map<String, Object> map = new HashMap<String, Object>();
		if (voou.getPerson() != null && !voou.getPerson().equals(""))
			map.put("person", voou.getPerson());
		if (voou.getStorehouse() != null && !voou.getStorehouse().equals(""))
			map.put("storehouse", voou.getStorehouse());
		if (voou.getCategory() != null && !voou.getCategory().equals(""))
			map.put("category", voou.getCategory());
		if (voou.getPlace() != null && !voou.getPlace().equals(""))
			map.put("place", voou.getPlace());
		if (voou.getNumber() != null && !voou.getNumber().equals(""))
			map.put("number", voou.getNumber());
		if (voou.getStartTime() != null && !voou.getStartTime().equals(""))
			map.put("startTime", voou.getStartTime());
		if (voou.getEndTime() != null && !voou.getEndTime().equals(""))
			map.put("endTime", voou.getEndTime());
		ols.exportExcel(map);
		return null;
	}
	
	
	/**
	 * 获得劳防用品添加page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuOutLibAddPage(){
		return "getFanghuOutLibAddPage_success";
	}
	
	/**
	 * 获得劳防用品更新page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuOutLibUpdatePage(){
		this.fanghuOutLib=this.ols.getFanghuOutLibById(this.fanghuOutLib.getId());
		return "getFanghuOutLibUpdatePage_success";
	}

	/**
	 * 添加劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String addFanghuOutLib(){
		Users user=this.userServer.findUserById(this.fanghuOutLib.getUserId());
		if(user!=null){
			this.fanghuOutLib.setUserName(user.getName());
			this.fanghuOutLib.setDept(user.getDept());
			this.fanghuOutLib.setCardNum(user.getCardId());
		}
		this.ols.addFanghuOutLib(this.fanghuOutLib);
		return "addFanghuOutLib_success";
	}
	/**
	 * 删除劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String deleteFanghuOutLib(){
		this.ols.deleteFanghuOutLib(this.fanghuOutLib);
		return "deleteFanghuOutLib_success";
	}
	/**
	 * 更新劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateFanghuOutLib(){
		this.ols.updateFanghuOutLib(this.fanghuOutLib);
		return "updateFanghuOutLib_success";
	}
	
	/**
	 * 获得劳防用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuOutLibById(Integer id){
		this.ols.getFanghuOutLibById(this.fanghuOutLib.getId());
		return "getFanghuOutLibById_success";
	}
	/**
	 * 获得劳防用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuOutLibListByUserId(){
		Object[] object = this.ols.getFanghuOutLibList(this.fanghuOutLib, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.fanghuOutLibList = (List<FanghuOutLib>) object[0];
			if (this.fanghuOutLibList != null && this.fanghuOutLibList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("outlib_getFanghuOutLibListByUserId.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else{
			errorMessage = null;
		}
		return "getFanghuOutLibListByUserId_success";
	}
	
	
	public String getUserListAll(){
		Object[] object = this.ols.getUserListAll(this.user, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.userList = (List<Users>) object[0];
			if (this.userList != null && this.userList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("outlib_getUserListAll.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else{
			errorMessage = null;
		}
		return "getUserListAll_success";
	}
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public IOutLibService getOls() {
		return ols;
	}

	public void setOls(IOutLibService ols) {
		this.ols = ols;
	}

	public OutLib getOu() {
		return ou;
	}

	public void setOu(OutLib ou) {
		this.ou = ou;
	}

	public VOOutLib getVoou() {
		return voou;
	}

	public void setVoou(VOOutLib voou) {
		this.voou = voou;
	}
	public FanghuOutLib getFanghuOutLib() {
		return fanghuOutLib;
	}
	public void setFanghuOutLib(FanghuOutLib fanghuOutLib) {
		this.fanghuOutLib = fanghuOutLib;
	}
	public List<FanghuOutLib> getFanghuOutLibList() {
		return fanghuOutLibList;
	}
	public void setFanghuOutLibList(List<FanghuOutLib> fanghuOutLibList) {
		this.fanghuOutLibList = fanghuOutLibList;
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
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
