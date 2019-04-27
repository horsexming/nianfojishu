package com.task.Server.bp;

import java.util.List;
import java.util.Map;

import com.task.entity.bp.Product;
import com.task.entity.bp.Templet;

public interface ProductService {

	/**
	 * 根据计划单编号拿到配件和组件，请继续调用saveProduct来保存到数据库
	 * 
	 * @param detailNumber
	 *            计划单编号
	 * @return 是否保存成功
	 */
	Map<String, List<Map<String, Templet>>> getProduct(String detailNumber);

	/**
	 * 根据配件来保存
	 * 
	 * @param leafs
	 * @param disLeafs
	 * @return
	 */
	boolean saveProduct(String month, Map<String, Templet> leafs,
			Map<String, Templet> disLeafs);

	/** 分页 */
	public Object[] getList(int pageNo, int pageSize);

	/** 获得一个Product的配件 */
	List<Product> getSingleProduct(String number);

	/** 获得所有的计划单编号 */
	List<String> getPruchaseNumber();

	boolean delete(String string);

	List<Product> getMonth(String month);

	Product get(Integer id);

	

}
