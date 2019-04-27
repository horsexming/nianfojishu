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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					指纹信息查询
				</h3>
				<table class="table">
					<tr>
						<th colspan="7">
							<input type="button" value="添加指纹信息" onclick="toAdd('${id}')" style="width: 100px;height: 40px"/>
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							手指类型
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="fingerprintMgList" id="fingerprint"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${fingerprint.name}
						</td>
						<td align="center">
							${fingerprint.code}
						</td>
						<td align="center">
							${fingerprint.dept}
						</td>
						<td align="center">
							${fingerprint.fingerType}
						</td>
						<td align="center">
							${fingerprint.addTime}
						</td>
						<td align="center">
							<a
								href="FingerprintMgAction_oneBangDing.action?id=${fingerprint.id}">下发设备 </a>
						</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function toAdd(id){
	window.location.href = "FingerprintMgAction_toadd.action?id="+id;
}
</script>
	</body>
</html>
