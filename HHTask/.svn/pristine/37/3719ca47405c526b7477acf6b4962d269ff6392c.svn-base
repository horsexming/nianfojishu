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
			<style type="text/css">
.ztree li a {
	color: #fff;
}
</style>
	</head>
	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
					<div>
						查看 <font color="red"> ${banCi.name} </font>已绑定人员
					</div>
					<form action="banCiAction_findStationCondition.action?tag=${tag}"
						method="post" style="margin: 0px">
						<br>
						<table class="table">
							<tr>
								<td align="right">
									姓名:
								</td>
								<td>
									<input type="text" name="user.name" />
								</td>
								<td align="right">
									部门:
								</td>
								<td>
								<s:if test="tag=='dept'">
										<input id="dept1" type="text" name="user.dept" id="dept" value="${Users.dept}" readonly="readonly"/>
									</s:if>
									<s:else>
										<div class="zTreeDemoBackground left">
											<ul class="list">
												<li class="title">
													<input id="dept1" type="text" name="user.dept" id="dept" value="${user.dept}"/>
													<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
													<br><font color="red">(按住Ctrl建不松点击,可多选!)</font>
												</li>
											</ul>
										</div>
										<div id="menuContent" class="menuContent"
											style="display: none; position: absolute;">
											<ul id="treeDemo" class="ztree"
												style="margin-top: 0; width: 160px;"></ul>
										</div>
									</s:else>
								</td>
								<td rowspan="2">
									<input type="hidden" name="banCi.id" value="${banCi.id}">
									<input type="submit" value="查询"
										style="width: 100px; height: 50px;" />
								</td>
							</tr>
							<tr>
								<td align="right">
									卡号:
								</td>
								<td>
									<input type="text" name="user.cardId" />
								</td>
								<td align="right">
									工号:
								</td>
								<td>
									<input type="text" name="user.code" />
								</td>

							</tr>
						</table>
					</form>
					<form action="banCiAction_jiebangding.action?tag=${tag}" method="post"
						style="margin: 0px">
						<input type="hidden" name="banCi.id" value="${banCi.id}">
						<table class="table" align="center">
							<tr>
								<td align="right" colspan="7">
									<font color="red">共选择 <label id="peopleLabel">
											${count}
										</label> <input type="hidden" id="propleText" name="peopleNum"
											style="width: 20px;" readonly="readonly"> 人</font>
									<input type="submit" value="解除绑定"
										style="width: 70px; height: 40px;" align="top">
									<br>
									<br>
								</td>
							</tr>
							<tr bgcolor="green">
								<td colspan="7" style="font-family: 微软雅黑; font-weight: bold;"
									align="center">
									<font color="#ffffff"> 已绑定用户</font>
								</td>
							</tr>
							<tr align="center" bgcolor="#c0dcf2"
								style="height: 40px; font-weight: bold;">
								<td>
									序号
								</td>
								<td>
									工号
								</td>
								<td>
									卡号
								</td>
								<td>
									姓名
								</td>
								<td>
									部门
								</td>
								<td>
									职位
								</td>
								<td>
									<input type="checkbox" id="checkAll"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>
							<s:iterator id="users" value="bangusersList"
								status="ststusfunction">
								<s:if test="#ststusfunction.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:if test="#ststusfunction.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#ststusfunction.index+1" />
									</font>
								</td>
								<td>
									${users.code}
								</td>
								<td>
									${users.cardId}
								</td>
								<td>
									${users.name}
								</td>
								<td>
									${users.dept}
								</td>
								<td>
									${users.duty}
								</td>
								<td>
									<input type="checkbox"
										id="user<s:property value="#ststusfunction.index"/>"
										name="usersId" value="${users.id}" onclick="chageNum(this)">
								</td>
								</tr>
							</s:iterator>
							<tr>
								<td colspan="7" align="right"
									style="font-weight: bold; padding-right: 40px">
									<input type="checkbox" id="checkAll2"
										onclick="chageAllCheck(this)">
									全选
								</td>
							</tr>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="7" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="7" align="center" style="color: red">
										${errorMessage}
									</td>
								</s:else>
							</tr>
							<tr>
								<td align="right" colspan="7">
									<br>
									<font color="red">共选择 <label id="peopleLabel2">
											${count}
										</label>人</font>
									<input type="submit" value="解除绑定"
										style="width: 70px; height: 40px;" align="top">
									<br>
									<br>
									<br>
									<br>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageBgcolor(obj) {
	obj.style.background = "#c0dcf2";
}
function outBgcolor(obj, oldColor) {
	obj.style.background = oldColor;
}
function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox, checkBox.id);
				}
			}
		}
	}
}
var num = "${count}";
if (num == "") {
	num = 0;
}
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
			checkAll2.checked = true;
		}
		num++;
	} else if (num == 0 && check.checked == false) {
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
	document.getElementById("propleText").value = num;
}

var mfzTree;
var addzTree;
var delzTree;
var updatezTree;

var id;
var pId;
var name;
var setting = {
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		//beforeClick : beforeClick,
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	parent.mfzTree();
});
var zNodes = [];
parent.mfzTree = function() {
	$.ajax( {
		url : 'DeptNumberAction!findAllDept.action',
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(doc) {
			$(doc).each(function() {
				zNodes.push( {
					id : $(this).attr('id'),
					pId : $(this).attr('fatherId'),
					name : $(this).attr('dept'),
					target : "main",
					click : false
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandAll(true);
		},
		error : function() {
			alert("服务器异常!");
		}
	});
};
function beforeClick(treeId, treeNode) {
	var check = (treeNode && !treeNode.isParent);
	if (!check)
		alert("只能选择最底层");
	return check;
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getSelectedNodes(), v = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for ( var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ";";
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	cityObj = $("#dept1").val(v);

}

function showMenu() {
	var cityObj = $("#dept1");
	var cityOffset = $("#dept1").offset();
	$("#menuContent").css( {
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px"
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
			event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

</script>
	</body>
</html>
