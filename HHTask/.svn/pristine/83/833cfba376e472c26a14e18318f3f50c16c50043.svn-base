package com.task.action.gongyi.score;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.gongyi.score.GongyiGuichengScoreServer;
import com.task.entity.Users;
import com.task.entity.gongyi.gongxu.HanjieZuoyeGuifan;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.score.GongyiGuichengScore;
import com.task.entity.gongyi.score.GongyiGuichengScoreItem;
import com.task.entity.gongyi.score.GongyiGuichengScoreLeibie;
import com.task.entity.sop.fhyp.FanghuYongpin;
import com.task.entity.sop.fhyp.FanghuYongpinQuanxian;
import com.task.util.MKUtil;
/**
 * 
 * @author 陈曦
 *
 */
public class GongyiGuichengScoreAction extends ActionSupport {
	private GongyiGuichengScoreLeibie  gongyiGuichengScoreLeibie;
	private GongyiGuichengScoreLeibie  gongyiGuichengScoreJilu;
	private GongyiGuichengScoreLeibie  gongyiGuichengScoreXuanxiang;
	private List<GongyiGuichengScoreLeibie>  gongyiGuichengScoreLeibieList;
	private List<GongyiGuichengScoreLeibie>  gongyiGuichengScoreJiluList;
	private List<GongyiGuichengScoreLeibie>  gongyiGuichengScoreXuanxiangList;
	
	private GongyiGuichengScoreItem gongyiGuichengScoreItem;
	private GongyiGuichengScore gongyiGuichengScore;
	private GongyiGuichengScoreServer gongyiGuichengScoreServer;
	
	
	private String errorMessage;
	private String successMessage;
	/**分页*/
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	 
	public String getGongyiGuichengScorePage(){
		GongyiGuichengScore gongyiGuichengScore=this.gongyiGuichengScoreServer.getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(this.gongyiGuichengScore.getGongyiGuichengId(), this.gongyiGuichengScore.getModel(), this.gongyiGuichengScore.getProcessDataId());
		if(gongyiGuichengScore==null){
			this.gongyiGuichengScoreServer.addGongyiGuichengScore(this.gongyiGuichengScore);
		}
		return "getGongyiGuichengScorePage_success";
	}
	 /**
	 * 添加工艺规程伏分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String addGongyiGuichengScore(){
		this.gongyiGuichengScoreServer.addGongyiGuichengScore(this.gongyiGuichengScore);
		return null;
	}
	/**
	 * 删除工艺规程分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String deleteGongyiGuichengScore(){
		this.gongyiGuichengScoreServer.deleteGongyiGuichengScore(this.gongyiGuichengScore);
		return null;
	}
	/**
	 * 更新工艺规程分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String updateGongyiGuichengScore(){
		GongyiGuichengScore gongyiGuichengScore=this.gongyiGuichengScoreServer.getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(this.gongyiGuichengScore.getGongyiGuichengId(), this.gongyiGuichengScore.getModel(), this.gongyiGuichengScore.getProcessDataId());
		if(gongyiGuichengScore==null){
			this.gongyiGuichengScoreServer.addGongyiGuichengScore(this.gongyiGuichengScore);
		}else{
			this.gongyiGuichengScoreServer.updateGongyiGuichengScore(this.gongyiGuichengScore);
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}
	/**
	 * 获得工艺规程分数记录 根据工艺规程ID  模块  工序数据ID
	 * @param id
	 * @return
	 */
	public GongyiGuichengScore getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(){
		GongyiGuichengScore gongyiGuichengScore=this.gongyiGuichengScoreServer.getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(this.gongyiGuichengScore.getGongyiGuichengId(), this.gongyiGuichengScore.getModel(), this.gongyiGuichengScore.getProcessDataId());
		if(gongyiGuichengScore!=null){
			MKUtil.writeJSON(true, "操作成功", gongyiGuichengScore);
		}else{
			MKUtil.writeJSON(false, "未找到该条记录", null);
		}
		
		return null;
	}
	
	
	/***************************************************打分类别管理************************************************/
	/**
	 * 获得添加防护用品page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreLeibieAddPage(){
		return "getGongyiGuichengScoreLeibieAddPage_success";
	}
	
	/**
	 * 添加打分类别
	 * @param 
	 * @return
	 */
	public String addGongyiGuichengScoreLeibie(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.addGongyiGuichengScoreLeibie(this.gongyiGuichengScoreLeibie);
		return "addGongyiGuichengScoreLeibie_success";
	}
	/**
	 * 删除打分类别
	 * @param 
	 * @return
	 */
	public String deleteGongyiGuichengScoreLeibie(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.deleteGongyiGuichengScoreLeibie(this.gongyiGuichengScoreLeibie);
		return "deleteGongyiGuichengScoreLeibie_success";
	}
	
	/**
	 * 获得更新打分类别page
	 * @param 
	 * @return
	 */
	public String getGongyiGuichengScoreLeibieUpdatePage(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreLeibie.getId());
		this.gongyiGuichengScoreLeibie=gongyiGuichengScoreLeibie;
		return "getGongyiGuichengScoreLeibieUpdatePage_success";
	}
	/**
	 * 更新打分类别
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateGongyiGuichengScoreLeibie(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(this.gongyiGuichengScoreLeibie);
		return "updateGongyiGuichengScoreLeibie_success";
	}
	
	/**
	 * 获得防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String  getGongyiGuichengScoreLeibieById(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreLeibie.getId());
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreLeibie);
		return null;
	}
	/**
	 * 获得防护用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreLeibieListAll(){
		Object[] object = this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieListAll(this.gongyiGuichengScoreLeibie, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.gongyiGuichengScoreLeibieList = (List<GongyiGuichengScoreLeibie>) object[0];
			if (this.gongyiGuichengScoreLeibieList != null && this.gongyiGuichengScoreLeibieList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("gongyiGuichengScoreAction!getGongyiGuichengScoreLeibieListAll.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else{
			errorMessage = null;
		}
		return "getGongyiGuichengScoreLeibieListAll_success";
	}
	/**
	 * 获得防护用品集合For select
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreLeibieListAllForSelect(){
		List<GongyiGuichengScoreLeibie>  gongyiGuichengScoreLeibieList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieListAllForSelect(this.gongyiGuichengScoreLeibie);
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreLeibieList);
		return null;
	}
	/***************************************************打分记录管理************************************************/
	/**
	 * 获得添加防护用品page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreJiluAddPage(){
		return "getGongyiGuichengScoreJiluAddPage_success";
	}
	
	/**
	 * 添加打分类别
	 * @param 
	 * @return
	 */
	public String addGongyiGuichengScoreJilu(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=this.gongyiGuichengScoreServer.addGongyiGuichengScoreLeibie(this.gongyiGuichengScoreJilu);
		return "addGongyiGuichengScoreJilu_success";
	}
	/**
	 * 删除打分类别
	 * @param 
	 * @return
	 */
	public String deleteGongyiGuichengScoreJilu(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=this.gongyiGuichengScoreServer.deleteGongyiGuichengScoreLeibie(this.gongyiGuichengScoreJilu);
		return "deleteGongyiGuichengScoreJilu_success";
	}
	
	/**
	 * 获得更新打分类别page
	 * @param 
	 * @return
	 */
	public String getGongyiGuichengScoreJiluUpdatePage(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreJilu.getId());
		this.gongyiGuichengScoreJilu=gongyiGuichengScoreJilu;
		return "getGongyiGuichengScoreJiluUpdatePage_success";
	}
	/**
	 * 更新打分类别
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateGongyiGuichengScoreJilu(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(this.gongyiGuichengScoreJilu);
		return "updateGongyiGuichengScoreJilu_success";
	}
	
	/**
	 * 获得打分记录
	 * @param fanghuYongpin
	 * @return
	 */
	public String  getGongyiGuichengScoreJiluById(Integer id){
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreJilu.getId());
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreLeibie);
		return null;
	}
	/**
	 * 获得打分记录
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreJiluListAll(){
		this.gongyiGuichengScoreJiluList=(List<GongyiGuichengScoreLeibie>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreJiluListByParentId(this.gongyiGuichengScoreJilu.getParentId());
		return "getGongyiGuichengScoreJiluListAll_success";
	}
	
	/**
	 * 获得打分记录
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreJiluListByParentId(){
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreJiluListTemp=(List<GongyiGuichengScoreLeibie>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreJiluListByParentId(this.gongyiGuichengScoreJilu.getParentId());
		for(GongyiGuichengScoreLeibie  gongyiGuichengScoreLeibieTemp: gongyiGuichengScoreJiluListTemp){
			List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangListTemp=(List<GongyiGuichengScoreLeibie>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreXuanxiangListByParentId(gongyiGuichengScoreLeibieTemp.getId());
			gongyiGuichengScoreLeibieTemp.setGongyiGuichengScoreXuanxiangList(gongyiGuichengScoreXuanxiangListTemp);
		}
		
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreJiluListTemp);
		return null;
	}
	/***************************************************打分选项管理************************************************/
	/**
	 * 获得打分选项page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreXuanxiangAddPage(){
		return "getGongyiGuichengScoreXuanxiangAddPage_success";
	}
	
	/**
	 * 添加打分选项
	 * @param 
	 * @return
	 */
	public String addGongyiGuichengScoreXuanxiang(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang=this.gongyiGuichengScoreServer.addGongyiGuichengScoreLeibie(this.gongyiGuichengScoreXuanxiang);
		
		Integer gongyiGuichengScoreXuanxiangParentId=this.gongyiGuichengScoreXuanxiang.getParentId();
		Integer jiluScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreXuanxiangListByParentId(gongyiGuichengScoreXuanxiangParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiangTemp: gongyiGuichengScoreXuanxiangList){
			Integer score=gongyiGuichengScoreXuanxiangTemp.getScore();
			score=score==null?0: score;
			jiluScore=jiluScore>score?jiluScore: score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreJilu.setId(gongyiGuichengScoreXuanxiangParentId);
		gongyiGuichengScoreJilu.setScore(jiluScore);
		gongyiGuichengScoreJilu =this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreJilu);
		
		Integer gongyiGuichengScoreJiluParentId=gongyiGuichengScoreJilu.getParentId();
		Integer leibieScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreJiluList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreJiluListByParentId(gongyiGuichengScoreJiluParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreJiluTemp: gongyiGuichengScoreJiluList){
			Integer score=gongyiGuichengScoreJiluTemp.getScore();
			score=score==null?0: score;
			leibieScore+=score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreLeibie.setId(gongyiGuichengScoreJiluParentId);
		gongyiGuichengScoreLeibie.setScore(leibieScore);
		gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreLeibie);
		
		return "addGongyiGuichengScoreXuanxiang_success";
	}
	/**
	 * 删除打分选项
	 * @param 
	 * @return
	 */
	public String deleteGongyiGuichengScoreXuanxiang(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang=this.gongyiGuichengScoreServer.deleteGongyiGuichengScoreLeibie(this.gongyiGuichengScoreXuanxiang);
		
		Integer gongyiGuichengScoreXuanxiangParentId=this.gongyiGuichengScoreXuanxiang.getParentId();
		Integer jiluScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreXuanxiangListByParentId(gongyiGuichengScoreXuanxiangParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiangTemp: gongyiGuichengScoreXuanxiangList){
			Integer score=gongyiGuichengScoreXuanxiangTemp.getScore();
			score=score==null?0: score;
			jiluScore=jiluScore>score?jiluScore: score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreJilu.setId(gongyiGuichengScoreXuanxiangParentId);
		gongyiGuichengScoreJilu.setScore(jiluScore);
		gongyiGuichengScoreJilu =this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreJilu);
		
		Integer gongyiGuichengScoreJiluParentId=gongyiGuichengScoreJilu.getParentId();
		Integer leibieScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreJiluList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreJiluListByParentId(gongyiGuichengScoreJiluParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreJiluTemp: gongyiGuichengScoreJiluList){
			Integer score=gongyiGuichengScoreJiluTemp.getScore();
			score=score==null?0: score;
			leibieScore+=score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreLeibie.setId(gongyiGuichengScoreJiluParentId);
		gongyiGuichengScoreLeibie.setScore(leibieScore);
		gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreLeibie);
		
		return "deleteGongyiGuichengScoreXuanxiang_success";
	}
	
	/**
	 * 获得更新打分选项page
	 * @param 
	 * @return
	 */
	public String getGongyiGuichengScoreXuanxiangUpdatePage(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreXuanxiang.getId());
		this.gongyiGuichengScoreXuanxiang=gongyiGuichengScoreXuanxiang;
		return "getGongyiGuichengScoreXuanxiangUpdatePage_success";
	}
	/**
	 * 更新打分选项
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateGongyiGuichengScoreXuanxiang(){
		GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(this.gongyiGuichengScoreXuanxiang);
		
		Integer gongyiGuichengScoreXuanxiangParentId=this.gongyiGuichengScoreXuanxiang.getParentId();
		Integer jiluScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreXuanxiangListByParentId(gongyiGuichengScoreXuanxiangParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiangTemp: gongyiGuichengScoreXuanxiangList){
			Integer score=gongyiGuichengScoreXuanxiangTemp.getScore();
			score=score==null?0: score;
			jiluScore=jiluScore>score?jiluScore: score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreJilu=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreJilu.setId(gongyiGuichengScoreXuanxiangParentId);
		gongyiGuichengScoreJilu.setScore(jiluScore);
		gongyiGuichengScoreJilu =this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreJilu);
		
		Integer gongyiGuichengScoreJiluParentId=gongyiGuichengScoreJilu.getParentId();
		Integer leibieScore=0;
		List<GongyiGuichengScoreLeibie> gongyiGuichengScoreJiluList=this.gongyiGuichengScoreServer.getGongyiGuichengScoreJiluListByParentId(gongyiGuichengScoreJiluParentId);
		for(GongyiGuichengScoreLeibie gongyiGuichengScoreJiluTemp: gongyiGuichengScoreJiluList){
			Integer score=gongyiGuichengScoreJiluTemp.getScore();
			score=score==null?0: score;
			leibieScore+=score;
		}
		GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=new GongyiGuichengScoreLeibie();
		gongyiGuichengScoreLeibie.setId(gongyiGuichengScoreJiluParentId);
		gongyiGuichengScoreLeibie.setScore(leibieScore);
		gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.updateGongyiGuichengScoreLeibie(gongyiGuichengScoreLeibie);
		
		return "updateGongyiGuichengScoreXuanxiang_success";
	}
	
	/**
	 * 获得打分选项
	 * @param fanghuYongpin
	 * @return
	 */
	public String  getGongyiGuichengScoreXuanxiangById(Integer id){
		GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieById(this.gongyiGuichengScoreXuanxiang.getId());
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreLeibie);
		return null;
	}
	/**
	 * 获得打分选项集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getGongyiGuichengScoreXuanxiangListAll(){
		this.gongyiGuichengScoreXuanxiangList=(List<GongyiGuichengScoreLeibie>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreXuanxiangListByParentId(this.gongyiGuichengScoreXuanxiang.getParentId());
		return "getGongyiGuichengScoreXuanxiangListAll_success";
	}
	
	/******************************************更新工艺规程打分页面*********************************************************/
	public String getGongyiGuiChengMarkingPage(){
		return "getGongyiGuiChengMarkingPage_success";
	}
	public String updateGongyiGuiChengMarkingPage() {
		try {		
			//焊接作业规范
			String gongyiGuichengScoreItemParams=this.gongyiGuichengScoreItem.getParams();
			JSONArray gongyiGuichengScoreItemJson = JSONArray.fromObject(gongyiGuichengScoreItemParams);
			List<GongyiGuichengScoreItem> gongyiGuichengScoreItemList = (List<GongyiGuichengScoreItem>)JSONArray.toCollection(gongyiGuichengScoreItemJson, GongyiGuichengScoreItem.class);
			for(GongyiGuichengScoreItem gongyiGuichengScoreItem: gongyiGuichengScoreItemList){
				if(gongyiGuichengScoreItem!=null){
					if(gongyiGuichengScoreItem.getId()!=null){
						this.gongyiGuichengScoreServer.updateGongyiGuichengScoreItem(gongyiGuichengScoreItem);
					}else{
						this.gongyiGuichengScoreServer.addGongyiGuichengScoreItem(gongyiGuichengScoreItem);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}
	public String getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(){
		List<GongyiGuichengScoreItem> gongyiGuichengScoreItemList=(List<GongyiGuichengScoreItem>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(this.gongyiGuichengScoreItem);
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengScoreItemList);
		return null;
	}
	
	//判断是否可以提交
	public String isPresent(){
		boolean isPresent=true;
		List<GongyiGuichengScoreItem> gongyiGuichengScoreItemList=(List<GongyiGuichengScoreItem>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreItemUniqueListByGongyiGuichengIdAndProcessDataIdAndModel(this.gongyiGuichengScoreItem);
		for(GongyiGuichengScoreItem gongyiGuichengScoreItemTemp: gongyiGuichengScoreItemList){
			List<GongyiGuichengScoreItem> gongyiGuichengScoreItemListTemp=(List<GongyiGuichengScoreItem>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreItemListByProcessDataIdAndModel(gongyiGuichengScoreItemTemp);
			Integer totalScore=0;
			for(GongyiGuichengScoreItem gongyiGuichengScoreItemTempTemp: gongyiGuichengScoreItemListTemp){
				Integer score=gongyiGuichengScoreItemTempTemp.getScore();
				score=score==null?0: score;
				totalScore+=score;
			}
			GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieByBiaoshi(gongyiGuichengScoreItemTemp.getModel());
			Integer leibieScore=gongyiGuichengScoreLeibie.getScore();
			leibieScore=leibieScore==null?0: leibieScore;
			if(totalScore!=leibieScore){
				isPresent=false;
				break;
			}
		}
		if(isPresent){
			MKUtil.writeJSON(true, "操作成功", null);
		}else{
			MKUtil.writeJSON(false, "操作成功", null);
		}
		return null;
	}
	
	public String getGongyiGuichengScoreItemHuizong(){
		List list=new ArrayList();
		List<GongyiGuichengScoreItem> gongyiGuichengScoreItemListTemp=(List<GongyiGuichengScoreItem>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreItemUniqueListByGongyiGuichengIdAndProcessDataIdAndModel(this.gongyiGuichengScoreItem);
		for(GongyiGuichengScoreItem gongyiGuichengScoreItemTemp: gongyiGuichengScoreItemListTemp){
			List<GongyiGuichengScoreItem> gongyiGuichengScoreItemListTempTemp=(List<GongyiGuichengScoreItem>)this.gongyiGuichengScoreServer.getGongyiGuichengScoreItemListByProcessDataIdAndModel(gongyiGuichengScoreItemTemp);
			Integer totalScore=0;
			for(GongyiGuichengScoreItem gongyiGuichengScoreItemTempTemp: gongyiGuichengScoreItemListTempTemp){
				Integer score=gongyiGuichengScoreItemTempTemp.getScore();
				score=score==null?0: score;
				totalScore+=score;
			}
			
			GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie=this.gongyiGuichengScoreServer.getGongyiGuichengScoreLeibieByBiaoshi(gongyiGuichengScoreItemTemp.getModel());
			Integer leibieScore=gongyiGuichengScoreLeibie.getScore();
			leibieScore=leibieScore==null?0: leibieScore;
			
			Integer processDataId=gongyiGuichengScoreItemTemp.getProcessDataId();
			JSONObject jsonObject =new JSONObject();
			String name=null;
			Integer no=null;
			if(processDataId!=null){
				ProcessData processData=this.gongyiGuichengScoreServer.getProcessDataById(processDataId);
				name=processData.getGongxuName();
				no=processData.getGongxuNo();
				
			}else{
				String biaoshi=gongyiGuichengScoreItemTemp.getBiaoshi();
				if("gygcsy".equals(biaoshi)){
					name="工艺规程首页";
				}else{
					name="工艺程序图表";
				}
				no=0;
			}
			jsonObject.put("name", name);
			jsonObject.put("no", no);
			jsonObject.put("score", totalScore);
			jsonObject.put("leibieScore", leibieScore);
			list.add(jsonObject);
		}
		MKUtil.writeJSON(true, "操作成功", list);
		return null;
	}
	
	
	public GongyiGuichengScoreServer getGongyiGuichengScoreServer() {
		return gongyiGuichengScoreServer;
	}
	public void setGongyiGuichengScoreServer(GongyiGuichengScoreServer gongyiGuichengScoreServer) {
		this.gongyiGuichengScoreServer = gongyiGuichengScoreServer;
	}
	public GongyiGuichengScore getGongyiGuichengScore() {
		return gongyiGuichengScore;
	}
	public void setGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore) {
		this.gongyiGuichengScore = gongyiGuichengScore;
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
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreLeibie() {
		return gongyiGuichengScoreLeibie;
	}
	public void setGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie) {
		this.gongyiGuichengScoreLeibie = gongyiGuichengScoreLeibie;
	}
	public List<GongyiGuichengScoreLeibie> getGongyiGuichengScoreLeibieList() {
		return gongyiGuichengScoreLeibieList;
	}
	public void setGongyiGuichengScoreLeibieList(List<GongyiGuichengScoreLeibie> gongyiGuichengScoreLeibieList) {
		this.gongyiGuichengScoreLeibieList = gongyiGuichengScoreLeibieList;
	}
	public List<GongyiGuichengScoreLeibie> getGongyiGuichengScoreJiluList() {
		return gongyiGuichengScoreJiluList;
	}
	public void setGongyiGuichengScoreJiluList(List<GongyiGuichengScoreLeibie> gongyiGuichengScoreJiluList) {
		this.gongyiGuichengScoreJiluList = gongyiGuichengScoreJiluList;
	}
	public List<GongyiGuichengScoreLeibie> getGongyiGuichengScoreXuanxiangList() {
		return gongyiGuichengScoreXuanxiangList;
	}
	public void setGongyiGuichengScoreXuanxiangList(List<GongyiGuichengScoreLeibie> gongyiGuichengScoreXuanxiangList) {
		this.gongyiGuichengScoreXuanxiangList = gongyiGuichengScoreXuanxiangList;
	}
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreJilu() {
		return gongyiGuichengScoreJilu;
	}
	public void setGongyiGuichengScoreJilu(GongyiGuichengScoreLeibie gongyiGuichengScoreJilu) {
		this.gongyiGuichengScoreJilu = gongyiGuichengScoreJilu;
	}
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreXuanxiang() {
		return gongyiGuichengScoreXuanxiang;
	}
	public void setGongyiGuichengScoreXuanxiang(GongyiGuichengScoreLeibie gongyiGuichengScoreXuanxiang) {
		this.gongyiGuichengScoreXuanxiang = gongyiGuichengScoreXuanxiang;
	}
	public GongyiGuichengScoreItem getGongyiGuichengScoreItem() {
		return gongyiGuichengScoreItem;
	}
	public void setGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem) {
		this.gongyiGuichengScoreItem = gongyiGuichengScoreItem;
	}
	
}
