package com.task.Server.qimi;



import java.util.List;

import com.task.entity.qimi.Gas;

public interface GasServer {
  /**
   * 添加
   * @param gas
   * @return
   */
	String addGas(Gas gas);

/**
 * 查询
 * @param gas
 * @param parseInt
 * @param pageSize
 * @return
 */
Object[] findGas(Gas gas, int parseInt, int pageSize);


}
