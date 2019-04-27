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
		table {
		}
		table td {
		text-align:center;
}
	</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; background: url('<%=basePath%>images/title.jpg') no-repeat;"
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
				</div>
			</div>
			<div align="center">
				<form action="QualityccAction!addQualitycheckto.action" method="post" id="xform">
					<table class="table" style="width: 85%;">
						<tr>
							<th style="stress: 16px;" align="center" colspan="4">
								添加质量审核表模板
							</th>
						</tr>
						<tr>
							<td>	产品图号：</td><td>
								<input type="text" name="qualitycheckto.leibie" id="leibie"
									 />
								
							</td>
						
							<td>客户名称：</td><td>
								<input type="text" name="qualitycheckto.kehu" id="kehu" 
									 />
								
							</td>
						</tr>
						<tr>
							<td>
							产品名称：</td><td>
								<input type="text" name="qualitycheckto.productname" id="mingcheng"
									 />
							</td>
						
							<td>
								引用标准：</td>
							<td><input  type="text" name="qualitycheckto.yybz"  id="yybzz"/>
							</td>
						</tr>
						<tr>
							<td>备 &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp注：</td><td>
								<input  type="text" name="qualitycheckto.beizhu" />
							</td>
							<td></td>
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<input type="button" value="添加"  onclick="tijiao();"
								style="width: 80px; height: 50px;" />
								<input type="reset" value="重置"
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
			<script type="text/javascript">
function kehuCheck0(){
 	var testValue=document.getElementById("kehu").value;
 	 	if(testValue==""){
 		alert("请填写客户!");
 		return false;
 	}
 	
 }
 function tijiao(){
 	var kehu=document.getElementById("kehu").value;
 	var mingcheng=document.getElementById("mingcheng").value;
 	var leibie=document.getElementById("leibie").value;
 	var yybzz=document.getElementById("yybzz").value;
 	if(kehu==""){
 		alert("请填写客户!");
 		return false;
 	}else if(mingcheng==""){
 		alert("请填写产品名称!");
 		return false;
 	}else if(leibie==""){
 		alert("请填写产品类别!");
 		return false;
 	}else if(yybzz==""){
 		alert("请填写引用标准!");
 		return false;
 	}else{
	document.forms.xform.action = "QualityccAction!addQualitycheckto.action";
	document.forms.xform.submit();
	}
}
</script>
	</body>
</html>
