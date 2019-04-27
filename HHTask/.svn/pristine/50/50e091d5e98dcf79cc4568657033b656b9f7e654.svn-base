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
					<%--<table border="0" width="100%" class="table">
						<tr>
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
					</table>
				--%></form>

				<table style="width: 980px; border-collapse: collapse;font-size: 12px" border="0"
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
							开始时间
						</td>
						<td >
							结束时间
						</td>
						<td >
							创建时间
						</td>
						<td width="60px">
							状态
						</td>
						<td width="140px">
							操作
						</td>
					</tr>
					<s:iterator id="overtime1" value="overtimeListForSpForDsp" status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="11" align="center">
									<font color="#ffffff"> 待审批加班记录</font>
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
							<s:date name="startDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							<s:date name="endDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							<s:date name="createDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							${status}
						</td>
						<td>
							<a target="_blank" onclick="return window.confirm('确定审批该记录?')"
								href="CircuitRunAction_findAduitPage.action?id=${epId}">
								前往审批</a>
								<input type="checkbox" name="idCheckbox" value="${id}"/>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="11" style="text-align: right;">
							<%--<a target="_blank" id="shenpiBatchButton" 
							href="" style="cursor: pointer;" >批量审批</a>
							--%>
							<input type="button" id="shenpiBatchButton" value="批量审批"
									style="width: 100px; height: 50px;" />
							<input type="button" id="dahuiBatchButton" value="批量打回"
									style="width: 100px; height: 50px;" />
							&nbsp;&nbsp;全选
							<input type="checkbox" id="quanxuanCheckbox"/>
						</td>
					</tr>
					<s:iterator id="overtime" value="overtimeListForSpForYsp" status="ststusOvertime">
						<s:if test="#ststusOvertime.first">
							<tr bgcolor="green">
								<th colspan="11" align="center">
									<font color="#ffffff"> 已审批加班记录</font>
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
							<s:date name="startDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							<s:date name="endDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							<s:date name="createDate" format="yyyy-MM-dd HH:mm"/>
						</td>
						<td>
							${status}
						</td>
						<td>
							<a target="_blank"
								href="CircuitRunAction_findAduitPage.action?id=${epId}">
								查看审批结果</a>
							<%--<s:if test="%{#overtime.status=='同意' && user.dept='财务'}">
								<a target="_blank" onclick="return window.confirm('确认改操作码?')"
								href="overtimeAction!confirmOvertime.action?overtime.id=${id}">
								确认加班</a>
							</s:if>
						--%></td>
						</tr>
					</s:iterator>

					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
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
						<td colspan="7" align="center">
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
$(function(){
	$("#shenpiBatchButton").bind("click",function(){
		var idArr='';
		var $idCheckboxArr=$("input[name='idCheckbox']:checked");
		if($idCheckboxArr.length<1){
			alert('至少选择一条记录');
			return ;	
		}
		$idCheckboxArr.each(function(i,n){
			idArr+=n.value+",";
		});
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "overtimeAction!shenpiBatch.action",
	        data:{
				'overtime.params': idArr,
				'overtime.status': "ty"
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert('操作成功');
					window.location="overtimeAction!findAllOvertime.action?role=sp";
				}
			}
		});
	});
	$("#dahuiBatchButton").bind("click",function(){
		var idArr='';
		var $idCheckboxArr=$("input[name='idCheckbox']:checked");
		if($idCheckboxArr.length<1){
			alert('至少选择一条记录');
			return ;	
		}
		$idCheckboxArr.each(function(i,n){
			idArr+=n.value+",";
		});
		$.ajax({
			type: "post",
			dataType: "json",
	        url: "overtimeAction!shenpiBatch.action",
	        data:{
				'overtime.params': idArr,
				'overtime.status': "dh"
			},
			async: false,
			success: function(data){
				var success=data.success;
				if(success){
					alert('操作成功');
					window.location="overtimeAction!findAllOvertime.action?role=sp";
				}
			}
		});
	});
	$("#quanxuanCheckbox").bind("click",function(){
		
		var checked=$("#quanxuanCheckbox").attr("checked");
		if(checked=='checked'){
			//$("#quanxuanCheckbox").attr("checked",false);
			$("input[name='idCheckbox']").attr("checked",true);
		}else{
			//$("#quanxuanCheckbox").attr("checked",true);
			$("input[name='idCheckbox']").attr("checked",false);
		}
	});
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