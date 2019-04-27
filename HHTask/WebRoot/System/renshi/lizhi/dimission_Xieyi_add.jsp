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
		<div align="center" id="gongneng">
			<div align="center"
				style="width: 756px; height: 1086px; border: 0px solid #000000;">
				<%-- 
				style="width: 70%; height: 100%; border: 0px solid #000000;">
				--%>
				<form action="dimission_XieYiAction_add.action" method="post"
					onsubmit="return dimiss_ZYAdd()">
					<table class="table" align="center" id="contract">
						<tr>
							<th align="center" colspan="2" style="size: 20pt;">
								添加终止劳动关系协议书
								<input type="hidden" name="dimissionLog.id"
									value="${dimissionLog.id}" />
								<input type="hidden" name="users.id"
									value="${users.id}" />
								<font color="red">${errorMessage}</font>
							</th>
						</tr>
						<tr>
							<th colspan="2" align="right">
								编号：${number}
								<input type="hidden" name="dimissionXieYi.xieyi_number"
									value="${number}" />
								&nbsp;&nbsp;&nbsp;&nbsp;
							</th>
						</tr>
						<tr>
							<th align="left">
								&nbsp;甲方：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._Afang" id="_Afang" value="${companyInfo.name}"
									style="width: 60%; font-weight: bold; font-size: 14px;" />
							</th>
							<th align="left">
								&nbsp;法定代表人：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._AfangRepresentative"
									id="_AfangRepresentative" value="才明嵩"
									style="width: 60%; font-weight: bold; font-size: 14px;" />
							</th>
						</tr>
						<tr>
							<th colspan="2" align="left">
								&nbsp;地址：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._Aaddress" id="_Aaddress"
									value="上海安亭和静路1200号"
									style="width: 90%; font-weight: bold; font-size: 14px;" />
							</th>
						</tr>
						<s:if test="dimissionLog==null">
							<tr>
								<th colspan="2" align="left">
									&nbsp;乙方：
									<input class="horizontalLine" type="text"
										value="${users.name}"
										style="width: 75%; font-weight: bold; font-size: 14px;"
										name="dimissionXieYi._Bfang" id="_Bfang" style="width: 90%" />
								</th>
							</tr>
							<tr>
								<th align="left">
									&nbsp;身份证号：
									<input class="horizontalLine" type="text"
										value="${users.uid}" name="dimissionXieYi._Buid"
										id="_Buid" style="width: 75%" />
								</th>
								<th align="left">
									&nbsp;电话号码：
									<input class="horizontalLine" type="text"
										value="${users.password.phoneNumber}" name="dimissionXieYi._Btel"
										id="_Btel" style="width: 75%" />
								</th>
							</tr>
							<tr>
								<th colspan="2" align="left">
									&nbsp;户籍地址：
									<input class="horizontalLine" type="text"
										value="${users.password.presentAddress}" name="dimissionXieYi._Baddress"
										id="_Baddress" style="width: 75%" />
								</th>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<th colspan="2" align="left">
									&nbsp;乙方：
									<input class="horizontalLine" type="text"
										value="${dimissionLog.name}"
										style="width: 75%; font-weight: bold; font-size: 14px;"
										name="dimissionXieYi._Bfang" id="_Bfang" style="width: 90%" />
								</th>
							</tr>
							<tr>
								<th align="left">
									&nbsp;身份证号：
									<input class="horizontalLine" type="text"
										value="${dimissionLog.uid}" name="dimissionXieYi._Buid"
										id="_Buid" style="width: 75%" />
								</th>
								<th align="left">
									&nbsp;电话号码：
									<input class="horizontalLine" type="text"
										value="${dimissionLog.tel}" name="dimissionXieYi._Btel"
										id="_Btel" style="width: 75%" />
								</th>
							</tr>
							<tr>
								<th colspan="2" align="left">
									&nbsp;户籍地址：
									<input class="horizontalLine" type="text"
										value="${dimissionLog.address}" name="dimissionXieYi._Baddress"
										id="_Baddress" style="width: 75%" />
								</th>
							</tr>
						</s:else>
						<tr>
							<th colspan="2" align="left">
								&nbsp;函件送达地址：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._BrealAddress" id="_BrealAddress"
									style="width: 75%" />
							</th>
						</tr>
						<tr>
							<td colspan="2">
								<SPAN style="font-size: 10px; font-weight: bold;">（乙方确认上述联系方式和地址真实有效，如今后按上述方式和地址不能联系乙方将由乙方承担相应法律责任）</SPAN>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								协议条款
							</th>
						</tr>
						<tr>
							<s:iterator value="provisionlist" id="provil" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="left">
								</s:if>
								<s:else>
									<tr align="left">
								</s:else>
								<td align="center" colspan="2" style="font-weight: bold;">
									<s:property value="#pageStatus.index+1" /><br/>
									<textarea rows="5" cols="100" name="proContent">${provil.content}</textarea><br/>
									<input type="button" value="删除" style="width: 60px"
										onclick="delTr(this.parentElement.parentElement);" />
									<input type="button" name="b1" value="↑上移" style="width: 60px"
										onClick="movetr(this.parentElement.parentElement)">
									<input type="button" name="b2" value="↓下移" style="width: 60px;"
										onClick="movetr(this.parentElement.parentElement)">
								</td>
							</s:iterator>
						</tr>
						<tr id="uploadtr">
							<td colspan="2" align="right">
								<input type="button" value="添加条款" onclick="addProvision()"
									style="width: 80; height: 30px;">
							</td>
						</tr>
						<tr>
							<th align="left">
								甲方：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._Afang2" id="_Afang2" value="${companyInfo.name}"
									style="width: 75%; font-weight: bold; font-size: 14px;" />
							</th>
							<th rowspan="2" align="left">
								乙方：
								<input class="horizontalLine" type="text"
									name="dimissionXieYi._Bfang2" id="_Bfang2"
									style="width: 75%; font-weight: bold; font-size: 14px;" />
							</th>
						</tr>
						<tr>
							<th align="left">
								法定代表人：
							</th>
						</tr>

						<tr>
							<th align="left">
								日 期：
								<input class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									type="text" name="dimissionXieYi._AaddDate" id="_AaddDate"
									style="width: 60%" />
							</th>
							<th align="left">
								日 期：
								<input class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
									type="text" name="dimissionXieYi._BaddDate" id="_BaddDate"
									style="width: 60%" />
							</th>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" value="保存"
									style="width: 80px; height: 40px;">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="reset" value="重置"
									style="width: 80px; height: 40px;">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

function dimiss_ZYAdd() {
	if (!validateText("_Afang", "甲方")) {
		return false;
	}
	if (!validateText("_Aaddress", "甲方地址")) {
		return false;
	}
	if (!validateText("_Bfang", "乙方")) {
		return false;
	}
	if (!validateText("_Buid", "乙方身份证")) {
		return false;
	}
	if (!validateText("_Baddress", "乙方户籍地址")) {
		return false;
	}
	if (!validateText("_Btel", "乙方电话")) {
		return false;
	}
	if (!validateText("_BrealAddress", "乙方函件送达地址")) {
		return false;
	}
	if (!validateText("_Afang2", "甲方")) {
		return false;
	}
}

function validateText(id, textname) {
	var textValue = $("#" + id).val();
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}

var provisionSize = parseInt("<s:property value='provisionlist.size' />", 0);
function addProvision() {
	var _tbody = document.getElementById("contract").tBodies[0];//获得第一个tbody
	var uploadtr = document.getElementById("uploadtr");//将要在该Tr之前添加元素

	var _tr = document.createElement("tr");
	_tr.onmouseover = function() {
		chageBgcolor(this);
	}
	if (provisionSize % 2 == 1) {
		_tr.style.background = "#e6f3fb";
		_tr.onmouseout = function() {
			outBgcolor(this, '#e6f3fb');
		}
	} else {
		_tr.onmouseout = function() {
			outBgcolor(this, '');
		}
	}
	var td1 = document.createElement("td");//序号
	td1.align = "center";
	td1.colSpan = 2;//列占2格位置
	var word = document.createTextNode(provisionSize + 1);//序号

	var textarea1 = document.createElement("textarea");//创建输入框
	textarea1.name = "proContent";
	textarea1.rows = "5";
	textarea1.cols = "100";
	
	//var checkbox1 = document.createElement("input");//复选框
	//checkbox1.type = "checkbox";
	//checkbox1.name = "provisionId";
	//checkbox1.value = provisionSize;
	//checkbox1.onclick = function() {
	//	chageCheckbox(this);
	//}
	var delInput = document.createElement("input");//删除
	delInput.type = "button";
	delInput.value = "删除";
	delInput.style.width = "60px";
	delInput.onclick = function() {
		_tbody.deleteRow(_tr.rowIndex);
		//provisionSize--;
	}
	var br1 = document.createElement("br");//换行
	var upInput = document.createElement("input");//上移
	upInput.type = "button";
	upInput.name = "b1";
	upInput.value = "↑上移";
	upInput.style.width = "60px";
	upInput.onclick = function() {
		movetr(_tr);
	}
	var downInput = document.createElement("input");//xia移
	downInput.type = "button";
	downInput.name = "b2";
	downInput.value = "↓下移";
	downInput.style.width = "60px";
	downInput.onclick = function() {
		movetr(_tr);
	}

	_tbody.insertBefore(_tr, uploadtr);
	_tr.insertBefore(td1, null);
	td1.insertBefore(word, null);
	td1.insertBefore(textarea1, null);
	td1.insertBefore(br1,null);
	td1.insertBefore(delInput, null);
	td1.insertBefore(upInput, null);
	td1.insertBefore(downInput, null);
	provisionSize++;
}
//删除条款(已存在条款)
function delTr(_tr) {
	var _tbody = document.getElementById("contract").tBodies[0];//获得第一个tbody
	_tbody.deleteRow(_tr.rowIndex);
}
//移动行
function movetr(trObject) {
	var _table = document.getElementById("contract");
	var currindex = trObject.rowIndex;
	if (event.srcElement.name == "b1" && currindex > 0) {
		_table.moveRow(currindex, currindex - 1);
		currindex -= 1;
		event.srcElement.focus();
	}
	if (event.srcElement.name == "b2") {
		_table.moveRow(currindex, currindex + 1);
		currindex += 1;
	}
}

</script>
	</body>
</html>
