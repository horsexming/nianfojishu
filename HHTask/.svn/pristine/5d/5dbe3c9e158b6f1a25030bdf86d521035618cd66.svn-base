<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在对品质管理进行操作</span>
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
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng"
		style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong"
			style="width: 100%; font-weight: bold; height: 50px; "
			align="left">
			<div
				style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
				align="left">
				
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="" style="color: #ffffff">刷新</a>
			</div>
		</div>
		
		<div align="center">
			<h3>电子签名管理</h3>
				<form action="signatureAction_findSignature.action?test=<s:property value="test"/>" method="post" >
				<table class="table" >
					<tr>
						<td align="center">工号： <input type="text" name="signature.code" />
						</td>
						<td align="center"> 姓名： <input type="text" name="signature.name" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="submit" style="width: 100px; height: 40px;" value="查询"  class="input" />
						<input type="button" style="width: 100px; height: 40px;" value="添加"  class="input"  onclick="add(<s:property value='test'/>)"/>
						 </td>
					</tr>
				</table>
			</form>
			
			<table  class="table">
				<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">序号</td>
					<td align="center">工号</td>
					<td align="center">姓名</td>
					<td align="center">默认状态</td>
					<td align="center">电子签名</td>
					<td align="center">操作</td>
					<td></td>
				</tr>
				<s:iterator value="maps" id="pageList" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
					<td><s:if test="#pageStatus.index%2==1">
							<font>
						</s:if> <s:else>
							<font color="#c0dcf2">
						</s:else> <s:property value="#pageStatus.index+1" /> </font></td>
					<td>${pageList.code}</td>
					<td>${pageList.name}</td>
					<td>${pageList.default_address}</td>
					<td><img src="<%=basePath%>${pageList.signature_address}"  height="60px;" width="100px;" align="middle" ></td>
					<td>
<%--					<a href="DownAction.action?fileName=${pageList.signature_address}&directory=${pageList.signature_address}">下载</a>--%>
						<a onclick="update(${pageList.id})">修改</a>/
						<a href="signatureAction_findHistorySignature.action?signature.id=${pageList.id}&test=<s:property value="test"/>">查看历史</a>/
					</td>
				</s:iterator>
				</tr>
				<tr>
					<s:if test="errorMessage==null">
						<td colspan="12" align="right">第 <font color="red"><s:property
									value="cpage" /> </font> / <s:property value="total" /> 页 <fenye:pages
								cpage="%{cpage}" total="%{total}" url="%{url}" styleClass="page"
								theme="number" />
					</s:if>
					<s:else>
						<td colspan="11" align="center" style="color: red">
							${errorMessage}
					</s:else>
					</td>
				</tr>
			</table>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<script type="text/javascript"  src="<%=request.getContextPath()%>/javascript/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	function add(obj) {
		var url = "<%=request.getContextPath()%>/System/signature/addSignature.jsp?test="+obj;
		//var url = "<%=request.getContextPath()%>/System/signature/addSignature.jsp?test=aaa";
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
	
	function update(obj){
		var url = "signatureAction_findSignatureByid.action?sal_id="+obj;
		$("#showProcess").attr("src", url);	
		chageDiv('block');
	}
</script>
</body>
</html>
