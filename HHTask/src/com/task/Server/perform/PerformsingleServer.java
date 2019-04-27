package com.task.Server.perform;

import java.util.List;

import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.QuotedPrice;
import com.task.entity.sop.OutSourcingApp;

public interface PerformsingleServer {

	/***
	 * 查询OA单号
	 * @param bargainNum
	 * @return
	 */
	List findbargainSource1(String bargainNum);

	/***
	 * 根据设备单号查询设备
	 * @param bargainNum
	 * @return
	 */
	Object[] findbargainSource2(String bargainNum);

	/***
	 * 根据kvp编号查询kvp
	 * @param bargainNum
	 * @return
	 */
	Object[] findbargainSource3(String bargainNum);

	/***
	 * 根据议价单号查询询比议价
	 * @param bargainNum
	 * @return
	 */
	Object[] findbargainSource4(String bargainNum);

	/***
	 * 添加采购执行单
	 * @param performsingle
	 * @param performsingleDetails
	 * @return
	 */
	boolean addPerformsingle(Performsingle performsingle,
			List<PerformsingleDetail> performsingleDetails,List<OutSourcingApp> list);

	/***
	 * 查询采购申请单
	 * @param performsingle
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findPerformsingle(Performsingle performsingle, int parseInt,
			int pageSize,String tag);

	/***
	 * 删除采购执行单
	 * @param performsingle
	 * @return
	 */
	boolean delPerformsingle(Performsingle performsingle,List<OutSourcingApp> list);

	/***
	 * 根据单号查询OA
	 * @param performsingle
	 * @return
	 */
	Object[] salPerformsingle(Performsingle performsingle);

	/***
	 * 获取项目对象
	 * @param performsingle
	 * @return
	 */
	QuotedPrice getQuotedPrice(Performsingle performsingle);

	/***
	 * 采购执行单审批
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findContractExamList(int parseInt, int pageSize);

	/***
	 * 采购执行单审批
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateContractExamList(Integer[] detailSelect, String tag);

	/***
	 * 获取设备询比议价
	 * @param performsingle
	 * @return
	 */
	Bargain findbargain(Performsingle performsingle);

	QuotedPrice getQuotedPrice1(Performsingle performsingle);

	/***
	 * 根据外委申请编号查询外购外委评审
	 * @param bargainNum
	 * @return
	 */
	Object[] findbargainSource5(String bargainNum);

	/***
	 * 查询原材料的明细
	 * @param bargainNum
	 * @return
	 */
	List findbargainSource6(String bargainNum);

	/***
	 * 查看设备明细
	 * @param bargainNum
	 * @return
	 */
	List findbargainSource7(String bargainNum);

	BargainingDetails findBargainprice(String bargainNum);
    /**
     * 通过id获取询比议价商品信息
     * @param ids
     * @return
     */
	List findDGoodsListByIds(String ids);
	
	List<Performsingle> findPerformsingle1(String tag);

}
