package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.BonusShiZhiServer;
import com.task.entity.shizhi.BonusShiZhi;
import com.task.entity.shizhi.ProTryMakeScore;

/**
 * 月奖金额Action层
 * 
 * @author 唐晓斌
 * 
 */
public class BonusShiZhiAction {
	private BonusShiZhiServer bonusShiZhiServer;//技能系数服务层
	private BonusShiZhi bonusShiZhi;//技能系数对象
	private ProTryMakeScore proTryMakeScore;//项目试制评分对象
	private List<BonusShiZhi> bonusList;//技能系数列表
	
	
	private List<ProTryMakeScore> hadPtmList;//已绑定的项目试制评审列表
	private List<ProTryMakeScore> unHadPtmList;//未绑定的项目试制评审列表
	private Integer count;//已绑定的项目试制评审数量
	private int[] checkboxs;//将要绑定的项目评审的id;
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
 public String showList(){
		if (bonusShiZhi != null) {
			ActionContext.getContext().getSession().put("bonusShiZhi",
					bonusShiZhi);
		    } else {//用来保持分页时带着查询条件
		    	bonusShiZhi = (BonusShiZhi) ActionContext.getContext().getSession().get("bonusShiZhi");
		      }
		Map<Integer, Object> map = bonusShiZhiServer.findBonusShiZhisByCondition(
 				bonusShiZhi, Integer.parseInt(cpage), pageSize);
		bonusList = (List<BonusShiZhi>) map.get(1);// 显示页的技能系数列表
			if (bonusList != null & bonusList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("bonusShiZhiAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "bonus_show";
 }
 
 
 public String add(){
	 List<BonusShiZhi>bonusList= bonusShiZhiServer.findAll();
	 if(bonusList!=null){
		 for(BonusShiZhi bShiZhi:bonusList){
			 if(bShiZhi.getMonth()!=null
					 &&bonusShiZhi.getMonth()!=null
					 &&bShiZhi.getMonth().equals(bonusShiZhi.getMonth())){
				 errorMessage="添加失败,该月的月奖金额已存在！";
				  return "bonus_add";
			 }
		 }
	 }
	 boolean b=bonusShiZhiServer.add(bonusShiZhi);
	 if(b){
		 bonusShiZhi.setBonus(null);//浮点数不参与查询
		 successMessage="添加成功！";
		 
		return showList();
	 }else{ 
		 errorMessage="添加失败！";
	  return "bonus_add";
	 }
 }
 
 public String toupdate(){
	 bonusShiZhi=bonusShiZhiServer.getById(bonusShiZhi.getId());
	if(bonusShiZhi!=null){
		 return "bonus_update";
			
	}else{
		errorMessage="修改失败,不存在该技能系数！";
	}
	return showList();
 }
 public String update(){
	 List<BonusShiZhi>bonusList= bonusShiZhiServer.findAll();
	 if(bonusList!=null){
		 for(BonusShiZhi bShiZhi:bonusList){
			 if(bShiZhi.getId()!=bonusShiZhi.getId()
					 &&bShiZhi.getMonth()!=null
					 &&bonusShiZhi.getMonth()!=null
					 &&bShiZhi.getMonth().equals(bonusShiZhi.getMonth())){
				 errorMessage="修改失败,该月的月奖金额已存在！";
				  return toupdate();
			 }
		 }
	 }
	 boolean b=bonusShiZhiServer.update(bonusShiZhi);
	 if(b){
		 successMessage="修改成功！";
		 bonusShiZhi=null;
		 return showList();
	 }else{
		 errorMessage="修改失败！";
		 return toupdate();
	 }
 }
 public String delete(){
	 boolean b=bonusShiZhiServer.deleteById(bonusShiZhi.getId());
	 if(b){
		 successMessage="删除成功！";
	 }else{
		 errorMessage="删除失败！";
	 }
	 bonusShiZhi=null;
	 return showList();
 }
 public String proTryMakeView(){
	 bonusShiZhi= bonusShiZhiServer.getById(bonusShiZhi.getId());
	 return "bonus_ptmsview";
 }
 
 //get set方法

public String getSuccessMessage() {
	return successMessage;
}
public BonusShiZhiServer getBonusShiZhiServer() {
	return bonusShiZhiServer;
}

public void setBonusShiZhiServer(BonusShiZhiServer bonusShiZhiServer) {
	this.bonusShiZhiServer = bonusShiZhiServer;
}

public BonusShiZhi getBonusShiZhi() {
	return bonusShiZhi;
}

public void setBonusShiZhi(BonusShiZhi bonusShiZhi) {
	this.bonusShiZhi = bonusShiZhi;
}

public List<BonusShiZhi> getBonusList() {
	return bonusList;
}

public void setBonusList(List<BonusShiZhi> bonusList) {
	this.bonusList = bonusList;
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

public ProTryMakeScore getProTryMakeScore() {
	return proTryMakeScore;
}

public void setProTryMakeScore(ProTryMakeScore proTryMakeScore) {
	this.proTryMakeScore = proTryMakeScore;
}


public List<ProTryMakeScore> getHadPtmList() {
	return hadPtmList;
}


public void setHadPtmList(List<ProTryMakeScore> hadPtmList) {
	this.hadPtmList = hadPtmList;
}


public List<ProTryMakeScore> getUnHadPtmList() {
	return unHadPtmList;
}


public void setUnHadPtmList(List<ProTryMakeScore> unHadPtmList) {
	this.unHadPtmList = unHadPtmList;
}


public Integer getCount() {
	return count;
}


public void setCount(Integer count) {
	this.count = count;
}


public int[] getCheckboxs() {
	return checkboxs;
}


public void setCheckboxs(int[] checkboxs) {
	this.checkboxs = checkboxs;
}


 
 
}
