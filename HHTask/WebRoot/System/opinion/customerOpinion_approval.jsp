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
				<form id="form1" action="customerOpinionAction_approval.action"
					method="post">
					<table class="table">
						<tr>
							<th>
								客户
							</th>
							<td>
								<s:property value="customerOpinion.otherCompany" />
							</td>
							<th>
								投诉标题
							</th>
							<td>
								<s:property value="customerOpinion.title" />
							</td>
						</tr>
						<tr>
							<th>
								投诉内容
							</th>
							<td colspan="3">
								<s:property value="customerOpinion.context" />
							</td>
						</tr>
						<s:if test="tag=='approval1'">
							<tr>
								<th>
									分析方案
								</th>
								<td colspan="3">
									<s:property value="customerOpinion.analysisText" />
								</td>
							</tr>
						</s:if>
						<s:elseif test="tag=='approval2'">
							<tr>
								<th>
									改进方案
								</th>
								<td colspan="3">
									<s:property value="customerOpinion.executiveText" />
								</td>
							</tr>
						</s:elseif>
						<tr>
							<th>
								审批时间
							</th>
							<td>
								<input id="analysisTime" type="text" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									name="customerOpinion.analysisTime" />
							</td>
							<td colspan="2" align="center">
								<s:if test="tag=='approval1'">
									<s:if test="customerOpinion.analysisFile!=null">
										<a
<%--											href="<%=path%>/upload/file/analysis/<s:property value="customerOpinion.analysisFile"/>">下载分析文件</a>--%>
											href="FileViewAction.action?FilePath=/upload/file/analysis/<s:property value="customerOpinion.analysisFile"/>">查看分析文件</a>
									</s:if>
									<s:else>无分析文件
				                    </s:else>
								</s:if>
								<s:elseif test="tag=='approval2'">
									<s:if test="customerOpinion.executiveFile!=null">
										<a
<%--											href="<%=path%>/upload/file/improve/<s:property value="customerOpinion.executiveFile"/>">下载改进文件</a>--%>
											href="FileViewAction.action?FilePath=/upload/file/improve/<s:property value="customerOpinion.executiveFile"/>">查看改进文件</a>
									</s:if>
									<s:else>无分析文件
				                    </s:else>
								</s:elseif>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="hidden" name="tag"
									value="<s:property value="tag"/>">
								<input type="hidden" name="customerOpinion.id"
									value="<s:property value="customerOpinion.id"/>">
								<input id="status" type="hidden" name="customerOpinion.status"
									value="<s:property value="customerOpinion.status"/>">
								<input type="button" value="同意"
									onclick="toSubmit('yes','${tag}')">
								<input type="button" value="打回"
									onclick="toSubmit('np','${tag}')">

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
