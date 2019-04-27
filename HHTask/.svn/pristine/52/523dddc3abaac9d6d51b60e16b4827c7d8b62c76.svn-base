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
							<span id="title">您正在班次进行操作</span>
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
					班次管理
				</h3>
				<form action="banCiAction_findbanCi.action?tag=${tag}" method="post">
					<table class="table">
						<tr>
							<td align="center">
								名称：
								<input type="text" name="banCi.name"
									value="<s:property value="banCi.name"/>" />
							</td>
							<td align="center">
								开始时间：
								<input type="text" id="firsttime" class="Wdate"
									name="banCi.firsttime"
									onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<td align="center">
								结束时间：
								<input type="text" id="finishtime" class="Wdate"
									name="banCi.finishtime"
									onclick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="添加班次" class="input" onclick="add()" />
								<input type="button" style="width: 100px; height: 40px;"
									value="快速换班" class="input" onclick="change()" />
							</td>
						</tr>
					</table>

					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<td align="center">
								序号
							</td>
							<td align="center">
								名称
							</td>
<%--							<td align="center">--%>
<%--								开始时间--%>
<%--							</td>--%>
<%--							<td align="center">--%>
<%--								结束时间--%>
<%--							</td>--%>
							<td align="center">
								上班日期
							</td>
							<td align="center" colspan="2">
								操作
							</td>
						</tr>
						<s:iterator value="banciList" id="pageList" status="pageStatus">
							<tr>
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td align="center" rowspan="2">
									<s:if test="#pageStatus.index%2==1">
									</s:if>
									<s:else>
										<font color="#c0dcf2"></font>
									</s:else>
									<s:property value="#pageStatus.index+1" />
								</td>
								<td align="center">
									${pageList.name}
								</td>
<%--								<td align="center">--%>
<%--									${pageList.firsttime}--%>
<%--								</td>--%>
<%--								<td align="center">--%>
<%--									${pageList.finishtime}--%>
<%--								</td>--%>
								<td align="center">
									${pageList.sbdate}
								</td>
								<td align="center" colspan="2">
									<s:if test="tag=='del'">
									<input type="button" value="删除(Delete)"
										onclick="delete_2('${pageList.name}','${pageList.id}')" />
										<a onclick="update(${pageList.id})">修改</a> 
<%--										<a--%>
<%--										onclick="return window.confirm('您将删除班次，该班次下绑定的人员将被清空！请问是否继续?')"--%>
<%--										href="banCiAction_delBanCi.action?tag=${tag}&banCi.id=${pageList.id}">删除</a>--%>
									</s:if>
									<s:elseif test="tag=='admin'">
										<a onclick="update(${pageList.id})">修改</a> 
										<a
											href="banCiAction_findStationCondition.action?banCi.id=${pageList.id}&tag=${tag}">/已绑定人员</a>
										<a
											href="banCiAction_findUserById.action?banCi.id=${pageList.id}&tag=${tag}">/未绑定人员</a>
									</s:elseif>
									<s:else>
										<a
											href="banCiAction_findStationCondition.action?banCi.id=${pageList.id}&tag=${tag}">已绑定人员</a>
										<a
											href="banCiAction_findUserById.action?banCi.id=${pageList.id}&tag=${tag}">/未绑定人员</a>
									</s:else>
								</td>
							</tr>
							<s:if test="#pageList.banCiTimeShow!=null">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
									<th>
										工作时段
									</th>
									<td colspan="2" align="left">
										${pageList.banCiTimeShow}
									</td>
							</s:if>
						</s:iterator>
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
								<td colspan="4" align="center" style="color: red">
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
	var url = "<%=request.getContextPath()%>/System/banCi/addbanCi.jsp";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function update(obj) {
	var url = "<%=request.getContextPath()%>/banCiAction_salBanCiByid.action?tag=${tag}&banCi.id="
			+ obj;
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
function delete_2(name,id1) {
	if(window.confirm('您将删除 '+name+' 班次，该班次下绑定的人员将被清空！请问是否继续?')){
		window.location.href = "banCiAction_delBanCi.action?tag=${tag}&banCi.id="+id1;
	}
}
function change() {
	var url = "<%=request.getContextPath()%>/banCiAction_tochangeBanCi.action";
	$("#showProcess").attr("src", url);
	chageDiv('block');
}
</script>
	</body>
</html>
