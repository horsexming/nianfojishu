
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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WaigouwaiweiPlanAction!findFllScz.action"
					method="post">
					<input name="pageStatus" value="sgsh" type="hidden">
					<table class="table">
						<tr>
							<th>
								订单号:
							</th>
							<td>
								<input id="orderPlanNum"
									name="waigouPlan.waigouOrder.planNumber"
									value="${waigouPlan.waigouOrder.planNumber}" />
							</td>
							<%--<th>
								件号:
							</th>
							<td>
								<input name="waigouPlan.markId" value="${waigouPlan.markId}" />
							</td>
							--%>
							<td>
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>
				<form action="WaigouwaiweiPlanAction!orderToshAndQs.action"
					method="post">
					<h2 style="color: red;">
						${errorMessage}
					</h2>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
							</th>
							<th align="center">
								订单号
							</th>
							<th align="center">
								订单类型
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								供货属性
							</th>
							<th align="center">
								协商交付日期
							</th>
							<th align="center">
								待送货
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								确认数量
							</th>
							<th align="center">
								图纸
							</th>
						</tr>
						<s:iterator value="wwPlanList" id="pageWgww2" status="pageStatus2">

							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="right">
								<s:if test="#pageWgww2.status=='供应商领料'">
								供应商领料
								</s:if>
								<s:else>
									<input type="checkbox" name="processIds"
										value="${pageWgww2.id}" onclick="chageNum(this)"
										>
								</s:else>
								<s:property value="#pageStatus2.index+1" />
							</td>
							<td>
								${pageWgww2.waigouOrder.planNumber}
							</td>
							<td>
								${pageWgww2.type}
							</td>
							<td>
								${pageWgww2.gysName}
							</td>
							<td align="left">
								${pageWgww2.markId}
							</td>
							<td align="left">
								${pageWgww2.proName}
							</td>
							<td>
								${pageWgww2.banben}
							</td>
							<td>
								${pageWgww2.kgliao}
							</td>
							<td>
								${pageWgww2.jiaofuTime}
							</td>
							<th>
								${pageWgww2.syNumber}
							</th>
							<td>
								${pageWgww2.status}
							</td>
							<td>
								<input id="$a_${pageWgww2.id}" name="processNumbers" value="${pageWgww2.syNumber}"
									style="width: 100px;" disabled="disabled"
									onkeyup="checkNum('${pageWgww2.syNumber}',this)">
							</td>
							<td>
								<input type="button" value="查看图纸" style="height: 35px;"
									onclick="javascript:location.href='WaigouwaiweiPlanAction!gysTzview2.action?id=${pageWgww2.id}';">

							</td>
							</tr>
						</s:iterator>
						<tr>
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2">
							</th>
							<td colspan="18" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
						<tr>
							<th colspan="18">
								<s:if test="wwPlanList!=null&&wwPlanList.size()>0">
									<input type="button" value="确认收货" class="input" id="subto"
										onclick="subtodis(this)" />
								</s:if>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$("#orderPlanNum").focus();
	$("#orderPlanNum").select();
})

function checkNum(num, obj) {
	var nownum = parseFloat($(obj).val());
	var oldnum = parseFloat(num);
	if (nownum > oldnum) {
		alert("确认数量不能超过" + oldnum);
		$(obj).val(oldnum);
		$(obj).focus();
		$(obj).select();
	} else if (nownum < 0) {
		alert("确认数量不能填写负数!");
		$(obj).val(oldnum);
		$(obj).focus();
		$(obj).select();
	}
}

function subtodis(obj) {
	$("#subto").attr("disabled", "disabled");
	obj.form.submit();
}

function chageAllCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			var checkBox = inputs[i];
			if (checkBox.checked != obj.checked) {
				checkBox.checked = obj.checked;
				if (checkBox.id != "checkAll2" && checkBox.id != "checkAll") {
					//chageNum(checkBox);
					disprocessNumbers(checkBox);
				}
			}
		}
	}
}

function chageNum(obj){
	disprocessNumbers(obj);
	var checkAll2=document.getElementById("checkAll2");
	var checkAll =document.getElementById("checkAll");
	var checkboxs=document.getElementsByName("processIds");
	var count=0;
	for(var i=0;i<checkboxs.length;i++){
		if(checkboxs[i].checked==false){
			checkAll.checked=false;
			checkAll2.checked=false;
			return;
		}else{
			count++;
		}
	}
	if(count==checkboxs.length){
		checkAll.checked=true;
		checkAll2.checked=true;
	}
}

/**
 * 改变数量是否失效
 * @param {Object} obj=多选框对象
 */
function disprocessNumbers(obj){
	var processNumbers = $(obj).parent().parent().find("input[name=processNumbers]");
	if(obj.checked ){//如果选中
		processNumbers.attr("disabled",false);
	}else{
		processNumbers.attr("disabled",true);
	}
	
}


</script>
	</body>
</html>
