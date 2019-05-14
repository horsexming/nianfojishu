package com.hhu.count.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hhu.count.entity.course;
import com.hhu.count.vo.CourseVO;
import com.hhu.count.vo.UserCourseVO;



/*操作课程常用接口*/
public interface CourseDao {
	public void InsertCourse(course course);
	//通过发布者ID查询课程
	public List<course> selectByWriterId(String writerid);
	//查询所有
	public List<course> selectAll();
	//通过发布者ID以及状态查询
	public List<course>selectByState(Map<String, String>map);
	//修改课程信息
	public void updateCourse(course course);
	//通过ID查询课程信息 
	public course selectByID(String id);
	//删除课程
	public void DeleteCourseByID(String id);
	//修改状态
	public void updateCourseState(Map<String,Object> map);
	//设置首次发布时间
	public void updatefirstTime(Map<String,Object> map);
	//模糊查询
	public List<course> selectCourseLike(Map<String,String> map);
	//课程名的模糊查询 有阅读记录
	public List<course> FindCLikeCName(Map<String, String>map);
	//发布者名的模糊查询 有阅读记录
	public List<course> FindCLikeWriteName(Map<String, String>map);
	//课程名的模糊查询 没有阅读记录
	public List<course> FindCLikeCName2(String coursename);
	//发布者名的模糊查询没有阅读记录
	public List<course> FindCLikeWriteName2(String writer);

	//批量发布
	public void BatchRelease(Map<String, Object>map);
	//用户首页
	public List<UserCourseVO>selectAllCourse(String id);
	//查询最新记录
	public List<course>selectLimitTen();
	public List<course>selLimitUser(@Param("pn")Integer pn,@Param("pagesize") Integer pagesize);
	public List<course>chaxunceshi(@Param("id") String id,@Param("pn")Integer pn,@Param("pagesize") Integer pagesize);
	//课程统计
	public List<CourseVO>SelCourseVO(String writerid);
	//权限绑定
	public void updatePowers(Map<String,String>map);
	
	
}
