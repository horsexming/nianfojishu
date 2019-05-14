package com.hhu.count.server;

import java.util.Date;
import java.util.List;
import com.hhu.count.entity.course;
import com.hhu.count.vo.CourseVO;
import com.hhu.count.vo.UserCourseVO;

public interface CourseServer {
	
	public void InsertCourse(course course);
	//通过发布者ID查询课程
	public List<course> selectByWriterId(String writerid);
	//查询所有
	public List<course> selectAll();
	//通过发布者ID以及状态查询
	public List<course>selectByState(String writerid,String state);
	//修改课程信息
	public void updateCourse(course course);
	//通过ID查询课程信息 
	public course selectByID(String id);
	//删除课程
	public void DeleteCourseByID(String id);
	//修改状态
	public void updateCourseState(String id,String state,Date publishTime,Date stopTime);
	//设置首次发布时间
	public void updatefirstTime(String id,Date firstDate);
	//模糊查询
	public List<course> selectCourseLike(String writerid,String coursename);
	//课程名的模糊查询 
	public List<course> FindCLikeCName(String coursename,String id);
	//发布者名的模糊查询
	public List<course> FindCLikeWriteName(String writer,String id);
	//课程名的模糊查询 没有阅读记录
	public List<course> FindCLikeCName2(String coursename);
	//发布者名的模糊查询没有阅读记录
	public List<course> FindCLikeWriteName2(String writer);
	//批量发布课程
	public void BatchRelease(String []id);
	//批量停止课程
	public void StopCourse(String []id);
	//用户首页
	public List<UserCourseVO>selectAllCourse(String id);
	
	//课程统计
	public List<CourseVO>SelCourseVO(String writerid);
	//权限绑定
	public void updatePowers(String id,String CoursePower);
	//查询课程
	public List<course>chaxunceshi(String id ,String username,Integer pn,Integer pagesize);
	public List<course>chaxunceshi(String id ,String username);
	//查询最新记录
	public List<course>selectLimitTen();
	public List<course>selectLimitTen(String username);
	public List<course>selLimitUser(String username,Integer pn, Integer pagesize);

}
