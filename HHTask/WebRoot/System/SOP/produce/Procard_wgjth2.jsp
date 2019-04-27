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
	<title>外购件替换物料</title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">信息展示</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="tcIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>外购件替换物料</h3>
				<div align="right">
				<font color="red">注:如不填写下单数量,系统会根据库存自动计算，如填写则以填写的下单数量为准</font>
				</div>
				<div>
					<form action="WaigouwaiweiPlanAction!changeWgj.action" method="post" onsubmit="return validata();">
					<input type="hidden" value="<s:property value="id"/>" name="procard.rootId">
					<input type="hidden" value="<s:property value="markId"/>" name="markId">
					<input type="hidden" value="<s:property value="kgliao"/>" name="kgliao">
						<div align="center" style="float: left;width: 47%;">
							<table class="table">
							<tr><th colspan="6">原物料</th>
							</tr>
							<tr>
							<th>件号</th>
							<th>版本号</th>
							<th>供料属性</th>
							<th>生产数量</th>
							<th>采购数量</th>
							<th>单位</th>
							</tr>
							<tr>
							<td align="center"><s:property value="procard.markId"/> </td>
							<td align="center"><s:property value="procard.banBenNumber"/> </td>
							<td align="center"><s:property value="procard.kgliao"/> </td>
							<td align="right"><s:property value="procard.filnalCount"/></td>
							<td align="right"><s:property value="procard.cgNumber"/></td>
							<td align="center"><s:property value="procard.unit"/></td>
							</tr>
							</table>
						</div>
						<div style="float: left;width: 4%;">
						==>
						</div>
						<div align="center" style="float: left;width: 47%;">
						<table class="table">
							<tr><th colspan="6">替代物料</th>
							</tr>
							<tr>
							<th>件号</th>
							<th>版本号</th>
							<th>供料属性</th>
							<th>使用数量</th>
							<th>下单数量</th>
							<th>单位</th>
							</tr>
							<tr>
							<td align="center"><input name="procard.markId"  id="wajmarkId" readonly="readonly"><input type="button" value="选择" onclick="selectWgj()"></inpu> </td>
							<td align="center"><input name="procard.banBenNumber" style="width: 40px;" id="wajbanBenNumber" readonly="readonly"> </td>
							<td align="center"><input name="procard.kgliao" style="width: 40px;" id="kgliao" readonly="readonly"/></td>
							<td align="center"><input name="procard.filnalCount" style="width: 40px;" id="filnalCount" onkeyup="mustBeNumber('filnalCount')"> </td>
							<td align="center"><input name="procard.cgNumber" style="width: 40px;" id="cgNumber" onkeyup="mustBeNumber('filnalCount')"> </td>
							<td align="center"><input name="procard.unit" style="width: 40px;" id="danwei2" readonly="readonly"> </td>
							</tr>
							</table>
						</div>
						<div style="clear: both;" align="center">
						<br/>
						<s:if test="procard!=null">
							<input id="sbtn" type="submit"  value="确定" style="width: 80px;height: 40px;" >
						</s:if>
						<s:else><font color="red">无法替换:请检查是否已经领料!</font> </s:else>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function selectWgj(){
	$("#tcIframe").attr("src",
			"procardTemplateGyAction_showYclAndWgj.action?type=wgj2");
	chageDiv('block');
}
function  validata(){
	//var wajmarkId =$("#wajmarkId").val();
	//var ymarkId = "${procard.markId}";
	var wajmarkId =$("#wajmarkId").val();
	var ymarkId = "${procard.markId}";
	var kgliao =$("#kgliao").val();
	var ykgliao = "${procard.kgliao}";
	var banben =$("#wajbanBenNumber").val();
	var ybaneben = "${procard.banBenNumber}";
	if(wajmarkId==null||wajmarkId==""){
		alert("请选择替换的外购件!");
		return false;
	}else if(wajmarkId==ymarkId&&kgliao==ykgliao&&banben==ybaneben){
		alert("相同零件，无需替换!");
		return false
	}
	$("#sbtn").attr("disabled","disabled");
	return true;
}
</SCRIPT>
	</body>
</html>
