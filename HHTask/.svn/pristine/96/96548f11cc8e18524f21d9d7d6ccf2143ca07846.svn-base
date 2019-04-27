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
		<script type="text/javascript"
			src="<%=path%>/javascript/jquery-1.7.2.min.js"></script>
	</head>
	<body onload="createDept('ndept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<h3>当月充值数量查询</h3>
				<form action="AttendanceAction!showRechage.action" method="post">
					<table class="table">
						<tr>
							<th>
								起始时间：
							</th>
							<td>
								<input id="startTime" style="width: 155px" class="Wdate"
									type="text" name="startDate" value="${startDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th>
								结束时间：
							</th>
							<td>
								<input id="endTime" style="width: 155px" class="Wdate"
									type="text" name="endDate" value="${endDate}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th>
								部门名称：
							</th>
							<td>
								<select id="ndept" name="rechargeZhi.dept"
									style="width: 155px;">
									<s:if test="rechargeZhi.dept!=null">
										<option value="${rechargeZhi.dept}">
											${rechargeZhi.dept}
										</option>
									</s:if>
									<s:else>
										<option value="">
											--请选择部门--
										</option>
									</s:else>
								</select>
							</td>
							<th>
								姓名：
							</th>
							<td>
								<select name="rechargeZhi.name" id="usersname" onclick="deptNotNull()">
									<s:if test="rechargeZhi.dept!=null">
										<option value="${rechargeZhi.name}">
											${rechargeZhi.name}
										</option>
									</s:if>
									<s:else>
										<option value="">
											--请选择人员--
										</option>
									</s:else>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="submit" class="input" value="查询" />
								<input type="button" class="input" value="数据导出"
									onclick="exportExcel(this.form);todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							姓名
						</th>
						<th>
							工号
						</th>
						<th>
							部门
						</th>
						<th>
							卡号
						</th>
						<th>
							当月充值数量
						</th>
						<th>
							应充值金额
						</th>
					</tr>
					<s:iterator value="rechargeZhiList" id="pageAttendance" status="pageStatus">
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
						<td>
							${pageAttendance.name}
						</td>
						<td>
							${pageAttendance.code}
						</td>
						<td>
							${pageAttendance.dept}
						</td>
						<td>
							${pageAttendance.cardId}
						</td>
						<td>
							${pageAttendance.sumNumber}
						</td>
						<td>
							${pageAttendance.sumNumberZong}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="7" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<td colspan="7" align="center" style="color: red">
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		</div>
		<script type="text/javascript">
//导出EXCEL
function exportExcel(objForm) {
	objForm.action = "AttendanceAction!daoOutChong.action";
	objForm.submit();
	objForm.action = "AttendanceAction!showRechage.action";
}
function deptNotNull() {
	if ($("#ndept").val() == "" || $("#ndept").val() == "") {
		alert("部门不能为空！");
		return false;
	}
}
//根据部门显示人员
$(function(){
	//显示部门对应的员工信息
	$("#ndept").bind(
			"change",
			function() {
				if ($("#ndept").val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#ndept").val()
						},
						success : function(useradsfa) {
							$("#usersname").empty();//清空
							$("#ncode").val("");
							$("<option>      </option>").appendTo("#usersname");
							$(useradsfa).each(
									function() {
										$("<option value='" 
											+ this.name + "'>"
											+ this.name
											+ "</option>")
										.appendTo("#usersname")
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				} else {
					$("#usersname").empty();//清空
				}
		});
});


</script>
	</body>
</html>
