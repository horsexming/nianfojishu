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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				
					<h3>生产退料批量审批</h3>
					<form action="procardBlAction_findAllTuiLiao.action?tag=${tag}"
						method="post">

						<table class="table" align="center">
							<tr>
								<td align="center">
									总成件号
									<input type="text" name="procard.rootMarkId" value="<s:property value="procard.rootMarkId"/>" />
								</td>
								<td align="center">
									总成批次
									<input type="text" name="procard.rootSelfCard" value="<s:property value="procard.rootSelfCard"/>" />
								</td>
							</tr>
							<tr>
								<td align="center">
									业务件号
									<input type="text" name="procard.ywMarkId" value="<s:property value="procard.ywMarkId"/>" />
								</td>
								<td align="center">
									订单号
									<input type="text" name="procard.orderNumber" value="<s:property value="procard.orderNumber"/>" />
								</td>
							</tr>
								<tr>
								<td align="center">
									申请人
									<input type="text" name="procard.appliUserName" value="<s:property value="procard.appliUserName"/>" />
								</td>
								<td align="center">
									仓区
									<input type="text" name="procard.cangqu" value="<s:property value="procard.cangqu"/>" />
								</td>
							</tr>
							</tr>
								<tr>
								<td align="center">
									申请时间从
									<input type="text" name="startime" value="<s:property value="procard.appliUserName"/>"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  class="Wdate" />
								</td>
								<td align="center">
									止
									<input type="text" name="endtime" value="<s:property value="procard.cangqu"/>"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"   class="Wdate"  />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
					<form action=""
					method="post">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</th>
							<th>
								件号
							</th>
							<th>
								批次
							</th>
							<th>
								名称
							</th>
							<th>
								仓区
							</th>
							<th>
								类型
							</th>
							<th>
								单位
							</th>
							<th>
								可领数量
							</th>
							<th>
								已发料数量
							</th>
							<th>
								申请退料数量
							</th>
							<th>
								已退料数量
							</th>
							<th>
								申请状态
							</th>
							<th>
								申请人
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator value="listProcardTl" id="procard2"
							status="blStatus2">
							<s:if test="#blStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${blStatus2.index+1}
								<input type="checkbox" name="procardIds1" value="${procard2.id}"
									onclick="chageNum(this)">
							</td>
							<td>
								${procard2.markId}
							</td>
							<td>
								${procard2.selfCard}
							</td>
							<td>
								${procard2.proName}
							</td>
							<td>
								${procard2.cangqu}
							</td>
							<td>
								${procard2.procardStyle}
							</td>
							<td>
								${procard2.unit}
							</td>
							<td>
								${procard2.filnalCount}
							</td>
							<td>
								${procard2.filnalCount-procard2.hascount}
							</td>
							<td>
								${procard2.stuiLiaoNumber}
							</td>
							<td>
								${procard2.ytuiLiaoNumber}
							</td>
							<td>
								<a href="CircuitRunAction_findAduitPage.action?id=${procard2.epId}">${procard2.tuiLiaoStatus}</a>
							</td>
							<td>
								${procard2.appliUserName}
							</td>
							<td>
								<s:if test="#procard2.epId!=null">
									<input type="button" value="审批动态" style="height: 25px;width: 80px;" onclick="shenpi(${procard2.epId})">
								</s:if>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="1" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2" name="procardIds">
								全选
							</th>
							<td colspan="20">
							<s:if test="tag=='wei'">
								<input id="ok" class="input" style="width: 120px;" align="top"
									type="button" value="审批通过"
									onclick="toSubmitTl1(this.form,'ok')"/>
								<input id="ng" class="input" align="top" type="button"
									value="审批驳回" onclick="toSubmitTl1(this.form,'no')"/>
							</s:if>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
<%--function chageAllCheck() {--%>
<%--	var checkAll = document.getElementById("checkAll");--%>
<%--	var checkboxs = document.getElementsByName("checkboxs");--%>
<%--	if (checkAll.checked == true) {--%>
<%--		for ( var i = 0; i < checkboxs.length; i++) {--%>
<%--			checkboxs[i].checked = true;--%>
<%--			$("#peiqiCount"+i).show();--%>
<%--			$("#peiqiCount"+i).removeAttr("disabled");--%>
<%--		}--%>
<%--		$("#htr1").show();--%>
<%--		$("#htr2").show();--%>
<%--	} else {--%>
<%--		for ( var i = 0; i < checkboxs.length; i++) {--%>
<%--			checkboxs[i].checked = false;--%>
<%--			$("#peiqiCount"+i).hide()--%>
<%--			$("#peiqiCount"+i).attr("disabled","disabled");--%>
<%--		}--%>
<%--		$("#htr1").hide();--%>
<%--		$("#htr2").hide();--%>
<%--	}--%>
<%----%>
<%--}--%>
<%--function chageNum() {--%>
<%--	var checkAll = document.getElementById("checkAll");--%>
<%--	var checkboxs = document.getElementsByName("checkboxs");--%>
<%--	var count = 0;--%>
<%--	for ( var i = 0; i < checkboxs.length; i++) {--%>
<%--		if (checkboxs[i].checked == false) {--%>
<%--			checkAll.checked = false;--%>
<%--			$("#peiqiCount"+i).hide()--%>
<%--			$("#peiqiCount"+i).attr("disabled","disabled");--%>
<%--		} else {--%>
<%--			$("#peiqiCount"+i).show();--%>
<%--			$("#peiqiCount"+i).removeAttr("disabled");--%>
<%--			count++;--%>
<%--		}--%>
<%--	}--%>
<%--	if(count>0){--%>
<%--		$("#htr1").show();--%>
<%--		$("#htr2").show();--%>
<%--	}else{--%>
<%--		$("#htr1").hide();--%>
<%--		$("#htr2").hide();--%>
<%--	}--%>
<%--	if (count == checkboxs.length) {--%>
<%--		checkAll.checked = true;--%>
<%--	}--%>
<%--}--%>
function appli1(id,num){
	var stknum= Number($.trim($("#stuiLiaoNumber_"+num).val()));
	var ytknum= Number($("#ytuiLiaoNumber_"+num).val());
	if(Number(stknum)<=0){
		alert("退料数量不能小于0");
		$("#stuiLiaoNumber_"+num).val(ytknum);
		return false;
	}else{
		if(Number(stknum)>Number(ytknum)){
			alert("退料数量不能大于可退料数量:"+ytknum);
			$("#stuiLiaoNumber_"+num).val(ytknum);
			return false;
		}else{
			$("#tuiliao_"+num).attr("disabled","disabled");
			//alert("申请成功"+id + "+"+stknum);
			window.location.href="procardBlAction_applicationTuiliao.action?procard.id="+id+"&procard.stuiLiaoNumber="+stknum;
		}
	}
}
function shenpi(id){
	window.location.href="CircuitRunAction_findAduitPage.action?id="+id;
}

function toSubmitTl1(form,tag){
	form.action = "procardBlAction_shenPiTuiLiao.action?okNo="+tag+"&tag=${tag}";
	form.submit();
}


</SCRIPT>
	</body>
</html>
