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
		<s:if test="ieList==null||ieList.size()<=2">
			<div align="center">
				<br>
			    <form action="QuotedPrice_evaluationartner.action" method="post">
			    <input type="hidden" name='investorEvaluation.iofQpId'  value="${investorOfQp.id}">
				<div><input type="radio" name="investorEvaluation.type"  value="点赞" id="type1"> <label onclick="">点赞</label>
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="investorEvaluation.type"  value="吐槽" id="type2"> <label onclick="">吐槽</label>
				 </div>
				 <div align="center">
				 	评价内容:<textarea name='investorEvaluation.remark' rows="5" cols="40"></textarea>
				 	<br>
				 	<input type="submit" value="提交">
				</div>
			    </form>
			</div>
		</s:if>
			<s:if test="ieList!=null&&ieList.size()>0">
			<div align="center">
				<h3>您对${quotedPrice.zuzhang}组长的历史评价</h3>
			</div>
			<div align="center">
				 <div align="center" style="float: left;width: 40%;">
				 	<font size="3"> 评价类型</font>
				 </div>
				 <div align="left" style="float: left;width: 1%;">
				 	|
				 </div>
				 <div align="center" style="float: left;width: 58%;">
					<font size="3"> 评价内容</font>
				 </div>
				 <div style="clear: both;"><hr></div>
				</div>
			<s:iterator value="ieList" id="pageie">
				<div align="center">
				 <div align="center" style="float: left;width: 40%;">
				 	<h3>${pageie.type}</h3>
				 </div>
				 <div align="left" style="float: left;width: 58%;">
				|&nbsp;&nbsp;&nbsp;	<font size="3"> ${pageie.remark}</font>
				 </div>
				 <div style="clear: both;"><hr/></div>
				</div>
			</s:iterator>
		</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
