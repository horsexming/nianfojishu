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
				<h3>设变外购减单操作</h3>
				<div>
					件号：${procardsbwg.markId}&nbsp;&nbsp;版本号：${procardsbwg.markId}&nbsp;&nbsp;减单数量：${procardsbwg.markId}&nbsp;&nbsp;已减数量${procardsbwg.markId}
				</div>
				<div>
					<table class="table">
						<tr>
						<td align="center" bgcolor="red" colspan="10">物料需求单展示</td>
						</tr>
						<tr>
							<th>件号<s:property value="id"/></th>
							<th>名称</th>
							<th>图号</th>
							<th>规格</th>
							<th>供料属性</th>
							<th>物料类别</th>
							<th>订单数量</th>
							<th>已采购数量</th>
							<th>减单数量</th>
							<th>操作</th>
						</tr>
						<s:iterator value="manyalOrderPlanList" id="pagemop" status="mopStatus">
						<form action="procardTemplateGyAction_manyalOrderPlansbld.action" method="post">
						<input type="hidden" name="cpage" value="${cpage}">	
							<input type="hidden" name="id" value="${param.id}">
							<input type="hidden" name="mop.id" value="${pagemop.id}">
						<tr>
							<td align="center">${pagemop.markId}</td>
							<td align="center">${pagemop.proName}</td>
							<td align="center">${pagemop.tuhao}</td>
							<td align="center">${pagemop.specification}</td>
							<td align="center">${pagemop.kgliao}</td>
							<td align="center">${pagemop.wgType}</td>
							<td align="right">${pagemop.number}</td>
							<td align="right">${pagemop.outcgNumber}</td>
							<td align="center"><input id="sbjdCount1_${mopStatus.index}" name="mop.number" onkeyup="checkCount1(${pagemop.number},${pagemop.outcgNumber},${mopStatus.index})"></td>
							<td align="center"><input type="submit" value="减单" style="width: 60px;height: 40px;"></td>
						</tr>
						</form>
						</s:iterator>
					</table>
					<br/>
					<table class="table">
					<tr>
						<td align="center" bgcolor="red" colspan="14">采购订单展示</td>
					</tr>
					<tr>
						<th>采购单号</th>
						<th>状态</th>
						<th>件号</th>
						<th>名称</th>
						<th>图号</th>
						<th>规格</th>
						<th>供料属性</th>
						<th>物料类别</th>
						<th>订单数量</th>
						<th>未送货数量</th>
						<th>扣减申请数量</th>
						<th>减单数量</th>
						<th>操作</th>
						<th>申请进度</th>
					</tr>
						<s:iterator value="waigouPlanList" id="pagePlan" status="planStatu">
						<form action="procardTemplateGyAction_waigouPlansbld.action" method="post">
							<input type="hidden" name="id" value="${param.id}">
							<input type="hidden" name="cpage" value="${cpage}">
							<input type="hidden" name="waigouplan.id" value="${pagePlan.id}">
						<tr>
						<td align="center">${pagePlan.planNumber}</td>
						<td align="center">${pagePlan.status}</td>
						<td align="center">${pagePlan.markId}</td>
						<td align="center">${pagePlan.proName}</td>
						<td align="center">${pagePlan.tuhao}</td>
						<td align="center">${pagePlan.specification}</td>
						<td align="center">${pagePlan.kgliao}</td>
						<td align="center">${pagePlan.wgType}</td>
						<td align="center">${pagePlan.number}</td>
						<td align="center">${pagePlan.syNumber}</td>
						<td align="center">${pagePlan.sbjdApplyCount}</td>
						<td align="center"><input name="waigouplan.sbjdApplyCount" id="sbjdCount2_${planStatu.index}" 
						onchange="numyanzheng(this);checkCount2(${pagePlan.syNumber},${pagePlan.sbjdApplyCount},${planStatu.index})" > </td>
						<td align="center"><input type="submit" value="减单" style="width: 60px;height: 40px;"></td>
						<td>
							<s:if test="#pagePlan.clApplyList!=null">
								<s:iterator value="#pagePlan.clApplyList" id="clapply" status="clStatus">
								<a href="CircuitRunAction_findAduitPage.action?id=${clapply.epId}">${clapply.applyNumber}(${clapply.epStatus})</a><br/>
								</s:iterator>
							</s:if>
						</td>
						</tr>
						</form>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">
function checkCount1(num,outnum,index){
	if(mustBeNumber("sbjdCount1_"+index)){
		var djCount =$("#sbjdCount1_"+index).val();
		if(outnum==null){
			outnum=0;
		}
		if((num-outnum)<djCount){
			alert("数量超额!");
			$("#sbjdCount1_"+index).val(0);
		}
	}
}
function checkCount2(syCount,applyCount,index){
		var djCount =$("#sbjdCount2_"+index).val();
		if(applyCount==null){
			applyCount=0;
		}
		if((syCount-applyCount)<djCount){
			alert("数量超额!");
			$("#sbjdCount2_"+index).val(0);
		}
}
</script>
	</body>
</html>
