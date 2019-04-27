package com.task.action.yw;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.yw.IBusinessService;
import com.task.Server.yw.IBusinessSubsidiaryService;
import com.task.Server.yw.IExamineFlowService;
import com.task.Server.yw.IInvoiceService;
import com.task.Server.yw.IPrintProofService;
import com.task.Server.yw.ISellService;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.ServerImpl.yw.FileUtil;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.Business;
import com.task.entity.BusinessSubsidiary;
import com.task.entity.BusinessSubsidiaryResult;
import com.task.entity.ExamineFlow;
import com.task.entity.Invoice;
import com.task.entity.PrintProof;
import com.task.entity.Sell;
import com.task.entity.Users;

/**
 * @Title: BusinessAction.java
 * @Package com.task.action.yw
 * @Description: TODO 业务Action
 * @author 曾建森
 * @date 2012-10-29 下午12:47:08
 * @version V1.0
 */
public class BusinessAction extends ActionSupport implements ServletResponseAware{
	
	private Set<Invoice> invoices;
	private ExamineFlow ef;
	private Business bu;
	private Invoice invoice;
	private IBusinessService bus;
	private IBusinessSubsidiaryService bss;
	private IInvoiceService ins;
	private ISellService sel;
	private IPrintProofService pps;
	private IExamineFlowService efs;
	private List list;
	private String errorMessage;
	private File myFile;
	private String myFileFileName;
	private String id;
	private String type;
	private String content;
	private String collectionUnit;
	private String transactor;
	private String beginTime;
	private String endTime;
	private HttpServletResponse response;
	private String buId;
	private String inId;
	private int[] selected;
	private Sell sell;
	private String flow;
	private String role;
	private String resultStr;
	private int ifAgree;
	private String condition;
	private String status;
	private String way;
	private int num = 1;
	private String mark;
	private PrintProof pp;
	private List<Business> lis;
	private float mony;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	/**
	 * @Title: initializeBusiness
	 * @Description: 初始化业务信息
	 * @return String
	 * @throws
	 */	
	public String initializeBusiness(){
		if (flow != null) {
			resultStr = "audit_index";
			if (flow.equals("A")) {
				role = "物流经理";
			}
			if (flow.equals("B")) {
				role = "副总经理";
			}
			if (flow.equals("C")) {
				role = "总经理";
			}
		} else {
			resultStr = "business_index";
		}
		Object[] object = bus.queryAllBusiness(Integer.parseInt(cpage),pageSize,flow);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_initializeBusiness.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return resultStr;
	}
	/**
	 * @Title: queryByCondition
	 * @Description: 根据条件查询业务内容
	 * @return String
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String queryByCondition(){
		if (flow != null) {
			resultStr = "audit_index";
			if (flow.equals("A")) {
				role = "物流经理";
			}
			if (flow.equals("B")) {
				role = "副总经理";
			}
			if (flow.equals("C")) {
				role = "总经理";
			}
		} else {
			resultStr = "business_index";
		}
		Map map = new HashMap();
		if(type != null && !type.equals("") && !type.equals("选择类型")){
			map.put("type", type);
		}
		if(content != null && !content.equals("")){
			map.put("content", content);
		}
		if(collectionUnit != null && !collectionUnit.equals("")){
			map.put("collectionUnit", collectionUnit);
		}
		if(transactor != null && !transactor.equals("")){
			map.put("transactor", transactor);
		}
		if(beginTime != null && !beginTime.equals("")){
			map.put("beginTime", beginTime);
		}
		if(endTime != null && !endTime.equals("")){
			map.put("endTime", endTime);
		}
		if(map.size() > 0 && (beginTime != null && endTime != null)){
			ActionContext.getContext().getSession().put("condition", map);
		}else{
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		Object[] object = bus.queryByCondition(map, Integer.parseInt(cpage), pageSize,flow);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_queryByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return resultStr;
	}
	/**
	 * @Title: auditProcess
	 * @Description: 审核结果
	 * @return String
	 * @throws
	 */
	public String auditProcess(){
		if(id == null){
			Map map = new HashMap();
			map.put("flow", flow);
			ResponseUtil.write(response, "审核失败！请重新审核。", "business_initializeBusiness.action", map);
			return null;
		}
		if(flow != null){
			if (flow.equals("A")) {
				role = "物流经理";
			}
			if (flow.equals("B")) {
				role = "副总经理";
			}
			if (flow.equals("C")) {
				role = "总经理";
			}
		}
		Users users = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
		String dept = users.getDept();
		boolean bol = bus.auditBusiness(Integer.parseInt(id), flow, ifAgree,dept);
		if(bol){
			Map map = new HashMap();
			map.put("flow", flow);
			ResponseUtil.write(response, "审核执行完成！", "business_initializeBusiness.action", map);
		}else{
			Map map = new HashMap();
			map.put("flow", flow);
			ResponseUtil.write(response, "审核执行失败！请重新审核。", "business_initializeBusiness.action", map);
		}
		return null;
	}
	/**
	 * @Title: queryAllProof
	 * @Description: 查询所有总经理审核的付款凭证
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String queryAllProof(){
		Object[] object = pps.queryAllPrintProof(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_queryAllProof.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "examine_proof";
	}
	/**
	 * @Title: checkProof
	 * @Description: 查询付款凭证的明细
	 * @return String
	 * @throws
	 */
	public String checkProof(){
		pp = pps.getPrintProofById(Integer.parseInt(id));
		return "detail_proof";
	}
	/**
	 * @Title: proofAgree
	 * @Description: 同意付款凭证
	 * @return String
	 * @throws
	 */
	public String proofAgree(){
		pp = pps.getPrintProofById(Integer.parseInt(id));
		pp.setStatus("Agree");
		pps.update(pp);
		ResponseUtil.write(response, "审核操作成功!谢谢", "business_queryAllProof.action", null);
		return null;
	}
	/**
	 * @Title: proofGoBack
	 * @Description: 打回付款凭证
	 * @return String
	 * @throws
	 */
	public String proofGoBack(){
		pp = pps.getPrintProofById(Integer.parseInt(id));
		pp.setStatus("goBack");
		pps.update(pp);
		ResponseUtil.write(response, "审核操作成功！谢谢。", "business_queryAllProof.action", null);
		return null;
	}
	/**
	 * @Title: queryAgreeProof
	 * @Description: 查询所有审核通过的付款凭证
	 * @return String
	 * @throws
	 */
	public String queryAgreeProof(){
		Object[] object = pps.queryAgreeProof(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_queryAgreeProof.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "agree_proof";
	}
	/**
	 * @Title: queryGoBackProof
	 * @Description: 查询所有打回的付款凭证
	 * @return String
	 * @throws
	 */
	public String queryGoBackProof(){
		Object[] object = pps.queryAgreeProof(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_queryGoBackProof.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "goBack_proof";
	}
	/**
	 * @Title: batchPrintProof
	 * @Description: 批量初始化付款凭证条件
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String batchPrintProof(){
		if(selected != null){
			mony = 0.0f;
			if(selected.length > 0){
				ActionContext.getContext().getSession().put("select", selected);
			}
			lis = new ArrayList();
				for(int i : selected){
					bu = bus.getBusinessById(i);
					mony += bu.getMoney();
					lis.add(bu);
				}
				Users users = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
				String deptId = users.getPassword().getDeptNumber();
				mark = FileUtil.getNum(deptId);
				try {
					role = ConvertNumber.doChangeRMB(mony);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "print_init";
		}
		return null;
	}
	/**
	 * @Title: SaveProof
	 * @Description: 保存付款凭证
	 * @return String
	 * @throws
	 */
	public String saveProof(){
			selected = (int[]) ActionContext.getContext().getSession().get("select");
			if(selected != null && selected.length > 0){
				Set<Business> seleId = new HashSet<Business>();
				pp.setStatus("审核");
				for(int i : selected){
					bu = bus.getBusinessById(i);
					bu.setStatus("凭证审核");
					bu.setPp(pp);
					seleId.add(bu);
				}
				pp.setBuss(seleId);
				pps.add(pp);
					ResponseUtil.write(response, "保存操作成功！谢谢", "business_initPrintProof.action", null);
			}else{
				ResponseUtil.write(response, "保存操作失败！谢谢", "business_initPrintProof.action", null);
			}
			return null;
	}
	/**
	 * @Title: initPrint
	 * @Description: 打印付款凭证
	 * @return String
	 * @throws
	 */
	public String initPrint(){
		if(id != null){
			pp = pps.getPrintProofById(Integer.parseInt(id));
			return "init_printProof";
		}
		return null;
	}
	/**
	 * @Title: initPrintProof
	 * @Description: 查询所有通过的业务
	 * @return String
	 * @throws
	 */
	public String initPrintProof(){
		Object[] object = bus.queryByPass(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("register_initPrintProof.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "init_print";
	}
	/**
	 * @Title: queryDetailByBusinessId
	 * @Description: 根据业务ID查询业务明细
	 * @return String
	 * @throws
	 */
	public String queryDetailByBusinessId(){
		if(id != null){
			ActionContext.getContext().getSession().put("BusinessId", id);
		}else{
			id = (String) ActionContext.getContext().getSession().get("BusinessId");
		}
		Object[] object = bss.queryBusinessSubsidiaryByBusinessId(Integer.parseInt(id), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			List buslist = (List) object[0];
			if (buslist != null && buslist.size() > 0) {
				list = new ArrayList<BusinessSubsidiaryResult>();
				for(int i = 0;i < buslist.size();i++){
					BusinessSubsidiary bs = (BusinessSubsidiary) buslist.get(i);
					Sell se = sel.getSellById(bs.getSellId());
					BusinessSubsidiaryResult bsr = new BusinessSubsidiaryResult(bs.getId(),se.getSellId(),bs.getBusinessId(),se.getSellLot(),
							se.getSellMarkId(),se.getSellGoods(),se.getSellCount(),se.getSellUnit(),se.getSellCompanyName(),se.getSellDate());
					list.add(bsr);
				}
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("business_queryDetailByBusinessId.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "business_detail";
	}
	/**
	 * @Title: querySellByBusinessId
	 * @Description: 根据业务ID查询业务中没有出库明细
	 * @return String
	 * @throws
	 */
	public String querySellByBusinessId(){
		Map map = new HashMap();
		if(sell != null){
			if(sell.getSellLot() != null && !sell.getSellLot().equals("")){
				map.put("lot", sell.getSellLot());
			}
			if(sell.getSellMarkId() != null && !sell.getSellMarkId().equals("")){
				map.put("markId", sell.getSellMarkId());
			}
			if(sell.getSellCompanyName() != null && !sell.getSellCompanyName().equals("")){
				map.put("companyName", sell.getSellCompanyName());
			}
			if(sell.getSellWarehouse() != null && !sell.getSellWarehouse().equals("")){
				map.put("warehouse", sell.getSellWarehouse());
			}
			if(sell.getSellGoods() != null && !sell.getSellGoods().equals("")){
				map.put("goods", sell.getSellGoods());
			}
			if(beginTime != null && !beginTime.equals("")){
				map.put("beginTime", beginTime);
			}
			if(endTime != null && !endTime.equals("")){
				map.put("endTime", endTime);
			}
		}
		if(id != null){
			ActionContext.getContext().getSession().put("BusinessId", id);
		}else{
			id = (String) ActionContext.getContext().getSession().get("BusinessId");
		}
		if(map.size() > 0 ){
			ActionContext.getContext().getSession().put("condition", map);
		}else{
			if (errorMessage == null ) {
				map = (Map) ActionContext.getContext().getSession().get(
							"condition");
			} else
				ActionContext.getContext().getSession().remove("condition");
			}
		Object[] object = sel.querySellByBusinessIdAndCondition(map,Integer.parseInt(id), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("business_querySellByBusinessId.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "business_addDetail";
	}
	/**
	 * @Title: querInvoiceByBusinessID
	 * @Description: 根据业务ID查询所有发票
	 * @return String
	 * @throws
	 */
	public String queryInvoiceByBusinessId(){
		if(id != null){
			ActionContext.getContext().getSession().put("BusinessId", id);
		}else{
			id = (String) ActionContext.getContext().getSession().get("BusinessId");
		}
		Object[] object = ins.queryInvoiceByBusinessId(Integer.parseInt(id), Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("business_queryInvoiceByBusinessId.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "business_invoice";
	}
	/**
	 * @Title: batchAddDetail
	 * @Description: 批量添加出库明细
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String batchAddDetail(){
		StringBuffer str = new StringBuffer();
		str.append("添加明细成功！");
		if(selected != null && selected.length > 0){
			for (int i = 0; i < selected.length; i++) {
				int sellId = (selected[i]);
				bss.add(sellId, Integer.parseInt(id));
			}
		}else{
			str.delete(0, str.length());
			str.append("添加明细失败！");
		}
		Map map = new HashMap();
		map.put("id", id);
		ResponseUtil.write(response, str.toString(), "business_querySellByBusinessId.action", map);
		return null;
	}
	/**
	 * @Title: delDetail
	 * @Description: 根据ID删除业务明细
	 * @return String
	 * @throws
	 */
	public String delDetail(){
		bss.del(Integer.parseInt(inId));
		return "detail_redirect";
	}
	/**
	 * @Title: addInvoice
	 * @Description: 添加发票
	 * @return String
	 * @throws
	 */	 
	public String addInvoice(){
		String fileName = null;
		if(myFile != null && myFileFileName.length() > 0){
			String copyLocation = "D:/WorkSpace/HHTask/WebRoot/upload/paymentBasis";
//			String copyLocation = "D:/Workspaces/MyEclipse 8.6/test/HHTask/WebRoot/upload/paymentBasis";
			fileName = FileUtil.uploadFile(myFile, myFileFileName, "upload/paymentBasis",copyLocation);
			if(fileName != null){
				invoice.setFile(fileName);
			}else{
				ResponseUtil.write(response, "添加发票失败！", "business_initializeBusiness.action", null);
				return null;
			}
		boolean bol = ins.addInvoiceAndUpdateBusinessMoney(Integer.parseInt(id), invoice);
			if(bol){
				ResponseUtil.write(response, "添加发票成功！", "business_initializeBusiness.action", null);
			}else{
				ResponseUtil.write(response, "添加发票失败！", "business_initializeBusiness.action", null);
			}
		}
		return null;
	}
	/**
	 * @Title: initUpdateInvoice
	 * @Description: 初始化发票修改页面
	 * @return String
	 * @throws
	 */
	public String initUpdateInvoice(){
		invoice = ins.getInvoiceById(Integer.parseInt(inId));
		return "invoice_initUpdate";
	}
	/**
	 * @Title: updateInvoice
	 * @Description: 修改发票
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String updateInvoice(){
		if(invoice != null){
			ins.update(invoice);
		}else{
			errorMessage = "修改失败!";
		}
		return "invoice_redirect";
	}
	/**
	 * @Title: delInvoice
	 * @Description: TODO 删除发票
	 * @param @return 
	 * @return String
	 * @throws
	 */
	public String delInvoice(){
		ins.delById(Integer.parseInt(inId));
		return "invoice_redirect";
	}
	/**
	 * @Title: Save
	 * @Description: 保存新业务内容
	 * @return String
	 * @throws
	 */
	public String save(){
		String fileName = null;
		if(myFile != null && myFileFileName.length() > 0 ){
			String copyLocation = "D:/WorkSpace/HHTask/WebRoot/upload/paymentBasis";
//			String copyLocation = "D:/Workspaces/MyEclipse 8.6/test/HHTask/WebRoot/upload/paymentBasis";
			fileName = FileUtil.uploadFile(myFile, myFileFileName, "upload/paymentBasis",copyLocation);
			if(fileName == null){
				errorMessage = "上传文件失败，请重新上传!谢谢";
				return "business_index";
			}else{
				bu.setPaymentBasis(fileName);
			}
		}else{
			errorMessage = "上传文件失败，请重新上传!谢谢";
			return "business_index";
		}
		Users users = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
		bu.setDept(users.getDept());
		bu.setStatus("NO");
		bu.setFlow("物流");
		bus.add(bu);
		return "business_redirect";
	}
	/**
	 * @Title: initUpdate
	 * @Description: 初始化修改的记录
	 * @return String
	 * @throws
	 */
	public String initUpdate(){
		if(id != null){
			int buId = Integer.parseInt(id);
			bu = bus.getBusinessById(buId);
			return "business_update";
		}
		return null;
	}
	/**
	 * @Title: update
	 * @Description: 修改记录
	 * @return String
	 * @throws
	 */
	public String update(){
		bus.update(bu);
		return "business_redirect";
	}
	/**
	 * @Title: del
	 * @Description: 删除记录
	 * @return String
	 * @throws
	 */
	public String del(){
		if(id != null){
			int buId = Integer.parseInt(id);
			if(buId != 0){
				bus.delById(buId);
				return "business_redirect";
			}
		}
		return null;
	}
	/**
	 * @Title: queryExamineFlow
	 * @Description: 查询所有审核流程
	 * @return String
	 * @throws
	 */
	public String queryExamineFlow(){
		Object[] object = efs.queryAllExamineFlow(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("business_queryExamineFlow.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "examine_flow";
	}
	/**
	 * @Title: addExamineFlow
	 * @Description: 添加审核流程
	 * @return String
	 * @throws
	 */
	public String addExamineFlow(){
		if(ef != null){
			efs.add(ef);
		}
		return "examine_redirect";
	}
	/**
	 * @Title: initExamineFlow
	 * @Description: 初始化修改审核流程页面 
	 * @return String
	 * @throws
	 */
	public String initExamineFlow(){
		if(id != null){
			ef = efs.getExamineFlowById(Integer.parseInt(id));
			return "examine_update";
		}
		return null;
	}
	/**
	 * @Title: updateExamineFlow
	 * @Description: 修改审核流程
	 * @return String
	 * @throws
	 */
	public String updateExamineFlow(){
		if(ef != null){
			efs.update(ef);
			return "examine_redirect";
		}
		return null;
	}
	/**
	 * @Title: delExamineFlow
	 * @Description: 删除审核流程
	 * @return String
	 * @throws
	 */
	public String delExamineFlow(){
		if(id != null){
			efs.del(Integer.parseInt(id));
			return "examine_redirect";
		}
		return null;
	}
	
	public int[] getSelected() {
		return selected;
	}
	public void setSelected(int[] selected) {
		this.selected = selected;
	}
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	public String getBuId() {
		return buId;
	}
	public void setBuId(String buId) {
		this.buId = buId;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public IInvoiceService getIns() {
		return ins;
	}
	public void setIns(IInvoiceService ins) {
		this.ins = ins;
	}
	public String getTransactor() {
		return transactor;
	}
	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCollectionUnit() {
		return collectionUnit;
	}
	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IBusinessService getBus() {
		return bus;
	}
	public void setBus(IBusinessService bus) {
		this.bus = bus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
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
	public Business getBu() {
		return bu;
	}
	public void setBu(Business bu) {
		this.bu = bu;
	}
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public ISellService getSel() {
		return sel;
	}
	public void setSel(ISellService sel) {
		this.sel = sel;
	}
	public IBusinessSubsidiaryService getBss() {
		return bss;
	}
	public void setBss(IBusinessSubsidiaryService bss) {
		this.bss = bss;
	}
	public Sell getSell() {
		return sell;
	}
	public void setSell(Sell sell) {
		this.sell = sell;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getResultStr() {
		return resultStr;
	}
	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	public int getIfAgree() {
		return ifAgree;
	}
	public void setIfAgree(int ifAgree) {
		this.ifAgree = ifAgree;
	}
	public Set<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public PrintProof getPp() {
		return pp;
	}
	public void setPp(PrintProof pp) {
		this.pp = pp;
	}
	public IPrintProofService getPps() {
		return pps;
	}
	public void setPps(IPrintProofService pps) {
		this.pps = pps;
	}
	public List<Business> getLis() {
		return lis;
	}
	public void setLis(List<Business> lis) {
		this.lis = lis;
	}
	public float getMony() {
		return mony;
	}
	public void setMony(float mony) {
		this.mony = mony;
	}
	public IExamineFlowService getEfs() {
		return efs;
	}
	public void setEfs(IExamineFlowService efs) {
		this.efs = efs;
	}
	public ExamineFlow getEf() {
		return ef;
	}
	public void setEf(ExamineFlow ef) {
		this.ef = ef;
	}
	
}
