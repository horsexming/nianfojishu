<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>查看课程</title>
	<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/normalize.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/demo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/sheets-of-paper-a4.css">	
</head>
<body class="document">
	<div class="page" >
	<c:if test="${LookCourse.state==0}">
		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/UpdateCourseState/${LookCourse.id}">课程发布</a>
		&nbsp;&nbsp;&nbsp;
		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/UpdateCourse/${LookCourse.id}">课程修改</a>		
		&nbsp;&nbsp;&nbsp;
		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Admin/toAdminIndex">返回首页</a>
	</c:if>			
	<c:if test="${LookCourse.state==1}">		
		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Admin/toAdminIndex">返回首页</a>
		&nbsp;&nbsp;&nbsp;
		<a style="text-decoration: underline;" href="<%=request.getContextPath()%>/Course/CourseState/${LookCourse.id}">课程停止</a>
	</c:if>	
	<h2 align="center">${LookCourse.coursename}</h2>
	<form action="">	
		<input type="hidden" name="writer" value="${LookCourse.writer}">
		<input type="hidden" name="writerid" value="${LookCourse.writerid}">
		<input type="hidden" name="id" value="${LookCourse.id}">
		课程名：
		<input type="text" readonly="readonly" name="coursename" id="coursename" value="${LookCourse.coursename}" style="width:220px; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid">		
		&nbsp;&nbsp;&nbsp;&nbsp;
		每日阅读量：
		<input type="text" readonly="readonly" name="courseRead" value="${LookCourse.courseRead}" style= "width:220px; BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: solid">		
		<br><br>
		课程状态：
		<c:if test="${LookCourse.state==0}">
			<u>
			待发布
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;
			</u>
			
		</c:if>
		<c:if test="${LookCourse.state==1}">
		<u>
		已发布
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;
		</u>		
		</c:if>
		<c:if test="${LookCourse.state==1}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			发布时间：&nbsp;&nbsp;&nbsp;&nbsp;
			<u>
				<fmt:formatDate
			value="${LookCourse.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</u>	
		</c:if>
		<c:if test="${LookCourse.state==0}">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			提交时间：&nbsp;&nbsp;&nbsp;&nbsp;
			<u>
				<fmt:formatDate
				value="${LookCourse.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</u>		
		</c:if>							
		<br>
		<br>
		课程内容：
		<br>
		<br>
		<textarea rows="30" cols="80" name="coursetext" readonly="readonly">
		${LookCourse.coursetext}
		</textarea>
		</form>
	</div>	
</body>
</html>