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
		<style type="text/css">
			.table{
				border:0px solid #999;
				border-width: 1px;
				border-collapse:collapse;
			}
			.table th,.table td {
				border-width: 1px;
				padding: 0px;
			}
			
			.subTable{
				text-align: center;
				border-collapse:collapse;
				width: 100%;
				border-width:1px; 
				border-style:hidden;
			}
		</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					
				</div>
			</div>
			
			<div align="center">
				<table border="0" class="table" style="text-align: center;width: 50%">
					<tr><th>内容</th><th>操作</th></tr>
					<tr><td>工序参数</td>
						<td>
							<a target="_blank" href="processDataAction!getProcessDataAddPage.action?processData.id=${processData.id}">修改</a>
						</td>
					</tr>
					<tr><td>作业规范</td>
						<td>
							<a target="_blank" href="processDataAction!findAllOperationStandardList.action?processData.id=${processData.id}">查看</a>
						</td>
					</tr>
					<tr><td>过程参数</td>
						<td>
							<a target="_blank" href="processDataAction!getProcessParamAddPage.action?processData.id=${processData.id}">修改</a>
						</td>
					</tr>
					<tr><td>检查项目</td>
						<td>
							<a target="_blank" href="processDataAction!findAllDetectionItemList.action?processData.id=${processData.id}">查看</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	
});
</script>
