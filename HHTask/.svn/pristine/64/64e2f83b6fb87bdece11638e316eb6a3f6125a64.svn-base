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
			<div align="center">
				<br />
				<p>
					<font style="width: 10px; height: 12px;" size="5px;">
						校验量、检具</font>
				</p>
				<br />

				<div style="width: 100%;" align="center">
					<div style="width: 60%;">
					<font id="msgfont" color="red" size="5mm"></font>
						<form action="CheckoutAndGagesAction_jYCag.action" method="POST" onsubmit="return check()" >
							<table class="table">
								<tr>
									<th align="right">
										名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
									</th>
									<td>
										<input type="text" value="${cag.name}" name="lcr.name" id="name">
									</td>
								</tr>
								<tr>
									<th align="right">
										校验报告
									</th>
									<td>
										<input type="file" value="" name="attachment" />
									</td>
								</tr>
							</table>
							<table class="table" id="bybz_table">
								<tr>
									<th>序号</th>
									<th>校验方法</th>
									<th>校验标准</th>
									<th>校验结果</th>
									<th>备注<input type="hidden" value="<s:property value="bybzList.size()" />" id="listsize"></th>
								</tr>
								<s:iterator value="bybzList" id="pageList" status="pageStatus1">
								<tr align="center">
									<td>
										${pageStatus1.index+1}
									</td>
									<td>
										<input type="text" value="<s:property value="#pageList.baoyangMeans" />" name="lcr.ljuCRMList[${pageStatus1.index}].jymeans" readonly="readonly">
									</td>
									<td>
										<input type="text" value="<s:property value="#pageList.baoyangCondition" />" name="lcr.ljuCRMList[${pageStatus1.index}].checks" readonly="readonly">
									</td>
									<td>
										<input type="text" value="" name="lcr.ljuCRMList[${pageStatus1.index}].jyresult">
									</td>
									<td>
										<input type="text" value="" name="lcr.ljuCRMList[${pageStatus1.index}].remarks">
									</td>
								</tr>
							</s:iterator>
							</table>
							<p>
								<input type="hidden" value="${cag.id}" name="lcr.cagId">
								<input type="submit" value="校验" class="input"
											onclick="todisabled(this)">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</p>
										
						</form>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function getUsers(){
		var code = $("#codeLiable").val();
		if(code!=""){
				$.ajax( {
		type : "POST",
		url : "CheckoutAndGagesAction_findUsersByCode.action",
		data : {
			code:code		
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				if(data.onWork == '离职'){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"已离职，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else if(data.internal == "否"){
					$("#msgfont").html("工号:"+code+"的员工:"+data.name+"不为内部员工，请从新输入责任人工号!");
					$("#codeLiable").val("");
				}else{
					$("#msgfont").html("");
					$("#personLiable").val(data.name);
				}
			}else{
				$("#msgfont").html(code+"不是内部工号，请重新输入。");
			}
		}
	})
		}
		
	}
function check(){
	var cagname = $("#name").val();
	var code = $("#codeLiable").val();
	var jyCycle = $("#jyCycle").val();
	if(cagname == ""){
		$("#msgfont").html('请填写量、检具名称');
		$("#cagname").focus();
		return false;
	}else if(code == ""){
		$("#msgfont").html('请填写责任人工号');
		$("#code").focus();
		return false;
	}else if(jyCycle == ""){
		$("#msgfont").html('请填校验周期');
		$("#jyCycle").focus();
		return false;
	}
	$("#msgfont").html('');
	return true;
}

var index = parseInt($("#listsize").val());
function addline(){
	var newline = '<tr align="center"><td>'+(index+1)+'</td>' +
					'<td><input type="text" name="cag.bybzList['+index+'].baoyangMeans" ></td>' +
					'<td><input type="text" name="cag.bybzList['+index+'].baoyangCondition" ></td><td></td></tr>' 
	$("#bybz_table").append(newline);
	index++;
}
function delline(){
	var num = $("#list_size").val();
	var n = $('#bybz_table tr').length;
	if(index<1){
		alert("已经没有了保养项了，不能再删了。");
		return;
	}
	$($('#bybz_table tr')[n-1]).remove();
	index--;
	
}
</SCRIPT>
	</body>
</html>
