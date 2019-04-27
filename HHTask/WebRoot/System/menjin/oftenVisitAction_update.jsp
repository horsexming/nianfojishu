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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="InEmployeeCarInforAction_toadd.action"
						style="color: rgb(79, 77, 77)";>刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					修改部员车辆信息
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="InEmployeeCarInforAction_update.action?tag=${tag}"
					method="post" enctype="multipart/form-data"
					onsubmit="return validate()">
					<table class="table">
						<tr style="width: 100%">
							<th align="right">
								被访人部门
								<input type="hidden" name="tag" value="cf" />
								<input type="hidden" name="all" value="${all}" />
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.ndept" id="ndept"
									value="${inEmployeeCarInfor.ndept}" readonly="readonly" />
							</td>
							<th align="right">
								被访人姓名
								<input type="hidden" name="inEmployeeCarInfor.id"
									value="${inEmployeeCarInfor.id}" />
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.name" id="name"
									readonly="readonly" value="${inEmployeeCarInfor.name}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								被访人工号
							</th>
							<td align="left" colspan="3">
								<input type="text" name="inEmployeeCarInfor.ncode" id="ncode"
									readonly="readonly" value="${inEmployeeCarInfor.ncode}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								常访人姓名
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.oftenname"
									id="oftenname" value="${inEmployeeCarInfor.oftenname}" />
							</td>
							<th align="right">
								常访人性别
							</th>
							<td align="left">
								<SELECT name="inEmployeeCarInfor.oftenSex" id="oftenSex"
									style="width: 156px;">
									<option value="${inEmployeeCarInfor.oftenSex}">
										${inEmployeeCarInfor.oftenSex}
									</option>
									<option value="男">
										男
									</option>
									<option value="女">
										女
									</option>
								</SELECT>
							</td>
						</tr>
						<tr>
							<th align="right">
								常访人车牌号
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.nplates"
									id="nplates" value="${inEmployeeCarInfor.nplates}" />
							</td>
							<th align="right">
								常访人手机号
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.oftenTel"
									maxlength="11" id="oftenTel"
									value="${inEmployeeCarInfor.oftenTel}" />
								<font color="red"><span id="errorMessage"></span> </font><font
									color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								汽车行驶证附件
							</th>
							<td align="left" colspan="1">
								<input type="file" name="fujian" id="carFiles" />
								<a href="<%=path%>${inEmployeeCarInfor.carFiles}">查看附件</a>
								<input type="hidden" name="inEmployeeCarInfor.carFiles"
									id="carFiles" value="${inEmployeeCarInfor.carFiles}" />
							</td>
							<th align="right">
								车型品牌
							</th>
							<td align="left">
								<input type="text" name="inEmployeeCarInfor.carModels"
									id="carModels" value="${inEmployeeCarInfor.carModels}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								常访原因
							</th>
							<td align="left" colspan="3">
								<input type="text" name="inEmployeeCarInfor.oftenInfor"
									id="oftenInfor" value="${inEmployeeCarInfor.oftenInfor}"
									style="width: 60%;" />
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" value="修改(update)"
									style="width: 100px; height: 50px;" />
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
	if (!validateText("ndept", "被访人部门")) {
		return false;
	}
	if (!validateText("name", "被访人名称")) {
		return false;
	}
	if (!validateText("ncode", "被访人工号")) {
		return false;
	}
	if (!validateText("oftenname", "常访人姓名")) {
		return false;
	}
	if (!validateText("oftenSex", "常访人性别")) {
		return false;
	}
	if (!validateText("nplates", "常访人车牌号")) {
		return false;
	}
	if (!validateText("oftenTel", "常访人手机号")) {
		return false;
	}
	if (!validateText("oftenInfor", "常访原因")) {
		return false;
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
$("#oftenTel").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
	</body>
</html>
