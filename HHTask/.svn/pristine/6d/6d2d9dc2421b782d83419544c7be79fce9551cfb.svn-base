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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
				        项目材料清单<br/>(add project material order)
				</h3>
				<form action="projectMaterialOrderAction_add.action"
					method="post" onsubmit="return validate();">
					<table id="addTable" width="100%" border="1" style="border-collapse: collapse;">
						<tr>
						   <td align="center" colspan="4">
								项目名称（project Name）：
							</td>
							<td align="center" colspan="6" >
								<s:property value="projectMaterialOrder.proName"/>
							</td>
						</tr>
						<tr>
						   <td align="center" colspan="4">
								领取状态（receive status）：
							</td>
							<td align="center" colspan="6" >
								<s:property value="projectMaterialOrder.receiveStatus"/>
							</td>
						</tr>
						<tr>
						   <td align="center" colspan="4">
								备注（remark）：
							</td>
							<td align="center" colspan="6" >
								<s:property value="projectMaterialOrder.remark"/>
							</td>
						</tr>
						<tr>
						 <td align="center">
								项目名称（part Number）
							</td>
						   <td align="center">
								件号（part Number）
							</td>
							<td align="center">
								名称（part Name）
							</td>
							<td align="center">
								牌号（trademark）
							</td>
							<td align="center">
								规格（specification）
							</td>
							<td align="center">
								数量（need Number）
							</td>
							<td  align="center">
							     实发（real count）
							</td>
							<td align="center">
								单位（part unit）
							</td>
							<td align="center">
								供应商（supplier）
							</td>
							<td align="center">
								可领（receive）
							</td>
						</tr>
						<s:iterator value="pmList" id="pm" status="index">
						<tr >
						<td align="center">
								<s:property value="#pm.proName"/>
							</td>
						   <td align="center">
								<s:property value="#pm.markId"/>
							</td>
							<td align="center">
								<s:property value="#pm.materialName"/>
							</td>
							<td align="center">
								<s:property value="#pm.paihao"/>
							</td>
							<td align="center">
							<s:property value="#pm.guige"/>
							</td>
							<td align="center">
								<s:property value="#pm.needNumber"/>
							</td>
							<td align="center">
							<input type="hidden" id="pmid<s:property value='#index.index'/>" value="<s:property value="#pm.id"/>">
							<s:if test="#pm.canReceive=='是'.toString()&&#pm.isGuanLiao=='yes'">
								<input value="0" id="shifa<s:property value='#index.index'/>" onkeyup="checkshifacount('<s:property value="#pm.paihao"/>','<s:property value="#pm.guige"/>',<s:property value='#index.index' />)">
							/<s:property value="#pm.shifaUnit"/>
							</s:if>
							<s:else>
								<input value="<s:property value="#pm.shifa"/>" id="shifa<s:property value='#index.index'/>" readonly="readonly"">
							</s:else>
							</td>
							<td align="center">
							<s:property value="#pm.unit"/>
							</td>
							<td align="center">
							<s:property value="#pm.supplier"/>
							</td>
							<td align="center">
							<s:property value="#pm.canReceive"/>
							<s:if test="#pm.canReceive=='否'.toString()">
							<font color="red">（库存不足）</font>
							</s:if>
							</td>
						</tr>
							</s:iterator>
				<s:if test="%{cannotreceive==0}">
				<tr>
				<td colspan="10" align="center">
				 领料人:
				 <input type="text" id="receiveuser" />
				</td>
				</tr>
				<tr>
				<td colspan="10" align="center">
				 <input type="button" value="领取（receive）" onclick="receiveMaterial();" class="input"/>
				</td>
				</tr>
				</s:if>
					</table>
				</form>
				<input type="hidden" value="<s:property value='successMessage'/>" id="successMessage">
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function receiveMaterial(){
			var listSize="<s:property value='pmList.size()'/>";
			var idshifa="";
			var receiveuser=$("#receiveuser").val();
			if(receiveuser==null||receiveuser==""){
				alert("领料人不能为空!");
				return;
			}
			for(var i=0;i<listSize;i++){
				if(($("#shifa"+i).val()-0)==0){
					alert("实发数不能为0");
					return;
				}
				if(i==(listSize-1)){
					idshifa=idshifa+$("#pmid"+i).val()+":"+$("#shifa"+i).val();
				}else{
					idshifa=idshifa+$("#pmid"+i).val()+":"+$("#shifa"+i).val()+",";
				}
				
			}
			//alert(idshifa);
			$.ajax({
				type : "POST",
				url : "projectMaterialOrderAction_receiveMaterial.action",
				dataType : "json",
				data : {
				    receiveuser : receiveuser,
				    idshifa : idshifa,
					'projectMaterialOrder.id' : ${projectMaterialOrder.id}
				},
				success : function(msg) {
					alert(msg.message);
					if(msg.success){
			           window.location.href="sellAction!findSellByCondition.action";
					}
				}
			})
		}
		//核实实发数量
function checkshifacount(goodsMarkId,goodsFormat,obj){
	var shifa=$("#shifa"+obj).val();
	$.ajax({
		type : "POST",
		url : "projectMaterialOrderAction_checkshifacount.action",
		dataType : "json",
		data : {
		   goodsMarkId : goodsMarkId,
		   goodsFormat : goodsFormat,
		   shifa : shifa
		},
	   success : function(data){
		 if(!data.success){
		   alert("库存不足！");
		   $("#shifa"+obj).val(0);
		 }
	   }
	});
}
		 
		</SCRIPT>
	</body>
</html>
