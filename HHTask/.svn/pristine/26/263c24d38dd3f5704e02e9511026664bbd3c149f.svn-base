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
				门卫卡信息查询
				</h3>
					<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							卡号
						</td>
						<td align="center">
							性别
						</td>
						<td align="center">
							民族
						</td>
						<td align="center">
							籍贯
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							身份证
						</td>
						<td align="center" colspan="2">
							操作
						</td>
					</tr>
					<s:iterator value="guardCardList" id="samples"
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
							${samples.name}
						</td>
						<td align="center">
							${samples.code}
						</td>
						<td align="center">
							${samples.cardId}
						</td>
						<td align="center">
							${samples.sex}
						</td>
						<td align="center">
							${samples.nation}
						</td>
						<td align="center">
							${samples.birthplace}
						</td>
						<td align="center">
							${samples.dept}
						</td>
						<td align="center">
							${samples.uid}
						</td>
						<td align="center" colspan="2">
							<a href="GuardCardAction_getByIdGuardCard.action?guardCard.id=${samples.id}">修改</a>
							<a onclick="return window.confirm('您将删除数据，是否继续?')"
							href="GuardCardAction_deleteGuardCard.action?guardCard.id=${samples.id}">删除</a>
						</td>
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
							<td colspan="12" align="center" style="color: red">
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
