package com.task.Server;

import java.util.List;

import com.task.entity.Yusuantianbaobiao;
import com.task.entity.Yusuantianbaototal;

public interface YusuantianbaobiaoServer {
	/***
	 * 保存
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public boolean saveBaobiao(Yusuantianbaobiao yusuantianbaobiao,String statu);

	/***
	 * 分页查询
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public List getAllList(int pageNo, int pageSize, String bumen, String niandu);

	public List getAllList(int pageNo, int pageSize, int id);

	/***
	 * 获得总数
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public Integer getcount();
	
	/***
	 * 获得总数
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public Integer getcount1(Integer id);

	/***
	 * 查看
	 */
	public Yusuantianbaobiao findYusuantianbaobiao(int id);

	/***
	 * 删除
	 */
	public boolean deleteBaobiao(Yusuantianbaobiao yusuantianbaobiao);

	/***
	 * 保存总表
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public boolean saveZBaobiao(Yusuantianbaototal yusuantianbaototal);

	/***
	 * 分页查询总表
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public List getZAllList(int pageNo, int pageSize);

	public List addZAllList(int pageNo, int pageSize, String bumen);

	/***
	 * 查询总表数
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public Integer getZcount();

	/***
	 * 修改总表
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public boolean updateYusuantianbaototal(
			Yusuantianbaototal yusuantianbaototal);

	/***
	 * 删除总表
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public boolean deleteBaobiao(Yusuantianbaototal yusuantianbaototal);

	/***
	 * 通过Id查询总表
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public Yusuantianbaototal findYusuantianbaototal(int id);

	/***
	 * 打回修改后提交审批
	 */
	public void dahuixiugaishenpi(int epid);

	/***
	 * 修改支出
	 * 
	 * @param yusuantianbaobiao
	 * @return
	 */
	public boolean updatezcYusuantianbaobiao(double zhichu, int id, String yuefen);

	/***
	 * 根据部门 和 年份 查找明细
	 * 
	 * @return
	 */
	public List findMingxi();

	/***
	 * 审批修改
	 */
	public boolean shenpi(int id, int key);

	public Object[] findExamList(Integer cpage, Integer pageSize);

	/***
	 * 修改金额
	 */
	public boolean xiuGaiZongJine(int id);

	/***
	 * 修改剩余
	 */
	public boolean xiuGaiSengyu(float key, int id);

	public Yusuantianbaototal findYusuantianbaototalByZ(
			Yusuantianbaobiao yusuantianbaobiao);

	/***
	 * 修改
	 */
	boolean updateYusuantianbaobiao(Yusuantianbaobiao yusuantianbaobiao,
			String status);

	Integer getcount2(String bumen, String niandu);
}
