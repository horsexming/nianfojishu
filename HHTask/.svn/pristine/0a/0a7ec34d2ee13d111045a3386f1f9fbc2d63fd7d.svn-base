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
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
			<div align="center">
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td align="center">序号</td>
						<td align="center">工号</td>
						<td align="center">姓名</td>
						<td align="center">部门</td>
						<td align="center">用户组</td>
						<td align="center">操作</td>
					</tr>
					<s:iterator id="u" value="proUserList" status="st">
						<tr>
							<td>${st.index + 1} </td>
							<td>${u.user.code}</td>
							<td>${u.user.name}</td>
							<td>${u.user.dept}</td>
							<td>${u.userGroup}</td>
							<td>
								<a  href="proUserAction!getProUserAddPage.action?proUser.proId=${u.proId}">添加</a>&nbsp;
								<a  href="proUserAction!getProUserUpdatePage.action?proUser.id=${u.id}">修改</a>&nbsp;
								<a  onclick="return window.confirm('确认要删除该人员记录?')" href="proUserAction!deleteProUser.action?proUser.id=${u.id}">删除</a>&nbsp;
							</td>
							
						</tr>
					</s:iterator>
					
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="9" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
