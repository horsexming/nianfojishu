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
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%; margin-top: 10px;">
			<div align="center">
				<h3>
					添加预付款单
					<br />
					<s:if test="successMessage!=null">
						<font color="red">${successMessage}</font>
					</s:if>
				</h3>
				<form action="zhaobiaoAction!addPrepaymentsApplicationDetail.action" method="post"
					onsubmit="return adddata()">
					<table class="table" align="center">
						<tr>
							<td align="right">
								预付项目名称：
							</td>
							<td align="left">
								<input type="text" name="prepayApp.yyName" id="yyName"
									value="${prepayApp.yyName}" />
							</td>
							<td align="right">
								采购总额：
							</td>
							<td align="left">
							<fmt:formatNumber value="${prepayApp.allMoney}" pattern="#.00" type="number" />
								<input  type="hidden" id="zongallMoney" name="prepayApp.allMoney"
									value="<fmt:formatNumber value="${prepayApp.allMoney}" pattern="#.00" type="number" />" 
									 />
<%--							</s:else>--%>
							</td>
						</tr>
						<tr>
							<td align="right">
								预付比例：
							</td>
							<td align="left">
								<input type="text" id="zongyfbl" name="prepayApp.yfbl"
									onblur="changvalue2()"
									/>
<%--									onkeyup="changvalue2()" --%>
								%
								<font color="red">*</font>
								<font id="show1" color="red" style="display: none">
									预付款比例需小于${coun}%</font>
							</td>
							<td align="right">
								预付金额：
							</td>
							<td align="left">
								<input type="text" id="zongyfMoney" name="prepayApp.yfMoney"
								 readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td align="right">
								票据类别：
							</td>
							<td align="left">
								<SELECT name="prepayApp.pjType">
									<option value="">
										票据类别
									</option>
									<option value="支票">
										支票
									</option>
									<option value="电汇">
										电汇
									</option>
								</SELECT>
							</td>
							<td align="right">
								预计报销日期：
							</td>
							<td align="left">
								<input class="Wdate" type="text" id="expectedTime"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
 									name="prepayApp.expectedTime" value="${prepayApp.expectedTime}" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								订单编号：
							</td>
							<td align="left" style="word-break:break-all" colspan="3">
								${prepayApp.poNumber}
								<input type="hidden" name="prepayApp.poNumber" value="${prepayApp.poNumber}" />
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td align="center" colspan="4">--%>
<%--								<input type="submit"--%>
<%--									style="width: 100px; height: 40px; margin-left: 70px;"--%>
<%--									value="添加(add)" />--%>
<%--							</td>--%>
<%--						</tr>--%>
					</table>
				
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							采购总额
						</td>
						<td align="center">
							预付金额
						</td>
						<td align="center">
							预付比例
						</td>
					</tr>

					<s:iterator value="prepaymentsApplicationDetailsList" id="samples" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
							<input type="hidden" id="orderId${pageStatus.index}" name="prepaymentsApplicationDetailsList[${pageStatus.index}].orderId" value="${samples.orderId}" 
								 readonly="readonly" />
						</td>
						<td align="center">
						<input type="hidden" id="yyName" name="prepaymentsApplicationDetailsList[${pageStatus.index}].yyName" value="${samples.yyName}" 
								 readonly="readonly" />
							${samples.yyName}
						</td>
						<td align="center">
						<input type="hidden" id="poNumber" name="prepaymentsApplicationDetailsList[${pageStatus.index}].poNumber" value="${samples.poNumber}" 
								 readonly="readonly" />
							${samples.poNumber}
						</td>
						<td align="center">
						<input type="hidden" id="allMoney${pageStatus.index}" name="prepaymentsApplicationDetailsList[${pageStatus.index}].allMoney" value="${samples.allMoney}" 
								 readonly="readonly" />
							${samples.allMoney}
						</td>
						<td align="center">
						<input type="text" id="yfMoney${pageStatus.index}" name="prepaymentsApplicationDetailsList[${pageStatus.index}].yfMoney" value="${samples.yfMoney}" 
								 readonly="readonly" style="border-width:0;"/>
						</td>
						<td align="center">
						<input type="text" id="yfbl${pageStatus.index}" name="prepaymentsApplicationDetailsList[${pageStatus.index}].yfbl" value="${samples.yfbl}" 
								 readonly="readonly" style="border-width:0;"/>%
						</td>
						
					</s:iterator>
					<tr>
							<td colspan="12" align="center" style="color: red">
							<input type="submit" value="添加" style="height:30PX;width:60px">
						</td>
						</tr>
				</table>
				</form>
				
				<table class="table">
				<tr>
					<td colspan="12" align="center" style="color: white;background-color: green;">
					<h3>订单相关历史预付款明细</h3>
						</td>
						</tr>
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							项目名称
						</td>
						<td align="center">
							订单编号
						</td>
						<td align="center">
							预付金额
						</td>
						<td align="center">
							预付比例
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center">
							添加人
						</td>
					</tr>

					<s:iterator value="prepaymentsApplicationDetailsList1" id="sample" status="page">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#page.index+1" />
						</td>
						<td align="center">
							${sample.yyName}
						</td>
						<td align="center">
							${sample.poNumber}
						</td>
						<td align="center">
							${sample.yfMoney}
						</td>
						<td align="center">
							${sample.yfbl}%
						</td>
						<td align="center">
							${sample.addTime}
						</td>
						<td align="center">
							${sample.addName}
						</td>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
<%--$(function() {--%>
<%--	var errorMessage = '${errorMessage}';--%>
<%--	if (errorMessage != "") {--%>
<%--		parent.location.reload(true);//刷新父页面--%>
<%--	}--%>
<%--});--%>
function adddata() {
	if (!validateText("yyName", "预付项目名称")) {
		return false;
	}
	if (!validateText("zongallMoney", "采购总额")) {
		return false;
	}
	if (!validateText("zongyfbl", "预付比例")) {
		return false;
	}
	var zong = $("#zongyfbl").val();
	if(zong<=0){
		alert("预付款比例不能小于0");
		return false;
	}
	if (!validateText("expectedTime", "预计报销日期")) {
		return false;
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
function changvalue2(){
	var zongyfbl = $("#zongyfbl").val();
	if(zongyfbl>100||zongyfbl<0){
		$("#zongyfbl").val("");
		alert("请输入0-100之间的数");
	}
	var orderId=new Array();
	var size =<s:property value="prepaymentsApplicationDetailsList.size()"/>;
	for(var i=0; i<size; i++){
		 orderId[i] = $("#orderId"+i).val();
	}
	$.ajax({  
    url:"zhaobiaoAction!findBeyond.action",    //请求的url地址  
    dataType:"json",   //返回格式为json  
    async:true,//请求是否异步，默认为异步，这也是ajax重要特性  
    data:{
		orderId:orderId,
		coun:zongyfbl
		},    //参数值  
    type:"POST",   //请求方式 get 或者post  
  	traditional: true,
    success:function(data){
			if(data!=null){
				alert(data);
				zongyfbl = 0;
				var zongallMoney = $("#zongallMoney").val();
				$("#zongyfMoney").val(zongallMoney*(zongyfbl/100));
				$("#zongyfbl").val(0);
				for(var i=0; i<size; i++){
					$("#yfbl"+i).val(zongyfbl);
					$("#yfMoney"+i).val(zongyfbl/100*$("#allMoney"+i).val());
				}
			}else{
				var zongallMoney = $("#zongallMoney").val();
				$("#zongyfMoney").val(zongallMoney*(zongyfbl/100));
				for(var i=0; i<size; i++){
					$("#yfbl"+i).val(zongyfbl);
					$("#yfMoney"+i).val(zongyfbl/100*$("#allMoney"+i).val());
				}
			}
        
    },  
});	
}
</script>
	</body>
</html>
