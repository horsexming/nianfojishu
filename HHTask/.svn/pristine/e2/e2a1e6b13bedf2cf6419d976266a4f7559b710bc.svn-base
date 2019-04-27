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
.sss {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

.sss th,.sss td {
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
					<form
						action="OrderManagementAction!conditionsfind.action?pageStatus=${pageStatus}"
						method="post">
						<table align="center" style="width: 100%" border="1">
							<tr>
								<th colspan="6">
									<font size="5">查看订单信息</font>
								</th>
							</tr>
							<tr>
								<th>
									订单编号
								</th>
								<td>
									<input type="text" name="orderManagement.ordernumber" />
								</td>
								<th>
									客户名称
								</th>
								<td>
									<input type="text" name="orderManagement.ordercompanyname" />
								</td>
								<th>
									概要
								</th>
								<td>
									<input type="text" name="orderManagement.ordersummary" />
								</td>
							</tr>
							<tr>
								<th>
									联系人
								</th>
								<td>
									<input type="text" name="orderManagement.ordername" />
								</td>

								<s:if test="pageStatus=='ptyh'">
									<th>
										负责人
									</th>
									<td colspan="3">
										<input type="text" name="orderManagement.orderpersoncharge"
											readonly="readonly" value="${name}" />
										&nbsp;&nbsp;
										<input type="submit" value="确  定" />
										&nbsp;&nbsp;
										<a
											href="OrderManagementAction!findAll.action?pageStatus=${pageStatus}">查看所有</a>
									</td>
								</s:if>
								<s:else>
									<th>
										创建人
									</th>
									<td colspan="3">
										<input type="text" name="orderManagement.ordercreatePeople" />
										&nbsp;&nbsp;
										<input type="submit" value="确  定" />
										&nbsp;&nbsp;
										<a
											href="OrderManagementAction!findAll.action?pageStatus=${pageStatus}">查看所有</a>
									</td>
								</s:else>
							</tr>
						</table>
					</form>
					<table align="center" class="sss">
						<tr>
							<th>
								订单编号
							</th>
							<th>
								客户名称
							</th>
							<th>
								联系人
							</th>
							<th>
								联系人电话
							</th>
							<th>
								创建人
							</th>
							<th>
								负责人
							</th>
							<th>
								创建时间
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
							<td align="center">
								${listall.ordernumber}
							</td>
							<td align="center">
								${listall.ordercompanyname}
							</td>
							<td align="center">
								${listall.ordername}
							</td>
							<td align="center">
								${listall.ordermobilenumber}
							</td>
							<td align="center">
								${listall.ordercreatePeople}
							</td>
							<td align="center">
								${listall.orderpersoncharge}
							</td>
							<td>
								${listall.ordercreatedatatime}
							</td>
							<td>
								<a href="OrderManagementAction!delete.action?id=${listall.id}"
									onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>
								<a
									href="OrderManagementAction!updatefind.action?id=${listall.id}">修改</a>
								<a
									href="OrderManagementAction!findDetailed.action?id=${listall.id}">查看详细</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="8" align="right">
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
