package com.task.Server.sop;

import java.io.File;
import java.util.List;

import com.task.entity.sop.OutSourcingApp;

/**
 * 外委申请单接口
 * 
 * @author jhh
 * 
 */
public interface OSApplyServer {
	/** 添加外委申请 **/
	boolean saveOSApp(OutSourcingApp osa, File infor, String inforFileName,
			File machine, String machineFileName, File abilityLack,
			String abilityLackFileName, File othersLack,
			String othersLackFileName);

	/** 查看外委申请 **/
	public Object[] findOSAppList(OutSourcingApp osa, String startDate,
			String endDate, Integer cpage, Integer PageSize, String tag);

	/** 根据ID获得外委申请单 **/
	OutSourcingApp getOSAById(Integer id);

	/** 根据ID更新外委申请单 **/
	boolean updateOSAById(OutSourcingApp osa, File infor, String inforFileName,
			File machine, String machineFileName, File abilityLack,
			String abilityLackFileName, File othersLack,
			String othersLackFileName);

	String findSelectList(String tag);


	/***
	 * 获得所有订单号
	 * 
	 * @return
	 */
	Object[] findOrderNum();

	/***
	 * 获得所有报警单号
	 * 
	 * @return
	 */
	Object[] findMaintenance();

	/***
	 * 计算所需工资总额
	 * 
	 * @return
	 */
	Object[] getWage(String selfCode);

	List listYuanCailiao();

	boolean saveOSApp1(OutSourcingApp osa);

	void updateOSAById(OutSourcingApp osa);

	void updateOSAById2(OutSourcingApp osa, File machine,
			String machineFileName, File othersLack, String othersLackFileName);

	void updateOSAById3(OutSourcingApp osa, File infor, String inforFileName);

	void updateOSAById4(OutSourcingApp osa, File abilityLack,
			String abilityLackFileName);

	void updateOSAById0(OutSourcingApp osa);
	/**
	 * 根据bom的rootId添加一整个bom的外委外购评审单
	 */
	boolean saveBomOSApp(Integer rootId);
    /**
     * 同步集合报价bom还没有生成外购外委评审单的外购件和外委工序
     * @return
     */
	String updateUnOsAppFromBom();

}
