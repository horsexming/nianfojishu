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
						修改量、检具信息</font>
				</p>
				<br />

				<div style="width: 100%;" align="center">
					<div style="width: 60%;">
					<font id="msgfont" color="red" size="5mm"></font>
						<form action="CheckoutAndGagesAction_updatecag.action" method="POST" onsubmit="return check()">
							<table class="table">
								<tr>
									<th align="right">
										名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
									</th>
									<td>
										<input type="text" value="${cag.name}" name="cag.name" id="name">
									</td>
								</tr>
								<tr>
									<th align="right">
										使&nbsp;用&nbsp;状&nbsp;态
									</th>
									<td>
										<s:if test='cag.useStatus == "在用"'>
											<input type="radio" value="在用" name="cag.useStatus"
											id="useStatus1" checked="checked">
											在用
											<input type="radio" value="停用" name="cag.useStatus"
												id="useStatus2">
											停用
										</s:if>
										<s:else>
											<input type="radio" value="在用" name="cag.useStatus"
											id="useStatus1" checked="checked">
											在用
											<input type="radio" value="停用" name="cag.useStatus"
												id="useStatus2" checked="checked">
											停用
										</s:else>
									</td>
								</tr>
								<tr>
									<th align="right">
										责任人工号
									</th>
									<td>
										<input type="text" name="cag.codeLiable" value="${cag.codeLiable}"
											id="codeLiable" onblur="getUsers()" onkeyup="getUsers()" />
									</td>
								</tr>
								<tr>
									<th align="right">
										责任人姓名
									</th>
									<td>
										<input type="text" name="cag.personLiable" value="${cag.personLiable}"
											id="personLiable" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										校&nbsp;验&nbsp;周&nbsp;期
									</th>
									<td>
										<input type="text" name="cag.jyCycle" value="${cag.jyCycle}" id="jyCycle"
											onkeyup="numyanzheng(this,'zhengshu')"
											onblur="numyanzheng(this,'zhengshu')" />(天)
									</td>
								</tr>
							</table>
							<table class="table" id="bybz_table">
								<tr>
									<th>序号</th>
									<th>校验方法</th>
									<th>校验标准</th>
									<td>
										<input type="button" value="添加" onclick="addline()">
										<input type="button" value="删除" onclick="delline()">
										
										<input type="hidden" value="<s:property value="bybzList.size()" />" id="listsize">
									</td>
								</tr>
								<s:iterator value="bybzList" id="pageList" status="pageStatus1">
								<tr align="center">
									<td>
										${pageStatus1.index+1}
										<input type="hidden" value="<s:property value="#pageList.id" />" name="cag.bybzList[${pageStatus1.index}].id">
									</td>
									<td>
										<input type="text" value="<s:property value="#pageList.baoyangMeans" />" name="cag.bybzList[${pageStatus1.index}].baoyangMeans">
									</td>
									<td>
										<input type="text" value="<s:property value="#pageList.baoyangCondition" />" name="cag.bybzList[${pageStatus1.index}].baoyangCondition">
									</td>
									<td>
									</td>
								</tr>
							</s:iterator>
							</table>
							<p>
								<input type="hidden" value="${cag.id}" name="cag.id">
								<input type="submit" value="修改" class="input"
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
