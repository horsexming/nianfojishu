package com.task.Server.menjin;

import java.util.List;
import java.util.Map;

import com.task.entity.menjin.WarehouseApplication;
import com.task.entity.menjin.WarehouseCertificate;


public interface WarehouseApplicationServer {
	/**
	 * 添加
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String addWarehouseApplication(WarehouseApplication ware,List<WarehouseCertificate> wac);// 添加

	/**
	 * 修改
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String updateWarehouseApplication(WarehouseApplication warehouseApplication);// 修改

	/**
	 * 删除
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public String deleteWarehouseApplication(Integer id);// 删除

	/**
	 * 根据id查询对象
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public WarehouseApplication byIdWarehouseApplication(Integer id);// 根据id得到对象
	public WarehouseApplication byIdWarehouseApplication(Integer id,Integer userId);// 根据id和userId得到对象
	public WarehouseCertificate byIdWarehouseC(Integer id);// 根据id得到对象
	public WarehouseCertificate byIdWarehouseC(Integer id,Integer userId);// 根据id和userId得到对象

	/**
	 * 分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findWarehouseApplication(WarehouseApplication warehouseApplication,
			int pageNo, int pageSize);// 分页查询对象
	
	
	/**
	 * 申请记录Android端分页查询
	 * 
	 * @author Li_Cong
	 * @return
	 */
	public Map<Integer, Object> findWarehouseAppAndroid(WarehouseApplication warehouseApplication,
			int pageNo, int pageSize, Integer userId);// 分页查询对象
	
	/**
	 * 根据申请ID查询可开库位
	 * @param id
	 * @return
	 */
	public List<WarehouseCertificate> findWareCert(Integer id);
	
	/**
	 * 开关柜子
	 * @return
	 */
	public String openandClose(WarehouseCertificate wac);
	public String oaCloseA(WarehouseCertificate wac);
	
	/**
	 * 确认已存入
	 * @return
	 */
	public String queren(WarehouseCertificate wac);
	/**
	 * 确认已存入
	 * @param wac 开门凭证记录
	 * @param i 确认存入的数量
	 * @return
	 */
	public String querenAnd(WarehouseCertificate wac,Integer i);
	
	/**
	 * 闪烁
	 * @param wac
	 */
	public void shansuo(WarehouseCertificate wac);
	public String shansuoAnd(WarehouseCertificate wac);
	/**
	 * 发送二维码
	 * @param wac
	 * @return
	 */
	public String sendTow(WarehouseCertificate wac);
//	public void huanyuanProcard();
}
