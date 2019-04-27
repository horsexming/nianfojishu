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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong">
			
			<div align="center" >
			添加成功
<%--				<font color="red">${successMessage}</font>--%>
			</div>
<%--			<div style="font-weight: bold; margin-top: 10px;border: solid #000000 1px;" align="center">--%>
<%--				<form action="OsTemplate_add.action" method="post" onsubmit="return yanzhen()">--%>
<%--					<table>--%>
<%--						<tr>--%>
<%--							<td align="center" colspan="2"><h3>添加模板</h3></td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								种&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<select name="t.ctype1" style="width:150px " id="ctype1">--%>
<%--			   					<option value=""></option>--%>
<%--									<option value="外购">外购</option>--%>
<%--									<option value="自治">自治</option>--%>
<%--								</select>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<input type="text" name="t.cmodel" id="cmodel"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<select name="t.ctype" style="width:150px " id="ctype">--%>
<%--			   					<option value=""></option>--%>
<%--									<option value="端盖">端盖</option>--%>
<%--									<option value="隔盘">隔盘</option>--%>
<%--									<option value="内管">内管</option>--%>
<%--									<option value="外管">外管</option>--%>
<%--									<option value="吊钩">吊钩</option>--%>
<%--									<option value="法兰">法兰</option>--%>
<%--									<option value="护板">护板</option>--%>
<%--									<option value="岩棉">岩棉</option>--%>
<%--									<option value="筒体">筒体</option>--%>
<%--									<option value="螺帽">螺帽</option>--%>
<%--									<option value="其它">其它</option>--%>
<%--									<option value="螺纹嘴">螺纹嘴</option>--%>
<%--									<option value="净化器">净化器</option>--%>
<%--									<option value="波纹管">波纹管</option>--%>
<%--									<option value="玻璃纤维">玻璃纤维</option>--%>
<%--								</select>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<input type="text" name="t.name" id="name"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<input type="text" name="t.partNumber" id="partNumber"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								材&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<input type="text" name="t.material" id="material"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<input type="text" name="t.serialNumber" id="serialNumber"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td align="right">--%>
<%--										上传附件:<br/>--%>
<%--									</td>--%>
<%--									<td colspan="5">--%>
<%--										<input type="button" id="fileButton_1"--%>
<%--											onclick="uploadFile(this,1)" value="上传附件">--%>
<%----%>
<%--										<div id="fileDiv_1" style="display: none;">--%>
<%----%>
<%--										</div>--%>
<%--									</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="2" align="center">--%>
<%--								<input type="submit" value="提交" id="submit" />&nbsp;&nbsp;--%>
<%--								<input type="reset" value="重置"/>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</form>--%>
<%--			</div>--%>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
var fileDivHTML = "";
var count = 0;
function uploadFile(obj, few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	if (obj.value == "上传附件") {
		fileDiv.style.display = "block";
		obj.value = "添加文件";
	}
	fileDivHTML = "<div id='file"
			+ count
			+ "'><input type='file' name='attachment'><a href='javascript:delFile("
			+ count + "," + few + ")'>删除</a></div>";
	fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
	count++;
}
function delFile(obj, few) {
	document.getElementById("file" + obj).parentNode.removeChild(document
			.getElementById("file" + obj));
	count--;
	if (count <= 0) {
		count = 0;
		document.getElementById("fileButton_" + few).value = "上传附件";
		document.getElementById("fileDiv_" + few).style.display = "none";
	}
}
function yanzhen(){
	var ctype1=document.getElementById("ctype1");
	var cmodel=document.getElementById("cmodel");
	var ctype=document.getElementById("ctype");
	var name=document.getElementById("name");
	var partNumber=document.getElementById("partNumber");
	var material=document.getElementById("material");
	var serialNumber=document.getElementById("serialNumber");
	 if(ctype1!=null && ctype1.value==""){
		 alert("种类不能为空");
		 return false;
	 }else if(cmodel!=null && cmodel.value==""){
		 alert("车型不能为空");
		 return false;
	 }else if(ctype!=null && ctype.value==""){
		 alert("类型不能为空");
		 return false;
	 }else if(name!=null && name.value==""){
		 alert("名称不能为空");
		 return false;
	 }else if(partNumber!=null && partNumber.value==""){
		 alert("件号不能为空");
		 return false;
	 }else if(material!=null && material.value==""){
		 alert("材料不能为空");
		 return false;
	 }else if(serialNumber!=null && serialNumber.value==""){
		 alert("编号不能为空");
			return false;	
	 }else{
		 return true;
	 }
	 document.getElementById("submit").disabled="disabled";
	
}

</SCRIPT>
	</body>
</html>
