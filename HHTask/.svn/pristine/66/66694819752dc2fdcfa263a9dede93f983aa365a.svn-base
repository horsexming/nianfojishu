package com.task.Server.studentAttend;

import java.util.List;
import java.util.Map;

import com.task.entity.Lesson;
import com.task.entity.StudentAttendVo;

public interface StudentAttendServer {
	
	/**
	 * 登陆
	 * @param code 学号
	 * @param pwd 密码
	 * @param role 角色
	 * @return
	 */
	 String login(String code,String pwd,String role);
	 /**
	  * 查询个人课时情况
	  * @param code
	  * @return
	  */
	 List<Lesson> getLessonS(String code);
	 /**
	  * 登记
	  * @param code 学号
	  * @param id 课时Id
	  * @return
	  */
	 String attend(String code,Integer id);
	 /**
	  * 添加课时
	  * @param ldate 日期
	  * @param ltime 上课时间
	  * @param lnumber 课时
	  * @return
	  */
	 String addLesson(Lesson lesson);
	 /**
	  * 查看登记情况
	  * @param ldate
	  * @return
	  */
	 List<StudentAttendVo> selectAttend(String ldate);
	/**
	 * 查询所有课时；
	 * @return
	 */
	 List<Lesson> findAllLesson();
	 Map<Integer, Object> findAllLessonlist(Lesson lesson,
				int pageNo, int pageSize);
	 /**
	  * 查询学生登记情况
	  * @param code
	  * @param name
	  * @param month
	  * @param parseInt
	  * @param pageSize
	  * @return
	  */
	Map<Integer, Object> findAllStudentAttendVolist(String code, String name,
			String month, int parseInt, int pageSize);
	 /**
	  * 根据Id查询课时
	  * @param id
	  * @return
	  */
	Lesson getLessonById(Integer id);
	/**
	 * 修改课时
	 */
	boolean updateLesson(Lesson lesson);
	/**
	 * 删除课时
	 */
	 boolean delLesson(Lesson lesson);
	 /**
	  * 查询所有未激活课程
	  */
	 List<Lesson> findAllWLessonlist();
	 /**
	  * 根据课时序号查询课时
	  */
	 Lesson getLessonByNumber(Integer Number);
	
}
