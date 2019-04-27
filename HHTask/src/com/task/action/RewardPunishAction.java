package com.task.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.RewardPunishServer;
import com.task.entity.RewardPunish;
import com.task.entity.RewardPunishCollect;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class RewardPunishAction extends ActionSupport {
	private RewardPunish rewardPunish=new RewardPunish();
	private Date startDate;
	private Date endDate;
	private List<RewardPunish> rewardPunishList;
	private List<RewardPunishCollect> rewardPunishCollectList;

	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	
	private RewardPunishServer rewardPunishServer;

	// 添加加班记录
	public String addRewardPunish() {
		rewardPunishServer.addRewardPunish(this.rewardPunish);
		return "addRewardPunish_success";
	}

	// 删除加班记录
	public String deleteRewardPunish() {
		RewardPunish rewardPunish = rewardPunishServer.getRewardPunishById(this.rewardPunish.getId());
		rewardPunishServer.deleteRewardPunish(rewardPunish);
		return "deleteRewardPunish_success";
	}

	// 更新加班记录
	public String updateRewardPunish() {
		RewardPunish rewardPunish = rewardPunishServer.getRewardPunishById(this.rewardPunish.getId());
		
			//BeanUtils.copyProperties(this.overtime, overtime,new String[]{"id","status","filnalStartDate","filnalEndDate"});
			Integer id=this.rewardPunish.getId();//id
			Integer userId=this.rewardPunish.getUserId();//用户ID
			String name=this.rewardPunish.getName();//姓名
			String code=this.rewardPunish.getCode();//工号
			String dept=this.rewardPunish.getDept();//部门
			Date date=this.rewardPunish.getDate();//日期
			String project=this.rewardPunish.getProject();//项目
			String type=this.rewardPunish.getType();//奖扣类型
			Double money=this.rewardPunish.getMoney();//金额
			String explain=this.rewardPunish.getExplain();//金额
			if (id != null && id!=0) {
				
			}
			if (userId != null && userId!=0) {
				rewardPunish.setUserId(userId);
			}
			if (name != null && !name.equals("")) {
				rewardPunish.setName(name);
			}
			if (code != null && !code.equals("")) {
				rewardPunish.setCode(code);
			}
			if (dept != null && !dept.equals("")) {
				rewardPunish.setDept(dept);
			}
			if (date != null && !date.equals("")) {
				rewardPunish.setDate(date);
			}
			if (project != null && !project.equals("")) {
				rewardPunish.setProject(project);
			}
			if (type != null && !type.equals("")) {
				rewardPunish.setType(type);
			}
			if (money != null && !money.equals("")) {
				rewardPunish.setMoney(money);
			}
			if (explain != null && !explain.equals("")) {
				rewardPunish.setExplain(explain);
			}
			
			rewardPunishServer.updateRewardPunish(rewardPunish);
		return "updaeRewardPunish_success";
	}
	
	//获得修改页面
	public String getUpdatePage(){
		this.rewardPunish=rewardPunishServer.getRewardPunishById(this.rewardPunish.getId());
		return "getUpdatePage";
	}

	// 获得加班记录
	public RewardPunish getRewardPunishById() {
		return rewardPunishServer.getRewardPunishById(this.rewardPunish.getId());
	}

	// 获得加班记录集合
	public String findAllRewardPunish() {
		Map map = new HashMap();
		Integer id=this.rewardPunish.getId();//id
		String name=this.rewardPunish.getName();//姓名
		String code=this.rewardPunish.getCode();//工号
		String dept=this.rewardPunish.getDept();//部门
		Date date=this.rewardPunish.getDate();//日期
		String project=this.rewardPunish.getProject();//项目
		String type=this.rewardPunish.getType();//奖扣类型
		Double money=this.rewardPunish.getMoney();//金额
		if (name != null && !name.equals("")) {
			map.put("name", name);
		}
		if (code != null && !code.equals("")) {
			map.put("code", code);
		}
		if (dept != null && !dept.equals("")) {
			map.put("dept", dept);
		}
		if (date != null && !date.equals("")) {
			map.put("date", date);
		}
		if (project != null && !project.equals("")) {
			map.put("project", project);
		}
		if (type != null && !type.equals("")) {
			map.put("type", type);
		}
		if (money != null && !money.equals("")) {
			map.put("money", money);
		}
		Object[] object = rewardPunishServer.findAllRewardPunish(map, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			rewardPunishList = (List<RewardPunish>) object[0];
			if (rewardPunishList != null && rewardPunishList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("rewardPunishAction!findAllRewardPunish.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "hr_rewardPunish_list";
	}
	
	//按月份统计记录
	public String collectRewardPunishByMonth(){
		Map map=new HashMap();
		//if (startDate != null && !startDate.equals("")) {
		//	map.put("startDate", startDate);
		//}
		//if (endDate != null && !endDate.equals("")) {
		//	map.put("endDate", endDate);
		//}
		rewardPunishCollectList=rewardPunishServer.collectRewardPunishByMonth(map);
		return "hr_rewardPunish_collect";
	}
	

	// 查询所有部门为页面Select使用
	public String finAllMarkIdForSetlect() {
		String message = rewardPunishServer.finAllMarkIdForSetlect();
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 查询用户为页面input使用
	public String findUserByCode() {
		Users u = rewardPunishServer.findUserByCode(this.rewardPunish.getCode());
		if (u != null) {
			Users u1 = new Users();
			BeanUtils.copyProperties(u, u1, new String[] { "moduleFunction",
					"worklogClass", "template" });
			u1.getPassword().setUser(null);
			MKUtil.writeJSON(true, "查询成功", u1);
			// MKUtil.writeJSON(obj);
		}
		return null;
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

	public RewardPunish getRewardPunish() {
		return rewardPunish;
	}

	public void setRewardPunish(RewardPunish rewardPunish) {
		this.rewardPunish = rewardPunish;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<RewardPunish> getRewardPunishList() {
		return rewardPunishList;
	}

	public void setRewardPunishList(List<RewardPunish> rewardPunishList) {
		this.rewardPunishList = rewardPunishList;
	}

	public RewardPunishServer getRewardPunishServer() {
		return rewardPunishServer;
	}

	public void setRewardPunishServer(RewardPunishServer rewardPunishServer) {
		this.rewardPunishServer = rewardPunishServer;
	}

	public List<RewardPunishCollect> getRewardPunishCollectList() {
		return rewardPunishCollectList;
	}

	public void setRewardPunishCollectList(List<RewardPunishCollect> rewardPunishCollectList) {
		this.rewardPunishCollectList = rewardPunishCollectList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
