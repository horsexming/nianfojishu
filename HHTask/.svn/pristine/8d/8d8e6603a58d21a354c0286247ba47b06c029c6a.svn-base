package com.task.Server;

import java.util.List;

import com.task.entity.Qualitycheckta;
import com.task.entity.Qualitycheckto;
import com.task.entity.Qualityta;
import com.task.entity.Qualityto;

public interface QualityccServer {
	// 创建检验产品类型
	public boolean addQualitycheckto(Qualitycheckto qualitycheckto);

	// 分页查询检验产品类型
	public List findQualitychecktolist(int pageNo, int pageSize);
	public Integer qualitychecktoCount();
	/***
	 * 查询检验产品类型
	 * @return
	 */
	public List findQualitycheckto();

	// 删除检验产品
	public boolean deleteQualitycheckto(Qualitycheckto qualitycheckto);
	/***
	 * 通过模板标号，查找模板
	 * @param id
	 * @return
	 */
	public Qualitycheckto findQualitychecktoByAddtime(String addtime);
	// 通过ID 查询检验产品类型
	public Qualitycheckto findQualitychecktoById(int id);
	/***
	 * 添加检验产品项目
	 * 
	 * @param qualitycheckta
	 *            添加产品明细
	 * @return
	 */
	public boolean addQualitycheckta(Qualitycheckta qualitycheckta);
	/***
	 * 根据产品id查询审核明细
	 * 
	 * @param toId
	 *            产品id
	 * @return
	 */
	public List findQualitychecktas(Integer toId);
	public List findQualitytas(Integer toId);
	/***
	 *根据id查询质检项目
	 */
	public Qualitycheckta findQualitychecktaById(int id);
	/***
	 * 删除质检项目
	 */
	public boolean deleteQualitycheckta(Qualitycheckta qualitycheckta);
	/***
	 *保存质量检查表
	 * @param qualityto
	 * @return
	 */
	public boolean addQualityto(Qualityto qualityto,Qualitycheckto qualitycheckto);
	/***
	 * 修改模板
	 */
	public boolean updateQualitycheckto(Qualitycheckto qualitycheckto);
	/***
	 * 修改质量检查表
	 */
	public boolean updateQualityto(Qualityto qualityto);
	/***
	 * 分页查询质量检查表
	 */
	public List findQualityByPage(int pageNo, int pageSize);
	public Integer qualitytoCount();
	/***
	 * 查询质量表
	 */
	public List findQualityAll();
	/***
	 * 通过id查询质量表
	 */
	public Qualityto findQualitytoById(int id);
	/***
	 * 删除质量检查表
	 */
	public boolean deleteQualityto(Qualityto qualityto);
	/***
	 * 保存质检信息
	 */
	public boolean addQualityta(Qualityta qualityta,Qualitycheckta qualitycheckta);

}
