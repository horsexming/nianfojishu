package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.CraftLoadServer;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.shizhi.SkillType;
import com.task.util.MKUtil;

/**
 * 技能系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class CraftLoadAction {
	private CraftLoadServer craftLoadServer;// 奖金负荷系数服务层
	private CraftLoad craftLoad;// 奖金负荷系数对象
	private List<CraftLoad> cLoadList;// 奖金负荷系数列表
//	private List<ProcessTemplate> qpInfoList;// 工序列表
    private List<CraftComplexity> ccList;//工艺复杂系数
    private List<ProProcessDifficulty> ppdList;//加工难点系数
    private List<SkillType> skillTypeList;//技能列表
    private List<SkillScore> skillScoreList;//技能系数列表
	private Integer id;
	private String flag;

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;


	public String showList() {
		if (craftLoad != null) {
			ActionContext.getContext().getSession().put("craftLoad", craftLoad);
		} else {// 用来保持分页时带着查询条件
			craftLoad = (CraftLoad) ActionContext.getContext().getSession()
					.get("craftLoad");
		}
		
		Map<Integer, Object> map = craftLoadServer.findCraftLoadsByCondition(
				craftLoad, Integer.parseInt(cpage), pageSize);
		cLoadList = (List<CraftLoad>) map.get(1);// 显示页的奖金负荷系数列表
		if (cLoadList != null & cLoadList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("craftLoadAction_showList.action?forreturn=1");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "cload_show";
	}

	public String toupdate() {
		Map<Integer, Object> map = craftLoadServer.getScoreMap(craftLoad
				.getId());
		craftLoad = (CraftLoad) map.get(1);
		ccList = (List<CraftComplexity>) map.get(2);
		ppdList = (List<ProProcessDifficulty>) map.get(3);
		return "cload_update";
	}

	public String update() {
		boolean b = craftLoadServer.update(craftLoad);
		if (b) {
			this.successMessage = "修改成功！";
			craftLoad = null;
			return showList();
		} else {
			this.errorMessage = "修改失败";
			return "cload_update";
		}
	}
	public String delete() {
		boolean b = craftLoadServer.deleteById(craftLoad.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		craftLoad = null;
		return showList();
	}
	 public void getSkills(){
		 if(id!=null&&id!=0&&flag!=null){
			 skillTypeList=craftLoadServer.getSkills(flag,id);
			 MKUtil.writeJSON(skillTypeList);
		 }else if(id!=null&&id==-1){
			 MKUtil.writeJSON(null);
		 }
	 }
	 public void getSkillScores(){
		 if(id!=null&&id!=0&&flag!=null){
			 skillScoreList=craftLoadServer.getSkillScores(id);
			 MKUtil.writeJSON(skillScoreList);
		 }
	 }

	// get set方法

	public String getSuccessMessage() {
		return successMessage;
	}

	public CraftLoadServer getCraftLoadServer() {
		return craftLoadServer;
	}

	public void setCraftLoadServer(CraftLoadServer craftLoadServer) {
		this.craftLoadServer = craftLoadServer;
	}

	public List<CraftLoad> getcLoadList() {
		return cLoadList;
	}

	public void setcLoadList(List<CraftLoad> cLoadList) {
		this.cLoadList = cLoadList;
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

//	public List<ProcessTemplate> getQpInfoList() {
//		return qpInfoList;
//	}
//
//	public void setQpInfoList(List<ProcessTemplate> qpInfoList) {
//		this.qpInfoList = qpInfoList;
//	}

	public CraftLoad getCraftLoad() {
		return craftLoad;
	}

	public void setCraftLoad(CraftLoad craftLoad) {
		this.craftLoad = craftLoad;
	}



	public List<CraftComplexity> getCcList() {
		return ccList;
	}

	public void setCcList(List<CraftComplexity> ccList) {
		this.ccList = ccList;
	}

	public List<ProProcessDifficulty> getPpdList() {
		return ppdList;
	}

	public void setPpdList(List<ProProcessDifficulty> ppdList) {
		this.ppdList = ppdList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<SkillType> getSkillTypeList() {
		return skillTypeList;
	}

	public void setSkillTypeList(List<SkillType> skillTypeList) {
		this.skillTypeList = skillTypeList;
	}

	public List<SkillScore> getSkillScoreList() {
		return skillScoreList;
	}

	public void setSkillScoreList(List<SkillScore> skillScoreList) {
		this.skillScoreList = skillScoreList;
	}

	
	

}
