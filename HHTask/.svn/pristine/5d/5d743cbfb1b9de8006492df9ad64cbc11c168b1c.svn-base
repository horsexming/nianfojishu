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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="<%=basePath%>System/caiwu/budget/subMonthMoney_manage.jsp?rootId=${pageSmm.rootId}&pageStatus=${pageStatus}">填报</a>
				</div>
			</div>
			<div align="center">
				<div id="showMessage" align="center" style="color: red;">
				</div>
				<div id="title" align="center"
					style="border: 1px #000000 solid; font-size: 16px; font-weight: bolder;">
				</div>
				<div align="left">
					<!-- 显示科目树形 -->
					<div
						style="width: 25%; height: 100%; float: left; border-right: 1px solid #000000;"
						align="left">
						请选择将填报的科目:
						<hr />
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					<!--科目管理操作 -->
					<div id="addSmm" style="float: left; width: 73%; display: none;">
						<form id="addForm" style="margin: 0px; padding: 0px;">
							<input type="hidden" id="id" name="id" />
							<input type="hidden" id="rootId" name="deptMonthBudget.rootId" />
							<input type="hidden" id="budgetMonth"
								name="deptMonthBudget.budgetMonth" />
							<table class="table" style="width: 100%;">
								<tr>
									<th align="center" colspan="2">
										下月科目预算
									</th>
								</tr>
								<tr>
									<th align="right">
										计划类型:
									</th>
									<td>
										<input id="neijhStatus" type="radio" onclick="hehehide()"
											name="deptMonthBudget.jhStatus" value="nei" checked="checked">
										<span>计划内</span>
										<input id="waijhStatus" type="radio" onclick="heheshow()"
											name="deptMonthBudget.jhStatus" value="wai">
										计划外
										<span id="showMessagespan" style="color: red;"></span>
									</td>
								</tr>
								<tr id="yusuan" style="display: none;">
									<th align="right">
										年度预算:
									</th>
									<td>
										<select id="ys" name="">
										</select>
										<span id="syed"></span>
										<input type="hidden" name="idyu" id="idyu">
										<input type="hidden" name="shengyu" id="shengyu">
									</td>
								</tr>
								<tr>
									<th align="right">
										科目名:
									</th>
									<td>
										<input id="name" name="deptMonthBudget.name"
											readonly="readonly" />
									</td>
								</tr>
								<tr>
									<th align="right">
										预算金额:
									</th>
									<td>
										<input id="accountMoney" name="deptMonthBudget.accountMoney"
											onblur="numberCheck()"
											onkeyup="value=value.replace(/[^\d\.]/g,'');numberCheck()"
											value="0" />
										<span id="reaccountMoney"></span>
									</td>
								</tr>
								<tr>
									<th align="right">
										说明:
									</th>
									<td>
										<textarea id="budgetDetail"
											name="deptMonthBudget.budgetDetail" rows="5" cols="60"></textarea>
									</td>
								</tr>
								<tr>
									<th align="center" colspan="2">
										<input type="button" value="确定" class="input" id="subBut"
											onclick="submitForm('addForm','add')" />
										<input type="reset" value="重置" class="input" id="setBut" />
									</th>
								</tr>
							</table>
						</form>
						<div>
							<table id="showHistory" class="table" style="width: 100%;">
							</table>
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
//金额判定

function numberCheck() {
	var testValue = document.getElementById("accountMoney").value;
	var shengyu = document.getElementById("shengyu").value;
	var mdiv = document.getElementById("reaccountMoney");
	if (testValue == "") {
		mdiv.innerHTML = "<font color='red'>金额不能为空</font>";
	} else if (parseFloat(testValue) > parseFloat(shengyu)) {
		mdiv.innerHTML = "<font color='red'>预算金额不能大于年度预算剩余金额!</font>"
	} else {
		mdiv.innerHTML = "<font color='red'></font>"
	}
}

//显示
function heheshow() {
	$("#yusuan").show();
	$('#ys').empty();
	var a = window.document.getElementById("syed");
	a.innerHTML = "";
	$.ajax( {
		url : 'YusuantianbaobiaoAction!findMingxi.action',
		data : {},
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(data) {
			$("#ys").empty();//清空
			if (data == null) {
				$("#syed").html(
						"<font color='red'>未发现可用的年度预算项目,无法填报计划外预算!</font>");
			}
			$(data).each(
					function() {
						$(
								"<option value='" + this.shengyu + "|"
										+ this.id + "|" + this.xiangmumingda
										+ "'>" + this.xiangmumingda + "("
										+ this.shengyu + "/" + this.zongjine
										+ ")</option>").appendTo("#ys")

						showShengyu();
					});

		},
		error : function() {
			alert("服务器异常!未发现可用的年度预算项目");
		}
	});
	$("#ys").bind("change", function() {
		showShengyu();
	});
}

function showShengyu() {
	var a = window.document.getElementById("syed");
	var ys = $("#ys").val();
	var usersData = ys.split("|");
	var shengyu = usersData[0];
	var idyu = usersData[1];
	a.innerHTML = "剩余金额<font color='red'>" + shengyu + "</font>元";
	$("#idyu").val(idyu);
	$("#shengyu").val(shengyu);
}

//隐藏
function hehehide() {
	$("#yusuan").hide();
}

//========================================zTree显示
var id, pId, name, rootId, belongLayer, subId, month;

//自动组装树形结构
var setting = {
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick
	}
};
//读取树形数据
$(document).ready(function() {
	mfzTree();
});

var mfzTree = function() {
	$.ajax( {
		url : 'SubMonthMoneyAction!findSummByRootId.action',
		data : {
			id : "${param.rootId}",
			pageStatus : "${param.pageStatus}"
		},
		type : 'post',
		dataType : 'json',
		cache : true,
		success : function(data) {
			var zNodes = [];
			var doc = data.smmList;
			var smm = data.smMoney;
			//标题
		$("#title").html(smm.name + "填报");
		//填充属性结构
		$(doc).each(function() {
			zNodes.push( {
				id : $(this).attr('id'),
				name : $(this).attr('name'),
				budgetMonth : $(this).attr('budgetMonth'),
				pId : $(this).attr('fatherId'),
				rootId : $(this).attr('rootId'),
				belongLayer : $(this).attr('belongLayer'),
				open : true,
				click : false
			});
		});
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	},
	error : function() {
		alert("服务器异常!");
	}
	});
};
//点击回调函数
function onClick(event, treeId, treeNode, clickFlag) {
	$("#yusuan").hide();
	if (treeNode.isParent) {
		alert("请选择下层科目填报!");
		//显示添加Div
		$("#addSmm").hide();
		return false;
	}
	//计算是否填报
	//获得当前月份
	var date = new Date();
	var year = date.getFullYear();
	var nowmonth = date.getMonth() + 1;
	if (nowmonth < 10) {
		nowmonth = "0" + nowmonth;
	}
	var nowDate = year + "-" + nowmonth;//当前月份
	var start = new Date(nowDate);
	var budgetMonth = treeNode.budgetMonth;//申报月份
	var end = new Date(budgetMonth);
	//if (start < end) {
	//	$("#showMessagespan").text("当前可填报" + budgetMonth + "月份的计划内或计划外预算!");
	//} else if (nowDate == budgetMonth) {
	//	$("#showMessagespan").text("当前仅可填报" + budgetMonth + "月份的计划外预算!");
	//	$("#neijhStatus").removeAttr("checked");
	//	$("#neijhStatus").attr("disabled", "disabled");
	//	$("#waijhStatus").attr("checked", "checked");
	//	heheshow();
	//} else if (start > end) {
	//	alert("当前已不可填报" + budgetMonth + "月份的任何预算!");
	//	$("#addForm").hide();
	//}

	//显示添加Div
	$("#addSmm").show('slow');
	//赋值
	$("#id").val(treeNode.id);
	$("#rootId").val(treeNode.rootId);
	$("#name").val(treeNode.name);
	$("#budgetMonth").val(treeNode.budgetMonth);
	//清空数据
	$("#accountMoney").val("");
	$("#budgetDetail").val("");
	$("#showMessage").html("");

	//显示申报历史明细
	subId = treeNode.id;
	month = budgetMonth;
	showHistory();
}

/***
 * 显示历史填报
 */
function showHistory() {
	$('#showHistory').empty();//清空表格
	$
			.ajax( {
				url : 'SubMonthMoneyAction!findDeptMonthBudget.action',
				data : {
					id : subId,
					budgetMonth : month
				},
				type : 'post',
				dataType : 'json',
				cache : true,
				success : function(mes) {
					var data = mes.data;
					//添加表头
					var trtitle = "<tr><th>序号</th><th>科目名称</th><th>预算月份</th><th>计划类型</th><th>提交时间</th>"
							+ "<th>状态</th><th>说明</th><th>审核意见</th><th>操作</th></tr>";
					//填充属性结构
					$
							.each(
									data,
									function(i, object) {
										trtitle += "<tr><th>"
												+ (i + 1)
												+ "</th><th>"
												+ object.name
												+ "</th><th>"
												+ object.budgetMonth
												+ "</th><th>"
												+ object.jhStatus
												+ "</th><th>"
												+ object.subTime
												+ "</th><th>"
												+ object.status
												+ "</th><th>"
												+ object.budgetDetail
												+ "</th><th>"
												+ object.auditResult
												+ "</th><th><a href='SubMonthMoneyAction!findDmBudget.action?id=${pageDmBudget.id}'></a>"
												+ object.auditResult
												+ "</th></tr>";
									});
					$('#showHistory').append(trtitle);
				},
				error : function() {
					alert("服务器异常!");
				}
			});
}

//添加表单
function submitForm(formId, status) {
	var testValue = document.getElementById("accountMoney").value;
	var shengyu = document.getElementById("shengyu").value;
	//获得计划类型
	var jihuaType = $("input[name='deptMonthBudget.jhStatus']:checked").val();
	var bool = true;
	if (testValue == "") {
		alert("金额不能为空");
		bool = false;
	}

	if (jihuaType == "wai") {
		if (shengyu == "") {
			alert("请选择年度预算!");
			bool = false;
		} else if (parseFloat(testValue) > parseFloat(shengyu)) {
			alert("预算金额不能大于年度预算剩余金额!");
			bool = false;
		}
	}

	if (bool) {
		$("#showMessage").html("");
		if (status == "add") {
			$.ajax( {
				type : "POST",
				url : 'SubMonthMoneyAction!addDeptMonthBudget.action',
				dataType : 'json',
				data : $("#" + formId).serialize(),

				cache : false,//防止数据缓存
				success : function(mes) {
					if (mes.success) {
						$("#showMessage").html("填报成功!");
						alert("填报成功!");
						showHistory();
					} else {
						$("#showMessage").html("填报失败!原因:" + mes.message);
						alert("填报失败!原因:" + mes.message);
					}
				}
			});
		}
		id = null;
		pId = null;
		name = null;
		rootId = null;
	}

};
//=================================== zTree显示结束
</script>
	</body>
</html>
