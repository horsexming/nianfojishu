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
				<table class="table">
				<tr>
						<td colspan="11" align="center"><h3>项目分红记录</h3></td>
					</tr>
					<tr>
					 <th>
					 	项目阶段
					 </th>
					 <th>
					 	分红数量
					 </th>
					 <th>
					 	分红时间
					 </th>
					 <th>
					 	件号
					 </th>
					</tr>
					<s:iterator value="qpfhList" id="pageqpfh" status="pageStatus" >
					<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
					 	${pageqpfh.proStatus}
					 </td>
					 <td>
					 	${pageqpfh.money}
					 </td>
					 <td>
					 	${pageqpfh.addTime}
					 </td>
					 <td>
					 	${pageqpfh.markId}
					 </td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		</script>
	</body>
</html>
