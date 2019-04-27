/**
 * 
 */
package com.task.ServerImpl.lpanclear;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.task.Dao.TotalDao;
import com.task.Server.lpanclear.ClearInfoServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Users;
import com.task.entity.lpanclear.Cleanrecord;
import com.task.entity.lpanclear.ClearInfo;
import com.task.entity.lpanclear.Employee;
import com.task.entity.lpanclear.HomeTitle;
import com.task.entity.lpanclear.LpanUtils;
import com.task.entity.lpanclear.ClearPhone;
import com.task.entity.lpanclear.Score;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class ClearInfoServerImpl implements ClearInfoServer {
   
	private TotalDao totalDao;
	
	//一下为Employee实体类接口=================================================================================
	//=========================================================================================================
	/**
	 * 查询所有已在职员工信息Map
	 */
	public Map<Integer,Object> selectOnTheJob(Employee employee,Integer pageNo,Integer pageSize){
					
		if(employee ==null){
			employee= new Employee();
		}
		String hql = null;
		hql = totalDao.criteriaQueries(employee, null);
		hql+= " order by id asc";
		List<Employee> employeeList = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new LinkedHashMap<Integer,Object>(); 
		map.put(1, employeeList);
		map.put(2, count);				
		
	    return map;
	}	
	
	/**
	 * 查询所有已在职员工信息List
	 */
	public List<Employee> selectOnTheJob1(String employeeName,Integer pageNo,Integer pageSize){
		
		String hql = " from employee";
		if(employeeName !=null){
			hql+=" where employeeName=?"+employeeName;
		}
		if(pageNo ==null){
			pageNo=1;
		}
		hql+=" order by id asc limit ?"+pageNo+",?"+pageSize;
		List<Employee> employeeList = totalDao.query(hql);
		return employeeList;
	}
	
	/**
	 * 查询所有在职员工对象不分页
	 */
	public List<Employee> selectEmp(){
		List<Employee> empList = totalDao.query(" from Employee");
		return empList;
	}
	/**
	 * 获取总记录数(配合分页使用)
	 */
	public int getCount(){
		int count = totalDao.getCount(" COUNT(id) from employee");
		return count;
	}	
	
	/**
	 * 登陆用户并返回用户是否存在
	 */
	public Employee loginEmployee(Employee employee){
		if(employee !=null){
			employee= (Employee) totalDao.getObjectByCondition(" from Employee where employeeName=?",employee.getEmployeeName());
		}		
		return employee;
	}
	/**
	 * 获取总的评分数,进行封装
	 */
	public List<Employee> empFractionList(){
		
		return null;
	}
	
	//==========查询users在职员工==============================================================================
	/**
	 * 根据userId查询users表在职人员，如果employee不存在那就显示在下拉框中,可选取
	 */
	public Users selectUsers(Employee employee){
		Users users1 = new Users();
		if(employee !=null){
			users1 = (Users)totalDao.getObjectByCondition("select name from Users where id",employee.getUserId());
		}
		return users1;
	}
	
	/**
	 * 根据id查询返回对象
	 */
	public Employee findId(Integer id){
		Employee employee = new Employee();
		if(id !=null){
			employee = (Employee) totalDao.getObjectByCondition(" from Employee where id=?",id);
		}	
		return employee;
	}
	
	
	//一下为ClearInfo实体类接口=================================================================================
	//=========================================================================================================			
	/**
	 * 进行插入清洁员工时判断,如果已经添加当天清洁,为了防止再次添加,需要判断数据库中是否已经存在当前员工安排在当天清洁了
	 */
	public boolean selectJudge(ClearInfo clearInfo){
		int count = -1;
		if(clearInfo !=null){
			count= totalDao.getCount(" from ClearInfo where employee.id=? and clearDate=?",clearInfo.getEmployee().getId(),clearInfo.getClearDate());
		}
		if(count==0){
			return true;
		}		
		return false;
	}
	/**
	 * 插入当天某清洁员工
	 */
	public boolean insertEmpClear(ClearInfo clearInfo){		
		if(clearInfo !=null){
			return totalDao.save(clearInfo);
		}
		return false;
	}
	/**
	 * 取消当天某清洁员工
	 */
	public boolean deleteEmpClear(ClearInfo clearInfo){
		if(clearInfo !=null){			
			ClearInfo clearInfo1 = (ClearInfo)totalDao.getObjectByCondition(" from ClearInfo where clearDate=? and employee.id=?",clearInfo.getClearDate(),clearInfo.getEmployee().getId());
			if(clearInfo1 !=null){
				clearInfo1.setEmployee(null);
				boolean b = totalDao.delete(clearInfo1);
				return b;			
			}						
		}
		return false;
	}
	/**
	 * 按日期查询清洁员工
	 */
	public List<ClearInfo> selectClearInfos(String clearDate){
		List<ClearInfo> clearList = new ArrayList<ClearInfo>();
		if(clearDate ==null){
			clearList = totalDao.query("from ClearInfo where clearDate is null");
		}else{
			clearList = totalDao.query("from ClearInfo where clearDate=?", clearDate);
		}
		return clearList;
	}
	
	/**
	 * 按当天查询清洁员工
	 */
	public List<ClearInfo> selectClearInfos1(){		
		List<ClearInfo> clearList = totalDao.query("from ClearInfo where clearDate=?",Util.getDateTime("yyyy-MM-dd"));
		return clearList;
	}
	
	/**
	 * 查询循环内七天的值,根据当周日期查询并显示,条件未当周开始和当周结束
	 */
	public List<ClearInfo> selectClearWeeks(String clearDateks,String clearDatejs){
		
		return null;
	}
	/**
	 * 判断登陆用户是否属于当天值日人员
	 */
	public boolean judgeClearInfo(Users u){
		List<ClearInfo> clearList = totalDao.query("from ClearInfo where clearDate=?",Util.getDateTime("yyyy-MM-dd"));
		for (ClearInfo clearInfo : clearList) {
			if(u.getName().equals(clearInfo.getEmployee().getEmployeeName())){
				return true;	
			}			
		}
		return false;
	}
	
	
	//一下为homeTitle接口===================================================================================
	//=====================================================================================================
	/**
	 * 查询房间标题信息
	 */
	public List<HomeTitle> selectHomeTitles(){
		List<HomeTitle> hometilteList = totalDao.query(" from HomeTitle");
		return hometilteList;
	}
	/**
	 * 根据房间标题查询其ID
	 */
	public HomeTitle selectTnameChaId(HomeTitle homeTitle){
		if(homeTitle !=null){
			homeTitle = (HomeTitle)totalDao.getObjectByCondition("from HomeTitle where titleName=?",homeTitle.getTitleName());
		}
		return homeTitle;
	}
	/**
	 * 根据房间ID查询其标题
	 */
	public HomeTitle selectTitleIdChaName(HomeTitle homeTitle){
		if(homeTitle !=null){
			homeTitle = (HomeTitle)totalDao.getObjectByCondition("from HomeTitle where id=?",homeTitle.getId());
		}
		return homeTitle;
	}
	
	/**
	 * 根据房间ID查询其对象
	 */
	public HomeTitle selectTChaName(Integer titleId){
		HomeTitle homeTitle=new HomeTitle();
		if(titleId !=null){
			homeTitle = (HomeTitle)totalDao.getObjectByCondition("from HomeTitle where id=?",titleId);
		}
		return homeTitle;
		
	}
	/**
	 * 新增房间标题信息
	 */
	public boolean insertHomeTitle(HomeTitle homeTitle){
		if(homeTitle !=null){
			return totalDao.save(homeTitle);
		}
		return false;
	}
	/** 
	 * 删除房间标题信息
	 */
	public boolean deleteHomeTitle(Integer titleId){
		if(titleId !=null){
			Integer titleId1 = (Integer)totalDao.getObjectByCondition(" from HomeTitle where id=?",titleId);
			return totalDao.delete(titleId1);
		}
		return false;
	}
	/**
	 * 修改房间标题信息(此处不确定是否通过)
	 */
	public boolean updateHomeTitle(HomeTitle homeTitle){
		if(homeTitle !=null&&homeTitle.getId() !=null&&homeTitle.getTitleName() !=null){
			HomeTitle homeTitle1 = (HomeTitle)totalDao.getObjectByCondition(" from HomeTitle where id=?",homeTitle.getId());
			if(homeTitle1 !=null){
				homeTitle1.setTitleName(homeTitle.getTitleName());
				return totalDao.update(homeTitle1);
			}	
		}
		return false;
	}
	/**
	 * 对每个标题中的随机数进行修改
	 */
	public void updateRandomNum(HomeTitle homeTitle){
		if(homeTitle !=null){
			List<HomeTitle> hometilteList = totalDao.query(" from HomeTitle");
			for (HomeTitle homeTitle2 : hometilteList) {
				homeTitle2.setRandomNum(homeTitle.getRandomNum());
				totalDao.update(homeTitle2);
			}			
		}
	}
	
	
	/**
	 * 验证标题和随机数是否和数据库一致
	 */
	public boolean selectTNameAndRandomNum(HomeTitle homeTitle){
		if(homeTitle !=null){
			Integer count = totalDao.getCount(" from HomeTitle where id=? and randomNum=?", homeTitle.getId(),homeTitle.getRandomNum());
			if(count>0){
				return true;
			}
		}
		return false;
	}
	//一下为Cleanrecord实体类接口=================================================================================
	//=========================================================================================================
	/**
	 * 未评分时==早上进行rtx提醒
	 */
	@Override
	public  void timingAMTask(){
		LpanUtils lpanUtils = new LpanUtils(); 
		String temp_str = lpanUtils.getNowDate(); 
		//按照日期查询今日的记录(判断是否有无)
		Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",temp_str);
		//根据今天记录无的情况下查看排序后第一条(即最近日期的一条记录)
		Cleanrecord cleanrecord2 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		//查询当天清洁员工
		List<ClearInfo> clearInfoList = totalDao.query("from ClearInfo where clearDate=?",temp_str);
		//usersId数组
		Integer[] userId= new Integer[clearInfoList.size()];
        if(cleanrecord1==null&&clearInfoList !=null){
        	if(cleanrecord2.getState().equals("未完成")&&!(cleanrecord2.getSubmitter().equals("未提交"))){        				
        		for (int i = 0; i < clearInfoList.size(); i++){
        			if (clearInfoList.get(i).getEmployee().getUserId() != null){
        				userId[i] =clearInfoList.get(i).getEmployee().getUserId();
        			}				
        		}
        		AlertMessagesServerImpl.addAlertMessages("值日提醒","今天有您的值日任务请进行评分,如当天休班状态,可略过此记录!!!",userId,"ClearInfoAction_userOperation.action",true);       		
        	}
        }						
	}
	
	/**
	 * 当天未领取提交时==下班前(5:00)进行rtx提醒
	 */
	@Override
	public  void timingPMTask(){
		LpanUtils lpanUtils = new LpanUtils(); 
		String temp_str = lpanUtils.getNowDate(); 
		//按照日期查询今日的记录(判断是否有无)
		Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",temp_str);
		//根据今天记录无的情况下查看排序后第一条(即最近日期的一条记录)
		Cleanrecord cleanrecord2 = new Cleanrecord();
		cleanrecord2 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		//查询当天清洁员工
		List<ClearInfo> clearInfoList = totalDao.query("from ClearInfo where clearDate=?",temp_str);
		//usersId数组
		Integer[] userId= new Integer[clearInfoList.size()];
        if(cleanrecord1==null&&clearInfoList !=null){
        	if(!(cleanrecord2.getState().equals("未完成"))){        				
        		for (int i = 0; i < clearInfoList.size(); i++){
        			if (clearInfoList.get(i).getEmployee().getUserId() != null){
        				userId[i] =clearInfoList.get(i).getEmployee().getUserId();
        			}				
        		}
        		AlertMessagesServerImpl.addAlertMessages("值日提醒",
            			"今天有您的值日任务请在临走前进行领取并打扫后再进行提交,如当天休班状态,可略过此记录!!!",userId,null,true);       		
        	}
        }			
	}
	
	
	
	
	
	
	/**
	 * 用户登陆后判断其所能执行的操作
	 */
	public String userOperation(){
		
		String operation = "操作";                //返回提示
		Users u = Util.getLoginUser();            //登陆用户
		LpanUtils lpanUtils = new LpanUtils();    //工具类
		String temp_str = lpanUtils.getNowDate(); //当天日期
		//按照当天时间查询记录的领取提取评分人和状态及全部
		Cleanrecord cleanrecord3 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",Util.getDateTime("yyyy-MM-dd"));// 获取当天生成记录,无的话判断登陆人是否属于领取有的话判断是属于提交还是评分
        //查询当天清洁员工
		List<ClearInfo> clearInfoList = totalDao.query("from ClearInfo where clearDate=?",temp_str);
	    //查询记录中的最后一条记录的全部信息
		Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		//根据最后一条记录的eva评分时间来查询有无此领取提交theDay记录
		Cleanrecord cleanrecord5 = new Cleanrecord();
	    if(cleanrecord1 !=null){
			cleanrecord5 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord1.getEvaluatorTime());

	    }
		if(cleanrecord1!=null && clearInfoList!=null && clearInfoList.size()>0){
	    if(cleanrecord3 == null&&cleanrecord5==null&&u== null&&!(cleanrecord1.getEvaluatorTime().equals(temp_str))&&!(cleanrecord1.getState().equals("未完成"))){
	    	operation = "系统评分";
	    }else if(cleanrecord3 == null &&!(cleanrecord1.getState().equals("未完成"))&&u!= null){
			for(ClearInfo clear : clearInfoList){
				if(u.getName().equals(clear.getEmployee().getEmployeeName())){
					operation = "领取";
					System.out.println(operation);
				}
			}
		}else if(cleanrecord3 !=null&&u!=null&&temp_str.equals(cleanrecord3.getTheDay())&&cleanrecord3.getSubmitter().equals("未提交")&&cleanrecord3.getEvaluator().equals("未评分")){
			for(ClearInfo clear : clearInfoList){
				if(u.getName().equals(clear.getEmployee().getEmployeeName())){
					operation = "提交";
					System.out.println(operation);
				}
			}
		}else if(!(temp_str.equals(cleanrecord1.getTheDay()))&&cleanrecord1.getEvaluatorTime().equals(temp_str)&&cleanrecord1.getSubmitter().equals("未提交")&&cleanrecord1.getState().equals("未完成")){
			String theDayTime = temp_str;
			Cleanrecord cleanrecord2 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord1.getTheDay());
			cleanrecord2.setSubmitter("系统提交");
			cleanrecord2.setSubmitterTime(Util.getDateTime("yyyy-MM-dd"));
			cleanrecord2.setEvaluator("系统评分");
			cleanrecord2.setState("系统完成");
			cleanrecord2.setFraction(0);
			cleanrecord2.setTheDay(cleanrecord1.getTheDay());
			boolean zdtjfhjg = totalDao.update(cleanrecord2);
			System.out.println("输出自动提交所返回的结果"+zdtjfhjg);				
			List<ClearInfo> clearInfoListtj = totalDao.query("from ClearInfo where clearDate=?",cleanrecord1.getTheDay());
			//获取下一天的人员
			List<ClearInfo> clearInfoListth = totalDao.query("from ClearInfo where clearDate=?",cleanrecord1.getEvaluatorTime());
			for(ClearInfo clearInfo : clearInfoListtj){
				Score score = new Score();
				score.setScoreDate(theDayTime);
				score.setEmployee(clearInfo.getEmployee());
				score.setTitleName("整体");
				score.setFraction(0);
				score.setFrequency(1);
				score.setDifference(1);
				score.setClearTheDay(cleanrecord1.getTheDay());
				boolean zjtjfhjg =  totalDao.save(score);
				System.out.println("输出自动提交后评分返回结果:"+zjtjfhjg);
			}	
			if(clearInfoListth !=null){
				for(ClearInfo clearInfo :clearInfoListth){
					if(clearInfo !=null){
						boolean scthclear = totalDao.delete(clearInfo);
						System.out.println("删除返回结果scthclear"+scthclear);
					}					
				}	
				if(clearInfoListtj !=null){
					for(ClearInfo clearInfo : clearInfoListtj){
						ClearInfo clearInfoxz = new ClearInfo();
						clearInfoxz.setClearDate(cleanrecord1.getEvaluatorTime());
						clearInfoxz.setEmployee(clearInfo.getEmployee());
						boolean xzthclear = totalDao.save(clearInfoxz);
						System.out.println("新增返回结果xzthclear"+xzthclear);
					}					
				}
			}			
			operation = "系统提交";
		}else if(cleanrecord3 !=null&&u!=null&&temp_str.equals(cleanrecord3.getTheDay())&&!(cleanrecord3.getSubmitter().equals("未提交"))&&cleanrecord3.getEvaluator().equals("未评分")){
			operation = "待评分";
			System.out.println(operation);
		}else if(u!= null&&temp_str.equals(cleanrecord1.getEvaluatorTime())&&!(temp_str.equals(cleanrecord1.getTheDay()))&& cleanrecord1.getState().equals("未完成")&&cleanrecord1.getEvaluator().equals("未评分")) {
			for(ClearInfo clear : clearInfoList){
				if(u.getName().equals(clear.getEmployee().getEmployeeName())){
					operation = "评分";
					System.out.println(operation);
				}
			}
		}else if(cleanrecord3 == null&&cleanrecord1.getState().equals("未完成")&&!(cleanrecord1.getSubmitter().equals("未提交"))&&!(cleanrecord1.getEvaluatorTime().equals(temp_str))&&!(temp_str.equals(cleanrecord1.getTheDay()))&&Util.compareTime(temp_str,"yyyy-MM-dd",cleanrecord1.getEvaluatorTime(),"yyyy-MM-dd")) {
				Cleanrecord cleanrecord4 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord1.getTheDay());
				cleanrecord4.setEvaluator("系统评分");
				cleanrecord4.setState("系统完成");
				cleanrecord4.setFraction(0);
				cleanrecord4.setTheDay(cleanrecord1.getTheDay());				
				boolean xtpffhjg =  totalDao.update(cleanrecord4);
				System.out.println("自动评分返回的结果zjpf"+xtpffhjg);             
				//下面进行给清洁人员进行加0分 并记录次数和差(0分)记录
				List<ClearInfo> clearInfoListpf = totalDao.query("from ClearInfo where clearDate=?",cleanrecord1.getTheDay());
				for(ClearInfo clearInfo : clearInfoListpf){
					Score score = new Score();
					score.setScoreDate(temp_str);
					score.setEmployee(clearInfo.getEmployee());
					score.setTitleName("整体");
					score.setFraction(0);
					score.setFrequency(1);
					score.setDifference(1);
					score.setClearTheDay(cleanrecord1.getTheDay());
					boolean zjpffhjg = totalDao.save(score);
					System.out.println("未评分并自动评分后的结果zdpfjg" + zjpffhjg);
				}
				//未给前次评分的值日人员(即当天也并未领取提交无记录的)
				List<ClearInfo> clearEvaList = totalDao.query("from ClearInfo where clearDate=?",cleanrecord1.getEvaluatorTime());
				
				//被删除替换掉的下一组值日人员
				List<ClearInfo> clearTmListth = totalDao.query("from ClearInfo where clearDate=?",temp_str);
				if(clearTmListth !=null){
					for(ClearInfo clearInfo :clearTmListth){
						if(clearInfo !=null){
							boolean scthclear = totalDao.delete(clearInfo);
							System.out.println("删除返回结果scthclear"+scthclear);
						}					
					}	
					if(clearEvaList !=null){
						for(ClearInfo clearInfo : clearEvaList){
							ClearInfo clearInfoxz = new ClearInfo();
							clearInfoxz.setClearDate(temp_str);
							clearInfoxz.setEmployee(clearInfo.getEmployee());
							boolean xzthclear = totalDao.save(clearInfoxz);
							System.out.println("新增返回结果xzthclear"+xzthclear);
						}					
					}
				}				
				operation = "系统评分";
		    }
		}		    
		return operation;
	}
	
	
	
	/**
	 * 按照当天时间theDay查询记录中的领取人和提交人以及评估人
	 */
	public Cleanrecord selectRecord(){
		Cleanrecord cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",Util.getDateTime("yyyy-MM-dd"));
		return cleanrecord;
	}
	/**
	 * 按照当天时间evaluatorTime查询记录中的领取人和提交人以及评估人
	 */
	public Cleanrecord selectRecord1(){
		Cleanrecord cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");		
		Cleanrecord cleanrecord1 = new Cleanrecord();
		if(cleanrecord !=null){
			cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord.getTheDay());
		}
		return cleanrecord1;
	}
	/**
	 * 按照任意theDay时间进行查询记录中的领取人和提交人以及评估人
	 */
	public Cleanrecord selectRecordTheDay(String theDay){
		Cleanrecord cleanrecord = new Cleanrecord();
		if(theDay !=null&&!(theDay.equals(""))){
			cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",theDay);
		}else{
			cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		}
		return cleanrecord;
	}
	
	/**
	 * 查询数据库记录中未完成的记录的记录时间
	 */
	public Cleanrecord selectThaDay(Cleanrecord cleanrecord){
        if(cleanrecord !=null&&cleanrecord.getTheDay() !=null){
        	cleanrecord = (Cleanrecord)totalDao.getObjectByCondition("from Cleanrecord where theDay=?",Util.getDateTime("yyyy-MM-dd"));
        }
		return cleanrecord;
	}		
	/**
	 * 进行领取人和领取时间的新增
	 */
	public boolean insertReceive(){
		Users u = Util.getLoginUser();
		LpanUtils lpanUtils = new LpanUtils();
		String temp_str = lpanUtils.getNowDate();
		//查询下一个值日人所属日期
		ClearInfo clearInfo =  (ClearInfo)totalDao.getObjectByCondition("select DISTINCT new com.task.entity.lpanclear.ClearInfo(clearDate) from ClearInfo where clearDate>? order by clearDate asc",temp_str);
		String evaTime = null;
		if(clearInfo !=null){
			System.out.println("输出一下一组值日人的值日日期"+clearInfo.getClearDate());
			evaTime = clearInfo.getClearDate();
		}	
		Cleanrecord cleanrecord1 =new Cleanrecord();
		cleanrecord1.setReceiveTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		cleanrecord1.setTheDay(Util.getDateTime("yyyy-MM-dd"));			
		cleanrecord1.setReceive(u.getName());
		cleanrecord1.setEvaluatorTime(evaTime);
		cleanrecord1.setSubmitter("未提交");
		cleanrecord1.setEvaluator("未评分");
		cleanrecord1.setState("未完成");
		return totalDao.save(cleanrecord1);
	}			
	/**
	 * 对记录进行提交人和提交时间的修改
	 */
	public boolean updateSubmission(){
		LpanUtils lpanUtils = new LpanUtils();
		Users u = Util.getLoginUser();
		String temp_str = lpanUtils.getNowDate();
		String submitterTime = "";
		Date dt = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		submitterTime = sdf1.format(dt);		
		Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",temp_str);
		if(cleanrecord1 !=null){
			cleanrecord1.setTheDay(temp_str);
			cleanrecord1.setSubmitter(u.getName());
			cleanrecord1.setSubmitterTime(submitterTime);
			return totalDao.update(cleanrecord1);
		}
		return false;
	}			
	/**
	 * 对记录进行评估人和评估时间以及评分和此记录状态的修改
	 */
	public boolean updateEva(Cleanrecord cleanrecord){
		Cleanrecord cleanrecord2 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		Users u = Util.getLoginUser();
		System.out.println("输出评分人:" + u.getName());
		if(cleanrecord !=null&&cleanrecord2 !=null){			
			Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord2.getTheDay());
			if(cleanrecord1 !=null&&cleanrecord1.getState().equals("未完成")){
				cleanrecord1.setEvaluator(u.getName());
				cleanrecord1.setEvaluatorTime(Util.getDateTime("yyyy-MM-dd"));
				cleanrecord1.setFraction(cleanrecord.getFraction());				
				cleanrecord1.setState("已完成");
				cleanrecord1.setTheDay(cleanrecord2.getTheDay());
				return totalDao.update(cleanrecord1);
			}
		}
		return false;
	}	
	
	/**
	 * 在当天未进行提交时提交和评分一起修改来终止记录
	 */
	public boolean terminationRecord(Cleanrecord cleanrecord){		
		if(cleanrecord !=null){
			Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord.getTheDay());
			if(cleanrecord1 !=null){
				cleanrecord1.setSubmitter("系统提交");
				cleanrecord1.setSubmitterTime(Util.getDateTime("yyyy-MM-dd"));
				cleanrecord1.setEvaluator("系统评分");
				cleanrecord1.setEvaluatorTime(Util.getDateTime("yyyy-MM-dd"));
				cleanrecord1.setState("系统完成");
				cleanrecord1.setFraction(0);
				cleanrecord1.setTheDay(cleanrecord.getTheDay());
				return totalDao.update(cleanrecord1);
			}
		}	
		return false;
	}
	
	/**
	 * 第二天未进行评分,用修改来终止记录
	 */
	public boolean evaluatorRecord(Cleanrecord cleanrecord){
        if(cleanrecord !=null){
        	Cleanrecord cleanrecord1 = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord where theDay=?",cleanrecord.getTheDay());
			if(cleanrecord1 !=null){
				cleanrecord1.setEvaluator("系统评分");
				cleanrecord1.setEvaluatorTime(Util.getDateTime("yyyy-MM-dd"));
				cleanrecord1.setFraction(cleanrecord.getFraction());
				cleanrecord1.setTheDay(cleanrecord.getTheDay());
				return totalDao.update(cleanrecord1);
			}
        }
		return false;
	}
	
	/**
	 * 在当天未有记录的情况下(系统判定后)领取提交评分标题评分完全由系统方法完成
	 */
	public boolean insCleanAndScore(){
		//获取最后一条记录
		Cleanrecord cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		LpanUtils lpanUtils = new LpanUtils(); 
		String temp_str = lpanUtils.getNowDate();
		
		List<ClearInfo> clearInfoList = new ArrayList<ClearInfo>();               
		if(cleanrecord !=null){
			clearInfoList = totalDao.query("from ClearInfo where clearDate=?",cleanrecord.getEvaluatorTime());
		}	    
		Cleanrecord cleanrecord1 =new Cleanrecord();
		cleanrecord1.setReceiveTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		cleanrecord1.setTheDay(cleanrecord.getEvaluatorTime());			
		cleanrecord1.setReceive("系统领取");
		cleanrecord1.setSubmitter("系统提交");
		cleanrecord1.setSubmitterTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		cleanrecord1.setEvaluator("系统评分");
		cleanrecord1.setEvaluatorTime(Util.getDateTime("yyyy-MM-dd"));
		cleanrecord1.setFraction(0);
		cleanrecord1.setState("系统完成");
		
		boolean savejg =  totalDao.save(cleanrecord1);
		boolean xtjpffhjg = false;
		if(savejg&&clearInfoList !=null){
			for(ClearInfo clearInfo : clearInfoList){
				Score score = new Score();
				score.setScoreDate(temp_str);
				score.setEmployee(clearInfo.getEmployee());
				score.setTitleName("整体");
				score.setFraction(0);
				score.setFrequency(1);
				score.setDifference(1);
				score.setClearTheDay(cleanrecord.getEvaluatorTime());
			    xtjpffhjg = totalDao.save(score);
				System.out.println("未评分并自动评分后的结果zdpfjg" + xtjpffhjg);
			}
			if(xtjpffhjg){
				return true;
			}
		}		
		return false;
	}
	
	
	/**
	 *  查询记录中最后一条记录,并查询最后一条记录的theday时间和状态...
	 */
	public Cleanrecord selectLastOne(){
		Cleanrecord cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
		return cleanrecord;
	}
	/**
	 * 根据最后一条记录获取的theday查询有几条记录并是否完成返回集合
	 */
	public List<Cleanrecord> selectLastTheDay(String theday){
		List<Cleanrecord> cleanrecordList = new ArrayList<Cleanrecord>();
		if(theday !=null){
			cleanrecordList = totalDao.query(" from Cleanrecord where theday=?", theday);
		}
		return cleanrecordList;
	}
	
	
	//一下为Score实体类接口=================================================================================
	//=========================================================================================================	
	/**
	 * 新增所属人评分记录
	 * @return
	 */
    public boolean insertScore(Score score){
    	//Score score1 = new Score();
    	Employee employee = new Employee();
    	List<ClearInfo> clearTheDayList = new ArrayList<ClearInfo>();
    	List<Score> scoreList  = new ArrayList<Score>();
    	if(score !=null&&score.getTitleName() !=null&&score.getFraction() !=null){
    		
    		scoreList = totalDao.query(" from Score where scoreDate=? and titleName=?",Util.getDateTime("yyyy-MM-dd"),score.getTitleName());
        	
    		Cleanrecord cleanrecord = (Cleanrecord)totalDao.getObjectByCondition(" from Cleanrecord order by id desc");
        	if(cleanrecord !=null){
        		clearTheDayList = totalDao.query("from ClearInfo where clearDate=?", cleanrecord.getTheDay());
        	}  	
        	if(score !=null&&scoreList.size()==0&&clearTheDayList !=null){
        		for(ClearInfo clearInfo : clearTheDayList){	    
        			Score score1 = new Score();
    	    		score1.setFraction(cleanrecord.getFraction());
    	    		score1.setFrequency(1);
    	    		score1.setDifference(0);
    	    		score1.setScoreDate(Util.getDateTime("yyyy-MM-dd"));
    	    		score1.setClearTheDay(cleanrecord.getTheDay());
    	    		score1.setTitleName(score.getTitleName());
    	    		employee.setId(clearInfo.getEmployee().getId());
    	    		score1.setEmployee(employee);	
    	    		boolean fhpfcg =  totalDao.save(score1);
    	    		System.out.println("返回评分是否成功"+fhpfcg);
        		}
        		return true;
        	}
    	}
    	
    	return false;
    }  
    /**
     * 查询评分记录
     */
    public List<Score> selectAllScore(Integer days){
    	List<Score> scoreList = new ArrayList<Score>();
    	if(days ==null){
    		scoreList = totalDao.query("FROM Score ");
    	}else if(days==7){
    		scoreList = totalDao.query("FROM Score where clearTheDay BETWEEN ? AND ? ",LpanUtils.getTimesWeekmorning(),LpanUtils.getTimesWeeknight());
    	}
    	else if(days==30){
    		scoreList = totalDao.query("FROM Score where clearTheDay BETWEEN ? AND ? ",LpanUtils.getTimesMonthmorning(),LpanUtils.getTimesMonthnight());
    	}   	 	
    	return scoreList;
    } 
    /**
     * 按照一个月之内进行查询
     */
    public List<Score> selectWeekScore(Score score){
    	
    	return null;
    }
	
    
    /**
     * 按照最后一条领取提交评分记录的日期查询评分记录和分数
     */
    public List<Score> selectDateRecord(){
    	Cleanrecord cleanrecord = new Cleanrecord();
    	List<Score> scoretheDay = new ArrayList<Score>();
    	//排序后获取第一条
    	cleanrecord = (Cleanrecord)totalDao.getObjectByCondition("from Cleanrecord order by id desc");
    	//查询今天是否生成记录了
    	Cleanrecord cleanrecordjr = (Cleanrecord)totalDao.getObjectByCondition("from Cleanrecord where theDay=?",Util.getDateTime("yyyy-MM-dd"));	
    	//排序获取全部记录
    	if(cleanrecord!=null) {
    		List<Cleanrecord> cleanrecordList = (List<Cleanrecord>)totalDao.query("from Cleanrecord order by id desc");
        	if(cleanrecordjr ==null){
        		scoretheDay = totalDao.query("select DISTINCT new com.task.entity.lpanclear.Score( titleName,fraction,scoreDate,clearTheDay) from Score where clearTheDay=?",cleanrecord.getTheDay());
        	}else{
        		for(int i =0;i<cleanrecordList.size();i++){
        			if(i==1){
    					scoretheDay = totalDao.query("select DISTINCT new com.task.entity.lpanclear.Score( titleName,fraction,scoreDate,clearTheDay) from Score where clearTheDay=?",cleanrecordList.get(i).getTheDay());
        			}
        		}
        	}   	
    	}
    	return scoretheDay;
    }
   
    /**
     * 按照所指定日期查询评分记录和分数
     */
    public List<Score> selectDateDayRecord(String date){
    	List<Score> scoretheDay = new ArrayList<Score>();
    	if(date !=null){
        	scoretheDay = totalDao.query("select DISTINCT new com.task.entity.lpanclear.Score(titleName,fraction,scoreDate,clearTheDay) from Score where clearTheDay=?",date);
    	}
    	return scoretheDay;
    }
       
   
    /**
     * 按照日期查询前三天(包括当天)的评分记录和分数
     */
    public List<Score> selectDateSanRecord(){
    	Date dt = new Date();
    	List<Score> scoreList = totalDao.query("select DISTINCT new com.task.entity.lpanclear.Score(titleName,fraction,scoreDate,clearTheDay) FROM Score where clearTheDay BETWEEN ? AND ? order by clearTheDay asc",LpanUtils.getFrontErDay(dt),Util.getDateTime("yyyy-MM-dd"));  	
    	return scoreList;
    }
    
    /**
     * 按照日期查询前七天(包括当天)的评分记录和分数
     */
    public List<Score> selectDateQiRecord(){
    	Date dt = new Date();
    	List<Score> scoreList = totalDao.query("select DISTINCT new com.task.entity.lpanclear.Score(titleName,fraction,scoreDate,clearTheDay) FROM Score where clearTheDay BETWEEN ? AND ? ",LpanUtils.getFrontLiuDay(dt),Util.getDateTime("yyyy-MM-dd"));   	
    	return scoreList;   	
    }
    
    
    /**
     * 新增图片上传记录
     */
    public boolean insertPhrecord(String route,String primaryName){
    	Users u = Util.getLoginUser();
    	LpanUtils lpanUtils = new LpanUtils();
		String temp_str = lpanUtils.getNowDate();
		ClearInfo clearInfo =  (ClearInfo)totalDao.getObjectByCondition("select DISTINCT new com.task.entity.lpanclear.ClearInfo(clearDate) from ClearInfo where clearDate<? order by clearDate asc",temp_str);
    	ClearPhone clearPhone1 = new ClearPhone();
    	if(route !=null&&primaryName !=null&&clearInfo !=null){
    		clearPhone1.setPictureDay(clearInfo.getClearDate());
    		clearPhone1.setDayload(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));  		
    		clearPhone1.setRoute(route);
    		clearPhone1.setPrimaryName(primaryName);
    		clearPhone1.setManload(u.getName());
    		return totalDao.save(clearPhone1);
    	}
    	return true;
    }
    
    
    
    
    /**
     * 查询所有图片记录按分页查询
     */
    public Map<Integer,Object> selectPhrecordList(ClearPhone clearPhone,Integer pageNo,Integer pageSize){
    	
    	if(clearPhone ==null){
    		clearPhone= new ClearPhone();
		}
		String hql = null;
		hql = totalDao.criteriaQueries(clearPhone, null);
		hql+= " order by id asc";
		List<ClearPhone> clearPhoneList = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new LinkedHashMap<Integer,Object>(); 
		map.put(1, clearPhoneList);
		map.put(2, count);				
		
	    return map;
    	
    }
    

    
    /**
     * 查询所属记录
     */
    public List<ClearPhone> selectPhrecord(String pictureDay){
    	List<ClearPhone> cphoneList = new ArrayList<ClearPhone>();
    	if(pictureDay !=null){
        	cphoneList = totalDao.query(" from ClearPhone where pictureDay=?",pictureDay);
    	}
    	return cphoneList;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}