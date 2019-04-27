package com.task.ServerImpl.supplier;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.supplier.SupplierEvaluateService;
import com.task.entity.hegebaobiao.MouthHege;
import com.task.entity.hegebaobiao.QuarterHege;
import com.task.entity.supplier.SupplierEvaluate;
import com.task.entity.supplier.SupplierEvaluateLevel;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SupplierEvaluateServiceImpl implements SupplierEvaluateService {

    private TotalDao totalDao;

    @Override
    public SupplierEvaluate findSupplierEvaluate(Integer id) {
        return (SupplierEvaluate) totalDao.getObjectById(SupplierEvaluate.class, id);
    }

    @Override
    public List<SupplierEvaluate> findSupplierEvaluateListByMonth(String month) {
        String hql = "from SupplierEvaluate where timeframe=? order by qualityBatch desc";
        List<SupplierEvaluate> slist = totalDao.query(hql, month);
        return slist;
    }

    @Override
    public Boolean skipSupplier(SupplierEvaluate supplierEvaluate) {
        SupplierEvaluate sfind = findSupplierEvaluate(supplierEvaluate.getId());
        if (sfind.getSkipSupplier() == null || "false".equals(sfind.getSkipSupplier())) {
            sfind.setSkipSupplier("true");
        } else if ("true".equals(sfind.getSkipSupplier())) {
            sfind.setSkipSupplier("false");
        }
        Integer id=sfind.getSupplierid();
        ZhUser zhUser= (ZhUser) totalDao.getObjectById(ZhUser.class,id);
        zhUser.setSkipEvaluate("true");
        if(totalDao.update(zhUser)&&totalDao.update(sfind))
            return true;
        return false;
    }


    @Override
    public boolean updatelevelscore(List<String> ls) {
        try {
            if (findlevelsocre() != null) {
                List<SupplierEvaluateLevel> supplierEvaluateLevels = findlevelsocre();
                for (int i = 0; i < supplierEvaluateLevels.size(); i++) {
                    supplierEvaluateLevels.get(i).setLevelScore(Float.valueOf(ls.get(i)));
                    totalDao.update(supplierEvaluateLevels.get(i));
                }
            } else {
                SupplierEvaluateLevel s = new SupplierEvaluateLevel();
                SupplierEvaluateLevel s1 = new SupplierEvaluateLevel();
                SupplierEvaluateLevel s2 = new SupplierEvaluateLevel();
                s.setLevelScore(Float.valueOf(ls.get(0)));
                s.setLevelName("A");
                totalDao.save(s);
                s1.setLevelScore(Float.valueOf(ls.get(1)));
                s1.setLevelName("B");
                totalDao.save(s1);
                s2.setLevelScore(Float.valueOf(ls.get(2)));
                s2.setLevelName("C");
                totalDao.save(s2);
            }
            return true;
        }catch (Exception e){
            return false;
        }

    }


    @Override
    public boolean updatelevel(List<SupplierEvaluateLevel> ls) {
        List<SupplierEvaluateLevel> supplierEvaluateLevels =totalDao.query("from SupplierEvaluateLevel");
        for (SupplierEvaluateLevel s:supplierEvaluateLevels) {
            totalDao.delete(s);
        }
        for (SupplierEvaluateLevel s:ls) {
            totalDao.save(s);
        }
        return true;
    }


    @Override
    public List<SupplierEvaluateLevel> findlevelsocre() {
        List<SupplierEvaluateLevel> supplierEvaluateLevels = totalDao.query("from SupplierEvaluateLevel order by levelName");
        if (supplierEvaluateLevels.size() < 1) {
            return null;
        }
        return supplierEvaluateLevels;
    }

    @Override
    public Object[] findSupplierEvaluateList(SupplierEvaluate supplierEvaluate, String month, String category, int pageNo, int pageSize) {
        String hql = "from SupplierEvaluate where 1=1";
        if (month != null) {
            hql = hql + " and timeframe='" + month + "'";
        }
        if (category != null && !"".equals(category)) {
            hql = hql + " and supplierCatagory='" + category + "'";
        }
        if (supplierEvaluate.getSupplierName() != null && !"".equals(supplierEvaluate.getSupplierName())) {
            hql = hql + " and supplierName like '%" + supplierEvaluate.getSupplierName() + "%'";
        }
        hql = hql + " order by qualityBatch desc";
        List<SupplierEvaluate> slist = totalDao.findAllByPage(hql, pageNo, pageSize);
        int count = totalDao.getCount(hql);
        Object[] o = {slist, count};
        return o;
    }


    @Override
    public String addSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
        return null;
    }

    @Override
    public String addSupplierEvaluateList(List<SupplierEvaluate> slist) {
        try {
            for (SupplierEvaluate s : slist) {
                totalDao.save(s);
            }
        } catch (Exception e) {
            return e.toString();
        }
        return "成功";
    }

    @Override
    public String deleteSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
        return null;
    }

    @Override
    public String updateSupplierEvaluate(SupplierEvaluate supplierEvaluate) {
        return null;
    }


    //查询某月数据
    @Override
    public List<SupplierEvaluate> findMonthRecord(String month) {
        String hql = "from MouthHege where mouth=?";
        List<MouthHege> mlist = totalDao.query(hql, month);
        List<SupplierEvaluate> slist = new ArrayList<SupplierEvaluate>();
        String hqlforSupplierCclass = "select cclass from ZhUser where id=?";
        String hqlforSupplierSkip="select skipEvaluate from ZhUser where id=?";
        List<Object> l = new ArrayList<Object>();
        List<Object> l2 = new ArrayList<Object>();
        for (MouthHege m : mlist) {
            l2 = totalDao.query(hqlforSupplierSkip, m.getGysid());
            if(l2.size()>0){
                //是否跳过供应商
                if(!"true".equals(l2.get(0))){
                    SupplierEvaluate supplierEvaluate = new SupplierEvaluate();
                    supplierEvaluate.setSupplierid(m.getGysid());
                    l = totalDao.query(hqlforSupplierCclass, m.getGysid());
                    supplierEvaluate.setSupplierCatagory(l.get(0).toString());
                    supplierEvaluate.setTimeframe(m.getMouth());
                    supplierEvaluate.setSupplierName(m.getGysname());
                    supplierEvaluate.setQualityBatch(m.getPiciCount());
                    supplierEvaluate.setQualityQualifiedBatch(m.getPicihgCount());
                    supplierEvaluate.setQualityPercent(m.getPiciQualifiedrate());
                    //计算品质分
                    supplierEvaluate.setQualityScore(calculateScore(40f, m.getPiciQualifiedrate()));
                    supplierEvaluate.setDeliveryDateBatch(m.getZhunshijiaofuCount());
                    supplierEvaluate.setDeliveryDatePercent(m.getJiaofuhege());
                    //计算交期分
                    supplierEvaluate.setDeliveryDateScore(calculateScore(30f, m.getJiaofuhege()));
                    slist.add(supplierEvaluate);

                };
            }
        }
        return slist;
    }



    //查询季度数据
    @Override
    public List<SupplierEvaluate> findQuarterRecord(String quarter) {
        {
            String hql = "from QuarterHege where quarter=?";
            List<QuarterHege> qlist = totalDao.query(hql, quarter);
            List<SupplierEvaluate> slist = new ArrayList<SupplierEvaluate>();
            String hqlforSupplierCclass = "select cclass from ZhUser where id=?";
            String hqlforSupplierSkip="select skipEvaluate from ZhUser where id=?";
            List<Object> l = new ArrayList<Object>();
            List<Object> l2 = new ArrayList<Object>();
            for (QuarterHege m : qlist) {
                l2 = totalDao.query(hqlforSupplierSkip, m.getGysid());
                if(l2.size()>=0){
                    //是否跳过供应商
                    if(!"true".equals(l2.get(0))){
                        SupplierEvaluate supplierEvaluate = new SupplierEvaluate();
                        supplierEvaluate.setSupplierid(m.getGysid());
                        l = totalDao.query(hqlforSupplierCclass, m.getGysid());
                        supplierEvaluate.setSupplierCatagory(l.get(0).toString());
                        supplierEvaluate.setTimeframe(m.getQuarter());
                        supplierEvaluate.setSupplierName(m.getGysname());
                        supplierEvaluate.setQualityBatch(m.getPiciCount());
                        supplierEvaluate.setQualityQualifiedBatch(m.getPicihgCount());
                        supplierEvaluate.setQualityPercent(m.getPiciQualifiedrate());
                        //计算品质分
                        supplierEvaluate.setQualityScore(calculateScore(40f, m.getPiciQualifiedrate()));
                        supplierEvaluate.setDeliveryDateBatch(m.getZhunshijiaofuCount());
                        supplierEvaluate.setDeliveryDatePercent(m.getJiaofuhege());
                        //计算交期分
                        supplierEvaluate.setDeliveryDateScore(calculateScore(30f, m.getJiaofuhege()));
                        slist.add(supplierEvaluate);
                    };
                }
            }
            return slist;
        }
    }



    /*
        * @author fy
    　　* @date 2018/11/8 14:09
    　　* @Description: 验证当季是否已生成
    　　* @param [quarter]
    　　* @return boolean ture 已生成
    　　* @throws
    　　*/
    @Override
    public boolean repeatVerify(String quarter){
        List<String> slist=totalDao.query("select distinct timeframe from SupplierEvaluate");
        for (String s:slist){
            if(quarter.equals(s)){
              return true;
            }
        }
        return false;
    }


    /**
     * @author fy
     * @date 2018/8/13 10:16
     * @Description:品质,交期评分
     * @param [basis, pecent] 基础分 合格率/交货率
     * @return java.lang.Float
     * @throws
     */
    public Float calculateScore(Float basis, Float pecent) {
        if (pecent < 0.7) {
            pecent = 0f;
        }
        Float target = basis * pecent;
        return target;
    }


    /**
     * @author fy
     * @date 2018/8/13 10:20
     * @Description: 计算总体得分
     * @param [qualityScore, deliveryScore, costScore, serviceScore]
     * @return java.lang.Float
     * @throws
     */
    public Float calculateTotalScore(Float qualityScore, Float deliveryScore, Float costScore, Float serviceScore) {
        Float target = qualityScore + deliveryScore + costScore + serviceScore;
        int scale = 2;//设置位数
        int roundingMode = 4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd = new BigDecimal(target);
        bd = bd.setScale(scale, roundingMode);
        target = bd.floatValue();
        return target;
    }

    public String calculateTotalLevel(Float score) {


        List<SupplierEvaluateLevel> supplierEvaluateLevelList=totalDao.query("from SupplierEvaluateLevel order by levelScore desc");
        if(supplierEvaluateLevelList.size()>0){
            for (Integer i=0;i<supplierEvaluateLevelList.size();i++){
                if(score>=supplierEvaluateLevelList.get(i).getLevelScore()){
                    return supplierEvaluateLevelList.get(i).getLevelName();
                }
                if(i==supplierEvaluateLevelList.size()){
                    return "未设定评分";
                }
            }

        }
        return "未设定评分";
    }

    @Override
    public List<String> findlevelScore(){
        List<String> l=totalDao.query("Select levelScore from SupplierEvaluateLevel order by levelName");
        if (l.size()<3)
            l.add("未设定评分");
            l.add("未设定评分");
            l.add("未设定评分");
            return l;
    }

    @Override
    public  List<SupplierEvaluateLevel> findsupplierEvaluateLevelList(){
        return totalDao.query("from SupplierEvaluateLevel");
    }


    @Override
    public void exportEXCEL(String month){
        String hql = "from SupplierEvaluate where 1=1";
        if (month != null) {
            hql = hql + " and timeframe='" + month + "'";
        }
        hql = hql + " order by qualityBatch desc";
        List<SupplierEvaluate> slist = totalDao.query(hql);


        try {
            // 取得HttpServletResponse
            HttpServletResponse response = (HttpServletResponse) ActionContext
                    .getContext().get(ServletActionContext.HTTP_RESPONSE);
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String((month.toString()+"供应商考核评分表").getBytes("GB2312"), "8859_1")
                        + ".xlsx");// 设定输出文件头


            response.setContentType("application/msexcel");// 定义输出类型

            SXSSFWorkbook workBook = new SXSSFWorkbook(50000);
            org.apache.poi.ss.usermodel.Sheet sheet = workBook
                    .createSheet("供应商考核评分表");
            Row row = sheet.createRow(2);
            CellRangeAddress rangeAddress = new CellRangeAddress(0, 0, 1, 15);
            CellStyle style = workBook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            Font font = workBook.createFont();
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            style.setFont(font);
            sheet.addMergedRegion(rangeAddress);
            row = sheet.createRow(0);
            org.apache.poi.ss.usermodel.Cell cell = row.createCell(1);
            cell.setCellValue(month+"供应商考核评分表");
            cell.setCellStyle(style);


            row = sheet.createRow(1);
            CellRangeAddress rangeAddress2 = new CellRangeAddress(1, 1, 3, 6);
            sheet.addMergedRegion(rangeAddress2);
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("品质(40分)");
            CellRangeAddress rangeAddress3 = new CellRangeAddress(1, 1, 7, 9);
            sheet.addMergedRegion(rangeAddress3);
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("交期(30分)");

            CellRangeAddress rangeAddress4 = new CellRangeAddress(1, 1, 10, 11);
            sheet.addMergedRegion(rangeAddress4);
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("成本(20分)");

            CellRangeAddress rangeAddress5 = new CellRangeAddress(1, 1, 12, 13);
            sheet.addMergedRegion(rangeAddress5);
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("服务(10分)");

            row = sheet.createRow(2);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("序号");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("供应商");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("供应商类别");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("交货批次");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("合格批次");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("合格率");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("品质得分");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("准时交货批次\t");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("准时交货率\t");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("交期得分");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("成本得分");
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("原因");
            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("服务得分");
            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("原因");
            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("合计得分");
            cell = row.createCell(15, CellType.STRING);
            cell.setCellValue("评定级别");

            // 保留两位小数，
            DecimalFormat df = new DecimalFormat("#.##");
//            for (int i = 0, len = goodsStores.size(); i < len; i++) {
//                GoodsStore p = goodsStores.get(i);
//                if (p.getGoodsStoreMarkId() != null
//                        && !"".equals(p.getGoodsStoreMarkId())
//                        && p.getGoodsStoreMarkId() != null) {
//                    if ("成品库".equals(p.getGoodsStoreWarehouse())
//                            || "备货库".equals(p.getGoodsStoreWarehouse())) {
//                        PrintedOut printedout = (PrintedOut) totalDao
//                                .getObjectByCondition(
//                                        " from PrintedOut "
//                                                + " where className = 'GoodsStore'  and entiyId = ? ",
//                                        p.getGoodsStoreId());
//                        if (printedout != null) {
//                            p
//                                    .setJhdw(printedout.getPrintedOutOrder()
//                                            .getJhdw());
//                            p.setRukuGroup(printedout.getPrintedOutOrder()
//                                    .getRukuGroup());
//                        }
//                    }
//                }
            for (int i = 0, len = slist.size(); i < len; i++) {
                SupplierEvaluate s = slist.get(i);

                row = sheet.createRow(i + 3);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i + 1);
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(s.getSupplierName());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(s.getSupplierCatagory());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(s.getQualityBatch());
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(s.getQualityQualifiedBatch());
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(new DecimalFormat("#.#%").format(s.getQualityPercent()));
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(df.format(s.getQualityScore()));
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(s.getDeliveryDateBatch());
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(new DecimalFormat("#.#%").format(s.getDeliveryDatePercent()));
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(df.format(s.getDeliveryDateScore()));
                cell = row.createCell(10, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getCostScore());
                }
                cell = row.createCell(11, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getCostInfo());
                }
                cell = row.createCell(12, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getServiceScore());
                }
                cell = row.createCell(13, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getServiceinfo());
                }
                cell = row.createCell(14, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getEvaluateScore());
                }
                cell = row.createCell(15, CellType.STRING);
                if(s.getCostScore()!=null){
                    cell.setCellValue(s.getEvaluateLevel());
                }
            }
            workBook.write(os);
            workBook.close();// 记得关闭工作簿
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    /*
     * @author fy
     * @date 2018/8/20 10:12
     * @Description: 每月生成审核评分表
     * @param []
     * @return java.lang.Boolean
     * @throws
     */
    @Override
    public Boolean generateLastMonthReport() {
        List<SupplierEvaluate> supplierEvaluateList = findMonthRecord(Util.getLastMonth("yyyy-MM"));
        addSupplierEvaluateList(supplierEvaluateList);
        return true;
    }

    /**
        * @author fy
    　　* @date 2019/1/9 16:44
    　　* @Description: 生成上季度审核评分表
    　　* @param []
    　　* @return java.lang.Boolean
    　　* @throws
    　　*/
    @Override
    public Boolean generateLastQuartersReport() {
        String supplierEvaluateQuarter=Util.getLastMonth("yyyy-MM").toString();
        String month=supplierEvaluateQuarter.substring(5,7);
        supplierEvaluateQuarter=supplierEvaluateQuarter.substring(0,4)+"年"+((Integer.parseInt(month)+2)/3)+"季";
        if(!repeatVerify(supplierEvaluateQuarter)){
            List<SupplierEvaluate> supplierEvaluateList=findQuarterRecord(supplierEvaluateQuarter);
            addSupplierEvaluateList(supplierEvaluateList);
        }else
        {

        }
        return true;
    }

    @Override
    public Boolean findHaveMonthReport(String month) {
        List l = totalDao.query("from SupplierEvaluate where timeframe=?", month);
        if (l.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List getMonth() {
        return totalDao.query("select timeframe from SupplierEvaluate GROUP BY timeframe", null);
    }


    @Override
    public List getsupplierCatagory() {
        return totalDao.query("select supplierCatagory from SupplierEvaluate GROUP BY supplierCatagory", null);
    }


    @Override
    public String updateSupplierEvaluatesScore(List<SupplierEvaluate> supplierEvaluates) {
        try {
            Float scroe = 0F;
            String level = "";
            for (SupplierEvaluate s : supplierEvaluates) {
                SupplierEvaluate supplierEvaluateFind = findSupplierEvaluate(s.getId());
                BeanUtils.copyProperties(s, supplierEvaluateFind, new String[]{"id", "timeframe", "supplierid", "supplierName"
                        , "supplierCatagory", "qualityBatch", "qualityQualifiedBatch", "qualityPercent", "qualityScore"
                        , "deliveryDateBatch", "deliveryDatePercent", "deliveryDateScore", "skipSupplier"});
                //计算总分
                if(supplierEvaluateFind.getCostScore()!=null && supplierEvaluateFind.getServiceScore()!=null){
                    scroe = calculateTotalScore(supplierEvaluateFind.getQualityScore()
                            , supplierEvaluateFind.getCostScore(), supplierEvaluateFind.getServiceScore(), supplierEvaluateFind.getDeliveryDateScore());
                    supplierEvaluateFind.setEvaluateScore(scroe);
                    level = calculateTotalLevel(scroe);
                    supplierEvaluateFind.setEvaluateLevel(level);
                    totalDao.save(supplierEvaluateFind);
                }

            }
            return "成功";

        } catch (Exception e) {
            return e.toString();
        }

    }

    public TotalDao getTotalDao() {
        return totalDao;
    }

    public void setTotalDao(TotalDao totalDao) {
        this.totalDao = totalDao;
    }
}
