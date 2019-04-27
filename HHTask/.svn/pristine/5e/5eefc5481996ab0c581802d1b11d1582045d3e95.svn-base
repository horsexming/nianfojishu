<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
				</div>
				
				<div align="center">
					<form action="ClientManagementAction!conditionsinquires.action"
						method="post">
						<table align="center" style="width: 100%" border="1">
							<tr>
								<th colspan="6">
									<font>查询所有客户信息</font>
								</th>
							</tr>
							<tr>
								<th>
									客户姓名
								</th>
								<td>
									<input type="text" name="clientManagement.clientname" />
								</td>
								<th>
									公司名称
								</th>
								<td>
									<input type="text" name="clientManagement.clientcompanyname" />
								</td>
								<th>
									职位
								</th>
								<td>
									<input type="text" name="clientManagement.clientposition" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td colspan="5">
									<select name="clientManagement.clientsex">
										<option value=""></option>
										<option value="男">
											男
										</option>
										<option value="女">
											女
										</option>
									</select>
									&nbsp;&nbsp;
									<input type="submit" value="确  定" />
									&nbsp;&nbsp;
									<a href="ClientManagementAction!findAll.action">查看所有</a>
								</td>
							</tr>
						</table>
					</form>
					<table align="center" width="100%" border="0"
						style="border-collapse: collapse;">
						<tr>
							<th>
								客户姓名
							</th>
							<th>
								性别
							</th>
							<th>
								所在部门
							</th>
							<th>
								职位
							</th>
							<th>
								手机号码
							</th>
							<th>
								电话号码
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator id="listall" value="list" status="stauts">
							<s:if test="#stauts.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${listall.clientname}
							</td>
							<td>
								${listall.clientsex}
							</td>
							<td>
								${listall.clientdept}
							</td>
							<td>
								${listall.clientposition}
							</td>
							<td>
								${listall.clientmobilenumber}
							</td>
							<td>
								${listall.clientphonenumber}
							</td>
							<td>
								<a href="ClientManagementAction!delete.action?id=${listall.id}"
									onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>&nbsp;&nbsp;
								<a
									href="ClientManagementAction!updatefind.action?id=${listall.id}">修改</a>&nbsp;&nbsp;
								<a
									href="ClientManagementAction!findByclientManagement.action?id=${listall.id}">查看详细</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
					</table>
				</div>
				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
