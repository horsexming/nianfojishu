package com.task.Server.menjin;

import java.util.List;
import java.util.Map;

import com.task.entity.Users;
import com.task.entity.menjin.FingerprintMg;

/**
 * 
 * @author LiCong 2016-10-19
 *
 */

@SuppressWarnings("unchecked")
public interface FingerprintMgServer {
	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @param fingerprintMg
	 *            指纹表
	 * @param accessEquipment
	 *            考勤机id集合
	 * @return
	 */
	public String addFingerprintMg(FingerprintMg fingerprintMg, Integer[] accessEquipment);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateFingerprintMg(FingerprintMg fingerprintMg);// 修改

	/**
	 * 修改
	 * 
	 * @author duminglong
	 * @return
	 */
	public String updateIdentification(String Identification);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteFingerprintMg(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public FingerprintMg byIdFingerprintMg(Integer id);// 根据id得到对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findFingerprintMg(FingerprintMg fingerprintMg, int pageNo, int pageSize);// 分页查询对象

	/**
	 * 根据用户ID查询指纹信息
	 */
	public List byUserIdFing(Integer id);

	/**
	 * 根据指纹ID查询已绑定的设备信息
	 */
	public List byUserIdAcE(Integer id);

	/**
	 * 根据指纹ID查询所有未绑定的设备信息
	 */
	public List byUserIdAllAcE(Integer id);

	public List byUserIdAgrennAcE(Integer id);

	/**
	 * 指纹下发考勤机
	 * 
	 * @param aceId
	 *            设备ID集合
	 * @param fingerprintMg
	 *            指纹
	 */
	public String doorbangdingFings(Integer aceId[], FingerprintMg fingerprintMg);

	public Users byIdUserId(Integer id);

	/**
	 * 根据添加时间查询对象
	 * 
	 * @param addtime
	 * @return
	 */
	public FingerprintMg seltime(String addtime);// 查询
}
