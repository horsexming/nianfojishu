<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
//表单检查
function checkForm() {
	var cardId = document.getElementById("cardId");
	var userName = document.getElementById("userName");
	var dept = document.getElementById("dept");
	var code = document.getElementById("code");
	var gangweigongzi = document.getElementById("gangweigongzi");
	var baomijintie = document.getElementById("baomijintie");
	var dhbt = document.getElementById("dhbt");
	var jixiaokaohegongzi = document.getElementById("jixiaokaohegongzi");
	var gongjijin = document.getElementById("gongjijin");
	var ssBase = document.getElementById("ssBase");
	var jinenggongzi = document.getElementById("jinenggongzi");
	var gonglinggongzi = document.getElementById("gonglinggongzi");
	var isOnWork = document.getElementById("isOnWork");

	if (code.value == "") {
		alert("工号不能为空!");
		code.focus();
		return false;
	} else if (cardId.value == "") {
		alert("卡号不能为空!");
		cardId.focus();
		return false;
	} else if (userName.value == "") {
		alert("员工名称不能为空!");
		userName.focus();
		return false;
	} else if (dept.value == "") {
		alert("所属部门不能为空!");
		dept.focus();
		return false;
	} else if (gangweigongzi.value == "") {
		alert("岗位工资不能为空!");
		gangweigongzi.focus();
		return false;
	} else if (baomijintie.value == "") {
		alert("保密津贴不能为空!");
		baomijintie.focus();
		return false;
	} else if (dhbt.value == "") {
		alert("补贴不能为空!");
		dhbt.focus();
		return false;
	} else if (jinenggongzi.value == "") {
		alert("技能工资不能为空!");
		jinenggongzi.focus();
		return false;
	} else if (gonglinggongzi.value == "") {
		alert("岗位工资不能为空!");
		gonglinggongzi.focus();
		return false;
	} else if (jixiaokaohegongzi.value == "") {
		alert("绩效考核工资不能为空!");
		jixiaokaohegongzi.focus();
		return false;
	} else if (gongjijin.value == "") {
		alert("公积金基数不能为空!");
		gongjijin.focus();
		return false;
	} else if (ssBase.value == "") {
		alert("社保基金基数不能为空!");
		ssBase.focus();
		return false;
	} else if (isOnWork.value == "") {
		alert("修改说明不能为空!");
		isOnWork.focus();
		return false;
	} else {
		return true;
	}

}

//展示历史模版
var num = 0;
var oldobj;
var count = 0;
function showOldWageS(obj, code) {
	for ( var i = 0; i < num; i++) {
		$("#showWs").remove();
	}
	if (oldobj == obj) {
		if (count == 1) {
			count = 0;
			return;
		}
	} else {
		count = 0;
	}
	oldobj = obj;
	count++;
	$
			.ajax( {
				type : "POST",
				url : "WageStandardAction!findOldWageSByCode.action",
				data : "code=" + code,
				dataType : "json",
				success : function(msg) {
					var oldWageS = "";
					$(msg)
							.each(
									function(i, n) {
										num++;
										oldWageS += "<tr style='background: #FFFFCC' id='showWs'><th>"
												+ (i + 1)
												+ "</th><th>"
												+ n.userName
												+ "</th><th>"
												+ n.code
												+ "</th><th>"
												+ n.dept
												+ "</th><th>"
												+ n.gangweigongzi
												+ "</th><th align='right'>"
												+ n.gongjijin
												+ "("
												+ n.inputDate
												+ ")</th><th></th></tr>";
									});
					$(obj).after(oldWageS);
				}
			});
}

//保证未选中和选中时，试算的公积金的一致
function chageGjjName(obj) {
	var checkName = obj.name;
	var index = checkName.substring(checkName.indexOf("[") + 1, checkName
			.indexOf("]"));
	if (obj.checked == true) {
		var len = checkName.length - 2;
		var first = checkName.substring(0, len);
		$("#shisuan_" + index).attr("name", first + "gongjijin");
		$("#span_" + index).html("<font color='red'>已选中</font>");
	} else {
		$("#shisuan_" + index).attr("name", "");
		$("#span_" + index).html("");

	}
}

function checkedbox(boxid) {
	$("#check_" + boxid).attr("checked", "checked");
	chageGjjName(document.getElementById("check_" + boxid));
}

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll") {
					chageNum(checkBox);
				}
			}
		}
	}
}
//var showcount = "${pageSize}";
var showcount = 0;;
function chageNum(obj) {
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		var status = true;
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.id != "checkAll") {
					if (checkBox.checked == false) {
						status = false;
						break;
					}
				}
			}
		}
		if (status == true) {
			checkAll.checked = true;
		}
		showcount++;
	} else if (showcount == 0 && check.checked == false) {
		showcount = 0;
	} else {
		if (checkAll.checked == true) {
			checkAll.checked = false;
		}
		showcount--;
	}
	$("#showCount").html(showcount);
	chageGjjName(obj);
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<!-- 查询所有工资模板 -->
				<div align="center" id="findWageStrandar">
					<%--<form action="WageStandardAction!findWSByCondition.action"
						method="post" style="margin: 0px;">
						<input type="hidden" name="wageStandard.standardStatus" value="默认">
						<input type="hidden" name="pageStatus" value="${pageStatus}">
						<table class="table">
							<tr>
								<th colspan="6">
									工资模板查询
								</th>
							</tr>
							<tr>
								<td align="right">
									姓名:
								</td>
								<td align="left">
									<input name="wageStandard.userName" />
								</td>
								<td align="right">
									工号:
								</td>
								<td align="left">
									<input name="wageStandard.code" />
								</td>
							</tr>
							<tr>
								<td align="right">
									部门:
								</td>
								<td align="left">
									<select name="wageStandard.dept" id="selectDept"
										style="width: 155px">
										<option></option>
									</select>
								</td>
								<td align="right">
									卡号:
								</td>
								<td align="left">
									<input name="wageStandard.cardId">
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input value="查询" type="submit"
										style="width: 100px; height: 50px">
									<input value="重置" type="reset"
										style="width: 100px; height: 50px">
								</td>
							</tr>
						</table>
					</form>

					--%>
					<form action="WageStandardAction!updateWageSGjj.action"
						method="post" style="margin: 0px;">
						${errorMessage}
						<table class="table">
							<tr height="50px">
								<th colspan="8" align="center">
									公积金调整
									<br />
									<div align="right">
										<div align="left" style="width: 400px">
											1、点击查看历史工资模版;
											<br />
											2、时间范围为最近一年或不足一年(新员工)
										</div>
										共选中:
										<font color="red"><span id="showCount">0</span> </font>条
										<input type="submit" value="调整" class="input">
									</div>
								</th>
							</tr>
							<tr bgcolor="#c0dcf2" height="50px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									姓名
								</th>
								<th align="center">
									工号
								</th>
								<th align="center">
									部门
								</th>
								<th align="center">
									岗位工资
								</th>
								<th align="center">
									默认公积金
								</th>
								<th align="center">
									试算公积金
								</th>
								<th align="center">
									全选
									<br />
									<input type="checkbox" onclick="chageAllCheck(this)"
										id="checkAll">
								</th>
							</tr>
							<s:iterator value="WsList" id="pageWsList" status="pageStatus">
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 30px;" onmouseout="outBgcolor(this,'')"
									onclick="clickBgcolor(this);showOldWageS(this,'${pageWsList.code}')">
									<td>
										<s:property value="#pageStatus.index+1" />
									</td>
									<td style="width: 200px">
										${pageWsList.userName}
									</td>
									<td>
										${pageWsList.code}
									</td>
									<td style="width: 150px">
										${pageWsList.dept}
									</td>
									<td style="width: 100px">
										${pageWsList.gangweigongzi}
									</td>
									<td style="width: 200px">
										${pageWsList.gongjijin}
									</td>
									<td align="left" style="width: 300px">
										<input type="text" id="shisuan_${pageStatus.index}"
											value="${pageWsList.shiyebaoxian}"
											onfocus="checkedbox('${pageStatus.index}')">
										<input id="check_${pageStatus.index}"
											name="WsList[${pageStatus.index}].id"
											value="${pageWsList.id}" type="checkbox"
											onclick="chageGjjName(this);chageNum(this)">
										<span id="span_${pageStatus.index}"></span>
										<br />
										<s:if test="#pageWsList.yingfagongzi!=null">
								计算公式:avg(${pageWsList.updateDate}-${pageWsList.inputDate})*${pageWsList.gjjBase}
								<%--<a
												href="WageAction!findWageByCondition.action?wage.code=${pageWsList.code}"
												target="_showWage">${pageWsList.yingfagongzi}</a>})
								--%>
										</s:if>
										<s:else>
									无历史工资,保持不变。
								</s:else>
									</td>
									<td>
										<input type="submit" value="调整"
											style="width: 50; height: 30px">
									</td>
								</tr>
							</s:iterator>
							<tr>
						</table>
						<input type="submit" value="调整" class="input">
					</form>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
