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
		<title>人脸列表</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<h1 style="font-size: 20px;" align="center">人脸用户列表</h1>
			<div align="center">
				<s:if test="userId!=null&&userId>0">
					<input type="button" class="input" value="添加" onclick="toAdd()">
				</s:if>
				<s:elseif test="userId ==null && pageStatus!=null && pageStatus=='alarm'">
					<input type="button" class="input" value="添加报警人员" onclick="toAddAlarm()" style="width: 120px;">
				</s:elseif>
			</div>
			<table class="table" >
				<tr>
					<th>序号</th>
					<th>工号</th>
					<th>姓名</th>
					<th>人脸所在组</th>
					<th>人脸工号</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
				<s:iterator value="faceList" id="fl" status="ps">
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
							${fl.code }
						</td>
						<td align="center">
							${fl.userName }
						</td>
						<td align="center">
							${fl.groupName }
						</td>
						<td align="center">
							${fl.faceCode }
						</td>
						<td align="center">
							<a href="FileViewAction.action?FilePath=/upload/file/face/${fl.picturePath }">${fl.picturePath }</a>
						</td>
						<td>
							<input type="button" value="删除" onclick="deleteFaceUser(${fl.id})">
						</td>
					</tr>
				</s:iterator>
				
			</table>
			
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
			function toAdd(){
				location.href="${pageContext.request.contextPath}/faceAction!toAddFaceUsers.action?userId=${userId}";
			}
			
			function deleteFaceUser(id){
				location.href="${pageContext.request.contextPath}/faceAction!deleteFaceUsers.action?id="+id+"&faceUsers.userId=${userId}&pageStatus=${param.pageStatus}";
			}
			function toAddAlarm(){
				location.href="${pageContext.request.contextPath}/faceAction!toAddAlarm.action?pageStatus=alarm";
			}
		</script>
	</body>
</html>
