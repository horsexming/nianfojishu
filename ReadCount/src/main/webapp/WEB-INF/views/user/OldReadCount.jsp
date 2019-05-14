<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>读书系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/simple-line-icons/css/simple-line-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/vendor/font-awesome/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/read/css/styles.css">
    <script src="<%=request.getContextPath()%>/read/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/popper.js/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/vendor/chart.js/chart.min.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/carbon.js"></script>
	<script src="<%=request.getContextPath()%>/read/js/demo.js"></script>
</head>
<script type="text/javascript"> 	
	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();
		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}
		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}
</script>
<body class="sidebar-fixed header-fixed" onload="showTime()">
<div class="page-wrapper">
    <nav class="navbar page-header">
        <a href="#" class="btn btn-link sidebar-mobile-toggle d-md-none mr-auto">
            <i class="fa fa-bars"></i>
        </a>

        <a class="navbar-brand" href="#">
            <font size="5">阅读记录</font>
        </a>

        <a href="#" class="btn btn-link sidebar-toggle d-md-down-none">
            <i class="fa fa-bars"></i>
        </a>

        <ul class="navbar-nav ml-auto">
            <li class="nav-item d-md-down-none">
                             北京时间：
				<span id="hour"></span>时
				<span id="minute"></span>分
				<span id="second"></span>秒	
            </li>
			<li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
            <li class="nav-item d-md-down-none">
                <a>
                   <span class="small ml-1 d-md-down-none">欢迎用户：${username}</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                </a>
            </li>
        </ul>
    </nav>

    <div class="main-container">
        <div class="sidebar">
            <nav class="sidebar-nav">
                <ul class="nav">
                    <li class="nav-title">读书系统</li>

                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/Myread" class="nav-link active">
                            <i></i>首页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/updateUser" class="nav-link active">
                            <i></i>个人信息
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/ReadCount" class="nav-link active">
                            <i></i>阅读统计
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/User/tubiao" class="nav-link active">
                            <i></i>图表统计
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Common/logOut" class="nav-link active">
                            <i></i>退出系统
                        </a>
                    </li>                     
                </ul>
            </nav>
        </div>

        <div class="content">
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <font size="4">阅读历史记录</font>     
	                			<a href="<%=request.getContextPath()%>/User/Myread?pn=${pageInfo.pageNum}">
	                				<font color="#6CA6CD">&nbsp;&nbsp;返回首页&nbsp;</font>
	                			</a>	                							                              	
                            </div>
                            <div class="card-body p-0">      
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<table align="center" class="table table-hover">
										<tr>              
					                		<td>课程名称</td>
					                		<td>课程状态</td>              		
						                	<td>最新发布日期</td>		                                
					                		<td>阅读日期</td>
					                		<td>日阅读次数参考</td>
					                		<td>当日读次数</td>	                      		
					                	</tr>
					                	<c:forEach items="${OldReadCount}" var="OldRead">
					                		<tr>
						                		<td>${OldRead.coursename}</td>
						                		<c:if test="${OldRead.state==1}">
						                			<td>已发布</td>
						                		</c:if>
						                		<c:if test="${OldRead.state==0}">
						                			<td>已停课</td>
						                		</c:if>
						                		<c:if test="${OldRead.state==1}">
						                			<td><fmt:formatDate value="${OldRead.publishTime}" pattern="yyyy-MM-dd"/></td>
							                	</c:if>
							                	<c:if test="${OldRead.state==0}">
						                			<td>停课中</td>
							                	</c:if>	
							                	<td><fmt:formatDate value="${OldRead.readerDate}" pattern="yyyy-MM-dd"/></td>		    		               
						                		<td>${OldRead.courseRead}</td>	              	                
						                		<td>${OldRead.oldReading}</td>               			                					            		         		                										                			                				             		                		<td align="center">
					                		</tr>
					                	</c:forEach>
									</table>
                                </div>
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<table>	                
										<tr>
								    		<td>当前第<font color="blue">${pageInfo.pageNum}</font>页</td>
								    		<td>共有<font color="blue">${pageInfo.pages}</font>页</td>
								    		<td>共有<font color="blue">${pageInfo.total}</font>条记录</td>
								    	</tr> 
									</table>
									<div>					
										<div class="col-md-12 col-md-offset-6">
											<nav aria-label="Page navigation">
												<ul class="pagination">
													<li><a href="<%=request.getContextPath()%>/User/oudReader/${oldId}?pn=1" aria-label="Previous">首页</a></li>
													<c:if test="${pageInfo.hasPreviousPage}">
														<li>&nbsp;&nbsp;
															<a href="<%=request.getContextPath()%>/User/oudReader/${oldId}?pn=${pageInfo.pageNum-1}" aria-label="Previous">
																<span aria-hidden="true">&laquo;</span>
															</a>
														</li>
													</c:if>
													<!-- <li aria-label="Previous">&nbsp;</li> -->					    
													<c:forEach items="${pageInfo.navigatepageNums}" var="page_Number">
														<c:if test="${page_Number==pageInfo.pageNum}">
															<li class="active"><a href="#">&nbsp;&nbsp;${page_Number}</a></li>
														</c:if>
																			    		
														<c:if test="${page_Number!=pageInfo.pageNum}">
															<li><a href="<%=request.getContextPath()%>/User/oudReader/${oldId}?pn=${page_Number}">&nbsp;&nbsp;${page_Number}</a></li>
														</c:if>					  					    	
													</c:forEach>
													
													<c:if test="${pageInfo.hasNextPage}">
														<li>&nbsp;&nbsp;
															<a href="<%=request.getContextPath()%>/User/oudReader/${oldId}?pn=${pageInfo.pageNum+1}" aria-label="Next">
																<span aria-hidden="true">&raquo;</span>
															</a>
														</li>
													</c:if>
																		    
													<li><a href="<%=request.getContextPath()%>/User/oudReader/${oldId}?pn=${pageInfo.pages}" aria-label="Previous">&nbsp;&nbsp;尾页</a></li>							
												</ul>
											</nav>
										</div>
									</div>
									<!-- 加载分页信息完毕 -->	
                                </div>
                            </div>
                        </div>
                    </div>                   
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
