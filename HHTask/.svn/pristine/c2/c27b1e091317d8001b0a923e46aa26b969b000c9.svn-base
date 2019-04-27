package com.task.action.gzbj;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.gzbj.GzstoreUseLogServer;
import com.task.entity.gzbj.GzstoreUseLog;

public class GzstoreUseLogAction extends ActionSupport {
   private GzstoreUseLog gzstoreUseLog;
   private GzstoreUseLogServer gzstoreUseLogServer;
   private List<GzstoreUseLog> gzstoreUseLogList;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	
	public String showList(){
		if (gzstoreUseLog != null) {
			ActionContext.getContext().getSession().put("gzstoreUseLog",
					gzstoreUseLog);
		    } else {//用来保持分页时带着查询条件
		    	gzstoreUseLog = (GzstoreUseLog) ActionContext.getContext().getSession().get("gzstoreUseLog");
		      }
		Map<Integer, Object> map = gzstoreUseLogServer.findGzstoreUseLogsByCondition(
				gzstoreUseLog, Integer.parseInt(cpage), pageSize);
		gzstoreUseLogList = (List<GzstoreUseLog>) map.get(1);// 显示页的技能系数列表
			if (gzstoreUseLogList != null & gzstoreUseLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("gzstoreUseLogAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "gzstoreUseLog_show";
	}
	
   public String toUpdate(){
	   gzstoreUseLog=gzstoreUseLogServer.getById(gzstoreUseLog.getId());
	   if(gzstoreUseLog==null){
		   successMessage="没有找到目标!";
		   return showList();
	   }
	   return null;
   }
   public String delete(){
	   boolean b=gzstoreUseLogServer.deleteById(gzstoreUseLog.getId());
	   gzstoreUseLog=null;
	   if(b){
		   successMessage="删除成功!";
	   }else{
		   successMessage="删除失败!";
	   }
	   return showList();
   }
	public GzstoreUseLog getGzstoreUseLog() {
		return gzstoreUseLog;
	}

	public void setGzstoreUseLog(GzstoreUseLog gzstoreUseLog) {
		this.gzstoreUseLog = gzstoreUseLog;
	}

	public GzstoreUseLogServer getGzstoreUseLogServer() {
		return gzstoreUseLogServer;
	}

	public void setGzstoreUseLogServer(GzstoreUseLogServer gzstoreUseLogServer) {
		this.gzstoreUseLogServer = gzstoreUseLogServer;
	}

	public List<GzstoreUseLog> getGzstoreUseLogList() {
		return gzstoreUseLogList;
	}

	public void setGzstoreUseLogList(List<GzstoreUseLog> gzstoreUseLogList) {
		this.gzstoreUseLogList = gzstoreUseLogList;
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
	
}
