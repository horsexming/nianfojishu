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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">

			<div align="center">
				<h3>
					检测记录
				</h3>
				<form action="ProcardTemplateAction!showListProcardSpecification.action" method="post">
					<table class="table">
						<tr align="center">
							<th align="center">
								请输入件号
							</th>
							<td align="center">
								<input type="text" name="procardSpecification.markId"
									/>
							</td>
							<td align="center" colspan="2">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							件号
						</td>
						<td align="center">
							批次
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							标准值
						</td>
						<td align="center">
							实际测量值
						</td>
						<td align="center">
							测试时间
						</td>
					</tr>
					<s:iterator value="procardSpecificationList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.markId}
						</td>
						<td align="center">
							${samples.selfCard}
						</td>
						<td align="center">
							${samples.proName}
						</td>
						<td align="center">
							${samples.markIdSize}
						</td>
						<td align="center">
							${samples.markIdActualSize}
						</td>
						<td align="center">
							${samples.addTime}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="7" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
