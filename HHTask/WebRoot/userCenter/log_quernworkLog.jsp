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
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WorkLogAction!updateWorkLog.action"
					method="post" onsubmit="return checkForm()">
					<table class="table">
						<tr>
							<th align="right">
								标题:
							</th>
							<td>
								<SPAN>${workLog.title}</SPAN>
							</td>
						</tr>
						<tr>
							<th align="right">
								类别:
							</th>
							<td align="left">
								<SPAN>${workLog.workLogClass.name}</SPAN>
							</td>
						</tr>
						<tr>
							<th align="right">
								状态:
							</th>
							<td>
								<SPAN>${workLog.logStatus}</SPAN>
							</td>
						</tr>
						<tr>
							<th align="right">
								指派状态:
							</th>
							<td>
								<SPAN>${workLog.zpStatus}</SPAN>
							</td>
						</tr>
						<tr style="display: none;" id="rewu">
							<th align="right">
								任务人:
							</th>
							<td>
								部门：
									<SPAN>${workLog.dept}</SPAN>
								姓名：
								<SPAN>${workLog.userName}</SPAN>
								<input type="hidden" name="workLog.code" id="code" value="${workLog.code}" />
								<input type="hidden" name="workLog.cardId" id="cardId" value="${workLog.cardId}" />
								<input type="hidden" name="workLog.userId" id="userId" value="${workLog.userId}" />
								<input type="hidden" name="workLog.userName" id="userName"
									value="${workLog.userName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								任务交期:
							</th>
							<td>
								<s:if test="workLog.yjtime!=null">
									<input type="text" id="zptime" class="Wdate"  name="workLog.zptime" value="${workLog.zptime}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" onchange="jyzptime()">
								</s:if>
								<s:else>
									<SPAN>${workLog.zptime}</SPAN>
								</s:else>
							</td>
						</tr>
						<s:if test="workLog.yjtime!=null">
							<tr>
							<th align="right">
								是否确定:
							</th>
							<td>
								<input type="radio" name="workLog.qrStatus" value="同意" onclick="alert('选中该项之后，规定完成任务日期再不可更改')"/>
								确认
								<input type="radio" name="workLog.qrStatus" value="不同意" checked="checked"/>
								不确认
							</td>
							</tr>
						</s:if>
						<tr>
							<th align="right">
								预计交期:
							</th>
							<td>
								<s:if test="workLog.yjtime!=null">
									<SPAN>${workLog.yjtime}</SPAN>
								</s:if>
								<s:else>
									<input type="text" id="yjtime" class="Wdate"  name="workLog.yjtime" value="${workLog.zptime}"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" >
								</s:else>
							</td>
						</tr>
						<tr>
							<th align="right">
								内容:
							</th>
							<td align="left">
								<textarea rows="6" cols="80" name="workLog.content">${workLog.content}</textarea>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							<input type="hidden" name="pageStatus" value="zhipai"/>
									<input type="hidden" name="workLog.id" value="${workLog.id}" id="worklogid">
								<input type="hidden" name="workLog.zpStatus" value="确认"/>
								<input type="submit" value="确认"
									style="width: 80px; height: 50px" id="submit">
								<input type="reset" value="重置" style="width: 80px; height: 50px">
							</td>
						</tr>
					</table>
				</form>
				<input type="hidden" id="rebeack" value="${successMessage}"/>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
<SCRIPT type="text/javascript">
$(document).ready(function(){
		var rebeack=$("#rebeack").val();
		if(rebeack =="成功!"){
			alert(rebeack);
			parent.chageDiv('none');
			parent.window.location.reload();
		}
	})
function jyzptime(){
	$.ajax({
		type:"POST",
		url:"WorkLogAction!jyzptime.action",
		data:{'workLog.id':$("#worklogid").val(), 'workLog.zptime':$("#wxstarttime").val()},
		datatype:'json',
		success:function(data){
			if(data!=null&&data!=""){
				alert(data);
				$("#zptime").val("");
				$("#zptime").focus();
			}
		}
	})
}
function checkForm(){
	var zptime = document.getElementById("zptime");
	var yjtime = document.getElementById("yjtime");
	if(zptime!=null && zptime.value == ""){
		alert("任务交期不能为空");
		zptime.focus();
		return false;
	}else if(yjtime != null && yjtime.value == ""){
		alert("预计交期不能为空");
		yjtime.focus();
		return false;
	}
	document.getElementById("submit").disabled="disabled";
	return true;
}
</SCRIPT>
		</body>
</html>
<%
	request.getSession().removeAttribute("mfNames");
	request.getSession().removeAttribute("moduleFunction");
%>