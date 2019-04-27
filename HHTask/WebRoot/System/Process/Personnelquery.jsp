<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<body onload="createDept('selectDept')">
		<div id="bodyDiv" align="center"
			style="filter: Alpha(Opacity =           75); display: none; position: absolute; width: 100%; background: #000; opacity: 1;">
		</div>

		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>

				</div>
			</div>
			
			
			<div align="center">

				<div align="center">
					<form action="ProcessManagementAction!findas.action" method="post"
						style="margin: 0px;">
						<table align="center">
							<tr>
								<th colspan="8" style="color: red;">
									模板名称管理
								</th>
							</tr>

						</table>
					</form>

					<table width="100%" border="0" style="border-collapse: collapse;"
						class="table">
						<tr bgcolor="#c0dcf2" height="30px">
							<th align="center">
								序号
							</th>
							<th align="center">
								模板名称
							</th>

							<th align="center">
								操作
							</th>

						</tr>
						<s:iterator value="assessPersonnelList" id="pageecondition"
							status="pageStatus">
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
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="red">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>

							<td>

								${pageecondition.templateb}
							</td>

							<td>
								<a
									href="ProcessManagementAction!delSubmit.action?id=${pageecondition.id}"
									onClick="return window.confirm('确认要删除吗？')">定制流程</a>|
								<a
									href="ProcessManagementAction!delSubmit.action?id=${pageecondition.id}"
									onClick="return window.confirm('确认要删除吗？')">删除</a>
							</td>
							<s:if test="#pageStatus.last">
								</tr>
							</s:if>
						</s:iterator>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="11" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="11" align="center" style="color: red">
									${errorMessage}
								</td>
							</s:else>

						</tr>
					</table>

				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
	</body>
</html>
