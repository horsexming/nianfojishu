package com.task.action.iao;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.iao.InformServer;
import com.task.entity.Users;
import com.task.entity.iao.LeaveInform;
import com.task.util.Util;

public class InformAction extends ActionSupport {
	private LeaveInform leaveInform;
	private InformServer informServer;
	private Users users;
	private UserServer userServer;
	private String show;//页面控制参数
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String errorMessage;
	private String pageStatus;
	private int keshu;

	private List list;
	private int shu;
	private String atime;
	private String btime;
	
	/**
	private String starttime;//入职日期   							起薪日期
	private String fintime;	//离职日期							是否补差
	private String endtime;//工资结算截止日期					养老保险
	private int tiantime;//最后工作月份计薪天数
	private int month;//最后工作月份
	private Float postsalary;//最后工作月份应发岗位工资            		岗位工资
	private Float secrecysalary;//最后工作月份应发保密津贴     		保密津贴
	private Float performancesalary;//最后工作月份绩效工资基数	技能工资
	private Float  achievement ;//最后工作月份绩效工资			绩效考核工资
	private Float offset ;//离职补偿								补贴
	private String performance;//最后工作月份绩效考核得分			医疗保险
	private Float heji; //合计应发								特殊补贴
	private Float shebao; //社保统筹扣款							社保基数
	private Float yiliao;//医疗保险								住房费
	private Float shiye;//失业保险	
	private Float gongji; //公积金								公积金基数
	private Float shiji;//实际支付（实发）
	private String shuoming;//说明
	*/
	//删除
	public String deleteleaveInform(){
		leaveInform = informServer.findById(leaveInform.getId());
		informServer.delete(leaveInform);
		String a = leaveInform.getFuck1();
		if(a.equals("离场通知"))
			return "li";
		if(a.equals("入职通知"))
			return "ru";
		if(a.equals("调职通知"))
			return "ti";
		if(a.equals("财务离职通知"))
			return "cli";
		if(a.equals("财务入职通知"))
			return "cru";
		if(a.equals("财务个人工资调整通知"))
			return "cgrlm";
		if(a.equals("财务内退通知"))
			return "cnt";
		if(a.equals("财务特殊通知"))
			return "cwts";
		if(a.equals("行政通知"))
			return "xztz";		
		return "fuck";
	}
	//添加离职通知
	public String start() {
		String code = leaveInform.getCode();
		LeaveInform a = informServer.findByCode(code,"离场通知");
		if (a == null) {
			users = userServer.findUserByCode(code);
			String b = users.getOnWork();
			if(b.equals("在职")){
				show ="此人未申请离职";
				return "iaoAddCInform";
			}else{
				leaveInform.setDept(users.getDept());
				leaveInform.setUsername(users.getName());
				leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
				leaveInform.setFuck1("离场通知");
				informServer.save(leaveInform);
				return "print";	
			}
		} else {
			show ="此人已经离职";
			return "iaoAddCInform";
		}
	}
	//离职列表
	public String findli() {
		show = "离场通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize, "离场通知");
		this.setUrl("InformAction!findli.action");
		int count = informServer.getcount("离场通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("离场通知");
		return "findli";
	}
	//入职列表
	public String findru() {
		show = "入职通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize, "入职通知");
		this.setUrl("InformAction!findru.action");
		int count = informServer.getcount("入职通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("入职通知");
		return "findli";
	}
	//调职列表
	public String findti() {
		show = "调职通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"调职通知");
		this.setUrl("InformAction!findti.action");
		int count = informServer.getcount("调职通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("调职通知");
		return "findli";
	}
	//财务离职通知列表
	public String findcli() {
		show = "财务离职通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"财务离职通知");
		this.setUrl("InformAction!findli.action");
		int count = informServer.getcount("财务离职通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("财务离职通知");
		return "findli";
	}
	//财务入职通知列表
	public String findcru() {
		show = "财务入职通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"财务入职通知");
		this.setUrl("InformAction!findru.action");
		int count = informServer.getcount("财务入职通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("财务入职通知");
		return "findli";
	}
	//财务个人工资调整列表
	public String findcgrlm(){
		show = "财务个人工资调整通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"财务个人工资调整通知");
		this.setUrl("InformAction!findcgrlm.action");
		int count = informServer.getcount("财务个人工资调整通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("财务个人工资调整通知");
		return "findli";
	}
	//财务内退通知列表
	public String findcnt() {
		show = "财务内退通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"财务内退通知");
		this.setUrl("InformAction!findcnt.action");
		int count = informServer.getcount("财务内退通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("财务内退通知");
		return "findli";
	}
	//财务特殊通知列表
	public String findcwts() {
		show = "财务特殊通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"财务特殊通知");
		this.setUrl("InformAction!findcwts.action");
		int count = informServer.getcount("财务特殊通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("财务特殊通知");
		return "findli";
	}
	//行政通知列表
	public String findxztz() {
		show = "行政通知";
		list = informServer.getAllList(Integer.parseInt(cpage), pageSize,"行政通知");
		this.setUrl("InformAction!findxztz.action");
		int count = informServer.getcount("行政通知");
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = informServer.getshu("行政通知");
		return "findli";
	}
	// 离场通知
//	public String leave() {
//		leaveInform.setFuck1("离场通知");
//		users = userServer.findUserByCode(leaveInform.getCode());
//		informServer.save(leaveInform);
//		return "print";	
//	}
	//立场通知打印
	public String leaveprint(){
		leaveInform = informServer.findById(leaveInform.getId());
		return "print";
	}
	// 入（调）职启动
	public String addStart() {
		return "iaoAddCInform";
	}

	// 添加调职通知
	public String addCome() {
		leaveInform.setFuck1("调职通知");
		users = userServer.findUserByCode(leaveInform.getCode());
		leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
		boolean a =informServer.save(leaveInform);
		if(a){
			return "iaoComeInform";
		}else{
			show ="系统出错了";
			return "iaoAddCInform";
		}
	}
	//添加(新进)入职通知
	public String addCame() {
		leaveInform.setFuck1("入职通知");
		leaveInform.setReason("新进公司");
		users = userServer.findUserByCode(leaveInform.getCode());
		leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
		boolean a =informServer.save(leaveInform);
		if(a){
			return "iaoComeInform";
			
		}else{
			show ="此人已经(新)入职";
			return "iaoAddCInform";
		}
	}
	//入职单个查询
	public String findCame(){
		leaveInform = informServer.findById(leaveInform.getId());
		return "iaoComeInform";
	}
	//添加(实习)入职通知
	public String addCame1() {
		LeaveInform b = informServer.findByCode(leaveInform.getCode(), "入职通知");
	    if(b==null){
	    	leaveInform.setFuck1("入职通知");
	    	leaveInform.setReason("实习生");
	    	users = userServer.findUserByCode(leaveInform.getCode());
	    	leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
	    	informServer.save(leaveInform);
	    	return "iaoComeInform";
	    	
		}else{
			if(b.getReason().equals("新进公司")){
				show ="此人已经(新)入职";
				return "iaoAddCInform";
			}else{
				show ="此人已经(实习)入职";
				return "iaoAddCInform";
			}
		}
		
	}
	//财务添加通知
	public String addM(){
		return "caiwuAdd";
	}
	//财务通知 添加离职工资通知
	public String Cstart(){
		LeaveInform a = informServer.findByCode(leaveInform.getCode(),"离场通知");
		LeaveInform b = informServer.findByCode(leaveInform.getCode(),"财务离职通知");
		if(a != null){
			if(b !=null){
				show ="此人离职已被通知";
				return "caiwuAdd";
			}else{
				users = userServer.findUserByCode(leaveInform.getCode());
				leaveInform = a;
				return "caiwulizhiadd";
			}
		}else{
			show ="此人离职未被人事添加";
			return "caiwuAdd";
		}
	}
	//财务通知 添加离职工资通知(存储 打印)
	public String caiwulizhiPrint(){
		users = userServer.findUserByCode(leaveInform.getCode());
		LeaveInform c = informServer.findByCode(leaveInform.getCode(),"财务离职通知");
		if(c==null){
			leaveInform.setFuck1("财务离职通知");
			//计算总数
			Float a = leaveInform.getPostsalary()+leaveInform.getSecrecysalary()
					+leaveInform.getAchievement()+leaveInform.getOffset();
		
			Float b = leaveInform.getShebao()+leaveInform.getYiliao()+
			leaveInform.getShiye()+leaveInform.getGongji();
		
			leaveInform.setHeji(a);
			leaveInform.setShiji(a-b);
			
			informServer.save(leaveInform);
		}else{
			leaveInform=c;
		}
		return "caiwulizhiprint";
	}
	
	//财务入职工资结算添加
	public String caiwuruzhiAdd(){
		LeaveInform a = informServer.findByCode(leaveInform.getCode(),"财务入职通知");
		if(a == null){
			leaveInform.setFuck1("财务入职通知");
			users = userServer.findUserByCode(leaveInform.getCode());
			return "caiwuruzhi";
		}else{
			show ="此人入职已被添加";
			return "caiwuAdd";
		}
	}
	//财务入职工资结算添加(存储 打印)
	public String caiwuruzhiPrint(){
		users = userServer.findUserByCode(leaveInform.getCode());
		LeaveInform a = informServer.findByCode(leaveInform.getCode(),"财务入职通知");
		if(a==null){
			leaveInform.setReason(users.getCardId());
			leaveInform.setFuck1("财务入职通知");
			leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
			informServer.save(leaveInform);
		}else{
			leaveInform = a;
		}
		return "caiwuruzhiprint";
	}
	//财务内退工资结算添加
	public String caiwuneituiAdd(){
		LeaveInform a = informServer.findByCode(leaveInform.getCode(),"财务内退通知");
		if(a == null){
			leaveInform.setFuck1("财务内退通知");
			users = userServer.findUserByCode(leaveInform.getCode());
			return "caiwuruzhi";
		}else{
			show ="此人内退已被添加";
			return "caiwuAdd";
		}
	}
	//财务入职工资结算添加(存储 打印)
	public String caiwuneituiPrint(){
		users = userServer.findUserByCode(leaveInform.getCode());
		LeaveInform a = informServer.findByCode(leaveInform.getCode(),"财务内退通知");
		if(a==null){
			leaveInform.setReason(users.getCardId());
			leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
			leaveInform.setFuck1("财务内退通知");
			informServer.save(leaveInform);
		}else{
			leaveInform = a;
		}
		return "caiwuruzhiprint";
	}
	//财务个人工资调整
	public String caiwugerenAdd(){
		leaveInform.setFuck1("财务个人工资调整通知");
		users = userServer.findUserByCode(leaveInform.getCode());
		return "caiwuruzhi";
	}
	//财务个人工资调整(存储)
	public String caiwugerenPrint(){
		users = userServer.findUserByCode(leaveInform.getCode());
		leaveInform.setReason(users.getCardId());
		leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
		leaveInform.setFuck1("财务个人工资调整通知");
		informServer.save(leaveInform);
		return "caiwuruzhiprint";
	}
	//财务个人工资调整(打印)
	public String caiwugerenPrint1(){
		leaveInform = informServer.findById(leaveInform.getId(),"财务个人工资调整通知");
		return "caiwuruzhiprint";
	}
	//进入财务特殊通知添加
	public String comeCaiwutstzAdd(){
		return "caiwutstzAdd";
	}
	//财务特殊存储
	public String caiwutstz(){
		leaveInform.setCode(leaveInform.getFuck2());
		leaveInform.setReason(leaveInform.getFuck3());
		leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
		leaveInform.setFuck1("财务特殊通知");
		informServer.save(leaveInform);
		return "caiwutstzprint";
	}
	
	//特殊通知打印
	public String caiwutstzprint(){
		leaveInform = informServer.findById(leaveInform.getId(),"财务特殊通知");
		return "caiwutstzprint";
	}
	//行政通知添加
	public String xingzhengtzAdd(){
		return "xingzhengtz";
	}
	//行政通知存储
	public String xingzhengtz(){
		leaveInform.setTime(Util.getDateTime("yyyyMMddHHmmss"));
		leaveInform.setFuck1("行政通知");
		informServer.save(leaveInform);
		return "xingzhengtzprint";
	}
	//行政通知打印
	public String xingzhengtzprint(){
		leaveInform = informServer.findById(leaveInform.getId(),"行政通知");
		return "xingzhengtzprint";
	}
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public InformServer getInformServer() {
		return informServer;
	}

	public void setInformServer(InformServer informServer) {
		this.informServer = informServer;
	}

	public LeaveInform getLeaveInform() {
		return leaveInform;
	}

	public void setLeaveInform(LeaveInform leaveInform) {
		this.leaveInform = leaveInform;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public int getKeshu() {
		return keshu;
	}

	public void setKeshu(int keshu) {
		this.keshu = keshu;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getShu() {
		return shu;
	}

	public void setShu(int shu) {
		this.shu = shu;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public String getBtime() {
		return btime;
	}

	public void setBtime(String btime) {
		this.btime = btime;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}

}
