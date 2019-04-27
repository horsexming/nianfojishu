package com.task.Server.caiwu.pz;

import java.io.File;

import com.task.entity.caiwu.pz.CwCertificate;

/****
 * 财务凭证
 * 2017-08-21
 * @author @author licong
 * 
 */
public interface CwCertificateServer {
	/****
	 * 添加凭证
	 * @param cwCertificate
	 * @return
	 */
	String saveCwCertificate(CwCertificate cwCertificate,String tag);

	/****
	 * 凭证列表
	 * 
	 * @return
	 */
	Object[] findCwCertificateList(CwCertificate cwCertificate, int pageNo,
			int pageSize, String tag);

	/**
	 * 修改凭证表
	 * @param cwCertificate
	 * @return
	 */
	String updateCwCertificate(CwCertificate cwCertificate);
	
	/**
	 * 删除凭证表
	 * @param cwCertificate
	 * @return
	 */
	String deletecwCertificate(Integer id);

	/**
	 * 根据id得到凭证表
	 * @param id
	 * @return
	 */
	CwCertificate getCwCertificateById(Integer id);

	/**
	 * 导入凭证数据
	 * @param pinzheng
	 * @return
	 */
	String daoRupinzheng(File pinzheng);

}
