package com.task.util;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * AOP切面类
 * 
 * @author miraclerz
 */
public class DataSourceAdvice implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	// service方法执行之前被调用
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		String className = target.getClass().getName();
		String methodName = method.getName();
		//System.out.println("切入点: " + className + "类中" + methodName + "方法");

		if ("com.task.ServerImpl.sys.CircuitRunServerImpl".equals(className)) {
			//System.out.println("切换到主写: master");
			DataSourceSwitcher.setMaster();
		} else {
			if (methodName.startsWith("query") || methodName.startsWith("find")
					|| methodName.startsWith("show")
					|| methodName.startsWith("get")
					|| methodName.startsWith("search")
					|| methodName.startsWith("select")
					|| methodName.startsWith("to")
					|| methodName.startsWith("export")
					|| methodName.startsWith("huizong")
					|| methodName.startsWith("daochu")) {
				if(methodName.equals("findDqrWgPlanList")){
					DataSourceSwitcher.setMaster();
				}else{
					DataSourceSwitcher.setDataSource("slave");
				}
				
				// DataSourceSwitcher.setDataSource("master");
				//System.out.println("切换到读: slave");
			} else {
				//System.out.println("切换到主写: master");
				DataSourceSwitcher.setMaster();
			}
		}
	}

	// service方法执行完之后被调用
	public void afterReturning(Object arg0, Method method, Object[] args,
			Object target) throws Throwable {
	}

	// 抛出Exception之后被调用
	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		DataSourceSwitcher.setMaster();
		System.out.println("出现异常,切换到: master");
	}

}
