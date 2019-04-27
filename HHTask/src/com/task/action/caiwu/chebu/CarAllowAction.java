package com.task.action.caiwu.chebu;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.CarAllowServer;
import com.task.Server.renshi.DimissionLogServer;
import com.task.entity.Credentials;
import com.task.entity.Users;
import com.task.entity.caiwu.CaiwuRecharge;
import com.task.entity.caiwu.CarAllowOneApp;
import com.task.entity.caiwu.CarAllowSumApp;
import com.task.util.Util;
/*
 * 车辆补贴
 */
	public class CarAllowAction extends ActionSupport {
	private CarAllowServer carAllowServer;
	private DimissionLogServer dimissionLogServer;//下拉条款列表
	private Credentials credentials;// 证件对象
	private CarAllowSumApp carAllowSum;   //汇总
	private CarAllowOneApp carAllowOne;  //申请明细
	private CaiwuRecharge caiwuRecharge; //充值/报销明细
	private Float chongzhi[]; //充值数组
	private Integer listId[];//充值汇总id
	private Users users;
	private String tag;
	private String message;
	private List<CarAllowSumApp> listSum;
	private List<CarAllowOneApp> listOne;
	private List<CaiwuRecharge> listRechage;
	private List list;
	private String errorMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	/**
	 * 根据登陆人的信息判定跳转
	 * @return
	 */
	public String selectAPPPage(){
		users=(Users)Util.getLoginUser();
		String code=users.getCode();//工号
		if(null!=carAllowServer.getOneCarAllowByCode(code)){		
			carAllowSum=carAllowServer.getOneCarAllowByCode(code);
			return "showOneSumAllow";
		}else{
			list=dimissionLogServer.findProvision("chebu");
			credentials=carAllowServer.getPlatNumber(code);
			return "sumbitOneInfor";
		}
	}
	/**
	 * 添加新的车牌信息
	 * @return
	 */
	public String saveCarAllowSum(){
		//carAllowSum.setPlatenumber(tag+message);
		if(carAllowServer.saveCarAllow(carAllowSum)){			
			return "saveOK";
		}
		
		return ERROR;
	}
	/**
	 * 查看个人的车牌及车补汇总信息
	 * @return
	 */
	public String findOneSum(){
		carAllowSum=carAllowServer.getOneCarAllow(id);		
		return "showOneSumAllow";
	}
	/**
	 * 查看所有车补汇总信息
	 * @return
	 */
	public String findAllSum(){
		this.pageSize = 15;
		this.setUrl("carAllowAction!findAllSum.action" );
		Object[] obj = carAllowServer.findsumAppList(carAllowSum, Integer.parseInt(cpage), pageSize,null);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "queryallSumAllow";
	}
	/**
	 * 删除 测试
	 * @return
	 */
	public String delsumAllow(){
		carAllowServer.deletesumAllow(id);
		return "deleteOK";
	}
	/**
	 * 申请登记
	 * @return
	 */
	public String appDengji(){
		carAllowSum=carAllowServer.getOneCarAllow(id);
		return "appDengji";
	}
	/**
	 * 添加报销申请
	 * @return
	 */
	public String saveOnecarAllow(){
		carAllowServer.saveOneAllowAPP(id,carAllowOne);
		return "dengjiOK";
	}
	//根据条件查询个人的申请历史记录
	public String appHistory(){
		this.pageSize = 15;
		this.setUrl("carAllowAction!appHistory.action" );
		Object[] obj = carAllowServer.findoneappHistory(id,Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "appHistory";
	}
	//删除个人申请记录
	public String deleteOneHistory(){
		if(carAllowServer.deleteOneHistory(id)){
			return "deleteOneHistoryOK";
		}else{
			return ERROR;
		}
		
	}
	public String preshezhiXiane(){
		carAllowSum=carAllowServer.getOneCarAllow(id);
		return "shezhiXiane";
	}
	//设置限额
	public String shezhiXiane(){
		carAllowServer.shezhiXiane(id,carAllowSum);
		return "shezhiXianeOK";
	}
	
	public String preChongzhi(){
		list=carAllowServer.findsumList();
		return "preChongzhi";
	}
	/**
	 * 财务添加报销或充值
	 * @return
	 */
	public String saveCaiwuRecharge(){
		list=carAllowServer.chongzhi(listId,chongzhi);
		
		return "chongzhiOK";
	}
	/**
	 * 充值历史记录查询
	 * @return
	 */
	public String appchognzhiHistory(){
		this.pageSize = 15;
		this.setUrl("carAllowAction!appchognzhiHistory.action" );
		Object[] obj = carAllowServer.findChongzhiHistory(id,Integer.parseInt(cpage), pageSize,tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "appchognzhiHistory";
	}
	//删除充值记录
	public String deleteOneCZHistory(){
		if(carAllowServer.deleteOneCZHistory(id)){
			
			return "deleteOneCZHistoryok";
		}
		return ERROR;
	}
	//补贴预申请
	public String preshenling(){
		carAllowSum=carAllowServer.getOneCarAllow(id);		
		return "butieSehnling";
	}
	//补贴申领申请
	public String butieApp(){
		carAllowServer.savenutieApp(id,caiwuRecharge);
		return "butieSehnlingOK";
	}
	
	//定时执行补贴更新操作，每月1日自动更新
	public String updateButie(){
		carAllowServer.updateSum();
		return "error";
	}
	public CarAllowServer getCarAllowServer() {
		return carAllowServer;
	}
	public void setCarAllowServer(CarAllowServer carAllowServer) {
		this.carAllowServer = carAllowServer;
	}
	public CarAllowSumApp getCarAllowSum() {
		return carAllowSum;
	}
	public void setCarAllowSum(CarAllowSumApp carAllowSum) {
		this.carAllowSum = carAllowSum;
	}
	public CarAllowOneApp getCarAllowOne() {
		return carAllowOne;
	}
	public void setCarAllowOne(CarAllowOneApp carAllowOne) {
		this.carAllowOne = carAllowOne;
	}
	public CaiwuRecharge getCaiwuRecharge() {
		return caiwuRecharge;
	}
	public void setCaiwuRecharge(CaiwuRecharge caiwuRecharge) {
		this.caiwuRecharge = caiwuRecharge;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<CarAllowSumApp> getListSum() {
		return listSum;
	}
	public void setListSum(List<CarAllowSumApp> listSum) {
		this.listSum = listSum;
	}
	public List<CarAllowOneApp> getListOne() {
		return listOne;
	}
	public void setListOne(List<CarAllowOneApp> listOne) {
		this.listOne = listOne;
	}
	public List<CaiwuRecharge> getListRechage() {
		return listRechage;
	}
	public void setListRechage(List<CaiwuRecharge> listRechage) {
		this.listRechage = listRechage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Float[] getChongzhi() {
		return chongzhi;
	}
	public void setChongzhi(Float[] chongzhi) {
		this.chongzhi = chongzhi;
	}
	public Integer[] getListId() {
		return listId;
	}
	public void setListId(Integer[] listId) {
		this.listId = listId;
	}
	public DimissionLogServer getDimissionLogServer() {
		return dimissionLogServer;
	}
	public void setDimissionLogServer(DimissionLogServer dimissionLogServer) {
		this.dimissionLogServer = dimissionLogServer;
	}
	public Credentials getCredentials() {
		return credentials;
	}
	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
}
