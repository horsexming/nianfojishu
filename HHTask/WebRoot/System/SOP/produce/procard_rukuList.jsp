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
		<div>
			<div align="center">
					<table class="table" style="width: 85%;">
					<form >
						<th>业务件号：</th>
						<td><input type="text" name="procard.ywMarkId" value="${procard.ywMarkId}"/></td>
						<th>内部订单号：</th>
						<td><input type="text" name="procard.orderNumber" value="${procard.orderNumber}"/></td>
						<td colspan="4" align="center"> <input type="submit" value="查询"/></td>
					</form>
					</table>
<%--				<span style="color: red; font-weiht: bold;">${message}</span>--%>
				<table class="table" style="width: 85%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
						</th>
						<th align="center">
							类型
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							内部订单号
						</th>
						<th align="center">
							产品名称
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							提交数量
						</th>
						<th align="center">
							未入库数量
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<tr>
						<td colspan="8" style="font-size: 15px; color: red;">
							待入库的记录
						</td>
					</tr>
					<s:iterator value="procardList" status="se" id="procardPage">
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
							${procardPage.carStyle}
						</td>
						<td>
							${procardPage.markId}
						</td>
						<td>
							${procardPage.ywMarkId}
						</td>
						<td>
							${procardPage.orderNumber}
						</td>
						<td>
							${procardPage.proName}
						</td>
						<td>
							${procardPage.selfCard}
						</td>
						<td>
							${procardPage.tjNumber} 
						</td>
						<td>
							<fmt:formatNumber value="${procardPage.tjNumber-procardPage.rukuCount}" pattern="#.0000"/>
						</td>
						<td>
							<s:if test="pageStatus=='phone'">
								<div
									onclick="getcheckList2('${id}')"
									style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
									<br />
									入库码 
									<br />
									扫描
								</div>
							</s:if>
							<s:else>
								<a
								href="RunningWaterCardAction!findOneCardInfor.action?id=${id}&tag=noCard">申请入库</a>
							</s:else>
						</td>
					</s:iterator>
					<tr>
						<td colspan="20" align="right">
							共
							<s:property value="total" />
							页 第
							<s:property value="cpage" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
//提交验证
//onload = function() {
//			var me= "${message }";
//		if (me.length> 0) {
//				alert(me);							
//	  	} 
//	} 

var id =0;
function getcheckList2(num) {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
		id = num;
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}

function funFromjs(tm) {
	window.location.href = "RunningWaterCardAction!findOneCardInfor.action?id="+id+"&tag=noCard"+"&barCode="+tm;
}

function funFromjs1(tm) {
	$.ajax( {
		type : "POST",
		url : "GoodsStoreAction!OpenWNByWNNumber.action",
		data : {
			id:id,
			tag:"noCard",
			barCode:tm
		},
		dataType : "json",
		success : function(data) {
			if(data!=null){
					if(data.success){
						if(id>0){
							window.location.href = "RunningWaterCardAction!findOneCardInfor.action?id="+id+"&tag=noCard"+"&barCode="+data.message;
						}
					}else{
						alert(data.message);
						id = 0;
					}
				}
			}
		})
}
</script>
	</body>
</html>
