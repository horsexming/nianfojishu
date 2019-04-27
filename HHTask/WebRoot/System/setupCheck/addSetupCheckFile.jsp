<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body onload="createDept('dept');createDept('dept1');" >
<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
		<h4>添加纠正措施以及预防方案</h4>
			<form action="SetupCheckAction_updateWay.action"  method="post"  enctype="multipart/form-data" onsubmit="return validate()">
				<table class="table">
					<tr>
					  <th>原因分析
					  </th>
					  <td colspan="3">
					  <input type="hidden" name="tag" value="${tag}" id="tag"/>
					  <input type="hidden" name="setupCheck.id"
								value="${setupCheck.id}" id="id"/>
					   <textarea rows="5" cols="80" name="setupCheck.reason">${setupCheck.reason}</textarea>	
					  </td>
					  </tr>
					  <tr>
					  <th>纠正措施
					  </th>
					  <td colspan="3">
					  	 <textarea rows="5" cols="80" name="setupCheck.shortWay">${setupCheck.shortWay}</textarea>
					  </td>
					</tr>
					<tr>
					  <th>纠正措施负责人
					  </th>
					  <td>
					  所属部门：&nbsp;&nbsp;&nbsp;&nbsp;
					  <SELECT id="dept" onblur="deptNotNull();">
					  	<option value=""></option>
					  	</SELECT>
					  &nbsp;&nbsp;&nbsp;&nbsp;负责人：
					  	<SELECT id="name" style="width: 155px;">
					 		<option value="${setupCheck.shortPerson}">${setupCheck.shortPerson}</option>
					  	</SELECT>
					  	<input id="bfName" name="setupCheck.shortPerson"  type="hidden" value="${setupCheck.shortPerson}">
					  	<input id="code" name="setupCheck.shortId"  type="hidden" value="${setupCheck.shortId}">
					  </td>
					  <th>纠正措施完成日期
					  </th>
					  <td>
					  	<input name="setupCheck.shortTime"  value="${setupCheck.shortTime}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
					  </td>
					</tr>
					<tr>
						<th>纠正措施文件上传
					  </th>
					  <td colspan="3">
					  <s:if test="setupCheck.jiuzhenFile!=null">
						<FONT color="red">${setupCheck.jiuzhenFile}</FONT><a href="FileViewAction.action?FilePath=/upload/file/setupCheck/${setupCheck.jiuzhenFile}">下载</a>
					</s:if>
					
					<s:else><FONT color="red">无文件</FONT></s:else>
					  	<input type="file" name="setupCheck.jiuzhenF" id="jiuzhenF"/>
					  </td>
					</tr>
					<tr>
					  <th>预防措施
					  </th>
					  <td colspan="3">
					  	 <textarea rows="5" cols="80" name="setupCheck.longWay">${setupCheck.longWay}</textarea>
					  </td>
					  </tr>
					  <tr id="tr_1">
					  <th>预防措施负责人
					  </th>
					  <td>
					 	 所属部门：&nbsp;&nbsp;&nbsp;&nbsp;
					  <SELECT id="dept1"  onblur="deptNotNull1();">
					  	<option value=""></option>
					  	</SELECT>
					  	&nbsp;&nbsp;&nbsp;&nbsp;负责人：
					  	<SELECT id="name1" style="width: 155px;">
					 		<option value="${setupCheck.longPerson}">${setupCheck.longPerson}</option>
					  	</SELECT>
					  	<input id="bfName1" name="setupCheck.longPerson"  type="hidden"  value="${setupCheck.longPerson}">
					  	<input id="code1" name="setupCheck.longId"  type="hidden" value="${setupCheck.longId}">
					  </td>
					
					    <th>预防措施完成日期
					    </th>
					    <td colspan="3">
					   <input name="setupCheck.longTime"  value="${setupCheck.longTime}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
					    </td>
					</tr>
					<tr>
						<th>预防措施文件上传
					  </th>
					  <td colspan="3">
					  <s:if test="setupCheck.yufangFile!=null">
						<FONT color="red">${setupCheck.yufangFile}</FONT><a href="FileViewAction.action?FilePath=/upload/file/setupCheck/${setupCheck.yufangFile}">下载</a>
					</s:if>
					
					<s:else><FONT color="red">无文件</FONT></s:else>
					  	<input type="file" name="setupCheck.yufangF" id="yufangF"/>
					  </td>
					</tr>
					 <td colspan="4" align="center">
					   <input type="submit" value="提交">
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
 	$(function(){
	//显示部门对应的员工信息
	$("#dept").bind(
			"change",
			function() {
				if ($("#dept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#dept").val()
						},
						success : function(useradsfa) {
							$("#name").empty();//清空
							$("<option></option>").appendTo("#name");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code +"|"+this.name+ "'>"
															+ this.name
															+ "</option>")
												.appendTo("#name")
									});
								$("#name").bind("change", function() {
								var name = $("#name").val();
								var usersData = name.split("|");
								var bfName = usersData[1];
								$("#bfName").val(bfName);
								var code = usersData[0];
								$("#code").val(code);
							});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name").empty();//清空
				}
		});
});

 		$(function(){
	//显示部门对应的员工信息
	$("#dept1").bind(
			"change",
			function() {
				if ($("#dept1").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#dept1").val()
						},
						success : function(useradsfa) {
							$("#name1").empty();//清空
							$("<option></option>").appendTo("#name1");
							$(useradsfa).each(
									function() {
										$(
												"<option value='"
															+ this.code +"|"+this.name+"'>"
															+ this.name
															+ "</option>")
												.appendTo("#name1")
									});
								$("#name1").bind("change", function() {
								var name = $("#name1").val();
								var usersData = name.split("|");
								var bfName = usersData[1];
								$("#bfName1").val(bfName);
								var code = usersData[0];
								$("#code1").val(code);
							});
								
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#name1").empty();//清空
				}
		});
});
 function deptNotNull() {
	if ($("#dept").val() == "" || $("#dept").val() == "") {
		alert("部门不能为空！");
		return false;
	}
}
  function deptNotNull1() {
	if ($("#dept1").val() == "" || $("#dept1").val() == "") {
		alert("部门不能为空！");
		return false;
	}
}

	</script>
</body>
</html>

