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
		<div id="gongneng"
			style="width: 100%; border: solid 1px; margin-top: 10px;">
			<div id="xitong"
			</div>
			<s:if test="errorMessage!=null">
				<div align="center">
					<h3>
						<font color="red" style="color: red;">${errorMessage}</font>
					</h3>
				</div>
			</s:if>
			<div align="center">
				<form action="overtimeAction!addOvertime.action?tag=overList"
					method="post" style="" onsubmit="return checkType();">
					<br>
					<table border="0" style="width: 70%" class="table" id="tablebod">
						<tbody>
							<tr>
								<td align="right">
									加班开始时间:
								</td>
								<td>
									<input id="joined1" class="Wdate" type="text"
										name="overtime.startDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',skin:'whyGreen'})" />
										&nbsp;&nbsp;&nbsp; 中途休息时长：
									<input id="xiuxi" type="text"
										name="overtime.xiuxi" style="width: 60px;"/> 分钟
								</td>
								<td align="right">
									加班结束时间:
								</td>
								<td>
									<input id="joined2" class="Wdate" type="text"
										name="overtime.endDate"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td align="right">
									加班类型:
								</td>
								<td>
									<input id="overtimeType1" class="Wdate" type="radio"
										name="overtime.overtimeType" value="生产" checked="checked"
										onchange="gongchu(this.value);" />
									生产

									<input id="overtimeType2" class="Wdate" type="radio"
										name="overtime.overtimeType" value="项目"
										onchange="gongchu(this.value);" />
									项目

									<input id="overtimeType3" class="Wdate" type="radio" TITLE="持续改进"
										name="overtime.overtimeType" value="KVP"
										onchange="gongchu(this.value);" />
									持续改进(KVP)
									<input id="overtimeType3" class="Wdate" type="radio" TITLE="持续改进"
										name="overtime.overtimeType" value="其它"
										onchange="gongchu(this.value);" />
									其它
								</td>
								<td></td>
								<td></td>
							</tr>
							<!--  ********************************************************************************************** -->
							<tr id="t1" style="display: none;">
								<td align="right">
									加工件号:
								</td>
								<td>
									<!-- 
								<select name="overtime.markId" id="markId" multiple="multiple"
									size="2">

								</select>
								 -->
									<select name="overtime.markId" id="markId"
										style="width: 250px;">
									</select>
								</td>
								<td align="right" id="t12">
									数量:
								</td>
								<td>
									<input type="text" id="amount" name="overtime.amount" value="" />
								</td>
							</tr>
							<!--  ********************************************************************************************** -->
							<tr id="t2" style="display: none;">
								<td align="right">
									项目编号:
								</td>
								<td>
									<select name="overtime.markId" id="xiangmu"
										style="width: 250px;" onclick="getf1()">
									</select>
								</td>
								<td align="right">
								</td>
								<td>
								</td>

							</tr>
							<!--  ********************************************************************************************** 	-->
							<tr id="t4" style="display: none;">
								<td align="right">
									KVP编号:
								</td>
								<td>
									<select name="overtime.markId" id="kvp" style="width: 250px;"
										onclick="getf2()">

									</select>
								</td>
								<td align="right">
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<td align="right">
									是否换休:
								</td>
								<td>
									<input type="radio" name="overtime.huanxiu" value="是"
										checked="checked" />
									是
									<input type="radio" name="overtime.huanxiu" value="否"/>
									否
								</td>
								<td align="right">
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<th align="right">
									加班说明：
								</th>
								<td>
									<textarea rows="3" cols="50" id="shuoming"
										name="overtime.shuoming" /></textarea>
								</td>
								<th></th>
								<th></th>
							</tr>
							<tr>
								<td align="right">
									加班人部门:
								</td>
								<td colspan="3">
									<input id="applyId" type="hidden" name="overtime.applyId"
										value="${sessionScope.Users.id}" />
									<input id="applyCode" type="hidden" name="overtime.applyCode"
										value="${sessionScope.Users.code}" />
									<input id="applyName" type="hidden" name="overtime.applyName"
										value="${sessionScope.Users.name}" />
									<input id="applyDept" type="hidden" name="overtime.applyDept"
										value="${sessionScope.Users.dept}" />
<%--									<input id="userId0" type="hidden" name="overtimeListDai[0].overtimeId" value="" />--%>
<%--									<input id="usCode0" type="hidden" name="overtimeListDai[0].overtimeCode"--%>
<%--										value="" />--%>
<%--									<input id="usName0" type="hidden" name="overtimeListDai[0].overtimeName"--%>
<%--										value="" />--%>
<%--									<input id="usCardId0" type="hidden" name="overtimeListDai[0].overtimeCardId"--%>
<%--										value="" />--%>
<%--									<input id="usDept0" type="hidden" name="overtimeListDai[0].overtimeDept"--%>
<%--										value="" />--%>

									<select id="overtimeDept" style="color: #000;">
										<option value="">
											请选择
										</option>
									</select>
								</td>
							</tr>
							<tr style="width: 100%">
								<td align="right">加班人：</td>
								<td id="usertable0" colspan="3"></td>
							</tr>
							<tr>
								<td align="right">
									<input type="button" id="tijiao" onclick="addUser()"value="添加人员" />
								</td>
								<td colspan="3">
									<input type="button" id="shanchu" onclick="delUser()" style="display: none;" value="删除人员" />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="8">
									<input type="submit" value="提交"
										style="width: 100px; height: 50px;" />

									<input type="reset" value="重置"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

$(document).ready(function() {
	f1();
})
function f1() {
	$.ajax( {
		type : "POST",
		url : "overtimeAction!finAllMarkIdForSetlectAll.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj[0] + "/" + obj[1] + "'>" + "件号"
						+ obj[0] + "   批次" + obj[1] + "</option>";
			});
			$(bj).appendTo("#markId")
			$("#markId").tinyselect();
		}
	});
}





var size = 0;//信息条数
var index = 1;//信息下标
var xia = 1;

function addUser(){
	var trindex = 8 + size;
	var usertable = "usertable" + index;
	var overtimeDept = "overtimeDept" + index;
	$("#tablebod>tbody>tr")
			.eq(trindex)
			.after(
					"<tr id='addtr"+index+"'>" +
						"<td colspan='1' align='right'>加班人部门:" +
						"</td>" +
						"<td colspan='3'>" +
							"<select id="+overtimeDept+" onclick=\"deptName1('"+index+"')\" style='width: 152px;'>" +
								"<option value=''>请选择</option>" +
							"</select>"+
						"</td>" +
					"</tr>" +
					"<tr>" +
						"<td colspan='1' align='right'>" +
							"加班人:" +
						"</td>" +
						"<td id="+usertable+" colspan='3'></td>" +
					"</tr>");
	$.ajax( {
		type : "get",
		dataType : "text",
		url : "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async : false,
		success : function(data) {
			var dept = data.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				$("<option value='" + dept[i] + "'>" + dept[i] + "</option>").appendTo("#overtimeDept"+index);
			}
			//$("#overtimeDept"+index).tinyselect();
		}
	});
	size++;
	size++;
	index++;
	xia++;
	xia++;
	document.getElementById("shanchu").style.display = "block";
	
}

//删除
function delUser() {
	tablebod.deleteRow(xia + 7);
	tablebod.deleteRow(xia + 6);
	size--;
	size--;
	index--;
	xia--;
	xia--;
	if (size < 1) {
		document.getElementById("shanchu").style.display = "none";
	}
}

$(function(){
	$.ajax({
		type : "get",
		dataType : "text",
		url : "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async : false,
		success : function(data) {
			var dept = data.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				$("<option value='" + dept[i] + "'>" + dept[i] + "</option>").appendTo(
					"#overtimeDept");
			}
			$("#overtimeDept").tinyselect();
		}
	});

	//级联查询出部门所对应的所有人员
	$("#overtimeDept").bind(
			"change",
			function() {
				if ($("#overtimeDept"+index).val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#overtimeDept").val()
						},
						success : function(useradsfa) {
							$("#usertable0").empty();//清空
							$("#usertable0").append("<p><input type='checkbox' onchange='isQuanXuan(this)' checked='checked' >全选</p>");
							$(useradsfa).each(
									function(i) {
										if(i%12==0&&i>0){
											$("<br/>").appendTo("#usertable0");
										}
										$(
											"<input type='checkbox' id='single' checked='checked' name='usersId' "
											+ "value='" + this.id
											+ "' /> " + this.name +"</td>").appendTo(
											"#usertable0");
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				}
			});
});

function deptName1(obj){
	//级联查询出部门所对应的所有人员
	$("#overtimeDept"+obj).bind(
			"change",
			function() {
				if ($("#overtimeDept"+obj).val() != "") {
					$.ajax( {
						url : "UsersAction!findUsersByDept.action",
						type : 'post',
						dataType : 'json',
						cache : false,//防止数据缓存
						data : {
							deptName : $("#overtimeDept"+obj).val()
						},
						success : function(useradsfa) {
							$("#usertable"+obj).empty();//清空
							$("#usertable"+obj).append("<p><input type='checkbox' onchange='isQuanXuan(this)' checked='checked' >全选</p>");
							$(useradsfa).each(
									function(i) {
										if(i%12==0&&i>0){
											$("<br/>").appendTo("#usertable"+obj);
										}
										$(
											"<input type='checkbox' id='single' checked='checked' name='usersId' "
											+ "value='" + this.id
											+ "' /> " + this.name +"</td>").appendTo(
											"#usertable"+obj);
									});
						},
						error : function() {
							alert("服务器异常!");
						}
					});
				}
			});
}

function deptinfor1(ob){
	//$("#overtimeUser").bind("change", function() {
		var overtimeUser = $("#overtimeUser"+ob).val();
		var overtimeValues = overtimeUser.split("|");
		var dept = overtimeValues[0];
		var code = overtimeValues[1];
		var name = overtimeValues[2];
		var id = overtimeValues[3];
		var cardId = overtimeValues[4];
		$('#userId'+ob).val(id);
		$('#usCode'+ob).val(code);
		$('#usName'+ob).val(name);
		$('#usCardId'+ob).val(cardId);
		$('#usDept'+ob).val(dept);
	//});
}

function getf1() {
	$("#xiangmu").onclick = function() {
	}
	$.ajax( {
		type : "POST",
		url : "proAction!listPro.action",
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj.code + "'>" + obj.code + "   "
						+ obj.name + "</option>";
			});
			$(bj).appendTo("#xiangmu")
			$("#xiangmu").tinyselect();
		}
	});

}
function getf2() {
	$.ajax( {
		type : "POST",
		url : "proAction!listKVP.action",
		data : {
			overName : $("#overtimeName").val()
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='" + obj[0] + "'>" + obj[0] + "   "
						+ obj[1] + "</option>";
			});
			$(bj).appendTo("#kvp")
			$("#kvp").tinyselect();
		}
	});
}
//proAction!listPro.action",
function gongchu1(val) {
	document.getElementById("xiangmu").value = "";
<%--	document.getElementById("kvp").value = "";--%>
	document.getElementById("markId").value = "";
	//项目
	if (val == "项目") {
		$("#t1").css("display", "none");
<%--		$("#t3").css("display", "none");--%>
		$("#t2").show();
		//tr_modifing.style.display = "table−row";
		//显示项目信息
		//-----------------------------------------------------------
		//-------------------------------------KVP------------------------------------
	} 
	/*else if (val == "KVP") {
		$("#t1").css("display", "none");
		$("#t2").css("display", "none");
		$("#t3").show();
		//显示项目信息
		//-----------------------------------------------------------

		//------------------------
	} */
	else {
<%--		$("#t3").css("display", "none");--%>
		$("#t2").css("display", "none");
		$("#t1").show();
		//------------------------------------
		//---------------------------------------

	}
}
function checkType() {
	var overtimeDept = document.getElementById('overtimeDept').value;
	if (overtimeDept == "") {
		alert("加班部门不能为空");
		return false;
	}
<%--	var overtimeUser = document.getElementById('overtimeUser').value;--%>
<%--	if (overtimeUser == "") {--%>
<%--		alert("加班人不能为空");--%>
<%--		return false;--%>
<%--	}--%>

	var joined1 = document.getElementById('joined1').value;
	var joined2 = document.getElementById('joined2').value;
	if (joined1 == "") {
		alert("加班开始时间不能为空！");
		return false;
	}
	if (joined2 == "") {
		alert("加班结束时间不能为空！");
		return false;
	}
	if (joined1 > joined2) {
		alert("加班开始时间不能大于结束时间！！！");
		return false;
	}
	var shuoming = document.getElementById('shuoming').value;
	if (shuoming == "") {
		alert("加班说明不能为空");
		return false;
	}
	var xiangmu = document.getElementById('xiangmu').value;
	var kvp = document.getElementById('kvp').value;
	var markId = document.getElementById('markId').value;
	var amount = document.getElementById('amount').value;
	var value = "";
	var obj2 = document.getElementsByName('overtime.overtimeType');
	for ( var i = 0; i < obj2.length; i++) {
		if (obj2[i].checked == true) {
			value = obj2[i].value;
			break;
		}
	}

	/*if (value == "项目") {
		if (xiangmu == ""||xiangmu == null) {
			alert("项目不能为空");
			return false;
		}
		var amount = document.getElementById('amount').value;
		if (amount == ""||amount == null) {
			alert("加工件号不能为空！");
			return false;
		}
	}*/
<%--	if (value == "kvp") {--%>
<%--		if (kvp == ""||kvp == null) {--%>
<%--			alert("kVP不能为空");--%>
<%--			return false;--%>
<%--		}--%>
<%--	}--%>
	/*if (value == "生产") {
		if (markId == ""||markId == null) {
			alert("加工件号不能为空");
			return false;
		}
		if (amount == ""||amount == null) {
			alert("数量不能为空");
			return false;
		}
	}*/
	var is = ischaoshi();
	if(is=='true'){
		return true;
	}else{
		return false;
	}
	
	//return true;
}


function ischaoshi() {
	var joined1 = $("#joined1").val();
	var joined2 = $("#joined2").val();
	var usersId = "";
	var overtimeId = document.getElementsByName('usersId');
	for (i=0;i<overtimeId.length;i++){
		if(overtimeId[i].checked == true) {
		   	usersId+=overtimeId[i].value+",";
		}
	}
	var xiuxi = $("#xiuxi").val();
	var fanhui = "";
	if (joined1 != "" && joined2 != "" && usersId !="") {
		$.ajax( {
			type : "POST",
			async: false,
			url : "overtimeAction!isbandciscList.action",
			data : {
				usersIds:usersId,
				startDate:joined1,
				endDate:joined2,
				xiuxi:xiuxi
			},
			dataType : "json",
			success : function(data) {
				if (data != null) {
					if(data != "true"){
						if(data.indexOf("加班开始时间")!=-1){
							 $("#joined1").val("");
						}else if(data.indexOf("加班结束时间")!=-1){
							 $("#joined2").val("");
						}
						alert(data);
					}
				}
				fanhui = data;
			}
		});
	}
	return fanhui;
}

//全选
function chageAllCheck1(){
	var checkAll=document.getElementById("all");
	var checkboxs=document.getElementsByName("receiver");
	if(checkAll.checked==true){
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=true;
		}
	}else{
		for(var i=0;i<checkboxs.length;i++){
			checkboxs[i].checked=false;
		}
	}
}

function isQuanXuan(obj){
	$(obj).parent().parent().children("input").each(function(){
		if(obj.checked){
			$(this).attr("checked","checked");
		}else{
			$(this).removeAttr("checked");
		}
		
	});
}

//========================================zTree显示
//自动组装树形结构
/*var setting = {
	check : {//checkBox选择框
		enable : true,
		autoCheckTrigger : true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {//回调函数
		onClick : onClick,
		beforeCheck : beforeCheck,
		onCheck : onCheck
	}
};
//读取树形数据
$(document).ready(function() {
	showDeptSub();
});

function showDeptSub() {
	$.ajax( {
		type : 'post',
		url : 'DeptNumberAction!findAlldept.action',
		data : {
			id : 0
		},
		dataType : 'json',
		cache : true,
		success : function(doc) {
			var zNodes = [];
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					name : $(this).attr('deptName'),
					pId : $(this).attr('fatherId'),
					checked : false,
					open : true,
					click : false
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//父子关联关系(双向关联)
		$.fn.zTree.getZTreeObj("treeDemo").setting.check.chkboxType = {};
	},
	error : function() {
		alert("服务器异常!");
	}
	});
};

//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {

}

//选中回调函数
function onCheck(e, treeId, treeNode) {
}

//选中函数
function beforeCheck(treeId, treeNode) {
	var ids = document.getElementById("deptIds");
	var index = ids.value.indexOf("," + treeNode.id);
	if (index > 0) {
		ids.value = ids.value.replace("," + treeNode.id, "");
	} else {
		ids.value = ids.value + "," + treeNode.id;
	}
	getUsers(ids.value);
}

function getUsers(deptIds) {
	if (deptIds == 0) {
		$("#usertable").empty();
	}
	{
		$.ajax( {
			type : 'post',
			url : 'DeptNumberAction!getDeptUsers.action',
			dataType : 'json',
			data : {
				deptIds : deptIds
			},
			cache : false,//防止数据缓存
			success : function(allusers) {
				$("#usertable").empty();
				$(allusers).each(
						function(i, n) {
							$(
									"<tr><td><input type='checkbox' id='single' name='receiver' "
											+ "value='" + n.userName
											+ "' onchange='chageNum()' /> "
											+ n.name + "</td> </tr>").appendTo(
									"#usertable");
						});
			}

		});
	}
}*/

</script>
	</body>
</html>
