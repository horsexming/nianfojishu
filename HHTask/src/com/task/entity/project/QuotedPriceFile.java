package com.task.entity.project;
/**
 * 报价图纸 ta_pro_QuotedPriceFile
 * @author txb
 *
 */
public class QuotedPriceFile implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String markId;//件号
    private Integer processNO;//工序号
    private String processName;//工序名称
    private String fileName;//文件名
    private String fileName2;//初始上传时为加章文件，审批完成之后替换fileName
    private String oldfileName;//文件原名称
    private String month;//上传月份(上传文件夹以月份命名（yyyy-MM）)
    private String type;//文件类型(视频文件，工艺规范,原图文件,成型图)
//    private String productStyle;//试制，批产
    private String banBenNumber;//版本号（原图使用）
    private String status;//默认,历史
    private String userName;//上传人姓名
    private String userCode;//上传人工号
    private String addTime;//上传时间
    private Integer banci;// 版次
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public Integer getProcessNO() {
		return processNO;
	}
	public void setProcessNO(Integer processNO) {
		this.processNO = processNO;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName2() {
		return fileName2;
	}
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	public String getOldfileName() {
		return oldfileName;
	}
	public void setOldfileName(String oldfileName) {
		this.oldfileName = oldfileName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public String getProductStyle() {
//		return productStyle;
//	}
//	public void setProductStyle(String productStyle) {
//		this.productStyle = productStyle;
//	}
	public String getBanBenNumber() {
		return banBenNumber;
	}
	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
    
    
    
    
}