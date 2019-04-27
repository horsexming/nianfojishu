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
			<s:if test="errorMessage!=null">
			 <h3><font color="red">${errorMessage}</font> </h3>
			</s:if>
			<s:else>
				<form action="procardTemplateGyAction_upptlv.action" method="post">
				<input name="procardTemplate.id" type="hidden" value="${procardTemplate.id}">
				<table class="table">
					<tr>
					<td align="center" colspan="7">版本升级申请</td>
					</tr>
					<tr>
					<td align="center">件号</td>
					<td align="center">名称</td>
					<td align="center">类型</td>
					<td align="center">版本号</td>
					<td align="center">新版本号</td>
					<td align="center">升级备注</td>
					</tr>
					<s:if test="bbAply==null">
					<tr>
					<td align="center">${procardTemplate.markId}</td>
					<td align="center">${procardTemplate.proName}</td>
					<td align="center">${procardTemplate.procardStyle}</td>
					<td align="center">${procardTemplate.banBenNumber}</td>
					<td align="center">
					<input type="text" name="procardTemplate.newBanBenNumber" value="${procardTemplate.newBanBenNumber}">
					</td>
					<td align="center">
					<textarea rows="3" cols="40" name="procardTemplate.remark" ></textarea>
					</td>
					
					</tr>
					<tr>
						<td align="center" colspan="6"><input type="submit" value="提交" style="width: 60px;height: 40px;"></td>
					</tr>
					</s:if>
					<s:else>
					<tr>
					<td align="center">${bbAply.markId}</td>
					<td align="center">${bbAply.proName}</td>
					<td align="center">${bbAply.procardStyle}</td>
					<td align="center">${bbAply.banbenNumber}</td>
					<td align="center">${bbAply.newBanbenNumber}</td>
					<td align="center">${bbAply.remark}</td>
					<td align="center"><a href="CircuitRunAction_findAduitPage.action?id=${bbAply.epId}">查看审批</a></td>
					</tr>
					</s:else>
					<s:if test="bbAplyList!=null&&bbAplyList.size()>0">
					<tr>
					<td align="center" colspan="7" bgcolor="red">历史申请记录</td>
					</tr>
					<s:iterator value="bbAplyList" id="pagebbAply"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="center">${pagebbAply.markId}</td>
							<td align="center">${pagebbAply.proName}</td>
							<td align="center">${pagebbAply.procardStyle}</td>
							<td align="center">${pagebbAply.banbenNumber}</td>
							<td align="center">${pagebbAply.newBanbenNumber}</td>
							<td align="center">${pagebbAply.remark}</td>
							<td align="center"><a href="CircuitRunAction_findAduitPage.action?id=${pagebbAply.epId}">查看审批</a></td>
							</tr>
							</s:iterator>
						
					</s:if>
				</table>
				</form>
			</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
