package com.task.Server.dagnan;

import java.util.List;
import java.util.Map;

import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;

@SuppressWarnings("unchecked")
public interface DanganServer {
	/**
	 * @param  ArchiveUnarchiverAplt 存取档案信息
	 * 添加
	 * @return
	 */
	public String addDangAn(DangAn dangan, List<ArchiveUnarchiverAplt> aplt);
	/**
	 * 修改
	 * @return
	 */
	public String updateDangAn(DangAn dangan);
	/**
	 * 删除
	 * @return
	 */
	public String deleteDangAn(Integer id);
	/**
	 * 分页查询
	 * @return
	 */
	public Map<Integer, Object> findDangAnByCondition(DangAn dangan, int pageNo, int pageSize, String tag);
	/**
	 * 查询未使用的archiveUnarchiverAplt
	 */
	public List byIdArchiveUnarchiverAplt();
	/**
	 * 根据id查询对象
	 */
	public DangAn byIdDangan(Integer id);
	/**
	 * 根据id查询存取档明细对象 ArchiveUnarchiverAplt
	 */
	public List byIdFindDangan(Integer id);
	/**
	 * 查询价格表编号
	 */
	public String findPriceNum();
	/**
	 * 查询设备表名称
	 */
	public String findcaeNameNum();
	/**
	 * 查询设备表编号
	 */
	public List findaceNum(String name);
	public ArchiveUnarchiverAplt findaceNum_1();
	/**
	 * 添加财务档案信息
	 * @param dangan
	 * @param aplt
	 * @return
	 */
	String addCwDangAn(DangAn dangan, List<ArchiveUnarchiverAplt> aplt);
}
