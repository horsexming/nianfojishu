package com.task.Server.payment;

import com.task.entity.approval.Signature;

public interface SignatureServer {

	/***
	 * 查询所有电子签名
	 * @param signature
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findSignature(Signature signature, int parseInt, int pageSize,String test);

	/***
	 * 上传电子签名
	 * @param signature
	 */
	void saveSignature(Signature signature,String test);

	/***
	 * 删除历史电子签名
	 * @param delId
	 */
	void delSignature(Integer delId);

	/**
	 * 根据编号查询电子签名
	 * @param salId
	 */
	Signature findSignatureByid(Integer salId);

	/**
	 * 修改电子签名
	 * @param signature
	 */
	void updateSignature(Signature signature);

	/**
	 * 查看历史电子签名
	 * @param signature
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	Object[] findHistorySignature(Signature signature, int parseInt,
			int pageSize,String test);

}
