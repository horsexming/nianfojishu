<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>
<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/indexstyle/pintuer.js"></script>
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/indexstyle/common.js"></script>
</head>
<body>
<p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb10 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="<%=request.getContextPath()%>/User/toUserIndex">首页</a></li>
	            	<li><a href="<%=request.getContextPath()%>/User/updateUser">个人信息</a></li>
	            	<li><a href="<%=request.getContextPath()%>/User/Myread">阅读中课程</a></li>	            	
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>
	        	</ul>     	       
	    	</div>
		</div>
	    <div class="index-body xb10 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">
	                <span class='mt_tit'>未阅读课程</span>
	                <table align="center" class="table table-hover">
	                	<tr>	                		
	                		<td>序号</td>
	                		<td>课程名称</td>
	                		<td>发布者</td>
	                		<td>日阅读量参考</td>             		
	                		<td>发布时间</td>
	                		<td>课程阅读</td>
	                	</tr>
	                	<c:forEach items="${selectLimitTen}" var="LimitTenCourse" varStatus="status">
	                		<tr>		             		
		                		<td>${status.index+1}</td>
		                		<td>${LimitTenCourse.coursename}</td>
		                		<td>${LimitTenCourse.writer}</td>
		                		<td>${LimitTenCourse.courseRead}</td>		                		                			                		
		                		<td><fmt:formatDate value="${LimitTenCourse.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>		                		
		                		<td><a href="<%=request.getContextPath()%>/User/LookCourse/${LimitTenCourse.id}?pn=${pn}">阅读</a></td>		             			          			
	                		</tr>
	                	</c:forEach>
	                </table>
	                <!-- 加载分页信息 -->
					<table align="center">	                
						<tr>
				    		<td>当前第<font color="blue">${pn}</font>页</td>
				    		<td>共有<font color="blue">${page}</font>页</td>
				    		<td>共有<font color="blue">${pagenum}</font>条记录</td>
				    	</tr> 
					</table>
					<div class="container">					
						<div class="col-md-12 col-md-offset-6">
							<nav aria-label="Page navigation">
								<ul class="pagination">			
									<li><a href="<%=request.getContextPath()%>/User/toUserIndex?pn=1" aria-label="Previous">首页</a></li>
									<c:if test="${pn>1}">
										<li>
											<a href="<%=request.getContextPath()%>/User/toUserIndex?pn=${pn-1}" aria-label="Previous">
												<span aria-hidden="true">上一页</span>
											</a>
										</li>
									</c:if>	
									<li>
										<span aria-hidden="true">第<font color="blue">${pn}</font>页</span>
									</li>
									<c:if test="${pn<page}">
										<li>
											<a href="<%=request.getContextPath()%>/User/toUserIndex?pn=${pn+1}" aria-label="Next">
												<span aria-hidden="true">下一页</span>
											</a>
										</li>
									</c:if>				    
									<li><a href="<%=request.getContextPath()%>/User/toUserIndex?pn=${page}" aria-label="Previous">尾页</a></li>							
								</ul>
							</nav>
						</div>
					</div> 
						<!-- 加载分页信息完毕 -->			                
	            </div>
	        </div>
		</div> 
	</div>
</body>
</html>