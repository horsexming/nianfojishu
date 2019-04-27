<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在付款申请进行操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h3>
					合同管理
				</h3>
				<form
					action="bargainAction_findBarContract.action?test=<s:property value="test"/>"
					method="post">
					<table class="table">
						<tr>
							<td align="center">
								编号：
								<input type="text" name="barContract.contract_num" />
							</td>
							<td align="center">
								来源：
								<input type="text" name="barContract.contract_source" value="" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
						</tr>
					</table>

					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								合同编号
							</td>
							<td align="center">
								合同名称
							</td>
							<td align="center">
								供方
							</td>
							<td align="center">
								来源
							</td>
							<td align="center">
								单号
							</td>
							<td align="center">
								总金额
							</td>
							<td align="center">
								添加人
							</td>
							<td align="center">
								操作
							</td>
							<td></td>
						</tr>
						<s:iterator value="maps" id="pageList" status="pageStatus">
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
								${pageList.contract_num}
							</td>
							<td>
								${pageList.contract_name}
							</td>
							<td>
								${pageList.supplier}
							</td>
							<td>
								${pageList.contract_source}
							</td>
							<td style="width: 450px">
								${pageList.contract_num1}
							</td>
							<td>
								${pageList.heji}
							</td>
							<td>
								${pageList.username}
							</td>
							<td>
								<a
									href="bargainAction_salBarContract.action?barContract.id=${pageList.id}&status=print">预览/</a>
								<%--							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>--%>
								<%--							<s:if test='#pageList.status=="未审核"||#pageList.status=="打回"'>--%>
								<a
									href="bargainAction_salBarContract.action?barContract.id=${pageList.id}&
								barContract.contract_source=${pageList.contract_source}&barContract.contract_num1=${pageList.contract_num1}&
								test=<s:property value="test"/>">修改/</a>
								<a onclick="return window.confirm('此操作关联明细,确定删除?')
									href="bargainAction_delBarContract.action?barContract.id=${pageList.id}&test=<s:property value="test"/>">删除</a>
								<%--							</s:if>--%>

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
					</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add() {
	location.href = "<%=request.getContextPath()%>/System/payment1/addPaymentVoucher.jsp";
}
</script>
	</body>
</html>
