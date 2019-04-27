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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div class="container">
			<h1 class="text-center">会议列表</h1>
			<div class="row">
				<form action="${url}" method="post">
				<table class="table table-responsive">
					<tr >
						<th class="text-right">
							会议标题：
						</th>
						<td>
							<input type="text" name="meeting.title" value="${meeting.title}">
						</td>
						<th class="text-right">
							会议内容：
						</th>
						<td>
							<input type="text" name="meeting.content" value="${meeting.content}">
						</td>
					</tr>
					<tr>
						<td colspan="8" class="text-center">
							<input type="submit" value="查询" class="input btn ">
							<input type="button" value="前往添加" class="input btn" 
								onclick="location.href='${pageContext.request.contextPath}/meetingAction!toAddMeeting.action'">
						</td>
					</tr>
				</table>
				</form>
			</div>
			
			<div class="row">
				<table class="table table-responsive table-striped table-bordered">
					<thead>
						<tr >
							<th class="text-center">序号</th>
							<th class="text-center">会议标题</th>
							<th class="text-center">会议位置</th>
							<th class="text-center">会议内容</th>
							<th class="text-center">会议开始 -- 结束时间</th>
							<th class="text-center">添加时间</th>
							<th class="text-center">添加人</th>
							<th class="text-center">审批状态</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody class="text-center">
						<s:iterator value="meetingList" id="ml" status="ps">
							<tr>
								<td>${ps.index+1 }</td>
								<td>${ml.title }</td>
								<td>${ml.position}</td>
								<td>${ml.content }</td>
								<td>${ml.startDate} -- ${ml.endDate}</td>
								<td>${ml.addTime }</td>
								<td>${ml.userName }</td>
								<td><a href="CircuitRunAction_findAduitPage.action?id=${ml.epId}">${ml.epStatus}</a></td>
								<td>
									<input type="button" class="btn btn-default" value="修改" 
										onclick="location.href='${pageContext.request.contextPath}/meetingAction!toUpdateMeeting.action?id=${ml.id}'"/>
									<input type="button" class="btn btn-default" value="绑定人员" 
										onclick="location.href='${pageContext.request.contextPath}/meetingAction!toBindUsers.action?id=${ml.id}'"/>
									<input type="button" class="btn btn-default" value="删除" 
										onclick="deleteMeeting(${ml.id})"/>
								</td>
							</tr>
							
						</s:iterator>
					</tbody>
					<tfoot>
						<tr id="fenyeTr">
							<td class="text-right" colspan="20"> 
								第
								<font color="red" id="cpage">${cpage}</font> / <font id="total">${total}</font> 页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
	 								styleClass="page" theme="number" /> 
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript" >
		function deleteMeeting(id){
			if(confirm("删除会议会将会议人员一并删除,确认要删除会议吗？")){
				location.href='${pageContext.request.contextPath}/meetingAction!deleteMeeting.action?id='+id
			}
			
		}
		
	</script>
	</body>
</html>