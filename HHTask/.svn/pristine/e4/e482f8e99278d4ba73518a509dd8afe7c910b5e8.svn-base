<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div class="container">
				<form action="UsersAction!addRank.action" method="post">
					<div class="row clearfix">
						<h2 align="center">
							添加职级
						</h2>
					</div>		
					<div class="row">
		                <div class="form-group col-lg-6">
		                    <div class="input-group">
		                        <span class="input-group-addon">职级编码</span>
		                        <input class="form-control" type="text" name="rank.code" value="${rank.code}">
		                    </div>
		                </div>
		                <div class="form-group col-lg-6">
		                    <div class="input-group">
		                        <span class="input-group-addon">职级名称</span>
		                        <input class="form-control" type="text" name="rank.name" value="${rank.name}">
		                    </div>
		                </div>
					</div>
					<div class="row">
						<div class="form-group col-lg-6">
							<button type="submit" class="btn btn-default" style="background-color: #fff;">
								添加
							</button>
						</div>
					</div>
				</form>
				
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
