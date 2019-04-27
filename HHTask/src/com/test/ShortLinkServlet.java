package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.task.Server.sys.ShortLinkServer;
import com.task.ServerImpl.sys.ShortLinkServerImpl;
import com.task.entity.system.ShortLink;

public class ShortLinkServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 3364174373628946792L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
         PrintWriter pr = response.getWriter();
         String shortId = request.getPathInfo().substring(1,request.getPathInfo().length());
         WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
         ShortLinkServer server  = (ShortLinkServer) context.getBean("shortLinkServer");
         ShortLink shortLink = server.getshortByLongLink(shortId);
         if(shortLink!=null ) {
        	 if(shortLink.getVal()!=null && shortLink.getVal().equals("isLogin")) {
        		 response.sendRedirect(request.getContextPath()+"/login.jsp");
        	 }else {
        		 String longLink =shortLink.getLongUrl(); 
        		 response.sendRedirect(longLink);
        		 System.out.println(longLink);
        		 pr.print(longLink);
        	 }
         }
	     pr.flush();
	     pr.close();      
		
	}   	  	
}