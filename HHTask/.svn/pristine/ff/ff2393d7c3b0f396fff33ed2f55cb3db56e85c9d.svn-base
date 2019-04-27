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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				件号:<s:property value="procardSbWaster.markId"/>&nbsp;&nbsp;&nbsp;
				工序号:<s:property value="procardSbWaster.processNo"/>&nbsp;&nbsp;&nbsp;
				工序名称:<s:property value="procardSbWaster.processName"/>&nbsp;&nbsp;&nbsp;
				总数量:<s:property value="procardSbWaster.finalCount"/>/<s:property value="procardSbWaster.unit"/>&nbsp;&nbsp;&nbsp;
				待处理数量<s:property value="procardSbWaster.bfCount"/>/<s:property value="procardSbWaster.unit"/>&nbsp;&nbsp;&nbsp;
				<br/>
				<div align="center">
				<form method="post" action="procardTemplateGyAction_judegWaster.action">
				<input type="text" name="procardSbWaster.id" value="${procardSbWaster.id}">
				<table class="table">
					<tr>
						<td align="center" colspan="10">处理</td>
					</tr>
					<tr>
							<td colspan="10">
								<input type="radio" name="procardSbWaster.trueClType" value="报废" onclick="changecltype('bf')" checked="checked">报废&nbsp;&nbsp;
								<input type="radio" name="procardSbWaster.trueClType"  value="退库" onclick="changecltype('tk')">退库
							</td>
						</tr>
					<tr class="xctr" style="display: none;">
						<td>件号</td>
						<td>批次</td>
						<td>名称</td>
						<td>类型</td>
						<td>版本号</td>
						<td>批次总数量</td>
						<td>需要处理</td>
						<td>权值比例</td>
						<td>待处理数量</td>
						<td>报废数量</td>
						<td>退库数量</td>
					</tr>
						<s:iterator value="procardSbWaster.procardSbWasterxcList" id="pagexc" status="pagestatus">
						<tr class="xctr" style="display: none;">
							<td>${pagexc.markId}<input type="hidden" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].id" value="${pagexc.id}"></td>
						<td>${pagexc.selfCard}</td>
						<td>${pagexc.proName}</td>
						<td>${procardSbWaster.procardStyle}</td>
						<td >${pagexc.banbenNumber}</td>
						<td >
						<s:if test="#pagexc.xcType=='本身'">
						<input type="hidden" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].needcl" value="是" />是
						</s:if>
						<s:else>
						<input type="radio" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].needcl" value="是" onclick="isneedcl(${pagestatus.index},'yes')" checked="checked">是&nbsp;&nbsp;
						<input type="radio" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].needcl"  value="否" onclick="isneedcl(${pagestatus.index},'no')">否
						</s:else>
						</td>
						<td align="right">
						<s:if test="#pagexc.procardStyle=='外购'">
						<s:property value="#pagexc.quanzi1"/>:<s:property value="#pagexc.quanzi2"/>
						</s:if>
						<s:else>
						1:<s:property value="#pagexc.corrCount"/>
						</s:else>
						</td>
						<td align="right">${pagexc.finalCount}</td>
						<td>
						<s:if test="#pagexc.xcType=='本身'">
						<input id="bfclCount${pagestatus.index}" type="text" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].bfclCount" value="0"> 
						</s:if>
						<s:else>
						<input id="bfclCount${pagestatus.index}" type="text" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].bfclCount" value="0" style="display: none;"> 
						</s:else>
						</td>
						<td>
						<s:if test="#pagexc.xcType=='本身'">
						<input id="tkclcount${pagestatus.index}" type="text" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].tkclcount" value="${pagexc.glCount}">
						</s:if>
						<s:else>
						<input id="tkclcount${pagestatus.index}" type="text" name="procardSbWaster.procardSbWasterxcList[${pagestatus.index}].tkclcount" value="${pagexc.glCount}" style="display: none;">
						</s:else>
						</td>
						</tr>
						</s:iterator>
					<tr>
						<td><input type="submit" value="确定"> 
						</td>
					</tr>
				</table>
				</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changecltype(type){
	if(type=="tk"){
		$(".xctr").show();
	}else{
		$(".xctr").hide();
	}
}
function isneedcl(index,needcl){
	if(needcl=="yes"){
		$("#bfclCount"+index).show();
		$("#tkclcount"+index).show();
	}else{
		$("#bfclCount"+index).hide();
		$("#tkclcount"+index).hide();
	}
}
</SCRIPT>
	</body>
</html>
