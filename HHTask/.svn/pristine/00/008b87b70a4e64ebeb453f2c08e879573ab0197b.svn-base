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
				<h3>
					存取档案申请
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="DanganAction_add.action" method="post"
					enctype="multipart/form-data" onsubmit="return validate()">
					<table class="table" id="tablebod">
						<tbody>
							<tr>
								<th>
									存档室名称
								</th>
								<td align="center">
									<select name="dangAn.cdAceName" id="cdAceName"
										style="width: 156px;"
										onMouseOver="createDept('cdAceName','DanganAction_findSelectName.action')">
										<option value="">
											请选择存档室
										</option>
									</select>
								</td>
								<th>
									存档室门禁编号
								</th>
								<td align="center">
									<select name="dangAn.cdAceNum" id="cdAceNum"
										onclick="daNameNull()" style="width: 156px;">
									</select>
								</td>
								<th>
									申请人手机号
								</th>
								<td align="center">
									<input type="text" name="dangAn.sqTel" id="sqTel" />
								</td>
								<th>
									存档日期
								</th>
								<td align="center" >
									<input class="Wdate" type="text" name="dangAn.shenqingdate"
										id="shenqingdate" value=""
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								</td>
							</tr>
							<tr>
								<th>取档原因：</th>
								<td colspan="7"><input type="text" style="width: 90%" name="dangAn.quDangReason" id="sqTel" /></td>
							</tr>
							<tr>
								<th colspan="8">
									存取档信息
								</th>
							</tr>
							
							<tr>
								<td align="right" colspan="4" style="border-right-width: 0px;">
									<input type="button" id="tijia" onclick="addDangan(this,1)"
										value="添加档案" style="width: 110px; height: 25px;" />
								</td>
								<td align="left" colspan="4" style="border-left-width: 0px;">
									<input type="button" onclick="delFamily(this)" id="shanchu"
										style="display: none; width: 110px; height: 25px;"
										value="删除档案" />
								</td>
							</tr>
							<tr>
								<td colspan="8" align="center">
									<input type="submit" value="申  请(Application)"
										style="width: 130px; height: 40px;" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
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
		if ($("#documentName").val() == null || $("#documentName").val() == "") {
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
