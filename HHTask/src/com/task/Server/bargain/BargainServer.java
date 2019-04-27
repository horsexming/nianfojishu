package com.task.Server.bargain;

import java.util.List;
import java.util.Map;

import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.BarContractDetails;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainGoods;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.bargain.Company;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.ProjectManage;

public interface BargainServer {

	/***
	 * 添加询比议价
	 * 
	 * @param listgoods
	 * @param listcompany
	 * @return
	 */
	boolean addBargain(Bargain bargain, List<BargainGoods> listgoods,
			List<Company> listcompany, String selected_status);

	/**
	 * 查看询比议价
	 * 
	 * @param bargain
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findBargain(Bargain bargain, int parseInt, int pageSize,
			String test,String markId);

	/**
	 * 询比议价审核管理
	 * 
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findExamList(int parseInt, int pageSize);

	/***
	 * 询比议价审批(通过、驳回)
	 * 
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamBonus(Integer[] detailSelect, String tag);

	/**
	 * 修改及查看询比议价
	 * 
	 * @param bargain
	 * @return
	 */
	Map<String, Object> findBargainById(Bargain bargain);

	/**
	 * 查看询比议价对应审批节点人
	 * 
	 * @param payId
	 * @return
	 */
	List<Map> findBargain_ExecutionNode(Integer payId);

	/***
	 * 删除询比议价
	 * 
	 * @param bargain
	 */
	void delBargain(Bargain bargain);

	/**
	 * 修改询比议价
	 * 
	 * @param bargain
	 * @param listgoods
	 * @param listcompany
	 */
	void updateBargain(Bargain bargain, List<BargainGoods> listgoods,
			List<Company> listcompany, String selected_status);

	/***
	 * 查询个节点的审批意见
	 * 
	 * @param payId
	 * @return
	 */
	List findExecutionOpinion(Integer payId);

	/***
	 * 查询OA单号
	 * 
	 * @param source
	 * @return
	 */
	List findbargainSource1(String source);

	/***
	 * 查询设备维修单号
	 * 
	 * @param source
	 * @return
	 */
	List findbargainSource2(String source);

	/***
	 * 查询KVP单号
	 * 
	 * @param source
	 * @return
	 */
	List findbargainSource3(String source);

	List findbargainSource4(String contractNum1);

	/***
	 * 添加OA合同
	 * 
	 * @param barContract
	 * @param barContractDetails
	 * @return
	 */
	boolean saveBarContract(BarContract barContract,
			List<BarContractDetails> barContractDetailList,Integer id);

	/***
	 * 合同列表
	 * 
	 * @param barContract
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findBarContract(BarContract barContract, int parseInt,
			int pageSize, String test);

	/***
	 * 查看合同
	 * 
	 * @param barContract
	 */
	BarContract salBarContract(BarContract barContract);
	/**
	 * 
	 * @param contract_name
	 * @param contract_num1
	 * @param contract_source
	 * @return
	 */
	
	BarContract getbarContract(Integer id);
	/***
	 * 查询明细
	 * 
	 * @param barContract
	 * @return
	 */
	List salBarContractDetail(BarContract barContract);

	/***
	 * 修改合同
	 * 
	 * @param barContract
	 * @return
	 */
	boolean updateBarContract(BarContract barContract,
			List<BarContractDetails> barContractDetailsList);

	/***
	 * 删除合同
	 * 
	 * @param barContract
	 * @return
	 */
	boolean delBarContract(BarContract barContract);

	/***
	 * 查询询比议价
	 * 
	 * @param source
	 * @return
	 */
	List findbargainSource5(String source);

	/***
	 * 根据议价单号查询询比议价
	 * 
	 * @param contractNum1
	 * @return
	 */
	Object[] findbargainSource6(String contractNum1);

	/****
	 * 根据设备单号查询设备
	 * 
	 * @param contractNum1
	 * @return
	 */
	Object[] findbargainSource7(String contractNum1);

	/***
	 * 根据kvp编号查询kvp
	 * 
	 * @param contractNum1
	 * @return
	 */
	Object[] findbargainSource8(String contractNum1);

	/***
	 * 采购合同审批列表
	 * 
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findContractExamList(int parseInt, int pageSize);

	/***
	 * 采购合同审批
	 * 
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateContractExamList(Integer[] detailSelect, String tag);

	/***
	 * 根据合同编号查询电子签名
	 * 
	 * @param contractId
	 * @return
	 */
	Map<Integer, Object> findContractExecutionNode(Integer contractId);

	/***
	 * //根据议价单号查询外委评审
	 * 
	 * @return
	 */
	List findbargainNumber();

	/***
	 * 根据件号查询型别
	 * 
	 * @param gxNumber
	 * @return
	 */
	List findbargainNumber1(String gxNumber);

	/***
	 * 根据件号查询生产类型
	 * 
	 * @param gxType
	 * @return
	 */
	List findbargainNumber2(String gx_number);

	/***
	 * 根据件号查询询价单号
	 * 
	 * @param gxNumber
	 * @return
	 */
	List findbargainNumber3(String gxNumber);

	/***
	 * 根据询价单号查询项目编号
	 * 
	 * @param quotedNumber
	 * @return
	 */
	List findbargainNumber4(String quotedNumber);

	/***
	 * 根据件号下拉名称
	 * 
	 * @param quotedNumber
	 * @return
	 */
	List findbargainNumber5(String gx_number);

	/***
	 * 查询合同明细
	 * 
	 * @param id
	 * @return
	 */
	List<BarContractDetails> salContractDetails(Integer id);

	/***
	 * 通过项目编号查询项目
	 * 
	 * @param detailItemId
	 * @return
	 */
	ProjectManage salProjectManage(String detailItemId);

	/***
	 * 零部件及工序外委采购
	 * 
	 * @param source
	 * @return
	 */
	List findbargainSource9(String source);

	/***
	 * 查询执行单
	 * 
	 * @param contractNum1
	 * @return
	 */
	Performsingle findPerformsingle(String contractNum1);

	/***
	 * 查看原材料明细
	 * 
	 * @param contractNum1
	 * @return
	 */
	List findbargainSource10(String contractNum1);

	/***
	 * 查询设备
	 * 
	 * @return
	 */
	List findbargainSource11();

	/***
	 * 查询询比议价中未OA的单号
	 * 
	 * @param source
	 * @return
	 */
	List findBargainSource(String source);

	PerformsingleDetail findPerformsingle1(String contractNum1);

	List findbargainSource6();

	/**
	 * 获取跳往添加询比议价单页面时询比议价单需要的一些参数
	 * 
	 * @param bargain
	 * @param osaIds
	 * @return
	 */
	Bargain getPageBargain(Bargain bargain,String osaIds);

	/***
	 * 查询登录用户添加的合同信息
	 * 
	 * @return
	 */
	List getLoginHeTong();
	/**
	 * 获取完成的未进行过询比议价的外购外委评审的记录
	 */
	List findOsaList();
   /**
    * 获取没有参与生成过采购执行单的询比议价信息
    * @return
    */
	List findBargainwgww();
}
