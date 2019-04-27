package com.task.Server.rfid;

import com.task.entity.rfid.Rfid;

/**
 * 
 * @author LiCong 2016-10-19
 *
 */

public interface RfidServer {
	/**
	 * 添加
	 * @author Li_Cong
	 * @param rfid 
	 * @return
	 */
	public String addRfid(Rfid rfid);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateRfid(Rfid fingerprintMg);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteRfid(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Rfid byIdRfid(Integer id);// 根据id得到对象
	
	public void sendRfid();//开始读取

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Object[] findRfid(Rfid rfid, int pageNo, int pageSize);// 分页查询对象
}
