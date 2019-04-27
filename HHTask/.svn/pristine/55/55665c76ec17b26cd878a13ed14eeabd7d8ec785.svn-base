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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3>
					LED管理
					<br />
					(LED Management)
				</h3>
				<h3>
					LED名称：
					<s:property value="lED.name" />
				</h3>
				<form action="lEDAction_lEDLogView.action" method="post">
					<table class="table" align="center">
						<tr>
							<td align="center">
								<input type="hidden" name="pageStatus"
									value="<s:property value='pageStatus'/>" />
								内容（context）：
								<input type="text" name="lEDLog.context"
									value="<s:property value="lEDLog.context"/>" />
							</td>
							<td align="center">
								状态（status）：
								<input type="text" name="lEDLog.number"
									value="<s:property value="lEDLog.number"/>" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="2">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加lEDLog(add)" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(context)
						</td>
						<td align="center">
							内容
							<br />
							(context)
						</td>
						<td align="center">
							状态
							<br />
							(status)
						</td>
						<td align="center">
							生产状态
							<br />
							(productStatus)
						</td>
						<td align="center">
							添加时间
							<br />
							（addTime）
						</td>
					</tr>
					<s:iterator value="lEDLogList" id="lEDLogPage" status="pageStatus">
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
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${lEDLogPage.context}
						</td>
						<td>
							${lEDLogPage.status}
						</td>
						<td>
							${lEDLogPage.productStatus}
						</td>
						<td>
							${lEDLogPage.addTime}
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
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

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="11" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>