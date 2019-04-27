<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="favicon.ico" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<script type="text/javascript">
		</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng" align="left" style="width: 1000px;">
				<div id="xitong" align="center"
					style="width: 100%; height: 31px; font-weight: bold; margin-top: 15px; padding-top: 5px; padding-bottom: 5px; background: url('admin/images/admin_title.jpg');">
					<font color="#ffffff">添加首页</font>
				</div>
				
					<div align="center" id="addModule">
								<form id="addMf" action="" method="post"
									enctype="multipart/form-data">
									<input type="hidden" name="id" value="${moduleFunction.id}">
									<table class="table">
										<tr>
											<td align="right">
												功能名称:
											</td>
											<td>
												<input id="functionName" type="text"
													name="moduleFunction.functionName" />
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												功能名称(英文):
											</td>
											<td>
												<input type="text" name="moduleFunction.englishName" />
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												功能介绍:
											</td>
											<td>
												<textarea rows="8" cols="35"
													name="moduleFunction.functionIntro"></textarea>
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												图标:
											</td>
											<td>
												<input type="file" name="attachment" />
											</td>
										</tr>
										<tr>
											<td align="right">
												背景色:
											</td>
											<td>
												<input type="text" name="moduleFunction.bgColor"
													value="${moduleFunction.bgColor}" />
											</td>
										</tr>
										<tr>
											<td align="right">
												功能链接:
											</td>
											<td>
												<textarea rows="5" cols="40"
													name="moduleFunction.functionLink"></textarea>
												<font color="red">*</font>
											</td>
										</tr>
										<tr>
											<td align="right">
												新开页面:
											</td>
											<td>
												<input type="radio" name="moduleFunction.targetNewPage"
													value="yes">
												是
												<input type="radio" name="moduleFunction.targetNewPage"
													value="no" checked="checked">
												否
												<font color="red">*</font>
											</td>
										</tr>
										<tr id="dateTimeTr" style="display: none;">
											<td align="right">
												时间:
											</td>
											<td>
												从
												<input class="Wdate" type="text"
													name="moduleFunction.stratDateTime"
													onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
												到
												<input class="Wdate" type="text"
													name="moduleFunction.endDateTime"
													onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
												<br />
												本月
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center">
												<br>
												<input type="button" style="width: 100px; height: 50"
													onclick="chageForm1(this.form,'same')" value="添加同层">
												<input type="reset" style="width: 100px; height: 50px;"
													value="重置">
											</td>
										</tr>
									</table>
								</form>
							</div>
				
				
			</div>	
		</center>
	</body>
	<script type="text/javascript">
	$(function(){
		if(successMessage!=""){
			alert(successMessage);
			parent.location.reload(true);//刷新父页面
		}		
	})
	function chageForm1(form, status) {
	var functionName = document.getElementById("functionName");
	if (functionName.value == "") {
		alert("功能名称不能为空!");
		return false;
	} else {
		form.action = "ModuleFunctionAction!addMf1.action?pageStatus=" + status;
		form.submit();
	}
}
	
	
	</script>
</html>
