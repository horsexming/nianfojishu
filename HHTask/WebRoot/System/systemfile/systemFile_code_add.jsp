<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

				<form  method="post"  id="applyFrom">
					<table class="table">
						<tr>
							<th align="right" width="20%">
								文件来源
							</th>
							<td width="30%">
								<input type="hidden" name="systemFile.id" id="sourceInfo"
									value="${systemFile.id}" />
								<select name="systemFile.source" id="fileSource" onchange="judgeSource(this)"
									 value="${systemFile.source}" style="width:175px;">
									<option value="${systemFile.source}">
										${systemFile.source}
									</option>
								</select>
							</td>
							<th align="right" width="20%">
								文件类别
							</th>
							<td width="30%">
								<select name="systemFile.category" id="fileCategory"
									onclick="hetong()" value="${systemFile.category}">
									<option value="${systemFile.category}">
										${systemFile.category}
									</option>
								</select>
							</td>
							
						</tr>
						<tr>
							<th align="right">
								文件名称
							</th>
							<td colspan="1">
								<input id="fileName" name="systemFile.fileName" value="${systemFile.fileName}" style="width:200px;height: 30px;"/>
							</td>
							<th align="right">
								文件等级
							</th>
							<td>
								<select name="systemFile.fileLevel" id="fileLevel" style="width:200px;height: 30px;">
									<option value="${systemFile.fileLevel}">
										${systemFile.fileLevel}
									</option>
								</select>
							</td>
<%--							<th align="right" id="jine" style="display: none;">--%>
<%--								金额--%>
<%--							</th>--%>
<%--							<td colspan="1">--%>
<%--								<input name="systemFile.money" id="money" style="display: none;"--%>
<%--									type="fileNo" value="${systemFile.money}" />--%>
<%--							</td>--%>
						</tr>
						<tr>
							<th align="right">
								文件编号
							</th>
							<td>
								<input name="systemFile.fileNo" type="fileNo" id="fileNo"
									value="${systemFile.fileNo}" readonly="readonly" style="width:200px;height: 30px;"/>
							</td>
							<th align="right">
								文件版本号
							</th>
							<td>
								<input type="text" name="systemFile.banben" id="banben" readonly="readonly" style="width:200px;height: 30px;"/>
							</td>
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
							<th align="right" class="cpCodeTd">
								项目编号
							</th>
							<td class="cpCodeTd">
								<input type="text" id="proCode" name="systemFile.proCode" style="width:200px;height: 30px;">
							</td>
						</tr>
						<tr>
							<th align="right">
								文件描述
							</th>
							<td colspan="3">
								<textarea rows="6" cols="120" name="systemFile.description">${systemFile.description}</textarea>
							</td>
						</tr>
						<tr >
							<td colspan="4" id="appendTd" align="center">
								
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center" id="buttonTd">
								<input id="generatorCodeBtn" type="button" value="生成编号" class="input" onclick="generatorCode()">
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
	$(".cpCodeTd").hide();
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data : {
			tag : "laiyuan"
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.typeName + "'>"
										+ this.typeName + "</option>")
								.appendTo("#fileSource");
					});
			$("#fileSource").tinyselect();

		}
	});
	
	$.ajax( {
		type : "post",
		url : "systemFileAction_findall.action",
		data : {
			tag : "category"
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$(
								"<option value='" + this.code + "'>"
										+ this.typeName + "</option>")
								.appendTo("#fileCategory");
					});
			$("#fileCategory").tinyselect();
		}
	});
	
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
			$("#fileLevel").tinyselect();
		}
	});
	
	
	//显示项目信息
	/*$.ajax( {
		type : "POST",
		url : "QuotedPrice_getQuotedPriceByCon.action",
		dataType : "json",
		async : false,
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj.quotedNumber + "'>"+obj.quotedNumber+ "</option>";
			});
			$(bj).appendTo("#cpCode");
			$("#cpCode").tinyselect();
		}
	});*/
});

$(function() {
	var i = 0;
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
});
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
	if(!validateText("fileSource","文件来源")){
		return false;
	}
	if (!validateText("fileCategory", "文件类别")) {
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
	var sourceInfo = $("#sourceInfo").val();
	if("项目"==sourceInfo){
		if (!validateText("proCode", "项目编号")) {
			return false;
		}
	}
	return true;
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
	}else{
		$("#jine").hide();
		$("#money").hide();
	}
}


function judgeSource(obj){
	var source = obj.value;
	if(source !="" && source=="项目"){
		$(".cpCodeTd").show();
	}else{
		$(".cpCodeTd").hide();
	}
}
//生成并保存编号
function generatorCode(){
	//var sourceInfo = $("#sourceInfo").val();//文件来源
	//var fileCategory = $("#fileCategory").val();//文件类别
	if(!validate()){
		return ;		
	}
	var form = new FormData(document.getElementById("applyFrom"));
   	$.ajax({
             url:"${pageContext.request.contextPath}/systemFileAction_generatorFileNoAndSave.action",
             type:"post",
             data:form,
             processData:false,
             contentType:false,
             async : false, 
             success:function(data){
            	 var object = eval('('+data+')');


            	 $("#appendTd").children().filter("font").remove();
            	 if(object.success){
	            	$("#fileNo").val(object.data.fileNo);
	           		$("#banben").val(object.data.banben);
	           		$("#appendTd").append("<font style='font-size:18px;color:red'>编号生成成功</font><br>");
	           		$("#appendTd").append("<font style='font-size:18px;color:red'>文件编号：《"+object.data.fileNo+"》，版本号：《"+object.data.banben+"》</font><br>");
	           		$("#appendTd").append("<font style='font-size:18px;color:red'>请保存后为文件添加文件和版本号</font>");
	           		//alert("编号生成成功，请为文件添加文件和版本号");
	           		
	           		$("#generatorCodeBtn").hide();
	
	           		$("#buttonTd").append("<input type='button' value='保存' class='input' onclick='saveCode()'>");
	           		$("#buttonTd").append("<input type='button' value='提交文件' class='input' onclick='toSubmitFile()'>");
	           		$("#buttonTd").append("<input type='button' value='取消' class='input' onclick='cancalCode()'>");
            	 }else{
            		$("#appendTd").append("<font style='font-size:18px;color:red'>"+object.message+"</font>");
            	 }
            	
             },error:function(err){
           	  	alert(err);
             }
   	});
	
	
}

function saveCode(){
	var form = new FormData(document.getElementById("applyFrom"));
   	$.ajax({
	    url:"${pageContext.request.contextPath}/systemFileAction_saveOrCancalCode.action?tags=save",
	    type:"post",
	    data:form,
	    processData:false,
	    contentType:false,
	    async : false, 
	    success:function(data){
	   	 	alert(data);
        },error:function(err){
        	alert(err);
        }
    });
}

function cancalCode(){
	 $("#appendTd").text('');
	 $("#buttonTd").html('<input id="generatorCodeBtn" type="button" value="生成编号" class="input" onclick="generatorCode()">');
	//$("#appendTd").append("<font style='font-size:18px;color:red'>"+object.message+"</font>");
	/*var form = new FormData(document.getElementById("applyFrom"));
   	$.ajax({
	    url:"${pageContext.request.contextPath}/systemFileAction_saveOrCancalCode.action",
	    type:"post",
	    data:{
	    	"systemFile":form,
	    	"tags":"cancal"
	    },
	    processData:false,
	    contentType:false,
	    async : false, 
	    success:function(data){
	   	 	alert(data);
        },error:function(err){
        	alert(err);
        }
    });*/
}

function toSubmitFile(){
	
	//提交后获得ID
	var form = new FormData(document.getElementById("applyFrom"));
   	$.ajax({
	    url:"${pageContext.request.contextPath}/systemFileAction_toSubmitUnionSave.action",
	    type:"post",
	    data:form,
	    processData:false,
	    contentType:false,
	    async : false, 
	    success:function(data){
	    	var object = eval('('+data+')');
	    	if(object.success){
	    		var id = parseInt(object.message);
	    		location.href="${pageContext.request.contextPath}/systemFileAction_"+
	    		"getSystemFileDetail.action?tag=jsb&id="+id;
	    	}else{
		   	 	alert(object.message);
	    	}
        },error:function(err){
        	alert(err);
        }
    });
	
}
	</script>
	</body>
</html>
