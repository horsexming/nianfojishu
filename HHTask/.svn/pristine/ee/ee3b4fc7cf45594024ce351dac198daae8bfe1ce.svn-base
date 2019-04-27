<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DmltryAppFiles_showid.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  
  <body>
  
  <div align="center">
			<h3>
				修改信息 <br />
				<s:if test="successMessage!=null">
					<font color="red">${successMessage}</font>
				</s:if>
			</h3>
  
  <form action="DmltryAppFilesAction!updateDmltryAppFiles.action" method="post" enctype="multipart/form-data">
  <table class="table">
  <tr>
  <td>id</td>
  <td><input type="text" value="${dmltryAppFiles2.id}" name="dmltryAppFiles.id" /></td>
  </tr>
  
   <tr>
   <td>名称</td>
   <td><input type="text" value="${dmltryAppFiles2.appFilename}" name="dmltryAppFiles.appFilename" /></td>
   </tr>
   
    <tr>
   <td>描述</td>
   <td><input type="text" value="${dmltryAppFiles2.appFilesmshu}"  name="dmltryAppFiles.appFilesmshu" /></td>
   </tr>
   <tr>
   <td><input type="submit" value="修改"/></td>
    <td><input type="reset" value="重置"/></td>
   </tr>
  </table>
	</form>  
		</div>
	</div>
	<%@include file="/util/foot.jsp"%>
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
