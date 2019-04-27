package com.task.Server.oa;

import java.io.File;
import java.util.List;

import com.task.entity.GoodsStore;
import com.task.entity.OaAppDetail;
import com.task.entity.OaPrepareApply;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;

@SuppressWarnings("unchecked")
public interface OAAppDetailServer {
	/**
	 * 查找申报明细
	 * @param oadetail
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object [] findOADetail(OaAppDetail oadetail, String startDate,
			String endDate, Integer cpage,Integer pageSize, String tag);
	/**
	 * 下拉选项
	 * @param tag 前台下拉项目
	 * @param powerTag 权限标识
	 * @return
	 */
	String findOASelect(String tag,String powerTag);
	/**
	 * 判断有无预算记录
	 * @return
	 */
	boolean preSaveDetail();
	/**
	 * 查找下拉月算月份
	 * @param tag
	 * @return
	 */
	String findSelectMon(String tag);
	/**
	 * 查找合成字段
	 * @param tag
	 * @param planMonth
	 * @return
	 */
	List findchildSubjects(String tag,String planMonth);
	/**
	 * 根据类别查找物品名称
	 * @param tag
	 * @return
	 */
	String findchildClass(String tag,String message);
	/**
	 * 根据类别和物品名称查找规格
	 * @param tag
	 * @param powerTag
	 * @return
	 */
	String findFormat(String tag, String powerTag);
	/**
	 * 保存申请明细
	 * @param oadetail
	 * @return
	 */
	String  saveOADetail(OaAppDetail oadetail);
	/**
	 * 根据ID获取单条明细
	 * @param id
	 * @return
	 */
	OaAppDetail getOADetaailById(Integer id);
	/**
	 * 更新申请明细
	 * @param oadetail
	 * @param tag
	 * @return
	 */
	boolean updateOADetail(OaAppDetail oadetail,String tag);
	/**
	 * 根据ID删除申报明细
	 * @param oadetail
	 * @return
	 */
	boolean deleteOADetailById(OaAppDetail oadetail);
	//批量上传保存申报记录
	String saveLotUpload(File uploadDetail,OaAppDetail oadetail);
	/**
	 * 查找待打印申报信息和申报历史记录
	 * @param opApply
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag
	 * @return
	 */
	Object[] findPreApp(OaPrepareApply opApply, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag);
	/**
	 * 查询单个预申请内地申报明细
	 * @param oadetail 查询条件
	 * @param id 预申请ID
	 * @param startDate
	 * @param endDate
	 * @param cpage
	 * @param pageSize
	 * @param tag (查询待打印，全部)
	 * @param powerTag 权限self，buy，manager
	 * @return
	 */
	Object[] findPreAppDetail(OaAppDetail oadetail,Integer id, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag,String powerTag);
	/**
	 * 根据ID查找可以打印的detail明细
	 * @param id 预申请ID
	 * @param tag（print：打印，select：多选操作）
	 * @return
	 */
	List findPrintList(Integer id,String tag);
	/**
	 * 根据ID获取预申请对象
	 * @param id
	 * @return
	 */
	OaPrepareApply getPreById(Integer id);
	/**
	 * 打印明细更改状态和添加外键
	 * @param id
	 * @param tag
	 * @return
	 */
	String updatePrintStatus(Integer id, String tag);
	/**
	 * 查找补打印选择 list
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	List findSelectedDetail(Integer[] detailSelect,String tag);
	/**
	 * 查找需要审批的记录
	 * @return
	 */
	Object[] findExamList(Integer cpage, Integer pageSize);
	/**
	 * 审批操作
	 * @param detailSelect
	 * @param tag
	 * @return
	 */
	boolean updateExamOADetail(Integer[] detailSelect,String tag);
	/**
	 * 查看审批历史记录
	 * @return
	 */
	Object[] findExamHistoryList(OaAppDetail oadetail, String startDate,
			String endDate, Integer cpage, Integer pageSize, String tag);
	/**
	 * 添加申购名称和规格
	 * @param oadetail
	 * @return
	 */
	boolean saveKemu(OaAppDetail oadetail);
	/**
	 * 根据项目查找工装规格
	 * @param tag 项目编号
	 * @param powerTag 工装名称
	 * @return
	 */
	String findFormatByProject(String tag, String powerTag);
	//预算金额控制
	String compareBudgetCount(Integer id, Float money,Integer id1);
	
	List getNameBymingcheng(String tag, String powerTag);
	/***
	 * 查询当前用户未审批和审批历史的接口
	 * @param users
	 * @param bool
	 * @return
	 */
	List findNoApproval(Users users, boolean bool);
	/***
	 * 审批接口（通过、驳回）
	 * @param detailSelect
	 * @param tag
	 * @param opinion
	 * @param users
	 * @return
	 */
	boolean updateExamOADetail1(Integer[] detailSelect, String tag,
			String opinion, Users users);
	/***
	 * 获取验证码
	 * @param number
	 * @param code
	 * @param cusId
	 * @return
	 */
	boolean send(String number, String code, String cusId);
	
	/**
	 * 查询已到货的物品
	 * @param oadetail
	 * @param cpage
	 * @param pageSize
	 * @param tag 权限code&all
	 * @return
	 */
	Object [] findOADetail_1(OaAppDetail oadetail, Integer cpage,Integer pageSize, String tag, String status);
	
	/**
	 * 查询所有库位信息
	 */
	List<WarehouseNumber> findAllWNList_1();
	/**
	 * OA采购入库操作(工装)可以重复存放
	 */
	Object [] upfindOACaiGouRuGuiBacode(String bacode, String mxId, String tag);
	/**
	 * 根据得到库位
	 * @author LiCong
	 * @param id
	 * @return
	 */
	public WarehouseNumber byIdWN(Integer id);
	/***
	 * 库管确认入柜(库位)数量
	 * 
	 * @param list 明细集合
	 * @param wn 库位对象
	 * @return
	 */
	String updateGZToRG(List<OaAppDetail> list, WarehouseNumber wn);
	
	/**
	 * 导入申购物品名称
	 */
	String Addoadetail(File oadetailFile);
	/**
	 * 临时使用
	 */
	String scfuliaoOaApply();
		
	/**
	 * 根据申请编号保存申报
	 * @param oadetail
	 * @return
	 */
	boolean saveOADetail2(OaAppDetail oadetail);
	/**
	 * 设置批量导入模板的内容
	 * @return
	 */
	String settingOaUploadTemplate();
	
	/**
	 * 批量上传采购申请
	 * @param oadetail
	 * @param oadetailFile
	 * @return
	 */
	String saveBatchOADetail(OaAppDetail oadetail, File oadetailFile);
	/**
	 * 根据oaappdetail.id或得最近一次的入库历史记录
	 * @return
	 */
	GoodsStore getRecentlyGoodsStore(Integer id);
}
