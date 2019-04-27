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
				</div>
			</div>

			<div align="center">
				<form action="overtimeAction!findAllOvertime.action" method="post"
					style="">
					<br>
					<table class="table">
						<%--<tr>
							<td align="right">
								申请人工号:
							</td>
							<td>
								<input id="applyCode" type="text" name="overtime.applyCode" value="" />
							</td>
							<td align="right">
								申请人姓名:
							</td>
							<td>
								<input id="applyName" type="text" name="overtime.applyName" value="" />
							</td>
							<td align="right">
								申请人部门:
							</td>
							<td>
								<select id="applyDept" name="overtime.applyDept">
									<option value="">请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								加班人人工号:
							</td>
							<td>
								<input id="overtimeCode" type="text" name="overtime.overtimeCode" value="" />
							</td>
							<td align="right">
								加班人姓名:
							</td>
							<td>
								<input id="overtimeName" type="text" name="overtime.overtimeName" value="" />
							</td>
							<td align="right">
								加班人部门:
							</td>
							<td>
								<select id="overtimeDept" name="overtime.overtimeDept">
									<option value="">请选择</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								加班开始时间:
							</td>
							<td>
								<input id="startDate" class="Wdate" type="text" name="overtime.startDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">
								加班结束时间:
							</td>
							<td>
								<input id="endDate" class="Wdate" type="text" name="overtime.endDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td align="right">
								创建时间:
							</td>
							<td>
								<input id="createDate" class="Wdate" type="text" name="overtime.createDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							
						</tr>
						<tr>
							<td align="right">
								加工件号:
							</td>
							<td>
								<select name="overtime.markId" id="markId">
									<option value="">请选择</option>
								</select>
							</td>
							<td align="right">
								数量:
							</td>
							<td>
								<input type="text" name="overtime.amount" value="" />
							</td>
						
						</tr>
						<tr>
							<td align="center" colspan="8">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					--%>
					</table>
				</form>

				<table class="table" align="center">
					<tr align="center" bgcolor="#c0dcf2"
						style="height: 40px; font-weight: bold;">
						<td width="40px">
							序号
						</td>
						<td width="100px">
							加班人工号
						</td>
						<td width="100px">
							加班人姓名
						</td>
						<td width="100px">
							加工件号
						</td>
						<td width="50px">
							数量
						</td>
						<td>
							开始时间
						</td>
						<td>
							结束时间
						</td>
						<td>
							创建时间
						</td>
						<td>
							中途休息时长(分钟)
						</td>
						<td>
							加班时长(h)
						</td>
						<td width="60px">
							状态
						</td>
						<td width="140px">
							操作
						</td>
					</tr>
					<s:iterator id="overtime" value="overtimeListForJbForDtj"
						status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff"> 待提交加班记录</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusOvertime.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusOvertime.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusOvertime.index+1" />
							</font>
						</td>
						<td>
							${overtimeCode}
						</td>
						<td>
							${overtimeName}
						</td>
						<td>
							${markId}
						</td>
						<td>
							${amount}
						</td>
						<td>
							${startDate}
						</td>
						<td>
							${endDate}
						</td>
						<td>
							${createDate}
						</td>
						<td>
							${xiuxi}
						</td>
						<td>
							${overTimeLong}
						</td>
						<td>
							<s:if test="#overtime.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${overtime.epId}">${status}</a>
							</s:if>
						</td>
						<td>
							<a href="overtimeAction!findOvertimeIdByDetail.action?id=${id}">明细</a>
							<a href="overtimeAction!getUpdatePage.action?id=${id}&tag=${tag}&role=${role}"> 修改</a>/
							<a onclick="return window.confirm('确认要删除该加班记录?')"
								href="overtimeAction!deleteOvertime.action?id=${id}&tag=${tag}&role=${role}">删除</a>/
						</td>
						</tr>
					</s:iterator>
					<s:iterator id="overtime" value="overtimeListForJbForYtj"
						status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="15" align="center">
									<font color="#ffffff"> 已提交加班记录</font>
								</th>
							</tr>
						</s:if>
						<s:if test="#ststusOvertime.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#ststusOvertime.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="red">
							</s:else>
							<s:property value="#ststusOvertime.index+1" />
							</font>
						</td>
						<td>
							${overtimeCode}
						</td>
						<td>
							${overtimeName}
						</td>
						<td>
							${markId}
						</td>
						<td>
							${amount}
						</td>
						<td>
							${startDate}
						</td>
						<td>
							${endDate}
						</td>
						<td>
							${createDate}
						</td>
						<td>
							${xiuxi}
						</td>
						<td>
							${overTimeLong}
						</td>
						<td>
							<s:if test="#overtime.epId!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${overtime.epId}">${status}</a>
							</s:if>
						</td>
						<td>
							<a href="overtimeAction!findOvertimeIdByDetail.action?id=${id}&tag=backup">明细</a>
							<a href="overtimeAction!getUpdatePage.action?id=${id}&tag=backup">后补</a>
							<s:if test="#overtime.actualEpStatus!=null">
								<a href="CircuitRunAction_findAduitPage.action?id=${overtime.actualEpId}">后补状态</a>
							</s:if>
							<s:if test="#overtime.status=='未审批'||#overtime.status=='打回'">
								<a target="_blank"
									href="overtimeAction!getUpdatePage.action?id=${id}"> 修改</a>/
								<a onclick="return window.confirm('确认要删除该加班记录?')"
									href="overtimeAction!deleteOvertime.action?id=${id}&role=${role}">删除</a>/
							</s:if>
						</td>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
					</tr>
					<tr>
						<td colspan="15" align="center">
							<font color="red">${errorMessage}${successMessage}</font>
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
<script type="text/javascript">
$(function() {
	/*
	$.ajax({
		type: "get",
		dataType: "text",
	    url: "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async: false,
		success: function(data){
			var dept=data.split("|");
			for(var i=0;i<dept.length-1;i++){
				var applyDeptItem=new Option(dept[i],dept[i]);
				var overtimeDeptItem=new Option(dept[i],dept[i]);
				$("#applyDept").append(applyDeptItem); 
				$("#overtimeDept").append(overtimeDeptItem); 
			}
		}
	});
	 */
	/*
	$.ajax({
		type: "get",
		dataType: "text",
	    url: "overtimeAction!finAllMarkIdForSetlect.action",
		async: false,
		success: function(data){
			var dept=data.split("|");
			for(var i=0;i<dept.length-1;i++){
				var markIdItem=new Option(dept[i],dept[i]);
				$("#markId").append(markIdItem); 
			}
		}
	});
	 */
	//createDept('applyDept');
	//createDept('overtimeDept');
	//createDept('markId','overtimeAction!finAllMarkIdForSetlect.action');
});
</script>