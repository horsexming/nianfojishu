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
	<body onload="">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="ContractBonusAction!findAllContractBonus.action"
						target="_blank" style="color: #ffffff">查看所有总金额</a>
				</div>
			</div>
			
			<div align="center">
				<div id="showContractBonus">
					<form action="ContractBonusAction!updateExamContractBonus.action"
						method="post">
<%--						<table>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									部门:--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<select id="deptName2" style="width: 155px;"--%>
<%--										name="contractBonus.deptName"--%>
<%--										onclick="createDept('deptName2')">--%>
<%--										<option></option>--%>
<%--										<option value="${contractBonus.deptName}">--%>
<%--											${contractBonus.deptName}--%>
<%--										</option>--%>
<%--									</select>--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									月份:--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<input id="bonusMouth" class="Wdate" type="text"--%>
<%--										name="contractBonus.bonusMouth"--%>
<%--										onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"--%>
<%--										value="${contractBonus.bonusMouth}" />--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									状态:--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<select name="contractBonus.bonusStatus" style="width: 155px;">--%>
<%--										<option value="审核">--%>
<%--											审核--%>
<%--										</option>--%>
<%--									</select>--%>
<%--								</td>--%>
<%--								<td></td>--%>
<%--								<td>--%>
<%--									<input type="submit" value="查询" style="width: 100px;">--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</table>--%>
				

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
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								工号
							</th>
							<th align="center">
								卡号
							</th>
							<th align="center">
								姓名
							</th>
							<th align="center">
								部门
							</th>
							<th align="center">
								月份
							</th>
							<th align="center">
								总金额
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
						<s:iterator value="list" id="pageContractBonus"
							status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#pageStatus.index%2==1">
									<font>
								</s:if>
								<s:else>
									<font color="#c0dcf2">
								</s:else>
								<s:property value="#pageStatus.index+1" />
								</font>
							</td>
							<td>
								${pageContractBonus.code}
							</td>
							<td>
								${pageContractBonus.cardId}
							</td>
							<td>
								${pageContractBonus.userName}
							</td>
							<td>
								${pageContractBonus.deptName}
							</td>
							<td>
								${pageContractBonus.bonusMouth}
							</td>
							<td>
								${pageContractBonus.totalMoney}
							</td>
							<td>
								${pageContractBonus.bonusStatus}
							</td>
<%--							<td>--%>
<%--								<a--%>
<%--									href="ContractBonusAction!auditContractBonus.action?errorMessage=agree&contractBonusId=${pageContractBonus.id}">同意</a>/--%>
<%--								<a--%>
<%--									href="ContractBonusAction!auditContractBonus.action?errorMessage=back&contractBonusId=${pageContractBonus.id}">打回</a>--%>
<%--							</td>--%>
							<td>
								<input type="checkbox"  
									name="detailSelect" value="${pageContractBonus.id}" onclick="chageNum(this)" >
									<a href="CircuitRunAction_findAduitPage.action?id=${pageContractBonus.epId}">审批动态</a>
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
						<td align="right" colspan="9">
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
							<s:else>
								<td colspan="11" align="center" style="color: red">
							</s:else>
							</td>
						</tr>
					</table>
					${successMessage}
				</div>
		</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>

	</body>
	<script type="text/javascript">
	function chageType(obj,form){
    		if(obj.id=="ok"){
    			form.action="ContractBonusAction!updateExamContractBonus.action?tag=ok";
    			form.submit();
    		}else if (obj.id=="ng"){
    			form.action="ContractBonusAction!updateExamContractBonus.action?tag=ng";
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
