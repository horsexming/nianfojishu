package com.task.action.supplier;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.supplier.SupplierCertificationService;
import com.task.entity.Users;
import com.task.entity.supplier.SupplierCertification;
import com.task.entity.supplier.SupplierCertificationReview;
import com.task.entity.supplier.SupplierCertificationReviewContent;
import com.task.util.Util;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SupplierCertificationAction extends ActionSupport {
    private UserServer userService;
    private SupplierCertificationService supplierCertificationService;
    private SupplierCertification supplierCertification;
    private SupplierCertificationReview supplierCertificationReview;
    private SupplierCertificationReviewContent supplierCertificationReviewContent;
    private List<SupplierCertificationReviewContent> supplierCertificationReviewContents;
    private List<SupplierCertificationReview> supplierCertificationReviews;
    private List<SupplierCertification> supplierCertifications;
    private String successMessage;// 成功消息
    private String errorMessage;// 错误消息
    private Integer id;
    private Integer SupplierCertification_id;
    private String pageStatus;// 页面状态

    private List<String> reviewerStr;//审核人员
    private List<Users> supplierCertificationUsers;//当前处理人
    private List<Users> reviewUsers;//审批人员

    private File infoFile;
    private String infoFileContentType;
    private String infoFileFileName;

    //
    public String test2(){
        supplierCertificationService.findSupplierCertificationReviewer("1");
        return null;
    }

    //认证列表
    public String listall(){
        supplierCertifications=supplierCertificationService.listSupplierCertification();
        return "SupplierCertificationList";
    }
    //认证审核人员管理页面
    public String SupplierCertificationLevelpage() {
        reviewerStr=new ArrayList<String>();
        List<Users> ulist=new ArrayList<Users>();
        String name="";
        for (Integer i = 0; i < 3; i++) {
            ulist = supplierCertificationService.findSupplierCertificationReviewer(i.toString());
            if (ulist.size() > 0) {
                for (Users us : ulist) {
                    name += us.getName() + ";";
                }
                reviewerStr.add(name);
            }
            name = "";
        }
        return "SupplierCertificationLevel";
    }

    //修改认证审核人员
    public String updateSupplierCertificationLevel() {
        if (supplierCertificationReviews == null)
            supplierCertificationReviews = new ArrayList<SupplierCertificationReview>();
        for (Integer i = 0; i < (reviewerStr.size()); i++) {
            supplierCertificationReview = new SupplierCertificationReview();
            supplierCertificationReview.setReviewLevel(i.toString());
            String reviews="";
            String[] ss=reviewerStr.get(i).split(",");
            for(int ii=0;ii<ss.length;ii++){
                reviews+=ss[ii].substring(0, ss[ii].lastIndexOf("_"));
                reviews+=";";
            }
            supplierCertificationReview.setReviewUser(reviews);
            supplierCertificationReviews.add(supplierCertificationReview);
        }
        errorMessage = supplierCertificationService.updateSupplierCetificationLevel(supplierCertificationReviews);
        return ERROR;
    }

    //供应商认证申请页面
    public String addpage() {
        if(!supplierCertificationService.findSupplierCertificationReviewLevelExist()){
            pageStatus="false";
        };
        return "SupplierCertificationAction_addpage";
    }


    //认证页面
    public String findSupplierCertification() {
        reviewUsers=new ArrayList<Users>();
        if (supplierCertification == null)
            supplierCertification = new SupplierCertification();
        supplierCertification = supplierCertificationService.findSupplierCetification(id);
        supplierCertificationReviewContents = supplierCertificationService.findReviewContentBySupplierCertificationId(id);
        supplierCertificationUsers=supplierCertificationService.findSupplierCertificationhandler(id);
        for (SupplierCertificationReviewContent supplierCertificationReviewContent :supplierCertificationReviewContents) {
            Users u=userService.findUserById(Integer.parseInt(supplierCertificationReviewContent.getReviewUser()));
            reviewUsers.add(u);
        }
        return "SupplierCertificationAction_addpage";
    }

    public String SupplierCertificationList() {
        return "SupplierCertificationAction_addpage";
    }

    public String deleteSupplierCertification(){
        errorMessage=supplierCertificationService.deleteSupplierCetification(id);
        return "success";
    }

    //添加供应商认证申请
    public String AddSupplierCertification() {
        supplierCertification.setState("部门主管审核");
        supplierCertification.setApplyUser((Util.getLoginUser().getId()).toString());
        supplierCertification.setApplyUserName((Util.getLoginUser().getName()));
        errorMessage = supplierCertificationService.addSupplierCetification(supplierCertification);
        return "success";
    }

    //供应商认证审核流程
    public String updateSupplierCertification() {
        if (supplierCertification == null)
            supplierCertification = new SupplierCertification();
        supplierCertification = supplierCertificationService.findSupplierCetification(SupplierCertification_id);
        supplierCertificationReviewContent.setSupplierCertification(supplierCertification);
        supplierCertificationReviewContent.setReviewDate(Util.getDateTime());
        supplierCertificationReviewContent.setReviewUser(Util.getLoginUser().getId().toString());
        errorMessage = supplierCertificationService.addSupplierCetificationReviewContent(supplierCertificationReviewContent);
        supplierCertificationService.SupplierCetificationReviewContentFlow(SupplierCertification_id);
        return "success";
    }

    public String overSupplierCertification(){
        if (supplierCertification == null)
            supplierCertification = new SupplierCertification();
        supplierCertification = supplierCertificationService.findSupplierCetification(id);
        supplierCertification.setState("完成");
        errorMessage=supplierCertificationService.updateSupplierCetification(supplierCertification);
        return "success";
    }


    public String updateSupplierCertificationAttachment() {

        String newinfoFileName = "gysInfo"
                + Util.getDateTime("yyyyMMddHHmmss")
                + infoFileFileName.substring(infoFileFileName
                .lastIndexOf("."), infoFileFileName.length());


        String Path = "/upload/file/gysCertification";
        String realFilePath = ServletActionContext.getServletContext()
                .getRealPath(Path);
        // 如果不存在文件夹就创建
        File file = new File(realFilePath);
        File backfile = new File("D:/WorkSpace/HHTask/WebRoot/upload/file/gysCertification");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!backfile.exists()) {
            backfile.mkdirs();
        }
        String attachmentfile = Util.UploadFile(infoFile, infoFileFileName, newinfoFileName, "/upload/file/gysCertification", "D:/WorkSpace/HHTask/WebRoot/upload/file/gysCertification");
        if (!"上传文件失败!".equals(attachmentfile)&&!"备份文件失败!".equals(attachmentfile)) {
            SupplierCertification sC = supplierCertificationService.findSupplierCetification(SupplierCertification_id);
            sC.setInfo(supplierCertification.getInfo());
            sC.setInfo_attachment(attachmentfile);
            sC.setState("公司领导批示");
            supplierCertificationService.updateSupplierCetification(sC);
            errorMessage="成功";
        }else
            errorMessage=attachmentfile;
        return "success";
    }


    //get set
    public SupplierCertificationService getSupplierCertificationService() {
        return supplierCertificationService;
    }

    public void setSupplierCertificationService(SupplierCertificationService supplierCertificationService) {
        this.supplierCertificationService = supplierCertificationService;
    }

    public Integer getSupplierCertification_id() {
        return SupplierCertification_id;
    }

    public void setSupplierCertification_id(Integer supplierCertification_id) {
        SupplierCertification_id = supplierCertification_id;
    }

    public SupplierCertification getSupplierCertification() {
        return supplierCertification;
    }

    public void setSupplierCertification(SupplierCertification supplierCertification) {
        this.supplierCertification = supplierCertification;
    }

    public List<SupplierCertification> getSupplierCertifications() {
        return supplierCertifications;
    }

    public void setSupplierCertifications(List<SupplierCertification> supplierCertifications) {
        this.supplierCertifications = supplierCertifications;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(String pageStatus) {
        this.pageStatus = pageStatus;
    }


    public List<String> getReviewerStr() {
        return reviewerStr;
    }

    public void setReviewerStr(List<String> reviewerStr) {
        this.reviewerStr = reviewerStr;
    }

    public SupplierCertificationReview getSupplierCertificationReview() {
        return supplierCertificationReview;
    }

    public void setSupplierCertificationReview(SupplierCertificationReview supplierCertificationReview) {
        this.supplierCertificationReview = supplierCertificationReview;
    }

    public List<SupplierCertificationReview> getSupplierCertificationReviews() {
        return supplierCertificationReviews;
    }

    public void setSupplierCertificationReviews(List<SupplierCertificationReview> supplierCertificationReviews) {
        this.supplierCertificationReviews = supplierCertificationReviews;
    }

    public SupplierCertificationReviewContent getSupplierCertificationReviewContent() {
        return supplierCertificationReviewContent;
    }

    public void setSupplierCertificationReviewContent(SupplierCertificationReviewContent supplierCertificationReviewContent) {
        this.supplierCertificationReviewContent = supplierCertificationReviewContent;
    }

    public List<SupplierCertificationReviewContent> getSupplierCertificationReviewContents() {
        return supplierCertificationReviewContents;
    }

    public void setSupplierCertificationReviewContents(List<SupplierCertificationReviewContent> supplierCertificationReviewContents) {
        this.supplierCertificationReviewContents = supplierCertificationReviewContents;
    }

    public File getInfoFile() {
        return infoFile;
    }

    public void setInfoFile(File infoFile) {
        this.infoFile = infoFile;
    }

    public String getInfoFileContentType() {
        return infoFileContentType;
    }

    public void setInfoFileContentType(String infoFileContentType) {
        this.infoFileContentType = infoFileContentType;
    }

    public String getInfoFileFileName() {
        return infoFileFileName;
    }

    public void setInfoFileFileName(String infoFileFileName) {
        this.infoFileFileName = infoFileFileName;
    }

    public List<Users> getSupplierCertificationUsers() {
        return supplierCertificationUsers;
    }

    public void setSupplierCertificationUsers(List<Users> supplierCertificationUsers) {
        this.supplierCertificationUsers = supplierCertificationUsers;
    }

    public UserServer getUserService() {
        return userService;
    }

    public void setUserService(UserServer userService) {
        this.userService = userService;
    }

    public List<Users> getReviewUsers() {
        return reviewUsers;
    }

    public void setReviewUsers(List<Users> reviewUsers) {
        this.reviewUsers = reviewUsers;
    }
}
