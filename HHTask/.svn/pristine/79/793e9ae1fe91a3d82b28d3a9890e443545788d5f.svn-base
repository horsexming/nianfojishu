package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProcessSopTempServer;
import com.task.entity.shizhi.CraftComplexity;
import com.task.entity.shizhi.CraftLoad;
import com.task.entity.shizhi.ProProcessDifficulty;
import com.task.entity.shizhi.ProcessSopTemp;

/**
 * 技能系数Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProcessSopTempAction {
	private ProcessSopTempServer processSopTempServer;//技能系数服务层
	private ProcessSopTemp processSopTemp;//技能系数对象
	private List<ProcessSopTemp> processSopTempList;//技能系数列表
	private List<CraftComplexity> ccList;//工艺复杂系数
    private List<ProProcessDifficulty> ppdList;//加工难点系数
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String updateAll;//是否修改同工序名称工艺负荷系数
	
 public String showList(){
		if (processSopTemp != null) {
			ActionContext.getContext().getSession().put("processSopTemp",
					processSopTemp);
		    } else {//用来保持分页时带着查询条件
		    	processSopTemp = (ProcessSopTemp) ActionContext.getContext().getSession().get("processSopTemp");
		      }
		Map<Integer, Object> map = processSopTempServer.findPocessSopTempsByCondition(
				processSopTemp, Integer.parseInt(cpage), pageSize);
		processSopTempList = (List<ProcessSopTemp>) map.get(1);// 显示页的技能系数列表
			if (processSopTempList != null & processSopTempList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("processSopTempAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "processSopTemp_show";
 }
 
 public String add(){
	 return "processSopTemp_add";
 }
 
 public String toupdate(){
	 Map<Integer, Object> map = processSopTempServer.getScoreMap(processSopTemp.getId());
	 processSopTemp= (ProcessSopTemp) map.get(1);
	 if(processSopTemp==null){
		 successMessage="未找到对应的工序模板!";
		 return showList();
	 }
	 ccList = (List<CraftComplexity>) map.get(2);
	 ppdList = (List<ProProcessDifficulty>) map.get(3);
	 return"processSopTemp_update";
	 
 }
 
 public String update(){
	 boolean b = processSopTempServer.update(processSopTemp,updateAll);
		if (b) {
			this.successMessage = "修改成功！";
			processSopTemp = null;
			return showList();
		} else {
			this.successMessage = "修改失败";
			return toupdate();
		}
 }
 
 public String todelete(){
	 boolean b = processSopTempServer.delete(processSopTemp);
		if (b) {
			this.successMessage = "删除成功！";
			processSopTemp = null;
		} else {
			this.successMessage = "删除失败";
		}
		return showList();
 }
 
 public String updateAll(){
	 boolean b=processSopTempServer.updateAll();
	 if(b){
		 successMessage="更新完成!";
	 }else{
		 errorMessage = "更新失败,请检查后重试!";
	 }
	 return showList();
 }
 //get set方法
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

public ProcessSopTempServer getProcessSopTempServer() {
	return processSopTempServer;
}

public void setProcessSopTempServer(ProcessSopTempServer processSopTempServer) {
	this.processSopTempServer = processSopTempServer;
}

public ProcessSopTemp getProcessSopTemp() {
	return processSopTemp;
}

public void setProcessSopTemp(ProcessSopTemp processSopTemp) {
	this.processSopTemp = processSopTemp;
}

public List<ProcessSopTemp> getProcessSopTempList() {
	return processSopTempList;
}

public void setProcessSopTempList(List<ProcessSopTemp> processSopTempList) {
	this.processSopTempList = processSopTempList;
}

public String getUpdateAll() {
	return updateAll;
}

public void setUpdateAll(String updateAll) {
	this.updateAll = updateAll;
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
 
 
}
