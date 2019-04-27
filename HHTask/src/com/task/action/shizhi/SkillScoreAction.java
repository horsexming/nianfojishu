package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.SkillScoreServer;
import com.task.entity.shizhi.SkillScore;

/**
 * 技能系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class SkillScoreAction {
	private SkillScoreServer skillScoreServer;//技能系数服务层
	private SkillScore skillScore;//技能系数对象
	private List<SkillScore> sScoreList;//技能系数列表
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
 public String showList(){
		if (skillScore != null) {
			ActionContext.getContext().getSession().put("skillScore",
					skillScore);
		    } else {//用来保持分页时带着查询条件
		    	skillScore = (SkillScore) ActionContext.getContext().getSession().get("skillScore");
		      }
		Map<Integer, Object> map = skillScoreServer.findSkillScoresByCondition(
				skillScore, Integer.parseInt(cpage), pageSize);
			sScoreList = (List<SkillScore>) map.get(1);// 显示页的技能系数列表
			if (sScoreList != null & sScoreList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("skillScoreAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "skillscore_show";
 }
 
 public String add(){
	 boolean b=skillScoreServer.add(skillScore);
	 if(b){
		 successMessage="添加成功！";
		 skillScore.setScore(null);//控制不按浮点数属性查询
		 skillScore.setTotalscore(null);
		 skillScore.setDifficultScore(null);
		 skillScore.setTotal(null);
		return showList();
	 }else{ 
		 errorMessage="添加失败！";
	  return "skillscore_add";
	 }
 }
 
 public String toupdate(){
	SkillScore skillScore2=skillScoreServer.getById(skillScore.getId());
	if(skillScore2!=null){
		skillScore=skillScore2;
		 return "skillscore_update";
			
	}else{
		errorMessage="修改失败,不存在该技能系数！";
	}
	return showList();
 }
 public String update(){
	 boolean b=skillScoreServer.update(skillScore);
	 if(b){
		 successMessage="修改成功！";
		 skillScore=null;
		 return showList();
	 }else{
		 errorMessage="修改失败！";
		 return "skillscore_update";
	 }
 }
 public String delete(){
	 boolean b=skillScoreServer.deleteById(skillScore.getId());
	 if(b){
		 successMessage="删除成功！";
	 }else{
		 errorMessage="删除失败！";
	 }
	 skillScore=null;
	 return showList();
 }
 //get set方法

public List<SkillScore> getsScoreList() {
	return sScoreList;
}
public SkillScoreServer getSkillScoreServer() {
	return skillScoreServer;
}

public void setSkillScoreServer(SkillScoreServer skillScoreServer) {
	this.skillScoreServer = skillScoreServer;
}

public SkillScore getSkillScore() {
	return skillScore;
}

public void setSkillScore(SkillScore skillScore) {
	this.skillScore = skillScore;
}

public void setsScoreList(List<SkillScore> sScoreList) {
	this.sScoreList = sScoreList;
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
 
 
}
