package com.lc.action.studentAttend;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.task.Server.studentAttend.StudentAttendServer;
import com.task.entity.Lesson;
import com.task.entity.StudentAttendVo;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

public class AttendClassAction {
	private StudentAttendServer studentAttendServer;
	private String ldate;
	private String ltime;
	private Integer lnumber;
	private String code;
	private String name;
	private String month;
	private Integer id;
	private String pwd;
	private String role;
	private Lesson lesson;
	private List<Lesson> lessonList;
	private List<Lesson> wlessonList;//未激活课程
	private StudentAttendVo studentAttendVo;
	private List<StudentAttendVo> studentAttendVoList;
	private List list;
	private Users user;
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private String tag;
	
	
	public String initaddLesson(){
		try {
			if(lesson!=null){
				ActionContext.getContext().getSession().put("lesson", lesson);
			}else{
				lesson=(Lesson) ActionContext.getContext().getSession().get("lesson");
			}
			if("del_error".equals(errorMessage)){
				errorMessage = "删除失败";
			}else if("del_true".equals(errorMessage)){
				errorMessage = "删除成功";
			}else{
				errorMessage =  "";
			}
			Map<Integer, Object> map=new HashMap<Integer,Object>();
			map=studentAttendServer.findAllLessonlist(lesson, Integer.parseInt(cpage), pageSize);
			lessonList=(List<Lesson>) map.get(1);
			wlessonList = studentAttendServer.findAllWLessonlist();
			if(lessonList!=null && lessonList.size()>0){
				int count=(Integer)map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");  
				this.setUrl("attendClassAction_initaddLesson.action?status=");
				
			}else{
				
			}
		} catch (Exception e) {
			errorMessage = "出错了!";
		}
		
		return "lesson_add";
	}
	
	
	public String addLesson() {
		try {
			if(lesson!=null){
				
				errorMessage = studentAttendServer.addLesson(lesson);
				if("true".equals(errorMessage)){
					return "initaddLesson";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "出错了!";
		}
		
		return "error";
	}
	public String getlessonByid(){
		try {
			if(id!=null && id>0){
				lesson = studentAttendServer.getLessonById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "出错了!";
		}
		
		return "lesson_update";
	}
	public void findAllLessonList(){
		try {
			lessonList = studentAttendServer.findAllLesson();
			if(lessonList!=null && lessonList.size()>0){
				MKUtil.writeJSON(true, "获取成功", lessonList);
			}else{
				MKUtil.writeJSON(false, "没有获取到课时", null);
			}
		} catch (Exception e) {
			MKUtil.writeJSON(false,"啊哦，出错了呢!",e);
		}
		
	}  
	public String lessonattend(){
		if(code!=null){
			lessonList = studentAttendServer.getLessonS(code);
			user = Util.getLoginUser();
		}
		return "lesson_attend";
	}
	
	public void attend() {
		// TODO Auto-generated method stub
		if(lnumber!=null && !"".equals(lnumber)){
			lesson = studentAttendServer.getLessonByNumber(lnumber);
			if(lesson!=null){
				id = lesson.getId();
			}
		}
		String msg = studentAttendServer.attend(code, id);
		if(msg.equals("true")){
			MKUtil.writeJSON(true,"添加成功！",null);
		}else{
			MKUtil.writeJSON(false,msg,null);
		}
	}

	public void login() {
		// TODO Auto-generated method stub
		String msg=studentAttendServer.login(code, pwd, role);
		if(msg.equals("true")){
			MKUtil.writeJSON(true,null,null);
		}else{
			MKUtil.writeJSON(false,msg,null);
		}
	}
	public void getLessonsForSingle(){
		if(code!=null){
			lessonList = studentAttendServer.getLessonS(code);
		}
		MKUtil.writeJSON(true,null,lessonList);
	}

	public String delLesson(){
		try {
			if(lesson!=null){
				boolean bool = studentAttendServer.delLesson(lesson);
				if(bool){
					return "initaddLesson";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	public String updateLesson(){
		try {
			boolean bool = studentAttendServer.updateLesson(lesson);
			if(bool){
				errorMessage = "修改成功";
				return "lesson_update";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "error";
	}
	public String selectAttend() {
		pageSize=30;
		if(code!=null){
			ActionContext.getContext().getSession().put("teachercode", code);
		}else{
			code=(String) ActionContext.getContext().getSession().get("teachercode");
		}
		if(name!=null){
			ActionContext.getContext().getSession().put("teachername", name);
		}else{
			name=(String) ActionContext.getContext().getSession().get("teachername");
		}
		if(month!=null){
			ActionContext.getContext().getSession().put("teachermonth", month);
		}else{
			month=(String) ActionContext.getContext().getSession().get("teachermonth");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=studentAttendServer.findAllStudentAttendVolist(code,name,month, Integer.parseInt(cpage), pageSize);
		list=(List) map.get(1);
		studentAttendVoList=(List<StudentAttendVo>) map.get(2);
		if(studentAttendVoList!=null && studentAttendVoList.size()>0){
			int count=(Integer)map.get(3);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("attendClassAction_selectAttend.action?status=");
			
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "selectAttend";
	}
	public String activationLesson(){
		try {
			lesson = studentAttendServer.getLessonById(id);
			lesson.setActivation("激活");
			boolean bool =	studentAttendServer.updateLesson(lesson);
			if(bool){
				errorMessage = "jh_true";
				return "initaddLesson";
			}
			errorMessage = "激活失败";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "啊哦，出错了";
		}
		return "error";
	}
	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getLtime() {
		return ltime;
	}

	public void setLtime(String ltime) {
		this.ltime = ltime;
	}

	public Integer getLnumber() {
		return lnumber;
	}

	public void setLnumber(Integer lnumber) {
		this.lnumber = lnumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public StudentAttendServer getStudentAttendServer() {
		return studentAttendServer;
	}

	public void setStudentAttendServer(StudentAttendServer studentAttendServer) {
		this.studentAttendServer = studentAttendServer;
	}


	public Lesson getLesson() {
		return lesson;
	}


	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}


	public List<Lesson> getLessonList() {
		return lessonList;
	}


	public void setLessonList(List<Lesson> lessonList) {
		this.lessonList = lessonList;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public StudentAttendVo getStudentAttendVo() {
		return studentAttendVo;
	}


	public void setStudentAttendVo(StudentAttendVo studentAttendVo) {
		this.studentAttendVo = studentAttendVo;
	}


	public List<StudentAttendVo> getStudentAttendVoList() {
		return studentAttendVoList;
	}


	public void setStudentAttendVoList(List<StudentAttendVo> studentAttendVoList) {
		this.studentAttendVoList = studentAttendVoList;
	}


	public List getList() {
		return list;
	}


	public void setList(List list) {
		this.list = list;
	}


	public List<Lesson> getWlessonList() {
		return wlessonList;
	}


	public void setWlessonList(List<Lesson> wlessonList) {
		this.wlessonList = wlessonList;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	
}
