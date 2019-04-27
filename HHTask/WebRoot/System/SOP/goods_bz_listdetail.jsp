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
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div align="center">
				<h2>
					库存信息维护
				</h2>
				<form action="goodsAction!saveGoodsBzwSq.action" method="post" onsubmit="return adddata2()">
					<input type="hidden" name="goodsBzsq.goodsId"
						value="${goods.goodsId}">
					<table id="addtable" class="table" style="width: 95%;">
						<tr>
							<th>
								订单号:
							</th>
							<td>${goods.neiorderId }
									<input type="hidden" name="goodsBzsq.neiorderId" 
									value="${goods.neiorderId }" readonly="readonly" />
							</td>
							<th style="color: red;">
								件号:
							</th>
							<td>
								${goods.goodsMarkId }
								<input type="hidden" name="goodsBzsq.markId" id="goodsMarkId"
									value="${goods.goodsMarkId }" readonly="readonly" />
							</td>
							<th>
								批次:
							</th>
							<td>
								${goods.goodsLotId }
								<input type="hidden" name="goodsBzsq.selfCard"
									value="${goods.goodsLotId }" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								品名:
							</th>
							<td>
								<input type="text" name="goodsBzsq.procardName"
									value="${goods.goodsFullName }" />
							</td>
							<th>
								数量:
							</th>
							<td>
							<s:if test="goods.bzApplyCount==null">
								<input name="goodsBzsq.count" value="${goods.goodsCurQuantity}" />
							</s:if>
							<s:else>
								<input name="goodsBzsq.count" value="${goods.goodsCurQuantity- goods.bzApplyCount}" />
							</s:else>
							</td>
							<th>
								包装日期:
							</th>
							<td>
								<input type="text" name="goodsBzsq.bzDate" class="Wdate" id="baozhuangTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							
							<th>指派人员</th>
							<td colspan="5">
							<input type="hidden" id='zpry' name="goodsBzsq.zpbzUserId">
							<input type="hidden" id='zprycode' name="goodsBzsq.zpbzUserCode">
							<input type="hidden" id='zpryname' name="goodsBzsq.zpbzUserName">
							<label id="showry"></label>
								<input type="button" value="选择" onclick="selectry()">
							</td>
						</tr>
						<tr>
							<th colspan="6">
								包装物使用	<input type="button" value="增加" onclick="addbzLine()" style="width: 60px;height: 30px;">
							</th>
						</tr>
						<tr id="bzwTr0">
							<th>
								名称:
							</th>
							<td>
								<input type="text" data="mc" name="goodsBzsq.goods_bzwList[0].name" id="name"/>
							</td>
							<th>
								规格:
							</th>
							<td>
								<input type="text" data="gg" name="goodsBzsq.goods_bzwList[0].format" id="goodsMarkId" />
							</td>
							<th>
								数量:
							</th>
							<td>
								<input type="text" data="sl" name="goodsBzsq.goods_bzwList[0].count" id="count"/>
								<input type="button" value="删除" onclick="deletebzLine(0)" style="width: 60px;height: 30px;">
							</td>
						</tr>
						<tr>
							<th colspan="6">
								<input type="submit" value="提交" class="input">
							</th>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function selectry(){
	$("#showProcess").attr("src",
			"goodsAction!toSelectPeople.action");
	chageDiv('block');
}
var lineCount=1;
var lineindex=0;
function addbzLine(){
	lineCount++;
	lineindex++;
	var html="<tr id='bzwTr"+lineindex+"'><th>名称:</th><td><input data='mc' type='text' name='goodsBzsq.goods_bzwList["+lineindex+"].name' id='name"+lineindex+"'/></td>" +
	"<th>规格:</th><td><input data='gg' name='goodsBzsq.goods_bzwList["+lineindex+"].format' id='goodsMarkId"+lineindex+"' /></td>" +
	"<th>数量:</th><td><input data='sl' type='text' name='goodsBzsq.goods_bzwList["+lineindex+"].count' id='count"+lineindex+"'/>" +
	"<input type='button' value='删除' onclick='deletebzLine("+lineindex+")' style='width: 60px;height: 30px;'></td>" +
	"</tr>";
	$("#addtable>tbody>tr").eq(2+lineCount).after(html);
}
function deletebzLine(index){
	if(lineCount==1){
		alert("最少保留一条!");
		return false;
	}
	lineCount--;
	lineindex--;
	$("#bzwTr"+index).remove();
}
function adddata2() {
	if (!validateText("zpryname","指派人员")) {
		return false;
	}
	if (!validateText("baozhuangTime","包装日期")) {
		return false;
	}
		var a=$("input[data*='mc']");
			for(var i=0;i<a.length;i++){
			 	if($(a[i]).val()==""){
				 	alert("第"+(i+1)+"行名称没有填写");
				 	return false;
			 	}
			}
		var a=$("input[data*='gg']");
			for(var i=0;i<a.length;i++){
			 	if($(a[i]).val()==""){
				 	alert("第"+(i+1)+"行规格没有填写");
				 	return false;
			 	}
			}
		var a=$("input[data*='sl']");
			for(var i=0;i<a.length;i++){
			 	if($(a[i]).val()==""){
				 	alert("第"+(i+1)+"行数量没有填写");
				 	return false;
			 	}
	}
}

function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>

	</body>
</html>
