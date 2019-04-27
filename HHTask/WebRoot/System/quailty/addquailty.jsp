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
		<form action="QualityAction_addQuality.action"
			enctype="multipart/form-data" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">添加质量审核</font>
					</th>
				</tr>
				<tr>
					<s:if test="test==3">
						<th align="right">
							部门:
						</th>
						<td>
							<%--						<input name="quality.quality_name" value="111" />--%>
							<select id="department" name="quality.quality_name">
								<option selected="selected" value="">
									选择部门
								</option>
								<s:iterator id="cu" value="maps">
									<option value="${cu.ta_dept}">
										${cu.ta_dept}
									</option>
								</s:iterator>
							</select>
						</td>
					</s:if>
					<s:else>
						<th align="right">
							部门:
						</th>
						<td>
							<input name="quality.quality_name" value="${deptname}"
								readonly="readonly" />
						</td>
					</s:else>

					<!--					<set var="n" value="#parameters.test" />-->
					<s:if test="test==2||test==3">
						<th align="right">
							姓名:
						</th>
						<td>
							<input id="quality_pop" name="quality.quality_pop" value="" />
						</td>
					</s:if>
					<s:else>
						<th align="right">
							姓名:
						</th>
						<td>
							<input id="quality_pop" name="quality.quality_pop"
								value="${username}" readonly="readonly" title="只读" />
						</td>
					</s:else>
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
				   <td><input type="text" name="quality.quality_title">
				   </td>
				   <th align="right">添加日期:
				   </th>
				   <td>
				   <input id="quality_time" type="text"
										class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
											name="quality.quality_time" />
				   </td>
				</tr>
				<tr>
				   <th align="right">类别:
				   </th>
				   <td><SELECT name="quality.quality_type">
						 <option>产品审核
						 </option>
						 <option>过程审核
						 </option>
						 <option>体系审核
						 </option>
						</SELECT>
				   </td>
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
					<textarea id="quality_context" rows="10" cols="50" name="quality.quality_context" ></textarea>
<%--						<input id="quality_title" name="quality.quality_title" value="" />--%>
					</td>
				</tr>
				<tr>
				<th align="right">
						附件:
					</th>
					<td colspan="3">
						<input id="quality_file" type="file" name="quality_file" />
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
	$("#quality_title").focus();
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
