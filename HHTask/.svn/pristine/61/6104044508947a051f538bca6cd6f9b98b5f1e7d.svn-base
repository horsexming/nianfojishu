package com.task.action.sop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.ProcessCollectSever;
import com.task.entity.Screen;
import com.task.entity.Users;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.ProcessCollect;
import com.task.entity.systemfile.FileleixingOrdengji;
import com.task.util.MKUtil;
import com.task.util.Util;

public class ProcessCollectAction extends ActionSupport {

	private ProcessCollect pc;// 工序汇总对对象;
	private List<ProcessCollect> pcList;//
	private ProcessCollectSever pcserver;
	private String startDate;// 开始时间
	private String endDate;// 结束时间

	private String errorMessage;
	private String successMessage;
	private int pageSize = 15;
	private String cpage = "1";
	private String total;
	private String url;
	private String status = "";
	private String tag ;
	private String workShop;//车间
	private String workShopName;//车间名字
	private String productStyle;//生产类型
	private Double spotWeldingCount = 0d;//电焊数量
	private Double needCount = 0d;//需求数量
	private Double finishCount = 0d;//完成数量
	private Double hascount =0d;//剩余数量
	private Screen screen;
	// 查询所有工序汇总
	public String findAllList() {
		int count = pcserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		pcList = pcserver.findAllPcList(Integer.parseInt(cpage), pageSize);
		if (pcList != null) {
			this.setUrl("ProcessCollectAction_findAllList.action");
		}
		for(ProcessCollect pc : pcList){
			if(pc.getProcesdianshu()!=null){
				spotWeldingCount =spotWeldingCount+ (pc.getProcesdianshu()*pc.getTjNumber());
			}
			needCount += pc.getFinalCount();
			finishCount += pc.getTjNumber();
			hascount +=(pc.getFinalCount()-pc.getTjNumber());
		}
		return "pc_showList";
	}
	
	// 条件查询工序汇总;
	public String showpcList() {
		if (pc != null) {
			ActionContext.getContext().getSession().put("pc", pc);
		} else {
			pc = (ProcessCollect) ActionContext.getContext().getSession().get(
					"pc");
		}
		if (startDate != null) {
			ActionContext.getContext().getSession().put("startDate", startDate);
		} else {
			startDate = (String) ActionContext.getContext().getSession().get(
			"startDate");
		}
		if (endDate != null) {
			ActionContext.getContext().getSession().put("endDate", endDate);
		} else {
			endDate = (String) ActionContext.getContext().getSession().get(
			"endDate");
		}
		if (workShop != null) {
			ActionContext.getContext().getSession().put("workShop", workShop);
		} else {
			workShop = (String) ActionContext.getContext().getSession().get(
			"workShop");
		}
		if (productStyle != null) {
			ActionContext.getContext().getSession().put("productStyle", productStyle);
		} else {
			productStyle = (String) ActionContext.getContext().getSession().get(
			"productStyle");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if("person".equals(status)){
			pc = new ProcessCollect();
			Users user =	Util.getLoginUser();
			pc.setUsercodes(user.getCode());
			pc.setUsernames(user.getName());
		}
		map = pcserver.findPcCondition(pc, Integer.parseInt(cpage), pageSize, startDate, endDate,workShop,productStyle);
		pcList = (List<ProcessCollect>) map.get(1);
		for(ProcessCollect pc : pcList){
			if(pc.getProcesdianshu()!=null){
				spotWeldingCount =spotWeldingCount+ (pc.getProcesdianshu()*pc.getTjNumber());
			}
			needCount += pc.getFinalCount();
			finishCount += pc.getTjNumber();
			hascount +=(pc.getFinalCount()-pc.getTjNumber());
		}
		if (pcList != null && pcList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ProcessCollectAction_showpcList.action?status="+status);

		}
		return "pc_showList";
	}
	
	//大屏幕显示工序汇总
	public String showpcListdpm() {
		if (screen != null) {
			ActionContext.getContext().getSession().put("screen", screen);
		} else {
			screen = (Screen) ActionContext.getContext().getSession().get(
			"screen");
		}
		pageSize = 30;
		 Map<Integer, Object> map = pcserver.findPcCondition(pc, Integer.parseInt(cpage), pageSize, null,null,screen,null);
		pcList = (List<ProcessCollect>) map.get(1);
		for(ProcessCollect pc : pcList){
			if(pc.getProcesdianshu()!=null){
				spotWeldingCount =spotWeldingCount+ (pc.getProcesdianshu()*pc.getTjNumber());
			}
			needCount += pc.getFinalCount();
			finishCount += pc.getTjNumber();
			hascount +=(pc.getFinalCount()-pc.getTjNumber());
		}
		if (pcList != null && pcList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ProcessCollectAction_showpcListdpm.action?status="+status+"&tag="+tag+"&screen.id="+screen.getId());

		}
		return "pc_showListPingMu";
	}
	//导出
	public String exportEXCEL(){
		if("hz".equals(status)){
			pcserver.explorExcel1(pc,startDate,endDate,status,workShop);
		}else{
			pcserver.explorExcel(pc,startDate,endDate,status,workShop,productStyle);
		}
		return null;
	}


	//Poi导出
	public String exportEXCELbyPoi(){
		if("hz".equals(status)){
			pcserver.explorExcelbyPoi(pc,startDate,endDate,status,workShop);
		}else{
            pcserver.explorExcelbyPoi(pc,startDate,endDate,status,workShop,productStyle);
		}
		return null;
	}

	public String findall() {
		List<String[]> list = pcserver.findScreen();
		MKUtil.writeJSON(list);
		return null;
	}
	//查询所有工序
	public void findAllProcessGzstore(){
		try {
			List<String> str = pcserver.findAllProcessGzstore();
			MKUtil.writeJSON(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ProcessCollect getPc() {
		return pc;
	}

	public void setPc(ProcessCollect pc) {
		this.pc = pc;
	}

	public List<ProcessCollect> getPcList() {
		return pcList;
	}

	public void setPcList(List<ProcessCollect> pcList) {
		this.pcList = pcList;
	}

	public ProcessCollectSever getPcserver() {
		return pcserver;
	}

	public void setPcserver(ProcessCollectSever pcserver) {
		this.pcserver = pcserver;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getWorkShop() {
		return workShop;
	}

	public void setWorkShop(String workShop) {
		this.workShop = workShop;
	}

	public String getWorkShopName() {
		return workShopName;
	}

	public void setWorkShopName(String workShopName) {
		this.workShopName = workShopName;
	}
	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public Double getSpotWeldingCount() {
		return spotWeldingCount;
	}

	public void setSpotWeldingCount(Double spotWeldingCount) {
		this.spotWeldingCount = spotWeldingCount;
	}

	public Double getNeedCount() {
		return needCount;
	}

	public void setNeedCount(Double needCount) {
		this.needCount = needCount;
	}

	public Double getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(Double finishCount) {
		this.finishCount = finishCount;
	}

	public Double getHascount() {
		return hascount;
	}

	public void setHascount(Double hascount) {
		this.hascount = hascount;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}


	

	
	
}
