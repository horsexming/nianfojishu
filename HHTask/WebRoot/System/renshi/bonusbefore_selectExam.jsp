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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="oaAppDetailAction!findExamHistoryList.action"
						style="color: #ffffff">审批历史记录</a> &nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">

				<form
					action="oaAppDetailAction!updateExamDetail.action"
					method="post">

					<table class="table">
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
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<th align="center">
								序号
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								总金额
							</th>
							<th align="center">
								班组 
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								添加人姓名
							</th>
							<th align="center">
								时间
							</th>
							<th align="center" style="width: 40px;">
								<input type="checkbox" id="checkAll"
									onclick="chageAllCheck(this)">
								全选
							</th>
						</tr>

						<s:iterator value="list" status="se" id="detail">
							<s:if test="#se.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#se.index+1" />
							</td>
							<td>
								${detail.bonusmoneymonth}
							</td>
							<td>
								${detail.bonusmoneytotalmoney}
							</td>
							<td>
								${detail.bonusmoneyteam}
							</td>
							<td>
								${detail.bonusmoneystatus}
							</td>
							<td>
								${detail.bonusmoneyname}
							</td>
							<td>
								${detail.bonusmoneydatatime}
							</td>
							<td>
								<input type="checkbox"  
									name="detailSelect" value="${detail.id}" onclick="chageNum(this)" 
									 ><a href="CircuitRunAction_findAduitPage.action?id=${detail.epId}"">审批动态</a>
							</td>

							</tr>
						</s:iterator>
						<tr>
							<td colspan="12" align="right"
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
<%--										<font color="red">合计<label id="allMoney"></label>元</font>--%>
										<input id="ok" class="input"  style="width:120px;" align="top" type="submit" value="批量审批通过" onclick="chageType(this,this.form)"/>
    									<input id="ng" class="input" align="top" type="submit" value="批量驳回" onclick="chageType(this,this.form)" />
    									 共 <s:property value="total"/> 页 第 <s:property value="cpage"/> 页
        								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page" theme="number"/>
  			
								<br>
								<br>
							</td>
						</tr>

					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<script type="text/javascript">
    	function chageType(obj,form){
    		if(obj.id=="ok"){
    			//form.action="oaAppDetailAction!updateExamDetail.action?tag=ok";
    			form.action="BonusAction!updateExamBonus.action?tag=ok";
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="BonusAction!updateExamBonus.action?tag=ng";
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
	document.getElementById("allMoney").innerHTML=money;
}
</script>
</html>
