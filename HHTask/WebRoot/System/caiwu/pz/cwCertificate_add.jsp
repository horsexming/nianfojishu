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
	<body onload="dangan()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h3>
					<s:if test="tag=='pz'">
						添加财务档案信息
					</s:if>
					<s:else>
						添加发票信息
					</s:else>
				</h3>
				<form action="CwCertificateAction_addCwCertificate.action?tag=${tag}" method="post"
				 onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th>
								编号：
							</th>
							<th align="left">
								<input id="number" type="text" name="cwCertificate.number"/>
							</th>
							<th>
								日期：
							</th>
							<td align="left">
								<input id="pzDate" class="Wdate" id="pzDate" type="text" name="cwCertificate.pzDate"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
								/>
							</td>
						</tr>
						<tr>
							<th>
								档案柜号：
							</th>
							<td align="left">
								<SELECT id="dangangui">
								</SELECT>
								<input id="danganguiNum" type="hidden" name="cwCertificate.danganguiNum" />
								<input id="danganguiId" type="hidden" name="cwCertificate.danganguiId" />
							</td>
							<th>
							<s:if test="tag=='pz'">
								附件张数：
							</s:if>
							<s:else>
								发票张数：
							</s:else>
							</th>
							<td align="left">
								<input id="fujianNum" type="text" name="cwCertificate.fujianNum" />
							</td>
						</tr>
						<tr>
							<th>
								
								<s:if test="tag=='pz'">
									简介：
								</s:if>
								<s:else>
									发票类型：
								</s:else>
							</th>
							<td align="left" colspan="3">
								<input id="introduction" type="text" name="cwCertificate.introduction"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="添加(Add)"
									style="width: 80px; height: 30px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("number", "编号")) {
		return false;
	}
	if (!validateText("dangangui", "档案柜号")) {
		return false;
	}
	if ('${tag}'=='fp') {
		if (!validateText("fujianNum", "发票张数")) {
			return false;
		}
		if (!validateText("introduction", "发票类型")) {
			return false;
		}
	}else{
		if (!validateText("pzDate", "日期")) {
			return false;
		}
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function dangan(){
	$.ajax( {
		type : "POST",
		url : "AccessEquipmentAction_finAllGuihao.action",
		data : {
			tag:'${tag}'
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(data) {
			$("#dangangui").empty();//清空
			$("<option value=''>选择柜号</option>").appendTo("#dangangui")
			$(data).each(
					function() {
						$( 
								"<option value='" + this.id + "|"
										+ this.cabinetNum + "'>"
										+ this.cabinetNum + "|" +this.cabinetType + "</option>")
								.appendTo("#dangangui")
					});
			$("#dangangui").bind("change", function() {
				var name = $("#dangangui").val();
				var guihaoData = name.split("|");
				var ghId = guihaoData[0];
				var ghNum = guihaoData[1];
				$("#danganguiNum").val(ghNum);
				$("#danganguiId").val(ghId);
			});
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
</script>
	</body>
</html>
