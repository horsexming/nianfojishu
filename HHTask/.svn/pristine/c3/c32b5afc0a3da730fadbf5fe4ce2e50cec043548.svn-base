package com.task.Server.supplier;

import com.task.entity.supplier.SupplierEvaluate;
import com.task.entity.supplier.SupplierEvaluateLevel;

import java.util.List;

public interface SupplierEvaluateService {

    SupplierEvaluate findSupplierEvaluate(Integer id);

    List<SupplierEvaluate> findSupplierEvaluateListByMonth(String month);

    Boolean skipSupplier(SupplierEvaluate supplierEvaluate);


    boolean updatelevelscore(List<String> ls);


    boolean updatelevel(List<SupplierEvaluateLevel> ls);

    List<SupplierEvaluateLevel> findlevelsocre();

    Object[] findSupplierEvaluateList(SupplierEvaluate supplierEvaluate, String month, String category, int pageNo, int pageSize);

    String addSupplierEvaluate(SupplierEvaluate supplierEvaluate);

    String addSupplierEvaluateList(List<SupplierEvaluate> slist);

    String deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate);

    String updateSupplierEvaluate(SupplierEvaluate supplierEvaluate);

    //查询某月数据
    List<SupplierEvaluate> findMonthRecord(String month);

    //查询季度数据
    List<SupplierEvaluate> findQuarterRecord(String quarter);

    /*
        * @author fy
    　　* @date 2018/11/8 14:09
    　　* @Description: 验证当季是否已生成
    　　* @param [quarter]
    　　* @return boolean ture 已生成
    　　* @throws
    　　*/
    boolean repeatVerify(String quarter);

    List<String> findlevelScore();

    List<SupplierEvaluateLevel> findsupplierEvaluateLevelList();

    void exportEXCEL(String month);

    /*
        * @author fy
    　　* @date 2018/8/20 10:12
    　　* @Description: 每月生成审核评分表
    　　* @param []
    　　* @return java.lang.Boolean
    　　* @throws
    　　*/
    Boolean generateLastMonthReport();

    /*
        * @author fy
    　　* @date 2019/1/9 16:44
    　　* @Description: 生成上季度审核评分表
    　　* @param []
    　　* @return java.lang.Boolean
    　　* @throws
    　　*/
    Boolean generateLastQuartersReport();

    Boolean findHaveMonthReport(String month);

    List getMonth();

    List getsupplierCatagory();

    String updateSupplierEvaluatesScore(List<SupplierEvaluate> supplierEvaluates);
}
