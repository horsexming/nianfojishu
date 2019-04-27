<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					汇总页面
				</h3>
				<form action="register_collect.action" method="post"
					onsubmit="return testTime()" name="myForm">
					<table>
						<tr>
							<td>
								<label>
									起始时间：
								</label>
							</td>
							<td>
								<input id="startTime" style="width: 155px" class="Wdate"
									type="text" name="upTime" value="${upTime }"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td>
								<label>
									结束时间：
								</label>
							</td>
							<td>
								<input id="endTime" style="width: 155px" class="Wdate"
									type="text" name="downTime" value="${downTime }"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>
									部门名称：
							</td>
							<td>
								<select name="deptID" id="depatment">
									<option value="0">
										选择部门
									</option>
									<s:iterator id="dept" value="deptList">
										<s:if test="#dept.id == deptID">
											<option value="${dept.id }" selected="selected">${dept.name }</option>
										</s:if>
										<s:else>
											<option value="${dept.id }">${dept.name }</option>
										</s:else>
									</s:iterator>
								</select>
							</td>
							<td>
								<label>
									人员姓名：
								</label>
							</td>
							<td>
								<select name="personId" id="person">
									<option value="0" selected="selected">
										-----------
									</option>
								</select>
							</td>
							<td>
								<input type="hidden" name="errorMessage" value="all" >
								<input type="submit" style="width: 80px; height: 50px;" value="查询" />
							</td>
							<td>
								<input type="button" value="导出明细" style="width: 80px; height: 50px;" onclick="exportExcel();todisabledone(this)" data="downData" />
							</td>
							<td>
								<input type="button" value="按部门导出" style="width: 80px; height: 50px;" onclick="exportDeptExcel();todisabledone(this)" data="downData" />
							</td>
							
						</tr>
					</table>
				</form>


				<div id="cont" style="display: block">
					<form action="register_batchWithdrawMon.action" method="post"
						onsubmit="return valiNullSelect()">
					<table  class="table">
						<tr bgcolor="#c0dcf2" height="50px">
								<td>序号</td>
								<td>
									人员姓名
								</td>
								<td align="center">
									卡号
								</td>
								<td align="center">
									部门
								</td>
								<td align="center">
									月份
								</td>
								<td align="center">
									充值金额
								</td>
								<td align="center">
									消费金额
								</td>
								<td align="center">
									余额(份)
								</td>
								<td align="center">
									应退(元)
								</td>
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
									<td>
											${pageList.name }
									</td>
									<td>
											${pageList.cardNo }
									</td>
									<td>
											${pageList.deptName }
									</td>
									<td>
											${pageList.time }
									</td>
									<td>
											${pageList.supplyFun }
									</td>
									<td>
											${pageList.cosumeFund }
									</td>
									<td>
											${pageList.balance }
									</td>
									<td>
											${pageList.refund }
									</td>
								</tr>
							</s:iterator>
							<tr>
						<s:if test="errorMessage==null">
							<td colspan="6" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
						</table>
					</form>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
	function testTime() {
		var startStr = document.getElementById("startTime").value;
		var endStr = document.getElementById("endTime").value;
		if (startStr != "" && endStr != "") {
			var start = startStr.split("-");
			var end = endStr.split("-");
			var startTime = new Date(start[0], start[1], start[2]);
			var endTime = new Date(end[0], end[1], end[2]);
			var myDate = new Date(); //获得当前时间
			myDate.setMonth(myDate.getMonth() + 1);//为当前date的月份+1后重新赋值
			if (startTime <= endTime == false) {
				alert("开始时间不能大于结束时间!请重新选择!谢谢!");
				return false;
			} else if (endTime <= myDate == false) {
				alert("结束时间不能大于当前时间!请重新选择!谢谢!");
				return false;
			}
		} else {
			alert("请提供查询时间段");
			return false;
		}
		return true;
	}
	function withdrawing(id) {
		window.location = "register_withdrawMon.action?id=" + id;
	}
	function exportExcel() {
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		var deptId = document.getElementById("deptId");
		if (startTime != null && endTime != null) {
				endTime = endTime.value;
				startTime = startTime.value;
			if (endTime != "" && endTime.length > 0 && startTime != ""
					&& startTime.length > 0) {
				document.forms.myForm.action="register_export.action";
				document.forms.myForm.submit();
				document.forms.myForm.action="register_collect.action";
			}else{
				alert("请选择时间段!谢谢");
			}	
		} else {
			alert("请选择时间段!谢谢");
		}
	}
	function exportDeptExcel() {
		var startTime = document.getElementById("startTime");
		var endTime = document.getElementById("endTime");
		var deptId = document.getElementById("deptId");
		if (startTime != null && endTime != null) {
				endTime = endTime.value;
				startTime = startTime.value;
			if (endTime != "" && endTime.length > 0 && startTime != ""
					&& startTime.length > 0) {
				document.forms.myForm.action="register_deptExport.action";
				document.forms.myForm.submit();
				document.forms.myForm.action="register_collect.action";
			}else{
				alert("请选择时间段!谢谢");
			}	
		} else {
			alert("请选择时间段!谢谢");
		}
	}
</script>
		<script type="text/javascript">
$(document).ready(function(){
	$("#depatment").change(function(){
		var val = $("#depatment").val();
		document.getElementById("person").length=1;
		$.post("register_initPerson.action",{deptID:val},function(data){
			for(var i = 0;i < data.length;i++){
				$("#person").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
			}
		},"json");
	});
});
</script>
	</body>
</html>
