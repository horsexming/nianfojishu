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
				<form action="processDataAction!updateDetectionItem.action" method="post"
					style="">
					<input type="hidden" id="id" name="detectionItem.id" value="${detectionItem.id}"/>
					<input type="hidden" id="id" name="detectionItem.processDataId" value="${detectionItem.processDataId}"/>
					<table border="0" width="100%" class="table" style="text-align: center;">
						<tr><td>序号</td><td><input type="text" id="numb" name="detectionItem.numb" value="${detectionItem.numb}"/></td></tr>
						<tr><td>检测项目</td><td><input type="text" id="xiangmu" name="detectionItem.xiangmu" value="${detectionItem.xiangmu}"/></td></tr>
						<tr><td>测量器具</td><td><input type="text" id="qiju" name="detectionItem.qiju" value="${detectionItem.qiju}"/></td></tr>
						<tr><td>操作者测定频次</td><td><input type="text" id="caozuoPinci" name="detectionItem.caozuoPinci" value="${detectionItem.caozuoPinci}"/></td></tr>
						<tr><td>巡检测定频次</td><td><input type="text" id="xunjianPinci" name="detectionItem.xunjianPinci" value="${detectionItem.xunjianPinci}"/></td></tr>
						<tr>
							<tr>
							<td align="center" colspan="2">
								<input type="submit" value="修改"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
						</tr>
					</table>
				</form>
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
