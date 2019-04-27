package com.task.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageTag extends ComponentTagSupport {

	  	private String cpage;  //当前页   
	    private String total;  //总页数   
	    private String url;  //请求地址   
	    
	    private String styleClass;   //新增的样式属性    
	    private String theme;   //新增的分页样式属性    
	    
	    
	    
	    public void setTheme(String theme) {    
	        this.theme = theme;    
	    }    
	        
	    public void setStyleClass(String styleClass) {    
	        this.styleClass = styleClass;    
	    }    
	   

	    public void setCpage(String cpage) {   
	        this.cpage = cpage;   
	    }   
	  
	    public void setTotal(String total) {   
	        this.total = total;   
	    }   
	  
	    public void setUrl(String url) {   
	        this.url = url;   
	    }   
	  
	    @Override  
	    public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
	        return new Pages(arg0); //返回Pages Component，分页的逻辑处理都在这个Component中   
	    }   
	  
	    //获得参数   
	    @Override
		protected void populateParams() {   
	        super.populateParams();   
	           
	        Pages pages = (Pages)component;   
	        pages.setCpage(cpage);   
	        pages.setTotal(total);   
	        pages.setUrl(url);   
	        pages.setStyleClass(styleClass);    
	        pages.setTheme(theme);    
	    }   


}
