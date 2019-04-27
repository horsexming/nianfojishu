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
				<form action="" method="post">
					<table class="table">
						<tr>
						
						</tr>
					</table>
				</form>
			
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							银行
						</th>
						<th align="center">
							账户
						</th>
						<th align="center">
							金额
						</th>
						<th align="center">
							共收
						</th>
						<th align="center">
							共付
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="bankAccountsList" id="pageBankAccounts"
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>

						</td>
						<td>

						</td>
						<td>

						</td>
						<td>

						</td>
						<td>
							<a href="">明细</a>
						</td>
						</tr>
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
						</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
