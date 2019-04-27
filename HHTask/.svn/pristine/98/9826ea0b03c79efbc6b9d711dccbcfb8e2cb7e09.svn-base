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
		<SCRIPT type="text/javascript">
		$(function() {
			//加载所有件号
			$.ajax( {
				type : "post",
				url : "ProcardTemplateAction!findUpdateMarkId.action?type=${type}",
				dataType : "json",
				success : function(data) {
					//填充件号信息
					$(data).each(
						function() {
							$(
								"<option value='" +this.id+ "_" +this.markId + "_"+this.standardSize + "_" + this.errorRange + "'>" + this.markId + "</option>").appendTo("#markId_1");
						});
					$("#markId_1").tinyselect();
				}
			});
		});
		</SCRIPT>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div align="center">
					<h3>件号</h3>
				</div>
			</div>
			<div align="center" style="width: 100%;">
				<form action="ProcardTemplateAction!updateMarkIdCard.action?type=${type}" method="post" onsubmit="return yanAdd()">
					<table align="center" class="table"
					id="tableb">
						<tbody>
							<tr align="center">
								<th>请选择件号</th>
								<td align="center">
									<SELECT id="markId_1" style="width: 157px;" onchange="picihuoqu()">
										<option >
											请选择件号
										</option>
									</SELECT>
									<input type="hidden" name="procardTemplate.id" id="mark_id"/>
									<input type="hidden" name="procardTemplate.markId" id="markId_id"/>
								</td>
								<td align='center' colspan="2">
									<s:if test="type=='yes'">
										<input type="submit" style="height: 33px;width: 65px;" value='修改' />
									</s:if>
									<s:else>
										<input type="submit" style="height: 33px;width: 65px;" value='添加' />
									</s:else>
								</td>
							</tr>
							<tr >
								<th align='center'>件号标准尺寸
								</th>
								<td align='center'>
									<input type='text' name='procardTemplate.standardSize' id='standardSize' /> mm
								</td>
								<th align='center'>件号允许误差尺寸
								</th>
								<td align='center'>
									±<input type='text' name='procardTemplate.errorRange' id='errorRange'/> mm
								</td>
							</tr>
						</tbody>
					</table>
					<div style="margin-top:5px" align="center" >
						<SPAN style="color: red;font-size: 26px;">${errorMessage}</SPAN>
					</div>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function picihuoqu(){
	var zhi = $("#markId_1").val();
	var allzhi = zhi.split("_");
	$("#mark_id").val(allzhi[0]);
	$("#markId_id").val(allzhi[1]);
	if(allzhi[2] != null && allzhi[2] != "" && allzhi[2] != "null")
		$("#standardSize").val(allzhi[2]);
	if(allzhi[3] != null && allzhi[3] != "" && allzhi[3] != "null")
		$("#errorRange").val(allzhi[3]);
}

function markIdNotNull() {
	if ($("#markId_1").val() == "" || $("#markId_1").val() == "请选择件号") {
		alert("件号不能为空！");
		return false;
	}else{
		$("#selfCard0").val($("#piciNum_1").val());
	}
	
}

function yanAdd(){
	if (!validateText("markId_id", "件号")) {
		return false;
	}
	if (!validateText("standardSize", "标准尺寸")) {
		return false;
	}
	if (!validateText("errorRange", "误差范围")) {
		return false;
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "" || textValue == "请选择件号") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

		</SCRIPT>
	</body>
</html>
