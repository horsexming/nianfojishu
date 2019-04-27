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
							<span id="title">您正在对工序信息进行操作</span>
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
		<div id="gongneng">

			<div align="center">
				<h3>
					工序管理
				</h3>
				<form action="processGzstoreAction_findGX.action" method="post">
					<table class="table">
						<tr>
							<td align="center">
								名称：
								<input type="text" name="processGzstore.processName" />
								<input type="hidden" name="tag" value="${tag}" />
							</td>
							<td align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
									
								<s:if test="tag != 'zj'">
								<input type="button" style="width: 100px; height: 40px;"
									value="添加" onclick="add()" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="用户工序" onclick="userProcess()" class="input" />
								</s:if>
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
							名称
						</td>
						<!--						<td align="center">-->
						<!--							型别-->
						<!--						</td>-->
						<td align="center">
							描述
						</td>
						<td align="center">
							特殊工序
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
						<td align="left">
							${pageList.processName }
						</td>
						<!--						<td>-->
						<!--							${pageList.xingbie }-->
						<!--						</td>-->
						<td>
							${pageList.more}
						</td>
						<td>
							<s:if test="#pageList.isSpecial!=null&&#pageList.isSpecial=='特殊'">
							<label id="isSpecial<s:property value="#pageStatus.index" />">特殊</label>
							</s:if>
							<s:else>
							<label id="isSpecial<s:property value="#pageStatus.index" />">普通</label>
							</s:else>
						</td>
						<td>
							<s:if test="tag == 'zj'">
							<a href="javascript:;" onclick="bdosScope('${pageList.id }')">质检模板</a>/
							</s:if>	
							<s:else>
								<s:if test="#pageList.isSpecial==null||#pageList.isSpecial=='普通'">
									<a href="javascript:;" onclick="applySpecial(${pageList.id },<s:property value="#pageStatus.index" />);">申请特殊工序</a>/
								</s:if>
								<s:else>
									<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">审批动态</a>
								</s:else>
							<a
								href="GzstoreAction_findGgbj_Process.action?id=${pageList.id }">绑定工装</a>/
								<a
								href="processGzstoreAction_findAllPamList.action?id=${pageList.id }">绑定量、检具</a>/
							<a
								href="GzstoreAction_findGgbj_Process1.action?id=${pageList.id }">绑定设备</a>/
							<a onclick="update(${pageList.id })">修改</a>/
							<a href="GzstoreAction_delProcess2.action?del_id=${pageList.id }"
								onclick="return confirm('确定要删除吗?')">删除</a>
							</s:else>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js">
</script>
		<script type="text/javascript">
function add() {
	$("#showProcess").attr("src",
			"<%=request.getContextPath()%>/System/gzbj/Gzbj_addProcess.jsp");
	chageDiv('block');
}
function userProcess() {
	window.location.href = "<%=request.getContextPath()%>/System/gzbj/userprocess.jsp";
}
function update(pageid) {
	$("#showProcess").attr("src",
			"GzstoreAction_toupdateProcess.action?id=" + pageid);
	chageDiv('block');
}

function bdosScope(id) {
	$("#showProcess").attr("src",
			"processGzstoreAction_getosListbyid.action?id=" + id);
	chageDiv('block');
}
//申请特殊工序
function applySpecial(id,index) {
if (window.confirm('确定要将此工序申请为特殊工序吗?')) {
		$.ajax( {
			type : "POST",
			url : "processGzstoreAction_applySpecial.action",
			dataType : "json",
			data : {
				id : id
			},
			success : function(msg) {
				if (msg=="true") {
					$("#isSpecial"+index).empty();
					$("#isSpecial"+index).html("申请中");
				} else {
					alert(msg);
				}
			}
		});
	}
}
</script>
	</body>
</html>
