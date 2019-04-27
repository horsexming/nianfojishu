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
				<h3>
					${companyInfo.name}采购申报平台
					<br>
					<font color="red">${successMessage}</font>
				</h3>
				<form action="machineSparePartAction_nextMonthOa.action" id="oaform" method="post"
					onsubmit="return checkForm()">
					<input type="hidden" name="oadetail.appayTag"
						value="${oadetail.appayTag}">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
<%--							<tr>--%>
<%--								<th width="20%">--%>
<%--									预算月份--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<select name="oadetail.budgetMonth" style="width: 130px;"--%>
<%--										id="detailPlanMon"--%>
<%--										onMouseOver="createDept('detailPlanMon','oaAppDetailAction!findSelectMon.action')"--%>
<%--										onchange="selectSubjects()">--%>
<%--										<option value="">--%>
<%--											选择月份--%>
<%--										</option>--%>
<%--									</select>--%>
<%----%>
<%--								</td>--%>
<%--								<th width="20%">--%>
<%--									预算科目--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<select name="oadetail.deptMonthBudgetID" style="width: 150px;"--%>
<%--										id="subject">--%>
<%--									</select>--%>
<%--								</td>--%>
<%--							</tr>--%>
							
<%--							<tr>--%>
<%--								<th colspan="2">--%>
<%--									手动添加申购明细--%>
<%--								</th>--%>
<%--								<th colspan="2" align="left">--%>
<%--									<input type="file" name="uploadDetail">--%>
<%--									<br />--%>
<%--									<input type="button" value="导入EXCEL数据" id="start"--%>
<%--										onclick="strat(this.form)">--%>
<%--									<span style="font-size: 11px; color: red; font-weight: bold;">批量上传,请先选择预算月份和科目</span>--%>
<%--								</th>--%>
<%----%>
<%--							</tr>--%>
							
							<tr>
								<th>
									到货期限
								</th>
								<td>
									<input class="Wdate" type="text" name="oadetail.detailArrDate"
										size="15"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								</td>
								<th>
									是否加急
								</th>
								<td>
									<input type="radio" id="jiaji1" onclick="chagebaoxiaoClass()"
										name="oadetail.detailIsBusy" value="不加急" checked="checked" />
									不加急
									<input type="radio" id="jiaji2" " name="oadetail.detailIsBusy"
										value="加急" />
									加急
								</td>
							</tr>
							<tr>
								<th>
									申报时间
								</th>
								<td>
									<input class="Wdate" type="text" name="oadetail.detailAppDate"
										size="15"
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
							<tr>
								<th>
									计划依据
								</th>
								<td colspan="3">
									<textarea cols="88" name="oadetail.detailPlanAcco"
										id="detailPlanAcco"></textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<input type="submit" value="提交"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;&nbsp;
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
function changeF()  
    {  
            // 从下面的赋值可以的值，你在action 中只要得到name =“ccdd” 的值就可以了。  
            document.getElementById('ccdd').value=document.getElementById('detailFormat').options[document.getElementById('detailFormat').selectedIndex].value;  
    }  
$(function() {
	getUnit("danwei");
})
var text = "";
function writeSelect(obj) {
	text = text + String.fromCharCode(event.keyCode);
	var index = obj.selectedIndex; //序号，取当前选中选项的序号
	obj.options.length = 0;//清除所有option 
	obj.options.add(new Option(text, text)); //这个兼容IE与firefox
}
//下拉预算月份
function selectMonth() {
	createDept("detailPlanMon", "oaAppDetailAction!findSelectMon.action");
}
function selectSub() {
	alert("");
}
//显示项目编号
function showProjectNO() {
	$("#projectNO").show();
	var tag = "projectNO";
	//下拉项目菜单
	$.ajax( {
		type : "POST",
		url : "ProjectManage_findAllProMan.action",
		dataType : "json",
		success : function(msg) {
			$("#detailItemId").append("<option value=''></option>");
			$.each(msg, function(i, n) {
				$("#detailItemId").append(
						"<option value='" + n.id + "'>" + n.projectNum + "("
								+ n.projectName + ")</option>");
			});
		}
	});
}
//隐藏项目编号
function hiddenProjectNO() {
	$("#projectNO").hide();
}
var inforDivHTML = "";
var lineCount = 1;
var begAddLineNum = 6;
//选择部门
var planMonth = $("#planMonth").val();
function selectDept(few) {
	var budgetDept = $("#budgetDept");
	var budgetDetpR = document.getElementsByName("baoxiaodan.isSelfDept");
	for ( var i = 0; i < budgetDetpR.length; i++) {
		if (budgetDetpR[i].checked)
			var budgetDept = budgetDetpR[i].value;
	}
	var id = "course" + few;
	createDept(id, "BaoXiaoDanAction!findchildDept.action?tag=" + budgetDept
			+ "&planMonth=" + planMonth);
	selectSubjects(few);
}
//选择科目
function selectSubjects() {
	var planMonth = $("#detailPlanMon").val();
	var tag = "planMonth";
	$.ajax( {
		type : "POST",
		url : "oaAppDetailAction!findchildSubjects.action",
		data : {
			tag : tag,
			planMonth : planMonth
		},
		dataType : 'json',
		success : function(data) {
			$("#subject").empty();
			$(data).each(
					function() {
						$(
								"<option value='" + this.id + "'>" + this.name
										+ "(" + this.realMoney + "/"
										+ this.accountMoney + ")</option>")
								.appendTo("#subject");
					});
		}
	});

}
//选择物品名称
function selectGoodsName() {
	var childClass = $("#detailChildClass").val();
	var goodsName = $("#detailAppName");
	//判断是不是项目
	var proStyle = $("#budgetDept2").val();//是不是项目申请
	var proNO = $("#detailItemId").val(); //项目id
	var url = "oaAppDetailAction!findchildClass.action?tag=" + childClass;
	if ("项目申购" == proStyle && "工装" == childClass) {
		url = "oaAppDetailAction!findchildClass.action?powerTag=project&tag="
				+ proNO;
	}

	url = encodeURI(url);
	$("#detailAppName").empty();
	createDept('detailAppName', url);

}
//选择规格
function selectFormat() {
	var proStyle = $("#budgetDept2").val();//是不是项目申请
	var proNO = $("#detailItemId").val(); //项目编号

	var childClass = $("#detailChildClass").val();
	var goodsName = $("#detailAppName").val();//物品名称
	var detailFormat = $("#detailFormat");
	var url = "oaAppDetailAction!findFormat.action?tag=" + childClass
			+ "&powerTag=" + goodsName;
	if ("项目申购" == proStyle && "工装" == childClass) {
		url = "oaAppDetailAction!findFormatByProject.action?tag=" + proNO
				+ "&powerTag=" + goodsName;
	}
	url = encodeURI(url);
	$("#detailFormat").empty();
	createDept('detailFormat', url);
}
//提交验证
function checkForm() {
	var detailPlanMon = $("#detailPlanMon");//预算月份
	var subject = $("#subject");//预算科目

	if (detailPlanMon.val() == "") {
		alert("预算月份不能为空!");
		detailPlanMon.focus();
		return false;
	} else if (subject.val() == "") {
		alert("预算科目不能为空!");
		subject.focus();
		return false;
	} 
	return true;
}
//比较预算金额与申报金额大小
function compareCount(objForm) {
	checkForm();
	var detailBudgetMoney = $("#detailBudgetMoney");//预算单价
	var detailCount = $("#detailCount");//数量
	//判断申报金额与预算金额大小
	var money = parseFloat(detailBudgetMoney.val())
			* parseFloat(detailCount.val());
	var id = $("#subject").val();

	$.ajax( {
		type : "POST",
		url : 'oaAppDetailAction!compareBudgetCount.action',
		data : {
			id : id,
			money : money
		},
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(msg) {
			if ("NO" == msg) {
				alert("预算金额超出预算金额，请核实！");
				detailBudgetMoney.val(0);
				detailBudgetMoney.focus();
				return false;
			} else {
				objForm.action = "oaAppDetailAction!saveOADetail.action";
				objForm.submit();
			}
		}

	});

}
//上传文件
function strat(objForm) {
	document.getElementById("start").style.disabled = "disabled";
	startCheck();//验证
	objForm.action = "oaAppDetailAction!saveLotUpload.action";
	objForm.submit();
}
function startCheck() {
	var detailPlanMon = $("#detailPlanMon");//预算月份
	var subject = $("#subject");//预算科目
	if (detailPlanMon.val() == "") {
		alert("预算月份不能为空!");
		detailPlanMon.focus();
		return false;
	} else if (subject.val() == "") {
		alert("预算科目不能为空!");
		subject.focus();
		return false;
	}
}
//------------------------------------------
var count = 0;
function ondiv(obj) {
	count++;
	obj.style.background = "gray";
};
function outdiv(obj) {
	obj.style.background = "#ffffff";
	hidediv()

}
function hidediv() {
	count--;
	if (count == 0) {
		var showAll = document.getElementById("showAll");
		showAll.style.visibility = "hidden";
	}

}
function init() {
	count++;
	var detailAppName = document.getElementById("detailAppName");
	var showAll = document.getElementById("showAll");

	showAll.style.top = getTop(detailAppName) + 20;
	showAll.style.left = getLeft(detailAppName);
	if (detailAppName != "") {
		getAllNames();
	}
	showAll.style.visibility = "visible";
}
//获取元素的纵坐标（相对于窗口）
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}
//获取元素的横坐标（相对于窗口）
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}
function selectdiv(obj) {
	var detailAppName = document.getElementById("detailAppName");
	detailAppName.value = obj.innerHTML;

	var showAll = document.getElementById("showAll");
	showAll.style.visibility = "hidden";
	//onchange="selectFormat()"
	selectFormat();
}

//ajax获取所有的类似的全称   oaAppDetailAction!findchildClass.action
function getAllNames() {
	$("#detailFormat").empty();
	document.getElementById("ccdd").value="";
	$
			.ajax( {
				type : "POST",
				url : "oaAppDetailAction!getNameBymingcheng.action",
				dataType : "json",
				data : {
					tag : $("#detailChildClass").val(),powerTag:$("#detailAppName").val()
				},
				success : function(data) {
			$("#showAll").empty();
			$(data).each(
				function (){
					$("#showAll").append("<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)'>"
						+this+"</div>");
				});
		}
	});
}
</script>

</html>
