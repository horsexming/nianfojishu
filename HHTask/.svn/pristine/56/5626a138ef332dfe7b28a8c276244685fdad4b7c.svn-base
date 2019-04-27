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
		<title>摄像头列表</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<h1 style="font-size: 20px;" align="center">摄像头列表</h1>
			<div align="center">
				<input type="button" class="input" value="添加" onclick="toAdd()">
			</div>
			<table class="table" >
				<tr>
					<th>序号</th>
					<th>名称</th>
					<th>位置</th>
					<th>IP</th>
					<th>端口号</th>
					<th>比对相似度</th>
					<th>用户名</th>
					<th>用途</th>
					<th>操作</th>
				</tr>
				<s:iterator value="cameraList" id="fl" status="ps">
					<s:if test="#ps.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
						<td align="center">
							<s:property value="#ps.index+1" />
						</td>
						<td align="center">
							${fl.name }
						</td>
						<td align="center">
							${fl.position }
						</td>
						<td align="center">
							${fl.ip }
						</td>
						<td align="center">
							${fl.port }
						</td>
						<td align="center">
							${fl.similarity }
						</td>
						<td align="center">
							${fl.userName }
						</td>
						<td align="center">
							${fl.cameraUse }
						</td>
						<td>
							<input type="button" value="修改" onclick="updateCamera(${fl.id})">
							<input type="button" value="删除" onclick="deleteCamera(${fl.id})">
						</td>
					</tr>
				</s:iterator>
				<tr id="fenyeTr" align="right">
					<td class="text-right" colspan="20"> 
						第
						<font color="red" id="cpage">${cpage}</font> / <font id="total">${total}</font> 页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" /> 
					</td>
				</tr>
			</table>
			
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function toAdd(){
				location.href="${pageContext.request.contextPath}/faceAction!toFaceCameraUpdatePage.action";
			}
			
			function deleteCamera(id){
				location.href="${pageContext.request.contextPath}/faceAction!deleteFaceCamera.action?id="+id;
			}
			function updateCamera(id){
				location.href="${pageContext.request.contextPath}/faceAction!toFaceCameraUpdatePage.action?id="+id;
			}
		</script>
	</body>
</html>
