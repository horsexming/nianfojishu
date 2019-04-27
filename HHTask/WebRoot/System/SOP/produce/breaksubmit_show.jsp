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
			<div align="center" >
				<h2>${breaksubmit.markId}的详细信息</h2>
			<form action="ProcardAction!choseUsers.action" method="post" id="myform">
				<table class="table">
					<tr>
						<th align="right">
							自制件件号
						</th>
						<td>
							${breaksubmit.markId }
						</td>
						<th align="right">
							自制件名称
						</th>
						<td>
							${breaksubmit.proName}
						</td>
						<th align="right">
							工序号
						</th>
						<td>
							${breaksubmit.processNo }
						</td>
						<th align="right">
							工序名
						</th>
						<td>
							${breaksubmit.processName}
						</td>
					</tr>
					<tr>
						<th align="right">
							类型
						</th>
						<td>
							${breaksubmit.type }
						</td>
						<th align="right">
							组别
						</th>
						<td>
							${breaksubmit.breakgroup}
						</td>
						<th align="right">
							提交数量
						</th>
						<td>
							${breaksubmit.tjbreakcount }
						</td>
						<th align="right">
							确认数量
						</th>
						<td>
							${breaksubmit.qrbreakcount}
						</td>
					</tr>
					<tr>
						<th align="right">
							提交人
						</th>
						<td>
							${breaksubmit.tjUsersName }
						</td>
						<th align="right">
							确认人
						</th>
						<td>
							${breaksubmit.qrUsersName}
						</td>
						<th align="right">
							提交时间
						</th>
						<td>
							${breaksubmit.tjTime }
						</td>
						<th align="right">
							确认时间
						</th>
						<td>
							${breaksubmit.qrTime}
						</td>
					</tr>
					<tr>
						<th align="right">
							提交方式
						</th>
						<td>
							${breaksubmit.tjtype }
						</td>
						<th align="right">
							处理结果
						</th>
						<td>
							${breaksubmit.clResult}
						</td>
						<th align="right">
							责任人
						</th>
						<td colspan="3">
							<s:if test='pageStatus == "QC" && (breaksubmit.personLiable==null||breaksubmit.personLiable == "")'>
								<ul>
									<s:iterator value="list" id="proLog" status="pagestatus1">
										<li>
											<input type="radio" value="${proLog.id}" name="breaksubmit.prologId"/> ${proLog.usernames }
										</li>
									</s:iterator>
								</ul>
							</s:if>
							<s:else>
								${breaksubmit.personLiable }
							</s:else>
						</td>
					</tr>
					<tr>
						<th align="right">
							返修人工号
						</th>
						<td>
							<s:if test='pageStatus == "fanxiu"'>
								<input type="text" value="" name="breaksubmit.fanxiuCode">
							</s:if>
							<s:else>
								${breaksubmit.fanxiuCode}
							</s:else>
							
						</td>
						<td colspan="6" align="right">
							<span style="color:red">
							<b>注:多人返修中间以、分开。。</b>	
							</span>
						</td>
					</tr>
				</table>
				<input type="hidden" value="${breaksubmit.id}" name="breaksubmit.id">
				<input type="hidden" value="${pageStatus}" name="pageStatus">
				<input type="submit" value="确定责任人"  class="input"  id="sub">
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${pageStatus}' == 'fanxiu'){
		$("#myform").attr("action","ProcardAction!fanxiu.action")
		$("#sub").val("返修完成");
	}
	
	
})

</SCRIPT>
	</body>
</html>
