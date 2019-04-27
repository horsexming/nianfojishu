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
		<script type="text/javascript">

</script>
	</head>
	<body>
		<form action="QualityAction_updateQuality.action"  enctype="multipart/form-data"  method="post">
		<input type="hidden"  name="quality.id" value="${quality.id}" />
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">修改质量审核</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						部门:
					</th>
					<td>
						<input name="quality.quality_name" value="${quality.quality_name}" readonly="readonly"/>
					</td>
					
					<th align="right">
						姓名:
					</th>
					<td>
						<input id="quality_pop" name="quality.quality_pop"  value="${quality.quality_pop}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
				   <th align="right">标题:
				   </th>
				   <td><input type="text" name="quality.quality_title" value="${quality.quality_title}" >
				   </td>
				   <th align="right">添加日期:
				   </th>
				   <td>
				   <input id="quality_time" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',skin:'whyGreen'})"
											name="quality.quality_time" value="${quality.quality_time}"/>
				   </td>
				</tr>
				<tr>
				   <th align="right">类别:
				   </th>
				   <td><input type="text" name="quality.quality_type" value="${quality.quality_type}">
				   </td>
				   <th align="right">
				   </th>
				   <td>
				   </td>
				</tr>
				<tr>
					<th align="right">
						内容:
					</th>
					<td colspan="3">
					<textarea id="quality_context" rows="10" cols="50" name="quality.quality_context" >${quality.quality_context}</textarea>
<%--						<input id="quality_title" name="quality.quality_title" value="${quality.quality_title}" />--%>
					</td>
				</tr>
				<tr>
					<th align="right">
						附件:
					</th>
					<td colspan="3">
				    	  <input id="file1" type="file" name="quality_file"  value="${quality.quality_file}"/>
				    	  <s:if test="quality.quality_file!=null"><font color="red">原有文件谨慎上传</font>
				    	  </s:if>
					</td>
				
				</tr>
				
				<tr>
					<td align="center" colspan="4">
						<input type="submit" value=" 修改"  class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
<!--		<script type="text/javascript"  src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>-->
		<script type="text/javascript">
		$(function(){
			$("#quality_title").focus();
			var errorMessage = '${errorMessage}';
			if(errorMessage!=""){
				parent.location.reload(true);//刷新父页面
			}
		})
<%--		function isok(){--%>
<%--			var file1 = $("#file1").val();--%>
<%--			if(file1!=""){--%>
<%--				$("#span1").hide();--%>
<%--			}--%>
<%--		}--%>
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
