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
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/javascript/layer/theme/default/layer.css">
		<%--<script type="text/javascript"--%>
		<%--			src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js">--%>
		<%--			</script>--%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/layer/layer.js">
</script>
<style type="text/css">
	.searchicon{
		background-image: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAOCAYAAAAfSC3RAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAYdEVYdFNvZnR3YXJlAHBhaW50Lm5ldCA0LjAuOWwzfk4AAADESURBVDhPnZLBDcIwDEW7DIt0CmZgBW5tc2AAFugWDNB7FuDMAB0g+FV25AQLJA5Prb797djJUEoJmabptCzLVVjneb4Lo483yYYadqF0bBQkJzJdSJIOT/7RtPuKLmS0yPgCq+zhyGq+NYGU0tkCXvdQVNh68TgmX697JJahEf/uaAEWE80oMbb9OSPJus1jq5zA6SxmR0erJhFGM30h20kOk85WL5wCWogu3N9q3Y3QFM3Xw8D+adUn9QtvfEQJMWV4A2WMBVG/g0M8AAAAAElFTkSuQmCC');
	}
</style>
	</head>
	<body
		onload="createDept('dept','DeptNumberAction!finAllDeptNumberForSetlect.action')">
		<h3></h3>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center">
				<h4>
					${systemFile.id==null?'发起':'修改'}文件受控申请
				</h4>

				<form
					action="systemFileAction_${systemFile.id==null?'addforShenpi':'updateForShenpi'}.action"
					enctype="multipart/form-data" method="post"
					onsubmit="return validate()" id="ztree">
					<table class="table">
						<tr>
							<th align="right">文件申请类别</th>
							<td colspan="3">
								<select id="applyCategory" name="applyCategory">
									<option >新增</option>
									<option >修订</option>
									<option >作废</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件类型
								<input name="tags" value="tags" type="hidden"/>
							</th>
							<td>
								<input type="hidden" name="systemFile.id" id="systemFileId"
									value="${systemFile.id}" />
								<s:if test="tags=='hetong'">
									<select name="systemFile.fileType" onclick="hetong()" id="fileType">
										<option value="合同类">
											合同类
										</option>
									</select>
								</s:if>
								<s:else>
									<select name="systemFile.fileType" id="fileType"
										onclick="hetong()" value="${systemFile.fileType}">
										<option value="${systemFile.fileType}">
											${systemFile.fileType}
										</option>
									</select>
								</s:else>
							</td>
							<th align="right">
								文件等级
							</th>
							<td>
								<select name="systemFile.fileLevel" id="fileLevel">
									<option value="${systemFile.fileLevel}">
										${systemFile.fileLevel}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件名称
							</th>
							<td colspan="1" >
								<input id="fileName" name="systemFile.fileName" value="${systemFile.fileName }"
									style="width:200px;height: 30px;"/>
							</td>
							<s:if test="tags=='hetong'">
								<th align="right" id="jine">
									金额
								</th>
								<td colspan="1">
									<input name="systemFile.money" id="money"
										type="text" value="${systemFile.money}" 
										style="width:200px;height: 30px;"/>
								</td>
							</s:if>
							<s:else>
								<th align="right" >
									原文件版本
								</th>
								<td colspan="1">
									<input type="text" style="width:200px;height: 30px;"
										name="systemFile.banben_old" id="banbenOld" />
									
								</td>
							</s:else>
						</tr>
						<tr>
							<th align="right">
								文件编号
							</th>
							<td>
								<input name="systemFile.fileNo" type="fileNo" id="fileNo"
									value="${systemFile.fileNo}" onkeyup="loadInfoSys()"
									style="width:200px;height: 30px;"/>
							</td>
							<s:if test="tags=='hetong'">
								
								<th align="right">
									项目编号
								</th>
								<td>
									<input type="text" name="systemFile.proCode" id="proCode" 
										style="width:200px;height: 30px;" value="${systemFile.proCode}"/>
								</td>
							</s:if>
							<s:else>
								<th align="right">
									文件版本号
								</th>
								<td>
									<input type="text" name="systemFile.banben" id="banben" 
										style="width:200px;height: 30px;"value="${systemFile.banben}"/>
								</td>
							</s:else>
						</tr>
						<tr>
							<th align="right">
								所属部门
							</th>
							<td colspan="1">
								<SELECT id="dept" class="cxselet" name="systemFile.department">
									<option value="${Users.dept}">
										${Users.dept}
									</option>
								</SELECT>
							</td>
							<th align="right">
								产品编码
							</th>
							<td>
								<input type="text" name="systemFile.cpCode" id="cpCode"
									value="${systemFile.cpCode}"style="width:200px;height: 30px;">
							</td>
						</tr>
						<tr>

							<th align="right">
								${systemFile.id==null?'上传文件':'重新上传'}
							</th>
							<td colspan="3">
								<s:if test="tags=='hetong'">
									<input type='file' name='attachment' multiple="multiple">
								</s:if>
								<s:else>
									<input type="file" name="sys" id="sys" />
								</s:else>
<%--								<s:if test="systemFile.fileUrl!=null">--%>
<%--									<a--%>
<%--										href="FileViewAction.action?FilePath=/upload/file/sysFile/${systemFile.fileUrl}"><FONT--%>
<%--										color="red">${systemFile.fileUrl}</FONT> </a>--%>
<%--									<input type="hidden" value="${systemFile.fileUrl}" id="fileUrl">--%>
<%--									<a href="${pageContext.request.contextPath}/upload/file/sysFile/${systemFile.fileUrl}" style="display:none;" id="download"><FONT--%>
<%--										color="red">下载</FONT> </a>--%>
<%--								</s:if>--%>
									<div id="showFiles">
										<!-- js获取值时使用 -->
										<input type="hidden" id="fileInput" value="${systemFile.fileUrl}">
										<input type="hidden" id="fileNameInput" value="${systemFile.otherName }" />
									</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								描述
							</th>
							<td colspan="3">
								<textarea rows="8" cols="80" name="systemFile.description">${systemFile.description}</textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								文件管控审批人员
							</th>
							<td colspan="3">
								<div id="freeDeptDiv">
									<font color="red">文件管控审批人员:</font>
									<input type="hidden" value="" name="uidsAndLevels" id="uidsAndLevels">
									<hr />
									<div>
										<div >
											<div>
												<div style="float: left; " align="center">
													<div style="float: left; ">
														<input type="text" id="searchDeptInput"style="width: 120px;"
															  placeholder="搜索部门" onkeyup="searchDept()"><br>
														<select id="userGroup" name="" style="width: 120px;" size="15" onchange="changeDept()">
															<option value="">
																选择部门
															</option>
															
														</select>
														<br>
													</div>
													<div style="float: left; ">
														<input type="text" id="searchperson"style="width: 120px;"
															 onkeyup="changeDept()" placeholder="搜索审批人"><br>
														<select id="person" name="" style="width: 120px;" size="15">
															<option>
																选择审批人
															</option>
														</select>
													</div>
												</div>
												<div id="allLevel" style="float: left; width: 50%;" align="left">
												<div>
													<input type="button" value="添加审核等级" class="input"
														style="width: 100px;" onclick="addLevel()" />
													<input type="button" value="删除审核等级" class="input"
														style="width: 100px;" onclick="delLevel()" />
												</div>
												<div id="levelDiv1" >
													<div style="float: left; padding-top: 10px;">
														<input type="button" value="------->"
															onclick="getPerson(1,this)" />
														1级
														<input type="hidden" name="" value="1" />
														<br />
														<input type="button" value="<-------"
															onclick="backPerson(1,this)" />
													</div>
													<div style="float: left;">
														<select id="level1" style="width: 120px;" size="6"></select>
														<span id="addStatus1" style="color: red;"> </span>
													</div>
													<div style="clear: both;"></div>
													<br>
												</div>
											</div>
											<div style="clear: both;"></div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th align="right">
								推送人员
								<br />
								<br />
								<input id="test2" type="button" value="选择推送人员">
							</th>
							<td colspan="3">
								<input id="fid" name="systemFile.personToLookId"
									value="${systemFile.personToLookId}" readonly="readonly"
									type="hidden" />
								<textarea id="tishiPerson" name="systemFile.personToLook"
									rows="5" cols="80" readonly="readonly">${systemFile.personToLook}</textarea>
							</td>
						</tr>
						<td colspan="4" align="center">
							<input id="submitBtn" type="submit" value="提交" class="input">
							<%--							<input id="test1" type="button" value="文件管控审批流程">--%>
						</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	hetong();
	$("#applyCategory").tinyselect();
	var tag = "dengji";
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data : {
			tag : tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.typeName + "'>"
										+ this.typeName + "</option>")
								.appendTo("#fileLevel");
					});
		}
	});
});
$(function() {
	var tag = "leixing";
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data : {
			tag : tag
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.typeName + "'>"
										+ this.typeName + "</option>")
								.appendTo("#fileType");
					});

		}
	});
});

$(function() {
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(function() {
				var html = "";
				if (this.dept == "${Users.dept}") {
					html = "<option selected='selected' value='"
							+ this.id + "'>" + this.dept + "</option>";
				} else {
					html = "<option value='" + this.id + "'>"
							+ this.dept + "</option>";
				}
				$(html).appendTo("#userGroup");
			});
			changeDept();
		}
	});
	
	
	
});

$(function(){
	//显示查看
	var fileInput = $("#fileInput").val();
	if(fileInput!=null && fileInput!=""){
		var str = "<p>历史文件：</p>";
		if("合同类"==$("#fileType").val()){
			var suffix = fileInput.split(",");
			var fileName = $("#fileNameInput").val().split(",");
			for(var i=0;i<suffix.length;i++){
				var subSuffix = suffix[i].substring(suffix[i].indexOf("."));
				str+="<p id='pfile"+i+"'>文件"+(i+1)+"：<a href='${pageContext.request.contextPath}/FileViewAction.action?"+
						"FilePath=/upload/file/sysFile/"+suffix[i]+"'>"+fileName[i]+"</a>&nbsp;&nbsp;&nbsp;"+
						"<input type='hidden' value='"+suffix[i]+"' name='oldFiles'>"+
						"<input type='hidden' value='"+fileName[i]+"' name='oldFileNames'>"+
						"<a href='#' onclick='deleteFile("+i+")' >删除</a></p>";
			}
		}else{
			var suffix = fileInput.substring(fileInput.indexOf("."));
			//alert(suffix);
			str+="<a href='${pageContext.request.contextPath}/FileViewAction.action?FilePath=/upload/file/sysFile/"+fileInput+"'>"+fileInput+"</a><br>";
<%--			if(suffix.indexOf("jpg")<0 && suffix.indexOf("pdf")<0 && suffix.indexOf("jpg")<0){--%>
<%--				str+="&nbsp;<a href='${pageContext.request.contextPath}/upload/file/sysFile/"+fileInput+"'>下载</a>";--%>
<%--			}--%>
		}
		$("#showFiles").append(str);
	}
	
});


function changeDept(){
	var deptId = $("#userGroup").val();
	var searchperson = $("#searchperson").val();
	if (deptId > 0) {
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getusers.action",
			dataType : "json",
			data : {
				id : deptId,
				"variable":searchperson
			},
			async : false, 
			success : function(data) {
				//填充部门信息
				$("#person").empty();
				$("#person").append("<option>选择审批人</option>");
				$(data).each(function() {
					if(this!=null&& this.name!=null && this.name!=""){
						var html = "<option value='" + this.id + "'>"
							+ this.name + "</option>";
						$(html).appendTo("#person");
					}
				});
	
			}
		});
	}
}

//右移
function getPerson(id, obj) {
	var personVal = $("#person option:selected");
	if (personVal.val() > 0) {
		var userId = personVal.val();
		var userName = personVal.text();
		var a = true;
		var levels = $("#level"+id+" option").each(function(){
			var uid = $(this).val();
			if(parseInt(uid)==parseInt(userId)){
				alert("选择审批人重复");
				a = false;
				return false;
			}
		});
		if(a){
			$("#level"+id).append("<option value='"+userId+"'>"+userName+"</option>");
		}
	} else if (personVal.val() == null) {
		alert("请选择人员!");
	} else {
		alert(personVal.val());
	}
}

//左移
function backPerson(id, obj) {
	var levelVal = $("#level" + id);
	if (levelVal.val() > 0) {
		var checkIndex = $("#level" + id).get(0).selectedIndex;//获取Select选择的索引值 
		var so = $("#level" + id + " option:selected");
		$("#person").append(so);
		//选中第一个option
		$("#level" + id).get(0).selectedIndex = 0;
	} else if (levelVal.val() == null) {
		alert("请选择人员!");
	}
}


//添加审核等级
var count = 1;
function addLevel() {
	count++;
	$("<div id='levelDiv"
			+ count
			+ "'>"
			+ "<div style='float: left; padding-top: 10px;'>"
			+ "<input type='button' value='------->' onclick='getPerson("
			+ count
			+ ",this)' /> "
			+ count
			+ "级"
			+ " <input type='hidden' name='' value='"
			+ count
			+ "' /><br />"
			+ "<input type='button' value='<-------' onclick='backPerson("
			+ count
			+ ",this)' />"
			+ "</div>"
			+ "<div style='float: left;'><select id='level"
			+ count
			+ "' style='width: 120px;' size='6'></select><span id='addStatus"
			+ count
			+ "' style='color: red;'></span></div><div style='clear: both;'></div><br /></div>")
	.appendTo("#allLevel");
}

//删除审核等级
function delLevel() {
	if (count == 1) {
		alert("就剩一个了,不能再删了!");
		return false;
	} else {
		//先删除该审核等级里面的人员
		var selectSize = $("#level" + count).get(0).options.length;
		if (selectSize == 0) {
			$("#levelDiv" + count).remove();
			count--;
		} else {
			alert("请先删除审核等级为" + count + "级的人员!");
		}
	}

}
function deleteFile(delId){
	$("#pfile"+delId).remove();
}
function changefreeDept(i) {
	var deptId = $("#zrdept" + i).val();
	if (deptId > 0) {
		$.ajax( {
			type : "post",
			url : "AskForLeaveAction!getDeptUsers.action",
			dataType : "json",
			data : {
				id : deptId
			},
			success : function(data) {
				//填充部门信息
			var selectbox = $("#freeDeptUl" + i + " .tinyselect");
			if (selectbox.length > 1) {
				var len = selectbox.length - 1;
				for ( var n = len; n >= 1; n--) {
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople" + i).empty();
			$(data).each(
					function() {
						var html = "<option value='" + this.userId + "'>"
								+ this.userName + "</option>";
						$(html).appendTo("#zrpeople" + i);
					});
			$("#zrpeople" + i).tinyselect();

		}
		});
	}
}
var deptIndex = 0;
function setDept(i) {
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						var html = "";
						if (this.dept == "${Users.dept}") {
							html = "<option selected='selected' value='"
									+ this.id + "'>" + this.dept + "</option>";
						} else {
							html = "<option value='" + this.id + "'>"
									+ this.dept + "</option>";
						}
						$(html).appendTo("#zrdept" + i);
					});
			changefreeDept(i);
			$("#zrdept" + i).tinyselect();
		}
	});
}
function changefreeDept(i) {
	var deptId = $("#zrdept" + i).val();
	if (deptId > 0) {
		$.ajax( {
			type : "post",
			url : "GzstoreAction_getusers.action",
			dataType : "json",
			data : {
				id : deptId
			},
			success : function(data) {
				//填充部门信息
			var selectbox = $("#freeDeptUl" + i + " .tinyselect");
			if (selectbox.length > 1) {
				var len = selectbox.length - 1;
				for ( var n = len; n >= 1; n--) {
					$(selectbox[n]).remove();
				}
			}
			$("#zrpeople" + i).empty();
			$(data).each(
					function() {
						var html = "<option value='" + this.id + "'>"
								+ this.name + "</option>";
						$(html).appendTo("#zrpeople" + i);
					});
			$("#zrpeople" + i).tinyselect();

		}
		});
	}
}
function addFreeDept() {
	deptIndex++;
	var html = "<ul id='freeDeptUl" + deptIndex + "'>" + "<li id='freeDeptli"
			+ deptIndex + "'>" + "<SELECT id='zrdept" + deptIndex
			+ "' name='approvalId' onchange='changefreeDept(" + deptIndex
			+ ")'></SELECT>" + "<SELECT id='zrpeople" + deptIndex
			+ "' name='ids'></SELECT>"
			+ "<input type='button' value='删除' onclick='deleteFreeDept("
			+ deptIndex + ")' style='width: 60px;height: 30px'>" + "</li></ul>"
	$(html).appendTo("#freeDeptDiv");
	setDept(deptIndex);
}
function deleteFreeDept(index) {
	$("#freeDeptUl" + index).remove();
}<%--	 function showDiv(){--%>
<%--		 document.getElementById("tree").style.display="";--%>
<%--	 }--%>
$('#test2').on('click', function(){
layer.open({
  type: 2,
  title: '选择推送人员',
  area: ['450px', '800px'],
  fixed: false, //不固定
  maxmin: true,
  content: '<%=basePath%>/System/systemfile/checkPerson.jsp'
});
});
$('#test1').on('click', function(){
layer.open({
  type: 2,
  title: '文件管控审批流程',
  area: ['900px', '700px'],
  fixed: false, //不固定
  maxmin: true,
  content: '<%=basePath%>/CircuitCustomize_findAuditNodeByCcId.action?id=632'
});
});
function validate(){
	var applyCategory = $("#applyCategory").val();
	
	if(applyCategory!="作废"){
		if (!validateText("fileType", "文件类型")) {
			return false;
		}
		if (!validateText("fileLevel", "文件等级")) {
			return false;
		}
		if (!validateText("fileName", "文件名称")) {
			return false;
		}
		var fileType = $("#fileType").val();
		if('合同类'==fileType){
			if (!validateText("money", "合同金额")) {
				return false;
			}
		}
	}
	if (!validateText("fileNo", "文件编号")) {
		return false;
	}
	var isSubmit = true;
	var systemfileId = $("#systemFileId").val();
	if(systemfileId==null || systemfileId==""){
		//校验文件编号是否存在
		var fileNo = $("#fileNo").val();
		var banbenOld = $("#banbenOld").val();
		if(applyCategory=="新增"){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/systemFileAction_getSystemFileByFileNo.action",
				dataType:"json",
				data:{
					"systemFile.fileNo":fileNo
				},
				async:false,
				success:function(data){
					if(data!=null){
						alert("该文件编号已存在");
						isSubmit = false;
						return false;
					}
					
				}
			});
		}else if(applyCategory=="修订" || applyCategory=="作废"){
			if (!validateText("banbenOld", "原文件版本")) {
				return false;
			}
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/systemFileAction_getSystemFileByFileNo.action",
				dataType:"json",
				data:{
					"systemFile.fileNo":fileNo,
					"systemFile.banben_old":banbenOld
				},
				async:false,
				success:function(data){
					if(data==null){
						alert("该原文件版本和文件编号不存在");
						isSubmit = false;
						return false;
					}
					
				}
			});
		}
	}
	
	if(!isSubmit){
		return false;
	}
	
	var banben = $("#banben").val();
	if(banben!=null && banben==banbenOld){
		alert("原文件版本和现文件版本不能相同");
		$("#banben").focus();
		return false;
	}
	
	var arr = [];
	var szIndex = 0;
	for(var i=1;i<=count;i++){
		var levels = document.getElementById("level"+i).options;
		if(levels==null|| levels.length==0){
			alert("审核等级："+i+"级为空！");
			return false;
		}else{
			 for (var j = 0; j < levels.length; j++){
			 		arr.push(i+":"+ levels[j].value);
        	 }
		}
		document.getElementById("uidsAndLevels").value = arr;
	}
	
	
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		$("#" + id).focus();
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
function hetong(){
	var fileType = $("#fileType").val();
	if('合同类'==fileType){
		$("#jine").show();
		$("#money").show();
		$("#fileButton_1").show();
		//$("#sys").hide();
	}else{
		$("#jine").hide();
		$("#money").hide();
		$("#fileButton_1").hide();
		//$("#sys").show();
	}
}

function searchDept(){
	var searchDeptInput = $("#searchDeptInput").val();
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		data:{
			"variable":searchDeptInput
		},
		success : function(data) {
			$("#userGroup").empty();
			$("#userGroup").append("<option>搜索部门</option>");
			//填充部门信息
			$(data).each(function() {
				var html = "";
				if (this.dept == "${Users.dept}") {
					html = "<option selected='selected' value='"
							+ this.id + "'>" + this.dept + "</option>";
				} else {
					html = "<option value='" + this.id + "'>"
							+ this.dept + "</option>";
				}
				$(html).appendTo("#userGroup");
			});
			changeDept();
		}
	});
}

//修订时，输入文件编号，自动加载最高版本信息
function loadInfoSys(){
	var applyCategory = $("#applyCategory").val();
	if(applyCategory=="修订" || applyCategory=="作废"){
		var fileNo = $("#fileNo").val();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/systemFileAction_getSystemFileByFileNo.action",
			data:{
				"systemFile.fileNo":fileNo
			},
			dataType:"json",
			success:function(data){
				if(data!=null){
					$("#fileType").val(data.fileType);
					$("#fileLevel").val(data.fileLevel);
					$("#fileName").val(data.fileName);
					$("#banbenOld").val(data.banben);
					$("#dept").val(data.dept);
					$("#cpCode").val(data.cpCode);
					
				}
			}
			
		});
		
		
	}
	
}
	</script>
	</body>
</html>
