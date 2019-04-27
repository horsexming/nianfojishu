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
		<script type="text/javascript" src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="<%=path%>/System/shizhi/skillscore_add.jsp"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加关键备件<br/>
					（add skillscore）
				</h3>
				<form action="machineSparePartAction_add.action" method="post" enctype="multipart/form-data"
					onsubmit="return validate();">
					<table class="table">
					<tr>
							<th align="right">
								关键设备<br/>（name）：
							</th>
							<td>
								<SELECT id="keyMachine" name="machineSparePart.machine.id">
<%--								 <option value="0">请选择关键设备--%>
<%--								 </option>--%>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								备件编号<br/>（number）
							</th>
							<td>
								<input type="text" name="machineSparePart.number" id="number" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备件 名称<br/>（name）：
							</th>
							<td>
								<input type="text" name="machineSparePart.matetag" id="matetag" />
							</td>
						</tr>
						<tr>
							<th align="right">
								安全库存<br/>（total score）：
							</th>
							<td>
								<input value="0" type="text" name="machineSparePart.safeCount" id="safeCount" onblur="mustBeNumber('safeCount')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								备件单位<br/>（unit）：
							</th>
							<td>
								<input type="text" name="machineSparePart.unit" id="unit" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备件规格<br/>（format）：
							</th>
							<td>
								<input type="text" name="machineSparePart.format" id="format" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备件分类<br/>（parClass）：
							</th>
							<td>
								<input type="text" name="machineSparePart.parClass" id="parClass" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备件位置<br/>（difficult score）：
							</th>
							<td>
								<input type="text" name="machineSparePart.place" id="place" />
							</td>
						</tr>
						<tr>
							<th align="right">
								备件价格<br/>（price）：
							</th>
							<td>
								<input value="0" type="text" name="machineSparePart.price" id="price" onblur="mustBeNumber('price')"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								备件 车型<br/>（carModel）：
							</th>
							<td>
								<input type="text" name="machineSparePart.carModel" id="carModel" />
							</td>
						</tr>
						<tr>
						 <th align="right">
										添加时间:
									</th>
									<td>
										<input id="xbtime" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
											name="machineSparePart.addtime" />
										<font color="red">*</font>
									</td>
								</tr>
						<tr>
							<th align="right">
								备注<br/>（remake）：
							</th>
							<td>
								<input type="text" name="machineSparePart.remake" id="remake" />
							</td>
						</tr>
						<tr>
							<th align="right">
								设备图片<br/>（PIC.）：
							</th>
							<td>
								<input type="file" name="machineSparePart.picF" id="pic" onchange="validateFile();"/>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="提交(submit) "
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
function validate() {
if(!validateMsg("关键设备","keyMachine")){
	return false;
}

if(!validateMsg("编号","number")){
	return false;
}

if(!validateMsg("名称","matetag")){
	return false;
}

if(!validateMsg("单位","unit")){
	return false;
}

if(!validateMsg("规格","format")){
	return false;
}


if(!validateMsg("分类","parClass")){
	return false;
}
return true;
}
function getMachine(){
	$.ajax( {
		type : "post",
		url : "machineSparePartAction_getMachines.action",
		dataType : "json",
		success : function(data) {
			//填充设备信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "</option>").appendTo("#keyMachine");
						//userlist($("#deptname").val());
					});
		}
	});
}
$(document).ready(function(){
getMachine();
});
function validateMsg(msg,id){
	if($("#"+id).val()==null||$("#"+id).val()==""){
		alert(msg+"不能为空!");
		return false;
	}
	return true;
}
function validateFile(){ 
	var fileObject=$("#pic"); 
	var filepath=fileObject.val(); 
	var fileArr=filepath.split("//"); 
	var fileTArr=fileArr[fileArr.length-1].toLowerCase().split("."); 
	var filetype=fileTArr[fileTArr.length-1]; 
	if(filetype!="jpeg"&&filetype!="jpg"&&filetype!="bmp"&&filetype!="png"&&filetype!="gif"){ 
		var file = document.getElementById('pic');
		file.outerHTML = file.outerHTML; //重新初始化了file的html
		fileObject.focus(); 
		alert("上传文件必须为图片(.jpeg/.jpg/.bmp/.png/.gif)文件！"); 
	}
} 
</script>
	</body>
</html>
