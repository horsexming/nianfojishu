package com.task.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.BeanUtils;
import com.task.Server.IntegralServerDao;
import com.task.Server.IntegralsourceServerDao;
import com.task.Server.XiaoFeiServerDao;
import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.util.Util;

public class IntegralAction extends ActionSupport{
	private Integral integral; //积分表
	private Integralsource is;//积分来源
	private XiaoFei  xf;//消费表
	private List<Integralsource> isList;
	private List<XiaoFei> xfList;
	private List<Integral> inList;
	private IntegralServerDao integralServer;
	private IntegralsourceServerDao isServer;
	private XiaoFeiServerDao  xfServer;
	private String month;//本月月份
	
	private int id;
	private String errorMessage;
	private String successMessage;
	
	private int pageSize = 15;
	private String cpage="1";
	private String total;
	private String url;
	private String statue = "";
	//测试用的
	private String start;
	private String end;
	private Integer userId;
	
	private Integer xfjf;
	//页面跳转(添加);
	public String tiaozhuan(){
		return "tiaozhuan";
	}
	//页面跳转(消费，增加积分)
	public String tiaozhuan1(){
		setStatue(statue);
		setIntegral(integral);
		return "tiaozhuan1";
	}
	//页面跳转(test);
	public String tiaozhuan2(){
		return "tiaozhuan2";
	}
	//添加积分项(第一次添加给一个初始积分)
	public String  addIntegral(){
		errorMessage=integralServer.addIntegral(integral);
		if("true".equals(errorMessage)){
			successMessage="添加成功";
				return "tiaozhuan";
			}
		return ERROR;
	}
	//删除积分项(删除 积分来源记录，消费记录);
	public String delIntegral(){
		if(integral!=null){
			integral=integralServer.findIntegralbyid(integral.getId());
			boolean bool=integralServer.delIntegral(integral);
			if(bool){
				return "delSuccess" ;
			}
		}
		errorMessage="后台没有获取到数据";
		return ERROR;
	}
	//显示所有积分项(分页);
	public String showList(){
		int count=integralServer.getcont();
		int pageCount=(count+pageSize-1)/pageSize;
		this.setTotal(pageCount + "");
		month = Util.getDateTime("yyyy-MM");
		inList=integralServer.findAllByPage(Integer.parseInt(cpage), pageSize);
		//inList=integralServer.fIndAll();
		integral=null;
		if(inList!=null){
			this.setUrl("IntegralAction_showList.action");
			return "showlist";
		}
		return ERROR;
	}
	//条件查询(分页)
	public String findIntegral() {
		if ("person".equals(statue)) {
			Users user = Util.getLoginUser();
			integral = new Integral();
			integral.setIntegralName(user.getName());
			integral.setIntegrcode(user.getCode());
		}
		month = Util.getDateTime("yyyy-MM");
		if (integral != null) {
			ActionContext.getContext().getSession().put("integral", integral);
		} else {
			integral = (Integral) ActionContext.getContext().getSession().get(
					"integral");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = integralServer.findIntegralByCondition(integral, Integer
				.parseInt(cpage), pageSize);
		inList = (List<Integral>) map.get(1);
		if (inList != null && inList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("IntegralAction_findIntegral.action?statue=" + statue);
			return "showlist";//integral_showlist.jsp
		}
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return ERROR;
	}
	//更新积分项(增加积分，消费后减少积分);
	public String updateIntegral(){
		if(integral!=null){
			errorMessage=integralServer.updateIntegral(integral);
			if("true".equals(errorMessage)){
				successMessage="成功";
				return "tiaozhuan1";
			}
		}
		return null;
	}
	public void test1(){
		integral=integralServer.findIntegralbyid(55);
		Integral i=new Integral();
		BeanUtils.copyProperties(i ,integral,new String[]{"integralName","integrcode"});
		System.out.println(i.getIntegralName()+"  "+i.getIntegrcode());
		
	}
	//查看消费记录(个人);
//	public String ShowListXf(){
//		xfList=integralServer.getXiaoFeis(integral);
//		if(xfList!=null){
//			return "ShowListXf";
//		}
//		errorMessage = "没有找到你要查询的内容,请检查后重试!";
//		return ERROR;
//	}
	public String showListXf(){
		if(xf!=null){
			ActionContext.getContext().getSession().put("xf", xf);
		}else{
			xf=(XiaoFei) ActionContext.getContext().getSession().get("xf");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=xfServer.findIntegralByCondition(xf, Integer.parseInt(cpage), pageSize);
		xfList=(List<XiaoFei>)map.get(1);
		if(xfList!=null&&xfList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("IntegralAction_showListXf.action?statue="+statue);
			return "ShowListXf";
		}
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "listxferror";
	
	}
	//删除消费记录;
	public String delXiaoFei(){
		if(xf!=null){
			boolean bool=xfServer.delXiaoFei(xf);
			if(bool){
				errorMessage="删除成功";
				return "delXiaoFei";
			}
			errorMessage="删除失败";
		}
		return ERROR;
	}
	//查看积分来源项(个人);
//	public String ShowListIs(){
//		isList=integralServer.getiIntegralsources(integral);
//		if(isList!=null){
//			return "ShowListIs";
//		}
//		errorMessage="没有找到你要查询的内容！";
//		return ERROR;
//	}
	public String showListIs(){
			if(is!=null){
				ActionContext.getContext().getSession().put("is", is);
			}else{
				is=(Integralsource) ActionContext.getContext().getSession().get("is");
			}
			Map<Integer, Object> map=new HashMap<Integer,Object>();
			map=isServer.findIntegralByCondition(is, Integer.parseInt(cpage), pageSize);
			isList=(List<Integralsource>)map.get(1);
			if(isList!=null&&isList.size()>0){
				int count=(Integer)map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");  
				this.setUrl("IntegralAction_showListIs.action?statue=find");
				return "ShowListIs";
			}
		
		errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return ERROR;
	}
	//删除积分来源项;
	public String delIs(){
		if(is!=null){
			boolean bool=isServer.delIntegralsource(is);
			if(bool){
				errorMessage="删除成功";
				return "delIsSuccess";
			}
			errorMessage="删除失败";
		}
		return ERROR;
	}
	//查看积分来源项(所有)
	public String showListIsAll(){
		int count=isServer.getcont();
		int pageCount=(count+pageSize-1)/pageSize;
		this.setTotal(pageCount + "");
		isList=isServer.findAllByPage(Integer.parseInt(cpage), pageSize);
		//inList=integralServer.fIndAll();
		is=null;
		if(isList!=null){
			this.setUrl("IntegralAction_showListIsAll.action");
			return "ShowListIsAll";
		}
		return ERROR;
	}
	//查看消费记录(所有);
	public String showListXfAll(){
		int count=xfServer.getcont();
		int pageCount=(count+pageSize-1)/pageSize;
		this.setTotal(pageCount + "");
		xfList=xfServer.findAllByPage(Integer.parseInt(cpage), pageSize);
		//inList=integralServer.fIndAll();
		xf=null;
		if(xfList!=null){
			this.setUrl("IntegralAction_showListXfAll.action");
			return "ShowListXfAll";
		}
		return ERROR;
	}
	public String test() throws Exception{
		integralServer.xhjf2(start,userId);
		successMessage="成功!";
		return "tiaozhuan2";
	}
	//拉黑
	public String laheiIntegral(){
		boolean bool =	integralServer.laheiIntegral(id);
		if(bool){
			return "delSuccess";
		}
		errorMessage = "拉黑失败!";
		return ERROR;
	}
	public Integral getIntegral() {
		return integral;
	}
	public void setIntegral(Integral integral) {
		this.integral = integral;
	}
	public Integralsource getIs() {
		return is;
	}
	public void setIs(Integralsource is) {
		this.is = is;
	}
	public XiaoFei getXf() {
		return xf;
	}
	public void setXf(XiaoFei xf) {
		this.xf = xf;
	}
	public List<Integralsource> getIsList() {
		return isList;
	}
	public void setIsList(List<Integralsource> isList) {
		this.isList = isList;
	}
	
	public List<XiaoFei> getXfList() {
		return xfList;
	}
	public void setXfList(List<XiaoFei> xfList) {
		this.xfList = xfList;
	}
	public IntegralServerDao getIntegralServer() {
		return integralServer;
	}
	public void setIntegralServer(IntegralServerDao integralServer) {
		this.integralServer = integralServer;
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
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
	public List<Integral> getInList() {
		return inList;
	}
	public void setInList(List<Integral> inList) {
		this.inList = inList;
	}
	public IntegralsourceServerDao getIsServer() {
		return isServer;
	}
	public void setIsServer(IntegralsourceServerDao isServer) {
		this.isServer = isServer;
	}
	public XiaoFeiServerDao getXfServer() {
		return xfServer;
	}
	public void setXfServer(XiaoFeiServerDao xfServer) {
		this.xfServer = xfServer;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getXfjf() {
		return xfjf;
	}
	public void setXfjf(Integer xfjf) {
		this.xfjf = xfjf;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
