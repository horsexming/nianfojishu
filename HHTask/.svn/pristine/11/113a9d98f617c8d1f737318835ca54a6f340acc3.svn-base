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
		<div id="gongneng">

			<div align="center">
				<h2>
					出库管理
				</h2>
				<form action="sellAction!tuiKuSell.action"
					method="post" onsubmit="return tuiku()">
					<input type="hidden" name="sell.sellId" value="${sell.sellId}">
					<table class="table" style="width: 95%;">
						<tr>
							<th>
								批次:
							</th>
							<td>
								<input type="text" name="sell.sellLot" value="${sell.sellLot }"
									readonly="readonly" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<select name="sell.sellMarkId" id="sellMarkId">
									<option value="${sell.sellMarkId }">
										${sell.sellMarkId }
									</option>
								</select>
							</td>
							<th>
								品名:
							</th>
							<td>
								<input type="text" name="sell.sellGoods"
									value="${sell.sellGoods }" readonly="readonly" />
							</td>
							<th>
								规格：
							</th>
							<td>
								<input type="text" name="sell.sellFormat" id="sellFormat"
									value="${sell.sellFormat }" readonly="readonly" />
							</td>

							</th>
						</tr>
						<tr>
							<th>
								退料量：
							</th>
							<td>
								<input type="text" name="sell.sellCount" id="sellCount"
									value="${sell.sellCount-sell.tksellCount}" />
<%--								<input type="hidden" id="zsellCount" value="${sell.sellCount}"/>--%>
								<input type="hidden" id="ktksellCount" value="${sell.sellCount-sell.tksellCount}"/>
								<span id="span"></span>
								<span style="color: red">*</span>
								可退:<span style="color: red">${sell.sellCount-sell.tksellCount}</span>
							</td>
							<th>
								计量单位：
							</th>
							<td>
<%--							danwei--%>
								<select  name="sell.sellUnit" id="">
									<option selected="selected" value="${sell.sellUnit }">
										${sell.sellUnit }
									</option>
								</select>
							</td>
							<th>
								库别：
							</th>
							<td>
								<input type="text" name="sell.sellWarehouse" id="sellWarehouse"
									value="${sell.sellWarehouse }" readonly="readonly" />
							</td>
							<th>
								项目编号：
							</th>
							<td>
								<input type="text" name="sell.proNumber" id="proNumber"
									value="${sell.proNumber}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								转换数量:
							</th>
							<td>
								<input type="text" name="sell.sellZhishu" id="sellZhishu" readonly="readonly"
									value="${sell.sellZhishu }" onchange="numyanzhen(this)" />
								<span id="span1"></span>
							</td>
							<th>
								转换单位:
							</th>
							<td>
<%--							danwei1--%>
								<select name="sell.goodsStoreZHUnit" id="">
									<option selected="selected" value="${sell.goodsStoreZHUnit }">
										${sell.goodsStoreZHUnit }
									</option>
								</select>
							</td>

							<th>
								退库类型
							</th>
							<td>
								<select name="sell.style">
									<option value="退料入库">
										退料入库
									</option>
<%--									<option>--%>
<%--										销售出库--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										领料出库--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										返修出库--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										退料出库--%>
<%--									</option>--%>
<%--									<option>--%>
<%--										报废出库--%>
<%--									</option>--%>
								</select>
							</td>
							<th>
								工艺卡号:
							</th>
							<td>
								<input type="text" name="sell.sellArtsCard"
									value="${sell.sellArtsCard }" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th>
								退料日期
							</th>
							<td>
								<input class="Wdate" type="text" id="goodsChangeTime"
									name="sell.sellDate" value="${sell.sellDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<span style="color: red">*</span>
							</td>
							<th>
								供应商:
							</th>
							<td>
								<input type="text" name="sell.sellSupplier"
									value="${sell.sellSupplier }" readonly="readonly" />
							</td>
							<th>
								客户:
							</th>
							<td>
								<input type="text" name="sell.sellCompanyName"
									value="${sell.sellCompanyName }" />
							</td>

							<th>
								返回状态
							</th>
							<td>
								<select name="sell.sellPeople">
									<option>
										${sell.sellPeople}
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>
								退料人：
							</th>
							<td>
								<input type="text" name="sell.sellCharger"
									value="${sell.sellAdminName}" readonly="readonly" />
							</td>

							<th>
								负责人:
							</th>
							<td>
								<input type="text" name="sell.sellGetGoodsMan"
									value="${sell.sellGetGoodsMan}"  readonly="readonly"/>
							</td>

							<th>
								供料属性
							</th>
							<td>
								<select name="sell.kgliao">
									<option value="${sell.kgliao}">${sell.kgliao}</option>
								</select>
							</td>
							<th>
								送货单号:
							</th>
							<td>
								<input type="text" name="sell.sellSendnum"
									value="${sell.sellSendnum }" /><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								内部订单号
							</th>
							<td>
								<input type="text" name="sell.orderNum" value="${sell.orderNum}"
									readonly="readonly" />
							</td>
							<th>
								业务件号
							</th>
							<td>
								<input type="text" name="sell.ywmarkId" value="${sell.ywmarkId}"
									readonly="readonly" />
							</td>

							<th>
								总成批次
							</th>
							<td>
								<input type="text" name="sell.rootSelfCard"
									value="${sell.rootSelfCard}" readonly="readonly" />
							</td>
							<th>
								总成件号
							</th>
							<td>
								<input type="text" name="sell.sellProMarkId"
									value="${sell.sellProMarkId}" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								外部订单号
							</th>
							<td>
								<input type="text" name="sell.outOrderNumer"
									value="${sell.outOrderNumer}" />
							</td>
							<td align="right">
								制单人:
							</td>
							<td>
								${sell.sellAdminName}
							</td>
							<td align="right">
								制单日期:
							</td>
							<td>
								${sell.sellTime}
							</td>
							<td align="right">
								打印单号
							</td>
							<td>
								${sell.printNumber}
								<input type="hidden" name="sell.printNumber"
									value="${sell.printNumber}" />
							</td>
						</tr>
						<tr>
							<th colspan="8">
								<input type="submit" value="退料" />
								&nbsp;
								<input type="reset" value="取消" />
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
$(function() {
	getUnit("danwei");
	getUnit("danwei1");
	if ("${sell.sellWarehouse}" == "原材料库") {
		$("#span")
				.html(
						'<input type="button" onclick=getbili("sellCount") value="计算"/>');
		$("#span1")
				.html(
						'<input type="button" onclick=getbili("sellZhishu") value="计算"/>');
	}

})
function numyanzhen(obj) {
	var ty1 = '^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$';
	var re = new RegExp(ty1);
	if (obj != null && obj.value.match(re) == null) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}

function getbili(obj) {
	var value = $("#" + obj).val();
if(value !=null && value != ""){
	  	$.ajax( {
		type : "POST",
		url : "yuanclAndWaigjAction!getYWbytrademark.action",
		data : {
			'yuanclAndWaigj.trademark':$("#sellMarkId").val(),
			'yuanclAndWaigj.specification':$("#sellFormat").val(),
		},
		dataType : "json",
		success : function(data) {
			if(data!=null && data>0){
				if(obj == "sellCount"){
					$("#sellZhishu").val((value*data).toFixed(2))
				}else if(obj == "sellZhishu"){
				 	
					$("#sellCount").val((value/data).toFixed(2))
				}
				
			}else{
			alert("该原材料没有相关的单张重量")
		}
		}
	})

  }else{
	  alert("请先输入数量或转换数量")
  }
}

function tuiku(){
	if (!validateText("sellCount", "退料数量")) {
		return false;
	}
	var tknum= $.trim($("#sellCount").val());
	//var ztknum= $("#zsellCount").val();
	var ktknum= $("#ktksellCount").val();
	if(tknum<=0){
		alert("退料数量不能小于0");
		$("#sellCount").val(ktknum);
		return false;
	}else{
		if(tknum>ktknum){
			alert("退料数量不能大于可退料数量:"+ktknum);
			$("#sellCount").val(ktknum);
			return false;
		}
	}
}
function validateText(id, textname) {
	var textValue = $.trim($("#" + id).val());
	if (textValue == null || textValue == "") {
		$("#" + id).focus();
		alert(textname + "不能为空");
		return false;
	}
	return true;
}
</script>
	</body>
</html>
