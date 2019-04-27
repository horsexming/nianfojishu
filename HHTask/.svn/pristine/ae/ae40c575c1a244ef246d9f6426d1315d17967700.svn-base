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
							<span id="title">您正在PMI进行操作</span>
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
					PMI管理
				</h3>
				<form action="pmiManagementAction_findPmi.action" method="post">
					<table class="table">
						<tr>
							<td align="center">
								名称：
								<input type="text" name="pmiManagement.pmi_name"
									value="<s:property value="pmiManagement.pmi_name"/>" />
							</td>
							<td align="center">
								IP地址：
								<input type="text" name="pmiManagement.pmi_ip"
									value="<s:property value="pmiManagement.pmi_ip"/>" />
							</td>
						</tr>
						<tr>
							<td align="center">
								工位号：
								<input type="text" name="machine.workPosition"
									value="<s:property value="machine.workPosition"/>" />
							</td>
							<td align="center">
								状态：
								<select name="pmiManagement.status" style="width: 150px">
									<option value=""></option>
									<option value="空闲">
										空闲
									</option>
									<option value="生产中">
										生产中
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center">
								设备编号号：
								<input type="text" name="machine.no"
									value="<s:property value="machine.no"/>" />
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" class="input" onclick="add()" />
							</td>
						</tr>
					</table>

					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								名称
							</td>
							<td align="center">
								客户端ip
							</td>
							<td align="center">
								序号
							</td>
							<td align="center">
								服务端ip
							</td>
							<td align="center">
								端口
							</td>
							<td align="center">
								类型
							</td>
							<td align="center">
								工作状态
							</td>
							<td align="center">
								额定电流
							</td>
							<td align="center">
								报警百分比
							</td>
							<td align="center" colspan="2">
								操作
							</td>
						</tr>
						<tr>
							<s:iterator value="maps" id="pageList" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<s:if test="#pageList.status=='生产中'&&#pageList.status!=null">
										<tr align="center" bgcolor="#abc88b"
											onmouseover="chageBgcolor(this,'green')"
											onmouseout="outBgcolor(this,'#abc88b')">
									</s:if>
									<s:else>
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:else>

								</s:if>
								<s:else>
									<s:if test="#pageList.status=='生产中'&&#pageList.status!=null">
										<tr align="center" bgcolor="#abc88b"
											onmouseover="chageBgcolor(this,'green')"
											onmouseout="outBgcolor(this,'#abc88b')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
								</s:else>
								<td>
									<s:if test="#pageStatus.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td>
									${pageList.pmi_name}
								</td>
								<td>
									<a href="http://${pageList.pmi_ip}" target="_showPMIdetail">
										${pageList.pmi_ip}</a>
								</td>
								<td>
									${pageList.min_num}
								</td>
								<td>
									${pageList.pmi_serverIp}
								</td>
								<td>
									${pageList.pmi_port}
								</td>
								<td>
									${pageList.pmi_type}
								</td>
								<td>
									${pageList.status}
								</td>
								<td>
									<s:if test="#pageList.rated_Current!=null">
									${pageList.rated_Current}(A)</s:if>
									<s:else>${pageList.rated_Current}</s:else>
								</td>
								<td>
									<s:if test="#pageList.alert_Percentage!=null">
									${pageList.alert_Percentage}%</s:if>
									<s:else>${pageList.alert_Percentage}</s:else>
								</td>
								<td colspan="2">
									<a
										href="pmiManagementAction_findMachineByid.action?pmi_id=${pageList.id}">对应设备/</a>
									<a target="_block"
										href="pmiManagementAction_findProcessInforBypmi_id.action?pmi_id=${pageList.id}">生产工序/</a>
									<a onclick="update(${pageList.id})">修改/</a>
									<a
										href="pmiManagementAction_delPmi.action?pmiManagement.id=${pageList.id}">删除</a>
									<br />
									<font color="red"> <a style="color: red"
										href="ProcardAction!openOrClosePmi.action?id=${pageList.id}&pageStatus=1">打开</a>/
										<a style="color: red"
										href="ProcardAction!openOrClosePmi.action?id=${pageList.id}&pageStatus=0">关闭</a>/
										<a style="color: red"
										href="ProcardAction!openOrClosePmi.action?id=${pageList.id}&pageStatus=2">重置</a>
<%--										<a style="color: red"--%>
<%--										href="ProcardAction!openOrClosePmi.action?id=${pageList.id}&pageStatus=3">电压</a>/--%>
<%--										<a style="color: red"--%>
<%--										href="ProcardAction!openOrClosePmi.action?id=${pageList.id}&pageStatus=4">电流</a>--%>
									</font>
								</td>
							</s:iterator>
						</tr>
						<tr>
							<s:if test="errorMessage==null">
								<td colspan="10" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							<s:else>
								<td colspan="10" align="center" style="color: red">
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function add() {
	var url = "<%=request.getContextPath()%>/System/pmi/addPmi.jsp";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function update(obj) {
	var url = "<%=request.getContextPath()%>/pmiManagementAction_salPmiByid.action?pmiManagement.id="
			+ obj;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
