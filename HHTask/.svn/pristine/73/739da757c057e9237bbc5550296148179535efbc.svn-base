package com.task.Server.menjin;

import java.util.Map;

import com.task.entity.menjin.InEmployeeCarInfor;
import com.tast.entity.zhaobiao.ZhUser;

public interface InEmployeeCarInforServer {
	/**
	 * 添加车牌记录
	 * 
	 * @return
	 */
	public String addInEmployeeCarInfor(InEmployeeCarInfor InEmployeeCarInfor);

	/**
	 * 修改车牌记录
	 * 
	 * @return
	 */
	public String updateInEmployeeCarInfor(InEmployeeCarInfor InEmployeeCarInfor);

	/**
	 * 删除车牌记录
	 * 
	 * @return
	 */
	public boolean deleteInEmployeeCarInfor(
			InEmployeeCarInfor InEmployeeCarInfor);

	/**
	 * 分页查询车牌记录
	 * 
	 * @param InEmployeeCarInfor
	 * @param pageNo
	 * @param pageSize
	 * @param tag
	 *            标识常访/内部
	 * @param all
	 *            标识所有/部门
	 * @return
	 */
	public Map<Integer, Object> findInEmployeeCarInforByCondition(
			InEmployeeCarInfor InEmployeeCarInfor, int pageNo, int pageSize,
			String tag,
			String all);

	/**
	 * 根据id获得InEmployeeCarInfor对象
	 */
	public InEmployeeCarInfor getByIdInEmployeeCarInfor(Integer integer);
	/**
	 * 再次申请
	 */
	public String agreen(InEmployeeCarInfor inEmployeeCarInfor);
	/**
	 * 常访车辆导出
	 */
	public void exportExcel(InEmployeeCarInfor inEmployeeCarInfor);
	/**
	 * 内部车辆导出
	 */
	public void exportExcel_1(InEmployeeCarInfor inEmployeeCarInfor);
}
