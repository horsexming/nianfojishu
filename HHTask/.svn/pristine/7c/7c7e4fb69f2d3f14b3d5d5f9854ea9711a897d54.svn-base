package com.task.Server.onemark;

import java.util.List;
import java.util.Map;

import com.task.entity.onemark.OneLight;

public interface OneLightServer {
	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String addOneLight(OneLight light);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateOneLight(OneLight light);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteOneLight(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public OneLight byIdOneLight(Integer id);// 根据id得到对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findOneLight(OneLight light, int pageNo,
			int pageSize);// 分页查询对象

	/**
	 * 修改(打开关闭)
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateOCLight(OneLight light, Integer integer);// 修改

	/**
	 * 根据一体机标识 查绑定的灯
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findGongweiLight(String num);
	
	/**
	 * 根据users ID 查可绑定的灯
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findUserIdLight(Integer id);

}
