<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<body >
		<form action="pfmeasAction_addPfmeas.action"
			enctype="multipart/form-data" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">FMEAS管理</font>
					</th>
				</tr>
				<tr>
						<th align="right">
							部门:
						</th>
						<td>
							<input name="pfmeas.pfmeas_name" value="${Users.dept}"
								readonly="readonly" />
						</td>

					<!--					<set var="n" value="#parameters.test" />-->
						<th align="right">
							姓名:
						</th>
						<td>
							<input id="pfmeas_code" name="pfmeas.pfmeas_code" type="hidden"
								value="${Users.code}" readonly="readonly" title="只读" />
							<input id="pfmeas_pop" name="pfmeas.pfmeas_pop"
								value="${Users.name}" readonly="readonly" title="只读" />
						</td>
					<!--					<th align="right">-->
					<!--						姓名:-->
					<!--					</th>-->
					<!--					<td>-->
					<!--						<input id="quality_pop" name="quality.quality_pop"  value="${username}" readonly="readonly"/>-->
					<!--					</td>-->
				</tr>
				<tr>
				   <th align="right">标题:
				   </th>
				   <td><input type="text" name="pfmeas.pfmeas_title">
				   </td>
				   <th align="right">添加日期:
				   </th>
				   <td>
				   <input id="pfmeas_time" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
											name="pfmeas.pfmeas_time" />
				   </td>
				</tr>
				<tr>
				
				   
				   <th align="right">
				   </th>
				   <td>
				   </td>
				</tr>
				<tr>
					<th align="right">
						描述:
					</th>
					<td colspan="3">
					<textarea id="pfmeas_context" rows="10" cols="50" name="pfmeas.pfmeas_context" ></textarea>
<%--						<input id="quality_title" name="quality.quality_title" value="" />--%>
					</td>
				</tr>
				<tr>
				<th align="right">
						附件:
					</th>
					<td colspan="3">
						<input id="upload" type="file" name="upload" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 添加" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
$(function() {
	$("#pfmeas_title").focus();
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
		if('${test}'=='3'){
		createDept('department');
	}
})
//		function add(){
//			var aaa = $("#quality_file").val();
//			if(aaa==""){
//				alert("附件不能为空!");
//				return false;
//			}
//		}
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
