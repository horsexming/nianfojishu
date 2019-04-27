package com.task.ServerImpl;

import java.util.List;
import java.util.Set;

import com.task.Dao.TotalDao;
import com.task.Server.QualityccServer;
import com.task.entity.Qexamine;
import com.task.entity.Qualitycheckta;
import com.task.entity.Qualitycheckto;
import com.task.entity.Qualityta;
import com.task.entity.Qualityto;
import com.task.util.Util;

public class QualityccServerImpl implements QualityccServer {
	private TotalDao totalDao;

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	// 创建检验产品类型
	@Override
	public boolean addQualitycheckto(Qualitycheckto qualitycheckto) {
		qualitycheckto.setAddtime(Util.getDateTime("yyyyMMddHHmmss"));
		boolean b = totalDao.save(qualitycheckto);
		return b;
	}

	// 分页查询检验产品类型
	@Override
	public List findQualitychecktolist(int pageNo, int pageSize) {
		String hql = "from Qualitycheckto order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public Integer qualitychecktoCount() {
		return this.totalDao.getCount("from Qualitycheckto");
	}

	// 删除检验产品类型
	@Override
	public boolean deleteQualitycheckto(Qualitycheckto qualitycheckto) {
		qualitycheckto = this.findQualitychecktoById(qualitycheckto.getId());
		boolean b = totalDao.delete(qualitycheckto);
		return b;
	}

	public Qualitycheckto findQualitychecktoById(int id) {
		Qualitycheckto qualitycheckto = (Qualitycheckto) totalDao
				.getObjectById(Qualitycheckto.class, id);

		return qualitycheckto;
	}
	/***
	 * 通过模板标号，查找模板
	 * @param id
	 * @return
	 */
	public Qualitycheckto findQualitychecktoByAddtime(String addtime){
		String hql = "from Qualitycheckto where addtime =? ";
		List list  = totalDao.query(hql, addtime);
		Qualitycheckto qualitycheckto =  (Qualitycheckto)list.get(0);
		return qualitycheckto;
	}

	/***
	 * 根据产品id查询审核明细
	 * 
	 * @param toId
	 *            产品id
	 * @return
	 */
	@Override
	public List findQualitychecktas(Integer toId) {
		String hql = "from Qualitycheckta where qualitycheckto.id=? order by id desc";
		return totalDao.query(hql, toId);
	}
	public List findQualitytas(Integer toId){
		String hql = "from Qualityta where qualityto.id=? order by id desc";
		return totalDao.query(hql, toId);
	}

	/***
	 * 添加检验产品项目
	 */
	@Override
	public boolean addQualitycheckta(Qualitycheckta qualitycheckta) {
		Qualitycheckto qualitycheckto = qualitycheckta.getQualitycheckto();
		qualitycheckta.setQualitycheckto(qualitycheckto);
		qualitycheckta.setAddtime(Util.getDateTime("yyyyMMddHHmmss"));
		boolean b = totalDao.save(qualitycheckta);
		return b;
	}

	/***
	 *根据id查询质检项目
	 */
	public Qualitycheckta findQualitychecktaById(int id) {
		Qualitycheckta qualitycheckta = (Qualitycheckta) totalDao
				.getObjectById(Qualitycheckta.class, id);
		return qualitycheckta;
	}

	/***
	 * 删除质检项目
	 */
	@Override
	public boolean deleteQualitycheckta(Qualitycheckta qualitycheckta) {
		qualitycheckta = this.findQualitychecktaById(qualitycheckta.getId());
		boolean b = totalDao.delete(qualitycheckta);
		return b;
	}

	/***
	 * 查询检验产品类型
	 * 
	 * @return
	 */
	@Override
	public List findQualitycheckto() {
		String hql = "from Qualitycheckto order by id desc";
		return totalDao.query(hql);
	}

	/***
	 * 保存质量检查表
	 */
	@Override
	public boolean addQualityto(Qualityto qualityto,
			Qualitycheckto qualitycheckto) {
		qualityto.setAddtime(Util.getDateTime("yyyy.MM.dd"));
		qualityto.setYbshu(5);
		qualityto.setStatu("已添样本");
		qualityto.setQualitychecktoid(qualitycheckto.getId());
		qualityto.setKehu(qualitycheckto.getKehu());
		qualityto.setJcbh(qualitycheckto.getAddtime());
		qualityto.setProductname(qualitycheckto.getProductname());
		qualityto.setLeibie(qualitycheckto.getLeibie());
		qualityto.setBianhao(qualitycheckto.getLeibie());
		qualityto.setYybz(qualitycheckto.getYybz());
		qualityto.setPici(qualitycheckto.getPici());
		totalDao.save(qualityto);
		return true;
	}

	/***
	 * 分页查询质量检查表
	 */
	@Override
	public List findQualityByPage(int pageNo, int pageSize) {
		String hql = "from Qualityto order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	@Override
	public Integer qualitytoCount() {
		String hql = "from Qualityto";
		int a = this.totalDao.getCount(hql);
		return a;
	}

	/***
	 * 查询质量表
	 */
	@Override
	public List findQualityAll() {
		String hql = "from Qualityto";
		List list = totalDao.query(hql);
		return list;
	}

	/***
	 * 通过id查询质量表
	 */
	@Override
	public Qualityto findQualitytoById(int id) {
		Qualityto qualityto = (Qualityto) totalDao.getObjectById(
				Qualityto.class, id);
		return qualityto;
	}

	/***
	 * 删除质量检查表
	 */
	@Override
	public boolean deleteQualityto(Qualityto qualityto) {
		qualityto = this.findQualitytoById(qualityto.getId());
		boolean b = totalDao.delete(qualityto);
		return b;
	}

	/***
	 * 保存质检信息
	 */
	@Override
	public boolean addQualityta(Qualityta qualityta,
			Qualitycheckta qualitycheckta) {
		qualityta.setAddtime(Util.getDateTime("yyyyMMddHHmmss"));
		qualityta.setKehu(qualitycheckta.getKehu());
		qualityta.setJcbh(qualitycheckta.getQualitycheckto().getAddtime());
		qualityta.setProductname(qualitycheckta.getProductname());
		qualityta.setLeibie(qualitycheckta.getLeibie());
		qualityta.setProject(qualitycheckta.getAdditem());
		qualityta.setBiaozhun(qualitycheckta.getBiaozhun());
		qualityta.setFangshi(qualitycheckta.getFangshi());
		qualityta.setXishul(qualitycheckta.getXishul());
		qualityta.setXishuz(qualitycheckta.getXishuz());
		boolean b = totalDao.save(qualityta);
		return b;
	}
	/***
	 * 修改模板
	 */
	public boolean updateQualitycheckto(Qualitycheckto qualitycheckto){
		totalDao.update(qualitycheckto);
		return true;
	}
	/***
	 * 修改质量检查表
	 */
	public boolean updateQualityto(Qualityto qualityto){
		totalDao.update(qualityto);
		return true;
	}
}
