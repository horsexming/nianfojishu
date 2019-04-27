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
							<span id="title">您正在对动声控制系统进行操作</span>
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
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="" style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h3>
					客户管理
				</h3>
				<form action="CustomerAction_findCustomerAll.action" method="post">
					<table class="table">
						<tr>
							<th>
								姓名
							</th>
							<td align="left">
								<input type="text" name="customer.customer_name" />
							</td>
							<th>
								手机号
							</th>
							<td align="left">
								<input type="text" name="customer.customer_phone"
									onkeyup="this.value=this.value.replace(/[^\d]/g,'')" />
							</td>
						</tr>
						<tr>
							<th>
								车型
							</th>
							<td align="left">
								<input type="text" name="customer.cart_ype" />
							</td>
							<th>
								状态
							</th>
							<td align="left">
								<select name="customer.customer_state">
									<option value="">
										--请选择--
									</option>
									<option value="试用">
										试用
									</option>
									<option value="正式">
										正式
									</option>
									<option value="永久">
										永久
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" class="input" onclick="add()" />
							</td>
						</tr>
					</table>
				</form>

				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							公司名称
						</td>
						<td align="center">
							手机号
						</td>
						<td align="center">
							车型
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							周期
						</td>
						<td align="center">
							开始时间
						</td>
						<td align="center">
							结束时间
						</td>
						<td align="center">
							操作
						</td>
						<td></td>
					</tr>
					<s:iterator value="maps" id="cusList" status="pageStatus">
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
							${cusList.customer_name}
						</td>
						<td>
							${cusList.company_name}
						</td>
						<td>
							${cusList.customer_phone}
						</td>
						<td>
							${cusList.cart_ype }
						</td>
						<td>
							${cusList.customer_state}
						</td>
						<td>
							${cusList.period}
						</td>
						<td>
							${cusList.startdate}
						</td>
						<td>
							${cusList.enddate}
						</td>
						<td>
							<a onclick="update(${cusList.customer_id})">修改</a>/
							<a
								href="CustomerAction_delCustomerById.action?customer_id=${cusList.customer_id}">删除</a>
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
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function add() {
	var url = encodeURI(encodeURI("${pageContext.request.contextPath}/android/pscs/addcustomer.jsp"));
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function update(obj) {
	var url = "${pageContext.request.contextPath}/CustomerAction_findCustomerById.action?customer_id="
			+ obj;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
