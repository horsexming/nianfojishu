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
				<form action="sellAction!updateSell.action?tag=saveSell"
					method="post">
					<input type="hidden" name="sell.sellId" value="${sell.sellId}">
					<table class="table" style="width: 95%;">
						<tr>
							<th align="right">
								批次：
							</th>
							<td>
								${sell.sellLot }
								<%--								<input type="text" name="sell.sellLot" value="${sell.sellLot }"--%>
								<%--									readonly="readonly" />--%>
							</td>
							<th align="right">
								件号：
							</th>
							<td>
								${sell.sellMarkId }
								<%--								<select name="sell.sellMarkId" id="sellMarkId">--%>
								<%--									<option value="${sell.sellMarkId }">--%>
								<%--										${sell.sellMarkId }--%>
								<%--									</option>--%>
								<%--								</select>--%>
							</td>
							<th align="right">
								品名：
							</th>
							<td>
								${sell.sellGoods }
								<%--								<input type="text" name="sell.sellGoods"--%>
								<%--									value="${sell.sellGoods }" readonly="readonly" />--%>
							</td>
							<th align="right">
								规格：
							</th>
							<td>
								${sell.sellFormat }
								<%--								<input type="text" name="sell.sellFormat" id="sellFormat"--%>
								<%--									value="${sell.sellFormat }" readonly="readonly" />--%>
							</td>

							</th>
						</tr>
						<tr>
							<th align="right">
								出库量：
							</th>
							<td>
								<input type="text" name="sell.sellCount" id="sellCount" readonly="readonly"
									value="${sell.sellCount }" onchange="numyanzhen(this)" />
								<span id="span"></span>
								<span style="color: red">*</span>
							</td>
							<th align="right">
								计量单位：
							</th>
							<td>
								<%--							danwei--%>
								<select name="sell.sellUnit" id="">
									<option selected="selected" value="${sell.sellUnit }">
										${sell.sellUnit }
									</option>
								</select>
							</td>
							<th align="right">
								库别：
							</th>
							<td>
								${sell.sellWarehouse }
								<%--								<input type="text" name="sell.sellWarehouse" id="sellWarehouse"--%>
								<%--									value="${sell.sellWarehouse }" readonly="readonly" />--%>
							</td>
							<th align="right">
								项目编号：
							</th>
							<td>
								${sell.proNumber}
								<%--								<input type="text" name="sell.proNumber" id="proNumber"--%>
								<%--									value="${sell.proNumber}" readonly="readonly" />--%>
							</td>
						</tr>
						<tr>
							<th align="right">
								转换数量：
							</th>
							<td>

								<input type="text" name="sell.sellZhishu" id="sellZhishu"
									value="${sell.sellZhishu }" onchange="numyanzhen(this)" />
								<span id="span1"></span>
							</td>
							<th align="right">
								转换单位：
							</th>
							<td>
								${sell.goodsStoreZHUnit }
								<%--							danwei1--%>
								<%--								<select name="sell.goodsStoreZHUnit" id="">--%>
								<%--									<option selected="selected" value="${sell.goodsStoreZHUnit }">--%>
								<%--										${sell.goodsStoreZHUnit }--%>
								<%--									</option>--%>
								<%--								</select>--%>
							</td>

							<th align="right">
								出库类型：
							</th>
							<td>
								${sell.style}
								<%--								<select name="sell.style">--%>
								<%--									<option>--%>
								<%--										${sell.style}--%>
								<%--									</option>--%>
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
								<%--								</select>--%>
							</td>
							<th align="right">
								工艺卡号：
							</th>
							<td>
								<input type="text" name="sell.sellArtsCard"
									value="${sell.sellArtsCard }" />
							</td>
						</tr>
						<tr>
							<th align="right">
								出库日期：
							</th>
							<td>
								<input class="Wdate" type="text" id="goodsChangeTime"
									name="sell.sellDate" value="${sell.sellDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
								<span style="color: red">*</span>
							</td>
							<th align="right">
								供应商：
							</th>
							<td>
								${sell.sellSupplier }
								<%--								<input type="text" name="sell.sellSupplier"--%>
								<%--									value="${sell.sellSupplier }" readonly="readonly" />--%>
							</td>
							<th align="right">
								客户：
							</th>
							<td>
								<input type="text" name="sell.sellCompanyName"
									value="${sell.sellCompanyName }" />
							</td>

							<th align="right">
								返回状态：
							</th>
							<td>
								<select name="sell.sellPeople">
									<option>
										${sell.sellPeople}
									</option>
									<option>
										已确认
									</option>
									<option>
										在途
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								领料人：
							</th>
							<td>
								${sell.sellCharger}
								<%--								<input type="text" name="sell.sellCharger"--%>
								<%--									value="${sell.sellCharger}" readonly="readonly" />--%>
							</td>

							<th align="right">
								负责人：
							</th>
							<td>
								${sell.sellGetGoodsMan }
								<%--								<input type="text" name="sell.sellGetGoodsMan"--%>
								<%--									value="${sell.sellGetGoodsMan }"  readonly="readonly"/>--%>
							</td>

							<th align="right">
								供料属性：
							</th>
							<td>
								${sell.kgliao}
								<%--								<select name="sell.kgliao">--%>
								<%--									<option value="${sell.kgliao}">${sell.kgliao}</option>--%>
								<%--									<option value="<s:property value="sell.kgliao" />">--%>
								<%--										<s:if test="sell.kgliao=='TK'">--%>
								<%--										自购(TK)--%>
								<%--										</s:if>--%>
								<%--										<s:elseif test="sell.kgliao=='TK AVL'">--%>
								<%--										指定供应商(TK AVL)--%>
								<%--										</s:elseif>--%>
								<%--										<s:elseif test="sell.kgliao=='CS'">--%>
								<%--										客供(CS)--%>
								<%--										</s:elseif>--%>
								<%--										<s:elseif test="sell.kgliao=='TK Price'">--%>
								<%--										完全指定(TK Price)--%>
								<%--										</s:elseif>--%>
								<%--									</option>--%>
								<%--									<option value="TK">--%>
								<%--										自购(TK)--%>
								<%--									</option>--%>
								<%--									<option value="TK AVL">--%>
								<%--										指定供应商(TK AVL)--%>
								<%--									</option>--%>
								<%--									<option value="CS">--%>
								<%--										客供(CS)--%>
								<%--									</option>--%>
								<%--									<option value="TK Price">--%>
								<%--										完全指定(TK Price)--%>
								<%--									</option>--%>
								<%--								</select>--%>
							</td>
							<th align="right">
								送货单号：
							</th>
							<td>
								<input type="text" name="sell.sellSendnum"
									value="${sell.sellSendnum }" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								内部订单号：
							</th>
							<td>
								${sell.orderNum}
								<%--								<input type="text" name="sell.orderNum" value="${sell.orderNum}"--%>
								<%--									readonly="readonly" />--%>
							</td>
							<th align="right">
								业务件号：
							</th>
							<td>
								${sell.ywmarkId}
								<%--								<input type="text" name="sell.ywmarkId" value="${sell.ywmarkId}"--%>
								<%--									readonly="readonly" />--%>
							</td>

							<th align="right">
								总成批次：
							</th>
							<td>
								${sell.rootSelfCard}
								<%--								<input type="text" name="sell.rootSelfCard"--%>
								<%--									value="${sell.rootSelfCard}" readonly="readonly" />--%>
							</td>
							<th align="right">
								总成件号：
							</th>
							<td>
								${sell.sellProMarkId}
								<%--								<input type="text" name="sell.sellProMarkId"--%>
								<%--									value="${sell.sellProMarkId}" readonly="readonly" />--%>
							</td>
						</tr>
						<tr>
							<th align="right">
								外部订单号：
							</th>
							<td>
								<input type="text" name="sell.outOrderNumer"
									value="${sell.outOrderNumer}" />
							</td>
							<th align="right">
								制单人：
							</th>
							<td>
								${sell.sellAdminName}
							</td>
							<th align="right">
								制单日期：
							</th>
							<td>
								${sell.sellTime}
							</td>
							<th align="right" >
								打印单号：
							</th>
							<td>
								${sell.printNumber}
								<%--								<input type="hidden" name="sell.printNumber"--%>
								<%--									value="${sell.printNumber}" />--%>
							</td>
						</tr>
						<tr>
							<th align="right" align="right">
								备注：
							</th>
							<td colspan="10">
								${sell.sellGoodsMore}
							</td>
						</tr>
						<s:if test='status == "tuihuo"'>
							<tr>
								<th align="right">可退货数量：</th>
								<td	>
									<s:if test="sell.tksellCount!=null && sell.tksellCount>0">
										<input type="text" value="${sell.sellCount-sell.tksellCount}"
										readonly="readonly"/>
									</s:if>
									<s:else>
										<input type="text" value="${sell.sellCount}" 
										readonly="readonly"/>
									</s:else>
								</td>
								<th align="right">退货数量：</th>
								<td	>
									<s:if test="sell.tksellCount!=null && sell.tksellCount>0">
										<input type="text" id="tksellCount" value="${sell.sellCount-sell.tksellCount}" name="sell.tksellCount"
										onchange="numyanzheng(this);daxiao(this,${sell.sellCount-sell.tksellCount})""/>
									</s:if>
									<s:else>
										<input type="text" value="${sell.sellCount}" id="tksellCount" name="sell.tksellCount"
										onchange="numyanzheng(this);daxiao(this,${sell.sellCount})"/>
									</s:else>
								</td>
							</tr>
						</s:if>

						<s:if test='status == "tuihuo"'>
							<tr>
								<th colspan="8">
									<input type="button" id="sub" value="退货" onclick="tuihuo(this.form)"/>
								</th>
							</tr>
						</s:if>
						<s:elseif test='status != "mingxi"'>
							<tr>
								<th colspan="8">
									<input type="submit" value="修改" />
									&nbsp;
									<input type="reset" value="取消" />
								</th>
							</tr>
						</s:elseif>
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
function daxiao(obj,num){
	var  value = obj.value;
	if(value>num){
		alert('退货数量不能大于可退货数量，请重新输入');
		$(obj).val(num);
	}
}
function tuihuo(obj){
	var tksellCount = $("#tksellCount").val();
	if(tksellCount == ''){
		alert('请输入退货数量!~');
		return false;
	}
	$(obj).attr('action','sellAction!ChengPinTuiHuoSq.action');
	$(obj).submit();
	$("#sub").attr('disabled','disabled');
}

</script>
	</body>
</html>
