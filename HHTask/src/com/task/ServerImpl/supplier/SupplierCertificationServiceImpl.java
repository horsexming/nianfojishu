package com.task.ServerImpl.supplier;

import com.task.Dao.TotalDao;
import com.task.Server.supplier.SupplierCertificationService;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.Users;
import com.task.entity.supplier.SupplierCertification;
import com.task.entity.supplier.SupplierCertificationReview;
import com.task.entity.supplier.SupplierCertificationReviewContent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class SupplierCertificationServiceImpl implements SupplierCertificationService {
    private TotalDao totalDao;


    @Override
    public String addSupplierCetification(SupplierCertification supplierCertification) {
        if (supplierCertification != null) {
            Boolean b = totalDao.save(supplierCertification);
            if (b == true) {
                List<Users> usersList = new ArrayList<Users>();
                usersList = findSupplierCertificationReviewer("0");
                if(usersList!=null){
                    for (Users s:usersList) {
                        Integer[] us=new Integer[]{s.getId()};
                        AlertMessagesServerImpl.addAlertMessages("新供应商开发认证","等待审批",
                                us,"SupplierCertificationAction_findSupplierCertification.action?id="+supplierCertification.getId(),true);
                    }
                }
                return "申请成功";
            }
            return "申请失败";
        }
        return "申请失败";
    }

    @Override
    public String deleteSupplierCetification(Integer id) {
        Boolean b = totalDao.delete(findSupplierCetification(id));
        if (b) {
            return "成功";
        }
        return null;

    }

    @Override
    public String updateSupplierCetification(SupplierCertification supplierCertification) {
        SupplierCertification sfind = findSupplierCetification(supplierCertification.getId());
        BeanUtils.copyProperties(supplierCertification, sfind, new String[]{"id"});
        boolean r = totalDao.update(sfind);
        if (r == true) {
            return "成功";
        } else
            return "失败";
    }

    @Override
    public SupplierCertification findSupplierCetification(Integer id) {
        if (id != null) {
            return (SupplierCertification) totalDao.getObjectById(SupplierCertification.class, id);
        }
        return null;
    }

    @Override
    public List<SupplierCertificationReviewContent> findReviewContentBySupplierCertificationId(Integer id) {
        if (id != null) {
            SupplierCertification supplierCertification = findSupplierCetification(id);
            Set<SupplierCertificationReviewContent> s = supplierCertification.getSupplierCertificationReviewContents();
            List<SupplierCertificationReviewContent> slist = new ArrayList<SupplierCertificationReviewContent>();
            for (SupplierCertificationReviewContent src : s) {
                slist.add(src);
            }
            Collections.sort(slist, new Comparator<SupplierCertificationReviewContent>() {
                @Override
                public int compare(SupplierCertificationReviewContent o1, SupplierCertificationReviewContent o2) {
                    int i = o1.getId() - o2.getId();
                    return i;
                }
            });
            return slist;
        }
        return null;
    }

    @Override
    public SupplierCertificationReview findSupplierCetificationLevel(String str) {
        String hql = "from SupplierCertificationReview where reviewLevel=?";
        List srlist = totalDao.query(hql, str.toString());
        if (srlist.size() != 0) {
            return (SupplierCertificationReview) srlist.get(0);
        }
        return null;
    }

    @Override
    public String updateSupplierCetificationLevel(List<SupplierCertificationReview> srs) {
        if (srs != null) {
            for (SupplierCertificationReview sr : srs) {
                SupplierCertificationReview srfind = findSupplierCetificationLevel(sr.getReviewLevel());
                if (srfind == null) {
                    totalDao.save(sr);
                } else {
                    srfind.setReviewUser(sr.getReviewUser());
                    totalDao.update(srfind);
                }
            }
            return "成功";
        } else return "保存失败";
    }

    @Override
    public String addSupplierCetificationReviewContent(SupplierCertificationReviewContent src) {
        if (src != null) {
            totalDao.save(src);
            return "成功";
        }
        return null;
    }

    @Override
    public String SupplierCetificationReviewContentFlow(Integer id) {
        SupplierCertification sc = findSupplierCetification(id);
        //判断是否全部审核
        Boolean B_allReview = true;
        if (findNoReviewer(sc.getId(),sc.getState()).size() == 0) {
            B_allReview = true;
        }
        List<Users> usersList = new ArrayList<Users>();

        //判断是否有不同意
        if (!findfalseSupplierCertificationReviewContent(sc)) {
            sc.setState("不同意");
            updateSupplierCetification(sc);
            return "成功";
        }
        if ("部门主管审核".equals(sc.getState()) && B_allReview) {
            //更新至下一级状态
            sc.setState("采购总监审核");
            updateSupplierCetification(sc);
            //下级审核列表
            usersList = findSupplierCertificationReviewer("1");
            if (usersList != null) {
                for (Users s : usersList) {
//                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",
//                            sc.getState() + "等待审批", "任务反馈通知", s.getCode());
                    Integer[] us=new Integer[]{s.getId()};
                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",sc.getState() + "等待审批"
                            ,us,"SupplierCertificationAction_findSupplierCertification.action?id="+id,false);
                }
            }
            return null;
        }
        if ("采购总监审核".equals(sc.getState()) && B_allReview) {
            sc.setState("公司领导核准");
            updateSupplierCetification(sc);
            usersList = findSupplierCertificationReviewer("2");
            if (usersList != null) {
                for (Users s : usersList) {
                    Integer[] us=new Integer[]{s.getId()};
                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",sc.getState() + "等待审批"
                            ,us,"SupplierCertificationAction_findSupplierCertification.action?id="+id,false);
//                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",
//                            sc.getState() + "等待审批", "任务反馈通知", s.getCode());
                }
            }
            return null;
        }
        if ("公司领导核准".equals(sc.getState()) && B_allReview) {
            sc.setState("供应商信息调查");
            updateSupplierCetification(sc);
            return null;
        }
        if ("公司领导批示".equals(sc.getState()) && B_allReview) {
            sc.setState("合格供应商录入");
            updateSupplierCetification(sc);
            usersList = findSupplierCertificationReviewer("2");
            if (usersList != null) {
                for (Users s : usersList) {
//                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",
//                            sc.getState() + "等待审批", "任务反馈通知", s.getCode());
                    Integer[] us=new Integer[]{s.getId()};
                    AlertMessagesServerImpl.addAlertMessages("新供应商开发认证",sc.getState() + "等待审批"
                            ,us,"SupplierCertificationAction_findSupplierCertification.action?id="+id,false);
                }
            }
            return null;
        }

        return null;
    }


    //查找认证审批人员
    @Override
    public List<Users> findSupplierCertificationReviewer(String level) {
        String hql = "from SupplierCertificationReview where reviewLevel=?";
        List<SupplierCertificationReview> findlist = totalDao.query(hql, level);
        List<Users> usersList = new ArrayList<Users>();
        List<Users> l = new ArrayList<Users>();
        for (SupplierCertificationReview scr : findlist) {
            String[] idlist = scr.getReviewUser().split(";");
            hql = "from Users where id=?";
            for (int i = 0; i < idlist.length; i++) {
                l = totalDao.query(hql, Integer.valueOf(idlist[i]));
                usersList.add((Users) l.get(0));
            }
        }
        return usersList;
    }

    @Override
    public List<SupplierCertification> listSupplierCertification() {
        String hql = "from SupplierCertification order by id desc";
        List<SupplierCertification> slist = totalDao.query(hql);
        return slist;
    }

    //当前处理人
    @Override
    public List<Users> findSupplierCertificationhandler(Integer id) {
        SupplierCertification sC = (SupplierCertification) totalDao.getObjectById(SupplierCertification.class, id);
        List<Users> usersList = new ArrayList<Users>();
        String state = sC.getState();
        if ("供应商开发申请".equals(state) || "供应商信息调查".equals(state) || "合格供应商录入".equals(state)) {
            usersList.add((Users) totalDao.getObjectById(Users.class, Integer.valueOf(sC.getApplyUser())));
        }
        if ("部门主管审核".equals(state)) {
            usersList = findSupplierCertificationReviewer("0");
        }
        if ("采购总监审核".equals(state)) {
            usersList = findSupplierCertificationReviewer("1");
        }
        if ("公司领导核准".equals(state) || "公司领导批示".equals(state)) {
            usersList = findSupplierCertificationReviewer("2");
        }
        return usersList;
    }

    //查询未审批人员
    @Override
    public List<Users> findNoReviewer(Integer id,String level) {
        SupplierCertification supplierCertification = findSupplierCetification(id);
        Set<SupplierCertificationReviewContent> slist = supplierCertification.getSupplierCertificationReviewContents();
        //所有已处理人员
        List<Users> allReviewedUsersList = new ArrayList<Users>();
        //所有审核人员
        List<Users> allhandlerReviewer = findSupplierCertificationhandler(id);
        for (SupplierCertificationReviewContent sContent : slist) {
            if(level.equals(sContent.getReviewLevel())){
                Users u = (Users) totalDao.getObjectById(Users.class, Integer.valueOf(sContent.getReviewUser()));
                allReviewedUsersList.add(u);
            }
        }
        //未审批人员
        allhandlerReviewer.removeAll(allReviewedUsersList);
        return allhandlerReviewer;
    }


    /*
     * @author fy
     * @date 2018/8/3 15:17
     * @Description: 有不同意 返回false
     * @param [id]
     * @return java.lang.Boolean
     * @throws
     */
    @Override
    public Boolean findfalseSupplierCertificationReviewContent(SupplierCertification s) {
        Set<SupplierCertificationReviewContent> supplierCertificationReviewContentSet = s.getSupplierCertificationReviewContents();
        if (supplierCertificationReviewContentSet != null) {
            for (SupplierCertificationReviewContent sContent : supplierCertificationReviewContentSet) {
                if ("false".equals(sContent.getReviewOpinion()))
                    return false;
            }
        }
        return true;
    }

    @Override
    public Boolean findSupplierCertificationReviewLevelExist(){
        String hql="from SupplierCertificationReview";
        List l=totalDao.query(hql);
        if (l.size()<3){
            return false;
        }
        return true;
    }

    public TotalDao getTotalDao() {
        return totalDao;
    }

    public void setTotalDao(TotalDao totalDao) {
        this.totalDao = totalDao;
    }

}
