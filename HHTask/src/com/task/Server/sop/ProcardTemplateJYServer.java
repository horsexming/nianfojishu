package com.task.Server.sop;

import java.util.List;
import java.util.Map;

import com.task.entity.Machine;
import com.task.entity.sop.jy.ProcardTemplateJY;
import com.task.entity.sop.jy.ProcessTemplateJY;


/***
 * 流水卡片模板Server层
 * 
 * @author 刘培
 * 
 */
public interface ProcardTemplateJYServer {
	



	/***
	 * 查询所有总成流水卡片模板(分页)
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	Object[] findAllProcardTemplateJY(ProcardTemplateJY procardTemplateJY,
			int pageNo, int pageSize,String sql);

	/***
	 * 根据首层父类id查询流水卡片模板
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	List findProcardTemByRootId(int rootId);

	/***
	 * 根据id查询流水卡片模板
	 * 
	 * @param procardTemplateJY
	 * @return
	 */
	ProcardTemplateJY findProcardTemById(int id);


	/***
	 * 通过流水卡片id(外键)查询对应工序信息
	 * 
	 * @param fkId
	 * @return
	 */
	List findProcessByFkId(Integer fkId);

	/***
	 * 查询流水卡片模板(页面显示流水卡片模板详细使用)
	 * 
	 * @param id
	 * @return
	 */
	Object[] findCardTemForShow(int id);






	/***
	 * 包装精益节拍展示页面数据
	 * 
	 * @param id
	 * @param map
	 * @return
	 */
	String packageData(Integer id, Map map);
	/**
	 * 包装精益计算结果展示页面的数据
	 * @param id
	 * @param object
	 * @return
	 */
	String packageData2(Integer id, Map map);

	/***
	 * 查找总成信息
	 * 
	 * @param procardTem
	 * @param mentioningAwardPrice
	 * @return
	 */
	String packageProduct(ProcardTemplateJY procardTem,
			Double mentioningAwardPrice);

	/**
	 * @Title: trial
	 * @Description: 试算数据
	 * @param id
	 *            成品ID
	 * @return String 前台数据
	 * @throws
	 */
	String trial(Integer id, Float dayOutput);

	/**
	 * ajax 获取含有少许字符的全字符
	 */
	List<ProcardTemplateJY> getAllNames(String markId);



	/***
	 * 精益计算(计算总成下面所有件号的产能、送货量等精益计算相关数据)
	 * 
	 * @param id
	 *            rootID
	 * @return
	 */
	String jingyiJisuan(Integer id, ProcardTemplateJY procardTemplateJY);



	/**
	 * 查询所有的markId
	 * 
	 * @return
	 */
	public List<String> getAllMarkId();

	/**
	 * 将所有非总成的没有父对象的的模板都根据fatherId与父对象模板建立关系
	 * 
	 * @return
	 */
	public int updateFk();

	/***
	 *计算每的其他数据(产能比、送货量、生产时间)
	 * 
	 * @param id
	 * @param procardTem
	 * @return
	 */
	String jingyiJisuan2(Integer id, ProcardTemplateJY procardTem);
    /**
     * 通过id获取精益工序对象
     * @param id
     * @return
     */
	ProcessTemplateJY getProcessJYById(Integer id);
   /**
    * 修改精益工序
    * @param processTemplateJY
    * @param pageStatus
    * @return
    */
	boolean updateProcessJY(ProcessTemplateJY processTemplateJY,
			String pageStatus,String updateContext);
/**
 * 通过工序的id更改整个bom的进行状态
 * @param id
 * @param string
 * @return
 */
boolean setBomProgressStatusByprocessId(Integer id, String status);
/**
 * 通过rootId更改整个bom的进行状态
 * @param id
 * @param string
 * @return
 */
boolean setBomProgressStatusByRootId(Integer id, String status);
/**
 * 查看bom精益计算的结果是不是产能盈余全为正了
 * @param id
 */
boolean checkisOK(Integer id);
/**
 * 通过设备，工装，量具的部分编号或者名字查到设备，工装，量具
 * @param sbNO
 * @param sbName
 * @return
 */
List findSBGZLJList(String no, String name,String pageStatus);
/**
 * 修改工序的设备，工装，量具信息
 * @param sbNO
 * @param sbName
 * @param id
 * @return
 */
boolean updateProcessSBGZLJ(String no, String name, Integer id,String pageStatus);
/**
 * 修该工艺规范文件名
 * @param processTemplateJY
 * @return
 */
boolean updateFileName(ProcessTemplateJY processTemplateJY);
/**
 * 获取部门列表信息
 * @return
 */
List getDept();
/**
 * 通过部门信息获取其下人员
 * @param id
 * @return
 */
List getUsersByDeptId(Integer id,Integer processId);
/**
 * 修改工序的操作人员
 * @param id
 * @param processId
 * @return
 */
boolean updateOperator(Integer id, Integer processId);
/**
 * 将精益模板打回到流水卡模板
 * @param id
 * @return
 */
Map<Integer, Object> saveBacktoProcardTemplate(Integer id);
/**
 * 通过精益工序的id获取工艺规程id和工艺工序的id
 * @param id
 * @return
 */
Map<Integer,Integer> getGygcIdAndProcessDataId(String markId,Integer processNo);


}
