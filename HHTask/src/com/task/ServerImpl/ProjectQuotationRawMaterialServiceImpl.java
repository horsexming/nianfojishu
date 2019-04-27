package com.task.ServerImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.entity.ProjectQuotation;
import com.task.entity.ProjectQuotationRawMaterial;

public class ProjectQuotationRawMaterialServiceImpl implements com.task.Server.ProjectQuotationRawMaterialService {
	private TotalDao totalDao;

	@Override
	public void add(ProjectQuotationRawMaterial viewObj, int quotationId) {
		ProjectQuotation pq = (ProjectQuotation) totalDao.getObjectById(ProjectQuotation.class, quotationId);
		addOtherAttr(viewObj);
		viewObj.setRoot(pq);
		totalDao.save(viewObj);
	}

	@Override
	public void delete(ProjectQuotationRawMaterial viewObj) {
		ProjectQuotationRawMaterial rm = (ProjectQuotationRawMaterial) totalDao.getObjectById(viewObj.getClass(), viewObj.getId());
		rm.setRoot(null);
		totalDao.update(rm);
		totalDao.delete(rm);
	}

	@Override
	public Object[] getByQuotation(int quotationId, int pageNo, int pageSize) {
		String hql = "from ProjectQuotationRawMaterial p where p.root.id = ?";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, quotationId);
		int count = totalDao.getCount(hql,quotationId);
		Object[] o = { list, count };
		return o;
	}
	/**
	 * 计算其余的属性
	 * @param p
	 */
	private void addOtherAttr(ProjectQuotationRawMaterial p) {
		double grossWeight = getRMGrossWeight(p);
		double usage = getRMUsage(p.getNetWeight(), grossWeight);
		double materialCost = getRMMaterialCost(grossWeight, p.getPrice());
		double scrapValue = getRMScrapValue(grossWeight, p.getNetWeight(), p.getScrapPrice());
		double totalCost = getRMTotalCost(materialCost, scrapValue);
		p.setGrossWeight(grossWeight);
		p.setUsage(usage);
		p.setMaterialCost(materialCost);
		p.setScrapValue(scrapValue);
		p.setTotalCost(totalCost);
	}
	
	/**
	 * 计算定额
	 * @param p 要计算的对象
	 * @return 未处理的double类型
	 */
	private double getRMGrossWeight(ProjectQuotationRawMaterial p){
		double d = p.getUnitLength() * p.getUnitWidth() * p.getDensity() * p.getThickness() * (p.getRmType().equals("板料")? 1: 3.14) * (1 + p.getCoilScrap()) * (1 + p.getNonQuality()) / 1000000 ;
		return d;
	}
	
	/**
	 * 计算材料利用率
	 * @param f 
	 * @param netWeight 零件净重
	 * @param grossWeight 计算定额
	 * @return 
	 */
	private double getRMUsage(double netWeight, double grossWeight){
		double d = netWeight / grossWeight;
		return d;
	}

	/**
	 *  计算原材料成本
	 * @param grossWeight 计算定额
	 * @param price 原材料单价
	 * @return
	 */
	private double getRMMaterialCost(double grossWeight, double price){
		double d = grossWeight * price;
		return d;//四舍五入,保留三位小数
	}
	
	/**
	 * 计算回收价值
	 * @param grossWeight 计算定额
	 * @param netWeight 零件净重
	 * @param scrapPrice 废料单价
	 * @return
	 */
	private double getRMScrapValue(double grossWeight, double netWeight, double scrapPrice){
		return (grossWeight - netWeight) * scrapPrice;
	}

	/**
	 * 实际成本
	 * @param materialCost 原材料成本
	 * @param scrapValue 回收价值
	 * @return
	 */
	private double getRMTotalCost(double materialCost, double scrapValue){
		return materialCost - scrapValue;
	}
	@Override
	public void update(ProjectQuotationRawMaterial viewObj) {
		ProjectQuotationRawMaterial p = (ProjectQuotationRawMaterial) totalDao.getObjectById(ProjectQuotationRawMaterial.class, viewObj.getId());
		BeanUtils.copyProperties(viewObj, p, new String[]{"id", "root"});
		addOtherAttr(p);
		totalDao.update(p);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public ProjectQuotationRawMaterial get(ProjectQuotationRawMaterial rawMaterial) {
		ProjectQuotationRawMaterial p = (ProjectQuotationRawMaterial) totalDao.getObjectById(ProjectQuotationRawMaterial.class, rawMaterial.getId());
		return p;
	}

}
