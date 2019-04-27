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
		<script type="text/javascript">
		$(function(){//公章已被借出，请等待归还后再申请！
			alert("公章已被借出，请等待归还后再申请！");
		});
		</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				印章使用申请
			</div>
			<form method="post" id="form" enctype="multipart/form-data">
				<table class="table" style="width: 100%;">
					<tr>
						<td rowspan="">
							使用印章名称
						</td>
						<td colspan="3">
<%--							<input type="radio" value="公章" id="sealName" name="sealLog.name"--%>
<%--								checked="checked">--%>
<%--							公章--%>
<%--							<input type="radio" value="合同章" id="sealName" name="sealLog.name">--%>
<%--							合同章--%>
<%--							<input type="radio" value="发票章" id="sealName" name="sealLog.name">--%>
<%--							发票章--%>
<%--							<input type="radio" value="财务章" id="sealName" name="sealLog.name">--%>
<%--							财务章--%>
<%--							<input type="radio" value="名章" id="sealName" name="sealLog.name">--%>
<%--							名章--%>
								<input type="checkbox" value="合同章" id="sealName"
									name="sealLog.name" />
								合同章
								<input type="checkbox" value="发票章" id="sealName"
									name="sealLog.name" />
								发票章
								<input type="checkbox" value="财务章" id="sealName"
									name="sealLog.name" />
								财务章
								<input type="checkbox" value="名章" id="sealName"
									name="sealLog.name" />
								名章
								<input type="checkbox" value="法人章" id="sealName"
									name="sealLog.name" />
								法人章
								<input type="checkbox" value="机密文件" id="sealName"
									name="sealLog.name" />
								保险柜钥匙
								<input type="checkbox" value="保险柜钥匙" id="sealName"
									name="sealLog.name" />
								机密文件名称:
								<input type="text" id="sealName1" name="sealLog.name1" />
						</td>
					</tr>
					<tr>
						<td>
							是否机密：
						</td>
						<td colspan="3">
							<input type="radio" value="否" id="isConfidential"
								name="sealLog.isConfidential" checked="checked">
							否
							<input type="radio" value="是" id="isConfidential"
								name="sealLog.isConfidential">
							是
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							使用人员
						</td>
						<td>
							工号
						</td>
						<td>
							名称
						</td>
						<td>
							部门
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" id="userCode" name="sealLog.userCode"
								value="${sessionScope.Users.code}"
								onblur="setUser(this.value,'code')">
						</td>
						<td>
							<input type="text" id="userName" name="sealLog.userName"
								value="${sessionScope.Users.name}"
								onblur="setUser(this.value,'name')">
						</td>
						<td>
							<input type="text" id="userDept" name="sealLog.userDept"
								value="${sessionScope.Users.dept}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							使用用途简述
						</td>
						<td rowspan="2" colspan="2">
							<textarea id="useFor" cols="50px" name="sealLog.useFor"></textarea>
						</td>
						<td>
							上传附件
						</td>
					</tr>
					<tr>
						<td>
							<input type="file" id="file" name="fujian">
						</td>
					</tr>
					<tr>
						<td>
							是否需要存档
						</td>
						<td>
							是
							<input type="radio" id="isSave" name="sealLog.isSave" value="yes"
								checked="checked" onchange="changeSaveTr(this.value)" />
						</td>
						<%--			 <td>否<input type="radio" id="isSave" name="sealLog.isSave" value="no" onchange="changeSaveTr(this.value)"/></td>--%>
						<td>
							存档类型： 
							<SELECT name="sealLog.cunType" id="cunType">
								<option value="">--请选择存档类型--</option>
								<option value="电子档">电子档</option>
								<option value="原件">原件</option>
								<option value="复印件">复印件</option>
							</SELECT>
						</td>
					</tr>
					<tr id="needSave1">
						<td rowspan="4">
							存档资料
						</td>
						<td>
							文件名称
						</td>
						<td>
							类别
						</td>
						<td>
							预计存档日期
						</td>
					</tr>
					<tr id="needSave2">
						<td>
							<input type="text" name="sealLog.documentName" id="documentName">
						</td>
						<td>
							<SELECT id="documentType" name="sealLog.documentType">
								<option value="销售">
									销售
								</option>
								<option value="订单">
									订单
								</option>
								<option value="采购合同">
									采购合同
								</option>
								<option value="人事合同">
									人事合同
								</option>
								<option value="其他">
									其他
								</option>
							</SELECT>
						</td>
						<td>
							<input type="text" class="Wdate" name="sealLog.retime"
								id="retime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr id="needSave3">
						<td>
							负责人
						</td>
						<td>
							录入人
						</td>
						<td>
							档案失效日期
						</td>
					</tr>
					<tr id="needSave4">
						<td>
							<input type="text" name="sealLog.chargePerson" id="chargePerson">
						</td>
						<td>
							<input type="text" name="sealLog.inputPeople" id="inputPeople"
								value="${sessionScope.Users.name}" readonly="readonly">
						</td>
						<td>
							<input type="text" class="Wdate" name="sealLog.retime"
								id="retime"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<input type="button" value="提交" class="input"
								onclick="tosubmit()">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript"
			src="<%=path%>/javascript/ajaxfileupload/ajaxfileupload.js">
</script>
		<script type="text/javascript">
function tosubmit() {
	var sealNames = document.getElementsByName("sealLog.name");//印章名称
	var isConfidentials = document.getElementsByName("sealLog.isConfidential");//是否机密
	var userName = $("#userName").val();//申请人名字
	var userCode = $("#userCode").val();//申请人工号
	//var userTel = $("#userTel").val();//申请人手机 
	var useFor = $("#useFor").val();//用途
	var isSaves = document.getElementsByName("sealLog.isSave");//是否存档（yes,no）
	var sealName = "";
	var isConfidential = "";
	var isSave = "";
	var file = $("#file").val();
	if (file == null || file == "") {
		alert("请上传附件");
		return;
	}
	for ( var i = 0; i < sealNames.length; i++) {
		if (sealNames[i].checked == true) {
			//sealName=sealNames[i].value; 单选框时使用;
			sealName +=sealNames[i].value+",";
		}
	}
	for ( var i = 0; i < isConfidentials.length; i++) {
		if (isConfidentials[i].checked == true) {
			isConfidential = isConfidentials[i].value;
			break;
		}
	}
	for ( var i = 0; i < isSaves.length; i++) {
		if (isSaves[i].checked == true) {
			isSave = isSaves[i].value;
		}
	}
	
	//if(gongz){
	//	if (userTel == null || userTel == "") {
	//		alert("请输入手机号码");
	//		return;
	//	}else{
	//		var re = /^1\d{10}$/
	//	    if (!re.test(userTel)) {
	//	        alert("请输入正确的手机号");
	//	        return;
	//	    }
	//	}
	//}
	if (sealName == null || sealName == "") {
		alert("请选择印章名称");
		return;
	} else if (userCode == null || userCode == "") {
		alert("请输入申请人工号");
		return;
	} else if (userName == null || userName == "") {
		alert("请输入申请人名字");
		return;
	} else if (useFor == null || useFor == "") {
		alert("请输入申请通途");
		return;
	}
	if (isSave == 'yes') {
		if ($("#cunType").val() == null || $("#cunType").val() == "") {
			alert("请选择存档类型!");
			return;
		}else if ($("#documentName").val() == null || $("#documentName").val() == "") {
			alert("请输入文件名称!");
			return;
		} else if ($("#documentType").val() == null
				|| $("#documentType").val() == "") {
			alert("请输选择文档类型!");
			return;
		} else if ($("#retime").val() == null || $("#retime").val() == "") {
			alert("请输入预存档时间!");
			return;
		} else if ($("#chargePerson").val() == null
				|| $("#chargePerson").val() == "") {
			alert("请输入负责人!");
			return;
		}
	}
	

	$
			.ajaxFileUpload( {
				url : 'sealLogAction_add.action',//用于文件上传的服务器端请求地址
				type : 'post',
				secureuri : false,//一般设置为false
				fileElementId : 'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
				dataType : 'json',//返回值类型 一般设置为json
				data : {
					'sealLog.name' : sealName,
					'sealLog.userName' : userName,
					'sealLog.userCode' : userCode,
					'sealLog.userDept' : $("#userDept").val(),
					'sealLog.useFor' : useFor,
					'sealLog.isSave' : isSave,
					'sealLog.cunType' : $("#cunType").val(),
					'sealLog.documentName' : $("#documentName").val(),
					'sealLog.documentType' : $("#documentType").val(),
					'sealLog.retime' : $("#retime").val(),
					'sealLog.chargePerson' : $("#chargePerson").val(),
					'sealLog.isConfidential' : isConfidential,
					'sealLog.inputPeople' : $("#inputPeople").val()
				},
				success : function(data, status) //服务器成功响应处理函数
				{
					if (data) {
						alert("申请成功!");
						window.location.href = "sealLogAction_showList.action?pageStatus=single";
					} else {
						alert("申请失败!");
					}
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					alert(e);
				}
			})
}
function setUser(value, type) {
	if (value == null || value == "") {
		$("#userName").val();
		$("#userCode").val();
		$("#userDept").val();
		return;
	}
	var url = "sealLogAction_getUser.action?";
	if (type == 'code') {
		url = url + "sealLog.userCode=" + value
	} else {
		url = url + "sealLog.userName=" + value
	}
	$.ajax( {
		url : url,
		type : 'post',
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$("#userName").val(data.data.name);
				$("#userCode").val(data.data.code);
				$("#userDept").val(data.data.dept);
			} else {
				$("#userName").val();
				$("#userCode").val();
				$("#userDept").val();
			}

		}
	})

}
function changeSaveTr(value) {
	if (value == 'yes') {
		$("#needSave1").show();
		$("#needSave2").show();
		$("#needSave3").show();
		$("#needSave4").show();
	} else if (value == 'no') {
		$("#needSave1").hide();
		$("#needSave2").hide();
		$("#needSave3").hide();
		$("#needSave4").hide();
	}

}
$("#userTel").keyup(function() {
	var tmptxt = $(this).val();
	$(this).val(tmptxt.replace(/\D|^0/g, ''));
})
</script>
	</body>
</html>
