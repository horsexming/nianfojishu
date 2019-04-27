package com.task.action.bargain;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.bargain.BargainServer;
import com.task.entity.approval.Signature;
import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.BarContractDetails;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainGoods;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.bargain.Company;
import com.task.entity.bargain.CompanyVO;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.QuotedPrice;
import com.task.entity.sop.OutSourcingApp;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;

@SuppressWarnings("unchecked")
public class BargainAction extends ActionSupport {
	private BargainServer bargainServer;
	private Bargain bargain;
	private String markId;
	private BarContract barContract;
	private BarContractDetails barContractDetails;
	private List<BarContractDetails> barContractDetailsList;
	private BargainGoods goods;
	private Company company;
	private List<BargainGoods> listgoods;
	private List<Company> listcompany;
	private List<BargainingDetails> listDetail;
	private BargainingDetails bargainingDetails;
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<Map> maps;
	private List list;
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private Map<String, Object> map;
	private String test;
	private List<CompanyVO> listCompanyVO;
	private Integer pay_id;
	private String source;
	private String bargain_source;
	private String bargain_num;
	private String selected_status;
	private String contract_source;
	private String contract_num1;
	private String contract_name;
	private Float heji = 0F;
	private String status;
	private Float money1;
	private Integer contract_id;

	private File address;
	private String addressFileName;
	private String addressContextType;
	private File contract_affix;
	private String gx_number;
	private String gx_type;
	private String quotedNumber;
	private ProjectManage projectManage;
	private Performsingle performsingle;
	private PerformsingleDetail performsingleDetail;
	private String ids;

	// 添加询比议价
	public String addBargain() {
		if (this.bargainServer.addBargain(this.bargain, this.listgoods,
				this.listcompany, selected_status)) {
			successMessage = "申请成功!";
			return "addBargain";
		} else {
			errorMessage = "请检查数据的有效性!";
			return ERROR;
		}
	}

	// 修改询比议价
	public String updateBargain() {
		this.bargainServer.updateBargain(this.bargain, this.listgoods,
				this.listcompany, this.selected_status);
		return "updateBargain";
	}

	// 查询所有询比议价明细
	public String findBargain() {
		if (bargain != null) {
			ActionContext.getContext().getSession().put("bargain", bargain);
		} else {
			bargain = (Bargain) ActionContext.getContext().getSession().get(
					"bargain");
		}
		if (markId != null) {
			ActionContext.getContext().getSession().put("bargainmarkId", markId);
		} else {
			markId = (String) ActionContext.getContext().getSession().get(
			"bargainmarkId");
		}
		Object[] object = this.bargainServer.findBargain(bargain, Integer
				.parseInt(cpage), pageSize, test, markId);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("bargainAction_findBargain.action?test="
						+ this.test);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findBargain";

	}

	// 询比议价申请审批
	public String findExamList() {
		Object[] obj = bargainServer.findExamList(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("bargainAction_findExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamList";
	}

	// 修改及查看询比议价
	public String salBargain() {
		this.map = this.bargainServer.findBargainById(this.bargain);
		if (map.size() > 0) {
			this.bargain = (Bargain) map.get("bargain");
			this.listCompanyVO = (List<CompanyVO>) map.get("companyVOs");
			this.listgoods = (List<BargainGoods>) map.get("bargainGoods");
			// this.listDetail = (List<BargainingDetails>) map.get("details");
			// this.listcompany = (List<Company>) map.get("companies");
			if (!"".equals(test) && test != null) {
				return "salBargain1";// 预览
			} else {
				return "salBargain2";// 修改
			}
		}
		return null;

	}

	// 查看询比议价对应审批节点人
	public void findBargain_ExecutionNode() {
		maps = this.bargainServer.findBargain_ExecutionNode(this.pay_id);
		MKUtil.writeJSON(true, "", maps);// 把结果传到页面
	}

	// 删除询比议价
	public String delBargain() {
		this.bargainServer.delBargain(this.bargain);
		return "delBargain";
	}

	// 审批(通过、驳回)
	public String updateExamBargain() {
		try {
			if (bargainServer.updateExamBonus(detailSelect, tag)) {
				return "updateExamBargain";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 查询各审批意见
	public void findExecutionOpinion() {
		list = bargainServer.findExecutionOpinion(pay_id);
		if (list.size() > 0) {
			MKUtil.writeJSON(true, "", list);// 把结果传到页面
		} else {
			MKUtil.writeJSON(false, "", null);// 把结果传到页面
		}

	}

	// 根据议价来源查询议价单号(询比议价缘由)
	public void findbargainSource() {
		if (source != null && !"".equals(source)) {
			if ("OA".equals(source)) {
				// 查询OA单号
				list = bargainServer.findbargainSource1(source);
			}
			if ("SB".equals(source)) {
				// 查询设备维修
				list = bargainServer.findbargainSource2(source);
			}
			if ("KVP".equals(source)) {
				// 查询KVP
				list = bargainServer.findbargainSource3(source);
			}
			if ("XBYJ".equals(source)) {
				// 查询询比议价
				list = bargainServer.findbargainSource5(source);
			}
			if ("QT".equals(source)) {
				// 其他
				list = new ArrayList();
				list.add("临时采购");
				list.add("零部件及工序外委采购");
				list.add("原材料采购");
				// list.add("工装采购");
			}
			if (list.size() > 0) {
				MKUtil.writeJSON(list);// 把结果传到页面
			} else {
				MKUtil.writeJSON(null);// 把结果传到页面
			}
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据议价来源查询议价单号(合同缘由)
	public void findbargainSource1() {
		list = new ArrayList();
		if (source != null && !"".equals(source)) {
			if ("OA".equals(source)) {
				// 查询OA单号
				list = bargainServer.findbargainSource1(source);
			}
			if ("SB".equals(source)) {
				// 查询设备维修
				list = bargainServer.findbargainSource2(source);
			}
			if ("KVP".equals(source)) {
				// 查询KVP
				list = bargainServer.findbargainSource3(source);
			}
			if ("XBYJ".equals(source)) {
				// 查询询比议价
				list = bargainServer.findbargainSource5(source);
			}
			if ("设备".equals(source)) {
				// 查询设备
				list = bargainServer.findbargainSource11();
			}
			if ("紧急采购".equals(source)) {
				// 其他
				list.add("紧急采购");
			}
			if ("行政事务采购".equals(source)) {
				// 其他
				list.add("行政事务采购");
			}
			if ("原材料采购".equals(source)) {
				// 其他
				list.add("原材料采购");
				// list.add("板料");
				// list.add("管料");
			}
			if ("零部件及工序外委采购".equals(source)) {
				// 获取完成的未进行过询比议价的外购外委评审的记录
				list = bargainServer.findOsaList();
//				System.out.println(list.size());
				// 其他
				// list.add("零部件及工序外委采购");
				// 零部件及工序外委采购
				// list = bargainServer.findbargainSource9(source);
			}
			if ("外购工装".equals(source)) {
				// 其他
				list = bargainServer.findbargainSource6();
				// 零部件及工序外委采购
				// list = bargainServer.findbargainSource9(source);
			}
			if (list.size() > 0) {
				MKUtil.writeJSON(list);// 把结果传到页面
			} else {
				MKUtil.writeJSON(null);// 把结果传到页面
			}
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据议价来源查询议价单号(执行缘由)
	public void findbargainSource2() {
		list = new ArrayList();
		if (source != null && !"".equals(source)) {
			if ("OA".equals(source)) {
				// 查询OA单号
				// list = bargainServer.findbargainSource1(source);
				// 查询询比议价中未OA的单号
				list = this.bargainServer.findBargainSource(source);

			}
			if ("SB".equals(source)) {
				// 查询设备维修
				// list = bargainServer.findbargainSource2(source);
				// 从询比议价中查询为设备维修的单号
				list = this.bargainServer.findBargainSource(source);
			}
			if ("KVP".equals(source)) {
				// 查询KVP
				list = bargainServer.findbargainSource3(source);
			}
			if ("XBYJ".equals(source)) {
				// 查询询比议价
				list = bargainServer.findbargainSource5(source);
			}
			if ("紧急采购".equals(source)) {
				// 其他
				list.add("紧急采购");
			}
			if ("原材料采购".equals(source)) {
				// 其他
				// list.add("原材料采购");
				list.add("板料");
				list.add("管料");
			}
			if ("包装物".equals(source)) {
				list.add("包装物");
			}
			if ("设备".equals(source)) {
				// 查询设备
				// list = bargainServer.findbargainSource11();
				// 从询比议价中查询为设备单号
				list = this.bargainServer.findBargainSource(source);
			}

			if ("零部件及工序外委采购".equals(source)) {
				// 其他
				// list.add("零部件及工序外委采购");
				// 零部件及工序外委采购
				list = bargainServer.findBargainwgww();
				// list = bargainServer.findbargainSource9(source);
			}
			MKUtil.writeJSON(list);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据议价单号查询外委评审
	public void findbargainNumber() {
		list = this.bargainServer.findbargainNumber();
		if (list.size() > 0) {
			MKUtil.writeJSON(list);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据件号查询型别
	public void findbargainNumber1() {
		list = this.bargainServer.findbargainNumber1(gx_number);
		if (list.size() > 0) {
			MKUtil.writeJSON(list);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据件号查询生产类型
	public void findbargainNumber2() {
		list = this.bargainServer.findbargainNumber2(this.gx_number);
		if (list.size() > 0) {
			MKUtil.writeJSON(list);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据件号查询询价单号
	public void findbargainNumber3() {
		list = this.bargainServer.findbargainNumber3(this.gx_number);
		if (list.size() > 0) {
			QuotedPrice quotedPrice = (QuotedPrice) list.get(0);
			MKUtil.writeJSON(quotedPrice);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据询价单号查询项目编号
	public void findbargainNumber4() {
		list = this.bargainServer.findbargainNumber4(this.quotedNumber);
		if (list.size() > 0) {
			ProjectManage projectManag = (ProjectManage) list.get(0);
			MKUtil.writeJSON(projectManag);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 根据件号下拉名称
	public void findbargainNumber5() {
		
		list = this.bargainServer.findbargainNumber5(this.gx_number);
		if (list.size() > 0) {
			OutSourcingApp outSourcingApp = (OutSourcingApp) list.get(0);
			MKUtil.writeJSON(outSourcingApp);// 把结果传到页面
		} else {
			MKUtil.writeJSON(null);// 把结果传到页面
		}
	}

	// 跳转到添加询比议价页面
	public String jumpaddBargain() {
		// System/bargain/addbargain.jsp?test=1
		test = "1";
		bargain = bargainServer.getPageBargain(bargain, ids);
		if (bargain == null) {
			errorMessage = "填写有误,请重新填写!";
			return "error";
		}
		return "jumpaddBargain";
	}

	// 跳转到添加合同管理页面
	public String jumpaddBargain1() {
		if (!"".equals(contract_num1)) {
			if ("OA".equals(contract_source)) {
				Object[] object = bargainServer
						.findbargainSource7(contract_num1);
				if (object != null && object.length > 0) {
					list = (List) object[0];
				} else {
					errorMessage = "无法获取该条采购执行单，请检查后重试";
					return "error";
				}
				performsingle = bargainServer.findPerformsingle(contract_num1);
				for (int i = 0; i < list.size(); i++) {
					performsingleDetail = (PerformsingleDetail) list.get(i);
					if (performsingleDetail.getDetailCount() == null
							|| performsingleDetail.getDetailCount().equals("")
							|| performsingleDetail.getDetailBudgetMoney() == null
							|| performsingleDetail.getDetailBudgetMoney()
									.equals("")) {
						heji = 0F;
					} else {
						heji += performsingleDetail.getDetailCount()
								* performsingleDetail.getDetailBudgetMoney();
					}
				}
			}
			if ("SB".equals(contract_source)) {
				Object[] object = bargainServer
						.findbargainSource7(contract_num1);
				list = (List) object[0];
				// performsingle =
				// bargainServer.findPerformsingle(contract_num1);
				for (int i = 0; i < list.size(); i++) {
					performsingleDetail = (PerformsingleDetail) list.get(i);
					if (performsingleDetail.getMacrepair_amount() == null
							&& performsingleDetail.getMacrepair_amount()
									.equals("")
							&& performsingleDetail.getMacrepair_money() == null
							&& performsingleDetail.getMacrepair_money().equals(
									"")) {
						this.heji = 0F;
					} else {
						this.heji += Float.parseFloat(performsingleDetail
								.getMacrepair_amount())
								* Float.parseFloat(performsingleDetail
										.getMacrepair_money());
					}
				}
				// this.performsingleDetail =
				// this.bargainServer.findPerformsingle1(contract_num1);

			}
			if ("KVP".equals(contract_source)) {
				// 根据kvp编号查询kvp
				Object[] object = bargainServer
						.findbargainSource8(contract_num1);
				list = (List) object[0];
				performsingle = bargainServer.findPerformsingle(contract_num1);
			}
			if ("XBYJ".equals(contract_source)) {
				Object[] object = bargainServer
						.findbargainSource6(contract_num1);
				list = (List) object[0];
				money1 = (Float) object[1];
				performsingle = bargainServer.findPerformsingle(contract_num1);

				for (int i = 0; i < list.size(); i++) {
					BargainGoods bargainGoods = (BargainGoods) list.get(i);
					if (bargainGoods.getGoods_amount() == null
							|| money1 == null
							|| bargainGoods.getGoods_amount().equals("")) {
						heji = 0F;
					} else {
						heji += Integer
								.parseInt(bargainGoods.getGoods_amount())
								* money1;
					}
					bargainGoods.setPrice(money1);
				}
				if (heji <= 0) {
					heji = money1;
				}

			}
			try {
				contract_source = java.net.URLDecoder.decode(contract_source,
						"UTF-8");
				contract_num1 = java.net.URLDecoder.decode(contract_num1,
						"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if ("原材料采购".equals(contract_source)
					|| "包装物".equals(contract_source)) {
				// 查询原材料
				list = bargainServer.findbargainSource10(contract_num1);
				// 查询执行单
				performsingle = bargainServer.findPerformsingle(contract_num1);
			}
			if ("设备".equals(contract_source)) {
				// 设备
				list = bargainServer.findbargainSource10(contract_num1);
				// 查询执行单
				performsingle = bargainServer.findPerformsingle(contract_num1);
			}
			if ("零部件及工序外委采购".equals(contract_source)) {
				// 设备
				list = bargainServer.findbargainSource10(contract_num1);
				if (list != null) {
					// for (int i = 0; i < list.size(); i++) {
					// PerformsingleDetail detail = (PerformsingleDetail) list
					// .get(i);
					// System.out.println(detail.getGx_number());
					// System.out.println(detail.getGx_name());
					// System.out.println(detail.getGx_price());
					// System.out.println(detail.getGx_producetype());
					// System.out.println(detail.getGx_quotedNumber());
					// System.out.println(detail.getGx_goodstype());
					// }
					// 查询执行单
					performsingle = bargainServer
							.findPerformsingle(contract_num1);
				}
			}
			if ("QT".equals(contract_source)) {
				// 其他

			}

		}
		return "jumpaddBargain1";
	}

	// 添加采购合同
	public String addBarContract() {
		// 上传合同附件
		// if(this.contract_affix !=null){
		// //文件路径
		// String fileType =
		// addressFileName.substring(addressFileName.lastIndexOf("."),
		// addressFileName.length());
		// String realFileName=Util.getDateTime("yyyyMMddHHmmss")+fileType;
		// String realFilePath="/upload/barContract";
		// Upload upload=new Upload();
		// upload.UploadFile(contract_affix,addressFileName, realFileName,
		// realFilePath, null);
		// if(barContract==null){
		// barContract=new BarContract();
		// }
		// this.barContract.setContract_affix("/upload/barContract/"+realFileName);
		// }

		boolean bool = bargainServer.saveBarContract(barContract,
				barContractDetailsList,contract_id);
		if (bool) {
			return "addBarContract";
		} else {
			return ERROR;
		}
	}

	// 合同列表
	public String findBarContract() {
		if (barContract != null) {
			ActionContext.getContext().getSession().put("barContract",
					barContract);
		} else {
			barContract = (BarContract) ActionContext.getContext().getSession()
					.get("barContract");
		}
		Object[] object = bargainServer.findBarContract(barContract, Integer
				.parseInt(cpage), pageSize, test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("bargainAction_findBarContract.action?test="
						+ this.test);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findBarContract";
	}

	// 采购合同审批列表
	public String findContractExamList() {
		Object[] obj = bargainServer.findContractExamList(Integer
				.parseInt(cpage), pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("bargainAction_findContractExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			// this.setUrl("huikuanAction!findExamList.action?tag="+this.tag);
			this.setTotal(pageCount + "");
		}
		return "findContractExamList";
	}

	// 采购合同审批
	public String updateContractExamList() {
		try {
			if (bargainServer.updateContractExamList(detailSelect, tag)) {
				return "updateContractExamList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}
	public void getBargain1(){
			barContract =bargainServer.getbarContract(contract_id);
			if(barContract!=null){
				try {
					MKUtil.writeJSON(barContract);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
	}
	// 查看合同
	public String salBarContract() {
		// 查询合同
		barContract = this.bargainServer.salBarContract(barContract);
		// 查询合同明细
		this.list = this.bargainServer.salBarContractDetail(barContract);

		// 查询执行单
		performsingle = bargainServer.findPerformsingle(barContract
				.getContract_num1());

		if (barContract != null && list != null) {
			if (this.status != null && !"".equals(status)) {
				if ("OA".equals(barContract.getContract_source())) {
					// 查询合同明细
					this.barContractDetailsList = this.bargainServer
							.salContractDetails(barContract.getId());
					if (barContractDetailsList.size() > 0) {
						for (int i = 0; i < barContractDetailsList.size(); i++) {
							this.barContractDetails = barContractDetailsList
									.get(i);
							if (barContractDetails.getDetailItemId() != null
									&& !"".equals(barContractDetails
											.getDetailItemId())) {
								// 通过项目编号查询项目
								projectManage = this.bargainServer
										.salProjectManage(barContractDetails
												.getDetailItemId());
								// projectManage=null;
							}
						}
					}
				}
				return "printBarContract";
			} else {
				return "salBarContract";
			}
		} else {
			return ERROR;
		}
	}

	// 查询电子签名
	public void findContractExecutionNode() {
		Map<Integer, Object> map = this.bargainServer
				.findContractExecutionNode(this.contract_id);
		List<Signature> sigList = (List<Signature>) map.get(1);
		List<ExecutionNode> nodeList = (List<ExecutionNode>) map.get(2);
		MKUtil.writeJSON(true, "", nodeList, sigList);// 把结果传到页面

	}

	// 修改合同
	public String updateBarContract() {
		boolean bool = this.bargainServer.updateBarContract(barContract,
				barContractDetailsList);
		if (bool) {
			return "updateBarContract";
		} else {
			return ERROR;
		}
	}

	// 删除合同
	public String delBarContract() {
		boolean bool = this.bargainServer.delBarContract(barContract);
		if (bool) {
			return "delBarContract";
		} else {
			return ERROR;
		}
	}

	/***
	 * 查询当前登录用户对应的合同
	 */
	public void getLoginHeTong() {
		List list = bargainServer.getLoginHeTong();
		MKUtil.writeJSON(list);
	}

	public BargainingDetails getBargainingDetails() {
		return bargainingDetails;
	}

	public void setBargainingDetails(BargainingDetails bargainingDetails) {
		this.bargainingDetails = bargainingDetails;
	}

	public BargainServer getBargainServer() {
		return bargainServer;
	}

	public void setBargainServer(BargainServer bargainServer) {
		this.bargainServer = bargainServer;
	}

	public Bargain getBargain() {
		return bargain;
	}

	public void setBargain(Bargain bargain) {
		this.bargain = bargain;
	}

	public BargainGoods getGoods() {
		return goods;
	}

	public void setGoods(BargainGoods goods) {
		this.goods = goods;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<BargainGoods> getListgoods() {
		return listgoods;
	}

	public void setListgoods(List<BargainGoods> listgoods) {
		this.listgoods = listgoods;
	}

	public List<Company> getListcompany() {
		return listcompany;
	}

	public void setListcompany(List<Company> listcompany) {
		this.listcompany = listcompany;
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

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<BargainingDetails> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<BargainingDetails> listDetail) {
		this.listDetail = listDetail;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<CompanyVO> getListCompanyVO() {
		return listCompanyVO;
	}

	public void setListCompanyVO(List<CompanyVO> listCompanyVO) {
		this.listCompanyVO = listCompanyVO;
	}

	public Integer getPay_id() {
		return pay_id;
	}

	public void setPay_id(Integer payId) {
		pay_id = payId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBargain_source() {
		return bargain_source;
	}

	public void setBargain_source(String bargainSource) {
		bargain_source = bargainSource;
	}

	public String getBargain_num() {
		return bargain_num;
	}

	public void setBargain_num(String bargainNum) {
		bargain_num = bargainNum;
	}

	public String getSelected_status() {
		return selected_status;
	}

	public void setSelected_status(String selectedStatus) {
		selected_status = selectedStatus;
	}

	public BarContract getBarContract() {
		return barContract;
	}

	public void setBarContract(BarContract barContract) {
		this.barContract = barContract;
	}

	public String getContract_source() {
		return contract_source;
	}

	public void setContract_source(String contractSource) {
		contract_source = contractSource;
	}

	public String getContract_num1() {
		return contract_num1;
	}

	public void setContract_num1(String contractNum1) {
		contract_num1 = contractNum1;
	}

	public Float getHeji() {
		return heji;
	}

	public void setHeji(Float heji) {
		this.heji = heji;
	}

	public BarContractDetails getBarContractDetails() {
		return barContractDetails;
	}

	public void setBarContractDetails(BarContractDetails barContractDetails) {
		this.barContractDetails = barContractDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getMoney1() {
		return money1;
	}

	public void setMoney1(Float money1) {
		this.money1 = money1;
	}

	public List<BarContractDetails> getBarContractDetailsList() {
		return barContractDetailsList;
	}

	public void setBarContractDetailsList(
			List<BarContractDetails> barContractDetailsList) {
		this.barContractDetailsList = barContractDetailsList;
	}

	public Integer getContract_id() {
		return contract_id;
	}

	public void setContract_id(Integer contractId) {
		contract_id = contractId;
	}

	public File getAddress() {
		return address;
	}

	public void setAddress(File address) {
		this.address = address;
	}

	public String getAddressFileName() {
		return addressFileName;
	}

	public void setAddressFileName(String addressFileName) {
		this.addressFileName = addressFileName;
	}

	public String getAddressContextType() {
		return addressContextType;
	}

	public void setAddressContextType(String addressContextType) {
		this.addressContextType = addressContextType;
	}

	public File getContract_affix() {
		return contract_affix;
	}

	public void setContract_affix(File contractAffix) {
		contract_affix = contractAffix;
	}

	public String getGx_number() {
		return gx_number;
	}

	public void setGx_number(String gxNumber) {
		gx_number = gxNumber;
	}

	public String getGx_type() {
		return gx_type;
	}

	public void setGx_type(String gxType) {
		gx_type = gxType;
	}

	public String getQuotedNumber() {
		return quotedNumber;
	}

	public void setQuotedNumber(String quotedNumber) {
		this.quotedNumber = quotedNumber;
	}

	public ProjectManage getProjectManage() {
		return projectManage;
	}

	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}

	public Performsingle getPerformsingle() {
		return performsingle;
	}

	public void setPerformsingle(Performsingle performsingle) {
		this.performsingle = performsingle;
	}

	public PerformsingleDetail getPerformsingleDetail() {
		return performsingleDetail;
	}

	public void setPerformsingleDetail(PerformsingleDetail performsingleDetail) {
		this.performsingleDetail = performsingleDetail;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getContract_name() {
		return contract_name;
	}

	public void setContract_name(String contractName) {
		contract_name = contractName;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	
}
