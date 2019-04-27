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
$(function() {
	//添加按钮绑定事件
	$("#addPro")
			.bind(
					"click",
					function() {
						window.location.href="#showTop";
						$("#addProIf")
								.attr("src",
										"${pageContext.request.contextPath}/System/pro/lixiang/ProjectManage_add.jsp?pageStatus=${pageStatus}");
						chageDiv('block');
						//绑定刷新页面
						$("#bodyDiv").bind("click", function() {
							chageDiv('none');
							reload();
						});
						$("#closeimg").bind("click", function() {
							chageDiv('none');
							reload();
						});
					});

})
//显示明细
function showDetail(id) {
	window.location.href="#showTop";
	$("#addProIf").attr("src", "ProjectManage_findProjectManage.action?id=" + id);
	$("#addProIf").height("600");
	chageDiv('block');
}
//修改
function updatePro(id) {
	window.location.href="#showTop";
	$("#addProIf").attr("src",
			"ProjectManage_findProjectManage.action?pageStatus=update&id=" + id);
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
		<a name="showTop"></a>
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
							<span id="title">项目立项</span>
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
						style="width: 100%; height: 650px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="ProjectManage_findPMByCondition.action" method="post">
					<table class="table">
						<tr>
							<th colspan="4">
								<h2>
									项目立项管理
								</h2>
							</th>
						</tr>
						<tr>
							<th align="right">
								项目名称:
							</th>
							<th align="left">
								<input name="projectManage.projectName"
									value="${projectManage.projectName}" />
							</th>
							<th align="right">
								项目编号:
							</th>
							<th align="left">
								<input name="projectManage.projectNum"
									value="${projectManage.projectNum}" />
							</th>
						</tr>
						<tr>
							<th align="right">
								填报人:
							</th>
							<th align="left">
								<input name="projectManage.userName"
									value="${projectManage.userName}" />
							</th>
							<th align="right">
								添加时间:
							</th>
							<th align="left">
								<input class="Wdate" type="text" name="projectManage.dateTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									value="${projectManage.dateTime}" />
							</th>
						</tr>
						<tr>
							
							<th align="right">
								立项编码:
							</th>
							<th align="left">
								<input name="projectManage.projectNumber"
									value="${projectManage.projectNumber}" />
							</th>
							<th></th>
							<th></th>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="hidden" value="${pageStatus}" name="pageStatus"/>
								<input type="submit" value="查询" class="input" />
								<input id="addPro" type="button" value="添加" class="input" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center">
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							项目类别
						</th>
						<th align="center">
							项目名称
						</th>
						<th align="center">
							立项编码
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
							流程状态
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
							${pageProm.proType }
						</td>
						<td align="center">
							${pageProm.projectName}
						</td>
						<td align="center">
							${pageProm.projectNumber}
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
							<a href="CircuitRunAction_findAduitPage.action?id=${pageProm.epId}">${pageProm.aduitStatus}</a>
<%--							${pageProm.aduitStatus}--%>
						</td>
						<td align="center">
							${pageProm.status}
						</td>
						<td align="center">
							<s:if test='#pageProm.status == "立项"'>
								<a onclick="return window.confirm('确定要删除该信息吗?')"
									href="ProjectManage_delProjectManage.action?id=${pageProm.id}&cpage=${cpage}&total=${total}&pageStatus=${pageStatus}">删除</a>/</s:if>
							<a href="javascript:;" onclick="showDetail('${pageProm.id}')">明细</a>
							<s:if test='#pageProm.aduitStatus == "未审批" || #pageProm.aduitStatus == "打回"'>
								/
							<a href="javascript:;" onclick="updatePro('${pageProm.id}')">修改</a>
							</s:if>
							<s:else>/
								<a href="ProjectManage_findDeptProTime.action?id=${id}&pageStatus=show">查看进度</a>
							</s:else>
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
