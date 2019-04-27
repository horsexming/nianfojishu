package com.task.Server;

import com.task.entity.ChartNOGzType;
import java.util.List;
import java.util.Map;

import com.task.entity.ChartNOSC;
import com.task.entity.ChartNOSQ;

public interface ChartNOSQServer {
	/**\
	 * 添加
	 * @param cq
	 * @return
	 */
	String addChartNOSQ(ChartNOSQ cq);
	/**
	 * 查询所有申请记录
	 * @return
	 */
	Object [] findAllChartNOSQ(ChartNOSQ cq,int pageNo, int pagesize,String pageStatus);
	/**
	 * 查询所有图号
	 */
	Object [] findAllChartNOSC(ChartNOSC cc,int pageNo,int pagesize,String pageStatus);
	
	/**
	 * 删除申请
	 */
	String delChartNOSQ(ChartNOSQ cq);
	/**
	 * 修改申请
	 */
	String updateChartNOSQ(ChartNOSQ cq);
	/**
	 * 删除图号
	 */
	boolean delChartNOSC(ChartNOSC cc);
	/**
	 * 修改图号
	 */
	boolean updateChartNOSC(ChartNOSC cc);
	/**
	 * 根据Id获得申请记录
	 */
	Object[] findChartNOSQById(Integer id);
	/**
	 * 查询所有图号规则类型
	 */
	List<ChartNOGzType> findAllChartNOGzType(String pageStatus);
	
	ChartNOSQ getfirstNo(ChartNOSQ cq);
	/**
	 * 批量添加
	 */
	String pladdChartNOSQ(List<ChartNOSQ> cqList);
	/**
	 * 禁用图号
	 */
	boolean	disablecc(Integer id);
	public String export(ChartNOSC cc, String pageStatus);
	/**
	 * 添加规则类型
	 */
	boolean addgzType(ChartNOGzType gzType);
	/**
	 * 查询所有规则类型（分页）
	 */
	Object [] findAllgzType(ChartNOGzType gzType,int pageNo,int pagesize);
	/**
	 * 删除规则类型
	 */
	boolean delgzType(ChartNOGzType gzType);
	/**
	 * 
	 */
	Map<String, Object> changStyle(Integer id);
	
	String	disableccList(Integer[] arrayId );
	/**
	 * 打回重新申请
	 */
	public String cxsq(ChartNOSQ cq);
		
	/**
	 * 根据id获取图号
	 */
	public ChartNOSC findChartNOSQByid(Integer id);
	/**
	 * 重复的编码重新排序
	 */
	public void ChartNOSort();
	/**
	 * 解处理图号跳层问题
	 * @param firstChartNO 跳层前最后一个图号
	 * @param secondChartNO 跳层后第一个图号
	 * @return
	 */
	public String dealWithJumpLayer(String firstChartNO,String secondChartNO); 
		
	/**
	 * 
	 */
	ChartNOSQ getCqByType(String type);
}
