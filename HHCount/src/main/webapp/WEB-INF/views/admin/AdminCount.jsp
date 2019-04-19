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
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
</head>
<body>
	<p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb10 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="<%=request.getContextPath()%>/Admin/toAdminIndex">首页</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Admin/updateAdmin">个人信息</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/toRelease">课程发布</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Course/PrivilegeBinding">权限绑定</a></li>
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>
	        	</ul>      	        	      
	    	</div>
		</div>
	    <div class="index-body xb10 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">	           	        
	                <span class='mt_tit'>课程统计</span>	 
	                <span class='mt_tit'>&nbsp;</span>		                	          
	                <table align="center" class="table table-hover">
	                	<tr>	                	
	                		<td><font style="font-weight: bold;">课程名称</font></td>	             		
	                		<td align="center"><font style="font-weight: bold;">日应阅读次数</font></td>
	                		<td align="center"><font style="font-weight: bold;">阅读总人数</font></td>
	                		<td align="center"><font style="font-weight: bold;">阅读总次数</font></td>
	                		<td align="center"><font style="font-weight: bold;">实应该阅读总次数</font></td>
	                		<td align="center"><font style="font-weight: bold;">还应阅读次数</font></td>
	                		<td><font style="font-weight: bold;">状态</font></td>
	                		<td><font style="font-weight: bold;">最新发布时间</font></td>
	                		<td align="center"><font style="font-weight: bold;">操作</font></td>
	                	</tr>
	                	<c:forEach items="${AdminCourseList}" var="AdminCourse" varStatus="status">
	                		<tr>
	                			<c:if test="${AdminCourse.coursename.length()<=10}">
			                		<td>${AdminCourse.coursename}</td>
			                	</c:if>	
			                	<c:if test="${AdminCourse.coursename.length()>10}">
			                		<td>${AdminCourse.coursename.substring(0,10)}...</td>
			                	</c:if>	                			                		                			                		                			                               		
		                		<td align="center">${AdminCourse.courseRead}</td>	                		   	               		
		                		<td align="center">
		                			<c:if test="${AdminCourse.readNum!=0}">${AdminCourse.readNum}</c:if>
									<c:if test="${AdminCourse.readNum==0}">0</c:if>
		                		</td>
		                		<td align="center">
		                			<c:if test="${AdminCourse.userReading!=0}">${AdminCourse.userReading}</c:if>
									<c:if test="${AdminCourse.userReading==0||AdminCourse.readNum==0}">0</c:if>
		                		</td>
		                		<td align="center">
		                			<c:if test="${AdminCourse.SYReading!=0}">${AdminCourse.SYReading}</c:if>
									<c:if test="${AdminCourse.SYReading==0}">0</c:if>
		                		</td>
		                		<td align="center">
		                			<c:if test="${AdminCourse.YCReading>0}">${AdminCourse.YCReading}</c:if>
		                			<c:if test="${AdminCourse.readNum==0}">${AdminCourse.courseRead}</c:if>
									<c:if test="${AdminCourse.YCReading<=0}">已完成</c:if>
		                		</td>
		                		<td>
		                			<c:if test="${AdminCourse.state==1}"><font color="green">已发布</font></c:if>
									<c:if test="${AdminCourse.state==0}">待发布</c:if>
								</td>
		                		<td><fmt:formatDate value="${AdminCourse.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                		<c:if test="${AdminCourse.readNum!=0}">
		                			<td><a href="<%=request.getContextPath()%>/Admin/UserCourse/${AdminCourse.id}">统计</a></td>
                				</c:if>
                			</tr>
	                	</c:forEach>
	                </table>
	                	<!-- 加载分页信息 -->
					<table align="center">	                
						<tr>
				    		<td>当前第<font color="blue">${pageInfo.pageNum}</font>页</td>
				    		<td>共有<font color="blue">${pageInfo.pages}</font>页</td>
				    		<td>共有<font color="blue">${pageInfo.total}</font>条记录</td>
				    	</tr> 
					</table>
					<div class="container">					
						<div class="col-md-12 col-md-offset-6">
							<nav aria-label="Page navigation">
								<ul class="pagination">
									<li><a href="<%=request.getContextPath()%>/Course/AdminCount?pn=1" aria-label="Previous">首页</a></li>
									<c:if test="${pageInfo.hasPreviousPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Course/AdminCount?pn=${pageInfo.pageNum-1}" aria-label="Previous">
												<span aria-hidden="true">上一页</span>
											</a>
										</li>
									</c:if>
									<!-- <li aria-label="Previous">&nbsp;</li> -->					    
									<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
										<c:if test="${page_Number==pageInfo.pageNum}">
											<li class="active"><a href="#">${page_Number}</a></li>
										</c:if>															    		
										<c:if test="${page_Number!=pageInfo.pageNum}">
											<li><a href="<%=request.getContextPath()%>/Course/AdminCount?pn=${page_Number}">${page_Number}></a></li>
										</c:if>					  					    	
									</c:forEach>
									
									<c:if test="${pageInfo.hasNextPage}">
										<li>
											<a href="<%=request.getContextPath()%>/Course/AdminCount?pn=${pageInfo.pageNum+1}" aria-label="Next">
												<span aria-hidden="true">下一页</span>
											</a>
										</li>
									</c:if>											    
									<li><a href="<%=request.getContextPath()%>/Course/AdminCount?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
								</ul>
							</nav>
						</div>
						<!-- 加载分页信息完毕 -->		
					</div>	
	            </div>
	        </div>
		</div> 
	</div>
	
	
</body>
</html>