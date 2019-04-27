package com.task.Server.supplier;

import com.task.entity.Users;
import com.task.entity.supplier.SupplierCertification;
import com.task.entity.supplier.SupplierCertificationReview;
import com.task.entity.supplier.SupplierCertificationReviewContent;

import java.util.List;

public interface SupplierCertificationService {

    String addSupplierCetification(SupplierCertification supplierCertification);

    String deleteSupplierCetification(Integer id);

    String updateSupplierCetification(SupplierCertification supplierCertification);

    SupplierCertification findSupplierCetification(Integer id);

    List<SupplierCertificationReviewContent> findReviewContentBySupplierCertificationId(Integer id);

    SupplierCertificationReview findSupplierCetificationLevel(String str);

    String updateSupplierCetificationLevel(List<SupplierCertificationReview> srs);

    String addSupplierCetificationReviewContent(SupplierCertificationReviewContent src);

    String SupplierCetificationReviewContentFlow(Integer id);

    List<Users> findSupplierCertificationReviewer(String level);

    List<SupplierCertification> listSupplierCertification();

    //当前处理人
    List<Users> findSupplierCertificationhandler(Integer id);

    //查询未审批人员
    List<Users> findNoReviewer(Integer id, String level);

    /*
        * @author fy
    　　* @date 2018/8/3 15:17
    　　* @Description: 有不同意 返回false
    　　* @param [id]
    　　* @return java.lang.Boolean
    　　* @throws
    　　*/
    Boolean findfalseSupplierCertificationReviewContent(SupplierCertification s);

    Boolean findSupplierCertificationReviewLevelExist();
}
