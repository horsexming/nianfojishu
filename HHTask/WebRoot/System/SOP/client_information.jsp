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
		<div id="gongneng">
			<div align="center">
				<h3>
					客户详细信息
				</h3>
				<form action="clientManager_initClientManager.action" method="post">
					<table class="table" style="width: 45%">
						<tr>
							<td style="size: 16px;" align="center">
								<STRONG>公司信息</STRONG>
							</td>
						</tr>
						<tr>
							<td>
								公司名称：${cl.clientcompanyname}
							</td>
						</tr>
						<tr>
							<td>
								公司性质：${cl.natureOfBusiness}
							</td>
						</tr>
						<tr>
							<td>
								公司法人：${cl.legalPerson}
							</td>
						</tr>
						<tr>
							<td>
								时间：${cl.clientdatatime}
							</td>
						</tr>
						<tr>
							<td>
								开户行：${cl.banks}
							</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="cl.businessLicense"
									value="${cl.businessLicense}" />
								<input type="hidden" name="cl.organization"
									value="${cl.organization}" />
								<input type="hidden" name="cl.logo" value="${cl.logo}" />
							</td>
						</tr>
						<tr>
							<td>
								公司地址：${cl.address}
							</td>
						</tr>
						<tr>
							<td style="size: 16px;" align="center">
								<STRONG>联系人信息</STRONG>
							</td>
						</tr>
						<tr>
							<td>
								联系人：${cl.clientname}
							</td>
						</tr>
						<tr>
							<td>
								性别：${cl.clientsex }
							</td>
						</tr>
						<tr>
							<td>
								职位：${cl.clientposition}
							</td>
						</tr>
						<tr>
							<td>
								所在部门：${cl.clientdept}
							</td>
						</tr>
						<tr>
							<td>
								手机号：${cl.clientmobilenumber}
							</td>
						</tr>
						<tr>
							<td>
								电话号码：${cl.clientphonenumber}
							</td>
						</tr>
						<tr>
							<td>
								身份证号：${cl.clientcardnumber}
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="button" value="查询客户订单" onclick="detail(${cl.id})"
									style="width: 100px; height: 50px;" />
								<input type="submit" value="返回"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function detail(id) {
	window.location = "clientManager_detail.action?id=" + id+"&pageStatus=zhuanhuan";
}
</script>
	</body>
</html>
