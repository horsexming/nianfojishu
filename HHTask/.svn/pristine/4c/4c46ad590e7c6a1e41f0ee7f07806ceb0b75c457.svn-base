<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<style type="text/css">
td:hover .qs_ul {
	display: block;
}

.qs_ul {
	display: none;
	border: 1px solid #999;
	list-style: none;
	margin: 0;
	padding: 0;
	position: absolute;
	width: auto;
	background: #CCC;
	color: green;
}
.ztree li a {
	color: #fff;
}

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv" style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在进行不合格品退货操作</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div align="center">
			<form id="submitForm" action="wasteDisposeAction!updatePrice.action" enctype="multipart/form-data" method="post">
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th></th>
					<th align="center">仓区</th>
					<th align="center">库位</th>
					<th align="center">批次</th>
					<th align="center">供料属性</th>
					<th align="center">物料类别</th>
					<th align="center">品名</th>
					<th align="center">规格</th>
					<th align="center">供应商 </th>
					<th align="center" >入库类型</th>
					<th align="center" >入库时间</th>
					<th align="center" >原价格(单价)</th>
					<th align="center">处理数量</th>
					<th align="center">处理价格</th>
				</tr>
				<s:if test="{wasteDisponsalList.size()>0}">
					<s:iterator value="wasteDisponsalList" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#see.index+1" /></td>
						<td align="left" style="color: gray;">${gs.goodssHouseName}</td>
						<td align="left" style="color: gray;">${gs.goodsPosition}</td>
						<td align="right">${gs.goodsLotId}</td>
						<td align="right">${gs.goodsWgType}</td>
						<td align="right">${gs.goodsKgliao}</td>
						<td align="right">${gs.goodsFullName}</td>
						<td align="right">${gs.goodsFormat}</td>
						<td align="right">${gs.goodsSupplier}</td>
						<td align="right">${gs.goodsStyle}</td>
						<td align="right">${gs.goodsChangeTime}</td>
						<td align="right">${gs.goodsPrice}</td>
						<td align="right">${gs.disposeNum}</td>
						<td align="right">
							<input type="hidden" value="${gs.id}" name="wasteDisponsalList[${see.index}].id" />
							<input type="hidden" value="${gs.disposeNum}"  class="disposeNum" />
							<input type="text" value="${gs.disposePrice}" class="price"
								name="wasteDisponsalList[${see.index}].disposePrice" onchange="getTotalPrice()" style="width: 50px;"/>
						</td>
					</tr>
					</s:iterator>
					<tr>
						<th colspan="6"></th>
						<th colspan="3">
							总价格：<span id="totalPrice">${wasteDisponsalTotal.totalMoney}</span>
						</th>
					
					
					<tr>
						<td colspan="14" align="center">
							<!-- 
								<input type="button" id="fileButton"
								onclick="uploadFile(this)" value="上传附件">
							<div id="fileDiv" style="display: none;">

							</div>
							 -->
							 <input type="file" name="attachment" id="attachment"/>
						</td>
					</tr>
					<tr>
						<td colspan="14" align="center">
							<input type="button" onclick="checkSubmit()" value="提交审批" style="width: 100px;height: 50px;	"/>
						</td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="21" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
				</s:else>
			</table>
			</form>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
	<SCRIPT type="text/javascript">
	function checkSubmit(){
		var totalPrice = $("#totalPrice").text();
		 var attachment=document.getElementById("attachment").value;
		if(typeof(totalPrice) == "undefined" || totalPrice =="NaN" || totalPrice==0.0){
			alert("价格不能为空！");
			return ;
		}
		if(attachment=="" || attachment==null ){
			alert("请选择上传文件");
			return ;
		}
		$("#submitForm").submit();
	}
		var fileDivHTML = "";
		var count = 0;
		function uploadFile(obj) {
			var fileDiv = document.getElementById("fileDiv");
			if (obj.value == "上传附件") {
				fileDiv.style.display = "block";
				obj.value = "添加文件";
			}
			fileDivHTML = "<div id='file" + count + "'>" +
			"<input type='file' name='attachment'>" +
			"<a href='javascript:delFile("+ count + ")'>删除</a></div>";
			fileDiv.insertAdjacentHTML("beforeEnd", fileDivHTML);
			count++;
		}
		function delFile(obj) {
			document.getElementById("file" + obj).parentNode.removeChild(document
					.getElementById("file" + obj));
			count--;
			if (count <= 0) {
				count = 0;
				document.getElementById("fileButton").value = "上传附件";
				document.getElementById("fileDiv").style.display = "none";
			}
		}
		
		function getTotalPrice(){
			var prices = document.getElementsByClassName("price");
			var disposeNum=document.getElementsByClassName("disposeNum");
			var totalPrice=0.0;
			for(var i = 0; i<prices.length;i++){
				totalPrice+=parseFloat(prices[i].value)*parseFloat(disposeNum[i].value);
			}
			$("#totalPrice").text(totalPrice);
		}
	</SCRIPT>
</html>
