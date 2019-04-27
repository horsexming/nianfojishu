<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.css">
		<style type="text/css">
			caption{
				text-align: center;
			}
			body{
				margin:5px;
/* 				padding: 5px; */
			}
			.row{
				margin:0;
			}
		</style>
	</head>
	<body bgcolor="#ffffff">
		<div class="container">
			<form action="${pageContext.request.contextPath }/universalAction!addType.action" 
						method="post"  id="myform" autocomplete=off >
				<h2 align="center">
					添加系统类型
				</h2>
				
	            <div class="row clearfix"> 
	                <!--下拉框-->
	                <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">型别名称</span>
	                        <select class="form-control" id="categorySelect" name="type.category.id">
	                        	<s:iterator value="categoryList" id="cl" >
	                        		<option value="${cl.id}">${cl.categoryName }</option>
	                        	</s:iterator>
	                        </select>
	                    </div>
	                </div>
	            </div>
	            <div class="row clearfix">
		            <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">类型编码</span>
	                        <input class="form-control" type="text" name="type.code" value="${type.code}">
	                    </div>
		           	</div>
	            </div>
	            <div class="row clearfix">
		            <div class="form-group col-lg-6">
	                    <div class="input-group">
	                        <span class="input-group-addon">类型名称</span>
	                        <input class="form-control" type="text" name="type.name" value="${type.name }">
	                    </div>
		           	</div>
	            </div>
				<div class="row clearfix">
					<input type="hidden" value="${type.id}" name="type.id">
					<input type="hidden" value="${pageStatus}" name="pageStatus">
					<input type="hidden" value="${category.type}" name="category.type">
					<button type="submit" class="btn btn-default" id="addOrUpdate" style="background-color: #fff;">
						保存
					</button>
				</div>
			</form>
		</div>
	</body>
</html>
