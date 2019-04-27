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
				<h3>扫描入库申请条码</h3>
				<form action="storage_scanningApplyFor.action" method="post">
				<p><span><font style="font-size: 20px;font-weight: bold">请刷卡：</font></span>
					<input name="vsto.applyForNum" value="${vsto.applyForNum}"/><input type="submit" value="查询"/>
				</p>
				</form>
				<hr>
				<s:if test="list!=null&&!list.isEmpty()">
				<h3>入库申请详单</h3>
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td width="30px">序号</td>
						<td>部门</td>
						<td>类别</td>
						<td>编号</td>
						<td>名称</td>
						<td>规格</td>
						<td>单位</td>
						<td>数量</td>
						<td>计划月份</td>
						<td>到货期限</td>
						<td>计划依据</td>
						<td>预算金额</td>
						<td>加急</td>
						<td></td>
					</tr>
					<s:iterator value="list" id="pageList" status="pageStatus">
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
									<td>${pageList.detailAppDept}</td>
									<td>${pageList.detailChildClass}</td>
									<td>${pageList.detailSeqNum}</td>
									<td>${pageList.detailAppName}</td>
									<td>${pageList.detailFormat}</td>
									<td>${pageList.detailUnit}</td>
									<td>${pageList.detailCount}</td>
									<td>${pageList.detailPlanMon}</td>
									<td>${pageList.detailArrDate}</td>
									<td>${pageList.detailPlanAcco}</td>
									<td>${pageList.detailBudgetMoney}</td>
									<td>${pageList.detailIsBusy}</td>
									<td><a href=".action?id=${pageList.id}">入库</a></td>
						</s:iterator>
						</tr>
							<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
							<span style="width:700px;text-align:center;font-size:15px;font-weight:bold;margin-right:300px;">
	    						<a href="scanAppForm!readAddStorange.action" />打印入库明细</a>
	    					</span>
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
				</s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
