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

</script>

	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">BOM版本升级申请</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng">
			<div align="center" style="border: 1px solid #00000;">
				<form action="procardTemplateGyAction_bbApplyList.action"
					method="post">
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="6">
								BOM版本升级管理
							</th>
						</tr>
						<tr>
							<th>
								申请人:
							</th>
							<td>
								<input name="bbAply.applicantName"  />
							</td>
							<th>
								状态:
							</th>
							<td>
							   <SELECT name="bbAply.status">
							     <option>未审批</option>
							     <option>审批中</option>
							     <option>同意</option>
							     <option>打回</option>
							   </SELECT>
							</td>
						</tr>
						<tr>
							<th>
								开始时间:
							</th>
							<td><input class="Wdate" type="text" name="start"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
							<th>
								结束时间:
							</th>
							<td><input class="Wdate" type="text" name="end"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="查询(Query)" class="input" />
								<input type="reset" value="清空(Empty)" class="input" />
							</th>
						</tr>
					</table>
				</form>

				<div id="rootTemplateDiv">
					<div id="showMessage"
						style="color: red; font-size: 14px; font-weight: bolder;">
					</div>
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								申请人
							</th>
							<th align="center">
								申请时间
							</th>
							<th align="center">
								备注
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="bbAplyList" id="pagebbAply"
							status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageindex.index+1" />
							</td>
							<td>
								${pagebbAply.applicantName}
							</td>
							<td style="width: 180px;">
								${pagebbAply.applyTime}
							</td>
							<td>
								${pagebbAply.remark}
							</td>
							<td>
								${pagebbAply.status}
							</td>
							<td align="center">
							<a target="_bbapply"
												href="procardTemplateGyAction_bbApplydetail.action?id=${pagebbAply.id}">内容明细</a>
							<a target="_bbapply"
												href="CircuitRunAction_findAduitPage.action?id=${pagebbAply.epId}">审批明细</a>
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
								<td colspan="11" align="center" style="color: red">${errorMessage}
							</td>
							</s:else>
						</tr>
					</table>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function showDaoRuDiv(){
	$("#showProcardDetail").attr("src",
			"procardTemplateGyAction_toDaoRuBom.action");
	chageDiv('block');
}
		</SCRIPT>
	</body>
</html>
