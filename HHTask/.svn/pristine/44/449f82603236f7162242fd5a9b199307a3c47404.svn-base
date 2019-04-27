package com.task.Server.sop.qd;

import java.util.List;

import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.qd.LogoStickers;

/**
 * 质量标识贴server层
 * 
 * @author jhh
 * 
 */
public interface LogoStickerServer {
	/**
	 * 查询质量标识贴
	 * 
	 * @param procard
	 *            (质量标识贴条件查询对象)
	 * @param startDate
	 *            (制贴开始时间)
	 * @param endDate
	 *            (制贴结束时间)
	 * @param cpage
	 *            (当前页)
	 * @param PageSize
	 *            (页显示条数)
	 * @return
	 */
	public Object[] findSticck(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag);

	public Object[] findNOOKList(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag);

	/**
	 * 
	 * @param sticker
	 *            添加标识贴
	 * @return boolean
	 */
	public boolean saveStick(LogoStickers sticker);

	/**
	 * 根据ID查询对象
	 * 
	 * @param id
	 * @return
	 */
	public LogoStickers getStickById(Integer id);
	
	/**
	 * 根据编号查询对象
	 * 
	 * @param id
	 * @return
	 */
	public LogoStickers getStickBynumber(String number);

	/**
	 * 更新
	 * 
	 * @param sticker
	 * @return
	 */
	public boolean updateStick(LogoStickers sticker);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteStickById(Integer id);

	/**
	 * 根据条件查询user信息
	 * 
	 * @param style
	 *            ，条件（工号/姓名）
	 * @param content
	 *            ，值（工号/姓名）
	 * @return
	 */
	public String[] findUserInfor(String style, String content);

	/**
	 * 更改打印状态
	 * 
	 * @param id
	 * @return
	 */
	public boolean printInfor(Integer id);

	/**
	 * 数据汇总
	 * 
	 * @param sticker
	 * @return
	 */
	public Object[] findSum(LogoStickers sticker, String startDate,
			String endDate, Integer cpage, Integer PageSize);

	/**
	 * 选择下拉
	 * 
	 * @param tag
	 * @return
	 */
	public String selectItem(String tag);

	/**
	 * 汇总数据导出EXCEL
	 * 
	 * @param sticker
	 *            对象
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            截止时间
	 * @return
	 */
	public void export(LogoStickers sticker, String startDate, String endDate);

	public List findListPrint(String tag);

	/**
	 * 
	 * @return
	 */
	public List getsjMarkId();

	public List getsjSelfCard(String markId);

	public List getsjProcessNo(String markId, String lotId);

	public LogoStickers getStickerForCheck(String markId, String lotId,
			String processNO);

	public List getcheckList(Integer id);

	public Object[] findSjList(LogoStickers sticker, String startDate,
			String endDate, int parseInt, int pageSize, String tag);

	public String updateStick2(LogoStickers sticker,
			List<OsRecordScope> osRecordScopeList,String status);

	Object[] updateOsTemplate(Integer id);

	/****
	 * 首检打印消息
	 * 
	 * @param id
	 * @return
	 */
	String printMes(Integer id);
	
	String updateAlertCheck();
	/**
	 * 选择量、检具信息
	 * @param ids
	 * @return
	 */
	String	xuanzeMea(Integer[] ids,Integer id,Integer id1);
}
