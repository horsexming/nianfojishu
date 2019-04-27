<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<%
		Users user = (Users) session.getAttribute("Users");
	%>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h5>
					产品持续改进项目执行单
				</h5>
				<form id=testFrom  action="" method="post"  enctype="multipart/form-data" >
				<table style="width: 98%;" class="table" >
						<tr>
							<th colspan="5"  >
									产品评估
							</th>
							</tr>
							<tr >
							<th rowspan="2" >
								产品信息
								</th>
								<th>
								零件名称/项目名
								</th>
								<th align="left">
									${kvpAssess.part_name}
<%--									<input type="text" name="kvpAssess.part_name"  value="${kvpAssess.part_name}"> --%>
								</th>
								<th>
								零件号/项目号
								</th>
								<th align="left">
									${kvpAssess.part_number}
<%--									<input type="text" name="kvpAssess.part_number" value="${kvpAssess.part_number}"> --%>
								</th>
							</tr>
							<tr >
								<th>
								工序名/项目描述
								</th>
								<th align="left">
									${kvpAssess.process_name}
<%--									<input type="text" name="kvpAssess.process_name" style="width: 500px;" value="${kvpAssess.process_name}">  --%>
								</th>
								<th colspan="2"></th>
							</tr>
							<tr>
								<th id="change" rowspan="5">
									持续改进问题描述
								</th>
							</tr>
							<tr>
								<th colspan="2" >
									改进前问题	 
								</th>
								<th colspan="2" >
									改进后问题	&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
							</tr>
							<tr>
							<th colspan="2"  align="left">
								<div style="position: relative;left: 0px;top: 0px;">
								${kvpAssess.improved_beforeproblems}
										</div>
							</th>
							<th colspan="2"  align="left">
										<div style="position: relative;left: 0px;top: 0px;">
										${kvpAssess.improved_endproblems}
										</div>
							</th>
							</tr>
						<tr>
							<th>
							评估结论
							</th>
							<th colspan="2" >
							是否可以开展持续改进
							</th>
							<th>
							${kvpAssess.assessment_findings}
							</th>
						</tr>
					</table>
					<table style="width: 98%;" class="table" id="complexselectedlist">
						<tbody>
							<tr>
							<th colspan="6"  >
									项目执行
							</th>
							</tr>
							<tr>
								<th colspan="2">
								风险评估单编号
								</th>
							<th align="left">
									<input type="text" readonly="readonly" value="${kvpAssess.kvp_number}">
								</th>
								<th colspan="2">
								项目执行编号
								</th>
								<th align="left">
									<input type="text"  readonly="readonly"  name=""  value="${executeKVP.executeNumber}">
								</th>
							</tr>
							<tr>
							<th rowspan="3" >
								改进部门
								</th>
								<th>
								部门编号
								</th>
								<th align="left">
									<select name="executeKVP.improve_deptNum"  id="deptNum" >
										<option value="${executeKVP.improve_deptNum}">${executeKVP.improve_deptNum}</option>
									</select>
								</th>
								<th rowspan="3" >
								责任部门
								</th>
								<th>
								部门编号
								</th>
								<th align="left">
									<select name="executeKVP.res_deptNum"  id="deptNum1" >
										<option value="${executeKVP.res_deptNum}">${executeKVP.res_deptNum}</option>
									</select>
								</th>
							</tr>
							<tr>
							<th>
								责任员工
								</th>
								<th align="left">
									<select  id="username" >
									<option value="" >--请选择责任员工--</option>
									</select>
										<input type="text" readonly="readonly" name="executeKVP.improve_username" id="name1" value="${executeKVP.improve_username}">
								</th>
							
								<th>
								责任员工
								</th>
								<th align="left">
									<select  id="username1" >
									<option value="">--请选择责任员工--</option>
									</select>
									<input type="text" readonly="readonly" name="executeKVP.res_username" id="name2" value="${executeKVP.res_username}">
								</th>
							</tr>
							<tr>
							<th>
								员工编号
								</th>
								<th align="left">
									<input type="text" name="executeKVP.improve_usercode" readonly="readonly"  id="usercode"  value="${executeKVP.improve_usercode}"> 
								</th>
								<th>
								员工编号
								</th>
								<th align="left">
									<input type="text" name="executeKVP.res_usercode"  id="usercode1"  readonly="readonly" value="${executeKVP.res_usercode}"> 
								</th>
							</tr>
							<tr >
							<th rowspan="2" >
								产品信息
								</th>
								<th>
								零件名称/项目名
								</th>
								<th align="left">
									<input type="text" readonly="readonly" name="kvpAssess.part_name"  value="${kvpAssess.part_name}"> 
								</th>
								<th>
								零件号/项目号
								</th>
								<th align="left">
									<input type="text" readonly="readonly"  name="kvpAssess.part_number" value="${kvpAssess.part_number}"> 
								</th>
								<th>
								</th>
							</tr>
							<tr >
								<th>
								工序名/项目描述
								</th>
								<th colspan="3" align="left">
									<input type="text" readonly="readonly"  name="kvpAssess.process_name" style="width: 500px;" value="${kvpAssess.process_name}">  
								</th>
								<th></th>
							</tr>
							<tr>
								<th id="change" rowspan="3">
									持续改进问题描述
								</th>
							</tr>
							<tr>
								<th colspan="3" >
									改进前问题	 
								</th>
								<th colspan="3" >
									改进后问题	&nbsp;&nbsp;&nbsp;&nbsp;
								</th>
							</tr>
							<tr>
							<th colspan="3" >
								<div style="position: relative;left: 0px;top: 0px;">
												<textarea id="content"   name="executeKVP.improved_beforeproblems" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;">${executeKVP.improved_beforeproblems}</textarea>	
										</div>
							</th>
							<th colspan="3" >
										<div style="position: relative;left: 0px;top: 0px;">
												<textarea id="content1" name="executeKVP.improved_endproblems" cols="10" rows="4"  style="width:350px;height:100px;visibility:hidden;">${executeKVP.improved_endproblems}</textarea>	
										</div>
							</th>
							</tr>
							<tr >
							<th rowspan="2" >
								 成本分析
								</th>
								<th>
								单套周期(天)
								</th>
								<th colspan="2">
								每月单位人工成本(元)
								</th>
								<th>
								单套周期(天)
								</th>
								<th colspan="2">
								每月单位人工成本(元)
								</th>
							</tr>
							<tr>
								<th>
									<input type="text" id="materialcosts" name="executeKVP.materialcosts" value="${executeKVP.materialcosts}">
								</th>
								<th colspan="2">
									<input type="text" id="laborcosts" name="executeKVP.laborcosts" value="${executeKVP.laborcosts}">
								</th>
								<th>
									<input type="text" id="materialcosts1" name="executeKVP.materialcosts1" value="${executeKVP.materialcosts1}">
								</th>
								<th colspan="2">
									<input type="text" id="laborcosts1" name="executeKVP.laborcosts1" value="${executeKVP.laborcosts1}">
								</th>
							</tr>
<%--						<tr>--%>
<%--								<th colspan="3">--%>
<%--									成本结余--%>
<%--								</th>--%>
<%--								<th colspan="3">--%>
<%--									<input type="text" name="executeKVP.costsavings">--%>
<%--								</th>--%>
<%--							</tr>--%>
						<tr id="button1">
								<td colspan="6" align="center">
								<input id="test" type="hidden" value="<s:property value="test"/>">
								<input type="hidden" name="executeKVP.status"  value="${executeKVP.status}"> 
								<input type="hidden" name="executeKVP.id"  value="${executeKVP.id}"> 
									<input id="reviewButton" name="reviewButton" type="button" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
										&nbsp;&nbsp;
										<a href="CircuitRunAction_findAduitPage.action?id=${executeKVP.epId}">审批动态</a>
								</td>
							</tr>
					
						<tbody>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>
	<script type="text/javascript">
$(function() {
	var successMessage = "${successMessage}";
	if (successMessage != "") {
		alert(successMessage);
		parent.location.reload(true);//刷新父页面
	}
})

//查询所有部门编码
$("#deptNum").change(function(){
	var dept = $("#deptNum").val();
	selectDept1(dept);
});
$("#deptNum1").change(function(){
	var dept1 = $("#deptNum1").val();
	selectres_Dept1(dept1);
});

//改进部门
$("#username").change(function(){
	var newnames = $("#username").val();
	 var names= $("#name1").val();
	var i=names.indexOf(newnames);
	if(i==-1){
		if(names==""){
			names=newnames;
		}else{
		names=names+","+newnames;
		}
	}else{
		//第二次点击时移除当前选中的值
		names=names.replace(newnames, "");
		names=names.replace(",,",",");
		var fristname = names.substring(0,1);
		//当下标为1的值是,
		if(fristname==','){
			names = names.substring(1,names.length);
		}
	}
	 $("#name1").val(names);
	selectDept2(names);
});
$("#username1").change(function(){
	var name = $("#username1").val();
	
	var newnames = $("#username1").val()
	 var names= $("#name2").val();
	var i=names.indexOf(newnames);
	if(i==-1){
		if(names==""){
			names=newnames;
		}else{
		names=names+","+newnames;
		}
	}else{
		//第二次点击时移除当前选中的值
		names=names.replace(newnames, "");
		names=names.replace(",,",",");
		var fristname = names.substring(0,1);
		//当下标为1的值是,
		if(fristname==','){
			names = names.substring(1,names.length);
		}
	}
	 $("#name2").val(names);
	selectres_Dept2(names);
});

$(function(){
	selectDept();
})
//下拉所有的部门编码
function selectDept(){
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_selectDept.action", 
							data: "",
							dataType : "json",
							success: function(data){
								$("#deptNum").empty();//清空
								$("#deptNum1").empty();//清空
								$.each(data,function(i,n){
									$("#deptNum").append("<option value='"+n.deptNumber+"' title='"+n.dept+"'>"+n.deptNumber+"</option>");
									$("#deptNum1").append("<option value='"+n.deptNumber+"' title='"+n.dept+"'>"+n.deptNumber+"</option>");
								})
							}
					});
}
//查询部门对应的员工
function selectDept1(dept){
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_selectUser.action", 
							data : {
								"dept":dept
							},
							dataType : "json",
							success: function(data){
								$("#username").empty();//清空
								$.each(data,function(i,n){
									if(i==0){
										$("#username").append("<option value=''>--请选择员工--</option>");
										$("#username").append("<option value='"+n.name+"'>"+n.name+"</option>");
									}else{
									$("#username").append("<option value='"+n.name+"'>"+n.name+"</option>");
									}
								})
							}
					});
}

//查询部门对应的员工
function selectres_Dept1(dept){
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_selectUser.action", 
							data : {
								"dept":dept
							},
							dataType : "json",
							success: function(data){
								$("#username1").empty();//清空
								$.each(data,function(i,n){
									if(i==0){
										$("#username1").append("<option value=''>--请选择员工--</option>");
										$("#username1").append("<option value='"+n.name+"'>"+n.name+"</option>");
									}else{
									$("#username1").append("<option value='"+n.name+"'>"+n.name+"</option>");
									}
								})
							}
					});
}

//显示对应的员工编号
function selectDept2(names){
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_selectUserCode.action", 
							data : {
								"name":names
							},
							dataType : "json",
							success: function(data){
								$("#usercode").attr("value",data);
							}
					});
}
//显示对应的员工编号
function selectres_Dept2(names){
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_selectUserCode.action", 
							data : {
								"name":names
							},
							dataType : "json",
							success: function(data){
								$("#usercode1").attr("value",data);
							}
					});
}


var lineCount = 1;
var begAddLineNum = 1;//物品
var begAddLineNum1 = 8;//议价
var begAddLineNum2 = 10;//公司

function addImprove1(){
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tr.setAttribute('align', 'center');
	_tbody.insertBefore(_tr, uploadtr);
	begAddLineNum++;
	var x = _tr.insertCell(0);
	x.setAttribute('colspan',2)
	x.innerHTML = "<div id='image1_"+lineCount+"'><textarea rows='2' cols='50' name='improveKVPList["+lineCount+"].improved_beforeproblems'></textarea><a onclick='moveTr(this)'>删除</a></div>";
	var x1 = _tr.insertCell(1);
	x1.setAttribute('colspan',2)
	x1.innerHTML = "<div id='image2_"+lineCount+"'><textarea rows='2' cols='50' name='improveKVPList["+lineCount+"].improved_endproblems'></textarea><a onclick='moveTr(this)'>删除</a></div>";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "";
	lineCount++;
	var s=document.getElementById("change").attributes.getNamedItem("rowspan").nodeValue;
	 s++;
	$("#change").attr("rowspan",s);
}
 

//删除
function moveTr(obj) {
	var total = 0;
	var selectedTr = obj.parentNode.parentNode;
	var tbody = selectedTr.parentNode;
	tbody.removeChild(selectedTr);
	var s=document.getElementById("change").attributes.getNamedItem("rowspan").nodeValue;
	s--;
	$("#change").attr("rowspan",s);
}

$(function(){
	var self;
	var self2;
	var self3;
	var self4;
	function submit(){
		self.sync();
		self2.sync();
		var content=$('#content').val();
		var content1=$('#content1').val();
		var test = $("#test").val();
		var isNull=/^[\s]*$/;
		if(isNull.test(content)){
			alert('请填写内容！');
			return;
		}
		$.ajax({
							type: "POST",
							url: "kvpAssessAction_updateExecuteKVP.action",
							data: $('#testFrom').serialize(),
							dataType : "json",
							success: function(data){
								 if(data){
									 alert("修改成功!");
								 	window.location.href="<%=path%>/kvpAssessAction_findKVPAssess.action?test="+test;
								 }else{
									 alert("修改失败!");
								 }
							}
						});
				 
		
		
	}
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '600px',
			height: '200px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload1.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self = this;
			}
		});
	});
	KindEditor.ready(function(K) {
		var editor1 = K.create('#content1', {
			cssPath : '${pageContext.request.contextPath}/javascript/kindeditor-master/plugins/code/prettify.css',
			width : '600px',
			height: '200px',
			minWidth : 400,
			minHeight: 100,
			uploadJson : 'affixAction!upload1.action',
            allowFileManager : false,
			items : [ 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', '|', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold','italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'emoticons', 'image'],
			afterCreate : function() {
				self2 = this;
			}
		});
	});
	
	$('#reviewButton').bind('click', function(){
		var materialcosts = document.getElementById("materialcosts");
		var materialcosts1 = document.getElementById("laborcosts");
		var materialcosts1 = document.getElementById("materialcosts1");
		var laborcosts1 = document.getElementById("laborcosts1");
		if (materialcosts.value == "") {
		alert("改进前单套周期不能为空!");
		materialcosts.focus();
		return false;
		}
		if (laborcosts.value == "") {
		alert("改进前每月单位人工成本不能为空!");
		laborcosts.focus();
		return false;
		}
		if (materialcosts1.value == "") {
		alert("改进后单套周期不能为空!");
		materialcosts1.focus();
		return false;
		}
		if (laborcosts1.value == "") {
		alert("改进后每月单位人工成本不能为空!");
		laborcosts1.focus();
		return false;
		}
		submit();
	});
	
});
</script>

</html>
