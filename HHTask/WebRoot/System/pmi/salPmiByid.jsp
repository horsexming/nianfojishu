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
	<body>
		<form action="pmiManagementAction_updatePmi.action" method="post">
			<table align="center" class="table">
				<tr>
					<th colspan="4">
						<font size="5">修改PMI</font>
					</th>
				</tr>
				<tr>
					<th align="right">
						名称:
					</th>
					<td>
						<input name="pmiManagement.pmi_name"
							value="${pmiManagement.pmi_name}" />
					</td>
					<th align="right">
						客户端IP地址:
					</th>
					<td>
						<input name="pmiManagement.pmi_ip" value="${pmiManagement.pmi_ip}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						序号:
					</th>
					<td>
						<input id="min_num" name="pmiManagement.min_num" value="${pmiManagement.min_num}" />
					</td>
					<th align="right">
						服务端IP地址:
					</th>
					<td>
						<input id="pmi_serverIp" name="pmiManagement.pmi_serverIp" value="${pmiManagement.pmi_serverIp}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						端口:
					</th>
					<td>
						<input name="pmiManagement.pmi_port"
							value="${pmiManagement.pmi_port}" />
					</td>
					<th align="right">
						类型:
					</th>
					<td>
						<select id="pmi_type" name="pmiManagement.pmi_type">
							<s:if
								test="pmiManagement.pmi_type==null||pmiManagement.pmi_type=='强控'">
								<option value="强控">
									强控
								</option>
								<option value="弱控">
									弱控
								</option>
							</s:if>
							<s:else>
								<option value="弱控">
									弱控
								</option>
								<option value="强控">
									强控
								</option>
							</s:else>
						</select>
					</td>
				</tr>
				<tr>
					<th align="right">
						额定电流
					</th>
					<td>
						<input id="rated_Current" name="pmiManagement.rated_Current"
							value="${pmiManagement.rated_Current}" />
					</td>
					<th align="right">
						报警百分比
					</th>
					<td>
						<input id="alert_Percentage" name="pmiManagement.alert_Percentage"
							value="${pmiManagement.alert_Percentage}" />
					</td>
				</tr>
				<tr>
					<th align="right">
						工作状态:
					</th>
					<td>
						<select id="status" name="pmiManagement.status">
							<s:if
								test="pmiManagement.status==null||pmiManagement.status=='生产中'">
								<option value="生产中">
									生产中
								</option>
								<option value="空闲">
									空闲
								</option>
							</s:if>
							<s:else>
								<option value="空闲">
									空闲
								</option>
								<option value="生产中">
									生产中
								</option>
							</s:else>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<input type="hidden" name="pmiManagement.id"
							value="${pmiManagement.id}" />
						<input type="submit" value="修改" class="input" />
						<input type="reset" value=" 重置" class="input" />
					</td>
				</tr>
			</table>
		</form>
	</body>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript">
$(function() {
	var errorMessage = '${errorMessage}';
	if (errorMessage != "") {
		alert(errorMessage);
		parent.location.reload(true);//刷新父页面
	}
})
</script>
</html>
