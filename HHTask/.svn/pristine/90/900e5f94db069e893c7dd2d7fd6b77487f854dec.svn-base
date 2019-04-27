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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="center">
				<form action="gasAction_addGas.action" method="post"
					onsubmit="return checkForm('1')">
					<table>
						<tr>
							<td align="right">
								扫 描 入 库:
							</td>
							<td>
								<input type="text" id="runCard" name="gas.number" value="" />
								<font style="color: red">${successMessage}</font>
							</td>
						</tr>

					</table>
				</form>
				<br>
				<br>
				<br>
				<form action="gasAction_findGas.action" method="post"
					onsubmit="return checkForm('1')">
					<input type="text" id="idCard" name="gas.number" value="" />

					<input type="submit" style="width: 100px; height: 40px;" value="查询"
						class="input" />
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								序列号
							</td>
							<td align="center">
								添加时间
							</td>
							<td align="center">
								添加人
							</td>
						</tr>
						<tr>
							<s:iterator value="gasList" id="pageList1" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center">
									<s:if test="#pageStatus.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${pageList1.number}
								</td>
								<td align="center">
									${pageList1.addTime}
								</td>
								<td align="center">
									${pageList1.userss}
								</td>

							</s:iterator>
						</tr>

						<tr>
							<s:if test="errorMessage==null">
								<td colspan="4" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="6" align="center" style="color: red">
									${errorMessage}
							</s:else>
						</tr>
					</table>
				</form>

			</div>


			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//初始选中
$(function() {
	$("#runCard").focus();
})
</script>
	</body>
</html>
