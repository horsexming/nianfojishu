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
	<body onload="createDept('department');">
	<%@include file="/util/sonTop.jsp"%>
		<s:if test="%{name1=='geti'}">
		<form action="RepairAction!updateRepairtype.action?test='1'" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">修改报修类别信息</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						部门:
					</th>
					<td>
						 <input  type="text" name="repairType.department" value="${repairType.department}" readonly="readonly"/>
					</td>
					<th align="right">
						类别:
					</th>
					<td>
						<input id="category" name="repairType.category"  value="${repairType.category}"/>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
					<input type="hidden"  name="repairType.id"  value="${repairType.id}"/>
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="reset" value="取  消" class="input" />
					</td>
				</tr>
			</table>
		</form>
		</s:if>
		<s:else>
		<form action="RepairAction!updateRepairtype.action" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="6">
						<font size="5">修改报修类别信息</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						部门:
					</th>
					<td>
						<select id="department"
							name="repairType.department" >
							<option selected="selected" value="${repairType.department}">
							${repairType.department}
							</option>
							<s:iterator id="cu" value="list">
								<option value="${cu.ta_dept}">
									${cu.ta_dept}
								</option>
							</s:iterator>
						</select>
					</td>
					<th align="right">
						类别:
					</th>
					<td>
						<input id="category" name="repairType.category"  value="${repairType.category}"/>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
					<input type="hidden"  name="repairType.id"  value="${repairType.id}"/>
						<input type="submit" value="确  定" class="input" />
						&nbsp;&nbsp;
						<input type="reset" value="取  消" class="input" />
					</td>
				</tr>
			</table>
		</form>
		
		</s:else>
		
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
</script>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

</html>
