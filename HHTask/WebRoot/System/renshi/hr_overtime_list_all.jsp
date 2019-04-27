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
				<form action="overtimeAction!findOvertimeListForAll.action?tag=${tag}" method="post"
					style="">
					<br>
					<table border="0" width="100%" class="table">
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
						--%><tr>
							<td align="right">
								加班人人工号:
							</td>
							<td>
								<input id="overtimeCode" type="text" name="overtime.overtimeCode" value="${overtime.overtimeCode}" />
							</td>
							<td align="right">
								加班人姓名:
							</td>
							<td>
								<input id="overtimeName" type="text" name="overtime.overtimeName" value="${overtime.overtimeName}" />
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
								起始加班开始日期:
							</td>
							<td>
								<input id="startDate" class="Wdate" type="text" name="startDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${startDate}" />
							</td>
							<td align="right">
								截止加班开始日期:
							</td>
							<td>
								<input id="endDate" class="Wdate" type="text" name="endDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" value="${endDate}" />
							</td>
							<%--<td align="right">
								创建时间:
							</td>
							<td>
								<input id="createDate" class="Wdate" type="text" name="overtime.createDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							
						--%></tr>
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
								状态:
							</td>
							<td>
								<select name="overtime.status">
									<option value="">所有</option>
									<option value="未提交">未提交</option>
									<option value="审批中">审批中</option>
									<option value="同意">同意</option>
									<option value="打回">打回</option>
									<option value="已确认">已确认</option>
								</select>
							</td>
						
						</tr>
						<tr>
							<td align="center" colspan="8">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
								<input type="button" value="导出" onclick="exportExcel();todisabledone(this)" data="downData"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<div align="center">
					<input class="Wdate" type="text" name="tag" id = "tag" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
					<input type="submit" value="非换休时长导出" style="height: 30px;width: 110px;" onclick="exportExcel1();todisabledone(this)" data="downData"/>
				</div>
				<table class="table"
					align="center" >
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
						<td >
							申请开始时间
						</td>
						<td >
							申请结束时间
						</td>
						<td >
							实际开始时间
						</td>
						<td >
							实际结束时间
						</td>
						<td >
							加班时长(h)
						</td>
						<td >
							创建时间
						</td>
						<td>
							状态
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:iterator id="o" value="overtimeListForAll" status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="13" align="center">
									<font color="#ffffff"> 待确认加班记录</font>
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
						<td >
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
							${filnalStartDate}
						</td>
						<td>
							${filnalEndDate}
						</td>
						<td>
							${overTimeLong}
						</td>
						<td>
							${createDate}
						</td>
						<td>
							${status}
						</td>
						<td>
							<a href="overtimeAction!findOvertimeIdByDetail.action?id=${id}">明细</a>
							<a href="CircuitRunAction_findAduitPage.action?id=${epId}"">审批动态</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</s:if>
						<s:else>
							<tr>
								<td colspan="13" align="center">
									<font color="red">${errorMessage}${successMessage}</font>
								</td>
							</tr>
						</s:else>
					</tr>
					
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
<script type="text/javascript">
$(function(){
	
	
	
	/**/
	$.ajax({
		type: "get",
		dataType: "text",
        url: "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async: false,
		success: function(data){
			var dept=data.split("|");
			for(var i=0;i<dept.length-1;i++){
				//var applyDeptItem=new Option(dept[i],dept[i]);
				var overtimeDeptItem=new Option(dept[i],dept[i]);
    			//$("#applyDept").append(applyDeptItem); 
    			$("#overtimeDept").append(overtimeDeptItem); 
			}
		}
	});
	
	/**/
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
	
	//createDept('applyDept');
	//createDept('overtimeDept');
	createDept('markId','overtimeAction!finAllMarkIdForSetlect.action');
	var overtimeDept='${overtime.overtimeDept}';
	var markId='${overtime.markId}';
	var status='${overtime.status}';
	$("#overtimeDept").find("option[value='"+overtimeDept+"']").attr("selected",true);
	$("#markId").find("option[value='"+markId+"']").attr("selected",true);
	$("#status").find("option[value='"+status+"']").attr("selected",true);
});

//导出Excel
function exportExcel() {
	document.forms[0].action="overtimeAction!exportExcelOvertimeListForAll.action";
	document.forms[0].submit();
	document.forms[0].action="overtimeAction!findOvertimeListForAll.action";
}
//导出指定月份加班不换休数据
function exportExcel1() {
	var mouth = $("#tag").val();
	if(mouth==null||mouth==""){
		alert("请选择月份");
		return false;
	}
	document.forms[0].action="overtimeAction!showOverTimeshi.action?tag="+mouth;
	document.forms[0].submit();
	document.forms[0].action="overtimeAction!findOvertimeListForAll.action";
}
</script>