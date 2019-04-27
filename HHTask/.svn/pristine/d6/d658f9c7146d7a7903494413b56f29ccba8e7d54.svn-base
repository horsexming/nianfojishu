package com.task.Server.gongyi.gongxu;

import java.io.File;
import java.util.List;

import com.task.entity.gongyi.GongyiGuichengAffix;
import com.task.entity.gongyi.gongxu.DetectionItem;
import com.task.entity.gongyi.gongxu.HanjieGuochengCanshu;
import com.task.entity.gongyi.gongxu.HanjieJianceXiangmu;
import com.task.entity.gongyi.gongxu.HanjieZuoyeGuifan;
import com.task.entity.gongyi.gongxu.MaoliaoJishuTiaojian;
import com.task.entity.gongyi.gongxu.MaoliaoParam;
import com.task.entity.gongyi.gongxu.OperationOrder;
import com.task.entity.gongyi.gongxu.OperationOrderItem;
import com.task.entity.gongyi.gongxu.OperationStandard;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.gongxu.ProcessGuochengCanshu;
import com.task.entity.gongyi.gongxu.ProcessPart;
import com.task.entity.gongyi.gongxu.ProcessTable;

public interface ProcessDataServer {
	/**
	 * 添加工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessData addProcessData(ProcessData processData);

	/**
	 * 删除工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public String deleteProcessData(ProcessData processData);

	/**
	 * 更新工序数据记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public String updateProcessData(ProcessData processData);

	/**
	 * 获得工序数据记录
	 * 
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataById(Integer id);

	/**
	 * 获得工序数据记录 根据工艺规程ID 工序ID
	 * 
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataBygongyiGuichengIdandprocessId(
			Integer gongyiGuichengId, Integer processId);

	/**
	 * 获得工序数据记录 根据工艺规程ID
	 * 
	 * @param id
	 * @return
	 */
	public List getProcessDataListBygongyiGuichengId(Integer gongyiGuichengId);

	/**
	 * 判断是否存在工序数据记录
	 * 
	 * @param
	 * @return
	 */
	public ProcessData isExistProcessData(Integer gongyiGuichengId,
			Integer processId);

	/********************************* 作业规范 *************************************/
	/**
	 * 添加作业规程
	 * 
	 * @param
	 * @return
	 */
	public String addOperationStandard(OperationStandard operationStandard);

	/**
	 * 删除作业规程
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationStandard(OperationStandard operationStandard);

	/**
	 * 修改作业规程
	 * 
	 * @param
	 * @return
	 */
	public String updateOperationStandard(OperationStandard operationStandard);

	/**
	 * 获得作业规程
	 * 
	 * @param
	 * @return
	 */
	public OperationStandard getOperationStandardById(Integer id);

	/**
	 * 获得作业规范集合
	 * 
	 * @param processDataId
	 * @return
	 */
	public List findAllOperationStandardListByprocessDataId(
			Integer processDataId);

	/********************************** 过程参数 **********************************/
	/**
	 * 添加过程参数记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessGuochengCanshu addProcessGuochengCanshu(
			ProcessGuochengCanshu processGuochengCanshu);

	/**
	 * 更新过程参数记录
	 * 
	 * @param ProcessData
	 * @return
	 */
	public ProcessGuochengCanshu updateProcessGuochengCanshu(
			ProcessGuochengCanshu processGuochengCanshu);

	/**
	 * 获得过程参数记录
	 * 
	 * @param id
	 * @return
	 */
	public ProcessGuochengCanshu getProcessGuochengCanshuById(Integer id);

	/**
	 * 判断是否存在过程参数记录
	 * 
	 * @param id
	 * @return
	 */
	public List getProcessGuochengCanshuByprocessDataId(Integer procesDataId);

	/*********************************** 检测项目 ***********************************/
	/**
	 * 添加检测项目
	 * 
	 * @param
	 * @return
	 */
	public String addDetectionItem(DetectionItem detectionItem);

	/**
	 * 删除检测项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteDetectionItem(DetectionItem detectionItem);

	/**
	 * 修改检测项目
	 * 
	 * @param
	 * @return
	 */
	public String updateDetectionItem(DetectionItem detectionItem);

	/**
	 * 获得检测项目
	 * 
	 * @param
	 * @return
	 */
	public DetectionItem getDetectionItemById(Integer id);

	/**
	 * 获得检测项目集合
	 * 
	 * @param processDataId
	 * @return
	 */
	public List findAllDetectionItemListByprocessDataId(Integer processDataId);

	/******************************** 工艺程序图表 *******************************************/
	/**
	 * 添加工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public ProcessTable addProcessTable(ProcessTable processTable);

	/**
	 * 删除工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public String deleteProcessTable(ProcessTable processTable);

	/**
	 * 修改工艺程序图表
	 * 
	 * @param
	 * @return
	 */
	public ProcessTable updateProcessTable(ProcessTable processTable);

	/**
	 * 获得工艺程序图表 根据ID
	 * 
	 * @param
	 * @return
	 */

	public ProcessTable getProcessTableById(Integer id);

	/**
	 * 获得工艺程序图表 更具工艺规程ID
	 * 
	 * @param
	 * @return
	 */
	public ProcessTable getProcessTableBygongyiGuichengId(
			Integer gongyiGuichengId);

	/********************************** 零件 **********************************/
	/**
	 * 添加零件
	 * 
	 * @param
	 * @return
	 */
	public ProcessPart addProcessPart(ProcessPart processPart);

	/**
	 * 删除零件
	 * 
	 * @param
	 * @return
	 */
	public String deleteProcessPart(ProcessPart processPart);

	/**
	 * 修改零件
	 * 
	 * @param
	 * @return
	 */
	public ProcessPart updateProcessPart(ProcessPart processPart);

	/**
	 * 获得工艺程序图表 根据ID
	 * 
	 * @param
	 * @return
	 */

	public ProcessPart getProcessPartById(Integer id);

	/**
	 * 获得工艺程序图表 更具工艺规程ID
	 * 
	 * @param
	 * @return
	 */

	public List getProcessPartListByprocessDataId(Integer processDataId);

	/********************************** 操作顺序 **********************************/
	/**
	 * 添加操作顺序
	 * 
	 * @param
	 * @return
	 */
	public OperationOrder addOperationOrder(OperationOrder operationOrder);

	/**
	 * 删除操作顺序
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationOrder(OperationOrder operationOrder);

	/**
	 * 修改操作顺序
	 * 
	 * @param
	 * @return
	 */
	public OperationOrder updateOperationOrder(OperationOrder operationOrder);

	/**
	 * 获得操作顺序 根据ID
	 * 
	 * @param
	 * @return
	 */

	public OperationOrder getOperationOrderById(Integer id);

	/**
	 * 获得操作顺序 更具工艺数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getOperationOrderListByprocessDataId(Integer processDataId);

	/********************************* 操作顺序下检查项目 *******************************************/
	/**
	 * 添加操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public OperationOrderItem addOperationOrderItem(
			OperationOrderItem operationOrderItem);

	/**
	 * 删除操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteOperationOrderItem(OperationOrderItem operationOrderItem);

	/**
	 * 修改操作顺序下检查项目
	 * 
	 * @param
	 * @return
	 */
	public OperationOrderItem updateOperationOrderItem(
			OperationOrderItem operationOrderItem);

	/**
	 * 获得操作顺序下检查项目 根据ID
	 * 
	 * @param
	 * @return
	 */

	public OperationOrderItem getOperationOrderItemById(Integer id);

	/**
	 * 获得操作顺序下检查项目 根据操作顺序ID
	 * 
	 * @param
	 * @return
	 */

	public List getOperationOrderItemListByoperationOrderId(
			Integer operationOrderId);

	/***************************************** 毛料参数 ********************************************/
	/**
	 * 添加毛料参数
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoParam addMaoliaoParam(MaoliaoParam maoliaoParam);

	/**
	 * 删除毛料参数
	 * 
	 * @param
	 * @return
	 */
	public String deleteMaoliaoParam(MaoliaoParam maoliaoParam);

	/**
	 * 修改毛料参数
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoParam updateMaoliaoParam(MaoliaoParam maoliaoParam);

	/**
	 * 获得毛料参数
	 * 
	 * @param
	 * @return
	 */

	public MaoliaoParam getMaoliaoParamById(Integer id);

	/**
	 * 获得毛料参数 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getMaoliaoParamListByProcessDataId(Integer processDataId);

	/***************************************** 毛料技术条件 ********************************************/
	/**
	 * 添加毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoJishuTiaojian addMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian);

	/**
	 * 删除毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public String deleteMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian);

	/**
	 * 修改毛料技术条件
	 * 
	 * @param
	 * @return
	 */
	public MaoliaoJishuTiaojian updateMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian);

	/**
	 * 获得毛料技术条件
	 * 
	 * @param
	 * @return
	 */

	public MaoliaoJishuTiaojian getMaoliaoJishuTiaojianById(Integer id);

	/**
	 * 获得毛料技术条件 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getMaoliaoJishuTiaojianListByProcessDataId(Integer processDataId);

	/***************************************** 焊接作业规范 ********************************************/
	/**
	 * 添加焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public HanjieZuoyeGuifan addHanjieZuoyeGuifan(
			HanjieZuoyeGuifan hanjieZuoyeGuifan);

	/**
	 * 删除焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieZuoyeGuifan(HanjieZuoyeGuifan hanjieZuoyeGuifan);

	/**
	 * 修改焊接作业规范
	 * 
	 * @param
	 * @return
	 */
	public HanjieZuoyeGuifan updateHanjieZuoyeGuifan(
			HanjieZuoyeGuifan hanjieZuoyeGuifan);

	/**
	 * 获得焊接作业规范
	 * 
	 * @param
	 * @return
	 */

	public HanjieZuoyeGuifan getHanjieZuoyeGuifanById(Integer id);

	/**
	 * 获得焊接作业规范 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getHanjieZuoyeGuifanListByProcessDataId(Integer processDataId);

	/***************************************** 焊接过程参数 ********************************************/
	/**
	 * 添加焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public HanjieGuochengCanshu addHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu);

	/**
	 * 删除焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu);

	/**
	 * 修改焊接过程参数*
	 * 
	 * @param
	 * @return
	 */
	public HanjieGuochengCanshu updateHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu);

	/**
	 * 获得焊接过程参数*
	 * 
	 * @param
	 * @return
	 */

	public HanjieGuochengCanshu getHanjieGuochengCanshuById(Integer id);

	/**
	 * 获得焊接过程参数 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public HanjieGuochengCanshu getHanjieGuochengCanshuByProcessDataId(
			Integer processDataId);

	/***************************************** 焊接检测项目 ********************************************/
	/**
	 * 添加焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public HanjieJianceXiangmu addHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu);

	/**
	 * 删除焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public String deleteHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu);

	/**
	 * 修改焊焊接检测项目
	 * 
	 * @param
	 * @return
	 */
	public HanjieJianceXiangmu updateHanjieJianceXiangmu(
			HanjieJianceXiangmu hanjieJianceXiangmu);

	/**
	 * 获得焊接检测项目
	 * 
	 * @param
	 * @return
	 */

	public HanjieJianceXiangmu getHanjieJianceXiangmuById(Integer id);

	/**
	 * 获得焊接检测项目 根据工序数据ID
	 * 
	 * @param
	 * @return
	 */

	public List getHanjieJianceXiangmuListByProcessDataId(Integer processDataId);

	/************************************** 工艺规程附件 *******************************************************/
	/**
	 * 添加工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public GongyiGuichengAffix addGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix);

	/**
	 * 删除工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public String deleteGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix);

	/**
	 * 修改工艺规程附件
	 * 
	 * @param
	 * @return
	 */
	public GongyiGuichengAffix updateGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix);

	/**
	 * 获得工艺规程附件
	 * 
	 * @param
	 * @return
	 */

	public GongyiGuichengAffix getGongyiGuichengAffixById(Integer id);

	/**
	 * 获得工艺规程附件集合
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByProcessDataId(
			Integer processDataId, String affixType);

	/**
	 * 获得工艺规程附件集合 for Select 工艺规程看板 工艺ID 工序ID 附件类型图片
	 * 
	 * @param gongyiGuichengAffix
	 * @return
	 */
	public List getGongyiGuichengAffixListForTupianForSelectByGongyiGuichengIdAndProcessDataId(
			GongyiGuichengAffix gongyiGuichengAffix);

	/**
	 * 获得工艺规程附件集合 工艺规程ID工序ID
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId(
			Integer gongyiGuichengId, Integer processDataId);

	/**
	 * 获得工艺规程附件集合 工艺规程ID工序ID
	 * 
	 * @param
	 * @return
	 */

	public List getGongyiGuichengAffixListByGongyiGuichengAffix(
			GongyiGuichengAffix gongyiGuichengAffix);

	/**
	 * 获得设备集合
	 * 
	 * @return
	 */
	public List getMachineList();

	/**
	 * 获得工装集合
	 * 
	 * @return
	 */
	public List getGzstoreList();

	/**
	 * 查询附件集合 根据工艺规程ID
	 * 
	 * @param gongyiGuichengId
	 * @return
	 */
	public List getGongyiGuichengAffixListByGongyiGuichengId(
			Integer gongyiGuichengId);

	/**
	 * 获得在干工序信息 根据工艺规程ID
	 * 
	 * @param id
	 * @return
	 */
	List getProcessBygGygcId(Integer gongyiGuichengId);
}
