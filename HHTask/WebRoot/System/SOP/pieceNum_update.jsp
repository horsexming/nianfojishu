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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
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
			<form action="pieceNum_update.action" method="post" onsubmit="validate()">
				<table>
						<tr>
							<th align="center" colspan="2">修改产品件号</th>
						</tr>
						<tr>
							<td>件号：</td><td><input type="text" id="pieceNumber" name="pn.pieceNumber" style="width: 200px;" value="${pn.pieceNumber }"/></td>
						</tr>
						<tr>
							<td>名字：</td><td><input type="text" id="name" name="pn.name" value="${pn.name }"/></td>
						</tr>
						<tr>
							<td>车型：</td><td><input type="text" id="name" name="pn.carType" value="${pn.carType }"/></td>
						</tr>
						<tr>
							<td>型别：</td><td><input type="text" id="name" name="pn.tyoe" value="${pn.type }"/></td>
						</tr>
						<tr>
							<input type="hidden" name="pn.id" value="${pn.id }"/>
							<td align="center" colspan="2"><input type="submit" value="修改件号" style="width: 80px; height: 50px;"/></td>
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
				var pieceNumber = document.getElementById("pieceNumber").value; 
				var name = document.getElementById("name").value; 
				if(pieceNumber == ""){
					alert("请输入产品件号!谢谢");
					return false;
				}
				if(name == ""){
					alert("请输入名字！谢谢");
					return false;
				}
				return true;
			}
			</script>
	</body>
</html>
