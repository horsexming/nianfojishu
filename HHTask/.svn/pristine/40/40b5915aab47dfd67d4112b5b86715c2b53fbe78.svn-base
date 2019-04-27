package com.task.Server.caiwu;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.caiwu.CwUseDetail;
import com.task.entity.caiwu.CwVouchers;
import com.task.entity.caiwu.CwVouchersDetail;

public interface CwVouchersServer {

	/**
	 * 分页查询财务凭证
	 * 
	 * @param cwVouchers
	 * @param parseInt
	 * @param pageSize
	 * @param start
	 * @param end
	 * @param pageStatus
	 * @return
	 */
	Map<Integer, Object> findCwVouchersByCondition(CwVouchers cwVouchers,
			int parseInt, int pageSize, String start, String end, String tages);

	/**
	 * 通过Id获取财务凭证对象
	 * 
	 * @param id
	 * @return
	 */
	CwVouchers getCwVouchersById(int id);

	/**
	 * 通过财务凭证id获取其下明细
	 * 
	 * @param id
	 * @return
	 */
	List<CwVouchersDetail> findDetailByVouchersById(int id);

	/****
	 * 根据科目id查询对应的借贷记录
	 * 
	 * @param id
	 * @return
	 */
	List<CwVouchersDetail> findDetailBysubId(int id);

	/**
	 * 通过分录查询对应辅助明细
	 * 
	 * @param id
	 * @return
	 */
	List<CwUseDetail> findCwUseDetailByCvdId(int id);

	/********
	 * 固定资产月度折旧---计提
	 * 
	 * @return
	 */
	boolean accetMonthZhejiu();

	/**
	 * 确认已做账
	 * @param id
	 * @return
	 */
	String queren(int id);

	/**
	 * 确认已做账:上传做账凭证信息
	 * @param cwVouchers
	 * @return
	 */
	String shangchuan(CwVouchers cwVouchers, File proofFile,
			String fileName);


	/*
	    * @author fy
	　　* @date 2018/8/27 15:39
	　　* @Description:  通过ReceiptLogId查找CwVouchers
	　　* @param [id]
	　　* @return com.task.entity.caiwu.CwVouchers
	　　* @throws
	　　*/
	CwVouchers findCwVouchersByReceiptLogId(Integer id);
	
	
	/**
	 * 手动添加凭证明细
	 * @param cwVouchersDetailList,createTime
	 * @return
	 * @throws Exception 
	 */
	String insertCwVouchersDetail(List<CwVouchersDetail> cwVouchersDetailList,String createTime);
	
	
	/**
	 * 根据id查询需要修改的凭证明细信息
	 * @param id
	 * @return
	 */
	List<CwVouchersDetail> findCwVouchersDetailById(Integer id);
	
	/**
	 * 手动修改凭证明细
	 * @param cwVouchersDetail
	 * @param cwVouChersId
	 * @return
	 */
	String updateCwVouchersDetail(List<CwVouchersDetail>  cwVouchersDetailList,Integer cwVouChersId);
	
	
	/**
	 * 删除凭证
	 * @param id
	 * @return
	 */
	boolean deleteCwVouchers(Integer id);
	
}
