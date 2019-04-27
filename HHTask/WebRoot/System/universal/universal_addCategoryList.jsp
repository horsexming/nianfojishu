<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@include file="/util/sonHead.jsp"%>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/css/mobiscroll.core-2.5.2.css">
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="${pageContext.request.contextPath }/universalAction!addCategory.action" 
						method="post"  id="myform" autocomplete=off >
				<div class="row clearfix">
					<h2 align="center">
						添加系统类别
					</h2>
					<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">类别编码</span>
	                        <input class="form-control" type="text" name="category.type" value="${category.type}">
	                    </div>
	                </div>
	            </div>
	            <div class="row">
	            	<div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">类别名称</span>
	                        <input class="form-control" type="text" name="category.categoryName" value="${category.categoryName}">
	                    </div>
	                </div>
	            </div>
				<div class="form-group">
					<label for="">
						备注
					</label>
					<textarea class="form-control" rows="3"
						name="category.remarks" >${category.remarks}</textarea>
				</div>
				
				</br>
				<input type="hidden" value="${category.id}" name="category.id" id="categoryId" >
				<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
					保存
				</button>
				<div class="col-md-12 column">
<!-- 					<form method="post" id="fromFile"> -->
<!-- 						<input type="file" name="attachment" id="file" > -->
<!-- 						<input type="button" value="提交" class="input" onclick="submitFile()"> -->
<!-- 					</form> -->
				</div>
			</div>
			</form>
		</div>
	</body>
</html>
