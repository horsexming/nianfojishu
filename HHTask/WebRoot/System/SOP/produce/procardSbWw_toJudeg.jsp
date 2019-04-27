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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 90%; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">设变相关委外</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcardDetail" src="" marginwidth="0"
						marginheight="0" hspace="0" vspace="0" frameborder="0"
						scrolling="yes"
						style="width: 98%; height: 900px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>设变外委判定</h3>
				<br/>
				<table class="table">
					<tr>
						<td align="center" colspan="7">原由</td>
					</tr>
					<tr>
						<th>件号
						</th>
						<th>批次
						</th>
						<th>类型
						</th>
						<th>名称
						</th>
						<th>版本号
						</th>
						<th>图号
						</th>
						<th>设变类型
						</th>
					</tr>
					<s:iterator value="procardSbWw.procardSbWwDetailList" id="pagewwsbd">
					<td>${pagewwsbd.markId}
					</td>
					<td>${pagewwsbd.selfCard}
					</td>
					<td>${pagewwsbd.procardStyle}
					</td>
					<td>${pagewwsbd.proName}
					</td>
					<td>${pagewwsbd.banBenNumber}
					</td>
					<td>${pagewwsbd.tuhao}
					</td>
					<td>${pagewwsbd.sbtype}
					</td>
					</s:iterator>
					<tr>
					</tr>
				</table>
				<br/>
				<table class="table">
				<tr>
					<td align="center" colspan="13">采购订单</td>
				</tr>
				<tr>
					<td align="center">订单编号</td>
					<td align="center">供应商</td>
					<td align="center">来源</td>
					<td align="center">件号</td>
					<td align="center">工序号</td>
					<td align="center">工序名称</td>
					<td align="center">数量</td>
					<td align="center">未送货数量</td>
					<td align="center">外委类型</td>
					<td align="center">单位</td>
					<td align="center">单价</td>
					<td align="center">金额</td>
					<td align="center">操作</td>
				</tr>
				<s:if test="waigouplan!=null">
				<tr>
					<td align="left">${waigouplan.planNumber}</td>
					<td align="left">${waigouplan.gysName}</td>
					<td align="left">${waigouplan.wwSource}</td>
					<td align="left">${waigouplan.markId}</td>
					<td align="left">${waigouplan.processNOs}</td>
					<td align="left">${waigouplan.processNames}</td>
					<td align="left">${waigouplan.number}</td>
					<td align="right">${waigouplan.syNumber}</td>
					<td align="right">${waigouplan.wwType}</td>
					<td align="left">${waigouplan.unit}</td>
					<td align="right">${waigouplan.hsPrice}</td>
					<td align="left">
					<input type="button" value="取消 " onclick="breakwP(${waigouplan.id})">
					<input type="button" value="变更 " onclick="updatewP(${waigouplan.id})">
					<input type="button" value="继续 " onclick="continuewP(${waigouplan.id})">
					</td>
				</tr>
				</s:if>
				<s:else>
					<s:if test="wwApplyDetail!=null">
						<tr><td align="center"  colspan="13"><font color="red">未生成外委订单(手动)</font> </td>
						</tr>
					<tr>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left">手动外委</td>
						<td align="left">${wwApplyDetail.markId}</td>
						<td align="left">${wwApplyDetail.processNOs}</td>
						<td align="left">${wwApplyDetail.processNames}</td>
						<td align="left">${wwApplyDetail.applyCount}</td>
						<td align="right">${wwApplyDetail.applyCount}</td>
						<td align="right">${wwApplyDetail.wwType}</td>
						<td align="left">${wwApplyDetail.unit}</td>
						<td align="right"></td>
						<td align="left">
						<input type="button" value="取消 " onclick="breaksdd(${wwApplyDetail.id})">
						<input type="button" value="变更 " onclick="updatesdd(${wwApplyDetail.id})">
						<input type="button" value="继续 " onclick="continuesdd(${wwApplyDetail.id})">
					</tr>
					</s:if>
					<s:if test="waigouWaiweiPlanList!=null&&waigouWaiweiPlanList.size()>0">
					<tr>
						<td align="center"  colspan="13"><font color="red">未生成外委订单(BOM)</font> </td>
						<s:iterator value="waigouWaiweiPlanList" id="pagewwp" >
						<tr>
						<td align="left"></td>
						<td align="left"></td>
						<td align="left">BOM外委</td>
						<td align="left">${pagewwp.markId}</td>
						<td align="left">${pagewwp.processNo}</td>
						<td align="left">${pagewwp.processName}</td>
						<td align="left">${pagewwp.beginCount}</td>
						<td align="right">${pagewwp.number}</td>
						<td align="right">工序委外</td>
						<td align="left">${pagewwp.unit}</td>
						<td align="right"></td>
						<td align="left">
						<input type="button" value="取消 " onclick="breakbomwwp(${wwApplyDetail.id})">
						<input type="button" value="变更 " onclick="updatebomwwp(${wwApplyDetail.id})">
						<input type="button" value="继续 " onclick="continuebomwwp(${wwApplyDetail.id})">
					</tr>
						</s:iterator>
					</tr>
				</s:if>
				</s:else>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function breakwP(id){
	window.confirm("是否确定要取消此订单?"){
		window.location.href="ProcardTemplateAction!wwsbbreakwP.action?id=${procardSbWw.id}&id2="+id;
	}
}
function updatewP(id){
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!toUpdateSbwp.action?id=${procardSbWw.id}&id2="+id);
	chageDiv('block');
}
function continuewP(id){
	if(confirm("确定继续此订单?")){
		window.location.href="ProcardTemplateAction!jxsbwp.action?id=${procardSbWw.id}&id2="+id;
	}
}


function breaksdd(id){
	window.confirm("是否确定要取消此委外申请?"){
		window.location.href="ProcardTemplateAction!wwsbbreaksdd.action?id=${procardSbWw.id}&id2="+id;
	}
}
function updatesdd(id){
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!toUpdatesdd.action?id=${procardSbWw.id}&id2="+id);
	chageDiv('block');
}
function continuesdd(id){
	if(confirm("确定继续此委外申请?")){
		window.location.href="ProcardTemplateAction!jxsbsdd.action?id=${procardSbWw.id}&id2="+id;
	}
}



function breakbomwwp(id){
	window.confirm("是否确定要取消此BOM委外序列?"){
		window.location.href="ProcardTemplateAction!wwsbbreakbomwwp.action?id=${procardSbWw.id}&id2="+id;
	}
}
function updatebomwwp(id){
	$("#showProcardDetail").attr("src",
			"ProcardTemplateAction!toUpdatebomwwp.action?id=${procardSbWw.id}&id2="+id);
	chageDiv('block');
}
function continuebomwwp(id){
	if(confirm("确定继续此委外序列?")){
		window.location.href="ProcardTemplateAction!jxsbbomwwp.action?id=${procardSbWw.id}&id2="+id;
	}
}
</SCRIPT>
	</body>
</html>
