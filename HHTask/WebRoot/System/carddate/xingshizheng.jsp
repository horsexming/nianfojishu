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
				<form id="form" method="post">
					<table width="100%" border="0" class="table" bgcolor="#F0F0F0">
						<tr>
							<th colspan="8" align="center">
								<br />
								<font size="5px">行驶证</font>
								<br />
							</th>
						</tr>
						<tr>
							<th align="center">
								姓名:
							</th>
							<td>
								<input name="credentials.id" value="${credentials.id}"
									type="hidden">
								<input name="credentials.name" value="${credentials.name}">
								<input type="hidden" name="ps" value="${ps}">
							</td>
							<th align="center">
								姓别:
							</th>
							<td>
								<input name="credentials.sex" value="${credentials.sex}">
							</td>
							<th align="center">
								工号:
							</th>
							<td>
								<input name="credentials.code" value="${credentials.code}">
							</td>
							<th align="center">
								类型:
							</th>
							<td>
								<input name="credentials.cardtype" readonly="readonly"
									value="${credentials.cardtype}">
							</td>
						</tr>
						<tr>
							<th align="center">
								号牌号码:
							</th>
							<td>
								<input name="credentials.platenumber"
									value="${credentials.platenumber}">
							</td>
							<th align="center">
								车辆类型:
							</th>
							<td>
								<input name="credentials.vehicletype"
									value="${credentials.vehicletype}">
							</td>
							<th align="center">
								所有人:
							</th>
							<td>
								<input name="credentials.owner" value="${credentials.owner}">
							</td>
							<th align="center">
								品牌型号:
							</th>
							<td>
								<input name="credentials.model" value="${credentials.model}">
							</td>
						</tr>
						<tr>
							<th align="center">
								使用性质:
							</th>
							<td>
								<input name="credentials.usecharacter"
									value="${credentials.usecharacter}">
							</td>
							<th align="center">
								车辆识别代号:
							</th>
							<td>
								<input name="credentials.vin" value="${credentials.vin}">
							</td>
							<th align="center">
								发动机号码:
							</th>
							<td>
								<input name="credentials.enginenumber"
									value="${credentials.enginenumber}">
							</td>
							<th align="center">
								行驶证文件:
							</th>
							<td>
								<s:if test='ps=="update"'>
									<input type="file" id="file" name="credentials.credentialsfile">
								</s:if>
								<s:if test='ps!="update"'>
									<a href="<%=path%>${credentials.credentialsfile}">查看附件</a>
								</s:if>
							</td>
						</tr>
						<tr>
							<th align="center">
								有效起始日期:
							</th>
							<td>
								<input name="credentials.validfrom"
									value="${credentials.validfrom}">
							</td>
							<th align="center">
								有效期限:
							</th>
							<td>
								<input name="credentials.validfor"
									value="${credentials.validfor}">
							</td>
							<th align="center">
								周期:
							</th>
							<td>
								<input name="credentials.cycle" value="${credentials.cycle}">
							</td>
						</tr>
						<s:if test='tage=="2"'>
							<tr>
								<th colspan="8" align="center">
									<input type="button" style="width: 100px; height: 50px;"
										onclick="submitForm1()" value="修改">
								</th>
							</tr>
						</s:if>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function submitForm1(){
	$.ajax( {
		type : "POST",
		url : "CredentialsAction!update.action",
		dataType : "json",
		data : $("#form").serialize(),
		success : function(data) {
				alert(data);
				if(data=="修改成功"){
					parent.location.href="CredentialsAction!showallcredentials.action?cpage=${cpage}&ps=${ps}";
				}
	}
	});
}
</SCRIPT>
	</body>
</html>
