package com.task.ServerImpl.studentAttend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jna.platform.win32.Netapi32Util.User;
import com.task.Dao.TotalDao;
import com.task.Server.studentAttend.StudentAttendServer;
import com.task.entity.Lesson;
import com.task.entity.StudentAttend;
import com.task.entity.StudentAttendVo;
import com.task.entity.Users;
import com.task.util.MD5;
import com.task.util.Util;

public class StudentAttendServerImpl implements StudentAttendServer{
	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	@Override
	public String addLesson(Lesson lesson) {
		if(lesson!=null){
			String hql = "from Lesson where lnumber=?";
			if(lesson.getLnumber()>0){
				List<Lesson> lessonlist =	totalDao.query(hql, lesson.getLnumber());
				if(lessonlist ==null || lessonlist.size()==0){
				return	totalDao.save(lesson)+"";
				}else{
					return "已有该课时序号,请勿重复添加!";
				}
			}
			
		}
		return null;
	}

	@Override
	public String attend(String code, Integer id) {
		// TODO Auto-generated method stub
		Lesson lesson = (Lesson) totalDao.getObjectById(Lesson.class, id);
		if(lesson==null){
			return "该课时不存在！";
		}
		if(lesson.getActivation()==null||lesson.getActivation().equals("未激活")){
			return "该课程还未被激活！";
		}
		Users user = (Users) totalDao.getObjectByCondition("from Users where code=?", code);
		if(user==null){
			return "登陆人员有误！";
		}
		String addtime=(String) totalDao.getObjectByCondition("select addtime from StudentAttend where usercode and lId=?", id);
		if(addtime!=null){
			return "该课时已被登记过！";
		}
		StudentAttend sa = new StudentAttend();
		sa.setUserId(user.getId());//userId即学员Id
		sa.setUserName(user.getName());//学员名称
		sa.setUsercode(user.getCode());//学号
		sa.setlId(id);//课时Id;
		sa.setLdate(lesson.getLdate());//课时日期;
		sa.setAddtime(Util.getDateTime());//登记时间;
		sa.setLnumber(lesson.getLnumber());//课时序号;
		return totalDao.save(sa)+"";
	}

	@Override
	public String login(String code, String pwd, String role) {
		Users user= (Users) totalDao.getObjectByCondition("from Users where code=? and classrole='学生'", code);
		if(user==null){
			return "学号或密码错误!";
		}else{
			MD5 md5 = new MD5();
			String mdsPassword = md5.getMD5(pwd.getBytes());// 密码MD5转换
			String ps = user.getPassword().getPassword();
			if (!mdsPassword.equalsIgnoreCase(ps)) {
				return "学号或密码错误!";
			}
		}
		return "true";
	}
	
	
	@Override
	public List<StudentAttendVo> selectAttend(String ldate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lesson> getLessonS(String code) {
		// TODO Auto-generated method stub
		List<Lesson> list=totalDao.query("from Lesson");
		if(list!=null&&list.size()>0){
			for(Lesson lesson:list){
				if(lesson.getActivation()==null||lesson.getActivation().equals("未激活")){
					lesson.setAttendtime("未激活");
				}else{
					String addtime=(String) totalDao.getObjectByCondition("select addtime from StudentAttend where usercode=? and lId=?", code, lesson.getId());
					if(addtime!=null){
						lesson.setAttendtime(addtime);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<Lesson> findAllLesson() {
		String hql = "from Lesson order by lnumber";
		
		return totalDao.find(hql);
	}

	@Override
	public Map<Integer, Object> findAllLessonlist(Lesson lesson, int pageNo,
			int pageSize) {
		if(lesson == null){
			lesson = new Lesson();
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String hql = totalDao.criteriaQueries(lesson, null);
		int count = totalDao.getCount(hql);
		List<Lesson> lessonList = (List<Lesson>) totalDao
				.findAllByPage(hql+" and  activation = '激活' order by lnumber", pageNo, pageSize);
		map.put(1, lessonList);
		map.put(2, count);
		return map;
	}

	@Override
	public Lesson getLessonById(Integer id) {
		if(id!=null && id>0){
			return 	(Lesson) totalDao.get(Lesson.class, id);
		}
		return 	null;
	}
	@Override
	public Map<Integer, Object> findAllStudentAttendVolist(String code,
			String name, String month, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Users user=new Users();
		user.setClassrole("学生");
		if(code!=null&&code.length()>0){
			user.setCode(code);
		}
		if(name!=null&&name.length()>0){
			user.setName(name);
		}
		List<Lesson> lessons=new ArrayList<Lesson>();
		String sql = "from Lesson";
		if(month!=null&&month.length()>0){
			sql +=" where ldate like'"+month+"%'";
		}
		lessons = totalDao.query(sql);
		String hql = totalDao.criteriaQueries(user, null, null);
		int count = totalDao.getCount(hql);
		List<Users>  students= totalDao.findAllByPage(hql, pageNo, 100);
		List<StudentAttendVo> saVoList =new ArrayList<StudentAttendVo>();
		if(lessons!=null&&lessons.size()>0&&students!=null&&students.size()>0){
			for(Users student:students){
				StudentAttendVo asVo =new StudentAttendVo();
				asVo.setUserId(student.getId());
				asVo.setUserCode(student.getCode());
				asVo.setUserName(student.getName());
				List<Integer> lnumberList =new ArrayList<Integer>();
				List<String> attendStatusList =new ArrayList<String>();
				for(Lesson lesson:lessons){
					lnumberList.add(lesson.getLnumber());
					if(lesson.getActivation()==null||lesson.getActivation().equals("未激活")){
						attendStatusList.add("未激活");
					}else{
						StudentAttend sa = (StudentAttend) totalDao.getObjectByCondition("from StudentAttend where usercode=? and lId=?",student.getCode(), lesson.getId());
						if(sa==null){
							attendStatusList.add("未圆满"); 
						}else{
//					   attendStatusList.add(sa.getAddtime());  
							attendStatusList.add("圆满");  
						}
					}
				}
				asVo.setLnumberList(lnumberList);
				asVo.setAttendStatusList(attendStatusList);
				saVoList.add(asVo);
			}
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, lessons);
		map.put(2, saVoList);
		map.put(3, count);
		return map;
	}
@Override
	public boolean delLesson(Lesson lesson) {
		if(lesson!=null){
		return	totalDao.delete(lesson);
		}
		return false;
	}

	@Override
	public boolean updateLesson(Lesson lesson) {
		if(lesson!=null){
			return totalDao.update(lesson);
		}
		return false;
	}

	@Override
	public List<Lesson> findAllWLessonlist() {
		String hql = "from Lesson where activation <> '激活' or activation is null ";
		return totalDao.find(hql);
	}

	@Override
	public Lesson getLessonByNumber(Integer Number) {
		String hql = "from Lesson where lnumber = ?";
			
		return (Lesson) totalDao.getObjectByCondition(hql, Number);
	}
}
