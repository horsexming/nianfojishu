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
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h2>
					变更物料需求数量
				</h2>
				<table class="table">
					<tr>
						<th align="right">
							物料类别
						</th>
						<td>
							${manualPlan.wgType}
						</td>
					</tr>
					<tr>
						<th align="right">
							件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号
						</th>
						<td>
							${manualPlan.markId}
						</td>
					</tr>
					<tr>
						<th align="right">
							名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
						</th>
						<td>
							${manualPlan.proName}
						</td>
					</tr>
					<tr>
						<th align="right">
							采购类别
						</th>
						<td>
							${manualPlan.category }
						</td>
					</tr>
					<tr>
						<th align="right">
							规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格
						</th>
						<td>
							${manualPlan.specification}
						</td>
					</tr>
					<tr>
						<th align="right">
							单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位
						</th>
						<td>
							${manualPlan.unit}
						</td>
					</tr>
					<tr>
						<th align="right">
							供料属性
						</th>
						<td>
							${manualPlan.kgliao}
						</td>
					</tr>
					<tr>
						<th align="right">
							版&nbsp;&nbsp;本&nbsp;&nbsp;号
						</th>
						<td>
							${manualPlan.banben}
						</td>
					</tr>
					<tr>
						<th align="right">
							状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态
						</th>
						<td>
							${manualPlan.status }
						</td>
					</tr>
					<tr>
						<th align="right">
							需&nbsp;&nbsp;求&nbsp;&nbsp;量
						</th>
						<td>
							${manualPlan.number }
							<input type="hidden" value="${manualPlan.number }" id="demandNum" />
						</td>
					</tr>
					<tr>
						<th align="right">
							已采购数量
						</th>
						<td>
							${manualPlan.outcgNumber}
						</td>
					</tr>
					<s:if test="tag==null || tag!='search'">
						<tr>
							<th align="right">
								取消数量
							</th>
							<td>
								<input type="text"
									value="<fmt:formatNumber value='${manualPlan.number-manualPlan.outcgNumber}' pattern='#.0000'/>"
									id="number">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="变更数量" class="input" id="buttonss"
									onclick="changeNumber()">
								<input type="hidden" value="${manualPlan.id}" id="planId">
								<input type="button" value="返回" class="input"
									onclick="javascript:history.back(-1);">
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<th align="right">
								取消数量
							</th>
							<td>
								${manualPlan.cancalNum }
							</td>
						</tr>
					</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function changeNumber(){
	var number = $("#number").val();
	var planId = $("#planId").val();
	var demandNum = $("#demandNum").val();//需求数量
	if(number<0){
		alert("变更数量必须大于0");
		return;
	}else if(parseFloat(number)>parseFloat(demandNum)){
		alert("取消数量不能大于需求数量");
		return ;
	}
	if(number==null || number==""){
		alert("请填写数量");
		return;
	}
	$("#buttonss").attr("disabled",true);
	$.ajax({
		type:"POST",
		url:"${pageContext.request.contextPath}/ManualOrderPlanAction_changePlanNumber.action",
		data:{
			"manualPlan.id":planId,
			"num":number
		},
		dataType:"json",
		success:function(data){
			alert(data);
			if(data.indexOf("成功")>0){
				window.location.href="ManualOrderPlanAction_findmopList.action?tag=${tag}&cpage=${cpage}";
			}
		},error:function(){
			alert("系统异常");
		}
	});
}


function quxiao(id){
	if(confirm('确定要取消物料需求吗?')){
		window.location.href="ManualOrderPlanAction_quexiao.action?id="+id;
	}
	//window.location.href="ManualOrderPlanAction_getOrderPlanById.action?id="+id+"&tag=${tag}";
}
</SCRIPT>
	</body>
</html>
