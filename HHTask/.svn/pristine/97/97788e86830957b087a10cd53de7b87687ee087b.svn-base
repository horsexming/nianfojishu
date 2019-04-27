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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					${companyInfo.shortName} 出入申请填报单
				</h3>
				<form action="iaoAction!saveIaoApp.action" method="post"
					onsubmit="return checkForm()">
					<table width="85%" class="table" id="complexselectedlist">
						<tbody>
							<tr>
								<th>
									申请对象
								</th>
								<td>
									<select id="personStyle" name="iaoApply.iaoPersonTyle"
										onChange="selectPerType()">
										<option value="本人">
											<b style="font-size: 13px;">本人</b>
										</option>
										<option value="他人(内部)">
											<b style="font-size: 13px;">他人(内部)</b>
										</option>
										<option value="他人(外部)">
											<b style="font-size: 13px;">他人(外部)</b>
										</option>
									</select>
								</td>
								<th>
									出入类别
								</th>
								<td>
									<select name="iaoApply.iaoStyle" id="iaoStyle">
										<option value="公出">
											公出
										</option>
										<option value="病假">
											病假
										</option>
										<option value="事假">
											事假
										</option>
										<option value="来访">
											来访
										</option>
									</select>

								</td>
							</tr>
							<tr>
								<th>
									进出人姓名
								</th>
								<td>
									<input type="text" id="username" name="iaoApply.username"
										value="<%=user.getName()%>" />
								</td>

								<th>
									<span id="bycard1">进出人卡号</span>
									<span id="byname1" style="display: none;">公司名称</span>
								</th>
								<td>
									<span id="bycard2"><input type="text" id="card"
											name="iaoApply.outCard" value="<%=user.getCardId()%>" /> </span>
									<span id="byname2" style="display: none;"><input
											type="text" name="iaoApply.dept" /> </span>
								</td>
							</tr>
							<tr>
								<th>
									出门时间
								</th>
								<td>
									<input class="Wdate" type="text" name="iaoApply.applyOutTime"
										size="15" id="outTime" 
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								</td>
								<th>
									返回时间
								</th>
								<td>
									<input class="Wdate" type="text" name="iaoApply.applyInTime"
										size="15" id="inTime" 
										onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<th>
									车牌号
								</th>
								<td>
									<input type="text" name="iaoApply.plateNum" />
								</td>

								<th>
									随从人员姓名
								</th>
								<td>
									<input type="text" name="iaoApply.followPerson" />
								</td>

							</tr>
							<tr>
								<th>
									原因说明
								</th>
								<td colspan="3">
									<textarea cols="88" name="iaoApply.result" id="explain"></textarea>
								</td>
							</tr>
							<tr>
								<th colspan="4">
									携带物品
								</th>
							</tr>
							<tr>
								<th>
									序号
								</th>
								<th>
									物品名称
								</th>
								<th>
									数量
								</th>
								<th>
									备注
								</th>

							</tr>
								<tr align="left">
									<th>
									1
									</th>
									<th>
										<input type="text"
											name="listCarryGoods[0].count"
											 />
									</th>
									<th>
										<input type="text" name="listCarryGoods[0].markId"
											 />
									</th>
									<th>
										<input type="text" id="h0"
											name="listCarryGoods[${se.index}].more"  />
									</th>
								</tr>
							<tr id="uploadtr">
								<tr>
									<th align="left">
										<input type="button" id="inforButton_1"
											onclick="saveHKInfor(this,1)" value="添加明细" />
									</th>
									<th width="29%" align="left">
										<input id="deleteItem" style="display: none;" type="button"
											id="inforButton_2" onclick="delInfor()" value="删除明细" />
									</th>
									<th colspan="2">

									</th>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<input type="button" value="提交"
											onclick="actionSubmit(this.form)"
											style="width: 80px; height: 40px;" align="top">
										&nbsp;&nbsp;
										<input type="reset" value="取消"
											style="width: 80px; height: 40px;" align="top">
									</td>
								</tr>
								<tbody>
					</table>

					<input type="hidden" id="iaoUsername" name="iaoUsername"
						value="<%=user.getName()%>" />
					<input type="hidden" id="iaoUsercard" name="iaoUsercard"
						value="<%=user.getCardId()%>" />
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
	</body>

	<script type="text/javascript">
var inforDivHTML = "";
var lineCount = 1;
var begAddLineNum = 6;
function saveHKInfor(obj, few) {
	var _tbody = document.getElementById("complexselectedlist").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素
	var _tr = document.createElement("tr");
	_tbody.insertBefore(_tr, uploadtr);
	++begAddLineNum;
	//if(local.complexselectedlist.value.length != 0) {		
	var linNum = lineCount + 1;
	var x = _tr.insertCell(0);
	x.innerHTML = " <sapan" + linNum + "</span>";
	var x1 = _tr.insertCell(1);
	x1.innerHTML = "<input type=\"text\" name=\"listCarryGoods[" + lineCount
			+ "].goodsName\" >";
	var x2 = _tr.insertCell(2);
	x2.innerHTML = "<input type=\"text\" name=\"listCarryGoods[" + lineCount
			+ "].count\" >";
	var x3 = _tr.insertCell(3);
	x3.innerHTML = "<input type=\"text\" name=\"listCarryGoods[" + lineCount
			+ "].more\" >";
	//执行下拉
	//alert(lineCount);
	lineCount++;
	document.getElementById("deleteItem").style.display = "block";
	//} 
}
function delInfor() {
	//alert(begAddLineNum);
	complexselectedlist.deleteRow(begAddLineNum + 1);
	begAddLineNum--;
	lineCount--;
	if (begAddLineNum < 7) {
		document.getElementById("deleteItem").style.display = "none";
	}
}
//出门类型（本人/他人）
function selectPerType() {
	var iaoType = document.getElementById("personStyle").value;
	var iaousername = document.getElementById("iaoUsername").value;
	var iaousercard = document.getElementById("iaoUsercard").value;
	if ("本人" == iaoType) {
		document.getElementById("username").value = iaousername;
		document.getElementById("card").value = iaousercard;
		document.getElementById("bycard1").style.display = "block";
		document.getElementById("bycard2").style.display = "block";
		document.getElementById("byname1").style.display = "none";
		document.getElementById("byname2").style.display = "none";
	} else if ("他人(内部)" == iaoType) {
		document.getElementById("username").value = "";
		document.getElementById("card").value = "";
		document.getElementById("bycard1").style.display = "block";
		document.getElementById("bycard2").style.display = "block";
		document.getElementById("byname1").style.display = "none";
		document.getElementById("byname2").style.display = "none";
	} else if ("他人(外部)" == iaoType) {
		document.getElementById("username").value = "";
		document.getElementById("card").value = "";
		document.getElementById("byname1").style.display = "block";
		document.getElementById("byname2").style.display = "block";
		document.getElementById("bycard1").style.display = "none";
		document.getElementById("bycard2").style.display = "none";
	}

}
//提交action
function actionSubmit(objForm) {
	objForm.action = "iaoAction!saveIaoApp.action";
	objForm.submit();
}
//提交验证
function checkForm() {
	var username = document.getElementById("username");
	var outTime = document.getElementById("outTime");
	var inTime = document.getElementById("inTime");
	var explain = document.getElementById("explain");

	if (username.value == "") {
		alert("出门人不能为空!");
		username.focus();
		return false;
	} else if (outTime.value == "") {
		alert("申请出门时间不能为空!");
		outTime.focus();
		return false;
	} else if (inTime.value == "") {
		alert("预计返回时间不能为空!");
		inTime.focus();
		return false;
	} else if (explain.value == "") {
		alert("说明不能为空!");
		explain.focus();
		return false;
	}
}
</script>

</html>
