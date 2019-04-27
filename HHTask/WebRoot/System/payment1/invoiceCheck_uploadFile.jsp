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
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h2>
					<font size="5px;">发票文件上传</font>
				</h2>
				<form id="fromFile" method="post"enctype="multipart/form-data">
					<table>
						<tr class="tr">
							<td align="left">
								发票文件(图片或PDF)：
								<input type="hidden" name="invoiceCheck.id" value="${param.id}" />
							</td>
						</tr>
						<tr>
							<td align="left">
								<input name="fujian" class="ant-input ant-input-lg" id="invoFile" 
									type="file"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<br>
								<input type="submit" value="提交" onclick="validate()"
									class="ant-btn ant-btn-primary ant-btn-lg"
									style="width: 56px; height: 28px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	if (!validateText("invoFile", "发票文件")) {
		return false;
	}
	var form = new FormData(document.getElementById("fromFile"));
   	$.ajax({
        url:"${pageContext.request.contextPath}/FundApplyAction_uploadInvoiceCheckFile.action",
        type:"post",
        data:form,
        processData:false,
        contentType:false,
        async : false, 
        success:function(data){
        	alert(data);
        	if(data.indexOf("成功")>0){
        		window.parent.location.reload();
        	}
        },error:function(){
       	 
        }
   	});
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "" || textValue == "0") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
