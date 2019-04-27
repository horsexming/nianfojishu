<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<form action="procardBlAction_findProcardForbllingliao.action"
					method="post" onsubmit="return disabledCard()">
					<input type="hidden" name="rootId" value="${rootId}">
					<div align="left">
						<font color="red">注:工序和工序，仓区和仓区之间用,隔开,不填写表示全选,如果要包含没有关联工序的请用无来表示如（折弯,无）</font>
					</div>
					<table class="table">
						<tr>
							<th align="center" colspan="3">
								工序
							</th>
							<th align="center" colspan="3">
								仓区
							</th>
							<th align="center" colspan="3" rowspan="2">
								刷卡:
								<input id="skinput" type="text" name="cardId"
									style="width: 150px;">
							</th>
							<th align="center" colspan="7" rowspan="2">
								<input id="htr1" type="submit" value="前往领料"
									style="width: 80px; height: 40px;" disabled="disabled">
							</th>
						</tr>
						<tr>
							<td align="center" colspan="3">
								<textarea rows="3" cols="20" name="goods.processName"></textarea>
							</td>
							<td align="center" colspan="3">
								<textarea rows="3" cols="20" name="goods.goodHouseName"></textarea>
							</td>
						</tr>
						<tr>
							<th>
								序号
							</th>
							<th>
								<input type="checkbox" id="checkAll" onchange="chageAllCheck()">
							</th>
							<th style="width: 40px;">
								数量
								<br />
								<input id="zccount" value="${procard.filnalCount}"
									onkeyup="chagenumup(this,'${procard.filnalCount}')"
									onblur="changeAllptcount()"
									style="width: 40px; display: block;" />
							</th>
							<th>
								件号
							</th>
							<th>
								批次
							</th>
							<th style="max-width: 70px;">
								名称
							</th>
							<th>
								设变单
							</th>
							<th>
								类型
							</th>
							<th>
								预领料日期
							</th>
							<th>
								实际领料日期
							</th>
							<th>
								批次数量
							</th>
							<th>
								包工包料数量
							</th>
							<th>
								已领料数量
							</th>
							<th>
								待配齐数量
							</th>
							<th>
								单位
							</th>
							<th>
								需要领料
							</th>
						</tr>
						<tr>
							<th align="center" colspan="20"
								style="font-weight: bolder; font-size: 20px; background-color: green; color: #ffffff;">
								当前可领料物料列表
							</th>
						</tr>
						<s:set name="jydclIndex" value="0"></s:set>
						<s:iterator value="procardBlList" id="pageProcardbl1"
							status="blStatus1">
							<s:if test="#blStatus1.index%2==1">
								<tr align="center" style="height: 26px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 26px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${blStatus1.index+1}
							</td>
							<td>
								<s:if
									test="#pageProcardbl1.beforeSelfcard!=null||#pageProcardbl1.procard.status=='设变锁定'">
								</s:if>
								<s:elseif
									test="#pageProcardbl1.procard.hascount!=null&&#pageProcardbl1.procard.hascount>0">
									<input type="checkbox" name="checkboxs"
										value="${pageProcardbl1.id}" onchange="chageNum()" />
								</s:elseif>
							</td>
							<td>
								<s:if test="#pageProcardbl1.procard.status=='设变锁定'">
									<font color="red">设变锁定中<font />
								</s:if>
								<s:elseif test="#pageProcardbl1.beforeSelfcard!=null">
									<font color="red">先领此总成${pageProcardbl1.beforeSelfcard}批次<font />
								</s:elseif>
								<s:elseif
									test="#pageProcardbl1.procard.hascount!=null&&#pageProcardbl1.procard.hascount>0">
									<input class="ptcount" type="text" name="peiqiCount"
										id="peiqiCount<s:property value="#jydclIndex"/>"
										value="${pageProcardbl1.procard.hascount}"
										style="width: 40px; display: block;" disabled="disabled"
										onkeyup="chagenumup(this,'${pageProcardbl1.procard.hascount}')">
									<input type="hidden"
										id="hascounthid<s:property value="#jydclIndex"/>"
										value="${pageProcardbl1.procard.hascount}" />
									<input type="hidden"
										id="filnalCounthid<s:property value="#jydclIndex"/>"
										value="${pageProcardbl1.procard.filnalCount}" />
									<s:set name="jydclIndex" value="#jydclIndex+1"></s:set>
								</s:elseif>
							</td>
							<td align="left">
								${pageProcardbl1.procard.markId}
							</td>
							<td align="left">
								${pageProcardbl1.procard.selfCard}
							</td>
							<td align="left" style="max-width: 70px;">
								${pageProcardbl1.procard.proName}
							</td>
							<td style="font-size: 8px;">
								<s:if test="#pageProcardbl1.procard.sbId!=null">
									<a target="showsbdetail"
										href="procardTemplateGyAction_showSbApplyJd2.action?bbAply.id=${pageProcardbl1.procard.sbId}">${pageProcardbl1.procard.sbNumber}</a>
								</s:if>
							</td>
							<td align="left">
								${pageProcardbl1.procard.procardStyle}
							</td>
							<td align="left">
								${pageProcardbl1.ylingliaoTime}
							</td>
							<td align="left">
								${pageProcardbl1.rlingliaoTime}
							</td>
							<td align="right">
								${pageProcardbl1.procard.filnalCount}
							</td>
							<td align="right">
								${pageProcardbl1.procard.wwblCount}
							</td>
							<td align="right">
								<s:if
									test="#pageProcardbl1.procard.klNumber!=null&&#pageProcardbl1.procard.hascount!=null">
					${procard.klNumber-pageProcardbl1.procard.hascount} 
					</s:if>
								<s:else>
					0
					</s:else>
							</td>
							<td align="right">
								${pageProcardbl1.procard.hascount}
							</td>
							<td align="right">
								${pageProcardbl1.procard.unit}
							</td>
							<td align="right">
								${pageProcardbl1.lingliaoStatus}
							</td>
							</tr>
							<s:iterator value="#pageProcardbl1.procard.procardList"
								id="pageProcard">
								<tr style="font-size: 1px; background-color: #E0EEEE">
									<td colspan="3" align="right">
										<s:if
											test='#pageProcard.status=="取消"||#pageProcard.status=="设变锁定"'>
											暂停领料:<font
												style="color: red; font-size: 14px; font-weight: bolder;">${pageProcard.status}</font>
										</s:if>
										<s:else>
											${pageProcard.status}
										</s:else>
									</td>
									<td>
										${pageProcard.markId}
									</td>
									<td>
										${pageProcard.selfCard}
									</td>
									<td style="font-size: 1px;">
										${pageProcard.proName}
									</td>
									<td>
										<s:if test="#pageProcard.sbId>0">
											<a  target="showsbdetail"
												href="procardTemplateGyAction_showSbApplyJd2.action?bbAply.id=${pageProcard.sbId}">${pageProcard.sbNumber}</a>
										</s:if>
									</td>
									<td>
										${pageProcard.procardStyle}
									</td>
									<td></td>
									<td></td>
									<td align="right">
										${pageProcard.filnalCount}
									</td>
									<td align="right">
										${pageProcard.wwblCount}
									</td>
									<td align="right">
										<fmt:formatNumber type="number"
											value="${pageProcard.klNumber-pageProcard.hascount}"
											pattern="0.00" maxFractionDigits="2" />
									</td>
									<td align="right">
										${pageProcard.hascount}
									</td>
									<td align="right">
										${pageProcard.unit}
									</td>
									<td align="right">
										<s:if test="#pageProcard.klNumber-#pageProcard.hascount==0">
											<font color="red">未领</font>
										</s:if>
										<s:elseif
											test="#pageProcard.klNumber-#pageProcard.hascount>0.005">
											<font color="blue">未领完</font>
										</s:elseif>
										<s:else>已领</s:else>
									</td>
								</tr>
							</s:iterator>
						</s:iterator>
						<tr>
							<th align="center" colspan="20">
								<input id="htr2" type="submit" value="前往领料"
									style="width: 80px; height: 40px;" disabled="disabled">
							</th>
						</tr>
						<tr>
							<th align="center" colspan="15"
								style="font-weight: bolder; font-size: 20px; background-color: red; color: #ffffff;">
								未到可领时间物料列表
							</th>
						</tr>
						<s:iterator value="procardBlList2" id="pageProcardbl2"
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
							</td>
							<td>
							</td>
							<td>
							</td>
							<td align="left">
								${pageProcardbl2.procard.markId}
							</td>
							<td align="left">
								${pageProcardbl2.procard.selfCard}
							</td>
							<td align="left">
								${pageProcardbl2.procard.proName}
							</td>
							<td align="left">
								${pageProcardbl2.procard.procardStyle}
							</td>
							<td align="left">
								${pageProcardbl2.ylingliaoTime}
							</td>
							<td align="left">
								${pageProcardbl2.rlingliaoTime}
							</td>
							<td align="right">
								${pageProcardbl2.procard.filnalCount}
							</td>
							<td align="right"></td>
							<td align="right"></td>
							<td align="right">
								${pageProcardbl2.procard.hascount}
							</td>
							<td align="right" align="right">
								${pageProcardbl2.procard.unit}
							</td>
							<td align="right">
								${pageProcardbl2.lingliaoStatus}
							</td>
							</tr>
						</s:iterator>
						<tr>
							<th align="center" colspan="15"
								style="font-weight: bolder; font-size: 20px; background-color: green; color: #ffffff;">
								已配齐物料列表
							</th>
						</tr>
						<s:iterator value="procardBlList3" id="pageProcardbl3"
							status="blStatus3">
							<s:if test="#blStatus3.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${blStatus3.index+1}
							</td>
							<td>
							</td>
							<td>
							</td>
							<td align="left">
								${pageProcardbl3.procard.markId}
							</td>
							<td align="left">
								${pageProcardbl3.procard.selfCard}
							</td>
							<td align="left">
								${pageProcardbl3.procard.proName}
							</td>
							<td align="left">
								${pageProcardbl3.procard.procardStyle}
							</td>
							<td align="left">
								${pageProcardbl3.ylingliaoTime}
							</td>
							<td align="left">
								${pageProcardbl3.rlingliaoTime}
							</td>
							<td align="right">
								${pageProcardbl3.procard.filnalCount}
							</td>
							<td align="right"></td>
							<td align="right"></td>
							<td align="right">
								${pageProcardbl3.procard.hascount}
							</td>
							<td align="right">
								${pageProcardbl3.procard.unit}
							</td>
							<td align="right">
								${pageProcardbl3.lingliaoStatus}
							</td>
							</tr>
						</s:iterator>
						<%--				<tr id="htr1" style="display: none;">--%>
						<%--					<th align="center" colspan="10">--%>
						<%--						刷卡:<input type="text"  style="width: 150px;">--%>
						<%--					</th>--%>
						<%--				</tr>--%>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
			$("#peiqiCount"+i).removeAttr("disabled");
		}
		$("#htr1").removeAttr("disabled");;
		$("#htr2").removeAttr("disabled");;
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
			$("#peiqiCount"+i).attr("disabled","disabled");
		}
		$("#htr1").attr("disabled","disabled");
		$("#htr2").attr("disabled","disabled");
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			$("#peiqiCount"+i).attr("disabled","disabled");
		} else {
			$("#peiqiCount"+i).removeAttr("disabled");
			count++;
		}
	}
	if(count>0){
		$("#htr1").removeAttr("disabled");;
		$("#htr2").removeAttr("disabled");;
	}else{
		$("#htr1").attr("disabled","disabled");
		$("#htr2").attr("disabled","disabled");
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function changeAllptcount(){
	if(mustBeNumber("zccount")){
		var zcfilnalCount = "${procard.filnalCount}";
		var zcpt = $("#zccount").val();
		var zjpts = $(".ptcount");
		var ptindex=0;
		for (var ptindex=0;ptindex<zjpts.length;ptindex++ )	{
			var zjfilnalCount = $("#filnalCounthid"+ptindex).val();
			var zjhascount = $("#hascounthid"+ptindex).val();
			var ptcount = zjfilnalCount*zcpt/zcfilnalCount;
			if((ptcount-zjhascount)>0){
				ptcount = zjhascount;
			}
			$("#peiqiCount"+ptindex).val(ptcount);
        };
	}
}

function chagenumup(obj,num){
	var num=parseFloat(num);
	var num2=parseFloat(obj.value);
	if(num2>num){
		obj.value=num;
		alert("调整数量不能超过:"+num);
	}
}

function disabledCard(){
	var skinput =$("#skinput").val();
	if(skinput==null || skinput==""){
		alert("请刷卡或输入卡号");
		return false;
	}else{
		$("#skinput").attr("readonly","readonly");
		$("#htr1").attr("disabled","disabled");
		$("#htr2").attr("disabled","disabled");
	}
	return true;
}
</SCRIPT>
	</body>
</html>