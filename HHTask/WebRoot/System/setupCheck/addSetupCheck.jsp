<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body >
<h3></h3>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng">
		
		<div align="center">
		<h4>${setupCheck.id==null?'添加':'修改'}体系检验</h4>
			<form action="SetupCheckAction_${setupCheck.id==null?'add':'update'}.action"  method="post" onsubmit="return validate()"  enctype="multipart/form-data">
				<table class="table">
					<tr>
					  <th>审核方
					  </th>
					  <td>
					 <input type="hidden" name="tag" value="${tag}" id="tag"/>
					  <input type="hidden" name="setupCheck.id"
								value="${setupCheck.id}" id="id"/>
					  	<select name="setupCheck.auditParty" id="auditParty">
					  		<option value="${setupCheck.auditParty}">${setupCheck.auditParty}</option>
    						<option value="内部审核">内部审核</option>
							<option value="内部稽查">内部稽查</option>
							<option value="第三方审核">第三方审核</option>
							<option value="客户审核">客户审核</option>
							<option value="客户稽查">客户稽查</option>
    					</select>
					  </td>
					  <th>审核类别
					  </th>
					  <td>
					  	<select name="setupCheck.checkType" id="checkType" >
					  		<option value="${setupCheck.checkType}">${setupCheck.checkType}</option>
    						<option value="QSA">QSA</option>
							<option value="QPA">QPA</option>
							<option value="CSR">CSR</option>
							<option value="OHSAS">OHSAS</option>
							<option value="三防">三防</option>
							<option value="三标联合">三标联合</option>
							<option value="其他审核">其他审核</option>
							<option value="三按两遵守">三按两遵守</option>
							<option value="5S稽查">5S稽查</option>
							<option value="错混料稽查">错混料稽查</option>
							<option value="直发直提稽查">直发直提稽查</option>
							<option value="PCN稽查">PCN稽查</option>
							<option value="ECN稽查">ECN稽查</option>
    					</select>
					  </td>
					</tr>
					<tr>
					  <th>审核日期
					  </th>
					  <td>
					  	<input name="setupCheck.checkDate"  value="${setupCheck.checkDate}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
					  </td>
					  <th>完成日期
					  </th>
					  <td>
					  	<input name="setupCheck.allTime"  value="${setupCheck.allTime}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"/>
					  </td>
					</tr>
					<tr>
						<th>负责人部门</th>
						<td colspan="3">
							<input id ="department" name="setupCheck.department" value="${setupCheck.department}" style="border:none" readonly="readonly" placeholder="请在下面选择总负责人..."/>
						</td>
					</tr>
					<tr>
						<td colspan="4" bgcolor="red" align="center">选择总负责人</td>
					</tr>
					<tr>
						<th>总负责人</th>
						<td colspan="3">
							<input id ="fid" name="setupCheck.allId" value="${setupCheck.allId}" readonly="readonly" type="hidden"/>
							<input id ="uploadPerson" name="setupCheck.uploadPerson" value="${setupCheck.uploadPerson}" readonly="readonly" style="border:none" placeholder="请在下面选择第二负责人..."/>
						</td>
					</tr>
					<tr>
					  <th>所属部门
					  </th>
					  <td>
					  	<SELECT id="deptname" name="department" onchange="userlist(0)">
					  	<option>${setupCheck.department}</option>
					  	</SELECT>
					  </td>
					  <th>总负责人
					  </th>
					  <td id="userstd">
					 	<SELECT id="pname" style="width: 155px;" class="cxselect"  onchange="tianjia()">
					 	<option value="${setupCheck.allId}">${setupCheck.uploadPerson}</option>
					  	</SELECT>
					  </td>
					</tr>
					<tr>
						<td colspan="4" bgcolor="red" align="center">选择第二负责人</td>
					</tr>
					<tr>
						<th>第二负责人</th>
						<td colspan="3">
							<input id ="fid1" name="setupCheck.allId1" value="${setupCheck.allId1}" type="hidden"/>
							<input id ="uploadPerson1" name="setupCheck.uploadPerson1" value="${setupCheck.uploadPerson1}" readonly="readonly" style="border:none" placeholder="请在下面选择第二负责人..."/>
						</td>
					</tr>
					<tr>
					  <th>所属部门
					  </th>
					  <td>
					  	<SELECT id="deptname1" name="department1" onchange="userlist1(0)">
					  	<option>${setupCheck.department}</option>
					  	</SELECT>
					  </td>
					  <th>第二负责人
					  </th>
					  <td id="userstd1">
					 	<SELECT id="pname1" style="width: 155px;" class="cxselect"  onchange="tianjia1()">
					 	<option value="${setupCheck.allId1}">${setupCheck.uploadPerson1}</option>
					  	</SELECT>
					  </td>
					</tr>
					<tr>
					    <th>问题描述
					    </th>
					    <td colspan="3">
					    <textarea rows="8" cols="80" name="setupCheck.description">${setupCheck.description}</textarea>
					    </td>
					</tr>
					<tr>
					<th>上传文件</th>
					<td colspan="3">
					<s:if test="setupCheck.shortFile!=null"><FONT color="red">已有文件</FONT></s:if>
					<br/>
						<input type="file" name="shortFile" id="shortFile"/>
					</td>
					</tr>
					 <td colspan="4" align="center">
					   <input type="submit" value="提交">
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
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id +"_"+this.dept+ "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});
			
			$("#deptname").tinyselect();
		}
	});
});
  $(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id +"_"+this.dept+ "'>" + this.dept
										+ "</option>").appendTo("#deptname1");
					});
			
			$("#deptname1").tinyselect();
		}
	});
});
  function tianjia(){
	  var arrays1 =  $("#pname").val();
	  var department =$("#department").val();
	  var departments = department.split(";");
	  var values1 = arrays1.split("_");
	  if(values1.length==2){
		  var fid1=$("#fid").val();
		  var uploadPerson1=$("#uploadPerson").val();
		  var uploadPerson1s=uploadPerson1.split(";");
		  var deptname = $("#deptname").val();
		  var deptnames = deptname.split("_");
			  if(uploadPerson1s!=null&&uploadPerson1s!=""){
				   for(var i=0;i<uploadPerson1s.length;i++){
				 	 if(uploadPerson1s[i]!=values1[1]){
					   $("#fid").val(fid1+values1[0]+";");
		  			   $("#uploadPerson").val(uploadPerson1+values1[1]+";");
				  	}else{
					  alert("选择过该负责人！");
					  return;
				  }
				}
			  }else{
				 $("#fid").val(fid1+values1[0]+";");
		  		$("#uploadPerson").val(uploadPerson1+values1[1]+";");
		  	  }
			  if(deptnames.length==2){
			  if(departments!=null&&departments!=""){
			  for(var j=0;j<departments.length;j++){
				  if(departments[j]!=deptnames[1]){
					  $("#department").val(department+deptnames[1]+";"); 
				  }else{
					  return;
				  }
			  }
			  }else{
				$("#department").val(department+deptnames[1]+";");   
			  }
			}
		  
	  }
  }
    function tianjia1(){
	  var arrays1 =  $("#pname1").val();
	  var department =$("#department").val();
	  var departments = department.split(";");
	  var values1 = arrays1.split("_");
	  if(values1.length==2){
		  var fid1=$("#fid1").val();
		  var uploadPerson1=$("#uploadPerson1").val();
		  var uploadPerson1s=uploadPerson1.split(";");
		  var deptname = $("#deptname1").val();
		  var deptnames = deptname.split("_");
			  if(uploadPerson1s!=null&&uploadPerson1s!=""){
				   for(var i=0;i<uploadPerson1s.length-1;i++){
				 	 if(uploadPerson1s[i]!=values1[1]){
					   $("#fid1").val(fid1+values1[0]+";");
		  			   $("#uploadPerson1").val(uploadPerson1+values1[1]+";");
				  	}else{
					  alert("选择过该负责人！");
					  return;
				  }
				}
			  }else{
				 $("#fid1").val(fid1+values1[0]+";");
		  		$("#uploadPerson1").val(uploadPerson1+values1[1]+";");
		  	  }
			  if(deptnames.length==2){
			  if(departments!=null&&departments!=""){
			  for(var j=0;j<departments.length-1;j++){
				  if(departments[j]!=deptnames[1]){
					  $("#department").val(department+deptnames[1]+";"); 
				  }else{
					  return;
				  }
			  }
			  }else{
				$("#department").val(department+deptnames[1]+";");   
			  }
			}
		  
	  }
  }
  function userlist1(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var arrays = $("#deptname1").val();
	var values = arrays.split("_");
	if(values.length==2){
		var deptid = values[0];
	}
	if (deptid == "") {
		$("#pname1").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#pname1");
		var tinyselect = $(".tinyselect");
		$("#pname1").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#pname1").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#pname1");

						}
						$(data).each(
								function() {
									$(
											"<option value='" + this.code+"_"+ this.name+ "'>"
													+ this.name + "</option>")
											.appendTo("#pname1");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[2] != null) {
							var ip =tinyselect[3];
							if(ip!=null){
							document.getElementById("userstd1").removeChild(ip);
							}
						}
						$("#pname1").tinyselect();

					}
				});
	}

}
 function userlist(flag) {//flag0表示是点击部门的时候flag1表示页面刷新的时候
	var arrays = $("#deptname").val();
	var values = arrays.split("_");
	if(values.length==2){
		var deptid = values[0];
		//$("#dname").val(values[1]);
	}
	if (deptid == "") {
		$("#pname").empty();
		$("<option value='0'>请先选择部门</option>").appendTo("#pname");
		var tinyselect = $(".tinyselect");
		if (tinyselect[1] != null) {
			document.getElementById("userstd").removeChild(tinyselect[1]);
		}
		$("#pname").tinyselect();
	} else {
		$
				.ajax( {
					type : "post",
					url : "GzstoreAction_getusers.action",
					data : {
						id : deptid
					},
					dataType : "json",
					success : function(data) {
						if (flag == 0) {
							$("#pname").empty();
							$("<option value='0'>请选择人员</option>").appendTo(
									"#pname");

						}
						$(data).each(
								function() {
									$(
											"<option value='" + this.code+"_"+ this.name+ "'>"
													+ this.name + "</option>")
											.appendTo("#pname");
								});
						var tinyselect = $(".tinyselect");
						if (tinyselect[0] != null) {
							var ip =tinyselect[1];
							if(ip!=null){
								document.getElementById("userstd").removeChild(ip);
							}
						}
						$("#pname").tinyselect();

					}
				});
	}

}
	</script>
</body>
</html>

