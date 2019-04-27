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
				<form action="pieceNum_add.action" method="post"
					onsubmit="validate()">
					<table>
						<tr>
							<th align="center" colspan="2">
								添加产品件号
							</th>
						</tr>
						<tr>
							<td>
								件号：
							</td>
							<td>
								<input type="text" id="pieceNumber" name="pn.pieceNumber"
									style="width: 200px;" />
							</td>
						</tr>
						<tr>
							<td>
								名字：
							</td>
							<td>
								<input type="text" id="name" name="pn.name" />
							</td>
						</tr>
						<tr>
							<td>
								车型：
							</td>
							<td>
								<input type="text" id="carType" name="pn.carType" />
							</td>
						</tr>
						<tr>
							<td>
								型别：
							</td>
							<td>
								<input type="text" id="type" name="pn.type" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="添加件号" />
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
		<script>
function validate() {
	var pieceNumber = document.getElementById("pieceNumber").value; //件号
	var name = document.getElementById("name").value; //名字
	var carType = document.getElementById("carType").value; //车型
	var type = document.getElementById("type").value; //型别
	if (pieceNumber == "") {
		alert("请输入产品件号!谢谢");
		return false;
	}
	if (name == "") {
		alert("请输入产品名字！谢谢");
		return false;
	}
	if (carType == "") {
		alert("请输入车型！谢谢");
		return false;
	}
	if (type == "") {
		alert("请输入型别！谢谢");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
