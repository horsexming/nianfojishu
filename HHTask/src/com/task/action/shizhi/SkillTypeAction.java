package com.task.action.shizhi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.SkillTypeServer;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;
/**
 * 技能分类Action层
 * 
 * @author 唐晓斌
 * 
 */
public class SkillTypeAction {
	private SkillTypeServer skillTypeServer;//技能分类服务层
	private SkillType skillType;//技能分类对象
	private List<SkillType> sTypeList;//技能分类展示列表
	private List<SkillScore> hadSScoreList;//绑定的技能系数列表
	private List<SkillScore> unHadSScoreList;//未绑定的技能系数列表
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int count;//绑定技能系数数量
	private int[] checkboxs;//将要绑定的技能系数的id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	/**
	 * 分页显示技能分类
	 * @return
	 */
	public String showList(){
		if (skillType != null) {
			ActionContext.getContext().getSession().put("skillType",
					skillType);
		    } else {//用来保持分页时带着查询条件
		    	skillType = (SkillType) ActionContext.getContext().getSession().get("skillType");
		      }
		Map<Integer, Object> map = skillTypeServer.findSkillTypesByCondition(
				skillType, Integer.parseInt(cpage), pageSize);
			sTypeList = (List<SkillType>) map.get(1);// 显示页的技能系数列表
			if (sTypeList != null & sTypeList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("skillTypeAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "skilltype_show";
 }
/**
 * 添加技能分类
 * @return
 */
 public String add(){
	 boolean b=skillTypeServer.add(skillType);
	 if(b){
		 successMessage="添加成功！";
		 skillType.setMaxscore(null);//控制查询的方式中没有浮点数属性
		 skillType.setMinscore(null);
		return showList();
	 }else{ 
		 errorMessage="添加失败！";
	  return "skilltype_add";
	 }
 }
 /**
  * 跳往修改技能分类页面
  * @return
  */
 public String toupdate(){
	SkillType skillType2=skillTypeServer.getById(skillType.getId());
	if(skillType2!=null){
		skillType=skillType2;
		 return "skilltype_update";			
	}else{
		errorMessage="修改失败,不存在该技能分类！";
	}
	return showList();
 }
 /**
  * 修改技能分类
  * @return
  */
 public String update(){
	 boolean b=skillTypeServer.update(skillType);
	 if(b){System.out.println(b);
		 successMessage="修改成功！";
		 skillType=null;//控制查询结果为之前位置
		 return showList();
	 }else{
		 errorMessage="修改失败！";
		 return "skilltype_update";
	 }
 }
 /**
  * 删除技能分类
  * @return
  */
 public String delete(){
	 boolean b=skillTypeServer.deleteById(skillType.getId());
	 if(b){
		 successMessage="删除成功！";
	 }else{
		 errorMessage="删除失败！";
	 }
	 skillType=null;
	 return showList();
 }
 /**
  * 展示技能系数信息
  * @return
  */
 public String skillScoreView(){
		Map<Integer,Object>map=	skillTypeServer.getSScoresMap(skillType.getId(),Integer.parseInt(cpage), pageSize);
		if(map!=null){
			hadSScoreList=(List<SkillScore>) map.get(1);
			unHadSScoreList=(List<SkillScore>) map.get(2);
			if(unHadSScoreList==null||unHadSScoreList.size()==0){
				errorMessage="对不起没有查到数据！";
			}
			this.skillType=(SkillType)map.get(3);
			this.count=hadSScoreList.size();
			this.total=map.get(4).toString();
			this.url="skillTypeAction_skillScoreView.action";
			return "skilltype_scoreview";
		}
		errorMessage="查看失败,不存在该技能分类！";
		return this.showList();

 }
 public String linkSkillScore(){
	boolean b= skillTypeServer.linkSkillScore(skillType.getId(),checkboxs);
	if(b){
		successMessage="绑定成功！";
	}else{
		errorMessage="绑定失败！";
	}
	return this.skillScoreView();
 }
//Get和Set方法
public SkillTypeServer getSkillTypeServer() {
	return skillTypeServer;
}

public void setSkillTypeServer(SkillTypeServer skillTypeServer) {
	this.skillTypeServer = skillTypeServer;
}

public SkillType getSkillType() {
	return skillType;
}

public void setSkillType(SkillType skillType) {
	this.skillType = skillType;
}

public List<SkillType> getsTypeList() {
	return sTypeList;
}

public void setsTypeList(List<SkillType> sTypeList) {
	this.sTypeList = sTypeList;
}



public List<SkillScore> getHadSScoreList() {
	return hadSScoreList;
}

public void setHadSScoreList(List<SkillScore> hadSScoreList) {
	this.hadSScoreList = hadSScoreList;
}

public List<SkillScore> getUnHadSScoreList() {
	return unHadSScoreList;
}

public void setUnHadSScoreList(List<SkillScore> unHadSScoreList) {
	this.unHadSScoreList = unHadSScoreList;
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
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int[] getCheckboxs() {
	return checkboxs;
}
public void setCheckboxs(int[] checkboxs) {
	this.checkboxs = checkboxs;
}



	
	

}
