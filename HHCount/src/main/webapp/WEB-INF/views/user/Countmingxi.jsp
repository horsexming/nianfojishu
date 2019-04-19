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
<title>阅读统计</title>

<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/indexstyle/pintuer.js"></script>
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>

<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/pintuer.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/indexstyle/style.css">
</head>	
<body>
	<p>&nbsp;</p>
	<p align="right"><font size="3">欢迎<font size="3" color="green">${username}</font>来到系统
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p>&nbsp;</p>
	<div class="container-layout">
	    <div class="line head clearfix ">
	    	<div class="head-mu xb7 xm10 xs12 xl12">
	        	<div class="head-logo xb2 xm2 xs4 xl4">
	            	<a href="#">读书育人</a>
	        	</div>
	        	<ul class="head-list list-inline xb6 xm6 xs6 xl12">
	            	<li><a href="<%=request.getContextPath()%>/User/Myread">首页</a></li>       	            	
	            	<li><a href="<%=request.getContextPath()%>/Common/logOut">退出系统</a></li>      
	        	</ul>         
	    	</div>
		</div>
	    <div class="index-body xb7 xm12 xs12 xl12">
	        <div class="line index-body-nr-left clearfix">
	            <div class="main-tit xl12 xs12 xm12 xb12">
	                <span class='mt_tit'>阅读明细</span>
	              
	                <span class='mt_tit'>
	                	<a href="<%=request.getContextPath()%>/User/ReadCount" class="btn btn-default">
	                		<font color="#00CD00">返回阅读统计</font>			
	                	</a>
	               	</span>
	                <table align="center" class="table table-hover">
	                	<tr>              		                		
	                		<td>课程名</td>
	                		<td>日阅读次数参考</td>
	                		<td>我的阅读次数</td>
	                		<td>最早可阅读时间</td>
	                		<td>我的阅读时间</td>	
	                	</tr>
	                	<c:forEach items="${ReadCount}" var="ReadCount" varStatus="status">
	                		<tr>
					   			<td>${ReadCount.coursename}</td>
					   			<td>${ReadCount.courseRead}</td>
					   			<td>${ReadCount.reading}</td>
					   			<c:if test="${co=='Day'}">
									<td>
										<fmt:formatDate value="${ReadCount.buildFirst}" pattern="yyyy-MM-dd"/>
									</td>
									<td>
										<fmt:formatDate value="${ReadCount.myrDate}" pattern="yyyy-MM-dd"/>
									</td>					   			</c:if>
					   			<c:if test="${co=='Month'}">
									<td>
										<fmt:formatDate value="${ReadCount.buildFirst}" pattern="yyyy-MM"/>
									</td>
									<td>
										<fmt:formatDate value="${ReadCount.myrDate}" pattern="yyyy-MM"/>
									</td>					   			
								</c:if>
					   			<c:if test="${co=='Year'}">
									<td>
										<fmt:formatDate value="${ReadCount.buildFirst}" pattern="yyyy"/>
									</td>
									<td>
										<fmt:formatDate value="${ReadCount.myrDate}" pattern="yyyy"/>
									</td>					   			
								</c:if>
					   		</tr>
	                	</c:forEach>
	                </table>
<!-- 加载分页信息 -->
	<c:if test="${co=='Day'}">
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
				<li><a href="<%=request.getContextPath()%>/User/Countmingxi1/${readerDate}?pn=1" aria-label="Previous">首页</a></li>
				<c:if test="${pageInfo.hasPreviousPage}">
					<li>
						<a href="<%=request.getContextPath()%>/User/Countmingxi1/${readerDate}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
							<span aria-hidden="true">&laquo;</span>
						</a>
					</li>
				</c:if>
				<!-- <li aria-label="Previous">&nbsp;</li>	 -->				    
					<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
						<c:if test="${page_Number==pageInfo.pageNum}">
							<li class="active"><a href="#">${page_Number}</a></li>
						</c:if>											    		
						<c:if test="${page_Number!=pageInfo.pageNum}">
							<li><a href="<%=request.getContextPath()%>/User/Countmingxi1/${readerDate}?pn=${page_Number}">${page_Number}></a></li>
						</c:if>					  					    	
					</c:forEach>
					<c:if test="${pageInfo.hasNextPage}">
						<li>
							<a href="<%=request.getContextPath()%>/User/Countmingxi1/${readerDate}?pn=${pageInfo.pageNum+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
														    
					<li><a href="<%=request.getContextPath()%>/User/Countmingxi1/${readerDate}?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
				</ul>
			</nav>
		</div>		   		
	</c:if>
	<c:if test="${co=='Month'}">
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
						<li><a href="<%=request.getContextPath()%>/User/Countmingxi2/${readerDate}?pn=1" aria-label="Previous">首页</a></li>
							<c:if test="${pageInfo.hasPreviousPage}">
								<li>
									<a href="<%=request.getContextPath()%>/User/Countmingxi2/${readerDate}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
							<!-- <li aria-label="Previous">&nbsp;</li> -->					    
							<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
								<c:if test="${page_Number==pageInfo.pageNum}">
									<li class="active"><a href="#">${page_Number}</a></li>
								</c:if>
															    		
								<c:if test="${page_Number!=pageInfo.pageNum}">
									<li><a href="<%=request.getContextPath()%>/User/Countmingxi2/${readerDate}?pn=${page_Number}">${page_Number}></a></li>
								</c:if>					  					    	
							</c:forEach>							
							<c:if test="${pageInfo.hasNextPage}">
								<li>
									<a href="<%=request.getContextPath()%>/User/Countmingxi2/${readerDate}?pn=${pageInfo.pageNum+1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>										    
					<li><a href="<%=request.getContextPath()%>/User/Countmingxi2/${readerDate}?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
				</ul>
			</nav>
		</div>
	</c:if>  
	<c:if test="${co=='Year'}">
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
						<li><a href="<%=request.getContextPath()%>/User/Countmingxi3/${readerDate}?pn=1" aria-label="Previous">首页</a></li>
							<c:if test="${pageInfo.hasPreviousPage}">
								<li>
									<a href="<%=request.getContextPath()%>/User/Countmingxi3/${readerDate}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
										span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>
						<!-- <li aria-label="Previous">&nbsp;</li> -->					    
							<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
								<c:if test="${page_Number==pageInfo.pageNum}">
									<li class="active"><a href="#">${page_Number}</a></li>
								</c:if>
															    		
								<c:if test="${page_Number!=pageInfo.pageNum}">
									<li><a href="<%=request.getContextPath()%>/User/Countmingxi3/${readerDate}?pn=${page_Number}">${page_Number}></a></li>
								</c:if>					  					    	
							</c:forEach>									
							<c:if test="${pageInfo.hasNextPage}">
								<li>
									<a href="<%=request.getContextPath()%>/User/Countmingxi3/${readerDate}?pn=${pageInfo.pageNum+1}" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>								    
					<li><a href="<%=request.getContextPath()%>/User/Countmingxi3/${readerDate}?pn=${pageInfo.pages}" aria-label="Previous">尾页</a></li>							
				</ul>
			</nav>
		</div>
	</c:if>      
					
<!-- 加载分页信息完毕 -->		                
	            </div>
	        </div>
		</div> 
	</div>
</body>
</html>