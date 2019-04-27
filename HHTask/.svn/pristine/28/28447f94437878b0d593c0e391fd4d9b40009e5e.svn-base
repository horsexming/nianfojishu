package com.task.Server.SingleCar;

import java.util.List;
import java.util.Map;

import com.task.entity.singlecar.SingleCar;
import com.task.entity.singlecar.SingleCarAll;

public interface SingleCarServer {

	/***
	 * 申请用车单
	 * @param singleCar
	 * @return
	 */
	boolean addSingleCar(SingleCar singleCar);

	/***
	 * 查询省内用车单审核
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamListA(int parseInt, int pageSize);

	/***
	 * 查询省外用车单审核
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamListB(int parseInt, int pageSize);

	/***
	 * 省内用车单(通过、驳回)
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamListA(Integer[] detailSelect, String tag);

	 /***
	  * 省外用车单(通过、驳回)
	  * @param detailSelect
	  * @param tag
	  * @return
	  */
	boolean updateExamListB(Integer[] detailSelect, String tag);

	/***
	 * 查询用车单
	 * @param singleCar
	 * @param parseInt
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findSingleCar(SingleCar singleCar, int parseInt, int pageSize,
			String tag);

	/***
	 * 根据编号查询用车单
	 * @param id
	 * @return
	 */
	SingleCar findSingleCarById(Integer id);

	/***
	 * updateSingleCar
	 * @param singleCar
	 * @return
	 */
	boolean updateSingleCar(SingleCar singleCar);

	/**
	 * 删除用车信息
	 * @param id
	 * @return
	 */
	boolean delSingleCarById(Integer id);

	/***
	 * //总经办确认
	 * @param id
	 * @return
	 */
	boolean updateSingleCarStatus(Integer id);

	/***
	 * 查询各审批节点人
	 * @param id
	 * @return
	 */
	Map<Integer, Object> findPay_ExecutionNode(Integer id);
   /***
    * 根据驾驶员姓名统计总里程
    * @param pilotname
    * @return
    */
	SingleCarAll getCountkilometers(String pilotname,String firsttime,String finishtime,String car_number);
   /***
    * 申请审批里程数
    * @param pilotname
    * @param firsttime
    * @param finishtime
 * @param singleCarAll 
    * @return
    */

  boolean updateExamListC(String pilotname, String firsttime, String finishtime,String car_number);
 /***
  * 查询里程数
  * @param parseInt
  * @param pageSize
  * @return
  */
Object[] findExamListC(SingleCarAll singleCarAll, int parseInt, int pageSize,
		String status);
/**
 * 通过ID查询
 * @param id
 * @return
 */
SingleCarAll findSingleCarAllById(Integer id);

//boolean updateSingleCarAllStatus(Integer id);

/**
 * 通过参值查询
 * @param id
 * @return
 */
List findSingleCarAllNN(String s, String s1);

}
