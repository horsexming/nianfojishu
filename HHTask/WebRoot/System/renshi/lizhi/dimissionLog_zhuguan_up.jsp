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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				<form action="dimissionLogAction_updateZhuguan.action" method="post"
					onsubmit="return dimissupadte()">
					<table class="table" align="center">
						<tr>
							<th align="center" colspan="6" style="size: 20pt;">
								<img alt="loge" src="<%=path%>/images/zhaobiao.jpg"
									style="width: 30px; height: 30px;">
								主管确定离职申请单
								<input type="hidden" name="tag"
										value="zhuguan" />
								<input type="hidden" name="dimissionLog.id"
									value="${dimissionLog.id}" />
							</th>
						</tr>
						<tr>
							<td colspan="2" align="left" style="border-right-width: 0px;">
								<h3>
									&nbsp;&nbsp;&nbsp;&nbsp;员工合同编号：${dimissionLog.contract_number}
								</h3>
							</td>
							<td colspan="4" align="right" style="border-left-width: 0px;">
								<h3>
									申请单编号：${dimissionLog.shenqing_number} &nbsp;&nbsp;&nbsp;&nbsp;
								</h3>
							</td>
						</tr>
						<tr>
							<th>
								申请人姓名
							</th>
							<th>
								部门
							</th>
							<th>
								岗位
							</th>
							<th colspan="2">
								本厂工作年限
							</th>
							<th>
								工号
							</th>
						</tr>
						<tr>
							<td align="center">
								${dimissionLog.name}
							</td>
							<td align="center">
								${dimissionLog.dept}
							</td>
							<td align="center">
								${dimissionLog.job}
							</td>
							<td align="center" colspan="2">
								${dimissionLog.job}
							</td>
							<td align="center">
								${dimissionLog.code}
							</td>
						</tr>
						<tr>
							<th>
								离职原因：
							</th>
							<td colspan="5">
								${dimissionLog.dimission_Reason}
							</td>
						</tr>

						<tr>
							<th>
								离职后去向：
							</th>
							<td align="left" colspan="2">
								${dimissionLog.dimission_laterGo}
							</td>
							<th>
								请填写离职时间
							</th>
							<td align="center" colspan="2">
								<input class="Wdate" type="text" name="dimissionLog.app_time"
									id="app_time"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<%--<tr>
							<th>
								是否有遗留问题或劳动争议:
							</th>
							<td colspan="5">
										${dimissionLog.naowuzhengyi}&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="dimissionLog.naowuzhengyi=='是'&&dimissionLog.naowuzhengyi!=null">
									${dimissionLog.naowuzhengyi}
								</s:if>
							</td>
						</tr>
						--%>
						<tr>
							<th colspan="6">
								请确认以下条款
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="left">
								</s:if>
								<s:else>
									<tr align="left">
								</s:else>
								<td align="left" colspan="6">
									&nbsp;&nbsp;&nbsp;&nbsp;${provil.content}
								</td>
							</s:iterator>
						</tr>
						<tr>
							<td align="center" colspan="6">
								<input type="submit" value="确认" 
									onclick="return window.confirm('离职时间确认后将无法修改，是否继续?')" style="width: 80px; height: 40px;" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 40px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function dimissupadte(){
			
		var app_time = document.getElementById("app_time").value;
			if (app_time == "" || app_time == null) {
				alert("确认离职时间不能为空");
				return false;
			}else{
				//alert("确认后离职时间将无法修改，是否继续?");
			}
		}
		
		</SCRIPT>
	</body>
</html>
