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
	text-align: left;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>

			<div align="center">
				<h3>
					${companyInfo.name}采购申报平台
				</h3>
				<div style="font-size: 13; color: red; font-weight: bold;">
					${message}
				</div>
				<form action="oaAppDetailAction!saveBatchOADetail.action"
					enctype="multipart/form-data" id="oaform" method="post"
					onsubmit="return checkForm()">
					<input type="hidden" name="oadetail.appayTag"
						value="${oadetail.appayTag}">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									申报类型
								</th>
								<td>
									<input type="radio" id="budgetDept" onclick="hiddenProjectNO()"
										name="oadetail.detailClass" value="普通申购" checked="checked" />
									普通申购
									<input type="radio" id="budgetDept2" onclick="showProjectNO()"
										name="oadetail.detailClass" value="项目申购" />
									项目申购
								</td>
								<th>项目编号</th>
								<td>
									<div id="projectNO" style="display: none;">
										<select name="oadetail.detailItemId" style="width: 150px;"
											id="detailItemId">
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<th colspan="2">
									手动添加申购明细
								</th>
								<th colspan="2" align="left">
									<input type="file" name="oadetailFile" id="fileDoc">
									<%--<br />
									--%><input type="button" value="导入EXCEL数据" id="start"
										onclick="strat(this.form)">
									<span style="font-size: 11px; color: red; font-weight: bold;">批量上传,请先选择预算月份和科目</span>
								</th>

							</tr>
							<tr>
								<td colspan="4" style="font-size: 12px;">
									<font color="red" size="5">注</font>:
									<a onClick="settingOaUploadTemplate()" style="cursor:pointer">批量上传模版下载</a>
									 <!-- href="${pageContext.request.contextPath}/upload/sheet/OA/OAUploadTemplate.xls" -->
									<a href="FileViewAction.action?FilePath=/upload/sheet/OA/OAUploadTemplate.xls&Refresh=true">/预览</a>
									<br />
									备注：
									<br>
									1、采购申请前提是有部门预算申请，且预算申请可用余额大于申购金额。
									<br />
									2、手动添加是单条提交申购明细，批量上传为上传EXCEL标准格式的申报明细。
									<br />
									3、批量上传的前提是要先选择预算月份和预算科目。
									<br />
									4、处理后统一跳转到个人申报历史记录里。 5、审批通过后采购进行打印。
									<br />
									6、采购入库后预算完成，入库单价和数量均不可超过申购的单价和数量。
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
 //上传文件
function strat(objForm) {
	document.getElementById("start").style.disabled = "disabled";
	//startCheck();//验证
	objForm.action = "oaAppDetailAction!saveLotUpload.action";
	if($("#fileDoc").val()==''){
		alert("请选择导入文件！谢谢");
	}else{
		objForm.submit();
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
	document.getElementById("ccdd").value = "";
	$.ajax( {
		type : "POST",
		url : "oaAppDetailAction!getNameBymingcheng.action",
		dataType : "json",
		data : {
			tag : $("#detailChildClass").val(),
			powerTag : $("#detailAppName").val()
		},
		success : function(data) {
			$("#showAll").empty();
			$(data).each(function() {
				$("#showAll").append(
				"<div onmouseover='ondiv(this)' onmouseout='outdiv(this)' onclick='selectdiv(this)'>"
				+ this
				+ "</div>");
			});
		}
	});
}

function settingOaUploadTemplate(){
	$.ajax({
		type:"POST",
		url:"oaAppDetailAction!settingOaUploadTemplate.action",
		success:function(msg){
		window.location.href="${pageContext.request.contextPath}/upload/sheet/OA/OAUploadTemplate.xls";
		},
		error:function(){
			alert("获取模板出错");
		}
	});
}
</script>

</html>
