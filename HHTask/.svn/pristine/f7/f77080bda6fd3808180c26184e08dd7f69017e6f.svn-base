package com.task.Server.android.processpush;

import java.util.List;
import java.util.Map;

import com.task.entity.TaSopGongwei;
import com.task.entity.android.processpush.OneMachine;
import com.task.entity.onemark.OneLight;

/**
 * 接口
 * 
 * @author Li_Cong
 * 
 */
public interface OneMachineServer {
	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @param machine
	 * @return
	 */
	public String addOneMachine(OneMachine machine);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @param machine
	 * @return
	 */
	public String updateOneMachine(OneMachine machine);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @param machine
	 * @return
	 */
	public String deleteOneMachine(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @param machine
	 * @return
	 */
	public OneMachine byIdOneMachine(Integer id);// 根据id得到对象

	/**
	 * 分页查询 一体机
	 * 
	 * @author Li_Cong
	 * @param machine
	 * @return
	 */
	public Map<Integer, Object> findOneMachine(OneMachine machine, int pageNo,
			int pageSize);// 分页查询对象

	/**
	 * 得到当前一体机数量
	 * @return
	 */
	public Integer oneMachineCount();
	
	/**
	 * 根据一体机Id查询条件工位
	 * 
	 * @param taSopGongwei
	 * @param parseInt
	 * @param pageSize
	 * @param id
	 * @return
	 */
	public Object[] findMaIdByCondition(TaSopGongwei taSopGongwei,
			int parseInt, int pageSize, Integer id);

	/**
	 * 查询该一体机所有已绑定的工位
	 * 
	 * @param id
	 *            一体机id
	 * @return
	 */
	public List<TaSopGongwei> findAllBangGw(Integer id);

	/**
	 * 
	 * @param machine
	 *            一体机对象
	 * @param taGwId
	 *            选中要准备绑定的id数组
	 * @return
	 */
	public String binDingMachine(OneMachine machine, Integer[] taGwId);

	/**
	 * 查询该一体机所有未绑定的工位 分页查询
	 * 
	 * @param id
	 *            一体机id
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            条数
	 * @return
	 */
	public Object[] findAllTagw(Integer id, int parseInt, int pageSize);

	/*********************************************/
	/***
	 * 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号 根据工位查找所有待领工序信息
	 * 
	 * @param gongwei
	 *            员工卡卡号
	 * @return
	 */
	public List findProcardListByGwCard(String gongwei);

	/***
	 * 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号 根据工位查找所有待领工序信息
	 * 
	 * @param gongwei
	 *            员工卡卡号
	 * @return
	 */
	public List findProcardListByGwAndCard(String cardNumber);

	public OneMachine byIp(String ip);

	/**
	 * 判断工位是否存在
	 * 
	 * @param id
	 * @return
	 */
	List findPushgongweiById(Integer id);

	/**
	 * 根据IP获得图纸
	 * 
	 * @param ip
	 * @return
	 */
	public List tuzhiLj(String ip);

	/**
	 * 根据ip获得绑定工位
	 * 
	 * @param ip
	 * @return
	 */
	public String gongw(String ip);

	public String name();

	/**
	 * 根据工位号获得工序，产品，图纸链接
	 * 
	 * @param gongwei
	 * @param gongwei
	 * @return
	 */
	public List<Map<String, Object>> findgognxuInfor(String gongwei, String tag);

	public List findquexiantuzhi(String gongwei);

	/*********************** 绑定灯 ***********************/
	/**
	 * 查询该一体机所有已绑定的灯
	 * 
	 * @param id
	 *            一体机id
	 * @return
	 */
	public List<OneLight> findAllBangLight(Integer id);

	/**
	 * 
	 * @param onemach
	 *            一体机id
	 * @param taOLId
	 *            选中要准备绑定的id数组
	 * @return
	 */
	public String binDingOneLight(Integer onemach, Integer[] taOLId);

	/**
	 * 查询该所有未绑定一体机的灯 分页查询
	 * 
	 * @param oneLight
	 *            灯对象
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            条数
	 * @return
	 */
	public Object[] findAllOneLight(OneLight oneLight, int parseInt,
			int pageSize);

	/***
	 * 查询待检码
	 * 
	 * @return
	 */
	List findDjm();

}
