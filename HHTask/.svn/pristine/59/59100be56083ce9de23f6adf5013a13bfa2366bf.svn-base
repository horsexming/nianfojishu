package com.task.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import net.sf.morph.reflect.reflectors.SetReflector;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ChartNOSQServer;
import com.task.entity.ChartNOGzType;
import com.task.entity.ChartNOSC;
import com.task.entity.ChartNOSQ;
import com.task.util.MKUtil;

public class ChartNOSQAction {

	private ChartNOSQServer cqSever;
	private ChartNOSQ cq;
	private ChartNOSC cc;
	private List<ChartNOSQ> cqList;
	private List<ChartNOSC> ccList;
	private List<ChartNOGzType> cgtList;
	private ChartNOGzType gzType;
	private Integer id;
	private Integer[] arrayId;
	private String firstChartNO;
	private String secondChartNO;
	
	private String errorMessage;
	private String successMessage;
	private int pageSize = 15;
	private String cpage = "1";
	private String total;
	private String url;
	private String status = "";
	
	//查询所有图号申请记录
	public String findAllcqList(){
		if (cq != null) {
			ActionContext.getContext().getSession().put("cq", cq);
		} else {
			cq = (ChartNOSQ) ActionContext.getContext().getSession().get(
					"cq");
		}
		Object [] obj = cqSever.findAllChartNOSQ(cq, Integer.parseInt(cpage), pageSize,status);
				
		cqList = (List<ChartNOSQ>) obj[0];
		if (cqList != null && cqList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ChartNOSQAction_findAllcqList.action?status="+status);
		}
		return "cqList_show";
	}
	//查询所有图号
	public String findAllccList(){
		if (cc != null) {
			ActionContext.getContext().getSession().put("cc", cc);
		} else {
			cc = (ChartNOSC) ActionContext.getContext().getSession().get(
					"cc");
		}
		Object [] obj = cqSever.findAllChartNOSC(cc, Integer.parseInt(cpage), pageSize,status);
				
		ccList = (List<ChartNOSC>) obj[0];
		if (ccList != null && ccList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ChartNOSQAction_findAllccList.action?status="+status);
		}
		return "ccList_show";
	}
	//添加图号申请
	public String addcq(){
		if(cq!=null){
			errorMessage =	cqSever.addChartNOSQ(cq);
			if("true".equals(errorMessage)){
				return "findAllcqList";
			}
		}
		return "error";
	}
	//删除图号申请
	public String delcq(){
		if(cq!=null){
			errorMessage =	cqSever.delChartNOSQ(cq);
			if("true".equals(errorMessage)){
				errorMessage = "删除成功!";
			}
		}	
		return "error";
	}
	//删除图号
	public String delcc(){
		if(cc!=null){
			errorMessage = "删除失败!";
			boolean	bool =	cqSever.delChartNOSC(cc);
			if(bool){
				errorMessage = "删除成功!";
			}
		}
		return "error";
	}
	//根据Id获取图号申请信息
	public String findcqById(){
		Object [] obj = cqSever.findChartNOSQById(id);
		cq = (ChartNOSQ) obj[0];
		ccList = (List<ChartNOSC>) obj[1];
		return "cq_show";
	}
	//根据Id获取图号请信息
	public String findccById(){
		cc = cqSever.findChartNOSQByid(id);
		return "ChartNOSC_update";
	}
	//初始化添加
	public String initaddcq(){
		cgtList = cqSever.findAllChartNOGzType(status);
		return "cq_add";
	}
	public void getgzTypeList(){
		try {
			cgtList = cqSever.findAllChartNOGzType(status);
			MKUtil.writeJSON(cgtList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	public void getfirstNo(){
		cq = cqSever.getfirstNo(cq);
		MKUtil.writeJSON(cq);
	}
	public String pladdcq(){
		try {
			errorMessage =	cqSever.pladdChartNOSQ(cqList);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!";
		}
		return "cq_add";
	}
	//禁用图号
	public String disablecc(){
		boolean bool =	cqSever.disablecc(id);
		errorMessage = "禁用失败!";
		if(bool){
			errorMessage = "禁用成功!";
		}
		return "error";
	}
	//导出图号
	public String exportExcel(){
		errorMessage =	cqSever.export(cc, status);
		return "error";
	}
	//
	public String 	findAllgzType(){
		if ( gzType!= null) {
			ActionContext.getContext().getSession().put("gzType", gzType);
		} else {
			gzType = (ChartNOGzType) ActionContext.getContext().getSession().get(
					"gzType");
		}
		Object [] obj = cqSever.findAllgzType(gzType, Integer.parseInt(cpage), pageSize);
		cgtList = (List<ChartNOGzType>) obj[0];
		if (cgtList != null && cgtList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ChartNOSQAction_findAllgzType.action?status="+status);
		}
		return "gzType_show";
	}
	public String delgzType(){
		boolean	bool =	cqSever.delgzType(gzType);
		if(bool){
			errorMessage = "删除成功!";
			setUrl("ChartNOSQAction_findAllgzType.action?cpage="+cpage);
		}
		return "error";
	}
	public String addgzType(){
		boolean bool = cqSever.addgzType(gzType);
		if(bool){
			errorMessage = "true";
		}
		return "gzType_add";
	}
	
	public void changStyle(){
		Map<String, Object> map =	cqSever.changStyle(id);
		MKUtil.writeJSON(map);
	}
	//暂时停用一大批
	public String disableccList(){
		errorMessage = cqSever.disableccList(arrayId);
		setUrl("ChartNOSQAction_findAllccList.action?cpage="+cpage);
		return "error";
	}
	//重新申请
	public String cxsq(){
		errorMessage = cqSever.cxsq(cq);
		if("true".equals(errorMessage)){
			errorMessage = "重新申请成功!";
		}
		return "error";
	}
	
	//修改图号
	public String updatecc(){
		boolean bool = cqSever.updateChartNOSC(cc);
		return "findAllccList";
	}
	
	//重新排序
	public void ChartNOSort(){
		cqSever.ChartNOSort();
	}
	//处理跳层问题
	public void  dealWithJumpLayer(){
		try {
			errorMessage =cqSever.dealWithJumpLayer(firstChartNO, secondChartNO);
			MKUtil.writeJSON(errorMessage);
		} catch (RuntimeException e) {
			MKUtil.writeJSON(e.getMessage());
			e.printStackTrace();
		}
	}
	public void getCqByType(){
		try {
			cq =cqSever.getCqByType(secondChartNO);
			MKUtil.writeJSON(cq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public ChartNOSQServer getCqSever() {
		return cqSever;
	}
	public void setCqSever(ChartNOSQServer cqSever) {
		this.cqSever = cqSever;
	}
	public ChartNOSQ getCq() {
		return cq;
	}
	public void setCq(ChartNOSQ cq) {
		this.cq = cq;
	}
	public ChartNOSC getCc() {
		return cc;
	}
	public void setCc(ChartNOSC cc) {
		this.cc = cc;
	}
	public List<ChartNOSQ> getCqList() {
		return cqList;
	}
	public void setCqList(List<ChartNOSQ> cqList) {
		this.cqList = cqList;
	}
	public List<ChartNOSC> getCcList() {
		return ccList;
	}
	public void setCcList(List<ChartNOSC> ccList) {
		this.ccList = ccList;
	}
	public List<ChartNOGzType> getCgtList() {
		return cgtList;
	}
	public void setCgtList(List<ChartNOGzType> cgtList) {
		this.cgtList = cgtList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ChartNOGzType getGzType() {
		return gzType;
	}
	public void setGzType(ChartNOGzType gzType) {
		this.gzType = gzType;
	}
	public Integer[] getArrayId() {
		return arrayId;
	}
	public void setArrayId(Integer[] arrayId) {
		this.arrayId = arrayId;
	}
	public String getFirstChartNO() {
		return firstChartNO;
	}
	public void setFirstChartNO(String firstChartNO) {
		this.firstChartNO = firstChartNO;
	}
	public String getSecondChartNO() {
		return secondChartNO;
	}
	public void setSecondChartNO(String secondChartNO) {
		this.secondChartNO = secondChartNO;
	}
	
	
	
	
	
	
}
