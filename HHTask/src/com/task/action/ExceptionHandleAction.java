package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ExceptionHandleServer;

public class ExceptionHandleAction extends ActionSupport{
	private ExceptionHandleServer exceptionServer;
	
	
	
	
	public void setExceptionServer(ExceptionHandleServer exceptionServer) {
		this.exceptionServer = exceptionServer;
	}

	public ExceptionHandleServer getExceptionServer() {
		return exceptionServer;
	}
	
}
