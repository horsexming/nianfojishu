<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Conditioning_showDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%@include file="/util/sonHead.jsp"%>
<style type="text/css">
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}


</style>
  </head>
  
  <body>
  <%@include file="/util/sonTop.jsp"%>
  <form action="ConditioningAction!uadateConditioning.action" method="post" enctype="multipart/form-data" onsubmit="return validate()">
  <table class="table">
  <tr>
  <td>id</td>
   <td ><input type="text" value="${conditioning.id}"  name="conditioning.id" id="id" readonly="readonly" /> </td>
  </tr>
  <tr>
  <td>控制器ip</td>
  <td><input type="text" value="${conditioning.conditioningip}"   name="conditioning.conditioningip" id="conditioningip" readonly="readonly"/> </td>
  </tr>
  <tr>
  <td>办公区域</td>
  <td><input  type="text" value="${conditioning.region}" name="conditioning.region"   id="region" readonly="readonly"/> </td>
  </tr>
  <tr>
  <td>绑定人员</td>

						<td colspan="2">
								<div id="freeDeptDiv">
									<input type="hidden" value="" name="conditioning.userid" id="userid">
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
			</div>
			</td>
   </tr>
   
  <tr>
  <td colspan="2" align="left"><input type="submit" class="bangdin" value="绑定人员" ></td>
  </tr>
  </table>
  </form>
  	<%@include file="/util/foot.jsp"%>
  <script type="text/javascript">
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
 


			function validate() {



		
		var arr = [];
		var szIndex = 0;
		for(var i=1;i<=count;i++){
			var levels = document.getElementById("level"+i).options;
			if(levels==null|| levels.length==0){
				alert("审核等级："+i+"级为空！");
				return false;
			}else{
				 for (var j = 0; j < levels.length; j++){
				 		arr.push(levels[j].value);
	        	 }
			}
			document.getElementById("userid").value = arr;
		}
		

		if(!validateText("userid", "办公人员")){
			return false;
		}
		
		
		}
		
		
		
		
		function validateText(id, textname) {
			var textValue = $.trim($("#" + id).val());
			if (textValue == null || textValue == "") {
				alert(textname + "不能为空");
				return false;
			}
			return true;
		}


  
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

  </script>


  </body>
</html>
