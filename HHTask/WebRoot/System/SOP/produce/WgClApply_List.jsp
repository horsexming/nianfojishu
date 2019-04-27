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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"/>
							</th>
							<th>
								序号
							</th>
							<th>
								申请单编号
							</th>
							<th>
								采购单号
							</th>
							<th>
								件号
							</th>
							<th>
								零件名称
							</th>
							<th>
								总数量
							</th>
							<th >
								减单数量
							</th>
							<th>
								申请人工号
							</th>
							<th>
								申请人名称
							</th>
							<th>
								申请状态
							</th>
						</tr>
						<s:iterator id="pageWgcl" value="wgclApplyList"
								status="status0">
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
								</td>
								<td>
									<s:property value="#status0.index+1" />
								</td>
								<td>
									${pageWgcl.applyNumber}
								</td>
								<td>
									${pageWgcl.planNumber}
								</td>
								<td>
									${pageWgcl.markId}
								</td>
								<td>
									${pageWgcl.proName}
								</td>
								<td>
									${pageWgcl.allCount}
								</td>
								<td>
									${pageWgcl.clCount}
								</td>
								<td>
									${pageWgcl.addUsercode}
								</td>
								<td>
									${pageWgcl.addUsername}
								</td>
								<td>
									<a href="CircuitRunAction_findAduitPage.action?id=${pageWgcl.epId}">
									${pageWgcl.epStatus}
									</a>
								</td>
						</s:iterator>
					</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">

</SCRIPT>
	</body>
</html>
