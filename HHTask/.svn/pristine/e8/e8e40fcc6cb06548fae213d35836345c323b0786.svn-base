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
		<script type="text/javascript">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
			</div>
			<div align="center">
				<table  class="table">
				</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							项目阶段
						</th>
						<th align="center">
							金额
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							批次号
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							送货单号
						</th>
						<th align="center">
							申请人
						</th>
						<th align="center">
							工号
						</th>
						<th align="center">
							部门
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							来源
						</th>
					</tr>
					<s:iterator value="qpCostList" id="qpcost" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${qpcost.proStatus}
						</td>
						<td align="center">
							${qpcost.money}
						</td>
						<td align="center">
							${qpcost.markId}
						</td>
						<td align="center">
							${qpcost.selfCard}
						</td>
						<td align="center">
							${qpcost.counts}
						</td>
						<td align="center">
							${qpcost.sendNumber}
						</td>
						<td align="center">
							${qpcost.userName}
						</td>
						<td align="center">
							${qpcost.userCode}
						</td>
						<td align="center">
							${qpcost.dept}
						</td>
						<td align="center">
							${qpcost.addTime}
						</td>
						<td align="center">
							${qpcost.source}
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "POST",
		url : "ProjectManage_findAllProMan.action",
		dataType : "json",
		success : function(msg) {
			$("#proId").append("<option value=''></option>");
			$.each(msg, function(i, n) {
				$("#proId").append(
						"<option value='" + n.id + "'>" + n.projectNum + "("
								+ n.projectName + ")</option>");
			});
		}
	});
})
</script>
	</body>
</html>
