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
//显示明细
function showDetail(id) {
	$("#addProIf").attr("src", "ProjectManage_findProjectManage.action?id=" + id);
	$("#addProIf").height("600");
	chageDiv('block');
}
//修改
function addHs(id) {
	$("#addProIf").attr("src",
			"ProjectManage_findProjectManage.action?pageStatus=hs&id=" + id);
	//绑定刷新页面
	$("#bodyDiv").bind("click", function() {
		chageDiv('none');
		reload();
	});

	$("#closeimg").bind("click", function() {
		chageDiv('none');
		reload();
	});

	chageDiv('block');
}
</script>
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
							<span id="title">项目管理</span>
						</td>
						<td align="right">
							<img id="closeimg" alt="" src="<%=basePath%>/images/closeImage.png"
								width="30" height="32" onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="addProIf" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center">
				<table  class="table">
					<tr>
						<th colspan="8">
							待核算项目填报
						</th>
					</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							项目名称
						</th>
						<th align="center">
							项目编号
						</th>
						<th align="center">
							客户
						</th>
						<th align="center">
							填报人
						</th>
						<th align="center">
							添加时间
						</th>
						<th align="center">
							审批状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="projectManageList" id="pageProm"
						status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${pageProm.projectName}
						</td>
						<td align="center">
							${pageProm.projectNum}
						</td>
						<td align="center">
							${pageProm.client}
						</td>
						<td align="center">
							${pageProm.userName}
						</td>
						<td align="center">
							${pageProm.dateTime}
						</td>
						<td align="center">
							${pageProm.aduitStatus}
						</td>
						<td align="center">
							<a href="javascript:;" onclick="addHs('${pageProm.id}')">开始核算</a>/
							<a href="javascript:;" onclick="showDetail('${pageProm.id}')">明细</a>/
							<a
								href="ProjectManage_updateProManForHs.action?id=${pageProm.id}"
								onclick="return window.confirm('确定该项目的待核算信息已录入完成?')">录入完成</a>
						</td>
						</tr>
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
							<td colspan="11" align="center" style="color: red">
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
	</body>
</html>
