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
				<form action="huikuanAction!updateInvoice.action" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="taHk.id" value="${taHk.id}">
					<table style="width: 98%; margin: 5 px">
						<tr>
							<td colspan="10" align="right">
								编号：
								<s:property value="taHk.hkNum" />
								<span style="width: 50px;"></span>
							</td>
						</tr>
						<tr align="center" bgcolor="#c0dcf2"
							style="height: 40px; font-weight: bold;">
							<td>
								序号
							</td>
							<td>
								客户
							</td>
							<td>
								零件号
							</td>
							<td>
								开票数量
							</td>
							<td>
								送货单号
							</td>
							<td>
								订单号
							</td>
							<td>
								单价（含税）
							</td>
							<td>币种</td>
							<td>
								发票号
							</td>
							<td>
								上传发票
							</td>
						</tr>
						<s:iterator value="listHkSellSta" status="hk" id="huikuan">
							<s:if test="#hk.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#hk.index+1" />
							</td>
							
								    <input type="hidden"
									name="hkset[<s:property value='#hk.index' />].id" size="7"
									readonly="readonly" value="${id }"
									 />
									<input type="hidden"
									name="hkset[<s:property value='#hk.index' />].hkSellFile" size="7"
									readonly="readonly" value="${hkSellFile }" />
									<input type="hidden"
									name="invoiceArr[<s:property value='#hk.index' />].id" size="7"
									readonly="readonly" value="${TaHkHkInvoice.id }" />
									
								<td>	
								<input
									name="hkset[<s:property value='#hk.index' />].hkSellCumpanyName"
									size="7" readonly="readonly" value="${hkSellCumpanyName }" />
								</td>
								<td>
									<input
										name="hkset[<s:property value='#hk.index' />].hkSellMarkId"
										size="7" readonly="readonly" value="${hkSellMarkId }" />
								</td>
								<td>
									<input
										name="hkset[<s:property value='#hk.index' />].hkSellCount"
										size="7" readonly="readonly" value="${hkSellCount }" />
								</td>
								<td>
									<input
										name="hkset[<s:property value='#hk.index' />].hkSellSendId"
										size="7" readonly="readonly" value="${hkSellSendId }" />
								</td>
								<td>
									<input
										name="hkset[<s:property value='#hk.index' />].hkSellOrderId"
										size="7" readonly="readonly" value="${hkSellOrderId }" />
								</td>
								<td>
									<input style="width:100px;" value="${hkSellPrice}"
										name="hkset[<s:property value='#hk.index' />].hkSellPrice"
										size="7" readonly="readonly" />
									
								</td>
								<!-- name="invoiceArr[<s:property value='#hk.index' />].hkInvoMoneyUnit" -->
								<td>
								<!-- list="{'元','美元','欧元','港币','英镑','日元','瑞士法郎','澳元','加元'}"  -->
									<input name="hkset[<s:property value='#hk.index' />].hkSellMoneyUnit" value="${hkSellMoneyUnit}" readonly="readonly" />
									
								</td>
								<td>
								<!-- name="invoiceArr[<s:property value='#hk.index' />].hkInvoInvoNum" -->
									<input name="invoiceArr[<s:property value='#hk.index' />].hkInvoInvoNum"
										value="${TaHkHkInvoice.hkInvoInvoNum }" size="7" />
								</td>
								<td>
								<!-- 
								<input type="file" name="attachment" onchange="chageFlie(<s:property value="#hk.index+1" />, 'yes')">
								<input type="" name="attachmentStatus" id='value<s:property value="#hk.index+1" />' value="no">
								 -->
																
									<input type="button"
										id="fileButton_<s:property value="#hk.index+1" />"
										onclick="uploadFile(this,'<s:property value="#hk.index+1" />')"
										value="上传发票">
									<input type="hidden" name="attachmentStatus" id='value<s:property value="#hk.index+1" />' value="no">
									<div id="fileDiv_<s:property value='#hk.index+1'/>"
										style="display: none;">
									</div>
								
								</td>
								</tr>
						</s:iterator>
						<tr>
							<td colspan="10" align="left">
								回款周期：
								<input id="backCircle" name="taHk.hkPayCycle" value=${taHk.hkPayCycle } size="7" />
								(天) 回款倒计时开始时间：
								<input class="Wdate" type="text" name="taHk.hkBillTime" value="${taHk.hkBillTime }"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
							<tr>
								<td colspan="10" align="center">
									<input type="submit" value="提交" Onclick="return check22()"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;
									<input type="reset" value="取消"
										style="width: 60px; height: 40px;" align="top">
									&nbsp;
								</td>
							</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

	</body>
	<script type="text/javascript">
function uploadFile(obj, few) {
	obj.style.display = "none";
	var fileDiv = document.getElementById("fileDiv_" + few);
	fileDiv.style.display = "block";
	fileDiv.innerHTML = "<input type='file' name='attachment' onchange=chageFlie('"+few+"','yes') /><a href=javascript:delFile('"+few+"')>删除</a></div>";
}

function delFile(few) {
	var fileDiv = document.getElementById("fileDiv_" + few);
	var value = document.getElementById("value" + few);
	value.value="no";
	fileDiv.innerHTML = "";
	fileDiv.style.display = "none";
	document.getElementById("fileButton_"+few).style.display = "block";
}

function check22() {
	var circle = document.getElementById("backCircle");
	//alert(circle.value);
	if (null == circle.value || "" == circle.value) {
		alert("回款周期不能为空!");
		backCircle.focus();
		return false;
	}
}
//处理伤处附件
function showFile(index) {
	document.getElementById("fileButton_" + index).style.display = "none";
	document.getElementById("fileDiv_" + index).style.display = "block";

}
function hiddenFile(index) {
	document.getElementById("fileButton_" + index).style.display = "block";
	document.getElementById("fileDiv_" + index).style.display = "none";
}

function chageFlie(obj, sta) {
	var value = document.getElementById("value" + obj);
	value.value = sta;
}
</script>
</html>
