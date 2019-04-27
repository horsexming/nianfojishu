<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px;"
			align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left"></div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
			</div>
		</div>

		<div align="center">
			<h3>
				添加明细信息 <br />
				<s:if test="successMessage!=null">
					<font color="red">${successMessage}</font>
				</s:if>
			</h3>

			<form action="DmlAppFileUrlAction!toadd.action" method="post"
				enctype="multipart/form-data">
				<table class="table">

					<tr>
						<td align="right">版本号：</td>
						<td ><input type="text" value="${dmlAppFileUr1.coide}"
							name="dmltryAppFiles.appFilename" /></td>
					</tr>

					<tr>
						<td align="right">附件名称：</td>
						<td><input type="text"
							value="${dmlAppFileUr1.appfileurlfj}"
							name="dmltryAppFiles.appFilesmshu" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit" value="添加" />
						<input type="reset" value="重置" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
		function validate() {
			if (!validateText("kemu", "科目")) {
				return false;
			}
			if (!validateText("jiluTime", "费用截止日")) {
				return false;
			}
			if ($("#kemu").val() == '水费' || $("#kemu").val() == '电费') {
				if (!validateText("lastbiaoshu", "上次读表数")) {
					return false;
				}
				if (!validateText("thisbiaoshu", "本次读表数")) {
					return false;
				}
			} else {
				if (!validateText("yingfuJine", "应收金额")) {
					return false;
				}
			}
			if (!validateText("zhuangtai", "收款状态")) {
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
		function shuid() {
			if ($("#kemu").val() == '水费' || $("#kemu").val() == '电费') {
				$("#biao").show();
				$("#lastbiaoshu").removeAttr("disabled");
				$("#thisbiaoshu").removeAttr("disabled");
				$("#zu").hide();
				$("#yingfuJine").attr("disabled", "disabled");
			} else {
				$("#zu").show();
				$("#yingfuJine").removeAttr("disabled");
				$("#biao").hide();
				$("#lastbiaoshu").attr("disabled", "disabled");
				$("#thisbiaoshu").attr("disabled", "disabled");
			}
		}
	</script>
</body>
</html>
