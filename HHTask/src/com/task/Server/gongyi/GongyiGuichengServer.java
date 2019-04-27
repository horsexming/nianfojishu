package com.task.Server.gongyi;

import java.util.List;
import java.util.Map;

import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.vo.GongyiGuichengVo;
/**
 * 
 * @author 马凯
 *
 */
public interface GongyiGuichengServer {
	/**
	 * 添加工艺规程记录
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuicheng addGongyiGuicheng(GongyiGuicheng gongyiGuicheng);
	/**
	 * 删除工艺规程记录
	 * @param gongyiGuicheng
	 * @return
	 */
	public boolean deleteGongyiGuicheng(GongyiGuicheng gongyiGuicheng);
	/**
	 * 更新工艺规程记录
	 * @param gongyiGuicheng
	 * @return
	 */
	public GongyiGuicheng updateGongyiGuicheng(GongyiGuicheng gongyiGuicheng);
	/**
	 * 获得工艺规程记录
	 * @param id
	 * @return
	 */
	public GongyiGuicheng getGongyiGuichengById(Integer id);
	/**
	 * 获得工艺规程记录集合  根据件号
	 * @param jianhao
	 * @return
	 */
	public List getGongyiGuichengListByJianNumb(String jianNumb);
	/**
	 * 获得工艺规程记录集合
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuicheng(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	
	/**
	 * 获得工艺规程记录集合 待提交
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//public List findAllGongyiGuichengForDtj(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合  已提交
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//public Object[] findAllGongyiGuichengForYtj(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	
	/**
	 * 获得工艺规程记录集合 待审批
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//public List findAllGongyiGuichengForDsp(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合 已审批
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	//public Object[] findAllGongyiGuichengForYsp(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	
	/**
	 * 获得工艺规程记录集合 待编制
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDbz(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合 已编制
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYbz(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	/**
	 * 获得工艺规程记录集合 待校对
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDjd(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合 已校对
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYjd(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	/**
	 * 获得工艺规程记录集合 待审核
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDsh(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合 已审核
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYsh(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	/**
	 * 获得工艺规程记录集合 待批准
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAllGongyiGuichengForDpz(GongyiGuicheng gongyiGuicheng);
	
	/**
	 * 获得工艺规程记录集合 已批准
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForYpz(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	
	/**
	 * 获得所有的工艺规程记录
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForAll(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	/**
	 * 获得所有的工艺规程记录
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @param sql
	 * @return
	 */
	public Object[] findAllGongyiGuichengForAll(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize,String sql );
	
	/**
	 * 获得所有的工艺规程记录 for 看板
	 * @param gongyiGuicheng
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllGongyiGuichengForKanban(GongyiGuicheng gongyiGuicheng,int pageNo,int pageSize );
	/**
	 * 获得工艺流水卡片件号记录集合
	 * @return
	 */
	public List findJianNumbForSelect();
	
	/*******************************************历史版本********************************************************/
	/**
	 * 查看历史版本
	 */
	public List getGongyiGuichengListForHistory(GongyiGuicheng gongyiGuicheng);
	
	
	/**
	 * 查询出所有在干的件号
	 */
	public List<Map> getJianNumbForZaigan(Integer screenId);
	/**
	 * 通过报价bom的rootId将整棵bom树打散生成工艺编制系统的bom
	 * @param rootId
	 * @return
	 */
	public boolean addCraftBom(Integer rootId);
	/**
	 * 查询所有的件号
	 * @return
	 */
	public List<String> findJianNumbsAll();
	/**
	 * 根据markId获取
	 * @param markId
	 * @return
	 */
	public Map<Integer,Object> getGongyiGuichengByJianNumb(String MakrId,Integer rootId);
	/**
	 * 根据工艺控制Id获取报价Bom的rootId
	 * @param id
	 * @return
	 */
	public Integer getBomRootIdById(Integer id);
	/**
	 *通过报价系统的rootId将工艺控制bom送入sop或者lp中
	 */
	public Map<Integer, Object> createSopOrLpBomByRootId(Integer rootId,String productStyle);
	/**
	 * 检查是否可以作为组装bom的总成零件
	 * @param id
	 * @return
	 */
	public Map<Integer, Object> checkBuildById(Integer id);
	/**
	 * 将自有组装的BOM推送进sop或lp
	 * @param ggVoList
	 * @param productStyle
	 * @return
	 */
	public Map<Integer, Object> buildBomtoProcard(
			List<GongyiGuichengVo> ggVoList, String productStyle);
	/**
	 * 查询所有类型为总成的工艺零件
	 * @param gongyiGuicheng
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findGygcToSopOrLp(GongyiGuicheng gongyiGuicheng,
			int parseInt, int pageSize);
	/**
	 * 通过报价bom的rootId来查看工艺bom
	 * @param id
	 * @return
	 */
	public List findBomForReview(Integer id);
	
}
