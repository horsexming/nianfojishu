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
$(function() {
	//加载所有分组
	$
			.ajax( {
				type : "post",
				url : "AssessPersonnelAction!findAuditGroup.action?pageStatus=sh",
				dataType : "json",
				success : function(object) {
					//填充分组信息
					$("<option value='0'>所有审核人员</option>").appendTo(
							"#userGroup");
					$.each(object, function(i, group) {
						$(
								"<option value='" + group.id + "'>"
										+ group.groupName + "</option>")
								.appendTo("#userGroup");
					});

					//绑定选择分组
					$("#userGroup")
							.bind(
									"click",
									function() {
										$
												.ajax( {
													type : "post",
													url : "AssessPersonnelAction!findAuditPerson.action?pageStatus=sh",
													data : {
														id : $("#userGroup")
																.val()
													},
													dataType : "json",
													success : function(persons) {
														$("#person").empty();
														$(
																"<option>请选择人员</option>")
																.appendTo(
																		"#person");
														$
																.each(
																		persons,
																		function(
																				i,
																				person) {
																			$(
																					"<option value='"
																							+ person.userId
																							+ "'>"
																							+ person.userName
																							+ "</option>")
																					.appendTo(
																							"#person");
																		});
													}
												});
									});
				}
			});

});

//右移
function getPerson(id, obj) {
	var personVal = $("#person");
	var isdept = $("#isdept").attr("checked");
	var bddeptval = $("#bddept").val();
	 if(isdept ==  "checked" && bddeptval == null){
		alert('请先选择已绑定的部门');
		return;
	}
	if (personVal.val() > 0) {
		//临时设置为不可用,防止点击速度过快
		obj.disabled = true;
		if(bddeptval!=null && bddeptval>0){
			$("#bddeptId").val(bddeptval);
		}
		
		var userId = personVal.val();
		//查询用户信息
		$.ajax( {
			url : "UsersAction!findUserByIdForJson.action",
			data : {
				id : userId
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				//为临时form赋值，保存节点
			$("#auditLevel").val(id);
			$("#auditUserName").val(data.name);
			$("#auditUserDept").val(data.dept);
			$("#auditUserId").val(data.id);
			//保存审批节点
			$.ajax( {
				type : "POST",
				url : "CircuitCustomize_addAuditNode.action",
				dataType : "json",
				data : $("#auditNodeForm").serialize(),
				success : function(msg) {
					if (msg.success) {
						var checkIndex = $("#person").get(0).selectedIndex;//获取Select选择的索引值 
				//获得当前选择的option并移动
				var so = $("#person option:selected");
				$("#level" + id).append(so);
				//选中第一个option
				$("#person").get(0).selectedIndex = 1;
				//var selectSize = $("#level1").get(0).options.length;
				//$("#level1").size = selectSize;
				obj.disabled = false;
				success("addStatus",id)
			} else {
				alert("保存失败!检查人员是否已经存在!");
				obj.disabled = false;
				error("addStatus",id);
			}

		}
			});
		}
		})
	} else if (personVal.val() == null) {
		alert("请选择人员!");
	} else {
		alert(personVal.val());
	}
}

function success(name,id) {
	$("#"+name + id)
			.html(
					"<img	src='${pageContext.request.contextPath}/images/success.jpg'>");
}

function error(name,id) {
	$("#"+name + id).html(
			"<img	src='${pageContext.request.contextPath}/images/error.jpg'>");
}

//左移
function backPerson(id, obj) {
	var levelVal = $("#level" + id);
	var isdept = $("#isdept").attr("checked");
	var bddeptval = $("#bddept").val();
	 if(isdept ==  "checked" && bddeptval == null){
		alert('请先选择已绑定的部门');
		return;
	}
	if (levelVal.val() > 0) {
		//临时设置为不可用,防止点击速度过快
		obj.disabled = true;
		//保存审批节点
		$.ajax( {
			type : "POST",
			url : "CircuitCustomize_delAuditNode.action",
			dataType : "json",
			data : {
				id : levelVal.val(),
				ccId : "${id}",
				auditLevel : id,
				deptId : bddeptval
			},
			success : function(msg) {
				if (msg.success) {
					var checkIndex = $("#level" + id).get(0).selectedIndex;//获取Select选择的索引值 
			var so = $("#level" + id + " option:selected");
			$("#person").append(so);
			//选中第一个option
			$("#level" + id).get(0).selectedIndex = 0;
			obj.disabled = false;
			success("addStatus",id);
		} else {
			alert("删除失败!请检查!");
			obj.disabled = false;
			error("addStatus",id);
		}
	}
		});
	} else if (levelVal.val() == null) {
		alert("请选择人员!");
	}
}
//添加审核等级
var count = 1;
function addLevel() {
	count++;
	$(
			"<div id='levelDiv"
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
					+ "' style='width: 100px;' size='3'></select><span id='addStatus"
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

//是否选择部门
function xzdept(obj){
	if(obj!=null && obj.checked == true){
		$("#div_table").show();
	}
}
function qxdept(obj){
	if(obj!=null && obj.checked == true){
		$("#div_table").hide();
	}
}
//流程绑定部门
function bddept(obj){
	var dept = $("#deptname");
	if (dept.val()> 0){
		$.ajax( {
		type : "post",
		url : "CircuitCustomize_bddept.action",
		dataType : "json",
		data : {
				ccId : "${id}",
				deptId : dept.val()
			},
		success : function(data) {
			if(data == "true"){
				obj.disabled = true;
				var checkIndex = $("#deptname").get(0).selectedIndex;//获取Select选择的索引值 
				var so = $("#deptname option:selected");
				$("#bddept").append(so);
				//选中第一个option
				$("#deptname").get(0).selectedIndex = 0;
				obj.disabled = false;
				success("bddept_div","0");
			}else{
				alert("请检查部门是否已存在!");
				obj.disabled = false;
				error("bddept_div","0")
			}
		}
	});
	} else {
		alert("请选择部门!");
	}
	
}
//解除流程绑定部门
function jcbddept(obj){
	var dept = $("#bddept");
	if (dept.val()> 0){
		$.ajax( {
		type : "post",
		url : "CircuitCustomize_jcbddept.action",
		dataType : "json",
		data : {
				ccId : "${id}",
				deptId : dept.val()
			},
		success : function(data) {
			if(data == "true"){
				obj.disabled = true;
				var checkIndex = $("#bddept").get(0).selectedIndex;//获取Select选择的索引值 
				var so = $("#bddept option:selected");
				$("#deptname").append(so);
				//选中第一个option
				$("#bddept").get(0).selectedIndex = 0;
				obj.disabled = false;
				success("bddept_div","0");
			}else{
				alert("删除失败!请检查!");
				obj.disabled = false;
				error("bddept_div","0")
			}
		}
	});
	} else{
		alert("请选择部门!");
	}
	
}


$(function() {
	//加载所有分组
	$.ajax( {
		type : "post",
		url : "CircuitCustomize_findAlldept.action",
		dataType : "json",
		data : {
				ccId : "${id}"
			},
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.dept
										+ "</option>").appendTo("#deptname");
					});
		}
	});
	$.ajax( {
		type : "post",
		url : "CircuitCustomize_findbddept.action",
		dataType : "json",
		data : {
				ccId : "${id}"
			},
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this[0] + "'>" + this[1]
										+ "</option>").appendTo("#bddept");
					});
		}
	});
	
	
	
});


</script>
	</head>
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<form id="auditNodeForm" action="" style="display: none;">
			<input name="id" value="${id}">
			<input id="auditLevel" name="auditNode.auditLevel">
			<input id="auditUserName" name="auditNode.auditUserName">
			<input id="auditUserDept" name="auditNode.auditUserDept">
			<input id="auditUserId" name="auditNode.auditUserId">
			<input id="bddeptId" name="deptId" value="0">
		</form>
		<div id="gongneng">
			<div align="center" style="width: 100%;">
				<div style="font-size: 20px; font-weight: bolder;">
					${circuitCustomize.name} 
				</div>
				<hr />
				<div style="width: 30%; float: left;" id="dept_div">
				<p align="left">
						<STRONG>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否选择部门:</STRONG>
						<input type="radio" value="是" id="isdept" name="deptradio" onclick="xzdept(this)"/>是
						<input type="radio" value="否" checked="checked" name="deptradio" onclick="qxdept(this)"/>否
					</p>
					<table id="div_table" style="display: none;">
						<tr>
							<th>未绑定部门:</th>
							<th></th>
							<th>已绑定部门:</th>
							<td></td>
						</tr>
						<tr>
							<td>
								<select id="deptname" name="" style="width: 200px;" size="20">
									<option value="0">
										请选择部门
									</option>
							</select>
							</td>
							<td align="center">
									<input type="button" value="------->"
									   onclick="bddept(this)"/>
									   <br/>
									<input type="button" value="<-------"
									   onclick="jcbddept(this)"/>
							</td>
							<td>
								<select id="bddept" name="" style="width: 200px;" size="20" onchange="findAuditNode(this)">
								</select>
							</td>
							<td>
								<div id="bddept_div0">
								</div>
							</td>
						</tr>
					</table>
					
						
				</div>

				<div style="width: 70%;float: left;">
			<br/>
			<br/>
					<div style="float: left; width: 49%;" align="center">
						<select id="userGroup" name="" style="width: 100px;" size="20">
							<option>
								请选择分组
							</option>
						</select>
						<select id="person" name="" style="width: 100px;" size="20">
							<option>
								请先选择分组
							</option>
						</select>
					</div>
					<div id="allLevel" style="float: left; width: 50%;" align="left">
						<div>
							<input type="button" value="添加审核等级" class="input"
								style="width: 100px;" onclick="addLevel()" />
							<input type="button" value="删除审核等级" class="input"
								style="width: 100px;" onclick="delLevel()" />
						</div>
						<div id="levelDiv1">
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
								<select id="level1" style="width: 100px;" size="3"></select>
								<span id="addStatus1" style="color: red;"> </span>
							</div>
							<div style="clear: both;">
							</div>
							<br />
						</div>
					</div>
					<div style="clear: both;"></div>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//修改节点人员
$(function() {
		$.ajax( {
		type : "POST",
		url : "CircuitCustomize_findAuditNode.action",
		data : {
			id : '${id}'
		},
		dataType : "json",
		success : function(obj) {
			$.each(obj, function(i, auditNode) {
				var level = auditNode.auditLevel;
				if ($("#level" + level).length <= 0) {
					//不存在添加新的审核等级
					for (count; count < level;) {
						addLevel();
					}
				}
				//赋值
					$(
							"<option value='" + auditNode.auditUserId + "'>"
									+ auditNode.auditUserName + "</option>")
							.appendTo($("#level" + level));
				});
		}
	})
	
});

function findAuditNode(obj){
	if(obj!=null && obj.value >0){
		$.ajax( {
		type : "POST",
		url : "CircuitCustomize_findAuditNode.action",
		data : {
			id : '${id}',
			deptId :obj.value
		},
		dataType : "json",
		success : function(obj) {
				for(i=1;i<=count;i++){
					if(i==1){
						$("#level1").empty();
					}else{
						$("#levelDiv"+i).remove();
					}
				}
			count = 1;
			$.each(obj, function(i, auditNode) {
				var level = auditNode.auditLevel;
				if ($("#level" + level).length <= 0) {
					//不存在添加新的审核等级
					for (count; count < level;) {
						addLevel();
					}
				}
				//赋值
					$(
							"<option value='" + auditNode.auditUserId + "'>"
									+ auditNode.auditUserName + "</option>")
							.appendTo($("#level" + level));
				});
		}
	})
		}
}
</script>
	</body>
</html>
