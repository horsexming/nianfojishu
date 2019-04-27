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
					<table class="table">
						<tr>
							<th>
								客户
							</th>
							<td>
								${customerClaim.otherCompany}
							</td>
							<th>
								索赔标题
							</th>
							<td>
								${customerClaim.title}
							</td>
						</tr>
						<tr>
							<th>
								索赔内容
							</th>
							<td colspan="3">
								${customerClaim.context}
							</td>
						</tr>
							<tr>
								<th>
									分析方案
								</th>
								<td colspan="3">
									${customerClaim.analysisText}
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
											href="FileViewAction.action?FilePath=/upload/file/analysis/<s:property value="customerClaim.analysisFile"/>">查看分析文件</a>
									</s:if>
									<s:else>无分析文件
				                    </s:else>
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
