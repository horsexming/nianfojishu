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
		<style type="text/css">
.table2 {
	font-size: 16px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	border-width: 0px 0 0 0px;
	width: 100%;
}

.table2 th,.table2 td {
	border-width: 0 0px 0px 0;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div align="center" id="gongneng">
			<div id="xieyi" align="center"
				style="width: 756px; height: 1086px; border: 0px solid #000000;">
				<%-- 
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				--%>
					<table align="center" style="border: 0px;">
						<tr style="height: 50px;">
							<th align="center" colspan="2" style="size: 20pt;">
								<font style="font-size: 26px">${companyInfo.name}终止劳动关系协议书</font>
								<input type="hidden" name="dimissionLog.id"
									value="${dimissionLog.id}" />
								<font color="red">${errorMessage}</font>
							</th>
						</tr>
						<tr style="height: 30px;">
							<th colspan="2" align="right">
								编号：${dimissionXieYi.xieyi_number}&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr style="height: 25px;">
							<th align="left">
								&nbsp;甲方：${dimissionXieYi._Afang}
							</th>
							<th  align="left">
								&nbsp;法定代表人：${dimissionXieYi._AfangRepresentative}
							</th>
						</tr>
						<tr style="height: 25px;">
							<th colspan="2" align="left">
								&nbsp;地址：${dimissionXieYi._Aaddress}
							</th>
						</tr>
						<tr style="height: 35px;">
							<th colspan="2" align="left">
								&nbsp;乙方：${dimissionXieYi._Bfang}
							</th>
						</tr>
						<tr style="height: 25px;">
							<th align="left" style="width: 375px;">
								&nbsp;身份证号：${dimissionXieYi._Buid}
							</th>
							<th align="left">
								&nbsp;电话号码：${dimissionXieYi._Btel}
							</th>
						</tr>
						<tr style="height: 25px;">
							<th colspan="2" align="left">
								&nbsp;户籍地址：${dimissionXieYi._Baddress}
							</th>
						</tr>
						<tr style="height: 25px;">
							<th colspan="2" align="left">
								&nbsp;函件送达地址：${dimissionXieYi._BrealAddress}
							</th>
						</tr>
						<tr style="height: 25px;">
							<td colspan="2">
								<SPAN style="font-size: 9px; font-weight: bold;">（乙方确认上述联系方式和地址真实有效，如今后按上述方式和地址不能联系乙方将由乙方承担相应法律责任）</SPAN>
							</td>
						</tr>
						<tr style="height: 20px;">
							<th colspan="2">
								协议条款
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="left">
								</s:if>
								<s:else>
									<tr align="left">
								</s:else>
								<td align="left" colspan="2"
									style="font-weight: bold; height: 25">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						
						<tr style="height: 60px;">
							<th align="left">
								&nbsp;甲方：${dimissionXieYi._Afang2}
							</th>
							<th align="left">
								&nbsp;乙方：${dimissionXieYi._Bfang2}
							</th>
						</tr>
						<tr style="height: 60px;">
							<th align="left">
								&nbsp;法定代表人：
							</th>
							<td><br/></td>
						</tr>

						<tr style="height: 35px;">
							<th align="left">
								&nbsp;日 期：${dimissionXieYi._AaddDate}
							</th>
							<th align="left">
								&nbsp;日 期：${dimissionXieYi._BaddDate}
							</th>
						</tr>
					</table>
			</div>
			<div>
				<input type="submit" value="打印" onclick="pagePrint('xieyi','')"
					style="width: 80px; height: 50px;" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function dimiss_ZYAdd() {

}

function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
