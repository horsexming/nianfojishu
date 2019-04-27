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
		  <link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js"></script>
		 <script type="text/javascript"
		 		src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
		<%--  <script src="${pageContext.request.contextPath}/js/myJquery.js"></script> --%>
		<script type="text/javascript">
			// 现在window.$和window.jQuery是3.2.1版本:
			console.log($().jquery); // => '3.2.1'
			var $jq = jQuery.noConflict(true);
			// 现在window.$和window.jQuery被恢复成1.8.3版本:
			console.log($().jquery); // => 'jquery-1.8.3.js'
		
		</script>
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
							<span id="title">项目指派</span>
						</td>
						<td align="right">
							<img id="closeimg" alt="" src="<%=basePath%>/images/closeImage.png"
								width="30" height="32" onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showZhipaiWindow" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					项目文档查看
				</h3>
			</div>
			<div>
				<form action="projectPoolAction_searchProFile.action" method="post">
					<table class="table">
						<tr>
							<th>项目名称：</th>
							<th>
								<input type="text" name="projectManageyf.proName" value="${projectManageyf.proName}"/>
							</th>
							<th>模块名称查询：</th>
							<th>
								<input type="text" name="projectManageyf.schedule" value="${projectManageyf.schedule}"/>
							</th>
							<th>
								文件名称：
							</th>
							<th>
								<input type="text" name="projectManageyf.aliasFile" value="${projectManageyf.aliasFile }"/>
							</th>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input" >
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr>
						<th>序号</th>
						<th>项目名称</th>
						<th>项目描述</th>
						<th>文件</th>
						<th>文件包含模块</th>
					</tr>
					<s:iterator value="projectManageyfList" id="pro" status="ps">
						<s:if test="#ps.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${ps.index+1 }
							</td>
							<td>
								${pro.proName }
							</td>
							<td>
								${pro.remark }
							</td>
							<td>
								<div id="showFiles_${ps.index}" align="left">
									<!-- js获取值时使用 -->
									<input type="hidden" id="fileInput_${ps.index}" value="${pro.yfProjectFile}">
									<input type="hidden" id="fileNameInput_${ps.index}" value="${pro.aliasFile}" />
								</div>
							</td>
							<td>
								${pro.schedule }
							</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	$(function(){
		//显示查看
		for(var i=0;i<15;i++){
			var fileInput = $jq("#fileInput_"+i).val();
			if(fileInput!=null && fileInput!=""){
				var str = "";
				var suffix = fileInput.split(",");
				var fileName = $jq("#fileNameInput_"+i).val().split(",");
				for(var iiiii=0;iiiii<suffix.length;iiiii++){
					var subSuffix = suffix[iiiii].substring(suffix[iiiii].indexOf("."));
					str+="<p id='pfile"+i+"'>文件"+(iiiii+1)+"：<a href='${pageContext.request.contextPath}/FileViewAction.action?"+
							"FilePath=/upload/file/project/"+suffix[iiiii]+"'>"+fileName[iiiii]+"</a>"
				}
				$jq("#showFiles_"+i).append(str);
			}	
		}
		
		
	});
	</SCRIPT>
	</body>
</html>
