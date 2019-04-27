<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
//预览
function processResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var ulMessage = XMLHttpReq.responseText;
			var ulMessageDIV = document.getElementById("operatingDiv");
			ulMessageDIV.innerHTML = ulMessage;
			chageDiv("block");
		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
window.onload = chageForm;
function chageForm() {
	var status = "${status}";
	if (status == "byBack") {
		document.getElementById("bybackForm").style.display = "block";
	}
}
</script>
	</head>
	<body bgcolor="#ffffff">
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							您正在预览模板:
							<font color="red">${template.name}</font>
						</td>
						<td align="right">
							<img alt="" src="images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
				</div>
			</div>
		</div>

		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; height: 50px; "
					align="left">
					<div
						style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
						align="left">
						
					</div>
					<div style="float: left; width: 45%; padding-top: 5px;"
						align="right">
						<a
							href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
							style="color: #ffffff">待审核模板</a>
						<a href="TemplateAction!findAllTemplate.action?status=byBack"
							style="color: #ffffff">审核历史</a>
					</div>
				</div>
				
				<div id="examineDiv" style="width: 100%">
					<form id="bybackForm"
						action="TemplateAction!findTDsByCondition.action"
						style="padding: 0px; margin: 0px; display: none" method="post">
						<input type="hidden" name="status" value="byBack">
						<input name="template.assObject" value="员工级" type="hidden" />
						<table width="100%" border="1">
							<tr>
								<th colspan="4">
									<s:if test="status=='audit'">
									待审核模板
								</s:if>
									<s:elseif test="status=='byBack'">
									审核历史
								</s:elseif>
								</th>
							</tr>
							<tr>
								<td align="right">
									模板名称:
								</td>
								<td>
									<input name="template.name" />
								</td>
								<td align="right">
									考核月份:
								</td>
								<td>
									<input class="Wdate" type="text" name="template.asstMouth"
										onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
								</td>
							</tr>
							<tr>
								<td align="right">
									考核部门:
								</td>
								<td>
									<select id="dept" onmouseover="createDept('dept')"
										name="template.dept" style="width: 155px">
										<option></option>
									</select>
								</td>
								<td align="right">
									状态:
								</td>
								<td>
									<select name="template.status" style="width: 100px">
										<option></option>
										<option>
											通过
										</option>
										<option>
											打回
										</option>
										<option>
											打分
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="6" align="center">
									<input type="submit" value="查询"
										style="width: 80px; height: 60px">
									<input type="reset" value="重置"
										style="width: 80px; height: 60px">
								</td>
							</tr>
						</table>
					</form>
					<form action="TemplateAction!updateExamTemplate.action" method="post">
					<table  class="table">
					<tr>
							<td align="right" colspan="12">
								<font color="red">共选择 <label id="peopleLabel">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
								<br>
								<br>
							</td>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								模板名称
							</th>
							<th align="center">
								考核部门
							</th>
							<th align="center">
								考核对象
							</th>
							<th align="center">
								考核月份
							</th>
							<th align="center">
								状态
							</th>
<%--							<th align="center">--%>
<%--								操作--%>
<%--							</th>--%>
							<th align="center" style="width: 40px;">
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</th>
						</tr>
						<s:iterator value="list" id="pageTemplate"
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
							<td align="center">
								${pageTemplate.name}
							</td>
							<td align="center">
								${pageTemplate.dept}
							</td>
							<td align="center">
								${pageTemplate.assObject}
							</td>
							<td align="center">
								${pageTemplate.asstMouth}
							</td>
							<td align="center">
								${pageTemplate.status}
							</td>
<%--							<td align="center">--%>
<%--								<a--%>
<%--									href="javascript:sendRequest('TemplateAction!PreviewTemplate.action?id=${pageTemplate.id}',processResponse)">预览</a>--%>
<%--								<s:if test="#pageTemplate.status=='审核'">--%>
<%--								/--%>
<%--								<a onclick="return window.confirm('该模板将被审核通过,确定提交?')"--%>
<%--										href="TemplateAction!updateTemplate.action?id=${pageTemplate.id}&status=2">同意</a>/--%>
<%--								<a onclick="return window.confirm('该模板将被打回,确定提交?')"--%>
<%--										href="TemplateAction!updateTemplate.action?id=${pageTemplate.id}&status=3">打回</a>--%>
<%--								</s:if>--%>
<%--							</td>--%>
							<td>
								<input type="checkbox"  
									name="detailSelect" value="${pageTemplate.id}"  onclick="chageNum(this)"
									 ><a href="CircuitRunAction_findAduitPage.action?id=${pageTemplate.epId}"">审批动态</a>
							</td>
							</tr>
						</s:iterator>
						<tr>
								<td colspan="9" align="right"
								style="font-weight: bold; padding-right: 40px">
								<input type="checkbox" id="checkAll2"
									onclick="chageAllCheck(this)">
								全选
							</td>
						</tr>
						<tr>
						<td align="right" colspan="12">
							<font color="red">共选择 <label id="peopleLabel2">
										${count}
									</label> <input type="hidden" id="propleText" name="peopleNum"
										style="width: 20px;" readonly="readonly"> 条记录</font>
										<input id="ok" class="input"  style="width:120px;" align="top" type="button" value="批量审批通过" onclick="chageType(this,this.form)"/>
    									<input id="ng" class="input" align="top" type="button" value="批量驳回" onclick="chageType(this,this.form)" />
    									
							<s:if test="errorMessage==null">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />
							</s:if>
							</td>
						</tr>
					</table>
			</form>
				</div>
			</div>
			<br>
			<div id="ulMessageDIV"
				style="border: solid #0170b8 1px; width: 100%; display: none">
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<script type="text/javascript">
    	function chageType(obj,form){
    		if(obj.id=="ok"){
    			form.action="TemplateAction!updateExamTemplate.action?tag=ok";
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="TemplateAction!updateExamTemplate.action?tag=ng";
    			form.submit();
    		}
    	}

 
	function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					chageNum(checkBox,checkBox.id);
				}
			}
		}
	}
}
		var num = "${count}";
		if (num == "") {
			num = 0;
		}
		var money = 0;
function chageNum(obj,obj2) {
	
	var check = obj;
	var checkAll = document.getElementById("checkAll");
	var checkAll2 = document.getElementById("checkAll2");
	if (check.checked == true) {
		var inputs = document.getElementsByTagName("input");
		money+=parseFloat(obj2);
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
		money=0;
		num = 0;
	} else {
		if (checkAll.checked == true || checkAll2.checked == true) {
			checkAll.checked = false;
			checkAll2.checked = false;
		}
		money=money-obj2;
		num--;
	}
	document.getElementById("peopleLabel").innerHTML = num;
	document.getElementById("peopleLabel2").innerHTML = num;
}
</script>
</html>
