/**
 * 
 */
package com.task.action.expresscabinet;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.expresscabinet.WePayServer;
import com.task.entity.expresscabinet.Charging;
import com.task.entity.expresscabinet.Courier;
import com.task.entity.expresscabinet.CourierCompany;
import com.task.entity.lpanclear.Employee;
import com.task.util.Upload;
import com.task.util.Util;
/**
 * 微信支付action层
 */
public class WePayAction {
	private String errorMessage; // 错误消息
	private String successMessage;
	private WePayServer wePayServer;
	private String urlCode;        //二维码url 
	private Charging charging;     //快递柜收费标准表
	private Integer overTime;      //超时小时(按小时定义)
    private Integer cost;          //标准费用
    private List<Charging> chargingList;
    private Integer pageSize = 4; // 分页当前页显示记录行
	private Integer pageNo = 1; // 第几页
	private String cpage = "1";
	private String total;
	private String url;
	private List<CourierCompany> couCpanyList;
	private Courier courier;     //快递员信息表对象
	// 上传文件对象
	private File idFront;       //身份证正面照或是工作证正面照
	//上传文件名
	private String idFrontFileName;
	// 上传文件类型
	private String idFrontType;;    
	
	private String phoneNumber;  //手机号(跳转传递使用)
	
	private Integer couId;        //审批时查看其它信息所对应的id信息
	

	




	



	



	/**
	 * 微信支付
	 */
	public String weixinPay(){
        String productId = "5533685";   //商品编号(根据快递柜传递过来的编号进行查询价格，名称)
        String userId = "user02";
        String order_price ="4";
        String body = "可乐";
        String text = wePayServer.weixinPay(userId,productId,order_price,body); 
        System.out.println("输出一下text:"+text);
        if(text !=null){
            System.out.println("********成功获取二维码url*********");
            urlCode = text;
            System.out.println("输出一下urlCode"+urlCode);
        }            
        return "wepay";
	}
	
	/**
	 * 快递柜收费标准查询
	 */
	public String selectCharg(){
		
		
//		if (charging != null) {
//			ActionContext.getContext().getSession().put("chargingfy", charging);
//		} else {
//			// 根据条件分页
//			charging = (Charging) ActionContext.getContext().getSession().get(
//					"chargingfy");
//		}
		if (pageNo == null && pageSize == null) {
			pageNo = 1;
			pageSize = 4;
		}
		// 注意一下Object[] object
		Map<Integer, Object> map = wePayServer.selectCharg(charging,
				Integer.parseInt(cpage), pageSize);
		chargingList = (List<Charging>) map.get(1);
		if (chargingList != null && chargingList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("WePayAction_selectCharg.action");
			successMessage = null;
		} else {
			errorMessage = "没有找到符合  的内容";
		}

		return "express_charges";
	}
	
	
	
	/**
	 * 新增跳转
	 */
	public String toaddchang(){
		return "add_charging";
	}
	/**
	 * 新增新的快递柜标准
	 */
	public String insertCharg(){
		if(charging !=null){
			boolean fhxz = wePayServer.insertCharg(charging);
			if(fhxz){
				errorMessage = "添加成功";
				url = "WePayAction_toaddchang.action?errorMessage="+errorMessage;
			}else{
				errorMessage = "该快递柜类型已存在!!!";
			}
		}

		return "error";
	}
	
	
	
	/**
	 * 根据id查询收费标准对象
	 */
	public String selectCg(){
		if(charging !=null){
			charging = wePayServer.selectCg(charging);
			if(charging !=null){
				return "up_charging";
			}
		}
		errorMessage ="无法查到此修改对象";	
		return "error";
	}
	
	/**
	 * 修改跳转
	 */
	public String toupchang(){
		return "up_charging";
	}
	/**
	 * 修改快递柜收费标准
	 */
	public String updateCharg(){		
		if(charging !=null){
			boolean upCharg = wePayServer.updateCharg(charging);
			if(upCharg){
				errorMessage = "修改成功";
				url = "WePayAction_toupchang.action?errorMessage="+errorMessage;
			}else{
				errorMessage = "修改失败!!!";
			}			
		}
		return "error";
	}
	
	/**
	 * 删除跳转
	 */
	public String todechang(){
		return "express_charges";
	}
	
	/**
	 * 删除快递柜类型标准
	 * @return
	 */
	public String deleteCharg(){
		if(charging !=null){
			boolean upCharg = wePayServer.deleteCharg(charging);
			if(upCharg){
				errorMessage = "删除成功";
				url = "WePayAction_todechang.action?errorMessage="+errorMessage;
			}else{
				errorMessage = "删除失败!!!";
			}
		}
		return "error";
	}
	
	/**
	 * 查询所有快递公司
	 * @return
	 */
	public String selectCouCpany(){
		couCpanyList = wePayServer.selectCouCompany();
		
		return "add_courier";
	}
	
	
	/**
	 * 跳转到快递员信息录入界面
	 * @return
	 */
	public String tiaoZhuanAddCourier(){
		
		if(phoneNumber !=""||phoneNumber !=null){
			couCpanyList = wePayServer.selectCouCompany();
			return "add_courier";
		}else{
			errorMessage = "手机号为空!请重新请求";
		}		
		return "error";
	}
	
	
	
	/**
	 * 新增快递员申请信息
	 * @return
	 */
	public String AddCourier(){
		if(courier !=null&&idFront !=null){
			//此处进行上传图片的保存操作
		    String realFileName =Util.getDateTime("yyyyMMddHHmmss");
		    String realFilePath = "/upload/file/lpanclear";
		    String basePath=ServletActionContext.getServletContext().getRealPath(realFilePath);	
			File file = new File(basePath); 
		    if(!file.exists()){ 
				file.mkdir();   
			}	    
			Upload upload=new Upload();
			realFileName=upload.UploadFile(idFront, idFrontFileName, null,realFilePath, null);
			String file2 = new File(file,idFrontFileName).toString();
			System.out.println("文件名："+idFrontFileName);
			System.out.println("file2:"+file2);
			if(realFileName !=null&&idFrontFileName !=null){
				courier.setIdFront(realFileName);
				courier.setPrimaryName(idFrontFileName);
				 boolean xzfhjg = wePayServer.insertCourier(courier);
				if(xzfhjg){
					
					return "courier_tips";
				}else{
					errorMessage="您的信息填写出错,请确保信息无误。";
				}
			}
		}				
		return "error";		
	}
	
	
	/**
	 * 查询快递员录入的信息
	 * @return
	 */
	public String selectEpIdCourier(){
		if(couId !=null){
			courier =wePayServer.selectEpIdCourier(couId);
		}		 
		return "courier_ApprovalInfo";
	}
	
	
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public WePayServer getWePayServer() {
		return wePayServer;
	}
	public void setWePayServer(WePayServer wePayServer) {
		this.wePayServer = wePayServer;
	}
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
	public Charging getCharging() {
		return charging;
	}
	public void setCharging(Charging charging) {
		this.charging = charging;
	}
	public Integer getOverTime() {
		return overTime;
	}
	public void setOverTime(Integer overTime) {
		this.overTime = overTime;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public List<Charging> getChargingList() {
		return chargingList;
	}
	public void setChargingList(List<Charging> chargingList) {
		this.chargingList = chargingList;
	}
	public List<CourierCompany> getCouCpanyList() {
		return couCpanyList;
	}
	public void setCouCpanyList(List<CourierCompany> couCpanyList) {
		this.couCpanyList = couCpanyList;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public File getIdFront() {
		return idFront;
	}

	public void setIdFront(File idFront) {
		this.idFront = idFront;
	}

	public String getIdFrontFileName() {
		return idFrontFileName;
	}

	public void setIdFrontFileName(String idFrontFileName) {
		this.idFrontFileName = idFrontFileName;
	}

	public String getIdFrontType() {
		return idFrontType;
	}

	public void setIdFrontType(String idFrontType) {
		this.idFrontType = idFrontType;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public Integer getCouId() {
		return couId;
	}

	public void setCouId(Integer couId) {
		this.couId = couId;
	}
}
