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
				<s:if test="listaccountsDate!=null  && listaccountsDate.size()>0">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								财务记账日期
							</th>
							<th>
								库存成本核算方法
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator id="pagelist" value="listaccountsDate"
							status="statussdf">
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
								<s:property value="#statussdf.index+1" />
							</td>
							<td>
								${pagelist.jihao}
							</td>
							<td>
								<s:if test="#pagelist.goodsType=='xjxc'">
									先进先出</s:if>
								<s:elseif test="#pagelist.goodsType=='xjhc'">先进后出</s:elseif>
								<s:elseif test="#pagelist.goodsType=='monthAgv'">加权平均</s:elseif>
								<s:elseif test="#pagelist.goodsType=='allAgv'">移动加权平均</s:elseif>
							</td>
							<td>
								<a
									href="MeasuringAction_delAccountsDate.action?accountsDate.id=${pagelist.id}"
									onclick="confirm('确定要删除吗？')">删除</a>
							</td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<br />
					<br />
					<br />
					<form action="MeasuringAction_addAccountsDate.action" method="POST">
						财务结账日:
						<input type="text" value="${accountsDate.jihao}"
							name="accountsDate.jihao" onkeyup="numyanzheng(this,'zhengshu')"
							onblur="numyanzheng(this,'zhengshu')" />
						<br />
						<br />
						库存成本核算方法:
						<select name="accountsDate.goodsType">
							<option value="xjxc">
								先进先出
							</option>
							<option value="xjhc">
								先进后出
							</option>
							<option value="monthAgv">
								加权平均
							</option>
							<option value="allAgv">
								移动加权平均
							</option>
						</select>
						<br />
						<br />
						<input type="submit" value="提交" class="input" />
					</form>
				</s:else>

			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
