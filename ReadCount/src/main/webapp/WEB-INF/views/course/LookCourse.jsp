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
            <font size="5">课程查看</font>
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
                        <a href="<%=request.getContextPath()%>/Admin/toAdminIndex" class="nav-link active">
                            <i></i>首页
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Admin/updateAdmin" class="nav-link active">
                            <i></i>个人信息
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/toRelease" class="nav-link active">
                            <i></i>课程发布
                        </a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/AdminCount" class="nav-link active">
                            <i></i>课程统计
                        </a>
                    </li>
                     <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/Course/PrivilegeBinding" class="nav-link active">
                            <i></i>权限绑定
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
        <input type="hidden" name="writer" value="${LookCourse.writer}">
		<input type="hidden" name="writerid" value="${LookCourse.writerid}">
		<input type="hidden" name="id" value="${LookCourse.id}">
        <div class="content">
	       <c:if test="${LookCourse.state==0}">
	       		&nbsp;&nbsp;&nbsp;
	       		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/UpdateCourseState/${LookCourse.id}">课程发布</a>
				&nbsp;&nbsp;&nbsp;
				<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/UpdateCourse/${LookCourse.id}">课程修改</a>		
				&nbsp;&nbsp;&nbsp;
				<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Admin/toAdminIndex">返回首页</a>
			</c:if>			
			<c:if test="${LookCourse.state==1}">
				&nbsp;&nbsp;&nbsp;		
				<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Admin/toAdminIndex">返回首页</a>
				&nbsp;&nbsp;&nbsp;
				<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/CourseState/${LookCourse.id}">课程停止</a>
			</c:if>	
            <div class="container-fluid">
            	<div class="row">
                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span>课程名：</span>
                                    <span>${LookCourse.coursename}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span>每日阅读量参考：</span>
                                    <span>${LookCourse.courseRead}</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card p-4">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <span>课程状态：</span>
                                    <c:if test="${LookCourse.state==0}">
										<span>待发布</span>
									</c:if>
									<c:if test="${LookCourse.state==1}">
										<span>已发布</span>
									</c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${LookCourse.state==1}">
	                    <div class="col-md-3">
	                        <div class="card p-4">
	                            <div class="card-body d-flex justify-content-between align-items-center">
	                                <div>
	                                    <span>发布时间：</span>
	                                    <span><fmt:formatDate
											value="${LookCourse.publishTime}" pattern="yyyy-MM-dd"/>
										</span>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
					</c:if>
					<c:if test="${LookCourse.state==0}">
						<div class="col-md-3">
	                        <div class="card p-4">
	                            <div class="card-body d-flex justify-content-between align-items-center">
	                                <div>
	                                    <span>提交时间：</span>
	                                    <span><fmt:formatDate
											value="${LookCourse.createtime}" pattern="yyyy-MM-dd"/>
										</span>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
					</c:if>	
                </div>
                <div class="row ">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <font size="4">课程内容</font>
                            </div>
                            <div class="card-body p-0">      
                                <div class="justify-content-around mt-4 p-4 bg-light d-flex border-top d-md-down-none">
                                	<textarea rows="17" cols="170" name="coursetext" readonly="readonly">${LookCourse.coursetext}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>                   
                </div>
            </div>
        </div>
     </form>
    </div>
</div>
</body>
</html>