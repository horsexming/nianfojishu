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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h2>不合格品提交记录</h2>
			<form action="ProcardAction!findbreaksubmitList.action" method="POST">
				<table class="table" >
					<tr>
						<th align="right">
							类型
						</th>
						<th align="left">
							<input type="radio" value="零件损坏" name="breaksubmit.type">零件损坏 &nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="外购件不合格" name="breaksubmit.type">外购件不合格
						</th>
						<th align="right">
							组别
						</th>
						<th align="left">
							<input type="radio" value="上工序不合格" name="breaksubmit.breakgroup">上工序不合格&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" value="本工序不合格" name="breaksubmit.breakgroup">本工序不合格
						</th>
					</tr>
					<tr>
						<th align="right">
							工序号
						</th>
						<th align="left">
							<input type="text" value="" name="breaksubmit.processNo">
						</th>
						<th align="right">
							工序名
						</th>
						<th align="left">
							<input type="text" value="" name="breaksubmit.processName">
						</th>
					</tr>
					<tr>
						<th align="right">
							自制件件号
						</th>
						<th align="left">
							<input type="text" value="" name="breaksubmit.markId">
						</th>
						<th align="right">
							关联外购件件号
						</th>
						<th align="left">
							<input type="text" value="" name="breaksubmit.wgmarkId">
						</th>
					</tr>
					</table>
				<input type="submit" value="查询" class="input">
			</form>
			<table class="table">
				<tr  align="center" bgcolor="#c0dcf2" height="50px">
					<th>序号</th>
					<th>类型</th>
					<th>组别</th>
					<th>总成件号</th>
					<th>总成批次</th>
					<th>业务件号</th>
					<th>工序号</th>
					<th>工序名</th>
					<th>自制件件号</th>
					<th>关联外购件件号</th>
					<th>提交数量</th>
					<th>提交人</th>
					<th>提交时间</th>
					<th>确认数量</th>
					<th>确认人</th>
					<th>确认时间</th>
					<th>提交方式</th>
					<th>处理结果</th>
					<th>审批动态</th>
					<th>操作</th>
				</tr>
				<s:iterator value="bsList" id="pageList" status="pagestatus">
					<s:if test="#pagestatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pagestatus.index+1" />
								</td>
						<td>${pageList.type}</td>	
						<td>${pageList.breakgroup}</td>	
						<td>${pageList.rootmarkId}</td>
						<td>${pageList.rootselfCard}</td>
						<td>${pageList.ywmarkId}</td>
						<td>${pageList.processNo}</td>	
						<td>${pageList.processName}</td>	
						<td>${pageList.markId}</td>	
						<td>${pageList.wgmarkId}</td>	
						<td>${pageList.tjbreakcount}</td>	
						<td>${pageList.tjUsersName}</td>	
						<td>${pageList.tjTime}</td>	
						<td>${pageList.qrbreakcount}</td>	
						<td>${pageList.qrUsersName}</td>	
						<td>${pageList.qrTime}</td>	
						<td>${pageList.tjtype}</td>	
						<td>${pageList.clResult}</td>	
						<td><a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}" >审批动态</a></td>
						<td>
							<a href="ProcardAction!findbreaksubmitById.action?id=${pageList.id}" >明细${pageList.hxStatus} </a>
							<s:if test='pageStatus == "chanxian"   && (#pageList.hxStatus==null || #pageList.hxStatus == "")'>
								<s:if test='#pageList.clResult == "返修"'>
									/<a href="ProcardAction!findbreaksubmitById.action?id=${pageList.id}&pageStatus=fanxiu">返修 </a>
								</s:if>
							</s:if>
							<s:elseif test='pageStatus == "QC"'>
								<a href="ProcardAction!findbreaksubmitById.action?id=${pageList.id}&pageStatus=${pageStatus}" >选择责任人</a>
							</s:elseif>
						</td>
						</tr>
				</s:iterator>
				<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
			</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
