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
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">产品明细与维护</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" align="center"
					style="background-color: #ffffff; width: 100%;">
					<table class="table">
						<tr align="center">
							<th>数量</th>
							<td><input type="hidden" id="goodsId" name="goods.goodsId">
							<input type="hidden" id="procardId" name="goods.procardId">
							<input type="hidden" id="trindex" >
							<input id="goodsCurQuantity" name="goods.goodsCurQuantity"> </td>
						</tr>
						<tr><td align="center" colspan="2">
						领料人密码:
						<input id="pwsswords2" type="password" name="pwsswords" > 
						<input type="button" value="领取" onclick="out()" style="width: 60px;height: 40px;">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>半成品领料列表</h3>
				<br/>
				<form action="goodsAction!findBcpllList.action" method="post">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${goods.goodsMarkId}" name="goods.goodsMarkId"/>
							</td>
							<th align="right">
								生产批次
							</th>
							<td>
								<input type="text" value="${goods.wxselfCard}" name="goods.wxselfCard"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${goods.ywmarkId}" name="goods.ywmarkId"/>
							</td>
							<th align="right">
								内部订单号
							</th>
							<td>
								<input type="text" value="${goods.neiorderId}" name="goods.neiorderId"/>
							</td>
						<tr>
							<th align="right">
								仓区
							</th>
							<td>
								<input type="text" value="${goods.goodHouseName}" name="goods.goodHouseName"/>
							</td>
							<th align="right">
							</th>
							<td>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${tag}" name="tag"/>
					<input type="submit" value="查询" class="input"/>
				</form>
				<form action="goodsAction!bcpplOut.action" method="post">
				<input type="hidden" value="${tag}" name="tag" >
				<table class="table">
				<tr align="center" bgcolor="#c0dcf2" height="50px"> 
					<th>全选<input type="checkbox" id="checkAll" onchange="chageAllCheck()">序号</th>
					<th>总成件号</th>
					<th>总成批次</th>
					<th>内部订单号</th>
					<th>业务件号</th>
					<th>件号</th>
					<th>生产批次</th>
					<th>库存批次</th>
					<th>名称</th>
					<th>工序号</th>
					<th>工序名称</th>
					<th>下工序</th>
					<th>库别</th>
					<th>仓区</th>
					<th>库位</th>
					<th>数量</th>
					<th>库别</th>
					<th>仓区</th>
					<th>库位</th>
					<th>操作</th>
				</tr>
				<s:iterator value="list" id="pageGoods" status="pageindex">
					<s:if test="#pageindex.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" id="tr${pageindex.index}"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" id="tr${pageindex.index}"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center" width="60px;">
						<input type="text" name="lqCounts" id="lqCounts${pageindex.index}" value="${pageGoods.goodsCurQuantity}" style="display: none;width: 40px;" disabled="disabled">
				 		<input type="checkbox" name="goodsAndProcardIds" value="${pageGoods.goodsId},${pageGoods.procardId}"
								onchange="chageNum()" />${pageindex.index+1}</td>
						<td align="left">${pageGoods.goodsProMarkId}</td>
						<td align="left">${pageGoods.goodsArtsCard}</td>
						<td align="left">${pageGoods.neiorderId}</td>
						<td align="left">${pageGoods.ywmarkId}</td>
						<td align="left">${pageGoods.goodsMarkId}</td>
						<td align="left">${pageGoods.wxselfCard}</td>
						<td align="left">${pageGoods.goodsLotId}</td>
						<td align="left">${pageGoods.goodsFullName}</td>
						<td align="right">${pageGoods.processNo}</td>
						<td align="right">${pageGoods.processName}</td>
						<td align="right">${pageGoods.nextProcessname}</td>
						<td align="right">${pageGoods.goodsClass}</td>
						<td align="right">${pageGoods.goodHouseName}</td>
						<td align="right">${pageGoods.goodsPosition}</td>
						<td align="right">${pageGoods.goodsCurQuantity}</td>
						<td align="right">${pageGoods.goodsClass}</td>
						<td align="right">${pageGoods.goodHouseName}</td>
						<td align="right">${pageGoods.goodsPosition}</td>
						<td align="center"><input type="button" value="领料" onclick="lingliao(${pageGoods.procardId},${pageGoods.goodsId},${pageindex.index},${pageGoods.goodsCurQuantity})"></td>
						</tr>
				</s:iterator>
				<tr>
					<td colspan="18" align="left">
					领料人密码：<input id="pwsswords" type="password" name="pwsswords" > &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="批量领料" style="width: 80px;height: 60px;" onclick="todisabled(this)" >
					</td>
				</tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
<script type="text/javascript">

function lingliao(procardId,id,index,count){
	chageDiv('block');
	$("#goodsId").val(id);
	$("#procardId").val(procardId);
	$("#trindex").val(index);
	$("#goodsCurQuantity").val(count);
}
function out(){
	$.ajax( {
			type : "POST",
			url : "goodsAction!bcpOut.action",
			dataType : "json",
			data : {
				'goods.goodsId' : $("#goodsId").val(),
				'goods.procardId' : $("#procardId").val(),
				'goods.goodsCurQuantity' : $("#goodsCurQuantity").val(),
				'goods.lingliaocardId' : "${tag}",
				pwsswords : $("#pwsswords2").val()
			},
			success : function(msg) {
				if (msg=="true") {
					alert("领取成功!");
					$("#tr"+$("#trindex").val()).remove();
				} else {
					alert(msg);
				}
			}
		});
chageDiv('none');
}

function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("goodsAndProcardIds");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
			$("#lqCounts"+i).show();
			$("#lqCounts"+i).removeAttr("disabled");
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
			$("#lqCounts"+i).hide();
			$("#lqCounts"+i).attr("disabled","disabled");
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("goodsAndProcardIds");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			$("#lqCounts"+i).attr("disabled","disabled");
			$("#lqCounts"+i).hide();
		} else {
			$("#lqCounts"+i).show();
			$("#lqCounts"+i).removeAttr("disabled");
			count++;
		}
	}
	if(count>0){
	}else{
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
</script>
</html>
