package com.task.action.barandqr;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.idautomation.datamatrix.encoder.GifEncoder;
import com.opensymphony.xwork2.ActionContext;
import com.task.Server.barandqr.AirtightLogServer;
import com.task.entity.barandqr.AirMachine;
import com.task.entity.barandqr.AirProduct;
import com.task.entity.barandqr.AirtightLog;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.task.util.qrUtil.DataMatrix;

public class AirtightLogAction{
	private static String qrUrl = "pr.shhhes.com";
	private AirtightLog airtightLog;
	private AirProduct airProduct;//气密产品
	private AirMachine airMachine;//气密机器
	private List<AirtightLog> airtightLogList;
	private List<AirProduct> airProductList;
	private List<AirMachine> airMachineList;
	private AirtightLogServer airtightLogServer;
	private List<String> markIds;
	private String content;
	private String src = null;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int count;// 绑定技能系数数量
	private int[] checkboxs;// 将要绑定的技能系数的id;
	private String hadChecked;// 是否含有已锁定的件号
	private Integer id;
	private String airMarkId;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	/**
	 * 分页显示气密日志
	 * 
	 * @return
	 */
	public String showList() {
		if (airtightLog != null) {
			ActionContext.getContext().getSession().put("airtightLog",
					airtightLog);
		} else {// 用来保持分页时带着查询条件
			airtightLog = (AirtightLog) ActionContext.getContext().getSession()
					.get("airtightLog");
		}
		Map<Integer, Object> map = airtightLogServer
				.findAirtightLogsByCondition(airtightLog, Integer
						.parseInt(cpage), pageSize); 
		airtightLogList = (List<AirtightLog>) map.get(1);// 显示页的气密记录列表
		if (airtightLogList != null & airtightLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("airtightLogAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "airtightlog_show";
	}
     
	/**
	 * 分页显示气密日志(删除)
	 * 
	 * @return
	 */
	public String showList1() {
		if (airtightLog != null) {
			ActionContext.getContext().getSession().put("airtightLog",
					airtightLog);
		} else {// 用来保持分页时带着查询条件
			airtightLog = (AirtightLog) ActionContext.getContext().getSession()
					.get("airtightLog");
		}
		Map<Integer, Object> map = airtightLogServer
				.findAirtightLogsByCondition1(airtightLog, Integer
						.parseInt(cpage), pageSize); 
		airtightLogList = (List<AirtightLog>) map.get(1);// 显示页的气密记录列表
		if (airtightLogList != null & airtightLogList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("airtightLogAction_showList1.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "airtightlog_show1";
	}
	
	public String deleteLog() {
		boolean b = airtightLogServer.deleteLog(airtightLog.getId());
		if (b) {
			successMessage = "删除成功!";
		} else {
			successMessage = "删除失败!";
		}
		airtightLog = null;
		return showList1();
	}

	/**
	 * 分页显示气密产品
	 * 
	 * @return
	 */
	public String showProductList() {
		if (airProduct != null) {
			ActionContext.getContext().getSession().put("airProduct",
					airProduct);
		} else {// 用来保持分页时带着查询条件
			airProduct = (AirProduct) ActionContext.getContext().getSession()
					.get("airProduct");
		}
		Map<Integer, Object> map = airtightLogServer
				.findAirProductsByCondition(airProduct,
						Integer.parseInt(cpage), pageSize);
		airProductList = (List<AirProduct>) map.get(1);// 显示页的气密记录列表
		if (airProductList != null & airProductList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("airtightLogAction_showProductList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "airProduct_show";
	}

	public String addProduct() {
		String msg = airtightLogServer.addProduct(airProduct);
		if (!msg.equals("true")) {
			successMessage = msg;
		}
		return showProductList();
	}

	public String toUpdateProduct() {
		airProduct = airtightLogServer.getProductById(airProduct.getId());
		if (airProduct == null) {
			successMessage = "没有找到目标";
			return showProductList();
		}
		return "airProduct_update";
	}

	public String updateProduct() {
		String msg = airtightLogServer.updateProduct(airProduct);
		if (!msg.equals("true")) {
			successMessage = msg;
			airProduct = null;
			return toUpdateProduct();
		} else {
			airProduct = null;
			return showProductList();
		}
	}

	public String toDeleteProduct() {
		boolean b = airtightLogServer.deleteProduct(airProduct.getId());
		if (b) {
			successMessage = "删除成功!";
		} else {
			successMessage = "删除失败!";
		}
		airProduct = null;
		return showProductList();

	}

	/**
	 * 跳往生成条形码页面
	 * 
	 * @return
	 */
	public String toCreateBarCodeUrl() {
//		airProduct = airtightLogServer.getChecked();
		airMarkId = (String) ActionContext.getContext().getSession().get("airMarkId");
		hadChecked = (String) ActionContext.getContext().getSession().get("hadChecked");
//		if (airProduct != null) {
//			hadChecked = "yes";
//		} else {
//			hadChecked = "no";
//			markIds = airtightLogServer.getProductMarkId();
//		}
		if(hadChecked==null||!hadChecked.equals("yes")){
			hadChecked = "no";
			markIds = airtightLogServer.getProductMarkId();
		}else{
			airProduct=airtightLogServer.getAirProductByMarkId(airMarkId);
		}
		return "makebarcode";
	}

	/**
	 * 跳往修改绑定件号页面
	 * 
	 * @return
	 */
	public String changeMarkId() {
		hadChecked = "no";
		markIds = airtightLogServer.getProductMarkId();
		return "makebarcode";
	}

	/**
	 * 修改绑定件号
	 * 
	 * @return
	 */
	public String checkMarkId() {
		if (airProduct != null) {
//			airtightLogServer.checkMarkId(airProduct.getMarkId());
			ActionContext.getContext().getSession().put("hadChecked",
					"yes");
			ActionContext.getContext().getSession().put("airMarkId",
					airProduct.getMarkId());
		}
		return toCreateBarCodeUrl();
	}

	/**
	 * 生成条形码的url
	 */
	public void createBarCodeUrl() {
		String barurl = "barcode.action?msg="
				+ airtightLogServer.addBarCode(airtightLog);
		System.out.println(barurl);
		MKUtil.writeJSON(barurl);

	}

	/**
	 * 生成ecc200二维码
	 * 
	 * @return
	 */
	public void createMatrixQr() {
		DataMatrix dm = new DataMatrix();
		AirProduct airProduct = airtightLogServer.getAirProductByCode(content);
		if (airProduct == null) {
			MKUtil.writeJSON(false, null, null);
			return;
		}
		dm.setDataToEncode("[)>06(P)" + airProduct.getCustomerNumber()
				+ "}(20P)" + airProduct.getType() + "}(1P)"
				+ airProduct.getMarkId() + "}(S)" + content + "}<(]");
		dm.setSize(170, 90);
		dm.setEncodingMode(0);
		BufferedImage imageTemp = new BufferedImage(dm.getSize().width, dm
				.getSize().height, 13);
		Graphics imgTempGraphics = imageTemp.createGraphics();
		dm.paint(imgTempGraphics);
		dm.invalidate();
		int w = dm.getSize().width;
		int h = dm.getSize().height;
		imgTempGraphics.dispose();
		BufferedImage BarImage = new BufferedImage(w, h, 1);
		Graphics2D BarGraphics = BarImage.createGraphics();
		dm.setSize(w, h);
		dm.paint(BarGraphics);
		dm.setSize(w, h);
		OutputStream outb = null;
		GifEncoder encoder = null;
		try {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/qrcode");
			File file1 = new File(path);
			if (!file1.exists()) {
				file1.mkdir();
			}
			String dataString = Util.getDateTime("yyyyMMddHHmmss");
			File file = new File(path + "/" + dataString + ".jpg");
			if (!file.exists()) {
				file.createNewFile();
				outb = new FileOutputStream(file);
				encoder = new GifEncoder(BarImage, outb);
				encoder.encode();
			} else {
				file.delete();
				file.createNewFile();
				outb = new FileOutputStream(file);
				encoder = new GifEncoder(BarImage, outb);
				encoder.encode();
			}
			MKUtil.writeJSON(true,
					"/upload/file/qrcode/" + dataString + ".jpg", airProduct);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getProductMarkId() {
		List<String> markIdList = airtightLogServer.getProductMarkId();
		MKUtil.writeJSON(markIdList);
	}

	
	/**
	 * 分页显示气密机器
	 * 
	 * @return
	 */
	public String showMachineList() {
		if (airMachine != null) {
			ActionContext.getContext().getSession().put("airMachine",
					airMachine);
		} else {// 用来保持分页时带着查询条件
			airMachine = (AirMachine) ActionContext.getContext().getSession()
					.get("airMachine");
		}
		Map<Integer, Object> map = airtightLogServer
				.findAirMachinesByCondition(airMachine,
						Integer.parseInt(cpage), pageSize);
		airMachineList = (List<AirMachine>) map.get(1);// 显示页的气密记录列表
		if (airMachineList != null & airMachineList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("airtightLogAction_showMachineList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "airMachine_show";
	}
	public String toAddMachine(){
		return "airMachine_add";
	}
	
	public String addMachine() {
		if (airtightLogServer.addMachine(airMachine)) {
			errorMessage="添加成功!";
			url="airtightLogAction_showMachineList.action";
		}else{
			errorMessage = "添加失败!";
		}
		return "error";
	}
	public String toUpdateMachine(){
		airMachine = airtightLogServer.getAirMachieById(id);
		if(airMachine!=null){
			return "airMachine_update";
		}
		errorMessage="对不起,没有找到目标机器!";
		return "error";
	}
   public String updateMachine(){
	   if (airtightLogServer.updateMachine(airMachine)) {
			errorMessage="修改成功!";
			url="airtightLogAction_showMachineList.action";
		}else{
			errorMessage = "修改失败!";
		}
		return "error";
   }
   public String deleteMachineById(){
	   if (id!=null&&airtightLogServer.deleteMachine(id)) {
			errorMessage="删除成功!";
			url="airtightLogAction_showMachineList.action";
		}else{
			errorMessage = "删除失败!";
		}
		return "error";
   }
	public void setAirtightLog(AirtightLog airtightLog) {
		this.airtightLog = airtightLog;
	}

	public List<AirtightLog> getAirtightLogList() {
		return airtightLogList;
	}

	public void setAirtightLogList(List<AirtightLog> airtightLogList) {
		this.airtightLogList = airtightLogList;
	}

	public AirtightLogServer getAirtightLogServer() {
		return airtightLogServer;
	}

	public void setAirtightLogServer(AirtightLogServer airtightLogServer) {
		this.airtightLogServer = airtightLogServer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static String getQrUrl() {
		return qrUrl;
	}

	public static void setQrUrl(String qrUrl) {
		AirtightLogAction.qrUrl = qrUrl;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int[] getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(int[] checkboxs) {
		this.checkboxs = checkboxs;
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getHadChecked() {
		return hadChecked;
	}

	public void setHadChecked(String hadChecked) {
		this.hadChecked = hadChecked;
	}

	public AirProduct getAirProduct() {
		return airProduct;
	}

	public void setAirProduct(AirProduct airProduct) {
		this.airProduct = airProduct;
	}

	public List<String> getMarkIds() {
		return markIds;
	}

	public void setMarkIds(List<String> markIds) {
		this.markIds = markIds;
	}

	public List<AirProduct> getAirProductList() {
		return airProductList;
	}

	public void setAirProductList(List<AirProduct> airProductList) {
		this.airProductList = airProductList;
	}

	public AirMachine getAirMachine() {
		return airMachine;
	}

	public void setAirMachine(AirMachine airMachine) {
		this.airMachine = airMachine;
	}

	public List<AirMachine> getAirMachineList() {
		return airMachineList;
	}

	public void setAirMachineList(List<AirMachine> airMachineList) {
		this.airMachineList = airMachineList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AirtightLog getAirtightLog() {
		return airtightLog;
	}

	public String getAirMarkId() {
		return airMarkId;
	}

	public void setAirMarkId(String airMarkId) {
		this.airMarkId = airMarkId;
	}

}
