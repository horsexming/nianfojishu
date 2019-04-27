package com.task.Server.menjin;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.menjin.DepositCabinet;
import com.task.entity.menjin.DrinksType;
import com.task.entity.menjin.ReceiveCabinet;
import com.task.entity.menjin.ToolCabine;
import com.task.entity.onemark.OneLight;

public interface ToolCabineServer {
	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String addToolCabine(ToolCabine toolCabine);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateToolCabine(ToolCabine toolCabine);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteToolCabine(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public ToolCabine byIdToolCabine(Integer id);// 根据id得到对象

	/**
	 * 分页查询 柜子（工具柜/物品柜）
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findToolCabine(ToolCabine toolCabine,
			int pageNo, int pageSize, String tag);// 分页查询对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findDepositCabinet(
			DepositCabinet depositCabinet, int pageNo, int pageSize, String tag);// 分页查询对象

	public Map<Integer, Object> findReceiveCabinet(
			ReceiveCabinet receiveCabinet, int parseInt, int pageSize);

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public DepositCabinet byIdDepositCabinet(Integer id);// 根据id得到对象

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public ReceiveCabinet byIdReceiveCabinet(Integer id);// 根据id得到对象

	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String addReceiveCabinet(DepositCabinet cabinet,
			ReceiveCabinet receiveCabinet);// 添加

	public String addErWeiMa(DepositCabinet cabinet);// 添加
	
	/**
	 * 修改饮品积分
	 * @param drinksType
	 * @return
	 */
	public String updateDrinksType(DrinksType drinksType);
	
	/**
	 * 分页查询饮品
	 * @param DrinksType
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findDrinksType(
			DrinksType drinksType, int parseInt, int pageSize);
	
	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public DrinksType byIdDrinksType(Integer id);// 根据id得到对象

	/**
	 * 查询该柜子所有已绑定的人
	 * 
	 * @param id
	 *            柜子id
	 * @return
	 */
	public List<Users> findAllBangUsers(Integer id);

	/**
	 * 
	 * @param ace
	 *            设备id
	 * @param taUId
	 *            选中要准备绑定的id数组
	 * @return
	 */
	public String binDingUsers(Integer toCab, Integer[] usersId);

	/**
	 * 查询该所有未绑定柜子的用户 分页查询
	 * 
	 * @param Users
	 *            灯对象
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            条数
	 * @return
	 */
	public Object[] findAllUsers(Users users, int parseInt,
			int pageSize, Integer toCab);
	
	public List findCkTool();
	
	public List findkdgTool(String an);
	/**
	 * 查询登录人的保定柜子信息
	 * @return
	 */
	public List<ToolCabine> findpeople();
	/**
	 * 修改密码
	 * @return
	 */
	public String updatemima(Integer id, String oldpassword, String newpassword);
}
