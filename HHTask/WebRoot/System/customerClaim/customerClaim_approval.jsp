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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<form id="form1" action="customerClaimAction_approval.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								索赔单号
							</th>
							<td>
						  		<input type="hidden" name="customerClaim.id" value="<s:property value="customerClaim.id"/>">
							   <input name="customerClaim.claimNumber" id="claimNumber"  readonly="readonly" value="<s:property value="customerClaim.claimNumber"/>"/>
							</td>
							<th>
								客户
							</th>
							<td>
						  		<input type="hidden" name="customerClaim.id" value="<s:property value="customerClaim.id"/>">
							    <input name="customerClaim.otherCompany"  value="<s:property value="customerClaim.otherCompany"/>" id="otherCompany"/> 
							</td>
						</tr>
						<tr>
						<th>
								索赔标题
							</th>
							<td>
								<input name="customerClaim.title" id="title"  value="<s:property value="customerClaim.title"/>"/>
							</td>
						</tr>
						<tr>
							<th>
								索赔内容
							</th>
							<td colspan="3">
								<textarea rows="4" cols="100" name="customerClaim.context" id="context"><s:property value="customerClaim.context"/></textarea>
							</td>
						</tr>
						
							<tr>
								<th>
									分析方案
								</th>
								<td colspan="3">
									<textarea rows="4" cols="100" name="customerClaim.analysisText" id="context"><s:property value="customerClaim.analysisText"/></textarea>
								</td>
							</tr>
						<tr>
							<th>
								分析文件
							</th>
							<td colspan="2" align="center">
								
									<s:if test="customerClaim.analysisFile!=null">
										<a
<%--											href="<%=path%>/upload/file/analysis/<s:property value="customerClaim.analysisFile"/>">下载分析文件</a>--%>
											href="FileViewAction.action?FilePath=/upload/file/analysis/<s:property value="customerClaim.analysisFile"/>">下载分析文件</a>
									</s:if>
									<s:else>无分析文件
				                    </s:else>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="hidden" name="tag"
									value="<s:property value="tag"/>">
								<input type="hidden" name="customerClaim.id"
									value="<s:property value="customerClaim.id"/>">
								<input id="status" type="hidden" name="customerClaim.status"
									value="<s:property value="customerClaim.status"/>">
								<input type="button" value="修改"
									onclick="toSubmit('yes','${tag}')">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
	 function toSubmit(val,tag){
		 if(tag=="approval1"){
			 if(val=='yes'){
				 $("#status").val("完成");
			 }else{
				 $("#status").val("不合格");
			 }
		 }else{
			 if(val=='yes'){
				 $("#status").val("改进通过");
			 }else{  
				 $("#status").val("不合格");
			 }
		 }
		  $("#form1").submit();
	 }
	 
	</SCRIPT>
	</body>
</html>
