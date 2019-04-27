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
			<div id="xitong" >
			</div>
			
			<div  id="printDiv" style="margin-left: 10%;margin-right: 10%">
				<div align="left">
				<h3>
					&nbsp;&nbsp;诺骋代上海红湖排气系统${escrowMonth.month}月份支付汇总
				</h3>
				</div>
<%--				<div>--%>
<%--				<p><b>甲方：上海红湖排气系统有限公司</b></p>--%>
<%--				<p><b>乙方：中国航发贵州黎阳航空发动机有限公司</b></p>--%>
<%--				<div>--%>
<%--				&nbsp;&nbsp;&nbsp;&nbsp;经过双方友好协商，甲方委托乙方所属财务部门仅针对于甲方在乙方监管下<br/>--%>
<%--				所开具的委托贷款账户内的资金进行支付和收取的业务。当进行资金支付时，<br/>--%>
<%--				乙方所属财务部门应收到甲方的《付款通知单》（见本协议附表）后方可进行付款。<br/>--%>
<%--				在未征得甲方书面同意时，乙方不得擅自挪用甲方账户内资金，如果违反协议约定内容，<br/>--%>
<%--				甲方有权追究乙方的法律责任。<br/>--%>
<%--				&nbsp;&nbsp;&nbsp;&nbsp;本协议一式贰份，自2017年5月1日起经双方签字盖章后生效，终止日期由双方商定另行约定。--%>
<%--				附表：--%>
<%--				</div>--%>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							收款人全称
						</td>
						<td align="center">
							摘要
						</td>
						<td align="center">
							付款日期
						</td>
						<td align="center">
							付款方式
						</td>
						<td align="center">
							收款人开户银行
						</td>
						<td align="center">
							账号
						</td>
						<td align="center">
							金额
						</td>
						<td align="center">
							备注
						</td>
					</tr>
					<s:iterator value="escrowMonth.escrowlist" id="pageescrow" status="pageStatus">
						<tr align="center" >
						<td align="center">
							${pageStatus.index+1}
						</td>
						<td align="center">
							${pageescrow.shoukuanFang}
						</td>
						<td align="center" style="width: 150px;">
							${pageescrow.fukuanYongtu}
						</td>
						<td align="center">
							${pageescrow.paymentTime}
						</td>
						<td align="center" >${pageescrow.payWay}
<%--						<s:if test="#pageescrow.shoukuanZhanghao==null||#pageescrow.shoukuanZhanghao==''">--%>
<%--						现金--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--							银行转账--%>
<%--						</s:else>--%>
						</td>
						<td align="center">
						${pageescrow.shoukuanyh}
						</td>
						<td align="center" style="width: 150px;">
						${pageescrow.shoukuanZhanghao}
						</td>
						<td align="right">
							${pageescrow.yingfuJine}
						</td>
						<td align="center">
							${pageescrow.more}
						</td>
						
					</tr>
					</s:iterator>
					<tr>
					<td colspan="7"></td>
					<td align="right"><fmt:formatNumber type="number" value="${heji}" pattern="0.00" maxFractionDigits="2"/> </td>
					<td></td>
					</tr>
				</table>
				<br/>
				<div style="width: 33%;float: left;"><p>批准：<p/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
						<s:if test="escrowMonth.epStatus=='同意'&&qianmingList!=null&&qianmingList.size()>0">
										<img
											src="<%=path%><s:property value="qianmingList.get(qianmingList.size()-1)"/>"
											style="width: 50px; height: 50px" /> &nbsp;&nbsp;&nbsp;&nbsp;${spTime}
									</s:if>
				</div>
				<div style="width: 33%;float: left;">
				<p>复核：<p/>
<%--				<s:if test="qianmingList!=null&&qianmingList.size()>1">--%>
<%--										<s:iterator value="qianmingList" id="qmUrl"--%>
<%--											begin="0" end="qianmingList.size()-2">--%>
<%--											<img src="<%=path%><s:property value="#qmUrl"/>"--%>
<%--												style="width: 50px; height: 50px" />--%>
<%--										</s:iterator>--%>
<%--									</s:if>--%>
<%--									<s:elseif--%>
<%--										test="qianmingList!=null&&qianmingList.size()==1">--%>
<%--										<img--%>
<%--											src="<%=path%><s:property value="qianmingList.get(0)"/>"--%>
<%--											style="width: 50px; height: 50px" />--%>
<%--									</s:elseif>--%>
				</div>
				<div style="width: 33%;float: left;"><p>制表人：<p/></div>
				<div style="clear: both;"></div>
				<div align="right"> 上海红湖排气系统有限公司&nbsp;&nbsp;&nbsp;<label id="zpYear"></label>年<label id="zpMonth"></label>月<label id="zpdate"></label>日	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
			</div>
			<div align="center">
			<s:if test="escrowMonth.epStatus=='同意'">
				<form action="EscrowAction_print.action">
					<s:iterator value="ids" id="ttt">
					<input name="ids" value="${ttt}" type="hidden">
					</s:iterator>
					<input id="dyBtn" value="打印" type="submit" style="width: 60px;height: 40px;"  onclick="pagePrint('printDiv','sy');">
				</form>
			</s:if>
			<s:else>
			<a href="CircuitRunAction_findAduitPage.action?id=${escrowMonth.epId}">待审批</a>
			</s:else>
			</div>
				</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
$(function() {
	var today=new Date();
	var month =today.getMonth()+1;
	var date ="";
	if((today.getDate()-10)<0){
		date = "0"+today.getDate();
	}else{
		date = today.getDate();
	}
	$("#yfLable").html(month);
	$(".payMonth").html(month);
	$(".payDate").html(date);
	$("#zpYear").html(today.getFullYear());
	$("#zpMonth").html(month);
	$("#zpdate").html(date);
})
function chageNum(obj) {
	var inputs = document.getElementsByTagName("input");
	var num=0;
	for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].type == "checkbox") {
				var checkBox = inputs[i];
				if (checkBox.checked == true) {
						num++;
				}
			}
	}
	if(num==0){
		$("#dyBtn").attr("disabled","disabled");
	}else{
		$("#dyBtn").removeAttr("disabled");
	}
}
</script>
	</body>
</html>
