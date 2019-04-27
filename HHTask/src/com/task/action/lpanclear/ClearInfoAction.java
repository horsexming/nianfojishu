/**
 * 
 */
package com.task.action.lpanclear;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.task.Server.lpanclear.ClearInfoServer;
import com.task.entity.Users;
import com.task.entity.lpanclear.Cleanrecord;
import com.task.entity.lpanclear.ClearInfo;
import com.task.entity.lpanclear.ClearPhone;
import com.task.entity.lpanclear.Employee;
import com.task.entity.lpanclear.HomeTitle;
import com.task.entity.lpanclear.LpanUtils;
import com.task.entity.lpanclear.Score;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

/**
 * 清洁信息Action层
 */
public class ClearInfoAction {

	private String errorMessage; // 错误消息
	private String successMessage;
	private LpanUtils lpanUtils = new LpanUtils();// 个人时间工具类
	private String temp_str; // 当前时间
	private String[] dayDate; // 一周的日期(周一至周日)
	// ====================对人员进行操作的属性======================
	private String employeeName; // 条件查询
	private List<Employee> employeeList; // 人员信息集合
	private Map<Integer, Object> employeeMap; // 人员信息Map集合
	private ClearInfoServer clearInfoServer; // server层对象
	private Integer countEmp; // 人员记录总数
	private Employee employee; // 人员类对象
	private int pageIndex; // 分页当前页所在下标
	private Integer pageSize = 4; // 分页当前页显示记录行
	private Integer pageNo = 1; // 第几页
	private String cpage = "1";

	private Integer lastPage; // 最后一页
	private String total;
	private String url;
	// ====================对清洁表进行操作的属性===============================
	private ClearInfo clearInfo; // 清洁表信息对象
	private String clearDate; // 清洁当天时间
	private Integer employeeId; // 人员ID
	private List<ClearInfo> clearInfoList; // 清洁员工集合(查当天清洁人员)
	private Map<String, List<ClearInfo>> mapWeek;// homePage界面显示周期集合
	private Integer dateTemp; // 显示周期的初始值
	private Integer dateTemp1; // 界面进行上下周期按钮事件后传递的值
	private String sameDate; // 从ajax传递回来的选定日期
	// ====================对房间标题表操作的属性===============================
	private HomeTitle homeTitle; // 房间标题表对象
	private String titleName; // 标题名称
	private List<HomeTitle> homeTitleList; // 房间标题表集合对象
	private String randomNum; // 返回的随机数
	private Integer titleId; //

	// ====================对领取提交评分表操作的属性============================
	private Cleanrecord cleanrecord; // 领取提交评分记录表对象
	private Cleanrecord cleanrecord3; //
	private Cleanrecord cleanrecord2; //
	private String theDay;            //记录所属日期(即记录生成日期)
	private String operation = "操作"; // 由方法判断后赋值并传递到界面
	private String remenber; // 由前台返回的领取人提交人评分人
	//====================评分表操作属性================================
	private Score score; // 评分表对象
	private Integer fraction; // 评分分数
	private List<Score> scoreAllList;
	private Integer days;    
	private String scoreDate;         //评分所属日期
	private String pingfents;         //评分提示


	//====================from提交=====================================
	private ClearPhone clearPhone;
	private List<ClearPhone> clearPhoneList;
	
	// 上传文件对象
	private File picture;
	//上传文件名
	private String pictureFileName;
	// 上传文件类型
	private String pictureType;
	


	/**
	 * 查询所有已在职员工信息Map
	 */
	@SuppressWarnings("unchecked")
	public String selectOnTheJob() {
		if (employee != null) {
			ActionContext.getContext().getSession().put("employeefy", employee);
		} else {
			// 根据条件分页
			employee = (Employee) ActionContext.getContext().getSession().get(
					"employeefy");
		}
		if (pageNo == null && pageSize == null) {
			pageNo = 1;
			pageSize = 4;
		}
		// 注意一下Object[] object
		Map<Integer, Object> map = clearInfoServer.selectOnTheJob(employee,
				Integer.parseInt(cpage), pageSize);
		employeeList = (List<Employee>) map.get(1);
		if (employeeList != null && employeeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ClearInfoAction_selectOnTheJob.action");
			successMessage = null;
		} else {
			errorMessage = "没有找到符合  的内容";
		}

		mapWeek = new LinkedHashMap<String, List<ClearInfo>>();
		mapWeek.clear();
		System.out.println("调用前的初始值dateTemp1:" + dateTemp1);
		Calendar cal = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		temp_str = lpanUtils.getNowDate();
		Date date = null;
		try {
			date = sdf1.parse(temp_str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		System.out.println("输出dateTemp"+dateTemp);
		if (dateTemp == null&&dateTemp1==null) {
			dateTemp = 0;
			dateTemp1 =0;
		}else if(dateTemp1!=null&&dateTemp1<0&&dateTemp !=null){
			//dateTemp = dateTemp+dateTemp1;
		}else if(dateTemp1!=null&&dateTemp1>0&&dateTemp !=null){
			//dateTemp = dateTemp+dateTemp1;
		}
		//System.out.println("输出第二个dateTemp"+dateTemp);
		System.out.println("输出dateTemp1"+dateTemp1);
		dayDate = new String[7];		
		
		if (dateTemp==0||dateTemp1==1){			
			Date dBefore2 = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(lpanUtils.getWeek(dateTemp)); // 把当前时间赋给日历
			//calendar.setTime(lpanUtils.getNowWeekBegin());   // 把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_WEEK, 0);
			for (int i = 0; i <= 6; i++) { // 从周日之后开始循环七次,直接获取当周的一周时间日期
				calendar.add(Calendar.DAY_OF_WEEK, +1); // 设置为下一天
				dBefore2 = calendar.getTime(); // 得到下一天的时间
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
				String defaultStartDate = sdf2.format(dBefore2); // 格式化下一天		
				
                System.out.println(defaultStartDate);        
                
				dayDate[i] = defaultStartDate.toString();
				clearInfoList = clearInfoServer.selectClearInfos(dayDate[i]);
				mapWeek.put(dayDate[i], clearInfoList);				
			}
		} else if (dateTemp1==-1){
			cal.set(Calendar.DAY_OF_WEEK, dateTemp);
			Date dBefore2 = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(lpanUtils.getWeek(dateTemp)); // 把当前时间赋给日历
			for (int i = 0; i <= 6; i++) { // 从周日之后开始循环七次,直接获取当周的一周时间日期
				calendar.add(Calendar.DAY_OF_WEEK, +1); // 设置为下一天
				dBefore2 = calendar.getTime(); // 得到下一天的时间
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
				String defaultStartDate = sdf2.format(dBefore2); // 格式化下一天
				dayDate[i] = defaultStartDate.toString();
				clearInfoList = clearInfoServer.selectClearInfos(dayDate[i]);
				mapWeek.put(dayDate[i], clearInfoList);
			}
		}
		return "homePage";
	}

	//
	// /**
	// * 登陆用户并返回用户是否存在
	// */
	// public String loginEmployee(){
	// if(employee !=null){
	// if(clearInfoServer.loginEmployee(employee)){
	// return "true";
	// }else{
	// errorMessage ="用户不存在";
	// }
	// }
	// return "error";
	// }

	/**
	 * 存储ajax返回的sameDate值
	 */
	public void preservation() {
		System.out.println(sameDate);
		if (sameDate != null) {
			ActionContext.getContext().getSession().put("sameDateSession",sameDate);
			String sameDate1 = (String) ActionContext.getContext().getSession().get("sameDateSession");
			System.out.println("输出ajax返回的sameDate" + sameDate1);
		}
	}

	/**
	 * 进行插入清洁员工判断,如果未添加当天清洁就插入当天某清洁员工
	 */
	public String insertEmpClear() {
		dateTemp = (Integer) ActionContext.getContext().getSession().get("dateTempSession");
		String clearDate1 = (String) ActionContext.getContext().getSession().get("sameDateSession");
		clearInfo.setClearDate(clearDate1);
		if (clearInfoServer.selectJudge(clearInfo)) {
			if (clearInfoServer.insertEmpClear(clearInfo)){
				System.out.println("输出dateTemp"+dateTemp);
				MKUtil.writeJSON(1);
			} else {
				errorMessage = "插入清洁员工失败!";
			}
		} else {
			errorMessage = "人员已存在!";
		}
		return "error";
	}

	/**
	 * 取消当天某清洁员工
	 */
	public String quXiaoEmpClear() {
		dateTemp = (Integer) ActionContext.getContext().getSession().get("dateTempSession");
		//clearInfo = new ClearInfo();
		//clearInfo.setEmployee(employee);
		if (clearInfoServer.deleteEmpClear(clearInfo)){
			MKUtil.writeJSON(dateTemp);
		} else {
			errorMessage = "取消失败!!!";
		}
		return "error";
	}

	/**
	 * 查询当天清洁员工
	 */
	public String selectClearInfos() {
		if (clearDate != null) {
			clearInfoList = clearInfoServer.selectClearInfos(clearDate);
			return "homePage";
		} else {
			errorMessage = "当天时间无!!!";
		}
		return "error";
	}

	/**
	 * 查询循环内七天的值,根据当周日期查询并显示,条件未当周开始和当周结束
	 */
	public String selectClearWeeks(String clearDateks, String clearDatejs) {

		return null;
	}

	/**
	 * 查询标题方法并显示全部二维码
	 */
	public String homeTitle() {
		homeTitleList = clearInfoServer.selectHomeTitles();
		return "QRcode";
	}

	/**
	 * ajax返回读取homeTitle数据的方法
	 */
	public void ajaxtitleName(){
		homeTitleList = clearInfoServer.selectHomeTitles();
		MKUtil.writeJSON(homeTitleList);
	}

	/**
	 * ajax返回随机数randomNum并修改数据库中每个对象的randomNum值的方法
	 */
	public void ajaxRandomNum(){
		clearInfoServer.updateRandomNum(homeTitle);
	}
    

	/**
	 * 扫码登陆后判断用户所能做执行
	 * @return
	 */
	public String userOperation(){      
       		
		operation = clearInfoServer.userOperation();
		if(operation.equals("系统评分")){
			boolean xtpfjg = clearInfoServer.insCleanAndScore();
			System.out.println("系统领取和评分返回结果xtpfjg"+xtpfjg);
			operation = clearInfoServer.userOperation();
		}else if(operation.equals("系统提交")){
			operation = clearInfoServer.userOperation();
		}
		
		//附带显示一个当天评分(评的是昨天的值日)
		if(scoreDate ==null||scoreDate.equals("")){
			scoreAllList = clearInfoServer.selectDateSanRecord();
		}else{
			scoreAllList = clearInfoServer.selectDateDayRecord(scoreDate);
		}
		if(theDay ==null||theDay.equals("")){
			cleanrecord = clearInfoServer.selectLastOne();
		}else{
			cleanrecord = clearInfoServer.selectRecordTheDay(theDay);
		}
		
		return "login_xuanzhe";
	}


	/**
	 * 跳转到评分界面中
	 */
	public String pingfen(){
		temp_str = lpanUtils.getNowDate();
		cleanrecord2 = clearInfoServer.selectRecord1();
		homeTitleList = clearInfoServer.selectHomeTitles();
		pingfents ="空";
		return "score";
	}

	/**
	 * 进行领取操作的方法
	 */
	public String receiveOperation() {	
		boolean cleanbool = clearInfoServer.insertReceive();
		System.out.println(cleanbool);
		if (cleanbool) {
			operation = "操作";
			MKUtil.writeJSON(1);
		}		
		return null;
	}

	/**
	 * 进行提交操作的方法
	 */
	public String submitterOperation() {
		boolean upSubcount = clearInfoServer.updateSubmission();
		if (upSubcount) {
			operation = "操作";
			MKUtil.writeJSON(1);
		}
		return "login_xuanzhe";
	}

	/**
	 * 进行评分操作的方法
	 */
	public String evaluatorOperation(){
		Users u = Util.getLoginUser();
		
	    //首先获取最后一条记录的theDay
		Cleanrecord  cleanrecordzh =  clearInfoServer.selectLastOne();	
		String temp_str = lpanUtils.getNowDate();
		clearInfoList = clearInfoServer.selectClearInfos1();
		if(temp_str.equals(cleanrecordzh.getTheDay())){
			temp_str = lpanUtils.getNowDate();
			cleanrecord2 = clearInfoServer.selectRecord1();
			homeTitleList = clearInfoServer.selectHomeTitles();
			pingfents = "无权限";		
		}else{
			boolean sfsyzrry = clearInfoServer.judgeClearInfo(u);
			if(sfsyzrry){
				Integer count = -1;				
				cleanrecord = new Cleanrecord();			
				cleanrecord.setFraction(fraction);	   	    
				boolean countclean = clearInfoServer.updateEva(cleanrecord);			
				System.out.println("输出评分和完成记录detrypf" + countclean);			
				score = new Score();
				score.setTitleName(titleName);
				score.setFraction(fraction);
				System.out.println("输出一下titleName"+titleName);
				boolean countScore = clearInfoServer.insertScore(score);
				if(countScore){
					count =1;
				}
				System.out.println("输出对人员评分的记录countScore" + countScore);
							
				temp_str = lpanUtils.getNowDate();
				cleanrecord2 = clearInfoServer.selectRecord1();
				homeTitleList = clearInfoServer.selectHomeTitles();		
				//此处进行上传图片的保存操作
				if(picture !=null){
				    String realFileName =Util.getDateTime("yyyyMMddHHmmss");
				    String realFilePath = "/upload/file/lpanclear";
				    String basePath=ServletActionContext.getServletContext().getRealPath(realFilePath);	
					File file = new File(basePath); 
				    if(!file.exists()){ 
						file.mkdir();   
					}	    
					Upload upload=new Upload();
					realFileName=upload.UploadFile(picture, pictureFileName, null,realFilePath, null);
					String file2 = new File(file,pictureFileName).toString();
					System.out.println("文件名："+pictureFileName);
					System.out.println("file2:"+file2);
					boolean cphone = clearInfoServer.insertPhrecord(realFileName,pictureFileName);
					System.out.println(cphone);
				}			    		
				if(countclean&&count==1){
					pingfents = "评分成功";			
				}else if(countclean==false&&count==1){
					pingfents = "评分成功";
				}else if(countclean==false&&count==-1){
					pingfents = "已评分";
				}
			}else{
				temp_str = lpanUtils.getNowDate();
				cleanrecord2 = clearInfoServer.selectRecord1();
				homeTitleList = clearInfoServer.selectHomeTitles();
				pingfents = "无权限";
			}		
		}								                 
		return "score";
	}
	
    /**
     * 评分分数柱状统计图表方法
     */
	public void ajaxchart(){	
		scoreAllList = clearInfoServer.selectAllScore(days);
		employeeList = clearInfoServer.selectEmp();
		int length = scoreAllList.size();
		dayDate = new String[length];
		class Chart{
			private String label;
			private Integer y;
			public Chart(String label, Integer y) {
				super();
				this.label = label;
				this.y = y;
			}
			public Chart(){
				super();
			}
			public String getLabel(){
				return label;
			}
			public void setLabel(String label){
				this.label = label;
			}
			public Integer getY(){
				return y;
			}
			public void setY(Integer y){
				this.y = y;
			}			
		}
		List<Chart>  list = new ArrayList<Chart>();
		for (int j = 0; j < employeeList.size(); j++){
			Integer fraction = 0;
			Chart chart = new Chart();
			for (int i = 0; i <scoreAllList.size(); i++){	
				//System.out.println("输出empid"+employeeList.get(j).getId()+"输出分数中empid"+scoreAllList.get(i).getEmployee().getId());
				if(employeeList.get(j).getId().equals(scoreAllList.get(i).getEmployee().getId())){						
					fraction = fraction+scoreAllList.get(i).getFraction();					 
				}
			}
			chart = new Chart(employeeList.get(j).getEmployeeName(),fraction);
			list.add(chart);			 
		}
		MKUtil.writeJSON(list);
	}
	
	
	
//	/**
//	 * java图片上传,(主要用于拍照上传图片)
//	 */
//	public String uploadFile2() throws IOException{
//    String basePath=ServletActionContext.getServletContext().getRealPath("/upload/file/lpanclear");
//    //String filePath = basePath+File.separator+pictureFileName;
//    File file = new File(basePath); 
//    if(!file.exists()){ 
//        file.mkdir(); 
//    }
//    //将页面传过来的数据通过FileUtils 拷贝到我们刚刚定义的路径下
//    FileUtils.copyFile(picture, new File(file,pictureFileName));
//    String file2 = new File(file,pictureFileName).toString();
//    System.out.println("文件名："+pictureFileName);
//    System.out.println("file2:"+file2);
//    return "score";
//
//}
	
	
	
	
	/**
	 * 进行上传图片的查看方法
	 */
	public String selectPhrecordList(){
		
		if (clearPhone != null){
			ActionContext.getContext().getSession().put("clearPhonefy", clearPhone);
		}else{
			clearPhone = (ClearPhone) ActionContext.getContext().getSession().get("clearPhonefy");
		}
		if (pageNo == null && pageSize == null){
			pageNo = 1;
			pageSize = 4;
		}
		Map<Integer, Object> map = clearInfoServer.selectPhrecordList(clearPhone,
				Integer.parseInt(cpage), pageSize);
		clearPhoneList = (List<ClearPhone>) map.get(1);
		if (clearPhoneList != null && clearPhoneList.size() > 0){
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("ClearInfoAction_selectPhrecordList.action");
			successMessage = null;
		} else {
			errorMessage = "没有找到符合  的内容";
		}
		//获取当前上传图片所属日期的值日人员
		clearInfoList = clearInfoServer.selectClearInfos(clearDate);
		
		return "clear_picture";
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

	public LpanUtils getLpanUtils() {
		return lpanUtils;
	}

	public void setLpanUtils(LpanUtils lpanUtils) {
		this.lpanUtils = lpanUtils;
	}

	public String getTemp_str() {
		return temp_str;
	}

	public void setTemp_str(String tempStr) {
		temp_str = tempStr;
	}

	public String[] getDayDate() {
		return dayDate;
	}

	public void setDayDate(String[] dayDate) {
		this.dayDate = dayDate;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	// ====================对人员进行操作的属性getset方法======================
	// =======================================================================
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

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Map<Integer, Object> getEmployeeMap() {
		return employeeMap;
	}

	public void setEmployeeMap(Map<Integer, Object> employeeMap) {
		this.employeeMap = employeeMap;
	}

	public ClearInfoServer getClearInfoServer() {
		return clearInfoServer;
	}

	public void setClearInfoServer(ClearInfoServer clearInfoServer) {
		this.clearInfoServer = clearInfoServer;
	}

	public Integer getCountEmp() {
		return countEmp;
	}

	public void setCountEmp(Integer countEmp) {
		this.countEmp = countEmp;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// ============================================================================
	// ============================================================================

	// ====================对清洁表进行操作的属性===================================
	// ============================================================================
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ClearInfo getClearInfo() {
		return clearInfo;
	}

	public void setClearInfo(ClearInfo clearInfo) {
		this.clearInfo = clearInfo;
	}

	public String getClearDate() {
		return clearDate;
	}

	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public List<ClearInfo> getClearInfoList() {
		return clearInfoList;
	}

	public void setClearInfoList(List<ClearInfo> clearInfoList) {
		this.clearInfoList = clearInfoList;
	}

	public Map<String, List<ClearInfo>> getMapWeek() {
		return mapWeek;
	}

	public void setMapWeek(Map<String, List<ClearInfo>> mapWeek) {
		this.mapWeek = mapWeek;
	}

	public Integer getDateTemp() {
		return dateTemp;
	}

	public void setDateTemp(Integer dateTemp) {
		this.dateTemp = dateTemp;
	}

	public Integer getDateTemp1() {
		return dateTemp1;
	}

	public void setDateTemp1(Integer dateTemp1) {
		this.dateTemp1 = dateTemp1;
	}

	public String getSameDate() {
		return sameDate;
	}

	public void setSameDate(String sameDate) {
		this.sameDate = sameDate;
	}

	// ============================================================================
	// ============================================================================

	// ====================对房间标题表操作的属性===================================
	// ============================================================================
	public HomeTitle getHomeTitle() {
		return homeTitle;
	}

	public void setHomeTitle(HomeTitle homeTitle) {
		this.homeTitle = homeTitle;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	public List<HomeTitle> getHomeTitleList() {
		return homeTitleList;
	}

	public void setHomeTitleList(List<HomeTitle> homeTitleList) {
		this.homeTitleList = homeTitleList;
	}

	// ============================================================================
	// ============================================================================

	// ====================对领取提交评分表操作的属性===============================
	// ============================================================================
	public Cleanrecord getCleanrecord() {
		return cleanrecord;
	}

	public void setCleanrecord(Cleanrecord cleanrecord) {
		this.cleanrecord = cleanrecord;
	}

	public Cleanrecord getCleanrecord3() {
		return cleanrecord3;
	}

	public Cleanrecord getCleanrecord2() {
		return cleanrecord2;
	}

	public void setCleanrecord2(Cleanrecord cleanrecord2) {
		this.cleanrecord2 = cleanrecord2;
	}

	public void setCleanrecord3(Cleanrecord cleanrecord3) {
		this.cleanrecord3 = cleanrecord3;
	}
	public String getTheDay() {
		return theDay;
	}
	public void setTheDay(String theDay) {
		this.theDay = theDay;
	}
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getRemenber() {
		return remenber;
	}

	public void setRemenber(String remenber) {
		this.remenber = remenber;
	}

	// ============================================================================
	// ============================================================================

	// ========================================================================
	// ====================对评分表操作的属性===============================
	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public Integer getFraction() {
		return fraction;
	}
	public String getScoreDate() {
		return scoreDate;
	}
	public void setScoreDate(String scoreDate) {
		this.scoreDate = scoreDate;
	}
	public void setFraction(Integer fraction) {
		this.fraction = fraction;
	}
	public List<Score> getScoreAllList() {
		return scoreAllList;
	}
	public void setScoreAllList(List<Score> scoreAllList) {
		this.scoreAllList = scoreAllList;
	}
	
    public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getPingfents() {
		return pingfents;
	}
	public void setPingfents(String pingfents) {
		this.pingfents = pingfents;
	}
	//==========================================================================
	//============from提交保存路径表属性======================================
	public ClearPhone getClearPhone() {
		return clearPhone;
	}
	public void setClearPhone(ClearPhone clearPhone) {
		this.clearPhone = clearPhone;
	}
	public List<ClearPhone> getClearPhoneList() {
		return clearPhoneList;
	}
	public void setClearPhoneList(List<ClearPhone> clearPhoneList) {
		this.clearPhoneList = clearPhoneList;
	}
	
	public File getPicture() {
		return picture;
	}
	public void setPicture(File picture) {
		this.picture = picture;
	}
	public String getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String getPictureType() {
		return pictureType;
	}
	public void setPictureType(String pictureType) {
		this.pictureType = pictureType;
	}
}