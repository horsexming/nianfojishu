package com.task.Server.gongyi.score;

import java.util.List;

import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.score.GongyiGuichengScore;
import com.task.entity.gongyi.score.GongyiGuichengScoreItem;
import com.task.entity.gongyi.score.GongyiGuichengScoreLeibie;
/**
 * 
 * @author 马凯
 *
 */
public interface GongyiGuichengScoreServer {
	/**
	 * 添加工艺规程伏分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String addGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore);
	/**
	 * 删除工艺规程分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String deleteGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore);
	/**
	 * 更新工艺规程分数记录
	 * @param gongyiGuichengScore
	 * @return
	 */
	public String updateGongyiGuichengScore(GongyiGuichengScore gongyiGuichengScore);
	/**
	 * 获得工艺规程打分记录记录 根据工艺规程ID  模块  工序数据ID
	 * @param id
	 * @return
	 */
	public GongyiGuichengScore getGongyiGuichengScoreByGongyiGuichengIdAndModelAndProcessDataId(Integer gongyiGuichengId,String model,Integer processDataId);
	
	/**********************************工艺规程打分类别管理**********************************************/
	/**
	 * 添加打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie addGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie GongyiGuichengScoreLeibie);

	/**
	 * 删除打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie deleteGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie GongyiGuichengScoreLeibie);

	/**
	 * 更新打分类别
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreLeibie updateGongyiGuichengScoreLeibie(GongyiGuichengScoreLeibie GongyiGuichengScoreLeibie);
	
	/**
	 * 获得工艺规程记录
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreLeibieById(Integer id) ;
	
	/**
	 * 获得工艺规程记录 根据标识
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreLeibie getGongyiGuichengScoreLeibieByBiaoshi(String biaoshi);
	
	/**
	 * 获得打分类别集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] getGongyiGuichengScoreLeibieListAll(GongyiGuichengScoreLeibie GongyiGuichengScoreLeibie, int pageNo, int pageSize);
	/**
	 * 获得打分类别集合For select
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreLeibieListAllForSelect(GongyiGuichengScoreLeibie gongyiGuichengScoreLeibie);
	/**
	 * 获得打分类别下检查条目
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreLeibieListByParentId(Integer parentId);
	/**
	 * 获得打分类别下打分记录
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreJiluListByParentId(Integer parentId);
	
	/**
	 * 获得打分选项
	 * @param 
	 * @return
	 */
	public List getGongyiGuichengScoreXuanxiangListByParentId(Integer parentId);
	
	/*****************************************工艺规程打分***************************************/
	/**
	 * 添加打分
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem addGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem);
	/**
	 * 删除打分
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem deleteGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem);

	/**
	 * 更新打分
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuichengScoreItem updateGongyiGuichengScoreItem(GongyiGuichengScoreItem gongyiGuichengScoreItem);
	
	/**
	 * 获得打分
	 * @param id
	 * @return
	 */
	public GongyiGuichengScoreItem getGongyiGuichengScoreItemById(Integer id);
	
	/**
	 * 获得打分类别集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(Integer gongyiGuichengId, Integer processDataId);
	/**
	 * 获得打分集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(GongyiGuichengScoreItem gongyiGuichengScoreItem);
	/**
	 * 获得打分唯一记录 根据 工艺规程ID 工序ID Model
	 * @param gongyiGuichengScoreItem
	 * @return
	 */
	public List getGongyiGuichengScoreItemUniqueListByGongyiGuichengIdAndProcessDataIdAndModel(GongyiGuichengScoreItem gongyiGuichengScoreItem);
	/**
	 * 获得打分记录集合 根据 工艺规程ID 工序ID Model
	 * @param gongyiGuichengScoreItem
	 * @return
	 */
	public List getGongyiGuichengScoreItemListByProcessDataIdAndModel(GongyiGuichengScoreItem gongyiGuichengScoreItem);
	
	
	/**
	 * 获得工序数据记录
	 * @param id
	 * @return
	 */
	public ProcessData getProcessDataById(Integer id);
}
