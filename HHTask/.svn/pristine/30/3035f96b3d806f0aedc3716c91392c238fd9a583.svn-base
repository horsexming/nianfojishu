package com.task.Server;

import java.util.List;
import java.util.Set;

import com.task.entity.Gantt;
import com.task.entity.sop.Procard;




public interface GanttServer {
	/**
	 * 添加甘特图一项
	 */
	 boolean addOneGantt(Gantt gantt);
	 /**
	  * 添加子项目
	  * @param gantt
	  * @return
	  */
	 String addSon(Gantt gantt);
	
	/**
	 * 查询所有甘特项
	 * @return
	 */
	List<Gantt> queryAllGantts();
	/**
	 * 查询所有甘特项1
	 * @return
	 */
	List<Gantt> queryAllGantts1(Integer rootId);
	
	/**删除甘特图一项
	 * @return
	 */
	boolean deleteOneGantt(Gantt gantt);

	/**修改甘特图一项
	 * @return
	 */
	boolean updateOneGantt(Gantt gantt);

	/**
	 * 查找所有部门
	 * @return
	 */
	List findAllDepts();

	/**
	 * 查找直接子节点
	 * @return
	 */
	List<Gantt> gainChild(Integer parentId);
	
	
	/**
	 * 递归查找子节点以及子节点的字节点.... 
	 */
	List<Integer> gainAllChild(Integer parentId,List<Integer> allChild);
	
	
	/**
	 * 递归查找父节点以及父节点的父节点.... 
	 */
	List<Integer> gainAllParent(Integer childId,List<Integer> allParent);
	/**
	 * 查找树形结构当前最大层数
	 */
	Integer queryMaxCengNUm();
	
	/**
	 *  导出生产零件时计算出单台数量(递归)
	 */
	Float gainDanTai(Gantt g,Float result);


	Gantt findById(int i);
}
