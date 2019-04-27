package com.task.Server.unpasskp;

import java.util.Map;

import com.task.entity.unpasskp.ProductUnPassKp;

public interface ProductUnPassKpServer {
    /**
     * 分页显示未开票通过产品
     * @param productUnPassKp
     * @param cpage
     * @param pageSize
     * @return
     */
	Map<Integer, Object> findProductUnPassKpByCondition(
			ProductUnPassKp productUnPassKp, int cpage, int pageSize);
	ProductUnPassKp getById(Integer id);
    String update(ProductUnPassKp productUnPassKp) throws Exception;
    boolean delete(ProductUnPassKp productUnPassKp);
}
