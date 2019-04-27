package com.task.ServerImpl.payment;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.task.Dao.TotalDao;
import com.task.Server.payment.SignatureServer;
import com.task.Server.sys.CircuitRunServer;
import com.task.entity.Users;
import com.task.entity.approval.Signature;
import com.task.entity.payment.PaymentVoucher;
import com.task.entity.quality.Quality;
import com.task.util.Util;

public class SignatureServerImpl implements SignatureServer {
	private TotalDao totalDao;
	private CircuitRunServer circuitRunServer;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public CircuitRunServer getCircuitRunServer() {
		return circuitRunServer;
	}

	public void setCircuitRunServer(CircuitRunServer circuitRunServer) {
		this.circuitRunServer = circuitRunServer;
	}

	/*
	 * 查询所有电子签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#findSignature(com.task.entity.approval.Signature, int, int)
	 */
	@Override
	public Object[] findSignature(Signature signature, int parseInt,
			int pageSize,String test) {
		if (signature == null) {
			signature = new Signature();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(signature, null);
		hql +=" and default_address='默认'";
		if(signature.getCode()!=null&&!"".equals(signature.getCode())){
			hql += " and code='" + signature.getCode() + "'";
		}if(signature.getName()!=null&&!"".equals(signature.getName())){
			hql += " and name='" + signature.getName() + "'";
		}
		if("1".equals(test)){
			hql += " and name='" + loginUser.getName() + "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	/*
	 *查看历史签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#findHistorySignature(com.task.entity.approval.Signature, int, int)
	 */
	@Override
	public Object[] findHistorySignature(Signature signature, int parseInt,
			int pageSize,String test) {
		Signature signature3 = new Signature();
		Signature signature2 = (Signature) this.totalDao.getObjectById(Signature.class, signature.getId());
		if (signature2 == null) {
			signature2 = new Signature();
		}
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		String hql = totalDao.criteriaQueries(signature3, null);
		hql +=" and default_address='历史'";
		if(signature.getCode()!=null&&!"".equals(signature.getCode())){
			hql += " and code='" + signature.getCode() + "'";
		}if(signature.getName()!=null&&!"".equals(signature.getName())){
			hql += " and name='" + signature.getName() + "'";
		}
			hql +=" and name='"+signature2.getName()+"'";
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 上传电子签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#saveSignature(com.task.entity.approval.Signature)
	 */
	@Override
	public void saveSignature(Signature signature,String test) {
		if(test.equals("1")){
			signature.setCode(Util.getLoginUser().getCode());
			signature.setName(Util.getLoginUser().getName());
		}
		String createdate1 = Util.getDateTime("yyyy-MM-dd");
		signature.setSignature_date(createdate1);
		String hql = "from  Signature where code='"+signature.getCode()+"'";
		 List list =   this.totalDao.query(hql, null);
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Signature signature2 = (Signature) list.get(i);
				signature2.setDefault_address("历史");
			}
		}
		signature.setDefault_address("默认");
		this.totalDao.save(signature);
		
	}

	/*
	 * 删除历史电子签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#delSignature(java.lang.Integer)
	 */
	@Override
	public void delSignature(Integer delId) {
		Signature signature = (Signature) this.totalDao.getObjectById(Signature.class,delId);
		if(signature.getSignature_address()!=null){
			//先删除原有的文件
			String fileRealPath = ServletActionContext.getServletContext().getRealPath(signature.getSignature_address());
			File uploadFile = new File(fileRealPath);
			uploadFile.delete();
		}
		this.totalDao.delete(signature);
		
	}

	/*
	 * 根据编号查询电子签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#findSignatureByid(java.lang.String)
	 */
	@Override
	public Signature findSignatureByid(Integer salId) {
		// TODO Auto-generated method stub
		Signature signature = (Signature) this.totalDao.getObjectById(Signature.class, salId);
		return signature;
	}

	/*
	 * 
	 * 修改其电子签名(non-Javadoc)
	 * @see com.task.Server.payment.SignatureServer#updateSignature(com.task.entity.approval.Signature)
	 */
	@Override
	public void updateSignature(Signature signature) {
		String createdate1 = Util.getDateTime("yyyy-MM-dd");
		if(signature.getSignature_address()!=null){
			Signature signature2 = (Signature) this.totalDao.getObjectById(Signature.class, signature.getId());
			signature2.setDefault_address("历史");
			this.totalDao.update(signature2);
			
			Signature signature3 = new Signature();
			signature3.setCode(signature.getCode());
			signature3.setName(signature.getName());
			signature3.setSignature_date(createdate1);
			signature3.setDefault_address("默认");
			signature3.setSignature_address(signature.getSignature_address());
			this.totalDao.save(signature3);
		}
	}

	
	

}
