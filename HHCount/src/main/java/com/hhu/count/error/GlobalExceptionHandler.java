package com.hhu.count.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * 
 * 设置登录超时页面跳转
 * 
 * */
@ControllerAdvice(basePackages = "com.hhu.count.controler")
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView resolveException() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
