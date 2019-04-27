package com.task.ServerImpl;

import com.task.Dao.TotalDao;
import com.task.Server.ExceptionHandleServer;
import com.task.entity.ExceptionHandle;

public class ExceptionHandleServerImpl implements ExceptionHandleServer{
	private static TotalDao totaldao;
	
	
	/**
	 * 保存一项异常处理记录
	 * @param exception
	 * @return
	 */
	public static boolean save(ExceptionHandle exception) {
		// TODO Auto-generated method stub
		return totaldao.save(exception);
	}


	public TotalDao getTotaldao() {
		return totaldao;
	}


	public void setTotaldao(TotalDao totaldao) {
		this.totaldao = totaldao;
	}
	
	
	
	
	

}
