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
		<div align="center">
				<div align="center">
					<table class="table">
						<tr><th>零件号</th><td>${quotedPrice.markId}</td> <th>名称</th><td>${quotedPrice.proName}</td> </tr>					
					</table>
					<br/>
					<form action="QuotedPrice_selectLeader.action">
					<input type="hidden" value="${quotedPrice.id)" name="${quotedPrice.id}">
					<table class="table">
						<tr>
						<th>选择</th>
						<th>名称</th>
						<th>工号</th>
						<th>部门</th>
						</tr>
						<s:if test="investorList.size()>0">
						<s:iterator value="investorList" id="pageInvestor"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageInvestor.isSelected=='yes'">
								<input checked="checked"  type="radio" name="investor.id" value="${pageInvestor.id}">	<s:property value="#pageindex.index+1" />
								</s:if>
								<s:else>
								<input  type="radio" name="investor.id" value="${pageInvestor.id}">	<s:property value="#pageindex.index+1" />
								</s:else>
							</td>
							<td>
								${pageInvestor.name}
							</td>
							<td>
								${pageInvestor.code}
							</td>
							<td>
								${pageInvestor.dept}
							</td>
							</tr>
						</s:iterator>
						<tr>
						 <th colspan="4"><input type="submit" value="提交">
						 </th>
						</tr>
						</s:if>
						<s:else>
						<tr>
						 <th colspan="4"><font color="red">此项目没有投资者</font>
						 </th>
						</tr>
						</s:else>
					</table>
					</form>
				</div>
			</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
