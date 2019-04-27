package com.task.Server.menjin;

import java.util.List;

import com.task.entity.menjin.Visit;
import com.task.entity.menjin.VisitCo;

@SuppressWarnings("unchecked")
public interface VisitServer {
	/**
	 * 添加来访登记
	 * 
	 * @param visit
	 * @return
	 */
	boolean addVisit(Visit visit);
	String addVisitOut(Visit visit);

	/**
	 * 根据id查询来访登记
	 * 
	 * @param visit
	 * @return
	 */
	Visit salvisitByid(Integer integer);

	/**
	 * 查询
	 * 
	 * @param visit
	 * 
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            每页个数
	 * @return
	 */
	Object[] findVisitByCondition(Visit visit, int parseInt, int pageSize,String taga);

	/**
	 * @author Li_Cong
	 * 根据id 去生成出门信息
	 * @param id
	 */
	public String backOut(Visit visit);
	
	/**
	 * @author Li_Cong
	 * 根据id 查询今天已识别未申请的车牌号
	 * @param id
	 */
	public List allWeisb();
	
	/**
	 * 再次申请
	 */
	public String agreen(Visit visit);
	
	/**
	 * 根据手机号码查询来访信息
	 * @param visit
	 * @return
	 */
	public int visitTel(Visit visit);
	/**
	 * 判断车牌号是否为内部车
	 * @param visit
	 * @return
	 */
	public int visitCarpai(String car);

	/**
	 * 删除申请记录
	 * @param visit1
	 * @return
	 */
	public boolean deleteVisit(Visit visit1);
	
	public List findVisitS();
	
	public void findUsers();//农业户口信息
	
	/**
	 * 添加来访登记(免申请)
	 * 
	 * @param visit
	 * @return
	 */
	boolean addVisitwithoutApply(Visit visit);
	
	List<VisitCo> findvisitColist();
	
}
