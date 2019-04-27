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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<%-- 		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"> </script>  --%>
		<script type="text/javascript"
 			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"> </script> 
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
// 			console.log($().jquery); // => '3.2.1'
// 			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.5版本:
// 			console.log($().jquery); // => '1.5.0'
		
		</script>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<!-- 模态框（Modal） -->
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<form action="${url}" method="post">
					<table class="table">
						<tr>
							<th class="text-right">来访人</th>
							<td><input type="text" name="visitor.visitorName" value="${visitor.visitorName }"></td>
							<th class="text-right">来访人公司</th>
							<td><input type="text" name="visitor.visitorComp" value="${visitor.visitorComp }"></td>
							<th class="text-right">来访人手机号</th>
							<td><input type="text" name="visitor.visitorPhone" value="${visitor.visitorPhone }"></td>
						</tr>
						<tr>
							<th class="text-right">来访时间</th>
							<td><input type="text" name="visitor.dateTime" value="${visitor.dateTime}"></td>
							<th class="text-right">来访状态</th>
							<td>
								<select name="visitor.visitorStatus">
									<s:iterator value="{'','预申请','待打印','待进门','待出门','已出门'}" id="status">
										<s:if test="#status==visitor.visitorStatus">
											<option selected="selected">${status }</option>
										</s:if>
										<s:else>
											<option>${status }</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<th class="text-right">审批状态</th>
							<td>
								<select name="visitor.epStatus">
									<s:iterator value="{'','预申请','未审批','打回','同意'}" id="status">
										<s:if test="#status==visitor.epStatus">
											<option selected="selected">${status }</option>
										</s:if>
										<s:else>
											<option>${status }</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="6" class="text-center">
								<input type="submit" value="查询" class="input" >
							</td>
						</tr>
					</table>
					</form>
				</div>
			</div>
			<div class="row">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>序号</th>
							<th>来访人</th>
							<th>来访人公司</th>
							<th>来访人手机号</th>
							<th>申请时间</th>
							<th>来访时间</th>
							<th>进入时间</th>
							<th>离开时间</th>
							<th>被访人</th>
							<th>来访缘由</th>
							<th>来访状态</th>
							<td>审批状态</td>
							<td>操作</td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="visitorList" id="vl" status="ps">
							<s:if test="#ps.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 25px;" onmouseout="outBgcolor(this,'')">
							</s:else>
								<td class="text-center">${ps.index+1}</td>
								<td>${vl.visitorName }</td>
								<td>${vl.visitorComp }</td>
								<td>${vl.visitorPhone }</td>
								<td>${vl.addTime }</td>
								<td>${vl.dateTime }</td>
								<td>${vl.inTime }</td>
								<td>${vl.outTime }</td>
								<td>${vl.interviewee }</td>
								<td>${vl.visitorCause }</td>
								<td>${vl.visitorStatus }</td>
								<td><a href="CircuitRunAction_findAduitPage.action?id=${vl.epId}">${vl.epStatus }</a></td>
								<td>
									<a href="${pageContext.request.contextPath}/visitorAction!showVisitorDetail.action?id=${vl.id}">查看明细</a>
									<s:if test="tag!=null && tag=='self'">
										<br>
										<a href="${pageContext.request.contextPath}/visitorAction!showVisitorDetail.action?id=${vl.id}&pageStatus=longVisitor">常访申请</a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					<tfoot>
						<tr>
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
	</body>
</html>
