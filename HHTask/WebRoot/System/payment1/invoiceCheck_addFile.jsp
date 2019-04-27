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
		<style type="text/css">
.tr {
	height: 50px;
}

.ant-input-lg {
	padding: 6px 7px;
	height: 32px;
}

.ant-input {
	position: relative;
	display: inline-block;
	padding: 4px 7px;
	width: 100%;
	width: 280px;
	height: 28px;
	font-size: 12px;
	line-height: 1.5;
	color: rgba(0, 0, 0, 0.65);
	background-color: #fff;
	background-image: none;
	border: 1px solid #d9d9d9;
	border-radius: 4px;
	transition: all .3s;
}

.ant-form-explain,.ant-form-extra {
	color: #f04134;
	line-height: 1.5;
	display: none;
}

.has-error .ant-form-explain,.has-error .ant-form-split {
	color: #f04134;
}

.has-success.has-feedback:after {
	content: '\E630';
	color: #00a854;
}

.ant-btn-lg {
	padding: 0 15px;
	font-size: 14px;
	border-radius: 4px;
	height: 32px;
}

.ant-btn,.ant-btn:active,.ant-btn:focus {
	outline: 0;
}

.ant-btn {
	display: inline-block;
	margin-bottom: 0;
	font-weight: 500;
	text-align: center;
	touch-action: manipulation;
	cursor: pointer;
	background-image: none;
	border: 1px solid transparent;
	white-space: nowrap;
	line-height: 1.15;
	padding: 0 15px;
	font-size: 12px;
	border-radius: 4px;
	height: 28px;
	user-select: none;
	transition: all 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);
	position: relative;
	color: rgba(0, 0, 0, 0.65);
	background-color: #fff;
	border-color: #d9d9d9;
}

.ant-btn-primary {
	border-color: #fff !important;
}

.ant-btn-primary {
	background: #2397CA !important;
}
<%--input::-webkit-input-placeholder{--%>
<%--    color:red;--%>
<%--}--%>
<%--input::-webkit-input-placeholder{--%>
<%--    color:red;--%>
<%--}--%>
<%--input::-webkit-input-placeholder{--%>
<%--    color:green;--%>
<%--}--%>
input::-moz-placeholder{   /* Mozilla Firefox 19+ */
    color:red;
}
input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
    color:red;
}
input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
    color:red;
}
</style>
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
					<a href="" style="color: rgb(79, 77, 77)"><br /> </a>
				</div>
			</div>

			<div align="center">
				<h2>
					<input onclick="location.href='FundApplyAction_toAddInvoce.action'" type="button" value="增值税发票" style="background-color: rgb(52, 76, 124); color: #fff; width: 100px; height: 25px;">
					<input onclick="location.href='FundApplyAction_toAddInvoceQuota.action'"  type="button" value="定额发票" style="background-color: rgb(52, 76, 124); color: #fff; width: 100px; height: 25px;">
					<input type="button" value="电子发票" style="background-color: gary; color: #000; width: 100px; height: 25px;">
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h2>
				<br/>
				<br/>
				<form action="FundApplyAction_addInvoceFile.action" method="post"
					onsubmit="return validate()" enctype="multipart/form-data">
					<font size="5px;">OCR发票录入</font>
					<table>
						<tr class="tr">
							<td align="right">
								发票文件(图片或PDF)：
								<input type="hidden" name="pagestatus" value="code"/>
								<input type="hidden" name="invoiceCheckRecording.invoiceFilees" value=""/>
							</td>
							<td align="left">
								<input name="fujian"
									class="ant-input ant-input-lg" id="invoFile" 
									type="file"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="提交"
									class="ant-btn ant-btn-primary ant-btn-lg"
									style="width: 56px; height: 28px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
			hspace="0" vspace="0" frameborder="0" scrolling="yes"
			style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("invoFile", "发票图片")) {
		return false;
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "" || textValue == "0") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}


function add() {
	var url = "<%=request.getContextPath()%>/AccessEquipmentAction_toAddResAccessJi.action";
	$("#showProcess").attr("src", url);
}
<%--$("#cabOpenOrder").keyup(function() {--%>
<%--	var tmptxt = $(this).val();--%>
<%--	$(this).val(tmptxt.replace(/\D|^0/g, ''));--%>
<%--})--%>
</script>
	</body>
</html>
