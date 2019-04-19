<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程排行</title>
<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<span id=""></span>
<h2 align="left">未阅读课程排行</h2>
<p align="right">							
	<a href="<%=request.getContextPath()%>/User/toUserIndex">返回</a>	
	<------&nbsp;
</p>	
<ul class="nav nav-pills">
  <li role="presentation"><a href="<%=request.getContextPath()%>/User/ReadCount">阅读人数排行</a></li>
  <li role="presentation"><a href="<%=request.getContextPath()%>/User/ReadCountByM">阅读量排行</a></li>
  <li role="presentation"><a href="<%=request.getContextPath()%>/User/ReadCountByY">最新发布</a></li>
</ul>
	
   <table class="table table-hover">  		
	    <tr>
		    <td colspan="3">
		    	&nbsp;
		    </td>
		</tr>
   		<tr>
   			<td>课程名</td>
   			<td>读者数量</td>
   			<td>应读量</td>
   			<td>阅读总量</td>
   			<td>平均阅读量</td>
   			<td>发布时间</td>
   		</tr>
	   <c:forEach items="${Coursecompare}" var="ReadCounts">
	   		<tr>
	   			<td>
	   				<c:if test="${empty ReadCounts.courseName}">0</c:if>
	   				<c:if test="${not empty ReadCounts.courseName}">${ReadCounts.courseName}</c:if>
	   			</td>
	   			<td>
	   				<c:if test="${empty ReadCounts.readerNum}">0</c:if>
	   				<c:if test="${not empty ReadCounts.readerNum}">${ReadCounts.readerNum}</c:if>
	   			</td>
	   			<td>
	   				<c:if test="${empty ReadCounts.readability}">0</c:if>
	   				<c:if test="${not empty ReadCounts.readability}">${ReadCounts.readability}</c:if>
	   			</td>
	   			<td>
	   				<c:if test="${empty ReadCounts.numReader}">0</c:if>
	   				<c:if test="${not empty ReadCounts.numReader}">${ReadCounts.numReader}</c:if>
	   			</td>
	   			<td>
	   				<c:if test="${empty ReadCounts.averageReader}">0</c:if>
	   				<c:if test="${not empty ReadCounts.averageReader}">${ReadCounts.averageReader}</c:if>
	   			</td>
	   			<td>
	   			<fmt:formatDate value="${ReadCounts.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	   			</td>
	   		</tr>
	   </c:forEach>
   </table>
</body>
</html>