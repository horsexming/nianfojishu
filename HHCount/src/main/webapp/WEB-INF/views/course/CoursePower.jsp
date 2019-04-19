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
	<title>发布课程</title>
	<link type="image/png" href="http://tm-image.tianyancha.com/tm/b928c552e7d861b16141d5d40e950749.jpg" rel="shortcut icon">
	<script  src="<%=request.getContextPath()%>/static/js/jquery-3.3.1.min.js"></script>
	<link href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"rel="stylesheet" />
	<script src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/normalize.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/demo.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/course/css/sheets-of-paper-a4.css">	
	
</head>
<script type="text/javascript">
$(function() {
    $("#selectAll").click(function() {
        $(":checkbox[name='ids']").prop("checked", this.checked); // this指代的你当前选择的这个元素的JS对象
    });
});
</script>

<body class="document">
	<div class="page" >	
		<div align="right"><a href="<%=request.getContextPath()%>/Admin/toAdminIndex">返回</a></div>
		<h2 align="center">设置允许阅读本课程的用户</h2>
		<c:if test="${CoursePower==null}">
			${CoursePNull}
		</c:if>
		<c:if test="${CoursePower!=null}">
			<form action="<%=request.getContextPath()%>/Course/updatePoor/{CoursePowerID}" method="post">
				<table class="table table-bordered">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;全选&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="checkbox" id="selectAll">
						</td>
						<td>用户名</td>
						<td>真实姓名</td>
					</tr>
					<c:forEach items="${CoursePower}" var="CoursePower" varStatus="status">
						<tr>
							<td align="center">
								<input type="checkbox" id="subcheck" name="ids" value="${CoursePower.username}"
								<c:if test="${CoursePower.state=='1'}">checked="checked"</c:if>							
								>
							</td>
							<td>
								<span>${CoursePower.username}</span>
							</td>
							<td>
								<span>${CoursePower.realname}</span>
							</td>					  						  
					  	</tr> 	
					</c:forEach>
					<tr>
					  	<td align="right" colspan="3"><input type="submit" value="提交修改"></td>
					</tr>																										
				</table>			
			</form>
		</c:if>
	</div>	
</body>
</html>